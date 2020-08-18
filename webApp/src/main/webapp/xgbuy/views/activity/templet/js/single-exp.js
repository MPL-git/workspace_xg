var single_explosion = $('.page[data-id="activity_templet_single_explosion"]:visible'),
	single_explosion_scroll = single_explosion.find('.scroll-more'),
	single_explosion_tag1 = single_explosion.find('.tag1');

var singleExplosion = {
	data: {
		size: 10,
		timer: []
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '4'
		});

		new ajax(getApi.getFourCategories, {
			success: function (res) {
				var _list = res.returnData,
					_str = '';

				for (var i = 0; i < _list.length; i++) {
					_str += '<a class="fg flex dc ac jc' + (i == 0 ? ' active' : '') + '" data-id="' + _list[i].id + '" data-adCatalog="' + _list[i].adCatalog + '" onclick="singleExplosion.tapNav(this)"><span class="fg flex ac">' + _list[i].name + '</span></a>';
				}

				single_explosion_tag1.html(_str);

				require(['qpTime'], function () {
					_ts.tapNav(single_explosion_tag1.find('a').eq(0));
				});
			}
		});
	},

	getPagingList: function (_scroll) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.explosionActivity, {
			data: {
				type: 2,
				productTypeIdOne: _td.id,
				currentPage: single_explosion_scroll.attr('data-page') || 0,
				pageSize: _td.size
			},
			success: function (res) {
				var _list = res.returnData,
					_len = _list.length,
					_size = res.returnSize;

				for (var i = 0; i < _list.length; i++) {
					res.returnData[i].discount = famateDiscount(_list[i].discount) / 10;
				}

				res.returnData = imgCacheProductPic(_list, 'activity_single_explosion_list_tpl');

				if (_scroll) {
					if (_len) {
						var _cur = single_explosion_scroll.attr('data-page');
						_cur++;
						single_explosion_scroll.attr('data-page', _cur);
						single_explosion_scroll.append(template('activity_single_explosion_list_tpl', res));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_size && (_td.size = _size);
					single_explosion_scroll.html(template('activity_single_explosion_list_tpl', res));

					for (var i = 0; i < _td.timer.length; i++) {
						clearInterval(_td.timer[i]);
					}
					_td.timer = [];

					_len == _td.size && scrollHand.init(single_explosion_scroll, function () {
						_ts.getPagingList(true);
					}, true);
				}

				if (_len) {
					var _tm = single_explosion_scroll.find('.time');

					for (var i = 0; i < _tm.length; i++) {
						var _this = $(_tm[i]);

						if (!_this.hasClass('has-timer')) {
							_this.addClass('has-timer');

							var t = {
									c: _this.data('current'),
									b: _this.data('begin'),
									e: _this.data('end')
								},
								_s = t.c,
								_e = t.e;

							if (t.c > t.e) {
								_s = t.e;
								_e = t.c;
							} else if (t.c < t.b) {
								_e = t.b;
							}

							var _qt = 0,
								_q = $.qpTime({
									s: _s,
									e: _e
								}, function (pt) {
									_this.html('还剩 ' + (pt.t < 86400000 ? (pt.h + '小时' + pt.m + '分' + pt.s + '秒') : (parseInt(pt.d) + '天')));

									!pt.t && (clearInterval(_q), _ts.getPagingList(), _qt = 1);
								});

							if (_qt) break;

							_td.timer.push(_q);
						}
					}
				}
			}
		});
	},

	tapNav: function (_this) {
		var _ts = this,
			_td = _ts.data;

		_this = $(_this);
		_td.id = _this.data('id');
		_this.addClass('active').siblings().removeClass('active');
		single_explosion_scroll.removeAttr('data-page').removeAttr('data-old');

		_ts.getPagingList();

		traffic.setPv(_td, {
			pvCur: 1,
			pageType: '5',
			valueId: _this.data('id')
		});
	}
};

getTpl(function () {
	singleExplosion.init();
});

function onShow() {
	traffic.setPv(singleExplosion.data);
}