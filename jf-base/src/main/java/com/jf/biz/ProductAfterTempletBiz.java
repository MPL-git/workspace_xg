package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProductAfterTempletExtMapper;
import com.jf.dao.ProductAfterTempletMapper;
import com.jf.entity.ProductAfterTemplet;
import com.jf.entity.ProductAfterTempletExample;
import com.jf.entity.ProductAfterTempletExt;
import com.jf.entity.ProductAfterTempletExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductAfterTempletBiz extends BaseService<ProductAfterTemplet, ProductAfterTempletExample> {

	@Autowired
	private ProductAfterTempletMapper dao;
	@Autowired
	private ProductAfterTempletExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setProductAfterTempletMapper(ProductAfterTempletMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ProductAfterTempletExt save(ProductAfterTempletExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ProductAfterTemplet update(ProductAfterTemplet model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ProductAfterTemplet model = new ProductAfterTemplet();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ProductAfterTempletExt findById(int id){
		return extDao.findById(id);
	}

	public ProductAfterTempletExt find(ProductAfterTempletExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ProductAfterTempletExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ProductAfterTempletExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ProductAfterTempletExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ProductAfterTempletExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ProductAfterTempletExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ProductAfterTempletExt> list(ProductAfterTempletExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ProductAfterTempletExt> paginate(ProductAfterTempletExtExample example) {
		List<ProductAfterTempletExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
