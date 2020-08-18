package com.jf.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.bean.ExcelBean;
import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtil;
import com.jf.entity.DepositOrder;
import com.jf.entity.DepositOrderCustom;
import com.jf.entity.DepositOrderCustomExample;
import com.jf.entity.MchtDeposit;
import com.jf.entity.MchtDepositDtlCustom;
import com.jf.entity.MchtDepositDtlCustomExample;
import com.jf.entity.PlatformCapitalAccount;
import com.jf.entity.ViolateOrder;
import com.jf.service.DepositOrderService;
import com.jf.service.MchtDepositDtlService;
import com.jf.service.MchtDepositService;
import com.jf.service.PlatformCapitalAccountService;
import com.jf.service.ViolateOrderService;

@Controller
@RequestMapping("/mchtDeposit")
public class MchtDepositController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(MchtDepositController.class);

	@Resource
	private MchtDepositService mchtDepositService;
	
	@Resource
	private MchtDepositDtlService mchtDepositDtlService;
	
	@Resource
	private DepositOrderService depositOrderService;
	
	@Resource
	private ViolateOrderService violateOrderService;
	
	@Resource
	private PlatformCapitalAccountService platformCapitalAccountService;
	
	/**
	 * 保证金管理首页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/mchtDepositIndex")
	public String mchtDepositIndex(Model model, HttpServletRequest request) {
		MchtDeposit mchtDeposit = mchtDepositService.getMchtDepositByMchtId(this.getMchtInfo().getId());
		model.addAttribute("mchtDeposit", mchtDeposit);
		model.addAttribute("txnTypeList", DataDicUtil.getStatusList("BU_MCHT_DEPOSIT_DTL", "TXN_TYPE"));
		return "mchtDeposit/mchtDepositIndex";
	}
	/**
	 * 商家保证金明细列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/getMchtDepositDtlList")
	@ResponseBody
	public ResponseMsg getMchtDepositDtlList(HttpServletRequest request,Page page) {
		Integer depositId = Integer.parseInt(request.getParameter("depositId"));
		Map<String, Object> returnData = new HashMap<String, Object>();
		MchtDepositDtlCustomExample mchtDepositDtlCustomExample=new MchtDepositDtlCustomExample();
		mchtDepositDtlCustomExample.setOrderByClause("t.id desc");
		MchtDepositDtlCustomExample.MchtDepositDtlCustomCriteria criteria=mchtDepositDtlCustomExample.createCriteria();
		criteria.andDelFlagEqualTo("0").andDepositIdEqualTo(depositId);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (!StringUtil.isEmpty(request.getParameter("createTimeBegin"))) {
				criteria.andCreateDateGreaterThanOrEqualTo(sf.parse(request.getParameter("createTimeBegin")+" 00:00:00"));
			}
			if (!StringUtil.isEmpty(request.getParameter("createTimeEnd"))) {
				criteria.andCreateDateLessThanOrEqualTo(sf.parse(request.getParameter("createTimeEnd")+" 23:59:59"));
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String txnType = request.getParameter("search_txnType");
		if(!StringUtil.isEmpty(txnType)){
			criteria.andTxnTypeEqualTo(txnType);
		}
		criteria.andTxnTypeNotEqualTo("A");//应缴变化
		int totalCount = mchtDepositDtlService.countByExample(mchtDepositDtlCustomExample);
		mchtDepositDtlCustomExample.setLimitStart(page.getLimitStart());
		mchtDepositDtlCustomExample.setLimitSize(page.getLimitSize());
		List<MchtDepositDtlCustom> mchtDepositDtlCustoms = mchtDepositDtlService.selectMchtDepositDtlCustomByExample(mchtDepositDtlCustomExample);
		for(MchtDepositDtlCustom mchtDepositDtlCustom:mchtDepositDtlCustoms){
			String txnTypeDesc = DataDicUtil.getStatusDesc("BU_MCHT_DEPOSIT_DTL", "TXN_TYPE", mchtDepositDtlCustom.getTxnType());
			String typeSubDesc = DataDicUtil.getStatusDesc("BU_MCHT_DEPOSIT_DTL", "TYPE_SUB", mchtDepositDtlCustom.getTypeSub());
			if(mchtDepositDtlCustom.getTxnType().equals("A")){
				mchtDepositDtlCustom.setBizTypeDesc("追加保证金");
			}else if(mchtDepositDtlCustom.getTxnType().equals("B") || mchtDepositDtlCustom.getTxnType().equals("C")){
				mchtDepositDtlCustom.setBizTypeDesc("缴款");
			}else if(mchtDepositDtlCustom.getTxnType().equals("D")){
				mchtDepositDtlCustom.setBizTypeDesc("退返保证金");
			}else if(mchtDepositDtlCustom.getTxnType().equals("E") || mchtDepositDtlCustom.getTxnType().equals("G")){
				mchtDepositDtlCustom.setBizTypeDesc("扣款");
			}else if(mchtDepositDtlCustom.getTxnType().equals("F")){
				mchtDepositDtlCustom.setBizTypeDesc("扣款退回");
			}
			mchtDepositDtlCustom.setChangeDetail(txnTypeDesc+"("+typeSubDesc+")");
			if(mchtDepositDtlCustom.getBizId()!=null && mchtDepositDtlCustom.getBizType()!=null){
				if(mchtDepositDtlCustom.getBizType().equals("2")){
					ViolateOrder violateOrder = violateOrderService.selectByPrimaryKey(mchtDepositDtlCustom.getBizId());
					String orderCode = violateOrder.getOrderCode();
					int r=mchtDepositDtlCustom.getTxnAmt().compareTo(BigDecimal.ZERO); //和0，Zero比较
					if(r==1 || r==0){//大于
						mchtDepositDtlCustom.setChangeDetail(txnTypeDesc+"("+typeSubDesc+")"+"<br>涉及违规单<a href='javascript:;' onclick='viewViolateOrder("+mchtDepositDtlCustom.getBizId()+")'>"+orderCode+"</a>");
					}else if(r==-1){//小于
						mchtDepositDtlCustom.setChangeDetail(txnTypeDesc+"("+typeSubDesc+")"+"<br>涉及违规单<a href='javascript:;' onclick='viewViolateOrder("+mchtDepositDtlCustom.getBizId()+")'>"+orderCode+"</a>");
					}
				}
			}
			if(mchtDepositDtlCustom.getTxnType().equals("G")){//售后扣款
				mchtDepositDtlCustom.setChangeDetail(mchtDepositDtlCustom.getChangeDetail()+"<br>"+mchtDepositDtlCustom.getRemarks());
			}
		}
		returnData.put("Rows", mchtDepositDtlCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/export")
	public void export(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Integer depositId = Integer.parseInt(request.getParameter("depositId"));
		MchtDepositDtlCustomExample mchtDepositDtlCustomExample=new MchtDepositDtlCustomExample();
		MchtDepositDtlCustomExample.MchtDepositDtlCustomCriteria criteria=mchtDepositDtlCustomExample.createCriteria();
		criteria.andDelFlagEqualTo("0").andDepositIdEqualTo(depositId);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (!StringUtil.isEmpty(request.getParameter("createTimeBegin"))) {
				criteria.andCreateDateGreaterThanOrEqualTo(sf.parse(request.getParameter("createTimeBegin")+" 00:00:00"));
			}
			if (!StringUtil.isEmpty(request.getParameter("createTimeEnd"))) {
				criteria.andCreateDateLessThanOrEqualTo(sf.parse(request.getParameter("createTimeEnd")+" 23:59:59"));
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String bizType = request.getParameter("search_bizType");
		if(!StringUtil.isEmpty(bizType)){
			if(bizType.equals("1")){
				criteria.andBizTypeEqualTo(bizType);
			}else if(bizType.equals("2")){
				criteria.andBizTypeEqualTo(bizType);
				criteria.andTxnAmtLessThan(BigDecimal.ZERO);
			}else if(bizType.equals("3")){
				criteria.andBizTypeEqualTo("2");
				criteria.andTxnAmtGreaterThan(BigDecimal.ZERO);
			}
			
		}
		List<MchtDepositDtlCustom> mchtDepositDtlCustoms = mchtDepositDtlService.selectMchtDepositDtlCustomByExample(mchtDepositDtlCustomExample);
		for(MchtDepositDtlCustom mchtDepositDtlCustom:mchtDepositDtlCustoms){
			String txnTypeDesc = DataDicUtil.getStatusDesc("BU_MCHT_DEPOSIT_DTL", "TXN_TYPE", mchtDepositDtlCustom.getTxnType());
			String typeSubDesc = DataDicUtil.getStatusDesc("BU_MCHT_DEPOSIT_DTL", "TYPE_SUB", mchtDepositDtlCustom.getTypeSub());
			if(mchtDepositDtlCustom.getTxnType().equals("A")){
				mchtDepositDtlCustom.setBizTypeDesc("追加保证金");
			}else if(mchtDepositDtlCustom.getTxnType().equals("B") || mchtDepositDtlCustom.getTxnType().equals("C")){
				mchtDepositDtlCustom.setBizTypeDesc("缴款");
			}else if(mchtDepositDtlCustom.getTxnType().equals("D")){
				mchtDepositDtlCustom.setBizTypeDesc("退返保证金");
			}else if(mchtDepositDtlCustom.getTxnType().equals("E")){
				mchtDepositDtlCustom.setBizTypeDesc("扣款");
			}else if(mchtDepositDtlCustom.getTxnType().equals("F")){
				mchtDepositDtlCustom.setBizTypeDesc("扣款退回");
			}
			mchtDepositDtlCustom.setChangeDetail(txnTypeDesc+"("+typeSubDesc+")");
			if(mchtDepositDtlCustom.getBizId()!=null && mchtDepositDtlCustom.getBizType()!=null){
				if(mchtDepositDtlCustom.getBizType().equals("2")){
					ViolateOrder violateOrder = violateOrderService.selectByPrimaryKey(mchtDepositDtlCustom.getBizId());
					String orderCode = violateOrder.getOrderCode();
					int r=mchtDepositDtlCustom.getTxnAmt().compareTo(BigDecimal.ZERO); //和0，Zero比较
					if(r==1){//大于
						mchtDepositDtlCustom.setChangeDetail(txnTypeDesc+"("+typeSubDesc+")"+"<br>涉及违规单<a href='javascript:;' onclick='viewViolateOrder("+mchtDepositDtlCustom.getBizId()+")'>"+orderCode+"</a>");
					}else if(r==-1){//小于
						mchtDepositDtlCustom.setChangeDetail(txnTypeDesc+"("+typeSubDesc+")"+"<br>涉及违规单<a href='javascript:;' onclick='viewViolateOrder("+mchtDepositDtlCustom.getBizId()+")'>"+orderCode+"</a>");
					}
				}
			}
		}
		String[] titles = {"变更时间", "变更明细", "类型","金额（元）"};
		ExcelBean excelBean = new ExcelBean("导出保证金变更明细" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xls",
				"导出保证金变更明细", titles);
		List<String[]> datas = new ArrayList<>();
		for (MchtDepositDtlCustom mchtDepositDtlCustom : mchtDepositDtlCustoms) {
			String[] data = {
					sf.format(mchtDepositDtlCustom.getCreateDate()),
					mchtDepositDtlCustom.getChangeDetail(),
					mchtDepositDtlCustom.getBizTypeDesc(),
					mchtDepositDtlCustom.getTxnAmt().toString()
				};
			datas.add(data);
		}
		excelBean.setDataList(datas);
		ExcelUtils.export(excelBean,response);
	}
	
	/**
	 * 缴纳保证金页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/addDepositOrder")
	public String toAddDepositOrder(Model model, HttpServletRequest request) {
		Integer depositId = Integer.parseInt(request.getParameter("depositId"));
		MchtDeposit mchtDeposit = mchtDepositService.selectByPrimaryKey(depositId);
		List<PlatformCapitalAccount> aliAccountList = platformCapitalAccountService.getPlatformCapitalAccountListByPaymentType("1");
		List<PlatformCapitalAccount> publicBankAccountList  = platformCapitalAccountService.getPlatformCapitalAccountListByPaymentTypeAndAccountType("3","1");
		List<PlatformCapitalAccount> privateBankAccountList  = platformCapitalAccountService.getPlatformCapitalAccountListByPaymentTypeAndAccountType("3","2");
		model.addAttribute("mchtDeposit", mchtDeposit);
		model.addAttribute("aliAccountList", aliAccountList);
		model.addAttribute("publicBankAccountList", publicBankAccountList);
		model.addAttribute("privateBankAccountList", privateBankAccountList);
		return "mchtDeposit/addDepositOrder";
	}
	
	/**
	 * 保存缴纳保证金
	 * 
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/saveDepositOrder")
	@ResponseBody
	public ResponseMsg saveDepositOrder(HttpServletRequest request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String amount = request.getParameter("amount");
		String paymentType = request.getParameter("paymentType");
		String paymentNo = request.getParameter("paymentNo");
		String accountId = request.getParameter("accountId");
		String paymentName = request.getParameter("paymentName");
		String accountNo = request.getParameter("accountNo");
		String accountName = request.getParameter("accountName");
		String payDate = request.getParameter("payDate");
		DepositOrder depositOrder = new DepositOrder();
		depositOrder.setCreateBy(this.getMchtInfo().getId());
		depositOrder.setCreateDate(new Date());
		depositOrder.setMchtId(this.getMchtInfo().getId());
		depositOrder.setDelFlag("0");
		depositOrder.setAmount(new BigDecimal(amount));
		depositOrder.setPaymentType(paymentType);
		depositOrder.setPaymentNo(paymentNo);
		depositOrder.setStatus("0");
		if(!StringUtil.isEmpty(accountId)){
			depositOrder.setAccountId(Integer.parseInt(accountId));
		}
		depositOrder.setAccountName(accountName);
		depositOrder.setAccountNo(accountNo);
		depositOrder.setPaymentName(paymentName);
		depositOrder.setPayDate(DateUtil.getDateByLongFormat(payDate));
		
		depositOrderService.insertSelective(depositOrder);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	
	/**
	 * 保证金缴款记录
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/depositOrderIndex")
	public String depositOrderIndex(Model model, HttpServletRequest request) {
		model.addAttribute("paymentTypeList", DataDicUtil.getStatusList("BU_DEPOSIT_ORDER", "PAYMENT_TYPE"));
		return "mchtDeposit/depositOrderIndex";
	}
	
	/**
	 * 商家保证金明细列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/getDepositOrderList")
	@ResponseBody
	public ResponseMsg getDepositOrderList(HttpServletRequest request,Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		DepositOrderCustomExample depositOrderCustomExample = new DepositOrderCustomExample();
		depositOrderCustomExample.setOrderByClause("t.id desc");
		DepositOrderCustomExample.Criteria criteria = depositOrderCustomExample.createCriteria();
		criteria.andDelFlagEqualTo("0").andMchtIdEqualTo(this.getMchtInfo().getId());
		if(!StringUtil.isEmpty(request.getParameter("search_paymentType"))){
			criteria.andPaymentTypeEqualTo(request.getParameter("search_paymentType"));
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (!StringUtil.isEmpty(request.getParameter("createTimeBegin"))) {
				criteria.andCreateDateGreaterThanOrEqualTo(sf.parse(request.getParameter("createTimeBegin")+" 00:00:00"));
			}
			if (!StringUtil.isEmpty(request.getParameter("createTimeEnd"))) {
				criteria.andCreateDateLessThanOrEqualTo(sf.parse(request.getParameter("createTimeEnd")+" 23:59:59"));
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int totalCount = depositOrderService.countByExample(depositOrderCustomExample);
		depositOrderCustomExample.setLimitStart(page.getLimitStart());
		depositOrderCustomExample.setLimitSize(page.getLimitSize());
		List<DepositOrderCustom> depositOrderCustoms = depositOrderService.selectDepositOrderCustomByExample(depositOrderCustomExample);
		for(DepositOrderCustom depositOrderCustom:depositOrderCustoms){
			if(depositOrderCustom.getAccountId()!=null){
				depositOrderCustom.setPlatformCapitalAccount(platformCapitalAccountService.selectByPrimaryKey(depositOrderCustom.getAccountId()));
			}
		}
		returnData.put("Rows", depositOrderCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
}
