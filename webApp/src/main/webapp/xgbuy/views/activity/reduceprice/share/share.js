var reduceprice_share = $('.page[data-id="activity_reduceprice_share"]:visible'),
	reduceprice_share_scroll = reduceprice_share.find('.scroll-more'),
	reduceprice_share_con = reduceprice_share_scroll.find('.touchfix'),
	reduceprice_share_help = reduceprice_share.find('.free-share-help'),
	reduceprice_share_rule = reduceprice_share.find('.free-share-rule'),

	reduceprice_share_row2 = '',
	reduceprice_share_row2_scroll = '',

	reduceprice_share_row3 = '',
	reduceprice_share_row3_list = '';

var reduceprice_share_share = {
	title: '快来帮我减价，最低1折抢大牌',
	content: '我在醒购参加助力大减价活动，快来帮我助力吧！',
	pictureUrl: '',
	url: ''
};

var reducepriceShare = {
	data: {
		host: 'm.xinggoubuy.com',
		time: 3600000*25, // 1*24小时
		id: '',
		pag: 10,
		cutOrderId: '',
		fixedAmount: 0 // 固定金额
	},

	init: function () {
		var _ts = this;
		
		$(function () {
			if (search_url) {
				for (var i = 0; i < search_url.length; i++) {
					var _a = search_url[i].split('='),
						_b = _a[1];

					switch (_a[0]) {
						case 'sourceMemberId': _ts.data.id = _b; break;
						case 'cutOrderId': _ts.data.cutOrderId = _b; break;
					}
				}
			}

			requestAppMember(function () {
				_ts.main();
			}, function () {
				getApi.getMyAssistanceCutPriceShareInfo = getApi.getMyAssistanceCutPriceShareInfoH5;
				getApi.getInviteDetail = getApi.getInviteDetailH5;
				getApi.getBargainGoodsList = getApi.getBargainGoodsListH5;
			});
		});
	},

	main: function () {
		var _ts = this,
			_td = _ts.data;

		require(['qpTime'], function () {
			new ajax(getApi.getMyAssistanceCutPriceShareInfo, {
				data: {
					memberId: member.id,
					cutOrderId: _td.cutOrderId,
				},
				success: function (e) {
					var _list = e.returnData,
						_black = _list.isBlack;

					if (_black) {
						shield(_list.blackReason);
						return;
					}

					_ts.fixedAmount = _list.fixedAmount;

					_list.memberAssistanceStatus == 6 && delayTip("只有新用户才能参与助力哦");

					_list.timer = _list.endDate - _list.currentDate;

					if (_list.auditStatus == 2) {
						reduceprice_share_con.html(template('reduceprice_share_tpl_row1s', _list));
						reduceprice_share_row3 = reduceprice_share_con.find('.row-3s');
						reduceprice_share_rule.addClass('rules');

						_ts.freeList();

						new ajax(getApi.addCutLinkClickLog, {
							data: {
								shareLinkType: 1,
								shareUrl: real_url,
								linkValue: _td.cutOrderId
							}
						});

					} else {
						reduceprice_share_con.html(template('reduceprice_share_tpl_row1', _list));
						reduceprice_share_row3 = reduceprice_share_con.find('.row-3');
					}

					reduceprice_share_row2 = reduceprice_share_con.find('.row-2');
					_ts.detail();
					_ts.timeRun();

					reduceprice_share_share.pictureUrl = _list.skuPic;
					reduceprice_share_share.url = real_url.replace('m.xgbuy.cc', _td.host).split(redirect_url)[0] + redirect_url + 'activity/reduceprice/share/index.html?cutOrderId=' + _td.cutOrderId + '&sourceMemberId=' + _list.memberId;
					shareHand.update(reduceprice_share_share);
				}
			});
		});
	},

	freeList: function (_scroll) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getBargainGoodsList, {
			data: {
				type: 10,
				memberId: member.id,
				currentPage: reduceprice_share_scroll.attr('data-page') || 0
			},
			success: function (e) {
				var _list = e.returnData,
					_data = _list.dataList,
					_len = _data.length;

				if (_len) {
					for (var i = 0; i < _len; i++) {
						!/\./g.test(_data[i].tagPrice) && (_data[i].tagPrice += '.00');
					}
				}

				_list.sourceMemberId = _td.id;
				_list.dataList = imgCacheProductPic(_list.dataList, 'reduceprice_price_cache');

				if (_scroll) {
					if (_len) {
						var _cur = reduceprice_share_scroll.attr('data-page');
						_cur++;
						reduceprice_share_scroll.attr('data-page', _cur);
						reduceprice_share_row3_list.append(template('reduceprice_share_tpl_row3_list', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					reduceprice_share_row3.html(template('reduceprice_share_tpl_row3', _list));
					reduceprice_share_row3_list = reduceprice_share_row3.find('.con-list');

					_len == _td.pag && scrollHand.init(reduceprice_share_scroll, function () {
						_ts.freeList(true);
					}, true);
				}
			}
		});
	},

	// 邀请详情
	detail: function (_scroll) {
		var _ts = this,
			_td = _ts.data,
			_cp = reduceprice_share_row2_scroll ? reduceprice_share_row2_scroll.attr('data-page') : 0;

		new ajax(getApi.getInviteDetail, {
			data: {
				activityType: 10,
				memberId: member.id,
				cutOrderId: _td.cutOrderId,
				currentPage: _cp || 0
			},
			success: function (e) {
				var _list = e.returnData,
					_len = _list.length;

				if (_list.length) {
					for (var i = 0; i < _len; i++) {
						!_list[i].weixinHead && (e.returnData[i].weixinHead = '../static/images/bg-head.png');
						_list[i].status == '1' && (e.returnData[i].content = e.returnData[i].content + '已减' + _ts.fixedAmount);
					}
				}

				if (_scroll) {
					if (_len) {
						var _cur = reduceprice_share_row2_scroll.attr('data-page');
						_cur++;
						reduceprice_share_row2_scroll.attr('data-page', _cur);
						reduceprice_share_row2_scroll.append(template('reduceprice_share_tpl_row2_list', e));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					reduceprice_share_row2.html(template('reduceprice_share_tpl_row2', e));
					reduceprice_share_row2_scroll = reduceprice_share_row2.find('.scroll-y');
					reduceprice_share_row2.hasClass('row-22') && reduceprice_share_row2_scroll.removeClass('scroll-y').addClass('scroll-x flex');

					_len == _td.pag && scrollHand.init(reduceprice_share_row2_scroll, function () {
						_ts.detail(true);
					});
				}
			}
		});
	},

	timer: function (e) {
		return famateNumber(e / 1e3 / 60 / 60) + ':' + famateNumber(e / 1e3 / 60 % 60) + ':' + famateNumber(e / 1e3 % 60);
	},

	timeRun: function (e) {
		var _ts = this,
			_td = _ts.data;

		$.each(reduceprice_share_con.find('time'), function () {
			var $ts = $(this);

			$.qpTime({
				s: 1,
				e: $ts.data('timer')
			}, function (pt) {
				$ts.html(_ts.timer(pt.t));
			});
		});
	},

	more: function (_this) {
		$(_this).addClass('hide').siblings('.show3').removeClass('show3');
	},

	load: function () {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.addFriendFinishTask, {
			data: {
				memberId: member.id,
				cutOrderId: _td.cutOrderId
			},
			success: function (e) {
				getUrl('my/download/index.html');
			},
			error: function (e) {
				reduceprice_share_help.html(template('reduceprice_share_tpl_help', e));
				popupShow(reduceprice_share_help);
			},
			limit: 1
		});
	},

	share: function () {
		reduceprice_share_share.title && shareHand.show({
			wx: 1
		});
	},

	buyNow: function (_id, _item, _num, _price, _cutOrderId) {
		var _ts = this;

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
					shopCardIds: _list,
					dataList: []
				}));

				getUrl('order/firm/index.html', true);
			},
			limit: 1
		});
	}
};

getTpl(function () {
	reducepriceShare.init();
});