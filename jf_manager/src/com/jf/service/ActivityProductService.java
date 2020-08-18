package com.jf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jf.common.constant.Const;
import com.jf.entity.ProductUpperLowerLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzs.common.utils.StringUtil;
import com.jf.dao.ActivityProductAuditLogCustomMapper;
import com.jf.dao.ActivityProductAuditLogMapper;
import com.jf.dao.ActivityProductCustomMapper;
import com.jf.dao.ActivityProductMapper;
import com.jf.dao.ProductAuditLogCustomMapper;
import com.jf.dao.ProductAuditLogMapper;
import com.jf.dao.ProductCustomMapper;
import com.jf.dao.ProductItemCustomMapper;
import com.jf.dao.ProductMapper;
import com.jf.entity.ActivityProduct;
import com.jf.entity.ActivityProductAuditLog;
import com.jf.entity.ActivityProductCustom;
import com.jf.entity.ActivityProductCustomExample;
import com.jf.entity.ActivityProductCustomNew;
import com.jf.entity.ActivityProductExample;
import com.jf.entity.Product;
import com.jf.entity.ProductAuditLog;
import com.jf.entity.ProductExample;
import com.jf.entity.ProductItemCustom;
import com.jf.entity.ProductItemExample;
import com.jf.entity.StaffBean;

@Service
@Transactional
public class ActivityProductService extends BaseService<ActivityProduct, ActivityProductExample> {

	@Autowired
	private ActivityProductMapper activityProductMapper;
	
	@Autowired
	private ActivityProductCustomMapper activityProductCustomMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private ActivityProductAuditLogMapper activityProductAuditLogMapper;
	
	@Autowired
	private ProductAuditLogMapper productAuditLogMapper;
	
	@Autowired
	private ProductAuditLogCustomMapper productAuditLogCustomMapper;
	
	@Autowired
	private ActivityProductAuditLogCustomMapper activityProductAuditLogCustomMapper;

	@Autowired
	private ProductUpperLowerLogService productUpperLowerLogService;

	@Autowired
	private ProductService productService;
	@Autowired
	public void setActivityProductMapper(ActivityProductMapper activityProductMapper) {
		super.setDao(activityProductMapper);
		this.activityProductMapper = activityProductMapper;
	}
	
	/*public List<ActivityProductCustom> selectByCustomExample(Map<String, Object> map){
		return activityProductCustomMapper.selectByCustomExample(map);
	}
	
	public Integer countByCustomExample(Map<String, Object> map){
		return activityProductCustomMapper.countByCustomExample(map);
	}*/
	public List<ActivityProductCustom> selectByCustomExample(ActivityProductExample example){
		return activityProductCustomMapper.selectByCustomExample(example);
	}
	
	public Integer countByCustomExample(ActivityProductExample example){
		return activityProductCustomMapper.countByCustomExample(example);
	}
	
	public List<ActivityProductCustom> totalshopCustom(HashMap<String, Object> paramMap) {
		return activityProductCustomMapper.shopcustom(paramMap);
	}
	
	public List<ActivityProductCustomNew> selectActivityProductCustomNewExample(ActivityProductCustomExample activityProductCustomExample) {
		return activityProductCustomMapper.selectActivityProductCustomNewExample(activityProductCustomExample);
	}
	
	public Integer countActivityProductCustomNewExample(ActivityProductCustomExample activityProductCustomExample) {
		return activityProductCustomMapper.countActivityProductCustomNewExample(activityProductCustomExample);
	}
	
	
	/**
	 * 获取活动下的商品列表
	 * @param map
	 * @return
	 */
	public List<ActivityProductCustom> getActivityIdByProductList(Integer activityId,Integer value,String productName,
			Integer staffId,Integer type){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("refuseFlag", value);
		map.put("productName", productName);
		map.put("staffId", staffId);
		map.put("type", type);
		return activityProductCustomMapper.getActivityIdByProductList(map);
	}
	
	/**
	 * 获取活动下的商品列表总条数
	 * @param map
	 * @return
	 */
	public Integer getActivityIdByProductCount(Integer activityId,Integer value,String productName,
			Integer staffId,Integer type){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("refuseFlag", value);
		map.put("productName", productName);
		map.put("staffId", staffId);
		map.put("type", type);
		return activityProductCustomMapper.getActivityIdByProductCount(map);
	}
	
	public Integer getStaffId(Integer productId){
		return activityProductCustomMapper.getStaffId(productId);
	}
	
	public Integer getStaffIdByActivity(Integer activityId){
		return activityProductCustomMapper.getStaffIdByActivity(activityId);
	}
	
	
	/**
	 * 获取活动商品详情
	 * @param activityProductId
	 * @return
	 */
	public ActivityProductCustom selectByActivityProductInfo(Integer activityProductId){
		return activityProductCustomMapper.selectByActivityProductInfo(activityProductId);
	}
	
	public List<ActivityProduct> getActivityProductList(Integer activityId){
		return activityProductCustomMapper.getActivityProductList(activityId);
	}
	
	/**
	 * 设置更新运营专员审核商品通过
	 * @param activityId
	 * @param staffId
	 */
	public void updateOperateAuditAdopt(Integer activityId,Integer staffId){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("staffId", staffId);
		activityProductCustomMapper.updateOperateAuditAdopt(map);
	}
	
	/**
	 * 设置更新设计专员审核商品通过
	 * @param activityId
	 * @param staffId
	 */
	public void updateDesignAuditAdopt(Integer activityId,Integer staffId){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("staffId", staffId);
		activityProductCustomMapper.updateDesignAuditAdopt(map);
	}
	/**
	 * 设置更新法务专员审核商品通过
	 * @param activityId
	 * @param staffId
	 */
	public void updateLawAuditAdopt(Integer activityId,Integer staffId){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("staffId", staffId);
		activityProductCustomMapper.updateLawAuditAdopt(map);
	}
	/**
	 * 设置更新法务专员审核商品通过
	 * @param activityId
	 * @param staffId
	 */
	public void updateCooAuditAdopt(Integer activityId,Integer staffId){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("staffId", staffId);
		activityProductCustomMapper.updateCooAuditAdopt(map);
	}

	public void updateActivityProductList(List<ActivityProduct> activityProducts) {
		for(ActivityProduct activityProduct:activityProducts){
			this.updateByPrimaryKeySelective(activityProduct);
		}
	}

	public List<ActivityProductCustom> getProductsByActivityAreaId(Integer activityAreaId) {
		return activityProductCustomMapper.getProductsByActivityAreaId(activityAreaId);
	}

	public void saveSort(HashMap<String, Object> map) {
		activityProductCustomMapper.saveSort(map);
	}
	
	
	/**
	 * 
	 * @Title selectByCustomNewExample   
	 * @Description TODO(新版品牌特卖审核商品)   
	 * @author pengl
	 * @date 2018年1月15日 下午3:28:19
	 */
	public List<ActivityProductCustomNew> selectByCustomNewExample(ActivityProductCustomExample activityProductCustomExample) {
		return activityProductCustomMapper.selectByCustomNewExample(activityProductCustomExample);
	}
	
	/**
	 * 
	 * @Title updateActivityProduct   
	 * @Description TODO(审核商品)   
	 * @author pengl
	 * @date 2018年1月16日 上午11:13:48
	 */
	public Map<String, Object> updateActivityProduct(HttpServletRequest request, StaffBean staffBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "200";
		String message = "";
		try {
			Integer staffId = Integer.parseInt(staffBean.getStaffID());
			Integer activityProductId = Integer.parseInt(request.getParameter("activityProductId"));
			String statusAudit = request.getParameter("statusAudit"); // 1.专员审核	2.排期审核
			String audit = request.getParameter("audit"); // 2.审核通过	3.已驳回
			Date date = new Date();


			ActivityProductCustomExample activityProductCustomExample = new ActivityProductCustomExample();
			activityProductCustomExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(activityProductId);
			List<ActivityProductCustomNew> activityProductCustomNewList = activityProductCustomMapper.selectByCustomNewExample(activityProductCustomExample);

			String auditStatus = "";
			if(activityProductCustomNewList != null && activityProductCustomNewList.size() > 0) {
				ActivityProductCustomNew activityProductCustomNew = activityProductCustomNewList.get(0);
				ActivityProduct activityProduct = activityProductCustomNewList.get(0);
				activityProduct.setUpdateBy(staffId);
				activityProduct.setUpdateDate(date);
				if(!StringUtil.isEmpty(statusAudit) && statusAudit.equals("1")) { // 1.专员审核
					ProductExample productExample = new ProductExample();
					productExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(activityProduct.getProductId());
					List<Product> productList = productMapper.selectByExample(productExample);
					Product product = null;
					if(productList != null && productList.size() > 0) {
						product = productList.get(0);
						auditStatus = product.getAuditStatus();
					}
					if(activityProduct.getCooAuditStatus() != null && (activityProduct.getCooAuditStatus().equals("2") || activityProduct.getCooAuditStatus().equals("3"))) {
						statusCode = "999";
						message = "对不起，已被排期人员审核，你不能操作。";
					}else if(activityProduct.getRefuseFlag() != null && activityProduct.getRefuseFlag().equals("1")) {
						statusCode = "999";
						message = "对不起，商品已驳回，你不能操作。";
					}else if(product != null && product.getAuditStatus() != null && product.getAuditStatus().equals("3") && 
							!StringUtil.isEmpty(audit) && audit.equals("2")) {
						statusCode = "999";
						message = "对不起，商品已驳回，你不能操作“通过”。";
					}else {
						activityProduct.setOperateAuditBy(staffId);
						activityProduct.setOperateAuditStatus(audit);
						if(!StringUtil.isEmpty(audit) && audit.equals("2")) { // 2.审核通过
							activityProduct.setCooAuditStatus("1");
							if(product != null) {
								product.setStatus("1"); //商品上架
								product.setAuditStatus("2"); //商品表的法务审核状态=通过
								product.setUpdateBy(staffId);
								product.setUpdateDate(date);
								productMapper.updateByPrimaryKeySelective(product); //修改商品信息

								if("1".equals(auditStatus)){
									//商品上下架日志
									ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
									productUpperLowerLog.setProductId(product.getId());
									productUpperLowerLog.setStatus(product.getStatus());
									productUpperLowerLog.setType(Const.PLATFORM);
									productUpperLowerLog.setCreateBy(staffId);
									productUpperLowerLog.setCreateDate(new Date());
									productUpperLowerLogService.insertSelective(productUpperLowerLog);
								}

								ProductAuditLog productAuditLog = new ProductAuditLog();
								productAuditLog.setCreateBy(staffId);
								productAuditLog.setCreateDate(date);
								productAuditLog.setUpdateDate(date);
								productAuditLog.setProductId(product.getId());
								productAuditLog.setStatus("2");
								productAuditLogMapper.insertSelective(productAuditLog);
							}
						}
						if(!StringUtil.isEmpty(audit) && audit.equals("3")) { // 3.已驳回
							activityProduct.setRefuseFlag("1"); // 是否驳回	0：否	1：是
						}
						addActivityProductAuditLog(activityProduct.getId(), "1", activityProduct.getOperateAuditStatus(), staffId, date);
						activityProductMapper.updateByPrimaryKeySelective(activityProduct); //修改活动商品信息
						map.put("productAuditStatus", product.getAuditStatus()); 
						message = "操作成功";
					}
				}
				if(!StringUtil.isEmpty(statusAudit) && statusAudit.equals("2")) { // 2.排期审核
					activityProduct.setCooAuditBy(staffId);
					activityProduct.setCooAuditStatus(audit);
					if(!StringUtil.isEmpty(audit) && audit.equals("2")) { // 2.审核通过
						activityProduct.setRefuseFlag("0"); // 是否驳回	0：否	1：是
						activityProduct.setActivityPrice(activityProductCustomNew.getSalePriceMin());
					}
					if(!StringUtil.isEmpty(audit) && audit.equals("3")) { // 3.已驳回
						activityProduct.setRefuseFlag("1"); // 是否驳回	0：否	1：是
					}
					addActivityProductAuditLog(activityProduct.getId(), "4", activityProduct.getCooAuditStatus(), staffId, date);
					activityProductMapper.updateByPrimaryKeySelective(activityProduct); //修改活动商品信息
					message = "操作成功";
				}
				map.put("audit", audit); 
				map.put("statusAudit", statusAudit); 
				map.put("cooAuditStatus", activityProduct.getCooAuditStatus()); 
				map.put("operateAuditStatus", activityProduct.getOperateAuditStatus()); 
				map.put("activityPrice", activityProductCustomNew.getSalePriceMin()); 
			}
		} catch (Exception e) {
			statusCode = "999";
			message = "系统错误";
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}
	
	/**
	 * 
	 * @Title updateAllActivityProduct   
	 * @Description TODO(品牌特卖：审核、排期的商品批量审核操作)   
	 * @author chengh
	 * @date 2018年5月6日 上午10:53:02
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> updateAllActivityProduct(HttpServletRequest request, StaffBean staffBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "200";
		String message = "";
		try {
			Integer staffId = Integer.parseInt(staffBean.getStaffID());
			List<Integer> activityProductId = (List<Integer>) request.getAttribute("productIdList");
			String statusAudit = (String) request.getAttribute("statusAudit"); // 1.专员审核	2.排期审核
			String audit = (String) request.getAttribute("audit"); // 2.审核通过	3.已驳回
			Date date = new Date();
			
			ActivityProductCustomExample activityProductCustomExample = new ActivityProductCustomExample();
			activityProductCustomExample.createCriteria().andDelFlagEqualTo("0").andIdIn(activityProductId);
			List<ActivityProductCustomNew> activityProductCustomNewList = activityProductCustomMapper.selectByCustomNewExample(activityProductCustomExample);
			//存储productId
			List<Integer> productIdList = new ArrayList<Integer>();
			for (ActivityProductCustomNew activityProductCustomNew : activityProductCustomNewList) {
				productIdList.add(activityProductCustomNew.getProductId());
			}

			//商品
			ProductExample productExample = new ProductExample();
			productExample.createCriteria().andDelFlagEqualTo("0").andIdIn(productIdList);

			if(activityProductCustomNewList != null && activityProductCustomNewList.size() > 0) {
				if(!StringUtil.isEmpty(statusAudit) && statusAudit.equals("1") && !StringUtil.isEmpty(audit) && audit.equals("2")) { // 1.专员审核
					//商品活动
					ActivityProductExample activityProductExample = new ActivityProductExample();
					activityProductExample.createCriteria().andDelFlagEqualTo("0").andIdIn(activityProductId);
					ActivityProduct activityProduct = new ActivityProduct();
					activityProduct.setCooAuditStatus("1");
					activityProduct.setUpdateBy(staffId);
					activityProduct.setUpdateDate(date);
					activityProduct.setOperateAuditBy(staffId);
					activityProduct.setOperateAuditStatus(audit);
					activityProduct.setRefuseFlag("0");
				activityProductMapper.updateByExampleSelective(activityProduct,activityProductExample); //修改活动商品信息

				Product product = new Product();
				product.setStatus("1"); //商品上架
				product.setAuditStatus("2"); //商品表的法务审核状态=通过
				product.setUpdateBy(staffId);
				product.setUpdateDate(date);
				productMapper.updateByExampleSelective(product, productExample);//修改活动信息

				//审核流水表日志
				List<ProductAuditLog> productAuditLogs = new ArrayList<ProductAuditLog>();
				for (Integer productId : productIdList) {
					ProductAuditLog productAuditLog = new ProductAuditLog();
					productAuditLog.setCreateBy(staffId);
					productAuditLog.setCreateDate(date);
					productAuditLog.setUpdateDate(date);
					productAuditLog.setProductId(productId);
					productAuditLog.setStatus("2");
					productAuditLogs.add(productAuditLog);
				}
				productAuditLogCustomMapper.insertProductAuditLogList(productAuditLogs);//新增审核流水信息		
				//活动审核流水表日志
				List<ActivityProductAuditLog> activityProductAuditLogs = new ArrayList<ActivityProductAuditLog>();
				for (ActivityProductCustomNew aProductCustomNew : activityProductCustomNewList) {
					ActivityProductAuditLog activityProductAuditLog = new ActivityProductAuditLog();
					activityProductAuditLog.setActivityProductId(aProductCustomNew.getId());
					activityProductAuditLog.setType("1");
					activityProductAuditLog.setStatus(aProductCustomNew.getOperateAuditStatus());
					activityProductAuditLog.setCreateBy(staffId);
					activityProductAuditLog.setCreateDate(date);
					activityProductAuditLog.setUpdateDate(date);
					activityProductAuditLogs.add(activityProductAuditLog);
				}
				activityProductAuditLogCustomMapper.insertActivityProductAuditLogs(activityProductAuditLogs);//新增活动审核流水信息
				message = "操作成功";
				}else if(!StringUtil.isEmpty(statusAudit) && statusAudit.equals("2") && !StringUtil.isEmpty(audit) && audit.equals("2")){
					//商品活动
					for (ActivityProductCustomNew acCustomNew : activityProductCustomNewList) {
						acCustomNew.setCooAuditBy(staffId);
						acCustomNew.setCooAuditStatus(audit);
						acCustomNew.setRefuseFlag("0"); // 是否驳回	0：否	1：是
						acCustomNew.setActivityPrice(acCustomNew.getSalePriceMin());
						activityProductMapper.updateByPrimaryKeySelective(acCustomNew); //修改活动商品信息
					}

					List<Product> products = productService.selectByExample(productExample);
					if(!products.isEmpty()){
						for (Product product : products){
							if("1".equals(product.getAuditStatus())){
								//商品上下架日志
								ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
								productUpperLowerLog.setProductId(product.getId());
								productUpperLowerLog.setStatus("1");
								productUpperLowerLog.setType(Const.PLATFORM);
								productUpperLowerLog.setCreateBy(staffId);
								productUpperLowerLog.setCreateDate(new Date());
								productUpperLowerLogService.insertSelective(productUpperLowerLog);
							}

						}
					}

					//活动审核流水表日志
					List<ActivityProductAuditLog> activityProductAuditLogs = new ArrayList<ActivityProductAuditLog>();
					for (ActivityProductCustomNew aProductCustomNew : activityProductCustomNewList) {
						ActivityProductAuditLog activityProductAuditLog = new ActivityProductAuditLog();
						activityProductAuditLog.setActivityProductId(aProductCustomNew.getId());
						activityProductAuditLog.setType("4");
						activityProductAuditLog.setStatus(aProductCustomNew.getOperateAuditStatus());
						activityProductAuditLog.setCreateBy(staffId);
						activityProductAuditLog.setCreateDate(date);
						activityProductAuditLog.setUpdateDate(date);
						activityProductAuditLogs.add(activityProductAuditLog);
					}
					activityProductAuditLogCustomMapper.insertActivityProductAuditLogs(activityProductAuditLogs);//新增活动审核流水信息
					message = "操作成功";
				}
			}
		} catch (Exception e) {
			statusCode = "999";
			message = "系统错误";
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}
	
	/**
	 * 
	 * @Title updatePromotionActivityProduct   
	 * @Description TODO(推广商品审核)   
	 * @author pengl
	 * @date 2018年2月8日 下午6:29:53
	 */
	public Map<String, Object> updatePromotionActivityProduct(HttpServletRequest request, StaffBean staffBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "200";
		String message = "";
		try {
			Integer staffId = Integer.parseInt(staffBean.getStaffID());
			Integer activityProductId = Integer.parseInt(request.getParameter("activityProductId"));
			String audit = request.getParameter("audit"); // 2.审核通过	3.已驳回
			Date date = new Date();
			ActivityProductCustomExample activityProductCustomExample = new ActivityProductCustomExample();
			activityProductCustomExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(activityProductId);
			List<ActivityProductCustomNew> activityProductCustomNewList = activityProductCustomMapper.selectByCustomNewExample(activityProductCustomExample);
			if(activityProductCustomNewList != null && activityProductCustomNewList.size() > 0) {
				ActivityProductCustomNew activityProductCustomNew = activityProductCustomNewList.get(0);
				ActivityProduct activityProduct = activityProductCustomNewList.get(0);
				activityProduct.setUpdateBy(staffId);
				activityProduct.setUpdateDate(date);
				activityProduct.setCooAuditBy(staffId);
				activityProduct.setCooAuditStatus(audit);
				if(!StringUtil.isEmpty(audit) && audit.equals("2")) { // 2.审核通过
					activityProduct.setRefuseFlag("0"); // 是否驳回	0：否	1：是
					activityProduct.setActivityPrice(activityProductCustomNew.getSalePriceMin());
					ProductExample productExample = new ProductExample();
					productExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(activityProduct.getProductId());
					List<Product> productList = productMapper.selectByExample(productExample);
					if(productList != null && productList.size() > 0) {
						Product product = productList.get(0);
						product.setStatus("1"); //商品上架
						product.setAuditStatus("2"); //商品表的法务审核状态=通过
						product.setUpdateBy(staffId);
						product.setUpdateDate(date);
						productMapper.updateByPrimaryKeySelective(product); //修改商品信息

						//商品上下架日志
						ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
						productUpperLowerLog.setProductId(product.getId());
						productUpperLowerLog.setStatus(product.getStatus());
						productUpperLowerLog.setType(Const.PLATFORM);
						productUpperLowerLog.setOffReason(product.getOffReason());
						productUpperLowerLog.setCreateBy(staffId);
						productUpperLowerLog.setCreateDate(new Date());
						productUpperLowerLogService.insertSelective(productUpperLowerLog);

						ProductAuditLog productAuditLog = new ProductAuditLog();
						productAuditLog.setCreateBy(staffId);
						productAuditLog.setCreateDate(date);
						productAuditLog.setUpdateDate(date);
						productAuditLog.setProductId(product.getId());
						productAuditLog.setStatus("2");
						productAuditLogMapper.insertSelective(productAuditLog);
						map.put("productAuditStatus", product.getAuditStatus()); 
					}
				}
				if(!StringUtil.isEmpty(audit) && audit.equals("3")) { // 3.已驳回
					activityProduct.setRefuseFlag("1"); // 是否驳回	0：否	1：是
				}
				addActivityProductAuditLog(activityProduct.getId(), "4", activityProduct.getCooAuditStatus(), staffId, date);
				activityProductMapper.updateByPrimaryKeySelective(activityProduct); //修改活动商品信息
				message = "操作成功";
				map.put("audit", audit); 
				map.put("activityPrice", activityProductCustomNew.getSalePriceMin()); 
			}
		} catch (Exception e) {
			statusCode = "999";
			message = "系统错误";
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}
	
	/**
	 * 
	 * @Title addActivityProductAuditLog   
	 * @Description TODO(保存活动商品审核流水)   
	 * @author pengl
	 * @date 2018年1月16日 上午11:10:21
	 */
	public void addActivityProductAuditLog(Integer activityProductId, String type, String status, Integer staffId, Date date) {
		ActivityProductAuditLog activityProductAuditLog = new ActivityProductAuditLog();
		activityProductAuditLog.setActivityProductId(activityProductId);
		activityProductAuditLog.setType(type);
		activityProductAuditLog.setStatus(status);
		activityProductAuditLog.setCreateBy(staffId);
		activityProductAuditLog.setCreateDate(date);
		activityProductAuditLog.setUpdateDate(date);
		activityProductAuditLogMapper.insertSelective(activityProductAuditLog);
	}
	
}
