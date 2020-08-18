package com.jf.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.entity.PlatformContact;
import com.jf.entity.PlatformContactCustom;
import com.jf.entity.PlatformContactCustomExample;
import com.jf.entity.PlatformContactExample;
import com.jf.entity.StateCode;
import com.jf.entity.SysStaffInfo;
import com.jf.entity.SysStaffInfoExample;
import com.jf.service.MchtInfoService;
import com.jf.service.PlatformContactService;
import com.jf.service.StatusService;
import com.jf.service.SysStaffInfoService;
import com.jf.service.WebcommonService;
import com.jf.vo.Page;

/**
 * @author Administratorhui
 * @date : 2017年1月18日 上午10:12:08
 */

@Controller
public class PlatformContactController extends BaseController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private PlatformContactService platformContactService;	
	
	@Resource
	private WebcommonService webcommonService;
	
	@Resource
	private SysStaffInfoService sysStaffInfoService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private StatusService statusService;
	
	//初始化界面
	@RequestMapping(value = "/service/platformContact/index.shtml")
	public ModelAndView productBrandIndex(HttpServletRequest request, HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap) {	
		
		String rtPage = "/platformContact/index";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		//状态
		paramMap.put("TABLE_NAME", "BU_PLATFORM_CONTACT");
		paramMap.put("COL_NAME", "STATUS");
		List<?> platformStatus = webcommonService.sysStatusList(paramMap);
		resMap.put("platformStatus", platformStatus);
		//对接人
		paramMap.put("TABLE_NAME", "BU_PLATFORM_CONTACT");
		paramMap.put("COL_NAME", "CONTACT_TYPE");
		List<?> platformType = webcommonService.sysStatusList(paramMap);
		resMap.put("platformType", platformType);
		
		return new ModelAndView(rtPage,resMap);
	}
	
	//获取列表数据
	@RequestMapping(value = "/service/platformContact/data.shtml")
	@ResponseBody
	public Map<String, Object> getListData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap,Page page) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		Integer totalCount =0;
		
		try {
			 
			PlatformContactCustomExample platformContactExample = new PlatformContactCustomExample();
			PlatformContactCustomExample.PlatformContactCustomCriteria createCriteria = platformContactExample.createCriteria();
			createCriteria.andDelFlagEqualTo("0");
			platformContactExample.setOrderByClause(" id desc");
			
			if(!StringUtil.isEmpty(request.getParameter("status")) ){
				createCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			if(!StringUtil.isEmpty(request.getParameter("contactType")) ){
				createCriteria.andContactTypeEqualTo(request.getParameter("contactType"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("SEARCHCHAR"))){
				createCriteria.andContactNameLike("%"+request.getParameter("SEARCHCHAR")+"%");
			}
			
			platformContactExample.setLimitStart(page.getLimitStart());
			platformContactExample.setLimitSize(page.getLimitSize());
			totalCount = platformContactService.countPlatformContactCustomByExample(platformContactExample);
			//返回列表数据
			List<PlatformContactCustom> platformContactCustoms =  platformContactService.selectPlatformContactCustomByExample(platformContactExample);
			
			resMap.put("Rows", platformContactCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resMap;
	}
	
	//查看与联系人相关的商家
	@RequestMapping(value = "/service/platformContact/lookup.shtml")
	@ResponseBody
	public ModelAndView lookupCompany(HttpServletRequest request, HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap,Page page) {
		String rtPage = "/platformContact/lookup";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("ID", paramMap.get("platformID"));
		return new ModelAndView(rtPage,resMap);
	}
	
	
	//联系人相关的商家列表
	@RequestMapping(value = "/service/platformContact/lookupdata.shtml")
	@ResponseBody
	public Map<String, Object>  getCompanyList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		Integer totalCount =0;
		try {			
			paramMap=this.setPageParametersLiger(request,paramMap);
        	totalCount = mchtInfoService.selectMchtInfoListCount(paramMap);
        	dataList = mchtInfoService.selectMchtInfoList(paramMap);
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
			totalCount = 0;
		}
	   return resMap;
	}
	
	
	//添加或修改对接人信息
	@RequestMapping(value = "/service/platformContact/editoraddcontact.shtml")
	public ModelAndView editoraddcontact(HttpServletRequest request) {	
		String rtPage = "/platformContact/editoraddcontact";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		if (!StringUtil.isEmpty(request.getParameter("id"))) {
			int id=Integer.valueOf(request.getParameter("id"));
			PlatformContactCustom platformContactCustom=platformContactService.selectPlatformContactCustomByPrimaryKey(id); 
			resMap.put("platformContactCustom", platformContactCustom);
		}
		
		resMap.put("platformType", DataDicUtil.getStatusList("BU_PLATFORM_CONTACT", "CONTACT_TYPE"));
		resMap.put("platformStatus", DataDicUtil.getStatusList("BU_PLATFORM_CONTACT", "STATUS"));
		return new ModelAndView(rtPage, resMap);
	}
	
	//保持修改或保持添加
	@ResponseBody
	@RequestMapping(value = "/service/platformContact/editSave.shtml")
	public ModelAndView savePlatformContace(HttpServletRequest request,PlatformContact platformContact){
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg =null;
		try {
			if(platformContact.getId() == null){
				
				//判断此用户是否已经是某种对接人类型
				PlatformContactExample platformContactExample=new PlatformContactExample();
				platformContactExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(platformContact.getStaffId());
				if(platformContactService.countByExample(platformContactExample)>0){
					throw new Exception("此用户已经是对接人");
				}
				
				//添加数据
				platformContactService.insertSelective(platformContact);
				platformContact.setCreateBy(platformContact.getId());
				platformContact.setCreateDate(new Date()); 
			}else{
				
				//判断此用户是否已经是某种对接人类型
				PlatformContactExample platformContactExample=new PlatformContactExample();
				platformContactExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(platformContact.getStaffId()).andIdNotEqualTo(platformContact.getId());
				if(platformContactService.countByExample(platformContactExample)>0){
					throw new Exception("此用户已经是对接人");
				}
				
				//更新数据
				platformContact.setUpdateBy(platformContact.getId());
				platformContact.setUpdateDate(new Date()); 
				platformContactService.updateByPrimaryKeySelective(platformContact);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = e.getMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	//获取系统用户
	@ResponseBody
	@RequestMapping(value = "/service/platformContact/staffdata.shtml")
	public Map<String, Object> getStaffData(HttpServletRequest request,HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap, Page page){
		Map<String,Object> resMap = new HashMap<String,Object>();
		Integer totalCount =0;
		String value = "";
		try {
			if (paramMap.get("condition") != null) {
				JSONArray fromObject = JSONArray.fromObject(paramMap.get("condition"));
				if (!fromObject.toString().equals("[]")) {
					JSONObject object = fromObject.getJSONObject(0);
					value = object.getString("value");
				}
			}
			SysStaffInfoExample sysStaffInfoExample = new SysStaffInfoExample();
			SysStaffInfoExample.Criteria criteria = sysStaffInfoExample.createCriteria();
			criteria.andStatusEqualTo("A");
			sysStaffInfoExample.setLimitStart(page.getLimitStart());
			sysStaffInfoExample.setLimitSize(page.getLimitSize());
			if (!StringUtil.isEmpty(value)) {
				criteria.andStaffNameLike("%"+value+"%");
			}
			totalCount = sysStaffInfoService.countByExample(sysStaffInfoExample);
			List<SysStaffInfo> selectByExample = sysStaffInfoService.selectByExample(sysStaffInfoExample);
			resMap.put("Rows", selectByExample);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	
	@RequestMapping(value = "/platformContact/getPlatformContact.shtml")
	@ResponseBody
	public Map<String, Object> getPlatformContact(Model model, HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
				PlatformContactExample platformContactExample=new PlatformContactExample();
				PlatformContactExample.Criteria criteria=platformContactExample.createCriteria();
				criteria.andDelFlagEqualTo("0")
					.andStatusEqualTo("0");
//					.andStaffIdEqualTo(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
				if(!StringUtil.isEmpty(request.getParameter("contactType"))){
					criteria.andContactTypeEqualTo(request.getParameter("contactType"));
				}
				List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExample);
				resMap.put("platformContacts", platformContacts);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	    //获取当前协助人信息
		@RequestMapping(value = "/service/platformContact/assistant.shtml")
		public ModelAndView ediassistant(HttpServletRequest request) {	
			String rtPage = "/platformContact/assistant";//重定向
			Map<String, Object> resMap = new HashMap<String, Object>();
			/*String ID=request.getParameter("id");
			resMap.put("id", ID);*/
			if (!StringUtil.isEmpty(request.getParameter("id"))) {
				int id=Integer.valueOf(request.getParameter("id"));
				PlatformContactCustom platformContactCustom=platformContactService.selectPlatformContactCustomByPrimaryKey(id);			
				resMap.put("platformContactCustom", platformContactCustom);
				
				PlatformContactExample platformContactExample=new PlatformContactExample();
				PlatformContactExample.Criteria criteria=platformContactExample.createCriteria();
				criteria.andDelFlagEqualTo("0").andIdNotEqualTo(platformContactCustom.getId());
				if (platformContactCustom.getContactType().equals("1")) {
					criteria.andContactTypeEqualTo("1");
				}
				if (platformContactCustom.getContactType().equals("2")) {
					criteria.andContactTypeEqualTo("2");
				}
				if (platformContactCustom.getContactType().equals("3")) {
					criteria.andContactTypeEqualTo("3");
				}
				if (platformContactCustom.getContactType().equals("4")) {
					criteria.andContactTypeEqualTo("4");
				}
				if (platformContactCustom.getContactType().equals("5")) {
					criteria.andContactTypeEqualTo("5");
				}
				if (platformContactCustom.getContactType().equals("6")) {
					criteria.andContactTypeEqualTo("6");
				}
				if (platformContactCustom.getContactType().equals("6")) {
					criteria.andContactTypeEqualTo("6");
				}
				if (platformContactCustom.getContactType().equals("7")) {
					criteria.andContactTypeEqualTo("7");
				}
				if (platformContactCustom.getContactType().equals("8")) {
					criteria.andContactTypeEqualTo("8");
				}
				List<PlatformContact> platformContact=platformContactService.selectByExample(platformContactExample);
				resMap.put("platformContactlist", platformContact);
			}
					
			return new ModelAndView(rtPage, resMap);
		}
		
		//修改或添加协助人信息
		@ResponseBody
		@RequestMapping(value = "/platformContact/assistantdata.shtml")
		public ModelAndView ediassistantPlatformContace(HttpServletRequest request, Integer id,String assistantid){	
			Map<String, Object> resMap = new HashMap<String, Object>();
			String rtPage = "/success/success";
			String code = null;
			String msg =null;
			try {
				PlatformContactExample platformContactExample=new PlatformContactExample();
				platformContactExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
				PlatformContact  platformContact=new PlatformContact();
				platformContact.setAssistantId(Integer.valueOf(assistantid));
				platformContact.setCreateBy(id);
				platformContact.setCreateDate(new Date()); 
				platformContactService.updateByExampleSelective(platformContact,platformContactExample);
				  
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage(); 
			} catch (Exception e) {
				code = StateCode.JSON_AJAX_ERROR.getStateCode();
				msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
			}
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);
			return new ModelAndView(rtPage, resMap);
		}

		@RequestMapping(value = "/service/platformContact/setRegisterUrl.shtml")
		@ResponseBody
		public Map<String, Object> setRegisterUrl(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "成功");
			try {
					PlatformContactExample e=new PlatformContactExample();
					e.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(request.getParameter("id")));
					PlatformContact pc = new PlatformContact();
					pc.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
					pc.setUpdateDate(new Date());
					InputStream stream = ActivityAreaNewController.class.getResourceAsStream("/base_config.properties");
					Properties properties = new Properties();
					properties.load(stream);
					String mUrl = properties.getProperty("mUrl");
					stream.close();
					pc.setRegisterUrl(mUrl+"/xgbuy/views/index.html?redirect_url=register/index.html?platformContactId="+request.getParameter("id"));
					platformContactService.updateByExampleSelective(pc, e);
			} catch (Exception e) {
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", e.getMessage());
				e.printStackTrace();
			}
			return resMap;
		}
		
		@RequestMapping(value = "/service/platformContact/setZsSettledUrl.shtml")
		@ResponseBody
		public Map<String, Object> setZsSettledUrl(Model model, HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "成功");
			try {
				PlatformContactExample e=new PlatformContactExample();
				e.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(request.getParameter("id")));
				PlatformContact pc = new PlatformContact();
				pc.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				pc.setUpdateDate(new Date());
				InputStream stream = ActivityAreaNewController.class.getResourceAsStream("/base_config.properties");
				Properties properties = new Properties();
				properties.load(stream);
				String mUrl = properties.getProperty("mUrl");
				stream.close();
				pc.setZsSettledUrl(mUrl+"/settled/views/apply_pop.html?platformContactId="+request.getParameter("id"));
				platformContactService.updateByExampleSelective(pc, e);
			} catch (Exception e) {
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", e.getMessage());
				e.printStackTrace();
			}
			return resMap;
		}
		
		//初始化界面
		@RequestMapping(value = "/service/platformContact/viewZsSettledUrl.shtml")
		public ModelAndView viewZsSettledUrl(HttpServletRequest request) {	
			String rtPage = "/platformContact/viewZsSettledUrl";//重定向
			Map<String, Object> resMap = new HashMap<String, Object>();
			String id = request.getParameter("id");
			PlatformContact platformContact = platformContactService.selectByPrimaryKey(Integer.parseInt(id));
			resMap.put("zsSettledUrl", platformContact.getZsSettledUrl());
			return new ModelAndView(rtPage,resMap);
		}
		
		//初始化界面
		@RequestMapping(value = "/service/platformContact/viewRegisterUrl.shtml")
		public ModelAndView viewRegisterUrl(HttpServletRequest request) {	
			String rtPage = "/platformContact/viewRegisterUrl";//重定向
			Map<String, Object> resMap = new HashMap<String, Object>();
			String id = request.getParameter("id");
			PlatformContact platformContact = platformContactService.selectByPrimaryKey(Integer.parseInt(id));
			resMap.put("registerUrl", platformContact.getRegisterUrl());
			return new ModelAndView(rtPage,resMap);
		}
}

