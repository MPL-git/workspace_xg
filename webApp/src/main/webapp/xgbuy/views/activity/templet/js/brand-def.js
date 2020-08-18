var activity_brand = $('.page[data-id="activity_templet_brand_def"]:visible'),
	activity_brand_con = activity_brand.find('.content'),
	activity_brand_scroll = activity_brand.find('.scroll-more'),
	activity_brand_swiper = activity_brand.find('.swiper-wrapper img'),
	activity_brand_list = '';

var brandDef = {
	data: {
		size: 10
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;
		var urlParams = real_url.split('?')[real_url.split('?').length-1].split('&');
		var meberId = urlParams.indexOf('memberId=');

		if (isxgbuy || meberId != -1) {
			$('#brandDefTop').append(template('brandDefHeader'));
		} else if (!isWeiXin) {
			$('#brandDefTop').append(template('brandDefDownload'))
		}

		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'activityAreaId': _td.act = _b; break;
					case 'isPreview': _td.pre = _b; break;
				}
			}
		}

		traffic.setPv(_td, {
			valueId: _td.act,
			pageType: '30'
		});

		_ts.getPagingList();
	},

	getPagingList: function (_scroll) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getBrandActivityByAreaId, {
			data: {
				activityAreaId: _td.act,
				memberId: member.id,
				currentPage: activity_brand_scroll.attr('data-page') || 0,
				pageSize: _td.size
			},
			success: function (res) {
				var _list = res.returnData,
					_len = _list.dataMapList.length,
					_size = res.returnSize,
					_coupon = _list.couponMapList,
					_title = _list.activityAreaName;

				_list.dataMapList = imgCacheBrandActivityEntryPic(_list.dataMapList, 'activity_brand_tpl_list');

				if (_scroll) {
					if (_len) {
						var _cur = activity_brand_scroll.attr('data-page');
						_list.page = _cur * _td.size;
						_cur++;
						activity_brand_scroll.attr('data-page', _cur);
						activity_brand_list.append(template('activity_brand_tpl_list', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_size && (_td.size = _size);
					_list.page = 0;
					activity_brand_con.html(template('activity_brand_tpl', _list));

					// _title && setTitle(_title);
					_title && $('#brandDefTop header p').html(_title);
					activity_brand_list = activity_brand_con.find('.common-activity-con');

					// 海报图
					if (_list.topPic) {
						_list.topPic = imgCache(_list.topPic, 'activity_brand_toppic');
						activity_brand_swiper.attr('src', _list.topPic);
					} else {
						activity_brand_swiper.closest('div').hide();
					}

					// 预览
					_td.pre && activity_brand_con.find('a').removeAttr('href').removeAttr('onclick');

					if (_coupon && _coupon.length) {
						member.list = _coupon;
						setStorage('coupon_act', _td.act);
					}

					// 倒计时
					var _time = activity_brand.find('.count-time'),
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
							}, function (e) {
								_span.eq(0).html(e.d);
								_span.eq(1).html(e.h);
								_span.eq(2).html(e.m);
								_span.eq(3).html(e.s);
							});
						});
					}

					_len == _td.size && scrollHand.init(activity_brand_scroll, function () {
						_ts.getPagingList(true);
					}, true);
				}
			},
			error: function (res) {
				if (res.returnCode == '9999') {
					activity_brand.removeClass('bgf4');
					activity_brand_scroll.html(template('activity_brand_msg', res));
				}
			}
		});
		
		//618
		console.log(_td.act)

		if(_td.act){
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
								activity_brand_scroll.append(template('activity_decorate_618_tpl', res.returnData));
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
				})
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
						res.returnData.activityAreaId = _td.act
						activity_brand_scroll.append(template('activity_decorate_618_tpl', res.returnData));
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
	}
};

getTpl(function () {
	brandDef.init();
});

function onShow() {
	traffic.setPv(brandDef.data);
}

/********************************** 618 **********************************/

function ancePopupShow(id) {
	var _ts = brandDef,
	_td = _ts.data;
	id && (_td.act = id)
	new ajax(getApi.allowanceInfo, {
		data: {
			activityAreaId: _td.act,
			memberId: member.id
		},
		success: function (res) {
		res && (res.returnData.activityAreaId = _td.act)
			activity_brand_scroll.append(template('activity_decorate_618_popup_tpl', res.returnData));
			$('.popup-ance').addClass('show');
		}
	})
}
function ancePopupHide() {
	$('.popup-ance').removeClass('show');
	$(".popup-ance-con .card").eq(0).removeClass('out')
}
// 津贴兑换
function exchange(allowanceInfoId,id){
	var _ts = brandDef,
	_td = _ts.data;
	$(".popup-ance-con .card").eq(0).css("transition","none")
	$(".popup-ance-con .card").eq(0).removeClass('out')

	new ajax(getApi.allowanceExchange,{
		data:{
			allowanceInfoId:allowanceInfoId,
			id:id
		},
		success: function(res){
			$(".lastExchangeAllowance").text(res.returnData.lastExchangeAllowance + "元")
			$(".allowanceBalance").text(res.returnData.allowanceBalance + "元")
			$(".integral").text(res.returnData.integral)
			$(".popup-ance-con .card").eq(0).css("transition","all 0.3s")
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

function imgCacheBrandActivityEntryPic(_list, _cac) {
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
			var _pic = _list[i].activityEntryPic;

			if (_pic) {
				for (var j = 0, _name = _pic.match(reg_cache_img)[0]; j < cache.list.name.length; j++) {
					(_name == cache.list.name[j]) && (_list[i].activityEntryPic = cache.list.path[j]);
				}
			}
		}
	}

	for (var i = 0; i < _list.length; i++) {
		var _pic = _list[i].activityEntryPic;

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