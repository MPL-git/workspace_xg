package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberSignInDtlExtMapper;
import com.jf.dao.MemberSignInDtlMapper;
import com.jf.entity.MemberSignInDtl;
import com.jf.entity.MemberSignInDtlExample;
import com.jf.entity.MemberSignInDtlExt;
import com.jf.entity.MemberSignInDtlExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberSignInDtlBiz extends BaseService<MemberSignInDtl, MemberSignInDtlExample> {

	@Autowired
	private MemberSignInDtlMapper dao;
	@Autowired
	private MemberSignInDtlExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberSignInDtlMapper(MemberSignInDtlMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberSignInDtlExt save(MemberSignInDtlExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberSignInDtl update(MemberSignInDtl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberSignInDtl model = new MemberSignInDtl();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberSignInDtlExt findById(int id){
		return extDao.findById(id);
	}

	public MemberSignInDtlExt find(MemberSignInDtlExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberSignInDtlExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberSignInDtlExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberSignInDtlExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberSignInDtlExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberSignInDtlExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberSignInDtlExt> list(MemberSignInDtlExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberSignInDtlExt> paginate(MemberSignInDtlExtExample example) {
		List<MemberSignInDtlExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
