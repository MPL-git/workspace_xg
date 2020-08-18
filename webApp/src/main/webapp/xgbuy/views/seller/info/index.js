var seller_info = $('.page[data-id="seller_info"]:visible'),
	seller_info_main = seller_info.find('.main');

var sellerInfoApi = {
	data: {
		mchtId: ''
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv();

		for (var i = 0; i < search_url.length; i++) {
			var _a = search_url[i].split('='),
				_b = _a[1];

			switch (_a[0]) {
				case 'mchtId': _td.mchtId = _b; break;
			}
		}

		new ajax(getApi.getMchtShopInfo, {
			data: {
				mchtId: _td.mchtId
			},
			success: function (res) {
				var _list = res.returnData;

				_list.mchtId = _td.mchtId;
				_td.mVerfiyFlag = _list.mVerfiyFlag;
				_list.shopLogo = imgCache(_list.shopLogo, 'seller_shop_logo');
				seller_info_main.html(template('seller_info_tpl', _list));
			}
		});
	},

	zizao: function () {
		var _ts = this,
			_td = _ts.data;

		getUrl('seller/zizao/index.html?mchtId=' + _td.mchtId, true, function () {
			if (_td.mVerfiyFlag === '') {
				getMember(function () {
					member.mVerfiyFlag === '0' && getUrl('register/mobilePwd/index.html');
				});
			} else if (_td.mVerfiyFlag === '0') {
				getUrl('register/mobilePwd/index.html');
			}
		});
	},

	operation: function () {
		var _ts = this,
		_td = _ts.data;

		getUrl('seller/operation/index.html?mchtId=' + _td.mchtId);
	}
};

getTpl(function () {
	sellerInfoApi.init();
});