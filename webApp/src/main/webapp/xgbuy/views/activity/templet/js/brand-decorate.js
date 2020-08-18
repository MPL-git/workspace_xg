var brand_decorate_scroll = $('.page:visible .scroll-decorate').eq(0),
	brand_decorate_con = $('.decorate:visible .content-decorate');

var brand_decorate_api = {
	cur: 1,
	pag: 10,
	post: 1,
	iosNative: 'intentTrailerListNative', // ios协议
	nav: [], // 导航栏模块
	nest: 0, // 是否嵌套引用
	url: undefined, // 请求接口
	type: undefined, // 新人专享等
	infoid: undefined, // 商城
	areaid: undefined, // 会展模块id
	pageid: undefined // 自建模块id
};

var header_height = $('.page:visible header').is(':visible') ? $('.page:visible header').height() : 0,
	module_id = '',
	page_left = '',
	page_width = 0,
	nav_height = 0,
	module_type = false,
	seckill_cur_time = 0,
	brand_decorate_11 = '',
	brand_decorate_11_cur = 0,
	seckillList_nav_this = '', // 秒杀
	brand_decorate_header = '', // 类型10
	brand_decorate_header_scroll = true, // 类型10阻止滑动触发
	decorateHeaderHeight = 0; // 类型10顶部栏高度

var seckillTimer = null; // 秒杀计时器

// 会场秒杀
var decorateType6 = {
	type: 6,
	tabList: '', // tab列
	conList: '', // con列
	tabSelect: '', // 选中tab
	sysCur: 0, // 本地当前时间
	tabIndex: ''
};
// 优惠券秒杀
var decorateType15 = {
	type: 15,
	tabList: '', // tab列
	conList: '', // con列
	tabSelect: '', // 选中tab
	tabIndex: 0
};

// 限时秒杀
var decorateType11 = {
	type: 11,
	tabList: '',
	conList: '',
	tabSelect: '',
	sysCur: 0,
	tabIndex: ''
};
// 获取包含新增子tab栏 的数据
var tabData = {
	data:''
}

var brandDecorate = {
	data: {

	},

	init: function () {
		var _ts = this,
			_td = _ts.data;
		var urlParams = real_url.split('?')[real_url.split('?').length-1].split('&');
		var meberId = urlParams.length > 1 ? urlParams.indexOf('memberId=') : 0;
		if (isxgbuy || !real_url.includes('activityAreaId') || meberId != -1) {
			brand_decorate_con.append(template('activity_decorate_header'));
		} else if (!isWeiXin) {
			brand_decorate_con.append(template('activity_decorate_download'))
		}

		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'activityAreaId': brand_decorate_api.areaid = _b; break;
					case 'infoId': brand_decorate_api.infoid = _b; break;
					case 'pageid': brand_decorate_api.pageid = _b; break;
					case 'type': brand_decorate_api.type = _b; break;
					case 'nest': brand_decorate_api.nest = _b; break;
					case 'sys': member.sys = _b; break;
				}
			}
		}
		brand_decorate_api.areaid && traffic.setPv(_td, {
			valueId: brand_decorate_api.areaid,
			pageType: '30'
		});
		brand_decorate_api.pageid && traffic.setPv(_td, {
			valueId: brand_decorate_api.pageid,
			pageType: '94'
		});
		_ts.main();
	},

	main: function () {
		var _ts = this,
			_td = _ts.data;
		if (getStorage('decorate_nest')) {
			brand_decorate_api.nest = getStorage('decorate_nest');
			removeStorage('decorate_nest');
		}

		if (brand_decorate_api.nest) {
			getStorage('decorate_type') && (brand_decorate_api.type = getStorage('decorate_type'));
			getStorage('decorate_areaid') && (brand_decorate_api.areaid = getStorage('decorate_areaid'));
			getStorage('decorate_pageid') && (brand_decorate_api.pageid = getStorage('decorate_pageid'));
			getStorage('decorate_infoid') && (brand_decorate_api.infoid = getStorage('decorate_infoid'));

			removeStorage('decorate_type');
			removeStorage('decorate_areaid');
			removeStorage('decorate_pageid');
			removeStorage('decorate_infoid');
		}

		brand_decorate_api.areaid == 'false' && (brand_decorate_api.areaid = undefined);

		if (brand_decorate_api.areaid) {
			brand_decorate_api.url = getApi.decorationMeeting;
		} else if (brand_decorate_api.pageid) {
			brand_decorate_api.url = getApi.customerpage;
			brand_decorate_con.addClass('decorate-page-product'); // 自建页面商品横向排版
		} else if (brand_decorate_api.infoid) {
			brand_decorate_api.url = getApi.getDecorateInfoPage;
		} else {
			brand_decorate_api.url = getApi.brandDecorate;
		}

		brand_decorate_api.type == 2 && (brand_decorate_api.iosNative = 'newExclusiveListNative');

		require(['template', 'qpTime'], function (template) {
			//登录退出同步memberid
			if (member.sys != 'web' && queryMember.id != member.id && queryMember.id == "") {
				member.id = ""
			}

			new ajax(brand_decorate_api.url, {
				data: {
					memberId: member.id,
					// memberId: queryMember.id,
					type: brand_decorate_api.type,
					decorateInfoId: brand_decorate_api.infoid,
					activityAreaId: brand_decorate_api.areaid,
					customPageId: brand_decorate_api.pageid
				},
				success: function (res) {
					var _list = res.returnData;

					if (!_list) {
						(!brand_decorate_api.areaid && !brand_decorate_api.pageid) ? $('.nest-home-decorate').remove() : brand_decorate_con.html(template('activity_decorate_end_tpl'));
						return;
					}

					// 标题
					var _title = _list.decorateInfoName;

					if (brand_decorate_api.areaid) {
						_title = _list.activityAreaName;
					} else if (brand_decorate_api.pageid) {
						_title = _list.pageName;
					}
					
					// (_title && !brand_decorate_api.nest) && setTitle(_title);
					(_title && !brand_decorate_api.nest) && $('header p').html(_title);

					// banner图
					if (_list.activityAreaTopPic) {
						_list.activityAreaTopPic = imgCache(_list.activityAreaTopPic, 'decorate_brand');
						brand_decorate_con.append('<div class="w75"><img src="' + _list.activityAreaTopPic + '" onerror="imgErrorEmpty(this)"></div>');
					}

					/*// 时间
					var _now = _list.currentTime,
						_end = _list.activityEndTime,
						_start = _list.activityBeginTime,
						_text = _list.activityTimeText;

					// 隐藏倒计时
					if (_now) {
						if (_now < _start) {
							brand_decorate_con.append('<div class="decorate-time flex dc ac jc"><h3>' + _text + '</h3><p></p></div>');
						} else {
							var _ends = new Date(_end),
								_starts = new Date(_start);

							brand_decorate_con.append('<div class="decorate-time flex dc ac jc"><h3>活动时间</h3><p><b>' + _starts.getFullYear() + '.' + (1 + _starts.getMonth()) + '.' + _starts.getDate() + ' ' + famateNumber(_starts.getHours()) + ':' + famateNumber(_starts.getMinutes()) + ' - ' + _ends.getFullYear() + '.' + (1 + _ends.getMonth()) + '.' + _ends.getDate() + ' ' + famateNumber(_ends.getHours()) + ':' + famateNumber(_ends.getMinutes()) + '</b></p></div>');
						}

						brand_decorate_con.addClass('time-ci');
					}*/

					var brand_decorate_is_empty = true;
					var traffic_dtl_2 = 0; // 特卖流量统计, 不规则排布顺序

					if (_list.decorateAreaList.length) {
						var box_list = $('<div class="box-list"><div class="nav flex hide"></div></div>');
						brand_decorate_11_cur = _list.currentTime - 1e3;

						$.each(_list.decorateAreaList, function (i) {
							var _daid = this.decorateAreaId;
							// if(i>6) return
							if (brand_decorate_api.areaid) {
								brand_decorate_api.nav.push({
									name: this.decorateAreaName,
									index: i
								});

								box_list.append($('<div class="nav-hide" data-index="' + i + '" data-mid="' + _daid + '"></div>'));
							}

							this.decorateModuleList = imgCacheDecorateModulePic(this.decorateModuleList, 'decorate_type1');

							$.each(this.decorateModuleList, function () {
								if ((this.moduleType == 1 || this.moduleType == 8 || this.moduleType == 9) && this.decorateModulePic) {
									// 热点模块
									var box_svg = $('<div class="box-svg w75" data-mid="' + _daid + '"><img src="' + this.decorateModulePic + '" onerror="imgErrorEmpty(this)"></div>');

									if (this.moduleMapList.length) {
										$.each(this.moduleMapList, function () {
											var _mts = this,
												_map = _mts.corrds.split(','),
												time = '';
											if (_mts.linkType == 34 && _mts.linkValue == 1) {
												//判断是否有领取过
												if (member.id && member.sys == 'web') {
													new ajax(getApi.checkRec, {
														data: {
															memberId: member.id
														},
														success: function (e) {
															if (e.returnData.received) {
																box_svg.append($('<a class="box-frame' + (_mts.msgType == 1 ? ' out flex ac jc out-get' : '') + (_mts.msgType == 2 ? ' out flex ac jc out-stock' : '') + (_mts.msgType == 3 ? ' out flex ac jc out-time' : '') + '" onclick=""'
																	+ 'data-ad="' + _mts.adCatalog + '"'
																	+ 'data-mid="' + _mts.linkValue + '"'
																	+ 'data-name="' + _mts.adCatalogName + '"'
																	+ 'data-url="' + _mts.linkUrl + '"'
																	+ 'data-type="' + _mts.linkType + '"'
																	+ 'data-link="' + _mts.linkValue + '"'
																	+ 'data-productid="' + _mts.linkValue + '"'
																	+ 'data-activityid="' + _mts.linkValue + '"'
																	+ 'data-recbeg="' + _mts.recBeginDate + '"'
																	+ 'data-activityareaid="' + brand_decorate_api.areaid + '"'
																	+ 'data-msgtype="' + _mts.msgType + '"'
																	+ 'data-rectype="' + _mts.recType + '"'
																	+ 'data-pos="' + traffic_dtl_2 + '"'
																	+ 'data-svip="' + _mts.isSvip + '"'
																	+ 'style="left: ' + Math.min(_map[0], _map[2]) + '%; top: ' + Math.min(_map[1], _map[3]) + '%; width: ' + Math.abs(_map[2] - _map[0]) + '%; height: ' + Math.abs(_map[3] - _map[1]) + '%;background: ' + "url(../views/activity/templet/images/yilingqu11.png) no-repeat right" + ';background-size: contain;"'
																	+ '></a>'));
															} else {
																box_svg.append($('<a class="box-frame' + (_mts.msgType == 1 ? ' out flex ac jc out-get' : '') + (_mts.msgType == 2 ? ' out flex ac jc out-stock' : '') + (_mts.msgType == 3 ? ' out flex ac jc out-time' : '') + '" onclick="getMapUrl(this, \'' + brand_decorate_api.iosNative + '\')"'
																	+ 'data-ad="' + _mts.adCatalog + '"'
																	+ 'data-mid="' + _mts.linkValue + '"'
																	+ 'data-name="' + _mts.adCatalogName + '"'
																	+ 'data-url="' + _mts.linkUrl + '"'
																	+ 'data-type="' + _mts.linkType + '"'
																	+ 'data-link="' + _mts.linkValue + '"'
																	+ 'data-productid="' + _mts.linkValue + '"'
																	+ 'data-activityid="' + _mts.linkValue + '"'
																	+ 'data-recbeg="' + _mts.recBeginDate + '"'
																	+ 'data-activityareaid="' + brand_decorate_api.areaid + '"'
																	+ 'data-msgtype="' + _mts.msgType + '"'
																	+ 'data-rectype="' + _mts.recType + '"'
																	+ 'data-pos="' + traffic_dtl_2 + '"'
																	+ 'data-svip="' + _mts.isSvip + '"'
																	+ 'style="left: ' + Math.min(_map[0], _map[2]) + '%; top: ' + Math.min(_map[1], _map[3]) + '%; width: ' + Math.abs(_map[2] - _map[0]) + '%; height: ' + Math.abs(_map[3] - _map[1]) + '%;"'
																	+ '></a>'));
															}
														}
													})
												} else if (queryMember.id && member.token && member.sys == 'android' || queryMember.id && member.token && member.sys == 'ios') {
													new ajax(getApi.appLogin, {
														data: {
															memberId: queryMember.id,
															token: member.token
														},
														success: function (e) {
															new ajax(getApi.checkRec, {
																data: {
																	memberId: queryMember.id
																},
																success: function (e) {

																	if (e.returnData.received) {
																		box_svg.append($('<a class="box-frame' + (_mts.msgType == 1 ? ' out flex ac jc out-get' : '') + (_mts.msgType == 2 ? ' out flex ac jc out-stock' : '') + (_mts.msgType == 3 ? ' out flex ac jc out-time' : '') + '" onclick=""'
																			+ 'data-ad="' + _mts.adCatalog + '"'
																			+ 'data-mid="' + _mts.linkValue + '"'
																			+ 'data-name="' + _mts.adCatalogName + '"'
																			+ 'data-url="' + _mts.linkUrl + '"'
																			+ 'data-type="' + _mts.linkType + '"'
																			+ 'data-link="' + _mts.linkValue + '"'
																			+ 'data-productid="' + _mts.linkValue + '"'
																			+ 'data-activityid="' + _mts.linkValue + '"'
																			+ 'data-recbeg="' + _mts.recBeginDate + '"'
																			+ 'data-activityareaid="' + brand_decorate_api.areaid + '"'
																			+ 'data-msgtype="' + _mts.msgType + '"'
																			+ 'data-rectype="' + _mts.recType + '"'
																			+ 'data-pos="' + traffic_dtl_2 + '"'
																			+ 'data-svip="' + _mts.isSvip + '"'
																			+ 'style="left: ' + Math.min(_map[0], _map[2]) + '%; top: ' + Math.min(_map[1], _map[3]) + '%; width: ' + Math.abs(_map[2] - _map[0]) + '%; height: ' + Math.abs(_map[3] - _map[1]) + '%;background: ' + "url(../views/activity/templet/images/yilingqu11.png) no-repeat right" + ';background-size: contain;"'
																			+ '></a>'));
																	} else {
																		box_svg.append($('<a class="box-frame' + (_mts.msgType == 1 ? ' out flex ac jc out-get' : '') + (_mts.msgType == 2 ? ' out flex ac jc out-stock' : '') + (_mts.msgType == 3 ? ' out flex ac jc out-time' : '') + '" onclick="getMapUrl(this, \'' + brand_decorate_api.iosNative + '\')"'
																			+ 'data-ad="' + _mts.adCatalog + '"'
																			+ 'data-mid="' + _mts.linkValue + '"'
																			+ 'data-name="' + _mts.adCatalogName + '"'
																			+ 'data-url="' + _mts.linkUrl + '"'
																			+ 'data-type="' + _mts.linkType + '"'
																			+ 'data-link="' + _mts.linkValue + '"'
																			+ 'data-productid="' + _mts.linkValue + '"'
																			+ 'data-activityid="' + _mts.linkValue + '"'
																			+ 'data-recbeg="' + _mts.recBeginDate + '"'
																			+ 'data-activityareaid="' + brand_decorate_api.areaid + '"'
																			+ 'data-msgtype="' + _mts.msgType + '"'
																			+ 'data-rectype="' + _mts.recType + '"'
																			+ 'data-pos="' + traffic_dtl_2 + '"'
																			+ 'data-svip="' + _mts.isSvip + '"'
																			+ 'style="left: ' + Math.min(_map[0], _map[2]) + '%; top: ' + Math.min(_map[1], _map[3]) + '%; width: ' + Math.abs(_map[2] - _map[0]) + '%; height: ' + Math.abs(_map[3] - _map[1]) + '%;"'
																			+ '></a>'));
																	}
																}
															})
														}
													})
												} else {
													box_svg.append($('<a class="box-frame' + (_mts.msgType == 1 ? ' out flex ac jc out-get' : '') + (_mts.msgType == 2 ? ' out flex ac jc out-stock' : '') + (_mts.msgType == 3 ? ' out flex ac jc out-time' : '') + '" onclick="getMapUrl(this, \'' + brand_decorate_api.iosNative + '\')"'
														+ 'data-ad="' + _mts.adCatalog + '"'
														+ 'data-mid="' + _mts.linkValue + '"'
														+ 'data-name="' + _mts.adCatalogName + '"'
														+ 'data-url="' + _mts.linkUrl + '"'
														+ 'data-type="' + _mts.linkType + '"'
														+ 'data-link="' + _mts.linkValue + '"'
														+ 'data-productid="' + _mts.linkValue + '"'
														+ 'data-activityid="' + _mts.linkValue + '"'
														+ 'data-recbeg="' + _mts.recBeginDate + '"'
														+ 'data-activityareaid="' + brand_decorate_api.areaid + '"'
														+ 'data-msgtype="' + _mts.msgType + '"'
														+ 'data-rectype="' + _mts.recType + '"'
														+ 'data-pos="' + traffic_dtl_2 + '"'
														+ 'data-svip="' + _mts.isSvip + '"'
														+ 'style="left: ' + Math.min(_map[0], _map[2]) + '%; top: ' + Math.min(_map[1], _map[3]) + '%; width: ' + Math.abs(_map[2] - _map[0]) + '%; height: ' + Math.abs(_map[3] - _map[1]) + '%;"'
														+ '></a>'));
												}
											}
											else if (_mts.linkType == 33) {

												time = '<div class="time-brand" style="font-size:' + _mts.fontSize + 'px;color:' + _mts.fontColor + '">' +
													'<div class="count-time" data-countDownBeginDate="' + _mts.countDownBeginDate + '" data-countDownEndDate="' + _mts.countDownEndDate + '">' +
													'<span>0</span>天' +
													'<span>0</span>时' +
													'<span>0</span>分' +
													'<span>0</span>秒' +
													'</div>' +
													'</div>'
												box_svg.append($('<a class="box-frame' + (_mts.msgType == 1 ? ' out flex ac jc out-get' : '') + (_mts.msgType == 2 ? ' out flex ac jc out-stock' : '') + (_mts.msgType == 3 ? ' out flex ac jc out-time' : '') + '" onclick="getMapUrl(this, \'' + brand_decorate_api.iosNative + '\')"'
													+ 'data-ad="' + _mts.adCatalog + '"'
													+ 'data-mid="' + _mts.linkValue + '"'
													+ 'data-name="' + _mts.adCatalogName + '"'
													+ 'data-url="' + _mts.linkUrl + '"'
													+ 'data-type="' + _mts.linkType + '"'
													+ 'data-link="' + _mts.linkValue + '"'
													+ 'data-productid="' + _mts.linkValue + '"'
													+ 'data-activityid="' + _mts.linkValue + '"'
													+ 'data-recbeg="' + _mts.recBeginDate + '"'
													+ 'data-activityareaid="' + brand_decorate_api.areaid + '"'
													+ 'data-msgtype="' + _mts.msgType + '"'
													+ 'data-rectype="' + _mts.recType + '"'
													+ 'data-pos="' + traffic_dtl_2 + '"'
													+ 'data-svip="' + _mts.isSvip + '"'
													+ 'style="left: ' + Math.min(_map[0], _map[2]) + '%; top: ' + Math.min(_map[1], _map[3]) + '%; width: ' + Math.abs(_map[2] - _map[0]) + '%; height: ' + Math.abs(_map[3] - _map[1]) + '%;"'
													+ '>' + time + '</a>'));
											}
											else {
												box_svg.append($('<a class="box-frame' + (_mts.msgType == 1 ? ' out flex ac jc out-get' : '') + (_mts.msgType == 2 ? ' out flex ac jc out-stock' : '') + (_mts.msgType == 3 ? ' out flex ac jc out-time' : '') + '" onclick="getMapUrl(this, \'' + brand_decorate_api.iosNative + '\')"'
													+ 'data-ad="' + _mts.adCatalog + '"'
													+ 'data-mid="' + _mts.linkValue + '"'
													+ 'data-name="' + _mts.adCatalogName + '"'
													+ 'data-url="' + _mts.linkUrl + '"'
													+ 'data-type="' + _mts.linkType + '"'
													+ 'data-link="' + _mts.linkValue + '"'
													+ 'data-productid="' + _mts.linkValue + '"'
													+ 'data-activityid="' + _mts.linkValue + '"'
													+ 'data-recbeg="' + _mts.recBeginDate + '"'
													+ 'data-activityareaid="' + brand_decorate_api.areaid + '"'
													+ 'data-msgtype="' + _mts.msgType + '"'
													+ 'data-rectype="' + _mts.recType + '"'
													+ 'data-pos="' + traffic_dtl_2 + '"'
													+ 'data-svip="' + _mts.isSvip + '"'
													+ 'style="left: ' + Math.min(_map[0], _map[2]) + '%; top: ' + Math.min(_map[1], _map[3]) + '%; width: ' + Math.abs(_map[2] - _map[0]) + '%; height: ' + Math.abs(_map[3] - _map[1]) + '%;"'
													+ '></a>'));
											}

											_mts.msgType == 3 && (brand_decorate_11 = 1);

											brand_decorate_api.areaid && _mts.linkType == 2 && traffic.delayRequest(function () {
												traffic.setDtl(_td, {
													type: 2,
													id: _mts.linkValue,
													pos: traffic_dtl_2
												});
											});

											traffic_dtl_2++;
										});
									}

									if (this.moduleType == 8) {
										var _box = $('<div></div>');
										box_svg.removeClass('w75');
										_box.html(box_svg)
										_box.addClass('decorate-footer scroll-x');
										box_svg = _box;
										$('.decorate:visible').addClass('decorate-seckill fn');
									}

									if (this.moduleType == 9) {
										var _x = $('<div class="scroll-x"></div>');

										_x.html('<div class="scroll-xsc">' + box_svg.html() + '</div>');
										box_svg.html(_x).addClass('decorate-scrollx').append('<div class="scroll-xcon"><span></span></div>');
										var img = new Image();
										img.src = this.decorateModulePic;
										img.onload = function () {
											box_svg.find(".scroll-xsc img").css({ "width": img.width * 750 / 124200 + "rem" });
											(isAndroid || isiOS) && box_svg.addClass('decorate-scrollxs');
										};

									}

									box_list.append(box_svg);
									brand_decorate_is_empty = false;
								} else if (this.moduleType == 2) {
									// 商品模块
									var _tbl = this.productBlockList;
									this.page = 0;
									this.conmonProduct = _tbl;
									this.areaid = brand_decorate_api.areaid
									this.conmonProduct = imgCachePic(this.conmonProduct, 'decorate_type2');

									if (this.productModuleTypeList.length > 0) {
										var that = this;
										// 商品模块
										// activity_decorate_product_tab
										var _top = this.productModuleTypeList,
											_item = '',
											box_svg = $('<div class="box-svg h100 flex"></div>'),
											_box = $('<div class="decorate-headers flex" data-mid="' + _daid + '" style="color: ' + _top.fieldFontColor + ';overflow: initial;background-size: cover;">'
												+ '<div class="decorate-header-con flex scroll-x fg"></div>'
												+'</div>');

										$.each(_top, function () {
											box_svg.append('<a class="nav-top flex ac jc add-tab" onclick="decorateJumpHeaders(this)"  data-model="'+ that.showModel +'" data-mid="' + this.productModuleTypeId + '" data-id = "'+ that.decorateModuleId +'"><p class="ellipsis">' + this.name + '</p><b style="background: ' + that.fieldSelectFontColor + ';"></b></a>');
										});

										box_svg.append('<a class="nav-top flex ac jc"><p class="">切换楼层</p><b></b></a>');
										_box.find('.decorate-header-con').html(box_svg);
										box_list.append(_box);
										addStylesheetRules([
											['.decorate-headers a.active',
												['color', that.fieldSelectFontColor],
											],
											['.decorate-headers a',
												['color', that.fieldFontColor],
											],
											['.decorate-headers',
												['background-color', that.fieldBgColor],
												// ['background-image', 'url('+ _top.fieldBgPic +')']
											],
											// ['.decorate-header.fixed',
											// 	['background-image', 'none']
											// ],
											// ['.decorate-header.on',
											// 	['background-image', 'none'],
											// 	['padding','0']
											// ]
										]);

										setTimeout(function () {
											$(".add-tab:first-child").addClass('active');
										},200)

										tabData.data = this;
										console.log(this)
									}

									//添加的是数据
									var box_list_data = $('<div style="background: '+ this.bgColor +'" class="box_list_data cl'+ this.decorateModuleId +'" data-mid="'+ this.decorateModuleId +'"></div>');
									var data_append = box_list_data.append(template('activity_decorate_product_tpl', this));
									box_list.append(data_append);

									this.conmonProduct.length && (brand_decorate_is_empty = false);
									brand_decorate_api.areaid && traffic.delayRequest(function () {
										for (var i = 0; i < _tbl.length; i++) {
											var _la = _tbl[i];

											traffic.setDtl(_td, {
												type: 3,
												id: _la.productId,
												pos: i
											});
										}
									});
								}else if (this.moduleType == 3) {
									// 特卖模块
									var _tbl = this.activityBlockList;
									this.page = 0;
									this.conmonBrand = _tbl;
									this.conmonBrand = imgCacheConmonBrandAreaEntryPic(this.conmonBrand, 'decorate_type3');
									box_list.append(template('activity_decorate_brand_tpl', this));

									this.conmonBrand.length && (brand_decorate_is_empty = false);

									brand_decorate_api.areaid && traffic.delayRequest(function () {
										for (var i = 0; i < _tbl.length; i++) {
											var _la = _tbl[i];

											_la.source == 1 ? traffic.setDtl(_td, {
												type: 1,
												id: _la.activityAreaId,
												pos: i
											}) : traffic.setDtl(_td, {
												type: 2,
												id: _la.activityId,
												pos: i
											});
										}
									});
								} else if (this.moduleType == 4) {
									// 商品列表
									var _tbl = this.activityList;
									this.page = 0;
									/*conmonProduct => productBlockList */
									this.productBlockList = this.productList;
									this.productBlockList = imgCachePic(this.productBlockList, 'decorate_type4');
									brand_decorate_api.len = this.productList.length;
									box_list.append(template('activity_decorate_product_tpl', this));

									this.productBlockList.length && (brand_decorate_is_empty = false);

									brand_decorate_api.areaid && traffic.delayRequest(function () {
										for (var i = 0; i < _tbl.length; i++) {
											var _la = _tbl[i];

											traffic.setDtl(_td, {
												type: 3,
												id: _la.productId,
												pos: i
											});
										}
									});
								} else if (this.moduleType == 5) {
									// 特卖列表
									var _tbl = this.activityList;
									this.page = 0;
									this.conmonBrand = _tbl;
									this.conmonBrand = imgCacheConmonBrandAreaEntryPic(this.conmonBrand, 'decorate_type5');
									brand_decorate_api.len = _tbl.length;
									box_list.append(template('activity_decorate_brand_tpl', this));

									this.conmonBrand.length && (brand_decorate_is_empty = false);

									brand_decorate_api.areaid && traffic.delayRequest(function () {
										for (var i = 0; i < _tbl.length; i++) {
											var _la = _tbl[i];

											_la.source == 1 ? traffic.setDtl(_td, {
												type: 1,
												id: _la.activityAreaId,
												pos: i
											}) : traffic.setDtl(_td, {
												type: 2,
												id: _la.activityId,
												pos: i
											});
										}
									});
								} else if (this.moduleType == 6) {
									// 会场秒杀
									if (this.secKillMap.productInfoList.length) {
										this.secKillMap.productInfoList = imgCacheProductPic(this.secKillMap.productInfoList, 'decorate_type6');
									}
									this.secKillMap.color = {
										btnDefaultBgColor: this.btnDefaultBgColor,
										btnSelectedBgColor: this.btnSelectedBgColor,
										defaultFontColor: this.defaultFontColor,
										selectedBgColor: this.selectedBgColor,
										selectedFontColor: this.selectedFontColor,
										timeColBgColor: this.timeColBgColor,
										totalBgColor: this.totalBgColor
									}
									box_list.append(template('activity_decorate_seckill_tpl', this.secKillMap));
									this.secKillMap.seckillList.length && (brand_decorate_is_empty = false);
									decorateType6.sysCur = (new Date).getTime();

									var seckillList6 = this.secKillMap.seckillList;

									for (var i = 0; i < seckillList6.length; i++) {
										seckillList6[i].context === '抢购中' && (decorateType6.tabIndex = i);
									}
								} else if (this.moduleType == 7) {//限时秒杀
									// 必购
									this.bgMap.productInfoList = imgCacheProductPic(this.bgMap.productInfoList, 'decorate_type7');
									box_list.append(template('activity_decorate_bgmap_tpl', this.bgMap));

									this.bgMap.bgModuleList.length && (brand_decorate_is_empty = false);
								} else if (this.moduleType == 10 && this.topMap.topFieldModuleList.length) {
									// 顶部栏
									var _top = this.topMap,
										_item = '',
										box_svg = $('<div class="box-svg h100 flex"></div>'),
										_box = $('<div class="decorate-header flex" data-mid="' + _daid + '" style="color: ' + _top.fieldFontColor + ';overflow: initial;background-size: cover;">'
											+ '<div class="decorate-header-con flex scroll-x fg"></div>'
											+ '<div class="decorate-header-btn flex ac jc fn open-btn-bg-' + _top.openBtnArrowColor + '" data-bg="' + _top.openBtnBgColor + '" onclick="showDecorateHeaderMore()" style="color: ' + _top.openBtnBgColor + ';background:none"><i></i></div>'
											+ '<div class="decorate-header-more" style="background-color: ' + _top.openFieldBgColor + '; color: ' + _top.openFontColor + ';"></div></div>');

									$.each(_top.topFieldModuleList, function () {
										box_svg.append('<a class="nav-top flex ac jc" onclick="decorateJumpHeader(this)" data-mid="' + this.linkDecorateAreaId + '"><p class="ellipsis">' + this.fieldName + '</p><b style="background: ' + _top.fieldSelectFontColor + ';"></b></a>');
									});
									_box.find('.decorate-header-more').html(box_svg.clone().addClass('fw'));
									box_svg.append('<a class="nav-top flex ac jc"><p class="ellipsis">切换楼层</p><b></b></a>');
									_box.find('.decorate-header-con').html(box_svg);

									box_list.append(_box);
									brand_decorate_is_empty = false;
									addStylesheetRules([
										['.decorate-header a.active',
											['color', _top.fieldSelectFontColor],
										],
										['.decorate-header',
											['background-color', _top.fieldBgColor],
											['background-image', 'url(' + _top.fieldBgPic + ')']
										],
										['.decorate-header.fixed',
											['background-image', 'none']
										],
										['.decorate-header.on',
											['background-image', 'none'],
											['padding', '0']
										]
									]);
								} else if (this.moduleType == 11) {
									// 限时秒杀
									if (this.secKillMap.productInfoList.length) {
										this.secKillMap.productInfoList = imgCacheProductPic(this.secKillMap.productInfoList, 'decorate_type11');
									}

									box_list.append(template('activity_decorate_rushbuy_seckill_tpl', this.secKillMap));
									this.secKillMap.seckillList.length && (brand_decorate_is_empty = false);
									decorateType11.sysCur = (new Date).getTime();

									var seckillList11 = this.secKillMap.seckillList;

									for (var i = 0; i < seckillList11.length; i++) {
										seckillList11[i].context === '抢购中' && (decorateType11.tabIndex = i);
									}
								} else if (this.moduleType == 15) {
									box_list.append(template('activity_decorate_coupon_seckill_tpl', this));
									var nextIndex = null
									if (this.timeCouponList.length) {
										brand_decorate_is_empty = false

										for (var i = 0; i < this.timeCouponList.length; i++) {
											if (this.timeCouponList[i].timeDesc == '即将开始') { nextIndex = i; break }
										}
									}
									if (nextIndex != null) {
										var begindDate = new Date(this.timeCouponList[i].recBeginDate.replace(/-/g, '/'))
										window.timeType15 && clearTimeout(window.timeType15)

										window.timeType15 = setTimeout(function () {
											seckillListNav15(brand_decorate_con.find('.con-seckill-15 .tab-nav a').eq(i), true)
										}, begindDate.getTime() - this.timeCouponList[i].currentTime)
									}

								}

								// 加载更多
								if (this.moduleType == 4 || this.moduleType == 5) {
									module_type = this.moduleType;
									module_id = this.decorateModuleId;

									brand_decorate_scroll[0].setAttribute('m-type', module_type);
									brand_decorate_scroll[0].setAttribute('m-id', module_id);
									brand_decorate_scroll[0].setAttribute('m-len', brand_decorate_api.len);
								}
							});
						});

						brand_decorate_con.append(box_list);

						//封装 方便更改状态调用
						function timelinkType33(el) {
							var _span = $(el).find('span'),

								_now = new Date(_list.currentTime),
								_end = new Date(parseInt($(el).attr("data-countdownenddate"))),
								_begin = new Date(parseInt($(el).attr("data-countdownbegindate"))),
								_ends = _end;

							if (_now.getTime() <= _begin.getTime()) {
								_ends = _begin;
							} else if (_now.getTime() <= _end.getTime()) {
							} else {
								_ends = false;
							}

							if (_ends) {
								require(['qpTime'], function () {
									$.qpTime({
										s: _now,
										e: _ends
									}, function (pt) {
										_span.eq(0).html(pt.d);
										_span.eq(1).html(pt.h);
										_span.eq(2).html(pt.m);
										_span.eq(3).html(pt.s);
										if (pt.t <= 0) {
											if (_now.getTime() <= _begin.getTime()) {
												_list.currentTime = _begin;
											} else if (_now.getTime() <= _end.getTime()) {
												_list.currentTime = _end;
											}
											timelinkType33(el)
										}
									});
								});
							}
						}
						// 倒计时
						var _time = brand_decorate_con.find('.time-brand .count-time');
						for (var _itime = 0; _itime < _time.length; _itime++) {
							timelinkType33(_time[_itime])
						}
						var brand_decorate_nav = brand_decorate_con.find('.nav'),
							// brand_decorate_time = brand_decorate_con.find('.decorate-time'),
							brand_decorate_nav_len = brand_decorate_api.nav.length,

							brand_decorate_footer = brand_decorate_con.find('.decorate-footer'),

							_scrollx = brand_decorate_con.find('.decorate-scrollx');

						brand_decorate_header = brand_decorate_con.find('.decorate-header');

						brand_decorate_header.closest('.page').addClass('decorate-header-page fn');

						// 优惠券
						if (brand_decorate_11) {
							var brand_decorate_11_time = brand_decorate_con.find('.out-time');

							$.each(brand_decorate_11_time, function () {
								var $_this = $(this);
								var _time = this.getAttribute('data-recbeg');
								$_this.append('<div></div>');
								var $_div = $_this.find('div');

								$.qpTime({
									m: 1,
									s: brand_decorate_11_cur,
									e: _time
								}, function (e) {
									if (e.t) {
										if (e.t <= 180000) {
											$_this.addClass('out-times').addClass('out');
											$_div.html(e.m + ':' + e.s + ':' + e.i);
										} else {
											$_this.attr('data-msgtype', 0).removeClass('out');
										}
									} else {
										$_div.remove();
										$_this.attr('data-msgtype', 0).removeClass('out-times').removeClass('out');
									}
								});
							});
						}

						// 类型6, 会场秒杀
						decorateType6.tabList = brand_decorate_con.find('.con-seckill-6 .tab-nav a');
						decorateType6.conList = brand_decorate_con.find('.con-seckill-6 .product-scroll a');

						if (decorateType6.tabList.length) {
							seckillListNav6(decorateType6.tabList.eq(decorateType6.tabIndex));

							// if (decorateType6.conList.length == 10) {
							// 	decorateType6.tabSelect = decorateType6.tabList.eq(decorateType6.tabIndex);

							// 	scrollHand.init(decorateType6.tabSelect.closest('.con-box').find('.product-scroll').parent(), function () {
							// 		seckillListNav6('', true);
							// 	});
							// }
						}

						// 类型11, 限时秒杀
						decorateType11.tabList = brand_decorate_con.find('.con-seckill-11 .tab-nav a');
						decorateType11.conList = brand_decorate_con.find('.con-seckill-11 .product-scroll a');

						if (decorateType11.tabList.length) {
							var _idx = decorateType11.tabIndex || 0,
								_now = decorateType11.tabList.eq(_idx),
								_next = decorateType11.tabList.eq(_idx + 1),
								_beg = _next.data('beg'),
								_cur = _now.data('cur');

							if (decorateType11.tabIndex === '') {
								_beg = _now.data('beg');
								decorateType11.tabIndex = 0;
							}

							decorateType11.tabIndex === 3 && (_beg = _now.data('end'));

							seckillListNav11(decorateType11.tabList.eq(decorateType11.tabIndex));

							/*if (decorateType11.tabIndex > 0) {
                                seckillListNav11(decorateType11.tabList.eq(decorateType11.tabIndex));
                            } else if (decorateType11.conList.length == 10) {
                                decorateType11.tabSelect = decorateType11.tabList.eq(0);

                                scrollHand.init(decorateType11.tabSelect.closest('.con-box').find('.product-scroll').parent(), function () {
                                    seckillListNav11('', true);
                                });
                            }*/

							if (_beg > _cur) {
								$.qpTime({
									s: _cur,
									e: _beg
								}, function (e) {
									if (!e.t) {
										setTimeout(function () {
											member.sys == 'android' ? refreshHomeWebview() : location.reload();
										}, 1e3);
									}
								});
							}
						}

						// 类型8
						if (brand_decorate_footer.length) {
							var _prt = brand_decorate_con.parent();
							_prt.append(brand_decorate_footer);

							var _bx = _prt.find('.decorate-footer .box-svg'),
								_img = _bx.find('img');

							_img[0].onload = function () {
								_bx.width(this.width);
							}
						}

						// 类型9
						if (_scrollx.length) {
							$.each(_scrollx.find('img'), function () {
								this.onload = function () {
									var _xsc = $(this).parent(),
										_scx = _xsc.parent(),
										_xcon = _scx.siblings('.scroll-xcon'),
										_span = _xcon.find('span'),
										w_img = $(this).width(),
										w_scx = _scrollx.width(),
										w_xcn = _xcon.width();

									if (w_img > w_scx) {
										_xsc.width(w_img);
										_span.width(w_xcn * w_scx / w_img);

										_scx.scroll(function (e) {
											_span.css('left', $(this).scrollLeft() * (w_xcn - _span.width()) / (w_img - w_scx));
										});
									} else {
										_xcon.addClass('hide');
									}
								}
							});
						}

						// 类型10
						if (brand_decorate_header.length) {
							require(['ceiling'], function () {
								// var brand_decorate_scroll = $('.page:visible .scroll-decorate').eq(0);
								var brand_decorate_scroll = $('.page:visible .scroll-decorate:last');
								setTimeout(function () {
									brand_decorate_header.ceiling(brand_decorate_scroll, $('.page:visible header').is(':visible') ? $('.page:visible header').height() : 0);
								}, 500)

							});
							brand_decorate_con.find('.decorate-header-con').scrollLeft(8)
							// brand_decorate_con.siblings('header').after(brand_decorate_header);
						}
						

						decorateType15.tabList = brand_decorate_con.find('.con-seckill-15 .tab-nav a');
						decorateType15.conList = brand_decorate_con.find('.con-seckill-15 .product-scroll a');

						//类型 15
						if (decorateType15.tabList.length) {

							seckillListNav15(decorateType15.tabList.eq(0));
						}

						// 存在分区名的分区大于1个展示导航
						if (brand_decorate_nav_len > 1) {
							var nav_list = '';

							for (var i = 0; i < brand_decorate_nav_len; i++) {
								nav_list += '<a data-index="' + brand_decorate_api.nav[i].index + '" onclick="decorateJump(' + brand_decorate_api.nav[i].index + ')" class="flex ac jc fg' + (i ? '' : ' active') + '">' + brand_decorate_api.nav[i].name + '</a>';
							}

							brand_decorate_nav.append(nav_list);
							brand_decorate_nav.attr('data-len', brand_decorate_nav_len);

							nav_height = 1 + brand_decorate_nav.height();

							// 展示分区导航大于4个, 左右滑动
							brand_decorate_nav_len > 4 && brand_decorate_nav.addClass('scroll-x');
						} else {
							brand_decorate_nav.remove();
						}

						// 滚动事件
						var old_page = 0,
							module_api = '',
							brand_decorate_header_old = undefined;

						// if (brand_decorate_nav_len > 1 && brand_decorate_time[0]) {
						// 	page_width = brand_decorate_scroll.width();
						// 	page_left = brand_decorate_scroll.offset().left;
						// }

						$('.page:visible>.scroll-more').scroll(function (e) {
							// 嵌套页面自带分页, 在嵌套页面的.scroll-more处添加类'noscroll-decorate'来禁止内页分页
							// 嵌套页面需要自定义页面有分页, 在嵌套页面的.scroll-more处添加类'scroll-decorate'
							if ($(this).hasClass('noscroll-decorate')) {
								return;
							}
							if ($(".decorate-header").hasClass("fixed")) {
							} else {
								// $(".scroll-decorate>.box-list").css("paddingTop","0")
								// $(".scroll-decorate>.box-list").css("paddingTop",".8rem")
							}
							$(this).scrollTop() == 0 && $(".scroll-decorate>.box-list").css("paddingTop", "0")

							var _ts = $(this).hasClass('scroll-decorate') ? this : $(this).find('.scroll-decorate')[0],
								_st = brand_decorate_scroll.scrollTop();

							brand_decorate_scroll = $(_ts);
							brand_decorate_con = brand_decorate_scroll.hasClass('content-decorate') ? brand_decorate_scroll : brand_decorate_scroll.find('.content-decorate');

							brand_decorate_nav = $(_ts).find('.nav');
							// brand_decorate_time = $(_ts).find('.decorate-time');
							brand_decorate_nav_len = brand_decorate_nav.attr('data-len');

							// if (brand_decorate_nav_len > 1 && brand_decorate_time[0]) {
							// 	if (brand_decorate_time.offset().top + brand_decorate_time.height() - header_height > 0) {
							// 		brand_decorate_nav.addClass('hide');
							// 	} else {
							// 		brand_decorate_nav.css({
							// 			'width': page_width,
							// 			'top': header_height,
							// 			'left': page_left
							// 		}).removeClass('hide');
							// 	}
							// }

							if (brand_decorate_header.length) {
								if (brand_decorate_header.hasClass('hide')) {
									// if (_st > font_size) {
									// 	brand_decorate_header.removeClass('hide');
									// 	brand_decorate_header.closest('.page').addClass('decorate-header-page fn');
									// }
								} else if (brand_decorate_header_scroll) {
									var _i = undefined,
										_s = $('.page:visible .scroll-decorate .nav-hide:not([data-mid=' + brand_decorate_header.data('mid') + '])');

									for (var i = 0; i < _s.length; i++) {
										if ($(_s[i]).offset().top <= (header_height + decorateHeaderHeight)) {
											_i = i;
										}
									}

									if (_i !== undefined && brand_decorate_header_old !== _i) {
										brand_decorate_header_old = _i;
										decorateHeaderActive($(_s[_i]).data('mid'));
									}
								}

								$('.decorate-header-more').removeClass('on').parent().removeClass('on');
								$('.decorate-header-btn').removeClass('on').css("background", "none");
								$('.decorate-header-con').removeClass('on');

							}

							if (_ts.getAttribute('m-type')) {
								module_type = _ts.getAttribute('m-type');
								module_id = _ts.getAttribute('m-id');
								brand_decorate_api.len = _ts.getAttribute('m-len');
								brand_decorate_api.cur = _ts.getAttribute('m-cur') || 1;
							}

							if (module_type == 4) {
								module_api = getApi.getProductList;
							} else if (module_type == 5) {
								module_api = getApi.getActivityList;
							}

							if (module_api || !brand_decorate_api.nest) {
								_st >= win_h ? scrollHand.show(brand_decorate_scroll) : scrollHand.hide(brand_decorate_scroll);
							}

							if (module_type && brand_decorate_api.pag == brand_decorate_api.len) {
								if (!brand_decorate_api.post) {
									return;
								}
								brand_decorate_api.post = 0;

								if (old_page < brand_decorate_api.cur && _ts.scrollTop + win_h / 4 > _ts.scrollHeight - _ts.offsetHeight) {
									old_page = brand_decorate_api.cur;

									new ajax(module_api, {
										data: {
											decorateModuleId: module_id,
											currentPage: brand_decorate_api.cur,
											pageSize: brand_decorate_api.pag
										},
										success: function (res) {
											var _tpl = '',
												_data = {},
												_list = res.returnData,
												_len = _list.length;

											_data.page = brand_decorate_api.cur * brand_decorate_api.pag;

											if (module_type == 4) {
												_tpl = 'activity_decorate_product_tpl';
												/*conmonProduct => productBlockList */
												_data.productBlockList = _list;
												_data.productBlockList = imgCachePic(_data.productBlockList, 'activity_decorate_product_tpl');
												_data.showModel = 2
												
												brand_decorate_api.areaid && traffic.delayRequest(function () {
													for (var i = 0; i < _data.productBlockList.length; i++) {
														traffic.setDtl(_td, {
															type: 3,
															id: _data.productBlockList[i].productId,
															pos: _data.page + i
														});
													}
												});
											} else if (module_type == 5) {
												_tpl = 'activity_decorate_brand_tpl';
												_data.conmonBrand = _list;
												_data.conmonBrand = imgCacheConmonBrandAreaEntryPic(_data.conmonBrand, 'activity_decorate_brand_tpl');

												brand_decorate_api.areaid && traffic.delayRequest(function () {
													for (var i = 0; i < _data.activityList.length; i++) {
														var _la = _data.activityList[i],
															_pg = _data.page + i;

														_la.source == 1 ? traffic.setDtl(_td, {
															type: 1,
															id: _la.activityAreaId,
															pos: _pg
														}) : traffic.setDtl(_td, {
															type: 2,
															id: _la.activityId,
															pos: _pg
														});
													}
												});
											}

											if (_len) {
												$(_ts).find('.box-list').append(template(_tpl, _data));
												brand_decorate_api.cur++;
												_ts.setAttribute('m-cur', brand_decorate_api.cur);
											} else {
												delayTip('已经全部加载完毕');
											}
										},
										complete: function () {
											brand_decorate_api.post = 1;
										}
									});
								} else {
									brand_decorate_api.post = 1;
								}
							}
						});

						setTimeout(scrollFix, 300);
						brand_decorate_header.length && (decorateHeaderHeight = brand_decorate_header.height());
					}

					if (brand_decorate_is_empty) {
						brand_decorate_con.html(template('activity_decorate_empty_tpl'));
						return;
					}
				}
			});
			if(brand_decorate_api.areaid){
				if (member.id && member.token && member.sys == 'android' || member.id && member.token && member.sys == 'ios') {
					new ajax(getApi.appLogin, {
						data: {
							memberId: member.id,
							token: member.token
						},
						success: function (e) {
							new ajax(getApi.allowancePopInfo, {
								data: {
									activityAreaId: brand_decorate_api.areaid
								},
								success: function (res) {
									console.log(res)
									if (!res.returnData.allowance) {
										return
									}
									res.returnData.activityAreaId = brand_decorate_api.areaid
									brand_decorate_con.append(template('activity_decorate_618_tpl', res.returnData));
									var popTypeTimeOut = getCookie('popTypeTimeOut' + brand_decorate_api.areaid)
									console.log(popTypeTimeOut)
									if (!popTypeTimeOut) {
										ancePopupShow()
			
										switch (res.returnData.popType) {
											case "1":
												// 每天一次
												setCookie('popTypeTimeOut' + brand_decorate_api.areaid, 1, 1)
												break;
											case "2":
												// 终生一次
												setCookie('popTypeTimeOut' + brand_decorate_api.areaid, 1, 365)
												break;
											case "3":
												// 自定义
												setCookie('popTypeTimeOut' + brand_decorate_api.areaid, 1, res.returnData.day)
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
							activityAreaId: brand_decorate_api.areaid
						},
						success: function (res) {
							console.log(res)
							if (!res.returnData.allowance) {
								return
							}
							res.returnData.activityAreaId = brand_decorate_api.areaid
							brand_decorate_con.append(template('activity_decorate_618_tpl', res.returnData));
							var popTypeTimeOut = getCookie('popTypeTimeOut' + brand_decorate_api.areaid)
							console.log(popTypeTimeOut)
							if (!popTypeTimeOut) {
								ancePopupShow()
	
								switch (res.returnData.popType) {
									case "1":
										// 每天一次
										setCookie('popTypeTimeOut' + brand_decorate_api.areaid, 1, 1)
										break;
									case "2":
										// 终生一次
										setCookie('popTypeTimeOut' + brand_decorate_api.areaid, 1, 365)
										break;
									case "3":
										// 自定义
										setCookie('popTypeTimeOut' + brand_decorate_api.areaid, 1, res.returnData.day)
										break;
								}
							}
						}
					})
				}
			}
		});
	},
};

getTpl(function () {
	brandDecorate.init();
});

function onShow() {
	brand_decorate_api.areaid && traffic.setPv(brandDecorate.data);
}

/********************************** 618 **********************************/

function ancePopupShow(id) {
	id && (brand_decorate_api.areaid = id)
	new ajax(getApi.allowanceInfo, {
		data: {
			activityAreaId: brand_decorate_api.areaid,
			memberId: member.id
		},
		success: function (res) {
		res && (res.returnData.activityAreaId = brand_decorate_api.areaid)
			brand_decorate_con.append(template('activity_decorate_618_popup_tpl', res.returnData));
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
	$(".popup-ance-con .card").eq(0).css("transition","none")
	$(".popup-ance-con .card").eq(0).removeClass('out')
	
	new ajax(getApi.allowanceExchange,{
		data:{
			allowanceInfoId:allowanceInfoId,
			id:id
		},
		success: function(res){
			$(".lastExchangeAllowance").text(res.returnData.lastExchangeAllowance + '元')
			$(".allowanceBalance").text(res.returnData.allowanceBalance + '元')
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
		!days && (days = 1)
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

//封装 方便更改状态调用
function timelinkType33(el, _currentTime) {
	var _span = $(el).find('span'),

		_now = new Date(_currentTime),
		_end = new Date(parseInt($(el).attr("data-countdownenddate"))),
		_begin = new Date(parseInt($(el).attr("data-countdownbegindate"))),
		_ends = _end;

	if (_now.getTime() < _begin.getTime()) {
		_ends = _begin;
	} else if (_now.getTime() < _end.getTime()) {
	} else {
		_ends = false;
	}

	if (_ends) {
		require(['qpTime'], function () {
			$.qpTime({
				s: _now,
				e: _ends
			}, function (pt) {
				_span.eq(0).html(pt.d);
				_span.eq(1).html(pt.h);
				_span.eq(2).html(pt.m);
				_span.eq(3).html(pt.s);
				if (pt.t <= 0) {
					if (_now.getTime() < _begin.getTime()) {
						timelinkType33(el, _begin)
					} else if (_now.getTime() < _end.getTime()) {
						timelinkType33(el, _end)
					}
				}
			});
		});
	}
}

var decorate_post_coupon = 1;
removeStorage('first_decorate_coupon');

function decorateCoupons(e) {
	decorateCoupon(function () {
		delayDecorateCoupons(e);
	});
}

function decorateCoupon(fn) {
	if (!decorate_post_coupon) return;
	decorate_post_coupon = 0;

	if (member.sys == 'ios' && !getStorage('first_decorate_coupon') && !member.id) {
		setTimeout(function () {
			setStorage('first_decorate_coupon', 1);
			fn();
		}, 300);
	} else {
		fn();
	}
}

function delayDecorateCoupons(_e) {
	new ajax(member.app ? getApi.addReceiveCouponH5 : getApi.addReceiveCoupon, {
		data: {
			couponId: _e.getAttribute('data-productid'),
			memberId: member.id
		},
		success: function (res) {
			alertTip(res.returnData.content);
		},
		error: function (res) {
			alertTip(res.returnMsg);
		},
		complete: function () {
			decorate_post_coupon = 1;
		},
		appLogin: function () {
			decorate_post_coupon = 1;
		}
	});
}

/********************************** end **********************************/

// 导航
function decorateJump(e) {
	brand_decorate_scroll = $('.page:visible .scroll-decorate');
	brand_decorate_scroll.scrollTop(brand_decorate_scroll.find('.nav-hide[data-index=' + e + ']').offset().top + brand_decorate_scroll.scrollTop() - header_height - nav_height);
	brand_decorate_scroll.find('.nav a[data-index=' + e + ']').addClass('active').siblings().removeClass('active');

}

function imgCacheConmonBrandAreaEntryPic(_list, _cac) {
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
			var _pic = _list[i].areaEntryPic;

			if (_pic) {
				for (var j = 0, _name = _pic.match(reg_cache_img)[0]; j < cache.list.name.length; j++) {
					(_name == cache.list.name[j]) && (_list[i].areaEntryPic = cache.list.path[j]);
				}
			}
		}
	}

	for (var i = 0; i < _list.length; i++) {
		var _pic = _list[i].areaEntryPic;

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

/********************************** end **********************************/

// 会场秒杀
function seckillListNav6(_this, _scroll) {
	_this && (decorateType6.tabSelect = $(_this));
	if (decorateType6.tabSelect.hasClass('active') && !_scroll) {
		return;
	}

	decorateType6.tabSelect.addClass('active').siblings().removeClass('active');
	decorateType6.tabSelect.css({ 'background': decorateType6.tabSelect.attr('data-ac'), 'color': decorateType6.tabSelect.attr('data-af') }).siblings().removeAttr("style")
	var b = decorateType6.tabSelect.find('b')
	b.css("background", b.attr("data-ac"))
	decorateType6.tabSelect.siblings().find('b').css("background", b.attr("data-dc"))

	var _con = decorateType6.tabSelect.closest('.con-box').find('.product-scroll'),
		_scr = _con.parent();

	if (!_scroll) {
		_scr.removeAttr('data-page').removeAttr('data-old');
		_scr.scrollLeft(0);
	}

	require(['template'], function (template) {
		new ajax(getApi.getTopSecKillProductList, {
			data: {
				beginTime: decorateType6.tabSelect.attr('data-beg'),
				currentPage: _scr.attr('data-page') || 0
			},
			success: function (res) {
				var _list = res.returnData,
					_len = _list.productInfoList.length;

				_list.productInfoList = imgCacheProductPic(_list.productInfoList, 'decorate_type6');

				if (_scroll) {
					if (_len) {
						var _cur = _scr.attr('data-page');
						_cur++;
						_scr.attr('data-page', _cur);

						_con.append(template('decorate_product_scroll', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_con.html(template('decorate_product_scroll', _list));
					seckillListTime(decorateType6.tabSelect, decorateType6);

					if (_len == 10) {
						scrollHand.init(_scr, function () {
							seckillListNav6('', true);
						});
					}
				}
			}
		});
	});
}

// 优惠券秒杀
function seckillListNav15(_this, _auto) {
	_this && (decorateType15.tabSelect = $(_this));
	var b = decorateType15.tabSelect.find('b')

	decorateType15.tabSelect.parent().find('.active').removeAttr("style")
	decorateType15.tabSelect.parent().find('.active b').css("background", b.attr("data-dc"))
	decorateType15.tabSelect.addClass('active').siblings().removeClass('active');
	decorateType15.tabSelect.css({ 'background': decorateType15.tabSelect.attr('data-ac'), 'color': decorateType15.tabSelect.attr('data-af') })
	b.css("background", b.attr("data-ac"))
	var _con = decorateType15.tabSelect.closest('.con-box').find('.coupon-list')
	var _nav = decorateType15.tabSelect.closest('.con-box').find('.tab-nav')

	require(['template'], function (template) {
		new ajax(getApi.getCouponListByCouponModuleTime, {
			data: {
				recBeginDate: decorateType15.tabSelect.attr('data-beg'),
				memberId: member.id,
				decorateModuleId: decorateType15.tabSelect.attr('data-decorateModuleId')
			},
			success: function (res) {
				res.returnData.totalBgColor = decorateType15.tabSelect.attr('data-tbc')
				res.returnData.couponBgColor = decorateType15.tabSelect.attr('data-cbc')
				if (_auto) {
					res.returnData.decorateModuleId = decorateType15.tabSelect.attr('data-decorateModuleId')
					res.returnData.selectedBgColor = decorateType15.tabSelect.attr('data-ac')
					res.returnData.selectedFontColor = decorateType15.tabSelect.attr('data-af')
					res.returnData.btnDefaultBgColor = decorateType15.tabSelect.find('b').attr('data-dc')
					res.returnData.btnSelectedBgColor = decorateType15.tabSelect.find('b').attr('data-ac')
					_nav.html(template('decorate_coupon_nav', res.returnData))
					var nextIndex = null
					if (res.returnData.timeCouponList.length) {
						brand_decorate_is_empty = false

						for (var i = 0; i < res.returnData.timeCouponList.length; i++) {
							if (res.returnData.timeCouponList[i].timeDesc == '即将开始') { nextIndex = i; break }
						}
					}
					$(".con-seckill-15 .tab-nav a").eq(0).click()

					if (nextIndex != null) {
						var begindDate = new Date(res.returnData.timeCouponList[i].recBeginDate.replace(/-/g, '/'))
						window.timeType15 && clearTimeout(window.timeType15)
						window.timeType15 = setTimeout(function () {
							seckillListNav15(brand_decorate_con.find('.con-seckill-15 .tab-nav a').eq(1), true)

						}, begindDate.getTime() - res.returnData.timeCouponList[i].currentTime)

					}
				}
				_con.html(template('decorate_coupon_list', res.returnData))
			}
		})
	})
}

//优惠券领券
function module15ActiveCoupon(_this, msgType, couponId) {
	if (msgType == '0') {
		new ajax(member.app ? getApi.addReceiveCouponH5 : getApi.addReceiveCoupon, {
			data: {
				couponId: couponId,
				memberId: member.id
			},
			success: function (res) {
				alertTip(res.returnData.content);
				if (res.returnData.isCanReceive) {
					$(_this).text("已领取").css("background", "#DDDDDD")
				}
			},
			error: function (res) {
				alertTip(res.returnMsg);
			},
			complete: function () {
			},
			appLogin: function () {
			}
		});
	}
}

// 限时秒杀
function seckillListNav11(_this, _scroll) {
	_this && (decorateType11.tabSelect = $(_this));
	if (decorateType11.tabSelect.hasClass('active') && !_scroll) {
		return;
	}

	decorateType11.tabSelect.addClass('active').siblings().removeClass('active');

	var _con = decorateType11.tabSelect.closest('.con-box').find('.product-scroll'),
		_scr = _con.parent();

	if (!_scroll) {
		_scr.removeAttr('data-page').removeAttr('data-old');
		_scr.scrollLeft(0);
	}

	require(['template'], function (template) {
		new ajax(getApi.getTopSecKillProductList, {
			data: {
				seckillType: 1,
				beginTime: decorateType11.tabSelect.attr('data-beg'),
				currentPage: _scr.attr('data-page') || 0
			},
			success: function (res) {
				var _list = res.returnData,
					_len = _list.productInfoList.length;

				_list.productInfoList = imgCacheProductPic(_list.productInfoList, 'decorate_type6');

				if (_scroll) {
					if (_len) {
						var _cur = _scr.attr('data-page');
						_cur++;
						_scr.attr('data-page', _cur);

						_con.append(template('decorate_product_scroll', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_con.html(template('decorate_product_scroll', _list));
					seckillListTime(decorateType11.tabSelect, decorateType11);

					if (_len == 10) {
						scrollHand.init(_scr, function () {
							seckillListNav11('', true);
						});
					}
				};
			}
		});
	});
}

function seckillListTime($ts, _obj) {
	var _beg = $ts.data('beg'),
		_cur = $ts.data('cur'),
		_end = $ts.data('end'),
		_ends = _end,
		_time = $ts.closest('.con-seckill-' + _obj.type).find('.tab-time'),
		_str = '';

	_cur += (new Date).getTime() - _obj.sysCur;

	if (_cur < _beg) {
		_ends = _beg;
		_str = '距离本场开始&nbsp;';
	} else if (_cur < _end) {
		_str = '距离本场结束&nbsp;';
	} else {
		_ends = false;
		_str = '活动已结束';
	}

	if (_ends) {
		if (_ends - _cur > 998) {
			clearInterval(seckillTimer);

			seckillTimer = $.qpTime({
				s: _cur,
				e: _ends
			}, function (e) {
				if (e.t) {
					_time.html(_str + '<b class="flex ac jc">' + e.h + '</b>:<b class="flex ac jc">' + e.m + '</b>:<b class="flex ac jc">' + e.s + '</b>');
				} else {
					location.reload();
				}
			});
		}
	} else {
		_time.html(_str);
	}
}

// 跳转限时抢购
function getRushbuy() {
	if (member.sys == 'android') {
		intentSeckill();
	} else if (member.sys == 'ios') {
		callIntentTrailer('intentTrailerListNative', 'intentSeckill');
	} else {
		getUrl('rushbuy/index.html?type=3', 'self');
	}
}

// 必购
function bgModuleListNav(_this) {
	var $ts = $(_this);

	if ($ts.hasClass('active')) {
		return;
	}

	require(['template'], function (template) {
		$ts.addClass('active').siblings().removeClass('active');

		new ajax(getApi.getBgProductInfo, {
			data: {
				bgModuleId: $ts.attr('data-id')
			},
			success: function (res) {
				var _list = res.returnData;

				_list.productInfoList = imgCacheProductPic(_list.productInfoList, 'decorate_type7');
				$ts.closest('.con-box').find('.product-scroll').html(template('decorate_product_scroll', _list));
			}
		});
	});
}

// 分区锚点
function decorateJumpId(e) {
	var _s = $('.page:visible .scroll-decorate'),
		_e = _s.find('.nav-hide[data-mid=' + e + ']');

	_e.length && _s.scrollTop(_e.offset().top + _s.scrollTop() - header_height);
}

function decorateJumpHeader(_ts) {
	var _ts = $(_ts),
		_mid = _ts.data('mid'),
		_s = $('.page.decorate:visible .scroll-decorate'),
		_e = _s.find('.nav-hide[data-mid=' + _mid + ']');
	stopSelf();


	if (_e.length) {
		brand_decorate_header_scroll = false;
		_s.scrollTop(_e.offset().top + _s.scrollTop() - header_height - decorateHeaderHeight + 2);
		var _t = _e.offset().top + _s.scrollTop() - header_height - decorateHeaderHeight + 2
		$('.decorate-header-more').removeClass('on').parent().removeClass('on');
		$('.decorate-header-btn').removeClass('on').css("background", "none");
		$('.decorate-header-con').removeClass('on');
		console.log(_t)
		if (!$(".decorate-header").hasClass("fixed")) {
			_t = _t + decorateHeaderHeight
		}

		setTimeout(function () {
			brand_decorate_header_scroll = true;
			if ($(".decorate-header").hasClass("fixed")) {
				$(".scroll-decorate>.box-list").css("paddingTop", ".8rem")
			} else {
				$(".scroll-decorate>.box-list").css("paddingTop", "0")
				if (!_e.prev().hasClass("decorate-header")) {
					_s.scrollTop(_t)
				}
			}
			decorateHeaderActive(_mid, _ts);

		}, 30);
	}
}
function decorateJumpHeaders(_ts) {
	var _ts = $(_ts),
		_mid = _ts.data('mid'),
	    type_id = _ts.data('id');
	$(_ts).addClass('active').siblings().removeClass('active');

	//查找 自定义属性中的元素
	new ajax(getApi.getModuleItemListByProductModuleTypeId, {
		data: {
			decorateModuleId: type_id,
			productModuleTypeId:_mid
		},
		success:function (res) {
			console.log(tabData.data)
			var _d = {}
			_d.productBlockList = res.returnData;
			_d.showModel = _ts.data('model')
			$(".cl"+ type_id +"").html(template('activity_decorate_product_tpl', _d));
		},
		error:function (res) {
			console.log(res)
		}
	})
}
function decorateHeaderActive(_mid, _ts) {
	var _c = $('.decorate-header-con'),
		_a = _c.find('a[data-mid=' + _mid + ']');

	if (_a.length) {
		var _f = _a.offset().left,
			_o = _f + _c.scrollLeft(),
			_l = _c.width() * .7,
			_bl = _ts ? _ts.parent().parent().hasClass('decorate-header-con') : 0;

		if (_o > _l && (_bl ? _f > _l : 1)) {
			_c.scrollLeft(_o - _l);
		} else if (_o <= 0 || _ts && _f < 0) {
			_c.scrollLeft(_o);
		}

		$('.decorate-header a').removeClass('active');
		$('.decorate-header a[data-mid=' + _mid + ']').addClass('active');
	}
}

function showDecorateHeaderMore() {
	if ($('.decorate-header-more').hasClass('on')) {
		$('.decorate-header-more').removeClass('on').parent().removeClass('on');
		$('.decorate-header-btn').removeClass('on').css("background", 'none');
		$('.decorate-header-con').removeClass('on');
	} else {
		$('.decorate-header-more').addClass('on').parent().addClass('on');
		$('.decorate-header-btn').addClass('on').css("background", $(".decorate-header-btn").attr("data-bg"));
		$('.decorate-header-con').addClass('on');
	}
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






















