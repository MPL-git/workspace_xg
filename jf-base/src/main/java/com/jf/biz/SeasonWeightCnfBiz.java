package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SeasonWeightCnfExtMapper;
import com.jf.dao.SeasonWeightCnfMapper;
import com.jf.entity.SeasonWeightCnf;
import com.jf.entity.SeasonWeightCnfExample;
import com.jf.entity.SeasonWeightCnfExt;
import com.jf.entity.SeasonWeightCnfExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SeasonWeightCnfBiz extends BaseService<SeasonWeightCnf, SeasonWeightCnfExample> {

	@Autowired
	private SeasonWeightCnfMapper dao;
	@Autowired
	private SeasonWeightCnfExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSeasonWeightCnfMapper(SeasonWeightCnfMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SeasonWeightCnfExt save(SeasonWeightCnfExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SeasonWeightCnf update(SeasonWeightCnf model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SeasonWeightCnf model = new SeasonWeightCnf();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SeasonWeightCnfExt findById(int id){
		return extDao.findById(id);
	}

	public SeasonWeightCnfExt find(SeasonWeightCnfExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SeasonWeightCnfExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SeasonWeightCnfExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SeasonWeightCnfExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SeasonWeightCnfExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SeasonWeightCnfExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SeasonWeightCnfExt> list(SeasonWeightCnfExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SeasonWeightCnfExt> paginate(SeasonWeightCnfExtExample example) {
		List<SeasonWeightCnfExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
