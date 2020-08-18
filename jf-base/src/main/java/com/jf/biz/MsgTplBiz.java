package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MsgTplExtMapper;
import com.jf.dao.MsgTplMapper;
import com.jf.entity.MsgTpl;
import com.jf.entity.MsgTplExample;
import com.jf.entity.MsgTplExt;
import com.jf.entity.MsgTplExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MsgTplBiz extends BaseService<MsgTpl, MsgTplExample> {

	@Autowired
	private MsgTplMapper dao;
	@Autowired
	private MsgTplExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMsgTplMapper(MsgTplMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MsgTplExt save(MsgTplExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MsgTpl update(MsgTpl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MsgTpl model = new MsgTpl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MsgTplExt findById(int id){
		return extDao.findById(id);
	}

	public MsgTplExt find(MsgTplExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MsgTplExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MsgTplExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MsgTplExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MsgTplExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MsgTplExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MsgTplExt> list(MsgTplExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MsgTplExt> paginate(MsgTplExtExample example) {
		List<MsgTplExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
