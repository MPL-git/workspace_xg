var page_about = $('.page[data-id="about"]:visible'),
	page_about_row = page_about.find('.row');

var aboutApi = {
	data: {
		tel: '',
		txt: ''
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '54'
		});

		new ajax(getApi.userInfo, {
			success: function (e) {
				var _list = e.returnData;

				_ts.data.tel = _list.customerServiceMobile;
				_ts.data.txt = _list.customerServiceWorkTime;
			}
		});

		new ajax(getApi.getCatalogList, {
			data: {
				catalogId: '',
				type: 2
			},
			success: function (e) {
				page_about_row.html(template('about_row', e));
			}
		});
	},

	call: function () {
		var _ts = this,
			_td = _ts.data;

		_td.tel && confirmPhone(_td.tel, _td.txt);
	},

	getRule: function (_id, _type) {
		if (_type == 1) {
			getUrl('rule/subordinate/index?id=' + _id + '&type=' + _type, 'self');
		} else {
			getUrl('link/information.html?id=' + _id + '&type'  + _type + '&pattern=1', 'self');
		}
	}
};

getTpl(function () {
	aboutApi.init();
});