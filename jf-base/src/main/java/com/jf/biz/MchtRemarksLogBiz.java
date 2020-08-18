package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtRemarksLogExtMapper;
import com.jf.dao.MchtRemarksLogMapper;
import com.jf.entity.MchtRemarksLog;
import com.jf.entity.MchtRemarksLogExample;
import com.jf.entity.MchtRemarksLogExt;
import com.jf.entity.MchtRemarksLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtRemarksLogBiz extends BaseService<MchtRemarksLog, MchtRemarksLogExample> {

	@Autowired
	private MchtRemarksLogMapper dao;
	@Autowired
	private MchtRemarksLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtRemarksLogMapper(MchtRemarksLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtRemarksLogExt save(MchtRemarksLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtRemarksLog update(MchtRemarksLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtRemarksLog model = new MchtRemarksLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtRemarksLogExt findById(int id){
		return extDao.findById(id);
	}

	public MchtRemarksLogExt find(MchtRemarksLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtRemarksLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtRemarksLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtRemarksLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtRemarksLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtRemarksLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtRemarksLogExt> list(MchtRemarksLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtRemarksLogExt> paginate(MchtRemarksLogExtExample example) {
		List<MchtRemarksLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
