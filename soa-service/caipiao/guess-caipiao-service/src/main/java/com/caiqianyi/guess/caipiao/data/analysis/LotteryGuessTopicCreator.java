package com.caiqianyi.guess.caipiao.data.analysis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caiqianyi.commons.exception.I18nMessageException;

public class LotteryGuessTopicCreator {
	
	private static Logger logger = LoggerFactory.getLogger(LotteryGuessTopicCreator.class);
	
	private final static String pdr[] = new String[]{"\\=","\\>","\\<","\\>\\=","\\<\\="};
	
	private final static String ljs[] = new String[]{"&&","||"};
	
	private static int has(String array[],String s){
		int count = 0;
		for(int i=0;i<array.length;i++){
			int k = 0;
			while(k < s.length()){
				int index = s.indexOf(array[i].replace("\\", ""),k);
				if(index == -1){
					return count;
				}
				k = index+1;
				count++;
			}
		}
		return count;
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
			String l = str.substring(1,str.length() -1);
			int ei = has(new String[]{"("}, l);
			logger.debug("check|s={},e={},str={},l={},ei={}",s,e,str,l,ei);
			
			Boolean ss = null;
			if(ei > 1){
				ss = check(lots, l);
			}else if(has(ljs, l) > 0
					|| has(pdr, l)  > 0){
				ss = bqCheck(lots, l);
			}
			if(ss != null){
				ls = ls.replace(str, ss+"");
			}
		}
		logger.debug("check|ls={}",ls);
		int ei = has(new String[]{"("}, ls);
		if(ei > 0 && has(ljs, ls) > 0){
			return check(lots, ls);
		}
		return bqCheck(lots, ls);
	}
	
	public static boolean bqCheck(String lots[],String line){
		Integer i = 0,
				last = null;
		List<Integer> resultK = new ArrayList<Integer>();
		List<Boolean> resultV = new ArrayList<Boolean>();
		while(i<line.length()){
			String str = line.substring(i, line.length());
			boolean isHas = false;
			for(int k=0;k<ljs.length;k++){
				int d = str.indexOf(ljs[k]);
				if(d > -1){
					i += d+2;
					String s = str.substring(0, d);
					logger.debug("bqCheck|s={},d={},i={},line={}",s,d,i,line);
					resultK.add(k);
					resultV.add(dxdCheck(lots,s));
					last = k;
					isHas = true;
					break;
				}
			}
			if(isHas == false){
				if(i == 0){
					return dxdCheck(lots, line);
				}
				resultV.add(dxdCheck(lots,str));
				logger.debug("bqCheck|str={},last={}",str,last);
				i = line.length();
			}
		}
		if(resultK.contains(1)){
			return resultV.contains(true);
		}
		logger.debug("bqCheck|line={},result={},is={}",line,resultV,resultV.contains(false));
		return !resultV.contains(false);
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
			logger.debug("calculate|s={},e={},str={}",s,e,str);
			String l = str.substring(1,str.length() -1);
			ls = ls.replace(str, calculateBase(l)+"");
		}
		logger.debug("calculate|ls={}",ls);
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
		System.out.println(check(lots, "(true&&1+2>=3)||true"));
		System.out.println(check(lots, "(((#N1*#N3+#N4)%2=0||1+1=2)&&1+2>=3&&1+#N1=9)&&((1+2)*2*(2+4)=36)"));
		//System.out.println("123&&123||".indexOf("&&"));
		
	}
}
