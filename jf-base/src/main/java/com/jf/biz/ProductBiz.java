package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProductExtMapper;
import com.jf.dao.ProductMapper;
import com.jf.entity.Product;
import com.jf.entity.ProductExample;
import com.jf.entity.ProductExt;
import com.jf.entity.ProductExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductBiz extends BaseService<Product, ProductExample> {

	@Autowired
	private ProductMapper dao;
	@Autowired
	private ProductExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setProductMapper(ProductMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ProductExt save(ProductExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public Product update(Product model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		Product model = new Product();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ProductExt findById(int id){
		return extDao.findById(id);
	}

	public ProductExt find(ProductExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ProductExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ProductExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ProductExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ProductExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ProductExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ProductExt> list(ProductExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ProductExt> paginate(ProductExtExample example) {
		List<ProductExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
