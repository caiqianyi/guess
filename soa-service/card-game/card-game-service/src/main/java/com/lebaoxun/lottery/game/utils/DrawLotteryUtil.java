package com.lebaoxun.lottery.game.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.lebaoxun.lottery.game.core.entity.Prize;

public class DrawLotteryUtil {
	
    private static int drawLottery(List<Prize> giftProbList){
        List<Double> sortRateList = new ArrayList<Double>();
        // 计算概率总和
        Double sumRate = 0D;
        for(Prize prize : giftProbList){
            sumRate += prize.getProb();
        }

        if(sumRate != 0){
            double rate = 0D;   //概率所占比例
            for(Prize prize : giftProbList){
            	Double prob = prize.getProb();
                rate += prob;   
                // 构建一个比例区段组成的集合(避免概率和不为1)
                sortRateList.add(rate / sumRate);
            }

            // 随机生成一个随机数，并排序
            double random = Math.random();
            sortRateList.add(random);
            Collections.sort(sortRateList);

            // 返回该随机数在比例集合中的索引
            return sortRateList.indexOf(random);
        }
        return -1;
    }
    
    public static int draw(List<Prize> giftProbList){
    	Collections.sort(giftProbList,new Comparator<Prize>() {
    		@Override
    		public int compare(Prize o1, Prize o2) {
    			// TODO Auto-generated method stub
    			return o1.getLevel().compareTo(o2.getLevel());
    		}
    	});
    	int id = drawLottery(giftProbList);
    	return getIndex(giftProbList, id);
    }
    
    private static int getIndex(List<Prize> giftProbList,int id){
    	Prize pz = giftProbList.get(id);
    	if(pz.getAmount() != null && pz.getAmount().equals(0)){
    		return getIndex(giftProbList, ++id);
    	}
    	return id;
    }

    public static void main(String[] args) throws InterruptedException {
    	Prize p1 = new Prize();
    	p1.setId(101);
    	p1.setProb(0.0000002D);
    	p1.setAmount(10);
    	p1.setLevel(1);

        Prize p2 = new Prize();
        p2.setId(102);
        p2.setProb(0.000001D);
        p2.setAmount(100);
    	p2.setLevel(2);

        Prize p3 = new Prize();
        p3.setId(103);
        p3.setProb(0.000002D);
        p3.setAmount(1000);
    	p3.setLevel(3);
        
        Prize p4 = new Prize();
        p4.setId(104);
        p4.setProb(0.00001D);
        p4.setAmount(10000);
    	p4.setLevel(4);

        Prize p5 = new Prize();
        p5.setId(105);
        p5.setProb(0.0001D);
    	p5.setLevel(5);

    	Prize p6 = new Prize();
        p6.setId(106);
        p6.setProb(0.001D);
        p6.setLevel(6);
        
        Prize p7 = new Prize();
        p7.setId(107);
        p7.setProb(0.01D);
        p7.setLevel(7);
        
        Prize p8 = new Prize();
        p8.setId(108);
        p8.setProb(0.1D);
        p8.setLevel(8);
        
        Prize p9 = new Prize();
        p9.setId(109);
        p9.setProb(0.8888868D);
        p9.setLevel(9);
        
        List<Prize> list = new ArrayList<Prize>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);
        list.add(p7);
        list.add(p8);
        list.add(p9);

        TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
        for(int k=0;k<3650000;k++){
        	for(int i=0;i<10;i++){
        		int index = draw(list);
        		Prize pz = list.get(index);
        		if(pz.getAmount() != null && pz.getAmount() > 0){
        			pz.setAmount(pz.getAmount()-1);
        		}
        		Integer count = map.get(index);
        		if(count == null){
        			count = 0;
        		}
        		map.put(index, ++count);
        	}
        	//Thread.sleep(10L);
        }
        
        for(Integer index : map.keySet()){
        	Prize pz = list.get(index);
        	System.out.print("count="+map.get(index));
        	System.out.print("      ");
        	System.out.println(new Gson().toJson(pz));
        }
    }
}
