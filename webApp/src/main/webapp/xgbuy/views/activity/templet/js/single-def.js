var single_def = $('.page[data-id="activity_templet_single_def"]:visible'),
	single_def_scroll = single_def.find('.scroll-more'),
	single_def_con = single_def.find('.content'),
	single_def_swiper = single_def.find('.swiper-wrapper img'),

	single_def_swiper_p = single_def_swiper.siblings().find('p'),
	single_def_filter = single_def.find('.dialog-filter'),
	single_def_filter_con = single_def_filter.find('.filter-section'),

	single_def_list = '',
	single_def_ticket = '';

var single_def_swiper_h = single_def.find('.swiper-container').height();

var singleDef = {
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

		var urlParams = real_url.split('?')[real_url.split('?').length-1].split('&');
		var meberId = urlParams.indexOf('memberId=');
		if (isxgbuy || meberId != -1) {
			$('#singleDefTop').append(template('singleDefHeader'));
		} else if (!isWeiXin) {
			$('#singleDefTop').append(template('singleDefDownload'));
		}

		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'activityAreaId': _td.act = _b; break;
					case 'isPreview': _td.pre = _b; break;
					case 'activityAreaType': _td.typ = _b; break;
				}
			}
		}

		traffic.setPv(_td, {
			valueId: _td.act,
			pageType: '30'
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
			single_def_scroll.removeAttr('data-page').removeAttr('data-old');
			single_def_scroll.scrollTop() > single_def_swiper_h && single_def_scroll.scrollTop(single_def_swiper_h);
		}

		new ajax(getApi.getIndividualActivityByAreaId, {
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
				activityAreaId: _td.act,

				currentPage: single_def_scroll.attr('data-page') || 0,
				pageSize: _td.size
			},
			success: function (res) {
				var _size = res.returnSize,
					_list = res.returnData,
					_act = _list.activityAreaId,
					_title = _list.activityAreaName;

				res.returnCode != '0000' && (_list = {
					produtMapList: []
				});

				var _prod = _list.produtMapList,
					_len = _prod.length;

				_list.produtMapList = imgCachePic(_prod, 'activity_single_tpl');

				if (_obj.scroll) {
					if (_len) {
						var _cur = single_def_scroll.attr('data-page');
						_list.page = _cur * _td.size;
						_cur++;
						single_def_scroll.attr('data-page', _cur);
						single_def_con.append(template('activity_single_list_tpl', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_list.page = 0;

					if (_obj.init) {
						// _title && setTitle(_title);
						_title && $('#singleDefTop header p').html(_title);
						_size && (_td.size = _size);
						_td.activityAreaId = _list.activityAreaId;

						single_def_con.html(template('activity_single_tpl', _list));

						single_def_con = single_def_con.find('.activity-single-con');

						// 倒计时
						var _time = single_def.find('.count-time'),
							time_p = _time.siblings('p'),
							_span = _time.find('span'),

							_now = new Date(_list.currentTime),
							_end = new Date(_list.activityEndTime),
							_begin = new Date(_list.activityBeginTime),
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

						// 海报图
						if (_list.topPic) {
							_list.topPic = imgCache(_list.topPic, 'activity_single_def_banner');
							single_def_swiper.attr('src', _list.topPic);
							single_def_swiper_p.html(_list.benefitPoint);
						} else {
							single_def_swiper.closest('div').hide();
						}
						console.log(_td.activityAreaId)
						// 优惠券
						new ajax(getApi.getActivityAreaCoupons, {
							data: {
								activityId: _td.act,
								activityAreaId: _td.activityAreaId ? _td.activityAreaId : _td.act
							},
							success: function (res1) {
								var _list1 = res1.returnData,
									_coupon = _list1.couponList || [];

								_coupon.length && (member.list = _coupon);
								single_def_ticket = single_def.find('.con-ticket');

								require(['ceiling'], function () {
									single_def_ticket.html(template('activity_single_coupon_tpl', _list1));
									single_def.find('.tag').ceiling(single_def_scroll, $('.page:visible header').is(':visible') ? $('.page:visible header').height() : 0);
								});
							}
						});

						shareHand.update({
							url: real_url,
							title: _list.activityAreaName,
							content: _list.benefitPoint,
							pictureUrl: _list.posterPic
						});

						// 预览
						_td.pre && single_def_con.addClass('p-e-n');
					} else {
						single_def_con.html(template('activity_single_list_tpl', _list));
					}

					_len == _td.size && scrollHand.init(single_def_scroll, function () {
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
					single_def.find('.activity-cart').remove();
					single_def.removeClass('bgf4');
					single_def_scroll.html(template('activity_single_def_msg', res));
				}
			}
		});

		//618
		if (_td.act) {
			if (member.id && member.token && member.sys == 'android' || member.id && member.token && member.sys == 'ios') {
				new ajax(getApi.appLogin, {
					data: {
						memberId: member.id,
						token: member.token
					},
					success: function (e) {
						new ajax(getApi.allowancePopInfo, {
							data: {
								activityAreaId: _td.act
							},
							success: function (res) {
								console.log(res)
								if (!res.returnData.allowance) {
									return
								}
								res.returnData.activityAreaId = _td.act
								single_def_scroll.append(template('activity_decorate_618_tpl', res.returnData));
								var popTypeTimeOut = getCookie('popTypeTimeOut' + _td.act)
								console.log(popTypeTimeOut)
								if (!popTypeTimeOut) {
									ancePopupShow()

									switch (res.returnData.popType) {
										case "1":
											// 每天一次
											setCookie('popTypeTimeOut' + _td.act, 1, 1)
											break;
										case "2":
											// 终生一次
											setCookie('popTypeTimeOut' + _td.act, 1, 365)
											break;
										case "3":
											// 自定义
											setCookie('popTypeTimeOut' + _td.act, 1, res.returnData.day)
											break;
									}
								}
							}
						})
					}
				}
				)
			}else{
				new ajax(getApi.allowancePopInfo, {
					data: {
						activityAreaId: _td.act
					},
					success: function (res) {
						console.log(res)
						if (!res.returnData.allowance) {
							return
						}
						single_def_scroll.append(template('activity_decorate_618_tpl', res.returnData));
						var popTypeTimeOut = getCookie('popTypeTimeOut' + _td.act)
						console.log(popTypeTimeOut)
						if (!popTypeTimeOut) {
							ancePopupShow()
	
							switch (res.returnData.popType) {
								case "1":
									// 每天一次
									setCookie('popTypeTimeOut' + _td.act, 1, 1)
									break;
								case "2":
									// 终生一次
									setCookie('popTypeTimeOut' + _td.act, 1, 365)
									break;
								case "3":
									// 自定义
									setCookie('popTypeTimeOut' + _td.act, 1, res.returnData.day)
									break;
							}
						}
					}
				})
			}
		}
	},

	filterDialog: function () {
		var _ts = this,
			_td = _ts.data;

		if (single_def_filter_con.is(':empty')) {
			new ajax(getApi.getProductScreeningConditionsByActivityId, {
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
								_b += '<li class="flex jc ac" data-val="' + _a[j].value + '" onclick="singleDef.filterActive(this)">' + _a[j].name + '</li>';
							}

							_c += '<div class="figure ' + _e[i].type + '"><h4 class="flex jb ac">' + _e[i].name + (_t != 'APP_PRODUCT_TYPE' ? '' : '<span class="flex ac" onclick="singleDef.filterToggle(this)">全部<i class="arrow-r ico"></i></span>') + '</h4><div><ul class="clearfix' + (_e[i].type == 'APP_PRODUCT_TYPE' ? ' multi' : '') + '">' + _b + '</ul></div></div>';
						}
					}

					single_def_filter_con.html(_c);

					single_def_scroll.addClass('hide');
					single_def_filter.removeClass('hide');
				}
			});
		} else {
			single_def_scroll.addClass('hide');
			single_def_filter.removeClass('hide');
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

		var _product = single_def_filter_con.find('.APP_PRODUCT_TYPE .active').parent()[0],
			_range = new Array(2),
			_arr = single_def_filter_con.find('.APP_PRICE_RANGE .active').data('val');

		_arr && (_range = _arr.split('-'));

		_td.pru = _product ? _product.getAttribute('data-multi') : '';
		_td.gro = single_def_filter_con.find('.APP_SUIT_PEOPLE .active').data('val');
		_td.min = _range[0];
		_td.max = _range[1];
		_td.sex = single_def_filter_con.find('.APP_SEX .active').data('val');

		_td.result = [];

		$.each(single_def_filter_con.find('.active'), function () {
			_td.result.push($(this).html());
		});

		if (_td.result.length) {
			single_def.find('.filter-succ-con').html(_td.result.join('、')).attr('title', _td.result.join('、')).parent().removeClass('hide');
			single_def.find('.tag-box .filter').addClass('active');
		} else {
			single_def.find('.filter-succ').addClass('hide');
			single_def.find('.tag-box .filter').removeClass('active');
		}

		$('.page:visible .dialog').is(':visible') && back();
		!isClear && member.app && history.back();
		removeStorage('singleFilter');

		_ts.getPagingList();
	},

	filterReset: function () {
		single_def_filter_con.find('li').removeClass('active');
	},

	filterClear: function () {
		this.filterReset();
		this.filterConfirm(1);
	}
};

getTpl(function () {
	singleDef.init();
});

function onShow() {
	traffic.setPv(singleDef.data);
}

/********************************** 618 **********************************/

function ancePopupShow(id) {
	var _ts = singleDef,
		_td = _ts.data;
	id && (_td.act = id)
	new ajax(getApi.allowanceInfo, {
		data: {
			activityAreaId: _td.act,
			memberId: member.id
		},
		success: function (res) {
			res && (res.returnData.activityAreaId = _td.act)
			single_def_scroll.append(template('activity_decorate_618_popup_tpl', res.returnData));
			$('.popup-ance').addClass('show');
		}
	})
}
function ancePopupHide() {
	$('.popup-ance').removeClass('show');
	$(".popup-ance-con .card").eq(0).removeClass('out')
}
// 津贴兑换
function exchange(allowanceInfoId, id) {
	$(".popup-ance-con .card").eq(0).css("transition", "none")
	$(".popup-ance-con .card").eq(0).removeClass('out')

	new ajax(getApi.allowanceExchange, {
		data: {
			allowanceInfoId: allowanceInfoId,
			id: id
		},
		success: function (res) {
			$(".lastExchangeAllowance").text(res.returnData.lastExchangeAllowance + '元')
			$(".allowanceBalance").text(res.returnData.allowanceBalance + '元')
			$(".integral").text(res.returnData.integral)
			$(".popup-ance-con .card").eq(0).css("transition", "all 0.3s")
			$(".popup-ance-con .card").eq(0).addClass('out')
			var _obj = {
				"linkType": 10,//津贴值变化
				"linkValue": {
				},
				"curturnVersion": 1,
				"extraMessage": "jsonString",
			}
			withApp(_obj)
		}
	})
}

/**
* cookie中存值
* */
function setCookie(name, value, days) {
	if (value) {
		var days = 1
		var exp = new Date();
		exp.setTime(exp.getTime() + days * 24 * 60 * 60 * 1000);
		console.log(exp)
		// 写入Cookie, toGMTString将时间转换成字符串
		document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
	}
};

/**
* cookie中取值
* */
function getCookie(name) {
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");	//匹配字段
	if (arr = document.cookie.match(reg)) {
		return unescape(arr[2]);
	} else {
		return null;
	}
};

// 会场顶部新增下载渠道
// 关闭
function closeDownload() {
	$('.activity-download').addClass('close-download');
}
// 下载领取（没有下载APP跳转下载页，已下载APP直接唤醒APP）
function downloadAppp() {
	var url = isAndroid ? 'xgbuy://xgapp.com/openxg?pageName=homePage' : 'XGApp://com.XGBuy?pageName=homePage';
	var downUrl = isAndroid ? 'http://xgbuy.cc/app/xgbuy.apk' : 'https://apps.apple.com/cn/app/id1250285750';
	location.href = url;
	setTimeout(function () {
		var hidden = window.document.hidden || window.document.mozHidden || window.document.msHidden || window.document.webkitHidden;
		if (!hidden) {
			location.href = downUrl;
		}
	}, 2000);
}