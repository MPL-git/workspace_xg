var register_mobile_pwd = $('.page[data-id="register_mobilePwd"]:visible'),
	register_mobile_vcode = register_mobile_pwd.find('.get-vcode');

var registerMobilePwdApi = {
	data: {
		codePost: 1
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		$(function () {
			if (search_url) {
				for (var i = 0; i < search_url.length; i++) {
					var _a = search_url[i].split('='),
						_b = _a[1];

					switch (_a[0]) {
						case 'novaplan': _td.novaplan = _b; break;
					}
				}
			}
			
			_td.novaplan && delayTip('开通新星计划前，请先绑定手机号哦~');
			_ts.getImgCode();
		});
	},

	code: function (_this) {
		var $ts = $(_this),
			_ts = this,
			_td = _ts.data;

		if (!_td.codePost) {
			delayTip('正在获取验证码...');
			return;
		}

		var _name = JSON.parse(formName($ts.closest('form').serialize()));

		if (_name.mobile.length != 11) {
			delayTip('请输入正确的手机号码');
			return;
		}

		if (!_name.validCode) {
			delayTip('图形验证码不能为空');
			return;
		}

		_td.codePost = 0;

		new ajax(getApi.verificationCode, {
			data: {
				mobile: _name.mobile,
				validCode: _name.validCode
			},
			success: function () {
				require(['qpTime'], function () {
					$.qpTime({
						s: 0,
						e: 6e4 - 1
					}, function (e) {
						$ts.html(e.s);

						if (!e.t) {
							$ts.html('获取验证码');
							_td.codePost = 1;
						}
					});
				});
			},
			error: function (e) {
				_td.codePost = 1;
				delayTip(e.returnMsg);
				_ts.getImgCode();
			},
			limit: 1
		});
	},

	submit: function (event, _this) {
		defSelf(event);

		require(['md5'], function (md5) {
			var _serialize = JSON.parse(formName($(_this).serialize()));

			if (!_serialize.mobile) {
				delayTip('请输入正确的手机号码');
				return;
			}

			if (!_serialize.password) {
				delayTip('请输入密码');
				return;
			}

			if (!_serialize.mobileVerificationCode) {
				delayTip('请输入验证码');
				return;
			}

			new ajax(member.app ? getApi.visitorsRegisteredMembersH5 : getApi.visitorsRegisteredMembers, {
				data: {
					memberId: member.id,
					mobile: _serialize.mobile,
					password: md5(_serialize.password),
					mobileVerificationCode: _serialize.mobileVerificationCode
				},
				success: function () {
					delayTip('成功绑定手机', 1e3, function () {
						location.reload();
					}, true);
				},
				limit: 1
			});
		});
	},

	getImgCode: function () {
		register_mobile_vcode.html($('<img src="' + getApi.getCaptcha + '?random=' + Math.random() + '"></img>'));
	}
};

registerMobilePwdApi.init();