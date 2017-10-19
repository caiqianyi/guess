package com.caiqianyi.commons.utils;

public class ModuloUtil {
  /**
   *  取模的工具类
   * @param molecule
   * @param denominator
   * @return String
   */
  public static String modulo(int molecule,int denominator){
	  String remainder=String.valueOf(molecule%denominator);
	  int length=remainder.length();
	  String str="";
	  for(int i=0;i<3-length;i++){
		  str+="0";
	  }
	  return str+remainder;
  }
}
