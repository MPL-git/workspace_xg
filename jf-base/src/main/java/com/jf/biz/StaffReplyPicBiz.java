package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.StaffReplyPicExtMapper;
import com.jf.dao.StaffReplyPicMapper;
import com.jf.entity.StaffReplyPic;
import com.jf.entity.StaffReplyPicExample;
import com.jf.entity.StaffReplyPicExt;
import com.jf.entity.StaffReplyPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StaffReplyPicBiz extends BaseService<StaffReplyPic, StaffReplyPicExample> {

	@Autowired
	private StaffReplyPicMapper dao;
	@Autowired
	private StaffReplyPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setStaffReplyPicMapper(StaffReplyPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public StaffReplyPicExt save(StaffReplyPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public StaffReplyPic update(StaffReplyPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		StaffReplyPic model = new StaffReplyPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public StaffReplyPicExt findById(int id){
		return extDao.findById(id);
	}

	public StaffReplyPicExt find(StaffReplyPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(StaffReplyPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, StaffReplyPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, StaffReplyPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, StaffReplyPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(StaffReplyPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<StaffReplyPicExt> list(StaffReplyPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<StaffReplyPicExt> paginate(StaffReplyPicExtExample example) {
		List<StaffReplyPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
