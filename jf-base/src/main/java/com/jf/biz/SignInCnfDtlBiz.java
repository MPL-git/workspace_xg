package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SignInCnfDtlExtMapper;
import com.jf.dao.SignInCnfDtlMapper;
import com.jf.entity.SignInCnfDtl;
import com.jf.entity.SignInCnfDtlExample;
import com.jf.entity.SignInCnfDtlExt;
import com.jf.entity.SignInCnfDtlExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SignInCnfDtlBiz extends BaseService<SignInCnfDtl, SignInCnfDtlExample> {

	@Autowired
	private SignInCnfDtlMapper dao;
	@Autowired
	private SignInCnfDtlExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSignInCnfDtlMapper(SignInCnfDtlMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SignInCnfDtlExt save(SignInCnfDtlExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SignInCnfDtl update(SignInCnfDtl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SignInCnfDtl model = new SignInCnfDtl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SignInCnfDtlExt findById(int id){
		return extDao.findById(id);
	}

	public SignInCnfDtlExt find(SignInCnfDtlExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SignInCnfDtlExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SignInCnfDtlExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SignInCnfDtlExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SignInCnfDtlExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SignInCnfDtlExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SignInCnfDtlExt> list(SignInCnfDtlExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SignInCnfDtlExt> paginate(SignInCnfDtlExtExample example) {
		List<SignInCnfDtlExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
