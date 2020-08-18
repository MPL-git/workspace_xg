package com.jf.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.HttpUtil;
import com.gzs.common.utils.StringUtil;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.service.NoticeService;
import com.jf.service.WebcommonService;


@Controller
public class NoticeController extends BaseController{
	@Value("${domain.domainName}")
	private String domainName;
	@Value("${domain.domainNameFile}")
	private String domainNameFile;
	@Value("${notice.filePath}")
	private String noticeFilePath; 
	@Resource
	private NoticeService noticeService;

	@Resource
	private WebcommonService webcommonService;
	@RequestMapping(value = "/notice/list.shtml")
	public ModelAndView comminfoList(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/notice/list";//重定向
		Map<String,Object> resMap = new HashMap<String,Object>(); 
		paramMap.put("TABLE_NAME","SYS_NOTICE"); 
		paramMap.put("COL_NAME","NOTICE_LEVEL");
    	List<?> NoticeLevel = webcommonService.sysStatusList(paramMap);
    	resMap.put("NoticeLevel", NoticeLevel); 
    	paramMap.clear();
    	paramMap.put("TABLE_NAME","SYS_NOTICE"); 
		paramMap.put("COL_NAME","STATUS");
    	List<?> STATUSType = webcommonService.sysStatusList(paramMap);
    	resMap.put("STATUSType", STATUSType);
		return new ModelAndView(rtPage,resMap);
	}  
	@RequestMapping(value = "/notice/datalist.shtml")
	@ResponseBody
	public Map<String, Object> NoticeList(HttpServletRequest request,HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/notice/list";//重定向
		Map<String,Object> resMap = new HashMap<String,Object>();
		List<?> dataList = null;
		Integer totalCount =0;
		try {
			//查询
			paramMap=this.setPageParametersLiger(request,paramMap);
        	String page=StringUtil.valueOf(paramMap.get("page")); 
        	totalCount = noticeService.queryNoticeListTotal(paramMap);
        	dataList = noticeService.queryNoticeList(paramMap);
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
			totalCount = 0;
		}
    	return resMap; 
	} 
	@RequestMapping(value = "/notice/add.shtml")
	public ModelAndView Noticeadd(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/notice/edit";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
	 
				StaffBean staffBean = this.getSessionStaffBean(request);
				paramMap.put("STAFF_ID", staffBean.getStaffID()); 
				List<?> dataList=noticeService.queryNoticeList(paramMap);
	        	if(dataList!=null&&dataList.size()>0){
	        		HashMap<String,Object> tmpMap = (HashMap<String,Object>)dataList.get(0);    
	        	}  

	    		paramMap.put("TABLE_NAME","SYS_NOTICE"); 
	    		paramMap.put("COL_NAME","NOTICE_LEVEL");
	        	List<?> NoticeLevel = webcommonService.sysStatusList(paramMap);
	        	resMap.put("NoticeLevel", NoticeLevel);  
	        	
	        	Date date = new Date();
	        	GregorianCalendar gregorianCalendar = new GregorianCalendar();
	    		gregorianCalendar.setTime(date); 
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    		resMap.put("BGN_DATE", sdf.format(gregorianCalendar.getTime())); 
	    		resMap.put("END_DATE", sdf.format(gregorianCalendar.getTime())); 
	        	//新增
	        	resMap.put("TYPE", "1"); 
		return new ModelAndView(rtPage, resMap);
	}
	@RequestMapping(value = "/notice/edit.shtml")
	public ModelAndView NoticeEdit(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/notice/edit";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		  
    	List<?> dataList=noticeService.queryNoticeList(paramMap);
    	if(dataList!=null&&dataList.size()>0){
    		HashMap<String,Object> tmpMap = (HashMap<String,Object>)dataList.get(0);
    		resMap.putAll(tmpMap);
    		/*
			resMap.put("NOTICE_ID", StringUtil.valueOf(tmpMap.get("NOTICE_ID")));
			resMap.put("NOTICE_TITLE", StringUtil.valueOf(tmpMap.get("NOTICE_TITLE")));
			resMap.put("NOTICE_LEVEL", StringUtil.valueOf(tmpMap.get("NOTICE_LEVEL")));
			resMap.put("BGN_DATE", StringUtil.valueOf(tmpMap.get("BGN_DATE")));
			resMap.put("END_DATE", StringUtil.valueOf(tmpMap.get("END_DATE"))); 
*/
			paramMap.put("TABLE_NAME","SYS_NOTICE"); 
			paramMap.put("COL_NAME","NOTICE_LEVEL");
	    	List<?> NoticeLevel = webcommonService.sysStatusList(paramMap);
	    	resMap.put("NoticeLevel", NoticeLevel); 
    	} 
    	//修改
    	resMap.put("TYPE", "2"); 
return new ModelAndView(rtPage, resMap);
	}
	@RequestMapping(value = "/notice/success.shtml")  
	public ModelAndView saveSuccess(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		StaffBean staffBean = this.getSessionStaffBean(request);
		paramMap.put("LOGIN_STAFF_ID", staffBean.getStaffID()); 
		String type=(String)paramMap.get("TYPE");
		String code = null;
		String msg = "";
		try {
			 
			if("1".equals(type)){
				noticeService.insertNotice(paramMap);
			}else if("2".equals(type)){
				noticeService.editNotice(paramMap); 
			}
			//生成静态页面  http://218.66.56.77/notice/newsInfobyID.shtml?NEWS_ID=100
			String url = "http://" + domainNameFile  + "/notice/noticeInfo.shtml?NOTICE_ID=" + paramMap.get("NOTICE_ID");
			String file=noticeFilePath+ File.separator+"notice_"+ paramMap.get("NOTICE_ID")+".html";
			HttpUtil.createHtmlPage(url, file);//生成静态页面
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);

		return new ModelAndView(rtPage, resMap);
	}
	@RequestMapping(value = "/notice/del.shtml")
	@ResponseBody
	public Map<String, Object>  del(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = RES_MSG_PAGE;//重定向
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		StaffBean staffBean = this.getSessionStaffBean(request);
		paramMap.put("LOGIN_STAFF_ID", staffBean.getStaffID()); 
		String type=(String)paramMap.get("TYPE");
		String code = null;
		String msg = "";
		try {
			 
			 noticeService.deleteNotice(paramMap); 
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap; 
	}
}
