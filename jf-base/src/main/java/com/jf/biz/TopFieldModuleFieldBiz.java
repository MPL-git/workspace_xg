package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.TopFieldModuleFieldExtMapper;
import com.jf.dao.TopFieldModuleFieldMapper;
import com.jf.entity.TopFieldModuleField;
import com.jf.entity.TopFieldModuleFieldExample;
import com.jf.entity.TopFieldModuleFieldExt;
import com.jf.entity.TopFieldModuleFieldExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TopFieldModuleFieldBiz extends BaseService<TopFieldModuleField, TopFieldModuleFieldExample> {

	@Autowired
	private TopFieldModuleFieldMapper dao;
	@Autowired
	private TopFieldModuleFieldExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setTopFieldModuleFieldMapper(TopFieldModuleFieldMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public TopFieldModuleFieldExt save(TopFieldModuleFieldExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public TopFieldModuleField update(TopFieldModuleField model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		TopFieldModuleField model = new TopFieldModuleField();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public TopFieldModuleFieldExt findById(int id){
		return extDao.findById(id);
	}

	public TopFieldModuleFieldExt find(TopFieldModuleFieldExtExample example){
		return extDao.find(example.fill());
	}

	public int count(TopFieldModuleFieldExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, TopFieldModuleFieldExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, TopFieldModuleFieldExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, TopFieldModuleFieldExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(TopFieldModuleFieldExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<TopFieldModuleFieldExt> list(TopFieldModuleFieldExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<TopFieldModuleFieldExt> paginate(TopFieldModuleFieldExtExample example) {
		List<TopFieldModuleFieldExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
