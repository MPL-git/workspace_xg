package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.IntellectualPropertyRightExtMapper;
import com.jf.dao.IntellectualPropertyRightMapper;
import com.jf.entity.IntellectualPropertyRight;
import com.jf.entity.IntellectualPropertyRightExample;
import com.jf.entity.IntellectualPropertyRightExt;
import com.jf.entity.IntellectualPropertyRightExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IntellectualPropertyRightBiz extends BaseService<IntellectualPropertyRight, IntellectualPropertyRightExample> {

	@Autowired
	private IntellectualPropertyRightMapper dao;
	@Autowired
	private IntellectualPropertyRightExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setIntellectualPropertyRightMapper(IntellectualPropertyRightMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public IntellectualPropertyRightExt save(IntellectualPropertyRightExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public IntellectualPropertyRight update(IntellectualPropertyRight model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		IntellectualPropertyRight model = new IntellectualPropertyRight();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public IntellectualPropertyRightExt findById(int id){
		return extDao.findById(id);
	}

	public IntellectualPropertyRightExt find(IntellectualPropertyRightExtExample example){
		return extDao.find(example.fill());
	}

	public int count(IntellectualPropertyRightExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, IntellectualPropertyRightExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, IntellectualPropertyRightExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, IntellectualPropertyRightExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(IntellectualPropertyRightExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<IntellectualPropertyRightExt> list(IntellectualPropertyRightExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<IntellectualPropertyRightExt> paginate(IntellectualPropertyRightExtExample example) {
		List<IntellectualPropertyRightExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
