var checkin_detail = $('.page[data-id="activity_newsign_buqian_detail"]:visible'),
	checkin_detail_main = checkin_detail.find(".scroll-decorately"),

	checkin_detail_checkin_main = checkin_detail_main.find(".checkin-main"),
	checkin_detail_footer = checkin_detail_main.find(".detail-footer"),

	checkin_detail_container = checkin_detail_checkin_main.find(".main-container");
	checkin_xiangzi = checkin_detail.find('.popup-xiangzi .con-xiangzi');

var checkin_share = {
	title: '签到7天即送出惊喜礼包，还有现金红包等你拿！',
	content: '快来帮我领取补签卡吧，就差你了。',
	pictureUrl: lead_url + 'views/activity/newsign/resources/share.jpg',
	url: ''
};

var detailApi = {
	data: {
		time: 3600000 * 24, // 倒计1*24小时
		post: 1,
		host: 'm.xinggoubuy.com',
		id: ''
	},

	timer: null,

	init: function () {
		var _ts = this,
			_td = _ts.data;

		$(function () {
			if (search_url) {
				for (var i = 0; i < search_url.length; i++) {
					var _a = search_url[i].split('='),
						_b = _a[1];

					switch (_a[0]) {
						case 'memberSupplementarySignInId': _td.memberSupplementarySignInId = _b; break;
					}
				}
			}

			requestAppMember(function () {
				_ts.post();
			}, function () {
				getApi.getMyAssistanceDtlList = getApi.getMyAssistanceDtlListH5;
				getApi.getMySupplementarySignSharePage = getApi.getMySupplementarySignSharePageH5;
			});
		});
	},

	post: function () {
		var _ts = this,
			_td = _ts.data;

		require(['qpTime'], function () {
			new ajax(getApi.getMySupplementarySignSharePage, {
				data: {
					memberSupplementarySignInId: _td.memberSupplementarySignInId,
					status: _td.status,
					taskStatus: _td.taskStatus,
					memberId: member.id,
				},
				success: function (e) {
					var _list = e.returnData,
						_memberAssistanceStatus = _list.memberAssistanceStatus,
						currentMemberId = _list.currentMemberId,
						currentMemberId = _list.currentMemberId;

					_td.currentMemberId = currentMemberId,
					_td.timed = _td.time - (_list.currentDate - _list.beginTime);

					_list.timer = _td.time - (_list.currentDate - _list.beginTime);
					_list.timer > 0 && (_list.time = _ts.timer(_list.timer));

					checkin_detail_footer.html(template('checkin_detail_tpl_footer', _list));
					checkin_detail_container.html(template('checkin_detail_tpl_container', _list));

					_ts.timeRun();

					_memberAssistanceStatus == 6 && delayTip('只有新用户才能参与助力哦');
				}
			});
			getMember(function () {
				checkin_share.url = real_url.replace('m.xgbuy.cc', _td.host).split(redirect_url)[0] + redirect_url + 'activity/newsign/buqian/detail/index.html?memberId=' + member.id + "&memberSupplementarySignInId=" + _td.memberSupplementarySignInId;
				shareHand.update(checkin_share, {
					start: function () {
						closeSelf('.popup-doyoushare');
					}
				});
			});
		});
	},

	timer: function (e) {
		return famateNumber(e / 1e3 / 60 / 60) + ':' + famateNumber(e / 1e3 / 60 % 60) + ':' + famateNumber(e / 1e3 % 60);
	},

	timeRun: function (e) {
		var _ts = this,
			_tm = checkin_detail_container.find('time');

		_tm && $.qpTime({
			s: 1,
			e: _tm.data('timer')
		}, function (pt) {
			_tm.html(_ts.timer(pt.t));
			!pt.t && _tm.parent().empty().addClass('r-empty');
		});
	},
	
		// 签到领箱子
	getGift: function (i) {
		var _ts = this,
			_td = _ts.data;

		var btns = checkin_detail.find('.friendhelp-content');

		for (var i = 0; i < btns.length; i++) {
			btns[i].index = i;

			btns[i].onclick = function () {
				var s = this.index;

				new ajax(getApi.getSignInHomePage, {
					data: {
						memberId: member.id,
						dateTime: new Date().getTime()
					},
					success: function (e) {
						var _list = e.returnData,
							cumulativeAwardList = _list.cumulativeAwardList,
							currentDate = _list.currentDate;
						_black = _list.isBlack;

						var currentDatenow = new Date(currentDate).getDate();
						cumulativeAwardList[s].dateTimenow = currentDatenow;
						dateTimenow = cumulativeAwardList[s].dateTimenow;
						checkin_xiangzi.html(template('checkin_tpl_xiangzi', cumulativeAwardList[s]));
						if (cumulativeAwardList[s].productMap) {
							_td.productId = cumulativeAwardList[s].productMap.productId;
							_td.stockSum = cumulativeAwardList[s].productMap.stockSum;
							_td.cumulativeSignInSettingId = cumulativeAwardList[s].cumulativeSignInSettingId;
						} else {
							_td.productId = '';
						}
						popupShow('.popup-xiangzi');
					}
				});
			}
		}
	},

	// 关闭签到
	closeSign: function (_this) {
		closeSelf(_this);
	},

	// 立即助力
	clickZhuli: function () {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.addMemberAssistanceDtl, {
			data: {
				memberId: _td.currentMemberId,
				memberSupplementarySignInId: _td.memberSupplementarySignInId
			},
			success: function () {
				delayTip('助力成功');
				 _ts.post()
			},
			error: function () {
				delayTip('助力失败');
			},
			limit: 1
		});
	},

	// 分享
	share: function () {
		var _ts = this,
		_td = _ts.data;

		checkin_share.url && shareHand.show({
			wx: 1,
			title: '分享到'
		});
	}
};

getTpl(function () {
	detailApi.init();
});