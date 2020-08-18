var novaplan_offriend = $('.page[data-id="activity_novaplan_pages_friend_home_offriends"]:visible'),
	novaplan_offriend_scroll = novaplan_offriend.find('.scroll-more'),
	novaplan_offriend_tab = novaplan_offriend.find('.tab-top a');

var novaplanOffriend = {
	data: {
		tab: getStorage('offriend_typ') ? getStorage('offriend_typ').replace('_', '') : '4'
	},

	init: function () {
		var _ts = this,
			_td = _ts.data,
			_index = Number(_td.tab) - 4;

		novaplan_offriend_tab.eq(_index).addClass('active');
		removeStorage('offriend_typ');

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
			
			_ts.main();
		});
	},

	main: function (_scroll) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getMemberAttentions, {
			data: {
				friendMemberId: _td.frientMenberId,
				type: _td.tab,
				currentPage: novaplan_offriend_scroll.attr('data-page') || 0,
				pageSize: 10
			},
			success: function (res) {
				var _list = res.returnData;

				_list.dataList = imgCachePic(_list.dataList, 'activity_novaplan_pages_friend_home_offriends_head');

				if (_scroll) {
					if (_list.dataList.length) {
						var _cur = novaplan_offriend_scroll.attr('data-page');
						_cur++;
						novaplan_offriend_scroll.attr('data-page', _cur);
						novaplan_offriend_scroll.append(template('offriend_list', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_list.tab = _td.tab;
					novaplan_offriend_scroll.html(template('offriend_list', _list));

					_list.dataList.length == 10 && scrollHand.init(novaplan_offriend_scroll, function () {
						_ts.main(true);
					}, true);
				}
				
				_list.followStatus != 4 && _ts.swipeleft(novaplan_offriend_scroll.children());
			}
		});
	},

	getTab: function (index, _this) {
		var _ts = this,
			_td = _ts.data;

		_td.tab = Number(index) + 3;
		novaplan_offriend_tab.removeClass('active');
		novaplan_offriend_tab.eq(index - 1).addClass('active');
		novaplan_offriend_scroll.attr('data-page', '0');

		_ts.main();
	},

	goHome: function (_id,followstatus) {
		var _ts = this,
			_td = _ts.data;

			getUrl('activity/novaplan/pages/friend/home/index.html?frientMenberId=' + _id + (followstatus == 4 ?'&my=true':''), 'self');
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
				novaplanOffriend.getTab(_td.tab - 3)
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
		if(r!=0 && e.attr("data-followStatus") == '4'){
			return
		}
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
	}
};

getTpl(function () {
	novaplanOffriend.init();
});

$(function () {
	var offriend_refresh_text = document.querySelector('.novaplan-offriends .refreshText'),
		offriend_refresh_container = document.querySelector('.novaplan-offriends .scroll-y'),
		offriend_is_down = 0,
		offriend_refresh = 0,
		offriend_startY;

	offriend_refresh_container.addEventListener('touchstart', function(e) {
		if (offriend_refresh_container.scrollTop == 0) {
			offriend_is_down = 1;
			offriend_startY = e.changedTouches[0].clientY;
		}
	});

	offriend_refresh_container.addEventListener('touchmove', function (e) {
		if(offriend_is_down == 1) {
			var dy = e.changedTouches[0].clientY - offriend_startY;
			if(dy > 20) {
				e.preventDefault();
				offriend_refresh_text.style.display = 'block';
				dy > 100 ? dy = 100 : dy;
				offriend_refresh_text.style.height = dy + 'px';
				offriend_refresh_text.style.lineHeight = dy + 'px';
				
				if (dy > 40) {
					offriend_refresh_text.innerText = '松开刷新';
					offriend_refresh = 1;
				} else {
					offriend_refresh_text.innerText = '下拉刷新';
					offriend_refresh = 0;
				}
			} else {
				offriend_refresh_text.style.height = 0;
				offriend_refresh_text.style.lineHeight = 0;
				offriend_refresh_text.style.display = 'none';
			}
		}
	});

	offriend_refresh_container.addEventListener('touchend', function (e) {
		offriend_is_down = 0;
		offriend_refresh_text.style.height = 0;
		offriend_refresh_text.style.lineHeight = 0;
		offriend_refresh_text.style.display = 'none';

		if (offriend_refresh) {
			setStorage('offriend_typ', '_' + novaplanOffriend.data.tab);
			getUrl('activity/novaplan/pages/friend/home/offriends/index.html?frientMenberId=' + novaplanOffriend.data.frientMenberId, 'self');
		}
	});
});