package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CouponAddTaskConfigExtMapper;
import com.jf.dao.CouponAddTaskConfigMapper;
import com.jf.entity.CouponAddTaskConfig;
import com.jf.entity.CouponAddTaskConfigExample;
import com.jf.entity.CouponAddTaskConfigExt;
import com.jf.entity.CouponAddTaskConfigExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponAddTaskConfigBiz extends BaseService<CouponAddTaskConfig, CouponAddTaskConfigExample> {

	@Autowired
	private CouponAddTaskConfigMapper dao;
	@Autowired
	private CouponAddTaskConfigExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCouponAddTaskConfigMapper(CouponAddTaskConfigMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CouponAddTaskConfigExt save(CouponAddTaskConfigExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CouponAddTaskConfig update(CouponAddTaskConfig model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CouponAddTaskConfig model = new CouponAddTaskConfig();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CouponAddTaskConfigExt findById(int id){
		return extDao.findById(id);
	}

	public CouponAddTaskConfigExt find(CouponAddTaskConfigExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CouponAddTaskConfigExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CouponAddTaskConfigExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CouponAddTaskConfigExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CouponAddTaskConfigExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CouponAddTaskConfigExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CouponAddTaskConfigExt> list(CouponAddTaskConfigExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CouponAddTaskConfigExt> paginate(CouponAddTaskConfigExtExample example) {
		List<CouponAddTaskConfigExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
