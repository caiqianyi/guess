package com.caiqianyi.guess.caipiao.data.analysis;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caiqianyi.commons.exception.I18nMessageException;

public class LotteryGuessTopicCreator {
	
	private static Logger logger = LoggerFactory.getLogger(LotteryGuessTopicCreator.class);
	
	private final static String regex = "\\([0-9\\+\\-\\*\\/%]+\\)";
	
	private final static String pdr[] = new String[]{"\\=","\\>","\\<","\\>\\=","\\<\\="};

	public static boolean lotteryCheck(String lots[],String line){
		for(int i=1;i<=lots.length;i++){
			line = line.replaceAll("n"+i, Integer.parseInt(lots[i-1])+"");
		}
		int index = -1;
		for(int i=0;i<pdr.length;i++){
			int idex = line.split(pdr[i]).length;
			if(idex < 2){
				continue;
			}
			if(idex > 2){
				throw new I18nMessageException("500","Wrong Expression");
			}
			if(idex == 1 && index > -1){
				throw new I18nMessageException("500","Wrong Expression");
			}
			index = i;
		}
		String lls[] = line.split(pdr[index]);
		String left = lls[0];
		Double result = calculate(left),
				right = Double.parseDouble(lls[1]);
		switch (index) {
		case 1:
			return result > right;
		case 2:
			return result < right;
		case 3:
			return result >= right;
		case 4:
			return result <= right;
		}
		return result.equals(right);
	}

	public static Double calculate(String line) {
		
		line = line.replaceAll(" ", "");
		
		// 创建 Pattern 对象
		Pattern pattern = Pattern.compile(regex);
		// 创建 matcher 对象
		Matcher mather = pattern.matcher(line);
		List<Double> list = new ArrayList<Double>();
		while(mather.find()){
			String s = mather.group();
			Double r = calculateBase(s.substring(1,s.length() -1));
			line = line.replace(s, ""+r);
			list.add(r);
		}
		return calculateBase(line);
	}

	private static Double calculateBase(String str) {
		String temp = "";
		if (str.charAt(0) != '-'
				&& !(str.charAt(0) <= '9' && str.charAt(0) >= '0')) {
			throw new I18nMessageException("500","Wrong Expression");
		}
		LinkedList<Double> list = new LinkedList<Double>();
		LinkedList<Character> optList = new LinkedList<Character>();
		Double doubleTemp;
		boolean isFormerOpt = true;
		for (int index = 0; index <= str.length() - 1; index++) {
			if (index == 0) {
				isFormerOpt = true;
			} else {
				if (str.charAt(index - 1) > '9' || str.charAt(index - 1) < '0') {
					isFormerOpt = true;
				} else {
					isFormerOpt = false;
				}
			}
			if (str.charAt(index) != '+' && str.charAt(index) != '*'
					&& str.charAt(index) != '/' && str.charAt(index) != '%'
					&& (!(str.charAt(index) == '-' && isFormerOpt == false))) {
				temp += str.charAt(index);
			} else {
				doubleTemp = new Double(temp);
				list.add(doubleTemp);
				temp = "";
				optList.add(str.charAt(index));
			}
		}
		doubleTemp = new Double(temp);
		list.add(doubleTemp);
		temp = "";
		/*
		 * for (int index = 0; index <= list.size() - 1; index++) {
		 * System.out.println(list.get(index)); } for (int index = 0; index <=
		 * optList.size() - 1; index++) {
		 * System.out.println(optList.get(index)); }
		 */
		boolean isThereHigherOpt = true;
		while (isThereHigherOpt == true) {
			/*
			 * for (Iterator<Character> it = optList.iterator(); it.hasNext();)
			 * { if (it.next() == '*' || it.next() == '/') { isThereHigherOpt =
			 * true; int index = optList.indexOf(it.next());
			 * 
			 * break; } }
			 */
			isThereHigherOpt = false;
			for (int index = 0; index <= optList.size() - 1; index++) {
				if (optList.get(index) == '*') {
					Double t = list.get(index) * list.get(index + 1);
					list.remove(index + 1);
					list.set(index, t);
					optList.remove(index);
					isThereHigherOpt = true;
					break;
				}
				if (optList.get(index) == '/') {
					Double t = list.get(index) / list.get(index + 1);
					list.remove(index + 1);
					list.set(index, t);
					optList.remove(index);
					isThereHigherOpt = true;
					break;
				}
				if (optList.get(index) == '%') {
					Double t = list.get(index) % list.get(index + 1);
					list.remove(index + 1);
					list.set(index, t);
					optList.remove(index);
					isThereHigherOpt = true;
					break;
				}
			}
		}
		while (optList.isEmpty() == false) {
			for (int index = 0; index <= optList.size() - 1; index++) {
				if (optList.get(index) == '+') {
					Double t = list.get(index) + list.get(index + 1);
					list.remove(index + 1);
					list.set(index, t);
					optList.remove(index);
					break;
				}
				if (optList.get(index) == '-') {
					Double t = list.get(index) + 0.0 - list.get(index + 1);
					list.remove(index + 1);
					list.set(index, t);
					optList.remove(index);
					break;
				}
			}
		}
		/*
		 * System.out.println("/////////////////////////////////"); for (int
		 * index = 0; index <= optList.size() - 1; index++) { //
		 * System.out.println(index); System.out.println(list.get(index));
		 * System.out.println(optList.get(index));
		 * System.out.println(list.get(index + 1)); }
		 */
		if (list.size() == 1) {
			return list.get(0);
		}
		throw new I18nMessageException("500","Wrong Expression");
	}

	public static void main(String[] args) {
		String lots[] = new String[] { "08", "01", "06", "04", "10" };
		System.out.println(lotteryCheck(lots, "(n1*n3+n4)%2=0&&"));
	}
}
