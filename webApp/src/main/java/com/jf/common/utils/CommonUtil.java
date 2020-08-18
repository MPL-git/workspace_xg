package com.jf.common.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

public class CommonUtil {
	
	private static Map<String, List<String>> orderCodeMap=new HashMap<String, List<String>>();
	private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
	
	private static 	Random random = new Random();
	
	/**
	 * 获取单号,不重复
	 * @return
	 */
	public static String getOrderCode(){
	       
		//生成随机数:当前精确到秒的时间再加6位的数字随机序列
        String rdNum=df.format(new Date());
        String orderCode=null;
        orderCode = rdNum + getRandomNum(4);
        if(orderCodeMap.get(rdNum)==null){
        	orderCodeMap.clear();
        	List<String> orderCodeList=new ArrayList<String>();
        	orderCodeList.add(orderCode);
        	orderCodeMap.put(rdNum, orderCodeList);
        	return orderCode;
        	
        }else{
        	List<String> orderCodeList=orderCodeMap.get(rdNum);
        	if(!orderCodeList.contains(orderCode)){
        		orderCodeList.add(orderCode);
        		return orderCode;
        	}else{
        		return getOrderCode();
        	}
        }
        
	}
	
	/**
	 * 获取指定位数的随机数(纯数字)
	 * 
	 * @param length
	 *            随机数的位数
	 * @return String
	 */
	public static String getRandomNum(int length) {
		if (length <= 0) {
			length = 1;
		}
		StringBuilder res = new StringBuilder();
		int i = 0;
		while (i < length) {
			res.append(random.nextInt(10));
			i++;
		}
		return res.toString();
	}
	
	
	
	/**
	 * 生成带签名的请求数据
	 * @param paramData
	 * @return
	 */
	public static JSONObject createReqData(JSONObject reqParam){
		
		
		String timeStamp=String.valueOf(new Date().getTime() / 1000);
		String nonceStr=getRandomStringByLength(16);
		
		Map<String, String> paramMap=new HashMap<String, String>();
		if(reqParam.containsKey("token")){
			
			paramMap.put("token", reqParam.getString("token"));
		}
		paramMap.put("timeStamp", timeStamp);
		paramMap.put("nonceStr", nonceStr);
		paramMap.put("reqData", reqParam.get("reqData").toString());
		String sign=SignatureUtil.createSignature(paramMap);
		reqParam.put("timeStamp", timeStamp);
		reqParam.put("nonceStr", nonceStr);
		reqParam.put("signature", sign);
		return reqParam;
	}
	
	/**
	 * 获取随机数 	
	 * @param length
	 * @return
	 */
	    public static String getRandomStringByLength(int length) {
	        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < length; i++) {
	            int number = random.nextInt(base.length());
	            sb.append(base.charAt(number));
	        }
	        return sb.toString();
	    }
	    
	    
	    public static String getRealIp() throws SocketException {  
	        String localip = null;// 本地IP，如果没有配置外网IP则返回它  
	        String netip = null;// 外网IP  
	  
	        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();  
	        InetAddress ip = null;  
	        boolean finded = false;// 是否找到外网IP  
	        while (netInterfaces.hasMoreElements() && !finded) {  
	            NetworkInterface ni = netInterfaces.nextElement();  
	            Enumeration<InetAddress> address = ni.getInetAddresses();  
	            while (address.hasMoreElements()) {  
	                ip = address.nextElement();  
	                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP  
	                    netip = ip.getHostAddress();  
	                    finded = true;  
	                    break;  
	                } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()) {// 内网IP  
	                    localip = ip.getHostAddress();  
	                }  
	            }  
	        }  
	  
	        if (netip != null && !"".equals(netip)) {  
	            return netip;  
	        } else {  
	            return localip;  
	        }  
	    }
	    
	    /**
	     * 
	     * 方法描述 ：获取渠道
	     * @author  chenwf: 
	     * @date 创建时间：2017年6月20日 下午7:54:07 
	     * @version
	     * @param sprChnl
	     * @return
	     */
	    public static String getSprChnl(String sprChnl){ 
	    	if(sprChnl.equals("_wandoujia")){
	    		sprChnl = "";
	    	}else if(sprChnl.equals("_360")){
	    		sprChnl = "";
	    	}else if(sprChnl.equals("_baidu")){
	    		sprChnl = "";
	    	}else if(sprChnl.equals("_xiaomi")){
	    		sprChnl = "";
	    	}else if(sprChnl.equals("_tencent")){
	    		sprChnl = "";
	    	}else if(sprChnl.equals("_taobao")){
	    		sprChnl = "";
	    	}else if(sprChnl.equals("_xinggou")){
	    		sprChnl = "1001";
	    	}else{
	    		//苹果一个渠道
	    		sprChnl = "1002";
	    	}
	    	return sprChnl;
	    }
	
	
	public static void main(String[] args) {
		Map<String, String> map=new HashMap<String, String>();
		for (int i = 0; i < 1000555; i++) {
			String a=CommonUtil.getOrderCode();
			System.out.println(a);
			if(map.containsKey(a)){
				System.out.println("---has--"+i+"---"+a);
				break;
			}
			map.put(a, a);
			
		}
		
	}
}
