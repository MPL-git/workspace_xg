var goods_detail = $('.page[data-id="goods_detail"]:visible'),
	goods_detail_con = goods_detail.find('.content'),
	goods_detail_start = goods_detail.find('.start'),
	goods_detail_coupon_title = goods_detail.find('.head-span'),
	goods_detail_coupon = goods_detail.find('.product-coupon'),

	goods_detail_freight = '',
	goods_detail_freight_con = '',

	goods_detail_red = '',

	goods_detail_share = {
		url: '',
		title: '',
		content: '',
		pictureUrl: ''
	},

	goods_detail_share_sp1 = {
	},

	goods_coupon_param = {},

	goods_freight_province = {};

var goodsDetail = {
	data: {
		more: 1,
		sec: '',
		sku: '',
		data: '',
		abs: '',
		label: '',
		thumb: '',
		standard: '',
		mobileVerfiyFlag: 1,

		cutOrderId: '', // 助力减价新增 助力单ID-分享时使用
		maxInviteTimes: 0,
		fixedAmount: 0,
		skuPic: '',

		preSell: 0, // 是否定金

		adSprId: '', // 抖音标记

		spreadAmountStr: '', // 分润金额
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;
			laywifi2 = 0
			laywifi_th = false
		traffic.setPv(_td, {
			pageType: '29'
		});
		window.addEventListener("popstate", function (e) {
			var v = $("video")
			for (var i = 0; i < v.length; i++) {
				v[i].pause()
			}
			window.removeEventListener("popstate",function(){
			})
		}, false);
		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'id': _td.id = _b; break;
					case 'code': _td.code = _b; break;
					case 'adSprId': _td.adSprId = _b; break;
					case 'cutOrderId': _td.cutOrderId = _b; break;
					case 'activityAreaId': _td.act = _b; break;
				}
			}
		}

		!getStorage('adSprId') && _td.adSprId && setStorage('adSprId', _td.adSprId);

		new ajax(getApi.productInfo, {
			data: {
				memberId: member.id,
				id: _td.id,
				code: _td.code,
				cutOrderId: _td.cutOrderId
			},
			success: function (e) {
				var _list = e.returnData,
					data_map = _list.dataMap,
					_type = _list.activityType;

				(_type == 1 || _type == 3 || _type == 4 || _type == 5) && (_td.sec = true);

				goods_detail_share.title = _list.shareMainTitle;
				goods_detail_share.content = _list.shareTitle;
				goods_detail_share.url = _list.shareUrl;
				goods_detail_share.pictureUrl = _list.shopLogo;

				if (_list.auditStatus == 1) {
					setTitle('商品未上架');
					goods_detail_con.html(template('goods_detail_tpl_closed', _list));

					_list.decorateInfoId && $.ajax({
						method: 'get',
						url: lead_url + 'views/activity/templet/brand_decorate.html',
						datatype: 'html',
						success: function (data) {
							setStorage('decorate_infoid', _list.decorateInfoId);
							setStorage('decorate_areaid', false);
							setStorage('decorate_nest', 1);

							data = data
								.replace('<header class="flex', '<header class="flex hide')
								.replace('page flex dc activity-single-day', 'activity-single-day')
								.replace(' data-id="activity_templet_brand_decorate"', '');

							goods_detail_con.append(data).addClass('scroll-y');
						}
					});
				} else if (data_map) {
					_list.activityId = _list.activityId || '';
					data_map.discount = famateDiscount(data_map.discount);
					_list.activityAreaId = _td.act || _list.activityAreaId;
					_td.data = data_map;
					_td.standard = _list.standardMap;
					!_td.id && (_td.id = data_map.id);
					_td.mobileVerfiyFlag = _list.mobileVerfiyFlag;
					_td.cutOrderId = _list.cutOrderId;
					goods_coupon_param = {
						mchtId: _list.dataMap.mchtId,
						activityId: _list.activityId,
						activityType: _list.activityType,
						activityAreaId: _list.activityAreaId,
						source: _list.source
					};
					_td.spreadAmountStr = data_map.spreadAmountStr;

					_list.bulletinBoardPic && (_list.bulletinBoardPic = imgCache(_list.bulletinBoardPic, 'goods_detail_bullet'));
					_list.brandRecommendMapList && _list.brandRecommendMapList.length && (_list.brandRecommendMapList[0].entryPic = imgCache(_list.brandRecommendMapList[0].entryPic, 'goods_detail_brand'));
					(_list.activityType == 7 || _list.activityType == 8) && goods_detail.find('header .share').addClass('hide');

					var t = {
						c: 1e3 * _list.currentTime,
						b: 1e3 * _list.activityBeginTime,
						e: 1e3 * _list.activityEndTime,
					};

					_list.productAvgScore && (_list.productAvgScore = _list.productAvgScore.toFixed(2));
					_list.mchtAvgScore && (_list.mchtAvgScore = _list.mchtAvgScore.toFixed(2));
					_list.wuliuAvgScore && (_list.wuliuAvgScore = _list.wuliuAvgScore.toFixed(2));

					_list.dataMap.novaPlanAmountRuleH5 && (_list.dataMap.novaPlanAmountRuleH5 = _list.dataMap.novaPlanAmountRuleH5.split("charset=UTF-8' >")[1].split("</BODY>")[0]);

					// 审核通过的预售状态
					if (_type == 11) {
						var _tb = new Date(t.b),
							_te = new Date(t.e);
						_td.preSell = 1;

						_list.preSellBeginTimeShort = famateNumber(1 + _tb.getMonth()) + '.' +
							famateNumber(_tb.getDate()) + ' ' +
							famateNumber(_tb.getHours()) + ':' +
							famateNumber(_tb.getMinutes());

						_list.preSellBeginTime = _tb.getFullYear() + '.' + _list.preSellBeginTimeShort;

						_list.preSellEndTime = famateNumber(1 + _te.getMonth()) + '.' +
							famateNumber(_te.getDate()) + ' ' +
							famateNumber(_te.getHours()) + ':' +
							famateNumber(_te.getMinutes());
							if(_te.getFullYear()!=_tb.getFullYear()){
							_list.preSellEndTime = _te.getFullYear() + '.' + _list.preSellEndTime;
							}

					}

					!_list.productCouponMap && (_list.productCouponMap = {});

					_list.isExist && goods_detail.find('.collection').addClass('reminded');
					_list.picMapList = imgCachePic(_list.picMapList, 'goods_detail_tpl');
					goods_detail_con.html(template('goods_detail_tpl', _list));

					goods_detail_start.html(template('goods_detail_start_tpl', data_map));

					if (_list.productCouponMap.isHasProductCoupon) {
						goods_detail.find('.red-envelopes').removeClass('hide')
					}

					// 领取当前商品的优惠卷
					if (_list.productCouponMap.isHasProductCoupon && _list.productCouponMap.productCouponList.length) {
						goods_detail_coupon.html(template('goods_detail_coupon_con_tpl', _list.productCouponMap));
						goods_detail_coupon_title.html(_list.productCouponMap.tips);
					}

					_list.productUrl && $.ajax({
						method: 'get',
						url: lead_url + 'views/activity/templet/brand_decorate.html',
						datatype: 'html',
						success: function (data) {
							setStorage('decorate_infoid', _list.productUrl.split('?infoId=')[1]);
							setStorage('decorate_areaid', false);
							setStorage('decorate_nest', 1);

							data = data
								.replace('<header class="flex', '<header class="flex hide')
								.replace('page flex dc activity-single-day', 'activity-single-day')
								.replace(' data-id="activity_templet_brand_decorate"', '');

							goods_detail_con.find('.nest-decorate-type3').html(data);
						}
					});

					if (_list.activityType != '101' && _list.activityType != 7 && _list.activityType != 8 || data_map.hasNotStartActivity) {
						var head_time = goods_detail_con.find('.con-price .count-time'),
							foot_reds = goods_detail_con.find('.footer .btn-reds');

						require(['qpTime'], function () {
							if (t.c > t.e) {
								foot_reds.html('已结束').addClass('btn-gray').removeAttr('onclick');
								head_time.html('已结束');
							} else if (t.c >= t.b) {
								if (t.e - t.c > 998) {
									$.qpTime({
										s: t.c,
										e: t.e
									}, function (pt) {
										if (pt.t) {
											head_time.html('距结束 ' + pt.d + '天' + pt.h + '时' + pt.m + '分' + pt.s + '秒');
										} else {
											setTimeout(function () {
												foot_reds.html('已结束').addClass('btn-gray').removeAttr('onclick');
											}, 1e3);
										}
									});
								}

								var _nb = new Date(t.b),
									_ne = new Date(t.e);

								goods_detail_con.find('.act-time').html('活动时间：'
									+ (1 + _nb.getMonth()) + '-' + _nb.getDate() + ' ' + _nb.getHours() + ':' + famateNumber(_nb.getMinutes()) + ' 至 '
									+ (1 + _ne.getMonth()) + '-' + _ne.getDate() + ' ' + _ne.getHours() + ':' + famateNumber(_ne.getMinutes()));
							} else {
								if (t.b - t.c > 998) {
									$.qpTime({
										s: t.c,
										e: t.b
									}, function (pt) {
										if (pt.t) {
											pt.d == '00' && pt.h == '00' && parseInt(pt.m) < 5 && foot_reds.html('即将开抢(' + (60 * parseInt(pt.m) + parseInt(pt.s)) + 's)');
											head_time.html('距开始 ' + pt.d + '天' + pt.h + '时' + pt.m + '分' + pt.s + '秒');
										} else {
											new ajax(getApi.productInfo, {
												data: {
													memberId: member.id,
													id: _td.id
												},
												success: function (es) {
													var _lists = es.returnData;

													_lists && _lists.dataMap ? location.reload() : foot_reds.html('已结束').addClass('btn-gray').removeAttr('onclick');
												}
											});
										}
									});
								}
							}
						});
					}

					if ((_list.activityType == 7 || _list.activityType == 8) && _list.cutPriceStatus == 1 && t.c >= t.b && t.c <= t.e) {
						var foot_time = goods_detail_con.find('.footer .count-time');

						if (t.e - t.c > 998) {
							require(['qpTime'], function () {
								$.qpTime({
									s: t.c,
									e: t.e
								}, function (pt) {
									if (pt.t) {
										foot_time.html(pt.h + ':' + pt.m + ':' + pt.s);
									} else {
										setTimeout(function () {
											location.reload();
										}, 1e3);
									}
								});
							});
						}

						goods_detail_share.title = '我在砍价免费拿' + _list.dataMap.name;
						goods_detail_share.content = '我正在参加醒购官方砍价活动，帮我砍砍，砍到0元就能免费领取';
						goods_detail_share.url = real_url.replace('m.xgbuy.cc', 'm.xinggoubuy.com').split(redirect_url)[0] + redirect_url + 'activity/' + (_list.activityType == 7 ? 'cut' : 'free') + 'price/share.html?cutOrderId=' + _list.cutOrderId;
						goods_detail_share.pictureUrl = _list.dataMap.skuPic;
					}

					// 助力大减价
					if (_list.activityType == 10) {
						_td.maxInviteTimes = _list.maxInviteTimes;
						_td.fixedAmount = _list.fixedAmount;
						_td.skuPic = _list.dataMap.skuPic;

						_td.cutOrderId && updateReduceShare(true);

						if (_list.cutPriceStatus == 1) {
							var countDown = goods_detail_con.find('.reduction-rule .count-time');
							var t = {
								c: 1e3 * _list.currentTime,
								b: _list.taskBeginDate,
								e: _list.taskEndDate,
							};

							if (t.e - t.c > 998) {
								require(['qpTime'], function () {
									$.qpTime({
										s: t.c,
										e: t.e
									}, function (pt) {
										if (pt.t) {
											countDown.html(pt.h + ':' + pt.m + ':' + pt.s);
										} else {
											setTimeout(function () {
												location.reload();
											}, 1e3);
										}
									});
								});
							}
						}
					}
					var _swiper = {
						autoplay: null,
						loop:true,
						container: $('.page:visible .swiper-container'),
						pagination: {
							el: '.pagination',
							type: 'fraction',
						}
					}
					if ($(".page:visible .swiper-slide video").length > 0) {
						_swiper.autoplay = false
					}
					// 幻灯片
					initSwiper(_swiper);

					// 选择规格
					infoInit(goods_detail.find('.info'), _list);

					// 产品参数
					if (_list.productParams) {
						_list.productParams = _list.productParams.split('<BODY>')[1].split('</BODY>')[0];
						goods_detail.find('.popup-parameter .con-1').html(_list.productParams);
						goods_detail.find('.parameter').show();
					} else {
						goods_detail.find('.parameter').hide();
					}

					// 增减
					inputNumber(goods_detail.find('.input-number input'), function (_input) {
						editGoodsDetail(1, _input);
					}, function (_input) {
						editGoodsDetail(-1, _input);
					});

					// 获取购物车件数
					// getCartPiece(function (piece) {
					// 	piece && $('#goods_detail_piece').append(piece);
					// });

					// 客服数据
					_td.abs = data_map.name;
					_td.label = '￥' + data_map.salePrice;
					_td.thumb = data_map.skuPic_S;

					// 分享
					var _scroll = goods_detail.find('.scroll-more'),
						_share = _scroll.find('.share'),
						_shares = goods_detail.find('header .share'),
						_collection = goods_detail.find('header .collection');

					_share.length && _scroll.scroll(function () {
						if ($(this).scrollTop() >= _share.offset().top + _share.height()) {
							_shares.hasClass('s') && _shares.stop().fadeIn();
							_shares.removeClass('s');
							_collection.hasClass('s') && _collection.stop().fadeIn();
							_collection.removeClass('s');
						} else {
							!_shares.hasClass('s') && _shares.stop().fadeOut();
							_shares.addClass('s');
							!_collection.hasClass('s') && _collection.stop().fadeOut();
							_collection.addClass('s');
						}
					});
					//足迹
					if(_type ==0 || _type == 6){
						if(member.id && member.token && member.sys == 'android' || member.id && member.token && member.sys == 'ios'){
							new ajax(getApi.appLogin, {
								data:{
									memberId: member.id,
									token:member.token
								},
								success:function (res) {
									new ajax(getApi.addFootPrint, {
										data: {
											memberId: member.id,
											type: 1,
											productId: _td.id
										},
										disLogin: 1
									});
								},
								error:function (res) {
									alert('请求失败')
								}
							})
						}else if (member.id && member.sys == 'web') {
							new ajax(getApi.addFootPrint, {
								data: {
									memberId: member.id,
									type: 1,
									productId: _td.id
								},
								disLogin: 1
							});
						}
					}
					

					// 水印
					wkPicFix();

					// 运费
					goods_detail_freight = goods_detail_con.find('.con-freight');
					goods_detail_freight_con = goods_detail_con.find('.popup-freight .scroll-y');
					getFreight();

					// 红包
					goods_detail_red = goods_detail_con.find('.red-envelopes');

					if (goods_detail_red.length) {
						var t_start,
							t_move,
							t_end = {
								x: 0,
								y: 0
							};

						var t_l = goods_detail_red.offset().left,
							t_t = goods_detail_red.offset().top,
							t_x = win_w - goods_detail_red.width() - t_l,
							t_y = win_h - t_t - goods_detail_red.height() - goods_detail.find('.footer').height(),
							_t_x = -t_l,
							_t_y = goods_detail.find('header').height() - t_t;

						goods_detail_red.tapstart(function (evt) {
							(isiOS || isAndroid) && (evt = evt.targetTouches[0]);

							t_start = {
								x: evt.pageX,
								y: evt.pageY
							};
						}).tapend(function () {
							t_start = 0;
							t_move ? (t_end = t_move, t_move = 0) : popupShow('.popup-coupon');
						});

						goods_detail.tapmove(function (evt) {
							if (t_start) {
								(isiOS || isAndroid) && (evt = evt.targetTouches[0]);

								t_move = {
									x: evt.pageX - t_start.x + t_end.x,
									y: evt.pageY - t_start.y + t_end.y
								};

								t_move.x > t_x && (t_move.x = t_x);
								t_move.x < _t_x && (t_move.x = _t_x);
								t_move.y > t_y && (t_move.y = t_y);
								t_move.y < _t_y && (t_move.y = _t_y);

								goods_detail_red.css('transform', 'translate3d(' + t_move.x + 'px, ' + t_move.y + 'px, 0)');
							}
						});
					}
				}

				shareHand.update(goods_detail_share);
				scrollFix();
			}
		});
	},

	getCoupon: function (e, _s) {
		!$(_s).hasClass('coupon-gray') && new ajax(getApi.addReceiveCoupon, {
			data: {
				memberId: member.id,
				couponId: e
			},
			success: function (res) {
				update_cart = 1;
				delayTip(res.returnData.content);
			},
		});
	},

	scroll: function (_this) {
		var _ts = this,
			_td = _ts.data;

		_td.more && (_this.scrollTop + _this.offsetHeight + 10) > _this.scrollHeight && _ts.detail(goods_detail.find('.pull-more'));
		_this.scrollTop > $(_this).height() ? scrollHand.show($(_this)) : scrollHand.hide($(_this));
		var vs = $("video")
		for(var i=0;i<vs.length;i++){
			if ((700 < vs.eq(i).offset().top || vs.eq(i).offset().top < -500) && vs[i].currentTime != 0) {
				vs[i].load();
			}
		}

	},

	detail: function (_this) {
		var _ts = this,
			_td = _ts.data;

		if (!_td.more) return;

		_td.more = 0;
		_this = $(_this);

		new ajax("../../api/n/getProductDetailTemp", {
			data: {
				id: _td.id
			},
			success: function (res) {
				var d = res.returnData,
					_detail = goods_detail.find('.detail');
				var _dimg = (d.productDetailPics ? d.productDetailPics : null)
				var _dvideo = (d.productVideos ? d.productVideos : null)
				var _str = '<p style="width: 100%; padding: 0px;">'
				if (_dvideo) {
					for (var i = 0; i < _dvideo.length; i++) {
						// _str+='<div style="background: #000;text-align: center;height: 7.5rem;display: flex;"><video poster="'+ _dimg[0] + '" class="detail-video" x-webkit-airplay="true" x5-playsinline="true" playsinline="true" webkit-playsinline="true" preload="auto" controls="controls"  src="'+ _dvideo[i].videoUrl +'" type="video/mp4"></video></div>'
						_str += '<div style="background: #000;text-align: center;display: flex;position: relative;height:7.5rem" class=" detail-video"><video class="video-js" controls preload="auto" data-setup="{}"'+
							'x-webkit-airplay="true" x5-playsinline="true" playsinline="true" webkit-playsinline="true"'+
							'width="100%" height="7.5rem" poster=' + _dvideo[i].videoCover +'>'+
							'<source src=' + _dvideo[i].videoUrl +' type="video/mp4">'+
							'<p class="vjs-no-js">不支持H5播放</p>'+
						'</video></div>'
					}
				}
				if (_dimg) {
					for (var i = 0; i < _dimg.length; i++) {
						_str += '<img src="' + _dimg[i] + '">'
					}
				}
				_str += "</p>"
				_detail.html(_str);
				_detail.find('p:empty').remove();
				var myPlayerList = document.getElementsByClassName('detail-video');
				if(myPlayerList.length > 0){
					var player = myPlayerList[0].getElementsByTagName("video")[0]
					videojsRendering(player)
				}


				var _m = goods_detail_con.find('.scroll-more'),
					_a = _this.offset().top + _m.scrollTop() - _this.height(),
					_b = _detail.find('img');

				_m.addClass('detail-more-scroll');

				$.each(_b, function () {
					this.src = imgCache(this.src, 'goods_detail_more');
				});

				_b.length && _b.parent().not('div').css({
					'width': '100%',
					'padding': 0
				});

				setTimeout(function () {
					_this.hide();

					_m.animate({
						scrollTop: _a
					}, 300);

					setTimeout(function () {
						_m.removeClass('detail-more-scroll');
					}, 300);
				}, 300);
			},
			error: function () {
				_td.more = 1;
			}
		});
	}
};

getTpl(function () {
	goodsDetail.init();
});


// function listPlay(ts) {
	// window.objSwiper.autoplay.stop();
	// $(".vjs-big-play-button").hide();

	// var laywifi = getStorage("laywifi")
	// if(networkStr != "wifi" && !laywifi){
	// 	ts.pause()
	// 	layer.open({
	// 		title: [
	// 		  '温馨提示',
	// 		  'font-size:18px;height:51px;line-height:51px'
	// 		]
	// 		,className: 'popuo'
	// 		,content: '你正在使用移动数据网络，会产生流量费用，是否继续观看？'
	// 		,btn: ['继续播放', '取消']
	// 		,yes: function(){
	// 			ts.play()
	// 			$(".vjs-big-play-button").hide();
	// 			window.objSwiper.autoplay.start();
	// 			setStorage("laywifi", true)
	// 		}
	// 		,no: function(){
	// 			window.objSwiper.autoplay.start();

	// 		}
	// 	  });
	// }else{
	// 	ts.play()
	// 	$(".vjs-big-play-button").hide();
	// 	if(networkStr != "wifi"){
	// 		layer.open({
	// 			content: '当前非Wi-Fi播放，请注意流量消耗！'
	// 			,skin: 'msg'
	// 			,time: 2 //2秒后自动关闭
	// 		  });
	// 	}
	// }
// }


function onShow() {
	traffic.setPv(goodsDetail.data);

	if (update_cart) {
		update_cart = 0;

		$('.page[data-id="cart"]:visible').length && getUrl('', 'self');
	}
}


// 更新大减价分享
function updateReduceShare(param) {
	if (param) {
		goods_detail_share = {
			content: "我在醒购参加助力大减价活动，快来帮我助力吧！",
			pictureUrl: goodsDetail.data.skuPic,
			title: "快来帮我减价，最低1折抢大牌",
			url: real_url.split(redirect_url)[0] + redirect_url + 'activity/reduceprice/share/index.html?cutOrderId=' +
				goodsDetail.data.cutOrderId + '&sourceMemberId=' + member.id
		}
	} else {
		getMember(function () {
			goods_detail_share = {
				content: "我在醒购参加助力大减价活动，快来帮我助力吧！",
				pictureUrl: goodsDetail.data.skuPic,
				title: "快来帮我减价，最低1折抢大牌",
				url: real_url.split(redirect_url)[0] + redirect_url + 'activity/reduceprice/share/index.html?cutOrderId=' +
					goodsDetail.data.cutOrderId + '&sourceMemberId=' + member.id
			}
		});
	}

	goods_detail_share_sp1.ext = {
		activityType: 10,
		id: goodsDetail.data.cutOrderId
	};
}

// 选择规格
function infoInit(info, _list) {
	var info_load = true,
		info_length = _list.standardMap.length,
		info_ids = _list.dataMap.id,
		info_deposit = _list.dataMap.deposit,
		info_deductAmount = _list.dataMap.deductAmount,

		info_index_arr = new Array(info_length), // 存储选中的索引
		info_propValIds_arr = new Array(info_length); // 存储选中的规格

	$.each(info, function () {
		var info_li = $(this).find('li'),
			info_div = $(this).find('.info-list'),
			info_propIds = [];// 商品的所有大规格id

		$.each(info_div, function () {
			info_propIds.push($(this).data('id'));
		});

		info_propIds = info_propIds.join(',');

		info_li.click(function () {
			if (!info_load) {
				return;
			}
			info_load = false;

			var info_propValIds = [], // 选中的规格集合
				info_index = $(this).closest('div').index(); // 当前模块索引

			if ($(this).hasClass('no')) {
				info_load = true;
				return;
			}

			if ($(this).hasClass('on')) {
				info_index_arr[info_index] = undefined;
				info_propValIds_arr[info_index] = undefined;
				info_index = undefined;
			} else {
				info_index_arr[info_index] = $(this).index();

				for (var i = 0; i < info_index_arr.length; i++) {
					info_propValIds_arr[i] = info_index_arr[i] !== undefined ? info_div.eq(i).find('li').eq(info_index_arr[i]).data('propvalid') : undefined;
				}
			}

			for (var i = 0; i < info_propValIds_arr.length; i++) {
				info_propValIds_arr[i] !== undefined && info_propValIds.push(info_propValIds_arr[i]);
			}
			info_propValIds = info_propValIds.join(',');

			$.each(info, function () {
				for (var i = 0; i < info_index_arr.length; i++) {
					var j = $(this).find('.info-list').eq(i).find('li');
					j.removeClass('on');

					info_index_arr[i] !== undefined && j.eq(info_index_arr[i]).addClass('on');
				}
			});

			new ajax(getApi.productItems, {
				data: {
					id: info_ids,
					propIds: info_propIds,
					propValIds: info_propValIds
				},
				success: function (data) {
					var _list = data.returnData,
						data_list = _list.standardMapList,
						_ddp = goods_detail.find('.popup-add-cart dd p'),
						_ddps = goods_detail_con.find('.con.info'),
						_ons = []; // 存储所有选中规格

					if (_list.svipSalePrice) {
						goods_detail.find('.svip-money').empty().html('￥' + _list.svipSalePrice);
						goods_detail.find('.svip-content').empty().html(_list.svipContent);
						goods_detail.find('.shop-seller').empty().html(_list.shopwnerEquityAmount);
						goods_detail.find('.seller-content').empty().html(_list.shopwnerEquityStr);
					}
					goods_detail.find('.pre-sell-price span').eq(0).html('原售价 ￥' + _list.salePrice);
					goods_detail.find('.pre-arrival-price').html((_list.salePrice - info_deductAmount + info_deposit).toFixed(1));

					if (info_index !== undefined) {
						$.each(info_div.eq(info_index).find('li'), function () {
							!$(this).hasClass('no') && $(this).addClass('yes');
						});
					}

					info_li.removeClass('no');
					_ddps.find('.info-color-choice').html('');

					for (var i = 0; i < data_list.length; i++) {
						if (i !== info_index) {
							var info_div_lis = info_div.eq(i).find('li');
							var data_list_lis = data_list[i].propValueMapList;

							$.each(info_div_lis, function () {
								for (var j = 0; j < data_list_lis.length; j++) {
									$(this).data('propvalid') === data_list_lis[j].propValId && $(this).addClass('yes');
								}
							});
						}
					}

					$.each(info_li, function () {
						if ($(this).hasClass('on')) {
							_ons.push($(this).text());
							$(this).hasClass('fn') && goods_detail.find('.info-color-choice').html($(this).text());
						}
						$(this).hasClass('yes') ? $(this).removeClass('yes') : $(this).addClass('no');
					});

					if (_ons.length) {
						_ons = _ons.join('、');
						_ddp.find('span').html(_ons);
						_ddps.find('.info-lists').html('已选择 ' + _ons).removeClass('hide').siblings('div').addClass('hide');
						_ddp.removeClass('hide');
						goodsDetail.data.sku = _list.skuId;
					} else {
						_ddp.addClass('hide');
						_ddps.find('.info-lists').addClass('hide').siblings('div').removeClass('hide');
						goodsDetail.data.sku = '';
					}

					_list.skuPic_S = imgCache(_list.skuPic_S, 'goods_detail_skus');
					_list.skuPic = imgCache(_list.skuPic, 'goods_detail_sku');

					goods_detail.find('.popup-add-cart img').attr('src', _list.skuPic_S);
					goods_detail.find('.popup-add-pic img').attr('src', _list.skuPic);

					goods_detail_con.find('.sale-price').text(_list.salePrice);
					goods_detail_con.find('.con-price s').text(_list.tagPrice);

					if (goodsDetail.data.sku && $('.goods-detail2 .info-color ul').find('.on').length) {
						goods_detail.find('.popup-info-color img').attr('src', _list.skuPic);
						goods_detail.find('.popup-info-color').show();
					} else {
						goods_detail.find('.popup-info-color').hide();
					}
				},
				complete: function () {
					info_load = true;
				}
			});
		});
	});
}

function goodsDetailSubmit(_this, _cut) {
	defSelf();

	if (!goodsDetail.data.data) return;

	var that = $(_this),
		_quantity = goods_detail.find('.popup-add-cart .input-number input').val(),
		_info = that.siblings('.popup-con').find('.info-list'),
		_act = that.data('act');

	if (_info.length) {
		for (var i = 0; i < _info.length; i++) {
			if (!_info.eq(i).find('.on').length) {
				delayTip('请选择商品' + _info.eq(i).find('h3').text());
				return;
			}
		}
	}

	if (_cut) {
		goodsDetailPopupAddr();
		return;
	}

	var _client = '';

	isAndroid && (_client = 2);
	isiOS && (_client = 1);
	// currentTime < activityBeginTime
	if (goodsDetail.data.preSell && !that.data('begintime')) {
		setStorage('order_firmpre', JSON.stringify({
			productId: goodsDetail.data.id,
			quantity: _quantity,
			productItemId: goodsDetail.data.sku
		}));

		getUrl('order/firmpre/index.html', 'self');
		return;
	}

	new ajax(getApi.addShoppingCart, {
		data: {
			memberId: member.id,
			productId: goodsDetail.data.id,
			productItemId: goodsDetail.data.sku,
			quantity: _quantity,
			activityId: that.data('act'),
			activityType: that.data('typ'),
			singleProductActivityId: that.data('id')
		},
		success: function (e) {
			var _list = e.returnData;

			if (goodsDetail.data.sec) {
				setStorage('cart_detail', JSON.stringify({
					shopCardIds: _list,
					payAmount: _quantity * goodsDetail.data.data.salePrice,
					nums: _quantity * goodsDetail.data.data.salePrice,
					coup: 0
				}));

				setStorage('cart_pay', JSON.stringify({
					sourceClient: _client,
					mermberPlatformCouponId: '',
					shopCardIds: _list,
					payAmount: _quantity * goodsDetail.data.data.salePrice,
					dataList: []
				}));
				traffic.setAction(goodsDetail.data, {
					type: 15,
					id: _list
				});
				getUrl('order/firm/index.html', 'self');
			} else {
				// removeStorage('cart_piece');

				// getCartPiece(function (piece) {
				// 	piece && $('#goods_detail_piece').append(piece);
				// });

				goods_detail.find('.popup-add-cart').removeClass('show');
				$('.page:visible .scroll-more').css('pointer-events', 'auto');

				goods_detail.find('.popup-add-cart').hasClass('add-now') ? getUrl('cart?buyNow=' + _list, 'self') : delayTip('已添加，别把我忘了哦');

				traffic.setAction(goodsDetail.data, {
					type: 14,
					id: _list
				});
				// traffic.setAction(goodsDetail.data, {
				// 	type: 4,
				// 	id: goodsDetail.data.id
				// });
			}
		}
	});
}

function goodsDetailAddNow() {
	popupShow('.popup-add-cart');
	goods_detail.find('.popup-add-cart').addClass('add-now');
}

function editGoodsDetail(_quantity, elem) {
	var _val = Number(elem.val());

	if (_quantity > 0) {
		_val++;
		elem.val(_val);
	} else {
		_val--;
		elem.val(_val);
	}
}

// 停用领取优惠券
function getCoupons(_act) {
	new ajax(getApi.activityCoupon, {
		data: {
			memberId: member.id,
			activityAreaId: _act
		},
		success: function (res) {
			var _list = res.returnData;

			if (_list.length) {
				member.list = _list;

				require(['template'], function (template) {
					$('.page:visible .coupon-list').html(template('goods_detail_coupon_tpl', res));
					popupShow('.popup-coupons');
				});
			} else {
				delayTip(res.returnMsg);
			}
		}
	});
}

// 请选择收货地址
var goods_detail_addr_scroll = '';

function goodsDetailPopupAddr(that) {
	if (goods_detail_addr_scroll && goods_detail_addr_scroll.hasClass('has-data')) {
		popupShow('.popup-addr');
	} else {
		goods_detail_addr_scroll = goods_detail_con.find('.popup-addr ul');
		goodsDetailPopupAddrPost();
	}
}

function goodsDetailPopupAddrPost(_sl, _ad) {
	_ad && goods_detail_addr_scroll.scrollTop(0).removeClass('has-data').removeAttr('data-page').removeAttr('data-old');

	require(['template'], function (template) {
		new ajax(member.app ? getApi.getMemberAddressListH5 : getApi.getMemberAddressList, {
			data: {
				memberId: member.id,
				currentPage: goods_detail_addr_scroll.attr('data-page') || 0,
				pageSize: 10
			},
			success: function (res) {
				var _list = res.returnData,
					_len = _list.length;

				if (_sl) {
					if (_len) {
						var _cur = goods_detail_addr_scroll.attr('data-page');
						_cur++;
						goods_detail_addr_scroll.attr('data-page', _cur);
						goods_detail_addr_scroll.append(template('goods_detail_addr_tpl', res));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					goods_detail_addr_scroll.html(template('goods_detail_addr_tpl', res)).addClass('has-data');
					popupShow('.popup-addr');

					_len == 10 && scrollHand.init(goods_detail_addr_scroll, function () {
						goodsDetailPopupAddrPost(true);
					});
				}
			}
		});
	});
}

// 添加收货地址
var goods_detail_addr_api = {
	addressId: '',
	receiverName: '',
	receiverPhone: '',
	receiverAddress: ''
};

function goodsDetailAddAddr() {
	setStorage('goods_detail_address', 1);
	getUrl('address/edit/index.html', 'self');
}

function goodsDetailAddCart() {
	if (!goodsDetail.data.mobileVerfiyFlag) {
		getUrl('register/mobilePwd/index.html', 'self');
		return;
	}

	goodsDetail.data.standard.length ? popupShow('.popup-add-cart') : goodsDetailPopupAddr();
}

// 确认收货地址
function goodsDetailAddrCheck(_this) {
	var _str = [],
		_div = $(_this).find('div'),
		_span = _div.find('span'),
		_p = _div.find('p');

	goods_detail_addr_api.addressId = _div.data('id');
	goods_detail_addr_api.receiverName = _span.eq(0).text();
	goods_detail_addr_api.receiverPhone = _span.eq(1).text();
	goods_detail_addr_api.receiverAddress = _p.text();

	_str.push(goods_detail_addr_api.receiverName);
	_str.push(goods_detail_addr_api.receiverPhone);
	_str.push(goods_detail_addr_api.receiverAddress);

	_str = _str.join('，');

	popupShow('.popup-addr-check', null, function (e) {
		e.find('.txt').html(_str);
	});
}

// 确认砍价
function goodsDetailCutOrder(_id, _type) {
	getMember(function () {
		var _cli = 3;

		if (isWeiXin) {
			_cli = 4;
		} else if (member.sys == 'ios') {
			_cli = 1;
		} else if (member.sys == 'android') {
			_cli = 2;
		}

		new ajax(member.app ? getApi.addMemberCutOrderInfoH5 : getApi.addMemberCutOrderInfo, {
			data: {
				type: _type,
				memberId: member.id,
				cutOrderId: '',
				productId: _id,
				productItemId: goodsDetail.data.sku,
				addressId: goods_detail_addr_api.addressId,
				receiverName: goods_detail_addr_api.receiverName,
				receiverPhone: goods_detail_addr_api.receiverPhone,
				receiverAddress: goods_detail_addr_api.receiverAddress,
				sourceClient: _cli
			},
			success: function (res) {
				var _list = res.returnData;
				closeSelf('.popup');

				if (_type == 7) {
					getUrl('activity/cutprice/share/index.html?share=2&cutOrderId=' + _list.cutOrderId, true);
				} else if (_type == 8) {
					getUrl('activity/freeprice/share/index.html?&cutOrderId=' + _list.cutOrderId, true);
				} else if (_type == 10) {
					goodsDetail.data.cutOrderId = _list.cutOrderId
					updateReduceShare(false);
					// goodsDetailShare(true);
					iosAndroidShare(member.id,goodsDetail.data.cutOrderId,goodsDetail.data.id);
					new ajax(getApi.updateProdutStock, {
						data: {
							activityType: 10,
							id: goodsDetail.data.cutOrderId
						},
						success: function () {
						}
					});
				}
			}
		});
	});
}

// 分享
function goodsDetailShare(_wx) {
console.log(_wx)
	if (goods_detail_share.title) {
		$('.page:visible .popup-share').remove();

		shareHand.update(goods_detail_share, {
			success: function () {
				goods_coupon_param.activityType == '10' ? getUrl('', 'self') : closeSelf('.popup');
			}
		});

		shareHand.show({
			wx: _wx,
			// title: goods_coupon_param.activityType == '10' ? ('<div' + (member.app ? ' style="font-size: 17px; text-align: center;"' : '') + '>邀请<span style="color: #ff5050;">' + goodsDetail.data.maxInviteTimes + '位</span>APP新用户立减<span style="color: #ff5050;">' + (goodsDetail.data.fixedAmount * goodsDetail.data.maxInviteTimes) + '元</span></div>') : '分享到',
			title: goods_coupon_param.activityType == '10' ?('<span' + (member.app ? ' style="font-size: 17px;text-align: center;display:block;"' : '') + '>邀请<font color="#ff5050">' + goodsDetail.data.maxInviteTimes + '位</font>APP新用户立减<font color="#ff5050">' + (goodsDetail.data.fixedAmount * goodsDetail.data.maxInviteTimes) + '元</font></span>'): '分享到',

			sp1: goods_detail_share_sp1.sp1,
			ext: goods_detail_share_sp1.ext
		});

		traffic.setAction(goodsDetail.data, {
			type: 2,
			id: goodsDetail.data.id
		});
	}
}

// 查看全部评价
function getEvaluaProduct(_id) {
	setStorage('evalua_product', JSON.stringify({
		title: goods_detail_share.title,
		content: goods_detail_share.content,
		url: goods_detail_share.url,
		pictureUrl: goods_detail_share.pictureUrl
	}));

	getUrl('evalua/product/index.html?productId=' + _id, 'self');
}

// 运费
function tapFreight(_this) {
	var ts = $(_this);

	removeStorage('province');
	goods_freight_province.provinceId = ts.data('id');

	getFreight(ts.data('name'), function () {
		ts.addClass('active').siblings().removeClass('active');
	});
}

function getFreight(_name, _f) {
	var _p = getStorage('province');

	if (_p) {
		_p = JSON.parse(_p);
		goods_freight_province = _p;
	}

	new ajax(getApi.getProductFreightTemplate, {
		data: {
			productId: goodsDetail.data.id,
			provinceId: goods_freight_province.provinceId
		},
		success: function (e) {
			var _list = e.returnData;

			goods_freight_province.provinceName = _name || goods_freight_province.provinceName || _list.provinceName;
			goods_freight_province.freight = _list.freight;

			require(['template'], function (template) {
				goods_detail_freight.html(template('goods_detail_freight_tpl', goods_freight_province));
				closeSelf('.popup-freight');
				setStorage('province', JSON.stringify(goods_freight_province));
				_f && _f();
			});
		}
	});
}

function showFreight() {
	if (goods_detail_freight_con.html()) {
		popupShow('.popup-freight');
	} else {
		new ajax(getApi.getAreaByParentId, {
			data: {
				parentId: 0
			},
			success: function (res) {
				require(['template'], function (template) {
					goods_detail_freight_con.html(template('goods_detail_freight_con_tpl', res));
					popupShow('.popup-freight');
				});
			}
		});
	}
}

function tapByPreferential() {
	var _type = goods_coupon_param.activityType;

	if (_type == 0) {
		if (goods_coupon_param.source == 1) {
			new ajax(getApi.areaTempletByAreaId, {
				data: {
					activityAreaId: goods_coupon_param.activityAreaId,
				},
				success: function (res) {
					getMapUrlType1(res.returnData.areaUrl);
				}
			});
		} else if (goods_coupon_param.source == 2) {
			getUrlActiveSingle1(goods_coupon_param.activityId);
		}
	} else if (_type == 1) {
		// 新人专享
		if (member.sys == 'android') {
			intentNewUserShare();
		} else if (member.sys == 'ios') {
			callIntentTrailer(_native, 'intentNewUserShare');
		} else {
			getUrl('newman/index.html?type=1', 'self');
		}
	} else if (_type == 2) {
		// 爆款
		if (member.sys == 'android') {
			intentSingleExplo();
		} else if (member.sys == 'ios') {
			callIntentTrailer(_native, 'intentSingleExplo');
		} else {
			getUrl('activity/templet/single_explosion.html?type=2', 'self');
		}
	} else if (_type == 3) {
		// 抢购
		if (member.sys == 'android') {
			intentSeckill();
		} else if (member.sys == 'ios') {
			callIntentTrailer(_native, 'intentSeckill');
		} else {
			getUrl('rushbuy/index.html?type=3', 'self');
		}
	} else if (_type == 4) {
		// 秒杀
		if (member.sys == 'android') {
			intentNewUserSeckill();
		} else if (member.sys == 'ios') {
			callIntentTrailer(_native, 'intentNewUserSeckill');
		} else {
			getUrl('newman/seckill/index.html');
		}
	} else if (_type == 5) {
		// 积分
		if (member.sys == 'android') {
			intentIntegralMall();
		} else if (member.sys == 'ios') {
			callIntentTrailer(_native, 'intentIntegralMall');
		} else {
			getUrl('integral/detail/index.html');
		}
	} else if (_type == 6) {
		// 断码清仓
		if (member.sys == 'android') {
			intentFaultCode2();
		} else if (member.sys == 'ios') {
			callIntentTrailer(_native, 'intentFaultCode2');
		} else {
			getUrl('activity/templet/single_duanma', 'self');
		}
	} else if (_type == 7) {
		// 砍价
		var _url1 = real_url.split(redirect_url)[0] + redirect_url + 'activity/cutprice/index.html';

		if (member.sys == 'ios') {
			callIntentTrailer(_native, 'JumpWebView', { 'url': _url1 });
		} else {
			getUrl(_url1, 'self');
		}
	} else if (_type == 8) {
		// 免单
		var _url1 = real_url.split(redirect_url)[0] + redirect_url + 'activity/freeprice/index.html';

		if (member.sys == 'ios') {
			callIntentTrailer(_native, 'JumpWebView', { 'url': _url1 });
		} else {
			getUrl(_url1, 'self');
		}
	} else if (_type == 101) {
		// 店铺
		getUrlShop(goods_coupon_param.mchtId);
	}
}

function joinActivity(id, type, _sp1) {
	if (member.sys == 'ios') {
		checkAppVer(53, function () {
			checkJoinActivity(id, type, _sp1);
		});
	} else if (member.sys == 'android') {
		checkAppVer(50, function () {
			checkJoinActivity(id, type, _sp1);
		});
	} else {
		checkJoinActivity(id, type, _sp1);
	}
}

// 参加助力减价活动
function checkJoinActivity(id, type, _sp1) {
	goodsDetail.data.standard.length ? popupShow('.popup-add-cart') : goodsDetailCutOrder(id, type);
	goods_detail_share_sp1.sp1 = _sp1;
}

// 参加助力减价活动如果有SKU选择完后提交
function joinActivitySubmit(_this, id, type) {
	// 判断SKU是否选择
	var that = $(_this), _info = that.siblings('.popup-con').find('.info-list');
	if (_info.length) {
		for (var i = 0; i < _info.length; i++) {
			if (!_info.eq(i).find('.on').length) {
				delayTip('请选择商品' + _info.eq(i).find('h3').text());
				return;
			}
		}
	}
	// 请求接口
	goodsDetailCutOrder(id, type);
}

function jumpPurchase(_id, _item, _price, _cutOrderId) {
	if (member.sys == 'ios') {
		checkAppVer(53, function () {
			checkJumpPurchase(_id, _item, _price, _cutOrderId);
		});
	} else if (member.sys == 'android') {
		checkAppVer(50, function () {
			checkJumpPurchase(_id, _item, _price, _cutOrderId);
		});
	} else {
		checkJumpPurchase(_id, _item, _price, _cutOrderId);
	}
}

// 购买
function checkJumpPurchase(_id, _item, _price, _cutOrderId) {
	new ajax(getApi.addShoppingCart, {
		data: {
			memberId: member.id,
			productId: _id,
			productItemId: _item,
			quantity: 1,
			activityId: '',
			activityType: 10,
			singleProductActivityId: ''
		},
		success: function (e) {
			var _list = e.returnData,
				_client = '';

			isAndroid && (_client = 2);
			isiOS && (_client = 1);

			setStorage('cart_detail', JSON.stringify({
				shopCardIds: _list,
				payAmount: _price,
				nums: _price,
				coup: 0
			}));

			setStorage('cart_pay', JSON.stringify({
				cutOrderId: _cutOrderId,
				sourceClient: _client,
				mermberPlatformCouponId: '',
				payAmount: _price,
				shopCardIds: _list,
				dataList: []
			}));
			traffic.setAction(goodsDetail.data, {
				type: 15,
				id: _list
			});
			getUrl('order/firm/index.html', 'self');
		},
		limit: 1
	});
}

// 查看全部评价
function getEvaluaProduct(_id) {
	setStorage('evalua_product', JSON.stringify({
		title: goods_detail_share.title,
		content: goods_detail_share.content,
		url: goods_detail_share.url,
		pictureUrl: goods_detail_share.pictureUrl
	}));

	getUrl('evalua/product/index.html?productId=' + _id, 'self');
}

function getReminds(_this) {
	getRemind(_this, 0, function (_is) {
		var collection = goods_detail.find('.collection');

		if (_is) {
			collection.addClass('reminded');

			traffic.setAction(goodsDetail.data, {
				type: 3,
				id: goodsDetail.data.id
			});
		} else {
			collection.removeClass('reminded');
		}
	});
}

function transfer() {
	goods_detail.find('.collection-bt').click()
}

// 分润分享 
function goodsStarShare() {
	isWeiXin && goodsDetail.data.spreadAmountStr && goodsDetail.data.spreadAmountStr != 0 ? popupShow('.popup-detail-start') : goodsDetailShare();
	// isWeiXin && goodsDetail.data.spreadAmountStr && goodsDetail.data.spreadAmountStr != 0 ? popupShow('.popup-detail-start') : iosAndroidShare(member.id,goodsDetail.data.cutOrderId,goodsDetail.data.id);

}

// 继续邀请 
function nextJoinActivity() {
	goods_detail_share_sp1.sp1 = 2
	updateReduceShare(false);
	// goodsDetailShare(true);
	iosAndroidShare(member.id,goodsDetail.data.cutOrderId,goodsDetail.data.id);

}
function iosAndroidShare (memberId,cutOrderId,productId) {
	new ajax(getApi.getMyAssistanceCutPriceShareInfo, {
		data: {
			type: 10,
			memberId: memberId,
			cutOrderId: cutOrderId,
			productId: productId
		},
		success: function (e) {
			var res = e.returnData;
			shareHand.transShow({
				wx: 1,
				// title: '<div' + (member.app ? ' style="font-size: 17px;text-align: center;"' : '') + '>邀请<span style="color: #ff5050;">' + _maxInviteTimes + '位</span>APP新用户立减<span style="color: #ff5050;">' + (_fixedAmount * _maxInviteTimes) + '元</span></div>',
				title: '<span' + (member.app ? ' style="font-size: 17px;text-align: center;display:block;"' : '') + '>邀请<font color="#ff5050">' + 1000 + '位</font>APP新用户立减<font color="#ff5050">' + 60 + '元</font></span>',
				sp1: '1' || '0',
				"skuPic":res.skuPic,//商品图
				// "salePrice":res.salePrice,//商品价格
				"salePrice":res.cutLadderList.slice(res.cutLadderList.length - 1)[0].price,//商品价格
				"shareTitle":res.shareTitle,//分享标题
				"shareDesc":res.shareDesc,//分享的描述
				"sharePic":"",//分享的背景图
				"webpageUrl":res.webPageUrl,//分享跳转页面地址
				"wxPath":'page/activity/reducePrice/share/index?cutOrderId=' + res.cutOrderId + '&sourceMemberId=' + res.sourceMemberId,//小程序具体页面
				"xcxShareType":res.xcxShareType,//分享小程序的版本
				"originalId":res.originalId,//小程序醒购原始ID

			});

		}
	});
}
