package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.IntegralDtlExtMapper;
import com.jf.dao.IntegralDtlMapper;
import com.jf.entity.IntegralDtl;
import com.jf.entity.IntegralDtlExample;
import com.jf.entity.IntegralDtlExt;
import com.jf.entity.IntegralDtlExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IntegralDtlBiz extends BaseService<IntegralDtl, IntegralDtlExample> {

	@Autowired
	private IntegralDtlMapper dao;
	@Autowired
	private IntegralDtlExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setIntegralDtlMapper(IntegralDtlMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public IntegralDtlExt save(IntegralDtlExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public IntegralDtl update(IntegralDtl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		IntegralDtl model = new IntegralDtl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public IntegralDtlExt findById(int id){
		return extDao.findById(id);
	}

	public IntegralDtlExt find(IntegralDtlExtExample example){
		return extDao.find(example.fill());
	}

	public int count(IntegralDtlExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, IntegralDtlExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, IntegralDtlExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, IntegralDtlExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(IntegralDtlExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<IntegralDtlExt> list(IntegralDtlExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<IntegralDtlExt> paginate(IntegralDtlExtExample example) {
		List<IntegralDtlExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
