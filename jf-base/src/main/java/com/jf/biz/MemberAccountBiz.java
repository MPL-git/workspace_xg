package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberAccountExtMapper;
import com.jf.dao.MemberAccountMapper;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountExample;
import com.jf.entity.MemberAccountExt;
import com.jf.entity.MemberAccountExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberAccountBiz extends BaseService<MemberAccount, MemberAccountExample> {

	@Autowired
	private MemberAccountMapper dao;
	@Autowired
	private MemberAccountExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberAccountMapper(MemberAccountMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberAccountExt save(MemberAccountExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberAccount update(MemberAccount model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberAccount model = new MemberAccount();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberAccountExt findById(int id){
		return extDao.findById(id);
	}

	public MemberAccountExt find(MemberAccountExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberAccountExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberAccountExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberAccountExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberAccountExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberAccountExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberAccountExt> list(MemberAccountExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberAccountExt> paginate(MemberAccountExtExample example) {
		List<MemberAccountExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
