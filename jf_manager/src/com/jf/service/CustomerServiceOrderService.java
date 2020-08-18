package com.jf.service;

import com.gzs.common.utils.StringUtil;
import com.jf.common.constant.Const;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.dao.CustomerServiceLogMapper;
import com.jf.dao.CustomerServiceOrderCustomMapper;
import com.jf.dao.CustomerServiceOrderMapper;
import com.jf.dao.CustomerServicePicMapper;
import com.jf.dao.CustomerServiceStatusLogMapper;
import com.jf.dao.MsgTplMapper;
import com.jf.dao.OrderDtlCustomMapper;
import com.jf.dao.PlatformMsgMapper;
import com.jf.dao.ServiceLogPicMapper;
import com.jf.dao.SubOrderCustomMapper;
import com.jf.entity.CustomerServiceLog;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.CustomerServiceOrderCustom;
import com.jf.entity.CustomerServiceOrderExample;
import com.jf.entity.CustomerServicePic;
import com.jf.entity.CustomerServicePicExample;
import com.jf.entity.CustomerServiceStatusLog;
import com.jf.entity.MsgTpl;
import com.jf.entity.MsgTplExample;
import com.jf.entity.OrderDtlCustom;
import com.jf.entity.PlatformMsg;
import com.jf.entity.ServiceLogPic;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CustomerServiceOrderService extends BaseService<CustomerServiceOrder,CustomerServiceOrderExample> {
	@Autowired
	private CustomerServiceOrderMapper customerServiceOrderMapper;
	@Autowired
	private CustomerServiceOrderCustomMapper customerServiceOrderCustomMapper;
	@Autowired
	private SubOrderCustomMapper subOrderCustomMapper;
	@Autowired
	private OrderDtlCustomMapper orderDtlCustomMapper;
	@Autowired
	private CustomerServiceStatusLogMapper customerServiceStatusLogMapper;
	@Autowired
	private CustomerServiceLogMapper customerServiceLogMapper;
	@Autowired
	private CustomerServicePicMapper customerServicePicMapper;
	@Autowired
	private ServiceLogPicMapper serviceLogPicMapper;
	@Autowired
	private MsgTplMapper msgTplMapper;
	@Autowired
	private PlatformMsgMapper platformMsgMapper;
	
	@Autowired
	public void setCustomerServiceOrderMapper(CustomerServiceOrderMapper customerServiceOrderMapper) {
		super.setDao(customerServiceOrderMapper);
		this.customerServiceOrderMapper = customerServiceOrderMapper;
	}
	
	public int countCustomerServiceOrderCustomByExample(CustomerServiceOrderExample example){
		return customerServiceOrderCustomMapper.countByExample(example);
	}
    public List<CustomerServiceOrderCustom> selectCustomerServiceOrderCustomByExample(CustomerServiceOrderExample example){
    	return customerServiceOrderCustomMapper.selectByExample(example);
    }

	public int countCustomerOrderList(HashMap paramMap){
		return customerServiceOrderCustomMapper.countCustomerOrderList(paramMap);
	}
	public List<CustomerServiceOrderCustom> selectCustomerOrderList(HashMap paramMap){
		return customerServiceOrderCustomMapper.selectCustomerOrderList(paramMap);
	}

    public CustomerServiceOrderCustom selectCustomerServiceOrderCustomByPrimaryKey(Integer id){
    	return customerServiceOrderCustomMapper.selectByPrimaryKey(id);
    }
    
    @SuppressWarnings("unchecked")
	public void saveCustomerServiceOrder(HttpServletRequest request) {
    	if(!StringUtil.isEmpty(request.getParameter("orderDtlId"))) {
    		Date date = new Date();
    		// 订单明细
			OrderDtlCustom orderDtlCustom = orderDtlCustomMapper.selectByPrimaryKey(Integer.parseInt(request.getParameter("orderDtlId")));
    		
			CustomerServiceOrder customerServiceOrder = new CustomerServiceOrder();
			customerServiceOrder.setSubOrderId(orderDtlCustom.getSubOrderId());
			customerServiceOrder.setOrderDtlId(orderDtlCustom.getId());
			if(!StringUtil.isEmpty(request.getParameter("serviceType"))) {
				customerServiceOrder.setServiceType(request.getParameter("serviceType"));
			}else {
				customerServiceOrder.setServiceType("A");
			}
			customerServiceOrder.setOrderCode(customerServiceOrder.getServiceType()+CommonUtil.getOrderCode()); //售后单号
			customerServiceOrder.setProStatus(customerServiceOrder.getServiceType()+"1"); //进度状态
			customerServiceOrder.setStatus("0");
			customerServiceOrder.setContactPhone(request.getParameter("contactPhone"));
			customerServiceOrder.setReason(DataDicUtil.getStatusDesc("BU_CUSTOMER_SERVICE_ORDER", "REASON", request.getParameter("reason"))); //售后原因
			customerServiceOrder.setQuantity(orderDtlCustom.getQuantity()); //数量
			customerServiceOrder.setAmount(orderDtlCustom.getPayAmount()); 
			customerServiceOrder.setInitiator("2");
			customerServiceOrder.setCreateBy(orderDtlCustom.getMemberId());
			customerServiceOrder.setCreateDate(date);
			customerServiceOrder.setUpdateDate(date);
			customerServiceOrder.setRemarks(request.getParameter("remarks"));
			customerServiceOrderMapper.insertSelective(customerServiceOrder); //保存售后单
			
			CustomerServiceStatusLog customerServiceStatusLog = new CustomerServiceStatusLog();
			customerServiceStatusLog.setServiceOrderId(customerServiceOrder.getId());
			customerServiceStatusLog.setStatus(customerServiceOrder.getStatus());
			customerServiceStatusLog.setProStatus(customerServiceOrder.getProStatus());
			customerServiceStatusLog.setCreateBy(orderDtlCustom.getMemberId());
			customerServiceStatusLog.setCreateDate(date);
			customerServiceStatusLog.setUpdateDate(date);
			customerServiceStatusLog.setRemarks(customerServiceOrder.getRemarks());
			customerServiceStatusLogMapper.insertSelective(customerServiceStatusLog); //保存售后单状态流水日志
			
			CustomerServiceLog customerServiceLog = new CustomerServiceLog();
			customerServiceLog.setServiceOrderId(customerServiceOrder.getId());
			customerServiceLog.setOperatorType("3");
			String context = "";
			if(customerServiceOrder.getServiceType().equals("A") && customerServiceOrder.getProStatus().equals("A1")){
				context = "<font>申请退款</font><br>"
								+ "申请数量："+customerServiceOrder.getQuantity()+"<br>"
								+ "退款金额："+customerServiceOrder.getAmount()+"<br>"
								+ "退款原因："+customerServiceOrder.getReason()+"<br>"
								+ "原因说明："+customerServiceOrder.getRemarks();
			}else if(customerServiceOrder.getServiceType().equals("B") && customerServiceOrder.getProStatus().equals("B1")){
				context = "<font>申请退货</font><br>"
						+ "申请数量："+customerServiceOrder.getQuantity()+"<br>"
						+ "退款金额："+customerServiceOrder.getAmount()+"<br>"
						+ "退货原因："+customerServiceOrder.getReason()+"<br>"
						+ "原因说明："+customerServiceOrder.getRemarks();
			}else if(customerServiceOrder.getServiceType().equals("C") && customerServiceOrder.getProStatus().equals("C1")){
				context = "<font>申请售后换货</font><br>"
						+ "申请数量："+customerServiceOrder.getQuantity()+"<br>"
						+ "退款原因："+customerServiceOrder.getReason()+"<br>"
						+ "原因说明："+customerServiceOrder.getRemarks();
			}
			customerServiceLog.setContent(context);
			customerServiceLog.setCreateBy(orderDtlCustom.getMemberId());
			customerServiceLog.setCreateDate(date);
			customerServiceLog.setUpdateDate(date);
			customerServiceLog.setRemarks(customerServiceOrder.getRemarks());
			customerServiceLogMapper.insertSelective(customerServiceLog); //保存售后记录表
			
			if(!StringUtil.isEmpty(request.getParameter("customerServicePic"))) {
				JSONArray customerServicePicArray = JSONArray.fromObject(request.getParameter("customerServicePic"));
				Iterator<JSONObject> it = customerServicePicArray.iterator();
				while(it.hasNext()) {
					JSONObject customerServicePic = it.next();
					CustomerServicePic customerPic = new CustomerServicePic();
					customerPic.setServiceOrderId(customerServiceOrder.getId());
					customerPic.setPic(customerServicePic.getString("picPath"));
					customerPic.setCreateBy(orderDtlCustom.getMemberId());
					customerPic.setCreateDate(date);
					customerPic.setUpdateDate(date);
					customerServicePicMapper.insertSelective(customerPic); //保存售后单图片表
					
					ServiceLogPic serviceLogPic = new ServiceLogPic();
					serviceLogPic.setServiceLogId(customerServiceLog.getId());
					serviceLogPic.setPic(customerServicePic.getString("picPath"));
					serviceLogPic.setCreateBy(orderDtlCustom.getMemberId());
					serviceLogPic.setCreateDate(date);
					serviceLogPic.setUpdateDate(date);
					serviceLogPicMapper.insertSelective(serviceLogPic); //保存售后记录图片表
				}
			}
			
			PlatformMsg platformMsg = new PlatformMsg();
			platformMsg.setMchtId(orderDtlCustom.getMchtId());
			String tplType = "";
			if(customerServiceOrder.getServiceType().equals(Const.CUSTOMER_SERVICE_ORDER_SERVICE_TYPE_A)) {
				platformMsg.setMsgType(Const.PLATFORM_MSG_MSG_TYPE_A1);
				platformMsg.setTitle(Const.PLATFORM_MSG_TITLE_A);
				tplType = Const.MSG_TPL_MSG_TYPE_A1;
			}
			if(customerServiceOrder.getServiceType().equals(Const.CUSTOMER_SERVICE_ORDER_SERVICE_TYPE_B)) {
				platformMsg.setMsgType(Const.PLATFORM_MSG_MSG_TYPE_A2);
				platformMsg.setTitle(Const.PLATFORM_MSG_TITLE_B);
				tplType = Const.MSG_TPL_MSG_TYPE_A20;
			}
			if(customerServiceOrder.getServiceType().equals(Const.CUSTOMER_SERVICE_ORDER_SERVICE_TYPE_C)) {
				platformMsg.setMsgType(Const.PLATFORM_MSG_MSG_TYPE_A3);
				platformMsg.setTitle(Const.PLATFORM_MSG_TITLE_C);
				tplType = Const.MSG_TPL_MSG_TYPE_A30;
			}
			String content = this.findMsgContent(tplType, customerServiceOrder.getOrderCode(), customerServiceOrder.getId(), orderDtlCustom.getSubOrderCode(), orderDtlCustom.getSubOrderId(), orderDtlCustom.getPayAmount());
			platformMsg.setContent(content);
			platformMsg.setBizId(customerServiceOrder.getId());
			platformMsg.setCreateBy(orderDtlCustom.getMemberId());
			platformMsg.setCreateDate(date);
			platformMsg.setUpdateDate(date);
			platformMsgMapper.insertSelective(platformMsg); //保存站内信
			
		}
		
    }
    
    
    public String findMsgContent(String tplType, String orderCode,Integer orderCodeId, String subOrderCode,Integer subOrderCodeId, BigDecimal amount) {
		String content = "";
		MsgTplExample tplExample = new MsgTplExample();
		tplExample.createCriteria().andTplTypeEqualTo("A").andMsgTypeEqualTo(tplType).andDelFlagEqualTo("0");
		List<MsgTpl> msgTpls = msgTplMapper.selectByExample(tplExample);
		if(CollectionUtils.isNotEmpty(msgTpls)){
			content = msgTpls.get(0).getContent();
			//售后单
			//您有1条新的退款申请，请及时处理！退款编号《<a href="javascript:void(0)" 
			//onclick="getContent('customerServiceOrder/customerServiceOrderView?id=298')">A201707251703011614</a>》， 
			//退款金额：0.01元，相关订单号：《<a href="javascript:void(0)" 
			//onclick="getContent('subOrder/subOrderView?id=1262')">XGS201707251702294350</a>》。
			//如超过2天未处理，将自动审核通过。
			String url = "";
			if(content.contains("{service_code}")){
				url = "'customerServiceOrder/customerServiceOrderView?id="+orderCodeId+"'";
				orderCode = "《<a href=\"javascript:void(0)\" onclick=\"showDetail("+url+")\">"+orderCode+"</a>》";
				content = content.replace("{service_code}", orderCode);
			}
			if(content.contains("{order_code}")){
				url = "'subOrder/subOrderView?id="+subOrderCodeId+"'";
				subOrderCode = "《<a href=\"javascript:void(0)\" onclick=\"showDetail("+url+")\">"+subOrderCode+"</a>》";
				content = content.replace("{order_code}", subOrderCode);
			}
			if(content.contains("{amount}")){
				content = content.replace("{amount}", amount.toString());
			}
		}
		return content;
	}
    
    
    public void updateCustomerServiceOrder(HttpServletRequest request) {
    	// 售后单
    	CustomerServiceOrderCustom customerServiceOrderCustom = customerServiceOrderCustomMapper.selectByPrimaryKey(Integer.parseInt(request.getParameter("customerServiceOrderId")));
    	// 订单明细
    	OrderDtlCustom orderDtlCustom = orderDtlCustomMapper.selectByPrimaryKey(customerServiceOrderCustom.getOrderDtlId());
    	Date date = new Date();
    	CustomerServiceOrder customerServiceOrder = new CustomerServiceOrder();
		customerServiceOrder.setId(customerServiceOrderCustom.getId());
		customerServiceOrder.setMemberExpressCompany(request.getParameter("memberExpressCompany"));
		customerServiceOrder.setMemberExpressNo(request.getParameter("memberExpressNo"));
		customerServiceOrder.setUpdateBy(orderDtlCustom.getMemberId());
		customerServiceOrder.setUpdateDate(date);
		customerServiceOrder.setProStatus(customerServiceOrderCustom.getServiceType()+"4");//客户已寄件
		customerServiceOrderMapper.updateByPrimaryKeySelective(customerServiceOrder); // 修改售后单
		
		StringBuilder sb = new StringBuilder();
		sb.append("<font>已寄件</font>");
		sb.append("<br>");
		sb.append("<font>快递公司:</font>"+request.getParameter("memberExpressCompany"));
		sb.append("<br>");
		sb.append("<font>快递单号:</font>"+request.getParameter("memberExpressNo"));
		CustomerServiceStatusLog customerServiceStatusLog = new CustomerServiceStatusLog();
		customerServiceStatusLog.setServiceOrderId(customerServiceOrderCustom.getId());
		customerServiceStatusLog.setStatus(customerServiceOrderCustom.getStatus());
		customerServiceStatusLog.setProStatus(customerServiceOrderCustom.getServiceType()+"4");
		customerServiceStatusLog.setCreateBy(orderDtlCustom.getMemberId());
		customerServiceStatusLog.setCreateDate(date);
		customerServiceStatusLog.setUpdateDate(date);
		customerServiceStatusLog.setRemarks(sb.toString());
		customerServiceStatusLogMapper.insertSelective(customerServiceStatusLog); //保存售后单状态流水日志
		
		CustomerServiceLog customerServiceLog = new CustomerServiceLog();
		customerServiceLog.setServiceOrderId(customerServiceOrderCustom.getId());
		customerServiceLog.setOperatorType("3"); //系统
		customerServiceLog.setContent(sb.toString());
		customerServiceLog.setRemarks("系统填写物流信息");
		customerServiceLog.setCreateBy(orderDtlCustom.getMemberId());
		customerServiceLog.setCreateDate(date);
		customerServiceLog.setDelFlag("0");
		customerServiceLogMapper.insertSelective(customerServiceLog); //保存售后记录表
		
		PlatformMsg platformMsg = new PlatformMsg();
		platformMsg.setMchtId(orderDtlCustom.getMchtId());
		String tplType = "";
		if(customerServiceOrderCustom.getServiceType().equals(Const.CUSTOMER_SERVICE_ORDER_SERVICE_TYPE_B)) {
			platformMsg.setMsgType(Const.PLATFORM_MSG_MSG_TYPE_A2);
			platformMsg.setTitle(Const.PLATFORM_MSG_TITLE_B21);
			tplType = Const.MSG_TPL_MSG_TYPE_A21;
		}
		if(customerServiceOrderCustom.getServiceType().equals(Const.CUSTOMER_SERVICE_ORDER_SERVICE_TYPE_C)) {
			platformMsg.setMsgType(Const.PLATFORM_MSG_MSG_TYPE_A3);
			platformMsg.setTitle(Const.PLATFORM_MSG_TITLE_C31);
			tplType = Const.MSG_TPL_MSG_TYPE_A31;
		}
		String content = this.findMsgContent(tplType, customerServiceOrderCustom.getOrderCode(), customerServiceOrderCustom.getId(), orderDtlCustom.getSubOrderCode(), orderDtlCustom.getSubOrderId(), orderDtlCustom.getPayAmount());
		platformMsg.setContent(content);
		platformMsg.setBizId(customerServiceOrderCustom.getId());
		platformMsg.setCreateBy(orderDtlCustom.getMemberId());
		platformMsg.setCreateDate(date);
		platformMsg.setUpdateDate(date);
		platformMsgMapper.insertSelective(platformMsg); //保存站内信
    	
    }

	public List<CustomerServiceOrderCustom> selectCustomServiceOrderCountList(Map<String, Object> paramMap) {
		
		return customerServiceOrderCustomMapper.selectCustomServiceOrderCountList(paramMap);
	}

	public List<CustomerServiceOrderCustom> selectCustomServiceOrderAmountCountList(
			Map<String, Object> paramMap) {
		
		return customerServiceOrderCustomMapper.selectCustomServiceOrderAmountCountList(paramMap);
	}

	public List<HashMap<String, Object>> mchtCustomServiceQuantityList(HashMap<String, Object> paramMap) {
		return customerServiceOrderCustomMapper.mchtCustomServiceQuantityList(paramMap);
	}

	public List<HashMap<String, Object>> mchtCustomServiceAmountList(HashMap<String, Object> paramMap) {
		return customerServiceOrderCustomMapper.mchtCustomServiceAmountList(paramMap);
	}
    
	@SuppressWarnings("unchecked")
	public void againUpdateCustomerServiceOrder(HttpServletRequest request) {
		String customerServiceOrderId = request.getParameter("customerServiceOrderId");
		CustomerServiceOrder customerServiceOrder = this.selectByPrimaryKey(Integer.parseInt(customerServiceOrderId));
		OrderDtlCustom orderDtlCustom = orderDtlCustomMapper.selectByPrimaryKey(customerServiceOrder.getOrderDtlId());
		if(!StringUtil.isEmpty(request.getParameter("serviceType"))) {
			customerServiceOrder.setServiceType(request.getParameter("serviceType"));
		}else {
			customerServiceOrder.setServiceType("A");
		}
		customerServiceOrder.setProStatus(customerServiceOrder.getServiceType()+"1"); //进度状态
		customerServiceOrder.setStatus("0");
		customerServiceOrder.setContactPhone(request.getParameter("contactPhone"));
		customerServiceOrder.setReason(DataDicUtil.getStatusDesc("BU_CUSTOMER_SERVICE_ORDER", "REASON", request.getParameter("reason"))); //售后原因
		customerServiceOrder.setQuantity(orderDtlCustom.getQuantity()); //数量
		customerServiceOrder.setAmount(orderDtlCustom.getPayAmount()); 
		customerServiceOrder.setInitiator("2");//发起人 1 app用户  2 平台人员
		Date date = new Date();
		customerServiceOrder.setUpdateDate(date);
		customerServiceOrder.setRemarks(request.getParameter("remarks"));
		customerServiceOrderMapper.updateByPrimaryKeySelective(customerServiceOrder); // 修改售后单
		
		CustomerServiceStatusLog customerServiceStatusLog = new CustomerServiceStatusLog();
		customerServiceStatusLog.setServiceOrderId(customerServiceOrder.getId());
		customerServiceStatusLog.setStatus(customerServiceOrder.getStatus());
		customerServiceStatusLog.setProStatus(customerServiceOrder.getProStatus());
		customerServiceStatusLog.setCreateBy(orderDtlCustom.getMemberId());
		customerServiceStatusLog.setCreateDate(date);
		customerServiceStatusLog.setUpdateDate(date);
		customerServiceStatusLog.setRemarks(customerServiceOrder.getRemarks());
		customerServiceStatusLogMapper.insertSelective(customerServiceStatusLog);
		
		CustomerServiceLog customerServiceLog = new CustomerServiceLog();
		customerServiceLog.setServiceOrderId(customerServiceOrder.getId());
		customerServiceLog.setOperatorType("3");
		String context = "";
		if(customerServiceOrder.getServiceType().equals("A") && customerServiceOrder.getProStatus().equals("A1")){
			context = "<font>申请退款</font><br>"
							+ "申请数量："+customerServiceOrder.getQuantity()+"<br>"
							+ "退款金额："+customerServiceOrder.getAmount()+"<br>"
							+ "退款原因："+customerServiceOrder.getReason()+"<br>"
							+ "原因说明："+customerServiceOrder.getRemarks();
		}else if(customerServiceOrder.getServiceType().equals("B") && customerServiceOrder.getProStatus().equals("B1")){
			context = "<font>申请退货</font><br>"
					+ "申请数量："+customerServiceOrder.getQuantity()+"<br>"
					+ "退款金额："+customerServiceOrder.getAmount()+"<br>"
					+ "退货原因："+customerServiceOrder.getReason()+"<br>"
					+ "原因说明："+customerServiceOrder.getRemarks();
		}else if(customerServiceOrder.getServiceType().equals("C") && customerServiceOrder.getProStatus().equals("C1")){
			context = "<font>申请售后换货</font><br>"
					+ "申请数量："+customerServiceOrder.getQuantity()+"<br>"
					+ "退款原因："+customerServiceOrder.getReason()+"<br>"
					+ "原因说明："+customerServiceOrder.getRemarks();
		}
		customerServiceLog.setContent(context);
		customerServiceLog.setCreateBy(orderDtlCustom.getMemberId());
		customerServiceLog.setCreateDate(date);
		customerServiceLog.setUpdateDate(date);
		customerServiceLog.setRemarks(customerServiceOrder.getRemarks());
		customerServiceLogMapper.insertSelective(customerServiceLog);
		
		CustomerServicePic csp = new CustomerServicePic();
		csp.setDelFlag("1");
		CustomerServicePicExample example = new CustomerServicePicExample();
		CustomerServicePicExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andServiceOrderIdEqualTo(customerServiceOrder.getId());
		customerServicePicMapper.updateByExampleSelective(csp, example);
		
		if(!StringUtil.isEmpty(request.getParameter("customerServicePic"))) {
			JSONArray customerServicePicArray = JSONArray.fromObject(request.getParameter("customerServicePic"));
			Iterator<JSONObject> it = customerServicePicArray.iterator();
			while(it.hasNext()) {
				JSONObject customerServicePic = it.next();
				CustomerServicePic customerPic = new CustomerServicePic();
				customerPic.setServiceOrderId(customerServiceOrder.getId());
				customerPic.setPic(customerServicePic.getString("picPath"));
				customerPic.setCreateBy(orderDtlCustom.getMemberId());
				customerPic.setCreateDate(date);
				customerPic.setUpdateDate(date);
				customerServicePicMapper.insertSelective(customerPic); //保存售后单图片表
				
				ServiceLogPic serviceLogPic = new ServiceLogPic();
				serviceLogPic.setServiceLogId(customerServiceLog.getId());
				serviceLogPic.setPic(customerServicePic.getString("picPath"));
				serviceLogPic.setCreateBy(orderDtlCustom.getMemberId());
				serviceLogPic.setCreateDate(date);
				serviceLogPic.setUpdateDate(date);
				serviceLogPicMapper.insertSelective(serviceLogPic); //保存售后记录图片表
			}
		}
		
		PlatformMsg platformMsg = new PlatformMsg();
		platformMsg.setMchtId(orderDtlCustom.getMchtId());
		String tplType = "";
		if(customerServiceOrder.getServiceType().equals(Const.CUSTOMER_SERVICE_ORDER_SERVICE_TYPE_A)) {
			platformMsg.setMsgType(Const.PLATFORM_MSG_MSG_TYPE_A1);
			platformMsg.setTitle(Const.PLATFORM_MSG_TITLE_A);
			tplType = Const.MSG_TPL_MSG_TYPE_A1;
		}
		if(customerServiceOrder.getServiceType().equals(Const.CUSTOMER_SERVICE_ORDER_SERVICE_TYPE_B)) {
			platformMsg.setMsgType(Const.PLATFORM_MSG_MSG_TYPE_A2);
			platformMsg.setTitle(Const.PLATFORM_MSG_TITLE_B);
			tplType = Const.MSG_TPL_MSG_TYPE_A20;
		}
		if(customerServiceOrder.getServiceType().equals(Const.CUSTOMER_SERVICE_ORDER_SERVICE_TYPE_C)) {
			platformMsg.setMsgType(Const.PLATFORM_MSG_MSG_TYPE_A3);
			platformMsg.setTitle(Const.PLATFORM_MSG_TITLE_C);
			tplType = Const.MSG_TPL_MSG_TYPE_A30;
		}
		String content = this.findMsgContent(tplType, customerServiceOrder.getOrderCode(), customerServiceOrder.getId(), orderDtlCustom.getSubOrderCode(), orderDtlCustom.getSubOrderId(), orderDtlCustom.getPayAmount());
		platformMsg.setContent(content);
		platformMsg.setBizId(customerServiceOrder.getId());
		platformMsg.setCreateBy(orderDtlCustom.getMemberId());
		platformMsg.setCreateDate(date);
		platformMsg.setUpdateDate(date);
		platformMsgMapper.insertSelective(platformMsg); //保存站内信
    }
}
