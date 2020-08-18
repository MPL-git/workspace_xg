package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SpreadActivityGroupSetExtMapper;
import com.jf.dao.SpreadActivityGroupSetMapper;
import com.jf.entity.SpreadActivityGroupSet;
import com.jf.entity.SpreadActivityGroupSetExample;
import com.jf.entity.SpreadActivityGroupSetExt;
import com.jf.entity.SpreadActivityGroupSetExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SpreadActivityGroupSetBiz extends BaseService<SpreadActivityGroupSet, SpreadActivityGroupSetExample> {

	@Autowired
	private SpreadActivityGroupSetMapper dao;
	@Autowired
	private SpreadActivityGroupSetExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSpreadActivityGroupSetMapper(SpreadActivityGroupSetMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SpreadActivityGroupSetExt save(SpreadActivityGroupSetExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SpreadActivityGroupSet update(SpreadActivityGroupSet model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SpreadActivityGroupSet model = new SpreadActivityGroupSet();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SpreadActivityGroupSetExt findById(int id){
		return extDao.findById(id);
	}

	public SpreadActivityGroupSetExt find(SpreadActivityGroupSetExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SpreadActivityGroupSetExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SpreadActivityGroupSetExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SpreadActivityGroupSetExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SpreadActivityGroupSetExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SpreadActivityGroupSetExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SpreadActivityGroupSetExt> list(SpreadActivityGroupSetExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SpreadActivityGroupSetExt> paginate(SpreadActivityGroupSetExtExample example) {
		List<SpreadActivityGroupSetExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
