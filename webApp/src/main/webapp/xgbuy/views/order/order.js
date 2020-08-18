var page_order = $('.page[data-id="order"]:visible'),
	page_order_scroll = page_order.find('.tab-body .tab-con'),
	page_tab_con = page_order.find('.tab-order'),
	page_order_tab = page_order.find('.tab-top a');

var orderApi = {
	data: {
		con: '',
		scroll: '',
		type: getStorage('order_typ') ? getStorage('order_typ').replace('_', '') : ''
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '38'
		});

		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'type': _td.type = _b; break;
				}
			}

			removeStorage('order_typ');
		}

		_ts.main();
		shareHand.update();
	},

	main: function (_type, _scroll, _f) {
		var _ts = this,
			_td = _ts.data;

		_type !== undefined && (_td.type = _type);

		if (!_scroll) {
			_ts.active();

			if (_f) {
				_f();
			} else if (_td.scroll.hasClass('has-data')) {
				_td.scroll.addClass('show');
				return;
			}
		}

		new ajax(getApi.orderDt, {
			data: {
				memberId: member.id,
				subOrderStatus: _td.type,
				currentPage: _td.scroll.attr('data-page') || 0,
				pageSize: 10
			},
			success: function (e) {
				var _list = e.returnData,
					_pic = _list.spoofingPic,
					_dep = _list.subDepositOrderCount,
					_data = _list.dataList,
					_len = _data.length;

				_list.dataList = imgCacheOrderSkuPic(_list.dataList, 'order_tpl');

				for (var i = 0; i < _data.length; i++) {
					if (_data[i].subOrderStatus == 0 && _data[i].currentTime > _data[i].unpaidEndTime) {
						_list.dataList[i].subOrderStatus = 4;
					}
				}

				if (_scroll) {
					if (_len) {
						var _cur = _td.scroll.attr('data-page');
						_cur++;
						_td.scroll.attr('data-page', _cur);
						_td.con.append(template('order_tpl', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_td.con.html(template('order_tpl', _list));

					if (_len) {
						_dep && _td.con.prepend('<a onclick="getUrl(\'order/earnest/index.html\', \'self\')" class="order-dep flex ac jb"><dt><h3 class="flex ac"><em class="flex ac jc">预售</em>预售定金在这里！</h3><p class="c9">有<span>' + _dep + '</span>笔预售定金订单，请点击查看</p></dt><dd></dd></a>');
						_pic && _td.con.prepend('<div class="order-notice"><img src="' + _pic + '"></div>');
					}

					_len == 10 && scrollHand.init(_td.scroll, function () {
						_ts.main(_type, true);
					});

					_td.scroll.addClass('has-data');
				}

				_ts.payTime();

				!_td.scroll.hasClass('show') && _td.scroll.addClass('show');

				// 抖音布码
				if (getStorage('pay_from_douyin')) {
					removeStorage('pay_from_douyin');

					_ts.douyinPaySuccess(_data[0].combineOrderId);
				}
			}
		});
	},

	douyinPaySuccess: function (_id) {
		var _ts = this;

		new ajax(getApi.getMemberOrderPaySuccess, {
			data: {
				combineOrderId: _id,
				adSprId: getStorage('adSprId')
			},
			success: function (res) {
				if (res.returnData) {
					getDouyin(douyin_convert2);
					douyin_delay = 2000;
				} else if (douyin_delay <= 5000) {
					setTimeout(function () {
						if (douyin_delay == 2000) {
							douyin_delay = 5000;
							_ts.douyinPaySuccess(_id);
						} else if (douyin_delay == 5000) {
							douyin_delay++;
							_ts.douyinPaySuccess(_id);
						}
					}, douyin_delay);
				} else {
					douyin_delay = 2000;
				}
			}
		});
	},

	active: function () {
		var _ts = this,
			_td = _ts.data,
			_index = 0;

		page_order_tab.removeClass('active');
		page_order_scroll.removeClass('show');

		switch(_td.type) {
			case '0': _index = 1; break;
			case '1': _index = 2; break;
			case '2': _index = 3; break;
			case '3': _index = 4; break;
			default: _index = 0; break;
		}

		_td.scroll = page_order_scroll.eq(_index);
		_td.con = _td.scroll.find('.touchfix');

		page_order_tab.eq(_index).addClass('active');

		setStorage('order_typ', '_' + _td.type);
	},

	payTime: function () {
		var _ts = this,
			_td = _ts.data;

		require(['qpTime'], function () {
			$.each(_td.con.find('.count-time'), function () {
				var $ts = $(this);

				if (!$ts.hasClass('run')) {
					var _cur = $ts.data('cur'),
						_end = $ts.data('end'),
						_id = $ts.data('order-id');

					$ts.addClass('run');

					$.qpTime({
						s: _cur,
						e: _end
					}, function (pt) {
						if (pt.t) {
							$ts.html(pt.h + ':' + pt.m + ':' + pt.s);
						} else {
							new ajax(getApi.cancelOrderById, {
								data: {
									memberId: member.id,
									combineOrderId: _id
								},
								success: function () {
									location.reload();
								},
								limit: 1
							});
						}
					});
				}
			});
		});
	},

	detail: function (a, b) {
		var _ts = this,
			_td = _ts.data;

		_td.type == '0' && (b = '');
		getUrl('order/detail/index.html?combineOrderId=' + a + '&subOrderId=' + b, 'self');
	},

	// 删除订单
	deleteSelf: function (_this, _sub, _com) {
		confirmTip('是否删除该订单？', function () {
			new ajax(getApi.deleteOrderById, {
				data: {
					memberId: member.id,
					subOrderId: _sub,
					combineOrderId: _com
				},
				success: function (e) {
					$(_this).closest('li').remove();
					delayTip('成功删除订单');
				},
				limit: 1
			});
		});
	},

	// 跳转评价页面
	click_draw: function() {
		var _ts = this,
			_td = _ts.data;

		getUrl('evalua/index.html?subOrderId='+ _td.sub, true);
	},

	// 跳转详情页
	click_currentpage: function() {
		var _ts = this,
			_td = _ts.data;

		getUrl('order/detail/index.html?subOrderId='+ _td.sub, true);
	},

	// 确认收货
	orderConfirm: function (_sub) {
		var _ts = this,
			_td = _ts.data;

		_td.sub = _sub;

		confirmTip('您确认您已经收到您购买的物品？', function () {
			new ajax(getApi.confirmReceiptOrderById, {
				data: {
					memberId: member.id,
					subOrderId: _sub
				},
				success: function () {
					orderApi.main('3');

					page_tab_con.append(template('order_tab_order'));
					popupShow('.popup-order-msg');
				},
				limit: 1
			});
		});
	},

	// 提醒发货
	remindDelivery: function (_sub) {
		confirmTip('每隔一分钟可提醒商家发货，是否提醒商家发货？', function () {
			new ajax(getApi.addRemindDelivery, {
				data: {
					memberId: member.id,
					subOrderId: _sub
				},
				success: function () {
					delayTip('已提醒商家发货');
				},
				limit: 1
			});
		}, '是', '否');
	}
};

getTpl(function () {
	orderApi.init();
});

function onShow() {
	traffic.setPv(orderApi.data);
}

function imgCacheOrderSkuPic(_list, _cac) {
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
			var _data = _list[i].data;

			if (_data.length) {
				for (var k = 0; k < _data.length; k++) {
					var _pic = _data[k].skuPic,
						_n = cache.list.name;

					if (_pic && _n.length) {
						for (var j = 0, _name = _pic.match(reg_cache_img)[0]; j < _n.length; j++) {
							(_name == _n[j]) && (_list[i].data[k].skuPic = cache.list.path[j]);
						}
					}
				}
			}
		}
	}

	for (var i = 0; i < _list.length; i++) {
		var _data = _list[i].data;

		if (_data.length) {
			for (var k = 0; k < _data.length; k++) {
				var _pic = _data[k].skuPic;

				if (_pic) {
					var _arr = _pic.match(reg_cache_img);

					cache.path.push(_arr.input);
					cache.name.push(_arr[0]);
				}
			}
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