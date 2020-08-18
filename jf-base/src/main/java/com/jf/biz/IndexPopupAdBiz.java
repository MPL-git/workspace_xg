package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.IndexPopupAdExtMapper;
import com.jf.dao.IndexPopupAdMapper;
import com.jf.entity.IndexPopupAd;
import com.jf.entity.IndexPopupAdExample;
import com.jf.entity.IndexPopupAdExt;
import com.jf.entity.IndexPopupAdExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IndexPopupAdBiz extends BaseService<IndexPopupAd, IndexPopupAdExample> {

	@Autowired
	private IndexPopupAdMapper dao;
	@Autowired
	private IndexPopupAdExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setIndexPopupAdMapper(IndexPopupAdMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public IndexPopupAdExt save(IndexPopupAdExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public IndexPopupAd update(IndexPopupAd model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		IndexPopupAd model = new IndexPopupAd();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public IndexPopupAdExt findById(int id){
		return extDao.findById(id);
	}

	public IndexPopupAdExt find(IndexPopupAdExtExample example){
		return extDao.find(example.fill());
	}

	public int count(IndexPopupAdExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, IndexPopupAdExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, IndexPopupAdExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, IndexPopupAdExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(IndexPopupAdExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<IndexPopupAdExt> list(IndexPopupAdExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<IndexPopupAdExt> paginate(IndexPopupAdExtExample example) {
		List<IndexPopupAdExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
