package com.jf.controller.command.mcht;

import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.entity.MchtUser;
import com.jf.service.MchtUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.util.regex.Pattern;

/**
 * 子账号修改密码
 *
 * @author huangdl
 */
public class CMchtSubAccountUserModifyPassword extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtSubAccountUserModifyPassword.class);

	@Resource
	private MchtUserService mchtUserService;

	private String oldPassword;	//旧密码
	private String newPassword;	//新密码
	private String confirmPassword;	//确认密码

	private MchtUser currentUser;

	@Override
	public void init() {
		oldPassword = getPara("oldPassword");
		Assert.notBlank(oldPassword, "请输入旧密码");

		newPassword = getPara("newPassword");
		Assert.notBlank(newPassword, "请输入新密码");
		if(!Pattern.compile("^[\\S]{6,20}$").matcher(newPassword).matches()){
			throw new BizException("新密码必须6-12个字符");
		}

		confirmPassword = getPara("confirmPassword");
		Assert.notBlank(confirmPassword, "请输入确认密码");

		if(!newPassword.equals(confirmPassword)){
			throw new BizException("两次输入的密码不一致");
		}

		currentUser = context.getUserInfo();
	}

	@Override
	public void doCommand() {
		//修改密码
		mchtUserService.modifyPassword(currentUser.getId(), oldPassword, newPassword);
	}

}
