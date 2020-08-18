package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SportLogExtMapper;
import com.jf.dao.SportLogMapper;
import com.jf.entity.SportLog;
import com.jf.entity.SportLogExample;
import com.jf.entity.SportLogExt;
import com.jf.entity.SportLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SportLogBiz extends BaseService<SportLog, SportLogExample> {

	@Autowired
	private SportLogMapper dao;
	@Autowired
	private SportLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSportLogMapper(SportLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SportLogExt save(SportLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SportLog update(SportLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SportLog model = new SportLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SportLogExt findById(int id){
		return extDao.findById(id);
	}

	public SportLogExt find(SportLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SportLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SportLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SportLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SportLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SportLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SportLogExt> list(SportLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SportLogExt> paginate(SportLogExtExample example) {
		List<SportLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
