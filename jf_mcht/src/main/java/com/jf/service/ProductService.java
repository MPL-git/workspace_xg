package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.*;
import com.jf.dao.CommonMapper;
import com.jf.dao.ProductCustomMapper;
import com.jf.dao.ProductMapper;
import com.jf.dao.ProductVideoMapper;
import com.jf.dao.SourceNicheMapper;
import com.jf.entity.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class ProductService extends BaseService<Product, ProductExample> {
    @Autowired
    private ProductMapper dao;

    @Autowired
    private ProductCustomMapper productCustomMapper;
    @Autowired
    private ProductItemService productItemService;
    @Autowired
    private ProductPicService productPicService;

    @Autowired
    private ProductDescPicService productDescPicService;

    @Autowired
    private CommonMapper mommonMapper;

    @Autowired
    private OrderDtlService orderDtlService;

    @Resource
    private ProductPropValueService productPropValueService;

    @Resource
    private ProductWeightService productWeightService;

    @Resource
    private ProductPriceChangeLogService productPriceChangeLogService;

    @Resource
    private ProductTypeService productTypeService;

    @Resource
    private ProductBrandService productBrandService;

    @Resource
    private CloudProductItemService cloudProductItemService;

    @Resource
    private ThirdPlatformProductInfoService thirdPlatformProductInfoService;

    @Resource
    private ProductVideoMapper productVideoMapper;

    @Resource
	private SourceNicheMapper sourceNicheMapper;

    @Resource
	private ProductMapper productMapper;

    @Resource
	private ProductExtendService productExtendService;

    @Resource
	private ProductUpperLowerLogService productUpperLowerLogService;
    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        super.setDao(productMapper);
        this.dao = productMapper;
    }


    public int countProductCustomByExample(ProductCustomExample example) {
        return productCustomMapper.countByExample(example);
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
    
    public List<?> getProductPropNameByProductId(Integer productId){
    	return productCustomMapper.getProductPropNameByProductId(productId);
    }

    public Product getLastProduct4UsebleProductType(Integer mchtId) {
        return productCustomMapper.getLastProduct4UsebleProductType(mchtId);
    }


    public int count(QueryObject queryObject) {
        return dao.countByExample(builderQuery(queryObject));
    }

    public List<Product> list(QueryObject queryObject) {

        return dao.selectByExample(builderQuery(queryObject));
    }

    public Page<Product> paginate(QueryObject queryObject) {
        ProductExample example = builderQuery(queryObject);
        example.setLimitStart(queryObject.getLimitStart());
        example.setLimitSize(queryObject.getPageSize());

        List<Product> list = dao.selectByExample(example);
        int totalCount = dao.countByExample(example);
        return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
    }

    private ProductCustomExample builderQuery(QueryObject queryObject) {
        ProductCustomExample example = new ProductCustomExample();
        ProductCustomExample.ProductCustomCriteria criteria = example.createCriteria();

        if (queryObject.getQuery("delFlag") != null) {
            criteria.andDelFlagEqualTo(queryObject.getQueryToStr("delFlag"));
        }
        if (queryObject.getQuery("mchtId") != null) {
            criteria.andMchtIdEqualTo(queryObject.getQueryToInt("mchtId"));
        }
        if (queryObject.getQuery("brandId") != null) {
            criteria.andBrandIdEqualTo(queryObject.getQueryToInt("brandId"));
        }
        if (queryObject.getQuery("status") != null) {
            criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
        }
        if (queryObject.getQuery("auditStatus") != null) {
            criteria.andAuditStatusEqualTo(queryObject.getQueryToStr("auditStatus"));
        }
        if (queryObject.getQuery("auditStatusIn") != null) {
            List<String> auditStatusList = queryObject.getQuery("auditStatusIn");
            criteria.andAuditStatusIn(auditStatusList);
        }
        if (queryObject.getQuery("name") != null) {
            criteria.andNameLike("%" + queryObject.getQueryToStr("name") + "%");
        }
        if (queryObject.getQuery("artNo") != null) {
            criteria.andArtNoLike("%" + queryObject.getQueryToStr("artNo") + "%");
        }
        if (queryObject.getQuery("artNos") != null) {
            List<String> artNoList = queryObject.getQuery("artNos");
            criteria.andArtNoIn(artNoList);
        }
        if (queryObject.getQuery("remarks") != null) {
            criteria.andRemarksLike("%" + queryObject.getQueryToStr("remarks") + "%");
        }
        if (queryObject.getQuery("code") != null) {
            criteria.andCodeEqualTo(queryObject.getQueryToStr("code"));
        }
        if (queryObject.getQuery("idNotIn") != null) {
            List<Integer> idNotIn = queryObject.getQuery("idNotIn");
            criteria.andIdNotIn(idNotIn);
        }
        if (queryObject.getQuery("ids") != null) {
            List<Integer> ids = queryObject.getQuery("ids");
            criteria.andIdIn(ids);
        }
        if (queryObject.getQuery("saleType") != null) {
            criteria.andSaleTypeEqualTo(queryObject.getQueryToStr("saleType"));
        }
        if (queryObject.getQuery("freeProduct") != null) {
            criteria.andShopProductActivityStatusEqualTo("0");
        }
        if (queryObject.getQuery("firstProductTypeIdsIn") != null) {
            String firstProductTypeIds = queryObject.getQuery("firstProductTypeIdsIn");
            criteria.andProductTypeIdsIn(firstProductTypeIds);
        }
        if (queryObject.getQuery("hasProductBrandStatus") != null) {
            criteria.andProductBrandIdIn(queryObject.getQueryToInt("mchtId"));
        }
        if (queryObject.getQuery("activityId") != null) {
            criteria.andSaleActivityIdEqualTo(queryObject.getQueryToInt("activityId"));
        }
        if (queryObject.getQuery("productInActivity") != null) {
            criteria.andProductInActivityEqualTo(queryObject.getQueryToInt("productInActivity"));
        }

        if (queryObject.getSort().size() > 0) {
            example.setOrderByClause(queryObject.getSortString());
        }

        return example;
    }

	public Product fillInfo(Product product){
		Integer stock = 0;
		BigDecimal minSalePrice = null;
		BigDecimal maxSalePrice = null;
		BigDecimal minSettlePrice = null;
		BigDecimal maxSettlePrice = null;

		List<ProductItem> itemList = productItemService.findListByProductId(product.getId());
		List<Integer> productItemIds = new ArrayList<>();
		productItemIds.add(0);
		for(ProductItem item : itemList){
			stock += item.getStock();
			productItemIds.add(item.getId());
			if(minSalePrice == null || minSalePrice.compareTo(item.getSalePrice()) == 1){
				minSalePrice = item.getSalePrice();
			}
			if(maxSalePrice == null || maxSalePrice.compareTo(item.getSalePrice()) == -1){
				maxSalePrice = item.getSalePrice();
			}
			//不同规格  最大结算价，最小结算价
			if(minSettlePrice == null || minSettlePrice.compareTo(item.getCostPrice()) == 1){
				minSettlePrice = item.getCostPrice();
			}
			if(maxSettlePrice == null || maxSettlePrice.compareTo(item.getCostPrice()) == -1){
				maxSettlePrice = item.getCostPrice();
			}
		}
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("productId", product.getId());
		queryObject.addQuery("payStatus", "1");
		product.put("saleQuantity", orderDtlService.querySaleQuantity(queryObject));	//历史总销量
		queryObject.addQuery("inDay", 30);
		product.put("saleQuantityIn30Day", orderDtlService.querySaleQuantity(queryObject));	//30天销量

		product.put("stock", stock);				//库存
		product.put("minSalePrice", minSalePrice);	//最低销售价
		product.put("maxSalePrice", maxSalePrice);	//最高销售价
		product.put("minSettlePrice",minSettlePrice );   //最小结算价
		product.put("maxSettlePrice",maxSettlePrice );   //最大结算价

        ProductPic productPic = productPicService.findMainByProductId(product.getId());
        if (productPic != null) {
            product.put("pic", productPic.getPic());    // 商品图片
        }

		return product;
	}
	
	public void addProduct(Product product, JSONArray productItems, JSONArray propValueList, MchtInfo mchtInfo, String productMainPics, String productDescPics,String foodLabelPics, HttpServletRequest request,String commitType) {
		
		product.setCode(generateProductCode());
		product.setRemarksColor("1");
		
		//设置商品二级品类Id和一级品类Id
		ProductType productType3=productTypeService.selectByPrimaryKey(product.getProductTypeId());
		ProductType productType2=productTypeService.selectByPrimaryKey(productType3.getParentId());
		ProductType productType1=productTypeService.selectByPrimaryKey(productType2.getParentId());
		product.setProductType2Id(productType2.getId());
		product.setProductType1Id(productType2.getParentId());
		
		//上市年份
		
		//设置搜索域字段 = 商品名称+商品品牌名称+品牌中文+品牌英文+商品名称+商品所属一级品类+商品所属二级品类+商品所属三级级品类+商品货号+商品适用性别+店铺名称+商品长
		ProductBrand productBrand = productBrandService.selectByPrimaryKey(product.getBrandId());
		String searchFields=productBrand.getName()+(StringUtil.isEmpty(productBrand.getNameZh())?"":(";"+productBrand.getNameZh()))+(StringUtil.isEmpty(productBrand.getNameEn())?"":(";"+productBrand.getNameEn()))
				            + ";"+product.getName()+";"+productType1.getName()+";"+productType2.getName()+";"+productType3.getName()
				            + (StringUtil.isEmpty(product.getArtNo())?"":(";"+product.getArtNo()));
		
		if("10".equals(product.getSuitSex())){
			searchFields=searchFields+";男";
		}
		if("01".equals(product.getSuitSex())){
			searchFields=searchFields+";女";
		}
		if("11".equals(product.getSuitSex())){
			searchFields=searchFields+";男女";
		}
		
		searchFields= searchFields +";"+mchtInfo.getShopName()+";"+ product.getCode();
		
		product.setSearchFields(searchFields);
				            		
		//如果是SPOP,SVIP则为9.5折
		if("1".equals(mchtInfo.getMchtType())){
			product.setSvipDiscount(new BigDecimal("0.95"));
		}
		//新增商品
		this.insertSelective(product);

		if ("1".equals(product.getSaleType()) && "1".equals(commitType)) {
			//商品上下架日志
			ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
			productUpperLowerLog.setProductId(product.getId());
			productUpperLowerLog.setStatus(product.getStatus());
			productUpperLowerLog.setType(Const.BUSINESS);
			productUpperLowerLog.setCreateBy(product.getCreateBy());
			productUpperLowerLog.setCreateDate(new Date());
			productUpperLowerLogService.insertSelective(productUpperLowerLog);
		}

        Integer[] propIdArray = new Integer[propValueList.size()];
        for (int i = 0; i < propValueList.size(); i++) {
            Integer propId = propValueList.getJSONObject(i).getInt("propId");
            propIdArray[i] = propId;
        }

        //创建商品SKU
        ProductItem productItem;
        for (int i = 0; i < productItems.size(); i++) {
            JSONObject productItemJson = productItems.getJSONObject(i);
            productItem = new ProductItem();
            productItem.setProductId(product.getId());
            productItem.setTagPrice(new BigDecimal(productItemJson.getString("tagPrice")));

            productItem.setSalePrice(new BigDecimal(productItemJson.getString("salePrice")));

            if (!productItemJson.containsKey("mallPrice") || productItemJson.getString("mallPrice") == null || "".equals(productItemJson.getString("mallPrice")) || new BigDecimal(productItemJson.getString("mallPrice")).compareTo(new BigDecimal(0)) <= 0) {
                productItem.setMallPrice(productItem.getSalePrice());
            } else {
                productItem.setMallPrice(new BigDecimal(productItemJson.getString("mallPrice")));
            }


            productItem.setStock(productItemJson.getInt("stock"));
            productItem.setSku(productItemJson.getString("sku"));
            productItem.setLockedAmount(0);

            if ("1".equals(mchtInfo.getMchtType())) {
                productItem.setCostPrice(new BigDecimal(productItemJson.getString("costPrice")));
            }

            String skuPic = productItemJson.getString("skuPic");//data:image/jpg;base64,         
			
			productItem.setPic(skuPic);
			String propValueIds="";
			if(propIdArray.length>0){
				for (int j = 0; j < propIdArray.length; j++) {
					//根据规格值查询规格值id
					String propValue = productItemJson.getString(propIdArray[j].toString()).toUpperCase();
					ProductPropValueExample productPropValueExample=new ProductPropValueExample();
					productPropValueExample.createCriteria().andDelFlagEqualTo("0").andPropIdEqualTo(propIdArray[j]).andPropValueEqualTo(propValue);
					List<ProductPropValue> productPropValueList=productPropValueService.selectByExample(productPropValueExample);
					Integer propValueId;
					if (productPropValueList==null||productPropValueList.size()==0) {// 规格值不存在则新增
						ProductPropValue productPropValue = new ProductPropValue();
						productPropValue.setPropId(propIdArray[j]);
						productPropValue.setPropValue(propValue);
						productPropValue.setDelFlag("0");
						productPropValue.setCreateDate(new Date());
						productPropValue.setCreateBy(product.getCreateBy());
						productPropValue.setRemarks("商家：" + mchtInfo.getCompanyName() + "创建");
						productPropValueService.insertSelective(productPropValue);
						propValueId=productPropValue.getId();
					}else{
						propValueId=productPropValueList.get(0).getId();
					}
					
					propValueIds=propValueIds+","+propValueId;
				
				}
			}
			if(propValueIds.length()>0){
				productItem.setPropValId(propValueIds.substring(1));
			}else{
				continue;
			}
			productItem.setManageSelfFreight(StringUtils.isEmpty(productItemJson.getString("selfOperatedFreight"))?null:new BigDecimal(productItemJson.getString("selfOperatedFreight")));
			productItem.setDelFlag("0");
			productItem.setCreateDate(new Date());
			productItem.setCreateBy(product.getCreateBy());
			productItemService.insertSelective(productItem);
			
			
			//生成小图
			String sourceFileName=productItem.getPic();
			String smallFileName= FileUtil.getSmallImageName(sourceFileName);
			FileUtil.resizeImage(sourceFileName, smallFileName, 240, 240);


		}
		//插入商品主图
		JSONArray productPicArray=JSONArray.fromObject(productMainPics);
		ProductPic productPic;
		if(productPicArray.size()>0){
			boolean hasSetDefault=false;
			Integer firstPicId=null;
			for (int i = 0; i < productPicArray.size(); i++) {
				JSONObject productPicObject=productPicArray.getJSONObject(i);
				productPic=new ProductPic();
				productPic.setCreateBy(product.getCreateBy());
				productPic.setCreateDate(new Date());
				productPic.setDelFlag("0");
				productPic.setIsDefault(productPicObject.getString("isDefault"));
				productPic.setPic(productPicObject.getString("pic"));
				productPic.setProductId(product.getId());
				productPic.setSeqNo(productPicObject.getInt("seqNo"));
				productPicService.insertSelective(productPic);
				if("1".equals(productPicObject.getString("isDefault"))){
					hasSetDefault=true;
				}
				if(i==0){
					firstPicId=productPic.getId();
				}


				//生成小图
				String sourceFileName=productPic.getPic();
				String middleFileName= FileUtil.getMiddleImageName(sourceFileName);
				FileUtil.resizeImage(sourceFileName, middleFileName, 320, 320);


				//生成0.7压缩比例的压缩图
				String compressFileName= FileUtil.getCompressImageName(sourceFileName, 0.7f);
				FileUtil.compressImage(sourceFileName, compressFileName, 0.7f);
			}
			
			
			
			if(!hasSetDefault){
				productPic=new ProductPic();
				productPic.setId(firstPicId);
				productPic.setIsDefault("1");
				productPicService.updateByPrimaryKeySelective(productPic);
			}
			

		}
		
		
		//插入商品详情图
		JSONArray productDescPicArray=JSONArray.fromObject(productDescPics);
		Iterator<JSONObject> productDescPicIt= productDescPicArray.iterator();
		ProductDescPic productDescPic;
		while (productDescPicIt.hasNext()) {
			JSONObject productDescPicObject=productDescPicIt.next();
			productDescPic=new ProductDescPic();
			productDescPic.setCreateBy(product.getCreateBy());
			productDescPic.setCreateDate(new Date());
			productDescPic.setDelFlag("0");
			productDescPic.setPic(productDescPicObject.getString("pic"));
			productDescPic.setProductId(product.getId());
			productDescPic.setSeqNo(productDescPicObject.getInt("seqNo"));
			productDescPicService.insertSelective(productDescPic);
			
			//生成0.7压缩比例的压缩图
			String sourceFileName=productDescPic.getPic();
			String compressFileName= FileUtil.getCompressImageName(sourceFileName, 0.7f);
			FileUtil.compressImage(sourceFileName, compressFileName, 0.7f);
			
		}

		//当商品品类为食品超市
		if(product.getProductType1Id() == 1047){
			ProductExtend productExtend=new ProductExtend();
			productExtend.setCreateBy(product.getCreateBy());
			productExtend.setCreateDate(new Date());
			productExtend.setDelFlag("0");
			productExtend.setProductId(product.getId());
			productExtend.setStorageConditions(request.getParameter("storageConditions"));
			productExtend.setPlaceOfOrigin(request.getParameter("placeOfOrigin"));
			productExtend.setFoodLabelPic(foodLabelPics);
			productExtend.setProducerInformation(request.getParameter("producerInformation"));
			productExtend.setApprovalNumber(request.getParameter("approvalNumber"));
			productExtendService.insertSelective(productExtend);
			//生成0.7压缩比例的压缩图
			if(StringUtils.isNotEmpty(productExtend.getFoodLabelPic())){
				String sourceFileName=productExtend.getFoodLabelPic();
				String compressFileName= FileUtil.getCompressImageName(sourceFileName, 0.7f);
				FileUtil.compressImage(sourceFileName, compressFileName, 0.7f);
			}
		}

		//将商城价最低的sku id 设置到商品表，并记录一条价格变动记录
		if(product.getId()!=null){
			ProductItem minPriceProductItem=productItemService.getMinMallPriceItem(product.getId());
			ProductItem minSalePriceProductItem=productItemService.getMinSalePriceItem(product.getId());

			Product product4update=new Product();
			product4update.setId(product.getId());
			product4update.setMinPriceItemId(minPriceProductItem.getId());
			product4update.setMinMallPrice(minPriceProductItem.getMallPrice());
			product4update.setMinSalePrice(minPriceProductItem.getSalePrice());
			product4update.setMinTagPrice(minPriceProductItem.getTagPrice());
			product4update.setMinSalePriceItemId(minSalePriceProductItem.getId());
			updateByPrimaryKeySelective(product4update);

			ProductPriceChangeLog productPriceChangeLog=new ProductPriceChangeLog();
			productPriceChangeLog.setDelFlag("0");
			productPriceChangeLog.setProductId(product.getId());
			productPriceChangeLog.setCreateDate(product.getCreateDate());
			productPriceChangeLog.setCreateBy(product.getCreateBy());
			productPriceChangeLog.setMinMallPrice(minPriceProductItem.getMallPrice());
			productPriceChangeLog.setMinMallPriceItemId(minPriceProductItem.getId());
			productPriceChangeLog.setMinSalePrice(minSalePriceProductItem.getSalePrice());
			productPriceChangeLog.setMinSalePriceItemId(minSalePriceProductItem.getId());
			productPriceChangeLogService.insertSelective(productPriceChangeLog);
			
		}

		
		//更新商品权重
		productWeightService.updateProductWeights(product.getId());
		
		//保存商品小视频
		String mainVideoUrl = request.getParameter("mainVideoUrl");
		String mainVideoSize = request.getParameter("mainVideoSize");
		String descVideoUrl = request.getParameter("descVideoUrl");
		String descVideoSize = request.getParameter("descVideoSize");
		if(!StringUtil.isEmpty(mainVideoUrl)){
			ProductVideo productVideo = new ProductVideo();
			productVideo.setProductId(product.getId());
			productVideo.setVideoUrl(mainVideoUrl);
			productVideo.setVideoType("1");//1.商品主图
			if(!StringUtil.isEmpty(mainVideoSize)){
				productVideo.setSize(new Double(mainVideoSize));
			}
			productVideo.setCreateDate(new Date());
			productVideo.setDelFlag("0");
			productVideoMapper.insertSelective(productVideo);
		}
		if(!StringUtil.isEmpty(descVideoUrl)){
			ProductVideo productVideo = new ProductVideo();
			productVideo.setProductId(product.getId());
			String extension = descVideoUrl.substring(descVideoUrl.lastIndexOf("."));
			String fileNamePath = descVideoUrl.substring(0,descVideoUrl.lastIndexOf("."));
			InputStream stream = ProductService.class.getResourceAsStream("/config.properties");
			String defaultPath=null;
			try {
				Properties properties = new Properties();
				properties.load(stream);
				defaultPath = properties.getProperty("annex.filePath");
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			productVideo.setVideoUrl(descVideoUrl);
			productVideo.setVideoType("2");//2.商品描述
			if(!StringUtil.isEmpty(descVideoSize)){
				productVideo.setSize(new Double(descVideoSize));
			}
			String videoCoverPath = defaultPath+fileNamePath+".jpg";//视频封面绝对路径
			File file = new File(videoCoverPath);
			if(!file.isFile() && !file.exists()){
				String videoPath = defaultPath+descVideoUrl;//视频绝对路径
				VideoProcessing.grabberVideoFramer(videoPath, videoCoverPath);
			}
			productVideo.setVideoCover(fileNamePath+".jpg");
			productVideo.setCreateDate(new Date());
			productVideo.setDelFlag("0");
			productVideoMapper.insertSelective(productVideo);
		}
	}



	public void editProduct(Product product, JSONArray productItems, JSONArray propValueList, MchtInfo mchtInfo, String productMainPics, String productDescPics, String foodLabelPics, HttpServletRequest request) {
		
		Product oldProduct=this.selectByPrimaryKey(product.getId());

		//旧的最低价item
		ProductItemExample oldItemExample =  new ProductItemExample();
		oldItemExample.createCriteria().andProductIdEqualTo(product.getId()).andDelFlagEqualTo("0");
		oldItemExample.setOrderByClause("mall_price asc,id desc");
		oldItemExample.setLimitStart(0);
		oldItemExample.setLimitSize(1);
		ProductItem oldMinMallPriceItem = productItemService.selectByExample(oldItemExample).get(0);

		oldItemExample.setOrderByClause("sale_price asc,id desc");
		ProductItem oldMinSalePriceItem = productItemService.selectByExample(oldItemExample).get(0);

		//防止修改商品类型
//		product.setSaleType(null);

        //设置商品二级品类Id和一级品类Id
        ProductType productType3 = productTypeService.selectByPrimaryKey(product.getProductTypeId());
        ProductType productType2 = productTypeService.selectByPrimaryKey(productType3.getParentId());
        ProductType productType1 = productTypeService.selectByPrimaryKey(productType2.getParentId());
        product.setProductType2Id(productType2.getId());
        product.setProductType1Id(productType2.getParentId());


        //设置搜索域字段 = 商品名称+商品品牌名称+品牌中文+品牌英文+商品名称+商品所属一级品类+商品所属二级品类+商品所属三级级品类+商品货号+商品适用性别
        ProductBrand productBrand = productBrandService.selectByPrimaryKey(product.getBrandId());
        String searchFields = productBrand.getName() + (StringUtil.isEmpty(productBrand.getNameZh()) ? "" : (";" + productBrand.getNameZh())) + (StringUtil.isEmpty(productBrand.getNameEn()) ? "" : (";" + productBrand.getNameEn()))
                + ";" + product.getName() + ";" + productType1.getName() + ";" + productType2.getName() + ";" + productType3.getName()
                + (StringUtil.isEmpty(product.getArtNo()) ? "" : (";" + product.getArtNo()));

        if ("10".equals(product.getSuitSex())) {
            searchFields = searchFields + ";男";
        }
        if ("01".equals(product.getSuitSex())) {
            searchFields = searchFields + ";女";
        }
        if ("11".equals(product.getSuitSex())) {
            searchFields = searchFields + ";男女";
        }


		searchFields= searchFields +";"+mchtInfo.getShopName()+";"+ oldProduct.getCode();
		
		product.setSearchFields(searchFields);

		//如果商家为SPOP,则SVIP折扣固定为9.5折
		if("1".equals(mchtInfo.getMchtType())){
			product.setSvipDiscount(new BigDecimal("0.95"));
		}

		if(product.getSvipDiscount()==null){
			String productActivityStatus = this.getProductActivityStatus(product.getId());
			if(productActivityStatus.equals("0") || productActivityStatus.equals("6") || productActivityStatus.equals("7")){
				productCustomMapper.updateSvipDiscount(product.getId());
			}else{
				throw new ArgException("提报活动的商品SVIP折扣不允许修改。");
			}
		}


		this.updateByPrimaryKeySelective(product);

		Integer[] propIdArray=new Integer[propValueList.size()];
		for (int i = 0; i < propValueList.size(); i++) {
			Integer propId = propValueList.getJSONObject(i).getInt("propId");
			propIdArray[i]=propId;
		}
		
		
		//创建商品SKU
		
		//先把所有sku置为删除状态
		ProductItemExample productItemExample=new ProductItemExample();
		productItemExample.createCriteria().andProductIdEqualTo(product.getId());
		ProductItem productItem4Update=new ProductItem();
		productItem4Update.setDelFlag("1");
		productItemService.updateByExampleSelective(productItem4Update, productItemExample);
		
		ProductItem productItem;
		for (int i = 0; i < productItems.size(); i++) {
			JSONObject productItemJson=productItems.getJSONObject(i);
			
			String propValueIds="";
			Integer[] propValueIdsArray=new Integer[propIdArray.length];
			if(propIdArray.length>0){
				for (int j = 0; j < propIdArray.length; j++) {
					
					//根据规格值查询规格值id
					String propValue = productItemJson.getString(propIdArray[j].toString()).toUpperCase();
					ProductPropValueExample productPropValueExample=new ProductPropValueExample();
					productPropValueExample.createCriteria().andDelFlagEqualTo("0").andPropIdEqualTo(propIdArray[j]).andPropValueEqualTo(propValue);
					List<ProductPropValue> productPropValueList=productPropValueService.selectByExample(productPropValueExample);
					Integer propValueId;
					if (productPropValueList==null||productPropValueList.size()==0) {// 规格值不存在则新增
						ProductPropValue productPropValue = new ProductPropValue();
						productPropValue.setPropId(propIdArray[j]);
						productPropValue.setPropValue(propValue);
						productPropValue.setDelFlag("0");
						productPropValue.setCreateDate(new Date());
						productPropValue.setCreateBy(product.getCreateBy());
						productPropValue.setRemarks("商家：" + mchtInfo.getCompanyName() + "创建");
						productPropValueService.insertSelective(productPropValue);
						propValueId=productPropValue.getId();
					}else{
						propValueId=productPropValueList.get(0).getId();
					}
					
					propValueIds=propValueIds+","+propValueId;
					propValueIdsArray[j]=propValueId;
				}
			}
			
			if(propValueIds.length()<=0){
				continue;
			}
			
			//判断sku是否已经存在
			Map<String, Object> paramMap=new HashMap<>();
			paramMap.put("productId", product.getId());
			paramMap.put("propValueIds", propValueIdsArray);
			paramMap.put("propCount", propValueIdsArray.length);
			ProductItem oldProductItem=productItemService.queryProductItemByProductIdAndPropValueIds(paramMap);
			if(oldProductItem==null){
				productItem=new ProductItem();
				productItem.setProductId(product.getId());
				productItem.setTagPrice(new BigDecimal(productItemJson.getString("tagPrice")));
				productItem.setSalePrice(new BigDecimal(productItemJson.getString("salePrice")));
				
				
				
				if(!productItemJson.containsKey("mallPrice")||productItemJson.getString("mallPrice")==null||"".equals(productItemJson.getString("mallPrice"))||new BigDecimal(productItemJson.getString("mallPrice")).compareTo(new BigDecimal(0))<=0){
					productItem.setMallPrice(productItem.getSalePrice());
				}else{
					productItem.setMallPrice(new BigDecimal(productItemJson.getString("mallPrice")));
				}
				productItem.setStock(productItemJson.getInt("stock"));
				String cloudProductItemId = productItemJson.getString("cloudProductItemId");
				if(!StringUtil.isEmpty(cloudProductItemId)){
					productItem.setCloudProductItemId(Integer.parseInt(cloudProductItemId));
					CloudProductItem cloudProductItem = cloudProductItemService.selectByPrimaryKey(Integer.parseInt(cloudProductItemId));
					String supplierStatus = cloudProductItemService.getSupplierStatusByItemId(productItem.getCloudProductItemId());
					HashMap<String,Object> map = new HashMap<String,Object>();
					map.put("id", productItem.getCloudProductItemId());
					map.put("mchtId", oldProduct.getMchtId());
					String mchtSupplierStatus = cloudProductItemService.getMchtSupplierStatus(map);
					if("1".equals(supplierStatus) && "1".equals(mchtSupplierStatus)){
						productItem.setStock(cloudProductItem.getStock());
					}
				}
				productItem.setSku(productItemJson.getString("sku"));
				productItem.setLockedAmount(0);
				
				if("1".equals(mchtInfo.getMchtType())){
					productItem.setCostPrice(new BigDecimal(productItemJson.getString("costPrice")));
				}
				
				String skuPic = productItemJson.getString("skuPic");      
				
				if(skuPic.indexOf("file_servelt")>=0){
					skuPic=skuPic.substring(skuPic.indexOf("file_servelt")+12);
				}
				
				productItem.setPic(skuPic);
				productItem.setPropValId(propValueIds.substring(1));
				productItem.setManageSelfFreight(StringUtils.isEmpty(productItemJson.getString("manageSelfFreight"))?null:new BigDecimal(productItemJson.getString("manageSelfFreight")));
				productItem.setDelFlag("0");
				productItem.setCreateDate(new Date());
				productItem.setCreateBy(product.getUpdateBy());
				productItemService.insertSelective(productItem);
			}else{
				productItem=oldProductItem;
				productItem.setId(oldProductItem.getId());
				productItem.setTagPrice(new BigDecimal(productItemJson.getString("tagPrice")));
				productItem.setSalePrice(new BigDecimal(productItemJson.getString("salePrice")));
				
				if(!productItemJson.containsKey("mallPrice")||productItemJson.getString("mallPrice")==null||"".equals(productItemJson.getString("mallPrice"))||new BigDecimal(productItemJson.getString("mallPrice")).compareTo(new BigDecimal(0))<=0){
					productItem.setMallPrice(productItem.getSalePrice());
				}else{
					productItem.setMallPrice(new BigDecimal(productItemJson.getString("mallPrice")));
				}
				productItem.setStock(productItemJson.getInt("stock"));
				String cloudProductItemId = productItemJson.getString("cloudProductItemId");
				if(!StringUtil.isEmpty(cloudProductItemId)){
					productItem.setCloudProductItemId(Integer.parseInt(cloudProductItemId));
					CloudProductItem cloudProductItem = cloudProductItemService.selectByPrimaryKey(Integer.parseInt(cloudProductItemId));
					String supplierStatus = cloudProductItemService.getSupplierStatusByItemId(productItem.getCloudProductItemId());
					HashMap<String,Object> map = new HashMap<String,Object>();
					map.put("id", productItem.getCloudProductItemId());
					map.put("mchtId", oldProduct.getMchtId());
					String mchtSupplierStatus = cloudProductItemService.getMchtSupplierStatus(map);
					if("1".equals(supplierStatus) && "1".equals(mchtSupplierStatus)){
						productItem.setStock(cloudProductItem.getStock());
					}
				}
				productItem.setSku(productItemJson.getString("sku"));
				
				if("1".equals(mchtInfo.getMchtType())){
					productItem.setCostPrice(new BigDecimal(productItemJson.getString("costPrice")));
				}
				
				String skuPic = productItemJson.getString("skuPic");
				if(skuPic.indexOf("file_servelt")>=0){
					skuPic=skuPic.substring(skuPic.indexOf("file_servelt")+12);
				}
				if((oldProductItem.getPic()==null||"".equals(oldProductItem.getPic())||skuPic.indexOf(oldProductItem.getPic())<0)){//图片有改变
					productItem.setPic(skuPic);
				}else{
					productItem.setPic(oldProductItem.getPic());
				}
				productItem.setDelFlag("0");
				productItem.setPropValId(propValueIds.substring(1));
				productItem.setUpdateDate(new Date());
				productItem.setUpdateBy(product.getUpdateBy());
				productItem.setManageSelfFreight(StringUtils.isEmpty(productItemJson.getString("manageSelfFreight"))?null:new BigDecimal(productItemJson.getString("manageSelfFreight")));
				productItemService.updateByPrimaryKey(productItem);
				if(StringUtil.isEmpty(cloudProductItemId)){
					productItemService.setCloudProductItemIdNull(productItem.getId());
				}
			}
			
			//生成小图
			String sourceFileName=productItem.getPic();
			String smallFileName= FileUtil.getSmallImageName(sourceFileName);
			File targetFlie=new File(FileUtil.defaultPath.concat(smallFileName));
			if(!targetFlie.exists()){
				FileUtil.resizeImage(sourceFileName, smallFileName, 240, 240);
			}

		}

		//更新商品主图
		ProductPicExample productPicExample=new ProductPicExample();
		productPicExample.createCriteria().andProductIdEqualTo(product.getId());
		ProductPic productPic4Update=new ProductPic();
		productPic4Update.setDelFlag("1");
		productPicService.updateByExampleSelective(productPic4Update, productPicExample);

		JSONArray productPicArray=JSONArray.fromObject(productMainPics);
		ProductPic productPic;

		if (productPicArray.size()>0) {
			boolean hasSetDefault=false;
			Integer firstPicId=null;
			for (int i = 0; i < productPicArray.size(); i++) {
				JSONObject productPicObject=productPicArray.getJSONObject(i);

				if("1".equals(productPicObject.getString("isDefault"))){
					hasSetDefault=true;
				}

				productPicExample=new ProductPicExample();
				productPicExample.createCriteria().andProductIdEqualTo(product.getId()).andPicEqualTo(productPicObject.getString("pic"));

				List<ProductPic> productPics=productPicService.selectByExample(productPicExample);
				if(productPics!=null&&productPics.size()>0){
					productPic=productPics.get(0);
					productPic.setDelFlag("0");
					productPic.setIsDefault(productPicObject.getString("isDefault"));
					productPic.setSeqNo(productPicObject.getInt("seqNo"));
					productPicService.updateByPrimaryKeySelective(productPic);
					if(i==0){
						firstPicId=productPic.getId();
					}
					continue;
				}

				productPic=new ProductPic();
				productPic.setCreateBy(mchtInfo.getId());
				productPic.setCreateDate(new Date());
				productPic.setDelFlag("0");
				productPic.setIsDefault(productPicObject.getString("isDefault"));
				productPic.setPic(productPicObject.getString("pic"));
				productPic.setProductId(product.getId());
				productPic.setSeqNo(productPicObject.getInt("seqNo"));
				productPicService.insertSelective(productPic);
				//生成小图
				String sourceFileName=productPic.getPic();
				String middleFileName= FileUtil.getMiddleImageName(sourceFileName);
				FileUtil.resizeImage(sourceFileName, middleFileName, 320, 320);

				//生成0.7压缩比例的压缩图
				String compressFileName= FileUtil.getCompressImageName(sourceFileName, 0.7f);
				FileUtil.compressImage(sourceFileName, compressFileName, 0.7f);

				if(i==0){
					firstPicId=productPic.getId();
				}
			}
			if(!hasSetDefault){
				productPic=new ProductPic();
				productPic.setId(firstPicId);
				productPic.setIsDefault("1");
				productPicService.updateByPrimaryKeySelective(productPic);
			}

		}

		//更新商品详情图
		ProductDescPicExample productDescPicExample=new ProductDescPicExample();
		productDescPicExample.createCriteria().andProductIdEqualTo(product.getId());
		ProductDescPic productDescPic4Update=new ProductDescPic();
		productDescPic4Update.setDelFlag("1");
		productDescPicService.updateByExampleSelective(productDescPic4Update, productDescPicExample);

		JSONArray productDescPicArray=JSONArray.fromObject(productDescPics);
		Iterator<JSONObject> productDescPicIt= productDescPicArray.iterator();
		ProductDescPic productDescPic;
		while (productDescPicIt.hasNext()) {
			JSONObject productDescPicObject=productDescPicIt.next();

			productDescPicExample=new ProductDescPicExample();
			productDescPicExample.createCriteria().andProductIdEqualTo(product.getId()).andPicEqualTo(productDescPicObject.getString("pic"));
			List<ProductDescPic> productDescPicList=productDescPicService.selectByExample(productDescPicExample);
			if(productDescPicList!=null&&productDescPicList.size()>0){
				productDescPic=productDescPicList.get(0);
				productDescPic.setDelFlag("0");
				productDescPic.setSeqNo(productDescPicObject.getInt("seqNo"));
				productDescPicService.updateByPrimaryKeySelective(productDescPic);
				continue;
			}

			productDescPic=new ProductDescPic();
			productDescPic.setCreateBy(mchtInfo.getId());
			productDescPic.setCreateDate(new Date());
			productDescPic.setDelFlag("0");
			productDescPic.setPic(productDescPicObject.getString("pic"));
			productDescPic.setProductId(product.getId());
			productDescPic.setSeqNo(productDescPicObject.getInt("seqNo"));
			productDescPicService.insertSelective(productDescPic);
			//生成0.7压缩比例的压缩图
			String sourceFileName=productDescPic.getPic();
			String compressFileName= FileUtil.getCompressImageName(sourceFileName, 0.7f);
			FileUtil.compressImage(sourceFileName, compressFileName, 0.7f);
		}

		//当商品品类为食品超市
		if(product.getProductType1Id() == 1047){
			ProductExtendExample productExtendExample = new ProductExtendExample();
			productExtendExample.createCriteria().andProductIdEqualTo(product.getId()).andDelFlagEqualTo("0");
			List<ProductExtend> productExtends = productExtendService.selectByExample(productExtendExample);
			if(!productExtends.isEmpty()){
				ProductExtend productExtend = productExtends.get(0);
				productExtend.setUpdateBy(product.getUpdateBy());
				productExtend.setUpdateDate(new Date());
				productExtend.setStorageConditions(request.getParameter("storageConditions"));
				productExtend.setPlaceOfOrigin(request.getParameter("placeOfOrigin"));
				productExtend.setFoodLabelPic(foodLabelPics);
				productExtend.setProducerInformation(request.getParameter("producerInformation"));
				productExtend.setApprovalNumber(request.getParameter("approvalNumber"));
				productExtendService.updateByPrimaryKeySelective(productExtend);
				//生成0.7压缩比例的压缩图
				if(StringUtils.isNotEmpty(productExtend.getFoodLabelPic())){
					String sourceFileName=productExtend.getFoodLabelPic();
					String compressFileName= FileUtil.getCompressImageName(sourceFileName, 0.7f);
					FileUtil.compressImage(sourceFileName, compressFileName, 0.7f);
				}
			}else{
				ProductExtend productExtend = new ProductExtend();
				productExtend.setProductId(product.getId());
				productExtend.setStorageConditions(request.getParameter("storageConditions"));
				productExtend.setPlaceOfOrigin(request.getParameter("placeOfOrigin"));
				productExtend.setFoodLabelPic(foodLabelPics);
				productExtend.setProducerInformation(request.getParameter("producerInformation"));
				productExtend.setApprovalNumber(request.getParameter("approvalNumber"));
				productExtend.setCreateBy(oldProduct.getCreateBy());
				productExtend.setCreateDate(new Date());
				productExtendService.insertSelective(productExtend);
				//生成0.7压缩比例的压缩图
				if(StringUtils.isNotEmpty(productExtend.getFoodLabelPic())){
					String sourceFileName=productExtend.getFoodLabelPic();
					String compressFileName= FileUtil.getCompressImageName(sourceFileName, 0.7f);
					FileUtil.compressImage(sourceFileName, compressFileName, 0.7f);
				}
			}
		}

		//将商城价最低的sku id 设置到商品表
		if(product.getId()!=null){
			ProductItem minPriceProductItem = productItemService.getMinMallPriceItem(product.getId());
			ProductItem minSalePriceProductItem=productItemService.getMinSalePriceItem(product.getId());

			Product product4update=new Product();
			product4update.setId(product.getId());
			product4update.setMinPriceItemId(minPriceProductItem.getId());
			product4update.setMinMallPrice(minPriceProductItem.getMallPrice());
			product4update.setMinSalePrice(minPriceProductItem.getSalePrice());
			product4update.setMinTagPrice(minPriceProductItem.getTagPrice());
			product4update.setMinSalePriceItemId(minSalePriceProductItem.getId());

			updateByPrimaryKeySelective(product4update);

			if(minPriceProductItem.getMallPrice().compareTo(oldProduct.getMinMallPrice())!=0||minSalePriceProductItem.getSalePrice().compareTo(oldMinSalePriceItem.getSalePrice())!=0){
				ProductPriceChangeLog productPriceChangeLog=new ProductPriceChangeLog();
				productPriceChangeLog.setDelFlag("0");
				productPriceChangeLog.setProductId(product.getId());
				productPriceChangeLog.setCreateDate(product.getUpdateDate());
				productPriceChangeLog.setCreateBy(product.getUpdateBy());
				productPriceChangeLog.setMinMallPrice(minPriceProductItem.getMallPrice());
				productPriceChangeLog.setMinMallPriceItemId(minPriceProductItem.getId());
				productPriceChangeLog.setMinSalePrice(minSalePriceProductItem.getSalePrice());
				productPriceChangeLog.setMinSalePriceItemId(minSalePriceProductItem.getId());
				productPriceChangeLogService.insertSelective(productPriceChangeLog);
			}

		}

		//更新商品权重
		productWeightService.updateProductWeights(product.getId());


		//如果是第三方平台商品（淘宝，天猫等），则更新第三方商品信息表
		ThirdPlatformProductInfoExample thirdPlatformProductInfoExample =  new ThirdPlatformProductInfoExample();
		thirdPlatformProductInfoExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(product.getId());
		List<ThirdPlatformProductInfo> thirdPlatformProductInfos = thirdPlatformProductInfoService.selectByExample(thirdPlatformProductInfoExample);
		if(thirdPlatformProductInfos!=null&&thirdPlatformProductInfos.size()>0){
			ThirdPlatformProductInfo thirdPlatformProductInfo =  thirdPlatformProductInfos.get(0);
			thirdPlatformProductInfo.setStatus(product.getStatus());
			thirdPlatformProductInfo.setProductType1Id(product.getProductType1Id());
			thirdPlatformProductInfo.setProductType2Id(product.getProductType2Id());
			thirdPlatformProductInfo.setProductType3Id(product.getProductTypeId());
			thirdPlatformProductInfo.setSearchFields(product.getSearchFields());
			thirdPlatformProductInfoService.updateByPrimaryKeySelective(thirdPlatformProductInfo);
		}

		//更新商品小视频
		String mainVideoUrl = request.getParameter("mainVideoUrl");
		String mainVideoSize = request.getParameter("mainVideoSize");
		String descVideoUrl = request.getParameter("descVideoUrl");
		String descVideoSize = request.getParameter("descVideoSize");
		if(!StringUtil.isEmpty(mainVideoUrl)){
			ProductVideoExample e = new ProductVideoExample();
			e.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(product.getId()).andVideoTypeEqualTo("1");
			List<ProductVideo> productVideos = productVideoMapper.selectByExample(e);
			if(productVideos!=null && productVideos.size()>0){
				ProductVideo productVideo = productVideos.get(0);
				if(!productVideo.getVideoUrl().equals(mainVideoUrl)){
					productVideo.setVideoUrl(mainVideoUrl);
					productVideo.setUpdateDate(new Date());
					productVideoMapper.updateByPrimaryKeySelective(productVideo);
				}
			}else{
				ProductVideo productVideo = new ProductVideo();
				productVideo.setProductId(product.getId());
				productVideo.setVideoType("1");
				productVideo.setVideoUrl(mainVideoUrl);
				productVideo.setSize(new Double(mainVideoSize));
				productVideo.setCreateDate(new Date());
				productVideo.setDelFlag("0");
				productVideoMapper.insertSelective(productVideo);
			}
		}else{
			ProductVideo productVideo = new ProductVideo();
			productVideo.setUpdateDate(new Date());
			productVideo.setDelFlag("1");
			ProductVideoExample e = new ProductVideoExample();
			e.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(product.getId()).andVideoTypeEqualTo("1");
			productVideoMapper.updateByExampleSelective(productVideo, e);
		}
		if(!StringUtil.isEmpty(descVideoUrl)){
			ProductVideoExample e = new ProductVideoExample();
			e.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(product.getId()).andVideoTypeEqualTo("2");
			List<ProductVideo> productVideos = productVideoMapper.selectByExample(e);
			if(productVideos!=null && productVideos.size()>0){
				ProductVideo productVideo = productVideos.get(0);
				if(!productVideo.getVideoUrl().equals(descVideoUrl)){
					productVideo.setVideoUrl(descVideoUrl);
					InputStream stream = ProductService.class.getResourceAsStream("/config.properties");
					String defaultPath=null;
					try {
						Properties properties = new Properties();
						properties.load(stream);
						defaultPath = properties.getProperty("annex.filePath");
						stream.close();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
					String filePath = descVideoUrl.substring(0, descVideoUrl.lastIndexOf("."));
					String videoCoverPath = defaultPath+filePath+".jpg";//视频封面绝对路径
					File file = new File(videoCoverPath);
					if(!file.isFile() && !file.exists()){
						String videoPath = defaultPath+descVideoUrl;//视频绝对路径
						VideoProcessing.grabberVideoFramer(videoPath, videoCoverPath);
					}
					productVideo.setVideoCover(filePath+".jpg");
					productVideo.setUpdateDate(new Date());
					productVideoMapper.updateByPrimaryKeySelective(productVideo);
				}
			}else{
				ProductVideo productVideo = new ProductVideo();
				productVideo.setProductId(product.getId());
				productVideo.setVideoType("2");
				productVideo.setVideoUrl(descVideoUrl);
				productVideo.setSize(new Double(descVideoSize));
				InputStream stream = ProductService.class.getResourceAsStream("/config.properties");
				String defaultPath=null;
				try {
					Properties properties = new Properties();
					properties.load(stream);
					defaultPath = properties.getProperty("annex.filePath");
					stream.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
				String filePath = descVideoUrl.substring(0, descVideoUrl.lastIndexOf("."));
				String videoCoverPath = defaultPath+filePath+".jpg";//视频封面绝对路径
				File file = new File(videoCoverPath);
				if(!file.isFile() && !file.exists()){
					String videoPath = defaultPath+descVideoUrl;//视频绝对路径
					VideoProcessing.grabberVideoFramer(videoPath, videoCoverPath);
				}
				productVideo.setVideoCover(filePath+".jpg");
				productVideo.setCreateDate(new Date());
				productVideo.setDelFlag("0");
				productVideoMapper.insertSelective(productVideo);
			}
		}else{
			ProductVideo productVideo = new ProductVideo();
			productVideo.setUpdateDate(new Date());
			productVideo.setDelFlag("1");
			ProductVideoExample e = new ProductVideoExample();
			e.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(product.getId()).andVideoTypeEqualTo("2");
			productVideoMapper.updateByExampleSelective(productVideo, e);
		}
	}
	public void editProductSku(Product product, JSONArray productItems, MchtInfo mchtInfo) throws ArgException {
		
		Product oldProduct=this.selectByPrimaryKey(product.getId());
		ProductItem oldMinSalePriceItem=productItemService.selectByPrimaryKey(oldProduct.getMinSalePriceItemId());
		
		Product product4Update=new Product();
		product4Update.setId(product.getId());
		product4Update.setCsTempletId(product.getCsTempletId());
		product4Update.setLimitBuy(product.getLimitBuy());
		product4Update.setSaleType(product.getSaleType());
		product4Update.setUpdateBy(product.getUpdateBy());
		product4Update.setUpdateDate(product.getUpdateDate());
		product4Update.setSvipDiscount(product.getSvipDiscount());
		//如果是SPOP商家，SVIP折扣固定为9.5折
		if("1".equals(mchtInfo.getMchtType())){
			product4Update.setSvipDiscount(new BigDecimal("0.95"));
		}
		this.updateByPrimaryKeySelective(product4Update);
		if(product.getSvipDiscount() == null){
			String productActivityStatus = this.getProductActivityStatus(product.getId());
			if(productActivityStatus.equals("0") || productActivityStatus.equals("6") || productActivityStatus.equals("7")){
				productCustomMapper.updateSvipDiscount(product.getId());
			}else{
				throw new ArgException("提报活动的商品SVIP折扣不允许修改。");
			}
		}
		ProductItem productItem4Update;
		for (int i = 0; i < productItems.size(); i++) {
			JSONObject productItemJson=productItems.getJSONObject(i);
			ProductItem oldProductItem=productItemService.selectByPrimaryKey(Integer.valueOf(productItemJson.getString("productItemId")));
			if(!oldProductItem.getProductId().equals(product.getId())){
				throw new ArgException("修改出错");
			}

			productItem4Update=new ProductItem();
			productItem4Update.setId(Integer.valueOf(productItemJson.getString("productItemId")));
			productItem4Update.setTagPrice(new BigDecimal(productItemJson.getString("tagPrice")));
			productItem4Update.setSalePrice(new BigDecimal(productItemJson.getString("salePrice")));
			
			if(!productItemJson.containsKey("mallPrice")||productItemJson.getString("mallPrice")==null||"".equals(productItemJson.getString("mallPrice"))||new BigDecimal(productItemJson.getString("mallPrice")).compareTo(new BigDecimal(0))<=0){
				productItem4Update.setMallPrice(productItem4Update.getSalePrice());
			}else{
				productItem4Update.setMallPrice(new BigDecimal(productItemJson.getString("mallPrice")));
			}

//			productItem4Update.setStock(productItemJson.getInt("stock"));//库存在输入框失去焦点时，时时修改，这里不在做修改
			productItem4Update.setSku(productItemJson.getString("sku"));
			if("1".equals(mchtInfo.getMchtType())){
				productItem4Update.setCostPrice(new BigDecimal(productItemJson.getString("costPrice")));
			}
			
			String skuPic = productItemJson.getString("skuPic");
			if((oldProductItem.getPic()==null||"".equals(oldProductItem.getPic())||skuPic.indexOf(oldProductItem.getPic())<0)){//图片有改变
				
				productItem4Update.setPic(skuPic);
			}
			productItem4Update.setUpdateDate(new Date());
			productItem4Update.setUpdateBy(product.getUpdateBy());
			productItemService.updateByPrimaryKeySelective(productItem4Update);
			
			
		}
		
		//将商城价最低的sku id 设置到商品表
		if(product.getId()!=null){
			ProductItem minPriceProductItem = productItemService.getMinMallPriceItem(product.getId());
			ProductItem minSalePriceProductItem = productItemService.getMinSalePriceItem(product.getId());
			
			Product product4update=new Product();
			product4update.setId(product.getId());
			product4update.setMinPriceItemId(minPriceProductItem.getId());
			product4update.setMinMallPrice(minPriceProductItem.getMallPrice());
			product4update.setMinSalePrice(minPriceProductItem.getSalePrice());
			product4update.setMinTagPrice(minPriceProductItem.getTagPrice());
			product4update.setMinSalePriceItemId(minSalePriceProductItem.getId());
			updateByPrimaryKeySelective(product4update);
			
			if(minPriceProductItem.getMallPrice().compareTo(oldProduct.getMinMallPrice())!=0||minSalePriceProductItem.getSalePrice().compareTo(oldMinSalePriceItem.getSalePrice())!=0){
				ProductPriceChangeLog productPriceChangeLog=new ProductPriceChangeLog();
				productPriceChangeLog.setDelFlag("0");
				productPriceChangeLog.setProductId(product.getId());
				productPriceChangeLog.setCreateDate(product.getUpdateDate());
				productPriceChangeLog.setCreateBy(product.getUpdateBy());
				productPriceChangeLog.setMinMallPrice(minPriceProductItem.getMallPrice());
				productPriceChangeLog.setMinMallPriceItemId(minPriceProductItem.getId());
				productPriceChangeLog.setMinSalePrice(minSalePriceProductItem.getSalePrice());
				productPriceChangeLog.setMinSalePriceItemId(minSalePriceProductItem.getId());
				productPriceChangeLogService.insertSelective(productPriceChangeLog);
			}
			
			
		}
		
	}
	
	public String generateProductCode(){
		Integer sequence=mommonMapper.getSequence("productCode");
		int radomStringLength=12-sequence.toString().length();
		return sequence+ CommonUtil.getRandomNum(radomStringLength);
	}

	public List<ProductCustom> getProductsByActivityId(Integer activityId) {
		return productCustomMapper.getProductsByActivityId(activityId);
	}
	
	public List<ProductCustom> getProductsByParamMap(Map<String,Object> paramMap) {
		return productCustomMapper.getProductsByParamMap(paramMap);
	}


	public String getProductActivityStatus(Integer id) {
		
		Product product=this.selectByPrimaryKey(id);
		if("1".equals(product.getSaleType())){
			return this.getShopProductActivityStatus(id);
		}else{
			return this.getSingleProductActivityStatus(id);
		}
		
	}
	public String getShopProductActivityStatus(Integer id) {
		return productCustomMapper.getShopProductActivityStatus(id);
	}
	public String getSingleProductActivityStatus(Integer id) {
		return productCustomMapper.getSingleProductActivityStatus(id);
	}


	public void outFreightTemplate(List<Integer> productIdList) {
		productCustomMapper.outFreightTemplate(productIdList);
	}
	
	public void selectFreightTemplate(HashMap<String,Object> map) {
		productCustomMapper.selectFreightTemplate(map);
	}

	public int countCurrentTemplate(Integer freightTemplateId) {
		return productCustomMapper.countCurrentTemplate(freightTemplateId);
	}

	public int countNoTemplate(Integer mchtId) {
		return productCustomMapper.countNoTemplate(mchtId);
	}

	public int countOtherTemplateCount(HashMap<String, Object> map) {
		return productCustomMapper.countOtherTemplateCount(map);
	}
	
	public void productCopy(String sourceMchtId, String targetMchtId,String brandId) {
	       
        ProductExample productExample=new ProductExample();
        ProductExample.Criteria productCriteria = productExample.createCriteria();
        productCriteria.andDelFlagEqualTo("0").andMchtIdEqualTo(Integer.valueOf(sourceMchtId));
        
        if(!StringUtil.isEmpty(brandId)){
        	productCriteria.andBrandIdEqualTo(Integer.valueOf(brandId));
        }
        
        List<Product> sourceProducts=this.selectByExample(productExample);
        int i=0;
        int totalCount=sourceProducts.size();
        
        String nowDateString = DateUtil.getStandardDate(new Date());
        String batchCode=generateProductCode();
        
        if(sourceProducts!=null){
        	for(Product sourceProduct:sourceProducts){
        		i++;
        		System.out.println("复制商品"+i+"/"+totalCount+"id:"+sourceProduct.getId());
        		Product targetProduct=new Product();
        		
        		targetProduct.setMchtId(Integer.valueOf(targetMchtId));
        		targetProduct.setCode(generateProductCode());
        		targetProduct.setName(sourceProduct.getName());
        		targetProduct.setSearchFields(sourceProduct.getSearchFields());
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
        		targetProduct.setCreateBy(null);
        		targetProduct.setCreateDate(new Date());
        		targetProduct.setUpdateBy(null);
        		targetProduct.setUpdateDate(null);
//        		targetProduct.setRemarks(nowDateString+"批次："+batchCode+"复制商家id为:"+sourceMchtId+"的商品（id）"+sourceProduct.getId());
        		targetProduct.setRemarks(sourceProduct.getRemarks());
        		targetProduct.setDelFlag("0");
        		
        		this.insertSelective(targetProduct);

				//商品上下架日志
				ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
				productUpperLowerLog.setProductId(targetProduct.getId());
				productUpperLowerLog.setStatus(targetProduct.getStatus());
				productUpperLowerLog.setType(Const.BUSINESS);
				productUpperLowerLog.setOffReason(targetProduct.getOffReason());
				productUpperLowerLog.setCreateDate(new Date());
				productUpperLowerLogService.insertSelective(productUpperLowerLog);

        		//复制主图
        		ProductPicExample productPicExample=new ProductPicExample();
        		productPicExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(sourceProduct.getId());
        		List<ProductPic> productPics = productPicService.selectByExample(productPicExample);
        		if(productPics!=null){
        			for(ProductPic sourceProductPic:productPics){
        				ProductPic targetProductPic=new ProductPic();
        				targetProductPic.setProductId(targetProduct.getId());
        				targetProductPic.setPic(sourceProductPic.getPic());
        				targetProductPic.setIsDefault(sourceProductPic.getIsDefault());
        				targetProductPic.setSeqNo(sourceProductPic.getSeqNo());
        				targetProductPic.setCreateBy(null);
        				targetProductPic.setCreateDate(new Date());
        				targetProductPic.setUpdateBy(null);
        				targetProductPic.setUpdateDate(null);
        				targetProductPic.setRemarks(nowDateString+"批次："+batchCode+"复制id："+sourceProductPic.getId());
        				targetProductPic.setDelFlag("0");
        				productPicService.insertSelective(targetProductPic);
        				
        			}
        		}
        		
        		//复制商品详情图
        		ProductDescPicExample productDescPicExample=new ProductDescPicExample();
        		productDescPicExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(sourceProduct.getId());
        		List<ProductDescPic> productDescPics=productDescPicService.selectByExample(productDescPicExample);
        		if(productDescPics!=null){
        			for(ProductDescPic sourceProductDescPic:productDescPics){
        				ProductDescPic targetProductDescPic=new ProductDescPic();
        				targetProductDescPic.setProductId(targetProduct.getId());
        				targetProductDescPic.setPic(sourceProductDescPic.getPic());
        				targetProductDescPic.setSeqNo(sourceProductDescPic.getSeqNo());
        				targetProductDescPic.setCreateBy(null);
        				targetProductDescPic.setCreateDate(new Date());
        				targetProductDescPic.setUpdateBy(null);
        				targetProductDescPic.setUpdateDate(null);
        				targetProductDescPic.setRemarks(nowDateString+"批次："+batchCode+"复制id:"+sourceProductDescPic.getId());
        				targetProductDescPic.setDelFlag("0");
        				productDescPicService.insertSelective(targetProductDescPic);
        			}
        		}
        		
        		
        		//复制sku
        		ProductItemExample productItemExample=new ProductItemExample();
        		productItemExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(sourceProduct.getId());
        		List<ProductItem> sourceProductItems=productItemService.selectByExample(productItemExample);
        		if(sourceProductItems!=null){
        			for(ProductItem sourceProductItem:sourceProductItems){
        				ProductItem targetProductItem=new ProductItem();
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
        				targetProductItem.setCreateBy(null);
        				targetProductItem.setCreateDate(new Date());
        				targetProductItem.setUpdateBy(null);
        				targetProductItem.setUpdateDate(null);
        				targetProductItem.setRemarks(nowDateString+"批次："+batchCode+"复制id:"+sourceProductItem.getId());
        				targetProductItem.setDelFlag("0");
        				productItemService.insertSelective(targetProductItem);
        			}
        		}
        		
        		//将商城价最低的sku id 设置到商品表
        		if(targetProduct.getId()!=null){
					ProductItem minPriceProductItem = productItemService.getMinMallPriceItem(targetProduct.getId());
					ProductItem minSalePriceProductItem = productItemService.getMinSalePriceItem(targetProduct.getId());
        			
        			Product product4update=new Product();
        			product4update.setId(targetProduct.getId());
        			product4update.setMinPriceItemId(minPriceProductItem.getId());
        			product4update.setMinMallPrice(minPriceProductItem.getMallPrice());
        			product4update.setMinSalePrice(minPriceProductItem.getSalePrice());
        			product4update.setMinTagPrice(minPriceProductItem.getTagPrice());
        			product4update.setMinSalePriceItemId(minSalePriceProductItem.getId());
        			
        			updateByPrimaryKeySelective(product4update);
        			
        			
        		}
        		
        		//更新商品权重
        		productWeightService.updateProductWeights(targetProduct.getId());
        		
        		
        	}
        }

    }

    /**
     * 获取商品销售数据
     *
     * @param paramMap
     * @return
     */
    public List<HashMap<String, Object>> getProductSaleData(HashMap<String, Object> paramMap) {
        return productCustomMapper.getProductSaleData(paramMap);
    }


    public int countProductsByParamMap(Map<String, Object> paramMap) {
        return productCustomMapper.countProductsByParamMap(paramMap);
    }

    /**
     * 获取店铺装修展示数据
     *
     * @return
     */
    public List<ProductCustom> selectShopDecorateList(Integer mchtId) {
        List<ProductCustom> productList = productCustomMapper.selectShopDecorateList(mchtId);
        for (ProductCustom productCustom : productList) {
            BigDecimal goodComment = BigDecimal.valueOf(productCustom.getGoodCommentCount());
            BigDecimal totalComment = BigDecimal.valueOf(productCustom.getTotalCommentCount());
            String percent = totalComment.compareTo(BigDecimal.ZERO) == 0 ? "-" : goodComment.multiply(new BigDecimal(100)).divide(totalComment, 2, BigDecimal.ROUND_HALF_UP) + "%";
            productCustom.setGoodCommentRate(percent);
        }
        return productList;
    }

    //修改库存
    public int changeStock(int productItemId, int stock, int changeBy) throws ArgException {

        ProductItem oldProductItem = productItemService.selectByPrimaryKey(productItemId);
        ProductItem productItem4Update = new ProductItem();
        productItem4Update.setId(productItemId);
        //要修改的库存<锁定的库存时，最低只能将库存设置为锁定库存的数量
        productItem4Update.setStock(oldProductItem.getLockedAmount() > stock ? oldProductItem.getLockedAmount() : stock);
        productItem4Update.setUpdateDate(new Date());
        productItem4Update.setUpdateBy(changeBy);
        productItemService.updateByPrimaryKeySelective(productItem4Update);

        //设置商品最低价
        setProductMinPrice(oldProductItem.getProductId(),changeBy);

        return productItem4Update.getStock();

    }

    public Product setProductMinPrice(Integer productId,Integer setBy) {
        Product oldProduct = this.selectByPrimaryKey(productId);
        //设置最低商城价
        Product product4update = new Product();
        product4update.setId(productId);
		ProductItem minPriceProductItem = productItemService.getMinMallPriceItem(productId);
        product4update.setMinPriceItemId(minPriceProductItem.getId());
        product4update.setMinMallPrice(minPriceProductItem.getMallPrice());
        product4update.setMinSalePrice(minPriceProductItem.getSalePrice());
        product4update.setMinTagPrice(minPriceProductItem.getTagPrice());

        //设置最低活动价
		ProductItem minSalePriceProductItem = productItemService.getMinSalePriceItem(productId);
        product4update.setMinSalePriceItemId(minSalePriceProductItem.getId());

        if((product4update.getMinSalePriceItemId() != null && !product4update.getMinSalePriceItemId().equals(oldProduct.getMinSalePriceItemId()))
                || (product4update.getMinPriceItemId() != null && !product4update.getMinPriceItemId().equals(oldProduct.getMinPriceItemId()))
                || (product4update.getMinMallPrice() != null && !product4update.getMinMallPrice().equals(oldProduct.getMinMallPrice()))
                || (product4update.getMinSalePrice() != null && !product4update.getMinSalePrice().equals(oldProduct.getMinSalePrice()))
                || (product4update.getMinTagPrice() != null && !product4update.getMinTagPrice().equals(oldProduct.getMinTagPrice())) ){
            updateByPrimaryKeySelective(product4update);
        }
        return product4update;
    }

    public void changeProductStatus(Product product4Update) throws ArgException {
        this.updateByPrimaryKeySelective(product4Update);

		//商品上下架日志
		ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
		productUpperLowerLog.setProductId(product4Update.getId());
		productUpperLowerLog.setStatus(product4Update.getStatus());
		productUpperLowerLog.setType(Const.BUSINESS);
		if("0".equals(product4Update.getStatus())){
			productUpperLowerLog.setOffReason("商家下架");
		}
		productUpperLowerLog.setCreateDate(new Date());
		productUpperLowerLogService.insertSelective(productUpperLowerLog);

        //如果是第三方平台商品（淘宝，天猫等），则更新第三方商品信息表的状态
        ThirdPlatformProductInfoExample thirdPlatformProductInfoExample = new ThirdPlatformProductInfoExample();
        thirdPlatformProductInfoExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(product4Update.getId());
        List<ThirdPlatformProductInfo> thirdPlatformProductInfos = thirdPlatformProductInfoService.selectByExample(thirdPlatformProductInfoExample);
        if (thirdPlatformProductInfos != null && thirdPlatformProductInfos.size() > 0) {
            ThirdPlatformProductInfo thirdPlatformProductInfo = thirdPlatformProductInfos.get(0);
            thirdPlatformProductInfo.setStatus(product4Update.getStatus());
            thirdPlatformProductInfoService.updateByPrimaryKeySelective(thirdPlatformProductInfo);
        }


    }

    public int countProductSaleData(HashMap<String, Object> paramMap) {
        return productCustomMapper.countProductSaleData(paramMap);
    }

	public void batchUndercarriage(List<Integer> successIds,String status) {
		ProductExample productExample = new ProductExample();
		Product product = new Product();
    	if("0".equals(status)){//下架
			//查询营销活动是否有已报名改商品，有的话删除
			SourceNicheExample sourceNicheExample = new SourceNicheExample();
			sourceNicheExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andLinkIdIn(successIds);
			List<SourceNiche> sourceNicheList = sourceNicheMapper.selectByExample(sourceNicheExample);
			if(!sourceNicheList.isEmpty()) {
				SourceNiche sourceNiche = sourceNicheList.get(0);
				sourceNiche.setStatus("1");
				sourceNiche.setDelFlag("1");
				sourceNicheMapper.updateByPrimaryKeySelective(sourceNiche);
			}
			//批量修改为下架状态
			productExample.createCriteria().andIdIn(successIds).andDelFlagEqualTo("0");
			product.setStatus("0");
			product.setOffReason("3");
			productMapper.updateByExampleSelective(product,productExample);
			batchChangeProductStatus(successIds,status);
			for (Integer id : successIds){
				//商品上下架日志
				ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
				productUpperLowerLog.setProductId(id);
				productUpperLowerLog.setStatus(product.getStatus());
				productUpperLowerLog.setType(Const.BUSINESS);
				productUpperLowerLog.setOffReason("商家下架");
				productUpperLowerLog.setCreateDate(new Date());
				productUpperLowerLogService.insertSelective(productUpperLowerLog);
			}
		}else if("1".equals(status)){//删除
			productExample.createCriteria().andIdIn(successIds).andDelFlagEqualTo("0");
			product.setDelFlag("1");
			productMapper.updateByExampleSelective(product,productExample);
		}
	}

	//批量修改第三方平台商品状态
	public void batchChangeProductStatus(List<Integer> successIds,String status) throws ArgException {
		//如果是第三方平台商品（淘宝，天猫等），则更新第三方商品信息表的状态
		ThirdPlatformProductInfoExample thirdPlatformProductInfoExample = new ThirdPlatformProductInfoExample();
		thirdPlatformProductInfoExample.createCriteria().andDelFlagEqualTo("0").andProductIdIn(successIds);
		ThirdPlatformProductInfo thirdPlatformProductInfo = new ThirdPlatformProductInfo();
		thirdPlatformProductInfo.setStatus(status);
		thirdPlatformProductInfoService.updateByExampleSelective(thirdPlatformProductInfo,thirdPlatformProductInfoExample);
	}

	public List<ProductCustom> selectProductAndPropValue(ProductCustomExample productCustomExample) {
    	return productCustomMapper.selectProductAndPropValue(productCustomExample);
	}
}
