package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.*;
import com.jf.entity.InformationCustomExample.InformationCustomCriteria;
import com.jf.service.CatalogService;
import com.jf.service.InformationService;
import com.jf.service.MchtInformationService;
import com.jf.vo.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class InformationController extends BaseController{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8008075173755044265L;

	@Resource
	private InformationService informationService;
	
	@Resource
	private CatalogService catalogService;
	@Resource
	private MchtInformationService mchtInformationService;
	
	@RequestMapping(value = "/infomation/index.shtml")
	 public ModelAndView inforIndex(@RequestParam HashMap<String, Object> paramMap,@RequestParam String seeInfoInfo) {
		String rtPage = "/infomation/index";//  首页
		paramMap.put("seeInfoInfo",seeInfoInfo);
		return new ModelAndView(rtPage,paramMap);
	}
	
	@RequestMapping(value="/infomation/data.shtml")
	@ResponseBody
	public HashMap<String, Object> getInformation(HttpServletRequest request, Page page,@RequestParam String seeInfoInfo){
	
		String staffs = request.getParameter("staff");
		
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		InformationCustomExample informationExample = new InformationCustomExample();
		informationExample.setOrderByClause("t.release_time desc,id desc");
		InformationCustomCriteria createCriteria = informationExample.createCriteria();
		if(seeInfoInfo.equals("11")){
			CatalogExample catalogExample=new CatalogExample();
			CatalogExample parentExample=new CatalogExample();
			parentExample.createCriteria().andBackNameEqualTo("醒购动态").andDelFlagEqualTo("0").andStatusEqualTo("1");
			List<Catalog> parentCatalogs = catalogService.selectByExample(parentExample);
			Integer parentCatalogId = parentCatalogs.get(0).getId();
			List<Integer> catalogIdList = new ArrayList<Integer>();
			catalogExample.createCriteria().andParentIdEqualTo(parentCatalogId);
			List<Catalog> catalogList = catalogService.selectByExample(catalogExample);
			if(catalogList.size() > 0){
			for (int i = 0; i < catalogList.size() ; i++) {
                  catalogIdList.add(catalogList.get(i).getId());
			}
			    createCriteria.andCatalogIdIn(catalogIdList);
			    }
		    }
			if (!StringUtil.isEmpty(staffs)) {
				StaffBean staffBean = this.getSessionStaffBean(request);
				int staffId = Integer.valueOf(staffBean.getStaffID());
				createCriteria.andCreateByEqualTo(staffId);
			} else {
				if (!StringUtil.isEmpty(request.getParameter("catalogId"))) {
					createCriteria.andCatalogAll(Integer.valueOf(request.getParameter("catalogId")));
				}

				if (!StringUtil.isEmpty(request.getParameter("title"))) {
					createCriteria.andTitleECLike(request.getParameter("title"));
				}

				if (!StringUtil.isEmpty(request.getParameter("status"))) {
					createCriteria.andStatusEqualTo(request.getParameter("status"));
				}
			}

			if (StringUtil.isEmpty(request.getParameter("catalogId")) && StringUtil.isEmpty(request.getParameter("status")) && StringUtil.isEmpty(request.getParameter("title"))) {
				createCriteria.andStatusEqualTo("1");
			}
			createCriteria.andDelFlagEqualTo("0");


			Integer countByExample = informationService.countByExample(informationExample);

			informationExample.setLimitStart(page.getLimitStart());
			informationExample.setLimitSize(page.getLimitSize());

			List<InformationCustom> selectByExample = informationService.selectInformationByExample(informationExample);

			for (int i = 0; i < selectByExample.size(); i++) {
				InformationCustom informationCustom = selectByExample.get(i);
				informationCustom.setCreatDataDsc(getdata(informationCustom.getReleaseTime()));
				String infoType = informationCustom.getInfoType();
				String desinfotype = "";
				if (!"".equals(infoType) && infoType != null) {
					String[] split = infoType.split(",");
					for (int j = 0; j < split.length; j++) {
						if (split[j].equals("1")) {
							//1会员2新申请商家3入驻商家4游客
							desinfotype += ",游客";
						} else if (split[j].equals("2")) {
							desinfotype += ",会员";
						} else if (split[j].equals("3")) {
							desinfotype += ",新申请商家";
						} else if (split[j].equals("4")) {
							desinfotype += ",入驻商家";
						} else if (split[j].equals("5")) {
							desinfotype += ",平台用户";
						}
					}
					if (desinfotype.length() > 1) {
						desinfotype = desinfotype.substring(1);
					}
				}
				informationCustom.setInfoTypeDsc(desinfotype);
				String catalogListName = informationCustom.getCatalogListName();
				catalogListName = "栏目：" + catalogListName;
				informationCustom.setCatalogListName(catalogListName);

				String reTitle = informationCustom.getTitle() + "<br> <font color=\"#BEBEBE\">" + informationCustom.getCatalogListName() + "</font>";
				informationCustom.setTitle(reTitle);
			}

			resMap.put("seeInfoInfo", seeInfoInfo);
			resMap.put("Rows", selectByExample);
			resMap.put("Total", countByExample);
		return resMap;
	}

	@RequestMapping(value = "/infomation/getCatalogTree.shtml")
	@ResponseBody
	public List<Catalog> getProductTypeTree(HttpServletRequest request)  {
		String seeInfoInfo = request.getParameter("seeInfoInfo");
		CatalogExample example=new CatalogExample();
		List<Catalog> selectByExample;
		if (seeInfoInfo.equals("11")) {
			CatalogExample parentExample = new CatalogExample();
			parentExample.createCriteria().andBackNameEqualTo("醒购动态").andDelFlagEqualTo("0").andStatusEqualTo("1");
			List<Catalog> parentCatalogs = catalogService.selectByExample(parentExample);
			Integer id = 0;
			if(parentCatalogs.size() >0 ) {
				Integer parentCatalogId = parentCatalogs.get(0).getId();
				id = parentCatalogId;
			}else {
				id = 70;
			}
				example.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").
						andParentIdEqualTo(id);
				Catalog catalog = catalogService.selectByPrimaryKey(id);
				selectByExample = catalogService.selectByExample(example);
				selectByExample.add(catalog);

		}else {
			example.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
			selectByExample = catalogService.selectByExample(example);
	    }
		return selectByExample;
	}
	
	private String getdata(Date time){
		if(time == null){
			return "";
		}
		//时间戳转化为Sting或Date  
	    SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");  
	    String date = format.format(time); 
	    return date;
	}
	
	@RequestMapping(value="/infomation/deltinfo.shtml")
	public  ModelAndView infordelIndex(HttpServletRequest request, HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/infomation/deltinfo";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage,resMap);
	}
	
	///infomation/deldata.shtml
	@RequestMapping(value="/infomation/deldata.shtml")
	@ResponseBody
	public HashMap<String, Object> getInformationdel(HttpServletRequest request, Page page){
	
		String staffs = request.getParameter("staff");
		
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		InformationCustomExample informationExample = new InformationCustomExample();
		InformationCustomCriteria createCriteria = informationExample.createCriteria();
		informationExample.setOrderByClause("t.release_time desc,id desc");
		
		if(!StringUtil.isEmpty(staffs)){
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			createCriteria.andCreateByEqualTo(staffId);
		}else{
			if(!StringUtil.isEmpty(request.getParameter("catalogId"))){
				createCriteria.andCatalogAll(Integer.valueOf(request.getParameter("catalogId")));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("title"))){
				createCriteria.andTitleECLike(request.getParameter("title"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("status"))){
				createCriteria.andStatusEqualTo(request.getParameter("status"));
			}
		}
		
		createCriteria.andDelFlagEqualTo("1");
		Integer countByExample = informationService.countByExample(informationExample);
		
		informationExample.setLimitStart(page.getLimitStart());
		informationExample.setLimitSize(page.getLimitSize());
		
		List<InformationCustom> selectByExample = informationService.selectInformationByExample(informationExample);
		for (int i = 0; i < selectByExample.size(); i++) {
			InformationCustom informationCustom = selectByExample.get(i);
			informationCustom.setCreatDataDsc(getdata(informationCustom.getReleaseTime()));
			String infoType = informationCustom.getInfoType();
			String desinfotype = "";
			if (!"".equals(infoType) && infoType!=null) {
				String[] split = infoType.split(",");
				for (int j = 0; j < split.length; j++) {
					if(split[j].equals("1")){
						//1游客2会员2未合作商家3入驻商家4平台用户
						desinfotype += ",游客";
					}else if(split[j].equals("2")){
						desinfotype += ",会员";
					}else if(split[j].equals("3")){
						desinfotype += ",入驻商家";
					}else if(split[j].equals("4")){
						desinfotype += ",入驻商家";
					}else if(split[j].equals("5")){
						desinfotype += ",平台用户";
					}
				}
				desinfotype = desinfotype.substring(1);
			}
			informationCustom.setInfoTypeDsc(desinfotype);
			
			String catalogListName = informationCustom.getCatalogListName();
			catalogListName = "栏目："+catalogListName;
			informationCustom.setCatalogListName(catalogListName);
			
			String reTitle = informationCustom.getTitle() + "<br> <font color=\"#BEBEBE\">" +informationCustom.getCatalogListName() + "</font>";
			informationCustom.setTitle(reTitle);
		}
		resMap.put("Rows", selectByExample);
		resMap.put("Total", countByExample);
		return resMap;
	}
	
	//保存和修改
	@RequestMapping(value="/infomation/editinfo.shtml")
	public ModelAndView editinfo(HttpServletRequest request,HttpServletResponse response,@RequestParam String seeInfoInfo,
			@RequestParam HashMap<String, Object> paramMap){
		/*String resPage = "/infomation/editinfo";*/
		String resPage=null;
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		String inforId = paramMap.get("inforId").toString();
		//状态
		List<SysStatus> platformType = DataDicUtil.getTableStatus("BU_INFORMATION", "STATUS");
		HashMap<String, String> hash = new HashMap<String, String>();
		for (int i = 0; i < platformType.size(); i++) {
			SysStatus sysStatus = platformType.get(i);
			hash.put(sysStatus.getStatusValue(), sysStatus.getStatusDesc());
		}
		
		if (!StringUtil.isEmpty(inforId)) {
			InformationCustomExample informationExample = new InformationCustomExample();
			InformationCustomCriteria createCriteria = informationExample.createCriteria();
			createCriteria.andIdEqualTo(Integer.valueOf(inforId));
			createCriteria.andDelFlagEqualTo("0");
			List<InformationCustom> selectByExample = informationService.selectInformationByExample(informationExample);
			if (selectByExample.size()>0) {
				InformationCustom informationCustom = selectByExample.get(0);
				String getdata = getdata(informationCustom.getReleaseTime());
				resMap.put("Info", informationCustom);
				resMap.put("statuvalue", informationCustom.getStatus());
				resMap.put("statudes", hash.get(informationCustom.getStatus()));
				resMap.put("NOTICE_CONTENT", informationCustom.getContent());
				resMap.put("ReleaseTime", getdata);
				resMap.put("method", "edit");
			}
		}else{
			resMap.put("method", "add");
		}
		resMap.put("statu", platformType);
		resMap.put("inforId", paramMap.get("inforId"));
		resMap.put("seeInfoInfo",seeInfoInfo);
		if (seeInfoInfo.equals("1")) {//信息查看
			resPage = "/infomation/seeinfo";
		}else if (seeInfoInfo.equals("0") || seeInfoInfo.equals("11")) {  //信息管理编辑+(权限)信息管理
			resPage = "/infomation/editinfo";
		}
		return new ModelAndView(resPage, resMap);
	}
	
	//批量删除
	@RequestMapping(value = "/infomation/cancleInfo.shtml")
	@ResponseBody
	public Map<String, Object> delSuccess(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = ""; 
		try {    
			String parameter = request.getParameter("Ids");
			String parameter2 = request.getParameter("status");
			String[] split = parameter.split(",");

			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			for (int i = 0; i < split.length; i++) {
				List<Information>  selectByExample  = null;
				int inforId=Integer.valueOf(split[i]);
				InformationCustomExample informationExample = new InformationCustomExample();
				InformationCustomCriteria createCriteria = informationExample.createCriteria();
				if (parameter2.equals("0")) {
					//恢复
					createCriteria.andIdEqualTo(Integer.valueOf(inforId));
					createCriteria.andDelFlagEqualTo("1").andIdEqualTo(Integer.valueOf(inforId));
					selectByExample = informationService.selectByExample(informationExample);
					if (selectByExample.size() > 0) {
						Information information = selectByExample.get(0);
						information.setDelFlag("0");
						information.setUpdateBy(staffId);
						information.setUpdateDate(new Date());
						informationService.updateByPrimaryKeySelective(information);
					}
				}else{
					//删除
					createCriteria.andIdEqualTo(Integer.valueOf(inforId));
					createCriteria.andDelFlagEqualTo("0").andIdEqualTo(Integer.valueOf(inforId));
					selectByExample = informationService.selectByExample(informationExample);
					if (selectByExample.size() > 0) {
						Information information = selectByExample.get(0);
						information.setDelFlag("1");
						information.setUpdateBy(staffId);
						information.setUpdateDate(new Date());
						informationService.updateByPrimaryKeySelective(information);
					}
				}
				
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

	/**
	 * 已读商家页面
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/infomation/showReadMcht.shtml")
	public ModelAndView showReadMcht(HttpServletRequest request,Integer id) {
		ModelAndView m = new ModelAndView();
		m.addObject("id",id);
		m.setViewName("/infomation/readMcht");
		return  m;
	}


	/**
	 * 已读商家列表
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/information/getReadMchtList.shtml")
	@ResponseBody
	public Map<String, Object> getActivityProductList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<MchtInformation> dataList = null;
		Integer totalCount = 0;
		String inforMationId = request.getParameter("id");
		String mchtCode = request.getParameter("mchtCode");
		String companyName = request.getParameter("companyName");
		try {
			MchtInformationCustomExample mchtInformationCustomExample = new MchtInformationCustomExample();
			MchtInformationCustomExample.MchtInformationCustomCriteria criteria = mchtInformationCustomExample.createCriteria();
			criteria.andInformationIdEqualTo(Integer.parseInt(inforMationId)).andDelFlagEqualTo("0");
			if (!StringUtil.isEmpty(mchtCode)){
				criteria.andMchtCodeEqualTo(mchtCode);
			}
			if (!StringUtil.isEmpty(companyName)){
				criteria.andCompanyNameLikeTo(companyName);
			}
			totalCount=mchtInformationService.countByCustomExample(mchtInformationCustomExample);
			mchtInformationCustomExample.setLimitSize(page.getLimitSize());
			mchtInformationCustomExample.setLimitStart(page.getLimitStart());
			mchtInformationCustomExample.setOrderByClause("create_date desc");
			List<MchtInformation> mchtInformations = mchtInformationService.selectByCustomExample(mchtInformationCustomExample);
			dataList=mchtInformations;
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}



	//批量修改状态页面
	@RequestMapping(value="/infomation/changeStatupage.shtml")
	public ModelAndView changeStatupage(HttpServletRequest request,HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap){
		String resPage = "/infomation/changeStatupage";
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		//状态
		List<SysStatus> platformType = DataDicUtil.getTableStatus("BU_INFORMATION", "STATUS");
		resMap.put("statu", platformType);
		resMap.put("inforIds", paramMap.get("inforIds"));
		return new ModelAndView(resPage, resMap);
	}
	
	//批量修改状态
	@RequestMapping(value = "/infomation/changeStatu.shtml")
	@ResponseBody
	public ModelAndView changeStatu(HttpServletRequest request, 
			HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap,String id,String status) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String rtPage = "/success/success";
		String code = null;
		String msg = ""; 
		try {    
			String[] split = id.split(",");

			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			for (int i = 0; i < split.length; i++) {
				int inforId=Integer.valueOf(split[i]);
				InformationCustomExample informationExample = new InformationCustomExample();
				InformationCustomCriteria createCriteria = informationExample.createCriteria();
				createCriteria.andIdEqualTo(Integer.valueOf(inforId));
				createCriteria.andDelFlagEqualTo("0");
				Information selectByPrimaryKey = informationService.selectByPrimaryKey(inforId);
				selectByPrimaryKey.setStatus(status);
				selectByPrimaryKey.setUpdateBy(staffId);
				selectByPrimaryKey.setUpdateDate(new Date());
				informationService.updateByPrimaryKeySelective(selectByPrimaryKey);
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
	
	//批量修改栏目页面
	@RequestMapping(value="/infomation/changecatalogpage.shtml")
	public ModelAndView changecatalogpage(HttpServletRequest request,HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap,@RequestParam String seeInfoInfo){
		String resPage = "/infomation/changecatalogpage";
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("inforIds", paramMap.get("inforIds"));
		resMap.put("seeInfoInfo",seeInfoInfo);
		return new ModelAndView(resPage, resMap);
	}
	
	//批量修改栏目
	@RequestMapping(value = "/infomation/changecatalog.shtml")
	@ResponseBody
	public ModelAndView changecatalog(HttpServletRequest request, 
			HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap,String id,String catalogId) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String rtPage = "/success/success";
		String code = null;
		String msg = ""; 
		try {    
			String[] split = id.split(",");
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			for (int i = 0; i < split.length; i++) {
				int inforId=Integer.valueOf(split[i]);
				InformationCustomExample informationExample = new InformationCustomExample();
				InformationCustomCriteria createCriteria = informationExample.createCriteria();
				createCriteria.andIdEqualTo(Integer.valueOf(inforId));
				createCriteria.andDelFlagEqualTo("0");
				Information selectByPrimaryKey = informationService.selectByPrimaryKey(inforId);
				selectByPrimaryKey.setCatalogId(Integer.valueOf(catalogId));
				selectByPrimaryKey.setUpdateBy(staffId);
				selectByPrimaryKey.setUpdateDate(new Date());
				informationService.updateByPrimaryKeySelective(selectByPrimaryKey);
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
	
	/**
	 * 报存数据
	 */
	@RequestMapping(value ="/infomation/saveinformation.shtml")
	@ResponseBody
	public ModelAndView saveinformation(HttpServletRequest request,Information information){
		String resPage = "/success/success";
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = ""; 
		StaffBean staffBean =  this.getSessionStaffBean(request);
		int staffId=Integer.valueOf(staffBean.getStaffID());
		try {    
			if (StringUtil.isEmpty(request.getParameter("id"))) {
				//添加信息
				information.setCreateBy(staffId);
				information.setCreateDate(new Date());
				information.setUpdateBy(staffId);
				information.setUpdateDate(new Date());
				informationService.insertSelective(information);
			}else{
				information.setUpdateBy(staffId);
				information.setUpdateDate(new Date());
				informationService.updateByPrimaryKeySelective(information);
				informationService.updateReleaseTimeByPrimaryKey(information);
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
		return new ModelAndView(resPage, resMap);
		
	}
	
	//信息查看
	@RequestMapping(value = "/infomation/list.shtml")
	 public ModelAndView list(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/infomation/list";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage,resMap);
	}
	
	//信息查看列表数据
	@RequestMapping(value="/infomation/datas.shtml")
	@ResponseBody
	public HashMap<String, Object> getInformationlist(HttpServletRequest request, Page page){
	
		String staffs = request.getParameter("staff");
		
		String infoTypeDsc=request.getParameter("infoTypeDsc");
		
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		InformationCustomExample informationExample = new InformationCustomExample();
		InformationCustomCriteria createCriteria = informationExample.createCriteria();
		informationExample.setOrderByClause("t.release_time desc,id desc");
		
		if(!StringUtil.isEmpty(staffs)){
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			createCriteria.andCreateByEqualTo(staffId);
		}else{
			if(!StringUtil.isEmpty(request.getParameter("catalogId"))){
				createCriteria.andCatalogAll(Integer.valueOf(request.getParameter("catalogId")));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("title"))){
				createCriteria.andTitleECLike(request.getParameter("title"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("status"))){
				createCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			if(!StringUtil.isEmpty(request.getParameter("id"))){
				createCriteria.andIdEqualTo(Integer.valueOf(request.getParameter("id")));
			}
			/*if(!StringUtil.isEmpty(infoTypeDsc)){
				if (infoTypeDsc.equals("1")) {
					createCriteria.andInfoTypeEqualTo("1");
				}else if (infoTypeDsc.equals("2")) {
					createCriteria.andInfoTypeEqualTo("2");
				}else if (infoTypeDsc.equals("3")) {
					createCriteria.andInfoTypeEqualTo("3");
				}else if (infoTypeDsc.equals("4")) {
					createCriteria.andInfoTypeEqualTo("4");
				}else if (infoTypeDsc.equals("5")) {
					createCriteria.andInfoTypeEqualTo("5");
				}
			}*/
			if(!StringUtils.isEmpty(infoTypeDsc)){
				/*if(infoTypeDsc.equals("1")){
					createCriteria.andInfoTypeLike("1%");
				}else if(infoTypeDsc.equals("2") || infoTypeDsc.equals("3") || infoTypeDsc.equals("4") || infoTypeDsc.equals("5")){
					createCriteria.andInfoTypeLikeTo(infoTypeDsc);
				}*/
				createCriteria.andInfoTypeLikeTo(infoTypeDsc);
			}
			
		}
		
		if(StringUtil.isEmpty(request.getParameter("catalogId")) && StringUtil.isEmpty(request.getParameter("status")) && StringUtil.isEmpty(request.getParameter("title"))){
			createCriteria.andStatusEqualTo("1");
		}
		
		createCriteria.andDelFlagEqualTo("0");
		
		Integer countByExample = informationService.countByExample(informationExample);
		
		informationExample.setLimitStart(page.getLimitStart());
		informationExample.setLimitSize(page.getLimitSize());
		
		List<InformationCustom> selectByExample = informationService.selectInformationByExample(informationExample);
		for (int i = 0; i < selectByExample.size(); i++) {
			InformationCustom informationCustom = selectByExample.get(i);
			informationCustom.setCreatDataDsc(getdata(informationCustom.getReleaseTime()));
			String infoType = informationCustom.getInfoType();
			String desinfotype = "";
			if (!"".equals(infoType) && infoType!=null){
				String[] split = infoType.split(",");
				for (int j = 0; j < split.length; j++) {
					if(split[j].equals("1")){
						//1会员2新申请商家3入驻商家4游客
						desinfotype += ",游客";
					}else if(split[j].equals("2")){
						desinfotype += ",会员";
					}else if(split[j].equals("3")){
						desinfotype += ",新申请商家";
					}else if(split[j].equals("4")){
						desinfotype += ",入驻商家";
					}else if(split[j].equals("5")){
						desinfotype += ",平台用户";
					}
				}
				desinfotype = desinfotype.substring(1);
			}
			informationCustom.setInfoTypeDsc(desinfotype);
			String catalogListName = informationCustom.getCatalogListName();
			catalogListName = "栏目："+catalogListName;
			informationCustom.setCatalogListName(catalogListName);
			
			String reTitle = informationCustom.getTitle() + "<br> <font color=\"#BEBEBE\">" +informationCustom.getCatalogListName() + "</font>";
			informationCustom.setTitle(reTitle);
		}

		resMap.put("Rows", selectByExample);
		resMap.put("Total", countByExample);
		return resMap;
	}
	
	//根据栏目树id查询列表数据
	@RequestMapping(value = "/infomation/listOther.shtml")
	 public ModelAndView listOther(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/infomation/listOther";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("id", paramMap.get("id"));
		return new ModelAndView(rtPage,resMap);
	}
	
	
	@RequestMapping(value="/infomation/listdata.shtml")
	@ResponseBody
	public HashMap<String, Object> getInformationlistdata(HttpServletRequest request, Page page){
	
		String staffs = request.getParameter("staff");
		
		String infoTypeDsc=request.getParameter("infoTypeDsc");
		
		int id=Integer.valueOf(request.getParameter("id"));
		
		
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		InformationCustomExample informationExample = new InformationCustomExample();
		InformationCustomCriteria createCriteria = informationExample.createCriteria();
		createCriteria.andCatalogIdEqualTo(id);
		informationExample.setOrderByClause("t.release_time desc,id desc");

		if(!StringUtil.isEmpty(staffs)){
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			createCriteria.andCreateByEqualTo(staffId);
		}else{
			if(!StringUtil.isEmpty(request.getParameter("catalogId"))){
				createCriteria.andCatalogAll(Integer.valueOf(request.getParameter("catalogId")));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("title"))){
				createCriteria.andTitleECLike(request.getParameter("title"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("status"))){
				createCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			if(!StringUtil.isEmpty(request.getParameter("ids"))){
				createCriteria.andIdEqualTo(Integer.valueOf(request.getParameter("ids")));
			}
			if(!StringUtils.isEmpty(infoTypeDsc)){
				/*if(infoTypeDsc.equals("1")){
					createCriteria.andInfoTypeLike("1%");
				}else if(infoTypeDsc.equals("2") || infoTypeDsc.equals("3") || infoTypeDsc.equals("4") || infoTypeDsc.equals("5")){
				}*/
				createCriteria.andInfoTypeLikeTo(infoTypeDsc);
			}
			
		}
		
		if(StringUtil.isEmpty(request.getParameter("catalogId")) && StringUtil.isEmpty(request.getParameter("status")) && StringUtil.isEmpty(request.getParameter("title"))){
			createCriteria.andStatusEqualTo("1");
		}
		
		createCriteria.andDelFlagEqualTo("0");
		
		Integer countByExample = informationService.countByExample(informationExample);
		
		informationExample.setLimitStart(page.getLimitStart());
		informationExample.setLimitSize(page.getLimitSize());
		
		List<InformationCustom> selectByExample = informationService.selectInformationByExample(informationExample);
		for (int i = 0; i < selectByExample.size(); i++) {
			InformationCustom informationCustom = selectByExample.get(i);
			informationCustom.setCreatDataDsc(getdata(informationCustom.getReleaseTime()));
			String infoType = informationCustom.getInfoType();
			String desinfotype = "";
			if (!"".equals(infoType) && infoType!=null){
				String[] split = infoType.split(",");
				for (int j = 0; j < split.length; j++) {
					if(split[j].equals("1")){
						//1会员2新申请商家3入驻商家4游客
						desinfotype += ",游客";
					}else if(split[j].equals("2")){
						desinfotype += ",会员";
					}else if(split[j].equals("3")){
						desinfotype += ",新申请商家";
					}else if(split[j].equals("4")){
						desinfotype += ",入驻商家";
					}else if(split[j].equals("5")){
						desinfotype += ",平台用户";
					}
				}
				desinfotype = desinfotype.substring(1);
			}
			informationCustom.setInfoTypeDsc(desinfotype);
			String catalogListName = informationCustom.getCatalogListName();
			catalogListName = "栏目："+catalogListName;
			informationCustom.setCatalogListName(catalogListName);
			
			String reTitle = informationCustom.getTitle() + "<br> <font color=\"#BEBEBE\">" +informationCustom.getCatalogListName() + "</font>";
			informationCustom.setTitle(reTitle);
		}

		resMap.put("Rows", selectByExample);
		resMap.put("Total", countByExample);
		return resMap;
	}
	
	
	
}
