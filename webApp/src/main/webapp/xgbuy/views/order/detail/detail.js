var order_detail = $('.page[data-id="order_detail"]:visible'),
	order_detail_con = order_detail.find('.content'),
	order_detail_timer,
	order_detail_timer_clear;

	order_detail_timer_clear && clearInterval(order_detail_timer_clear);

var orderDetailApi = {
	data: {
		combineOrderId: '',
		subOrderId: ''
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'combineOrderId': _td.combineOrderId = _b; break;
					case 'subOrderId': _td.subOrderId = _b; break;
				}
			}
		}

		traffic.setPv();

		_ts.main();
	},

	main: function () {
		var _ts = this,
			_td = _ts.data;

		require(['qpTime'], function () {
			new ajax(getApi.orderDtlInfo, {
				data: {
					memberId: member.id,
					combineOrderId: _td.combineOrderId,
					subOrderId: _td.subOrderId
				},
				success: function (res) {
					var _list = res.returnData,
						_data = _list.dataList[0];

					for (var i = 0; i < _data.data.length; i++) {
						_data.data[i].depositSum = _data.data[i].depositSum.toFixed(2);
						_data.data[i].deductAmountSum = _data.data[i].deductAmountSum.toFixed(2);
					}

					order_detail_con.html(template('order_detail_tpl', _data));
					_list.spoofingPic && order_detail.find('.notice').html('<img src="' + _list.spoofingPic + '">');

					var receipt_time = order_detail_con.find('.receipt-time');
					receipt_time[0] && receipt_time.html(_data.confirmReceiptTimeStr);

					_data.subOrderStatus != 4 && _data.subOrderStatus != 5 && order_detail.addClass('show-after-sale');

					clipHnad(order_detail_con.find('.btn-copy')[0]);

					order_detail_timer = order_detail.find('.order-detail-timer i');

					if (_data.subOrderStatus == '0' && order_detail_timer.length) {
						order_detail_timer_clear = $.qpTime({
							s: _data.currentTime,
							e: _data.unpaidEndTime
						}, function (pt) {
							if (pt.t) {
								order_detail_timer.html(pt.h + ':' + pt.m + ':' + pt.s);
							} else {
								new ajax(getApi.cancelOrderById, {
									data: {
										memberId: member.id,
										combineOrderId: _td.combineOrderId
									},
									success: function () {
										location.reload();
									},
									limit: 1
								});
							}
						});
					}
				}
			});
		});
	},

	// 取消订单
	cancelSelf: function (_com) {
		confirmTip('是否取消该订单？', function () {
			new ajax(getApi.cancelOrderById, {
				data: {
					memberId: member.id,
					combineOrderId: _com
				},
				success: function () {
					back(false, true);
					delayTip('成功取消订单');
				},
				limit: 1
			});
		});
	},

	// 删除订单
	deleteSelf: function (_sub, _com) {
		confirmTip('是否删除该订单？', function () {
			new ajax(getApi.deleteOrderById, {
				data: {
					memberId: member.id,
					subOrderId: _sub,
					combineOrderId: _com
				},
				success: function (e) {
					back(false, true);
					delayTip('成功删除订单');
				},
				limit: 1
			});
		});
	},

	// 追评
	evalua: function (_dtl, _sub) {
		stopSelf();
		getUrl('evalua/index.html?orderDtlId=' + _dtl + '&subOrderId=' + _sub + '&type=2', 'self');
	},

	// 确认收货
	orderConfirm: function (_sub) {
		var _ts = this,
			_td = _ts.data;

		_td.sub = _sub;

		confirmTip('您确认您已经收到您购买的物品？', function () {
			new ajax(getApi.confirmReceiptOrderById, {
				data: {
					memberId: member.id,
					subOrderId: _sub
				},
				success: function () {
					getUrl('', true);
					delayTip('您已成功确认收货，前往评价可获得积分哦~');
				},
				limit: 1
			});
		});
	},

	// 售后服务
	myApp: function () {
		stopSelf();

		confirmTip('请在APP上操作, 是否现在打开APP?', function () {
			closeSelf();
			getUrl('my/download/index.html');
		});
	}
};

getTpl(function () {
	orderDetailApi.init();
});