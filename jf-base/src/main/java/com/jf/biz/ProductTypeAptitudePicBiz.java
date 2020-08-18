package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProductTypeAptitudePicExtMapper;
import com.jf.dao.ProductTypeAptitudePicMapper;
import com.jf.entity.ProductTypeAptitudePic;
import com.jf.entity.ProductTypeAptitudePicExample;
import com.jf.entity.ProductTypeAptitudePicExt;
import com.jf.entity.ProductTypeAptitudePicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductTypeAptitudePicBiz extends BaseService<ProductTypeAptitudePic, ProductTypeAptitudePicExample> {

	@Autowired
	private ProductTypeAptitudePicMapper dao;
	@Autowired
	private ProductTypeAptitudePicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setProductTypeAptitudePicMapper(ProductTypeAptitudePicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ProductTypeAptitudePicExt save(ProductTypeAptitudePicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ProductTypeAptitudePic update(ProductTypeAptitudePic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ProductTypeAptitudePic model = new ProductTypeAptitudePic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ProductTypeAptitudePicExt findById(int id){
		return extDao.findById(id);
	}

	public ProductTypeAptitudePicExt find(ProductTypeAptitudePicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ProductTypeAptitudePicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ProductTypeAptitudePicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ProductTypeAptitudePicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ProductTypeAptitudePicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ProductTypeAptitudePicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ProductTypeAptitudePicExt> list(ProductTypeAptitudePicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ProductTypeAptitudePicExt> paginate(ProductTypeAptitudePicExtExample example) {
		List<ProductTypeAptitudePicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
