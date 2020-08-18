var _vpage = $('.page:visible'),
	brandDecorate = {
		data: {

		}
	};

if (_vpage.hasClass('home')) {
	brandDecorate = homeApi;
} else if (_vpage.hasClass('salesman')) {
	brandDecorate = salesmanApi;
}
function postNest(fn) {
	var _infoId = salesmanApi.data.infoId;
		pageTitle = undefined // 指定标题


	require(['template'], function (template) {
		new ajax(getApi.getXgShopwnerEquityDetail, {
			data: {
				infoId: _infoId,
			},
			success: function (res) {
				var _list = res.returnData.decorateInfoMap;

				if (!_list) return;

				if (_list.decorateAreaList.length) {
					var box_list = $('<div class="box-list"></div>');

					$.each(_list.decorateAreaList, function (i) {
						$.each(this.decorateModuleList, function () {
							if (this.moduleType == 1) {
								this.decorateModulePic = imgCache(this.decorateModulePic, 'nest_home');
								var box_svg = $('<div class="box-svg w75"><img src="' + this.decorateModulePic + '"></div>');

								$.each(this.moduleMapList, function () {
									var _map = this.corrds.split(',');

									box_svg.append($('<a class="box-frame" onclick="getMapUrl(this)"'
									+ 'data-ad="' + this.adCatalog + '"'
									+ 'data-name="' + this.adCatalogName + '"'
									+ 'data-url="' + this.linkUrl + '"'
									+ 'data-type="' + this.linkType + '"'
									+ 'data-link="' + this.linkValue + '"'
									+ 'data-productid="' + this.linkValue + '"'
									+ 'data-activityid="' + this.linkValue + '"'
									+ 'data-activityareaid=""'
									+ 'data-msgtype="' + this.msgType + '"'
									+ 'style="left: ' + Math.min(_map[0], _map[2]) + '%; top: ' + Math.min(_map[1], _map[3]) + '%; width: ' + Math.abs(_map[2] - _map[0]) + '%; height: ' + Math.abs(_map[3] - _map[1]) + '%;"'
									+ '></a>'));
								});

								box_list.append(box_svg);
							} else if (this.moduleType == 2) {
								this.conmonProduct = this.productBlockList;
								this.conmonProduct = imgCachePic(this.conmonProduct, 'salesman_decorate_product');
								box_list.append(template('salesman_decorate_product_tpl', this));
							} else if (this.moduleType == 3) {
								this.conmonBrand = this.activityBlockList;
								this.conmonBrand = imgCacheConmonBrandAreaEntryPic(this.conmonBrand, 'salesman_decorate_brand');
								box_list.append(template('salesman_decorate_brand_tpl', this));
							} else if (this.moduleType == 13) {
								box_list.append(template('salesman_decorate_video_tpl', this));
							} else if (this.moduleType == 14){
								this.decorateInfoName = _list.decorateInfoName
								box_list.append(template('graphic_text_tpl', this));
							}
						});
					});

					fn(box_list, _infoId);
				}
			}
		});
	});
}

function imgCacheConmonBrandAreaEntryPic(_list, _cac) {
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
			var _pic = _list[i].areaEntryPic;

			if (_pic) {
				for (var j = 0, _name = _pic.match(reg_cache_img)[0]; j < cache.list.name.length; j++) {
					(_name == cache.list.name[j]) && (_list[i].areaEntryPic = cache.list.path[j]);
				}
			}
		}
	}

	for (var i = 0; i < _list.length; i++) {
		var _pic = _list[i].areaEntryPic;

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