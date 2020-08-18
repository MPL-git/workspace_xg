var novaplan_friend = $('.page[data-id="activity_shopowner_pages_friend"]:visible'),
	novaplan_friend_scroll = novaplan_friend.find('.scroll-more'),
	novaplan_friend_tab = novaplan_friend.find('.tab-top a');

var novaplanFriend = {
	data: {
		tab: getStorage('friend_typ') ? getStorage('friend_typ').replace('_', '') : '1'
	},

	init: function () {
		var _ts = this,
			_td = _ts.data,
			_index = Number(_td.tab) - 1;

		novaplan_friend_tab.eq(_index).addClass('active');
		removeStorage('friend_typ');

		_ts.main();
	},

	main: function (_scroll) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getMemberAttentions, {
			data: {
				type: _td.tab,
				currentPage: novaplan_friend_scroll.attr('data-page') || 0,
				pageSize: 10
			},
			success: function (res) {
				var _list = res.returnData;

				_list.dataList = imgCachePic(_list.dataList, 'activity_novaplan_pages_friend_head');

				if (_scroll) {
					if (_list.dataList.length) {
						var _cur = novaplan_friend_scroll.attr('data-page');
						_cur++;
						novaplan_friend_scroll.attr('data-page', _cur);
						novaplan_friend_scroll.append(template('friend_list', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_list.tab = _td.tab;
					novaplan_friend_scroll.html(template('friend_list', _list));

					_list.dataList.length == 10 && scrollHand.init(novaplan_friend_scroll, function () {
						_ts.main(true);
					}, true);
				}
			}
		});
	},

	getTab: function (index, _this) {
		var _ts = this,
			_td = _ts.data;

		_td.tab = index;
		novaplan_friend_tab.removeClass('active');
		novaplan_friend_tab.eq(index - 1).addClass('active');
		novaplan_friend_scroll.removeAttr('data-page');
		novaplan_friend_scroll.removeAttr('data-old');

		_ts.main();
	},

	goHome: function (_id) {
		var _ts = this,
			_td = _ts.data;

		getUrl('activity/shopowner/pages/friend/home/index.html?frientMenberId=' + _id, 'self');
	},

	getFollow: function (e, _this, _id) {
		e.stopPropagation();
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.updateMemberAttention, {
			data: {
				friendMemberId: _id
			},
			success: function (res) {
				var followStatus = res.returnData.followStatus;
				$(_this).removeClass('follow-2 follow-3');

				if (followStatus == 1) {
					$(_this).addClass('follow-2');
				} else if (followStatus == 2) {
					$(_this).addClass('follow-3');
				}
			}
		});
	}
};

getTpl(function () {
	novaplanFriend.init();
});

$(function () {
	var friend_refresh_text = document.querySelector('.novaplan-friend .refreshText'),
		friend_refresh_container = document.querySelector('.novaplan-friend .scroll-y'),
		friend_is_down = 0,
		friend_refresh = 0,
		friend_startY;

	friend_refresh_container.addEventListener('touchstart', function(e) {
		if (friend_refresh_container.scrollTop == 0) {
			friend_is_down = 1;
			friend_startY = e.changedTouches[0].clientY;
		}
	});

	friend_refresh_container.addEventListener('touchmove', function (e) {
		if(friend_is_down == 1) {
			var dy = e.changedTouches[0].clientY - friend_startY;
			if(dy > 20) {
				e.preventDefault();
				friend_refresh_text.style.display = 'block';
				dy > 100 ? dy = 100 : dy;
				friend_refresh_text.style.height = dy + 'px';
				friend_refresh_text.style.lineHeight = dy + 'px';
				
				if (dy > 40) {
					friend_refresh_text.innerText = '松开刷新';
					friend_refresh = 1;
				} else {
					friend_refresh_text.innerText = '下拉刷新';
					friend_refresh = 0;
				}
			} else {
				friend_refresh_text.style.height = 0;
				friend_refresh_text.style.lineHeight = 0;
				friend_refresh_text.style.display = 'none';
			}
		}
	});

	friend_refresh_container.addEventListener('touchend', function (e) {
		friend_is_down = 0;
		friend_refresh_text.style.height = 0;
		friend_refresh_text.style.lineHeight = 0;
		friend_refresh_text.style.display = 'none';

		if (friend_refresh) {
			setStorage('friend_typ', '_' + novaplanFriend.data.tab);
			getUrl('activity/shopowner/pages/friend/index.html', 'self');
		}
	});
});