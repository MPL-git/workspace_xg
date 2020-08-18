package com.jf.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jf.common.constant.BaseDefine;
import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.core.BeanInjector;
import com.jf.common.ext.util.ArgKit;
import com.jf.common.ext.util.JsonKit;
import com.jf.common.ext.util.StrKit;
import com.jf.entity.MchtInfoCustom;
import com.jf.entity.MchtUser;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaseController implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 从session中获取user对象
	 * @param request
	 * @return
	 */
	public MchtUser getSessionUserInfo(HttpServletRequest request){
		if(request.getSession().getAttribute(BaseDefine.MCHT_USER)!=null){
    		if(request.getSession().getAttribute(BaseDefine.MCHT_USER) instanceof MchtUser){
    			return (MchtUser)request.getSession().getAttribute(BaseDefine.MCHT_USER);
    		}else{
    			return null;
    		}
    	}else{
    		return null;
    	}
	}
	
	
	/**
	 * 从session中获取mhctInfo对象
	 * @param request
	 * @return
	 */
	
	public MchtInfoCustom getSessionMchtInfo(HttpServletRequest request){
		if(request.getSession().getAttribute(BaseDefine.MCHT_INFO)!=null){
			if(request.getSession().getAttribute(BaseDefine.MCHT_INFO) instanceof MchtInfoCustom){
				return (MchtInfoCustom)request.getSession().getAttribute(BaseDefine.MCHT_INFO);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}

	protected int getMchtId() {
		HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		MchtInfoCustom mchtInfoCustom = getSessionMchtInfo(request);
		return mchtInfoCustom == null ? 0 : mchtInfoCustom.getId();
	}

	public MchtUser getUserInfo(){
		HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		if(request.getSession().getAttribute(BaseDefine.MCHT_USER)!=null){
			if(request.getSession().getAttribute(BaseDefine.MCHT_USER) instanceof MchtUser){
				return (MchtUser)request.getSession().getAttribute(BaseDefine.MCHT_USER);
			}
		}
		return null;
	}

	public MchtInfoCustom getMchtInfo(){
		HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		if(request.getSession().getAttribute(BaseDefine.MCHT_INFO)!=null){
			if(request.getSession().getAttribute(BaseDefine.MCHT_INFO) instanceof MchtInfoCustom){
				return (MchtInfoCustom)request.getSession().getAttribute(BaseDefine.MCHT_INFO);
			}
		}
		return null;
	}

	public String page(String page) {
		return page;
	}

	public String page(Map<String, Object> data, String page) {
		HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Set<String> keySet = data.keySet();
		for (String key : keySet) {
			request.setAttribute(key, data.get(key));
		}

		return page;
	}

	public String json() {
		Map<String, Object> renderMap = new HashMap<String, Object>();
		renderMap.put("success", true);
		renderMap.put("code", "0000");
		return JsonKit.toJson(renderMap);
	}

	public String json(Map<String, Object> data) {
		Map<String, Object> renderMap = new HashMap<String, Object>();
		renderMap.put("success", true);
		renderMap.put("code", "0000");
		renderMap.put("data", data);
		return JsonKit.toJson(renderMap);
	}

	/**
	 * 重定向类型的跳转
	 */
	public String redirect(String url) {
		return "redirect:" + url;
	}

	/**
	 * 转发类型的跳转
	 */
	public String forward(String url) {
		return "forward:" + url;
	}

	public void exportExcel(Map<String, Object> data, HttpServletResponse response){
		String fileName = (String)data.get("fileName");
		XSSFWorkbook xssfWorkbook = (XSSFWorkbook)data.get("xssfWorkbook");
		try {
			response.setContentType("application/octet-stream");// 设置文件输出类型
			response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			//response.setHeader("Content-Length", String.valueOf(inputStream.available()));// 设置输出长度

			OutputStream ouputStream = response.getOutputStream();
			xssfWorkbook.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * 获取请求参数，将它转为int<br>
	 * 不存在返回0
	 *
	 * @param name
	 * @return
	 */
	public int getParaToInt(String name) {
		return toInt(getArgForStr(name), 0);
	}

	/**
	 * 获取请求参数，将它转为int<br>
	 * 不存在返回默认值
	 *
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public Integer getParaToInt(String name, Integer defaultValue) {
		return toInt(getArgForStr(name), defaultValue);
	}

	private Integer toInt(String value, Integer defaultValue) {
		if (value == null || "".equals(value.trim()) || "null".equalsIgnoreCase(value.trim()))
			return defaultValue;
		if (value.startsWith("N") || value.startsWith("n"))
			return -Integer.parseInt(value.substring(1));
		return Integer.parseInt(value);
	}

	/**
	 * 默认为0
	 *
	 * @param name
	 * @return
	 */
	public long getParaToLong(String name) {
		return toLong(getArgForStr(name), 0L);
	}

	public Long getParaToLong(String name, Long defaultValue) {
		return toLong(getArgForStr(name), defaultValue);
	}

	private Long toLong(String value, Long defaultValue) {
		if (value == null || "".equals(value.trim()) || "null".equalsIgnoreCase(value.trim()))
			return defaultValue;
		if (value.startsWith("N") || value.startsWith("n"))
			return -Long.parseLong(value.substring(1));
		return Long.parseLong(value);
	}

	public float getParaToFloat(String name) {
		return toFloat(getArgForStr(name), 0f);
	}

	public float getParaToFloat(String name, float defaultValue) {
		return toFloat(getArgForStr(name), defaultValue);
	}

	private Float toFloat(String value, Float defaultValue) {
		if (StrKit.isBlank(value)) {
			return defaultValue;
		}

		return Float.parseFloat(value);
	}

	public double getParaToDouble(String name) {
		return toDouble(getArgForStr(name), 0d);
	}

	public double getParaToDouble(String name, double defaultValue) {
		return toDouble(getArgForStr(name), defaultValue);
	}

	private Double toDouble(String value, Double defaultValue) {
		if (StrKit.isBlank(value)) {
			return defaultValue;
		}

		return Double.parseDouble(value);
	}

	/**
	 * 获取请求值
	 *
	 * @param name
	 * @return
	 */
	public String getPara(String name) {
		return getArgForStr(name);
	}

	/**
	 * 获取请求值，如果不存在，则设置为默认值
	 *
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public String getPara(String name, String defaultValue) {
		String result = getArgForStr(name);

		return StrKit.isBlank(result) ? defaultValue : result;
	}

	/**
	 * 获取请求参数，将它转为boolean<br>
	 * 不存在返回默认值
	 *
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public Boolean getParaToBoolean(String name, Boolean defaultValue) {
		return toBoolean(getArgForStr(name), defaultValue);
	}

	/**
	 * 获取请求参数，将它转为boolean<br>
	 * 不存在返回false
	 *
	 * @param name
	 * @return
	 */
	public boolean getParaToBoolean(String name) {
		return toBoolean(getArgForStr(name), false);
	}

	private Boolean toBoolean(String value, Boolean defaultValue) {
		if (value == null || "".equals(value.trim()))
			return defaultValue;
		value = value.trim().toLowerCase();
		if ("1".equals(value) || "true".equals(value))
			return Boolean.TRUE;
		else if ("0".equals(value) || "false".equals(value))
			return Boolean.FALSE;
		throw new RuntimeException("Can not parse the parameter \"" + value + "\" to boolean value.");
	}

	/**
	 * 获取日期
	 *
	 * @param name
	 * @return
	 */
	public Date getParaToDate(String name) {
		return toDate(getArgForStr(name), null);
	}

	public Date getParaToDate(String name, Date defaultValue) {
		return toDate(getArgForStr(name), defaultValue);
	}

	private Date toDate(String value, Date defaultValue) {
		try {
			if (value == null || "".equals(value.trim()))
				return defaultValue;
			return new java.text.SimpleDateFormat("yyyy-MM-dd").parse(value.trim());

		} catch (Exception e) {
			throw new RuntimeException("Can not parse the parameter \"" + value + "\" to Date value.");
		}
	}

	/**
	 * 获取日期
	 *
	 * @param name
	 * @return
	 */
	public Date getParaToDateTime(String name) {
		return toDateTime(getArgForStr(name), null);
	}

	public Date getParaToDateTime(String name, Date defaultValue) {
		return toDateTime(getArgForStr(name), defaultValue);
	}

	private Date toDateTime(String value, Date defaultValue) {
		try {
			if (value == null || "".equals(value.trim()))
				return defaultValue;
			return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value.trim());

		} catch (Exception e) {
			throw new RuntimeException("Can not parse the parameter \"" + value + "\" to DateTime value.");
		}
	}



	private String getArgForStr(String name) {
		return (String) ArgKit.getArgsMap().get(name);
	}

	/**
	 * Get bean from arg map.
	 */
	public <T> T getBean(Class<T> modelClass, String modelName, Map<String, Object> argsMap) {
		return (T)BeanInjector.inject(modelClass, modelName, argsMap, false);
	}

	/**
	 * Returns the value of the named attribute as an Object, or null if no attribute of the given name exists.
	 * @param name a String specifying the name of the attribute
	 * @return an Object containing the value of the attribute, or null if the attribute does not exist
	 */
	public <T> T getAttr(String name) {
		HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return (T)request.getAttribute(name);
	}

	/**
	 * 执行业务
	 */
	public Map<String, Object> doAction(ABaseCommand biz) {
		biz.initContext(this);
		biz.initArgs(ArgKit.getArgsMap());
		biz.init();
		biz.doCommand();
		return biz.getData();
	}

	public <T> List<T> getParaToList(String name, Class<T> clazz) {
		String str = getPara(name);
		if (StrKit.isBlank(str) || str.equals("[]")) {
			return new ArrayList<>();
		}

		return JSONArray.parseArray(str, clazz);
	}

	public <T> T getParaToBean(String name, Class<T> clazz) {
		if (StrKit.isBlank(getPara(name))) {
			return null;
		} else {
			JSONObject jsonObject = JSONObject.parseObject(getPara(name));
			return this.getBean(clazz, (String)null, jsonObject);
		}
	}

}
