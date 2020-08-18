package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProductFeeRateExtMapper;
import com.jf.dao.ProductFeeRateMapper;
import com.jf.entity.ProductFeeRate;
import com.jf.entity.ProductFeeRateExample;
import com.jf.entity.ProductFeeRateExt;
import com.jf.entity.ProductFeeRateExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductFeeRateBiz extends BaseService<ProductFeeRate, ProductFeeRateExample> {

	@Autowired
	private ProductFeeRateMapper dao;
	@Autowired
	private ProductFeeRateExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setProductFeeRateMapper(ProductFeeRateMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ProductFeeRateExt save(ProductFeeRateExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ProductFeeRate update(ProductFeeRate model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ProductFeeRate model = new ProductFeeRate();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ProductFeeRateExt findById(int id){
		return extDao.findById(id);
	}

	public ProductFeeRateExt find(ProductFeeRateExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ProductFeeRateExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ProductFeeRateExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ProductFeeRateExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ProductFeeRateExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ProductFeeRateExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ProductFeeRateExt> list(ProductFeeRateExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ProductFeeRateExt> paginate(ProductFeeRateExtExample example) {
		List<ProductFeeRateExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
