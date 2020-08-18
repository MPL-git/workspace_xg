package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CombineOrderExtMapper;
import com.jf.dao.CombineOrderMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.CombineOrderExample;
import com.jf.entity.CombineOrderExt;
import com.jf.entity.CombineOrderExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CombineOrderBiz extends BaseService<CombineOrder, CombineOrderExample> {

	@Autowired
	private CombineOrderMapper dao;
	@Autowired
	private CombineOrderExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCombineOrderMapper(CombineOrderMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CombineOrderExt save(CombineOrderExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CombineOrder update(CombineOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CombineOrder model = new CombineOrder();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CombineOrderExt findById(int id){
		return extDao.findById(id);
	}

	public CombineOrderExt find(CombineOrderExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CombineOrderExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CombineOrderExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CombineOrderExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CombineOrderExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CombineOrderExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CombineOrderExt> list(CombineOrderExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CombineOrderExt> paginate(CombineOrderExtExample example) {
		List<CombineOrderExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
