package com.jf.controller;

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
import com.jf.entity.ToutiaoCampaignInfoCustom;
import com.jf.entity.ToutiaoCampaignInfoCustomExample;
import com.jf.service.ToutiaoCampaignInfoService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class ToutiaoCampaignInfoController extends BaseController {

	@Autowired
	private ToutiaoCampaignInfoService toutiaoCampaignInfoService;
	
	/**
	 * 
	 * @Title toutiaoCampaignInfoManager   
	 * @Description TODO(广告组)   
	 * @author pengl
	 * @date 2018年8月17日 下午1:42:15
	 */
	@RequestMapping("/toutiaoCampaign/toutiaoCampaignInfoManager.shtml")
	public ModelAndView toutiaoCampaignInfoManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/toutiao/getToutiaoCampaignList");
		m.addObject("landingTypeList", DataDicUtil.getTableStatus("TOUTIAO_CAMPAIGN_INFO", "LANDING_TYPE"));
		m.addObject("statusList", DataDicUtil.getTableStatus("TOUTIAO_CAMPAIGN_INFO", "STATUS"));
		return m;
	}
	
	
	@ResponseBody
	@RequestMapping("/toutiaoCampaign/getToutiaoCampaignList.shtml")
	public Map<String, Object> getToutiaoCampaignList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<ToutiaoCampaignInfoCustom> dataList = null;
		Integer totalCount = 0;
		try {
			ToutiaoCampaignInfoCustomExample toutiaoCampaignInfoCustomExample = new ToutiaoCampaignInfoCustomExample();
			ToutiaoCampaignInfoCustomExample.ToutiaoCampaignInfoCustomCriteria toutiaoCampaignInfoCustomCriteria = toutiaoCampaignInfoCustomExample.createCriteria();
			toutiaoCampaignInfoCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("advertiserId")) ) {
				toutiaoCampaignInfoCustomCriteria.andAdvertiserIdEqualTo(request.getParameter("advertiserId"));
			}
			if(!StringUtil.isEmpty(request.getParameter("advertiserName")) ) {
				toutiaoCampaignInfoCustomCriteria.andAdvertiserNameLike("%"+request.getParameter("advertiserName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("campaignId")) ) {
				toutiaoCampaignInfoCustomCriteria.andCampaignIdEqualTo(request.getParameter("campaignId"));
			}
			if(!StringUtil.isEmpty(request.getParameter("campaignName")) ) {
				toutiaoCampaignInfoCustomCriteria.andNameLike("%"+request.getParameter("campaignName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("landingType")) ) {
				toutiaoCampaignInfoCustomCriteria.andLandingTypeEqualTo(request.getParameter("landingType"));
			}
			if(!StringUtil.isEmpty(request.getParameter("status")) ) {
				toutiaoCampaignInfoCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			toutiaoCampaignInfoCustomExample.setOrderByClause(" t.campaign_create_time desc");
			toutiaoCampaignInfoCustomExample.setLimitStart(page.getLimitStart());
			toutiaoCampaignInfoCustomExample.setLimitSize(page.getLimitSize());
			dataList = toutiaoCampaignInfoService.selectByCustomExample(toutiaoCampaignInfoCustomExample);
			totalCount = toutiaoCampaignInfoService.countByCustomExample(toutiaoCampaignInfoCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title delCampaign   
	 * @Description TODO(删除广告组)   
	 * @author pengl
	 * @date 2018年8月20日 下午7:28:18
	 *//*
	@ResponseBody
	@RequestMapping("/toutiaoCampaign/delCampaign.shtml")
	public Map<String, Object> delCampaign(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "200";
		String message = "";
		try {
			if(!StringUtil.isEmpty(request.getParameter("ids")) ) {
				StaffBean staffBean = this.getSessionStaffBean(request);
				String[] strs = request.getParameter("ids").split(",");
				List<Integer> idList = new ArrayList<Integer>();
				for(String id : strs) {
					idList.add(Integer.parseInt(id));
				}
				ToutiaoCampaignInfoExample toutiaoCampaignInfoExample = new ToutiaoCampaignInfoExample();
				toutiaoCampaignInfoExample.createCriteria().andDelFlagEqualTo("0")
					.andIdIn(idList);
				ToutiaoCampaignInfo toutiaoCampaignInfo = new ToutiaoCampaignInfo();
				toutiaoCampaignInfo.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
				toutiaoCampaignInfo.setUpdateDate(new Date());
				toutiaoCampaignInfo.setDelFlag("1");
				toutiaoCampaignInfoService.updateByExampleSelective(toutiaoCampaignInfo, toutiaoCampaignInfoExample);
			}
		} catch (Exception e) {
			statusCode = "999";
			message = "系统错误";
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}*/
	
	
	
	
	
	
}
