var NTKF_PARAM = {},
	search_url = location.search.replace('?', '').split('&'),
	_siteid = '', // 对接组id
	_itemid = '', // 商品id
	_orderid = '', // 订单id
	_orderprice = '', // 订单价格
	_uname = '', // 昵称
	_uid = '', // 用户id
	_userlevel = '', // 用户等级
	_isvip = '', // 用户vip
	_back = 0; // 返回

for (var i = 0; i < search_url.length; i++) {
	var _a = search_url[i].split('='),
		_b = _a[1];

	switch (_a[0]) {
		case 'siteid': _siteid = _b; break;
		case 'itemid': _itemid = _b; break;
		case 'orderid': _orderid = _b; break;
		case 'orderprice': _orderprice = _b; break;
		case 'uname': _uname = _b; break;
		case 'uid': _uid = _b; break;
		case 'userlevel': _userlevel = _b; break;
		case 'isvip': _isvip = _b; break;
		case 'back': _back = _b; break;
	}
}

if (_back) {
	require(['https://res.wx.qq.com/open/js/jweixin-1.3.2.js'], function (wx) {
		wx.miniProgram.navigateBack({
			delta: 1
		});
	});
} else {
	history.replaceState(null, null, location.href + '&back=1');

	NTKF_PARAM = {
		"siteid": "je_1000",
		"sellerid": "1000",
		"settingid": _siteid,
		"uid": _uid,
		"uname": decodeURI(_uname),
		"isvip": _isvip,
		"userlevel": _userlevel,
		"itemid": _itemid,
		"orderid": _orderid,
		"orderprice": _orderprice
	};

	require(['https://dl.ntalker.com/js/xn6/ntkfstat.js?siteid=' + NTKF_PARAM.siteid], function () {
		NTKF.im_openInPageChat(NTKF_PARAM.settingid);
	});
}