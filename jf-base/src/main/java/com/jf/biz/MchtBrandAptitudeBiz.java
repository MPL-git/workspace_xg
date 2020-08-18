package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBrandAptitudeExtMapper;
import com.jf.dao.MchtBrandAptitudeMapper;
import com.jf.entity.MchtBrandAptitude;
import com.jf.entity.MchtBrandAptitudeExample;
import com.jf.entity.MchtBrandAptitudeExt;
import com.jf.entity.MchtBrandAptitudeExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtBrandAptitudeBiz extends BaseService<MchtBrandAptitude, MchtBrandAptitudeExample> {

	@Autowired
	private MchtBrandAptitudeMapper dao;
	@Autowired
	private MchtBrandAptitudeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtBrandAptitudeMapper(MchtBrandAptitudeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtBrandAptitudeExt save(MchtBrandAptitudeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtBrandAptitude update(MchtBrandAptitude model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtBrandAptitude model = new MchtBrandAptitude();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtBrandAptitudeExt findById(int id){
		return extDao.findById(id);
	}

	public MchtBrandAptitudeExt find(MchtBrandAptitudeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtBrandAptitudeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtBrandAptitudeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtBrandAptitudeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtBrandAptitudeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtBrandAptitudeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtBrandAptitudeExt> list(MchtBrandAptitudeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtBrandAptitudeExt> paginate(MchtBrandAptitudeExtExample example) {
		List<MchtBrandAptitudeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
