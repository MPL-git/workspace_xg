package com.jf.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.ToutiaoAdvertiserInfo;
import com.jf.entity.ToutiaoAdvertiserInfoCustom;
import com.jf.entity.ToutiaoAdvertiserInfoCustomExample;
import com.jf.entity.ToutiaoAdvertiserInfoExample;
import com.jf.entity.ToutiaoTokenAdvertiserInfo;
import com.jf.entity.ToutiaoTokenInfo;
import com.jf.entity.ToutiaoTokenInfoExample;
import com.jf.service.ToutiaoAdInfoService;
import com.jf.service.ToutiaoAdvertiserInfoService;
import com.jf.service.ToutiaoCampaignInfoService;
import com.jf.service.ToutiaoTokenInfoService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class ToutiaoAdvertiserInfoController extends BaseController {

	@Autowired
	private ToutiaoAdvertiserInfoService toutiaoAdvertiserInfoService;
	
	@Autowired
	private ToutiaoCampaignInfoService toutiaoCampaignInfoService;
	
	@Autowired
	private ToutiaoAdInfoService toutiaoAdInfoService;
	
	@Autowired
	private ToutiaoTokenInfoService toutiaoTokenInfoService;
	
	/**
	 * 
	 * @Title toutiaoAdvertiserInfoManager   
	 * @Description TODO(广告主信息)   
	 * @author pengl
	 * @date 2018年8月20日 下午2:02:24
	 */
	@RequestMapping("/toutiaoAdvertiser/toutiaoAdvertiserInfoManager.shtml")
	public ModelAndView toutiaoAdvertiserInfoManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/toutiao/getToutiaoAdvertiserList");
		ToutiaoTokenInfoExample toutiaoTokenInfoExample = new ToutiaoTokenInfoExample();
		toutiaoTokenInfoExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andAccessTokenIsNotNull();
		List<ToutiaoTokenInfo> toutiaoTokenInfoList = toutiaoTokenInfoService.selectByExample(toutiaoTokenInfoExample);
		m.addObject("toutiaoTokenInfoList", toutiaoTokenInfoList);
		m.addObject("roleList", DataDicUtil.getTableStatus("TOUTIAO_ADVERTISER_INFO", "ROLE"));
		m.addObject("statusList", DataDicUtil.getTableStatus("TOUTIAO_ADVERTISER_INFO", "STATUS"));
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/toutiaoAdvertiser/getToutiaoAdvertiserList.shtml")
	public Map<String, Object> getToutiaoAdvertiserList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<ToutiaoAdvertiserInfoCustom> dataList = null;
		Integer totalCount = 0;
		try {
			ToutiaoAdvertiserInfoCustomExample toutiaoAdvertiserInfoCustomExample = new ToutiaoAdvertiserInfoCustomExample();
			ToutiaoAdvertiserInfoCustomExample.ToutiaoAdvertiserInfoCustomCriteria toutiaoAdvertiserInfoCustomCriteria = toutiaoAdvertiserInfoCustomExample.createCriteria();
			toutiaoAdvertiserInfoCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("advertiserId")) ) {
				toutiaoAdvertiserInfoCustomCriteria.andAdvertiserIdEqualTo(request.getParameter("advertiserId"));
			}
			if(!StringUtil.isEmpty(request.getParameter("advertiserName")) ) {
				toutiaoAdvertiserInfoCustomCriteria.andNameLike("%"+request.getParameter("advertiserName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("role")) ) {
				toutiaoAdvertiserInfoCustomCriteria.andRoleEqualTo(request.getParameter("role"));
			}
			if(!StringUtil.isEmpty(request.getParameter("status")) ) {
				toutiaoAdvertiserInfoCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			String tokenId = request.getParameter("tokenId");
			if(!StringUtil.isEmpty(tokenId) ) {
				toutiaoAdvertiserInfoCustomCriteria.andTokenId(Integer.parseInt(tokenId));
			}
			toutiaoAdvertiserInfoCustomExample.setOrderByClause(" t.id desc");
			toutiaoAdvertiserInfoCustomExample.setLimitStart(page.getLimitStart());
			toutiaoAdvertiserInfoCustomExample.setLimitSize(page.getLimitSize());
			dataList = toutiaoAdvertiserInfoService.selectByCustomExample(toutiaoAdvertiserInfoCustomExample);
			totalCount = toutiaoAdvertiserInfoService.countByCustomExample(toutiaoAdvertiserInfoCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title createToutiaoAdvertiserManager   
	 * @Description TODO(添加广告主)   
	 * @author pengl
	 * @date 2018年8月20日 下午2:18:38
	 */
	@RequestMapping("/toutiaoAdvertiser/createToutiaoAdvertiserManager.shtml")
	public ModelAndView createToutiaoAdvertiserManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/toutiao/createToutiaoAdvertiser");
		ToutiaoTokenInfoExample toutiaoTokenInfoExample = new ToutiaoTokenInfoExample();
		toutiaoTokenInfoExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andAccessTokenIsNotNull();
		List<ToutiaoTokenInfo> toutiaoTokenInfoList = toutiaoTokenInfoService.selectByExample(toutiaoTokenInfoExample);
		m.addObject("toutiaoTokenInfoList", toutiaoTokenInfoList);
		return m;
	}
	
	@RequestMapping("/toutiaoAdvertiser/saveToutiaoAdvertiser.shtml")
	public ModelAndView saveToutiaoAdvertiser(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Date date = new Date();
			ToutiaoAdvertiserInfo toutiaoAdvertiserInfo = new ToutiaoAdvertiserInfo();
			toutiaoAdvertiserInfo.setAdvertiserId(request.getParameter("advertiserId"));
			toutiaoAdvertiserInfo.setCreateBy(Integer.parseInt(staffBean.getStaffID()));
			toutiaoAdvertiserInfo.setCreateDate(date);
			toutiaoAdvertiserInfo.setDelFlag("0");
			ToutiaoTokenAdvertiserInfo toutiaoTokenAdvertiserInfo = new ToutiaoTokenAdvertiserInfo();
			toutiaoTokenAdvertiserInfo.setAdvertiserId(request.getParameter("advertiserId"));
			toutiaoTokenAdvertiserInfo.setTokenId(Integer.parseInt(request.getParameter("tokenId")));
			toutiaoTokenAdvertiserInfo.setCreateBy(Integer.parseInt(staffBean.getStaffID()));
			toutiaoTokenAdvertiserInfo.setCreateDate(date);
			toutiaoTokenAdvertiserInfo.setDelFlag("0");
			toutiaoAdvertiserInfoService.saveToutiaoAdvertiserInfo(toutiaoAdvertiserInfo, toutiaoTokenAdvertiserInfo);
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
	
	/**
	 * 
	 * @Title updatedVertiser   
	 * @Description TODO(删除广告主)   
	 * @author pengl
	 * @date 2018年8月20日 下午7:28:18
	 */
	@ResponseBody
	@RequestMapping("/toutiaoAdvertiser/delAdvertiser.shtml")
	public Map<String, Object> delAdvertiser(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "200";
		String message = "";
		try {
			if(!StringUtil.isEmpty(request.getParameter("id")) ) {
				StaffBean staffBean = this.getSessionStaffBean(request);
				ToutiaoAdvertiserInfo toutiaoAdvertiserInfo = toutiaoAdvertiserInfoService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
				toutiaoAdvertiserInfoService.delAdvertiser(staffBean, toutiaoAdvertiserInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			statusCode = "999";
			message = "系统错误";
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}
	
	/**
	 * 
	 * @Title updatedVertiser   
	 * @Description TODO(更新广告主信息)   
	 * @author pengl
	 * @date 2018年8月20日 下午4:36:35
	 */
	@ResponseBody
	@RequestMapping("/toutiaoAdvertiser/updatedAdvertiser.shtml")
	public Map<String, Object> updatedVertiser(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "200";
		String message = "";
		try {
			if(!StringUtil.isEmpty(request.getParameter("id")) ) {
				StaffBean staffBean = this.getSessionStaffBean(request);
				ToutiaoAdvertiserInfo toutiaoAdvertiserInfo = toutiaoAdvertiserInfoService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
				int batchCode = 1;
				if(toutiaoAdvertiserInfo.getBatchCode() != null ) {
					batchCode = toutiaoAdvertiserInfo.getBatchCode() + 1;
				}
				Map<String, String> returnMap = toutiaoAdvertiserInfoService.getAdvertiserInfo(staffBean, toutiaoAdvertiserInfo.getAdvertiserId(), batchCode);
				statusCode = returnMap.get("code").equals("0")?"200":returnMap.get("code");
				message = returnMap.get("message");
			}
		} catch (Exception e) {
			e.printStackTrace();
			statusCode = "999";
			message = "系统错误";
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}
	
	/**
	 * 
	 * @Title updatedCampaign   
	 * @Description TODO(更新广告组信息)   
	 * @author pengl
	 * @date 2018年8月20日 下午6:37:20
	 */
	@ResponseBody
	@RequestMapping("/toutiaoAdvertiser/updatedCampaignList.shtml")
	public Map<String, Object> updatedCampaignList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "200";
		String message = "";
		try {
			if(!StringUtil.isEmpty(request.getParameter("id")) ) {
				StaffBean staffBean = this.getSessionStaffBean(request);
				ToutiaoAdvertiserInfoCustom toutiaoAdvertiserInfoCustom = toutiaoAdvertiserInfoService.selectByCustomPrimaryKey(Integer.parseInt(request.getParameter("id")));
				int batchCode = 1;
				if(toutiaoAdvertiserInfoCustom.getCampaignBatchCode() != null ) {
					batchCode = toutiaoAdvertiserInfoCustom.getCampaignBatchCode() + 1;
				}
				int campaignPageSize = toutiaoAdvertiserInfoCustom.getCampaignPageSize()==null?50:toutiaoAdvertiserInfoCustom.getCampaignPageSize();
				Map<String, Object> returnMap = toutiaoCampaignInfoService.campaignGetList(staffBean, toutiaoAdvertiserInfoCustom.getAdvertiserId(), batchCode, campaignPageSize);
				statusCode = returnMap.get("code").toString();
				message = returnMap.get("message").toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			statusCode = "999";
			message = "系统错误";
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}
	
	/**
	 * 
	 * @Title updateAdList   
	 * @Description TODO(更新广告计划信息)   
	 * @author pengl
	 * @date 2018年8月21日 上午9:03:06
	 */
	@ResponseBody
	@RequestMapping("/toutiaoAdvertiser/updateAdList.shtml")
	public Map<String, Object> updateAdList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "200";
		String message = "";
		try {
			if(!StringUtil.isEmpty(request.getParameter("id")) ) {
				StaffBean staffBean = this.getSessionStaffBean(request);
				ToutiaoAdvertiserInfoCustom toutiaoAdvertiserInfoCustom = toutiaoAdvertiserInfoService.selectByCustomPrimaryKey(Integer.parseInt(request.getParameter("id")));
				int batchCode = 1;
				if(toutiaoAdvertiserInfoCustom.getAdBatchCode() != null ) {
					batchCode = toutiaoAdvertiserInfoCustom.getAdBatchCode() + 1;
				}
				int adPageSize = toutiaoAdvertiserInfoCustom.getAdPageSize()==null?50:toutiaoAdvertiserInfoCustom.getAdPageSize();
				Map<String, Object> returnMap = toutiaoAdInfoService.adList(staffBean, toutiaoAdvertiserInfoCustom.getAdvertiserId(), batchCode, adPageSize);
				statusCode = returnMap.get("code").toString();
				message = returnMap.get("message").toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			statusCode = "999";
			message = "系统错误";
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}
	
	/**
	 * 
	 * @Title validateAdvertiserId   
	 * @Description TODO(广告主ID验证是否已存在)   
	 * @author pengl
	 * @date 2018年8月23日 上午10:29:03
	 */
	@ResponseBody
	@RequestMapping("/toutiaoAdvertiser/validateAdvertiserId.shtml")
	public Map<String, Object> validateAdvertiserId(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "200";
		String message = "";
		try {
			if(!StringUtil.isEmpty(request.getParameter("advertiserId")) ) {
				ToutiaoAdvertiserInfoExample toutiaoAdvertiserInfoExample = new ToutiaoAdvertiserInfoExample();
				toutiaoAdvertiserInfoExample.createCriteria().andDelFlagEqualTo("0").andAdvertiserIdEqualTo(request.getParameter("advertiserId"));
				List<ToutiaoAdvertiserInfo> toutiaoAdvertiserInfos = toutiaoAdvertiserInfoService.selectByExample(toutiaoAdvertiserInfoExample);
				message = toutiaoAdvertiserInfos.size()+"";
			}
		} catch (Exception e) {
			statusCode = "999";
			message = "系统错误";
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}
	
}
