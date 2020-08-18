package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberCumulativeSignInExtMapper;
import com.jf.dao.MemberCumulativeSignInMapper;
import com.jf.entity.MemberCumulativeSignIn;
import com.jf.entity.MemberCumulativeSignInExample;
import com.jf.entity.MemberCumulativeSignInExt;
import com.jf.entity.MemberCumulativeSignInExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberCumulativeSignInBiz extends BaseService<MemberCumulativeSignIn, MemberCumulativeSignInExample> {

	@Autowired
	private MemberCumulativeSignInMapper dao;
	@Autowired
	private MemberCumulativeSignInExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberCumulativeSignInMapper(MemberCumulativeSignInMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberCumulativeSignInExt save(MemberCumulativeSignInExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberCumulativeSignIn update(MemberCumulativeSignIn model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberCumulativeSignIn model = new MemberCumulativeSignIn();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberCumulativeSignInExt findById(int id){
		return extDao.findById(id);
	}

	public MemberCumulativeSignInExt find(MemberCumulativeSignInExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberCumulativeSignInExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberCumulativeSignInExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberCumulativeSignInExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberCumulativeSignInExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberCumulativeSignInExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberCumulativeSignInExt> list(MemberCumulativeSignInExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberCumulativeSignInExt> paginate(MemberCumulativeSignInExtExample example) {
		List<MemberCumulativeSignInExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
