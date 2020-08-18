package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.dao.CustomerServiceLogMapper;
import com.jf.entity.CustomerServiceLog;
import com.jf.entity.CustomerServiceLogExample;
import com.jf.entity.CustomerServiceOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月5日 下午1:42:48 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class CustomerServiceLogService extends BaseService<CustomerServiceLog, CustomerServiceLogExample> {
	
	@Autowired
	private CustomerServiceLogMapper customerServiceLogMapper;

	@Autowired
	public void setCustomerServiceLogMapper(CustomerServiceLogMapper customerServiceLogMapper) {
		this.setDao(customerServiceLogMapper);
		this.customerServiceLogMapper = customerServiceLogMapper;
	}
	
	/**
	 * 
	 * 方法描述 :根据售后订单id找日志
	 * @author  chenwf: 
	 * @date 创建时间：2017年7月18日 下午7:52:09 
	 * @version
	 * @param serviceOrderId
	 * @return
	 */
	public List<CustomerServiceLog> findListByServiceOrderId(Integer serviceOrderId) {
		CustomerServiceLogExample customerServiceLogExample = new CustomerServiceLogExample();
		customerServiceLogExample.createCriteria().andServiceOrderIdEqualTo(serviceOrderId).andDelFlagEqualTo("0");
		customerServiceLogExample.setOrderByClause("create_date desc");
		return customerServiceLogMapper.selectByExample(customerServiceLogExample);
	}

	public CustomerServiceLog add(CustomerServiceOrder customerServiceOrder, String reasonStr,
								  Date date) {
		String serviceType = customerServiceOrder.getServiceType();
		Integer quantity = customerServiceOrder.getQuantity();
		String proStatus = customerServiceOrder.getProStatus();
		String remarks = customerServiceOrder.getRemarks();


		String amountStr = "¥"+customerServiceOrder.getAmount();
		if(customerServiceOrder.getDepositAmount().compareTo(new BigDecimal(0)) > 0) {
			amountStr += "+"+"¥"+customerServiceOrder.getDepositAmount();
		}
		if(customerServiceOrder.getDepositAmount() != null && "A2".equals(customerServiceOrder.getProStatus()) 
				&& "A7".equals(customerServiceOrder.getReason()) && customerServiceOrder.getDepositAmount().intValue() > 0) {
			amountStr += "+"+customerServiceOrder.getDepositAmount().intValue()*100+"积分";
		}
		String context = "";
		if(serviceType.equals(Const.CUSTOMER_ORDER_TYPE_A) && proStatus.equals(Const.CUSTOMER_ORDER_PRO_STATUS_A1)){
			context = "<font>申请退款</font><br>"
							+ "申请数量："+quantity+"<br>"
							+ "退款金额："+amountStr+"<br>"
							+ "退款原因："+reasonStr+"<br>"
							+ "原因说明："+remarks;
		}else if(serviceType.equals(Const.CUSTOMER_ORDER_TYPE_B) && proStatus.equals(Const.CUSTOMER_ORDER_PRO_STATUS_B1)){
			context = "<font>申请退货</font><br>"
					+ "申请数量："+quantity+"<br>"
					+ "退款金额："+amountStr+"<br>"
					+ "退货原因："+reasonStr+"<br>"
					+ "原因说明："+remarks;
		}else if(serviceType.equals(Const.CUSTOMER_ORDER_TYPE_C) && proStatus.equals(Const.CUSTOMER_ORDER_PRO_STATUS_C1)){
			context = "<font>申请售后换货</font><br>"
					+ "申请数量："+quantity+"<br>"
					+ "退款原因："+reasonStr+"<br>"
					+ "原因说明："+remarks;
		}
		if("1".equals(customerServiceOrder.getIsAllowMchtModify())){
			context += "<br>其他：允许商家换货改退款";
		}

		CustomerServiceLog customerServiceLog = new CustomerServiceLog();
		customerServiceLog.setServiceOrderId(customerServiceOrder.getId());
		customerServiceLog.setOperatorType(Const.OPERATOR_TYPE_MEMBER);//客户
		customerServiceLog.setContent(context);
		customerServiceLog.setRemarks(remarks);
		customerServiceLog.setCreateBy(customerServiceOrder.getCreateBy());
		customerServiceLog.setCreateDate(date);
		customerServiceLog.setDelFlag("0");
		return saveModel(customerServiceLog);
		
	}

	public CustomerServiceLog saveModel(CustomerServiceLog customerServiceLog) {
		customerServiceLogMapper.insertSelective(customerServiceLog);
		return customerServiceLog;
	}

	public CustomerServiceLog save(Integer serviceOrderId, String operatorType, Integer createBy, String content, String remark) {
		CustomerServiceLog model = new CustomerServiceLog();
		model.setServiceOrderId(serviceOrderId);
		model.setOperatorType(operatorType);
		model.setContent(content);
		model.setRemarks(remark);
		model.setCreateBy(1);
		model.setCreateDate(new Date());
		model.setDelFlag("0");
		return save(model);
	}

	private CustomerServiceLog save(CustomerServiceLog model) {
		customerServiceLogMapper.insertSelective(model);
		return model;
	}
	
}
