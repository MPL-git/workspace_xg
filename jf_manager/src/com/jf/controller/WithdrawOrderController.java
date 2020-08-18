package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.entity.WithdrawCnf;
import com.jf.entity.WithdrawCnfExample;
import com.jf.entity.WithdrawOrderCustom;
import com.jf.entity.WithdrawOrderCustomExample;
import com.jf.service.WithdrawCnfService;
import com.jf.service.WithdrawOrderService;
import com.jf.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("serial")
@Controller
public class WithdrawOrderController extends BaseController {

	@Autowired
	private WithdrawOrderService withdrawOrderService;
	
	@Autowired
	private WithdrawCnfService withdrawCnfService;

	/**
	 * 
	 * @Title withdrawOrderManager   
	 * @Description TODO(签到提现汇总)   
	 * @author pengl
	 * @date 2018年6月14日 上午10:27:19
	 */
	@RequestMapping("/withdrawOrder/withdrawOrderManager.shtml")
	public ModelAndView withdrawOrderManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/withdrawOrder/getWithdrawOrderList");
		m.addObject("statusList", DataDicUtil.getTableStatus("BU_WITHDRAW_ORDER", "STATUS"));
		return m;
	}
	
	/**
	 * 
	 * @Title getWithdrawOrderList   
	 * @Description TODO(签到提现汇总)   
	 * @author pengl
	 * @date 2018年6月14日 上午10:38:29
	 */
	@ResponseBody
	@RequestMapping("/withdrawOrder/getWithdrawOrderList.shtml")
	public Map<String, Object> getWithdrawOrderList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<WithdrawOrderCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			WithdrawOrderCustomExample withdrawOrderCustomExample = new WithdrawOrderCustomExample();
			WithdrawOrderCustomExample.WithdrawOrderCustomCriteria withdrawOrderCustomCriteria = withdrawOrderCustomExample.createCriteria();
			withdrawOrderCustomCriteria.andDelFlagEqualTo("0");
			
			if(!StringUtil.isEmpty(request.getParameter("beginCreateDate"))) {
				withdrawOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginCreateDate")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
				withdrawOrderCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCreateDate")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("status"))) {
				withdrawOrderCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			withdrawOrderCustomExample.setOrderByClause(" t.id desc");
			withdrawOrderCustomExample.setLimitStart(page.getLimitStart());
			withdrawOrderCustomExample.setLimitSize(page.getLimitSize());
			totalCount = withdrawOrderService.countByCustomExample(withdrawOrderCustomExample);
			dataList = withdrawOrderService.selectByCustomExample(withdrawOrderCustomExample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title exportWithdrawOrderList   
	 * @Description TODO(导出)   
	 * @author pengl
	 * @date 2018年6月14日 上午10:48:43
	 */
	@RequestMapping("/withdrawOrder/exportWithdrawOrderList.shtml")
	public void exportWithdrawOrderList(HttpServletRequest request, HttpServletResponse response) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			WithdrawOrderCustomExample withdrawOrderCustomExample = new WithdrawOrderCustomExample();
			WithdrawOrderCustomExample.WithdrawOrderCustomCriteria withdrawOrderCustomCriteria = withdrawOrderCustomExample.createCriteria();
			withdrawOrderCustomCriteria.andDelFlagEqualTo("0");
			
			if(!StringUtil.isEmpty(request.getParameter("beginCreateDate"))) {
				withdrawOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginCreateDate")));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
				withdrawOrderCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCreateDate")));
			}
			if(!StringUtil.isEmpty(request.getParameter("status"))) {
				withdrawOrderCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			withdrawOrderCustomExample.setOrderByClause(" t.id desc");
			List<WithdrawOrderCustom> dataList = withdrawOrderService.selectByCustomExample(withdrawOrderCustomExample);

			String[] titles = {"申请时间","用户ID","微信ID","提现方式","提现金额","状态"};
			ExcelBean excelBean = new ExcelBean("签到提现汇总.xls", "签到提现汇总", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(WithdrawOrderCustom withdrawOrderCustom : dataList) {
				String[] data = {
						sdf.format(withdrawOrderCustom.getCreateDate()),
						withdrawOrderCustom.getMemberId().toString(),
						withdrawOrderCustom.getWeixinId(),
						withdrawOrderCustom.getWithdrawType().equals("2")?withdrawOrderCustom.getAmount()+"元微信红包":withdrawOrderCustom.getCouponName(),
						withdrawOrderCustom.getAmount().toString(),
						withdrawOrderCustom.getStatusDesc()
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
	 * @Title withdrawOrderAuditManager   
	 * @Description TODO(签到提现审核)   
	 * @author pengl
	 * @date 2018年8月8日 下午1:59:54
	 */
	@RequestMapping("/withdrawOrder/withdrawOrderAuditManager.shtml")
	public ModelAndView withdrawOrderAuditManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/withdrawOrder/getWithdrawOrderAuditList");
		WithdrawCnfExample withdrawCnfExample = new WithdrawCnfExample();
		withdrawCnfExample.createCriteria().andDelFlagEqualTo("0");
		List<WithdrawCnf> withdrawCnfList = withdrawCnfService.selectByExample(withdrawCnfExample);
		m.addObject("withdrawCnfList", withdrawCnfList);
		m.addObject("statusList", DataDicUtil.getTableStatus("BU_WITHDRAW_ORDER", "STATUS"));
		m.addObject("withdrawTypeList", DataDicUtil.getTableStatus("BU_WITHDRAW_ORDER", "WITHDRAW_TYPE"));
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/withdrawOrder/getWithdrawOrderAuditList.shtml")
	public Map<String, Object> getWithdrawOrderAuditList(HttpServletRequest request, Page page, Integer memberId) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<WithdrawOrderCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			WithdrawOrderCustomExample withdrawOrderCustomExample = new WithdrawOrderCustomExample();
			WithdrawOrderCustomExample.WithdrawOrderCustomCriteria withdrawOrderCustomCriteria = withdrawOrderCustomExample.createCriteria();
			withdrawOrderCustomCriteria.andDelFlagEqualTo("0").andWithdrawTypeIn(Arrays.asList("1","2"));
			if(memberId != null ) {
				withdrawOrderCustomCriteria.andMemberIdEqualTo(memberId);
			}
			if(!StringUtil.isEmpty(request.getParameter("beginCreateDate"))) {
				withdrawOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginCreateDate")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
				withdrawOrderCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCreateDate")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("status"))) { //审核状态
				withdrawOrderCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			if(!StringUtil.isEmpty(request.getParameter("withdrawType"))) { //提现类型
				withdrawOrderCustomCriteria.andWithdrawTypeEqualTo(request.getParameter("withdrawType"));
			}
			if(!StringUtil.isEmpty(request.getParameter("withdrawCnfId"))) { //提现名称
				withdrawOrderCustomCriteria.andWithdrawCnfIdEqualTo(Integer.parseInt(request.getParameter("withdrawCnfId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("expenseFlag"))) { //是否消费
				withdrawOrderCustomCriteria.andExpenseFlag(request.getParameter("expenseFlag"));
			}
			if(!StringUtil.isEmpty(request.getParameter("withdrawOrderFlag"))) { //是否有提现
				withdrawOrderCustomCriteria.andWithdrawOrderFlag(request.getParameter("withdrawOrderFlag"));
			}
			withdrawOrderCustomExample.setOrderByClause(" t.status , t.create_date asc");
			withdrawOrderCustomExample.setLimitStart(page.getLimitStart());
			withdrawOrderCustomExample.setLimitSize(page.getLimitSize());
			totalCount = withdrawOrderService.countByCustomExample(withdrawOrderCustomExample);
			dataList = withdrawOrderService.selectByCustomExample(withdrawOrderCustomExample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}

	@RequestMapping("/withdrawOrder/exportWithdrawOrderAuditList.shtml")
	public void exportWithdrawOrderAuditList(HttpServletRequest request,HttpServletResponse response,Integer memberId){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			WithdrawOrderCustomExample withdrawOrderCustomExample = new WithdrawOrderCustomExample();
			WithdrawOrderCustomExample.WithdrawOrderCustomCriteria withdrawOrderAuditCustomCriteria = withdrawOrderCustomExample.createCriteria();
			withdrawOrderAuditCustomCriteria.andDelFlagEqualTo("0").andWithdrawTypeIn(Arrays.asList("1","2"));
			if(memberId != null ) {
				withdrawOrderAuditCustomCriteria.andMemberIdEqualTo(memberId);
			}
			if(!StringUtil.isEmpty(request.getParameter("beginCreateDate"))) {
				withdrawOrderAuditCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginCreateDate")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
				withdrawOrderAuditCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCreateDate")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("status"))) { //审核状态
				withdrawOrderAuditCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			if(!StringUtil.isEmpty(request.getParameter("withdrawType"))) { //提现类型
				withdrawOrderAuditCustomCriteria.andWithdrawTypeEqualTo(request.getParameter("withdrawType"));
			}
			if(!StringUtil.isEmpty(request.getParameter("withdrawCnfId"))) { //提现名称
				withdrawOrderAuditCustomCriteria.andWithdrawCnfIdEqualTo(Integer.parseInt(request.getParameter("withdrawCnfId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("expenseFlag"))) { //是否消费
				withdrawOrderAuditCustomCriteria.andExpenseFlag(request.getParameter("expenseFlag"));
			}
			if(!StringUtil.isEmpty(request.getParameter("withdrawOrderFlag"))) { //是否有提现
				withdrawOrderAuditCustomCriteria.andWithdrawOrderFlag(request.getParameter("withdrawOrderFlag"));
			}
			withdrawOrderCustomExample.setOrderByClause(" t.status , t.create_date asc");
			List<WithdrawOrderCustom> dataList = withdrawOrderService.selectByCustomExample(withdrawOrderCustomExample);

			String[] titles = {"会员ID","会员名称","是否有消费","提现次数","剩余可提现余额","已提现金额","提现金额","提现类型","提现名称","申请时间","状态"};
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			ExcelBean excelBean = new ExcelBean("签到提现列表"+df.format(new Date())+".xls","签到提现列表",titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(WithdrawOrderCustom withdrawOrderCustom : dataList) {
				String[] data = {
						withdrawOrderCustom.getMemberId().toString(),
						withdrawOrderCustom.getMemberNick(),
						withdrawOrderCustom.getCombineOrderCount()>0?"是":"否",
						withdrawOrderCustom.getWithdrawOrderCount().toString(),
						withdrawOrderCustom.getMemberAccountFreeze().toString(),
						withdrawOrderCustom.getAmountSum().toString(),
						withdrawOrderCustom.getAmount().toString(),
						withdrawOrderCustom.getWithdrawType().equals("2")?withdrawOrderCustom.getAmount()+"元微信红包":withdrawOrderCustom.getCouponName(),//提现类型
						withdrawOrderCustom.getWithdrawCnfName(),
						sdf.format(withdrawOrderCustom.getCreateDate()), //申请时间
						withdrawOrderCustom.getStatusDesc() + (withdrawOrderCustom.getWithdrawMethod().equals("1")?"(线下提现)":"")
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
	 * 
	 * @Title auditWithdrawOrderManager   
	 * @Description TODO(审核)   
	 * @author pengl
	 * @date 2018年8月8日 下午5:14:06
	 */
	@RequestMapping("/withdrawOrder/auditWithdrawOrderManager.shtml")
	public ModelAndView auditWithdrawOrderManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/withdrawOrder/auditWithdrawOrder");
		if(!StringUtil.isEmpty(request.getParameter("ids"))) {
			String[] ids = request.getParameter("ids").split(",");
			m.addObject("idsLength", ids.length);
		}
		m.addObject("ids", request.getParameter("ids"));
		return m;
	}
	
	/**
	 * 
	 * @Title auditWithdrawOrder   
	 * @Description TODO(审核)   
	 * @author pengl
	 * @date 2018年8月8日 下午6:23:34
	 */
	@RequestMapping("/withdrawOrder/auditWithdrawOrder.shtml")
	@ResponseBody
	public Map<String, Object> auditWithdrawOrder(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			List<Integer> idsList = new ArrayList<Integer>();
			if(!StringUtil.isEmpty(request.getParameter("ids"))) {
				String[] ids = request.getParameter("ids").split(",");
				for(String idStr : ids) {
					if(!StringUtil.isEmpty(idStr)) {
						String[] idA = idStr.split("\\-");
						if(!StringUtil.isEmpty(idA[1]) && "1".equals(idA[1])) {
							if(!idsList.contains(Integer.parseInt(idA[0]))){
								idsList.add(Integer.parseInt(idA[0]));
							}
						}
					}
				}
			}
			withdrawOrderService.auditWithdrawOrder(staffID, idsList, request.getParameter("status"), request.getParameter("remarks"));
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "系统异常，请稍后重试");
		}
		return resMap; 
	}
	
	
	
}
