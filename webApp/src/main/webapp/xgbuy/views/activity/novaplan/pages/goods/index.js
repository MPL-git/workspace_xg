var novaplan_goods = $('.page[data-id="activity_novaplan_pages_goods"]:visible'),
	novaplan_goods_scroll = novaplan_goods.find('.scroll-more'),
	novaplan_goods_nav = novaplan_goods.find('.nav'),
	novaplan_goods_swiper = novaplan_goods.find('.banner'),
	novaplan_goods_goods = novaplan_goods.find('.goods');

var novaplanGoods = {
	data: {
		id: '',
		pag: 10
	},

	init: function () {
		var _ts = this;

		_ts.nav();
	},

	// 导航
	nav: function () {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getProductCategoryManage, {
			data: {
				type: 3
			},
			success: function (res) {
				var _list = res.returnData;

				for (var i = 0; i < _list.length; i++) {
					_list[i].sourceProductTypeName.length > 5 && (_list[i].sourceProductTypeName = _list[i].sourceProductTypeName.substring(0, 5));
				}

				_td.id = _list[0].sourceProductTypeId;

				_ts.swiper();
				_ts.goods();

				novaplan_goods_nav.html(template('novaplan_goods_nav_tpl', res));
			}
		});
	},

	// 轮播图
	swiper: function () {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getProductCategoryAdManage, {
			data: {
				type: 3,
				sourceProductTypeId: _td.id
			},
			success: function (res) {
				var _list = res.returnData,
					_adList = _list.adList;

				_list.adList = imgCachePic(_adList, 'novaplan_goods_swiper');
				novaplan_goods_swiper.html(template('novaplan_goods_swiper_tpl', _list));
				_adList.length > 1 && initSwiper();

				traffic.delayRequest(function () {
					for (var i = 0; i < _adList.length; i++) {
						traffic.setDtl(_td, {
							type: 4,
							id: _adList[i].id,
							pos: i
						});
					}
				});
			}
		});
	},

	// 商品
	goods: function (_scroll) {
		var _ts = this,
			_td = _ts.data,
			_cur = novaplan_goods_scroll.attr('data-page') || 0;

		!_scroll && novaplan_goods_scroll.addClass('scroll-fix');

		new ajax(getApi.getGoodEveryDayProductList, {
			data: {
				type: 3,
				sourceProductTypeId: _td.id,
				currentPage: _cur,
				pageSize: _td.pag
			},
			success: function (e) {
				var _pag = e.returnSize,
					_list = e.returnData,
					_len = _list.dataList.length;

				_list.dataList = imgCacheProductPic(_list.dataList, 'novaplan_goods_tpl_goods');

				if (_scroll) {
					if (_len) {
						_list.page = _cur * _td.pag;
						novaplan_goods_scroll.attr('data-page', ++_cur);
						novaplan_goods_goods.append(template('novaplan_goods_tpl_goods', _list));
					} else if (_cur > 1) {
						delayTip('已经全部加载完毕');
					}
				} else {
					_list.page = 0;
					novaplan_goods_goods.html(template('novaplan_goods_tpl_goods', _list));
					_pag && (_td.pag = _pag);

					scrollHand.init(novaplan_goods_scroll, function () {
						_len == _td.pag && _ts.goods(true);
					}, true);

					scrollFix(novaplan_goods_scroll);
				}
			}
		});
	},

	// 点击导航
	changeNav: function (ts) {
		var _ts = this,
			_td = _ts.data;

		_td.id = $(ts).data('id');
		$(ts).addClass('active').siblings().removeClass('active');
		novaplan_goods_scroll.removeAttr('data-page').scrollTop(0);
		_ts.swiper();
		_ts.goods();
	}
};

getTpl(function () {
	novaplanGoods.init();
});