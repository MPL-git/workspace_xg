package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.WeixinXcxSprPlanExtMapper;
import com.jf.dao.WeixinXcxSprPlanMapper;
import com.jf.entity.WeixinXcxSprPlan;
import com.jf.entity.WeixinXcxSprPlanExample;
import com.jf.entity.WeixinXcxSprPlanExt;
import com.jf.entity.WeixinXcxSprPlanExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WeixinXcxSprPlanBiz extends BaseService<WeixinXcxSprPlan, WeixinXcxSprPlanExample> {

	@Autowired
	private WeixinXcxSprPlanMapper dao;
	@Autowired
	private WeixinXcxSprPlanExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setWeixinXcxSprPlanMapper(WeixinXcxSprPlanMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public WeixinXcxSprPlanExt save(WeixinXcxSprPlanExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public WeixinXcxSprPlan update(WeixinXcxSprPlan model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		WeixinXcxSprPlan model = new WeixinXcxSprPlan();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public WeixinXcxSprPlanExt findById(int id){
		return extDao.findById(id);
	}

	public WeixinXcxSprPlanExt find(WeixinXcxSprPlanExtExample example){
		return extDao.find(example.fill());
	}

	public int count(WeixinXcxSprPlanExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, WeixinXcxSprPlanExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, WeixinXcxSprPlanExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, WeixinXcxSprPlanExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(WeixinXcxSprPlanExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<WeixinXcxSprPlanExt> list(WeixinXcxSprPlanExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<WeixinXcxSprPlanExt> paginate(WeixinXcxSprPlanExtExample example) {
		List<WeixinXcxSprPlanExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
