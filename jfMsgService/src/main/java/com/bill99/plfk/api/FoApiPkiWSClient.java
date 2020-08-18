package com.bill99.plfk.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.bill99.plfk.util.CustomerUtil;
import com.bill99.plfk.util.MyX509TrustManager;
import com.bill99.plfk.util.StringUtils;
import com.bill99.schema.fo.settlement.SettlementPkiApiRequest;
import com.bill99.schema.fo.settlement.SettlementPkiApiResponse;


/**
 * webservices 请求应答码
 * */
public class FoApiPkiWSClient {
//private static final String URL = "https://sandbox.99bill.com/fo-batch-settlement/services";
public static final String URL = "https://www.99bill.com/fo-batch-settlement/services";
//生产地址
	private static String responseXML="";//返回的xml
	/**
	 * 用于把请求信息发送给快钱的webservices服务，同时拿到对应的应答信息
	 * */
	public static SettlementPkiApiResponse doit(SettlementPkiApiRequest request) {
		SettlementPkiApiResponse response = null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		try {
			// 创建URL
			

			SSLContext sslContext = SSLContext.getInstance("TLSv1.2", "SunJSSE");//第一个参数为?返回实现指定安全套接字协议的SSLContext对象。第二个为提供者
			TrustManager[] tm = {new MyX509TrustManager()};
			sslContext.init(null, tm, new SecureRandom());
			HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
				public boolean verify(String s, SSLSession sslsession) {
					System.out.println("WARNING: Hostname is not matched for cert.");
						return true;
				}};
			SSLSocketFactory ssf = sslContext.getSocketFactory(); 
			HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
			URL urlString = new URL(FoApiPkiWSClient.URL);
			HttpsURLConnection urlConn = (HttpsURLConnection) urlString.openConnection();
			urlConn.setRequestProperty("content-type","text/xml;charset=utf-8");
			urlConn.setDoOutput(true);
			urlConn.setReadTimeout(1200000);
//			urlConn.setConnectTimeout(1200000);
			PrintWriter out = new PrintWriter(urlConn.getOutputStream());
			String postContent = StringUtils.ReqFormat(CustomerUtil.settlementPkiApiRequestToXml(request));
			if (postContent == null){
				return null;
			}
			out.print(postContent);
			out.close();
			urlConn.connect();
			
			/*获取服务器端返回信息*/
			isr=new InputStreamReader(urlConn.getInputStream());
			StringBuffer sb=new StringBuffer();
			if(isr!=null){
				br = new BufferedReader(isr);
	            String inputLine="";
	            while ((inputLine = br.readLine())!= null){
	                sb.append(inputLine);
	            }
			}
            
            String sbr=new String(sb.toString().getBytes(),"utf-8");
			if (sbr.length() > 0) {
				System.out.println(sbr);
				responseXML=StringUtils.ResFormat(sbr);
				response=CustomerUtil.xmlToSettlementPkiApiResponse(responseXML);
			}
		} catch (MalformedURLException e) {
			System.out.println(e.toString());
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				br.close();
				isr.close();
			} catch (IOException e) {
				br=null;
				isr=null;
				e.printStackTrace();
			}
		}
		return response;
	}
	public static String getResponseXML() {
		return responseXML;
	}
	public static void setResponseXML(String responseXML) {
		FoApiPkiWSClient.responseXML = responseXML;
	}
}
