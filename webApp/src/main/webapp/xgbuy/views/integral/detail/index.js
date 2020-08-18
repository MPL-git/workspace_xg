var integral_detail = $('.page[data-id="integral_detail"]:visible'),
	integral_detail_scroll = integral_detail.find('.scroll-more'),
	integral_detail_scrolls = integral_detail.find('.scroll-moreList'),
	integral_detail_scrolls_tab = integral_detail.find('.scroll-moreListe-tab'),
	integral_detail_scrolls_tabs = integral_detail.find('.scroll-moreListe-tabs'),
	hintInfo = integral_detail.find('.hintInfo'),
    integral_detail_scrollData = integral_detail.find('.scroll-moreData');
var integralDetail = {
	data: {
		pag: 10,
		pags : 0,
		pageScrolltops:0,
		tabOpenFlag:false,
        flagScroll:false,
        productTypeId:0,
        listDetails:0,
        memberId:'13074193'
	},
	init: function () {
		var _ts = this,
			_td = _ts.data;
		traffic.setPv(_td, {
			pageType: '16'
		});

		//页面一进来就判断是否有memberId
        let memberItem = localStorage.getItem('member');
        if(memberItem){
            _td.memberId = JSON.parse(memberItem).id;
            _ts.getPagingList();
            _ts.getDataList();
        }else{
            _td.memberId = "";
            _ts.getPagingList();
            _ts.getDataList();
        }
	},
	getPagingList: function (_scroll) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getSingleIntegralMallActivityList, {
			data: {
				memberId: member.id,
				type: 5,
				currentPage:0,
				pageSize: _ts.data.pag
			},
			success: function (res) {
				var _pag = res.returnSize,
					_list = res.returnData,
					_title = _list.integral;
				_list.data = imgCacheProductPic(_list.data, 'integral_detail_list_tpl');
					_pag && (_ts.data.pag = _pag);
					integral_detail_scroll.append(template('integral_detail_tpl', _list));
					setTitle(_title + '积分');
				integral_detail_scrolls_tab.html(template('integral_detail_menu_tpl', _list));
				integral_detail_scrolls_tabs.html(template('integral_detail_menus_tpl', _list));
			}
		});
	},
	getDataList:function (_scroll,res) {
		var _ts = this,
			_td = _ts.data;
		let productTypeId = (res == undefined || res == null || res == 0) ? '' : Number(res);
		new ajax(getApi.getSingleProductActivityCustomsByProductTypeId, {
			data: {
				memberId: _td.memberId,
				type: 5,
				currentPage: _ts.data.pags,
				pageSize: _ts.data.pag,
				productTypeId: productTypeId
			},
			success: function (res) {
				var _len = res.returnData.data.length,
					_list = res.returnData,
					_pag = res.returnSize;
				_list.data = imgCacheProductPic(_list.data, 'integral_detail_list_tpl');
                _td.listDetails = _list.data.length;
                if(_list.data.length < 10){
                    _td.flagScroll = false;
                    delayTip('已经全部加载完毕');
                    hintInfo.html("沒有更多內容啦")
                }else{
                    _td.flagScroll = true;
                }
                integral_detail_scrolls.append(template('integral_detail_list_tpl', {data:_list.data}));
			}
		});
	}
};

getTpl(function () {
	integralDetail.init();
});

function onShow() {
	traffic.setPv(integralDetail.data);
}
function menuOptions(res,agn,that) {
	var lileft = $(that).offset().left;
	var liwidth = $(that).width()/2;
	var widths = $(window).width()/2;
	var scrollL = $(that).parent().scrollLeft();
	$(".coverInfo").animate({
		scrollLeft:scrollL + (lileft - widths) + liwidth
	})
	$(".menuOption:nth-child("+agn+")").addClass('borders');
	$(".menuOption:nth-child("+agn+")").siblings().removeClass('borders');
	$(".detailsLists span:nth-child("+agn+")").addClass('borderBg');
	$(".detailsLists span:nth-child("+agn+")").siblings().removeClass('borderBg');
    integral_detail_scrolls.html(template('integral_detail_list_tpl', {data:{}}));
	$(".swiperContainer").css("display","none");
    integralDetail.data.pags = 0;
    integralDetail.data.productTypeId = res;
	integralDetail.getDataList(false,res);
}

$(".scroll-moreData").scroll(function(e) {
    let pageHei = $(".scroll-moreData")[0].clientHeight;//屏幕高度
    let pageTotalHei = $(".scroll-moreData")[0].scrollHeight;//页面总高度
    let pageScroll = $(".scroll-moreData").scrollTop(); //页面卷曲的高度
    if((pageHei+pageScroll+30) > pageTotalHei && integralDetail.data.flagScroll){
        let res = integralDetail.data.productTypeId;
        integralDetail.data.pags ++;
        integralDetail.getDataList(false,res);
        integralDetail.data.flagScroll = false;
    }
	integralDetail.data.pageScrolltops = $(".scroll-moreData").scrollTop();
	let headTop = $(".header")[0].offsetHeight;
    let scorll = Math.floor($( ".flexBox" ).offset().top)-headTop;
    if(scorll < 40){
        $( ".menuListBar" ).addClass("menuListBars");
		$( ".flexBox:first" ).addClass('mt_4')
    }else{
        $( ".menuListBar" ).removeClass("menuListBars");
		$( ".flexBox:first" ).removeClass('mt_4')
    }
});
function imgClick() {
	let headTop = $(".header")[0].offsetHeight;
	let scorll = Math.floor($( ".menuListBar" ).offset().top);
	let scorllTops = integralDetail.data.pageScrolltops+(scorll - headTop);
	integralDetail.data.tabOpenFlag = !integralDetail.data.tabOpenFlag;
		$(".scroll-moreData").animate({ scrollTop: scorllTops}, 500);
	if(integralDetail.data.tabOpenFlag){
		$( ".menuLists" ).addClass("dispNo")
	}else{
		$( ".menuLists" ).removeClass("dispNo")
	}
}
function HideImgClick() {
	integralDetail.data.tabOpenFlag = !integralDetail.data.tabOpenFlag;
	$( ".menuLists" ).removeClass("dispNo")
}

