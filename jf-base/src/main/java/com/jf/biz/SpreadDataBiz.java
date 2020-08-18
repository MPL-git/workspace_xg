package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SpreadDataExtMapper;
import com.jf.dao.SpreadDataMapper;
import com.jf.entity.SpreadData;
import com.jf.entity.SpreadDataExample;
import com.jf.entity.SpreadDataExt;
import com.jf.entity.SpreadDataExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SpreadDataBiz extends BaseService<SpreadData, SpreadDataExample> {

	@Autowired
	private SpreadDataMapper dao;
	@Autowired
	private SpreadDataExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSpreadDataMapper(SpreadDataMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SpreadDataExt save(SpreadDataExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SpreadData update(SpreadData model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SpreadData model = new SpreadData();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SpreadDataExt findById(int id){
		return extDao.findById(id);
	}

	public SpreadDataExt find(SpreadDataExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SpreadDataExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SpreadDataExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SpreadDataExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SpreadDataExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SpreadDataExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SpreadDataExt> list(SpreadDataExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SpreadDataExt> paginate(SpreadDataExtExample example) {
		List<SpreadDataExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
