package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProductTypeExtMapper;
import com.jf.dao.ProductTypeMapper;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.ProductTypeExt;
import com.jf.entity.ProductTypeExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductTypeBiz extends BaseService<ProductType, ProductTypeExample> {

	@Autowired
	private ProductTypeMapper dao;
	@Autowired
	private ProductTypeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setProductTypeMapper(ProductTypeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ProductTypeExt save(ProductTypeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ProductType update(ProductType model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ProductType model = new ProductType();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ProductTypeExt findById(int id){
		return extDao.findById(id);
	}

	public ProductTypeExt find(ProductTypeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ProductTypeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ProductTypeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ProductTypeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ProductTypeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ProductTypeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ProductTypeExt> list(ProductTypeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ProductTypeExt> paginate(ProductTypeExtExample example) {
		List<ProductTypeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
