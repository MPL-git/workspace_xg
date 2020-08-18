var goods_detail = $('.page[data-id="activity_newsign_goods_detail"]:visible'),
	goods_detail_con = goods_detail.find('.content');

var goodsApi = {
	data: {

	},

	init: function () {
		var _ts = this;

		$(function () {
			if (search_url) {
				for (var i = 0; i < search_url.length; i++) {
					var _a = search_url[i].split('='),
						_b = _a[1];

					switch (_a[0]) {
						case 'id': _ts.data.id = _b; break;
						case 'code': _ts.data.code = _b; break;
					}
				}
			}

			requestAppMember(function () {
				_ts.post();
			});
		});
	},

	post: function () {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getCommonProductBaseInfo, {
			data: {
				memberId: member.id,
				id: _ts.data.id
			},
			success: function (res) {
				var _list = res.returnData;

				goods_detail_con.html(template('checkin_newsign_goods_detail_tpl', _list));
				initSwiper();
				_ts.infoInit(goods_detail.find('.info'), _list);
				_ts.goodsDetailMore();
			}
		});
	},

	goodsDetailMore: function() {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.productDetail, {
			data: {
				memberId: member.id,
				id: _ts.data.id
			},
			success: function (res) {
				var d = res.returnData,
					_detail = goods_detail.find('.detail');

				d = d.replace("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no'>", '<p>');
				d = d.replace(/br/g, 'p');
				d = d.replace(/	/g, '<p>');
				d = d.replace(/: /g, ':');
				d = d.replace(/:/g, ': <span>');
				d = d.replace(/：/g, ': <span>');
				d = d.replace(/http: <span>/g, 'http:');

				_detail.html(d);
				_detail.find('p:empty').remove();

				var _m = goods_detail_con.find('.scroll-more'),
					_b = _detail.find('img');

				$.each(_b, function () {
					this.src = imgCache(this.src, 'goods_detail_more');
				});

				_b.length && _b.parent().not('div').css({
					'width': '100%',
					'padding': 0
				});
			}
		});
	},

	// 选择规格
	infoInit: function(info, _list) {
		var _ts = this,
			_td = _ts.data,
			info_load = true,
			info_length = _list.standardMapList.length,
			info_index_arr = new Array(info_length), // 存储选中的索引
			info_propValIds_arr = new Array(info_length); // 存储选中的规格

		$.each(info, function () {
			var info_li = $(this).find('li'),
				info_div = $(this).find('.info-list'),
				info_propIds = []; // 商品的所有大规格id

			$.each(info_div, function () {
				info_propIds.push($(this).data('id'));
			});

			info_propIds = info_propIds.join(',');

			info_li.tap(function () {
				if (!info_load) {
					return;
				}
				info_load = false;

				var info_propValIds = [], // 选中的规格集合
					info_index = $(this).closest('div').index(); // 当前模块索引

				if ($(this).hasClass('no')) {
					info_load = true;
					return;
				}

				if ($(this).hasClass('on')) {
					info_index_arr[info_index] = undefined;
					info_propValIds_arr[info_index] = undefined;
					info_index = undefined;
				} else {
					info_index_arr[info_index] = $(this).index();

					for (var i = 0; i < info_index_arr.length; i++) {
						info_propValIds_arr[i] = info_index_arr[i] !== undefined ? info_div.eq(i).find('li').eq(info_index_arr[i]).data('propvalid') : undefined;
					}
				}

				for (var i = 0; i < info_propValIds_arr.length; i++) {
					info_propValIds_arr[i] !== undefined && info_propValIds.push(info_propValIds_arr[i]);
				}
				info_propValIds = info_propValIds.join(',');

				$.each(info, function () {
					for (var i = 0; i < info_index_arr.length; i++) {
						var j = $(this).find('.info-list').eq(i).find('li');
						j.removeClass('on');

						info_index_arr[i] !== undefined && j.eq(info_index_arr[i]).addClass('on');
					}
				});

				new ajax(getApi.productItems, {
					data: {
						id: _ts.data.id,
						propIds: info_propIds,
						propValIds: info_propValIds
					},
					success: function (res) {
						var _list = res.returnData,
							data_list = _list.standardMapList,
							_ddp = $('.popup-add-cart dd p'),
							_ddps = goods_detail_con.find('.con.info'),
							_ons = []; // 存储所有选中规格

						if (info_index !== undefined) {
							$.each(info_div.eq(info_index).find('li'), function () {
								!$(this).hasClass('no') && $(this).addClass('yes');
							});
						}

						info_li.removeClass('no');
						_ddps.find('.info-color-choice').html('');

						for (var i = 0; i < data_list.length; i++) {
							if (i !== info_index) {
								var info_div_lis = info_div.eq(i).find('li');
								var data_list_lis = data_list[i].propValueMapList;

								$.each(info_div_lis, function () {
									for (var j = 0; j < data_list_lis.length; j++) {
										$(this).data('propvalid') === data_list_lis[j].propValId && $(this).addClass('yes');
									}
								});
							}
						}

						$.each(info_li, function () {
							if ($(this).hasClass('on')) {
								_ons.push($(this).text());
								$(this).parent().siblings('h3').find('.info-color-choice').html($(this).text());
							}
							$(this).hasClass('yes') ? $(this).removeClass('yes') : $(this).addClass('no');
						});

						if (_ons.length) {
							_ons = _ons.join('、');
							_ddp.find('span').html(_ons);
							_ddps.find('.info-lists').html('已选择 ' + _ons).removeClass('hide').siblings('div').addClass('hide');
							_ddp.removeClass('hide');
						} else {
							_ddp.addClass('hide');
							_ddps.find('.info-lists').addClass('hide').siblings('div').removeClass('hide');
						}

						_list.skuPic_S = imgCache(_list.skuPic_S, 'goods_detail_skus');
						_list.skuPic = imgCache(_list.skuPic, 'goods_detail_sku');

						$('.popup-add-cart img').attr('src', _list.skuPic_S);
						$('.popup-add-pic img').attr('src', _list.skuPic);

						goods_detail_con.find('.').text(_list.salePrice);
						if ($('.goods-detail2 .info-color ul').find('.on').length) {
							$('.popup-info-color img').attr('src', _list.skuPic);
							$('.popup-info-color').show();
						} else {
							$('.popup-info-color').hide();
						}
					},
					complete: function () {
						info_load = true;
					}
				});
			});
		});
	}
};

getTpl(function () {
	goodsApi.init();
});