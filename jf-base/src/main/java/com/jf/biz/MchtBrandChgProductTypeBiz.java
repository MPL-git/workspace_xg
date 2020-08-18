package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBrandChgProductTypeExtMapper;
import com.jf.dao.MchtBrandChgProductTypeMapper;
import com.jf.entity.MchtBrandChgProductType;
import com.jf.entity.MchtBrandChgProductTypeExample;
import com.jf.entity.MchtBrandChgProductTypeExt;
import com.jf.entity.MchtBrandChgProductTypeExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtBrandChgProductTypeBiz extends BaseService<MchtBrandChgProductType, MchtBrandChgProductTypeExample> {

	@Autowired
	private MchtBrandChgProductTypeMapper dao;
	@Autowired
	private MchtBrandChgProductTypeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtBrandChgProductTypeMapper(MchtBrandChgProductTypeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtBrandChgProductTypeExt save(MchtBrandChgProductTypeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtBrandChgProductType update(MchtBrandChgProductType model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtBrandChgProductType model = new MchtBrandChgProductType();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtBrandChgProductTypeExt findById(int id){
		return extDao.findById(id);
	}

	public MchtBrandChgProductTypeExt find(MchtBrandChgProductTypeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtBrandChgProductTypeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtBrandChgProductTypeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtBrandChgProductTypeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtBrandChgProductTypeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtBrandChgProductTypeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtBrandChgProductTypeExt> list(MchtBrandChgProductTypeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtBrandChgProductTypeExt> paginate(MchtBrandChgProductTypeExtExample example) {
		List<MchtBrandChgProductTypeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
