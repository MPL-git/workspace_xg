package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.SupplierProductBrandMapper;
import com.jf.dao.SupplierProductBrandExtMapper;
import com.jf.entity.SupplierProductBrand;
import com.jf.entity.SupplierProductBrandExample;
import com.jf.entity.SupplierProductBrandExt;
import com.jf.entity.SupplierProductBrandExtExample;
import com.jf.common.base.BaseService;

@Service
public class SupplierProductBrandBiz extends BaseService<SupplierProductBrand, SupplierProductBrandExample> {

	@Autowired
	private SupplierProductBrandMapper dao;
	@Autowired
	private SupplierProductBrandExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSupplierProductBrandMapper(SupplierProductBrandMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SupplierProductBrandExt save(SupplierProductBrandExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SupplierProductBrand update(SupplierProductBrand model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SupplierProductBrand model = new SupplierProductBrand();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SupplierProductBrandExt findById(int id){
		return extDao.findById(id);
	}

	public SupplierProductBrandExt find(SupplierProductBrandExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SupplierProductBrandExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SupplierProductBrandExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SupplierProductBrandExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SupplierProductBrandExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SupplierProductBrandExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SupplierProductBrandExt> list(SupplierProductBrandExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SupplierProductBrandExt> paginate(SupplierProductBrandExtExample example) {
		List<SupplierProductBrandExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
