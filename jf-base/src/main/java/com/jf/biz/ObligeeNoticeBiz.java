package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ObligeeNoticeExtMapper;
import com.jf.dao.ObligeeNoticeMapper;
import com.jf.entity.ObligeeNotice;
import com.jf.entity.ObligeeNoticeExample;
import com.jf.entity.ObligeeNoticeExt;
import com.jf.entity.ObligeeNoticeExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ObligeeNoticeBiz extends BaseService<ObligeeNotice, ObligeeNoticeExample> {

	@Autowired
	private ObligeeNoticeMapper dao;
	@Autowired
	private ObligeeNoticeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setObligeeNoticeMapper(ObligeeNoticeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ObligeeNoticeExt save(ObligeeNoticeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ObligeeNotice update(ObligeeNotice model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ObligeeNotice model = new ObligeeNotice();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ObligeeNoticeExt findById(int id){
		return extDao.findById(id);
	}

	public ObligeeNoticeExt find(ObligeeNoticeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ObligeeNoticeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ObligeeNoticeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ObligeeNoticeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ObligeeNoticeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ObligeeNoticeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ObligeeNoticeExt> list(ObligeeNoticeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ObligeeNoticeExt> paginate(ObligeeNoticeExtExample example) {
		List<ObligeeNoticeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
