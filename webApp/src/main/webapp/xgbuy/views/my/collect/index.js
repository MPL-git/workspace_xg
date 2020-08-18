var my_collect = $('.page[data-id="my_collect"]:visible'),
	my_collect_con = my_collect.find('.tab-body .tab-con'),
	my_collect_tab = my_collect.find('.tab-top a');

var myCollect = {
	data: {
		con: '',
		scroll: '',
		type: '1',

		slide: 1
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '41'
		});

		_ts.getPagingList();
	},

	getPagingList: function (_type, _scroll) {
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

		new ajax(getApi.remindSale, {
			data: {
				remindType: _td.type.toString(),
				pageSize: 10,
				currentPage: _td.scroll.attr('data-page') || 0
			},
			success: function (res) {
				var _list = res.returnData,
					_len = _list.arrayData.length,
					_pag = res.returnSize,
					_str = '';

				!_list.arrayData && (_list.arrayData = []);

				if (_td.type == 1) {
					_str = 'my_collect_tpl_brand';
					_list.arrayData = imgCacheEntryPic(_list.arrayData, 'my_collect_tpl_brand');
				} else if (_td.type == 2) {
					_str = 'my_collect_tpl_goods';
					_list.arrayData = imgCachePic(_list.arrayData, 'my_collect_tpl_goods');
				} else if (_td.type == 3) {
					_str = 'my_collect_tpl_shop';
					_list.arrayData = imgCacheLogo(_list.arrayData, 'my_collect_tpl_shop');
				}

				if (_scroll) {
					if (_len) {
						var _cur = _td.scroll.attr('data-page');
						_cur++;
						_td.scroll.attr('data-page', _cur);
						_td.con.append(template(_str, _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_td.con.html(template(_str, _list));

					_len == 10 && scrollHand.init(_td.scroll, function () {
						_ts.getPagingList(_type, true);
					});

					_td.scroll.addClass('has-data');
				}

				_ts.swipeleft(_td.con.children());

				!_td.scroll.hasClass('show') && _td.scroll.addClass('show');
			}
		});
	},

	active: function () {
		var _ts = this,
			_td = _ts.data;

		my_collect_tab.removeClass('active');
		my_collect_con.removeClass('show');

		_td.scroll = my_collect_con.eq(_td.type - 1);
		_td.con = _td.scroll.find('.touchfix');
		my_collect_tab.eq(_td.type - 1).addClass('active');
	},

	remove: function (event, _this) {
		var _this = $(_this),
			_ts = this,
			_td = _ts.data;

		stopSelf(event);

		new ajax(getApi.deleteRemindSale, {
			data: {
				memberId: member.id,
				remindType: _td.type,
				type: _td.type,
				remindId: _this.data('id')
			},
			success: function (res) {
				res.returnCode == '0000' && _this.parent().parent().remove();
			},
			limit: 1
		});
	},

	swipeleft: function (e) {
		var _ts = this,
			_td = _ts.data,
			_l = 0,
			_w = 0;

		if (isAndroid || isiOS) {
			e.on('touchstart', function (e) {
				_l = e.targetTouches[0].pageX;
				!_w && (_w = $(this).find('.slip-btn-friend').width());
			}).on('touchmove', function (e) {
				var left = parseInt($(this).attr('data-end'));
				isNaN(left) && (left = 0);
				var _c = e.targetTouches[0].pageX - _l + left;

				_c > 0 && (_c = 0);
				_c < -_w && (_c = -_w);
				_ts.swipeMove($(this), _c + 'px');
				_ts.swipeMove($(this).siblings(), 0, true);
				$(this).siblings().removeAttr('data-end');
			}).on('touchend', function (e) {
				var _s = 0,
					_f = parseInt($(this).attr('data-left'));

				_f < -_w / 2 && (_s = -_w + 'px');
				_s != 0 && _ts.shopDynamic(e, function () { })
				_ts.swipeMove($(this), _s, true);
				$(this).attr('data-end', _s);
			});
		} else {
			e.swipeleft(function (evt) {
				stopSelf(evt);
				defSelf(evt);
				_ts.swipeMove($(this), '-1.2rem', true);
				_ts.swipeMove($(this).siblings(), 0, true);
				_td.slide = 0;
			}).swiperight(function (evt) {
				stopSelf(evt);
				defSelf(evt);
				_ts.swipeMove($(this), 0, true);
				_td.slide = 0;
			}).click(function () {
				_td.slide = 1;
			});
		}
	},

	swipeMove: function (e, r, s) {
		var _ts = this,
			_td = _ts.data;
		s ? e.addClass('trans') : e.removeClass('trans');

		e.attr('data-left', r).css({
			'-webkit-transform': 'translate3d(' + r + ', 0, 0)',
			'transform': 'translate3d(' + r + ', 0, 0)'
		});
	},

	brand: function (e) {
		var _ts = this,
			_td = _ts.data;

		_td.slide && getUrlActive(e);
	},

	goods: function (e) {
		var _ts = this,
			_td = _ts.data;

		_td.slide && getUrlGoods(e);
	},

	shop: function (e) {
		var _ts = this,
			_td = _ts.data;

		_td.slide && getUrlShop(e);
	},
	shopDynamic: function (e, f) {
		const _ts = this,
			_td = _ts.data;
		_e = $(e.currentTarget)

		new ajax(getApi.hasCollectionMcht, {
			data: {
				type: 3,
				memberId: member.id,
				remindId: _e.attr("data-id")
			},
			success: function (res) {
				console.log(res)
				var _btn = _e.find(".slip-btn-friend .btn-sd")
				if (res.returnData.shieldingDynamics == 0) {
					_btn.eq(1).removeClass("hide");
					_btn.eq(0).addClass("hide")
				}else{
					_btn.eq(0).removeClass("hide");
					_btn.eq(1).addClass("hide")
				}
				f && f()

			}
		})
	},
	//屏蔽动态
	updateDynamics: function (e) {
		var _ts = this,
			_td = _ts.data;
		e.stopPropagation();

		var _e = $(e.currentTarget)
		new ajax(getApi.updateMchtShieldingDynamics, {
			data: {
				type: 3,
				memberId: member.id,
				remindId: _e.attr("data-id")
			},
			success: function (res) {
				console.log(res)
				delayTip(res.returnData.shieldingDynamics == 0 ? '已接收店铺动态' : '已屏蔽店铺动态');
				_ts.swipeMove(_e.parent().parent(), 0, true);
				_e.parent().parent().attr('data-end', 0);

			}
		})
	},
};

getTpl(function () {
	myCollect.init();
});

function onShow() {
	traffic.setPv(myCollect.data);
}

function imgCacheLogo(_list, _cac) {
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
			var _pic = _list[i].shopLogo;

			if (_pic) {
				for (var j = 0, _name = _pic.match(reg_cache_img)[0]; j < cache.list.name.length; j++) {
					(_name == cache.list.name[j]) && (_pic = cache.list.path[j]);
				}
			}
		}
	}

	for (var i = 0; i < _list.length; i++) {

		var _m = _list[i].shopLogo;

		if (_m) {
			var _arr = _m.match(reg_cache_img);

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