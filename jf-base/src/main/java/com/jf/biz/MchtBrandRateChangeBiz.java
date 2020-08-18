package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBrandRateChangeExtMapper;
import com.jf.dao.MchtBrandRateChangeMapper;
import com.jf.entity.MchtBrandRateChange;
import com.jf.entity.MchtBrandRateChangeExample;
import com.jf.entity.MchtBrandRateChangeExt;
import com.jf.entity.MchtBrandRateChangeExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtBrandRateChangeBiz extends BaseService<MchtBrandRateChange, MchtBrandRateChangeExample> {

	@Autowired
	private MchtBrandRateChangeMapper dao;
	@Autowired
	private MchtBrandRateChangeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtBrandRateChangeMapper(MchtBrandRateChangeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtBrandRateChangeExt save(MchtBrandRateChangeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtBrandRateChange update(MchtBrandRateChange model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtBrandRateChange model = new MchtBrandRateChange();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtBrandRateChangeExt findById(int id){
		return extDao.findById(id);
	}

	public MchtBrandRateChangeExt find(MchtBrandRateChangeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtBrandRateChangeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtBrandRateChangeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtBrandRateChangeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtBrandRateChangeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtBrandRateChangeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtBrandRateChangeExt> list(MchtBrandRateChangeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtBrandRateChangeExt> paginate(MchtBrandRateChangeExtExample example) {
		List<MchtBrandRateChangeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
