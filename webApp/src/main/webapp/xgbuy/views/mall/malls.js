var malls = $('.page.malls:visible'),
	head_search = malls.find('.head-search input'),
	mask_search = malls.find('.mask-search');

var mallsApi = {
	data: {
		cache: getStorage('mall_search_cache') ? JSON.parse(getStorage('mall_search_cache')) : [], // 历史记录本地存储
		slide: false // 历史记录左右滑动中
	},

	blur: function () {
		malls.removeClass('mallf');
	},

	focus: function (_this) {
		var _ts = this,
			_td = _ts.data;

		malls = $(_this).closest('.malls');
		head_search = malls.find('.head-search input');
		mask_search = malls.find('.mask-search');

		if (malls.hasClass('mallf')) {
			return;
		}

		malls.addClass('mallf');

		require(['template'], function (template) {
			new ajax(getApi.getHotSearchWord, {
				success: function (e) {
					var _list = e.returnData;

					_list.cacheList = _td.cache;

					mask_search.html(template('mall_search_tpl', _list));
					_ts.swipeleft(mask_search.find('.row-2 li'));
				}
			});
		});
	},
	
	submit: function (_name) {
		stopSelf();
		defSelf();

		var _ts = this,
			_td = _ts.data,
			_val = _name || $.trim(head_search.val()) || head_search.attr('placeholder');

		if (_td.slide || !_val.replace(/\s/g, '')) {
			return;
		}

		require(['template'], function (template) {
			new ajax(getApi.addHotSearchWord, {
				data: {
					memberId: member.id,
					client: member.psys,
					reqImei: '',
					searchName: _val
				},
				success: function (e) {
					var _list = e.returnData,
						_index = undefined;

					for (var i = 0; i < _td.cache.length; i++) {
						if (_td.cache[i].searchName == _val) {
							_index = i;
							break;
						}
					}

					_index !== undefined && (_val = _td.cache.splice(_index, 1)[0].searchName);

					_td.cache.unshift({
						searchName: _val
					});

					setStorage('mall_search_cache', JSON.stringify(_td.cache));
					_ts.blur();

					removeStorage('mall_category_id');
					removeStorage('mall_brand_id');
					setStorage('mask_search_name', _val);
					setStorage('mall_input_name', _val);
					getUrl('mall/list/index.html', 'self');
				},
				limit: 1
			});
		});
	},

	update: function (e) {
		stopSelf();
		var _ts = this,
			_td = _ts.data;

		require(['template'], function (template) {
			if (e) {
				_td.cache.splice($(e).parent().index(), 1);
				setStorage('mall_search_cache', JSON.stringify(_td.cache));

				mask_search.find('.row-2 ul').html(template('mall_search_tpl_cache', {
					cacheList: _td.cache
				}));

				_ts.swipeleft(mask_search.find('.row-2 li'));
			} else {
				_td.cache = [];
				removeStorage('mall_search_cache');
				mask_search.find('.row-2').empty();
			}

			(!_td.cache.length && !mask_search.find('.row-1').length) && mask_search.html(template('mall_search_tpl_empty'));
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
				!_w && (_w = $(this).find('span').width());
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
				_td.slide = true;
			}).swiperight(function (evt) {
				stopSelf(evt);
				defSelf(evt);
				_ts.swipeMove($(this), 0, true);
				_td.slide = true;
			}).click(function () {
				_td.slide = false;
			});
		}
	},

	swipeMove: function (e, r, s) {
		s ? e.addClass('trans') : e.removeClass('trans');

		e.attr('data-left', r).css({
			'-webkit-transform': 'translate3d(' + r + ', 0, 0)',
			'transform': 'translate3d(' + r + ', 0, 0)'
		});
	}
};