var novaplan_newsfeed = $('.page[data-id="activity_shopowner_newsfeed"]:visible'),
	novaplan_newsfeed_row1 = novaplan_newsfeed.find('.row-1'),
	novaplan_newsfeed_row2 = novaplan_newsfeed.find('.row-2'),
	newsfeed_list_left = novaplan_newsfeed_row2.find('.left'),
	newsfeed_list_right = novaplan_newsfeed_row2.find('.right'),
	novaplan_newsfeed_row3 = novaplan_newsfeed.find('.row-3'),
	novaplan_newsfeed_btn = novaplan_newsfeed_row3.find('.btn');
	
var novaplanNewsfeedApi = {
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
						case 'type': _td.type = _b; break;
						case 'id': _td.id = _b; break;
					}
				}
			}

			if (_td.type == 1) {
				novaplan_newsfeed.find('.ellipsis').empty();
				novaplan_newsfeed.find('.ellipsis').html('好友动态');
			}

			_ts.post();
		});
	},

	post: function () {
		var _ts = this,
			_td = _ts.data;
			
		if (_td.type == 1) {
			new ajax(getApi.getMemberDynamic, {
				data: {
					memberId: _td.memberId,
					memberDynamicId: _td.id
				},
				success: function (e) {
					var _list = e.returnData,
						_len = _list.productList.length || 0,
						_mchtId = _list.mchtId,
						_attentionStatus = _list.attentionStatus,
						_friendMemberId = _list.friendMemberId,
						_attentionButton = _list.attentionButton,
						_mchtShopDynamicId = _list.mchtShopDynamicId;
						_left = { dataList: [] },
						_right = { dataList: [] };

					_td.attentionStatus = _attentionStatus;
					_td.mchtShopDynamicId = _mchtShopDynamicId;
					_td.memberDynamicId = _td.id;
					_td.friendMemberId = _friendMemberId;
					_td.mchtId = _mchtId;

					_list.topCover = imgCache(_list.topCover, 'novaplan_newsfeed_top_cover_1');
					_list.pic = imgCache(_list.pic, 'novaplan_newsfeed_top_pic_1');
					_list.productList = imgCacheProductPic(_list.productList, 'novaplan_newsfeed_product_1');
					_list.memberDynamicList = imgCacheMsdPic(_list.memberDynamicList, 'novaplan_newsfeed_msdpic_1');

					novaplan_newsfeed_row1.html(template('newsfeed_tpl_person_msg', _list));

					if (_attentionButton == 0) {
						novaplan_newsfeed.find('.ellipsis').empty();
						novaplan_newsfeed.find('.ellipsis').html('我的动态');
					}

					for (var i = 0; i < _len; i++) {
						i % 2 ? _right.dataList.push(_list.productList[i]) : _left.dataList.push(_list.productList[i]);
					}

					if (_len > 1) {
						newsfeed_list_left.html(template('newsfeed_list_tpl_product', _left));
						newsfeed_list_right.html(template('newsfeed_list_tpl_product', _right));
					} else if (_len == 1) {
						newsfeed_list_left.html(template('newsfeed_list_tpl_product', _left));
					} else {
						novaplan_newsfeed_row2.html(template('newsfeed_list_tpl_product', _left));
					}

					novaplan_newsfeed_row3.html(template('newsfeed_tpl_person', _list));
				}
			});
		} else {
			new ajax(getApi.getMchtShopDynamic, {
				data: {
					memberId: _td.memberId,
					mchtShopDynamicId: _td.id
				},
				success: function (e) {
					var _list = e.returnData,
						_len = _list.productList.length || 0,
						_mchtId = _list.mchtId,
						_isCollectuonShop = _list.isCollectuonShop,
						_left = { dataList: [] },
						_right = { dataList: [] };

					_td.isCollectuonShop = _isCollectuonShop;
					_td.mchtId = _mchtId;

					_list.topCover = imgCache(_list.topCover, 'novaplan_newsfeed_top_cover');
					_list.shopLogo = imgCache(_list.shopLogo, 'novaplan_newsfeed_top_pic');
					_list.productList = imgCacheProductPic(_list.productList, 'novaplan_newsfeed_product');
					_list.mchtShopDynamicList = imgCacheMsdPic(_list.mchtShopDynamicList, 'novaplan_newsfeed_msdpic');

					novaplan_newsfeed_row1.html(template('newsfeed_tpl_msg', _list));

					for (var i = 0; i < _len; i++) {
						i % 2 ? _right.dataList.push(_list.productList[i]) : _left.dataList.push(_list.productList[i]);
					}

					if (_len > 1) {
						newsfeed_list_left.html(template('newsfeed_list_tpl_product', _left));
						newsfeed_list_right.html(template('newsfeed_list_tpl_product', _right));
					} else if (_len == 1) {
						newsfeed_list_left.html(template('newsfeed_list_tpl_product', _left));
					} else {
						novaplan_newsfeed_row2.html(template('newsfeed_list_tpl_product', _left));
					}
					novaplan_newsfeed_row3.html(template('newsfeed_tpl_shop', _list));
				}
			});
		}
	},

	// 收藏
	collect: function (_this) {
		var _ts = this,
			_td = _ts.data;

		if (_td.isCollectuonShop) {
			new ajax(getApi.deleteRemindSale, {
				data: {
					memberId: _td.memberId,
					type: 3,
					remindId: _td.mchtId
				},
				success: function () {
					$(_this).removeClass('bgy');
					$(_this).addClass('bgr');
					$(_this).empty();
					$(_this).html('收藏');
					_td.isCollectuonShop = false;
					delayTip('已取消收藏');
				},
				limit: 1
			});
		} else {
			new ajax(getApi.addRemindSale, {
				data: {
					memberId: _td.memberId,
					remindType: 3,
					remindId: _td.mchtId
				},
				success: function () {
					$(_this).removeClass('bgr');
					$(_this).addClass('bgy');
					_td.isCollectuonShop = true;
					$(_this).empty();
					$(_this).html('已收藏');
					delayTip('收藏成功');
				},
				limit: 1
			});
		}
	},

	// 转发
	getShare: function () {
		var _ts = this,
			_td = _ts.data;

		if (_td.memberDynamicId) {
			new ajax(getApi.addMemberDynamicForward, {
				data: {
					memberId: _td.memberId,
					type: _td.type,
					mchtShopDynamicId: _td.mchtShopDynamicId,
					memberDynamicId: _td.id
				},
				success: function () {
					delayTip('转发成功');
				},
				limit: 1
			});
		} else {
			new ajax(getApi.addMemberDynamicForward, {
				data: {
					memberId: _td.memberId,
					type: _td.type,
					mchtShopDynamicId: _td.id
				},
				success: function () {
					delayTip('转发成功');
				},
				limit: 1
			});
		}
	},

	// 关注
	attention: function (_this) {
		var _ts = this,
			_td = _ts.data;

		if (_td.attentionStatus == 1) {
			new ajax(getApi.updateMemberAttention, {
				data: {
					memberId: _td.memberId,
					friendMemberId: _td.friendMemberId
				},
				success: function () {
					$(_this).removeClass('bgy');
					$(_this).addClass('bgr');
					$(_this).empty();
					$(_this).html('关注');
					_td.attentionStatus = false;
					delayTip('已取消关注');
				},
				limit: 1
			});
		} else {
			new ajax(getApi.updateMemberAttention, {
				data: {
					memberId: _td.memberId,
					friendMemberId: _td.friendMemberId
				},
				success: function () {
					$(_this).removeClass('bgr');
					$(_this).addClass('bgy');
					_td.attentionStatus = true;
					$(_this).empty();
					$(_this).html('已关注');
					delayTip('关注成功');
				},
				limit: 1
			});
		}
	}
};

getTpl(function () {
	novaplanNewsfeedApi.init();
});

function imgCacheMsdPic(_list, _cac) {
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
			var _pic = _list[i].msdPic;

			if (_pic) {
				for (var j = 0, _name = _pic.match(reg_cache_img)[0]; j < cache.list.name.length; j++) {
					(_name == cache.list.name[j]) && (_list[i].msdPic = cache.list.path[j]);
				}
			}
		}
	}

	for (var i = 0; i < _list.length; i++) {
		var _pic = _list[i].msdPic;

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