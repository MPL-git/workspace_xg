package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SubOrderExtMapper;
import com.jf.dao.SubOrderMapper;
import com.jf.entity.SubOrder;
import com.jf.entity.SubOrderExample;
import com.jf.entity.SubOrderExt;
import com.jf.entity.SubOrderExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SubOrderBiz extends BaseService<SubOrder, SubOrderExample> {

	@Autowired
	private SubOrderMapper dao;
	@Autowired
	private SubOrderExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSubOrderMapper(SubOrderMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SubOrderExt save(SubOrderExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SubOrder update(SubOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SubOrder model = new SubOrder();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SubOrderExt findById(int id){
		return extDao.findById(id);
	}

	public SubOrderExt find(SubOrderExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SubOrderExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SubOrderExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SubOrderExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SubOrderExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SubOrderExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SubOrderExt> list(SubOrderExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SubOrderExt> paginate(SubOrderExtExample example) {
		List<SubOrderExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
