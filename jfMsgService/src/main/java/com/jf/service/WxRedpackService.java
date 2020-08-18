package com.jf.service;

import java.math.BigDecimal;
import java.util.Date;

import com.jf.common.base.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;
import com.jf.dao.WxRedpackMapper;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountDtl;
import com.jf.entity.WithdrawOrder;
import com.jf.entity.WithdrawOrderStatusLog;
import com.jf.entity.WxRedpack;
import com.jf.entity.WxRedpackExample;
import com.jf.entity.pay.WxRedPacketRecordRes;
import com.jf.entity.pay.WxRedPacketReq;
import com.jf.entity.pay.WxRedPacketRes;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年6月11日 下午5:27:08 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class WxRedpackService extends BaseService<WxRedpack, WxRedpackExample> {
	private static Logger logger = LoggerFactory.getLogger(WxRedpackService.class);
	@Autowired
	private WxRedpackMapper wxRedpackMapper;
	@Autowired
	private WithdrawOrderService withdrawOrderService;
	@Autowired
	private WithdrawOrderStatusLogService withdrawOrderStatusLogService;
	@Autowired
	private MemberAccountService memberAccountService;
	@Autowired
	private MemberAccountDtlService memberAccountDtlService;

	@Autowired
	public void setWxRedpackMapper(WxRedpackMapper wxRedpackMapper) {
		this.setDao(wxRedpackMapper);
		this.wxRedpackMapper = wxRedpackMapper;
	}

	public void updateWithdrawOrderInfo(WxRedPacketRes res, WxRedPacketReq req, WxRedpack wxRedpack, String status) {
		Date date = new Date();
		BigDecimal one = new BigDecimal("-1");
		if(!status.equals("SUCCESS")){
			WithdrawOrder order = withdrawOrderService.selectByPrimaryKey(wxRedpack.getWithdrawOrderId());
			//解冻
			MemberAccount account = memberAccountService.updateMemberAccountFreeze(order.getAccId(),order.getAmount().multiply(one),date);
			if(account == null){
				return;
			}
			BigDecimal balance = account.getBalance();
			BigDecimal freeze = account.getFreeze();
			order.setStatus("6");
			order.setUpdateDate(date);
			withdrawOrderService.updateByPrimaryKeySelective(order);
			WithdrawOrderStatusLog log = new WithdrawOrderStatusLog();
			log.setStatus("6");
			log.setWithdrawOrderId(wxRedpack.getWithdrawOrderId());
			log.setUpdateDate(date);
			withdrawOrderStatusLogService.insertSelective(log);
			
			MemberAccountDtl accountDtl = new MemberAccountDtl();
			accountDtl.setAccId(account.getId());
			accountDtl.setTallyMode(Const.INTEGRAL_TALLY_MODE_INCOME);
			accountDtl.setBalance(balance);
			accountDtl.setTotalFreeze(freeze.subtract(wxRedpack.getAmount()));
			accountDtl.setFreezeAmount(wxRedpack.getAmount());
			accountDtl.setBizType("7");
			accountDtl.setBizId(order.getId());
			accountDtl.setUpdateDate(date);
			accountDtl.setRemarks("发送红包失败,解冻金额");
			memberAccountDtlService.insertSelective(accountDtl);
		}
		if(status.equals("SUCCESS")){
			//0 未发放 1发放中 2已发放待领取 3发放失败 4已领取 (发放成功)5退款中 6已退款
			wxRedpack.setStatus("2");
		}else{
			wxRedpack.setStatus("3");
		}
		wxRedpack.setMchId(req.getMch_id());
		wxRedpack.setWxappid(req.getWxappid());
		wxRedpack.setOpenid(req.getRe_openid());
		wxRedpack.setWishing(req.getWishing());
		wxRedpack.setActName(req.getAct_name());
		wxRedpack.setSceneId(req.getScene_id());
		wxRedpack.setReturnCode(res.getReturn_code());
		wxRedpack.setReturnMsg(res.getReturn_msg());
		wxRedpack.setResultCode(res.getResult_code());
		wxRedpack.setErrCode(res.getErr_code());
		wxRedpack.setErrCodeDes(res.getErr_code_des());
		wxRedpack.setSendListid(res.getSend_listid());
		wxRedpack.setUpdateDate(date);
		updateByPrimaryKeySelective(wxRedpack);
	}

	public void updateWxRedPacket(WxRedPacketRecordRes res, WxRedPacketReq req, WxRedpack wxRedpack, String string) {
		Date date = new Date();
		BigDecimal one = new BigDecimal("-1");
		//SENDING:发放中 1
		//SENT:已发放待领取 2
		//FAILED：发放失败 3
		//RECEIVED:已领取 4
		//RFUND_ING:退款中 5
		//REFUND:已退款 6
		String status = "";
		if(res.getStatus().equals("SENDING")){
			status = "1";
		}else if(res.getStatus().equals("SENT")){
			status = "2";
		}else if(res.getStatus().equals("FAILED")){
			status = "3";
		}else if(res.getStatus().equals("RECEIVED")){
			status = "4";
		}else if(res.getStatus().equals("RFUND_ING")){
			status = "5";
		}else if(res.getStatus().equals("REFUND")){
			status = "6";
		}
		
		if(status.equals("1") || status.equals("2") || status.equals("5")){
			return;
		}else if(status.equals("4")){//已领取,领取成功,扣除冻结金额，扣除余额
			WithdrawOrder order = withdrawOrderService.selectByPrimaryKey(wxRedpack.getWithdrawOrderId());
			MemberAccount account = memberAccountService.updateMemberAccountBalanceAndFreeze(order.getAccId(),wxRedpack.getAmount().multiply(one),date);
			if(account == null){
				return;
			}
			BigDecimal balance = account.getBalance();
			BigDecimal freeze = account.getFreeze();
			
			order.setStatus("4");
			order.setUpdateDate(date);
			withdrawOrderService.updateByPrimaryKeySelective(order);
			WithdrawOrderStatusLog log = new WithdrawOrderStatusLog();
			log.setStatus("4");
			log.setWithdrawOrderId(wxRedpack.getWithdrawOrderId());
			log.setUpdateDate(date);
			withdrawOrderStatusLogService.insertSelective(log);
			
			//解冻明细
			MemberAccountDtl thawAccountDtl = new MemberAccountDtl();
			thawAccountDtl.setAccId(account.getId());
			thawAccountDtl.setTallyMode(Const.INTEGRAL_TALLY_MODE_INCOME);
			thawAccountDtl.setTotalFreeze(freeze.subtract(wxRedpack.getAmount()));
			thawAccountDtl.setFreezeAmount(wxRedpack.getAmount());
			thawAccountDtl.setBalance(balance);
			thawAccountDtl.setBizType("7");
			thawAccountDtl.setBizId(order.getId());
			thawAccountDtl.setUpdateDate(date);
			thawAccountDtl.setRemarks("红包领取成功,解冻金额");
			memberAccountDtlService.insertSelective(thawAccountDtl);
			//提现明细
			MemberAccountDtl withdrawAccountDtl = new MemberAccountDtl();
			withdrawAccountDtl.setAccId(account.getId());
			withdrawAccountDtl.setTallyMode(Const.INTEGRAL_TALLY_MODE_ACCOUNT);
			withdrawAccountDtl.setAmount(wxRedpack.getAmount());
			withdrawAccountDtl.setBalance(balance.subtract(wxRedpack.getAmount()));
			withdrawAccountDtl.setBizType("2");
			withdrawAccountDtl.setBizId(order.getId());
			withdrawAccountDtl.setUpdateDate(date);
			withdrawAccountDtl.setRemarks("红包领取成功,扣除余额");
			memberAccountDtlService.insertSelective(withdrawAccountDtl);
		}else if(status.equals("3") || status.equals("6")){
			WithdrawOrder order = withdrawOrderService.selectByPrimaryKey(wxRedpack.getWithdrawOrderId());
			//解冻
			MemberAccount account = memberAccountService.updateMemberAccountFreeze(order.getAccId(),order.getAmount().multiply(one),date);
			if(account == null){
				return;
			}
			BigDecimal balance = account.getBalance();
			BigDecimal freeze = account.getFreeze();
			order.setStatus("6");
			order.setUpdateDate(date);
			withdrawOrderService.updateByPrimaryKeySelective(order);
			WithdrawOrderStatusLog log = new WithdrawOrderStatusLog();
			log.setStatus("6");
			log.setWithdrawOrderId(wxRedpack.getWithdrawOrderId());
			log.setUpdateDate(date);
			withdrawOrderStatusLogService.insertSelective(log);
			
			MemberAccountDtl accountDtl = new MemberAccountDtl();
			accountDtl.setAccId(account.getId());
			accountDtl.setTallyMode(Const.INTEGRAL_TALLY_MODE_INCOME);
			accountDtl.setBalance(balance);
			accountDtl.setTotalFreeze(freeze.subtract(wxRedpack.getAmount()));
			accountDtl.setFreezeAmount(wxRedpack.getAmount());
			accountDtl.setBizType("7");
			accountDtl.setBizId(order.getId());
			accountDtl.setUpdateDate(date);
			accountDtl.setRemarks("红包领取失败,解冻金额");
			memberAccountDtlService.insertSelective(accountDtl);
		}else{
			return;
		}
		
		wxRedpack.setStatus(status);
		wxRedpack.setReturnCode(res.getReturn_code());
		wxRedpack.setReturnMsg(res.getReturn_msg());
		wxRedpack.setResultCode(res.getResult_code());
		wxRedpack.setErrCode(res.getErr_code());
		wxRedpack.setErrCodeDes(res.getErr_code_des());
		wxRedpack.setUpdateDate(date);
		updateByPrimaryKeySelective(wxRedpack);
	}
}
