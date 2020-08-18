var address_list = $('.page[data-id="order_detail_address_list"]:visible'),
	address_list_scroll = address_list.find('.scroll-more');

var orderAddressList = {
	data: {
		size: 10,
		combineOrderId: '',
		subOrderId: '',
		usedAddressId: ''
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
					case 'addressId': _td.usedAddressId = _b; break;
				}
			}
		}
		
		if(getStorage('address_list')){
			_td.addressIdStorage = JSON.parse(getStorage('address_list')).addressId;
		}

		traffic.setPv();

		_ts.getPagingList();
	},

	getPagingList: function (_scroll) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getMemberAddressList, {
			data: {
				currentPage: address_list_scroll.attr('data-page') || 0,
				pageSize: _ts.data.size
			},
			success: function (res) {
				var _size = res.returnSize,
					_list = res.returnData,
					_len = _list.length;
				if(_td.addressIdStorage){
					res.addressIdStorage = _td.addressIdStorage
				}
				
				if (_scroll) {
					if (_len) {
						var _cur = address_list_scroll.attr('data-page');
						_cur++;
						address_list_scroll.attr('data-page', _cur);
						address_list_scroll.append(template('address_list_tpl', res));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_size && (_ts.data.size = _size);
					address_list_scroll.html(template('address_list_tpl', res));

					_len == _ts.data.size && scrollHand.init(address_list_scroll, function () {
						_ts.getPagingList(true);
					}, true);
				}
			}
		});
	},

	default: function (that) {
		var that = $(that);

		var _c1 = that.find('.con-1'),
			_c1_name = _c1.find('h3 span').text(),
			_c1_phone = _c1.find('b').text(),
			_c1_full = _c1.find('p').text();

		setStorage('address_list', JSON.stringify({
			addressId: that.data('id'),
			recipientName: _c1_name,
			recipientPhone: _c1_phone,
			recipientAddressFullName: _c1_full
		}));

		getUrl(`order/detail/change/index.html?combineOrderId=${this.data.combineOrderId}&subOrderId=${this.data.subOrderId}`, orderDetailChangeApi.changeAddress());
	},
	update: function (that) {
		that = $(that);

		setStorage('address_add', JSON.stringify({
			id: that.data('id'),
			recipient: that.data('recipient'),
			address: that.data('address'),
			province: that.data('province'),

			city: that.data('city'),
			county: that.data('county'),
			phone: that.data('phone'),

			provinceName: that.data('nprovince'),
			cityName: that.data('ncity'),
			countyName: that.data('ncounty'),

			title: '编辑地址'
		}));

		getUrl(`order/detail/address/edit.html?combineOrderId=${this.data.combineOrderId}&subOrderId=${this.data.subOrderId}&addressId=${this.data.usedAddressId}`, 'self');
	},
	deleted: function (that) {
		var _this = this;

		that = $(that);
		if (_this.data.usedAddressId == that.closest('li').data('id')) {
			popupShow('.address-del');
			return
		}
		confirmTip('是否删除收货地址?', function () {
			new ajax(getApi.deleteAddress, {
				data: {
					memberId: member.id,
					id: that.closest('li').data('id')
				},
				success: function () {
					that.closest('li').remove();
					delayTip('成功删除收货地址');
					closeSelf();

					if (!address_list_scroll.find('li').length) {
						address_list_scroll.html(template('address_list_tpl_empty'));
					} else {
						address_list_scroll.removeAttr('data-page').removeAttr('data-old');
						_this.getPagingList();
					}
				}
			});
		});
	},
	addAddress: function () {
		removeStorage('address_add');
		getUrl(`order/detail/address/edit.html?combineOrderId=${this.data.combineOrderId}&subOrderId=${this.data.subOrderId}&addressId=${this.data.usedAddressId}`, 'self');
	}
};

getTpl(function () {
	setTitle('选择地址');
	orderAddressList.init();
});