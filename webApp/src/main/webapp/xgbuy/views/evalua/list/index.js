var evalua_list = $('.page[data-id="evalua_list"]:visible'),
	evalua_list_con = evalua_list.find('.content'),
	evalua_list_update = evalua_list.find('.for-update');

var evaluaListApi = {
	data: {
		list: '',
		subOrderId: ''
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		for (var i = 0; i < search_url.length; i++) {
			var _a = search_url[i].split('='),
				_b = _a[1];

			switch (_a[0]) {
				case 'subOrderId': _td.subOrderId = _b; break;
			}
		}

		traffic.setPv();

		_td.subOrderId && _ts.post();
	},

	post: function () {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getOrderCommentList, {
			data: {
				subOrderId: _td.subOrderId
			},
			success: function (e) {
				var _list = e.returnData;

				_td.list = _list;
				_td.list.subOrderId = _td.subOrderId;

				evalua_list_con.html(template('evalua_list_tpl', _list));
				_list.modifyCommentButton && evalua_list_update.append('<a onclick="evaluaListApi.update()">修改</a>');
			}
		});
	},

	update: function () {
		var _ts = this,
			_td = _ts.data;

		if (_td.subOrderId) {
			setStorage('evalua_update_data', JSON.stringify(_td.list));
			getUrl('evalua/update/index.html', 'self');
		}
	}
};

getTpl(function () {
	evaluaListApi.init();
});