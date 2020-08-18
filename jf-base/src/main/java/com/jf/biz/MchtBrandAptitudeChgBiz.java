package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBrandAptitudeChgExtMapper;
import com.jf.dao.MchtBrandAptitudeChgMapper;
import com.jf.entity.MchtBrandAptitudeChg;
import com.jf.entity.MchtBrandAptitudeChgExample;
import com.jf.entity.MchtBrandAptitudeChgExt;
import com.jf.entity.MchtBrandAptitudeChgExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtBrandAptitudeChgBiz extends BaseService<MchtBrandAptitudeChg, MchtBrandAptitudeChgExample> {

	@Autowired
	private MchtBrandAptitudeChgMapper dao;
	@Autowired
	private MchtBrandAptitudeChgExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtBrandAptitudeChgMapper(MchtBrandAptitudeChgMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtBrandAptitudeChgExt save(MchtBrandAptitudeChgExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtBrandAptitudeChg update(MchtBrandAptitudeChg model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtBrandAptitudeChg model = new MchtBrandAptitudeChg();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtBrandAptitudeChgExt findById(int id){
		return extDao.findById(id);
	}

	public MchtBrandAptitudeChgExt find(MchtBrandAptitudeChgExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtBrandAptitudeChgExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtBrandAptitudeChgExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtBrandAptitudeChgExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtBrandAptitudeChgExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtBrandAptitudeChgExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtBrandAptitudeChgExt> list(MchtBrandAptitudeChgExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtBrandAptitudeChgExt> paginate(MchtBrandAptitudeChgExtExample example) {
		List<MchtBrandAptitudeChgExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
