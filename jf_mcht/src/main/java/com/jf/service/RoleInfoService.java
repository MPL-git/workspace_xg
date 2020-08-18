package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.RoleInfoMapper;
import com.jf.entity.MchtUser;
import com.jf.entity.RoleInfo;
import com.jf.entity.RoleInfoExample;
import com.jf.entity.RoleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RoleInfoService {

	@Autowired
	private RoleInfoMapper dao;
	@Autowired
	private RoleUserService roleUserService;
	@Autowired
	private MchtUserService mchtUserService;
	@Resource
	private RoleMenuService roleMenuService;
	@Resource
	private MenuService menuService;

	public RoleInfo save(Integer mchtId, RoleInfo model, List<Integer> menuIdList) {
		boolean isUpdate = model.getId() != null && model.getId() > 0;

		RoleInfo roleInfo;
		if(isUpdate){
			roleInfo = findById(model.getId());
			if(roleInfo == null || !roleInfo.getMchtId().equals(mchtId)){
				throw new BizException("找不到id为" + model.getId() + "的角色信息");
			}
		}else{
			roleInfo = new RoleInfo();
		}

		roleInfo.setRoleName(model.getRoleName());
		roleInfo.setRemarks(model.getRemarks());
		roleInfo.setMchtId(mchtId);
		if(isUpdate){
			update(roleInfo);
		}else{
			save(roleInfo);
		}
		// 保存角色菜单
		roleMenuService.save(roleInfo.getId(), menuIdList);

		return roleInfo;
	}



	public RoleInfo findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public RoleInfo save(RoleInfo model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public RoleInfo update(RoleInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		RoleInfo model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}

		// 该角色下如果有用户，则提示先转移用户到别的角色
		if(roleUserService.findListByRoleId(id).size() > 0){
			throw new BizException("请先转移该角色下的账号再删除");
		}

		// 删除角色菜单
		roleMenuService.deleteByRoleId(id);

		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<RoleInfo> findList(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public List<RoleInfo> findListByMchtId(Integer mchtId) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtId", mchtId);
		return findList(queryObject);
	}

	public Page<RoleInfo> paginate(QueryObject queryObject) {
		RoleInfoExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<RoleInfo> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		Page<RoleInfo> page = new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);

		List<RoleUser> roleUserList;
		List<Integer> userIds;
		List<MchtUser> userList;
		String userNames;
		for(RoleInfo info : page.getList()){
			userNames = "";
			roleUserList = roleUserService.findListByRoleId(info.getId());
			if(roleUserList.size() > 0){
				userIds = new ArrayList<>();
				for(RoleUser roleUser : roleUserList){
					userIds.add(roleUser.getUserId());
				}
				//queryObject = new QueryObject();
				queryObject.addQuery("ids", userIds);
				userList = mchtUserService.findList(queryObject);
				for(MchtUser mchtUser : userList){
					userNames += mchtUser.getUserName() + ",";
				}
				userNames = userNames.substring(0, userNames.length() -1);
			}
			info.put("userNames", userNames);
		}
		return page;
	}

	private RoleInfoExample builderQuery(QueryObject queryObject) {
		RoleInfoExample example = new RoleInfoExample();
		RoleInfoExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("mchtId") != null){
			criteria.andMchtIdEqualTo(queryObject.getQueryToInt("mchtId"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

}
