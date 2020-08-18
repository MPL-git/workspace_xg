package com.jf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoExample;
import com.jf.entity.ProductType;
import com.jf.service.MchtInfoService;
import com.jf.service.ProductBrandService;
import com.jf.service.ProductTypeService;
import com.jf.service.ShopDecorateInfoService;
import com.jf.service.ShoppingMallService;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月19日 下午2:27:38 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class ShoppingMallController extends BaseController{
	@Resource
	private ShoppingMallService shoppingMallService;
	@Resource
	private ProductTypeService productTypeService;
	@Resource
	private ProductBrandService productBrandService;
	@Resource
	private ShopDecorateInfoService shopDecorateInfoService;
	@Resource
	private MchtInfoService mchtInfoService;
	/**
	 * (废弃：2018-07-18：商城二期改版改用getShoppingMallCategoryData)
	 * 方法描述 ：商城首页数据展示
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月1日 下午1:53:57 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getShoppingMallData")
	@ResponseBody
	public ResponseMsg getShoppingMallData(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"currentPage"};
			this.requiredStr(obj,request);
			Integer pageSize = Const.RETURN_SIZE_10;
			Map<String, Object> map = shoppingMallService.getShoppingMallData(reqPRM,reqDataJson,pageSize);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：商家店铺基本信息
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月2日 上午9:35:18 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getMchtShoppingInfo")
	@ResponseBody
	public ResponseMsg getMchtShoppingInfo(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"mchtId"};
			this.requiredStr(obj,request);
			Map<String, Object> map = shoppingMallService.getMchtShoppingInfo(reqPRM,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	/**
	 * 
	 * 方法描述 ：商店店铺商品信息
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月2日 上午9:35:38 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getMchtShoppingProductList")
	@ResponseBody
	public ResponseMsg getMchtShoppingProductList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"currentPage","mchtId"};
			this.requiredStr(obj,request);
			Integer pageSize = Const.RETURN_SIZE_10;
			Map<String, Object> map = shoppingMallService.getMchtShoppingProductList(reqPRM,reqDataJson,pageSize);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：商家商品分类
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月2日 上午11:09:28 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getMchtShoppingProductClass")
	@ResponseBody
	public ResponseMsg getMchtShoppingProductClass(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"mchtId"};
			this.requiredStr(obj,request);
			List<Map<String, Object>> dataList = shoppingMallService.getMchtShoppingProductClass(reqPRM,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,dataList);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取商城类目（左侧数据）
	 * @author  chenwf: 
	 * @date 创建时间：2018年7月18日 下午1:27:32 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getShoppingMallCategory")
	@ResponseBody
	public ResponseMsg getShoppingMallCategory(HttpServletRequest request){
		try {
			Map<String, Object> map = shoppingMallService.getShoppingMallCategory();
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取商城类目数据（右侧数据）
	 * @author  chenwf: 
	 * @date 创建时间：2018年7月18日 下午1:27:32 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getShoppingMallCategoryData")
	@ResponseBody
	public ResponseMsg getShoppingMallCategoryData(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Map<String, Object> map = shoppingMallService.getShoppingMallCategoryData(reqPRM,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 商城二期版本  商城商品列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getShoppingMallProductListData")
	@ResponseBody
	public ResponseMsg getShoppingMallProductListData(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Integer pageSize = Const.RETURN_SIZE_10;
			Integer currentPage=reqDataJson.getInt("currentPage");
			Map<String,Object> map = new HashMap<String,Object>();
			if(currentPage>=101){//性能问题，搜索结果只取前100页
				List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
				map.put("dataList", dataList);
			}else{
				map = shoppingMallService.getShoppingMallProductListData(reqPRM,reqDataJson,pageSize);
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 获取商品分类
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getCategoryList")
	@ResponseBody
	public ResponseMsg getCategoryList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Integer productTypeId = null;
			if(reqDataJson.containsKey("productTypeId") && !StringUtil.isBlank(reqDataJson.getString("productTypeId"))){
				productTypeId = reqDataJson.getInt("productTypeId");
			}
			Map<String, Object> map = productTypeService.getCategoryList(productTypeId);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 获取商品品牌列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getShopMallProductBrandList")
	@ResponseBody
	public ResponseMsg getAllBrandList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			//根据搜索词拆成几个关键词
			List<String> wordList = new ArrayList<String>();
			//类目id
			Integer productTypeId= null;
			Integer productTypeOneId= null;
			Integer productTypeTwoId= null;
			Integer productTypeThreeId= null;
			//品牌
			List<Integer> brandIdList = new ArrayList<Integer>();
			//类目id 查询该类目是属于几级类目id
			if(reqDataJson.containsKey("productTypeId") && !StringUtil.isBlank(reqDataJson.getString("productTypeId"))){
				productTypeId = reqDataJson.getInt("productTypeId");
				ProductType productType = productTypeService.selectByPrimaryKey(productTypeId);
				if(productType != null && productType.getId() != null){
					if("1".equals(productType.gettLevel())){
						productTypeOneId = productTypeId;
					}else if("2".equals(productType.gettLevel())){
						productTypeTwoId = productTypeId;
					}else if("3".equals(productType.gettLevel())){
						productTypeThreeId = productTypeId;
					}else{
						productTypeId = null;
					}
				}else{
					productTypeId = null;
				}
			}
			if(reqDataJson.containsKey("brandId") && !StringUtil.isBlank(reqDataJson.getString("brandId"))){
				for (String id : reqDataJson.getString("brandId").split(",")) {
					brandIdList.add(Integer.valueOf(id));
				}
			}
			if(reqDataJson.containsKey("searchName") && !StringUtil.isBlank(reqDataJson.getString("searchName"))){
				wordList = StringUtil.seg(reqDataJson.getString("searchName").replace(";", "").trim());//数据库的商品搜索域用;号作为各个字段的分隔符，所以过滤掉;
				if(wordList.size() > 4){
					wordList.subList(0, 4);//只取前面3个
				}
			}
			String orderByType = "2";//2查询全部且是根据首字母排序
			paramsMap.put("wordList", wordList);
			paramsMap.put("productTypeOneId", productTypeOneId);
			paramsMap.put("productTypeTwoId", productTypeTwoId);
			paramsMap.put("productTypeThreeId", productTypeThreeId);
			paramsMap.put("orderByType", orderByType);
			paramsMap.put("brandIdList", brandIdList);
			Map<String, Object> map = productBrandService.getShopMallProductBrandMap(paramsMap,orderByType,reqPRM);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	
	
	/**
	 * 店铺——装修信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getShopDecorateInfo")
	@ResponseBody
	public ResponseMsg getShopDecorateInfo(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Integer mchtId = null;
			if(reqDataJson.containsKey("mchtId") && !StringUtil.isBlank(reqDataJson.getString("mchtId"))){
				mchtId = reqDataJson.getInt("mchtId");
			}else if(reqDataJson.containsKey("mchtCode") && !StringUtil.isBlank(reqDataJson.getString("mchtCode"))){
				MchtInfoExample mchtInfoExample = new MchtInfoExample();
				mchtInfoExample.createCriteria().andMchtCodeEqualTo(reqDataJson.getString("mchtCode"));
				List<MchtInfo> mchtInfos = mchtInfoService.selectByExample(mchtInfoExample);
				if(CollectionUtils.isNotEmpty(mchtInfos)){
					mchtId = mchtInfos.get(0).getId();
				}else{
					throw new ArgException("店铺已关闭");
				}
			}else{
				throw new ArgException("商家id不能为空");
			}
			Map<String, Object> resMap = new HashMap<>();
			resMap.put("mchtId", mchtId);
			Map<String, Object> map = shopDecorateInfoService.getShopDecorateInfo(resMap);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 店铺——商品列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getShopProductList")
	@ResponseBody
	public ResponseMsg getShopProductList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"currentPage","type"};
			this.requiredStr(obj,request);
			Integer pageSize = Const.RETURN_SIZE_10;
			Map<String, Object> map = shoppingMallService.getShopProductList(reqPRM,reqDataJson,pageSize);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 店铺——特卖列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getShopActivityList")
	@ResponseBody
	public ResponseMsg getShopActivityList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"mchtId","currentPage"};
			this.requiredStr(obj,request);
			Integer pageSize = Const.RETURN_SIZE_10;
			Map<String, Object> map = shoppingMallService.getShopActivityList(reqPRM,reqDataJson,pageSize);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	
	/**
	 * 店铺——商家店铺信息页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getMchtShopInfo")
	@ResponseBody
	public ResponseMsg getMchtShopInfo(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Map<String, Object> map = shoppingMallService.getMchtShopInfo(reqPRM,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
	
	/**
	 * 店铺——故事详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getshopStoryDetail")
	@ResponseBody
	public ResponseMsg getshopStoryDetail(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Map<String, Object> map = shoppingMallService.getshopStoryDetail(reqPRM,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
}
