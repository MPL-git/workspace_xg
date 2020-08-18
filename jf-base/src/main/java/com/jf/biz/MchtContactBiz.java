package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtContactExtMapper;
import com.jf.dao.MchtContactMapper;
import com.jf.entity.MchtContact;
import com.jf.entity.MchtContactExample;
import com.jf.entity.MchtContactExt;
import com.jf.entity.MchtContactExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtContactBiz extends BaseService<MchtContact, MchtContactExample> {

	@Autowired
	private MchtContactMapper dao;
	@Autowired
	private MchtContactExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtContactMapper(MchtContactMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtContactExt save(MchtContactExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtContact update(MchtContact model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtContact model = new MchtContact();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtContactExt findById(int id){
		return extDao.findById(id);
	}

	public MchtContactExt find(MchtContactExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtContactExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtContactExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtContactExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtContactExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtContactExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtContactExt> list(MchtContactExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtContactExt> paginate(MchtContactExtExample example) {
		List<MchtContactExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
