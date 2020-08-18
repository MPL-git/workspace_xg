package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.AppealLogExtMapper;
import com.jf.dao.AppealLogMapper;
import com.jf.entity.AppealLog;
import com.jf.entity.AppealLogExample;
import com.jf.entity.AppealLogExt;
import com.jf.entity.AppealLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppealLogBiz extends BaseService<AppealLog, AppealLogExample> {

	@Autowired
	private AppealLogMapper dao;
	@Autowired
	private AppealLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setAppealLogMapper(AppealLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public AppealLogExt save(AppealLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public AppealLog update(AppealLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		AppealLog model = new AppealLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public AppealLogExt findById(int id){
		return extDao.findById(id);
	}

	public AppealLogExt find(AppealLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(AppealLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, AppealLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, AppealLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, AppealLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(AppealLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<AppealLogExt> list(AppealLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<AppealLogExt> paginate(AppealLogExtExample example) {
		List<AppealLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
