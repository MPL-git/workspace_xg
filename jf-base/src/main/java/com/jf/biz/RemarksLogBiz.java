package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.RemarksLogExtMapper;
import com.jf.dao.RemarksLogMapper;
import com.jf.entity.RemarksLog;
import com.jf.entity.RemarksLogExample;
import com.jf.entity.RemarksLogExt;
import com.jf.entity.RemarksLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RemarksLogBiz extends BaseService<RemarksLog, RemarksLogExample> {

	@Autowired
	private RemarksLogMapper dao;
	@Autowired
	private RemarksLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setRemarksLogMapper(RemarksLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public RemarksLogExt save(RemarksLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public RemarksLog update(RemarksLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		RemarksLog model = new RemarksLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public RemarksLogExt findById(int id){
		return extDao.findById(id);
	}

	public RemarksLogExt find(RemarksLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(RemarksLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, RemarksLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, RemarksLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, RemarksLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(RemarksLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<RemarksLogExt> list(RemarksLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<RemarksLogExt> paginate(RemarksLogExtExample example) {
		List<RemarksLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
