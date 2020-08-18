package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SpreadNameExtMapper;
import com.jf.dao.SpreadNameMapper;
import com.jf.entity.SpreadName;
import com.jf.entity.SpreadNameExample;
import com.jf.entity.SpreadNameExt;
import com.jf.entity.SpreadNameExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SpreadNameBiz extends BaseService<SpreadName, SpreadNameExample> {

	@Autowired
	private SpreadNameMapper dao;
	@Autowired
	private SpreadNameExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSpreadNameMapper(SpreadNameMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SpreadNameExt save(SpreadNameExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SpreadName update(SpreadName model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SpreadName model = new SpreadName();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SpreadNameExt findById(int id){
		return extDao.findById(id);
	}

	public SpreadNameExt find(SpreadNameExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SpreadNameExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SpreadNameExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SpreadNameExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SpreadNameExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SpreadNameExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SpreadNameExt> list(SpreadNameExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SpreadNameExt> paginate(SpreadNameExtExample example) {
		List<SpreadNameExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
