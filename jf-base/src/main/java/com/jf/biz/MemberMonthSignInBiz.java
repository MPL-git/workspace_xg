package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberMonthSignInExtMapper;
import com.jf.dao.MemberMonthSignInMapper;
import com.jf.entity.MemberMonthSignIn;
import com.jf.entity.MemberMonthSignInExample;
import com.jf.entity.MemberMonthSignInExt;
import com.jf.entity.MemberMonthSignInExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberMonthSignInBiz extends BaseService<MemberMonthSignIn, MemberMonthSignInExample> {

	@Autowired
	private MemberMonthSignInMapper dao;
	@Autowired
	private MemberMonthSignInExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberMonthSignInMapper(MemberMonthSignInMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberMonthSignInExt save(MemberMonthSignInExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberMonthSignIn update(MemberMonthSignIn model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberMonthSignIn model = new MemberMonthSignIn();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberMonthSignInExt findById(int id){
		return extDao.findById(id);
	}

	public MemberMonthSignInExt find(MemberMonthSignInExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberMonthSignInExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberMonthSignInExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberMonthSignInExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberMonthSignInExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberMonthSignInExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberMonthSignInExt> list(MemberMonthSignInExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberMonthSignInExt> paginate(MemberMonthSignInExtExample example) {
		List<MemberMonthSignInExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
