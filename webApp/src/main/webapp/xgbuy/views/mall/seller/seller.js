var mall_seller = $('.page[data-id="mall_seller"]:visible'),
	mall_seller_scroll = mall_seller.find('.scroll-more'),
	mall_seller_row1 = mall_seller_scroll.find('.row-1'),
	mall_seller_row2 = mall_seller_scroll.find('.row-2'),
	mall_seller_product = '';

var mallSellerApi = {
	share: {
		url: '',
		title: '',
		content: '',
		pictureUrl: ''
	},

	data: {
		mchtid: '', // 商户id
		typeid: '', // 分类id
		new: '', //新品
		price: '', // 价格
		fromPlatform: 0, //页面来自平台标识
		pag: 10,
		productClass: {
			level2: [],
			level3: []
		}
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		$(function () {
			if (search_url) {
				for (var i = 0; i < search_url.length; i++) {
					var _a = search_url[i].split('='),
						_b = _a[1];

					switch (_a[0]) {
						case 'mchtId': _td.mchtid = _b; break;
						case 'id': _td.mchtid = _b; break;
						case 'fromPlatform': _td.fromPlatform = _b; break;
					}
				}
			}

			_ts.post();
		});
	},

	post: function () {
		var _ts = this,
			_td = _ts.data,
			_sr = _ts.share;

		new ajax(getApi.getMchtShoppingInfo, {
			data: {
				mchtId: _td.mchtid
			},
			success: function (e) {
				var _list = e.returnData,
					_title = _list.shopName;

				_sr.title = _title;
				_sr.content = _list.shareContent;
				_sr.url = _list.shareShopUrl;
				_sr.pictureUrl = _list.shopLogo;

				_list.mchtid = _td.mchtid;

				setTitle(_title);

				if (_list.shopStatus == '0') {
					mall_seller_scroll.html(template('mall_seller_tpl_closed', { msg: _list.shopStatusMsg }));
					return;
				}

				_ts.postProduct();

				_list.shopLogo = imgCache(_list.shopLogo, 'mall_seller_tpl_shoplogo');
				_list.brandRecommendMapList = imgCacheEntryPic(_list.brandRecommendMapList, 'mall_seller_tpl_row1_swiper');
				mall_seller_row1.html(template('mall_seller_tpl_row1', _list));


				var _con3 = mall_seller_row1.find('.con-3'),
					_pType = _list.preferentialType,
					_len = _list.brandRecommendMapList.length;

				_len > 1 && initSwiper();

				if (_pType == 1) {
					// 优惠券
					_con3.html(template('mall_seller_tpl_coup1', _list));
				} else if (_pType == 2) {
					// 满减
					var _arr = [],
						_rule = _list.fullCuts[0].rule;

					if (_rule) {
						_rule = _rule.split('|');

						for (var i = 0; i < _rule.length; i++) {
							_rule[i] = _rule[i].split(',');

							_arr.push({
								minimum: _rule[i][0],
								money: _rule[i][1]
							});
						}

						_list.coup2 = _arr;

						_con3.html(template('mall_seller_tpl_coup2', _list));
					}
				} else if (_pType == 3) {
					// 满增
					_con3.html(template('mall_seller_tpl_coup3', _list));
				} else if (_pType == 4) {
					// 多买优惠
					var _arr = [],
						_rule = _list.fullDiscounts[0].rule;

					if (_rule) {
						_rule = _rule.split('|');

						for (var i = 0; i < _rule.length; i++) {
							_rule[i] = _rule[i].split(',');

							_arr.push({
								minimum: _rule[i][0],
								money: _rule[i][1]
							});
						}

						_list.coup4 = _arr;

						_con3.html(template('mall_seller_tpl_coup4', _list));
					}
				}

				shareHand.update(_sr);
			}
		});
	},

	postProduct: function (_scroll, _filter) {
		var _ts = this,
			_td = _ts.data;

		var url = getApi.getMchtShoppingProductList,
			orderType = 0,
			pageSize = 10;

		if (_td.fromPlatform == 1) {
			url = getApi.mchtShopProductList
			if (_td.new != '') {
				orderType = 1
			} else if (_td.price == "asc") {
				orderType = 2
			} else if (_td.price == "desc") {
				orderType = 3
			}
		}
		new ajax(url, {
			data: {
				mchtId: _td.mchtid,
				mallPrice: _td.price,
				newProduct: _td.new,
				shopProductTypeId: _td.typeid,
				currentPage: mall_seller_scroll.attr('data-page') || 0,
				orderType: orderType,
				pageSize: pageSize,
				productType3: _td.typeid
			},
			success: function (e) {
				var _list = e.returnData,
					_len = _list.dataList.length;

				require(['ceiling'], function () {
					_list.dataList = imgCacheProductPic(_list.dataList, 'mall_seller_tpl_product');

					if (_scroll) {
						if (_len) {
							var _cur = mall_seller_scroll.attr('data-page');
							_cur++;
							mall_seller_scroll.attr('data-page', _cur);
							mall_seller_product.append(template('mall_seller_tpl_product', _list));
						} else {
							delayTip('已经全部加载完毕');
						}
					} else {
						if (_filter) {
							mall_seller_product.html(template('mall_seller_tpl_product', _list));
						} else {
							mall_seller_row2.html(template('mall_seller_tpl_row2', _list));

							var _header = mall_seller.find('header:visible');
							_header = _header.length ? _header.height() : 0;

							mall_seller_row2.find('.tag').ceiling(mall_seller_scroll, _header);
							mall_seller_product = mall_seller_row2.find('.product');
						}

						_len == _td.pag && scrollHand.init(mall_seller_scroll, function () {
							_ts.postProduct(true);
						}, true);
					}
				});
			}
		});
	},

	tagFilter: function (_this, _name) {
		var _ts = this,
			_td = _ts.data,
			$this = $(_this),
			_filter = $this.siblings('.tag-filter');

		if (_name == 'filter') {
			if ($this.hasClass('active')) {
				$this.removeClass('active');
				_filter.addClass('hide');

				_td.typeid = '';
			} else {
				$this.addClass('active');

				if (_filter.hasClass('has-data')) {
					_filter.removeClass('hide');
				} else {
					// getApi.mchtShopProductTypeList 
					if (_td.fromPlatform != '1') {
						new ajax(getApi.getMchtShoppingProductClass, {
							data: {
								mchtId: _td.mchtid
							},
							success: function (e) {
								_filter.html(template('mall_seller_tpl_filter', e));
								_filter.addClass('has-data');
								_filter.removeClass('hide');
							}
						});

					} else {
						new ajax(getApi.mchtShopProductTypeList, {
							data: {
								mchtId: _td.mchtid
							},
							success: function (e) {
								console.log(e)
								_td.productClass.level2 = e.returnData
								_filter.html(template('mall_seller_tpl_filter_fromPlatform', _td.productClass));
								_filter.addClass('has-data');
								_filter.removeClass('hide');
							}
						});
					}

				}
			}
		} else if (!$this.hasClass('active') || $this.hasClass('sub')) {
			if ($this.hasClass('sub')) {
				_td.new = '';
				_td.price = '';

				if ($this.hasClass('desc')) {
					$this.removeClass('desc');
					$this.addClass('asc');

					if (_name == 'new') {
						$this.removeClass('active');
						_td.new = 'asc';
					} else if (_name == 'price') {
						_td.price = 'asc';
					}
				} else {
					$this.removeClass('asc');
					$this.addClass('desc');

					if (_name == 'new') {
						$this.addClass('active');
						_td.new = 'desc';
					} else if (_name == 'price') {
						_td.price = 'desc';
					}
				}

				$this.siblings('.sub').removeClass('desc').removeClass('asc');
			} else {
				_td.new = '';
				_td.price = '';
				_td.typeid = '';

				_filter.addClass('hide').children().removeClass('active');
				$this.siblings().removeClass('active').removeClass('desc').removeClass('asc');

				$this.addClass('active').siblings('.filter').text('分类').addClass('jc');
				_filter.find('a').eq(0).addClass('active');
				_td.productClass.level3 = []
				_td.productClass.level2Active = $this.data('id')
				_filter.html(template('mall_seller_tpl_filter_fromPlatform', _td.productClass));
			}

			mall_seller_scroll.removeAttr('data-page');
			_ts.postProduct(false, true);

			$this.siblings(':not(.filter)').removeClass('active');
		}
	},

	tagFilterMore: function (_this) {
		var _ts = this,
			_td = _ts.data,
			$this = $(_this);

		if (!$this.hasClass('active')) {
			_td.typeid = $this.data('id');
			mall_seller_scroll.removeAttr('data-page');
			_ts.postProduct(false, true);

			var _txt = $this.text(),
				_filter = $this.parent().siblings('.filter');

			if (_txt.length > 6) {
				_txt = _txt.substring(0, 5) + '...';
				_filter.removeClass('jc');
			} else {
				_filter.addClass('jc');
			}

			$this.addClass('active').siblings().removeClass('active').parent().addClass('hide').siblings('.dif').removeClass('active');
			_filter.text(_txt).removeClass('active');
		}
	},
	// 分类点击事件 （平台）
	tagFilterMorePlatform: function (_this) {

		var _ts = this,
			_td = _ts.data,
			$this = $(_this),
			_filter = $this.parent().parent();

		if (!$this.hasClass('active')) {
			var tLevel = $this.data('tlevel')
			switch (tLevel) {
				case 2:
					if ($this.data('id') == "") {
						_td.productClass.level3 = []
						_td.productClass.level2Active = $this.data('id')
						_filter.html(template('mall_seller_tpl_filter_fromPlatform', _td.productClass));
						_td.typeid = $this.data('id');
						mall_seller_scroll.removeAttr('data-page');
						_ts.postProduct(false, true);

						var _txt = $this.text();

						if (_txt.length > 6) {
							_txt = _txt.substring(0, 5) + '...';
							_filter.siblings('.filter').removeClass('jc');
						} else {
							_filter.siblings('.filter').addClass('jc');
						}
						$this.addClass('active').siblings().removeClass('active')
						_filter.addClass('hide').siblings('.dif').removeClass('active');
						_filter.siblings('.filter').text(_txt).removeClass('active');
					} else {
						new ajax(getApi.mchtShopProductTypeList, {
							data: {
								mchtId: _td.mchtid,
								productType2: $this.data('id')
							},
							success: function (e) {
								console.log(e)
								_td.productClass.level3 = e.returnData
								_td.productClass.level2Active = $this.data('id')
								_filter.html(template('mall_seller_tpl_filter_fromPlatform', _td.productClass));
							}
						});
					}
					break
				case 3:
					_td.typeid = $this.data('id');
					mall_seller_scroll.removeAttr('data-page');
					_ts.postProduct(false, true);

					var _txt = $this.text(),
						_filter = $this.parent().parent().siblings('.filter');

					if (_txt.length > 6) {
						_txt = _txt.substring(0, 5) + '...';
						_filter.removeClass('jc');
					} else {
						_filter.addClass('jc');
					}
					$this.addClass('active').siblings().removeClass('active')
					$this.parent().parent().addClass('hide').siblings('.dif').removeClass('active');
					_filter.text(_txt).removeClass('active');
					console.log(_td.typeid)
					break
			}

		}
	},


	share: function () {
		var _ts = this,
			_sr = _ts.share;

		_sr.title && shareHand.show();
	}
};

getTpl(function () {
	mallSellerApi.init();
});