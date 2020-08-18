var page_revenueUse = $('.page[data-id="revenueUse"]:visible'),
	revenueUse_con = page_revenueUse.find('.tab-body .tab-con'),
	revenueUse_tab = page_revenueUse.find('.tab-top a');


var revenueUseApi = {
	data: {
		con: '',
		scroll: '',
		type: '1',

		slide: 1
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'activityAreaId': _td.activityAreaId = _b; break;
				}
			}
		}

		_ts.getPagingList();


	},

	getPagingList: function (_type, _scroll) {
		var _ts = this,
			_td = _ts.data;

		_type !== undefined && (_td.type = _type);

		if (!_scroll) {
			_ts.active();

			if (_td.scroll.hasClass('has-data')) {
				_td.scroll.addClass('show');
				return;
			}
		}
		var url = ''
		if(_td.type == 1){
			url = getApi.memberAllowanceList
		}else if(_td.type == 2){
			url = getApi.memberUsedAllowanceList
		}
		new ajax(url,{
			data:{
				memberId:member.id,
				pageSize: 10,
				currentPage: _td.scroll.attr('data-page') || 0
			},
			success: function(res){
				var _list = res.returnData,
				_len = _list.list.length,
				_pag = res.returnSize,
				_str = 'revenue_tpl';

			!_list.list && (_list.list = []);
			
			if (_scroll) {
				if (_len) {
					var _cur = _td.scroll.attr('data-page');
					_cur++;
					_td.scroll.attr('data-page', _cur);
					_td.con.append(template(_str, _list));
				} else {
					delayTip('已经全部加载完毕');
				}
			} else {
				_list.cur = 0
				_td.con.html(template(_str, _list));

				_len == 10 && scrollHand.init(_td.scroll, function () {
					_ts.getPagingList(_type, true);
				});

				_td.scroll.addClass('has-data');
			}
				_td.scroll.addClass('has-data');
				!_td.scroll.hasClass('show') && _td.scroll.addClass('show');
			}
		})
		
	},
	active: function () {
		var _ts = this,
			_td = _ts.data;

			revenueUse_tab.removeClass('active');
			revenueUse_con.removeClass('show');

		_td.scroll = revenueUse_con.eq(_td.type - 1);
		_td.con = _td.scroll.find('.touchfix');
		revenueUse_tab.eq(_td.type - 1).addClass('active');
	},

};

getTpl(function () {
	revenueUseApi.init();
});

