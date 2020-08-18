package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ProductBrandTrademarkInfoExtMapper;
import com.jf.dao.ProductBrandTrademarkInfoMapper;
import com.jf.entity.ProductBrandTrademarkInfo;
import com.jf.entity.ProductBrandTrademarkInfoExample;
import com.jf.entity.ProductBrandTrademarkInfoExt;
import com.jf.entity.ProductBrandTrademarkInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductBrandTrademarkInfoBiz extends BaseService<ProductBrandTrademarkInfo, ProductBrandTrademarkInfoExample> {

	@Autowired
	private ProductBrandTrademarkInfoMapper dao;
	@Autowired
	private ProductBrandTrademarkInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setProductBrandTrademarkInfoMapper(ProductBrandTrademarkInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ProductBrandTrademarkInfoExt save(ProductBrandTrademarkInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ProductBrandTrademarkInfo update(ProductBrandTrademarkInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ProductBrandTrademarkInfo model = new ProductBrandTrademarkInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ProductBrandTrademarkInfoExt findById(int id){
		return extDao.findById(id);
	}

	public ProductBrandTrademarkInfoExt find(ProductBrandTrademarkInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ProductBrandTrademarkInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ProductBrandTrademarkInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ProductBrandTrademarkInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ProductBrandTrademarkInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ProductBrandTrademarkInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ProductBrandTrademarkInfoExt> list(ProductBrandTrademarkInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ProductBrandTrademarkInfoExt> paginate(ProductBrandTrademarkInfoExtExample example) {
		List<ProductBrandTrademarkInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
