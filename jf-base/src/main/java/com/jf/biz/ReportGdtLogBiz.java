package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ReportGdtLogExtMapper;
import com.jf.dao.ReportGdtLogMapper;
import com.jf.entity.ReportGdtLog;
import com.jf.entity.ReportGdtLogExample;
import com.jf.entity.ReportGdtLogExt;
import com.jf.entity.ReportGdtLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReportGdtLogBiz extends BaseService<ReportGdtLog, ReportGdtLogExample> {

	@Autowired
	private ReportGdtLogMapper dao;
	@Autowired
	private ReportGdtLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setReportGdtLogMapper(ReportGdtLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ReportGdtLogExt save(ReportGdtLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ReportGdtLog update(ReportGdtLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ReportGdtLog model = new ReportGdtLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ReportGdtLogExt findById(int id){
		return extDao.findById(id);
	}

	public ReportGdtLogExt find(ReportGdtLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ReportGdtLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ReportGdtLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ReportGdtLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ReportGdtLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ReportGdtLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ReportGdtLogExt> list(ReportGdtLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ReportGdtLogExt> paginate(ReportGdtLogExtExample example) {
		List<ReportGdtLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
