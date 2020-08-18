package com.jf.service;

import com.gzs.common.utils.StringUtil;
import com.jf.common.constant.Const;
import com.jf.common.utils.StringUtils;
import com.jf.dao.ProductItemMapper;
import com.jf.dao.ProductMapper;
import com.jf.dao.ProductUpperLowerLogMapper;
import com.jf.dao.SeckillBrandGroupProductCustomMapper;
import com.jf.dao.SeckillTimeMapper;
import com.jf.dao.SingleProductActivityAuditLogMapper;
import com.jf.dao.SingleProductActivityCustomMapper;
import com.jf.dao.SingleProductActivityMapper;
import com.jf.entity.Product;
import com.jf.entity.ProductExample;
import com.jf.entity.ProductItem;
import com.jf.entity.ProductItemExample;
import com.jf.entity.ProductUpperLowerLog;
import com.jf.entity.SeckillBrandGroupProduct;
import com.jf.entity.SeckillBrandGroupProductCustomExample;
import com.jf.entity.SeckillTime;
import com.jf.entity.SeckillTimeExample;
import com.jf.entity.SingleProductActivity;
import com.jf.entity.SingleProductActivityAuditLog;
import com.jf.entity.SingleProductActivityCustom;
import com.jf.entity.SingleProductActivityCustomExample;
import com.jf.entity.SingleProductActivityExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName SingleProductActivityService
 * @Description TODO(单品活动)
 * @author pengl
 * @date 2017年9月29日 上午10:56:50
 */
@Service
@Transactional
public class SingleProductActivityService extends BaseService<SingleProductActivity, SingleProductActivityExample> {

	@Autowired
	private SingleProductActivityMapper singleProductActivityMapper;
	
	@Autowired
	private SingleProductActivityCustomMapper singleProductActivityCustomMapper;
	
	@Autowired
	private SingleProductActivityAuditLogMapper singleProductActivityAuditLogMapper;
	
	@Autowired
	private SeckillTimeMapper seckillTimeMapper;

	@Autowired
	private ProductItemMapper productItemMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private SeckillBrandGroupProductCustomMapper seckillBrandGroupProductCustomMapper;

	@Autowired
	private ProductUpperLowerLogMapper productUpperLowerLogMapper;

	@Autowired
	private ProductService productService;
	@Autowired
	public void setSingleProductActivityMapper(SingleProductActivityMapper singleProductActivityMapper) {
		super.setDao(singleProductActivityMapper);
		this.singleProductActivityMapper = singleProductActivityMapper;
	}
	
	public List<SingleProductActivityCustom> selectByCustomExampl(SingleProductActivityCustomExample example) {
		return singleProductActivityCustomMapper.selectByCustomExampl(example);
	}
	
	public Integer countByCustomExample(SingleProductActivityCustomExample example) {
		return singleProductActivityCustomMapper.countByCustomExample(example);
	}
	
	public List<SingleProductActivityCustom> selectBySingleProductActivityProductExampl(SingleProductActivityCustomExample example) {
		return singleProductActivityCustomMapper.selectBySingleProductActivityProductExampl(example);
	}
	
	public Integer countBySingleProductActivityProductExample(SingleProductActivityCustomExample example) {
		return singleProductActivityCustomMapper.countBySingleProductActivityProductExample(example);
	}
	
	public Integer updateByCustomExampleSelective(@Param("record") SingleProductActivity record, @Param("example") SingleProductActivityCustomExample example) {
		return singleProductActivityCustomMapper.updateByCustomExampleSelective(record, example);
	}
	
	public List<SingleProductActivityCustom> selectProductQuantitySumsByProductIds( List<Integer> productIds){
		return singleProductActivityCustomMapper.selectProductQuantitySumsByProductIds(productIds);
	}
	
	public void saveSingleProductActivityAuditLog(Integer staffId, Integer id, BigDecimal activityPrice, 
			BigDecimal comparePrice, String auditStatus, String remarks,String seckillType) {
		SingleProductActivityExample singleProductActivityExample = new SingleProductActivityExample();
		SingleProductActivityExample.Criteria singleProductActivityCriteria = singleProductActivityExample.createCriteria();
		singleProductActivityCriteria.andDelFlagEqualTo("0")
			.andIdEqualTo(id);
		SingleProductActivity singleProductActivity = new SingleProductActivity();
		if(auditStatus.equals("1")) {
			singleProductActivity.setActivityPrice(activityPrice);
			singleProductActivity.setComparePrice(comparePrice);
			singleProductActivity.setIsClose("0");
		}
		singleProductActivity.setAuditStatus(auditStatus);
		if(!auditStatus.equals("1"))
			singleProductActivity.setIsClose("1");
		singleProductActivity.setFirstAuditBy(staffId);
		singleProductActivity.setUpdateBy(staffId);
		singleProductActivity.setUpdateDate(new Date());
		singleProductActivity.setSeckillType(seckillType);
		this.updateByExampleSelective(singleProductActivity, singleProductActivityExample);// 修改单品活动
		
		SingleProductActivityAuditLog singleProductActivityAuditLog = new SingleProductActivityAuditLog();
		singleProductActivityAuditLog.setSingleProductActivityId(id);
		singleProductActivityAuditLog.setStatus(auditStatus);
		singleProductActivityAuditLog.setCreateBy(staffId);
		singleProductActivityAuditLog.setCreateDate(new Date());
		singleProductActivityAuditLog.setUpdateBy(staffId);
		singleProductActivityAuditLog.setUpdateDate(new Date());
		singleProductActivityAuditLog.setRemarks(remarks);
		singleProductActivityAuditLog.setDelFlag("0");
		singleProductActivityAuditLogMapper.insertSelective(singleProductActivityAuditLog);// 保存单品活动审核流水
	}
	
	public void saveScheduleAuditOrRetrial(Integer staffId, Integer id, String flag, String beginTime, 
			String endTime, Integer seckillTimeId, String auditStatus, String remarks, Integer productId,
			Double aPrice, String type,String seckillType,String platformPreferential) {
		SingleProductActivityExample singleProductActivityExample = new SingleProductActivityExample();
		SingleProductActivityExample.Criteria singleProductActivityCriteria = singleProductActivityExample.createCriteria();
		singleProductActivityCriteria.andDelFlagEqualTo("0")
			.andIdEqualTo(id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SingleProductActivity singleProductActivity = new SingleProductActivity();
		try {
			if(flag.equals("1")) {// 秒杀排期
				singleProductActivity.setSeckillTimeId(seckillTimeId);
				if(beginTime != null && !beginTime.equals("") && auditStatus.equals("3")) {
					SeckillTimeExample seckillTimeExample = new SeckillTimeExample();
					seckillTimeExample.createCriteria()
					.andDelFlagEqualTo("0")
//					.andStatusEqualTo("1")
					.andIdEqualTo(seckillTimeId);
					List<SeckillTime> seckillTimeList = seckillTimeMapper.selectByExample(seckillTimeExample);
					if(seckillTimeList != null && seckillTimeList.size() > 0) {
						String beginDate = beginTime + " " + seckillTimeList.get(0).getStartHours() + ":" + seckillTimeList.get(0).getStartMin();
						singleProductActivity.setBeginTime(sdf.parse(beginDate));
						Integer hours = Integer.valueOf(seckillTimeList.get(0).getContinueHours()) * 60 * 60 * 1000;
						Integer min = Integer.valueOf(seckillTimeList.get(0).getContinueMin()) * 60 * 1000;
						Long endDate = sdf.parse(beginDate).getTime() + hours + min;
						singleProductActivity.setEndTime(sdf.parse(sdf.format(endDate)));
					}
				}
			}
			if(flag.equals("2") && auditStatus.equals("3")) {// 新用户、爆款排期
				if(!StringUtil.isEmpty(beginTime))
					singleProductActivity.setBeginTime(sdf.parse(beginTime));
				if(!StringUtil.isEmpty(endTime))
					singleProductActivity.setEndTime(sdf.parse(endTime));
			}
			if(flag.equals("3")) {// 全部报名复审
				singleProductActivity.setIsClose("1");
			}
			singleProductActivity.setScheduleAuditBy(staffId);
			singleProductActivity.setUpdateBy(staffId);
			singleProductActivity.setUpdateDate(new Date());
			singleProductActivity.setAuditStatus(auditStatus);
			if(!StringUtil.isEmpty(type)) {
				singleProductActivity.setType(type);
				if(!type.equals("3")){//限时抢购
					singleProductActivity.setSeckillType("");
				}
			}
			if(!StringUtil.isEmpty(seckillType)) {
				singleProductActivity.setSeckillType(seckillType);
			}
			if(!StringUtil.isEmpty(platformPreferential)) {
				singleProductActivity.setPlatformPreferential(new BigDecimal(platformPreferential));
			}
			this.updateByExampleSelective(singleProductActivity, singleProductActivityExample);// 修改单品活动
			
			if(auditStatus.equals("3")) {
				
				/*ProductItemExample productItemExample = new ProductItemExample();
				productItemExample.createCriteria()
					.andDelFlagEqualTo("0")
					.andProductIdEqualTo(productId);
				ProductItem productItem = new ProductItem();
				productItem.setSalePrice(BigDecimal.valueOf(aPrice));
				productItem.setUpdateDate(new Date());
				productItem.setUpdateBy(staffId);
				productItemMapper.updateByExampleSelective(productItem, productItemExample);// 修改商品SKU 销售价*/	
				
				ProductExample ProductExample = new ProductExample();
				ProductExample.createCriteria().andDelFlagEqualTo("0")
					.andIdEqualTo(productId);
				Product product = new Product();
				product.setStatus("1");
				product.setAuditStatus("2");
				product.setMinSalePrice(BigDecimal.valueOf(aPrice)); // 最低销售价
				product.setUpdateDate(new Date());
				product.setUpdateBy(staffId);

				List<Product> products = productService.selectByExample(ProductExample);
				if(!products.isEmpty()){
					if ("1".equals(products.get(0).getAuditStatus())){
						//商品上下架日志
						ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
						productUpperLowerLog.setProductId(productId);
						productUpperLowerLog.setStatus(product.getStatus());
						productUpperLowerLog.setType(Const.PLATFORM);
						productUpperLowerLog.setCreateBy(staffId);
						productUpperLowerLog.setCreateDate(new Date());
						productUpperLowerLogMapper.insertSelective(productUpperLowerLog);
					}
				}
				productMapper.updateByExampleSelective(product, ProductExample);// 商品上架、通过
			}
			
			SingleProductActivityAuditLog singleProductActivityAuditLog = new SingleProductActivityAuditLog();
			singleProductActivityAuditLog.setSingleProductActivityId(id);
			singleProductActivityAuditLog.setStatus(auditStatus);
			singleProductActivityAuditLog.setCreateBy(staffId);
			singleProductActivityAuditLog.setCreateDate(new Date());
			singleProductActivityAuditLog.setUpdateBy(staffId);
			singleProductActivityAuditLog.setUpdateDate(new Date());
			singleProductActivityAuditLog.setRemarks(remarks);
			singleProductActivityAuditLog.setDelFlag("0");
			singleProductActivityAuditLogMapper.insertSelective(singleProductActivityAuditLog);// 保存单品活动审核流水
			
			if(flag.equals("1")) { //秒杀排期（限时抢购重排时，判断是否删除品牌团中添加商品）
				SeckillBrandGroupProductCustomExample seckillBrandGroupProductCustomExample = new SeckillBrandGroupProductCustomExample();
				SeckillBrandGroupProductCustomExample.SeckillBrandGroupProductCustomCriteria seckillBrandGroupProductCustomCriteria = seckillBrandGroupProductCustomExample.createCriteria();
				seckillBrandGroupProductCustomCriteria.andDelFlagEqualTo("0").andSingleProductActivityIdEqualTo(id);
				if(singleProductActivity.getBeginTime()!=null){
					seckillBrandGroupProductCustomCriteria.andSeckillBrandGroupBeginTimeNotEqualTo(sdf.format(singleProductActivity.getBeginTime()));
				}
				SeckillBrandGroupProduct seckillBrandGroupProduct = new SeckillBrandGroupProduct();
				seckillBrandGroupProduct.setDelFlag("1");
				seckillBrandGroupProduct.setUpdateBy(staffId);
				seckillBrandGroupProduct.setUpdateDate(new Date());
				seckillBrandGroupProductCustomMapper.updateByCustomExampleSelective(seckillBrandGroupProduct, seckillBrandGroupProductCustomExample);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Title saveScheduleAuditOrRetrialCustom   
	 * @Description TODO(自定义排期)   
	 * @author pengl
	 * @date 2018年6月8日 下午6:54:10
	 */
	public void saveScheduleAuditOrRetrialCustom(Integer staffId, Integer id, String beginTime, 
			String endTime, String auditStatus, String remarks, Integer productId, Double aPrice, String type,String seckillType) {
		SingleProductActivityExample singleProductActivityExample = new SingleProductActivityExample();
		SingleProductActivityExample.Criteria singleProductActivityCriteria = singleProductActivityExample.createCriteria();
		singleProductActivityCriteria.andDelFlagEqualTo("0")
			.andIdEqualTo(id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SingleProductActivity singleProductActivity = new SingleProductActivity();
		try {
			if(!StringUtil.isEmpty(beginTime)) {
				singleProductActivity.setBeginTime(sdf.parse(beginTime));
			}
			if(!StringUtil.isEmpty(endTime)) {
				singleProductActivity.setEndTime(sdf.parse(endTime));
			}
			singleProductActivity.setScheduleAuditBy(staffId);
			singleProductActivity.setUpdateBy(staffId);
			singleProductActivity.setUpdateDate(new Date());
			singleProductActivity.setAuditStatus(auditStatus);
			if(!StringUtil.isEmpty(type)) {
				singleProductActivity.setType(type);
			}
			if(!StringUtil.isEmpty(seckillType)) {
				singleProductActivity.setSeckillType(seckillType);
			}
			this.updateByExampleSelective(singleProductActivity, singleProductActivityExample);// 修改单品活动

			if(auditStatus.equals("3")) {
				ProductItemExample productItemExample = new ProductItemExample();
				productItemExample.createCriteria()
					.andDelFlagEqualTo("0")
					.andProductIdEqualTo(productId);
				ProductItem productItem = new ProductItem();
				productItem.setSalePrice(BigDecimal.valueOf(aPrice));
				productItem.setUpdateDate(new Date());
				productItem.setUpdateBy(staffId);
				productItemMapper.updateByExampleSelective(productItem, productItemExample);// 修改商品SKU 销售价
				
				ProductExample ProductExample = new ProductExample();
				ProductExample.createCriteria().andDelFlagEqualTo("0")
					.andIdEqualTo(productId);
				Product product = new Product();
				product.setStatus("1");
				product.setAuditStatus("2");
				product.setUpdateDate(new Date());
				product.setUpdateBy(staffId);
				productMapper.updateByExampleSelective(product, ProductExample);// 商品上架、通过

				//商品上下架日志
				List<Product> products = productService.selectByExample(ProductExample);
				if(!products.isEmpty()) {
					if ("0".equals(products.get(0).getAuditStatus())) {
						//商品上下架日志
						ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
						productUpperLowerLog.setProductId(productId);
						productUpperLowerLog.setStatus(product.getStatus());
						productUpperLowerLog.setType(Const.PLATFORM);
						productUpperLowerLog.setOffReason(product.getOffReason());
						productUpperLowerLog.setCreateBy(staffId);
						productUpperLowerLog.setCreateDate(new Date());
						productUpperLowerLogMapper.insertSelective(productUpperLowerLog);
					}
				}
			}
			
			SingleProductActivityAuditLog singleProductActivityAuditLog = new SingleProductActivityAuditLog();
			singleProductActivityAuditLog.setSingleProductActivityId(id);
			singleProductActivityAuditLog.setStatus(auditStatus);
			singleProductActivityAuditLog.setCreateBy(staffId);
			singleProductActivityAuditLog.setCreateDate(new Date());
			singleProductActivityAuditLog.setUpdateBy(staffId);
			singleProductActivityAuditLog.setUpdateDate(new Date());
			singleProductActivityAuditLog.setRemarks(remarks);
			singleProductActivityAuditLog.setDelFlag("0");
			singleProductActivityAuditLogMapper.insertSelective(singleProductActivityAuditLog);// 保存单品活动审核流水
			
			if(type.equals("3")) { //秒杀排期（限时抢购重排时，判断是否删除品牌团中添加商品）
				SeckillBrandGroupProductCustomExample seckillBrandGroupProductCustomExample = new SeckillBrandGroupProductCustomExample();
				SeckillBrandGroupProductCustomExample.SeckillBrandGroupProductCustomCriteria seckillBrandGroupProductCustomCriteria = seckillBrandGroupProductCustomExample.createCriteria();
				seckillBrandGroupProductCustomCriteria.andDelFlagEqualTo("0").andSingleProductActivityIdEqualTo(id);
				seckillBrandGroupProductCustomCriteria.andSeckillBrandGroupBeginTimeNotEqualTo(sdf.format(singleProductActivity.getBeginTime()));
				SeckillBrandGroupProduct seckillBrandGroupProduct = new SeckillBrandGroupProduct();
				seckillBrandGroupProduct.setDelFlag("1");
				seckillBrandGroupProduct.setUpdateBy(staffId);
				seckillBrandGroupProduct.setUpdateDate(new Date());
				seckillBrandGroupProductCustomMapper.updateByCustomExampleSelective(seckillBrandGroupProduct, seckillBrandGroupProductCustomExample);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void updateSingleProductActivityOrProductIsClose(Integer staffId, List<Integer> list, List<Integer> productIdList) {
		if(list.size() > 0) {
			SingleProductActivityExample singleProductActivityExample = new SingleProductActivityExample();
			SingleProductActivityExample.Criteria singleProductActivityCriteria = singleProductActivityExample.createCriteria();
			singleProductActivityCriteria.andDelFlagEqualTo("0")
				.andIdIn(list);
			SingleProductActivity singleProductActivity = new SingleProductActivity();
			singleProductActivity.setIsClose("1");
			singleProductActivity.setUpdateBy(staffId);
			singleProductActivity.setUpdateDate(new Date());
			singleProductActivityMapper.updateByExampleSelective(singleProductActivity, singleProductActivityExample);
		}
		if(productIdList.size() > 0) {
			ProductExample productExample = new ProductExample();
			ProductExample.Criteria productCriteria = productExample.createCriteria();
			productCriteria.andDelFlagEqualTo("0").andIdIn(productIdList);
			Product product = new Product();
			product.setIsActivity("0");
			product.setUpdateBy(staffId);
			product.setUpdateDate(new Date());
			productMapper.updateByExampleSelective(product, productExample);
		}
	}
	
	/**
	 * 
	 * @Title singleProductActivityClose   
	 * @Description TODO(关闭单品活动)   
	 * @author pengl
	 * @date 2018年9月7日 下午4:56:52
	 */
	public void singleProductActivityClose(Map<String, String> paramMap) {
		Integer mchtId = Integer.parseInt(paramMap.get("mchtId"));
		String productBrandId = paramMap.get("productBrandId");
		Integer staffId = Integer.parseInt(paramMap.get("staffId"));
		Date date = new Date();
		
		//第二版需求（STORY#1058）
		//单品审核状态 = 排期通过 的  要处理： 全部将单品活动的结束时间设置成当前时间
		SingleProductActivityCustomExample singleProductActivityCustomExampleA = new SingleProductActivityCustomExample();
		SingleProductActivityCustomExample.SingleProductActivityCustomCriteria singleProductActivityCustomCriteriaA = singleProductActivityCustomExampleA.createCriteria();
		singleProductActivityCustomCriteriaA.andDelFlagEqualTo("0")
			.andAuditStatusEqualTo("3")
			.andMchtIdEqualTo(mchtId)
			.andEndTimeGreaterThan(date);
		if(!"0".equals(productBrandId)) {
			singleProductActivityCustomCriteriaA.andProductBrandIdEqualTo(productBrandId);
		}
		SingleProductActivity singleProductActivityA = new SingleProductActivity();
		singleProductActivityA.setEndTime(date);
		singleProductActivityA.setUpdateBy(staffId);
		singleProductActivityA.setUpdateDate(date);
		singleProductActivityCustomMapper.updateByCustomExampleSelective(singleProductActivityA, singleProductActivityCustomExampleA);
		//单品审核状态 = 待审的  要处理：将单品设置成 关闭状态=是 单品审核状态 = 初审驳回
		SingleProductActivityCustomExample singleProductActivityCustomExampleB = new SingleProductActivityCustomExample();
		SingleProductActivityCustomExample.SingleProductActivityCustomCriteria singleProductActivityCustomCriteriaB = singleProductActivityCustomExampleB.createCriteria();
		singleProductActivityCustomCriteriaB.andDelFlagEqualTo("0")
			.andAuditStatusEqualTo("0")
			.andMchtIdEqualTo(mchtId);
		if(!"0".equals(productBrandId)) {
			singleProductActivityCustomCriteriaB.andProductBrandIdEqualTo(productBrandId);
		}
		SingleProductActivity singleProductActivityB = new SingleProductActivity();
		singleProductActivityB.setAuditStatus("2");
		singleProductActivityB.setIsClose("1");
		singleProductActivityB.setUpdateBy(staffId);
		singleProductActivityB.setUpdateDate(date);
		singleProductActivityCustomMapper.updateByCustomExampleSelective(singleProductActivityB, singleProductActivityCustomExampleB);
		//单品审核状态 = 初审通过的  要处理：将单品设置成 关闭状态=是 单品审核状态 = 排期驳回
		SingleProductActivityCustomExample singleProductActivityCustomExampleC = new SingleProductActivityCustomExample();
		SingleProductActivityCustomExample.SingleProductActivityCustomCriteria singleProductActivityCustomCriteriaC = singleProductActivityCustomExampleC.createCriteria();
		singleProductActivityCustomCriteriaC.andDelFlagEqualTo("0")
			.andAuditStatusEqualTo("1")
			.andMchtIdEqualTo(mchtId);
		if(!"0".equals(productBrandId)) {
			singleProductActivityCustomCriteriaC.andProductBrandIdEqualTo(productBrandId);
		}
		SingleProductActivity singleProductActivityC = new SingleProductActivity();
		singleProductActivityC.setAuditStatus("4");
		singleProductActivityC.setIsClose("1");
		singleProductActivityC.setUpdateBy(staffId);
		singleProductActivityC.setUpdateDate(date);
		singleProductActivityCustomMapper.updateByCustomExampleSelective(singleProductActivityC, singleProductActivityCustomExampleC);
		
		
		/*//第一版需求
		//待审更改为 初审驳回
		SingleProductActivityCustomExample singleProductActivityCustomExampleA = new SingleProductActivityCustomExample();
		SingleProductActivityCustomExample.SingleProductActivityCustomCriteria singleProductActivityCustomCriteriaA = singleProductActivityCustomExampleA.createCriteria();
		singleProductActivityCustomCriteriaA.andDelFlagEqualTo("0")
			.andAuditStatusEqualTo("0")
			.andMchtIdEqualTo(mchtId);
		if(!StringUtils.isEmpty(productBrandId) && !productBrandId.equals("0")){
			singleProductActivityCustomCriteriaA.andProductBrandIdEqualTo(productBrandId);
		}
		SingleProductActivity singleProductActivityA = new SingleProductActivity();
		singleProductActivityA.setAuditStatus("2");
		singleProductActivityA.setUpdateBy(staffId);
		singleProductActivityA.setUpdateDate(date);
		singleProductActivityCustomMapper.updateByCustomExampleSelective(singleProductActivityA, singleProductActivityCustomExampleA);
		//初始通过、排期通过更改 为 排期驳回
		SingleProductActivityCustomExample singleProductActivityCustomExampleB = new SingleProductActivityCustomExample();
		SingleProductActivityCustomExample.SingleProductActivityCustomCriteria singleProductActivityCustomCriteriaB = singleProductActivityCustomExampleB.createCriteria();
		singleProductActivityCustomCriteriaB.andDelFlagEqualTo("0")
			.andAuditStatusIn(Arrays.asList("1","3"))
			.andMchtIdEqualTo(mchtId);
		if(!StringUtils.isEmpty(productBrandId) && !productBrandId.equals("0")){
			singleProductActivityCustomCriteriaB.andProductBrandIdEqualTo(productBrandId);
		}
		SingleProductActivity singleProductActivityB = new SingleProductActivity();
		singleProductActivityB.setAuditStatus("4");
		singleProductActivityB.setUpdateBy(staffId);
		singleProductActivityB.setUpdateDate(date);
		singleProductActivityCustomMapper.updateByCustomExampleSelective(singleProductActivityB, singleProductActivityCustomExampleB);*/
	}

	public void batchSaveScheduleAuditOrRetrial(Integer staffId, String ids,
			String flag, String beginTime, String endTime,
			Integer seckillTimeId, String auditStatus, String remarks,
			String type, String seckillType) {
		List<Integer> idList = new ArrayList<Integer>();
		if(!StringUtils.isEmpty(ids)){
			for(String id:ids.split(",")){
				idList.add(Integer.parseInt(id));
			}
		}
		if(idList.size()>0){
			SingleProductActivityExample e = new SingleProductActivityExample();
			SingleProductActivityExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0").andIdIn(idList);
			List<SingleProductActivity> list = this.selectByExample(e);
			for(SingleProductActivity singleProductActivity:list){
				if("4".equals(singleProductActivity.getAuditStatus())){//原本是排期驳回的
					continue;
				}else{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					try {
						if(flag.equals("1")) {// 秒杀排期
							if(beginTime != null && !beginTime.equals("") && auditStatus.equals("3")) {
								SeckillTimeExample seckillTimeExample = new SeckillTimeExample();
								seckillTimeExample.createCriteria()
								.andDelFlagEqualTo("0")
//								.andStatusEqualTo("1")
								.andIdEqualTo(seckillTimeId);
								List<SeckillTime> seckillTimeList = seckillTimeMapper.selectByExample(seckillTimeExample);
								if(seckillTimeList != null && seckillTimeList.size() > 0) {
									String beginDate = beginTime + " " + seckillTimeList.get(0).getStartHours() + ":" + seckillTimeList.get(0).getStartMin();
									singleProductActivity.setBeginTime(sdf.parse(beginDate));
									Integer hours = Integer.valueOf(seckillTimeList.get(0).getContinueHours()) * 60 * 60 * 1000;
									Integer min = Integer.valueOf(seckillTimeList.get(0).getContinueMin()) * 60 * 1000;
									Long endDate = sdf.parse(beginDate).getTime() + hours + min;
									singleProductActivity.setEndTime(sdf.parse(sdf.format(endDate)));
								}
							}
						}
						if(flag.equals("2") && auditStatus.equals("3")) {// 新用户、爆款排期
							if(!StringUtil.isEmpty(beginTime))
								singleProductActivity.setBeginTime(sdf.parse(beginTime));
							if(!StringUtil.isEmpty(endTime))
								singleProductActivity.setEndTime(sdf.parse(endTime));
						}
						if(flag.equals("3")) {// 全部报名复审
							singleProductActivity.setIsClose("1");
						}
						singleProductActivity.setScheduleAuditBy(staffId);
						singleProductActivity.setUpdateBy(staffId);
						singleProductActivity.setUpdateDate(new Date());
						singleProductActivity.setAuditStatus(auditStatus);
						if(!StringUtil.isEmpty(type)) {
							singleProductActivity.setType(type);
							if(!type.equals("3")){//限时抢购
								singleProductActivity.setSeckillType("");
							}
						}
						if(!StringUtil.isEmpty(seckillType)) {
							singleProductActivity.setSeckillType(seckillType);
						}
						this.updateByPrimaryKeySelective(singleProductActivity);// 修改单品活动
						
						if(auditStatus.equals("3")) {
							
							ProductExample ProductExample = new ProductExample();
							ProductExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(singleProductActivity.getProductId());
							Product product = new Product();
							product.setStatus("1");
							product.setAuditStatus("2");
							product.setMinSalePrice(singleProductActivity.getActivityPrice()); // 最低销售价
							product.setUpdateDate(new Date());
							product.setUpdateBy(staffId);

							//商品上下架日志
							List<Product> products = productService.selectByExample(ProductExample);
							if(!products.isEmpty()){
								if("1".equals(products.get(0).getAuditStatus())){
									ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
									productUpperLowerLog.setProductId(singleProductActivity.getProductId());
									productUpperLowerLog.setStatus(product.getStatus());
									productUpperLowerLog.setType(Const.PLATFORM);
									productUpperLowerLog.setOffReason(product.getOffReason());
									productUpperLowerLog.setCreateBy(staffId);
									productUpperLowerLog.setCreateDate(new Date());
									productUpperLowerLogMapper.insertSelective(productUpperLowerLog);
								}
							}
							productMapper.updateByExampleSelective(product, ProductExample);// 商品上架、通过
						}
						
						SingleProductActivityAuditLog singleProductActivityAuditLog = new SingleProductActivityAuditLog();
						singleProductActivityAuditLog.setSingleProductActivityId(singleProductActivity.getId());
						singleProductActivityAuditLog.setStatus(auditStatus);
						singleProductActivityAuditLog.setCreateBy(staffId);
						singleProductActivityAuditLog.setCreateDate(new Date());
						singleProductActivityAuditLog.setUpdateBy(staffId);
						singleProductActivityAuditLog.setUpdateDate(new Date());
						singleProductActivityAuditLog.setRemarks(remarks);
						singleProductActivityAuditLog.setDelFlag("0");
						singleProductActivityAuditLogMapper.insertSelective(singleProductActivityAuditLog);// 保存单品活动审核流水
						
						if(flag.equals("1")) { //秒杀排期（限时抢购重排时，判断是否删除品牌团中添加商品）
							SeckillBrandGroupProductCustomExample seckillBrandGroupProductCustomExample = new SeckillBrandGroupProductCustomExample();
							SeckillBrandGroupProductCustomExample.SeckillBrandGroupProductCustomCriteria seckillBrandGroupProductCustomCriteria = seckillBrandGroupProductCustomExample.createCriteria();
							seckillBrandGroupProductCustomCriteria.andDelFlagEqualTo("0").andSingleProductActivityIdEqualTo(singleProductActivity.getId());
							seckillBrandGroupProductCustomCriteria.andSeckillBrandGroupBeginTimeNotEqualTo(sdf.format(singleProductActivity.getBeginTime()));
							SeckillBrandGroupProduct seckillBrandGroupProduct = new SeckillBrandGroupProduct();
							seckillBrandGroupProduct.setDelFlag("1");
							seckillBrandGroupProduct.setUpdateBy(staffId);
							seckillBrandGroupProduct.setUpdateDate(new Date());
							seckillBrandGroupProductCustomMapper.updateByCustomExampleSelective(seckillBrandGroupProduct, seckillBrandGroupProductCustomExample);
						}
						
					} catch (Exception ep) {
						ep.printStackTrace();
					}
				}
			}
		}
	}

    public Integer countSingleProductActivityTrafficStatisticsRealTime(HashMap<String, Object> paraMap) {
		return singleProductActivityCustomMapper.countSingleProductActivityTrafficStatisticsRealTime(paraMap);
    }

	public List<HashMap<String,Object>> selectSingleProductActivityTrafficStatisticsRealTime(HashMap<String, Object> paraMap) {
		return singleProductActivityCustomMapper.selectSingleProductActivityTrafficStatisticsRealTime(paraMap);
	}
}
