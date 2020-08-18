package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProductAuditLogExtMapper;
import com.jf.dao.ProductAuditLogMapper;
import com.jf.entity.ProductAuditLog;
import com.jf.entity.ProductAuditLogExample;
import com.jf.entity.ProductAuditLogExt;
import com.jf.entity.ProductAuditLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductAuditLogBiz extends BaseService<ProductAuditLog, ProductAuditLogExample> {

	@Autowired
	private ProductAuditLogMapper dao;
	@Autowired
	private ProductAuditLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setProductAuditLogMapper(ProductAuditLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ProductAuditLogExt save(ProductAuditLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ProductAuditLog update(ProductAuditLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ProductAuditLog model = new ProductAuditLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ProductAuditLogExt findById(int id){
		return extDao.findById(id);
	}

	public ProductAuditLogExt find(ProductAuditLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ProductAuditLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ProductAuditLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ProductAuditLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ProductAuditLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ProductAuditLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ProductAuditLogExt> list(ProductAuditLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ProductAuditLogExt> paginate(ProductAuditLogExtExample example) {
		List<ProductAuditLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
