package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.enums.ActivityPreferentialTypeEnum;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ActivityAreaMapper;
import com.jf.dao.ActivityCustomMapper;
import com.jf.dao.ActivityMapper;
import com.jf.dao.ActivityProductMapper;
import com.jf.entity.Activity;
import com.jf.entity.ActivityArea;
import com.jf.entity.ActivityCustom;
import com.jf.entity.ActivityExample;
import com.jf.entity.ActivityProduct;
import com.jf.entity.ActivityProductExample;
import com.jf.entity.ProductBrand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ActivityService extends BaseService<Activity,ActivityExample> {

	@Autowired
	private ActivityMapper dao;
	@Autowired
	private ActivityAreaService activityAreaService;
	@Autowired
	private ActivityProductService activityProductService;
	@Autowired
	private ProductItemService productItemService;
	@Autowired
	private OrderDtlService orderDtlService;
	@Autowired
	private ActivityCustomMapper activityCustomMapper;
	@Autowired
	private ActivityAreaMapper activityAreaMapper;
	@Autowired
	private ActivityProductMapper activityProductMapper;
	@Autowired
	private ProductBrandService productBrandService;
	
	
	@Autowired
	public void setActivityMapper(ActivityMapper activityMapper) {
		super.setDao(activityMapper);
		this.dao = activityMapper;
	}

	/**
	 * 添加活动
	 */
	public Activity save(int mchtId, int activityAreaId, Activity paramActivity){
		Activity activity = findByAreaId(mchtId, activityAreaId);
		boolean isUpdate = activity != null;
		if(activity == null){
			activity = new Activity();
			activity.setMchtId(mchtId);
			activity.setActivityAreaId(activityAreaId);
			activity.setStatus(Const.ACTIVITY_STATUS_WAIT_COMMIT);
			activity.setOperateAuditStatus(Const.AUDIT_STATUS_NONE);
		}
		
		activity.setName(paramActivity.getName());
		activity.setProductTypeId(paramActivity.getProductTypeId());
		activity.setProductTypeSecondId(paramActivity.getProductTypeSecondId());
		activity.setBrandLimitType(paramActivity.getBrandLimitType());
		activity.setProductBrandId(paramActivity.getProductBrandId());
		activity.setExpectedStartTime(paramActivity.getExpectedStartTime());
		activity.setEntryPic(paramActivity.getEntryPic());
		activity.setPosterPic(paramActivity.getPosterPic());
		activity.setPreSellAuditStatus(paramActivity.getPreSellAuditStatus());
		activity.setBrandTeamPic(paramActivity.getBrandTeamPic());
		if(isUpdate){
			update(activity);
		}else{
			save(activity);
		}
		
		return activity;
	}
	
	/**
	 * 添加活动
	 */
	public Activity save(int mchtId, int activityAreaId,int activityId, Activity paramActivity){
		Activity activity = this.selectByPrimaryKey(activityId);
		ActivityArea activityArea = activityAreaService.findById(activityAreaId);
		boolean isUpdate = activity != null;
		if(activity == null){
			activity = new Activity();
			activity.setMchtId(mchtId);
			activity.setActivityAreaId(activityAreaId);
			if(!StringUtil.isEmpty(paramActivity.getStatus())){
				activity.setStatus(paramActivity.getStatus());
			}else{
				activity.setStatus(Const.ACTIVITY_STATUS_WAIT_COMMIT);
			}
			if(!StringUtil.isEmpty(paramActivity.getOperateAuditStatus())){
				activity.setOperateAuditStatus(paramActivity.getOperateAuditStatus());
			}else{
				activity.setOperateAuditStatus(Const.AUDIT_STATUS_NONE);
			}
		}
		if(!StringUtil.isEmpty(paramActivity.getCooAuditStatus()) && "2".equals(paramActivity.getCooAuditStatus())) {
			activity.setCooAuditStatus(paramActivity.getCooAuditStatus());
		}
		activity.setName(paramActivity.getName());
		activity.setProductTypeId(paramActivity.getProductTypeId());
		activity.setProductTypeSecondId(paramActivity.getProductTypeSecondId());
		activity.setBrandLimitType(paramActivity.getBrandLimitType());
		activity.setProductBrandId(paramActivity.getProductBrandId());
		activity.setExpectedStartTime(paramActivity.getExpectedStartTime());
		activity.setEntryPic(paramActivity.getEntryPic());
		activity.setPosterPic(paramActivity.getPosterPic());
		//预售的话直接通过审核
		if(StringUtils.equals(activityArea.getIsPreSell(),"1")){
			activity.setPreSellAuditStatus("2");
		}
		if(isUpdate){
			update(activity);
		}else{
			save(activity);
		}

		return activity;
	}

	public Activity commit(int id){
		Activity model = findById(id);
		if(!model.getStatus().equals(Const.ACTIVITY_STATUS_WAIT_COMMIT) && !model.getStatus().equals(Const.ACTIVITY_STATUS_AUDIT_REJECT)){
			throw new BizException("不是未提报或驳回的活动不能提交");
		}

		if (model.getProductBrandId() == null) {
			throw new BizException("活动主品牌数据错误");
		}

		ProductBrand productBrand = productBrandService.findById(model.getProductBrandId());
		if (StringUtil.isEmpty(productBrand.getStatus())) {
			throw new BizException("活动主品牌状态数据错误");
		}

		if (!"1".equals(productBrand.getStatus())) {
			throw new BizException("当前活动主品牌已关闭或暂停，无法提报审核");
		}

		ActivityArea activityArea = activityAreaService.findById(model.getActivityAreaId());
		if(activityArea.getType() != null && (activityArea.getType().equals(Const.ACTIVITY_TYPE_BAOKUAN) || activityArea.getType().equals(Const.ACTIVITY_TYPE_NEWGUY))){
			//如果是爆款或新用户专享活动，则无需审核直接通过
			model.setStatus(Const.ACTIVITY_STATUS_PASS);
			model.setOperateAuditStatus(Const.AUDIT_STATUS_PASS);
			model.setDesignAuditStatus(Const.AUDIT_STATUS_PASS);
			model.setLawAuditStatus(Const.AUDIT_STATUS_PASS);
			model.setCooAuditStatus(Const.AUDIT_STATUS_PASS);
		}else{
			model.setStatus(Const.ACTIVITY_STATUS_WAIT_AUDIT);
			model.setOperateAuditStatus(Const.AUDIT_STATUS_WAIT);
			model.setOperateAuditBy(null);
			model.setOperateAuditRemarks(null);
			model.setDesignAuditBy(null);
			model.setDesignAuditStatus(null);
			model.setDesignAuditRemarks(null);
			model.setLawAuditBy(null);
			model.setLawAuditStatus(null);
			model.setLawAuditRemarks(null);
			model.setCooAuditBy(null);
			model.setCooAuditStatus(null);
			model.setCooAuditRemarks(null);
		}

		if(model.getSubmitTime() == null){
			model.setSubmitTime(new Date());
		}
		update(model);
		
		if(activityArea.getPreferentialType().equals(String.valueOf(ActivityPreferentialTypeEnum.GIVE.getValue()))){
			ActivityProduct ap = new ActivityProduct();
			ap.setOperateAuditStatus("2");//【专员审核状态】默认为通过
			ap.setCooAuditStatus("2");//【排期状态】默认为通过
			ap.setRefuseFlag("0");
			ap.setUpdateDate(new Date());
			ActivityProductExample ape = new ActivityProductExample();
			ape.createCriteria().andDelFlagEqualTo("0").andActivityIdEqualTo(id).andIsGiftEqualTo("1"); 
			activityProductMapper.updateByExampleSelective(ap, ape);
		}
		return model;
	}

	public Activity findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public Activity findByAreaId(int mchtId, int areaId){
		QueryObject queryObject = new QueryObject(1, 1);
		queryObject.addQuery("mchtId", mchtId);
		queryObject.addQuery("activityAreaId", areaId);
		List<Activity> list = list(queryObject);
		return list.size() > 0 ? list.get(0) : null;
	}

	public Activity save(Activity model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public Activity update(Activity model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		Activity model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public void deleteByAreaId(int mchtId, int areaId) {
		Activity activity = findByAreaId(mchtId, areaId);
		if(activity != null)	delete(activity.getId());
	}

	public int count(QueryObject queryObject) {

		return dao.countByExample(builderQuery(queryObject));
	}

	public List<Activity> list(QueryObject queryObject) {

		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<Activity> paginate(QueryObject queryObject) {
		ActivityExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<Activity> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}



	private ActivityExample builderQuery(QueryObject queryObject) {
		ActivityExample example = new ActivityExample();
		ActivityExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("status") != null){
			criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
		}
		if(queryObject.getQuery("statusNotEquals") != null){
			criteria.andStatusNotEqualTo(queryObject.getQueryToStr("statusNotEquals"));
		}
		if(queryObject.getQuery("activityAreaId") != null){
			criteria.andActivityAreaIdEqualTo(queryObject.getQueryToInt("activityAreaId"));
		}
		if(queryObject.getQuery("mchtId") != null){
			criteria.andMchtIdEqualTo(queryObject.getQueryToInt("mchtId"));
		}
		if(queryObject.getQuery("productBrandId") != null){
			criteria.andProductBrandIdEqualTo(queryObject.getQueryToInt("productBrandId"));
		}
		if(queryObject.getQuery("productTypeId") != null){
			criteria.andProductTypeIdEqualTo(queryObject.getQueryToInt("productTypeId"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

	public Activity fillInfo(Activity info){
		// 活动商品数量
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("activityId", info.getId());
		queryObject.addQuery("refuseFlag", Const.FLAG_FALSE);
		queryObject.addQuery("delFlag", Const.FLAG_FALSE);
		List<ActivityProduct> activityProductList = activityProductService.list(queryObject);
		info.put("productCount", activityProductList.size());	//活动商品数量

		//活动商品库存
		List<Integer> productIds = new ArrayList<>();
		productIds.add(0);
		for(ActivityProduct activityProduct : activityProductList){
			productIds.add(activityProduct.getProductId());
		}
		queryObject = new QueryObject();
		queryObject.addQuery("productIds", productIds);
		info.put("productStock", productItemService.queryStock(queryObject));	//商品库存

		queryObject = new QueryObject();
		queryObject.addQuery("activityId", info.getId());
		queryObject.addQuery("payStatus", "1");
		info.put("saleQuantity", orderDtlService.querySaleQuantity(queryObject));	//销量
		//info.put("saleQuantity", "99");	//历史总销量

		return info;
	}
	
	public List<ActivityCustom> selectActivityCustomByExample(ActivityExample example) {
		return activityCustomMapper.selectByExample(example);
	}
	
	/**
	 * 
	 * @Title saveDesignImg   
	 * @Description TODO(保存修改图片)   
	 * @author pengl
	 * @date 2018年3月7日 上午11:51:30
	 */
	public void saveDesignImg(String statusSource, String activityId, String entryPic, String posterPic,String brandTeamPic) {
		Activity activity = new Activity();
		activity.setId(Integer.parseInt(activityId));
		activity.setEntryPic(entryPic);
		activity.setPosterPic(posterPic);
		activity.setBrandTeamPic(brandTeamPic);
		activity.setDesignAuditStatus("1"); //1：待审核
		dao.updateByPrimaryKeySelective(activity);
		if("2".equals(statusSource)) { //2：特卖
			Activity act = dao.selectByPrimaryKey(Integer.parseInt(activityId));
			if(act.getActivityAreaId() != null) {
				ActivityArea activityArea = new ActivityArea();
				activityArea.setId(act.getActivityAreaId());
				activityArea.setEntryPic(entryPic);
				activityAreaMapper.updateByPrimaryKeySelective(activityArea);
			}
		}
	}
	
	public String getActivityStatusByProductId(Integer productId){
		return activityCustomMapper.getActivityStatusByProductId(productId);
	}
}
