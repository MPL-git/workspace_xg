var brand_custom = $('.page[data-id="activity_templet_brand_custom"]:visible'),
	brand_custom_scroll = brand_custom.find('.scroll-more'),
	brand_custom_top = brand_custom.find('.con-top'),
	brand_custom_con = brand_custom.find('.con-brand');

var brandCustomApi = {
	data: {
		size: 10
	},

	append: {
		len: 0,
		arr: [],
		val: []
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'productTypeId': _td.id = _b; break;
					case 'adCatalog': _td.adc = _b; break;
					case 'title': _td.tit = _b; break;
				}
			}
		}

		traffic.setPv(_td, {
			pageType: '60'
		});

		_td.tit && setTitle(_td.tit);
		_ts.main();
	},

	main: function () {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getAdByproductTypeId, {
			data: {
				type: _td.id,
				productTypeId: _td.id,
				catalog: _td.adc
			},
			success: function (e) {
				var _list = e.returnData;

				_list.topPic = imgCachePic(_list.topPic, 'brand_custom_toppic');
				_list.topPic1 = imgCachePic(_list.topPic1, 'brand_custom_toppic1');
				_list.topPic2 = imgCachePic(_list.topPic2, 'brand_custom_toppic1');

				brand_custom_top.html(template('brand_custom_tpl_top', _list));
				_list.topPic.length > 1 && initSwiper();

				_ts.getPagingList();

				traffic.delayRequest(function () {
					for (var i = 0; i < _list.topPic.length; i++) {
						traffic.setDtl(_td, {
							type: 4,
							id: _list.topPic[i].id,
							pos: i
						});
					}

					_list.topPic1[0] && traffic.setDtl(_td, {
						type: 4,
						id: _list.topPic1[0].id
					});

					_list.topPic2[0] && traffic.setDtl(_td, {
						type: 4,
						id: _list.topPic2[0].id
					});
				});
			}
		});
	},

	getPagingList: function (_scroll) {
		var _ts = this,
			_td = _ts.data,
			_tp = _ts.append;

		new ajax(getApi.activityCustom, {
			data: {
				productTypeId: _td.id,
				adCatalog: _td.adc,
				pageSize: _td.size,
				currentPage: brand_custom_scroll.attr('data-page') || 0
			},
			success: function (e) {
				var _list = e.returnData,
					_size = e.returnSize,
					_activ = _list.activityList,
					_brand = _list.brandGroupMap,
					_len = _activ.length;

				!_td.tit && setTitle(_list.title);

				_list = imgCacheEntryPic(_list, 'brand_custom_cache');

				if (_scroll) {
					if (_len) {
						var _cur = brand_custom_scroll.attr('data-page');
						_list.page = _cur * _td.size;
						_cur++;
						brand_custom_scroll.attr('data-page', _cur);

						for (var i = 0; i < _tp.arr.length; i++) {
							if (_tp.arr[i] > _tp.len && _tp.arr[i] <= _tp.len + _len) {
								_list.activityList.splice(_tp.arr[i] - _tp.len + i - 1, 0, _tp.val[i]);
							}
						}

						brand_custom_con.append(template('brand_custom_tpl', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_tp.arr = Object.keys(_brand);

					for (var i = 0; i < _tp.arr.length; i++) {
						_tp.val.push(_brand[_tp.arr[i]]);
					}

					for (var i = 0; i < _tp.arr.length; i++) {
						if (_tp.arr[i] > _tp.len && _tp.arr[i] <= _tp.len + _len) {
							_list.activityList.splice(_tp.arr[i] - _tp.len + i, 0, _tp.val[i]);
						}
					}

					_list.page = 0;
					brand_custom_con.html(template('brand_custom_tpl', _list));

					_size && (_td.size = _size);

					_len == _td.size && scrollHand.init(brand_custom_scroll, function () {
						_ts.getPagingList(true);
					}, true);

					scrollFix();
				}

				traffic.delayRequest(function () {
					for (var i = 0; i < _list.activityList.length; i++) {
						var _la = _list.activityList[i],
							_pg = _list.page + i;

						_la.source == 1 ? traffic.setDtl(_td, {
							type: 1,
							id: _la.activityAreaId,
							pos: _pg
						}) : traffic.setDtl(_td, {
							type: 2,
							id: _la.activityId,
							pos: _pg
						});
					}
				});

				_len && (_tp.len += _len);
			}
		});
	},

	brand: function (_this) {
		setStorage('brand_custom_brand', JSON.stringify({
			pic: _this.getAttribute('data-pic'),
			name: _this.getAttribute('data-name'),
			id: _this.getAttribute('data-id')
		}));

		getUrl('activity/templet/brand_custom_brand.html', 'self');
	}
};

getTpl(function () {
	brandCustomApi.init();
});

function onShow() {
	traffic.setPv(brandCustomApi.data);
}