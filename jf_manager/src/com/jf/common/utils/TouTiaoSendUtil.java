package com.jf.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONObject;


public class TouTiaoSendUtil {

	
	/**
	 * get请求（普通接口访问）
	 */
	@SuppressWarnings("rawtypes")
	public static String sendGet(String url, Map<String, Object> params, String accessToken) {
		BufferedReader in = null;        
        StringBuilder result = new StringBuilder(); 
        try {
        	if (params != null) {
        		JSONObject paramsJson = JSONObject.fromObject(params);
        		StringBuilder param = new StringBuilder(); 
        		Iterator iterator = paramsJson.keys();
        		String key = "";
        		while(iterator.hasNext()){
        			if(param.length() > 0 ){
        				param.append("&");
        			}
        			key = iterator.next().toString();
        			param.append(key);
        			param.append("=");
        			param.append(paramsJson.getString(key));	
        		}
        		url = url+"?"+param;
        	}
        	URL realUrl = new URL(url);
            HttpsURLConnection conn =(HttpsURLConnection) realUrl.openConnection();
            // GET方法
            conn.setRequestMethod("GET");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Access-Token", accessToken);
            conn.connect();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {            
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
	
	/**
	 * post请求（普通接口访问）
	 */
	public static String sendPost(String url, Map<String, Object> params, String accessToken) {
		OutputStreamWriter out = null;
        BufferedReader in = null;        
        StringBuilder result = new StringBuilder(); 
        try {
            URL realUrl = new URL(url);
            HttpsURLConnection conn =(HttpsURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("POST");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Access-Token", accessToken);
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数
            if (params != null) {
            	JSONObject paramsJson = JSONObject.fromObject(params);
            	out.write(paramsJson.toString());
            }
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {            
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
	
	
	
	
}
