package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProductItemExtMapper;
import com.jf.dao.ProductItemMapper;
import com.jf.entity.ProductItem;
import com.jf.entity.ProductItemExample;
import com.jf.entity.ProductItemExt;
import com.jf.entity.ProductItemExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductItemBiz extends BaseService<ProductItem, ProductItemExample> {

	@Autowired
	private ProductItemMapper dao;
	@Autowired
	private ProductItemExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setProductItemMapper(ProductItemMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ProductItemExt save(ProductItemExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ProductItem update(ProductItem model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ProductItem model = new ProductItem();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ProductItemExt findById(int id){
		return extDao.findById(id);
	}

	public ProductItemExt find(ProductItemExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ProductItemExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ProductItemExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ProductItemExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ProductItemExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ProductItemExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ProductItemExt> list(ProductItemExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ProductItemExt> paginate(ProductItemExtExample example) {
		List<ProductItemExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
