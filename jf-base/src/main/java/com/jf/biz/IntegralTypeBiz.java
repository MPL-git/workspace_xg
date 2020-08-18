package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.IntegralTypeExtMapper;
import com.jf.dao.IntegralTypeMapper;
import com.jf.entity.IntegralType;
import com.jf.entity.IntegralTypeExample;
import com.jf.entity.IntegralTypeExt;
import com.jf.entity.IntegralTypeExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IntegralTypeBiz extends BaseService<IntegralType, IntegralTypeExample> {

	@Autowired
	private IntegralTypeMapper dao;
	@Autowired
	private IntegralTypeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setIntegralTypeMapper(IntegralTypeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public IntegralTypeExt save(IntegralTypeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public IntegralType update(IntegralType model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		IntegralType model = new IntegralType();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public IntegralTypeExt findById(int id){
		return extDao.findById(id);
	}

	public IntegralTypeExt find(IntegralTypeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(IntegralTypeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, IntegralTypeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, IntegralTypeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, IntegralTypeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(IntegralTypeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<IntegralTypeExt> list(IntegralTypeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<IntegralTypeExt> paginate(IntegralTypeExtExample example) {
		List<IntegralTypeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
