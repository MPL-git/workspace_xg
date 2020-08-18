package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CouponExtMapper;
import com.jf.dao.CouponMapper;
import com.jf.entity.Coupon;
import com.jf.entity.CouponExample;
import com.jf.entity.CouponExt;
import com.jf.entity.CouponExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponBiz extends BaseService<Coupon, CouponExample> {

	@Autowired
	private CouponMapper dao;
	@Autowired
	private CouponExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCouponMapper(CouponMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CouponExt save(CouponExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public Coupon update(Coupon model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		Coupon model = new Coupon();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CouponExt findById(int id){
		return extDao.findById(id);
	}

	public CouponExt find(CouponExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CouponExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CouponExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CouponExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CouponExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CouponExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CouponExt> list(CouponExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CouponExt> paginate(CouponExtExample example) {
		List<CouponExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
