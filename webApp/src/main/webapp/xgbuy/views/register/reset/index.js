var register_reset = $('.page[data-id="register_reset"]:visible');
var register_reset_api = {};

$(function () {
	register_reset_api.tel = getStorage('register_tel');
	
	require(['md5'], function (md5) {
		register_reset.find('form').submit(function (e) {
			e.preventDefault();

			var that = $(this),
				_pwd1 = that.find('.password1'),
				_pwd = that.find('.password');

			if (_pwd1.val() != _pwd.val()) {
				_pwd[0].setCustomValidity('两次输入的密码不匹配');
			} else {
				new ajax(getApi.resetPassword, {
					data: {
						mobile: register_reset_api.tel,
						password: md5(_pwd.val())
					},
					success: function () {
						delayTip('密码重置成功');
						getUrl('register/login/index.html');
					},
					limit: 1
				});
			}
		});
	});

	register_reset.find('.con-1 input').on('input', function () {
		this.setCustomValidity('');
	});
});