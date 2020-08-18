package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.IndexTopStyleExtMapper;
import com.jf.dao.IndexTopStyleMapper;
import com.jf.entity.IndexTopStyle;
import com.jf.entity.IndexTopStyleExample;
import com.jf.entity.IndexTopStyleExt;
import com.jf.entity.IndexTopStyleExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IndexTopStyleBiz extends BaseService<IndexTopStyle, IndexTopStyleExample> {

	@Autowired
	private IndexTopStyleMapper dao;
	@Autowired
	private IndexTopStyleExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setIndexTopStyleMapper(IndexTopStyleMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public IndexTopStyleExt save(IndexTopStyleExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public IndexTopStyle update(IndexTopStyle model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		IndexTopStyle model = new IndexTopStyle();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public IndexTopStyleExt findById(int id){
		return extDao.findById(id);
	}

	public IndexTopStyleExt find(IndexTopStyleExtExample example){
		return extDao.find(example.fill());
	}

	public int count(IndexTopStyleExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, IndexTopStyleExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, IndexTopStyleExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, IndexTopStyleExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(IndexTopStyleExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<IndexTopStyleExt> list(IndexTopStyleExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<IndexTopStyleExt> paginate(IndexTopStyleExtExample example) {
		List<IndexTopStyleExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
