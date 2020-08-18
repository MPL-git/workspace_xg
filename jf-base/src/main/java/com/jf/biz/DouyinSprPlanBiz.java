package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.DouyinSprPlanExtMapper;
import com.jf.dao.DouyinSprPlanMapper;
import com.jf.entity.DouyinSprPlan;
import com.jf.entity.DouyinSprPlanExample;
import com.jf.entity.DouyinSprPlanExt;
import com.jf.entity.DouyinSprPlanExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DouyinSprPlanBiz extends BaseService<DouyinSprPlan, DouyinSprPlanExample> {

	@Autowired
	private DouyinSprPlanMapper dao;
	@Autowired
	private DouyinSprPlanExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setDouyinSprPlanMapper(DouyinSprPlanMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public DouyinSprPlanExt save(DouyinSprPlanExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public DouyinSprPlan update(DouyinSprPlan model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		DouyinSprPlan model = new DouyinSprPlan();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public DouyinSprPlanExt findById(int id){
		return extDao.findById(id);
	}

	public DouyinSprPlanExt find(DouyinSprPlanExtExample example){
		return extDao.find(example.fill());
	}

	public int count(DouyinSprPlanExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, DouyinSprPlanExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, DouyinSprPlanExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, DouyinSprPlanExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(DouyinSprPlanExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<DouyinSprPlanExt> list(DouyinSprPlanExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<DouyinSprPlanExt> paginate(DouyinSprPlanExtExample example) {
		List<DouyinSprPlanExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
