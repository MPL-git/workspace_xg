package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProductVideoExtMapper;
import com.jf.dao.ProductVideoMapper;
import com.jf.entity.ProductVideo;
import com.jf.entity.ProductVideoExample;
import com.jf.entity.ProductVideoExt;
import com.jf.entity.ProductVideoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductVideoBiz extends BaseService<ProductVideo, ProductVideoExample> {

	@Autowired
	private ProductVideoMapper dao;
	@Autowired
	private ProductVideoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setProductVideoMapper(ProductVideoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ProductVideoExt save(ProductVideoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ProductVideo update(ProductVideo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ProductVideo model = new ProductVideo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ProductVideoExt findById(int id){
		return extDao.findById(id);
	}

	public ProductVideoExt find(ProductVideoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ProductVideoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ProductVideoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ProductVideoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ProductVideoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ProductVideoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ProductVideoExt> list(ProductVideoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ProductVideoExt> paginate(ProductVideoExtExample example) {
		List<ProductVideoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
