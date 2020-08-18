package com.jf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.jf.dao.ToutiaoAdInfoMapper;
import com.jf.dao.ToutiaoAdvertiserInfoCustomMapper;
import com.jf.dao.ToutiaoAdvertiserInfoMapper;
import com.jf.dao.ToutiaoCampaignInfoMapper;
import com.jf.dao.ToutiaoTokenAdvertiserInfoCustomMapper;
import com.jf.dao.ToutiaoTokenAdvertiserInfoMapper;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import com.jf.entity.ToutiaoAdInfoExample;
import com.jf.entity.ToutiaoAdInfoWithBLOBs;
import com.jf.entity.ToutiaoAdvertiserInfo;
import com.jf.entity.ToutiaoAdvertiserInfoCustom;
import com.jf.entity.ToutiaoAdvertiserInfoCustomExample;
import com.jf.entity.ToutiaoAdvertiserInfoExample;
import com.jf.entity.ToutiaoCampaignInfo;
import com.jf.entity.ToutiaoCampaignInfoExample;
import com.jf.entity.ToutiaoTokenAdvertiserInfo;
import com.jf.entity.ToutiaoTokenAdvertiserInfoCustom;
import com.jf.entity.ToutiaoTokenAdvertiserInfoExample;

@Service
@Transactional
public class ToutiaoAdvertiserInfoService extends BaseService<ToutiaoAdvertiserInfo, ToutiaoAdvertiserInfoExample> {

	@Autowired
	private ToutiaoAdvertiserInfoMapper toutiaoAdvertiserInfoMapper;
	
	@Autowired
	private ToutiaoAdvertiserInfoCustomMapper toutiaoAdvertiserInfoCustomMapper;
	
	@Autowired
	private ToutiaoCampaignInfoMapper toutiaoCampaignInfoMapper;
	
	@Autowired
	private ToutiaoAdInfoMapper toutiaoAdInfoMapper;
	
	@Autowired
	private SysParamCfgMapper sysParamCfgMapper;
	
	@Autowired
	private ToutiaoTokenAdvertiserInfoCustomMapper toutiaoTokenAdvertiserInfoCustomMapper;

	@Autowired
	private ToutiaoTokenAdvertiserInfoMapper toutiaoTokenAdvertiserInfoMapper;
	
	@Autowired
	public void setToutiaoAdvertiserInfoMapper(ToutiaoAdvertiserInfoMapper toutiaoAdvertiserInfoMapper) {
		super.setDao(toutiaoAdvertiserInfoMapper);
		this.toutiaoAdvertiserInfoMapper = toutiaoAdvertiserInfoMapper;
	}
	
	public int countByCustomExample(ToutiaoAdvertiserInfoCustomExample example) {
		return toutiaoAdvertiserInfoCustomMapper.countByCustomExample(example);
	}

	public List<ToutiaoAdvertiserInfoCustom> selectByCustomExample(ToutiaoAdvertiserInfoCustomExample example) {
		return toutiaoAdvertiserInfoCustomMapper.selectByCustomExample(example);
	}

	public ToutiaoAdvertiserInfoCustom selectByCustomPrimaryKey(Integer id) {
		return toutiaoAdvertiserInfoCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") ToutiaoAdvertiserInfo record, @Param("example") ToutiaoAdvertiserInfoCustomExample example) {
		return toutiaoAdvertiserInfoCustomMapper.updateByCustomExampleSelective(record, example);
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
	 * @Title getAdvertiserInfo   
	 * @Description TODO(更新广告主信息)   
	 * @author pengl
	 * @date 2018年8月20日 下午4:47:07
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getAdvertiserInfo(StaffBean staffBean, String advertiserId, int batchCode) {
		Map<String, String> resultMap = new HashMap<String, String>();
		int staffId = Integer.parseInt(staffBean.getStaffID());
		Date date = new Date();
		ToutiaoTokenAdvertiserInfoCustom toutiaoTokenAdvertiserInfoCustom = toutiaoTokenAdvertiserInfoCustomMapper.selectToutiaoTokenAdvertiserInfo(advertiserId);
		if(toutiaoTokenAdvertiserInfoCustom != null) {
			String toutiaoAccessToken = toutiaoTokenAdvertiserInfoCustom.getAccessToken();
			Map<String, Object> map = new HashMap<String, Object>();
			List<String> advertiserIdList = new ArrayList<String>(); 
			advertiserIdList.add(advertiserId);
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
					toutiaoAdvertiserInfo.setBalance(dataJson.containsKey("balance")?dataJson.get("balance").equals("null")?null:new BigDecimal(dataJson.getString("balance")):null);
					toutiaoAdvertiserInfo.setUpdateBy(staffId);
					toutiaoAdvertiserInfo.setUpdateDate(date);
					toutiaoAdvertiserInfo.setBatchCode(batchCode); // 批次号
					
					ToutiaoAdvertiserInfoExample toutiaoAdvertiserInfoExample = new ToutiaoAdvertiserInfoExample();
					toutiaoAdvertiserInfoExample.createCriteria().andDelFlagEqualTo("0").andAdvertiserIdEqualTo(advertiserId);
					toutiaoAdvertiserInfoMapper.updateByExampleSelective(toutiaoAdvertiserInfo, toutiaoAdvertiserInfoExample);
				}
			}
			resultMap.put("code", adGetJson.getString("code"));
			resultMap.put("message", adGetJson.getString("message"));
		}else {
			resultMap.put("code", StateCode.JSON_AJAX_ERROR.getStateCode());
			resultMap.put("message", "token不存在！");
		}
		return resultMap;
	}
	
	/**
	 * 
	 * @Title delAdvertiser   
	 * @Description TODO(删除广告主)   
	 * @author pengl
	 * @date 2018年8月21日 下午12:38:16
	 */
	public void delAdvertiser(StaffBean staffBean, ToutiaoAdvertiserInfo toutiaoAdvertiserInfo) {
		Date date = new Date();
		// 删除广告主
		ToutiaoAdvertiserInfo toutiaoAdvertiser = new ToutiaoAdvertiserInfo();
		toutiaoAdvertiser.setId(toutiaoAdvertiserInfo.getId());
		toutiaoAdvertiser.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
		toutiaoAdvertiser.setUpdateDate(date);
		toutiaoAdvertiser.setDelFlag("1");
		toutiaoAdvertiserInfoMapper.updateByPrimaryKeySelective(toutiaoAdvertiser);
		// 删除广告组
		ToutiaoCampaignInfoExample toutiaoCampaignInfoExample = new ToutiaoCampaignInfoExample();
		toutiaoCampaignInfoExample.createCriteria().andDelFlagEqualTo("0")
			.andAdvertiserIdEqualTo(toutiaoAdvertiserInfo.getAdvertiserId());
		ToutiaoCampaignInfo toutiaoCampaign = new ToutiaoCampaignInfo();
		toutiaoCampaign.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
		toutiaoCampaign.setUpdateDate(date);
		toutiaoCampaign.setDelFlag("1");
		toutiaoCampaignInfoMapper.updateByExampleSelective(toutiaoCampaign, toutiaoCampaignInfoExample);
		// 删除广告计划
		ToutiaoAdInfoExample toutiaoAdInfoExample = new ToutiaoAdInfoExample();
		toutiaoAdInfoExample.createCriteria().andDelFlagEqualTo("0")
			.andAdvertiserIdEqualTo(toutiaoAdvertiserInfo.getAdvertiserId());
		ToutiaoAdInfoWithBLOBs toutiaoAd = new ToutiaoAdInfoWithBLOBs();
		toutiaoAd.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
		toutiaoAd.setUpdateDate(date);
		toutiaoAd.setDelFlag("1");
		toutiaoAdInfoMapper.updateByExampleSelective(toutiaoAd, toutiaoAdInfoExample);
		// 删除头条授权广告主关系
		ToutiaoTokenAdvertiserInfoExample toutiaoTokenAdvertiserInfoExample = new ToutiaoTokenAdvertiserInfoExample();
		toutiaoTokenAdvertiserInfoExample.createCriteria().andDelFlagEqualTo("0").andAdvertiserIdEqualTo(toutiaoAdvertiserInfo.getAdvertiserId());
		ToutiaoTokenAdvertiserInfo toutiaoTokenAdvertiserInfo = new ToutiaoTokenAdvertiserInfo();
		toutiaoTokenAdvertiserInfo.setUpdateBy(Integer.parseInt(staffBean.getStaffID()));
		toutiaoTokenAdvertiserInfo.setUpdateDate(date);
		toutiaoTokenAdvertiserInfo.setDelFlag("1");
		toutiaoTokenAdvertiserInfoMapper.updateByExampleSelective(toutiaoTokenAdvertiserInfo, toutiaoTokenAdvertiserInfoExample);
	}
	
	/**
	 * 
	 * @Title saveToutiaoAdvertiserInfo   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年12月21日 下午6:29:26
	 */
	public void saveToutiaoAdvertiserInfo(ToutiaoAdvertiserInfo toutiaoAdvertiserInfo, ToutiaoTokenAdvertiserInfo toutiaoTokenAdvertiserInfo) {
		toutiaoAdvertiserInfoMapper.insertSelective(toutiaoAdvertiserInfo);
		toutiaoTokenAdvertiserInfoMapper.insertSelective(toutiaoTokenAdvertiserInfo);
	}
	
	
	
	
}
