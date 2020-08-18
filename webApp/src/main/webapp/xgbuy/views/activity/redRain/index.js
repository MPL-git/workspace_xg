'use strict';

var canvas = document.getElementById('ranCanvas');
var ctx = canvas.getContext('2d');
var w = window.innerWidth;
var h = window.innerHeight;
canvas.width = w;
canvas.height = h;

var member = {}; // 用户数据
var animatTimer = null; // 动画帧
var timer = null; // 倒计时函数变量
var pushTimer = null; // 数组添加数据
var intergal = 0; // 总积分
var shareData = {}; // 分享数据
var flag = true; // 判断是否可以继续游戏

var initImgArr = [{ img: './images/reward.png' }, { img: './images/boom.png' }, { img: './images/boom1.png' }, { img: './images/sock_g.png' }, { img: './images/sock_w.png' }, { img: './images/snowboom.png' }, { img: './images/snowboom_part1.png' }, { img: './images/snowboom_part2.png' }];
var initCutImgArr = [{
  img: './images/cut.png',
  x: -60,
  y: 24,
  speedX: 3,
  speedY: -1,
  radiusW: 118,
  radiusH: 40
}, {
  img: './images/add.png',
  x: 20,
  y: -15,
  speedX: null,
  speedY: 0,
  radiusW: 25,
  radiusH: 15
}, {
  img: './images/part_red1.png',
  x: 0,
  y: 0,
  speedX: -1,
  speedY: 0,
  radiusW: 52.3,
  radiusH: 36
}, {
  img: './images/part_red2.png',
  x: 0,
  y: 40,
  speedX: 1,
  speedY: 0,
  radiusW: 60,
  radiusH: 32
}, {
  img: './images/part_sock_g1.png',
  x: 0,
  y: 0,
  speedX: -1,
  speedY: 0,
  radiusW: 34,
  radiusH: 43
}, {
  img: './images/part_sock_g2.png',
  x: 0,
  y: 30,
  speedX: 1,
  speedY: 0,
  radiusW: 48,
  radiusH: 36
}, {
  img: './images/part_sock_w1.png',
  x: 0,
  y: 0,
  speedX: -1,
  speedY: 0,
  radiusW: 34,
  radiusH: 43
}, {
  img: './images/part_sock_w2.png',
  x: 0,
  y: 30,
  speedX: 1,
  speedY: 0,
  radiusW: 48,
  radiusH: 36
}];
var imgArr = [];
var cutImgArr = [];
var isLoadingImg = false;
var redArr = []; // 红包/炸弹
var cutArr = []; // 切开的红包
var cutBoomArr = []; // 切开炸弹

var initData = {}; // 初始数据

var isHidden = false; // 退出当前页后不再制造红包
var hiddenCount = 0;
var carryTime = 0;

var hasRain = false;
var isInGIF = false; // 是否在GIF倒计时内
var picImg = null; // GIF图片路径
var gifTimer = null; // GIF倒计时
function resetInitData() {
  canvas = document.getElementById('ranCanvas');
  ctx = canvas.getContext('2d');
  w = window.innerWidth;
  h = window.innerHeight;
  canvas.width = w;
  canvas.height = h;

  member = {}; // 用户数据
  animatTimer = null; // 动画帧
  timer = null; // 倒计时函数变量
  pushTimer = null; // 数组添加数据
  intergal = 0; // 总积分
  shareData = {}; // 分享数据
  flag = true; // 判断是否可以继续游戏
  initImgArr = [{ img: './images/reward.png' }, { img: './images/boom.png' }, { img: './images/boom1.png' }, { img: './images/sock_g.png' }, { img: './images/sock_w.png' }, { img: './images/snowboom.png' }, { img: './images/snowboom_part1.png' }, { img: './images/snowboom_part2.png' }];
  initCutImgArr = [{
    img: './images/cut.png',
    x: -60,
    y: 24,
    speedX: 3,
    speedY: -1,
    radiusW: 118,
    radiusH: 40
  }, {
    img: './images/add.png',
    x: 20,
    y: -15,
    speedX: null,
    speedY: 0,
    radiusW: 25,
    radiusH: 15
  }, {
    img: './images/part_red1.png',
    x: 0,
    y: 0,
    speedX: -1,
    speedY: 0,
    radiusW: 52.3,
    radiusH: 36
  }, {
    img: './images/part_red2.png',
    x: 0,
    y: 40,
    speedX: 1,
    speedY: 0,
    radiusW: 60,
    radiusH: 32
  }, {
    img: './images/part_sock_g1.png',
    x: 0,
    y: 0,
    speedX: -1,
    speedY: 0,
    radiusW: 34,
    radiusH: 43
  }, {
    img: './images/part_sock_g2.png',
    x: 0,
    y: 30,
    speedX: 1,
    speedY: 0,
    radiusW: 48,
    radiusH: 36
  }, {
    img: './images/part_sock_w1.png',
    x: 0,
    y: 0,
    speedX: -1,
    speedY: 0,
    radiusW: 34,
    radiusH: 43
  }, {
    img: './images/part_sock_w2.png',
    x: 0,
    y: 30,
    speedX: 1,
    speedY: 0,
    radiusW: 48,
    radiusH: 36
  }];

  imgArr = [];
  cutImgArr = [];
  isLoadingImg = false;
  redArr = []; // 红包/炸弹
  cutArr = []; // 切开的红包
  cutBoomArr = []; // 切开炸弹

  initData = {}; // 初始数据

  isHidden = false; // 退出当前页后不再制造红包
  hiddenCount = 0;
  carryTime = 0;

  hasRain = false;
  isInGIF = false; // 是否在GIF倒计时内
  picImg = null; // GIF图片路径
  gifTimer = null; // GIF倒计时
}

// 缓存图片为DOM元素，避免绘制canvas时候还需要异步读取图片
function loadImg(arr) {
  isLoadingImg = true;
  return new Promise(function (resolve) {
    var count = 0;

    var _loop = function _loop(i) {
      var image = new Image();
      image.onload = function () {
        count++;
        arr[i].img = image;
        count === arr.length && resolve();
      };
      image.src = arr[i].img;
    };

    for (var i = 0; i < arr.length; i++) {
      _loop(i);
    }
  });
}

function draws() {
  var arr = redArr.concat(cutArr, cutBoomArr);
  arr.forEach(function (item) {
    item.y = item.y + item.speedY;
    item.x = item.speedX ? item.x + item.speedX : item.x;
    ctx.drawImage(item.img, item.x, item.y, item.radiusW, item.radiusH || item.radiusW);
  });
}

function moves() {
  ctx.clearRect(0, 0, innerWidth, innerHeight);
  draws();
  animatTimer = requestAnimationFrame(moves);
}

function pushCoins() {
  // 每次随机生成1~3个红包/炸弹
  if (!isHidden) {
    var rand = randomRound(1, 3);
    var arr = [];
    for (var i = 0; i < rand; i++) {
      var isBoom = Math.random(0, 1) <= initData.bombPercent;
      var imgEl = null;
      if (initData.rainType === 'P') {
        imgEl = isBoom ? imgArr[1].img : imgArr[0].img;
      } else {
        imgEl = isBoom ? imgArr[2].img : Math.random(0, 1) <= 0.5 ? imgArr[0].img : imgArr[1].img;
      }
      // 创建新的红包对象
      var newRed = {
        x: randomRound(0, isBoom ? window.innerWidth - 45 : window.innerWidth - 60),
        y: 0 - randomRound(0, 150),
        radiusW: isBoom ? 45 : 52.3,
        radiusH: isBoom ? 45 : 60,
        img: imgEl,
        speedY: randomRound(1, 5)
      };
      arr.push(newRed);
    }
    // 每次都插入一批新红包对象arr到运动的红包数组redArr
    redArr = redArr.concat(arr);
    pushTimer = setTimeout(pushCoins, 600);
  }
}

function randomRound(min, max) {
  return Math.round(Math.random() * (max - min)) + min;
}

// 切开的红包
function cutPack(index) {
  var arr = [];
  var reg = /sock_g/g;
  if (initData.rainType === 'P') {
    arr = cutImgArr;
  } else {
    // arr = redArr[index].img.src.includes('sock_g') ? cutImgArr.slice(0, 4) : cutImgArr.slice(0, 2).concat(cutImgArr.slice(4, 6));
    arr = reg.test(redArr[index].img.src) ? cutImgArr.slice(0, 4) : cutImgArr.slice(0, 2).concat(cutImgArr.slice(4, 6));
  }
  arr.forEach(function (item) {
    // var params = Object.assign({}, item);
    var params = deepObject(item);
    params.x = params.x + redArr[index].x;
    params.y = params.y + redArr[index].y;
    params.speedX = params.speedX ? params.speedX + redArr[index].speedY : null;
    params.speedY = params.speedY + redArr[index].speedY;
    cutArr.push(params);
  });
  intergal++;
  document.getElementById('rainInterval').innerText = intergal;
  setTimeout(function () {
    cutArr = [];
  }, 100);
}

// 切到炸弹手机震动
function cutBoom(index) {
  if (initData.rainType === 'S') {
    cutBoomArr.push({
      img: cutImgArr[0].img,
      x: redArr[index].x - 60,
      y: redArr[index].y + 20,
      speedY: redArr[index].speedY - 1,
      radiusW: 118,
      radiusH: 40
    });
    cutBoomArr.push({
      img: imgArr[3].img,
      x: redArr[index].x,
      y: redArr[index].y + 10,
      speedX: -redArr[index].speedY,
      speedY: redArr[index].speedY,
      radiusW: 45,
      radiusH: 35
    });
    cutBoomArr.push({
      img: imgArr[4].img,
      x: redArr[index].x,
      y: redArr[index].y + 30,
      speedY: redArr[index].speedY,
      radiusW: 45,
      radiusH: 25
    });
  } else {
    cutBoomArr.push({
      img: imgArr[2].img,
      x: redArr[index].x - 15,
      y: redArr[index].y - 10,
      speedY: redArr[index].speedY,
      radiusW: 75,
      radiusH: 65
    });
  }
  intergal = intergal <= 0 ? 0 : intergal - 1;
  document.getElementById('rainInterval').innerText = intergal;
  setTimeout(function () {
    cutBoomArr = [];
  }, 100);
  if (member.sys === 'android') {
    navigator.vibrate = navigator.vibrate || navigator.webkitVibrate || navigator.mozVibrate || navigator.msVibrate;
    if (navigator.vibrate) {
      navigator.vibrate(500);
    }
  } else if (member.sys === 'ios') {
    callIntentTrailer('intentTrailerListNative', 'interactiveWithApp', JSON.stringify({ "linkType": 9 }));
  } else {
    alert('H5');
  }
}

function deepObject(obj){ //深拷贝
  var cloneObj = {};
  for(var key in obj){
      var objChild = Object.prototype.toString.call(obj[key]);
      cloneObj[key] = (objChild === '[object Array]' || objChild === '[object Object]') ? deepObject(obj[key]) : obj[key];
  }
  return cloneObj;
}

// 手指划到红包/炸弹则删除红包/炸弹 
canvas.addEventListener('touchmove', function (e) {
  var reg = /boom/g;
  var touchX, touchY, intervalX, intervalY;
  touchX = e.targetTouches[0].clientX;
  touchY = e.targetTouches[0].clientY;
  redArr && redArr.forEach(function (item, index) {
    // var isBoom = item.img.src.includes('boom');
    var isBoom = reg.test(item.img.src);
    var maxX = isBoom ? item.x + 45 : item.x + 52.3;
    var maxy = isBoom ? item.y + 45 : item.y + 60;
    intervalX = touchX >= item.x && touchX <= maxX;
    intervalY = touchY >= item.y && touchY <= maxy;
    if (intervalX && intervalY) {
      isBoom ? cutBoom(index) : cutPack(index);
      redArr.splice(index, 1);
    }
  });
});

// 关闭红包雨-结算
function closeCanvs() {
  if (isInGIF) {
    closeSettle();
    return;
  }
  flag = false;
  clearInterval(timer);
  if (hasRain) {
    $.ajax({
      url: '../../../../../api/y/couponRain/settle',
      type: 'post',
      data: JSON.stringify({
        reqData: {
          recordId: initData.recordId,
          memberId: member.id,
          score: intergal,
          system: member.sys == 'ios' ? 1 : 2
        },
        nonceStr: "OV5QOVX21A0446K8",
        signature: "7f8c4ad7f6d349cf28879670b9fe79b9"
      }),
      success: function success(res) {
        reset();
        if (res.returnData) {
          document.getElementById('showSettle').innerHTML = settlementModal(res.returnData);
          shareData = { shareAble: res.returnData.shareAble, shareContent: res.returnData.shareContent, wxPath: res.returnData.wxPath, shareCodeUrl: res.returnData.shareCodeUrl, wxSharePic: res.returnData.wxSharePic, originalId: res.returnData.originalId, xcxShareType: res.returnData.xcxShareType, wxShareContent: res.returnData.wxShareContent };
        }
      },
      error: function error(err) {
        console.log('err', err);
      }
    });
  } else {
    closeSettle();
  }
}

// 红包雨结算模板（奖励结算/没有奖励）
function settlementModal(data) {
  var str = '';
  var shareStr = data.shareAble ? '<p class="share-rain"  onclick="shareAgain()">分享游戏，可再获得一次机会<span></span></p>' : '';
  data.couponList.forEach(function (item) {
    str += item.amountType == 1 || item.amountType == 3 ? '<div class="coupon">\n        <div class="coupon-left">' + (item.integral || item.discount) + '<span>' + (item.amountType == 1 ? '积分' : '折扣') + '</span></div>\n        <div class="coupon-right">' + item.displayName + '</div>\n      </div>' : '<div class="coupon">\n        <div class="coupon-left"><span>\uFFE5</span>' + item.denomination + '</div>\n        <div class="coupon-right">' + item.displayName + '</div>\n      </div>';
  });
  var modalStr = data.packCount > 0 ? '<div class="red-rain">\n      <div class="settlement">\n        <p class="rain-grab">\u606D\u559C\u60A8\u4E2D\u5956\u4E86\uFF01</p>\n        <p>\u5FEB\u6765\u770B\u770B\u4F60\u7684\u5956\u52B1\u5427\uFF01</p>\n        <div class="coupon-list">\n          ' + str + '\n        </div>\n        <div class="settlement-bottom">\n          <div class="check-coupon" onclick="checkCoupon()">\u67E5\u770B\u6211\u7684\u4F18\u60E0\u5238</div>\n          ' + shareStr + '\n          <p class="next-rain">' + data.nextRainTipContent + '</p>\n          <p class="rain-rule" onclick="toRulePage(' + data.informationId + ')">\u6D3B\u52A8\u89C4\u5219</p>\n        </div>\n        <div class="close-settle" onclick="closeSettle()"></div>\n      </div>\n    </div>' : '<div class="red-rain">\n      <div class="rain-regret">\n        <div class="regret-bottom">\n          ' + shareStr + '\n          <p class="next-rain">' + data.nextRainTipContent + '</p>\n          <p class="rain-rule" onclick="toRulePage(' + data.informationId + ')">\u6D3B\u52A8\u89C4\u5219</p>\n        </div>\n        <div class="close-settle" onclick="closeSettle()"></div>\n      </div>\n    <div>';
  return modalStr;
}

// 倒计时
function countdownClose() {
  clearInterval(timer);
  document.getElementsByClassName('rain-countdown')[0].innerHTML = '<span>\u5269</span>\n    <span id="countNum">' + initData.gameSeconds + '</span>\n  <span>S</span>';
  timer = setInterval(function () {
    initData.gameSeconds--;
    document.getElementById('countNum').innerText = initData.gameSeconds;
    if (initData.gameSeconds <= 0) {
      initData.gameSeconds = 0;
      clearInterval(timer);
      closeCanvs();
    }
  }, 1000);
}

// 获取数据recordId
function getrecordId() {
  $.ajax({
    url: '../../../../../api/y/couponRain/participate',
    type: 'post',
    data: JSON.stringify({
      reqData: {
        couponRainId: initData.id,
        memberId: member.id,
        system: member.sys == 'ios' ? 1 : 2
      },
      nonceStr: "OV5QOVX21A0446K8",
      signature: "7f8c4ad7f6d349cf28879670b9fe79b9"
    }),
    success: function success(res) {
      res.returnCode == '0000' && (initData.recordId = res.returnData.recordId) || showNoRainModal(res.returnMsg);
    },
    error: function error(err) {
      console.log('err', err);
    }
  });
}

// 已参与过红包雨或者不能参与该红包雨
function showNoRainModal(msg) {
  document.getElementById('inRedRain').style.opacity = 0;
  document.getElementById('noredTxt').innerText = msg || '红包雨活动尚未开始';
  document.getElementsByClassName('red-rain-end')[0].style.display = 'block';
}

// 分享后返回当前页面显示GIF五秒倒计时
function showGIF() {
  document.getElementsByClassName('show-git')[0].innerHTML = '\n    <img src="' + picImg + '" />\n    <div class="close-settle close-gif" onclick="closeGIF()"></div>\n  ';
  document.getElementsByClassName('show-git')[0].style.display = 'block';
  gifTimer = setTimeout(closeGIF, 5000);
}

function closeGIF() {
  isInGIF = false;
  clearTimeout(gifTimer);
  document.getElementsByClassName('show-git')[0].style.display = 'none';
  document.getElementById('inRedRain').style.opacity = 1;
  init();
}

function init() {
  if (isInGIF) return;
  if (flag == true) {
    flag = false;
    clearInterval(timer);
    $.ajax({
      url: '../../../../../api/n/couponRain',
      type: 'post',
      data: JSON.stringify({
        reqData: {
          memberId: member.id,
          system: member.sys == 'ios' ? 1 : 2
        },
        nonceStr: "OV5QOVX21A0446K8",
        signature: "7f8c4ad7f6d349cf28879670b9fe79b9"
      }),
      success: function success(res) {
        document.getElementById('inRedRain').style.opacity = 1;
        var returnData = res.returnData;
        var couponRain = res.returnData.couponRain;
        var systemCurrentTime = returnData.systemCurrentTime;
        var startTime = couponRain && couponRain.startTime;
        var endTime = couponRain && couponRain.endTime;
        if (returnData.rainType === 'P') {
          document.getElementsByClassName('rain-total')[0].innerHTML = '<img src="./images/total.png" />\n          <span class="rain-total-x">x</span>\n          <span id="rainInterval">0</span>';
          imgArr = initImgArr.slice(0, 3);
          cutImgArr = initCutImgArr.slice(0, 4);
        } else {
          document.getElementsByClassName('rain-total')[0].innerHTML = '<img src="./images/sock_total.png" />\n          <span class="rain-total-x">x</span>\n          <span id="rainInterval">0</span>';
          imgArr = initImgArr.slice(3);
          cutImgArr = initCutImgArr.slice(0, 2).concat(initCutImgArr.slice(4));
        }
        hasRain = returnData.rain && systemCurrentTime >= startTime && systemCurrentTime <= endTime;
        if (hasRain) {
          picImg = couponRain.pic;
          initData = { id: couponRain.id, gameSeconds: couponRain.gameSeconds, rainType: returnData.rainType, bombPercent: parseFloat(couponRain.bombPercent) };
          getrecordId();
          clearTimeout(pushTimer);
          pushCoins();
          moves();
          countdownClose();
        } else {
          showNoRainModal(returnData.noRainTip);
        }
      },
      error: function error(e) {
        console.log('err', e);
      }
    });
  }
}

function reset() {
  cancelAnimationFrame(animatTimer);
  ctx.clearRect(0, 0, window.innerWidth, window.innerHeight);
  clearTimeout(pushTimer);
  intergal = 0;
  document.getElementById('rainInterval').innerText = intergal;
  animatTimer = null;
  timer = null;
  redArr = [];
  cutArr = [];
  cutBoomArr = [];
  document.getElementById('inRedRain').style.opacity = 0;
}

// 关闭结算
function closeSettle() {
  reset();
  document.getElementsByTagName('body')[0].innerHTML = '';
  if (member.sys === 'android') {
    window.H5PluginManager.interactiveWithApp(JSON.stringify({ "linkType": 4 }));
  } else if (member.sys === 'ios') {
    callIntentTrailer('intentTrailerListNative', 'interactiveWithApp', JSON.stringify({ "linkType": 4 }));
  } else {
    alert('H5');
  }
}

// 查看我的优惠券
function checkCoupon() {
  if (member.sys === 'android') {
    window.H5PluginManager.interactiveWithApp(JSON.stringify({ "linkType": 6 }));
  } else if (member.sys === 'ios') {
    callIntentTrailer('intentTrailerListNative', 'interactiveWithApp', JSON.stringify({ "linkType": 6 }));
  } else {
    alert('H5');
  }
}

// 分享
function shareAgain() {
  isInGIF = true;
  document.getElementById('inRedRain').style.opacity = 0;
  var params = JSON.stringify({
    "linkType": 7,
    "linkValue": shareData
  });
  if (member.sys === 'android') {
    window.H5PluginManager.interactiveWithApp(params);
  } else if (member.sys === 'ios') {
    callIntentTrailer('intentTrailerListNative', 'interactiveWithApp', params);
  } else {
    alert('H5');
  }
}

// 活动规则
function toRulePage(informationId) {
  var params = JSON.stringify({
    "linkType": 8,
    "linkValue": { informationId: String(informationId) }
  });
  if (member.sys === 'android') {
    window.H5PluginManager.interactiveWithApp(params);
  } else if (member.sys === 'ios') {
    callIntentTrailer('intentTrailerListNative', 'interactiveWithApp', params);
  } else {
    alert('H5');
  }
}

// 监听是否在当前红包雨页面，如果不在则停止倒计时
document.addEventListener('visibilitychange', function () {
  hiddenCount++;
  isHidden = window.document.hidden || window.document.mozHidden || window.document.msHidden || window.document.webkitHidden; // 判断是否在红包雨页面
  if (isHidden) {
    carryTime = new Date().getTime();
    clearInterval(timer);
  } else if (hiddenCount > 1) {
    var nowTimes = new Date().getTime();
    initData.gameSeconds = initData.gameSeconds && initData.gameSeconds - parseInt((nowTimes - carryTime) / 1000) || null;
    if (initData.gameSeconds === null || isInGIF) {
      return;
    }
    if (initData.gameSeconds <= 0) {
      initData.gameSeconds = 0;
      clearInterval(timer);
      closeCanvs();
    } else {
      countdownClose();
      clearTimeout(pushTimer);
      pushCoins();
    }
  }
});
// =========================== APP api =================================

// 获取(用户id, token, web或app, ios或anz)
function getMemberId(mid, tok, web, sys, net) {
  if (!canvas.width) {
    resetInitData();
  }
  mid && (member.id = mid);
  tok && (member.token = tok);
  web && (member.web = web.toLowerCase());
  sys && (member.sys = sys.toLowerCase());
  net && (member.net = net);
  $.ajax({
    url: '../../../../../api/z/appLogin',
    type: 'post',
    data: JSON.stringify({
      reqData: {
        memberId: mid
      },
      token: tok,
      nonceStr: "OV5QOVX21A0446K8",
      signature: "7f8c4ad7f6d349cf28879670b9fe79b9"
    }),
    success: function success(e) {
      if (isLoadingImg) {
        init();
      } else {
        Promise.all([loadImg(initImgArr), loadImg(initCutImgArr)]).then(function () {
          init();
        });
      }
    },
    error: function error(err) {
      console.log(err);
    }
  });
}

// ios跳转
function callIntentTrailer(handlerInterface, handlerMethod, parameters) {
  var dic = { 'handlerInterface': handlerInterface, 'function': handlerMethod, 'parameters': parameters };

  if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
    window.webkit.messageHandlers[handlerInterface].postMessage(dic);
  } else {
    window[handlerInterface][handlerMethod](JSON.stringify(dic));
  }
}

// 分享后再来一次
function couponRainShare(type) {
  if (type == 1) {
    document.getElementById('showSettle').innerHTML = '';
    $.ajax({
      url: '../../../../../api/y/couponRain/logShare',
      type: 'post',
      data: JSON.stringify({
        reqData: {
          memberId: member.id,
          couponRainId: initData.id,
          system: member.sys == 'ios' ? 1 : 2
        },
        nonceStr: "OV5QOVX21A0446K8",
        signature: "7f8c4ad7f6d349cf28879670b9fe79b9"
      }),
      success: function success(res) {
        res.returnData.gameAgainAble && (flag = true) && showGIF();
      },
      error: function error(err) {
        console.log(err);
      }
    });
  }
}

function getH5PageType() {}

function getAppParams() {}

function checkInReminderCallback() {}

function appUnloadAddPv() {}
