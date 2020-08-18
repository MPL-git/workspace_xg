var address = $('.page[data-id="address"]:visible'),
	address_con = address.find('.scroll-more');

var addressApi = {
	data: {
		size: 10
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '45'
		});

		removeStorage('address_add');

		_ts.getPagingList();
	},

	getPagingList: function (_scroll) {
		var _ts = this,
			_td = _ts.data;

		_td.post = 0;

		new ajax(getApi.getMemberAddressList, {
			data: {
				currentPage: address_con.attr('data-page') || 0,
				pageSize: _ts.data.size
			},
			success: function (res) {
				var _size = res.returnSize,
					_list = res.returnData,
					_len = _list.length;

				if (_scroll) {
					if (_len) {
						var _cur = address_con.attr('data-page');
						_cur++;
						address_con.attr('data-page', _cur);
						address_con.append(template('address_tpl', res));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_size && (_ts.data.size = _size);
					address_con.html(template('address_tpl', res));

					_len == _ts.data.size && scrollHand.init(address_con, function () {
						_ts.getPagingList(true);
					}, true);
				}
			},
			complete: function () {
				_td.post = 1;
			}
		});
	},

	back: function () {
		getStorage('cart_detail_address') ? getUrl('address/list', true) : getUrl('my');
	},

	default: function (that) {
		var _ts = this,
			_td = _ts.data;

		that = $(that);

		if (_td.post && !that.hasClass('ticked')) {
			_td.post = 0;

			new ajax(getApi.defaultAddress, {
				data: {
					memberId: member.id,
					id: that.closest('.con-2').data('id')
				},
				success: function () {
					address_con.find('.ticked').removeClass('ticked');
					that.addClass('ticked');
				},
				complete: function () {
					_td.post = 1;
				}
			});
		}
	},

	deleted: function (that) {
		var _ts = this,
			_td = _ts.data;

		that = $(that);

		confirmTip('是否删除收货地址?', function () {
			new ajax(getApi.deleteAddress, {
				data: {
					memberId: member.id,
					id: that.closest('.con-2').data('id')
				},
				success: function () {
					that.closest('li').remove();
					delayTip('成功删除收货地址');
					closeSelf();
					setStorage('address_del', that.closest('.con-2').data('id'));
					if (!address_con.find('li').length) {
						address_con.html(template('address_tpl_empty'));
					} else {
						address_con.removeAttr('data-page').removeAttr('data-old');
						_ts.getPagingList();
					}
				}
			});
		});
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

		getUrl('address/edit/index.html', 'self');
	}
};

getTpl(function () {
	addressApi.init();
});

function onShow() {
	traffic.setPv(addressApi.data);
}