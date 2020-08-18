var address_list = $('.page[data-id="address_list"]:visible'),
	address_list_scroll = address_list.find('.scroll-more');

var addressList = {
	data: {
		size: 10
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;
		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'id': _td.id = _b; break;
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
			_c1_name = _c1.find('span').text(),
			_c1_phone = _c1.find('b').text(),
			_c1_full = _c1.find('p').text();

		setStorage('address_list', JSON.stringify({
			addressId: that.data('id'),
			recipientName: _c1_name,
			recipientPhone: _c1_phone,
			recipientAddressFullName: _c1_full
		}));

		location.reload();
	}
};

getTpl(function () {
	addressList.init();
});