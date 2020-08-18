package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.CouponRainSetupMapper;
import com.jf.dao.CouponRainSetupExtMapper;
import com.jf.entity.CouponRainSetup;
import com.jf.entity.CouponRainSetupExample;
import com.jf.entity.CouponRainSetupExt;
import com.jf.entity.CouponRainSetupExtExample;
import com.jf.common.base.BaseService;

@Service
public class CouponRainSetupBiz extends BaseService<CouponRainSetup, CouponRainSetupExample> {

	@Autowired
	private CouponRainSetupMapper dao;
	@Autowired
	private CouponRainSetupExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCouponRainSetupMapper(CouponRainSetupMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CouponRainSetupExt save(CouponRainSetupExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CouponRainSetup update(CouponRainSetup model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CouponRainSetup model = new CouponRainSetup();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CouponRainSetupExt findById(int id){
		return extDao.findById(id);
	}

	public CouponRainSetupExt find(CouponRainSetupExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CouponRainSetupExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CouponRainSetupExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CouponRainSetupExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CouponRainSetupExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CouponRainSetupExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CouponRainSetupExt> list(CouponRainSetupExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CouponRainSetupExt> paginate(CouponRainSetupExtExample example) {
		List<CouponRainSetupExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
