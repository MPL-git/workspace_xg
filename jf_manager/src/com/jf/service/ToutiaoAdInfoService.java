package com.jf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzs.common.utils.StringUtil;
import com.jf.common.constant.Const;
import com.jf.common.utils.TouTiaoSendUtil;
import com.jf.dao.SysParamCfgMapper;
import com.jf.dao.ToutiaoAdInfoCustomMapper;
import com.jf.dao.ToutiaoAdInfoMapper;
import com.jf.dao.ToutiaoAdvertiserInfoMapper;
import com.jf.dao.ToutiaoTokenAdvertiserInfoCustomMapper;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import com.jf.entity.ToutiaoAdInfo;
import com.jf.entity.ToutiaoAdInfoCustom;
import com.jf.entity.ToutiaoAdInfoCustomExample;
import com.jf.entity.ToutiaoAdInfoExample;
import com.jf.entity.ToutiaoAdInfoWithBLOBs;
import com.jf.entity.ToutiaoAdvertiserInfo;
import com.jf.entity.ToutiaoAdvertiserInfoExample;
import com.jf.entity.ToutiaoTokenAdvertiserInfoCustom;

@Service
@Transactional
public class ToutiaoAdInfoService extends BaseService<ToutiaoAdInfo, ToutiaoAdInfoExample> {

	@Autowired
	private ToutiaoAdInfoMapper toutiaoAdInfoMapper;
	
	@Autowired
	private ToutiaoAdInfoCustomMapper toutiaoAdInfoCustomMapper;
	
	@Autowired
	private ToutiaoAdvertiserInfoMapper toutiaoAdvertiserInfoMapper;
	
	@Autowired
	private SysParamCfgMapper sysParamCfgMapper;
	
	@Autowired
	private ToutiaoTokenAdvertiserInfoCustomMapper toutiaoTokenAdvertiserInfoCustomMapper;
	
	@Autowired
	public void setToutiaoAdInfoMapper(ToutiaoAdInfoMapper toutiaoAdInfoMapper) {
		super.setDao(toutiaoAdInfoMapper);
		this.toutiaoAdInfoMapper = toutiaoAdInfoMapper;
	}
	
	public int countByCustomExample(ToutiaoAdInfoCustomExample example) {
		return toutiaoAdInfoCustomMapper.countByCustomExample(example);
	}

	public List<ToutiaoAdInfoCustom> selectByCustomExample(ToutiaoAdInfoCustomExample example) {
		return toutiaoAdInfoCustomMapper.selectByCustomExample(example);
	}

	public ToutiaoAdInfoCustom selectByCustomPrimaryKey(Integer id) {
		return toutiaoAdInfoCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") ToutiaoAdInfo record, @Param("example") ToutiaoAdInfoCustomExample example) {
		return toutiaoAdInfoCustomMapper.updateByCustomExampleSelective(record, example);
	}
	
	/**
	 * 
	 * @Title getParamCfgList   
	 * @Description TODO(获取系统配置信息)   
	 * @author pengl
	 * @date 2018年8月16日 上午9:22:58
	 */
	public List<SysParamCfg> getParamCfgList(String paramCode) {
		SysParamCfgExample toutiaoAuthCodeExample = new SysParamCfgExample();
		toutiaoAuthCodeExample.createCriteria().andParamCodeEqualTo(paramCode);
		return sysParamCfgMapper.selectByExample(toutiaoAuthCodeExample);
	}
	
	/**
	 * 
	 * @Title sendPsotToutiaoCreateAd   
	 * @Description TODO(发送创建头条推广广告计划信息)   
	 * @author pengl
	 * @date 2018年8月17日 下午6:38:03
	 */
	@SuppressWarnings("rawtypes")
	public String sendPsotToutiaoCreateAd(HttpServletRequest request) {
		if(!StringUtil.isEmpty(request.getParameter("adInfoId")) ) {
			ToutiaoAdInfoWithBLOBs toutiaoAdInfoWithBLOBs = toutiaoAdInfoMapper.selectByPrimaryKey(Integer.parseInt(request.getParameter("adInfoId")));
			ToutiaoTokenAdvertiserInfoCustom toutiaoTokenAdvertiserInfoCustom = toutiaoTokenAdvertiserInfoCustomMapper.selectToutiaoTokenAdvertiserInfo(toutiaoAdInfoWithBLOBs.getAdvertiserId());
			String toutiaoAccessToken = toutiaoTokenAdvertiserInfoCustom.getAccessToken();
			if(!StringUtil.isEmpty(toutiaoAccessToken)) {
				String adName = request.getParameter("adName");
				Integer beginNum = Integer.parseInt(request.getParameter("beginNum"));
				Integer copyNum = Integer.parseInt(request.getParameter("copyNum"));
				int successNum = 0;
				int errorNum = 0;
				String returnCode = "0";
				try {
					// 广告计划详情
					Map<String, Object> adMap = new HashMap<String, Object>();
					if(!"null".equals(toutiaoAdInfoWithBLOBs.getAdvertiserId()) ) {
						adMap.put("advertiser_id", toutiaoAdInfoWithBLOBs.getAdvertiserId());
					}
					if(!"null".equals(toutiaoAdInfoWithBLOBs.getCampaignId()) ) {
						adMap.put("campaign_id", toutiaoAdInfoWithBLOBs.getCampaignId());
					}
					if(!"null".equals(toutiaoAdInfoWithBLOBs.getDeliveryRange()) && !StringUtil.isEmpty(toutiaoAdInfoWithBLOBs.getDeliveryRange()) ) {
						adMap.put("delivery_range", toutiaoAdInfoWithBLOBs.getDeliveryRange());
					}
					if(!"null".equals(toutiaoAdInfoWithBLOBs.getBudgetMode()) ) {
						adMap.put("budget_mode", toutiaoAdInfoWithBLOBs.getBudgetMode());
					}
					if(toutiaoAdInfoWithBLOBs.getBudget() != null ) {
						adMap.put("budget", toutiaoAdInfoWithBLOBs.getBudget());
					}
					if(!"null".equals(toutiaoAdInfoWithBLOBs.getStartTime()) ) {
						adMap.put("start_time", toutiaoAdInfoWithBLOBs.getStartTime());
					}
					if(!"null".equals(toutiaoAdInfoWithBLOBs.getEndTime()) ) {
						adMap.put("end_time", toutiaoAdInfoWithBLOBs.getEndTime());
					}
					if(toutiaoAdInfoWithBLOBs.getBid() != null ) {
						adMap.put("bid", toutiaoAdInfoWithBLOBs.getBid());
					}
					if(!"null".equals(toutiaoAdInfoWithBLOBs.getPricing()) ) {
						adMap.put("pricing", toutiaoAdInfoWithBLOBs.getPricing());
					}
					if(!"null".equals(toutiaoAdInfoWithBLOBs.getScheduleType()) ) {
						adMap.put("schedule_type", toutiaoAdInfoWithBLOBs.getScheduleType());
					}
					if(!"null".equals(toutiaoAdInfoWithBLOBs.getScheduleTime()) ) {
						adMap.put("schedule_time", toutiaoAdInfoWithBLOBs.getScheduleTime());
					}
					if(!"null".equals(toutiaoAdInfoWithBLOBs.getFlowControlMode()) ) {
						adMap.put("flow_control_mode", toutiaoAdInfoWithBLOBs.getFlowControlMode());
					}
					if(!"null".equals(toutiaoAdInfoWithBLOBs.getOpenUrl()) && !StringUtil.isEmpty(toutiaoAdInfoWithBLOBs.getOpenUrl()) ) {
						adMap.put("open_url", toutiaoAdInfoWithBLOBs.getOpenUrl());
					}
					if(!"null".equals(toutiaoAdInfoWithBLOBs.getDownloadType()) && !StringUtil.isEmpty(toutiaoAdInfoWithBLOBs.getDownloadType()) ) {
						adMap.put("download_type", toutiaoAdInfoWithBLOBs.getDownloadType());
					}
					if(!"null".equals(toutiaoAdInfoWithBLOBs.getExternalUrl()) ) {
						adMap.put("external_url", toutiaoAdInfoWithBLOBs.getExternalUrl());
					}
					if(!"null".equals(toutiaoAdInfoWithBLOBs.getDownloadUrl()) ) {
						adMap.put("download_url", toutiaoAdInfoWithBLOBs.getDownloadUrl());
					}
					if(!"null".equals(toutiaoAdInfoWithBLOBs.getAppType()) && !StringUtil.isEmpty(toutiaoAdInfoWithBLOBs.getAppType()) ) {
						adMap.put("app_type", toutiaoAdInfoWithBLOBs.getAppType());
					}
					if(!"null".equals(toutiaoAdInfoWithBLOBs.getAdPackage()) ) {
						adMap.put("package", toutiaoAdInfoWithBLOBs.getAdPackage());
					}
					if(!"null".equals(toutiaoAdInfoWithBLOBs.getHideIfConverted()) && !StringUtil.isEmpty(toutiaoAdInfoWithBLOBs.getHideIfConverted()) ) {
						adMap.put("hide_if_converted", toutiaoAdInfoWithBLOBs.getHideIfConverted());
					}
					if(toutiaoAdInfoWithBLOBs.getHideIfExists() != null ) {
						adMap.put("hide_if_exists", toutiaoAdInfoWithBLOBs.getHideIfExists());
					}
					if(toutiaoAdInfoWithBLOBs.getCpaBid() != null ) {
						adMap.put("cpa_bid", toutiaoAdInfoWithBLOBs.getCpaBid());
					}
					/*if(toutiaoAdInfoWithBLOBs.getCpaSkipFirstPhrase() != null ) {
						adMap.put("cpa_skip_first_phrase", toutiaoAdInfoWithBLOBs.getCpaSkipFirstPhrase());
					}*/
					if(toutiaoAdInfoWithBLOBs.getConvertId() != null ) {
						adMap.put("convert_id", toutiaoAdInfoWithBLOBs.getConvertId());
					}
					if(!"null".equals(toutiaoAdInfoWithBLOBs.getUnionVideoType()) && !StringUtil.isEmpty(toutiaoAdInfoWithBLOBs.getUnionVideoType()) ) {
						adMap.put("union_video_type", toutiaoAdInfoWithBLOBs.getUnionVideoType());
					}
					if(!"null".equals(toutiaoAdInfoWithBLOBs.getDeepBidType()) && !StringUtil.isEmpty(toutiaoAdInfoWithBLOBs.getDeepBidType()) ) {
						adMap.put("deep_bid_type", toutiaoAdInfoWithBLOBs.getDeepBidType());
					}
					if(toutiaoAdInfoWithBLOBs.getDeepCpabid() != null ) {
						adMap.put("deep_cpabid", toutiaoAdInfoWithBLOBs.getDeepCpabid());
					}
					if(!"null".equals(toutiaoAdInfoWithBLOBs.getUniqueFk()) && !StringUtil.isEmpty(toutiaoAdInfoWithBLOBs.getUniqueFk()) ) {
						adMap.put("unique_fk", toutiaoAdInfoWithBLOBs.getUniqueFk());
					}
					
					if(!StringUtil.isEmpty(toutiaoAdInfoWithBLOBs.getAudience()) ) {
						JSONObject audienceJson = JSONObject.fromObject(toutiaoAdInfoWithBLOBs.getAudience());
						Iterator iterator = audienceJson.keys();
						String key = "";
						while(iterator.hasNext()){
							key = iterator.next().toString();
							if(!"launch_price".equals(key) && !audienceJson.get(key).equals("null")) {
								if("auto_extend_targets".equals(key) ) {
									JSONArray aetJson = JSONArray.fromObject(audienceJson.get(key));
									List<String> aetList = new ArrayList<String>();
									for(Object aet : aetJson ) {
										if(!aet.toString().equals("null") ) {
											aetList.add(aet.toString());
										}
									}
									adMap.put(key, aetList);
								}else {
									adMap.put(key, audienceJson.get(key));
								}
							}
						}
					}
					
					// 广告创意详情
					Map<String, Object> creativeDtlMap = new HashMap<String, Object>();
					if(!StringUtil.isEmpty(toutiaoAdInfoWithBLOBs.getCreativeDtl()) ) {
						JSONObject creativeDtlJson = JSONObject.fromObject(toutiaoAdInfoWithBLOBs.getCreativeDtl());
						Iterator iterator = creativeDtlJson.keys();
						String key = "";
						while(iterator.hasNext()){
							key = iterator.next().toString();
							if(!creativeDtlJson.get(key).equals("null")) {
								creativeDtlMap.put(key, creativeDtlJson.get(key));
							}
						}
					}
					Random random = new Random();
					for (int i = beginNum; i < (beginNum+copyNum); i++) {
						//睡眠时间20s~50s的随机数
						if(i != beginNum) {
							Thread.sleep((random.nextInt(50-20+1) + 20) * 1000);
						}
						returnCode = adCreate(toutiaoAccessToken, adName+i, adMap, creativeDtlMap);
						if("0".equals(returnCode)) {
							++successNum;
						}else {
							++errorNum;
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "成功发送"+successNum+"条，失败"+(copyNum-successNum)+"条。";
				}
				return "成功发送"+successNum+"条，失败"+errorNum+"条。";
			}
			return "头条token不存在！";
		}
		return "计划信息ID为空！";
	}

	public String adCreate(String toutiaoAccessToken, String adName, Map<String, Object> adMap, Map<String, Object> creativeDtlMap) {
		adMap.put("name", adName);
		String adCreateStr = TouTiaoSendUtil.sendPost(Const.AD_CREATE, adMap, toutiaoAccessToken);
		JSONObject adCreateJson = JSONObject.fromObject(adCreateStr);
		//System.out.println("adCreateJson===================>"+adCreateJson);
		Random random = new Random();
		try {
			if("0".equals(adCreateJson.getString("code"))) {
				JSONObject dataJson = adCreateJson.getJSONObject("data");
				String adId = dataJson.getString("ad_id");
				if(creativeDtlMap.size() > 0 ) {
					creativeDtlMap.put("ad_id", adId);
					//睡眠5s~15s的随机数，防止头条接口那边的延迟问题
					Thread.sleep((random.nextInt(15-5+1) + 5) * 1000);
					//广告计划创建成功后，创建广告创意。广告创意有可能创建失败，如果创建失败连续创建3次。
					for(int i=0;i<Const.SEND_ERROR_NUM;i++) {
						String creativeCreateV2Str = TouTiaoSendUtil.sendPost(Const.CREATIVE_CREATE_V2, creativeDtlMap, toutiaoAccessToken);
						JSONObject creativeCreateV2StrJson = JSONObject.fromObject(creativeCreateV2Str);
						//System.out.println("creativeCreateV2StrJson===================>"+creativeCreateV2StrJson);
						if("0".equals(creativeCreateV2StrJson.getString("code")) ) {
							break;
						}
					}
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adCreateJson.getString("code");
	}
	

	/**
	 * 
	 * @Title adList   
	 * @Description TODO(获取广告计划（新）)   
	 * @author pengl
	 * @date 2018年8月15日 下午8:07:57
	 */
	public Map<String, Object> adList(StaffBean staffBean, String advertiserId, int batchCode, int adPageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ToutiaoTokenAdvertiserInfoCustom toutiaoTokenAdvertiserInfoCustom = toutiaoTokenAdvertiserInfoCustomMapper.selectToutiaoTokenAdvertiserInfo(advertiserId);
		if(toutiaoTokenAdvertiserInfoCustom != null) {
			String toutiaoAccessToken = toutiaoTokenAdvertiserInfoCustom.getAccessToken();
			int staffId = Integer.parseInt(staffBean.getStaffID());
			int adPage = 1;
			// 第一次访问（获取页码信息）
			for (int i = 0; i < Const.SEND_ERROR_NUM; i++) {
				resultMap = adInfo(toutiaoAccessToken, advertiserId, adPage, adPageSize, batchCode, staffId);
				if("0".equals(resultMap.get("code")) ) {
					break;
				}
			}
			adPage = (Integer)resultMap.get("totalPage");
			// 第二次及多次访问（遍历循环）
			for (int j = 2; j <= adPage; j++) {
				for (int i = 0; i < Const.SEND_ERROR_NUM; i++) {
					resultMap = adInfo(toutiaoAccessToken, advertiserId, j, adPageSize, batchCode, staffId);
					if("0".equals(resultMap.get("code")) ) {
						break;
					}
				}
			}
			
			// 逻辑删除批次号不一样的
			ToutiaoAdInfoExample toutiaoAdInfoExample = new ToutiaoAdInfoExample();
			toutiaoAdInfoExample.createCriteria().andDelFlagEqualTo("0")
				.andAdvertiserIdEqualTo(advertiserId)
				.andBatchCodeNotEqualTo(batchCode);
			ToutiaoAdInfoWithBLOBs toutiaoAdInfoWithBLOBs = new ToutiaoAdInfoWithBLOBs();
			toutiaoAdInfoWithBLOBs.setUpdateBy(staffId);
			toutiaoAdInfoWithBLOBs.setUpdateDate(new Date());
			toutiaoAdInfoWithBLOBs.setDelFlag("1");
			toutiaoAdInfoMapper.updateByExampleSelective(toutiaoAdInfoWithBLOBs, toutiaoAdInfoExample);
			
			resultMap.put("code", "200");
			resultMap.put("message", StateCode.JSON_AJAX_SUCCESS.getStateMessage());
		}else {
			resultMap.put("code", StateCode.JSON_AJAX_ERROR.getStateCode());
			resultMap.put("message", "token不存在！");
		}
		return resultMap;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> adInfo(String toutiaoAccessToken, String advertiserId, int adPage, int adPageSize, int batchCode, int staffID ) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Date date = new Date();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("advertiser_id", advertiserId);
		map.put("page", adPage);
		map.put("page_size", adPageSize);
		
		/*Map<String, Object> map2 = new HashMap<String, Object>();
		List<Long> idsList = new ArrayList<Long>();
		idsList.add(new Long("1628515406567438"));
		map2.put("ad_name", "产品测试-穿山甲-ocpm-激励视频");
		map2.put("ids", idsList);
		map.put("filtering", map2);
		String[] fields = {"status", "open_url", "ad_create_time", "ad_modify_time", "adx_channel", 
				"audit_reject_reason", "campaign_id", "cpa_phrase", "pricing", "ad_id", 
				"schedule_type", "cpa_bid", "app_type", "flow_control_mode", "inventory_type", 
				"download_url", "id", "adx_index", "start_time", "opt_status", 
				"modify_time", "cpa_skip_first_phrase", "budget_mode", "convert_id", "bid", 
				"advertiser_id", "app_behavior_target", "hide_if_converted", "name", "package", 
				"schedule_time", "budget", "hide_if_exists", "audience", "end_time", 
				"download_type", "external_url", "delivery_range"};
		map.put("fields", fields);*/
		
		String adGetStr = TouTiaoSendUtil.sendGet(Const.AD_GET, map, toutiaoAccessToken);
		JSONObject adGetJson = JSONObject.fromObject(adGetStr);
		
		//System.out.println("adGetJson======>"+adGetJson);
		
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
					String modifyTime = adJson.containsKey("modify_time")?adJson.getString("modify_time"):null;
					ToutiaoAdInfoWithBLOBs toutiaoAdInfoWithBLOBs = new ToutiaoAdInfoWithBLOBs();
					toutiaoAdInfoWithBLOBs.setAdId(adId);
					toutiaoAdInfoWithBLOBs.setAdvertiserId(adJson.containsKey("advertiser_id")?adJson.getString("advertiser_id"):null);
					toutiaoAdInfoWithBLOBs.setCampaignId(adJson.containsKey("campaign_id")?adJson.getString("campaign_id"):null);
					toutiaoAdInfoWithBLOBs.setName(adJson.containsKey("name")?adJson.getString("name"):null);
					toutiaoAdInfoWithBLOBs.setModifyTime(modifyTime);
					toutiaoAdInfoWithBLOBs.setAdModifyTime(adJson.containsKey("ad_modify_time")?adJson.getString("ad_modify_time"):null);
					toutiaoAdInfoWithBLOBs.setAdCreateTime(adJson.containsKey("ad_create_time")?adJson.getString("ad_create_time"):null);
					toutiaoAdInfoWithBLOBs.setBudgetMode(adJson.containsKey("budget_mode")?adJson.getString("budget_mode"):null);
					toutiaoAdInfoWithBLOBs.setBudget(adJson.containsKey("budget")?adJson.get("budget").equals("null")?null:new BigDecimal(adJson.getString("budget")):null);
					toutiaoAdInfoWithBLOBs.setHideIfExists(adJson.containsKey("hide_if_exists")?adJson.getInt("hide_if_exists"):null);
					toutiaoAdInfoWithBLOBs.setStatus(adJson.containsKey("status")?adJson.getString("status"):null);
					toutiaoAdInfoWithBLOBs.setOptStatus(adJson.containsKey("opt_status")?adJson.getString("opt_status"):null);
					toutiaoAdInfoWithBLOBs.setStartTime(adJson.containsKey("start_time")?adJson.getString("start_time"):null);
					toutiaoAdInfoWithBLOBs.setEndTime(adJson.containsKey("end_time")?adJson.getString("end_time"):null);
					toutiaoAdInfoWithBLOBs.setBid(adJson.containsKey("bid")?adJson.get("bid").equals("null")?null:new BigDecimal(adJson.getString("bid")):null);
					toutiaoAdInfoWithBLOBs.setPricing(adJson.containsKey("pricing")?adJson.getString("pricing"):null);
					toutiaoAdInfoWithBLOBs.setScheduleType(adJson.containsKey("schedule_type")?adJson.getString("schedule_type"):null);
					toutiaoAdInfoWithBLOBs.setScheduleTime(adJson.containsKey("schedule_time")?adJson.getString("schedule_time"):null);
					toutiaoAdInfoWithBLOBs.setFlowControlMode(adJson.containsKey("flow_control_mode")?adJson.getString("flow_control_mode"):null);
					toutiaoAdInfoWithBLOBs.setOpenUrl(adJson.containsKey("open_url")?adJson.getString("open_url"):null);
					toutiaoAdInfoWithBLOBs.setDownloadType(adJson.containsKey("download_type")?adJson.getString("download_type"):null);
					toutiaoAdInfoWithBLOBs.setExternalUrl(adJson.containsKey("external_url")?adJson.getString("external_url"):null);
					toutiaoAdInfoWithBLOBs.setDownloadUrl(adJson.containsKey("download_url")?adJson.getString("download_url"):null);
					toutiaoAdInfoWithBLOBs.setAppType(adJson.containsKey("app_type")?adJson.getString("app_type"):null);
					toutiaoAdInfoWithBLOBs.setAdPackage(adJson.containsKey("package")?adJson.getString("package"):null);
					toutiaoAdInfoWithBLOBs.setAuditRejectReason(adJson.containsKey("audit_reject_reason")?adJson.getString("audit_reject_reason"):null);
					toutiaoAdInfoWithBLOBs.setCpaBid(adJson.containsKey("cpa_bid")?adJson.get("cpa_bid").equals("null")?null:new BigDecimal(adJson.getString("cpa_bid")):null);
					toutiaoAdInfoWithBLOBs.setCpaSkipFirstPhrase(adJson.containsKey("cpa_skip_first_phrase")?adJson.getInt("cpa_skip_first_phrase"):null);
					toutiaoAdInfoWithBLOBs.setConvertId(adJson.containsKey("convert_id")?adJson.getString("convert_id"):null);
					toutiaoAdInfoWithBLOBs.setHideIfConverted(adJson.containsKey("hide_if_converted")?adJson.getString("hide_if_converted"):null);
					toutiaoAdInfoWithBLOBs.setAudience(adJson.containsKey("audience")?adJson.getString("audience"):null);
					toutiaoAdInfoWithBLOBs.setUpdateDate(date);
					toutiaoAdInfoWithBLOBs.setUpdateBy(staffID);
					toutiaoAdInfoWithBLOBs.setDelFlag("0");
					toutiaoAdInfoWithBLOBs.setBatchCode(batchCode); // 批次号
					toutiaoAdInfoWithBLOBs.setDeliveryRange(adJson.containsKey("delivery_range")?adJson.getString("delivery_range"):null);
					toutiaoAdInfoWithBLOBs.setUnionVideoType(adJson.containsKey("union_video_type")?adJson.getString("union_video_type"):null);
					toutiaoAdInfoWithBLOBs.setDeepBidType(adJson.containsKey("deep_bid_type")?adJson.getString("deep_bid_type"):null);
					toutiaoAdInfoWithBLOBs.setDeepCpabid(adJson.containsKey("deep_cpabid")?adJson.get("deep_cpabid").equals("null")?null:new BigDecimal(adJson.getString("deep_cpabid")):null);
					toutiaoAdInfoWithBLOBs.setUniqueFk(adJson.containsKey("unique_fk")?adJson.getString("unique_fk"):null);
					
					// 更新广告计划信息
					ToutiaoAdInfoExample toutiaoAdInfoExample = new ToutiaoAdInfoExample();
					toutiaoAdInfoExample.createCriteria().andDelFlagEqualTo("0")
						.andAdvertiserIdEqualTo(advertiserId)
						.andAdIdEqualTo(adId);
					List<ToutiaoAdInfo> toutiaoAdInfoList = toutiaoAdInfoMapper.selectByExample(toutiaoAdInfoExample);
					if(toutiaoAdInfoList != null && toutiaoAdInfoList.size() > 0) { // 本地数据库中已存在
						if(!modifyTime.equals(toutiaoAdInfoList.get(0).getModifyTime())) { // 上次修改时间戳不相等，更新操作
							// 创意详情信息（json字符串，注意：新创建时，修改广告主ID与计划ID）
							if(adId != null){
								Map<String, Object> creativeReadV2Map = null;
								for (int i = 0; i < Const.SEND_ERROR_NUM; i++) {
									creativeReadV2Map = creativeReadV2(toutiaoAccessToken, advertiserId, adId);
									if("0".equals(creativeReadV2Map.get("code")) ) {
										break;
									}
								}
								toutiaoAdInfoWithBLOBs.setCreativeDtl(creativeReadV2Map.get("data")!=null?creativeReadV2Map.get("data").toString():null);
							}
							ToutiaoAdInfoExample toutiaoAdInfoAExample = new ToutiaoAdInfoExample();
							toutiaoAdInfoAExample.createCriteria().andDelFlagEqualTo("0")
								.andAdvertiserIdEqualTo(advertiserId)
								.andAdIdEqualTo(adId)
								.andModifyTimeNotEqualTo(modifyTime);
							toutiaoAdInfoMapper.updateByExampleSelective(toutiaoAdInfoWithBLOBs, toutiaoAdInfoAExample);
						}else { // 上次修改时间相等，更新批次号操作
							ToutiaoAdInfoExample toutiaoAdInfoBExample = new ToutiaoAdInfoExample();
							toutiaoAdInfoBExample.createCriteria().andDelFlagEqualTo("0")
								.andAdvertiserIdEqualTo(advertiserId)
								.andAdIdEqualTo(adId)
								.andModifyTimeEqualTo(modifyTime);
							ToutiaoAdInfoWithBLOBs toutiaoAdInfoWithBLOBsB = new ToutiaoAdInfoWithBLOBs();
							toutiaoAdInfoWithBLOBsB.setBatchCode(batchCode);
							toutiaoAdInfoWithBLOBsB.setUpdateBy(staffID);
							toutiaoAdInfoWithBLOBsB.setUpdateDate(date);
							toutiaoAdInfoMapper.updateByExampleSelective(toutiaoAdInfoWithBLOBsB, toutiaoAdInfoBExample);
						}
					}else { // 不存在时，插入操作
						toutiaoAdInfoWithBLOBs.setCreateBy(staffID);
						toutiaoAdInfoWithBLOBs.setCreateDate(date);
						// 创意详情信息（json字符串，注意：新创建时，修改广告主ID与计划ID）
						if(adId != null){
							Map<String, Object> creativeReadV2Map = null;
							for (int i = 0; i < Const.SEND_ERROR_NUM; i++) {
								creativeReadV2Map = creativeReadV2(toutiaoAccessToken, advertiserId, adId);
								if("0".equals(creativeReadV2Map.get("code")) ) {
									break;
								}
							}
							toutiaoAdInfoWithBLOBs.setCreativeDtl(creativeReadV2Map.get("data")!=null?creativeReadV2Map.get("data").toString():null);
						}
						toutiaoAdInfoMapper.insertSelective(toutiaoAdInfoWithBLOBs);
					}
				}
			}
			// 最后一页时修改广告主表中广告计划分页信息
			if(adPage == totalPage ) {
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
	
	/**
	 * 
	 * @Title creativeReadV2   
	 * @Description TODO(创意详细信息（新版）)   
	 * @author pengl
	 * @date 2018年8月16日 下午3:24:24
	 */
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
	}
	
	
}
