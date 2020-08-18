package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CatalogExtMapper;
import com.jf.dao.CatalogMapper;
import com.jf.entity.Catalog;
import com.jf.entity.CatalogExample;
import com.jf.entity.CatalogExt;
import com.jf.entity.CatalogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CatalogBiz extends BaseService<Catalog, CatalogExample> {

	@Autowired
	private CatalogMapper dao;
	@Autowired
	private CatalogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCatalogMapper(CatalogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CatalogExt save(CatalogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public Catalog update(Catalog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		Catalog model = new Catalog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CatalogExt findById(int id){
		return extDao.findById(id);
	}

	public CatalogExt find(CatalogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CatalogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CatalogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CatalogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CatalogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CatalogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CatalogExt> list(CatalogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CatalogExt> paginate(CatalogExtExample example) {
		List<CatalogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
