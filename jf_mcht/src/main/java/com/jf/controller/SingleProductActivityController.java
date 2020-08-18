package com.jf.controller;

import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.*;
import com.jf.entity.*;
import com.jf.service.*;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/singleProductActivity")
public class SingleProductActivityController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(SingleProductActivityController.class);

	@Resource
	private SingleProductActivityService singleProductActivityService;
	@Resource
	private ProductService productService;
	@Resource
	private ProductItemService productItemService;
	@Resource
	private ActivityProductService activityProductService;
	@Resource
	private MchtSingleActivityCnfMapper mchtSingleActivityCnfMapper;
	@Resource
	private SingleProductActivityCnfMapper singleProductActivityCnfMapper;
	@Resource
	private SingleProductActivityControlMapper singleProductActivityControlMapper;
	@Resource
	private MchtProductTypeService mchtProductTypeService;
	@Resource
	private ProductTypeService productTypeService;
	@Resource
	private ActivityRuleConfigurationMapper activityRuleConfigurationMapper;
	@Resource
	private ShopScoreService shopScoreService;
	@Resource
	private SeckillTimeMapper seckillTimeMapper;
	@Resource
	private CommentMapper commentMapper;
	
	private ArrayList<Integer> mchtIdList = new ArrayList<Integer>() {{
	    add(8);
	    add(13);
	    add(92);
	    add(97);
	    add(113);
	}};
	
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	/**
	 * 单品活动报名
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model, HttpServletRequest request) {
		List<String> auditStatusList = new ArrayList<String>();
		auditStatusList.add("0");
		auditStatusList.add("1");
		auditStatusList.add("3");
		Integer mchtId = this.getMchtInfo().getId();
		String showSingleActivity1 = this.showSingleActivity(mchtId, "1");
		model.addAttribute("showSingleActivity1", showSingleActivity1);
		MchtSingleActivityCnfExample e = new MchtSingleActivityCnfExample();
		MchtSingleActivityCnfExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		c.andActivityTypeEqualTo("1");
		List<MchtSingleActivityCnf> mchtSingleActivityCnfs = mchtSingleActivityCnfMapper.selectByExample(e);
		if(mchtSingleActivityCnfs!=null && mchtSingleActivityCnfs.size()>0){
			MchtSingleActivityCnf mchtSingleActivityCnf = mchtSingleActivityCnfs.get(0);
			model.addAttribute("newUserIsSpecial", mchtSingleActivityCnf.getIsSpecial());
		}	
		
		String showSingleActivity2 = this.showSingleActivity(mchtId, "2");
		model.addAttribute("showSingleActivity2", showSingleActivity2);
		MchtSingleActivityCnfExample e2 = new MchtSingleActivityCnfExample();
		MchtSingleActivityCnfExample.Criteria c2 = e2.createCriteria();
		c2.andDelFlagEqualTo("0");
		c2.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		c2.andActivityTypeEqualTo("2");
		List<MchtSingleActivityCnf> mchtSingleActivityCnfs2 = mchtSingleActivityCnfMapper.selectByExample(e2);
		if(mchtSingleActivityCnfs2!=null && mchtSingleActivityCnfs2.size()>0){
			MchtSingleActivityCnf mchtSingleActivityCnf2 = mchtSingleActivityCnfs2.get(0);
			model.addAttribute("hotIsSpecial", mchtSingleActivityCnf2.getIsSpecial());
		}	
		
		String showSingleActivity3 = this.showSingleActivity(mchtId, "3");
		model.addAttribute("showSingleActivity3", showSingleActivity3);
		MchtSingleActivityCnfExample me = new MchtSingleActivityCnfExample();
		MchtSingleActivityCnfExample.Criteria mc = me.createCriteria();
		mc.andDelFlagEqualTo("0");
		mc.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		mc.andActivityTypeEqualTo("3");
		List<MchtSingleActivityCnf> mchtSingleActivityCnfs3 = mchtSingleActivityCnfMapper.selectByExample(me);
		if(mchtSingleActivityCnfs3!=null && mchtSingleActivityCnfs3.size()>0){
			MchtSingleActivityCnf mchtSingleActivityCnf3 = mchtSingleActivityCnfs3.get(0);
			model.addAttribute("killIsSpecial", mchtSingleActivityCnf3.getIsSpecial());
		}	
		
		String showSingleActivity4 = this.showSingleActivity(mchtId, "4");
		model.addAttribute("showSingleActivity4", showSingleActivity4);
		MchtSingleActivityCnfExample me4 = new MchtSingleActivityCnfExample();
		MchtSingleActivityCnfExample.Criteria mc4 = me4.createCriteria();
		mc4.andDelFlagEqualTo("0");
		mc4.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		mc4.andActivityTypeEqualTo("4");
		List<MchtSingleActivityCnf> mchtSingleActivityCnfs4 = mchtSingleActivityCnfMapper.selectByExample(me4);
		if(mchtSingleActivityCnfs4!=null && mchtSingleActivityCnfs4.size()>0){
			MchtSingleActivityCnf mchtSingleActivityCnf4 = mchtSingleActivityCnfs4.get(0);
			model.addAttribute("newUserKillIsSpecial", mchtSingleActivityCnf4.getIsSpecial());
		}	
		
		String showSingleActivity5 = this.showSingleActivity(mchtId, "5");
		model.addAttribute("showSingleActivity5", showSingleActivity5);
		MchtSingleActivityCnfExample me5 = new MchtSingleActivityCnfExample();
		MchtSingleActivityCnfExample.Criteria mc5 = me5.createCriteria();
		mc5.andDelFlagEqualTo("0");
		mc5.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		mc5.andActivityTypeEqualTo("5");
		List<MchtSingleActivityCnf> mchtSingleActivityCnfs5 = mchtSingleActivityCnfMapper.selectByExample(me5);
		if(mchtSingleActivityCnfs5!=null && mchtSingleActivityCnfs5.size()>0){
			MchtSingleActivityCnf mchtSingleActivityCnf5 = mchtSingleActivityCnfs5.get(0);
			model.addAttribute("integralIsSpecial", mchtSingleActivityCnf5.getIsSpecial());
		}	
		
		String showSingleActivity6 = this.showSingleActivity(mchtId, "6");
		model.addAttribute("showSingleActivity6", showSingleActivity6);
		MchtSingleActivityCnfExample me6 = new MchtSingleActivityCnfExample();
		MchtSingleActivityCnfExample.Criteria mc6 = me6.createCriteria();
		mc6.andDelFlagEqualTo("0");
		mc6.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		mc6.andActivityTypeEqualTo("6");
		List<MchtSingleActivityCnf> mchtSingleActivityCnfs6 = mchtSingleActivityCnfMapper.selectByExample(me6);
		if(mchtSingleActivityCnfs6!=null && mchtSingleActivityCnfs6.size()>0){
			MchtSingleActivityCnf mchtSingleActivityCnf6 = mchtSingleActivityCnfs6.get(0);
			model.addAttribute("brokenCodeIsSpecial", mchtSingleActivityCnf6.getIsSpecial());
		}	
		
		String showSingleActivity7 = this.showSingleActivity(mchtId, "7");
		model.addAttribute("showSingleActivity7", showSingleActivity7);
		MchtSingleActivityCnfExample me7 = new MchtSingleActivityCnfExample();
		MchtSingleActivityCnfExample.Criteria mc7 = me7.createCriteria();
		mc7.andDelFlagEqualTo("0");
		mc7.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		mc7.andActivityTypeEqualTo("7");
		List<MchtSingleActivityCnf> mchtSingleActivityCnfs7 = mchtSingleActivityCnfMapper.selectByExample(me7);
		if(mchtSingleActivityCnfs7!=null && mchtSingleActivityCnfs7.size()>0){
			MchtSingleActivityCnf mchtSingleActivityCnf7 = mchtSingleActivityCnfs7.get(0);
			model.addAttribute("cutFreeIsSpecial", mchtSingleActivityCnf7.getIsSpecial());
		}	
		
		String showSingleActivity8 = this.showSingleActivity(mchtId, "8");
		model.addAttribute("showSingleActivity8", showSingleActivity8);
		MchtSingleActivityCnfExample me8 = new MchtSingleActivityCnfExample();
		MchtSingleActivityCnfExample.Criteria mc8 = me8.createCriteria();
		mc8.andDelFlagEqualTo("0");
		mc8.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		mc8.andActivityTypeEqualTo("8");
		List<MchtSingleActivityCnf> mchtSingleActivityCnfs8 = mchtSingleActivityCnfMapper.selectByExample(me8);
		if(mchtSingleActivityCnfs8!=null && mchtSingleActivityCnfs8.size()>0){
			MchtSingleActivityCnf mchtSingleActivityCnf8 = mchtSingleActivityCnfs8.get(0);
			model.addAttribute("invitationFreeIsSpecial", mchtSingleActivityCnf8.getIsSpecial());
		}	
		
		String showSingleActivity10 = this.showSingleActivity(mchtId, "10");
		model.addAttribute("showSingleActivity10", showSingleActivity10);
		MchtSingleActivityCnfExample me10 = new MchtSingleActivityCnfExample();
		MchtSingleActivityCnfExample.Criteria mc10 = me10.createCriteria();
		mc10.andDelFlagEqualTo("0");
		mc10.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		mc10.andActivityTypeEqualTo("10");
		List<MchtSingleActivityCnf> mchtSingleActivityCnfs10 = mchtSingleActivityCnfMapper.selectByExample(me10);
		if(mchtSingleActivityCnfs10!=null && mchtSingleActivityCnfs10.size()>0){
			MchtSingleActivityCnf mchtSingleActivityCnf10 = mchtSingleActivityCnfs10.get(0);
			model.addAttribute("assistanceIsSpecial", mchtSingleActivityCnf10.getIsSpecial());
		}	
		
		model.addAttribute("mchtInfoStatus", this.getSessionMchtInfo(request).getStatus());
		ActivityRuleConfigurationExample activityRuleConfigurationExample = new ActivityRuleConfigurationExample();
		activityRuleConfigurationExample.setOrderByClause("id asc");
		activityRuleConfigurationExample.createCriteria().andDelFlagEqualTo("0");
		List<ActivityRuleConfiguration> activityRuleConfigurationList = activityRuleConfigurationMapper.selectByExample(activityRuleConfigurationExample);
		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
		List<ProductType> productTypeList = productTypeService.selectByExample(pte);
		Map<Integer,String> map = new HashMap<Integer,String>();
		for(ProductType productType:productTypeList){
			map.put(productType.getId(), productType.getName());
		}
		MchtProductTypeExample mpte = new MchtProductTypeExample();
		mpte.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		List<MchtProductType> mchtProductTypes = mchtProductTypeService.selectByExample(mpte);
		List<Integer> productTypeIdList = new ArrayList<Integer>();
		if(mchtProductTypes!=null && mchtProductTypes.size()>0){
			for(MchtProductType mchtProductType:mchtProductTypes){
				if(!productTypeIdList.contains(mchtProductType.getProductTypeId())){
					productTypeIdList.add(mchtProductType.getProductTypeId());
				}
			}
		}
		for(ActivityRuleConfiguration activityRuleConfiguration:activityRuleConfigurationList){
			String priceRulesStr="";
			String salesRulesStr="";
			String stockRulesStr="";
			String favorableRateStr="";
			String salesCycleRulesStr="";
			String otherRequirementsStr="";
			int priceRuleIndex = 0;
			if(!StringUtil.isEmpty(activityRuleConfiguration.getPriceRules())){
				Map<String,Object> resultMap = createPriceRulesHtml(map, priceRulesStr,activityRuleConfiguration,productTypeIdList);
				priceRulesStr = resultMap.get("priceRulesStr").toString();
				priceRuleIndex = (Integer)resultMap.get("index");
			}
			int salesRuleIndex = priceRuleIndex;
			if(!StringUtil.isEmpty(activityRuleConfiguration.getSalesRules())){
				 Map<String,Object> resultMap = createSalesRulesHtml(map, salesRulesStr,activityRuleConfiguration,salesRuleIndex,productTypeIdList);
				 salesRulesStr = resultMap.get("salesRulesStr").toString();
				 salesRuleIndex = (Integer)resultMap.get("index");
			}
			int stockRuleIndex = salesRuleIndex;
			if(!StringUtil.isEmpty(activityRuleConfiguration.getStockRules())){
				Map<String,Object> resultMap = createStockRulesHtml(map, stockRulesStr,activityRuleConfiguration,stockRuleIndex,productTypeIdList);
				stockRulesStr = resultMap.get("stockRulesStr").toString();
				stockRuleIndex = (Integer)resultMap.get("index");
			}
			if(activityRuleConfiguration.getFavorableRate()!=null){
				stockRuleIndex++;
				favorableRateStr = stockRuleIndex+"、所报商品的好评率不得低于"+activityRuleConfiguration.getFavorableRate().multiply(new BigDecimal(100))+"%";
				if(activityRuleConfiguration.getShopComment()!=null){
					favorableRateStr+="；店铺评价不低于"+activityRuleConfiguration.getShopComment()+"分<br>";
				}
			}else{
				if(activityRuleConfiguration.getShopComment()!=null){
					stockRuleIndex++;
					favorableRateStr+=stockRuleIndex+"、店铺评价不低于"+activityRuleConfiguration.getShopComment()+"分<br>";
				}
			}
			int salesCycleRuleIndex = stockRuleIndex;
			if(!StringUtil.isEmpty(activityRuleConfiguration.getSalesCycleRules())){
				Map<String,Object> resultMap = createSalesCycleRulesHtml(map, salesCycleRulesStr,activityRuleConfiguration,salesCycleRuleIndex,productTypeIdList);
				salesCycleRulesStr = resultMap.get("salesCycleRulesStr").toString();
				salesCycleRuleIndex = (Integer)resultMap.get("index");
			}
			if(!StringUtil.isEmpty(activityRuleConfiguration.getOtherRequirements())){
				salesCycleRuleIndex++;
				otherRequirementsStr+=salesCycleRuleIndex+"、"+activityRuleConfiguration.getOtherRequirements();
			}
			model.addAttribute("html"+activityRuleConfiguration.getType(), priceRulesStr+salesRulesStr+stockRulesStr+favorableRateStr+salesCycleRulesStr+otherRequirementsStr);
			model.addAttribute("shopComment"+activityRuleConfiguration.getType(), activityRuleConfiguration.getShopComment());
		}
		List<HashMap<String,Object>> list = shopScoreService.countShopScoreByMhctId(this.getSessionMchtInfo(request).getId());
		String mchtScore = list.get(0).get("mchtScore").toString();
		if(new BigDecimal(0).compareTo(new BigDecimal(mchtScore)) == 0){
			model.addAttribute("mchtScore", "5");
		}else{
			String wuliuScore = list.get(0).get("wuliuScore").toString();
			String productScore = list.get(0).get("productScore").toString();
			BigDecimal add = new BigDecimal(mchtScore).add(new BigDecimal(wuliuScore)).add(new BigDecimal(productScore));
			BigDecimal divide = add.divide(new BigDecimal("3"), 2, BigDecimal.ROUND_HALF_UP);
			model.addAttribute("mchtScore", divide);
		}
		return "singleProductActivity/index";
	}

	private Map<String,Object> createPriceRulesHtml(Map<Integer, String> map, String priceRulesStr,ActivityRuleConfiguration activityRuleConfiguration,List<Integer> productTypeIdList) {
		String[] priceRulesArray = activityRuleConfiguration.getPriceRules().split(";");
		int index = 0;
		for(int i=1;i<=priceRulesArray.length;i++){
			String[] priceRuleArray = priceRulesArray[i-1].split("_");
			if(!productTypeIdList.contains(Integer.parseInt(priceRuleArray[0]))){
				continue;
			}else{
				String tmpStr = "";
				String productTypeName = map.get(Integer.parseInt(priceRuleArray[0]));
				if(StringUtil.isEmpty(productTypeName)){
					continue;
				}
				index++;
				tmpStr+=index+"、"+productTypeName+"商品报名价格";
				if(priceRuleArray[1].equals("0")){
					tmpStr+="低于吊牌价"+priceRuleArray[2]+"折";
				}else{
					tmpStr+="不高于"+priceRuleArray[2]+"元";
				}
				priceRulesStr+=tmpStr+"<br>";
			}
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("priceRulesStr", priceRulesStr);
		resultMap.put("index", index);
		return resultMap;
	}
	
	private Map<String,Object> createSalesRulesHtml(Map<Integer, String> map, String salesRulesStr,ActivityRuleConfiguration activityRuleConfiguration,int priceRuleIndex,List<Integer> productTypeIdList) {
		String[] salesRulesArray = activityRuleConfiguration.getSalesRules().split(";");
		for(int i=1;i<=salesRulesArray.length;i++){
			String[] salesRuleArray = salesRulesArray[i-1].split("_");
			if(!productTypeIdList.contains(Integer.parseInt(salesRuleArray[0]))){
				continue;
			}else{
				String tmpStr = "";
				String productTypeName = map.get(Integer.parseInt(salesRuleArray[0]));
				if(StringUtil.isEmpty(productTypeName)){
					continue;
				}
				priceRuleIndex++;
				tmpStr+=priceRuleIndex+"、"+productTypeName+"商品的总销量不小于"+salesRuleArray[1];
				salesRulesStr+=tmpStr+"<br>";
			}
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("salesRulesStr", salesRulesStr);
		resultMap.put("index", priceRuleIndex);
		return resultMap;
	}
	
	private Map<String,Object> createStockRulesHtml(Map<Integer, String> map, String stockRulesStr,ActivityRuleConfiguration activityRuleConfiguration,int salesRuleIndex,List<Integer> productTypeIdList) {
		String[] stockRulesArray = activityRuleConfiguration.getStockRules().split(";");
		for(int i=1;i<=stockRulesArray.length;i++){
			String[] stockRuleArray = stockRulesArray[i-1].split("_");
			if(!productTypeIdList.contains(Integer.parseInt(stockRuleArray[0]))){
				continue;
			}else{
				String tmpStr = "";
				String productTypeName = map.get(Integer.parseInt(stockRuleArray[0]));
				if(StringUtil.isEmpty(productTypeName)){
					continue;
				}
				salesRuleIndex++;
				tmpStr+=salesRuleIndex+"、"+productTypeName+"商品的总库存不小于"+stockRuleArray[1];
				stockRulesStr+=tmpStr+"<br>";
			}
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("stockRulesStr", stockRulesStr);
		resultMap.put("index", salesRuleIndex);
		return resultMap;
	}
	
	private Map<String,Object> createSalesCycleRulesHtml(Map<Integer, String> map, String salesCycleRulesStr,ActivityRuleConfiguration activityRuleConfiguration,int stockRuleIndex,List<Integer> productTypeIdList) {
		String[] salesCycleRulesArray = activityRuleConfiguration.getSalesCycleRules().split(";");
		for(int i=1;i<=salesCycleRulesArray.length;i++){
			String[] salesCycleRuleArray = salesCycleRulesArray[i-1].split("_");
			if(!productTypeIdList.contains(Integer.parseInt(salesCycleRuleArray[0]))){
				continue;
			}else{
				String tmpStr = "";
				String productTypeName = map.get(Integer.parseInt(salesCycleRuleArray[0]));
				if(StringUtil.isEmpty(productTypeName)){
					continue;
				}
				stockRuleIndex++;
				tmpStr+=stockRuleIndex+"、"+productTypeName+"商品销售周期为"+salesCycleRuleArray[1]+"天;在同一个活动销售期内，同一个商家同品牌只能提报"+salesCycleRuleArray[2]+"个商品";
				salesCycleRulesStr+=tmpStr+"<br>";
			}
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("salesCycleRulesStr", salesCycleRulesStr);
		resultMap.put("index", stockRuleIndex);
		return resultMap;
	}
	
	/**
	 * 单品活动管理
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest request) {
		List<SysStatus> types = DataDicUtil.getStatusList("BU_SINGLE_PRODUCT_ACTIVITY", "TYPE");
		List<SysStatus> auditStatusList = DataDicUtil.getStatusList("BU_SINGLE_PRODUCT_ACTIVITY", "AUDIT_STATUS");
		model.addAttribute("types", types);
		model.addAttribute("auditStatusList", auditStatusList);
		return "singleProductActivity/list";
	}
	
	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/data")
	@ResponseBody
	public ResponseMsg data(HttpServletRequest request, Page page) {
		String code = request.getParameter("search_code");
		String type = request.getParameter("search_type");
		String auditStatus = request.getParameter("search_auditStatus");
		Map<String, Object> returnData = new HashMap<String, Object>();
		SingleProductActivityCustomExample example = new SingleProductActivityCustomExample();
		example.setOrderByClause("t.id desc");
		SingleProductActivityCustomExample.SingleProductActivityCustomCriteria criteria = example.createCriteria();
		criteria.andMchtIdEqualTo(this.getMchtInfo().getId());
		criteria.andDelFlagEqualTo("0");
		criteria.andSaleTypeEqualTo("2");//2.单品款
		if(!StringUtil.isEmpty(code)){
			criteria.andProductCodeEqualTo(code.trim());
		}
		if(!StringUtil.isEmpty(type)){
			criteria.andTypeEqualTo(type);
		}
		if(!StringUtil.isEmpty(auditStatus)){
			criteria.andAuditStatusEqualTo(auditStatus);
		}
		int totalCount = singleProductActivityService.countSingleProductActivityCustomByExample(example);
		example.setLimitStart(page.getLimitStart());
		example.setLimitSize(page.getLimitSize());
		List<SingleProductActivityCustom> singleProductActivityCustoms = singleProductActivityService.selectSingleProductActivityCustomByExample(example);
		for(SingleProductActivityCustom singleProductActivityCustom:singleProductActivityCustoms){
			singleProductActivityCustom.setPic(FileUtil.getMiddleImageName(singleProductActivityCustom.getPic()));
		}
		returnData.put("Rows", singleProductActivityCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);

	}
	
	/**
	 * 单品报名页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/toAdd")
	public String toAdd(Model model,HttpServletRequest request) {
		String type=request.getParameter("type");
		model.addAttribute("type", type);
		model.addAttribute("isSpecial", request.getParameter("isSpecial"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("nowDate", sdf.format(new Date()));
		model.addAttribute("limitDate", sdf.format(DateUtil.getDateAfterAndBeginTime(new Date(), 30)));
		if(type.equals("3")){
			SeckillTimeExample e = new SeckillTimeExample();
			e.setOrderByClause("start_hours asc,continue_hours asc");
			SeckillTimeExample.Criteria c = e.createCriteria();	
			c.andDelFlagEqualTo("0");
			c.andStatusEqualTo("1");//1.启用
			c.andSeckillTypeEqualTo("1");//1.限时秒杀
			List<SeckillTime> seckillTimeList = seckillTimeMapper.selectByExample(e);
			model.addAttribute("seckillTimeList", seckillTimeList);
		}
		return "singleProductActivity/toAdd";
	}
	
	/**
	 * 保存
	 * 
	 * @param request
	 * @param page
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/save")
	@ResponseBody
	public ResponseMsg save(HttpServletRequest request) throws ParseException {
		Integer mchtId = this.getMchtInfo().getId();
		Integer productId = Integer.parseInt(request.getParameter("productId"));
		String type = request.getParameter("type");
		String originalprice = request.getParameter("originalPrice");
		String activityPrice = request.getParameter("activityPrice");
		String comparePrice = request.getParameter("comparePrice");
		String expectedDate = request.getParameter("expectedDate");
		String remarks = request.getParameter("remarks");
		String svipDiscount = request.getParameter("svipDiscount");
		String isSpecial = request.getParameter("isSpecial");
		String seckillTimeId = request.getParameter("seckillTimeId");
		ProductCustom productCustom = productService.selectProductCustomByPrimaryKey(productId);
		if(!productCustom.getSaleType().equals("2")){
			return new ResponseMsg(ResponseMsg.ERROR, "单品活动的商品必须是单品款才能报名");
		}
		SingleProductActivity singleProductActivity = new SingleProductActivity();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(!StringUtil.isEmpty(isSpecial) && isSpecial.equals("0")){
			singleProductActivity.setAuditStatus("1");//1.初审通过
			singleProductActivity.setBeginTime(sdf.parse(expectedDate));
			if(type.equals("3")){//3.限时抢购
				singleProductActivity.setSeckillType("1");
				SeckillTime seckillTime = seckillTimeMapper.selectByPrimaryKey(Integer.parseInt(seckillTimeId));
				Date endTime = DateUtils.addMinutes(singleProductActivity.getBeginTime(), Integer.parseInt(seckillTime.getContinueHours())*60+Integer.parseInt(seckillTime.getContinueMin()));
				singleProductActivity.setEndTime(endTime);
			}
			ActivityRuleConfigurationExample e = new ActivityRuleConfigurationExample();
			e.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo(type);
			List<ActivityRuleConfiguration> activityRuleConfigurations = activityRuleConfigurationMapper.selectByExample(e);
			if(activityRuleConfigurations!=null && activityRuleConfigurations.size()>0){
				ActivityRuleConfiguration activityRuleConfiguration = activityRuleConfigurations.get(0);
				if(!StringUtil.isEmpty(activityRuleConfiguration.getPriceRules())){
					String[] priceRulesArray = activityRuleConfiguration.getPriceRules().split(";");
					for(String priceRuleStr:priceRulesArray){
						String[] priceRuleArray = priceRuleStr.split("_");
						if(priceRuleArray[0].equals(productCustom.getProductType1Id().toString())){
							if(priceRuleArray[1].equals("0")){
								if(productCustom.getTagPriceMin()!=null){
									BigDecimal discountPrice = productCustom.getTagPriceMin().multiply(new BigDecimal(priceRuleArray[2])).multiply(new BigDecimal(0.1));
									if(new BigDecimal(activityPrice).compareTo(discountPrice)>0){
										DecimalFormat decimalFormat =new DecimalFormat("#.00");  
										return new ResponseMsg(ResponseMsg.ERROR, "当前选择的商品的活动价过高,大于"+decimalFormat.format(discountPrice)+"元");
									}
								}
							}else{
								if(new BigDecimal(activityPrice).compareTo(new BigDecimal(priceRuleArray[2]))>0){
									return new ResponseMsg(ResponseMsg.ERROR, "当前选择的商品的活动价过高,大于"+priceRuleArray[2]+"元");
								}
							}
							break;
						}else{
							continue;
						}
					}
				}
				if(!StringUtil.isEmpty(activityRuleConfiguration.getSalesRules())){
					String[] salesRulesArray = activityRuleConfiguration.getSalesRules().split(";");
					for(String salesRuleStr:salesRulesArray){
						String[] salesRuleArray = salesRuleStr.split("_");
						if(salesRuleArray[0].equals(productCustom.getProductType1Id().toString())){
							Integer limitSalesVolume = Integer.parseInt(salesRuleArray[1]);
							if(productCustom.getTotalSalesVolume()==null || productCustom.getTotalSalesVolume()<limitSalesVolume){
								return new ResponseMsg(ResponseMsg.ERROR, "当前选择商品的销量不符合要求,销量小于"+limitSalesVolume);
							}
							break;
						}else{
							continue;
						}
					}
				}
				if(!StringUtil.isEmpty(activityRuleConfiguration.getStockRules())){
					String[] stockRulesArray = activityRuleConfiguration.getStockRules().split(";");
					for(String stockRuleStr:stockRulesArray){
						String[] stockRuleArray = stockRuleStr.split("_");
						if(stockRuleArray[0].equals(productCustom.getProductType1Id().toString())){
							Integer limitStock = Integer.parseInt(stockRuleArray[1]);
							if(productCustom.getStock()<limitStock){
								return new ResponseMsg(ResponseMsg.ERROR, "当前选择商品的库存不符合报名要求,库存小于"+limitStock);
							}
							break;
						}else{
							continue;
						}
					}
				}
				if(activityRuleConfiguration.getFavorableRate()!=null){
					if(productCustom.getGoodCommentRate()!=null && new BigDecimal(productCustom.getGoodCommentRate()).compareTo(activityRuleConfiguration.getFavorableRate())<0){
						return new ResponseMsg(ResponseMsg.ERROR, "当前选择商品的好评率不符合报名要求,好评率小于"+activityRuleConfiguration.getFavorableRate().multiply(new BigDecimal(100))+"%");
					}
				}
				if(!StringUtil.isEmpty(activityRuleConfiguration.getSalesCycleRules())){
					String[] salesCycleRulesArray = activityRuleConfiguration.getSalesCycleRules().split(";");
					for(String salesCycleRuleStr:salesCycleRulesArray){
						String[] salesCycleRuleArray = salesCycleRuleStr.split("_");
						if(salesCycleRuleArray[0].equals(productCustom.getProductType1Id().toString())){
							if(!type.equals("3")){
								Date beginDate = DateUtils.addDays(sdf.parse(expectedDate), -Integer.parseInt(salesCycleRuleArray[1]));
								Date endDate = DateUtils.addDays(sdf.parse(expectedDate), Integer.parseInt(salesCycleRuleArray[1]));
								Map<String,Object> paramMap = new HashMap<String,Object>();
								paramMap.put("mchtId", this.getSessionMchtInfo(request).getId());
								paramMap.put("productBrandId", productCustom.getBrandId());
								paramMap.put("expectedDate", expectedDate);
								paramMap.put("beginDate", sdf.format(beginDate));
								paramMap.put("endDate", sdf.format(endDate));
								int count1 = singleProductActivityService.getEnrollCount1(paramMap);
								int count2 = singleProductActivityService.getEnrollCount2(paramMap);
								Integer limitBrandCount = Integer.parseInt(salesCycleRuleArray[2]);
								if(count1+count2>=limitBrandCount){
									return new ResponseMsg(ResponseMsg.ERROR, "已超过提报的数量");
								}
								Date endTime = DateUtils.addDays(singleProductActivity.getBeginTime(), Integer.parseInt(salesCycleRuleArray[1]));
								singleProductActivity.setEndTime(endTime);
							}else{
								SeckillTime seckillTime = seckillTimeMapper.selectByPrimaryKey(Integer.parseInt(seckillTimeId));
								int diffMinutes = Integer.parseInt(seckillTime.getContinueHours())*60+Integer.parseInt(seckillTime.getContinueMin());
								Date beginDate = DateUtils.addMinutes(sdf.parse(expectedDate), -diffMinutes);
								Date endDate = DateUtils.addMinutes(sdf.parse(expectedDate),diffMinutes);
								Map<String,Object> paramMap = new HashMap<String,Object>();
								paramMap.put("mchtId", this.getSessionMchtInfo(request).getId());
								paramMap.put("productBrandId", productCustom.getBrandId());
								paramMap.put("expectedDate", expectedDate);
								paramMap.put("beginDate", sdf.format(beginDate));
								paramMap.put("endDate", sdf.format(endDate));
								int count1 = singleProductActivityService.getEnrollCount1(paramMap);
								int count2 = singleProductActivityService.getEnrollCount2(paramMap);
								Integer limitBrandCount = Integer.parseInt(salesCycleRuleArray[2]);
								if(count1+count2>=limitBrandCount){
									return new ResponseMsg(ResponseMsg.ERROR, "已超过提报的数量");
								}
							}
							break;
						}else{
							continue;
						}
					}
				}
			}
		}else{
			singleProductActivity.setAuditStatus("0");//待审
		}
		singleProductActivity.setType(type);
		singleProductActivity.setMchtId(mchtId);
		singleProductActivity.setProductId(productId);
		singleProductActivity.setOriginalPrice(originalprice);
		singleProductActivity.setActivityPrice(new BigDecimal(activityPrice));
		if(!StringUtil.isEmpty(comparePrice) ) {
			singleProductActivity.setComparePrice(new BigDecimal(comparePrice));
		}
		if(!StringUtil.isEmpty(seckillTimeId)){
			singleProductActivity.setSeckillTimeId(Integer.parseInt(seckillTimeId));
		}
		singleProductActivity.setUpdateDate(new Date());
		singleProductActivity.setExpectedDate(sdf.parse(expectedDate));
		if(!StringUtil.isEmpty(remarks) ) {
			singleProductActivity.setRemarks(remarks);
		}
		Product product = productService.selectByPrimaryKey(productId);
		String activitying = productService.getProductActivityStatus(productId);
		if(!activitying.equals("0")){
			return new ResponseMsg(ResponseMsg.ERROR, "当前商品无法报名，请重新选择商品");
		}
		product.setUpdateBy(this.getSessionMchtInfo(request).getId());
		product.setUpdateDate(new Date());
		if(!StringUtil.isEmpty(svipDiscount)){
			product.setSvipDiscount(new BigDecimal(svipDiscount));
		}
		if(singleProductActivity.getActivityPrice().compareTo(productCustom.getSalePriceMin()) != 0){
			return new ResponseMsg(ResponseMsg.ERROR, "活动价格有变更，当前商品无法报名，请重新选择商品报名");
		}
		if(!type.equals("10")){
			singleProductActivityService.update(singleProductActivity, product);
		}else{
			String eachMoney = request.getParameter("eachMoney");
			String maxCount = request.getParameter("maxCount");
			CutPriceCnf cpc = new CutPriceCnf();
			cpc.setDelFlag("0");
			cpc.setCreateBy(this.getSessionUserInfo(request).getId());
			cpc.setCreateDate(new Date());
			cpc.setMaxInviteTimes(Integer.parseInt(maxCount));
			
			CutPriceCnfDtl cpcd = new CutPriceCnfDtl();
			cpcd.setDelFlag("0");
			cpcd.setCreateBy(this.getSessionUserInfo(request).getId());
			cpcd.setCreateDate(new Date());
			cpcd.setRateType("4");//4.固定金额
			cpcd.setFixedAmount(new BigDecimal(eachMoney));
			
			singleProductActivityService.update(singleProductActivity, product,cpc,cpcd);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 获取今天的报名款数
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getCount")
	@ResponseBody
	public ResponseMsg getCount(HttpServletRequest request) {
		String type = request.getParameter("type");
		Integer mchtId = this.getMchtInfo().getId();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = df.format(new Date());
		String begin = today+" 00:00:00";
		String end = today+" 23:59:59";
		SingleProductActivityExample example = new SingleProductActivityExample();
		SingleProductActivityExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andIsCloseEqualTo("0");
		criteria.andMchtIdEqualTo(mchtId);
		criteria.andTypeEqualTo(type);
		try {
			criteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(begin));
			criteria.andCreateDateLessThanOrEqualTo(sdf.parse(end));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<String> auditStatusList = new ArrayList<String>();
		auditStatusList.add("0");
		auditStatusList.add("1");
		auditStatusList.add("3");
		criteria.andAuditStatusIn(auditStatusList);
		int count = singleProductActivityService.countByExample(example);
		Map<String, Object> returnData = new HashMap<String, Object>();
		if(mchtIdList.contains(mchtId)){
			returnData.put("count", 0);
		}else{
			returnData.put("count", count);
		}
		
		MchtSingleActivityCnfExample e = new MchtSingleActivityCnfExample();
		MchtSingleActivityCnfExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtIdEqualTo(mchtId);
		c.andActivityTypeEqualTo(type);
		List<MchtSingleActivityCnf> mchtSingleActivityCnfs = mchtSingleActivityCnfMapper.selectByExample(e);
		if(mchtSingleActivityCnfs!=null && mchtSingleActivityCnfs.size()>0){
			MchtSingleActivityCnf mchtSingleActivityCnf = mchtSingleActivityCnfs.get(0);
			returnData.put("defaultCount", mchtSingleActivityCnf.getLimitQuality());
		}else{
			SingleProductActivityCnfExample space = new SingleProductActivityCnfExample();
			SingleProductActivityCnfExample.Criteria spaceC = space.createCriteria();
			spaceC.andDelFlagEqualTo("0");
			spaceC.andActivityTypeEqualTo(type);
			List<SingleProductActivityCnf> singleProductActivityCnfs = singleProductActivityCnfMapper.selectByExample(space);
			if(singleProductActivityCnfs!=null && singleProductActivityCnfs.size()>0){
				returnData.put("defaultCount", singleProductActivityCnfs.get(0).getLimitMchtQuality());
			}
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 商品列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/getProductList")
	@ResponseBody
	public ResponseMsg getProductList(HttpServletRequest request, Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String searchType = request.getParameter("searchType");
		String searchKeywrod = request.getParameter("searchKeywrod");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("mchtId", this.getMchtInfo().getId());
		paramMap.put("searchType", searchType);
		paramMap.put("code", searchKeywrod.trim());
		paramMap.put("artNo", searchKeywrod.trim());
		paramMap.put("productName", searchKeywrod.trim());
//		paramMap.put("isActivity", 0);2018年7月10日早开会决定：报名时不再设置在活动中。该字段将会弃用。
		paramMap.put("saleType", "2");
		paramMap.put("auditStatusNotEqual", "3");//商品法务审核状态不等于驳回
		paramMap.put("limitStart", (page.getPage()-1)*page.getPagesize());
		paramMap.put("limitSize", page.getPagesize());
		int totalCount = productService.countProductsByParamMap(paramMap);
		List<ProductCustom> productCustoms = productService.getProductsByParamMap(paramMap);
		returnData.put("Rows", productCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 检验商品是否参加过其他类型活动
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/hasTakePartIn")
	@ResponseBody
	public ResponseMsg hasTakePartIn(HttpServletRequest request, Page page) {
		Integer productId = Integer.parseInt(request.getParameter("productId"));
		Map<String, Object> returnData = new HashMap<String, Object>();
		SingleProductActivityExample example = new SingleProductActivityExample();
		SingleProductActivityExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andIsCloseEqualTo("0");
		criteria.andMchtIdEqualTo(this.getMchtInfo().getId());
		criteria.andProductIdEqualTo(productId);
		List<String> auditStatusList = new ArrayList<String>();
		auditStatusList.add("0");
		auditStatusList.add("1");
		auditStatusList.add("3");
		criteria.andAuditStatusIn(auditStatusList);
		List<SingleProductActivity> singleProductActivitys = singleProductActivityService.selectByExample(example);
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("productId", productId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		paramMap.put("date", sdf.format(new Date()));
		int count = activityProductService.getEffectiveProductCount(paramMap);
		if(count>0 || singleProductActivitys.size()>0){
			returnData.put("hasTakePartIn", true);
		}else{
			returnData.put("hasTakePartIn", false);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 商品销售周期内，当前商家，同品牌的商品已报名数
	 * 
	 * @param request
	 * @param page
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/getEnrollCount")
	@ResponseBody
	public ResponseMsg getEnrollCount(HttpServletRequest request) throws ParseException {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String type = request.getParameter("type");
		Integer productId = Integer.parseInt(request.getParameter("productId"));
		String seckillTimeId = request.getParameter("seckillTimeId");
		String expectedDateStr = request.getParameter("expectedDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Product product = productService.selectByPrimaryKey(productId);
		ActivityRuleConfigurationExample e = new ActivityRuleConfigurationExample();
		e.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo(type);
		List<ActivityRuleConfiguration> activityRuleConfigurations = activityRuleConfigurationMapper.selectByExample(e);
		if(activityRuleConfigurations!=null && activityRuleConfigurations.size()>0){
			ActivityRuleConfiguration activityRuleConfiguration = activityRuleConfigurations.get(0);
			if(!StringUtil.isEmpty(activityRuleConfiguration.getSalesCycleRules())){
				String[] salesCycleRulesArray = activityRuleConfiguration.getSalesCycleRules().split(";");
				for(String salesCycleRuleStr:salesCycleRulesArray){
					String[] salesCycleRuleArray = salesCycleRuleStr.split("_");
					if(salesCycleRuleArray[0].equals(product.getProductType1Id().toString())){
						if(!type.equals("3")){
							Date beginDate = DateUtils.addDays(sdf.parse(expectedDateStr), -Integer.parseInt(salesCycleRuleArray[1]));
							Date endDate = DateUtils.addDays(sdf.parse(expectedDateStr), Integer.parseInt(salesCycleRuleArray[1]));
							Map<String,Object> paramMap = new HashMap<String,Object>();
							paramMap.put("mchtId", this.getSessionMchtInfo(request).getId());
							paramMap.put("productBrandId", product.getBrandId());
							paramMap.put("expectedDate", expectedDateStr);
							paramMap.put("beginDate", sdf.format(beginDate));
							paramMap.put("endDate", sdf.format(endDate));
							int count1 = singleProductActivityService.getEnrollCount1(paramMap);
							int count2 = singleProductActivityService.getEnrollCount2(paramMap);
							Integer limitBrandCount = Integer.parseInt(salesCycleRuleArray[2]);
							returnData.put("count", count1+count2);
							returnData.put("limitBrandCount", limitBrandCount);
						}else{
							SeckillTime seckillTime = seckillTimeMapper.selectByPrimaryKey(Integer.parseInt(seckillTimeId));
							int diffMinutes = Integer.parseInt(seckillTime.getContinueHours())*60+Integer.parseInt(seckillTime.getContinueMin());
							Date beginDate = DateUtils.addMinutes(sdf.parse(expectedDateStr), -diffMinutes);
							Date endDate = DateUtils.addMinutes(sdf.parse(expectedDateStr),diffMinutes);
							Map<String,Object> paramMap = new HashMap<String,Object>();
							paramMap.put("mchtId", this.getSessionMchtInfo(request).getId());
							paramMap.put("productBrandId", product.getBrandId());
							paramMap.put("expectedDate", expectedDateStr);
							paramMap.put("beginDate", sdf.format(beginDate));
							paramMap.put("endDate", sdf.format(endDate));
							int count1 = singleProductActivityService.getEnrollCount1(paramMap);
							int count2 = singleProductActivityService.getEnrollCount2(paramMap);
							Integer limitBrandCount = Integer.parseInt(salesCycleRuleArray[2]);
							returnData.put("count", count1+count2);
							returnData.put("limitBrandCount", limitBrandCount);
						}
						break;
					}else{
						continue;
					}
				}
			}
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	public String showSingleActivity(Integer mchtId,String type){
		SingleProductActivityControlExample space = new SingleProductActivityControlExample();
		space.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo(type);
		List<SingleProductActivityControl> singleProductActivityControlList = singleProductActivityControlMapper.selectByExample(space);
		String typeShow = "1";
		if(singleProductActivityControlList!=null && singleProductActivityControlList.size()>0){
			SingleProductActivityControl singleProductActivityControl = singleProductActivityControlList.get(0);
			String hideToMchtIds = singleProductActivityControl.getHideToMchtIds();
			String showToMchtIds = singleProductActivityControl.getShowToMchtIds();
			String productTypeIds = singleProductActivityControl.getProductTypeIds();
			if(!StringUtil.isEmpty(hideToMchtIds)){//判断特定不可见
				String[] hideToMchtIdArray = hideToMchtIds.split(",");
				boolean flag = Arrays.asList(hideToMchtIdArray).contains(mchtId.toString());
				if(flag){
					typeShow = "0";
				}else{
					//判断特定可见
					if(!StringUtil.isEmpty(showToMchtIds)){
						String[] showToMchtIdArray = showToMchtIds.split(",");
						flag = Arrays.asList(showToMchtIdArray).contains(mchtId.toString());
						if(!flag){
							typeShow = "0";
						}
					}else{
						//判断不可见类目
						if(!StringUtil.isEmpty(productTypeIds)){
							MchtProductTypeExample mpte = new MchtProductTypeExample();
							mpte.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andIsMainEqualTo("1").andStatusEqualTo("1");
							List<MchtProductType> mchtProductTypeList = mchtProductTypeService.selectByExample(mpte);
							String productTypeIdStr = "";
							if(mchtProductTypeList!=null && mchtProductTypeList.size()>0){
								MchtProductType mchtProductType = mchtProductTypeList.get(0);
								productTypeIdStr = mchtProductType.getProductTypeId().toString();
							}
							String[] productTypeIdArray = productTypeIds.split(",");
							flag = Arrays.asList(productTypeIdArray).contains(productTypeIdStr);
							if(flag){
								typeShow = "0";
							}
						}
					}
				}
			}else{
				//判断特定可见
				if(!StringUtil.isEmpty(showToMchtIds)){
					String[] showToMchtIdArray = showToMchtIds.split(",");
					boolean flag = Arrays.asList(showToMchtIdArray).contains(mchtId.toString());
					if(!flag){
						typeShow = "0";
					}
				}else{
					//判断不可见类目
					if(!StringUtil.isEmpty(productTypeIds)){
						MchtProductTypeExample mpte = new MchtProductTypeExample();
						mpte.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andIsMainEqualTo("1").andStatusEqualTo("1");
						List<MchtProductType> mchtProductTypeList = mchtProductTypeService.selectByExample(mpte);
						String productTypeIdStr = "";
						if(mchtProductTypeList!=null && mchtProductTypeList.size()>0){
							MchtProductType mchtProductType = mchtProductTypeList.get(0);
							productTypeIdStr = mchtProductType.getProductTypeId().toString();
						}
						String[] productTypeIdArray = productTypeIds.split(",");
						boolean flag = Arrays.asList(productTypeIdArray).contains(productTypeIdStr);
						if(flag){
							typeShow = "0";
						}
					}
				}
			}
		}
		return typeShow;
	}
}
