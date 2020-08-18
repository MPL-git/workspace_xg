var novaplan_friend = $('.page[data-id="activity_novaplan_pages_friend"]:visible'),
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
				_td.tab != 3 && _ts.swipeleft(novaplan_friend_scroll.children());

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

		getUrl('activity/novaplan/pages/friend/home/index.html?frientMenberId=' + _id, 'self');
	},

	getFollow: function (e) {
		e.stopPropagation();
		var _ts = this,
			_td = _ts.data,
			_this = e.currentTarget;
			_id = $(_this).attr("data-id");
			
			

		new ajax(getApi.updateMemberAttention, {
			data: {
				friendMemberId: _id
			},
			success: function (res) {
				console.log(res)
				delayTip(res.returnData.followStatus == 3 ? '取消关注' : '关注成功');
				novaplanFriend.getTab(_td.tab)
			}
		});
	},
	
	swipeleft: function (e) {
		var _ts = this,
			_td = _ts.data,
			_l = 0,
			_w = 0;

		if (isAndroid || isiOS) {
			e.on('touchstart', function(e) {
				_l = e.targetTouches[0].pageX;
				!_w && (_w = $(this).find('.slip-btn-friend').width());
			}).on('touchmove', function(e) {
				var left = parseInt($(this).attr('data-end'));
				isNaN(left) && (left = 0);
				var _c = e.targetTouches[0].pageX - _l + left;

				_c > 0 && (_c = 0);
				_c < -_w && (_c = -_w);
				_ts.swipeMove($(this), _c + 'px');
				_ts.swipeMove($(this).siblings(), 0, true);
				$(this).siblings().removeAttr('data-end');
			}).on('touchend', function(e) {
				var _s = 0,
					_f = parseInt($(this).attr('data-left'));

				_f < -_w / 2 && (_s = -_w + 'px');
				_ts.swipeMove($(this), _s, true);
				$(this).attr('data-end', _s);
			});
		} else {
			e.swipeleft(function (evt) {
				stopSelf(evt);
				defSelf(evt);
				_ts.swipeMove($(this), '-1.2rem', true);
				_ts.swipeMove($(this).siblings(), 0, true);
				_td.slide = 0;
			}).swiperight(function (evt) {
				stopSelf(evt);
				defSelf(evt);
				_ts.swipeMove($(this), 0, true);
				_td.slide = 0;
			}).click(function () {
				_td.slide = 1;
			});
		}
	},

	swipeMove: function (e, r, s) {
		s ? e.addClass('trans') : e.removeClass('trans');

		e.attr('data-left', r).css({
			'-webkit-transform': 'translate3d(' + r + ', 0, 0)',
			'transform': 'translate3d(' + r + ', 0, 0)'
		});
	},
	slipClick: function(e){
		var _ts = this,
		_td = _ts.data;
		console.log(e)
		e.stopPropagation();
		var _e = $(e.currentTarget).parent(),
		_w = _e.find(".slip-btn-friend").width()
		if(_e.attr('data-end') != 0){
			_ts.swipeMove(_e, 0, true);
			_e.attr('data-end', 0);
		}else{
			_ts.swipeMove(_e.siblings(), 0, true);
			_ts.swipeMove(_e, -_w + 'px', true);
			_e.attr('data-end', -_w + 'px');
		}
	},
  //屏蔽动态
  updateDynamics: function(e){
	var _ts = this,
	_td = _ts.data;
	e.stopPropagation();

	var _e = $(e.currentTarget)
    new ajax(getApi.updateShieldingDynamics, {
      data: {
        type: 3,
        memberId: member.id,
        friendMemberId: _e.attr('data-id')
      },
      success: function (res) {
        console.log(res)
        delayTip(res.returnData.shieldingDynamics == 0 ? '已接收好友动态' : '已屏蔽好友动态');
        novaplanFriend.getTab(_td.tab)
      }
    })
  },
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
			getUrl('activity/novaplan/pages/friend/index.html', 'self');
		}
	});
});