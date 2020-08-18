var order_earnest = $('.page[data-id="order_earnest"]:visible'),
	order_earnest_scroll = order_earnest.find('.scroll-more'),
	order_earnest_con = '';

var orderEarnestApi = {
	data: {
		combineDepositOrderId: '', // 去支付的订单ID
		payId: false, // 是否勾选payId
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv();

		_ts.main();

		removeStorage('order_pay_earnest');
		removeStorage('order_firmpre');
	},

	main: function (_scroll) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getSubDepositOrderList, {
			data: {
				currentPage: order_earnest_scroll.attr('data-page') || 0,
				pageSize: 10
			},
			success: function (res) {
				var _list = res.returnData;

				_list = imgCacheSkuPic(_list, 'earnest_tpl');

				if (_scroll) {
					if (_list.length) {
						var _cur = order_earnest_scroll.attr('data-page');
						_cur++;
						order_earnest_scroll.attr('data-page', _cur);
						order_earnest_con.append(template('earnest_tpl_con', res));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					order_earnest_scroll.html(template('earnest_tpl', res));
					order_earnest_con = order_earnest_scroll.find('ul');

					_list.length == 10 && scrollHand.init(order_earnest_scroll, function () {
						_ts.main(true);
					}, true);
				}
			}
		});
	},

	// 取消订单
	cancel: function (_this, _com) {
		confirmTip('是否取消预售定金付款？', function () {
			new ajax(getApi.updateSubDepositOrder, {
				data: {
					combineDepositOrderId: _com
				},
				success: function () {
					$(_this).closest('li').remove();
					delayTip('成功取消订单');
				},
				limit: 1
			});
		});
	},

	// 勾选payId
	getPayId: function (_this) {
		var _tick = $(_this).find('.tick');

		_tick.addClass('ticked').closest('.tap-tick').siblings().find('.tick').removeClass('ticked');
		this.data.payId = _tick.data('id');
	},

	// 支付
	payMent: function (_this) {
		var _ts = this,
			_td = _ts.data,
			_id = $(_this).closest('.order-detail-footer').siblings('.bot-2').find('.ticked').data('id'),
			_com = $(_this).data('id');

		new ajax(getApi.submitDepositAfterPayment, {
			data: {
				combineDepositOrderId: _com,
				payId: _id
			},
			success: function (res) {
				payTypeApi(_id, res.returnData, true);
			},
			limit: 1
		});
	}
};

getTpl(function () {
	orderEarnestApi.init();
});

function imgCacheSkuPic(_list, _cac) {
	if (!_list || !_list.length) {
		return _list;
	}

	var cache = {
		list: win_lc.getItem(_cac),
		path: [],
		name: []
	};

	if (cache.list) {
		cache.list = JSON.parse(cache.list);
		cache.path = cache.list.path,
		cache.name = cache.list.name;

		for (var i = 0; i < _list.length; i++) {
			var _pic = _list[i].skuPic;

			if (_pic) {
				for (var j = 0, _name = _pic.match(reg_cache_img)[0]; j < cache.list.name.length; j++) {
					(_name == cache.list.name[j]) && (_list[i].skuPic = cache.list.path[j]);
				}
			}
		}
	}

	for (var i = 0; i < _list.length; i++) {
		var _pic = _list[i].skuPic;

		if (_pic) {
			var _arr = _pic.match(reg_cache_img);

			cache.path.push(_arr.input);
			cache.name.push(_arr[0]);
		}
	}

	var _name = [],
		_path = [],
		_same = {};

	for (var i = 0, d; (d = cache.name[i]) != null; i++) {
		if (!_same[d]) {
			_name.push(d);
			_path.push(cache.path[i]);
			_same[d] = true;
		}
	}
	cache.name = _name;
	cache.path = _path;

	win_lc.setItem(_cac, JSON.stringify({
		path: cache.path,
		name: cache.name
	}));

	return _list;
}