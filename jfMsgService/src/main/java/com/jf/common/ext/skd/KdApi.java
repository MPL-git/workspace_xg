package com.jf.common.ext.skd;

import com.alibaba.fastjson.JSONObject;
import com.jf.common.utils.StringUtil;
import com.jf.entity.CombineOrderCustom;
import com.jf.entity.dto.ZzySubscribeDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 快递鸟
 */
public class KdApi {
	
	private final static Log log = LogFactory.getLog(KdApi.class);

	public static String EBusinessID = "1288977";
	public static String AppKey = "ca830a5c-68d4-4b24-90d3-8339dadce324";
	//测试请求url
	public static String SUBSCRIBE_URL = "http://api.kdniao.com/api/dist";
	//private static String SUBSCRIBE_URL = "http://testapi.kdniao.com:8081/api/dist";
	//正式请求url
	//private String ReqURL = "http://api.kdniao.com/api/dist";

	private static String QUERY_URL = "http://api.kdniao.com/Ebusiness/EbusinessOrderHandle.aspx";

	//猪猪快递云
	private static String ZZY_APP_ID = "96152";
	private static String ZZY_OUTER_ID = "mB8dWpdwFC2xb7tG";
	private static String ZZY_CREATE_TASK_URL = "http://yun.zhuzhufanli.com/mini/create/";
	private static String ZZY_QUERY_URL = "http://yun.zhuzhufanli.com/mini/query/";
	private static String ZZY_BATCH_QUERY_URL = "http://yun.zhuzhufanli.com/mini/select/";


	public static void main(String[] args) throws Exception {
		CombineOrderCustom combineOrderCustom = new CombineOrderCustom();
		combineOrderCustom.setReceiverPhone("13600000443");
		combineOrderCustom.setReceiverName("陈某某");
		combineOrderCustom.setProvinceName("福建省");
		combineOrderCustom.setCityName("厦门市");
		combineOrderCustom.setCountyName("湖里区");
		combineOrderCustom.setReceiverAddress("福建省厦门市湖里区观音山");
		KdApi.subscribe("", "YD", "1202748295805",combineOrderCustom);
		//KdApi.queryTraces("YTO", "885094421879289276");
	}

	/**
	 * 订阅
	 * @param combineOrderCustom 
	 */
	public static boolean subscribe(String orderNumber, String shipperCode, String logisticCode, CombineOrderCustom combineOrderCustom) throws Exception {
		JSONObject data = new JSONObject();
		String Mobile = "";
		String Name = "";
		String ProvinceName = "";
		String CityName = "";
		String ExpAreaName = "";
		String Address = "";
		if("YD".equals(shipperCode)){
			if(combineOrderCustom == null || combineOrderCustom.getId() == null){
				combineOrderCustom = new CombineOrderCustom();
				combineOrderCustom.setReceiverPhone("13600000443");
				combineOrderCustom.setReceiverName("陈某某");
				combineOrderCustom.setProvinceName("福建省");
				combineOrderCustom.setCityName("厦门市");
				combineOrderCustom.setCountyName("湖里区");
				combineOrderCustom.setReceiverAddress("福建省厦门市湖里区观音山");
			}
			Mobile = combineOrderCustom.getReceiverPhone();
			if(StringUtil.isBlank(Mobile)){
				Mobile = "13600000443";
			}else{
				StringBuffer sb = new StringBuffer(Mobile);
				Mobile = sb.replace(3, 8, "00000").toString();
			}
			Name = combineOrderCustom.getReceiverName();
			if(StringUtil.isBlank(Name)){
				Name = "聚某某";
			}else{
				Name = Name.substring(0, 1)+"某某";
			}
			ProvinceName = combineOrderCustom.getProvinceName();
			CityName = combineOrderCustom.getCityName();
			ExpAreaName = combineOrderCustom.getCountyName();
			Address = combineOrderCustom.getReceiverAddress();
			JSONObject receiver = new JSONObject();
			receiver.put("Mobile", Mobile);
			receiver.put("Name", Name);
			receiver.put("ProvinceName", ProvinceName);
			receiver.put("CityName", CityName);
			receiver.put("ExpAreaName", ExpAreaName);
			receiver.put("Address", Address);
			
			JSONObject sender = new JSONObject();
			sender.put("Mobile", Mobile);
			sender.put("Name", Name);
			sender.put("ProvinceName", ProvinceName);
			sender.put("CityName", CityName);
			sender.put("ExpAreaName", ExpAreaName);
			sender.put("Address", Address);
			data.put("Receiver", receiver);
			data.put("Sender", sender);
		}else if("SF".equals(shipperCode)){
			if(combineOrderCustom == null || combineOrderCustom.getId() == null){
				return false;
			}
			Mobile = combineOrderCustom.getReceiverPhone();
			data.put("CustomerName", Mobile.substring(Mobile.length()-4,Mobile.length()));
		} else if ("JD".equals(shipperCode)) {
			data.put("CustomerName", combineOrderCustom.getMerchantCode());
		}
		
		data.put("OrderCode", orderNumber);
		data.put("ShipperCode", shipperCode);
		data.put("LogisticCode", logisticCode);
		
		String requestData = data.toJSONString();

		Map<String, String> params = new HashMap<String, String>();
		params.put("RequestData", urlEncoder(requestData, "UTF-8"));
		params.put("EBusinessID", EBusinessID);
		params.put("RequestType", "8008");
		String dataSign=encrypt(requestData, AppKey, "UTF-8");
		params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
		params.put("DataType", "2");

		String result=sendPost(SUBSCRIBE_URL, params);
		log.info(result);

		return isSucc(result);
	}

	/**
	 * Json方式 查询订单物流轨迹
	 * @throws Exception
	 */
	public static String queryTraces(String shipperCode, String logisticCode, CombineOrderCustom CustomerName) throws Exception {
		JSONObject data = new JSONObject();
		//data.put("OrderCode", orderNumber);
		data.put("ShipperCode", shipperCode);
		data.put("LogisticCode", logisticCode);
		if ("SF".equals(shipperCode)) {
			String mobile = CustomerName.getReceiverPhone();
			data.put("CustomerName", mobile.substring(mobile.length()-4, mobile.length()));
		}else if ("JD".equals(shipperCode)){
			data.put("CustomerName", CustomerName.getMerchantCode());
		}
		String requestData = data.toJSONString();

		Map<String, String> params = new HashMap<String, String>();
		params.put("RequestData", urlEncoder(requestData, "UTF-8"));
		params.put("EBusinessID", EBusinessID);
		params.put("RequestType", "8001");
		String dataSign=encrypt(requestData, AppKey, "UTF-8");
		params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
		params.put("DataType", "2");

		String result=sendPost(QUERY_URL, params);
		log.debug(result);

		return result;
	}



	private static boolean isSucc(String response){
		boolean b = false;
		JSONObject jsonObject = JSONObject.parseObject(response);
		if(jsonObject.getBoolean("Success")){
			b = true;
		}else{
			log.error(response);
		}
		return b;
	}



	/**
	 * MD5加密
	 * @param str 内容
	 * @param charset 编码方式
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static String MD5(String str, String charset) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes(charset));
		byte[] result = md.digest();
		StringBuffer sb = new StringBuffer(32);
		for (int i = 0; i < result.length; i++) {
			int val = result[i] & 0xff;
			if (val <= 0xf) {
				sb.append("0");
			}
			sb.append(Integer.toHexString(val));
		}
		return sb.toString().toLowerCase();
	}

	/**
	 * base64编码
	 * @param str 内容
	 * @param charset 编码方式
	 * @throws UnsupportedEncodingException
	 */
	private static String base64(String str, String charset) throws UnsupportedEncodingException {
		String encoded = base64Encode(str.getBytes(charset));
		return encoded;
	}

	@SuppressWarnings("unused")
	public static String urlEncoder(String str, String charset) throws UnsupportedEncodingException{
		String result = URLEncoder.encode(str, charset);
		return result;
	}

	/**
	 * 电商Sign签名生成
	 * @param content 内容
	 * @param keyValue Appkey
	 * @param charset 编码方式
	 * @throws UnsupportedEncodingException ,Exception
	 * @return DataSign签名
	 */
	@SuppressWarnings("unused")
	public static String encrypt (String content, String keyValue, String charset) throws UnsupportedEncodingException, Exception
	{
		if (keyValue != null)
		{
			return base64(MD5(content + keyValue, charset), charset);
		}
		return base64(MD5(content, charset), charset);
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * @param url 发送请求的 URL
	 * @param params 请求的参数集合
	 * @return 远程资源的响应结果
	 */
	@SuppressWarnings("unused")
	public static String sendPost(String url, Map<String, String> params) {
		OutputStreamWriter out = null;
		BufferedReader in = null;
		StringBuilder result = new StringBuilder();
		try {
			URL realUrl = new URL(url);
			HttpURLConnection conn =(HttpURLConnection) realUrl.openConnection();
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// POST方法
			conn.setRequestMethod("POST");
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.connect();
			// 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数
			if (params != null) {
				StringBuilder param = new StringBuilder();
				for (Map.Entry<String, String> entry : params.entrySet()) {
					if(param.length()>0){
						param.append("&");
					}
					param.append(entry.getKey());
					param.append("=");
					param.append(entry.getValue());
					System.out.println(entry.getKey()+":"+entry.getValue());
				}
//				System.out.println("param:"+param.toString());
				out.write(param.toString());
			}
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream(), "UTF-8"));
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

	private static char[] base64EncodeChars = new char[] {
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
			'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
			'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
			'w', 'x', 'y', 'z', '0', '1', '2', '3',
			'4', '5', '6', '7', '8', '9', '+', '/' };

	private static String base64Encode(byte[] data) {
		StringBuffer sb = new StringBuffer();
		int len = data.length;
		int i = 0;
		int b1, b2, b3;
		while (i < len) {
			b1 = data[i++] & 0xff;
			if (i == len)
			{
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
				sb.append("==");
				break;
			}
			b2 = data[i++] & 0xff;
			if (i == len)
			{
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
				sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
				sb.append("=");
				break;
			}
			b3 = data[i++] & 0xff;
			sb.append(base64EncodeChars[b1 >>> 2]);
			sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
			sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
			sb.append(base64EncodeChars[b3 & 0x3f]);
		}
		return sb.toString();
	}

	public static String zzySubscribe(ZzySubscribeDTO zzySubscribeDTO) throws Exception {
		HashMap<String, String> map = new HashMap<>();
		map.put("appid",ZZY_APP_ID);
		map.put("outerid",ZZY_OUTER_ID);
		map.put("zffs","jinbi");
		map.put("kdgs",zzySubscribeDTO.getExpressCompanyCode());
		map.put("kddhs",zzySubscribeDTO.getExpressCode());
		map.put("isBackTaskName","no");
		return doFromDataPost(ZZY_CREATE_TASK_URL, map);
	}

	public static String zzyBatchSubscribe(String expressCompanyCode, String logisticCodes) throws Exception {
		HashMap<String, String> map = new HashMap<>();
		map.put("appid",ZZY_APP_ID);
		map.put("outerid",ZZY_OUTER_ID);
		map.put("zffs","jinbi");
		map.put("kdgs",expressCompanyCode);
		map.put("kddhs",logisticCodes);
		map.put("isBackTaskName","yes");
		return doFromDataPost(ZZY_CREATE_TASK_URL, map);
	}

    public static String zzyAgainQuert(String expressCompanyCode, String expressCode) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("kdgs",expressCompanyCode);
        map.put("kddh",expressCode);
        return doFromDataPost(ZZY_QUERY_URL, map);
    }

	public static String zzyBatchAgainQuert(String zzyTag, String pageNo) throws Exception {
		HashMap<String, String> map = new HashMap<>();
		map.put("appid",ZZY_APP_ID);
		map.put("outerid",ZZY_OUTER_ID);
		map.put("pageno",pageNo);
		map.put("taskname",zzyTag);
		return doFromDataPost(ZZY_BATCH_QUERY_URL, map);
	}

	public static String doFromDataPost(String url, HashMap<String, String> map) throws Exception {
		String result = "";
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(550000).setConnectTimeout(550000)
				.setConnectionRequestTimeout(550000).setStaleConnectionCheckEnabled(true).build();
		client = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
		// client = HttpClients.createDefault();
		URIBuilder uriBuilder = new URIBuilder(url);
		HttpPost httpPost = new HttpPost(uriBuilder.build());
		httpPost.setHeader("Connection", "Keep-Alive");
		httpPost.setHeader("Charset", "UTF-8");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
			params.add(pair);
		}
		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		try {
			response = client.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, "UTF-8");
				}
			}
		} catch (ClientProtocolException e) {
			throw new RuntimeException("创建连接失败" + e);
		} catch (IOException e) {
			throw new RuntimeException("创建连接失败" + e);
		}

		return result;
	}

}