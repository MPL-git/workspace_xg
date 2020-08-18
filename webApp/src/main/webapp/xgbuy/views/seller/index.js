var seller = $('.page[data-id="seller"]:visible'),
	seller_search = seller.find('#seller_search'),
	seller_header_height = seller.find('header').height(),
	seller_topdd = seller.find('.col-top dd'),
	seller_coupon = seller.find('.shop-coupon'),
	seller_topdt = seller.find('.col-top dt'),
	seller_nav = seller.find('.col-nav'),
	seller_filter = seller.find('.dialog-filter'),
	seller_decorate = seller.find('.decorate'),
	seller_scroll = seller.find('.tab-body'),
	seller_con = seller.find('.content .tab-con');

var seller_share = {
	url: real_url,
	content: '刚刚在醒购微信购物发现一个不错的店铺，赶紧来看看吧！'
};

var sellerApi = {
	data: {
		pag: 10,

		mchtId: '',
		mchtCode: '',

		type: '',  // 1首页; 2商品; 3上新

		tab: 0, // 选项卡
		con: '', // 当前 tab-con
		old: '', // 历史 tab-con

		info: '', // 判断只加载一次顶部信息
		filter: '', // 判断只加载一次筛选项

		top: 0 // nav与顶部距离
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		for (var i = 0; i < search_url.length; i++) {
			var _a = search_url[i].split('='),
				_b = _a[1];

			switch (_a[0]) {
				case 'mchtId': _td.mchtId = _b; break;
				case 'mchtCode': _td.mchtCode = _b; break;
				case 'preview': _td.preview = _b; break;
			}
		}

		_td.mchtId && traffic.setPv(_td, {
			pageType: '59',
			mchtId: _td.mchtId
		});

		$.ajax({
			method: 'get',
			url: lead_url + 'views/activity/nest/seller/index.html',
			datatype: 'html',
			success: function (data) {
				setStorage('nest_seller', JSON.stringify({
					mchtId: _td.mchtId,
					mchtCode: _td.mchtCode,
					preview: _td.preview
				}));

				seller_decorate.html(data);
			}
		});

		_ts.shopmsg();
		_ts.main();

		if (_td.preview) {
			seller_topdd.addClass('p-e-n');
			seller.find('header').addClass('p-e-n');
			seller.find('.content').addClass('p-e-n');
			seller.find('.footer-tab .ico-2').addClass('p-e-n');
			seller.find('.footer-tab .ico-3').addClass('p-e-n');
		}
		
		new ajax(getApi.addFootPrint, {
			data: {
				memberId: member.id,
				type: 2,
				productId: _td.mchtId
			},
			disLogin: 1
		});
	},

	main: function (_tab, _scroll, _ob) {
		var _ts = this,
			_td = _ts.data,
			_obs = Object.assign({
				brandId: '', // 品牌id
				productTypeId: '', // 一级类目id
				classId: '', // 精选分类id
				searchName: '' // 搜索词
			}, _ob);

		if (_td.shopcouponlist && _tab == 0) {
			seller_coupon.removeClass('hide');
		} else {
			seller_coupon.addClass('hide');
		}

		_obs.searchName && (_obs.name = _obs.searchName);
		_obs.name && seller_search.val(_obs.name);
		_td.tab = _tab || 0;
		_td.con = seller_con.eq(_td.tab);
		if (!_td.con.hasClass('hide') && !_scroll && !_ob) return;

		_td.api = getApi.getShopProductList;
		_td.tpl = 'seller_product_tpl';
		_td.left = _td.con.find('.product-sp3 .fl');
		_td.right = _td.con.find('.product-sp3 .fr');
		_td.old && !_ob && _td.old.attr('data-top', seller_scroll.scrollTop());
		_td.old = _td.con;

		if (!_scroll) {
			_ts.active();

			if (_td.con.attr('data-page')) {
				seller_scroll.attr('data-page', _td.con.attr('data-page'));
				seller_scroll.attr('data-old', _td.con.attr('data-old'));
			} else {
				seller_scroll.removeAttr('data-page');
				seller_scroll.removeAttr('data-old');
			}

			if (_td.con.hasClass('has-data')) {
				_ts.initScroll(_ob);
				return;
			}
		}

		if (_td.tab === 0) {
			_td.type = 1;
		} else if (_td.tab === 1) {
			_td.type = 2;
		} else if (_td.tab === 2) {
			_td.api = getApi.getShopActivityList;
		} else if (_td.tab === 3) {
			_td.type = 3;
		}

		new ajax(_td.api, {
			data: {
				mchtId: _td.mchtId,
				mchtCode: _td.mchtCode,
				type: _td.type,
				brandId: _obs.brandId,
				productTypeId: _obs.productTypeId,
				classId: _obs.classId,
				searchName: _obs.searchName,
				currentPage: seller_scroll.attr('data-page') || 0
			},
			success: function (e) {
				var _list = e.returnData,
					_size = e.returnSize,

					_data,
					_len,
					_rep,
					_left,
					_right;

				if (_td.tab == 2) {
					_len = _list.activityList.length;
					_list.activityList = imgCacheEntryPic(_list.activityList, 'seller_brand_tpl');
				} else {
					_data = _list.dataList;
					_len = _data.length;
					_rep = _list.repStatus;
					_left = { dataList: [] };
					_right = { dataList: [] };

					if (_rep == 0) {
						shield(_list.repMsg);
						return;
					}

					_data.dataList = imgCacheProductPic(_data.dataList, 'seller_product_tpl');

					for (var i = 0; i < _len; i++) {
						i % 2 ? _right.dataList.push(_data[i]) : _left.dataList.push(_data[i]);
					}
				}

				if (_scroll) {
					if (_len) {
						var _cur = seller_scroll.attr('data-page');
						_cur++;
						seller_scroll.attr('data-page', _cur);

						if (_td.tab == 2) {
							_td.con.append(template('seller_brand_tpl', _list));
						} else {
							_td.left.append(template(_td.tpl, _left));
							_td.right.append(template(_td.tpl, _right));
						}
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					var _sp3 = _td.con.find('.product-sp3'),
						_empty = _td.con.find('.page-empty');

					if (_empty.length) {
						_empty.remove();
						_sp3.show();
					}

					if (_len) {
						if (_td.tab == 2) {
							_td.con.html(template('seller_brand_tpl', _list));
						} else {
							_td.left.html(template(_td.tpl, _left));
							_td.right.html(template(_td.tpl, _right));
						}
					} else {
						_sp3.hide();
						_td.con.append(template('seller_empty_tpl'));
					}

					_size && (_td.pag = _size);

					_td.con.addClass('has-data');
					_td.con.attr('data-len', _len);
					_ts.initScroll(_ob);
				}

				_td.con.attr('data-page', seller_scroll.attr('data-page'));
				_td.con.attr('data-old', seller_scroll.attr('data-old'));
			},
			limit: 1
		});
	},

	shopmsg: function () {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getMchtShopInfo, {
			data: {
				mchtId: _td.mchtId
			},
			success: function(res) {
				var _list = res.returnData;
				_td.isCollectuonShop = _list.isCollectuonShop;

				if (!_td.info) {
					_td.info = 1;
					_list.shopLogo = _list.shopLogo ? imgCache(_list.shopLogo, 'seller_shop_logo') : '../static/images/seller.png';
					seller_topdd.html(template('seller_top_tpl', _list));
					_td.top = seller_nav.offset().top - seller_header_height;
					setTitle(_td.preview ? '店铺装修预览' : _list.shopName);

					seller_share.title = _list.shopName;
					seller_share.pictureUrl = _list.shopLogo;
					shareHand.update(seller_share);

					_td.xiaoNengId = _list.xiaoNengId;
					!_td.preview && _ts.shopLogo(_list.shopLogo);
				}

				if (_list.shopCouponList.length > 0) {
					_td.shopcouponlist = _list.shopCouponList.length;

					if (_td.tab == 0) {
						seller_coupon.removeClass('hide');
						seller_coupon.html(template('seller_msg_tpl', _list));
					}
				} else {
					seller_coupon.addClass('hide');
				}
				if(_list.manageSelf != 1){
					$(".popup-con").append('<a class="flex ac m-info" onclick="sellerApi.info()">\n' +
						'\t\t\t\t<span class="flex ac fg btb">店铺信息</span>\n' +
						'\t\t\t</a>')
				}

			}
		});
	},

	active: function () {
		var _ts = this,
			_td = _ts.data;

		seller_con.eq(_td.tab).removeClass('hide').siblings('.tab-con').addClass('hide');
		seller.find('.tab-' + _td.tab).addClass('active').siblings().removeClass('active');

		seller_scroll.off('scroll');
		seller.find('.ico-back-top').addClass('hide');
	},

	initScroll: function (_ob) {
		var _ts = this,
			_td = _ts.data,
			_con = seller_con.eq(_td.tab),
			_top = _con.attr('data-top'),
			_len = _con.attr('data-len');

		_top && seller_scroll.scrollTop(_top);
		_ob && seller_scroll.scrollTop(_td.top);

		_len == _td.pag && scrollHand.init(seller_scroll, function () {
			_ts.main(_td.tab, true, _ob);
		}, true);
	},

	focus: function () {
		var _ts = this,
			_td = _ts.data;

		seller.addClass('sp2');
		seller_filter.removeClass('hide');

		if (!_td.filter) {
			_td.filter = 1;

			new ajax(getApi.getShopProductScreeningConditions, {
				data: {
					mchtId: _td.mchtId
				},
				success: function (res) {
					seller_filter.find('.con-f').append(template('seller_filter_tpl', res.returnData));
				},
				error: function () {
					_td.filter = '';
				}
			});
		}
	},

	back: function () {
		var _ts = this,
			_td = _ts.data;

		if (!_td.tab || seller_filter.is(':visible')) {
			back(null, false, function () {
				seller.removeClass('sp2');
			});

			seller_search.blur();
		} else {
			_ts.main();
		}
	},

	share: function () {
		var _ts = this,
			_td = _ts.data;

		seller_share.title && shareHand.show();

		traffic.setAction(_td, {
			type: 6,
			id: _td.mchtId
		});
	},

	getFilter: function (ts, _str, _id, _name) {
		var _ob = {},
			_ts = this,
			_td = _ts.data;

		_str ? (typeof _str === 'object' ? (_ob = _str) : (_ob[_str] = _id)) : seller_search.val('');
		_name && (_ob.name = _name);

		seller_con.eq(1).removeClass('has-data').removeAttr('data-page').removeAttr('data-top');
		this.main(1, null, _ob);
		this.back();

		seller_filter.find('li').removeClass('active');
		ts && $(ts).addClass('active');
	},

	submit: function (ts) {
		defSelf();
		this.getFilter(null, JSON.parse(decodeURIComponent(formName($(ts).serialize()), true)));
	},

	scroll: function (ts) {
		if (seller_nav.offset().top - seller_header_height >= 1) {
			$(ts).removeClass('fixed');
			seller.removeClass('sp1');
		} else {
			$(ts).addClass('fixed');
			seller.addClass('sp1');
		}
	},

	server: function () {
		var _ts = this,
			_td = _ts.data;

		getUrlServer(_td.xiaoNengId);
	},

	info: function () {
		var _ts = this,
			_td = _ts.data;

		_td.mchtId && getUrl('seller/info/index.html?mchtId=' + _td.mchtId, 'self');
	},

	shopLogo: function (e) {
		seller_topdt.html('<img src="' + e + '">');

		addStylesheetRules([
			['.seller.sp1 .footer-tab a.ico-0:before',
				['background-image', 'url(' + e + ')']
			]
		]);
	},

	getRemind: function (_this) {
		var _ts = this,
			_td = _ts.data;

		getRemind(_this, true);

		!$(_this).hasClass('reminded') && traffic.setAction(_td, {
			type: 7,
			id: _td.mchtId
		});
	},
	
	// 领取优惠券
	getCoupon: function(_this, _e, _type, _can) {
		var _ts = this,
			_td = _ts.data;

		if (_type == 0) {
			if (_can == 1) {
				if (_td.isCollectuonShop) {
					new ajax(getApi.addReceiveCoupon, {
						data: {
							couponId: _e,
							memberId: member.id
						},
						success: function(res) {
							delayTip(res.returnData.content);
							$(_this).find('.footer').addClass('ff38a').empty().html('已领取');
							update_cart = 1;
						}
					});
				} else {
					popupShow('.seller-coupon');
					_td.check = _this;
					_td.couponid = _e;
				}
			} else {
				new ajax(getApi.addReceiveCoupon, {
					data: {
						couponId: _e,
						memberId: member.id
					},
					success: function(res) {
						delayTip(res.returnData.content);
						$(_this).find('.footer').addClass('ff38a').empty().html('已领取');
						update_cart = 1;
					}
				});
			}
		}
	},
	
	// 收藏保存 
	save: function() {
		var _ts = this,
			_td = _ts.data;

		new ajax(member.app ? getApi.addRemindSaleH5 : getApi.addRemindSale, {
			data: {
				memberId: member.id,
				type: 3,
				remindType: 3,
				remindId: _td.mchtId
			},
			success: function () {
				$(seller.find('.btn-collect')).text('已收藏');
					closeSelf('.seller-coupon');
				new ajax(getApi.addReceiveCoupon, {
					data: {
						couponId: _td.couponid,
						memberId: member.id
					},
					success: function(res) {
						delayTip(res.returnData.content);
						$(_td.check).find('.footer').empty().html('已领取');
					}
				});
			},
			limit: 1
		});
	}
};

getTpl(function () {
	sellerApi.init();
});

function onShow(b) {
	var _td = sellerApi.data;

	if (b && post_id === 'seller') {
		for (var i = 0; i < b.length; i++) {
			var _a = b[i].split('='),
				_b = _a[1];

			switch (_a[0]) {
				case 'mchtId': _td.mchtId = _b; break;
				case 'mchtCode': _td.mchtCode = _b; break;
				case 'preview': _td.preview = _b; break;
			}
		}

		seller = $('.page[data-id="seller"]:visible');
		seller_search = seller.find('#seller_search');
		seller_header_height = seller.find('header').height();
		seller_topdd = seller.find('.col-top dd');
		seller_topdt = seller.find('.col-top dt');
		seller_nav = seller.find('.col-nav');
		seller_filter = seller.find('.dialog-filter');
		seller_decorate = seller.find('.decorate');
		seller_scroll = seller.find('.tab-body');
		seller_con = seller.find('.content .tab-con');

		seller.removeClass('sp2');
		seller_search.blur();
	}

	_td.mchtId && traffic.setPv(_td);

	if (update_cart) {
		update_cart = 0;

		$('.page[data-id="cart"]:visible').length && getUrl('', 'self');
	}
}