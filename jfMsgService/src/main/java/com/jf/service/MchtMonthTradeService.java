package com.jf.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.StringUtil;
import com.jf.dao.MchtMonthTradeCustomMapper;
import com.jf.dao.MchtMonthTradeMapper;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtMonthTrade;
import com.jf.entity.MchtMonthTradeExample;

@Service
@Transactional
public class MchtMonthTradeService extends BaseService<MchtMonthTrade, MchtMonthTradeExample> {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(MchtMonthTradeService.class);
	
	@Autowired
	private MchtMonthTradeMapper mchtMonthTradeMapper;
	
	@Autowired
	private MchtMonthTradeCustomMapper mchtMonthTradeCustomMapper;
	
	@Autowired
	public void setMchtMonthTradeMapper(MchtMonthTradeMapper mchtMonthTradeMapper) {
		super.setDao(mchtMonthTradeMapper);
		this.mchtMonthTradeMapper = mchtMonthTradeMapper;
	}
	
	/**
	 * 
	 * @Title generateMchtMonthTrade   
	 * @Description TODO(生成每月商家月往来单)   
	 * @author pengl
	 * @date 2018年4月16日 下午5:37:45
	 */
	public void generateMchtMonthTrade(String tradeMonth, String startDate, String endDate) {
		if(StringUtil.isEmpty(tradeMonth)) {
			return;
		}
		List<MchtInfo> mchtInfoList = mchtMonthTradeCustomMapper.selectNoMchtMonthTradeMchtList(tradeMonth);
		if(mchtInfoList == null || mchtInfoList.size() == 0) {
			return;
		}
		Map<Integer, MchtMonthTrade> mchtMonthTradeMap = new HashMap<Integer, MchtMonthTrade>();
		Date date = new Date();
		for(MchtInfo mchtInfo : mchtInfoList) {
			MchtMonthTrade mchtMonthTrade = new MchtMonthTrade();
			mchtMonthTrade.setTradeMonth(tradeMonth);
			mchtMonthTrade.setMchtId(mchtInfo.getId());
			mchtMonthTrade.setBeginUnpay(new BigDecimal(0));
			mchtMonthTrade.setCurrentMonthSettleAmount(new BigDecimal(0));
			mchtMonthTrade.setCurrentDepositAmount(new BigDecimal(0));
			mchtMonthTrade.setCurrentPayAmount(new BigDecimal(0));
			mchtMonthTrade.setViolateNeedDeduct(new BigDecimal(0));
			mchtMonthTrade.setDepositDtl(new BigDecimal(0));
			mchtMonthTrade.setDiscount(new BigDecimal(0));
			mchtMonthTrade.setEndUnpay(new BigDecimal(0));
			mchtMonthTrade.setTotalOrderPayAmount(new BigDecimal(0));
			mchtMonthTrade.setCollectDepositAmount(new BigDecimal(0));
			mchtMonthTrade.setCurrentChangeAmount(new BigDecimal(0));
			mchtMonthTrade.setDepositBalance(new BigDecimal(0));
			mchtMonthTrade.setCreateDate(date);
			mchtMonthTrade.setDelFlag("0");
			mchtMonthTradeMap.put(mchtInfo.getId(), mchtMonthTrade);
		}
		
		//本期应结货款
			//全部子订单明细
		Map<String, Object> odMap = new HashMap<String, Object>();
		odMap.put("tradeMonth", tradeMonth);
		odMap.put("startDate", startDate);
		odMap.put("endDate", endDate);
		List<Map<String, Object>> orderDtlMapList = mchtMonthTradeCustomMapper.selectNoMchtMonthTradeOrderDtlList(odMap);
		for(Map<String, Object> orderDtlMap : orderDtlMapList) {
			MchtMonthTrade mchtMonthTrade = mchtMonthTradeMap.get(orderDtlMap.get("mcht_id"));
			if(mchtMonthTrade != null) {
				//本期应结货款
				mchtMonthTrade.setCurrentMonthSettleAmount(orderDtlMap.get("sum_settle_amount")==null?new BigDecimal(0):((BigDecimal)orderDtlMap.get("sum_settle_amount")));
				//本期订单实收金额
				mchtMonthTrade.setTotalOrderPayAmount(orderDtlMap.get("sum_pay_amount")==null?new BigDecimal(0):((BigDecimal)orderDtlMap.get("sum_pay_amount")));
			}
		}
			//子订单明细(退款、退货)
		Map<String, Object> psMap = new HashMap<String, Object>();
		psMap.put("tradeMonth", tradeMonth);
		psMap.put("productStatus", "'2','3'");
		psMap.put("startDate", startDate);
		psMap.put("endDate", endDate);
		List<Map<String, Object>> productStatusMapList = mchtMonthTradeCustomMapper.selectNoMchtMonthTradeOrderDtlList(psMap);
		for(Map<String, Object> productStatusMap : productStatusMapList) {
			MchtMonthTrade mchtMonthTrade = mchtMonthTradeMap.get(productStatusMap.get("mcht_id"));
			if(mchtMonthTrade != null) {
				//本期应结货款
				mchtMonthTrade.setCurrentMonthSettleAmount(mchtMonthTrade.getCurrentMonthSettleAmount().subtract(productStatusMap.get("sum_settle_amount")==null?new BigDecimal(0):((BigDecimal)productStatusMap.get("sum_settle_amount"))));
				//本期订单实收金额
				mchtMonthTrade.setTotalOrderPayAmount(mchtMonthTrade.getTotalOrderPayAmount().subtract(productStatusMap.get("sum_pay_amount")==null?new BigDecimal(0):((BigDecimal)productStatusMap.get("sum_pay_amount"))));
			}
		}
		
		//保证金现缴
		Map<String, Object> mddMap = new HashMap<String, Object>();
		mddMap.put("tradeMonth", tradeMonth);
		mddMap.put("txnType", "'B','D'");
		mddMap.put("startDate", startDate);
		mddMap.put("endDate", endDate);
		List<Map<String, Object>> mchtDepositDtlMapListBD = mchtMonthTradeCustomMapper.selectNoMchtMonthTradeMchtDepositDtlList(mddMap);
		for(Map<String, Object> mchtDepositDtlMap : mchtDepositDtlMapListBD) {
			MchtMonthTrade mchtMonthTrade = mchtMonthTradeMap.get(mchtDepositDtlMap.get("mcht_id"));
			if(mchtMonthTrade != null) {
				//保证金现缴
				mchtMonthTrade.setCurrentDepositAmount(mchtDepositDtlMap.get("sum_txn_amt")==null?new BigDecimal(0):((BigDecimal)mchtDepositDtlMap.get("sum_txn_amt")));
			}
		}
		
		//本期付款金额
		Map<String, Object> ptmlMap = new HashMap<String, Object>();
		ptmlMap.put("tradeMonth", tradeMonth);
		ptmlMap.put("type", "1");
		ptmlMap.put("startDate", startDate);
		ptmlMap.put("endDate", endDate);
		List<Map<String, Object>> payToMchtLogMapList = mchtMonthTradeCustomMapper.selectNoMchtMonthTradePayToMchtLogList(ptmlMap);
		for(Map<String, Object> payToMchtLogMap : payToMchtLogMapList) {
			MchtMonthTrade mchtMonthTrade = mchtMonthTradeMap.get(payToMchtLogMap.get("mcht_id"));
			if(mchtMonthTrade != null) {
				//本期付款金额
				mchtMonthTrade.setCurrentPayAmount(payToMchtLogMap.get("sum_pay_amount")==null?new BigDecimal(0):((BigDecimal)payToMchtLogMap.get("sum_pay_amount")));
			}
		}
		
		//应扣违规
		mddMap.put("txnType", "'E','F'");
		List<Map<String, Object>> mchtDepositDtlMapListEF = mchtMonthTradeCustomMapper.selectNoMchtMonthTradeMchtDepositDtlList(mddMap);
		for(Map<String, Object> mchtDepositDtlMap : mchtDepositDtlMapListEF) {
			MchtMonthTrade mchtMonthTrade = mchtMonthTradeMap.get(mchtDepositDtlMap.get("mcht_id"));
			if(mchtMonthTrade != null) {
				//应扣违规
				mchtMonthTrade.setViolateNeedDeduct(mchtDepositDtlMap.get("sum_txn_amt")==null?new BigDecimal(0):((BigDecimal)mchtDepositDtlMap.get("sum_txn_amt")));
			}
		}
		
		//保证金往来
		mddMap.put("txnType", "'A'");
		List<Map<String, Object>> mchtDepositDtlMapListA = mchtMonthTradeCustomMapper.selectNoMchtMonthTradeMchtDepositDtlList(mddMap);
		for(Map<String, Object> mchtDepositDtlMap : mchtDepositDtlMapListA) {
			MchtMonthTrade mchtMonthTrade = mchtMonthTradeMap.get(mchtDepositDtlMap.get("mcht_id"));
			if(mchtMonthTrade != null) {
				//保证金往来
				mchtMonthTrade.setDepositDtl(mchtDepositDtlMap.get("sum_txn_amt")==null?new BigDecimal(0):((BigDecimal)mchtDepositDtlMap.get("sum_txn_amt")));
			}
		}
		
		for(Integer mchtId : mchtMonthTradeMap.keySet()) {
			MchtMonthTrade mchtMonthTrade = mchtMonthTradeMap.get(mchtId);
			//查询上期商家月往来报表
			Calendar calendar = Calendar.getInstance(); 
			calendar.set(Integer.valueOf(tradeMonth.substring(0, 4)),Integer.valueOf(tradeMonth.substring(5, 7))-1,1);
			calendar.add(Calendar.MONTH, -1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			MchtMonthTradeExample mchtMonthTradeExample = new MchtMonthTradeExample();
			mchtMonthTradeExample.createCriteria().andDelFlagEqualTo("0")
				.andMchtIdEqualTo(mchtId)
				.andTradeMonthEqualTo(sdf.format(calendar.getTime()));
			List<MchtMonthTrade> mchtMonthTradeList = mchtMonthTradeMapper.selectByExample(mchtMonthTradeExample);
			if(mchtMonthTradeList != null && mchtMonthTradeList.size() > 0) {
				//期初结欠
				mchtMonthTrade.setBeginUnpay(mchtMonthTradeList.get(0).getEndUnpay());
				//已收保证金
				mchtMonthTrade.setCollectDepositAmount(mchtMonthTradeList.get(0).getDepositBalance());
			}
			//本期增减
			mchtMonthTrade.setCurrentChangeAmount(mchtMonthTrade.getDepositDtl());
			//保证金余额
			mchtMonthTrade.setDepositBalance(mchtMonthTrade.getCollectDepositAmount().add(mchtMonthTrade.getCurrentChangeAmount()));
			//期末结欠
			mchtMonthTrade.setEndUnpay(mchtMonthTrade.getBeginUnpay().add(mchtMonthTrade.getCurrentMonthSettleAmount())
					.add(mchtMonthTrade.getCurrentDepositAmount()).subtract(mchtMonthTrade.getCurrentPayAmount())
					.add(mchtMonthTrade.getViolateNeedDeduct()).subtract(mchtMonthTrade.getDepositDtl())
					.add(mchtMonthTrade.getDiscount()));
			
			
			mchtMonthTradeMapper.insertSelective(mchtMonthTrade);
		}
	}
	
}
