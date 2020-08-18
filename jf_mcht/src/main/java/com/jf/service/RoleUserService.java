package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.RoleUserMapper;
import com.jf.entity.RoleUser;
import com.jf.entity.RoleUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RoleUserService {

	@Autowired
	private RoleUserMapper dao;


	/**
	 * 关联角色用户
	 * @param operator 操作人
	 * @param roleId	角色ID
	 * @param userId	用户ID
     * @return
     */
	public RoleUser save(int operator, int roleId, int userId) {
		RoleUser model = findByUserId(userId);
		boolean isUpdate = model != null;

		if(model == null){
			model = new RoleUser();
			model.setUserId(userId);
			model.setCreateBy(operator);
		}
		model.setRoleId(roleId);

		if(isUpdate){
			model.setUpdateBy(operator);
			update(model);
		}else{
			save(model);
		}
		return model;
	}


	public RoleUser findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public RoleUser findByUserId(Integer id) {
		QueryObject queryObject = new QueryObject(1, 1);
		queryObject.addQuery("userId", id);
		List<RoleUser> list = findList(queryObject);
		return list.size() > 0 ? list.get(0) : null;
	}

	public RoleUser save(RoleUser model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public RoleUser update(RoleUser model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		RoleUser model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<RoleUser> findList(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public List<RoleUser> findListByRoleId(int roleId) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("roleId", roleId);
		queryObject.addQuery("delFlag", Const.FLAG_FALSE);
		return findList(queryObject);
	}

	public Page<RoleUser> paginate(QueryObject queryObject) {
		RoleUserExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<RoleUser> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private RoleUserExample builderQuery(QueryObject queryObject) {
		RoleUserExample example = new RoleUserExample();
		RoleUserExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("roleId") != null){
			criteria.andRoleIdEqualTo(queryObject.getQueryToInt("roleId"));
		}
		if(queryObject.getQuery("userId") != null){
			criteria.andUserIdEqualTo(queryObject.getQueryToInt("userId"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

}
