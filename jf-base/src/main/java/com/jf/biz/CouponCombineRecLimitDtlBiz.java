package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CouponCombineRecLimitDtlExtMapper;
import com.jf.dao.CouponCombineRecLimitDtlMapper;
import com.jf.entity.CouponCombineRecLimitDtl;
import com.jf.entity.CouponCombineRecLimitDtlExample;
import com.jf.entity.CouponCombineRecLimitDtlExt;
import com.jf.entity.CouponCombineRecLimitDtlExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponCombineRecLimitDtlBiz extends BaseService<CouponCombineRecLimitDtl, CouponCombineRecLimitDtlExample> {

	@Autowired
	private CouponCombineRecLimitDtlMapper dao;
	@Autowired
	private CouponCombineRecLimitDtlExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCouponCombineRecLimitDtlMapper(CouponCombineRecLimitDtlMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CouponCombineRecLimitDtlExt save(CouponCombineRecLimitDtlExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CouponCombineRecLimitDtl update(CouponCombineRecLimitDtl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CouponCombineRecLimitDtl model = new CouponCombineRecLimitDtl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CouponCombineRecLimitDtlExt findById(int id){
		return extDao.findById(id);
	}

	public CouponCombineRecLimitDtlExt find(CouponCombineRecLimitDtlExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CouponCombineRecLimitDtlExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CouponCombineRecLimitDtlExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CouponCombineRecLimitDtlExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CouponCombineRecLimitDtlExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CouponCombineRecLimitDtlExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CouponCombineRecLimitDtlExt> list(CouponCombineRecLimitDtlExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CouponCombineRecLimitDtlExt> paginate(CouponCombineRecLimitDtlExtExample example) {
		List<CouponCombineRecLimitDtlExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
