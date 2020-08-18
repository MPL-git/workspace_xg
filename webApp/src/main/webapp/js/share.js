var _token = 'PRMloYfGGSeEPWOtTlMFo5C1xxP-rhxgUTYCncemTDaBVzQD2';

// 兼容timeout, 提高性能
Date.now || (Date.now = function() {
	return (new Date).getTime();
});
(function() {
	if (!window.requestAnimationFrame) {
		window.requestAnimationFrame = window.webkitRequestAnimationFrame;
		window.cancelAnimationFrame = window.webkitCancelAnimationFrame || window.webkitCancelRequestAnimationFrame;
	}

	if (/iP(ad|hone|od).*OS 6/.test(window.navigator.userAgent) || !window.requestAnimationFrame || !window.cancelAnimationFrame) {
		var f = 0;
		window.requestAnimationFrame = function(a) {
			var b = Date.now(),
				c = Math.max(f + 16, b);

			return setTimeout(function() {
				a(f = c);
			}, c - b);
		};
		window.cancelAnimationFrame = clearTimeout;
	}
})();

var post_id = location.search.replace('?id=', '').split('&') || 16;

// 请求数据
$.post('api/n/getProductInfoListByShare', JSON.stringify({
	reqData: {
		memberId: '',
		id: post_id[0],
		activityAreaId: ''
	},
	token: _token,
	timeStamp: (new Date).getTime(),
	nonceStr: 'OV5QOVX21A0446K8',
	signature: '7f8c4ad7f6d349cf28879670b9fe79b9'
}), function (e) {
	if (e.returnCode === "0000") {
		var a = e.returnData,
			b = a.dataMap,
			c = a.standardMap;

		if (!b) {
			return;
		}

		// 价格
		$('#b_salePrice').html('￥' + b.salePrice);
		$('#b_tagPrice').html('￥' + b.tagPrice);

		// 名称
		$('#b_name').html(b.name);

		// 打折
		var bb_discount = (10 * b.discount).toFixed(1),
			bs_discount = parseInt(bb_discount);

		$('#b_discount').removeClass('hide').children().html((bb_discount > bs_discount ? bb_discount : bs_discount) + '折');
		
		
		//<HTML><BODY><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no'><video src='https://vtest.xgbuy.cc/201908/video/2/1567147237539_148.mp4'></video><img src=http://ptest.xgbuy.cc/201908/product/2/1567147326319_3191_70Q.jpg></BODY></HTML>
		// 详细描述
		a.htmlDesc = a.htmlDesc.replace("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no'>", '<p>');
		a.htmlDesc = a.htmlDesc.replace(/br/g, 'p');
		a.htmlDesc = a.htmlDesc.replace(/	/g, '<p>');
		a.htmlDesc = a.htmlDesc.replace(/: /g, ':');
		a.htmlDesc = a.htmlDesc.replace(/:/g, ': <span>');
		a.htmlDesc = a.htmlDesc.replace(/：/g, ': <span>');
		a.htmlDesc = a.htmlDesc.replace(/http: <span>/g, 'http:');
		a.htmlDesc = a.htmlDesc.replace(/https: <span>/g, 'https:');
		a.htmlDesc = a.htmlDesc.replace(/width: <span>/g, 'width:');
		$('#a_htmlDesc').html(a.htmlDesc);
		$('#a_htmlDesc p:empty').remove();
		$('#a_htmlDesc img').parent().not('div').css({
			'width': '100%',
			'padding': 0
		});

		// 幻灯片
		for (var i = 0; i < a.productVideos.length; i++) {
			$('.swiper-wrapper').append('<li class="swiper-slide"><video src="' + a.productVideos[i].videoUrl + '" controls="controls" style="width:100%;height:100%"></video></li>');
		}
		for (var i = 0; i < a.picMapList.length; i++) {
			$('.swiper-wrapper').append('<li class="swiper-slide"><img src="' + a.picMapList[i].pic + '"></li>');
		}

		new Swiper('.swiper-container', {
			loop: true,
			autoplay: 5e3,
			pagination: {
				el: '.pagination'
			}
		});

		// 尺寸
		(function () {
			var info_load = false, // 请求状态
				info_index_arr = new Array(c.length), // 存储选中的索引
				info_propValIds_arr = new Array(c.length), // 存储选中的规格
				info_propIds = [];// 商品的所有大规格id

			for (var i = 0; i < c.length; i++) {
				var str = '';

				for (var j = 0; j < c[i].propValueMapList.length; j++) {
					var obj = c[i].propValueMapList[j];
					str += '<li data-propvalid="' + obj.propValId + '" data-status="' + obj.status + '">' + obj.propValue + '</li>';
				}
				$('#share_info').append('<div data-id="' + c[i].propId + '"><h3>' + c[i].propName + '</h3><ul class="clearfix">' + str + '</ul></div>');
			}

			var info_li = $('#share_info li'),
				info_div = $('#share_info>div');

			$.each(info_div, function () {
				info_propIds.push($(this).data('id'));
			});

			info_propIds = info_propIds.join(',');
			
			info_li.tap(function () {
				if (info_load) {
					return;
				}
				info_load = true;
				var that = $(this),
					info_propValIds = [], // 选中的规格集合
					info_index = that.closest('div').index(), // 当前模块索引
					info_id = that.closest('div').data('id'); // 当前模块id

				if (that.hasClass('no')) {
					info_load = false;
					return;
				}

				if (that.hasClass('on')) {
					info_index_arr[info_index] = undefined;
					info_propValIds_arr[info_index] = undefined;
					info_index = undefined;
				} else {
					info_index_arr[info_index] = that.index();

					for (var i = 0; i < info_index_arr.length; i++) {
						info_propValIds_arr[i] = info_index_arr[i] !== undefined ? info_div.eq(i).find('li').eq(info_index_arr[i]).data('propvalid') : undefined;
					}
				}

				for (var i = 0; i < info_propValIds_arr.length; i++) {
					info_propValIds_arr[i] !== undefined && info_propValIds.push(info_propValIds_arr[i]);
				}
				info_propValIds = info_propValIds.join(',');

				infoActive();
				infoPost(info_index, info_propValIds);
			});

			// 选中
			function infoActive() {
				for (var i = 0; i < info_index_arr.length; i++) {
					var j = info_div.eq(i).find('li');
					j.removeClass('on');

					info_index_arr[i] !== undefined && j.eq(info_index_arr[i]).addClass('on');
				}
			}

			// 请求
			function infoPost(info_index, info_propValIds) {
				$.post('api/n/getProductItemsById', JSON.stringify({
					reqData: {
						id: b.id,
						propIds: info_propIds,
						propValIds: info_propValIds
					},
					token: _token,
					timeStamp: (new Date).getTime(),
					nonceStr: 'OV5QOVX21A0446K8',
					signature: '7f8c4ad7f6d349cf28879670b9fe79b9'
				}), function (data) {
					infoDiff(data.returnData.standardMapList, info_index);
				});
			}

			// 非选中
			function infoDiff(data_list, info_index) {
				if (info_index !== undefined) {
					$.each(info_div.eq(info_index).find('li'), function () {
						!$(this).hasClass('no') && $(this).addClass('yes');
					});
				}

				info_li.removeClass('no');

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
					$(this).hasClass('yes') ? $(this).removeClass('yes') : $(this).addClass('no');
				});

				info_load = false;
			}
		})();

		// 48小时发货
		for (var i = 0; i < a.serviceList.length; i++) {
			$('#a_serviceList').append('<span>' + a.serviceList[i] + '</span>')
		}

		// 去购买
		if ($('.buy').length) {
			requestAnimationFrame(function () {
				// $('.buy').append('<a href="http://xgbuy.cc/app/downloadIndex.shtml" class="buy">去购买</a>');
				var _u = location.href.split('webApp')[0] + 'webApp/xgbuy/views/?redirect_url=my/download/index.html?pageName=productDetailPage&productId=' + post_id;
				$('.buy').append('<a href="' + _u + '" class="buy">去购买</a>');
				$('body').css('padding-bottom', '1rem');
			});
		}
	} else {
		alert(e);
	}
});