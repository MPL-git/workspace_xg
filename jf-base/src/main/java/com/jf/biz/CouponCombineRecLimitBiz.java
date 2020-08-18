package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CouponCombineRecLimitExtMapper;
import com.jf.dao.CouponCombineRecLimitMapper;
import com.jf.entity.CouponCombineRecLimit;
import com.jf.entity.CouponCombineRecLimitExample;
import com.jf.entity.CouponCombineRecLimitExt;
import com.jf.entity.CouponCombineRecLimitExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponCombineRecLimitBiz extends BaseService<CouponCombineRecLimit, CouponCombineRecLimitExample> {

	@Autowired
	private CouponCombineRecLimitMapper dao;
	@Autowired
	private CouponCombineRecLimitExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCouponCombineRecLimitMapper(CouponCombineRecLimitMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CouponCombineRecLimitExt save(CouponCombineRecLimitExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CouponCombineRecLimit update(CouponCombineRecLimit model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CouponCombineRecLimit model = new CouponCombineRecLimit();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CouponCombineRecLimitExt findById(int id){
		return extDao.findById(id);
	}

	public CouponCombineRecLimitExt find(CouponCombineRecLimitExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CouponCombineRecLimitExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CouponCombineRecLimitExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CouponCombineRecLimitExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CouponCombineRecLimitExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CouponCombineRecLimitExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CouponCombineRecLimitExt> list(CouponCombineRecLimitExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CouponCombineRecLimitExt> paginate(CouponCombineRecLimitExtExample example) {
		List<CouponCombineRecLimitExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
