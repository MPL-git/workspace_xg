var page_help = $('.page[data-id="help"]:visible'),
	help_scroll = page_help.find('.scroll-more'),
	help_form = page_help.find('form'),
	help_con = help_scroll.find('ul');

var helpApi = {
	data: {

	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '48'
		});

		_ts.main();
	},

	main: function () {
		new ajax(getApi.getHelpTagList, {
			success: function (e) {
				help_con.html(template('help_tpl', e));
			}
		});
	},

	detail: function (e, n) {
		removeStorage('help_name');
		getUrl('help/detail/index.html?id=' + e + '&name=' + n, 'self');
	},

	submit: function (_this) {
		defSelf();

		var _input = $(_this).find('input');
		_input.blur();

		new ajax(getApi.searchHelpItemList, {
			data: {
				helpName: _input.val()
			},
			success: function (e) {
				if (e.returnData.length) {
					setStorage('help_name', JSON.stringify(e));
					getUrl('help/detail/index.html?name=' + _input.val(), 'self');
				} else {
					delayTip('暂无找到匹配内容，请重新输入~');
				}
			},
			limit: 1
		});
	}
};

getTpl(function () {
	helpApi.init();
});

function onShow() {
	traffic.setPv(helpApi.data);
}