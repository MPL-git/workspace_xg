package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.StringUtils;
import com.jf.common.utils.TaobaoUtil;
import com.jf.dao.TaobaoProductTypePageNoMapper;
import com.jf.entity.*;
import com.jf.service.*;
import com.jf.vo.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class TaobaokeController extends BaseController {
	
	@Resource
	private TaobaokeService taobaokeService;
	
	@Resource
	private ThirdPlatformProductInfoService thirdPlatformProductInfoService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private ProductDescPicService productDescPicService;
	
	@Resource
	private ProductPicService productPicService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private SysStaffProductTypeService sysstaffproductTypeService;
	
	@Resource
	private TaobaoProductTypePageNoMapper taobaoProductTypePageNoMapper;
	
	@Resource
	private WeitaoChannelService weitaoChannelService;
	
	@Resource
	private WeitaoChannelProductService weitaoChannelProductService;
	
	@RequestMapping(value = "/taobaoke/getTaobaokeProducts.shtml")
	@ResponseBody
	public Map<String, Object> getTaobaokeProducts(HttpServletRequest request){
		Map<String, Object> result= new HashMap<String, Object>();
		try {
			int productTypId = Integer.valueOf(request.getParameter("productTypId"));
			int pageNo = Integer.valueOf(request.getParameter("pageNo"));
			int pageSize = Integer.valueOf(request.getParameter("pageSize"));
			Boolean isCoupon = null;
			if(request.getParameter("isCoupon")!=null){
				isCoupon = "1".equals(request.getParameter("isCoupon"))?true:false;
			}
			taobaokeService.getProducts(productTypId,isCoupon,pageNo, pageSize);
			result.put("returnCode", "0000");
			result.put("returnMsg", "获取成功");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			result.put("returnCode", "4004");
			result.put("returnMsg", "获取失败");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 商品列表（淘宝客）列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/taobaoke/thirdPlatformProductInfoList.shtml")
	public ModelAndView thirdPlatformProductInfoList(HttpServletRequest request) throws IOException {
		String rtPage = "taobaoke/thirdPlatformProductInfoList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		resMap.put("productTypes", productTypes);
		resMap.put("seqNoAsc", "1");
		
		//频道
		WetaoChannelExample example = new WetaoChannelExample();
		example.createCriteria().andDelFlagEqualTo("0");
		List<WetaoChannel> WetaoChannels = weitaoChannelService.selectByExample(example );
		resMap.put("WetaoChannels", WetaoChannels);

		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 
	 * @Title getProductTypeList   
	 * @Description TODO(分类)   
	 * @author yjc
	 * @date 2018年12月27日16:42:28
	 */
	@ResponseBody
	@RequestMapping("/taobaoke/getProductTypeList.shtml")
	public Map<String, Object> getProductTypeList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			SysStaffProductTypeCustomExample sysstaffProductTypeex = new SysStaffProductTypeCustomExample();
			SysStaffProductTypeCustomExample.SysStaffProductTypeCustomCriteria sysstaffProductTypeexCriteria = sysstaffProductTypeex.createCriteria();
			sysstaffProductTypeexCriteria.andDelFlagEqualTo("0").andStaffIdEqualTo(staffID);
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					sysstaffProductTypeexCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}
			resMap.put("isCwOrgStatus", isCwOrgStatus);
			
			List<SysStaffProductTypeCustom> sysStaffProductTypeList = sysstaffproductTypeService.selectByCustomExample(sysstaffProductTypeex);
			List<Integer> productTypeIdList = new ArrayList<Integer>();
			for(SysStaffProductType sysStaffProductType:sysStaffProductTypeList){
				if(!productTypeIdList.contains(sysStaffProductType.getProductTypeId())){
					productTypeIdList.add(sysStaffProductType.getProductTypeId());
				}
			}
			if(productTypeIdList!=null && productTypeIdList.size()>0){
				HashMap<String,Object> map = new HashMap<String,Object>();
				map.put("productTypeIdList", productTypeIdList);
				if(!StringUtil.isEmpty(request.getParameter("condition"))) {
					JSONArray jsonArray = JSONArray.fromObject(request.getParameter("condition"));
					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						String searchvalue = jsonObject.getString("value");
						String searchitem = jsonObject.getString("field");
						if (!StringUtil.isEmpty(searchvalue)) {
							if (searchitem.equals("name")) {
								map.put("name", "%"+searchvalue+"%");
							}
						}
					}
				}
				totalCount = productTypeService.countListByFirstProductTypeIds(map);
				map.put("limitStart", (page.getPage()-1)*page.getPagesize());
				map.put("limitSize", page.getPagesize());
				List<HashMap<String,Object>> productTypeList = productTypeService.getListByFirstProductTypeIds(map);
				resMap.put("Rows", productTypeList);
				resMap.put("Total", totalCount);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 商品列表（淘宝客）列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/taobaoke/thirdPlatformProductInfoData.shtml")
	@ResponseBody
	public Map<String, Object> thirdPlatformProductInfoData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			String seqNoAsc = request.getParameter("seqNoAsc");
			ThirdPlatformProductInfoCustomExample example = new ThirdPlatformProductInfoCustomExample();
			if(!StringUtils.isEmpty(seqNoAsc) && seqNoAsc.equals("1")){
				example.setOrderByClause("ifnull(t.seq_no,999999) asc , t.update_date desc");
			}else{
				example.setOrderByClause("t.id desc");
			}
			ThirdPlatformProductInfoCustomExample.ThirdPlatformProductInfoCustomCriteria c = example.createCriteria();
			c.andDelFlagEqualTo("0");
			String title = request.getParameter("title");
			String refId = request.getParameter("refId");
			String id = request.getParameter("id");
			String source = request.getParameter("source");
			String productType1Id = request.getParameter("productType1Id");
			String productType2Id = request.getParameter("productType2Id");
			String productTypeId = request.getParameter("productTypeId");
			String productCode = request.getParameter("productCode");
			String status = request.getParameter("status");
			String zkFinalPriceMin = request.getParameter("zkFinalPriceMin");
			String zkFinalPriceMax = request.getParameter("zkFinalPriceMax");
			String couponAmountMin = request.getParameter("couponAmountMin");
			String couponAmountMax = request.getParameter("couponAmountMax");
			String commissionRateMin = request.getParameter("commissionRateMin");
			String commissionRateMax = request.getParameter("commissionRateMax");
			String volume = request.getParameter("volume");
			
			//频道
			String WetaoChannel = request.getParameter("WetaoChannel");
			if(!StringUtils.isEmpty(WetaoChannel)){
			c.andWetaoChannelIdEqualTo(Integer.parseInt(WetaoChannel));
			}
			
			if(!StringUtils.isEmpty(title)){
				c.andTitleLike("%"+title.trim()+"%");
			}
			if(!StringUtils.isEmpty(refId)){
				c.andRefIdEqualTo(refId);
			}
			if(!StringUtils.isEmpty(id)){
				c.andIdEqualTo(Integer.parseInt(id));
			}
			if(!StringUtils.isEmpty(source)){
				c.andSourceEqualTo(source);
			}
			if(!StringUtils.isEmpty(productTypeId)){
				c.andProductTypeIdEqualTo(Integer.parseInt(productTypeId));
			}else{
				if(!StringUtils.isEmpty(productType2Id)){
					c.andProductType2IdEqualTo(Integer.parseInt(productType2Id));
				}else if(!StringUtils.isEmpty(productType1Id)){
					c.andProductType1IdEqualTo(Integer.parseInt(productType1Id));
				}
			}
			if(!StringUtils.isEmpty(productCode)){
				c.andProductCodeEqualTo(productCode);
			}
			if(!StringUtils.isEmpty(status)){
				/*c.andStatusEqualTo(status);*/
				c.andStatusEqualTo(status);
			}
			if(!StringUtils.isEmpty(zkFinalPriceMin)){
				c.andZkFinalPriceGreaterThanOrEqualTo(new BigDecimal(zkFinalPriceMin));
			}
			if(!StringUtils.isEmpty(zkFinalPriceMax)){
				c.andZkFinalPriceLessThanOrEqualTo(new BigDecimal(zkFinalPriceMax));
			}
			if(!StringUtils.isEmpty(couponAmountMin)){
				c.andCouponAmountGreaterThanOrEqualTo(new BigDecimal(couponAmountMin));
			}
			if(!StringUtils.isEmpty(couponAmountMax)){
				c.andCouponAmountLessThanOrEqualTo(new BigDecimal(couponAmountMax));
			}
			if(!StringUtils.isEmpty(commissionRateMin)){
				BigDecimal commissionRate = new BigDecimal(commissionRateMin);
				commissionRate = commissionRate.multiply(new BigDecimal(100));//1111.11或者1111
				String commissionRateStr = commissionRate.toString();
				if(commissionRateStr.indexOf(".")>=0){
					commissionRateStr = commissionRateStr.substring(0, commissionRateStr.lastIndexOf("."));//1111
				}
				c.andCommissionRate1GreaterThanOrEqualTo(Integer.parseInt(commissionRateStr));
			}
			if(!StringUtils.isEmpty(commissionRateMax)){
				BigDecimal commissionRate = new BigDecimal(commissionRateMax);
				commissionRate = commissionRate.multiply(new BigDecimal(100));//1111.11或者1111
				String commissionRateStr = commissionRate.toString();
				if(commissionRateStr.indexOf(".")>=0){
					commissionRateStr = commissionRateStr.substring(0, commissionRateStr.lastIndexOf("."));//1111
				}
				c.andCommissionRate1LessThanOrEqualTo(Integer.parseInt(commissionRateStr));
			}
			if(!StringUtils.isEmpty(volume)){
				c.andVolumeGreaterThanOrEqualTo(Integer.parseInt(volume));
			}
			totalCount = thirdPlatformProductInfoService.countByCustomExample(example);
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			List<ThirdPlatformProductInfoCustom> thirdPlatformProductInfoCustoms = thirdPlatformProductInfoService.selectByCustomExample(example);
			resMap.put("Rows", thirdPlatformProductInfoCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 上架/下架
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/taobaoke/changeStatus.shtml")
	@ResponseBody
	public Map<String, Object> changeStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String productId = request.getParameter("productId");
			Product product = productService.selectByPrimaryKey(Integer.parseInt(productId));
			if(product.getStatus().equals("0")){
				product.setStatus("1");
			}else{
				product.setStatus("0");
			}
     		product.setUpdateDate(new Date());
			product.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			
			String id = request.getParameter("id");
			ThirdPlatformProductInfo thirdPlatformProductInfo = thirdPlatformProductInfoService.selectByPrimaryKey(Integer.parseInt(id));
			if(thirdPlatformProductInfo.getStatus().equals("0")){
				thirdPlatformProductInfo.setStatus("1");
			}else{
				thirdPlatformProductInfo.setStatus("0");
			}
			/*thirdPlatformProductInfo.setUpdateDate(new Date());*/
			thirdPlatformProductInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			
			thirdPlatformProductInfoService.update(product, thirdPlatformProductInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 查看页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/taobaoke/toView.shtml")
	public ModelAndView toView(HttpServletRequest request) {
		String rtPage = "taobaoke/toView";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		ThirdPlatformProductInfoCustomExample example = new ThirdPlatformProductInfoCustomExample();
		example.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(id));
		List<ThirdPlatformProductInfoCustom> thirdPlatformProductInfoCustoms = thirdPlatformProductInfoService.selectByCustomExample(example);
		if(thirdPlatformProductInfoCustoms!=null && thirdPlatformProductInfoCustoms.size()>0){
			resMap.put("thirdPlatformProductInfoCustom", thirdPlatformProductInfoCustoms.get(0));
			ProductPicExample productPicExample=new ProductPicExample();
			productPicExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(thirdPlatformProductInfoCustoms.get(0).getProductId());
			productPicExample.setOrderByClause("seq_no asc");
			List<ProductPic> productPics=productPicService.selectByExample(productPicExample);
			resMap.put("pics", productPics);
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 新增页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/taobaoke/toAdd.shtml")
	public ModelAndView toAdd(HttpServletRequest request) {
		String rtPage = "taobaoke/toAdd";
		Map<String, Object> resMap = new HashMap<String, Object>();
		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypeList = productTypeService.selectByExample(pte);
		resMap.put("productTypeList", productTypeList);
		
		//频道
		WetaoChannelExample example = new WetaoChannelExample();
		example.createCriteria().andDelFlagEqualTo("0");
		List<WetaoChannel> wetaoChannels = weitaoChannelService.selectByExample(example );
		resMap.put("wetaoChannels", wetaoChannels);
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 搜索淘宝/天猫商品ID
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/taobaoke/getInfoByRefId.shtml")
	@ResponseBody
	public Map<String, Object> getInfoByRefId(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String refId = request.getParameter("refId");
			JSONArray ja = TaobaoUtil.getItemInfo(refId);
			resMap.put("infoJSONArray", ja);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 获取下级商品分类
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/taobaoke/getProductTypeListByParentId.shtml")
	@ResponseBody
	public Map<String, Object> getProductTypeListByParentId(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String parentId = request.getParameter("parentId");
			ProductTypeExample pte = new ProductTypeExample();
			pte.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andParentIdEqualTo(Integer.parseInt(parentId));
			List<ProductType> productTypes = productTypeService.selectByExample(pte);
			resMap.put("productTypes", productTypes);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 提交保存
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/taobaoke/saveTaobaoProduct.shtml")
	@ResponseBody
	public Map<String, Object> saveTaobaoProduct(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String refId = request.getParameter("refId");
			String productTypeId = request.getParameter("productTypeId");
			String wetaoChannelId = request.getParameter("wetaoChannelId");
			if(StringUtils.isEmpty(wetaoChannelId)){
				wetaoChannelId="-1";
			}
			taobaokeService.createTaobaoProduct(refId, Integer.parseInt(productTypeId),Integer.parseInt(wetaoChannelId));
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 抓取商品页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/taobaoke/toBatchAdd.shtml")
	public ModelAndView toBatchAdd(HttpServletRequest request) {
		String rtPage = "taobaoke/toBatchAdd";
		Map<String, Object> resMap = new HashMap<String, Object>();
		ProductTypeExample pte = new ProductTypeExample();
		pte.setOrderByClause("ifnull(seq_no,999999) asc");
		pte.createCriteria().andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		resMap.put("productTypes", productTypes);
		
		//频道
		WetaoChannelExample example = new WetaoChannelExample();
		example.createCriteria().andDelFlagEqualTo("0");
		List<WetaoChannel> wetaoChannels = weitaoChannelService.selectByExample(example );
		resMap.put("wetaoChannels", wetaoChannels);
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 商品确认列表页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/taobaoke/getProductList.shtml")
	public ModelAndView getProductList(HttpServletRequest request) throws UnsupportedEncodingException {
		String rtPage = "taobaoke/getProductList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String productTypeId = request.getParameter("productTypeId");
		String num = request.getParameter("num");
		String isCoupon = request.getParameter("isCoupon");
		String source = request.getParameter("source");
		String keyword = request.getParameter("keyword");
		String start_price = request.getParameter("start_price");
		String end_price = request.getParameter("end_price");
		String start_tk_rate = request.getParameter("start_tk_rate");
		String end_tk_rate = request.getParameter("end_tk_rate");
		String need_free_shipment = request.getParameter("need_free_shipment");
		String wetaoChannel = request.getParameter("wetaoChannel");
		resMap.put("wetaoChannel", wetaoChannel);
		resMap.put("productTypeId", productTypeId);
		resMap.put("num", num);
		resMap.put("isCoupon", isCoupon);
		resMap.put("source", source);
		ProductType productType = productTypeService.selectByPrimaryKey(Integer.parseInt(productTypeId));
		resMap.put("productType", productType);
		keyword=URLDecoder.decode(keyword, "UTF-8");
		resMap.put("keyword", keyword);
		resMap.put("start_price", start_price);
		resMap.put("end_price", end_price);
		resMap.put("start_tk_rate", start_tk_rate);
		resMap.put("end_tk_rate", end_tk_rate);
		resMap.put("need_free_shipment", need_free_shipment);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 商品列表（淘宝客）列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/taobaoke/getProductData.shtml")
	@ResponseBody
	public Map<String, Object> getProductData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			String productTypeId = request.getParameter("productTypeId");
			String num = request.getParameter("num");
			String isCoupon = request.getParameter("isCoupon");
			String source = request.getParameter("source");
			String keyword = request.getParameter("keyword");
			String start_price = request.getParameter("start_price");
			String end_price = request.getParameter("end_price");
			String start_tk_rate = request.getParameter("start_tk_rate");
			String end_tk_rate = request.getParameter("end_tk_rate");
			String need_free_shipment = request.getParameter("need_free_shipment");
			boolean hasCoupon = false;
			if(!StringUtils.isEmpty(isCoupon)){
				hasCoupon = "1".equals(isCoupon)?true:false;
			}
			TaobaoProductTypePageNo taobaoProductTypePageNo = new TaobaoProductTypePageNo();
			int pageNo = 1;
			TaobaoProductTypePageNoExample example = new TaobaoProductTypePageNoExample();
			example.createCriteria().andProductTypeIdEqualTo(Integer.parseInt(productTypeId));
			List<TaobaoProductTypePageNo> taobaoProductTypePageNos = taobaoProductTypePageNoMapper.selectByExample(example);
			if(taobaoProductTypePageNos!=null && taobaoProductTypePageNos.size()>0){
				taobaoProductTypePageNo = taobaoProductTypePageNos.get(0);
				pageNo = taobaoProductTypePageNo.getPageNo();
			}
			JSONArray nTbkItems = taobaokeService.getTaoBaoProducts(null, hasCoupon, pageNo, Integer.parseInt(num),source,keyword,start_price,end_price,start_tk_rate,end_tk_rate,need_free_shipment);
			if(taobaoProductTypePageNo.getId()!=null){
				if(nTbkItems!=null && nTbkItems.size()>0){
					pageNo++;
					taobaoProductTypePageNo.setPageNo(pageNo);
					taobaoProductTypePageNo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
					taobaoProductTypePageNo.setUpdateDate(new Date());
					taobaoProductTypePageNoMapper.updateByPrimaryKey(taobaoProductTypePageNo);
				}
			}else{
				if(nTbkItems!=null && nTbkItems.size()>0){
					pageNo++;
					taobaoProductTypePageNo.setProductTypeId(Integer.parseInt(productTypeId));
					taobaoProductTypePageNo.setPageNo(pageNo);
					taobaoProductTypePageNo.setDelFlag("0");
					taobaoProductTypePageNo.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
					taobaoProductTypePageNo.setCreateDate(new Date());
					taobaoProductTypePageNoMapper.insertSelective(taobaoProductTypePageNo);
				}
			}
			
			resMap.put("Rows", nTbkItems);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 批量提交保存商品
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/taobaoke/saveTaobaoProductList.shtml")
	@ResponseBody
	public Map<String, Object> saveTaobaoProductList(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String productData = request.getParameter("productData");
			String productTypeId = request.getParameter("productTypeId");
			String delData = request.getParameter("delData");
			String wetaoChannel = request.getParameter("wetaoChannel");//频道
			if(StringUtils.isEmpty(wetaoChannel)){
				wetaoChannel="-1";
			}
			JSONArray nTbkItems = JSONArray.fromObject(productData);
			productService.createTaobaokeProducts(nTbkItems, Integer.parseInt(productTypeId),delData,Integer.parseInt(wetaoChannel));
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 批量上架/下架
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/taobaoke/batchChangeStatus.shtml")
	@ResponseBody
	public Map<String, Object> batchChangeStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String ids = request.getParameter("ids");
			String productIds = request.getParameter("productIds");
			String status = request.getParameter("status");
			
			Product product = new Product();
			product.setStatus(status);
			product.setUpdateDate(new Date());
			product.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			String[] productIdsArray = productIds.split(",");
			List<Integer> productIdList = new ArrayList<Integer>();
			for(String id:productIdsArray){
				productIdList.add(Integer.parseInt(id));
			}
			ProductExample example = new ProductExample();
			example.createCriteria().andDelFlagEqualTo("0").andIdIn(productIdList);
			
			ThirdPlatformProductInfo thirdPlatformProductInfo = new ThirdPlatformProductInfo();
			thirdPlatformProductInfo.setStatus(status);
			/*thirdPlatformProductInfo.setUpdateDate(new Date());*/
			thirdPlatformProductInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			String[] idsArray = ids.split(",");
			List<Integer> idList = new ArrayList<Integer>();
			for(String id:idsArray){
				idList.add(Integer.parseInt(id));
			}
			ThirdPlatformProductInfoExample e = new ThirdPlatformProductInfoExample();
			e.createCriteria().andDelFlagEqualTo("0").andIdIn(idList);
			thirdPlatformProductInfoService.update(product, example,thirdPlatformProductInfo,e,productIdList);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 根据上级id获取商品分类
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/taobaoke/getProductTypesByParentId.shtml")
	@ResponseBody
	public Map<String, Object> getProductTypesByParentId(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String parentId = request.getParameter("parentId");
			ProductTypeExample example = new ProductTypeExample();
			example.setOrderByClause("seq_no asc");
			example.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andParentIdEqualTo(Integer.parseInt(parentId));
			List<ProductType> productTypeList = productTypeService.selectByExample(example);
			resMap.put("productTypeList", productTypeList);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 选品库列表页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/taobaoke/favoritesList.shtml")
	public ModelAndView getFavoritesList(HttpServletRequest request) throws UnsupportedEncodingException {
		String rtPage = "taobaoke/favoritesList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 选品库列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/taobaoke/getFavoritesData.shtml")
	@ResponseBody
	public Map<String, Object> getFavoritesData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String type = request.getParameter("type");
			String fields = "favorites_title,favorites_id,type";
			if(StringUtils.isEmpty(type)){
				type = "-1";
			}
			JSONObject jo = TaobaoUtil.getFavorites(fields, Integer.parseInt(type), page.getPage(), page.getPagesize());
			resMap.put("Rows", jo.getJSONArray("Rows"));
			resMap.put("Total", jo.getInt("Total"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 一键抓取页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/taobaoke/toBatchCatch.shtml")
	public ModelAndView toBatchCatch(HttpServletRequest request) throws UnsupportedEncodingException {
		String rtPage = "taobaoke/toBatchCatch";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String favoritesId = request.getParameter("favoritesId");
		resMap.put("favoritesId", favoritesId);
		ProductTypeExample e = new ProductTypeExample();
		e.setOrderByClause("ifnull(seq_no,999999) asc");
		e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
		List<ProductType> productTypes = productTypeService.selectByExample(e);
		resMap.put("productTypes", productTypes);
		//频道
		WetaoChannelExample example = new WetaoChannelExample();
		example.createCriteria().andDelFlagEqualTo("0");
		List<WetaoChannel> wetaoChannels = weitaoChannelService.selectByExample(example );
		resMap.put("wetaoChannels", wetaoChannels);
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 一键抓取，保存商品
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/taobaoke/batchCatch.shtml")
	@ResponseBody
	public Map<String, Object> batchCatch(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String wetaoChannel = request.getParameter("wetaoChannel");//频道
			if(StringUtils.isEmpty(wetaoChannel)){
				wetaoChannel="-1";
			}
			String favoritesId = request.getParameter("favoritesId");
			String productTypeId = request.getParameter("productTypeId");
			taobaokeService.createTaobaoProduct(Integer.parseInt(productTypeId),Integer.parseInt(favoritesId),1, 100,Integer.parseInt(wetaoChannel));
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 保存排序
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/taobaoke/thirdPlatformProductInfo/saveSeqNo.shtml")
	@ResponseBody
	public Map<String, Object> saveSeqNo(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String id = request.getParameter("id");
			String seqNo = request.getParameter("seqNo");
			ThirdPlatformProductInfo thirdPlatformProductInfo = thirdPlatformProductInfoService.selectByPrimaryKey(Integer.parseInt(id));
			thirdPlatformProductInfo.setSeqNo(Integer.parseInt(seqNo));
			thirdPlatformProductInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			/*thirdPlatformProductInfo.setUpdateDate(new Date());*/
			thirdPlatformProductInfoService.updateByPrimaryKeySelective(thirdPlatformProductInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	/**
	 * 保存SeqNo为空的排序
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/taobaoke/thirdPlatformProductInfo/saveNullSeqNo.shtml")
	@ResponseBody
	public Map<String, Object> saveNullSeqNo(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String id = request.getParameter("id");
			String seqNo = request.getParameter("seqNo");
			int Pid = Integer.parseInt(id);
			thirdPlatformProductInfoService.updateNullSeqNo(Pid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	
	
	
	/**
	 * 设置上下架时间页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/taobaoke/toBatchSetDate.shtml")
	public ModelAndView toBatchSetDate(HttpServletRequest request) {
		String rtPage = "taobaoke/toBatchSetDate";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("ids", request.getParameter("ids"));
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 设置上下架时间
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/taobaoke/batchSetDate.shtml")
	@ResponseBody
	public Map<String, Object> batchSetDate(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String ids = request.getParameter("ids");
			String autoUpDate = request.getParameter("autoUpDate");
			String autoDownDate = request.getParameter("autoDownDate");
			List<Integer> idList = new ArrayList<Integer>();
			for(String idStr:ids.split(",")){
				idList.add(Integer.parseInt(idStr));
			}
			ThirdPlatformProductInfoExample e = new ThirdPlatformProductInfoExample();
			e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andIdIn(idList);
			ThirdPlatformProductInfo thirdPlatformProductInfo = new ThirdPlatformProductInfo();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			thirdPlatformProductInfo.setAutoUpDate(sdf.parse(autoUpDate+" 00:00:00"));
			thirdPlatformProductInfo.setAutoDownDate(sdf.parse(autoDownDate+" 23:59:59"));
			thirdPlatformProductInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			/*thirdPlatformProductInfo.setUpdateDate(new Date());*/
			thirdPlatformProductInfoService.updateByExampleSelective(thirdPlatformProductInfo, e);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 编辑页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/taobaoke/toEdit.shtml")
	public ModelAndView toEdit(HttpServletRequest request) {
		String rtPage = "taobaoke/toEdit";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		ThirdPlatformProductInfoCustomExample example = new ThirdPlatformProductInfoCustomExample();
		example.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(id));
		List<ThirdPlatformProductInfoCustom> thirdPlatformProductInfoCustoms = thirdPlatformProductInfoService.selectByCustomExample(example);
		if(thirdPlatformProductInfoCustoms!=null && thirdPlatformProductInfoCustoms.size()>0){
			ThirdPlatformProductInfoCustom thirdPlatformProductInfoCustom = thirdPlatformProductInfoCustoms.get(0);
			resMap.put("thirdPlatformProductInfoCustom", thirdPlatformProductInfoCustom);
			ProductPicExample productPicExample=new ProductPicExample();
			productPicExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(thirdPlatformProductInfoCustoms.get(0).getProductId());
			productPicExample.setOrderByClause("seq_no asc");
			List<ProductPic> productPics=productPicService.selectByExample(productPicExample);
			resMap.put("pics", productPics);
			for(ProductPic productPic:productPics){
				if(!StringUtils.isEmpty(productPic.getIsDefault()) && productPic.getIsDefault().equals("1")){
					resMap.put("isDefault", productPic.getPic());
					break;
				}
			}
			ProductTypeExample e = new ProductTypeExample();
			e.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(thirdPlatformProductInfoCustom.getProductType1Id()).andStatusEqualTo("1");
			List<ProductType> productTypeList = productTypeService.selectByExample(e);
			resMap.put("productTypeList", productTypeList);
			
			ProductTypeExample productTypeExample = new ProductTypeExample();
			productTypeExample.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(thirdPlatformProductInfoCustom.getProductType2Id()).andStatusEqualTo("1");
			List<ProductType> pts = productTypeService.selectByExample(productTypeExample);
			resMap.put("pts", pts);
		}
		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		resMap.put("productTypes", productTypes);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 提交保存
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/taobaoke/saveThirdPlatformProductInfo.shtml")
	@ResponseBody
	public Map<String, Object> saveThirdPlatformProductInfo(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String productType1Id = request.getParameter("productType1Id");
			String productType2Id = request.getParameter("productType2Id");
			String productTypeId = request.getParameter("productTypeId");
			String autoUpDate = request.getParameter("autoUpDate");
			String autoDownDate = request.getParameter("autoDownDate");
			String seqNo = request.getParameter("seqNo");
			String imgs = request.getParameter("imgs");
			ThirdPlatformProductInfo thirdPlatformProductInfo = thirdPlatformProductInfoService.selectByPrimaryKey(Integer.parseInt(id));
			thirdPlatformProductInfo.setTitle(title);
			thirdPlatformProductInfo.setProductType1Id(Integer.parseInt(productType1Id));
			thirdPlatformProductInfo.setProductType2Id(Integer.parseInt(productType2Id));
			thirdPlatformProductInfo.setProductType3Id(Integer.parseInt(productTypeId));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtils.isEmpty(autoUpDate)){
				thirdPlatformProductInfo.setAutoUpDate(sdf.parse(autoUpDate+" 00:00:00"));
			}
			if(!StringUtils.isEmpty(autoDownDate)){
				thirdPlatformProductInfo.setAutoDownDate(sdf.parse(autoDownDate+" 23:59:59"));
			}
			if(!StringUtils.isEmpty(seqNo)){
				thirdPlatformProductInfo.setSeqNo(Integer.parseInt(seqNo));
			}
			thirdPlatformProductInfoService.save(thirdPlatformProductInfo,imgs);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	
	
	/**
	 * 频道批量修改页面
	 * @param request
	 * @param response
	 * @param platformMsgId
	 * @return
	 */
	@RequestMapping("/taobaoke/toBatchModification.shtml")
	@ResponseBody
	public ModelAndView toBatchModification(HttpServletRequest request) {
		String rtPage = "taobaoke/toBatchModification";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String productIds = request.getParameter("productIds");
		if(StringUtils.isEmpty(productIds)){
			resMap.put("productIds", productIds);
		}
		//频道
		WetaoChannelExample example = new WetaoChannelExample();
		example.createCriteria().andDelFlagEqualTo("0");
		List<WetaoChannel> WetaoChannels = weitaoChannelService.selectByExample(example );
		resMap.put("WetaoChannels", WetaoChannels);
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 频道的批量修改
	 * @param request
	 * @param response
	 * @param platformMsgId
	 * @return
	 */
	@RequestMapping("/taobaoke/batchModificationEdit.shtml")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String productIds = request.getParameter("productIds");
		String channelId = request.getParameter("channelId");
		try{
			if(!StringUtils.isEmpty(productIds)&&!StringUtils.isEmpty(channelId)){
			String[] split = productIds.split(",");
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());		
			for (int i = 0; i < split.length; i++) {
				WetaoChannelProductExample example = new WetaoChannelProductExample();
				example.createCriteria().andProductIdEqualTo(Integer.parseInt(split[i])).andDelFlagEqualTo("0");
				List<WetaoChannelProduct> WetaoChannelProducts = weitaoChannelProductService.selectByExample(example);	
				if(WetaoChannelProducts==null||WetaoChannelProducts.size()<=0){
					WetaoChannelProduct wetaoChannelProduct = new WetaoChannelProduct();
					wetaoChannelProduct.setDelFlag("0");
					wetaoChannelProduct.setChannelId(Integer.parseInt(channelId));
					wetaoChannelProduct.setProductId(Integer.parseInt(split[i]));
					wetaoChannelProduct.setCreateBy(staffId);
					wetaoChannelProduct.setCreateDate(new Date());
					weitaoChannelProductService.insertSelective(wetaoChannelProduct);
				}else{
				WetaoChannelProduct wetaoChannelProduct = WetaoChannelProducts.get(0);
				wetaoChannelProduct.setChannelId(Integer.parseInt(channelId));
				/*wetaoChannelProduct.setUpdateDate(new Date());*/
				wetaoChannelProduct.setUpdateBy(staffId);
				weitaoChannelProductService.updateByPrimaryKeySelective(wetaoChannelProduct);
					}
				}
			}
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "批量修改失败，请稍后重试");
			return resMap;
		}
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		return resMap;
		
	}
	
	
	
	
}
