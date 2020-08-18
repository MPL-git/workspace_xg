package com.jf.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.ArgException;
import com.jf.common.constant.Const;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.CutPriceOrderDtlCustomMapper;
import com.jf.dao.CutPriceOrderDtlMapper;
import com.jf.entity.CutPriceCnf;
import com.jf.entity.CutPriceCnfDtl;
import com.jf.entity.CutPriceCnfDtlExample;
import com.jf.entity.CutPriceCnfExample;
import com.jf.entity.CutPriceOrder;
import com.jf.entity.CutPriceOrderDtl;
import com.jf.entity.CutPriceOrderDtlCustom;
import com.jf.entity.CutPriceOrderDtlExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.ProductItem;

import net.sf.json.JSONObject;

@Service
@Transactional
public class CutPriceOrderDtlService extends BaseService<CutPriceOrderDtl, CutPriceOrderDtlExample> {
	@Autowired
	private CutPriceOrderDtlMapper cutPriceOrderDtlMapper;
	@Autowired
	private CutPriceOrderDtlCustomMapper cutPriceOrderDtlCustomMapper;
	@Autowired
	private CutPriceCnfService cutPriceCnfService;
	@Autowired
	private CutPriceCnfDtlService cutPriceCnfDtlService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CombineOrderService combineOrderService;
	@Autowired
	private CutPriceOrderService cutPriceOrderService;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private BlackListService blackListService;
	@Autowired
	private CutPriceOrderLogService cutPriceOrderLogService;
	@Autowired
	public void setCutPriceOrderDtlMapper(CutPriceOrderDtlMapper cutPriceOrderDtlMapper) {
		this.setDao(cutPriceOrderDtlMapper);
		this.cutPriceOrderDtlMapper = cutPriceOrderDtlMapper;
	}
	public List<CutPriceOrderDtlCustom> getMemberCutOrderDtlList(Map<String, Object> paramsMap) {
		
		return cutPriceOrderDtlCustomMapper.getMemberCutOrderDtlList(paramsMap);
	}
	public BigDecimal addCutPriceOrderDtl(CutPriceOrder cutPriceOrder, Integer memberId, List<String> rateTypeList, Date date, int memberCutCount) {
		Random rand = new Random();
		String status = cutPriceOrder.getStatus();
		Date createDate = cutPriceOrder.getCreateDate();
		BigDecimal amount = new BigDecimal("0");//当前砍价金额
		Integer singleProductActivityId = cutPriceOrder.getSingleProductActivityId();
		BigDecimal tagPrice = cutPriceOrder.getTagPrice();
		if(status.equals("1") && DateUtil.addDay(createDate, 1).after(date)){
			//砍价中还可以砍价
			BigDecimal cutAmount = new BigDecimal("0");//已砍金额
			CutPriceOrderDtlExample orderDtlExample = new CutPriceOrderDtlExample();
			orderDtlExample.createCriteria().andCutPriceOrderIdEqualTo(cutPriceOrder.getId()).andDelFlagEqualTo("0");
			List<CutPriceOrderDtl> orderDtls = selectByExample(orderDtlExample);
			if(CollectionUtils.isNotEmpty(orderDtls)){
				for (CutPriceOrderDtl cutPriceOrderDtl : orderDtls) {
					if(cutPriceOrderDtl.getMemberId().toString().equals(memberId.toString())){
						throw new ArgException("您已经帮忙砍过咯！");
					}
					cutAmount = cutAmount.add(cutPriceOrderDtl.getAmount());
				}
			}
			//砍价方案
			CutPriceCnfExample cnfExample = new CutPriceCnfExample();
			cnfExample.createCriteria().andSingleProductActivityIdEqualTo(singleProductActivityId).andDelFlagEqualTo("0");
			List<CutPriceCnf> cutPriceCnfs = cutPriceCnfService.selectByExample(cnfExample);
			if(CollectionUtils.isNotEmpty(cutPriceCnfs)){
				CutPriceCnf cutPriceCnf = cutPriceCnfs.get(0);
				CutPriceCnfDtlExample cnfDtlExample = new CutPriceCnfDtlExample();
				cnfDtlExample.createCriteria().andCutPriceCnfIdEqualTo(cutPriceCnf.getId()).andRateTypeIn(rateTypeList).andDelFlagEqualTo("0");
				cnfDtlExample.setOrderByClause("rate_type desc,begin_amount");
				List<CutPriceCnfDtl> cutPriceCnfDtls = cutPriceCnfDtlService.selectByExample(cnfDtlExample);
				for (CutPriceCnfDtl cutPriceCnfDtl : cutPriceCnfDtls) {
					BigDecimal beginAmount = cutPriceCnfDtl.getBeginAmount();
					BigDecimal endAmount = cutPriceCnfDtl.getEndAmount();
					BigDecimal beginRate = cutPriceCnfDtl.getBeginRate();
					BigDecimal endRate = cutPriceCnfDtl.getEndRate();
					Double randRate = beginRate.doubleValue() + ((endRate.doubleValue() - beginRate.doubleValue()) * rand.nextDouble());
					if(beginAmount != null){
						if(cutAmount.compareTo(beginAmount) >= 0 && cutAmount.compareTo(endAmount) < 0){
							amount = tagPrice.multiply(new BigDecimal(randRate)).divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_DOWN);
							break;
						}
					}else{
						amount = tagPrice.multiply(new BigDecimal(randRate)).divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_DOWN);
						break;
					}
				}
				String remarks = amount.toString();
				if(amount.add(cutAmount).compareTo(tagPrice) >= 0){
					amount = tagPrice.subtract(cutAmount);
					//这边砍价已成功，满足砍价金额，先更新砍价单主表状态
					int count = cutPriceOrderService.updateCutOrderStatus(cutPriceOrder.getId());//更新砍价单主表为砍价成功，直接在sql中注入为砍价成功状态
					if(count <= 0){
						return amount;
					}else{
//						com.jf.entity.Product product = productService.selectByPrimaryKey(cutPriceOrder.getProductId());
//						combineOrderService.addSettleAccounts(singleProductActivityId,item,product,cutPriceOrder,date,request);
					}
					//日志
					cutPriceOrderLogService.addCutPriceOrderLog(memberId, cutPriceOrder.getId(), "2", "之前状态"+status+":砍价成功");
				}
				//写入砍价明细
				boolean isNewEnjoy = memberInfoService.getIsNewEnjoy(memberId, null);
				CutPriceOrderDtl cutPriceOrderDtl = new CutPriceOrderDtl();
				cutPriceOrderDtl.setMemberId(memberId);
				cutPriceOrderDtl.setCutPriceOrderId(cutPriceOrder.getId());
				cutPriceOrderDtl.setAmount(amount);
				if(isNewEnjoy && memberCutCount == 0){
					cutPriceOrderDtl.setIsNewMember("1");
				}else{
					cutPriceOrderDtl.setIsNewMember("0");
				}
				cutPriceOrderDtl.setCreateBy(memberId);
				cutPriceOrderDtl.setCreateDate(date);
				cutPriceOrderDtl.setDelFlag("0");
				cutPriceOrderDtl.setRemarks(remarks);
				insertSelective(cutPriceOrderDtl);
			}else{
				throw new ArgException("砍价失败");
			}
		}else if(status.equals("2") || status.equals("4")){
			throw new ArgException("商品已砍到底价");
		}else if(DateUtil.addDay(createDate, 1).before(date) || status.equals("3")){
			throw new ArgException("砍价已失效");
		}else{
			throw new ArgException("砍价已失效");
		}
		return amount;
	}

	/**
	 * 获取助力减价每刀减金额
	 */
	public BigDecimal getAssistancePerCutAmount(Integer singleProductActivityId) {
		List<CutPriceCnf> cnfs = cutPriceCnfService.findModelBySingleActivityId(singleProductActivityId);
		CutPriceCnf cnf = cnfs.get(0);
		List<CutPriceCnfDtl> cnfDtls = cutPriceCnfDtlService.findModelByCnfId(cnf.getId());
		CutPriceCnfDtl cnfDtl = cnfDtls.get(0);
		return cnfDtl.getFixedAmount();
	}
	public void addFriendFinishTask(MemberInfo memberInfo, JSONObject reqDataJson, JSONObject reqPRM) {
		Integer memberId = memberInfo.getId();
		Integer cutOrderId = reqDataJson.getInt("cutOrderId");
		CutPriceOrder cutPriceOrder = cutPriceOrderService.selectByPrimaryKey(cutOrderId);
		if (cutPriceOrder == null) {
			throw new ArgException("活动已失效");
		}
		String activityType = cutPriceOrder.getActivityType();
		String auditStatus = memberInfoService.getMemberAuditStatus(memberId,activityType);
		//来源用户是否被屏蔽
		if(!Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE.equals(activityType)){
			Map<String,Object> black = blackListService.getIsBlack(cutPriceOrder.getMemberId(),"3");
			if((boolean) black.get("isBlack")){
				throw new ArgException("对方已被屏蔽");
			}
		}
		if(auditStatus.equals("1")){
			throw new ArgException("只有新用户或者未被邀请才能帮助好友完成任务哦");
		}
		CutPriceOrderDtl cutPriceOrderDtl = new CutPriceOrderDtl();
		String system = reqPRM.getString("system");
		String proStatus = "0";
		if(Const.WX_XCX.equals(system)){
			if(!"1".equals(memberInfo.getmVerfiyFlag()) || StringUtil.isBlank(memberInfo.getMobile())){
				if (!activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE)) { // 助力大减价不需要绑定手机号，改自需求1794
					throw new ArgException("您未绑定手机，请先绑定手机");
				}
			}
			proStatus = "1";
			cutPriceOrderDtl.setUpdateDate(new Date());
			if (activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_INVITE_FREE_CHARGE)) {
				if ( cutPriceOrder.getId() != null) {
					String stauts = cutPriceOrder.getStatus();
					Date createDate = cutPriceOrder.getCreateDate();
					Date currentDate = new Date();
					if (stauts.equals("1") && DateUtil.addHour(createDate, 24).compareTo(currentDate) >= 0) {
						//不在砍价中的，不进行以下步骤,邀请享免单只有24小时的时限
						//获取已完成邀请人数
						CutPriceOrderDtlExample cutPriceOrderDtlExample = new CutPriceOrderDtlExample();
						cutPriceOrderDtlExample.createCriteria().andCutPriceOrderIdEqualTo(cutPriceOrder.getId()).andStatusEqualTo("1").andDelFlagEqualTo("0");
						int finishInviteNum = countByExample(cutPriceOrderDtlExample) + 1;

						//获取需要请人数
						CutPriceCnfExample cutPriceCnfExample = new CutPriceCnfExample();
						cutPriceCnfExample.createCriteria().andSingleProductActivityIdEqualTo(cutPriceOrder.getSingleProductActivityId()).andDelFlagEqualTo("0");
						cutPriceCnfExample.setLimitStart(0);
						cutPriceCnfExample.setLimitSize(1);
						cutPriceCnfExample.setOrderByClause("id desc");
						List<CutPriceCnf> cnfs = cutPriceCnfService.selectByExample(cutPriceCnfExample);
						if (CollectionUtils.isNotEmpty(cnfs)) {
							CutPriceCnf cnf = cnfs.get(0);
							if (cnf.getInviteTimes() != null && cnf.getInviteTimes() == finishInviteNum) {
								//完成任务，更新主砍价单信息
								cutPriceOrder.setStatus("2");
								cutPriceOrder.setCanOrder("1");
								cutPriceOrder.setUpdateDate(currentDate);
								cutPriceOrderService.updateByPrimaryKeySelective(cutPriceOrder);
								//日志
								cutPriceOrderLogService.addCutPriceOrderLog(memberInfo.getId(), cutPriceOrder.getId(), "2", "砍价成功");
							}
						}
					}
				}
			} else if (activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE)) {
				cutPriceOrderDtl.setAmount(getAssistancePerCutAmount(cutPriceOrder.getSingleProductActivityId()));
				if ("1".equals(cutPriceOrder.getStatus())) {
					//获取已完成邀请人数
					CutPriceOrderDtlExample cutPriceOrderDtlExample = new CutPriceOrderDtlExample();
					cutPriceOrderDtlExample.createCriteria().andCutPriceOrderIdEqualTo(cutPriceOrder.getId()).andStatusEqualTo("1").andDelFlagEqualTo("0");
					int finishInviteNum = countByExample(cutPriceOrderDtlExample) + 1;
					//获取需要请人数
					CutPriceCnfExample cutPriceCnfExample = new CutPriceCnfExample();
					cutPriceCnfExample.createCriteria().andSingleProductActivityIdEqualTo(cutPriceOrder.getSingleProductActivityId()).andDelFlagEqualTo("0");
					cutPriceCnfExample.setLimitStart(0);
					cutPriceCnfExample.setLimitSize(1);
					cutPriceCnfExample.setOrderByClause("id desc");
					List<CutPriceCnf> cnfs = cutPriceCnfService.selectByExample(cutPriceCnfExample);
					if (CollectionUtils.isNotEmpty(cnfs)) {
						CutPriceCnf cnf = cnfs.get(0);
						if (cnf.getMaxInviteTimes() != null && cnf.getMaxInviteTimes() == finishInviteNum) {
							//完成任务，更新主砍价单信息
							cutPriceOrder.setStatus("2");
							cutPriceOrder.setCanOrder("1");
							cutPriceOrder.setUpdateDate(new Date());
							cutPriceOrderService.updateByPrimaryKeySelective(cutPriceOrder);
							//日志
							cutPriceOrderLogService.addCutPriceOrderLog(memberInfo.getId(), cutPriceOrder.getId(), "2", "助力成功");
						}
					}
				}
			}
		}
		
		cutPriceOrderDtl.setMemberId(memberId);
		cutPriceOrderDtl.setCutPriceOrderId(cutOrderId);
		cutPriceOrderDtl.setIsNewMember("1");
		cutPriceOrderDtl.setStatus(proStatus);
		cutPriceOrderDtl.setCreateBy(memberId);
		cutPriceOrderDtl.setCreateDate(new Date());
		cutPriceOrderDtl.setDelFlag("0");
		insertSelective(cutPriceOrderDtl);
	}
	public int getAlreadyInvitedCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cutPriceOrderDtlCustomMapper.getAlreadyInvitedCount(map);
	}
	public List<CutPriceOrderDtlCustom> getInviteDetail(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return cutPriceOrderDtlCustomMapper.getInviteDetail(paramsMap);
	}

	public int findAssistanceNumByOrderId(Integer cutOrderId) {
		CutPriceOrderDtlExample cutPriceOrderDtlExample = new CutPriceOrderDtlExample();
		cutPriceOrderDtlExample.createCriteria().andCutPriceOrderIdEqualTo(cutOrderId).andDelFlagEqualTo("0");
		return countByExample(cutPriceOrderDtlExample);
	}
	public List<CutPriceOrderDtl> findModelByOrderId(Integer cutOrderId,Integer memberId) {
		CutPriceOrderDtlExample cutPriceOrderDtlExample = new CutPriceOrderDtlExample();
		cutPriceOrderDtlExample.createCriteria().andMemberIdEqualTo(memberId).andCutPriceOrderIdEqualTo(cutOrderId).andDelFlagEqualTo("0");
		return selectByExample(cutPriceOrderDtlExample);
	}

	public Map<String, String> getMemberAssistanceStatus(Integer cutOrderId, Integer memberId, Integer assistanceNum, Integer maxInviteTimes, Date beginDate, Date endDate) {
		String memberAssistanceStatus = "";//1立即助力
		String memberAssistanceStatusStr = "";
		String errMsg = "";
		Date currentDate = new Date();

		int assistanceStatus = memberInfoService.getMemberAssistanceStatus(memberId, cutOrderId);
		if (assistanceStatus < 3) {
			if (assistanceNum < maxInviteTimes) {//已邀请人数 > 最大可邀请人数
				if (currentDate.after(beginDate) && currentDate.before(endDate)) {
					List<CutPriceOrderDtl> cutPriceOrderDtls = findModelByOrderId(cutOrderId, memberId);
					if (CollectionUtils.isNotEmpty(cutPriceOrderDtls)) {
						if ("1".equals(cutPriceOrderDtls.get(0).getStatus())) {
							memberAssistanceStatus = "4";
							memberAssistanceStatusStr = "助力已成功";
							errMsg = "邀请已助力完成";
						} else {
							memberAssistanceStatus = "2";
							memberAssistanceStatusStr = "立即下载完成助力";
							errMsg = "请前往下载登陆，为好友助力";
						}
					} else {
						memberAssistanceStatus = "1";
						memberAssistanceStatusStr = "立即帮他助力减价";
					}
				} else {
					memberAssistanceStatus = "5";
					memberAssistanceStatusStr = "任务已超时";
					errMsg = "任务已超时";
				}
			} else {
				memberAssistanceStatus = "3";
				memberAssistanceStatusStr = "助力已完成";
				errMsg = "已助力成功";
			}
		} else {
			memberAssistanceStatus = "6";
			memberAssistanceStatusStr = "我也要助力减价拿";
			errMsg = "只有新用户才能参与助力哦";
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberAssistanceStatus", memberAssistanceStatus);
		map.put("memberAssistanceStatusStr", memberAssistanceStatusStr);
		map.put("errMsg", errMsg);
		return map;
	}

	public List<CutPriceOrderDtl> findMemberAssistanceLog(Integer memberId) {
		return cutPriceOrderDtlCustomMapper.findMemberAssistanceLog(memberId);
	}
}
