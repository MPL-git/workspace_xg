package com.jf.controller;

import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.StringUtil;
import com.jf.entity.*;
import com.jf.entity.CouponExample.Criteria;
import com.jf.service.*;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/shopPreferentialInfo")
public class ShopPreferentialInfoController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(ShopPreferentialInfoController.class);

	@Resource
	private ShopPreferentialInfoService shopPreferentialInfoService;
	
	@Resource
	private FullCutService fullCutService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private CouponService couponService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	
	@Resource
	private ProductItemService productItemService;

	@Resource
	private ActivityService activityService;

	@Resource
	private ActivityAreaService activityAreaService;

	/**
	 * 优惠管理列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/shopPreferentialInfoIndex")
	public String shopPreferentialInfoIndex(Model model, HttpServletRequest request) {
		MchtInfoCustom mchtInfoCustom = this.getSessionMchtInfo(request);
		int productCount = shopPreferentialInfoService.countUpProductCount(mchtInfoCustom.getId());
		model.addAttribute("shopName", mchtInfoCustom.getShopName());
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtInfoCustom.getId());
		model.addAttribute("shopLogo", mchtInfo.getShopLogo());
		model.addAttribute("productCount", productCount);
		String searchOrderType = request.getParameter("searchOrderType");
		if(StringUtil.isEmpty(searchOrderType)){
			model.addAttribute("searchOrderType", "1");
		}else{
			model.addAttribute("searchOrderType", request.getParameter("searchOrderType"));
		}
		return "shopPreferentialInfo/shopPreferentialInfoIndex";
	}
	
	/**
	 * 优惠管理数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/getShopPreferentialInfoList")
	@ResponseBody
	public ResponseMsg getShopPreferentialInfoList(HttpServletRequest request, Page page) throws ParseException {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String searchOrderType = request.getParameter("searchOrderType");
		String searchType = request.getParameter("search_type");
		String searchStatus = request.getParameter("search_status");
		if(!StringUtil.isEmpty(searchOrderType) && !searchOrderType.equals("0")){
			if(searchOrderType.equals("1")){
				ShopPreferentialInfoExample e = new ShopPreferentialInfoExample();
				e.setOrderByClause("id desc");
				ShopPreferentialInfoExample.Criteria c = e.createCriteria();
				c.andDelFlagEqualTo("0");
				c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
				e.setLimitStart(page.getLimitStart());
				e.setLimitSize(page.getLimitSize());
				if(!StringUtil.isEmpty(searchStatus)){
					if("0".equals(searchStatus)){//未开始
						c.andBeginDateGreaterThan(new Date());
					}
					if("1".equals(searchStatus)){//活动中
						c.andBeginDateLessThanOrEqualTo(new Date());
						c.andEndDateGreaterThan(new Date());
					}
					if("2".equals(searchStatus)){//已结束
						c.andEndDateLessThanOrEqualTo(new Date());
					}			
				}
				int totalCount = shopPreferentialInfoService.countByExample(e);
				List<ShopPreferentialInfoCustom> shopPreferentialInfoCustoms = shopPreferentialInfoService.selectCustomByExample(e);
				returnData.put("Rows", shopPreferentialInfoCustoms);
				returnData.put("Total", totalCount);
			}else if(searchOrderType.equals("2")){
				CouponExample couExample = new CouponExample();
				couExample.setOrderByClause("id desc");
				Criteria createCriteria = couExample.createCriteria();
				createCriteria.andDelFlagEqualTo("0");
				createCriteria.andCouponTypeEqualTo("1");//店铺券
				createCriteria.andRangEqualTo("4");
				if(!StringUtil.isEmpty(searchType)){
					createCriteria.andPreferentialTypeEqualTo(searchType);
					
					}	
				if(!StringUtil.isEmpty(searchStatus)){
					if("0".equals(searchStatus)){//未开始
						createCriteria.andRecBeginDateGreaterThan(new Date());
					}
					if("1".equals(searchStatus)){//活动中
						createCriteria.andRecBeginDateLessThanOrEqualTo(new Date());
					/*	createCriteria.andRecBeginDateLessThanOrEqualTo(new Date());*/
						createCriteria.andRecEndDateGreaterThanOrEqualTo(new Date());
					}
					if("2".equals(searchStatus)){//已结束
						createCriteria.andRecEndDateLessThanOrEqualTo(new Date());
					}					
				}
				createCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
				couExample.setLimitStart(page.getLimitStart());
				couExample.setLimitSize(page.getLimitSize());
				int totalCount = couponService.countByExample(couExample);

				List<CouponCustom> couponCustoms = couponService.selectCouponCustomByExample(couExample);
				returnData.put("Rows", couponCustoms);
				returnData.put("Total", totalCount);
				
			}else if(searchOrderType.equals("3")){
				CouponExample couExample = new CouponExample();
				couExample.setOrderByClause("id desc");
				Criteria createCriteria = couExample.createCriteria();
				createCriteria.andDelFlagEqualTo("0");
				createCriteria.andCouponTypeEqualTo("4");//店铺券
				if(!StringUtil.isEmpty(searchStatus)){
					if("0".equals(searchStatus)){//未开始
						createCriteria.andRecBeginDateGreaterThan(new Date());
					}
					if("1".equals(searchStatus)){//活动中
						createCriteria.andRecBeginDateLessThan(new Date());
						/*createCriteria.andRecBeginDateLessThanOrEqualTo(new Date());*/
						createCriteria.andRecEndDateGreaterThan(new Date());
					}
					if("2".equals(searchStatus)){//已结束
						createCriteria.andRecEndDateLessThanOrEqualTo(new Date());
					}			
				}
				createCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
				couExample.setLimitStart(page.getLimitStart());
				couExample.setLimitSize(page.getLimitSize());
				int totalCount = couponService.countByExample(couExample);

				List<CouponCustom> couponCustoms = couponService.selectCouponCustomByExample(couExample);
				returnData.put("Rows", couponCustoms);
				returnData.put("Total", totalCount);
				
			}else if (searchOrderType.equals("4")){
				Integer status = -1;
				if (request.getParameter("search_status").length()>0){
					status = Integer.valueOf(request.getParameter("search_status"));
				}
				Integer mchtId = this.getSessionMchtInfo(request).getId();
				List<Map<String,Object>> activityAreasCount = activityAreaService.queryCouponAndAreaId(mchtId,status,0,5000);
				Integer limitStart = page.getLimitStart();
				Integer limitSize = page.getLimitSize();
				List<Map<String,Object>> activityAreas = activityAreaService.queryCouponAndAreaId(mchtId,status,limitStart,limitSize);
				List<Map<String,Object>> maps = new ArrayList<>();
				for (Map<String,Object> activityArea : activityAreas) {
					Integer id = (Integer) activityArea.get("id");
					ActivityExample where = new ActivityExample();
					where.setOrderByClause("create_date desc");
					ActivityExample.Criteria criteria = where.createCriteria();
					criteria.andActivityAreaIdEqualTo(id);
					criteria.andStatusEqualTo("6");
					criteria.andDelFlagEqualTo("0");
					List<Activity> activities = activityService.selectByExample(where);
					CouponExample couExample = new CouponExample();
					couExample.createCriteria().andActivityAreaIdEqualTo(id).andDelFlagEqualTo("0").andRangEqualTo("3");
					List<Coupon> coupons = couponService.selectByExample(couExample);
					String rules = "";
					String quantitys = "";
					for (Coupon coupon : coupons) {
						BigDecimal minimum = coupon.getMinimum();
						BigDecimal money = coupon.getMoney();
						rules += "满" + minimum + "减" + money + "<br>";
						Integer recQuantity = coupon.getRecQuantity();
						Integer grantQuantity = coupon.getGrantQuantity();
						Integer number = grantQuantity - recQuantity;
						quantitys += grantQuantity + "/" + recQuantity + "/" + number + "<br>";
					}
					for (Activity activity : activities) {
						Map<String,Object> map = new HashMap<>();
						map.put("name",activity.getName());
						//map.put("status",activity.getStatus());
						map.put("areaId",id);
						map.put("activityBeginTime",activityArea.get("activityBeginTime"));
						map.put("activityEndTime",activityArea.get("activityEndTime"));
						map.put("rules",rules);
						map.put("quantitys",quantitys);
						maps.add(map);
					}

				}
				returnData.put("Rows", maps);
				returnData.put("Total", activityAreasCount.size());
			}
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}

	
	/**
	 * 验证店铺活动的创建数量不超过20个
	watchShopView
	 * 
	 * @param request
	 * @param
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/checkShopPreferentialInfoNum")
	@ResponseBody
	public Map<String, Object> checkShopPreferentialInfoNum(Model model, HttpServletRequest request) {
		Map<String , Object> reMap = new  HashMap();
		reMap.put("returnDate", "0000");
		reMap.put("remsg", "可以添加");
		ShopPreferentialInfoExample shopPreferentialExample = new ShopPreferentialInfoExample();
		ShopPreferentialInfoExample.Criteria createCriteria = shopPreferentialExample.createCriteria();
		createCriteria.andDelFlagEqualTo("0");
		createCriteria.andEndDateGreaterThan(new Date());
		createCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		int manCount = shopPreferentialInfoService.countByExample(shopPreferentialExample );
		if(manCount>=20){
			reMap.put("returnDate", "4004");
			reMap.put("remsg", "对不起,最多只能创建20个同类型活动");
			return reMap;
		}
		return reMap;	
	}
	
	/**
	 * 添加/编辑 店铺满减活动
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/toEditShopPreferentialInfo")
	public String toEditShopPreferentialInfo(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		if(!StringUtil.isEmpty(id)){
			ShopPreferentialInfo shopPreferentialInfo = shopPreferentialInfoService.selectByPrimaryKey(Integer.parseInt(id));
			model.addAttribute("shopPreferentialInfo", shopPreferentialInfo);
			if(shopPreferentialInfo.getType().equals("2")){//2.满减
				FullCut fullCut = fullCutService.findById(shopPreferentialInfo.getPreferentialId());
				model.addAttribute("sumFlag", fullCut.getSumFlag());
				model.addAttribute("rules", fullCut.getRule());
			}
		}else{
			model.addAttribute("sumFlag", "0");
		}
		model.addAttribute("id", id);
		return "shopPreferentialInfo/toEditShopPreferentialInfo";
	}
	
	/**
	 * 保存
	 * 
	 * @param request
	 * @param
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/saveShopPreferentialInfo")
	@ResponseBody
	public ResponseMsg saveShopPreferentialInfo(HttpServletRequest request) throws ParseException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String sumFlag = request.getParameter("sumFlag");
		String rule = request.getParameter("rule");
		String replaceRule = rule.replace("\"","").replace("\"","");

		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ShopPreferentialInfoExample spie = new ShopPreferentialInfoExample();
		ShopPreferentialInfoExample.Criteria spiec = spie.createCriteria();
		spiec.andDelFlagEqualTo("0");
		spiec.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		spiec.andBeginDateGreaterThanOrEqualTo(sdf.parse(beginDate));
		spiec.andBeginDateLessThanOrEqualTo(sdf.parse(endDate));
		List<ShopPreferentialInfo> shopPreferentialInfos = shopPreferentialInfoService.selectByExample(spie);
		
		ShopPreferentialInfoExample e = new ShopPreferentialInfoExample();
		ShopPreferentialInfoExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		c.andEndDateGreaterThanOrEqualTo(sdf.parse(beginDate));
		c.andEndDateLessThanOrEqualTo(sdf.parse(endDate));
		List<ShopPreferentialInfo> shopPreferentialInfoList = shopPreferentialInfoService.selectByExample(e);
		
		ShopPreferentialInfoExample example = new ShopPreferentialInfoExample();
		ShopPreferentialInfoExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		criteria.andBeginDateLessThanOrEqualTo(sdf.parse(beginDate));
		criteria.andEndDateGreaterThanOrEqualTo(sdf.parse(endDate));
		List<ShopPreferentialInfo> spiList = shopPreferentialInfoService.selectByExample(example);
		
		ShopPreferentialInfo shopPreferentialInfo = new ShopPreferentialInfo();
		FullCut fc = new FullCut();
		if(!StringUtil.isEmpty(id)){
			shopPreferentialInfo = shopPreferentialInfoService.selectByPrimaryKey(Integer.parseInt(id));
			shopPreferentialInfos.addAll(shopPreferentialInfoList);
			shopPreferentialInfos.addAll(spiList);
			for(ShopPreferentialInfo spi:shopPreferentialInfos){
				if(!spi.getId().equals(shopPreferentialInfo.getId())){
					return new ResponseMsg(ResponseMsg.ERROR, "优惠活动时间不允许重叠");
				}
			}
			if(shopPreferentialInfo.getType().equals("2")){//2.满减
				fc = fullCutService.findById(shopPreferentialInfo.getPreferentialId());
				fc.setUpdateBy(this.getSessionMchtInfo(request).getId());
				fc.setUpdateDate(new Date());
			}
		}else{
			if(shopPreferentialInfos.size()>0 || shopPreferentialInfoList.size()>0 || spiList.size()>0){
				return new ResponseMsg(ResponseMsg.ERROR, "优惠活动时间不允许重叠");
			}
			shopPreferentialInfo.setDelFlag("0");
			shopPreferentialInfo.setCreateBy(this.getSessionMchtInfo(request).getId());
			shopPreferentialInfo.setCreateDate(new Date());
			shopPreferentialInfo.setMchtId(this.getSessionMchtInfo(request).getId());
			shopPreferentialInfo.setType("2");//2.满减
			shopPreferentialInfo.setStatus("1");//0.未启用
			fc.setDelFlag("0");
			fc.setCreateDate(new Date());
			fc.setCreateBy(this.getSessionMchtInfo(request).getId());
			fc.setRang("4");//4.店铺
			fc.setMchtId(this.getSessionMchtInfo(request).getId());
			if("1".equals(mchtInfo.getIsManageSelf())){//spop自营
				fc.setBelong("1");//1.平台
			}else{
				fc.setBelong("2");//2.商家
			}

		}
		fc.setSumFlag(sumFlag);
		if(sumFlag.equals("1")){//1.累加
			fc.setLadderFlag("0");//0.非阶梯
		}else{//0.不累加
			fc.setLadderFlag("1");//1.阶梯
		}
		fc.setRule(replaceRule.trim());
		shopPreferentialInfo.setName(name);
		shopPreferentialInfo.setBeginDate(sdf.parse(beginDate));
		shopPreferentialInfo.setEndDate(sdf.parse(endDate));
		shopPreferentialInfoService.save(shopPreferentialInfo,fc);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

	
	/**
	 * 验证店铺券的创建数量不超过20个
	watchShopView
	 * 
	 * @param request
	 * @param
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/checkCouponNum")
	@ResponseBody
	public Map<String, Object> checkCouponNum(Model model, HttpServletRequest request) {
		Map<String , Object> reMap = new  HashMap();
		reMap.put("returnDate", "0000");
		reMap.put("remsg", "可以添加");
		String id = request.getParameter("id");
		String preferentialType = request.getParameter("preferentialType");//1满减 2折扣
		if(!StringUtil.isEmpty(preferentialType)){
			if("1".equals(preferentialType)){//计算满减数量
				CouponExample manExample = new CouponExample();
				Criteria createCriteria = manExample.createCriteria();
				createCriteria.andDelFlagEqualTo("0");
				createCriteria.andRecEndDateGreaterThan(new Date());
				createCriteria.andPreferentialTypeEqualTo("1");//优惠类型
				createCriteria.andCouponTypeEqualTo("1");//优惠券类型
				createCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
				int manCount = couponService.countByExample(manExample );
				if(manCount>=20){
					reMap.put("returnDate", "4004");
					reMap.put("remsg", "对不起,最多只能创建20个同类型活动");
					return reMap;
				}
			}
			
			if("2".equals(preferentialType)){//计算折扣数量
				CouponExample manExample = new CouponExample();
				Criteria createCriteria = manExample.createCriteria();
				createCriteria.andDelFlagEqualTo("0");
				createCriteria.andRecEndDateGreaterThan(new Date());
				createCriteria.andPreferentialTypeEqualTo("2");//优惠类型
				createCriteria.andCouponTypeEqualTo("1");//优惠券类型
				createCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
				int manCount = couponService.countByExample(manExample );
				if(manCount>=20){
					reMap.put("returnDate", "4004");
					reMap.put("remsg", "对不起,最多只能创建20个同类型活动");
					return reMap;
				}
			}
			
			if("3".equals(preferentialType)){//计算折扣数量
				CouponExample manExample = new CouponExample();
				Criteria createCriteria = manExample.createCriteria();
				createCriteria.andDelFlagEqualTo("0");
				createCriteria.andRecEndDateGreaterThan(new Date());
				createCriteria.andPreferentialTypeEqualTo("1");//优惠类型
				createCriteria.andCouponTypeEqualTo("4");//优惠券类型
				createCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
				int manCount = couponService.countByExample(manExample );
				if(manCount>=20){
					reMap.put("returnDate", "4004");
					reMap.put("remsg", "对不起,最多只能创建20个同类型活动");
					return reMap;
				}
			}
			
		}
		
		return reMap;
		
	}
	
	
	/**
	 * 添加/编辑 店铺满减劵  店铺折扣券
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/shopFullReduction")
	public String shopFullReduction(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		String preferentialType = request.getParameter("preferentialType");//1满减 2折扣
		if(!StringUtil.isEmpty(preferentialType)){
			model.addAttribute("preferentialType", preferentialType);
		}
		if(!StringUtil.isEmpty(id)){
			Coupon coupon = couponService.selectByPrimaryKey(Integer.parseInt(id));
			model.addAttribute("preferentialType", coupon.getPreferentialType());
			model.addAttribute("coupon", coupon);	
		}	
		model.addAttribute("id", id);
		return "shopPreferentialInfo/shopFullReduction";
	}
	
	
	
	/**
	 * 添加/编辑 商品券
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/productCertificates")
	public  String productCertificates(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		String preferentialType = request.getParameter("preferentialType");//1满减 2折扣
		
		String isHide = request.getParameter("isHide");
		if(!StringUtil.isEmpty(isHide)){
			model.addAttribute("isHide", isHide);
		}

		if(!StringUtil.isEmpty(id)){
			Coupon coupon = couponService.selectByPrimaryKey(Integer.parseInt(id));
			model.addAttribute("preferentialType", coupon.getPreferentialType());
			//得到ids对应的商品
			List<ProductCustom> productList = new ArrayList<>();
			List<Integer> productIds = new ArrayList<>();
			
			String typeIds = coupon.getTypeIds();
			String[] split = typeIds.split(",");
			for (int i = 0; i < split.length; i++) {
				ProductCustom productCustom = productService.selectProductCustomByPrimaryKey(Integer.valueOf(split[i]));
				productIds.add(productCustom.getId());
				productList.add(productCustom);
			}	
			model.addAttribute("productList",productList);
			model.addAttribute("productIds",productIds);

			model.addAttribute("typeIds", coupon.getTypeIds());
			model.addAttribute("coupon", coupon);
			
		}
		
		model.addAttribute("id", id);
		return "shopPreferentialInfo/productCertificates";
	}
	
	
	
	
	
	/**
	 * 店铺满减劵 和 折扣券 保存
	 * 
	 * @param request
	 * @param
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/shopFullReductionSave")
	@ResponseBody
	public ResponseMsg shopFullReductionSave(HttpServletRequest request) throws ParseException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String recBeginDate = request.getParameter("recBeginDate");
		String recEndDate = request.getParameter("recEndDate");
		String grantQuantity = request.getParameter("grantQuantity");//发行量
		String recEach = request.getParameter("recEach");//限领数量
		String careShopCanRec = request.getParameter("careShopCanRec");//关注店铺可领
		String preferentialType = request.getParameter("preferentialType");//1满减 2折扣

		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());
		//满减
		String money = request.getParameter("money");
		String minimum = request.getParameter("minimum");
		//折扣
		String minicount = request.getParameter("minicount");
		String discount = request.getParameter("discount");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Coupon coupon = new Coupon();
		if(!StringUtil.isEmpty(id)){
			coupon = couponService.selectByPrimaryKey(Integer.parseInt(id));

			coupon.setStatus("1");//启用
			coupon.setDelFlag("0");
			coupon.setUpdateBy(this.getSessionMchtInfo(request).getId());
			coupon.setUpdateDate(new Date());
			coupon.setRecType("1");//领取方式
			coupon.setRecQuantity(0);//领取量
			coupon.setCanSuperpose("0");//是否可叠加
			coupon.setRang("4");
			if("1".equals(mchtInfo.getIsManageSelf())){//spop自营 1.平台
				coupon.setBelong("1");
			}else{
				coupon.setBelong("2");
			}
			coupon.setExpiryType("1");
			coupon.setRecLimitType("2");
			coupon.setMchtId(this.getSessionMchtInfo(request).getId());
			if(!StringUtil.isEmpty(money)){
				BigDecimal bd=new BigDecimal(money); 
				coupon.setMoney(bd);
			}
			if(!StringUtil.isEmpty(minimum)){
				BigDecimal minmoney=new BigDecimal(minimum); 
				coupon.setMinimum(minmoney);
			}
			if(!StringUtil.isEmpty(careShopCanRec)){
				coupon.setCareShopCanRec(careShopCanRec);	
			}
			
			if(!StringUtil.isEmpty(recBeginDate)){
				coupon.setExpiryBeginDate(sdf.parse(recBeginDate));
				coupon.setRecBeginDate(sdf.parse(recBeginDate));
			}
			if(!StringUtil.isEmpty(recEndDate)){
				coupon.setExpiryEndDate(sdf.parse(recEndDate));
				coupon.setRecEndDate(sdf.parse(recEndDate));
			}
			if(!StringUtil.isEmpty(grantQuantity)){
				coupon.setGrantQuantity(Integer.parseInt(grantQuantity));				
			}
			if(!StringUtil.isEmpty(recEach)){
				coupon.setRecEach(Integer.parseInt(recEach));				
			}
			if(!StringUtil.isEmpty(name)){
				coupon.setName(name);				
			}
			if(!StringUtil.isEmpty(minicount)){
				coupon.setMinicount(Integer.parseInt(minicount));
			}
			if(!StringUtil.isEmpty(discount)){
				Double discountNum = (Double.parseDouble(discount))*0.1;
				BigDecimal discounts=new BigDecimal(discountNum);		
				coupon.setDiscount(discounts);
			}
			if(!StringUtil.isEmpty(preferentialType)){
				coupon.setPreferentialType(preferentialType);
			}
			

			
		}else{	
			coupon.setStatus("1");//启用
			coupon.setDelFlag("0");
			coupon.setCreateBy(this.getSessionMchtInfo(request).getId());
			coupon.setCreateDate(new Date());
			coupon.setRecType("1");//领取方式
			coupon.setRecQuantity(0);//领取量
			coupon.setRang("4");//使用范围
			if("1".equals(mchtInfo.getIsManageSelf())){//spop自营 1.平台
				coupon.setBelong("1");
			}else{
				coupon.setBelong("2");//商家负责
			}
			coupon.setCanSuperpose("0");//是否可叠加
			coupon.setExpiryType("1");//有效类型绝对时间
			coupon.setRecLimitType("2");//没人限领指定数量
			coupon.setMchtId(this.getSessionMchtInfo(request).getId());
			
			
			if(!StringUtil.isEmpty(preferentialType)){
				if("1".equals(preferentialType)){
					coupon.setConditionType("2");
				}
				if("2".equals(preferentialType)){
					coupon.setConditionType("3");
				}
			}
			if(!StringUtil.isEmpty(money)){
				BigDecimal bd=new BigDecimal(money); 
				coupon.setMoney(bd);
			}
			if(!StringUtil.isEmpty(minimum)){
				BigDecimal minmoney=new BigDecimal(minimum); 
				coupon.setMinimum(minmoney);
			}
			if(!StringUtil.isEmpty(careShopCanRec)){
				coupon.setCareShopCanRec(careShopCanRec);	
			}
			
			if(!StringUtil.isEmpty(recBeginDate)){
				coupon.setExpiryBeginDate(sdf.parse(recBeginDate));
				coupon.setRecBeginDate(sdf.parse(recBeginDate));
			}
			if(!StringUtil.isEmpty(recEndDate)){
				coupon.setExpiryEndDate(sdf.parse(recEndDate));
				coupon.setRecEndDate(sdf.parse(recEndDate));
			}
			if(!StringUtil.isEmpty(grantQuantity)){
				coupon.setGrantQuantity(Integer.parseInt(grantQuantity));				
			}
			if(!StringUtil.isEmpty(recEach)){
				coupon.setRecEach(Integer.parseInt(recEach));				
			}
			if(!StringUtil.isEmpty(name)){
				coupon.setName(name);				
			}
			if(!StringUtil.isEmpty(minicount)){
				coupon.setMinicount(Integer.parseInt(minicount));
			}
			if(!StringUtil.isEmpty(discount)){
				Double discountNum = (Double.parseDouble(discount))*0.1;			
				BigDecimal discounts=new BigDecimal(discountNum);
				coupon.setDiscount(discounts);
			}
			if(!StringUtil.isEmpty(preferentialType)){
				coupon.setPreferentialType(preferentialType);
			}

	}
		couponService.saveCoupon(coupon);

		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	
	
	
	/**
	 * 查看商品券 watchView
	 * 
	 * @param request
	 * @param
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/watchView")
	public String watchView(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		/*String preferentialType = request.getParameter("preferentialType");//1满减 2折扣
*/		
		if(!StringUtil.isEmpty(id)){
			Coupon coupon = couponService.selectByPrimaryKey(Integer.parseInt(id));
			model.addAttribute("preferentialType", coupon.getPreferentialType());
			model.addAttribute("coupon", coupon);
			
			//得到ids对应的商品
			List<ProductCustom> productList = new ArrayList<>();
			String typeIds = coupon.getTypeIds();
			
			if(!StringUtil.isEmpty(typeIds)){
			String[] split = typeIds.split(",");
			for (int i = 0; i < split.length; i++) {
				ProductCustom productCustom = productService.selectProductCustomByPrimaryKey(Integer.valueOf(split[i]));
				productList.add(productCustom);
			}	
			model.addAttribute("productList",productList);
			}
		}
		
		
		model.addAttribute("id", id);
		return "shopPreferentialInfo/watchView";
	}
	
	/**
	 * 查看店铺满减活动 
	watchShopView
	 * 
	 * @param request
	 * @param
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/watchShopView")
	public String watchShopView(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
	
		if(!StringUtil.isEmpty(id)){
			
			ShopPreferentialInfo shopPreferentialInfo = shopPreferentialInfoService.selectByPrimaryKey(Integer.parseInt(id));
			model.addAttribute("shopPreferentialInfo", shopPreferentialInfo);
			
			if(shopPreferentialInfo.getType().equals("2")){//2.满减
				FullCut fullCut = fullCutService.findById(shopPreferentialInfo.getPreferentialId());
				model.addAttribute("sumFlag", fullCut.getSumFlag());
				model.addAttribute("rules", fullCut.getRule());
			}
		}else{
			model.addAttribute("sumFlag", "0");
		}
			

		
		model.addAttribute("id", id);
		return "shopPreferentialInfo/watchShopView";
	}

	/**
	 * 特卖优惠券追加页面
	watchShopView
	 * 
	 * @param request
	 * @param
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/addQuantity")
	public String addQuantity(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		model.addAttribute("type", type);
		if ("4".equals(type)){
			model.addAttribute("areaId", id);
		}
        return "shopPreferentialInfo/addQuantity";
	}

    @RequestMapping({"/appendQuantity"})
    public String appendQuantity(Model model, HttpServletRequest request) {
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        model.addAttribute("type", type);
        if (!StringUtil.isEmpty(id)) {
            Coupon coupon = (Coupon)this.couponService.selectByPrimaryKey(Integer.parseInt(id));
            model.addAttribute("coupon", coupon);
        }

        return "shopPreferentialInfo/appendQuantity";
    }
	
	/**
	 * 特卖优惠券追加
	watchShopView
	 * 
	 * @param request
	 * @param
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/appendGrantQuantitys")
	@ResponseBody
	public ResponseMsg appendGrantQuantitys(Model model, HttpServletRequest request) {
		String areaId = request.getParameter("areaId");
		String grantQuantitys = request.getParameter("grantQuantity");
		if (!StringUtil.isEmpty(grantQuantitys)&&!StringUtil.isEmpty(areaId)){
			CouponExample where = new CouponExample();
			where.createCriteria().andActivityAreaIdEqualTo(Integer.valueOf(areaId));
			List<Coupon> coupons = couponService.selectByExample(where);
			for (Coupon coupon : coupons) {
				Integer grantQuantity = coupon.getGrantQuantity();
				coupon.setGrantQuantity(grantQuantity+Integer.parseInt(grantQuantitys));
				couponService.updateByPrimaryKeySelective(coupon);
			}
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}


    @RequestMapping({"/addGrantQuantitys"})
    @ResponseBody
    public ResponseMsg addGrantQuantitys(Model model, HttpServletRequest request) {
        String id = request.getParameter("id");
        String grantQuantitys = request.getParameter("grantQuantity");
        if (!StringUtil.isEmpty(grantQuantitys) && !StringUtil.isEmpty(id)) {
            Coupon coupon = (Coupon)this.couponService.selectByPrimaryKey(Integer.parseInt(id));
            Integer grantQuantity = coupon.getGrantQuantity();
            coupon.setGrantQuantity(grantQuantity + Integer.parseInt(grantQuantitys));
            this.couponService.updateByPrimaryKeySelective(coupon);
        }

        return new ResponseMsg("0000", "请求成功");
    }
	
	
	
	/**
	 * 商品券的保存
	 * 
	 * @param request
	 * @param
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/saveCertificates")
	@ResponseBody
	public ResponseMsg saveCertificates(HttpServletRequest request) throws ParseException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String recBeginDate = request.getParameter("recBeginDate");
		String recEndDate = request.getParameter("recEndDate");
		String grantQuantity = request.getParameter("grantQuantity");//发行量
		String recEach = request.getParameter("recEach");//限领数量
		String careShopCanRec = request.getParameter("careShopCanRec");//关注店铺可领
		String preferentialType = request.getParameter("preferentialType");//1满减 2折扣
		String money = request.getParameter("money");//面额
		String isIntegralTurntable = request.getParameter("isIntegralTurntable");//是否参加积分抽奖
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());
		//商品券的ID集合
		String paramList = request.getParameter("paramList");
		JSONArray productIdsList = JSONArray.fromObject(paramList);				
		List<String> list = (List<String>) JSONArray.toCollection(productIdsList, String.class);//相关订单号集合
		String idsList="";
		for (int i = 0; i < list.size(); i++) {
			if(i==0){
				idsList+=list.get(i);
			}else{
				idsList+=","+list.get(i);
			}
		}
		String replaceIds = idsList.replace("'", "");
		
		String[] split = replaceIds.split(",");
		if(!StringUtil.isEmpty(id)){
			Coupon coupon = couponService.selectByPrimaryKey(Integer.parseInt(id));
			coupon.setStatus("1");//启用
			coupon.setDelFlag("0");
			coupon.setCreateBy(this.getSessionMchtInfo(request).getId());
			coupon.setCreateDate(new Date());
			coupon.setCanSuperpose("0");//是否可叠加
			coupon.setConditionType("2");
			coupon.setRang("4");//使用范围
			if("1".equals(mchtInfo.getIsManageSelf())){//spop自营 1.平台
				coupon.setBelong("1");
			}else{
				coupon.setBelong("2");//商家负责
			}
			coupon.setExpiryType("1");//有效类型相对时间
			coupon.setRecQuantity(0);//领取量
			if(StringUtils.equals(isIntegralTurntable,"1")){
				coupon.setRecLimitType("3");//不限
				coupon.setRecEach(0);
			}else{
				if(!StringUtil.isEmpty(recEach)){
					coupon.setRecEach(Integer.parseInt(recEach));
				}
				coupon.setRecLimitType("2");//每人限领指定数量
			}
			coupon.setIsIntegralTurntable(isIntegralTurntable);//是否参与积分转盘
			coupon.setMchtId(this.getSessionMchtInfo(request).getId());
			if(!StringUtil.isEmpty(careShopCanRec)){
				coupon.setCareShopCanRec(careShopCanRec);	
			}
			if(!StringUtil.isEmpty(recBeginDate)){
				coupon.setExpiryBeginDate(sdf.parse(recBeginDate));
				coupon.setRecBeginDate(sdf.parse(recBeginDate));
			}
			if(!StringUtil.isEmpty(recEndDate)){
				coupon.setExpiryEndDate(sdf.parse(recEndDate));
				coupon.setRecEndDate(sdf.parse(recEndDate));
			}
			if(!StringUtil.isEmpty(grantQuantity)){
				coupon.setGrantQuantity(Integer.parseInt(grantQuantity));				
			}
			if(!StringUtil.isEmpty(name)){
				coupon.setName(name);				
			}
			if(!StringUtil.isEmpty(preferentialType)){
				coupon.setPreferentialType(preferentialType);
			}
			
			if(!StringUtil.isEmpty(paramList)){
				coupon.setTypeIds(split[0]);//商品id
				coupon.setCouponType("4");//优惠券类型(店铺)
				coupon.setPreferentialType("1");//优惠类型
				coupon.setRecType("1");//领取方式
				
			}		
			
			if(!StringUtil.isEmpty(money)){
				BigDecimal bd=new BigDecimal(money); 
				coupon.setMoney(bd);
			}
			couponService.updateByPrimaryKeySelective(coupon);
		}else{
			for (int i = 0; i < split.length; i++) {
			Coupon coupon = new Coupon();
			coupon.setStatus("1");//启用
			coupon.setDelFlag("0");
			coupon.setCreateBy(this.getSessionMchtInfo(request).getId());
			coupon.setCreateDate(new Date());
			coupon.setCanSuperpose("0");//是否可叠加
			coupon.setConditionType("2");
			coupon.setRang("4");//使用范围
			if("1".equals(mchtInfo.getIsManageSelf())){//spop自营 1.平台
				coupon.setBelong("1");
			}else{
				coupon.setBelong("2");//商家负责
			}
			coupon.setExpiryType("1");//有效类型相对时间
			coupon.setRecQuantity(0);//领取量
			if(StringUtils.equals(isIntegralTurntable,"1")){
				coupon.setRecLimitType("3");//不限
				coupon.setRecEach(0);
			}else{
				if(!StringUtil.isEmpty(recEach)){
					coupon.setRecEach(Integer.parseInt(recEach));
				}
				coupon.setRecLimitType("2");//每人限领指定数量
			}
			coupon.setIsIntegralTurntable(isIntegralTurntable);//是否参与积分转盘
			coupon.setMchtId(this.getSessionMchtInfo(request).getId());
			if(!StringUtil.isEmpty(careShopCanRec)){
				coupon.setCareShopCanRec(careShopCanRec);	
			}
			
			if(!StringUtil.isEmpty(recBeginDate)){
				coupon.setExpiryBeginDate(sdf.parse(recBeginDate));
				coupon.setRecBeginDate(sdf.parse(recBeginDate));
			}
			if(!StringUtil.isEmpty(recEndDate)){
				coupon.setExpiryEndDate(sdf.parse(recEndDate));
				coupon.setRecEndDate(sdf.parse(recEndDate));
			}
			if(!StringUtil.isEmpty(grantQuantity)){
				coupon.setGrantQuantity(Integer.parseInt(grantQuantity));				
			}
			if(!StringUtil.isEmpty(name)){
				coupon.setName(name);				
			}
			if(!StringUtil.isEmpty(preferentialType)){
				coupon.setPreferentialType(preferentialType);
			}
			
			if(!StringUtil.isEmpty(paramList)){
				coupon.setTypeIds(split[i]);//商品id
				coupon.setCouponType("4");//优惠券类型(店铺)
				coupon.setPreferentialType("1");//优惠类型
				coupon.setRecType("1");//领取方式
			}		
			
			if(!StringUtil.isEmpty(money)){
				BigDecimal bd=new BigDecimal(money); 
				coupon.setMoney(bd);
			}
			couponService.insertSelective(coupon);
			
			}
		}
			
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

	
	
	
	/**
	 * 转到商品选择页面
	 * 
	 * @param request
	 * @return
	 */
	//商品选择
	@RequestMapping("/shopDynamicChoose")
	public ModelAndView shopDynamicChoose(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<>();
		
		String isHide = request.getParameter("isHide");
		if(!StringUtil.isEmpty(isHide)){
			paramMap.put("isHide", isHide);
		}
		//活动类型标识
		String activityType = request.getParameter("activityType");
		paramMap.put("activityType", activityType);
		//品牌
		paramMap.put("productBrandList", mchtProductBrandService.getMchtProductBrandList(getUserInfo().getMchtId()));
		return new ModelAndView("/shopPreferentialInfo/shopDynamicChoose",paramMap);
	}
	
	
	//商品列表
	@RequestMapping("/productList")
	@ResponseBody
	public ResponseMsg productList(HttpServletRequest request,Model model,Page page) {
		String activityType = request.getParameter("activityType");
		
		Map<String, Object> returnData = new HashMap<String, Object>();
		ProductCustomExample productCustomExample = new ProductCustomExample();
		ProductCustomExample.ProductCustomCriteria productCustomCriteria = productCustomExample.createCriteria();
		productCustomCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andStatusEqualTo("1").andSaleTypeEqualTo("1");
		productCustomExample.setOrderByClause("weights DESC");
		//商品ID、名称、货号  productBrand
		String brandId = request.getParameter("brandId");
		if (!StringUtil.isEmpty(request.getParameter("brandId"))) {
			productCustomCriteria.andBrandIdEqualTo(Integer.parseInt(brandId));
			/*productCustomCriteria.andProductBrandIdIn(Integer.parseInt(brandId));*/
		}
		
		
		String productIDorNameorArtNo = request.getParameter("productIDorNameorArtNo");
		String productIdData = request.getParameter("productIdData");
		String productName = request.getParameter("productName");
		String productArtNo = request.getParameter("productArtNo");

		if(!StringUtil.isEmpty(productIdData)){//商品ID
			List<String> idList = Arrays.asList(productIdData.split("\r\n"));	
			if("0".equals(productIDorNameorArtNo)){	
				productCustomCriteria.andCodeIn(idList);
			}
		}
		if(!StringUtil.isEmpty(productName)){//商品名称
			List<String> idList = Arrays.asList(productName.split("\r\n"));	
			if("1".equals(productIDorNameorArtNo)){	
				productCustomCriteria.andNameIn(idList);
			}
		}
		if(!StringUtil.isEmpty(productArtNo)){//商品货号
			List<String> idList = Arrays.asList(productArtNo.split("\r\n"));	
			if("2".equals(productIDorNameorArtNo)){	
				productCustomCriteria.andArtNoIn(idList);
			}
		}
		
		String pageNumberStr = request.getParameter("pageNumber");
		Integer pageNumber;
		if(!StringUtil.isEmpty(pageNumberStr)){
			pageNumber = Integer.parseInt(pageNumberStr);
		}else{
			pageNumber = page.getPage();
		}
		
		//价格
		if (!StringUtil.isEmpty(request.getParameter("priceMin"))) {
			
			productCustomCriteria.andProductChooseMinActivityPriceMoreThan(Integer.valueOf(request.getParameter("priceMin")));
		}
		if (!StringUtil.isEmpty(request.getParameter("priceMax"))) {
			productCustomCriteria.andProductChooseMaxActivityPriceLessThan(Integer.valueOf(request.getParameter("priceMax")));
		}
		
		int totalCount = productService.countProductCustomByExample(productCustomExample);
		
		String pageSize = request.getParameter("pageSize");
		if(StringUtil.isEmpty(pageSize)){
			pageSize ="10";
		}
		productCustomExample.setLimitStart(Integer.parseInt(pageSize)  * (pageNumber - 1));
		productCustomExample.setLimitSize(Integer.parseInt(pageSize));
		List<ProductCustom> productCustoms = productService.selectProductCustomByExample(productCustomExample);
		returnData.put("Rows", productCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	
	

	//商品添加
	@RequestMapping("/addProduct")
	@ResponseBody
	public ResponseMsg addProduct(HttpServletRequest request) {
		String productId = request.getParameter("productId");
		String money = request.getParameter("money");
		String  price ="";
		try{
			
			if(!StringUtil.isEmpty(productId)&&!StringUtil.isEmpty(money)){
				//item表商品价格
				ProductItemExample itemExample = new ProductItemExample();
				itemExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(Integer.parseInt(productId));
				List<ProductItem> productItemlist = productItemService.selectByExample(itemExample );
				ProductItem productItem = productItemlist.get(0);
				//SVIP折扣
				Product product = productService.selectByPrimaryKey(Integer.parseInt(productId));
				BigDecimal svipDiscount = product.getSvipDiscount();
				
				if(svipDiscount!=null){
					
					BigDecimal tagPrice =productItem.getTagPrice().multiply(svipDiscount);//吊牌价
					BigDecimal mallPrice = productItem.getMallPrice().multiply(svipDiscount);//商城价
					BigDecimal salePrice = productItem.getSalePrice().multiply(svipDiscount);//活动价
					
					BigDecimal moneys = new BigDecimal(money);
					if(productItem.getSalePrice()!=null){
						if( moneys.compareTo(salePrice)==1){
							return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG,false);
						}
						price = salePrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"";
					}else if(productItem.getMallPrice()!=null){
						if( moneys.compareTo(mallPrice)==1 ){
							return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG,false);
						}
						price = mallPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"";
					}else if(productItem.getTagPrice()!=null){
						if(moneys.compareTo(tagPrice)==1){
							return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG,false);
						}			
						price = tagPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"";

					}
				}else{
					BigDecimal tagPrice =productItem.getTagPrice();
					BigDecimal mallPrice = productItem.getMallPrice();
					BigDecimal salePrice = productItem.getSalePrice();
					BigDecimal moneys = new BigDecimal(money);		
					if(productItem.getSalePrice()!=null){
						if( moneys.compareTo(salePrice)==1){
						return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG,false);
						}
						price = productItem.getSalePrice()+"";
					}else if(productItem.getMallPrice()!=null){
						if( moneys.compareTo(mallPrice)==1 ){
							return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG,false);
						}
						price = productItem.getMallPrice()+"";
					}else if(productItem.getTagPrice()!=null){
						if(moneys.compareTo(tagPrice)==1){
							return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG,false);
						}	
						price = productItem.getTagPrice()+"";

					}
				}
				
				
				
				
				
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,price);
	}
	
	
	
	//商品查询
	@RequestMapping("/selectProduct")
	@ResponseBody
	public ProductCustom selectProduct(HttpServletRequest request) {
		String productId = request.getParameter("productId");
		ProductCustom productCustom = productService.selectProductCustomByPrimaryKey(Integer.valueOf(productId));
		
		
		return productCustom;
	}
	
	/**
	 * 优惠券的启用或过期
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
/*	@RequestMapping(value = "/changeCouponStatus")
	@ResponseBody
	public ResponseMsg changeCouponStatus(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			Coupon coupon = couponService.selectByPrimaryKey(Integer.parseInt(id));
			if(coupon.getStatus().equals("0")){
				coupon.setStatus("1");
			}else {
				coupon.setStatus("2");
			}
			coupon.setUpdateDate(new Date());
			coupon.setUpdateBy(this.getSessionMchtInfo(request).getId());
			couponService.updateByPrimaryKeySelective(coupon);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}*/
	

	
	/**
	 *优惠券的删除
	 * @param request
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/removeCoupon")
	@ResponseBody
	public ResponseMsg removeCoupon(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			Coupon coupon = couponService.selectByPrimaryKey(Integer.parseInt(id));
			coupon.setUpdateDate(new Date());
			coupon.setUpdateBy(this.getSessionMchtInfo(request).getId());
			coupon.setDelFlag("1");
			couponService.updateByPrimaryKeySelective(coupon);	
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	

	/**
	 * 优惠券的提前结束
	 * @param request
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/endInAdvanceCoupon")
	@ResponseBody
	public ResponseMsg endInAdvanceCoupon(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			Coupon coupon = couponService.selectByPrimaryKey(Integer.parseInt(id));
			coupon.setUpdateDate(new Date());
			coupon.setUpdateBy(this.getSessionMchtInfo(request).getId());
			coupon.setStatus("2");
			Date now = new Date();
			if(coupon.getRecEndDate().after(now)){
				coupon.setRecEndDate(now);
			}else{
				return new ResponseMsg(ResponseMsg.ERROR, "活动时间已结束，无需提前结束");
			}
			couponService.updateByPrimaryKeySelective(coupon);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	
	
	
	
	/**
	 * 启用/不启用
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
/*	@RequestMapping(value = "/changeStatus")
	@ResponseBody
	public ResponseMsg changeStatus(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			ShopPreferentialInfo shopPreferentialInfo = shopPreferentialInfoService.selectByPrimaryKey(Integer.parseInt(id));
			if(shopPreferentialInfo.getStatus().equals("0")){
				shopPreferentialInfo.setStatus("1");
			}else{
				shopPreferentialInfo.setStatus("0");
			}
			shopPreferentialInfo.setUpdateDate(new Date());
			shopPreferentialInfo.setUpdateBy(this.getSessionMchtInfo(request).getId());
			shopPreferentialInfoService.updateByPrimaryKeySelective(shopPreferentialInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}*/
	
	/**
	 * 删除
	 * @param request
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseMsg delete(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			ShopPreferentialInfo shopPreferentialInfo = shopPreferentialInfoService.selectByPrimaryKey(Integer.parseInt(id));
			shopPreferentialInfo.setUpdateDate(new Date());
			shopPreferentialInfo.setUpdateBy(this.getSessionMchtInfo(request).getId());
			shopPreferentialInfo.setDelFlag("1");//删除
			shopPreferentialInfoService.updateByPrimaryKeySelective(shopPreferentialInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

	
	/**
	 * 提前结束
	 * @param request
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/endInAdvance")
	@ResponseBody
	public ResponseMsg endInAdvance(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			ShopPreferentialInfo shopPreferentialInfo = shopPreferentialInfoService.selectByPrimaryKey(Integer.parseInt(id));
			shopPreferentialInfo.setUpdateDate(new Date());
			shopPreferentialInfo.setUpdateBy(this.getSessionMchtInfo(request).getId());
			Date now = new Date();
			if(shopPreferentialInfo.getEndDate().after(now)){
				shopPreferentialInfo.setEndDate(now);
			}else{
				return new ResponseMsg(ResponseMsg.ERROR, "活动时间已结束，无需提前结束");
			}
			shopPreferentialInfoService.updateByPrimaryKeySelective(shopPreferentialInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 更新店铺logo
	 * @param request
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/updateShopLogo")
	@ResponseBody
	public ResponseMsg updateShopLogo(HttpServletRequest request) {
		try {
			String shopLogo = request.getParameter("shopLogo");
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());
			mchtInfo.setShopLogo(shopLogo);
			mchtInfo.setUpdateDate(new Date());
			mchtInfo.setUpdateBy(this.getSessionMchtInfo(request).getId());
			mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

}
