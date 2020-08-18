var cut_price = $('.page[data-id="activity_cutprice"]:visible'),
	cut_price_tbtn = cut_price.find('.tab-btn'),
	cut_price_tbody = cut_price.find('.tab-body'),

	cut_price_con1 = cut_price_tbody.find('.con-1'),
	cut_price_con2 = cut_price_tbody.find('.con-2'),

	cut_price_list1 = cut_price_con1.find('.con-list'),
	cut_price_list2 = cut_price_con2.find('.con-list');

var cutPrice = {
	data: {
		time: 3600000*24, // 1*24小时
		type: 7,
		tab: 0,
		id: '',
		pag: 10,
		scroll: '',
		list: '',
		tpl: '',
		btn: ''
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'sourceMemberId': _td.id = _b; break;
					case 'tab': _td.tab = _b; break;
				}
			}
		}

		traffic.setPv(_td, {
			pageType: '19'
		});

		requestAppMember(function () {
			require(['qpTime'], function () {
				_ts.post(_td.tab, null, function () {
					_ts.role();
					shareHand.update();
				});
			});
		}, function () {
			getApi.getBargainGoodsList = getApi.getBargainGoodsListH5;
			getApi.getMyBargainGoodsInfo = getApi.getMyBargainGoodsInfoH5;
		});
	},

	post: function (_tab, _scroll, fn) {
		var _ts = this,
			_td = _ts.data;

		if (_tab == 0) {
			_td.scroll = cut_price_con1;
			_td.list = cut_price_list1;
			_td.tpl = 'cut_price_tpl_con1';
			_td.btn = cut_price_tbtn.find('a').eq(0);
			_td.api = getApi.getBargainGoodsList;

			traffic.setPv(_td, {
				pvTab: 1,
				pageType: '20'
			});
		} else if (_tab == 1) {
			_td.scroll = cut_price_con2;
			_td.list = cut_price_list2;
			_td.tpl = 'cut_price_tpl_con2';
			_td.btn = cut_price_tbtn.find('a').eq(1);
			_td.api = getApi.getMyBargainGoodsInfo;
			
			traffic.setPv(_td, {
				pvTab: 1,
				pageType: '21'
			});
		}

		if (!_scroll && _td.scroll.hasClass('has-data')) {
			_ts.active(_td.scroll, _td.btn);
			return;
		}

		new ajax(_td.api, {
			data: {
				type: _td.type,
				memberId: member.id,
				sourceMemberId: _td.id,
				currentPage: _td.scroll.attr('data-page') || 0
			},
			success: function (e) {
				var _list = e.returnData,
					_data = _list.dataList,
					_len = _data.length,
					_black = _list.isBlack;

				if (_black) {
					shield(_list.blackReason);
					return;
				}

				if (_len) {
					if (_tab == 1) {
						var _curr = _list.currentDate;

						for (var i = 0; i < _len; i++) {
							_data[i].timer = _td.time - (_curr - _data[i].createDate);
						}
					}

					for (var i = 0; i < _len; i++) {
						!/\./g.test(_data[i].tagPrice) && (_data[i].tagPrice += '.00');
					}
				}

				_list.sourceMemberId = _td.id;
				_list.dataList = imgCacheProductPic(_list.dataList, 'cut_price_cache');

				if (_scroll) {
					if (_len) {
						var _cur = _td.scroll.attr('data-page');
						_cur++;
						_td.scroll.attr('data-page', _cur);
						_td.list.append(template(_td.tpl, _list));
						_ts.timeRun();
					} else {
						_td.list.append('<div class="tpl-end flex ac jc">没有更多内容啦</div>');
					}
				} else {
					_td.list.html(template(_td.tpl, _list));
					_td.scroll.addClass('has-data');
					_ts.active(_td.scroll, _td.btn);
					_ts.timeRun();

					_len == _td.pag ? scrollHand.init(_td.scroll, function () {
						_ts.post(_tab, true);
					}, true) : _td.list.append('<div class="tpl-end flex ac jc">没有更多内容啦</div>');

					fn && fn();
					scrollFix();
				}
			},
			limit: 1
		});
	},

	active: function (a, b) {
		a.removeClass('hide').siblings('.tab-con').addClass('hide');
		b.addClass('active').siblings().removeClass('active');
	},

	timer: function (e) {
		return famateNumber(e / 1e3 / 60 / 60) + ':' + famateNumber(e / 1e3 / 60 % 60) + ':' + famateNumber(e / 1e3 % 60);
	},

	timeRun: function (e) {
		var _ts = this;

		$.each(_ts.data.list.find('time'), function () {
			var $ts = $(this);

			$.qpTime({
				s: 1,
				e: $ts.data('timer')
			}, function (pt) {
				$ts.html(_ts.timer(pt.t));
			});
		});
	},

	// 轮播文字
	role: function () {
		var _ts = this;

		new ajax(getApi.getBargainGoodsTopInfo, {
			success: function (e) {
				var _list = e.returnData,
					_len = _list.memberReceiveInfoList.length;

				if (_len && !cut_price_tbody.find('.role-text').length) {
					var _role = $('<div class="role-text"></div>');

					_role.html(template('cut_price_tpl_role', _list));
					cut_price_tbody.find('.top1 p').append(_role);
					_len > 1 && _ts.roleRun();
				}
			}
		});
	},

	roleRun: function () {
		require(['vticker'], function () {
			cut_price_tbody.find('.role-text').vTicker({
				speed: 1500,
				pause: 3e3
			});
		});
	},
};

getTpl(function () {
	cutPrice.init();
});

function onShow() {
	traffic.setPv(cutPrice.data);
}