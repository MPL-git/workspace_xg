package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.BaseDefine;
import com.jf.common.constant.Const;
import com.jf.common.ext.util.JsonKit;
import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.Config;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.entity.MemberInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * web转发控制器的公共基础类，集成了一些基本的方法
 */
public class BaseController  implements Serializable {
	public static String CONTEXTPATH = Config.getProperty("CONTEXTPATH");
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Map<String, Object> map = new HashMap<String, Object>();
	public final static String userSessionKey = "USER_SESSION";
	protected final  String ERROR_PAGE = "/common/success";     //成功页面
	protected final  String SUCCESS_PAGE = "/common/error";  	//系统错误页面
	protected final  String RES_MSG_PAGE = "/common/common_msg";  	//系统操作公共提示页面
	protected final  String OPER_RESULT = "/common/result";		//操作结果页面
	
	//------------ 分页条 参数设置 -----
	private String  PAGE_NUM_SHOW="10"; //数字展现导航页个数  
	private String  PAGE_PER_NUM="10";   //每页展示多少条记录
	
	//------ ajax 操作类返回JSON 数据格式{"statusCode"："","message"：""}-----
	protected final  String  JSON_RESULT_CODE ="statusCode";  //返回结果编码
	protected final  String  JSON_RESULT_MESSAGE="message";     //结果说明

	@Autowired
	private HttpServletRequest httpServletRequest;

	protected Integer getMemberId() {
		MemberInfo memberInfo = (MemberInfo) httpServletRequest.getSession().getAttribute(BaseDefine.MEMBER_INFO);
		if(memberInfo != null && memberInfo.getId() != null){
			return memberInfo.getId();
		}
		return null;
	}

	/**
	 * 
	 * 方法描述 ：request请求字段验证
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月22日 上午10:18:48 
	 * @version
	 * @param obj
	 * @param request
	 * @throws ArgException
	 */
	public void requiredStr(Object[] obj, HttpServletRequest request) throws ArgException{
		JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
		JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
		if(obj != null){
			for(Object object : obj) {
				if(JsonUtils.isEmpty(reqDataJson, object.toString())){
					throw new ArgException("reqData字段"+object+"不能为空");
				}
				if(StringUtil.isBlank(reqDataJson.getString(object.toString()))){
					throw new ArgException(object+"不能为空");
				}
			}
		}
	}
	

	/**
	 * 取验证参数中文名
	 * @param item
	 * @return
	 * @author huangcp Mar 3, 2014
	 */
	private String getParameterName(String item) {
		return item.substring(0, item.indexOf(":"));
	}

	/**
	 * 取验证参数编码
	 * @param item
	 * @return
	 * @author huangcp Mar 3, 2014
	 */
	private String getParameterDesc(String item) {
		return item.substring(item.indexOf(":") + 1);
	}
 protected  HashMap<String,Object> setPageParameters(HttpServletRequest request,HashMap<String,Object> paramMap ){
	   if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		String numPerPage = StringUtil.valueOf(paramMap.get("numPerPage"));
		numPerPage = !"".equals(numPerPage) ? numPerPage : this.PAGE_PER_NUM;
		String pageNum = StringUtil.valueOf(paramMap.get("pageNum"));
		pageNum = !"".equals(pageNum) ? pageNum : "1";
		

		request.setAttribute("numPerPage", numPerPage); // 每页多少条
		request.setAttribute("currentPage", pageNum); // 当前页码
		request.setAttribute("pageNumShown", this.PAGE_NUM_SHOW); // 数字展现导航页个数
		String totalCount=StringUtil.valueOf(request.getParameter("totalCount"));
		String isFirst=StringUtil.valueOf(request.getParameter("isFirst"));
		request.setAttribute("totalCount",totalCount); // 总共多少条
		request.setAttribute("isFirst", isFirst); // 非第一次翻页面记录 

		int curPageId = Integer.valueOf(pageNum);
		int pageRecord = Integer.valueOf(numPerPage);
		paramMap.put("MAX_NUM", pageRecord); // 每页查询多少条
		paramMap.put("MIN_NUM", (curPageId - 1) * pageRecord); // 当前页最小记录行号
	   /*
		 * String startDate=StringUtil.valueOf(map.get("START_DATE")); String
		 * endDate=StringUtil.valueOf(map.get("END_DATE"));
		 * if(!"".equals(startDate)){ map.put("START_DATE",
		 * startDate.replaceAll("-", "")); } if(!"".equals(endDate)){
		 * map.put("END_DATE", endDate.replaceAll("-", "")); }
		 */
       
       return paramMap; 
   }
 
 protected  HashMap<String,Object> setPageParametersLiger(HttpServletRequest request,HashMap<String,Object> paramMap ){
	   if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		String numPerPage = StringUtil.valueOf(paramMap.get("pagesize"));
		numPerPage = !"".equals(numPerPage) ? numPerPage : this.PAGE_PER_NUM;
		String pageNum = StringUtil.valueOf(paramMap.get("page"));
		pageNum = !"".equals(pageNum) ? pageNum : "1";
		
	/*	String totalCount=StringUtil.valueOf(request.getParameter("totalCount"));
		String isFirst=StringUtil.valueOf(request.getParameter("isFirst"));
		request.setAttribute("totalCount",totalCount); // 总共多少条
		request.setAttribute("isFirst", isFirst); // 非第一次翻页面记录 
*/
		int curPageId = Integer.valueOf(pageNum);
		int pageRecord = Integer.valueOf(numPerPage);
		paramMap.put("MAX_NUM", pageRecord); // 每页查询多少条
		paramMap.put("MIN_NUM", (curPageId - 1) * pageRecord); // 当前页最小记录行号
	   /*
		 * String startDate=StringUtil.valueOf(map.get("START_DATE")); String
		 * endDate=StringUtil.valueOf(map.get("END_DATE"));
		 * if(!"".equals(startDate)){ map.put("START_DATE",
		 * startDate.replaceAll("-", "")); } if(!"".equals(endDate)){
		 * map.put("END_DATE", endDate.replaceAll("-", "")); }
		 */
     
     return paramMap; 
 }
 
	
	
    /**  
     * 初始化分页基本参数  
     */  
 protected void initPage(HttpServletRequest request,String totalCount ){    
       
        /**页面状态,这个状态是分页自带的,与业务无关*/  
		request.setAttribute("totalCount", totalCount); // 总共多少条
		request.setAttribute("isFirst", "0"); // 非第一次翻页面记录  
    }    
     
    /**
     * 查询条件赋值
     */
   /* public void setPageParameters(Map<String, Object> map, String maxNumKey, String minNumKey,Pagination page) {
    	if(!StringUtil.isEmpty(StringUtil.objToStr(map.get("pageRecord")))){
    		page.setPageRecord(Integer.parseInt(StringUtil.objToStr(map.get("pageRecord"))));
    	}
		Float pageRecord = Float.valueOf(page.getPageRecord());
		Float TotalRecord = Float.valueOf(page.getTotalRecord());
		double i = Math.ceil(TotalRecord / pageRecord);
		i = i == 0 ? 1 : i;
		page.setTotalPage((int) i);
		int targetPage=0;
		if(null==map.get("targetPage")||StringUtil.isEmpty(StringUtil.objToStr(map.get("targetPage")))){
			targetPage = page.getTargetPage();
			page.setMinNum((page.getTargetPage() - 1)
					* page.getPageRecord());
			page.setMaxNum(page.getTargetPage()
					* page.getPageRecord());
		}else{
			targetPage = Integer.parseInt(StringUtil.objToStr(map.get("targetPage")));
			page.setMinNum((targetPage - 1)* page.getPageRecord());
			page.setMaxNum(targetPage
					* page.getPageRecord());
		}
		if("1".equals(StringUtil.objToStr(map.get("isExport")))){
			page.setMinNum(0);
			page.setMaxNum(99999);
		}
		// 把当前页改为目标页
		page.setCurrentPage(targetPage);
		maxNumKey = StringUtil.isEmpty(maxNumKey) ? "MAX_NUM" : maxNumKey;
		minNumKey = StringUtil.isEmpty(minNumKey) ? "MIN_NUM" : minNumKey;
		map.put(maxNumKey, page.getMaxNum());
		map.put(minNumKey, page.getMinNum());
		// 如果是全量导出
		if (page.getIsExport().equals("all")) {
			map.put(maxNumKey, "60000");
			map.put(minNumKey, "0");
			return;
		}
	}  
    
    protected void setNewPageParameters(HttpServletRequest request,Map<String, Object> map, String maxNumKey, String minNumKey,Pagination page){
    	setPageParameters(map,maxNumKey,minNumKey,page);
    	map.put("".equals(StringUtil.valueOf(maxNumKey))?"MAX_NUM":maxNumKey, page.getPageRecord());
    	String startDate=StringUtil.valueOf(map.get("START_DATE"));
    	String endDate=StringUtil.valueOf(map.get("END_DATE"));
    	if(!"".equals(startDate)){ map.put("START_DATE", startDate.replaceAll("-", "")); }
    	if(!"".equals(endDate)){ map.put("END_DATE", endDate.replaceAll("-", "")); }
    	
    }
    
  
    //获取分页参数的基本条件
    protected Pagination getPage(HttpServletRequest request){    
        
        *//**页面状态,这个状态是分页自带的,与业务无关*//*   
        //初始化分页工具条
    	Pagination	page = new Pagination();
    	
    	if(null!=request.getAttribute("currentPage")){
    		page.setCurrentPage(Integer.parseInt(StringUtil.objToStr(request.getAttribute("currentPage"))));
    	}
		if(null!=request.getAttribute("totalPage")){
			page.setTotalPage(Integer.parseInt(StringUtil.objToStr(request.getAttribute("totalPage"))));		
		}
		if(null!=request.getAttribute("totalRecord")){
			page.setTotalRecord(Integer.parseInt(StringUtil.objToStr(request.getAttribute("totalRecord"))));
			
		}
		if(null!=request.getAttribute("pageRecord")){
			page.setPageRecord(Integer.parseInt(StringUtil.objToStr(request.getAttribute("pageRecord"))));
		}
		
		if(null!=request.getAttribute("targetPage")){
			page.setTargetPage(Integer.parseInt(StringUtil.objToStr(request.getAttribute("targetPage"))));
		}
		
       
        return page;    
    } 
    
    
    
    /**
     * 清除用户信息
     * @param request
     */
    public void removeSessionStaffBean(HttpServletRequest request){
    	request.getSession().removeAttribute(userSessionKey);
    }
    
    /**
	 * 将List设置为输出JSON字符串，并返回包含Json的ModelAndView对象，
	 * 该方法主要用在异步访问的时候，输出list为json格式
	 * @param object 数据
	 * @param resultName 存放list的结构名称，在返回到页面的时候，需要用它来获取值
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView getMavForJson(Object object,String resultName) {
		//1、设置结果
		Map resMap = new HashMap<String,Object>();
		resMap.put(resultName,object);
				
		//2.将结果设置为JSON格式
		MappingJacksonJsonView mapJson = new MappingJacksonJsonView();
		mapJson.setAttributesMap(resMap);
		
		//3.返回控制器目标对象
		ModelAndView r = new ModelAndView();
		r.setView(mapJson);
		
		return r;
	}
	
	protected String getStr(HashMap<String, Object> map,String colName) {
		return StringUtil.objToStr(map.get(colName));
	}
	
	protected String getWebRootPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/"); 
	}


	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	protected void response_json(Map map,HttpServletResponse response){
		try {
			
			JSONArray jsonArray = JSONArray.fromObject(map);
			
	        String jsonstr = jsonArray.toString();
	        
	        String json = jsonstr.substring(1, jsonstr.length() - 1);
	       
	        response.setContentType("text/json; charset=gbk");
	        response.getWriter().write(json);
		} catch (Exception e) {
			LoggerFactory.getLogger(getClass()).warn("MAP转JSON异常");
		}
		
	}
	protected void response_json(List<Map<String,String>> list,HttpServletResponse response){
		try {
			JSONArray jsonArray = JSONArray.fromObject(list);
	        String jsonstr = jsonArray.toString();
	        response.setContentType("text/json; charset=gbk");
	        response.getWriter().write(jsonstr);
		} catch (Exception e) {
			LoggerFactory.getLogger(getClass()).warn("List转JSON异常");
		}
		
	}
	
	protected Integer getMemberId(HttpServletRequest request){
		MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(BaseDefine.MEMBER_INFO);
		if(memberInfo != null && memberInfo.getId() != null){
			return memberInfo.getId();
		}
		return null;
	}
	protected String getMemberIdStr(HttpServletRequest request){
		MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(BaseDefine.MEMBER_INFO);
		if(memberInfo != null && memberInfo.getId() != null){
			return memberInfo.getId().toString();
		}
		return null;
	}
	
	protected Integer getApinMemberId(JSONObject reqPRM,JSONObject reqDataJson,HttpServletRequest request){
		Integer memberId = null;
		if(reqPRM.getString("system").equals(Const.ANDROID) || reqPRM.getString("system").equals(Const.IOS)){
			if(!JsonUtils.isEmpty(reqDataJson, "memberId")){
				memberId = reqDataJson.getInt("memberId");
			}
		}
		if(memberId == null){
			MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(BaseDefine.MEMBER_INFO);
			if(memberInfo != null && memberInfo.getId() != null){
				memberId = memberInfo.getId();
			}
		}
		return memberId;
	}
	
	/**
	 * 
	 * 方法描述 ：null值更改为""
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月22日 上午10:23:19 
	 * @version
	 * @param obj
	 * @return
	 */
	public Object setStrByNull(Object obj){
		if(obj == null){
			obj = "";
		}
		return obj;
	}

	public JSONObject getParams(){
		HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		JSONObject reqPRM = (JSONObject)request.getAttribute("reqPRM");
		JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
		return reqDataJson;
	}

	public String getSystem(){
		HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		JSONObject reqPRM = (JSONObject)request.getAttribute("reqPRM");
		return reqPRM.getString("system");
	}

	public int getVersion(){
		HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		JSONObject reqPRM = (JSONObject)request.getAttribute("reqPRM");
		return reqPRM.optInt("version");
	}

	public <T> List<T> getParaToList(JSONObject reqDataJson, String name, Class<T> clazz) {
		String str = reqDataJson.getString(name);
		if (StrKit.isBlank(str) || str.equals("[]")) {
			return new ArrayList<>();
		}

		return com.alibaba.fastjson.JSONArray.parseArray(str, clazz);
	}

	public <T> T getParaToBean(JSONObject reqDataJson, String name, Class<T> clazz) {
		String str = reqDataJson.getString(name);
		if (StrKit.isBlank(str)) {
			return null;
		} else {
			return com.alibaba.fastjson.JSONObject.parseObject(str, clazz);
		}
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
		renderMap.put("returnCode", ResponseMsg.SUCCESS);
		renderMap.put("returnMsg", ResponseMsg.SUCCESS_MSG);
		return JsonKit.toJson(renderMap);
	}

	public String json(Map<String, Object> data) {
		Map<String, Object> renderMap = new HashMap<String, Object>();
		renderMap.put("returnCode", ResponseMsg.SUCCESS);
		renderMap.put("returnMsg", ResponseMsg.SUCCESS_MSG);
		renderMap.put("returnData", data);
		return JsonKit.toJson(renderMap);
	}
}
