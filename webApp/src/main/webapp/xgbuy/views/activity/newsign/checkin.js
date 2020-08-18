var checkin = $('.page[data-id="activity_newsign"]:visible'),
	checkin_scroll = checkin.find('.scroll-more'),
	checkin_tab1 = checkin.find('.tab-1'),
	checkin_tabcar = checkin.find('.tab-car'),
	checkin_tab2 = checkin.find('.tab-2'),
	checkin_tab3 = checkin.find('.tab-3'),
	checkin_table = checkin.find('.tab-3 .tab-box .tab-box1'),
	checkin_sign = checkin.find('.popup-sign .con'),
	checkin_buqian = checkin.find('.popup-buqian .con-buqian'),

	checkin_xiangzi = checkin.find('.popup-xiangzi .con-xiangzi');

var checkinApi = {
	data: {
		post: 1,
		propValId: [],
		sku: [],
		propValIds: [],
		type: 1
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
			_ts.role();
			shareHand.update();
		}, function () {
			getApi.getSignInHomePage = getApi.getSignInHomePageH5;
			getApi.addMemberNewSignIn = getApi.addMemberNewSignInH5;
			getApi.addTiredSignGift = getApi.addTiredSignGiftH5;
			getApi.updateUserInfo = getApi.updateUserInfoH5;
		});
	},

	post: function () {
		var _ts = this,
			_td = _ts.data;

		_td.type = 1;

		new ajax(getApi.getSignInHomePage, {
			data: {
				memberId: member.id,
				type: _td.type
			},
			success: function (e) {
				var _list = e.returnData,
					currentDate = _list.currentDate,
					currentSignInSettingId = _list.currentSignInSettingId,
					monthSignInList = _list.monthSignInList,
					supplementaryCardNum = _list.supplementaryCardNum,
					_black = _list.isBlack,
					_isSignInRemind = _list.isSignInRemind;

				_td.supplementaryCardNum = supplementaryCardNum;
				_td.currentSignInSettingId = currentSignInSettingId;
				_td.isSignInRemind = _isSignInRemind;

				if (_black) {
					shield(_list.blackReason);
					return;
				}

				if (currentDate) {
					_td.time = new Date(currentDate).getFullYear() + "年" + (new Date(currentDate).getMonth() + 1) + "月";
					checkin.find(".time-show").empty();
					checkin.find(".time-show").append(_td.time);
				};

				checkin_tab1.html(template('checkin_tpl_title', _list));
				checkin_tab2.html(template('checkin_tpl_button'));
				checkin_table.html(template('checkin_tpl_table', _list));
				checkin_buqian.html(template('checkin_tpl_buqian', _list));

				document.getElementById("rowtitle1").classList.add("hide");
				document.getElementById("rowtitle").classList.remove("hide");

				if (member.id) {
					if (_list.currentSignInStatus == 1) {
						if (member.sys == 'android') {
							checkAppVer(49, function () {
								getSignDate(currentDate);
							});
						} else if (member.sys == 'ios') {
							checkAppVer(51, function () {
								callIntentTrailer('intentTrailerListNative', 'getSignDate', { 'timer': currentDate });
							});
						}
					};

					if (member.sys == "android") {
						document.getElementById("changebox1").classList.remove("tab-box3");
						document.getElementById("changebox1").classList.add("tab-box2");
						if ($('.tab-3').width() - $('.tab-box1').width() < 0) {
							document.getElementById("changebox2").classList.remove("tab-3x");
							document.getElementById("changebox2").classList.add("tab-3f");
						}
					};

					if (member.app) {
						checkin_btn = checkin_tab1.find('.msg-send');
						checkin_btn.removeClass('hide');
					}

					var tits = checkin.find(".td-title");

					for (var i = 0; i < tits.length; i++) {
						_td.signIntime = monthSignInList[i].signInDate
						var monthTime = (new Date(_td.signIntime).getMonth() + 1) + "." + (new Date(_td.signIntime).getDate());
						$(tits[i]).empty();
						$(tits[i]).append(monthTime);
					}

					// 判断是否补签
					var arr = checkin.find(".tab-tds");

					for (var i = 0; i < arr.length; i++) {
						arr[i].index = i;

						arr[i].onclick = function () {
							var s = this.index;

							if ($(arr[s]).is(".tab-td1")) {
								_td.signInSettingId = monthSignInList[s].signInSettingId;
								_td.signInDate = monthSignInList[s].signInDate;

								var timesed = (new Date(_td.signInDate).getMonth() + 1) + "月" + (new Date(_td.signInDate).getDate()) + "日";
								checkin.find(".buqian-data").empty();
								checkin.find(".buqian-data").append(timesed);
								popupShow('.popup-buqian');
							}
						}
					};

					var td1 = checkin.find(".tab-td2");

					if (td1.length) {
						td1[0].onclick = function () {
							_ts.getCash();
						}
					}
				} else {
					document.getElementById("btn2").classList.add("tab-time2");
					document.getElementById("btn1").classList.remove("tab-time2");

					var tits = checkin.find(".td-title");

					for (var i = 0; i < tits.length; i++) {
						_td.signIntime = monthSignInList[i].signInDate;

						var monthTime = (new Date(_td.signIntime).getMonth() + 1) + "." + (new Date(_td.signIntime).getDate());
						$(tits[i]).empty();
						$(tits[i]).append(monthTime);
					}
				}
			},
			error: function () {
				delayTip('网络异常');
			},
			limit: 1
		});
	},

	// 补签
	getSupplementary: function () {
		var _ts = this,
			_td = _ts.data;

		if (member.sys == "web") {
			delayTip('请下载醒购APP签到');
			return;
		};

		if (_td.supplementaryCardNum > 0) {
			new ajax(getApi.addMemberNewSignIn, {
				data: {
					memberId: member.id,
					signInSettingId: _td.signInSettingId,
					signInClient: 1
				},
				success: function (e) {
					_ts.post()
					_ts.closeSign()
					var _list = e.returnData;
					checkin_sign.html(template('checkin_tpl_sign', _list));
					popupShow('.popup-sign');
				},
				limit: 1
			});
		} else {
			_ts.post();
			_ts.closeSign();
			delayTip("补签卡数量不足");
		}
	},

	// 红包提现
	getMyhongbao: function () {
		getMember(function () {
			getUrl('activity/newsign/draw/index.html', 'self');
		});
	},

	// 积分商城
	getMyjifen: function () {
		getMember(function () {
			if (member.sys == 'android') {
				intentIntegralMall();
			} else if (member.sys == 'ios') {
				callIntentTrailer('intentTrailerListNative', 'intentIntegralMall');
			} else {
				getUrl('integral/detail/index.html');
			}
		});
	},

	// 优惠卷
	getMyyouhuijuan: function () {
		getMember(function () {
			if (member.sys == 'android') {
				intentCoupon();
			} else if (member.sys == 'ios') {
				checkAppVer(50, function () {
					callIntentTrailer('intentTrailerListNative', 'intentCoupon');
				});
			} else {
				getUrl('coupon/index');
			}
		});
	},

	// 签到领现金红包
	getCash: function () {
		var _ts = this,
			_td = _ts.data;

		if (member.sys == "web") {
			delayTip('请下载醒购APP签到');
			return;
		};

		new ajax(getApi.addMemberNewSignIn, {
			data: {
				memberId: member.id,
				signInSettingId: _td.currentSignInSettingId,
				signInClient: 1
			},
			success: function (e) {
				var _list = e.returnData,
					_currentDate = _list.currentDate;

				if (member.sys == 'android') {
					checkAppVer(49, function () {
						getSignDate(_currentDate);
					});
				} else if (member.sys == 'ios') {
					checkAppVer(51, function () {
						callIntentTrailer('intentTrailerListNative', 'getSignDate', { 'timer': _currentDate });
					});
				};

				checkin_sign.html(template('checkin_tpl_sign', _list));
				_ts.post();
				popupShow('.popup-sign');
			},
			limit: 1
		});
	},

	// 上一月
	click_last: function () {
		var _ts = this,
			_td = _ts.data;

		_td.type = 2;

		var timed = new Date().getTime() - (new Date().getDate() * 3600 * 24 * 1000);

		new ajax(getApi.getSignInHomePage, {
			data: {
				memberId: member.id,
				type: _td.type
			},
			success: function (e) {
				var _list = e.returnData,
					currentDate = _list.currentDate,
					monthSignInList = _list.monthSignInList,
					_black = _list.isBlack;

				if (currentDate) {
					_td.time = new Date(timed).getFullYear() + "年" + (new Date(timed).getMonth() + 1) + "月";
					checkin.find(".time-show").empty();
					checkin.find(".time-show").append(_td.time);
				};

				checkin_tab1.html(template('checkin_tpl_title', _list));
				checkin_tab2.html(template('checkin_tpl_button'));
				checkin_table.html(template('checkin_tpl_table', _list));

				document.getElementById("rowtitle").classList.add("hide");
				document.getElementById("rowtitle1").classList.remove("hide");
				document.getElementById("btn1").classList.add("tab-time2");
				document.getElementById("btn2").classList.remove("tab-time2");
				checkin.find(".span-qiandao").empty();
				checkin.find(".span-qiandao").html("签到记录");

				if (member.app) {
					checkin_btn = checkin_tab1.find('.msg-send');
					checkin_btn.removeClass('hide');
				}

				// 所有的具体时间
				var tits = checkin.find(".td-title");

				for (var i = 0; i < tits.length; i++) {
					_td.signIntim = monthSignInList[i].signInDate
					var monthTim = (new Date(_td.signIntim).getMonth() + 1) + "." + (new Date(_td.signIntim).getDate());
					$(tits[i]).empty();
					$(tits[i]).append(monthTim);
				}

				if (_black) {
					shield(_list.blackReason);
					return;
				}
			},
			limit: 1
		});
	},

	// 当前月
	click_now: function () {
		var _ts = this,
			_td = _ts.data;

		document.getElementById("btn2").classList.add("tab-time2");
		document.getElementById("btn1").classList.remove("tab-time2");
		_ts.post()
	},

	// 签到领箱子
	getGift: function () {
		var _ts = this,
			_td = _ts.data;

		var btns = checkin.find(".sign-xiangzi");

		for (var i = 0; i < btns.length; i++) {
			btns[i].index = i;
			btns[i].onclick = function () {
				var s = this.index;

				new ajax(getApi.getSignInHomePage, {
					data: {
						memberId: member.id,
						type: _td.type,
					},
					success: function (e) {
						var _list = e.returnData,
							cumulativeAwardList = _list.cumulativeAwardList,
							currentDate = _list.currentDate;

						_black = _list.isBlack;

						var currentDatenow = new Date(currentDate).getDate();

						if (_td.type == 1) {
							cumulativeAwardList[s].dateTimenow = currentDatenow;
							dateTimenow = cumulativeAwardList[s].dateTimenow;
						} else {
							cumulativeAwardList[s].dateTimenow = 31;
							dateTimenow = cumulativeAwardList[s].dateTimenow;
						};

						checkin_xiangzi.html(template('checkin_tpl_xiangzi', cumulativeAwardList[s]));

						if (checkin.find('.tab-msgs').children('.h155').length <= 3) {
							checkin.find('.tab-msgs').addClass('jc');
						} else {
							if (checkin.find('.tab-msgs').hasClass('js')) {
								checkin.find('.tab-msgs').removeClass('jc');
							}
						};

						if (cumulativeAwardList) {
							_td.cumulativeSignInSettingId = cumulativeAwardList[s].cumulativeSignInSettingId;
							if (cumulativeAwardList[s].productMap) {
								_td.productId = cumulativeAwardList[s].productMap.productId;
								_td.stockSum = cumulativeAwardList[s].productMap.stockSum;
							} else {
								_td.productId = '';
								_td.stockSum = 0;
							}
						}
						popupShow('.popup-xiangzi');
					}
				});
			}
		}
	},

	// 关闭签到
	closeSign: function (_this) {
		var _ts = this;

		_ts.post();
		closeSelf(_this);
	},

	//领取累签的商品 
	click_getgift: function () {
		var _ts = this,
			_td = _ts.data;

		if (_td.stockSum > 0) {
			new ajax(getApi.getProductSkuInfo, {
				data: {
					memberId: member.id,
					id: _td.productId,
				},
				success: function (e, i) {
					var _list = e.returnData,
						_black = _list.isBlack,
						_productId = _list.productId;
					_standardMapList = _list.standardMapList,
						_propValueMapList = _standardMapList.propValueMapList;
					_td.productId = _productId
					_ts.post();

					var arr = [];
					for (var i = 0; i < _standardMapList.length; i++) {
						arr.push(_standardMapList[i].propId)
					}
					_td.propIds = arr;

					checkin_tabcar.html(template('checkin_goods_detail_tpl', _list));

					popupShow('.popup-add-cart');

					// 选择规格
					var uls = checkin.find(".info-list ul");
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
									checkin.find('.no').hasClass('cur') && checkin.find('.no').removeClass('cur');
									checkin.find('.img-pic').attr('src', _list.skuPic_S);
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
									checkin.find('.no').hasClass('cur') && checkin.find('.no').removeClass('cur');
								}
							});
						}
					}
				}
			});
		} else {
			new ajax(getApi.addTiredSignGift, {
				data: {
					memberId: member.id,
					type: 1,
					cumulativeSignInSettingId: _td.cumulativeSignInSettingId
				},
				success: function () {
					_ts.post();
					closeSelf('.popup-xiangzi');
					delayTip("领取成功");
				}
			});
			_ts.post();
		}
	},

	// 商品结算页面
	goodsDetailSubmit: function () {
		var _ts = this,
			_td = _ts.data;

		var arr = checkin.find(".cur");
		var arrs = checkin.find(".img-span");

		if (arrs.length > 0) {
			if (arrs) {
				for (var i = 0; i < arrs.length; i++) {
					var aa = checkin.find(".info-list");

					if (!($(aa[i])).find('.cur').length) {
						delayTip('请选择商品' + $(arrs[i]).text());
						_td.sku = [];
						return
					}
				}
				var cc = checkin.find('.cur');

				if (cc.length == arrs.length) {
					getMember(function () {
						getUrl('activity/newsign/firm/index.html?productId=' + _td.productId + "&memberId=" + member.id + "&productItemId=" + _td.skuId + "&cumulativeSignInSettingId=" + _td.cumulativeSignInSettingId, 'self');
					});
				}
			}
		} else {
			_td.skuId = '';

			getMember(function () {
				getUrl('activity/newsign/firm/index.html?productId=' + _td.productId + "&memberId=" + member.id + "&productItemId=" + "&productItemId=" + _td.skuId + "&cumulativeSignInSettingId=" + _td.cumulativeSignInSettingId, 'self');
			});
		}
	},

	// 轮播文字
	role: function () {
		var _ts = this;

		new ajax(getApi.getSignInBroadcastContent, {
			data: {
				memberId: member.id,
				type: 2
			},
			success: function (e) {
				var _list = e.returnData;

				if (_list.length && !checkin_scroll.find('.role-texts').length) {
					var _role = $('<div class="role-texts"></div>');
					_role.html(template('checkin_tpl_role', e));
					checkin_scroll.append(_role);
					_list.length > 1 && _ts.roleRun();

					if (member.app) {
						checkin_role = checkin_scroll.find('.role-texts');
						checkin_role.addClass('t10');
					}
				}
				checkin_role = checkin_tab1.find('.role-texts');
			}
		});
	},

	roleRun: function () {
		require(['vticker'], function () {
			checkin.find('.role-texts').vTicker({
				speed: 1500,
				pause: 3e3
			});
		});
	},
	
	openSend: function () {
		var _ts = this;

		getCheckInReminder(function (isRemind) {
			if (isRemind != undefined) { // 判断APP支持该方法
				if (isRemind && isRemind != 0) {
					// 不弹出去设置确认框
					// 调用消息推送接口
					_ts.longer();
				} else {
					// 弹出去设置确认框
					popupShow('.popup-clear');
				}
			}
		});
	},

	longer: function () {
		var _ts = this,
			_td = _ts.data;
		checkin_sendCycle = checkin_tab1.find('.sign-remind');
		
		if (checkin_sendCycle.hasClass('send-cycle') && _td.isSignInRemind == 1) {
			_ts.openSend();
		} else {
			if (member.sys == 'android') {
				checkAppVer(57, function () {
					if (checkin_sendCycle.hasClass('send-cycle')) {
						new ajax(getApi.updateUserInfo, {
							data: {
								memberId: member.id,
								type: 2,
								isAcceptPush: 1
							},
							success: function () {
								checkin_sendCycle.removeClass('send-cycle');
								checkin_sendCycle.addClass('send-cycles');
								setTimeout(function () {
									checkin_sendCycle.find('.send-right').removeClass('hide');
								},0.2*1e3)
								delayTip("已成功开启提醒");
								closeSelf('.popup-clear');
							}
						});
					} else if (checkin_sendCycle.hasClass('send-cycles')) {
						popupShow('.popup-tell');
					}
				});
			} else if (member.sys == 'ios') {
				checkAppVer(58, function () {
					if (checkin_sendCycle.hasClass('send-cycle')) {
						new ajax(getApi.updateUserInfo, {
							data: {
								memberId: member.id,
								type: 2,
								isAcceptPush: 1
							},
							success: function () {
								checkin_sendCycle.removeClass('send-cycle');
								checkin_sendCycle.addClass('send-cycles');
								setTimeout(function () {
									checkin_sendCycle.find('.send-right').removeClass('hide');
								},0.2*1e3)
								delayTip("已成功开启提醒");
								closeSelf('.popup-clear');
							}
						});
					} else if (checkin_sendCycle.hasClass('send-cycles')) {
						popupShow('.popup-tell');
					}
				});
			} else {
				delayTip('请下载最新版本！')
			}
		}
	},

	closeOpen: function () {
		var _ts = this;
		checkin_sendCycle = checkin_tab1.find('.sign-remind');

		new ajax(getApi.updateUserInfo, {
			data: {
				memberId: member.id,
				type: 2,
				isAcceptPush: 2
			},
			success: function () {
				checkin_sendCycle.removeClass('send-cycles');
				checkin_sendCycle.addClass('send-cycle');
				checkin_sendCycle.find('.send-right').addClass('hide');

				delayTip("已关闭提醒");
				_ts.post();
				closeSelf('.popup-tell');
			}
		});
	}
};

getTpl(function () {
	checkinApi.init();
});

function onShow() {
	traffic.setPv(checkinApi.data);
}