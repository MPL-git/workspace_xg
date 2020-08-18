package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.GrowthDtlExtMapper;
import com.jf.dao.GrowthDtlMapper;
import com.jf.entity.GrowthDtl;
import com.jf.entity.GrowthDtlExample;
import com.jf.entity.GrowthDtlExt;
import com.jf.entity.GrowthDtlExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GrowthDtlBiz extends BaseService<GrowthDtl, GrowthDtlExample> {

	@Autowired
	private GrowthDtlMapper dao;
	@Autowired
	private GrowthDtlExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setGrowthDtlMapper(GrowthDtlMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public GrowthDtlExt save(GrowthDtlExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public GrowthDtl update(GrowthDtl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		GrowthDtl model = new GrowthDtl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public GrowthDtlExt findById(int id){
		return extDao.findById(id);
	}

	public GrowthDtlExt find(GrowthDtlExtExample example){
		return extDao.find(example.fill());
	}

	public int count(GrowthDtlExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, GrowthDtlExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, GrowthDtlExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, GrowthDtlExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(GrowthDtlExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<GrowthDtlExt> list(GrowthDtlExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<GrowthDtlExt> paginate(GrowthDtlExtExample example) {
		List<GrowthDtlExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
