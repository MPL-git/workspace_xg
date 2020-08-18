package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProductBrandTmpExtMapper;
import com.jf.dao.ProductBrandTmpMapper;
import com.jf.entity.ProductBrandTmp;
import com.jf.entity.ProductBrandTmpExample;
import com.jf.entity.ProductBrandTmpExt;
import com.jf.entity.ProductBrandTmpExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductBrandTmpBiz extends BaseService<ProductBrandTmp, ProductBrandTmpExample> {

	@Autowired
	private ProductBrandTmpMapper dao;
	@Autowired
	private ProductBrandTmpExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setProductBrandTmpMapper(ProductBrandTmpMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ProductBrandTmpExt save(ProductBrandTmpExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ProductBrandTmp update(ProductBrandTmp model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ProductBrandTmp model = new ProductBrandTmp();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ProductBrandTmpExt findById(int id){
		return extDao.findById(id);
	}

	public ProductBrandTmpExt find(ProductBrandTmpExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ProductBrandTmpExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ProductBrandTmpExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ProductBrandTmpExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ProductBrandTmpExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ProductBrandTmpExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ProductBrandTmpExt> list(ProductBrandTmpExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ProductBrandTmpExt> paginate(ProductBrandTmpExtExample example) {
		List<ProductBrandTmpExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
