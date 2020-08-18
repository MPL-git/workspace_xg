package com.jf.controller.command.mcht;

import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.BizException;
import com.jf.entity.MchtUser;
import com.jf.entity.RoleInfo;
import com.jf.service.MenuService;
import com.jf.service.RoleInfoService;
import com.jf.service.RoleMenuService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;

/**
 * 编辑角色
 *
 * @author huangdl
 */
public class CMchtSubAccountRoleEdit extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtSubAccountRoleEdit.class);

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
		if(id > 0){
			RoleInfo roleInfo = roleInfoService.findById(id);
			if(roleInfo == null || !roleInfo.getMchtId().equals(currentUser.getMchtId())){
				throw new BizException("未找到该角色");
			}
			data.put("model", roleInfo);
		}

	}

}
