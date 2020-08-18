package com.jf.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.MchtInfoCustom;
import com.jf.entity.MchtInfoCustomExample;
import com.jf.entity.PlatformMsg;
import com.jf.entity.PlatformMsgCustom;
import com.jf.entity.PlatformMsgCustomExample;
import com.jf.entity.PlatformMsgEdit;
import com.jf.entity.PlatformMsgEditCustom;
import com.jf.entity.PlatformMsgEditCustomExample;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.SubOrderAttachment;
import com.jf.service.MchtInfoService;
import com.jf.service.PlatformMsgEditService;
import com.jf.service.PlatformMsgService;
import com.jf.service.SysStatusService;
import com.jf.vo.Page;

/**
 * 站内消息
 * @author luoyl
 * 创建时间：2017-3-26 14:47
 */
@Controller
@RequestMapping("platformMsg")
public class PlatformMsgController extends BaseController{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3736298942988954093L;
	@Resource
	private PlatformMsgService platformMsgService;
	@Autowired
	private SysStatusService sysStatusService;
	@Resource
	private PlatformMsgEditService platformMsgEditService;	
	@Autowired
	private MchtInfoService mchtInfoService;
	/**
	 * 站内消息列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("platformMsg-list.shtml")
	public ModelAndView getList(Model model,HttpServletRequest request){
		String rtPage = "/platformMsg/list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		resMap.put("statusList", DataDicUtil.getStatusList("BU_PLATFORM_MSG", "MSG_TYPE"));
		return new ModelAndView(rtPage,resMap);
	}

	@RequestMapping("platformMsg-list-data.shtml")
	@ResponseBody
	public Map<String, Object> getListData(HttpServletRequest request,HttpServletResponse response,Page page){
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		Integer totalCount =0;
		try {
			
			PlatformMsgCustomExample platformMsgCustomExample=new PlatformMsgCustomExample();
			PlatformMsgCustomExample.PlatformMsgCustomCriteria platformMsgCustomCriteria=platformMsgCustomExample.createCriteria(); 
			platformMsgCustomCriteria.andDelFlagEqualTo("0");
			platformMsgCustomExample.setOrderByClause("a.id desc");
			//商家搜索(包括：商家简称（shortName）和商家编号（mchtCode）)
			if(!StringUtil.isEmpty(request.getParameter("memberName"))){
				String mchtName=request.getParameter("memberName");
				platformMsgCustomCriteria.andMchtNameOrMchtCodeLike("%"+mchtName+"%");
			}
			//状态类型
			if(!StringUtil.isEmpty(request.getParameter("msgType"))){
				platformMsgCustomCriteria.andMsgTypeEqualTo(request.getParameter("msgType"));
			}
			platformMsgCustomExample.setLimitSize(page.getLimitSize());
			platformMsgCustomExample.setLimitStart(page.getLimitStart());
			totalCount=platformMsgService.countPlatformMsgCustomByExample(platformMsgCustomExample);
			dataList=platformMsgService.selectPlatformMsgCustomByExample(platformMsgCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @param platformMsgId
	 * @return
	 */
	@RequestMapping("platformMsg-del.shtml")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request,HttpServletResponse response,String platformMsgId){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			String[] split = platformMsgId.split(",");
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			for (int i = 0; i < split.length; i++) {
				PlatformMsg platformMsg=platformMsgService.selectByPrimaryKey(Integer.valueOf(split[i]));
				platformMsg.setDelFlag("1");
				platformMsg.setUpdateBy(staffId);
				platformMsg.setUpdateDate(new Date());
				platformMsgService.updateByPrimaryKeySelective(platformMsg);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	
	/**
	 * 查看详情
	 * @param model
	 * @param request
	 * @param infoId
	 * @return
	 */
	@RequestMapping("platformMsg-deitInfo.shtml")
	public String editInfo(Model model,HttpServletRequest request,String infoId){
		PlatformMsgCustom platformMsg=platformMsgService.selectPlatformMsgCustomByPrimaryKey(Integer.valueOf(infoId));
		model.addAttribute("platformMsg", platformMsg);
		platformMsg.setUpdateDate(new Date());
		platformMsgService.updateByPrimaryKeySelective(platformMsg);
		return "/platformMsg/info";
	}
	
	
	
	//创建站内信列表
	@RequestMapping(value = "establishMail.shtml")
	public ModelAndView establishMailList(HttpServletRequest request) {
		String page = "/platformMsg/establishMail";
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(page, resMap);
	}
	    //获取站内信列表数据
		@RequestMapping(value = "establishmaildata.shtml")
		@ResponseBody
		public Map<String, Object> establishmailData(HttpServletRequest request,Page page) {
			Map<String,Object> resMap = new HashMap<String,Object>();
			Integer totalCount =0;		
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				PlatformMsgEditCustomExample platformMsgEditCustomExample = new PlatformMsgEditCustomExample();
				PlatformMsgEditCustomExample.PlatformMsgEditExampleCustomCriteria createCriteria = platformMsgEditCustomExample.createCriteria();
				createCriteria.andDelFlagEqualTo("0");
				if (!StringUtil.isEmpty(request.getParameter("platformMsgEditmsgType"))) {
					createCriteria.andMsgTypeEqualTo(request.getParameter("platformMsgEditmsgType"));						
				}
				if (!StringUtil.isEmpty(request.getParameter("platformMsgEditmsgstatus"))) {
					createCriteria.andStatusEqualTo(request.getParameter("platformMsgEditmsgstatus"));						
				}
				if(!StringUtil.isEmpty(request.getParameter("dateBegin")) ){
					createCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("dateBegin")+" 00:00:00"));
				}		
				if(!StringUtil.isEmpty(request.getParameter("dateEnd")) ){
					createCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("dateEnd")+" 23:59:59"));
				}			
				platformMsgEditCustomExample.setLimitStart(page.getLimitStart());
				platformMsgEditCustomExample.setLimitSize(page.getLimitSize());
				totalCount = platformMsgEditService.countPlatformMsgEditMapperCustomByExample(platformMsgEditCustomExample);
                
				List<PlatformMsgEditCustom> PlatformMsgEditCustoms = platformMsgEditService.selectPlatformMsgEditMapperCustomByExample(platformMsgEditCustomExample);
				
				resMap.put("Rows", PlatformMsgEditCustoms);
				resMap.put("Total", totalCount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return resMap;
		}
		
		//获取站内信信息
		@RequestMapping(value = "editEstablishMail.shtml")
		public ModelAndView updateeditEstablishMail(HttpServletRequest request, String establishmailID) {
			ModelAndView m = new ModelAndView();			
			if(!StringUtil.isEmpty(establishmailID)) {
			PlatformMsgEdit platformMsgEdit = platformMsgEditService.selectByPrimaryKey(Integer.parseInt(establishmailID));
				m.addObject("platformMsgEdit", platformMsgEdit);
			}			
				m.setViewName("/platformMsg/editEstablishMail");
			return m;
		}
		
		//创建或修改站内信
		@ResponseBody
		@RequestMapping(value = "addestablishMail.shtml")
		public ModelAndView addupdateEditestablishMail(HttpServletRequest request, PlatformMsgEdit platformMsgEdit) {
			String rtPage = "/success/success";
			Map<String, Object> resMap = new HashMap<String, Object>();
			String code = "";
			String msg = "";
			try {
				StaffBean staffBean = this.getSessionStaffBean(request);
				Integer staffId = Integer.valueOf(staffBean.getStaffID());
				Date date = new Date();
				if(platformMsgEdit.getId() == null) { //创建
					platformMsgEdit.setMchtScope("1");
					platformMsgEdit.setCreateBy(staffId);
					platformMsgEdit.setCreateDate(date);
					platformMsgEdit.setUpdateDate(date);
					platformMsgEditService.insertSelective(platformMsgEdit); 
				}else { //修改
					platformMsgEdit.setId(platformMsgEdit.getId());
					platformMsgEdit.setUpdateBy(staffId);
					platformMsgEdit.setUpdateDate(date);
					platformMsgEditService.updateByPrimaryKeySelective(platformMsgEdit);
				}
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			} catch (Exception e) {
				e.printStackTrace();
				code = StateCode.JSON_AJAX_ERROR.getStateCode();
				msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
			}
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);
			return new ModelAndView(rtPage, resMap); 
		}
		
		//删除站内信
		@ResponseBody
		@RequestMapping(value = "deleteestablishMail.shtml")
		public Map<String, Object> saledel(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>(); 
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "成功");
			try {
				Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
				Integer id=Integer.valueOf(request.getParameter("id"));
				PlatformMsgEdit PlatformmsgEdit=new PlatformMsgEdit();
				PlatformmsgEdit.setId(id);
				PlatformmsgEdit.setUpdateBy(staffId);
				PlatformmsgEdit.setUpdateDate(new Date());
				PlatformmsgEdit.setDelFlag("1");
				platformMsgEditService.updateByPrimaryKeySelective(PlatformmsgEdit);

			}catch (Exception e) {
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", e.getMessage());
				e.printStackTrace();
			}
			return resMap;
		}
		
		//提审站内信
		@ResponseBody
		@RequestMapping(value = "trialestablishMail.shtml")
		public Map<String, Object> trialestablishMailStatus(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>(); 
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "成功");
			try {
				Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
				Integer id=Integer.valueOf(request.getParameter("id"));
				String status=request.getParameter("status");				
				PlatformMsgEdit platformmsgEdit=new PlatformMsgEdit();
				platformmsgEdit.setId(id);
				platformmsgEdit.setUpdateBy(staffId);
				platformmsgEdit.setUpdateDate(new Date());
				platformmsgEdit.setStatus(status);
				platformMsgEditService.updateByPrimaryKeySelective(platformmsgEdit);
			}catch (Exception e) {
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", e.getMessage());
				e.printStackTrace();
			}
			return resMap;
		}
		
		
		//检测站内信商家
		@RequestMapping(value ="establishMailmchtInfoList.shtml")
		public ModelAndView platformmsgemberInfoList(HttpServletRequest request) {
			ModelAndView m = new ModelAndView();
			if(!StringUtil.isEmpty(request.getParameter("mchtCodes"))) {
				MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
				MchtInfoCustomExample.MchtInfoCustomCriteria mchtInfoCustomCriteria = mchtInfoCustomExample.createCriteria();
				mchtInfoCustomCriteria.andDelFlagEqualTo("0");
				List<String> mchtCodeList = new ArrayList<String>();
				String[] mchtCodeS = request.getParameter("mchtCodes").split(",");
				for(String mchtCode : mchtCodeS) {
					mchtCodeList.add(mchtCode);
				}
				mchtInfoCustomCriteria.andMchtCodeIn(mchtCodeList);
				List<MchtInfoCustom> dataList = mchtInfoService.selectByExample(mchtInfoCustomExample);
				String mchtIdGroup = "";
				for(MchtInfoCustom mchtInfoCustom : dataList) {
					if("".equals(mchtIdGroup)) {
						mchtIdGroup = mchtInfoCustom.getId().toString();
					}else {
						mchtIdGroup += "," + mchtInfoCustom.getId().toString();
					}	
				}
				m.addObject("mchtIdGroup", mchtIdGroup);
				m.setViewName("/platformMsg/mchtInfoList");
			}	
			return m;
		}
		
		//获取站内信商家列表数据
		@ResponseBody
		@RequestMapping(value = "mchtInfodata.shtml")
		public Map<String, Object> mchtmemberInfoList(HttpServletRequest request, Page page){
			Map<String, Object> resMap = new HashMap<String, Object>();
			List<MchtInfoCustom> dataList = new ArrayList<MchtInfoCustom>();
			Integer totalCount = 0;
			try {
				MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
				MchtInfoCustomExample.MchtInfoCustomCriteria mchtInfoCustomCriteria = mchtInfoCustomExample.createCriteria();
				mchtInfoCustomCriteria.andDelFlagEqualTo("0");
				if(!StringUtil.isEmpty(request.getParameter("mchtIdGroup"))) {
					List<Integer> mchtIdList = new ArrayList<Integer>();
					String[] mchtIdS = request.getParameter("mchtIdGroup").split(",");
					for(String mchtId : mchtIdS) {
						mchtIdList.add(Integer.valueOf(mchtId));
					}
					mchtInfoCustomCriteria.andIdIn(mchtIdList);
				}else {
					mchtInfoCustomCriteria.andIdEqualTo(0);
				}
				
				mchtInfoCustomExample.setLimitSize(page.getLimitSize());
				mchtInfoCustomExample.setLimitStart(page.getLimitStart());
				dataList = mchtInfoService.selectByExample(mchtInfoCustomExample);
				totalCount = mchtInfoService.countByExample(mchtInfoCustomExample);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
			return resMap;
		}
		
		//发送站内信
		@ResponseBody
		@RequestMapping(value = "sendoutestablishMail.shtml")
		public Map<String, Object> sendoutestablishmail(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>(); 
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "成功");
			try {
				Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
				Date date = new Date();
				Integer id=Integer.valueOf(request.getParameter("id"));
				String status=request.getParameter("status");
				PlatformMsgEdit platformMsgEdit = platformMsgEditService.selectByPrimaryKey(id);
				MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
				MchtInfoCustomExample.MchtInfoCustomCriteria mchtInfoCustomCriteria = mchtInfoCustomExample.createCriteria();
				mchtInfoCustomCriteria.andDelFlagEqualTo("0");
				List<String> mchtidList = new ArrayList<String>();
				String[] mchtId =platformMsgEdit.getMchtCodes().split(",");
				for (String mchtid : mchtId) {
					 mchtidList.add(mchtid);
				}
				mchtInfoCustomCriteria.andMchtCodeIn(mchtidList);
				List<MchtInfoCustom> dataList = mchtInfoService.selectByExample(mchtInfoCustomExample);
				for (MchtInfoCustom mchtInfoCustom : dataList) {
					PlatformMsg platformMsg=new PlatformMsg();
					platformMsg.setMsgType(platformMsgEdit.getMsgType());
					platformMsg.setTitle(platformMsgEdit.getTitle());
					platformMsg.setContent(platformMsgEdit.getContent());
					platformMsg.setMchtId(mchtInfoCustom.getId());
					platformMsg.setCreateDate(date);
					platformMsg.setCreateBy(staffId);
					platformMsg.setPlatformMsgEditId(platformMsgEdit.getId());
					platformMsgService.insertSelective(platformMsg);				
				}
				
				platformMsgEdit.setStatus(status);
				platformMsgEdit.setUpdateDate(date);
				platformMsgEdit.setUpdateBy(staffId);
				platformMsgEditService.updateByPrimaryKeySelective(platformMsgEdit);
				
			}catch (Exception e) {
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", e.getMessage());
				e.printStackTrace();
			}
			return resMap;
		}
		
		
		//查看站内信
		@RequestMapping(value = "establishMailSee.shtml")
		public ModelAndView establishMailsee(HttpServletRequest request, String establishmailid) {
			ModelAndView m = new ModelAndView();
			if(!StringUtil.isEmpty(establishmailid)) {
				PlatformMsgEdit platformMsgEdit = platformMsgEditService.selectByPrimaryKey(Integer.parseInt(establishmailid));
				if(platformMsgEdit.getMchtCodes()!=null) {
					MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
					MchtInfoCustomExample.MchtInfoCustomCriteria mchtInfoCustomCriteria = mchtInfoCustomExample.createCriteria();
					mchtInfoCustomCriteria.andDelFlagEqualTo("0");
					List<String> mchtcodeList = new ArrayList<String>();
					String[] mchtCodeS = platformMsgEdit.getMchtCodes().split(",");
					for(String mchtCode : mchtCodeS) {
						mchtcodeList.add(mchtCode);
					}
					mchtInfoCustomCriteria.andMchtCodeIn(mchtcodeList);
					List<MchtInfoCustom> dataList = mchtInfoService.selectByExample(mchtInfoCustomExample);
					String mchtidGroup = "";
					for(MchtInfoCustom mchtInfoCustom : dataList) {
						if("".equals(mchtidGroup)) {
							mchtidGroup = mchtInfoCustom.getId().toString();
						}else {
							mchtidGroup += "," + mchtInfoCustom.getId().toString();
						}	
					}
					m.addObject("mchtidGroup", mchtidGroup);
				}
				m.addObject("platformsgEdit", platformMsgEdit);
			}
				m.setViewName("/platformMsg/seeEstablishMail");
				return m;
			}
	
		
		
		//站内信审核
		@RequestMapping(value = "examineMail.shtml")
		public ModelAndView examineMailList(HttpServletRequest request) {
			String page = "/platformMsg/examineMail";
			Map<String, Object> resMap = new HashMap<String, Object>();
			return new ModelAndView(page, resMap);
		}
		    //获取站内信审核列表
			@RequestMapping(value = "examineMaildata.shtml")
			@ResponseBody
			public Map<String, Object> examineMaildata(HttpServletRequest request,Page page) {
				Map<String,Object> resMap = new HashMap<String,Object>();
				Integer totalCount =0;		
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					PlatformMsgEditCustomExample platformMsgEditCustomExample = new PlatformMsgEditCustomExample();
					PlatformMsgEditCustomExample.PlatformMsgEditExampleCustomCriteria createCriteria = platformMsgEditCustomExample.createCriteria();
					createCriteria.andDelFlagEqualTo("0");
					if (!StringUtil.isEmpty(request.getParameter("examineMailType"))) {
						createCriteria.andMsgTypeEqualTo(request.getParameter("examineMailType"));						
					}
					if (!StringUtil.isEmpty(request.getParameter("examineMailstatus"))) {
						createCriteria.andStatusEqualTo(request.getParameter("examineMailstatus"));						
					}
					if(!StringUtil.isEmpty(request.getParameter("updateBegin")) ){
						createCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("updateBegin")+" 00:00:00"));
					}		
					if(!StringUtil.isEmpty(request.getParameter("updateEnd")) ){
						createCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("updateEnd")+" 23:59:59"));
					}	
					if(!StringUtil.isEmpty(request.getParameter("mchtCode")) ){
						createCriteria.andMchtCodesLike("%"+request.getParameter("mchtCode")+"%");
					}
					platformMsgEditCustomExample.setLimitStart(page.getLimitStart());
					platformMsgEditCustomExample.setLimitSize(page.getLimitSize());
					totalCount = platformMsgEditService.countPlatformMsgEditMapperCustomByExample(platformMsgEditCustomExample);
	                
					List<PlatformMsgEditCustom> PlatformMsgEditCustoms = platformMsgEditService.selectPlatformMsgEditMapperCustomByExample(platformMsgEditCustomExample);
					
					resMap.put("Rows", PlatformMsgEditCustoms);
					resMap.put("Total", totalCount);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return resMap;
			}
			
			//审核站内信
			@RequestMapping(value = "toestablishMailSee.shtml")
			public ModelAndView toestablishMailSee(HttpServletRequest request, String establishmailId) {
				ModelAndView m = new ModelAndView();
				if(!StringUtil.isEmpty(establishmailId)) {
					PlatformMsgEdit platformMsgexamine = platformMsgEditService.selectByPrimaryKey(Integer.parseInt(establishmailId));
					if(platformMsgexamine.getMchtCodes()!=null) {
						MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
						MchtInfoCustomExample.MchtInfoCustomCriteria mchtInfoCustomCriteria = mchtInfoCustomExample.createCriteria();
						mchtInfoCustomCriteria.andDelFlagEqualTo("0");
						List<String> mchtcodeList = new ArrayList<String>();
						String[] mchtcodes = platformMsgexamine.getMchtCodes().split(",");
						for(String mchtCode : mchtcodes) {
							mchtcodeList.add(mchtCode);
						}
						mchtInfoCustomCriteria.andMchtCodeIn(mchtcodeList);
						List<MchtInfoCustom> dataList = mchtInfoService.selectByExample(mchtInfoCustomExample);
						String mchtidGroup = "";
						for(MchtInfoCustom mchtInfoCustom : dataList) {
							if("".equals(mchtidGroup)) {
								mchtidGroup = mchtInfoCustom.getId().toString();
							}else {
								mchtidGroup += "," + mchtInfoCustom.getId().toString();
							}	
						}
						m.addObject("mchtidGroup", mchtidGroup);
					}
					m.addObject("platformsgexamine", platformMsgexamine);
				}
					m.setViewName("/platformMsg/toexamineMail");
					return m;
				}
			
			//审核提交
			@ResponseBody
			@RequestMapping(value = "updatetoexamineMail.shtml")
			public ModelAndView updatetoexamineMail(HttpServletRequest request, Integer id, String status, String auditRemarks) {
				String rtPage = "/success/success";
				Map<String, Object> resMap = new HashMap<String, Object>();
				String code = "";
				String msg = "";
				try {
					StaffBean staffBean = this.getSessionStaffBean(request);
					Integer staffId = Integer.valueOf(staffBean.getStaffID());
					Date date = new Date();
					if (id!=null) {
					   PlatformMsgEdit platformMsgtoexamine = platformMsgEditService.selectByPrimaryKey(id);
					   platformMsgtoexamine.setStatus(status);
					   platformMsgtoexamine.setAuditRemarks(auditRemarks);
					   platformMsgtoexamine.setUpdateBy(staffId);
					   platformMsgtoexamine.setUpdateDate(date);
					   platformMsgEditService.updateByPrimaryKeySelective(platformMsgtoexamine);
					}
					code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
					msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
				} catch (Exception e) {
					e.printStackTrace();
					code = StateCode.JSON_AJAX_ERROR.getStateCode();
					msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
				}
				resMap.put(this.JSON_RESULT_CODE, code);
				resMap.put(this.JSON_RESULT_MESSAGE, msg);
				return new ModelAndView(rtPage, resMap); 
			}
			
			//查看审核站内信
			@RequestMapping(value = "seeToexamineMail.shtml")
			public ModelAndView seeToexamineMail(HttpServletRequest request, String establishmailIdsee) {
				ModelAndView m = new ModelAndView();
				if(!StringUtil.isEmpty(establishmailIdsee)) {
					PlatformMsgEdit platformsgtoexamine = platformMsgEditService.selectByPrimaryKey(Integer.parseInt(establishmailIdsee));
					if(platformsgtoexamine.getMchtCodes()!=null) {
						MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
						MchtInfoCustomExample.MchtInfoCustomCriteria mchtInfoCustomCriteria = mchtInfoCustomExample.createCriteria();
						mchtInfoCustomCriteria.andDelFlagEqualTo("0");
						List<String> mchtcodeList = new ArrayList<String>();
						String[] mchtcodes = platformsgtoexamine.getMchtCodes().split(",");
						for(String mchtCode : mchtcodes) {
							mchtcodeList.add(mchtCode);
						}
						mchtInfoCustomCriteria.andMchtCodeIn(mchtcodeList);
						List<MchtInfoCustom> dataList = mchtInfoService.selectByExample(mchtInfoCustomExample);
						String mchtidGroup = "";
						for(MchtInfoCustom mchtInfoCustom : dataList) {
							if("".equals(mchtidGroup)) {
								mchtidGroup = mchtInfoCustom.getId().toString();
							}else {
								mchtidGroup += "," + mchtInfoCustom.getId().toString();
							}	
						}
						m.addObject("mchtidGroup", mchtidGroup);
					}
					m.addObject("platformsgtoexamine", platformsgtoexamine);
				}
					m.setViewName("/platformMsg/seetoexamineMail");
					return m;
				}
	
			@RequestMapping("/checkFileExists")
			@ResponseBody
			public Map<String, Object> checkFileExists(HttpServletRequest request) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", "200");
				map.put("msg", "操作成功！");
				String attachmentPath = request.getParameter("attachmentPath");
				if(!StringUtils.isEmpty(attachmentPath)){
					InputStream stream = OrderController.class.getResourceAsStream("/base_config.properties");
					String defaultPath=null;
					try {
						Properties properties = new Properties();
						properties.load(stream);
						defaultPath = properties.getProperty("annex.filePath");
						stream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					if(defaultPath==null){
						map.put("code", "4004");
						map.put("msg", "文件目录不存在");
						return map;
					}
					File file = new File(defaultPath+attachmentPath);
					//如果文件不存在
					if(!file.exists()){
						map.put("code", "4004");
						map.put("msg", "附件不存在或已被删除");
						return map;
					}
				}else{
					map.put("code", "4004");
					map.put("msg", "附件不存在或已被删除");
					return map;
				}
				return map;
			}
			
			@RequestMapping("/downLoadAttachment")
			public void downLoadAttachment(HttpServletRequest request,HttpServletResponse response) throws Exception {
				String filePath = request.getParameter("filePath");
				String fileName = request.getParameter("fileName");
				InputStream stream = OrderController.class.getResourceAsStream("/base_config.properties");
				String defaultPath=null;
				try {
					Properties properties = new Properties();
					properties.load(stream);
					defaultPath = properties.getProperty("annex.filePath");
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(defaultPath==null){
					return;
				}
				File file = new File(defaultPath+filePath);
				//如果文件不存在
				if(!file.exists()){
				    return;
				}
				//设置响应头，控制浏览器下载该文件
				String downloadFileName = "";
				String agent = request.getHeader("USER-AGENT");  
		        if(agent != null && agent.toLowerCase().indexOf("firefox") > 0)
		        {
		            downloadFileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";    
		        }
		        else
		        {
		            downloadFileName =  java.net.URLEncoder.encode(fileName, "UTF-8");
		        }
				response.setHeader("content-disposition", "attachment;filename=" + downloadFileName);
				//读取要下载的文件，保存到文件输入流
				FileInputStream in = new FileInputStream(defaultPath+filePath);
				//创建输出流
				OutputStream out = response.getOutputStream();
				//缓存区
				byte buffer[] = new byte[1024];
				int len = 0;
				//循环将输入流中的内容读取到缓冲区中
				while((len = in.read(buffer)) > 0){
				    out.write(buffer, 0, len);
				}
				//关闭
				in.close();
				out.flush();
				out.close(); 
			}		
}
