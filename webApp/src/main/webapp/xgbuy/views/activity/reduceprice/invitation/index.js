var activity_reduceprice_invitation = $('.page[data-id="activity_reduceprice_invitation"]:visible'),
    invitationCon = activity_reduceprice_invitation.find("#invitation_content");
invitationRecord = activity_reduceprice_invitation.find("#invitation_content_record");
recommendNew = activity_reduceprice_invitation.find("#recommend_new");
recommendList = activity_reduceprice_invitation.find("#recommend_list");
var invitation = {
    data: {
        time: 3600000 * 25, // 倒计1*25小时
        host: 'm.xinggoubuy.com',
        id: '',
        tab: 0,
        flag: true,
        cutOrderId: '',
        page: 0,
        helpData: [],
        memberId: '',
        pag: 10,
        maplocation: '',
        dataLista: [],
        count: '',
        countNum: '',
        counts: 1,
        newOrend: false,
        bottomEnd: true,
        currentPages: 0,
        recorPage:0,
        recordEnd:true,
        hideList:[]
    },

    ajax: {
        getInviteDetail: []
    },
    init: function () {
        var _ts = this,
            _td = _ts.data;
        $('.showOrHide').slideUp(1);
        if (search_url) {
            for (var i = 0; i < search_url.length; i++) {
                var _a = search_url[i].split('='),
                    _b = _a[1];
                switch (_a[0]) {
                    case 'cutOrderId':
                        _td.cutOrderId = _b;
                        break;
                    case 'id':
                        _td.id = _b;
                        break;

                }
            }

        }
        clearInterval(window.timer);
        if(member.sys){

        }
        _ts.transPage();
        _ts.helpRecord();
        _ts.helpSucceed();
        _ts.newRecommend();
        // var member = JSON.parse(localStorage.getItem('member'));
        // _td.memberId = member.id;
    },
    //页面加载使用数据
    transPage: function () {
        var _ts = this,
            _td = _ts.data;
        new ajax(getApi.getMyAssistanceCutPriceShareInfo, {
            data: {
                id: _td.id,
                cutOrderId: _td.cutOrderId
            },
            success: function (res) {
                var _e = res.returnData;
                var data = {
                    skuPic: _e.skuPic,
                    headPic: _e.headPic,
                    beginDate: _e.beginDate,
                    currentDate: _e.currentDate,
                    buyEndDate: _e.buyEndDate,
                    cutLadderList: _e.cutLadderList,
                    fixedAmount: _e.fixedAmount,
                    productName: _e.productName,
                    endDate: _e.endDate,
                    productId: _e.productId,
                    productItemId: _e.productItemId,
                    quantity: _e.quantity,
                    price: _e.salePrice - _e.assistanceNum * _e.fixedAmount,
                    curOrderId: _e.cutOrderId,
                    unAssistanceNum: _e.assistanceNum,
                    taskStatus: _e.taskStatus,
                    memberAssistanceStatus: _e.memberAssistanceStatus
                };
                var cutList = _e.cutLadderList;
                var mean = (100 - (20 * cutList.length)) / (cutList.length - 1);
                for (var i = 0; i < cutList.length; i++) {
                    if (cutList[i].selected) {
                        var maplocation = 20 * (i + 1) + i * mean - 10;
                        _td.maplocation = maplocation;

                    }
                }

                invitationCon.html(template('invitation_con', data));
                require(['qpTime'], function () {

                    _ts.timeRun();

                });
            }
        });
    },
    helpRecord: function () {
        var _ts = this,
            _td = _ts.data;
        new ajax(getApi.getInviteDetail, {
            data: {
                activityType: 10,
                cutOrderId: _td.cutOrderId,
                currentPage: _td.recorPage,
                memberId: member.id,
                pageSize: 5
            },
            success: function (res) {
                _td.helpData = res.returnData;
                if(res.returnData.length>=5){
                    _td.recordEnd = true
                }else{
                    _td.recordEnd = false
                }
                var dataList = _td.helpData;
                if (dataList.length > 0 || _td.recorPage != 0) {
                    //截取前五个 如果有全部截取第二页则不截取
                    _td.hideList = _td.hideList.concat(dataList);
                    var showList = _td.hideList.slice(0, 5);
                    //再渲染数据之前判断是否大于5条
                    if(_td.hideList.length <= 5 && _td.recorPage == 0){
                        _td.flag = false;
                        $(".invitationPerson").addClass('showOrHide');
                        $('.showOrHides').slideDown(500, function () {
                            $(".img").removeClass('showImg');
                            $(".img").addClass('hideImg');
                        });
                    }
                    //值截取第一页的前5个 后面的请求跟它没关
                    $(".invitationPerson").html('')
                    for (var i = 0; i < showList.length; i++) {
                        $(".invitationPerson").append(
                            '<div class="invitationPersonList">' +
                            '<img src="' + showList[i].weixinHead + '" alt="">' +
                            '<span>' + showList[i].memberNick + '</span>' +
                            '<div class="price">-' + showList[i].amount + '</div>' +
                            '</div>'
                        )
                    }
                    $(".invitationPersons").html('')
                    for (var j = 0; j < _td.hideList.length; j++) {
                        $(".invitationPersons").append(
                            '<div class="invitationPersonLists">' +
                            '<div class="record_head_portrait">' +
                            '<img class="details_img" src="' + _td.hideList[j].weixinHead + '"></img>' +
                            '<div class="head_text">' + _td.hideList[j].memberNick + '</div>' +
                            '<div class="head_price">-' + _td.hideList[j].amount + '</div>' +
                            '</div>' +
                            '<div class="details_text">' + _td.hideList[j].content + '</div>' +
                            '<div class="details_texts">' + _td.hideList[j].createDateStr + '</div>' +
                            '</div>'
                        )
                    }
                    // invitationRecord.html(template('invitation_record', data));


                } else {
                    //没数据
                    $(".nostate").html('<div class="invitationState"> 当前还无人助力，加油哦！</div>')
                }
            }
        })
    },

    iosAndroidShare: function (memberId, cutOrderId, productId) {
        var _ts = this,
            _td = _ts.data;
        //调用微信分享弹窗
        new ajax(getApi.getMyAssistanceCutPriceShareInfo, {
            data: {
                type: 10,
                memberId: member.id,
                cutOrderId: _td.cutOrderId,
                productId: _td.id
            },
            success: function (e) {
                var res = e.returnData;
                shareHand.transShow({
                    wx: 1,
                    // title: '<div' + (member.app ? ' style="font-size: 17px;text-align: center;"' : '') + '>邀请<span style="color: #ff5050;">' + _maxInviteTimes + '位</span>APP新用户立减<span style="color: #ff5050;">' + (_fixedAmount * _maxInviteTimes) + '元</span></div>',
                    title: '<span' + (member.app ? ' style="font-size: 17px;text-align: center;display:block;"' : '') + '>邀请<font color="#ff5050">' + 1000 + '位</font>APP新用户立减<font color="#ff5050">' + 60 + '元</font></span>',
                    sp1: '1' || '0',
                    "skuPic": res.skuPic,//商品图
                    // "salePrice": res.salePrice,//商品价格
                    "salePrice":res.cutLadderList.slice(res.cutLadderList.length - 1)[0].price,//商品价格
                    "shareTitle": res.shareTitle,//分享标题
                    "shareDesc": res.shareDesc,//分享的描述
                    "sharePic": "",//分享的背景图
                    "webpageUrl": res.webPageUrl,//分享跳转页面地址
                    "wxPath": 'page/activity/reducePrice/share/index?cutOrderId=' + res.cutOrderId + '&sourceMemberId=' + res.sourceMemberId,//小程序具体页面
                    "xcxShareType": res.xcxShareType,//分享小程序的版本
                    "originalId": res.originalId,//小程序醒购原始ID

                });
            }
        });
    },


    recommendNew: function () {
        var _ts = this,
            _td = _ts.data;
        $(".newList").removeClass('showColor');
        $(".news").addClass('showColor');
        $(".recommendNew").removeClass('hideList');
        $(".recommendList").addClass('hideList');
        _td.newOrend = false;
    },
    recommendList: function () {
        var _ts = this,
            _td = _ts.data;
        $(".news").removeClass('showColor');
        $(".newList").addClass('showColor');
        $(".recommendList").removeClass('hideList');
        $(".recommendNew").addClass('hideList');
        _td.newOrend = true;
    },
    showOrHideBtn: function () {
        var _ts = this,
            _td = _ts.data;
        if (_td.flag) {
            _td.flag = false;
            $(".invitationPerson").addClass('showOrHide')
            $('.showOrHides').slideDown(500, function () {
                $(".img").removeClass('showImg');
                $(".img").addClass('hideImg');
            });
        } else {
            _td.flag = true
            $('.showOrHides').slideUp(500, function () {
                $(".img").addClass('showImg');
                $(".img").removeClass('hideImg');
                $(".invitationPerson").removeClass('showOrHide')
            });

        }
    },
    helpSucceed: function () {

        var _ts = this,
            _td = _ts.data;
        new ajax(getApi.findAssistanceCutCompleteLog, {
            data: {
                "productId": _td.id,
                "currentPage": _td.page,
                "pageSize": 10
            },
            success: function (res) {
                // recommendNew.html(template('invitation_recommend_new', res));
                var resData = res.returnData;
                _td.dataLista = _td.dataLista.concat(res.returnData);
                var dataList = _td.dataLista;
                for (var i = 0; i < resData.length; i++) {
                    $(".recommendNewListScroll").append(
                        '<li class="recommendNewList">' +
                        '<img src="' + resData[i].wxHead + '" alt="">' +
                        '<span class="recommendNewListName">' + resData[i].nickName + '</span>' +
                        '<span class="recommendNewListPrice">￥' + resData[i].finalPrice + '成交</span>' +
                        '</li>'
                    )
                }
                //判断是否加载完成
                var flag = false;
                window.clearInterval(window.timer);
                window.timer = setInterval(function () {
                    var $self = $(".recommendNew").find("ul:first");
                    var lineHeight = $self.find("li:first").height();
                    $self.animate({
                        "margin-top": (-lineHeight - 14) * _td.count++ + "px"
                    });
                    var hei = (lineHeight + 14) * _td.countNum++;
                    if (resData.length >= 10 && hei == 225 * _td.counts) {
                        _td.page++;
                        _td.counts++;
                        _ts.helpSucceed();

                    } else if (resData.length < 10) {
                        clearInterval(window.timer);
                        clearInterval(window.timers);
                        window.timer = setInterval(function () {
                            var $self = $(".recommendNew").find("ul:first");
                            var lineHeight = $self.find("li:first").height();
                            $self.animate({
                                "margin-top": (-lineHeight - 14 - hei) + "px"
                            }, 500, function () {
                                $self.css({
                                    "margin-top": -hei+"px"
                                });
                                $self.find("li:first").appendTo($self);
                            })
                        }, 5000);
                    }
                }, 5000)
            }
        })
    },
    newRecommend: function (_scroll) {
        var _ts = this,
            _td = _ts.data;
        !_scroll && recommendList.removeAttr('data-page').removeAttr('old-page').scrollTop(0);
        new ajax(getApi.getBargainGoodsList, {
            data: {
                "type": 10,
                "memberId": member.id,
                "currentPage": _td.currentPages
            },
            success: function (res) {
                var _list = res.returnData,
                    _data = _list.dataList,
                    _len = _data.length;
                recommendList.append(template('invitation_recommend_list', res.returnData));

                _len<10 && _td.currentPages > 0 ? delayTip('已经全部加载完毕') : ''

            }
        })
    },
    timer: function (e) {
        return famateNumber(e / 1e3 / 60 / 60) + ':' + famateNumber(e / 1e3 / 60 % 60) + ':' + famateNumber(e / 1e3 % 60);
    },
    timeRun: function (e) {
        var _ts = this,
            _td = _ts.data;
        $.each(invitationCon.find('time'), function () {
            var $ts = $(this);
            $.qpTime({
                s: 1,
                e: $ts.data('timer')
            }, function (pt) {
                $ts.html(_ts.timer(pt.t));
            });
        });
        $.each(invitationCon.find('.invitationPlanBgOn'), function () {
            var $ts = $(this);
            $ts.css({'width': _td.maplocation + '%'})
        });
    },

//    立即购买
    buyNow: function (_id, _item, _num, _price, _cutOrderId, _un) {
        var _ts = this;
        if (_un && _un > 0) {
            _ts.buyNowConfirm(_id, _item, _num, _price, _cutOrderId);
            return;
        }

        if (member.sys == 'ios') {
            checkAppVer(53, function () {
                _ts.checkBuyNow(_id, _item, _num, _price, _cutOrderId);
            });
        } else if (member.sys == 'android') {
            checkAppVer(50, function () {
                _ts.checkBuyNow(_id, _item, _num, _price, _cutOrderId);
            });
        } else {
            _ts.checkBuyNow(_id, _item, _num, _price, _cutOrderId);
        }
    },

    checkBuyNow: function (_id, _item, _num, _price, _cutOrderId) {
        new ajax(getApi.addShoppingCart, {
            data: {
                memberId: member.id,
                productId: _id,
                productItemId: _item,
                quantity: _num,
                activityId: '',
                activityType: 10,
                singleProductActivityId: ''
            },
            success: function (e) {
                var _list = e.returnData,
                    _client = '';

                isAndroid && (_client = 2);
                isiOS && (_client = 1);
                setStorage('cart_detail', JSON.stringify({
                    shopCardIds: _list,
                    payAmount: _num * _price,
                    nums: _num * _price,
                    coup: 0
                }));

                setStorage('cart_pay', JSON.stringify({
                    cutOrderId: _cutOrderId,
                    sourceClient: _client,
                    mermberPlatformCouponId: '',
                    payAmount: _num * _price,
                    shopCardIds: _list,
                    dataList: []
                }));
                getUrl('order/firm/index.html', true);
            },
            limit: 1
        });
    },

    buyNowConfirm: function (_id, _item, _num, _price, _cutOrderId) {
        var _tip = $('.page:visible .confirm-buynow');

        $('.popup').removeClass('show');
        $('.page:visible .scroll-more').css('pointer-events', 'none');

        if (_tip.length) {
            _tip.addClass('show');
        } else {
            $('.page:visible').append('<div class="popup confirm-buynow">'
                + '<div class="popup-center popup-tip">'
                + '<div>'
                + '<h3 class="flex ac jc">提示</h3>'
                + '<p class="flex jc txt scroll-y">存在未完成助力是否继续购买</p>'
                + '</div>'
                + '<div class="flex">'
                + '<a class="btn flex fg ac jc c9 bdr" data-id="' + _cutOrderId + '" onclick="invitation.detail(this)">查看助力</a>'
                + '<a class="btn flex fg ac jc popup-click f50" onclick="invitation.buyNow(' + _id + ', ' + _item + ', ' + _num + ', ' + _price + ', ' + _cutOrderId + ')">继续购买</a>'
                + '</div>'
                + '</div>'
                + '</div>');

            $('.page:visible .confirm-buynow').addClass('show');
        }
    },
};
$(".invitation").scroll(function (e) {
//    判断元素是否到底了
    var pageHei = $(".invitation")[0].clientHeight;//屏幕高度
    var pageTotalHei = $(".invitation")[0].scrollHeight;//页面总高度
    var pageScroll = $(".invitation").scrollTop(); //页面卷曲的高度
    //判断是那个页面需要滚动加载
    if ((pageHei + pageScroll + 30) > pageTotalHei && invitation.data.newOrend) {
        //    如果到底了则为false
        if (invitation.data.bottomEnd && invitation.data.newOrend) {
            invitation.data.bottomEnd = false;
            invitation.data.currentPages++;
            invitation.newRecommend();
        }
    }
});
$(".invitationPersons").scroll(function () {
    var pageHei = $(".invitationPersons")[0].clientHeight;//屏幕高度
    var pageTotalHei = $(".invitationPersons")[0].scrollHeight;//页面总高度
    var pageScroll = $(".invitationPersons").scrollTop(); //页面卷曲的高度
    //判断是那个页面需要滚动加载
    if ((pageHei + pageScroll + 30) > pageTotalHei && invitation.data.recordEnd) {
        //    如果到底了则为false
        invitation.data.recordEnd = false;

        if(invitation.data.recordEnd == false){
            invitation.data.recorPage++;
            invitation.helpRecord();
        }
    }


})
getTpl(function () {
    invitation.init();
    window.timer = null;
    window.timers = null;
});



























