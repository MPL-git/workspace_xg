var member = {
	id: '',
	app: '',
	sys: 'web',
	mver: '',
	psys: 2,
	webver: $('#js_decorate').attr('src').split('?var=')[1],
	appver: 0,
	iosver: undefined
};

require.config({
	paths: {
		'template': '../../../../static/js/template-web-min', // 模板
	}
});

var getApi = {
	getTopSecKillProductList: '../../../../../api/n/getTopSecKillProductList',
	areaTempletByAreaId: '../../../../../api/n/getActivityAreaTemplateByAreaId',
	customerpage: '../../../../../api/n/getCustomerPage',
	brandDecorate: '../../../../../api/n/getHomePageDecorateInfo',
	getDecorationMeeting: '../../../../../api/n/getDecorationMeeting',
	getDecorateInfoPage: '../../../../../api/n/getDecorateInfoPage',
	getActivityList: '../../../../../api/n/getActivityList',
	getProductList: '../../../../../api/n/getProductList',
	addReceiveCoupon: '../../../../../api/y/addReceiveCoupon',
	activitySelection: '../../../../../api/n/activitySelection/detail'
};

var redirect_url = '?redirect_url=',
	location_href = location.href,
	page_urls = location_href.split('views')[0] + 'views/' + redirect_url,
	page_list = $('.decorate:visible .content');

var win_w = window.innerWidth,
	win_ls = window.sessionStorage,
	win_lc = window.localStorage;

var reg_cache_img = new RegExp('[^/]+(?!.*/)');

!win_lc && (win_lc = win_ls);

function getMemberId(mid, tok, web, sys) {
	mid && (member.id = mid);
	tok && (member.token = tok);
	sys && (member.sys = sys.toLowerCase());

	member.app = 1;
	member.sys == 'ios' && (member.psys = 1);
}

if (/sys=ios/g.test(location_href)) {
	member.sys = 'ios';
} else if (/sys=android/g.test(location_href)) {
	member.sys = 'android';
}

var _userAgent = navigator.userAgent,
	isxgbuy = /xgbuy/.test(_userAgent);

if (isxgbuy) {
	member.app = isxgbuy;

	var _xgbuy = _userAgent.split('xgbuy/')[1];

	if (/plat\//.test(_xgbuy)) {
		var _xgbuy1 = _xgbuy.split(' ');

		member.sys = _xgbuy1[3].split('plat/')[1];
		member.mver = _xgbuy1[1].split('sys/')[1];
		member.iosver = _xgbuy1[0];
		member.appver = parseInt(_xgbuy1[2].split('ser/')[1]);
	} else {
		var _xgbuy2 = _xgbuy.split('/');

		member.sys = _xgbuy2[0];
		member.mver = _xgbuy2[1];
		member.appver = _xgbuy2[2];
		member.iosver = _xgbuy2[3];

		if (member.iosver) {
			var _str = '';

			for (var i = 0; i < member.iosver.length; i++) {
				/\d/.test(member.iosver[i]) && (_str += member.iosver[i]);
			}
			member.appver = Number(_str);
		}

		member.sys == 'android' && (member.appver = parseInt(member.appver));
	}

	member.psys = member.sys == 'ios' ? 1 : 2;
}

var brand_decorate_api = {
	cur: 1,
	pag: 10,
	scroll: $('.scroll-more:visible'),
	api: getApi.brandDecorate,
	iosNative: 'intentTrailerListNative', // ios协议
	type: undefined, // 新人专享等
	types: undefined, // 兼容旧入口
	homePage: undefined, // ios只在首页采用loadUrl接口
	infoid: undefined, // 商城
	id:undefined,
	areaid: undefined, // 会展模块id
	pageid: undefined, // 自建模块id
	decorateInfoName:undefined //文章标题
};

var module_type = '',
	module_id = '',
	module_api = '';

var seckillTimer = null; // 秒杀计时器

// 会场秒杀
var decorateType6 = {
	type: 6,
	tabList: '', // tab列
	conList: '', // con列
	tabSelect: '', // 选中tab
	sysCur: 0, // 本地当前时间
	tabIndex: ''
};

// 限时秒杀
var decorateType11 = {
	type: 11,
	tabList: '',
	conList: '',
	tabSelect: '',
	tabIndex: ''
};

// 弹窗
function alertTip(_txt) {
	if ($('body').find('.alert-tip').length) {
		$('.alert-tip p').html(_txt);
		$('.alert-tip').show();
	} else {
		$('body').append('<div onclick="closeSelf(this)" class="popup show"><div class="popup-center popup-tip" onclick="stopSelf()"><div><h3 class="flex ac jc">提示</h3><p class="flex ac jc" style="font-size: .24rem; word-break: break-all;">' + _txt + '</p></div><a class="flex ac jc" onclick="closeSelf(this)">确认</a></div></div>');
	}
}

var delay_tip_timer = null;

function delayTip(_txt, _time, fn) {
	if (_txt == '') {
		return;
	}

	if (member.sys == 'android') {
		if (member.appver >= 53) {
			appDelayTip(_txt);
			return;
		}
	} else if (member.sys == 'ios') {
		if (member.appver >= 56) {
			if (brand_decorate_api.homePage) {
				loadURL('http://appDelayTip?title=' + _txt);
			} else {
				callIntentTrailer(brand_decorate_api.iosNative, 'appDelayTip', {'title': _txt});
			}
			return;
		}
	}

	var _tip = $('body').find('.delay-tip');

	if (_tip.length) {
		_tip.children().html(_txt);
	} else {
		$('body').append('<div class="delay-tip" style="height: auto; bottom: 5rem;"><div class="scroll-y">' + _txt + '</div></div>');
		_tip = $('body').find('.delay-tip');
	}

	_tip.addClass('show');
	clearTimeout(delay_tip_timer);

	delay_tip_timer = setTimeout(function () {
		_tip.removeClass('show');
		fn && fn();
	}, _time || 3e3);
}

// 关闭弹窗
function closeSelf(_this) {
	window.event.preventDefault();
	_this ? ($(_this).hasClass('popup') ? $(_this).hide() : $(_this).closest('.popup').hide()) : $('.page:visible .popup').hide();
}

function stopSelf() {
	window.event.stopPropagation();
}

var scrollHand = {
	post: 1,
	init: function (e, f, tp, f1) { // (scroll的jQ对象, 分页函数, 返回顶部, 滚动函数)
		var _ts = this,
			_horiz = false;

		_ts.post = 1;

		if (e.hasClass('scroll-x')) {
			_horiz = true;
		}

		if (!e.attr('data-page')) {
			e.attr('data-page', 1);
			e.attr('data-old', 0);

			if (_horiz) {
				_ts.horiz(e, f);
			} else {
				_ts.vertic(e, f);
			}
		}

		e.scroll(function () {
			if (_horiz) {
				var _l = this.scrollLeft;

				f1 && f1(_l);
				_ts.horiz(e, f);
			} else {
				var _t = this.scrollTop;
				
				tp && (_t > e.height() ? _ts.show(e) : _ts.hide(e));
				f1 && f1(_t);

				_ts.vertic(e, f);
			}
		});
	},

	// 垂直分页
	vertic: function (e, f) {
		if (!this.post) {
			return;
		}
		this.post = 0;

		var _s = e[0],
			_cur = Number(e.attr('data-page')),
			_old = Number(e.attr('data-old')),
			_st = _s.scrollTop,
			_sh = _s.offsetHeight,
			_ss = _s.scrollHeight,
			_eh = e.height() / 2;

		(_eh + _sh > _ss) && (_eh = (_ss - _sh) / 2);

		if (_old < _cur && _st + _sh + _eh >= _ss) {
			e.attr('data-old', _cur);
			f();
		} else {
			this.post = 1;
		}
	},

	// 水平分页
	horiz: function (e, f) {
		if (!this.post) {
			return;
		}
		this.post = 0;

		var _s = e[0],
			_cur = Number(e.attr('data-page')),
			_old = Number(e.attr('data-old')),
			_st = _s.scrollLeft,
			_sh = _s.offsetWidth,
			_ss = _s.scrollWidth,
			_eh = e.width() / 2;

		(_eh + _sh > _ss) && (_eh = (_ss - _sh) / 2);

		if (_old < _cur && _st + _sh + _eh >= _ss) {
			e.attr('data-old', _cur);
			f();
		} else {
			this.post = 1;
		}
	},

	back: function (e) { // (scroll的js对象)
		$(e).siblings('.scroll-y').scrollTop(0);
	},
	show: function (e) { // 加入或显示"返回顶部" (scroll的jQ对象)
		var _e = e.parent().find('.ico-back-top');
		_e.length ? _e.removeClass('hide') : e.parent().append('<div class="ico-back-top" onclick="scrollHand.back(this)"></div>');
	},
	hide: function (e) { // 隐藏返回顶部 (scroll的jQ对象)
		e.parent().find('.ico-back-top').addClass('hide');
	}
};

// 验证app版本是否符合接口
function checkAppVer(e, f, h) {
	var _e = member.appver;

	if (member.sys == 'ios' && /\./g.test(e)) {
		if (member.iosver) {
			f();
			return;
		}

		e = parseInt(e.replace(/\./g,''));
		_e = parseInt(_e.replace(/\./g,''));
	}

	if (_e >= e) {
		f();
	} else if (typeof h == 'function') {
		h();
	} else if (!h) {
		delayTip('请下载最新版本！');
	}
}

$(function () {
	var _search1 = location_href.split('?')[1];

	if (_search1) {
		var _searchs = _search1.split('&');

		for (var i = 0; i < _searchs.length; i++) {
			var _search = _searchs[i].split('='),
				_search_1 = parseInt(_search[1]);

			_search_1.toString() == 'NaN' && (_search_1 = _search[1]);

			switch (_search[0]) {
				case 'activityAreaId': brand_decorate_api.areaid = _search_1; break;
				case 'infoId': brand_decorate_api.infoid = _search_1; break;
				case 'id': brand_decorate_api.id = _search_1; break;
				case 'pageid': brand_decorate_api.pageid = _search_1; break;
				case 'type': brand_decorate_api.type = _search_1; break;
				case 'types': brand_decorate_api.types = _search_1; break;
				case 'homePage': brand_decorate_api.homePage = _search_1; break;
				case 'sys': member.sys = _search_1; break;
			}
			
		}
	}

	brand_decorate_api.type == 2 && (brand_decorate_api.iosNative = 'newExclusiveListNative');
	member.sys == 'ios' && page_list.addClass('ios');

	if (/iphone/gi.test(navigator.userAgent) && (screen.height == 812 && screen.width == 375)) {
		page_list.addClass('iosx');
	}

	postApi(getApi.activitySelection, {id: brand_decorate_api.id},function(e){
		if(JSON.parse(e).returnCode=='0000'){
			$(".decorateInfoName").text(JSON.parse(e).returnData.activitySelection.title)
			brand_decorate_api.infoid = JSON.parse(e).returnData.activitySelection.decorateInfoId
		}
		brand_decorate_api.infoid && (brand_decorate_api.api = getApi.getDecorateInfoPage);
	brand_decorate_api.areaid && (brand_decorate_api.api = getApi.getDecorationMeeting);
	brand_decorate_api.pageid && (brand_decorate_api.api = getApi.customerpage);
		require(['template'], function (template) {
			postApi(brand_decorate_api.api, {
				type: brand_decorate_api.type,
				decorateInfoId: brand_decorate_api.infoid,
				activityAreaId: brand_decorate_api.areaid,
				customPageId: brand_decorate_api.pageid
			}, function (e) {
				var _list = e.returnData;
	
				if (!_list) {
					return;
				}
				
				if (_list && _list.decorateAreaList && _list.decorateAreaList.length) {
					var box_list = $('<div class="box-list"></div>');
	
					$.each(_list.decorateAreaList, function (i) {
						$.each(this.decorateModuleList, function () {
							if ((this.moduleType == 1 || this.moduleType == 9) && this.decorateModulePic) {
								// 热点模块
								this.decorateModulePic = imgCacheAppDecorate(this.decorateModulePic, 'app_decorate');
								var box_svg = $('<div class="box-svg swiper-slide"><img src="' + this.decorateModulePic + '" onerror="imgErrorEmpty(this)"></div>');
	
								$.each(this.moduleMapList, function () {
									var _map = this.corrds.split(',');
									box_svg.append($('<a class="box-frame" onclick="getMapUrl(this)"'
										+ 'data-ad="' + this.adCatalog + '"'
										+ 'data-name="' + this.adCatalogName + '"'
										+ 'data-url="' + this.linkUrl + '"'
										+ 'data-type="' + this.linkType + '"'
										+ 'data-link="' + this.linkValue + '"'
										+ 'data-productid="' + this.linkValue + '"'
										+ 'data-activityid="' + this.linkValue + '"'
										+ 'data-activityareaid=""'
										+ 'data-msgtype="' + this.msgType + '"'
										+ 'style="left: ' + Math.min(_map[0], _map[2]) + '%; top: ' + Math.min(_map[1], _map[3]) + '%; width: ' + Math.abs(_map[2] - _map[0]) + '%; height: ' + Math.abs(_map[3] - _map[1]) + '%;"'
										+ '></a>'));
								});
	
								if (this.moduleType == 9) {
									var _x = $('<div class="scroll-x"></div>');
									_x.html('<div class="scroll-xsc">' + box_svg.html() + '</div>');
									box_svg.html(_x).addClass('decorate-scrollx').append('<div class="scroll-xcon"><span></span></div>');
									member.sys == 'ios' && box_svg.addClass('decorate-scrollxs');
								}
	
								box_list.append(box_svg);
							} else if (this.moduleType == 2) {
								// 商品模块
								this.conmonProduct = this.productBlockList;
								this.conmonProduct = imgCachePic(this.conmonProduct, 'activity_decorate_product_tpl');
								box_list.append(template('activity_decorate_product_tpl', this));
							} else if (this.moduleType == 3) {
								// 特卖模块
								this.conmonBrand = this.activityBlockList;
								this.conmonBrand = imgCacheConmonBrandAreaEntryPic(this.conmonBrand, 'activity_decorate_brand_tpl');
								box_list.append(template('activity_decorate_brand_tpl', this));
							} else if (this.moduleType == 4) {
								// 商品列表
								this.conmonProduct = this.productList;
								this.conmonProduct = imgCachePic(this.conmonProduct, 'activity_decorate_product_tpl');
								brand_decorate_api.len = this.productList.length;
								box_list.append(template('activity_decorate_product_tpl', this));
							} else if (this.moduleType == 5) {
								// 特卖列表
								this.conmonBrand = this.activityList;
								this.conmonBrand = imgCacheConmonBrandAreaEntryPic(this.conmonBrand, 'activity_decorate_brand_tpl');
								brand_decorate_api.len = this.activityList.length;
								box_list.append(template('activity_decorate_brand_tpl', this));
							} else if (this.moduleType == 6) {
								// 会场秒杀
								if (this.secKillMap.productInfoList.length) {
									this.secKillMap.productInfoList = imgCacheProductPic(this.secKillMap.productInfoList, 'decorate_type6');
								}
	
								box_list.append(template('activity_decorate_seckill_tpl', this.secKillMap));
								decorateType6.sysCur = (new Date).getTime();
	
								var seckillList6 = this.secKillMap.seckillList;
	
								for (var i = 0; i < seckillList6.length; i++) {
									seckillList6[i].context === '抢购中' && (decorateType6.tabIndex = i);
								}
							} else if (this.moduleType == 11) {
								// 限时秒杀
								if (this.secKillMap.productInfoList.length) {
									this.secKillMap.productInfoList = imgCacheProductPic(this.secKillMap.productInfoList, 'decorate_type11');
								}
	
								box_list.append(template('activity_decorate_rushbuy_seckill_tpl', this.secKillMap));
	
								var seckillList11 = this.secKillMap.seckillList;
	
								for (var i = 0; i < seckillList11.length; i++) {
									seckillList11[i].context === '抢购中' && (decorateType11.tabIndex = i);
								}
							} else if (this.moduleType == 14){
								// 图文
								box_list.append(template('graphic_text_tpl', this));
							}
	
							// 加载更多
							if (this.moduleType == 4 || this.moduleType == 5) {
								module_type = this.moduleType;
								module_id = this.decorateModuleId;
							}
						});
					});
	
					page_list.append(box_list);
	
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
	
							this.parentNode.addEventListener('touchstart', function(e) {
								e.stopPropagation();
								needSlidingAround('1');
							});
						});
					}
	
					// 会场秒杀
					decorateType6.tabList = page_list.find('.con-seckill-6 .tab-nav a');
					decorateType6.conList = page_list.find('.con-seckill-6 .product-scroll a');
	
					if (decorateType6.tabList.length) {
						seckillListNav6(decorateType6.tabList.eq(decorateType6.tabIndex));
	
						/*if (decorateType6.conList.length == 10) {
							decorateType6.tabSelect = decorateType6.tabList.eq(0);
	
							scrollHand.init(decorateType6.tabSelect.closest('.con-box').find('.product-scroll').parent(), function () {
								seckillListNav6('', true);
							});
						}*/
	
						document.querySelector('.con-seckill').addEventListener('touchstart', function(e) {
							e.stopPropagation();
							needSlidingAround('1');
						});
					}
	
					// 限时秒杀
					decorateType11.tabList = page_list.find('.con-seckill-11 .tab-nav a');
					decorateType11.conList = page_list.find('.con-seckill-11 .product-scroll a');
	
					if (decorateType11.tabList.length) {
						var _idx = decorateType11.tabIndex || 0,
							_now = decorateType11.tabList.eq(_idx),
							_next = decorateType11.tabList.eq(_idx + 1),
							_beg = _next.data('beg'),
							_cur = _now.data('cur');
	
						if (decorateType11.tabIndex === '') {
							_beg = _now.data('beg');
							decorateType11.tabIndex = 0;
						}
	
						decorateType11.tabIndex === 3 && (_beg = _now.data('end'));
	
						seckillListNav11(decorateType11.tabList.eq(decorateType11.tabIndex));
	
						/*if (decorateType11.tabIndex > 0) {
							seckillListNav11(decorateType11.tabList.eq(decorateType11.tabIndex));
						} else if (decorateType11.conList.length == 10) {
								decorateType11.tabSelect = decorateType11.tabList.eq(0);
	
								scrollHand.init(decorateType11.tabSelect.closest('.con-box').find('.product-scroll').parent(), function () {
									seckillListNav11('', true);
								});
							}
						}*/
	
						_beg > _cur && $.qpTime({
							s: _cur,
							e: _beg
						}, function (pt) {
							!pt.t && setTimeout(function () {
								if (member.sys == 'android') {
									refreshHomeWebview();
								} else {
									location.reload();
								}
							}, 1e3);
						});
	
						document.querySelector('.con-seckill').addEventListener('touchstart', function(e) {
							e.stopPropagation();
							needSlidingAround('1');
						});
					}
	
					// 嵌套页返回高度
					setTimeout(function () {
						var _img = $('.content img');
						_img.length && catalogImgLoad(_img);
					}, 30);
	
					page_list[0].addEventListener('touchstart', function() {
						needSlidingAround('2');
					});
				} else if (member.sys == 'ios') {
					if (brand_decorate_api.homePage) {
						loadURL('http://getWebContentHeight?contentSize=0');
					} else {
						callIntentTrailer((brand_decorate_api.type == 2 || brand_decorate_api.types == 2) ? 'newExclusiveListNative' : brand_decorate_api.iosNative, 'getWebContentHeight', {'contentSize': 0});
					}
					// checkAppVer(54, function () {
					// 	loadURL('http://getWebContentHeight?contentSize=0');
					// }, function () {
					// 	callIntentTrailer((brand_decorate_api.type == 2 || brand_decorate_api.types == 2) ? 'newExclusiveListNative' : brand_decorate_api.iosNative, 'getWebContentHeight', {'contentSize': 0});
					// });
				} else if (member.sys == 'android') {
					hideH5(e.returnMsg);
				}
			}, function (_e) {
				if (member.sys == 'android') {
					hideH5(_e);
				}
			});
		});
	})
	
});

// 监测APP分页
var app_scroll_allow = true,
	app_scroll_top = 0;

var box_height = 0;

function getPageBottom(_top) {
	if (!app_scroll_allow || app_scroll_top == _top || !module_type) {
		return
	}

	app_scroll_top = _top;
	app_scroll_allow = false;

	if (module_type == 4) {
		module_api = getApi.getProductList;
	} else if (module_type == 5) {
		module_api = getApi.getActivityList;
	}

	postApi(module_api, {
		decorateModuleId: module_id,
		currentPage: brand_decorate_api.cur,
		pageSize: brand_decorate_api.pag
	}, function (e) {
		var _tpl = '',
			_data = {},
			_list = e.returnData,
			_box = brand_decorate_api.scroll.find('.box-list');

		if (module_type == 4) {
			_tpl = 'activity_decorate_product_tpl';
			_data.conmonProduct = _list;
			_data.conmonProduct = imgCachePic(_data.conmonProduct, 'activity_decorate_product_tpl');
		} else if (module_type == 5) {
			_tpl = 'activity_decorate_brand_tpl';
			_data.conmonBrand = _list;
			_data.conmonBrand = imgCacheConmonBrandAreaEntryPic(_data.conmonBrand, 'activity_decorate_brand_tpl');
		}

		if (_list.length) {
			require(['template'], function (template) {
				!box_height && (box_height = _box.height());
				_box.append(template(_tpl, _data));

				brand_decorate_api.cur++;
				app_scroll_allow = true;

				_list.length < brand_decorate_api.pag && delayTip('已经全部加载完毕');

				// member.sys == 'ios' && checkAppVer(54, function () {
				// 	loadURL('http://getWebContentHeight?contentSize=' + _box.height());
				// }, function () {
				// 	callIntentTrailer(brand_decorate_api.iosNative, 'getWebContentHeight', {'contentSize': _box.height()});
				// });

				if (member.sys == 'ios') {
					var _hhh = 0;

					if (module_type == 4) {
						_hhh = win_w * 522 / 750;
						box_height += _hhh * Math.ceil(_list.length / 2);
					} else if (module_type == 5) {
						_hhh = win_w * 509 / 750;
						box_height += _hhh * _list.length;
					}

					if (brand_decorate_api.homePage) {
						loadURL('http://getWebContentHeight?contentSize=' + box_height);
					} else {
						callIntentTrailer(brand_decorate_api.iosNative, 'getWebContentHeight', {'contentSize': box_height});
					}
				}
			});
		}
	});
}

function getPageBottomBack() {
	if (member.sys == 'ios') {
		intentpageTop();
	} else if (member.sys == 'ios') {
		// checkAppVer(54, function () {
		// 	loadURL('http://intentpageTop');
		// }, function () {
		// 	callIntentTrailer(brand_decorate_api.iosNative, 'intentpageTop');
		// });
		if (brand_decorate_api.homePage) {
			loadURL('http://intentpageTop');
		} else {
			callIntentTrailer(brand_decorate_api.iosNative, 'intentpageTop');
		}
	}

	brand_decorate_api.scroll.find('.ico-back-top').addClass('hide');
}

// 返回顶部
function intentpageTop() {
	window.H5PluginManager.intentpageTop();
}

// 装修会场高度(安卓)
function getWebviewHeight(a) {
	window.H5PluginManager.getWebviewHeight(a);
}

function catalogImgLoad(_img) {
	var _h = true;

	$.each(_img, function () {
		!this.height && (_h = false);
	});

	if (!_h) {
		requestAnimationFrame(function () {
			catalogImgLoad(_img);
		});
	} else {
		var _hh = Math.ceil(page_list.find('.box-list').height());

		if (member.sys == 'android') {
			getWebviewHeight(_hh);
		} else if (member.sys == 'ios') {
			// checkAppVer(54, function () {
			// 	loadURL('http://getWebContentHeight?contentSize=' + _hh);
			// }, function () {
			// 	callIntentTrailer((brand_decorate_api.type == 2 || brand_decorate_api.types == 2) ? 'newExclusiveListNative' : brand_decorate_api.iosNative, 'getWebContentHeight', {'contentSize': _hh});
			// });

			if (brand_decorate_api.homePage) {
				loadURL('http://getWebContentHeight?contentSize=' + _hh);
			} else {
				callIntentTrailer((brand_decorate_api.type == 2 || brand_decorate_api.types == 2) ? 'newExclusiveListNative' : brand_decorate_api.iosNative, 'getWebContentHeight', {'contentSize': _hh});
			}
		}
	}
}

// ios移除禁止点击标记
function removeIosPostMark() {
	$('.ios-post-mark').removeClass('ios-post-mark');
}

// 跳转
function getMapUrl(_this) {
	var _type = $(_this).attr('data-type'),
		_link = $(_this).attr('data-link'),
		_ad = $(_this).attr('data-ad'),
		_name = $(_this).attr('data-name'),
		_linkUrl = $(_this).attr('data-url'),
		_productid = $(_this).attr('data-productid'),

		_new = _type + '_' + _link;

	// if ($(this).hasClass('ios-post-mark')) {
	// 	return;
	// }

	// member.sys == 'ios' && checkAppVer(55, function () {
	// 	$(this).addClass('ios-post-mark');
	// });

	if (_type == 1) {
		// 会场
		$.ajax({
			method: 'post',
			// url: getApi.areaTempletByAreaId + '?activityAreaId=' + _link,
			url: getApi.areaTempletByAreaId,
			data: JSON.stringify({
				reqData: {	
					activityAreaId: _link,
					memberId: member.id
				},
				system: member.psys,
				version: member.appver,
				token: member.token,
				timeStamp: Date.now(),
				nonceStr: "OV5QOVX21A0446K8",
				signature: "7f8c4ad7f6d349cf28879670b9fe79b9"
			}),
			success: function (e) {
				var _url = e.returnData.areaUrl;

				if (!_url) {
					getUrl('activity/templet/brand_protection.html', 'self', null, false);
					return;
				}

				if (member.sys == 'ios') {
					checkAppVer(54, function () {
						loadURL('http://JumpWebView?url=' + _url);
					}, function () {
						callIntentTrailer(brand_decorate_api.iosNative, 'JumpWebView', {'url': _url});
					});
					if (brand_decorate_api.homePage) {
						loadURL('http://JumpWebView?url=' + _url);
					} else {
						callIntentTrailer(brand_decorate_api.iosNative, 'JumpWebView', {'url': _url});
					}
				} else {
					// /!http|xgbuy/g.test(_url) && (_url += (/\?/g.test(_url) ? '&' : '?') + 'new' + _new);
					getUrl(_url, 'self', null, false, _name);
				}
			}
		});
	} else if (_type == 2) {
		// 特卖 || 活动
		getUrlActiveSingle1(_this);
	} else if (_type == 3) {
		// 商品
		getUrlGoods(_this);
	} else if (_type == 4) {
		if (member.sys == 'ios') {
			// 自建页面
			// checkAppVer(54, function () {
			// 	loadURL('http://JumpWebView?url=' + location_href.split('/views/')[0] + '/views/ios.html' + redirect_url + 'activity/templet/brand_decorate.html?pageid=' + _link);
			// }, function () {
			// 	callIntentTrailer(brand_decorate_api.iosNative, 'JumpWebView', {'url': location_href.split('/views/')[0] + '/views/ios.html' + redirect_url + 'activity/templet/brand_decorate.html?pageid=' + _link});
			// });
			if (brand_decorate_api.homePage) {
				loadURL('http://JumpWebView?url=' + location_href.split('/views/')[0] + '/views/ios.html' + redirect_url + 'activity/templet/brand_decorate.html?pageid=' + _link);
			} else {
				callIntentTrailer(brand_decorate_api.iosNative, 'JumpWebView', {'url': location_href.split('/views/')[0] + '/views/ios.html' + redirect_url + 'activity/templet/brand_decorate.html?pageid=' + _link});
			}
		} else {
			getUrl('activity/templet/brand_decorate.html?pageid=' + _link + '&new=' + _new);
		}
	} else if (_type == 5) {
		// 栏目
		if (_link == 1) {
			// 新用户专享
			if (member.sys == 'android') {
				intentNewUserShare();
			} else if (member.sys == 'ios') {
				// checkAppVer(54, function () {
				// 	loadURL('http://intentNewUserShare');
				// }, function () {
				// 	callIntentTrailer(brand_decorate_api.iosNative, 'intentNewUserShare');
				// });
				if (brand_decorate_api.homePage) {
					loadURL('http://intentNewUserShare');
				} else {
					callIntentTrailer(brand_decorate_api.iosNative, 'intentNewUserShare');
				}
			} else {
				getUrl('newman/index.html.html?type=1');
			}
		} else if (_link == 2) {
			// 单品爆款
			if (member.sys == 'android') {
				intentSingleExplo();
			} else if (member.sys == 'ios') {
				// checkAppVer(54, function () {
				// 	loadURL('http://intentSingleExplo');
				// }, function () {
				// 	callIntentTrailer(brand_decorate_api.iosNative, 'intentSingleExplo');
				// });
				if (brand_decorate_api.homePage) {
					loadURL('http://intentSingleExplo');
				} else {
					callIntentTrailer(brand_decorate_api.iosNative, 'intentSingleExplo');
				}
			} else {
				getUrl('activity/templet/single_explosion.html?type=2');
			}
		} else if (_link == 3) {
			// 限时抢购
			if (member.sys == 'android') {
				intentSeckill();
			} else if (member.sys == 'ios') {
				// checkAppVer(54, function () {
				// 	loadURL('http://intentSeckill');
				// }, function () {
				// 	callIntentTrailer(brand_decorate_api.iosNative, 'intentSeckill');
				// });
				if (brand_decorate_api.homePage) {
					loadURL('http://intentSeckill');
				} else {
					callIntentTrailer(brand_decorate_api.iosNative, 'intentSeckill');
				}
			} else {
				getUrl('rushbuy/index.html?type=3');
			}
		} else if (_link == 4) {
			// 新用户秒杀
			if (member.sys == 'android') {
				intentNewUserSeckill();
			} else if (member.sys == 'ios') {
				// checkAppVer(54, function () {
				// 	loadURL('http://intentNewUserSeckill');
				// }, function () {
				// 	callIntentTrailer(brand_decorate_api.iosNative, 'intentNewUserSeckill');
				// });
				if (brand_decorate_api.homePage) {
					loadURL('http://intentNewUserSeckill');
				} else {
					callIntentTrailer(brand_decorate_api.iosNative, 'intentNewUserSeckill');
				}
			} else {
				getUrl('newman/seckill/index.html');
			}
		} else if (_link == 5) {
			// 积分商城
			if (member.sys == 'android') {
				intentIntegralMall();
			} else if (member.sys == 'ios') {
				// checkAppVer(54, function () {
				// 	loadURL('http://intentIntegralMall');
				// }, function () {
				// 	callIntentTrailer(brand_decorate_api.iosNative, 'intentIntegralMall');
				// });
				if (brand_decorate_api.homePage) {
					loadURL('http://intentIntegralMall');
				} else {
					callIntentTrailer(brand_decorate_api.iosNative, 'intentIntegralMall');
				}
			} else {
				getUrl('integral/detail/index.html');
			}
		} else if (_link == 6) {
			// 断码清仓
			if (member.sys == 'android') {
				intentFaultCode2();
			} else if (member.sys == 'ios') {
				// checkAppVer(54, function () {
				// 	loadURL('http://intentFaultCode2');
				// }, function () {
				// 	callIntentTrailer(brand_decorate_api.iosNative, 'intentFaultCode2');
				// });
				if (brand_decorate_api.homePage) {
					loadURL('http://intentFaultCode2');
				} else {
					callIntentTrailer(brand_decorate_api.iosNative, 'intentFaultCode2');
				}
			} else {
				getUrl('activity/templet/single_duanma');
			}
		} else if (_link == 7) {
			// 签到
			var _url1 = location_href.split('/views/')[0] + '/views/' + redirect_url + 'activity/newsign/index.html';

			if (member.sys == 'ios') {
				// checkAppVer(54, function () {
				// 	loadURL('http://JumpWebView?url=' + _url1);
				// }, function () {
				// 	callIntentTrailer(brand_decorate_api.iosNative, 'JumpWebView', {'url': _url1});
				// });
				if (brand_decorate_api.homePage) {
					loadURL('http://JumpWebView?url=' + _url1);
				} else {
					callIntentTrailer(brand_decorate_api.iosNative, 'JumpWebView', {'url': _url1});
				}
			} else {
				getUrl(_url1);
			}
		} else if (_link == 8) {
			// 砍价
			var _url1 = location_href.split('/views/')[0] + '/views/' + redirect_url + 'activity/cutprice/index.html';

			if (member.sys == 'ios') {
				// checkAppVer(54, function () {
				// 	loadURL('http://JumpWebView?url=' + _url1);
				// }, function () {
				// 	callIntentTrailer(brand_decorate_api.iosNative, 'JumpWebView', {'url': _url1});
				// });
				if (brand_decorate_api.homePage) {
					loadURL('http://JumpWebView?url=' + _url1);
				} else {
					callIntentTrailer(brand_decorate_api.iosNative, 'JumpWebView', {'url': _url1});
				}
			} else {
				getUrl(_url1, 'self');
			}
		} else if (_link == 9) {
			// 免单
			var _url1 = location_href.split('/views/')[0] + '/views/' + redirect_url + 'activity/freeprice/index.html';

			if (member.sys == 'ios') {
				// checkAppVer(54, function () {
				// 	loadURL('http://JumpWebView?url=' + _url1);
				// }, function () {
				// 	callIntentTrailer(brand_decorate_api.iosNative, 'JumpWebView', {'url': _url1});
				// });
				if (brand_decorate_api.homePage) {
					loadURL('http://JumpWebView?url=' + _url1);
				} else {
					callIntentTrailer(brand_decorate_api.iosNative, 'JumpWebView', {'url': _url1});
				}
			} else {
				getUrl(_url1, 'self');
			}
		} else if (_link == 10) {
			// 有好货
			if (member.sys == 'android') {
				checkAppVer(53, function () {
					haveGoods();
				});
			} else if (member.sys == 'ios') {
				checkAppVer(56, function () {
					if (brand_decorate_api.homePage) {
						loadURL('http://haveGoods');
					} else {
						callIntentTrailer(brand_decorate_api.iosNative, 'haveGoods');
					}
				});
			}
		} else if (_link == 11) {
			// 每日好店
			if (member.sys == 'android') {
				checkAppVer(53, function () {
					everydayShop();
				});
			} else if (member.sys == 'ios') {
				checkAppVer(56, function () {
					if (brand_decorate_api.homePage) {
						loadURL('http://everydayShop');
					} else {
						callIntentTrailer(brand_decorate_api.iosNative, 'everydayShop');
					}
				});
			}
		} else if (_link == 12) {
			// 品牌团
			if (member.sys == 'android') {
				checkAppVer(53, function () {
					brandGroup();
				});
			} else if (member.sys == 'ios') {
				checkAppVer(56, function () {
					if (brand_decorate_api.homePage) {
						loadURL('http://brandGroup');
					} else {
						callIntentTrailer(brand_decorate_api.iosNative, 'brandGroup');
					}
				});
			}
		} else if (_link == 13) {
			// 新星计划
			if (member.sys == 'android') {
				checkAppVer(55, function () {
					window.H5PluginManager.novaPlanHome();
				});
			} else if (member.sys == 'ios') {
				checkAppVer(57, function () {
					if (brand_decorate_api.homePage) {
						loadURL('http://novaPlanHome');
					} else {
						callIntentTrailer(brand_decorate_api.iosNative, 'novaPlanHome');
					}
				});
			}
		} else if (_link == 14) {
			// 淘宝优选
			if (member.sys == 'android') {
				checkAppVer(55, function () {
					taobaoyouxuan();
				});
			} else if (member.sys == 'ios') {
				checkAppVer(57, function () {
					if (brand_decorate_api.homePage) {
						loadURL('http://taobaoyouxuan');
					} else {
						callIntentTrailer(brand_decorate_api.iosNative, 'taobaoyouxuan');
					}
				});
			}
		} else if (_link == 15 || _link == 16 || _link == 18 || _link == 19 || _link == 20) {
			// 潮流上新 / 潮流男装 / 运动鞋服 / 潮流美妆 / 食品超市
			if (member.sys == 'android') {
				checkAppVer(58, function () {
					chaoLiuColumn(_link);
				});
			} else if (member.sys == 'ios') {
				checkAppVer(59, function () {
					if (brand_decorate_api.homePage) {
						loadURL('http://chaoLiuColumn?type=' + _link);
					} else {
						callIntentTrailer(brand_decorate_api.iosNative, 'chaoLiuColumn', {'type': _link});
					}
				});
			}
		} else if (_link == 17) {
			// 断码特惠
			if (member.sys == 'android') {
				checkAppVer(58, function () {
					chaoLiuDuanma(_link);
				});
			} else if (member.sys == 'ios') {
				checkAppVer(59, function () {
					if (brand_decorate_api.homePage) {
						loadURL('http://chaoLiuDuanma?type=' + _link);
					} else {
						callIntentTrailer(brand_decorate_api.iosNative, 'chaoLiuDuanma', {'type': _link});
					}
				});
			}
		} else if (_link == 22) {
			if (member.sys == 'android') {
				checkAppVer(53, function () {
					intentSvip();
				});
			} else if (member.sys == 'ios') {
				checkAppVer(58, function () {
					if (brand_decorate_api.homePage) {
						loadURL('http://intentSvip');
					} else {
						callIntentTrailer('intentTrailerNative', 'intentSvip');
					}
				});
			} else {
				console.log('H5的svip首页暂未开发');
			}
		}
	} else if (_type == 6) {
		// 旧品牌特卖一级类目
		// if (_ad) {
			if (member.sys == 'android') {
				intentCatalog(_name, _ad, _link);
			} else if (member.sys == 'ios') {
				// checkAppVer(54, function () {
				// 	loadURL('http://intentCatalog?id=' + _link + '&ad=' + _ad + '&name=' + _name);
				// }, function () {
				// 	callIntentTrailer(brand_decorate_api.iosNative, 'intentCatalog', {'id': _link, 'ad': _ad, 'name': _name});
				// });
				if (brand_decorate_api.homePage) {
					loadURL('http://intentCatalog?id=' + _link + '&ad=' + _ad + '&name=' + _name + '&productTypeId=' + _productid);
				} else {
					callIntentTrailer(brand_decorate_api.iosNative, 'intentCatalog', {'id': _link, 'ad': _ad, 'name': _name , 'productTypeId': _productid});
				}
			} else {
				getUrl('activity/templet/brand_custom.html?productTypeId=' + _link + '&adCatalog=' + _ad + '&title=' + _name);
			}
		// } else {
		// 	console.log('adCatalog为空');
		// }
	} else if (_type == 7) {
		if (member.sys == 'android') {
			intentShoppingMall(_link, '', _name);
		} else if (member.sys == 'ios') {
			// checkAppVer(54, function () {
			// 	loadURL('http://intentShoppingMall?productTypeOne=' + _link + '&productTypeTwo=' + '' + '&productTypeTitle=' + _name);
			// }, function () {
			// 	callIntentTrailer(brand_decorate_api.iosNative, 'intentShoppingMall', {'productTypeOne': _link, 'productTypeTwo': '', 'productTypeTitle': _name});
			// });
			if (brand_decorate_api.homePage) {
				loadURL('http://intentShoppingMall?productTypeOne=' + _link + '&productTypeTwo=' + '' + '&productTypeTitle=' + _name);
			} else {
				callIntentTrailer(brand_decorate_api.iosNative, 'intentShoppingMall', {'productTypeOne': _link, 'productTypeTwo': '', 'productTypeTitle': _name});
			}
		} else {
			window.sessionStorage.setItem('mall_title', _name);
			getUrl('mall/one.html?type=' + _link);
		}
	} else if (_type == 8) {
		if (member.sys == 'android') {
			intentShoppingMall('', _link, _name);
		} else if (member.sys == 'ios') {
			// checkAppVer(54, function () {
			// 	loadURL('http://intentShoppingMall?productTypeTwo=' + _link + '&productTypeOne=' + '' + '&productTypeTitle=' + _name);
			// }, function () {
			// 	callIntentTrailer(brand_decorate_api.iosNative, 'intentShoppingMall', {'productTypeOne': '', 'productTypeTwo': _link, 'productTypeTitle': _name});
			// });
			if (brand_decorate_api.homePage) {
				loadURL('http://intentShoppingMall?productTypeTwo=' + _link + '&productTypeOne=' + '' + '&productTypeTitle=' + _name);
			} else {
				callIntentTrailer(brand_decorate_api.iosNative, 'intentShoppingMall', {'productTypeOne': '', 'productTypeTwo': _link, 'productTypeTitle': _name});
			}
		} else {
			getUrl('mall/two.html?type=' + _link);
		}
	} else if (_type == 9) {
		// url 链接 
		if (member.sys == 'ios') {
			// checkAppVer(54, function () {
			// 	loadURL('http://JumpWebView?url=' + _linkUrl);
			// }, function () {
			// 	callIntentTrailer(brand_decorate_api.iosNative, 'JumpWebView', {'url': _linkUrl});
			// });
			if (brand_decorate_api.homePage) {
				loadURL('http://JumpWebView?url=' + _linkUrl);
			} else {
				callIntentTrailer(brand_decorate_api.iosNative, 'JumpWebView', {'url': _linkUrl});
			}
		} else {
			/!http|xgbuy/g.test(_linkUrl) && (_linkUrl += (/\?/g.test(_linkUrl) ? '&' : '?') + 'new' + _new);
			getUrl(_linkUrl, 'self', null, false, _name);
		}
	} else if (_type == 11) {
		// 优惠券
		if (member.sys == 'android') {
			if (member.appver >= 58) {
				getCounpons(_this.getAttribute('data-productid'));
				return;
			} else {
				delayTip('请下载最新版本！');
			}
		} else if (member.sys == 'ios') {
			if (member.appver >= 59) {
				if (brand_decorate_api.homePage) {
					loadURL('http://getCounpons?couponID=' + _this.getAttribute('data-productid'));
				} else {
					callIntentTrailer(brand_decorate_api.iosNative, 'getCounpons', {'couponID': _this.getAttribute('data-productid')});
				}
				return;
			} else {
				delayTip('请下载最新版本！');
			}
		} else {
			$.ajax({
				method: 'post',
				url: getApi.addReceiveCoupon,
				data: JSON.stringify({
					reqData: {	
						couponId: _this.getAttribute('data-productid'),
						memberId: member.id
					},
					system: member.psys,
					version: member.appver,
					token: member.token,
					timeStamp: Date.now(),
					nonceStr: "OV5QOVX21A0446K8",
					signature: "7f8c4ad7f6d349cf28879670b9fe79b9"
				}),
				success: function (res) {
					if (res.returnData) {
						alertTip(res.returnData.content);
					}
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
	} else if (_type == 12) {
		// APP商城
		if (member.sys == 'android') {
			intentNewShopmallPage(_link);
		} else if (member.sys == 'ios') {
			// checkAppVer(54, function () {
			// 	loadURL('http://intentNewShopmallPage?categoryId=' + _link);
			// }, function () {
			// 	callIntentTrailer(brand_decorate_api.iosNative, 'intentNewShopmallPage', {'categoryId': _link});
			// });
			if (brand_decorate_api.homePage) {
				loadURL('http://intentNewShopmallPage?categoryId=' + _link);
			} else {
				callIntentTrailer(brand_decorate_api.iosNative, 'intentNewShopmallPage', {'categoryId': _link});
			}
		} else {
			getUrl('mall');
		}
	} else if (_type == 13) {
		// 店铺
		getUrlShop(_link);
	} else if (_type == 14) {
		// 微淘关键字
		if (member.sys == 'android') {
			checkAppVer(51, function () {
				goToWeiTaoList(_linkUrl);
			});
		} else if (member.sys == 'ios') {
			if (brand_decorate_api.homePage) {
				loadURL('http://goToWeiTaoList?weitaoKey=' + _linkUrl);
			} else {
				callIntentTrailer(brand_decorate_api.iosNative, 'goToWeiTaoList', {'weitaoKey': _linkUrl});
			}
		}
	} else if (_type == 15) {
		// 新品牌团
		if (member.sys == 'android') {
			checkAppVer(55, function () {
				newBrandGroup(_link);
			});
		} else if (member.sys == 'ios') {
			checkAppVer(57, function () {
				if (brand_decorate_api.homePage) {
					loadURL('http://newBrandGroup?brandTeamTypeId=' + _link);
				} else {
					callIntentTrailer(brand_decorate_api.iosNative, 'newBrandGroup', {'brandTeamTypeId': _link});
				}
			});
		}
	}
	else if (_type == 16) {
		// 新品牌团 - 二级分类
		if (member.sys == 'android') {
			checkAppVer(58, function () {
				NewBrandGroupSecondClassify(_linkUrl);
			});
		} else if (member.sys == 'ios') {
			checkAppVer(59, function () {
				if (brand_decorate_api.homePage) {
					loadURL('http://NewBrandGroupSecondClassify?value=' + _linkUrl);
				} else {
					callIntentTrailer(brand_decorate_api.iosNative, 'NewBrandGroupSecondClassify', {'value': _linkUrl});
				}
			});
		}
	} else if (_type == 17) {
		// 淘宝优选频道
		if (member.sys == 'android') {
			checkAppVer(57, function () {
				taobaoyouxuanpingdao(_link);
			});
		} else if (member.sys == 'ios') {
			checkAppVer(58, function () {
				if (brand_decorate_api.homePage) {
					loadURL('http://taobaoyouxuanpingdao?id=' + _link);
				} else {
					callIntentTrailer(brand_decorate_api.iosNative, 'taobaoyouxuanpingdao', {'id': _link});
				}
			});
		}
	} else if (_type == 19 || _type == 21 || _type == 23 || _type == 25 || _type == 27 || _type == 29 || _type == 31) {
		// 有好货品牌 / 潮鞋上新品牌 / 潮流男装品牌 / 断码特惠品牌 / 运动鞋服品牌 / 潮流美妆品牌 / 食品超市品牌
		if (member.sys == 'android') {
			checkAppVer(58, function () {
				chaoLiuList(_linkUrl, _type);
			});
		} else if (member.sys == 'ios') {
			checkAppVer(59, function () {
				if (brand_decorate_api.homePage) {
					loadURL('http://chaoLiuList?id=' + _linkUrl + '&type=' + _type);
				} else {
					callIntentTrailer(brand_decorate_api.iosNative, 'chaoLiuList', {'id': _linkUrl, 'type': _type});
				}
			});
		}
	} else if (_type == 18 || _type == 20 || _type == 22 || _type == 24 || _type == 26 || _type == 28 || _type == 30 || _type == 32) {
		// 有好货二级分类// 潮鞋上新二级分类 / 潮流男装二级分类 / 断码特惠二级分类 / 运动鞋服二级分类 / 潮流美妆二级分类 / 食品超市二级分类 / 每日好店二级分类
		if (member.sys == 'android') {
			checkAppVer(58, function () {
				chaoLiuSecond(_linkUrl, _type);
			});
		} else if (member.sys == 'ios') {
			checkAppVer(59, function () {
				if (brand_decorate_api.homePage) {
					loadURL('http://chaoLiuSecond?id=' + _linkUrl + '&type=' + _type);
				} else {
					callIntentTrailer(brand_decorate_api.iosNative, 'chaoLiuSecond', {'id': _linkUrl, 'type': _type});
				}
			});
		}
	} else if (_type == 33) {
		// 大学生创业二级分类
	}
}

// 跳转 - 店铺
function getUrlShop(e) {
	stopSelf();
	var _id = typeof e === 'object' ? e.getAttribute('data-id') : e;

	if (member.sys == 'ios') {
		// checkAppVer(54, function () {
		// 	loadURL('http://intentShopping?mchtId=' + _id);
		// }, function () {
		// 	checkAppVer(43, function () {
		// 		callIntentTrailer(brand_decorate_api.iosNative, 'intentShopping', {'mchtId': _id});
		// 	}, function () {
		// 		getUrl('seller/index.html?mchtId=' + _id);
		// 	});
		// });
		if (brand_decorate_api.homePage) {
			loadURL('http://intentShopping?mchtId=' + _id);
		} else {
			checkAppVer(43, function () {
				callIntentTrailer(brand_decorate_api.iosNative, 'intentShopping', {'mchtId': _id});
			}, function () {
				getUrl('seller/index.html?mchtId=' + _id);
			});
		}
	} else if (member.sys == 'android') {
		checkAppVer(36, function () {
			intentShopping(_id);
		}, function () {
			getUrl('seller/index.html?mchtId=' + _id);
		});
	} else {
		getUrl('seller/index.html?mchtId=' + _id);
	}
}

// 会场秒杀
function seckillListNav6(_this, _scroll) {
	_this && (decorateType6.tabSelect = $(_this));
	if (decorateType6.tabSelect.hasClass('active') && !_scroll) {
		return;
	}

	decorateType6.tabSelect.addClass('active').siblings().removeClass('active');

	var _con = decorateType6.tabSelect.closest('.con-box').find('.product-scroll'),
		_scr = _con.parent();

	if (!_scroll) {
		_scr.removeAttr('data-page').removeAttr('data-old');
		_scr.scrollLeft(0);
	}

	require(['template'], function (template) {
		postApi(getApi.getTopSecKillProductList, {
			beginTime: decorateType6.tabSelect.attr('data-beg'),
			currentPage: _scr.attr('data-page') || 0
		}, function (e) {
			var _list = e.returnData,
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
				seckillListTime(decorateType6.tabSelect, decorateType6);

				if (_len == 10) {
					scrollHand.init(_scr, function () {
						seckillListNav6('', true);
					});
				}
			}
		});
	});
}

// 限时秒杀
function seckillListNav11(_this, _scroll) {
	_this && (decorateType11.tabSelect = $(_this));
	if (decorateType11.tabSelect.hasClass('active') && !_scroll) {
		return;
	}

	decorateType11.tabSelect.addClass('active').siblings().removeClass('active');

	var _con = decorateType11.tabSelect.closest('.con-box').find('.product-scroll'),
		_scr = _con.parent();

	if (!_scroll) {
		_scr.removeAttr('data-page').removeAttr('data-old');
		_scr.scrollLeft(0);
	}

	require(['template'], function (template) {
		postApi(getApi.getTopSecKillProductList, {
			seckillType: 1,
			beginTime: decorateType11.tabSelect.attr('data-beg'),
			currentPage: _scr.attr('data-page') || 0
		}, function (e) {
			var _list = e.returnData,
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
		});
	});
}

function getRushbuy() {
	if (member.sys == 'android') {
		intentSeckill();
	} else if (member.sys == 'ios') {
		// checkAppVer(54, function () {
		// 	loadURL('http://intentSeckill');
		// }, function () {
		// 	callIntentTrailer(brand_decorate_api.iosNative, 'intentSeckill');
		// });
		if (brand_decorate_api.homePage) {
			loadURL('http://intentSeckill');
		} else {
			callIntentTrailer(brand_decorate_api.iosNative, 'intentSeckill');
		}
	} else {
		getUrl('rushbuy/index.html?type=3', 'self');
	}
}

function seckillListTime($ts, _obj) {
	var _beg = $ts.data('beg'),
		_cur = $ts.data('cur'),
		_end = $ts.data('end'),
		_ends = _end,
		_time = $ts.closest('.con-seckill-' + _obj.type).find('.tab-time'),
		_str = '';

	_cur += (new Date).getTime() - _obj.sysCur;

	if (_cur < _beg) {
		_ends = _beg;
		_str = '距离本场开始&nbsp;';
	} else if (_cur < _end) {
		_str = '距离本场结束&nbsp;';
	} else {
		_ends = false;
		_str = '活动已结束';
	}

	if (_ends) {
		clearInterval(seckillTimer);

		seckillTimer = $.qpTime({
			s: _cur,
			e: _ends
		}, function (pt) {
			_time.html(_str + '<b class="flex ac jc">' + pt.h + '</b>:<b class="flex ac jc">' + pt.m + '</b>:<b class="flex ac jc">' + pt.s + '</b>');

			!pt.t && setTimeout(function () {
				location.reload();
			}, 1e3);
		});
	} else {
		_time.html(_str);
	}
}

// 首页刷新
function refreshHomeWebview() {
	window.H5PluginManager.refreshHomeWebview();
}

// 商品详情
function intentProductDetail(a, b) {
	window.H5PluginManager.intentProductDetail(a, b);
}

// 活动列表
function intentTrailer(a, b) {
	window.H5PluginManager.intentTrailer(a, b);
}

// 新用户专享
function intentNewUserShare() {
	window.H5PluginManager.intentNewUserShare();
}
// 单品爆款
function intentSingleExplo() {
	window.H5PluginManager.intentSingleExplo();
}
// 限时抢购
function intentSeckill() {
	window.H5PluginManager.intentSeckill();
}
// 新用户秒杀
function intentNewUserSeckill() {
	window.H5PluginManager.intentNewUserSeckill();
}
// 积分商城
function intentIntegralMall() {
	window.H5PluginManager.intentIntegralMall();
}
// 断码清仓
function intentFaultCode2() {
	window.H5PluginManager.intentFaultCode2();
}
// 一级栏目 (productTypeId)
function intentCatalog(_name, _ad, _link) {
	window.H5PluginManager.intentCatalog(_name, _ad, _link);
}

// 商城1/2级 (productTypeId)
function intentShoppingMall(_one, _two, _name) {
	window.H5PluginManager.intentShoppingMall(_one, _two, _name);
}
// 新商城
function intentNewShopmallPage(_link) {
	window.H5PluginManager.intentNewShopmallPage(_link);
}

// 店铺
function intentShopping(_id) {
	window.H5PluginManager.intentShopping(_id);
}

// 安卓滑动模块
function needSlidingAround(_n) {
	window.H5PluginManager && window.H5PluginManager.needSlidingAround && window.H5PluginManager.needSlidingAround(_n);
}

// 微淘关键字
function goToWeiTaoList(_n) {
	window.H5PluginManager.goToWeiTaoList(_n);
}

// 新品牌团
function newBrandGroup(_n) {
	window.H5PluginManager.newBrandGroup(_n);
}

// 新品牌团二级分类
function NewBrandGroupSecondClassify(_n) {
	window.H5PluginManager.NewBrandGroupSecondClassify(_n);
}

// 淘宝优选
function taobaoyouxuan() {
	window.H5PluginManager.taobaoyouxuan();
}

// 淘宝优选频道
function taobaoyouxuanpingdao(_n) {
	window.H5PluginManager.taobaoyouxuanpingdao(_n);
}

// 潮流一级
function chaoLiuList(_n, _t) {
	window.H5PluginManager.chaoLiuList(_n, _t);
}

// 潮流二级
function chaoLiuSecond(_n, _t) {
	window.H5PluginManager.chaoLiuSecond(_n, _t);
}

// 潮流栏目
function chaoLiuColumn(_n) {
	window.H5PluginManager.chaoLiuColumn(_n);
}

// 断码特惠
function chaoLiuDuanma(_n) {
	window.H5PluginManager.chaoLiuDuanma(_n);
}

// 安卓首页加载失败
function hideH5(_n) {
	window.H5PluginManager && window.H5PluginManager.hideH5 && window.H5PluginManager.hideH5(_n);
}

// 有好货
function haveGoods() {
	window.H5PluginManager.haveGoods();
}

// 每日好店
function everydayShop() {
	window.H5PluginManager.everydayShop();
}

// 品牌团
function brandGroup() {
	window.H5PluginManager.brandGroup();
}

// svip
function intentSvip() {
	window.H5PluginManager.intentSvip();
}

// app提示
function appDelayTip(e) {
	window.H5PluginManager.appDelayTip(e);
}

// 首页装修优惠券领取
function getCounpons(e) {
	window.H5PluginManager.getCounpons(e);
}

function getSvip(e) {
	defSelf(e);

	if (member.sys == 'android') {
		intentSvip();
	} else if (member.sys == 'ios') {
		if (brand_decorate_api.homePage) {
			loadURL('http://intentSvip');
		} else {
			callIntentTrailer('intentTrailerNative', 'intentSvip');
		}
	} else {
		console.log('H5的svip首页暂未开发');
	}
}

// ios跳转接口
function callIntentTrailer(handlerInterface, handlerMethod, parameters) {
	var dic = {'handlerInterface': handlerInterface, 'function': handlerMethod, 'parameters': parameters};
	
	if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
		window.webkit.messageHandlers[handlerInterface].postMessage(dic);
	} else {
		window[handlerInterface][handlerMethod](JSON.stringify(dic));
	}
}

// 请求接口
function postApi(url, data, fn, fm) {
	data = data === null ? '' : JSON.stringify({
		reqData: data,
		system: member.psys,
		version: member.appver,
		token: '',
		timeStamp: (new Date).getTime(),
		nonceStr: "OV5QOVX21A0446K8",
		signature: "7f8c4ad7f6d349cf28879670b9fe79b9"
	});

	$.ajax({
		method: 'post',
		url: url,
		data: data,
		success: function (e) {
			fn(e);
		},
		error: function (e) {
			fm && fm(e);
		}
	});
}

function getUrl(_url) {
	location.href = /http/g.test(_url) ? _url : page_urls + _url;
}

// 跳转 - 活动
function getSingleHot(_id, _name, _act) {
	_act == 'undefined' && (_act = '');
	getUrl('activity/templet/single_hot.html?activityId=' + _id + '&activityAreaId=' + _act);
}

// 跳转 - 活动列表(single_hot)
function getUrlActiveSingle1(e) {
	var _activityid = e.getAttribute('data-activityid'),
		_name = e.getAttribute('data-name');

	if (member.sys == 'android') {
		intentTrailer(_name, _activityid);
	} else if (member.sys == 'ios') {
		// checkAppVer(54, function () {
		// 	loadURL('http://intentTrailer?activityName=' + _name + '&activityId=' + _activityid);
		// }, function () {
		// 	callIntentTrailer('intentTrailerNative', 'intentTrailer', {'activityName': _name, 'activityId': _activityid});
		// });
		if (brand_decorate_api.homePage) {
			loadURL('http://intentTrailer?activityName=' + _name + '&activityId=' + _activityid);
		} else {
			callIntentTrailer('intentTrailerNative', 'intentTrailer', {'activityName': _name, 'activityId': _activityid});
		}
	} else {
		getSingleHot(_activityid, _name);
	}
}

// 跳转 - 商品详情
function getUrlGoods(e, _s) {
	var _pro = e.getAttribute('data-productid'),
		_act = e.getAttribute('data-activityareaid');

	_act == 'undefined' && (_act = '');

	if (!_pro) {
		return;
	}

	if (member.sys == 'android') {
		intentProductDetail(_pro, _act);
	} else if (member.sys == 'ios') {
		if (_s) {
			// checkAppVer(54, function () {
			// 	loadURL('http://intentProductDetail?productId=' + _pro + '&activityAreaId=' + _act + '&isFromLimit=' + true);
			// }, function () {
			// 	callIntentTrailer(brand_decorate_api.iosNative, 'intentProductDetail', {'productId': _pro, 'activityAreaId': _act, 'isFromLimit': true});
			// });
			if (brand_decorate_api.homePage) {
				loadURL('http://intentProductDetail?productId=' + _pro + '&activityAreaId=' + _act + '&isFromLimit=' + true);
			} else {
				callIntentTrailer(brand_decorate_api.iosNative, 'intentProductDetail', {'productId': _pro, 'activityAreaId': _act, 'isFromLimit': true});
			}
		} else {
			// checkAppVer(54, function () {
			// 	loadURL('http://intentProductDetail?productId=' + _pro + '&activityAreaId=' + _act);
			// }, function () {
			// 	callIntentTrailer(brand_decorate_api.iosNative, 'intentProductDetail', {'productId': _pro, 'activityAreaId': _act});
			// });
			if (brand_decorate_api.homePage) {
				loadURL('http://intentProductDetail?productId=' + _pro + '&activityAreaId=' + _act);
			} else {
				callIntentTrailer(brand_decorate_api.iosNative, 'intentProductDetail', {'productId': _pro, 'activityAreaId': _act});
			}
		}
	} else {
		getUrl('goods/detail.html?id=' + _pro + '&activityAreaId=' + _act);
	}
}

function imgCacheAppDecorate(_list, _cac) {
	if (!_list) {
		return _list;
	}

	var cache = {
		list: win_lc.getItem(_cac),
		path: [],
		name: []
	};

	if (cache.list) {
		cache.list = JSON.parse(cache.list);
		cache.path = cache.list.path,
		cache.name = cache.list.name;

		for (var j = 0, _name = _list.match(reg_cache_img)[0]; j < cache.list.name.length; j++) {
			(_name == cache.list.name[j]) && (_list = cache.list.path[j]);
		}
	}

	var _arr = _list.match(reg_cache_img);

	cache.path.push(_arr.input);
	cache.name.push(_arr[0]);

	var _name = [],
		_path = [],
		_same = {};

	for (var i = 0, d; (d = cache.name[i]) != null; i++) {
		if (!_same[d]) {
			_name.push(d);
			_path.push(cache.path[i]);
			_same[d] = true;
		}
	}
	cache.name = _name;
	cache.path = _path;

	win_lc.setItem(_cac, JSON.stringify({
		path: cache.path,
		name: cache.name
	}));

	return _list;
}

function imgCachePic(_list, _cac) {
	if (!_list || !_list.length) {
		return _list;
	}

	var cache = {
		list: win_lc.getItem(_cac),
		path: [],
		name: []
	};

	if (cache.list) {
		cache.list = JSON.parse(cache.list);
		cache.path = cache.list.path,
		cache.name = cache.list.name;

		for (var i = 0; i < _list.length; i++) {
			var _pic = _list[i].pic;

			if (_pic) {
				for (var j = 0, _name = _pic.match(reg_cache_img)[0]; j < cache.list.name.length; j++) {
					(_name == cache.list.name[j]) && (_list[i].pic = cache.list.path[j]);
				}
			}
		}
	}

	for (var i = 0; i < _list.length; i++) {
		var _pic = _list[i].pic;

		if (_pic) {
			var _arr = _pic.match(reg_cache_img);

			cache.path.push(_arr.input);
			cache.name.push(_arr[0]);
		}
	}

	var _name = [],
		_path = [],
		_same = {};

	for (var i = 0, d; (d = cache.name[i]) != null; i++) {
		if (!_same[d]) {
			_name.push(d);
			_path.push(cache.path[i]);
			_same[d] = true;
		}
	}
	cache.name = _name;
	cache.path = _path;

	win_lc.setItem(_cac, JSON.stringify({
		path: cache.path,
		name: cache.name
	}));

	return _list;
}

function imgCacheConmonBrandAreaEntryPic(_list, _cac) {
	var cache = {
		list: win_lc.getItem(_cac),
		path: [],
		name: []
	};

	if (cache.list) {
		cache.list = JSON.parse(cache.list);
		cache.path = cache.list.path,
		cache.name = cache.list.name;

		for (var i = 0; i < _list.length; i++) {
			var _pic = _list[i].areaEntryPic;

			if (_pic) {
				for (var j = 0, _name = _pic.match(reg_cache_img)[0]; j < cache.list.name.length; j++) {
					(_name == cache.list.name[j]) && (_list[i].areaEntryPic = cache.list.path[j]);
				}
			}
		}
	}

	for (var i = 0; i < _list.length; i++) {
		var _pic = _list[i].areaEntryPic;

		if (_pic) {
			var _arr = _pic.match(reg_cache_img);

			cache.path.push(_arr.input);
			cache.name.push(_arr[0]);
		}
	}

	var _name = [],
		_path = [],
		_same = {};

	for (var i = 0, d; (d = cache.name[i]) != null; i++) {
		if (!_same[d]) {
			_name.push(d);
			_path.push(cache.path[i]);
			_same[d] = true;
		}
	}
	cache.name = _name;
	cache.path = _path;

	win_lc.setItem(_cac, JSON.stringify({
		path: cache.path,
		name: cache.name
	}));

	return _list;
}

function imgCacheProductPic(_list, _cac) {
	if (!_list || !_list.length) {
		return _list;
	}

	var cache = {
		list: win_lc.getItem(_cac),
		path: [],
		name: []
	};

	if (cache.list) {
		cache.list = JSON.parse(cache.list);
		cache.path = cache.list.path,
		cache.name = cache.list.name;

		for (var i = 0; i < _list.length; i++) {
			var _pic = _list[i].productPic;

			if (_pic) {
				for (var j = 0, _name = _pic.match(reg_cache_img)[0]; j < cache.list.name.length; j++) {
					(_name == cache.list.name[j]) && (_list[i].productPic = cache.list.path[j]);
				}
			}
		}
	}

	for (var i = 0; i < _list.length; i++) {
		var _pic = _list[i].productPic;

		if (_pic) {
			var _arr = _pic.match(reg_cache_img);

			cache.path.push(_arr.input);
			cache.name.push(_arr[0]);
		}
	}

	var _name = [],
		_path = [],
		_same = {};

	for (var i = 0, d; (d = cache.name[i]) != null; i++) {
		if (!_same[d]) {
			_name.push(d);
			_path.push(cache.path[i]);
			_same[d] = true;
		}
	}
	cache.name = _name;
	cache.path = _path;

	win_lc.setItem(_cac, JSON.stringify({
		path: cache.path,
		name: cache.name
	}));

	return _list;
}

function getUrlActive(e) {
	var _name = $(e).data('name'),
		_url = $(e).data('url'),
		_source = $(e).data('source'),
		_id = $(e).data('activityid'),
		_act = $(e).data('activityareaid'),

		_new = _source + '_' + _act;

	if (_source == 1) {
		if (_url) {
			getUrl(_url, 'self');
		} else {
			$.ajax({
				method: 'post',
				url: getApi.areaTempletByAreaId + '?activityAreaId=' + _act,
				success: function (e) {
					var _u = e.returnData.areaUrl;

					if (member.sys == 'ios') {
						// checkAppVer(54, function () {
						// 	loadURL('http://JumpWebView?url=' + _u);
						// }, function () {
						// 	callIntentTrailer(brand_decorate_api.iosNative, 'JumpWebView', {'url': _u});
						// });
						if (brand_decorate_api.homePage) {
							loadURL('http://JumpWebView?url=' + _u);
						} else {
							callIntentTrailer(brand_decorate_api.iosNative, 'JumpWebView', {'url': _u});
						}
					} else {
						/!http|xgbuy/g.test(_u) && (_u += (/\?/g.test(_u) ? '&' : '?') + 'new' + _new);
						getUrl(_u, 'self', null, false, _name);
					}
				},
				error: function (e) {
					delayTip(e.statusText);
				}
			});
		}
	} else {
		// getSingleHot(_id, _name);
		getUrlActiveSingle1(e)
	}
}

function getSingleHot(_id, _name) {
	var _url = location_href.split('/views/')[0] + '/views/ios.html' + redirect_url + 'activity/templet/single_hot.html?activityId=' + _id;
	if (member.sys == 'ios') {
		// checkAppVer(54, function () {
		// 	loadURL('http://JumpWebView?url=' + _url);
		// }, function () {
		// 	callIntentTrailer(brand_decorate_api.iosNative, 'JumpWebView', {'url': _url});
		// });
		if (brand_decorate_api.homePage) {
			loadURL('http://JumpWebView?url=' + _url);
		} else {
			callIntentTrailer(brand_decorate_api.iosNative, 'JumpWebView', {'url': _url});
		}
	} else {
		getUrl(_url, 'self', null, false, _name);
	}
}

// iOS获取链接
function loadURL(url) {
	var iFrame;
	iFrame = document.createElement("iframe");
	iFrame.setAttribute("src", url);
	iFrame.setAttribute("style", "display:none;");
	iFrame.setAttribute("height", "0px");
	iFrame.setAttribute("width", "0px");
	iFrame.setAttribute("frameborder", "0");

	document.body.appendChild(iFrame);
}

function imgErrorEmpty(_this) {
	_this.style.display = 'none';
}