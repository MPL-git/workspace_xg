var cup_record = $('.page[data-id="cup_record"]:visible'),
	cup_record_con1 = cup_record.find('.tab-body .con-1'),
	cup_record_con2 = cup_record.find('.tab-body .con-2'),
	cup_record_con3 = cup_record.find('.tab-body .con-3'),

	cup_record_api = {
		pag: 10
	};

var cupRecordApi = {
	init: function (_type) {
		var _this = this,
			_con = cup_record_con1;

		getStorage('member_app') && (member.app = 1);

		if (member.app) {
			cup_record.find('.tab-body').css('height', 'calc(100vh - .8rem' + (isiOS ? (isiOSX ? ' - 88px' : ' - 64px') : '') + ')');
			setStorage('member_app', 1);
		}

		if (_type == 1) {
			_con = cup_record_con2;
		} else if (_type == 2) {
			_con = cup_record_con3;
		}

		_this.post(_type, false, _con);
	},

	post: function (_type, _scroll, _con) {
		var _this = this;

		new ajax(member.app ? getApi.getSportGuessRecordH5 : getApi.getSportGuessRecord, {
			data: {
				memberId: member.id,
				result: _type || '',
				currentPage: _scroll ? _con.attr('data-page') : 0
			},
			success: function (res) {
				var _len = res.returnData.length;

				if (_scroll) {
					if (_len) {
						_con.append(template('cup_record_tpl', res));
						var _cur = _con.attr('data-page');
						_cur++;
						_con.attr('data-page', _cur);
					} else {
						delayTip('已经全部加载完毕');
					}

					_con.removeAttr('data-post');
				} else {
					_con.html(template(_len ? 'cup_record_tpl' : 'cup_record_tpl_empty', res));
					_len == cup_record_api.pag && _this.scroll(_con, _type);
				}
			},
			error: function (res) {
				var _e = typeof(res) == 'object' ? res : JSON.parse(res);
				_e.returnCode == '1001' && back();
			}
		});
	},

	scroll: function (_con, _type) {
		var _this = this;

		if (!_con.attr('data-page')) {
			_con.attr('data-page', 1);
			_con.attr('data-old', 0);
			_this.scrollFn(_con, _type);
		}

		_con.scroll(function () {
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

		if (_old < _cur && _con[0].scrollTop + win_h / 4 > _con[0].scrollHeight - _con[0].offsetHeight) {
			_con.attr('data-old', _cur);
			_this.post(_type, true, _con);
		} else {
			_con.removeAttr('data-post');
		}
	},

	changeTab: function (_this) {
		$(_this).addClass('active').siblings().removeClass('active').parent().siblings().find('.tab-con').eq($(_this).index()).removeClass('hide').siblings().addClass('hide');
		!$(_this).hasClass('data') && this.init($(_this).index());
		$(_this).addClass('data');
	},

	slideUp: function (_this) {
		if ($(_this).hasClass('on')) {
			$(_this).removeClass('on').parent().siblings().stop().slideUp();
		} else {
			$(_this).addClass('on').parent().siblings().stop().slideDown();
		}
	}
};

getTpl(function () {
	cupRecordApi.init();
});