package com.jf.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http请求服务
 * 
 * @author gonghongqing01
 * @since 2016-11-01
 */
public class HttpUtil {
	
	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
    /**
     * http post请求
     * 
     * @param url
     * @param params
     * @return 返回请求结果，如果结果状态为400，返回null
     * @throws Exception
     */
    public static String httpPost(String url, List<NameValuePair> params) {
        HttpClient client = new DefaultHttpClient();
        // 设置连接超时时间
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 500);
        // 设置读取超时时间
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 500);
        HttpPost post = new HttpPost(url);
        HttpEntity formEntity;
        try {
            formEntity = new UrlEncodedFormEntity(params);
            post.setEntity(formEntity);
            HttpResponse response = client.execute(post);
            return handle(response);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * http 发送json
     * 
     * @param url
     * @param json
     * @return
     */
    public static String httpPostJson(String url, String json) {
        HttpClient client = new DefaultHttpClient();
        // 设置连接超时时间
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 500);
        // 设置读取超时时间
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 500);
        HttpPost post = new HttpPost(url);
        StringEntity entity;
        String resData = null;
        try {
            // 解决中文乱码问题
            entity = new StringEntity(json, "UTF-8");
            // 解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            post.setEntity(entity);
            HttpResponse result = client.execute(post);
            resData = EntityUtils.toString(result.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
            return resData;
        }
        return resData;

    }

    /**
     * http get请求
     * 
     * @param url
     * @return 返回请求结果，如果结果状态为400，返回null
     * @throws Exception
     */
    public static String httpGet(String requestUrl) throws Exception {
//        HttpClient client = getWrapHttpClient();
//        // 设置连接超时时间
//        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 500);
//        // 设置读取超时时间
//        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 500);
//        HttpGet request = new HttpGet();
//        request.setURI(new URI(url));
//        HttpResponse response = client.execute(request);
//        return handle(response);


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

    /**
     * http请求结果处理返回
     * 
     * @param response
     * @return 返回请求结果，如果结果状态为400，返回null
     * @throws Exception
     */
    private static String handle(HttpResponse response) throws Exception {
        if (response == null) {
            return null;
        }
        BufferedReader br = null;
        String context = null;
        try {
            int code = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            if ((code >= 200) && (code < 300)) {
                if (entity != null) {
                    br = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";
                    String nl = System.getProperty("line.separator");
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append(nl);
                    }
                    br.close();
                    context = sb.toString();
                }
            } else {
                System.out.println("请求失败,HTTP编码:" + code);
            }
        } catch (Exception e) {
            return null;
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return context;
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
			}

			httpUrlConn.disconnect();
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();

	}
    
    public static String saveFileFromUrl(String url,String targetPath){
        //System.out.println("fileName---->"+filePath);  
        //创建不同的文件夹目录  
        FileOutputStream fileOut = null;  
        HttpURLConnection conn = null;  
        InputStream inputStream = null;  
        try  
       {  
            // 建立链接  
            URL httpUrl=null;  
            
			if (url.contains("https")) {
				httpUrl = new URL(null, url, new sun.net.www.protocol.https.Handler());
			} else {
				httpUrl = new URL(url);
			}
            
            conn=(HttpURLConnection) httpUrl.openConnection();  
            //以Post方式提交表单，默认get方式  
            conn.setRequestMethod("GET");  
            conn.setDoInput(true);    
            conn.setDoOutput(true);  
            // post方式不能使用缓存   
            conn.setUseCaches(false);  
            //连接指定的资源   
            conn.connect();  
            //获取网络输入流  
            inputStream=conn.getInputStream();  
            BufferedInputStream bis = new BufferedInputStream(inputStream);  
            //写入到文件（注意文件保存路径的后面一定要加上文件的名称）  
            fileOut = new FileOutputStream(targetPath);  
            BufferedOutputStream bos = new BufferedOutputStream(fileOut);  
              
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
       } catch (Exception e)  
       {  
            e.printStackTrace();  
            System.out.println("获取文件异常！！:url为："+url);  
            return null;
       }  
          
        return targetPath;  
    }
    
    
        public static HttpClient getWrapHttpClient() throws Exception {  
        	HttpClient base=new DefaultHttpClient();
            SSLContext ctx = SSLContext.getInstance("TLS");  
            X509TrustManager tm = new X509TrustManager() {  
                @Override  
                public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {  
                }  
      
                @Override  
                public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {  
                }  
      
                @Override  
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {  
                    return new java.security.cert.X509Certificate[0];  
                }  
            };  
            ctx.init(null, new TrustManager[]{tm}, null);  
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);  
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
            ClientConnectionManager ccm = base.getConnectionManager();  
            SchemeRegistry sr = ccm.getSchemeRegistry();  
            sr.register(new Scheme("https", ssf, 443));
            return new DefaultHttpClient(ccm, base.getParams());  
        }  
    
    
    
    public static void main(String[] args) {
    	HttpUtil.saveFileFromUrl("http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46","D://aa.jpg");
	}
}
