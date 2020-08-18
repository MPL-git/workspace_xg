var pointsDraw = $('.page[data-id="activity_pointsLottery"]:visible'),
pointsTemplate = pointsDraw.find('.points-lottery-template'),
ruleContent = pointsDraw.find('.points-rule-content');
var pointsDrawShareData = {};

var pointsLottery = {
  data: {
    winnerTimer: null, // 公告轮询
    timerMid: null, // 中速
    timerSlow: null, // 慢速
    boxIndex: 0, // 选中奖品
    spin: 0, // 转的圈数
    settingList: [],
    drawBox: null, // 九宫格节点
    prizeDescList: [], // 获奖名单
    isFree: false, // 抽奖是否免费
    freeTimes: 0, // 免费次数
    consume: 0, // 消耗积分
    totalIntegral: 0, // 总积分
    drawIndex: 0, // 抽奖位置
    drawFlag: true, // 抽奖开关
    prizeData: {} // 抽中奖品数
  },

  init: function () {
    pointsTemplate.html(template('pointsLotteryTemplate'));
    var _this = this;
    new ajax('../../api/n/integralLottery/info', {
      success: function (res) {
        if (res.returnCode === '0000') {
          var returnData = res.returnData;
          _this.data.prizeDescList = returnData.prizeDescList;
          _this.data.settingList = returnData.settingList;
          _this.data.isFree = returnData.free;
          _this.data.freeTimes = returnData.restFreeTimes;
          _this.data.consume = returnData.perIntegralSpend;
          _this.data.totalIntegral = returnData.memberIntegral;
          _this.nineTemeplate();
          _this.winnerTemeplate();
          _this.getRules(returnData.informationId);
          pointsDrawShareData = {
            wxPath: returnData.wxPath,
            originalId: returnData.originalId,
            xcxShareType: returnData.xcxShareType,
            wxShareContent: returnData.wxShareContent,
            wxSharePic: returnData.wxSharePic
          }
          setStorage('pointsDrawShareData', JSON.stringify(pointsDrawShareData))
        }
      }
    })
  },

  // 获取规则
  getRules: function (id) {
    new ajax(getApi.getInformation, {
      data: {
        memberId: member.id,
        id: id,
				type: 1
      },
      success: function (res) {
        ruleContent.html(res.returnData.content);
      }
    })
  },
  // 展示规则
  showRule: function () {
    this.modalOperation();
    popupShow('.points-rules-mark');
  },

  // 我的记录
  toRecord: function () {
    if (queryMember.id) {
      this.resetData();
      getUrl('activity/pointsLottery/record/index.html', true);
    } else {
      new ajax(getApi.appLogin, {
        data: {
          memberId: member.id,
          token: member.token
        },
        success: function () {}
      })
    }
  },

  // 九宫格模板
  nineTemeplate: function () {
    pointsDraw.find('.main-integral p').text(`我的积分：${this.data.totalIntegral}`);
    var str = '', setList = this.data.settingList;
    for (var i = 0; i < setList.length; i++) {
      if (setList[i].noStock) {
        str += `<div class="draw-${setList[i].position} lottery-box-item">
                <p class="lottery-thank">谢谢惠顾</p>
              </div>`
      } else {
        switch (setList[i].type) {
          case '1':
            str += `<div class="draw-${setList[i].position} lottery-box-item">
                      <div class="points-lottery-coins"></div>
                      <p class="lottery-integral">${setList[i].integral}积分</p>
                    </div>`;
            break;
          case '2':
            str += `<div class="draw-${setList[i].position} lottery-box-item">
                      <img src="${setList[i].productPic}" />
                      <p class="points-lottery-discount">优惠${setList[i].denomination}元</p>
                    </div>`;
            break;
          case '3':
            str += `<div class="draw-${setList[i].position} lottery-box-item" onclick="getUrlGoods(${setList[i].productId})">
                    <img src="${setList[i].productPic}" />
                    <p>抽奖免费拿</p>
                  </div>`;
            break;
        }
      }
    }
    str += `<div class="lottery" onclick="pointsLottery.lottery()">
            <p>抽奖</p>
            ${this.data.isFree && this.data.freeTimes > 0 ? `<span>免费${this.data.freeTimes}次</span>` : `<span>${this.data.consume}积分/次</span>`}
          </div>`
    pointsDraw.find('.points-lottery-box').html(str);
    this.data.drawBox = pointsDraw.find('.lottery-box-item');
  },

  // 奖品弹窗
  showModalTemplate: function (prizeData) {
    var modalContentStr = '',
    prizeStr = prizeData.prizeDesc && prizeData.prizeDesc.length > 5 ? `${prizeData.prizeDesc.substring(0, 4)}...` : prizeData.prizeDesc;
    switch (prizeData.type) {
      case '1':
        modalContentStr = `<div class="modal-prize modal-prize-coin">
                            <div class="prize-coin-img"></div>
                            <p>${prizeStr}</p>
                          </div>
                          <p class="modal-get-internal">${prizeData.prizeSubDesc}</p>`;
        break;
      case '2':
        modalContentStr = `<div class="modal-prize-coupon">
                            <img src="${prizeData.productPic}" />
                            <div class="coupon-direction">
                              <div class="coupon-direction-title">
                                <p>${prizeStr}</p>
                                <span>商品券</span>
                              </div>
                              <p class="modal-prize-date">${prizeData.prizeSubDesc}</p>
                            </div>
                          </div>`;
        break;
      case '3':
        modalContentStr = `<div class="modal-prize">
                            <img src="${prizeData.productPic}" />
                            <p>${prizeStr}</p>
                          </div>`
        break;
      case '4': 
        modalContentStr = `<div class="modal-prize">
                            <p class="modal-prize-thinks">谢谢惠顾</p>
                          </div>`;
        break;
    }
    var modalTemplateStr = `<i onclick="pointsLottery.modalOperation('1')"></i>
                            <h3>${prizeData.prizeTitle}</h3>
                            ${modalContentStr}
                            ${prizeData.shareTip ? `<p class="points-lottery-share">${prizeData.shareTip}</p>` : ''}
                            <div class="modal-btn" onclick="pointsLottery.modalOperation(${prizeData.type})">${prizeData.type == '3' ? '去领取' : '知道了'}</div>`;
    pointsDraw.find('.points-modal').html(modalTemplateStr);
    popupShow('.points-lottery-mark');
    this.resetData();
  },

  // 关闭弹窗
  modalOperation(type) {
    if (type == '3') {
      getUrl('activity/pointsLottery/record/index.html', true);
    }
    closeSelf(pointsDraw.find('.points-lottery-mark'));
  },

  // 获奖名单
  winnerTemeplate: function() {
    var winnerStr = [],
    winnerList = this.data.prizeDescList,
    listTimes = winnerList.length == 1 ? 10 : winnerList.length < 5 ? 5 : 2;
    console.log('listTimes', listTimes);
    if (winnerList.length > 0) {
      for (var i = 0; i < listTimes; i++) {
        for (var j = 0; j < winnerList.length; j++) {
          winnerStr.push(`<li>${winnerList[j]}</li>`);
        }
      }
      pointsDraw.find('.points-lottery-winner ul').html(winnerStr.join(''));
      var listLength = pointsDraw.find('.points-lottery-winner ul').children().length;
      this.data.winnerTimer = setInterval(function() {
        pointsDraw.find('.points-lottery-winner ul').animate({top: '-0.44rem'}, function () {
          pointsDraw.find('.points-lottery-winner ul').css({top: '0'})
          pointsDraw.find('.points-lottery-winner ul').children().first().remove();
        } );
        listLength--;
        winnerStr.shift();
        if (listLength <= 8) {
          for (var i = 0; i < 2; i++) {
            for (var j = 0; j < winnerList.length; j++) {
              winnerStr.push(`<li>${winnerList[j]}</li>`);
            }
          }
          pointsDraw.find('.points-lottery-winner ul').html(winnerStr.join(''));
          listLength = pointsDraw.find('.points-lottery-winner ul').children().length;
        }
      }.bind(this), 3000);
    } else {
      pointsDraw.find('.lottery-winner-content').html('<p>暂无中奖名单</p>');
    }
  },

  lottery: function () {
    var _this = this;
    if (!_this.data.drawFlag) return;
    _this.data.drawFlag = false;
    if (_this.data.isFree && _this.data.freeTimes > 0) {
      _this.data.freeTimes--;
    } else {
      _this.data.totalIntegral -= _this.data.consume;
    }

    if (queryMember.id) {
      new ajax('../../api/y/integralLottery/draw', {
        data: {
          pageSettingsList: _this.data.settingList,
          free: _this.data.isFree
        },
        success: function (res) {
          _this.data.drawIndex = res.returnData.position == 8 ? 0 : res.returnData.position;
          _this.data.prizeData = res.returnData;
          _this.midV();
        },
        error: function (err) {
          _this.data.drawFlag = true;
          if (err.returnCode == '5001') {
            popupShow('.points-lottery-refresh');
          } else {
            delayTip(err.returnMsg);
          }
        }
      })
    } else {
      _this.data.drawFlag = true;
      new ajax(getApi.appLogin, {
        data: {
          memberId: member.id,
          token: member.token
        },
        success: function () {
          new ajax('../../api/y/integralLottery/draw', {
            data: {
              pageSettingsList: _this.data.settingList,
              free: _this.data.isFree
            },
            success: function (res) {
              _this.data.drawIndex = res.returnData.position == 8 ? 0 : res.returnData.position;
              _this.data.prizeData = res.returnData;
              _this.midV();
            }
          })
        }
      })
    }
  },
  // 中速
  midV: function () {
    var _this = this;
    _this.data.timerMid = setInterval(function () {
      if (_this.data.spin > 2) {
        clearInterval(_this.data.timerMid);
        _this.slowV();
      }
      _this.plateTurn();
    }, 100);
  },
  // 慢速
  slowV: function () {
    var _this = this;
    _this.data.timerSlow = setInterval(function () {
      _this.plateTurn(_this.data.drawIndex);
    }, 300);
  },
  plateTurn: function (drawIndex) {
    // 重置九宫格样式
    for (var i = 0; i < this.data.drawBox.length; i++) {
      this.data.drawBox.eq(i).removeClass('activity');
    }
    // 选中奖品
    this.data.drawBox.eq(this.data.boxIndex).addClass('activity');
    this.data.boxIndex++;
    if (this.data.boxIndex > 7) {
      this.data.boxIndex = 0;
      this.data.spin++;
    }
    if (this.data.spin > 3 && drawIndex != undefined && (this.data.boxIndex === drawIndex)) {
      clearInterval(this.data.timerSlow);
      setTimeout(function() {
        this.showModalTemplate(this.data.prizeData);
        this.init();
      }.bind(this), 200);
    }
  },
  // 重置数据
  resetData: function () {
    this.data.spin = 0;
    this.data.boxIndex = 0;
    this.data.drawFlag = true;
    clearInterval(this.data.winnerTimer);
    this.data.winnerTimer = null;
  },
  // 初始化接口返回5001
  refreshPage() {
    closeSelf(pointsDraw.find('.points-lottery-refresh'));
    this.init();
  }
}

getTpl(function () {
  if (queryMember.id) {
    new ajax(getApi.appLogin, {
      data: {
        memberId: member.id,
        token: member.token
      },
      success: function (e) {
        setTitle('积分转盘');
        pointsLottery.init();
      }
    })
  } else {
    setTitle('积分转盘');
    pointsLottery.init();
  }
});

// APP 方法
function getIntegralDrawShareInfo () {
  var params = JSON.stringify({
    "linkType": 11,
    "linkValue": pointsDrawShareData
  })
  if (member.sys === 'android') {
    window.H5PluginManager.interactiveWithApp(params)
  } else if (member.sys === 'ios') {
    callIntentTrailer('intentTrailerListNative', 'interactiveWithApp', params)
  } else {
    alert('H5')
  }
}

function integralDrawShare (type) {
  console.log('type', type);
  if (type == 1) {
    new ajax('../../api/y/integralLottery/logShare', {});
    pointsLottery.init();
  }
}