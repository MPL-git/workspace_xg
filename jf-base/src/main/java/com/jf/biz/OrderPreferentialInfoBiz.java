package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.OrderPreferentialInfoExtMapper;
import com.jf.dao.OrderPreferentialInfoMapper;
import com.jf.entity.OrderPreferentialInfo;
import com.jf.entity.OrderPreferentialInfoExample;
import com.jf.entity.OrderPreferentialInfoExt;
import com.jf.entity.OrderPreferentialInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderPreferentialInfoBiz extends BaseService<OrderPreferentialInfo, OrderPreferentialInfoExample> {

	@Autowired
	private OrderPreferentialInfoMapper dao;
	@Autowired
	private OrderPreferentialInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setOrderPreferentialInfoMapper(OrderPreferentialInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public OrderPreferentialInfoExt save(OrderPreferentialInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public OrderPreferentialInfo update(OrderPreferentialInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		OrderPreferentialInfo model = new OrderPreferentialInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public OrderPreferentialInfoExt findById(int id){
		return extDao.findById(id);
	}

	public OrderPreferentialInfoExt find(OrderPreferentialInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(OrderPreferentialInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, OrderPreferentialInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, OrderPreferentialInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, OrderPreferentialInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(OrderPreferentialInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<OrderPreferentialInfoExt> list(OrderPreferentialInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<OrderPreferentialInfoExt> paginate(OrderPreferentialInfoExtExample example) {
		List<OrderPreferentialInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
