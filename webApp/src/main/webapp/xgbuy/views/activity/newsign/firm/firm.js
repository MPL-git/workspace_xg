var newsign_order_firm = $('.page[data-id="activity_newsign_firm"]:visible'),
	cart_detail_scroll = newsign_order_firm.find('.scroll-more'),
	cart_detail_freight_con = newsign_order_firm.find('.popup-freight .freight-con');

var nwesignOrderApi = {
	data: {
		address_Id: ''
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		$(function () {
			_td.address_list = getStorage('address_list');

			if (search_url) {
				for (var i = 0; i < search_url.length; i++) {
					var _a = search_url[i].split('='),
						_b = _a[1];

					switch (_a[0]) {
						case 'productId': _td.productId = _b; break;
						case 'memberId': _td.memberId = _b; break;
						case 'productItemId': _td.productItemId = _b; break;
						case 'cumulativeSignInSettingId': _td.cumulativeSignInSettingId = _b; break;
					}
				}
			}

			requestAppMember(function () {
				_ts.post();
			}, function () {
				getApi.getSignInPayInfo = getApi.getSignInPayInfoH5;
			});
		});
	},

	post: function () {
		var _ts = this,
			_td = _ts.data;

		if (_td.address_list) {
			_td.address_list = JSON.parse(_td.address_list);
			_td.address_Id = _td.address_list.addressId
			removeStorage('address_list');
		}

		new ajax(getApi.getSignInPayInfo, {
			data: {
				memberId: _td.memberId,
				productId: _td.productId,
				productItemId: _td.productItemId,
				addressId: _td.address_Id
			},
			success: function(e) {
				var _list = e.returnData,
					_prod = _list.productDataList,
					_addressId = _list.addressMap.addressId;

				_td.address_Id = _addressId;

				_list.invalid = [];
				_list.valable = [];
		
				if (_prod.length) {
					for (var i = 0; i < _prod.length; i++) {
						if (_prod[i].isInvalidProduct == '0') {
							_list.valable.push(_prod[i]);
						} else if (_prod[i].isInvalidProduct == '1') {
							_list.invalid.push(_prod[i]);
						}
		
						_prod[i].freightType == '2' && cart_detail_freight.productDataList.push(_prod[i]);
					}
				}

				_list.invalid.length && $(cart_detail_submit).val('返回购物车').siblings().css('opacity', '0');

				cart_detail_scroll.html(template('cart_detail_tpl', _list));

				cart_detail_scroll.find('.popup-pay .tap-tick').tap(function () {
					var _tick = $(this).find('.tick');
		
					if (_tick.hasClass('ticked')) {
						cart_detail_pay.payId = 0;
						_tick.removeClass('ticked');
					} else {
						cart_detail_pay.payId = 0;
						_tick.addClass('ticked').parent().siblings().find('.tick').removeClass('ticked');
					}
				});

				var cart_detail_submit = newsign_order_firm.find('.cart_detail_submit'),
					cart_detail_tip = newsign_order_firm.find('.cart_detail_tip');

				cart_detail_submit.click(function () {
					new ajax(getApi.addTiredSignGift, {
						data: {
							memberId: _td.memberId,
							addressId: _td.address_Id,
							productId: _td.productId,
							productItemId: _td.productItemId,
							cumulativeSignInSettingId: _td.cumulativeSignInSettingId,
							type: 2
						},
						success: function () {
							getUrl('order/index.html', 'self');
						}
					});
				});

				cart_detail_scroll.scroll(function () {
					$(this).scrollTop() > $(this).find('.row-1').height() ? cart_detail_tip.show() : cart_detail_tip.hide();
				});
			}
		});
	},

	cartDetailSet: function (e) {
		getUrl('address/list.html?id=' + $(e).data('id'), 'self');
		setStorage('cart_detail_address', true);
	},
	
	addressSet: function () {
		setStorage('cart_detail_address', 1);
		getUrl('address/edit/index.html', 'self');
	},
};

getTpl(function () {
	nwesignOrderApi.init();
});