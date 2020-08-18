package com.jf.controller;

import com.alibaba.fastjson.JSONArray;
import com.jf.common.constant.Const;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.controller.command.mcht.CMchtSubAccountRoleEdit;
import com.jf.controller.command.mcht.CMchtSubAccountRoleMenuTree;
import com.jf.controller.command.mcht.CMchtSubAccountRoleSave;
import com.jf.controller.command.mcht.CMchtSubAccountUserModifyPassword;
import com.jf.controller.command.mcht.CMchtSubAccountUserSave;
import com.jf.entity.MchtUser;
import com.jf.entity.RoleInfo;
import com.jf.entity.RoleUser;
import com.jf.service.MchtUserService;
import com.jf.service.RoleInfoService;
import com.jf.service.RoleUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("subAccount")
public class MchtSubAccountController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(MchtSubAccountController.class);

	@Resource
	private RoleInfoService roleInfoService;
	@Resource
	private RoleUserService roleUserService;
	@Resource
	private MchtUserService mchtUserService;

	@RequestMapping
	public String index() {

		return page("subAccount/listRole");
	}

	@RequestMapping("listRolePage")
	public String listRolePage() {

		return page("subAccount/listRole");
	}

	@ResponseBody
	@RequestMapping("listRole")
	public String listRole(QueryObject queryObject) {
		queryObject.addQuery("mchtId", getUserInfo().getMchtId());
		queryObject.addQuery("roleName", getPara("roleName").trim());
		queryObject.addSort("create_date", QueryObject.SORT_DESC);

		Page<RoleInfo> page = roleInfoService.paginate(queryObject);

		Map<String, Object> data = new HashMap<>();
		data.put("page", page);
		return json(data);
	}

	@RequestMapping("editRole")
	public String editRole() {

		return page(doAction(new CMchtSubAccountRoleEdit()), "subAccount/editRole");
	}

	@RequestMapping("viewRole")
	public String viewRole() {

		return page(doAction(new CMchtSubAccountRoleEdit()), "subAccount/viewRole");
	}

	@ResponseBody
	@RequestMapping("getRoleMenuTree")
	public String getRoleMenuTree() {

		return json(doAction(new CMchtSubAccountRoleMenuTree()));
	}

	@ResponseBody
	@RequestMapping("saveRole")
	public String saveRole(RoleInfo model) {

		return json(doAction(new CMchtSubAccountRoleSave()));
	}

	@ResponseBody
	@RequestMapping("deleteRole")
	public String deleteRole(String ids) {
		Assert.notBlank(ids, "ID不能为空");
		JSONArray idArray = JSONArray.parseArray(ids);
		for (int index = 0; index < idArray.size(); index ++) {
			roleInfoService.delete(idArray.getIntValue(index));
		}

		return json();
	}

	@RequestMapping("listUserPage")
	public String listUserPage() {

		return page("subAccount/listUser");
	}

	@ResponseBody
	@RequestMapping("listUser")
	public String listUser(QueryObject queryObject) {
		queryObject.addQuery("mchtId", getUserInfo().getMchtId());
		queryObject.addQuery("userName", getPara("userName"));
		queryObject.addQuery("isPrimary", Const.FLAG_FALSE);
		queryObject.addSort("status", QueryObject.SORT_ASC);
		queryObject.addSort("create_date", QueryObject.SORT_DESC);

		Page<MchtUser> page = mchtUserService.paginate(queryObject);
		RoleUser roleUser;
		for(MchtUser mchtUser : page.getList()){
			roleUser = roleUserService.findByUserId(mchtUser.getId());
			if(roleUser != null){
				RoleInfo roleInfo = roleInfoService.findById(roleUser.getRoleId());
				mchtUser.put("roleId", roleInfo.getId());
				mchtUser.put("roleName", roleInfo.getRoleName());
			}

			// 最后登录时间
			mchtUser.put("lastLoginTime", new Date());
		}

		Map<String, Object> data = new HashMap<>();
		data.put("page", page);
		return json(data);
	}

	@RequestMapping("editUser")
	public String editUser(Integer id) {
		MchtUser currentUser = getUserInfo();

		Map<String, Object> data = new HashMap<>();
		MchtUser primaryUser = mchtUserService.findPrimaryByMchtId(currentUser.getMchtId());
		data.put("primaryUser", primaryUser);	//主账号

		List<RoleInfo> roleList = roleInfoService.findListByMchtId(currentUser.getMchtId());
		data.put("roleList", roleList);	//角色列表

		if(id != null && id > 0){
			MchtUser model = mchtUserService.findById(id);
			if(model == null || !model.getMchtId().equals(currentUser.getMchtId())){
				throw new BizException("找不到该用户");
			}
			RoleUser roleUser = roleUserService.findByUserId(model.getId());
			model.put("roleId", roleUser.getRoleId());
			model.setUserCode(model.getUserCode().substring(primaryUser.getUserCode().length()+1));
			data.put("model", model);
		}
		return page(data, "subAccount/editUser");
	}

	@ResponseBody
	@RequestMapping("saveUser")
	public String saveUser() {

		return json(doAction(new CMchtSubAccountUserSave()));
	}

	@RequestMapping("modifyPassword")
	public String modifyPassword() {
		return page("subAccount/modifyPassword");
	}

	@ResponseBody
	@RequestMapping("modifyPasswordCommit")
	public String modifyPasswordCommit() {

		return json(doAction(new CMchtSubAccountUserModifyPassword()));
	}


}
