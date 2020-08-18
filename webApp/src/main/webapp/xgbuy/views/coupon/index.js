var coupon = $('.page[data-id="coupon"]:visible'),
	coupon_tab = coupon.find('.tab-top'),
	coupon_tab_con = '',
	coupon_body_con = coupon.find('.tab-body .tab-con');

var couponApi = {
	data: {
		pag: 10,
		tab: 1,
		scroll: '',
		con: ''
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '40'
		});

		requestAppMember(function () {
			new ajax(getApi.getMemberCouponCounts, {
				data: {
					memberId: member.id
				},
				success: function (res) {
					var _list = res.returnData;

					coupon_tab.html(template('coupon_tpl_top', _list));
					coupon_tab_con = coupon_tab.find('a');
					_ts.getPagingList(1);
				}
			});
		}, function () {
			getApi.addReceiveCoupon = getApi.addReceiveCouponH5;
		});
	},

	getPagingList: function (_tab, _scroll) {
		var _ts = this,
			_td = _ts.data;

		_tab && (_td.tab = _tab);

		if (!_scroll) {
			_ts.active();

			if (_td.scroll.hasClass('has-data')) {
				_td.scroll.addClass('show');
				return;
			}
		}

		new ajax(getApi.memberCoupon, {
			data: {
				type: _td.tab,
				currentPage: _td.scroll.attr('data-page') || 0
			},
			success: function (res) {
				var _size = res.returnSize,
					_list = res.returnData,
					_len = _list.dataList.length;

				if (_len) {
					for (var i = 0; i < _len; i++) {
						var _r = _list.dataList[i];

						_list.dataList[i].begDate = _ts.newTime(_r.expiryBeginDate);
						_list.dataList[i].endDate = _ts.newTime(_r.expiryEndDate);
						_list.dataList[i].curDate = _ts.newTime(_r.currentDate);

						(_td.tab == 1 && (_r.expiryEndDate - _r.currentDate) < 3600000*24) && (_list.dataList[i].hasIng = true);
					}
				}

				_list.tab = _td.tab;

				if (_scroll) {
					if (_len) {
						var _cur = _td.scroll.attr('data-page');
						_cur++;
						_td.scroll.attr('data-page', _cur);
						_td.con.append(template('coupon_tpl', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_td.con.html(template('coupon_tpl', _list));

					_size && (_td.pag = _size);

					_len == _td.pag && scrollHand.init(_td.scroll, function () {
						_ts.getPagingList(_tab, true);
					}, true);

					_td.scroll.addClass('has-data');

					scrollFix(_td.scroll);
				}

				!_td.scroll.hasClass('show') && _td.scroll.addClass('show');
			}
		});
	},

	newTime: function (e) {
		var _a = new Date(e);
		return _a.getFullYear() + '-' + famateNumber(1 + _a.getMonth()) + '-' + famateNumber(_a.getDate()) + ' ' + famateNumber(_a.getHours()) + ':' + famateNumber(_a.getMinutes());
	},

	active: function () {
		var _ts = this,
			_td = _ts.data,
			_index = _td.tab - 1;

		coupon_tab_con.removeClass('active');
		coupon_body_con.removeClass('show');

		_td.scroll = coupon_body_con.eq(_index);
		_td.con = _td.scroll.find('.touchfix');
		coupon_tab_con.eq(_index).addClass('active');
	},

	exc: function (_this) {
		var _val = $(_this).siblings('input').val();

		_val ? new ajax(getApi.addReceiveCoupon, {
			data: {
				type: 1,
				code: _val,
				memberId: member.id
			},
			success: function (res) {
				if (res.returnCode == '0000') {
					coupon.find('.popup-set-coupon').hide();

					delayTip(res.returnData.content, 1e3, function () {
						getUrl('', 'self');
					}, 1);
				} else {
					delayTip('领取失败');
				}
			},
			limit: 1
		}) : delayTip('请输入兑换码');
	},

	link: function (_this) {
		var _rang = _this.getAttribute('data-rang');

		if (_rang == 2) {
			getUrlActive(_this);
		} else if (_rang == 3) {
			getUrlActive(_this);
		} else if (_rang == 4) {
			getUrlShop(_this);
		}
	},

	send: function (name,id) {
		getUrl('coupon/send/index.html?preferentialInfo=' + name + '&id=' + id ,'true');
	}
};

getTpl(function () {
	couponApi.init();
});

function onShow() {
	traffic.setPv(couponApi.data);
}