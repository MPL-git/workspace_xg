package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.utils.DateUtil;
import com.jf.dao.CutPriceOrderDtlCustomMapper;
import com.jf.dao.CutPriceOrderDtlMapper;
import com.jf.entity.CutPriceCnf;
import com.jf.entity.CutPriceCnfExample;
import com.jf.entity.CutPriceOrder;
import com.jf.entity.CutPriceOrderDtl;
import com.jf.entity.CutPriceOrderDtlExample;
import com.jf.entity.MemberInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CutPriceOrderDtlService extends BaseService<CutPriceOrderDtl, CutPriceOrderDtlExample> {
	@Autowired
	private CutPriceOrderDtlMapper cutPriceOrderDtlMapper;
	@Autowired
	private CutPriceOrderDtlCustomMapper cutPriceOrderDtlCustomMapper;
	@Autowired
	private CutPriceOrderService cutPriceOrderService;
	@Autowired
	private CutPriceCnfService cutPriceCnfService;
	@Autowired
	private CutPriceOrderLogService cutPriceOrderLogService;
	@Autowired
	public void setCutPriceOrderDtlMapper(CutPriceOrderDtlMapper cutPriceOrderDtlMapper) {
		this.setDao(cutPriceOrderDtlMapper);
		this.cutPriceOrderDtlMapper = cutPriceOrderDtlMapper;
	}
	public void updateCutOrderDtlInfo(MemberInfo memberInfo) {
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("memberId", memberInfo.getId());
		paramsMap.put("activityType", Const.PRODUCT_ACTIVITY_TYPE_INVITE_FREE_CHARGE);
		List<CutPriceOrderDtl> list = cutPriceOrderDtlCustomMapper.getMemberInviteOrder(paramsMap);
		if(CollectionUtils.isNotEmpty(list)){
			CutPriceOrderDtl orderDtl = list.get(0);
			int count = cutPriceOrderDtlCustomMapper.updateInviteOrderHelpSuccess(orderDtl);
			if(count <= 0){
				return;
			}
			CutPriceOrder cutPriceOrder = cutPriceOrderService.selectByPrimaryKey(orderDtl.getCutPriceOrderId());
			if(cutPriceOrder != null && cutPriceOrder.getId() != null){
				String stauts = cutPriceOrder.getStatus();
				Date createDate = cutPriceOrder.getCreateDate();
				Date currentDate = new Date();
				if(stauts.equals("1") && DateUtil.addHour(createDate, 24).compareTo(currentDate) >= 0){
					//不在砍价中的，不进行以下步骤,邀请享免单只有6个小时的时限
					//获取已完成邀请人数
					CutPriceOrderDtlExample cutPriceOrderDtlExample = new CutPriceOrderDtlExample();
					cutPriceOrderDtlExample.createCriteria().andCutPriceOrderIdEqualTo(cutPriceOrder.getId()).andStatusEqualTo("1").andDelFlagEqualTo("0");
					int finishInviteNum = countByExample(cutPriceOrderDtlExample);
					
					//获取需要请人数
					CutPriceCnfExample cutPriceCnfExample = new CutPriceCnfExample();
					cutPriceCnfExample.createCriteria().andSingleProductActivityIdEqualTo(cutPriceOrder.getSingleProductActivityId()).andDelFlagEqualTo("0");
					cutPriceCnfExample.setLimitStart(0);
					cutPriceCnfExample.setLimitSize(1);
					cutPriceCnfExample.setOrderByClause("id desc");
					List<CutPriceCnf> cnfs = cutPriceCnfService.selectByExample(cutPriceCnfExample);
					if(CollectionUtils.isNotEmpty(cnfs)){
						CutPriceCnf cnf = cnfs.get(0);
						if(cnf.getInviteTimes() != null && cnf.getInviteTimes() == finishInviteNum){
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
		}
		paramsMap.put("activityType", Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE);
		list = cutPriceOrderDtlCustomMapper.getMemberInviteOrder(paramsMap);
		if(CollectionUtils.isNotEmpty(list)){
			CutPriceOrderDtl orderDtl = list.get(0);
			int count = cutPriceOrderDtlCustomMapper.updateInviteOrderHelpSuccess(orderDtl);
			if(count <= 0){
				return;
			}
			CutPriceOrder cutPriceOrder = cutPriceOrderService.selectByPrimaryKey(orderDtl.getCutPriceOrderId());
			if(cutPriceOrder != null && "1".equals(cutPriceOrder.getStatus())){
				//获取已完成邀请人数
				CutPriceOrderDtlExample cutPriceOrderDtlExample = new CutPriceOrderDtlExample();
				cutPriceOrderDtlExample.createCriteria().andCutPriceOrderIdEqualTo(cutPriceOrder.getId()).andStatusEqualTo("1").andDelFlagEqualTo("0");
				int finishInviteNum = countByExample(cutPriceOrderDtlExample);
				//获取需要请人数
				CutPriceCnfExample cutPriceCnfExample = new CutPriceCnfExample();
				cutPriceCnfExample.createCriteria().andSingleProductActivityIdEqualTo(cutPriceOrder.getSingleProductActivityId()).andDelFlagEqualTo("0");
				cutPriceCnfExample.setLimitStart(0);
				cutPriceCnfExample.setLimitSize(1);
				cutPriceCnfExample.setOrderByClause("id desc");
				List<CutPriceCnf> cnfs = cutPriceCnfService.selectByExample(cutPriceCnfExample);
				if(CollectionUtils.isNotEmpty(cnfs)){
					CutPriceCnf cnf = cnfs.get(0);
					if(cnf.getMaxInviteTimes() != null && cnf.getMaxInviteTimes() == finishInviteNum){
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
	
	
}
