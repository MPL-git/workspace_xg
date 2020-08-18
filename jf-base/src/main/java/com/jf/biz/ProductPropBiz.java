package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProductPropExtMapper;
import com.jf.dao.ProductPropMapper;
import com.jf.entity.ProductProp;
import com.jf.entity.ProductPropExample;
import com.jf.entity.ProductPropExt;
import com.jf.entity.ProductPropExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductPropBiz extends BaseService<ProductProp, ProductPropExample> {

	@Autowired
	private ProductPropMapper dao;
	@Autowired
	private ProductPropExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setProductPropMapper(ProductPropMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ProductPropExt save(ProductPropExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ProductProp update(ProductProp model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ProductProp model = new ProductProp();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ProductPropExt findById(int id){
		return extDao.findById(id);
	}

	public ProductPropExt find(ProductPropExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ProductPropExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ProductPropExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ProductPropExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ProductPropExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ProductPropExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ProductPropExt> list(ProductPropExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ProductPropExt> paginate(ProductPropExtExample example) {
		List<ProductPropExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
