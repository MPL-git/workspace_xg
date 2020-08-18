package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberCumulativeSignInCopyExtMapper;
import com.jf.dao.MemberCumulativeSignInCopyMapper;
import com.jf.entity.MemberCumulativeSignInCopy;
import com.jf.entity.MemberCumulativeSignInCopyExample;
import com.jf.entity.MemberCumulativeSignInCopyExt;
import com.jf.entity.MemberCumulativeSignInCopyExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberCumulativeSignInCopyBiz extends BaseService<MemberCumulativeSignInCopy, MemberCumulativeSignInCopyExample> {

	@Autowired
	private MemberCumulativeSignInCopyMapper dao;
	@Autowired
	private MemberCumulativeSignInCopyExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberCumulativeSignInCopyMapper(MemberCumulativeSignInCopyMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberCumulativeSignInCopyExt save(MemberCumulativeSignInCopyExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberCumulativeSignInCopy update(MemberCumulativeSignInCopy model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberCumulativeSignInCopy model = new MemberCumulativeSignInCopy();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberCumulativeSignInCopyExt findById(int id){
		return extDao.findById(id);
	}

	public MemberCumulativeSignInCopyExt find(MemberCumulativeSignInCopyExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberCumulativeSignInCopyExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberCumulativeSignInCopyExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberCumulativeSignInCopyExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberCumulativeSignInCopyExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberCumulativeSignInCopyExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberCumulativeSignInCopyExt> list(MemberCumulativeSignInCopyExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberCumulativeSignInCopyExt> paginate(MemberCumulativeSignInCopyExtExample example) {
		List<MemberCumulativeSignInCopyExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
