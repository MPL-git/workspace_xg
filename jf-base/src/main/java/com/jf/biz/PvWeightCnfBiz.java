package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.PvWeightCnfExtMapper;
import com.jf.dao.PvWeightCnfMapper;
import com.jf.entity.PvWeightCnf;
import com.jf.entity.PvWeightCnfExample;
import com.jf.entity.PvWeightCnfExt;
import com.jf.entity.PvWeightCnfExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PvWeightCnfBiz extends BaseService<PvWeightCnf, PvWeightCnfExample> {

	@Autowired
	private PvWeightCnfMapper dao;
	@Autowired
	private PvWeightCnfExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setPvWeightCnfMapper(PvWeightCnfMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public PvWeightCnfExt save(PvWeightCnfExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public PvWeightCnf update(PvWeightCnf model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		PvWeightCnf model = new PvWeightCnf();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public PvWeightCnfExt findById(int id){
		return extDao.findById(id);
	}

	public PvWeightCnfExt find(PvWeightCnfExtExample example){
		return extDao.find(example.fill());
	}

	public int count(PvWeightCnfExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, PvWeightCnfExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, PvWeightCnfExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, PvWeightCnfExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(PvWeightCnfExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<PvWeightCnfExt> list(PvWeightCnfExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<PvWeightCnfExt> paginate(PvWeightCnfExtExample example) {
		List<PvWeightCnfExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
