package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.GradeWeightCnfExtMapper;
import com.jf.dao.GradeWeightCnfMapper;
import com.jf.entity.GradeWeightCnf;
import com.jf.entity.GradeWeightCnfExample;
import com.jf.entity.GradeWeightCnfExt;
import com.jf.entity.GradeWeightCnfExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GradeWeightCnfBiz extends BaseService<GradeWeightCnf, GradeWeightCnfExample> {

	@Autowired
	private GradeWeightCnfMapper dao;
	@Autowired
	private GradeWeightCnfExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setGradeWeightCnfMapper(GradeWeightCnfMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public GradeWeightCnfExt save(GradeWeightCnfExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public GradeWeightCnf update(GradeWeightCnf model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		GradeWeightCnf model = new GradeWeightCnf();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public GradeWeightCnfExt findById(int id){
		return extDao.findById(id);
	}

	public GradeWeightCnfExt find(GradeWeightCnfExtExample example){
		return extDao.find(example.fill());
	}

	public int count(GradeWeightCnfExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, GradeWeightCnfExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, GradeWeightCnfExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, GradeWeightCnfExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(GradeWeightCnfExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<GradeWeightCnfExt> list(GradeWeightCnfExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<GradeWeightCnfExt> paginate(GradeWeightCnfExtExample example) {
		List<GradeWeightCnfExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
