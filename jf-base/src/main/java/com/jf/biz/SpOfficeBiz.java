package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.SpOfficeMapper;
import com.jf.dao.SpOfficeExtMapper;
import com.jf.entity.SpOffice;
import com.jf.entity.SpOfficeExample;
import com.jf.entity.SpOfficeExt;
import com.jf.entity.SpOfficeExtExample;
import com.jf.common.base.BaseService;

@Service
public class SpOfficeBiz extends BaseService<SpOffice, SpOfficeExample> {

	@Autowired
	private SpOfficeMapper dao;
	@Autowired
	private SpOfficeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSpOfficeMapper(SpOfficeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SpOfficeExt save(SpOfficeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SpOffice update(SpOffice model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SpOffice model = new SpOffice();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SpOfficeExt findById(int id){
		return extDao.findById(id);
	}

	public SpOfficeExt find(SpOfficeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SpOfficeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SpOfficeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SpOfficeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SpOfficeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SpOfficeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SpOfficeExt> list(SpOfficeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SpOfficeExt> paginate(SpOfficeExtExample example) {
		List<SpOfficeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
