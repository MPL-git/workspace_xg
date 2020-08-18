package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ImpeachHandleLogExtMapper;
import com.jf.dao.ImpeachHandleLogMapper;
import com.jf.entity.ImpeachHandleLog;
import com.jf.entity.ImpeachHandleLogExample;
import com.jf.entity.ImpeachHandleLogExt;
import com.jf.entity.ImpeachHandleLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ImpeachHandleLogBiz extends BaseService<ImpeachHandleLog, ImpeachHandleLogExample> {

	@Autowired
	private ImpeachHandleLogMapper dao;
	@Autowired
	private ImpeachHandleLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setImpeachHandleLogMapper(ImpeachHandleLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ImpeachHandleLogExt save(ImpeachHandleLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ImpeachHandleLog update(ImpeachHandleLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ImpeachHandleLog model = new ImpeachHandleLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ImpeachHandleLogExt findById(int id){
		return extDao.findById(id);
	}

	public ImpeachHandleLogExt find(ImpeachHandleLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ImpeachHandleLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ImpeachHandleLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ImpeachHandleLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ImpeachHandleLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ImpeachHandleLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ImpeachHandleLogExt> list(ImpeachHandleLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ImpeachHandleLogExt> paginate(ImpeachHandleLogExtExample example) {
		List<ImpeachHandleLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
