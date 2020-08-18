package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.AttachmentHistoryExtMapper;
import com.jf.dao.AttachmentHistoryMapper;
import com.jf.entity.AttachmentHistory;
import com.jf.entity.AttachmentHistoryExample;
import com.jf.entity.AttachmentHistoryExt;
import com.jf.entity.AttachmentHistoryExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AttachmentHistoryBiz extends BaseService<AttachmentHistory, AttachmentHistoryExample> {

	@Autowired
	private AttachmentHistoryMapper dao;
	@Autowired
	private AttachmentHistoryExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setAttachmentHistoryMapper(AttachmentHistoryMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public AttachmentHistoryExt save(AttachmentHistoryExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public AttachmentHistory update(AttachmentHistory model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		AttachmentHistory model = new AttachmentHistory();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public AttachmentHistoryExt findById(int id){
		return extDao.findById(id);
	}

	public AttachmentHistoryExt find(AttachmentHistoryExtExample example){
		return extDao.find(example.fill());
	}

	public int count(AttachmentHistoryExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, AttachmentHistoryExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, AttachmentHistoryExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, AttachmentHistoryExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(AttachmentHistoryExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<AttachmentHistoryExt> list(AttachmentHistoryExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<AttachmentHistoryExt> paginate(AttachmentHistoryExtExample example) {
		List<AttachmentHistoryExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
