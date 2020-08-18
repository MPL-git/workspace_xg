package com.jf.common.utils;
import com.jf.entity.TaobaokeConfig;
import com.jf.entity.TaobaokeConfigExample;
import com.jf.service.TaobaokeConfigService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.GZIPInputStream;
@Component
@Lazy(false)
public class TaobaoUtil {
	private static TaobaokeConfigService taobaokeConfigService;

	private static final String SIGN_METHOD_MD5 = "md5";
	private static final String SIGN_METHOD_HMAC = "hmac";
	private static final String CHARSET_UTF8 = "utf-8";
	private static final String CONTENT_ENCODING_GZIP = "gzip";

	// TOP服务地址，正式环境需要设置为http://gw.api.taobao.com/router/rest
	private static  String serverUrl = "http://gw.api.taobao.com/router/rest";
	private static  String appKey; // 可替换为您的沙箱环境应用的appKey
	private static  String appSecret; // 可替换为您的沙箱环境应用的appSecret
	private static  String adzoneId;
	public static	Integer mchtId;//淘宝可商品要挂靠的商家
	public static  Integer brandId;//淘宝客商品要挂靠的品牌id
//	private static  String sessionKey = "test"; // 必须替换为沙箱账号授权得到的真实有效sessionKey


	@Resource
	public synchronized void setTaobaokeConfigService(TaobaokeConfigService taobaokeConfigService) {
		TaobaoUtil.taobaokeConfigService = taobaokeConfigService;
		TaobaokeConfigExample taobaokeConfigExample = new TaobaokeConfigExample();
		taobaokeConfigExample.createCriteria().andDelFlagEqualTo("0");
		List<TaobaokeConfig> taobaokeConfigs = taobaokeConfigService.selectByExample(taobaokeConfigExample);
		if(taobaokeConfigs!=null&&taobaokeConfigs.size()>0){
			TaobaokeConfig taobaokeConfig = taobaokeConfigs.get(0);
			appKey = taobaokeConfig.getAppKey();
			appSecret = taobaokeConfig.getAppSecret();
			adzoneId = taobaokeConfig.getAdzoneId();
			mchtId = taobaokeConfig.getMchtId();
			brandId = taobaokeConfig.getBrandId();
		}

	}

	
	
	
	
	
	
	

	/**
	 * 获取淘宝客商品(通用物料搜索API（导购）) 获取多个商品
	 * @param catId 类目id
	 * @param q  搜索词
	 * @param hasCoupon  是否有优惠券
	 * @param materialId  物料Id
	 * @return
	 * @throws IOException
	 */
	public static JSONArray getMaterials(String catId,String q,Boolean hasCoupon,String start_price,String end_price,String start_tk_rate,String end_tk_rate,String need_free_shipment,int pageNo,int pageSize) throws IOException {
		
		JSONArray resultArray =  null;
		
		Map<String, Object> params = new HashMap<String, Object>();
		// 公共参数
		params.put("method", "taobao.tbk.dg.material.optional");
		params.put("app_key", appKey);
//		params.put("session", sessionKey);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		params.put("timestamp", df.format(new Date()));
		params.put("format", "json");
		params.put("v", "2.0");
		params.put("sign_method", "hmac");
		// 业务参数
		params.put("adzone_id", adzoneId);
		params.put("platform", "2");
		params.put("page_no", String.valueOf(pageNo));
		params.put("page_size", String.valueOf(pageSize));
		if(catId!=null){
			params.put("cat", catId);
		}
		
		if(q!=null){
			params.put("q", q);
		}
		
		if(hasCoupon!=null){
			params.put("has_coupon", hasCoupon);
		}
		
		if(!StringUtils.isEmpty(start_price)){
			params.put("start_price", start_price);
		}
		if(!StringUtils.isEmpty(end_price)){
			params.put("end_price", end_price);
		}
		if(!StringUtils.isEmpty(start_tk_rate)){
			BigDecimal startTkRate = new BigDecimal(start_tk_rate);
			startTkRate = startTkRate.multiply(new BigDecimal(100));
			String startTkRateStr = startTkRate.toString();//123.00或者123
			if(startTkRateStr.indexOf(".")>=0){
				startTkRateStr = startTkRateStr.substring(0, startTkRateStr.lastIndexOf("."));//123
			}
			params.put("start_tk_rate", Long.parseLong(startTkRateStr));//如：1234表示12.34%，jsp页面输入12表示12%，故这边需要*100
		}
		if(!StringUtils.isEmpty(end_tk_rate)){
			BigDecimal endTkRate = new BigDecimal(end_tk_rate);
			endTkRate = endTkRate.multiply(new BigDecimal(100));//1111.11或者1111
			String endTkRateStr = endTkRate.toString();
			if(endTkRateStr.indexOf(".")>=0){
				endTkRateStr = endTkRateStr.substring(0, endTkRateStr.lastIndexOf("."));//1111
			}
			params.put("end_tk_rate", Long.parseLong(endTkRateStr));
		}
		if(!StringUtils.isEmpty(need_free_shipment)){
			if(need_free_shipment.equals("1")){
				params.put("need_free_shipment", true);//包邮
			}else{
				params.put("need_free_shipment", false);//false或不设置表示不限
			}
		}
		
		// 签名参数
		params.put("sign", signTopRequest(params, appSecret, SIGN_METHOD_HMAC));
		// 请用API
		JSONObject returnObject= JSONObject.fromObject(callApi(new URL(serverUrl), params));
		if(!returnObject.containsKey("error_response")){
			JSONObject tbkItemGetResponse=returnObject.getJSONObject("tbk_dg_material_optional_response");
			JSONObject results=tbkItemGetResponse.getJSONObject("result_list");
			resultArray =  results.getJSONArray("map_data");
		}
		
		return resultArray;
		
	}

	/**
	 * 获取淘宝客商品列表
	 * @param catId 类目id
	 * @param q  搜索词
	 * @return
	 * @throws IOException
	 */
	public static JSONArray getItems(String catId,String q,int pageNo,int pageSize) throws IOException {
		
		JSONArray resultArray =  null;
		
		Map<String, Object> params = new HashMap<String, Object>();
		// 公共参数
		params.put("method", "taobao.tbk.item.get");
		params.put("app_key", appKey);
//		params.put("session", sessionKey);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		params.put("timestamp", df.format(new Date()));
		params.put("format", "json");
		params.put("v", "2.0");
		params.put("sign_method", "hmac");
		// 业务参数
		params.put("fields", "num_iid");
		params.put("platform", "2");
		params.put("page_no", String.valueOf(pageNo));
		params.put("page_size", String.valueOf(pageSize));
		if(catId!=null){
			params.put("cat", catId);
		}
		
		if(q!=null){
			params.put("q", q);
		}
		
		// 签名参数
		params.put("sign", signTopRequest(params, appSecret, SIGN_METHOD_HMAC));
		// 请用API
		JSONObject returnObject= JSONObject.fromObject(callApi(new URL(serverUrl), params));
		if(!returnObject.containsKey("error_response")){
			JSONObject tbkItemGetResponse=returnObject.getJSONObject("tbk_item_get_response");
			JSONObject results=tbkItemGetResponse.getJSONObject("results");
			resultArray =  results.getJSONArray("n_tbk_item");
		}
		
		return resultArray;
		
	}
	
	/**
	 * 获取淘宝客商品详情信息
	 * @param numIid 商品id
	 * @return
	 * @throws IOException
	 */
	
	public static JSONArray getItemInfo(String numIids) throws IOException {
		
		JSONArray resultArray =  null;
		
		Map<String, Object> params = new HashMap<String, Object>();
		// 公共参数
		params.put("method", "taobao.tbk.item.info.get");
		params.put("app_key", appKey);
//		params.put("session", sessionKey);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		params.put("timestamp", df.format(new Date()));
		params.put("format", "json");
		params.put("v", "2.0");
		params.put("sign_method", "hmac");
		// 业务参数
		params.put("platform", "2");
		params.put("num_iids", numIids);
		
		// 签名参数
		params.put("sign", signTopRequest(params, appSecret, SIGN_METHOD_HMAC));
		// 请用API
		JSONObject returnObject= JSONObject.fromObject(callApi(new URL(serverUrl), params));
		if(!returnObject.containsKey("error_response")){
			JSONObject tbkItemGetResponse=returnObject.getJSONObject("tbk_item_info_get_response");
			JSONObject results=tbkItemGetResponse.getJSONObject("results");
			resultArray =  results.getJSONArray("n_tbk_item");
		}
		return resultArray;
	}
	
	
	/**
	 * 获取淘宝客好券商品
	 * @param numIid 商品id
	 * @return
	 * @throws IOException
	 */
	
	public static JSONArray getCouponItems(String catId,String q,int pageNo,int pageSize) throws IOException {
		
		JSONArray resultArray =  null;
		
		Map<String, Object> params = new HashMap<String, Object>();
		// 公共参数
		params.put("method", "taobao.tbk.dg.item.coupon.get");
		params.put("app_key", appKey);
//		params.put("session", sessionKey);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		params.put("timestamp", df.format(new Date()));
		params.put("format", "json");
		params.put("v", "2.0");
		params.put("sign_method", "hmac");
		// 业务参数
		params.put("adzone_id", adzoneId);
		params.put("platform", "2");
		params.put("page_no", String.valueOf(pageNo));
		params.put("page_size", String.valueOf(pageSize));
		if(catId!=null){
			params.put("cat", catId);
		}
		
		if(q!=null){
			params.put("q", q);
		}
		
		// 签名参数
		params.put("sign", signTopRequest(params, appSecret, SIGN_METHOD_HMAC));
		// 请用API
		JSONObject returnObject= JSONObject.fromObject(callApi(new URL(serverUrl), params));
		
		if(!returnObject.containsKey("error_response")){
			JSONObject tbkItemGetResponse=returnObject.getJSONObject("tbk_dg_item_coupon_get_response");
			JSONObject results=tbkItemGetResponse.getJSONObject("results");
			resultArray =  results.getJSONArray("tbk_coupon");
		}
		return resultArray;
	}
	
	
	/**
	 * 获取淘宝联盟选品库列表
	 * @param numIid 商品id
	 * @return
	 * @throws IOException
	 */
	
	public static JSONObject getFavorites(String fields,int type,int pageNo,int pageSize) throws IOException {
		
		JSONArray resultArray =  null;
		
		Map<String, Object> params = new HashMap<String, Object>();
		// 公共参数
		//params.put("method", "taobao.tbk.uatm.favorites.get");
		params.put("method", "taobao.tbk.dg.optimus.material");
		params.put("app_key", appKey);
		params.put("adzone_id", adzoneId);
		params.put("material_id", 31519);
//		params.put("session", sessionKey);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		params.put("timestamp", df.format(new Date()));
		params.put("format", "json");
		params.put("v", "2.0");
		params.put("sign_method", "hmac");
		// 业务参数
		params.put("fields", fields);//返回的字段列表
		params.put("type", type);
		params.put("page_no", pageNo);
		params.put("page_size", pageSize);
		
		// 签名参数
		params.put("sign", signTopRequest(params, appSecret, SIGN_METHOD_HMAC));
		// 请用API
		JSONObject returnObject= JSONObject.fromObject(callApi(new URL(serverUrl), params));
		System.out.println("returnObject:" + returnObject.toString());
		int totalCount = 0;
		if(!returnObject.containsKey("error_response")){
			/*JSONObject favoritesGetResponse=returnObject.getJSONObject("tbk_uatm_favorites_get_response");
			totalCount=favoritesGetResponse.getInt("total_results");
			JSONObject results=favoritesGetResponse.getJSONObject("results");
			resultArray =  results.getJSONArray("tbk_favorites");*/
			JSONObject favoritesInfo=returnObject.getJSONObject("tbk_dg_optimus_material_response").getJSONObject("result_list").getJSONArray("map_data").getJSONObject(0).getJSONObject("favorites_info");
			totalCount=favoritesInfo.getInt("total_count");
			JSONObject results=favoritesInfo.getJSONObject("favorites_list");
			resultArray =  results.getJSONArray("favorites_detail");
		}
		JSONObject jo = new JSONObject();
		jo.put("Rows", resultArray);
		jo.put("Total", totalCount);
		return jo;
	}
	
	/**
	 * 获取淘宝联盟选品库的宝贝信息
	 * @param numIid 商品id
	 * @return
	 * @throws IOException
	 */
	
	public static JSONArray getFavoritesItem(int favoritesId,int pageNo,int pageSize) throws IOException {
		
		JSONArray resultArray =  null;
		
		Map<String, Object> params = new HashMap<String, Object>();
		// 公共参数
		//params.put("method", "taobao.tbk.uatm.favorites.item.get");
		params.put("method", "taobao.tbk.dg.optimus.material");
		params.put("app_key", appKey);
//		params.put("session", sessionKey);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		params.put("timestamp", df.format(new Date()));
		params.put("format", "json");
		params.put("v", "2.0");
		params.put("sign_method", "hmac");
		// 业务参数
		params.put("adzone_id", adzoneId);
		params.put("material_id", 31539);
		params.put("favorites_id", favoritesId);
		params.put("page_no", pageNo);
		params.put("page_size", pageSize);
		
		// 签名参数
		params.put("sign", signTopRequest(params, appSecret, SIGN_METHOD_HMAC));
		// 请用API
		JSONObject returnObject= JSONObject.fromObject(callApi(new URL(serverUrl), params));
		System.out.println("----------returnObject:" + returnObject.toString());
		if(!returnObject.containsKey("error_response")){
			JSONObject favoritesItemGetResponse=returnObject.getJSONObject("tbk_dg_optimus_material_response");
			if(!favoritesItemGetResponse.getJSONObject("result_list").isNullObject()){
				    JSONObject results = favoritesItemGetResponse.getJSONObject("result_list");
					resultArray = results.getJSONArray("map_data");
			}

		}
		return resultArray;
	}
	
	/**
	 * 获取淘宝客优惠券信息
	 * @param numIid 商品id
	 * @return
	 * @throws IOException
	 */
	
	public static JSONObject getCouponInfo(String itemId,String couponId) throws IOException {
		
		JSONObject resultObject =  null;
		
		Map<String, Object> params = new HashMap<String, Object>();
		// 公共参数
		params.put("method", "taobao.tbk.coupon.get");
		params.put("app_key", appKey);
//		params.put("session", sessionKey);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		params.put("timestamp", df.format(new Date()));
		params.put("format", "json");
		params.put("v", "2.0");
		params.put("sign_method", "hmac");
		// 业务参数
		params.put("item_id", itemId);
		params.put("activity_id", couponId);
		
		// 签名参数
		params.put("sign", signTopRequest(params, appSecret, SIGN_METHOD_HMAC));
		// 请用API
		JSONObject returnObject= JSONObject.fromObject(callApi(new URL(serverUrl), params));
		
		if(!returnObject.containsKey("error_response")){
			JSONObject tbkItemGetResponse=returnObject.getJSONObject("tbk_coupon_get_response");
			resultObject=tbkItemGetResponse.getJSONObject("data");
		}
		return resultObject;
	}

	/**
	 * 对TOP请求进行签名。
	 */
	public static String signTopRequest(Map<String, Object> params, String secret, String signMethod) throws IOException {
		// 第一步：检查参数是否已经排序
		String[] keys = params.keySet().toArray(new String[0]);
		Arrays.sort(keys);

		// 第二步：把所有参数名和参数值串在一起
		StringBuilder query = new StringBuilder();
		if (SIGN_METHOD_MD5.equals(signMethod)) {
			query.append(secret);
		}
		for (String key : keys) {
			String value = String.valueOf(params.get(key));
			if (isNotEmpty(key) && isNotEmpty(value)) {
				query.append(key).append(value);
			}
		}

		// 第三步：使用MD5/HMAC加密
		byte[] bytes;
		if (SIGN_METHOD_HMAC.equals(signMethod)) {
			bytes = encryptHMAC(query.toString(), secret);
		} else {
			query.append(secret);
			bytes = encryptMD5(query.toString());
		}

		// 第四步：把二进制转化为大写的十六进制
		return byte2hex(bytes);
	}

	/**
	 * 对字节流进行HMAC_MD5摘要。
	 */
	private static byte[] encryptHMAC(String data, String secret) throws IOException {
		byte[] bytes = null;
		try {
			SecretKey secretKey = new SecretKeySpec(secret.getBytes(CHARSET_UTF8), "HmacMD5");
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			bytes = mac.doFinal(data.getBytes(CHARSET_UTF8));
		} catch (GeneralSecurityException gse) {
			throw new IOException(gse.toString());
		}
		return bytes;
	}

	/**
	 * 对字符串采用UTF-8编码后，用MD5进行摘要。
	 */
	private static byte[] encryptMD5(String data) throws IOException {
		return encryptMD5(data.getBytes(CHARSET_UTF8));
	}

	/**
	 * 对字节流进行MD5摘要。
	 */
	private static byte[] encryptMD5(byte[] data) throws IOException {
		byte[] bytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			bytes = md.digest(data);
		} catch (GeneralSecurityException gse) {
			throw new IOException(gse.toString());
		}
		return bytes;
	}

	/**
	 * 把字节流转换为十六进制表示方式。
	 */
	private static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString();
	}

	public static String callApi(URL url, Map<String, Object> params) throws IOException {
		String query = buildQuery(params, CHARSET_UTF8);
		byte[] content = {};
		if (query != null) {
			content = query.getBytes(CHARSET_UTF8);
		}

		HttpURLConnection conn = null;
		OutputStream out = null;
		String rsp = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Host", url.getHost());
			conn.setRequestProperty("Accept", "text/xml,text/javascript");
			conn.setRequestProperty("User-Agent", "top-sdk-java");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHARSET_UTF8);
			out = conn.getOutputStream();
			out.write(content);
			rsp = getResponseAsString(conn);
		} finally {
			if (out != null) {
				out.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}

		return rsp;
	}

	private static String buildQuery(Map<String, Object> params, String charset) throws IOException {
		if (params == null || params.isEmpty()) {
			return null;
		}

		StringBuilder query = new StringBuilder();
		Set<Entry<String, Object>> entries = params.entrySet();
		boolean hasParam = false;

		for (Entry<String, Object> entry : entries) {
			String name = entry.getKey();
			String value = String.valueOf(entry.getValue());
			// 忽略参数名或参数值为空的参数
			if (isNotEmpty(name) && isNotEmpty(value)) {
				if (hasParam) {
					query.append("&");
				} else {
					hasParam = true;
				}

				query.append(name).append("=").append(URLEncoder.encode(value, charset));
			}
		}

		return query.toString();
	}

	private static String getResponseAsString(HttpURLConnection conn) throws IOException {
		String charset = getResponseCharset(conn.getContentType());
		if (conn.getResponseCode() < 400) {
			String contentEncoding = conn.getContentEncoding();
			if (CONTENT_ENCODING_GZIP.equalsIgnoreCase(contentEncoding)) {
				return getStreamAsString(new GZIPInputStream(conn.getInputStream()), charset);
			} else {
				return getStreamAsString(conn.getInputStream(), charset);
			}
		} else {// Client Error 4xx and Server Error 5xx
			throw new IOException(conn.getResponseCode() + " " + conn.getResponseMessage());
		}
	}

	private static String getStreamAsString(InputStream stream, String charset) throws IOException {
		try {
			Reader reader = new InputStreamReader(stream, charset);
			StringBuilder response = new StringBuilder();

			final char[] buff = new char[1024];
			int read = 0;
			while ((read = reader.read(buff)) > 0) {
				response.append(buff, 0, read);
			}

			return response.toString();
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}

	private static String getResponseCharset(String ctype) {
		String charset = CHARSET_UTF8;

		if (isNotEmpty(ctype)) {
			String[] params = ctype.split(";");
			for (String param : params) {
				param = param.trim();
				if (param.startsWith("charset")) {
					String[] pair = param.split("=", 2);
					if (pair.length == 2) {
						if (isNotEmpty(pair[1])) {
							charset = pair[1].trim();
						}
					}
					break;
				}
			}
		}

		return charset;
	}

	private static boolean isNotEmpty(String value) {
		int strLen;
		if (value == null || (strLen = value.length()) == 0) {
			return false;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(getItemInfo("558527134875"));
		// System.out.println(getItems(null,"手机",1,20));
		// System.out.println(getCouponItems(null,"手机",1,20));
		// System.out.println(getCouponInfo(582007471616L));
		// System.out.println(getMaterials(null,"手机",true,1,5));
		// System.out.println(getMaterials("558527134875"));
		// System.out.println(getFavorites("favorites_title,favorites_id,type", 2, 1, 10));
	}
	
}