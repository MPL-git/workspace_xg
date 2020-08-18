package com.jf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.entity.StateCode;
import com.jf.entity.SysStatus;
import com.jf.entity.ToutiaoAdInfo;
import com.jf.entity.ToutiaoAdInfoCustom;
import com.jf.entity.ToutiaoAdInfoCustomExample;
import com.jf.service.ToutiaoAdInfoService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class ToutiaoAdInfoController extends BaseController {

	@Autowired
	private ToutiaoAdInfoService toutiaoAdInfoService;
	
	/**
	 * 
	 * @Title toutiaoAdInfoManager   
	 * @Description TODO(广告计划)   
	 * @author pengl
	 * @date 2018年8月17日 下午2:32:45
	 */
	@RequestMapping("/toutiaoAd/toutiaoAdInfoManager.shtml")
	public ModelAndView toutiaoAdInfoManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/toutiao/getToutiaoAdList");
		m.addObject("landingTypeList", DataDicUtil.getTableStatus("TOUTIAO_CAMPAIGN_INFO", "LANDING_TYPE"));
		m.addObject("statusList", DataDicUtil.getTableStatus("TOUTIAO_AD_INFO", "STATUS"));
		return m;
	}
	
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/toutiaoAd/getToutiaoAdList.shtml")
	public Map<String, Object> getToutiaoAdList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<ToutiaoAdInfoCustom> dataList = null;
		Integer totalCount = 0;
		try {
			ToutiaoAdInfoCustomExample toutiaoAdInfoCustomExample = new ToutiaoAdInfoCustomExample();
			ToutiaoAdInfoCustomExample.ToutiaoAdInfoCustomCriteria toutiaoAdInfoCustomCriteria = toutiaoAdInfoCustomExample.createCriteria();
			toutiaoAdInfoCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("adId")) ) {
				toutiaoAdInfoCustomCriteria.andAdIdEqualTo(request.getParameter("adId"));
			}
			if(!StringUtil.isEmpty(request.getParameter("adName")) ) {
				toutiaoAdInfoCustomCriteria.andNameLike("%"+request.getParameter("adName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("advertiserName")) ) {
				toutiaoAdInfoCustomCriteria.andAdvertiserNameLike("%"+request.getParameter("advertiserName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("campaignName")) ) {
				toutiaoAdInfoCustomCriteria.andCampaignNameLike("%"+request.getParameter("campaignName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("landingType")) ) {
				toutiaoAdInfoCustomCriteria.andLandingTypeEqualTo(request.getParameter("landingType"));
			}
			if(!StringUtil.isEmpty(request.getParameter("status")) ) {
				toutiaoAdInfoCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			toutiaoAdInfoCustomExample.setOrderByClause(" t.ad_create_time desc");
			toutiaoAdInfoCustomExample.setLimitStart(page.getLimitStart());
			toutiaoAdInfoCustomExample.setLimitSize(page.getLimitSize());
			dataList = toutiaoAdInfoService.selectByCustomExample(toutiaoAdInfoCustomExample);
			totalCount = toutiaoAdInfoService.countByCustomExample(toutiaoAdInfoCustomExample);
			List<SysStatus> sysStatusList = DataDicUtil.getTableStatus("TOUTIAO_AD_INFO", "INVENTORY_TYPE");
			for(ToutiaoAdInfoCustom toutiaoAdInfoCustom : dataList ) {
				if(!StringUtil.isEmpty(toutiaoAdInfoCustom.getCreativeDtl()) ) {
					JSONObject creativeDtlJson = JSONObject.fromObject(toutiaoAdInfoCustom.getCreativeDtl());
					if(creativeDtlJson.containsKey("inventory_type") ) {
						List<String> inventoryTypeList = creativeDtlJson.getJSONArray("inventory_type");
						StringBuffer stringBuffer = new StringBuffer();
						for(String inventoryType : inventoryTypeList) {
							if(stringBuffer.length() != 0) {
								stringBuffer.append(",");
							}
							for(SysStatus sysStatus : sysStatusList) {
								if(sysStatus.getStatusValue().equals(inventoryType) ) {
									stringBuffer.append(sysStatus.getStatusDesc());
								}
							}
						}
						toutiaoAdInfoCustom.setInventoryType(stringBuffer.toString());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title copyToutiaoAdManager   
	 * @Description TODO(复制计划)   
	 * @author pengl
	 * @date 2018年8月17日 下午4:19:37
	 */
	@RequestMapping("/toutiaoAd/copyToutiaoAdManager.shtml")
	public ModelAndView copyToutiaoAdManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/toutiao/copyToutiaoAd");
		if(!StringUtil.isEmpty(request.getParameter("adInfoId")) ) {
			ToutiaoAdInfo toutiaoAdInfo = toutiaoAdInfoService.selectByPrimaryKey(Integer.parseInt(request.getParameter("adInfoId")));
			m.addObject("toutiaoAdInfo", toutiaoAdInfo);
		}
		return m;
	}
	
	/**
	 * 
	 * @Title copyToutiaoAd   
	 * @Description TODO(发送创建头条推广广告计划信息)   
	 * @author pengl
	 * @date 2018年8月17日 下午6:07:02
	 */
	@RequestMapping("/toutiaoAd/copyToutiaoAd.shtml")
	public ModelAndView copyToutiaoAd(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			String message = toutiaoAdInfoService.sendPsotToutiaoCreateAd(request);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = message;
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap); 
	}
	
	
	
}
