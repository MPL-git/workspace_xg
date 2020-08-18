package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.common.utils.TouTiaoSendUtil;
import com.jf.dao.ToutiaoAdInfoMapper;
import com.jf.dao.ToutiaoAdvertiserInfoCustomMapper;
import com.jf.dao.ToutiaoAdvertiserInfoMapper;
import com.jf.dao.ToutiaoCampaignInfoMapper;
import com.jf.dao.ToutiaoTokenInfoMapper;
import com.jf.entity.ToutiaoAdvertiserInfo;
import com.jf.entity.ToutiaoAdvertiserInfoExample;
import com.jf.entity.ToutiaoTokenInfo;
import com.jf.entity.ToutiaoTokenInfoExample;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ToutiaoAdvertiserInfoService extends BaseService<ToutiaoAdvertiserInfo, ToutiaoAdvertiserInfoExample> {

	@Autowired
	private ToutiaoAdvertiserInfoMapper toutiaoAdvertiserInfoMapper;
	
	@Autowired
	private ToutiaoAdvertiserInfoCustomMapper toutiaoAdvertiserInfoCustomMapper;
	
	@Autowired
	private ToutiaoAdInfoMapper toutiaoAdInfoMapper;
	
	@Autowired
	private ToutiaoCampaignInfoMapper toutiaoCampaignInfoMapper;
	
	@Autowired
	private ToutiaoTokenInfoMapper toutiaoTokenInfoMapper;
	
	@Autowired
	public void setToutiaoAdvertiserInfoMapper(ToutiaoAdvertiserInfoMapper toutiaoAdvertiserInfoMapper) {
		super.setDao(toutiaoAdvertiserInfoMapper);
		this.toutiaoAdvertiserInfoMapper = toutiaoAdvertiserInfoMapper;
	}
	
	/**
	 * 
	 * @Title oauthBack   
	 * @Description TODO(头条推广授权)   
	 * @author pengl
	 * @date 2018年8月15日 下午5:00:28
	 */
	public ResponseMsg oauthBack(HttpServletRequest request) {
        try {
			String stateStr = request.getParameter("state");
			if(!StringUtil.isEmpty(stateStr)) {
				String[] stateNum = stateStr.split("_");
				String tokenId = stateNum[0];
				String state = stateNum[1];
				ToutiaoTokenInfoExample toutiaoTokenInfoExample = new ToutiaoTokenInfoExample();
				toutiaoTokenInfoExample.createCriteria().andDelFlagEqualTo("0")
					.andIdEqualTo(Integer.parseInt(tokenId)).andStateEqualTo(state);
				List<ToutiaoTokenInfo> toutiaoTokenInfoList = toutiaoTokenInfoMapper.selectByExample(toutiaoTokenInfoExample);
				if(CollectionUtils.isNotEmpty(toutiaoTokenInfoList)) {
					ToutiaoTokenInfo toutiaoTokenInfo = toutiaoTokenInfoList.get(0);
					toutiaoTokenInfo.setAuthCode(request.getParameter("auth_code"));
					toutiaoTokenInfoMapper.updateByPrimaryKeySelective(toutiaoTokenInfo);
				}else {
					return new ResponseMsg(ResponseMsg.ERROR, "头条授权信息不存在");
				}
			}else {
				return new ResponseMsg(ResponseMsg.ERROR, "state为空");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
        return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 
	 * @Title accessToken   
	 * @Description TODO(头条推广获取access_token)   
	 * @author pengl
	 * @date 2018年8月15日 下午5:08:02
	 */
	public ResponseMsg accessToken(HttpServletRequest request) {
		String tokenId = request.getParameter("tokenId");
		if(!StringUtil.isEmpty(tokenId)) {
			ToutiaoTokenInfo toutiaoTokenInfo = toutiaoTokenInfoMapper.selectByPrimaryKey(Integer.parseInt(tokenId));
			String toutiaoAuthCode = toutiaoTokenInfo.getAuthCode();
			if(StringUtil.isEmpty(toutiaoAuthCode)) {
				return new ResponseMsg(ResponseMsg.ERROR, "auth_code为空");
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("appid", toutiaoTokenInfo.getAppId());
			map.put("secret", toutiaoTokenInfo.getSecret());
			map.put("grant_type", Const.GRANT_TYPE_ACCESS_TOKEN);
			map.put("auth_code", toutiaoAuthCode);
			String accessTokenStr = TouTiaoSendUtil.sendPostToken(Const.ACCESS_TOKEN_POST, map);
			JSONObject accessTokenJson = JSONObject.fromObject(accessTokenStr);
			if(accessTokenJson.get("data") != null) {
				JSONObject dataJson = JSONObject.fromObject(accessTokenJson.get("data"));
				ToutiaoTokenInfo toutiaoToken= new ToutiaoTokenInfo();
				toutiaoToken.setId(Integer.parseInt(tokenId));
				toutiaoToken.setAccessToken(dataJson.get("access_token").toString());
				toutiaoToken.setRefreshToken(dataJson.get("refresh_token").toString());
				toutiaoTokenInfoMapper.updateByPrimaryKeySelective(toutiaoToken);
			}
			return new ResponseMsg(accessTokenJson.getString("code"), accessTokenJson.getString("message"));
		}else {
			return new ResponseMsg(ResponseMsg.ERROR, "tokenId为空");
		}
	}
	
	/**
	 * 
	 * @Title refreshToken   
	 * @Description TODO(头条推广刷新access_token)   
	 * @author pengl
	 * @date 2018年8月15日 下午5:09:29
	 */
	public ResponseMsg refreshToken(String tokenId) {
		Date date = new Date();
		if(!StringUtil.isEmpty(tokenId)) {
			ToutiaoTokenInfo toutiaoTokenInfo = toutiaoTokenInfoMapper.selectByPrimaryKey(Integer.parseInt(tokenId));
			String toutiaoRefreshToken = toutiaoTokenInfo.getRefreshToken();
			if(StringUtil.isEmpty(toutiaoRefreshToken)) {
				return new ResponseMsg(ResponseMsg.ERROR, "refresh_token为空");
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("appid", toutiaoTokenInfo.getAppId());
			map.put("secret", toutiaoTokenInfo.getSecret());
			map.put("grant_type", Const.GRANT_TYPE_REFRESH_TOKEN);
			map.put("refresh_token", toutiaoRefreshToken);
			String refreshTokenStr = TouTiaoSendUtil.sendPostToken(Const.REFRESH_TOKEN_POST, map);
			JSONObject refreshTokenJson = JSONObject.fromObject(refreshTokenStr);
			if(refreshTokenJson.get("data") != null) {
				JSONObject dataJson = JSONObject.fromObject(refreshTokenJson.get("data"));
				ToutiaoTokenInfo toutiaoToken = new ToutiaoTokenInfo();
				toutiaoToken.setId(toutiaoTokenInfo.getId());
				toutiaoToken.setRefreshToken(dataJson.get("refresh_token").toString());
				toutiaoToken.setAccessToken(dataJson.get("access_token").toString());
				toutiaoToken.setUpdateDate(date);
				toutiaoTokenInfoMapper.updateByPrimaryKeySelective(toutiaoToken);
			}
			return new ResponseMsg(refreshTokenJson.getString("code"), refreshTokenJson.getString("message").toString());
		}else {
			ToutiaoTokenInfoExample toutiaoTokenInfoExample = new ToutiaoTokenInfoExample();
			toutiaoTokenInfoExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andRefreshTokenIsNotNull();
			List<ToutiaoTokenInfo> toutiaoTokenInfoList = toutiaoTokenInfoMapper.selectByExample(toutiaoTokenInfoExample);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
			for(ToutiaoTokenInfo toutiaoTokenInfo : toutiaoTokenInfoList) {
				String updateDateStr = sdf.format(date)+":00:00";
				if(toutiaoTokenInfo.getUpdateDate() == null || toutiaoTokenInfo.getUpdateDate().compareTo(DateUtil.getDate(updateDateStr)) == -1 ) {
					String toutiaoRefreshToken = toutiaoTokenInfo.getRefreshToken();
					Map<String, String> map = new HashMap<String, String>();
					map.put("appid", toutiaoTokenInfo.getAppId());
					map.put("secret", toutiaoTokenInfo.getSecret());
					map.put("grant_type", Const.GRANT_TYPE_REFRESH_TOKEN);
					map.put("refresh_token", toutiaoRefreshToken);
					String refreshTokenStr = TouTiaoSendUtil.sendPostToken(Const.REFRESH_TOKEN_POST, map);
					JSONObject refreshTokenJson = JSONObject.fromObject(refreshTokenStr);
					if(refreshTokenJson.get("data") != null) {
						JSONObject dataJson = JSONObject.fromObject(refreshTokenJson.get("data"));
						ToutiaoTokenInfo toutiaoToken = new ToutiaoTokenInfo();
						toutiaoToken.setId(toutiaoTokenInfo.getId());
						toutiaoToken.setRefreshToken(dataJson.get("refresh_token").toString());
						toutiaoToken.setAccessToken(dataJson.get("access_token").toString());
						toutiaoToken.setUpdateDate(date);
						toutiaoTokenInfoMapper.updateByPrimaryKeySelective(toutiaoToken);
					}
				}
			}
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		}
	}
	
	/**
	 * 
	 * @Title advertiserInfoList   
	 * @Description TODO(头条推广广告主信息)   
	 * @author pengl
	 * @date 2018年8月15日 下午6:45:36
	 *//*
	public ResponseMsg advertiserInfoList() {
		List<ParamCfg> paramCfgList = getParamCfgList("TOUTIAO_ACCESS_TOKEN");
		if(paramCfgList != null && paramCfgList.size() > 0) {
			String toutiaoAccessToken = paramCfgList.get(0).getParamValue();
			if(!StringUtil.isEmpty(toutiaoAccessToken)) {
				ToutiaoAdvertiserInfoExample toutiaoAdvertiserInfoExample = new ToutiaoAdvertiserInfoExample();
				toutiaoAdvertiserInfoExample.createCriteria().andDelFlagEqualTo("0");
				List<ToutiaoAdvertiserInfo> toutiaoAdvertiserInfoList = toutiaoAdvertiserInfoMapper.selectByExample(toutiaoAdvertiserInfoExample);
				int toutiaoAdvertiserInfoListSize = toutiaoAdvertiserInfoList.size();
				if(toutiaoAdvertiserInfoList != null && toutiaoAdvertiserInfoListSize > 0) {
					Map<String, Object> resultMap = null;
					if(toutiaoAdvertiserInfoListSize > 100) {
						int fromIndex = 0;
						int toIndex = 100;
						for (int i = 0; i < toutiaoAdvertiserInfoListSize; i++) {
							if(toIndex == toutiaoAdvertiserInfoListSize) {
								break;
							}
							toIndex = (i+1)*100<toutiaoAdvertiserInfoListSize?(i+1)*100:toutiaoAdvertiserInfoListSize;
							List<ToutiaoAdvertiserInfo> toutiaoAdvertiserInfos = toutiaoAdvertiserInfoList.subList(fromIndex, toIndex);
							for (int j = 0; j < Const.SEND_ERROR_NUM; j++) {
								resultMap = advertiserInfo(toutiaoAccessToken, toutiaoAdvertiserInfos);
								if("0".equals(resultMap.get("code"))) {
									break;
								}
							}
							System.out.println("头条推广广告主信息"+fromIndex+"~"+toIndex+"======>"+resultMap.get("code")+"------"+resultMap.get("message"));
							fromIndex = toIndex + 1;
						}
					}else {
						for (int i = 0; i < Const.SEND_ERROR_NUM; i++) {
							resultMap = advertiserInfo(toutiaoAccessToken, toutiaoAdvertiserInfoList);
							if("0".equals(resultMap.get("code"))) {
								break;
							}
						}
						System.out.println("头条推广广告主信息0~"+toutiaoAdvertiserInfoListSize+"======>"+resultMap.get("code")+"------"+resultMap.get("message"));
					}
				}
				return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
			}
		}
		return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> advertiserInfo(String toutiaoAccessToken, List<ToutiaoAdvertiserInfo> toutiaoAdvertiserInfoList) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> advertiserIdList = new ArrayList<String>(); 
		for(ToutiaoAdvertiserInfo toutiaoAdvertiserInfo : toutiaoAdvertiserInfoList) {
			advertiserIdList.add(toutiaoAdvertiserInfo.getAdvertiserId());
		}
		String[] fields = {"id", "name", "description", "email", "contacter","phonenumber", "role", "status", 
				"telephone", "address", "reason","license_url", "license_no", "license_province", "license_city",
				"company", "brand", "promotion_area", "promotion_center_province","promotion_center_city", 
				"industry", "balance"};
		map.put("advertiser_ids", advertiserIdList);
		map.put("fields", fields);
		String adGetStr = TouTiaoSendUtil.sendGet(Const.ADVERTISER_INFO, map, toutiaoAccessToken);
		JSONObject adGetJson = JSONObject.fromObject(adGetStr);
		if("0".equals(adGetJson.getString("code"))) {
			List<JSONObject> dataList =  adGetJson.getJSONArray("data");
			List<ToutiaoAdvertiserInfo> toutiaoAdvertiserInfos = new ArrayList<ToutiaoAdvertiserInfo>();
			for(JSONObject dataJson : dataList) {
				ToutiaoAdvertiserInfo toutiaoAdvertiserInfo = new ToutiaoAdvertiserInfo();
				toutiaoAdvertiserInfo.setAdvertiserId(dataJson.containsKey("id")?dataJson.getString("id"):null);
				toutiaoAdvertiserInfo.setName(dataJson.containsKey("name")?dataJson.getString("name"):null);
				toutiaoAdvertiserInfo.setDescription(dataJson.containsKey("description")?dataJson.getString("description"):null);
				toutiaoAdvertiserInfo.setEmail(dataJson.containsKey("email")?dataJson.getString("email"):null);
				toutiaoAdvertiserInfo.setContacter(dataJson.containsKey("contacter")?dataJson.getString("contacter"):null);
				toutiaoAdvertiserInfo.setPhonenumber(dataJson.containsKey("phonenumber")?dataJson.getString("phonenumber"):null);
				toutiaoAdvertiserInfo.setRole(dataJson.containsKey("role")?dataJson.getString("role"):null);
				toutiaoAdvertiserInfo.setStatus(dataJson.containsKey("status")?dataJson.getString("status"):null);
				toutiaoAdvertiserInfo.setTelephone(dataJson.containsKey("telephone")?dataJson.getString("telephone"):null);
				toutiaoAdvertiserInfo.setAddress(dataJson.containsKey("address")?dataJson.getString("address"):null);
				toutiaoAdvertiserInfo.setReason(dataJson.containsKey("reason")?dataJson.getString("reason"):null);
				toutiaoAdvertiserInfo.setLicenseUrl(dataJson.containsKey("license_url")?dataJson.getString("license_url"):null);
				toutiaoAdvertiserInfo.setLicenseNo(dataJson.containsKey("license_no")?dataJson.getString("license_no"):null);
				toutiaoAdvertiserInfo.setLicenseProvince(dataJson.containsKey("license_province")?dataJson.getString("license_province"):null);
				toutiaoAdvertiserInfo.setLicenseCity(dataJson.containsKey("license_city")?dataJson.getString("license_city"):null);
				toutiaoAdvertiserInfo.setCompany(dataJson.containsKey("company")?dataJson.getString("company"):null);
				toutiaoAdvertiserInfo.setBrand(dataJson.containsKey("brand")?dataJson.getString("brand"):null);
				toutiaoAdvertiserInfo.setPromotionArea(dataJson.containsKey("promotion_area")?dataJson.getString("promotion_area"):null);
				toutiaoAdvertiserInfo.setPromotionCenterProvince(dataJson.containsKey("promotion_center_province")?dataJson.getString("promotion_center_province"):null);
				toutiaoAdvertiserInfo.setPromotionCenterCity(dataJson.containsKey("promotion_center_city")?dataJson.getString("promotion_center_city"):null);
				toutiaoAdvertiserInfo.setIndustry(dataJson.containsKey("industry")?dataJson.getString("industry"):null);
				toutiaoAdvertiserInfo.setBalance(dataJson.containsKey("balance")?new BigDecimal(dataJson.getString("balance")):null);
				toutiaoAdvertiserInfos.add(toutiaoAdvertiserInfo);
			}
			toutiaoAdvertiserInfoCustomMapper.updateByPrimaryKeySelectiveList(toutiaoAdvertiserInfos);
		}
		resultMap.put("code", adGetJson.getString("code"));
		resultMap.put("message", adGetJson.getString("message"));
		return resultMap;
	}
	
	*//**
	 * 
	 * @Title campaignGetList   
	 * @Description TODO(获取广告组（新）)   
	 * @author pengl
	 * @date 2018年8月15日 下午8:07:57
	 *//*
	public ResponseMsg campaignGetList() {
		List<ParamCfg> paramCfgList = getParamCfgList("TOUTIAO_ACCESS_TOKEN");
		if(paramCfgList != null && paramCfgList.size() > 0) {
			String toutiaoAccessToken = paramCfgList.get(0).getParamValue();
			if(!StringUtil.isEmpty(toutiaoAccessToken)) {
				ToutiaoAdvertiserInfoExample toutiaoAdvertiserInfoExample = new ToutiaoAdvertiserInfoExample();
				toutiaoAdvertiserInfoExample.createCriteria().andDelFlagEqualTo("0");
				List<ToutiaoAdvertiserInfo> toutiaoAdvertiserInfoList = toutiaoAdvertiserInfoMapper.selectByExample(toutiaoAdvertiserInfoExample);
				for(ToutiaoAdvertiserInfo toutiaoAdvertiserInfo : toutiaoAdvertiserInfoList) {
					Map<String, Object> resultMap = null;
					String advertiserId = toutiaoAdvertiserInfo.getAdvertiserId();
					int campaignPage = toutiaoAdvertiserInfo.getCampaignPage();
					int campaignPageSize = toutiaoAdvertiserInfo.getCampaignPageSize();
					int campaignTotalPage = toutiaoAdvertiserInfo.getCampaignTotalPage();
					// 第一次访问（获取页码信息）
					for (int i = 0; i < Const.SEND_ERROR_NUM; i++) {
						resultMap = campaignGet(toutiaoAccessToken, advertiserId, 1, campaignPageSize, campaignTotalPage);
						if("0".equals(resultMap.get("code")) ) {
							break;
						}
					}
					campaignPage = (int)resultMap.get("totalPage") - campaignTotalPage;
					// 第二次及多次访问（遍历循环）
					for (int j = 0; j < campaignPage; j++) {
						for (int i = 0; i < Const.SEND_ERROR_NUM; i++) {
							resultMap = campaignGet(toutiaoAccessToken, advertiserId, j+2, campaignPageSize, campaignTotalPage);
							if("0".equals(resultMap.get("code")) ) {
								break;
							}
						}
					}
					System.out.println("获取广告组（新）"+toutiaoAdvertiserInfo.getAdvertiserId()+"======>"
							+resultMap.get("code")+"====="+resultMap.get("message"));
				}
				return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
			}else {
				return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
			}
		}
		return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> campaignGet(String toutiaoAccessToken, String advertiserId, int campaignPage, int campaignPageSize, int campaignTotalPage ) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Date date = new Date();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("advertiser_id", advertiserId);
		map.put("page", campaignPage);
		map.put("page_size", campaignPageSize);
		String[] fields = {"id", "name", "budget", "budget_mode", "landing_type","status",
				"campaign_create_time", "campaign_modify_time"};
		map.put("fields", fields);
		String campaignGetStr = TouTiaoSendUtil.sendGet(Const.CAMPAIGN_GET, map, toutiaoAccessToken);
		JSONObject campaignGetJson = JSONObject.fromObject(campaignGetStr);
		resultMap.put("code", campaignGetJson.getString("code"));
		resultMap.put("message", campaignGetJson.getString("message"));
		if("0".equals(campaignGetJson.getString("code")) ) {
			JSONObject dataJson = campaignGetJson.getJSONObject("data");
			JSONObject pageInfoJson = dataJson.getJSONObject("page_info");
			int page = pageInfoJson.getInt("page");
			int pageSize = pageInfoJson.getInt("page_size");
			int totalNumber = pageInfoJson.getInt("total_number");
			int totalPage = pageInfoJson.getInt("total_page");
			resultMap.put("page", page);
			resultMap.put("pageSize", pageSize);
			resultMap.put("totalNumber", totalNumber);
			resultMap.put("totalPage", totalPage);
			List<JSONObject> listJson = dataJson.containsKey("list")?dataJson.getJSONArray("list"):null;
			if(listJson != null) {
				for(JSONObject campaignJson : listJson) {
					String campaignId = campaignJson.containsKey("id")?campaignJson.getString("id"):null;
					ToutiaoCampaignInfo toutiaoCampaignInfo = new ToutiaoCampaignInfo();
					toutiaoCampaignInfo.setAdvertiserId(advertiserId);
					toutiaoCampaignInfo.setCampaignId(campaignId);
					toutiaoCampaignInfo.setName(campaignJson.containsKey("name")?campaignJson.getString("name"):null);
					toutiaoCampaignInfo.setBudget(campaignJson.containsKey("budget")?new BigDecimal(campaignJson.getString("budget")):null);
					toutiaoCampaignInfo.setBudgetMode(campaignJson.containsKey("budget_mode")?campaignJson.getString("budget_mode"):null);
					toutiaoCampaignInfo.setLandingType(campaignJson.containsKey("landing_type")?campaignJson.getString("landing_type"):null);
					toutiaoCampaignInfo.setModifyTime(campaignJson.containsKey("modify_time")?campaignJson.getString("modify_time"):null);
					toutiaoCampaignInfo.setStatus(campaignJson.containsKey("status")?campaignJson.getString("status"):null);
					toutiaoCampaignInfo.setCampaignCreateTime(campaignJson.containsKey("campaign_create_time")?campaignJson.getString("campaign_create_time"):null);
					toutiaoCampaignInfo.setCampaignModifyTime(campaignJson.containsKey("campaign_modify_time")?campaignJson.getString("campaign_modify_time"):null);
					toutiaoCampaignInfo.setPageInfo(dataJson.getString("page_info"));
					toutiaoCampaignInfo.setCreateDate(date);
					toutiaoCampaignInfo.setDelFlag("0");
					if(campaignPage == 1 || campaignPage == (totalPage - campaignTotalPage) || campaignPage == (totalPage - campaignTotalPage + 1)) {
						ToutiaoCampaignInfoExample toutiaoCampaignInfoExample = new ToutiaoCampaignInfoExample();
						toutiaoCampaignInfoExample.createCriteria().andDelFlagEqualTo("0").andCampaignIdEqualTo(campaignId);
						List<ToutiaoCampaignInfo> toutiaoCampaignInfoList = toutiaoCampaignInfoMapper.selectByExample(toutiaoCampaignInfoExample);
						if(toutiaoCampaignInfoList == null || toutiaoCampaignInfoList.size() == 0) {
							toutiaoCampaignInfoMapper.insertSelective(toutiaoCampaignInfo);
						}
					}else {
						toutiaoCampaignInfoMapper.insertSelective(toutiaoCampaignInfo);
					}
				}
			}
			// 最后一页时修改广告主表中广告组分页信息
			if(campaignPage == 1 || campaignPage == (totalPage - campaignTotalPage + 1) ) {
				ToutiaoAdvertiserInfoExample toutiaoAdvertiserInfoExample = new ToutiaoAdvertiserInfoExample();
				toutiaoAdvertiserInfoExample.createCriteria().andDelFlagEqualTo("0")
					.andAdvertiserIdEqualTo(advertiserId);
				ToutiaoAdvertiserInfo toutiaoAdvertiserInfo = new ToutiaoAdvertiserInfo();
				toutiaoAdvertiserInfo.setCampaignPage(totalPage);
				toutiaoAdvertiserInfo.setCampaignPageSize(pageSize);
				toutiaoAdvertiserInfo.setCampaignTotalNumber(totalNumber);
				toutiaoAdvertiserInfo.setCampaignTotalPage(totalPage);
				toutiaoAdvertiserInfoMapper.updateByExampleSelective(toutiaoAdvertiserInfo, toutiaoAdvertiserInfoExample);
			}
		}
		return resultMap;
	}
	
	*//**
	 * 
	 * @Title adGetList   
	 * @Description TODO(获取广告计划（新）)   
	 * @author pengl
	 * @date 2018年8月16日 下午1:40:10
	 *//*
	public ResponseMsg adGetList() {
		List<ParamCfg> paramCfgList = getParamCfgList("TOUTIAO_ACCESS_TOKEN");
		if(paramCfgList != null && paramCfgList.size() > 0) {
			String toutiaoAccessToken = paramCfgList.get(0).getParamValue();
			if(!StringUtil.isEmpty(toutiaoAccessToken)) {
				ToutiaoAdvertiserInfoExample toutiaoAdvertiserInfoExample = new ToutiaoAdvertiserInfoExample();
				toutiaoAdvertiserInfoExample.createCriteria().andDelFlagEqualTo("0");
				List<ToutiaoAdvertiserInfo> toutiaoAdvertiserInfoList = toutiaoAdvertiserInfoMapper.selectByExample(toutiaoAdvertiserInfoExample);
				for(ToutiaoAdvertiserInfo toutiaoAdvertiserInfo : toutiaoAdvertiserInfoList) {
					Map<String, Object> resultMap = null;
					String advertiserId = toutiaoAdvertiserInfo.getAdvertiserId();
					int adPage = toutiaoAdvertiserInfo.getAdPage();
					int adPageSize = toutiaoAdvertiserInfo.getAdPageSize();
					int adTotalPage = toutiaoAdvertiserInfo.getAdTotalPage();
					// 第一次访问（获取页码信息）
					for (int i = 0; i < Const.SEND_ERROR_NUM; i++) {
						resultMap = adGet(toutiaoAccessToken, advertiserId, 1, adPageSize, adTotalPage);
						if("0".equals(resultMap.get("code")) ) {
							break;
						}
					}
					adPage = (int)resultMap.get("totalPage") - adTotalPage;
					// 第二次及多次访问（遍历循环）
					for (int j = 0; j < adPage; j++) {
						for (int i = 0; i < Const.SEND_ERROR_NUM; i++) {
							resultMap = adGet(toutiaoAccessToken, advertiserId, j+2, adPageSize, adTotalPage);
							if("0".equals(resultMap.get("code")) ) {
								break;
							}
						}
					}
					System.out.println("获取广告计划（新）"+toutiaoAdvertiserInfo.getAdvertiserId()+"======>"
							+resultMap.get("code")+"====="+resultMap.get("message"));
				}
				return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
			}else {
				return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
			}
		}
		return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> adGet(String toutiaoAccessToken, String advertiserId, int adPage, int adPageSize, int adTotalPage ) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Date date = new Date();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("advertiser_id", advertiserId);
		map.put("page", adPage);
		map.put("page_size", adPageSize);
		String[] fields = {"status", "open_url", "ad_create_time", "ad_modify_time", "adx_channel", 
				"audit_reject_reason","campaign_id", "cpa_phrase", "pricing", "cpa_bid", 
				"app_type","flow_control_mode", "inventory_type", "download_url", "id",
				"adx_index", "start_time", "opt_status", "modify_time","cpa_skip_first_phrase", 
				"budget_mode", "convert_id", "bid","advertiser_id", "app_behavior_target", 
				"hide_if_converted","name", "package", "schedule_time","budget", 
				"schedule_type", "audience", "end_time", "external_url"};
		map.put("fields", fields);
		String adGetStr = TouTiaoSendUtil.sendGet(Const.AD_GET, map, toutiaoAccessToken);
		JSONObject adGetJson = JSONObject.fromObject(adGetStr);
		System.out.println("adGetJson======>"+adGetJson);
		resultMap.put("code", adGetJson.getString("code"));
		resultMap.put("message", adGetJson.getString("message"));
		if("0".equals(adGetJson.getString("code")) ) {
			JSONObject dataJson = adGetJson.getJSONObject("data");
			JSONObject pageInfoJson = dataJson.getJSONObject("page_info");
			int page = pageInfoJson.getInt("page");
			int pageSize = pageInfoJson.getInt("page_size");
			int totalNumber = pageInfoJson.getInt("total_number");
			int totalPage = pageInfoJson.getInt("total_page");
			resultMap.put("page", page);
			resultMap.put("pageSize", pageSize);
			resultMap.put("totalNumber", totalNumber);
			resultMap.put("totalPage", totalPage);
			List<JSONObject> listJson = dataJson.containsKey("list")?dataJson.getJSONArray("list"):null;
			if(listJson != null) {
				for(JSONObject adJson : listJson) {
					String adId = adJson.containsKey("id")?adJson.getString("id"):null;
					ToutiaoAdInfo toutiaoAdInfo = new ToutiaoAdInfo();
					toutiaoAdInfo.setAdId(adId);
					toutiaoAdInfo.setAdvertiserId(adJson.containsKey("advertiser_id")?adJson.getString("advertiser_id"):null);
					toutiaoAdInfo.setCampaignId(adJson.containsKey("campaign_id")?adJson.getString("campaign_id"):null);
					toutiaoAdInfo.setName(adJson.containsKey("name")?adJson.getString("name"):null);
					toutiaoAdInfo.setModifyTime(adJson.containsKey("modify_time")?adJson.getString("modify_time"):null);
					toutiaoAdInfo.setAdModifyTime(adJson.containsKey("ad_modify_time")?adJson.getString("ad_modify_time"):null);
					toutiaoAdInfo.setAdCreateTime(adJson.containsKey("ad_create_time")?adJson.getString("ad_create_time"):null);
					toutiaoAdInfo.setBudgetMode(adJson.containsKey("budget_mode")?adJson.getString("budget_mode"):null);
					toutiaoAdInfo.setBudget(adJson.containsKey("budget")?new BigDecimal(adJson.getString("budget")):null);
					toutiaoAdInfo.setStatus(adJson.containsKey("status")?adJson.getString("status"):null);
					toutiaoAdInfo.setOptStatus(adJson.containsKey("opt_status")?adJson.getString("opt_status"):null);
					toutiaoAdInfo.setStartTime(adJson.containsKey("start_time")?adJson.getString("start_time"):null);
					toutiaoAdInfo.setEndTime(adJson.containsKey("end_time")?adJson.getString("end_time"):null);
					toutiaoAdInfo.setBid(adJson.containsKey("bid")?new BigDecimal(adJson.getString("bid")):null);
					toutiaoAdInfo.setPricing(adJson.containsKey("pricing")?adJson.getString("pricing"):null);
					toutiaoAdInfo.setScheduleType(adJson.containsKey("schedule_type")?adJson.getString("schedule_type"):null);
					toutiaoAdInfo.setScheduleTime(adJson.containsKey("schedule_time")?adJson.getString("schedule_time"):null);
					toutiaoAdInfo.setFlowControlMode(adJson.containsKey("flow_control_mode")?adJson.getString("flow_control_mode"):null);
					toutiaoAdInfo.setOpenUrl(adJson.containsKey("open_url")?adJson.getString("open_url"):null);
					toutiaoAdInfo.setExternalUrl(adJson.containsKey("external_url")?adJson.getString("external_url"):null);
					toutiaoAdInfo.setDownloadUrl(adJson.containsKey("download_url")?adJson.getString("download_url"):null);
					toutiaoAdInfo.setAppType(adJson.containsKey("app_type")?adJson.getString("app_type"):null);
					toutiaoAdInfo.setAdPackage(adJson.containsKey("package")?adJson.getString("package"):null);
					toutiaoAdInfo.setAuditRejectReason(adJson.containsKey("audit_reject_reason")?adJson.getString("audit_reject_reason"):null);
					toutiaoAdInfo.setCpaBid(adJson.containsKey("cpa_bid")?new BigDecimal(adJson.getString("cpa_bid")):null);
					toutiaoAdInfo.setCpaSkipFirstPhrase(adJson.containsKey("cpa_skip_first_phrase")?adJson.getInt("cpa_skip_first_phrase"):null);
					toutiaoAdInfo.setConvertId(adJson.containsKey("convert_id")?adJson.getString("convert_id"):null);
					toutiaoAdInfo.setHideIfConverted(adJson.containsKey("hide_if_converted")?adJson.getString("hide_if_converted"):null);
					toutiaoAdInfo.setAudience(adJson.containsKey("audience")?adJson.getString("audience"):null);
					// 创意详情信息（json字符串，注意：新创建时，修改广告主ID与计划ID）
					if(adId != null){
						Map<String, Object> creativeReadV2Map = null;
						for (int i = 0; i < Const.SEND_ERROR_NUM; i++) {
							creativeReadV2Map = creativeReadV2(toutiaoAccessToken, advertiserId, adId);
							if("0".equals(creativeReadV2Map.get("code")) ) {
								break;
							}
						}
						toutiaoAdInfo.setCreativeDtl(creativeReadV2Map.get("data")!=null?creativeReadV2Map.get("data").toString():null);
					}
					toutiaoAdInfo.setPageInfo(adJson.containsKey("page_info")?adJson.getString("page_info"):null);
					toutiaoAdInfo.setCreateDate(date);
					toutiaoAdInfo.setDelFlag("0");
					if(adPage == 1 && adId != null || adPage == (totalPage - adTotalPage) && adId != null || adPage == (totalPage - adTotalPage + 1) && adId != null ) {
						ToutiaoAdInfoExample toutiaoAdInfoExample = new ToutiaoAdInfoExample();
						toutiaoAdInfoExample.createCriteria().andDelFlagEqualTo("0").andAdIdEqualTo(adId);
						List<ToutiaoAdInfo> toutiaoAdInfoList = toutiaoAdInfoMapper.selectByExample(toutiaoAdInfoExample);
						if(toutiaoAdInfoList.size() == 0) {
							toutiaoAdInfoMapper.insertSelective(toutiaoAdInfo);
						}
					}else {
						toutiaoAdInfoMapper.insertSelective(toutiaoAdInfo);
					}
				}
			}
			// 最后一页时修改广告主表中广告计划分页信息
			if(adPage == 1 || adPage == (totalPage - adTotalPage + 1) ) {
				ToutiaoAdvertiserInfoExample toutiaoAdvertiserInfoExample = new ToutiaoAdvertiserInfoExample();
				toutiaoAdvertiserInfoExample.createCriteria().andDelFlagEqualTo("0")
					.andAdvertiserIdEqualTo(advertiserId);
				ToutiaoAdvertiserInfo toutiaoAdvertiserInfo = new ToutiaoAdvertiserInfo();
				toutiaoAdvertiserInfo.setAdPage(totalPage);
				toutiaoAdvertiserInfo.setAdPageSize(pageSize);
				toutiaoAdvertiserInfo.setAdTotalNumber(totalNumber);
				toutiaoAdvertiserInfo.setAdTotalPage(totalPage);
				toutiaoAdvertiserInfoMapper.updateByExampleSelective(toutiaoAdvertiserInfo, toutiaoAdvertiserInfoExample);
			}
		}
		return resultMap;
	}
	
	*//**
	 * 
	 * @Title creativeReadV2   
	 * @Description TODO(创意详细信息（新版）)   
	 * @author pengl
	 * @date 2018年8月16日 下午3:24:24
	 *//*
	public Map<String, Object> creativeReadV2(String toutiaoAccessToken, String advertiserId, String adId ) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("advertiser_id", advertiserId);
		map.put("ad_id", adId);
		String creativeSelectStr = TouTiaoSendUtil.sendGet(Const.CREATIVE_READ_V2, map, toutiaoAccessToken);
		JSONObject creativeSelectJson = JSONObject.fromObject(creativeSelectStr);
		resultMap.put("code", creativeSelectJson.getString("code"));
		resultMap.put("message", creativeSelectJson.getString("message"));
		if("0".equals(creativeSelectJson.getString("code")) ) {
			resultMap.put("data", creativeSelectJson.getString("data"));
		}
		return resultMap;
	}*/
	
	
	
}
