var cut_share = $('.page[data-id="activity_cutprice_share"]:visible'),
	cut_share_scroll = cut_share.find('.scroll-more'),
	cut_share_con = cut_share_scroll.find('.touchfix'),
	cut_share_row1 = cut_share.find('.row-1'),
	cut_share_row2 = cut_share.find('.row-2'),
	cut_share_popup = cut_share.find('.popup-cut-share'),
	cut_share_record = '',
	cut_share_mask = '';

var cut_share_share = {
	title: '',
	content: '我正在参加醒购官方砍价活动，帮我砍砍，砍到0元就能免费领取',
	pictureUrl: '',
	url: ''
};

var cut_share_api = {
	pag: 10
};

var cutShare = {
	data: {
		host: 'm.xinggoubuy.com',
		time: 3600000*24, // 1*24小时
		mobileVerfiyFlag: 1,
		id: '',
		pag: 10,
		share: '',
		memberId: '',
		cutOrderId: '',
		productId: '',
		productItemId: '',
		addressId: '',
		receiverName: '',
		receiverPhone: '',
		receiverAddress: ''
	},

	init: function () {
		var _ts = this;

		$(function () {
			if (search_url) {
				for (var i = 0; i < search_url.length; i++) {
					var _a = search_url[i].split('='),
						_b = _a[1];

					switch (_a[0]) {
						case 'share': _ts.data.share = _b; break;
						case 'sourceMemberId': _ts.data.id = _b; break;
						case 'cutOrderId': _ts.data.cutOrderId = _b; break;
					}
				}
			}

			requestAppMember(function () {
				_ts.post();
			}, function () {
				getApi.addMemberCutOrderInfo = getApi.addMemberCutOrderInfoH5;
				getApi.getMyCutShareInfo = getApi.getMyCutShareInfoH5;
				getApi.getBargainGoodsList = getApi.getBargainGoodsListH5;
			});
		});
	},

	help: function () {
		var _ts = this,
			_td = _ts.data;

		if (!_td.mobileVerfiyFlag) {
			getUrl('register/mobilePwd/index.html', 'self');
			return;
		}

		new ajax(getApi.addMemberCutOrderInfo, {
			data: {
				memberId: member.id,
				cutOrderId: _td.cutOrderId,
				productId: _td.productId,
				productItemId: '',
				addressId: '',
				receiverName: '',
				receiverPhone: '',
				receiverAddress: ''
			},
			success: function (e) {
				_ts.helpTpl(e);
			},
			error: function (e) {
				_ts.helpTpl({
					returnMsg: e.returnMsg
				});
			},
			limit: 1
		});
	},

	helpTpl: function (e) {
		var _ts = this;

		cut_share_popup.addClass('show').html(template('cut_share_popup_tpl', e));

		if (e.returnData) {
			cut_share_record.removeAttr('data-old').removeAttr('data-page');
			_ts.post(false, true);
		}
	},

	post: function (_scroll, _flesh) {
		var _ts = this,
			_td = _ts.data;

		require(['qpTime'], function () {
			new ajax(getApi.getMyCutShareInfo, {
				data: {
					memberId: member.id,
					cutOrderId: _td.cutOrderId,
					currentPage: cut_share_record ? (cut_share_record.attr('data-page') || 0) : 0
				},
				success: function (e) {
					var _list = e.returnData,
						_pag = e.returnSize,
						_len = _list.dataList.length,
						_black = _list.isBlack;

					if (_black) {
						shield(_list.blackReason);
						return;
					}

					if (_scroll) {
						if (_len) {
							var _cur = cut_share_record.attr('data-page');
							_cur++;
							cut_share_record.attr('data-page', _cur);
							cut_share_record.append(template('cut_share_tpl_record_list', _list));
						} else {
							delayTip('已经全部加载完毕');
						}
					} else {
						_list.timer = _td.time - (_list.currentDate - _list.createDate);
						_list.timer > 0 && (_list.time = _ts.timer(_list.timer));
						!/\./g.test(_list.tagPrice) && (_list.tagPrice += '.00');
						_td.mobileVerfiyFlag = _list.mobileVerfiyFlag;

						cut_share_row1.html(template('cut_share_tpl', _list));
						cut_share_row2.html(template('cut_share_tpl_record', _list));

						_ts.timeRun();

						if (!_flesh) {
							cut_share_share.title = '我在砍价免费拿' + _list.productName;
							cut_share_share.pictureUrl = _list.skuPic;
							cut_share_share.url = real_url.replace('m.xgbuy.cc', _td.host).split(redirect_url)[0] + redirect_url + 'activity/cutprice/share.html?cutOrderId=' + _td.cutOrderId + '&sourceMemberId=' + _td.id;
							shareHand.update(cut_share_share, {
								close: function () {
									cut_share_mask && cut_share_mask.removeClass('show');
								}
							});

							_ts.data.productId = _list.productId;
							cut_share_record = cut_share_row2.find('.con-record');

							_pag && (cut_share_api.pag = _pag);

							if (_list.showType == 1) {
								_ts.cutList();
							} else {
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

										cut_share_con.append(data);
									}
								});
							}
						}

						_len == cut_share_api.pag && scrollHand.init(cut_share_record, function () {
							_ts.post(true);
						});

						scrollFix(cut_share_scroll);
					}
				}
			});
		});
	},

	cutList: function (_scroll) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getBargainGoodsList, {
			data: {
				type: 7,
				memberId: member.id,
				sourceMemberId: _td.id,
				currentPage: cut_share_scroll.attr('data-page') || 0
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
				_list.dataList = imgCacheProductPic(_list.dataList, 'cut_price_cache');

				if (_scroll) {
					if (_len) {
						var _cur = cut_share_scroll.attr('data-page');
						_cur++;
						cut_share_scroll.attr('data-page', _cur);
						cut_share_con.find('.con-list').append(template('cut_price_tpl_share_list', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					cut_share_con.append(template('cut_price_tpl_share', _list));

					_len == _td.pag && scrollHand.init(cut_share_scroll, function () {
						_ts.cutList(true);
					}, true);
				}
			}
		});
	},

	timer: function (e) {
		return famateNumber(e / 1e3 / 60 / 60) + ':' + famateNumber(e / 1e3 / 60 % 60) + ':' + famateNumber(e / 1e3 % 60);
	},

	timeRun: function (e) {
		var _ts = this,
			_tm = cut_share_row1.find('time');

		_tm && $.qpTime({
			s: 1,
			e: _tm.data('timer')
		}, function (pt) {
			_tm.html(_ts.timer(pt.t));
			!pt.t && _tm.parent().empty().addClass('r-empty');
		});
	},

	share: function () {
		cut_share_share.title && shareHand.show({
			wx: 1
		});
	},

	moreRecord: function (_this) {
		var $ts = $(_this),
			_record = $ts.siblings('.con-record');

		$ts.addClass('hide');
		_record.removeClass('show2');
		scrollFix(_record);
	}
};

getTpl(function () {
	cutShare.init();
});