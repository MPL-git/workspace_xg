package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.CouponRainRecordMapper;
import com.jf.dao.CouponRainRecordExtMapper;
import com.jf.entity.CouponRainRecord;
import com.jf.entity.CouponRainRecordExample;
import com.jf.entity.CouponRainRecordExt;
import com.jf.entity.CouponRainRecordExtExample;
import com.jf.common.base.BaseService;

@Service
public class CouponRainRecordBiz extends BaseService<CouponRainRecord, CouponRainRecordExample> {

	@Autowired
	private CouponRainRecordMapper dao;
	@Autowired
	private CouponRainRecordExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCouponRainRecordMapper(CouponRainRecordMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CouponRainRecordExt save(CouponRainRecordExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CouponRainRecord update(CouponRainRecord model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CouponRainRecord model = new CouponRainRecord();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CouponRainRecordExt findById(int id){
		return extDao.findById(id);
	}

	public CouponRainRecordExt find(CouponRainRecordExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CouponRainRecordExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CouponRainRecordExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CouponRainRecordExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CouponRainRecordExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CouponRainRecordExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CouponRainRecordExt> list(CouponRainRecordExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CouponRainRecordExt> paginate(CouponRainRecordExtExample example) {
		List<CouponRainRecordExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
