package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProductPicExtMapper;
import com.jf.dao.ProductPicMapper;
import com.jf.entity.ProductPic;
import com.jf.entity.ProductPicExample;
import com.jf.entity.ProductPicExt;
import com.jf.entity.ProductPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductPicBiz extends BaseService<ProductPic, ProductPicExample> {

	@Autowired
	private ProductPicMapper dao;
	@Autowired
	private ProductPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setProductPicMapper(ProductPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ProductPicExt save(ProductPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ProductPic update(ProductPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ProductPic model = new ProductPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ProductPicExt findById(int id){
		return extDao.findById(id);
	}

	public ProductPicExt find(ProductPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ProductPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ProductPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ProductPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ProductPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ProductPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ProductPicExt> list(ProductPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ProductPicExt> paginate(ProductPicExtExample example) {
		List<ProductPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
