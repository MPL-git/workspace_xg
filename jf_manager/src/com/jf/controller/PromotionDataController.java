package com.jf.controller;
import com.gzs.common.utils.StringUtil;
import com.jf.common.constant.Const;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.*;
import com.jf.service.*;
import com.jf.vo.Page;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/promotionData")
public class PromotionDataController extends BaseController {
	
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
	
	@Autowired
	private SubOrderReportService subOrderReportService;
	
	@Autowired
	private SysPaymentService sysPaymentService;
	
	@Autowired
	private TrackDataService trackDataService;
	
	@Autowired
	private SpreadActivityGroupService spreadActivityGroupService;	
	
	@Autowired
	private SpreadActivityGroupSetService spreadActivityGroupSetService;	
	
	@Resource
	private AndroidChannelGroupService androidChannelGroupService;

	@Autowired
	private SpreadActivityPermissionService spreadActivityPermissionService;
	
	@Autowired
	private CommonService commonService;

	@Autowired
	private AndroidChannelGroupSetService androidChannelGroupSetService;

	@Autowired
	private AndroidChannelGroupSetDtlService androidChannelGroupSetDtlService;

	@Autowired
	private SysStatusService sysStatusService;

	private static final long serialVersionUID = 1L;
	
	/**
	 * 订单统计列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/orderCount/list.shtml")
	public ModelAndView list(HttpServletRequest request) {
		String rtPage = "/dataCenter/promotionData/orderCountList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateEnd = df.format(new Date());
		String dateBegin = dateEnd.substring(0,7)+"-01";
		resMap.put("order_date_end", dateEnd);
		resMap.put("order_date_begin", dateBegin);
		ProductTypeExample example = new ProductTypeExample();
		ProductTypeExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0").andTLevelEqualTo(1);
		
		//钟表运营部状态，只获取主营类目为钟表 
		String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
				criteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
			}
		}
		resMap.put("isCwOrgStatus", isCwOrgStatus);
		
		List<ProductType> firstProductTypes = productTypeService.selectByExample(example);
		resMap.put("firstProductTypes", firstProductTypes);
		
		List<SysStatus> sprChnls = DataDicUtil.getStatusList("BU_MEMBER_INFO", "SPR_CHNL");
		resMap.put("sprChnls", sprChnls);
		SysPaymentExample sysPaymentExample = new SysPaymentExample();
		SysPaymentExample.Criteria sysPaymentCriteria = sysPaymentExample.createCriteria();
		sysPaymentCriteria.andDelFlagEqualTo("0");
		sysPaymentCriteria.andIsEffectEqualTo("1");
		List<SysPayment> sysPayments = sysPaymentService.selectByExample(sysPaymentExample);
		resMap.put("payments", sysPayments);
		
		/*查询渠道集合*/
		AndroidChannelGroupExample androidChannelGroupExample = new AndroidChannelGroupExample();
		androidChannelGroupExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		androidChannelGroupExample.setOrderByClause(" type, IFNULL(seq_no, 999999), id");
		List<AndroidChannelGroup> androidChannelGroups = androidChannelGroupService.selectByExample(androidChannelGroupExample);
		resMap.put("androidChannelGroups", androidChannelGroups);
		
/*		List<String> mobileBrands = memberInfoService.findMobileBrandList();
		resMap.put("mobileBrands", mobileBrands);*/
		List<SysStatus> sourceClients = DataDicUtil.getStatusList("BU_COMBINE_ORDER", "SOURCE_CLIENT");
		resMap.put("sourceClients", sourceClients);
		SpreadActivityGroupExample spreadActivityGroupExample = new SpreadActivityGroupExample();
		spreadActivityGroupExample.createCriteria().andDelFlagEqualTo("0");
		spreadActivityGroupExample.setOrderByClause(" IFNULL(t.seq_no, 99999999999) asc, t.create_date asc");
		List<SpreadActivityGroupCustom> spreadActivityGroupCustoms = spreadActivityGroupService.selectSpreadActivityGroupCustomByExample(spreadActivityGroupExample);
		resMap.put("spreadActivityGroupCustoms", spreadActivityGroupCustoms);
		if(StringUtils.isEmpty(request.getParameter("searchType"))){
			resMap.put("searchType", "1");
		}else{
			resMap.put("searchType", request.getParameter("searchType"));
		}
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 订单统计列表数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/orderCount/data.shtml")
	@ResponseBody
	public Map<String, Object> data(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String orderDateBegin = request.getParameter("order_date_begin")+" 00:00:00";
			String orderDateEnd = request.getParameter("order_date_end")+" 23:59:59";
			String sprChnl = request.getParameter("sprChnl");
			String mchtCode = request.getParameter("mchtCode");
			String province = request.getParameter("province");
			String city = request.getParameter("city");
			String status = request.getParameter("status");
			String sysPayment = request.getParameter("sysPayment");
			String brandName = request.getParameter("brandName");
			String firstProductTypeId = request.getParameter("firstProductTypeId");
			String secondProductTypeId = request.getParameter("secondProductTypeId");
			String activityId = request.getParameter("activityId");
			String activityAreaId = request.getParameter("activityAreaId");
			String reqMobileBrand = request.getParameter("reqMobileBrand");
			String sourceClient = request.getParameter("sourceClient");
			String consumePeople = request.getParameter("consumePeople");
			String searchType = request.getParameter("searchType");
			String channel = request.getParameter("channel");
			String spreadname = request.getParameter("spreadname");
			String spreadActivityGroupId = request.getParameter("spreadActivityGroupId");
			String regClient = request.getParameter("regClient");
			String visitType = request.getParameter("visitType");
			
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			if(!StringUtil.isEmpty(request.getParameter("order_date_begin"))){
				paramMap.put("orderDateBegin",orderDateBegin);
			}else{
				orderDateEnd = df.format(new Date());
				orderDateBegin = orderDateEnd.substring(0,7)+"-01 00:00:00";
				paramMap.put("orderDateBegin",orderDateBegin);
			}
			if(!StringUtil.isEmpty(request.getParameter("order_date_end"))){
				paramMap.put("orderDateEnd",orderDateEnd);
			}else{
				orderDateEnd = df.format(new Date());
				paramMap.put("orderDateEnd",orderDateEnd);
			}
			if(!StringUtil.isEmpty(sprChnl)){
				
				Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
				if (pattern.matcher(sprChnl).matches())
				{
					paramMap.put("sprChnl",sprChnl);
				}else{
					paramMap.put("sprChnlName",sprChnl+"%");
				}
				
			}
			if(!StringUtil.isEmpty(mchtCode)){
				paramMap.put("mchtCode",mchtCode);
			}
			if(!StringUtil.isEmpty(province)){
				paramMap.put("province",province);
			}
			if(!StringUtil.isEmpty(city)){
				paramMap.put("city",city);
			}
			if(!StringUtil.isEmpty(status)){
				paramMap.put("status",status);
			}
			if(!StringUtil.isEmpty(sysPayment)){
				paramMap.put("sysPayment",Integer.parseInt(sysPayment));
			}
			if(!StringUtil.isEmpty(brandName)){
				paramMap.put("brandName",brandName);
			}
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					paramMap.put("firstProductTypeId",Integer.parseInt(isCwOrgProductTypeId));
				}
			}else {
				if(!StringUtil.isEmpty(firstProductTypeId)){
					paramMap.put("firstProductTypeId",Integer.parseInt(firstProductTypeId));
				}
			}
			
			if(!StringUtil.isEmpty(secondProductTypeId)){
				paramMap.put("secondProductTypeId",Integer.parseInt(secondProductTypeId));
			}
			if(!StringUtil.isEmpty(activityId)){
				paramMap.put("activityId",Integer.parseInt(activityId));
			}
			if(!StringUtil.isEmpty(activityAreaId)){
				paramMap.put("activityAreaId",Integer.parseInt(activityAreaId));
			}
			if(!StringUtil.isEmpty(reqMobileBrand)){
				paramMap.put("reqMobileBrand",reqMobileBrand);
			}
			if(!StringUtil.isEmpty(sourceClient)){
				paramMap.put("sourceClient",sourceClient);
			}
			if(!StringUtil.isEmpty(consumePeople)){
				paramMap.put("consumePeople",consumePeople);
			}
			if(!StringUtil.isEmpty(searchType)){
				paramMap.put("searchType",searchType);
			}
			if(!StringUtil.isEmpty(spreadname)) {
				paramMap.put("spreadname",spreadname);
			}
			if(!StringUtil.isEmpty(channel)) {
				paramMap.put("channel",channel);
			}
			if(!StringUtil.isEmpty(spreadActivityGroupId)) {
				paramMap.put("spreadActivityGroupId",spreadActivityGroupId);
			}
			if(!StringUtil.isEmpty(regClient)) {
				paramMap.put("regClient",regClient);
			}
			if(!StringUtil.isEmpty(visitType)) {
				paramMap.put("visitType",visitType);
			}
			/*paramMap.put("appointMchtCode",1);*/
			paramMap.put("listSpecMchtCode", commonService.listSpecMchtCode());
			List<OrderDtlCustom> orderDtlCustoms = new ArrayList<OrderDtlCustom>();
			OrderDtlCustom odc = new OrderDtlCustom();
			List<OrderDtlCustom> orderDtlCustomList = orderDtlService.totalOrderCount(paramMap);
			if(orderDtlCustomList!=null && orderDtlCustomList.size()>0){
				odc = orderDtlCustomList.get(0);
				odc.setEachDay("合计");
				odc.setEachHour("合计");
				odc.setMobileBrand("合计");
			}
			if(searchType.equals("1")){//按每天
				orderDtlCustoms = orderDtlService.orderCount(paramMap);
				HashMap<String, OrderDtlCustom> map = new HashMap<String, OrderDtlCustom>();
				List<String> containDays = new ArrayList<String>();
				for(OrderDtlCustom orderDtlCustom:orderDtlCustoms){
					containDays.add(orderDtlCustom.getEachDay());
					map.put(orderDtlCustom.getEachDay(), orderDtlCustom);
				}
				List<String> betweenDays = this.getBetweenDays(orderDateBegin, orderDateEnd);
				for(int i= 0;i<betweenDays.size();i++){
					if(!containDays.contains(betweenDays.get(i))){
						OrderDtlCustom orderDtlCustom = new OrderDtlCustom();
						orderDtlCustom.setEachDay(betweenDays.get(i));
						orderDtlCustom.setOrderCount(0);
						orderDtlCustom.setSubOrderCount(0);
						orderDtlCustom.setConsumeCount(0);
						orderDtlCustom.setConversionRate(0.00F);
						orderDtlCustom.setProductSalePrice(new BigDecimal(0));
						orderDtlCustom.setTotalPlatformPreferential(new BigDecimal(0));
						orderDtlCustom.setTotalIntegralPreferential(new BigDecimal(0));
						orderDtlCustom.setTotalMchtPreferential(new BigDecimal(0));
						orderDtlCustom.setTotalPayAmount(new BigDecimal(0));
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
			}else if(searchType.equals("2")){//按小时
				orderDtlCustoms = orderDtlService.orderCount(paramMap);
				HashMap<String, OrderDtlCustom> map = new HashMap<String, OrderDtlCustom>();
				List<String> betweenHours = new ArrayList<String>();
				betweenHours.add("00");
				betweenHours.add("01");
				betweenHours.add("02");
				betweenHours.add("03");
				betweenHours.add("04");
				betweenHours.add("05");
				betweenHours.add("06");
				betweenHours.add("07");
				betweenHours.add("08");
				betweenHours.add("09");
				betweenHours.add("10");
				betweenHours.add("11");
				betweenHours.add("12");
				betweenHours.add("13");
				betweenHours.add("14");
				betweenHours.add("15");
				betweenHours.add("16");
				betweenHours.add("17");
				betweenHours.add("18");
				betweenHours.add("19");
				betweenHours.add("20");
				betweenHours.add("21");
				betweenHours.add("22");
				betweenHours.add("23");
				List<String> containHours = new ArrayList<String>();
				for(OrderDtlCustom orderDtlCustom:orderDtlCustoms){
					containHours.add(orderDtlCustom.getEachHour());
					map.put(orderDtlCustom.getEachDay(), orderDtlCustom);
				}
				for(int i= 0;i<betweenHours.size();i++){
					if(!containHours.contains(betweenHours.get(i))){
						OrderDtlCustom orderDtlCustom = new OrderDtlCustom();
						orderDtlCustom.setEachHour(betweenHours.get(i));
						orderDtlCustom.setOrderCount(0);
						orderDtlCustom.setSubOrderCount(0);
						orderDtlCustom.setConsumeCount(0);
						orderDtlCustom.setConversionRate(0.00F);
						orderDtlCustom.setProductSalePrice(new BigDecimal(0));
						orderDtlCustom.setTotalPlatformPreferential(new BigDecimal(0));
						orderDtlCustom.setTotalIntegralPreferential(new BigDecimal(0));
						orderDtlCustom.setTotalMchtPreferential(new BigDecimal(0));
						orderDtlCustom.setTotalPayAmount(new BigDecimal(0));
						orderDtlCustoms.add(orderDtlCustom);
						map.put(betweenHours.get(i), orderDtlCustom);
					}
				}
				Collections.sort(orderDtlCustoms, new Comparator<OrderDtlCustom>() {
		            @Override
		            public int compare(OrderDtlCustom c1, OrderDtlCustom c2) {
		                //升序
		                return c1.getEachHour().compareTo(c2.getEachHour());
		            }
		        });
			}else if(searchType.equals("3")){//按手机品牌
				orderDtlCustoms = orderDtlService.orderCount(paramMap);
			}
			orderDtlCustoms.add(0, odc);
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
	
	@RequestMapping(value = "/orderCount/MD5.shtml")
	public ModelAndView MD5(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/promotionData/MD5");
		return m;
	}
	
	/**
	 * 
	 * @Title trackDataManager   
	 * @Description TODO(导入推广数据管理)   
	 * @author pengl
	 * @date 2017年12月1日 下午1:52:07
	 */
	@RequestMapping(value = "/orderCount/trackDataManager.shtml")
	public ModelAndView trackDataManager(HttpServletRequest request, String flag) {
		ModelAndView m = new ModelAndView();
		if(flag.equals("1")) { 
			m.setViewName("/dataCenter/promotionData/trackDataList");
		}else if(flag.equals("2")) {
			m.setViewName("/dataCenter/promotionData/updateOrSaveTrackDatas");
			m.addObject("status", request.getParameter("status"));
		}
		return m;
	}
	
	/**
	 * 
	 * @Title getTrackDataList   
	 * @Description TODO(推广数据列表)   
	 * @author pengl
	 * @date 2017年12月1日 下午2:10:04
	 */
	@ResponseBody
	@RequestMapping(value = "/orderCount/getTrackDataList.shtml")
	public Map<String, Object> getTrackDataList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		Integer totalCount = 0;
		try {
			TrackDataExample trackDataExample = new TrackDataExample();
			TrackDataExample.Criteria trackDataCriteria = trackDataExample.createCriteria();
			trackDataCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("spreadname"))) { //推广活动名称
				trackDataCriteria.andSpreadnameEqualTo(request.getParameter("spreadname"));
			}
			if(!StringUtil.isEmpty(request.getParameter("activityname"))) { //活动组
				trackDataCriteria.andActivitynameEqualTo(request.getParameter("activityname"));
			}
			if(!StringUtil.isEmpty(request.getParameter("channel"))) { //渠道名
				trackDataCriteria.andChannelEqualTo(request.getParameter("channel"));
			}
			trackDataExample.setOrderByClause(" id desc");
			trackDataExample.setLimitSize(page.getLimitSize());
			trackDataExample.setLimitStart(page.getLimitStart());
			dataList = trackDataService.selectByExample(trackDataExample);
			totalCount = trackDataService.countByExample(trackDataExample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
			totalCount = 0;
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title updateOrSaveTrackDatas   
	 * @Description TODO(导入推广数据)   
	 * @author pengl
	 * @date 2017年12月1日 下午2:15:15
	 */
	@RequestMapping(value = "/orderCount/updateOrSaveTrackDatas.shtml")
	public ModelAndView updateOrSaveTrackDatas(HttpServletRequest request, String status, MultipartFile excelFile) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = null;
		String msg =null;
		TrackData trackData = null;
		List<TrackData> trackDataList = null;
		StaffBean staffBean = this.getSessionStaffBean(request);
		Integer staffId = Integer.valueOf(staffBean.getStaffID());
		try {
			// 构造 Workbook 对象
			Workbook book = null;
			//创建Excel工作薄   
			try {  
                // Excel 2007获取方法
                book = new XSSFWorkbook(excelFile.getInputStream());  
            } catch (Exception ex) {  
                // Excel 2003获取方法  
                book = new HSSFWorkbook(excelFile.getInputStream());  
            }
			//得到第一个工作表   
			Sheet sheet = book.getSheetAt(0);  
            Row row = null;  
            //遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数    
//            for(int i = 0; i < book.getNumberOfSheets(); i++) {
            	trackDataList = new ArrayList<TrackData>();
//                sheet = book.getSheetAt(i); 
                for(int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {  
                	if(sheet.getPhysicalNumberOfRows() <= 5000) {
                		row = sheet.getRow(j);
        				if(status.equals("IOS")) { //ios
        					if(j != 0) {
        						if(row != null && !"".equals(row)) {
            						if(row.getCell(0) != null && !"".equals(row.getCell(0)) 
            								&& row.getCell(0).toString().length() == 36 
            								&& !row.getCell(0).equals("00000000-0000-0000-0000-000000000000") ) {
            							trackData = new TrackData();
            							trackData.setIdfa(row.getCell(0).toString().trim());
            							trackData.setActivityname(row.getCell(1)==null?"":row.getCell(1).toString().trim());
            							trackDataList.add(trackData);
            						}
        						}
        					}else {
        						if(row == null || row.equals("") || row.getCell(0) == null || !row.getCell(0).toString().trim().equals("idfa") 
        								|| row.getCell(1) == null || !row.getCell(1).toString().trim().equals("activityname") )
        							throw new NumberFormatException("导入文件模版错误");
        					}
        				}else if(status.equals("ANDROID")) { //Android
        					if(j != 0) {
        						if(row != null && !"".equals(row)) {
            						if(row.getCell(0) != null && !"".equals(row.getCell(0)) ) {
            							trackData = new TrackData();
            							if(isNumeric(row.getCell(0).toString())) {
            								trackData.setImei(new BigDecimal(row.getCell(0).toString()).toString());
            							}else {
            								trackData.setImei(row.getCell(0).toString().trim());
            							}
            							trackData.setDevicetype(row.getCell(1)==null?"":row.getCell(1).toString().trim());
            							trackData.setSpreadname(row.getCell(2)==null?"":row.getCell(2).toString().trim());
            							trackData.setChannel(row.getCell(3)==null?"":row.getCell(3).toString().trim());
            							trackData.setActivityname(row.getCell(4)==null?"":row.getCell(4).toString().trim());
            							trackData.setSpreadurl(row.getCell(5)==null?"":row.getCell(5).toString().trim());
            							trackData.setUip(row.getCell(6)==null?"":row.getCell(6).toString().trim());
            							trackDataList.add(trackData);
            						}
        						}
        					}else {
        						if(row == null || row.equals("") || row.getCell(0) == null || !row.getCell(0).toString().trim().equals("imei") 
        								|| row.getCell(1) == null || !row.getCell(1).toString().trim().equals("devicetype")
        								|| row.getCell(2) == null || !row.getCell(2).toString().trim().equals("spreadname")
        								|| row.getCell(3) == null || !row.getCell(3).toString().trim().equals("channel")
        								|| row.getCell(4) == null || !row.getCell(4).toString().trim().equals("activityname") 
        								|| row.getCell(5) == null || !row.getCell(5).toString().trim().equals("spreadurl")
        								|| row.getCell(6) == null || !row.getCell(6).toString().trim().equals("uip") )
        							throw new NumberFormatException("导入文件模版错误");
        					}
        				}
                	}else {
                		throw new NumberFormatException("导入文件超过五千行");
                	}
                }
                Map<String, Object> map = trackDataService.updateOrInsertTrackData(request, trackDataList, staffId, status);
                if(map != null) {
                	if(status.equals("IOS")) {
                		msg = "操作成功，修改 "+map.get("updateNum")+" 条数据。";
                	}else if(status.equals("ANDROID")) {
                		msg = "操作成功，新增 "+map.get("insertNum")+" 条数据，已存在 "+map.get("updateNum")+" 条数据。";
                	}else {
                		msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
                	}
                }else {
                	msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
                }
//            }
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			if(e.getMessage().equals("导入文件超过五千行") || e.getMessage().equals("导入文件模版错误")) {
				msg = e.getMessage();
			}else {
				msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
			}
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	public boolean isNumeric(String str) {
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }

        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
	
	/**
	 * 安卓手机统计列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/androidCount/list.shtml")
	public ModelAndView androidCountList(HttpServletRequest request) {
		String rtPage = "/dataCenter/promotionData/androidCountList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateBegin = df.format(new Date());
		String dateEnd = dateBegin;
		resMap.put("order_date_begin", dateBegin);
		resMap.put("order_date_end", dateEnd);
		/*查询渠道集合*/
		SpreadActivityPermissionExample spreadActivityPermissionExample = new SpreadActivityPermissionExample();
		spreadActivityPermissionExample.createCriteria().andDelFlagEqualTo("0")
			.andStatusEqualTo("1").andTypeEqualTo("3")
			.andStaffInfoIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SpreadActivityPermission> spreadActivityPermissionList = spreadActivityPermissionService.selectByExample(spreadActivityPermissionExample);
		List<Integer> spreadIdList = new ArrayList<Integer>();
		for(SpreadActivityPermission spreadActivityPermission : spreadActivityPermissionList ) {
			spreadIdList.add(spreadActivityPermission.getSpreadId());
		}
		List<AndroidChannelGroup> androidChannelGroups = null;
		if(spreadIdList.size() > 0 ) {
			AndroidChannelGroupExample androidChannelGroupExample = new AndroidChannelGroupExample();
			androidChannelGroupExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(spreadIdList);
			androidChannelGroupExample.setOrderByClause(" type asc, IFNULL(seq_no, 999999) asc, id desc");
			androidChannelGroups = androidChannelGroupService.selectByExample(androidChannelGroupExample);
		}
		resMap.put("androidChannelGroups", androidChannelGroups);

		SpreadActivityPermissionExample groupSetPermissionExample = new SpreadActivityPermissionExample();
		groupSetPermissionExample.createCriteria().andDelFlagEqualTo("0")
				.andStatusEqualTo("1").andTypeEqualTo("4")
				.andStaffInfoIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SpreadActivityPermission> groupSetPermissionList = spreadActivityPermissionService.selectByExample(groupSetPermissionExample);
		List<Integer> spreadActivityGroupSetIdList = new ArrayList<Integer>();
		for(SpreadActivityPermission spreadActivityPermission : groupSetPermissionList ) {
			spreadActivityGroupSetIdList.add(spreadActivityPermission.getSpreadId());
		}
		List<AndroidChannelGroupSet> androidChannelGroupSetList = null;
		if(spreadActivityGroupSetIdList.size() > 0 ) {
			AndroidChannelGroupSetExample androidChannelGroupSetExample = new AndroidChannelGroupSetExample();
			androidChannelGroupSetExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(spreadActivityGroupSetIdList);
			androidChannelGroupSetExample.setOrderByClause(" ifnull(seq_no, 999999) asc, id desc");
			androidChannelGroupSetList = androidChannelGroupSetService.selectByExample(androidChannelGroupSetExample);
		}
		resMap.put("androidChannelGroupSetList", androidChannelGroupSetList);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 订单统计列表数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/androidCount/data.shtml")
	@ResponseBody
	public Map<String, Object> androidCountData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			boolean sprChnlStatus = false;
			String orderDateBegin = request.getParameter("order_date_begin");
			String orderDateEnd = request.getParameter("order_date_end");
			String sprChnl = request.getParameter("sprChnl");
			String visitType = request.getParameter("visitType");//访客类型
			if(StringUtil.isEmpty(orderDateBegin) || StringUtil.isEmpty(orderDateEnd) || StringUtil.isEmpty(sprChnl)){
				resMap.put("Rows", new ArrayList<HashMap<String, Object>>());
				return resMap;
			}
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("orderDateBegin",orderDateBegin+" 00:00:00");
			paramMap.put("orderDateEnd",orderDateEnd+" 23:59:59");
			//新增访客类型
			if(!StringUtil.isEmpty(visitType) ) {
				paramMap.put("visitType",visitType);
			}
			if(!StringUtil.isEmpty(sprChnl)){
				String[] sprChnlStr = sprChnl.split("\\|");
				if("1".equals(sprChnlStr[0]) ) {
					paramMap.put("sprChnlName", sprChnlStr[1]+"%");
					sprChnlStatus = true;
				}else {
					AndroidChannelGroupSetDtlCustomExample androidChannelGroupSetDtlCustomExample = new AndroidChannelGroupSetDtlCustomExample();
					AndroidChannelGroupSetDtlCustomExample.AndroidChannelGroupSetDtlCustomCriteria androidChannelGroupSetDtlCustomCriteria = androidChannelGroupSetDtlCustomExample.createCriteria();
					androidChannelGroupSetDtlCustomCriteria.andDelFlagEqualTo("0")
							.andAndroidChannelGroupSetIdEqualTo(Integer.parseInt(sprChnlStr[1]));
					androidChannelGroupSetDtlCustomCriteria.andGroupStatusEqualTo("1");
					List<AndroidChannelGroupSetDtlCustom> androidChannelGroupSetDtlCustomList = androidChannelGroupSetDtlService.selectByCustomExample(androidChannelGroupSetDtlCustomExample);
					List<String> groupPrefixList = new ArrayList<>();
					for (AndroidChannelGroupSetDtlCustom androidChannelGroupSetDtlCustom : androidChannelGroupSetDtlCustomList) {
						groupPrefixList.add(androidChannelGroupSetDtlCustom.getGroupPrefix()+"%");
					}
					paramMap.put("groupPrefixList", groupPrefixList);
					sprChnlStatus = true;
				}
			}
			List<HashMap<String, Object>> eachSprChnlList = new ArrayList<HashMap<String, Object>>();
			if(sprChnlStatus) {
				eachSprChnlList = combineOrderService.androidCount(paramMap);
				List<HashMap<String, Object>> list = combineOrderService.totalAndroidCount(paramMap);
				eachSprChnlList.addAll(list);
			}
			resMap.put("Rows", eachSprChnlList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * IOS手机统计列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/iosCount/list.shtml")
	public ModelAndView iosCountList(HttpServletRequest request) {
		String rtPage = "/dataCenter/promotionData/iosCountList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SysStatus> channelList = DataDicUtil.getStatusList("BU_SPREAD_ACTIVITY_GROUP", "CHANNEL");
		resMap.put("channelList", channelList);
		
		SpreadActivityPermissionExample groupPermissionExample = new SpreadActivityPermissionExample();
		groupPermissionExample.createCriteria().andDelFlagEqualTo("0")
			.andStatusEqualTo("1").andTypeEqualTo("1")
			.andStaffInfoIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SpreadActivityPermission> groupPermissionList = spreadActivityPermissionService.selectByExample(groupPermissionExample);
		List<Integer> spreadActivityGroupIdList = new ArrayList<Integer>();
		for(SpreadActivityPermission spreadActivityPermission : groupPermissionList ) {
			spreadActivityGroupIdList.add(spreadActivityPermission.getSpreadId());
		}
		List<SpreadActivityGroupCustom> spreadActivityGroupList = null;
		if(spreadActivityGroupIdList.size() > 0) {
			SpreadActivityGroupExample spreadActivityGroupExample = new SpreadActivityGroupExample();
			SpreadActivityGroupExample.Criteria secondProductTypesCriteria = spreadActivityGroupExample.createCriteria();
			secondProductTypesCriteria.andDelFlagEqualTo("0").andIsEffectEqualTo("1")
					.andIdIn(spreadActivityGroupIdList).andDeviceTypeEqualTo(Const.DEVICE_TYPE_IOS);
			spreadActivityGroupExample.setOrderByClause(" IFNULL(t.seq_no, 99999999999) asc, t.create_date asc");
			spreadActivityGroupList = spreadActivityGroupService.selectSpreadActivityGroupCustomByExample(spreadActivityGroupExample);
		}
		resMap.put("spreadActivityGroupList", spreadActivityGroupList);
		
		SpreadActivityPermissionExample groupSetPermissionExample = new SpreadActivityPermissionExample();
		groupSetPermissionExample.createCriteria().andDelFlagEqualTo("0")
			.andStatusEqualTo("1").andTypeEqualTo("2")
			.andStaffInfoIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SpreadActivityPermission> groupSetPermissionList = spreadActivityPermissionService.selectByExample(groupSetPermissionExample);
		List<Integer> spreadActivityGroupSetIdList = new ArrayList<Integer>();
		for(SpreadActivityPermission spreadActivityPermission : groupSetPermissionList ) {
			spreadActivityGroupSetIdList.add(spreadActivityPermission.getSpreadId());
		}
		List<SpreadActivityGroupSetCustom> spreadActivityGroupSetCustomList = null;
		if(spreadActivityGroupSetIdList.size() > 0 ) {
			SpreadActivityGroupSetCustomExample spreadActivityGroupSetCustomExample = new SpreadActivityGroupSetCustomExample();
			SpreadActivityGroupSetCustomExample.Criteria spreadActivityGroupSetCustomCriteria = spreadActivityGroupSetCustomExample.createCriteria();
			spreadActivityGroupSetCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1")
					.andIdIn(spreadActivityGroupSetIdList).andDeviceTypeEqualTo(Const.DEVICE_TYPE_IOS);
			spreadActivityGroupSetCustomExample.setOrderByClause(" ifnull(t.seq_no, 999999999), t.id desc");
			spreadActivityGroupSetCustomList = spreadActivityGroupSetService.selectByCustomExample(spreadActivityGroupSetCustomExample);
		}
		resMap.put("spreadActivityGroupSetCustomList", spreadActivityGroupSetCustomList);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateBegin = df.format(new Date());
		String dateEnd = dateBegin;
		resMap.put("order_date_begin", dateBegin);
		resMap.put("order_date_end", dateEnd);
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * IOS统计列表数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/iosCount/data.shtml")
	@ResponseBody
	public Map<String, Object> iosCountData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String orderDateBegin = request.getParameter("order_date_begin");
			String orderDateEnd = request.getParameter("order_date_end");
			String channel = request.getParameter("channel");
			String activityGroupId = request.getParameter("activityGroupId");
			String spreadName = request.getParameter("spreadName");
			String visitType = request.getParameter("visitType");
			if(StringUtil.isEmpty(orderDateBegin) || StringUtil.isEmpty(orderDateEnd) || StringUtil.isEmpty(channel)){
				resMap.put("Rows", new ArrayList<HashMap<String, Object>>());
				return resMap;
			}
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("orderDateBegin",orderDateBegin+" 00:00:00");
			paramMap.put("orderDateEnd",orderDateEnd+" 23:59:59");
			paramMap.put("channel",channel);
			if(!StringUtil.isEmpty(activityGroupId)) {
				if(activityGroupId.contains("A") ) {
					paramMap.put("activityGroupId",Integer.parseInt(activityGroupId.split("-")[1]));
				}else if(activityGroupId.contains("B") ) {
					paramMap.put("activityGroupSetId",Integer.parseInt(activityGroupId.split("-")[1]));
				}
			}
			if(!StringUtil.isEmpty(spreadName)) {
				paramMap.put("spreadName",spreadName);
			}
			if(!StringUtil.isEmpty(visitType) ) {
				paramMap.put("visitType",visitType);
			}
//			落地页关于热云回调的数据来源类型区分  --是根据渠道名称关联的 类型可暂不判断
//			if ("6".equals(channel)){
//				paramMap.put("type","2");
//			}else {
//				paramMap.put("type","1");
//			}
//			if (Objects.equals("6",channel)){
//
//			}
			paramMap.put("deviceType",Const.DEVICE_TYPE_IOS);
			List<HashMap<String, Object>> eachSprChnlList = combineOrderService.iosCount(paramMap);
			List<HashMap<String, Object>> list = combineOrderService.totalIosCount(paramMap);
			eachSprChnlList.addAll(list);
			resMap.put("Rows", eachSprChnlList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title androidCountNewList   
	 * @Description TODO(安卓手机统计（新）)   
	 * @author pengl
	 * @date 2019年2月12日 下午2:33:29
	 */
	@RequestMapping(value = "/androidCountNew/list.shtml")
	public ModelAndView androidCountNewList(HttpServletRequest request) {
		String rtPage = "/dataCenter/promotionData/androidCountNewList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		/*查询渠道集合*/
		SpreadActivityPermissionExample spreadActivityPermissionExample = new SpreadActivityPermissionExample();
		spreadActivityPermissionExample.createCriteria().andDelFlagEqualTo("0")
			.andStatusEqualTo("1").andTypeEqualTo("3")
			.andStaffInfoIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SpreadActivityPermission> spreadActivityPermissionList = spreadActivityPermissionService.selectByExample(spreadActivityPermissionExample);
		List<Integer> spreadIdList = new ArrayList<Integer>();
		for(SpreadActivityPermission spreadActivityPermission : spreadActivityPermissionList ) {
			spreadIdList.add(spreadActivityPermission.getSpreadId());
		}
		List<AndroidChannelGroup> androidChannelGroups = null;
		if(spreadIdList.size() > 0 ) {
			AndroidChannelGroupExample androidChannelGroupExample = new AndroidChannelGroupExample();
			androidChannelGroupExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(spreadIdList);
			androidChannelGroupExample.setOrderByClause(" type, IFNULL(seq_no, 999999) asc, id desc");
			androidChannelGroups = androidChannelGroupService.selectByExample(androidChannelGroupExample);
		}
		resMap.put("androidChannelGroups", androidChannelGroups);

		SpreadActivityPermissionExample groupSetPermissionExample = new SpreadActivityPermissionExample();
		groupSetPermissionExample.createCriteria().andDelFlagEqualTo("0")
				.andStatusEqualTo("1").andTypeEqualTo("4")
				.andStaffInfoIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SpreadActivityPermission> groupSetPermissionList = spreadActivityPermissionService.selectByExample(groupSetPermissionExample);
		List<Integer> spreadActivityGroupSetIdList = new ArrayList<Integer>();
		for(SpreadActivityPermission spreadActivityPermission : groupSetPermissionList ) {
			spreadActivityGroupSetIdList.add(spreadActivityPermission.getSpreadId());
		}
		List<AndroidChannelGroupSet> androidChannelGroupSetList = null;
		if(spreadActivityGroupSetIdList.size() > 0 ) {
			AndroidChannelGroupSetExample androidChannelGroupSetExample = new AndroidChannelGroupSetExample();
			androidChannelGroupSetExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(spreadActivityGroupSetIdList);
			androidChannelGroupSetExample.setOrderByClause(" ifnull(seq_no, 999999) asc, id desc");
			androidChannelGroupSetList = androidChannelGroupSetService.selectByExample(androidChannelGroupSetExample);
		}
		resMap.put("androidChannelGroupSetList", androidChannelGroupSetList);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 
	 * @Title androidCountData   
	 * @Description TODO(安卓手机统计（新）数据)   
	 * @author pengl
	 * @date 2019年2月12日 下午2:35:11
	 */
	@ResponseBody
	@RequestMapping(value = "/androidCountNew/data.shtml")
	public Map<String, Object> androidCountDataNew(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String orderDateBegin = request.getParameter("order_date_begin");
			String orderDateEnd = request.getParameter("order_date_end");
			String sprChnl = request.getParameter("sprChnl");
            String visitType = request.getParameter("visitType");//访客类型
            String sprChnlFirst = "";
			String sprChnlEnd = "";
			List<String> groupPrefixList = new ArrayList<>();
			List<Integer> androidChannelGroupIdList = new ArrayList<>();
			if(!StringUtil.isEmpty(sprChnl) ) {
				String[] sprChnlStr = sprChnl.split(",");
				sprChnlFirst = sprChnlStr[0];
				if("1".equals(sprChnlFirst) ) {
					sprChnl = sprChnlStr[1];
				}else {
					sprChnlEnd = sprChnlStr[1].split("\\|")[1];
					AndroidChannelGroupSetDtlCustomExample androidChannelGroupSetDtlCustomExample = new AndroidChannelGroupSetDtlCustomExample();
					AndroidChannelGroupSetDtlCustomExample.AndroidChannelGroupSetDtlCustomCriteria androidChannelGroupSetDtlCustomCriteria = androidChannelGroupSetDtlCustomExample.createCriteria();
					androidChannelGroupSetDtlCustomCriteria.andDelFlagEqualTo("0")
							.andAndroidChannelGroupSetIdEqualTo(Integer.parseInt(sprChnlStr[1].split("\\|")[0]));
					androidChannelGroupSetDtlCustomCriteria.andGroupStatusEqualTo("1");
					List<AndroidChannelGroupSetDtlCustom> androidChannelGroupSetDtlCustomList = androidChannelGroupSetDtlService.selectByCustomExample(androidChannelGroupSetDtlCustomExample);
					for (AndroidChannelGroupSetDtlCustom androidChannelGroupSetDtlCustom : androidChannelGroupSetDtlCustomList) {
						groupPrefixList.add(androidChannelGroupSetDtlCustom.getGroupPrefix()+"%");
						androidChannelGroupIdList.add(androidChannelGroupSetDtlCustom.getAndroidChannelGroupId());
					}
				}
			}
			Double total_consumption= 0.0;
			Double actual_consumption=0.0;
			Integer conversion_quantity=0;
			Double single_active_cost=0.0;
			Integer new_guest_combine_order_count=0;
			Integer new_guest_consume_count=0;
			Double new_guest_cost=0.0;
			Double new_guest_pay_amount=0.0;
			Double new_unit_price=0.0;
			Integer old_guest_combine_order_count=0;
			Integer old_guest_consume_count=0;
			Double old_guest_pay_amount=0.0;
			Double old_unit_price=0.0;
			Double total_pay_amount=0.0;
			Double rate=0.0;
			Double roi=0.0;
			if(StringUtil.isEmpty(orderDateBegin) || StringUtil.isEmpty(orderDateEnd) || (StringUtil.isEmpty(sprChnl) && groupPrefixList.size() == 0) ){
				resMap.put("Rows", new ArrayList<HashMap<String, Object>>());
				return resMap;
			}
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("orderDateBegin",orderDateBegin+" 00:00:00");
			paramMap.put("orderDateEnd",orderDateEnd+" 23:59:59");
            if(!StringUtil.isEmpty(visitType) ) {//访客类型
                paramMap.put("visitType",visitType);
            }
			if(!StringUtil.isEmpty(sprChnl) && "1".equals(sprChnlFirst) ){
				String[] sprVar = sprChnl.split("\\|");
				paramMap.put("androidChannelGroupId", sprVar[0]);
				paramMap.put("sprChnlName", sprVar[1]+"%");
				paramMap.put("sprChnlNameDesc", sprVar[1]+"（集合）");
				paramMap.put("groupType", sprVar[2]);
			}else if(!StringUtil.isEmpty(sprChnl) && "2".equals(sprChnlFirst) ) {
				paramMap.put("androidChannelGroupIdList", androidChannelGroupIdList);
				paramMap.put("groupPrefixList", groupPrefixList);
				paramMap.put("sprChnlNameDesc", sprChnlEnd+"（组）");
				paramMap.put("groupType", "3");
			}
			List<HashMap<String, Object>> eachSprChnlList = combineOrderService.androidCountNew(paramMap);
//			List<HashMap<String, Object>> list = combineOrderService.totalAndroidCount(paramMap);
			List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
			
			List<HashMap<String, Object>> newSprChnlList=new ArrayList<HashMap<String,Object>>();
			SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
			Date date1 = formatter.parse(orderDateBegin);
			Date date2 = formatter.parse(orderDateEnd);
			
			if (eachSprChnlList==null || eachSprChnlList.size()==0){
				newSprChnlList = combineOrderService.androidCountNewNull(paramMap);
				for(int i=0;i<newSprChnlList.size();i++){
					Object tmpStr=null;
					tmpStr=newSprChnlList.get(i).get("total_consumption");
					if (tmpStr!=null){
						total_consumption+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=newSprChnlList.get(i).get("actual_consumption");
					if (tmpStr!=null){
						actual_consumption+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=newSprChnlList.get(i).get("conversion_quantity");
					if (tmpStr!=null){
						conversion_quantity+=Double.valueOf(tmpStr.toString()).intValue();
					}
					tmpStr=newSprChnlList.get(i).get("single_active_cost");
					if (tmpStr!=null){
						single_active_cost+=Double.valueOf(tmpStr.toString());
					}
				}
			}else{
				for(int i=0;i<eachSprChnlList.size();i++){
					Object tmpStr=null;
					tmpStr=eachSprChnlList.get(i).get("total_consumption");
					if (tmpStr!=null){
						total_consumption+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("actual_consumption");
					if (tmpStr!=null){
						actual_consumption+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("conversion_quantity");
					if (tmpStr!=null){
						conversion_quantity+=Integer.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("single_active_cost");
					if (tmpStr!=null){
						single_active_cost+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("new_guest_combine_order_count");
					if (tmpStr!=null){
						new_guest_combine_order_count+=Integer.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("new_guest_consume_count");
					if (tmpStr!=null){
						new_guest_consume_count+=Integer.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("new_guest_cost");
					if (tmpStr!=null){
						new_guest_cost+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("new_guest_pay_amount");
					if (tmpStr!=null){
						new_guest_pay_amount+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("new_unit_price");
					if (tmpStr!=null){
						new_unit_price+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("old_guest_combine_order_count");
					if (tmpStr!=null){
						old_guest_combine_order_count+=Integer.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("old_guest_consume_count");
					if (tmpStr!=null){
						old_guest_consume_count+=Integer.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("old_guest_pay_amount");
					if (tmpStr!=null){
						old_guest_pay_amount+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("old_unit_price");
					if (tmpStr!=null){
						old_unit_price+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("total_pay_amount");
					if (tmpStr!=null){
						total_pay_amount+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("rate");
					if (tmpStr!=null){
						rate+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("roi");
					if (tmpStr!=null){
						roi+=Double.valueOf(tmpStr.toString());
					}
					
					Date date3=formatter.parse(eachSprChnlList.get(i).get("create_date").toString());
					while (date3.compareTo(date1)>0){			
						List<HashMap<String, Object>> tmpList=new ArrayList<HashMap<String,Object>>();
						paramMap.put("orderDateBegin",date1);
						
				        Calendar c0 = Calendar.getInstance();  
				        c0.setTime(date3);   //设置当前日期  
				        c0.add(Calendar.DATE, -1); //日期减1天  
				        Date date4 = c0.getTime();  
				        
						paramMap.put("orderDateEnd",formatter.format(date4)+" 23:59:59");
						
						tmpList = combineOrderService.androidCountNewNull(paramMap);
						for(int j=0;j<tmpList.size();j++){
							HashMap<String,Object> p = new HashMap<String, Object>();
							p=tmpList.get(j);
							newSprChnlList.add(p);
							Object tmpStr2=null;
							tmpStr2=p.get("total_consumption");
							if (tmpStr2!=null){
								total_consumption+=Double.valueOf(tmpStr2.toString());
							}
							tmpStr2=p.get("actual_consumption");
							if (tmpStr2!=null){
								actual_consumption+=Double.valueOf(tmpStr2.toString());
							}
							tmpStr2=p.get("conversion_quantity");
							if (tmpStr2!=null){
								conversion_quantity+=Double.valueOf(tmpStr2.toString()).intValue();
							}
							tmpStr2=p.get("single_active_cost");
							if (tmpStr2!=null){
								single_active_cost+=Double.valueOf(tmpStr2.toString());
							}
						}
				        date1 = date3;
					}
					newSprChnlList.add(eachSprChnlList.get(i));
			        Calendar c = Calendar.getInstance();  
			        c.setTime(date1);   //设置当前日期  
			        c.add(Calendar.DATE, 1); //日期加1天  
			        date1 = c.getTime();
				}
				
				if (date2.compareTo(date1)>=0){
					List<HashMap<String, Object>> tmpList=new ArrayList<HashMap<String,Object>>();
					paramMap.put("orderDateBegin",date1);
					paramMap.put("orderDateEnd",orderDateEnd+" 23:59:59");
					tmpList = combineOrderService.androidCountNewNull(paramMap);
					for(int i=0;i<tmpList.size();i++){
						HashMap<String,Object> p = new HashMap<String, Object>();
						p=tmpList.get(i);
						newSprChnlList.add(p);
						Object tmpStr3=null;
						tmpStr3=p.get("total_consumption");
						if (tmpStr3!=null){
							total_consumption+=Double.valueOf(tmpStr3.toString());
						}
						tmpStr3=p.get("actual_consumption");
						if (tmpStr3!=null){
							actual_consumption+=Double.valueOf(tmpStr3.toString());
						}
						tmpStr3=p.get("conversion_quantity");
						if (tmpStr3!=null){
							conversion_quantity+=Double.valueOf(tmpStr3.toString()).intValue();
						}
						tmpStr3=p.get("single_active_cost");
						if (tmpStr3!=null){
							single_active_cost+=Double.valueOf(tmpStr3.toString());
						}
					}
				}
			}
			
			if(eachSprChnlList != null && eachSprChnlList.size() > 0) {
				HashMap<String,Object> o = new HashMap<String, Object>();
				o.put("spreadname", paramMap.get("sprChnlNameDesc"));
				o.put("create_date", "合计");
				o.put("total_consumption", (float)Math.round(total_consumption*100)/100);
				o.put("actual_consumption", (float)Math.round(actual_consumption*100)/100);
				o.put("conversion_quantity", conversion_quantity);
				o.put("single_active_cost", conversion_quantity==0?null:(float)Math.round(actual_consumption/conversion_quantity*100)/100);
				o.put("new_guest_combine_order_count", new_guest_combine_order_count);
				o.put("new_guest_consume_count", new_guest_consume_count);
				o.put("new_guest_cost", new_guest_combine_order_count==0?null:(float)Math.round(actual_consumption/new_guest_combine_order_count*100)/100);
				o.put("new_guest_pay_amount", (float)Math.round(new_guest_pay_amount*100)/100);
				o.put("new_unit_price", new_guest_combine_order_count==0?null:(float)Math.round(new_guest_pay_amount/new_guest_combine_order_count*100)/100);
				o.put("old_guest_combine_order_count", old_guest_combine_order_count);
				o.put("old_guest_consume_count", old_guest_consume_count);
				o.put("old_guest_pay_amount", (float)Math.round(old_guest_pay_amount*100)/100);
				o.put("old_unit_price", old_guest_consume_count==0?null:(float)Math.round(old_guest_pay_amount/old_guest_consume_count*100)/100);
				o.put("total_pay_amount", (float)Math.round(total_pay_amount*100)/100);
				o.put("rate", old_guest_consume_count==0?null:(float)Math.round((float)new_guest_consume_count/old_guest_consume_count*100)/100);
				o.put("roi", actual_consumption==0.0?null:(float)Math.round(total_pay_amount/actual_consumption*100)/100);
				list.add(o);
			}
			newSprChnlList.addAll(list);
			resMap.put("Rows", newSprChnlList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title iosCountNewList   
	 * @Description TODO(iOS活动统计（新）)   
	 * @author pengl
	 * @date 2019年2月12日 下午3:52:23
	 */
	@RequestMapping(value = "/iosCountNew/list.shtml")
	public ModelAndView iosCountNewList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/promotionData/iosCountNewList");
		List<SysStatus> channelList = DataDicUtil.getStatusList("BU_SPREAD_ACTIVITY_GROUP", "CHANNEL");
		m.addObject("channelList", channelList);

		SpreadActivityPermissionExample groupPermissionExample = new SpreadActivityPermissionExample();
		groupPermissionExample.createCriteria().andDelFlagEqualTo("0")
			.andStatusEqualTo("1").andTypeEqualTo("1")
			.andStaffInfoIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SpreadActivityPermission> groupPermissionList = spreadActivityPermissionService.selectByExample(groupPermissionExample);
		List<Integer> spreadActivityGroupIdList = new ArrayList<Integer>();
		for(SpreadActivityPermission spreadActivityPermission : groupPermissionList ) {
			spreadActivityGroupIdList.add(spreadActivityPermission.getSpreadId()); //推广id
		}
		List<SpreadActivityGroupCustom> spreadActivityGroupList = null;
		if(spreadActivityGroupIdList.size() > 0) {
			SpreadActivityGroupExample spreadActivityGroupExample = new SpreadActivityGroupExample();
			SpreadActivityGroupExample.Criteria secondProductTypesCriteria = spreadActivityGroupExample.createCriteria();
			secondProductTypesCriteria.andDelFlagEqualTo("0").andIsEffectEqualTo("1").andIdIn(spreadActivityGroupIdList)
					.andDeviceTypeEqualTo(Const.DEVICE_TYPE_IOS);
			spreadActivityGroupExample.setOrderByClause(" IFNULL(t.seq_no, 99999999999) asc, t.create_date asc");
			spreadActivityGroupList = spreadActivityGroupService.selectSpreadActivityGroupCustomByExample(spreadActivityGroupExample);
		}
		m.addObject("spreadActivityGroupList", spreadActivityGroupList);

		SpreadActivityPermissionExample groupSetPermissionExample = new SpreadActivityPermissionExample();
		groupSetPermissionExample.createCriteria().andDelFlagEqualTo("0")
			.andStatusEqualTo("1").andTypeEqualTo("2")
			.andStaffInfoIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SpreadActivityPermission> groupSetPermissionList = spreadActivityPermissionService.selectByExample(groupSetPermissionExample);
		List<Integer> spreadActivityGroupSetIdList = new ArrayList<Integer>();
		for(SpreadActivityPermission spreadActivityPermission : groupSetPermissionList ) {
			spreadActivityGroupSetIdList.add(spreadActivityPermission.getSpreadId());
		}
		List<SpreadActivityGroupSetCustom> spreadActivityGroupSetCustomList = null;
		if(spreadActivityGroupSetIdList.size() > 0 ) {
			SpreadActivityGroupSetCustomExample spreadActivityGroupSetCustomExample = new SpreadActivityGroupSetCustomExample();
			SpreadActivityGroupSetCustomExample.Criteria spreadActivityGroupSetCustomCriteria = spreadActivityGroupSetCustomExample.createCriteria();
			spreadActivityGroupSetCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(spreadActivityGroupSetIdList)
					.andDeviceTypeEqualTo(Const.DEVICE_TYPE_IOS);
			spreadActivityGroupSetCustomExample.setOrderByClause(" ifnull(t.seq_no, 999999999), t.id desc");
			spreadActivityGroupSetCustomList = spreadActivityGroupSetService.selectByCustomExample(spreadActivityGroupSetCustomExample);
		}
		m.addObject("spreadActivityGroupSetCustomList", spreadActivityGroupSetCustomList);
		
		return m;
	}
	
	/**
	 * 
	 * @Title iosCountNewData   
	 * @Description TODO(iOS活动统计（新）数据)   
	 * @author pengl
	 * @date 2019年2月12日 下午4:17:12
	 */
	@ResponseBody
	@RequestMapping(value = "/iosCountNew/data.shtml")
	public Map<String, Object> iosCountNewData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String orderDateBegin = request.getParameter("order_date_begin");
			String orderDateEnd = request.getParameter("order_date_end");
			String channel = request.getParameter("channel");
			String activityGroupId = request.getParameter("activityGroupId");
			String spreadName = request.getParameter("spreadName");
			String visitType = request.getParameter("visitType");
			String channelName = request.getParameter("channelName");
			Double total_consumption= 0.0;
			Double actual_consumption=0.0;
			Integer conversion_quantity=0;
			Double single_active_cost=0.0;
			Integer new_guest_combine_order_count=0;
			Integer new_guest_consume_count=0;
			Double new_guest_cost=0.0;
			Double new_guest_pay_amount=0.0;
			Double new_unit_price=0.0;
			Integer old_guest_combine_order_count=0;
			Integer old_guest_consume_count=0;
			Double old_guest_pay_amount=0.0;
			Double old_unit_price=0.0;
			Double total_pay_amount=0.0;
			Double rate=0.0;
			Double roi=0.0;
			if(StringUtil.isEmpty(orderDateBegin) || StringUtil.isEmpty(orderDateEnd) || StringUtil.isEmpty(channel) || StringUtil.isEmpty(activityGroupId)){
				resMap.put("Rows", new ArrayList<HashMap<String, Object>>());
				return resMap;
			}
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("orderDateBegin",orderDateBegin+" 00:00:00");
			paramMap.put("orderDateEnd",orderDateEnd+" 23:59:59");
			paramMap.put("channel",channel);
			paramMap.put("channelName",channelName);
			paramMap.put("deviceType", Const.DEVICE_TYPE_IOS);
			if(!StringUtil.isEmpty(activityGroupId)) {
				if(activityGroupId.contains("A") ) {
					paramMap.put("activityGroupId",Integer.parseInt(activityGroupId.split("-")[1]));
				}else if(activityGroupId.contains("B") ) {
					paramMap.put("activityGroupSetId",Integer.parseInt(activityGroupId.split("-")[1]));
				}
			}
			if(!StringUtil.isEmpty(spreadName)) {
				paramMap.put("spreadName",spreadName);
			}
			if(!StringUtil.isEmpty(visitType) ) {
				paramMap.put("visitType",visitType);
			}
			List<HashMap<String, Object>> eachSprChnlList = combineOrderService.iosCountNew(paramMap);
			//List<HashMap<String, Object>> list = combineOrderService.totalIosCount(paramMap);
			List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
			
			List<HashMap<String, Object>> newSprChnlList=new ArrayList<HashMap<String,Object>>();
			SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
			Date date1 = formatter.parse(orderDateBegin);
			Date date2 = formatter.parse(orderDateEnd);
			
			if (eachSprChnlList==null || eachSprChnlList.size()==0){
				newSprChnlList = combineOrderService.iosCountNewNull(paramMap);
				for(int i=0;i<newSprChnlList.size();i++){
					Object tmpStr=null;
					tmpStr=newSprChnlList.get(i).get("total_consumption");
					if (tmpStr!=null){
						total_consumption+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=newSprChnlList.get(i).get("actual_consumption");
					if (tmpStr!=null){
						actual_consumption+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=newSprChnlList.get(i).get("conversion_quantity");
					if (tmpStr!=null){
						conversion_quantity+=Double.valueOf(tmpStr.toString()).intValue();
					}
					tmpStr=newSprChnlList.get(i).get("single_active_cost");
					if (tmpStr!=null){
						single_active_cost+=Double.valueOf(tmpStr.toString());
					}
				}
			}else{
				for(int i=0;i<eachSprChnlList.size();i++){
					Object tmpStr=null;
					tmpStr=eachSprChnlList.get(i).get("total_consumption");
					if (tmpStr!=null){
						total_consumption+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("actual_consumption");
					if (tmpStr!=null){
						actual_consumption+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("conversion_quantity");
					if (tmpStr!=null){
						conversion_quantity+=Integer.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("single_active_cost");
					if (tmpStr!=null){
						single_active_cost+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("new_guest_combine_order_count");
					if (tmpStr!=null){
						new_guest_combine_order_count+=Integer.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("new_guest_consume_count");
					if (tmpStr!=null){
						new_guest_consume_count+=Integer.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("new_guest_cost");
					if (tmpStr!=null){
						new_guest_cost+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("new_guest_pay_amount");
					if (tmpStr!=null){
						new_guest_pay_amount+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("new_unit_price");
					if (tmpStr!=null){
						new_unit_price+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("old_guest_combine_order_count");
					if (tmpStr!=null){
						old_guest_combine_order_count+=Integer.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("old_guest_consume_count");
					if (tmpStr!=null){
						old_guest_consume_count+=Integer.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("old_guest_pay_amount");
					if (tmpStr!=null){
						old_guest_pay_amount+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("old_unit_price");
					if (tmpStr!=null){
						old_unit_price+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("total_pay_amount");
					if (tmpStr!=null){
						total_pay_amount+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("rate");
					if (tmpStr!=null){
						rate+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("roi");
					if (tmpStr!=null){
						roi+=Double.valueOf(tmpStr.toString());
					}
					
					Date date3=formatter.parse(eachSprChnlList.get(i).get("create_date").toString());
					while (date3.compareTo(date1)>0){			
						List<HashMap<String, Object>> tmpList=new ArrayList<HashMap<String,Object>>();
						paramMap.put("orderDateBegin",date1);
						
				        Calendar c0 = Calendar.getInstance();  
				        c0.setTime(date3);   //设置当前日期  
				        c0.add(Calendar.DATE, -1); //日期减1天  
				        Date date4 = c0.getTime();  
				        
						paramMap.put("orderDateEnd",formatter.format(date4)+" 23:59:59");
						
						tmpList = combineOrderService.iosCountNewNull(paramMap);
						for(int j=0;j<tmpList.size();j++){
							HashMap<String,Object> p = new HashMap<String, Object>();
							p=tmpList.get(j);
							newSprChnlList.add(p);
							Object tmpStr2=null;
							tmpStr2=p.get("total_consumption");
							if (tmpStr2!=null){
								total_consumption+=Double.valueOf(tmpStr2.toString());
							}
							tmpStr2=p.get("actual_consumption");
							if (tmpStr2!=null){
								actual_consumption+=Double.valueOf(tmpStr2.toString());
							}
							tmpStr2=p.get("conversion_quantity");
							if (tmpStr2!=null){
								conversion_quantity+=Double.valueOf(tmpStr2.toString()).intValue();
							}
							tmpStr2=p.get("single_active_cost");
							if (tmpStr2!=null){
								single_active_cost+=Double.valueOf(tmpStr2.toString());
							}
						}
						date1 = date3;
					}
					newSprChnlList.add(eachSprChnlList.get(i));
			        Calendar c = Calendar.getInstance();  
			        c.setTime(date1);   //设置当前日期  
			        c.add(Calendar.DATE, 1); //日期加1天  
			        date1 = c.getTime();
				}
				if (date2.compareTo(date1)>=0){
					List<HashMap<String, Object>> tmpList=new ArrayList<HashMap<String,Object>>();
					paramMap.put("orderDateBegin",date1);
					paramMap.put("orderDateEnd",orderDateEnd+" 23:59:59");
					tmpList = combineOrderService.iosCountNewNull(paramMap);
					for(int i=0;i<tmpList.size();i++){
						HashMap<String,Object> p = new HashMap<String, Object>();
						p=tmpList.get(i);
						newSprChnlList.add(p);
						Object tmpStr3=null;
						tmpStr3=p.get("total_consumption");
						if (tmpStr3!=null){
							total_consumption+=Double.valueOf(tmpStr3.toString());
						}
						tmpStr3=p.get("actual_consumption");
						if (tmpStr3!=null){
							actual_consumption+=Double.valueOf(tmpStr3.toString());
						}
						tmpStr3=p.get("conversion_quantity");
						if (tmpStr3!=null){
							conversion_quantity+=Double.valueOf(tmpStr3.toString()).intValue();
						}
						tmpStr3=p.get("single_active_cost");
						if (tmpStr3!=null){
							single_active_cost+=Double.valueOf(tmpStr3.toString());
						}
					}
				}
			}
				
			if(newSprChnlList != null && newSprChnlList.size() > 0) {
				HashMap<String,Object> o = new HashMap<String, Object>();
				o.put("spreadname", paramMap.get("channelName"));
				o.put("create_date", "合计");
				o.put("total_consumption", (float)Math.round(total_consumption*100)/100);
				o.put("actual_consumption", (float)Math.round(actual_consumption*100)/100);
				o.put("conversion_quantity", conversion_quantity);
				o.put("single_active_cost", conversion_quantity==0?null:(float)Math.round(actual_consumption/conversion_quantity*100)/100);
				o.put("new_guest_combine_order_count", new_guest_combine_order_count);
				o.put("new_guest_consume_count", new_guest_consume_count);
				o.put("new_guest_cost", new_guest_combine_order_count==0?null:(float)Math.round(actual_consumption/new_guest_combine_order_count*100)/100);
				o.put("new_guest_pay_amount", (float)Math.round(new_guest_pay_amount*100)/100);
				o.put("new_unit_price", (float)Math.round(new_guest_pay_amount/new_guest_combine_order_count*100)/100);
				o.put("old_guest_combine_order_count", old_guest_combine_order_count);
				o.put("old_guest_consume_count", old_guest_consume_count);
				o.put("old_guest_pay_amount", (float)Math.round(old_guest_pay_amount*100)/100);
				o.put("old_unit_price", old_guest_consume_count==0?null:(float)Math.round(old_guest_pay_amount/old_guest_consume_count*100)/100);
				o.put("total_pay_amount", (float)Math.round(total_pay_amount*100)/100);
				o.put("rate", old_guest_consume_count==0?null:(float)Math.round((float)new_guest_consume_count/old_guest_consume_count*100)/100);
				o.put("roi", actual_consumption==0.0?null:(float)Math.round(total_pay_amount/actual_consumption*100)/100);
				list.add(o);
			}
			newSprChnlList.addAll(list);
			resMap.put("Rows", newSprChnlList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	
	
	
	
	/**
	 * 
	 * @MethodName: androidCountReturnList
	 * @Description: (安卓手机统计（退货）)
	 * @author Pengl
	 * @date 2019年3月30日 下午6:31:19
	 */
	@RequestMapping(value = "/androidCountReturn/list.shtml")
	public ModelAndView androidCountReturnList(HttpServletRequest request) {
		String rtPage = "/dataCenter/promotionData/androidCountReturnList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		/*查询渠道集合*/
		SpreadActivityPermissionExample spreadActivityPermissionExample = new SpreadActivityPermissionExample();
		spreadActivityPermissionExample.createCriteria().andDelFlagEqualTo("0")
			.andStatusEqualTo("1").andTypeEqualTo("3")
			.andStaffInfoIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SpreadActivityPermission> spreadActivityPermissionList = spreadActivityPermissionService.selectByExample(spreadActivityPermissionExample);
		List<Integer> spreadIdList = new ArrayList<Integer>();
		for(SpreadActivityPermission spreadActivityPermission : spreadActivityPermissionList ) {
			spreadIdList.add(spreadActivityPermission.getSpreadId());
		}
		List<AndroidChannelGroup> androidChannelGroups = null;
		if(spreadIdList.size() > 0 ) {
			AndroidChannelGroupExample androidChannelGroupExample = new AndroidChannelGroupExample();
			androidChannelGroupExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(spreadIdList);
			androidChannelGroupExample.setOrderByClause(" type, IFNULL(seq_no, 999999) asc, id desc");
			androidChannelGroups = androidChannelGroupService.selectByExample(androidChannelGroupExample);
		}
		resMap.put("androidChannelGroups", androidChannelGroups);

		SpreadActivityPermissionExample groupSetPermissionExample = new SpreadActivityPermissionExample();
		groupSetPermissionExample.createCriteria().andDelFlagEqualTo("0")
				.andStatusEqualTo("1").andTypeEqualTo("4")
				.andStaffInfoIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SpreadActivityPermission> groupSetPermissionList = spreadActivityPermissionService.selectByExample(groupSetPermissionExample);
		List<Integer> spreadActivityGroupSetIdList = new ArrayList<Integer>();
		for(SpreadActivityPermission spreadActivityPermission : groupSetPermissionList ) {
			spreadActivityGroupSetIdList.add(spreadActivityPermission.getSpreadId());
		}
		List<AndroidChannelGroupSet> androidChannelGroupSetList = null;
		if(spreadActivityGroupSetIdList.size() > 0 ) {
			AndroidChannelGroupSetExample androidChannelGroupSetExample = new AndroidChannelGroupSetExample();
			androidChannelGroupSetExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(spreadActivityGroupSetIdList);
			androidChannelGroupSetExample.setOrderByClause(" ifnull(seq_no, 999999) asc, id desc");
			androidChannelGroupSetList = androidChannelGroupSetService.selectByExample(androidChannelGroupSetExample);
		}
		resMap.put("androidChannelGroupSetList", androidChannelGroupSetList);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 
	 * @MethodName: androidCountReturnData
	 * @Description: (安卓手机统计（退货）数据)
	 * @author Pengl
	 * @date 2019年3月30日 下午6:31:53
	 */
	@ResponseBody
	@RequestMapping(value = "/androidCountReturn/data.shtml")
	public Map<String, Object> androidCountReturnData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String orderDateBegin = request.getParameter("order_date_begin");
			String orderDateEnd = request.getParameter("order_date_end");
			String sprChnl = request.getParameter("sprChnl");
			String visitType = request.getParameter("visitType");
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			List<String> groupPrefixList = new ArrayList<>();
			if(!StringUtil.isEmpty(sprChnl)){
				String[] sprChnlStr = sprChnl.split(",");
				if("1".equals(sprChnlStr[0]) ) {
					paramMap.put("sprChnlName", sprChnlStr[1]+"%");
					paramMap.put("sprChnlNameDesc", sprChnlStr[1]+"（集合）");
				}else {
					AndroidChannelGroupSetDtlCustomExample androidChannelGroupSetDtlCustomExample = new AndroidChannelGroupSetDtlCustomExample();
					AndroidChannelGroupSetDtlCustomExample.AndroidChannelGroupSetDtlCustomCriteria androidChannelGroupSetDtlCustomCriteria = androidChannelGroupSetDtlCustomExample.createCriteria();
					androidChannelGroupSetDtlCustomCriteria.andDelFlagEqualTo("0")
							.andAndroidChannelGroupSetIdEqualTo(Integer.parseInt(sprChnlStr[1].split("\\|")[0]));
					androidChannelGroupSetDtlCustomCriteria.andGroupStatusEqualTo("1");
					List<AndroidChannelGroupSetDtlCustom> androidChannelGroupSetDtlCustomList = androidChannelGroupSetDtlService.selectByCustomExample(androidChannelGroupSetDtlCustomExample);
					for (AndroidChannelGroupSetDtlCustom androidChannelGroupSetDtlCustom : androidChannelGroupSetDtlCustomList) {
						groupPrefixList.add(androidChannelGroupSetDtlCustom.getGroupPrefix()+"%");
					}
					paramMap.put("groupPrefixList", groupPrefixList);
					paramMap.put("sprChnlNameDesc", sprChnlStr[1].split("\\|")[1]+"（组）");
				}
			}
			if(StringUtil.isEmpty(orderDateBegin) || StringUtil.isEmpty(orderDateEnd) || (StringUtil.isEmpty(sprChnl) && groupPrefixList.size() > 0)){
				resMap.put("Rows", new ArrayList<HashMap<String, Object>>());
				return resMap;
			}
			paramMap.put("orderDateBegin",orderDateBegin+" 00:00:00");
			paramMap.put("orderDateEnd",orderDateEnd+" 23:59:59");
			if(!StringUtil.isEmpty(visitType) ) {//访客类型
				paramMap.put("visitType",visitType);
			}
			List<HashMap<String, Object>> eachSprChnlList = combineOrderService.androidCountReturn(paramMap);
			List<HashMap<String, Object>> list = combineOrderService.totalAndroidCountReturn(paramMap);
			if(list != null && list.size() > 0) {
				list.get(0).put("chnl_name", paramMap.get("sprChnlNameDesc"));
				list.get(0).put("create_date", "合计");
			}
			eachSprChnlList.addAll(list);
			resMap.put("Rows", eachSprChnlList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @MethodName: iosCountReturnList
	 * @Description: (iOS活动统计（退货）)
	 * @author Pengl
	 * @date 2019年3月30日 下午6:32:35
	 */
	@RequestMapping(value = "/iosCountReturn/list.shtml")
	public ModelAndView iosCountReturnList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/promotionData/iosCountReturnList");
		List<SysStatus> channelList = DataDicUtil.getStatusList("BU_SPREAD_ACTIVITY_GROUP", "CHANNEL");
		m.addObject("channelList", channelList);
		
		SpreadActivityPermissionExample groupPermissionExample = new SpreadActivityPermissionExample();
		groupPermissionExample.createCriteria().andDelFlagEqualTo("0")
			.andStatusEqualTo("1").andTypeEqualTo("1")
			.andStaffInfoIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SpreadActivityPermission> groupPermissionList = spreadActivityPermissionService.selectByExample(groupPermissionExample);
		List<Integer> spreadActivityGroupIdList = new ArrayList<Integer>();
		for(SpreadActivityPermission spreadActivityPermission : groupPermissionList ) {
			spreadActivityGroupIdList.add(spreadActivityPermission.getSpreadId());
		}
		List<SpreadActivityGroupCustom> spreadActivityGroupList = null;
		if(spreadActivityGroupIdList.size() > 0) {
			SpreadActivityGroupExample spreadActivityGroupExample = new SpreadActivityGroupExample();
			SpreadActivityGroupExample.Criteria secondProductTypesCriteria = spreadActivityGroupExample.createCriteria();
			secondProductTypesCriteria.andDelFlagEqualTo("0").andIsEffectEqualTo("1").andIdIn(spreadActivityGroupIdList)
					.andDeviceTypeEqualTo(Const.DEVICE_TYPE_IOS);
			spreadActivityGroupExample.setOrderByClause(" IFNULL(t.seq_no, 99999999999) asc, t.create_date asc");
			spreadActivityGroupList = spreadActivityGroupService.selectSpreadActivityGroupCustomByExample(spreadActivityGroupExample);
		}
		m.addObject("spreadActivityGroupList", spreadActivityGroupList);
		
		SpreadActivityPermissionExample groupSetPermissionExample = new SpreadActivityPermissionExample();
		groupSetPermissionExample.createCriteria().andDelFlagEqualTo("0")
			.andStatusEqualTo("1").andTypeEqualTo("2")
			.andStaffInfoIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SpreadActivityPermission> groupSetPermissionList = spreadActivityPermissionService.selectByExample(groupSetPermissionExample);
		List<Integer> spreadActivityGroupSetIdList = new ArrayList<Integer>();
		for(SpreadActivityPermission spreadActivityPermission : groupSetPermissionList ) {
			spreadActivityGroupSetIdList.add(spreadActivityPermission.getSpreadId());
		}
		List<SpreadActivityGroupSetCustom> spreadActivityGroupSetCustomList = null;
		if(spreadActivityGroupSetIdList.size() > 0 ) {
			SpreadActivityGroupSetCustomExample spreadActivityGroupSetCustomExample = new SpreadActivityGroupSetCustomExample();
			SpreadActivityGroupSetCustomExample.Criteria spreadActivityGroupSetCustomCriteria = spreadActivityGroupSetCustomExample.createCriteria();
			spreadActivityGroupSetCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(spreadActivityGroupSetIdList)
					.andDeviceTypeEqualTo(Const.DEVICE_TYPE_IOS);
			spreadActivityGroupSetCustomExample.setOrderByClause(" ifnull(t.seq_no, 999999999), t.id desc");
			spreadActivityGroupSetCustomList = spreadActivityGroupSetService.selectByCustomExample(spreadActivityGroupSetCustomExample);
		}
		m.addObject("spreadActivityGroupSetCustomList", spreadActivityGroupSetCustomList);
		
		return m;
	}
	
	/**
	 * 
	 * @MethodName: iosCountNewData
	 * @Description: (iOS活动统计（退货）数据)
	 * @author Pengl
	 * @date 2019年3月30日 下午6:32:45
	 */
	@ResponseBody
	@RequestMapping(value = "/iosCountReturn/data.shtml")
	public Map<String, Object> iosCountReturnData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String orderDateBegin = request.getParameter("order_date_begin");
			String orderDateEnd = request.getParameter("order_date_end");
			String channel = request.getParameter("channel");
			String activityGroupId = request.getParameter("activityGroupId");
			String spreadName = request.getParameter("spreadName");
			String visitType = request.getParameter("visitType");
			String channelName = request.getParameter("channelName");
			if(StringUtil.isEmpty(orderDateBegin) || StringUtil.isEmpty(orderDateEnd) || StringUtil.isEmpty(channel) ){
				resMap.put("Rows", new ArrayList<HashMap<String, Object>>());
				return resMap;
			}
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("orderDateBegin",orderDateBegin+" 00:00:00");
			paramMap.put("orderDateEnd",orderDateEnd+" 23:59:59");
			paramMap.put("channel",channel);
			paramMap.put("channelName",channelName);
			paramMap.put("deviceType", Const.DEVICE_TYPE_IOS);
			if(!StringUtil.isEmpty(activityGroupId)) {
				if(activityGroupId.contains("A") ) {
					paramMap.put("activityGroupId",Integer.parseInt(activityGroupId.split("-")[1]));
				}else if(activityGroupId.contains("B") ) {
					paramMap.put("activityGroupSetId",Integer.parseInt(activityGroupId.split("-")[1]));
				}
			}
			if(!StringUtil.isEmpty(spreadName)) {
				paramMap.put("spreadName",spreadName);
			}
			if(!StringUtil.isEmpty(visitType) ) {
				paramMap.put("visitType",visitType);
			}
			List<HashMap<String, Object>> eachSprChnlList = combineOrderService.iosCountReturn(paramMap);
			List<HashMap<String, Object>> list = combineOrderService.totalIosCountReturn(paramMap);
			eachSprChnlList.addAll(list);
			resMap.put("Rows", eachSprChnlList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	/**
	 * 
	 * @Title androidChannelGroupIndex   
	 * @Description TODO(安卓渠道集合管理)   
	 * @author linzw
	 * @date 2019年6月20日 17:30:00
	 */
	@RequestMapping(value = "/androidChannelGroup/list.shtml")
	public ModelAndView androidChannelGroupIndex(HttpServletRequest request) {
		String rePage = "/dataCenter/promotionData/androidChannelGroupIndex";
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rePage, resMap);
	}

	@RequestMapping(value = "/androidChannelGroup/dataList.shtml")
	@ResponseBody
	public Map<String, Object> androidChannelGroupDataList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		
		AndroidChannelGroupExample androidChannelGroupExample=new AndroidChannelGroupExample();
		AndroidChannelGroupExample.Criteria criteria=androidChannelGroupExample.createCriteria().andDelFlagEqualTo("0");
		
		if(!StringUtil.isEmpty(request.getParameter("groupId"))){
			criteria.andGroupIdEqualTo(request.getParameter("groupId"));
		}
		if(!StringUtil.isEmpty(request.getParameter("groupName"))){
			criteria.andGroupNameLike("%"+request.getParameter("groupName")+"%");
		}
		if(!StringUtil.isEmpty(request.getParameter("status"))){
			criteria.andStatusEqualTo(request.getParameter("status"));
		}
		
		totalCount=androidChannelGroupService.countByExample(androidChannelGroupExample);
		
		androidChannelGroupExample.setLimitSize(page.getLimitSize());
		androidChannelGroupExample.setLimitStart(page.getLimitStart());
		androidChannelGroupExample.setOrderByClause(" IFNULL(seq_no, 999999) asc, id desc");
		List<AndroidChannelGroup> androidChannelGroups=androidChannelGroupService.selectByExample(androidChannelGroupExample);

		resMap.put("Rows", androidChannelGroups);
		resMap.put("Total", totalCount);

		return resMap;
	}	
	
	//添加，修改安卓渠道集合
	@RequestMapping(value = "/androidChannelGroup/edit.shtml")
	public ModelAndView editAndroidChannelGroup(HttpServletRequest request) {	
		String rtPage = "/dataCenter/promotionData/editAndroidChannelGroup";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		if (!StringUtil.isEmpty(request.getParameter("id"))) {
			int id=Integer.valueOf(request.getParameter("id"));
			AndroidChannelGroup androidChannelGroup=androidChannelGroupService.selectByPrimaryKey(id);
			resMap.put("androidChannelGroup", androidChannelGroup);
		}else{
			resMap.put("androidChannelGroup", new AndroidChannelGroup());
		}
		
		return new ModelAndView(rtPage, resMap);
	}
	
	//保存修改或添加安卓渠道集合
	@ResponseBody
	@RequestMapping(value = "/androidChannelGroup/save.shtml")
	public ModelAndView saveAndroidChannelGroup(HttpServletRequest request,AndroidChannelGroup androidChannelGroup){
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg =null;
		try {
			if(androidChannelGroup.getId() == null){
				androidChannelGroup.setCreateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
				androidChannelGroup.setCreateDate(new Date());
				androidChannelGroupService.insertSelective(androidChannelGroup);
			}else{
				androidChannelGroup.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
				androidChannelGroup.setUpdateDate(new Date());
				androidChannelGroupService.updateByPrimaryKeySelective(androidChannelGroup);
			}
						
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = e.getMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	//批量修改安卓渠道集合启用状态
	@RequestMapping(value = "/androidChannelGroup/batchEdit.shtml")
	public ModelAndView batchEditAndroidChannelGroup(HttpServletRequest request) {	
		String rtPage = "/dataCenter/promotionData/batchEditAndroidChannelGroup";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("androidChannelGroupIds", request.getParameter("androidChannelGroupIds"));
		return new ModelAndView(rtPage, resMap);
	}
	
	//批量修改安卓渠道集合启用状态
	@ResponseBody
	@RequestMapping(value = "/androidChannelGroup/batchSave.shtml")
	public ModelAndView batchEditAndroidChannelGroup(HttpServletRequest request,AndroidChannelGroup androidChannelGroup){
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg =null;
		try {
			if(StringUtil.isEmpty(request.getParameter("androidChannelGroupIds"))){
				throw new Exception("Id不能为空。");
			}
			if(StringUtil.isEmpty(androidChannelGroup.getStatus())){
				androidChannelGroup.setStatus(null);
			}
			String[] androidChannelGroupIds=request.getParameter("androidChannelGroupIds").split(",");
			Integer idGroups[]=new Integer[androidChannelGroupIds.length];
			for(int i=0; i<idGroups.length; i++)
			{
				idGroups[i]=Integer.valueOf(androidChannelGroupIds[i]);
			}
			
			List<Integer> idList = Arrays.asList(idGroups);
			
			AndroidChannelGroupExample androidChannelGroupExample=new AndroidChannelGroupExample();
			AndroidChannelGroupExample.Criteria criteria=androidChannelGroupExample.createCriteria().andDelFlagEqualTo("0");
			criteria.andIdIn(idList);
			
			androidChannelGroup.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
			androidChannelGroup.setUpdateDate(new Date());
			androidChannelGroupService.updateByExampleSelective(androidChannelGroup, androidChannelGroupExample);
			
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = e.getMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	//更新排序值
	@ResponseBody
	@RequestMapping("/androidChannelGroup/updateSeqNo.shtml")
	public Map<String, Object> androidChannelGroupUpdateSeqNo(HttpServletRequest request, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			String id = request.getParameter("id");
			String seqNo = request.getParameter("seqNo");
			if(!StringUtil.isEmpty(id)) {
				AndroidChannelGroup androidChannelGroup = new AndroidChannelGroup();
				androidChannelGroup.setId(Integer.parseInt(id));
				androidChannelGroup.setSeqNo(Integer.parseInt(seqNo));
				androidChannelGroupService.updateByPrimaryKeySelective(androidChannelGroup);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	//删除渠道集合
	@ResponseBody
	@RequestMapping("/androidChannelGroup/del.shtml")
	public Map<String, Object> delAndroidChannelGroup(HttpServletRequest request, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String id = request.getParameter("id");
			if(!StringUtil.isEmpty(id)) {
				AndroidChannelGroup androidChannelGroup = new AndroidChannelGroup();
				androidChannelGroup.setId(Integer.parseInt(id));
				androidChannelGroup.setUpdateBy(Integer.parseInt(staffID));
				androidChannelGroup.setUpdateDate(date);
				androidChannelGroup.setDelFlag("1");
				androidChannelGroupService.updateByPrimaryKeySelective(androidChannelGroup);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/*导入推广数据*/
	@RequestMapping(value = "/srpeadDataImport/index.shtml")
	public ModelAndView srpeadDataImportIndex(HttpServletRequest request) {
		String rtPage = "/dataCenter/promotionData/spreadDataIndex";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage, resMap);
	}
	
	/*导入或更新数据*/
	@RequestMapping(value = "/srpeadDataImport/updateOrSaveSpreadDatas.shtml")
	public ModelAndView updateOrSaveSpreadDatas(HttpServletRequest request, MultipartFile excelFile) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = null;
		String msg =null;
		SpreadData spreadData = null;
		List<SpreadData> spreadDataList = null;
		StaffBean staffBean = this.getSessionStaffBean(request);
		Integer staffId = Integer.valueOf(staffBean.getStaffID());
		try {
			// 构造 Workbook 对象
			Workbook book = null;
			//创建Excel工作薄   
			try {  
                // Excel 2007获取方法
                book = new XSSFWorkbook(excelFile.getInputStream());  
            } catch (Exception ex) {  
                // Excel 2003获取方法  
                book = new HSSFWorkbook(excelFile.getInputStream());  
            }
			//得到第一个工作表   
			Sheet sheet = book.getSheetAt(0);  
            Row row = null;  
            //遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数    
//            for(int i = 0; i < book.getNumberOfSheets(); i++) {
            spreadDataList = new ArrayList<SpreadData>();
//                sheet = book.getSheetAt(i); 
                for(int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {  
                	if(sheet.getPhysicalNumberOfRows() <= 5000) {
                		row = sheet.getRow(j);
    					if(j != 0) {
    						if(row != null && !"".equals(row)) {
        						if(row.getCell(0) != null && !"".equals(row.getCell(0)) ) {
        							spreadData = new SpreadData();
        					        spreadData.setDate(row.getCell(0)==null?null:row.getCell(0).getDateCellValue());
        					        row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
        							spreadData.setAccountId(row.getCell(1).getStringCellValue().trim().replace("'", ""));
        							/*
        							spreadData.setAccountName(row.getCell(2).toString().trim());
        							row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
        							spreadData.setDisplayQuantity(row.getCell(4).getStringCellValue().trim());
        							row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
        							spreadData.setClickQuantity(row.getCell(5).getStringCellValue().trim());
        							
        							row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
        							double d6 = Double.valueOf(row.getCell(6).getStringCellValue());
        					        DecimalFormat df6 = new DecimalFormat("#.##%");
        					        df6.setRoundingMode(RoundingMode.DOWN);       					        
        							spreadData.setClickRate(row.getCell(6)==null?null:df6.format(d5));
        							
        							row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
        							double d9 = Double.valueOf(row.getCell(9).getStringCellValue());
        					        DecimalFormat df9 = new DecimalFormat("#.##%");
        					        df8.setRoundingMode(RoundingMode.DOWN);
        							spreadData.setConversionRate(row.getCell(9)==null?null:df9.format(d9));
        							        							
        							row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
        							spreadData.setConsumptionRingRatio(row.getCell(11).getStringCellValue().trim());
        							row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
        							spreadData.setBalance(row.getCell(12).getStringCellValue().trim());
        							row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
        							spreadData.setAvailableBalance(row.getCell(13).getStringCellValue().trim());
        							spreadData.setEmail(row.getCell(14)==null?null:row.getCell(14).toString().trim());
        							spreadData.setRemarks(row.getCell(15)==null?null:row.getCell(15).toString().trim());
        							
        							row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
        							spreadData.setConversionCost(row.getCell(8).getStringCellValue().trim());
        							*/
        							
        							row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
        							spreadData.setConversionQuantity(row.getCell(7).getStringCellValue().trim());
        							
        							row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
        							spreadData.setTotalConsumption(row.getCell(10).getStringCellValue().trim());

        							spreadDataList.add(spreadData);
        						}
    						}
    					}else {
    						if(row == null || row.equals("") 
    								|| row.getCell(0) == null || !row.getCell(0).toString().trim().equals("日期") 
    								|| row.getCell(1) == null || !row.getCell(1).toString().trim().equals("ID")
    								|| row.getCell(7) == null || !row.getCell(7).toString().trim().equals("转化数") 
    								|| row.getCell(10) == null || !row.getCell(10).toString().trim().equals("总消耗")
    								/*
    								|| row.getCell(2) == null || !row.getCell(2).toString().trim().equals("账户名称")
    								|| row.getCell(3) == null || !row.getCell(2).toString().trim().equals("所属代理商")
    								|| row.getCell(4) == null || !row.getCell(3).toString().trim().equals("展示量")
    								|| row.getCell(5) == null || !row.getCell(4).toString().trim().equals("点击量") 
    								|| row.getCell(6) == null || !row.getCell(5).toString().trim().equals("点击率")
    								|| row.getCell(8) == null || !row.getCell(7).toString().trim().equals("转化成本")
    								|| row.getCell(9) == null || !row.getCell(8).toString().trim().equals("转化率")  								
    								|| row.getCell(11) == null || !row.getCell(10).toString().trim().equals("消耗环比")
    	    						|| row.getCell(12) == null || !row.getCell(11).toString().trim().equals("余额")
    	    						|| row.getCell(13) == null || !row.getCell(12).toString().trim().equals("可用余额")
    	    						|| row.getCell(14) == null || !row.getCell(13).toString().trim().equals("邮箱")
    	    	    				|| row.getCell(15) == null || !row.getCell(14).toString().trim().equals("备注")*/
    								)
    							throw new NumberFormatException("导入文件模版错误，请检查日期、ID、转化数、总消耗是否分别为第1、2、8、11列");
    					}
                	}else {
                		throw new NumberFormatException("导入文件超过五千行");
                	}
                }
                Map<String, Object> map = androidChannelGroupService.updateOrInsertSpreadData(request, spreadDataList, staffId);
                if(map != null) {
                	msg = "操作成功，新增 "+map.get("insertNum")+" 条数据，更新 "+map.get("updateNum")+" 条数据。";
                }else {
                	msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
                }
//            }
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			if(e.getMessage().equals("导入文件超过五千行") || e.getMessage().equals("导入文件模版错误")) {
				msg = e.getMessage();
			}else {
				msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
			}
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}

	@RequestMapping(value = "/androidChannelGroup/androidChannelGroupManager.shtml")
	public ModelAndView androidChannelGroupManager(HttpServletRequest request) {
		String rtPage = "/dataCenter/promotionData/androidChannelGroupList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SpreadActivityPermissionExample groupSetPermissionExample = new SpreadActivityPermissionExample();
		groupSetPermissionExample.createCriteria().andDelFlagEqualTo("0")
				.andStatusEqualTo("1").andTypeEqualTo("4")
				.andStaffInfoIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SpreadActivityPermission> groupSetPermissionList = spreadActivityPermissionService.selectByExample(groupSetPermissionExample);
		List<Integer> spreadActivityGroupSetIdList = new ArrayList<Integer>();
		for(SpreadActivityPermission spreadActivityPermission : groupSetPermissionList ) {
			spreadActivityGroupSetIdList.add(spreadActivityPermission.getSpreadId());
		}
		List<AndroidChannelGroupSet> androidChannelGroupSetList = null;
		if(spreadActivityGroupSetIdList.size() > 0 ) {
			AndroidChannelGroupSetExample androidChannelGroupSetExample = new AndroidChannelGroupSetExample();
			androidChannelGroupSetExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(spreadActivityGroupSetIdList);
			androidChannelGroupSetExample.setOrderByClause(" ifnull(seq_no, 999999) asc, id desc");
			androidChannelGroupSetList = androidChannelGroupSetService.selectByExample(androidChannelGroupSetExample);
		}
		resMap.put("androidChannelGroupSetList", androidChannelGroupSetList);
		return new ModelAndView(rtPage,resMap);
	}

	@ResponseBody
	@RequestMapping(value = "/androidChannelGroup/androidChannelGroupList.shtml")
	public Map<String, Object> androidChannelGroupList(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String orderDateBegin = request.getParameter("orderDateBegin");
			String orderDateEnd = request.getParameter("orderDateEnd");
			String androidChannelGroupSetStr = request.getParameter("androidChannelGroupSetStr");
			String visitType = request.getParameter("visitType");
			String androidChannelGroupSetId = "";
			String androidChannelGroupSetName = "";
			if(!StringUtil.isEmpty(androidChannelGroupSetStr) ) {
				String[] androidChannelGroupSetSt = androidChannelGroupSetStr.split(",");
				androidChannelGroupSetId = androidChannelGroupSetSt[0];
				androidChannelGroupSetName = androidChannelGroupSetSt[1];
			}
			if(StringUtil.isEmpty(orderDateBegin) || StringUtil.isEmpty(orderDateEnd) || StringUtil.isEmpty(androidChannelGroupSetId) ) {
				resMap.put("Rows", new ArrayList<HashMap<String, Object>>());
				return resMap;
			}
			Double total_consumption = 0.0;
			Double actual_consumption = 0.0;
			Integer conversion_quantity = 0;
			Double single_active_cost = 0.0;
			Integer new_guest_combine_order_count = 0;
			Integer new_guest_consume_count = 0;
			Double new_guest_cost = 0.0;
			Double new_guest_pay_amount = 0.0;
			Double new_unit_price = 0.0;
			Integer old_guest_combine_order_count = 0;
			Integer old_guest_consume_count = 0;
			Double old_guest_pay_amount = 0.0;
			Double old_unit_price = 0.0;
			Double total_pay_amount = 0.0;
			Double rate = 0.0;
			Double roi = 0.0;
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("orderDateBegin", orderDateBegin+" 00:00:00");
			paramMap.put("orderDateEnd", orderDateEnd+" 23:59:59");
			paramMap.put("androidChannelGroupSetId", androidChannelGroupSetId);
			if(!StringUtil.isEmpty(visitType) ) { //访客类型
				paramMap.put("visitType",visitType);
			}
			Date odBegin = sdf.parse(orderDateBegin);
			Date odEnd = sdf.parse(orderDateEnd);
			List<Map<String, Object>> newAndroidChannelGroupList = new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> androidChannelGroupList = combineOrderService.androidChannelGroupList(paramMap);
			if(androidChannelGroupList != null && androidChannelGroupList.size() > 0 ) {
				for(int i=0;i<androidChannelGroupList.size();i++){
					Object tmpStr = null;
					tmpStr = androidChannelGroupList.get(i).get("total_consumption");
					if (androidChannelGroupList.get(i).get("total_consumption") != null ) {
						total_consumption += Double.valueOf(tmpStr.toString());
					}
					tmpStr = androidChannelGroupList.get(i).get("actual_consumption");
					if (tmpStr != null ) {
						actual_consumption += Double.valueOf(tmpStr.toString());
					}
					tmpStr = androidChannelGroupList.get(i).get("conversion_quantity");
					if (tmpStr != null ) {
						conversion_quantity += Integer.valueOf(tmpStr.toString());
					}
					tmpStr = androidChannelGroupList.get(i).get("single_active_cost");
					if (tmpStr != null ) {
						single_active_cost += Double.valueOf(tmpStr.toString());
					}
					tmpStr = androidChannelGroupList.get(i).get("new_guest_combine_order_count");
					if (tmpStr != null ) {
						new_guest_combine_order_count += Integer.valueOf(tmpStr.toString());
					}
					tmpStr = androidChannelGroupList.get(i).get("new_guest_consume_count");
					if (tmpStr != null ) {
						new_guest_consume_count += Integer.valueOf(tmpStr.toString());
					}
					tmpStr = androidChannelGroupList.get(i).get("new_guest_cost");
					if (tmpStr != null ) {
						new_guest_cost += Double.valueOf(tmpStr.toString());
					}
					tmpStr = androidChannelGroupList.get(i).get("new_guest_pay_amount");
					if (tmpStr != null ) {
						new_guest_pay_amount += Double.valueOf(tmpStr.toString());
					}
					tmpStr = androidChannelGroupList.get(i).get("new_unit_price");
					if (tmpStr != null ) {
						new_unit_price += Double.valueOf(tmpStr.toString());
					}
					tmpStr = androidChannelGroupList.get(i).get("old_guest_combine_order_count");
					if (tmpStr != null ) {
						old_guest_combine_order_count += Integer.valueOf(tmpStr.toString());
					}
					tmpStr = androidChannelGroupList.get(i).get("old_guest_consume_count");
					if (tmpStr != null ) {
						old_guest_consume_count += Integer.valueOf(tmpStr.toString());
					}
					tmpStr = androidChannelGroupList.get(i).get("old_guest_pay_amount");
					if (tmpStr != null ) {
						old_guest_pay_amount += Double.valueOf(tmpStr.toString());
					}
					tmpStr = androidChannelGroupList.get(i).get("old_unit_price");
					if (tmpStr != null ) {
						old_unit_price += Double.valueOf(tmpStr.toString());
					}
					tmpStr = androidChannelGroupList.get(i).get("total_pay_amount");
					if (tmpStr != null ) {
						total_pay_amount += Double.valueOf(tmpStr.toString());
					}
					tmpStr = androidChannelGroupList.get(i).get("rate");
					if (tmpStr != null ) {
						rate += Double.valueOf(tmpStr.toString());
					}
					tmpStr = androidChannelGroupList.get(i).get("roi");
					if (tmpStr != null ) {
						roi += Double.valueOf(tmpStr.toString());
					}
					Date createDate = sdf.parse(androidChannelGroupList.get(i).get("create_date").toString());
					while (createDate.compareTo(odBegin) > 0 ) {
						List<Map<String, Object>> tmpList = new ArrayList<Map<String,Object>>();
						paramMap.put("orderDateBegin", odBegin);
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(createDate); //设置当前日期
						calendar.add(Calendar.DATE, -1); //日期减1天
						paramMap.put("orderDateEnd", sdf.format(calendar.getTime())+" 23:59:59");

						tmpList = combineOrderService.androidChannelGroupNull(paramMap);
						for(int j=0;j<tmpList.size();j++ ) {
							Map<String, Object> map = new HashMap<String, Object>();
							map = tmpList.get(j);
							newAndroidChannelGroupList.add(map);
							Object tmpSt = null;
							tmpSt = map.get("total_consumption");
							if (tmpSt != null ) {
								total_consumption += Double.valueOf(tmpSt.toString());
							}
							tmpSt = map.get("actual_consumption");
							if (tmpSt != null ) {
								actual_consumption += Double.valueOf(tmpSt.toString());
							}
							tmpSt = map.get("conversion_quantity");
							if (tmpSt != null ) {
								conversion_quantity += Double.valueOf(tmpSt.toString()).intValue();
							}
							tmpSt = map.get("single_active_cost");
							if (tmpSt != null){
								single_active_cost += Double.valueOf(tmpSt.toString());
							}
						}
						odBegin = createDate;
					}
					newAndroidChannelGroupList.add(androidChannelGroupList.get(i));
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(odBegin); //设置当前日期
					calendar.add(Calendar.DATE, 1); //日期加1天
					odBegin = calendar.getTime();
				}

				if (odEnd.compareTo(odBegin) >= 0 ) {
					List<Map<String, Object>> tmpList = new ArrayList<Map<String,Object>>();
					paramMap.put("orderDateBegin", odBegin);
					paramMap.put("orderDateEnd", orderDateEnd+" 23:59:59");
					tmpList = combineOrderService.androidChannelGroupNull(paramMap);
					for(int i=0;i<tmpList.size();i++ ) {
						Map<String, Object> map = new HashMap<String, Object>();
						map = tmpList.get(i);
						newAndroidChannelGroupList.add(map);
						Object tmpS = null;
						tmpS = map.get("total_consumption");
						if (tmpS != null ) {
							total_consumption += Double.valueOf(tmpS.toString());
						}
						tmpS  = map.get("actual_consumption");
						if (tmpS != null ) {
							actual_consumption += Double.valueOf(tmpS.toString());
						}
						tmpS = map.get("conversion_quantity");
						if (tmpS != null ) {
							conversion_quantity += Double.valueOf(tmpS.toString()).intValue();
						}
						tmpS = map.get("single_active_cost");
						if (tmpS != null ) {
							single_active_cost += Double.valueOf(tmpS.toString());
						}
					}
				}
			}else {
				newAndroidChannelGroupList = combineOrderService.androidChannelGroupNull(paramMap);
				for(int i=0;i<newAndroidChannelGroupList.size();i++ ) {
					Object tmpStr = null;
					tmpStr = newAndroidChannelGroupList.get(i).get("total_consumption");
					if (tmpStr != null ) {
						total_consumption += Double.valueOf(tmpStr.toString());
					}
					tmpStr = newAndroidChannelGroupList.get(i).get("actual_consumption");
					if (tmpStr != null ) {
						actual_consumption += Double.valueOf(tmpStr.toString());
					}
					tmpStr = newAndroidChannelGroupList.get(i).get("conversion_quantity");
					if (tmpStr != null ) {
						conversion_quantity += Double.valueOf(tmpStr.toString()).intValue();
					}
					tmpStr = newAndroidChannelGroupList.get(i).get("single_active_cost");
					if (tmpStr != null ) {
						single_active_cost += Double.valueOf(tmpStr.toString());
					}
				}
			}
			if(androidChannelGroupList != null && androidChannelGroupList.size() > 0) {
				List<Map<String, Object>> listMap = new ArrayList<>();
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("create_date", "合计");
				map.put("group_name", androidChannelGroupSetName);
				map.put("total_consumption", (float)Math.round(total_consumption*100)/100);
				map.put("actual_consumption", (float)Math.round(actual_consumption*100)/100);
				map.put("conversion_quantity", conversion_quantity);
				map.put("single_active_cost", conversion_quantity==0?null:(float)Math.round(actual_consumption/conversion_quantity*100)/100);
				map.put("new_guest_combine_order_count", new_guest_combine_order_count);
				map.put("new_guest_consume_count", new_guest_consume_count);
				map.put("new_guest_cost", new_guest_combine_order_count==0?null:(float)Math.round(actual_consumption/new_guest_combine_order_count*100)/100);
				map.put("new_guest_pay_amount", (float)Math.round(new_guest_pay_amount*100)/100);
				map.put("new_unit_price", new_guest_combine_order_count==0?null:(float)Math.round(new_guest_pay_amount/new_guest_combine_order_count*100)/100);
				map.put("old_guest_combine_order_count", old_guest_combine_order_count);
				map.put("old_guest_consume_count", old_guest_consume_count);
				map.put("old_guest_pay_amount", (float)Math.round(old_guest_pay_amount*100)/100);
				map.put("old_unit_price", old_guest_consume_count==0?null:(float)Math.round(old_guest_pay_amount/old_guest_consume_count*100)/100);
				map.put("total_pay_amount", (float)Math.round(total_pay_amount*100)/100);
				map.put("rate", old_guest_consume_count==0?null:(float)Math.round((float)new_guest_consume_count/old_guest_consume_count*100)/100);
				map.put("roi", actual_consumption==0.0?null:(float)Math.round(total_pay_amount/actual_consumption*100)/100);
				listMap.add(map);
				newAndroidChannelGroupList.addAll(listMap);
			}
			resMap.put("Rows", newAndroidChannelGroupList);
			return resMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}


	@RequestMapping(value = "/spreadActivityGroup/spreadActivityGroupManager.shtml")
	public ModelAndView spreadActivityGroupManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/promotionData/spreadActivityGroupList");
		List<SysStatus> channelList = DataDicUtil.getStatusList("BU_SPREAD_ACTIVITY_GROUP", "CHANNEL");
		m.addObject("channelList", channelList);

		SpreadActivityPermissionExample groupSetPermissionExample = new SpreadActivityPermissionExample();
		groupSetPermissionExample.createCriteria().andDelFlagEqualTo("0")
				.andStatusEqualTo("1").andTypeEqualTo("2")
				.andStaffInfoIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SpreadActivityPermission> groupSetPermissionList = spreadActivityPermissionService.selectByExample(groupSetPermissionExample);
		List<Integer> spreadActivityGroupSetIdList = new ArrayList<Integer>();
		for(SpreadActivityPermission spreadActivityPermission : groupSetPermissionList ) {
			spreadActivityGroupSetIdList.add(spreadActivityPermission.getSpreadId());
		}
		List<SpreadActivityGroupSetCustom> spreadActivityGroupSetCustomList = null;
		if(spreadActivityGroupSetIdList.size() > 0 ) {
			SpreadActivityGroupSetCustomExample spreadActivityGroupSetCustomExample = new SpreadActivityGroupSetCustomExample();
			SpreadActivityGroupSetCustomExample.Criteria spreadActivityGroupSetCustomCriteria = spreadActivityGroupSetCustomExample.createCriteria();
			spreadActivityGroupSetCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(spreadActivityGroupSetIdList)
				.andDeviceTypeEqualTo(Const.DEVICE_TYPE_IOS);
			spreadActivityGroupSetCustomExample.setOrderByClause(" ifnull(t.seq_no, 999999999), t.id desc");
			spreadActivityGroupSetCustomList = spreadActivityGroupSetService.selectByCustomExample(spreadActivityGroupSetCustomExample);
		}
		m.addObject("spreadActivityGroupSetCustomList", spreadActivityGroupSetCustomList);

		return m;
	}

	@ResponseBody
	@RequestMapping(value = "/spreadActivityGroup/spreadActivityGroupList.shtml")
	public Map<String, Object> spreadActivityGroupList(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String orderDateBegin = request.getParameter("orderDateBegin");
			String orderDateEnd = request.getParameter("orderDateEnd");
			String channel = request.getParameter("channel");
			String activityGroupSetId = request.getParameter("activityGroupSetId");
			String spreadName = request.getParameter("spreadName");
			String visitType = request.getParameter("visitType");
			String channelName = request.getParameter("channelName");
			if(StringUtil.isEmpty(orderDateBegin) || StringUtil.isEmpty(orderDateEnd) || StringUtil.isEmpty(channel) || StringUtil.isEmpty(activityGroupSetId) ) {
				resMap.put("Rows", new ArrayList<HashMap<String, Object>>());
				return resMap;
			}
			Double total_consumption = 0.0;
			Double actual_consumption = 0.0;
			Integer conversion_quantity = 0;
			Double single_active_cost = 0.0;
			Integer new_guest_combine_order_count = 0;
			Integer new_guest_consume_count = 0;
			Double new_guest_cost = 0.0;
			Double new_guest_pay_amount = 0.0;
			Double new_unit_price = 0.0;
			Integer old_guest_combine_order_count = 0;
			Integer old_guest_consume_count = 0;
			Double old_guest_pay_amount = 0.0;
			Double old_unit_price = 0.0;
			Double total_pay_amount = 0.0;
			Double rate = 0.0;
			Double roi = 0.0;
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("orderDateBegin", orderDateBegin+" 00:00:00");
			paramMap.put("orderDateEnd", orderDateEnd+" 23:59:59");
			paramMap.put("channel", channel);
			paramMap.put("activityGroupSetId", activityGroupSetId);
			paramMap.put("deviceType", Const.DEVICE_TYPE_IOS);
			if(!StringUtil.isEmpty(spreadName) ) {
				paramMap.put("spreadName", spreadName);
			}
			if(!StringUtil.isEmpty(visitType) ) {
				paramMap.put("visitType", visitType);
			}
			List<Map<String, Object>> spreadActivityGroupList = combineOrderService.spreadActivityGroupList(paramMap);
			List<Map<String, Object>> newSpreadActivityGroupList = new ArrayList<Map<String,Object>>();
			Date odBegin = sdf.parse(orderDateBegin);
			Date odEnd = sdf.parse(orderDateEnd);
			if (spreadActivityGroupList != null && spreadActivityGroupList.size() > 0 ) {
				for(int i=0;i<spreadActivityGroupList.size();i++ ) {
					Object tmpStr = null;
					tmpStr = spreadActivityGroupList.get(i).get("total_consumption");
					if (tmpStr != null ) {
						total_consumption += Double.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("actual_consumption");
					if (tmpStr != null ) {
						actual_consumption += Double.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("conversion_quantity");
					if (tmpStr != null ) {
						conversion_quantity += Integer.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("single_active_cost");
					if (tmpStr != null ) {
						single_active_cost += Double.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("new_guest_combine_order_count");
					if (tmpStr != null ) {
						new_guest_combine_order_count += Integer.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("new_guest_consume_count");
					if (tmpStr != null ) {
						new_guest_consume_count += Integer.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("new_guest_cost");
					if (tmpStr != null ) {
						new_guest_cost += Double.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("new_guest_pay_amount");
					if (tmpStr != null ) {
						new_guest_pay_amount += Double.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("new_unit_price");
					if (tmpStr != null ) {
						new_unit_price += Double.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("old_guest_combine_order_count");
					if (tmpStr != null ) {
						old_guest_combine_order_count += Integer.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("old_guest_consume_count");
					if (tmpStr != null ) {
						old_guest_consume_count += Integer.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("old_guest_pay_amount");
					if (tmpStr != null ) {
						old_guest_pay_amount += Double.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("old_unit_price");
					if (tmpStr != null ) {
						old_unit_price += Double.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("total_pay_amount");
					if (tmpStr != null ) {
						total_pay_amount += Double.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("rate");
					if (tmpStr !=  null ) {
						rate += Double.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("roi");
					if (tmpStr != null ) {
						roi += Double.valueOf(tmpStr.toString());
					}

					Date createDate = sdf.parse(spreadActivityGroupList.get(i).get("create_date").toString());
					while (createDate.compareTo(odBegin) > 0 ) {
						List<Map<String, Object>> tmpList = new ArrayList<Map<String,Object>>();
						paramMap.put("orderDateBegin", odBegin);

						Calendar calendar = Calendar.getInstance();
						calendar.setTime(createDate);   //设置当前日期
						calendar.add(Calendar.DATE, -1); //日期减1天
						paramMap.put("orderDateEnd", sdf.format(calendar.getTime())+" 23:59:59");

						tmpList = combineOrderService.spreadActivityGroupNull(paramMap);
						for(int j=0;j<tmpList.size();j++ ) {
							Map<String,Object> map = new HashMap<String, Object>();
							map = tmpList.get(j);
							newSpreadActivityGroupList.add(map);
							Object tmpSt = null;
							tmpSt = map.get("total_consumption");
							if (tmpSt != null ) {
								total_consumption += Double.valueOf(tmpSt.toString());
							}
							tmpSt = map.get("actual_consumption");
							if (tmpSt != null ) {
								actual_consumption += Double.valueOf(tmpSt.toString());
							}
							tmpSt = map.get("conversion_quantity");
							if (tmpSt != null ) {
								conversion_quantity += Double.valueOf(tmpSt.toString()).intValue();
							}
							tmpSt = map.get("single_active_cost");
							if (tmpSt != null ) {
								single_active_cost += Double.valueOf(tmpSt.toString());
							}
						}
						odBegin = createDate;
					}
					newSpreadActivityGroupList.add(spreadActivityGroupList.get(i));
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(odBegin); //设置当前日期
					calendar.add(Calendar.DATE, 1); //日期加1天
					odBegin = calendar.getTime();
				}
				if (odEnd.compareTo(odBegin) >= 0 ) {
					List<Map<String, Object>> tmpList = new ArrayList<Map<String,Object>>();
					paramMap.put("orderDateBegin", odBegin);
					paramMap.put("orderDateEnd", orderDateEnd+" 23:59:59");
					tmpList = combineOrderService.spreadActivityGroupNull(paramMap);
					for(int i=0;i<tmpList.size();i++ ) {
						Map<String,Object> map = new HashMap<String, Object>();
						map = tmpList.get(i);
						newSpreadActivityGroupList.add(map);
						Object tmpSt = null;
						tmpSt = map.get("total_consumption");
						if (tmpSt != null ) {
							total_consumption += Double.valueOf(tmpSt.toString());
						}
						tmpSt = map.get("actual_consumption");
						if (tmpSt != null ) {
							actual_consumption += Double.valueOf(tmpSt.toString());
						}
						tmpSt = map.get("conversion_quantity");
						if (tmpSt != null ) {
							conversion_quantity += Double.valueOf(tmpSt.toString()).intValue();
						}
						tmpSt = map.get("single_active_cost");
						if (tmpSt != null ) {
							single_active_cost += Double.valueOf(tmpSt.toString());
						}
					}
				}
			}
			if(newSpreadActivityGroupList != null && newSpreadActivityGroupList.size() > 0) {
				List<Map<String, Object>> listMap = new ArrayList<>();
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("create_date", "合计");
				map.put("activity_group", channelName);
				map.put("total_consumption", (float)Math.round(total_consumption*100)/100);
				map.put("actual_consumption", (float)Math.round(actual_consumption*100)/100);
				map.put("conversion_quantity", conversion_quantity);
				map.put("single_active_cost", conversion_quantity==0?null:(float)Math.round(actual_consumption/conversion_quantity*100)/100);
				map.put("new_guest_combine_order_count", new_guest_combine_order_count);
				map.put("new_guest_consume_count", new_guest_consume_count);
				map.put("new_guest_cost", new_guest_combine_order_count==0?null:(float)Math.round(actual_consumption/new_guest_combine_order_count*100)/100);
				map.put("new_guest_pay_amount", (float)Math.round(new_guest_pay_amount*100)/100);
				map.put("new_unit_price", (float)Math.round(new_guest_pay_amount/new_guest_combine_order_count*100)/100);
				map.put("old_guest_combine_order_count", old_guest_combine_order_count);
				map.put("old_guest_consume_count", old_guest_consume_count);
				map.put("old_guest_pay_amount", (float)Math.round(old_guest_pay_amount*100)/100);
				map.put("old_unit_price", old_guest_consume_count==0?null:(float)Math.round(old_guest_pay_amount/old_guest_consume_count*100)/100);
				map.put("total_pay_amount", (float)Math.round(total_pay_amount*100)/100);
				map.put("rate", old_guest_consume_count==0?null:(float)Math.round((float)new_guest_consume_count/old_guest_consume_count*100)/100);
				map.put("roi", actual_consumption==0.0?null:(float)Math.round(total_pay_amount/actual_consumption*100)/100);
				listMap.add(map);
				newSpreadActivityGroupList.addAll(listMap);
			}
			resMap.put("Rows", newSpreadActivityGroupList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}


	@RequestMapping(value = "/spreadChannelData/spreadChannelDataManager.shtml")
	public ModelAndView spreadChannelDataManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/promotionData/spreadChannelDataList");
		List<SysStatus> channelList = DataDicUtil.getStatusList("BU_SPREAD_ACTIVITY_GROUP", "CHANNEL");
		m.addObject("channelList", channelList);
		m.addObject("reportDayDate", DateUtil.formatDateByFormat(DateUtil.getDateAfter(new Date(), -1), "yyyy-MM-dd"));
		m.addObject("reportMonthDate", DateUtil.formatDateByFormat(DateUtil.getMonthsAgo(new Date(), -1), "yyyy-MM"));
		return m;
	}

	@ResponseBody
	@RequestMapping(value = "/spreadChannelData/spreadChannelDataList.shtml")
	public Map<String, Object> spreadChannelDataList(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, String>> listMap = new ArrayList<>();
		boolean flag = false;
		try {
			String reportType = request.getParameter("reportType");
			String reportDayDateBegin = request.getParameter("reportDayDateBegin");
			String reportDayDateEnd = request.getParameter("reportDayDateEnd");
			String reportMonthDateBegin = request.getParameter("reportMonthDateBegin");
			String reportMonthDateEnd = request.getParameter("reportMonthDateEnd");
			//1,显示为零渠道;2,隐藏为零渠道
			String showStatus = "1";
			if (!StringUtils.isEmpty(request.getParameter("showStatus"))) {
				showStatus = request.getParameter("showStatus");
			}
			String channelStr = request.getParameter("channel");
			String channel = null;
			String channelInt = null;
			if(!StringUtil.isEmpty(reportType) ) {
				Map<String, Object> paramMap = new HashMap<>();
				if(!StringUtil.isEmpty(channelStr) ) {
					flag = true;
					String[] channelS = channelStr.split(",");
					channel = channelS[1];
					channelInt = channelS[0];
				}
				paramMap.put("channel", channel);
				paramMap.put("channelInt", channelInt);
				if("day".equals(reportType) && !StringUtil.isEmpty(reportDayDateBegin) && !StringUtil.isEmpty(reportDayDateEnd) ){
					paramMap.put("reportDateBegin", reportDayDateBegin);
					paramMap.put("reportDateEnd", reportDayDateEnd);
				}else if("month".equals(reportType) && !StringUtil.isEmpty(reportMonthDateBegin) && !StringUtil.isEmpty(reportMonthDateEnd) ) {
					paramMap.put("reportDateBegin", DateUtil.getMonthBegin(reportMonthDateBegin+"-01"));
					paramMap.put("reportDateEnd", DateUtil.getMonthEnd(reportMonthDateEnd+"-01"));
				}else {
					resMap.put("Rows", listMap);
					return resMap;
				}
				List<Map<String, Object>> iosList = combineOrderService.iosSpreadChannelDataList(paramMap);
				List<Map<String, Object>> androidList = combineOrderService.androidSpreadChannelDataList(paramMap);

				SysStatusExample sysStatusExample = new SysStatusExample();
				SysStatusExample.Criteria sysStatusCriteria = sysStatusExample.createCriteria();
				sysStatusCriteria.andTableNameEqualTo("BU_SPREAD_ACTIVITY_GROUP").andColNameEqualTo("CHANNEL");
				if(!StringUtil.isEmpty(channel) ) {
					sysStatusCriteria.andStatusDescEqualTo(channel);
				}
				List<SysStatus> channelList = sysStatusService.selectByExample(sysStatusExample);

				for(SysStatus ss : channelList ) {
					Map<String, Object> iosMap = null;
					for(Map<String, Object> ios : iosList ) {
						if(ios.get("channel") != null && ios.get("channel").toString().equals(ss.getStatusDesc()) ) {
							iosMap = ios;
						}
					}
					Map<String, String> iosReturnMap = new HashMap<>();
					iosReturnMap.put("channel", ss.getStatusDesc());
					iosReturnMap.put("channel_type", "IOS");
					if(iosMap != null && ("1".equals(showStatus) || !"0.00".equals(iosMap.get("actual_consumption").toString())) ) {
						iosReturnMap.put("total_pay_amount_sum", iosMap.get("total_pay_amount_sum").toString());
						iosReturnMap.put("total_consumption_sum", iosMap.get("total_consumption_sum").toString());
						iosReturnMap.put("actual_consumption", iosMap.get("actual_consumption").toString());
						iosReturnMap.put("conversion_quantity_sum", iosMap.get("conversion_quantity_sum").toString());
						iosReturnMap.put("each_conversion_quantity", Integer.parseInt(iosMap.get("conversion_quantity_sum").toString())==0?"0.00":
								new BigDecimal(iosMap.get("actual_consumption").toString()).divide(new BigDecimal(iosMap.get("conversion_quantity_sum").toString()), 2, BigDecimal.ROUND_DOWN).toString());
						iosReturnMap.put("new_guest_combine_order_count", iosMap.get("new_guest_combine_order_count").toString());
						iosReturnMap.put("new_conversion_quantity", Integer.parseInt(iosMap.get("new_guest_combine_order_count").toString())==0?"0.00":
								new BigDecimal(iosMap.get("actual_consumption").toString()).divide(new BigDecimal(iosMap.get("new_guest_combine_order_count").toString()), 2, BigDecimal.ROUND_DOWN).toString());
						listMap.add(iosReturnMap);
					}else if(flag){
						iosReturnMap.put("total_pay_amount_sum", "0.00");
						iosReturnMap.put("total_consumption_sum", "0.00");
						iosReturnMap.put("actual_consumption", "0.00");
						iosReturnMap.put("conversion_quantity_sum", "0");
						iosReturnMap.put("each_conversion_quantity", "0.00");
						iosReturnMap.put("new_guest_combine_order_count", "0");
						iosReturnMap.put("new_conversion_quantity", "0.00");
						listMap.add(iosReturnMap);
					}

					Map<String, Object> androidMap = null;
					for(Map<String, Object> android : androidList ) {
						if(android.get("channel") != null && android.get("channel").toString().equals(ss.getStatusDesc()) ) {
							androidMap = android;
						}
					}
					Map<String, String> androidReturnMap = new HashMap<>();
					androidReturnMap.put("channel", ss.getStatusDesc());
					androidReturnMap.put("channel_type", "Android");
					if(androidMap != null && ("1".equals(showStatus) || !"0.00".equals(iosMap.get("actual_consumption").toString())) ) {
						androidReturnMap.put("total_pay_amount_sum", androidMap.get("total_pay_amount_sum").toString());
						androidReturnMap.put("total_consumption_sum", androidMap.get("total_consumption_sum").toString());
						androidReturnMap.put("actual_consumption", androidMap.get("actual_consumption").toString());
						androidReturnMap.put("conversion_quantity_sum", androidMap.get("conversion_quantity_sum").toString());
						androidReturnMap.put("each_conversion_quantity", Integer.parseInt(androidMap.get("conversion_quantity_sum").toString())==0?"0.00":
								new BigDecimal(androidMap.get("actual_consumption").toString()).divide(new BigDecimal(androidMap.get("conversion_quantity_sum").toString()), 2, BigDecimal.ROUND_DOWN).toString());
						androidReturnMap.put("new_guest_combine_order_count", androidMap.get("new_guest_combine_order_count").toString());
						androidReturnMap.put("new_conversion_quantity", Integer.parseInt(androidMap.get("new_guest_combine_order_count").toString())==0?"0.00":
								new BigDecimal(androidMap.get("actual_consumption").toString()).divide(new BigDecimal(androidMap.get("new_guest_combine_order_count").toString()), 2, BigDecimal.ROUND_DOWN).toString());
						listMap.add(androidReturnMap);
					}else if(flag){
						androidReturnMap.put("total_pay_amount_sum", "0.00");
						androidReturnMap.put("total_consumption_sum", "0.00");
						androidReturnMap.put("actual_consumption", "0.00");
						androidReturnMap.put("conversion_quantity_sum", "0");
						androidReturnMap.put("each_conversion_quantity", "0.00");
						androidReturnMap.put("new_guest_combine_order_count", "0");
						androidReturnMap.put("new_conversion_quantity", "0.00");
						listMap.add(androidReturnMap);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", listMap);
		return resMap;
	}

	@ResponseBody
	@RequestMapping("/spreadChannelData/spreadChannelDataTotal.shtml")
	public Map<String, Object> spreadChannelDataTotal(HttpServletRequest request, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			String reportType = request.getParameter("reportType");
			String reportDayDateBegin = request.getParameter("reportDayDateBegin");
			String reportDayDateEnd = request.getParameter("reportDayDateEnd");
			String reportMonthDateBegin = request.getParameter("reportMonthDateBegin");
			String reportMonthDateEnd = request.getParameter("reportMonthDateEnd");
			String showStatus = request.getParameter("showStatus");
			String channelStr = request.getParameter("channel");
			String channel = null;
			String channelInt = null;
			if(!StringUtil.isEmpty(reportType) ) {
				Map<String, Object> paramMap = new HashMap<>();
				if(!StringUtil.isEmpty(channelStr) ) {
					String[] channelS = channelStr.split(",");
					channel = channelS[1];
					channelInt = channelS[0];
				}
				paramMap.put("channel", channel);
				paramMap.put("channelInt", channelInt);
				if("month".equals(reportType) && !StringUtil.isEmpty(reportMonthDateBegin) && !StringUtil.isEmpty(reportMonthDateEnd) ) {
					paramMap.put("reportDateBegin", DateUtil.getMonthBegin(reportMonthDateBegin+"-01"));
					paramMap.put("reportDateEnd", DateUtil.getMonthEnd(reportMonthDateEnd+"-01"));
				}else {
					paramMap.put("reportDateBegin", reportDayDateBegin);
					paramMap.put("reportDateEnd", reportDayDateEnd);
				}
				if(!StringUtils.isEmpty(showStatus) && "2".equals(showStatus)){
					paramMap.put("showStatus", showStatus);
				}
				Map<String, Object> iosMap = combineOrderService.iosSpreadChannelDataTotal(paramMap);
				Map<String, Object> androidMap = combineOrderService.androidSpreadChannelDataTotal(paramMap);
				Map<String, Object> coMap = combineOrderService.getTotalPayAmountSum(paramMap);

				Integer new_guest_combine_order_count = Integer.parseInt(iosMap.get("new_guest_combine_order_count").toString())+Integer.parseInt(androidMap.get("new_guest_combine_order_count").toString());
				BigDecimal actual_consumption = new BigDecimal(iosMap.get("actual_consumption").toString()).add(new BigDecimal(androidMap.get("actual_consumption").toString()));
				BigDecimal total_pay_amount_sum = new BigDecimal(iosMap.get("total_pay_amount_sum").toString()).add(new BigDecimal(androidMap.get("total_pay_amount_sum").toString()));
				BigDecimal co_pay_amount_sum = new BigDecimal(coMap.get("co_pay_amount_sum").toString());
				Integer combine_order_count = Integer.parseInt(coMap.get("combine_order_count").toString());

				BigDecimal entirety_new_shop = new_guest_combine_order_count.intValue()==0?new BigDecimal("0.00"):actual_consumption.divide(new BigDecimal(new_guest_combine_order_count.toString()),2, BigDecimal.ROUND_DOWN);
				BigDecimal each_member_combine_order_pirce = combine_order_count.intValue()==0?new BigDecimal("0.00"):co_pay_amount_sum.divide(new BigDecimal(combine_order_count.toString()),2, BigDecimal.ROUND_DOWN);
				BigDecimal roi = actual_consumption.intValue()==0?new BigDecimal("0.00"):co_pay_amount_sum.divide(actual_consumption,2, BigDecimal.ROUND_DOWN);

				map.put("entirety_new_shop", entirety_new_shop.toString());
				map.put("actual_consumption", actual_consumption.toString());
				map.put("total_pay_amount_sum", total_pay_amount_sum.toString());
				map.put("combine_order_count", combine_order_count.toString());
				map.put("each_member_combine_order_pirce", each_member_combine_order_pirce.toString());
				map.put("co_pay_amount_sum", co_pay_amount_sum.toString());
				map.put("roi", roi.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}

	@RequestMapping("/spreadChannelData/updateDiscountRateBatchManager.shtml")
	public ModelAndView updateDiscountRateBatchManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/promotionData/updateDiscountRateBatch");
		AndroidChannelGroupExample androidChannelGroupExample = new AndroidChannelGroupExample();
		androidChannelGroupExample.createCriteria().andDelFlagEqualTo("0");
		androidChannelGroupExample.setOrderByClause(" IFNULL(seq_no, 999999) asc, id desc");
		List<AndroidChannelGroup> androidChannelGroupList = androidChannelGroupService.selectByExample(androidChannelGroupExample);
		List<JSONObject> androidChannelGroupJson = new ArrayList();
		JSONObject listBoxJson;
		for(AndroidChannelGroup androidChannelGroup : androidChannelGroupList ) {
			listBoxJson = new JSONObject();
			listBoxJson.put("id", androidChannelGroup.getId());
			listBoxJson.put("text", androidChannelGroup.getGroupName());
			androidChannelGroupJson.add(listBoxJson);
		}
		m.addObject("androidChannelGroupJson", androidChannelGroupJson);
		return m;
	}

	@RequestMapping("/spreadChannelData/updateDiscountRateBatch.shtml")
	public ModelAndView updateDiscountRateBatch(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			if(!StringUtil.isEmpty(request.getParameter("discountRate")) && !StringUtil.isEmpty(request.getParameter("ids")) ) {
				String staffId = this.getSessionStaffBean(request).getStaffID();
				AndroidChannelGroupExample androidChannelGroupExample = new AndroidChannelGroupExample();
				AndroidChannelGroupExample.Criteria androidChannelGroupCriteria = androidChannelGroupExample.createCriteria();
				if(!StringUtil.isEmpty(request.getParameter("ids")) ) {
					String[] ids = request.getParameter("ids").split(",");
					List<Integer> idList = new ArrayList<>();
					for(String id : ids ) {
						idList.add(Integer.parseInt(id));
					}
					androidChannelGroupCriteria.andIdIn(idList);
				}
				AndroidChannelGroup androidChannelGroup = new AndroidChannelGroup();
				androidChannelGroup.setDiscountRate(new BigDecimal(request.getParameter("discountRate")));
				androidChannelGroup.setUpdateBy(Integer.parseInt(staffId));
				androidChannelGroup.setUpdateDate(new Date());
				androidChannelGroupService.updateByExampleSelective(androidChannelGroup, androidChannelGroupExample);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}


	/**
	 * 安卓活动统计列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/androidActivityCount/list.shtml")
	public ModelAndView androidActivityCountList(HttpServletRequest request) {
		String rtPage = "/dataCenter/promotionData/androidActivityCountList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SysStatus> channelList = DataDicUtil.getStatusList("BU_SPREAD_ACTIVITY_GROUP", "CHANNEL");
		resMap.put("channelList", channelList);

		SpreadActivityPermissionExample groupPermissionExample = new SpreadActivityPermissionExample();
		groupPermissionExample.createCriteria().andDelFlagEqualTo("0")
				.andStatusEqualTo("1").andTypeEqualTo("1")
				.andStaffInfoIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SpreadActivityPermission> groupPermissionList = spreadActivityPermissionService.selectByExample(groupPermissionExample);
		List<Integer> spreadActivityGroupIdList = new ArrayList<Integer>();
		for(SpreadActivityPermission spreadActivityPermission : groupPermissionList ) {
			spreadActivityGroupIdList.add(spreadActivityPermission.getSpreadId());
		}
		List<SpreadActivityGroupCustom> spreadActivityGroupList = null;
		if(spreadActivityGroupIdList.size() > 0) {
			SpreadActivityGroupExample spreadActivityGroupExample = new SpreadActivityGroupExample();
			SpreadActivityGroupExample.Criteria secondProductTypesCriteria = spreadActivityGroupExample.createCriteria();
			secondProductTypesCriteria.andDelFlagEqualTo("0").andIsEffectEqualTo("1").andIdIn(spreadActivityGroupIdList)
					.andDeviceTypeEqualTo(Const.DEVICE_TYPE_ANDROID);
			spreadActivityGroupExample.setOrderByClause(" IFNULL(t.seq_no, 99999999999) asc, t.create_date asc");
			spreadActivityGroupList = spreadActivityGroupService.selectSpreadActivityGroupCustomByExample(spreadActivityGroupExample);
		}
		resMap.put("spreadActivityGroupList", spreadActivityGroupList);

		SpreadActivityPermissionExample groupSetPermissionExample = new SpreadActivityPermissionExample();
		groupSetPermissionExample.createCriteria().andDelFlagEqualTo("0")
				.andStatusEqualTo("1").andTypeEqualTo("2")
				.andStaffInfoIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SpreadActivityPermission> groupSetPermissionList = spreadActivityPermissionService.selectByExample(groupSetPermissionExample);
		List<Integer> spreadActivityGroupSetIdList = new ArrayList<Integer>();
		for(SpreadActivityPermission spreadActivityPermission : groupSetPermissionList ) {
			spreadActivityGroupSetIdList.add(spreadActivityPermission.getSpreadId());
		}
		List<SpreadActivityGroupSetCustom> spreadActivityGroupSetCustomList = null;
		if(spreadActivityGroupSetIdList.size() > 0 ) {
			SpreadActivityGroupSetCustomExample spreadActivityGroupSetCustomExample = new SpreadActivityGroupSetCustomExample();
			SpreadActivityGroupSetCustomExample.Criteria spreadActivityGroupSetCustomCriteria = spreadActivityGroupSetCustomExample.createCriteria();
			spreadActivityGroupSetCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(spreadActivityGroupSetIdList)
					.andDeviceTypeEqualTo(Const.DEVICE_TYPE_ANDROID);
			spreadActivityGroupSetCustomExample.setOrderByClause(" ifnull(t.seq_no, 999999999), t.id desc");
			spreadActivityGroupSetCustomList = spreadActivityGroupSetService.selectByCustomExample(spreadActivityGroupSetCustomExample);
		}
		resMap.put("spreadActivityGroupSetCustomList", spreadActivityGroupSetCustomList);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateBegin = df.format(new Date());
		String dateEnd = dateBegin;
		resMap.put("order_date_begin", dateBegin);
		resMap.put("order_date_end", dateEnd);

		return new ModelAndView(rtPage,resMap);
	}

	/**
	 * 安卓活动统计列表数据
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/androidActivityCount/data.shtml")
	public Map<String, Object> androidActivityCountData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String orderDateBegin = request.getParameter("order_date_begin");
			String orderDateEnd = request.getParameter("order_date_end");
			String channel = request.getParameter("channel");
			String activityGroupId = request.getParameter("activityGroupId");
			String spreadName = request.getParameter("spreadName");
			String visitType = request.getParameter("visitType");
			if(StringUtil.isEmpty(orderDateBegin) || StringUtil.isEmpty(orderDateEnd) || StringUtil.isEmpty(channel)){
				resMap.put("Rows", new ArrayList<HashMap<String, Object>>());
				return resMap;
			}
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("orderDateBegin",orderDateBegin+" 00:00:00");
			paramMap.put("orderDateEnd",orderDateEnd+" 23:59:59");
			paramMap.put("channel",channel);
			if(!StringUtil.isEmpty(activityGroupId)) {
				if(activityGroupId.contains("A") ) {
					paramMap.put("activityGroupId",Integer.parseInt(activityGroupId.split("-")[1]));
				}else if(activityGroupId.contains("B") ) {
					paramMap.put("activityGroupSetId",Integer.parseInt(activityGroupId.split("-")[1]));
				}
			}
			if(!StringUtil.isEmpty(spreadName)) {
				paramMap.put("spreadName",spreadName);
			}
			if(!StringUtil.isEmpty(visitType) ) {
				paramMap.put("visitType",visitType);
			}
			paramMap.put("deviceType",Const.DEVICE_TYPE_ANDROID);
			List<HashMap<String, Object>> eachSprChnlList = combineOrderService.iosCount(paramMap);
			List<HashMap<String, Object>> list = combineOrderService.totalIosCount(paramMap);
			eachSprChnlList.addAll(list);
			resMap.put("Rows", eachSprChnlList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 安卓活动统计（新）列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/androidActivityCountNew/list.shtml")
	public ModelAndView androidActivityCountNewList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/promotionData/androidActivityCountNewList");
		List<SysStatus> channelList = DataDicUtil.getStatusList("BU_SPREAD_ACTIVITY_GROUP", "CHANNEL");
		m.addObject("channelList", channelList);

		SpreadActivityPermissionExample groupPermissionExample = new SpreadActivityPermissionExample();
		groupPermissionExample.createCriteria().andDelFlagEqualTo("0")
				.andStatusEqualTo("1").andTypeEqualTo("1")
				.andStaffInfoIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SpreadActivityPermission> groupPermissionList = spreadActivityPermissionService.selectByExample(groupPermissionExample);
		List<Integer> spreadActivityGroupIdList = new ArrayList<Integer>();
		for(SpreadActivityPermission spreadActivityPermission : groupPermissionList ) {
			spreadActivityGroupIdList.add(spreadActivityPermission.getSpreadId());
		}
		List<SpreadActivityGroupCustom> spreadActivityGroupList = null;
		if(spreadActivityGroupIdList.size() > 0) {
			SpreadActivityGroupExample spreadActivityGroupExample = new SpreadActivityGroupExample();
			SpreadActivityGroupExample.Criteria secondProductTypesCriteria = spreadActivityGroupExample.createCriteria();
			secondProductTypesCriteria.andDelFlagEqualTo("0").andIsEffectEqualTo("1").andIdIn(spreadActivityGroupIdList)
					.andDeviceTypeEqualTo(Const.DEVICE_TYPE_ANDROID);
			spreadActivityGroupExample.setOrderByClause(" IFNULL(t.seq_no, 99999999999) asc, t.create_date asc");
			spreadActivityGroupList = spreadActivityGroupService.selectSpreadActivityGroupCustomByExample(spreadActivityGroupExample);
		}
		m.addObject("spreadActivityGroupList", spreadActivityGroupList);

		SpreadActivityPermissionExample groupSetPermissionExample = new SpreadActivityPermissionExample();
		groupSetPermissionExample.createCriteria().andDelFlagEqualTo("0")
				.andStatusEqualTo("1").andTypeEqualTo("2")
				.andStaffInfoIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SpreadActivityPermission> groupSetPermissionList = spreadActivityPermissionService.selectByExample(groupSetPermissionExample);
		List<Integer> spreadActivityGroupSetIdList = new ArrayList<Integer>();
		for(SpreadActivityPermission spreadActivityPermission : groupSetPermissionList ) {
			spreadActivityGroupSetIdList.add(spreadActivityPermission.getSpreadId());
		}
		List<SpreadActivityGroupSetCustom> spreadActivityGroupSetCustomList = null;
		if(spreadActivityGroupSetIdList.size() > 0 ) {
			SpreadActivityGroupSetCustomExample spreadActivityGroupSetCustomExample = new SpreadActivityGroupSetCustomExample();
			SpreadActivityGroupSetCustomExample.Criteria spreadActivityGroupSetCustomCriteria = spreadActivityGroupSetCustomExample.createCriteria();
			spreadActivityGroupSetCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(spreadActivityGroupSetIdList)
					.andDeviceTypeEqualTo(Const.DEVICE_TYPE_ANDROID);
			spreadActivityGroupSetCustomExample.setOrderByClause(" ifnull(t.seq_no, 999999999), t.id desc");
			spreadActivityGroupSetCustomList = spreadActivityGroupSetService.selectByCustomExample(spreadActivityGroupSetCustomExample);
		}
		m.addObject("spreadActivityGroupSetCustomList", spreadActivityGroupSetCustomList);

		return m;
	}

	/**
	 * 安卓活动统计（新）列表数据
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/androidActivityCountNew/data.shtml")
	public Map<String, Object> androidActivityCountNewData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String orderDateBegin = request.getParameter("order_date_begin");
			String orderDateEnd = request.getParameter("order_date_end");
			String channel = request.getParameter("channel");
			String activityGroupId = request.getParameter("activityGroupId");
			String spreadName = request.getParameter("spreadName");
			String visitType = request.getParameter("visitType");
			String channelName = request.getParameter("channelName");
			Double total_consumption= 0.0;
			Double actual_consumption=0.0;
			Integer conversion_quantity=0;
			Double single_active_cost=0.0;
			Integer new_guest_combine_order_count=0;
			Integer new_guest_consume_count=0;
			Double new_guest_cost=0.0;
			Double new_guest_pay_amount=0.0;
			Double new_unit_price=0.0;
			Integer old_guest_combine_order_count=0;
			Integer old_guest_consume_count=0;
			Double old_guest_pay_amount=0.0;
			Double old_unit_price=0.0;
			Double total_pay_amount=0.0;
			Double rate=0.0;
			Double roi=0.0;
			if(StringUtil.isEmpty(orderDateBegin) || StringUtil.isEmpty(orderDateEnd) || StringUtil.isEmpty(channel) || StringUtil.isEmpty(activityGroupId)){
				resMap.put("Rows", new ArrayList<HashMap<String, Object>>());
				return resMap;
			}
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("orderDateBegin",orderDateBegin+" 00:00:00");
			paramMap.put("orderDateEnd",orderDateEnd+" 23:59:59");
			paramMap.put("channel",channel);
			paramMap.put("channelName",channelName);
			paramMap.put("deviceType", Const.DEVICE_TYPE_ANDROID);
			if(!StringUtil.isEmpty(activityGroupId)) {
				if(activityGroupId.contains("A") ) {
					paramMap.put("activityGroupId",Integer.parseInt(activityGroupId.split("-")[1]));
				}else if(activityGroupId.contains("B") ) {
					paramMap.put("activityGroupSetId",Integer.parseInt(activityGroupId.split("-")[1]));
				}
			}
			if(!StringUtil.isEmpty(spreadName)) {
				paramMap.put("spreadName",spreadName);
			}
			if(!StringUtil.isEmpty(visitType) ) {
				paramMap.put("visitType",visitType);
			}
			List<HashMap<String, Object>> eachSprChnlList = combineOrderService.iosCountNew(paramMap);
			//List<HashMap<String, Object>> list = combineOrderService.totalIosCount(paramMap);
			List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();

			List<HashMap<String, Object>> newSprChnlList=new ArrayList<HashMap<String,Object>>();
			SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
			Date date1 = formatter.parse(orderDateBegin);
			Date date2 = formatter.parse(orderDateEnd);

			if (eachSprChnlList==null || eachSprChnlList.size()==0){
				newSprChnlList = combineOrderService.iosCountNewNull(paramMap);
				for(int i=0;i<newSprChnlList.size();i++){
					Object tmpStr=null;
					tmpStr=newSprChnlList.get(i).get("total_consumption");
					if (tmpStr!=null){
						total_consumption+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=newSprChnlList.get(i).get("actual_consumption");
					if (tmpStr!=null){
						actual_consumption+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=newSprChnlList.get(i).get("conversion_quantity");
					if (tmpStr!=null){
						conversion_quantity+=Double.valueOf(tmpStr.toString()).intValue();
					}
					tmpStr=newSprChnlList.get(i).get("single_active_cost");
					if (tmpStr!=null){
						single_active_cost+=Double.valueOf(tmpStr.toString());
					}
				}
			}else{
				for(int i=0;i<eachSprChnlList.size();i++){
					Object tmpStr=null;
					tmpStr=eachSprChnlList.get(i).get("total_consumption");
					if (tmpStr!=null){
						total_consumption+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("actual_consumption");
					if (tmpStr!=null){
						actual_consumption+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("conversion_quantity");
					if (tmpStr!=null){
						conversion_quantity+=Integer.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("single_active_cost");
					if (tmpStr!=null){
						single_active_cost+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("new_guest_combine_order_count");
					if (tmpStr!=null){
						new_guest_combine_order_count+=Integer.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("new_guest_consume_count");
					if (tmpStr!=null){
						new_guest_consume_count+=Integer.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("new_guest_cost");
					if (tmpStr!=null){
						new_guest_cost+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("new_guest_pay_amount");
					if (tmpStr!=null){
						new_guest_pay_amount+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("new_unit_price");
					if (tmpStr!=null){
						new_unit_price+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("old_guest_combine_order_count");
					if (tmpStr!=null){
						old_guest_combine_order_count+=Integer.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("old_guest_consume_count");
					if (tmpStr!=null){
						old_guest_consume_count+=Integer.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("old_guest_pay_amount");
					if (tmpStr!=null){
						old_guest_pay_amount+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("old_unit_price");
					if (tmpStr!=null){
						old_unit_price+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("total_pay_amount");
					if (tmpStr!=null){
						total_pay_amount+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("rate");
					if (tmpStr!=null){
						rate+=Double.valueOf(tmpStr.toString());
					}
					tmpStr=eachSprChnlList.get(i).get("roi");
					if (tmpStr!=null){
						roi+=Double.valueOf(tmpStr.toString());
					}

					Date date3=formatter.parse(eachSprChnlList.get(i).get("create_date").toString());
					while (date3.compareTo(date1)>0){
						List<HashMap<String, Object>> tmpList=new ArrayList<HashMap<String,Object>>();
						paramMap.put("orderDateBegin",date1);

						Calendar c0 = Calendar.getInstance();
						c0.setTime(date3);   //设置当前日期
						c0.add(Calendar.DATE, -1); //日期减1天
						Date date4 = c0.getTime();

						paramMap.put("orderDateEnd",formatter.format(date4)+" 23:59:59");

						tmpList = combineOrderService.iosCountNewNull(paramMap);
						for(int j=0;j<tmpList.size();j++){
							HashMap<String,Object> p = new HashMap<String, Object>();
							p=tmpList.get(j);
							newSprChnlList.add(p);
							Object tmpStr2=null;
							tmpStr2=p.get("total_consumption");
							if (tmpStr2!=null){
								total_consumption+=Double.valueOf(tmpStr2.toString());
							}
							tmpStr2=p.get("actual_consumption");
							if (tmpStr2!=null){
								actual_consumption+=Double.valueOf(tmpStr2.toString());
							}
							tmpStr2=p.get("conversion_quantity");
							if (tmpStr2!=null){
								conversion_quantity+=Double.valueOf(tmpStr2.toString()).intValue();
							}
							tmpStr2=p.get("single_active_cost");
							if (tmpStr2!=null){
								single_active_cost+=Double.valueOf(tmpStr2.toString());
							}
						}
						date1 = date3;
					}
					newSprChnlList.add(eachSprChnlList.get(i));
					Calendar c = Calendar.getInstance();
					c.setTime(date1);   //设置当前日期
					c.add(Calendar.DATE, 1); //日期加1天
					date1 = c.getTime();
				}
				if (date2.compareTo(date1)>=0){
					List<HashMap<String, Object>> tmpList=new ArrayList<HashMap<String,Object>>();
					paramMap.put("orderDateBegin",date1);
					paramMap.put("orderDateEnd",orderDateEnd+" 23:59:59");
					tmpList = combineOrderService.iosCountNewNull(paramMap);
					for(int i=0;i<tmpList.size();i++){
						HashMap<String,Object> p = new HashMap<String, Object>();
						p=tmpList.get(i);
						newSprChnlList.add(p);
						Object tmpStr3=null;
						tmpStr3=p.get("total_consumption");
						if (tmpStr3!=null){
							total_consumption+=Double.valueOf(tmpStr3.toString());
						}
						tmpStr3=p.get("actual_consumption");
						if (tmpStr3!=null){
							actual_consumption+=Double.valueOf(tmpStr3.toString());
						}
						tmpStr3=p.get("conversion_quantity");
						if (tmpStr3!=null){
							conversion_quantity+=Double.valueOf(tmpStr3.toString()).intValue();
						}
						tmpStr3=p.get("single_active_cost");
						if (tmpStr3!=null){
							single_active_cost+=Double.valueOf(tmpStr3.toString());
						}
					}
				}
			}

			if(newSprChnlList != null && newSprChnlList.size() > 0) {
				HashMap<String,Object> o = new HashMap<String, Object>();
				o.put("spreadname", paramMap.get("channelName"));
				o.put("create_date", "合计");
				o.put("total_consumption", (float)Math.round(total_consumption*100)/100);
				o.put("actual_consumption", (float)Math.round(actual_consumption*100)/100);
				o.put("conversion_quantity", conversion_quantity);
				o.put("single_active_cost", conversion_quantity==0?null:(float)Math.round(actual_consumption/conversion_quantity*100)/100);
				o.put("new_guest_combine_order_count", new_guest_combine_order_count);
				o.put("new_guest_consume_count", new_guest_consume_count);
				o.put("new_guest_cost", new_guest_combine_order_count==0?null:(float)Math.round(actual_consumption/new_guest_combine_order_count*100)/100);
				o.put("new_guest_pay_amount", (float)Math.round(new_guest_pay_amount*100)/100);
				o.put("new_unit_price", (float)Math.round(new_guest_pay_amount/new_guest_combine_order_count*100)/100);
				o.put("old_guest_combine_order_count", old_guest_combine_order_count);
				o.put("old_guest_consume_count", old_guest_consume_count);
				o.put("old_guest_pay_amount", (float)Math.round(old_guest_pay_amount*100)/100);
				o.put("old_unit_price", old_guest_consume_count==0?null:(float)Math.round(old_guest_pay_amount/old_guest_consume_count*100)/100);
				o.put("total_pay_amount", (float)Math.round(total_pay_amount*100)/100);
				o.put("rate", old_guest_consume_count==0?null:(float)Math.round((float)new_guest_consume_count/old_guest_consume_count*100)/100);
				o.put("roi", actual_consumption==0.0?null:(float)Math.round(total_pay_amount/actual_consumption*100)/100);
				list.add(o);
			}
			newSprChnlList.addAll(list);
			resMap.put("Rows", newSprChnlList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 安卓活动统计（退货）列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/androidActivityCountReturn/list.shtml")
	public ModelAndView androidActivityCountReturnList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/promotionData/androidActivityCountReturnList");
		List<SysStatus> channelList = DataDicUtil.getStatusList("BU_SPREAD_ACTIVITY_GROUP", "CHANNEL");
		m.addObject("channelList", channelList);

		SpreadActivityPermissionExample groupPermissionExample = new SpreadActivityPermissionExample();
		groupPermissionExample.createCriteria().andDelFlagEqualTo("0")
				.andStatusEqualTo("1").andTypeEqualTo("1")
				.andStaffInfoIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SpreadActivityPermission> groupPermissionList = spreadActivityPermissionService.selectByExample(groupPermissionExample);
		List<Integer> spreadActivityGroupIdList = new ArrayList<Integer>();
		for(SpreadActivityPermission spreadActivityPermission : groupPermissionList ) {
			spreadActivityGroupIdList.add(spreadActivityPermission.getSpreadId());
		}
		List<SpreadActivityGroupCustom> spreadActivityGroupList = null;
		if(spreadActivityGroupIdList.size() > 0) {
			SpreadActivityGroupExample spreadActivityGroupExample = new SpreadActivityGroupExample();
			SpreadActivityGroupExample.Criteria secondProductTypesCriteria = spreadActivityGroupExample.createCriteria();
			secondProductTypesCriteria.andDelFlagEqualTo("0").andIsEffectEqualTo("1").andIdIn(spreadActivityGroupIdList)
					.andDeviceTypeEqualTo(Const.DEVICE_TYPE_ANDROID);
			spreadActivityGroupExample.setOrderByClause(" IFNULL(t.seq_no, 99999999999) asc, t.create_date asc");
			spreadActivityGroupList = spreadActivityGroupService.selectSpreadActivityGroupCustomByExample(spreadActivityGroupExample);
		}
		m.addObject("spreadActivityGroupList", spreadActivityGroupList);

		SpreadActivityPermissionExample groupSetPermissionExample = new SpreadActivityPermissionExample();
		groupSetPermissionExample.createCriteria().andDelFlagEqualTo("0")
				.andStatusEqualTo("1").andTypeEqualTo("2")
				.andStaffInfoIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SpreadActivityPermission> groupSetPermissionList = spreadActivityPermissionService.selectByExample(groupSetPermissionExample);
		List<Integer> spreadActivityGroupSetIdList = new ArrayList<Integer>();
		for(SpreadActivityPermission spreadActivityPermission : groupSetPermissionList ) {
			spreadActivityGroupSetIdList.add(spreadActivityPermission.getSpreadId());
		}
		List<SpreadActivityGroupSetCustom> spreadActivityGroupSetCustomList = null;
		if(spreadActivityGroupSetIdList.size() > 0 ) {
			SpreadActivityGroupSetCustomExample spreadActivityGroupSetCustomExample = new SpreadActivityGroupSetCustomExample();
			SpreadActivityGroupSetCustomExample.Criteria spreadActivityGroupSetCustomCriteria = spreadActivityGroupSetCustomExample.createCriteria();
			spreadActivityGroupSetCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(spreadActivityGroupSetIdList)
					.andDeviceTypeEqualTo(Const.DEVICE_TYPE_ANDROID);
			spreadActivityGroupSetCustomExample.setOrderByClause(" ifnull(t.seq_no, 999999999), t.id desc");
			spreadActivityGroupSetCustomList = spreadActivityGroupSetService.selectByCustomExample(spreadActivityGroupSetCustomExample);
		}
		m.addObject("spreadActivityGroupSetCustomList", spreadActivityGroupSetCustomList);

		return m;
	}

	/**
	 * 安卓活动统计（退货）列表数据
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/androidActivityCountReturn/data.shtml")
	public Map<String, Object> androidActivityCountReturnData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String orderDateBegin = request.getParameter("order_date_begin");
			String orderDateEnd = request.getParameter("order_date_end");
			String channel = request.getParameter("channel");
			String activityGroupId = request.getParameter("activityGroupId");
			String spreadName = request.getParameter("spreadName");
			String visitType = request.getParameter("visitType");
			String channelName = request.getParameter("channelName");
			if(StringUtil.isEmpty(orderDateBegin) || StringUtil.isEmpty(orderDateEnd) || StringUtil.isEmpty(channel) ){
				resMap.put("Rows", new ArrayList<HashMap<String, Object>>());
				return resMap;
			}
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("orderDateBegin",orderDateBegin+" 00:00:00");
			paramMap.put("orderDateEnd",orderDateEnd+" 23:59:59");
			paramMap.put("channel",channel);
			paramMap.put("channelName",channelName);
			paramMap.put("deviceType", Const.DEVICE_TYPE_ANDROID);
			if(!StringUtil.isEmpty(activityGroupId)) {
				if(activityGroupId.contains("A") ) {
					paramMap.put("activityGroupId",Integer.parseInt(activityGroupId.split("-")[1]));
				}else if(activityGroupId.contains("B") ) {
					paramMap.put("activityGroupSetId",Integer.parseInt(activityGroupId.split("-")[1]));
				}
			}
			if(!StringUtil.isEmpty(spreadName)) {
				paramMap.put("spreadName",spreadName);
			}
			if(!StringUtil.isEmpty(visitType) ) {
				paramMap.put("visitType",visitType);
			}
			List<HashMap<String, Object>> eachSprChnlList = combineOrderService.iosCountReturn(paramMap);
			List<HashMap<String, Object>> list = combineOrderService.totalIosCountReturn(paramMap);
			eachSprChnlList.addAll(list);
			resMap.put("Rows", eachSprChnlList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 安卓活动统计（组）列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/androidActivityGroup/list.shtml")
	public ModelAndView androidActivityGroupList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/promotionData/androidActivityGroupList");
		List<SysStatus> channelList = DataDicUtil.getStatusList("BU_SPREAD_ACTIVITY_GROUP", "CHANNEL");
		m.addObject("channelList", channelList);

		SpreadActivityPermissionExample groupSetPermissionExample = new SpreadActivityPermissionExample();
		groupSetPermissionExample.createCriteria().andDelFlagEqualTo("0")
				.andStatusEqualTo("1").andTypeEqualTo("2")
				.andStaffInfoIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<SpreadActivityPermission> groupSetPermissionList = spreadActivityPermissionService.selectByExample(groupSetPermissionExample);
		List<Integer> spreadActivityGroupSetIdList = new ArrayList<Integer>();
		for(SpreadActivityPermission spreadActivityPermission : groupSetPermissionList ) {
			spreadActivityGroupSetIdList.add(spreadActivityPermission.getSpreadId());
		}
		List<SpreadActivityGroupSetCustom> spreadActivityGroupSetCustomList = null;
		if(spreadActivityGroupSetIdList.size() > 0 ) {
			SpreadActivityGroupSetCustomExample spreadActivityGroupSetCustomExample = new SpreadActivityGroupSetCustomExample();
			SpreadActivityGroupSetCustomExample.Criteria spreadActivityGroupSetCustomCriteria = spreadActivityGroupSetCustomExample.createCriteria();
			spreadActivityGroupSetCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(spreadActivityGroupSetIdList)
					.andDeviceTypeEqualTo(Const.DEVICE_TYPE_ANDROID);
			spreadActivityGroupSetCustomExample.setOrderByClause(" ifnull(t.seq_no, 999999999), t.id desc");
			spreadActivityGroupSetCustomList = spreadActivityGroupSetService.selectByCustomExample(spreadActivityGroupSetCustomExample);
		}
		m.addObject("spreadActivityGroupSetCustomList", spreadActivityGroupSetCustomList);

		return m;
	}

	/**
	 * 安卓活动统计（组）列表数据
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/androidActivityGroup/data.shtml")
	public Map<String, Object> androidActivityGroupData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String orderDateBegin = request.getParameter("orderDateBegin");
			String orderDateEnd = request.getParameter("orderDateEnd");
			String channel = request.getParameter("channel");
			String activityGroupSetId = request.getParameter("activityGroupSetId");
			String spreadName = request.getParameter("spreadName");
			String visitType = request.getParameter("visitType");
			String channelName = request.getParameter("channelName");
			if(StringUtil.isEmpty(orderDateBegin) || StringUtil.isEmpty(orderDateEnd) || StringUtil.isEmpty(channel) || StringUtil.isEmpty(activityGroupSetId) ) {
				resMap.put("Rows", new ArrayList<HashMap<String, Object>>());
				return resMap;
			}
			Double total_consumption = 0.0;
			Double actual_consumption = 0.0;
			Integer conversion_quantity = 0;
			Double single_active_cost = 0.0;
			Integer new_guest_combine_order_count = 0;
			Integer new_guest_consume_count = 0;
			Double new_guest_cost = 0.0;
			Double new_guest_pay_amount = 0.0;
			Double new_unit_price = 0.0;
			Integer old_guest_combine_order_count = 0;
			Integer old_guest_consume_count = 0;
			Double old_guest_pay_amount = 0.0;
			Double old_unit_price = 0.0;
			Double total_pay_amount = 0.0;
			Double rate = 0.0;
			Double roi = 0.0;
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("orderDateBegin", orderDateBegin+" 00:00:00");
			paramMap.put("orderDateEnd", orderDateEnd+" 23:59:59");
			paramMap.put("channel", channel);
			paramMap.put("activityGroupSetId", activityGroupSetId);
			paramMap.put("deviceType", Const.DEVICE_TYPE_ANDROID);
			if(!StringUtil.isEmpty(spreadName) ) {
				paramMap.put("spreadName", spreadName);
			}
			if(!StringUtil.isEmpty(visitType) ) {
				paramMap.put("visitType", visitType);
			}
			List<Map<String, Object>> spreadActivityGroupList = combineOrderService.spreadActivityGroupList(paramMap);
			List<Map<String, Object>> newSpreadActivityGroupList = new ArrayList<Map<String,Object>>();
			Date odBegin = sdf.parse(orderDateBegin);
			Date odEnd = sdf.parse(orderDateEnd);
			if (spreadActivityGroupList != null && spreadActivityGroupList.size() > 0 ) {
				for(int i=0;i<spreadActivityGroupList.size();i++ ) {
					Object tmpStr = null;
					tmpStr = spreadActivityGroupList.get(i).get("total_consumption");
					if (tmpStr != null ) {
						total_consumption += Double.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("actual_consumption");
					if (tmpStr != null ) {
						actual_consumption += Double.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("conversion_quantity");
					if (tmpStr != null ) {
						conversion_quantity += Integer.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("single_active_cost");
					if (tmpStr != null ) {
						single_active_cost += Double.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("new_guest_combine_order_count");
					if (tmpStr != null ) {
						new_guest_combine_order_count += Integer.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("new_guest_consume_count");
					if (tmpStr != null ) {
						new_guest_consume_count += Integer.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("new_guest_cost");
					if (tmpStr != null ) {
						new_guest_cost += Double.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("new_guest_pay_amount");
					if (tmpStr != null ) {
						new_guest_pay_amount += Double.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("new_unit_price");
					if (tmpStr != null ) {
						new_unit_price += Double.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("old_guest_combine_order_count");
					if (tmpStr != null ) {
						old_guest_combine_order_count += Integer.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("old_guest_consume_count");
					if (tmpStr != null ) {
						old_guest_consume_count += Integer.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("old_guest_pay_amount");
					if (tmpStr != null ) {
						old_guest_pay_amount += Double.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("old_unit_price");
					if (tmpStr != null ) {
						old_unit_price += Double.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("total_pay_amount");
					if (tmpStr != null ) {
						total_pay_amount += Double.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("rate");
					if (tmpStr !=  null ) {
						rate += Double.valueOf(tmpStr.toString());
					}
					tmpStr = spreadActivityGroupList.get(i).get("roi");
					if (tmpStr != null ) {
						roi += Double.valueOf(tmpStr.toString());
					}

					Date createDate = sdf.parse(spreadActivityGroupList.get(i).get("create_date").toString());
					while (createDate.compareTo(odBegin) > 0 ) {
						List<Map<String, Object>> tmpList = new ArrayList<Map<String,Object>>();
						paramMap.put("orderDateBegin", odBegin);

						Calendar calendar = Calendar.getInstance();
						calendar.setTime(createDate);   //设置当前日期
						calendar.add(Calendar.DATE, -1); //日期减1天
						paramMap.put("orderDateEnd", sdf.format(calendar.getTime())+" 23:59:59");

						tmpList = combineOrderService.spreadActivityGroupNull(paramMap);
						for(int j=0;j<tmpList.size();j++ ) {
							Map<String,Object> map = new HashMap<String, Object>();
							map = tmpList.get(j);
							newSpreadActivityGroupList.add(map);
							Object tmpSt = null;
							tmpSt = map.get("total_consumption");
							if (tmpSt != null ) {
								total_consumption += Double.valueOf(tmpSt.toString());
							}
							tmpSt = map.get("actual_consumption");
							if (tmpSt != null ) {
								actual_consumption += Double.valueOf(tmpSt.toString());
							}
							tmpSt = map.get("conversion_quantity");
							if (tmpSt != null ) {
								conversion_quantity += Double.valueOf(tmpSt.toString()).intValue();
							}
							tmpSt = map.get("single_active_cost");
							if (tmpSt != null ) {
								single_active_cost += Double.valueOf(tmpSt.toString());
							}
						}
						odBegin = createDate;
					}
					newSpreadActivityGroupList.add(spreadActivityGroupList.get(i));
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(odBegin); //设置当前日期
					calendar.add(Calendar.DATE, 1); //日期加1天
					odBegin = calendar.getTime();
				}
				if (odEnd.compareTo(odBegin) >= 0 ) {
					List<Map<String, Object>> tmpList = new ArrayList<Map<String,Object>>();
					paramMap.put("orderDateBegin", odBegin);
					paramMap.put("orderDateEnd", orderDateEnd+" 23:59:59");
					tmpList = combineOrderService.spreadActivityGroupNull(paramMap);
					for(int i=0;i<tmpList.size();i++ ) {
						Map<String,Object> map = new HashMap<String, Object>();
						map = tmpList.get(i);
						newSpreadActivityGroupList.add(map);
						Object tmpSt = null;
						tmpSt = map.get("total_consumption");
						if (tmpSt != null ) {
							total_consumption += Double.valueOf(tmpSt.toString());
						}
						tmpSt = map.get("actual_consumption");
						if (tmpSt != null ) {
							actual_consumption += Double.valueOf(tmpSt.toString());
						}
						tmpSt = map.get("conversion_quantity");
						if (tmpSt != null ) {
							conversion_quantity += Double.valueOf(tmpSt.toString()).intValue();
						}
						tmpSt = map.get("single_active_cost");
						if (tmpSt != null ) {
							single_active_cost += Double.valueOf(tmpSt.toString());
						}
					}
				}
			}
			if(newSpreadActivityGroupList != null && newSpreadActivityGroupList.size() > 0) {
				List<Map<String, Object>> listMap = new ArrayList<>();
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("create_date", "合计");
				map.put("activity_group", channelName);
				map.put("total_consumption", (float)Math.round(total_consumption*100)/100);
				map.put("actual_consumption", (float)Math.round(actual_consumption*100)/100);
				map.put("conversion_quantity", conversion_quantity);
				map.put("single_active_cost", conversion_quantity==0?null:(float)Math.round(actual_consumption/conversion_quantity*100)/100);
				map.put("new_guest_combine_order_count", new_guest_combine_order_count);
				map.put("new_guest_consume_count", new_guest_consume_count);
				map.put("new_guest_cost", new_guest_combine_order_count==0?null:(float)Math.round(actual_consumption/new_guest_combine_order_count*100)/100);
				map.put("new_guest_pay_amount", (float)Math.round(new_guest_pay_amount*100)/100);
				map.put("new_unit_price", (float)Math.round(new_guest_pay_amount/new_guest_combine_order_count*100)/100);
				map.put("old_guest_combine_order_count", old_guest_combine_order_count);
				map.put("old_guest_consume_count", old_guest_consume_count);
				map.put("old_guest_pay_amount", (float)Math.round(old_guest_pay_amount*100)/100);
				map.put("old_unit_price", old_guest_consume_count==0?null:(float)Math.round(old_guest_pay_amount/old_guest_consume_count*100)/100);
				map.put("total_pay_amount", (float)Math.round(total_pay_amount*100)/100);
				map.put("rate", old_guest_consume_count==0?null:(float)Math.round((float)new_guest_consume_count/old_guest_consume_count*100)/100);
				map.put("roi", actual_consumption==0.0?null:(float)Math.round(total_pay_amount/actual_consumption*100)/100);
				listMap.add(map);
				newSpreadActivityGroupList.addAll(listMap);
			}
			resMap.put("Rows", newSpreadActivityGroupList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}





}
