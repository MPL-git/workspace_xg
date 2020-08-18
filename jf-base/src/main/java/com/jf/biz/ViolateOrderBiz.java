package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ViolateOrderExtMapper;
import com.jf.dao.ViolateOrderMapper;
import com.jf.entity.ViolateOrder;
import com.jf.entity.ViolateOrderExample;
import com.jf.entity.ViolateOrderExt;
import com.jf.entity.ViolateOrderExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ViolateOrderBiz extends BaseService<ViolateOrder, ViolateOrderExample> {

	@Autowired
	private ViolateOrderMapper dao;
	@Autowired
	private ViolateOrderExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setViolateOrderMapper(ViolateOrderMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ViolateOrderExt save(ViolateOrderExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ViolateOrder update(ViolateOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ViolateOrder model = new ViolateOrder();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ViolateOrderExt findById(int id){
		return extDao.findById(id);
	}

	public ViolateOrderExt find(ViolateOrderExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ViolateOrderExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ViolateOrderExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ViolateOrderExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ViolateOrderExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ViolateOrderExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ViolateOrderExt> list(ViolateOrderExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ViolateOrderExt> paginate(ViolateOrderExtExample example) {
		List<ViolateOrderExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
