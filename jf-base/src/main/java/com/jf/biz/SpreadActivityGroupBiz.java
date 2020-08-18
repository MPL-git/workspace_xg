package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SpreadActivityGroupExtMapper;
import com.jf.dao.SpreadActivityGroupMapper;
import com.jf.entity.SpreadActivityGroup;
import com.jf.entity.SpreadActivityGroupExample;
import com.jf.entity.SpreadActivityGroupExt;
import com.jf.entity.SpreadActivityGroupExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SpreadActivityGroupBiz extends BaseService<SpreadActivityGroup, SpreadActivityGroupExample> {

	@Autowired
	private SpreadActivityGroupMapper dao;
	@Autowired
	private SpreadActivityGroupExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSpreadActivityGroupMapper(SpreadActivityGroupMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SpreadActivityGroupExt save(SpreadActivityGroupExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SpreadActivityGroup update(SpreadActivityGroup model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SpreadActivityGroup model = new SpreadActivityGroup();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SpreadActivityGroupExt findById(int id){
		return extDao.findById(id);
	}

	public SpreadActivityGroupExt find(SpreadActivityGroupExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SpreadActivityGroupExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SpreadActivityGroupExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SpreadActivityGroupExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SpreadActivityGroupExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SpreadActivityGroupExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SpreadActivityGroupExt> list(SpreadActivityGroupExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SpreadActivityGroupExt> paginate(SpreadActivityGroupExtExample example) {
		List<SpreadActivityGroupExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
