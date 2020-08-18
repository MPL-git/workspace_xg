var winningRecord = $(".page[data-id='activity_pointsLottery_record']:visible"),
winningRecordTemplate = winningRecord.find('.winning-record-template'),
winningMark = winningRecord.find('.rp-mark-content'),
winningBigImg = winningRecord.find('.rp-large-img');

var winningRecordApi = {
  data: {
    currentPage: 0,
    listData: [],
    addressData: {}, // 用户默认地址
    memberLotteryId: '', // 用户中奖纪录ID
    productId: '', // 商品ID
    propIds: [],
    propValId: [],
    comTip: [], // 商品规格提示
    canShowMore: true,
    skuPic: '',
    itemIndex: 0 // 领取奖励下标
  },
  init: function () {
    winningRecordTemplate.html(template('winningRecordTemplate'));
    getH5PageType();
    var _this = this;
    new ajax('../../api/y/memberLottery/list', {
      data: {
        currentPage: _this.data.currentPage
      },
      success: function (res) {
        _this.data.listData = _this.data.listData.concat(res.returnData);
        if (res.returnData.length < 1 && _this.data.listData.length < 1) {
          winningRecord.find('.winning-record-items').html("<div class='no-winning-record'>您暂时没有中奖记录</div>");
        }
        if (res.returnData.length > 7) {
          _this.data.canShowMore = true;
          winningRecord.find('.winning-jagged').css('display', 'flex');
        } else {
          _this.data.canShowMore = false;
          winningRecord.find('.winning-jagged').html('没有更多记录了~').css('display', 'block');
        }
        if (res.returnCode == '0000') {
          var listStr = '';
          for (var i = 0; i < _this.data.listData.length; i++) {
            var btnStr = _this.data.listData[i].received && _this.data.listData[i].type == '3' ? '已领取' : _this.data.listData[i].type == '3' ? '立即领取' : '查看';
            listStr += `<li>
                          <p>${_this.data.listData[i].prizeName}</p>
                          <span>${_this.data.listData[i].createDate}</span>
                          <div class="winning-reward-btn ${_this.data.listData[i].received && _this.data.listData[i].type == '3' ? 'no-operation' : 'can-operation'}" onclick="winningRecordApi.opearaWin(${i})">${btnStr}</div>
                        </li>`
          }
          winningRecord.find('.winning-record-detail').html(listStr);
        }
      }
    })
  },
  opearaWin: function (index) {
    this.data.itemIndex = index;
    var itemData = this.data.listData[index];
    if (itemData.received && itemData.type == '3') {
      if (member.sys == 'android') {
        intentMyorder();
      } else if (member.sys == 'ios') {
        checkAppVer('2.1.8', function () {
          callIntentTrailer('intentTrailerNative', 'intentMyorder');
        });
      } else {
        getUrl('order/index.html', true);
      }
    } else {
      switch (itemData.type) {
        case '1':
          if (member.sys == 'android') {
            intentIntegral();
          } else if (member.sys == 'ios') {
            callIntentTrailer('intentTrailerListNative', 'intentIntegral');
          } else {
            getUrl('integral/index.html', true);
          }
          break;
        case '2':
          if (member.sys == 'android') {
            intentCoupon();
          } else if (member.sys == 'ios') {
            checkAppVer(50, function () {
              callIntentTrailer('intentTrailerListNative', 'intentCoupon');
            });
          } else {
            getUrl('coupon/index');
          }
          break;
        case '3':
          this.showMarkPrize(itemData);
          break;
      }
    }
  },
  // 展示更多
  showMoreList: function () {
    if (this.data.canShowMore) {
      this.data.currentPage++;
      this.init();
    }
  },
  // 获取用户默认地址
  getMemberAddress: function () {
    var _this = this;
    if(getStorage('address_list')){
      _this.data.addressData = JSON.parse(getStorage('address_list'));
      if (winningRecord.find('.rp-address-info')) {
        winningRecord.find('.rp-address-info').html(`<p class="rp-connect">
        <span>${_this.data.addressData.recipientName}</span>
        ${_this.data.addressData.recipientPhone}
      </p>
      <p>${_this.data.addressData.recipientAddressFullName}</p>`)
      }
		} else {
      new ajax('../../api/y/memberDefaultAddress', {
        success: function (res) {
          if (res.returnCode == '0000' && res.returnData) {
            _this.data.addressData = res.returnData;
          }
        }
      })
    }
  },
  // 跳转添加地址
  getAddress:function () {
    this.data.addressData.addressId ? getUrl(`activity/pointsLottery/address/list.html`, 'self') : getUrl(`activity/pointsLottery/address/edit.html`, 'self');
  },
  // 领取奖励
  showMarkPrize: function (itemData) {
    var _this = this;
    _this.data.memberLotteryId = itemData.id;
    _this.data.productId = itemData.productId;
    new ajax(getApi.productInfo, {
      data: {
        memberId: member.id,
        id: itemData.productId
      },
      success: function (res) {
        if (res.returnCode == '0000') {
          if (res.returnData.auditStatus == '1' || res.returnData.dataMap.stock <= 0) {
            return delayTip('库存不足，领取失败，请联系客服！');
          }
          var standardMap = res.returnData.standardMap,
          dataMap = res.returnData.dataMap,
          standardStr = '';
          _this.data.skuPic = dataMap.skuPic;
          for (var i = 0; i < standardMap.length; i++) {
            var standardItem = standardMap[i].propValueMapList;
            var itemStr = '';
            _this.data.propIds.push(standardMap[i].propId);
            for (var j = 0; j < standardItem.length; j++) {
              itemStr += `<li data-propValId="${standardItem[j].propValId}">${standardItem[j].propValue}</li>`;
            }
            standardStr += `<div class="rp-sort">
                              <h3>${standardMap[i].propName}</h3>
                              <ul data-id="${standardMap[i].propId}">
                                ${itemStr}
                              </ul>
                            </div>`
          }
          var templateStr = `<div class="rp-mark-content">
                              <div class="rp-top">
                                <div class="rp-img" onclick="winningRecordApi.showLargeImg()">
                                  <img src="${dataMap.skuPic_S}" />
                                </div>
                                <div>
                                  <div class="rp-price">
                                    <p>￥${dataMap.salePrice}</p>
                                  </div>
                                  <div class="rp-chosed"></div>
                                </div>
                              </div>
                              ${standardStr}
                              <div class="rp-shipping-address">
                                <h3>收货地址</h3>
                                <div class="rp-address" onclick="winningRecordApi.getAddress()">
                                  ${_this.data.addressData.recipientAddressFullName ?
                                    `<div class="rp-address-info">
                                      <p class="rp-connect">
                                        <span>${_this.data.addressData.recipientName}</span>
                                        ${_this.data.addressData.recipientPhone}
                                      </p>
                                      <p>${_this.data.addressData.recipientAddressFullName}</p>
                                    </div>` :
                                    `<p onclick="getUrl('activity/pointsLottery/address/edit.html', 'self')">当前暂无收货地址，去添加</p>`}
                                    <i></i>
                                </div>
                              </div>
                            </div>`;
          winningMark.html(templateStr);
          popupShow('.modal-mark');
          changeStandard();
        }
      }
    })
  },
  // 放大图片
  showLargeImg: function () {
    winningBigImg.html(`<img src="${this.data.skuPic}">`);
    popupShow('.rp-large-pic');
  },
  // 提交
  submit: function () {
    var _this = this;
    var source = '';
    if (isiOS) {
      source = '1';
    } else if (isAndroid) {
      source = '2';
    } else if (isWeiXin) {
      source = '4';
    } else {
      source = '3';
    }
    if (_this.data.propIds.length > 0) {
      var flag = true;
      for (var i = 0; i < _this.data.propValId.length; i++) {
        if (_this.data.propValId[i] == null) {
          flag = false;
          delayTip(_this.data.comTip[i]);
          return;
        }
      }
      if (flag) {
        new ajax(getApi.productItems, {
          data: {
            id: _this.data.productId,
            propIds: _this.data.propIds.join(','),
            propValIds: _this.data.propValId.join(',')
          },
          success: function (res) {
            if (res.returnCode == '0000') {
              new ajax('../../api/y/memberLottery/receiveProduct', {
                data: {
                  memberLotteryId: _this.data.memberLotteryId,
                  memberAddressId: _this.data.addressData.addressId,
                  productItemId: res.returnData.skuId,
                  sourceClient: source
                },
                success: function () {
                  popupShow('.rp-success-mark');
                }
              })
            }
          }
        })
      }
    } else {
      new ajax('../../api/y/memberLottery/receiveProduct', {
        data: {
          memberLotteryId: _this.data.memberLotteryId,
          memberAddressId: _this.data.addressData.addressId,
          productItemId: '',
          sourceClient: source
        },
        success: function () {
          popupShow('.rp-success-mark');
        }
      })
    }
  },
  // 重置数据
  reset() {
    closeSelf(winningRecord.find('.rp-success-mark'));
    closeSelf(winningRecord.find('.modal-mark'));
    this.data.currentPage = 0;
    this.data.listData = [];
    this.data.propIds = [];
    this.data.propValId = [];
    this.data.comTip = [];
    this.init();
    this.getMemberAddress();
  },
  // 选择规则
  chosedTem(arr) {
    var newArr = [];
    var str = '';
    for (var i = 0; i < arr.length; i++) {
      if (arr[i] != null) {
        newArr.push(arr[i]);
      }
    }
    str = newArr.length > 0 ?
    `<span>选择</span><p>${newArr.join('、')}</p>`:
    ''
    winningRecord.find('.rp-chosed').html(str);
  }
}

getTpl(function () {
  setTitle('我的中奖记录');
  winningRecordApi.init();
  winningRecordApi.getMemberAddress(); // 获取用户默认地址
});
// 选择商品类别
function changeStandard() {
  var normList = winningRecord.find('.rp-sort')
  propText = [];
  winningRecordApi.data.propValId = [];

  $.each(normList, function (index) {
    winningRecordApi.data.propValId.push(null);
    propText.push(null);
    winningRecordApi.data.comTip.push(`请选择商品${$(this).find('h3').text()}`);
    var items = $(this).find('li');
    items.click(function () {
      $(this).siblings('li').removeClass('rp-chose-active');
      if ($(this).hasClass('rp-chose-active')) {
        $(this).removeClass('rp-chose-active');
        winningRecordApi.data.propValId[index] = null;
        propText[index] = null;
        winningRecordApi.chosedTem(propText);
      } else {
        $(this).addClass('rp-chose-active');
        winningRecordApi.data.propValId[index] = $(this).attr('data-propValId');
        propText[index] = $(this).text();
        winningRecordApi.chosedTem(propText);
      }
    })
  })
}