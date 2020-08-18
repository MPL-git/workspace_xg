var single_duanma = $('.page[data-id="activity_templet_single_duanma"]:visible'),
	single_duanma_tby = single_duanma.find('.tab-body'),
	single_duanma_tag1 = single_duanma.find('.tag1'),
	single_duanma_tag2 = single_duanma.find('.tag2'),
	single_duanma_tag3 = single_duanma.find('.tag3'),
	single_duanma_scroll = single_duanma.find('.scroll-more');

var singleDuanma = {
	data: {
		size: 10
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '17'
		});

		new ajax(getApi.getSingleScreeningConditions6, {
			data: {
				type: 6
			},
			success: function (res) {
				var _list = res.returnData,
					_tab1 = _list.categoryList,
					_tab2 = '',
					_tab3 = _list.sizeList,

					list_tab1 = '',
					list_tab3 = '',
					_arr = [];

				for (var i = 0; i < _tab1.length; i++) {
					for (var s = 0; s < _tab1[i].categoryTabList.length; s++) {
						_tab1[i].categoryTabList[s].productTypeidx = s + 1;
						_arr.push(_tab1[i].categoryTabList[s]);
					}
				}

				if (_arr.length) {
						var list_tab2 = '<div class="flex scroll-x ac w100">';

						if (_arr.length) {
							for (var j = 0; j < _arr.length; j++) {
								list_tab2 += '<a class="flex fn ac jc show-active' + (j == 0 ? ' active' : '') + '" data-idx="' + _arr[j].productTypeidx + '" data-id2="' + _arr[j].productTypeIdTwo + '" onclick="singleDuanma.filterDuanma2(this)">' + _arr[j].productTypeName + '</a>';
							}
						}
						list_tab2 += '</div>';
						list_tab2 += '<div class="flex ac jc tab-select hide" onclick="popupShow(\'.popup-select\')"><img class ="tab-retract" src="activity/templet/images/tab-retract.png"></div>';
						
					single_duanma_tag2.append(list_tab2);
					
					_tab1[0].categoryTabList.length && (_td.id2 = _tab1[0].categoryTabList[0].productTypeIdTwo);
					var tab_select = single_duanma_tag2.find('.tab-select');
					var tab_scroll = single_duanma_tag2.find('.scroll-x');

					if (_arr.length > 4) {
						if (tab_select.hasClass('hide')) {
							tab_select.removeClass('hide');
							tab_scroll.addClass('w88');
						}
					} else {
						tab_select.addClass('hide');
						tab_scroll.removeClass('w88');
					}
				}

				if (_arr.length) {
					var list_tab4 = '<div>';

					if (_arr.length) {
						for (var j = 0; j < _arr.length; j++) {
							list_tab4 += '<a class="flex fn ac jc show-active' + (j == 0 ? ' active' : '') + '" data-idx="' + _arr[j].productTypeidx + '" data-id2="' + _arr[j].productTypeIdTwo + '" onclick="singleDuanma.filterDuanma2(this)">' + _arr[j].productTypeName + '</a>';
						}
					}
					list_tab4 += '</div>';
					
					single_duanma_tby.append(list_tab4);
				}

				if (_tab3.length) {
					_td.sizeName = _list.name;
					list_tab3 = '<div class="fg flex fw"><span class="flex fn ac jc">' + _list.name + '</span>';

					for (var i = 0; i < _tab3.length; i++) {
						list_tab3 += '<a class="flex fn ac jc" data-id3="' + _tab3[i].propValId + '" onclick="singleDuanma.filterDuanma3(this)">' + _tab3[i].propValue + '</a>';
					}
					
					list_tab3 += '</div>';
				}

				single_duanma_tag1.html(list_tab1);
				single_duanma_tag3.html(list_tab3);

				var tab_footer = single_duanma.find('.tab-footer');

				_tab3.length > 6 ? tab_footer.removeClass('hide') : tab_footer.addClass('hide');

				_ts.getPagingList();
			}
		});
	},

	getPagingList: function (_scroll) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getSingleBrokenCodeClearingActivityList, {
			data: {
				type: 6,
				productTypeIdTwo: _td.id2,
				propValId: _td.id3 || '',
				currentPage: single_duanma_scroll.attr('data-page') || 0,
				pageSize: _td.size
			},
			success: function (res) {
				var _size = res.returnSize,
					_list = res.returnData,
					_prod = _list.list,
					_len = _prod.length;

				if (_prod) {
					for (var i = 0; i < _len; i++) {
						_prod[i].discount = famateDiscount(_prod[i].discount);
					}
				}

				_list.list = imgCacheProductPic(_list.list, 'activity_single_duanma_list_tpl');

				if (_scroll) {
					if (_len) {
						var _cur = single_duanma_scroll.attr('data-page');
						_cur++;
						single_duanma_scroll.attr('data-page', _cur);
						single_duanma_scroll.append(template('activity_single_duanma_list_tpl', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_size && (_td.size = _size);
					single_duanma_scroll.html(template('activity_single_duanma_list_tpl', _list));
					!_len && single_duanma_scroll.addClass('pt0');

					_len == _td.size && scrollHand.init(single_duanma_scroll, function () {
						_ts.getPagingList(true);
					}, true);
				}
			}
		});
	},

	tapNav: function () {
		single_duanma_scroll.removeAttr('data-page').removeAttr('data-old');

		this.getPagingList();
	},

	filterDuanma2: function (_this) {
		var _ts = this,
			_td = _ts.data;

		_td.id2 = $(_this).data('id2');
		_td.idx = $(_this).data('idx');
		_td.id3 = '';
		
		new ajax(getApi.getTwoCategoryProductSize, {
			data: {
				type: 6,
				productTypeIdTwo: _td.id2
			},
			success: function (res) {
				var _tab3 = res.returnData.sizeList,
					list_tab3 = '';

				if (_tab3.length) {
					list_tab3 = '<div class="fg flex fw"><span class="flex fn ac jc">' + _td.sizeName + '</span>';

					for (var i = 0; i < _tab3.length; i++) {
						list_tab3 += '<a class="flex fn ac jc" data-id3="' + _tab3[i].propValId + '" onclick="singleDuanma.filterDuanma3(this)">' + _tab3[i].propValue + '</a>';
					}
					
					list_tab3 += '</div>';
				}

				single_duanma_tag3.html(list_tab3);

				_ts.tapNav();

				var scrolll_left = single_duanma_tag2.find('.w88');

				if (_td.idx < 4 ) {
					_td.idx = 0
				}
				scrolll_left.scrollLeft(_td.idx * 70);

				var tab_footer = single_duanma.find('.tab-footer');

				single_duanma_tag3.removeClass('h88');

				if (_tab3.length > 6) {
					if (tab_footer.hasClass('hide')) {
						tab_footer.removeClass('hide');
					}
				} else {
					tab_footer.addClass('hide');
				}

				_ts.sizeShow();
				closeSelf();

				var arr = single_duanma.find('.show-active');

				for (var i = 0; i < arr.length; i++) {
					$(arr[i]).removeClass('active');

					$(arr[i]).data('id2') == _td.id2 && $(arr[i]).addClass('active');
				}
			}
		});
	},

	filterDuanma3: function (_this) {
		var _ts = this,
			_td = _ts.data;

		_this = $(_this);

		if (_this.hasClass('active')) {
			_this.removeClass('active');
			_td.id3 = '';
		} else {
			_this.addClass('active').siblings().removeClass('active');
			_td.id3 = _this.data('id3');
		}
		
		_ts.tapNav();
	},

	sizeShow: function () {
		if (single_duanma_tag3.hasClass('h88')) {
			single_duanma_tag3.removeClass('h88');
			single_duanma.find('.clearance-retract').addClass('hide');
			single_duanma.find('.clearance-close').removeClass('hide');
		} else {
			single_duanma_tag3.addClass('h88');
			single_duanma.find('.clearance-close').addClass('hide');
			single_duanma.find('.clearance-retract').removeClass('hide');
		}
	}
};

getTpl(function () {
	singleDuanma.init();
});

function onShow() {
	traffic.setPv(singleDuanma.data);
}