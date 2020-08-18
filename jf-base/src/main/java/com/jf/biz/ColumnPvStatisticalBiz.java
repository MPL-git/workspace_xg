package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ColumnPvStatisticalExtMapper;
import com.jf.dao.ColumnPvStatisticalMapper;
import com.jf.entity.ColumnPvStatistical;
import com.jf.entity.ColumnPvStatisticalExample;
import com.jf.entity.ColumnPvStatisticalExt;
import com.jf.entity.ColumnPvStatisticalExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ColumnPvStatisticalBiz extends BaseService<ColumnPvStatistical, ColumnPvStatisticalExample> {

	@Autowired
	private ColumnPvStatisticalMapper dao;
	@Autowired
	private ColumnPvStatisticalExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setColumnPvStatisticalMapper(ColumnPvStatisticalMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ColumnPvStatisticalExt save(ColumnPvStatisticalExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ColumnPvStatistical update(ColumnPvStatistical model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ColumnPvStatistical model = new ColumnPvStatistical();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ColumnPvStatisticalExt findById(int id){
		return extDao.findById(id);
	}

	public ColumnPvStatisticalExt find(ColumnPvStatisticalExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ColumnPvStatisticalExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ColumnPvStatisticalExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ColumnPvStatisticalExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ColumnPvStatisticalExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ColumnPvStatisticalExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ColumnPvStatisticalExt> list(ColumnPvStatisticalExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ColumnPvStatisticalExt> paginate(ColumnPvStatisticalExtExample example) {
		List<ColumnPvStatisticalExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
