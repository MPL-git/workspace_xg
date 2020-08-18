package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CouponRainExtMapper;
import com.jf.dao.CouponRainMapper;
import com.jf.entity.CouponRain;
import com.jf.entity.CouponRainExample;
import com.jf.entity.CouponRainExt;
import com.jf.entity.CouponRainExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponRainBiz extends BaseService<CouponRain, CouponRainExample> {

	@Autowired
	private CouponRainMapper dao;
	@Autowired
	private CouponRainExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCouponRainMapper(CouponRainMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CouponRainExt save(CouponRainExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CouponRain update(CouponRain model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CouponRain model = new CouponRain();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CouponRainExt findById(int id){
		return extDao.findById(id);
	}

	public CouponRainExt find(CouponRainExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CouponRainExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CouponRainExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CouponRainExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CouponRainExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CouponRainExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CouponRainExt> list(CouponRainExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CouponRainExt> paginate(CouponRainExtExample example) {
		List<CouponRainExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
