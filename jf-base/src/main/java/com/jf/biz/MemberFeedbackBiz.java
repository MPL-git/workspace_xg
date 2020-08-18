package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberFeedbackExtMapper;
import com.jf.dao.MemberFeedbackMapper;
import com.jf.entity.MemberFeedback;
import com.jf.entity.MemberFeedbackExample;
import com.jf.entity.MemberFeedbackExt;
import com.jf.entity.MemberFeedbackExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberFeedbackBiz extends BaseService<MemberFeedback, MemberFeedbackExample> {

	@Autowired
	private MemberFeedbackMapper dao;
	@Autowired
	private MemberFeedbackExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberFeedbackMapper(MemberFeedbackMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberFeedbackExt save(MemberFeedbackExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberFeedback update(MemberFeedback model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberFeedback model = new MemberFeedback();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberFeedbackExt findById(int id){
		return extDao.findById(id);
	}

	public MemberFeedbackExt find(MemberFeedbackExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberFeedbackExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberFeedbackExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberFeedbackExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberFeedbackExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberFeedbackExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberFeedbackExt> list(MemberFeedbackExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberFeedbackExt> paginate(MemberFeedbackExtExample example) {
		List<MemberFeedbackExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
