var help_detail = $('.page[data-id="help_detail"]:visible'),
	help_detail_scroll = help_detail.find('.scroll-more'),
	help_detail_con = help_detail_scroll.find('ul');

var helpDetailApi = {
	data: {
		id: '',
		name: '',
		input: ''
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		$(function () {
			if (search_url) {
				for (var i = 0; i < search_url.length; i++) {
					var _a = search_url[i].split('='),
						_b = _a[1];

					switch (_a[0]) {
						case 'id': _td.id = _b; break;
						case 'name': _td.name = _b; break;
					}
				}
			}

			_td.name && setTitle(_td.name);
			_td.input = getStorage('help_name');
			_ts.main();
		});
	},

	main: function () {
		var _ts = this,
			_td = _ts.data;

		if (_td.id) {
			new ajax(getApi.getHelpItemList, {
				data: {
					helpTagId: _td.id
				},
				success: function (e) {
					if (e.returnData.length) {
						for (var i = 0; i < e.returnData.length; i++) {
							e.returnData[i].content = e.returnData[i].content.replace(/<br>/g, '');
						}
					}

					help_detail_con.html(template('help_detail_tpl', e));
				}
			});
		} else if (_td.input) {
			_td.input = JSON.parse(_td.input);
			removeStorage('help_name');

			if (_td.input.returnData.length) {
				for (var i = 0; i < _td.input.returnData.length; i++) {
					_td.input.returnData[i].content = _td.input.returnData[i].content.replace(/<br>/g, '');
				}
			}

			help_detail_con.html(template('help_detail_tpl', _td.input));
		} else {
			help_detail_con.html(template('help_detail_tpl_empty'));
		}
	},

	detail: function (e) {
		getUrl('help/detail/index.html?id=' + e, 'self');
	}
};

getTpl(function () {
	helpDetailApi.init();
});