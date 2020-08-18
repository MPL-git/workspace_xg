var mall = $('.page[data-id="mall"]:visible'),
	mall_con = mall.find('.content'),
	mall_nav = mall_con.find('.nav'),
	mall_nav_con = mall_nav.find('.touchfix'),
	mall_main_con = mall_con.find('.main .touchfix'),

	head_search = mall.find('.head-search input');

var mallApi = {
	data: {
		categoryId: ''
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'categoryId': _td.categoryId = _b; break;
					case 'shoptype': _td.shoptype = _b; break;
				}
			}
		}

		traffic.setPv(_td, {
			pageType: '55'
		});

		_ts.nav(_td.shoptype);

		shareHand.update();
	},

	nav: function (_id) {
		var _ts = this,
			_td = _ts.data;

		_id && (_td.categoryId = _id);

		new ajax(getApi.getShoppingMallCategory, {
			success: function (e) {
				var _list = e.returnData,
					_name = _list.searchName;

				_td.categoryId && (_list.activeId = _td.categoryId);
				head_search.attr('placeholder', _name);
				setStorage('mall_input_name', _name);
				mall_nav_con.html(template('mall_nav_tpl', _list));

				var _act = mall_nav_con.find('.active');

				if (_act.length) {
					var	_top = _act.position().top;
					(_top > mall_nav.height() - _act.height()) && mall_nav.scrollTop(_top);

					_ts.main();
				} else {
					mall_con.html(template('mall_tpl_empty'));
				}
			}
		});
	},

	main: function () {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getShoppingMallCategoryData, {
			data: {
				categoryId: _td.categoryId
			},
			success: function (e) {
				var _list = e.returnData;
				mall_main_con.html(template('mall_main_tpl', _list));
			}
		});
	},

	adLink: function (_this) {
		var _type = _this.getAttribute('data-type'),
			_link = _this.getAttribute('data-link'),
			_new = _type + '_' + _link;

		if (_type == 1) {
			// 会场
			new ajax(getApi.areaTempletByAreaId, {
				data: {
					activityAreaId: _link,
				},
				success: function (res) {
					Type1(res.returnData.areaUrl, '', 'intentTrailerListNative', _new);
				}
			});
		} else if (_type == 2) {
			// 活动
			_this.setAttribute('data-activityid', _link);
			getUrlActiveSingle1(_this);
		} else if (_type == 3) {
			// 商品
			_this.setAttribute('data-productid', _link);
			getUrlGoods(_this);
		} else if (_type == 4) {
			// 链接
			location.href = _link;
		} else if (_type == 7) {
			// 自定义
			var _url1 = real_url.split(redirect_url)[0] + redirect_url + 'activity/templet/brand_decorate.html?pageid=' + _link;

			if (member.sys == 'ios') {
				callIntentTrailer('intentTrailerListNative', 'JumpWebView', {'url': _url1});
			} else {
				getUrl(_url1 + '&new=' + _new, 'self');
			}
		} else if (_type == 10) {
			// 店铺
			getUrl('seller/index.html?mchtId=' + _link, 'self');
		} else if (_type == 6 && _link == 24) {
			// 主会场
			getUrl('main_venue/index.html', 'self');
		}
	},

	categoryItemLink: function (e, categoryItemName) {
		var _type = e.getAttribute('data-type'),
			_id = e.getAttribute('data-id'),
			_name = e.getAttribute('data-name'),
			_link = e.getAttribute('data-link');

		removeStorage('mask_search_name');

		if (_type == 1) {
			removeStorage('mall_category_id');
			removeStorage('mall_category_name');
			setStorage('mall_brand_id', _link);
			setStorage('mall_brand_name', _name);
		} else if (_type == 2) {
			removeStorage('mall_brand_id');
			removeStorage('mall_brand_name');
			setStorage('mall_category_id', _link);
			setStorage('mall_category_name', _name);
		}

		getUrl('mall/list/index.html?categoryItemName='+ categoryItemName, 'self');
	}
};

getTpl(function () {
	mallApi.init();
});

function onShow() {
	traffic.setPv(mallApi.data);
}