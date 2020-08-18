package com.jf.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.MchtSettleOrderCustomMapper;
import com.jf.dao.MchtSettleSituationCustomMapper;
import com.jf.dao.MchtSettleSituationMapper;
import com.jf.entity.CustomerServiceOrderCustom;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtSettleSituation;
import com.jf.entity.MchtSettleSituationCustom;
import com.jf.entity.MchtSettleSituationCustomExample;
import com.jf.entity.MchtSettleSituationExample;
import com.jf.entity.OrderDtlCustom;
import com.jf.entity.PayToMchtLog;
@Service
public class MchtSettleSituationService extends BaseService<MchtSettleSituation,MchtSettleSituationExample> {
	@Autowired
	private MchtSettleSituationMapper mchtSettleSituationMapper;
	
	@Autowired
	private MchtSettleSituationCustomMapper mchtSettleSituationCustomMapper;
	
	@Autowired
	private MchtSettleOrderCustomMapper mchtSettleOrderCustomMapper;
	
	@Autowired
	private MchtInfoService mchtInfoService;
	
	@Autowired
	public void setMchtSettleSituationMapper(MchtSettleSituationMapper mchtSettleSituationMapper) {
		super.setDao(mchtSettleSituationMapper);
		this.mchtSettleSituationMapper = mchtSettleSituationMapper;
	}
	
	public int countByExample(MchtSettleSituationCustomExample example){
		return mchtSettleSituationCustomMapper.countByExample(example);
	}
	
	public List<MchtSettleSituationCustom> selectByExample(MchtSettleSituationCustomExample example){
		return mchtSettleSituationCustomMapper.selectByExample(example);
	}

	public List<MchtSettleSituationCustom> getSettleSituationList(HashMap<String, String> paramMap) {
		String settleDate = paramMap.get("settleDate");
		Map<Integer, MchtSettleSituationCustom> settleSituationMap=new HashMap<Integer, MchtSettleSituationCustom>();
		//查出要统计的所有订单明细
		List<OrderDtlCustom> orderDtlList=mchtSettleOrderCustomMapper.selectNoSituationCustomOrderDtlList(paramMap);
		MchtSettleSituationCustom mchtSettleSituationCustom;
		for(OrderDtlCustom orderDtlCustom:orderDtlList){
			mchtSettleSituationCustom=settleSituationMap.get(orderDtlCustom.getMchtId());
			if(mchtSettleSituationCustom==null){
				mchtSettleSituationCustom=new MchtSettleSituationCustom();
				mchtSettleSituationCustom.setMchtId(orderDtlCustom.getMchtId());
				mchtSettleSituationCustom.setProductNum(0);
				mchtSettleSituationCustom.setSettlePriceTotal(new BigDecimal(0));
				mchtSettleSituationCustom.setMchtPreferentialTotal(new BigDecimal(0));
				mchtSettleSituationCustom.setOrderAmount(new BigDecimal(0));
				mchtSettleSituationCustom.setRefundAmount(new BigDecimal(0));
				mchtSettleSituationCustom.setCommissionAmount(new BigDecimal(0));
				mchtSettleSituationCustom.setSettleAmount(new BigDecimal(0));
				mchtSettleSituationCustom.setPayAmount(new BigDecimal(0));
				
				settleSituationMap.put(orderDtlCustom.getMchtId(),mchtSettleSituationCustom);
			}
			
			mchtSettleSituationCustom.setProductNum(mchtSettleSituationCustom.getProductNum().intValue()+orderDtlCustom.getQuantity());
			
			if("1".equals(orderDtlCustom.getMchtType())){//SPOP
				mchtSettleSituationCustom.setSettlePriceTotal(mchtSettleSituationCustom.getSettlePriceTotal().add(orderDtlCustom.getSettlePrice().multiply(new BigDecimal(orderDtlCustom.getQuantity()))));//数量*结算价格
			}
			mchtSettleSituationCustom.setMchtPreferentialTotal(mchtSettleSituationCustom.getMchtPreferentialTotal().add(orderDtlCustom.getMchtPreferential()));
			mchtSettleSituationCustom.setOrderAmount(mchtSettleSituationCustom.getOrderAmount().add(orderDtlCustom.getPayAmount()));
			
			if("2".equals(orderDtlCustom.getMchtType())){//POP 有技术服务费
				mchtSettleSituationCustom.setCommissionAmount(mchtSettleSituationCustom.getCommissionAmount().add(orderDtlCustom.getCommissionAmount()));
			}
			mchtSettleSituationCustom.setSettleAmount(mchtSettleSituationCustom.getSettleAmount().add(orderDtlCustom.getSettleAmount()));
			
			
		}
		
		//查出要统计的所有直赔单
		List<CustomerServiceOrderCustom> customOrderList=mchtSettleOrderCustomMapper.selectNoSituationCustomOrderList(paramMap);
		for(CustomerServiceOrderCustom customerServiceOrder:customOrderList){
			mchtSettleSituationCustom=settleSituationMap.get(customerServiceOrder.getMchtId());
			if(mchtSettleSituationCustom==null){
				mchtSettleSituationCustom=new MchtSettleSituationCustom();
				mchtSettleSituationCustom.setMchtId(customerServiceOrder.getMchtId());
				mchtSettleSituationCustom.setProductNum(0);
				mchtSettleSituationCustom.setSettlePriceTotal(new BigDecimal(0));
				mchtSettleSituationCustom.setMchtPreferentialTotal(new BigDecimal(0));
				mchtSettleSituationCustom.setOrderAmount(new BigDecimal(0));
				mchtSettleSituationCustom.setRefundAmount(new BigDecimal(0));
				mchtSettleSituationCustom.setCommissionAmount(new BigDecimal(0));
				mchtSettleSituationCustom.setSettleAmount(new BigDecimal(0));
				mchtSettleSituationCustom.setPayAmount(new BigDecimal(0));
				
				settleSituationMap.put(customerServiceOrder.getMchtId(),mchtSettleSituationCustom);
			}
			mchtSettleSituationCustom.setRefundAmount(mchtSettleSituationCustom.getRefundAmount().add(customerServiceOrder.getAmount()));
			
			
		}
		
		//统计本期付款金额，根据付款记录表来统计
		List<PayToMchtLog> payToMchtLogList=mchtSettleOrderCustomMapper.selectNoSituationPayLogList(paramMap);
		
		for(PayToMchtLog payToMchtLog:payToMchtLogList){
			mchtSettleSituationCustom=settleSituationMap.get(payToMchtLog.getMchtId());
			if(mchtSettleSituationCustom==null){
				mchtSettleSituationCustom=new MchtSettleSituationCustom();
				mchtSettleSituationCustom.setMchtId(payToMchtLog.getMchtId());
				mchtSettleSituationCustom.setProductNum(0);
				mchtSettleSituationCustom.setSettlePriceTotal(new BigDecimal(0));
				mchtSettleSituationCustom.setMchtPreferentialTotal(new BigDecimal(0));
				mchtSettleSituationCustom.setOrderAmount(new BigDecimal(0));
				mchtSettleSituationCustom.setRefundAmount(new BigDecimal(0));
				mchtSettleSituationCustom.setCommissionAmount(new BigDecimal(0));
				mchtSettleSituationCustom.setSettleAmount(new BigDecimal(0));
				mchtSettleSituationCustom.setPayAmount(new BigDecimal(0));
				
				settleSituationMap.put(payToMchtLog.getMchtId(),mchtSettleSituationCustom);
			}
			mchtSettleSituationCustom.setPayAmount(mchtSettleSituationCustom.getPayAmount().add(payToMchtLog.getPayAmount()));
			
		}
		
		
		//统计累计保证金
		List<Map<String, Object>> depositTotalList=mchtSettleOrderCustomMapper.selectSituationDepositTotal(paramMap);
		Map<Integer, BigDecimal> depositTotalMap=new HashMap<Integer, BigDecimal>();
		if(depositTotalList!=null&&depositTotalList.size()>0){
			for(Map<String, Object> depositTotal:depositTotalList){
				depositTotalMap.put((Integer)depositTotal.get("mchtId"), (BigDecimal)depositTotal.get("totalPayAmount"));
			}
		}
		
		List<MchtSettleSituationCustom> mchtSettleSituationCustomList = new ArrayList<MchtSettleSituationCustom>();
		//生成结算情况记录
		for(Integer mchtId:settleSituationMap.keySet()){
			mchtSettleSituationCustom=settleSituationMap.get(mchtId);
			mchtSettleSituationCustom.setSettleDate(settleDate);

			//查询期初未付金额=上期末的未付金额      商家入驻的第一个月 a=0
			Calendar calendar=Calendar.getInstance(); 
			calendar.set(Integer.valueOf(settleDate.substring(0, 4)),Integer.valueOf(settleDate.substring(5, 7))-1,1);
			calendar.add(Calendar.MONTH, -1);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("mchtId", mchtId);
			map.put("settleDate", sdf.format(calendar.getTime()));
			MchtSettleSituation lastMchtSettleSituation = mchtSettleOrderCustomMapper.selectSituationByMchtIdAndDate(map);
			if(lastMchtSettleSituation==null){
				mchtSettleSituationCustom.setBeginUnpay(new BigDecimal(0));
			}else{
				mchtSettleSituationCustom.setBeginUnpay(lastMchtSettleSituation.getEndUnpay());
			}
			
			mchtSettleSituationCustom.setNeedPayAmount(mchtSettleSituationCustom.getSettleAmount().subtract(mchtSettleSituationCustom.getRefundAmount()));
			mchtSettleSituationCustom.setUnpayAmount(mchtSettleSituationCustom.getNeedPayAmount().subtract(mchtSettleSituationCustom.getPayAmount()));
			mchtSettleSituationCustom.setEndUnpay(mchtSettleSituationCustom.getBeginUnpay().add(mchtSettleSituationCustom.getNeedPayAmount()).subtract(mchtSettleSituationCustom.getPayAmount()));
			
			//累计保证金
			mchtSettleSituationCustom.setDepositTotal(depositTotalMap.get(mchtId)==null?new BigDecimal(0):depositTotalMap.get(mchtId));
			
			mchtSettleSituationCustom.setCreateDate(new Date());
			mchtSettleSituationCustom.setDelFlag("0");
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtSettleSituationCustom.getMchtId());
			mchtSettleSituationCustom.setMchtCode(mchtInfo.getMchtCode());
			mchtSettleSituationCustom.setShortName(mchtInfo.getShortName());
			mchtSettleSituationCustom.setCompanyName(mchtInfo.getCompanyName());
			mchtSettleSituationCustomList.add(mchtSettleSituationCustom);
		}
		return mchtSettleSituationCustomList;
	}

	public List<MchtSettleSituationCustom> currentSituationData(Map<String, String> paramMap) {
		return mchtSettleSituationCustomMapper.currentSituationData(paramMap);
	}

}
