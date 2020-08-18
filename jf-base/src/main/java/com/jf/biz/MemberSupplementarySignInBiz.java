package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberSupplementarySignInExtMapper;
import com.jf.dao.MemberSupplementarySignInMapper;
import com.jf.entity.MemberSupplementarySignIn;
import com.jf.entity.MemberSupplementarySignInExample;
import com.jf.entity.MemberSupplementarySignInExt;
import com.jf.entity.MemberSupplementarySignInExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberSupplementarySignInBiz extends BaseService<MemberSupplementarySignIn, MemberSupplementarySignInExample> {

	@Autowired
	private MemberSupplementarySignInMapper dao;
	@Autowired
	private MemberSupplementarySignInExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberSupplementarySignInMapper(MemberSupplementarySignInMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberSupplementarySignInExt save(MemberSupplementarySignInExt model){
		if (model.getId() != null && model.getId() > 0) {
			//model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberSupplementarySignIn update(MemberSupplementarySignIn model){
		//model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberSupplementarySignIn model = new MemberSupplementarySignIn();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberSupplementarySignInExt findById(int id){
		return extDao.findById(id);
	}

	public MemberSupplementarySignInExt find(MemberSupplementarySignInExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberSupplementarySignInExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberSupplementarySignInExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberSupplementarySignInExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberSupplementarySignInExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberSupplementarySignInExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberSupplementarySignInExt> list(MemberSupplementarySignInExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberSupplementarySignInExt> paginate(MemberSupplementarySignInExtExample example) {
		List<MemberSupplementarySignInExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
