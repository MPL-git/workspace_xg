package com.jf.controller.command.mcht;

import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.util.StrKit;
import com.jf.entity.MchtUser;
import com.jf.entity.RoleInfo;
import com.jf.service.MchtUserService;
import com.jf.service.RoleInfoService;
import com.jf.service.RoleUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.util.regex.Pattern;

/**
 * 保存子账号
 *
 * @author huangdl
 */
public class CMchtSubAccountUserSave extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtSubAccountUserSave.class);

	@Resource
	private MchtUserService mchtUserService;
	@Resource
	private RoleInfoService roleInfoService;
	@Resource
	private RoleUserService roleUserService;

	private MchtUser model;
	private int roleId;

	private MchtUser currentUser;

	@Override
	public void init() {
		model = getBean(MchtUser.class, "model");

		roleId = getParaToInt("roleId");
		Assert.notZero(roleId, "请选择角色");

		Assert.notBlank(model.getUserCode(), "用户名不能为空");
		Assert.lessThan(StrKit.length(model.getUserCode()), 24, "用户名太长，请不要超过24个字符");

		if(model.getId() == null || model.getId() == 0){
			Assert.notBlank(model.getPassword(), "密码不能为空");
		}
		if(StrKit.notBlank(model.getPassword())){
			if(!Pattern.compile("^[\\S]{6,20}$").matcher(model.getPassword()).matches()){
				throw new BizException("密码必须6-12个字符");
			}
		}

		Assert.notBlank(model.getUserName(), "姓名不能为空");
		Assert.lessThan(StrKit.length(model.getUserName()), 24, "姓名太长，请不要超过24个字符");

		if(StrKit.notBlank(model.getMobile())){
			if(!Pattern.compile("^1\\d{10}$").matcher(model.getMobile()).matches()){
				throw new BizException("手机号无效");
			}
		}

		currentUser = context.getUserInfo();
	}

	@Override
	public void doCommand() {
		RoleInfo role = roleInfoService.findById(roleId);
		if(role == null || !role.getMchtId().equals(currentUser.getMchtId())){
			throw new BizException("找不到该角色");
		}

		// 保存子账号
		MchtUser mchtUser = mchtUserService.save(currentUser.getId(), model);
		// 关联角色
		roleUserService.save(currentUser.getId(), roleId, mchtUser.getId());
		data.put("mchtUser", mchtUser);
	}

}
