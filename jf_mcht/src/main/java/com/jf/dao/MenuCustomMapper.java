package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.Menu;
import com.jf.entity.MenuCustom;
import com.jf.entity.MenuExample;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MenuCustomMapper extends BaseDao<Menu, MenuExample> {
	List<MenuCustom> queryMenuTree(int parentId);
}