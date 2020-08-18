var single_hot = $('.page[data-id="activity_templet_single_hot"]:visible'),
	single_hot_scroll = single_hot.find('.scroll-more'),
	single_hot_con = single_hot.find('.content'),
	single_hot_swiper = single_hot.find('.swiper-wrapper img'),

	single_hot_swiper_p = single_hot_swiper.siblings().find('p'),
	single_hot_filter = single_hot.find('.dialog-filter'),
	single_hot_filter_con = single_hot_filter.find('.filter-section'),

	single_hot_list = '',
	single_hot_ticket = '';

var single_hot_swiper_h = single_hot.find('.swiper-container').height();

var singleHot = {
	data: {
		size: 10,

		title: '', // 标题

		stock: '', // 有货
		sale: '', // 价格
		disc: '', // 折扣
		pru: '', // 商品品类
		gro: '', // 适用人群
		min: '', // 价格区间-最小
		max: '', // 价格区间-最大
		sex: '' // 性别
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'isPreview': _td.pre = _b; break;
					case 'memberId': member.id = _b; break;
					case 'activityId': _td.act = _b; break;
				}
			}
		}

		traffic.setPv(_td, {
			pageType: '27'
		});

		var _sf = JSON.parse(getStorage('singleFilter'));

		if ($('.wrapper').hasClass('app') && _sf) {
			_td.result = _sf.result;

			_td.pru = _sf.pru;
			_td.gro = _sf.gro;
			_td.min = _sf.min;
			_td.max = _sf.max;
			_td.sex = _sf.sex;

			_td.stock = _sf.stock;
			_td.sale = _sf.sale;
			_td.disc = _sf.disc;

			removeStorage('singleFilter');
		}

		// app历史回退时, 数据的兼容处理, 这里忽略历史已翻的页数, 忽略历史筛选的选中效果
		if (_td.result && _td.result.length) {
			var _fs = single_hot.find('.filter-succ-con');

			_fs.html(_td.result.join('、'));
			_fs.attr('title', _td.result.join('、'));
			_fs.find('.filter-succ').removeClass('hide');
			_fs.find('.tag-box .filter').addClass('active');
		}

		_td.stock && single_hot.find('.tag-box .stock').addClass('active');
		_td.sale && single_hot.find('.tag-box .sale').addClass('active').addClass(_td.sale.toLowerCase());
		_td.disc && single_hot.find('.tag-box .disc').addClass('active').addClass(_td.disc.toLowerCase());

		_ts.getPagingList('', {
			init: 1
		});
	},

	getPagingList: function (_that, _obj) {
		var _ts = this,
			_td = _ts.data;

		_obj = _obj || {};

		if (_that) {
			_that = $(_that);

			if (_that.hasClass('stock')) {
				// 有货
				if (_td.stock) {
					_td.stock = '';
					$(_that).removeClass('active');
				} else {
					_td.stock = 'active';
					$(_that).addClass('active');
				}

				_td.sale = '';
				_td.disc = '';
			} else if (_that.hasClass('sale')) {
				// 价格
				if (_td.sale == 'DESC') {
					_td.sale = 'ASC';
					_that.addClass('asc').removeClass('desc');
				} else {
					_td.sale = 'DESC';
					_that.addClass('desc').removeClass('asc');
				}

				_that.siblings('.disc').removeClass('asc').removeClass('desc');
				_td.disc = '';
			} else if (_that.hasClass('disc')) {
				// 折扣
				if (_td.disc == 'DESC') {
					_td.disc = 'ASC';
					_that.addClass('asc').removeClass('desc');
				} else {
					_td.disc = 'DESC';
					_that.addClass('desc').removeClass('asc');
				}

				_that.siblings('.sale').removeClass('asc').removeClass('desc');
				_td.sale = '';
			}
		}

		if (!_obj.scroll) {
			single_hot_scroll.removeAttr('data-page').removeAttr('data-old');
			single_hot_scroll.scrollTop() > single_hot_swiper_h && single_hot_scroll.scrollTop(single_hot_swiper_h);
		}

		new ajax(getApi.getActivityProductList, {
			data: {
				memberId: member.id,
				productTypeId: _td.pru,
				suitGroup: _td.gro || '',
				salePriceMin: _td.min || '',
				salePriceMax: _td.max || '',
				suitSex: _td.sex || '',
				stockMark: _td.stock,
				salePriceSort: _td.sale,
				discountSort: _td.disc,
				activityId: _td.act,

				currentPage: single_hot_scroll.attr('data-page') || 0,
				pageSize: _td.size
			},
			success: function (res) {
				var _size = res.returnSize,
					_list = res.returnData,
					_act = _list.activityAreaId,
					_title = _list.activityName;

				res.returnCode != '0000' && (_list = {
					productInfoList: []
				});

				var _prod = _list.productInfoList,
					_len = _prod.length;

				_list.productInfoList = imgCachePic(_prod, 'activity_single_hot_tpl');

				if (_obj.scroll) {
					if (_len) {
						var _cur = single_hot_scroll.attr('data-page');
						_list.page = _cur * _td.size;
						_cur++;
						single_hot_scroll.attr('data-page', _cur);
						single_hot_list.append(template('activity_single_hot_list_tpl', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_list.page = 0;

					if (_obj.init) {
						_title && setTitle(_title);
						_size && (_td.size = _size);
						_td.activityAreaId = _list.activityAreaId;

						single_hot_con.html(template('activity_single_hot_tpl', _list));

						single_hot_list = single_hot_con.find('.activity-single-con');

						// 海报图
						if (_list.posterPic) {
							_list.posterPic = imgCache(_list.posterPic, 'activity_single_hot_banner');
							single_hot_swiper.attr('src', _list.posterPic);
							single_hot_swiper_p.html(_list.benefitPoint);
						} else {
							single_hot_swiper.closest('div').hide();
						}
						
						// 优惠券
						new ajax(getApi.getActivityAreaCoupons, {
							data: {
								activityId: _td.act,
								activityAreaId: _list.activityAreaId
							},
							success: function (res1) {
								var _list1 = res1.returnData,
									_coupon = _list1.couponList || [];

								_coupon.length && (member.list = _coupon);
								single_hot_ticket = single_hot_con.find('.con-ticket');

								require(['ceiling'], function () {
									single_hot_ticket.html(template('activity_single_hot_coupon_tpl', _list1));
									single_hot.find('.tag').ceiling(single_hot_scroll, $('.page:visible header').is(':visible') ? $('.page:visible header').height() : 0);
								});
							}
						});

						// 倒计时
						var _time = single_hot_con.find('.count-time'),
							time_p = _time.siblings('p'),
							_span = _time.find('span'),

							_now = new Date(_list.currentTime),
							_end = new Date(_list.activityEndDate * 1e3),
							_begin = new Date(_list.activityBeginDate * 1e3),
							_ends = _end;

						if (_now.getTime() <= _begin.getTime()) {
							_ends = _begin;
							time_p.html('距离活动开始还剩');
						} else if (_now.getTime() <= _end.getTime()) {
							time_p.html('离活动结束仅剩');
						} else {
							_ends = false;
							time_p.html('活动已结束');
						}

						if (_ends) {
							require(['qpTime'], function () {
								$.qpTime({
									s: _now,
									e: _ends
								}, function (pt) {
									_span.eq(0).html(pt.d);
									_span.eq(1).html(pt.h);
									_span.eq(2).html(pt.m);
									_span.eq(3).html(pt.s);
								});
							});
						}

						// 足迹
						new ajax(getApi.addFootPrint, {
							data: {
								memberId: member.id,
								type: 3,
								productId: _td.act
							},
							disLogin: 1
						});

						shareHand.update({
							url: real_url,
							title: _list.activityName,
							content: _list.benefitPoint,
							pictureUrl: _list.posterPic
						});

						// 收藏
						$('.page:visible header .sc').html('<a class="' + (_list.isExist ? 'reminded ' : '') + 'remind-active flex fn ac jc" onclick="singleHot.collect(this)" data-type="1" data-id="' + _act + '">');

						// 预览
						_td.pre && single_hot_list.addClass('p-e-n');
					} else {
						single_hot_list.html(template('activity_single_hot_list_tpl', _list));
					}

					_len == _td.size && scrollHand.init(single_hot_scroll, function () {
						_ts.getPagingList(_that, {
							scroll: 1
						});
					}, true);
				}

				// 水印
				wkPicFix();

				traffic.delayRequest(function () {
					for (var i = 0; i < _prod.length; i++) {
						traffic.setDtl(_td, {
							type: 3,
							id: _prod[i].productId,
							pos: _list.page + i
						});
					}
				});
			},
			error: function (res) {
				if (res.returnCode == '9999' && !_that) {
					single_hot.removeClass('bgf4');
					single_hot_scroll.html(template('activity_single_hot_msg', res));
				}
			}
		});
	},

	collect: function (_this) {
		var _ts = this,
			_td = _ts.data;

		getRemind(_this);

		!$(_this).hasClass('reminded') && traffic.setAction(_td, {
			type: 1,
			id: _td.act
		});
	},

	filterDialog: function () {
		var _ts = this,
			_td = _ts.data;

		if (single_hot_filter_con.is(':empty')) {
			new ajax(getApi.getProductScreeningConditions, {
				data: {
					activityId: _td.act
				},
				success: function (res) {
					var _e = res.returnData,
						_c = '';

					for (var i = 0; i < _e.length; i++) {
						var _a = _e[i].productList,
							_t = _e[i].type,
							_b = '';

						if (_a) {
							for (var j = 0; j < _a.length; j++) {
								_b += '<li class="flex jc ac" data-val="' + _a[j].value + '" onclick="singleHot.filterActive(this)">' + _a[j].name + '</li>';
							}
							
							_c += '<div class="figure ' + _e[i].type + '"><h4 class="flex jb ac">' + _e[i].name + (_t != 'APP_PRODUCT_TYPE' ? '' : '<span class="flex ac" onclick="singleHot.filterToggle(this)">全部<i class="arrow-r ico"></i></span>') + '</h4><div><ul class="clearfix' + (_e[i].type == 'APP_PRODUCT_TYPE' ? ' multi' : '') + '">' + _b + '</ul></div></div>';
						}
					}

					single_hot_filter_con.html(_c);

					single_hot_scroll.addClass('hide');
					single_hot_filter.removeClass('hide');
				}
			});
		} else {
			single_hot_scroll.addClass('hide');
			single_hot_filter.removeClass('hide');
		}

		member.app && history.pushState(null, null, real_url);
		setStorage('singleFilter', JSON.stringify(_td));
	},

	filterActive: function (_this) {
		_this = $(_this);

		_this.toggleClass('active');

		if (!_this.parent().hasClass('multi')) {
			_this.siblings().removeClass('active');
		} else {
			var _multi = [];

			$.each(_this.parent().find('.active'), function () {
				_multi.push(_this.data('val'));
			});

			_multi = _multi.join();
			_this.parent().attr('data-multi', _multi);
		}
	},

	filterToggle: function (_this) {
		$(_this).toggleClass('on').closest('h4').siblings().stop().slideToggle();
	},

	filterConfirm: function (isClear) {
		var _ts = this,
			_td = _ts.data;

		defSelf();

		var _product = single_hot_filter_con.find('.APP_PRODUCT_TYPE .active').parent()[0],
			_range = new Array(2),
			_arr = single_hot_filter_con.find('.APP_PRICE_RANGE .active').data('val');

		_arr && (_range = _arr.split('-'));

		_td.pru = _product ? _product.getAttribute('data-multi') : '';
		_td.gro = single_hot_filter_con.find('.APP_SUIT_PEOPLE .active').data('val');
		_td.min = _range[0];
		_td.max = _range[1];
		_td.sex = single_hot_filter_con.find('.APP_SEX .active').data('val');

		_td.result = [];

		$.each(single_hot_filter_con.find('.active'), function () {
			_td.result.push($(this).html());
		});

		if (_td.result.length) {
			single_hot.find('.filter-succ-con').html(_td.result.join('、')).attr('title', _td.result.join('、')).parent().removeClass('hide');
			single_hot.find('.tag-box .filter').addClass('active');
		} else {
			single_hot.find('.filter-succ').addClass('hide');
			single_hot.find('.tag-box .filter').removeClass('active');
		}

		$('.page:visible .dialog').is(':visible') && back();
		!isClear && member.app && history.back();
		removeStorage('singleFilter');

		_ts.getPagingList();
	},

	filterReset: function () {
		single_hot_filter_con.find('li').removeClass('active');
	},

	filterClear: function () {
		this.filterReset();
		this.filterConfirm(1);
	}
};

getTpl(function () {
	singleHot.init();
});

function onShow() {
	traffic.setPv(singleHot.data);
}