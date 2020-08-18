package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.IndexTopTabExtMapper;
import com.jf.dao.IndexTopTabMapper;
import com.jf.entity.IndexTopTab;
import com.jf.entity.IndexTopTabExample;
import com.jf.entity.IndexTopTabExt;
import com.jf.entity.IndexTopTabExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IndexTopTabBiz extends BaseService<IndexTopTab, IndexTopTabExample> {

	@Autowired
	private IndexTopTabMapper dao;
	@Autowired
	private IndexTopTabExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setIndexTopTabMapper(IndexTopTabMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public IndexTopTabExt save(IndexTopTabExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public IndexTopTab update(IndexTopTab model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		IndexTopTab model = new IndexTopTab();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public IndexTopTabExt findById(int id){
		return extDao.findById(id);
	}

	public IndexTopTabExt find(IndexTopTabExtExample example){
		return extDao.find(example.fill());
	}

	public int count(IndexTopTabExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, IndexTopTabExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, IndexTopTabExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, IndexTopTabExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(IndexTopTabExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<IndexTopTabExt> list(IndexTopTabExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<IndexTopTabExt> paginate(IndexTopTabExtExample example) {
		List<IndexTopTabExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
