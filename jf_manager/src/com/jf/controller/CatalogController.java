package com.jf.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.beans.Menu;
import com.gzs.common.utils.DateUtil;
import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.entity.Catalog;
import com.jf.entity.CatalogExample;
import com.jf.entity.InformationExample;
import com.jf.entity.MchtInfoCustom;
import com.jf.entity.MchtInfoCustomExample;
import com.jf.entity.MchtSettledApply;
import com.jf.entity.MchtSettledApplyPic;
import com.jf.entity.MchtSettledApplyPicExample;
import com.jf.entity.PlatformContact;
import com.jf.entity.PlatformContactExample;
import com.jf.entity.PlatformMsg;
import com.jf.entity.StaffBean;
import com.jf.entity.StaffOpinionFeedback;
import com.jf.entity.StaffOpinionFeedbackCustom;
import com.jf.entity.StaffOpinionFeedbackCustomExample;
import com.jf.entity.StaffOpinionFeedbackExample;
import com.jf.entity.StaffOpinionFeedbackPic;
import com.jf.entity.StaffOpinionFeedbackPicExample;
import com.jf.entity.StaffReply;
import com.jf.entity.StaffReplyCustom;
import com.jf.entity.StaffReplyCustomExample;
import com.jf.entity.StaffReplyExample;
import com.jf.entity.StaffReplyPic;
import com.jf.entity.StaffReplyPicExample;
import com.jf.entity.StateCode;
import com.jf.entity.SysOrganization;
import com.jf.entity.SysOrganizationExample;
import com.jf.entity.SysStatus;
import com.jf.service.CatalogService;
import com.jf.service.InformationService;
import com.jf.service.StaffOpinionFeedbackPicService;
import com.jf.service.StaffOpinionFeedbackService;
import com.jf.service.StaffReplyPicService;
import com.jf.service.StaffReplyService;
import com.jf.service.SysOrganizationService;
import com.jf.vo.Page;

/**
 * @author Administrator
 * 栏目管理控制器
 */

@Controller
public class CatalogController extends BaseController{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private CatalogService catalogService;

	@Resource
	private InformationService informationService;	
	
	@Resource
	private SysOrganizationService sysOrganizationService;
	
	@Resource
	private StaffOpinionFeedbackService staffOpinionFeedbackService;
	
	@Resource
	private StaffOpinionFeedbackPicService staffOpinionFeedbackPicService;
	
	@Resource
	private StaffReplyService staffReplyService;
	
	@Resource
	private StaffReplyPicService staffReplyPicService;
	
	@RequestMapping(value = "/catalog/index.shtml")
	 public ModelAndView productTypeFind(HttpServletRequest request, String catalogs, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		/*String rtPage = "/catalog/index";// 首页
*/		String rtPage =null;
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		try {
			List<Menu> selectCatalog = catalogService.selectCatalog();
			ObjectMapper objectMapper = new ObjectMapper();
			resMap.put("catalogList", objectMapper.writeValueAsString(selectCatalog));
			if (catalogs.equals("1")) {//信息查看
				rtPage = "/infomation/informationView";
			}else {//栏目管理
				rtPage = "/catalog/index";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value ="/catalog/list.shtml")
	public ModelAndView productTypeList(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/catalog/list";//重定向
		Map<String,Object> resMap = new HashMap<String,Object>();
		
		resMap.put("id", paramMap.get("id"));
		return new ModelAndView(rtPage,resMap);
	} 
	
	@RequestMapping(value = "/catalog/datalist.shtml")
	@ResponseBody //ModelAndView
	public  Map<String, Object> datalist(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		String totalCount ="0";
		try {
			
			int id=Integer.valueOf(request.getParameter("id"));
			String parameter = request.getParameter("catalogname");
			paramMap.put("search", parameter);
			CatalogExample example = new CatalogExample();
			CatalogExample.Criteria catalogCriteria = example.createCriteria();
			
			catalogCriteria.andParentIdEqualTo(id);
			
			int getprarent = catalogService.countByExample(example);
			
		    paramMap.put("ISNO", getprarent);
			paramMap=this.setPageParametersLiger(request,paramMap);
        	totalCount = catalogService.queryDataCount(paramMap);
        	dataList = catalogService.selectProductTypeList(paramMap);
        	resMap.put("id", paramMap.get("id"));
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
			totalCount = "0";
		}
	   return resMap;
	}
	
	@RequestMapping(value="/catalog/editcatalog.shtml")
	public ModelAndView editcatalog(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap){
		String rtPage = "/catalog/editcatalog";//重定向
		Map<String,Object> resMap = new HashMap<String,Object>();
		//栏目状态
		List<SysStatus> status = DataDicUtil.getTableStatus("BU_CATALOG", "STATUS");
		if (paramMap!=null) {
			if(paramMap.get("todo")!=null && paramMap.get("todo").equals("add")){
				//直接去添加栏目
				paramMap=this.setPageParametersLiger(request,paramMap);
				String parentname = catalogService.getparentByid(paramMap);
				resMap.put("parentname", parentname);
				resMap.put("parent_id", paramMap.get("id"));
			}else{
				if ( paramMap.get("id")!=null) {
					//根据id获取信息
					paramMap=this.setPageParametersLiger(request,paramMap);
					List<?> selectProductTypeListByid = catalogService.selectProductTypeListByid(paramMap);
					if (selectProductTypeListByid.size()>0) {
						resMap.put("catalog", selectProductTypeListByid.get(0));
					}
				}
			}
		}
		
		resMap.put("id", paramMap.get("id"));
		resMap.put("todo",paramMap.get("todo"));
		resMap.put("status", status);
		return new ModelAndView(rtPage,resMap);
	}
	
	//保存添加
	@RequestMapping(value="/catalog/addcatalog.shtml")
	public ModelAndView catalogSave(HttpServletRequest request,Catalog catalog,String method) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = null;
		String msg =null;
		String type="";
		String prodName="";
		StaffBean staffBean = this.getSessionStaffBean(request);
		int staffId=Integer.valueOf(staffBean.getStaffID());
		catalog.setCreateBy(staffId);
		catalog.setUpdateBy(staffId);
		catalog.setDelFlag("0");
		catalog.setUpdateDate(new Date());
		prodName = catalog.getFrontName();
		try {
			if (method.equals("update")) {
				//更新
				type = "2";
				catalogService.updateByPrimaryKeySelective(catalog);
			}else{
				//添加
				type = "1";
				catalogService.insertSelective(catalog);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put("type", type);
		resMap.put("prodName", prodName);
		resMap.put("id", catalog.getId());
		resMap.put("name", prodName);
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);

		return new ModelAndView(rtPage, resMap);
	}
	
	//判断是否存在栏目目录
	@RequestMapping(value = "/catalog/checkCatalog.shtml")
	@ResponseBody
	public Map<String, Object> checkCatalog(HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		String name=request.getParameter("name");
		CatalogExample example = new CatalogExample();
		example.createCriteria().andCatalogEqualTo(name).andDelFlagEqualTo("0");
		
		int count=catalogService.countByExample(example);
		resMap.put("count", count);
		if(count>0){
			resMap.put("hased", "1");
		}else{
			resMap.put("hased", "0");
		}
		return resMap;
	}
	
	//删除时先判断是否有子目录
	@ResponseBody
	@RequestMapping(value = "/catalog/checkchildCatalog.shtml")
	public Map<String, Object> checkchildCatalog(HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		String id=request.getParameter("name").toString();
		CatalogExample example = new CatalogExample();
		example.createCriteria().andParentIdEqualTo(Integer.valueOf(id)).andDelFlagEqualTo("0");
		int count=catalogService.countByExample(example);
		resMap.put("count", count);
		if(count>0){
			resMap.put("hased", "1");
		}else{
			resMap.put("hased", "0");
		}
		return resMap;
	}
	
	//删除
	@ResponseBody
	@RequestMapping(value="/catalog/cancle.shtml")
	public Map<String, Object> catalogCancle(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = null;
		String msg =null;
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			String id=request.getParameter("id").toString();
			InformationExample informationExample = new InformationExample();
			InformationExample.Criteria informationCriteria = informationExample.createCriteria();
			informationCriteria.andDelFlagEqualTo("0").andCatalogIdEqualTo(Integer.valueOf(id));
			int infoCount = informationService.countByExample(informationExample);
			if (infoCount>0){
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, "请转移或删除栏目下信息后再操作");
				return resMap;
			}
			
			CatalogExample example = new CatalogExample();
			CatalogExample.Criteria createCriteria = example.createCriteria();
			createCriteria.andDelFlagEqualTo("0").andIdEqualTo(Integer.valueOf(id));
			List<Catalog> selectByExample = catalogService.selectByExample(example);
			if (selectByExample.size() > 0) {
				Catalog catalog = selectByExample.get(0);
				catalog.setDelFlag("1");
				catalog.setUpdateBy(staffId);
				catalog.setUpdateDate(new Date());
				catalogService.updateByPrimaryKeySelective(catalog);
			}
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
	
	//我的意见反馈
	@RequestMapping("/staffOpinionFeedback/list.shtml")
	public ModelAndView staffopinionFeedback(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/staffOpinionFeedback/list");
		SysOrganizationExample sysOrganizationExample=new SysOrganizationExample();
		sysOrganizationExample.createCriteria().andStatusEqualTo("A");
		List<SysOrganization> sysOrganizationList=sysOrganizationService.selectByExample(sysOrganizationExample);
		m.addObject("sysOrganizationList",sysOrganizationList);		
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/staffOpinionFeedback/data.shtml")
	public Map<String, Object> gestaffopinionFeedbackList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<StaffOpinionFeedbackCustom> dataList = null;
		Integer totalCount = 0;
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			
			StaffOpinionFeedbackCustomExample staffOpinionFeedbackCustomExample = new StaffOpinionFeedbackCustomExample();
			StaffOpinionFeedbackCustomExample.StaffOpinionFeedbackCustomCriteria staffOpinionFeedbackCustomCriteria = staffOpinionFeedbackCustomExample.createCriteria();
			staffOpinionFeedbackCustomCriteria.andDelFlagEqualTo("0").andIsAnonymousNotEqualTo("1").andCreateByEqualTo(staffId);
			staffOpinionFeedbackCustomExample.setOrderByClause("s.create_date desc");
			
			if (!StringUtil.isEmpty(request.getParameter("id"))) {
				staffOpinionFeedbackCustomCriteria.andIdEqualTo(Integer.valueOf(request.getParameter("id")));
			}
			if (!StringUtil.isEmpty(request.getParameter("organizationId"))) {
				staffOpinionFeedbackCustomCriteria.andOrganizationIdEqualTo(request.getParameter("organizationId"));
			}
			if (!StringUtil.isEmpty(request.getParameter("reply"))) {
				staffOpinionFeedbackCustomCriteria.andReplyEqualTo(request.getParameter("reply"));
			}
			if (!StringUtil.isEmpty(request.getParameter("startCreateDate")) && !StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
				staffOpinionFeedbackCustomCriteria.andCreateDateBetween(sdf.parse(request.getParameter("startCreateDate")+" 00:00:00"), sdf.parse(request.getParameter("endCreateDate")+" 23:59:59"));				
			}
			staffOpinionFeedbackCustomExample.setLimitStart(page.getLimitStart());
			staffOpinionFeedbackCustomExample.setLimitSize(page.getLimitSize());
			totalCount = staffOpinionFeedbackService.countBystaffOpinionFeedbackCustomExample(staffOpinionFeedbackCustomExample);
			dataList = staffOpinionFeedbackService.selectByStaffOpinionFeedbackCustomExample(staffOpinionFeedbackCustomExample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	//获取员工意见反馈图片
	@ResponseBody
	@RequestMapping("/staffOpinionFeedback/staffopinionfeedbackpicPicList.shtml")
	public List<StaffOpinionFeedbackPic> getStaffOpinionFeedbackPicList(HttpServletRequest request){
		List<StaffOpinionFeedbackPic> staffOpinionFeedbackPicList = new ArrayList<StaffOpinionFeedbackPic>();
		if(!StringUtil.isEmpty(request.getParameter("feedbackcontentid"))){
			StaffOpinionFeedbackPicExample staffOpinionFeedbackPicExample=new StaffOpinionFeedbackPicExample();
			staffOpinionFeedbackPicExample.createCriteria().andDelFlagEqualTo("0").andFeedbackContentIdEqualTo(Integer.valueOf(request.getParameter("feedbackcontentid")));			
			staffOpinionFeedbackPicExample.setLimitStart(0);
			staffOpinionFeedbackPicExample.setLimitSize(5);
			staffOpinionFeedbackPicList = staffOpinionFeedbackPicService.selectByExample(staffOpinionFeedbackPicExample);
		}
		return staffOpinionFeedbackPicList;
	}
	
	//添加界面
	@RequestMapping(value = "/staffOpinionFeedback/edit.shtml")
	public ModelAndView addActivityBrandGroup(HttpServletRequest request) {
		String rtPage = "/staffOpinionFeedback/edit";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			
			SysOrganizationExample sysOrganizationExample=new SysOrganizationExample();
			sysOrganizationExample.createCriteria().andStatusEqualTo("A");
			List<SysOrganization> sysOrganizationList=sysOrganizationService.selectByExample(sysOrganizationExample);
			resMap.put("sysOrganizationList", sysOrganizationList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage, resMap);
	}
	
	    //添加反馈数据
		@RequestMapping(value = "/staffOpinionFeedback/addstaffOpinionFeedback.shtml")
		@ResponseBody
	 	public ModelAndView addstaffOpinionFeedback(HttpServletRequest request,StaffOpinionFeedback staffOpinionFeedback,String staffFeedbackContentPics){
			String rtPage = "/success/success";
			Map<String, Object> resMap = new HashMap<String, Object>();
			String code = null;
			String msg =null;
			try {
				StaffBean staffBean = this.getSessionStaffBean(request);
				int staffId=Integer.valueOf(staffBean.getStaffID());
				
		            Calendar calendar =  Calendar.getInstance();
	    		           
				    calendar.set(Calendar.HOUR_OF_DAY,0);
			        calendar.set(Calendar.MINUTE,0);
			        calendar.set(Calendar.SECOND,0);
			        calendar.set(Calendar.MILLISECOND,0);
			        Date dayStart = calendar.getTime();
			        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        String startStr = simpleDateFormat.format(dayStart);

                    
			        calendar.set(Calendar.HOUR_OF_DAY,23);
			        calendar.set(Calendar.MINUTE,59);
			        calendar.set(Calendar.SECOND,59);
			        calendar.set(Calendar.MILLISECOND,999);
			        Date dayEnd = calendar.getTime();
			        String endStr = simpleDateFormat.format(dayEnd);
								
				StaffOpinionFeedbackCustomExample staffOpinionFeedbackCustomExample=new StaffOpinionFeedbackCustomExample();
				StaffOpinionFeedbackCustomExample.StaffOpinionFeedbackCustomCriteria staffOpinionFeedbackCustomCriteria=staffOpinionFeedbackCustomExample.createCriteria();
				staffOpinionFeedbackCustomCriteria.andDelFlagEqualTo("0").andIsAnonymousEqualTo(staffOpinionFeedback.getIsanonymous());
				staffOpinionFeedbackCustomCriteria.andCreateDateBegin(startStr,endStr);
				List<StaffOpinionFeedbackCustom> staffOpinionFeedbackCustomlist=staffOpinionFeedbackService.selectByStaffOpinionFeedbackCustomExample(staffOpinionFeedbackCustomExample);
				if (staffOpinionFeedbackCustomlist!=null && staffOpinionFeedbackCustomlist.size()>0) {
					if (staffOpinionFeedbackCustomlist.size()>4 && staffOpinionFeedbackCustomlist.get(0).getIsanonymous().equals("1")) {
						code = StateCode.JSON_AJAX_ERROR.getStateCode();
						msg = "一天内(24h)只允许存在5条匿名反馈~！";
						resMap.put(this.JSON_RESULT_CODE, code);
						resMap.put(this.JSON_RESULT_MESSAGE, msg);
						return new ModelAndView(rtPage, resMap);
						
					}				
				}
				
				if (StringUtil.isEmpty(staffFeedbackContentPics)) {
					
					staffOpinionFeedback.setCreateBy(staffId);
					staffOpinionFeedback.setCreateDate(new Date());	
					staffOpinionFeedbackService.insertSelective(staffOpinionFeedback);
						
			    }else {	
			    	
					staffOpinionFeedback.setCreateBy(staffId);
					staffOpinionFeedback.setCreateDate(new Date());
				    staffOpinionFeedbackService.insertStaffOpinionFeedback(staffOpinionFeedback, staffFeedbackContentPics);	
				    
				}
					
				
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
		
		
		//员工意见反馈
		@RequestMapping("/staffOpinionFeedback/staffopinionFeedbacklist.shtml")
		public ModelAndView staffOpinionFeedback(HttpServletRequest request) {
			ModelAndView m = new ModelAndView("/staffOpinionFeedback/staffopinionFeedbacklist");
			SysOrganizationExample sysOrganizationExample=new SysOrganizationExample();
			sysOrganizationExample.createCriteria().andStatusEqualTo("A");
			List<SysOrganization> sysOrganizationList=sysOrganizationService.selectByExample(sysOrganizationExample);
			m.addObject("sysOrganizationList",sysOrganizationList);		
			return m;
		}
		
		@ResponseBody
		@RequestMapping("/staffOpinionFeedback/staffOpinionFeedbackdata.shtml")
		public Map<String, Object> getstaffOpinionFeedbacktList(HttpServletRequest request, Page page) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<StaffOpinionFeedbackCustom> dataList = null;
			Integer totalCount = 0;
			try {
								
				StaffOpinionFeedbackCustomExample staffOpinionFeedbackCustomExample = new StaffOpinionFeedbackCustomExample();
				StaffOpinionFeedbackCustomExample.StaffOpinionFeedbackCustomCriteria staffOpinionFeedbackCustomCriteria = staffOpinionFeedbackCustomExample.createCriteria();
				staffOpinionFeedbackCustomCriteria.andDelFlagEqualTo("0");
				staffOpinionFeedbackCustomExample.setOrderByClause("s.create_date desc");
				
				if (!StringUtil.isEmpty(request.getParameter("id"))) {
					staffOpinionFeedbackCustomCriteria.andIdEqualTo(Integer.valueOf(request.getParameter("id")));
				}
				if (!StringUtil.isEmpty(request.getParameter("organizationId"))) {
					staffOpinionFeedbackCustomCriteria.andOrganizationIdEqualTo(request.getParameter("organizationId"));
				}
				if (!StringUtil.isEmpty(request.getParameter("reply"))) {
					staffOpinionFeedbackCustomCriteria.andReplyEqualTo(request.getParameter("reply"));
				}
				if (!StringUtil.isEmpty(request.getParameter("startCreateDate")) && !StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
					staffOpinionFeedbackCustomCriteria.andCreateDateBetween(sdf.parse(request.getParameter("startCreateDate")+" 00:00:00"), sdf.parse(request.getParameter("endCreateDate")+" 23:59:59"));				
				}
				staffOpinionFeedbackCustomExample.setLimitStart(page.getLimitStart());
				staffOpinionFeedbackCustomExample.setLimitSize(page.getLimitSize());
				totalCount = staffOpinionFeedbackService.countBystaffOpinionFeedbackCustomExample(staffOpinionFeedbackCustomExample);
				dataList = staffOpinionFeedbackService.selectByStaffOpinionFeedbackCustomExample(staffOpinionFeedbackCustomExample);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
			return resMap;
		}
		
		//员工意见反馈详情
		@RequestMapping(value = "/staffOpinionFeedback/editstaffreply.shtml")
		public ModelAndView editstaffreply(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) {
			String rtPage = "/staffOpinionFeedback/editstaffreply";// 重定向
		    
			Map<String, Object> resMap = new HashMap<String, Object>();
			
			if (!StringUtil.isEmpty(request.getParameter("id"))) {
				
				StaffOpinionFeedback staffOpinionFeedback = staffOpinionFeedbackService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
				resMap.put("staffOpinionFeedback", staffOpinionFeedback);
											
				StaffOpinionFeedbackPicExample staffOpinionFeedbackPicExample=new StaffOpinionFeedbackPicExample();
				staffOpinionFeedbackPicExample.createCriteria().andDelFlagEqualTo("0").andFeedbackContentIdEqualTo(staffOpinionFeedback.getId());
				List<StaffOpinionFeedbackPic> staffOpinionFeedbackPics=staffOpinionFeedbackPicService.selectByExample(staffOpinionFeedbackPicExample);
				resMap.put("staffOpinionFeedbackPicList", staffOpinionFeedbackPics);
						
				
				
				/*SysOrganizationExample sysOrganizationExample=new SysOrganizationExample();
				sysOrganizationExample.createCriteria().andStatusEqualTo("A");
				List<SysOrganization> sysOrganizationList=sysOrganizationService.selectByExample(sysOrganizationExample);
				resMap.put("sysOrganizationList",sysOrganizationList);	*/
												
				
				StaffReplyCustomExample staffReplycustomExample=new StaffReplyCustomExample();
				staffReplycustomExample.createCriteria().andDelFlagEqualTo("0").andFeedbackContentIdEqualTo(Integer.valueOf(request.getParameter("id")));
				List<StaffReplyCustom> staffReplycustomlist=staffReplyService.selectByStaffReplyCustomExample(staffReplycustomExample);
				resMap.put("staffReplycustomlist", staffReplycustomlist);
				
			    String replyid="";
				for (StaffReplyCustom staffReplyCustom : staffReplycustomlist) {
					
					replyid +=staffReplyCustom.getId().toString()+",";
								
				}
				
				resMap.put("replyid", replyid);
			}
		
			return new ModelAndView(rtPage, resMap);
		}
		
		
		       //添加回复数据
				@RequestMapping(value = "/staffreply/addstaffreply.shtml")
				@ResponseBody
			 	public ModelAndView addstaffreply(HttpServletRequest request,StaffReply staffReply,String replyPics,String id){
					String rtPage = "/success/success";
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = null;
					String msg =null;
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						int staffId=Integer.valueOf(staffBean.getStaffID());
							 
						    if (StringUtil.isEmpty(replyPics)) {
						    	staffReply.setFeedbackContentId(Integer.valueOf(id));
							    staffReply.setCreateBy(staffId);
							    staffReply.setCreateDate(new Date());
							    staffReplyService.insertSelective(staffReply);
							    
							     StaffOpinionFeedback staffOpinionFeedback=staffOpinionFeedbackService.selectByPrimaryKey(Integer.valueOf(id));
						         staffOpinionFeedback.setReply("1");
						         staffOpinionFeedback.setUpdateBy(staffId);
						         staffOpinionFeedback.setUpdateDate(new Date());
						         staffOpinionFeedbackService.updateByPrimaryKey(staffOpinionFeedback);
								
							}else {
								
								staffReply.setFeedbackContentId(Integer.valueOf(id));
								staffReply.setCreateBy(staffId);
								staffReply.setCreateDate(new Date());
								staffReplyService.insertStaffReply(staffReply, replyPics);	
								
								StaffOpinionFeedback staffOpinionFeedback=staffOpinionFeedbackService.selectByPrimaryKey(Integer.valueOf(id));
								staffOpinionFeedback.setReply("1");
								staffOpinionFeedback.setUpdateBy(staffId);
								staffOpinionFeedback.setUpdateDate(new Date());
								staffOpinionFeedbackService.updateByPrimaryKey(staffOpinionFeedback);
							}
										
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
				
				//获取回复内容
				@ResponseBody
				@RequestMapping(value = "/staffreply/staffreplydata.shtml")
				public Map<String, Object> staffreplydataList(HttpServletRequest request, Page page){
					Map<String, Object> resMap = new HashMap<String, Object>();
					List<StaffReplyCustom> dataList = new ArrayList<StaffReplyCustom>();
					Integer totalCount = 0;
					try {
						StaffReplyCustomExample staffReplycustomExample=new StaffReplyCustomExample();
						StaffReplyCustomExample.StaffReplyCustomCriteria staffReplyCustomCriteria=staffReplycustomExample.createCriteria();
						staffReplyCustomCriteria.andDelFlagEqualTo("0");
						staffReplycustomExample.setOrderByClause("r.create_date desc");
						List<Integer> replyidList = new ArrayList<Integer>();
						String[] replyid = request.getParameter("replyid").split(",");
						for (String replyids : replyid) {
							replyidList.add(Integer.valueOf(replyids));
						}
						staffReplyCustomCriteria.andIdIn(replyidList);
						staffReplycustomExample.setLimitSize(page.getLimitSize());
						staffReplycustomExample.setLimitStart(page.getLimitStart());
						dataList = staffReplyService.selectByStaffReplyCustomExample(staffReplycustomExample);
						totalCount = staffReplyService.countByExample(staffReplycustomExample);
					} catch (Exception e) {
						e.printStackTrace();
					}
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
					return resMap;
				}
				
				
				
				//删除回复数据
				@RequestMapping(value = "/staffreply/updatestaffreplydata.shtml")
				@ResponseBody
				public Map<String, Object> delete(HttpServletRequest request,HttpServletResponse response,String id){
					Map<String, Object> resMap = new HashMap<String, Object>();

					resMap.put("returnCode", "0000");
					resMap.put("returnMsg", "成功");
					try {
						String staffreplyid=request.getParameter("id");
						StaffBean staffBean = this.getSessionStaffBean(request);
						int staffId=Integer.valueOf(staffBean.getStaffID());
						
						StaffReply staffReply=staffReplyService.selectByPrimaryKey(Integer.valueOf(staffreplyid));
						staffReply.setUpdateBy(staffId);
						staffReply.setUpdateDate(new Date());
						staffReply.setDelFlag("1");
						staffReplyService.updateByPrimaryKeySelective(staffReply);
						
						
						StaffReplyPicExample staffReplyPicExample=new StaffReplyPicExample();
						StaffReplyPicExample.Criteria staffReplyPicCriteria=staffReplyPicExample.createCriteria();
						staffReplyPicCriteria.andDelFlagEqualTo("0").andReplyIdEqualTo(Integer.valueOf(staffreplyid));
						
						StaffReplyPic staffReplyPic=new StaffReplyPic();
						staffReplyPic.setUpdateBy(staffId);
						staffReplyPic.setUpdateDate(new Date());
						staffReplyPic.setDelFlag("1");
						staffReplyPicService.updateByExampleSelective(staffReplyPic, staffReplyPicExample);
						
						StaffReplyExample staffReplyExample=new StaffReplyExample();
						staffReplyExample.createCriteria().andDelFlagEqualTo("0").andFeedbackContentIdEqualTo(staffReply.getFeedbackContentId());
						List<StaffReply> listStaffReplies=staffReplyService.selectByExample(staffReplyExample);
						if (listStaffReplies.size()<=0) {
							StaffOpinionFeedback staffOpinionFeedback=staffOpinionFeedbackService.selectByPrimaryKey(staffReply.getFeedbackContentId());
							staffOpinionFeedback.setReply("2");
							staffOpinionFeedback.setUpdateBy(staffId);
							staffOpinionFeedback.setUpdateDate(new Date());
							staffOpinionFeedbackService.updateByPrimaryKey(staffOpinionFeedback);
						}
						
						
					} catch (NumberFormatException e) {
						e.printStackTrace();
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", e.getMessage());
					}
					
					return resMap;
				}
				
				
				//获取员工回复图片
				@ResponseBody
				@RequestMapping("/staffreply/staffreplyPicList.shtml")
				public List<StaffReplyPic> getstaffreplyPicList(HttpServletRequest request){
					List<StaffReplyPic> staffReplyPicList = new ArrayList<StaffReplyPic>();
					if(!StringUtil.isEmpty(request.getParameter("staffrePlyid"))){
						StaffReplyPicExample staffReplyPicExample=new StaffReplyPicExample();
						StaffReplyPicExample.Criteria staffReplyPicCriteria=staffReplyPicExample.createCriteria();
						staffReplyPicCriteria.andDelFlagEqualTo("0").andReplyIdEqualTo(Integer.valueOf(request.getParameter("staffrePlyid")));		
						staffReplyPicExample.setLimitStart(0);
						staffReplyPicExample.setLimitSize(5);
						staffReplyPicList = staffReplyPicService.selectByExample(staffReplyPicExample);
					}
					return staffReplyPicList;
				}
				
				
				//我的意见反馈回复界面
				@ResponseBody
				@RequestMapping(value = "/staffreply/viewstaffreplydata.shtml")
				public Map<String, Object> viewstaffreplydata(HttpServletRequest request, Page page){
					Map<String, Object> resMap = new HashMap<String, Object>();
					List<StaffReplyCustom> dataList = new ArrayList<StaffReplyCustom>();
					Integer totalCount = 0;
					try {
																
						StaffReplyCustomExample staffReplycustomExample=new StaffReplyCustomExample();
						StaffReplyCustomExample.StaffReplyCustomCriteria staffReplyCustomCriteria=staffReplycustomExample.createCriteria();
						staffReplyCustomCriteria.andDelFlagEqualTo("0");
						staffReplycustomExample.setOrderByClause("r.create_date desc");
						List<Integer> replyidList = new ArrayList<Integer>();
						String[] replyid = request.getParameter("replyid").split(",");
						for (String replyids : replyid) {
							replyidList.add(Integer.valueOf(replyids));
						}
						staffReplyCustomCriteria.andIdIn(replyidList);
						staffReplycustomExample.setLimitSize(page.getLimitSize());
						staffReplycustomExample.setLimitStart(page.getLimitStart());
						dataList = staffReplyService.selectByStaffReplyCustomExample(staffReplycustomExample);
						totalCount = staffReplyService.countByExample(staffReplycustomExample);
					} catch (Exception e) {
						e.printStackTrace();
					}
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
					return resMap;
				}
				
				
				
				//我的意见反馈详情
				@RequestMapping(value = "/staffOpinionFeedback/view.shtml")
				public ModelAndView view(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) {
					String rtPage = "/staffOpinionFeedback/viewstaffreply";// 重定向
				    
					Map<String, Object> resMap = new HashMap<String, Object>();
					
					if (!StringUtil.isEmpty(request.getParameter("id"))) {
						
						StaffOpinionFeedback staffOpinionFeedback = staffOpinionFeedbackService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
						resMap.put("staffOpinionFeedback", staffOpinionFeedback);
						
						StaffOpinionFeedbackPicExample staffOpinionFeedbackPicExample=new StaffOpinionFeedbackPicExample();
						staffOpinionFeedbackPicExample.createCriteria().andDelFlagEqualTo("0").andFeedbackContentIdEqualTo(staffOpinionFeedback.getId());
						List<StaffOpinionFeedbackPic> staffOpinionFeedbackPics=staffOpinionFeedbackPicService.selectByExample(staffOpinionFeedbackPicExample);
						List<Map<String, Object>> staffOpinionFeedbackPicList=new ArrayList<Map<String, Object>>();
						for (StaffOpinionFeedbackPic staffOpinionFeedbackPic : staffOpinionFeedbackPics) {
							Map<String, Object> pic=new HashMap<String, Object>();
							pic.put("PICTURE_PATH", staffOpinionFeedbackPic.getFeedbackContentPic());
							staffOpinionFeedbackPicList.add(pic);
						}
						
						/*SysOrganizationExample sysOrganizationExample=new SysOrganizationExample();
						sysOrganizationExample.createCriteria().andStatusEqualTo("A");
						List<SysOrganization> sysOrganizationList=sysOrganizationService.selectByExample(sysOrganizationExample);
						resMap.put("sysOrganizationList",sysOrganizationList);	*/
						
						resMap.put("staffOpinionFeedbackPicList", staffOpinionFeedbackPicList);
						
						
						StaffReplyCustomExample staffReplycustomExample=new StaffReplyCustomExample();
						staffReplycustomExample.createCriteria().andDelFlagEqualTo("0").andFeedbackContentIdEqualTo(Integer.valueOf(request.getParameter("id")));
						List<StaffReplyCustom> staffReplycustomlist=staffReplyService.selectByStaffReplyCustomExample(staffReplycustomExample);
						resMap.put("staffReplycustomlist", staffReplycustomlist);
						
					    String replyid="";
						for (StaffReplyCustom staffReplyCustom : staffReplycustomlist) {
							
							replyid +=staffReplyCustom.getId().toString()+",";
						}
						
						resMap.put("replyid", replyid);
					}
				
					return new ModelAndView(rtPage, resMap);
				}
				
				
}
