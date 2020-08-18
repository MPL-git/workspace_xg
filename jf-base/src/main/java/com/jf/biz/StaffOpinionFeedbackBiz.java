package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.StaffOpinionFeedbackExtMapper;
import com.jf.dao.StaffOpinionFeedbackMapper;
import com.jf.entity.StaffOpinionFeedback;
import com.jf.entity.StaffOpinionFeedbackExample;
import com.jf.entity.StaffOpinionFeedbackExt;
import com.jf.entity.StaffOpinionFeedbackExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StaffOpinionFeedbackBiz extends BaseService<StaffOpinionFeedback, StaffOpinionFeedbackExample> {

	@Autowired
	private StaffOpinionFeedbackMapper dao;
	@Autowired
	private StaffOpinionFeedbackExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setStaffOpinionFeedbackMapper(StaffOpinionFeedbackMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public StaffOpinionFeedbackExt save(StaffOpinionFeedbackExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public StaffOpinionFeedback update(StaffOpinionFeedback model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		StaffOpinionFeedback model = new StaffOpinionFeedback();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public StaffOpinionFeedbackExt findById(int id){
		return extDao.findById(id);
	}

	public StaffOpinionFeedbackExt find(StaffOpinionFeedbackExtExample example){
		return extDao.find(example.fill());
	}

	public int count(StaffOpinionFeedbackExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, StaffOpinionFeedbackExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, StaffOpinionFeedbackExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, StaffOpinionFeedbackExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(StaffOpinionFeedbackExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<StaffOpinionFeedbackExt> list(StaffOpinionFeedbackExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<StaffOpinionFeedbackExt> paginate(StaffOpinionFeedbackExtExample example) {
		List<StaffOpinionFeedbackExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
