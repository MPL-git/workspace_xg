var rushbuy = $('.page[data-id="rushbuy"]:visible'),
	rushbuy_scroll = rushbuy.find('.scroll-more'),
	rushbuy_tbtn = '',
	rushbuy_tcon = '';

var rushbuyApi = {
	data: {
		content: '',
		type: '',
		time: '',
		tab: '',
		con: '',
		beg: 0,
		cur: 0,
		end: 0,
		off: 0
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '1'
		});

		new ajax(getApi.seckillTimeTab, {
			success: function (res) {
				var _index = 0,
					_list = res.returnData,
					_data = _list.data,
					_header = rushbuy.find('header:visible');

				if (!_data.length) {
					rushbuy_scroll.html(template('rushbuy_tpl_emptys'));
					return;
				}

				_list.topPic = imgCachePic(_list.topPic, 'rushbuy_swiper');
				rushbuy_scroll.html(template('rushbuy_tpl', _list));
				
				rushbuy_tbtn = rushbuy_scroll.find('.tab-head .tab-btn');
				rushbuy_tcon = rushbuy_scroll.find('.tab-body .tab-con');

				for (var i = 0; i < _data.length; i++) {
					_data[i].selected && (_index = i);
				}

				_data = _data[_index];
				_td.tab = rushbuy_tbtn.eq(_index);
				_td.con = rushbuy_tcon.eq(_index);
				_td.list = _td.con.find('.list');
				_td.type = _data.type;
				_td.content = _data.content;
				_td.beg = _data.beginTime;
				_td.cur = _data.currentTime;
				_td.end = _data.endTime;
				_td.off = (new Date).getTime();

				_list.topPic.length ? _list.topPic.length > 1 && initSwiper() : rushbuy.addClass('emptys');
				_ts.post();

				require(['ceiling'], function() {
					rushbuy_tbtn.parent().ceiling(rushbuy_scroll, _header.length ? _header.height() : 0);
				});

				shareHand.update();

				traffic.delayRequest(function () {
					for (var i = 0; i < _list.topPic.length; i++) {
						traffic.setDtl(_td, {
							type: 4,
							id: _list.topPic[i].id,
							pos: i
						});
					}
				});

				dropDown(rushbuy, _ts.post);
			}
		});
	},

	post: function (_this, _scroll) {
		var _ts = this,
			_td = _ts.data;

		!$(_this).hasClass('active') && _td.con.attr('data-top', rushbuy_scroll.scrollTop());

		if (!_scroll) {
			if (_this) {
				if ($(_this).hasClass('active')) return;

				_td.tab = $(_this);
				_td.con = rushbuy_tcon.eq(_td.tab.index());
				_td.list = _td.con.find('.list');
				_td.time = _td.list.find('.time');
				_td.type = _td.tab.data('type');
				_td.content = _td.tab.data('content');
				_td.beg = _td.tab.data('beg');
				_td.cur = _td.tab.data('cur');
				_td.end = _td.tab.data('end');
			}

			_ts.active();
			rushbuy_scroll.scrollTop(_td.con.attr('data-top') || 0);

			if (_td.con.hasClass('has-data')) return;
		}

		new ajax(getApi.seckillNewTime, {
			data: {
				beginTime: _td.beg,
				currentPage: _scroll ? rushbuy_scroll.attr('data-page') : 0
			},
			success: function (e) {
				var _list = e.returnData,
					_size = e.returnSize,
					_len = _list.length;

				e.type = _td.type;

				for (var i = 0; i < _len; i++) {
					e.returnData[i].brandGroupList = imgCacheEntryPic(_list[i].brandGroupList, 'rushbuy_brand');
					e.returnData[i].productInfoList = imgCacheProductPic(_list[i].productInfoList, 'rushbuy_product');

					if (e.type == 1 || e.type == 3) {
						for (var j = 0; j < e.returnData[i].productInfoList.length; j++) {
							e.returnData[i].productInfoList[j].salesRatio = parseInt(100 * e.returnData[i].productInfoList[j].salesRatio.toFixed(2)) || 1;
						}
					}
				}

				if (_scroll) {
					if (_len) {
						var _cur = rushbuy_scroll.attr('data-page');
						e.page = _cur * _size;
						_cur++;
						rushbuy_scroll.attr('data-page', _cur);
						_td.con.attr('data-page', _cur);
						_td.con.attr('data-old', rushbuy_scroll.attr('data-old'));

						_td.list.append(template('rushbuy_tpl_con', e));
					} else {
						_td.con.attr('data-old', rushbuy_scroll.attr('data-old'));
						delayTip('已经全部加载完毕');
					}
				} else {
					e.page = 0;
					e.size = _size;
					_td.list.html(template('rushbuy_tpl_scroll', e));
					_td.time = _td.list.find('.time');
					_ts.time();

					(_len == _size && !_td.con.hasClass('has-data')) && scrollHand.init(rushbuy_scroll, function () {
						_ts.post(_this, true);
					}, true);

					_td.con.addClass('has-data');
					_td.con.attr('data-page', rushbuy_scroll.attr('data-page'));
					_td.con.attr('data-old', rushbuy_scroll.attr('data-old'));
				}
			}
		});
	},

	active: function (_a) {
		var _ts = this,
			_td = _ts.data,
			_page = _td.con.attr('data-page'),
			_old = _td.con.attr('data-old');

		_td.tab.addClass('active').siblings().removeClass('active');
		_td.con.removeClass('hide').siblings().addClass('hide');

		_page !== undefined ? rushbuy_scroll.attr('data-page', _page) : rushbuy_scroll.removeAttr('data-page');
		_old !== undefined ? rushbuy_scroll.attr('data-old', _old) : rushbuy_scroll.removeAttr('data-old');
	},

	time: function () {
		var _ts = this,
			_td = _ts.data;

		_td.time.html(template('rushbuy_tpl_time', _td));

		if (_td.type == 5) return;

		var _time = _td.time.find('div'),
			_cur = _td.cur + (new Date).getTime() - _td.off,
			_end = _td.end,
			_str = '距结束';

		if (_td.type == 2 || _td.type == 4) {
			_end = _td.beg;
			_str = '距开始';
		}

		if (_end - _cur > 998) {
			require(['qpTime'], function() {
				$.qpTime({
					s: _cur,
					e: _end
				}, function (e) {
					if (e.t) {
						_time.html('<time class="flex ac jc">' + _str
							+ ':<b class="flex ac jc">' + famateNumber(parseInt(e.h) + 24 * parseInt(e.d))
							+ '</b>:<b class="flex ac jc">' + e.m + '</b>:<b class="flex ac jc">'
							+ e.s + '</b></time>');
					} else {
						location.reload();
					}
				});
			});
		}
	}
};

getTpl(function () {
	rushbuyApi.init();
});

function onShow() {
	traffic.setPv(rushbuyApi.data);
}