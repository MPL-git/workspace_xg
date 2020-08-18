var page_rule_subordinate = $('.page[data-id="rule_subordinate"]:visible'),
	page_rule_subordinate_row = page_rule_subordinate.find('.row');

var ruleSubordinateApi = {
	data: {
		tel: '',
		txt: ''
	},

	init: function () {
		var _ts = this,
			_id,
			_type;

		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'type': _type = _b; break;
					case 'id': _id = _b; break;
				}
			}
		}

		new ajax(getApi.getCatalogList, {
			data: {
				catalogId: _id,
				type: _type
			},
			success: function (e) {
				page_rule_subordinate_row.html(template('rule_subordinate_row', e));
			}
		});

		new ajax(getApi.userInfo, {
			success: function (e) {
				var _list = e.returnData;

				_ts.data.tel = _list.customerServiceMobile;
				_ts.data.txt = _list.customerServiceWorkTime;
				page_rule_subordinate_row.after(template('rule_subordinate_footer', _ts.data));
			}
		});
	},

	call: function () {
		this.data.tel && confirmPhone(this.data.tel, this.data.txt);
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
	ruleSubordinateApi.init();
});