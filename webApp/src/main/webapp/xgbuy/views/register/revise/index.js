var register_revise = $('.page[data-id="register_revise"]:visible');

$(function () {
	require(['md5'], function (md5) {
		if (getStorage('haspwd') == 1) {
			register_revise.find('.password0').parent().remove();

			$('title').html('设置密码');
			$('.page:visible header p').html('设置密码');

			register_revise.find('form').submit(function (e) {
				e.preventDefault();
				var that = $(this),
					_pwd1 = that.find('.password1'),
					_pwd = that.find('.password');

				if (_pwd1.val() != _pwd.val()) {
					_pwd[0].setCustomValidity('两次输入的密码不匹配');
				} else {
					new ajax(getApi.setPassword, {
						data: {
							memberId: member.id,
							newPwd: md5(_pwd.val())
						},
						success: function () {
							new ajax(getApi.loginout, {
								success: function () {
									delayTip('密码设置成功');
									getUrl('my');
								}
							});
						}
					});
				}
			});
		} else {
			register_revise.find('form').submit(function (e) {
				e.preventDefault();
				var that = $(this),
					_pwd0 = that.find('.password0'),
					_pwd1 = that.find('.password1'),
					_pwd = that.find('.password');

				if (_pwd1.val() != _pwd.val()) {
					_pwd[0].setCustomValidity('两次输入的密码不匹配');
				} else {
					new ajax(getApi.updatePassword, {
						data: {
							memberId: member.id,
							oldPwd: md5(_pwd0.val()),
							newPwd: md5(_pwd.val())
						},
						success: function () {
							getStorage('member_app') && (member.app = 1);

							if (member.app) {
								setStorage('member_app', 1);
								back();
							} else {
								new ajax(getApi.loginout, {
									success: function () {
										getUrl('my', false, function () {
											getUrl('register/login/index.html', true);
										});

										delayTip('密码修改成功');
									}
								});
							}
						}
					});
				}
			});
		}
	});

	register_revise.find('.con-1 input').on('input', function () {
		this.setCustomValidity('');
	});
});