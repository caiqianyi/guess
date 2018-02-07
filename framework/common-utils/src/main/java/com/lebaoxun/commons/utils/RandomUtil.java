package com.lebaoxun.commons.utils;

import java.util.Random;

public class RandomUtil {
	
	//生成6位随机数个位不等于0
	public static int randomNumber(){
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
		    int index = rand.nextInt(i);
		    int tmp = array[index];
		    array[index] = array[i - 1];
		    array[i - 1] = tmp;
		}
		int result = 0;
		for(int i = 0; i < 6; i++){
		 result = result * 10 + array[i];
		 if(result<10000){
			 continue;
		 }
		}
		if(result<10000){
			return  0  ;
		}else{
			return  result  ;
		}
		
	}
	public static void main(String[] args) {
		int d = RandomUtil.randomNumber();
		System.out.println(d);
	}
 
}
