package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.SysAppMessageMapper;
import com.jf.dao.SysAppMessageExtMapper;
import com.jf.entity.SysAppMessage;
import com.jf.entity.SysAppMessageExample;
import com.jf.entity.SysAppMessageExt;
import com.jf.entity.SysAppMessageExtExample;
import com.jf.common.base.BaseService;

@Service
public class SysAppMessageBiz extends BaseService<SysAppMessage, SysAppMessageExample> {

	@Autowired
	private SysAppMessageMapper dao;
	@Autowired
	private SysAppMessageExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSysAppMessageMapper(SysAppMessageMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SysAppMessageExt save(SysAppMessageExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SysAppMessage update(SysAppMessage model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SysAppMessage model = new SysAppMessage();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SysAppMessageExt findById(int id){
		return extDao.findById(id);
	}

	public SysAppMessageExt find(SysAppMessageExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SysAppMessageExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SysAppMessageExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SysAppMessageExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SysAppMessageExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SysAppMessageExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SysAppMessageExt> list(SysAppMessageExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SysAppMessageExt> paginate(SysAppMessageExtExample example) {
		List<SysAppMessageExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
