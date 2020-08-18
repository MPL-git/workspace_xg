var evalua_product = $('.page[data-id="evalua_product"]:visible'),
	evalua_product_scroll = evalua_product.find('.scroll-more');
	evalua_product_con = evalua_product_scroll.find('.touchfix');

var evalua_product_share = getStorage('evalua_product');

var evaluaProductApi = {
	data: {
		size: 10,
		commentIdList: [],
		productId: ''
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		for (var i = 0; i < search_url.length; i++) {
			var _a = search_url[i].split('='),
				_b = _a[1];

			switch (_a[0]) {
				case 'productId': _td.productId = _b; break;
			}
		}

		traffic.setPv();

		if (evalua_product_share) {
			evalua_product_share = JSON.parse(evalua_product_share);
			shareHand.update(evalua_product_share);
		}
		
		_td.productId && _ts.post();
	},

	post: function (_scroll) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getUserProductAllComment, {
			data: {
				productId: _td.productId,
				currentPage: evalua_product_scroll.attr('data-page') || 0,
				commentIdList: _td.commentIdList
			},
			success: function (e) {
				var _size = e.returnSize,
					_list = e.returnData,
					_len = _list.dataList.length;

				if (_scroll) {
					if (_len) {
						var _cur = evalua_product_scroll.attr('data-page');
						_cur++;
						evalua_product_scroll.attr('data-page', _cur);
						evalua_product_con.append(template('evalua_product_tpl', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					evalua_product_con.html(template('evalua_product_tpl', _list));
					_td.commentIdList = _list.commentIdList;
					_size && (_td.size = _size);

					_len == _td.size && scrollHand.init(evalua_product_scroll, function () {
						_ts.post(true);
					}, true);
				}
			}
		});
	},

	share: function () {
		shareHand.show();
	}
};

getTpl(function () {
	evaluaProductApi.init();
});