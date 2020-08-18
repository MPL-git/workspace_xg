var page_history = $('.page[data-id="history"]:visible'),
	history_scroll = page_history.find('.scroll-more'),

	history_tab_con = page_history.find('.tab-top a'),
	history_body = page_history.find('.popup-clear .tab-body'),

	history_main1 = history_scroll.find('.tab1'),
	history_main2 = history_scroll.find('.tab2'),
	history_main3 = history_scroll.find('.tab3'),

	history_con = '';

var historyApi = {
	data: {
		size: 10,
		type: 1
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '44'
		});

		_ts.getPagingList();
	},

	getPagingList: function (_scroll) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.footPrint, {
			data: {
				currentPage: $(history_scroll[_td.type -1]).attr('data-page') || 0,
				type: _td.type,
				pageSize: _td.size
			},
			success: function (e) {
				var _size = e.returnSize,
					_list = e.returnData,
					_len = _list.length;
					e.type = _td.type;

				if (_len) {
					for (var i = 0; i < _list.length; i++) {
						e.returnData[i].discount = famateDiscount(_list[i].discount);
					}

					e.returnData = imgCachePic(_list, 'history_cache');
				}

				if (_scroll) {
					if (_len) {
						var _cur = $(history_scroll[_td.type -1]).attr('data-page');
						_cur++;

						if (_td.type == 1) {
							history_con = history_main1.find('.con-goods');
						} else if (_td.type == 2) {
							history_con = history_main2.find('.con-goods');
						} else {
							history_con = history_main3.find('.con-goods');
						}

						if ($(history_scroll[_td.type -1]).find('.con-goods dl').length >= 10) {
							$(history_scroll[_td.type -1]).attr('data-page', _cur);
							history_con.append(template('history_tpl_con', e));
						}
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_td.list = _len;

					if (_td.type == 1) {
						history_main1.html(template('history_tpl_con', e));
						history_con = history_main1.find('.con-goods');
					} else if (_td.type == 2) {
						history_main2.html(template('history_tpl_con', e));
						history_con = history_main2.find('.con-goods');
					} else {
						history_main3.html(template('history_tpl_con', e));
						history_con = history_main3.find('.con-goods');
					}

					_size && (_td.size = _size);

					_len == _td.size && scrollHand.init(history_scroll, function () {
						_ts.getPagingList(true);
					}, true);

					scrollFix();
				}
			}
		});
	},

	//商品 
	goods: function () {
		var _ts = this,
		_td = _ts.data;
		
		if (!$(history_tab_con[0]).hasClass('active')) {
			for (var i = 0; i < history_tab_con.length; i++) {
				if ($(history_tab_con[i]).hasClass('active')) {
					$(history_tab_con[i]).removeClass('active');
				}
			}
			$(history_tab_con[0]).addClass('active');
			history_body.empty();
			history_body.html('确定要清空所有商品足迹吗？');
			_td.type = 1;
			for (var i = 0; i < history_scroll.length; i++) {
				if (!$(history_scroll[i]).hasClass('hide')) {
					$(history_scroll[i]).addClass('hide');
				}
			}
			$(history_scroll[0]).removeClass('hide');
			$(history_scroll[0]).removeAttr('data-page');
			$(history_scroll[0]).removeAttr('data-old');
			$(history_scroll[0]).find('.touchfix').empty();
			
			_ts.getPagingList();
			$(history_scroll[0]).animate({scrollTop:0},10);
		}
	},

	// 店铺
	shop: function () {
		var _ts = this,
		_td = _ts.data;

		if (!$(history_tab_con[2]).hasClass('active')) {
			for (var i = 0; i < history_tab_con.length; i++) {
				if ($(history_tab_con[i]).hasClass('active')) {
					$(history_tab_con[i]).removeClass('active');
				}
			}
			$(history_tab_con[2]).addClass('active');
			history_body.empty();
			history_body.html('确定要清空所有店铺足迹吗？');
			_td.type = 2;
			for (var i = 0; i < history_scroll.length; i++) {
				if (!$(history_scroll[i]).hasClass('hide')) {
					$(history_scroll[i]).addClass('hide');
				}
			}
			$(history_scroll[1]).removeClass('hide');
			$(history_scroll[1]).removeAttr('data-page');
			$(history_scroll[1]).removeAttr('data-old');
			$(history_scroll[1]).find('.touchfix').empty();
			_ts.getPagingList();
			$(history_scroll[1]).animate({scrollTop:0},10);
		}
	},

	// 特卖
	spsell: function () {
		var _ts = this,
		_td = _ts.data;

		if (!$(history_tab_con[1]).hasClass('active')) {
			for (var i = 0; i < history_tab_con.length; i++) {
				if ($(history_tab_con[i]).hasClass('active')) {
					$(history_tab_con[i]).removeClass('active');
				}
			}
			$(history_tab_con[1]).addClass('active');
			history_body.empty();
			history_body.html('确定要清空所有特卖足迹吗？');
			_td.type = 3;
			for (var i = 0; i < history_scroll.length; i++) {
				if (!$(history_scroll[i]).hasClass('hide')) {
					$(history_scroll[i]).addClass('hide')
				}
			}
			$(history_scroll[2]).removeClass('hide');
			$(history_scroll[2]).removeAttr('data-page');
			$(history_scroll[2]).removeAttr('data-old');
			$(history_scroll[2]).find('.touchfix').empty();
			
			_ts.getPagingList();
			$(history_scroll[2]).animate({scrollTop:0},10);
		}
	},

	showClear: function () {
		var _ts = this,
			_td = _ts.data;

		_td.list ? popupShow('.popup-clear') : delayTip('您还没有任何浏览记录~');
	},

	clear: function () {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.deleteFootPrint, {
			data: {
				memberId: member.id,
				type: _td.type
			},
			success: function () {
				new ajax(getApi.footPrint, {
					data: {
						currentPage: 0,
						type: _td.type,
						pageSize: _td.size
					},
					success: function (e) {
						_td.list = 0;

						if (_td.type == 1) {
							history_main1.html(template('history_tpl_con', e));
						} else if (_td.type == 2) {
							history_main2.html(template('history_tpl_con', e));
						} else {
							history_main3.html(template('history_tpl_con', e));
						}
						closeSelf();
						delayTip('成功清空足迹');
					}
				});
			}
		});
	}
};

getTpl(function () {
	historyApi.init();
});

function onShow() {
	traffic.setPv(historyApi.data);
}