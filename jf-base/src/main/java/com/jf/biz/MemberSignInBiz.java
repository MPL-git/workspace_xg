package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberSignInExtMapper;
import com.jf.dao.MemberSignInMapper;
import com.jf.entity.MemberSignIn;
import com.jf.entity.MemberSignInExample;
import com.jf.entity.MemberSignInExt;
import com.jf.entity.MemberSignInExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberSignInBiz extends BaseService<MemberSignIn, MemberSignInExample> {

	@Autowired
	private MemberSignInMapper dao;
	@Autowired
	private MemberSignInExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberSignInMapper(MemberSignInMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberSignInExt save(MemberSignInExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberSignIn update(MemberSignIn model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberSignIn model = new MemberSignIn();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberSignInExt findById(int id){
		return extDao.findById(id);
	}

	public MemberSignInExt find(MemberSignInExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberSignInExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberSignInExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberSignInExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberSignInExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberSignInExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberSignInExt> list(MemberSignInExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberSignInExt> paginate(MemberSignInExtExample example) {
		List<MemberSignInExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
