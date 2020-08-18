package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProductCopyLogExtMapper;
import com.jf.dao.ProductCopyLogMapper;
import com.jf.entity.ProductCopyLog;
import com.jf.entity.ProductCopyLogExample;
import com.jf.entity.ProductCopyLogExt;
import com.jf.entity.ProductCopyLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductCopyLogBiz extends BaseService<ProductCopyLog, ProductCopyLogExample> {

	@Autowired
	private ProductCopyLogMapper dao;
	@Autowired
	private ProductCopyLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setProductCopyLogMapper(ProductCopyLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ProductCopyLogExt save(ProductCopyLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ProductCopyLog update(ProductCopyLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ProductCopyLog model = new ProductCopyLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ProductCopyLogExt findById(int id){
		return extDao.findById(id);
	}

	public ProductCopyLogExt find(ProductCopyLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ProductCopyLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ProductCopyLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ProductCopyLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ProductCopyLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ProductCopyLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ProductCopyLogExt> list(ProductCopyLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ProductCopyLogExt> paginate(ProductCopyLogExtExample example) {
		List<ProductCopyLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
