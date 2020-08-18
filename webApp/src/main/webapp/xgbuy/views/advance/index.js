var advance = $('.page[data-id="advance"]:visible'),
	advance_scroll = advance.find('.scroll-more');

var advanceApi = {
	data: {
		pag: 10
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '69'
		});

		_ts.getPagingList();
	},

	getPagingList: function (_scroll) {
		var _ts = this;

		new ajax(getApi.preheatList, {
			data: {
				memberId: member.id,
				activityTime: '',
				currentPage: advance_scroll.attr('data-page') || 0,
				pageSize: _ts.data.pag
			},
			success: function (res) {
				var _pag = res.returnSize,
					_list = res.returnData,
					_row = _list.Rows,
					_len = _row.length;

				if (_len) {
					for (var i = 0; i < _len; i++) {
						var _start = new Date(1e3 * _row[i].activityBeginTime),
							_now = new Date(_list.currentTime),
							_t1 = _start.getDate(),
							_t2 = famateNumber(_start.getHours()),
							_t3 = famateNumber(_start.getMinutes());

						_t1 = _now.getDate() == _t1 ? '' : (_t1 > 0 ? _t1 + '日 ' : '');
						_list.Rows[i].time = _t1 + _t2 + ':' + _t3 + ' 开售';
					}

					_list.Rows = imgCacheEntryPic(_list.Rows, 'advance_tpl');
				}

				if (_scroll) {
					if (_len) {
						var _cur = advance_scroll.attr('data-page');
						_list.page = _cur;
						_cur++;
						advance_scroll.attr('data-page', _cur);
						advance_scroll.find('.con-brand').append(template('advance_tpl_list', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_list.page = 0;
					advance_scroll.html(template('advance_tpl', _list));
					_pag && (_ts.data.pag = _pag);

					_len == _ts.data.pag && scrollHand.init(advance_scroll, function () {
						_ts.getPagingList(true);
					}, true);
				}
			}
		});
	},

	getRemind: function (_this) {
		var _ts = this,
			_td = _ts.data;

		getRemind(_this);

		!$(_this).hasClass('reminded') && traffic.setAction(_td, {
			type: 1,
			id: $(_this).data('id')
		});
	}
};

getTpl(function () {
	advanceApi.init();
});

function onShow() {
	traffic.setPv(advanceApi.data);
}