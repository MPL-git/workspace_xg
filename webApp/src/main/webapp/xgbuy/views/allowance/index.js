var page_allowance = $('.page[data-id="allowance"]:visible'),
	allowance_con = page_allowance.find('.content');


var allowanceApi = {
	data: {
		navId: 0,//商品导航ID
		navIndex: 0,//商品索引
		currentPage: 0, //商品列表页数
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'activityAreaId': _td.activityAreaId = _b; break;
				}
			}
		}

		_ts.detail()
		
	},
	detail: function () {
		var _ts = this,
			_td = _ts.data;
		new ajax(getApi.allowanceDetail, {
			data: {
				memberId: member.id,
			},
			success: function (res) {
				_td.activityAreaId = res.returnData.activityAreaId
				_td.navList = res.returnData.productTypeList

				allowance_con.html(template('allowance_list_tpl_detail', res.returnData))
				if (_td.navList && _td.navList.length) {
					_td.navId = _td.navList[0].id
					_ts.tabNav()
					_ts.productList()
					_ts.touch()
				}
			}
		})
	},
	touch: function () {
		var _ts = this,
			_td = _ts.data,
			allowance_product = page_allowance.find('.product'),
		    _l = 0,
			_w = 0;
		allowance_product.on('touchstart', function (e) {
			_l = e.changedTouches[0].pageX;
			_w = e.changedTouches[0].pageY;
		});
		allowance_product[0].addEventListener("touchend",function(e){
			var _x = e.changedTouches[0].pageX - _l,
			_y = e.changedTouches[0].pageY - _w;
			
			if(Math.abs(_x)>Math.abs(_y)){
				if(_x > 150 &&  _td.navIndex > 0){
					_td.navIndex--
					_ts.nav(_td.navList[_td.navIndex].id,_td.navIndex)
				}else if(_x < -150 && _td.navIndex < _td.navList.length - 1){
					_td.navIndex++
					_ts.nav(_td.navList[_td.navIndex].id,_td.navIndex)
					
				}

			}
			
		})
	},
	productList: function (_scroll, _this) {
		var _ts = this,
			_td = _ts.data,
			allowance_product = page_allowance.find('.product');
		_this && allowance_con.removeAttr('data-page')

		_td.activityAreaId != null && (new ajax(getApi.areaProductList, {
			data: {
				activityAreaId: _td.activityAreaId,
				productTypeId: _td.navId,
				currentPage: _td.currentPage
			},
			success: function (res) {
				var _len = res.returnData.productInfoList.length || 0;
				_td.currentPage = _td.currentPage + 1

				if (_scroll) {
					if (_len) {
						allowance_con.attr('data-page', _td.currentPage);
						allowance_product.append(template('allowance_list_tpl_product', res.returnData));
					} else {
						// delayTip('已经全部加载完毕');
					}
				} else {
					allowance_product.html(template('allowance_list_tpl_product', res.returnData));
					scrollHand.init(allowance_con, function () {
						_ts.productList(true);
					}, true);
				}

			}
		}))
	},
	tabNav: function () {
		var _ts = this,
			_td = _ts.data,
			allowance_nav = page_allowance.find('.tab-nav');
		allowance_nav.html(template('nav_tpl', _td));
		require(['ceiling'], function () {
			// var brand_decorate_scroll = $('.page:visible .scroll-decorate').eq(0);
			setTimeout(function () {
				allowance_nav.ceiling(allowance_con, $('.page:visible header').is(':visible') ? $('.page:visible header').height() : 0);
			}, 500)

		});
	},
	exchange: function (id) {
		new ajax(getApi.allowanceExchange, {
			data: {
				id: id
			},
			success: function (res) {
				$(".allowanceBalance").text('￥' + res.returnData.allowanceBalance)
				var _obj = {
					"linkType": 10,//津贴值变化
					"linkValue": {
					},
					"curturnVersion": 1,
					"extraMessage": "jsonString",
				}
				withApp(_obj)
			}
		})
	},
	//商品切换导航
	nav: function (id, index, domts) {
		var _ts = this,
			_td = _ts.data;
		_td.navId = id;
		_td.navIndex = index
		_td.currentPage = 0;
		_ts.tabNav();
		// $(".tab-nav").removeClass("active")
		// $(domts).addClass("active")
		_ts.productList(false, true);
	}

};

getTpl(function () {
	allowanceApi.init();
});

