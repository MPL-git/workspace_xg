package com.jf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ActivityAreaMapper;
import com.jf.entity.ActivityArea;
import com.jf.entity.ActivityAreaExample;
import com.jf.entity.Product;
import com.jf.entity.SingleProductActivity;
import com.jf.entity.SingleProductActivityExample;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;

@Service
@Transactional
public class ActivityAreaService extends BaseService<ActivityArea,ActivityAreaExample> {
	@Autowired
	private ActivityAreaMapper activityAreaMapper;
	
	@Resource
	private ProductService productService;
	@Resource
	private SysParamCfgService sysParamCfgService;
	@Resource
	private ActivityAreaService activityAreaService;
	@Resource
	private SingleProductActivityService singleProductActivityService;
	
	
	@Autowired
	public void setActivityAreaMapper(ActivityAreaMapper activityAreaMapper) {
		super.setDao(activityAreaMapper);
		this.activityAreaMapper = activityAreaMapper;
	}


	public ActivityArea findModel(Integer activityAreaId) {
		
		return activityAreaMapper.selectByPrimaryKey(activityAreaId);
	}
	
	public void updateActivityAreaProductInfoTask() {
		Date date = new Date();
		SysParamCfg sysParamCfg = null;
		SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
		sysParamCfgExample.createCriteria().andParamCodeEqualTo("APP_ACTIVITY_AREA_PRODUCT_SIGN_ID");
		List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
		if(CollectionUtils.isNotEmpty(sysParamCfgs)){
			sysParamCfg = sysParamCfgs.get(0);
			Integer activityAreaId = Integer.valueOf(sysParamCfg.getParamValue());
			ActivityAreaExample areaExample = new ActivityAreaExample();
			areaExample.createCriteria().andActivityBeginTimeLessThanOrEqualTo(date)
			.andActivityEndTimeGreaterThan(date).andDelFlagEqualTo("0").andSourceEqualTo("1");
			areaExample.setOrderByClause("id");
			List<ActivityArea> activityAreas = activityAreaService.selectByExample(areaExample);
			if(CollectionUtils.isNotEmpty(activityAreas)){
				List<Integer> productIds = new ArrayList<Integer>();
				String id = activityAreas.get(0).getId().toString();
				sysParamCfg.setParamValue(id);
				sysParamCfgService.updateByPrimaryKeySelective(sysParamCfg);
				List<Product> products = productService.getActivityAreaEndProduct(activityAreaId);
				if(CollectionUtils.isNotEmpty(products)){
					for (Product product : products) {
						productIds.add(product.getId());
					}
					if(CollectionUtils.isNotEmpty(productIds)){
						productService.updateProductActivityStatus(productIds);
					}
				}
			}
		}
		
	}


	public void updateActivityProductInfoTask() {
		Date date = new Date();
		SysParamCfg sysParamCfg = null;
		SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
		sysParamCfgExample.createCriteria().andParamCodeEqualTo("APP_ACTIVITY_PRODUCT_SIGN_ID");
		List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
		if(CollectionUtils.isNotEmpty(sysParamCfgs)){
			sysParamCfg = sysParamCfgs.get(0);
			Integer activityAreaId = Integer.valueOf(sysParamCfg.getParamValue());
			ActivityAreaExample areaExample = new ActivityAreaExample();
			areaExample.createCriteria().andActivityBeginTimeLessThanOrEqualTo(date)
			.andActivityEndTimeGreaterThan(date).andDelFlagEqualTo("0").andSourceEqualTo("2");
			areaExample.setOrderByClause("id");
			List<ActivityArea> activityAreas = activityAreaService.selectByExample(areaExample);
			if(CollectionUtils.isNotEmpty(activityAreas)){
				List<Integer> productIds = new ArrayList<Integer>();
				String id = activityAreas.get(0).getId().toString();
				sysParamCfg.setParamValue(id);
				sysParamCfgService.updateByPrimaryKeySelective(sysParamCfg);
				List<Product> products = productService.getActivityEndProduct(activityAreaId);
				if(CollectionUtils.isNotEmpty(products)){
					for (Product product : products) {
						productIds.add(product.getId());
					}
					if(CollectionUtils.isNotEmpty(productIds)){
						productService.updateProductActivityStatus(productIds);
					}
				}
			}
		}
		
	}


	public void updateSingleActivityProductInfoTask() {
		Date date = new Date();
		SysParamCfg sysParamCfg = null;
		SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
		sysParamCfgExample.createCriteria().andParamCodeEqualTo("APP_SINGLE_PRODUCT_SIGN_ID");
		List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
		if(CollectionUtils.isNotEmpty(sysParamCfgs)){
			sysParamCfg = sysParamCfgs.get(0);
			//获取开始中活动最小的id
			Integer singleActivityId = Integer.valueOf(sysParamCfg.getParamValue());
			SingleProductActivityExample productActivityExample = new SingleProductActivityExample();
			productActivityExample.createCriteria().andBeginTimeLessThanOrEqualTo(date)
			.andEndTimeGreaterThan(date).andDelFlagEqualTo("0");
			productActivityExample.setOrderByClause("id");
			List<SingleProductActivity> productActivities = singleProductActivityService.selectByExample(productActivityExample);
			if(CollectionUtils.isNotEmpty(productActivities)){
				List<Integer> productIds = new ArrayList<Integer>();
				String id = productActivities.get(0).getId().toString();
				sysParamCfg.setParamValue(id);
				sysParamCfgService.updateByPrimaryKeySelective(sysParamCfg);
				//修改商品数据
				List<Product> products = productService.getSingleActivityEndProduct(singleActivityId);
				if(CollectionUtils.isNotEmpty(products)){
					for (Product product : products) {
						productIds.add(product.getId());
					}
					if(CollectionUtils.isNotEmpty(productIds)){
						productService.updateProductActivityStatus(productIds);
					}
				}
			}
		}
		
	}
	
}
