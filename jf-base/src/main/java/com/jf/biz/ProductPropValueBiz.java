package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProductPropValueExtMapper;
import com.jf.dao.ProductPropValueMapper;
import com.jf.entity.ProductPropValue;
import com.jf.entity.ProductPropValueExample;
import com.jf.entity.ProductPropValueExt;
import com.jf.entity.ProductPropValueExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductPropValueBiz extends BaseService<ProductPropValue, ProductPropValueExample> {

	@Autowired
	private ProductPropValueMapper dao;
	@Autowired
	private ProductPropValueExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setProductPropValueMapper(ProductPropValueMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ProductPropValueExt save(ProductPropValueExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ProductPropValue update(ProductPropValue model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ProductPropValue model = new ProductPropValue();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ProductPropValueExt findById(int id){
		return extDao.findById(id);
	}

	public ProductPropValueExt find(ProductPropValueExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ProductPropValueExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ProductPropValueExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ProductPropValueExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ProductPropValueExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ProductPropValueExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ProductPropValueExt> list(ProductPropValueExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ProductPropValueExt> paginate(ProductPropValueExtExample example) {
		List<ProductPropValueExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
