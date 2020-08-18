var salesman = $('.page[data-id="activity_shopowner_pages_salesman"]:visible'),
	salesman_con = salesman.find('.touchfix'),
	salesman_footer = salesman.find('.footer');

var salesmanApi = {
	data: {
		id: ''
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		for (var i = 0; i < search_url.length; i++) {
			var _a = search_url[i].split('='),
				_b = _a[1];

			switch (_a[0]) {
				case 'type': _td.type = _b; break;
				case 'id': _td.id = _b; break;
				case 'salesmanId': _td.salesmanId = _b; break;
				case 'infoId': _td.infoId = _b; break;
			}
		}

		(isWeiXin || _td.infoId) ? _ts.main() : salesman_con.html('请使用微信扫描二维码/打开链接~').addClass('flex ac jc').css('font-size', '.3rem');
	},

	main: function () {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getXgShopwnerEquityDetail, {
			data: {
				infoId: _td.infoId
			},
			success: function (res) {
				var _list = res.returnData;

				setTitle(_list.title);

				if (_list.status == '0' && !_td.infoId) {
					getUrl('home');
				} else {
					_td.informationId = _list.informationId;

					$.ajax({
						method: 'get',
						url: lead_url + 'views/activity/nest/salesman/index.html',
						datatype: 'html',
						success: function (data) {
							salesman_con.html(data);
							scrollFix();
						}
					});

					!_td.infoId && salesman_footer.removeClass('hide');
					scrollFix();
				}

				shareHand.update();
			}
		});
	},

	getCreate: function () {
		var _ts = this,
			_td = _ts.data;

		_td.informationId && getUrl('activity/shopowner/pages/create/index.html?salesmanId=' + _td.salesmanId + '&id=' + _td.informationId);
	}
};

getTpl(function () {
	salesmanApi.init();
});