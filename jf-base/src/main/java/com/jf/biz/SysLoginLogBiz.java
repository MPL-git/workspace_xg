package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.SysLoginLogMapper;
import com.jf.dao.SysLoginLogExtMapper;
import com.jf.entity.SysLoginLog;
import com.jf.entity.SysLoginLogExample;
import com.jf.entity.SysLoginLogExt;
import com.jf.entity.SysLoginLogExtExample;
import com.jf.common.base.BaseService;

@Service
public class SysLoginLogBiz extends BaseService<SysLoginLog, SysLoginLogExample> {

	@Autowired
	private SysLoginLogMapper dao;
	@Autowired
	private SysLoginLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSysLoginLogMapper(SysLoginLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SysLoginLogExt save(SysLoginLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SysLoginLog update(SysLoginLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SysLoginLog model = new SysLoginLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SysLoginLogExt findById(int id){
		return extDao.findById(id);
	}

	public SysLoginLogExt find(SysLoginLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SysLoginLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SysLoginLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SysLoginLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SysLoginLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SysLoginLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SysLoginLogExt> list(SysLoginLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SysLoginLogExt> paginate(SysLoginLogExtExample example) {
		List<SysLoginLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
