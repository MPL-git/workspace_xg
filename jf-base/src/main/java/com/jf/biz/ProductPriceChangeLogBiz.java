package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProductPriceChangeLogExtMapper;
import com.jf.dao.ProductPriceChangeLogMapper;
import com.jf.entity.ProductPriceChangeLog;
import com.jf.entity.ProductPriceChangeLogExample;
import com.jf.entity.ProductPriceChangeLogExt;
import com.jf.entity.ProductPriceChangeLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductPriceChangeLogBiz extends BaseService<ProductPriceChangeLog, ProductPriceChangeLogExample> {

	@Autowired
	private ProductPriceChangeLogMapper dao;
	@Autowired
	private ProductPriceChangeLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setProductPriceChangeLogMapper(ProductPriceChangeLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ProductPriceChangeLogExt save(ProductPriceChangeLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ProductPriceChangeLog update(ProductPriceChangeLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ProductPriceChangeLog model = new ProductPriceChangeLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ProductPriceChangeLogExt findById(int id){
		return extDao.findById(id);
	}

	public ProductPriceChangeLogExt find(ProductPriceChangeLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ProductPriceChangeLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ProductPriceChangeLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ProductPriceChangeLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ProductPriceChangeLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ProductPriceChangeLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ProductPriceChangeLogExt> list(ProductPriceChangeLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ProductPriceChangeLogExt> paginate(ProductPriceChangeLogExtExample example) {
		List<ProductPriceChangeLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
