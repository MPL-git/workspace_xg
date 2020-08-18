var register_login = $('.page[data-id="register_login"]:visible');

$(function () {
	getImgCode();
	var _td = {};
	traffic.setPv(_td, {
		pageType: '75'
	});

	register_login.find('.submit-pwd').submit(function (e) {
		e.preventDefault();
		registerSign(JSON.parse(formName($(this).serialize())), function () {
			getUrl(location.href, true, function () {
				delayTip('登录成功');
				traffic.setAction(_td, {
					type: 10,
					id: member.id
				});
			});
		});
	});

	// 获取验证码
	var register_login_post_code = 1;

	register_login.find('.get-code').click(function () {
		if (!register_login_post_code) {
			delayTip('正在获取验证码...');
			return;
		}

		var that = $(this),
			_name = JSON.parse(formName($(this).closest('form').serialize()));

		if (_name.loginCode.length != 11) {
			delayTip('请输入正确的手机号码');
			return;
		}

		if (!_name.validCode) {
			delayTip('图形验证码不能为空');
			return;
		}

		register_login_post_code = 0;

		new ajax(getApi.verifiCodeNow, {
			data: {
				mobile: _name.loginCode,
				validCode: _name.validCode
			},
			success: function () {
				require(['qpTime'], function () {
					$.qpTime({
						s: 0,
						e: 6e4 - 1
					}, function (e) {
						that.html(e.s);

						if (!e.t) {
							that.html('获取验证码');
							register_login_post_code = 1;
						}
					});
				});
			},
			error: function (res) {
				delayTip(res.returnMsg);
				register_login_post_code = 1;
				getImgCode();
			}
		});
	});

	// 快速登录
	var _sys = '';

	if (isAndroid) {
		_sys = 'Android';
	} else if (isiOS) {
		_sys = 'IOS';
	}

	register_login.find('.submit-now input[name=system]').val(_sys);

	register_login.find('.submit-now').submit(function (e) {
		e.preventDefault();

		new ajax(getApi.loginNow, {
			data: JSON.parse(formName($(this).serialize())),
			success: function (res) {
				cookieLogin(res);
				console.log(res)
				getUrl(location.href, true, function () {
					delayTip('登录成功');
					traffic.setAction(_td, {
						type: 10,
						id: member.id
					});
				});
			},
			limit: 1
		});
	});

	// 切换
	register_login.find('.tab-head a').click(function () {
		$(this).addClass('active').siblings().removeClass('active');
		register_login.find('.tab-con').eq($(this).index()).addClass('show').siblings().removeClass('show');
	});

	// 按钮变红
	register_login.find('.txt-code').on('keyup', function () {
		registerChangeOn($(this));
	});

	register_login.find('input[name=password]').on('keyup', function () {
		registerChangeOn($(this));
	});

	registerChangeOn(register_login.find('.txt-code'));
	registerChangeOn(register_login.find('input[name=password]'));
});

function registerChangeOn(e) {
	var _sub = e.closest('form').find('.btn-submit');
	e.val() ? _sub.addClass('on') : _sub.removeClass('on');
}

function getImgCode() {
	register_login.find('.get-vcode').html($('<img src="' + getApi.getCaptcha + '?random=' + Math.random() + '"></img>'));
}