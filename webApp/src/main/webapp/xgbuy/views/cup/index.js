var page_cup = $('.page[data-id="cup"]:visible'),
	cup_scroll = page_cup.find('.scroll-more'),
	cup_con = page_cup.find('.content'),
	cup_swiper = cup_con.find('.swiper-container'),
	cup_row1 = page_cup.find('.row-1'),
	cup_row21 = page_cup.find('.row-2 .con-1'),
	cup_row22 = page_cup.find('.row-2 .con-2'),
	cup_row23 = page_cup.find('.row-2 .con-3'),
	cup_guess = page_cup.find('.popup-guess .popup-cup-con'),

	cup_api = {
		pag: 10
	};

var cupApi = {
	data: {
		rate: 1, // 竞猜队伍的积分比例
		min: 100 // 参与竞猜的最小积分
	},

	init: function (_type) {
		var _this = this;
		getStorage('member_app') && (member.app = 1);

		if (member.app) {
			// cup_con.find('.tab-body').css('height', 'calc(100vh - 5.62rem' + (isiOS ? (isiOSX ? ' - 88px' : ' - 64px') : '') + ')');
			setStorage('member_app', 1);
		}

		$(function () {
			if (member.app) {
				loadShow();
				_this.requestMemberWeb(_type);
			} else {
				_this.post(_type, true);
			}
		});
	},

	requestMemberWeb: function (_type) {
		var _this = this;

		requestAnimationFrame(function () {
			if (member.web) {
				_this.post(_type, true);
				loadHide();
			} else {
				_this.requestMemberWeb(_type);
			}
		});
	},

	post: function (_type, _init, _scroll, _flesh) {
		var _this = this;

		new ajax(member.app ? getApi.getSportListH5 : getApi.getSportList, {
			data: {
				memberId: member.id,
				type: _type,
				currentPage: _scroll ? _scroll.attr('data-page') : 0
			},
			success: function (res) {
				var _list = res.returnData,
					_len = _list.dataList.length;

				if (_init) {
					_list.integral == '' && (_list.integral = 0);
					_list.nick == '' && (_list.nick = '游客');

					_list.min = _this.data.min;
					_list.max = _list.integral;
						
					cup_row1.html(template('cup_tpl_row1', _list));
					cup_guess.html(template('cup_tpl_guess', _list));

					if (_flesh) {
						return;
					}
				}

				if (_type == 1) {
					if (_len) {
						for (var i = 0; i < _len; i++) {
							var _date = new Date(_list.dataList[i].beginTime);

							_list.dataList[i].date = 1 + _date.getMonth() + '月' + _date.getDate() + '日';
							_list.dataList[i].time = famateNumber(_date.getHours()) + ':' + famateNumber(_date.getMinutes());
						}
					}

					_list.dataList = imgCacheHomeTeamPic(_list.dataList, 'cup_home_team_1');
					_list.dataList = imgCacheAwayTeamPic(_list.dataList, 'cup_away_team_1');

					if (_scroll) {
						if (_len) {
							cup_row21.append(template('cup_tpl_row2_con1', _list));
							var _cur = cup_row21.attr('data-page');
							_cur++;
							cup_row21.attr('data-page', _cur);
						} else {
							delayTip('已经全部加载完毕');
						}

						cup_row21.removeAttr('data-post');
					} else {
						cup_row21.html(template(_len ? 'cup_tpl_row2_con1' : 'cup_tpl_empty', _list));
						_len == cup_api.pag && _this.scroll(cup_row21, _type);
					}
				} else if (_type == 2) {
					var _date = new Date(_list.sportGuessEndTime);
					_list.date = 1 + _date.getMonth() + '月' + _date.getDate() + '日 ' + _date.getHours() + '点';
					cup_row22.html(template(_len ? 'cup_tpl_row2_con2' : 'cup_tpl_empty', _list));
				} else if (_type == 3) {
					if (_len) {
						for (var i = 0; i < _len; i++) {
							var _date = new Date(_list.dataList[i].beginTime);
							_list.dataList[i].date = 1 + _date.getMonth() + '月' + _date.getDate() + '日';
						}
					}

					_list.dataList = imgCacheHomeTeamPic(_list.dataList, 'cup_home_team_2');
					_list.dataList = imgCacheAwayTeamPic(_list.dataList, 'cup_away_team_2');

					if (_scroll) {
						if (_len) {
							cup_row23.append(template('cup_tpl_row2_con3', _list));
							var _cur = cup_row23.attr('data-page');
							_cur++;
							cup_row23.attr('data-page', _cur);
						} else {
							delayTip('已经全部加载完毕');
						}

						cup_row23.removeAttr('data-post');
					} else {
						cup_row23.html(template(_len ? 'cup_tpl_row2_con3' : 'cup_tpl_empty', _list));
						_len == cup_api.pag && _this.scroll(cup_row23, _type);
					}
				}

				if (_init && !_flesh) {
					cup_swiper.removeClass('hide');

					initSwiper({
						autoplay: 5e3
					});
				}
			}
		});
	},

	scroll: function (_con, _type) {
		var _this = this;

		if (!_con.attr('data-page')) {
			_con.attr('data-page', 1);
			_con.attr('data-old', 0);
			_con.attr('data-top', 0);
			_this.scrollFn(_con, _type);
		}

		_con.scrollTop(_con.attr('data-top'));

		cup_scroll.scroll(function () {
			if (!_con.is(':visible')) {
				return;
			}
			var _t = cup_scroll.scrollTop();

			_t > cup_scroll.height() ? scrollHand.show(cup_scroll) : scrollHand.hide(cup_scroll);

			_con.attr('data-top', _t);
			_this.scrollFn(_con, _type);
		});
	},

	scrollFn: function (_con, _type) {
		if (_con.attr('data-post')) {
			return;
		}

		_con.attr('data-post', true);

		var _this = this,
			_cur = _con.attr('data-page'),
			_old = _con.attr('data-old');

		if (_old < _cur && cup_scroll[0].scrollTop + win_h / 4 > cup_scroll[0].scrollHeight - cup_scroll[0].offsetHeight) {
			_con.attr('data-old', _cur);
			_this.post(_type, false, _con);
		} else {
			_con.removeAttr('data-post');
		}
	},

	guess: function (_this, _sec) {
		if (_sec && !$(_this).hasClass('on')) {
			return;
		}

		var _type = $(_this).attr('data-type'),
			_sportid = $(_this).attr('data-sport'),
			_teamid = $(_this).attr('data-team');

		this.data.rate = $(_this).attr('data-rate')
		this.integralChange();

		popupShow('.popup-guess', null, function (e) {
			e.attr('data-type', _type || '');
			e.attr('data-sportid', _sportid || '');
			e.attr('data-teamid', _teamid || '');

			e.find('.con-1').addClass('hide').first().removeClass('hide').children().removeClass('on').first().addClass('on');
		});
	},

	integralChange: function () {
		var _inter1 = 100,
			_inter2 = 100,
			_child = cup_guess.find('.con-1:not(.hide)'),
			_input = _child.find('input'),
			_num = Number(_input.length ? _input.val() :  _child.find('.on').text());

		if (_num) {
			_inter1 = _num;
			_inter2 = _num * this.data.rate;
		}

		cup_guess.find('.integral-0').html(parseInt(_inter1));
		cup_guess.find('.integral-1').html(parseInt(_inter2));
	},

	guessPoints: function (_this) {
		$(_this).addClass('on').siblings().removeClass('on');
		this.integralChange();
	},
	guessPointsOther: function (_this) {
		$(_this).parent().addClass('hide').siblings('.con-1').removeClass('hide');
		this.integralChange();
	},
	guessInput: function (_this) {
		$(_this).val(parseInt($(_this).val()));
		this.integralChange();
	},
	guessSubmit: function (_min, _max) {
		var _that = this,
			_parent = cup_guess.parent(),
			_sportid = _parent.attr('data-sportid'),
			_teamid = _parent.attr('data-teamid'),
			_type = _parent.attr('data-type'),

			_child = cup_guess.find('.con-1:not(.hide)'),
			_input = _child.find('input'),
			_num = Number(_input.length ? _input.val() :  _child.find('.on').text());

		if (_num) {
			// if (_num > _max) {
			// 	delayTip('您的积分不足');
			// } else if (_num < _min) {
			// 	delayTip('输入的竞猜积分需要大于100');
			// } else {
			// }
			new ajax(member.app ? getApi.addSportGuessH5 : getApi.addSportGuess, {
				data: {
					memberId: member.id,
					integral: _num,
					sportTeamId: _teamid,
					sportId: _sportid,
					type: _type
				},
				success: function () {
					popupShow('.popup-succ');
					_that.post(1, true, false, true);
					closeSelf('.popup-guess');
				}
			});
		} else {
			delayTip('请选择竞猜积分');
		}
	},

	changeTab: function (_this) {
		if (!$(_this).hasClass('active')) {
			$(_this).addClass('active').siblings().removeClass('active').parent().siblings().find('.tab-con').eq($(_this).index()).removeClass('hide').siblings().addClass('hide');
			!$(_this).hasClass('data') && this.post(1 + $(_this).index());
			$(_this).addClass('data');
		}
	},

	getUrlIntegral: function () {
		if (member.sys == 'android') {
			intentIntegralMall();
		} else if (member.sys == 'ios') {
			callIntentTrailer('intentTrailerListNative', 'intentIntegralMall');
		} else {
			getUrl('integral/detail/index.html');
		}
	}
};

getTpl(function () {
	cupApi.init(1);
});

function imgCacheHomeTeamPic(_list, _cac) {
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
			var _pic = _list[i].homeTeamPic;

			if (_pic) {
				for (var j = 0, _name = _pic.match(reg_cache_img)[0]; j < cache.list.name.length; j++) {
					(_name == cache.list.name[j]) && (_list[i].homeTeamPic = cache.list.path[j]);
				}
			}
		}
	}

	for (var i = 0; i < _list.length; i++) {
		var _pic = _list[i].homeTeamPic;

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

function imgCacheAwayTeamPic(_list, _cac) {
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
			var _pic = _list[i].awayTeamPic;

			if (_pic) {
				for (var j = 0, _name = _pic.match(reg_cache_img)[0]; j < cache.list.name.length; j++) {
					(_name == cache.list.name[j]) && (_list[i].awayTeamPic = cache.list.path[j]);
				}
			}
		}
	}

	for (var i = 0; i < _list.length; i++) {
		var _pic = _list[i].awayTeamPic;

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