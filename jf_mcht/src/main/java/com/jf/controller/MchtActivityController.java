package com.jf.controller;

import com.alibaba.fastjson.JSONArray;
import com.jf.bean.ExcelBean;
import com.jf.common.base.ResponseMsg;
import com.jf.common.enums.ActivityStatusEnum;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtil;
import com.jf.controller.command.mcht.*;
import com.jf.dao.ActivityProductDepositMapper;
import com.jf.dao.ActivityProductMapper;
import com.jf.entity.*;
import com.jf.service.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 商家品牌特卖活动
 *
 * @author huangdl
 */
@Controller
@RequestMapping("mcht/activity")
public class MchtActivityController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(MchtActivityController.class);
	
	@Resource
	private ActivityAreaService activityAreaService;
	@Resource
	private ActivityService activityService;
	@Resource
	private ActivityProductService activityProductService;
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	@Resource
	private ProductService productService;
	@Resource
	private ActivityProductMapper activityProductMapper;
	@Resource
	private ProductTypeService productTypeService;
	@Resource
	private ActivityProductDepositMapper activityProductDepositMapper;
	@Resource
	private ProductItemService productItemService;
	@Resource
	private ActivityAreaPreSellRuleService activityAreaPreSellRuleService;
	@Resource
	private ActivityProductDepositService activityProductDepositService;
	@Resource
	private MchtInfoService mchtInfoService;
	@Resource
	private ProductPriceChangeLogService productPriceChangeLogService;
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@RequestMapping
	public String index() {
		Map<String, Object> data = new HashMap<>();
		data.put("brandList", mchtProductBrandService.getMchtProductBrandList(getUserInfo().getMchtId()));	// 品牌
		data.put("statusList", ActivityStatusEnum.list);	// 活动状态
		data.put("pageNumber", getParaToInt("pageNumber"));
		try {
			String name = getPara("name");
			if(!StringUtil.isEmpty(name)){
				data.put("name", URLDecoder.decode(name, "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		data.put("productBrandId", getParaToInt("productBrandId"));
		data.put("selectedStatus", getPara("status"));
		return page(data,"mchtActivity/list");
	}

	/**
	 * 活动列表
	 */
	@ResponseBody
	@RequestMapping("list")
	public String list() {
		
		return json(doAction(new CMchtActivityList()));
	}

	/**
	 * 查看活动页面
	 * 要根据商家类型选择性展示--结算价等
	 */
	@RequestMapping("view")
	public String view() {
		Map<String, Object> map = doAction(new CMchtActivityView());
		MchtUser usr = this.getUserInfo();
		//商家类型
		map.put("mchtInfo",mchtInfoService.selectByPrimaryKey(usr.getMchtId()));
		return page(map, "mchtActivity/view");
	}
	
	/**
	 * 添加活动页面  根据商家类型判断选择性展示
	 */
	@RequestMapping("edit")
	public String edit() {
		Map<String, Object> map = doAction(new CMchtActivityEdit());
		MchtUser usr = this.getUserInfo();
		// 此页面品牌下拉数据过滤是否特卖条件
		map.put("productBrandList", mchtProductBrandService.getActivityMchtProductBrandList(usr.getMchtId()));	// 品牌
		map.put("mchtInfo",mchtInfoService.selectByPrimaryKey(usr.getMchtId()));
		return page(map, "mchtActivity/edit");
	}

	/**
	 * 保存活动
	 */
	@ResponseBody
	@RequestMapping("save")
	public String save() {

		return json(doAction(new CMchtActivitySave()));
	}

	/**
	 * 删除活动
	 */
	@ResponseBody
	@RequestMapping("delete")
	public String delete(String ids) {
		Assert.notBlank(ids, "ID不能为空");

		MchtUser mchtUser = getUserInfo();
		JSONArray idArray = JSONArray.parseArray(ids);
		for (int index = 0; index < idArray.size(); index ++) {
			activityAreaService.delete(mchtUser.getMchtId(), idArray.getIntValue(index));
		}

		return json();
	}
    //特卖活动商品管理  spop商家新增结算价展示
	@RequestMapping("listProductPage")
	public String listProductPage() {
		Map<String, Object> map = doAction(new CActivityListProductPage());
		MchtUser usr = this.getUserInfo();
		//商家类型
		map.put("mchtInfo",mchtInfoService.selectByPrimaryKey(usr.getMchtId()));
		return page(map, "mchtActivity/listProduct");
	}

	/**
	 * 活动商品列表
	 */
	@ResponseBody
	@RequestMapping("listProduct")
	public String listProduct() {

		return json(doAction(new CActivityListProduct()));
	}

	/**
	 * 添加活动商品
	 */
	@ResponseBody
	@RequestMapping("addProduct")
	public String addProduct(Integer activityId, String productIds,String isPreSell,String productIdsAdvanceSale,String productStatus) {
		Assert.moreThanZero(activityId, "活动ID不能为空");
		Assert.notBlank(productIds, "商品ID不能为空");
		MchtUser mchtUser = getUserInfo();
		Activity activity = activityService.findById(activityId);
		if(activity == null || !activity.getMchtId().equals(mchtUser.getMchtId())){
			throw new BizException("未找到该活动");
		}
		if(StringUtils.equals(isPreSell,"1")){//预售会场
			JSONArray idArray = JSONArray.parseArray(productIdsAdvanceSale);
				//用于记录报名不成功的商品数量
				int failCount = 0;
				int successCount = 0;
				//判断是否符合预售会场报名规则
				ActivityAreaPreSellRuleExample activityAreaPreSellRuleExample = new ActivityAreaPreSellRuleExample();
				activityAreaPreSellRuleExample.createCriteria().andDelFlagEqualTo("0").andActivityAreaIdEqualTo(activity.getActivityAreaId());
				ActivityAreaPreSellRule activityAreaPreSellRule = activityAreaPreSellRuleService.selectByExample(activityAreaPreSellRuleExample).get(0);
				//用于存可以报名的商品信息
				List<com.alibaba.fastjson.JSONObject> product = new ArrayList<>();
				String returnMessage = "";
				for (int index = 0; index < idArray.size(); index ++) {
					int productId = idArray.getJSONObject(index).getIntValue("productId");
					double payInAdvance = 0.00;
					double deduction = 0.00;
					if(!StringUtils.equals(productStatus,"3")){//预售会场商品第一次报名
						payInAdvance = idArray.getJSONObject(index).getDoubleValue("payInAdvance");//定金金额
						deduction = idArray.getJSONObject(index).getDoubleValue("deduction");//抵扣金额
					}else{//预售会场商品驳回后报名
						//查询预售商品的定金金额跟抵扣金额
						ActivityProductDepositExample activityProductDepositExample = new ActivityProductDepositExample();
						activityProductDepositExample.createCriteria().andDelFlagEqualTo("0").andActivityIdEqualTo(activityId).andProductIdEqualTo(productId);
						List<ActivityProductDeposit> activityProductDeposits = activityProductDepositService.selectByExample(activityProductDepositExample);
						payInAdvance = activityProductDeposits.get(0).getDeposit().doubleValue();//定金金额
						deduction = activityProductDeposits.get(0).getDeductAmount().doubleValue();//抵扣金额
					}
					double activityPriceLimit = activityAreaPreSellRule.getActivityPriceLimit().doubleValue();//预付商品金额
					double depositLimit = activityAreaPreSellRule.getDepositLimit().doubleValue();//定金金额
					//查询活动价
					ProductItemExample productItemExample = new ProductItemExample();
					productItemExample.createCriteria().andProductIdEqualTo(productId).andDelFlagEqualTo("0");
					productItemExample.setOrderByClause(" sale_price asc");
					List<ProductItem> productItems = productItemService.selectByExample(productItemExample);
					double salePrice = productItems.get(0).getSalePrice().doubleValue();

					//预付商品金额、定金金额判断
					if(salePrice < activityPriceLimit){
						failCount++;
						returnMessage = "预付商品金额不可低于"+activityPriceLimit;
						continue;
					}
					if(payInAdvance < depositLimit){
						failCount++;
						returnMessage = "定金金额不可低于"+depositLimit;
						continue;
					}
					if(payInAdvance<salePrice*((double)activityAreaPreSellRule.getMinRate()/100) || payInAdvance>salePrice*((double)activityAreaPreSellRule.getMaxRate()/100)){
						failCount++;
						returnMessage = "定金应在预付商品价格"+activityAreaPreSellRule.getMinRate()+"% 至 "+activityAreaPreSellRule.getMaxRate()+"%之间";
						continue;
					}

					DecimalFormat df = new DecimalFormat("0.00");
					double v = 0.00;
					try {
						v = df.parse(df.format(payInAdvance * activityAreaPreSellRule.getOffsetMultiple().doubleValue())).doubleValue();
					} catch (ParseException e) {
						e.printStackTrace();
					}
					if(deduction/v<1){
						failCount++;
						returnMessage = "定金抵扣倍数应高于等于"+activityAreaPreSellRule.getOffsetMultiple()+"倍";
						continue;
					}
					product.add(idArray.getJSONObject(index));
					ActivityProduct activityProduct = activityProductService.addProduct(mchtUser.getMchtId(), activityId, productId,isPreSell,productStatus,idArray.getJSONObject(index));
					if(activityProduct != null){
						successCount++;
					}
				}
				Map<String,Object> map = new HashMap<>();
				//如果是单个商品提示对应的错误，批量的话提示成功跟失败的商品数
				if(idArray.size() == 1){
					map.put("message",returnMessage);
					map.put("returnType","0");
					return json(map);
				}else{
					map.put("message","报名成功商品数："+successCount+",报名失败商品数："+failCount);
					map.put("returnType","1");
					return json(map);
				}
		}else{
			JSONArray idArray = JSONArray.parseArray(productIds);
			for (int index = 0; index < idArray.size(); index ++) {
				activityProductService.addProduct(mchtUser.getMchtId(), activityId, idArray.getIntValue(index),null,null,null);
			}
		}
		return json();
	}

	/**
	 * 退出活动商品
	 */
	@ResponseBody
	@RequestMapping("cancelProduct")
	public String cancelProduct(Integer activityId, String productIds) {
		Assert.moreThanZero(activityId, "活动ID不能为空");
		Assert.notBlank(productIds, "商品ID不能为空");

		MchtUser mchtUser = getUserInfo();
		Activity activity = activityService.findById(activityId);
		if(activity == null || !activity.getMchtId().equals(mchtUser.getMchtId())){
			throw new BizException("未找到该活动");
		}
		ActivityArea activityArea = activityAreaService.findById(activity.getActivityAreaId());
		if(activityArea.getActivityEndTime()!=null && activityArea.getActivityEndTime().before(new Date())){
			throw new BizException("活动已经结束，无需再次退出活动");
		}
		JSONArray idArray = JSONArray.parseArray(productIds);
		for (int index = 0; index < idArray.size(); index ++) {
			activityProductService.delProduct(activityId, idArray.getIntValue(index),activityArea.getIsPreSell());
		}

		return json();
	}

	/**
	 * 提交活动
	 */
	@ResponseBody
	@RequestMapping("commitActivity")
	public String commitActivity() {

		return json(doAction(new CActivityCommit()));
	}
	
	/**
	 * 获取排序预览的商品
	 */
	@ResponseBody
	@RequestMapping("getProducts")
	public JSONObject getProducts(HttpServletRequest request,Integer activityId) {
		Activity activity = activityService.findById(activityId);
		List<ProductCustom> productCustoms = productService.getProductsByActivityId(activityId);
		net.sf.json.JSONArray ja = new net.sf.json.JSONArray();
		for(ProductCustom productCustom:productCustoms){
			JSONObject jo = new JSONObject();
			jo.put("id", productCustom.getId());
			jo.put("activityProductId", productCustom.getActivityProductId());
			jo.put("name", productCustom.getName());
			jo.put("img", productCustom.getPic());
			jo.put("price", productCustom.getSalePriceMin());
			jo.put("cost", productCustom.getTagPriceMin());
			java.text.DecimalFormat df =new java.text.DecimalFormat("#.0"); 
			BigDecimal discount = productCustom.getSalePriceMin().divide(productCustom.getTagPriceMin(),10,BigDecimal.ROUND_HALF_DOWN);
			jo.put("discount",df.format(discount.multiply(new BigDecimal(10))));
			ja.add(jo);
		}
		JSONObject data = new JSONObject();
		data.put("list", ja);
		data.put("posterPic", activity.getPosterPic());
		return data;
	}
	
	/**
	 * 保存排序结果
	 */
	@ResponseBody
	@RequestMapping("saveSort")
	public String saveSort(HttpServletRequest request,String activityProductIds) {
		String[] activityProductIdArray = activityProductIds.split(",");
		String dataStr="";
		for(int i=0;i<activityProductIdArray.length;i++){
			if(i!=activityProductIdArray.length-1){
				dataStr+=activityProductIdArray[i]+","+(i+1)+"|";
			}else{
				dataStr+=activityProductIdArray[i]+","+(i+1);
			}
		}
		Integer mchtId = this.getSessionMchtInfo(request).getId();
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("data", dataStr);
		map.put("mchtId", mchtId);
		activityProductService.saveSort(map);
		return json();
	}
	
	/**
	 * 获取二级类目
	 */
	@ResponseBody
	@RequestMapping("getSecondProductType")
	public JSONObject getSecondProductType(HttpServletRequest request) {
		String productTypeId = request.getParameter("productTypeId");
		ProductTypeExample example = new ProductTypeExample();
		ProductTypeExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andParentIdEqualTo(Integer.parseInt(productTypeId));
		List<ProductType> productTypes = productTypeService.selectByExample(example);
		net.sf.json.JSONArray ja = new net.sf.json.JSONArray();
		for(ProductType productType:productTypes){
			JSONObject jo = new JSONObject();
			jo.put("productTypeSecondId", productType.getId());
			jo.put("name", productType.getName());
			ja.add(jo);
		}
		JSONObject data = new JSONObject();
		data.put("list", ja);
		return data;
	}
	
	/**
	 * 
	 * @Title seeDesign   
	 * @Description TODO(设计驳回查看详情)   
	 * @author pengl
	 * @date 2018年3月6日 下午5:50:30
	 */
	@RequestMapping("seeDesign")
	public String seeDesign(HttpServletRequest request, Model model) {
		String activityId = request.getParameter("activityId");
		Activity activity = activityService.selectByPrimaryKey(Integer.parseInt(activityId));
		model.addAttribute("activity", activity);
		return "mchtActivity/seeDesign";
	}
	
	/**
	 * 
	 * @Title updateDesignImg   
	 * @Description TODO(修改图片)   
	 * @author pengl
	 * @date 2018年3月7日 上午9:24:54
	 */
	@RequestMapping("updateDesignImg")
	public String updateDesignImg(HttpServletRequest request, Model model) {
		String activityId = request.getParameter("activityId");
		String statusSource = request.getParameter("statusSource"); //1：会场   2：特卖
		Activity activity = activityService.selectByPrimaryKey(Integer.parseInt(activityId));
		model.addAttribute("activity", activity);
		model.addAttribute("statusSource", statusSource);
		return "mchtActivity/updateDesignImg";
	}
	/**
	 * 
	 * @Title saveDesignImg   
	 * @Description TODO(保存修改图片)   
	 * @author pengl
	 * @date 2018年3月7日 上午11:04:38
	 */
	@ResponseBody
	@RequestMapping("saveDesignImg")
	public Map<String, Object> saveDesignImg(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String message = "操作成功！";
		try {
			String statusSource = request.getParameter("statusSource"); //1：会场   2：特卖
			String activityId = request.getParameter("id");
			String entryPic = request.getParameter("entryPic");
			String posterPic = request.getParameter("posterPic");
			String brandTeamPic = request.getParameter("brandTeamPic");
			activityService.saveDesignImg(statusSource, activityId, entryPic, posterPic,brandTeamPic);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			message = "系统错误！";
		}
		map.put("code", code);
		map.put("message", message);
		return map;
	}
	
	/**
	 * 预售设置页面
	 */
	@RequestMapping("toActivityProductDeposit")
	public String toActivityProductDeposit(HttpServletRequest request, Model model) {
		String activityId = request.getParameter("activityId");
		String productId = request.getParameter("productId"); //1：会场   2：特卖
		model.addAttribute("activityId", activityId);
		model.addAttribute("productId", productId);
		ActivityProductDepositExample e = new ActivityProductDepositExample();
		e.createCriteria().andDelFlagEqualTo("0").andActivityIdEqualTo(Integer.parseInt(activityId)).andProductIdEqualTo(Integer.parseInt(productId));
		List<ActivityProductDeposit> activityProductDeposits = activityProductDepositMapper.selectByExample(e);
		if(activityProductDeposits!=null && activityProductDeposits.size()>0){
			model.addAttribute("activityProductDeposit", activityProductDeposits.get(0));
		}
		ProductCustom productCustom = productService.selectProductCustomByPrimaryKey(Integer.parseInt(productId));
		model.addAttribute("salePriceMin", productCustom.getSalePriceMin());
		model.addAttribute("salePriceMax", productCustom.getSalePriceMax());
		return "mchtActivity/toActivityProductDeposit";
	}
	
	/**
	 * 保存预售设置页面
	 */
	@ResponseBody
	@RequestMapping("saveActivityProductDeposit")
	public ResponseMsg saveActivityProductDeposit(HttpServletRequest request) {
		String id = request.getParameter("id");
		String activityId = request.getParameter("activityId");
		String productId = request.getParameter("productId");
		String deposit = request.getParameter("deposit");
		String deductAmount = request.getParameter("deductAmount");
		ActivityProductDeposit activityProductDeposit = new ActivityProductDeposit();
		if(!StringUtil.isEmpty(id)){
			activityProductDeposit = activityProductDepositMapper.selectByPrimaryKey(Integer.parseInt(id));
			activityProductDeposit.setUpdateBy(this.getSessionUserInfo(request).getId());
			activityProductDeposit.setUpdateDate(new Date());
		}else{
			activityProductDeposit.setDelFlag("0");
			activityProductDeposit.setCreateBy(this.getSessionUserInfo(request).getId());
			activityProductDeposit.setCreateDate(new Date());
			activityProductDeposit.setActivityId(Integer.parseInt(activityId));
			activityProductDeposit.setProductId(Integer.parseInt(productId));
		}
		activityProductDeposit.setDeposit(new BigDecimal(deposit));
		activityProductDeposit.setDeductAmount(new BigDecimal(deductAmount));
		if(activityProductDeposit.getId()!=null){
			activityProductDepositMapper.updateByPrimaryKeySelective(activityProductDeposit);
		}else{
			activityProductDepositMapper.insertSelective(activityProductDeposit);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,null);
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("exportExcel")
	public void exportExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String activityId = request.getParameter("activityId");
		List<ProductItemCustom> productItemCustoms = productItemService.getExportProductItemCustom(Integer.parseInt(activityId));
		List<Integer> propValIdList = new ArrayList<Integer>();
		for (ProductItemCustom productItemCustom : productItemCustoms) {
			String propValId = productItemCustom.getPropValId();
			if(!StringUtil.isEmpty(propValId)){
				String[] array = propValId.split(",");
				for(String idStr:array){
					if(!propValIdList.contains(Integer.parseInt(idStr))){
						propValIdList.add(Integer.parseInt(idStr));
					}
				}
			}
			
		}
		String test = "0";
		for (int i = 1;i<30000;i++){
			test = test+","+i;
		}
		System.out.println(test);
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("propValIdList", propValIdList);
		Map<Integer, String> totalMap = new HashMap<Integer, String>();
		if(propValIdList.size()>0){
			List<HashMap<String, Object>> list = productItemService.getPropDescList(paramMap);
			for(HashMap<String, Object> map:list){
				Integer id = (Integer) map.get("id");
				String propDesc = (String) map.get("prop_desc");
				totalMap.put(id,propDesc);
			}
		}
		//查询出mchtInfo
		MchtUser usr = this.getUserInfo();
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(usr.getMchtId());

		String [] titles=null;
		if (mchtInfo.getMchtType().equals("2")){
			titles = new String[]{ "SKUid", "库存数量", "新库存数量","商城价","新商城价","活动价","新活动价","商品名称","品牌","货号","规格","SKU商家编码","温馨提示"};
		}
		if (mchtInfo.getMchtType().equals("1")){
			titles = new String[]{ "SKUid", "库存数量", "新库存数量","商城价","新商城价","活动价","新活动价","结算价","新结算价","商品名称","品牌","货号","规格","SKU商家编码","温馨提示"};
		}

		ExcelBean excelBean = new ExcelBean("醒购批量改价库存SKU导出" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xls",
				"醒购批量改价库存SKU导出", titles);
		List<String[]> datas = new ArrayList<>();
		for (ProductItemCustom productItemCustom : productItemCustoms) {
			String[] array = productItemCustom.getPropValId().split(",");
			String propDesc = "";
			for(String idStr:array){
				String tmpStr = totalMap.get(Integer.parseInt(idStr));
				if(StringUtil.isEmpty(propDesc)){
					propDesc = tmpStr;
				}else{
					propDesc+=","+tmpStr;
				}
			}
			//数据
			String[] data=null;
			if (mchtInfo.getMchtType().equals("2")){
				data =new String[] {
						productItemCustom.getId().toString(),
						productItemCustom.getStock().toString(),
						"",
						productItemCustom.getMallPrice()==null?"":productItemCustom.getMallPrice().toString(),
						"",
						productItemCustom.getSalePrice()==null?"":productItemCustom.getSalePrice().toString(),
						"",
						productItemCustom.getProductName(),
						productItemCustom.getProductBrandName(),
						"`"+productItemCustom.getArtNo(),
						propDesc,
						"`"+productItemCustom.getSku(),
						"`只可修改【新库存数量、新商城价、新活动价】"
				};
			}
			if (mchtInfo.getMchtType().equals("1")){
				data =new String[] {
						productItemCustom.getId().toString(),
						productItemCustom.getStock().toString(),
						"",
						productItemCustom.getMallPrice()==null?"":productItemCustom.getMallPrice().toString(),
						"",
						productItemCustom.getSalePrice()==null?"":productItemCustom.getSalePrice().toString(),
						"",
						//结算价
						productItemCustom.getCostPrice()==null?"":productItemCustom.getCostPrice().toString(),
						"",
						productItemCustom.getProductName(),
						productItemCustom.getProductBrandName(),
						"`"+productItemCustom.getArtNo(),
						propDesc,
						"`"+productItemCustom.getSku(),
						"`只可修改【新库存数量、新商城价、新活动价、新结算价】"
				};
			}
			datas.add(data);
		}
		excelBean.setDataList(datas);
		ExcelUtils.export(excelBean,response);
	}
	
	/**
	 * 导入excel
	 * 
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping("importExcel")
	@ResponseBody
	public ResponseMsg importExcel(HttpServletRequest request) throws Exception{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    
        MultipartFile file = multipartRequest.getFile("file");
        if(file == null){
        	return new ResponseMsg(ResponseMsg.ERROR, "请先上传文件");
        }
        if(file.isEmpty()){
        	return new ResponseMsg(ResponseMsg.ERROR, "文件不存在！");
        }
        if(!file.getOriginalFilename().contains("醒购批量改价库存SKU导出")){
        	return new ResponseMsg(ResponseMsg.ERROR, "上传文件模板错误");
        }
        List<ArrayList<String>> dataList = ExcelUtils.read(file, file.getOriginalFilename(), 9);//7表示取excel表格的前三列
        if (dataList == null || dataList.size() < 1) {
        	return new ResponseMsg(ResponseMsg.ERROR, "导入文件格式只支持.xls或.xlsx",null);
		}
        if(dataList.size()>2001){
        	return new ResponseMsg(ResponseMsg.ERROR, "导入的数据不能超过2000条！",null);
        }
        String activityId = request.getParameter("activityId");
        List<ProductItemCustom> productItemCustoms = productItemService.getExportProductItemCustom(Integer.parseInt(activityId));
        List<Integer> idList = new ArrayList<Integer>();
        Integer productId=null;
        for (ProductItemCustom productItemCustom : productItemCustoms) {
        	idList.add(productItemCustom.getId());
        	//获取产品id
			 productId = productItemCustom.getProductId();
		}
		//查询svip折扣,如果为空,设置折扣为1.00
		Product product = productService.selectByPrimaryKey(productId);
		BigDecimal svipDiscount = product.getSvipDiscount();
		double svip=0.00;
		if (svipDiscount==null){
			svip=1.00;
		}else {
			 svip = svipDiscount.doubleValue();
		}
        //查询商家类型，根据商家类型进行筛选
		MchtUser usr = this.getUserInfo();
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(usr.getMchtId());
		String mchtType = mchtInfo.getMchtType();

		int errorCount = 0;
        int successCount = 0;
		for (int i = 1; i < dataList.size(); i++) {
    		try {
    			ProductItem pi = new ProductItem();
            	pi.setUpdateBy(this.getSessionUserInfo(request).getId());
            	pi.setUpdateDate(new Date());
                List<String> data = dataList.get(i);
                String id = data.get(0);
                String newStock = data.get(2);
                String newMallPrice = data.get(4);
                //新活动价
                String newSalePrice = data.get(6);
                //旧活动价
				BigDecimal oldSalePrice = new BigDecimal(data.get(5));

				//新结算价
				String newCostPrice =null;
				if (mchtType.equals("1")){
				 newCostPrice = data.get(8);
				}


				if(!idList.contains(Integer.parseInt(id))){
                	errorCount++;
                	continue;
                }
                pi.setId(Integer.parseInt(id));

                if(!StringUtil.isEmpty(newStock)){
                	pi.setStock(Integer.parseInt(newStock));
				}

                if(!StringUtil.isEmpty(newMallPrice) ){
                	pi.setMallPrice(new BigDecimal(newMallPrice));
				}
                boolean isSuccess = false;
                if (mchtType.equals("2")) {
					//如果新活动价不为空 将新活动价设置进去
					if (!StringUtil.isEmpty(newSalePrice)&& new BigDecimal(newSalePrice).compareTo(new BigDecimal(0)) == 1) {
						pi.setSalePrice(new BigDecimal(newSalePrice));
						isSuccess = true;
					}
					if (StringUtil.isEmpty(newSalePrice)){
						pi.setSalePrice(oldSalePrice);
						isSuccess = true;
					}

				}
					// 将excel中的结算价导入品牌特卖详情中的商品信息
					// 只有走到if里并且设值成功才能够进行更新,不然就错误条数+1
				if (mchtType.equals("1")) {

							if (!StringUtil.isEmpty(newSalePrice)) {
								pi.setSalePrice(new BigDecimal(newSalePrice));
							}
                            //两个为空的判断
							if (StringUtil.isEmpty(newCostPrice) && StringUtil.isEmpty(newSalePrice)) {
								if (new BigDecimal(data.get(7)).divide(oldSalePrice, 4, BigDecimal.ROUND_DOWN).compareTo(new BigDecimal(svip - 0.2)) == -1 ) {
									pi.setCostPrice(new BigDecimal(data.get(7)));
                                    isSuccess = true;
								}
							} else {
								//当新活动价==NULL，判断 新结算价 / 活动价 < SVIP折扣率 - 20%
								if (StringUtil.isEmpty(newSalePrice)) {
									if (new BigDecimal(newCostPrice).divide(oldSalePrice, 4, BigDecimal.ROUND_DOWN).compareTo(new BigDecimal(svip - 0.2)) == -1 && new BigDecimal(newCostPrice).compareTo(new BigDecimal(0))==1) {
										pi.setCostPrice(new BigDecimal(newCostPrice));
										isSuccess = true;
									}
								}
								//当新结算价==NULL，判断 结算价 / 新活动价 < SVIP折扣率 - 20%
								else if (StringUtil.isEmpty(newCostPrice)) {
									if (new BigDecimal(data.get(7)).divide(new BigDecimal(newSalePrice), 4, BigDecimal.ROUND_DOWN).compareTo(new BigDecimal(svip - 0.2)) == -1 && new BigDecimal(newSalePrice).compareTo(new BigDecimal(0))==1) {
										pi.setCostPrice(new BigDecimal(data.get(7)));
										isSuccess = true;
									}
								} else { //两个都不为空设置
									if (new BigDecimal(newSalePrice).compareTo(new BigDecimal(0))==1 && new BigDecimal(newCostPrice).compareTo(new BigDecimal(0))==1){
									if (new BigDecimal(newCostPrice).divide(new BigDecimal(newSalePrice), 4, BigDecimal.ROUND_DOWN).compareTo(new BigDecimal(svip - 0.2)) == -1) {
										pi.setCostPrice(new BigDecimal(newCostPrice));
										isSuccess = true;
										}
									}
								}
							}
						}


				if (isSuccess) {

					//旧的商品item
					ProductItem oldProductItem = productItemService.selectByPrimaryKey(pi.getId());

					ProductItem oldMinMallPriceItem = productItemService.getMinMallPriceItem(oldProductItem.getProductId());
					ProductItem oldMinSalePriceItem = productItemService.getMinSalePriceItem(oldProductItem.getProductId());

					int updateCount = productItemService.updateByPrimaryKeySelective(pi);
					if (updateCount > 0) {
						successCount++;

						//设置商城最低价
						productService.setProductMinPrice(oldMinSalePriceItem.getProductId(),pi.getUpdateBy());//设置商品最低价

						//如果商品最低价格有变，则插入一条价格变动日志
						//插入价格变动日志
						ProductItem minMallPriceItem = productItemService.getMinMallPriceItem(oldProductItem.getProductId());
						ProductItem minSalePriceItem = productItemService.getMinSalePriceItem(oldProductItem.getProductId());

						if(minMallPriceItem.getMallPrice().compareTo(oldMinMallPriceItem.getMallPrice())!=0||minSalePriceItem.getSalePrice().compareTo(oldMinSalePriceItem.getSalePrice())!=0){
							ProductPriceChangeLog productPriceChangeLog=new ProductPriceChangeLog();
							productPriceChangeLog.setDelFlag("0");
							productPriceChangeLog.setProductId(oldMinSalePriceItem.getProductId());
							productPriceChangeLog.setCreateDate(pi.getUpdateDate());
							productPriceChangeLog.setCreateBy(pi.getUpdateBy());
							productPriceChangeLog.setMinMallPrice(minMallPriceItem.getMallPrice());
							productPriceChangeLog.setMinMallPriceItemId(minMallPriceItem.getId());
							productPriceChangeLog.setMinSalePrice(minSalePriceItem.getSalePrice());
							productPriceChangeLog.setMinSalePriceItemId(minSalePriceItem.getId());
							productPriceChangeLogService.insertSelective(productPriceChangeLog);
						}

					} else {
						errorCount++;
					}
				}else {
					errorCount++;
				}
			} catch (Exception e) {
				e.printStackTrace();
				errorCount++;
				continue;
			}
        }
        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("successCount", successCount);
        returnData.put("errorCount", errorCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,returnData);
	}

}
