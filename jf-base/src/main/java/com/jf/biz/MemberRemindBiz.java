package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberRemindExtMapper;
import com.jf.dao.MemberRemindMapper;
import com.jf.entity.MemberRemind;
import com.jf.entity.MemberRemindExample;
import com.jf.entity.MemberRemindExt;
import com.jf.entity.MemberRemindExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberRemindBiz extends BaseService<MemberRemind, MemberRemindExample> {

	@Autowired
	private MemberRemindMapper dao;
	@Autowired
	private MemberRemindExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberRemindMapper(MemberRemindMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberRemindExt save(MemberRemindExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberRemind update(MemberRemind model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberRemind model = new MemberRemind();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberRemindExt findById(int id){
		return extDao.findById(id);
	}

	public MemberRemindExt find(MemberRemindExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberRemindExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberRemindExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberRemindExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberRemindExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberRemindExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberRemindExt> list(MemberRemindExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberRemindExt> paginate(MemberRemindExtExample example) {
		List<MemberRemindExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
