package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.StaffOpinionFeedbackPicExtMapper;
import com.jf.dao.StaffOpinionFeedbackPicMapper;
import com.jf.entity.StaffOpinionFeedbackPic;
import com.jf.entity.StaffOpinionFeedbackPicExample;
import com.jf.entity.StaffOpinionFeedbackPicExt;
import com.jf.entity.StaffOpinionFeedbackPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StaffOpinionFeedbackPicBiz extends BaseService<StaffOpinionFeedbackPic, StaffOpinionFeedbackPicExample> {

	@Autowired
	private StaffOpinionFeedbackPicMapper dao;
	@Autowired
	private StaffOpinionFeedbackPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setStaffOpinionFeedbackPicMapper(StaffOpinionFeedbackPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public StaffOpinionFeedbackPicExt save(StaffOpinionFeedbackPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public StaffOpinionFeedbackPic update(StaffOpinionFeedbackPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		StaffOpinionFeedbackPic model = new StaffOpinionFeedbackPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public StaffOpinionFeedbackPicExt findById(int id){
		return extDao.findById(id);
	}

	public StaffOpinionFeedbackPicExt find(StaffOpinionFeedbackPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(StaffOpinionFeedbackPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, StaffOpinionFeedbackPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, StaffOpinionFeedbackPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, StaffOpinionFeedbackPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(StaffOpinionFeedbackPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<StaffOpinionFeedbackPicExt> list(StaffOpinionFeedbackPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<StaffOpinionFeedbackPicExt> paginate(StaffOpinionFeedbackPicExtExample example) {
		List<StaffOpinionFeedbackPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
