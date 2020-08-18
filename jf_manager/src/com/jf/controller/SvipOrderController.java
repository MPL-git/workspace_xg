package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.exception.ArgException;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtils;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoCustom;
import com.jf.entity.MemberInfoCustomExample;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.SvipOrder;
import com.jf.entity.SvipOrderCustom;
import com.jf.entity.SvipOrderCustomExample;
import com.jf.entity.SvipProductRecommend;
import com.jf.entity.SvipProductRecommendCustom;
import com.jf.entity.SvipProductRecommendCustomExample;
import com.jf.entity.SvipProductRecommendCustomExample.SvipProductRecommendCustomCriteria;
import com.jf.entity.SvipProductRecommendExample;
import com.jf.entity.SvipRecommendNavigation;
import com.jf.entity.SvipRecommendNavigationExample;
import com.jf.service.MemberInfoService;
import com.jf.service.ProductTypeService;
import com.jf.service.SvipOrderService;
import com.jf.service.SvipProductRecommendService;
import com.jf.service.SvipRecommendNavigationService;
import com.jf.vo.Page;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
@Controller
public class SvipOrderController extends BaseController {

	@Autowired
	private SvipOrderService svipOrderService;
	
	@Autowired
	private MemberInfoService memberInfoService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	@Autowired
	private SvipProductRecommendService svipProductRecommendService;

	@Autowired
	private SvipRecommendNavigationService svipRecommendNavigationService;
	
	/**
	 * 
	 * @Title svipMemberManager   
	 * @Description TODO(SVIP会员列表管理)   
	 * @author pengl
	 * @date 2019年3月5日 下午2:14:37
	 */
	@RequestMapping("/svipOrder/svipMemberManager.shtml")
	public ModelAndView svipMemberManager(HttpServletRequest request) {	
		ModelAndView m = new ModelAndView("/member/svip/getSvipMemberList");
		return m;
	}
	
	/**
	 * 
	 * @Title getSvipMemberList   
	 * @Description TODO(SVIP会员列表数据)   
	 * @author pengl
	 * @date 2019年3月5日 下午2:15:09
	 */
	@ResponseBody
	@RequestMapping("/svipOrder/getSvipMemberList.shtml")
	public Map<String, Object> getSvipMemberList(HttpServletRequest request, Page page) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		List<MemberInfoCustom> dataList = null;
		Integer totalCount = 0;
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			MemberInfoCustomExample memberInfoCustomExample = new MemberInfoCustomExample();
			MemberInfoCustomExample.MemberInfoCustomCriteria memberInfoCustomCriteria = memberInfoCustomExample.createCriteria();
			memberInfoCustomCriteria.andDelFlagEqualTo("0").andIsSvipEqualTo("1");
			if(!StringUtil.isEmpty(request.getParameter("id")) ) {
				memberInfoCustomCriteria.andIdEqualTo(Integer.parseInt(request.getParameter("id")));
			}
			if(!StringUtil.isEmpty(request.getParameter("payDateBegin")) && !StringUtil.isEmpty(request.getParameter("payDateEnd")) ) {
				memberInfoCustomCriteria.andPayDateBetween(request.getParameter("payDateBegin")+" 00:00:00", request.getParameter("payDateEnd")+" 23:59:59");
			}else if(!StringUtil.isEmpty(request.getParameter("payDateBegin")) ) {
				memberInfoCustomCriteria.andPayDateGreaterThanOrEqualTo(request.getParameter("payDateBegin")+" 00:00:00");
			}else if(!StringUtil.isEmpty(request.getParameter("payDateEnd")) ) {
				memberInfoCustomCriteria.andPayDateLessThanOrEqualTo(request.getParameter("payDateEnd")+" 23:59:59");
			}
			if(!StringUtil.isEmpty(request.getParameter("svipExpireDateBegin")) ) {
				memberInfoCustomCriteria.andSvipExpireDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("svipExpireDateBegin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("svipExpireDateEnd")) ) {
				memberInfoCustomCriteria.andSvipExpireDateLessThanOrEqualTo(sdf.parse(request.getParameter("svipExpireDateEnd")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("svipStatus")) ) {
				if("1".equals(request.getParameter("svipStatus")) ) { // 会员已激活
					memberInfoCustomCriteria.andSvipExpireDateGreaterThan(date);
				}else { // 会员已过期
					memberInfoCustomCriteria.andSvipExpireDateLessThan(date);
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("date_begin")) ) {
				memberInfoCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("date_end")) ) {
				memberInfoCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("date_end")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("loginDateBegin")) || !StringUtil.isEmpty(request.getParameter("loginDateEnd")) ) {
				memberInfoCustomCriteria.andLastLoginTimeBetween(request.getParameter("loginDateBegin"), request.getParameter("loginDateEnd"));
			}
			if(!StringUtil.isEmpty(request.getParameter("firstPayDateBegin")) || !StringUtil.isEmpty(request.getParameter("firstPayDateEnd")) ) {
				memberInfoCustomCriteria.andFirstBuyTimeBetween(request.getParameter("firstPayDateBegin"), request.getParameter("firstPayDateEnd"));
			}
			if(!StringUtil.isEmpty(request.getParameter("lastPayDateBegin")) || !StringUtil.isEmpty(request.getParameter("lastPayDateEnd")) ) {
				memberInfoCustomCriteria.andLastBuyTimeBetween(request.getParameter("lastPayDateBegin"), request.getParameter("lastPayDateEnd"));
			}
			if(!StringUtil.isEmpty(request.getParameter("payOrderCountMin"))) {
				memberInfoCustomCriteria.andtotalBuyCountGreaterThanOrEqualTo(Integer.parseInt(request.getParameter("payOrderCountMin")));
			}
			if(!StringUtil.isEmpty(request.getParameter("payOrderCountMax"))) {
				memberInfoCustomCriteria.andtotalBuyCountLessThanOrEqualTo(Integer.parseInt(request.getParameter("payOrderCountMax")));
			}
			if(!StringUtil.isEmpty(request.getParameter("payOrderAmountMin"))) {
				memberInfoCustomCriteria.andtotalBuyAmountGreaterThanOrEqualTo(new BigDecimal(request.getParameter("payOrderAmountMin")));
			}
			if(!StringUtil.isEmpty(request.getParameter("payOrderAmountMax"))) {
				memberInfoCustomCriteria.andtotalBuyAmountLessThanOrEqualTo(new BigDecimal(request.getParameter("payOrderAmountMax")));
			}
			memberInfoCustomExample.setOrderByClause(" (select so.pay_date from bu_svip_order so where so.del_flag = '0' and so.buy_type = '1' and so.status = '1' and so.member_id = a.id order by so.id asc limit 1) desc");
			memberInfoCustomExample.setLimitStart(page.getLimitStart());
			memberInfoCustomExample.setLimitSize(page.getLimitSize());
			dataList = memberInfoService.selectMemberInfoCustomByExample(memberInfoCustomExample);
			totalCount = memberInfoService.countMemberInfoCustomByExample(memberInfoCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title svipOrderManager   
	 * @Description TODO(会员购买列表管理)   
	 * @author pengl
	 * @date 2019年3月5日 下午3:27:48
	 */
	@RequestMapping("/svipOrder/svipOrderManager.shtml")
	public ModelAndView svipOrderManager(HttpServletRequest request) {	
		ModelAndView m = new ModelAndView("/member/svip/getSvipOrderList");
		m.addObject("buyTypeList", DataDicUtil.getStatusList("BU_SVIP_ORDER", "BUY_TYPE"));
		m.addObject("statusTypeList", DataDicUtil.getStatusList("BU_SVIP_ORDER", "STATUS"));
		return m;
	}
	
	/**
	 * 
	 * @Title getSvipOrderList   
	 * @Description TODO(会员购买列表)   
	 * @author pengl
	 * @date 2019年3月5日 下午4:01:17
	 */
	@ResponseBody
	@RequestMapping("/svipOrder/getSvipOrderList.shtml")
	public Map<String, Object> getSvipOrderList(HttpServletRequest request, Page page) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		List<SvipOrderCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SvipOrderCustomExample svipOrderCustomExample = new SvipOrderCustomExample();
			SvipOrderCustomExample.SvipOrderCustomCriteria svipOrderCustomCriteria = svipOrderCustomExample.createCriteria();
			svipOrderCustomCriteria.andDelFlagEqualTo("0");
			svipOrderCustomExample.setOrderByClause(" t.pay_date desc,create_date desc");
			if(!StringUtil.isEmpty(request.getParameter("memberId")) ) {
				svipOrderCustomCriteria.andMemberIdEqualTo(Integer.parseInt(request.getParameter("memberId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("buyType")) ) {
				svipOrderCustomCriteria.andBuyTypeEqualTo(request.getParameter("buyType"));
			}
			if(!StringUtil.isEmpty(request.getParameter("statusType")) ) {
				svipOrderCustomCriteria.andStatusEqualTo(request.getParameter("statusType"));
			}
			if(!StringUtil.isEmpty(request.getParameter("payDateBegin")) ) {
				svipOrderCustomCriteria.andPayDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("payDateBegin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("payDateEnd")) ) {
				svipOrderCustomCriteria.andPayDateLessThanOrEqualTo(sdf.parse(request.getParameter("payDateEnd")+" 23:59:59"));
			}
			svipOrderCustomExample.setLimitStart(page.getLimitStart());
			svipOrderCustomExample.setLimitSize(page.getLimitSize());
			dataList = svipOrderService.selectByCustomExample(svipOrderCustomExample);
			totalCount = svipOrderService.countByCustomExample(svipOrderCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title financeSvipOrderManager   
	 * @Description TODO(年费收款明细)   
	 * @author pengl
	 * @date 2019年3月11日 上午9:22:53
	 */
	@RequestMapping("/svipOrder/financeSvipOrderManager.shtml")
	public ModelAndView financeSvipOrderManager(HttpServletRequest request) {	
		ModelAndView m = new ModelAndView("/member/svip/financeSvipOrderList");
		m.addObject("svipOrderStatusList", DataDicUtil.getStatusList("BU_SVIP_ORDER", "STATUS"));
		if(!StringUtils.isEmpty(request.getParameter("payDate"))){
			m.addObject("nowDate", request.getParameter("payDate"));
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			m.addObject("nowDate", sdf.format(new Date()));
		}
		return m;
	}
	
	/**
	 * 
	 * @Title financeSvipOrderList   
	 * @Description TODO(年费收款明细)   
	 * @author pengl
	 * @date 2019年3月11日 上午9:23:22
	 */
	@ResponseBody
	@RequestMapping("/svipOrder/financeSvipOrderList.shtml")
	public Map<String, Object> financeSvipOrderList(HttpServletRequest request, Page page) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		List<SvipOrderCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SvipOrderCustomExample svipOrderCustomExample = new SvipOrderCustomExample();
			SvipOrderCustomExample.SvipOrderCustomCriteria svipOrderCustomCriteria = svipOrderCustomExample.createCriteria();
			svipOrderCustomCriteria.andDelFlagEqualTo("0").andStatusIn(Arrays.asList("1","2"));
			if(!StringUtil.isEmpty(request.getParameter("memberId")) ) {
				svipOrderCustomCriteria.andMemberIdEqualTo(Integer.parseInt(request.getParameter("memberId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("orderCode")) ) {
				svipOrderCustomCriteria.andOrderCodeLike("%"+request.getParameter("orderCode")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("paymentId")) ) {
				String paymentId=request.getParameter("paymentId");
				if (paymentId.equals("1")){//微信APP/H5
					Integer[] wxStr={2,5};
					List<Integer> wxList = Arrays.asList(wxStr);
					svipOrderCustomCriteria.andPaymentIdIn(wxList);
				}
				if (paymentId.equals("2")){//支付宝APP/H5
					Integer[] zfbStr={1,6};
					List<Integer> zfbList = Arrays.asList(zfbStr);
					svipOrderCustomCriteria.andPaymentIdIn(zfbList);
				}
				if (paymentId.equals("3")){//微信公众号
					Integer[] wxStr2={4,7};
					List<Integer> wxList2 = Arrays.asList(wxStr2);
					svipOrderCustomCriteria.andPaymentIdIn(wxList2);
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("payDateBegin")) ) {
				svipOrderCustomCriteria.andPayDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("payDateBegin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("payDateEnd")) ) {
				svipOrderCustomCriteria.andPayDateLessThanOrEqualTo(sdf.parse(request.getParameter("payDateEnd")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("paymentNo")) ) {
				svipOrderCustomCriteria.andPaymentNoLike("%"+request.getParameter("paymentNo")+"%");
			}
			svipOrderCustomExample.setOrderByClause(" t.pay_date desc");
			svipOrderCustomExample.setLimitStart(page.getLimitStart());
			svipOrderCustomExample.setLimitSize(page.getLimitSize());
			dataList = svipOrderService.selectByCustomExample(svipOrderCustomExample);
			totalCount = svipOrderService.countByCustomExample(svipOrderCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title exportFinanceSvipOrderList   
	 * @Description TODO(年费收款明细导出)   
	 * @author pengl
	 * @date 2019年3月11日 上午9:24:35
	 */
	@RequestMapping("/svipOrder/exportFinanceSvipOrderList.shtml")
	public void exportFinanceSvipOrderList(HttpServletRequest request, HttpServletResponse response) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SvipOrderCustomExample svipOrderCustomExample = new SvipOrderCustomExample();
			SvipOrderCustomExample.SvipOrderCustomCriteria svipOrderCustomCriteria = svipOrderCustomExample.createCriteria();
			svipOrderCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
			if(!StringUtil.isEmpty(request.getParameter("memberId")) ) {
				svipOrderCustomCriteria.andMemberIdEqualTo(Integer.parseInt(request.getParameter("memberId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("orderCode")) ) {
				svipOrderCustomCriteria.andOrderCodeLike("%"+request.getParameter("orderCode")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("paymentId")) ) {
				String paymentId=request.getParameter("paymentId");
				if (paymentId.equals("1")){//微信APP/H5
					Integer[] wxStr={2,5};
					List<Integer> wxList = Arrays.asList(wxStr);
					svipOrderCustomCriteria.andPaymentIdIn(wxList);
				}
				if (paymentId.equals("2")){//支付宝APP/H5
					Integer[] zfbStr={1,6};
					List<Integer> zfbList = Arrays.asList(zfbStr);
					svipOrderCustomCriteria.andPaymentIdIn(zfbList);
				}
				if (paymentId.equals("3")){//微信公众号
					Integer[] wxStr2={4,7};
					List<Integer> wxList2 = Arrays.asList(wxStr2);
					svipOrderCustomCriteria.andPaymentIdIn(wxList2);
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("payDateBegin")) ) {
				svipOrderCustomCriteria.andPayDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("payDateBegin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("payDateEnd")) ) {
				svipOrderCustomCriteria.andPayDateLessThanOrEqualTo(sdf.parse(request.getParameter("payDateEnd")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("paymentNo")) ) {
				svipOrderCustomCriteria.andPaymentNoLike("%"+request.getParameter("paymentNo")+"%");
			}
			svipOrderCustomExample.setOrderByClause(" t.pay_date desc");
			List<SvipOrderCustom> svipOrderCustomList = svipOrderService.selectByCustomExample(svipOrderCustomExample);
			String[] titles = {"订单编号","会员ID","昵称","购买年限","付款渠道","实付金额","订单状态","支付时间","支付交易号","交易完成时间"};
			ExcelBean excelBean = new ExcelBean("SVIP收款明细.xls", "SVIP收款明细", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(SvipOrderCustom svipOrderCustom : svipOrderCustomList) {
				String[] data = {
						svipOrderCustom.getOrderCode(),
						svipOrderCustom.getMemberId()==null?"":svipOrderCustom.getMemberId().toString(),
						svipOrderCustom.getMemberNick(),
						svipOrderCustom.getYearsOfPurchase().toString(),
						svipOrderCustom.getPaymentName(),
						svipOrderCustom.getPayAmount()==null?"":svipOrderCustom.getPayAmount().toString(),
						svipOrderCustom.getSvipOrderDesc(),
						svipOrderCustom.getPayDate()==null?"":sdf.format(svipOrderCustom.getPayDate()),
						"`"+svipOrderCustom.getPaymentNo(),
						svipOrderCustom.getDealCompleteDate()==null?"":sdf.format(svipOrderCustom.getDealCompleteDate())
					};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Title countSvipOrderManager   
	 * @Description TODO(每日年费收款汇总)   
	 * @author pengl
	 * @date 2019年3月11日 上午9:24:59
	 */
	@RequestMapping("/svipOrder/countSvipOrderManager.shtml")
	public ModelAndView countSvipOrderManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/member/svip/countSvipOrderList");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String pay_date_end = sdf.format(new Date());
		String pay_date_begin = pay_date_end.substring(0,7)+"-01";
		m.addObject("pay_date_end", pay_date_end);
		m.addObject("pay_date_begin", pay_date_begin);
		return m;
	}
	
	/**
	 * 
	 * @Title countSvipOrderList   
	 * @Description TODO(每日年费收款汇总)   
	 * @author pengl
	 * @date 2019年3月11日 上午9:25:19
	 */
	@ResponseBody
	@RequestMapping("/svipOrder/countSvipOrderList.shtml")
	public Map<String, Object> countSvipOrderList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = null;
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String payDateBegin = request.getParameter("pay_date_begin");
			String payDateEnd = request.getParameter("pay_date_end");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(payDateBegin) ){
				paramMap.put("payDateBegin", payDateBegin+" 00:00:00");
			}else{
				paramMap.put("payDateBegin", sdf.format(new Date()).substring(0,7)+"-01 00:00:00");
			}
			if(!StringUtil.isEmpty(payDateEnd) ){
				paramMap.put("payDateEnd", payDateEnd+" 23:59:59");
			}else{
				paramMap.put("payDateEnd", sdf.format(new Date()));
			}
			dataList = svipOrderService.countSvipOrderList(paramMap);
			resMap.put("Rows", dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title exportCountSvipOrderList   
	 * @Description TODO(每日年费收款汇总导出)   
	 * @author pengl
	 * @date 2019年3月11日 上午9:25:36
	 */
	@RequestMapping("/svipOrder/exportCountSvipOrderList.shtml")
	public void exportCountSvipOrderList(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String payDateBegin = request.getParameter("pay_date_begin");
			String payDateEnd = request.getParameter("pay_date_end");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(payDateBegin) ){
				paramMap.put("payDateBegin", payDateBegin+" 00:00:00");
			}else{
				paramMap.put("payDateBegin", sdf.format(new Date()).substring(0,7)+"-01 00:00:00");
			}
			if(!StringUtil.isEmpty(payDateEnd) ){
				paramMap.put("payDateEnd", payDateEnd+" 23:59:59");
			}else{
				paramMap.put("payDateEnd", sdf.format(new Date()));
			}
			List<Map<String, Object>> dataList = svipOrderService.countSvipOrderList(paramMap);
			String[] titles = {"付款日期","笔数","母订单金额","微信APP/H5金额","支付宝金额","微信公众号/小程序金额","微信APP/H5笔数","支付宝笔数","微信公众号/小程序笔数"};
			ExcelBean excelBean = new ExcelBean("每日SVIP收款汇总.xls", "每日SVIP收款汇总", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(Map<String, Object> dataMap : dataList) {
				String[] data = {
						dataMap.get("eachDay").toString(),
						dataMap.get("totalCount").toString(),
						dataMap.get("combineAmount").toString(),
						dataMap.get("wxAmount").toString(),
						dataMap.get("zfbAmount").toString(),
						dataMap.get("gzhAmount").toString(),
						dataMap.get("wxCount").toString(),
						dataMap.get("zfbCount").toString(),
						dataMap.get("gzhCount").toString(),
					};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//导出SVIP列表
	@RequestMapping("/svipOrder/getSvipMemberList/exportExcel.shtml")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			MemberInfoCustomExample memberInfoCustomExample = new MemberInfoCustomExample();
			MemberInfoCustomExample.MemberInfoCustomCriteria memberInfoCustomCriteria = memberInfoCustomExample.createCriteria();
			memberInfoCustomCriteria.andDelFlagEqualTo("0").andIsSvipEqualTo("1");
			if(!StringUtil.isEmpty(request.getParameter("id")) ) {
				memberInfoCustomCriteria.andIdEqualTo(Integer.parseInt(request.getParameter("id")));
			}
			if(!StringUtil.isEmpty(request.getParameter("payDateBegin")) && !StringUtil.isEmpty(request.getParameter("payDateEnd")) ) {
				memberInfoCustomCriteria.andPayDateBetween(request.getParameter("payDateBegin")+" 00:00:00", request.getParameter("payDateEnd")+" 23:59:59");
			}else if(!StringUtil.isEmpty(request.getParameter("payDateBegin")) ) {
				memberInfoCustomCriteria.andPayDateGreaterThanOrEqualTo(request.getParameter("payDateBegin")+" 00:00:00");
			}else if(!StringUtil.isEmpty(request.getParameter("payDateEnd")) ) {
				memberInfoCustomCriteria.andPayDateLessThanOrEqualTo(request.getParameter("payDateEnd")+" 23:59:59");
			}
			if(!StringUtil.isEmpty(request.getParameter("svipExpireDateBegin")) ) {
				memberInfoCustomCriteria.andSvipExpireDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("svipExpireDateBegin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("svipExpireDateEnd")) ) {
				memberInfoCustomCriteria.andSvipExpireDateLessThanOrEqualTo(sdf.parse(request.getParameter("svipExpireDateEnd")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("svipStatus")) ) {
				if("1".equals(request.getParameter("svipStatus")) ) { // 会员已激活
					memberInfoCustomCriteria.andSvipExpireDateGreaterThan(date);
				}else { // 会员已过期
					memberInfoCustomCriteria.andSvipExpireDateLessThan(date);
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("date_begin")) ) {
				memberInfoCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("date_end")) ) {
				memberInfoCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("date_end")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("loginDateBegin")) || !StringUtil.isEmpty(request.getParameter("loginDateEnd")) ) {
				memberInfoCustomCriteria.andLastLoginTimeBetween(request.getParameter("loginDateBegin"), request.getParameter("loginDateEnd"));
			}
			if(!StringUtil.isEmpty(request.getParameter("firstPayDateBegin")) || !StringUtil.isEmpty(request.getParameter("firstPayDateEnd")) ) {
				memberInfoCustomCriteria.andFirstBuyTimeBetween(request.getParameter("firstPayDateBegin"), request.getParameter("firstPayDateEnd"));
			}
			if(!StringUtil.isEmpty(request.getParameter("lastPayDateBegin")) || !StringUtil.isEmpty(request.getParameter("lastPayDateEnd")) ) {
				memberInfoCustomCriteria.andLastBuyTimeBetween(request.getParameter("lastPayDateBegin"), request.getParameter("lastPayDateEnd"));
			}
			if(!StringUtil.isEmpty(request.getParameter("payOrderCountMin"))) {
				memberInfoCustomCriteria.andtotalBuyCountGreaterThanOrEqualTo(Integer.parseInt(request.getParameter("payOrderCountMin")));
			}
			if(!StringUtil.isEmpty(request.getParameter("payOrderCountMax"))) {
				memberInfoCustomCriteria.andtotalBuyCountLessThanOrEqualTo(Integer.parseInt(request.getParameter("payOrderCountMax")));
			}
			if(!StringUtil.isEmpty(request.getParameter("payOrderAmountMin"))) {
				memberInfoCustomCriteria.andtotalBuyAmountGreaterThanOrEqualTo(new BigDecimal(request.getParameter("payOrderAmountMin")));
			}
			if(!StringUtil.isEmpty(request.getParameter("payOrderAmountMax"))) {
				memberInfoCustomCriteria.andtotalBuyAmountLessThanOrEqualTo(new BigDecimal(request.getParameter("payOrderAmountMax")));
			}
			memberInfoCustomExample.setOrderByClause(" (select so.pay_date from bu_svip_order so where so.del_flag = '0' and so.buy_type = '1' and so.status = '1' and so.member_id = a.id order by so.id asc limit 1) desc");
			
			List<MemberInfoCustom> memberInfoCustoms = memberInfoService.selectMemberInfoCustomByExample(memberInfoCustomExample);
			String[] titles = {"会员ID","昵称","会员状态","SVIP状态","注册时间","首次购买SVIP时间","SVIP到期时间","最后登录时间","首次消费时间","最后消费时间","消费订单数","消费金额","积分"};
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ExcelBean excelBean = new ExcelBean("导出SVIP列表.xls", "导出SVIP列表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for (MemberInfoCustom memberInfoCustom : memberInfoCustoms) {
				  String state="";
				 if (memberInfoCustom.getSvipExpireDate()!=null && memberInfoCustom.getSvipExpireDate().getTime()>date.getTime()) {
					 state="已激活";
				 }else {
					 state="已过期";
				} 
				
				String[] data = {
						memberInfoCustom.getId()==null?"":memberInfoCustom.getId().toString(),
						memberInfoCustom.getNick()==null?"":memberInfoCustom.getNick(),
					    memberInfoCustom.getStatusDesc()==null?"":memberInfoCustom.getStatusDesc(),
					    state,
					    memberInfoCustom.getCreateDate()==null?"":df.format(memberInfoCustom.getCreateDate()),
					    memberInfoCustom.getBuySvipPayDate()==null?"":df.format(memberInfoCustom.getBuySvipPayDate()),
					    memberInfoCustom.getSvipExpireDate()==null?"":df.format(memberInfoCustom.getSvipExpireDate()),
					    memberInfoCustom.getMsLastLoginTime()==null?"":df.format(memberInfoCustom.getMsLastLoginTime()),
					    memberInfoCustom.getMsFirstBuyTime()==null?"":df.format(memberInfoCustom.getMsFirstBuyTime()),
				        memberInfoCustom.getMsLastBuyTime()==null?"":df.format(memberInfoCustom.getMsLastBuyTime()), 
				        memberInfoCustom.getTotalBuyCount()==null?"":memberInfoCustom.getTotalBuyCount().toString(),
				        memberInfoCustom.getTotalBuyAmount()==null?"":memberInfoCustom.getTotalBuyAmount().toString(),
				        memberInfoCustom.getIntegral()==null?"":memberInfoCustom.getIntegral().toString()
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @Title svipProductRecommend   
	 * @Description TODO(SVIP商品推荐管理列表)   
	 * @author XDD
	 * @date 2019年8月14日 11:18
	 */
	@RequestMapping("/svipOrder/svipProductRecommend.shtml")
	public ModelAndView svipProductRecommend(HttpServletRequest request) {	
		ModelAndView m = new ModelAndView("/member/svip/svipProductRecommendList");
		//一级分类
		ProductTypeExample example = new ProductTypeExample();
		example.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(example);
		m.addObject("productTypes", productTypes);
		return m;
	}
	
	/**
	 * SVIP退款
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/svipOrder/SvipOrderUpdateStatus.shtml")
	@ResponseBody
	public Map<String, Object> SvipOrderUpdateStatus(Model model,HttpServletRequest request){
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			if(StringUtil.isEmpty(request.getParameter("id"))){
				throw new ArgException("订单不存在");
			}
			if(StringUtil.isEmpty(request.getParameter("status")) || StringUtil.isEmpty(request.getParameter("memberId"))){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "操作失败");
				return resMap;
			}
			Integer memberId = Integer.valueOf(request.getParameter("memberId"));
			MemberInfoCustom memberInfoCustom = memberInfoService.selectMemberInfoCustomByPrimaryKey(memberId);
			Date now = new Date();
			if (memberInfoCustom.getIsSvip()==null || memberInfoCustom.getSvipExpireDate()==null || "0".equals(memberInfoCustom.getIsSvip()) || memberInfoCustom.getSvipExpireDate().before(now)) {
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "会员不合法");
				return resMap;
			}
			Integer staffID = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer id = Integer.valueOf(request.getParameter("id"));
			SvipOrder svipOrder2 = svipOrderService.selectByCustomPrimaryKey(id);
			Integer yearsOfPurchase = svipOrder2.getYearsOfPurchase();
			Calendar calendar = Calendar.getInstance();  
			calendar.setTime(memberInfoCustom.getSvipExpireDate());
			calendar.add(Calendar.YEAR, yearsOfPurchase*(-1));
			Date newDate = calendar.getTime();
			//修改会员的SVIP状态
			MemberInfo memberInfo = new MemberInfo();
			memberInfo.setId(memberId);
			if(newDate.before(now)){
				memberInfo.setIsSvip("0");
				memberInfo.setSvipExpireDate(newDate);
			}else {
				memberInfo.setSvipExpireDate(newDate);
			}
			memberInfo.setUpdateBy(staffID);
			memberInfo.setUpdateDate(now);
			
			//修改SVIP订单为已退款
			SvipOrder svipOrder = new SvipOrder();
			svipOrder.setId(id);
			svipOrder.setStatus(request.getParameter("status"));
			svipOrder.setUpdateBy(staffID);
			svipOrder.setUpdateDate(now);
			svipOrder.setRefundDate(now);
			svipOrder.setRefundBy(staffID);
			svipOrderService.SvipOrderUpdateStatus(svipOrder,memberInfo);
		} catch (ArgException e){
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title getSvipProductRecommendList  
	 * @Description TODO(SVIP商品推荐管理列表)   
	 * @author XDD
	 * @date 2019年8月14日 14:28
	 */
	@ResponseBody
	@RequestMapping("/svipOrder/getSvipProductRecommendList.shtml")
	public Map<String, Object> getSvipProductRecommendList(HttpServletRequest request, Page page) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		List<SvipProductRecommendCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SvipProductRecommendCustomExample example = new SvipProductRecommendCustomExample();
			SvipProductRecommendCustomCriteria criteria = example.createCriteria();
			criteria.andAllDelflagNotNull();
			String name = request.getParameter("name");
			if(!StringUtils.isEmpty(name)){
				criteria.andNameLike("%"+name+"%");
			}
			String code = request.getParameter("code");
			if(!StringUtils.isEmpty(code)){
				criteria.andCodeEqualto(code);
			}
			String productType1 = request.getParameter("productType1");
			if(!StringUtils.isEmpty(productType1)){
				criteria.andProductType1IdEqualto(Integer.parseInt(productType1));
			}
			String source = request.getParameter("source");
			if(!StringUtils.isEmpty(source)){
				criteria.andSvipProductRecommendSourceEqualTo(source);
			}
			String mchtCode = request.getParameter("mchtCode");
			if(!StringUtils.isEmpty(mchtCode)){
				criteria.andMchtCodeEqualto(mchtCode);
			}
			String order_by_seq_no = request.getParameter("order_by_seq_no");
			if(!StringUtils.isEmpty(order_by_seq_no)){
				example.setOrderByClause(" IFNULL(t.seq_no,99999),t.id desc");
			}else{
				example.setOrderByClause(" t.id desc");
			}
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			dataList = svipProductRecommendService.selectSvipProductRecommendCustomByExample(example);
			totalCount = svipProductRecommendService.countSvipProductRecommendCustomByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 删除SVIP推荐商品
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/svipOrder/delSvipProductRecommend.shtml")
	@ResponseBody
	public Map<String, Object> delSvipProductRecommend(Model model,HttpServletRequest request){
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			if(StringUtil.isEmpty(request.getParameter("id"))){
				throw new ArgException("商品不存在");
			}
			Integer staffID = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer id = Integer.valueOf(request.getParameter("id"));
			SvipProductRecommend svipProductRecommend = new SvipProductRecommend();
			svipProductRecommend.setId(id);
			svipProductRecommend.setUpdateBy(staffID);
			svipProductRecommend.setUpdateDate(new Date());
			svipProductRecommend.setDelFlag("1");
			svipProductRecommendService.updateByPrimaryKeySelective(svipProductRecommend);
		} catch (ArgException e){
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	//更改排序
	@ResponseBody
	@RequestMapping("/svipOrder/updateSvipProductRecommendSeqNo.shtml")
	public Map<String, Object> updateSvipProductRecommendSeqNo(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String id = request.getParameter("id");
			String seqNo = request.getParameter("seqNo");
			if(!StringUtil.isEmpty(id)) {
				if(!StringUtil.isEmpty(seqNo)) {
					SvipProductRecommend svipProductRecommend = new SvipProductRecommend();
					svipProductRecommend.setId(Integer.parseInt(id));
					svipProductRecommend.setSeqNo(Integer.parseInt(seqNo));
					svipProductRecommend.setUpdateBy(Integer.parseInt(staffID));
					svipProductRecommend.setUpdateDate(date);
					svipProductRecommendService.updateByPrimaryKeySelective(svipProductRecommend);
				}else{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					svipProductRecommendService.updateSeqNoIsNull(Integer.parseInt(id),Integer.parseInt(staffID),sdf.format(date));
				}
			}else {
				code = "9999";
				msg = "ID为空！";
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
	
	//手动新增页面
	@RequestMapping("/svipOrder/manuallyAdd.shtml")
	public ModelAndView manuallyAdd(HttpServletRequest request) {	
		ModelAndView m = new ModelAndView("/member/svip/manuallyAdd");
		return m;
	}
	
	//保存手动新增
	@RequestMapping(value = "/svipOrder/saveManuallyAdd.shtml")
	@ResponseBody
	public Map<String, Object> saveManuallyAdd(HttpServletRequest request, Page page) {
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try{
			String productCodes = request.getParameter("productCodes");
			HashSet<String> productCodesSet = new HashSet<>();
			if(!StringUtils.isEmpty(productCodes)){
				Collections.addAll(productCodesSet,productCodes.split("\n"));
			}
			ArrayList<Integer> productIds = new ArrayList<>();
			ArrayList<HashMap<String, Object>> productByCodeLists = svipProductRecommendService.selectProductByCodeList(productCodesSet);
			for (HashMap<String, Object> map : productByCodeLists) {
				if(map.get("ProductAuditStatus").equals("2")&&map.get("ProductStatus").equals("1")
						&&map.get("ProductSaleType").equals("1")&&map.get("MchtStatus").equals("1")
						&&map.get("MchtShopStatus").equals("1")&&map.get("MchtProductBrandStatus").equals("1")
						&&map.get("MchtProductBrandAuditStatus").equals("3")){
					productIds.add((Integer)map.get("ProductId"));
				}
			}
			//不存在符合条件的商品不执行
			if(productIds.size() == 0){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "不存在符合条件的商品");
				return resMap;
			}
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			svipProductRecommendService.saveManuallyAdd(productIds,staffID);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}
	
	//自动抓取页面
	@RequestMapping("/svipOrder/automaticGrab.shtml")
	public ModelAndView automaticGrab(HttpServletRequest request) {	
		ModelAndView m = new ModelAndView("/member/svip/automaticGrab");
		return m;
	}
	
	//自动抓取列表
	@ResponseBody
	@RequestMapping("/svipOrder/getAutomaticGrabList.shtml")
	public Map<String, Object> getAutomaticGrabList(HttpServletRequest request, Page page) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		List<SvipProductRecommendCustom> dataList = null;
		Integer totalCount = 0;
		try {
			Integer limitStart = page.getLimitStart();
			Integer limitEnd = page.getPage() * page.getPagesize();
			ArrayList<Integer> automaticGrabProductIds = svipProductRecommendService.getAutomaticGrabProductIds();
			totalCount = automaticGrabProductIds.size();
			if (limitEnd <= totalCount) {
				dataList = svipProductRecommendService.selectAutomaticGrabList(automaticGrabProductIds.subList(limitStart, limitEnd));
			}else if(limitEnd < totalCount + page.getPagesize()){
				dataList = svipProductRecommendService.selectAutomaticGrabList(automaticGrabProductIds.subList(limitStart, totalCount));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	//保存自动抓取
	@RequestMapping(value = "/svipOrder/saveAutomaticGrab.shtml")
	@ResponseBody
	public Map<String, Object> saveAutomaticGrab(HttpServletRequest request, Page page) {
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try{
			String delProductIds = request.getParameter("delProductIds");
			ArrayList<Integer> productIdsList = new ArrayList<Integer>();
			if(!StringUtils.isEmpty(delProductIds)){
				Integer[] LongIds= (Integer[]) ConvertUtils.convert(delProductIds.split(","), Integer.class);
				Collections.addAll(productIdsList,LongIds);
			}
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			svipProductRecommendService.saveAutomaticGrab(productIdsList,staffID);
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败");
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 *
	 * @Title refundSvipOrderManager
	 * @Description TODO(年费退款明细)
	 * @author chenyk
	 * @date 2020年6月18日 上午9:23:22
	 */
	@RequestMapping("/svipOrder/refundSvipOrderManager.shtml")
	public ModelAndView refundSvipOrderManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/member/svip/refundSvipOrderList");
		m.addObject("svipOrderStatusList", DataDicUtil.getStatusList("BU_SVIP_ORDER", "STATUS"));
		if(!StringUtils.isEmpty(request.getParameter("refundDate"))){
			m.addObject("nowDate", request.getParameter("refundDate"));
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			m.addObject("nowDate", sdf.format(new Date()));
		}
		return m;
	}

	/**
	 *
	 * @Title refundSvipOrderList
	 * @Description TODO(年费退款明细)
	 * @author chenyk
	 * @date 2020年6月18日 上午9:23:22
	 */
	@ResponseBody
	@RequestMapping("/svipOrder/refundSvipOrderList.shtml")
	public Map<String, Object> refundSvipOrderList(HttpServletRequest request, Page page) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		List<SvipOrderCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SvipOrderCustomExample svipOrderCustomExample = new SvipOrderCustomExample();
			SvipOrderCustomExample.SvipOrderCustomCriteria svipOrderCustomCriteria = svipOrderCustomExample.createCriteria();
			svipOrderCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("2");
			if(!StringUtil.isEmpty(request.getParameter("memberId")) ) {
				svipOrderCustomCriteria.andMemberIdEqualTo(Integer.parseInt(request.getParameter("memberId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("orderCode")) ) {
				svipOrderCustomCriteria.andOrderCodeLike("%"+request.getParameter("orderCode")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("paymentId")) ) {
				String paymentId=request.getParameter("paymentId");
				if (paymentId.equals("1")){//微信APP/H5
					Integer[] wxStr={2,5};
					List<Integer> wxList = Arrays.asList(wxStr);
					svipOrderCustomCriteria.andPaymentIdIn(wxList);
				}
				if (paymentId.equals("2")){//支付宝APP/H5
					Integer[] zfbStr={1,6};
					List<Integer> zfbList = Arrays.asList(zfbStr);
					svipOrderCustomCriteria.andPaymentIdIn(zfbList);
				}
				if (paymentId.equals("3")){//微信公众号
					Integer[] wxStr2={4,7};
					List<Integer> wxList2 = Arrays.asList(wxStr2);
					svipOrderCustomCriteria.andPaymentIdIn(wxList2);
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("refundDateBegin")) ) {
				svipOrderCustomCriteria.andRefundDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("refundDateBegin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("refundDateEnd")) ) {
				svipOrderCustomCriteria.andRefundDateLessThanOrEqualTo(sdf.parse(request.getParameter("refundDateEnd")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("paymentNo")) ) {
				svipOrderCustomCriteria.andPaymentNoLike("%"+request.getParameter("paymentNo")+"%");
			}
			svipOrderCustomExample.setOrderByClause(" t.refund_date desc");
			svipOrderCustomExample.setLimitStart(page.getLimitStart());
			svipOrderCustomExample.setLimitSize(page.getLimitSize());
			dataList = svipOrderService.selectByCustomExample(svipOrderCustomExample);
			totalCount = svipOrderService.countByCustomExample(svipOrderCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}

	/**
	 *
	 * @Title exportFinanceSvipOrderList
	 * @Description TODO(年费退款明细导出)
	 * @author chenyk
	 * @date 2020年6月18日 上午9:23:22
	 */
	@RequestMapping("/svipOrder/exportRefundSvipOrderList.shtml")
	public void exportRefundSvipOrderList(HttpServletRequest request, HttpServletResponse response) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SvipOrderCustomExample svipOrderCustomExample = new SvipOrderCustomExample();
			SvipOrderCustomExample.SvipOrderCustomCriteria svipOrderCustomCriteria = svipOrderCustomExample.createCriteria();
			svipOrderCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("2");
			if(!StringUtil.isEmpty(request.getParameter("memberId")) ) {
				svipOrderCustomCriteria.andMemberIdEqualTo(Integer.parseInt(request.getParameter("memberId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("orderCode")) ) {
				svipOrderCustomCriteria.andOrderCodeLike("%"+request.getParameter("orderCode")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("paymentId")) ) {
				String paymentId=request.getParameter("paymentId");
				if (paymentId.equals("1")){//微信APP/H5
					Integer[] wxStr={2,5};
					List<Integer> wxList = Arrays.asList(wxStr);
					svipOrderCustomCriteria.andPaymentIdIn(wxList);
				}
				if (paymentId.equals("2")){//支付宝APP/H5
					Integer[] zfbStr={1,6};
					List<Integer> zfbList = Arrays.asList(zfbStr);
					svipOrderCustomCriteria.andPaymentIdIn(zfbList);
				}
				if (paymentId.equals("3")){//微信公众号
					Integer[] wxStr2={4,7};
					List<Integer> wxList2 = Arrays.asList(wxStr2);
					svipOrderCustomCriteria.andPaymentIdIn(wxList2);
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("refundDateBegin")) ) {
				svipOrderCustomCriteria.andRefundDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("refundDateBegin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("refundDateEnd")) ) {
				svipOrderCustomCriteria.andRefundDateLessThanOrEqualTo(sdf.parse(request.getParameter("refundDateEnd")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("paymentNo")) ) {
				svipOrderCustomCriteria.andPaymentNoLike("%"+request.getParameter("paymentNo")+"%");
			}
			svipOrderCustomExample.setOrderByClause(" t.refund_date desc");
			List<SvipOrderCustom> svipOrderCustomList = svipOrderService.selectByCustomExample(svipOrderCustomExample);
			String[] titles = {"订单编号","会员ID","昵称","退款渠道","实付金额","订单状态","退款时间"};
			ExcelBean excelBean = new ExcelBean("SVIP退款明细.xls", "SVIP退款明细", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(SvipOrderCustom svipOrderCustom : svipOrderCustomList) {
				String[] data = {
						svipOrderCustom.getOrderCode(),
						svipOrderCustom.getMemberId()==null?"":svipOrderCustom.getMemberId().toString(),
						svipOrderCustom.getMemberNick(),
						svipOrderCustom.getPaymentName(),
						svipOrderCustom.getPayAmount()==null?"":svipOrderCustom.getPayAmount().toString(),
						svipOrderCustom.getSvipOrderDesc(),
						svipOrderCustom.getRefundDate()==null?"":sdf.format(svipOrderCustom.getRefundDate())
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 *
	 * @Title refCountSvipOrderManager
	 * @Description TODO(每日年费退款汇总)
	 * @author chenyk
	 * @date 2020年6月18日 下午15:24:59
	 */
	@RequestMapping("/svipOrder/refCountSvipOrderManager.shtml")
	public ModelAndView refCountSvipOrderManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/member/svip/refCountSvipOrderList");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String refund_date_end = sdf.format(new Date());
		String refund_date_begin = refund_date_end.substring(0,7)+"-01";
		m.addObject("refund_date_end", refund_date_end);
		m.addObject("refund_date_begin", refund_date_begin);
		return m;
	}

	/**
	 *
	 * @Title refCountSvipOrderList
	 * @Description TODO(每日年费退款汇总)
	 * @author chenyk
	 * @date 2020年6月18日 下午15:25:19
	 */
	@ResponseBody
	@RequestMapping("/svipOrder/refCountSvipOrderList.shtml")
	public Map<String, Object> refCountSvipOrderList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = null;
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String refundDateBegin = request.getParameter("refund_date_begin");
			String refundDateEnd = request.getParameter("refund_date_end");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(refundDateBegin) ){
				paramMap.put("refundDateBegin", refundDateBegin+" 00:00:00");
			}else{
				paramMap.put("refundDateBegin", sdf.format(new Date()).substring(0,7)+"-01 00:00:00");
			}
			if(!StringUtil.isEmpty(refundDateEnd) ){
				paramMap.put("refundDateEnd", refundDateEnd+" 23:59:59");
			}else{
				paramMap.put("refundDateEnd", sdf.format(new Date()));
			}
			dataList = svipOrderService.refCountSvipOrderList(paramMap);
			resMap.put("Rows", dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 *
	 * @Title exportRefCountSvipOrderList
	 * @Description TODO(每日年费退款汇总导出)
	 * @author chenyk
	 * @date 2020年6月18日 下午15:25:36
	 */
	@RequestMapping("/svipOrder/exportRefCountSvipOrderList.shtml")
	public void exportRefCountSvipOrderList(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String refundDateBegin = request.getParameter("refund_date_begin");
			String refundDateEnd = request.getParameter("refund_date_end");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(refundDateBegin) ){
				paramMap.put("refundDateBegin", refundDateBegin+" 00:00:00");
			}else{
				paramMap.put("refundDateBegin", sdf.format(new Date()).substring(0,7)+"-01 00:00:00");
			}
			if(!StringUtil.isEmpty(refundDateEnd) ){
				paramMap.put("refundDateEnd", refundDateEnd+" 23:59:59");
			}else{
				paramMap.put("refundDateEnd", sdf.format(new Date()));
			}
			List<Map<String, Object>> dataList = svipOrderService.refCountSvipOrderList(paramMap);
			String[] titles = {"退款日期","笔数","退款金额","微信金额","支付宝金额"};
			ExcelBean excelBean = new ExcelBean("每日SVIP退款汇总.xls", "每日SVIP退款汇总", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(Map<String, Object> dataMap : dataList) {
				String[] data = {
						dataMap.get("eachDay")+"",
						dataMap.get("totalCount")+"",
						dataMap.get("combineAmount")+"",
						dataMap.get("wxAmount")+"",
						dataMap.get("zfbAmount")+""
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * SVIP推荐导航管理页面
	 * @author XDD
	 * @date 2020年7月4日 下午17:28:36
	 */
	@RequestMapping("/svipOrder/svipRecommendNavigationView.shtml")
	public ModelAndView svipRecommendNavigationView(HttpServletRequest request) {
		return new ModelAndView("/member/svip/svipRecommendNavigationView");
	}

	/**
	 * SVIP推荐导航管理列表
	 * @author XDD
	 * @date 2020年7月4日 下午17:28:36
	 */
	@ResponseBody
	@RequestMapping("/svipOrder/svipRecommendNavigationList.shtml")
	public Map<String, Object> svipRecommendNavigationList(HttpServletRequest request, Page page) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		List<SvipRecommendNavigation> dataList = null;
		Integer totalCount = 0;
		try {
			SvipRecommendNavigationExample example = new SvipRecommendNavigationExample();
			SvipRecommendNavigationExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			example.setOrderByClause(" IFNULL(seq_no,99999) ASC, id ASC");
			totalCount = svipRecommendNavigationService.countByExample(example);
			dataList = svipRecommendNavigationService.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}

	/**
	 * SVIP推荐导航管理上下架状态
	 * @author XDD
	 * @date 2020年7月4日 下午17:28:36
	 */
	@RequestMapping("/svipOrder/changeSvipRecommendNavigation.shtml")
	@ResponseBody
	public Map<String, Object> changeSvipRecommendNavigation(HttpServletRequest request, String id, String status) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			Date date = new Date();
			SvipRecommendNavigation svipRecommendNavigation = new SvipRecommendNavigation();
			svipRecommendNavigation.setId(Integer.valueOf(id));
			svipRecommendNavigation.setStatus(status);
			svipRecommendNavigation.setStatusDate(date);
			svipRecommendNavigation.setUpdateBy(staffID);
			svipRecommendNavigation.setUpdateDate(date);
			svipRecommendNavigationService.updateByPrimaryKeySelective(svipRecommendNavigation);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
		}
		return resMap;
	}

	/**
	 * 删除SVIP推荐导航管理
	 * @author XDD
	 * @date 2020年7月4日 下午17:28:36
	 */
	@RequestMapping("/svipOrder/delSvipRecommendNavigation.shtml")
	@ResponseBody
	public Map<String, Object> delSvipRecommendNavigation(HttpServletRequest request, String id) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			SvipRecommendNavigation svipRecommendNavigation = new SvipRecommendNavigation();
			svipRecommendNavigation.setId(Integer.valueOf(id));
			svipRecommendNavigation.setUpdateBy(staffID);
			svipRecommendNavigation.setUpdateDate(new Date());
			svipRecommendNavigation.setDelFlag("1");
			svipRecommendNavigationService.updateByPrimaryKeySelective(svipRecommendNavigation);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
		}
		return resMap;
	}

	/**
	 * SVIP推荐导航修改排序
	 * @author XDD
	 * @date 2020年7月4日 下午17:28:36
	 */
	@ResponseBody
	@RequestMapping("/svipOrder/updateSvipRecommendNavigationSeqNo.shtml")
	public Map<String, Object> updateSvipRecommendNavigationSeqNo(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String id = request.getParameter("id");
			String seqNo = request.getParameter("seqNo");
			if(!StringUtil.isEmpty(id)) {
				if(!StringUtil.isEmpty(seqNo)) {
					SvipRecommendNavigation svipRecommendNavigation = new SvipRecommendNavigation();
					svipRecommendNavigation.setId(Integer.parseInt(id));
					svipRecommendNavigation.setSeqNo(Integer.parseInt(seqNo));
					svipRecommendNavigation.setUpdateBy(Integer.parseInt(staffID));
					svipRecommendNavigation.setUpdateDate(date);
					svipRecommendNavigationService.updateByPrimaryKeySelective(svipRecommendNavigation);
				}else{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					svipProductRecommendService.updateSeqNoIsNull(Integer.parseInt(id),Integer.parseInt(staffID),sdf.format(date));
					svipRecommendNavigationService.updateSeqNoIsNull(Integer.parseInt(id),Integer.parseInt(staffID),sdf.format(date));
				}
			}else {
				code = "9999";
				msg = "ID为空！";
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

	/**
	 * SVIP推荐导航编辑页面
	 * @author XDD
	 * @date 2020年7月4日 下午17:28:36
	 */
	@RequestMapping("/svipOrder/editSvipRecommendNavigationView.shtml")
	public ModelAndView editSvipRecommendNavigationView(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/member/svip/editSvipRecommendNavigationView");
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			SvipRecommendNavigation svipRecommendNavigation = svipRecommendNavigationService.selectByPrimaryKey(Integer.valueOf(id));
			m.addObject("svipRecommendNavigation", svipRecommendNavigation);
		}
		return m;
	}

	/**
	 * SVIP推荐导航编辑数据提交
	 * @author XDD
	 * @date 2020年7月4日 下午17:28:36
	 */
	@RequestMapping("/svipOrder/editSvipRecommendNavigation.shtml")
	@ResponseBody
	public Map<String, Object> editSvipRecommendNavigation(HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String remarks = request.getParameter("remarks");
			String keyword = request.getParameter("keyword");
			String seqNo = request.getParameter("seqNo");
			SvipRecommendNavigation svipRecommendNavigation = new SvipRecommendNavigation();
			if(!StringUtils.isEmpty(id)){
				svipRecommendNavigation = svipRecommendNavigationService.selectByPrimaryKey(Integer.valueOf(id));
			}
			svipRecommendNavigation.setName(name);
			svipRecommendNavigation.setRemarks(remarks);
			svipRecommendNavigation.setKeyword(keyword);
			if(!StringUtils.isEmpty(seqNo)){
				svipRecommendNavigation.setSeqNo(Integer.valueOf(seqNo));
			}else {
				svipRecommendNavigation.setSeqNo(null);
			}
			svipRecommendNavigationService.saveSvipRecommendNavigation(svipRecommendNavigation, staffID);
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * SVIP商品推荐批量添加关键字
	 * @author XDD
	 * @date 2020年7月4日 下午17:28:36
	 */
	@ResponseBody
	@RequestMapping("/svipOrder/batchSaveKeyword.shtml")
	public Map<String, Object> batchSaveKeyword(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			String keyword = request.getParameter("keyword");
			SvipProductRecommendExample example = new SvipProductRecommendExample();
			SvipProductRecommendExample.Criteria criteria = example.createCriteria();
			criteria.andIdIn( Arrays.asList((Integer[]) ConvertUtils.convert(request.getParameter("ids").split(","), Integer.class)));
			SvipProductRecommend svipProductRecommend = new SvipProductRecommend();
			svipProductRecommend.setKeyword(keyword);
			svipProductRecommend.setUpdateBy(staffID);
			svipProductRecommend.setUpdateDate(new Date());
			svipProductRecommendService.updateByExampleSelective(svipProductRecommend, example);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "操作失败！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
}
