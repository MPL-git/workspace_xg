package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MenuCustomMapper;
import com.jf.dao.MenuMapper;
import com.jf.entity.Menu;
import com.jf.entity.MenuCustom;
import com.jf.entity.MenuExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuService extends BaseService<Menu,MenuExample> {
	@Autowired
	private MenuMapper dao;
	
	@Autowired
	private MenuCustomMapper menuCustomMapper;
	
	@Autowired
	public void setMenuMapper(MenuMapper menuMapper) {
		super.setDao(menuMapper);
		this.dao = menuMapper;
	}
	
	public List<MenuCustom> queryMenuTree(int parentId){
		return menuCustomMapper.queryMenuTree(parentId);
	}



	public List<Menu> findList(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	private MenuExample builderQuery(QueryObject queryObject) {
		MenuExample example = new MenuExample();
		MenuExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("ids") != null){
			List<Integer> ids = queryObject.getQuery("ids");
			criteria.andIdIn(ids);
		}
		if(queryObject.getQuery("parentId") != null){
			criteria.andParentIdEqualTo(queryObject.getQueryToInt("parentId"));
		}
		if(queryObject.getQuery("menuName") != null){
			criteria.andMenuNameEqualTo(queryObject.getQueryToStr("menuName"));
		}
		if(queryObject.getQuery("filterMenuName") != null){
			criteria.andMenuNameNotEqualTo(queryObject.getQueryToStr("filterMenuName"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
}
