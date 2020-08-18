package com.jf.controller.command.mcht;

import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.QueryObject;
import com.jf.entity.MchtUser;
import com.jf.entity.Menu;
import com.jf.entity.RoleInfo;
import com.jf.entity.RoleMenu;
import com.jf.service.MenuService;
import com.jf.service.RoleInfoService;
import com.jf.service.RoleMenuService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * 获取角色菜单树
 *
 * @author huangdl
 */
public class CMchtSubAccountRoleMenuTree extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtSubAccountRoleMenuTree.class);

	@Resource
	private RoleInfoService roleInfoService;
	@Resource
	private RoleMenuService roleMenuService;
	@Resource
	private MenuService menuService;

	private int id;

	private MchtUser currentUser;

	@Override
	public void init() {
		id = getParaToInt("id");

		currentUser = context.getUserInfo();
	}

	@Override
	public void doCommand() {
//		List<MenuCustom> menuList = menuService.queryMenuTree(0);
//		for(MenuCustom menu : menuList){
//			menu.put("open", false);
//			menu.put("checked", false);
//			if(menu.getMenuName().equals("子账号管理")){
//				menu.put("isHidden", true);
//			}
//
//			if(menu.getSubMenus() !=null && menu.getSubMenus().size() > 0){
//				for(Menu subMenu : menu.getSubMenus()){
//					if(subMenu.getMenuName().equals("子账号管理")){
//						subMenu.put("isHidden", true);
//					}
//				}
//			}
//		}
//		data.put("menuList", menuList);

		// 过滤掉特殊菜单
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("parentId", 0);
		queryObject.addQuery("filterMenuName", "子账号管理");
		List<Menu> menuList = menuService.findList(queryObject);
		List<Menu> subMenus;
		for(Menu menu : menuList){
			queryObject.addQuery("parentId", menu.getId());
			subMenus = menuService.findList(queryObject);
			menu.put("subMenus", subMenus);
		}
		data.put("menuList", menuList);

		if(id > 0){
			RoleInfo roleInfo = roleInfoService.findById(id);
			if(roleInfo == null || !roleInfo.getMchtId().equals(currentUser.getMchtId())){
				throw new BizException("未找到该角色");
			}
			List<RoleMenu> roleMenuList = roleMenuService.findListByRoleId(roleInfo.getId());
			if(roleMenuList.size() > 0){
				for(Menu menu : menuList){
					for(RoleMenu roleMenu : roleMenuList){
						if(roleMenu.getMenuId() == menu.getId()){
							menu.put("checked", true);
						}
					}
					subMenus = menu.get("subMenus");
					if(subMenus !=null && subMenus.size() > 0){
						for(Menu subMenu : subMenus){
							for(RoleMenu roleMenu : roleMenuList){
								if(roleMenu.getMenuId() == subMenu.getId()){
									subMenu.put("checked", true);
								}
							}
						}
					}
				}
			}
		}

	}

}
