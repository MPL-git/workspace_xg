package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SingleProductActivityControlExtMapper;
import com.jf.dao.SingleProductActivityControlMapper;
import com.jf.entity.SingleProductActivityControl;
import com.jf.entity.SingleProductActivityControlExample;
import com.jf.entity.SingleProductActivityControlExt;
import com.jf.entity.SingleProductActivityControlExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SingleProductActivityControlBiz extends BaseService<SingleProductActivityControl, SingleProductActivityControlExample> {

	@Autowired
	private SingleProductActivityControlMapper dao;
	@Autowired
	private SingleProductActivityControlExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSingleProductActivityControlMapper(SingleProductActivityControlMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SingleProductActivityControlExt save(SingleProductActivityControlExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SingleProductActivityControl update(SingleProductActivityControl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SingleProductActivityControl model = new SingleProductActivityControl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SingleProductActivityControlExt findById(int id){
		return extDao.findById(id);
	}

	public SingleProductActivityControlExt find(SingleProductActivityControlExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SingleProductActivityControlExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SingleProductActivityControlExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SingleProductActivityControlExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SingleProductActivityControlExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SingleProductActivityControlExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SingleProductActivityControlExt> list(SingleProductActivityControlExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SingleProductActivityControlExt> paginate(SingleProductActivityControlExtExample example) {
		List<SingleProductActivityControlExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
