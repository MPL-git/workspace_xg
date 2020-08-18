package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.StaffReplyExtMapper;
import com.jf.dao.StaffReplyMapper;
import com.jf.entity.StaffReply;
import com.jf.entity.StaffReplyExample;
import com.jf.entity.StaffReplyExt;
import com.jf.entity.StaffReplyExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StaffReplyBiz extends BaseService<StaffReply, StaffReplyExample> {

	@Autowired
	private StaffReplyMapper dao;
	@Autowired
	private StaffReplyExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setStaffReplyMapper(StaffReplyMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public StaffReplyExt save(StaffReplyExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public StaffReply update(StaffReply model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		StaffReply model = new StaffReply();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public StaffReplyExt findById(int id){
		return extDao.findById(id);
	}

	public StaffReplyExt find(StaffReplyExtExample example){
		return extDao.find(example.fill());
	}

	public int count(StaffReplyExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, StaffReplyExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, StaffReplyExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, StaffReplyExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(StaffReplyExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<StaffReplyExt> list(StaffReplyExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<StaffReplyExt> paginate(StaffReplyExtExample example) {
		List<StaffReplyExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
