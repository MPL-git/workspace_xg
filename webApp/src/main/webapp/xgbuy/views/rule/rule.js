var page_rule = $('.page[data-id="rule"]:visible'),
	page_rule_row = page_rule.find('.row');

var ruleApi = {
	data: {
		list: []
	},

	init: function () {
		var _ts = this;

		new ajax(getApi.getCatalogList, {
			data: {
				catalogId: '',
				type: 1
			},
			success: function (e) {
				var _list = e.returnData;
				
				_ts.data.list = _list;
				page_rule_row.html(template('rule_row', _ts.data));
			}
		});
	}
};

function getRule (_id, _type) {
	if (_type == 1) {
		getUrl('rule/subordinate/index?id=' + _id + '&type=' + _type, 'self');
	} else {
		getUrl('link/information.html?id=' + _id + '&type'  + _type + '&pattern=1', 'self');
	}
}

getTpl(function () {
	ruleApi.init();
});