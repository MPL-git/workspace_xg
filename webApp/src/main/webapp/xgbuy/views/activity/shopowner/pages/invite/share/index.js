var a_invite_share_page = $('.page[data-id="activity_shopowner_pages_invite_share"]:visible'),
	a_invite_share_page_phone = a_invite_share_page.find('.phone-inp'),
	a_invite_share_page_code = a_invite_share_page.find('.code-inp'),
	a_invite_share_page_code_bt = a_invite_share_page.find('.code-bt'),
	a_invite_share_page_vcode = a_invite_share_page.find('.vcode-inp');

var aInviteShareApi = {
	data: {
		setInter: '',
		time: ''
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
						case 'tab': _td.type = _b; break;
						case 'invitationCode': _td.invitationCode = _b; break;
					}
				}
			}

			_ts.getImgCode();
		});
	},

	getImgCode: function () {
		a_invite_share_page.find('.vcode-img').attr('src', getApi.getCaptcha + '?random=' + Math.random());
	},

	getCode: function () {
		var _ts = this,
			_td = _ts.data,
			phone = a_invite_share_page_phone.val(),
			vcode = a_invite_share_page_vcode.val(),
			reg = /^1\d{10}$/;

		if (!reg.test(phone)) {
			delayTip('请输入正确的手机号码');
			return
		}

		if (_td.time) {
			delayTip('60秒内不能重复获取验证码，请稍后在尝试');
		} else {
			new ajax(getApi.registerCode, {
				data: {
					mobile: phone,
					validCode: vcode
				},
				success: function (res) {
					_td.time = 60;

					_td.setInter = setInterval (function () {
						if (_td.time == 0) {
							clearInterval(_td.setInter);
							a_invite_share_page_code_bt.text('获取验证码');
							return;
						}
						_td.time--;
						a_invite_share_page_code_bt.text(_td.time);
					}, 1e3);
				},
				limit: 1
			});
		}
	},

	submit: function () {
		var _ts = this,
			_td = _ts.data,
			phone = a_invite_share_page_phone.val(),
			code = a_invite_share_page_code.val(),
			vcode = a_invite_share_page_vcode.val(),
			reg = /^1\d{10}$/,
			_sys;

		if (!reg.test(phone)) {
			delayTip('请输入正确的手机号码');
			return;
		}

		if (code == '') {
			delayTip('验证码不可为空');
			return;
		}

		if (isAndroid) {
			_sys = 'Android';
		} else if (isiOS) {
			_sys = 'IOS';
		}

		if (isWeiXin) {
			new ajax(member.app ? getApi.visitorsRegisteredMembersH5 : getApi.visitorsRegisteredMembers, {
				data: {
					memberId: member.id,
					mobile: phone,
					password: '',
					mobileVerificationCode: code,
					type: 2
				},
				success: _ts.package,
				limit: 1
			});
		} else {
			new ajax(getApi.loginNow, {
				data: {
					invitationCode: _td.invitationCode,
					loginCode: phone,
					verifyCode: code,
					validCode: vcode,
					system: _sys
				},
				success: _ts.package,
				limit: 1
			});
		}
	},

	package: function () {
		new ajax(getApi.couponEnjoy, {
			data: {
				type: 2
			},
			success: function () {
				delayTip('恭喜您成功领取500元大礼包~');

				setTimeout(function () {
					getUrl('activity/shopowner/pages/invite/share/success/index.html', 'self');
				}, 2e3);
			}
		});
	},

	pageScroll: function () {
		a_invite_share_page.find('.scroll-y').scrollTop(font_size * 8);
	}
};

aInviteShareApi.init();