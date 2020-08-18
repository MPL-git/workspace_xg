package com.bill99.plfk.test;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.bill99.plfk.api.CustomerTool;
import com.bill99.plfk.entity.DealInfoEntity;
import com.bill99.plfk.entity.OrderInfoEntity;
import com.bill99.schema.fo.settlement.BatchSettlementApplyResponse;
import com.bill99.schema.fo.settlement.BatchidQueryResponse;
import com.bill99.schema.fo.settlement.ComplexQueryResponse;
import com.bill99.schema.fo.settlement.SettlementPkiApiResponse;

public class TestApi {
	public static String pay(String batchId) {
		/** 版本号 */
		String version = "1.0.1";
		/** 提交类型 */
		String serviceType = "fo.batch.settlement.pay";
		/** 商户编号 */
		String memberCode = "10210555915";//沙箱：10012138842
		/** 加密类型 */
		String featureCode = "F889";
		/** 付款方帐号 */
		String payerAcctCode = memberCode + "01";
		/** 批次号 */
		String batchNo =batchId; //"batchNo_"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		/** 发起日期 */
		String applyDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		/** 付款商户名称 */
		String merchantName = "厦门聚买网络科技有限公司";//测试商户
		/** 总金额 */
		String totalAmt = "2000";
		/** 总笔额 */
		String totalCnt = "2";
		/** 付费方式 */
		String feeType = "1";
		/** 币种 */
		String cur = "RMB";
		/** 是否验证金额 */
		String checkAmtCnt = "0";
		/** 是否整批失败 */
		String batchFail = "1";
		/** 充值方式 */
		String rechargeType = "1";
		/** 是否自动退款 */
		String autoRefund = "0";   
		/** 是否短信通知 0:通知; 1:不通知*/
		String phoneNoteFlag = "0";
		/** 预留字段1 */
		String merchantMemo1 = "memo1";
		/** 预留字段2 */
		String merchantMemo2 = "memo2";
		/** 预留字段3 */
		String merchantMemo3 = "memo3";
		
		/** 商家订单号 */
		String merchantId = "orderid_JM"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		/** 金额 */
		String amt = "1000";
		/** 银行名称 */
		String bank = "交通银行";
		/** 户名 */
		String name = "陈小灵";
		/** 卡号 */
		String bankCardNo = "6222623550000451638";
		/** 开户行 */
		String branchBank = "泉州支行";
		/** 对公对私0:企业; 1:个人 */ 
		String payeeType = "0";
		/** 省份 */
		String province = "福建省";
		/** 城市 */
		String city = "泉州市";
		/** 快钱交易备注 */
		String memo = "快钱备注";
		/** 银行交易用备注 */
		String bankPurpose = "银行交易备注";
		/** 银行交易备注 */
		String bankMemo = "交易备注";
		/** 收款方通知知内容 */
		String payeeNote = "通知内容";
		/** 收款方手机号 */
		String payeeMobile = "15860758063";
		/** 收款方邮件 */
		String payeeEmail = "chenxiaoling@jfbuy.com";	
		/** 到账时效 */
		String period = "";
		/** 商户预留字段1 */
		String orderMemo1 = "order1";
		/** 商户预留字段2 */
		String orderMemo2 = "order2";
		/** 商户预留字段3 */
		String orderMemo3 = "order3";

		List<OrderInfoEntity> ordersInfo = new ArrayList<OrderInfoEntity>();
		for (int i = 0; i < 1; i++) {
			OrderInfoEntity orderInfo = new OrderInfoEntity();
			orderInfo.setMerchantId(merchantId + i);
			orderInfo.setAmt(amt);
			orderInfo.setBank(bank);
			orderInfo.setName(name);
			orderInfo.setBankCardNo(bankCardNo);
			orderInfo.setBranchBank(branchBank);
			orderInfo.setPayeeType(payeeType);
			orderInfo.setProvince(province);
			orderInfo.setCity(city);
			orderInfo.setMemo(memo);
			orderInfo.setBankPurpose(bankPurpose);
			orderInfo.setBankMemo(bankMemo);
			orderInfo.setPayeeNote(payeeNote);
			orderInfo.setPayeeMobile(payeeMobile);
			orderInfo.setPayeeEmail(payeeEmail);
			orderInfo.setPeriod(period);
			orderInfo.setMerchantMemo1(orderMemo1);
			orderInfo.setMerchantMemo2(orderMemo2);
			orderInfo.setMerchantMemo3(orderMemo3);
			ordersInfo.add(orderInfo);
		}
		
		OrderInfoEntity orderInfo = new OrderInfoEntity();
		orderInfo.setMerchantId(merchantId + 1);
		orderInfo.setAmt(amt);
		orderInfo.setBank("兴业银行");
		orderInfo.setName("徐花蓉");
		orderInfo.setBankCardNo("622908126910808713");
//		orderInfo.setBranchBank("");
		orderInfo.setPayeeType(payeeType);
		orderInfo.setProvince(province);
		orderInfo.setCity("厦门市");
		orderInfo.setMemo(memo);
		orderInfo.setBankPurpose(bankPurpose);
		orderInfo.setBankMemo(bankMemo);
		orderInfo.setPayeeNote(payeeNote);
		orderInfo.setPayeeMobile("15280064110");
		orderInfo.setPayeeEmail("1160646314@qq.com");
		orderInfo.setPeriod(period);
		orderInfo.setMerchantMemo1(orderMemo1);
		orderInfo.setMerchantMemo2(orderMemo2);
		orderInfo.setMerchantMemo3(orderMemo3);
		ordersInfo.add(orderInfo);
		
		DealInfoEntity dealInfo = new DealInfoEntity();
		dealInfo.setPayerAcctCode(payerAcctCode);
		dealInfo.setBatchNo(batchNo);
		dealInfo.setApplyDate(applyDate);
		dealInfo.setName(merchantName);
		dealInfo.setTotalAmt(totalAmt);
		dealInfo.setTotalCnt(totalCnt);
		dealInfo.setFeeType(feeType);
		dealInfo.setCur(cur);
		dealInfo.setCheckAmtCnt(checkAmtCnt);
		dealInfo.setBatchFail(batchFail);
		dealInfo.setRechargeType(rechargeType);
		dealInfo.setAutoRefund(autoRefund);
		dealInfo.setPhoneNoteFlag(phoneNoteFlag);
		dealInfo.setMerchantMemo1(merchantMemo1);
		dealInfo.setMerchantMemo2(merchantMemo2);
		dealInfo.setMerchantMemo3(merchantMemo3);
		dealInfo.setOrdersInfo(ordersInfo);

		dealInfo.setServiceType(serviceType);
		dealInfo.setVersion(version);
		dealInfo.setFeatureCode(featureCode);
		dealInfo.setMemberCode(memberCode);

		CustomerTool ct = new CustomerTool();
		String result="提交失败";
		if ("F889".equalsIgnoreCase(dealInfo.getFeatureCode())) {
			SettlementPkiApiResponse response = ct.apply_ws(dealInfo);
			BatchSettlementApplyResponse bsar = (BatchSettlementApplyResponse) ct.unseal(response, dealInfo);// 得到解密数据
			System.out.println("提交的批次号为:"
					+ bsar.getResponseBody().getBatchNo());
			System.out.println("申请成功笔数为:"
					+ bsar.getResponseBody().getTotalApplySuccCnt());
			System.out.println("返回的json："+ JSONObject.fromObject(bsar.getResponseBody()).toString());
			result="提交成功";
		} else {
			if (ct.apply_ftp(dealInfo)) {
				System.out.println("上传成功");
				result="上传成功";
			}
		}
		System.out.println("执行完毕");
		return result;
	}

	public static String query1(String batchId) {
		String version = "1.0.1";
		String serviceType = "fo.batch.settlement.batchidquery";
//		String memberCode = "10012138842";//沙箱  
		String memberCode = "10210555915";  
		String featureCode = "F889";
		String batchNo =batchId;
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
		System.out.println("查询到的付款成功笔数为：" + bidqr.getResponseBody().getBatchList().getTotalApplySuccCnt());
		System.out.println(JSONObject.fromObject(bidqr.getResponseBody()).toString());
		return "查询到付款成功笔数为：" + bidqr.getResponseBody().getBatchList().getTotalApplySuccCnt();
	}

	public static void query2() {
		String version = "1.0.1";
		String serviceType = "fo.batch.settlement.complexquery";
//		String memberCode = "10012138842";
		String memberCode = "10210555915";
		String featureCode = "F889";

//		String beginApplyDate = "20190212000000";
//		String endApplyDate = "20190213235959";
		String beginApplyDate = "20190605000000";
		String endApplyDate = "20190611235959";
		String pageSize = "100";
		String page="1";

		DealInfoEntity dealInfo = new DealInfoEntity();
		dealInfo.setBeginApplyDate(beginApplyDate);
		dealInfo.setPage(page);
		dealInfo.setEndApplyDate(endApplyDate);
		dealInfo.setPageSize(pageSize);
		dealInfo.setServiceType(serviceType);
		dealInfo.setVersion(version);
		dealInfo.setFeatureCode(featureCode);
		dealInfo.setMemberCode(memberCode);

		CustomerTool ct = new CustomerTool();
		SettlementPkiApiResponse response = ct.apply_ws(dealInfo);
		ComplexQueryResponse cqr = (ComplexQueryResponse) ct.unseal(response,dealInfo);//得到解密数据
		System.out.println("查询到的笔数为：" + cqr.getResponseBody().getTotalCnt());
		System.out.println(JSONObject.fromObject(cqr.getResponseBody()).toString());
	}

	//org.springframework.orm.hibernate3.HibernateSystemException: a different object	with the same identifier value was already associated with the session
	
	public static void main(String args[]) {
		String batchNo = "batchNo_JM"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		System.out.println(batchNo);
		pay(batchNo);
		//	query1("batchNo_20190213162456"); //
		
	//		query2();
	}
}
