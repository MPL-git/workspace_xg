package com.single.payment;

import java.util.HashMap;
import java.util.Map;



public class TestMap {

	private static Map<String,Map<String,String>> localmap = new HashMap<String, Map<String,String>>();
	public static void main(String[] args) {
		for (int i =0;i<7;i++) {
			long now = System.currentTimeMillis();
			HashMap<String,String> map =(HashMap<String, String>) localmap.get("111111");
			if(map!=null){
				long firstfailtime = Long.valueOf((String)map.get("firstfailtime")).longValue();//第一笔失败时间
				int failtimes = Integer.valueOf((String)map.get("failtimes")).intValue();//失败次数
				System.out.println(firstfailtime+":"+failtimes);
				if((now-firstfailtime)<=(2*60*1000)){//如果在约定的时间内
					if(failtimes>=6){//次数达到约定次数，进入黑名单
						//存入缓存，时效  10分钟，
						System.out.println("放入黑名单");
						localmap.remove("111111");
					}else{
						map.put("failtimes", (failtimes+1)+"");
					}
				}else{
					map.put("firstfailtime", now+"");
					map.put("failtimes", 1+"");
				}
			}else{
				map = new HashMap<String, String>();
				map.put("firstfailtime", now+"");
				map.put("failtimes", 1+"");
				localmap.put("111111", map);
			}
		}
		
	}
}
