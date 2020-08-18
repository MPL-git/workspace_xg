package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.dao.ActivityAreaMapper;
import com.jf.dao.ActivityCustomMapper;
import com.jf.dao.DouyinSprPlanCustomMapper;
import com.jf.dao.DouyinSprPlanMapper;
import com.jf.dao.ProductMapper;
import com.jf.entity.ActivityArea;
import com.jf.entity.ActivityAreaExample;
import com.jf.entity.ActivityCustomExample;
import com.jf.entity.ActivityNewCustom;
import com.jf.entity.DouyinSprPlan;
import com.jf.entity.DouyinSprPlanCustom;
import com.jf.entity.DouyinSprPlanCustomExample;
import com.jf.entity.DouyinSprPlanExample;
import com.jf.entity.Product;
import com.jf.entity.ProductExample;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DouyinSprPlanService extends BaseService<DouyinSprPlan, DouyinSprPlanExample> {

	@Autowired
	private DouyinSprPlanMapper douyinSprPlanMapper;
	
	@Autowired
	private DouyinSprPlanCustomMapper douyinSprPlanCustomMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private ActivityCustomMapper activityCustomMapper;
	
	@Autowired
	private ActivityAreaMapper activityAreaMapper;
	
	@Autowired
	public void setDouyinSprPlanMapper(DouyinSprPlanMapper douyinSprPlanMapper) {
		super.setDao(douyinSprPlanMapper);
		this.douyinSprPlanMapper = douyinSprPlanMapper;
	}
	
	public int countByCustomExample(DouyinSprPlanCustomExample example) {
		return douyinSprPlanCustomMapper.countByCustomExample(example);
	}

	public List<DouyinSprPlanCustom> selectByCustomExample(DouyinSprPlanCustomExample example) {
		return douyinSprPlanCustomMapper.selectByCustomExample(example);
	}

	public DouyinSprPlanCustom selectByCustomPrimaryKey(Integer id) {
		return douyinSprPlanCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") DouyinSprPlan record, @Param("example") DouyinSprPlanCustomExample example) {
		return douyinSprPlanCustomMapper.updateByCustomExampleSelective(record, example);
	}

	public List<Map<String, Object>> getDouyinSprPlanList(Map<String, Object> paramMap) {
		return douyinSprPlanCustomMapper.getDouyinSprPlanList(paramMap);
	}
	
	public int countDouyinSprPlanList(Map<String, Object> paramMap) {
		return douyinSprPlanCustomMapper.countDouyinSprPlanList(paramMap);
	}
	
	public List<Map<String, Object>> getDouyinSprPlanStatisticsList(Map<String, Object> paramMap) {
		return douyinSprPlanCustomMapper.getDouyinSprPlanStatisticsList(paramMap);
	}
	
	public void editDouyinSprChnl(DouyinSprPlan douyinSprPlan, String linkId) {
		douyinSprPlanMapper.insertSelective(douyinSprPlan);
		if("1".equals(douyinSprPlan.getSprPlanType()) ) { //1 首页
			douyinSprPlan.setSprUrl(Const.DOUYIN_SY+douyinSprPlan.getId()+"&convertId="+douyinSprPlan.getConvertId()+"&trackingId="+douyinSprPlan.getTrackingId()+"&convertId2="+douyinSprPlan.getConvertId2());
		}else if("2".equals(douyinSprPlan.getSprPlanType()) ) { //2 限时抢购
			douyinSprPlan.setSprUrl(Const.DOUYIN_XSQG+douyinSprPlan.getId()+"&convertId="+douyinSprPlan.getConvertId()+"&trackingId="+douyinSprPlan.getTrackingId()+"&convertId2="+douyinSprPlan.getConvertId2());
		}else if("3".equals(douyinSprPlan.getSprPlanType()) ) { //3 单品爆款
			douyinSprPlan.setSprUrl(Const.DOUYIN_DPBK+douyinSprPlan.getId()+"&convertId="+douyinSprPlan.getConvertId()+"&trackingId="+douyinSprPlan.getTrackingId()+"&convertId2="+douyinSprPlan.getConvertId2());
		}else if("4".equals(douyinSprPlan.getSprPlanType()) ) { //4 新用户专区
			douyinSprPlan.setSprUrl(Const.DOUYIN_XYHZQ+douyinSprPlan.getId()+"&convertId="+douyinSprPlan.getConvertId()+"&trackingId="+douyinSprPlan.getTrackingId()+"&convertId2="+douyinSprPlan.getConvertId2());
		}else if("5".equals(douyinSprPlan.getSprPlanType()) ) { //5 新用户秒杀
			douyinSprPlan.setSprUrl(Const.DOUYIN_XYHMS+douyinSprPlan.getId()+"&convertId="+douyinSprPlan.getConvertId()+"&trackingId="+douyinSprPlan.getTrackingId()+"&convertId2="+douyinSprPlan.getConvertId2());
		}else if("6".equals(douyinSprPlan.getSprPlanType()) ) { //6 断码清仓
			douyinSprPlan.setSprUrl(Const.DOUYIN_DMQC+douyinSprPlan.getId()+"&convertId="+douyinSprPlan.getConvertId()+"&trackingId="+douyinSprPlan.getTrackingId()+"&convertId2="+douyinSprPlan.getConvertId2());
		}else if("7".equals(douyinSprPlan.getSprPlanType()) ) { //7 商品详情页
			douyinSprPlan.setSprUrl(Const.DOUYIN_SPXQY+linkId+"&adSprId="+douyinSprPlan.getId()+"&convertId="+douyinSprPlan.getConvertId()+"&trackingId="+douyinSprPlan.getTrackingId()+"&convertId2="+douyinSprPlan.getConvertId2());
		}else if("8".equals(douyinSprPlan.getSprPlanType()) ) { //8 特卖详情页
			douyinSprPlan.setSprUrl(Const.DOUYIN_TMXQY+douyinSprPlan.getLinkValue()+"&adSprId="+douyinSprPlan.getId()+"&convertId="+douyinSprPlan.getConvertId()+"&trackingId="+douyinSprPlan.getTrackingId()+"&convertId2="+douyinSprPlan.getConvertId2());
		}else if("9".equals(douyinSprPlan.getSprPlanType()) ) { //9 会场详情页
			if("1".equals(linkId) ) { //1 品牌会场
				douyinSprPlan.setSprUrl(Const.DOUYIN_PPHCXQY+douyinSprPlan.getLinkValue()+"&adSprId="+douyinSprPlan.getId()+"&convertId="+douyinSprPlan.getConvertId()+"&trackingId="+douyinSprPlan.getTrackingId()+"&convertId2="+douyinSprPlan.getConvertId2());
			}else { // 2 单品会场
				douyinSprPlan.setSprUrl(Const.DOUYIN_DPHCXQY+douyinSprPlan.getLinkValue()+"&adSprId="+douyinSprPlan.getId()+"&convertId="+douyinSprPlan.getConvertId()+"&trackingId="+douyinSprPlan.getTrackingId()+"&convertId2="+douyinSprPlan.getConvertId2());
			}
		}
		douyinSprPlanMapper.updateByPrimaryKeySelective(douyinSprPlan);
	}
	
	public String validateLinkValue(String sprPlanType, String linkValue) {
		String returnStr = "";
		if("7".equals(sprPlanType) ) { //商品详情
			ProductExample productExample = new ProductExample();
			productExample.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(linkValue);
			productExample.setOrderByClause(" id desc");
			productExample.setLimitStart(0);
			productExample.setLimitSize(1);
			List<Product> productList = productMapper.selectByExample(productExample);
			if(CollectionUtils.isNotEmpty(productList) ) {
				returnStr = productList.get(0).getId().toString();
			}
		}else if("8".equals(sprPlanType) ) { //特卖详情页
			ActivityCustomExample activityCustomExample = new ActivityCustomExample();
			ActivityCustomExample.ActivityCustomCriteria activityCustomCriteria = activityCustomExample.createCriteria();
			activityCustomCriteria.andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(linkValue));
			activityCustomCriteria.andActivityAreaBySourceEqualTo("2"); //品牌特卖
			List<ActivityNewCustom> activityNewCustomList = activityCustomMapper.selectByCustomExample(activityCustomExample);
			if(CollectionUtils.isNotEmpty(activityNewCustomList) ) {
				returnStr = activityNewCustomList.get(0).getId().toString();
			}
		}else if("9".equals(sprPlanType) ) { //会场详情页
			ActivityAreaExample activityAreaExample = new ActivityAreaExample();
			ActivityAreaExample.Criteria activityAreaCriteria = activityAreaExample.createCriteria();
			activityAreaCriteria.andDelFlagEqualTo("0");
			activityAreaCriteria.andSourceEqualTo("1"); //会场
			List<String> typeList = new ArrayList<String>();
			typeList.add("1");
			typeList.add("2");
			activityAreaCriteria.andTypeIn(typeList); //会场类型 in （品牌会场、单品会场）
			activityAreaCriteria.andStatusEqualTo("1"); //会场启用状态=启用
			activityAreaCriteria.andIdEqualTo(Integer.parseInt(linkValue));
			List<ActivityArea> activityAreaList = activityAreaMapper.selectByExample(activityAreaExample);
			if(CollectionUtils.isNotEmpty(activityAreaList) ) {
				returnStr = activityAreaList.get(0).getType();
			}
		}
		return returnStr;
	}
	
}
