var record = $('.page[data-id="activity_novaplan_cash_record"]:visible'),
	record_tab = record.find('.tab-top'),
	record_tab_con = '',
	record_body_con = record.find('.tab-body .tab-con');

var cashRecordApi = {
	data: {
		pag: 10,
		tab: 1,
		scroll: '',
		con: ''
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		$(function () {
			requestAppMember(function () {
				record_tab_con = record_tab.find('a');
				_ts.main(1);
			});
		});
	},

	main: function (_tab, _scroll) {
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

		new ajax(getApi.getMemberBalanceDtl, {
			data: {
				type: _td.tab,
				currentPage: _td.scroll.attr('data-page') || 0
			},
			success: function (e) {
				var _size = e.returnSize.dataList,
					_len = e.returnData.dataList.length;

				e.tab = _td.tab;

				if (_scroll) {
					if (_len) {
						var _cur = _td.scroll.attr('data-page');
						_cur++;
						_td.scroll.attr('data-page', _cur);
						_td.con.append(template('novaplan_cash_tpl_record', e));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_td.con.html(template('novaplan_cash_tpl_record', e));

					_size && (_td.pag = _size);

					_len == _td.pag && scrollHand.init(_td.scroll, function () {
						_ts.main(_tab, true);
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

		record_tab_con.removeClass('active');
		record_body_con.removeClass('show');

		_td.scroll = record_body_con.eq(_index);
		_td.con = _td.scroll.find('.touchfix');
		record_tab_con.eq(_index).addClass('active');
	},

	// 驳回原因 
	tip: function (e) {
		var tby = record.find('.tab-by');
		tby.empty();
		tby.html(e);
		popupShow('.popup-clear');
	}
};

getTpl(function () {
	cashRecordApi.init();
});