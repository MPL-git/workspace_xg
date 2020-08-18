package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.AttachmentExtMapper;
import com.jf.dao.AttachmentMapper;
import com.jf.entity.Attachment;
import com.jf.entity.AttachmentExample;
import com.jf.entity.AttachmentExt;
import com.jf.entity.AttachmentExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AttachmentBiz extends BaseService<Attachment, AttachmentExample> {

	@Autowired
	private AttachmentMapper dao;
	@Autowired
	private AttachmentExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setAttachmentMapper(AttachmentMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public AttachmentExt save(AttachmentExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public Attachment update(Attachment model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		Attachment model = new Attachment();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public AttachmentExt findById(int id){
		return extDao.findById(id);
	}

	public AttachmentExt find(AttachmentExtExample example){
		return extDao.find(example.fill());
	}

	public int count(AttachmentExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, AttachmentExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, AttachmentExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, AttachmentExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(AttachmentExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<AttachmentExt> list(AttachmentExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<AttachmentExt> paginate(AttachmentExtExample example) {
		List<AttachmentExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
