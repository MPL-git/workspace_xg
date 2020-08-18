package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.SvipPracticeRecordMapper;
import com.jf.dao.SvipPracticeRecordExtMapper;
import com.jf.entity.SvipPracticeRecord;
import com.jf.entity.SvipPracticeRecordExample;
import com.jf.entity.SvipPracticeRecordExt;
import com.jf.entity.SvipPracticeRecordExtExample;
import com.jf.common.base.BaseService;

@Service
public class SvipPracticeRecordBiz extends BaseService<SvipPracticeRecord, SvipPracticeRecordExample> {

	@Autowired
	private SvipPracticeRecordMapper dao;
	@Autowired
	private SvipPracticeRecordExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSvipPracticeRecordMapper(SvipPracticeRecordMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SvipPracticeRecordExt save(SvipPracticeRecordExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SvipPracticeRecord update(SvipPracticeRecord model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SvipPracticeRecord model = new SvipPracticeRecord();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SvipPracticeRecordExt findById(int id){
		return extDao.findById(id);
	}

	public SvipPracticeRecordExt find(SvipPracticeRecordExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SvipPracticeRecordExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SvipPracticeRecordExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SvipPracticeRecordExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SvipPracticeRecordExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SvipPracticeRecordExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SvipPracticeRecordExt> list(SvipPracticeRecordExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SvipPracticeRecordExt> paginate(SvipPracticeRecordExtExample example) {
		List<SvipPracticeRecordExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
