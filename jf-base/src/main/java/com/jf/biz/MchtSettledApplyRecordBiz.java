package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtSettledApplyRecordExtMapper;
import com.jf.dao.MchtSettledApplyRecordMapper;
import com.jf.entity.MchtSettledApplyRecord;
import com.jf.entity.MchtSettledApplyRecordExample;
import com.jf.entity.MchtSettledApplyRecordExt;
import com.jf.entity.MchtSettledApplyRecordExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtSettledApplyRecordBiz extends BaseService<MchtSettledApplyRecord, MchtSettledApplyRecordExample> {

	@Autowired
	private MchtSettledApplyRecordMapper dao;
	@Autowired
	private MchtSettledApplyRecordExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtSettledApplyRecordMapper(MchtSettledApplyRecordMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtSettledApplyRecordExt save(MchtSettledApplyRecordExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtSettledApplyRecord update(MchtSettledApplyRecord model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtSettledApplyRecord model = new MchtSettledApplyRecord();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtSettledApplyRecordExt findById(int id){
		return extDao.findById(id);
	}

	public MchtSettledApplyRecordExt find(MchtSettledApplyRecordExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtSettledApplyRecordExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtSettledApplyRecordExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtSettledApplyRecordExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtSettledApplyRecordExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtSettledApplyRecordExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtSettledApplyRecordExt> list(MchtSettledApplyRecordExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtSettledApplyRecordExt> paginate(MchtSettledApplyRecordExtExample example) {
		List<MchtSettledApplyRecordExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
