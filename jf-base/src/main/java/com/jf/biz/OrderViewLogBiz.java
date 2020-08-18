package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.OrderViewLogExtMapper;
import com.jf.dao.OrderViewLogMapper;
import com.jf.entity.OrderViewLog;
import com.jf.entity.OrderViewLogExample;
import com.jf.entity.OrderViewLogExt;
import com.jf.entity.OrderViewLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderViewLogBiz extends BaseService<OrderViewLog, OrderViewLogExample> {

	@Autowired
	private OrderViewLogMapper dao;
	@Autowired
	private OrderViewLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setOrderViewLogMapper(OrderViewLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public OrderViewLogExt save(OrderViewLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public OrderViewLog update(OrderViewLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		OrderViewLog model = new OrderViewLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public OrderViewLogExt findById(int id){
		return extDao.findById(id);
	}

	public OrderViewLogExt find(OrderViewLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(OrderViewLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, OrderViewLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, OrderViewLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, OrderViewLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(OrderViewLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<OrderViewLogExt> list(OrderViewLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<OrderViewLogExt> paginate(OrderViewLogExtExample example) {
		List<OrderViewLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
