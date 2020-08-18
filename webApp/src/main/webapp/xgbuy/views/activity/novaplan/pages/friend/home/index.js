var friend_home = $('.page[data-id="activity_novaplan_pages_friend_home"]:visible'),
	friend_home_scroll = friend_home.find('.scroll-more'),
	my_popup_dynamic = friend_home.find('.popup-dynamic'),

	friend_home_scroll_user = friend_home.find('.user'),
	friend_home_scroll_list = friend_home.find('.tab-body');

var friendHomeApi = {
	data: {

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
						case 'frientMenberId': _td.frientMenberId = _b; break;
						case 'my': _td.my = _b; break;
					}
				}
			}

			new ajax(getApi.getMemberDynamicDtl, {
				data: {
					friendMemberId: _td.frientMenberId
				},
				success: function (res) {
					var _list = res.returnData;

					if (_list.attentionButton == 0) {
						friend_home_scroll_list.addClass('no-mb');
						setTitle('我的主页');
					} else {
						setTitle(_list.memberNick);
					}
					friend_home_scroll_user.html(template('friend_home_user', _list));
				}
			});

			_ts.main();
		});
	},

	main: function (_scroll) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getMemberDynamicList, {
			data: {
				friendMemberId: _td.frientMenberId,
				currentPage: friend_home_scroll.attr('data-page') || 0,
				pageSize: 10
			},
			success: function (res) {
				var _list = res.returnData;

				_list.dataList = imgCachePic(_list.dataList, 'activity_novaplan_pages_friend_home_head');
				_list.dataList = imgCacheMyNovaPlanPic(_list.dataList, 'activity_novaplan_pages_friend_home_product');

				if (_scroll) {
					if (_list.dataList.length) {
						var _cur = friend_home_scroll.attr('data-page');
						friend_home_scroll_list.append(template('friend_tpl_novaplan_con', _list));
						_ts.ellipsisAdju(_cur)
						_cur++;
						friend_home_scroll.attr('data-page', _cur);

					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					friend_home_scroll_list.html(template('friend_tpl_novaplan_con', _list));
					_ts.ellipsisAdju(_cur)

					_list.dataList.length == 10 && scrollHand.init(friend_home_scroll, function () {
						_ts.main(true);
					}, true);
				}
			}
		});
	},

	getOffriends: function (_index) {
		var _ts = this,
			_td = _ts.data;

		setStorage('offriend_typ', '_' + _index);

		getUrl('activity/novaplan/pages/friend/home/offriends/index.html?frientMenberId=' + _td.frientMenberId, 'self');
	},

	getFollow: function (_this) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.updateMemberAttention, {
			data: {
				friendMemberId: _td.frientMenberId
			},
			success: function (res) {
				var followStatus = res.returnData.followStatus;
				$(_this).removeClass('follow');
				$(_this).text('')

				if (followStatus == 3) {
					$(_this).addClass('follow');
				} else {
					$(_this).text('取消关注');
				}
			}
		});
	},

	getDetails: function (_id) {
		getUrl('activity/novaplan/pages/newsfeed/index.html?type=1&id=' + _id, 'self');
	},

	getShop: function (e, _id) {
		e.stopPropagation();
		getUrl('seller/index.html?mchtId=' + _id, 'self');
	},

	getCommodity: function (_id) {
		getUrl('goods/detail.html?id=' + _id, 'self');
	},
	// 转发
	getShare: function () {
		var _ts = this,
			_td = _ts.data,
			_this = $(_td.forwardTarget.ts_list);
		closeSelf('.popup-share-reduce')
		new ajax(getApi.addMemberDynamicForward, {
			data: {
				memberId: member.id,
				type: 1,
				memberDynamicId: _td.forwardTarget.id,
				mchtShopDynamicId: _td.forwardTarget.mchtShopDynamicId
			},
			success: function (res) {
				_this.find('b').html(res.returnData.forwardCount);
				delayTip('转发成功');
			},
			limit: _this
		});
	},
	//微信转发
	getShareWx: function () {
		shareHand.tip()
		closeSelf('.popup-share-reduce')
	},

	getFabulous: function (_this, _id, _id2) {
		var _ts = this,
			_td = _ts.data,
			_this = $(_this);

		new ajax(getApi.addMemberDynamicFabulous, {
			data: {
				memberId: member.id,
				type: _this.data('type'),
				memberDynamicId: _this.data('id'),
				mchtShopDynamicId: _this.data('mcht')
			},
			success: function (res) {
				var _list = res.returnData;

				_this.find('b').html(_list.fabulousCount === '0' ? '赞' : _list.fabulousCount);
				_list.tallyModel === '1' ? _this.addClass('fabulous-1') : _this.removeClass('fabulous-1');

				// _list.fabulousCount == 0 && (_list.fabulousCount = '赞');
				// $(_this).text(_list.fabulousCount).removeClass('fabulous-1');
				// _list.tallyModel == 1 && $(_this).addClass('fabulous-1');
			},
			limit: 1
		});
	},

	//弹出动态操作
	showPopupDynamic: function (e) {
		e.stopPropagation();
		var _ts = this,
			_td = _ts.data,
			target = $(e.currentTarget).parent().parent().parent().parent().attr("data-obj");

		_td.dynamicTarget = JSON.parse(target)
		_ts.friendDynamic()

		popupShow('.popup-dynamic');
	},

	//获取用户关系
	friendDynamic: function () {
		var _ts = this,
			_td = _ts.data;
		new ajax(getApi.hasFocusOnFriends, {
			data: {
				friendMemberId: _td.frientMenberId,
				memberId: member.id
			},
			success: function (res) {
				console.log(res)
				_td.dynamicTarget.shieldingDynamics = res.returnData.shieldingDynamics
				_td.dynamicTarget.hasFocusOnFriends = res.returnData.hasFocusOnFriends
				_td.dynamicTarget.my = _td.my
				console.log(_td.dynamicTarget)
				my_popup_dynamic.html(template('friend_tpl_popup_dynamic', _td.dynamicTarget));
			}
		})
	},

	//动态文字高度
	ellipsisAdju: function (page) {
		const ts = this,
			td = ts.data;
		res = $('.my-nova .col-2')
		page == undefined && (page = 0)
		var _res = res.slice(page * 10, page * 10 + 10)

		for (var i = 0; i < _res.length; i++) {
			console.log($(_res[i]).height())
			if ($(_res[i]).height() > 48) {
				$(_res[i]).siblings(".btn-row-1").eq(0).removeClass("hide")
			}
			$(_res[i]).addClass("clamp-3")
		}
	},

	updateSd: function () {
		const _ts = this,
			_td = _ts.data;
		console.log(_td.dynamicTarget)
		//好友动态屏蔽
		new ajax(getApi.updateShieldingDynamics, {
			data: {
				type: 3,
				memberId: member.id,
				friendMemberId: _td.frientMenberId
			},
			success: function (res) {
				console.log(res)
				delayTip(res.returnData.shieldingDynamics == 0 ? '已接收好友动态' : '已屏蔽好友动态');
				_td.dynamicTarget.shieldingDynamics = res.returnData.shieldingDynamics
				my_popup_dynamic.html(template('friend_tpl_popup_dynamic', _td.dynamicTarget));
				closeSelf('.popup-dynamic')
				friendHomeApi.main(_td.tab, 1)
			}
		})


	},

	//关注 取消关注

	getFollow: function () {
		const _ts = this,
			_td = _ts.data;

		new ajax(getApi.updateMemberAttention, {
			data: {
				friendMemberId: _td.frientMenberId
			},
			success: function (res) {
				closeSelf('.popup-dynamic')
				delayTip(res.returnData.followStatus == 3 ? '取消关注' : '关注成功')
				friendHomeApi.main(_td.tab, 1)
			}
		});
	},

	//删除动态
	deteleDynamic: function () {
		const _ts = this,
			_td = _ts.data;
		new ajax(getApi.updateMemberDynamic, {
			data: {
				mchtShopDynamicId: _td.dynamicTarget.mchtShopDynamicId,
				memberId: member.id
			},
			success: function (res) {
				closeSelf('.popup-dynamic')
				delayTip('删除成功')
				friendHomeApi.main(_td.tab, 1)
			}
		})
	},
	//转发弹窗
	showForward: function (ts) {
		const _ts = this,
			_td = _ts.data;
		var target = $(ts).parent().parent().parent().attr("data-obj")
		_td.forwardTarget = JSON.parse(target)
		_td.forwardTarget.ts_list = ts

		popupShow('.popup-share-reduce');
	}

};

getTpl(function () {
	friendHomeApi.init();
});

friend_home_scroll.scroll(function () {
	var _header = friend_home.find('header');

	$(this).scrollTop() > 150 ? _header.addClass('pattern2') : _header.removeClass('pattern2');
});

function imgCacheMyNovaPlanPic(_list, _cac) {
	if (!_list || !_list.length) {
		return _list;
	}

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
			var _data = _list[i].productList;

			if (_data.length) {
				for (var k = 0; k < _data.length; k++) {
					var _pic = _data[k].productPic,
						_n = cache.list.name;

					if (_pic && _n.length) {
						for (var j = 0, _name = _pic.match(reg_cache_img)[0]; j < _n.length; j++) {
							(_name == _n[j]) && (_list[i].productList[k].productPic = cache.list.path[j]);
						}
					}
				}
			}
		}
	}

	for (var i = 0; i < _list.length; i++) {
		var _data = _list[i].productList;

		if (_data.length) {
			for (var k = 0; k < _data.length; k++) {
				var _pic = _data[k].productPic;

				if (_pic) {
					var _arr = _pic.match(reg_cache_img);

					cache.path.push(_arr.input);
					cache.name.push(_arr[0]);
				}
			}
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