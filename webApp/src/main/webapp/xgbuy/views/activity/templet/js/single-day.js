var single_day = $('.page[data-id="activity_templet_single_day"]:visible'),
	single_day_scroll = single_day.find('.scroll-more'),

	single_day_api = {
		act: 1
	};

$(function () {
	var _search1 = base64Atob(location.href.split(redirect_url)[1]).split('?')[1];

	if (_search1) {
		var _searchs = _search1.split('&');

		for (var i = 0; i < _searchs.length; i++) {
			var _search = _searchs[i].split('='),
				_search_1 = parseInt(_search[1]);

			_search_1.toString() == 'NaN' && (_search_1 = _search[1]);

			switch (_search[0]) {
				case 'activityAreaId': single_day_api.act = _search_1; break;
			}
		}
	}

	require(['template', 'qpTime'], function (template) {
		new ajax(getApi.areaTempletCode, {
			data: {
				suffix: _suffix,
				activityAreaId: single_day_api.act
			},
			success: function (res) {
				var _code = res.returnData.code;

				if (!_code) {
					single_day_scroll.html(template('single_day_tpl_empty')).addClass('flex dc');
					return;
				}

				var _list = typeof(_code) == 'object' ? _code : JSON.parse(_code),
					_title = _list.title,
					_url = _list.baseUrl || '';

				// 标题
				if (_title) {
					$('title').html(_title);
					$('.page:visible header p').html(_title);
				}

				// 添加根路径
				_list.poster && (_list.poster = _url + _list.poster);

				if (_list.floor) {
					for (var i = 0; i < _list.floor.length; i++) {
						_list.floor[i].title && (_list.floor[i].title = _url + _list.floor[i].title);

						if (_list.floor[i].ad.length > 0) {
							for (var j = 0; j < _list.floor[i].ad.length; j++) {
								_list.floor[i].ad[j].pic && (_list.floor[i].ad[j].pic = _url + _list.floor[i].ad[j].pic);
							}
						}
					}
				}

				single_day_scroll.html(template('activity_single_day_tpl', _list));

				var _floor = single_day_scroll.find('.section'),
					_header = $('.page:visible header').is(':visible') ? $('.page:visible header').height() : 0,
					_sport = _floor.find('.product-sport'),
					_clothing = _floor.find('.product-clothing'),
					_shoes = _floor.find('.product-shoes'),
					_life = _floor.find('.product-life');

				new ajax(getApi.getH5ProductInfoList, {
					data: {
						sportProducts: _sport.data('product') || "",
						clothingProducts: _clothing.data('product') || "",
						shoesProducts: _shoes.data('product') || "",
						lifeProducts: _life.data('product') || ""
					},
					success: function (res) {
						var _lis = res.returnData,
							_time = _lis.currentTime;

						if (_lis) {
							_sport.html(singleDayList(_lis.sportList));
							_clothing.html(singleDayList(_lis.clothingList));
							_shoes.html(singleDayList(_lis.shoesList));
							_life.html(singleDayList(_lis.lifeList));

							_floor.find('.nav a').click(function (evt) {
								evt.preventDefault();
								single_day_scroll.scrollTop(_floor.find('.floor').eq($(this).index()).offset().top + single_day_scroll.scrollTop() - _header);
							});

							// 倒计时
							var count_time = single_day_scroll.find('.count-time');

							if (count_time.length) {
								var _count = count_time.siblings('p'),
									_span = count_time.find('span'),
									_now = _time || new Date().getTime(),

									_start = _list.timer.start.toString().split(',');
									_end = _list.timer.end.toString().split(','),
									_ends = '';

								_start = _start.length > 1 ? singleDayTimer(_start) : _start[0] / 1e3,
								_end = _end.length > 1 ? singleDayTimer(_end) : _end[0] / 1e3,
								_ends = _end;

								if (_now <= _start) {
									_ends = _start;
									_count.html('距离活动开始还剩');
								} else if (_now <= _end) {
									_count.html('离活动结束仅剩');
								} else {
									_ends = false;
									_count.html('活动已结束');
								}

								if (_ends) {
									$.qpTime({
										s: _now,
										e: _ends
									}, function (e) {
										_span.eq(0).html(singleDayTime(e.d));
										_span.eq(1).html(singleDayTime(e.h));
										_span.eq(2).html(singleDayTime(e.m));
										_span.eq(3).html(singleDayTime(e.s));
									});
								}
							}
						}
					}
				});
			}
		});
	});
});

function singleDayTimer(e) {
	var _l = e.length,
		_time;

	if (_l == 2) {
		_time = new Date(e[0], e[1]);
	} else if (_l == 3) {
		_time = new Date(e[0], e[1], e[2]);
	} else if (_l == 4) {
		_time = new Date(e[0], e[1], e[2], e[3]);
	} else if (_l == 5) {
		_time = new Date(e[0], e[1], e[2], e[3], e[4]);
	} else {
		_time = new Date(e[0], e[1], e[2], e[3], e[4], e[5]);
	}

	return _time.getTime();
}

function singleDayTime(e) {
	return '<b class="flex ac jc">' + parseInt(e / 10) + '</b><b class="flex ac jc">' + e % 10 + '</b>';
}

function singleDayList(e) {
	var _str = '';

	if (e) {
		for (var i = 0; i < e.length; i++) {
			var _e = e[i];

			_str += 
			'<a class="mb20"'
				+ 'onclick="getUrlGoods(\'' + _e.productId + '\')">'
				+ '<div class="con-1">'
					+ '<img src="' + _e.pic + '">'
					+ (_e.stock == 0 ? '<div class="flex jc ac">已售罄</div>' : '')
				+ '</div>'
				+ '<div class="con-2">'
					+ '<p title="' + _e.productName + '">' + _e.productName + '</p>'
					+ '<div class="flex ac jb">'
						+ '<span>'
							+ '<strong class="f50">' + _e.salePrice + '</strong>'
							+ '<s class="c9">¥' + _e.tagPrice + '</s>'
						+ '</span>'
						+ (_e.discount ? '<mark class="btn-f50">' + _e.discount + '折</mark>' : '')
					+ '</div>'
				+ '</div>'
			+ '</a>';
		}
	}

	return _str;
}

function singleDayTop() {
	$('.page:visible .scroll-more').scrollTop(0);
}

function activeDayLink(e) {
	var _type = e.getAttribute('data-type'),
		_name = e.getAttribute('data-name'),
		_url = e.getAttribute('data-url');

	if (_type == 1) {
		// 模板(id, 会场id, 模板名)
		new ajax(getApi.areaTempletByAreaId, {
			data: {
				activityAreaId: e.getAttribute('data-activityid')
			},
			success: function (res) {
				getUrl(res.returnData.areaUrl, 'self', null, false, _name);
			}
		});
	} else if (_type == 2) {
		// 特卖(活动id, 活动名)
		getUrlActiveSingle1(e);
	} else if (_type == 3) {
		// 商品详情(商品id, 会场id)
		getUrlGoods(e);
	}
}