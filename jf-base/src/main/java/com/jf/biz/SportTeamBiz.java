package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SportTeamExtMapper;
import com.jf.dao.SportTeamMapper;
import com.jf.entity.SportTeam;
import com.jf.entity.SportTeamExample;
import com.jf.entity.SportTeamExt;
import com.jf.entity.SportTeamExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SportTeamBiz extends BaseService<SportTeam, SportTeamExample> {

	@Autowired
	private SportTeamMapper dao;
	@Autowired
	private SportTeamExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSportTeamMapper(SportTeamMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SportTeamExt save(SportTeamExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SportTeam update(SportTeam model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SportTeam model = new SportTeam();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SportTeamExt findById(int id){
		return extDao.findById(id);
	}

	public SportTeamExt find(SportTeamExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SportTeamExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SportTeamExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SportTeamExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SportTeamExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SportTeamExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SportTeamExt> list(SportTeamExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SportTeamExt> paginate(SportTeamExtExample example) {
		List<SportTeamExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
