package com.jf.common.utils.taobaoke;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
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

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.jf.common.utils.StringUtil;
import com.jf.entity.TaobaokeConfig;
import com.jf.entity.TaobaokeConfigExample;
import com.jf.service.TaobaokeConfigService;

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
	private static  String appKey; //可替换为您的沙箱环境应用的appKey
	private static  String appSecret; //可替换为您的沙箱环境应用的appSecret
	private static  String adzoneId;
	public static	Integer mchtId;//淘宝可商品要挂靠的商家
	public static  Integer brandId;//淘宝客商品要挂靠的品牌id
	
	@Resource
	public synchronized void setTaobaokeConfigService(TaobaokeConfigService taobaokeConfigService) {
		TaobaoUtil.taobaokeConfigService = taobaokeConfigService;
		TaobaokeConfigExample taobaokeConfigExample = new TaobaokeConfigExample();
		taobaokeConfigExample.createCriteria().andDelFlagEqualTo("0");
		List<TaobaokeConfig> taobaokeConfigs = taobaokeConfigService.selectByExample(taobaokeConfigExample);
		if(taobaokeConfigs != null && taobaokeConfigs.size() > 0 ) {
			TaobaokeConfig taobaokeConfig = taobaokeConfigs.get(0);
			appKey = taobaokeConfig.getAppKey();
			appSecret = taobaokeConfig.getAppSecret();
			adzoneId = taobaokeConfig.getAdzoneId();
			mchtId = taobaokeConfig.getMchtId();
			brandId = taobaokeConfig.getBrandId();
		}
	}
	
	/**
	 * 
	 * @MethodName: getMaterialOptional
	 * @Description: (淘宝客-推广者-物料搜索 )
	 * @param: pageNo 第几页，默认：1
	 * @param: pageSize 页大小，默认20，1~100
	 * @param: q 商品筛选-查询词
	 * @author Pengl
	 * @date 2019年7月16日 下午5:05:14
	 */
	public static JSONArray getMaterialOptional(int pageNo, int pageSize, String sort, String q, Boolean hasCoupon) throws IOException {
		JSONArray resultArray =  null;
		Map<String, Object> params = new HashMap<String, Object>();
		// 公共参数
		params.put("method", "taobao.tbk.dg.material.optional");
		params.put("app_key", appKey);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		params.put("timestamp", df.format(new Date()));
		params.put("format", "json");
		params.put("v", "2.0");
		params.put("sign_method", SIGN_METHOD_HMAC);
		// 业务参数
		params.put("adzone_id", adzoneId);
		params.put("platform", "2");
		params.put("page_no", String.valueOf(pageNo));
		params.put("page_size", String.valueOf(pageSize));
		if(!StringUtil.isEmpty(sort) ) {
			params.put("sort", sort);
		}
		if(!StringUtil.isEmpty(q) ) {
			params.put("q", q);
		}
		if(hasCoupon != null ) {
			params.put("has_coupon", hasCoupon);
		}
		// 签名参数
		params.put("sign", signTopRequest(params, appSecret, SIGN_METHOD_HMAC));
		// 请用API
		JSONObject returnObject = JSONObject.fromObject(callApi(new URL(serverUrl), params));
		if(!returnObject.containsKey("error_response") ) {
			JSONObject tbkItemGetResponse = returnObject.getJSONObject("tbk_dg_material_optional_response");
			JSONObject results = tbkItemGetResponse.getJSONObject("result_list");
			resultArray =  results.getJSONArray("map_data");
		}
		return resultArray;
	}
	
	/**
	 * 签名算法
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

		// 第四步：把二进制转化为大写的十六进制(正确签名应该为32大写字符串，此方法需要时使用)
		return byte2hex(bytes);
	}

	/**
	 * 对字节流进行HMAC_MD5摘要。
	 */
	public static byte[] encryptHMAC(String data, String secret) throws IOException {
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
	public static String byte2hex(byte[] bytes) {
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
	
	
	
}
