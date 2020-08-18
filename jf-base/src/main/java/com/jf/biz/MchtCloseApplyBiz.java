package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtCloseApplyExtMapper;
import com.jf.dao.MchtCloseApplyMapper;
import com.jf.entity.MchtCloseApply;
import com.jf.entity.MchtCloseApplyExample;
import com.jf.entity.MchtCloseApplyExt;
import com.jf.entity.MchtCloseApplyExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtCloseApplyBiz extends BaseService<MchtCloseApply, MchtCloseApplyExample> {

	@Autowired
	private MchtCloseApplyMapper dao;
	@Autowired
	private MchtCloseApplyExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtCloseApplyMapper(MchtCloseApplyMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtCloseApplyExt save(MchtCloseApplyExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtCloseApply update(MchtCloseApply model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtCloseApply model = new MchtCloseApply();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtCloseApplyExt findById(int id){
		return extDao.findById(id);
	}

	public MchtCloseApplyExt find(MchtCloseApplyExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtCloseApplyExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtCloseApplyExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtCloseApplyExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtCloseApplyExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtCloseApplyExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtCloseApplyExt> list(MchtCloseApplyExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtCloseApplyExt> paginate(MchtCloseApplyExtExample example) {
		List<MchtCloseApplyExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
