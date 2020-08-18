    var activity_brand_group = $('.page[data-id="activity_templet_brand_group"]:visible'),
    activity_brand_group_special = activity_brand_group.find('.special'), //特卖
    activity_brand_group_scroll = activity_brand_group.find('.scroll-more'),
    activity_brand_group_decorate = activity_brand_group.find('.decorate'), // 装修
    activity_brand_group_swiper = activity_brand_group.find('.swiper'); //轮播图

var brandGroup = {
    data: {
        id: '',
        productTypeId: 0,
        type: 0,
        size: 10
    },
    init: function () {
        var _ts = this,
            _td = _ts.data;

        if (search_url) {
            for (var i = 0; i < search_url.length; i++) {
                var _a = search_url[i].split('='),
                    _b = _a[1];

                switch (_a[0]) {
                    case 'id': _td.id = _b; break;
                    case 'productTypeId': _td.productTypeId = _b; break;
                    case 'type': _td.type = _b; break;
                    case 'productType2Ids': _td.productType2Ids = _b; break;
                }
            }
        }
        if(!_td.productType2Ids){ // 品牌特卖 没有轮播和装修
            _ts.groupSwiper() //轮播图和装修
        }else{
            // activity_brand_group.find("header .ellipsis").text('品牌特卖');
        }
        _ts.special() //特卖

    },
    groupSwiper: function () {
        var _ts = this,
            _td = _ts.data;
        new ajax(getApi.getBrandGroupCategoryAds, {
            data: {
                brandTeamTypeId: _td.id
            },
            success: function (e) {
            console.log(e)
                var _list = e.returnData.adList,
                    _listDecorate = e.returnData.decorateInfoMap;
                    activity_brand_group.find("header .ellipsis").text(_listDecorate.decorateInfoName);
                    _list = imgCachePic(_list, 'brand_group_swiper');
                    _listDecorate = imgCachePic(_listDecorate, 'brand_group_decorate');

                activity_brand_group_swiper.html(template('group_swiper_tpl', e));
                _list.length > 1 && initSwiper();

                if (!_listDecorate) return;
                if (_listDecorate.decorateAreaList.length) {
                    var box_list = $('<div class="box-list"></div>');

                    $.each(_listDecorate.decorateAreaList, function (i) {
                        $.each(this.decorateModuleList, function () {
                            if (this.moduleType == 1 || this.moduleType == 9) {
                                console.log(this.decorateModulePic)
                                this.decorateModulePic = imgCache(this.decorateModulePic, 'nest_home');
                                var box_svg = $('<div class="box-svg w75"><img src="' + this.decorateModulePic + '"  onerror = "javascript:this.src = \'\'"></div>');

                                $.each(this.moduleMapList, function () {
                                    var _map = this.corrds.split(',');
                                    if (this.linkType == 5 && this.linkValue == 11) {

                                        box_svg.append($('<a class="box-frame" onclick="getMapUrl(this)"'
                                            + 'data-ad="' + this.adCatalog + '"'
                                            + 'data-name="' + this.adCatalogName + '"'
                                            + 'data-url="' + this.linkUrl + '"'
                                            + 'data-type="' + this.linkType + '"'
                                            + 'data-link="' + this.linkValue + '"'
                                            + 'data-productid="' + this.linkValue + '"'
                                            + 'data-activityid="' + this.linkValue + '"'
                                            + 'data-activityid="' + this.linkValue + '"'
                                            + 'data-msgtype="' + this.msgType + '"'
                                            + 'style="left: ' + Math.min(_map[0], _map[2]) + '%; top: ' + Math.min(_map[1], _map[3]) + '%; width: ' + Math.abs(_map[2] - _map[0]) + '%; height: ' + Math.abs(_map[3] - _map[1]) + '%;background: ' + "rgba(0,0,0,0.5)" + ';"'
                                            + '></a>'));
                                    } else {
                                        box_svg.append($('<a class="box-frame" onclick="getMapUrl(this)"'
                                            + 'data-ad="' + this.adCatalog + '"'
                                            + 'data-name="' + this.adCatalogName + '"'
                                            + 'data-url="' + this.linkUrl + '"'
                                            + 'data-type="' + this.linkType + '"'
                                            + 'data-link="' + this.linkValue + '"'
                                            + 'data-productid="' + this.linkValue + '"'
                                            + 'data-activityid="' + this.linkValue + '"'
                                            + 'data-activityid="' + this.linkValue + '"'
                                            + 'data-msgtype="' + this.msgType + '"'
                                            + 'style="left: ' + Math.min(_map[0], _map[2]) + '%; top: ' + Math.min(_map[1], _map[3]) + '%; width: ' + Math.abs(_map[2] - _map[0]) + '%; height: ' + Math.abs(_map[3] - _map[1]) + '%;"'
                                            + '></a>'));
                                    }

                                });

                                box_list.append(box_svg);
                            }
                        });

                        activity_brand_group_decorate.append(box_list);
                    });
                }
            }
        });
    },
    special: function (_scroll) {
        var _ts = this,
            _td = _ts.data;
        new ajax(getApi.getCategoryBrandGroup, {
            data: {
                productTypeId: '',
				adCatalog: 1,

				brandTeamTypeId: _td.id,
				productType1Id: '',
				productType2Ids: _td.productType2Ids,

				currentPage: activity_brand_group_scroll.attr('data-page') || 0,
				pageSize: _td.size
            },
            success: function (res) {
                console.log(res)
                if(_scroll){
                    if (res.returnData.dataList.length) {
						var _cur = activity_brand_group_scroll.attr('data-page');
						_cur++;
						activity_brand_group_scroll.attr('data-page', _cur);
                        activity_brand_group_special.append(template('group_activityList_tpl',res.returnData))
					} else {
						delayTip('已经全部加载完毕');
					}
                }else{
                    if(res.returnData.dataList.length == 0){
                        activity_brand_group_special.html(template('group_activity_msg'))
                    }else{
                        activity_brand_group_special.html(template('group_activityList_tpl',res.returnData))
                    }
                    res.returnData.dataList.length == _td.size && scrollHand.init(activity_brand_group_scroll, function () {
						_ts.special(true);
					}, true);
                }
            }
        })
    },
}

getTpl(function () {
    brandGroup.init();
});