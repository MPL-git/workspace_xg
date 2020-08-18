package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtFeedbackExtMapper;
import com.jf.dao.MchtFeedbackMapper;
import com.jf.entity.MchtFeedback;
import com.jf.entity.MchtFeedbackExample;
import com.jf.entity.MchtFeedbackExt;
import com.jf.entity.MchtFeedbackExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtFeedbackBiz extends BaseService<MchtFeedback, MchtFeedbackExample> {

	@Autowired
	private MchtFeedbackMapper dao;
	@Autowired
	private MchtFeedbackExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtFeedbackMapper(MchtFeedbackMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtFeedbackExt save(MchtFeedbackExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtFeedback update(MchtFeedback model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtFeedback model = new MchtFeedback();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtFeedbackExt findById(int id){
		return extDao.findById(id);
	}

	public MchtFeedbackExt find(MchtFeedbackExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtFeedbackExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtFeedbackExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtFeedbackExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtFeedbackExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtFeedbackExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtFeedbackExt> list(MchtFeedbackExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtFeedbackExt> paginate(MchtFeedbackExtExample example) {
		List<MchtFeedbackExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
