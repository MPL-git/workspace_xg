var newman_seckill = $('.page[data-id="newman_seckill"]:visible'),
	newman_seckill_scroll = newman_seckill.find('.scroll-more'),
	newman_seckill_swiper = newman_seckill.find('.swiper'),
	newman_seckill_con = newman_seckill_scroll.find('.main');

var newmanSeckill = {
	data: {
		size: 10
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '34'
		});

		new ajax(getApi.getAdInfos, {
			data: {
				catalog: 9,
				position: 1
			},
			success: function (res) {
				var _list = res.returnData,
					_data = _list.data;

				_list.data = imgCachePic(_data, 'newman_seckill_swiper');
				newman_seckill_swiper.html(template('newman_seckill_tpl_swiper', _list));
				_data.length > 1 && initSwiper();

				traffic.delayRequest(function () {
					for (var i = 0; i < _data.length; i++) {
						traffic.setDtl(_td, {
							type: 4,
							id: _data[i].id,
							pos: i
						});
					}
				});
			}
		});

		require(['qpTime'], function() {
			_ts.main();
		});
	},

	main: function (_scroll) {
		var _ts = this,
			_td = _ts.data,
			_page = newman_seckill_scroll.attr('data-page');

		new ajax(getApi.seckillTimeUser, {
			data: {
				pageSize: _td.size,
				currentPage: _page || 0
			},
			success: function (res) {
				var _size = res.returnSize,
					_list = res.returnData,
					_len = _list.length;

				res.returnData = imgCacheProductPic(_list, 'newman_seckill_tpl');

				if (_scroll) {
					if (_len) {
						newman_seckill_scroll.attr('data-page', ++_page);
						newman_seckill_con.append(template('newman_seckill_tpl', res));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_size && (_td.size = _size);

					newman_seckill_con.html(template('newman_seckill_tpl', res));

					_len == _td.size && scrollHand.init(newman_seckill_scroll, function () {
						_ts.main(true);
					}, true);

					scrollFix(newman_seckill_scroll);
				}

				$.each(newman_seckill.find('.main dl'), function () {
					var that = $(this);

					if (!that.hasClass('has-time')) {
						that.addClass('has-time');

						var dd = that.find('dd'),
							t = {
								c: 1e3 * that.data('current'),
								b: 1e3 * that.data('begin'),
								e: 1e3 * that.data('end'),
								f: that.data('stock')
							};

						if (t.c > t.e) {
							// 已抢光
							t.c - t.e > 998 && $.qpTime({
								s: t.e,
								e: t.c
							}, function (pt) {
								dd.find('.p-time').html('剩余时间 ' + (pt.d ? (pt.d + '天') : '') + pt.h + '时' + pt.m + '分' + pt.s + '秒');

								!pt.t && setTimeout(function () {
									dd.closest('dl').remove();
								}, 1e3);
							});
						} else if (t.c >= t.b) {
							// 马上抢购
							t.e - t.c > 998 && $.qpTime({
								s: t.c,
								e: t.e
							}, function (pt) {
								dd.find('.p-time').html('剩余时间 ' + (pt.d ? (pt.d + '天') : '') + pt.h + '时' + pt.m + '分' + pt.s + '秒');

								!pt.t && setTimeout(function () {
									t.f ? getUrl('', 'self') : dd.closest('dl').remove();
								}, 1e3);
							});
						} else {
							// 即将开抢
							var _d = new Date(t.b);

							dd.find('.p-time').removeClass('f50').html('开抢时间 ' + (1 + _d.getMonth()) + '月' + _d.getDate() + '日 ' + famateNumber(_d.getHours()) + ':' + famateNumber(_d.getMinutes()));

							t.b - t.c > 998 && $.qpTime({
								s: t.c,
								e: t.b
							}, function (pt) {
								pt.t <= 3e5 && dd.find('a').html('即将开抢(' + parseInt(pt.t / 1e3) + 's)');

								!pt.t && setTimeout(function () {
									location.reload();
								}, 1e3);
							});
						}
					}
				});
			}
		});
	}
};

getTpl(function () {
	newmanSeckill.init();
});

function onShow() {
	traffic.setPv(newmanSeckill.data);
}