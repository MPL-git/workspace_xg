var brand_custom_brand = $('.page[data-id="activity_templet_brand_custom_brand"]:visible'),
	brand_custom_brand_scroll = brand_custom_brand.find('.scroll-more'),
	brand_custom_brand_img = brand_custom_brand.find('.swiper-container img'),
	brand_custom_brand_con = brand_custom_brand.find('.con-brand');

var brandCustomBrandApi = {
	data: {
		tit: getStorage('brand_custom_brand'),
		size: 10
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '30'
		});

		_td.tit && (_td.tit = JSON.parse(_td.tit));
		_td.id = _td.tit.id;

		setTitle(_td.tit.name);
		_ts.getPagingList();
	},

	getPagingList: function (_scroll) {
		var _ts = this,
			_td = _ts.data,
			_tp = _ts.append;

		new ajax(getApi.getActivityBrandGroupActivityList, {
			data: {
				activityBrandGroupId: _td.id,
				currentPage: brand_custom_brand_scroll.attr('data-page') || 0
			},
			success: function (e) {
				var _list = e.returnData,
					_size = e.returnSize,
					_activ = _list.activityList,
					_len = _activ.length;

				_list.activityList = imgCacheEntryPic(_list.activityList, 'brand_custom_brand_cache');

				if (_scroll) {
					if (_len) {
						var _cur = brand_custom_brand_scroll.attr('data-page');
						_list.page = _cur * _td.size;
						_cur++;
						brand_custom_brand_scroll.attr('data-page', _cur);
						brand_custom_brand_con.append(template('brand_custom_brand_tpl', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					if (_len) {
						brand_custom_brand_img.attr('src', _td.tit.pic);
					} else {
						brand_custom_brand_img.closest('.swiper-container').hide();
					}

					_list.page = 0;
					brand_custom_brand_con.html(template('brand_custom_brand_tpl', _list));

					_size && (_td.size = _size);

					_len == _td.size && scrollHand.init(brand_custom_brand_scroll, function () {
						_ts.getPagingList(true);
					}, true);

					scrollFix();
				}

				traffic.delayRequest(function () {
					for (var i = 0; i < _list.activityList.length; i++) {
						var _la = _list.activityList[i],
							_pg = _list.page + i;

						_la.source == 1 ? traffic.setDtl(_td, {
							type: 1,
							id: _la.activityAreaId,
							pos: _pg
						}) : traffic.setDtl(_td, {
							type: 2,
							id: _la.activityId,
							pos: _pg
						});
					}
				});
			}
		});
	}
};

getTpl(function () {
	brandCustomBrandApi.init();
});

function onShow() {
	traffic.setPv(brandCustomBrandApi.data);
}