package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.SingleProductActivityProductTypeMapper;
import com.jf.entity.*;
import com.jf.entity.dto.AdDTO;
import com.jf.vo.request.stockclearance.ProductPropRequest;
import com.jf.vo.request.stockclearance.SelectSingleProductActivityProductRequest;
import com.jf.vo.response.stockclearance.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Pengl
 * @create 2020-08-04 下午 3:35
 */
@Service
@Transactional
public class SingleProductActivityProductTypeService extends BaseService<SingleProductActivityProductType, SingleProductActivityProductTypeExample> {

	@Autowired
	private SingleProductActivityProductTypeMapper singleProductActivityProductTypeMapper;

	@Autowired
	private void getSingleProductActivityProductTypeMapper(SingleProductActivityProductTypeMapper singleProductActivityProductTypeMapper) {
		super.setDao(singleProductActivityProductTypeMapper);
		this.singleProductActivityProductTypeMapper = singleProductActivityProductTypeMapper;
	}

	@Autowired
	private AdInfoService adInfoService;

	@Autowired
	private SingleProductActivityService singleProductActivityService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private ProductPropValueService productPropValueService;


	public StockClearanceProductTypeResponse getSingleProductActivityProductType() {
		StockClearanceProductTypeResponse stockClearanceProductTypeResponse = new StockClearanceProductTypeResponse();
		//类目集合
		SingleProductActivityProductTypeExample singleProductActivityProductTypeExample = new SingleProductActivityProductTypeExample();
		singleProductActivityProductTypeExample.createCriteria().andDelFlagEqualTo("0")
				.andTypeEqualTo("1").andStatusEqualTo("1");
		singleProductActivityProductTypeExample.setOrderByClause(" ifnull(seq_no, 99999) asc, id asc");
		List<SingleProductActivityProductType> singleProductActivityProductTypeList = singleProductActivityProductTypeMapper.selectByExample(singleProductActivityProductTypeExample);
		for(SingleProductActivityProductType singleProductActivityProductType : singleProductActivityProductTypeList) {
			ProductTypeView productTypeView = new ProductTypeView();
			productTypeView.setSingleProductActivityProductTypeId(singleProductActivityProductType.getId());
			productTypeView.setProductTypeName(singleProductActivityProductType.getName());
			productTypeView.setProductTypeIdTwo(singleProductActivityProductType.getProductType2Id());
			stockClearanceProductTypeResponse.getProductTypeList().add(productTypeView);
		}
		return stockClearanceProductTypeResponse;
	}

	public StockClearanceProductResponse getSingleProductActivityProduct() {
		StockClearanceProductResponse stockClearanceProductResponse = new StockClearanceProductResponse();
		//广告位集合
		AdInfoExample adInfoExample = new AdInfoExample();
		adInfoExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("1")
				.andCatalogEqualTo(Const.AD_CATALOG_STOCK_CLEARANCE)
				.andPositionEqualTo(Const.AD_POSITION_TOP).andStatusEqualTo("1")
				.andAutoUpDateLessThanOrEqualTo(DateUtil.getDate())
				.andAutoDownDateGreaterThanOrEqualTo(DateUtil.getDate());
		adInfoExample.setOrderByClause(" ifnull(seq_no, 99999) asc, id asc");
		adInfoExample.setLimitStart(0);
		adInfoExample.setLimitSize(5);
		List<AdInfo> adInfoList = adInfoService.selectByExample(adInfoExample);
		for(AdInfo adInfo : adInfoList) {
			AdDTO adDTO = new AdDTO();
			adDTO.setAdId(adInfo.getId().toString());
			adDTO.setLinkType(adInfo.getLinkType());
			adDTO.setLinkId(adInfo.getLinkId());
			adDTO.setLinkUrl(adInfo.getLinkUrl());
			adDTO.setPic(adInfo.getPic());
			adDTO.setType("2");
			Map<String, Object> dataMap = commonService.buildAdMap(adDTO);
			stockClearanceProductResponse.getLinkTypeList().add(dataMap);
		}
		//今日推荐商品集合
		Map<String, Object> param = new HashMap<>();
		param.put("currentPage", 0);
		param.put("pageSize", Const.RETURN_SIZE_10);
		param.put("type", "6");
		param.put("isRecommend", "1");
		List<Map<String, Object>> productMapList  = singleProductActivityService.getSingleBrokenCodeClearingActivityList(param);
		if(productMapList.size() >= 4) {
			stockClearanceProductResponse.setProductList(productMapList);
		}
		return stockClearanceProductResponse;
	}

	public ProductPropResponse getProductPropResponse(ProductPropRequest productPropRequest) {
		ProductPropResponse productPropResponse = new ProductPropResponse();
		SingleProductActivityProductType singleProductActivityProductType = singleProductActivityProductTypeMapper.selectByPrimaryKey(productPropRequest.getSingleProductActivityProductTypeId());
		String productType2Id = "12";
		if(singleProductActivityProductType.getProductType1Id() != null) {
			productType2Id = singleProductActivityProductType.getProductType2Id();
		}
		if(!StringUtil.isEmpty(productType2Id)) {
			Map<String, Object> param = new HashMap<>();
			List<String> productType2Ids = Arrays.asList(productType2Id.split(","));
			List<Integer> productTypeId2List = new ArrayList<>();
			for(String pt2Id : productType2Ids) {
				productTypeId2List.add(Integer.valueOf(pt2Id));
			}
			param.put("productTypeId2List", productTypeId2List);
			String propValIds = singleProductActivityService.getPropValIds(param);
			if(!StringUtil.isEmpty(propValIds)) {
				Map<String, Object> propValIdMap = new HashMap<String, Object>();
				List<String> pvlist = Arrays.asList(propValIds.split(","));
				List<Integer> propValIdList = new ArrayList<>();
				for(String pvId : pvlist) {
					propValIdList.add(Integer.valueOf(pvId));
				}
				propValIdMap.put("propValIdList", propValIdList);
				List<ProductPropValue> productPropValueList = productPropValueService.getProductPropValueModelByIds(propValIdMap);
				for(ProductPropValue productPropValue : productPropValueList) {
					ProductPropView productPropView = new ProductPropView();
					productPropView.setPropValId(productPropValue.getAlias());
					productPropView.setPropValue(productPropValue.getAlias());
					productPropResponse.getProductPropList().add(productPropView);
				}
			}
		}
		return productPropResponse;
	}

	public SelectSingleProductActivityProductResponse getSingleProduct(SelectSingleProductActivityProductRequest selectSingleProductActivityProductRequest) {
		SelectSingleProductActivityProductResponse selectSingleProductActivityProductResponse = new SelectSingleProductActivityProductResponse();
		//今日推荐商品集合
		Map<String, Object> param = new HashMap<>();
		param.put("currentPage", Const.RETURN_SIZE_10*selectSingleProductActivityProductRequest.getCurrentPage());
		param.put("pageSize", Const.RETURN_SIZE_10);
		param.put("type", "6");
		param.put("isRecommend", "1");
		List<Map<String, Object>> productMapList  = singleProductActivityService.getSingleBrokenCodeClearingActivityList(param);
		selectSingleProductActivityProductResponse.setProductList(productMapList);
		return selectSingleProductActivityProductResponse;
	}


	public Map<String, Object> getSingleProductTopInfo() {
		Map<String, Object> map = new HashMap<>();
		SysParamCfg paramCfg = DataDicUtil.getSysParamCfgModel("IS_RECOMMEND_TOP_PIC","");
		if(paramCfg != null && !StringUtil.isBlank(paramCfg.getParamValue()) ) {
			map.put("isRecommendTopPic", StringUtil.getPic(paramCfg.getParamValue(), ""));
		}
		return map;
	}



}


