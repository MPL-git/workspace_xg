var activity_reduceprice = $('.page[data-id="activity_reduceprice"]:visible'),
	reduceprice_tbtn = activity_reduceprice.find('.flex-btns'),
	reduceprice_con1 = activity_reduceprice.find('.reduceprice-1'),
	reduceprice_con2 = activity_reduceprice.find('.reduceprice-2'),
	reduceprice_tabcar = activity_reduceprice.find('.tab-car'),

	reduceprice_list1 = reduceprice_con1.find('.tab-list'),
	reduceprice_list2 = reduceprice_con2.find('.tab-list'),
	reduceprice_rule = activity_reduceprice.find("#reduceprice_rule")

	reduce_price_detail = activity_reduceprice.find('.popup-reduce-detail .popup-con');

var reduceprice_share_share = {
	title: '快来帮我减价，最低1折抢大牌',
	content: '我在醒购参加助力大减价活动，快来帮我助力吧！',
	pictureUrl: '',
	url: location.href
};

var reducePrice = {
	data: {
		time: 3600000 * 25, // 倒计1*25小时
		host: 'm.xinggoubuy.com',
		id: '',
		tab: 0,
		seven: false,
		propValIds: [],
		pag: 10,
		scroll: '',
		list: '',
		tpl: '',
		btn: '',
		ruletab: 0,
		_minPayAmount:'',
		_fixedAmount:''
	},

	ajax: {
		getInviteDetail: []
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '72'
		});
		reduceprice_rule.html(template('rule_tpl', _td));

		requestAppMember(function () {
			_ts.post(0);
			shareHand.update();
		}, function () {
			getApi.getMyAssistanceGoodsTaskList = getApi.getMyAssistanceGoodsTaskListH5;
			getApi.addMemberCutOrderInfo = getApi.addMemberCutOrderInfoH5;
			getApi.getInviteDetail = getApi.getInviteDetailH5;
		});
	},

	post: function (_tab, _scroll, fn) {
		var _ts = this,
			_td = _ts.data;

		if (_tab == 0) {
			_td.scroll = reduceprice_con1;
			_td.list = reduceprice_list1;
			_td.tpl = 'reduceprice_tpl_con1';
			_td.btn = reduceprice_tbtn.find('a').eq(0);
			_td.api = getApi.getBargainGoodsList;
			_td.tab = _tab;
			document.title = '助力省钱';
			$(".ellipsis-title").html('助力省钱');
			traffic.setPv(_td, {
				pvTab: 1,
				pageType: '73'
			});
		} else if (_tab == 1) {
			_td.scroll = reduceprice_con2;
			_td.list = reduceprice_list2;
			_td.tpl = 'reduceprice_tpl_con2';
			_td.btn = reduceprice_tbtn.find('a').eq(1);
			_td.api = getApi.getMyAssistanceGoodsTaskList;
			_td.tab = _tab;
			document.title = '我的邀请单';
			$(".ellipsis-title").html('我的邀请单');
			reduceprice_con2.removeAttr('data-page').removeClass('has-data');
			traffic.setPv(_td, {
				pvTab: 1,
				pageType: '74'
			});
			// _ts.post()
		}

		if (!_scroll && _td.scroll.hasClass('has-data')) {
			_ts.active(_td.scroll, _td.btn);
			return;
		}

		require(['qpTime'], function () {
			new ajax(_td.api, {
				data: {
					type: 10,
					memberId: member.id,
					currentPage: _td.scroll.attr('data-page') || 0
				},
				success: function (e) {
					if(_tab == 0){
						new ajax(getApi.getMyAssistanceGoodsTaskList, {
						data: {
							type: 10,
							memberId: member.id,
							currentPage: 0
						}
					})
					}
					var _list = e.returnData,
						_data = _list.dataList,
						_len = _data.length,
						_black = _list.isBlack;

					if (_black) {
						shield(_list.blackReason);
						return;
					}

					if (_len) {
						if (_tab == 1) {
							for (var i = 0; i < _len; i++) {
								var _di = _data[i];
								_di.timer = _di.endDate - _list.currentDate;
							}
						}
					}
					_list.dataList = imgCacheProductPic(_list.dataList, 'reduce_price_cache');
					if (_scroll) {
						if (_len) {
							var _cur = _td.scroll.attr('data-page');
							_cur++;
							_td.scroll.attr('data-page', _cur);
							_td.list.append(template(_td.tpl, _list));

						} else {
							delayTip('已经全部加载完毕');
						}
					} else {
						_td.list.html(template(_td.tpl, _list));
						_td.scroll.addClass('has-data');
						_ts.active(_td.scroll, _td.btn);
						_ts.timeRun();
						_len == _td.pag && scrollHand.init(_td.scroll, function () {
							_ts.post(_tab, true);
						}, true);
						fn && fn();
						scrollFix();
					}
				}
			});
		});
	},
	rulepost: function(a){
		var _ts = this,
			_td = _ts.data;
			
		_td.ruletab = a
		reduceprice_rule.html(template('rule_tpl', _td));
	},
	active: function (a, b) {
		a.removeClass('hide').siblings('.tab-con').addClass('hide');
		b.addClass('f50').siblings().removeClass('f50');
	},

	timer: function (e) {
		return famateNumber(e / 1e3 / 60 / 60) + ':' + famateNumber(e / 1e3 / 60 % 60) + ':' + famateNumber(e / 1e3 % 60);
	},

	timeRun: function (e) {
		var _ts = this,
			_td = _ts.data;
		$.each(reduceprice_list2.find('time'), function () {
			var $ts = $(this);
			$.qpTime({
				s: 1,
				e: $ts.data('timer')
			}, function (pt) {

				if($ts.data('tab') == 1){
					$ts.html(_ts.timer(pt.t)+' 后结束');
					// $('.continue').html('继续邀请');
					// $('.continue').addClass('continueInvitation');
					$ts.parent().next().children().next().children().next().children().next().html('继续邀请');
					$ts.parent().next().children().next().children().next().children().next().addClass('continueInvitation');
					if(pt.t <= 0){
						$(".buyEnd").removeClass('hide');
						$ts.html('')
						$ts.parent().next().children().next().children().next().children().next().html('立即购买');
						$ts.parent().next().children().next().children().next().children().next().addClass('rightawayBuy');
						$ts.parent().next().children().next().children().next().children().next().removeClass('continueInvitation');
					}
				}else if($ts.data('tab') == 2){
					$ts.html('助力已结束 '+_ts.timer(pt.t)+' 结束付款');
					if(pt.t <= 0){
						$ts.html('任务结束');
						$ts.parent().next().children().next().children().next().children().next().html('超时未付款');
						$ts.parent().next().children().next().children().next().children().next().removeClass('rightawayBuy');
						$ts.parent().next().children().next().children().next().children().next().removeClass('continueInvitation');
					}
				}else{
					$ts.html('助力已完成 '+_ts.timer(pt.t)+' 结束付款');
					if(pt.t <= 0){
						$ts.html('邀请已完成')
						$ts.parent().next().children().next().children().next().children().next().html('立即下单');
						$ts.parent().next().children().next().children().next().children().next().removeClass('rightawayBuy');
						$ts.parent().next().children().next().children().next().children().next().removeClass('continueInvitation');
					}
				}
				// if(pt.t == 0){
				// 	$ts.html('助力已结束');
				// 	$(".buyEnd").removeClass('hide')
				// 	$('.buyEnd').html(_ts.timer(pt.t)+' 结束付款')
				// }
			});
		});
	},

	getPrice: function (_productId, _fixedAmount, _maxInviteTimes, _minPayAmount) {
		var _ts = this,
			_td = _ts.data;
		_td.propValIds = [];
		_td._minPayAmount = _minPayAmount;
		_td._fixedAmount = _fixedAmount;
		if (member.sys == 'ios') {
			checkAppVer(53, function () {
				_ts.checkGetPrice(_productId, _fixedAmount, _maxInviteTimes, _minPayAmount);
			});
		} else if (member.sys == 'android') {
			checkAppVer(50, function () {
				_ts.checkGetPrice(_productId, _fixedAmount, _maxInviteTimes, _minPayAmount);
			});
		} else {
			_ts.checkGetPrice(_productId, _fixedAmount, _maxInviteTimes, _minPayAmount);
		}
	},

	// 减价拿
	checkGetPrice: function (_productId, _fixedAmount, _maxInviteTimes, _minPayAmount) {
		var _ts = this,
			_td = _ts.data;
		new ajax(getApi.getProductSkuInfo, {
			data: {
				memberId: member.id,
				id: _productId,
			},
			success: function (e) {
				var _list = e.returnData,
					standardMapList = _list.standardMapList,
					_pic = _list.pic,
					_productId = _list.productId;

				_td.fixedAmount = _fixedAmount;
				_td.maxInviteTimes = _maxInviteTimes;
				_td.standardMapList = standardMapList;
				_td.productId = _productId;
				_td.pic = _pic;
				var arr = [];

				for (var i = 0; i < standardMapList.length; i++) {
					arr.push(standardMapList[i].propId)
				}

				_td.propIds = arr;
				reduceprice_tabcar.html(template('reduceprice_detail_tpl', _list));

				if (_list.standardMapList.length > 0) {
					popupShow('.popup-add-cart');
				} else {
					_ts.goodsDetailSubmit();
				}

				// 选择规格
				var uls = activity_reduceprice.find(".info-list ul");
				var lists = $(uls[0]).find('li');
				for (var i = 0; i < lists.length; i++) {
					lists[i].index = i;
					lists[i].onclick = function () {
						var it = this.index;
						if (_td.t1 == it) {
							if ($(lists[_td.t1]).hasClass('cur')) {
								$(lists[_td.t1]).removeClass('cur');
							} else {
								$(lists[it]).addClass('cur');
							}
						} else {
							if ($(lists[it]).hasClass('no')) { } else {
								$(lists[it]).addClass('cur');
								$(lists[_td.t1]).removeClass('cur');
							}
						}
						_td.t1 = it;
						msg1 = $(lists[it]).data('propvalid');
						if (_td.msg1 == msg1) {
							for (var i = 0; i < _td.propValIds.length; i++) {
								if (_td.propValIds[i] == _td.msg1) {
									_td.propValIds.splice(i, 1);
								}
							}
							_td.msg1 = '';
						} else {
							for (var i = 0; i < _td.propValIds.length; i++) {
								if (_td.propValIds[i] == _td.msg1) {
									_td.propValIds.splice(i, 1);
								}
							}
							_td.msg1 = '';
							_td.msg1 = $(lists[it]).data('propvalid');
							_td.propValIds.push(_td.msg1);
						}

						!$(lists[it]).hasClass('no') && new ajax(getApi.productItems, {
							data: {
								memberId: member.id,
								propIds: (_td.propIds).toString(),
								id: _productId,
								propValIds: (_td.propValIds).toString() || '',
							},
							success: function (e) {
								var _list = e.returnData,
									_standardMapList = _list.standardMapList,
									skuId = _list.skuId;

								if (_standardMapList.length > 1) {
									propValueMapList1 = _list.standardMapList[1].propValueMapList;
								} else {
									propValueMapList1 = _list.standardMapList[0].propValueMapList;
								}
								_td.skuId = skuId;
								var propMapList1 = [];
								for (var j = 0; j < propValueMapList1.length; j++) {
									propMapList1.push(propValueMapList1[j].propValId);
								}

								var listx = $(uls[1]).find('li');
								for (var i = 0; i < listx.length; i++) {
									$(listx[i]).addClass('no')
									if (propMapList1.indexOf($(listx[i]).data('propvalid')) >= 0) {
										if ($(listx[i]).hasClass('cur')) {
											$(listx[i]).removeClass('no');
											$(listx[i]).addClass('cur');
										} else {
											$(listx[i]).removeClass('no');
										}
									}
								}
								activity_reduceprice.find('.no').hasClass('cur') && activity_reduceprice.find('.no').removeClass('cur');
								activity_reduceprice.find('.img-pic').attr('src', _list.skuPic_S);
							}
						});
					}
				}

				var listx = $(uls[1]).find('li');

				for (var i = 0; i < listx.length; i++) {
					listx[i].index = i;

					listx[i].onclick = function () {
						var it = this.index;

						if (_td.t2 == it) {
							$(listx[_td.t2]).hasClass('cur') ? $(listx[_td.t2]).removeClass('cur') : $(listx[it]).addClass('cur');
						} else {
							$(listx[it]).addClass('cur');
							$(listx[_td.t2]).removeClass('cur');
						}
						_td.t2 = it;
						msg2 = $(listx[it]).data('propvalid');

						if (_td.msg2 == msg2) {
							for (var i = 0; i < _td.propValIds.length; i++) {
								_td.propValIds[i] == _td.msg2 && _td.propValIds.splice(i, 1);
							}
							_td.msg2 = ''
						} else {
							for (var i = 0; i < _td.propValIds.length; i++) {
								_td.propValIds[i] == _td.msg2 && _td.propValIds.splice(i, 1);
							}
							_td.msg2 = '';
							_td.msg2 = $(listx[it]).data('propvalid');
							_td.propValIds.push(_td.msg2);
						}

						!$(listx[it]).hasClass('no') && new ajax(getApi.productItems, {
							data: {
								memberId: member.id,
								propIds: (_td.propIds).toString(),
								id: _productId,
								propValIds: (_td.propValIds).toString() || '',
							},
							success: function (e) {
								var _list = e.returnData,
									propValueMapList0 = _list.standardMapList[0].propValueMapList,
									skuId = _list.skuId;

								_td.skuId = skuId;

								var listx = $(uls[0]).find('li');
								var propMapList0 = [];

								for (var j = 0; j < propValueMapList0.length; j++) {
									propMapList0.push(propValueMapList0[j].propValId);
								}

								var listx = $(uls[0]).find('li');

								for (var i = 0; i < listx.length; i++) {
									$(listx[i]).addClass('no');

									if (propMapList0.indexOf($(listx[i]).data('propvalid')) >= 0) {
										if ($(listx[i]).hasClass('cur')) {
											$(listx[i]).removeClass('no');
											$(listx[i]).addClass('cur');
										} else {
											$(listx[i]).removeClass('no');
										}
									}
								}
								activity_reduceprice.find('.no').hasClass('cur') && activity_reduceprice.find('.no').removeClass('cur');
							}
						});
					}
				}
			},
			limit: 1
		});
	},

	/*提交商品时弹出弹窗 点击分享调用分享按钮*/

	// 提交商品
	goodsDetailSubmit: function () {
		var _ts = this,
			_td = _ts.data;

		var arrs = activity_reduceprice.find(".img-span");
		closeSelf('.confirm-buynow');

		if (arrs.length > 0) {
			if (arrs) {
				for (var i = 0; i < arrs.length; i++) {
					var aa = activity_reduceprice.find(".info-list");

					if (!($(aa[i])).find('.cur').length) {
						delayTip('请选择商品' + $(arrs[i]).text());
						return
					}
				}
				var cc = activity_reduceprice.find('.cur');
				if (cc.length == arrs.length) {
					_td.sku = 'true';
					_ts.pleaseAlert()

				}
			}
		} else {
			_td.sku = 'false'
			_ts.pleaseAlert()

		}
	},

	share: function (_curOrderId, _skuPic, _maxInviteTimes, _fixedAmount, _sp1) {
		var _ts = this,
			_td = _ts.data;

		getMember(function () {
			$('.page:visible .popup-share').remove();

			reduceprice_share_share.pictureUrl = _skuPic;
			reduceprice_share_share.url = real_url.replace('m.xgbuy.cc', _td.host).split(redirect_url)[0] + redirect_url + 'activity/reduceprice/share/index.html?cutOrderId=' + _curOrderId + '&sourceMemberId=' + member.id;

			shareHand.update(reduceprice_share_share, {
				succ: function () {
					_ts.post(1);
				}
			});

			shareHand.show({
				wx: 1,
				// title: '<div' + (member.app ? ' style="font-size: 17px;text-align: center;"' : '') + '>邀请<span style="color: #ff5050;">' + _maxInviteTimes + '位</span>APP新用户立减<span style="color: #ff5050;">' + (_fixedAmount * _maxInviteTimes) + '元</span></div>',
				title: '<span' + (member.app ? ' style="font-size: 17px;text-align: center;display:block;"' : '') + '>邀请<font color="#ff5050">' + _maxInviteTimes + '位</font>APP新用户立减<font color="#ff5050">' + (_fixedAmount * _maxInviteTimes) + '元</font></span>',
				sp1: _sp1 || '0',
				ext: {
					activityType: 10,
					id: _curOrderId
				}
			});
		});
	},

	buyNow: function (_id, _item, _num, _price, _cutOrderId, _un) {
		var _ts = this;
		if (_un && _un > 0) {
			_ts.buyNowConfirm(_id, _item, _num, _price, _cutOrderId);
			return;
		}

		if (member.sys == 'ios') {
			checkAppVer(53, function () {
				_ts.checkBuyNow(_id, _item, _num, _price, _cutOrderId);
			});
		} else if (member.sys == 'android') {
			checkAppVer(50, function () {
				_ts.checkBuyNow(_id, _item, _num, _price, _cutOrderId);
			});
		} else {
			_ts.checkBuyNow(_id, _item, _num, _price, _cutOrderId);
		}
	},

	checkBuyNow: function (_id, _item, _num, _price, _cutOrderId) {
		new ajax(getApi.addShoppingCart, {
			data: {
				memberId: member.id,
				productId: _id,
				productItemId: _item,
				quantity: _num,
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
					payAmount: _num * _price,
					nums: _num * _price,
					coup: 0
				}));

				setStorage('cart_pay', JSON.stringify({
					cutOrderId: _cutOrderId,
					sourceClient: _client,
					mermberPlatformCouponId: '',
					payAmount: _num * _price,
					shopCardIds: _list,
					dataList: []
				}));
				getUrl('order/firm/index.html', true);
			},
			limit: 1
		});
	},

	buyNowConfirm: function (_id, _item, _num, _price, _cutOrderId) {
		var _tip = $('.page:visible .confirm-buynow');

		$('.popup').removeClass('show');
		$('.page:visible .scroll-more').css('pointer-events', 'none');

		if (_tip.length) {
			_tip.addClass('show');
		} else {
			$('.page:visible').append('<div class="popup confirm-buynow">'
				+ '<div class="popup-center popup-tip">'
				+ '<div>'
				+ '<h3 class="flex ac jc">提示</h3>'
				+ '<p class="flex jc txt scroll-y">存在未完成助力是否继续购买</p>'
				+ '</div>'
				+ '<div class="flex">'
				+ '<a class="btn flex fg ac jc c9 bdr" data-id="' + _cutOrderId + '" onclick="reducePrice.detail(this)">查看助力</a>'
				+ '<a class="btn flex fg ac jc popup-click f50" onclick="reducePrice.buyNow(' + _id + ', ' + _item + ', ' + _num + ', ' + _price + ', ' + _cutOrderId + ')">继续购买</a>'
				+ '</div>'
				+ '</div>'
				+ '</div>');

			$('.page:visible .confirm-buynow').addClass('show');
		}
	},

	// 邀请详情
	detail: function (that, _scroll) {
		var _ts = this,
			_td = _ts.data,
			_aj = _ts.ajax,
			_id = $(that).data('id');

		closeSelf('.confirm-buynow');

		!_scroll && reduce_price_detail.removeAttr('data-page').removeAttr('old-page').scrollTop(0);

		var _ag = new ajax(getApi.getInviteDetail, {
			limit: !_scroll ? $(that) : null,
			data: {
				memberId: member.id,
				activityType: 10,
				cutOrderId: _id,
				currentPage: reduce_price_detail.attr('data-page') || 0
			},
			before: function () {
				for (i in _aj.getInviteDetail) {
					_aj.getInviteDetail[i].abort();
					_aj.getInviteDetail.splice(i, 1);
				}
			},
			success: function (e) {
				var _list = e.returnData,
					_len = _list.length;

				if (_list.length) {
					for (var i = 0; i < _len; i++) {
						!_list[i].weixinHead && (e.returnData[i].weixinHead = '../static/images/bg-head.png');
					}
				}

				if (_scroll) {
					if (_len) {
						var _cur = reduce_price_detail.attr('data-page');
						_cur++;
						reduce_price_detail.attr('data-page', _cur);
						reduce_price_detail.append(template('reduce_price_tpl_detail', e));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					reduce_price_detail.html(template('reduce_price_tpl_detail', e));
					popupShow('.popup-reduce-detail');

					_len == _td.pag && scrollHand.init(reduce_price_detail, function () {
						_ts.detail(that, true);
					});
				}
			}
		});

		_aj.getInviteDetail.push(_ag);
	},
	//减价拿 弹窗
	closeAlert:function () {
		$(".alertHelp").removeClass('alertHelpShow')
	},
	//弹窗
	pleaseAlert:function(){
		var _ts = this,
			_td = _ts.data;
		$('.alertHelp').addClass('alertHelpShow');
		$('.alertHelp').append(
			'<div class="alertChild">'+
			'<div class="pleasePrice"> 邀请1位APP新用户立减'+ _ts.data._fixedAmount +'元</div>'+
			'<div class="pleasePrice">最低'+ _ts.data._minPayAmount +'元购买</div>'+
			'<div class="goodYou pleasePrice" onclick="reducePrice.pleaseYou()">邀请好友助力</div>'+
			'<div class="closeAlert" onclick="reducePrice.closeAlert()">x</div>'+
			'</div>'
		)
	},
	pleaseYou:function () {
		var _ts = this,
			_td = _ts.data;

		if(_td.sku == 'true'){
			new ajax(getApi.addMemberCutOrderInfo, {
				data: {
					type: 10,
					memberId: member.id,
					cutOrderId: '',
					productId: _td.productId,
					productItemId: _td.skuId,
				},
				success: function (e) {
					var _list = e.returnData,
						_cutOrderId = _list.cutOrderId;
					$(".alertHelp").removeClass('alertHelpShow')
					activity_reduceprice.find(".popup-add-cart").removeClass("show");
					reduceprice_con2.removeAttr('data-page').removeClass('has-data');
					// _ts.share(_cutOrderId, _td.pic, _td.maxInviteTimes, _td.fixedAmount, '1');
					_ts.iosAndroidShare(member.id,_cutOrderId,_td.productId);
					new ajax(getApi.updateProdutStock, {
						data: {
							activityType: 10,
							id: _cutOrderId
						},
						success: function () {
						}
					});
				},
				error: function (e) {
					var _msg = e.returnMsg;
					delayTip(_msg);
					activity_reduceprice.find(".popup-add-cart").removeClass("show");
				}
			});
		}else{
			//调用微信分享弹窗
			new ajax(getApi.addMemberCutOrderInfo, {
				data: {
					type: 10,
					memberId: member.id,
					cutOrderId: '',
					productId: _td.productId,
					productItemId: '',
				},
				success: function (e) {
					var _list = e.returnData,
						_cutOrderId = _list.cutOrderId;
					$(".alertHelp").removeClass('alertHelpShow');
					activity_reduceprice.find(".popup-add-cart").removeClass("show");
					reduceprice_con2.removeAttr('data-page').removeClass('has-data');
					// _ts.share(_cutOrderId, _td.pic, _td.maxInviteTimes, _td.fixedAmount, '1');
					_ts.iosAndroidShare(member.id,_cutOrderId,_td.productId);
					new ajax(getApi.updateProdutStock, {
						data: {
							activityType: 10,
							id: _cutOrderId
						},
						success: function () {
						}
					});
				}
			});
		}
	},


	iosAndroidShare:function (memberId,cutOrderId,productId) {
		var _ts = this,
			_td = _ts.data;
		//调用微信分享弹窗
		new ajax(getApi.getMyAssistanceCutPriceShareInfo, {
			data: {
				type: 10,
				memberId: memberId,
				cutOrderId: cutOrderId,
				productId: productId
			},
			success: function (e) {
				var res = e.returnData;
				console.log()
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
	},



	btnGrays:function () {
	}
};

getTpl(function () {
	reducePrice.init();
});

function onShow() {
	traffic.setPv(reducePrice.data);
}

// dropDown(activity_reduceprice, function () {
// 	activity_reduceprice.find(".reduceprice-1").removeAttr('data-page').removeAttr('data-old');
// 	activity_reduceprice.find(".reduceprice-2").removeAttr('data-page').removeAttr('data-old');
// 	reducePrice.post(reducePrice.data.tab);
// });