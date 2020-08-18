var checkin_draw = $('.page[data-id="activity_checkin_draw"]:visible'),
	checkin_draw_scroll = checkin_draw.find('.scroll-more'),
	checkin_draw_row1 = checkin_draw.find('.row-1'),
	checkin_draw_row2 = checkin_draw.find('.row-2'),
	checkin_draw_row3 = checkin_draw.find('.row-3'),
	checkin_draw_btn = $('.page:visible .popup-record .tab-head a')[0],
	checkin_draw_con = $('.page:visible .popup-record .tab-con');

var checkinDrawApi = {
	data:{
		memberBalance: '',
		isWithdrawFail: ''
	},

	init: function () {
		var _ts = this;

		requestAppMember(function () {
			_ts.post();
		}, function () {
			getApi.getMemberSignInPage = getApi.getMemberSignInPageH5;
			getApi.getWithdrawCashtraPage = getApi.getWithdrawCashtraPageH5;
			getApi.getSignInBroadcastContent = getApi.getSignInBroadcastContentH5;
			getApi.getSignInOrDrawRecords = getApi.getSignInOrDrawRecordsH5;
			getApi.addMemberWithdrawCash = getApi.addMemberWithdrawCashH5;
		});
	},

	post: function (_flesh) {
		var _ts = this;
			_td = _ts.data;

		new ajax(getApi.getMemberSignInPage, {
			data: {
				memberId: member.id
			},
			success: function (e) {
				var _list = e.returnData,
				_memberBalance = _list.memberBalance,
				_black = _list.isBlack;
			_td.memberBalance = _memberBalance

				if (_black) {
					shield(_list.blackReason);
					return;
				}

				!_list.memberBalance && (_list.memberBalance = '0.00');
				checkin_draw_row1.html(template('checkin_draw_tpl', _list));

				if (_flesh) {
					return;
				}

			 	_ts.cnf();
				_ts.role();
				checkin_draw_row3.removeClass('hide');
			}
		});
	},

	cnf: function (_scroll) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getWithdrawCashtraPage, {
			data: {
				memberId: member.id,
				currentPage: checkin_draw_scroll.attr('data-page') || 0
			},
			success: function (e) {
				var _list = e.returnData,
					_cnf = _list.withdrawCnf,
					_len = _cnf.length,
					_isWithdrawFail =_list.isWithdrawFail;
				_td.isWithdrawFail =_isWithdrawFail;
			

				for (var i = 0; i < _len; i++) {
					if (_cnf[i].withdrawType == 1) {
						_cnf[i].withdrawText = '现金券';
					} else if (_cnf[i].withdrawType == 2) {
						_cnf[i].withdrawText = '微信红包';
					}
				}

				if (_scroll) {
					if (_len) {
						var _cur = checkin_draw_scroll.attr('data-page');
						_cur++;
						checkin_draw_scroll.attr('data-page', _cur);
						checkin_draw_row2.append(template('checkin_draw_tpl_cnf', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					checkin_draw_row2.html(template('checkin_draw_tpl_cnf', _list));

					_len == 10 && scrollHand.init(checkin_draw_scroll, function () {
						_ts.cnf(true);
					}, true);
				}
			}
		});
	},

	// 轮播文字
	role: function () {
		var _ts = this;

		new ajax(getApi.getSignInBroadcastContent, {
			data: {
				memberId: member.id
			},
			success: function (e) {
				var _list = e.returnData;

				if (_list.length && !checkin_draw_scroll.find('.role-text').length) {
					var _role = $('<div class="role-text"></div>');
					_role.html(template('checkin_draw_tpl_role', e));
					checkin_draw_scroll.append(_role);
					_list.length > 1 && _ts.roleRun();
				}
			}
		});
	},

	roleRun: function () {
		require(['vticker'], function () {
			checkin_draw.find('.role-text').vTicker({
				speed: 1500,
				pause: 3e3
			});
		});
	},

	// 红包明细
	record: function () {
		checkin_draw_con.scrollTop(0).removeClass('has-data').removeAttr('data-page').removeAttr('data-old');
		this.changeTabRecord(checkin_draw_btn);
	},

	// 立即提现
	getCnf: function (_amount, _id, _withdrawText) {
		var _ts = this,
			_td = _ts.data;

		if (!member.app) {
			confirmTip('请在APP上操作, 是否现在打开APP?', function () {
				closeSelf();
				getUrl('my/download/index.html', true);
			});
		} else {
			_ts.confirmCnf(_amount, _withdrawText, function () {
				new ajax(getApi.addMemberWithdrawCash, {
					data: {
						memberId: member.id,
						withdrawCashId: _id,
					},
					success: function (e) {
						_ts.confirmSuss(_amount, _withdrawText);
					},
					error: function(e){
						_td.isWithdrawFail == false && delayTip(e.returnMsg);

						if (_td.memberBalance >= _amount) {
							_td.isWithdrawFail == true && getUrl('activity/newsign/draw/yichang/index.html?withdrawCashId=' + _id, true);
						} else {
							delayTip("余额不足");
						}
					},
					limit: 1
				});
			});
		}
	},

	// tab切换
	changeTabRecord: function (_this, _tab, _scroll) {
		checkin_draw_btn = _this;
		$ts = $(_this);

		var _ts = this,
			_type = $ts.data('type'),
			evt = window.event;

		$ts.addClass('active').siblings().removeClass('active');

		var _con = $ts.parent().siblings().find('.data-type-' + _type),
			_pag = _con.attr('data-page') || 0;

		_con.removeClass('hide').siblings().addClass('hide');
		_con.addClass('scroll-y').siblings().removeClass('scroll-y');

		if (_tab && _con.hasClass('has-data')) {
			return;
		}

		new ajax(getApi.getSignInOrDrawRecords, {
			data: {
				memberId: member.id,
				type: _type,
				currentPage: _pag
			},
			success: function (e) {
				var _len = e.returnData.length;
			
				if (_scroll) {
					if (_len) {
						var _cur = _con.attr('data-page');
						_cur++;
						_con.attr('data-page', _cur);
						_con.append(template('checkin_draw_tpl_record', e));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_con.html(template('checkin_draw_tpl_record', e)).addClass('has-data');
					popupShow('.popup-record', evt);

					_len == 10 && scrollHand.init(_con, function () {
						_ts.changeTabRecord(_this, false, true);
					});
				}
			}
		});
	},

	// 确定领取
	confirmCnf: function (_money, _type, _fun) {
		$('.popup').removeClass('show');
		$('.page:visible').find('.confirm-tip')[0] && $('.confirm-tip').remove();

		$('.page:visible').append('<div class="popup popup-style-confirm popup-style-sign show">'
			+ '<div class="popup-checkin-con" onclick="stopSelf()">'
				+ '<a class="close-x" onclick="closeSelf(this)"></a>'
				+ '<h3 class="flex ac jc">确定领取' + _money + '元' + _type + '</h3>'
				+ '<div class="flex jc">'
					+ '<a class="btn-checkin flex ac jc fn" onclick="closeSelf(this)">取消</a>'
					+ '<a class="btn-checkin flex ac jc fn popup-click">确定</a>'
				+ '</div>'
			+ '</div>'
		+ '</div>');

		$('.page:visible .popup-style-confirm .popup-click').on('click', function (e) {
			e.preventDefault();
			_fun();
		});
	},

	// 领取成功
	confirmSuss: function (_money, _type) {
		$('.popup').removeClass('show');
		$('.page:visible').find('.confirm-tip')[0] && $('.confirm-tip').remove();

		var _h3 = '恭喜成功提现' + _money + '元' + _type,
			_p = '审核成功后请到“我的->优惠券”查看';

		if (_type == '微信红包') {
			_h3 = '提现成功';
			_p = '红包提现成功，审核后将发送到您的微信，请注意查收哦';
		}

		$('.page:visible').append('<div class="popup popup-style-confirm popup-style-sign show">'
			+ '<div class="popup-checkin-con" onclick="stopSelf()">'
				+ '<a class="close-x" onclick="closeSelf(this)"></a>'
				+ '<h3 class="flex ac jc">' + _h3 + '</h3>'
				+ '<p class="c9 flex ac jc">' + _p + '</p>'
				+ '<div class="flex jc">'
					+ '<a class="btn-checkin flex ac jc fn" style="width: 2.8rem; margin-top: .4rem;" onclick="closeSelf(this)">知道了</a>'
				+ '</div>'
			+ '</div>'
		+ '</div>');

		this.post(true);

		try {
			checkinApi.post(true);
		} catch(e) {}
	}
};

getTpl(function () {
	checkinDrawApi.init();
});