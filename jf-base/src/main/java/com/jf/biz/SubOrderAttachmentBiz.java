package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SubOrderAttachmentExtMapper;
import com.jf.dao.SubOrderAttachmentMapper;
import com.jf.entity.SubOrderAttachment;
import com.jf.entity.SubOrderAttachmentExample;
import com.jf.entity.SubOrderAttachmentExt;
import com.jf.entity.SubOrderAttachmentExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SubOrderAttachmentBiz extends BaseService<SubOrderAttachment, SubOrderAttachmentExample> {

	@Autowired
	private SubOrderAttachmentMapper dao;
	@Autowired
	private SubOrderAttachmentExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSubOrderAttachmentMapper(SubOrderAttachmentMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SubOrderAttachmentExt save(SubOrderAttachmentExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SubOrderAttachment update(SubOrderAttachment model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SubOrderAttachment model = new SubOrderAttachment();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SubOrderAttachmentExt findById(int id){
		return extDao.findById(id);
	}

	public SubOrderAttachmentExt find(SubOrderAttachmentExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SubOrderAttachmentExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SubOrderAttachmentExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SubOrderAttachmentExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SubOrderAttachmentExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SubOrderAttachmentExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SubOrderAttachmentExt> list(SubOrderAttachmentExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SubOrderAttachmentExt> paginate(SubOrderAttachmentExtExample example) {
		List<SubOrderAttachmentExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
