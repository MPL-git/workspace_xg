package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.IntellectualPropertyRightAppealExtMapper;
import com.jf.dao.IntellectualPropertyRightAppealMapper;
import com.jf.entity.IntellectualPropertyRightAppeal;
import com.jf.entity.IntellectualPropertyRightAppealExample;
import com.jf.entity.IntellectualPropertyRightAppealExt;
import com.jf.entity.IntellectualPropertyRightAppealExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IntellectualPropertyRightAppealBiz extends BaseService<IntellectualPropertyRightAppeal, IntellectualPropertyRightAppealExample> {

	@Autowired
	private IntellectualPropertyRightAppealMapper dao;
	@Autowired
	private IntellectualPropertyRightAppealExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setIntellectualPropertyRightAppealMapper(IntellectualPropertyRightAppealMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public IntellectualPropertyRightAppealExt save(IntellectualPropertyRightAppealExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public IntellectualPropertyRightAppeal update(IntellectualPropertyRightAppeal model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		IntellectualPropertyRightAppeal model = new IntellectualPropertyRightAppeal();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public IntellectualPropertyRightAppealExt findById(int id){
		return extDao.findById(id);
	}

	public IntellectualPropertyRightAppealExt find(IntellectualPropertyRightAppealExtExample example){
		return extDao.find(example.fill());
	}

	public int count(IntellectualPropertyRightAppealExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, IntellectualPropertyRightAppealExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, IntellectualPropertyRightAppealExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, IntellectualPropertyRightAppealExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(IntellectualPropertyRightAppealExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<IntellectualPropertyRightAppealExt> list(IntellectualPropertyRightAppealExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<IntellectualPropertyRightAppealExt> paginate(IntellectualPropertyRightAppealExtExample example) {
		List<IntellectualPropertyRightAppealExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
