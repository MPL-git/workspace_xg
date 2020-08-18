package com.jf.service;

import com.alibaba.fastjson.JSONObject;
import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ActivityProductCustomMapper;
import com.jf.dao.ActivityProductDepositMapper;
import com.jf.dao.ActivityProductMapper;
import com.jf.entity.*;
import com.jf.entity.ProductItemExample.Criteria;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class ActivityProductService {

	@Autowired
	private ActivityProductMapper dao;
	@Autowired
	private ActivityProductCustomMapper customDao;
	@Autowired
	private ActivityAreaService activityAreaService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderDtlService orderDtlService;
	@Autowired
	private ActivityProductDepositMapper activityProductDepositMapper;
	@Autowired
	private ProductItemService productItemService;
	@Autowired
	private ActivityProductDepositService activityProductDepositService;
	@Autowired
	private AllowanceInfoService allowanceInfoService;
	/**
	 * 添加活动商品
	 *
	 * @param activityId the activity id
	 * @param productId  the product id
	 */
	public ActivityProduct addProduct(int mchtId, int activityId, int productId, String isPreSell, String productStatus,JSONObject productParams) {
		ActivityProduct ap = findByProductId(activityId, productId);
		//当活动表有该商品，则获取该商品的活动价
		BigDecimal activityPrice = null;
		if(ap!=null){//BUG #4356,重新报名：先删除原有的，再重新添加
			activityPrice = ap.getActivityPrice();
			ap.setDelFlag("1");
			this.update(ap);
		}
		String shopProductActivityStatus = productService.getShopProductActivityStatus(productId);
		if(!shopProductActivityStatus.equals("0")){//0.闲置
			throw new BizException("商品不是闲置的，不能报名,请刷新页面");
		}
		Activity activity = activityService.findById(activityId);
		ActivityArea activityArea = activityAreaService.findById(activity.getActivityAreaId());

		ActivityProduct model = findByProductId(activityId, productId);
		
		Product product = productService.selectByPrimaryKey(productId);
		if(product == null || product.getMchtId() != mchtId){
			throw new BizException("未找到该商品");
		}
		if(!product.getSaleType().equals("1")){
			throw new BizException("品牌特卖的商品必须是品牌款才能报名");
		}
		if(product.getSvipDiscount()==null && !StringUtils.equals(isPreSell,"1")){
			throw new BizException("请先设置SVIP折扣再进行报名");
		}
		product.setUpdateDate(new Date());
		//如果是报名预售商品，就把SVIP设置为null
		if(StringUtils.equals(isPreSell,"1")){
			product.setSvipDiscount(null);
		}
		productService.updateByPrimaryKey(product);
		boolean isNew = model == null;
		if(isNew){
			model = new ActivityProduct();
			model.setActivityId(activityId);
			model.setProductId(productId);
			model.setOperateAuditStatus("1");//1：待审核
			
			//需求1651 如果商品是第一次报名，则要把活动价最小值存入，当做第一次报名活动价
			if(activityPrice != null){
				model.setActivityPrice(activityPrice);
			}else{
				ProductItemExample productItemExample = new ProductItemExample();
				Criteria criteria = productItemExample.createCriteria();
				productItemExample.setOrderByClause(" sale_price asc");
				criteria.andDelFlagEqualTo("0").andProductIdEqualTo(productId);
				List<ProductItem> productItems = productItemService.selectByExample(productItemExample);
				model.setActivityPrice(productItems.get(0).getSalePrice());
			}			
		}else{//BUG #4356后，再也没有修改的情况出现了
			
			model.setDelFlag(Const.FLAG_FALSE);

			model.setOperateAuditStatus("1");
			model.setOperateAuditBy(null);

			model.setDesignAuditStatus(null);
			model.setDesignAuditBy(null);

			model.setLawAuditStatus(null);
			model.setLawAuditBy(null);

			model.setCooAuditStatus(null);
			model.setCooAuditBy(null);
		}
		
		model.setRefuseFlag(Const.FLAG_FALSE);
		boolean isDanpin = (activityArea.getType() != null &&
				(activityArea.getType().equals(Const.ACTIVITY_TYPE_DANPIN) || activityArea.getType().equals(Const.ACTIVITY_TYPE_BAOKUAN) || activityArea.getType().equals(Const.ACTIVITY_TYPE_NEWGUY) ));
		if(isDanpin){
			model.setOperateAuditStatus(Const.AUDIT_STATUS_WAIT);
		}
		if(product.getAuditStatus().equals(Const.AUDIT_STATUS_PASS)){
			model.setLawAuditStatus(Const.AUDIT_STATUS_PASS);
		}

		if(isNew){
			save(model);
		}else{
			update(model);
		}

		//预售会场
		if(StringUtils.equals(isPreSell,"1")){
			//添加商品到活动预售商品定金设置表
			ActivityProductDepositExample activityProductDepositExample = new ActivityProductDepositExample();
			activityProductDepositExample.createCriteria().andDelFlagEqualTo("0").andActivityIdEqualTo(activityId).andProductIdEqualTo(productId);
			List<ActivityProductDeposit> activityProductDeposits = activityProductDepositService.selectByExample(activityProductDepositExample);
			if(activityProductDeposits.isEmpty()){
				ActivityProductDeposit activityProductDeposit = new ActivityProductDeposit();
				activityProductDeposit.setActivityId(activityId);
				activityProductDeposit.setProductId(productId);
				activityProductDeposit.setDeposit(new BigDecimal(productParams.getDoubleValue("payInAdvance")));
				activityProductDeposit.setDeductAmount(new BigDecimal(productParams.getDoubleValue("deduction")));
				activityProductDeposit.setCreateBy(mchtId);
				activityProductDeposit.setCreateDate(new Date());
				activityProductDeposit.setUpdateBy(mchtId);
				activityProductDeposit.setUpdateDate(new Date());
				activityProductDeposit.setDelFlag("0");
				activityProductDepositService.insertSelective(activityProductDeposit);
			}else {
				ActivityProductDeposit activityProductDeposit = activityProductDeposits.get(0);
				activityProductDeposit.setUpdateDate(new Date());
				activityProductDeposit.setUpdateBy(mchtId);
				activityProductDepositService.updateByPrimaryKeySelective(activityProductDeposit);
			}
		}
		return model;
	}

	/**
	 * 退出活动商品
	 *
	 * @param activityId the activity id
	 * @param productId  the product id
	 */
	public ActivityProduct delProduct(Integer activityId, Integer productId,String isPreSell) {
		ActivityProduct model = findByProductId(activityId, productId);
		Product product = productService.selectByPrimaryKey(productId);
		product.setUpdateDate(new Date());
//		product.setIsActivity("0");2018年7月10日早开会决定：退出活动时不再设置为不在活动中。该字段将会弃用。
		productService.updateByPrimaryKeySelective(product);
		if(model != null){
			model.setDelFlag(Const.FLAG_TRUE);
			update(model);
			//如果是预售会场，就删除活动预售商品定金设置表该商品数据
			if(StringUtils.equals(isPreSell,"1")){
				ActivityProductDepositExample activityProductDepositExample = new ActivityProductDepositExample();
				activityProductDepositExample.createCriteria().andProductIdEqualTo(productId).andActivityIdEqualTo(activityId).andDelFlagEqualTo("0");
				ActivityProductDeposit activityProductDeposit = new ActivityProductDeposit();
				activityProductDeposit.setDelFlag("1");
				activityProductDepositService.updateByExampleSelective(activityProductDeposit,activityProductDepositExample);
			}
		}
		return model;
	}

	/**
	 * 活动商品列表
	 *
	 *
	 * @param id
	 * @param isPreSell  the isPreSell
	 * @param activityId  the activity id
	 * @param status      the status
	 * @param queryObject the query object
	 * @return page
	 */
	public Page<Product> paginateProduct(Integer activityAreaId, String isPreSell, int activityId, int status, QueryObject queryObject) {
		Page<Product> page = productService.paginate(builderProductQuery(activityId, status, queryObject));
		for(Product product : page.getList()){
			productService.fillInfo(product);
			QueryObject qo = new QueryObject();
			qo.addQuery("activityId", activityId);
			qo.addQuery("productId", product.getId());
			qo.addQuery("payStatus", "1");
			product.put("activityProductSaleQuantity", orderDtlService.querySaleQuantity(qo));//销量
			ActivityProductDepositExample example = new ActivityProductDepositExample();
			example.createCriteria().andDelFlagEqualTo("0").andActivityIdEqualTo(activityId).andProductIdEqualTo(product.getId());
			List<ActivityProductDeposit> activityProductDeposits = activityProductDepositMapper.selectByExample(example);
			//传统官方会场活动
			if(activityProductDeposits!=null && activityProductDeposits.size()>0){
				product.put("deposit", activityProductDeposits.get(0).getDeposit());//定金
				product.put("deductAmount", activityProductDeposits.get(0).getDeductAmount());//抵扣金额
			}
			AllowanceInfo allowanceInfo = allowanceInfoService.findByActivityAreaId(activityAreaId);
			if (allowanceInfo!=null){ //购物津贴是非阶梯的
				String[] rule = allowanceInfo.getRule().split("[,]");
				product.put("minimum", rule[0]);
				product.put("money", rule[1]);
			}
			//预售官方会场活动
			/*if(StringUtils.equals(isPreSell,"1")){
				product.put("deposit", activityProductDeposits.get(0).getDeposit());//定金
				product.put("deductAmount", activityProductDeposits.get(0).getDeductAmount());//抵扣金额
			}*/
			if(status != Const.ACTIVITY_PRODUCT_STATUS_WAIT){
				ActivityProduct activityProduct = findByProductId(activityId, product.getId());
				product.put("activityProductId", activityProduct.getId());
				product.put("activityProductRemarks", activityProduct.getRemarks());
				product.put("activityProductRefuseFlag", activityProduct.getRefuseFlag());
				product.put("activityProductCooAuditStatus", activityProduct.getCooAuditStatus());
				product.put("activityProductOperateAuditStatus", activityProduct.getOperateAuditStatus());
			}
		}
		return page;
	}

	/**
	 * 活动商品数量
	 *
	 * @param activityId  the activity id
	 * @param status      the status
	 * @return page
	 */
	public int countProduct(int mchtId, int activityId, int status) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtId", mchtId);
		queryObject.addQuery("saleType", "1");
		if(status == 1){
			queryObject.addQuery("hasProductBrandStatus", true);
		}
		if(status == Const.ACTIVITY_PRODUCT_STATUS_WAIT){

		}
		return productService.count(builderProductQuery(activityId, status, queryObject));
	}


















	public ActivityProduct findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public ActivityProduct findByProductId(Integer activityId, Integer productId){
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("delFlag", "0");
		queryObject.addQuery("activityId", activityId);
		queryObject.addQuery("productId", productId);
		List<ActivityProduct> list = list(queryObject);
		return list.size() > 0 ? list.get(0) : null;
	}

	public ActivityProduct save(ActivityProduct model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public ActivityProduct update(ActivityProduct model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ActivityProduct model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<ActivityProduct> list(QueryObject queryObject) {

		return dao.selectByExample(builderQuery(queryObject));
	}

//	/**
//	 * 活动商品
//	 *
//	 * @param activityId the activity id
//	 * @return integer
//	 */
//	public List<ActivityProduct> list(int activityId) {
//		QueryObject queryObject = new QueryObject();
//		queryObject.addQuery("activityId", activityId);
//		List<ActivityProduct> list = list(queryObject);
//		for(ActivityProduct activityProduct : list){
//
//		}
//		return list;
//	}

	public Page<ActivityProduct> paginate(QueryObject queryObject) {
		ActivityProductExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<ActivityProduct> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private ActivityProductExample builderQuery(QueryObject queryObject) {
		ActivityProductExample example = new ActivityProductExample();
		ActivityProductExample.Criteria criteria = example.createCriteria();
		if(queryObject.getQuery("delFlag") != null){
			criteria.andDelFlagEqualTo(queryObject.getQueryToStr("delFlag"));
		}
		if(queryObject.getQuery("activityId") != null){
			criteria.andActivityIdEqualTo(queryObject.getQueryToInt("activityId"));
		}
		if(queryObject.getQuery("productId") != null){
			criteria.andProductIdEqualTo(queryObject.getQueryToInt("productId"));
		}
		if(queryObject.getQuery("refuseFlag") != null){
			criteria.andRefuseFlagEqualTo(queryObject.getQueryToStr("refuseFlag"));
		}
		if(queryObject.getQuery("ids") != null){
			List<Integer> ids = queryObject.getQuery("ids");
			criteria.andIdIn(ids);
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

	private QueryObject builderProductQuery(int activityId, int status, QueryObject productQueryObject){
		Activity activity = activityService.findById(activityId);
		ActivityArea activityArea = activityAreaService.findById(activity.getActivityAreaId());
		if(activityArea!=null && activityArea.getSource().equals("1") && activityArea.getType().equals("3")){//推广会场
			productQueryObject.addQuery("firstProductTypeIdsIn", activityArea.getProductTypeGroup());
		}
		productQueryObject.addQuery("mchtId", activity.getMchtId());
		productQueryObject.addQuery("delFlag", Const.FLAG_FALSE);

		if(status == Const.ACTIVITY_PRODUCT_STATUS_WAIT){
			productQueryObject.addQuery("freeProduct", "闲置商品");
			productQueryObject.addQuery("saleType", "1");
			// 活动类型是品牌活动时需要过滤品牌
			boolean isBrand = activityArea.getMchtId() > 0 || Const.ACTIVITY_TYPE_BRAND.equals(activityArea.getType());
			if(isBrand){
				if(activity.getBrandLimitType().equals("1")){
					productQueryObject.addQuery("brandId", activity.getProductBrandId());
				}
			}
			if(activity.getBrandLimitType().equals("1")){
				productQueryObject.addQuery("brandId", activity.getProductBrandId());
			}
			List<String> auditStatusList = new ArrayList<>(2);
			auditStatusList.add("1");
			auditStatusList.add("2");
			productQueryObject.addQuery("auditStatusIn", auditStatusList);
		}else{
			QueryObject queryObject = new QueryObject();
			queryObject.addQuery("activityId", activityId);
			queryObject.addQuery("delFlag", Const.FLAG_FALSE);
			if(status == Const.ACTIVITY_PRODUCT_STATUS_NORMAL){
				queryObject.addQuery("refuseFlag", Const.FLAG_FALSE);
			}else if(status == Const.ACTIVITY_PRODUCT_STATUS_REJECT){
				queryObject.addQuery("refuseFlag", Const.FLAG_TRUE);
			}

			List<ActivityProduct> activityProductList = list(queryObject);
			List<Integer> ids = new ArrayList<>();
			ids.add(0);
			for(ActivityProduct activityProduct : activityProductList){
				ids.add(activityProduct.getProductId());
			}

			productQueryObject.addQuery("ids", ids);
		}
		return productQueryObject;
	}

	public List<Integer> getProductIdsByActivityId(Integer activityId){
		return customDao.getProductIdsByActivityId(activityId);
	}
	
	public void saveSort(HashMap<String, Object> map) {
		customDao.saveSort(map);
	}

	public int getEffectiveProductCount(Map<String, Object> paramMap) {
		return customDao.getEffectiveProductCount(paramMap);
	}

	public List<Integer> getProductIds(Map<String, Object> paramMap) {
		return customDao.getProductIds(paramMap);
	}
	
	public List<ActivityProduct> checkSvipDiscount(Integer activityId){
		return customDao.checkSvipDiscount(activityId);
	}
}
