package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.OrderLogExtMapper;
import com.jf.dao.OrderLogMapper;
import com.jf.entity.OrderLog;
import com.jf.entity.OrderLogExample;
import com.jf.entity.OrderLogExt;
import com.jf.entity.OrderLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderLogBiz extends BaseService<OrderLog, OrderLogExample> {

	@Autowired
	private OrderLogMapper dao;
	@Autowired
	private OrderLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setOrderLogMapper(OrderLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public OrderLogExt save(OrderLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public OrderLog update(OrderLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		OrderLog model = new OrderLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public OrderLogExt findById(int id){
		return extDao.findById(id);
	}

	public OrderLogExt find(OrderLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(OrderLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, OrderLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, OrderLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, OrderLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(OrderLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<OrderLogExt> list(OrderLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<OrderLogExt> paginate(OrderLogExtExample example) {
		List<OrderLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
