package com.jf.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtils;
import com.jf.dao.ViolateOrderCustomMapper;
import com.jf.dao.ViolateOrderMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.ComplainOrderPic;
import com.jf.entity.IntegralDtl;
import com.jf.entity.MchtDeposit;
import com.jf.entity.MchtDepositDtl;
import com.jf.entity.MchtDepositExample;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountExample;
import com.jf.entity.PlatformMsg;
import com.jf.entity.SubOrder;
import com.jf.entity.ViolateComplainOrder;
import com.jf.entity.ViolateOrder;
import com.jf.entity.ViolateOrderCustom;
import com.jf.entity.ViolateOrderCustomExample;
import com.jf.entity.ViolateOrderExample;
import com.jf.entity.ViolatePlatformRemarksPic;
import com.jf.entity.ViolatePlatformRemarksPicExample;
import com.jf.vo.DebitRecord;

@Service
@Transactional
public class ViolateOrderService extends BaseService<ViolateOrder,ViolateOrderExample> {
	@Autowired
	private ViolateOrderMapper dao;
	
	@Autowired
	private ViolateOrderCustomMapper violateOrderCustomMapper;
	
	@Autowired
	private ViolateComplainOrderService violateComplainOrderService;
	
	@Autowired
	private ComplainOrderPicService complainOrderPicService;
	
	@Autowired
	private MchtDepositDtlService mchtDepositDtlService;
	
	@Autowired
	private MchtDepositService mchtDepositService;
	
	@Autowired
	private ViolatePlatformRemarksPicService violatePlatformRemarksPicService;
	
	@Autowired
	private PlatformMsgService platformMsgService;
	
	@Autowired
	private SubOrderService subOrderService;
	
	@Autowired
	private CombineOrderService combineOrderService;
	
	@Autowired
	private MemberAccountService memberAccountService;
	
	@Autowired
	private IntegralDtlService integralDtlService;
	
	@Autowired
	public void setViolateOrderMapper(ViolateOrderMapper violateOrderMapper) {
		super.setDao(violateOrderMapper);
		this.dao = violateOrderMapper;
	}
	
	public void setMinDeductionQuotaNull(Integer id){
		 violateOrderCustomMapper.setMinDeductionQuotaNull(id);
	}
	
	public int countViolateOrderCustomByExample(ViolateOrderCustomExample example){
		return violateOrderCustomMapper.countByExample(example);
	}
	
	public List<ViolateOrderCustom> selectViolateOrderCustomByExample(ViolateOrderCustomExample example){
		return violateOrderCustomMapper.selectByExample(example);
	}

	public void saveViolateComplainOrder(ViolateOrder violateOrder,ViolateComplainOrder violateComplainOrder,List<ComplainOrderPic> complainOrderPics) {
		this.updateByPrimaryKey(violateOrder);
		violateComplainOrderService.insertSelective(violateComplainOrder);
		if(complainOrderPics!=null && complainOrderPics.size()>0){
			for(ComplainOrderPic complainOrderPic:complainOrderPics){
				complainOrderPic.setComplainOrderId(violateComplainOrder.getId());
				complainOrderPicService.insertSelective(complainOrderPic);
			}
		}
	}
	
	public List<DebitRecord> selectDebitRecordList(HashMap<String, Object> paramMap){
		return violateOrderCustomMapper.selectDebitRecordList(paramMap);
	}
	
	public int debitRecordListCount(HashMap<String, Object> paramMap)
	{
		return violateOrderCustomMapper.debitRecordListCount(paramMap);
	}

	public void update(ViolateOrder violateOrder,ViolateComplainOrder violateComplainOrder) {
		this.updateByPrimaryKeySelective(violateOrder);
		if(violateComplainOrder!=null){
			violateComplainOrderService.updateByPrimaryKeySelective(violateComplainOrder);
		}
	}

	public void update(ViolateOrder violateOrder, MchtDeposit mchtDeposit,
			MchtDepositDtl mchtDepositDtl) {
		this.updateByPrimaryKeySelective(violateOrder);
		mchtDepositService.updateByPrimaryKeySelective(mchtDeposit);
		mchtDepositDtlService.insertSelective(mchtDepositDtl);
	}
	
	public void update(ViolateOrder violateOrder, MchtDeposit mchtDeposit,MchtDepositDtl mchtDepositDtl,PlatformMsg platformMsg) {
		this.updateByPrimaryKeySelective(violateOrder);
		mchtDepositService.updateByPrimaryKeySelective(mchtDeposit);
		mchtDepositDtlService.insertSelective(mchtDepositDtl);
		if(platformMsg!=null){
			platformMsgService.insertSelective(platformMsg);
		}
	}

	public void update(ViolateComplainOrder violateComplainOrder,
			ViolateOrder violateOrder, MchtDeposit mchtDeposit,
			MchtDepositDtl mchtDepositDtl) {
		this.updateByPrimaryKeySelective(violateOrder);
		if(violateComplainOrder!=null){
			violateComplainOrderService.updateByPrimaryKeySelective(violateComplainOrder);
		}
		mchtDepositService.updateByPrimaryKeySelective(mchtDeposit);
		mchtDepositDtlService.insertSelective(mchtDepositDtl);
	}

	public List<Map<String, Object>> getAllCreateBy() {
		return violateOrderCustomMapper.getAllCreateBy();
	}
	
	public List<Map<String, Object>> getplatformProcessorList() {
		return violateOrderCustomMapper.getplatformProcessorList();
	}

	public List<HashMap<String,Object>> getMchtInfoBySubOrderCode(String subOrderCode) {
		return violateOrderCustomMapper.getMchtInfoBySubOrderCode(subOrderCode);
	}

	public void save(ViolateOrder violateOrder,String remarkPics) {
		if(violateOrder.getId()!=null){
			this.updateByPrimaryKeySelective(violateOrder);
			ViolatePlatformRemarksPicExample example=new ViolatePlatformRemarksPicExample();
			example.createCriteria().andViolateOrderIdEqualTo(violateOrder.getId()).andDelFlagEqualTo("0");
			ViolatePlatformRemarksPic violatePlatformRemarksPicUpdate=new ViolatePlatformRemarksPic();
			violatePlatformRemarksPicUpdate.setDelFlag("1");
			violatePlatformRemarksPicService.updateByExampleSelective(violatePlatformRemarksPicUpdate, example);
			if(!StringUtils.isEmpty(remarkPics)){
				JSONArray picArray=JSONArray.fromObject(remarkPics);
				Iterator<JSONObject> it= picArray.iterator();
				while (it.hasNext()) {
					JSONObject pic=it.next();
					
					ViolatePlatformRemarksPicExample violatePlatformRemarksPicExample=new ViolatePlatformRemarksPicExample();
					violatePlatformRemarksPicExample.createCriteria().andViolateOrderIdEqualTo(violateOrder.getId()).andPicEqualTo(pic.getString("picPath"));
					ViolatePlatformRemarksPic picUpdate = new ViolatePlatformRemarksPic();
					picUpdate.setDelFlag("0");
					int updateCount = violatePlatformRemarksPicService.updateByExampleSelective(picUpdate, violatePlatformRemarksPicExample);
					if(updateCount>0){
						continue;
					}
					ViolatePlatformRemarksPic violatePlatformRemarksPicTmp = new ViolatePlatformRemarksPic();
					violatePlatformRemarksPicTmp.setViolateOrderId(violateOrder.getId());
					violatePlatformRemarksPicTmp.setPic(pic.getString("picPath"));
					violatePlatformRemarksPicTmp.setCreateBy(violateOrder.getUpdateBy());
					violatePlatformRemarksPicService.insertSelective(violatePlatformRemarksPicTmp);
				}
			}
		}else{
			this.insertSelective(violateOrder);
			if(!StringUtils.isEmpty(remarkPics)){
				JSONArray picArray=JSONArray.fromObject(remarkPics);
				Iterator<JSONObject> it= picArray.iterator();
				while (it.hasNext()) {
					JSONObject pic=it.next();
					ViolatePlatformRemarksPic violatePlatformRemarksPicTmp = new ViolatePlatformRemarksPic();
					violatePlatformRemarksPicTmp.setViolateOrderId(violateOrder.getId());
					violatePlatformRemarksPicTmp.setPic(pic.getString("picPath"));
					violatePlatformRemarksPicTmp.setCreateBy(violateOrder.getUpdateBy());
					violatePlatformRemarksPicService.insertSelective(violatePlatformRemarksPicTmp);
				}
			}
		}
	}

	public void update(ViolateOrder violateOrder,
			ViolateComplainOrder violateComplainOrder, MchtDeposit mchtDeposit,
			MchtDepositDtl mchtDepositDtl, PlatformMsg platformMsg) {
		this.updateByPrimaryKeySelective(violateOrder);
		violateComplainOrderService.updateByPrimaryKeySelective(violateComplainOrder);
		mchtDepositService.updateByPrimaryKeySelective(mchtDeposit);
		mchtDepositDtlService.insertSelective(mchtDepositDtl);
		platformMsgService.insertSelective(platformMsg);
	}

	public void update(ViolateOrder violateOrder,
			ViolateComplainOrder violateComplainOrder, PlatformMsg platformMsg) {
		this.updateByPrimaryKeySelective(violateOrder);
		violateComplainOrderService.updateByPrimaryKeySelective(violateComplainOrder);
		platformMsgService.insertSelective(platformMsg);
	}

	public void update(ViolateOrder violateOrder,
			ViolateComplainOrder violateComplainOrder, PlatformMsg platformMsg,
			List<ComplainOrderPic> complainOrderPics) {
		this.updateByPrimaryKeySelective(violateOrder);
		violateComplainOrderService.updateByPrimaryKeySelective(violateComplainOrder);
		platformMsgService.insertSelective(platformMsg);
		if(complainOrderPics!=null && complainOrderPics.size()>0){
			for(ComplainOrderPic complainOrderPic:complainOrderPics){
				complainOrderPicService.insertSelective(complainOrderPic);
			}
		}
	}
	
	/**
	 * 
	 * @Title updateAgainAuditStatus   
	 * @Description TODO(违规复审)   
	 * @author pengl
	 * @date 2019年2月21日 下午9:41:54
	 */
	public void updateAgainAuditStatus(HashMap<String, String> paramMap, Integer staffID) throws Exception {
		if(!StringUtils.isEmpty(paramMap.get("id")) && !StringUtils.isEmpty(paramMap.get("againAuditStatus"))) {
			Date date = new Date();
			ViolateOrder violateOrder = dao.selectByPrimaryKey(Integer.parseInt(paramMap.get("id")));
			// 修改违规单
			violateOrder.setAgainAuditStatus(paramMap.get("againAuditStatus"));
			violateOrder.setAgainAuditDate(date);
			violateOrder.setAgainAuditBy(staffID);
			violateOrder.setAgainAuditRemarks(paramMap.get("againAuditRemarks"));
			// 修改保证金
			MchtDepositExample mchtDepositExample = new MchtDepositExample();
			mchtDepositExample.setOrderByClause(" id desc");
			mchtDepositExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(violateOrder.getMchtId());
			List<MchtDeposit> mchtDepositList = mchtDepositService.selectByExample(mchtDepositExample);
			// 会员账户信息
			SubOrder subOrder = subOrderService.selectByPrimaryKey(violateOrder.getSubOrderId());
			CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrder.getCombineOrderId());
			MemberAccountExample memberAccountExample = new MemberAccountExample();
			memberAccountExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(combineOrder.getMemberId());
			List<MemberAccount> memberAccountList = memberAccountService.selectByExample(memberAccountExample);
			// 复审失败
			if("2".equals(paramMap.get("againAuditStatus")) && "6".equals(violateOrder.getStatus())) { 
				if(mchtDepositList != null && mchtDepositList.size() > 0) {
					MchtDeposit mchtDeposit = mchtDepositList.get(0);
					if(violateOrder.getMoney() != null) {
						mchtDeposit.setPayAmt(mchtDeposit.getPayAmt().subtract(violateOrder.getMoney()));
						mchtDeposit.setUnpayAmt(mchtDeposit.getUnpayAmt().add(violateOrder.getMoney()));
					}
					mchtDeposit.setUpdateDate(date);
					mchtDeposit.setUpdateBy(staffID);
					mchtDepositService.updateByPrimaryKeySelective(mchtDeposit);
					// 添加保证金明细
					MchtDepositDtl mchtDepositDtl = new MchtDepositDtl();
					mchtDepositDtl.setDepositId(mchtDeposit.getId());
					mchtDepositDtl.setTxnType("E"); // 违规扣款
					if(violateOrder.getViolateType().equals("A")){ // 入驻
						mchtDepositDtl.setTypeSub("E1");
					}else if(violateOrder.getViolateType().equals("B")) { // 发布信息
						mchtDepositDtl.setTypeSub("E2");
					}else if(violateOrder.getViolateType().equals("C")) { // 销售
						mchtDepositDtl.setTypeSub("E3");
					}else if(violateOrder.getViolateType().equals("D")) { // 售后
						mchtDepositDtl.setTypeSub("E4");
					}else if(violateOrder.getViolateType().equals("E")) { // 其它
						mchtDepositDtl.setTypeSub("E5");
					}
					if(violateOrder.getMoney() != null) {
						mchtDepositDtl.setTxnAmt(violateOrder.getMoney().negate());
					}else {
						mchtDepositDtl.setTxnAmt(new BigDecimal(0));
					}
					mchtDepositDtl.setPayAmt(mchtDeposit.getPayAmt());
					mchtDepositDtl.setBizType("2"); // 违规单
					mchtDepositDtl.setBizId(violateOrder.getId());
					mchtDepositDtl.setCreateDate(date);
					mchtDepositDtl.setCreateBy(staffID);
					mchtDepositDtl.setUpdateDate(date);
					mchtDepositDtl.setUpdateBy(staffID);
					String violateTypeDesc = DataDicUtil.getStatusDesc("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_TYPE", violateOrder.getViolateType());
					String violateActionDesc = DataDicUtil.getStatusDesc("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_ACTION", violateOrder.getViolateAction());
					mchtDepositDtl.setRemarks("【"+violateTypeDesc+"】"+violateActionDesc);
					mchtDepositDtlService.insertSelective(mchtDepositDtl);
				}
				// 修改积分	补偿用户积分
				if(violateOrder.getJifenIntegral() != null && violateOrder.getJifenIntegral() > 0) {
					// 商家保证金不足时不给用户赠送积分
					if(mchtDepositList == null || mchtDepositList.size() == 0 || mchtDepositList.get(0).getPayAmt().compareTo(new BigDecimal(0)) < 0) {
						if(!"3".equals(violateOrder.getJifenStatus())){
							violateOrder.setJifenStatus("3");
						}
					}else {
						violateOrder.setJifenStatus("1");
						IntegralDtl integralDtl = new IntegralDtl();
						if(memberAccountList != null && memberAccountList.size() > 0) {
							MemberAccount memberAccount = memberAccountList.get(0);
							integralDtl.setAccId(memberAccount.getId());
							integralDtl.setTallyMode("1"); // 进账
							integralDtl.setType(8); // 系统赠送
							integralDtl.setIntegral(violateOrder.getJifenIntegral());
							if(memberAccount.getIntegral() != null) {
								integralDtl.setBalance(memberAccount.getIntegral().intValue()+violateOrder.getJifenIntegral().intValue());
							}else {
								integralDtl.setBalance(violateOrder.getJifenIntegral());
							}
							integralDtl.setBizType("4"); // 违规单
							integralDtl.setOrderId(violateOrder.getSubOrderId());
							integralDtl.setCreateDate(date);
							integralDtl.setCreateBy(staffID);
							integralDtl.setRemarks("商家违规，系统补偿用户积分");
							integralDtl.setDelFlag("0");
							integralDtlService.insertSelective(integralDtl);
							if(memberAccount.getIntegral() != null) {
								memberAccount.setIntegral(memberAccount.getIntegral()+violateOrder.getJifenIntegral());
							}else{
								memberAccount.setIntegral(violateOrder.getJifenIntegral());
							}
							memberAccount.setUpdateDate(date);
							memberAccount.setUpdateBy(staffID);
							memberAccountService.updateByPrimaryKeySelective(memberAccount);
						}
					}
				}
			}
			// 线下申诉成功
			if("3".equals(paramMap.get("againAuditStatus")) && ("4".equals(violateOrder.getStatus()) || "5".equals(violateOrder.getStatus()))) {
				// 修改保证金
				if(mchtDepositList != null && mchtDepositList.size() > 0) {
					MchtDeposit mchtDeposit = mchtDepositList.get(0);
					mchtDeposit.setId(mchtDeposit.getId());
					if(violateOrder.getMoney() != null) {
						mchtDeposit.setPayAmt(mchtDeposit.getPayAmt().add(violateOrder.getMoney()));
						mchtDeposit.setUnpayAmt(mchtDeposit.getUnpayAmt().subtract(violateOrder.getMoney()));
					}
					mchtDeposit.setUpdateDate(date);
					mchtDeposit.setUpdateBy(staffID);
					mchtDepositService.updateByPrimaryKeySelective(mchtDeposit);
					// 添加保证金明细
					MchtDepositDtl mchtDepositDtl = new MchtDepositDtl();
					mchtDepositDtl.setDepositId(mchtDeposit.getId());
					mchtDepositDtl.setTxnType("F"); // 退保证金
					mchtDepositDtl.setTypeSub("F4"); // 售后
					if(violateOrder.getMoney() != null) {
						mchtDepositDtl.setTxnAmt(violateOrder.getMoney());
					}else {
						mchtDepositDtl.setTxnAmt(new BigDecimal(0));
					}
					mchtDepositDtl.setPayAmt(mchtDeposit.getPayAmt());
					mchtDepositDtl.setBizType("2"); // 违规单
					mchtDepositDtl.setBizId(violateOrder.getId());
					mchtDepositDtl.setCreateDate(date);
					mchtDepositDtl.setCreateBy(staffID);
					mchtDepositDtl.setUpdateDate(date);
					mchtDepositDtl.setUpdateBy(staffID);
					String violateTypeDesc = DataDicUtil.getStatusDesc("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_TYPE", violateOrder.getViolateType());
					String violateActionDesc = DataDicUtil.getStatusDesc("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_ACTION", violateOrder.getViolateAction());
					mchtDepositDtl.setRemarks("【"+violateTypeDesc+"】"+violateActionDesc);
					mchtDepositDtlService.insertSelective(mchtDepositDtl);
				}
				// 修改积分	扣除用户积分
				if(violateOrder.getJifenIntegral() != null && violateOrder.getJifenIntegral() > 0) {
					IntegralDtl integralDtl = new IntegralDtl();
					if(memberAccountList != null && memberAccountList.size() > 0) {
						MemberAccount memberAccount = memberAccountList.get(0);
						// 用户积分不够时，将用户积分清零
						int jifenIntegral = violateOrder.getJifenIntegral();
						int integral = 0;
						if(violateOrder.getJifenIntegral().compareTo(memberAccount.getIntegral()) > 0 ) {
							jifenIntegral = memberAccount.getIntegral().intValue();
						}else {
							integral = memberAccount.getIntegral().intValue() - violateOrder.getJifenIntegral().intValue();
						}
						if(memberAccount.getIntegral() > 0) {
							integralDtl.setAccId(memberAccount.getId());
							integralDtl.setTallyMode("2"); // 出账
							integralDtl.setType(15); // 违规撤销退回
							integralDtl.setIntegral(jifenIntegral);
							integralDtl.setBalance(integral);
							integralDtl.setBizType("4"); // 违规单
							integralDtl.setOrderId(violateOrder.getSubOrderId());
							integralDtl.setCreateDate(date);
							integralDtl.setCreateBy(staffID);
							integralDtl.setRemarks("违规撤销退回，系统扣除用户积分");
							integralDtl.setDelFlag("0");
							integralDtlService.insertSelective(integralDtl);
							memberAccount.setIntegral(integral);
							memberAccount.setUpdateDate(date);
							memberAccount.setUpdateBy(staffID);
							memberAccountService.updateByPrimaryKeySelective(memberAccount);
							violateOrder.setJifenStatus("0"); //未补偿
						}
					}
				}
			}
			dao.updateByPrimaryKeySelective(violateOrder);
		}else {
			throw new Exception("违规单ID为空！");
		}
	}

	public List<ViolateOrderCustom> selectExportViolateOrderCustomByExample(ViolateOrderCustomExample violateOrderCustomExample) {
		return violateOrderCustomMapper.selectExportViolateOrderCustomByExample(violateOrderCustomExample);
	}
	
	
	
	
}
