package com.jf.controller.command.mcht;

import com.alibaba.fastjson.JSONArray;
import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.util.StrKit;
import com.jf.entity.MchtUser;
import com.jf.entity.RoleInfo;
import com.jf.service.RoleInfoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 保存角色
 *
 * @author huangdl
 */
public class CMchtSubAccountRoleSave extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtSubAccountRoleSave.class);

	@Resource
	private RoleInfoService roleInfoService;

	private RoleInfo model;
	private String menus;

	private MchtUser currentUser;

	@Override
	public void init() {
		model = getBean(RoleInfo.class, "model");
		Assert.notBlank(model.getRoleName(), "角色名称不能为空");
		Assert.lessThan(StrKit.length(model.getRoleName()), 24, "角色名称太长，请不要超过12个汉字");

		menus = getPara("menus");

		currentUser = context.getUserInfo();
	}

	@Override
	public void doCommand() {
		List<Integer> menuIdList = new ArrayList<>();
		JSONArray menuArray = JSONArray.parseArray(menus);
		for (int index = 0; index < menuArray.size(); index ++) {
			menuIdList.add(menuArray.getJSONObject(index).getIntValue("id"));
		}

		RoleInfo roleInfo = roleInfoService.save(currentUser.getMchtId(), model, menuIdList);
		data.put("roleInfo", roleInfo);
	}

}
