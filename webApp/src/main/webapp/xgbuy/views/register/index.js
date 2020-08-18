var page_register = $('.page[data-id="register"]:visible'),
	register_code_post = 1,
	platformContactId = '';

if (search_url) {
	for (var i = 0; i < search_url.length; i++) {
		var _a = search_url[i].split('='),
			_b = _a[1];

		switch (_a[0]) {
			case 'platformContactId': platformContactId = _b; break;
		}
	}
}

require(['md5'], function (md5) {
	page_register.find('form').submit(function (e) {
		e.preventDefault();
		var that = $(this),
			_hid = that.find('input[name=password]'),
			_mob = that.find('input[name=mobile]').val(),
			_pwd = that.find('.password').val();

		_hid.val(md5(_pwd));
		
		var dataObj = JSON.parse(formName($(this).serialize()));
		
		dataObj.platformContactId = platformContactId;

		new ajax(getApi.memberRegister, {
			data: dataObj,
			success: function (res) {
				cookieLogin(res, {
					loginCode: _mob,
					password: _pwd
				});

				delayTip('注册成功');
				getUrl('register/success/index.html', 'self');
			}
		});
	});
});

page_register.find('.get-code').click(function () {
	if (!register_code_post) {
		delayTip('正在获取验证码...');
		return;
	}
	register_code_post = 0;
	var that = $(this);

	new ajax(getApi.registerCode, {
		data: {
			mobile: $(this).closest('form').find('input[name=mobile]').val(),
			type: -1
		},
		success: function () {
			require(['qpTime'], function () {
				$.qpTime({
					s: 0,
					e: 6e4 - 1
				}, function (pt) {
					that.html(pt.s);

					if (!pt.t) {
						that.html('获取验证码');
						register_code_post = 1;
					}
				});
			});
		},
		error: function (res) {
			delayTip(res.returnMsg);
			register_code_post = 1;
		}
	});
});