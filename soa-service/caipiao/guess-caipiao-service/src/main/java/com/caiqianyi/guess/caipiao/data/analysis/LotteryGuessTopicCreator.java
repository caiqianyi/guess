package com.caiqianyi.guess.caipiao.data.analysis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import com.caiqianyi.commons.exception.I18nMessageException;

public class LotteryGuessTopicCreator {
	
	private final static String pdr[] = new String[]{"\\=","\\>","\\<","\\>\\=","\\<\\="};
	
	private final static String ljs[] = new String[]{"&&","||"};
	
	private static boolean has(String array[],String s){
		for(int i=0;i<array.length;i++){
			if(s.indexOf(array[i].replace("\\", ""))>-1){
				return true;
			}
		}
		return false;
	}
	
	private static LinkedHashMap<Integer,Integer> pair(String str, char s, char e){
		LinkedHashMap<Integer,Integer> result = new LinkedHashMap<Integer,Integer>();
		List<Integer> i1 = new ArrayList<Integer>();
		for(int i=0;i<str.length();i++){
			if(str.charAt(i) == s){
				i1.add(i);
			}
			if(str.charAt(i) == e){
				Integer end = i == str.length()? i : i+1;
				result.put(i1.remove(i1.size()-1), end);
			}
		}
		return result;
	}
	
	public static boolean check(String lots[],String line){
		LinkedHashMap<Integer,Integer> result = pair(line, '(', ')');
		String ls = line;
		for(Integer s : result.keySet()){
			Integer e = result.get(s);
			String str = line.substring(s,e);
			if(has(ljs, str)
					|| has(pdr, str)){
				String l = str.substring(1,str.length() -1);
				ls = ls.replace(str, bqCheck(lots, l)+"");
			}
		}
		return bqCheck(lots, ls);
	}
	
	public static boolean bqCheck(String lots[],String line){
		Integer i = 0,
				last = null;
		LinkedHashMap<Integer,Boolean> result= new LinkedHashMap<Integer,Boolean>(); 
		while(i<line.length()){
			String str = line.substring(i, line.length());
			boolean isHas = false;
			for(int k=0;k<ljs.length;k++){
				int d = str.indexOf(ljs[k]);
				if(d > -1){
					i += d+2;
					String s = str.substring(0, d);
					result.put(k, dxdCheck(lots,s));
					last = k;
					isHas = true;
					break;
				}
			}
			if(isHas == false){
				if(i == 0){
					return dxdCheck(lots, line);
				}
				result.put(last, dxdCheck(lots,str));
				i = line.length();
			}
		}
		if(result.containsKey(1)){
			return result.containsValue(true);
		}
		return !result.containsValue(false);
	}

	public static boolean dxdCheck(String lots[],String line){
		if("true".equalsIgnoreCase(line.trim()) || 
				"false".equalsIgnoreCase(line.trim())){
			return Boolean.parseBoolean(line);
		}
		for(int i=1;i<=lots.length;i++){
			line = line.replaceAll("#N"+i, Integer.parseInt(lots[i-1])+"");
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
		
		LinkedHashMap<Integer,Integer> result = pair(line, '(', ')');
		String ls = line;
		for(Integer s : result.keySet()){
			Integer e = result.get(s);
			String str = line.substring(s,e);
			String l = str.substring(1,str.length() -1);
			ls = ls.replace(str, calculateBase(l)+"");
		}
		return calculateBase(ls);
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
		System.out.println(check(lots, "((#N1*#N3+#N4)%2=0||1+1=2)&&1+2=3&&((1+2)*2*(2+4)=36)"));
		//System.out.println("123&&123||".indexOf("&&"));
	}
}
