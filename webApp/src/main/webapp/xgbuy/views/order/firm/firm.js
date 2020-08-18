var order_firm = $('.page[data-id="order_firm"]:visible'),
	order_firm_con = order_firm.find('.scroll-more'),
	order_firm_footer = order_firm.find('.order-detail-footer'),
	order_firm_footer_con1 = order_firm_footer.find('.con-1'),
	order_firm_footer_strong = order_firm_footer.find('strong'),
	order_firm_freight_con = order_firm.find('.order-firm-freight .freight-con'),
	order_firm_douyin = order_firm.find('.no-after-sale');

var orderFirm = {
	data: {
		list: '',
		pay: getStorage('cart_pay'),
		address: getStorage('address_list'),

		freight: {
			productMapList: [],
			freightCustomMap: [],
			list: [],
			price: '',
			tag: ''
		}
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '71'
		});

		if (_td.pay) {
			_td.pay = JSON.parse(_td.pay);
		} else {
			getUrl('cart', 'self');
			return;
		}

		if (_td.address) {
			_td.address = JSON.parse(_td.address);
			// removeStorage('address_list');
		}

		removeStorage('cart_detail_address');

		_td.pay.combineOrderId = '';
		_td.pay.sourceClient = '1';
		_td.pay.payId = null;

		new ajax(getApi.payOrderInfo, {
			data: {
				cutOrderId: _td.pay.cutOrderId,
				memberId: member.id,
				addressId: _td.address ? _td.address.addressId : '',
				mermberPlatformCouponId: _td.pay.mermberPlatformCouponId,
				shopCardIds: _td.pay.shopCardIds,
				payAmount: _td.pay.payAmount,
				productCouponJson: _td.pay.productCouponJson
			},
			success: function (e) {
				var _list = e.returnData.Rows,
					_prod = _list.productMapList;

				if (_td.address) {
					_td.pay.addressId = _td.address.addressId;

					_list.addressMap.addressId = _td.address.addressId;
					_list.addressMap.recipientName = _td.address.recipientName;
					_list.addressMap.recipientPhone = _td.address.recipientPhone;
					_list.addressMap.recipientAddressFullName = _td.address.recipientAddressFullName;
				} else {
					_td.pay.addressId = _list.addressMap.addressId;
				}

				_list.invalid = [];
				_list.valable = [];

				if (_prod.length) {
					for (var i = 0; i < _prod.length; i++) {
						if (_prod[i].isInvalidProduct == '0') {
							_list.valable.push(_prod[i]);
						} else if (_prod[i].isInvalidProduct == '1') {
							_list.invalid.push(_prod[i]);
						}

						_prod[i].freightType == '2' && _td.freight.productMapList.push(_prod[i]);
					}

					_td.freight.freightCustomMap = _list.freightCustomMap;
				}

				_td.list = _list;
				
				_list.invalid.length && order_firm_footer.find('input').val('返回购物车').siblings().css('opacity', '0');
				order_firm_con.html(template('cart_detail_tpl', _list));
				global_douyin && order_firm_douyin.addClass('douyin').html('了解订单详情：下载醒购APP - 抖音登陆-个人中心-我的订单 <a class="close-x" onclick="orderFirm.closeDouyin()"></a>');

				_td.pay.payId = order_firm_con.find('.popup-pay .ticked').data('id');
				_td.pay.isUserIntegral = _list.integral ? true : false;
				_td.pay.integral = _list.integral;
				_td.shopwnerAmount = _list.shopwnerEquityAmount || 0;
				_ts.countPay();

				_list.addressMap.addressId && order_firm_footer_con1.html('<p class="ellipsis">送货至：' + _list.addressMap.recipientAddressFullName + '</p>');
				!_td.pay.addressId && popupShow('.popup-set-address');

				order_firm_con.scroll(function () {
					$(this).scrollTop() > $(this).find('.row-1').height() ? order_firm_footer_con1.show() : order_firm_footer_con1.hide();
				});
			},
			error: function (res) {
				// back(0, 1);
				delayTip(res.returnMsg)
			},
			limit: 1
		});
	},

	// 积分
	getIntegral: function (_this) {
		var _ts = this,
			_td = _ts.data,
			_this = $(_this).find('.tick');

		if (_this.hasClass('ticked')) {
			_this.removeClass('ticked');
			_td.pay.isUserIntegral = false;
			_td.pay.integral = 0;
		} else {
			_this.addClass('ticked');
			_td.pay.isUserIntegral = true;
			_td.pay.integral = _td.list.integral;
		}

		_ts.countPay();
	},

	// 计算
	countPay: function () {
		var _ts = this,
			_td = _ts.data;

		_td.pay.payAmount = _td.list.totalProductAmount + _td.list.freight - _td.list.preferentialAmount - _td.pay.integral - _td.shopwnerAmount;
		_td.pay.payAmount = Number(_td.pay.payAmount.toFixed(2));
		order_firm_footer_strong.html('¥' + _td.pay.payAmount);
	},

	// 支付方式
	getPayMethod: function (_this) {
		var _ts = this,
			_td = _ts.data,
			_tick = $(_this).find('.tick');

		if (_tick.hasClass('ticked')) {
			_td.pay.payId = null;
			_tick.removeClass('ticked');
		} else {
			_td.pay.payId = _tick.data('id');
			_tick.addClass('ticked').parent().siblings().find('.tick').removeClass('ticked');
		}
	},

	submit: function () {
		var _ts = this,
			_td = _ts.data;

		if (_td.list.invalid.length) {
			getUrl('cart', 'self');
			return;
		}

		if (!_td.pay.addressId) {
			popupShow('.popup-set-address');
			return;
		}

		if (!_td.pay.payId) {
			delayTip('请选择支付方式！');
			return;
		}
		console.log(_td.list.collegeStudentStatus)
		if(_td.list.collegeStudentStatus){
			console.log("需要认证")
			_td.pay.payId = 7
		}

		_td.pay.remarks = order_firm.find('.message input').val();

		global_douyin && (_td.pay.adSprId = getStorage('adSprId'));

		new ajax(getApi.orderPay, {
			data: _td.pay,
			success: function (res) {
				var _list = res.returnData;

				if (!_list.isHasStock) {
					delayTip('库存不足！');
					order_firm_con.find('.is-has-stock').removeClass('hide').siblings('em').remove();
				} else {
					// 抖音布码
					setStorage('pay_from_douyin', 1);
					getDouyin(douyin_convert1);

					setStorage('pay_from_firm', 1);
					payTypeApi(_td.pay.payId, _list);
					$('.page[data-page="cart"]').remove();
				}
			},
			error: function (res) {
				res.returnCode == '4005' ? _ts.backCart(res.returnMsg) : delayTip(res.returnMsg);
			},
			limit: 1
		});
	},

	addressMore: function (_this) {
		setStorage('cart_detail_address', 1);
		getUrl('address/list/index.html?id=' + $(_this).data('id'), 'self');
	},

	addressSet: function () {
		setStorage('cart_detail_address', 1);
		getUrl('address/edit/index.html', 'self');
	},

	backCart: function (_txt) {
		var _tip = $('.page:visible .alert-tip');

		$('.popup').removeClass('show');

		if (_tip.length) {
			_tip.find('p').html(_txt);
			_tip.addClass('show');
		} else {
			$('.page:visible').append('<div onclick="closeSelf(this)" class="popup alert-tip">'
				+ '<div class="popup-center popup-tip" onclick="stopSelf()">'
					+ '<div>'
						+ '<h3 class="flex ac jc">温馨提示</h3>'
						+ '<p class="flex jc txt scroll-y">' + _txt + '</p>'
					+ '</div>'
					+ '<a class="flex ac jc f50" onclick="back(\'cart\', true)">返回购物车</a>'
				+ '</div>'
			+ '</div>');

			$('.page:visible .alert-tip').addClass('show');
		}
	},

	// 运费
	getFreightType: function (_this) {
		var _ts = this,
			_td = _ts.data,
			_this = $(_this),
			_tpl = _this.data('tpl'),
			_type = _this.data('type');

		if (_type == '2') {
			_td.freight.list = [];
			_td.freight.price = '';
			_td.freight.tag = '';

			for (var i = 0; i < _td.freight.productMapList.length; i++) {
				_td.freight.productMapList[i].freightTemplateId == _tpl && _td.freight.list.push(_td.freight.productMapList[i]);
			}

			_td.freight.price = _td.freight.freightCustomMap[_tpl].freightTemplateTotalFreight;
			_td.freight.tag = _td.freight.freightCustomMap[_tpl].freightTemplateName;

			order_firm_freight_con.html(template('order_firm_freight_tpl', _td.freight));
			popupShow('.order-firm-freight');
		}
	},

	// 关闭抖音提示
	closeDouyin: function () {
		order_firm_douyin.remove();
	}
};

getTpl(function () {
	orderFirm.init();
});

function onShow() {
	traffic.setPv(orderFirm.data);
}