var mall_list = $('.page[data-id="mall_list"]:visible'),
	mall_list_head = mall_list.find('.head-search'),
	mall_list_scroll = mall_list.find('.scroll-more'),
	mall_list_tag = mall_list_scroll.find('.tag'),
	mall_list_product = mall_list_scroll.find('.product'),
	mall_list_left = mall_list_product.find('.left'),
	mall_list_right = mall_list_product.find('.right'),
	mall_list_pick = mall_list.find('.popup-mall-pick'),
	mall_list_brand = mall_list.find('.popup-mall-brand'),
	mall_list_category = mall_list.find('.popup-mall-category'),

	head_search = mall_list.find('.head-search input'),

	category_ipt = '',
	brand_ipt = '';

var mallListApi = {
	data: {
		searchName: getStorage('mask_search_name') || '',
		productTypeId: getStorage('mall_category_id') || '',
		brandId: getStorage('mall_brand_id') || '',
		saleWeightSorf: '',
		salePriceSorf: '',
		suitSex: '',
		suitGroup: '',
		season: '',
		priceMin: '',
		priceMax: '',

		size: 10,
		pick: '',
		brand: '',

		inputName: getStorage('mall_input_name') || '',
		categoryName: getStorage('mall_category_name') || '',
		brandName: getStorage('mall_brand_name') || ''
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'searchName': _td.searchName = _b; break;
					case 'productTypeId': _td.productTypeId = _b; break;
					case 'brandId': _td.brandId = _b; break;
					case 'categoryItemName': _td.categoryItemName = _b; break;
				}
			}
		}

		traffic.setPv(_td, {
			pageType: '58'
		});

		_ts.post();

		!_td.categoryItemName && (_td.categoryItemName = _td.inputName);
		head_search.attr('placeholder', _td.categoryItemName);
	},

	post: function (_scroll, _this, _submit) {
		var _ts = this,
			_td = _ts.data;

		_this && mall_list_scroll.removeAttr('data-page').removeAttr('data-old').scrollTop(0);

		require(['ceiling'], function () {
			new ajax(getApi.getShoppingMallProductListData, {
				data: {
					searchName: _td.searchName,
					productTypeId: _td.productTypeId,
					brandId: _td.brandId,
					saleWeightSorf: _td.saleWeightSorf,
					salePriceSorf: _td.salePriceSorf,
					suitSex: _td.suitSex,
					suitGroup: _td.suitGroup,
					season: _td.season,
					priceMin: _td.priceMin,
					priceMax: _td.priceMax,
					currentPage: mall_list_scroll.attr('data-page') || 0
				},
				success: function (e) {
					var _list = e.returnData,
						_size = e.returnSize,
						_len = _list.dataList.length,
						_tag = mall_list_tag.find('a'),

						_left = { dataList: [] };
						_right = { dataList: [] };

					_list.dataList = imgCacheProductPic(_list.dataList, 'mall_list_tpl_product');

					for (var i = 0; i < _len; i++) {
						i % 2 ? _right.dataList.push(_list.dataList[i]) : _left.dataList.push(_list.dataList[i]);
					}

					if (_scroll) {
						if (_len) {
							var _cur = mall_list_scroll.attr('data-page');
							_left.page = _cur * _ts.size;
							_right.page = _cur * _ts.size;
							_right.right = 1;
							_cur++;
							mall_list_scroll.attr('data-page', _cur);

							mall_list_left.append(template('mall_list_tpl_product', _left));
							mall_list_right.append(template('mall_list_tpl_product', _right));
						} else {
							delayTip('已经全部加载完毕');
						}
					} else {
						if (!_len) {
							mall_list_left.css('width', '100%');
							mall_list_right.hide();
						} else {
							mall_list_left.css('width', 'calc(50% - .05rem)');
							if(_right.dataList.length > 0) {
								mall_list_right.show();
							} else {
								mall_list_right.hide();
							}
						}

						_left.page = 0;
						_right.page = 0;
						_right.right = 1;

						mall_list_left.html(template('mall_list_tpl_product', _left));
						mall_list_right.html(template('mall_list_tpl_product', _right));

						if (!_submit) {
							if (!_this) {
								_size && (_ts.size = _size);
								mall_list_scroll.find('.tag').ceiling(mall_list_scroll, mall_list_head.height());
							}

							_this = _this ? $(_this) : _tag.eq(0);
							_tag.removeClass('active').removeClass('asc').removeClass('desc');
							_this.addClass(_td[_this.data('name')]).addClass('active');
						}

						traffic.delayRequest(function () {
							for (var i = 0; i < _list.dataList.length; i++) {
								traffic.setDtl(_td, {
									type: 3,
									id: _list.dataList[i].productId,
									pos: _left.page + i
								});
							}
						});

						_len == _td.size && scrollHand.init(mall_list_scroll, function () {
							_ts.post(true);
						}, true);
					}
				}
			});
		});
	},

	// 切换
	tag: function (_this) {
		var _ts = this,
			_td = _ts.data,
			$ts = $(_this),
			_name = $ts.data('name');

		if ($ts.hasClass('filter')) {
			_ts.filter(_this);
			return;
		}

		if (_name == '') {
			_td.salePriceSorf = '';
			_td.saleWeightSorf = '';
			_td.suitSex = '';
			_td.suitGroup = '';
			_td.season = '';
			_td.priceMin = '';
			_td.priceMax = '';
		} else if (_name == 'saleWeightSorf') {
			_td.salePriceSorf = '';
			_td.saleWeightSorf = _td.saleWeightSorf == 'asc' ? 'desc' : 'asc';
		} else if (_name == 'salePriceSorf') {
			_td.saleWeightSorf = '';
			_td.salePriceSorf = _td.salePriceSorf == 'asc' ? 'desc' : 'asc';
		}

		_ts.post(false, _this);
	},

	// 筛选
	filter: function (_this) {
		var _ts = this,
			_td = _ts.data,
			$ts = $(_this);

		if (!$ts.hasClass('active')) {
			$ts.addClass('active');
			popupShow(mall_list_pick);

			if ($ts.hasClass('has-data')) return;

			new ajax(getApi.getShopMallProductScreeningConditions, {
				data: {
					productTypeId: _td.productTypeId,
					searchName: _td.searchName,
					brandId: _td.brandId
				},
				success: function (e) {
					var _list = e.returnData;

					if (_td.productTypeId) {
						_list.productTypeMap.val = _td.categoryName;

						for (var i = 0; i < _list.productTypeMap.categoryList.length; i++) {
							if (_list.productTypeMap.categoryList[i].productTypeId == _td.productTypeId) {
								_list.productTypeMap.categoryList[i].productTypeName != '全部品类' && (_list.productTypeMap.link = _td.productTypeId);
								!_td.categoryName && (_list.productTypeMap.val = _list.productTypeMap.categoryList[i].productTypeName);
							}
						}

						(_list.productTypeMap.val && _list.productTypeMap.parentName) && (_list.productTypeMap.val = _list.productTypeMap.parentName + '-' + _list.productTypeMap.val);
					}

					if (_td.brandId) {
						_list.brandMap.val = _td.brandName;

						for (var i = 0; i < _list.brandMap.productList.length; i++) {
							if (_list.brandMap.productList[i].value == _td.brandId) {
								_list.brandMap.link = _td.brandId;
								_list.brandMap.val = _list.brandMap.productList[i].name;
							}
						}
					}

					_td.pick = _list;
					$ts.addClass('has-data');
					mall_list_pick.find('.popup-right').html(template('mall_pick_tpl', _list));
					category_ipt = mall_list_pick.find('.category h3 input');
					brand_ipt = mall_list_pick.find('.brand h3 input');
				}
			});
		}
	},

	// 单选
	radio: function (_this, _name, _val) {
		var _ts = this,
			_td = _ts.data,
			$ts = $(_this),
			_val = $ts.val() || _val || '',
			_proid = '';

		if (_name == 'categoryClear') {
			mall_list_pick.find('.category .con-1 input').removeClass('on');
			mall_list_category.removeClass('has-data');
			_ts.radio(_this, 'categoryList');
			closeSelf(_this);
		}

		if (_name == 'categoryMore') {
			if (!$ts.hasClass('on')) {
				mall_list_pick.find('.category .con-1 input').removeClass('on').eq($ts.index()).not('.more').addClass('on');
				$ts.parent().siblings('h4').removeClass('on');
				_ts.radio(_this, 'categoryList', _val);
				closeSelf(_this);
			}
			return;
		}

		if ($ts.hasClass('more')) {
			popupShow(mall_list_category);

			var _idx = mall_list_pick.find('.category .con-1 input.on').index();

			if (mall_list_category.hasClass('has-data')) {
				_idx >= 0 && mall_list_category.find('.row-2 a').eq(_idx).addClass('on').siblings().removeClass('on').parent().siblings('h4').removeClass('on');;
				return;
			}

			new ajax(getApi.getCategoryList, {
				data: {
					productTypeId: _proid
				},
				success: function (e) {
					var _list = e.returnData;
					mall_list_category.addClass('has-data').find('.popup-right').html(template('mall_category_tpl', _list));
					_idx >= 0 && mall_list_category.find('.row-2 a').eq(_idx).addClass('on').siblings().removeClass('on').parent().siblings('h4').removeClass('on');;
				}
			});

			return;
		}

		if ($ts.hasClass('on')) {
			_val = '';
			
			$ts.removeClass('on');

			if (_name == 'categoryList') {
				category_ipt.removeAttr('data-id');
				mall_list_category.find('.row-2 a').removeClass('on').parent().siblings('h4').addClass('on');
			}
		} else {
			_proid = $ts.data('id');
			$ts.addClass('on').siblings().removeClass('on');

			if (_name == 'categoryList') {
				category_ipt.attr('data-id', _proid);
			}
		}

		if (_name == 'categoryList') {
			new ajax(getApi.getShopMallProductScreeningConditions, {
				data: {
					productTypeId: _proid,
					searchName: _td.searchName,
					brandId: ''
				},
				success: function (e) {
					var _list = e.returnData,
						_txt = category_ipt.data('name');
						
					category_ipt.val(_val ? (_txt ? (_txt + '-' + _val) : _val) : '').closest('.col-1').siblings('.brand').html(template('mall_pick_tpl_brand', _list));
					brand_ipt = mall_list_pick.find('.brand h3 input');
				}
			});
		}
	},

	// 多选
	checkbox: function (_this, _val) {
		var $ts = $(_this),
			_ts = this,
			_td = _ts.data;

		if ($ts.hasClass('more')) {
			popupShow(mall_list_brand);

			var _id = $ts.closest('.col-1').find('h3 input').attr('data-id').toString();

			if (mall_list_brand.hasClass('has-data')) {
				var _ul = mall_list_brand.find('.row-2 ul');
				_ul.find('a').removeClass('on');

				if (_id) {
					_id = _id.split(',');

					for (var i = 0; i < _id.length; i++) {
						_ul.find('a[data-id=' + _id[i] + ']').addClass('on');
					}
				}

				return;
			}

			new ajax(getApi.getShopMallProductBrandList, {
				data: {
					productTypeId: _td.productTypeId,
					searchName: _td.searchName,
					brandId: ''
				},
				success: function (e) {
					var _list = e.returnData;

					_list.letterList = [];

					for (var i = 0; i < _list.productList.length; i++) {
						_list.letterList.push(_list.productList[i].letterIndex);
					}

					_list.letterList = arrdew(_list.letterList);
					_td.brand = _list;
					mall_list_brand.addClass('has-data').find('.popup-right').html(template('mall_brand_tpl', _list));

					var _ul = mall_list_brand.find('.row-2 ul');

					for (var i = 0; i < _list.letterList.length; i++) {
						_ul.find('li[data-letter=' + _list.letterList[i] + ']').eq(0).prepend('<p class="flex ac" data-index="' + _list.letterList[i] + '">' + _list.letterList[i] + '</p>');
					}

					_ul.find('a').removeClass('on');

					if (_id) {
						_id = _id.split(',');

						for (var i = 0; i < _id.length; i++) {
							_ul.find('a[data-id=' + _id[i] + ']').addClass('on');
						}
					}
				}
			});

			return;
		}

		$ts.hasClass('on') ? $ts.removeClass('on') : $ts.addClass('on');

		if (_val) {
			return;
		} else {
			_ts.sureBrand(_this);
		}
	},

	// 确定
	sure: function () {
		var _ts = this,
			_td = _ts.data,
			_row = mall_list_pick.find('.popup-right .row-1');

		_td.productTypeId = _row.find('.category h3 input').attr('data-id') || '';
		_td.brandId = _row.find('.brand h3 input').attr('data-id') || '';

		_td.suitSex = _row.find('.sex .con-1 .on').attr('data-id') || '';
		_td.suitGroup = _row.find('.group .con-1 .on').attr('data-id') || '';
		_td.season = _row.find('.season .con-1 .on').attr('data-id') || '';
		_td.priceMax = _row.find('.price input[name=priceMax]').val() || '';
		_td.priceMin = _row.find('.price input[name=priceMin]').val() || '';

		if (Number(_td.priceMax) < Number(_td.priceMin)) {
			_row.find('.price input[name=priceMax]').val('');
			_row.find('.price input[name=priceMin]').val('');
			delayTip('最高价不能小于最低价，请重新输入~');
			return;
		}

		_td.saleWeightSorf = '';
		_td.salePriceSorf = '';
		mall_list_tag.find('a').removeClass('active').removeClass('asc').removeClass('desc').eq(0).addClass('active');

		_ts.post(false, true, true);
		_ts.closePick();
	},

	// 重置
	reset: function () {
		var _ts = this,
			_td = _ts.data;

		mall_list_pick.find('.popup-right').html(template('mall_pick_tpl', _td.pick));
		category_ipt = mall_list_pick.find('.category h3 input');
		brand_ipt = mall_list_pick.find('.brand h3 input');
		mall_list_category.removeClass('has-data');
	},

	// 下拉
	drop: function (_this) {
		var $ts = $(_this),
			_con = $ts.parent().siblings();

		if ($ts.hasClass('active')) {
			$ts.removeClass('active');
			_con.addClass('narrow');
		} else {
			$ts.addClass('active');
			_con.removeClass('narrow');
		}
	},

	// 关闭筛选
	closePick: function (_this) {
		mall_list_tag.find('.filter').removeClass('active');
		closeSelf(_this);
	},

	// 品牌重置
	resetBrand: function () {
		var _ts = this,
			_td = _ts.data;

		mall_list_brand.find('.popup-right').html(template('mall_brand_tpl', _td.brand));

		var _ul = mall_list_brand.find('.row-2 ul');

		for (var i = 0; i < _td.brand.letterList.length; i++) {
			_ul.find('li[data-letter=' + _td.brand.letterList[i] + ']').eq(0).prepend('<p class="flex ac" data-index="' + _td.brand.letterList[i] + '">' + _td.brand.letterList[i] + '</p>');
		}
	},

	// 品牌确定
	sureBrand: function (_this) {
		var _id = '',
			_name = '',
			$ts = _this ? $(_this) : mall_list_brand.find('h4');

		if (_this) {
			_id = brand_ipt.attr('data-id');
			_name = brand_ipt.val();

			_id = _id == '' ? [] : _id.toString().split(',');
			_name = _name == '' ? [] : _name.split('、');

			if ($ts.hasClass('on')) {
				_id.push(_this.getAttribute('data-id'));
				_id = _id.join(',');

				_name.push(_this.value || this.innerText);
				_name = _name.join('、');
			} else {
				for (var i = 0; i < _id.length; i++) {
					if (_id[i] == _this.getAttribute('data-id')) {
						_id.splice(i, 1);
						_id = _id.join(',');
						_name.splice(i, 1);
						_name = _name.join('、');
						break;
					}
				}
			}
		} else {
			var _sib = $ts.parent().find('.on');

			_name = [];
			_id = [];

			$.each(_sib, function () {
				_name.push(this.value || this.innerText);
				_id.push(this.getAttribute('data-id'));
			});

			_name = _name.join('、');
			_id = _id.join(',');
		}

		brand_ipt.val(_name).attr('data-id', _id);

		if (!_this) {
			var _con = brand_ipt.closest('.col-1').find('.con-1');
			_con.find('input').removeClass('on');

			if (_id) {
				_id = _id.split(',');

				for (var i = 0; i < _id.length; i++) {
					_con.find('input[data-id=' + _id[i] + ']').addClass('on');
				}
			}

			closeSelf(mall_list_brand);
		}
	},

	// 字母定位
	letterTop: function (e) {
		var _col = mall_list_brand.find('.row-2 .col-1'),
			_p = _col.find('p[data-index=' + e + ']');

		_col.scrollTop(_p.position().top + _col.scrollTop());
	}
};

getTpl(function () {
	mallListApi.init();
});

function onShow() {
	traffic.setPv(mallListApi.data);
}