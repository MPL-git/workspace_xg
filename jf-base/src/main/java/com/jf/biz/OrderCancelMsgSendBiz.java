package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.OrderCancelMsgSendExtMapper;
import com.jf.dao.OrderCancelMsgSendMapper;
import com.jf.entity.OrderCancelMsgSend;
import com.jf.entity.OrderCancelMsgSendExample;
import com.jf.entity.OrderCancelMsgSendExt;
import com.jf.entity.OrderCancelMsgSendExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderCancelMsgSendBiz extends BaseService<OrderCancelMsgSend, OrderCancelMsgSendExample> {

	@Autowired
	private OrderCancelMsgSendMapper dao;
	@Autowired
	private OrderCancelMsgSendExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setOrderCancelMsgSendMapper(OrderCancelMsgSendMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public OrderCancelMsgSendExt save(OrderCancelMsgSendExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public OrderCancelMsgSend update(OrderCancelMsgSend model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		OrderCancelMsgSend model = new OrderCancelMsgSend();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public OrderCancelMsgSendExt findById(int id){
		return extDao.findById(id);
	}

	public OrderCancelMsgSendExt find(OrderCancelMsgSendExtExample example){
		return extDao.find(example.fill());
	}

	public int count(OrderCancelMsgSendExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, OrderCancelMsgSendExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, OrderCancelMsgSendExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, OrderCancelMsgSendExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(OrderCancelMsgSendExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<OrderCancelMsgSendExt> list(OrderCancelMsgSendExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<OrderCancelMsgSendExt> paginate(OrderCancelMsgSendExtExample example) {
		List<OrderCancelMsgSendExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
