package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.IntegralDailyStatisticsExtMapper;
import com.jf.dao.IntegralDailyStatisticsMapper;
import com.jf.entity.IntegralDailyStatistics;
import com.jf.entity.IntegralDailyStatisticsExample;
import com.jf.entity.IntegralDailyStatisticsExt;
import com.jf.entity.IntegralDailyStatisticsExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IntegralDailyStatisticsBiz extends BaseService<IntegralDailyStatistics, IntegralDailyStatisticsExample> {

	@Autowired
	private IntegralDailyStatisticsMapper dao;
	@Autowired
	private IntegralDailyStatisticsExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setIntegralDailyStatisticsMapper(IntegralDailyStatisticsMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public IntegralDailyStatisticsExt save(IntegralDailyStatisticsExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public IntegralDailyStatistics update(IntegralDailyStatistics model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		IntegralDailyStatistics model = new IntegralDailyStatistics();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public IntegralDailyStatisticsExt findById(int id){
		return extDao.findById(id);
	}

	public IntegralDailyStatisticsExt find(IntegralDailyStatisticsExtExample example){
		return extDao.find(example.fill());
	}

	public int count(IntegralDailyStatisticsExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, IntegralDailyStatisticsExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, IntegralDailyStatisticsExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, IntegralDailyStatisticsExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(IntegralDailyStatisticsExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<IntegralDailyStatisticsExt> list(IntegralDailyStatisticsExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<IntegralDailyStatisticsExt> paginate(IntegralDailyStatisticsExtExample example) {
		List<IntegralDailyStatisticsExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
