var checkin = $('.page[data-id="activity_checkin"]:visible'),
	checkin_scroll = checkin.find('.scroll-more'),
	checkin_row1 = checkin.find('.row-1'),
	checkin_row2 = checkin.find('.row-2'),
	checkin_rule = checkin.find('.popup-checkin-rule .tab-body'),
	checkin_friend = checkin.find('.popup-friend .con'),
	checkin_sign = checkin.find('.popup-sign .con');

var checkin_share = {
	title: '一起来醒购签到领红包吧',
	content: '一起来醒购签到领取红包吧，邀请好友获得额外红包哦',
	pictureUrl: lead_url + 'views/activity/checkin/resources/share.jpg',
	url: ''
};

var checkinApi = {
	data: {
		host: 'm.xinggoubuy.com',
		id: '',
		seven: false
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'sourceid': _ts.data.id = _b; break;
				}
			}
		}

		traffic.setPv(_td, {
			pageType: '25'
		});

		requestAppMember(function () {
			_ts.post();
		}, function () {
			getApi.getMemberSignInPage = getApi.getMemberSignInPageH5;
			getApi.getSignInBroadcastContent = getApi.getSignInBroadcastContentH5;
			getApi.addMemberSignIn = getApi.addMemberSignInH5;
			getApi.getShareFriendSignIn = getApi.getShareFriendSignInH5;
			getApi.addReceiveExtraAmount = getApi.addReceiveExtraAmountH5;
		});
	},

	post: function (_flesh) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getMemberSignInPage, {
			data: {
				memberId: member.id
			},
			success: function (e) {
				var _list = e.returnData,
					_infoid = _list.decorateInfoId,
					_black = _list.isBlack;

				if (_black) {
					shield(_list.blackReason);
					return;
				}

				for (var i = 0; i < _list.isSignInList.length; i++) {
					var _date = '';

					switch(i) {
						case 0: _date = '一'; break;
						case 1: _date = '二'; break;
						case 2: _date = '三'; break;
						case 3: _date = '四'; break;
						case 4: _date = '五'; break;
						case 5: _date = '六'; break;
						case 6: _date = '七'; break;
					}

					_list.isSignInList[i].date = '第' + _date + '天';
				}

				!_list.memberBalance && (_list.memberBalance = '0.00');
				_list.isWeiXin = isWeiXin;
				isWeiXin && checkin.find('.popup-friend h3').html('邀请好友签到记录');

				checkin_row1.html(template('checkin_tpl', _list));

				if (_flesh) {
					return;
				}

				_td.seven = _list.isSignInList[6].isSignIn;

				_infoid && $.ajax({
					method: 'get',
					url: real_url.split('/views/')[0] + '/views/activity/templet/brand_decorate.html',
					datatype: 'html',
					success: function (data) {
						setStorage('decorate_infoid', _infoid);
						setStorage('decorate_nest', 1);
						checkin_row2.html(data.replace('<header class="flex', '<header class="flex hide').replace('page flex dc activity-single-day', 'activity-single-day').replace(' data-id="activity_templet_brand_decorate"', ''));
					}
				});

				getMember(function () {
					checkin_share.url = real_url.replace('m.xgbuy.cc', _td.host).split(redirect_url)[0] + redirect_url + 'activity/checkin/index.html?sourceid=' + member.id;
					shareHand.update(checkin_share, {
						start: function () {
							closeSelf('.popup-doyoushare');
						}
					});
				});

				_ts.role();
			}
		});
	},

	// 轮播文字
	role: function () {
		var _ts = this;

		new ajax(getApi.getSignInBroadcastContent, {
			success: function (e) {
				var _list = e.returnData;

				if (_list.length && !checkin_scroll.find('.role-text').length) {
					var _role = $('<div class="role-text"></div>');
					_role.html(template('checkin_tpl_role', e));
					checkin_scroll.append(_role);
					_list.length > 1 && _ts.roleRun();
				}
			}
		});
	},

	roleRun: function () {
		require(['vticker'], function () {
			checkin.find('.role-text').vTicker({
				speed: 1500,
				pause: 3e3
			});
		});
	},

	// 规则
	rule: function () {
		var evt = window.event;

		checkin_rule.html(template('checkin_tpl_rule'));
		popupShow('.popup-checkin-rule', evt);
	},

	// 签到提醒 (隐藏了)
	remind: function (_this) {
		if ($(_this).hasClass('on')) {
			$(_this).removeClass('on');
		} else {
			$(_this).addClass('on');
		}
	},

	// 签到领现金红包
	getCash: function () {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.addMemberSignIn, {
			data: {
				sourceMemberId: _ts.data.id,
				signInClient: 2,
				memberId: member.id
			},
			success: function (e) {
				var _list = e.returnData;
				_list.isWeiXin = isWeiXin;
				checkin_sign.html(template('checkin_tpl_sign', _list));
				popupShow('.popup-sign');
				_ts.post(true);
			},
			limit: 1
		});
	},

	// 好友签到记录
	friendSign: function () {
		var evt = window.event;

		new ajax(getApi.getShareFriendSignIn, {
			data: {
				memberId: member.id
			},
			success: function (e) {
				var _list = e.returnData,
					_limit = _list.inviteLimit,
					_data = _list.dataList,
					_len = _data.length;

				if (_len > _limit) {
					_data = _data.slice(0, _limit);
				} else if (_len < _limit) {
					for (var i = 0; i < (_limit - _len); i++) {
						_data.push({
							headPic: '../static/images/bg-head.png'
						});
					}
				}

				_list.isWeiXin = isWeiXin;
				checkin_friend.html(template('checkin_tpl_friend', _list));
				popupShow('.popup-friend', evt);
			},
			limit: 1
		});
	},

	// 立即领取红包现金
	handCash: function (_id) {
		var _ts = this;

		new ajax(getApi.addReceiveExtraAmount, {
			data: {
				memberId: member.id,
				mIds: _id
			},
			success: function () {
				_ts.post(true);
				closeSelf('.popup-friend');
				delayTip('领取成功');
			},
			limit: 1
		});
	},

	// 关闭签到
	closeSign: function (_this) {
		var _ts = this,
			_td = _ts.data;

		closeSelf(_this);
		_td.seven && popupShow('.popup-doyoushare');
	},

	// tab切换
	changeTab: function (_this) {
		!$(_this).hasClass('active') && $(_this).addClass('active').siblings().removeClass('active').parent().siblings().find('.tab-con').eq($(_this).index()).removeClass('hide').siblings().addClass('hide');
	},

	// 分享
	share: function () {
		checkin_share.url && shareHand.show({
			wx: 1,
			title: '越多好友签到，收到红包越多'
		});
	}
};

getTpl(function () {
	checkinApi.init();
});

function onShow() {
	traffic.setPv(checkinApi.data);
}