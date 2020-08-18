package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtMonthlyCollectionsExtMapper;
import com.jf.dao.MchtMonthlyCollectionsMapper;
import com.jf.entity.MchtMonthlyCollections;
import com.jf.entity.MchtMonthlyCollectionsExample;
import com.jf.entity.MchtMonthlyCollectionsExt;
import com.jf.entity.MchtMonthlyCollectionsExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtMonthlyCollectionsBiz extends BaseService<MchtMonthlyCollections, MchtMonthlyCollectionsExample> {

	@Autowired
	private MchtMonthlyCollectionsMapper dao;
	@Autowired
	private MchtMonthlyCollectionsExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtMonthlyCollectionsMapper(MchtMonthlyCollectionsMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtMonthlyCollectionsExt save(MchtMonthlyCollectionsExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtMonthlyCollections update(MchtMonthlyCollections model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtMonthlyCollections model = new MchtMonthlyCollections();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtMonthlyCollectionsExt findById(int id){
		return extDao.findById(id);
	}

	public MchtMonthlyCollectionsExt find(MchtMonthlyCollectionsExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtMonthlyCollectionsExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtMonthlyCollectionsExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtMonthlyCollectionsExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtMonthlyCollectionsExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtMonthlyCollectionsExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtMonthlyCollectionsExt> list(MchtMonthlyCollectionsExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtMonthlyCollectionsExt> paginate(MchtMonthlyCollectionsExtExample example) {
		List<MchtMonthlyCollectionsExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
