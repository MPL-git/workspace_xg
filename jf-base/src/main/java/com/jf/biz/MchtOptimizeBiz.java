package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtOptimizeExtMapper;
import com.jf.dao.MchtOptimizeMapper;
import com.jf.entity.MchtOptimize;
import com.jf.entity.MchtOptimizeExample;
import com.jf.entity.MchtOptimizeExt;
import com.jf.entity.MchtOptimizeExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtOptimizeBiz extends BaseService<MchtOptimize, MchtOptimizeExample> {

	@Autowired
	private MchtOptimizeMapper dao;
	@Autowired
	private MchtOptimizeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtOptimizeMapper(MchtOptimizeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtOptimizeExt save(MchtOptimizeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtOptimize update(MchtOptimize model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtOptimize model = new MchtOptimize();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtOptimizeExt findById(int id){
		return extDao.findById(id);
	}

	public MchtOptimizeExt find(MchtOptimizeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtOptimizeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtOptimizeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtOptimizeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtOptimizeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtOptimizeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtOptimizeExt> list(MchtOptimizeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtOptimizeExt> paginate(MchtOptimizeExtExample example) {
		List<MchtOptimizeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
