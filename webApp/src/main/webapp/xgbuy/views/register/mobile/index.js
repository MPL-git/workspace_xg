var register_mobile = $('.page[data-id="register_mobile"]:visible'),
	register_code_post_tel = 1;

$(function () {
	getImgCode();

	register_mobile.find('.get-code').click(function () {
		if (!register_code_post_tel) {
			delayTip('正在获取验证码...');
			return;
		}
		var that = $(this),
			_name = JSON.parse(formName($(this).closest('form').serialize()));

		if (_name.mobile.length != 11) {
			delayTip('请输入正确的手机号码');
			return;
		}

		if (!_name.validCode) {
			delayTip('图形验证码不能为空');
			return;
		}

		register_code_post_tel = 0;

		new ajax(getApi.verificationCode, {
			data: {
				mobile: _name.mobile,
				validCode: _name.validCode
			},
			success: function (res) {
				require(['qpTime'], function () {
					$.qpTime({
						s: 0,
						e: 6e4 - 1
					}, function (pt) {
						that.html(pt.s);

						if (!pt.t) {
							that.html('获取验证码');
							register_code_post_tel = 1;
						}
					});
				});
			},
			error: function (res) {
				register_code_post_tel = 1;
				delayTip(res.returnMsg);
				getImgCode();
			}
		});
	});

	register_mobile.find('form').submit(function (e) {
		e.preventDefault();
		var _serialize = JSON.parse(formName($(this).serialize()));

		new ajax(getApi.updateBindMobile, {
			data: {
				memberId: member.id,
				mobile: _serialize.mobile,
				mobileVerificationCode: _serialize.mobileVerificationCode
			},
			success: function (res) {
				member.app ? back('', 1) : getUrl('set/self', 'self');
			},
			limit: 1
		});
	});
});

function getImgCode() {
	register_mobile.find('.get-vcode').html($('<img src="' + getApi.getCaptcha + '?random=' + Math.random() + '"></img>'));
}