var novaplan_create = $('.page[data-id="activity_shopowner_pages_create"]:visible'),
	novaplan_create_checkbox = novaplan_create.find('.checkbox');

var novaplanCreateApi = {
	data:{
		read: 0
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
			}
		}

		_ts.post();
		_td.salesmanId && novaplan_create.find('.xieyi').addClass('hide');
	},

	post: function () {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getInformation, {
			data: {
				memberId: member.id,
				id:  _td.id,
				type: _td.type
			},
			success: function (e) {
				var _list = e.returnData;
					
				setTitle(_list.title);

				if (_list.isBlack) {
					shield(_list.blackReason);
					return;
				}

				_td.payAmount = _list.payAmount;

				novaplan_create.find('.content').html(_list.content);
				_list.catalogId === '68' && novaplan_create_checkbox.html(template('novaplan_create_tpl', _list));
			}
		});
	},

	open: function () {
		var _ts = this,
			_td = _ts.data;

		_td.read ? orderPayType('', _td.payAmount, 1) : delayTip('请先阅读并同意《醒购店长计划协议》');
	},

	tick: function (_this) {
		var _ts = this,
			_td = _ts.data,
			_that = $(_this).find('a');

		if (_td.read) {
			_td.read = 0;
			_that.removeClass('ticked');
		} else {
			_td.read = 1;
			_that.addClass('ticked');
		}
	},

	orderPay: function (_this) {
		var _ts = this,
			_td = _ts.data,
			_id = $(_this).closest('.order-detail-footer').siblings('.bot-2').find('.ticked').data('id');

		_id ? new ajax(getApi.submitShopownerOrderPayment, {
			data: {
				payAmount: _td.payAmount,
				salesmanId: _td.salesmanId,
				paymentId: _id
			},
			success: function (res) {
				setStorage('pay_sign', JSON.stringify(res.returnData));
				_ts.weChatPay(function () {
					novaplan_create_checkbox.find('.checkbox-item').addClass('hide').siblings('.btn-red').addClass('btn-gray').html('已购买').removeAttr('onclick');
					getUrl('home', true);
				});
			}
		}) : delayTip('请先勾选支付方式！');
	},

	weChatPay: function (_f) {
		var _ts = this,
			_td = _ts.data;

		if (typeof WeixinJSBridge == "undefined") {
			if (document.addEventListener) {
				document.addEventListener('WeixinJSBridgeReady', function () {
					_ts.onBridgeReady(_f);
				}, false);
			} else if (document.attachEvent) {
				document.attachEvent('WeixinJSBridgeReady', function () {
					_ts.onBridgeReady(_f);
				});
				document.attachEvent('onWeixinJSBridgeReady', function () {
					_ts.onBridgeReady(_f);
				});
			}
		} else {
			_ts.onBridgeReady(_f);
		}
	},

	onBridgeReady:function (_f) {
		var _list = getStorage('pay_sign');

		if (_list) {
			_list = JSON.parse(_list);

			WeixinJSBridge.invoke('getBrandWCPayRequest', {
				appId: _list.appId,
				timeStamp: _list.timeStamp.toString(),
				nonceStr: _list.nonceStr,
				package: _list.package,
				signType: _list.signType,
				paySign: _list.paySign
			}, function(res) {
				if (res.err_msg === 'get_brand_wcpay_request:ok') {
					delayTip('支付成功', 1e3, function () {
						_f ? _f() : location.reload();
					});
				} else if (res.err_msg === 'system:access_denied' && android_pay_fix) {
					android_pay_fix = 0;
					_ts.onBridgeReady(_f);
				} else {
					delayTip('支付失败', 1e3, function () {
						location.reload();
					});
				}
			});
		}
	}
};

getTpl(function () {
	novaplanCreateApi.init();
});
