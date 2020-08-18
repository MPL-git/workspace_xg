package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ActivityAreaPvStatisticalExtMapper;
import com.jf.dao.ActivityAreaPvStatisticalMapper;
import com.jf.entity.ActivityAreaPvStatistical;
import com.jf.entity.ActivityAreaPvStatisticalExample;
import com.jf.entity.ActivityAreaPvStatisticalExt;
import com.jf.entity.ActivityAreaPvStatisticalExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityAreaPvStatisticalBiz extends BaseService<ActivityAreaPvStatistical, ActivityAreaPvStatisticalExample> {

	@Autowired
	private ActivityAreaPvStatisticalMapper dao;
	@Autowired
	private ActivityAreaPvStatisticalExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setActivityAreaPvStatisticalMapper(ActivityAreaPvStatisticalMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ActivityAreaPvStatisticalExt save(ActivityAreaPvStatisticalExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ActivityAreaPvStatistical update(ActivityAreaPvStatistical model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ActivityAreaPvStatistical model = new ActivityAreaPvStatistical();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ActivityAreaPvStatisticalExt findById(int id){
		return extDao.findById(id);
	}

	public ActivityAreaPvStatisticalExt find(ActivityAreaPvStatisticalExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ActivityAreaPvStatisticalExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ActivityAreaPvStatisticalExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ActivityAreaPvStatisticalExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ActivityAreaPvStatisticalExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ActivityAreaPvStatisticalExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ActivityAreaPvStatisticalExt> list(ActivityAreaPvStatisticalExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ActivityAreaPvStatisticalExt> paginate(ActivityAreaPvStatisticalExtExample example) {
		List<ActivityAreaPvStatisticalExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
