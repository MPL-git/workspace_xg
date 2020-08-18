package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberActionExtMapper;
import com.jf.dao.MemberActionMapper;
import com.jf.entity.MemberAction;
import com.jf.entity.MemberActionExample;
import com.jf.entity.MemberActionExt;
import com.jf.entity.MemberActionExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberActionBiz extends BaseService<MemberAction, MemberActionExample> {

	@Autowired
	private MemberActionMapper dao;
	@Autowired
	private MemberActionExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberActionMapper(MemberActionMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberActionExt save(MemberActionExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberAction update(MemberAction model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberAction model = new MemberAction();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberActionExt findById(int id){
		return extDao.findById(id);
	}

	public MemberActionExt find(MemberActionExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberActionExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberActionExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberActionExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberActionExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberActionExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberActionExt> list(MemberActionExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberActionExt> paginate(MemberActionExtExample example) {
		List<MemberActionExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
