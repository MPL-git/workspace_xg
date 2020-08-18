package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProductBrandExtMapper;
import com.jf.dao.ProductBrandMapper;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductBrandExample;
import com.jf.entity.ProductBrandExt;
import com.jf.entity.ProductBrandExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductBrandBiz extends BaseService<ProductBrand, ProductBrandExample> {

	@Autowired
	private ProductBrandMapper dao;
	@Autowired
	private ProductBrandExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setProductBrandMapper(ProductBrandMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ProductBrandExt save(ProductBrandExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ProductBrand update(ProductBrand model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ProductBrand model = new ProductBrand();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ProductBrandExt findById(int id){
		return extDao.findById(id);
	}

	public ProductBrandExt find(ProductBrandExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ProductBrandExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ProductBrandExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ProductBrandExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ProductBrandExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ProductBrandExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ProductBrandExt> list(ProductBrandExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ProductBrandExt> paginate(ProductBrandExtExample example) {
		List<ProductBrandExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
