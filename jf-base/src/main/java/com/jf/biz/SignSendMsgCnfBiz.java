package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SignSendMsgCnfExtMapper;
import com.jf.dao.SignSendMsgCnfMapper;
import com.jf.entity.SignSendMsgCnf;
import com.jf.entity.SignSendMsgCnfExample;
import com.jf.entity.SignSendMsgCnfExt;
import com.jf.entity.SignSendMsgCnfExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SignSendMsgCnfBiz extends BaseService<SignSendMsgCnf, SignSendMsgCnfExample> {

	@Autowired
	private SignSendMsgCnfMapper dao;
	@Autowired
	private SignSendMsgCnfExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSignSendMsgCnfMapper(SignSendMsgCnfMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SignSendMsgCnfExt save(SignSendMsgCnfExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SignSendMsgCnf update(SignSendMsgCnf model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SignSendMsgCnf model = new SignSendMsgCnf();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SignSendMsgCnfExt findById(int id){
		return extDao.findById(id);
	}

	public SignSendMsgCnfExt find(SignSendMsgCnfExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SignSendMsgCnfExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SignSendMsgCnfExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SignSendMsgCnfExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SignSendMsgCnfExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SignSendMsgCnfExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SignSendMsgCnfExt> list(SignSendMsgCnfExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SignSendMsgCnfExt> paginate(SignSendMsgCnfExtExample example) {
		List<SignSendMsgCnfExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
