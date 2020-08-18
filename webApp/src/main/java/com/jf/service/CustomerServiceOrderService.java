package com.jf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.ArgException;
import com.jf.common.constant.Const;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.CustomerServiceOrderMapper;
import com.jf.entity.CustomerServiceLog;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.CustomerServiceOrderExample;
import com.jf.entity.CustomerServicePic;
import com.jf.entity.CustomerServicePicExample;
import com.jf.entity.CustomerServiceStatusLog;
import com.jf.entity.ServiceLogPic;
import com.jf.entity.ServiceLogPicExample;
import com.jf.entity.SubOrder;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月4日 上午10:47:49 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class CustomerServiceOrderService extends BaseService<CustomerServiceOrder, CustomerServiceOrderExample> {
	
	@Autowired
	private CustomerServiceOrderMapper customerServiceOrderMapper;
	@Resource
	private CustomerServicePicService customerServicePicService;
	
	@Resource
	private CustomerServiceStatusLogService customerServiceStatusLogService;
	
	@Resource
	private CustomerServiceLogService customerServiceLogService;
	
	@Resource
	private ServiceLogPicService serviceLogPicService;
	@Resource
	private PlatformMsgService platformMsgService;
	@Resource
	private MsgTplService msgTplService;
	@Resource
	private SubOrderService subOrderService;
	
	@Resource
	private SysAppMessageService sysAppMessageService;
	@Autowired
	public void setCustomerServiceOrderMapper(CustomerServiceOrderMapper customerServiceOrderMapper) {
		this.setDao(customerServiceOrderMapper);
		this.customerServiceOrderMapper = customerServiceOrderMapper;
	}
	public List<CustomerServiceOrder> findList(Integer orderDtlId, Integer memberId, String serviceType) {
		CustomerServiceOrderExample customerServiceOrderExample = new CustomerServiceOrderExample();
		if(serviceType.equals(Const.CUSTOMER_ORDER_TYPE_A)){
			customerServiceOrderExample.createCriteria().andOrderDtlIdEqualTo(orderDtlId).andDelFlagEqualTo("0")
					.andCreateByEqualTo(Integer.valueOf(memberId)).andServiceTypeEqualTo(serviceType);
		}else if(serviceType.equals("BC")){
			customerServiceOrderExample.or().andOrderDtlIdEqualTo(orderDtlId).andDelFlagEqualTo("0")
			.andCreateByEqualTo(Integer.valueOf(memberId)).andServiceTypeEqualTo(Const.CUSTOMER_ORDER_TYPE_B);
			customerServiceOrderExample.or().andOrderDtlIdEqualTo(orderDtlId).andDelFlagEqualTo("0")
			.andCreateByEqualTo(Integer.valueOf(memberId)).andServiceTypeEqualTo(Const.CUSTOMER_ORDER_TYPE_C);
		}else if(serviceType.equals("AB")){
			customerServiceOrderExample.or().andOrderDtlIdEqualTo(orderDtlId).andDelFlagEqualTo("0")
			.andCreateByEqualTo(Integer.valueOf(memberId)).andServiceTypeEqualTo(Const.CUSTOMER_ORDER_TYPE_A)
			.andStatusEqualTo(Const.CUSTOMER_ORDER_STATUS_SUCCESS);
			customerServiceOrderExample.or().andOrderDtlIdEqualTo(orderDtlId).andDelFlagEqualTo("0")
			.andCreateByEqualTo(Integer.valueOf(memberId)).andServiceTypeEqualTo(Const.CUSTOMER_ORDER_TYPE_B)
			.andStatusEqualTo(Const.CUSTOMER_ORDER_STATUS_SUCCESS);
		}
		customerServiceOrderExample.setOrderByClause("id desc");
		return customerServiceOrderMapper.selectByExample(customerServiceOrderExample);
	}
	public List<CustomerServiceOrder> findListBySubOrderIdAndStatus(Integer subOrderId,
			String status) {
		CustomerServiceOrderExample customerServiceOrderExample = new CustomerServiceOrderExample();
		customerServiceOrderExample.createCriteria().andSubOrderIdEqualTo(subOrderId).andDelFlagEqualTo("0")
		.andStatusEqualTo(status);
		return customerServiceOrderMapper.selectByExample(customerServiceOrderExample);
	}
	
	public int findListBySubOrderIdAndStatusCount(Integer subOrderId, List<String> csServiceList) {
		CustomerServiceOrderExample customerServiceOrderExample = new CustomerServiceOrderExample();
		customerServiceOrderExample.createCriteria().andSubOrderIdEqualTo(subOrderId).andDelFlagEqualTo("0").andStatusIn(csServiceList);
		return customerServiceOrderMapper.countByExample(customerServiceOrderExample);
	}
	
	public CustomerServiceOrder addCustomerService(Integer memberId, Integer subOrderId, Integer orderDtlId, String serviceType,
			String contactPhone, String reason, String reasonStr, String remarks, Integer quantity, BigDecimal amount,
			String pic) {
		//判断是否已经存在进行中的售后单
		CustomerServiceOrderExample customerServiceOrderExample=new CustomerServiceOrderExample();
		List<String> serviceOrderStatus=new ArrayList<String>();
		serviceOrderStatus.add(Const.CUSTOMER_ORDER_STATUS_SUCCESS);
		serviceOrderStatus.add(Const.CUSTOMER_ORDER_STATUS_REFUNDING);
		customerServiceOrderExample.createCriteria().andDelFlagEqualTo("0").andOrderDtlIdEqualTo(orderDtlId).andStatusIn(serviceOrderStatus);
		int count=customerServiceOrderMapper.countByExample(customerServiceOrderExample);
		if(count>0){
			throw new ArgException("您已经申请过售后,请勿重新提交。");
		}
		Date date = new Date();
		String orderCode = "";
		String proStatus = serviceType+"1";
		SubOrder subOrder = subOrderService.findListById(subOrderId);
		//1售后单表
		CustomerServiceOrder customerServiceOrder = new CustomerServiceOrder();
		customerServiceOrder.setSubOrderId(subOrderId);
		customerServiceOrder.setOrderDtlId(orderDtlId);
		
		if(!StringUtil.isBlank(serviceType)){
			orderCode = serviceType + CommonUtil.getOrderCode();
			customerServiceOrder.setOrderCode(orderCode);
			customerServiceOrder.setServiceType(serviceType);
			customerServiceOrder.setProStatus(proStatus);
		}
		customerServiceOrder.setContactPhone(contactPhone);
		customerServiceOrder.setReason(reason);
		customerServiceOrder.setQuantity(quantity);
		customerServiceOrder.setAmount(amount);
		customerServiceOrder.setRemarks(remarks);
		customerServiceOrder.setCreateBy(memberId);
		customerServiceOrder.setCreateDate(date);
		customerServiceOrder.setUpdateDate(date);
		//售后状态 0.进行中 1已完成 2已拒绝 3已撤销
		customerServiceOrder.setStatus(Const.CUSTOMER_ORDER_STATUS_REFUNDING);
		customerServiceOrder.setDelFlag("0");
		saveModel(customerServiceOrder);
		
		//2插入售后单状态流水日志表
		CustomerServiceStatusLog customerServiceStatusLog = customerServiceStatusLogService.add(customerServiceOrder.getId(),Const.CUSTOMER_ORDER_STATUS_REFUNDING,proStatus,memberId,remarks,date);
		
		//3售后记录表
		CustomerServiceLog customerServiceLog = customerServiceLogService.add(customerServiceOrder.getId(),Const.OPERATOR_TYPE_MEMBER,quantity,amount,reasonStr,remarks,proStatus,memberId,date,serviceType);
		
		
		//插入图片
		if(!StringUtil.isBlank(pic)){
			String[] picList = pic.split(",");
			for (String str : picList) {
				str = StringUtil.replace(str,"xgbuy.cc");
				
				//4售后单图片表
				customerServicePicService.add(customerServiceOrder.getId(),str,memberId,date,remarks);
				//5售后记录图片表
				serviceLogPicService.add(customerServiceLog.getId(),str,memberId,date,remarks);
			}
		}
		//站内信息
		String title = "";
		String tplType = "";
		String msgType = "";
		if(serviceType.equals(Const.CUSTOMER_ORDER_TYPE_A)){
			title = Const.PLATFORM_MSG_TITLE_A1;
			tplType = Const.MSG_TLP_TYPE_A1;
			msgType = Const.PLATFORM_MSG_TYPE_A1;
		}else if(serviceType.equals(Const.CUSTOMER_ORDER_TYPE_B)){
			title = Const.PLATFORM_MSG_TITLE_A20;
			tplType = Const.MSG_TLP_TYPE_A20;
			msgType = Const.PLATFORM_MSG_TYPE_A2;
		}else{
			title = Const.PLATFORM_MSG_TITLE_A30;
			tplType = Const.MSG_TLP_TYPE_A30;
			msgType = Const.PLATFORM_MSG_TYPE_A3;
		}
		String content = msgTplService.findMsgContent(tplType,customerServiceOrder.getOrderCode(),customerServiceOrder.getId(),subOrder.getSubOrderCode(),subOrder.getId(),customerServiceOrder.getAmount());
		platformMsgService.add(subOrder.getMchtId(),msgType,title,content,customerServiceOrder.getId(),Const.PLATFORM_MSG_STATUS_0);
		return customerServiceOrder;
	}
	
	public void saveModel(CustomerServiceOrder customerServiceOrder) {
		
		customerServiceOrderMapper.insertSelective(customerServiceOrder);
	}
	public void updateCustomerService(Integer memberId, Integer serviceOrderId, String serviceType, String contactPhone,
			String reason, String reasonStr, String remarks, Integer quantity, BigDecimal amount, String pic) {
		Date date = new Date();
		String proStatus = serviceType+"1";
		//1售后单表
		CustomerServiceOrder customerServiceOrder = customerServiceOrderMapper.selectByPrimaryKey(serviceOrderId);
		String status = customerServiceOrder.getStatus();
		String proS = customerServiceOrder.getProStatus();
		String sType = customerServiceOrder.getServiceType();
		if(status.equals(Const.CUSTOMER_ORDER_STATUS_CANCEL)
				|| status.equals(Const.CUSTOMER_ORDER_STATUS_REJECT)){
			throw new ArgException("不能重复申请售后");
		}
		if(customerServiceOrder.getStatus().equals(Const.CUSTOMER_ORDER_STATUS_REFUNDING)){
			if(!proS.equals(Const.CUSTOMER_ORDER_PRO_STATUS_A1) && sType.equals("A")){
				throw new ArgException("不能重复申请售后");
			}else if(!proS.equals(Const.CUSTOMER_ORDER_PRO_STATUS_B1) && sType.equals("B")){
				throw new ArgException("不能重复申请售后");
			}else if(!proS.equals(Const.CUSTOMER_ORDER_PRO_STATUS_C1) && sType.equals("C")){
				throw new ArgException("不能重复申请售后");
			}else if(!proS.equals(Const.CUSTOMER_ORDER_PRO_STATUS_D1) && sType.equals("D")){
				throw new ArgException("不能重复申请售后");
			}
		}
		SubOrder subOrder = subOrderService.findListById(customerServiceOrder.getSubOrderId());
		String orderCode = customerServiceOrder.getOrderCode();
		orderCode = serviceType+orderCode.substring(1);
		customerServiceOrder.setId(serviceOrderId);
		customerServiceOrder.setServiceType(serviceType);
		customerServiceOrder.setOrderCode(orderCode);
		customerServiceOrder.setContactPhone(contactPhone);
		customerServiceOrder.setReason(reason);
		customerServiceOrder.setStatus(Const.CUSTOMER_ORDER_STATUS_REFUNDING);
		customerServiceOrder.setProStatus(proStatus);
		customerServiceOrder.setQuantity(quantity);
		customerServiceOrder.setAmount(amount);
		customerServiceOrder.setRemarks(remarks);
		customerServiceOrder.setUpdateBy(memberId);
		customerServiceOrder.setUpdateDate(date);
		customerServiceOrderMapper.updateByPrimaryKeySelective(customerServiceOrder);
		
		//2插入售后单状态流水日志表
		customerServiceStatusLogService.add(customerServiceOrder.getId(),Const.CUSTOMER_ORDER_STATUS_REFUNDING, 
				proStatus, memberId, remarks, date);
		
		//3售后记录表
		CustomerServiceLog customerServiceLog = customerServiceLogService.add(customerServiceOrder.getId(), Const.OPERATOR_TYPE_MEMBER, 
				quantity, amount, reasonStr, remarks, proStatus, memberId, date, serviceType);
		
		//把售后单图片设置为删除标志1
		CustomerServicePicExample customerServicePicExampleUpdateAll = new CustomerServicePicExample();
		customerServicePicExampleUpdateAll.createCriteria().andServiceOrderIdEqualTo(serviceOrderId).andCreateByEqualTo(memberId);
		CustomerServicePic customerServicePicUpdateAll = new CustomerServicePic();
		customerServicePicUpdateAll.setDelFlag("1");
		customerServicePicService.updateByExampleSelective(customerServicePicUpdateAll, customerServicePicExampleUpdateAll);
		//售后记录图片设置为删除标志1
		ServiceLogPicExample serviceLogPicExampleUpdateAll = new ServiceLogPicExample();
		serviceLogPicExampleUpdateAll.createCriteria().andServiceLogIdEqualTo(customerServiceLog.getId()).andCreateByEqualTo(memberId);
		ServiceLogPic serviceLogPicUpdateAll = new ServiceLogPic();
		serviceLogPicUpdateAll.setDelFlag("1");
		serviceLogPicService.updateByExampleSelective(serviceLogPicUpdateAll, serviceLogPicExampleUpdateAll);
		
		if(!StringUtil.isBlank(pic)){
			String[] picList = pic.split(",");
			for (String customPic : picList) {
				customPic = StringUtil.replace(customPic,"xgbuy.cc");
				
				//根据图片url查找是否存在,存在就把删除标志设置为0，不存在则 循环走下一次
				CustomerServicePicExample customerServicePicExampleUpdate = new CustomerServicePicExample();
				customerServicePicExampleUpdate.createCriteria().andPicEqualTo(customPic).andServiceOrderIdEqualTo(serviceOrderId).andCreateByEqualTo(memberId);
				CustomerServicePic customerServicePicUpdate = new CustomerServicePic();
				customerServicePicUpdate.setDelFlag("0");
				int updateCustomerServicePicCount = customerServicePicService.updateByExampleSelective(customerServicePicUpdate, customerServicePicExampleUpdate);
				if(updateCustomerServicePicCount > 0){
					continue;
				}
				//不存在则做插入操作
				customerServicePicService.add(serviceOrderId, customPic, memberId, date, remarks);
				
			}
			
			for (String str : picList) {
				str = StringUtil.replace(str,"xgbuy.cc");
				//根据图片url查找是否存在,存在就把删除标志设置为0，不存在则 循环走下一次
				ServiceLogPicExample serviceLogPicExampleUpdate = new ServiceLogPicExample();
				serviceLogPicExampleUpdate.createCriteria().andPicEqualTo(str).andServiceLogIdEqualTo(customerServiceLog.getId()).andCreateByEqualTo(memberId);
				ServiceLogPic serviceLogPicUpdate = new ServiceLogPic();
				serviceLogPicUpdate.setDelFlag("0");
				int updateServiceLogPicCount = serviceLogPicService.updateByExampleSelective(serviceLogPicUpdate, serviceLogPicExampleUpdate);
				if(updateServiceLogPicCount > 0){
					continue;
				}
				//不存在则做插入操作
				serviceLogPicService.add(customerServiceLog.getId(), str, memberId, date, remarks);
				
			}
			
		}
		
		//站内信息
		String title = "";
		String tplType = "";
		String msgType = "";
		if(serviceType.equals(Const.CUSTOMER_ORDER_TYPE_A)){
			title = Const.PLATFORM_MSG_TITLE_A1;
			tplType = Const.MSG_TLP_TYPE_A1;
			msgType = Const.PLATFORM_MSG_TYPE_A1;
		}else if(serviceType.equals(Const.CUSTOMER_ORDER_TYPE_B)){
			title = Const.PLATFORM_MSG_TITLE_A20;
			tplType = Const.MSG_TLP_TYPE_A20;
			msgType = Const.PLATFORM_MSG_TYPE_A2;
		}else{
			title = Const.PLATFORM_MSG_TITLE_A30;
			tplType = Const.MSG_TLP_TYPE_A30;
			msgType = Const.PLATFORM_MSG_TYPE_A3;
		}
		String content = msgTplService.findMsgContent(tplType,customerServiceOrder.getOrderCode(),customerServiceOrder.getId(),subOrder.getSubOrderCode(),subOrder.getId(),customerServiceOrder.getAmount());
		platformMsgService.add(subOrder.getMchtId(),msgType,title,content,customerServiceOrder.getId(),Const.PLATFORM_MSG_STATUS_0);
		
	}
	public CustomerServiceOrder findListById(Integer serviceOrderId) {
		
		return customerServiceOrderMapper.selectByPrimaryKey(serviceOrderId);
	}
	/**
	 * 
	 * 方法描述 ：退款成功
	 * @author  chenwf: 
	 * @date 创建时间：2017年7月14日 上午9:21:24 
	 * @version
	 * @param model
	 * @param remark 
	 */
	public void updateRefundSuccess(CustomerServiceOrder model, String remark) {
		String serviceType = model.getServiceType();
		String proStatus = "";
		String content = "退款操作完成，金额将原路返回到客户支付账号上，实际到账时间以在线支付工具和银行结算时间为准。请耐心等待。同时售后完成。";
		// 记录售后单状态流水
		String appContent = "你的"+model.getAmount()+"元有退款进度有更新";
		if(serviceType.equals("A")){
			proStatus = serviceType+"4";
			sysAppMessageService.add(Const.APP_MESSAGE_TYPR_TRAN_MESSAGE,Const.APP_MSG_TITLE_REFUND_SPEED_REMIND,appContent,Const.APP_MESSAGE_SERVICE_ORDER,model.getId(),model.getCreateBy(),1,"0");
		}else if (serviceType.equals("B")) {
			proStatus = serviceType+"7";
			sysAppMessageService.add(Const.APP_MESSAGE_TYPR_TRAN_MESSAGE,Const.APP_MSG_TITLE_REFUND_SPEED_REMIND,appContent,Const.APP_MESSAGE_SERVICE_ORDER,model.getId(),model.getCreateBy(),1,"0");
		}else if (serviceType.equals("D")) {
			proStatus = serviceType+"2";
		}
		
		model.setStatus(Const.CUSTOMER_ORDER_STATUS_SUCCESS);
		model.setProStatus(proStatus);
		model.setRemarks("退款成功");
		update(model);
		customerServiceStatusLogService.save(model.getId(), model.getStatus(), proStatus, remark);
		customerServiceLogService.save(model.getId(),Const.OPERATOR_TYPE_SYSTEM,1,content,remark);
	}
	
	public CustomerServiceOrder update(CustomerServiceOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}
	
}
