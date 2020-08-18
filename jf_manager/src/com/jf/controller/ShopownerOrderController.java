package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtils;
import com.jf.entity.ShopownerOrderCustom;
import com.jf.entity.ShopownerOrderCustomExample;
import com.jf.entity.ShopownerOrderCustomExample.ShopownerOrderCustomCriteria;
import com.jf.entity.SysPayment;
import com.jf.entity.SysPaymentExample;
import com.jf.entity.SysPaymentExample.Criteria;
import com.jf.service.ShopownerOrderService;
import com.jf.service.SysPaymentService;
import com.jf.vo.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;



@Controller
public class ShopownerOrderController extends BaseController {
	private static final Log logger = LogFactory.getLog(ShopownerOrderService.class);
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ShopownerOrderService shopownerOrderService;
	
	@Autowired
	private SysPaymentService sysPaymentService;
	
	/**
	 * @Title financeSvipOrderManager   
	 * @Description TODO(店长年费收款明细)   
	 */
	@RequestMapping("/shopownerOrder/shopownerOrder.shtml")
	public ModelAndView shopownerOrder(HttpServletRequest request) {	
		ModelAndView m = new ModelAndView("/shopowner/shopownerOrderList");
		List<SysPayment> list = new ArrayList<SysPayment>();
		if(!StringUtils.isEmpty(request.getParameter("paymentId"))){
			m.addObject("paymentId", request.getParameter("paymentId"));
			SysPayment paymentOne = new SysPayment();//设置id=100为微信APP/H5
			paymentOne.setId(100);
			paymentOne.setRemarks("微信APP/H5");
			SysPayment paymentTwo = new SysPayment();//设置id=110为支付宝APP/H5
			paymentTwo.setId(110);
			paymentTwo.setRemarks("支付宝APP/H5");
			SysPayment paymentThree = new SysPayment();//设置id=120为微信公众号/小程序
			paymentThree.setId(120);
			paymentThree.setRemarks("微信公众号/小程序");
			list.add(paymentOne);
			list.add(paymentTwo);
			list.add(paymentThree);
		}else {
			SysPaymentExample example = new SysPaymentExample();
			Criteria criteria = example.createCriteria().andDelFlagEqualTo("0");
			list = sysPaymentService.selectByExample(example);
		}
		m.addObject("sysPaymentList",list);
		if(!StringUtils.isEmpty(request.getParameter("payDate"))){
			m.addObject("nowDate", request.getParameter("payDate"));
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			m.addObject("nowDate", sdf.format(new Date()));
		}
		return m;
	}
	
	/**
	 * @Title shopownerOrderList   
	 * @Description TODO(店长年费收款明细列表)   
	 */
	@ResponseBody
	@RequestMapping("/shopownerOrder/shopownerOrderList.shtml")
	public Map<String, Object> shopownerOrderList(HttpServletRequest request, Page page) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		List<ShopownerOrderCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ShopownerOrderCustomExample example = new ShopownerOrderCustomExample();
			ShopownerOrderCustomCriteria criteria = example.createCriteria();
			example.setOrderByClause("pay_date desc");
			criteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
			
			if(!StringUtil.isEmpty(request.getParameter("memberId")) ) {
				criteria.andMemberIdEqualTo(Integer.parseInt(request.getParameter("memberId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("orderCode")) ) {
				criteria.andOrderCodeEqualTo(request.getParameter("orderCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("paymentId")) ) {
				String paymentId=request.getParameter("paymentId");
				if (paymentId.equals("100")){//微信APP/H5
					Integer[] wxStr={2,5};
					List<Integer> wxList = Arrays.asList(wxStr);
					criteria.andPaymentIdIn(wxList);
				}else if(paymentId.equals("110")){//支付宝APP/H5
					Integer[] zfbStr={1,6};
					List<Integer> zfbList = Arrays.asList(zfbStr);
					criteria.andPaymentIdIn(zfbList);
				}else if(paymentId.equals("120")){//微信公众号/小程序
					Integer[] wxStr2={4,7};
					List<Integer> wxList2 = Arrays.asList(wxStr2);
					criteria.andPaymentIdIn(wxList2);
				}else{
					criteria.andPaymentIdEqualTo(Integer.parseInt(request.getParameter("paymentId")));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("payDateBegin")) ) {
				criteria.andPayDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("payDateBegin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("payDateEnd")) ) {
				criteria.andPayDateLessThanOrEqualTo(sdf.parse(request.getParameter("payDateEnd")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("paymentNo")) ) {
				criteria.andPaymentNoEqualTo(request.getParameter("paymentNo"));
			}
			totalCount = shopownerOrderService.countByCustomExample(example);
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			dataList = shopownerOrderService.selectByCustomExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * @Title exportShopownerOrderList   
	 * @Description TODO(店长年费收款明细列表导出)   
	 */
	@RequestMapping("/shopownerOrder/exportShopownerOrderList.shtml")
	public void exportShopownerOrderList(HttpServletRequest request, HttpServletResponse response) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ShopownerOrderCustomExample example = new ShopownerOrderCustomExample();
			ShopownerOrderCustomCriteria criteria = example.createCriteria();
			example.setOrderByClause("pay_date desc");
			criteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
			
			if(!StringUtil.isEmpty(request.getParameter("memberId")) ) {
				criteria.andMemberIdEqualTo(Integer.parseInt(request.getParameter("memberId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("orderCode")) ) {
				criteria.andOrderCodeEqualTo(request.getParameter("orderCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("paymentId")) ) {
				String paymentId=request.getParameter("paymentId");
				if (paymentId.equals("100")){//微信APP/H5
					Integer[] wxStr={2,5};
					List<Integer> wxList = Arrays.asList(wxStr);
					criteria.andPaymentIdIn(wxList);
				}else if(paymentId.equals("110")){//支付宝APP/H5
					Integer[] zfbStr={1,6};
					List<Integer> zfbList = Arrays.asList(zfbStr);
					criteria.andPaymentIdIn(zfbList);
				}else if(paymentId.equals("120")){//微信公众号/小程序
					Integer[] wxStr2={4,7};
					List<Integer> wxList2 = Arrays.asList(wxStr2);
					criteria.andPaymentIdIn(wxList2);
				}else{
					criteria.andPaymentIdEqualTo(Integer.parseInt(request.getParameter("paymentId")));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("payDateBegin")) ) {
				criteria.andPayDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("payDateBegin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("payDateEnd")) ) {
				criteria.andPayDateLessThanOrEqualTo(sdf.parse(request.getParameter("payDateEnd")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("paymentNo")) ) {
				criteria.andPaymentNoEqualTo(request.getParameter("paymentNo"));
			}
			List<ShopownerOrderCustom> dataList = shopownerOrderService.selectByCustomExample(example);
			String[] titles = {"订单编号","会员ID","昵称","付款渠道","实付金额","订单状态","支付时间"};
			ExcelBean excelBean = new ExcelBean("店长年费收款明细.xls", "店长年费收款明细", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(ShopownerOrderCustom shopownerOrderCustom : dataList) {
				String[] data = {
						shopownerOrderCustom.getOrderCode(),
						shopownerOrderCustom.getMemberId()==null?"":shopownerOrderCustom.getMemberId().toString(),
						shopownerOrderCustom.getNick(),
						shopownerOrderCustom.getPaymentName(),
						shopownerOrderCustom.getPayAmount()==null?"":shopownerOrderCustom.getPayAmount().toString(),
						shopownerOrderCustom.getStatus()=="0"?"未付款":"已付款",
						shopownerOrderCustom.getPayDate()==null?"":sdf.format(shopownerOrderCustom.getPayDate())
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
	 * @Title statisticsShopownerOrder   
	 * @Description TODO(店长权益收款汇总)   
	 */
	@RequestMapping("/shopownerOrder/statisticsShopownerOrder.shtml")
	public ModelAndView statisticsShopownerOrder(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/shopowner/statisticsShopownerOrder");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String pay_date_end = sdf.format(new Date());
		String pay_date_begin = pay_date_end.substring(0,7)+"-01";
		m.addObject("pay_date_end", pay_date_end);
		m.addObject("pay_date_begin", pay_date_begin);
		return m;
	}
	
	/**
	 * @Title statisticsShopownerOrder   
	 * @Description TODO(店长权益收款汇总列表)   
	 */
	@ResponseBody
	@RequestMapping("/shopownerOrder/statisticsShopownerOrderList.shtml")
	public Map<String, Object> statisticsShopownerOrderList(HttpServletRequest request, Page page) {
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
			dataList = shopownerOrderService.statisticsShopownerOrder(paramMap);
			resMap.put("Rows", dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * @Title exportStatisticsShopownerOrderList  
	 * @Description TODO(店长权益收款汇总导出)   
	 */
	@RequestMapping("/shopownerOrder/exportStatisticsShopownerOrderList.shtml")
	public void exportStatisticsShopownerOrderList(HttpServletRequest request, HttpServletResponse response) {
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
			List<Map<String, Object>> dataList = shopownerOrderService.statisticsShopownerOrder(paramMap);
			String[] titles = {"付款日期","笔数","总金额","支付宝APP/H5金额","微信APP/H5金额","微信公众号/小程序金额","支付宝APP/H5笔数","微信APP/H5笔数","微信公众号/小程序笔数"};
			ExcelBean excelBean = new ExcelBean("店长权益收款汇总.xls", "店长权益收款汇总", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(Map<String, Object> dataMap : dataList) {
				String[] data = {
						dataMap.get("eachDay").toString(),
						dataMap.get("totalCount").toString(),
						dataMap.get("combineAmount").toString(),
						dataMap.get("zfbAmount").toString(),
						dataMap.get("wxAmount").toString(),
						dataMap.get("gzhAmount").toString(),
						dataMap.get("zfbCount").toString(),
						dataMap.get("wxCount").toString(),
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
}
