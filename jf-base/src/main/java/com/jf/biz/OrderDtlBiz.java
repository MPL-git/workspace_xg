package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.OrderDtlExtMapper;
import com.jf.dao.OrderDtlMapper;
import com.jf.entity.OrderDtl;
import com.jf.entity.OrderDtlExample;
import com.jf.entity.OrderDtlExt;
import com.jf.entity.OrderDtlExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderDtlBiz extends BaseService<OrderDtl, OrderDtlExample> {

	@Autowired
	private OrderDtlMapper dao;
	@Autowired
	private OrderDtlExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setOrderDtlMapper(OrderDtlMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public OrderDtlExt save(OrderDtlExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public OrderDtl update(OrderDtl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		OrderDtl model = new OrderDtl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public OrderDtlExt findById(int id){
		return extDao.findById(id);
	}

	public OrderDtlExt find(OrderDtlExtExample example){
		return extDao.find(example.fill());
	}

	public int count(OrderDtlExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, OrderDtlExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, OrderDtlExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, OrderDtlExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(OrderDtlExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<OrderDtlExt> list(OrderDtlExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<OrderDtlExt> paginate(OrderDtlExtExample example) {
		List<OrderDtlExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
