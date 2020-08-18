package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.SysAppMessageMemberMapper;
import com.jf.dao.SysAppMessageMemberExtMapper;
import com.jf.entity.SysAppMessageMember;
import com.jf.entity.SysAppMessageMemberExample;
import com.jf.entity.SysAppMessageMemberExt;
import com.jf.entity.SysAppMessageMemberExtExample;
import com.jf.common.base.BaseService;

@Service
public class SysAppMessageMemberBiz extends BaseService<SysAppMessageMember, SysAppMessageMemberExample> {

	@Autowired
	private SysAppMessageMemberMapper dao;
	@Autowired
	private SysAppMessageMemberExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSysAppMessageMemberMapper(SysAppMessageMemberMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SysAppMessageMemberExt save(SysAppMessageMemberExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SysAppMessageMember update(SysAppMessageMember model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SysAppMessageMember model = new SysAppMessageMember();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SysAppMessageMemberExt findById(int id){
		return extDao.findById(id);
	}

	public SysAppMessageMemberExt find(SysAppMessageMemberExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SysAppMessageMemberExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SysAppMessageMemberExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SysAppMessageMemberExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SysAppMessageMemberExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SysAppMessageMemberExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SysAppMessageMemberExt> list(SysAppMessageMemberExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SysAppMessageMemberExt> paginate(SysAppMessageMemberExtExample example) {
		List<SysAppMessageMemberExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
