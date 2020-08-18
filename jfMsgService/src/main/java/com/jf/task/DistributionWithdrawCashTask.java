package com.jf.task;

import com.bill99.asap.exception.CryptoException;
import com.bill99.asap.service.ICryptoService;
import com.bill99.asap.service.impl.CryptoServiceFactory;
import com.bill99.plfk.api.CustomerTool;
import com.bill99.plfk.entity.DealInfoEntity;
import com.bill99.plfk.entity.OrderInfoEntity;
import com.bill99.schema.asap.commons.Mpf;
import com.bill99.schema.asap.data.SealedData;
import com.bill99.schema.asap.data.UnsealedData;
import com.bill99.schema.fo.settlement.ApplyResponseType;
import com.bill99.schema.fo.settlement.BatchListType;
import com.bill99.schema.fo.settlement.BatchSettlementApplyResponse;
import com.bill99.schema.fo.settlement.BatchidQueryResponse;
import com.bill99.schema.fo.settlement.ComplexQueryResponse;
import com.bill99.schema.fo.settlement.ComplexQueryResponseType;
import com.bill99.schema.fo.settlement.Pay2bankResultType;
import com.bill99.schema.fo.settlement.SettlementPkiApiResponse;
import com.jf.common.ext.RegCondition;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.AreaMapper;
import com.jf.dao.BankBranchMapper;
import com.jf.dao.BankMapper;
import com.jf.entity.Area;
import com.jf.entity.Bank;
import com.jf.entity.BankBranch;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountDtl;
import com.jf.entity.WithdrawOrder;
import com.jf.entity.WithdrawOrderExample;
import com.jf.entity.WithdrawOrderStatusLog;
import com.jf.service.MemberAccountDtlService;
import com.jf.service.MemberAccountService;
import com.jf.service.WithdrawOrderService;
import com.jf.service.WithdrawOrderStatusLogService;
import com.single.payment.dto.Pay2bankOrder;
import com.single.payment.dto.Pay2bankRequest;
import com.single.payment.dto.Pay2bankResponse;
import com.single.payment.dto.Pay2bankSearchDetail;
import com.single.payment.dto.Pay2bankSearchRequest;
import com.single.payment.dto.Pay2bankSearchRequestParam;
import com.single.payment.dto.Pay2bankSearchResponse;
import com.single.payment.dto.Pay2bankSearchResult;
import com.single.payment.util.CCSUtil;
import com.single.payment.util.PKIUtil;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

@RegCondition
@Component
public class DistributionWithdrawCashTask {
    private static Logger logger = LoggerFactory.getLogger(DistributionWithdrawCashTask.class);
    
    @Resource
    private WithdrawOrderService withdrawOrderService;
    
    @Resource
    private WithdrawOrderStatusLogService withdrawOrderStatusLogService;
    
    @Resource
    private BankBranchMapper bankBranchMapper;
    
    @Resource
    private BankMapper bankMapper;
    
    @Resource
    private AreaMapper areaMapper;
    
    @Resource
    private MemberAccountService memberAccountService;
    
    @Resource
    private MemberAccountDtlService memberAccountDtlService;
    //字符编码
  	private static String encoding = "UTF-8";
    /** 版本号 */
    private static String VERSION_BATCH = "1.0.1";
    private static String VERSION_SINGLE = "1.0";
    /** 批量提交类型 */
    private static String BATCH_APPLY = "fo.batch.settlement.pay";
	private static String BATCH_COMPLEXQUERY = "fo.batch.settlement.complexquery";
	private static String ACTION_BATCHIDQUERY = "fo.batch.settlement.batchidquery";
	/** 加密类型 */
	private static String FEATURECODE_BATCH = "F889";
	private static String FEATURECODE_SINGLE = "F41";
	/** 批次号 */
	private String batchNo = "batchNo_JM"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	/** 发起日期 */
	private String applyDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	/** 付款商户名称 */
	private String merchantName = "厦门聚买网络科技有限公司";
	/** 付费方式 */
	private String feeType = "1";
	/** 币种 */
	private String cur = "RMB";
	/** 是否验证金额 */
	private String checkAmtCnt = "0";
	/** 是否整批失败 */
	private String batchFail = "1";
	/** 充值方式 */
	private String rechargeType = "1";
	/** 是否自动退款 */
	private String autoRefund = "0";   
	/** 是否短信通知 0:通知; 1:不通知*/
	private String phoneNoteFlag = "0";
	/** 对公对私0:企业; 1:个人 */ 
	private String payeeType = "0";
	/** 到账时效 */
	private String period = "2";
	/** 收款方付手续费 */
	private String feeAction = "0";
	/** 快钱交易备注 */
//	String memo = "快钱备注";
	/** 银行交易用备注 */
//	String bankPurpose = "银行交易备注";
	/** 银行交易备注 */
//	String bankMemo = "交易备注";
	/** 收款方通知知内容 */
//	String payeeNote = "通知内容";
	/** 收款方手机号 */
//	String payeeMobile = "15860758063";
	/** 收款方邮件 */
//	String payeeEmail = "chenxiaoling@jfbuy.com";
	
	//单笔付款服务端URL
	private static String SINGLE_PAY_URL = "https://www.99bill.com/fo-pay/pay2bank/pay";  //生产地址
	//单笔查询服务端URL
	private static String SINGLE_QUERY_URL = "https://www.99bill.com/fo-pay-query/pay2bank/query";  //生产地址
    /**
     * 审核通过的新星余额提现调用快钱接口进行转账：每天晚上2点自动运行一次
     *
     *
     */
//  @Scheduled(cron="0 0 2 * * ?")
//  @Scheduled(cron="0 0/5 * * * ?")
//  @Scheduled(cron="0 0/15 * * * ?")
    public void toCashByBatch(){
        logger.info("提现类型为新星余额提现且财务审核状态为通过且状态为审核通过的提现单调用快钱接口进行转账:start");
        Date now = new Date();
        WithdrawOrderExample woe = new WithdrawOrderExample();
        woe.createCriteria().andDelFlagEqualTo("0").andWithdrawTypeEqualTo("3").andCwAuditStatusEqualTo("1").andStatusEqualTo("2");
        List<WithdrawOrder> withdrawOrders = withdrawOrderService.selectByExample(woe);
        List<WithdrawOrder> removeList = new ArrayList<WithdrawOrder>();
        List<Integer> removeIdList = new ArrayList<Integer>();
        List<Integer> withdrawOrderIdList = new ArrayList<Integer>();
        for(WithdrawOrder withdrawOrder:withdrawOrders){
        	Pattern pattern = Pattern.compile("[0-9]*"); 
            boolean isNumber = pattern.matcher(withdrawOrder.getAlipayAccount()).matches();
            if(!isNumber){//银行账号有误
            	removeIdList.add(withdrawOrder.getId());
        		removeList.add(withdrawOrder);
            }else{
            	boolean isBankAccount = checkBankCard(withdrawOrder.getAlipayAccount());
            	if(!isBankAccount){//银行账号有误
            		removeIdList.add(withdrawOrder.getId());
            		removeList.add(withdrawOrder);
            	}else{
            		if(withdrawOrder.getAmount() == null || withdrawOrder.getAmount().compareTo(new BigDecimal(0))<=0){
            			withdrawOrder.setDelFlag("1");
            			withdrawOrderService.updateByPrimaryKeySelective(withdrawOrder);
            			continue;
            		}
            		withdrawOrderIdList.add(withdrawOrder.getId());
            	}
            }
        }
        withdrawOrders.removeAll(removeList);
    	withdrawOrderService.batchUpdate(withdrawOrderIdList,"3",withdrawOrders);//3.提现中
    	withdrawOrderService.batchUpdate(removeIdList,"6",removeList);//6.提现失败
    	/** 商户编号 */
		String memberCode = "";
    	InputStream stream = DistributionWithdrawCashTask.class.getResourceAsStream("/config.properties");
		try {
			Properties properties = new Properties();
			properties.load(stream);
			memberCode = properties.getProperty("memberCode");
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	//TODO 批量保存 bu_withdraw_order_status_log
		Map<Integer,WithdrawOrder> map = new HashMap<Integer,WithdrawOrder>();
    	List<OrderInfoEntity> ordersInfo = new ArrayList<OrderInfoEntity>();
    	BigDecimal totalAmt = new BigDecimal(0);
    	if(withdrawOrders!=null && withdrawOrders.size()>0){
    		for(WithdrawOrder withdrawOrder:withdrawOrders){
    			WithdrawOrderExample e = new WithdrawOrderExample();
    			WithdrawOrderExample.Criteria c = e.createCriteria();
    			c.andDelFlagEqualTo("0");
    			c.andMemberIdEqualTo(withdrawOrder.getMemberId());
    			c.andWithdrawTypeEqualTo(withdrawOrder.getWithdrawType());
    			c.andCreateDateBetween(DateUtil.getDateAfter(withdrawOrder.getCreateDate(), -1), withdrawOrder.getCreateDate());
    			List<WithdrawOrder> withdrawOrderList = withdrawOrderService.selectByExample(e);
    			if(withdrawOrderList!=null && withdrawOrderList.size()>1){//24小时内只能提现一次，当该会员24小时内的分润提现单数量大于1单时，该提现单为异常单
    				withdrawOrder.setDelFlag("1");
    				withdrawOrderService.updateByPrimaryKeySelective(withdrawOrder);
    				continue;
    			}
    			
    			MemberAccount memberAccount = memberAccountService.selectByPrimaryKey(withdrawOrder.getAccId());
    			if(memberAccount.getProfitInviteFreeze().compareTo(new BigDecimal(0))<=0){//账户分润邀新冻结金额（新星冻结金额）<=0时，该提现单为异常单
    				withdrawOrder.setDelFlag("1");
    				withdrawOrderService.updateByPrimaryKeySelective(withdrawOrder);
    				continue;
    			}
    			if(memberAccount.getProfitInviteFreeze().compareTo(withdrawOrder.getAmount())<0){//账户分润邀新冻结金额（新星冻结金额）<提现金额时，该提现单为异常单
    				withdrawOrder.setDelFlag("1");
    				withdrawOrderService.updateByPrimaryKeySelective(withdrawOrder);
    				continue;
    			}
    			map.put(withdrawOrder.getId(), withdrawOrder);
    			totalAmt = totalAmt.add(withdrawOrder.getAmount());
    			OrderInfoEntity orderInfo = new OrderInfoEntity();
    			orderInfo.setMerchantId("withdraworderid_" + withdrawOrder.getId());
    			String amountStr = withdrawOrder.getAmount().multiply(new BigDecimal(100)).toString();
    			orderInfo.setAmt(amountStr.substring(0, amountStr.lastIndexOf(".")));
    			BankBranch bankBranch = bankBranchMapper.selectByPrimaryKey(withdrawOrder.getBankBranchId());
    			Bank bank = bankMapper.selectByPrimaryKey(bankBranch.getBankId());
    			Area area = areaMapper.selectByPrimaryKey(bankBranch.getAreaId());
    			Area parentArea = areaMapper.selectByPrimaryKey(area.getParentId());
    			orderInfo.setBank(bank.getName());
    			orderInfo.setName(withdrawOrder.getRealName());
    			orderInfo.setBankCardNo(withdrawOrder.getAlipayAccount());
//				orderInfo.setBranchBank(branchBank);
    			orderInfo.setPayeeType(payeeType);
    			orderInfo.setProvince(parentArea.getAreaName());
    			orderInfo.setCity(area.getAreaName());
    			orderInfo.setPeriod(period);
    			ordersInfo.add(orderInfo);
    		}
    		//TODO 调用快钱接口转账
        	DealInfoEntity dealInfo = new DealInfoEntity();
    		dealInfo.setPayerAcctCode(memberCode + "01");
    		dealInfo.setBatchNo(batchNo);
    		dealInfo.setApplyDate(applyDate);
    		dealInfo.setName(merchantName);

    		dealInfo.setTotalCnt(String.valueOf(withdrawOrders.size()));
    		dealInfo.setFeeType(feeType);
    		dealInfo.setCur(cur);
    		dealInfo.setCheckAmtCnt(checkAmtCnt);
    		dealInfo.setBatchFail(batchFail);
    		dealInfo.setRechargeType(rechargeType);
    		dealInfo.setAutoRefund(autoRefund);
    		dealInfo.setPhoneNoteFlag(phoneNoteFlag);
    		dealInfo.setServiceType(BATCH_APPLY);
    		dealInfo.setVersion(VERSION_BATCH);
    		dealInfo.setFeatureCode(FEATURECODE_BATCH);
    		dealInfo.setMemberCode(memberCode);
    		String totalAmtStr = totalAmt.multiply(new BigDecimal(100)).toString();
    		dealInfo.setTotalAmt(totalAmtStr.substring(0, totalAmtStr.lastIndexOf(".")));
            dealInfo.setOrdersInfo(ordersInfo);
            CustomerTool ct = new CustomerTool();
    		if ("F889".equalsIgnoreCase(dealInfo.getFeatureCode())) {
    			SettlementPkiApiResponse response = ct.apply_ws(dealInfo);
    			BatchSettlementApplyResponse bsar = (BatchSettlementApplyResponse) ct.unseal(response, dealInfo);// 得到解密数据
    			ApplyResponseType applyResponseType = bsar.getResponseBody();
    			if(applyResponseType.getStatus().equals("112")){//申请失败
    				List<Integer> idList = new ArrayList<Integer>();
    				for(WithdrawOrder withdrawOrder:withdrawOrders){
    					idList.add(withdrawOrder.getId());
    				}
    				withdrawOrderService.batchUpdate(idList,"6",withdrawOrders);//6.提现失败
    			}else{
    				List<Pay2bankResultType> pay2bankResultTypeList = applyResponseType.getPay2bankLists();
    				for(Pay2bankResultType pay2bankResultType:pay2bankResultTypeList){
    					if(!pay2bankResultType.getErrorCode().equals("0000")){//提现失败
    						if(pay2bankResultType.getPay2bank()!=null){
    							String[] array = pay2bankResultType.getPay2bank().getMerchantId().split("_");
    							if(array.length>=2){
    								Integer withdrawOrderId = Integer.parseInt(array[1]);
    								WithdrawOrder withdrawOrder = map.get(withdrawOrderId);
    								if(withdrawOrder!=null && !StringUtil.isEmpty(withdrawOrder.getStatus())){//提现失败
    									MemberAccount memberAccount = memberAccountService.selectByPrimaryKey(withdrawOrder.getAccId());
    									cashFail(withdrawOrder, memberAccount);
    								}
    							}
    						}
    					}
    				}
    			}
    			System.out.println("提交的批次号为:"	+ bsar.getResponseBody().getBatchNo());
    			System.out.println("申请成功笔数为:"	+ bsar.getResponseBody().getTotalApplySuccCnt());
    			System.out.println("返回的json："+ JSONObject.fromObject(bsar.getResponseBody()).toString());
    		} else {
    			if (ct.apply_ftp(dealInfo)) {
    				System.out.println("上传成功");
    			}
    		}
    	}
        logger.info("提现类型为新星余额提现且财务审核状态为通过且状态为审核通过的提现单调用快钱接口进行转账:end");
    }

    /**
     * 根据批次号查询：每天晚上2点自动运行一次
     *
     *
     */
//  @Scheduled(cron="0 0 2 * * ?")
    public void toQueryByBatchNo(){
    	String version = "1.0.1";
		String serviceType = "fo.batch.settlement.batchidquery";
		String memberCode = "10210555915";  
		String featureCode = "F889";
		String batchNo ="";//TODO 填上批次号
		String listFlag = "0";// 是否显示详细明细
		String page = "1";
		String pageSize = "10";

		DealInfoEntity dealInfo = new DealInfoEntity();
		dealInfo.setBatchNo(batchNo);
		dealInfo.setListFlag(listFlag);
		dealInfo.setPage(page);
		dealInfo.setPageSize(pageSize);
		dealInfo.setServiceType(serviceType);
		dealInfo.setVersion(version);
		dealInfo.setFeatureCode(featureCode);
		dealInfo.setMemberCode(memberCode);
		CustomerTool ct = new CustomerTool();
		SettlementPkiApiResponse response = ct.apply_ws(dealInfo);
		BatchidQueryResponse bidqr = (BatchidQueryResponse) ct.unseal(response,dealInfo);// 得到解密数据
		BatchListType batchListType = bidqr.getResponseBody();
		List<Pay2bankResultType> pay2bankResultTypeList = batchListType.getBatchList().getPay2bankLists();
		for(Pay2bankResultType pay2bankResultType:pay2bankResultTypeList){
			String[] array = pay2bankResultType.getPay2bank().getMerchantId().split("_");
			Integer withdrawOrderId = Integer.parseInt(array[1]);
			WithdrawOrder withdrawOrder = withdrawOrderService.selectByPrimaryKey(withdrawOrderId);
			if(withdrawOrder!=null && !StringUtil.isEmpty(withdrawOrder.getStatus())){
				if(withdrawOrder.getStatus().equals("3")){//提现中
					updateAmountAndStatus(pay2bankResultType.getStatus(), withdrawOrder);
				}
			}
		}
    }
    
    /**
     * 根据起始时间查询：每天晚上2点自动运行一次
     *
     *
     */
//  @Scheduled(cron="0 0 2 * * ?")
//  @Scheduled(cron="0 0/1 * * * ?")
    public void toBatchQueryByDate(){
		/** 商户编号 */
		String memberCode = "";
    	InputStream stream = DistributionWithdrawCashTask.class.getResourceAsStream("/config.properties");
		try {
			Properties properties = new Properties();
			properties.load(stream);
			memberCode = properties.getProperty("memberCode");
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		String pageSize = "100";
//		String page="1";
		String beginApplyDate = new SimpleDateFormat("yyyyMMddHHmmss").format(DateUtil.getDateAfter(new Date(), -7));
		String endApplyDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		DealInfoEntity dealInfo = new DealInfoEntity();
		dealInfo.setBeginApplyDate(beginApplyDate);
		dealInfo.setEndApplyDate(endApplyDate);
//		dealInfo.setPage(page);
//		dealInfo.setPageSize(pageSize);
		dealInfo.setServiceType(BATCH_COMPLEXQUERY);
		dealInfo.setVersion(VERSION_BATCH);
		dealInfo.setFeatureCode(FEATURECODE_BATCH);
		dealInfo.setMemberCode(memberCode);

		CustomerTool ct = new CustomerTool();
		SettlementPkiApiResponse response = ct.apply_ws(dealInfo);
		ComplexQueryResponse cqr = (ComplexQueryResponse) ct.unseal(response,dealInfo);//得到解密数据
		ComplexQueryResponseType complexQueryResponseType = cqr.getResponseBody();
		List<Pay2bankResultType> pay2bankResultTypeList = complexQueryResponseType.getPay2bankLists();
		for(Pay2bankResultType pay2bankResultType:pay2bankResultTypeList){
			String[] array = pay2bankResultType.getPay2bank().getMerchantId().split("_");
			if(array.length>=2){
				Integer withdrawOrderId = Integer.parseInt(array[1]);
				WithdrawOrder withdrawOrder = withdrawOrderService.selectByPrimaryKey(withdrawOrderId);
				if(withdrawOrder!=null && !StringUtil.isEmpty(withdrawOrder.getStatus())){
					if(withdrawOrder.getStatus().equals("4") || withdrawOrder.getStatus().equals("6")){//4.提现成功 6.提现失败
						continue;
					}else if(withdrawOrder.getStatus().equals("3")){//3.提现中
						updateAmountAndStatus(pay2bankResultType.getStatus(), withdrawOrder);
					}
				}
			}
		}
    }
    
    /**
     * 审核通过的新星余额提现调用快钱接口进行转账：每天晚上2点自动运行一次
     *
     *
     */
//  @Scheduled(cron="0 0 2 * * ?")
    @Scheduled(cron="0 0/5 * * * ?")
    public void toCashBySingle(){
        logger.info("提现类型为新星余额提现且财务审核状态为通过且状态为审核通过的提现单调用快钱接口进行转账:start");
        Date now = new Date();
        WithdrawOrderExample woe = new WithdrawOrderExample();
        woe.createCriteria().andDelFlagEqualTo("0").andWithdrawTypeEqualTo("3").andCwAuditStatusEqualTo("1").andStatusEqualTo("2");
        List<WithdrawOrder> withdrawOrders = withdrawOrderService.selectByExample(woe);
        List<WithdrawOrder> removeList = new ArrayList<WithdrawOrder>();
        List<Integer> removeIdList = new ArrayList<Integer>();
        List<Integer> withdrawOrderIdList = new ArrayList<Integer>();
        for(WithdrawOrder withdrawOrder:withdrawOrders){
        	boolean isBankAccount = checkBankCard(withdrawOrder.getAlipayAccount());
        	if(!isBankAccount){//银行账号有误
        		removeIdList.add(withdrawOrder.getId());
        		removeList.add(withdrawOrder);
        	}else{
        		if(withdrawOrder.getAmount() == null || withdrawOrder.getAmount().compareTo(new BigDecimal(0))<=0){
        			removeIdList.add(withdrawOrder.getId());
            		removeList.add(withdrawOrder);
            		withdrawOrder.setDelFlag("1");
    				withdrawOrderService.updateByPrimaryKeySelective(withdrawOrder);
    			}
        		withdrawOrderIdList.add(withdrawOrder.getId());
        	}
        }
        withdrawOrders.removeAll(removeList);
    	withdrawOrderService.batchUpdate(withdrawOrderIdList,"3",withdrawOrders);//3.提现中
    	withdrawOrderService.batchUpdate(removeIdList,"6",removeList);//6.提现失败
    	/** 商户编号 */
		String memberCode = "";
    	InputStream stream = DistributionWithdrawCashTask.class.getResourceAsStream("/config.properties");
		try {
			Properties properties = new Properties();
			properties.load(stream);
			memberCode = properties.getProperty("memberCode");
			stream.close();
			if(withdrawOrders!=null && withdrawOrders.size()>0){
	    		for(WithdrawOrder withdrawOrder:withdrawOrders){
	    			WithdrawOrderExample e = new WithdrawOrderExample();
	    			WithdrawOrderExample.Criteria c = e.createCriteria();
	    			c.andDelFlagEqualTo("0");
	    			c.andMemberIdEqualTo(withdrawOrder.getMemberId());
	    			c.andWithdrawTypeEqualTo(withdrawOrder.getWithdrawType());
	    			c.andCreateDateBetween(DateUtil.getDateAfter(withdrawOrder.getCreateDate(), -1), withdrawOrder.getCreateDate());
	    			List<WithdrawOrder> withdrawOrderList = withdrawOrderService.selectByExample(e);
	    			if(withdrawOrderList!=null && withdrawOrderList.size()>1){//24小时内只能提现一次，当该会员24小时内的分润提现单数量大于1单时，该提现单为异常单
	    				withdrawOrder.setDelFlag("1");
	    				withdrawOrderService.updateByPrimaryKeySelective(withdrawOrder);
	    				continue;
	    			}
	    			
	    			MemberAccount memberAccount = memberAccountService.selectByPrimaryKey(withdrawOrder.getAccId());
	    			if(memberAccount.getProfitInviteFreeze().compareTo(new BigDecimal(0))<=0){//账户分润邀新冻结金额（新星冻结金额）<=0时，该提现单为异常单
	    				withdrawOrder.setDelFlag("1");
	    				withdrawOrderService.updateByPrimaryKeySelective(withdrawOrder);
	    				continue;
	    			}
	    			if(memberAccount.getProfitInviteFreeze().compareTo(withdrawOrder.getAmount())<0){//账户分润邀新冻结金额（新星冻结金额）<提现金额时，该提现单为异常单
	    				withdrawOrder.setDelFlag("1");
	    				withdrawOrderService.updateByPrimaryKeySelective(withdrawOrder);
	    				continue;
	    			}
	    			Pay2bankOrder order = new Pay2bankOrder();
	    			//商家订单号 必填
	    			order.setOrderId("withdraworderid_" +withdrawOrder.getId());
	    			//金额（分） 必填
	    			String amountStr = withdrawOrder.getAmount().multiply(new BigDecimal(100)).toString();
	    			order.setAmount(amountStr.substring(0, amountStr.lastIndexOf(".")));
	    			//银行名称 必填
	    			if(withdrawOrder.getBankId()!=null){
	    				Bank bank = bankMapper.selectByPrimaryKey(withdrawOrder.getBankId());
	    				order.setBankName(bank.getName());
	    			}else{
	    				if(withdrawOrder.getBankBranchId()!=null){
		    				BankBranch bankBranch = bankBranchMapper.selectByPrimaryKey(withdrawOrder.getBankBranchId());
		    				Bank bank = bankMapper.selectByPrimaryKey(bankBranch.getBankId());
		    				order.setBankName(bank.getName());
		    			}else{
		    				cashFail(withdrawOrder, memberAccount);
		    				continue;
		    			}
	    			}
	    			//收款人姓名  必填
	    			order.setCreditName(withdrawOrder.getRealName());
	    			//银行卡号 必填
	    			order.setBankAcctId(withdrawOrder.getAlipayAccount());
	    			//备注 非必填
	    			if(withdrawOrder.getAmount().compareTo(new BigDecimal(50000))>=0){
	    				order.setRemark("单笔大于五万！");
	    				continue;
	    			}else{
	    				order.setRemark(withdrawOrder.getMemberId()+"单笔付款转账");
	    			}
	    			//手续费作用方：0收款方付费1付款方付费  非必填 默认1
//	    			order.setFeeAction("1");
	    			order.setFeeAction(feeAction);
	    			String pkiMsg = pay_genPKIMsg(order, memberCode);
	    			String sealMsg = pay_invokeCSSCollection(pkiMsg);
	    			String errorCode = pay_unsealMsg(sealMsg,memberCode);
	    			if(!errorCode.equals("0000")){//提现失败
	    				cashFail(withdrawOrder, memberAccount);
	    			}
	    		}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        logger.info("提现类型为新星余额提现且财务审核状态为通过且状态为审核通过的提现单调用快钱接口进行转账:end");
    }

	public void cashFail(WithdrawOrder withdrawOrder,
			MemberAccount memberAccount) {
		withdrawOrder.setStatus("6");//提现失败
		withdrawOrder.setUpdateDate(new Date());
		MemberAccountDtl memberAccountDtl = new MemberAccountDtl();
		memberAccountDtl.setDelFlag("0");
		memberAccountDtl.setAccId(withdrawOrder.getAccId());
		memberAccountDtl.setTallyMode("1");//进账
		memberAccountDtl.setFreezeAmount(withdrawOrder.getAmount());
		memberAccountDtl.setTotalFreeze(memberAccount.getProfitInviteFreeze().subtract(withdrawOrder.getAmount()));
		memberAccountDtl.setBalance(memberAccount.getProfitInviteBalance());
		memberAccountDtl.setBizType("11");//11  拉新分润提现解冻（新星余额提现解冻）
		memberAccountDtl.setBizId(withdrawOrder.getId());
		memberAccountDtl.setCreateDate(new Date());
		memberAccount.setProfitInviteFreeze(memberAccount.getProfitInviteFreeze().subtract(withdrawOrder.getAmount()));
		
		WithdrawOrderStatusLog log = new WithdrawOrderStatusLog();
		log.setDelFlag("0");
		log.setCreateDate(new Date());
		log.setWithdrawOrderId(withdrawOrder.getId());
		log.setStatus(withdrawOrder.getStatus());
		memberAccountDtlService.save(withdrawOrder,memberAccountDtl,null,memberAccount,log);
	}
    
    /**
     * 根据起始时间查询：每天晚上2点自动运行一次
     *
     *
     */
//  @Scheduled(cron="0 0 2 * * ?")
    @Scheduled(cron="0 0/5 * * * ?")
    public void toSingleQueryByDate(){
    	Pay2bankSearchRequestParam order = new Pay2bankSearchRequestParam();
    	//页码 必填 正整数
		order.setTargetPage("1");
		//每页条数  必填  1-20  正整数
		order.setPageSize("20");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startDate = sdf.format(DateUtil.getDateAfter(new Date(), -5));
		String endDate = sdf.format(new Date());
		//开始时间 必填
		order.setStartDate(startDate); //2017-11-19 08:12:12
		//结束时间 必填  结束-开始<=7天
		order.setEndDate(endDate); //2017-11-21 23:59:59
		try {
			/** 商户编号 */
			String memberCode = "";
			InputStream stream = DistributionWithdrawCashTask.class.getResourceAsStream("/config.properties");
			Properties properties = new Properties();
			properties.load(stream);
			memberCode = properties.getProperty("memberCode");
			stream.close();
			
			String pkiMsg = genPKIMsg(order,memberCode);
			String sealMsg = invokeCSSCollection(pkiMsg);
			//返回的加密报文解密
			String totalCnt = unsealMsg(sealMsg,memberCode);
			int totalPage = Integer.parseInt(totalCnt)/20;
			if(Integer.parseInt(totalCnt)%20>0 && totalPage>0){
				totalPage++;
			}
			for(int i=2;i<=totalPage;i++){
				order.setTargetPage(String.valueOf(i));
				pkiMsg = genPKIMsg(order,memberCode);
				sealMsg = invokeCSSCollection(pkiMsg);
				//返回的加密报文解密
				unsealMsg(sealMsg,memberCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void updateAmountAndStatus(String status,WithdrawOrder withdrawOrder) {
		if(withdrawOrder!=null){
			MemberAccount memberAccount = memberAccountService.selectByPrimaryKey(withdrawOrder.getAccId());
			MemberAccountDtl memberAccountDtl = new MemberAccountDtl();
			MemberAccountDtl mad = new MemberAccountDtl();
			if(status.equals("101")){//进行中
				
			}else if(status.equals("111")){//出款成功
				withdrawOrder.setStatus("4");//提现成功
				withdrawOrder.setUpdateDate(new Date());
				
				memberAccountDtl.setDelFlag("0");
				memberAccountDtl.setAccId(withdrawOrder.getAccId());
				memberAccountDtl.setTallyMode("1");//进账
				memberAccountDtl.setFreezeAmount(withdrawOrder.getAmount());
				memberAccountDtl.setTotalFreeze(memberAccount.getProfitInviteFreeze().subtract(withdrawOrder.getAmount()));
				memberAccountDtl.setBizType("11");//11  拉新分润提现解冻（新星余额提现解冻）
				memberAccountDtl.setBizId(withdrawOrder.getId());
				memberAccountDtl.setCreateDate(new Date());
				memberAccount.setProfitInviteFreeze(memberAccount.getProfitInviteFreeze().subtract(withdrawOrder.getAmount()));
				
				mad.setDelFlag("0");
				mad.setAccId(withdrawOrder.getAccId());
				mad.setTallyMode("2");//出账
				mad.setAmount(withdrawOrder.getAmount());
				mad.setBalance(memberAccount.getProfitInviteBalance().subtract(withdrawOrder.getAmount()));
				mad.setBizType("12");//12 拉新分润提现（新星余额提现）
				mad.setBizId(withdrawOrder.getId());
				mad.setCreateDate(new Date());
				memberAccount.setProfitInviteBalance(memberAccount.getProfitInviteBalance().subtract(withdrawOrder.getAmount()));
				
				WithdrawOrderStatusLog log = new WithdrawOrderStatusLog();
				log.setDelFlag("0");
				log.setCreateDate(new Date());
				log.setWithdrawOrderId(withdrawOrder.getId());
				log.setStatus(withdrawOrder.getStatus());
				memberAccountDtlService.save(withdrawOrder,memberAccountDtl,mad,memberAccount,log);
				
			}else if(status.equals("112") || status.equals("114")){//112.出款失败,114.已经退款
				cashFail(withdrawOrder, memberAccount);
			}
		}
	}
    
    /**
     * 校验银行卡卡号(比较算出的校验位和卡号里的校验位)
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        return cardId.charAt(cardId.length() - 1) == bit;        
    }
    
    /**
     * 用不含校验位的银行卡卡号，采用 Luhm 校验算法获得校验位(卡号最后一位为校验位)
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            throw new IllegalArgumentException("Bank card code must be number!");
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;            
        }
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
    }
    
    public String unsealMsg(String msg,String membercode) throws Exception {
		System.out.println("加密返回报文 = " + msg);
		Pay2bankSearchResponse response = CCSUtil.converyToJavaBean(msg, Pay2bankSearchResponse.class);
		SealedData sealedData = new SealedData();
		sealedData.setOriginalData(response.getSearchResponseBody().getSealDataType().getOriginalData()==null?null:PKIUtil.utf8String2ByteWithBase64(response.getSearchResponseBody().getSealDataType().getOriginalData()));
		sealedData.setSignedData(response.getSearchResponseBody().getSealDataType().getSignedData()==null?null:PKIUtil.utf8String2ByteWithBase64(response.getSearchResponseBody().getSealDataType().getSignedData()));
		sealedData.setEncryptedData(response.getSearchResponseBody().getSealDataType().getEncryptedData()==null?null:PKIUtil.utf8String2ByteWithBase64(response.getSearchResponseBody().getSealDataType().getEncryptedData()));
		sealedData.setDigitalEnvelope(response.getSearchResponseBody().getSealDataType().getDigitalEnvelope()==null?null:PKIUtil.utf8String2ByteWithBase64(response.getSearchResponseBody().getSealDataType().getDigitalEnvelope()));
		Mpf mpf = genMpf(FEATURECODE_SINGLE , membercode);
		UnsealedData unsealedData = null; 
		try {
			ICryptoService service = CryptoServiceFactory.createCryptoService();
			unsealedData = service.unseal(mpf, sealedData);
		} catch (CryptoException e) {
			System.out.println(e);
		}
		byte[] decryptedData = unsealedData.getDecryptedData();
		String rtnString = null;
		if (null != decryptedData) {
			rtnString = PKIUtil.byte2UTF8String(decryptedData);
			System.out.println("解密后返回报文 = " + rtnString);
		} else {
			rtnString = PKIUtil.byte2UTF8String(sealedData.getOriginalData());
			System.out.println("解密后返回报文 = " + rtnString);
		}
		Pay2bankSearchResult result = CCSUtil.converyToJavaBean(rtnString, Pay2bankSearchResult.class);
		System.out.println("返回的json："+ JSONObject.fromObject(result).toString());
		List<Pay2bankSearchDetail> pay2bankSearchDetailList = result.getResultList();
		if(pay2bankSearchDetailList!=null && pay2bankSearchDetailList.size()>0){
			for(Pay2bankSearchDetail pay2bankSearchDetail:pay2bankSearchDetailList){
				String[] array = pay2bankSearchDetail.getOrderId().split("_");
				if(array.length>=2){
					Integer withdrawOrderId = Integer.parseInt(array[1]);
					WithdrawOrder withdrawOrder = withdrawOrderService.selectByPrimaryKey(withdrawOrderId);
					if(withdrawOrder!=null && !StringUtil.isEmpty(withdrawOrder.getStatus()) && withdrawOrder.getStatus().equals("3")){//提现中
						updateAmountAndStatus(pay2bankSearchDetail.getStatus(), withdrawOrder);//更新为提现成功4或者提现失败6
					}
				}
			}
		}
		String totalCnt = result.getPay2bankSearchResponseParam().getTotalCnt();
		if(StringUtil.isEmpty(totalCnt)){
			totalCnt = "0";
		}
		return totalCnt;
	}
	
	public static String genPKIMsg(Pay2bankSearchRequestParam orderDto,String membercode) {
		String orderXml = CCSUtil.convertToXml(orderDto, encoding);
		System.out.println("请求明文报文 = " + orderXml);
		//获取原始报文
		String originalStr = orderXml;
		//加签、加密
		Mpf mpf = genMpf(FEATURECODE_SINGLE , membercode);
		SealedData sealedData = null;
		try {
			ICryptoService service = CryptoServiceFactory.createCryptoService();
			sealedData = service.seal(mpf, originalStr.getBytes());
		} catch (CryptoException e) {
			System.out.println(e);
		}
		Pay2bankSearchRequest request = CCSUtil.genSearchRequest(membercode , VERSION_SINGLE);
		byte[] nullbyte = {};
		byte[] byteOri = sealedData.getOriginalData() == null ? nullbyte : sealedData.getOriginalData();
		byte[] byteEnc = sealedData.getEncryptedData() == null ? nullbyte : sealedData.getEncryptedData();
		byte[] byteEnv = sealedData.getDigitalEnvelope() == null ? nullbyte : sealedData.getDigitalEnvelope();
		byte[] byteSig = sealedData.getSignedData() == null ? nullbyte : sealedData.getSignedData();
		request.getSearchRequestBody().getSealDataType().setOriginalData(PKIUtil.byte2UTF8StringWithBase64(byteOri));
		//获取加签报文
		request.getSearchRequestBody().getSealDataType().setSignedData(PKIUtil.byte2UTF8StringWithBase64(byteSig));
//		//获取加密报文
		request.getSearchRequestBody().getSealDataType().setEncryptedData(PKIUtil.byte2UTF8StringWithBase64(byteEnc));
//		//数字信封
		request.getSearchRequestBody().getSealDataType().setDigitalEnvelope(PKIUtil.byte2UTF8StringWithBase64(byteEnv));
		
		//请求报文
		String requestXml = CCSUtil.convertToXml(request, encoding);
		System.out.println("请求加密报文 = " + requestXml);
		return requestXml;
	}
	
	
	public static String invokeCSSCollection(String requestXml) throws Exception {
		//初始化HttpClient
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(SINGLE_QUERY_URL);
		SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
	        sslContext.init(null, null, null);
	        SSLContext.setDefault(sslContext);
		//请求服务端
//		InputStream in_withcode = new ByteArrayInputStream(requestXml.getBytes(encoding));
//		method.setRequestBody(in_withcode);
		// url的连接等待超时时间设置  
        client.getHttpConnectionManager().getParams().setConnectionTimeout(2000);  

        // 读取数据超时时间设置  
        client.getHttpConnectionManager().getParams().setSoTimeout(3000);  
		method.setRequestEntity(new StringRequestEntity(requestXml, "text/html", "utf-8"));
		client.executeMethod(method);
		
		//打印服务器返回的状态
		System.out.println(method.getStatusLine());
		
		//打印返回的信息
		InputStream stream = method.getResponseBodyAsStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(stream,encoding));
		StringBuffer buf = new StringBuffer();
		String line;
		while (null != (line = br.readLine())) {
			buf.append(line).append("\n");
		}
		//释放连接
		method.releaseConnection();
		return buf.toString();
	}
	
	public static String pay_unsealMsg(String msg,String membercode) throws Exception {
		System.out.println("加密返回报文 = " + msg);
		Pay2bankResponse response = CCSUtil.converyToJavaBean(msg, Pay2bankResponse.class);
		SealedData sealedData = new SealedData();
		sealedData.setOriginalData(response.getResponseBody().getSealDataType().getOriginalData()==null?null:PKIUtil.utf8String2ByteWithBase64(response.getResponseBody().getSealDataType().getOriginalData()));
		sealedData.setSignedData(response.getResponseBody().getSealDataType().getSignedData()==null?null:PKIUtil.utf8String2ByteWithBase64(response.getResponseBody().getSealDataType().getSignedData()));
		sealedData.setEncryptedData(response.getResponseBody().getSealDataType().getEncryptedData()==null?null:PKIUtil.utf8String2ByteWithBase64(response.getResponseBody().getSealDataType().getEncryptedData()));
		sealedData.setDigitalEnvelope(response.getResponseBody().getSealDataType().getDigitalEnvelope()==null?null:PKIUtil.utf8String2ByteWithBase64(response.getResponseBody().getSealDataType().getDigitalEnvelope()));
		Mpf mpf = genMpf(FEATURECODE_SINGLE, membercode);
		UnsealedData unsealedData = null; 
		try {
			ICryptoService service = CryptoServiceFactory.createCryptoService();
			unsealedData = service.unseal(mpf, sealedData);
		} catch (CryptoException e) {
			System.out.println(e);
		}
		byte[] decryptedData = unsealedData.getDecryptedData();
		if (null != decryptedData) {
			String rtnString = PKIUtil.byte2UTF8String(decryptedData);
			System.out.println("解密后返回报文 = " + rtnString);
		} else {
			String  rtnString = PKIUtil.byte2UTF8String(sealedData.getOriginalData());
			System.out.println("解密后返回报文 = " + rtnString);
		}
		return response.getResponseBody().getErrorCode();
	}
	
	public static String pay_genPKIMsg(Pay2bankOrder orderDto,String membercode) {
		String orderXml = CCSUtil.convertToXml(orderDto, encoding);
		System.out.println("请求明文报文 = " + orderXml);
		//获取原始报文
		String originalStr = orderXml;
		//加签、加密
		Mpf mpf = genMpf(FEATURECODE_SINGLE , membercode);
		SealedData sealedData = null;
		try {
			ICryptoService service = CryptoServiceFactory.createCryptoService();
			sealedData = service.seal(mpf, originalStr.getBytes());
		} catch (CryptoException e) {
			System.out.println(e);
		}
		Pay2bankRequest request = CCSUtil.genRequest(membercode , VERSION_SINGLE);
		byte[] nullbyte = {};
		byte[] byteOri = sealedData.getOriginalData() == null ? nullbyte : sealedData.getOriginalData();
		byte[] byteEnc = sealedData.getEncryptedData() == null ? nullbyte : sealedData.getEncryptedData();
		byte[] byteEnv = sealedData.getDigitalEnvelope() == null ? nullbyte : sealedData.getDigitalEnvelope();
		byte[] byteSig = sealedData.getSignedData() == null ? nullbyte : sealedData.getSignedData();
		request.getRequestBody().getSealDataType().setOriginalData(PKIUtil.byte2UTF8StringWithBase64(byteOri));
		//获取加签报文
		request.getRequestBody().getSealDataType().setSignedData(PKIUtil.byte2UTF8StringWithBase64(byteSig));
//		//获取加密报文
		request.getRequestBody().getSealDataType().setEncryptedData(PKIUtil.byte2UTF8StringWithBase64(byteEnc));
//		//数字信封
		request.getRequestBody().getSealDataType().setDigitalEnvelope(PKIUtil.byte2UTF8StringWithBase64(byteEnv));
		
		//请求报文
		String requestXml = CCSUtil.convertToXml(request, encoding);
		System.out.println("请求加密报文 = " + requestXml);
		return requestXml;
	}
	
	
	public static String pay_invokeCSSCollection(String requestXml) throws Exception {
		//初始化HttpClient
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(SINGLE_PAY_URL);
		SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
	        sslContext.init(null, null, null);
	        SSLContext.setDefault(sslContext);
		//请求服务端
//		InputStream in_withcode = new ByteArrayInputStream(requestXml.getBytes(encoding));
//		method.setRequestBody(in_withcode);
		// url的连接等待超时时间设置  
        client.getHttpConnectionManager().getParams().setConnectionTimeout(2000);  

        // 读取数据超时时间设置  
        client.getHttpConnectionManager().getParams().setSoTimeout(3000);  
		method.setRequestEntity(new StringRequestEntity(requestXml, "text/html", "utf-8"));
		client.executeMethod(method);
		
		//打印服务器返回的状态
		System.out.println(method.getStatusLine());
		
		//打印返回的信息
		InputStream stream = method.getResponseBodyAsStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(stream,encoding));
		StringBuffer buf = new StringBuffer();
		String line;
		while (null != (line = br.readLine())) {
			buf.append(line).append("\n");
		}
		//释放连接
		method.releaseConnection();
		return buf.toString();
	}
	
	public static Mpf genMpf(String fetureCode, String membercode) {
		Mpf mpf = new Mpf();
		mpf.setFeatureCode(fetureCode);
		mpf.setMemberCode(membercode);
		return mpf;
	}
}
