package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SportExtMapper;
import com.jf.dao.SportMapper;
import com.jf.entity.Sport;
import com.jf.entity.SportExample;
import com.jf.entity.SportExt;
import com.jf.entity.SportExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SportBiz extends BaseService<Sport, SportExample> {

	@Autowired
	private SportMapper dao;
	@Autowired
	private SportExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSportMapper(SportMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SportExt save(SportExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public Sport update(Sport model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		Sport model = new Sport();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SportExt findById(int id){
		return extDao.findById(id);
	}

	public SportExt find(SportExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SportExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SportExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SportExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SportExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SportExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SportExt> list(SportExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SportExt> paginate(SportExtExample example) {
		List<SportExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
