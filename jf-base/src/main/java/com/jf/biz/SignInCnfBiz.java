package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SignInCnfExtMapper;
import com.jf.dao.SignInCnfMapper;
import com.jf.entity.SignInCnf;
import com.jf.entity.SignInCnfExample;
import com.jf.entity.SignInCnfExt;
import com.jf.entity.SignInCnfExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SignInCnfBiz extends BaseService<SignInCnf, SignInCnfExample> {

	@Autowired
	private SignInCnfMapper dao;
	@Autowired
	private SignInCnfExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSignInCnfMapper(SignInCnfMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SignInCnfExt save(SignInCnfExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SignInCnf update(SignInCnf model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SignInCnf model = new SignInCnf();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SignInCnfExt findById(int id){
		return extDao.findById(id);
	}

	public SignInCnfExt find(SignInCnfExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SignInCnfExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SignInCnfExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SignInCnfExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SignInCnfExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SignInCnfExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SignInCnfExt> list(SignInCnfExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SignInCnfExt> paginate(SignInCnfExtExample example) {
		List<SignInCnfExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
