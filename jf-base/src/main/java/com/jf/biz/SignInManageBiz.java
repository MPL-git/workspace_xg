package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SignInManageExtMapper;
import com.jf.dao.SignInManageMapper;
import com.jf.entity.SignInManage;
import com.jf.entity.SignInManageExample;
import com.jf.entity.SignInManageExt;
import com.jf.entity.SignInManageExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SignInManageBiz extends BaseService<SignInManage, SignInManageExample> {

	@Autowired
	private SignInManageMapper dao;
	@Autowired
	private SignInManageExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSignInManageMapper(SignInManageMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SignInManageExt save(SignInManageExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SignInManage update(SignInManage model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SignInManage model = new SignInManage();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SignInManageExt findById(int id){
		return extDao.findById(id);
	}

	public SignInManageExt find(SignInManageExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SignInManageExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SignInManageExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SignInManageExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SignInManageExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SignInManageExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SignInManageExt> list(SignInManageExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SignInManageExt> paginate(SignInManageExtExample example) {
		List<SignInManageExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
