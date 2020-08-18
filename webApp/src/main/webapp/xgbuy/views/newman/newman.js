var newman = $('.page[data-id="newman"]:visible'),
	newman_scroll = newman.find('.scroll-more'),
	newman_main = newman_scroll.find('.touchfix'),
	newman_coupon = newman.find('.popup-newman'),
	newman_title,
	newman_seckill,
	newman_con;

var newmanApi = {
	data: {
		size: 10,
		type: 2
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'type': _td.type = _b; break;
				}
			}
		}

		traffic.setPv(_td, {
			pageType: '14'
		});

		_ts.main();
		shareHand.update();
	},

	main: function (_scroll) {
		var _ts = this,
			_td = _ts.data,
			_page = newman_scroll.attr('data-page');

		new ajax(getApi.newEnjoy, {
			data: {
				type: _td.type,
				pageSize: _td.size,
				currentPage: _page || 0
			},
			success: function (res) {
				var _size = res.returnSize,
					_list = res.returnData,
					_len = _list.data.length;
				_list.adList = imgCachePic(_list.adList, 'newman_tpl_ad');
				_list.data = imgCacheProductPic(_list.data, 'newman_tpl_data');
				_list.page = _page || 0;
				if (_scroll) {
					if (_len) {
						newman_scroll.attr('data-page', ++_page);
						newman_con.append(template('newman_tpl_con', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_size && (_td.size = _size);
					_list.page = 0;
					var listdata = _list.data;
					for(var i=0;i<listdata.length;i++){
						listdata[i]['index'] = i
					}

					newman_main.html(template('newman_tpl', _list));
					newman_seckill = newman_main.find('.row-seckill');
					newman_con = newman_main.find('.activity-single-con');
					newman_title = newman.find('.row-title');

					traffic.delayRequest(function () {
						for (var i = 0; i < _list.adList.length; i++) {
							traffic.setDtl(_td, {
								type: 4,
								id: _list.adList[i].id,
								pos: i
							});
						}
					});

					new ajax(getApi.newEnjoyActivity, {
						data: {
							version: 3,
							system: 2
						},
						success: function (res1) {
							var _list1 = res1.returnData;

							_list1.secKill.backgroundPic = imgCache(_list1.secKill.backgroundPic, 'newman_tpl_seckill_bg');
							_list1.secKill.secKillProducts = imgCacheProductPic(_list1.secKill.secKillProducts, 'newman_tpl_seckill_ps');

							newman_seckill.html(template(_list1.secKill.secKillProducts.length || _len ? 'newman_tpl_seckill' : 'newman_tpl_empty', _list1));
							newman_title.html(template('newman_tpl_title', _list1));
						},
						complete: scrollFix
					});

					_len == _td.size && scrollHand.init(newman_scroll, function () {
						_ts.main(true);
					}, true);

				}
				if(_len != 0){
					_ts.timeRun(_page - 1 )
				}

			}
		});
	},

	getCoupon: function (_id) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.couponEnjoy, {
			data: {
				memberId: member.id,
				type: _td.type
			},
			success: function (res) {
				if (res.returnCode == '1003') {
					delayTip('请先绑定手机号');
					getUrl('register/mobile/index.html');
					return;
				}

				if (res.returnData.length) {
					newman_coupon.html(template('newman_tpl_coupon', res));
					popupShow('.popup-newman');
				} else {
					delayTip('您已经领取过优惠券，不要太贪心哦');
				}
			},
			error: function () {
				delayTip('请先绑定手机号');
				getUrl('register/mobile/index.html');
			},
			limit: 1
		});

		traffic.setDtl(_td, {
			type: 4,
			id: _id
		});
	},
	timer: function (e) {
		var _ts = this;
		var D = parseInt(e/(60*60*24*1000));
		var h = parseInt(e/(60*60*1000)%24);
		var m = parseInt(e/(60*1000)%60);
		var s = parseInt(e/1000%60);
		strDate = '剩余时间：'+_ts.timeInt(D)+'天'+_ts.timeInt(h)+'时'+_ts.timeInt(m)+'分'+_ts.timeInt(s)+'秒';
		return strDate;
	},
	timeInt:function(e){
		return e>=10?e:'0'+e;
	},
	timeRun: function (e) {

		var _ts = this,
			_td = _ts.data;
		e = e || 0
		$.each(newman_con.find('time[data-page=' + e +']'), function () {
			var $ts = $(this);
			console.log(e);
				require(['qpTime'], function () {
					$.qpTime({
						s: '',
						e: $ts.data('timer')
					}, function (pt) {
						$ts.html(_ts.timer(pt.t));
					});
				});
		});
	},
};

getTpl(function () {
	newmanApi.init();
});

function onShow() {
	traffic.setPv(newmanApi.data);
}