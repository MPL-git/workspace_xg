package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SeckillBrandGroupProductExtMapper;
import com.jf.dao.SeckillBrandGroupProductMapper;
import com.jf.entity.SeckillBrandGroupProduct;
import com.jf.entity.SeckillBrandGroupProductExample;
import com.jf.entity.SeckillBrandGroupProductExt;
import com.jf.entity.SeckillBrandGroupProductExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SeckillBrandGroupProductBiz extends BaseService<SeckillBrandGroupProduct, SeckillBrandGroupProductExample> {

	@Autowired
	private SeckillBrandGroupProductMapper dao;
	@Autowired
	private SeckillBrandGroupProductExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSeckillBrandGroupProductMapper(SeckillBrandGroupProductMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SeckillBrandGroupProductExt save(SeckillBrandGroupProductExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SeckillBrandGroupProduct update(SeckillBrandGroupProduct model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SeckillBrandGroupProduct model = new SeckillBrandGroupProduct();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SeckillBrandGroupProductExt findById(int id){
		return extDao.findById(id);
	}

	public SeckillBrandGroupProductExt find(SeckillBrandGroupProductExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SeckillBrandGroupProductExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SeckillBrandGroupProductExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SeckillBrandGroupProductExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SeckillBrandGroupProductExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SeckillBrandGroupProductExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SeckillBrandGroupProductExt> list(SeckillBrandGroupProductExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SeckillBrandGroupProductExt> paginate(SeckillBrandGroupProductExtExample example) {
		List<SeckillBrandGroupProductExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
