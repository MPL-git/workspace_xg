package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.OrderLogMapper;
import com.jf.entity.OrderLog;
import com.jf.entity.OrderLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderLogService {

	@Autowired
	private OrderLogMapper dao;

	public OrderLog save(int subOrderId, String status, String remark){
		OrderLog model = new OrderLog();
		model.setSubOrderId(subOrderId);
		model.setStatus(status);
		model.setRemarks(remark);
		return save(model);
	}

	public OrderLog findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public OrderLog save(OrderLog model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public OrderLog update(OrderLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		OrderLog model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<OrderLog> findList(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<OrderLog> paginate(QueryObject queryObject) {
		OrderLogExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<OrderLog> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private OrderLogExample builderQuery(QueryObject queryObject) {
		OrderLogExample example = new OrderLogExample();
		OrderLogExample.Criteria criteria = example.createCriteria();
		if(queryObject.getQuery("delFlag") != null){
			criteria.andDelFlagEqualTo(queryObject.getQueryToStr("delFlag"));
		}
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
}
