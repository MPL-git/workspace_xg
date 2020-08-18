package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.OrderAbnormalLogExtMapper;
import com.jf.dao.OrderAbnormalLogMapper;
import com.jf.entity.OrderAbnormalLog;
import com.jf.entity.OrderAbnormalLogExample;
import com.jf.entity.OrderAbnormalLogExt;
import com.jf.entity.OrderAbnormalLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderAbnormalLogBiz extends BaseService<OrderAbnormalLog, OrderAbnormalLogExample> {

	@Autowired
	private OrderAbnormalLogMapper dao;
	@Autowired
	private OrderAbnormalLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setOrderAbnormalLogMapper(OrderAbnormalLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public OrderAbnormalLogExt save(OrderAbnormalLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public OrderAbnormalLog update(OrderAbnormalLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		OrderAbnormalLog model = new OrderAbnormalLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public OrderAbnormalLogExt findById(int id){
		return extDao.findById(id);
	}

	public OrderAbnormalLogExt find(OrderAbnormalLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(OrderAbnormalLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, OrderAbnormalLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, OrderAbnormalLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, OrderAbnormalLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(OrderAbnormalLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<OrderAbnormalLogExt> list(OrderAbnormalLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<OrderAbnormalLogExt> paginate(OrderAbnormalLogExtExample example) {
		List<OrderAbnormalLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
