package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.RoleMenuMapper;
import com.jf.entity.Menu;
import com.jf.entity.MenuCustom;
import com.jf.entity.RoleMenu;
import com.jf.entity.RoleMenuExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RoleMenuService {

	@Autowired
	private RoleMenuMapper dao;
	@Autowired
	private MenuService menuService;

	public void save(Integer roleId, List<Integer> menuIdList) {
		// 删除旧的角色菜单
		deleteByRoleId(roleId);

		// 保存新的角色菜单
		for(Integer menuId : menuIdList){
			save(roleId, menuId);
		}

	}

	public RoleMenu save(int roleId, int menuId){
		RoleMenu model = new RoleMenu();
		model.setRoleId(roleId);
		model.setMenuId(menuId);
		return save(model);
	}


	/**
	 * 角色菜单
     */
	public List<MenuCustom> findRoleMenuList(int roleId) {
		List<MenuCustom> menuList = new ArrayList<>();

		List<RoleMenu> roleMenuList = findListByRoleId(roleId);
		if(roleMenuList.size() > 0){
			List<MenuCustom> allMenuList = menuService.queryMenuTree(0);
			for(MenuCustom menu : allMenuList){
				for(RoleMenu roleMenu : roleMenuList){
					if(roleMenu.getMenuId() == menu.getId()){
						menuList.add(menu);
						break;
					}
				}
				if(menu.getSubMenus() !=null && menu.getSubMenus().size() > 0){
					List<Menu> subMenuList = new ArrayList<>();
					for(Menu subMenu : menu.getSubMenus()){
						for(RoleMenu roleMenu : roleMenuList){
							if(roleMenu.getMenuId() == subMenu.getId()){
								subMenuList.add(subMenu);
								break;
							}
						}
					}
					menu.setSubMenus(subMenuList);
				}
			}
		}

		return menuList;
	}


	public RoleMenu findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public RoleMenu save(RoleMenu model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public RoleMenu update(RoleMenu model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		RoleMenu model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		dao.deleteByPrimaryKey(id);
	}

	public void deleteByRoleId(int roleId){
		List<RoleMenu> list = findListByRoleId(roleId);
		for(RoleMenu roleMenu : list){
			delete(roleMenu.getId());
		}
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<RoleMenu> findList(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public List<RoleMenu> findListByRoleId(int roleId) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("roleId", roleId);
		queryObject.addQuery("delFlag", Const.FLAG_FALSE);
		return findList(queryObject);
	}

	public Page<RoleMenu> paginate(QueryObject queryObject) {
		RoleMenuExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<RoleMenu> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private RoleMenuExample builderQuery(QueryObject queryObject) {
		RoleMenuExample example = new RoleMenuExample();
		RoleMenuExample.Criteria criteria = example.createCriteria();
		if(queryObject.getQuery("delFlag") != null){
			criteria.andDelFlagEqualTo(queryObject.getQueryToStr("delFlag"));
		}
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("roleId") != null){
			criteria.andRoleIdEqualTo(queryObject.getQueryToInt("roleId"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

}
