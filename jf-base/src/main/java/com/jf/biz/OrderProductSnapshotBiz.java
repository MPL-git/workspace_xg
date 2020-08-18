package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.OrderProductSnapshotExtMapper;
import com.jf.dao.OrderProductSnapshotMapper;
import com.jf.entity.OrderProductSnapshot;
import com.jf.entity.OrderProductSnapshotExample;
import com.jf.entity.OrderProductSnapshotExt;
import com.jf.entity.OrderProductSnapshotExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderProductSnapshotBiz extends BaseService<OrderProductSnapshot, OrderProductSnapshotExample> {

	@Autowired
	private OrderProductSnapshotMapper dao;
	@Autowired
	private OrderProductSnapshotExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setOrderProductSnapshotMapper(OrderProductSnapshotMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public OrderProductSnapshotExt save(OrderProductSnapshotExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public OrderProductSnapshot update(OrderProductSnapshot model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		OrderProductSnapshot model = new OrderProductSnapshot();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public OrderProductSnapshotExt findById(int id){
		return extDao.findById(id);
	}

	public OrderProductSnapshotExt find(OrderProductSnapshotExtExample example){
		return extDao.find(example.fill());
	}

	public int count(OrderProductSnapshotExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, OrderProductSnapshotExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, OrderProductSnapshotExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, OrderProductSnapshotExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(OrderProductSnapshotExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<OrderProductSnapshotExt> list(OrderProductSnapshotExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<OrderProductSnapshotExt> paginate(OrderProductSnapshotExtExample example) {
		List<OrderProductSnapshotExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
