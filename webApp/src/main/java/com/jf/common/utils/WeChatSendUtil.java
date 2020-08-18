package com.jf.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONObject;


public class WeChatSendUtil {

	
	/**
	 * post请求（普通接口访问）
	 */
	public static String sendPost(String url, Map<String, Object> params, String targetPath) {
		//创建不同的文件夹目录  
        FileOutputStream fileOutputStream = null;
		OutputStreamWriter out = null;
		BufferedInputStream bis = null;  
		BufferedOutputStream bos = null;
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
            //获取网络输入流
            bis = new BufferedInputStream(conn.getInputStream()); 
            //写入到文件（注意文件保存路径的后面一定要加上文件的名称）  
            fileOutputStream = new FileOutputStream(targetPath); 
            bos = new BufferedOutputStream(fileOutputStream);  
            byte[] buf = new byte[4096];  
            int length = bis.read(buf);  
            //保存文件  
            while(length != -1)  
            {  
                bos.write(buf, 0, length);  
                length = bis.read(buf);  
            }  
            bos.close();  
            bis.close();  
            conn.disconnect();
            fileOutputStream.close();
        } catch (Exception e) {            
        	 e.printStackTrace();  
             System.out.println("获取文件异常！！:url为："+url);  
             return null;
        }
        return targetPath;
    }
	
	
	
	
}
