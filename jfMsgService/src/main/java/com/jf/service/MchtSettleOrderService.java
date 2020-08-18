package com.jf.service;

import java.math.BigDecimal;
import java.text.ParseException;
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
import com.jf.dao.CustomerServiceOrderMapper;
import com.jf.dao.MchtSettleOrderCustomMapper;
import com.jf.dao.MchtSettleOrderMapper;
import com.jf.dao.MchtSettleSituationMapper;
import com.jf.dao.OrderDtlMapper;
import com.jf.dao.SubDepositOrderMapper;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.CustomerServiceOrderCustom;
import com.jf.entity.CustomerServiceOrderExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtSettleOrder;
import com.jf.entity.MchtSettleOrderCustom;
import com.jf.entity.MchtSettleOrderExample;
import com.jf.entity.MchtSettleSituation;
import com.jf.entity.OrderDtl;
import com.jf.entity.OrderDtlCustom;
import com.jf.entity.OrderDtlExample;
import com.jf.entity.PayToMchtLog;
import com.jf.entity.SubDepositOrder;
import com.jf.entity.SubDepositOrderExample;

@Service
@Transactional
public class MchtSettleOrderService extends BaseService<MchtSettleOrder, MchtSettleOrderExample> {
	private static Logger logger = LoggerFactory.getLogger(MchtSettleOrderService.class);
	@Autowired
	private MchtSettleOrderMapper mchtSettleOrderMapper;

	@Autowired
	private MchtSettleOrderCustomMapper mchtSettleOrderCustomMapper;

	@Autowired
	private OrderDtlMapper orderDtlMapper;

	@Autowired
	private CustomerServiceOrderMapper customerServiceOrderMapper;

	@Autowired
	private MchtSettleSituationMapper mchtSettleSituationMapper;
	
	@Autowired
	private SubDepositOrderMapper subDepositOrderMapper;

	@Autowired
	public void setMchtSettleOrderMapper(MchtSettleOrderMapper mchtSettleOrderMapper) {
		super.setDao(mchtSettleOrderMapper);
		this.mchtSettleOrderMapper = mchtSettleOrderMapper;
	}

	/**
	 * 生成结算单
	 * 
	 * @param beginDate
	 * @param endDate
	 */
	public void generateSettleOrder(String beginDate, String endDate) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDateTime;
		Date endDateTime;
		try {
			beginDateTime = sdf.parse(beginDate);
			endDateTime = sdf.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		Map<Integer, MchtSettleOrderCustom> settleOrderMap = new HashMap<Integer, MchtSettleOrderCustom>();
		// 查出要统计的所有订单明细
		List<OrderDtlCustom> orderDtlList = mchtSettleOrderCustomMapper.selectNoSettleOrderDtlList(beginDate, endDate);
		MchtSettleOrderCustom mchtSettleOrderCustom;
		for (OrderDtlCustom orderDtlCustom : orderDtlList) {
			mchtSettleOrderCustom = settleOrderMap.get(orderDtlCustom.getMchtId());
			if (mchtSettleOrderCustom == null) {
				mchtSettleOrderCustom = new MchtSettleOrderCustom();
				mchtSettleOrderCustom.setMchtId(orderDtlCustom.getMchtId());
				mchtSettleOrderCustom.setMchtType(orderDtlCustom.getMchtType());
				mchtSettleOrderCustom.setProductNum(0);
				mchtSettleOrderCustom.setSettlePriceTotal(new BigDecimal(0));
				mchtSettleOrderCustom.setMchtPreferentialTotal(new BigDecimal(0));
				mchtSettleOrderCustom.setOrderAmount(new BigDecimal(0));
				mchtSettleOrderCustom.setRefundAmount(new BigDecimal(0));
				mchtSettleOrderCustom.setCommissionAmount(new BigDecimal(0));

				mchtSettleOrderCustom.setSettleAmount(new BigDecimal(0));
				mchtSettleOrderCustom.setEarnestMoney(new BigDecimal(0));

				settleOrderMap.put(orderDtlCustom.getMchtId(), mchtSettleOrderCustom);
			}

			mchtSettleOrderCustom.setProductNum(mchtSettleOrderCustom.getProductNum().intValue() + orderDtlCustom.getQuantity());

			if ("1".equals(mchtSettleOrderCustom.getMchtType())) {// 联营
				mchtSettleOrderCustom.setSettlePriceTotal(mchtSettleOrderCustom.getSettlePriceTotal().add(orderDtlCustom.getSettlePrice().multiply(new BigDecimal(orderDtlCustom.getQuantity()))));// 数量*结算价格
			}
			mchtSettleOrderCustom.setMchtPreferentialTotal(mchtSettleOrderCustom.getMchtPreferentialTotal().add(orderDtlCustom.getMchtPreferential()));
			mchtSettleOrderCustom.setOrderAmount(mchtSettleOrderCustom.getOrderAmount().add(orderDtlCustom.getPayAmount()));

			if ("2".equals(mchtSettleOrderCustom.getMchtType())) {// POP 有技术服务费
				mchtSettleOrderCustom.setCommissionAmount(mchtSettleOrderCustom.getCommissionAmount().add(orderDtlCustom.getCommissionAmount()));
			}
			mchtSettleOrderCustom.setSettleAmount(mchtSettleOrderCustom.getSettleAmount().add(orderDtlCustom.getSettleAmount()));

			mchtSettleOrderCustom.getOrderDtlIds().add(orderDtlCustom.getId());

		}

		// 统计订单对应的定金金额
		for (Integer mchtId : settleOrderMap.keySet()) {
			mchtSettleOrderCustom = settleOrderMap.get(mchtId);
			List<Map<String, Object>> depositMaps = mchtSettleOrderCustomMapper.selectTotalDepositSettleAmountByOrderDtlIds(mchtSettleOrderCustom.getOrderDtlIds());
			if (depositMaps != null && depositMaps.size() > 0) {
				for (Map<String, Object> depositMap : depositMaps) {
					if (depositMap.get("settle_amount") != null) {
						mchtSettleOrderCustom.setSettleAmount(mchtSettleOrderCustom.getSettleAmount().add((BigDecimal) depositMap.get("settle_amount")));
					}
					if (depositMap.get("commission_amount") != null) {
						mchtSettleOrderCustom.setCommissionAmount(mchtSettleOrderCustom.getCommissionAmount().add((BigDecimal) depositMap.get("commission_amount")));
					}

					if (depositMap.get("total_deposit") != null) {
						mchtSettleOrderCustom.setEarnestMoney(mchtSettleOrderCustom.getEarnestMoney().add((BigDecimal) depositMap.get("total_deposit")));
					}
					mchtSettleOrderCustom.getSubDepositOrderIds().add((Integer)depositMap.get("id"));
				}
			}

		}

		// 统计未付尾款的定金金额（活动已结束的）
		List<Map<String, Object>> noSettleSubDepositOrderMaps = mchtSettleOrderCustomMapper.selectNoSettleSubDepositOrderList(beginDate, endDate);
		if(noSettleSubDepositOrderMaps!=null&&noSettleSubDepositOrderMaps.size()>0){
			for(Map<String, Object> depositOrderMap : noSettleSubDepositOrderMaps){
				
				Integer mchtId=(Integer)depositOrderMap.get("mcht_id");
				
				mchtSettleOrderCustom = settleOrderMap.get(mchtId);
				if (mchtSettleOrderCustom == null) {
					mchtSettleOrderCustom = new MchtSettleOrderCustom();
					mchtSettleOrderCustom.setMchtId(mchtId);
					mchtSettleOrderCustom.setMchtType((String)depositOrderMap.get("mcht_type"));
					mchtSettleOrderCustom.setProductNum(0);
					mchtSettleOrderCustom.setSettlePriceTotal(new BigDecimal(0));
					mchtSettleOrderCustom.setMchtPreferentialTotal(new BigDecimal(0));
					mchtSettleOrderCustom.setOrderAmount(new BigDecimal(0));
					mchtSettleOrderCustom.setRefundAmount(new BigDecimal(0));
					mchtSettleOrderCustom.setCommissionAmount(new BigDecimal(0));
					mchtSettleOrderCustom.setSettleAmount(new BigDecimal(0));
					mchtSettleOrderCustom.setEarnestMoney(new BigDecimal(0));
					settleOrderMap.put(mchtId, mchtSettleOrderCustom);
				}
				
				if (depositOrderMap.get("settle_amount") != null) {
					mchtSettleOrderCustom.setSettleAmount(mchtSettleOrderCustom.getSettleAmount().add((BigDecimal) depositOrderMap.get("settle_amount")));
				}
				if (depositOrderMap.get("commission_amount") != null) {
					mchtSettleOrderCustom.setCommissionAmount(mchtSettleOrderCustom.getCommissionAmount().add((BigDecimal) depositOrderMap.get("commission_amount")));
				}

				if (depositOrderMap.get("total_deposit") != null) {
					mchtSettleOrderCustom.setEarnestMoney(mchtSettleOrderCustom.getEarnestMoney().add((BigDecimal) depositOrderMap.get("total_deposit")));
				}
				mchtSettleOrderCustom.getSubDepositOrderIds().add((Integer)depositOrderMap.get("id"));
			}
		}
		
		

		// 查出要统计的所有直赔单
		List<CustomerServiceOrderCustom> customOrderList = mchtSettleOrderCustomMapper.selectNoSettleCustomOrderList(beginDate, endDate);
		for (CustomerServiceOrderCustom customerServiceOrder : customOrderList) {
			mchtSettleOrderCustom = settleOrderMap.get(customerServiceOrder.getMchtId());
			if (mchtSettleOrderCustom == null) {
				mchtSettleOrderCustom = new MchtSettleOrderCustom();
				mchtSettleOrderCustom.setMchtId(customerServiceOrder.getMchtId());
				mchtSettleOrderCustom.setMchtType(customerServiceOrder.getMchtType());
				mchtSettleOrderCustom.setProductNum(0);
				mchtSettleOrderCustom.setSettlePriceTotal(new BigDecimal(0));
				mchtSettleOrderCustom.setMchtPreferentialTotal(new BigDecimal(0));
				mchtSettleOrderCustom.setOrderAmount(new BigDecimal(0));
				mchtSettleOrderCustom.setRefundAmount(new BigDecimal(0));
				mchtSettleOrderCustom.setCommissionAmount(new BigDecimal(0));

				mchtSettleOrderCustom.setSettleAmount(new BigDecimal(0));
				mchtSettleOrderCustom.setEarnestMoney(new BigDecimal(0));

				settleOrderMap.put(customerServiceOrder.getMchtId(), mchtSettleOrderCustom);
			}
			mchtSettleOrderCustom.setRefundAmount(mchtSettleOrderCustom.getRefundAmount().add(customerServiceOrder.getAmount()));

			mchtSettleOrderCustom.getCustomerServiceOrderIds().add(customerServiceOrder.getId());

		}

		// 插入结算单表
		for (Integer mchtId : settleOrderMap.keySet()) {
			mchtSettleOrderCustom = settleOrderMap.get(mchtId);
			mchtSettleOrderCustom.setBeginDate(beginDateTime);
			mchtSettleOrderCustom.setEndDate(endDateTime);
			mchtSettleOrderCustom.setDepositAmount(new BigDecimal(0));

			mchtSettleOrderCustom.setPayAmount(new BigDecimal(0));
			mchtSettleOrderCustom.setConfirmStatus("1");
			mchtSettleOrderCustom.setCreateDate(new Date());
			mchtSettleOrderCustom.setDelFlag("0");

			// 结算单金额要减掉直赔单金额
			mchtSettleOrderCustom.setSettleAmount(mchtSettleOrderCustom.getSettleAmount().subtract(mchtSettleOrderCustom.getRefundAmount()));
			mchtSettleOrderCustom.setNeedPayAmount(mchtSettleOrderCustom.getSettleAmount());

			mchtSettleOrderMapper.insertSelective(mchtSettleOrderCustom);
            
			//将结算单id写到订单明细中
			if (mchtSettleOrderCustom.getOrderDtlIds().size() > 0) {
				OrderDtlExample orderDtlExample = new OrderDtlExample();
				orderDtlExample.createCriteria().andIdIn(mchtSettleOrderCustom.getOrderDtlIds());
				OrderDtl orderDtl4Update = new OrderDtl();
				orderDtl4Update.setMchtSettleOrderId(mchtSettleOrderCustom.getId());
				orderDtlMapper.updateByExampleSelective(orderDtl4Update, orderDtlExample);
			}
			
			
			//将结算单id写到预售定金子订单中
			if(mchtSettleOrderCustom.getSubDepositOrderIds().size()>0){
			  SubDepositOrderExample subDepositOrderExample = new SubDepositOrderExample();
			  subDepositOrderExample.createCriteria().andIdIn(mchtSettleOrderCustom.getSubDepositOrderIds());
			  SubDepositOrder subDepositOrder4Update = new SubDepositOrder();
			  subDepositOrder4Update.setMchtSettleOrderId(mchtSettleOrderCustom.getId());
			  subDepositOrderMapper.updateByExampleSelective(subDepositOrder4Update, subDepositOrderExample);
			}
			

			//将结算单id写到售后单中
			if (mchtSettleOrderCustom.getCustomerServiceOrderIds().size() > 0) {
				CustomerServiceOrderExample customerServiceOrderExample = new CustomerServiceOrderExample();
				customerServiceOrderExample.createCriteria().andIdIn(mchtSettleOrderCustom.getCustomerServiceOrderIds());

				CustomerServiceOrder customerServiceOrder4Update = new CustomerServiceOrder();
				customerServiceOrder4Update.setMchtSettleOrderId(mchtSettleOrderCustom.getId());
				customerServiceOrderMapper.updateByExampleSelective(customerServiceOrder4Update, customerServiceOrderExample);
			}

		}

		// 没有订单的商家，也生成一条数据为0的结算单
		List<MchtInfo> mchtInfoList = mchtSettleOrderCustomMapper.selectNoSettleMchtInfo(beginDate, endDate);
		for (MchtInfo mchtInfo : mchtInfoList) {
			mchtSettleOrderCustom = new MchtSettleOrderCustom();
			mchtSettleOrderCustom.setMchtId(mchtInfo.getId());
			mchtSettleOrderCustom.setMchtType(mchtInfo.getMchtType());
			mchtSettleOrderCustom.setProductNum(0);
			mchtSettleOrderCustom.setSettlePriceTotal(new BigDecimal(0));
			mchtSettleOrderCustom.setMchtPreferentialTotal(new BigDecimal(0));
			mchtSettleOrderCustom.setOrderAmount(new BigDecimal(0));
			mchtSettleOrderCustom.setRefundAmount(new BigDecimal(0));
			mchtSettleOrderCustom.setCommissionAmount(new BigDecimal(0));
			mchtSettleOrderCustom.setSettleAmount(new BigDecimal(0));
			mchtSettleOrderCustom.setEarnestMoney(new BigDecimal(0));
			mchtSettleOrderCustom.setBeginDate(beginDateTime);
			mchtSettleOrderCustom.setEndDate(endDateTime);
			mchtSettleOrderCustom.setDepositAmount(new BigDecimal(0));
			mchtSettleOrderCustom.setNeedPayAmount(new BigDecimal(0));
			mchtSettleOrderCustom.setPayAmount(new BigDecimal(0));
			mchtSettleOrderCustom.setConfirmStatus("1");
			mchtSettleOrderCustom.setCreateDate(new Date());
			mchtSettleOrderCustom.setDelFlag("0");
			mchtSettleOrderMapper.insertSelective(mchtSettleOrderCustom);
		}

	}

	/**
	 * 生成每月结算状况
	 * 
	 * @param beginDate
	 * @param endDate
	 */
	public void generateSettleSituation(String settleDate) {

		if (StringUtil.isEmpty(settleDate)) {
			return;
		}

		Map<Integer, MchtSettleSituation> settleSituationMap = new HashMap<Integer, MchtSettleSituation>();
		MchtSettleSituation mchtSettleSituation;

		// 查询要统计的商家
		List<MchtInfo> mchtInfos = mchtSettleOrderCustomMapper.selectNoSettleSituationMcht(settleDate);

		if (mchtInfos == null || mchtInfos.size() == 0) {
			return;
		}

		for (MchtInfo mchtInfo : mchtInfos) {
			mchtSettleSituation = new MchtSettleSituation();
			mchtSettleSituation.setMchtId(mchtInfo.getId());
			setMchtSettleSituationDefaultValue(mchtSettleSituation);
			settleSituationMap.put(mchtInfo.getId(), mchtSettleSituation);
		}

		// 查出要统计的所有订单明细(本期没订单的商家也要查询出来)
		List<OrderDtlCustom> orderDtlList = mchtSettleOrderCustomMapper.selectNoSituationCustomOrderDtlList(settleDate);

		for (OrderDtlCustom orderDtlCustom : orderDtlList) {
			mchtSettleSituation = settleSituationMap.get(orderDtlCustom.getMchtId());
			if (mchtSettleSituation == null) {
				continue;
			}

			mchtSettleSituation.setProductNum(mchtSettleSituation.getProductNum().intValue() + (orderDtlCustom.getId() == null ? 0 : orderDtlCustom.getQuantity()));
			mchtSettleSituation.setProductAmount(mchtSettleSituation.getProductAmount().add(orderDtlCustom.getId() == null ? new BigDecimal(0) : (orderDtlCustom.getSalePrice().multiply(new BigDecimal(orderDtlCustom.getQuantity())))));
			mchtSettleSituation.setPlatformPreferential(mchtSettleSituation.getPlatformPreferential().add(orderDtlCustom.getId() == null ? new BigDecimal(0) : (orderDtlCustom.getPlatformPreferential())));
			mchtSettleSituation.setIntegralPreferential(mchtSettleSituation.getIntegralPreferential().add(orderDtlCustom.getId() == null ? new BigDecimal(0) : (orderDtlCustom.getIntegralPreferential())));

			if ("1".equals(orderDtlCustom.getMchtType())) {// 联营
				mchtSettleSituation.setSettlePriceTotal(mchtSettleSituation.getSettlePriceTotal().add(orderDtlCustom.getId() == null ? new BigDecimal(0) : (orderDtlCustom.getSettlePrice().multiply(new BigDecimal(orderDtlCustom.getQuantity())))));// 数量*结算价格
			}
			mchtSettleSituation.setMchtPreferentialTotal(mchtSettleSituation.getMchtPreferentialTotal().add(orderDtlCustom.getId() == null ? new BigDecimal(0) : (orderDtlCustom.getMchtPreferential())));
			mchtSettleSituation.setOrderAmount(mchtSettleSituation.getOrderAmount().add(orderDtlCustom.getId() == null ? new BigDecimal(0) : (orderDtlCustom.getPayAmount())));

			if ("2".equals(orderDtlCustom.getMchtType())) {// POP 有技术服务费
				mchtSettleSituation.setCommissionAmount(mchtSettleSituation.getCommissionAmount().add(orderDtlCustom.getId() == null ? new BigDecimal(0) : (orderDtlCustom.getCommissionAmount())));
			}
			mchtSettleSituation.setSettleAmount(mchtSettleSituation.getSettleAmount().add(orderDtlCustom.getId() == null ? new BigDecimal(0) : (orderDtlCustom.getSettleAmount())));

		}

		// 统计退款，退货的订单数据
		List<OrderDtlCustom> returnOrderDtlCustoms = mchtSettleOrderCustomMapper.selectNoSituationReturnOrderDtlList(settleDate);
		for (OrderDtlCustom orderDtlCustom : returnOrderDtlCustoms) {
			mchtSettleSituation = settleSituationMap.get(orderDtlCustom.getMchtId());
			if (mchtSettleSituation == null) {
				continue;
			}

			mchtSettleSituation.setReturnProductNum(mchtSettleSituation.getReturnProductNum().intValue() + orderDtlCustom.getQuantity());
			mchtSettleSituation.setReturnProductAmount(mchtSettleSituation.getReturnProductAmount().add(orderDtlCustom.getSalePrice().multiply(new BigDecimal(orderDtlCustom.getQuantity()))));
			mchtSettleSituation.setReturnPlatformPreferential(mchtSettleSituation.getReturnPlatformPreferential().add(orderDtlCustom.getPlatformPreferential()));
			mchtSettleSituation.setReturnIntegralPreferential(mchtSettleSituation.getReturnIntegralPreferential().add(orderDtlCustom.getIntegralPreferential()));
			mchtSettleSituation.setReturnMchtPreferential(mchtSettleSituation.getReturnMchtPreferential().add(orderDtlCustom.getMchtPreferential()));

			if ("2".equals(orderDtlCustom.getMchtType())) {// POP 有技术服务费
				mchtSettleSituation.setCommissionAmount(mchtSettleSituation.getCommissionAmount().add(orderDtlCustom.getCommissionAmount()));
			}
			mchtSettleSituation.setReturnOrderAmount(mchtSettleSituation.getReturnOrderAmount().add(orderDtlCustom.getSettleAmount()));

		}

		// 统计当期退款，退货的实退金额
		List<OrderDtlCustom> CustomOrderListABs = mchtSettleOrderCustomMapper.selectNoSituationCustomOrderListAB(settleDate, "1");
		for (OrderDtlCustom orderDtlCustom : CustomOrderListABs) {
			mchtSettleSituation = settleSituationMap.get(orderDtlCustom.getMchtId());
			if (mchtSettleSituation == null) {
				continue;
			}

			mchtSettleSituation.setReturnAmount(mchtSettleSituation.getReturnAmount().add(orderDtlCustom.getPayAmount()));

		}

		// 统计跨期退款，退货的实退金额
		List<OrderDtlCustom> CustomOrderListABAcross = mchtSettleOrderCustomMapper.selectNoSituationCustomOrderListAB(settleDate, "2");
		for (OrderDtlCustom orderDtlCustom : CustomOrderListABAcross) {
			mchtSettleSituation = settleSituationMap.get(orderDtlCustom.getMchtId());
			if (mchtSettleSituation == null) {
				continue;
			}

			mchtSettleSituation.setAcrossMonthReturnAmount(mchtSettleSituation.getAcrossMonthReturnAmount().add(orderDtlCustom.getPayAmount()));

		}

		// 查出要统计的所有直赔单
		List<CustomerServiceOrderCustom> customOrderList = mchtSettleOrderCustomMapper.selectNoSituationCustomOrderList(settleDate);
		for (CustomerServiceOrderCustom customerServiceOrder : customOrderList) {
			mchtSettleSituation = settleSituationMap.get(customerServiceOrder.getMchtId());
			if (mchtSettleSituation == null) {
				continue;
			}
			mchtSettleSituation.setRefundAmount(mchtSettleSituation.getRefundAmount().add(customerServiceOrder.getAmount()));

		}

		// 统计本期付款金额，根据付款记录表来统计
		List<PayToMchtLog> payToMchtLogList = mchtSettleOrderCustomMapper.selectNoSituationPayLogList(settleDate);

		for (PayToMchtLog payToMchtLog : payToMchtLogList) {
			mchtSettleSituation = settleSituationMap.get(payToMchtLog.getMchtId());
			if (mchtSettleSituation == null) {
				continue;
			}
			mchtSettleSituation.setPayAmount(mchtSettleSituation.getPayAmount().add(payToMchtLog.getPayAmount()));

		}

		// 统计累计保证金
		List<Map<String, Object>> depositTotalList = mchtSettleOrderCustomMapper.selectSituationDepositTotal(settleDate);
		Map<Integer, BigDecimal> depositTotalMap = new HashMap<Integer, BigDecimal>();
		if (depositTotalList != null && depositTotalList.size() > 0) {
			for (Map<String, Object> depositTotal : depositTotalList) {
				depositTotalMap.put((Integer) depositTotal.get("mchtId"), (BigDecimal) depositTotal.get("totalPayAmount"));
			}
		}

		// 统计抵缴保证金
		List<Map<String, Object>> situationDeductionDepositList = mchtSettleOrderCustomMapper.selectSituationDeductionDeposit(settleDate);
		Map<Integer, BigDecimal> deductiondepositTotalMap = new HashMap<Integer, BigDecimal>();
		if (situationDeductionDepositList != null && situationDeductionDepositList.size() > 0) {
			for (Map<String, Object> deductiondepositTotal : situationDeductionDepositList) {
				deductiondepositTotalMap.put((Integer) deductiondepositTotal.get("mchtId"), (BigDecimal) deductiondepositTotal.get("totalDeductionDeposit"));
			}
		}

		// 统计本月可接结算
		List<Map<String, Object>> currentMonthSettleAmountList = mchtSettleOrderCustomMapper.selectCurrentMonthSettleAmount(settleDate);
		if (currentMonthSettleAmountList != null && currentMonthSettleAmountList.size() > 0) {
			for (Map<String, Object> currentMonthSettleAmount : currentMonthSettleAmountList) {
				mchtSettleSituation = settleSituationMap.get((Integer) currentMonthSettleAmount.get("mcht_id"));

				if (mchtSettleSituation == null) {
					continue;
				}

				mchtSettleSituation.setCurrentMonthSettleAmount((BigDecimal) currentMonthSettleAmount.get("settle_amount"));
			}
		}

		// 统计跨月可接结算
		List<Map<String, Object>> acrossMonthSettleAmountList = mchtSettleOrderCustomMapper.selectAcrossMonthSettleAmount(settleDate);
		if (acrossMonthSettleAmountList != null && acrossMonthSettleAmountList.size() > 0) {
			for (Map<String, Object> acrossMonthSettleAmount : acrossMonthSettleAmountList) {
				mchtSettleSituation = settleSituationMap.get((Integer) acrossMonthSettleAmount.get("mcht_id"));

				if (mchtSettleSituation == null) {
					continue;
				}

				mchtSettleSituation.setAcrossMonthSettleAmount((BigDecimal) acrossMonthSettleAmount.get("settle_amount"));
			}
		}

		// 生成结算情况记录
		for (Integer mchtId : settleSituationMap.keySet()) {
			mchtSettleSituation = settleSituationMap.get(mchtId);
			mchtSettleSituation.setSettleDate(settleDate);

			// 累计保证金
			mchtSettleSituation.setDepositTotal(depositTotalMap.get(mchtId) == null ? new BigDecimal(0) : depositTotalMap.get(mchtId));

			// 抵缴保证金
			mchtSettleSituation.setDeductionDepositTotal(deductiondepositTotalMap.get(mchtId) == null ? new BigDecimal(0) : deductiondepositTotalMap.get(mchtId));

			// 查询上个月的结算情况单，用于获取各期初金额
			Calendar calendar = Calendar.getInstance();
			calendar.set(Integer.valueOf(settleDate.substring(0, 4)), Integer.valueOf(settleDate.substring(5, 7)) - 1, 1);
			calendar.add(Calendar.MONTH, -1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			MchtSettleSituation lastMchtSettleSituation = mchtSettleOrderCustomMapper.selectSituationByMchtIdAndDate(mchtId, sdf.format(calendar.getTime()));
			if (lastMchtSettleSituation == null) {
				mchtSettleSituation.setBeginUnpay(new BigDecimal(0));// 期初未付金额=上期末的未付金额
																		// 商家入驻的第一个月
																		// a=0
				mchtSettleSituation.setBeginSettleAmout(new BigDecimal(0));
				mchtSettleSituation.setBeginNotOutAccount(new BigDecimal(0));
			} else {
				mchtSettleSituation.setBeginUnpay(lastMchtSettleSituation.getDiscountEndUnpay());
				mchtSettleSituation.setBeginSettleAmout(lastMchtSettleSituation.getEndSettleAmount());
				mchtSettleSituation.setBeginNotOutAccount(lastMchtSettleSituation.getEndNotOutAccount());
			}

			// 本期应付款=本期结算金额-本期直赔单金额-本期代退应扣
			mchtSettleSituation.setNeedPayAmount(mchtSettleSituation.getSettleAmount().subtract(mchtSettleSituation.getRefundAmount()).subtract(mchtSettleSituation.getReturnOrderAmount()));

			mchtSettleSituation.setUnpayAmount(mchtSettleSituation.getNeedPayAmount().subtract(mchtSettleSituation.getPayAmount()));

			// 期末未付=期初结欠+本期结算金额-本期直赔单金额-本期代退应扣-本期实付款金额-本期抵缴保证金
			mchtSettleSituation.setEndUnpay(mchtSettleSituation.getBeginUnpay().add(mchtSettleSituation.getSettleAmount()).subtract(mchtSettleSituation.getRefundAmount()).subtract(mchtSettleSituation.getReturnOrderAmount()).subtract(mchtSettleSituation.getPayAmount()).subtract(mchtSettleSituation.getDeductionDepositTotal()));

			mchtSettleSituation.setDiscountEndUnpay(mchtSettleSituation.getEndUnpay());

			// 期末未出账=期初未出账-跨月可结算+本期应付款金额-本期可结算
			mchtSettleSituation.setEndNotOutAccount(mchtSettleSituation.getBeginNotOutAccount().subtract(mchtSettleSituation.getAcrossMonthSettleAmount()).add(mchtSettleSituation.getNeedPayAmount()).subtract(mchtSettleSituation.getCurrentMonthSettleAmount()));

			// 期末可结算=折后期末应付-（期初未出账-跨月可结算+本期应付款金额-本月可结算）
			mchtSettleSituation.setEndSettleAmount(mchtSettleSituation.getDiscountEndUnpay().subtract(mchtSettleSituation.getBeginNotOutAccount().subtract(mchtSettleSituation.getAcrossMonthSettleAmount()).add(mchtSettleSituation.getNeedPayAmount()).subtract(mchtSettleSituation.getCurrentMonthSettleAmount())));

			mchtSettleSituation.setCreateDate(new Date());
			mchtSettleSituation.setDelFlag("0");

			mchtSettleSituationMapper.insertSelective(mchtSettleSituation);
		}

	}

	private void setMchtSettleSituationDefaultValue(MchtSettleSituation mchtSettleSituation) {
		mchtSettleSituation.setAcrossMonthSettleAmount(new BigDecimal(0));
		mchtSettleSituation.setAcrossMonthReturnAmount(new BigDecimal(0));
		mchtSettleSituation.setBeginNotOutAccount(new BigDecimal(0));
		mchtSettleSituation.setBeginSettleAmout(new BigDecimal(0));
		mchtSettleSituation.setBeginUnpay(new BigDecimal(0));
		mchtSettleSituation.setCommissionAmount(new BigDecimal(0));
		mchtSettleSituation.setCreateDate(new Date());
		mchtSettleSituation.setCurrentMonthSettleAmount(new BigDecimal(0));
		mchtSettleSituation.setDeductionDepositTotal(new BigDecimal(0));
		mchtSettleSituation.setDelFlag("0");
		mchtSettleSituation.setDepositTotal(new BigDecimal(0));
		mchtSettleSituation.setDiscount(new BigDecimal(0));
		mchtSettleSituation.setDiscountEndUnpay(new BigDecimal(0));
		mchtSettleSituation.setEndNotOutAccount(new BigDecimal(0));
		mchtSettleSituation.setEndSettleAmount(new BigDecimal(0));
		mchtSettleSituation.setEndUnpay(new BigDecimal(0));
		mchtSettleSituation.setIntegralPreferential(new BigDecimal(0));
		mchtSettleSituation.setMchtPreferentialTotal(new BigDecimal(0));
		mchtSettleSituation.setNeedPayAmount(new BigDecimal(0));
		mchtSettleSituation.setOrderAmount(new BigDecimal(0));
		mchtSettleSituation.setPayAmount(new BigDecimal(0));
		mchtSettleSituation.setPlatformPreferential(new BigDecimal(0));
		mchtSettleSituation.setProductAmount(new BigDecimal(0));
		mchtSettleSituation.setProductNum(0);
		mchtSettleSituation.setRefundAmount(new BigDecimal(0));
		mchtSettleSituation.setReturnAmount(new BigDecimal(0));
		mchtSettleSituation.setReturnCommissionAmount(new BigDecimal(0));
		mchtSettleSituation.setReturnIntegralPreferential(new BigDecimal(0));
		mchtSettleSituation.setReturnMchtPreferential(new BigDecimal(0));
		mchtSettleSituation.setReturnOrderAmount(new BigDecimal(0));
		mchtSettleSituation.setReturnPlatformPreferential(new BigDecimal(0));
		mchtSettleSituation.setReturnProductAmount(new BigDecimal(0));
		mchtSettleSituation.setReturnProductNum(0);
		mchtSettleSituation.setSettleAmount(new BigDecimal(0));
		mchtSettleSituation.setSettlePriceTotal(new BigDecimal(0));
	}

	public int autoConfirmSettleOrder() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, -14 * 24);

		MchtSettleOrderExample mchtSettleOrderExample = new MchtSettleOrderExample();
		mchtSettleOrderExample.createCriteria().andCreateDateLessThan(cal.getTime()).andConfirmStatusEqualTo("1");

		MchtSettleOrder mchtSettleOrder4Update = new MchtSettleOrder();
		mchtSettleOrder4Update.setConfirmStatus("2");
		mchtSettleOrder4Update.setRemarks("14*24小时商家未确认，自动变成待平台确认状态。");
		int count = mchtSettleOrderMapper.updateByExampleSelective(mchtSettleOrder4Update, mchtSettleOrderExample);
		return count;
	}

	public static void main(String[] args) {
		String settleDate = "2017-01-07";
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.valueOf(settleDate.substring(0, 4)), Integer.valueOf(settleDate.substring(5, 7)) - 1, Integer.valueOf(settleDate.substring(8, 10)));

		calendar.set(Integer.valueOf(settleDate.substring(0, 4)), Integer.valueOf(settleDate.substring(5, 7)) - 1, Integer.valueOf(settleDate.substring(8, 10)));
		calendar.add(Calendar.MONTH, -1);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// System.out.println(sdf.format(calendar.getTime()));

	}
}
