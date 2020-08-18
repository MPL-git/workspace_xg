var order_firmpre = $('.page[data-id="order_firmpre"]:visible'),
	order_firmpre_con = order_firmpre.find('.scroll-more'),
	order_firmpre_sale = order_firmpre.find('.order-detail-footer strong'),
	order_firmpre_message = '';

var orderFirmpre = {
	data: {
		payId: false, // 是否勾选payId
		protocol: false // 是否勾选协议
	},

	init: function () {
		var _ts = this,
			_td = _ts.data,
			_sr = getStorage('order_firmpre');

		traffic.setPv();

		if (_sr) {
			_sr = JSON.parse(_sr);

			_td.productId = _sr.productId;
			_td.productItemId = _sr.productItemId;
			_td.quantity = _sr.quantity;

			new ajax(getApi.getPayDepositOrderInfo, {
				data: {
					productId: _td.productId,
					productItemId: _td.productItemId,
					quantity: _td.quantity
				},
				success: function (res) {
					var _list = res.returnData;

					order_firmpre_con.html(template('order_firmpre_con_tpl', _list));
					order_firmpre_sale.html(_list.deposit);
					order_firmpre_message = order_firmpre.find('.message input');

					for (var i = 0; i < _list.payMapList.length; i++) {
						if (_list.payMapList[i].defaultPay == '1') {
							_td.payId = _list.payMapList[i].payId;
							break;
						}
					}
				},
				target: 1
			});
		} else {
			back('', 1);
		}
	},

	// 勾选payId
	getPayId: function (_this) {
		var _tick = $(_this).find('.tick');

		_tick.addClass('ticked').closest('.tap-tick').siblings().find('.tick').removeClass('ticked');
		this.data.payId = _tick.data('id');
	},

	// 提交支付
	submit: function () {
		var _ts = this,
			_td = _ts.data;

		if (!_td.payId) {
			delayTip('请选择支付方式！');
			return;
		}

		if (!_td.protocol) {
			delayTip('请先阅读并同意定金不退预售协议！');
			return;
		}

		new ajax(getApi.submitDepositOrderPayment, {
			data: {
				productId: _td.productId,
				productItemId: _td.productItemId,
				totalQuantity: _td.quantity,
				remarks: order_firmpre_message.val(),
				payId: _td.payId
			},
			success: function (res) {
				var _list = res.returnData;

				if (_list.isHasStock) {
					setStorage('order_pay_earnest', 1);
					payTypeApi(_td.payId, _list, true);
				} else {
					delayTip('库存不足！');
				}
			},
			limit: 1
		});
	},

	// 勾选协议
	protocol: function (_this) {
		$(_this).toggleClass('ticked');
		this.data.protocol = !this.data.protocol;
	}
};

getTpl(function () {
	orderFirmpre.init();
});
