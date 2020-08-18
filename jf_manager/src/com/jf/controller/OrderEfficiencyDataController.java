package com.jf.controller;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtils;
import com.jf.dao.DeliveryOvertimeCnfMapper;
import com.jf.entity.DeliveryOvertimeCnf;
import com.jf.entity.DeliveryOvertimeCnfExample;
import com.jf.entity.OrderAbnormalLogCustom;
import com.jf.entity.OrderAbnormalLogCustomExample;
import com.jf.entity.PlatformContact;
import com.jf.entity.PlatformContactExample;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductBrandExample;
import com.jf.entity.SysStatus;
import com.jf.entity.SysStatusExample;
import com.jf.entity.ViolateOrder;
import com.jf.entity.ViolatePunishStandard;
import com.jf.entity.ViolatePunishStandardExample;
import com.jf.service.OrderAbnormalLogService;
import com.jf.service.PlatformContactService;
import com.jf.service.ProductBrandService;
import com.jf.service.SubOrderService;
import com.jf.service.SysStatusService;
import com.jf.service.ViolatePunishStandardService;
import com.jf.vo.Page;

@Controller
@RequestMapping("/orderEfficiencyData")
public class OrderEfficiencyDataController extends BaseController {
	
	@Resource
	private DeliveryOvertimeCnfMapper deliveryOvertimeCnfMapper;
	
	@Resource
	private SubOrderService subOrderService;
	
	@Resource
	private ProductBrandService productBrandService;
	
	@Resource
	private OrderAbnormalLogService orderAbnormalLogService;
	
	@Resource
	private ViolatePunishStandardService violatePunishStandardService;
	
	@Resource
	private SysStatusService sysStatusService;
	
	@Resource
	private PlatformContactService platformContactService;

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	* @Title  发货超时设置  
	* @author yjc
	* @date 2018年1月10日11:37:09
	 */
	@RequestMapping("/deliveryOvertimeCnf/list.shtml")
	public ModelAndView deliveryOvertimeCnfList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/orderEfficiencyData/deliveryOvertimeCnfList");
		return m;
	}
	
	/**
	 * 
	* @Title 发货超时设置
	* @author yjc
	* @date 2018年1月10日11:36:48
	 */
	@RequestMapping("/deliveryOvertimeCnf/data.shtml")
	@ResponseBody
	public Map<String, Object> deliveryOvertimeCnfData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try{
			String date = request.getParameter("date");
			DeliveryOvertimeCnfExample example = new DeliveryOvertimeCnfExample();
			example.setOrderByClause("begin_date desc");
			DeliveryOvertimeCnfExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			if(!StringUtils.isEmpty(date)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				criteria.andBeginDateLessThanOrEqualTo(sdf.parse(date +" 00:00:00"));
				criteria.andEndDateGreaterThanOrEqualTo(sdf.parse(date +" 23:59:59"));
			}
			Integer totalCount = deliveryOvertimeCnfMapper.countByExample(example);
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			List<DeliveryOvertimeCnf> deliveryOvertimeCnfs = deliveryOvertimeCnfMapper.selectByExample(example);
			resMap.put("Rows", deliveryOvertimeCnfs);
			resMap.put("Total", totalCount);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/deliveryOvertimeCnf/delete.shtml")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "删除成功");
		try {
			String id = request.getParameter("id");
			if(!StringUtils.isEmpty(id)){
				DeliveryOvertimeCnf deliveryOvertimeCnf = deliveryOvertimeCnfMapper.selectByPrimaryKey(Integer.parseInt(id));
				if(deliveryOvertimeCnf!=null){
					deliveryOvertimeCnf.setDelFlag("1");
					deliveryOvertimeCnfMapper.updateByPrimaryKeySelective(deliveryOvertimeCnf);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 添加/修改页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/deliveryOvertimeCnf/toEdit.shtml")
	public ModelAndView add(HttpServletRequest request) {
		String rtPage = "/dataCenter/orderEfficiencyData/toEdit";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			DeliveryOvertimeCnf deliveryOvertimeCnf = deliveryOvertimeCnfMapper.selectByPrimaryKey(Integer.parseInt(id));
			resMap.put("deliveryOvertimeCnf", deliveryOvertimeCnf);
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 保存
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/deliveryOvertimeCnf/save.shtml")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String begin_date = request.getParameter("begin_date");
			String end_date = request.getParameter("end_date");
			String overtime = request.getParameter("overtime");
			String remarks = request.getParameter("remarks");
			String timeType = request.getParameter("timeType");
			String deliveryDate = request.getParameter("deliveryDate");
			String productNoticePic = request.getParameter("productNoticePic");
			String id = request.getParameter("id");
			List<Integer> idList = new ArrayList<Integer>();
			DeliveryOvertimeCnfExample e1 = new DeliveryOvertimeCnfExample();
			DeliveryOvertimeCnfExample.Criteria c1 = e1.createCriteria();
			c1.andDelFlagEqualTo("0");
			c1.andTimeTypeEqualTo(timeType);
			c1.andBeginDateGreaterThanOrEqualTo(sdf.parse(begin_date));
			c1.andBeginDateLessThanOrEqualTo(sdf.parse(end_date));
			int count1 = deliveryOvertimeCnfMapper.countByExample(e1);
			List<DeliveryOvertimeCnf> deliveryOvertimeCnf1List = deliveryOvertimeCnfMapper.selectByExample(e1);
			if(deliveryOvertimeCnf1List!=null && deliveryOvertimeCnf1List.size()>0){
				if(!idList.contains(deliveryOvertimeCnf1List.get(0).getId())){
					idList.add(deliveryOvertimeCnf1List.get(0).getId());
				}
			}
			DeliveryOvertimeCnfExample e2 = new DeliveryOvertimeCnfExample();
			DeliveryOvertimeCnfExample.Criteria c2 = e2.createCriteria();
			c2.andDelFlagEqualTo("0");
			c2.andTimeTypeEqualTo(timeType);
			c2.andEndDateGreaterThanOrEqualTo(sdf.parse(begin_date));
			c2.andEndDateLessThanOrEqualTo(sdf.parse(end_date));
			int count2 = deliveryOvertimeCnfMapper.countByExample(e2);
			List<DeliveryOvertimeCnf> deliveryOvertimeCnf2List = deliveryOvertimeCnfMapper.selectByExample(e2);
			if(deliveryOvertimeCnf2List!=null && deliveryOvertimeCnf2List.size()>0){
				if(!idList.contains(deliveryOvertimeCnf2List.get(0).getId())){
					idList.add(deliveryOvertimeCnf2List.get(0).getId());
				}
			}
			DeliveryOvertimeCnfExample e3 = new DeliveryOvertimeCnfExample();
			DeliveryOvertimeCnfExample.Criteria c3 = e3.createCriteria();
			c3.andDelFlagEqualTo("0");
			c3.andTimeTypeEqualTo(timeType);
			c3.andBeginDateLessThanOrEqualTo(sdf.parse(begin_date));
			c3.andEndDateGreaterThanOrEqualTo(sdf.parse(end_date));
			int count3 = deliveryOvertimeCnfMapper.countByExample(e3);
			List<DeliveryOvertimeCnf> deliveryOvertimeCnf3List = deliveryOvertimeCnfMapper.selectByExample(e3);
			if(deliveryOvertimeCnf3List!=null && deliveryOvertimeCnf3List.size()>0){
				if(!idList.contains(deliveryOvertimeCnf3List.get(0).getId())){
					idList.add(deliveryOvertimeCnf3List.get(0).getId());
				}
			}
			DeliveryOvertimeCnf deliveryOvertimeCnf = new DeliveryOvertimeCnf();
			if(StringUtils.isEmpty(id)){//新增
				if(count1>0 || count2>0 || count3>0){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "对不起，时间与其它数据有交集，不能添加。");
					return resMap;
				}
				deliveryOvertimeCnf.setDelFlag("0");
				deliveryOvertimeCnf.setCreateDate(new Date());
				deliveryOvertimeCnf.setCreateBy(this.getSessionStaffBean(request).getStaffID());
			}else{
				if(!idList.contains(Integer.parseInt(id))){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "对不起，时间与其它数据有交集，不能保存。");
					return resMap;
				}
				deliveryOvertimeCnf = deliveryOvertimeCnfMapper.selectByPrimaryKey(Integer.parseInt(id));
				deliveryOvertimeCnf.setUpdateDate(new Date());
				deliveryOvertimeCnf.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			}
			deliveryOvertimeCnf.setBeginDate(sdf.parse(begin_date));
			deliveryOvertimeCnf.setEndDate(sdf.parse(end_date));
			deliveryOvertimeCnf.setTimeType(timeType);
			if("0".equals(timeType)) {
				deliveryOvertimeCnf.setOvertime(Integer.parseInt(overtime));
			}else if("1".equals(timeType)) {
				deliveryOvertimeCnf.setDeliveryDate(sdf.parse(deliveryDate));
			}
			deliveryOvertimeCnf.setRemarks(remarks);
			deliveryOvertimeCnf.setProductNoticePic(productNoticePic);
			if(deliveryOvertimeCnf.getId()!=null){
				deliveryOvertimeCnfMapper.updateByPrimaryKeySelective(deliveryOvertimeCnf);
			}else{
				deliveryOvertimeCnfMapper.insertSelective(deliveryOvertimeCnf);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 
	* @Title  发货超时统计  
	* @author yjc
	* @date 2018年1月10日11:37:09
	 */
	@RequestMapping("/deliveryOvertimeCount/list.shtml")
	public ModelAndView subOrderReportList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/orderEfficiencyData/deliveryOvertimeCountList");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String payDateEnd = sdf.format(now);
		String payDateBegin = payDateEnd.substring(0, 7)+"-01";
		m.addObject("payDateBegin", payDateBegin);
		m.addObject("payDateEnd", payDateEnd);
		
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Integer isContact = 0; //默认不是对接人
		
		PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
		platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
		if (platformContacts !=null && platformContacts.size()>0) {
			isContact=1;
			Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
			m.addObject("myContactId", myContactId);
			m.addObject("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		m.addObject("platformMchtContacts", platformMchtContact); 
		
		m.addObject("isContact", isContact);
		return m;
	}
	
	/**
	 * 
	* @Title 发货超时统计
	* @author yjc
	* @date 2018年1月10日11:36:48
	 */
	@RequestMapping("/deliveryOvertimeCount/data.shtml")
	@ResponseBody
	public Map<String, Object> deliveryOvertimeCountData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try{
			String payDateBegin = request.getParameter("payDateBegin");
			String payDateEnd = request.getParameter("payDateEnd");
			String mchtCode = request.getParameter("mchtCode");
			String shopName = request.getParameter("shopName");
			String platformContactId=request.getParameter("platformContactId");
			if(StringUtils.isEmpty(payDateBegin) || StringUtils.isEmpty(payDateEnd)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date now = new Date();
				payDateEnd = sdf.format(now);
				payDateBegin = payDateEnd.substring(0, 7)+"-01";
			}
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("payDateBegin", payDateBegin+" 00:00:00");
			paramMap.put("payDateEnd", payDateEnd+" 23:59:59");
			if(!StringUtils.isEmpty(mchtCode)){
				paramMap.put("mchtCode", mchtCode);
			}
			if(!StringUtils.isEmpty(shopName)){
				paramMap.put("shopName", shopName);
			}
			if(!StringUtils.isEmpty(platformContactId)){
				paramMap.put("platformContactId", platformContactId);
			}
			List<HashMap<String,Object>> list = subOrderService.deliveryOvertimeCount(paramMap);
			List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
			if (page.getPage() * page.getPagesize() > list.size()) {
				result = (list.subList(page.getPagesize() * (page.getPage() - 1), list.size()));
			} else {
				result = (list.subList(page.getPagesize() * (page.getPage() - 1), page.getPage() * page.getPagesize()));
			}
			resMap.put("Rows", result);
			resMap.put("Total", list.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	* @Title  超时未发货订单  
	* @author yjc
	* @date 2018年1月10日11:37:09
	 */
	@RequestMapping("/overtimeNotDelivery/list.shtml")
	public ModelAndView overtimeNotDeliveryList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/orderEfficiencyData/overtimeNotDeliveryList");
		String mchtCode = request.getParameter("mchtCode");
		String payDateBegin = request.getParameter("payDateBegin");
		String payDateEnd = request.getParameter("payDateEnd");
		if(StringUtils.isEmpty(payDateBegin) || StringUtils.isEmpty(payDateEnd)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			payDateEnd = sdf.format(now);
			payDateBegin = payDateEnd.substring(0, 7)+"-01";
		}
		ProductBrandExample example = new ProductBrandExample();
		ProductBrandExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		List<ProductBrand> productBrands = productBrandService.selectByExample(example);
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("abnormalType", "3");
		List<HashMap<String, Object>> followByList = orderAbnormalLogService.getFollowByList(paramMap);
		List<SysStatus> followStatusList = DataDicUtil.getStatusList("BU_SUB_ORDER", "SHAM_FOLLOW_STATUS");
		m.addObject("mchtCode", mchtCode);
		m.addObject("payDateBegin", payDateBegin);
		m.addObject("payDateEnd", payDateEnd);
		m.addObject("productBrands", productBrands);
		m.addObject("followByList", followByList);
		m.addObject("followStatusList", followStatusList);
		
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Integer isContact = 0; //默认不是对接人
		
		PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
		platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
		if (platformContacts !=null && platformContacts.size()>0) {
			isContact=1;
			Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
			m.addObject("myContactId", myContactId);
			m.addObject("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		m.addObject("platformMchtContacts", platformMchtContact); 
		
		m.addObject("isContact", isContact);
		return m;
	}
	
	/**
	 * 
	* @Title 超时未发货订单
	* @author yjc
	* @date 2018年1月10日11:36:48
	 */
	@RequestMapping("/overtimeNotDelivery/data.shtml")
	@ResponseBody
	public Map<String, Object> overtimeNotDeliveryData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try{
			String payDateBegin = request.getParameter("payDateBegin");
			String payDateEnd = request.getParameter("payDateEnd");
			String brandId = request.getParameter("brandId");
			String mchtCode = request.getParameter("mchtCode");
			String shopName = request.getParameter("shopName");
			String followBy = request.getParameter("followBy");
			String followStatus = request.getParameter("followStatus");
			OrderAbnormalLogCustomExample oalce = new OrderAbnormalLogCustomExample();
			OrderAbnormalLogCustomExample.OrderAbnormalLogCustomCriteria oalcc = oalce.createCriteria();
			oalcc.andDelFlagEqualTo("0");
			oalcc.andAbnormalTypeEqualTo("3");
			oalcc.andSubOrderStatusEqualTo("1");
			if(StringUtils.isEmpty(payDateBegin) || StringUtils.isEmpty(payDateEnd)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date now = new Date();
				payDateEnd = sdf.format(now);
				payDateBegin = payDateEnd.substring(0, 7)+"-01";
			}
			oalcc.andOrderPayDateGreaterThanOrEqualTo(payDateBegin+" 00:00:00");
			oalcc.andOrderPayDateLessThanOrEqualTo(payDateEnd+" 23:59:59");
			if(!StringUtils.isEmpty(brandId)){
				oalcc.andBrandIdEqualTo(Integer.parseInt(brandId));
			}
			if(!StringUtils.isEmpty(mchtCode)){
				oalcc.andMchtCodeEqualTo(mchtCode);
			}
			if(!StringUtils.isEmpty(shopName)){
				oalcc.andShopNameEqualTo(shopName);
			}
			if(!StringUtils.isEmpty(followBy)){
				oalcc.andFollowByEqualTo(Integer.parseInt(followBy));
			}
			if(!StringUtils.isEmpty(followStatus)){
				if(!followStatus.equals("1")){
					oalcc.andFollowStatusEqualTo(followStatus);
				}else{
					oalcc.andFollowStatusIsNullOrNoDeal();
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("platformContactId"))) {
				Integer platformContactId=Integer.valueOf(request.getParameter("platformContactId"));
				//我对接的商家/我协助的商家
				oalcc.andPlatformContactEqualTo(platformContactId);
			}
			oalce.setLimitStart(page.getLimitStart());
			oalce.setLimitSize(page.getLimitSize());
			int totalCount = orderAbnormalLogService.countOrderAbnormalLogCustomByExample(oalce);
			List<OrderAbnormalLogCustom> orderAbnormalLogCustoms = orderAbnormalLogService.selectOrderAbnormalLogCustomByExample(oalce);
			Collections.sort(orderAbnormalLogCustoms, new Comparator<OrderAbnormalLogCustom>() {
	            @Override
	            public int compare(OrderAbnormalLogCustom c1, OrderAbnormalLogCustom c2) {
	                //降序
	                return c2.getPayDate().compareTo(c1.getPayDate());
	            }
	        });
			resMap.put("Rows", orderAbnormalLogCustoms);
			resMap.put("Total", totalCount);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title 发货超时订单  
	 * @author yjc
	 * @date 2018年1月10日11:37:09
	 */
	@RequestMapping("/deliveryOvertime/list.shtml")
	public ModelAndView deliveryOvertimeList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/orderEfficiencyData/deliveryOvertimeList");
		String mchtCode = request.getParameter("mchtCode");
		String payDateBegin = request.getParameter("payDateBegin");
		String payDateEnd = request.getParameter("payDateEnd");
		if(StringUtils.isEmpty(payDateBegin) || StringUtils.isEmpty(payDateEnd)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			payDateEnd = sdf.format(now);
			payDateBegin = payDateEnd.substring(0, 7)+"-01";
		}
		ProductBrandExample example = new ProductBrandExample();
		ProductBrandExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		List<ProductBrand> productBrands = productBrandService.selectByExample(example);
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("abnormalType", "3");
		List<HashMap<String, Object>> followByList = orderAbnormalLogService.getFollowByList(paramMap);
		List<SysStatus> followStatusList = DataDicUtil.getStatusList("BU_SUB_ORDER", "SHAM_FOLLOW_STATUS");
		m.addObject("mchtCode", mchtCode);
		m.addObject("payDateBegin", payDateBegin);
		m.addObject("payDateEnd", payDateEnd);
		m.addObject("followByList", followByList);
		m.addObject("followStatusList", followStatusList);
		m.addObject("productBrands", productBrands);
		
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Integer isContact = 0; //默认不是对接人
		
		PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
		platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
		if (platformContacts !=null && platformContacts.size()>0) {
			isContact=1;
			Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
			m.addObject("myContactId", myContactId);
			m.addObject("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		m.addObject("platformMchtContacts", platformMchtContact); 
		
		m.addObject("isContact", isContact);
		return m;
	}
	
	/**
	 * 
	 * @Title 发货超时订单
	 * @author yjc
	 * @date 2018年1月10日11:36:48
	 */
	@RequestMapping("/deliveryOvertime/data.shtml")
	@ResponseBody
	public Map<String, Object> deliveryOvertimeData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try{
			String payDateBegin = request.getParameter("payDateBegin");
			String payDateEnd = request.getParameter("payDateEnd");
			String brandId = request.getParameter("brandId");
			String mchtCode = request.getParameter("mchtCode");
			String shopName = request.getParameter("shopName");
			String followBy = request.getParameter("followBy");
			String followStatus = request.getParameter("followStatus");
			OrderAbnormalLogCustomExample oalce = new OrderAbnormalLogCustomExample();
			OrderAbnormalLogCustomExample.OrderAbnormalLogCustomCriteria oalcc = oalce.createCriteria();
			oalcc.andDelFlagEqualTo("0");
			oalcc.andAbnormalTypeEqualTo("3");
			if(StringUtils.isEmpty(payDateBegin) || StringUtils.isEmpty(payDateEnd)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date now = new Date();
				payDateEnd = sdf.format(now);
				payDateBegin = payDateEnd.substring(0, 7)+"-01";
			}
			oalcc.andOrderPayDateGreaterThanOrEqualTo(payDateBegin+" 00:00:00");
			oalcc.andOrderPayDateLessThanOrEqualTo(payDateEnd+" 23:59:59");
			if(!StringUtils.isEmpty(brandId)){
				oalcc.andBrandIdEqualTo(Integer.parseInt(brandId));
			}
			if(!StringUtils.isEmpty(mchtCode)){
				oalcc.andMchtCodeEqualTo(mchtCode);
			}
			if(!StringUtils.isEmpty(shopName)){
				oalcc.andShopNameEqualTo(shopName);
			}
			if(!StringUtils.isEmpty(followBy)){
				oalcc.andFollowByEqualTo(Integer.parseInt(followBy));
			}
			if(!StringUtils.isEmpty(followStatus)){
				if(!followStatus.equals("1")){
					oalcc.andFollowStatusEqualTo(followStatus);
				}else{
					oalcc.andFollowStatusIsNullOrNoDeal();
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("platformContactId"))) {
				Integer platformContactId=Integer.valueOf(request.getParameter("platformContactId"));
				//我对接的商家/我协助的商家
				oalcc.andPlatformContactEqualTo(platformContactId);
			}
			oalce.setLimitStart(page.getLimitStart());
			oalce.setLimitSize(page.getLimitSize());
			int totalCount = orderAbnormalLogService.countOrderAbnormalLogCustomByExample(oalce);
			List<OrderAbnormalLogCustom> orderAbnormalLogCustoms = orderAbnormalLogService.selectOrderAbnormalLogCustomByExample(oalce);
			Collections.sort(orderAbnormalLogCustoms, new Comparator<OrderAbnormalLogCustom>() {
	            @Override
	            public int compare(OrderAbnormalLogCustom c1, OrderAbnormalLogCustom c2) {
	                //降序
	                return c2.getPayDate().compareTo(c1.getPayDate());
	            }
	        });
			resMap.put("Rows", orderAbnormalLogCustoms);
			resMap.put("Total", totalCount);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title 虚发订单统计  
	 * @author yjc
	 * @date 2018年1月10日11:37:09
	 */
	@RequestMapping("/falseOrderCount/list.shtml")
	public ModelAndView falseOrderCountList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/orderEfficiencyData/falseOrderCountList");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String payDateEnd = sdf.format(now);
		String payDateBegin = payDateEnd.substring(0, 7)+"-01";
		m.addObject("payDateBegin", payDateBegin);
		m.addObject("payDateEnd", payDateEnd);
		
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Integer isContact = 0; //默认不是对接人
		
		PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
		platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
		if (platformContacts !=null && platformContacts.size()>0) {
			isContact=1;
			Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
			m.addObject("myContactId", myContactId);
			m.addObject("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		m.addObject("platformMchtContacts", platformMchtContact); 
		
		m.addObject("isContact", isContact);
		return m;
	}
	
	/**
	 * 
	 * @Title 虚发订单统计  
	 * @author yjc
	 * @date 2018年1月10日11:36:48
	 */
	@RequestMapping("/falseOrderCount/data.shtml")
	@ResponseBody
	public Map<String, Object> falseOrderCountData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try{
			String payDateBegin = request.getParameter("payDateBegin");
			String payDateEnd = request.getParameter("payDateEnd");
			String mchtCode = request.getParameter("mchtCode");
			String shopName = request.getParameter("shopName");
			String platformContactId=request.getParameter("platformContactId");
			if(StringUtils.isEmpty(payDateBegin) || StringUtils.isEmpty(payDateEnd)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date now = new Date();
				payDateEnd = sdf.format(now);
				payDateBegin = payDateEnd.substring(0, 7)+"-01";
			}
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("payDateBegin", payDateBegin+" 00:00:00");
			paramMap.put("payDateEnd", payDateEnd+" 23:59:59");
			if(!StringUtils.isEmpty(mchtCode)){
				paramMap.put("mchtCode", mchtCode);
			}
			if(!StringUtils.isEmpty(shopName)){
				paramMap.put("shopName", shopName);
			}
			if(!StringUtils.isEmpty(platformContactId)){
				paramMap.put("platformContactId", platformContactId);
			}
			List<HashMap<String,Object>> list = subOrderService.falseOrderCount(paramMap);
			List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
			if (page.getPage() * page.getPagesize() > list.size()) {
				result = (list.subList(page.getPagesize() * (page.getPage() - 1), list.size()));
			} else {
				result = (list.subList(page.getPagesize() * (page.getPage() - 1), page.getPage() * page.getPagesize()));
			}
			resMap.put("Rows", result);
			resMap.put("Total", list.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title 虚假发货订单
	 * @author yjc
	 * @date 2018年1月10日11:37:09
	 */
	@RequestMapping("/falseOrder/list.shtml")
	public ModelAndView falseOrderList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/orderEfficiencyData/falseOrderList");
		String mchtCode = request.getParameter("mchtCode");
		String payDateBegin = request.getParameter("payDateBegin");
		String payDateEnd = request.getParameter("payDateEnd");
		if(StringUtils.isEmpty(payDateBegin) || StringUtils.isEmpty(payDateEnd)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			payDateEnd = sdf.format(now);
			payDateBegin = payDateEnd.substring(0, 7)+"-01";
		}
		ProductBrandExample example = new ProductBrandExample();
		ProductBrandExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		List<ProductBrand> productBrands = productBrandService.selectByExample(example);
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("abnormalType", "4");
		List<HashMap<String, Object>> followByList = orderAbnormalLogService.getFollowByList(paramMap);
		List<SysStatus> followStatusList = DataDicUtil.getStatusList("BU_SUB_ORDER", "SHAM_FOLLOW_STATUS");
		m.addObject("mchtCode", mchtCode);
		m.addObject("payDateBegin", payDateBegin);
		m.addObject("payDateEnd", payDateEnd);
		m.addObject("followByList", followByList);
		m.addObject("followStatusList", followStatusList);
		m.addObject("productBrands", productBrands);
		
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Integer isContact = 0; //默认不是对接人
		
		PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
		platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
		if (platformContacts !=null && platformContacts.size()>0) {
			isContact=1;
			Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
			m.addObject("myContactId", myContactId);
			m.addObject("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		m.addObject("platformMchtContacts", platformMchtContact); 
		
		m.addObject("isContact", isContact);
		return m;
	}
	
	/**
	 * 
	 * @Title 虚假发货订单
	 * @author yjc
	 * @date 2018年1月10日11:36:48
	 */
	@RequestMapping("/falseOrder/data.shtml")
	@ResponseBody
	public Map<String, Object> falseOrderData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try{
			String payDateBegin = request.getParameter("payDateBegin");
			String payDateEnd = request.getParameter("payDateEnd");
			String brandId = request.getParameter("brandId");
			String mchtCode = request.getParameter("mchtCode");
			String shopName = request.getParameter("shopName");
			String followBy = request.getParameter("followBy");
			String followStatus = request.getParameter("followStatus");
			OrderAbnormalLogCustomExample oalce = new OrderAbnormalLogCustomExample();
			OrderAbnormalLogCustomExample.OrderAbnormalLogCustomCriteria oalcc = oalce.createCriteria();
			oalcc.andDelFlagEqualTo("0");
			oalcc.andAbnormalTypeEqualTo("4");
			if(StringUtils.isEmpty(payDateBegin) || StringUtils.isEmpty(payDateEnd)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date now = new Date();
				payDateEnd = sdf.format(now);
				payDateBegin = payDateEnd.substring(0, 7)+"-01";
			}
			oalcc.andOrderPayDateGreaterThanOrEqualTo(payDateBegin+" 00:00:00");
			oalcc.andOrderPayDateLessThanOrEqualTo(payDateEnd+" 23:59:59");
			if(!StringUtils.isEmpty(brandId)){
				oalcc.andBrandIdEqualTo(Integer.parseInt(brandId));
			}
			if(!StringUtils.isEmpty(mchtCode)){
				oalcc.andMchtCodeEqualTo(mchtCode);
			}
			if(!StringUtils.isEmpty(shopName)){
				oalcc.andShopNameEqualTo(shopName);
			}
			if(!StringUtils.isEmpty(followBy)){
				oalcc.andFollowByEqualTo(Integer.parseInt(followBy));
			}
			if(!StringUtils.isEmpty(followStatus)){
				if(!followStatus.equals("1")){
					oalcc.andFollowStatusEqualTo(followStatus);
				}else{
					oalcc.andFollowStatusIsNullOrNoDeal();
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("platformContactId"))) {
				Integer platformContactId=Integer.valueOf(request.getParameter("platformContactId"));
				//我对接的商家/我协助的商家
				oalcc.andPlatformContactEqualTo(platformContactId);
			}
			oalce.setLimitStart(page.getLimitStart());
			oalce.setLimitSize(page.getLimitSize());
			int totalCount = orderAbnormalLogService.countOrderAbnormalLogCustomByExample(oalce);
			List<OrderAbnormalLogCustom> orderAbnormalLogCustoms = orderAbnormalLogService.selectOrderAbnormalLogCustomByExample(oalce);
			Collections.sort(orderAbnormalLogCustoms, new Comparator<OrderAbnormalLogCustom>() {
	            @Override
	            public int compare(OrderAbnormalLogCustom c1, OrderAbnormalLogCustom c2) {
	                //降序
	                return c2.getPayDate().compareTo(c1.getPayDate());
	            }
	        });
			resMap.put("Rows", orderAbnormalLogCustoms);
			resMap.put("Total", totalCount);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title 缺货订单统计  
	 * @author yjc
	 * @date 2018年1月10日11:37:09
	 */
	@RequestMapping("/outOfStockOrderCount/list.shtml")
	public ModelAndView outOfStockOrderCountList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/orderEfficiencyData/outOfStockOrderCountList");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String payDateEnd = sdf.format(now);
		String payDateBegin = payDateEnd.substring(0, 7)+"-01";
		m.addObject("payDateBegin", payDateBegin);
		m.addObject("payDateEnd", payDateEnd);
		
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Integer isContact = 0; //默认不是对接人
		
		PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
		platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
		if (platformContacts !=null && platformContacts.size()>0) {
			isContact=1;
			Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
			m.addObject("myContactId", myContactId);
			m.addObject("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		m.addObject("platformMchtContacts", platformMchtContact); 
		
		m.addObject("isContact", isContact);
		return m;
	}
	
	/**
	 * 
	 * @Title 缺货订单统计  
	 * @author yjc
	 * @date 2018年1月10日11:36:48
	 */
	@RequestMapping("/outOfStockOrderCount/data.shtml")
	@ResponseBody
	public Map<String, Object> outOfStockOrderCountData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try{
			String payDateBegin = request.getParameter("payDateBegin");
			String payDateEnd = request.getParameter("payDateEnd");
			String mchtCode = request.getParameter("mchtCode");
			String shopName = request.getParameter("shopName");
			String platformContactId=request.getParameter("platformContactId");
			if(StringUtils.isEmpty(payDateBegin) || StringUtils.isEmpty(payDateEnd)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date now = new Date();
				payDateEnd = sdf.format(now);
				payDateBegin = payDateEnd.substring(0, 7)+"-01";
			}
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("payDateBegin", payDateBegin+" 00:00:00");
			paramMap.put("payDateEnd", payDateEnd+" 23:59:59");
			if(!StringUtils.isEmpty(mchtCode)){
				paramMap.put("mchtCode", mchtCode);
			}
			if(!StringUtils.isEmpty(shopName)){
				paramMap.put("shopName", shopName);
			}
			if(!StringUtils.isEmpty(platformContactId)){
				paramMap.put("platformContactId", platformContactId);
			}
			List<HashMap<String,Object>> list = subOrderService.outOfStockOrderCount(paramMap);
			List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
			if (page.getPage() * page.getPagesize() > list.size()) {
				result = (list.subList(page.getPagesize() * (page.getPage() - 1), list.size()));
			} else {
				result = (list.subList(page.getPagesize() * (page.getPage() - 1), page.getPage() * page.getPagesize()));
			}
			resMap.put("Rows", result);
			resMap.put("Total", list.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title 缺货订单
	 * @author yjc
	 * @date 2018年1月10日11:37:09
	 */
	@RequestMapping("/outOfStockOrder/list.shtml")
	public ModelAndView outOfStockOrderList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/orderEfficiencyData/outOfStockOrderList");
		String mchtCode = request.getParameter("mchtCode");
		String payDateBegin = request.getParameter("payDateBegin");
		String payDateEnd = request.getParameter("payDateEnd");
		if(StringUtils.isEmpty(payDateBegin) || StringUtils.isEmpty(payDateEnd)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			payDateEnd = sdf.format(now);
			payDateBegin = payDateEnd.substring(0, 7)+"-01";
		}
		ProductBrandExample example = new ProductBrandExample();
		ProductBrandExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		List<ProductBrand> productBrands = productBrandService.selectByExample(example);
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("abnormalType", "1");
		List<HashMap<String, Object>> followByList = orderAbnormalLogService.getFollowByList(paramMap);
		List<SysStatus> followStatusList = DataDicUtil.getStatusList("BU_SUB_ORDER", "SHAM_FOLLOW_STATUS");
		m.addObject("mchtCode", mchtCode);
		m.addObject("payDateBegin", payDateBegin);
		m.addObject("payDateEnd", payDateEnd);
		m.addObject("followByList", followByList);
		m.addObject("followStatusList", followStatusList);
		m.addObject("productBrands", productBrands);
		
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Integer isContact = 0; //默认不是对接人
		
		PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
		platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
		if (platformContacts !=null && platformContacts.size()>0) {
			isContact=1;
			Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
			m.addObject("myContactId", myContactId);
			m.addObject("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		m.addObject("platformMchtContacts", platformMchtContact); 
		
		m.addObject("isContact", isContact);
		return m;
	}
	
	/**
	 * 
	 * @Title 缺货订单
	 * @author yjc
	 * @date 2018年1月10日11:36:48
	 */
	@RequestMapping("/outOfStockOrder/data.shtml")
	@ResponseBody
	public Map<String, Object> outOfStockOrderData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try{
			String payDateBegin = request.getParameter("payDateBegin");
			String payDateEnd = request.getParameter("payDateEnd");
			String brandId = request.getParameter("brandId");
			String mchtCode = request.getParameter("mchtCode");
			String shopName = request.getParameter("shopName");
			String followBy = request.getParameter("followBy");
			String followStatus = request.getParameter("followStatus");
			OrderAbnormalLogCustomExample oalce = new OrderAbnormalLogCustomExample();
			OrderAbnormalLogCustomExample.OrderAbnormalLogCustomCriteria oalcc = oalce.createCriteria();
			oalcc.andDelFlagEqualTo("0");
			oalcc.andAbnormalTypeEqualTo("1");
			if(StringUtils.isEmpty(payDateBegin) || StringUtils.isEmpty(payDateEnd)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date now = new Date();
				payDateEnd = sdf.format(now);
				payDateBegin = payDateEnd.substring(0, 7)+"-01";
			}
			oalcc.andOrderPayDateGreaterThanOrEqualTo(payDateBegin+" 00:00:00");
			oalcc.andOrderPayDateLessThanOrEqualTo(payDateEnd+" 23:59:59");
			if(!StringUtils.isEmpty(brandId)){
				oalcc.andBrandIdEqualTo(Integer.parseInt(brandId));
			}
			if(!StringUtils.isEmpty(mchtCode)){
				oalcc.andMchtCodeEqualTo(mchtCode);
			}
			if(!StringUtils.isEmpty(shopName)){
				oalcc.andShopNameEqualTo(shopName);
			}
			if(!StringUtils.isEmpty(followBy)){
				oalcc.andFollowByEqualTo(Integer.parseInt(followBy));
			}
			if(!StringUtils.isEmpty(followStatus)){
				if(!followStatus.equals("1")){
					oalcc.andFollowStatusEqualTo(followStatus);
				}else{
					oalcc.andFollowStatusIsNullOrNoDeal();
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("platformContactId"))) {
				Integer platformContactId=Integer.valueOf(request.getParameter("platformContactId"));
				//我对接的商家/我协助的商家
				oalcc.andPlatformContactEqualTo(platformContactId);
			}
			oalce.setLimitStart(page.getLimitStart());
			oalce.setLimitSize(page.getLimitSize());
			int totalCount = orderAbnormalLogService.countOrderAbnormalLogCustomByExample(oalce);
			List<OrderAbnormalLogCustom> orderAbnormalLogCustoms = orderAbnormalLogService.selectOrderAbnormalLogCustomByExample(oalce);
			Collections.sort(orderAbnormalLogCustoms, new Comparator<OrderAbnormalLogCustom>() {
	            @Override
	            public int compare(OrderAbnormalLogCustom c1, OrderAbnormalLogCustom c2) {
	                //降序
	                return c2.getPayDate().compareTo(c1.getPayDate());
	            }
	        });
			resMap.put("Rows", orderAbnormalLogCustoms);
			resMap.put("Total", totalCount);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 缺货--人工发起违规
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/outOfStockOrder/addManuallyViolateOrder.shtml")
	public ModelAndView addManuallyViolateOrder(HttpServletRequest request) {
		String rtPage = "/violateOrder/editManuallyViolateOrder";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("subOrderCode", request.getParameter("subOrderCode"));
		resMap.put("mchtCode", request.getParameter("mchtCode"));
		resMap.put("violateTypeList", DataDicUtil.getStatusList("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_TYPE"));
		SysStatusExample example = new SysStatusExample();
		SysStatusExample.Criteria criteria = example.createCriteria();
		criteria.andTableNameEqualTo("BU_VIOLATE_PUNISH_STANDARD");
		criteria.andColNameEqualTo("VIOLATE_ACTION");
		criteria.andStatusValueLike("%D%");
		List<SysStatus> sysStatusList = sysStatusService.selectByExample(example);
		resMap.put("violateActions", sysStatusList);
		resMap.put("defaultViolateType", "D");
		resMap.put("defaultViolateAction", "D4");
		ViolatePunishStandardExample vpse = new ViolatePunishStandardExample();
		ViolatePunishStandardExample.Criteria vpsec = vpse.createCriteria();
		vpsec.andDelFlagEqualTo("0");
		vpsec.andViolateTypeEqualTo("D");
		vpsec.andViolateActionEqualTo("D4");
		List<ViolatePunishStandard> violatePunishStandards = violatePunishStandardService.selectByExample(vpse);
		resMap.put("violatePunishStandards", violatePunishStandards);
		ViolatePunishStandard violatePunishStandard = violatePunishStandards.get(0);
		resMap.put("defaultContent", violatePunishStandard.getContent());
		ViolateOrder violateOrder = new ViolateOrder();
		violateOrder.setContent("商家缺货");
		String regEx="[^0-9]";  
		Pattern p = Pattern.compile(regEx);  
		Matcher m = p.matcher(violatePunishStandard.getPunishStandard());  
		violateOrder.setMoney(new BigDecimal(m.replaceAll("").trim()));
		violateOrder.setJifenIntegral(violatePunishStandard.getJifenIntegral());
		violateOrder.setPunishMeans(violatePunishStandard.getPunishOther());
		violateOrder.setStatus(violatePunishStandard.getComplainFlag());
		violateOrder.setEnableHours(violatePunishStandard.getEnableHours());
		resMap.put("violateOrder", JSONObject.fromObject(violateOrder));
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 
	 * @Title 其他异常订单统计  
	 * @author yjc
	 * @date 2018年1月10日11:37:09
	 */
	@RequestMapping("/otherExceptionOrderCount/list.shtml")
	public ModelAndView otherExceptionOrderCountList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/orderEfficiencyData/otherExceptionOrderCountList");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String payDateEnd = sdf.format(now);
		String payDateBegin = payDateEnd.substring(0, 7)+"-01";
		m.addObject("payDateBegin", payDateBegin);
		m.addObject("payDateEnd", payDateEnd);
		
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Integer isContact = 0; //默认不是对接人
		
		PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
		platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
		if (platformContacts !=null && platformContacts.size()>0) {
			isContact=1;
			Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
			m.addObject("myContactId", myContactId);
			m.addObject("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		m.addObject("platformMchtContacts", platformMchtContact); 
		
		m.addObject("isContact", isContact);
		return m;
	}
	
	/**
	 * 
	 * @Title 其他异常订单统计  
	 * @author yjc
	 * @date 2018年1月10日11:36:48
	 */
	@RequestMapping("/otherExceptionOrderCount/data.shtml")
	@ResponseBody
	public Map<String, Object> otherExceptionOrderCountData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try{
			String payDateBegin = request.getParameter("payDateBegin");
			String payDateEnd = request.getParameter("payDateEnd");
			String mchtCode = request.getParameter("mchtCode");
			String shopName = request.getParameter("shopName");
			String platformContactId=request.getParameter("platformContactId");
			if(StringUtils.isEmpty(payDateBegin) || StringUtils.isEmpty(payDateEnd)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date now = new Date();
				payDateEnd = sdf.format(now);
				payDateBegin = payDateEnd.substring(0, 7)+"-01";
			}
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("payDateBegin", payDateBegin+" 00:00:00");
			paramMap.put("payDateEnd", payDateEnd+" 23:59:59");
			if(!StringUtils.isEmpty(mchtCode)){
				paramMap.put("mchtCode", mchtCode);
			}
			if(!StringUtils.isEmpty(shopName)){
				paramMap.put("shopName", shopName);
			}
			if(!StringUtils.isEmpty(platformContactId)){
				paramMap.put("platformContactId", platformContactId);
			}
			List<HashMap<String,Object>> list = subOrderService.otherExceptionOrderCount(paramMap);
			List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
			if (page.getPage() * page.getPagesize() > list.size()) {
				result = (list.subList(page.getPagesize() * (page.getPage() - 1), list.size()));
			} else {
				result = (list.subList(page.getPagesize() * (page.getPage() - 1), page.getPage() * page.getPagesize()));
			}
			resMap.put("Rows", result);
			resMap.put("Total", list.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title 其他异常订单
	 * @author yjc
	 * @date 2018年1月10日11:37:09
	 */
	@RequestMapping("/otherExceptionOrder/list.shtml")
	public ModelAndView otherExceptionOrderList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/orderEfficiencyData/otherExceptionOrderList");
		String mchtCode = request.getParameter("mchtCode");
		String payDateBegin = request.getParameter("payDateBegin");
		String payDateEnd = request.getParameter("payDateEnd");
		if(StringUtils.isEmpty(payDateBegin) || StringUtils.isEmpty(payDateEnd)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			payDateEnd = sdf.format(now);
			payDateBegin = payDateEnd.substring(0, 7)+"-01";
		}
		ProductBrandExample example = new ProductBrandExample();
		ProductBrandExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		List<ProductBrand> productBrands = productBrandService.selectByExample(example);
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("abnormalType", "2");
		List<HashMap<String, Object>> followByList = orderAbnormalLogService.getFollowByList(paramMap);
		List<SysStatus> followStatusList = DataDicUtil.getStatusList("BU_SUB_ORDER", "SHAM_FOLLOW_STATUS");
		m.addObject("mchtCode", mchtCode);
		m.addObject("payDateBegin", payDateBegin);
		m.addObject("payDateEnd", payDateEnd);
		m.addObject("followByList", followByList);
		m.addObject("followStatusList", followStatusList);
		m.addObject("productBrands", productBrands);
		
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Integer isContact = 0; //默认不是对接人
		
		PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
		platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
		if (platformContacts !=null && platformContacts.size()>0) {
			isContact=1;
			Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
			m.addObject("myContactId", myContactId);
			m.addObject("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		m.addObject("platformMchtContacts", platformMchtContact); 
		
		m.addObject("isContact", isContact);
		return m;
	}
	
	/**
	 * 
	 * @Title 其他异常订单
	 * @author yjc
	 * @date 2018年1月10日11:36:48
	 */
	@RequestMapping("/otherExceptionOrder/data.shtml")
	@ResponseBody
	public Map<String, Object> otherExceptionOrderData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try{
			String payDateBegin = request.getParameter("payDateBegin");
			String payDateEnd = request.getParameter("payDateEnd");
			String brandId = request.getParameter("brandId");
			String mchtCode = request.getParameter("mchtCode");
			String shopName = request.getParameter("shopName");
			String followBy = request.getParameter("followBy");
			String followStatus = request.getParameter("followStatus");
			OrderAbnormalLogCustomExample oalce = new OrderAbnormalLogCustomExample();
			OrderAbnormalLogCustomExample.OrderAbnormalLogCustomCriteria oalcc = oalce.createCriteria();
			oalcc.andDelFlagEqualTo("0");
			oalcc.andAbnormalTypeEqualTo("2");
			if(StringUtils.isEmpty(payDateBegin) || StringUtils.isEmpty(payDateEnd)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date now = new Date();
				payDateEnd = sdf.format(now);
				payDateBegin = payDateEnd.substring(0, 7)+"-01";
			}
			oalcc.andOrderPayDateGreaterThanOrEqualTo(payDateBegin+" 00:00:00");
			oalcc.andOrderPayDateLessThanOrEqualTo(payDateEnd+" 23:59:59");
			if(!StringUtils.isEmpty(brandId)){
				oalcc.andBrandIdEqualTo(Integer.parseInt(brandId));
			}
			if(!StringUtils.isEmpty(mchtCode)){
				oalcc.andMchtCodeEqualTo(mchtCode);
			}
			if(!StringUtils.isEmpty(shopName)){
				oalcc.andShopNameEqualTo(shopName);
			}
			if(!StringUtils.isEmpty(followBy)){
				oalcc.andFollowByEqualTo(Integer.parseInt(followBy));
			}
			if(!StringUtils.isEmpty(followStatus)){
				if(!followStatus.equals("1")){
					oalcc.andFollowStatusEqualTo(followStatus);
				}else{
					oalcc.andFollowStatusIsNullOrNoDeal();
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("platformContactId"))) {
				Integer platformContactId=Integer.valueOf(request.getParameter("platformContactId"));
				//我对接的商家/我协助的商家
				oalcc.andPlatformContactEqualTo(platformContactId);
			}
			oalce.setLimitStart(page.getLimitStart());
			oalce.setLimitSize(page.getLimitSize());
			int totalCount = orderAbnormalLogService.countOrderAbnormalLogCustomByExample(oalce);
			List<OrderAbnormalLogCustom> orderAbnormalLogCustoms = orderAbnormalLogService.selectOrderAbnormalLogCustomByExample(oalce);
			Collections.sort(orderAbnormalLogCustoms, new Comparator<OrderAbnormalLogCustom>() {
	            @Override
	            public int compare(OrderAbnormalLogCustom c1, OrderAbnormalLogCustom c2) {
	                //降序
	                return c2.getPayDate().compareTo(c1.getPayDate());
	            }
	        });
			resMap.put("Rows", orderAbnormalLogCustoms);
			resMap.put("Total", totalCount);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 其他异常--人工发起违规
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/otherExceptionOrder/addOtherExceptionViolateOrder.shtml")
	public ModelAndView addOtherExceptionViolateOrder(HttpServletRequest request) {
		String rtPage = "/violateOrder/editManuallyViolateOrder";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("subOrderCode", request.getParameter("subOrderCode"));
		resMap.put("mchtCode", request.getParameter("mchtCode"));
		resMap.put("violateTypeList", DataDicUtil.getStatusList("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_TYPE"));
		SysStatusExample example = new SysStatusExample();
		SysStatusExample.Criteria criteria = example.createCriteria();
		criteria.andTableNameEqualTo("BU_VIOLATE_PUNISH_STANDARD");
		criteria.andColNameEqualTo("VIOLATE_ACTION");
		criteria.andStatusValueLike("%D%");
		List<SysStatus> sysStatusList = sysStatusService.selectByExample(example);
		resMap.put("violateActions", sysStatusList);
		return new ModelAndView(rtPage, resMap);
	}
}
