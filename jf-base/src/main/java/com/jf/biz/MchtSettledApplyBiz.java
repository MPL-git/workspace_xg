package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtSettledApplyExtMapper;
import com.jf.dao.MchtSettledApplyMapper;
import com.jf.entity.MchtSettledApply;
import com.jf.entity.MchtSettledApplyExample;
import com.jf.entity.MchtSettledApplyExt;
import com.jf.entity.MchtSettledApplyExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtSettledApplyBiz extends BaseService<MchtSettledApply, MchtSettledApplyExample> {

	@Autowired
	private MchtSettledApplyMapper dao;
	@Autowired
	private MchtSettledApplyExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtSettledApplyMapper(MchtSettledApplyMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtSettledApplyExt save(MchtSettledApplyExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtSettledApply update(MchtSettledApply model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtSettledApply model = new MchtSettledApply();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtSettledApplyExt findById(int id){
		return extDao.findById(id);
	}

	public MchtSettledApplyExt find(MchtSettledApplyExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtSettledApplyExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtSettledApplyExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtSettledApplyExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtSettledApplyExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtSettledApplyExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtSettledApplyExt> list(MchtSettledApplyExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtSettledApplyExt> paginate(MchtSettledApplyExtExample example) {
		List<MchtSettledApplyExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
