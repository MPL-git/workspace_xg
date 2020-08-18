var seller_zizao = $('.page[data-id="seller_zizao"]:visible'),
	seller_zizao_main = seller_zizao.find('.main'),
	seller_zizao_row2 = seller_zizao.find('.row-2'),

	seller_zizao_code = '';

var sellerZizaoApi = {
	data: {
		mchtId: ''
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'mchtId': _td.mchtId = _b; break;
				}
			}
		}

		if (_td.mchtId) {
			seller_zizao_main.html(template('seller_zizao1_tpl'));
			seller_zizao_code = seller_zizao.find('.get-vcode');
			_ts.getImgCode();
		}
	},

	main: function (ts) {
		var _ts = this,
			_td = _ts.data,
			_v = $.trim(seller_zizao.find('.con-text').val());

		defSelf();

		if (!_v) {
			delayTip('请输入验证码');
			return;
		}

		new ajax(member.app ? getApi.getMchtGSQualificationsH5 : getApi.getMchtGSQualifications, {
			data: {
				memberId: member.id,
				mchtId: _td.mchtId,
				validCode: _v
			},
			success: function (res) {
				seller_zizao_main.html(template('seller_zizao2_tpl', res.returnData));
				seller_zizao_row2.removeClass('hide');
			},
			error: function (e) {
				_ts.getImgCode();
				delayTip(e.returnMsg);
			},
			limit: 1
		});
	},

	getImgCode: function () {
		seller_zizao_code.html($('<img src="' + getApi.getCaptcha + '?random=' + Math.random() + '"></img>'));
	}
};

getTpl(function () {
	sellerZizaoApi.init();
});