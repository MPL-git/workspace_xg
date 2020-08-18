var novaplan_order = $('.page[data-id="activity_shopowner_pages_order"]:visible'),
	novaplan_order_con = novaplan_order.find('.tab-body .tab-con'),
	novaplan_order_tab = novaplan_order.find('.tab-top a');

var novaplanOrder = {
	data: {
		con: '',
		scroll: '',
		type: '0'
	},

	init: function (_type, _scroll) {
		var _ts = this,
			_td = _ts.data;

		_type !== undefined && (_td.type = _type);

		if (!_scroll) {
			_ts.active();

			if (_td.scroll.hasClass('has-data')) {
				_td.scroll.addClass('show');
				return;
			}
		}

		new ajax(getApi.getSubMemberHistoryOrderList, {
			data: {
				memberId: member.id,
				type: _td.type,
				currentPage: _td.scroll.attr('data-page') || 0,
				pageSize: 10
			},
			success: function (e) {
				var _list = e.returnData,
					_pic = _list.spoofingPic,
					_dep = _list.subDepositOrderCount,
					_data = _list.dataList,
					_len = _data.length;

				_list.dataList = imgCacheMemberPic(_list.dataList, 'novaplan_order_tpl');

				if (_scroll) {
					if (_len) {
						var _cur = _td.scroll.attr('data-page');
						_cur++;
						_td.scroll.attr('data-page', _cur);
						_td.con.append(template('novaplan_order_tpl', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_td.con.html(template('novaplan_order_tpl', _list));

					_len == 10 && scrollHand.init(_td.scroll, function () {
						_ts.init(_type, true);
					});

					_td.scroll.addClass('has-data');
				}

				!_td.scroll.hasClass('show') && _td.scroll.addClass('show');
			}
		});
	},

	active: function () {
		var _ts = this,
			_td = _ts.data;

		novaplan_order_tab.removeClass('active');
		novaplan_order_con.removeClass('show');

		_td.scroll = novaplan_order_con.eq(_td.type);
		_td.con = _td.scroll.find('.touchfix');
		novaplan_order_tab.eq(_td.type).addClass('active');
	}
};

getTpl(function () {
	novaplanOrder.init();
});

function imgCacheMemberPic(_list, _cac) {
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
			var _pic = _list[i].memberPic;

			if (_pic) {
				for (var j = 0, _name = _pic.match(reg_cache_img)[0]; j < cache.list.name.length; j++) {
					(_name == cache.list.name[j]) && (_list[i].memberPic = cache.list.path[j]);
				}
			}
		}
	}

	for (var i = 0; i < _list.length; i++) {
		var _pic = _list[i].memberPic;

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