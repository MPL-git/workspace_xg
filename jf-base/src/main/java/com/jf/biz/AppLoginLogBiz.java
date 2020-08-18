package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.AppLoginLogMapper;
import com.jf.dao.AppLoginLogExtMapper;
import com.jf.entity.AppLoginLog;
import com.jf.entity.AppLoginLogExample;
import com.jf.entity.AppLoginLogExt;
import com.jf.entity.AppLoginLogExtExample;
import com.jf.common.base.BaseService;

@Service
public class AppLoginLogBiz extends BaseService<AppLoginLog, AppLoginLogExample> {

	@Autowired
	private AppLoginLogMapper dao;
	@Autowired
	private AppLoginLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setAppLoginLogMapper(AppLoginLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public AppLoginLogExt save(AppLoginLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public AppLoginLog update(AppLoginLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		AppLoginLog model = new AppLoginLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public AppLoginLogExt findById(int id){
		return extDao.findById(id);
	}

	public AppLoginLogExt find(AppLoginLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(AppLoginLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, AppLoginLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, AppLoginLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, AppLoginLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(AppLoginLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<AppLoginLogExt> list(AppLoginLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<AppLoginLogExt> paginate(AppLoginLogExtExample example) {
		List<AppLoginLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
