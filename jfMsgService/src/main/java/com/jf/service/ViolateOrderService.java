package com.jf.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.CombineOrderMapper;
import com.jf.dao.KdnWuliuInfoMapper;
import com.jf.dao.MchtContactMapper;
import com.jf.dao.MchtDepositDtlMapper;
import com.jf.dao.MchtDepositMapper;
import com.jf.dao.MemberInfoMapper;
import com.jf.dao.PlatformMsgMapper;
import com.jf.dao.SubOrderMapper;
import com.jf.dao.ViolateComplainOrderMapper;
import com.jf.dao.ViolateOrderCustomMapper;
import com.jf.dao.ViolateOrderMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.IntegralDtl;
import com.jf.entity.KdnWuliuInfo;
import com.jf.entity.KdnWuliuInfoExample;
import com.jf.entity.MchtContact;
import com.jf.entity.MchtContactExample;
import com.jf.entity.MchtDeposit;
import com.jf.entity.MchtDepositDtl;
import com.jf.entity.MchtDepositExample;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.PlatformMsg;
import com.jf.entity.Sms;
import com.jf.entity.SubOrder;
import com.jf.entity.SysStatus;
import com.jf.entity.SysStatusExample;
import com.jf.entity.ViolateOrder;
import com.jf.entity.ViolateOrderCustomExample;
import com.jf.entity.ViolateOrderExample;
@Service
@Transactional
public class ViolateOrderService extends BaseService<ViolateOrder, ViolateOrderExample> {
	private static Logger logger = LoggerFactory.getLogger(ViolateOrderService.class);
	
	@Autowired
	private ViolateOrderMapper dao;
	
	@Autowired
	private ViolateComplainOrderMapper violateComplainOrderMapper;
	
	@Autowired
	private ViolateOrderCustomMapper violateOrderCustomMapper;
	
	@Autowired
	private SubOrderMapper subOrderMapper;
	
	@Autowired
	private CombineOrderMapper combineOrderMapper;
	
	@Autowired
	private MemberAccountService memberAccountService;
	
	@Autowired
	private MemberInfoMapper memberInfoMapper;
	
	@Autowired
	private PlatformMsgMapper platformMsgMapper;
	
	@Autowired
	private MchtDepositMapper mchtDepositMapper;
	
	@Autowired
	private MchtContactMapper mchtContactMapper;
	
	@Autowired
	private MchtDepositDtlMapper mchtDepositDtlMapper;
	
	@Autowired
	private KdnWuliuInfoMapper kdnWuliuInfoMapper;
	
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private SysStatusService sysStatusService;
	
	private static Map<String, SysStatus> sysStatusMap=new HashMap<String, SysStatus>();
	
	@Autowired
	public void setViolateOrderMapper(ViolateOrderMapper violateOrderMapper) {
		this.setDao(violateOrderMapper);
		dao = violateOrderMapper;
	}
	
	
	
	public ViolateOrder findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public ViolateOrder save(ViolateOrder model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public ViolateOrder update(ViolateOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		ViolateOrder model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<ViolateOrder> findList(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<ViolateOrder> paginate(QueryObject queryObject) {
		ViolateOrderExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<ViolateOrder> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private ViolateOrderExample builderQuery(QueryObject queryObject) {
		ViolateOrderExample example = new ViolateOrderExample();
		ViolateOrderExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("statusIn") != null){
			List<String> statusIn = queryObject.getQuery("statusIn");
			criteria.andStatusIn(statusIn);
		}
		if(queryObject.getQuery("complainEndDateIsNotNull") != null){
			criteria.andComplainEndDateIsNotNull();
		}
		if(queryObject.getQuery("auditStatus") != null){
			criteria.andAuditStatusEqualTo(queryObject.getQueryToStr("auditStatus"));
		}
		if(queryObject.getQuery("complainEndDateBeforeNow") != null){
			criteria.andComplainEndDateLessThan(new Date());
		}
		if(queryObject.getQuery("violateType") != null){
			criteria.andViolateTypeEqualTo(queryObject.getQueryToStr("violateType"));
		}
		if(queryObject.getQuery("violateAction") != null){
			criteria.andViolateActionEqualTo(queryObject.getQueryToStr("violateAction"));
		}
		if(queryObject.getQuery("createDateInDays") != null){
			int deliveryDateBeforDays = queryObject.getQueryToInt("createDateInDays");
			criteria.andCreateDateGreaterThanOrEqualTo(DateTime.now().minusDays(deliveryDateBeforDays).toDate());
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

	public void updateStatus(ViolateOrder violateOrder) {
		if(violateOrder.getStatus().equals("1")){//1.未申诉-->4.超期未申诉
			violateOrder.setStatus("4");
			violateOrder.setStatusDate(new Date());
			//需要发放积分
//			dealWithIntegral(violateOrder);//STORY #1430
//			//需发送站内信
//			savePlatFormMsg(violateOrder);
		}else if(violateOrder.getStatus().equals("3")){//3.申诉中-->如果违规申诉单没人审核则不改变状态,即不处理，如果违规单申诉资料状态为待补充（即违规申诉单已被驳回），则违规单状态为申诉失败(5)
			if(violateOrder.getComplainInfoStatus()!=null && violateOrder.getComplainInfoStatus().equals("1")){//申诉资料状态 1.待补充
				violateOrder.setStatus("5");//申诉失败
				violateOrder.setStatusDate(new Date());
				//需要发放积分
//				dealWithIntegral(violateOrder);//STORY #1430
//				//需发送站内信
//				savePlatFormMsg(violateOrder);
			}
		}
		this.updateByPrimaryKeySelective(violateOrder);
}

	public void dealWithIntegral(ViolateOrder violateOrder) {
		if(violateOrder.getJifenIntegral()!=null && violateOrder.getJifenIntegral()>0){
			if(violateOrder.getJifenStatus()!=null && (violateOrder.getJifenStatus().equals("0")||violateOrder.getJifenStatus().equals("3"))){
				IntegralDtl integralDtl = new IntegralDtl();
				if(violateOrder.getSubOrderId()!=null && violateOrder.getSubOrderId()!=0){
					SubOrder subOrder = subOrderMapper.selectByPrimaryKey(violateOrder.getSubOrderId());
					
					
					//商家保证金不足时不给用户赠送积分
					MchtDepositExample mchtDepositExample=new MchtDepositExample();
					mchtDepositExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(subOrder.getMchtId());
					List<MchtDeposit> mchtDeposits=mchtDepositMapper.selectByExample(mchtDepositExample);
					if(mchtDeposits==null||mchtDeposits.size()==0||mchtDeposits.get(0).getPayAmt().compareTo(new BigDecimal(0))<0){
						if(!"3".equals(violateOrder.getJifenStatus())){
							violateOrder.setJifenStatus("3");
						}
						dao.updateByPrimaryKeySelective(violateOrder);
						return;
					}
					
					
					
					CombineOrder combineOrder = combineOrderMapper.selectByPrimaryKey(subOrder.getCombineOrderId());
					MemberAccountExample e = new MemberAccountExample();
					MemberAccountExample.Criteria c = e.createCriteria();
					c.andDelFlagEqualTo("0");
					c.andMemberIdEqualTo(combineOrder.getMemberId());
					List<MemberAccount> memberAccounts = memberAccountService.selectByExample(e);
					MemberAccount memberAccount = new MemberAccount();
					if(memberAccounts!=null && memberAccounts.size()>0){
						memberAccount = memberAccounts.get(0);
					}
					integralDtl.setAccId(memberAccount.getId());
					integralDtl.setTallyMode("1");//进账
					integralDtl.setType(8);//违规补偿
					integralDtl.setIntegral(violateOrder.getJifenIntegral());
					if(memberAccount.getIntegral()!=null){
						integralDtl.setBalance(memberAccount.getIntegral()+violateOrder.getJifenIntegral());
					}else{
						integralDtl.setBalance(violateOrder.getJifenIntegral());
					}
					integralDtl.setOrderId(violateOrder.getSubOrderId());
					integralDtl.setCreateDate(new Date());
					integralDtl.setRemarks("商家违规，系统补偿用户积分");
					integralDtl.setDelFlag("0");
					if(memberAccount.getIntegral() != null){
						memberAccount.setIntegral(memberAccount.getIntegral()+violateOrder.getJifenIntegral());
					}else{
						memberAccount.setIntegral(violateOrder.getJifenIntegral());
					}
					memberAccount.setUpdateDate(new Date());
					violateOrder.setJifenStatus("1");
					memberAccountService.update(violateOrder,memberAccount, integralDtl);
					
					
					//发送短信
					MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(combineOrder.getMemberId());
					String mobile=null;
					if(memberInfo!=null && StringUtil.isMobile(memberInfo.getMobile())){
						mobile=memberInfo.getMobile();
					}else{
						mobile=combineOrder.getReceiverPhone();
					}
					
					if (StringUtil.isMobile(mobile)) {
						Sms sms = new Sms();
						sms.setMobile(mobile);
						sms.setSmsType("3");
						sms.setContent("非常抱歉，您的订单未能及时配送。我们真诚赠送"+violateOrder.getJifenIntegral()+"个积分作为补偿，醒购感谢您的支持。");
						sms.setBizId(violateOrder.getId());
						sms.setMemberId(memberInfo.getId());
						sms.setSendStatus("0");
						sms.setCreateDate(new Date());
						sms.setSendCount(0);
						sms.setUpdateDate(new Date());
						sms.setDelFlag("0");
						smsService.insertSelective(sms);
					}else{
						logger.info("用户手机号不正确，不赠送积分。(违规单赠送积分)");
					}
				}
			}
		}else{
			dao.updateByPrimaryKeySelective(violateOrder);
		}
	}
	
	public void savePlatFormMsg(ViolateOrder violateOrder) {
		String violateOrderStatusDesc = "";
		if(violateOrder.getStatus().equals("4")){//超期未申诉
			violateOrderStatusDesc = "超期未申诉";
		}else if(violateOrder.getStatus().equals("5")){//申诉失败
			violateOrderStatusDesc = "申诉失败";
		}
		PlatformMsg platformMsg = new PlatformMsg();
		platformMsg.setMchtId(violateOrder.getMchtId());
		platformMsg.setMsgType("A5");//违规
		platformMsg.setTitle("关于违规{"+violateOrderStatusDesc+"}的通知");
		String content = "";
		if(violateOrder.getSubOrderId() == null || violateOrder.getSubOrderId() == 0){
			content = "您好！您有1条违规状态更新为{"+violateOrderStatusDesc+"}，违规单编号《<a href=\""+"javascript:;\""+"onclick="+"\"showDetail('violateOrder/violateOrderView?id="+violateOrder.getId()+"')\""+">"+violateOrder.getOrderCode()+"</a>》，请知悉！";
		}else{
			SubOrder subOrder = subOrderMapper.selectByPrimaryKey(violateOrder.getSubOrderId());
			content = "您好！您有1条违规状态更新为{"+violateOrderStatusDesc+"}，违规单编号《<a href=\""+"javascript:;\""+"onclick="+"\"showDetail('violateOrder/violateOrderView?id="+violateOrder.getId()+"')\""+">"+violateOrder.getOrderCode()+"</a>》，相关订单号：《<a href=\""+"javascript:;\""+"onclick="+"\"showDetail('subOrder/subOrderView?id="+violateOrder.getSubOrderId()+"')\""+">"+subOrder.getSubOrderCode()+"</a>》请知悉！";
		}
		platformMsg.setContent(content);
		
		
		platformMsg.setContent("您好！您有1条违规状态更新为{"+violateOrderStatusDesc+"}，违规单编号《"+violateOrder.getOrderCode()+"》，请知悉！");
		platformMsg.setBizId(violateOrder.getId());
		platformMsg.setStatus("0");//未读
		platformMsg.setCreateDate(new Date());
		platformMsg.setDelFlag("0");
		platformMsgMapper.insertSelective(platformMsg);
	}
	
	public List<ViolateOrder> selectByCustomExample(ViolateOrderCustomExample e) {
		return violateOrderCustomMapper.selectByExample(e);
	}



	public void shamDeliveryOrderAudit(ViolateOrder violateOrder) throws ParseException {
		
		KdnWuliuInfoExample e = new KdnWuliuInfoExample();
		KdnWuliuInfoExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andSubOrderIdEqualTo(violateOrder.getSubOrderId());
		List<KdnWuliuInfo> kdnWuliuInfos = kdnWuliuInfoMapper.selectByExample(e);
		boolean pass = false;
		SubOrder subOrder = subOrderMapper.selectByPrimaryKey(violateOrder.getSubOrderId());
		if(subOrder.getDeliveryDate()!=null){
			Date limitDate = DateUtil.getDateAfter(subOrder.getDeliveryDate(), 2);
			if(kdnWuliuInfos!=null && kdnWuliuInfos.size()>0){
				if(!StringUtil.isEmpty(kdnWuliuInfos.get(0).getTractInfo())){
					JSONArray ja = JSONArray.fromObject(kdnWuliuInfos.get(0).getTractInfo());
					if(ja.size()>1){
						JSONObject jo = (JSONObject)ja.get(0);
						String acceptTime = jo.getString("AcceptTime");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						if(sdf.parse(acceptTime).after(limitDate)){
							pass = true;//4、若子订单有物流信息且物流的第一条时间点>发货时间+48小时,则直接将该违规单审核状态更改为：通过
						}else{
							pass = false;//2、若子订单有物流信息且物流的第一条时间点<发货时间+48小时且存在多条物流信息的，则直接将该违规单审核状态更改为：驳回
						}
					}else{
						pass = true;//3、若子订单有物流信息且物流的第一条时间点<发货时间+48小时且只有一条，则直接将该违规单审核状态更改为：通过
					}
				}else{
					pass = true;//1、若子订单还是没有物流信息，则直接将该违规单审核状态更改为：通过
				}
			}else{
				pass = true;//1、若子订单还是没有物流信息，则直接将该违规单审核状态更改为：通过
			}
		}
		if(pass){
			Date date = new Date();
			violateOrder.setAuditStatus("1");//1.通过
			violateOrder.setAuditDate(date);
			violateOrder.setViolateDate(date);
			if(!violateOrder.getStatus().equals("2")){
				violateOrder.setComplainEndDate(DateUtil.getDateAfter(date, 3)); 
			}
			MchtDepositExample example = new MchtDepositExample();
			MchtDepositExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andMchtIdEqualTo(violateOrder.getMchtId());
			List<MchtDeposit> mchtDeposits = mchtDepositMapper.selectByExample(example);
			if(mchtDeposits!=null && mchtDeposits.size()>0){
				MchtDeposit mchtDeposit = mchtDeposits.get(0);
				MchtDepositDtl mchtDepositDtl = new MchtDepositDtl();
				if(violateOrder.getMoney()!=null){
					mchtDeposit.setPayAmt(mchtDeposit.getPayAmt().subtract(violateOrder.getMoney()));
					mchtDeposit.setUnpayAmt(mchtDeposit.getUnpayAmt().add(violateOrder.getMoney()));
				}else{
					mchtDeposit.setPayAmt(mchtDeposit.getPayAmt());
					mchtDeposit.setUnpayAmt(mchtDeposit.getUnpayAmt());
				}
				mchtDeposit.setUpdateDate(date);
				mchtDepositDtl.setDelFlag("0");
				mchtDepositDtl.setDepositId(mchtDeposit.getId());
				mchtDepositDtl.setTxnType("E");//违规扣款
				if(violateOrder.getViolateType().equals("A")){//入驻
					mchtDepositDtl.setTypeSub("E1");
				}else if(violateOrder.getViolateType().equals("B")){//发布信息
					mchtDepositDtl.setTypeSub("E2");
				}else if(violateOrder.getViolateType().equals("C")){//销售
					mchtDepositDtl.setTypeSub("E3");
				}else if(violateOrder.getViolateType().equals("D")){//售后
					mchtDepositDtl.setTypeSub("E4");
				}else if(violateOrder.getViolateType().equals("E")){//其它
					mchtDepositDtl.setTypeSub("E5");
				}
				if(violateOrder.getMoney()!=null){
					mchtDepositDtl.setTxnAmt(violateOrder.getMoney().negate());
				}else{
					mchtDepositDtl.setTxnAmt(new BigDecimal(0));
				}
				mchtDepositDtl.setPayAmt(mchtDeposit.getPayAmt());
				mchtDepositDtl.setBizType("2");//违规单
				mchtDepositDtl.setBizId(violateOrder.getId());
				mchtDepositDtl.setCreateDate(date);
				mchtDepositDtl.setUpdateDate(date);
				String violateTypeDesc = this.getStatusDesc("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_TYPE", violateOrder.getViolateType());
				String violateActionDesc = this.getStatusDesc("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_ACTION", violateOrder.getViolateAction());
				mchtDepositDtl.setRemarks("【"+violateTypeDesc+"】"+violateActionDesc);
				
				PlatformMsg platformMsg = new PlatformMsg();
				platformMsg.setMchtId(violateOrder.getMchtId());
				platformMsg.setMsgType("A5");//违规
				String statusDesc = this.getStatusDesc("BU_VIOLATE_ORDER", "STATUS", violateOrder.getStatus());
				platformMsg.setTitle("关于虚假发货违规通知");
				String content = "";
				if(violateOrder.getSubOrderId() == null || violateOrder.getSubOrderId() == 0){
					content = "您好！您有1条违规状态更新为{"+statusDesc+"}，违规单编号《<a href=\""+"javascript:;\""+"onclick="+"\"showDetail('violateOrder/violateOrderView?id="+violateOrder.getId()+"')\""+">"+violateOrder.getOrderCode()+"</a>》，请知悉！";
				}else{
					content = "您好！您有1条违规状态更新为{"+statusDesc+"}，违规单编号《<a href=\""+"javascript:;\""+"onclick="+"\"showDetail('violateOrder/violateOrderView?id="+violateOrder.getId()+"')\""+">"+violateOrder.getOrderCode()+"</a>》，相关订单号：《<a href=\""+"javascript:;\""+"onclick="+"\"showDetail('subOrder/subOrderView?id="+violateOrder.getSubOrderId()+"')\""+">"+subOrder.getSubOrderCode()+"</a>》请知悉！";
				}
				platformMsg.setContent(content);
				platformMsg.setBizId(violateOrder.getId());
				platformMsg.setStatus("0");//未读
				platformMsg.setCreateDate(new Date());
				platformMsg.setDelFlag("0");
				this.update(violateOrder,mchtDeposit,mchtDepositDtl,platformMsg);
			}
			//TODO 发送短信
			String toSendMobile = "";
			List<String> contactTypes = new ArrayList<String>();
			contactTypes.add("2");//运营
			contactTypes.add("1");//电商
			contactTypes.add("3");//订单对接人
			for(String contactType:contactTypes){
				MchtContactExample mce = new MchtContactExample();
				MchtContactExample.Criteria mcec = mce.createCriteria();
				mcec.andDelFlagEqualTo("0");
				mcec.andMchtIdEqualTo(violateOrder.getMchtId());
				mcec.andContactTypeEqualTo(contactType);
				List<MchtContact> mchtContacts = mchtContactMapper.selectByExample(mce);
				if(mchtContacts!=null && mchtContacts.size()>0){
					toSendMobile = mchtContacts.get(0).getMobile();
					break;
				}
			}
			if(!StringUtil.isEmpty(toSendMobile)){
				Sms sms = new Sms();
				sms.setMobile(toSendMobile);
				sms.setSmsType("4");
				sms.setContent("您好！您有新的违规："+violateOrder.getOrderCode()+"，请尽快登录平台处理。");
				sms.setBizId(violateOrder.getId());
				sms.setSendStatus("0");
				sms.setCreateDate(new Date());
				sms.setSendCount(0);
				sms.setUpdateDate(new Date());
				sms.setDelFlag("0");
				smsService.insertSelective(sms);
			}
		}
	}
	
	public void update(ViolateOrder violateOrder, MchtDeposit mchtDeposit,MchtDepositDtl mchtDepositDtl,PlatformMsg platformMsg) {
		this.updateByPrimaryKeySelective(violateOrder);
		mchtDepositMapper.updateByPrimaryKeySelective(mchtDeposit);
		mchtDepositDtlMapper.insertSelective(mchtDepositDtl);
		if(platformMsg!=null){
			platformMsgMapper.insertSelective(platformMsg);
		}
	}
	
	public String getStatusDesc(String tableName,String colName,String statusValue){
		SysStatus sysStatus=sysStatusMap.get(tableName+"_"+colName+"_"+statusValue);
		if(sysStatus==null){
			SysStatusExample sysStatusExample=new SysStatusExample();
			sysStatusExample.createCriteria().andTableNameEqualTo(tableName).andColNameEqualTo(colName).andStatusValueEqualTo(statusValue);
			List<SysStatus> sysStatusLst=sysStatusService.selectByExample(sysStatusExample);
			if(sysStatusLst!=null&&sysStatusLst.size()>0){
				sysStatus=sysStatusLst.get(0);
				sysStatusMap.put(tableName+"_"+colName+"_"+statusValue, sysStatus);
			}else{
				sysStatus=new SysStatus();
			}
		}
		return sysStatus.getStatusDesc();
		
	}
}
