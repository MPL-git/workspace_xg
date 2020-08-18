package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtSingleActivityCnfExtMapper;
import com.jf.dao.MchtSingleActivityCnfMapper;
import com.jf.entity.MchtSingleActivityCnf;
import com.jf.entity.MchtSingleActivityCnfExample;
import com.jf.entity.MchtSingleActivityCnfExt;
import com.jf.entity.MchtSingleActivityCnfExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtSingleActivityCnfBiz extends BaseService<MchtSingleActivityCnf, MchtSingleActivityCnfExample> {

	@Autowired
	private MchtSingleActivityCnfMapper dao;
	@Autowired
	private MchtSingleActivityCnfExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtSingleActivityCnfMapper(MchtSingleActivityCnfMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtSingleActivityCnfExt save(MchtSingleActivityCnfExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtSingleActivityCnf update(MchtSingleActivityCnf model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtSingleActivityCnf model = new MchtSingleActivityCnf();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtSingleActivityCnfExt findById(int id){
		return extDao.findById(id);
	}

	public MchtSingleActivityCnfExt find(MchtSingleActivityCnfExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtSingleActivityCnfExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtSingleActivityCnfExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtSingleActivityCnfExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtSingleActivityCnfExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtSingleActivityCnfExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtSingleActivityCnfExt> list(MchtSingleActivityCnfExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtSingleActivityCnfExt> paginate(MchtSingleActivityCnfExtExample example) {
		List<MchtSingleActivityCnfExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
