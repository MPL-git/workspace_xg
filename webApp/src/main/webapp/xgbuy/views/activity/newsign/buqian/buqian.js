var checkin_buqian = $('.page[data-id="activity_newsign_buqian"]:visible'),
	checkin_buqian_theader = checkin_buqian.find('.tab-header'),
	checkin_buqian_tbtn = checkin_buqian.find('.tab-btn'),

	checkin_buqian_con1 = checkin_buqian_theader.find('.checkin-1'),
	checkin_buqian_con2 = checkin_buqian_theader.find('.checkin-2'),

	checkin_buqian_list1 = checkin_buqian_con1.find('.tab-list'),
	checkin_buqian_list2 = checkin_buqian_con2.find('.tab-list'),

	checkin_time = checkin_buqian_list2.find('.time');

var checkin_share = {
	title: '签到7天即送出惊喜礼包，还有现金红包等你拿！',
	content: '快来帮我领取补签卡吧，就差你了。',
	pictureUrl: lead_url + 'views/activity/newsign/resources/share.jpg',
	url: ''
};

var buQian = {
	data: {
		time: 3600000 * 24, // 倒计1*24小时
		host: 'm.xinggoubuy.com',
		id: '',
		seven: false,
		memberSupplementarySignInId: ''
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		$(function () {
			if (search_url) {
				for (var i = 0; i < search_url.length; i++) {
					var _a = search_url[i].split('='),
						_b = _a[1];

					switch (_a[0]) {
						case 'sourceMemberId': _td.id = _b; break;
						case 'cutOrderId': _td.cutOrderId = _b; break;
						case 'tab': _td.tab = _b; break;
					}
				}
			}

			requestAppMember(function () {
				_ts.post();
			}, function () {
				getApi.getMyAssistanceDtlList = getApi.getMyAssistanceDtlListH5;
				getApi.getMySupplementarySignSharePage = getApi.getMySupplementarySignSharePageH5;
				getApi.addSupplementarySignTask = getApi.addSupplementarySignTaskH5;
				getApi.getMySupplementarySignTaskList = getApi.getMySupplementarySignTaskListH5;
			});
		});
	},

	post: function () {
		this.clickTab1();
	},

	// 补签卡的任务
	clickTab1: function () {
		var _ts = this,
			_td = _ts.data;

		checkin_buqian.find('.checkin-1').removeClass('hide');
		checkin_buqian.find('.my-buqian').removeClass('f50');
		checkin_buqian.find('.msg-buqian').addClass('f50');
		checkin_buqian.find('.checkin-2').addClass('hide');

		new ajax(getApi.getSupplementarySignTaskList, {
			data: {
				memberId: member.id
			},
			success: function (e) {
				var _list = e.returnData,
					_data = _list.dataList,
					_len = _data.length,
					_black = _list.isBlack;

				_td.signdata = _data

				checkin_buqian_list1.html(template('checkin_buqian_tpl_con1', _list));
				_ts.lunbo();
				_ts.timeRun();

				//领取任务 
				var btns = checkin_buqian.find(".flex-button");

				for (var i = 0; i < btns.length; i++) {
					btns[i].index = i;
					btns[i].onclick = function () {
						var s = this.index;

						new ajax(getApi.addSupplementarySignTask, {
							data: {
								memberId: member.id,
								supplementarySignInSettingId: _td.signdata[s].supplementarySignInSettingId
							},
							success: function (e) {
								var _msg = e.returnMsg;

								if (_msg == "请求成功") {
									_ts.clickTab2();
								} else {
									delayTip(_msg);
								}
							}
						});
					}
				}

				if (_black) {
					shield(_list.blackReason);
					return;
				}
			}
		});
	},

	// 我领取的任务
	clickTab2: function (_tab) {
		var _ts = this,
			_td = _ts.data,
			_tab = 1;

		checkin_buqian.find('.checkin-1').addClass('hide');
		checkin_buqian.find('.my-buqian').addClass('f50');
		checkin_buqian.find('.msg-buqian').removeClass('f50');
		checkin_buqian.find('.checkin-2').removeClass('hide');

		require(['qpTime'], function () {
			new ajax(getApi.getMySupplementarySignTaskList, {
				data: {
					memberId: member.id
				},
				success: function (e) {
					var _list = e.returnData,
						_data = _list.dataList,
						_len = _data.length,
						_black = _list.isBlack;
						_len = _data.length;

					if (_len) {
						for (var i = 0; i < _len; i++) {
							var _di = _data[i];
							_di.timer = _td.time - (_list.currentDate - _di.beginTime);
						}
					}

					if (_black) {
						shield(_list.blackReason);
						return;
					}
					checkin_buqian_list2.html(template('checkin_buqian_tpl_con2', _list));
					_ts.lunbo1();

					_ts.timeRun();
					
					var arr = checkin_buqian.find(".msg-btn");

					for (var i = 0; i < arr.length; i++) {
						arr[i].index = i;

						arr[i].onclick = function () {
							var s = this.index;
							_td.memberSupplementarySignInId = _data[s].memberSupplementarySignInId;

							if (_data[s].taskStatus == 0) {
								getMember(function () {
									checkin_share.url = real_url.replace('m.xgbuy.cc', _td.host).split(redirect_url)[0] + redirect_url + 'activity/newsign/buqian/detail/index.html?memberId=' + member.id + "&memberSupplementarySignInId=" + _td.memberSupplementarySignInId;
									shareHand.update(checkin_share, {
										start: function () {
											closeSelf('.popup-doyoushare');
										}
									});
								});
								_ts.share();
							}
						}
					}
				}
			});
		});
	},

	timer: function (e) {
		return famateNumber(e / 1e3 / 60 / 60) + ':' + famateNumber(e / 1e3 / 60 % 60) + ':' + famateNumber(e / 1e3 % 60);
	},

	timeRun: function (e) {
		var _ts = this,
			_td = _ts.data;

		$.each(checkin_buqian_list2.find('time'), function () {
			var $ts = $(this);

			$.qpTime({
				s: 1,
				e: $ts.data('timer')
			}, function (pt) {
				$ts.html(_ts.timer(pt.t));
			});
		});
	},

	//文字轮播
	lunbo: function () {
		var $this = checkin_buqian.find(".scrollNews");
		var scrollTimer;

		$this.hover(function () {
			clearInterval(scrollTimer);
		}, function () {
			scrollTimer = setInterval(function () {
				scrollNews($this);
			}, 2e3);
		}).trigger("mouseout");

		function scrollNews(nature) {
			var scroll = nature.find("ul:first");
			var lineHeight = scroll.find("li:first").height();

			scroll.animate({
				"margin-top": -lineHeight + "px"
			}, 1e3, function () {
				scroll.css({
					"margin-top": "0px"
				}).find("li:first").appendTo(scroll);
			});
		}
	},

	//文字轮播
	lunbo1: function () {
		var $this = checkin_buqian.find(".scrollNew");
		var scrollTimer;

		$this.hover(function () {
			clearInterval(scrollTimer);
		}, function () {
			scrollTimer = setInterval(function () {
				scrollNew($this);
			}, 2e3);
		}).trigger("mouseout");

		function scrollNew(nature) {
			var scroll = nature.find("ul:first");
			var lineHeight = scroll.find("li:first").height();

			scroll.animate({
				"margin-top": -lineHeight + "px"
			}, 1e3, function () {
				scroll.css({
					"margin-top": "0px"
				}).find("li:first").appendTo(scroll);
			});
		}
	},

	// 分享
	share: function () {
		checkin_share.url && shareHand.show({
			wx: 1,
			title: '分享到'
		});
	}
};

getTpl(function () {
	buQian.init();
});