var order_express = $('.page[data-id="order_express"]:visible'),
	order_express_con = order_express.find('.content');

var orderExpressApi = {
	data: {
		list: [],
		order_express_data: getStorage('order_express')
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv();

		if (_td.order_express_data) {
			_td.order_express_data = JSON.parse(_td.order_express_data);

			new ajax(getApi.getExpressInfoBySubOrderId, {
				data: {
					memberId: member.id,
					subOrderId: _td.order_express_data.subOrderId
				},
				success: function (res) {
					_td.list = res.returnData.dataList;
					var logistics_con = order_express_con.find('.logistics');
					logistics_con.html(template('order_express_tpl', _td));
					order_express_con.append(template('order_express_tab', _td));

					$.each(logistics_con.find('.copy-bt'), function () {
						clipHnad(this);
					});
				}
			});
		}
	},

	getTab: function (index) {
		order_express.find('.logistics-tab').removeClass('on');
		order_express.find('.logistics-b img').removeClass('on');
		order_express.find('.logistics-tab').eq(index).addClass('on');
		order_express.find('.logistics-b img').eq(index).addClass('on');
	}
}

getTpl(function () {
	orderExpressApi.init();
});