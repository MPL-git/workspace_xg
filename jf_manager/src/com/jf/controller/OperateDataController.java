package com.jf.controller;
import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtils;
import com.jf.common.utils.Week;
import com.jf.common.utils.WeekHelper;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoExample;
import com.jf.entity.OrderDtlCustom;
import com.jf.entity.OrderDtlCustomExample;
import com.jf.entity.PlatformContact;
import com.jf.entity.PlatformContactExample;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductBrandExample;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.SysStaffInfoCustom;
import com.jf.entity.SysStaffInfoCustomExample;
import com.jf.entity.SysStaffProductType;
import com.jf.entity.SysStaffProductTypeExample;
import com.jf.service.CombineOrderService;
import com.jf.service.CommonService;
import com.jf.service.MchtInfoService;
import com.jf.service.MchtPlatformContactService;
import com.jf.service.MemberInfoService;
import com.jf.service.OperateDataService;
import com.jf.service.OrderDtlService;
import com.jf.service.PlatformContactService;
import com.jf.service.ProductBrandService;
import com.jf.service.ProductTypeService;
import com.jf.service.SubOrderReportService;
import com.jf.service.SysStaffInfoService;
import com.jf.service.SysStaffProductTypeService;
import com.jf.vo.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Controller
@RequestMapping("/operateData")
public class OperateDataController extends BaseController {
	
	@Resource
	private OrderDtlService orderDtlService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private CombineOrderService combineOrderService;
	
	@Resource
	private MemberInfoService memberInfoService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private ProductBrandService productBrandService;
	
	@Autowired
	private SubOrderReportService subOrderReportService;

	@Autowired
	private MchtPlatformContactService mchtPlatformContactService;
	
	@Resource
	private PlatformContactService platformContactService;
	
	@Resource
	private SysStaffProductTypeService sysStaffProductTypeService;
	
	@Autowired
	private OperateDataService operateDataService;
	
	@Resource
	private SysStaffInfoService sysStaffInfoService;
	
	@Resource
	private CommonService commonService;

	private static final long serialVersionUID = 1L;
	
	/**
	 * 订单排行
	* @Title    
	* @author yjc
	* @date 2018年1月10日11:37:09
	 */
	@RequestMapping("/subOrderReport/list.shtml")
	public ModelAndView subOrderReportList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/operateData/subOrderReportList");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM");
		String startDateStr = df2.format(new Date())+"-01 00:00:00";	// 起止时间精确到秒 modify by huangdl 2019-07-16
		String endDateStr = df.format(new Date())+" 23:59:59";
		if(StringUtils.isEmpty(request.getParameter("startCreateDate"))){
			m.addObject("pay_date_begin", startDateStr);
		}else{
			m.addObject("pay_date_begin", request.getParameter("startCreateDate"));
		}
		if(StringUtils.isEmpty(request.getParameter("endCreateDate"))){
			m.addObject("pay_date_end", endDateStr);
		}else{
			m.addObject("pay_date_end", request.getParameter("endCreateDate"));
		}
		String rowType = request.getParameter("rowType");
		if(StringUtils.isEmpty(rowType)){
			rowType = "product";
		}
		m.addObject("rowType", rowType);
		m.addObject("mchtId", request.getParameter("mchtId"));
		m.addObject("brandId", request.getParameter("brandId"));
		m.addObject("productTypeId", request.getParameter("productTypeId"));
		m.addObject("pageSize", request.getParameter("pageSize"));
		m.addObject("orderType", request.getParameter("orderType"));
		m.addObject("activityType", request.getParameter("activityType"));
		m.addObject("selectedProductTypeId", request.getParameter("selectedProductTypeId"));
		ProductTypeExample example = new ProductTypeExample();
		ProductTypeExample.Criteria ProductTypeCriteria = example.createCriteria();
		ProductTypeCriteria.andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		
		//钟表运营部状态，只获取主营类目为钟表 
		String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
				ProductTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
			}
		}
		m.addObject("isCwOrgStatus", isCwOrgStatus);
		
		List<ProductType> productTypes = productTypeService.selectByExample(example);
		m.addObject("productTypes", productTypes);
		
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
		
		ResourceBundle resource = ResourceBundle.getBundle("base_config");
		String mUrl = resource.getString("mUrl");
		m.addObject("mchtUrl", mUrl+"/xgbuy/views/index.html?redirect_url=seller/index.html?mchtId=");
		return m;
	}
	
	/**
	 * 订单排行
	* @Title 
	* @author yjc
	* @date 2018年1月10日11:36:48
	 */
	@ResponseBody
	@RequestMapping("/subOrderReport/data.shtml")
	public Map<String, Object> subOrderReportData(HttpServletRequest request, String rowType, 
			String pay_date_begin, String pay_date_end, Integer pageSize, String orderType) {
		if(StringUtils.isBlank(rowType)) {
			rowType = "brand"; //默认按品牌
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isBlank(pay_date_begin)) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			pay_date_begin = sdf.format(calendar.getTime()); //默认本月1日
		}
		if(StringUtils.isBlank(pay_date_end)) {
			pay_date_end = sdf.format(new Date()); //默认今天
		}
		if(pageSize == null && pageSize.equals(new Integer(0))) {
			pageSize = 20; //默认20条
		}
		if(StringUtils.isBlank(orderType)) {
			orderType = "num"; //默认数量
		}
		String activityType = request.getParameter("activityType");
		String mchtId = request.getParameter("mchtId");
		String brandId = request.getParameter("brandId");
		String productTypeId = request.getParameter("productTypeId");
		String name = request.getParameter("name");
		String selectedProductTypeId = request.getParameter("selectedProductTypeId");
		String platformContactId=request.getParameter("platformContactId");
		String code=request.getParameter("code");
		Map<String, Object> map = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(activityType)){
			map.put("activityType", activityType);
		}
		if(!StringUtils.isEmpty(mchtId) && !mchtId.equals("0")){
			map.put("mchtId", Integer.parseInt(mchtId));
		}
		if(!StringUtils.isEmpty(brandId) && !brandId.equals("0")){
			map.put("brandId", Integer.parseInt(brandId));
		}
		if(!StringUtils.isEmpty(productTypeId) && !productTypeId.equals("0")){
			map.put("productTypeId", Integer.parseInt(productTypeId));
		}
		if(!StringUtils.isEmpty(name)){
			map.put("name", name);
		}
		/*List<Integer> mchtIds = mchtPlatformContactService.getMchtIdsByStaffId(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		if(mchtIds!=null && mchtIds.size()>0){
			map.put("mchtIds",mchtIds);
		}*/
		
		//钟表运营部状态，只获取主营类目为钟表 
		String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
				map.put("productTypeId", Integer.parseInt(isCwOrgProductTypeId));
			}
		}else {
			if(!StringUtils.isEmpty(selectedProductTypeId) && !selectedProductTypeId.equals("0")){
				map.put("selectedProductTypeId", Integer.parseInt(selectedProductTypeId));
			}
		}
		if (!StringUtil.isEmpty(platformContactId)) {
			map.put("platformContactId", platformContactId);
		}
		if (!StringUtil.isEmpty(code)) {
			map.put("code", code);
		}
		map.put("row_type", rowType);
		map.put("startPayDate", pay_date_begin);
		map.put("endPayDate", pay_date_end);
		map.put("pageSize", pageSize);
		map.put("orderType", orderType);
		map.put("listSpecMchtCode", commonService.listSpecMchtCode());
		List<Map<String, Object>> listMap = subOrderReportService.selectSubOrder(map);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("Rows", listMap);
		resultMap.put("Total", listMap.size());
		return resultMap;
	}


	/**
	 * 自营商家统计页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/manageSelfStatistics/list.shtml")
	public ModelAndView manageSelfStatisticsList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/operateData/manageSelfStatisticsList");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM");
		String startDateStr = df2.format(new Date())+"-01 00:00:00";	// 起止时间精确到秒
		String endDateStr = df.format(new Date())+" 23:59:59";
		if(StringUtils.isEmpty(request.getParameter("startCreateDate"))){
			m.addObject("pay_date_begin", startDateStr);
		}else{
			m.addObject("pay_date_begin", request.getParameter("startCreateDate"));
		}
		if(StringUtils.isEmpty(request.getParameter("endCreateDate"))){
			m.addObject("pay_date_end", endDateStr);
		}else{
			m.addObject("pay_date_end", request.getParameter("endCreateDate"));
		}
		String rowType = request.getParameter("rowType");
		if(StringUtils.isEmpty(rowType)){
			rowType = "mcht";
		}
		m.addObject("rowType", rowType);
		m.addObject("mchtId", request.getParameter("mchtId"));
		m.addObject("brandId", request.getParameter("brandId"));
		m.addObject("productTypeId", request.getParameter("productTypeId"));
		m.addObject("pageSize", request.getParameter("pageSize"));
		m.addObject("orderType", request.getParameter("orderType"));
		m.addObject("activityType", request.getParameter("activityType"));
		m.addObject("selectedProductTypeId", request.getParameter("selectedProductTypeId"));
		ProductTypeExample example = new ProductTypeExample();
		ProductTypeExample.Criteria ProductTypeCriteria = example.createCriteria();
		ProductTypeCriteria.andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");

		//钟表运营部状态，只获取主营类目为钟表
		String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
				ProductTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
			}
		}
		m.addObject("isCwOrgStatus", isCwOrgStatus);

		List<ProductType> productTypes = productTypeService.selectByExample(example);
		m.addObject("productTypes", productTypes);

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

		ResourceBundle resource = ResourceBundle.getBundle("base_config");
		String mUrl = resource.getString("mUrl");
		m.addObject("mchtUrl", mUrl+"/xgbuy/views/index.html?redirect_url=seller/index.html?mchtId=");
		return m;
	}



	/**
	 * 自营商家统计数据
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/manageSelfStatistics/data.shtml")
	public Map<String, Object> manageSelfStatisticsData(HttpServletRequest request, String rowType, String pay_date_begin, String pay_date_end, Integer pageSize, String orderType) {
		if(StringUtils.isBlank(rowType)) {
			rowType = "mcht"; //默认按品牌
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isBlank(pay_date_begin)) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			pay_date_begin = sdf.format(calendar.getTime()); //默认本月1日
		}
		if(StringUtils.isBlank(pay_date_end)) {
			pay_date_end = sdf.format(new Date()); //默认今天
		}
		if(pageSize == null && pageSize.equals(new Integer(0))) {
			pageSize = 20; //默认20条
		}
		if(StringUtils.isBlank(orderType)) {
			orderType = "num"; //默认销售数量
		}
		String activityType = request.getParameter("activityType");
		String mchtId = request.getParameter("mchtId");
		String brandId = request.getParameter("brandId");
		String productTypeId = request.getParameter("productTypeId");
		String name = request.getParameter("name");
		String selectedProductTypeId = request.getParameter("selectedProductTypeId");
		String platformContactId=request.getParameter("platformContactId");
		String code=request.getParameter("code");
		Map<String, Object> map = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(activityType)){
			map.put("activityType", activityType);
		}
		if(!StringUtils.isEmpty(mchtId) && !mchtId.equals("0")){
			map.put("mchtId", Integer.parseInt(mchtId));
		}
		if(!StringUtils.isEmpty(brandId) && !brandId.equals("0")){
			map.put("brandId", Integer.parseInt(brandId));
		}
		if(!StringUtils.isEmpty(productTypeId) && !productTypeId.equals("0")){
			map.put("productTypeId", Integer.parseInt(productTypeId));
		}
		if(!StringUtils.isEmpty(name)){
			map.put("name", name);
		}

		//钟表运营部状态，只获取主营类目为钟表
		String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
				map.put("productTypeId", Integer.parseInt(isCwOrgProductTypeId));
			}
		}else {
			if(!StringUtils.isEmpty(selectedProductTypeId) && !selectedProductTypeId.equals("0")){
				map.put("selectedProductTypeId", Integer.parseInt(selectedProductTypeId));
			}
		}
		if (!StringUtil.isEmpty(platformContactId)) {
			map.put("platformContactId", platformContactId);
		}
		if (!StringUtil.isEmpty(code)) {
			map.put("code", code);
		}
		map.put("row_type", rowType);
		map.put("startPayDate", pay_date_begin);
		map.put("endPayDate", pay_date_end);
		map.put("pageSize", pageSize);
		map.put("orderType", orderType);
//		map.put("listSpecMchtCode", commonService.listSpecMchtCode());
		List<Map<String, Object>> listMap = subOrderReportService.ManageSelfSubOrder(map);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("Rows", listMap);
		resultMap.put("Total", listMap.size());
		return resultMap;
	}


	
	/**
	 * 日报表列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/dayReport/list.shtml")
	public ModelAndView list(HttpServletRequest request) {
		String rtPage = "/dataCenter/operateData/dayReportList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = df.format(new Date());
		resMap.put("pay_date_end", dateStr);
		resMap.put("pay_date_begin", dateStr);
		resMap.put("status", "1");
		ProductTypeExample example = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = example.createCriteria();
		productTypeCriteria.andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		
		//钟表运营部状态，只获取主营类目为钟表 
		String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
				productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
			}
		}
		resMap.put("isCwOrgStatus", isCwOrgStatus);
		
		List<ProductType> productTypes = productTypeService.selectByExample(example);
		resMap.put("productTypes", productTypes);
		
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
			resMap.put("myContactId", myContactId);
			resMap.put("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		resMap.put("platformMchtContacts", platformMchtContact); 
		
		resMap.put("isContact", isContact);
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 日报表列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/dayReport/data.shtml")
	@ResponseBody
	public Map<String, Object> data(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String payDateBegin = request.getParameter("pay_date_begin");
			String payDateEnd = request.getParameter("pay_date_end");
			String mchtCode = request.getParameter("mchtCode");
			String status = request.getParameter("status");
			String productTypeId = request.getParameter("productTypeId");
			String platformContactId=request.getParameter("platformContactId");
			String activityType = request.getParameter("activityType");
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			if(!StringUtil.isEmpty(payDateBegin)){
				paramMap.put("payDateBegin",payDateBegin+" 00:00:00");
			}else{
				payDateEnd = df.format(new Date());
				payDateBegin = payDateEnd.substring(0,7)+"-01 00:00:00";
				paramMap.put("payDateBegin",payDateBegin);
			}
			if(!StringUtil.isEmpty(payDateEnd)){
				paramMap.put("payDateEnd",payDateEnd+" 23:59:59");
			}else{
				payDateEnd = df.format(new Date());
				paramMap.put("payDateEnd",payDateEnd);
			}
			if(!StringUtil.isEmpty(mchtCode)){
				paramMap.put("mchtCode",mchtCode);
			}
			if(!StringUtil.isEmpty(status)){
				paramMap.put("status",status);
			}
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					paramMap.put("productTypeId", Integer.parseInt(isCwOrgProductTypeId));
				}
			}else {
				if(!StringUtil.isEmpty(productTypeId)){
					paramMap.put("productTypeId",Integer.parseInt(productTypeId));
				}
			}
			if(!StringUtil.isEmpty(platformContactId)){
				paramMap.put("platformContactId",platformContactId);
			}
			if(!StringUtil.isEmpty(activityType)){
				paramMap.put("activityType",activityType);
			}
			paramMap.put("listSpecMchtCode",commonService.listSpecMchtCode());
			/*List<Integer> mchtIds = mchtPlatformContactService.getMchtIdsByStaffId(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			if(mchtIds!=null && mchtIds.size()>0){
				paramMap.put("mchtIds",mchtIds);
			}*/
			List<OrderDtlCustom> orderDtlCustoms = orderDtlService.dayReport(paramMap);
			HashMap<String, OrderDtlCustom> map = new HashMap<String, OrderDtlCustom>();
			List<String> containDays = new ArrayList<String>();
			for(OrderDtlCustom orderDtlCustom:orderDtlCustoms){
				containDays.add(orderDtlCustom.getEachDay());
				map.put(orderDtlCustom.getEachDay(), orderDtlCustom);
			}
			List<String> betweenDays = this.getBetweenDays(payDateBegin, payDateEnd);
			for(int i= 0;i<betweenDays.size();i++){
				if(!containDays.contains(betweenDays.get(i))){
					OrderDtlCustom orderDtlCustom = new OrderDtlCustom();
					orderDtlCustom.setEachDay(betweenDays.get(i));
					orderDtlCustom.setBuyUserCount(0);
					orderDtlCustom.setOrderCount(0);
					orderDtlCustom.setProductCount(0);
					orderDtlCustom.setProductSalePrice(new BigDecimal(0));
					orderDtlCustom.setTotalMchtPreferential(new BigDecimal(0));
					orderDtlCustom.setTotalPlatformPreferential(new BigDecimal(0));
					orderDtlCustom.setTotalPayAmount(new BigDecimal(0));
					orderDtlCustom.setTotalSettleAmount(new BigDecimal(0));
					orderDtlCustom.setUnitPrice(new BigDecimal(0));
					orderDtlCustom.setMchtDiscountRate(new BigDecimal(0));
					orderDtlCustom.setPlatDiscountRate(new BigDecimal(0));
					orderDtlCustom.setGrossProfitRate(new BigDecimal(0));
					orderDtlCustoms.add(orderDtlCustom);
					map.put(betweenDays.get(i), orderDtlCustom);
				}
			}
			Collections.sort(orderDtlCustoms, new Comparator<OrderDtlCustom>() {
	            @Override
	            public int compare(OrderDtlCustom c1, OrderDtlCustom c2) {
	                //升序
	                return c1.getEachDay().compareTo(c2.getEachDay());
	            }
	        });
			resMap.put("Rows", orderDtlCustoms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	public List<String> getBetweenDays(String stime,String etime){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date sdate=null;
        Date eDate=null;
        try {
             sdate=df.parse(stime);
             eDate=df.parse(etime);
        } catch (Exception e) {
              e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        List<String> list=new ArrayList<String>();
        while (sdate.getTime()<=eDate.getTime()) {
              list.add(df.format(sdate));
              c.setTime(sdate);
              c.add(Calendar.DATE, 1); // 日期加1天
              sdate = c.getTime();
              }
        return list;
  }
	
	/**
	 * 周报表列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/weekReport/list.shtml")
	public ModelAndView weekReportList(HttpServletRequest request) {
		String rtPage = "/dataCenter/operateData/weekReportList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateEnd = df.format(new Date());
		resMap.put("pay_date_end", dateEnd);
		resMap.put("status", "1");
		resMap.put("week", "5");
		ProductTypeExample example = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = example.createCriteria();
		productTypeCriteria.andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		
		//钟表运营部状态，只获取主营类目为钟表 
		String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
				productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
			}
		}
		resMap.put("isCwOrgStatus", isCwOrgStatus);
		
		List<ProductType> productTypes = productTypeService.selectByExample(example);
		resMap.put("productTypes", productTypes);
		
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
			resMap.put("myContactId", myContactId);
			resMap.put("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		resMap.put("platformMchtContacts", platformMchtContact); 
		
		resMap.put("isContact", isContact);
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 周报表列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	//@RequestMapping(value = "/weekReport/data.shtml")
	@ResponseBody
	public Map<String, Object> weekReportData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String payDateEnd = request.getParameter("pay_date_end");
			String mchtCode = request.getParameter("mchtCode");
			String status = request.getParameter("status");
			String week = request.getParameter("week");
			String productTypeId = request.getParameter("productTypeId");
			String platformContactId=request.getParameter("platformContactId");
			String activityType = request.getParameter("activityType");
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			if(!StringUtil.isEmpty(payDateEnd)){
				paramMap.put("payDateEnd",payDateEnd+" 23:59:59");
			}else{
				payDateEnd = df.format(new Date());
				paramMap.put("payDateEnd",payDateEnd+" 23:59:59");
			}
			Date payDateBegin = DateUtil.getDateAfter(df.parse(payDateEnd), -Integer.parseInt(week)*7+1);
			paramMap.put("payDateBegin",df.format(payDateBegin)+" 00:00:00");
			if(!StringUtil.isEmpty(mchtCode)){
				paramMap.put("mchtCode",mchtCode);
			}
			if(!StringUtil.isEmpty(status)){
				paramMap.put("status",status);
			}
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					paramMap.put("productTypeId",Integer.parseInt(isCwOrgProductTypeId));
				}
			}else {
				if(!StringUtil.isEmpty(productTypeId)){
					paramMap.put("productTypeId",Integer.parseInt(productTypeId));
				}
			}
			if (!StringUtil.isEmpty(platformContactId)) {
				paramMap.put("platformContactId", platformContactId);
			}	
			if(!StringUtil.isEmpty(activityType)){
				paramMap.put("activityType",activityType);
			}
			
			/*List<Integer> mchtIds = mchtPlatformContactService.getMchtIdsByStaffId(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			if(mchtIds!=null && mchtIds.size()>0){
				paramMap.put("mchtIds",mchtIds);
			}*/
			List<OrderDtlCustom> orderDtlCustoms = orderDtlService.dayReport(paramMap);
			HashMap<String, OrderDtlCustom> map = new HashMap<String, OrderDtlCustom>();
			List<String> containDays = new ArrayList<String>();
			for(OrderDtlCustom orderDtlCustom:orderDtlCustoms){
				containDays.add(orderDtlCustom.getEachDay());
				map.put(orderDtlCustom.getEachDay(), orderDtlCustom);
			}
			List<String> betweenDays = this.getBetweenDays(df.format(payDateBegin), payDateEnd);
			for(int i= 0;i<betweenDays.size();i++){
				if(!containDays.contains(betweenDays.get(i))){
					OrderDtlCustom orderDtlCustom = new OrderDtlCustom();
					orderDtlCustom.setEachDay(betweenDays.get(i));
					orderDtlCustom.setBuyUserCount(0);
					orderDtlCustom.setOrderCount(0);
					orderDtlCustom.setProductCount(0);
					orderDtlCustom.setProductSalePrice(new BigDecimal(0));
					orderDtlCustom.setTotalMchtPreferential(new BigDecimal(0));
					orderDtlCustom.setTotalPlatformPreferential(new BigDecimal(0));
					orderDtlCustom.setTotalPayAmount(new BigDecimal(0));
					orderDtlCustom.setTotalSettleAmount(new BigDecimal(0));
					orderDtlCustom.setUnitPrice(new BigDecimal(0));
					orderDtlCustom.setMchtDiscountRate(new BigDecimal(0));
					orderDtlCustom.setPlatDiscountRate(new BigDecimal(0));
					orderDtlCustom.setGrossProfitRate(new BigDecimal(0));
					orderDtlCustoms.add(orderDtlCustom);
					map.put(betweenDays.get(i), orderDtlCustom);
				}
			}
			Collections.sort(orderDtlCustoms, new Comparator<OrderDtlCustom>() {
	            @Override
	            public int compare(OrderDtlCustom c1, OrderDtlCustom c2) {
	                //升序
	                return c1.getEachDay().compareTo(c2.getEachDay());
	            }
	        });
			List<Week> weeks = WeekHelper.getWeekSplit(payDateBegin, df.parse(payDateEnd));
			List<OrderDtlCustom> orderDtlCustomList = new ArrayList<OrderDtlCustom>();
			for(int i=weeks.size()-1;i>=0;i--){
				OrderDtlCustom odc = new OrderDtlCustom();
				odc.setEachWeek(weeks.get(i).getWeekBegin()+"至"+weeks.get(i).getWeekEnd());
				int totalBuyUserCount = 0;
				int totalOrderCount = 0;
				int totalProductCount = 0;
				BigDecimal totalProductSalePrice = new BigDecimal(0);
				BigDecimal totalMchtPreferential = new BigDecimal(0);
				BigDecimal totalPlatformPreferential = new BigDecimal(0);
				BigDecimal totalPayAmount = new BigDecimal(0);
				BigDecimal totalSettleAmount = new BigDecimal(0);
				BigDecimal totalMchtDiscountRate = new BigDecimal(0);
				BigDecimal totalPlatDiscountRate = new BigDecimal(0);
				BigDecimal totalGrossProfitRate = new BigDecimal(0);
				for(OrderDtlCustom orderDtlCustom:orderDtlCustoms){
					if(df.parse(orderDtlCustom.getEachDay()).getTime()>=df.parse(weeks.get(i).getWeekBegin()).getTime() && df.parse(orderDtlCustom.getEachDay()).getTime()<=df.parse(weeks.get(i).getWeekEnd()).getTime()){
						totalBuyUserCount += orderDtlCustom.getBuyUserCount();
						totalOrderCount += orderDtlCustom.getOrderCount();
						totalProductCount += orderDtlCustom.getProductCount();
						totalProductSalePrice = totalProductSalePrice.add(orderDtlCustom.getProductSalePrice());
						totalMchtPreferential = totalMchtPreferential.add(orderDtlCustom.getTotalMchtPreferential());
						totalPlatformPreferential = totalPlatformPreferential.add(orderDtlCustom.getTotalPlatformPreferential());
						totalPayAmount = totalPayAmount.add(orderDtlCustom.getTotalPayAmount());
						totalSettleAmount = totalSettleAmount.add(orderDtlCustom.getTotalSettleAmount());
						totalMchtDiscountRate = totalMchtDiscountRate.add(orderDtlCustom.getMchtDiscountRate());
						totalPlatDiscountRate = totalPlatDiscountRate.add(orderDtlCustom.getPlatDiscountRate());
//						totalGrossProfitRate = totalGrossProfitRate.add(orderDtlCustom.getGrossProfitRate());
					}
					
				}
				odc.setBuyUserCount(totalBuyUserCount);
				odc.setOrderCount(totalOrderCount);
				odc.setProductCount(totalProductCount);
				odc.setProductSalePrice(totalProductSalePrice);
				odc.setTotalMchtPreferential(totalMchtPreferential);
				odc.setTotalPlatformPreferential(totalPlatformPreferential);
				odc.setTotalPayAmount(totalPayAmount);
				odc.setTotalSettleAmount(totalSettleAmount);
				if(totalOrderCount == 0){
					odc.setUnitPrice(new BigDecimal(0));
				}else{
					odc.setUnitPrice(totalPayAmount.divide(new BigDecimal(totalOrderCount), 2, RoundingMode.HALF_UP));
				}
				//odc.setMchtDiscountRate(totalMchtDiscountRate);
				//odc.setPlatDiscountRate(totalPlatDiscountRate);
				odc.setMchtDiscountRate(totalMchtPreferential.divide(totalProductSalePrice, 4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
				odc.setPlatDiscountRate(totalPlatformPreferential.divide(totalProductSalePrice, 4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
				BigDecimal rate = new BigDecimal(1).subtract(odc.getTotalSettleAmount().divide(odc.getTotalPayAmount(), 4,BigDecimal.ROUND_HALF_UP));
				odc.setGrossProfitRate(rate.multiply(new BigDecimal(100)));
				orderDtlCustomList.add(odc);
			}
			resMap.put("Rows", orderDtlCustomList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	/**
	 * 周报表列表数据-1.2
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/weekReport/data.shtml")
	@ResponseBody
	public Map<String, Object> weekReportDataNew(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String payDateEnd = request.getParameter("pay_date_end");
			String mchtCode = request.getParameter("mchtCode");
			String status = request.getParameter("status");
			String week = request.getParameter("week");
			String productTypeId = request.getParameter("productTypeId");
			String platformContactId=request.getParameter("platformContactId");
			String activityType = request.getParameter("activityType");
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			if(!StringUtil.isEmpty(payDateEnd)){
				paramMap.put("payDateEnd",payDateEnd+" 23:59:59");
			}else{
				payDateEnd = df.format(new Date());
				paramMap.put("payDateEnd",payDateEnd+" 23:59:59");
			}
			Date payDateBegin = DateUtil.getDateAfter(df.parse(payDateEnd), -Integer.parseInt(week)*7+1);
			paramMap.put("payDateBegin",df.format(payDateBegin)+" 00:00:00");
			if(!StringUtil.isEmpty(mchtCode)){
				paramMap.put("mchtCode",mchtCode);
			}
			if(!StringUtil.isEmpty(status)){
				paramMap.put("status",status);
			}
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					paramMap.put("productTypeId",Integer.parseInt(isCwOrgProductTypeId));
				}
			}else {
				if(!StringUtil.isEmpty(productTypeId)){
					paramMap.put("productTypeId",Integer.parseInt(productTypeId));
				}
			}
			if (!StringUtil.isEmpty(platformContactId)) {
				paramMap.put("platformContactId", platformContactId);
			}	
			if(!StringUtil.isEmpty(activityType)){
				paramMap.put("activityType",activityType);
			}
			
			paramMap.put("listSpecMchtCode",commonService.listSpecMchtCode());
			/*List<Integer> mchtIds = mchtPlatformContactService.getMchtIdsByStaffId(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			if(mchtIds!=null && mchtIds.size()>0){
				paramMap.put("mchtIds",mchtIds);
			}*/
			List<OrderDtlCustom> orderDtlCustoms = orderDtlService.weekReport(paramMap);
			List<Week> weeks = WeekHelper.getWeekSplit(payDateBegin, df.parse(payDateEnd));
			for(int i=weeks.size()-1;i>=0;i--){
				for(OrderDtlCustom orderDtlCustom:orderDtlCustoms){
					if(df.parse(orderDtlCustom.getEachDay()).getTime()>=df.parse(weeks.get(i).getWeekBegin()).getTime() && df.parse(orderDtlCustom.getEachDay()).getTime()<=df.parse(weeks.get(i).getWeekEnd()).getTime()){
						orderDtlCustom.setEachWeek(weeks.get(i).getWeekBegin()+"至"+weeks.get(i).getWeekEnd());
						break;
					}
				}
			}
			resMap.put("Rows", orderDtlCustoms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 二级类目报表列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/productTypeReport/list.shtml")
	public ModelAndView Typelist(HttpServletRequest request) {
		String rtPage = "/dataCenter/operateData/productTypeReportList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = df.format(new Date());
		resMap.put("pay_date_end", dateStr);
		resMap.put("pay_date_begin", dateStr);
		resMap.put("status", "1");
		ProductTypeExample example = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = example.createCriteria();
		productTypeCriteria.andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		
		//钟表运营部状态，只获取主营类目为钟表 
		String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
				productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
			}
		}
		resMap.put("isCwOrgStatus", isCwOrgStatus);
		
		List<ProductType> productTypes = productTypeService.selectByExample(example);
		resMap.put("productTypes", productTypes);
		
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
			resMap.put("myContactId", myContactId);
			resMap.put("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		resMap.put("platformMchtContacts", platformMchtContact); 
		
		resMap.put("isContact", isContact);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 二级类目报表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/productTypeReport/data.shtml")
	@ResponseBody
	public Map<String, Object> Typedata(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String payDateBegin = request.getParameter("pay_date_begin");
			String payDateEnd = request.getParameter("pay_date_end");
			String mchtCode = request.getParameter("mchtCode");
			String status = request.getParameter("status");
			String productTypeId = request.getParameter("productTypeId");
			String platformContactId=request.getParameter("platformContactId");
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			if(!StringUtil.isEmpty(payDateBegin)){
				paramMap.put("payDateBegin",payDateBegin+ " 00:00:00");
			}else{
				payDateEnd = df.format(new Date());
				payDateBegin = payDateEnd.substring(0,7)+"-01 00:00:00";
				paramMap.put("payDateBegin",payDateBegin);
			}
			if(!StringUtil.isEmpty(payDateEnd)){
				paramMap.put("payDateEnd",payDateEnd+" 23:59:59");
			}else{
				payDateEnd = df.format(new Date());
				paramMap.put("payDateEnd",payDateEnd);
			}
			if(!StringUtil.isEmpty(mchtCode)){
				paramMap.put("mchtCode",mchtCode);
			}
			if(!StringUtil.isEmpty(status)){
				paramMap.put("status",status);
			}
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					paramMap.put("productTypeId",Integer.parseInt(isCwOrgProductTypeId));
				}
			}else {
				if(!StringUtil.isEmpty(productTypeId)){
					paramMap.put("productTypeId",Integer.parseInt(productTypeId));
				}
			}
			if (!StringUtil.isEmpty(platformContactId)) {
				paramMap.put("platformContactId",platformContactId);
			}
			/*List<Integer> mchtIds = mchtPlatformContactService.getMchtIdsByStaffId(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			if(mchtIds!=null && mchtIds.size()>0){
				paramMap.put("mchtIds",mchtIds);
			}*/
			paramMap.put("listSpecMchtCode",commonService.listSpecMchtCode());
			List<OrderDtlCustom> orderDtlCustoms = orderDtlService.productTypeReport(paramMap);
			resMap.put("Rows", orderDtlCustoms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 订单导出列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/orderExport/list.shtml")
	public ModelAndView orderExportlist(HttpServletRequest request) {
		String rtPage = "/dataCenter/operateData/orderExportList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = df.format(new Date());
		resMap.put("pay_date_end", dateStr);
		resMap.put("pay_date_begin", dateStr);
		resMap.put("status", "1");
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 订单导出列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/orderExport/data.shtml")
	@ResponseBody
	public Map<String, Object> orderExportdata(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			String payDateBegin = request.getParameter("pay_date_begin");
			String payDateEnd = request.getParameter("pay_date_end");
			String mchtCode = request.getParameter("mchtCode");
			String status = request.getParameter("status");
			String activityAreaId = request.getParameter("activityAreaId");
			String activityId = request.getParameter("activityId");
			OrderDtlCustomExample example = new OrderDtlCustomExample();
			OrderDtlCustomExample.OrderDtlCustomCriteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andIsGiveEqualTo("0");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtils.isEmpty(payDateBegin)){
				//criteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(payDateBegin+" 00:00:00"));
				criteria.andPayDateBeginGreaterThanOrEqualTo(payDateBegin+" 00:00:00");
			}
			if(!StringUtils.isEmpty(payDateEnd)){
				//criteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(payDateEnd+" 23:59:59"));
				criteria.andPayDateEndLessThanOrEqualTo(payDateEnd+" 23:59:59");
			}
			if(!StringUtils.isEmpty(mchtCode)){
				criteria.andMchtCodeEqualTo(mchtCode);
			}
			if(!StringUtils.isEmpty(status)){
				criteria.andCombineOrderStatusEqualTo(status);
			}
			if(!StringUtils.isEmpty(activityAreaId)){
				criteria.andActivityAreaIdEqualTo(Integer.parseInt(activityAreaId));
			}
			if(!StringUtils.isEmpty(activityId)){
				criteria.andActivityIdEqualTo(Integer.parseInt(activityId));
			}
			/*String MchtCode="P193103','P193093','P193076";*/
			/*String MchtCode="";*/
			List<String> listSpecMchtCode=commonService.listSpecMchtCode();
			/*for (String listSpecMchtCodeS : listSpecMchtCode) {
				if(MchtCode==""){
					MchtCode+=listSpecMchtCodeS;
				}else{
					MchtCode+=","+listSpecMchtCodeS;
				}
			}*/
			List<String> list=new ArrayList<String>();
			for (String listSpecMchtCodeS : listSpecMchtCode) {
				list.add(listSpecMchtCodeS);
			}
			String notMchtid="";
			MchtInfoExample mchtInfoExample=new MchtInfoExample();
			mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtCodeIn(listSpecMchtCode);
			List<MchtInfo> mchtInfos=mchtInfoService.selectByExample(mchtInfoExample);
			for (MchtInfo mchtInfo : mchtInfos) {
				if (notMchtid=="") {
					notMchtid+=mchtInfo.getId();
				}else {
					notMchtid+=","+mchtInfo.getId();
				}
			}
			criteria.andNotMchtCodeEqualTo(notMchtid);
			
			totalCount = orderDtlService.countOrderDtlCustomByExample(example);
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			List<OrderDtlCustom> orderDtlCustoms = orderDtlService.selectOrderDtlCustomByExample(example);
			for(OrderDtlCustom orderDtlCustom:orderDtlCustoms){
				orderDtlCustom.setPlatformPreferential(orderDtlCustom.getPlatformPreferential().add(orderDtlCustom.getIntegralPreferential()));
			}
			resMap.put("Rows", orderDtlCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/orderExport/export.shtml")
	public void export(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			String payDateBegin = request.getParameter("pay_date_begin");
			String payDateEnd = request.getParameter("pay_date_end");
			String mchtCode = request.getParameter("mchtCode");
			String status = request.getParameter("status");
			String activityAreaId = request.getParameter("activityAreaId");
			String activityId = request.getParameter("activityId");
			OrderDtlCustomExample example = new OrderDtlCustomExample();
			OrderDtlCustomExample.OrderDtlCustomCriteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andIsGiveEqualTo("0");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtils.isEmpty(payDateBegin)){
				//criteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(payDateBegin+" 00:00:00"));
				criteria.andPayDateBeginGreaterThanOrEqualTo(payDateBegin+" 00:00:00");
			}
			if(!StringUtils.isEmpty(payDateEnd)){
				//criteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(orderDateEnd+" 23:59:59"));
				criteria.andPayDateEndLessThanOrEqualTo(payDateEnd+" 23:59:59");
			}
			if(!StringUtils.isEmpty(mchtCode)){
				criteria.andMchtCodeEqualTo(mchtCode);
			}
			if(!StringUtils.isEmpty(status)){
				criteria.andCombineOrderStatusEqualTo(status);
			}
			if(!StringUtils.isEmpty(activityAreaId)){
				criteria.andActivityAreaIdEqualTo(Integer.parseInt(activityAreaId));
			}
			if(!StringUtils.isEmpty(activityId)){
				criteria.andActivityIdEqualTo(Integer.parseInt(activityId));
			}
			List<OrderDtlCustom> orderDtlCustoms = orderDtlService.selectOrderDtlCustomByExample(example);
			String[] titles = {"付款日期","子订单编号","付款状态","品牌","货号","商品名称","一级类目","二级类目","三级类目","醒购价","数量","销售商品金额","商家优惠","平台优惠","积分优惠","实付金额","支付方式","商家序号","店铺名称","用户ID","设备","收货人地域"};
			ExcelBean excelBean = new ExcelBean("导出POP交易商品明细.xls",
					"导出POP交易商品明细", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			List<String[]> datas = new ArrayList<String[]>();
			for(OrderDtlCustom orderDtlCustom:orderDtlCustoms){
				String[] data = {
						df.format(orderDtlCustom.getPayDate()),
						orderDtlCustom.getSubOrderCode(),
						orderDtlCustom.getCombineOrderStatusDesc(),
						orderDtlCustom.getBrandName(),
						orderDtlCustom.getArtNo(),
						orderDtlCustom.getProductName(),
						orderDtlCustom.getFirstProductTypeName(),
						orderDtlCustom.getSecondProductTypeName(),
						orderDtlCustom.getProductTypeName(),
						orderDtlCustom.getSalePrice().toString(),
						orderDtlCustom.getQuantity().toString(),
						orderDtlCustom.getSalePrice().multiply(new BigDecimal(orderDtlCustom.getQuantity())).toString(),
						orderDtlCustom.getMchtPreferential()==null?"":orderDtlCustom.getMchtPreferential().toString(),
						orderDtlCustom.getPlatformPreferential()==null?"":orderDtlCustom.getPlatformPreferential().toString(),
						orderDtlCustom.getIntegralPreferential()==null?"":orderDtlCustom.getIntegralPreferential().toString(),
						orderDtlCustom.getPayAmount().toString(),
						orderDtlCustom.getPaymentName(),
						orderDtlCustom.getMchtCode(),
						orderDtlCustom.getMchtShopName(),
						orderDtlCustom.getMemberId().toString(),
						orderDtlCustom.getSourceClientDesc(),
						orderDtlCustom.getAreaName()
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 栏目销售报表列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/activityType/list.shtml")
	public ModelAndView activityTypeList(HttpServletRequest request) {
		String rtPage = "/dataCenter/operateData/activityTypeList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = df.format(new Date());
		resMap.put("pay_date_end", dateStr);
		resMap.put("pay_date_begin", dateStr);
		ProductTypeExample example = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = example.createCriteria();
		productTypeCriteria.andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		
		//钟表运营部状态，只获取主营类目为钟表 
		String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
				productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
			}
		}
		resMap.put("isCwOrgStatus", isCwOrgStatus);
		
		List<ProductType> productTypes = productTypeService.selectByExample(example);
		resMap.put("productTypes", productTypes);
		
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
			resMap.put("myContactId", myContactId);
			resMap.put("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		resMap.put("platformMchtContacts", platformMchtContact); 
		
		resMap.put("isContact", isContact);
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 栏目销售报表列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/activityType/data.shtml")
	@ResponseBody
	public Map<String, Object> activityTypeData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String payDateBegin = request.getParameter("pay_date_begin");
			String payDateEnd = request.getParameter("pay_date_end");
			String productTypeId = request.getParameter("productTypeId");
			String platformContactId=request.getParameter("platformContactId");
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			if(!StringUtil.isEmpty(payDateBegin)){
				paramMap.put("payDateBegin",payDateBegin+" 00:00:00");
			}else{
				payDateEnd = df.format(new Date());
				payDateBegin = payDateEnd.substring(0,7)+"-01 00:00:00";
				paramMap.put("payDateBegin",payDateBegin);
			}
			if(!StringUtil.isEmpty(payDateEnd)){
				paramMap.put("payDateEnd",payDateEnd+" 23:59:59");
			}else{
				payDateEnd = df.format(new Date());
				paramMap.put("payDateEnd",payDateEnd);
			}
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					paramMap.put("productTypeId",Integer.parseInt(isCwOrgProductTypeId));
				}
			}else {
				if(!StringUtil.isEmpty(productTypeId)){
					paramMap.put("productTypeId",Integer.parseInt(productTypeId));
				}
			}
			if (!StringUtil.isEmpty(platformContactId)) {
				paramMap.put("platformContactId", platformContactId);
			}
			
			paramMap.put("listSpecMchtCode", commonService.listSpecMchtCode());
			
			/*List<Integer> mchtIds = mchtPlatformContactService.getMchtIdsByStaffId(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			if(mchtIds!=null && mchtIds.size()>0){ 
				paramMap.put("mchtIds",mchtIds);
			}*/
			
			Double brand_sale_amount_sum=0.00;
			Integer brand_sale_amount_quantity_count=0;
			Double area_sale_amount_sum=0.00;
			Integer area_sale_quantity_count=0;
			Double new_user_sale_amount_sum=0.00;
			Integer new_user_sale_quantity_count=0;
			Double new_user_kill_amount_sum=0.00;
			Integer new_user_kill_quantity_count=0;
			Double single_product_amount_sum=0.00;
			Integer single_product_quantity_count=0;
			Double xg_kill_amount_sum=0.00;
			Integer xg_kill_quantity_count=0;
			Double integral_amount_sum=0.00;
			Integer integral_quantity_count=0;
			Double broken_code_amount_sum=0.00;
			Integer broken_code_quantity_count=0;
			Double mall_sale_amount_sum=0.00;
			Integer mall_sale_quantity_count=0;

			List<HashMap<String, Object>> list = orderDtlService.activityTypeSaleCount(paramMap);
			HashMap<String, Object> map = new HashMap<String, Object>();
			List<String> containDays = new ArrayList<String>();
			for(HashMap<String, Object> resultMap:list){
				brand_sale_amount_sum+=Double.valueOf(resultMap.get("brand_sale_amount").toString());
				brand_sale_amount_quantity_count+=Integer.valueOf(resultMap.get("brand_sale_amount_quantity").toString());
				area_sale_amount_sum+=Double.valueOf(resultMap.get("area_sale_amount").toString());
				area_sale_quantity_count+=Integer.valueOf(resultMap.get("area_sale_quantity").toString());
				new_user_sale_amount_sum+=Double.valueOf(resultMap.get("new_user_sale_amount").toString());
				new_user_sale_quantity_count+=Integer.valueOf(resultMap.get("new_user_sale_quantity").toString());
				new_user_kill_amount_sum+=Double.valueOf(resultMap.get("new_user_kill_amount").toString());
				new_user_kill_quantity_count+=Integer.valueOf(resultMap.get("new_user_kill_quantity").toString());
				single_product_amount_sum+=Double.valueOf(resultMap.get("single_product_amount").toString());
				single_product_quantity_count+=Integer.valueOf(resultMap.get("single_product_quantity").toString());
				xg_kill_amount_sum+=Double.valueOf(resultMap.get("xg_kill_amount").toString());
				xg_kill_quantity_count+=Integer.valueOf(resultMap.get("xg_kill_quantity").toString());
				integral_amount_sum+=Double.valueOf(resultMap.get("integral_amount").toString());
				integral_quantity_count+=Integer.valueOf(resultMap.get("integral_quantity").toString());
				broken_code_amount_sum+=Double.valueOf(resultMap.get("broken_code_amount").toString());
				broken_code_quantity_count+=Integer.valueOf(resultMap.get("broken_code_quantity").toString());
				mall_sale_amount_sum+=Double.valueOf(resultMap.get("mall_sale_amount").toString());
				mall_sale_quantity_count+=Integer.valueOf(resultMap.get("mall_sale_quantity").toString());
				containDays.add(resultMap.get("each_day").toString());
				map.put(resultMap.get("each_day").toString(), resultMap);
			}
			
			HashMap<String,Object> sumHashMap = new HashMap<String, Object>();
			sumHashMap.put("each_day", "合计");
			sumHashMap.put("brand_sale_amount",(float)Math.round(brand_sale_amount_sum*100)/100);
			sumHashMap.put("brand_sale_amount_quantity",brand_sale_amount_quantity_count);
			sumHashMap.put("area_sale_amount", (float)Math.round(area_sale_amount_sum*100)/100);
			sumHashMap.put("area_sale_quantity", area_sale_quantity_count);
			sumHashMap.put("new_user_sale_amount", (float)Math.round(new_user_sale_amount_sum*100)/100);
			sumHashMap.put("new_user_sale_quantity", new_user_sale_quantity_count);
			sumHashMap.put("new_user_kill_amount", (float)Math.round(new_user_kill_amount_sum*100)/100);
			sumHashMap.put("new_user_kill_quantity", new_user_kill_quantity_count);
			sumHashMap.put("single_product_amount", (float)Math.round(single_product_amount_sum*100)/100);
			sumHashMap.put("single_product_quantity", single_product_quantity_count);
			sumHashMap.put("xg_kill_amount", (float)Math.round(xg_kill_amount_sum*100)/100);
			sumHashMap.put("xg_kill_quantity", xg_kill_quantity_count);
			sumHashMap.put("integral_amount", (float)Math.round(integral_amount_sum*100)/100);
			sumHashMap.put("integral_quantity", integral_quantity_count);
			sumHashMap.put("broken_code_amount", (float)Math.round(broken_code_amount_sum*100)/100);
			sumHashMap.put("broken_code_quantity", broken_code_quantity_count);
			sumHashMap.put("mall_sale_amount", (float)Math.round(mall_sale_amount_sum*100)/100);
			sumHashMap.put("mall_sale_quantity", mall_sale_quantity_count);

			List<String> betweenDays = this.getBetweenDays(payDateBegin, payDateEnd);
			for(int i= 0;i<betweenDays.size();i++){
				if(!containDays.contains(betweenDays.get(i))){
					HashMap<String, Object> newMap = new HashMap<String, Object>();
					newMap.put("each_day", betweenDays.get(i));
					newMap.put("brand_sale_amount_quantity", "0");
					newMap.put("brand_sale_amount", "0.00");
					newMap.put("area_sale_quantity", "0");
					newMap.put("area_sale_amount", "0.00");
					newMap.put("new_user_sale_quantity", "0");
					newMap.put("new_user_sale_amount", "0.00");
					newMap.put("new_user_kill_quantity", "0");
					newMap.put("new_user_kill_amount", "0.00");
					newMap.put("single_product_quantity", "0");
					newMap.put("single_product_amount", "0.00");
					newMap.put("xg_kill_quantity", "0");
					newMap.put("xg_kill_amount", "0.00");
					newMap.put("integral_quantity", "0");
					newMap.put("integral_amount", "0.00");
					newMap.put("broken_code_quantity", "0");
					newMap.put("broken_code_amount", "0.00");
					newMap.put("mall_sale_quantity", "0");
					newMap.put("mall_sale_amount", "0.00");
					list.add(newMap);
					map.put(betweenDays.get(i), newMap);
				}
			}
			
			list.add(sumHashMap);
			
			Collections.sort(list, new Comparator<HashMap<String, Object>>() {
	            @Override
	            public int compare(HashMap<String, Object> c1, HashMap<String, Object> c2) {
	                //升序
	            	if (c1.get("each_day").toString().equals("合计")) {
	            		return -1;
					}
	            	if (c2.get("each_day").toString().equals("合计")) {
	            		return 1;
					}
	                return c1.get("each_day").toString().compareTo(c2.get("each_day").toString());
	            }
	        });
			
			
			resMap.put("Rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 销售简报列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/salesKit/list.shtml")
	public ModelAndView salesKitList(HttpServletRequest request) {
		String rtPage = "/dataCenter/operateData/salesKitList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		resMap.put("payDateBegin",df.format(new Date()));
		resMap.put("payDateEnd",df.format(new Date()));
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 销售简报列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/salesKit/data.shtml")
	@ResponseBody
	public Map<String, Object> salesKitData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String payDateBegin = request.getParameter("pay_date_begin");
			String payDateEnd = request.getParameter("pay_date_end");
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			if(StringUtil.isEmpty(payDateBegin)){
				payDateBegin = df.format(new Date());
			}
			if(StringUtil.isEmpty(payDateEnd)){
				payDateEnd = df.format(new Date());
			}
			paramMap.put("payDateBegin", payDateBegin+" 00:00:00");
			paramMap.put("payDateEnd", payDateEnd+" 23:59:59");
			
			paramMap.put("listMchtCode", commonService.listSpecMchtCode());
			
			List<HashMap<String, Object>> orderList = orderDtlService.getOrderCount(paramMap);
			List<HashMap<String, Object>> brandList = orderDtlService.getBrandCount(paramMap);
			resMap.put("returnCode", "0000");
			resMap.put("orderList", orderList);
			resMap.put("brandList", brandList);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "查询失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 每小时数据列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/eachHour/list.shtml")
	public ModelAndView eachHourList(HttpServletRequest request) {
		String rtPage = "/dataCenter/operateData/eachHourList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = df.format(new Date());
		resMap.put("payDateBegin",dateStr);
		resMap.put("payDateEnd",dateStr);
		ProductBrandExample example = new ProductBrandExample();
		ProductBrandExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		List<ProductBrand> productBrands = productBrandService.selectByExample(example);
		resMap.put("productBrands",productBrands);
		ProductTypeExample e = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = e.createCriteria();
		productTypeCriteria.andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		
		//钟表运营部状态，只获取主营类目为钟表 
		String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
				productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
			}
		}
		resMap.put("isCwOrgStatus", isCwOrgStatus);
		
		List<ProductType> productTypes = productTypeService.selectByExample(e);
		resMap.put("productTypes", productTypes);
		
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
			resMap.put("myContactId", myContactId);
			resMap.put("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		resMap.put("platformMchtContacts", platformMchtContact); 
		
		resMap.put("isContact", isContact);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 每小时数据列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/eachHour/data.shtml")
	@ResponseBody
	public Map<String, Object> eachHourData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String payDateBegin = request.getParameter("payDateBegin");
			String payDateEnd = request.getParameter("payDateEnd");
			String brandId = request.getParameter("brandId");
			String mchtCode = request.getParameter("mchtCode");
			String shopName = request.getParameter("shopName");
			String productTypeId = request.getParameter("productTypeId");
			String platformContactId=request.getParameter("platformContactId");
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			if(StringUtils.isEmpty(payDateBegin) || StringUtils.isEmpty(payDateEnd)){
				payDateEnd = sdf.format(new Date());
				payDateBegin = payDateEnd.substring(0,7)+"-01";
			}
			paramMap.put("payDateBegin", payDateBegin+" 00:00:00");
			paramMap.put("payDateEnd", payDateEnd+" 23:59:59");
			if(!StringUtils.isEmpty(brandId)){
				paramMap.put("brandId", brandId);
			}
			if(!StringUtils.isEmpty(mchtCode)){
				paramMap.put("mchtCode", mchtCode);
			}
			if(!StringUtils.isEmpty(shopName)){
				paramMap.put("shopName", shopName);
			}
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					paramMap.put("productTypeId", Integer.parseInt(isCwOrgProductTypeId));
				}
			}else {
				if(!StringUtils.isEmpty(productTypeId)){
					paramMap.put("productTypeId", Integer.parseInt(productTypeId));
				}
			}
			if (!StringUtil.isEmpty(platformContactId)) {
				paramMap.put("platformContactId", platformContactId);
			}
			/*List<Integer> mchtIds = mchtPlatformContactService.getMchtIdsByStaffId(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			if(mchtIds!=null && mchtIds.size()>0){
				paramMap.put("mchtIds",mchtIds);
			}*/
			paramMap.put("listSpecMchtCode", commonService.listSpecMchtCode());
			List<HashMap<String, Object>> list = orderDtlService.getEachHourCount(paramMap);
			//单位小时内无数据填充
			HashMap<Integer, HashMap<String, Object>> map = new HashMap<Integer, HashMap<String, Object>>();
			List<HashMap<String, Object>> list1 = new ArrayList<HashMap<String, Object>>();
			for (int i = 0; i < list.size(); i++) {
				Integer hour = Integer.valueOf(list.get(i).get("hour").toString());
				map.put(hour,list.get(i));
			}
			for (int i = 0; i <=23; i++) {
				HashMap<String, Object>  map1 =map.get(i);
				if (map1==null){
					map1= new HashMap<String,Object>();
					map1.put("hour",i <= 9 ? "0"+i : i);
				}
				list1.add(map1);
			}
			resMap.put("Rows", list1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title orderDtlPayAmountList   
	 * @Description TODO(一级类目销售报表)   
	 * @author pengl
	 * @date 2018年10月19日 下午1:46:03
	 */
	@RequestMapping(value = "/oneProductTypeReport/orderDtlPayAmountList.shtml")
	public ModelAndView orderDtlPayAmountList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/operateData/orderDtlPayAmountList");
		String staffId = this.getSessionStaffBean(request).getStaffID();
		//负责的类目
		SysStaffProductTypeExample staffProductTypeExample = new SysStaffProductTypeExample();
		staffProductTypeExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(staffId));
		List<SysStaffProductType> staffProductTypes = sysStaffProductTypeService.selectByExample(staffProductTypeExample);
		boolean ydProductType = false;
		boolean xxProductType = false;
		boolean fzProductType = false;
		boolean shProductType = false;
		boolean zbProductType = false;
		boolean smProductType = false;
		boolean mzProductType = false;
		boolean myProductType = false;
		boolean spProductType = false;
		//负责类目总状态
		boolean productTypeStatus = false; 
		if(staffProductTypes != null && staffProductTypes.size() > 0) {
			productTypeStatus = true;
			for(SysStaffProductType staffProductType : staffProductTypes) {
				if(staffProductType.getProductTypeId() == 2) {
					ydProductType = true;
				}
				if(staffProductType.getProductTypeId() == 3) {
					xxProductType = true;
				}
				if(staffProductType.getProductTypeId() == 4) {
					fzProductType = true;
				}
				if(staffProductType.getProductTypeId() == 5) {
					shProductType = true;
				}
				if(staffProductType.getProductTypeId() == 430) {
					zbProductType = true;
				}
				if(staffProductType.getProductTypeId() == 705) {
					smProductType = true;
				}
				if(staffProductType.getProductTypeId() == 858) {
					mzProductType = true;
				}
				if(staffProductType.getProductTypeId() == 954) {
					myProductType = true;
				}
				if(staffProductType.getProductTypeId() == 1047) {
					spProductType = true;
				}
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String endPayDate = sdf.format(new Date());
			String beginPayDate = endPayDate.substring(0,7) + "-01";
			m.addObject("beginPayDate", beginPayDate);
			m.addObject("endPayDate", endPayDate);
		}
		m.addObject("ydProductType", ydProductType);
		m.addObject("xxProductType", xxProductType);
		m.addObject("fzProductType", fzProductType);
		m.addObject("shProductType", shProductType);
		m.addObject("zbProductType", zbProductType);
		m.addObject("smProductType", smProductType);
		m.addObject("mzProductType", mzProductType);
		m.addObject("myProductType", myProductType);
		m.addObject("spProductType", spProductType);
		m.addObject("productTypeStatus", productTypeStatus);
		return m;
	}
	
	/**
	 * 
	 * @Title orderDtlPayAmountData   
	 * @Description TODO(一级类目销售报表)   
	 * @author pengl
	 * @date 2018年10月19日 下午1:46:20
	 */
	@ResponseBody
	@RequestMapping(value = "/oneProductTypeReport/orderDtlPayAmountData.shtml")
	public Map<String, Object> orderDtlPayAmountData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String ydProductType = request.getParameter("ydProductType");
			String xxProductType = request.getParameter("xxProductType");
			String fzProductType = request.getParameter("fzProductType");
			String shProductType = request.getParameter("shProductType");
			String zbProductType = request.getParameter("zbProductType");
			String smProductType = request.getParameter("smProductType");
			String mzProductType = request.getParameter("mzProductType");
			String myProductType = request.getParameter("myProductType");
			String spProductType = request.getParameter("spProductType");
			Map<String, Object> paramMap = new HashMap<String, Object>();
			if(!StringUtil.isEmpty(request.getParameter("activityType"))) {
				paramMap.put("activityType", request.getParameter("activityType"));
			}
			paramMap.put("beginPayDate", request.getParameter("beginPayDate")+ " 00:00:00");
			paramMap.put("endPayDate", request.getParameter("endPayDate")+ " 23:59:59");
			paramMap.put("listSpecMchtCode", commonService.listSpecMchtCode());
			List<Map<String, Object>> orderDtlPayAmounList = orderDtlService.selectOrderDtlPayAmounList(paramMap);
			List<Map<String, Object>> orderDtlPayAmounSum = orderDtlService.selectOrderDtlPayAmounSum(paramMap);
			List<String> payDateList = DateUtil.getBetweenDates(sdf.parse(request.getParameter("beginPayDate")), sdf.parse(request.getParameter("endPayDate")), sdf, "day");
			List<Map<String, Object>> returnList = new ArrayList<Map<String,Object>>();
			for(String payDate : payDateList) {
				Map<String, Object> returnMap = new HashMap<String, Object>();
				returnMap.put("pay_date", payDate);
				returnMap.put("yd_pay_amount_sum", 0);
				returnMap.put("xx_pay_amount_sum", 0);
				returnMap.put("fz_pay_amount_sum", 0);
				returnMap.put("sh_pay_amount_sum", 0);
				returnMap.put("zb_pay_amount_sum", 0);
				returnMap.put("sm_pay_amount_sum", 0);
				returnMap.put("mz_pay_amount_sum", 0);
				returnMap.put("my_pay_amount_sum", 0);
				returnMap.put("sp_pay_amount_sum", 0);
				for(Map<String, Object> map : orderDtlPayAmounList) {
					if(map.get("pay_date").toString().equals(payDate)) {
						String topParentId = map.get("top_parent_id").toString();
						if("2".equals(topParentId)) {  //运动户外
							returnMap.put("yd_pay_amount_sum", map.get("pay_amount_sum"));
						}else if("3".equals(topParentId)) { //鞋靴箱包
							returnMap.put("xx_pay_amount_sum", map.get("pay_amount_sum"));
						}else if("4".equals(topParentId)) { //服装配饰
							returnMap.put("fz_pay_amount_sum", map.get("pay_amount_sum"));
						}else if("5".equals(topParentId)) { //生活家居
							returnMap.put("sh_pay_amount_sum", map.get("pay_amount_sum"));
						}else if("430".equals(topParentId)) { //钟表珠宝
							returnMap.put("zb_pay_amount_sum", map.get("pay_amount_sum"));
						}else if("705".equals(topParentId)) { //数码家电
							returnMap.put("sm_pay_amount_sum", map.get("pay_amount_sum"));
						}else if("858".equals(topParentId)) { //美妆个护
							returnMap.put("mz_pay_amount_sum", map.get("pay_amount_sum"));
						}else if("954".equals(topParentId)) { //母婴童装
							returnMap.put("my_pay_amount_sum", map.get("pay_amount_sum"));
						}else if("1047".equals(topParentId)) { //食品超市
							returnMap.put("sp_pay_amount_sum", map.get("pay_amount_sum"));
						}
					}
				}
				BigDecimal payAmountSum = new BigDecimal(0);
				if("true".equals(ydProductType)) {
					payAmountSum = payAmountSum.add(new BigDecimal(returnMap.get("yd_pay_amount_sum").toString()));
				}
				if("true".equals(xxProductType)) {
					payAmountSum = payAmountSum.add(new BigDecimal(returnMap.get("xx_pay_amount_sum").toString()));
				}
				if("true".equals(fzProductType)) {
					payAmountSum = payAmountSum.add(new BigDecimal(returnMap.get("fz_pay_amount_sum").toString()));
				}
				if("true".equals(shProductType)) {
					payAmountSum = payAmountSum.add(new BigDecimal(returnMap.get("sh_pay_amount_sum").toString()));
				}
				if("true".equals(zbProductType)) {
					payAmountSum = payAmountSum.add(new BigDecimal(returnMap.get("zb_pay_amount_sum").toString()));
				}
				if("true".equals(smProductType)) {
					payAmountSum = payAmountSum.add(new BigDecimal(returnMap.get("sm_pay_amount_sum").toString()));
				}
				if("true".equals(mzProductType)) {
					payAmountSum = payAmountSum.add(new BigDecimal(returnMap.get("mz_pay_amount_sum").toString()));
				}
				if("true".equals(myProductType)) {
					payAmountSum = payAmountSum.add(new BigDecimal(returnMap.get("my_pay_amount_sum").toString()));
				}
				if("true".equals(spProductType)) {
					payAmountSum = payAmountSum.add(new BigDecimal(returnMap.get("sp_pay_amount_sum").toString()));
				}
				returnMap.put("pay_amount_sum", payAmountSum);
				returnList.add(returnMap);
			}
			Map<String, Object> orderDtlPayAmounSumMap = new HashMap<String, Object>();
			orderDtlPayAmounSumMap.put("pay_date", "类目汇总");
			orderDtlPayAmounSumMap.put("yd_pay_amount_sum", 0);
			orderDtlPayAmounSumMap.put("xx_pay_amount_sum", 0);
			orderDtlPayAmounSumMap.put("fz_pay_amount_sum", 0);
			orderDtlPayAmounSumMap.put("sh_pay_amount_sum", 0);
			orderDtlPayAmounSumMap.put("zb_pay_amount_sum", 0);
			orderDtlPayAmounSumMap.put("sm_pay_amount_sum", 0);
			orderDtlPayAmounSumMap.put("mz_pay_amount_sum", 0);
			orderDtlPayAmounSumMap.put("my_pay_amount_sum", 0);
			orderDtlPayAmounSumMap.put("sp_pay_amount_sum", 0);
			for(Map<String, Object> map : orderDtlPayAmounSum) {
				String topParentId = map.get("top_parent_id").toString();
				if("2".equals(topParentId)) {  //运动户外
					orderDtlPayAmounSumMap.put("yd_pay_amount_sum", map.get("pay_amount_sum"));
				}else if("3".equals(topParentId)) { //鞋靴箱包
					orderDtlPayAmounSumMap.put("xx_pay_amount_sum", map.get("pay_amount_sum"));
				}else if("4".equals(topParentId)) { //服装配饰
					orderDtlPayAmounSumMap.put("fz_pay_amount_sum", map.get("pay_amount_sum"));
				}else if("5".equals(topParentId)) { //生活家居
					orderDtlPayAmounSumMap.put("sh_pay_amount_sum", map.get("pay_amount_sum"));
				}else if("430".equals(topParentId)) { //钟表珠宝
					orderDtlPayAmounSumMap.put("zb_pay_amount_sum", map.get("pay_amount_sum"));
				}else if("705".equals(topParentId)) { //数码家电
					orderDtlPayAmounSumMap.put("sm_pay_amount_sum", map.get("pay_amount_sum"));
				}else if("858".equals(topParentId)) { //美妆个护
					orderDtlPayAmounSumMap.put("mz_pay_amount_sum", map.get("pay_amount_sum"));
				}else if("954".equals(topParentId)) { //母婴童装
					orderDtlPayAmounSumMap.put("my_pay_amount_sum", map.get("pay_amount_sum"));
				}else if("1047".equals(topParentId)) { //食品超市
					orderDtlPayAmounSumMap.put("sp_pay_amount_sum", map.get("pay_amount_sum"));
				}
			}
			BigDecimal payAmountSum = new BigDecimal(0);
			if("true".equals(ydProductType)) {
				payAmountSum = payAmountSum.add(new BigDecimal(orderDtlPayAmounSumMap.get("yd_pay_amount_sum").toString()));
			}
			if("true".equals(xxProductType)) {
				payAmountSum = payAmountSum.add(new BigDecimal(orderDtlPayAmounSumMap.get("xx_pay_amount_sum").toString()));
			}
			if("true".equals(fzProductType)) {
				payAmountSum = payAmountSum.add(new BigDecimal(orderDtlPayAmounSumMap.get("fz_pay_amount_sum").toString()));
			}
			if("true".equals(shProductType)) {
				payAmountSum = payAmountSum.add(new BigDecimal(orderDtlPayAmounSumMap.get("sh_pay_amount_sum").toString()));
			}
			if("true".equals(zbProductType)) {
				payAmountSum = payAmountSum.add(new BigDecimal(orderDtlPayAmounSumMap.get("zb_pay_amount_sum").toString()));
			}
			if("true".equals(smProductType)) {
				payAmountSum = payAmountSum.add(new BigDecimal(orderDtlPayAmounSumMap.get("sm_pay_amount_sum").toString()));
			}
			if("true".equals(mzProductType)) {
				payAmountSum = payAmountSum.add(new BigDecimal(orderDtlPayAmounSumMap.get("mz_pay_amount_sum").toString()));
			}
			if("true".equals(myProductType)) {
				payAmountSum = payAmountSum.add(new BigDecimal(orderDtlPayAmounSumMap.get("my_pay_amount_sum").toString()));
			}
			if("true".equals(spProductType)) {
				payAmountSum = payAmountSum.add(new BigDecimal(orderDtlPayAmounSumMap.get("sp_pay_amount_sum").toString()));
			}
			orderDtlPayAmounSumMap.put("pay_amount_sum", payAmountSum);
			returnList.add(orderDtlPayAmounSumMap);
			resMap.put("Rows", returnList);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title selectReQuantityRate   
	 * @Description TODO(获取退货率)   
	 * @author pengl
	 * @date 2018年10月25日 下午6:07:24
	 */
	@RequestMapping(value = "/subOrderReport/selectReQuantityRate.shtml")
	@ResponseBody
	public Map<String, Object> selectReQuantityRate(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String payDateBegin = request.getParameter("payDateBegin");
			String payDateEnd = request.getParameter("payDateEnd");
			String isManageSelf = request.getParameter("isManageSelf");  //自营商家统计请求的标识
			if(StringUtil.isEmpty(payDateBegin)) {
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
				payDateBegin = sdf.format(calendar.getTime()); //默认本月1日
			}
			if(StringUtils.isEmpty(payDateEnd)) {
				payDateEnd = sdf.format(new Date()); //默认今天
			}
			paramMap.put("paramId", request.getParameter("paramId"));
			paramMap.put("row_type", request.getParameter("rowType"));
			paramMap.put("payDateBegin", payDateBegin+" 00:00:00");
			paramMap.put("payDateEnd", payDateEnd+" 23:59:59");
			paramMap.put("isManageSelf",isManageSelf);  //自营商家统计请求时的标识
			String reQuantityRate = subOrderReportService.selectReQuantityRate(paramMap);
			resMap.put("reQuantityRate", reQuantityRate);
			resMap.put("code", "200");
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("code", "4004");
			resMap.put("msg", "获取退货率失败");
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title selectGoodCommentRate   
	 * @Description TODO(获取好评率)   
	 * @author pengl
	 * @date 2018年10月25日 下午6:08:21
	 */
	@RequestMapping(value = "/subOrderReport/selectGoodCommentRate.shtml")
	@ResponseBody
	public Map<String, Object> selectGoodCommentRate(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String payDateBegin = request.getParameter("payDateBegin");
			String payDateEnd = request.getParameter("payDateEnd");
			String isManageSelf = request.getParameter("isManageSelf");  //自营商家统计请求时的标识
			if(StringUtil.isEmpty(payDateBegin)) {
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
				payDateBegin = sdf.format(calendar.getTime()); //默认本月1日
			}
			if(StringUtils.isEmpty(payDateEnd)) {
				payDateEnd = sdf.format(new Date()); //默认今天
			}
			paramMap.put("paramId", request.getParameter("paramId"));
			paramMap.put("row_type", request.getParameter("rowType"));
			paramMap.put("payDateBegin", payDateBegin+" 00:00:00");
			paramMap.put("payDateEnd", payDateEnd+" 23:59:59");
			paramMap.put("isManageSelf",isManageSelf);
			String reQuantityRate = subOrderReportService.selectGoodCommentRate(paramMap);
			resMap.put("reQuantityRate", reQuantityRate);
			resMap.put("code", "200");
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("code", "4004");
			resMap.put("msg", "获取退货率失败");
		}
		return resMap;
	}
	
	
	/**
	 * 月报表列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/monthReport/list.shtml")
	public ModelAndView monthReportList() {
		String rtPage = "/dataCenter/operateData/monthReportList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		// 查询所有以及类目
        ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria typeCriteria = productTypeExample.createCriteria();
		typeCriteria.andDelFlagEqualTo("0");
		typeCriteria.andTLevelEqualTo(1);
        List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
        resMap.put("productTypeList", productTypeList);
        //默认时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, -2);
		String startDate = sdf.format(cal.getTime());
		resMap.put("startTime", startDate);
		resMap.put("endTime", sdf.format(new Date()));
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 日报表列表数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/monthReport/data.shtml")
	@ResponseBody
	public Map<String, Object> monthReportData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String begin = request.getParameter("begin_month");
			String end = request.getParameter("end_month");
			resMap.put("Rows", operateDataService.getMonthDatas(begin, end));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	     //店铺数据报表
		@RequestMapping("/ShopReport/shopReportList.shtml")
		public ModelAndView shopReportList(HttpServletRequest request) {
			ModelAndView m = new ModelAndView("/dataCenter/operateData/shopReportList");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = df.format(new Date());
			if(StringUtils.isEmpty(request.getParameter("endPaydateDate"))){
				m.addObject("endPaydateDate", dateStr);
			}else{
				m.addObject("endPaydateDate", request.getParameter("endPaydateDate"));
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			m.addObject("PaydateDateone", sdf.format(c.getTime()));
			
			Calendar c1 = Calendar.getInstance();
			c1.set(Calendar.DAY_OF_YEAR, c1.get(Calendar.DAY_OF_YEAR) -1);
			String paydateDate1=df.format(c1.getTime()).substring(0, 7);
			String endpaydateDate1=dateStr.substring(0, 7);
			if (!paydateDate1.equals(endpaydateDate1)) {
				c1.add(Calendar.MONTH, -1);
			}
			m.addObject("PaydateDatetwo", sdf.format(c1.getTime()));
		    
			Calendar c2 = Calendar.getInstance();
			c2.set(Calendar.DAY_OF_YEAR, c2.get(Calendar.DAY_OF_YEAR) -2);
			String paydateDate2=df.format(c1.getTime()).substring(0, 7);
			String endpaydateDate2=dateStr.substring(0, 7);
			if (!paydateDate2.equals(endpaydateDate2)) {
				c2.add(Calendar.MONTH, -1);
			}
			m.addObject("PaydateDatethree", sdf.format(c2.getTime()));
			
			Calendar c3 = Calendar.getInstance();
			c3.set(Calendar.DAY_OF_YEAR, c3.get(Calendar.DAY_OF_YEAR) -3);
			String paydateDate3=df.format(c1.getTime()).substring(0, 7);
			String endpaydateDate3=dateStr.substring(0, 7);
			if (!paydateDate3.equals(endpaydateDate3)) {
				c3.add(Calendar.MONTH, -1);
			}
			m.addObject("PaydateDatefour", sdf.format(c3.getTime()));
			
			Calendar c4 = Calendar.getInstance();
			c4.set(Calendar.DAY_OF_YEAR, c4.get(Calendar.DAY_OF_YEAR) -4);
			String paydateDate4=df.format(c1.getTime()).substring(0, 7);
			String endpaydateDate4=dateStr.substring(0, 7);
			if (!paydateDate4.equals(endpaydateDate4)) {
				c4.add(Calendar.MONTH, -1);
			}
			m.addObject("PaydateDatefive", sdf.format(c4.getTime()));
			
			Calendar c5 = Calendar.getInstance();
			c5.set(Calendar.DAY_OF_YEAR, c5.get(Calendar.DAY_OF_YEAR) -5);
			String paydateDate5=df.format(c1.getTime()).substring(0, 7);
			String endpaydateDate5=dateStr.substring(0, 7);
			if (!paydateDate5.equals(endpaydateDate5)) {
				c5.add(Calendar.MONTH, -1);
			}
			m.addObject("PaydateDatesix", sdf.format(c5.getTime()));
			
			Calendar c6 = Calendar.getInstance();
			c6.set(Calendar.DAY_OF_YEAR, c6.get(Calendar.DAY_OF_YEAR) -6);
			String paydateDate6=df.format(c1.getTime()).substring(0, 7);
			String endpaydateDate6=dateStr.substring(0, 7);
			if (!paydateDate6.equals(endpaydateDate6)) {
				c6.add(Calendar.MONTH, -1);
			}
			m.addObject("PaydateDateseven", sdf.format(c6.getTime()));
				
			String staffID = this.getSessionStaffBean(request).getStaffID();
			
			//对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）
			String isManagement = this.getSessionStaffBean(request).getIsManagement();
			m.addObject("isManagement", isManagement);
			m.addObject("staffID", staffID);
			SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
			SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
			sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
			List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
			m.addObject("sysStaffInfoCustomList", sysStaffInfoCustomList);
			
			ProductTypeExample pte = new ProductTypeExample();
			ProductTypeExample.Criteria productTypeCriteria = pte.createCriteria();
			productTypeCriteria.andDelFlagEqualTo("0");
			productTypeCriteria.andStatusEqualTo("1");
			productTypeCriteria.andTLevelEqualTo(1);
			List<ProductType> productTypes = productTypeService.selectByExample(pte);
			m.addObject("productTypes", productTypes);
			
			return m;
		}
		
		
		@RequestMapping(value = "/ShopReport/shopreportdata.shtml")
		@ResponseBody
		public Map<String, Object> shopreportdata(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap, Page page) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			List<Map<String, Object>> dataList = null;
			Integer totalCount = 0;
			try {
			
				String mchtCode=request.getParameter("mchtCode");
				String mchtName=request.getParameter("mchtName");
				String productTypeId=request.getParameter("productTypeId");
				String platContactStaffId=request.getParameter("platContactStaffId");
				String productBrandName=request.getParameter("productBrandName");
				
				paramMap.put("mchtCode",mchtCode);
				paramMap.put("mchtName",mchtName);
				paramMap.put("productTypeId",productTypeId);
				paramMap.put("platContactStaffId",platContactStaffId);
				paramMap.put("productBrandName",productBrandName);
				
				//当主营类目、对接人为空时，限制搜索范围为： 本人负责的类目 与 本人对接的商家 并集
				if(StringUtil.isEmpty(request.getParameter("productTypeId")) && StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {
				   paramMap.put("productTypeContactIsNull", this.getSessionStaffBean(request).getStaffID());
				}
								
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				
				String paydateDate1 = request.getParameter("endPaydateDate");
				
				if (!StringUtils.isEmpty(paydateDate1)) {
				    Date paydatedate1 = df.parse(paydateDate1);
				
				paramMap.put("paydateDate1", paydateDate1+" 00:00:00");
				paramMap.put("paydateDate1o", paydateDate1+" 23:59:59");
				
				paramMap.put("endPaydatedate", paydateDate1+" 23:59:59");
		
				Calendar c2= Calendar.getInstance();
				c2.setTime(paydatedate1);
				c2.add(Calendar.DATE, -1);
				paramMap.put("paydateDate2",df.format(c2.getTime())+" 00:00:00");
				paramMap.put("paydateDate2t",df.format(c2.getTime())+" 23:59:59"); 
			
				
				Calendar c3= Calendar.getInstance();
				c3.setTime(paydatedate1);
				c3.add(Calendar.DATE, -2);
				paramMap.put("paydateDate3", df.format(c3.getTime())+" 00:00:00");
				paramMap.put("paydateDate3s",df.format(c3.getTime())+" 23:59:59");
				
				Calendar c4= Calendar.getInstance();
				c4.setTime(paydatedate1);
				c4.add(Calendar.DATE, -3);
				paramMap.put("paydateDate4",df.format(c4.getTime())+" 00:00:00");
				paramMap.put("paydateDate4f",df.format(c4.getTime())+" 23:59:59");
				
				Calendar c5 = Calendar.getInstance();
				c5.setTime(paydatedate1);
				c5.add(Calendar.DATE, -4);
				paramMap.put("paydateDate5", df.format(c5.getTime())+" 00:00:00");
				paramMap.put("paydateDate5fi", df.format(c5.getTime())+" 23:59:59");
			
				Calendar c6 = Calendar.getInstance();
				c6.setTime(paydatedate1);
				c6.add(Calendar.DATE, -5);
				paramMap.put("paydateDate6", df.format(c6.getTime())+" 00:00:00");
				paramMap.put("paydateDate6sx", df.format(c6.getTime())+" 23:59:59");
				
				Calendar c7 = Calendar.getInstance();
				c7.setTime(paydatedate1);
				c7.add(Calendar.DATE, -6);
				paramMap.put("paydateDate7", df.format(c7.getTime())+" 00:00:00");
				paramMap.put("paydateDate7se", df.format(c7.getTime())+" 23:59:59");
				
				paramMap.put("startPaydatedate", df.format(c7.getTime())+" 00:00:00");
				
				}
				
				paramMap.put("listSpecMchtCode", commonService.listSpecMchtCode());
				
				paramMap.put("MIN_NUM", page.getLimitStart());
				paramMap.put("MAX_NUM", page.getLimitSize());
				
				dataList=mchtInfoService.selectMchtInfoShopCustomList(paramMap);
		
				resMap.put("Rows", dataList);
				resMap.put("Total", totalCount);
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			return resMap;

		}
		
		
		//品牌
		@ResponseBody
		@RequestMapping("/ShopReport/getProductBrandList.shtml")
		public Map<String, Object> getProductBrandList(HttpServletRequest request, Page page) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			List<ProductBrand> dataList = null;
			Integer totalCount = 0;
			try {
				ProductBrandExample productBrandExample = new ProductBrandExample();
				ProductBrandExample.Criteria productBrandCriteria = productBrandExample.createCriteria();
				productBrandCriteria.andDelFlagEqualTo("0");
				if(!StringUtil.isEmpty(request.getParameter("condition"))) {
					JSONArray jsonArray = JSONArray.fromObject(request.getParameter("condition"));
					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						String searchvalue = jsonObject.getString("value");
						String searchitem = jsonObject.getString("field");
						if (!StringUtil.isEmpty(searchvalue)) {
							if (searchitem.equals("name")) {
								productBrandCriteria.andNameLike("%"+searchvalue+"%");
							}
						}
					}
				}
				productBrandExample.setLimitSize(page.getLimitSize());
				productBrandExample.setLimitStart(page.getLimitStart());
				dataList = productBrandService.selectByExample(productBrandExample);
				totalCount = productBrandService.countByExample(productBrandExample);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
			return resMap;
		}
		
		
		
		//品牌数据报表
		@RequestMapping("/Brandreport/brandReportList.shtml")
		public ModelAndView brandReportList(HttpServletRequest request) {
			ModelAndView m = new ModelAndView("/dataCenter/operateData/brandReportList");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = df.format(new Date());
			if(StringUtils.isEmpty(request.getParameter("endPaydateDate"))){
				m.addObject("endPaydateDate", dateStr);
			}else{
				m.addObject("endPaydateDate", request.getParameter("endPaydateDate"));
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			m.addObject("PaydateDateone", sdf.format(c.getTime()));
			
			Calendar c1 = Calendar.getInstance();
			c1.set(Calendar.DAY_OF_YEAR, c1.get(Calendar.DAY_OF_YEAR) -1);
			String paydateDate1=df.format(c1.getTime()).substring(0, 7);
			String endpaydateDate1=dateStr.substring(0, 7);
			if (!paydateDate1.equals(endpaydateDate1)) {
				c1.add(Calendar.MONTH, -1);
			}
			m.addObject("PaydateDatetwo", sdf.format(c1.getTime()));
		    
			Calendar c2 = Calendar.getInstance();
			c2.set(Calendar.DAY_OF_YEAR, c2.get(Calendar.DAY_OF_YEAR) -2);
			String paydateDate2=df.format(c1.getTime()).substring(0, 7);
			String endpaydateDate2=dateStr.substring(0, 7);
			if (!paydateDate2.equals(endpaydateDate2)) {
				c2.add(Calendar.MONTH, -1);
			}
			m.addObject("PaydateDatethree", sdf.format(c2.getTime()));
			
			Calendar c3 = Calendar.getInstance();
			c3.set(Calendar.DAY_OF_YEAR, c3.get(Calendar.DAY_OF_YEAR) -3);
			String paydateDate3=df.format(c1.getTime()).substring(0, 7);
			String endpaydateDate3=dateStr.substring(0, 7);
			if (!paydateDate3.equals(endpaydateDate3)) {
				c3.add(Calendar.MONTH, -1);
			}
			m.addObject("PaydateDatefour", sdf.format(c3.getTime()));
			
			Calendar c4 = Calendar.getInstance();
			c4.set(Calendar.DAY_OF_YEAR, c4.get(Calendar.DAY_OF_YEAR) -4);
			String paydateDate4=df.format(c1.getTime()).substring(0, 7);
			String endpaydateDate4=dateStr.substring(0, 7);
			if (!paydateDate4.equals(endpaydateDate4)) {
				c4.add(Calendar.MONTH, -1);
			}
			m.addObject("PaydateDatefive", sdf.format(c4.getTime()));
			
			Calendar c5 = Calendar.getInstance();
			c5.set(Calendar.DAY_OF_YEAR, c5.get(Calendar.DAY_OF_YEAR) -5);
			String paydateDate5=df.format(c1.getTime()).substring(0, 7);
			String endpaydateDate5=dateStr.substring(0, 7);
			if (!paydateDate5.equals(endpaydateDate5)) {
				c5.add(Calendar.MONTH, -1);
			}
			m.addObject("PaydateDatesix", sdf.format(c5.getTime()));
			
			Calendar c6 = Calendar.getInstance();
			c6.set(Calendar.DAY_OF_YEAR, c6.get(Calendar.DAY_OF_YEAR) -6);
			String paydateDate6=df.format(c1.getTime()).substring(0, 7);
			String endpaydateDate6=dateStr.substring(0, 7);
			if (!paydateDate6.equals(endpaydateDate6)) {
				c6.add(Calendar.MONTH, -1);
			}
			m.addObject("PaydateDateseven", sdf.format(c6.getTime()));
				
			String staffID = this.getSessionStaffBean(request).getStaffID();
			
			//对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）
			String isManagement = this.getSessionStaffBean(request).getIsManagement();
			m.addObject("isManagement", isManagement);
			m.addObject("staffID", staffID);
			SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
			SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
			sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
			List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
			m.addObject("sysStaffInfoCustomList", sysStaffInfoCustomList);
			
			ProductTypeExample pte = new ProductTypeExample();
			ProductTypeExample.Criteria productTypeCriteria = pte.createCriteria();
			productTypeCriteria.andDelFlagEqualTo("0");
			productTypeCriteria.andStatusEqualTo("1");
			productTypeCriteria.andTLevelEqualTo(1);
			List<ProductType> productTypes = productTypeService.selectByExample(pte);
			m.addObject("productTypes", productTypes);
			
			return m;
		}
		
		
		@RequestMapping(value = "/brandReport/brandreportdata.shtml")
		@ResponseBody
		public Map<String, Object> brandreportdata(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap, Page page) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			List<Map<String, Object>> dataList = null;
			Integer totalCount = 0;
			try {

				String mchtCode=request.getParameter("mchtCode");
				String mchtName=request.getParameter("mchtName");
				String productTypeId=request.getParameter("productTypeId");
				String platContactStaffId=request.getParameter("platContactStaffId");
				String productBrandName=request.getParameter("productBrandName");
				
				paramMap.put("mchtCode",mchtCode);
				paramMap.put("mchtName",mchtName);
				paramMap.put("productTypeId",productTypeId);
				paramMap.put("platContactStaffId",platContactStaffId);
				paramMap.put("productBrandName",productBrandName);
				
				//当主营类目、对接人为空时，限制搜索范围为： 本人负责的类目 与 本人对接的商家 并集
				if(StringUtil.isEmpty(request.getParameter("productTypeId")) && StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {
				   paramMap.put("productTypeContactIsNull", this.getSessionStaffBean(request).getStaffID());
				 }
							
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				
				String paydateDate1 = request.getParameter("endPaydateDate");
				
				if (!StringUtils.isEmpty(paydateDate1)) {
				    Date paydatedate1 = df.parse(paydateDate1);
				
				paramMap.put("paydateDate1", paydateDate1+" 00:00:00");
				paramMap.put("paydateDate1o", paydateDate1+" 23:59:59");
				
				paramMap.put("endPaydatedate", paydateDate1+" 23:59:59");
		
				Calendar c2= Calendar.getInstance();
				c2.setTime(paydatedate1);
				c2.add(Calendar.DATE, -1);
				paramMap.put("paydateDate2",df.format(c2.getTime())+" 00:00:00");
				paramMap.put("paydateDate2t",df.format(c2.getTime())+" 23:59:59"); 
			
				
				Calendar c3= Calendar.getInstance();
				c3.setTime(paydatedate1);
				c3.add(Calendar.DATE, -2);
				paramMap.put("paydateDate3", df.format(c3.getTime())+" 00:00:00");
				paramMap.put("paydateDate3s",df.format(c3.getTime())+" 23:59:59");
				
				Calendar c4= Calendar.getInstance();
				c4.setTime(paydatedate1);
				c4.add(Calendar.DATE, -3);
				paramMap.put("paydateDate4",df.format(c4.getTime())+" 00:00:00");
				paramMap.put("paydateDate4f",df.format(c4.getTime())+" 23:59:59");
				
				Calendar c5 = Calendar.getInstance();
				c5.setTime(paydatedate1);
				c5.add(Calendar.DATE, -4);
				paramMap.put("paydateDate5", df.format(c5.getTime())+" 00:00:00");
				paramMap.put("paydateDate5fi", df.format(c5.getTime())+" 23:59:59");
			
				Calendar c6 = Calendar.getInstance();
				c6.setTime(paydatedate1);
				c6.add(Calendar.DATE, -5);
				paramMap.put("paydateDate6", df.format(c6.getTime())+" 00:00:00");
				paramMap.put("paydateDate6sx", df.format(c6.getTime())+" 23:59:59");
				
				Calendar c7 = Calendar.getInstance();
				c7.setTime(paydatedate1);
				c7.add(Calendar.DATE, -6);
				paramMap.put("paydateDate7", df.format(c7.getTime())+" 00:00:00");
				paramMap.put("paydateDate7se", df.format(c7.getTime())+" 23:59:59");
				
				paramMap.put("startPaydatedate", df.format(c7.getTime())+" 00:00:00");
				
				}
			    
				paramMap.put("listSpecMchtCode", commonService.listSpecMchtCode());
				
				paramMap.put("MIN_NUM", page.getLimitStart());
				paramMap.put("MAX_NUM", page.getLimitSize());
				
				dataList=productBrandService.selectMchtInfoBrandCustomList(paramMap);	  			
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
			return resMap;

		}
	
}
