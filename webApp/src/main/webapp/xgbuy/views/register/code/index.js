var register_code = $('.page[data-id="register_code"]:visible'),
	register_code_vcode = register_code.find('.get-vcode');

var registerCodeApi = {
	data: {
		codePost: 1
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

		new ajax(getApi.mobileCode, {
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

		var _serialize = JSON.parse(formName($(_this).serialize()));

		new ajax(getApi.verifiCode, {
			data: _serialize,
			success: function () {
				setStorage('register_tel', _serialize.mobile);
				getUrl('register/reset/index.html', true);
			},
			limit: 1
		});
	},

	getImgCode: function () {
		register_code_vcode.html($('<img src="' + getApi.getCaptcha + '?random=' + Math.random() + '"></img>'));
	}
};

registerCodeApi.getImgCode();