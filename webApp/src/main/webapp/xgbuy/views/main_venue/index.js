var main_venue = $('.page[data-id="main_venue"]:visible'),
	main_venue_con = main_venue.find('.content');

var main_venueApi = {
	data: {
		size: 10
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		// traffic.setPv(_td, {
		// 	pageType: '39'
		// });
		_ts.getMainVenueInfo();
	},
	getUrl: function (el, _refresh) {
		var _linkValue = $(el).attr("data-linkValue"),
			_linkType = $(el).attr("data-linkType"),
			_title = 	$(el).attr("data-title"),
			_templetType = 	$(el).attr("data-templetType"),
			_type = 	$(el).attr("data-type"),
			_page = 'main_venue' + _linkType + _linkValue,
			page_section = $("#section1");
			page_section.attr({"data-page-active": _page,'data-page-type': _linkType})

		_refresh && (_refresh == 'self' ? $('#section1 .page[data-page="' + _page + '"]').remove() : $('#section1 .page').remove());
		// _title && setTitle(_title);
		_title && $('#mainTop header p').html(_title);
		/*底部按钮选中*/
		var siblingImgs = $(el).parent().find("a>img")
		for(var i=0;i<siblingImgs.length;i++){
			$(siblingImgs[i]).attr("src",$(siblingImgs[i]).attr("data-defaultModulePic"))
		}
		var elImg = $(el).find("img")
		elImg.attr("src",elImg.attr("data-selectedModulePic"))
		setStorage("activityAreaModuleId",$(el).attr("data-activityAreaModuleId"))
		getH5PageType()
		
		var decoratefield = ''
		var url=''
		
		/*类型判断*/
		switch(_linkType){
			case '1':
				decoratefield = 'areaid'
				// new ajax(getApi.areaTempletByAreaId, {
				// 	data: {
				// 		activityAreaId: _linkValue
				// 	},
				// 	async: false,
				// 	success: function (e) {
				// 		var _u = e.returnData.areaUrl.split("?redirect_url=")[1];
				// 		console.log(_u)
				// 		_u && (url = _u)
				// 	}
				// });
				url = "activity/templet/brand_decorate.html?"+ decoratefield +"=" + _linkValue

				// switch(_templetType){
				// 	case '1':
				// 		switch(_type){
				// 			case '1':
				// 				console.log('品牌模板')
				// 				break
				// 			case '2':
				// 				console.log('单品模板')
				// 				break
				// 		}
				// 	break;
				// 	case '3':
				// 	break
				// }
				break;
			case '4':
				decoratefield = 'pageid'
				url = "activity/templet/brand_decorate.html?"+ decoratefield +"=" + _linkValue
				break;
			case '35':
				decoratefield = ''
				main_venueApi.getUrl($("#footer1 a")[0])
				main_venueApi.getMyyouhuijuan()
				return;
			case '36':
				decoratefield = ''
				main_venueApi.getUrl($("#footer1 a")[0])
				main_venueApi.getAllowance()
				return;
		}
		
		
		var page_id = $('#section1 .page[data-page="' + _page + '"]')
		if (page_id[0]) {
			page_id.siblings('.page').hide().end().show();
		} else {
			setStorage('decorate_nest', '1')
			setStorage('decorate_'+ decoratefield, _linkValue)
			
			$.ajax({
				method: 'get',
				url: url,
				datatype: 'html',
				cache: false,
				success: function (e) {
					page_id.remove();
					page_section.find('.page').hide();
					page_section.append(e);
					page_id = $('.page[data-id="activity_templet_brand_decorate"]:visible');
					page_id.attr('data-page', _page);
					main_venueApi.pageActive();
					//下拉刷新
					console.log(page_id.find(".box-list .touchfix").length)
					setTimeout(function(){
						if(!page_id.find(".box-list .touchfix").length){
							page_id.find(".box-list").addClass("touchfix")
						}
						dropDown(page_id,function(){ 
							page_id.remove();
							main_venueApi.getUrl(el)
						});
					},1000)
					
					// 微信加载空白
					removeStorage('weixin_load_hash');
				},
				error: function (e) {
					// 微信加载空白
					if (!getStorage('weixin_load_hash')) {
						setStorage('weixin_load_hash', '1');
						location.reload();
					}
					pop_state = true;
					loadHide();
				},
				complete: function () {
					loadHide();
				}
			});
		}
	},
	pageActive: function(){
		$("#section1 header").remove();
		var h = member.sys == 'web'? $(".main_venue header").height() : 0 //app判断
		$("#section1").css("height",($(".main_venue").height() - $("#footer1").height() - h)+ 'px')
		
		
	},
	getMainVenueInfo: function (_scroll) {
		var _ts = this,
			_td = _ts.data;
		var urlParams = real_url.split('?')[real_url.split('?').length-1].split('&');
		var meberId = urlParams.length > 1 ? urlParams.indexOf('memberId=') : 0;
		if (isxgbuy || meberId > -1) {
			$('#mainTop').append(template('mainVenueHeader'));
		} else if (!isWeiXin) {
			$('#mainTop').append(template('mainVenueDownload'))
		}
		new ajax(getApi.getMainVenueInfo, {
			data: {},
			success: function (res) {
				if(res.returnData.moduleList.length <= 0){
					$("#section1").html(template('tpl_empty'))
					$("#section1 .page-empty").css("height",($(".main_venue ").height() - $("#footer1").height())+ 'px')
					return
				}
				$("#footer1").html(template('footer_tpl', res.returnData))
				$("#footer1").css("background","url('"+ res.returnData.bottomBgPic +"')")
				main_venueApi.getUrl($("#footer1 a[data-linktype="+ res.returnData.linkType +"][data-linkValue="+ res.returnData.linkValue +"]"))
			}
		})
	},
	getMyyouhuijuan: function () {
		getMember(function () {
			if (member.sys == 'android') {
				intentCoupon();
			} else if (member.sys == 'ios') {
				checkAppVer(50, function () {
					callIntentTrailer('intentTrailerListNative', 'intentCoupon');
				});
			} else {
				getUrl('coupon/index');
			}
		});
	},
	getAllowance: function(){
		getUrl('allowance/index');
	}
};

getTpl(function () {
	main_venueApi.init();
});
