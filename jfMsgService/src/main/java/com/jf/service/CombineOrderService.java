package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CombineOrderCustomMapper;
import com.jf.dao.CombineOrderMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.CombineOrderCustom;
import com.jf.entity.CombineOrderCustomExample;
import com.jf.entity.CombineOrderExample;
import com.jf.entity.SubOrder;
import com.jf.entity.ViolateOrder;
import com.jf.service.allowance.MemberAllowanceUsageService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CombineOrderService extends BaseService<CombineOrder, CombineOrderExample> {

	private static Logger logger = LoggerFactory.getLogger(CombineOrderService.class);

	@Autowired
	private CombineOrderMapper combineOrderMapper;
	@Autowired
	private CombineOrderCustomMapper combineOrderCustomMapper;
	
	@Autowired
	private SubOrderService subOrderService; 
	@Autowired
	private ViolateOrderService violateOrderService;
	@Autowired
	private MemberAllowanceUsageService memberAllowanceUsageService;
	
	@Autowired
	public void setCombineOrderMapper(CombineOrderMapper combineOrderMapper) {
		this.setDao(combineOrderMapper);
		this.combineOrderMapper = combineOrderMapper;
	}

	/**
	 * 订单关闭
	 *
	 * @param model the combine order
	 */
	public void close(CombineOrder model, String reason) {
		logger.info("关闭订单:" + model.getCombineOrderCode());

		//Const.OPERATOR_TYPE_SYSTEM
		model.setStatus(Const.COMBINE_ORDER_STATUS_CANCEL);
		model.setCancelReason(reason);
		model.setCancelType("2");//取消类型，系统自动关闭
		model.setCancelDate(new Date());
		update(model);

		subOrderService.closeByCombileOrderId(model.getId());

		// 津贴使用记录删除
		memberAllowanceUsageService.deleteLog(model.getId());

	}

	/**
	 * 订单超时未发货
     */
	public void deliveryOverDue(CombineOrder model) {
		//生成违规单
		try {
			subOrderService.deliveryOverDueByCombileOrderId(model.getId());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}


	public CombineOrder findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public CombineOrder save(CombineOrder model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public CombineOrder update(CombineOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		CombineOrder model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	/**
	 * 订单付款后48小时未发货并且还未生成违规单的订单
     */
	public List<CombineOrder> findListOfDeliveryOverDue() {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("status", Const.COMBINE_ORDER_STATUS_PAID);
		queryObject.addQuery("payStatus", Const.COMBINE_ORDER_PAY_STATUS_SUCCESS);
		queryObject.addQuery("payDateBeforeHours", 48);

		// 从子订单表查未发货的
		QueryObject subOrderQuery = new QueryObject();
		subOrderQuery.addQuery("status", Const.ORDER_STATUS_PAID);
		subOrderQuery.addQuery("createDateBeforeHours", 48);
		// 过滤掉已生成违规单的（近30天的违规单，考虑性能）
		List<Integer> idNotIn = new ArrayList<>();
		idNotIn.add(0);
		QueryObject violateOrderQuery = new QueryObject();
		violateOrderQuery.addQuery("violateType", Const.VIOLATE_ORDER_TYPE_A1);
		violateOrderQuery.addQuery("createDateInDays", 30);
		List<ViolateOrder> violateOrderList = violateOrderService.findList(violateOrderQuery);
		for(ViolateOrder violateOrder: violateOrderList){
			idNotIn.add(violateOrder.getSubOrderId());
		}
		subOrderQuery.addQuery("idNotIn", idNotIn);

		List<Integer> idIn = new ArrayList<>();
		idIn.add(0);
		List<SubOrder> subOrderList = subOrderService.findList(subOrderQuery);
		for(SubOrder subOrder : subOrderList){
			idIn.add(subOrder.getCombineOrderId());
		}
		queryObject.addQuery("idIn", idIn);

		return findList(queryObject);
	}

	public List<CombineOrder> findList(QueryObject queryObject) {
		CombineOrderExample example = builderQuery(queryObject);
		if(queryObject.getLimitSize() > 0){
			example.setLimitStart(0);
			example.setLimitSize(queryObject.getLimitSize());
		}
		return dao.selectByExample(example);
	}

	public Page<CombineOrder> paginate(QueryObject queryObject) {
		CombineOrderExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<CombineOrder> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private CombineOrderExample builderQuery(QueryObject queryObject) {
		CombineOrderCustomExample example = new CombineOrderCustomExample();
		CombineOrderCustomExample.CombineOrderCustomCriteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("idIn") != null){
			List<Integer> idIn = queryObject.getQuery("idIn");
			criteria.andIdIn(idIn);
		}
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("idGreaterThan") != null){
			criteria.andIdGreaterThan(queryObject.getQueryToInt("idGreaterThan"));
		}
		if(queryObject.getQuery("status") != null){
			criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
		}
		if(queryObject.getQuery("payStatus") != null){
			criteria.andPayStatusEqualTo(queryObject.getQueryToStr("payStatus"));
		}
		// 下单超过多少分钟的订单
		if(queryObject.getQuery("createDateBeforeMinutes") != null){
			int createDateBeforeMinutes = queryObject.getQueryToInt("createDateBeforeMinutes");
			criteria.andCreateDateLessThan(DateTime.now().minusMinutes(createDateBeforeMinutes).toDate());
		}
		// 付款超过多少小时的订单
		if(queryObject.getQuery("payDateBeforeHours") != null){
			int payDateBeforeHours = queryObject.getQueryToInt("payDateBeforeHours");
			criteria.andPayDateLessThan(DateTime.now().minusHours(payDateBeforeHours).toDate());
		}

		if(queryObject.getQuery("existsTrackData") != null){
			criteria.andExistsTrackData();
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

	public List<CombineOrderCustom> getNoPaidOrderList(Map<String, Object> paramsMap) {
		
		return combineOrderCustomMapper.getNoPaidOrderList(paramsMap);
	}

	public CombineOrderCustom getOrderAddressInfo(Integer subOrderId) {
		
		return combineOrderCustomMapper.getOrderAddressInfo(subOrderId);
	}

	public List<CombineOrderCustom> findSuperiorGetFristOrderReward() {
		
		return combineOrderCustomMapper.findSuperiorGetFristOrderReward();
	}

	public CombineOrderCustom getCombineOrderCustomByLogisticCode(String kdnCode, String logisticCode) {

		return combineOrderCustomMapper.getCombineOrderCustomByLogisticCode(kdnCode, logisticCode);
	}

	public List<CombineOrderCustom> getMemberList(Map<String, Object> paramsMap) {

		return combineOrderCustomMapper.getMemberList(paramsMap);
	}
	
	public List<CombineOrderCustom> getCombineOrderList(Map<String, Object> paramsMap) {

		return combineOrderCustomMapper.getCombineOrderList(paramsMap);
	}
}
