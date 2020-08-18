package com.jf.common.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	public static JSONObject getPostJson(HttpServletRequest request) {
		return JSONObject.fromObject(getPostString(request));
	}

	public static String getPostString(HttpServletRequest request) {

		StringBuffer buffer = new StringBuffer();
		try {
			InputStream inputStream = request.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
		} catch (UnsupportedEncodingException e) {
			// SysLogger.error(HttpUtil.class, "请求字符集不支持，获取请求数据出错");
			e.printStackTrace();
		} catch (IOException e) {
			// SysLogger.error(HttpUtil.class, "IO异常，获取请求出错");
			e.printStackTrace();
		}

		return buffer.toString();
	}

	public static String sendGetRequest(String requestUrl) {

		StringBuffer buffer = new StringBuffer();

		try {
			// 建立连接
			URL url = null;
			if (requestUrl.contains("https")) {
				url = new URL(null, requestUrl, new sun.net.www.protocol.https.Handler());
				logger.debug("send https request------------" + requestUrl + "-----------------");
			} else {
				url = new URL(requestUrl);
				logger.debug("send http request----------------" + requestUrl + "-----------------");
			}
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");

			int responseCode = httpUrlConn.getResponseCode();
			logger.debug("response code -----" + responseCode + "-------------");
			if (responseCode == 200) {
				// 获取输入流
				InputStream inputStream = httpUrlConn.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

				// 读取返回结果
				buffer = new StringBuffer();
				String str = null;
				while ((str = bufferedReader.readLine()) != null) {
					buffer.append(str);
				}

				// 释放资源
				bufferedReader.close();
				inputStreamReader.close();
				inputStream.close();
			} else {
				logger.error("Error code -----" + responseCode + "-------------");
			}
			httpUrlConn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return buffer.toString();

	}
	
	
	
	public static String sendGetRequest(String requestUrl,Map<String, String> headers) {

		StringBuffer buffer = new StringBuffer();

		try {
			// 建立连接
			URL url = null;
			if (requestUrl.contains("https")) {
				url = new URL(null, requestUrl, new sun.net.www.protocol.https.Handler());
				logger.debug("send https request------------" + requestUrl + "-----------------");
			} else {
				url = new URL(requestUrl);
				logger.debug("send http request----------------" + requestUrl + "-----------------");
			}
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");

			if(headers!=null){
				for (String key:headers.keySet()) {
					httpUrlConn.setRequestProperty( key,
							headers.get(key));
				}
			}
			
			int responseCode = httpUrlConn.getResponseCode();
			logger.debug("response code -----" + responseCode + "-------------");
			if (responseCode == 200) {
				// 获取输入流
				InputStream inputStream = httpUrlConn.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

				// 读取返回结果
				buffer = new StringBuffer();
				String str = null;
				while ((str = bufferedReader.readLine()) != null) {
					buffer.append(str);
				}

				// 释放资源
				bufferedReader.close();
				inputStreamReader.close();
				inputStream.close();
			} else {
				logger.error("Error code -----" + responseCode + "-------------");
			}
			httpUrlConn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return buffer.toString();

	}
	
	
	
	
	
	

	public static String sendPostRequest(String requestUrl, String content) {

		StringBuffer buffer = new StringBuffer();
		try {

			URL url = null;
			if (requestUrl.contains("https")) {
				url = new URL(null, requestUrl, new sun.net.www.protocol.https.Handler());
			} else {
				url = new URL(requestUrl);
			}
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod("POST");
			// httpUrlConn.setRequestProperty( "Cookie",
			// "JSESSIONID=62A1A46FD933C9EDC6FCB16E7E4214E9");

			
			
				httpUrlConn.connect();
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 当有数据需要提交时
				if (null != content) {
					// 注意编码格式，防止中文乱码
					outputStream.write(content.getBytes("UTF-8"));
					// outputStream.write(URLEncoder.encode(content,
					// "utf-8").getBytes());
					outputStream.flush();
					
				}
				outputStream.close();
				int responseCode = httpUrlConn.getResponseCode();
				if (responseCode == 200) {
					logger.debug("response code -----" + responseCode + "-------------");
				// 将返回的输入流转换成字符串
				InputStream inputStream = httpUrlConn.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

				String str = null;
				while ((str = bufferedReader.readLine()) != null) {
					buffer.append(str);
				}
				bufferedReader.close();
				inputStreamReader.close();
				// 释放资源
				inputStream.close();
				inputStream = null;
			} else {
				logger.error("Error code -----" + responseCode + "-------------");
			}

			httpUrlConn.disconnect();
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return buffer.toString();

	}
	
	
	public static String sendPostRequest(String requestUrl, String content,Map<String, String> cookies,Map<String, String> headers) {
		
		StringBuffer buffer = new StringBuffer();
		try {
			
			URL url=null;
			if(requestUrl.contains("https")){
				url= new URL(null,requestUrl,new sun.net.www.protocol.https.Handler());
			}else{
				url = new URL(requestUrl);
			}
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod("POST");
			
			if(cookies!=null){
				StringBuffer cookiesBuffer=new StringBuffer();
				for (String key:cookies.keySet()) {
					cookiesBuffer.append(key+"="+cookies.get(key)+";");
				}
				 httpUrlConn.setRequestProperty( "Cookie",
						 cookiesBuffer.toString());
			}
			
			if(headers!=null){
				for (String key:headers.keySet()) {
					httpUrlConn.setRequestProperty( key,
							headers.get(key));
				}
			}
			
			// httpUrlConn.setRequestProperty( "Cookie",
			// "JSESSIONID=62A1A46FD933C9EDC6FCB16E7E4214E9");
			// 当有数据需要提交时
			if (null != content) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				
				outputStream.write(content.getBytes("UTF-8"));
				// outputStream.write(URLEncoder.encode(content,
				// "utf-8").getBytes());
				outputStream.close();
			}
			
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			return buffer.toString();
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
		
	}

	public static String sendPostRequest(String requestUrl, Map<String, String> params) {
		String paramsStr = createPostParamsFromMap(params);
		// SysLogger.forceInfo(HttpUtil.class, "http请求参数:"+paramsStr);
		return sendPostRequest(requestUrl, paramsStr);
	}

	public static String createPostParamsFromMap(Map<String, String> params) {
		StringBuffer paramBuffer = new StringBuffer();
		String paramsStr = "";
		for (String key : params.keySet()) {
			// if(params.get(key)!=null&&!"".equals(params.get(key))){
			try {
				paramBuffer.append("&").append(URLEncoder.encode(key, "utf-8")).append("=");
				if (params.get(key) != null && !"".equals(params.get(key))) {
					paramBuffer.append(URLEncoder.encode(params.get(key), "utf-8"));
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "";
			}
			// paramBuffer.append("&").append(key).append("=").append(params.get(key));
			// }
		}
		if (paramBuffer.length() > 0) {
			paramsStr = paramBuffer.toString().substring(1);
		}
		return paramsStr;
	}
	
	
	
	/**
     * 通过拼接的方式构造请求内容，实现参数传输以及文件传输
     * 
     * @param actionUrl 访问的服务器URL
     * @param params 普通参数
     * @param files 文件参数
     * @return
     * @throws IOException
     */
    public static String SendPostMultipartRequest(String actionUrl, Map<String, String> params, Map<String, File> files) throws IOException
    {
    	StringBuilder sb2 = new StringBuilder();
        String BOUNDARY = java.util.UUID.randomUUID().toString();
        String PREFIX = "--", LINEND = "\r\n";
        String MULTIPART_FROM_DATA = "multipart/form-data";
        String CHARSET = "UTF-8";

        URL uri = new URL(actionUrl);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
        conn.setReadTimeout(5 * 1000); // 缓存的最长时间
        conn.setDoInput(true);// 允许输入
        conn.setDoOutput(true);// 允许输出
        conn.setUseCaches(false); // 不允许使用缓存
        conn.setRequestMethod("POST");
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);
       
        DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
        
        if(params!=null){
        	// 首先组拼文本类型的参数
        	StringBuilder sb = new StringBuilder();
        	for (Map.Entry<String, String> entry : params.entrySet())
        	{
        		sb.append(PREFIX);
        		sb.append(BOUNDARY);
        		sb.append(LINEND);
        		sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
        		sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
        		sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
        		sb.append(LINEND);
        		sb.append(entry.getValue());
        		sb.append(LINEND);
        	}
        	outStream.write(sb.toString().getBytes());
        }
        InputStream in = null;
        // 发送文件数据
        if (files != null)
        {
            for (Map.Entry<String, File> file : files.entrySet())
            {
                StringBuilder sb1 = new StringBuilder();
                sb1.append(PREFIX);
                sb1.append(BOUNDARY);
                sb1.append(LINEND);
                // name是post中传参的键 filename是文件的名称
                sb1.append("Content-Disposition: form-data; name=\""+file.getKey()+"\"; filename=\"" + file.getValue().getName() + "\"" + LINEND);
                sb1.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINEND);
                sb1.append(LINEND);
                outStream.write(sb1.toString().getBytes());

                InputStream is = new FileInputStream(file.getValue());
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1)
                {
                    outStream.write(buffer, 0, len);
                }

                is.close();
                outStream.write(LINEND.getBytes());
            }

            // 请求结束标志
            byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
            outStream.write(end_data);
            outStream.flush();
            // 得到响应码
            int res = conn.getResponseCode();
            if (res == 200)
            {
            	// 将返回的输入流转换成字符串
    			InputStream inputStream = conn.getInputStream();
    			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
    			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    			String str = null;
    			while ((str = bufferedReader.readLine()) != null) {
    				sb2.append(str);
    			}
    			bufferedReader.close();
    			inputStreamReader.close();
    			// 释放资源
    			inputStream.close();
    			inputStream = null;
            }
            outStream.close();
            conn.disconnect();
        }
         return sb2.toString();
    }
    
    
    public static String uploadFile(File file, String RequestURL) 
    {
    		StringBuilder sb1 = new StringBuilder();
            String result = null;
            String BOUNDARY = UUID.randomUUID().toString(); // 边界标识 随机生成
            String PREFIX = "--", LINE_END = "\r\n";
            String CONTENT_TYPE = "multipart/form-data"; // 内容类型
            try {
                URL url = new URL(RequestURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(5000);
                conn.setConnectTimeout(5000);
                conn.setDoInput(true); // 允许输入流
                conn.setDoOutput(true); // 允许输出流
                conn.setUseCaches(false); // 不允许使用缓存
                conn.setRequestMethod("POST"); // 请求方式
                conn.setRequestProperty("Charset", "utf-8"); // 设置编码
                conn.setRequestProperty("connection", "keep-alive");
                conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);

                if (file != null) {
                    /**
                     * 当文件不为空，把文件包装并且上传
                     */
                    DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                    StringBuffer sb = new StringBuffer();
                    sb.append(PREFIX);
                    sb.append(BOUNDARY);
                    sb.append(LINE_END);
                    /**
                     * 注意： name里面的值为服务端需要key 只有这个key 才可以得到对应的文件
                     * filename是文件的名字，包含后缀名的 比如:abc.png
                     */
                    sb.append("Content-Disposition: form-data; name=\"filedata\"; filename=\""
                            + file.getName() + "\"" + LINE_END);
                    sb.append("Content-Type: application/octet-stream; charset=" + "utf-8" + LINE_END);
                    sb.append(LINE_END);
                    dos.write(sb.toString().getBytes());
                   
                 // 将返回的输入流转换成字符串
        			InputStream inputStream = conn.getInputStream();
        			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        			String str = null;
        			while ((str = bufferedReader.readLine()) != null) {
        				sb1.append(str);
        			}
                    
                    
                    result = sb1.toString();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

    

    public static void main(String[] args) {
    	
    	Map<String, String> paramMap=new HashMap<String, String>();
    	Map<String, File> filesMap=new HashMap<String, File>();
    	
    	paramMap.put("wo", "ha");
    	
    	File file1=new File("f://z/2.png");
    	
//    	System.out.println(file1.getName());
    	
    	filesMap.put("woshifile", file1);
    	
		String a=null;
			try {
				a = HttpUtil.SendPostMultipartRequest("http://114.80.232.3:9235/app/json_upload_accept.do", paramMap, filesMap);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			a = HttpUtil.uploadFile(file1,"http://114.80.232.3:9235/app/json_upload_accept.do");
		System.out.println(a);
    }
    

}
