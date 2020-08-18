package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtFeedbackPicExtMapper;
import com.jf.dao.MchtFeedbackPicMapper;
import com.jf.entity.MchtFeedbackPic;
import com.jf.entity.MchtFeedbackPicExample;
import com.jf.entity.MchtFeedbackPicExt;
import com.jf.entity.MchtFeedbackPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MchtFeedbackPicBiz extends BaseService<MchtFeedbackPic, MchtFeedbackPicExample> {

	@Autowired
	private MchtFeedbackPicMapper dao;
	@Autowired
	private MchtFeedbackPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtFeedbackPicMapper(MchtFeedbackPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtFeedbackPicExt save(MchtFeedbackPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MchtFeedbackPic update(MchtFeedbackPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MchtFeedbackPic model = new MchtFeedbackPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MchtFeedbackPicExt findById(int id){
		return extDao.findById(id);
	}

	public MchtFeedbackPicExt find(MchtFeedbackPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtFeedbackPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtFeedbackPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtFeedbackPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtFeedbackPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtFeedbackPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtFeedbackPicExt> list(MchtFeedbackPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtFeedbackPicExt> paginate(MchtFeedbackPicExtExample example) {
		List<MchtFeedbackPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
