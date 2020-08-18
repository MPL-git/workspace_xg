package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.RoleUserMapper;
import com.jf.dao.RoleUserExtMapper;
import com.jf.entity.RoleUser;
import com.jf.entity.RoleUserExample;
import com.jf.entity.RoleUserExt;
import com.jf.entity.RoleUserExtExample;
import com.jf.common.base.BaseService;

@Service
public class RoleUserBiz extends BaseService<RoleUser, RoleUserExample> {

	@Autowired
	private RoleUserMapper dao;
	@Autowired
	private RoleUserExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setRoleUserMapper(RoleUserMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public RoleUserExt save(RoleUserExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public RoleUser update(RoleUser model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		RoleUser model = new RoleUser();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public RoleUserExt findById(int id){
		return extDao.findById(id);
	}

	public RoleUserExt find(RoleUserExtExample example){
		return extDao.find(example.fill());
	}

	public int count(RoleUserExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, RoleUserExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, RoleUserExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, RoleUserExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(RoleUserExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<RoleUserExt> list(RoleUserExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<RoleUserExt> paginate(RoleUserExtExample example) {
		List<RoleUserExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
