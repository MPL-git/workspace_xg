// 限时秒杀
var nestDecorateType11 = {
	type: 11,
	tabList: '',
	conList: '',
	tabSelect: '',
	tabIndex: ''
};

var _vpage = $('.page:visible'),
	brandDecorate = {
		data: {

		}
	};

if (_vpage.hasClass('home')) {
	brandDecorate = homeApi;
} else if (_vpage.hasClass('seller')) {
	brandDecorate = sellerApi;
}

function postNestHome(infoid, fn) {
	if (!infoid) return;

	require(['template', 'qpTime'], function (template) {
		new ajax(getApi.getDecorateInfoPage, {
			data: {
				decorateInfoId: infoid,
				memberId: member.id
			},
			success: function (res) {
				var _list = res.returnData;

				if (!_list) return;
				if (_list.decorateAreaList.length) {
					var box_list = $('<div class="box-list"></div>');

					$.each(_list.decorateAreaList, function (i) {
						$.each(this.decorateModuleList, function () {
							if (this.moduleType == 1 || this.moduleType == 9) {
								this.decorateModulePic = imgCache(this.decorateModulePic, 'nest_home');
								var box_svg = $('<div class="box-svg w75"><img src="' + this.decorateModulePic + '"></div>');

								$.each(this.moduleMapList, function () {
									var _map = this.corrds.split(',');
									if(this.linkType == 5 && this.linkValue == 11){

										box_svg.append($('<a class="box-frame" onclick="getMapUrl(this)"'
											+ 'data-ad="' + this.adCatalog + '"'
											+ 'data-name="' + this.adCatalogName + '"'
											+ 'data-url="' + this.linkUrl + '"'
											+ 'data-type="' + this.linkType + '"'
											+ 'data-link="' + this.linkValue + '"'
											+ 'data-productid="' + this.linkValue + '"'
											+ 'data-activityid="' + this.linkValue + '"'
											+ 'data-activityid="' + this.linkValue + '"'
											+ 'data-msgtype="' + this.msgType + '"'
											+ 'style="left: ' + Math.min(_map[0], _map[2]) + '%; top: ' + Math.min(_map[1], _map[3]) + '%; width: ' + Math.abs(_map[2] - _map[0]) + '%; height: ' + Math.abs(_map[3] - _map[1]) + '%;background: ' + "rgba(0,0,0,0.5)" + ';"'
											+ '></a>'));
									}else{
										box_svg.append($('<a class="box-frame" onclick="getMapUrl(this)"'
											+ 'data-ad="' + this.adCatalog + '"'
											+ 'data-name="' + this.adCatalogName + '"'
											+ 'data-url="' + this.linkUrl + '"'
											+ 'data-type="' + this.linkType + '"'
											+ 'data-link="' + this.linkValue + '"'
											+ 'data-productid="' + this.linkValue + '"'
											+ 'data-activityid="' + this.linkValue + '"'
											+ 'data-activityid="' + this.linkValue + '"'
											+ 'data-msgtype="' + this.msgType + '"'
											+ 'style="left: ' + Math.min(_map[0], _map[2]) + '%; top: ' + Math.min(_map[1], _map[3]) + '%; width: ' + Math.abs(_map[2] - _map[0]) + '%; height: ' + Math.abs(_map[3] - _map[1]) + '%;"'
											+ '></a>'));
									}

								});

								if (this.moduleType == 9) {
									var _x = $('<div class="scroll-x"></div>');
									_x.html('<div class="scroll-xsc">' + box_svg.html() + '</div>');
									box_svg.html(_x).addClass('decorate-scrollx').append('<div class="scroll-xcon"><span></span></div>');
									(isAndroid || isiOS) && box_svg.addClass('decorate-scrollxs');
								}

								box_list.append(box_svg);
							}

							if (this.moduleType == 11) {
								if (this.secKillMap.productInfoList.length) {
									this.secKillMap.productInfoList = imgCacheProductPic(this.secKillMap.productInfoList, 'decorate_type11');
								}

								box_list.append(template('activity_decorate_rushbuy_seckill_tpl', this.secKillMap));

								var seckillList11 = this.secKillMap.seckillList;

								for (var i = 0; i < seckillList11.length; i++) {
									seckillList11[i].context === '抢购中' && (nestDecorateType11.tabIndex = i);
								}
							}
						});

						var page_list = $('.marketing');
						page_list.append(box_list);

						// 限时秒杀
						nestDecorateType11.tabList = page_list.find('.con-seckill-11 .tab-nav a'),
						nestDecorateType11.conList = page_list.find('.con-seckill-11 .product-scroll a');

						if (nestDecorateType11.tabList.length) {
							var _idx = nestDecorateType11.tabIndex || 0,
								_now = nestDecorateType11.tabList.eq(_idx),
								_next = nestDecorateType11.tabList.eq(_idx + 1),
								_beg = _next.data('beg'),
								_cur = _now.data('cur');

							if (nestDecorateType11.tabIndex === '') {
								_beg = _now.data('beg');
								nestDecorateType11.tabIndex = 0;
							}

							nestDecorateType11.tabIndex === 3 && (_beg = _now.data('end'));

							if (nestDecorateType11.tabIndex > 0) {
								seckillListNav11(nestDecorateType11.tabList.eq(nestDecorateType11.tabIndex));
							} else {
								if (nestDecorateType11.conList.length == 10) {
									nestDecorateType11.tabSelect = nestDecorateType11.tabList.eq(0);

									scrollHand.init(nestDecorateType11.tabSelect.closest('.con-box').find('.product-scroll').parent(), function () {
										seckillListNav11('', true);
									});
								}
							}

							if (_beg > _cur) {
								$.qpTime({
									s: _cur,
									e: _beg
								}, function (e) {
									if (!e.t) {
										setTimeout(function () {
											member.sys == 'android' ? refreshHomeWebview() : location.reload();
										}, 1e3);
									}
								});
							}
						}
					});

					fn(box_list);

					var _scrollx = box_list.find('.decorate-scrollx');

					if (_scrollx.length) {
						$.each(_scrollx.find('img'), function () {
							this.onload = function () {
								var _xsc = $(this).parent(),
									_scx = _xsc.parent(),
									_xcon = _scx.siblings('.scroll-xcon'),
									_span = _xcon.find('span'),
									w_img = $(this).width(),
									w_scx = _scrollx.width(),
									w_xcn = _xcon.width();

								if (w_img > w_scx) {
									_xsc.width(w_img);
									_span.width(w_xcn * w_scx / w_img);

									_scx.scroll(function (e) {
										_span.css('left', $(this).scrollLeft() * (w_xcn - _span.width()) / (w_img - w_scx));
									});
								} else {
									_xcon.addClass('hide');
								}
							}
						});
					}
				}
			}
		});
	});
}

// 限时秒杀
function seckillListNav11(_this, _scroll) {
	_this && (nestDecorateType11.tabSelect = $(_this));
	if (nestDecorateType11.tabSelect.hasClass('active') && !_scroll) {
		return;
	}

	nestDecorateType11.tabSelect.addClass('active').siblings().removeClass('active');

	var _con = nestDecorateType11.tabSelect.closest('.con-box').find('.product-scroll'),
		_scr = _con.parent();

	if (!_scroll) {
		_scr.removeAttr('data-page').removeAttr('data-old');
		_scr.scrollLeft(0);
	}

	require(['template'], function (template) {
		new ajax(getApi.getTopSecKillProductList, {
			data: {
				seckillType: 1,
				beginTime: nestDecorateType11.tabSelect.attr('data-beg'),
				currentPage: _scr.attr('data-page') || 0
			},
			success: function (res) {
				var _list = res.returnData,
					_len = _list.productInfoList.length;

				_list.productInfoList = imgCacheProductPic(_list.productInfoList, 'decorate_type6');

				if (_scroll) {
					if (_len) {
						var _cur = _scr.attr('data-page');
						_cur++;
						_scr.attr('data-page', _cur);

						_con.append(template('decorate_product_scroll', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_con.html(template('decorate_product_scroll', _list));

					if (_len == 10) {
						scrollHand.init(_scr, function () {
							seckillListNav11('', true);
						});
					}
				}
			}
		});
	});
}

function getRushbuy() {
	if (member.sys == 'android') {
		intentSeckill();
	} else if (member.sys == 'ios') {
		callIntentTrailer('intentTrailerListNative', 'intentSeckill');
	} else {
		getUrl('rushbuy/index.html?type=3', 'self');
	}
}


// 首页领取优惠券
var decorate_post_coupon = 1;
removeStorage('first_decorate_coupon');

function decorateCoupons(e) {
	decorateCoupon(function () {
		delayDecorateCoupons(e);
	});
}

function decorateCoupon(fn) {
	if (!decorate_post_coupon) return;
	decorate_post_coupon = 0;

	if (member.sys == 'ios' && !getStorage('first_decorate_coupon') && !member.id) {
		setTimeout(function () {
			setStorage('first_decorate_coupon', 1);
			fn();
		}, 300);
	} else {
		fn();
	}
}

function delayDecorateCoupons(_e) {
	new ajax(member.app ? getApi.addReceiveCouponH5 : getApi.addReceiveCoupon, {
		data: {
			couponId: _e.getAttribute('data-productid'),
			memberId: member.id
		},
		success: function (res) {
			alertTip(res.returnData.content);
		},
		error: function (res) {
			alertTip(res.returnMsg);
		},
		complete: function () {
			decorate_post_coupon = 1;
		},
		appLogin: function () {
			decorate_post_coupon = 1;
		}
	});
}