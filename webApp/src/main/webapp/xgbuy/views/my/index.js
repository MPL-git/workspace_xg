var page_my = $('.page[data-id="my"]:visible'),
	my_con = page_my.find('.main'),
	my_popup_dynamic = page_my.find('.popup-dynamic'),
	my_header = page_my.find('header').height(),
	my_con_nova_tab = '',
	my_con_nova_con = '',
	my_con_nova_top = '';

var myApi = {
	data: {
		xnid: '',
		tab: 1,
		pag: 10
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '36'
		});

		new ajax(getApi.userInfo, {
			success: function (res) {
				var _list = res.returnData;

				_td.xnid = _list.xiaoNengId;
				member.id = _list.memberId;
				my_con.html(template('my_tpl', _list));

				new ajax(getApi.userOrderCount, {
					success: function (e) {
						my_con.find('.my-order-more').html(template('my_tpl_order', e.returnData));
					},
					disLogin: 1
				});
				console.log(my_con)
				my_con_nova_tab = my_con.find('.tab-top a');
				my_con_nova_con = my_con.find('.tab-body');


				if (my_con_nova_tab.length) {
					my_con_nova_top = my_con_nova_tab.parent().offset().top - my_header;

					if (getStorage('novaplan_for_dynamic')) {
						removeStorage('novaplan_for_dynamic');
						_ts.main(_td.tab, 1);
					} else {
						_ts.main(_td.tab);
					}
				}

				var _yq = my_con.find('.novaplan-yq')[0];
				_yq && clipHnad(_yq);

				shareHand.update();
			},
			target: 1
		});
	},

	myApp: function () {
		confirmTip('请在APP上操作, 是否现在打开APP?', function () {
			closeSelf();
			getUrl('my/download/index.html');
		});
	},

	getMyUrlServer: function () {
		var _ts = this,
			_td = _ts.data;

		_td.xnid && (getUrlServer(_td.xnid), traffic.setPv(myApi.data, { pageType: '46', noTime: 1 }));
	},

	main: function (_tab, _top, _scroll) {
		var _ts = this,
			_td = _ts.data;

		if (!_scroll) {
			_td.tab = _tab;
			my_con.removeAttr('data-page');
			my_con_nova_tab.eq(_td.tab - 1).addClass('active').siblings().removeClass('active');

			_top && my_con.animate({
				scrollTop: my_con_nova_top
			}, 300);
		}

		new ajax(getApi.getMchtShopDynamicList, {
			data: {
				type: _td.tab,
				pageSize: _td.pag,
				currentPage: my_con.attr('data-page') || 0
			},
			success: function (e) {
				var _list = e.returnData,
					_size = e.returnSize,
					_data = _list.dataList,
					_len = _data.length;

				for (var i = 0; i < _len; i++) {
					_list.dataList[i].type = _data[i].type - 1;
				}

				_list.dataList = imgCachePic(_list.dataList, 'my_tpl_novaplan_head');
				_list.dataList = imgCacheMyNovaPlanPic(_list.dataList, 'my_tpl_novaplan_product');

				if (_scroll) {
					if (_len) {
						var _cur = my_con.attr('data-page');
						my_con_nova_con.append(template('my_tpl_novaplan_con', _list));
						_ts.ellipsisAdju(_cur)
						_cur++;
						my_con.attr('data-page', _cur);
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					my_con_nova_con.html(template('my_tpl_novaplan_con', _list));
					_ts.ellipsisAdju(_cur)

					_size && (_td.pag = _size);
					var _vh = 2 * (win_h - page_footer.height() - my_header);

					_len == _td.pag && scrollHand.init(my_con, function () {
						_ts.main(null, null, true);
					}, 0, function (_t) {
						_t > _vh ? scrollHand.show(my_con) : scrollHand.hide(my_con);
					});
				}
			}
		});
	},

	// 点赞
	getFabulous: function (_this) {
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
			},
			limit: _this
		});
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
				type: _td.forwardTarget.type,
				memberDynamicId: _td.forwardTarget.id,
				mchtShopDynamicId:_td.forwardTarget.mchtShopDynamicId
			},
			success: function (res) {
				_this.find('b').html(res.returnData.forwardCount);
				delayTip('转发成功');
			},
			limit: _this
		});
	},
	//微信转发
	getShareWx: function(){
		shareHand.tip()
		closeSelf('.popup-share-reduce')
	},

	//进入店铺
	getShop: function (e, _id) {
		e.stopPropagation();
		getUrl('seller/index.html?mchtId=' + _id, 'self');
	},

	//弹出动态操作
	showPopupDynamic: function (e) {
		e.stopPropagation();
		var _ts = this,
			_td = _ts.data,
			target = $(e.currentTarget).parent().parent().parent().parent().attr("data-obj");

		_td.dynamicTarget = JSON.parse(target)
		if (_td.dynamicTarget.type == 0) {
			_ts.shopDynamic()
		} else if (_td.dynamicTarget.type == 1) {
			_ts.friendDynamic()
		}

		popupShow('.popup-dynamic');
	},

	//获取店铺关系
	shopDynamic: function () {
		var _ts = this,
			_td = _ts.data;
		new ajax(getApi.hasCollectionMcht, {
			data: {
				type: 3,
				memberId: member.id,
				remindId: _td.dynamicTarget.mchtId
			},
			success: function (res) {
				_td.dynamicTarget.shieldingDynamics = res.returnData.shieldingDynamics
				_td.dynamicTarget.hasCollectionMcht = res.returnData.hasCollectionMcht
				my_popup_dynamic.html(template('my_tpl_popup_dynamic', _td.dynamicTarget));
			}
		})
	},
	//获取用户关系
	friendDynamic: function () {
		var _ts = this,
			_td = _ts.data;
		new ajax(getApi.hasFocusOnFriends, {
			data: {
				friendMemberId: _td.dynamicTarget.friendMemberId,
				memberId: member.id
			},
			success: function (res) {
				console.log(res)
				_td.dynamicTarget.shieldingDynamics = res.returnData.shieldingDynamics
				_td.dynamicTarget.hasFocusOnFriends = res.returnData.hasFocusOnFriends
				my_popup_dynamic.html(template('my_tpl_popup_dynamic', _td.dynamicTarget));
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

		for (let i = 0; i < _res.length; i++) {
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
		if (_td.dynamicTarget.source == 1) {
			//店铺动态屏蔽
			new ajax(getApi.updateMchtShieldingDynamics, {
				data: {
					type: 3,
					memberId: member.id,
					remindId: _td.dynamicTarget.mchtId
				},
				success: function (res) {
					console.log(res)
					delayTip(res.returnData.shieldingDynamics == 0 ? '已接收店铺动态，可前往收藏列表设置' : '已屏蔽店铺动态，可前往收藏列表设置');
					_td.dynamicTarget.shieldingDynamics = res.returnData.shieldingDynamics;
					my_popup_dynamic.html(template('my_tpl_popup_dynamic', _td.dynamicTarget));
					closeSelf('.popup-dynamic')
					myApi.main(_td.tab, 1)
				}
			})

		} else if (_td.dynamicTarget.source == 2) {
			//好友动态屏蔽
			new ajax(getApi.updateShieldingDynamics, {
				data: {
					type: 3,
					memberId: member.id,
					friendMemberId: _td.dynamicTarget.friendMemberId
				},
				success: function (res) {
					console.log(res)
					delayTip(res.returnData.shieldingDynamics == 0 ? '已接收好友动态，可前往好友列表设置' : '已屏蔽好友动态，可前往好友列表设置');
					_td.dynamicTarget.shieldingDynamics = res.returnData.shieldingDynamics
					my_popup_dynamic.html(template('my_tpl_popup_dynamic', _td.dynamicTarget));
					closeSelf('.popup-dynamic')
					myApi.main(_td.tab, 1)
				}
			})
		}


	},
	addCollect: function (e) {
		const _ts = this,
			_td = _ts.data;
		getRemind(e.currentTarget, '', function (res) {
			myApi.main(_td.tab, 1)
		});
	},
	//关注 取消关注

	getFollow: function () {
		const _ts = this,
			_td = _ts.data;

		new ajax(getApi.updateMemberAttention, {
			data: {
				friendMemberId: _td.dynamicTarget.friendMemberId
			},
			success: function (res) {
				closeSelf('.popup-dynamic')
				delayTip(res.returnData.followStatus == 3 ? '取消关注' : '关注成功')
				myApi.main(_td.tab, 1)
			}
		});
	},
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
				myApi.main(_td.tab, 1)
			}
		})
	},
		
	//转发弹窗
	showForward: function(ts){
		const _ts = this,
			_td = _ts.data;
		var target = $(ts).parent().parent().parent().attr("data-obj")
		_td.forwardTarget = JSON.parse(target)
		_td.forwardTarget.ts_list = ts

		popupShow('.popup-share-reduce');
	}
};

// 移除我的订单tab记录
removeStorage('order_typ');

getTpl(function () {
	myApi.init();
});

function onShow() {
	traffic.setPv(myApi.data);
}

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