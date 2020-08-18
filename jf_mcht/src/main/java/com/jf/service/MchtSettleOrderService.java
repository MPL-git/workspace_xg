package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtSettleOrderMapper;
import com.jf.entity.MchtSettleOrder;
import com.jf.entity.MchtSettleOrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MchtSettleOrderService {

	@Autowired
	private MchtSettleOrderMapper dao;

	/**
	 * 商家开票
	 */
	public MchtSettleOrder mchtInvoiced(int id, int expressId, String expressNo) {
		MchtSettleOrder model = findById(id);
		if(model.getMchtInvoiceStatus().equals(Const.MCHT_SETTLE_ORDER_MCHT_INVOICE_STATUS_YES)){
			throw new BizException("商家已开过票");
		}

		model.setMchtInvoiceExpressId(expressId);
		model.setMchtInvoiceExpressNo(expressNo);
		model.setMchtInvoiceStatus(Const.MCHT_SETTLE_ORDER_MCHT_INVOICE_STATUS_YES);
		model.setMchtInvoiceDate(new Date());
		update(model);
		return model;
	}

	/**
	 * 商家确认
     */
	public MchtSettleOrder mchtConfirmed(int id) {
		MchtSettleOrder model = findById(id);
		if(!model.getConfirmStatus().equals(Const.MCHT_SETTLE_ORDER_CONFIRM_STATUS_WAIT_MCHT)){
			throw new BizException("确认状态不对， 不能进行确认");
		}

		model.setConfirmStatus(Const.MCHT_SETTLE_ORDER_CONFIRM_STATUS_WAIT_PLAT);
		model.setMchtConfirmDate(new Date());
		update(model);
		return model;
	}


	public MchtSettleOrder findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public MchtSettleOrder save(MchtSettleOrder model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public MchtSettleOrder update(MchtSettleOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		MchtSettleOrder model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<MchtSettleOrder> findList(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<MchtSettleOrder> paginate(QueryObject queryObject) {
		MchtSettleOrderExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<MchtSettleOrder> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private MchtSettleOrderExample builderQuery(QueryObject queryObject) {
		MchtSettleOrderExample example = new MchtSettleOrderExample();
		MchtSettleOrderExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("mchtId") != null){
			criteria.andMchtIdEqualTo(queryObject.getQueryToInt("mchtId"));
		}
		if(queryObject.getQuery("confirmStatus") != null){
			criteria.andConfirmStatusEqualTo(queryObject.getQueryToStr("confirmStatus"));
		}
		if(queryObject.getQuery("payStatus") != null){
			criteria.andConfirmStatusEqualTo(queryObject.getQueryToStr("payStatus"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

}
