package com.jf.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;
import com.jf.common.utils.TouTiaoSendUtil;
import com.jf.dao.SysParamCfgMapper;
import com.jf.dao.ToutiaoAdvertiserInfoMapper;
import com.jf.dao.ToutiaoCampaignInfoCustomMapper;
import com.jf.dao.ToutiaoCampaignInfoMapper;
import com.jf.dao.ToutiaoTokenAdvertiserInfoCustomMapper;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import com.jf.entity.ToutiaoAdvertiserInfo;
import com.jf.entity.ToutiaoAdvertiserInfoExample;
import com.jf.entity.ToutiaoCampaignInfo;
import com.jf.entity.ToutiaoCampaignInfoCustom;
import com.jf.entity.ToutiaoCampaignInfoCustomExample;
import com.jf.entity.ToutiaoCampaignInfoExample;
import com.jf.entity.ToutiaoTokenAdvertiserInfoCustom;

@Service
@Transactional
public class ToutiaoCampaignInfoService extends BaseService<ToutiaoCampaignInfo, ToutiaoCampaignInfoExample> {
	
	@Autowired
	private ToutiaoCampaignInfoMapper toutiaoCampaignInfoMapper;
	
	@Autowired
	private ToutiaoCampaignInfoCustomMapper toutiaoCampaignInfoCustomMapper;
	
	@Autowired
	private ToutiaoAdvertiserInfoMapper toutiaoAdvertiserInfoMapper;
	
	@Autowired
	private SysParamCfgMapper sysParamCfgMapper;
	
	@Autowired
	private ToutiaoTokenAdvertiserInfoCustomMapper toutiaoTokenAdvertiserInfoCustomMapper;
	
	@Autowired
	public void setToutiaoCampaignInfoMapper(ToutiaoCampaignInfoMapper toutiaoCampaignInfoMapper) {
		super.setDao(toutiaoCampaignInfoMapper);
		this.toutiaoCampaignInfoMapper = toutiaoCampaignInfoMapper;
	}
	
	public int countByCustomExample(ToutiaoCampaignInfoCustomExample example) {
		return toutiaoCampaignInfoCustomMapper.countByCustomExample(example);
	}

	public List<ToutiaoCampaignInfoCustom> selectByCustomExample(ToutiaoCampaignInfoCustomExample example) {
		return toutiaoCampaignInfoCustomMapper.selectByCustomExample(example);
	}

	public ToutiaoCampaignInfoCustom selectByCustomPrimaryKey(Integer id) {
		return toutiaoCampaignInfoCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") ToutiaoCampaignInfo record, @Param("example") ToutiaoCampaignInfoCustomExample example) {
		return toutiaoCampaignInfoCustomMapper.updateByCustomExampleSelective(record, example);
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
	 * @Title campaignGetList   
	 * @Description TODO(获取广告组（新）)   
	 * @author pengl
	 * @date 2018年8月15日 下午8:07:57
	 */
	public Map<String, Object> campaignGetList(StaffBean staffBean, String advertiserId, int batchCode, int campaignPageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ToutiaoTokenAdvertiserInfoCustom toutiaoTokenAdvertiserInfoCustom = toutiaoTokenAdvertiserInfoCustomMapper.selectToutiaoTokenAdvertiserInfo(advertiserId);
		if(toutiaoTokenAdvertiserInfoCustom != null) {
			String toutiaoAccessToken = toutiaoTokenAdvertiserInfoCustom.getAccessToken();
			int staffId = Integer.parseInt(staffBean.getStaffID());
			int campaignPage = 1;
			// 第一次访问（获取页码信息）
			for (int i = 0; i < Const.SEND_ERROR_NUM; i++) {
				resultMap = campaignGet(toutiaoAccessToken, advertiserId, campaignPage, campaignPageSize, batchCode, staffId);
				if("0".equals(resultMap.get("code")) ) {
					break;
				}
			}
			campaignPage = (Integer)resultMap.get("totalPage");
			// 第二次及多次访问（遍历循环）
			for (int j = 2; j <= campaignPage; j++) {
				for (int i = 0; i < Const.SEND_ERROR_NUM; i++) {
					resultMap = campaignGet(toutiaoAccessToken, advertiserId, j, campaignPageSize, batchCode, staffId);
					if("0".equals(resultMap.get("code")) ) {
						break;
					}
				}
			}
			
			// 逻辑删除批次号不一样的
			ToutiaoCampaignInfoExample toutiaoCampaignInfoExample = new ToutiaoCampaignInfoExample();
			toutiaoCampaignInfoExample.createCriteria().andDelFlagEqualTo("0")
				.andAdvertiserIdEqualTo(advertiserId)
				.andBatchCodeNotEqualTo(batchCode);
			ToutiaoCampaignInfo toutiaoCampaignInfo = new ToutiaoCampaignInfo();
			toutiaoCampaignInfo.setUpdateBy(staffId);
			toutiaoCampaignInfo.setUpdateDate(new Date());
			toutiaoCampaignInfo.setDelFlag("1");
			toutiaoCampaignInfoMapper.updateByExampleSelective(toutiaoCampaignInfo, toutiaoCampaignInfoExample);
			
			resultMap.put("code", "200");
			resultMap.put("message", StateCode.JSON_AJAX_SUCCESS.getStateMessage());
		}else {
			resultMap.put("code", StateCode.JSON_AJAX_ERROR.getStateCode());
			resultMap.put("message", "token不存在！");
		}
		return resultMap;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> campaignGet(String toutiaoAccessToken, String advertiserId, int campaignPage, int campaignPageSize, int batchCode, int staffID ) {
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
		
		//System.out.println("campaignGetStr=====================>"+campaignGetStr);
		
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
					String modifyTime = campaignJson.containsKey("modify_time")?campaignJson.getString("modify_time"):null;
					ToutiaoCampaignInfo toutiaoCampaignInfo = new ToutiaoCampaignInfo();
					toutiaoCampaignInfo.setAdvertiserId(advertiserId);
					toutiaoCampaignInfo.setCampaignId(campaignId);
					toutiaoCampaignInfo.setName(campaignJson.containsKey("name")?campaignJson.getString("name"):null);
					toutiaoCampaignInfo.setBudget(campaignJson.containsKey("budget")?campaignJson.get("budget").equals("null")?null:new BigDecimal(campaignJson.getString("budget")):null);
					toutiaoCampaignInfo.setBudgetMode(campaignJson.containsKey("budget_mode")?campaignJson.getString("budget_mode"):null);
					toutiaoCampaignInfo.setLandingType(campaignJson.containsKey("landing_type")?campaignJson.get("landing_type").equals("null")?null:campaignJson.getString("landing_type"):null);
					toutiaoCampaignInfo.setModifyTime(modifyTime);
					toutiaoCampaignInfo.setStatus(campaignJson.containsKey("status")?campaignJson.getString("status"):null);
					toutiaoCampaignInfo.setCampaignCreateTime(campaignJson.containsKey("campaign_create_time")?campaignJson.getString("campaign_create_time"):null);
					toutiaoCampaignInfo.setCampaignModifyTime(campaignJson.containsKey("campaign_modify_time")?campaignJson.getString("campaign_modify_time"):null);
					toutiaoCampaignInfo.setPageInfo(dataJson.getString("page_info"));
					toutiaoCampaignInfo.setUpdateDate(date);
					toutiaoCampaignInfo.setUpdateBy(staffID);
					toutiaoCampaignInfo.setDelFlag("0");
					toutiaoCampaignInfo.setBatchCode(batchCode); // 批次号
					
					// 更新广告组信息
					ToutiaoCampaignInfoExample toutiaoCampaignInfoExample = new ToutiaoCampaignInfoExample();
					toutiaoCampaignInfoExample.createCriteria().andDelFlagEqualTo("0")
						.andAdvertiserIdEqualTo(advertiserId)
						.andCampaignIdEqualTo(campaignId);
					List<ToutiaoCampaignInfo> toutiaoCampaignInfoList = toutiaoCampaignInfoMapper.selectByExample(toutiaoCampaignInfoExample);
					if(toutiaoCampaignInfoList != null && toutiaoCampaignInfoList.size() > 0) { // 本地数据库中已存在
						if(!modifyTime.equals(toutiaoCampaignInfoList.get(0).getModifyTime())) { // 上次修改时间戳不相等，同步操作
							ToutiaoCampaignInfoExample toutiaoCampaignInfoAExample = new ToutiaoCampaignInfoExample();
							toutiaoCampaignInfoAExample.createCriteria().andDelFlagEqualTo("0")
								.andAdvertiserIdEqualTo(advertiserId)
								.andCampaignIdEqualTo(campaignId)
								.andModifyTimeEqualTo(modifyTime);
							toutiaoCampaignInfoMapper.updateByExampleSelective(toutiaoCampaignInfo, toutiaoCampaignInfoAExample);
						}else { // 上次修改时间相等，更新批次号操作
							ToutiaoCampaignInfoExample toutiaoCampaignInfoBExample = new ToutiaoCampaignInfoExample();
							toutiaoCampaignInfoBExample.createCriteria().andDelFlagEqualTo("0")
								.andAdvertiserIdEqualTo(advertiserId)
								.andCampaignIdEqualTo(campaignId)
								.andModifyTimeEqualTo(modifyTime);
							ToutiaoCampaignInfo toutiaoCampaignInfoB = new ToutiaoCampaignInfo();
							toutiaoCampaignInfoB.setBatchCode(batchCode);
							toutiaoCampaignInfoB.setUpdateBy(staffID);
							toutiaoCampaignInfoB.setUpdateDate(date);
							toutiaoCampaignInfoMapper.updateByExampleSelective(toutiaoCampaignInfoB, toutiaoCampaignInfoBExample);
						}
					}else {
						toutiaoCampaignInfo.setCreateBy(staffID);
						toutiaoCampaignInfo.setCreateDate(date);
						toutiaoCampaignInfoMapper.insertSelective(toutiaoCampaignInfo);
					}
				}
			}
			// 最后一页时修改广告主表中广告组分页信息
			if(campaignPage == totalPage ) {
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
	
	
	
	
	
}
