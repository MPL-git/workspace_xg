package com.jf.service;

import java.io.IOException;
import java.util.*;

import com.jf.common.base.BaseService;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MchtShopDynamicMapper;
import com.jf.entity.*;
import net.coobird.thumbnailator.geometry.Positions;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.FileUtil;
import com.jf.dao.ProductCustomMapper;
import com.jf.dao.ProductMapper;

/**
 * @author chenwf:
 * @date 创建时间：2017年4月27日 下午4:56:39
 * @version 1.0
 * @parameter
 * @return
 */
@Service
@Transactional
public class ProductService extends BaseService<Product, ProductExample> {

	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductCustomMapper productCustomMapper;

	@Autowired
	private SysParamCfgService sysParamCfgService;

	@Autowired
	private ActivityProductService activityProductService;

	@Autowired
	private ActivityService activityService;
	@Autowired
	private MchtInfoService mchtInfoService;
	@Autowired
	private MchtShopDynamicMapper mchtShopDynamicMapper;

	@Autowired
	public void setProductMapper(ProductMapper productMapper) {
		this.setDao(productMapper);
		this.productMapper = productMapper;
	}

	public String getProductactivityStatus(Integer id) {

		return productCustomMapper.getProductactivityStatus(id);
	}

	public List<Product> getActivityAreaEndProduct(Integer activityAreaId) {

		return productCustomMapper.getActivityAreaEndProduct(activityAreaId);
	}

	public List<Product> getActivityEndProduct(Integer activityAreaId) {

		return productCustomMapper.getActivityEndProduct(activityAreaId);
	}

	public List<Product> getSingleActivityEndProduct(Integer singleActivityId) {

		return productCustomMapper.getSingleActivityEndProduct(singleActivityId);
	}

	public void updateProductActivityStatus(List<Integer> productIds) {

		productCustomMapper.updateProductActivityStatus(productIds);
	}
	
	
	public synchronized void watermarkProductMainPic(Integer activityAreaId, String watermarkPicPath) {

		ActivityExample activityExample = new ActivityExample();
		activityExample.createCriteria().andDelFlagEqualTo("0").andActivityAreaIdEqualTo(activityAreaId);

		if (activityService.countByExample(activityExample) > 0) {
			Map<String, Object> params = new HashMap<String, Object>();
			List<Integer> areaIds = new ArrayList<Integer>();
			areaIds.add(activityAreaId);
			params.put("activityAreaIds", areaIds);
			List<Map<String, Object>> productPicMaps = productCustomMapper.getNoWatermarkProductPic(params);
			List<Integer> activityProductIds = new ArrayList<Integer>();
			if (productPicMaps != null && productPicMaps.size() > 0) {
				for (Map<String, Object> productPicMap : productPicMaps) {
					String pic = (String) productPicMap.get("pic");
					Integer activityId = (Integer) productPicMap.get("activity_id");
					String sourcePic = pic.substring(0, pic.lastIndexOf(".")) + "_" + "70Q" + pic.substring(pic.lastIndexOf("."));
					String sourcePicM = pic.substring(0, pic.lastIndexOf(".")) + "_" + "M" + pic.substring(pic.lastIndexOf("."));
					String target70QMkPic = pic.substring(0, pic.lastIndexOf(".")) + "_70Q_WM_" + activityAreaId + pic.substring(pic.lastIndexOf("."));
					String targetMMkPic = pic.substring(0, pic.lastIndexOf(".")) + "_M_WM_" + activityAreaId + pic.substring(pic.lastIndexOf("."));
					String watermarkPic = watermarkPicPath + "aa_" + activityAreaId + ".png";
					String watermarkPicM = watermarkPicPath + "aa_m_" + activityAreaId + ".png";
					try {
						FileUtil.imgWatermark(sourcePic, target70QMkPic, Positions.BOTTOM_RIGHT, watermarkPic, 1);
						FileUtil.imgWatermark(sourcePicM, targetMMkPic, Positions.BOTTOM_RIGHT, watermarkPicM, 1);
//						activityProductIds.add((Integer) productPicMap.get("activity_product_id"));
						ActivityProduct activityProduct4Update = new ActivityProduct();
						activityProduct4Update.setIsWatermark("1");
//						activityProduct4Update.setCreateBy(1);
						activityProduct4Update.setId((Integer) productPicMap.get("activity_product_id"));
						activityProductService.updateByPrimaryKeySelective(activityProduct4Update);
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
//				if (activityProductIds.size() > 0) {
//					ActivityProductExample activityProductExample = new ActivityProductExample();
//					activityProductExample.createCriteria().andIdIn(activityProductIds);
//					ActivityProduct activityProduct4Update = new ActivityProduct();
//					activityProduct4Update.setIsWatermark("1");
//					activityProductService.updateByExampleSelective(activityProduct4Update, activityProductExample);
//				}
			}
		}

	}
	
	public int offProductWhileNoStock(){
		return productCustomMapper.offProductWhileNoStock();
	}

	public Integer updateSkuLockedAmount(Integer productItemId, Integer quantity) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("productItemId", productItemId);
		params.put("quantity", quantity);
		return productCustomMapper.updateSkuLockedAmount(params);
	}

	public void addMchtShopDynamic() {
		MchtInfoExample mchtInfoExample = new MchtInfoExample();
		mchtInfoExample.createCriteria().andDelFlagEqualTo("0")
				.andStatusEqualTo("1").andShopStatusEqualTo("1");
		List<MchtInfo> mchtInfoList = mchtInfoService.selectByExample(mchtInfoExample);
		for(MchtInfo mchtInfo : mchtInfoList) {
			ProductExample productExample = new ProductExample();
			productExample.createCriteria().andDelFlagEqualTo("0")
					.andStatusEqualTo("1").andSaleTypeEqualTo("1")
					.andMchtIdEqualTo(mchtInfo.getId())
					.andCreateDateBetween(DateUtil.getDateAfterAndBeginTime(DateUtil.getDate(), -1), DateUtil.getDateAfterAndEndTime(DateUtil.getDate(), -1));
			productExample.setOrderByClause(" create_date desc");
			productExample.setLimitStart(0);
			productExample.setLimitSize(9);
			List<Product> productList = productMapper.selectByExample(productExample);
			if(CollectionUtils.isNotEmpty(productList)) {
				StringBuffer productIds = new StringBuffer();
				Date auditDate = productList.get(productList.size()-1).getCreateDate();
				for(Product product : productList) {
					if(!StringUtil.isEmpty(productIds.toString())) {
						productIds.append(",");
					}
					productIds.append(product.getId());
				}
				MchtShopDynamic mchtShopDynamic = new MchtShopDynamic();
				mchtShopDynamic.setMchtId(mchtInfo.getId());
				mchtShopDynamic.setContent("本店上新"+productList.size()+"件商品！！！");
				mchtShopDynamic.setProductIds(productIds.toString());
				mchtShopDynamic.setAuditStatus("1");
				mchtShopDynamic.setAuditDate(auditDate);
				mchtShopDynamic.setAuditBy(1);
				mchtShopDynamic.setCreateDate(DateUtil.getDate());
				mchtShopDynamic.setCreateBy(0);
				mchtShopDynamic.setRemarks("定时任务创建");
				mchtShopDynamic.setDelFlag("0");
				mchtShopDynamicMapper.insertSelective(mchtShopDynamic);
			}
		}
	}


}
