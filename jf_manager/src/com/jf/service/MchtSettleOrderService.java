package com.jf.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.ExpressMapper;
import com.jf.dao.MchtDepositDtlMapper;
import com.jf.dao.MchtDepositMapper;
import com.jf.dao.MchtSettleOrderCustomMapper;
import com.jf.dao.MchtSettleOrderMapper;
import com.jf.dao.PayToMchtLogMapper;
import com.jf.entity.MchtDeposit;
import com.jf.entity.MchtDepositDtl;
import com.jf.entity.MchtInfoCustom;
import com.jf.entity.MchtInfoCustomExample;
import com.jf.entity.MchtSettleOrder;
import com.jf.entity.MchtSettleOrderCustom;
import com.jf.entity.MchtSettleOrderCustomExample;
import com.jf.entity.MchtSettleOrderExample;
import com.jf.entity.PayToMchtLog;
@Service
public class MchtSettleOrderService extends BaseService<MchtSettleOrder,MchtSettleOrderExample> {
	@Autowired
	private MchtSettleOrderMapper mchtSettleOrderMapper;
	
	@Autowired
	private MchtSettleOrderCustomMapper mchtSettleOrderCustomMapper;
	
	@Autowired
	private MchtDepositDtlMapper mchtDepositDtlMapper;
	
	@Autowired
	private MchtDepositMapper mchtDepositMapper;
	
	@Autowired
	private ExpressMapper expressMapper;
	
	@Autowired
	private PayToMchtLogMapper payToMchtLogMapper;
	
	@Autowired
	public void setMchtSettleOrderMapper(MchtSettleOrderMapper mchtSettleOrderMapper) {
		super.setDao(mchtSettleOrderMapper);
		this.mchtSettleOrderMapper = mchtSettleOrderMapper;
	}
	
	public int countByExample(MchtSettleOrderCustomExample example){
		return mchtSettleOrderCustomMapper.countByExample(example);
	}
	
	public List<MchtSettleOrderCustom> selectByExample(MchtSettleOrderCustomExample example){
		return mchtSettleOrderCustomMapper.selectByExample(example);
	}

	public void updateMchtSettleOrder(MchtSettleOrder mchtSettleOrder,MchtDepositDtl mchtDepositDtl, MchtDeposit mchtDeposit) {
		this.updateByPrimaryKeySelective(mchtSettleOrder);
		mchtDepositDtlMapper.insertSelective(mchtDepositDtl);
		mchtDepositMapper.updateByPrimaryKeySelective(mchtDeposit);
	}

	public void updateMchtSettleOrder(MchtSettleOrder mchtSettleOrder,PayToMchtLog payToMchtLog) {
		this.updateByPrimaryKeySelective(mchtSettleOrder);
		payToMchtLogMapper.insertSelective(payToMchtLog);
	}

	public MchtSettleOrderCustom countCompleteSubOrder(Integer id) {
		return mchtSettleOrderCustomMapper.countCompleteSubOrder(id);
	}

	public BigDecimal countCustomerServiceOrder(Integer id) {
		return mchtSettleOrderCustomMapper.countCustomerServiceOrder(id);
	}

	public void batchConfirmConfirmStatus(List<Integer> idsList) {
		mchtSettleOrderCustomMapper.batchConfirmConfirmStatus(idsList);
	}

	public void batchPayStatus(HashMap<String, Object> paramMap) {
		mchtSettleOrderCustomMapper.batchPayStatus(paramMap);
	}

	public BigDecimal getNeedPayAmountByIds(List<Integer> idsList) {
		return mchtSettleOrderCustomMapper.getNeedPayAmountByIds(idsList);
	}

	public void batchPay(List<MchtSettleOrder> mchtSettleOrderList,List<PayToMchtLog> payToMchtLogList) {
		for(MchtSettleOrder mchtSettleOrder:mchtSettleOrderList){
			this.updateByPrimaryKeySelective(mchtSettleOrder);
		}
		for(PayToMchtLog payToMchtLog:payToMchtLogList){
			payToMchtLogMapper.insertSelective(payToMchtLog);
		}
	}

	/**
	 * @MethodName selectByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月23日 下午2:14:33
	 */
	public List<MchtSettleOrderCustom> selectByCustomExample(
			MchtSettleOrderCustomExample mchtSettleOrderCustomExample) {
		// TODO Auto-generated method stub
		return mchtSettleOrderCustomMapper.selectByCustomExample(mchtSettleOrderCustomExample);
	}
}
