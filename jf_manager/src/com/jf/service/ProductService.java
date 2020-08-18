package com.jf.service;

import com.gzs.common.utils.StringUtil;
import com.jf.common.constant.Const;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.TaobaoUtil;
import com.jf.dao.ProductCopyLogMapper;
import com.jf.dao.ProductCustomMapper;
import com.jf.dao.ProductMapper;
import com.jf.dao.ThirdPlatformProductInfoMapper;
import com.jf.entity.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class ProductService extends BaseService<Product, ProductExample> {
	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private ProductCustomMapper productCustomMapper;

	@Autowired
	private ProductCopyLogMapper productCopyLogMapper;

	@Autowired
	private CommonService commonService;

	@Autowired
	private ProductPicService productPicService;

	@Autowired
	private ProductDescPicService productDescPicService;

	@Autowired
	private ProductItemService productItemService;

	@Autowired
	private ProductWeightService productWeightService;
	
	@Autowired
	private ProductBrandService productBrandService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	@Autowired
	private SingleProductActivityService singleProductActivityService;
	
	
	@Autowired
	private ThirdPlatformProductInfoMapper thirdPlatformProductInfoMapper;
	
	@Autowired
	private WeitaoChannelProductService weitaoChannelProductService;

	@Autowired
	private ProductUpperLowerLogService productUpperLowerLogService;

	@Autowired
	private ProductExtendService productExtendService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private CommentPicService commentPicService;
	@Autowired
	public void setProductMapper(ProductMapper productMapper) {
		super.setDao(productMapper);
		this.productMapper = productMapper;
	}

	public void updateSeckillSkuPrice(Integer id, Integer productId, HttpServletRequest request) {
		
		Product product = new Product();
		product.setId(productId);
		product.setMinSalePrice(new BigDecimal(request.getParameter("seckillPrice")));
		updateByPrimaryKeySelective(product);
		BigDecimal seckillPrice=new BigDecimal(request.getParameter("seckillPrice")); 
		productItemService.updateByProductId(seckillPrice,productId);
		SingleProductActivity singleProductActivity = new SingleProductActivity();
		singleProductActivity.setActivityPrice(seckillPrice);
		singleProductActivity.setId(id);
		singleProductActivityService.updateByPrimaryKeySelective(singleProductActivity);
	}
	
	public int countProductCustomByExample(ProductCustomExample example) {
		return productCustomMapper.countByExample(example);
	}

	//爆款推荐列表
	public List<ProductCustom> selectRecommendedProductCustomByExample(ProductCustomExample example) {
		return productCustomMapper.selectRecommendedProductCustomByExample(example);
	}

	public List<ProductCustom> selectProductCustomByExample(ProductCustomExample example) {
		return productCustomMapper.selectByExample(example);
	}

	public ProductCustom selectProductCustomByPrimaryKey(Integer id) {
		return productCustomMapper.selectByPrimaryKey(id);
	}

	public List<ProductProp> getProductPropByProductId(Integer productId) {
		return productCustomMapper.getProductPropByProductId(productId);
	}

	public List<?> getProductPropNameByProductId(Integer productId) {
		return productCustomMapper.getProductPropNameByProductId(productId);
	}

	public String getActivityIdsByProductId(Integer productId) {
		return productCustomMapper.getActivityIdsByProductId(productId);
	}

	public List<HashMap<String, Object>> getLastActivityIdByProductId(Integer productId) {
		return productCustomMapper.getLastActivityIdByProductId(productId);
	}

	public List<HashMap<String, Object>> getLastSingleProductActivityIdByProductId(Integer productId) {
		return productCustomMapper.getLastSingleProductActivityIdByProductId(productId);
	}

	public List<Integer> getProductIds(HashMap<String, Object> paramMap) {
		return productCustomMapper.getProductIds(paramMap);
	}

	public List<Integer> getProductIdsByCode(String codeStr) {
		return productCustomMapper.getProductIdsByCode(codeStr);
	}

	public String getProductTypeAllChild(Integer productTypeId) {
		return productCustomMapper.getProductTypeAllChild(productTypeId);
	}

	public List<HashMap<String, Object>> getProductSortList(ProductCustomExample pce) {
		return productCustomMapper.getProductSortList(pce);
	}

	public int productCopy(Integer targetMchtId, List<Integer> productIds, Integer copyBy,String copyMoreTag) {

		if (productIds == null || productIds.size() == 0 || targetMchtId == null) {
			return 0;
		}

		ProductExample productExample = new ProductExample();
		ProductExample.Criteria productCriteria = productExample.createCriteria();
		productCriteria.andDelFlagEqualTo("0").andIdIn(productIds);

		List<Integer> productIdLogs = productCustomMapper.selectProdutCopyLog(targetMchtId, productIds);

		if (productIdLogs != null && productIdLogs.size() > 0) {
			productCriteria.andIdNotIn(productIdLogs);
		}

		List<Product> sourceProducts = productMapper.selectByExampleWithBLOBs(productExample);
		int i = 0;
		int totalCount = sourceProducts.size();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDateString = sdf.format(new Date());
		String batchCode = generateProductCode();

		Date now = new Date();

		if (sourceProducts != null) {
			for (Product sourceProduct : sourceProducts) {
				i++;
				System.out.println("复制商品" + i + "/" + totalCount + "id:" + sourceProduct.getId());
				Product targetProduct = new Product();

				targetProduct.setMchtId(Integer.valueOf(targetMchtId));
				targetProduct.setCode(generateProductCode());
				targetProduct.setSource(sourceProduct.getSource());
				targetProduct.setName(sourceProduct.getName());
				targetProduct.setStatus("0");
				targetProduct.setOffReason("3");
				targetProduct.setAuditStatus(sourceProduct.getAuditStatus());
				targetProduct.setAuditBy(sourceProduct.getAuditBy());
				targetProduct.setAuditRemarks(sourceProduct.getAuditRemarks());
				targetProduct.setBrandId(sourceProduct.getBrandId());
				targetProduct.setProductTypeId(sourceProduct.getProductTypeId());
				targetProduct.setProductType1Id(sourceProduct.getProductType1Id());
				targetProduct.setProductType2Id(sourceProduct.getProductType2Id());
				targetProduct.setArtNo(sourceProduct.getArtNo());
				targetProduct.setSuitSex(sourceProduct.getSuitSex());
				targetProduct.setSuitGroup(sourceProduct.getSuitGroup());
				targetProduct.setYear(sourceProduct.getYear());
				targetProduct.setSeason(sourceProduct.getSeason());
				targetProduct.setProductDesc(sourceProduct.getProductDesc());
				targetProduct.setLimitBuy(0);
				targetProduct.setCsTempletId(null);
				targetProduct.setRemarksColor(sourceProduct.getRemarksColor());
				targetProduct.setSaleType(sourceProduct.getSaleType());
				targetProduct.setDelDate(null);
				targetProduct.setShelfLife(sourceProduct.getShelfLife());
				targetProduct.setIsSingleProp(sourceProduct.getIsSingleProp());
				targetProduct.setShopProductCustomTypeIdGroup(null);
				targetProduct.setWeights(0);
				targetProduct.setIsActivity("0");
				targetProduct.setIsShow(sourceProduct.getIsShow());
				targetProduct.setMinSalePriceItemId(null);
				targetProduct.setMinPriceItemId(null);
				targetProduct.setMinMallPrice(null);
				targetProduct.setMinSalePrice(null);
				targetProduct.setMinTagPrice(null);
				targetProduct.setSaleWeight(0);
				targetProduct.setFreightTemplateId(null);
				targetProduct.setIsShopShow(sourceProduct.getIsShopShow());
				targetProduct.setCreateBy(copyBy);
				targetProduct.setCreateDate(new Date());
				targetProduct.setUpdateBy(null);
				targetProduct.setUpdateDate(null);
				// targetProduct.setRemarks(nowDateString+"批次："+batchCode+"复制商家id为:"+sourceMchtId+"的商品（id）"+sourceProduct.getId());
				targetProduct.setRemarks(sourceProduct.getRemarks());
				targetProduct.setDelFlag("0");
				targetProduct.setSearchFields(sourceProduct.getSearchFields());
				if(StringUtils.equals("1",copyMoreTag)){
					targetProduct.setVirtualSales(commentService.selectSaleQuantity(sourceProduct.getId()));
					targetProduct.setVirtualPayers(commentService.selectPayers(sourceProduct.getId()));
				}
				this.insertSelective(targetProduct);

				//复制食品超市
				ProductExtendExample productExtendExample = new ProductExtendExample();
				productExtendExample.createCriteria().andProductIdEqualTo(sourceProduct.getId());
				List<ProductExtend> productExtends = productExtendService.selectByExample(productExtendExample);
				if(!productExtends.isEmpty()){
					ProductExtend productExtend = productExtends.get(0);
					ProductExtend productExtendCopy = new ProductExtend();
					productExtendCopy.setProductId(targetProduct.getId());
					productExtendCopy.setStorageConditions(productExtend.getStorageConditions());
					productExtendCopy.setPlaceOfOrigin(productExtend.getPlaceOfOrigin());
					productExtendCopy.setFoodLabelPic(productExtend.getFoodLabelPic());
					productExtendCopy.setProducerInformation(productExtend.getProducerInformation());
					productExtendCopy.setApprovalNumber(productExtend.getApprovalNumber());
					productExtendCopy.setCreateBy(copyBy);
					productExtendCopy.setCreateDate(new Date());
					productExtendService.insertSelective(productExtendCopy);
				}

				//商品上下架日志
				ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
				productUpperLowerLog.setProductId(targetProduct.getId());
				productUpperLowerLog.setStatus("0");
				productUpperLowerLog.setType(Const.PLATFORM);
				productUpperLowerLog.setOffReason("3");
				productUpperLowerLog.setCreateBy(copyBy);
				productUpperLowerLog.setCreateDate(new Date());
				productUpperLowerLogService.insertSelective(productUpperLowerLog);

				// 复制主图
				ProductPicExample productPicExample = new ProductPicExample();
				productPicExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(sourceProduct.getId());
				List<ProductPic> productPics = productPicService.selectByExample(productPicExample);
				if (productPics != null) {
					for (ProductPic sourceProductPic : productPics) {
						ProductPic targetProductPic = new ProductPic();
						targetProductPic.setProductId(targetProduct.getId());
						targetProductPic.setPic(sourceProductPic.getPic());
						targetProductPic.setIsDefault(sourceProductPic.getIsDefault());
						targetProductPic.setSeqNo(sourceProductPic.getSeqNo());
						targetProductPic.setCreateBy(copyBy);
						targetProductPic.setCreateDate(new Date());
						targetProductPic.setUpdateBy(null);
						targetProductPic.setUpdateDate(null);
						targetProductPic.setRemarks(nowDateString + "批次：" + batchCode + "复制id：" + sourceProductPic.getId());
						targetProductPic.setDelFlag("0");
						productPicService.insertSelective(targetProductPic);

					}
				}

				// 复制商品详情图
				ProductDescPicExample productDescPicExample = new ProductDescPicExample();
				productDescPicExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(sourceProduct.getId());
				List<ProductDescPic> productDescPics = productDescPicService.selectByExample(productDescPicExample);
				if (productDescPics != null) {
					for (ProductDescPic sourceProductDescPic : productDescPics) {
						ProductDescPic targetProductDescPic = new ProductDescPic();
						targetProductDescPic.setProductId(targetProduct.getId());
						targetProductDescPic.setPic(sourceProductDescPic.getPic());
						targetProductDescPic.setSeqNo(sourceProductDescPic.getSeqNo());
						targetProductDescPic.setCreateBy(copyBy);
						targetProductDescPic.setCreateDate(new Date());
						targetProductDescPic.setUpdateBy(null);
						targetProductDescPic.setUpdateDate(null);
						targetProductDescPic.setRemarks(nowDateString + "批次：" + batchCode + "复制id:" + sourceProductDescPic.getId());
						targetProductDescPic.setDelFlag("0");
						productDescPicService.insertSelective(targetProductDescPic);
					}
				}

				// 复制sku
				ProductItemExample productItemExample = new ProductItemExample();
				productItemExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(sourceProduct.getId());
				List<ProductItem> sourceProductItems = productItemService.selectByExample(productItemExample);
				if (sourceProductItems != null) {
					for (ProductItem sourceProductItem : sourceProductItems) {
						ProductItem targetProductItem = new ProductItem();
						targetProductItem.setProductId(targetProduct.getId());
						targetProductItem.setSku(sourceProductItem.getSku());
						targetProductItem.setPropValId(sourceProductItem.getPropValId());
						targetProductItem.setStock(sourceProductItem.getStock());
						targetProductItem.setLockedAmount(0);
						targetProductItem.setTagPrice(sourceProductItem.getTagPrice());
						targetProductItem.setMallPrice(sourceProductItem.getMallPrice());
						targetProductItem.setSalePrice(sourceProductItem.getSalePrice());
						targetProductItem.setCostPrice(sourceProductItem.getCostPrice());
						targetProductItem.setPic(sourceProductItem.getPic());
						targetProductItem.setCreateBy(copyBy);
						targetProductItem.setCreateDate(new Date());
						targetProductItem.setUpdateBy(null);
						targetProductItem.setUpdateDate(null);
						targetProductItem.setRemarks(nowDateString + "批次：" + batchCode + "复制id:" + sourceProductItem.getId());
						targetProductItem.setDelFlag("0");
						productItemService.insertSelective(targetProductItem);
					}
				}

				// 将商城价最低的sku id 设置到商品表
				if (targetProduct.getId() != null) {
					resetProductMinPrice(targetProduct.getId());
				}

				// 更新商品权重
				if(StringUtils.equals("1",copyMoreTag)){
					productWeightService.updateProductWeights(targetProduct.getId(),sourceProduct);
					//评论复制
					List<Comment> comments = commentService.selectByPropDescCustomExample(sourceProduct.getId());
					if(!comments.isEmpty()){
						for (Comment comment : comments){
							CommentPicExample commentPicExample = new CommentPicExample();
							commentPicExample.createCriteria().andCommentIdEqualTo(comment.getId());
							List<CommentPic> commentPics = commentPicService.selectByExample(commentPicExample);

							comment.setMchtId(0);
							comment.setProductId(targetProduct.getId());
							comment.setSubOrderId(0);
							comment.setOrderDtlId(0);
							comment.setCreateBy(copyBy);
							commentService.insertSelective(comment);

							if (!commentPics.isEmpty()){
								for (CommentPic commentPic : commentPics){
									commentPic.setCommentId(comment.getId());
									commentPic.setCreateBy(copyBy);
									commentPic.setCreateDate(new Date());
								}
								commentPicService.insertBatch(commentPics);
							}
						}
					}
				}else{
					productWeightService.updateProductWeights(targetProduct.getId(),null);
				}

				ProductCopyLog productCopyLog = new ProductCopyLog();
				productCopyLog.setProductId(sourceProduct.getId());
				productCopyLog.setTargetMchtId(targetMchtId);
				productCopyLog.setCreateDate(now);
				productCopyLog.setCreateBy(copyBy);
				productCopyLogMapper.insertSelective(productCopyLog);

			}
		}

		return sourceProducts == null ? 0 : sourceProducts.size();

	}

	public void resetProductMinPrice(Integer productId) {
		//最小商城价规格
		ProductItem minPriceProductItem = productItemService.getMinMallPriceItem(productId);
		//最小活动价规格
		ProductItem minSalePriceProductItem = productItemService.getMinSalePriceItem(productId);

		Product product4update = new Product();
		product4update.setId(productId);
		product4update.setMinPriceItemId(minPriceProductItem.getId());
		product4update.setMinMallPrice(minPriceProductItem.getMallPrice());
		product4update.setMinSalePrice(minPriceProductItem.getSalePrice());
		product4update.setMinTagPrice(minPriceProductItem.getTagPrice());
		product4update.setMinSalePriceItemId(minSalePriceProductItem.getId());

		updateByPrimaryKeySelective(product4update);
	}

	public void createTaobaokeProduct(JSONObject taobaokeProduct, Integer productTypeId, Integer wetaoChannelId) {

		String numIid = taobaokeProduct.get("item_id").toString();
		ProductExample productExample = new ProductExample();
		ProductExample.Criteria criteria=productExample.createCriteria();
		criteria.andRefIdEqualTo(numIid);
		if("0".equals(taobaokeProduct.getString("user_type"))){
			criteria.andSourceEqualTo("1");
		}
		if("1".equals(taobaokeProduct.getString("user_type"))){
			criteria.andSourceEqualTo("2");
		}
		
		if (this.countByExample(productExample) > 0) {
			List<Product> products = this.selectByExample(productExample);
			Product product = products.get(0);
			ProductType productType3 = productTypeService.selectByPrimaryKey(productTypeId);
			ProductType productType2 = productTypeService.selectByPrimaryKey(productType3.getParentId());
			ProductType productType1=productTypeService.selectByPrimaryKey(productType2.getParentId());
			product.setProductType2Id(productType2.getId());
			product.setProductType1Id(productType2.getParentId());
			product.setProductTypeId(productTypeId);
			//设置搜索域字段 = 商品名称+商品品牌名称+品牌中文+品牌英文+商品名称+商品所属一级品类+商品所属二级品类+商品所属三级级品类+商品货号+商品适用性别
			ProductBrand productBrand = productBrandService.selectByPrimaryKey(product.getBrandId());
			String searchFields=productBrand.getName()+(StringUtil.isEmpty(productBrand.getNameZh())?"":(";"+productBrand.getNameZh()))+(StringUtil.isEmpty(productBrand.getNameEn())?"":(";"+productBrand.getNameEn()))
					            + ";"+product.getName()+";"+productType1.getName()+";"+productType2.getName()+";"+productType3.getName();
			product.setSearchFields(searchFields);
			product.setThirdPlatformCouponInfo(JsonUtils.getString(taobaokeProduct, "coupon_info"));
			this.updateByPrimaryKeySelective(product);
			
			ThirdPlatformProductInfo thirdPlatformProductInfo = new ThirdPlatformProductInfo();
			thirdPlatformProductInfo.setSearchFields(searchFields);
			thirdPlatformProductInfo.setShopTitle(JsonUtils.getString(taobaokeProduct, "shop_title"));
			thirdPlatformProductInfo.setReservePrice(taobaokeProduct.has("reserve_price")?new BigDecimal(JsonUtils.getString(taobaokeProduct, "reserve_price")):null);
			thirdPlatformProductInfo.setZkFinalPrice(taobaokeProduct.has("zk_final_price")?new BigDecimal(JsonUtils.getString(taobaokeProduct, "zk_final_price")):null);
			thirdPlatformProductInfo.setTitle(JsonUtils.getString(taobaokeProduct, "title"));
			thirdPlatformProductInfo.setNick(JsonUtils.getString(taobaokeProduct, "nick"));
			thirdPlatformProductInfo.setSellerId(JsonUtils.getString(taobaokeProduct, "seller_id"));
			thirdPlatformProductInfo.setVolume(taobaokeProduct.has("volume")?Integer.valueOf(JsonUtils.getString(taobaokeProduct, "volume")):null);
//			thirdPlatformProductInfo.setItemUrl(JsonUtils.getString(taobaokeProduct,"item_url"));
			thirdPlatformProductInfo.setCouponTotalCount(taobaokeProduct.has("coupon_total_count")?Integer.valueOf(JsonUtils.getString(taobaokeProduct, "coupon_total_count")):null);
			if(taobaokeProduct.has("commission_rate")){
				thirdPlatformProductInfo.setCommissionRate(JsonUtils.getString(taobaokeProduct, "commission_rate"));
			}else{
				if(taobaokeProduct.has("tk_rate")){
					String tkRate = JsonUtils.getString(taobaokeProduct, "tk_rate");//2.58
					if(tkRate.indexOf(".")>0){
						tkRate = tkRate.substring(0, tkRate.lastIndexOf("."))+tkRate.substring(tkRate.lastIndexOf(".")+1);//258
					}
					thirdPlatformProductInfo.setCommissionRate(tkRate);
				}
			}
			thirdPlatformProductInfo.setCouponInfo(JsonUtils.getString(taobaokeProduct, "coupon_info"));
			thirdPlatformProductInfo.setCategory(taobaokeProduct.has("category_id")?Integer.valueOf(JsonUtils.getString(taobaokeProduct, "category_id")):null);
			thirdPlatformProductInfo.setCouponRemainCount(taobaokeProduct.has("coupon_remain_count")?Integer.valueOf(JsonUtils.getString(taobaokeProduct, "coupon_remain_count")):null);
			thirdPlatformProductInfo.setCouponStartTime(JsonUtils.getString(taobaokeProduct, "coupon_start_time"));
			thirdPlatformProductInfo.setCouponEndTime(JsonUtils.getString(taobaokeProduct, "coupon_end_time"));
			thirdPlatformProductInfo.setCouponId(JsonUtils.getString(taobaokeProduct, "coupon_id"));
			thirdPlatformProductInfo.setCouponAmount(taobaokeProduct.has("coupon_amount")?new BigDecimal(taobaokeProduct.getString("coupon_amount")):null);
			thirdPlatformProductInfo.setCouponStartFee(taobaokeProduct.has("coupon_start_fee")?new BigDecimal(taobaokeProduct.getString("coupon_start_fee")):null);


			if(taobaokeProduct.has("coupon_click_url")){//好券购物清单接口
				if(!taobaokeProduct.getString("coupon_click_url").contains("http")) {
					thirdPlatformProductInfo.setCouponClickUrl("https:"+JsonUtils.getString(taobaokeProduct, "coupon_click_url"));
				}else {
					thirdPlatformProductInfo.setCouponClickUrl(JsonUtils.getString(taobaokeProduct, "coupon_click_url"));
				}
			}else if(taobaokeProduct.has("coupon_share_url")){//通用物料接口
				String couponUrl = JsonUtils.getString(taobaokeProduct, "coupon_share_url");
				if(!couponUrl.contains("http")){
					thirdPlatformProductInfo.setCouponClickUrl("https:"+ JsonUtils.getString(taobaokeProduct, "coupon_share_url"));
				}else{
					thirdPlatformProductInfo.setCouponClickUrl(JsonUtils.getString(taobaokeProduct, "coupon_share_url"));
				}
			}
			thirdPlatformProductInfo.setIsCoupon(!StringUtil.isEmpty(thirdPlatformProductInfo.getCouponClickUrl())?"1":"0");
			thirdPlatformProductInfo.setItemDescription(JsonUtils.getString(taobaokeProduct, "item_description"));
			thirdPlatformProductInfo.setUpdateDate(new Date());
			thirdPlatformProductInfo.setProductType1Id(productType2.getParentId());
			thirdPlatformProductInfo.setProductType2Id(productType2.getId());
			thirdPlatformProductInfo.setProductType3Id(productTypeId);
			ThirdPlatformProductInfoExample thirdPlatformProductInfoExample = new ThirdPlatformProductInfoExample();
			thirdPlatformProductInfoExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(product.getId()).andRefIdEqualTo(product.getRefId());
			thirdPlatformProductInfoMapper.updateByExampleSelective(thirdPlatformProductInfo, thirdPlatformProductInfoExample);
			//先删除全部主图
			ProductPic productPic = new ProductPic();
			productPic.setUpdateDate(new Date());
			productPic.setDelFlag("1");
			ProductPicExample ppe = new ProductPicExample();
			ppe.createCriteria().andProductIdEqualTo(product.getId()).andDelFlagEqualTo("0");
			productPicService.updateByExampleSelective(productPic, ppe);
			//插入主图
			productPic = new ProductPic();
			productPic.setProductId(product.getId());
			if(taobaokeProduct.getString("pict_url").indexOf("alicdn")>0 && !taobaokeProduct.getString("pict_url").contains("http"))
			{
				productPic.setPic("https:"+taobaokeProduct.getString("pict_url"));
			}else {
				productPic.setPic(taobaokeProduct.getString("pict_url"));
			}
			productPic.setIsDefault("1");
			productPic.setSeqNo(1);
			productPic.setCreateBy(null);
			productPic.setCreateDate(new Date());
			productPic.setUpdateBy(null);
			productPic.setUpdateDate(null);
			productPic.setRemarks(null);
			productPic.setDelFlag("0");
			productPicService.insertSelective(productPic);
			if(taobaokeProduct.has("small_images")){
				JSONObject smallImagesObject = taobaokeProduct.getJSONObject("small_images");
				if(!smallImagesObject.isEmpty() && !smallImagesObject.isNullObject() && smallImagesObject.size()>0){
					JSONArray smallImages = smallImagesObject.getJSONArray("string");
					if(smallImages!=null&&smallImages.size()>0){
						for (int i = 0; i < smallImages.size(); i++) {
							productPic = new ProductPic();
							productPic.setProductId(product.getId());
							if(smallImages.getString(i).indexOf("alicdn") >0 && !smallImages.getString(i).contains("http")) {
								productPic.setPic("https:"+smallImages.getString(i));
							}else{
								productPic.setPic(smallImages.getString(i));
							}
							productPic.setIsDefault("0");
							productPic.setSeqNo(i+2);
							productPic.setCreateBy(null);
							productPic.setCreateDate(new Date());
							productPic.setUpdateBy(null);
							productPic.setUpdateDate(null);
							productPic.setRemarks(null);
							productPic.setDelFlag("0");
							productPicService.insertSelective(productPic);
						}
					}
				}
			}
			
			//更新sku
			ProductItem productItem = new ProductItem();

			String s = taobaokeProduct.containsKey("reserve_price") ? taobaokeProduct.getString("reserve_price") : taobaokeProduct.containsKey("zk_final_price")?taobaokeProduct.getString("zk_final_price"):null;
			if(s != null){
				productItem.setTagPrice(new BigDecimal(s));
			}
			String s1 = taobaokeProduct.containsKey("zk_final_price")?taobaokeProduct.getString("zk_final_price"):null;
			if(s1 != null) {
				productItem.setMallPrice(new BigDecimal(s1));
				productItem.setSalePrice(new BigDecimal(s1));
			}else{
				productItem.setSalePrice(new BigDecimal("0.00"));
			}
			productItem.setPic(taobaokeProduct.getString("pict_url"));
			productItem.setUpdateDate(new Date());
			ProductItemExample pie = new ProductItemExample();
			pie.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(product.getId());
			productItemService.updateByExampleSelective(productItem, pie);
			
			// 将商城价最低的sku id 设置到商品表
			if (product.getId() != null) {
				resetProductMinPrice(product.getId());
			}
			// 更新商品权重
			productWeightService.updateProductWeights(product.getId(),null);
			
			//更新频道
			if(wetaoChannelId!=-1){
			WetaoChannelProductExample wcp = new WetaoChannelProductExample();
			wcp.createCriteria().andProductIdEqualTo(product.getId()).andDelFlagEqualTo("0");
			List<WetaoChannelProduct> wcps = weitaoChannelProductService.selectByExample(wcp);
			if(wcps.size()>0){
				WetaoChannelProduct wetaoChannelProduct = wcps.get(0);
				wetaoChannelProduct.setChannelId(wetaoChannelId);
				wetaoChannelProduct.setUpdateDate(new Date());
				weitaoChannelProductService.updateByPrimaryKeySelective(wetaoChannelProduct);
			/*	WetaoChannelProductExample wetaoChannelProductExample = new WetaoChannelProductExample();
				wetaoChannelProductExample.createCriteria().andProductIdEqualTo(product.getId()).andDelFlagEqualTo("0");
				weitaoChannelProductService.updateByExampleSelective(wetaoChannelProduct, wetaoChannelProductExample );		*/
			}else{
				WetaoChannelProduct wetaoChannelProduct = new WetaoChannelProduct();
				wetaoChannelProduct.setProductId(product.getId());
				wetaoChannelProduct.setChannelId(wetaoChannelId);
				wetaoChannelProduct.setCreateDate(new Date());
				wetaoChannelProduct.setDelFlag("0");
				weitaoChannelProductService.insertSelective(wetaoChannelProduct);
				
				}
			}
			
		}else{
			Date now = new Date();

			Product product = new Product();

			product.setMchtId(TaobaoUtil.mchtId);//TODO 设置淘宝客的商家id
			product.setCode(generateProductCode());
			
			if("0".equals(taobaokeProduct.getString("user_type"))){
				product.setSource("1");
			}
			if("1".equals(taobaokeProduct.getString("user_type"))){
				product.setSource("2");
			}
			product.setRefId(taobaokeProduct.get("item_id").toString());
			
			product.setName(taobaokeProduct.getString("title"));
			product.setStatus("1");
			product.setOffReason("3");
			product.setAuditStatus("2");//已审
			
			//淘宝商品全部归类为淘宝品牌
			product.setBrandId(TaobaoUtil.brandId);


			
			ProductType productType3 = productTypeService.selectByPrimaryKey(productTypeId);
			ProductType productType2 = productTypeService.selectByPrimaryKey(productType3.getParentId());
			ProductType productType1=productTypeService.selectByPrimaryKey(productType2.getParentId());
			product.setProductType2Id(productType2.getId());
			product.setProductType1Id(productType2.getParentId());
			product.setProductTypeId(productTypeId);
			
			
			//设置搜索域字段 = 商品名称+商品品牌名称+品牌中文+品牌英文+商品名称+商品所属一级品类+商品所属二级品类+商品所属三级级品类+商品货号+商品适用性别
			ProductBrand productBrand = productBrandService.selectByPrimaryKey(product.getBrandId());
			String searchFields=productBrand.getName()+(StringUtil.isEmpty(productBrand.getNameZh())?"":(";"+productBrand.getNameZh()))+(StringUtil.isEmpty(productBrand.getNameEn())?"":(";"+productBrand.getNameEn()))
					            + ";"+product.getName()+";"+productType1.getName()+";"+productType2.getName()+";"+productType3.getName();
			
			product.setArtNo("tb88888");
			product.setSuitSex(null);
			product.setSuitGroup(null);
			product.setYear(null);
			product.setSeason(null);
			product.setProductDesc(null);
			product.setLimitBuy(0);
			product.setCsTempletId(null);
			product.setRemarksColor(null);
			product.setSaleType("1");
			product.setDelDate(null);
			product.setIsSingleProp("0");
			product.setShopProductCustomTypeIdGroup(null);
			product.setWeights(0);
			product.setIsActivity("0");
			product.setIsShow("1");
			product.setMinSalePriceItemId(null);
			product.setMinPriceItemId(null);
			product.setMinMallPrice(null);
			product.setMinSalePrice(null);
			product.setMinTagPrice(null);
			product.setSaleWeight(0);
			product.setFreightTemplateId(null);
			product.setIsShopShow("1");
			product.setCreateBy(null);
			product.setCreateDate(new Date());
			product.setUpdateBy(null);
			product.setUpdateDate(null);
			product.setRemarks(null);
			product.setDelFlag("0");
			product.setSearchFields(searchFields);
			product.setThirdPlatformCouponInfo(JsonUtils.getString(taobaokeProduct, "coupon_info"));
			
			this.insertSelective(product);

			//商品上下架日志
			ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
			productUpperLowerLog.setProductId(product.getId());
			productUpperLowerLog.setStatus(product.getStatus());
			productUpperLowerLog.setType(Const.PLATFORM);
			productUpperLowerLog.setOffReason(product.getOffReason());
			productUpperLowerLog.setCreateBy(null);
			productUpperLowerLog.setCreateDate(new Date());
			productUpperLowerLogService.insertSelective(productUpperLowerLog);

			//插入商品第三方平台信息
			ThirdPlatformProductInfo thirdPlatformProductInfo = new ThirdPlatformProductInfo();
			thirdPlatformProductInfo.setProductId(product.getId());
			thirdPlatformProductInfo.setSource(product.getSource());
			thirdPlatformProductInfo.setRefId(product.getRefId());
			thirdPlatformProductInfo.setShopTitle(JsonUtils.getString(taobaokeProduct, "shop_title"));
			thirdPlatformProductInfo.setReservePrice(taobaokeProduct.has("reserve_price")?new BigDecimal(JsonUtils.getString(taobaokeProduct, "reserve_price")):null);
			thirdPlatformProductInfo.setZkFinalPrice(taobaokeProduct.has("zk_final_price")?new BigDecimal(JsonUtils.getString(taobaokeProduct, "zk_final_price")):null);
			thirdPlatformProductInfo.setTitle(JsonUtils.getString(taobaokeProduct, "title"));
			thirdPlatformProductInfo.setNick(JsonUtils.getString(taobaokeProduct, "nick"));
			thirdPlatformProductInfo.setSellerId(JsonUtils.getString(taobaokeProduct, "seller_id"));
			thirdPlatformProductInfo.setVolume(taobaokeProduct.has("volume")?Integer.valueOf(JsonUtils.getString(taobaokeProduct, "volume")):null);
			thirdPlatformProductInfo.setItemUrl(JsonUtils.getString(taobaokeProduct,"item_url"));
			thirdPlatformProductInfo.setCouponTotalCount(taobaokeProduct.has("coupon_total_count")?Integer.valueOf(JsonUtils.getString(taobaokeProduct, "coupon_total_count")):null);
			if(taobaokeProduct.has("commission_rate")){
				thirdPlatformProductInfo.setCommissionRate(JsonUtils.getString(taobaokeProduct, "commission_rate"));
			}else{
				if(taobaokeProduct.has("tk_rate")){
					String tkRate = JsonUtils.getString(taobaokeProduct, "tk_rate");//2.58
					if(tkRate.indexOf(".")>0){
						tkRate = tkRate.substring(0, tkRate.lastIndexOf("."))+tkRate.substring(tkRate.lastIndexOf(".")+1);//258
					}
					thirdPlatformProductInfo.setCommissionRate(tkRate);
				}
			}
			
			thirdPlatformProductInfo.setCouponInfo(JsonUtils.getString(taobaokeProduct, "coupon_info"));
			thirdPlatformProductInfo.setCategory(taobaokeProduct.has("category_id")?Integer.valueOf(JsonUtils.getString(taobaokeProduct, "category_id")):null);
			thirdPlatformProductInfo.setCouponRemainCount(taobaokeProduct.has("coupon_remain_count")?Integer.valueOf(JsonUtils.getString(taobaokeProduct, "coupon_remain_count")):null);
			thirdPlatformProductInfo.setCouponStartTime(JsonUtils.getString(taobaokeProduct, "coupon_start_time"));
			thirdPlatformProductInfo.setCouponEndTime(JsonUtils.getString(taobaokeProduct, "coupon_end_time"));
			
			
			thirdPlatformProductInfo.setCouponId(JsonUtils.getString(taobaokeProduct, "coupon_id"));
			thirdPlatformProductInfo.setCouponAmount(taobaokeProduct.has("coupon_amount")?new BigDecimal(taobaokeProduct.getString("coupon_amount")):null);
			thirdPlatformProductInfo.setCouponStartFee(taobaokeProduct.has("coupon_start_fee")?new BigDecimal(taobaokeProduct.getString("coupon_start_fee")):null);



			if(taobaokeProduct.has("coupon_click_url")){//好券购物清单接口
				if(!taobaokeProduct.getString("coupon_click_url").contains("http")) {
					thirdPlatformProductInfo.setCouponClickUrl("https:"+JsonUtils.getString(taobaokeProduct, "coupon_click_url"));
				}else {
					thirdPlatformProductInfo.setCouponClickUrl(JsonUtils.getString(taobaokeProduct, "coupon_click_url"));
				}
			}else if(taobaokeProduct.has("coupon_share_url")){//通用物料接口
				String couponUrl = JsonUtils.getString(taobaokeProduct, "coupon_share_url");
				if(!couponUrl.contains("http")){
					thirdPlatformProductInfo.setCouponClickUrl("https:"+ JsonUtils.getString(taobaokeProduct, "coupon_share_url"));
				}else{
					thirdPlatformProductInfo.setCouponClickUrl(JsonUtils.getString(taobaokeProduct, "coupon_share_url"));
				}
			}
			thirdPlatformProductInfo.setIsCoupon(!StringUtil.isEmpty(thirdPlatformProductInfo.getCouponClickUrl())?"1":"0");
			thirdPlatformProductInfo.setItemDescription(JsonUtils.getString(taobaokeProduct, "item_description"));
			thirdPlatformProductInfo.setCreateDate(new Date());
			thirdPlatformProductInfo.setUpdateDate(new Date());
			
			thirdPlatformProductInfo.setStatus("1");
			thirdPlatformProductInfo.setProductType1Id(productType2.getParentId());
			thirdPlatformProductInfo.setProductType2Id(productType2.getId());
			thirdPlatformProductInfo.setProductType3Id(productTypeId);
			thirdPlatformProductInfoMapper.insertSelective(thirdPlatformProductInfo);

			// 插入主图
			ProductPic productPic = new ProductPic();
			productPic = new ProductPic();
			productPic.setProductId(product.getId());
			if(taobaokeProduct.getString("pict_url").indexOf("alicdn")>0 && !taobaokeProduct.getString("pict_url").contains("http"))
			{
				productPic.setPic("https:"+taobaokeProduct.getString("pict_url"));
			}else {
				productPic.setPic(taobaokeProduct.getString("pict_url"));
			}
			productPic.setIsDefault("1");
			productPic.setSeqNo(1);
			productPic.setCreateBy(null);
			productPic.setCreateDate(new Date());
			productPic.setUpdateBy(null);
			productPic.setUpdateDate(null);
			productPic.setRemarks(null);
			productPic.setDelFlag("0");
			productPicService.insertSelective(productPic);
			if(taobaokeProduct.has("small_images")){
				JSONObject smallImagesObject = taobaokeProduct.getJSONObject("small_images");
				if(!smallImagesObject.isEmpty() && !smallImagesObject.isNullObject() && smallImagesObject.size()>0){
					JSONArray smallImages = smallImagesObject.getJSONArray("string");
					if(smallImages!=null&&smallImages.size()>0){
						for (int i = 0; i < smallImages.size(); i++) {
							productPic = new ProductPic();
							productPic.setProductId(product.getId());
							if(smallImages.getString(i).indexOf("alicdn") >0 && !smallImages.getString(i).contains("http")) {
								productPic.setPic("https:"+smallImages.getString(i));
							}else{
								productPic.setPic(smallImages.getString(i));
							}
							productPic.setIsDefault("0");
							productPic.setSeqNo(i+2);
							productPic.setCreateBy(null);
							productPic.setCreateDate(new Date());
							productPic.setUpdateBy(null);
							productPic.setUpdateDate(null);
							productPic.setRemarks(null);
							productPic.setDelFlag("0");
							productPicService.insertSelective(productPic);
						}
					}
				}
			}

			//创建sku
			ProductItem productItem = new ProductItem();
			productItem.setProductId(product.getId());
			productItem.setSku("");
			productItem.setPropValId("0");
			productItem.setStock(100000);
			productItem.setLockedAmount(0);
			String s = taobaokeProduct.containsKey("reserve_price") ? taobaokeProduct.getString("reserve_price") : taobaokeProduct.containsKey("zk_final_price")?taobaokeProduct.getString("zk_final_price"):null;
			if(s != null){
				productItem.setTagPrice(new BigDecimal(s));
			}
			String s1 = taobaokeProduct.containsKey("zk_final_price")?taobaokeProduct.getString("zk_final_price"):null;
			if(s1 != null) {
				productItem.setMallPrice(new BigDecimal(s1));
				productItem.setSalePrice(new BigDecimal(s1));
			}else{
				productItem.setSalePrice(new BigDecimal("0.00"));
			}
			productItem.setCostPrice(null);
			productItem.setPic(taobaokeProduct.getString("pict_url"));
			productItem.setCreateBy(null);
			productItem.setCreateDate(new Date());
			productItem.setUpdateBy(null);
			productItem.setUpdateDate(null);
			productItem.setRemarks(null);
			productItem.setDelFlag("0");
			productItemService.insertSelective(productItem);

			// 将商城价最低的sku id 设置到商品表
			if (product.getId() != null) {
				resetProductMinPrice(product.getId());
			}

			// 更新商品权重
			productWeightService.updateProductWeights(product.getId(),null);
			
			
			
			//插入频道信息
			if(wetaoChannelId!=-1){
				WetaoChannelProduct wetaoChannelProduct = new WetaoChannelProduct();
				wetaoChannelProduct.setProductId(product.getId());
				wetaoChannelProduct.setChannelId(wetaoChannelId);
				wetaoChannelProduct.setCreateDate(new Date());
				wetaoChannelProduct.setDelFlag("0");
				weitaoChannelProductService.insertSelective(wetaoChannelProduct);
			}
		
			
		}

	}

	public String generateProductCode() {
		Integer sequence = commonService.getSequence("productCode");
		int radomStringLength = 12 - sequence.toString().length();
		return sequence + CommonUtil.getRandomNum(radomStringLength);
	}

	
	public void createTaobaokeProducts(JSONArray nTbkItems, int productTypeId, String delData, int wetaoChannelId) {//频道
		String[] delDataArray = delData.split(",");
		for(int i=0;i<nTbkItems.size();i++){
			JSONObject taobaokeProduct = (JSONObject)nTbkItems.get(i);
			if(delDataArray.length>0){
				if(Arrays.asList(delDataArray).contains(taobaokeProduct.getString("item_id"))){
					continue;
				}
			}
			this.createTaobaokeProduct(taobaokeProduct, productTypeId,wetaoChannelId);//频道
		}
	}
	
	public int countMchtProductForm(Map<String, Object> map) {
		return productCustomMapper.countMchtProductForm(map);
	}
	
	/**
	 * 获取商家商品构成列表
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> selectMchtProductForm(Map<String, Object> map) {
		return productCustomMapper.selectMchtProductForm(map);
	}
	
	/**
	 * 低价监测驳回
	 * 
	 * @param map
	 * @return
	 */
	public void batchDiscountReject(String[] idsArr, HttpServletRequest request) {
		for (String idStr : idsArr) {
			int id = Integer.parseInt(idStr);
			Product product = new Product();
			product.setId(id);
			product.setStatus("0");
			product.setAuditStatus("3");
			Integer monitoring = Integer.parseInt(request.getParameter("monitoring"));
			if(monitoring==1||monitoring==2){
				product.setAuditRemarks("【低价监测】商品不可折扣低于2.0");
			}
			if(monitoring==3){
				product.setAuditRemarks("店铺暂停驳回");
			}
			if(monitoring==4){
				product.setAuditRemarks("店铺关闭驳回");
			}		
			productMapper.updateByPrimaryKeySelective(product);

			//商品上下架日志
			ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
			productUpperLowerLog.setProductId(id);
			productUpperLowerLog.setStatus(product.getStatus());
			productUpperLowerLog.setType(Const.PLATFORM);
			productUpperLowerLog.setOffReason(product.getOffReason());
			productUpperLowerLog.setCreateBy(product.getCreateBy());
			productUpperLowerLog.setCreateDate(new Date());
			productUpperLowerLogService.insertSelective(productUpperLowerLog);
		}
	}
	
	/**
	 *  查询商品的主要状态
	 * Create by XDD on 2019年8月29日
	 */
	public List<HashMap<String, Object>>  selectProductStatusByCode(String productCode){
		return productCustomMapper.selectProductStatusByCode(productCode);
	}


	/**
	 * 查询商品流量报表数据
	 * */
	public List<ProductCustom> getCommodityFlowReportData(Map<String,Object> map){
		return  productCustomMapper.getCommodityFlowReportData(map);
	}


	/**
	 * 上架商品统计count
	 * @param map
	 * @return
	 */
	public int countCommodityStatisticsList(Map<String, Object> map) {
		return productCustomMapper.countCommodityStatisticsList(map);
	}

	/**
	 * 上架商品统计数据
	 *
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> selectCommodityStatisticsList(Map<String, Object> map) {
		return productCustomMapper.selectCommodityStatisticsList(map);
	}

	/**
	 * 查找商品总销量在0到50的商家
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> selectCommoditySaleStatisticsIds(Map<String, Object> map) {
		return productCustomMapper.selectCommodityStatisticsIds(map);
	}

	public List<Map<String, Object>> selectCommoditySaleStatisticsList(Map<String, Object> map) {
		return productCustomMapper.selectCommoditySaleStatisticsList(map);
	}

	public int countCommoditySaleStatisticsList(Map<String, Object> map) {
		return productCustomMapper.countCommoditySaleStatisticsList(map);
	}

}
