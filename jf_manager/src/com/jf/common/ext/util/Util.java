package com.jf.common.ext.util;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jf.common.ext.exception.BizException;
import com.jf.common.utils.StringUtils;

public class Util {

	private static final Log logger = LogFactory.getLog(Util.class);

	/**
	 * 获取应用基址
	 * 
	 * @param request
	 * @return
	 */
	public static String getBaseUrl(HttpServletRequest request) {
		String servletPath = request.getServletPath();

		String baseUrl = request.getRequestURL().toString();

		if (!servletPath.equals("/")) {
			baseUrl = baseUrl.replaceAll(servletPath, "");
		}

		if (baseUrl.endsWith("/")) {
			baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
		}

		return baseUrl;
	}


	/**
	 * 获取客户端真实IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getRealIp(HttpServletRequest request) {
	    String ip = request.getHeader("WL-Proxy-Client-IP");
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	    	ip = request.getHeader("X-Forwarded-For");
	    	if(!StringUtils.isEmpty(ip)&&!"unknown".equalsIgnoreCase(ip)){
	    		ip=(ip.split(",")[0]).trim();
	    	}
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getHeader("Proxy-Client-IP");
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getRemoteAddr();
	    }
	    return ip;
	  }


	/**
	 * 计算页码
	 * 
	 * @param start
	 * @param limit
	 * @return
	 */
	public static int getPageNumber(int start, int limit) {
		return (start / limit) + 1;
	}

	/**
	 * 计算总页数
	 * 
	 * @param pageSize
	 * @param totalCount
	 * @return
	 */
	public static int getTotalPage(int pageSize, int totalCount) {
		return totalCount % pageSize == 0 ? (totalCount / pageSize) : (totalCount / pageSize) + 1;
	}

	/**
	 * 尝试通过get调用接口
	 * 
	 * @param apiUrl
	 * @param paramMap
	 * @param totalRetryCount
	 *            重试次数
	 * @return
	 */
	public static JSONObject tryGetJson(String apiUrl, Map<String, Object> paramMap, int totalRetryCount) {
		int retriedCount = 1;
		Map<String, String> params = new HashMap<String, String>();
		if (paramMap != null) {
			Set<String> keySet = paramMap.keySet();
			for (String key : keySet) {
				params.put(key, paramMap.get(key) + "");
			}
		}
		while (true) {
			try {
				logger.info("正在进行第[" + retriedCount + "]次调用[" + apiUrl + "], 参数：" + JsonKit.toJson(paramMap));
				String response = HttpKit.get(apiUrl, params);
				logger.info(response);
				return JSONObject.fromObject(response);

			} catch (Exception e) {
				e.printStackTrace();
				retriedCount++;

				if (retriedCount > totalRetryCount) {
                    logger.error("调用系统接口 " + retriedCount + " 次，没有得到正确的返回值");
					throw new BizException("内部服务错误");
				}
			}
		}
	}
	
	/**
	 * 尝试通过get调用接口
	 * 
	 * @param apiUrl
	 * @param paramMap
	 * @param data
	 * @param totalRetryCount
	 * @return
	 */
	public static JSONObject tryPostJson(String apiUrl, Map<String, Object> paramMap, String data, int totalRetryCount) {
		int retriedCount = 1;
		Map<String, String> params = new HashMap<String, String>();
		if (paramMap != null) {
			Set<String> keySet = paramMap.keySet();
			for (String key : keySet) {
				params.put(key, paramMap.get(key) + "");
			}
		}
		while (true) {
			try {
				logger.info("正在进行第[" + retriedCount + "]次调用[" + apiUrl + "], 参数：" + JsonKit.toJson(paramMap));
				String response = HttpKit.post(apiUrl, params, data);
				logger.info(response);
				return JSONObject.fromObject(response);

			} catch (Exception e) {
				e.printStackTrace();
				retriedCount++;

				if (retriedCount > totalRetryCount) {
					throw new BizException("内部服务错误");
				}
			}
		}
	}

}
