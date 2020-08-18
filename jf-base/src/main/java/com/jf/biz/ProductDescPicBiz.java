package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProductDescPicExtMapper;
import com.jf.dao.ProductDescPicMapper;
import com.jf.entity.ProductDescPic;
import com.jf.entity.ProductDescPicExample;
import com.jf.entity.ProductDescPicExt;
import com.jf.entity.ProductDescPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductDescPicBiz extends BaseService<ProductDescPic, ProductDescPicExample> {

	@Autowired
	private ProductDescPicMapper dao;
	@Autowired
	private ProductDescPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setProductDescPicMapper(ProductDescPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ProductDescPicExt save(ProductDescPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ProductDescPic update(ProductDescPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ProductDescPic model = new ProductDescPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ProductDescPicExt findById(int id){
		return extDao.findById(id);
	}

	public ProductDescPicExt find(ProductDescPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ProductDescPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ProductDescPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ProductDescPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ProductDescPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ProductDescPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ProductDescPicExt> list(ProductDescPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ProductDescPicExt> paginate(ProductDescPicExtExample example) {
		List<ProductDescPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
