package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SportTeamLogExtMapper;
import com.jf.dao.SportTeamLogMapper;
import com.jf.entity.SportTeamLog;
import com.jf.entity.SportTeamLogExample;
import com.jf.entity.SportTeamLogExt;
import com.jf.entity.SportTeamLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SportTeamLogBiz extends BaseService<SportTeamLog, SportTeamLogExample> {

	@Autowired
	private SportTeamLogMapper dao;
	@Autowired
	private SportTeamLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSportTeamLogMapper(SportTeamLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SportTeamLogExt save(SportTeamLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SportTeamLog update(SportTeamLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SportTeamLog model = new SportTeamLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SportTeamLogExt findById(int id){
		return extDao.findById(id);
	}

	public SportTeamLogExt find(SportTeamLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SportTeamLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SportTeamLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SportTeamLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SportTeamLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SportTeamLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SportTeamLogExt> list(SportTeamLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SportTeamLogExt> paginate(SportTeamLogExtExample example) {
		List<SportTeamLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
