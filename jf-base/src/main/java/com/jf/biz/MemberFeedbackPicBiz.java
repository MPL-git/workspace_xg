package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberFeedbackPicExtMapper;
import com.jf.dao.MemberFeedbackPicMapper;
import com.jf.entity.MemberFeedbackPic;
import com.jf.entity.MemberFeedbackPicExample;
import com.jf.entity.MemberFeedbackPicExt;
import com.jf.entity.MemberFeedbackPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberFeedbackPicBiz extends BaseService<MemberFeedbackPic, MemberFeedbackPicExample> {

	@Autowired
	private MemberFeedbackPicMapper dao;
	@Autowired
	private MemberFeedbackPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberFeedbackPicMapper(MemberFeedbackPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberFeedbackPicExt save(MemberFeedbackPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberFeedbackPic update(MemberFeedbackPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberFeedbackPic model = new MemberFeedbackPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberFeedbackPicExt findById(int id){
		return extDao.findById(id);
	}

	public MemberFeedbackPicExt find(MemberFeedbackPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberFeedbackPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberFeedbackPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberFeedbackPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberFeedbackPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberFeedbackPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberFeedbackPicExt> list(MemberFeedbackPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberFeedbackPicExt> paginate(MemberFeedbackPicExtExample example) {
		List<MemberFeedbackPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
