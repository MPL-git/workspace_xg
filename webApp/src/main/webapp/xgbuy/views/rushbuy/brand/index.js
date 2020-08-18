var rushbuy_brand = $('.page[data-id="rushbuy_brand"]:visible'),
	rushbuy_brand_scroll = rushbuy_brand.find('.scroll-more'),
	rushbuy_brand_con = '',
	rushbuy_brand_time = '';

var rushbuyBrandApi = {
	data: {
		pag: 10,
		type: '',
		beg: 0,
		cur: 0,
		end: 0
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '2'
		});

		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'type': _td.type = _b; break;
				}
			}
		}

		_ts.post();
	},

	post: function (_scroll) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getBrandGroupProductList, {
			data: {
				brandGroupId: _td.type,
				currentPage: rushbuy_brand_scroll.attr('data-page') || 0
			},
			success: function (res) {
				var _list = res.returnData,
					_pag = res.returnSize,
					_len = _list.productInfoList.length,
					_title = _list.brandGroupName;

				_td.beg = _list.beginTime,
				_td.cur = _list.currentTime;
				_td.end = _list.endTime,

				_list.productInfoList = imgCacheProductPic(_list.productInfoList, 'rushbuy_brand_product');

				if (_list.beginTime <= _list.currentTime) {
					for (var i = 0; i < _list.productInfoList.length; i++) {
						_list.productInfoList[i].salesRatio = parseInt(100 * _list.productInfoList[i].salesRatio) || 1;
					}
				}

				if (_scroll) {
					if (_len) {
						var _cur = rushbuy_brand_scroll.attr('data-page');
						_cur++;
						rushbuy_brand_scroll.attr('data-page', _cur);
						rushbuy_brand_con.append(template('rushbuy_brand_tpl_scroll', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_title && setTitle(_title);
					_list.posterPic = imgCache(_list.posterPic, 'rushbuy_brand_swiper');

					rushbuy_brand_scroll.html(template('rushbuy_brand_tpl', _list));
					rushbuy_brand_con = rushbuy_brand_scroll.find('.content');
					rushbuy_brand_time = rushbuy_brand.find('.count-time');
					_ts.time();
					_pag && (_td.pag = _pag);

					_td.pag == _len && scrollHand.init(rushbuy_brand_scroll, function () {
						_ts.post(true);
					}, true);
				}
			}
		});
	},

	time: function () {
		var _ts = this,
			_td = _ts.data,

			time_div = rushbuy_brand_time.find('.con-2 div'),
			time_p = rushbuy_brand_time.find('.con-2 p'),
			time_h = rushbuy_brand_time.find('.con-1'),

			_now = new Date(_td.cur),
			_end = new Date(_td.end),
			_begin = new Date(_td.beg),
			_ends = _end;

		if (_now.getTime() <= _begin.getTime()) {
			_ends = _begin;
			time_h.html('即将开抢');
			time_p.html('离本场开始仅有：');
		} else if (_now.getTime() <= _end.getTime()) {
			time_h.html('正在疯抢');
			time_p.html('离本场结束仅剩：');
		} else {
			_ends = false;
			time_p.html('活动已结束');
		}

		if (_ends) {
			require(['qpTime'], function () {
				_ends - _now > 998 && $.qpTime({
					s: _now,
					e: _ends
				}, function (pt) {
					var _str = '';

					parseInt(e.d) > 0 && (_str = '<span>' + e.d + '天</span>');
					_str += '<span>' + e.h + '时</span><span>' + e.m + '分</span><span>' + e.s + '秒</span>';
					time_div.html(_str);
					!e.t && location.reload();
				});
			});
		}
	}
};

getTpl(function () {
	rushbuyBrandApi.init();
});

function onShow() {
	traffic.setPv(rushbuyBrandApi.data);
}