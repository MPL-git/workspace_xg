package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ActivityPvStatisticalExtMapper;
import com.jf.dao.ActivityPvStatisticalMapper;
import com.jf.entity.ActivityPvStatistical;
import com.jf.entity.ActivityPvStatisticalExample;
import com.jf.entity.ActivityPvStatisticalExt;
import com.jf.entity.ActivityPvStatisticalExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityPvStatisticalBiz extends BaseService<ActivityPvStatistical, ActivityPvStatisticalExample> {

	@Autowired
	private ActivityPvStatisticalMapper dao;
	@Autowired
	private ActivityPvStatisticalExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setActivityPvStatisticalMapper(ActivityPvStatisticalMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ActivityPvStatisticalExt save(ActivityPvStatisticalExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ActivityPvStatistical update(ActivityPvStatistical model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ActivityPvStatistical model = new ActivityPvStatistical();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ActivityPvStatisticalExt findById(int id){
		return extDao.findById(id);
	}

	public ActivityPvStatisticalExt find(ActivityPvStatisticalExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ActivityPvStatisticalExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ActivityPvStatisticalExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ActivityPvStatisticalExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ActivityPvStatisticalExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ActivityPvStatisticalExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ActivityPvStatisticalExt> list(ActivityPvStatisticalExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ActivityPvStatisticalExt> paginate(ActivityPvStatisticalExtExample example) {
		List<ActivityPvStatisticalExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
