var free_share = $('.page[data-id="activity_freeprice_share"]:visible'),
	free_share_scroll = free_share.find('.scroll-more'),
	free_share_con = free_share_scroll.find('.touchfix'),
	free_share_help = free_share.find('.free-share-help'),
	free_share_rule = free_share.find('.free-share-rule'),

	free_share_row2 = '',
	free_share_row2_scroll = '',

	free_share_row3 = '',
	free_share_row3_list = '',

	free_share_mask = '',
	free_share_time = '';

var free_share_share = {
	title: '',
	content: '我正在参加醒购官方邀请好友免费拿活动，就差你来助力了！',
	pictureUrl: '',
	url: '',
	comment: ''
};

var freeShare = {
	data: {
		host: 'm.xinggoubuy.com',
		time: 3600000*24, // 1*24小时
		id: '',
		pag: 10,
		cutOrderId: ''
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
				getApi.getMyInviteShareInfo = getApi.getMyInviteShareInfoH5;
				getApi.getInviteDetail = getApi.getInviteDetailH5;
				getApi.getBargainGoodsList = getApi.getBargainGoodsListH5;
			});
		});
	},

	main: function () {
		var _ts = this,
			_td = _ts.data;

		require(['qpTime'], function () {
			new ajax(getApi.getMyInviteShareInfo, {
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

					_list.timer = _td.time - (_list.currentDate - _list.createDate);
					!/\./g.test(_list.tagPrice) && (_list.tagPrice += '.00');

					if (_list.showType == 1) {
						if (_list.auditStatus == 1) {
							setStorage('free_share_neednum', 1);
							getUrl('activity/freeprice/index.html?cutOrderId=' + _td.cutOrderId + '&sourceMemberId=' + member.id, true);
							return;
						}

						free_share_con.html(template('free_share_tpl_row1s', _list));
						free_share_row3 = free_share_con.find('.row-3s');
						free_share_rule.addClass('rules');

						if (_list.auditStatus == 0 && _list.lastNeedInviteNum > 0 && _list.timer > 0 && !member.app) {
							free_share_mask = $('<div class="share-mask-tip popup" onclick="freeShare.maskClose()">'
								+ '<div class="share-mask-con">'
									+ '<h3 class="flex ac jc">再邀请' + _list.lastNeedInviteNum + '位好友即可免费领取</h3>'
									+ '<div class="flex ac jc time" data-timer="' + _list.timer + '">'
										+ '剩余<b class="flex ac jc">:</b>'
										+ '<span class="flex ac jc t-h">00</span><b class="flex ac jc">:</b>'
										+ '<span class="flex ac jc t-m">00</span><b class="flex ac jc">:</b>'
										+ '<span class="flex ac jc t-s">00</span><b class="flex ac jc">:</b>'
										+ '<span class="flex ac jc t-l">00</span>'
									+ '</div>'
								+ '</div>'
							+ '</div>');

							free_share.append(free_share_mask).addClass('has-timer');

							free_share_time = {
								h: free_share.find('.t-h'),
								m: free_share.find('.t-m'),
								s: free_share.find('.t-s'),
								l: free_share.find('.t-l')
							};

							_ts.timerSelf(_list.timer);
							_ts.timeRunSelf();
						}

						_ts.freeList();
					} else {
						free_share_con.html(template('free_share_tpl_row1', _list));
						free_share_row2 = free_share_con.find('.row-2');
						free_share_row3 = free_share_con.find('.row-3');
						_ts.timeRun();
						_ts.detail();

						_list.decorateInfoId && $.ajax({
							method: 'get',
							url: lead_url + 'views/activity/templet/brand_decorate.html',
							datatype: 'html',
							success: function (data) {
								setStorage('decorate_infoid', _list.decorateInfoId);
								setStorage('decorate_nest', 1);

								data = data
								.replace('<header class="flex', '<header class="flex hide')
								.replace('page flex dc activity-single-day', 'activity-single-day')
								.replace(' data-id="activity_templet_brand_decorate"', '');

								free_share_row3.html(data);
							}
						});
					}

					free_share_share.title = '快来帮我免费领取' + _list.productName;
					free_share_share.pictureUrl = _list.skuPic;
					free_share_share.url = real_url.replace('m.xgbuy.cc', _td.host).split(redirect_url)[0] + redirect_url + 'activity/freeprice/share/index.html?share=1&cutOrderId=' + _td.cutOrderId + '&sourceMemberId=' + _td.id;
					shareHand.update(free_share_share, {
						close: _ts.close
					});
				}
			});
		});
	},

	freeList: function (_scroll) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getBargainGoodsList, {
			data: {
				type: 8,
				memberId: member.id,
				sourceMemberId: _td.id,
				currentPage: free_share_scroll.attr('data-page') || 0
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
				_list.dataList = imgCacheProductPic(_list.dataList, 'free_price_cache');

				if (_scroll) {
					if (_len) {
						var _cur = free_share_scroll.attr('data-page');
						_cur++;
						free_share_scroll.attr('data-page', _cur);
						free_share_row3_list.append(template('free_share_tpl_row3_list', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					free_share_row3.html(template('free_share_tpl_row3', _list));
					free_share_row3_list = free_share_row3.find('.con-list');

					_len == _td.pag && scrollHand.init(free_share_scroll, function () {
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
			_cp = free_share_row2_scroll ? free_share_row2_scroll.attr('data-page') : 0;

		new ajax(getApi.getInviteDetail, {
			data: {
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
					}
				}

				if (_scroll) {
					if (_len) {
						var _cur = free_share_row2_scroll.attr('data-page');
						_cur++;
						free_share_row2_scroll.attr('data-page', _cur);
						free_share_row2_scroll.append(template('free_share_tpl_row2_list', e));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					free_share_row2.html(template('free_share_tpl_row2', e));
					free_share_row2_scroll = free_share_row2.find('.scroll-y');
					free_share_row2.hasClass('row-22') && free_share_row2_scroll.removeClass('scroll-y').addClass('scroll-x flex');

					_len == _td.pag && scrollHand.init(free_share_row2_scroll, function () {
						_ts.detail(true);
					});
				}
			}
		});
	},

	timer: function (e) {
		return famateNumber(e / 1e3 / 60 / 60) + ':' + famateNumber(e / 1e3 / 60 % 60) + ':' + famateNumber(e / 1e3 % 60);
	},

	timerSelf: function (e) {
		if (free_share_time) {
			free_share_time.h.html(famateNumber(e / 1e3 / 60 / 60));
			free_share_time.m.html(famateNumber(e / 1e3 / 60 % 60));
			free_share_time.s.html(famateNumber(e / 1e3 % 60));
			free_share_time.l.html(famateNumber(e % 1e3 / 10));
		}
	},

	timeRunSelf: function () {
		var _ts = this,
			_tm = free_share.find('.time').data('timer');

		$.qpTime({
			s: 1,
			e: _tm
		}, function (pt) {
			_ts.timerSelf(pt.t);
		});
	},

	timeRun: function (e) {
		var _ts = this,
			_td = _ts.data;

		$.each(free_share_con.find('time'), function () {
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
				free_share_help.html(template('free_share_tpl_help', e));
				popupShow(free_share_help);
			},
			limit: 1
		});
	},

	share: function () {
		var _ts = this;

		if (free_share_share.title) {
			shareHand.show({
				wx: 1
			});
			free_share_mask && free_share_mask.addClass('show');
		}
	},

	close: function () {
		free_share_mask && free_share_mask.removeClass('show');
	},

	maskClose: function () {
		this.close();
	}
};

getTpl(function () {
	freeShare.init();
});