var friend_home = $('.page[data-id="activity_shopowner_pages_friend_home"]:visible'),
	friend_home_scroll = friend_home.find('.scroll-more'),
	friend_home_scroll_user = friend_home.find('.user'),
	friend_home_scroll_list = friend_home.find('.list');

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
						_cur++;
						friend_home_scroll.attr('data-page', _cur);
						friend_home_scroll_list.append(template('friend_home_list', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					friend_home_scroll_list.html(template('friend_home_list', _list));

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

		getUrl('activity/shopowner/pages/friend/home/offriends/index.html?frientMenberId=' + _td.frientMenberId, 'self');
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
		getUrl('activity/shopowner/pages/newsfeed/index.html?type=1&id=' + _id, 'self');
	},

	getShop: function (e,_id) {
		e.stopPropagation();
		getUrl('seller/index.html?mchtId=' + _id, 'self');
	},

	getCommodity: function (_id) {
		getUrl('goods/detail.html?id=' + _id, 'self');	
	},

	getShare: function (_this,_id,_id2) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.addMemberDynamicForward, {
			data: {
				type: 1,
				memberDynamicId: _id,
				mchtShopDynamicId: _id2
			},
			success: function (res) {
				var _list = res.returnData;
				
				$(_this).text(_list.forwardCount);
				delayTip('转发成功');
			},
			limit: 1
		});
	},

	getFabulous: function (_this,_id,_id2) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.addMemberDynamicFabulous, {
			data: {
				type: 1,
				memberDynamicId: _id,
				mchtShopDynamicId: _id2
			},
			success: function (res) {
				var _list = res.returnData;
				
				_list.fabulousCount == 0 && (_list.fabulousCount = '赞');
				$(_this).text(_list.fabulousCount).removeClass('fabulous-1');
				_list.tallyModel == 1 && $(_this).addClass('fabulous-1');
			},
			limit: 1
		});
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