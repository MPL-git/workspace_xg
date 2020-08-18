var free_price = $('.page[data-id="activity_freeprice"]:visible'),
	free_price_tbtn = free_price.find('.tab-btn'),
	free_price_tbody = free_price.find('.tab-body'),

	free_price_con1 = free_price_tbody.find('.con-1'),
	free_price_con2 = free_price_tbody.find('.con-2'),

	free_price_list1 = free_price_con1.find('.con-list'),
	free_price_list2 = free_price_con2.find('.con-list'),

	free_price_detail = free_price.find('.popup-free-detail .popup-con');

var freePrice = {
	data: {
		time: 3600000*24, // 倒计1*24小时
		type: 8,
		tab: 0,
		id: '',
		cutOrderId: '',
		pag: 10,
		scroll: '',
		list: '',
		tpl: '',
		btn: ''
	},

	ajax: {
		getInviteDetail: []
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
					case 'cutOrderId': _td.cutOrderId = _b; break;
					case 'tab': _td.tab = _b; break;
				}
			}
		}

		traffic.setPv(_td, {
			pageType: '22'
		});

		requestAppMember(function () {
			_ts.post(_td.tab, null, function () {
				_ts.role();
			});
			shareHand.update();
		}, function () {
			getApi.getBargainGoodsList = getApi.getBargainGoodsListH5;
			getApi.getMyBargainGoodsInfo = getApi.getMyBargainGoodsInfoH5;
			getApi.getInviteDetail = getApi.getInviteDetailH5;
		});
	},

	post: function (_tab, _scroll, fn) {
		var _ts = this,
			_td = _ts.data;

		if (_tab == 0) {
			_td.scroll = free_price_con1;
			_td.list = free_price_list1;
			_td.tpl = 'free_price_tpl_con1';
			_td.btn = free_price_tbtn.find('a').eq(0);
			_td.api = getApi.getBargainGoodsList;

			traffic.setPv(_td, {
				pvTab: 1,
				pageType: '23'
			});
		} else if (_tab == 1) {
			_td.scroll = free_price_con2;
			_td.list = free_price_list2;
			_td.tpl = 'free_price_tpl_con2';
			_td.btn = free_price_tbtn.find('a').eq(1);
			_td.api = getApi.getMyBargainGoodsInfo;

			traffic.setPv(_td, {
				pvTab: 1,
				pageType: '24'
			});
		}

		if (!_scroll && _td.scroll.hasClass('has-data')) {
			_ts.active(_td.scroll, _td.btn);
			return;
		}

		require(['qpTime'], function () {
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
							for (var i = 0; i < _len; i++) {
								var _di = _data[i];

								_di.timer = _td.time - (_list.currentDate - _di.createDate);
								_di.exitCount = _di.needInviteCount - _di.alreadyInvitedCount;
							}
						}

						for (var i = 0; i < _len; i++) {
							!/\./g.test(_data[i].tagPrice) && (_data[i].tagPrice += '.00');
						}
					}

					_list.sourceMemberId = _td.id;
					_list.dataList = imgCacheProductPic(_list.dataList, 'free_price_cache');

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
						if (_tab == 0 && _td.cutOrderId && getStorage('free_share_neednum')) {
							removeStorage('free_share_neednum');
							popupShow('.popup-free-help');
						}

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
				}
			});
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
		var _ts = this,
			_td = _ts.data;

		$.each(_td.list.find('time'), function () {
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

				if (_len && !free_price_tbody.find('.role-text').length) {
					var _role = $('<div class="role-text"></div>');

					_role.html(template('free_price_tpl_role', _list));
					free_price_tbody.find('.top1 p').append(_role);
					_len > 1 && _ts.roleRun();
				}
			}
		});
	},

	roleRun: function () {
		require(['vticker'], function () {
			free_price_tbody.find('.role-text').vTicker({
				speed: 1500,
				pause: 3e3
			});
		});
	},

	// 邀请详情
	detail: function (that, _scroll) {
		var _ts = this,
			_td = _ts.data,
			_aj = _ts.ajax,
			_id = $(that).data('id');

		!_scroll && free_price_detail.removeAttr('data-page').removeAttr('old-page').scrollTop(0);

		var _ag = new ajax(getApi.getInviteDetail, {
			limit: !_scroll ? $(that) : null,
			data: {
				memberId: member.id,
				cutOrderId: _id,
				currentPage: free_price_detail.attr('data-page') || 0
			},
			before: function () {
				for (i in _aj.getInviteDetail) {
					_aj.getInviteDetail[i].abort();
					_aj.getInviteDetail.splice(i, 1);
				}
			},
			success: function (e) {
				var _list = e.returnData,
					_len = _list.length;

				if (_list.length) {
					for (var i = 0; i < _len; i++) {
						!_list[i].weixinHead && (e.returnData[i].weixinHead = '../static/images/bg-head.png');
					}
				}

				if (_scroll) {
					if (_len) {
						var _cur = free_price_detail.attr('data-page');
						_cur++;
						free_price_detail.attr('data-page', _cur);
						free_price_detail.append(template('free_price_tpl_detail', e));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					free_price_detail.html(template('free_price_tpl_detail', e));
					popupShow('.popup-free-detail');

					_len == _td.pag && scrollHand.init(free_price_detail, function () {
						_ts.detail(that, true);
					});
				}
			}
		});

		_aj.getInviteDetail.push(_ag);
	}
};

getTpl(function () {
	freePrice.init();
});

function onShow() {
	traffic.setPv(freePrice.data);
}