var page_home = $('.page[data-id="home"]:visible'),
	home_scroll = page_home.find('.scroll-more'),
	home_swiper = page_home.find('.banner'),
	home_marketing = page_home.find('.marketing'),
	home_theme = page_home.find('.theme'),
	home_brand = page_home.find('.brand'),
	home_seckill = page_home.find('.seckill'),
	home_nav = page_home.find('.nav'),
	home_brand_con = '';

var homeApi = {
	data: {
		planType: 1,
		pag: 10
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '34'
		});

		_ts.swiper();
		_ts.decorate();
		_ts.brand();

		shareHand.update();

		dropDown(page_home);
	},

	// 轮播图
	swiper: function () {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.adInfo, {
			data: {
				memberId: member.id,
				type: 1,
				catalog: 1,
				position: 1
			},
			success: function (e) {
				var _list = e.returnData;

				e.returnData = imgCachePic(_list, 'home_swiper');
				home_swiper.html(template('home_swiper_tpl', e));
				_list.length > 1 && initSwiper();

				traffic.delayRequest(function () {
					for (var i = 0; i < _list.length; i++) {
						traffic.setDtl(_td, {
							type: 4,
							id: _list[i].id,
							pos: i
						});
					}
				});
			}
		});
	},

	// 营销市场&主题款
	decorate: function () {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.homeCategory, {
			data: {
				memberId: member.id
			},
			success: function (e) {
				var _list = e.returnData,
					_marketing = _list.marketing,
					_theme = _list.themePavilions;

				_td.planType = _list.planType;

				if (/infoId/g.test(_marketing)) {
					_marketing = _marketing.split('?infoId=')[1];
					_theme = _theme.split('?infoId=')[1];
				}

				_marketing && $.ajax({
					method: 'get',
					url: lead_url + 'views/activity/nest/home/home-marketing.html',
					datatype: 'html',
					success: function (data) {
						setStorage('nest_home_marketing', _marketing);
						home_marketing.html(data);
					}
				});

				_theme && $.ajax({
					method: 'get',
					url: lead_url + 'views/activity/nest/home/home-theme.html',
					datatype: 'html',
					success: function (data) {
						setStorage('nest_home_theme', _theme);
						home_theme.html(data);
					}
				});

				if (_td.planType != 3) {
					home_nav.html(template('home_nav_tpl', _list));

					if (_td.planType == 2) {
						home_nav.removeClass('nav-hide');
						// page_home.css('paddingTop', '.8rem');
					}
				} else {
					home_nav.addClass('nav-hide');
				}

				scrollFix();
			}
		});
	},

	// 精选特卖
	brand: function (_scroll) {
		var _ts = this,
			_td = _ts.data,
			_cu = home_scroll.attr('data-page') || 0;

		new ajax(getApi.activityCustom, {
			data: {
				memberId: member.id,
				productTypeId: '',
				adCatalog: 1,
				currentPage: _cu,
				pageSize: _td.pag
			},
			success: function (e) {
				var _pag = e.returnSize,
					_list = e.returnData,
					_len = _list.activityList.length;

				_list.activityList = imgCacheEntryPic(_list.activityList, 'home_tpl_brand_cache');

				if (_scroll) {
					if (_len) {
						var _cur = home_scroll.attr('data-page');
						_list.page = _cur * _td.pag;
						_cur++;
						home_scroll.attr('data-page', _cur);
						home_brand_con.append(template('home_tpl_brand_list', _list));
					} else if (_cu > 1) {
						delayTip('已经全部加载完毕');
					}
				} else {
					_list.page = 0;
					home_brand.html(template('home_tpl_brand', _list));
					home_brand_con = home_brand.find('.con-brand');
					_pag && (_td.pag = _pag);

					setTimeout(function () {
						scrollHand.init(home_scroll, function () {
							_len == _td.pag && _ts.brand(true);
						}, true, function (_t) {
							if (_td.planType == 1) {
								_t >= win_h / 2 ? home_nav.removeClass('nav-hide') : home_nav.addClass('nav-hide');
							}
						});
					}, 500);

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
	},

	randCustom: function (e) {
		var _name = $(e).data('name'),
			_id = $(e).data('id'),
			_type = $(e).data('type'),
			_productTypeId = $(e).data('productTypeId');
		this.navAll(false)
		getUrl('activity/templet/brand_group.html?productTypeId=' + _productTypeId + '&id=' + _id + '&type=' + _type, 'self');

	},
	
	navAll: function(b){
		if(b){
			$("#navAll").show()
		}else{
			$("#navAll").hide()
		}
	}
};

getTpl(function () {
	homeApi.init();
});

function onShow() {
	traffic.setPv(homeApi.data);
}