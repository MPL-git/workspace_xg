require.config({
	paths: {
		'vticker': 'jquery.vticker.min', // 纵向滚动
		'swiper': 'swiper.min', // 滑动
		'ceiling': 'plug-ceiling', // 自定义吸顶
		'clipboard': 'clipboard.min', // 复制
		'distpicker': 'distpicker.min', // 地址
		'template': 'template-web-min', // 模板
		'jweixin': 'jweixin-1.2.0', // 微信jdk
		'md5': 'md5.min', // md5加密 (注册)
		'qpTime': 'qpTime', // 倒计时 - 迷你版
		'douyin': 'douyin', // 抖音
		'multipicker': 'multiPicker' // 抖音
	}
});


window.HELP_IMPROVE_VIDEOJS = false;

var _userAgent = navigator.userAgent,
	isWeiXin = _userAgent.toLowerCase().match(/MicroMessenger/i) == 'micromessenger',
	isAndroid = _userAgent.indexOf('Android') > -1 || _userAgent.indexOf('Adr') > -1,
	isiOSX = /iphone/gi.test(navigator.userAgent) && (screen.height == 812 && screen.width == 375),
	isiOS = !!_userAgent.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/),
	isWeibo = _userAgent.toLowerCase().match(/weibo/i) == 'weibo',
	isQQ = !!_userAgent.toLowerCase().match(/mqqbrowser|qzone|qqbrowser/i),
	isxgbuy = /xgbuy/.test(_userAgent),
	_agentSplit = _userAgent.split(' '),

	win_h = window.innerHeight,
	win_w = window.innerWidth,

	win_ls = window.sessionStorage,
	win_lc = window.localStorage,

	redirect_url = '?redirect_url=', // URL分割标记
	reg_cache_img = new RegExp('[^/]+(?!.*/)'), // 缓存图片规则
	global_douyin = '', // 抖音
	douyin_track = '', // 抖音跟踪id
	douyin_convert1 = '', // 抖音转化id (下单)
	douyin_convert2 = '', // 抖音转化id (下单成功)
	douyin_delay = 2000, // 抖音转化下单成功返回未下单, 延迟2秒继续请求
	template = '', // 模板
	j_wx = '', // jweixin
	iosVersion = '', // ios版本

	android_pay_fix = 1, // 安卓第一次延迟支付
	initial_title = '醒购商城',
	page_body = $('body'),
	page_load = $('.loading'),
	page_footer = $('#footer'),
	page_section = $('#section'),

	pop_state = true, // 是否监听路由, 解决只在"返回"时监听路由
	post_id = '', // 请求页面id
	lead_url = '', // 前部分路径 (../webApp/xgbuy/)
	prev_url = getStorage('prev_url') || '', // 上一个页面地址
	first_url = '', // 首次访问的链接, 解决首次加载页面不推入历史 <不包括跳过历史记录的页面>
	real_url = '', // 真正的路径 (未加密, 初始加载页面要在$(fn{})中获取正确路径)
	search_url = '', // 链接携带的数据串

	member = {
		id: '',
		token: '',
		app: false, // 是否APP
		sys: script_index.getAttribute('data-sys'), // 系统版本, 默认web, ios, android
		psys: isWeiXin ? 4 : 3, // 提交的版本, ios>1, and>2, web>3, wx>4
		appver: 0, // 安卓服务器版本号, iosAPP版本号(新版本由iosver取代)
		iosver: undefined, // ios服务器版本号
		isSvip: '', // 判断是都是svip
		webver: script_index.getAttribute('data-ver'), // web版本号, 更新主脚本和本地缓存及APP缓存
		mver: _agentSplit[4].replace('WOW', 'Window').replace(')', '/') + _agentSplit[9], // 手机版本
		ip: '',
		list: '' // 优惠券列表数据
	},
	queryMember = {
		id: '',
		token: ""
	}

var update_cart; // 更新购物车
getAppAgent();

!win_lc && (win_lc = win_ls);

// isiOS && (iosVersion = parseInt(_userAgent.toLowerCase().match(/cpu iphone os (.*?) like mac os/)[1].replace(/_/g, ".")));
if (isiOS) {
	if (navigator.userAgent.indexOf("iPhone") != -1) {
		// iosVersion = parseInt(_userAgent.toLowerCase().match(/cpu iphone os (.*?) like mac os/)[1].replace(/_/g, "."))
		iosVersion = parseInt(_userAgent.toLowerCase().match(/cpu iphone os (.*?) like mac os/)[1].replace(/_/g, "."))
	}
	if (navigator.userAgent.indexOf("iPad") != -1) {
		// iosVersion = parseInt(_userAgent.toLowerCase().match(/cpu os (.*?) like mac os x/)[1].replace(/_/g, "."))
		iosVersion = parseInt(_userAgent.toLowerCase().match(/cpu os (.*?) like mac os/)[1].replace(/_/g, "."))
	}
}

var getApi = {
	adInfo: '../../api/n/getAdInfoList', // home 首页
	homeCategory: '../../api/n/getHomeCategory',
	// getHomeThirdPlate: '../../api/n/getHomeThirdPlate', // 新用户, 签到, 砍价 (废弃)
	activityCustom: '../../api/n/getTodayPreferentialActivityList', // (活动列表)
	getActivityBrandGroupActivityList: '../../api/n/getActivityBrandGroupActivityList', // 品牌团
	getBrandGroupCategoryAds: '../../api/n/getBrandGroupCategoryAds', // 品牌团 - 广告装修
    getCategoryBrandGroup: '../../api/n/getCategoryBrandGroup', // 品牌团 - 特卖列表

	addMemberFeedback: '../../api/y/addMemberFeedback', // 意见反馈

	newEnjoy: '../../api/n/getSingleNewEnjoyActivityList', // 新人专享
	newEnjoyActivity: '../../api/n/getSingleNewEnjoyActivityContent',
	couponEnjoy: '../../api/y/addCouponAndView20170731',

	expressInfo: '../../api/y/getExpressInfo', // 获取快递信息 (废弃)
	getExpressInfoBySubOrderId: '../../api/y/getExpressInfoBySubOrderId', // 获取快递信息

	seckillTimeTab: '../../api/n/getSeckillTimeTab', // single-seckill 限时秒杀
	// seckillTime: '../../api/n/getSeckillTimeList',
	seckillNewTime: '../../api/n/getNewSeckillTimeList',
	getBrandGroupProductList: '../../api/n/getBrandGroupProductList', // 品牌团秒杀

	seckillTimeUser: '../../api/n/getNewUserSeckillTimeList', // 新用户秒杀
	getAdInfos: '../../api/n/getAdInfos', // 新用户秒杀 banner

	userOrderCount: '../../api/y/getUserOrderCount', // my 个人中心

	userInfo: '../../api/y/getUserInfo', // set-self 个人资料
	updateUserInfo: '../../api/y/updateUserInfo', // (register-nick 修改昵称)
	updateUserInfoH5: '../../api/z/updateUserInfoH5', // (是否开通签到通知)
	uploadPic: '../../api/y/uploadPic', // (上传头像)
	uploadUserPic: '../../api/y/uploadUserPic', // (上传头像)
	bindingWeChat: '../../api/y/bindingWeChat', // 关联微信
	saveMemberAreaInfo: '../../api/y/saveMemberAreaInfo',//地区绑定

	getIntegralDtlList: '../../api/y/getIntegralDtlList', // integral 我的积分
	getSingleIntegralMallActivityList: '../../api/n/getSingleIntegralMallActivityList', // 积分商城列表
	getSingleProductActivityCustomsByProductTypeId: '../../api/n/getSingleProductActivityCustomsByProductTypeId', // 积分商城列表


	footPrint: '../../api/y/getMemberFootprintList', // history 我的足迹
	deleteFootPrint: '../../api/y/deleteMemberFootprint', // 清空
	addFootPrint: '../../api/y/addMemberFootprint', // 添加

	memberCoupon: '../../api/y/getMemberCouponList', // coupon 我的优惠券
	getMemberCouponCounts: '../../api/y/getMemberCouponCounts', // 优惠券数量
	addReceiveCoupon: '../../api/y/addReceiveCoupon', // 优惠券
	addReceiveCouponH5: '../../api/z/addReceiveCoupon',

	getMemberAddressList: '../../api/y/getMemberAddressList', // address 收货地址
	getMemberAddressListH5: '../../api/z/getMemberAddressListH5',

	defaultAddress: '../../api/y/updateDefaultAddress', // 默认
	deleteAddress: '../../api/y/deleteMemberAddress', // 删除
	updateAddress: '../../api/y/updateMemberAddress', // 编辑
	addAddress: '../../api/y/addMemberAddress', // 新增

	// preheatCategory: '../../api/n/getActivityPreheatCategory', // advance 预热
	preheatList: '../../api/n/getActivityTrailerList',
	addRemindSale: '../../api/y/addRemindSale', // 新增开售提醒
	addRemindSaleH5: '../../api/z/addRemindSaleH5',

	deleteRemindSale: '../../api/y/deleteRemindSale', // 删除开售提醒
	deleteRemindSaleH5: '../../api/z/deleteRemindSaleH5',

	// remindSale: '../../api/y/getRemindSaleList', // advance-remind 开售提醒 (废弃)
	remindSale: '../../api/y/getRemindSaleRecordList', // advance-remind 开售提醒

	getActivityProductList: '../../api/n/getActivityProductList', // single-hot
	getProductScreeningConditions: '../../api/n/getProductScreeningConditions',

	getSingleScreeningConditions6: '../../api/n/getSingleScreeningConditions6', // 断码清仓 - tab栏
	getTwoCategoryProductSize: '../../api/n/getTwoCategoryProductSize', // 尺码
	getSingleBrokenCodeClearingActivityList: '../../api/n/getSingleBrokenCodeClearingActivityList',

	getIndividualActivityByAreaId: '../../api/n/getIndividualActivityByAreaId', // single-def
	getProductScreeningConditionsByActivityId: '../../api/n/getProductScreeningConditionsByActivityId',
	getActivityAreaCoupons: '../../api/n/getActivityAreaCoupons', // 获取优惠券列表
	getCouponListByCouponModuleTime: '../../api/n/getCouponListByCouponModuleTime',//优惠券秒杀

	explosionActivity: '../../api/n/getSingleExplosionActivityList', // single-explosion
	getFourCategories: '../../api/n/getFourCategories', // 单品爆款 - 四大品类
	// explosionScreen: '../../api/n/getSingleActivityScreen', // (废弃)
	// explosionNewActivity: '../../api/n/getSingleNewExplosionActivityList', // 停用

	getBrandActivityByAreaId: '../../api/n/getBrandActivityByAreaId', // brand-def

	getAdByproductTypeId: '../../api/n/getAdByproductTypeId', // brand-custom

	shoppingCart: '../../api/y/getShoppingCart', // cart 购物车
	payOrderInfo: '../../api/y/gerPayOrderInfo',
	editShoppingCart: '../../api/y/editShoppingCart',
	shoppingCartNum: '../../api/y/getShoppingCartNum', // 购物车件数 (停用)

	orderPay: '../../api/y/submitOrderPayment', // cart-detail 购物车订单详情
	orderDt: '../../api/y/getOrderDtlList', // 全部订单

	orderDtlInfo: '../../api/y/getOrderDtlInfoList', // order-detail 订单详情
	confirmReceiptOrderById: '../../api/y/confirmReceiptOrderById', // 确认收货
	deleteOrderById: '../../api/y/deleteOrderById', // 删除订单
	cancelOrderById: '../../api/y/cancelOrderById', // 取消订单
	payType: '../../api/y/getPayType', // 去支付
	submitAfterPay: '../../api/y/submitAfterPayment', // 提交待付款订单
	addRemindDelivery: '../../api/y/addRemindDelivery', // 提醒发货

	// productInfo: '../../api/n/getProductInfoList', // goods-detail 商品详情 (废弃)
	productInfo: '../../api/n/getProductInfo', // goods-detail 商品详情
	productDetail: '../../api/n/getProductDetail', // 图文详情
	productItems: '../../api/n/getProductItemsById', // 规格sku
	addShoppingCart: '../../api/y/addShoppingCart', // 加入购物车/立即购买
	activityCoupon: '../../api/y/getCouponByActivityAreaId', // 获取优惠券
	getProductFreightTemplate: '../../api/n/getProductFreightTemplate', // 运费
	getAreaByParentId: '../../api/n/getAreaByParentId', // 省市区地址

	login: '../../api/n/login', // register-login 登录
	loginout: '../../api/y/loginOut', // 退出
	loginNow: '../../api/n/mobileLogin', // register-login 快速登录

	memberRegister: '../../api/n/memberRegister', // register 注册
	registerCode: '../../api/n/getMobileVerificationCode4Register',
	protocol: '../../n/platform/protocol', // 注册协议

	mobileCode: '../../api/n/getMobileVerificationCode4ResetPassword', // register-code 获取验证码
	verifiCode: '../../api/n/verificationSMSCode',
	verifiCodeNow: '../../api/n/getMobileLoginCode', // register-login 快速登录获取验证码
	getCaptcha: '../../getCaptcha', // 获取图片验证码

	verificationCode: '../../api/n/getVerificationCode', // register-mobile 绑定手机
	updateBindMobile: '../../api/y/updateBindMobile',

	visitorsRegisteredMembers: '../../api/y/visitorsRegisteredMembers', // 绑定手机(需要密码)
	visitorsRegisteredMembersH5: '../../api/z/visitorsRegisteredMembersH5',

	resetPassword: '../../api/n/resetPassword', // register-reset 重置密码

	updatePassword: '../../api/y/updatePassword', // register-revise 修改密码
	setPassword: '../../api/y/setPassword', // register-revise 设置密码

	getH5ProductInfoList: '../../api/n/getH5ProductInfoList', // 双11
	areaTempletCode: '../../api/n/getActivityAreaTempletCode', // 获取会场模板
	areaTempletByAreaId: '../../api/n/getActivityAreaTemplateByAreaId', // 获取会场模板(类型1)

	brandDecorate: '../../api/n/getHomePageDecorateInfo', // 会场装修自定义模块
	customerpage: '../../api/n/getCustomerPage', // 自建页面
	decorationMeeting: '../../api/n/getDecorationMeeting', // 会场装修
	getModuleItemListByProductModuleTypeId: '../../api/n/getModuleItemListByProductModuleTypeId',
	getProductList: '../../api/n/getProductList', // 会场装修分页(4)
	getActivityList: '../../api/n/getActivityList', // 会场装修分页(5)
	getDecorateInfoPage: '../../api/n/getDecorateInfoPage', // 会场装修分页(infoid)
	getBgProductInfo: '../../api/n/getBgProductInfo', // 会场装修分页(必购)
	getTopSecKillProductList: '../../api/n/getTopSecKillProductList', // 会场装修分页(秒杀)

	getMemberSignInPage: '../../api/n/getMemberSignInPage', // 现金签到
	getMemberSignInPageH5: '../../api/n/getMemberSignInPageH5',
	addMemberSignIn: '../../api/y/addMemberSignIn', // 签到领现金红包
	addMemberSignInH5: '../../api/z/addMemberSignInH5',
	addReceiveExtraAmount: '../../api/y/addReceiveExtraAmount', // 立即领取红包现金
	addReceiveExtraAmountH5: '../../api/z/addReceiveExtraAmountH5',
	getShareFriendSignIn: '../../api/y/getShareFriendSignIn', // 好友签到记录
	getShareFriendSignInH5: '../../api/z/getShareFriendSignInH5',
	getSignInBroadcastContent: '../../api/n/getSignInBroadcastContent', // 文字轮播
	getSignInBroadcastContentH5: '../../api/n/getSignInBroadcastContentH5',

	getWithdrawCashtraPage: '../../api/y/getWithdrawCashtraPage', // 提现配置
	getWithdrawCashtraPageH5: '../../api/z/getWithdrawCashtraPageH5',
	getSignInOrDrawRecords: '../../api/y/getSignInOrDrawRecords', // 红包明细
	getSignInOrDrawRecordsH5: '../../api/z/getSignInOrDrawRecordsH5',

	addMemberWithdrawCash: '../../api/y/addMemberWithdrawCash', // 提现
	addMemberWithdrawCashH5: '../../api/z/addMemberWithdrawCashH5',

	getSportList: '../../api/y/getSportList', // 世界杯比赛场次
	getSportListH5: '../../api/z/getSportListH5',
	addSportGuess: '../../api/y/addSportGuess', // 世界杯比赛竞猜
	addSportGuessH5: '../../api/z/addSportGuessH5',

	getSportGuessRecord: '../../api/y/getSportGuessRecord', // 世界杯比赛竞猜记录
	getSportGuessRecordH5: '../../api/z/getSportGuessRecordH5',
	getIntegralTask: '../../api/n/getIntegralTask', // 赚取积分
	getIntegralTaskH5: '../../api/n/getIntegralTaskH5',
	addMemberIntegralTask: '../../api/y/addMemberIntegralTask', // 领取500积分
	addMemberIntegralTaskH5: '../../api/z/addMemberIntegralTaskH5',

	getShoppingMallData: '../../api/n/getShoppingMallData', // 商城 (废弃)

	getMchtShoppingInfo: '../../api/n/getMchtShoppingInfo', // 店铺信息
	getMchtShoppingProductList: '../../api/n/getMchtShoppingProductList', // 店铺商品信息
	getMchtShoppingProductClass: '../../api/n/getMchtShoppingProductClass', // 店铺商品分类

	getBargainGoodsList: '../../api/n/getBargainGoodsList', // 砍价
	getBargainGoodsListH5: '../../api/n/getBargainGoodsListH5',
	getBargainGoodsTopInfo: '../../api/n/getBargainGoodsTopInfo', // 文字轮播
	getMyBargainGoodsInfo: '../../api/y/getMyBargainGoodsInfo', // 我参与的砍价商品
	getMyBargainGoodsInfoH5: '../../api/z/getMyBargainGoodsInfoH5',
	addMemberCutOrderInfo: '../../api/y/addMemberCutOrderInfo', // 砍一刀
	addMemberCutOrderInfoH5: '../../api/z/addMemberCutOrderInfoH5',
	getMyCutShareInfo: '../../api/y/getMyCutShareInfo', // 分享页
	getMyCutShareInfoH5: '../../api/z/getMyCutShareInfoH5',

	getMyAssistanceCutPriceShareInfo: '../../api/y/getMyAssistanceCutPriceShareInfo',//助力减价 过渡页分享

	addFriendFinishTask: '../../api/y/addFriendFinishTask', // 邀请免费拿
	addFriendFinishTaskH5: '../../api/z/addFriendFinishTaskH5',
	getMyInviteShareInfo: '../../api/y/getMyInviteShareInfo', // 分享页
	getMyInviteShareInfoH5: '../../api/z/getMyInviteShareInfoH5',
	getInviteDetail: '../../api/y/getInviteDetail', // 邀请详情
	getInviteDetailH5: '../../api/z/getInviteDetailH5',

	addReceiveCoupon618: '../../api/y/addReceiveCoupon618', // 618活动 (停用)
	addReceiveCoupon618H5: '../../api/z/addReceiveCoupon618H5',
	getActivityInfo618: '../../api/n/getActivityInfo618', // (停用)

	getWecharShareSign: '../../api/n/getWecharShareSign', // 微信传url

	getHelpTagList: '../../api/n/getHelpTagList', // 帮助中心
	getHelpItemList: '../../api/n/getHelpItemList', // 详情
	searchHelpItemList: '../../api/n/searchHelpItemList', // 搜索

	mchtUploadPic: '../../api/n/mchtUploadPic', // 招商入驻上传图片

	getCanCommentOrderDtl: '../../api/y/getCanCommentOrderDtl', // 评价商品信息
	addMemberComment: '../../api/y/addMemberComment', // 提交评价
	addMemberCommentDraw: '../../api/y/addMemberCommentDraw', // 刮一刮
	getOrderCommentList: '../../api/y/getOrderCommentList', // 我的评价
	getAppendCommentProduct: '../../api/y/getAppendCommentProduct', // 追评商品信息
	getLuckDrawRule: '../../api/n/getLuckDrawRule', // 刮一刮规则
	getUserProductAllComment: '../../api/n/getUserProductAllComment', // 商品的全部评价
	updateOrderComment: '../../api/y/updateOrderComment', // 修改评价

	getShoppingMallCategory: '../../api/n/getShoppingMallCategory', // 新版商城, 左侧
	getShoppingMallCategoryData: '../../api/n/getShoppingMallCategoryData', // 右侧
	addHotSearchWord: '../../api/n/addHotSearchWord', // 新增热词
	getHotSearchWord: '../../api/n/getHotSearchWord', // 获取热词
	getShoppingMallProductListData: '../../api/n/getShoppingMallProductListData', // 商品列表
	getShopMallProductScreeningConditions: '../../api/n/getShopMallProductScreeningConditions', // 商品筛选
	getShopMallProductBrandList: '../../api/n/getShopMallProductBrandList', // 品牌筛选
	getCategoryList: '../../api/n/getCategoryList', // 分类筛选

	getInformation: '../../api/n/getInformation', // 隐私政策

	getSignInHomePage: '../../api/n/getSignInHomePage', // 新版本签到 (页面)
	getSignInHomePageH5: '../../api/n/getSignInHomePageH5',
	addMemberNewSignIn: '../../api/y/addMemberNewSignIn', // 立即签到
	addMemberNewSignInH5: '../../api/z/addMemberNewSignInH5',
	getProductSkuInfo: '../../api/n/getProductSkuInfo', //获取商品sku信息
	getSignInPayInfo: '../../api/y/getSignInPayInfo', // 获取签到商品结算页面
	getSignInPayInfoH5: '../../api/z/getSignInPayInfoH5',
	addTiredSignGift: '../../api/y/addTiredSignGift', // 获取累签奖品
	addTiredSignGiftH5: '../../api/z/addTiredSignGiftH5',
	getSupplementarySignTaskList: '../../api/n/getSupplementarySignTaskList', //获取补签任务列表
	getMySupplementarySignTaskList: '../../api/y/getMySupplementarySignTaskList', //获取补签任务列表
	getMySupplementarySignTaskListH5: '../../api/z/getMySupplementarySignTaskListH5', //获取补签任务列表
	memberSupplementarySignInId: '../../api/n/memberSupplementarySignInId', // 获取我领取补签任务列表
	getMyAssistanceDtlList: '../../api/y/getMyAssistanceDtlList', // 获取我的任务邀请详情列表
	getMyAssistanceDtlListH5: '../../api/z/getMyAssistanceDtlListH5',
	getMySupplementarySignSharePage: '../../api/y/getMySupplementarySignSharePage', //获取我的补签分享页面
	getMySupplementarySignSharePageH5: '../../api/z/getMySupplementarySignSharePageH5',
	addSupplementarySignTask: '../../api/y/addSupplementarySignTask', //领取补签任务
	addSupplementarySignTaskH5: '../../api/z/addSupplementarySignTaskH5',
	addMemberAssistanceDtl: '../../api/y/addMemberAssistanceDtl', // 好友助力
	addMemberAssistanceDtlH5: '../../api/z/addMemberAssistanceDtlH5',

	getThirdPartyProductBaseInfo: '../../api/n/getThirdPartyProductBaseInfo', //获取第三方平台商品详情页面数据
	getCommonProductBaseInfo: '../../api/n/getCommonProductBaseInfo', //获取签到商品商品详情 (商品无状态判断)

	getShopDecorateInfo: '../../api/n/getShopDecorateInfo', // 店铺装修信息页面
	getShopProductList: '../../api/n/getShopProductList', // 店铺商品列表
	getShopActivityList: '../../api/n/getShopActivityList', // 店铺特卖列表
	getShopProductScreeningConditions: '../../api/n/getShopProductScreeningConditions', // 店铺商家商品筛选项
	getMchtShopInfo: '../../api/n/getMchtShopInfo', // 店铺商家信息
	getMchtGSQualifications: '../../api/y/getMchtGSQualifications', // 店铺商家工商资质信息
	getMchtGSQualificationsH5: '../../api/z/getMchtGSQualificationsH5',

	getPayDepositOrderInfo: '../../api/y/getPayDepositOrderInfo', // 预售定金购买信息页(确认订单)
	getSubDepositOrderList: '../../api/y/getSubDepositOrderList', // 获取预售定金列表
	submitDepositOrderPayment: '../../api/y/submitDepositOrderPayment', // 预售定金支付，获取预支付信息
	submitDepositAfterPayment: '../../api/y/submitDepositAfterPayment', // 预售定金支付失败，重新支付
	updateSubDepositOrder: '../../api/y/updateSubDepositOrder', // 取消预售定金子订单

	getProductTypeByParentId: '../../api/n/getProductTypeByParentId', // 招商入驻, 品类

	getBargainGoodsListH5: '../../api/n/getBargainGoodsListH5', // 获取砍价商品列表 H5
	getMyAssistanceGoodsTaskList: '../../api/y/getMyAssistanceGoodsTaskList', // 我领取的助力大减价列表
	getMyAssistanceGoodsTaskListH5: '../../api/z/getMyAssistanceGoodsTaskListH5',
	getMyAssistanceCutPriceShareInfo: '../../api/y/getMyAssistanceCutPriceShareInfo', // 助力减价分享页面
	getMyAssistanceCutPriceShareInfoH5: '../../api/z/getMyAssistanceCutPriceShareInfoH5',
	updateProdutStock: '../../api/y/updateProdutStock', // 助力减价分享更新库存
	addCutLinkClickLog: '../../api/y/addCutLinkClickLog', // 助力大减价分享链接点击日志

	getCatalogList: '../../api/y/getCatalogList', // 获取规则(about, rule)
	delShoppingCartList: '../../api/y/delShoppingCartList', // 清空购物车

	getMemberOrderPaySuccess: '../../api/y/getMemberOrderPaySuccess', // 订单是否支付成功

	// 新星计划
	getNovaPlanIndex: '../../api/y/getNovaPlanIndex', // 首页
	getSubMemberHistoryOrderList: '../../api/y/getSubMemberHistoryOrderList', // 推广订单
	updateMemberInvitationCode: '../../api/y/updateMemberInvitationCode', // 输入邀请码 (invitationCode)
	addReadStrategyIntegral: '../../api/y/addReadStrategyIntegral', // 攻略 - 积分
	addReadStrategyIntegralH5: '../../api/z/addReadStrategyIntegralH5',
	getNovaPlanHelpCenter: '../../api/y/getNovaPlanHelpCenter', // 攻略 - 列表
	getNovaPlanHelpCenterH5: '../../api/z/getNovaPlanHelpCenterH5',
	getMemberRenewalTaskProgress: '../../api/y/getMemberRenewalTaskProgress', // 续签任务
	getProductCategoryManage: '../../api/n/getProductCategoryManage', // 好货推荐 - 导航 (type: 3, 每日好店: 2, 每日好货: 1)
	getProductCategoryAdManage: '../../api/n/getProductCategoryAdManage', // 好货推荐 - 广告
	getGoodEveryDayProductList: '../../api/n/getGoodEveryDayProductList', // 好货推荐 - 列表
	getMchtShopDynamicList: '../../api/y/getMchtShopDynamicList', // 个人中心 - 推荐 | 关注
	addMemberDynamicForward: '../../api/y/addMemberDynamicForward', // 转发
	addMemberDynamicFabulous: '../../api/y/addMemberDynamicFabulous', // 点赞
	updateMchtShieldingDynamics: '../../api/y/updateMchtShieldingDynamics', //店铺屏蔽/接收动态
	updateShieldingDynamics: '../../api/y/updateShieldingDynamics', //好友屏蔽/接收动态
	updateMemberDynamic: '../../api/y/updateMemberDynamic', //删除动态

	applyCashWithdraw: '../../api/y/applyCashWithdraw', // 提现
	getMemberBalanceDtl: '../../api/y/getMemberBalanceDtl', // 提现 - 明细
	getMemberAccountInfo: '../../api/y/getMemberAccountInfo', // 提现 - 账户信息
	getBankBranch: '../../api/n/getBankBranch',// 提现 - 开户行 
	addMemberOpenNovaPlan: '../../api/y/addMemberOpenNovaPlan', // 开通 | 续签
	addMemberOpenNovaPlanH5: '../../api/z/addMemberOpenNovaPlanH5',
	getMemberRenewalProgressLog: '../../api/y/getMemberRenewalProgressLog', // 开通 - 记录
	getMchtShopDynamic: '../../api/n/getMchtShopDynamic', // 动态 - 店铺 
	getMemberDynamic: '../../api/n/getMemberDynamic', // 动态 - 好友
	hasCollectionMcht: '../../api/y/hasCollectionMcht', //判断店铺是否收藏
	hasFocusOnFriends: '../../api/y/hasFocusOnFriends', //判断好友是否关注

	getMemberAttentions: '../../api/y/getMemberAttentions', // 我的好友
	updateMemberAttention: '../../api/y/updateMemberAttention', // 关注 | 取消关注
	getInvitationFriendPage: '../../api/y/getInvitationFriendPage', // 邀请好友
	getMemberDynamicDtl: '../../api/y/getMemberDynamicDtl', // 好友主页 - 个人信息
	getMemberDynamicList: '../../api/y/getMemberDynamicList', // 好友主页 - 列表

	getXgShopwnerEquityDetail: '../../api/n/getXgShopwnerEquityDetail', // 店长权益
	submitShopownerOrderPayment: '../../api/y/submitShopownerOrderPayment', // 购买店长权益
	checkRec: '../../api/y/svipPractice/checkRec', //判断是否有svip
	svipPracticeRec: '../../api/y/svipPractice/rec', //領取體驗卡
	appLogin: '../../api/z/appLogin',
	giveMemberCouponBySvip: '../../api/y/giveMemberCouponBySvip',

	getMainVenueInfo: '../../api/n/getMainVenueInfo',//主会场按钮

	addCollegeStudentCertification: '../../api/y/addCollegeStudentCertification',//大学生认证
	addCollegeStudentCertificationH5: '../../api/z/addCollegeStudentCertificationH5',
	getCollegeStudentCertification: '../../api/y/getCollegeStudentCertification', //大学生认证信息
	getCollegeStudentCertificationH5: '../../api/z/getCollegeStudentCertificationH5',

	findAssistanceCutCompleteLog: '../../api/n/findAssistanceCutCompleteLog',//助力减价-成交记录列表

	// 618
	allowancePopInfo: '../../api/n/allowance/popInfo', //会场津贴浮窗信息查询
	allowanceInfo: '../../api/y/allowance/info', //主会场津贴领取信息查询
	allowanceDetail: '../../api/y/allowance/detail', //津贴详情查询
	memberAllowanceList: '../../api/y/memberAllowance/list', //用户已领取津贴查询
	memberUsedAllowanceList: '../../api/y/memberUsedAllowance/list', //用户已使用津贴查询
	areaProductList: '../../api/n/areaProduct/list', //津贴会场推荐商品查询
	allowanceExchange: '../../api/y/allowance/exchange', //津贴兑换

	// 修改订单地址
	orderAddressPageInfo: '../../api/y/order/addressPageInfo', // 获取订单收货页面信息
	orderAddressChange: '../../api/y/order/changeAddress' // 修改订单收获地址
};

// 带有底部的页面 (data-id)
var footerPage = [
	'home',
	'mall',
	'cart',
	'my'
];

// 跳过历史记录的页面 (data-id)
var pushSkipPage = [
	'register',
	'register_login',
	'register_code',
	// 'register_nick',
	'register_reset',
	'register_revise',
	'register_mobilePwd',
	'address',
	'address_edit',
	'address_list',
	'order_detail_change',
	'order_detail_address_list',
	'order_detail_address_edit'
];

// 登录页面返回的是上一页面 (data-id)
var loginBack = [
	'seller',
	'seller_info',
	'goods_detail',
	'activity_cutprice',
	'activity_freeprice',
	'activity_reduceprice',
	'activity_newsign'
];

// 在微信上隐藏头部 (data-id)
var wxHeaderHide = [
	'activity_checkin'
];

// 旧文件路径重定向至新路径
var oldFilePath = [
	{
		old: 'activity/cutprice/share.html',
		news: 'activity/cutprice/share/index.html'
	},
	{
		old: 'activity/freeprice/share.html',
		news: 'activity/freeprice/share/index.html'
	},
	{
		old: 'link/my-app.html',
		news: 'my/download/index.html'
	},
	{
		old: 'activity/checkin/draw.html',
		news: 'activity/checkin/draw/index.html'
	}
];

var shareHand = { // 分享
	wxReady: '',
	data: {
		url: '',
		title: '',
		content: '',
		comment: '',
		pictureUrl: ''
	},

	init: function (obj) {
		var _td = this.data;

		_td.url = obj.url ? obj.url : (lead_url + 'views/index.html');
		_td.title = obj.title ? obj.title : '醒购微信购物全新升级！';
		_td.content = obj.content ? obj.content : '醒购微信购物全新升级！每日享更多福利，更有爆款商品新人秒杀价！';
		_td.pictureUrl = obj.pictureUrl ? obj.pictureUrl : (lead_url + 'static/images/logo.png');
	},

	/*
	* _o {
		url: '',
		title: '',
		content: '',
		pictureUrl: '',
	}
	* _p {
		close: '', // 关闭分享
		success: '', // 分享成功
		fail: '', // 分享失败, 已失效
		start: '', // 分享开始
		ready: '' // 实例化成功
	}
	*/
	update: function (_o, _p) {
		var _ts = this,
			_td = _ts.data;

		_o = _o || {};

		if (isWeiXin) {
			_ts.init(_o);

			if (_p) {
				_p.close && (_ts.closeBack = _p.close);
				_p.success && (_ts.succBack = _p.success);
				_p.fail && (_ts.failBack = _p.fail);
				_p.start && (_ts.startBack = _p.start);
				_p.ready && (_ts.readyBack = _p.ready);
			}

			j_wx.ready(function () {
				_ts.appMessage();
				_ts.timeline();
				_ts.shareQQ();
				_ts.shareWeibo();
				_ts.readyBack && _ts.readyBack();
			});

			_ts.startBack && _ts.startBack();
		} else {
			_ts.init(_o);
		}
	},

	readyBack: null,
	startBack: null,
	closeBack: null,
	succBack: function (e) {
		this.close('.popup');
		delayTip('成功分享到' + e);
	},
	failBack: function () {
		this.close('.popup');
		delayTip('已取消分享');
	},

	/*
	* _o {
		wx: '', // 强制微信弹窗
		title: '', // 弹窗标题

		sp1: '1', // 助力大减价分享的同时更新库存
		ext: {}, // 助力大减价的砍价ID和类型
	}
	*/
	show: function (_o) {
		var _ts = this,
			_td = _ts.data;

		_o = _o || {};

		if (member.sys == 'android') {
			if (_o.sp1 != undefined) {
				checkAppVer(50, function () {
					window.H5PluginManager.intentShareSp1(JSON.stringify({
						extraHandleType: _o.sp1,
						pictureUrl: _td.pictureUrl,
						content: _td.content,
						title: _td.title,
						url: _td.url,
						popTitle: _o.title,
						extraParame: JSON.stringify(_o.ext)
					}));
				}, true);
			} else {
				intentShare(_td.title, _td.url, _td.content, _td.comment, _td.pictureUrl, true);
			}
		} else if (member.sys == 'ios') {
			if (_o.sp1 != undefined) {
				checkAppVer(53, function () {
					callIntentTrailer('intentTrailerListNative', 'intentShareSp1', {
						'title': _td.title,
						'url': _td.url,
						'content': _td.content,
						'comment': _td.comment,
						'pictureUrl': _td.pictureUrl,
						'extraHandleType': _o.sp1,
						'onlyweixin': member.app ? 'yes' : '',
						'popTitle': _o.title,
						'extraParame': _o.ext
					});
				}, true);
			} else {
				callIntentTrailer('intentTrailerListNative', 'intentShare', {
					'title': _td.title,
					'url': _td.url,
					'content': _td.content,
					'comment': _td.comment,
					'pictureUrl': _td.pictureUrl,
					'onlyweixin': member.app ? 'yes' : ''
				});
			}
		} else {
			if (isWeiXin) {
				if (_o.sp1 == '1') {
					new ajax(getApi.updateProdutStock, {
						data: {
							activityType: _o.ext.activityType,
							id: _o.ext.id
						},
						success: function () {
							_ts.tip();
						}
					});
				} else {
					_ts.tip();
				}
			} else {
				var _pop = $('.page:visible .popup-share');

				if (_pop.length) {
					_pop.addClass('show');
				} else {
					$('.page:visible').append(
						'<div class="popup popup-share" onclick="shareHand.close(this)">'
						+ '<div class="popup-bottom" onclick="stopSelf()">'
						+ '<div class="popup-top flex ac jc">' + (_o.title ? _o.title : '') + '</div>'
						+ '<div class="popup-con flex jc">'
						+ (_o.wx || isAndroid || isiOS ? ('<a onclick="shareHand.tip()">'
							+ '<img src="../static/images/share-wx.png">微信'
							+ '</a>'
							+ '<a onclick="shareHand.tip()">'
							+ '<img src="../static/images/share-py.png">朋友圈'
							+ '</a>') : ('<a href="https://connect.qq.com/widget/shareqq/index.html?url=' + _td.url
								+ '&title=' + _td.title
								+ '&pics=' + _td.pictureUrl
								+ '&summary=' + _td.content
								+ '" target="_blank">'
								+ '<img src="../static/images/share-qq.png">QQ'
								+ '</a>'
								+ '<a href="http://v.t.sina.com.cn/share/share.php?url=' + _td.url
								+ '&title=' + _td.title
								+ '&pic=' + _td.pictureUrl
								+ '" target="_blank">'
								+ '<img src="../static/images/share-wb.png">微博'
								+ '</a>'))
						+ '</div>'
						+ '<a class="close-share flex ac jc" onclick="shareHand.close(this)">取消分享</a>'
						+ '</div>'
						+ '</div>'
					);

					$('.page:visible .popup-share').addClass('show');
				}
			}
		}
	},
	transShow: function (_o) {
		var _ts = this,
			_td = _ts.data;

		_o = _o || {};

		if (member.sys == 'android') {
			if (_o.sp1 != undefined) {
				checkAppVer(67, function () {
					window.H5PluginManager.interactiveWithApp(
						JSON.stringify({
							"linkType": 1,
							"linkValue": {
								"proImg": _o.skuPic,
								"proPrice": _o.salePrice,
								"shareTitle": _o.shareTitle,
								"shareDesc": _o.shareDesc,
								"webpageUrl": _o.webpageUrl,
								// "wxPath":_o.wxPath,
								"wxPath": _o.wxPath,//小程序具体页面
								"xcxShareType": _o.xcxShareType,
								"originalId": _o.originalId,
							},
							"curturnVersion": 1,
							"extraMessage": "jsonString",
						})
					);
				});
			} else {
				intentShare(_td.title, _td.url, _td.content, _td.comment, _td.pictureUrl, true);
			}
		} else if (member.sys == 'ios') {
			if (_o.sp1 != undefined) {
				checkAppVer(66, function () {
					callIntentTrailer('intentTrailerListNative', 'interactiveWithApp',
						JSON.stringify({
							"linkType": 1,
							"linkValue": {
								"proImg": _o.skuPic,
								"proPrice": _o.salePrice,
								"shareTitle": _o.shareTitle,
								"shareDesc": _o.shareDesc,
								"webpageUrl": _o.webpageUrl,
								// "wxPath":_o.wxPath,
								"wxPath": _o.wxPath,//小程序具体页面
								"xcxShareType": _o.xcxShareType,
								"originalId": _o.originalId,
							},
							"curturnVersion": 1,
							"extraMessage": "jsonString",
						})
					);
				});
			} else {
				callIntentTrailer('intentTrailerListNative', 'intentShare', {
					'title': _td.title,
					'url': _td.url,
					'content': _td.content,
					'comment': _td.comment,
					'pictureUrl': _td.pictureUrl,
					'onlyweixin': member.app ? 'yes' : ''
				});
			}
		} else {
			if (isWeiXin) {
				if (_o.sp1 == '1') {
					new ajax(getApi.updateProdutStock, {
						data: {
							activityType: _o.ext.activityType,
							id: _o.ext.id
						},
						success: function () {
							_ts.tip();
						}
					});
				} else {
					_ts.tip();
				}
			} else {
				var _pop = $('.page:visible .popup-share');

				if (_pop.length) {
					_pop.addClass('show');
				} else {
					$('.page:visible').append(
						'<div class="popup popup-share" onclick="shareHand.close(this)">'
						+ '<div class="popup-bottom" onclick="stopSelf()">'
						+ '<div class="popup-top flex ac jc">' + (_o.title ? _o.title : '') + '</div>'
						+ '<div class="popup-con flex jc">'
						+ (_o.wx || isAndroid || isiOS ? ('<a onclick="shareHand.tip()">'
							+ '<img src="../static/images/share-wx.png">微信'
							+ '</a>'
							+ '<a onclick="shareHand.tip()">'
							+ '<img src="../static/images/share-py.png">朋友圈'
							+ '</a>') : ('<a href="https://connect.qq.com/widget/shareqq/index.html?url=' + _td.url
								+ '&title=' + _td.title
								+ '&pics=' + _td.pictureUrl
								+ '&summary=' + _td.content
								+ '" target="_blank">'
								+ '<img src="../static/images/share-qq.png">QQ'
								+ '</a>'
								+ '<a href="http://v.t.sina.com.cn/share/share.php?url=' + _td.url
								+ '&title=' + _td.title
								+ '&pic=' + _td.pictureUrl
								+ '" target="_blank">'
								+ '<img src="../static/images/share-wb.png">微博'
								+ '</a>'))
						+ '</div>'
						+ '<a class="close-share flex ac jc" onclick="shareHand.close(this)">取消分享</a>'
						+ '</div>'
						+ '</div>'
					);

					$('.page:visible .popup-share').addClass('show');
				}
			}
		}
	},
	close: function (e) {
		closeSelf(e);
		this.closeBack && this.closeBack();
	},

	tip: function (_dl) {
		if (isWeiXin || _dl) {
			var _tip = $('.page:visible .share-tip');

			if (_tip.length) {
				_tip.addClass('show')
			} else {
				$('.page:visible').append('<div class="popup share-tip" onclick="closeSelf(this)"></div>');
				$('.page:visible .share-tip').addClass('show');
			}
		} else {
			delayTip('请在微信上操作！');
		}

		this.close('.popup-share');
	},

	appMessage: function () {
		var _ts = this,
			_td = _ts.data;

		j_wx.onMenuShareAppMessage({
			title: _td.title,
			desc: _td.content,
			link: _td.url,
			imgUrl: _td.pictureUrl,
			type: '',
			dataUrl: '',
			success: function () {
				_ts.succBack('朋友');
			},
			cancel: _ts.failBack
		});
	},

	timeline: function () {
		var _ts = this,
			_td = _ts.data;

		j_wx.onMenuShareTimeline({
			title: _td.title,
			link: _td.url,
			imgUrl: _td.pictureUrl,
			success: function () {
				_ts.succBack('朋友圈');
			},
			cancel: _ts.failBack
		});
	},

	shareQQ: function () {
		var _ts = this,
			_td = _ts.data;

		j_wx.onMenuShareQQ({
			title: _td.title,
			link: _td.url,
			imgUrl: _td.pictureUrl,
			desc: _td.content,
			success: function () {
				_ts.succBack('QQ');
			},
			cancel: _ts.failBack
		});
	},

	shareWeibo: function () {
		var _ts = this,
			_td = _ts.data;

		j_wx.onMenuShareWeibo({
			title: _td.title,
			link: _td.url,
			imgUrl: _td.pictureUrl,
			desc: _td.content,
			success: function () {
				_ts.succBack('腾讯微博');
			},
			cancel: _ts.failBack
		});
	},

	chooseImage: function () {
		console.log(11111)
		j_wx.chooseImage({
			count: 9,
			sizeType: ['original', 'compressed'],
			sourceType: ['album', 'camera'],
			success: function (res) {
				console.log(res.localIds)
				// tempFilePath可以作为img标签的src属性显示图片
				var tempFilePaths = res.tempFilePaths
			}
		})
	},

	wxConfig: function (_f) {
		var _ts = this;

		new ajax(getApi.getWecharShareSign, {
			data: {
				url: location.href
			},
			success: function (res) {
				console.log(res)
				var _l = res.returnData;
				j_wx.config({
					debug: false,
					appId: _l.appId,
					beta: true,
					timestamp: _l.timestamp,
					nonceStr: _l.nonceStr,
					signature: _l.signature,
					jsApiList: [
						'onMenuShareTimeline',
						'onMenuShareAppMessage',
						'onMenuShareQQ',
						'onMenuShareWeibo',
						'chooseImage', 'previewImage', 'uploadImage', 'downloadImage'
					]
				});

				_f();
			},
			error: function () {
				_f();
				delayTip('微信初始化失败');
			}
		});
	}
};
var traffic = { // 流量统计
	host: '../../../appDataService/api/n/',
	item: {},
	fromApp: {},
	fromParent: {},

	list: [], // pv
	listPage: [], // pv不包括子项

	listDtl: [], // 统计dtl
	listAction: [], // 统计action
	start: 0, // 插入pv的开始时间

	allow: 0, // 是否允许统计
	first: 0, // APP第一次进入, 如果没有fromPvId, 不插入缓存的fromPvId

	ajax: function (_url, _obj) {
		var _ts = this,
			_c = member.psys;

		if (_c == 4) {
			_c = 3;
		} else if (_c == 3) {
			_c = 4;
		}
		$.ajax({
			method: 'post',
			url: _ts.host + _url,
			// async: false,
			data: JSON.stringify({
				reqData: _obj.data || {},
				client: _c,
				version: 0,
				timeStamp: Date.now(),
				nonceStr: "OV5QOVX21A0446K8",
				signature: "7f8c4ad7f6d349cf28879670b9fe79b9"
			}),
			success: function (res) {
				_obj.success && _obj.success(res.returnData);
			},
			error: function () {
				_obj.error && _obj.error();
			}
		});
	},

	/*
	* mounted (data, {pageType, mchtId, valueId})
	* updated (data)
	* tab (data, {pageType, pvTab})
	* current (data, {pageType, mchtId, valueId, pvCur})
	* noTime (data, {pageType, noTime})
	* no traffic ();
	*/
	setPv: function (_d, _o) {
		var _ts = this;
		_ts.allow && requestAppMember(function () {
			// _ts.allow && member.id && requestAppMember(function () {
			setTimeout(function () {
				var _lp = _ts.listPage,
					_h = _lp.length,
					_t = Date.now(),
					_s = 'h';

				_o = _o || {};

				if (_o.noTime) {
					var _c = getColumn()
					_ts.list.unshift({
						memberId: member.id,
						pvId: _s + member.id + _t + randomString(4),
						pageType: _o.pageType,
						valueId: _o.valueId || '',
						mchtId: _o.mchtId || '',
						fromPageType: _d.pv.pageType,
						fromPvId: _d.pv.pvId,
						stayTime: 0,
						deviceNumber: getDeviceNumber(),
						columnType: _c.type,
						columnId: _c.id,
						createDate: new Date().getTime()
					});

					win_lc.setItem('traffic', JSON.stringify(_ts.list));
					return;
				}

				if (_h) {
					var _ls = _ts.list,
						_l = _ls.length - 1,
						_g = _lp[_h - 1],
						_k = _ls[_l],
						_b = _ts.back;

					if (_d && _b) {
						for (var i = _l; i >= 0; i--) {
							if (_ls[i].pvId == _b.pvId) {
								_ts.list[i].stayTime += _t - (_ts.list[i].stayTime ? _b.now : _ts.list[i].now);
								break;
							}
						}

						delete _ts.back;
					}

					if (_k) {
						if (_o.pvTab) {
							_k.pvId != _g.pvId && (_ts.list[_l].stayTime = _t - _k.now);
						} else {
							_ts.list[_l].stayTime = _t - _k.now;

							if (_k.pvId != _g.pvId) {
								_g.stayTime = _t - _g.now;

								for (var i = _l; i >= 0; i--) {
									if (_ls[i].pvId == _g.pvId) {
										_ts.list[i].stayTime = _g.stayTime;
										break;
									}
								}
							}
						}
					}
				}

				if (_d) {
					var _f = {};

					if (_o.pageType) {
						if (_h) {
							if (_o.pvTab) {
								_h > 1 && (_f = _lp[_h - 2]);
							} else if (_o.pvCur) {
								_f = _d.pv;
							} else {
								_f = _lp[_h - 1];
							}
						} else {
							_ts.fromApp.pvId && (_f = _ts.fromApp);
						}

						if (member.sys == 'ios') {
							_s = 'i';
						} else if (member.sys == 'android') {
							_s = 'a';
						}
						var _c = getColumn()
						var _v = {
							memberId: member.id,
							pvId: _s + member.id + _t + randomString(4),
							pageType: _o.pageType,
							valueId: _o.valueId || '',
							mchtId: _o.mchtId || '',
							fromPageType: _f.pageType || '',
							fromPvId: _f.pvId || '',
							stayTime: 0,
							now: _t,
							deviceNumber: getDeviceNumber(),
							columnType: _c.type,
							columnId: _c.id,
							createDate: new Date().getTime()
						};

						if (_o.pvTab) {
							_d.pvTab = _v;
						} else {
							_d.pv = _v;
							_ts.listPage.push(_v);
						}

						_ts.list.push(_v);
					} else if (_d.pv) {
						_ts.back = {
							pvId: _d.pv.pvId,
							now: _t
						};
					}
				}

				_ts.setPvParam1 = _d;
				_ts.setPvParam2 = _o;

				win_lc.setItem('traffic', JSON.stringify(_ts.list));
			}, 80)

		});
	},

	delayRequest: function (_f) {
		requestAppMember(function () {
			setTimeout(_f, 10);
		});
	},

	// (data, {type, id, pos})
	setDtl: function (_d, _o) {
		var _ts = this,
			_p = _d.pv,
			_i;
		if (_ts.allow && _p) {
			for (var i = 0; i < _ts.listDtl.length; i++) {
				if (_o.type == _ts.listDtl[i].dtlItemType && _p.pvId == _ts.listDtl[i].memberPvId && _ts.listDtl[i].dtlItemId == _o.id) {
					_i = i;
					break;
				}
			}

			if (_i !== undefined) {
				_ts.listDtl[_i].clickCount++;
			} else {
				_ts.listDtl.push({
					memberPvId: _p.pvId,
					dtlItemType: _o.type,
					dtlItemId: _o.id,
					position: _o.pos || 0,
					clickCount: 0,
					memberId: member.id,
					deviceNumber: getDeviceNumber(),
					createDate: new Date().getTime()
				});
			}

			win_lc.setItem('traffic_dtl', JSON.stringify(_ts.listDtl));
		}
	},

	// (data, {type, id})
	setAction: function (_d, _o) {
		var _ts = this,
			_p = _d.pv;

		if (_p && _p.pvId) {
			var _c = getColumn()
			_ts.listAction.push({
				memberPvId: _p.pvId,
				memberId: member.id,
				actionItemId: _o.id || '',
				actionType: _o.type,
				deviceNumber: getDeviceNumber(),
				columnType: _c.type,
				columnId: _c.id,
				createDate: new Date().getTime()
			});

			win_lc.setItem('traffic_action', JSON.stringify(_ts.listAction));
		}
	},

	postPv: function () {
		var _ts = this,
			_v = _ts.list,
			_l = _v.length > 500 ? 500 : _v.length;

		if (_l > 0) {
			_v = _v.slice(0, _l);

			for (var i = 0; i < _v.length; i++) {
				delete _v[i].now;
			}

			_ts.ajax('memberPvCommit', {
				data: _v,
				success: function () {
					_ts.list.splice(0, _l);
					// const _vvv = _ts.list.splice(0, _l);
					// alertTip(JSON.stringify(_vvv));
					win_lc.setItem('traffic', JSON.stringify(_ts.list));
				}
			});
		}
	},

	postDtl: function () {
		var _ts = this,
			_v = _ts.listDtl,
			_l = _v.length > 500 ? 500 : _v.length;

		if (_l > 0) {
			_v = _v.slice(0, _l);

			_ts.ajax('memberPvDtlCommit', {
				data: _v,
				success: function () {
					_ts.listDtl.splice(0, _l);
					// const _vvv = _ts.listDtl.splice(0, _l);
					// alertTip(JSON.stringify(_vvv));
					win_lc.setItem('traffic_dtl', JSON.stringify(_ts.listDtl));
				}
			});
		}
	},

	postAction: function () {
		var _ts = this,
			_v = _ts.listAction,
			_l = _v.length > 500 ? 500 : _v.length;

		if (_l > 0) {
			_v = _v.slice(0, _l);

			_ts.ajax('memberActionCommit', {
				data: _v,
				success: function () {
					_ts.listAction.splice(0, _l);
					win_lc.setItem('traffic_action', JSON.stringify(_ts.listAction));
				}
			});
		}
	}
};

if (win_lc.getItem('storage_var') != member.webver) {
	win_lc.clear();
	clearStorage();
	try {
		win_lc.setItem('storage_var', member.webver);
	} catch (err) {
		alert('ios为保证正常使用, 请退出无痕浏览') // 可执行
	}
} else {
	// removeStorage('cart_piece'); // 购物车件数
	removeStorage('shop_card_id'); // 立即购买
	removeStorage('free_share_neednum'); // 只有新用户才能参与免单

	win_lc.getItem('member') && (member = JSON.parse(win_lc.getItem('member')));

	win_lc.getItem('douyin') && (global_douyin = win_lc.getItem('douyin'), member.psys = 6);
	win_lc.getItem('douyin_track') && (douyin_track = win_lc.getItem('douyin_track'));
	win_lc.getItem('douyin_convert1') && (douyin_convert1 = win_lc.getItem('douyin_convert1'));
	win_lc.getItem('douyin_convert2') && (douyin_convert2 = win_lc.getItem('douyin_convert2'));

	win_lc.getItem('traffic') && (traffic.list = JSON.parse(win_lc.getItem('traffic')));
	win_lc.getItem('traffic_dtl') && (traffic.listDtl = JSON.parse(win_lc.getItem('traffic_dtl')));
	win_lc.getItem('traffic_action') && (traffic.listAction = JSON.parse(win_lc.getItem('traffic_action')));
}

global_douyin && require(['douyin']);

if (isWeiXin) {
	if (getStorage('reload')) {
		removeStorage('reload');
	} else {
		setStorage('reload', 1);
		getStorage('reload') && location.reload();
	}
}

traffic.ajax('sysConfig', {
	success: function (res) {
		if (res.isCollect == 1) {
			traffic.allow = 1;

			requestAppMember(function () {
				traffic.postPv();
				traffic.postDtl();
				traffic.postAction();

				setInterval(function () {
					traffic.postPv();
					traffic.postDtl();
					traffic.postAction();
				}, res.commitIntervalTime * 1e3);
			});
		}
	}
});

(function () {
	var _url = location.href;

	if (/openid=/g.test(_url)) { // 抖音
		var _o1 = _url.split('views/index.html?'),
			_o2 = _o1[1].split('&redirect_url='),
			_o3 = decodeURIComponent(_o2[1]);

		var _surl = _o3.replace(/\?/g, '&').split('&'),
			_trackingid = '1631224844532744',
			_convertid1 = '1631769327523852',
			_convertid2 = '1631769351607308';

		for (var i = 0; i < _surl.length; i++) {
			var _a = _surl[i].split('='),
				_b = _a[1];

			switch (_a[0]) {
				case 'trackingId':
					_trackingid = _b;
					break;
				case 'convertId':
					_convertid1 = _b;
					break;
				case 'convertId2':
					_convertid2 = _b;
					break;
			}
		}

		global_douyin = 1;
		win_lc.setItem('douyin', 1);

		douyin_track = _trackingid;
		win_lc.setItem('douyin_track', douyin_track);

		douyin_convert1 = _convertid1;
		win_lc.setItem('douyin_convert1', douyin_convert1);

		douyin_convert2 = _convertid2;
		win_lc.setItem('douyin_convert2', douyin_convert2);

		member.psys = 6;

		getUrl(_o1[0] + 'views/index.html?redirect_url=' + _o3 + (/\?/g.test(_o3) ? '&' : '?') + _o2[0]);
	} else {
		_url = location.href.split(redirect_url);
		_url = _url[0].split('/views/')[0] + '/views/' + (_url[1] ? (redirect_url + _url[1]) : '');

		if (isWeiXin) {
			if (!win_ls.getItem('weixin_reload')) {
				win_ls.setItem('weixin_reload', 1);
				location.href = _url + '##';
			} else {
				require(['jweixin'], function (jwx) {
					j_wx = jwx;

					shareHand.wxConfig(function () {
						getMember(function () {
							getUrl(_url);
						}, 1);
					});
				});

				win_ls.removeItem('weixin_reload');
			}
		} else {
			!member.app && (member.id = '');
			member.web = '';

			getMember(function () {
				getUrl(_url);
			}, 1);
		}
	}
})();

$(document).ajaxComplete(function () {
	scrollHand.post = 1;
	uploadHand.post = 1;
});

// 阻止iOS10及更高版本页面缩放
var dtouch_end = 0;

document.addEventListener('touchstart', function (e) {
	e.touches.length > 1 && e.preventDefault();
});

document.addEventListener('touchend', function (e) {
	var _now = Date.now();
	_now - dtouch_end <= 300 && e.preventDefault();
	dtouch_end = _now;
}, false);

document.addEventListener('touchmove', function (e) {
	e.touches.length > 1 && e.preventDefault();
}, { passive: false });

// APP样式兼容
member.app && appStyleFit();

// 桌面样式修正
(!isAndroid && !isiOS) && addStylesheetRules([
	['.w75',
		['width', '7.5rem !important']
	],
	['.touchfix',
		['min-height', '100% !important']
	]
]);

// 兼容三星等安卓手机
isAndroid && addStylesheetRules([
	['.h345',
		['height', $('.app-spe-width .w345').width() + 'px !important']
	],
	['.product-3 .h345, .h370',
		['height', $('.app-spe-width .w370').width() + 'px !important']
	],
	['.h50vw',
		['height', '50vw !important']
	]
]);

// 微信样式兼容
addStylesheetRules([
	isWeiXin ? [
		'.show-after-sale.order-detail .content',
		['height', 'calc(100% - 1.48rem)']
	] : ['.no-after-sale',
			['display', 'none !important']
		]
]);

// 临时存储
function setStorage(e, d) {
	win_ls.setItem(e, d);
}

function getStorage(e) {
	return win_ls.getItem(e);
}

function removeStorage(e) {
	win_ls.removeItem(e);
}

function clearStorage() {
	win_ls.clear();
}

// 更新APP版本信息
function getAppAgent() {
	if (isxgbuy) {
		member.app = isxgbuy;

		var _xgbuy = navigator.userAgent.split('xgbuy/')[1];

		if (/plat\//.test(_xgbuy)) {
			var _xgbuy1 = _xgbuy.split(' ');

			member.sys = _xgbuy1[3].split('plat/')[1];
			member.mver = _xgbuy1[1].split('sys/')[1];
			member.iosver = _xgbuy1[0];
			member.appver = _xgbuy1[2].split('ser/')[1];
		} else {
			var _xgbuy2 = _xgbuy.split('/');

			member.sys = _xgbuy2[0];
			member.mver = _xgbuy2[1];
			member.appver = _xgbuy2[2];
			member.iosver = _xgbuy2[3];

			if (member.iosver) {
				var _str = '';

				for (var i = 0; i < member.iosver.length; i++) {
					/\d/.test(member.iosver[i]) && (_str += member.iosver[i]);
				}
				member.appver = Number(_str);
			}

			member.sys == 'android' && (member.appver = parseInt(member.appver));
		}

		member.psys = member.sys == 'ios' ? 1 : 2;
	}
}
// app交互
//  
// _obj = {
// 	"linkType": 1,
// 	"linkValue": {
// 	},
// 	"curturnVersion": 1,
// 	"extraMessage": "jsonString",
// }
// _f H5回调
function withApp(_obj, _f) {
	if (member.sys == 'android') {
		checkAppVer(67, function () {
			window.H5PluginManager.interactiveWithApp(
				JSON.stringify(_obj)
			);
		}, true);
	} else if (member.sys == 'ios') {
		checkAppVer(66, function () {
			callIntentTrailer('intentTrailerListNative', 'interactiveWithApp',
				JSON.stringify(_obj)
			);
		}, true);
	} else {
		_f && _f()
	}
}

// 转json
function formName(e) {
	return "{\"" + e.replace(/&/g, "\",\"").replace(/=/g, "\":\"") + "\"}";
}

// 生成随机字符串 (流量统计)
function randomString(_len) {
	_len = _len || 32;

	var _s = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678', // 去掉容易混淆的字符'oOLl 9gq Vv Uu I1'
		_l = _s.length,
		_p = '';

	for (var i = 0; i < _len; i++) {
		_p += _s.charAt(Math.floor(Math.random() * _l));
	}

	return _p;
}

// 加载动画
function loadShow() {
	page_load.find('div').removeClass('hide');
	page_load.find('p').removeClass('hide');
	page_load.removeClass('hide');
}

function loadHide() {

	page_load.addClass('hide');
	page_load.find('div').addClass('hide');
	page_load.find('p').addClass('hide');
}

// 密码登录
function registerSign(_login, fn1, fn2) {
	new ajax(getApi.login, {
		data: _login,
		success: function (e) {
			cookieLogin(e);
			fn1 && fn1();
		},
		error: function (e) {
			fn2 ? fn2() : delayTip(e.returnMsg);
		}
	});
}

// 注册登录与密码登录后, 存储登录信息
function cookieLogin(e) {
	e = e.returnData;

	member.id = e.memberId;
	member.name = e.levelName;
	member.pic = e.levelPic;
	member.nick = e.nick;
	member.token = e.token;
	updateMember();
}

function updateMember() {
	member.id ? win_lc.setItem('member', JSON.stringify(member)) : win_lc.removeItem('member');
}

// 登录
var request_app_login = null;

function memberFn() {
	if (member.sys == 'android') {
		member.token = '';
		login('未登录');
		requestAppLogin();
	} else if (member.sys == 'ios') {
		member.token = '';
		callLogin('Native', 'login', '未登录');
		requestAppLogin();
	} else {
		registerSign({}, function () {
			location.reload();
		}, function () {
			getUrl('register/login/index.html', 'self');
		});
	}
}

function requestAppLogin() {
	request_app_login = requestAnimationFrame(function () {
		if (member.token) {
			updateMember();
			location.reload();
		} else {
			requestAppLogin();
		}
	});
}

function requestAppMember(f2, f1) {
	if (member.app) {
		loadShow();
		requestMemberWeb(f2, f1);
	} else {
		f2();
	}
}

function requestMemberWeb(f2, f1) {
	requestAnimationFrame(function () {
		if (member.web) {
			f1 && f1();
			f2();
			loadHide();
		} else {
			requestMemberWeb(f2, f1);
		}
	});
}

// 退出
function outLogin() {
	new ajax(getApi.loginout, {
		success: function (res) {
			win_lc.removeItem('member');
			member.id = '';
			member.token = '';
			delayTip('退出成功');
			getUrl('home', true);
		}
	});
}


// 设置标题
function setTitle(_t) {
	$('title').html(_t);
	$('.page:visible>header p').html(_t);
}

// 显示隐藏密码
function toggleEye(e) {
	e = $(e);
	var _input = e.siblings('input');

	if (_input.attr('type') == 'text') {
		_input.attr('type', 'password');
		e.removeClass('open');
	} else {
		_input.attr('type', 'text');
		e.addClass('open');
	}
	return;
}

// 获取购物车件数
function getCartPiece(fn) {
	var _a = getStorage('cart_piece');

	if (_a || _a == '') {
		fn && fn(_a);
	} else {
		/*new ajax(getApi.shoppingCartNum, {
			data: {
				memberId: member.id
			},
			success: function (e) {
				var _e = e.returnData,
					_i = '';

				_e > 0 && (_i = '<i class="ico-radius flex ac jc">' + _e + '</i>');

				setStorage('cart_piece', _i);
				fn && fn(_i);
			},
			error: function () {
				fn && fn('');
			}
		});*/
	}
}

function updateCartPiece(fn) {
	removeStorage('cart_piece');

	getCartPiece(function (piece) {
		if (piece) {
			$('#footer .cart').append(piece);
			$('#goods_detail_piece').append(piece);
		} else {
			$('#footer .cart i').remove();
			$('#goods_detail_piece i').remove();
		}
	});
}

// input, 增, 减
function inputNumber(elem, fn1, fn2) {
	elem.next().click(function () {
		var _input = $(this).siblings('input'),
			_val = _input.val(),
			_buy = parseInt(_input.attr('data-buy')) || 0,
			_max = parseInt(_input.attr('max')) || '';

		if (_max) {
			if (parseInt(_val) >= _max - _buy) {
				delayTip('每人限购' + _max + '件' + (_buy ? '，您已购买' + _buy + '件~' : ''));
			} else {
				fn1 && fn1(_input);
			}
		} else {
			fn1 && fn1(_input);
		}
	}).end().prev().click(function () {
		var _input = $(this).siblings('input'),
			_val = _input.val(),
			_min = _input.attr('min') || 1;

		parseInt(_val) > parseInt(_min) && fn2 && fn2(_input);
	});
}

// 优化折扣 (.50 -> .5)
function famateDiscount(e, f) {
	var _f = f || 10,
		_a = (_f * e).toFixed(1),
		_b = parseInt(_a);

	return _a > _b ? _a : _b;
}

// 优化时间 (20:0 -> 20:00)
function famateNumber(e) {
	return e < 10 ? '0' + parseInt(e) : parseInt(e);
}

// 数组去重
function arrdew(e) {
	e.sort();
	var a = [e[0]];

	for (var i = 1; i < e.length; i++) {
		e[i] !== a[a.length - 1] && a.push(e[i]);
	}
	return a;
}

function Untapped() {
	console.log('当前功能尚未开发！');
}

// 实例化幻灯片
function initSwiper(obj) {
	var _swiper = {
		container: $('.page:visible .swiper-container'),
		autoplay: true,
		observer: true,
		observeParents: true,//修改swiper的父元素时，自动初始化swiper
		speed: 1500,
		loop: true,
		slide: 0,
		pagination: {
			el: '.pagination',
		}
	};

	if (obj) {
		obj.container !== undefined && (_swiper.container = obj.container);
		obj.autoplay !== undefined && (_swiper.autoplay = obj.autoplay);
		obj.speed !== undefined && (_swiper.speed = obj.speed);
		obj.slide !== undefined && (_swiper.slide = obj.slide);
		obj.loop !== undefined && (_swiper.loop = obj.loop);
		obj.pagination !== undefined && (_swiper.pagination = obj.pagination);
	}
	var _css = false;

	$.each($('.page:visible link'), function () {
		/swiper/g.test(this.href) && (_css = true);
	});

	!_css && $('.page:visible').prepend('<link rel="stylesheet" href="../static/css/swiper.min.css">');

	require(['swiper'], function (Swiper) {
		new Swiper(_swiper.container, {
			autoplay: _swiper.autoplay ? {
				disableOnInteraction: false
			} : false,
			loop: _swiper.loop,
			speed: _swiper.speed,
			initialSlide: _swiper.slide,
			pagination: _swiper.pagination,
			on: {
				click: function (event) {
					if ($(event.target).hasClass("good-wrapper")) {
						if (_swiper.container.hasClass("preview")) {
							console.log(_swiper.container)
							_swiper.container.removeClass("preview")
							$(".goods-detail").removeClass("goods-detail-preview")
						} else {
							_swiper.container.addClass("preview")
							$(".goods-detail").addClass("goods-detail-preview")
						}
					}
				},
				slideChange: function () {
					$(".pagination-fraction span").text(this.realIndex + 1)
					// if (this.activeIndex == (this.slides.length - 1)) {
					// 	this.slideTo(1, 0)
					// }
				},
			},
		});
		setTimeout(function () {
			var myPlayerList = $('.page:visible').find('.video-js');
			if (myPlayerList.length > 0) {
				videojsRendering(myPlayerList[0])
				videojsRendering(myPlayerList[1])
			}
		}, 0);
	});
}

var laywifi2 = 0
var laywifi_th = false

//videojs渲染
function videojsRendering(dom) {
	videojs(dom, {}, function () {
		var c = this.controlBar.children();
		var $el = $(this.el())
		var _this = this
		this.controlBar.removeChild(c[16]);
		this.controlBar.removeChild(c[15]);
		this.controlBar.removeChild(c[1]);
		$el.append('<button class="vjs-big-pause-button"><span aria-hidden="true" class="vjs-icon-placeholder"></span></button>');
		$el.find(".vjs-big-pause-button").on("click", function () {
			_this.pause()
		})
		$el.append('<button class="vjs-big-close-button"><span aria-hidden="true" class="vjs-icon-placeholder"></span></button>');
		$el.find(".vjs-big-close-button").on("click", function () {
			if ($el.parent().hasClass("swiper-slide")) {
				$('.page:visible .swiper-container').removeClass("preview")
				$(".page:visible .goods-detail").removeClass("goods-detail-preview")
			} else {
				$(".detail>.detail-video").removeClass("preview")
				$(".page:visible .goods-detail").removeClass("goods-detail-preview")
			}
		})
		$el.find(".vjs-control-bar").append('<button class="vjs-control vjs-button vij-full" type="button"><span class="vjs-icon-placeholder" ></span></button>')
		$el.find(".vij-full").on("click", function () {
			if ($el.parent().hasClass("swiper-slide")) {
				$('.page:visible .swiper-container').toggleClass("preview")
				$(".page:visible .goods-detail").toggleClass("goods-detail-preview")
			} else {
				$(".detail>.detail-video").toggleClass("preview")
				$(".page:visible .goods-detail").toggleClass("goods-detail-preview")
			}
		})
		$el.append('<div class="progress-bottom"><div></div></div>');
		$el.append('<div class="msg hide">当前非Wi-Fi播放，请注意流量消耗！</div>');
		this.el().addEventListener("timeupdate", function () {
			var duration = $el.find("video")[0].duration;
			var curTime = $el.find("video")[0].currentTime;
			var progressBar = $el.find(".progress-bottom").width()
			var percent = parseInt(progressBar * curTime / duration);
			$el.find(".progress-bottom>div").width(percent);
		}, true);
		this.el().addEventListener("loadstart", function () {
			$el.find(".progress-bottom>div").width(0);
			// $el.removeClass("vjs-user-inactive").addClass("vjs-user-active")
		}, true)
		this.el().addEventListener("ended", function () {
			_this.load()
		}, true)
		this.el().addEventListener("play", function () {
			if (member.sys == 'ios') {
				var dic = {
					'handlerInterface': 'intentTrailerListNative', 'function': 'getDeviceInfo', 'parameters': {}
				};

				if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
					window.webkit.messageHandlers['intentTrailerListNative'].postMessage(dic);
				} else {
					window['intentTrailerListNative']['getH5Params'](JSON.stringify(dic));
				}
			} else if (member.sys == 'android') {
				window.H5PluginManager.getDeviceInfo()
			}

			setTimeout(function () {
				networkStr = (member.net != 1 ? 'wifi' : 'other')
				var laywifi = localStorage.getItem("laywifi")
				if ((member.web == "ios" || member.web == "android" || true) && networkStr != "wifi") {
					if (!laywifi) {
						_this.pause()
						layer.open({
							title: [
								'温馨提示',
								'font-size:18px;height:51px;line-height:51px'
							]
							, className: 'popuo'
							, content: '你正在使用移动数据网络，会产生流量费用，是否继续观看？'
							, btn: ['继续播放', '取消']
							, yes: function () {
								localStorage.setItem("laywifi", true)
								laywifi_th = true
								_this.play()
								layer.closeAll()
								laywifi2--
							}
							, no: function () {
							}
						});
					} else {
						if (laywifi2 == 0 && !laywifi_th) {
							$el.find(".msg").removeClass("hide")
							setTimeout(function () {
								$el.find(".msg").addClass("hide")
							}, 2000)
						}
						laywifi2++
					}
				}
			}, 300)
		}, true)
	})
}

// 返回
function back(_url, _refresh, _f) {
	if ($('.page:visible .dialog').length && $('.page:visible .dialog:not(.hide)').length) {
		$('.page:visible .dialog:not(.hide)').addClass('hide').siblings('.scroll-more').removeClass('hide');
		_f && _f();
	} else if (prev_url) {
		var _m1 = false;

		if (real_url.split(redirect_url)[1].split('.html')[0] == 'register/login/index') {
			var _prv = prev_url.split(redirect_url)[1].split('/index.html')[0].split('.html')[0].replace(/\//g, '_');

			for (var i = 0; i < loginBack.length; i++) {
				if (loginBack[i] == _prv) {
					_m1 = true;
					break;
				}
			}
		}

		if (_m1) {
			getUrl(prev_url);
		} else {
			_refresh && $('.page').remove();

			history.back();
		}
	} else {
		if (_url) {
			getUrl(_url, _refresh);
		} else {
			_refresh && $('.page').remove();
			history.back();
		}
	}
}

// 加密
function base64Btoa(e) {
	return window.btoa(unescape(encodeURIComponent(e)));
}

// 解密
function base64Atob(e) {
	return decodeURIComponent(escape(window.atob(e)));
}

// 监听路由
window.addEventListener('popstate', function (event) {
	if ($('.page:visible .dialog').length && $('.page:visible .dialog:not(.hide)').length) {
		!member.app && history.forward();
		$('.page:visible .dialog:not(.hide)').addClass('hide').siblings('.scroll-more').removeClass('hide');
	} else {
		if (pop_state) {
			setStorage('traffic_back', 1);
			getUrl(location.href, false, null, true);
		}
		var _txt = $('.page:visible>header p').text();
		_txt && setTitle(_txt);
	}

	onShow && onShow(search_url);
});

// onShow -> post_id === ''
function onShow() {
}

// 浏览器关闭
window.onunload = function () {
	!member.app && traffic.setPv();
};
// 浏览器关闭之前
window.onbeforeunload = function () {
	traffic.postPv()
	traffic.postDtl();
	traffic.postAction();
}

// APP上统计数据
function appUnloadAddPv() {
	traffic.setPv();
	traffic.postPv()
	traffic.postDtl();
	traffic.postAction();
}

// app 右上角分享
function getH5PageType() {
	var s = ''
	real_url && (s = real_url.split("?redirect_url=")[1].split('?')[0])
	var type = 0
	var _id = "";
	var action = '';
	switch (s) {
		case "activity/templet/brand_decorate.html":
			for (var i = 0; i < search_url.length; i++) {
				if (search_url[i].indexOf("activityAreaId") > -1) {
					_id = search_url[i].split('=')[1]
				}
			}
			type = 1
			break
		case "activity/templet/single_def.html":
			for (var i = 0; i < search_url.length; i++) {
				if (search_url[i].indexOf("activityAreaId") > -1) {
					_id = search_url[i].split('=')[1]
				}
			}
			type = 1
			break
		case "activity/templet/brand_def.html":
			for (var i = 0; i < search_url.length; i++) {
				if (search_url[i].indexOf("activityAreaId") > -1) {
					_id = search_url[i].split('=')[1]
				}
			}
			type = 1
			break
		case "main_venue/index.html":
			_id = getStorage("activityAreaModuleId")
			type = 2
			break
		case "activity/pointsLottery/index.html":
			action = JSON.parse(getStorage('pointsDrawShareData'));
			type = 3;
			break;
	}

	if (member.sys == 'android') {
		checkAppVer(61, function () {
			getH5PageType_d(type, _id, '');
		});
	} else if (member.sys == 'ios') {
		checkAppVer(61, function () {
			callIntentTrailer('intentTrailerListNative', 'getH5PageType', { 'type': type, 'id': _id, 'action': action });
		});
	}
}

// 路由(地址, 是否刷新, 回调, "返回"是否推入历史, 标题)
function getUrl(_url, _refresh, fn, _pop, _title) {
	var _url0 = '',
		_url1 = '';

	pop_state = false;
	_url = _url.replace('##', '');
	!_url && (_url = location.href);
	/xgbuy\/views\/#/g.test(_url) && (_url = _url.replace('xgbuy/views/#', 'xgbuy/views/' + redirect_url)); // 纠正错误链接

	if (/http/g.test(_url)) {
		var _urls = _url.split(redirect_url);

		if (_urls[1] && _urls[1].length) {
			_urls[1] = _urls[1].split('==')[0];

			try {
				_urls[1] = base64Atob(_urls[1].split('&')[0]);
			} catch (e) {
			}

			if (_urls.length > 2) {
				for (var i = 0; i < _urls.length; i++) {
					i > 1 && (_urls[1] += redirect_url + _urls[i]);
				}
			}

			_url = _urls[1].split('.html');

			if (_url && _url.length > 1) {
				if (_url.length > 2) {
					for (var i = 0; i < _url.length; i++) {
						i > 1 && (_url[1] += '.html' + _url[i]);
					}
				}
				_url1 = _url[1];
			}

			_url0 = _url[0];
		} else {
			_url0 = 'home/index';
		}
	} else {
		_url = _url.replace('null/xgbuy/views/#', ''); // 纠正错误链接
		var _urls = _url.split('?');

		if (_urls[1] && _urls[1].length) {
			_url0 = _urls[0].replace(redirect_url, '') + (/\.html/g.test(_url) ? '' : (_urls[0].split('/').length > 1 ? '' : '/index'));
			_url1 = '?' + _urls[1];
		} else {
			_url0 = _url.replace(redirect_url, '') + (/\.html/g.test(_url) ? '' : (_url.split('/').length > 1 ? '' : '/index'));
		}
	}

	search_url = '';

	var _sys = '',
		_tab = '',
		_app = '',
		_new = '',
		_member = '';

	var _openid = '',
		_trackingid = '1631224844532744',
		_convertid1 = '1631769327523852',
		_convertid2 = '1631769351607308';

	if (_url1) {
		search_url = _url1.replace(/\?/g, '&').split('&');

		for (var i = 0; i < search_url.length; i++) {
			var _a = search_url[i].split('='),
				_b = _a[1];

			switch (_a[0]) {
				case 'sys':
					_sys = _b;
					break;
				case 'tab':
					_tab = _b;
					break;
				case 'new':
					_new = _b;
					break;

				case 'openid':
					_openid = _b;
					break;
				case 'trackingId':
					_trackingid = _b;
					break;
				case 'convertId':
					_convertid1 = _b;
					break;
				case 'convertId2':
					_convertid2 = _b;
					break;

				case 'memberId':
					_member = _b;
					break;
			}
		}
	}

	_tab ? page_body.addClass('tab') : page_body.removeClass('tab');

	if (_member) {
		member.id = _member;
		updateMember();
	}

	if (_sys) {
		member.app = 1;
		member.sys = _sys;
		appStyleFit();
	} else {
		_sys = member.sys;
	}

	if (_openid) {
		douyin_track = _trackingid;
		win_lc.setItem('douyin_track', douyin_track);

		douyin_convert1 = _convertid1;
		win_lc.setItem('douyin_convert1', douyin_convert1);

		douyin_convert2 = _convertid2;
		win_lc.setItem('douyin_convert2', douyin_convert2);

		global_douyin = 1;
		win_lc.setItem('douyin', 1);
		member.psys = 6;
	}

	_url0 = _url0.replace('.html', '') + '.html';
	lead_url = location.href.split('views')[0];

	for (var i = 0; i < oldFilePath.length; i++) {
		if (oldFilePath[i].old == _url0) {
			_url0 = oldFilePath[i].news;
			break;
		}
	}

	var _str = _url0.replace('/index.html', '').replace('.html', ''),
		_uu = lead_url + 'views/index.html?redirect_url=' + base64Btoa(_url0 + _url1),
		_id = _str.split('/').join('_'),
		_page = _new ? (_id + '_' + _new) : _id;

	prev_url = real_url;
	setStorage('prev_url', prev_url);

	!$('.page[data-page="' + _page + '"]') && ($('.page[data-id="' + _id + '"]').attr('data-page', _page));

	_refresh && (_refresh == 'self' ? $('.page[data-page="' + _page + '"]').remove() : $('.page').remove());

	var page_id = $('.page[data-page=' + _page + ']');
	real_url = lead_url + 'views/index.html?redirect_url=' + _url0 + _url1;


	// 清除app登录计时器(针对app未成功登录)
	cancelAnimationFrame(request_app_login);

	// 关闭音乐
	var _audio = $('audio');

	_audio.length && $.each(_audio, function () {
		this.pause();
		$(this).parent().removeClass('play');
	});

	post_id = _str.replace(/\//g, '_');

	if (page_id[0]) {
		page_id.siblings('.page').hide().end().show();
		pageActive(_str, _uu, page_id, fn, _pop, _title, _sys);
	} else {
		var _m1 = false;

		for (var i = 0; i < footerPage.length; i++) {
			if (footerPage[i] == _str) {
				_m1 = true;
				break;
			}
		}

		!_m1 && loadShow();
		$.ajax({
			method: 'get',
			url: _url0,
			datatype: 'html',
			cache: false,
			success: function (e) {
				page_id.remove();
				$('.page').hide();
				page_section.append(e);
				page_id = $('.page[data-id="' + _id + '"]:visible');
				page_id.attr('data-page', _page);
				pageActive(_str, _uu, page_id, fn, _pop, _title, _sys);

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
				if (_sys) {
					loadHide();
				} else {
					setTimeout(function () {
						loadHide();
					}, 100);
				}
			}
		});
	}
}

var timerPageActive = null;

function pageActive(_str, _uu, elem, fn, _pop, _title, _sys, _new) {
	var _header = elem.find('header:visible'),
		_headerp = _header.find('p').text();

	var _m1 = false,
		_m2 = false;

	for (var i = 0; i < wxHeaderHide.length; i++) {
		if (wxHeaderHide[i] == _str) {
			_header.addClass('hide');
			break;
		}
	}

	_title = _headerp ? _headerp : (_title || elem.data('title'));

	if (_title) {
		setTitle(_title);
	} else {
		clearTimeout(timerPageActive);

		timerPageActive = setTimeout(function () {
			!$('.page:visible header p').text() && setTitle(initial_title);
		}, 150);
	}

	for (var i = 0; i < footerPage.length; i++) {
		if (footerPage[i] == _str) {
			_m1 = true;
			break;
		}
	}

	if (_m1) {
		page_footer.removeClass('hide').find('a.' + _str).addClass('active').siblings('a').removeClass('active');
		page_section.addClass('has-footer');

		// getCartPiece(function (piece) {
		// 	piece ? page_footer.find('.cart').append(piece) : page_footer.find('i').remove();
		// });
	} else {
		page_footer.addClass('hide').find('a').removeClass('active');
		page_section.removeClass('has-footer');
		// removeStorage('cart_piece');
	}

	for (var i = 0; i < pushSkipPage.length; i++) {
		if (pushSkipPage[i] == _str.replace(/\//g, '_')) {
			_m2 = true;
			break;
		}
	}

	if ((first_url != _uu) && !_m2) {
		first_url ? !_pop && history.pushState(null, null, _uu) : history.replaceState(null, null, _uu);
		first_url = _uu;
	}

	pop_state = true;
	fn && fn();

	if (_sys) {
		loadHide();
	} else {
		setTimeout(function () {
			loadHide();
		}, 100);
	}

	console.info(real_url);

	var _c = win_ls.getItem("column")
	if (_c != null) {
		_c = JSON.parse(_c)
	} else {
		_c = {
			type: "",
			id: ""
		}
	}
	var s = real_url.split("?redirect_url=")[1].split('?')[0]


	if (prev_url.indexOf("cart/index.html") > -1 && real_url.indexOf("order/firm/index.html") == -1) {
		_c = {
			type: "27",
			id: ""
		}
	}
	switch (s) {
		case "home/index.html":
			_c = {
				type: "1",
				id: ""
			}
			break;
		case "activity/templet/brand_decorate.html":
			for (var i = 0; i < search_url.length; i++) {
				if (search_url[i].indexOf("pageid") > -1) {
					_c = {
						type: "2",
						id: search_url[i].split('=')[1]
					}
				} else if (search_url[i].indexOf("activityAreaId") > -1) {
					_c = {
						type: "3",
						id: search_url[i].split('=')[1]
					}
				}
			}
			break;
		case "activity/templet/brand_def.html":
			for (var i = 0; i < search_url.length; i++) {
				if (search_url[i].indexOf("activityAreaId") > -1) {
					_c = {
						type: "3",
						id: search_url[i].split('=')[1]
					}
				}
			}
			break;
		case "activity/templet/single_def.html":
			for (var i = 0; i < search_url.length; i++) {
				if (search_url[i].indexOf("activityAreaId") > -1) {
					_c = {
						type: "3",
						id: search_url[i].split('=')[1]
					}
				}
			}
			break;
		case "mall/index.html":
			_c = {
				type: "4",
				id: ""
			}
			break;
		case "newman/index.html":
			_c = {
				type: "5",
				id: ""
			}
			break;
		case "activity/templet/single_explosion.html":
			_c = {
				type: "6",
				id: ""
			}
			break;
		case "rushbuy/index.html":
			_c = {
				type: "7",
				id: ""
			}
			break;
		case "newman/seckill/index.html":
			_c = {
				type: "8",
				id: ""
			}
			break;
		case "integral/detail/index.html":
			_c = {
				type: "9",
				id: ""
			}
			break;
		case "activity/templet/single_duanma":
			_c = {
				type: "10",
				id: ""
			}
			break;
		case "activity/cutprice/index.html":
			_c = {
				type: "11",
				id: ""
			}
			break;
		case "activity/freeprice/index.html":
			_c = {
				type: "12",
				id: ""
			}
			break;
		case "activity/newsign/index.html":
			_c = {
				type: "13",
				id: ""
			}
			break;
		case "activity/reduceprice/index.html":
			_c = {
				type: "14",
				id: ""
			}
			break;
		// case "":
		// 	_c = {
		// 		type: "15",
		// 		id: ""
		// 	}
		// 	break;
		// case "":
		// 	_c = {
		// 		type: "16",
		// 		id: ""
		// 	}
		// 	break;
		// case "":
		// 	_c = {
		// 		type: "17",
		// 		id: ""
		// 	}
		// 	break;
		case "activity/novaplan/index.html":
			_c = {
				type: "18",
				id: ""
			}
			break;
		// case "":
		// 	_c = {
		// 		type: "19",
		// 		id: ""
		// 	}
		// 	break;
		// case "":
		// 	_c = {
		// 		type: "20",
		// 		id: ""
		// 	}
		// 	break;
		// case "":
		// 	_c = {
		// 		type: "21",
		// 		id: ""
		// 	}
		// 	break;
		// case "":
		// 	_c = {
		// 		type: "22",
		// 		id: ""
		// 	}
		// 	break;
		// case "":
		// 	_c = {
		// 		type: "23",
		// 		id: ""
		// 	}
		// 	break;
		// case "":
		// 	_c = {
		// 		type: "24",
		// 		id: ""
		// 	}
		// 	break;
		// case "":
		// 	_c = {
		// 		type: "25",
		// 		id: ""
		// 	}
		// 	break;
		case "":
			_c = {
				type: "26",
				id: ""
			}
			break;
		case "my/index.html":
			_c = {
				type: "28",
				id: ""
			}
			break;
		// case "":
		// 	_c = {
		// 		type: "29",
		// 		id: ""
		// 	}
		// 	break;
		case "main_venue/index.html":
			var venue = $("#section1").attr("data-page-active")
			$("#section1>div[data-page=" + venue + "]").show()
			break
	}

	win_ls.setItem("column", JSON.stringify(_c))


	if (prev_url && s == ('home/index.html')) {
		// 后退首页 轮播图继续轮播
		setTimeout(function () {
			var mySwiper = document.querySelector('.swiper-container').swiper
			mySwiper && mySwiper.slideToLoop(0);
		}, 1000)
	}
}

function shareQQ(_title, _url) {
	Untapped();
}

function shareWB(_title, _url) {
	Untapped();
}

// 弹窗
function alertTip(_txt, _obj) {
	if (_txt == '') return;

	_obj = _obj || {};

	var _tip = $('.page:visible .alert-tip').eq(0);

	$('.popup').removeClass('show');
	$('.page:visible .scroll-more').css('pointer-events', 'none');

	if (_tip.length) {
		_tip.find('p').html(_txt);
		_tip.addClass('show');
	} else {
		$('.page:visible').append('<div onclick="closeSelf(this)" class="popup alert-tip">'
			+ '<div class="popup-center popup-tip" onclick="stopSelf()">'
			+ '<div>'
			+ '<h3 class="flex ac jc">提醒</h3>'
			+ '<p class="flex jc txt scroll-y' + (_obj.left ? ' left' : '') + '">' + _txt + '</p>'
			+ '</div>'
			+ '<div class="flex">'
			+ '<a class="btn flex ac jc fg" onclick="alertTipBtn(this, ' + _obj.success + ')">' + (_obj.btn ? _obj.btn : '确认') + '</a>'
			+ '</div>'
			+ '</div>'
			+ '</div>');

		$('.page:visible .alert-tip').eq(0).addClass('show');
	}
}

function alertTipBtn(_this, _f) {
	closeSelf(_this);
	_f && _f();
}

function confirmTip(_txt, fn, _name, _cancel) {
	if (_txt == '') return;

	var _tip = $('.page:visible .confirm-tip');
	$('.page:visible .scroll-more').css('pointer-events', 'none');

	_tip.length && _tip.remove();
	$('.popup').removeClass('show');

	$('.page:visible').append('<div class="popup confirm-tip">'
		+ '<div class="popup-center popup-tip">'
		+ '<div>'
		+ '<h3 class="flex ac jc">提醒</h3>'
		+ '<p class="flex jc txt scroll-y">' + _txt + '</p>'
		+ '</div>'
		+ '<div class="flex">'
		+ '<a class="btn flex fg ac jc c9 bdr" onclick="closeSelf(this)">' + (_cancel || '取消') + '</a>'
		+ '<a class="btn flex fg ac jc popup-click f50">' + (_name || '确定') + '</a>'
		+ '</div>'
		+ '</div>'
		+ '</div>');

	_tip = $('.page:visible .confirm-tip');

	_tip.addClass('show');

	_tip.find('.popup-click').on('click', function (evt) {
		defSelf(evt);
		fn();
		closeSelf('.confirm-tip');
	});
}

var delay_tip_timer = null;

function delayTip(_txt, _time, _f, _m) {
	if (_txt == '' || _txt === undefined) return;

	var _tip = $('.page:visible .delay-tip');

	_time = _time || 3e3;
	clearTimeout(delay_tip_timer);

	if (_tip.length) {
		_tip.children().html(_txt);
		_tip.addClass('show');
		_m ? _tip.addClass('mask') : _tip.removeClass('mask');
	} else {
		$('.page:visible').append('<div class="delay-tip show' + (_m ? ' mask' : '') + '"><div class="scroll-y">' + _txt + '</div></div>');
		_tip = $('.page:visible .delay-tip');
	}

	delay_tip_timer = setTimeout(function () {
		_tip.removeClass('show');
		_f && _f();
	}, _time);
}

function popupShow(e, evt, fn) {
	defSelf(evt);
	typeof e === 'string' && (e = $('.page:visible ' + e));
	$('.page:visible .scroll-more').css('pointer-events', 'none');
	e.addClass('show');
	fn && fn(e);
}

function closeSelf(_this) {
	defSelf();
	_this ? ($(_this).hasClass('popup') ? $(_this).removeClass('show') : $(_this).closest('.popup').removeClass('show')) : $('.page:visible .popup').removeClass('show');
	$('.page:visible .scroll-more').css('pointer-events', 'auto');
}

function stopSelf(e) {
	e = e || window.event;
	e && e.stopPropagation();
}

function defSelf(e) {
	e = e || window.event;
	e && e.preventDefault();
}

// 屏蔽
function shield(msg) {
	var _shield = $('.page:visible .shield');

	if (_shield.length) {
		_shield.text(msg || '由于你的违规行为，已被屏蔽！').addClass('show');
	} else {
		_shield = $('<div class="shield popup show"></div>');
		_shield.text(msg || '由于你的违规行为，已被屏蔽！');
		$('.page:visible').append(_shield);
	}
}

// 图片预览
function previewPic(_this, _index) {
	var $ts = $(_this);
	_slide = _index === undefined ? $ts.index() : _index,
		_pre = $('.page:visible .preview-pic'),
		_str = '',
		_arr = [];

	_pre.length && _pre.remove();

	$.each($ts.closest('.list-pic').find('.img-pre'), function () {
		_arr.push($(this).children().attr('src'));
	});

	for (var i = 0; i < _arr.length; i++) {
		_str += '<li class="swiper-slide" onclick="closeSelf(this)"><img src="' + _arr[i] + '"></li>';
	}

	$('.page:visible').append('<div class="popup preview-pic end">'
		+ '<div class="swiper-container">'
		+ '<ul class="swiper-wrapper">' + _str + '</ul>'
		+ '<div class="pagination"></div>'
		+ '</div>'
		+ '</div>');

	_pre = $('.page:visible .preview-pic');
	_pre.addClass('show');

	initSwiper({
		container: _pre.children(),
		autoplay: false,
		loop: false,
		slide: _slide
	});
}

/*
* @qp

* _url  请求接口

* _obj  {data, method, target, success, error, complete}
* 	data	请求参数
* 	method	请求方法[post]
* 	success	请求成功
* 	error	请求失败
* 	complete	请求完成

*	limit 	限制连续请求
* 	target 	请求状态绑定对象
* 	before	限制连续请求, 请求前的回调函数

*	appLogin app登录中
* 	disLogin 不触发登录

* new ajax(getApi.getInviteDetail, { data: {}, success: {} });
*/
function ajax(_url, _obj) {
	var self = this;

	self.url = _url;
	self.data = _obj.data;
	self.method = _obj.method || 'post';
	self.success = _obj.success;
	self.error = _obj.error;
	self.complete = _obj.complete;

	self.limit = _obj.limit;
	self.target = _obj.target || self.limit;
	self.before = _obj.before;

	self.target && typeof self.target != 'object' && (self.target = page_body);
	self.async = _obj.async != 'undefined' ? _obj.async : true

	if (self.limit) {
		if (self.target.hasClass('ajax-ing')) {
			return;
		} else {
			self.before && self.before();

			self.target.removeClass('ajax-ed').addClass('ajax-ing');

			requestAnimationFrame(function () {
				self.target.addClass('ajax-ed');
			});
		}
	}
	self.request = $.ajax({
		method: self.method,
		url: self.url,
		data: JSON.stringify({
			reqData: self.data || {},
			system: member.psys,
			version: member.appver,
			token: member.token,
			timeStamp: Date.now(),
			nonceStr: "OV5QOVX21A0446K8",
			signature: "7f8c4ad7f6d349cf28879670b9fe79b9"
		}),
		async: self.async,
		success: function (res) {
			typeof (res) != 'object' && (res = JSON.parse(res));

			if (res.returnCode == '1001' || res.returnCode == '1002') {
				res.returnCode == '1002' && delayTip(res.returnMsg);

				if (_obj.disLogin) {
					self.error && self.error();
				} else {
					memberFn();
					_obj.appLogin && member.app && _obj.appLogin();
				}
			} else if (res.returnCode == '0000') {
				self.success && self.success(res);
			} else if (res.returnCode == '3001') {
				delayTip(res.returnMsg);
			} else {
				self.error ? self.error({
					returnCode: res.returnCode,
					returnMsg: res.returnMsg
				}) : delayTip(res.returnMsg);
			}
		},
		error: function (res) {
			self.error ? self.error({
				returnCode: res.returnCode,
				returnMsg: res.returnMsg
			}) : delayTip(res.returnMsg);
		},
		complete: function () {
			self.complete && self.complete();
			self.target && self.target.removeClass('ajax-ing');
		}
	});

	return self;
};

// 插入样式
function addStylesheetRules(e) {
	var a = document.createElement('style');
	document.head.appendChild(a);

	for (var a = a.sheet, i = 0; i < e.length; i++) {
		var j = 1,
			b = e[i],
			k = e[i][0],
			f = '';

		'[object Array]' === Object.prototype.toString.call(b[1][0]) && (b = b[1], j = 0);

		for (; j < b.length; j++) {
			var g = b[j];
			f = f + (g[0] + ':' + g[1] + (g[2] ? ' !important' : '') + ';');
		}

		a.insertRule(k + '{' + f + '}', a.cssRules.length);
	}
}

// 获取(用户id, token, web或app, ios或anz)
function getMemberId(mid, tok, web, sys, net) {
	mid && (member.id = mid);
	tok && (member.token = tok);
	web && (member.web = web.toLowerCase());
	sys && (member.sys = sys.toLowerCase());
	net && (member.net = net)
	member.app = 1;
	member.psys = (member.sys == 'ios' ? 1 : 2);
	appStyleFit();
	updateMember();
	queryMember.id = mid;
	// queryMember.token = tok;
}

// 获取APP的统计数据
function getAppParams(_obj) {
	if (!_obj) return;

	_obj = JSON.parse(_obj);
	if (_obj.fromPvId) {
		traffic.fromApp.pageType = _obj.fromPageType;
		traffic.fromApp.pvId = _obj.fromPvId;

		if (traffic.setPvParam1) {
			traffic.list.pop();
			traffic.listPage.pop();
			traffic.setPv(traffic.setPvParam1, traffic.setPvParam2);
		}
	}
	if (_obj.fromDeviceNumber) {
		win_lc.setItem("deviceNumber", _obj.fromDeviceNumber)
	}
	if (_obj.fromColumnType) {
		win_lc.setItem("column", {
			type: _obj.fromColumnType,
			id: ""
		})
	}
}
//手机权限调用
function getAppHWStatus(_obj) {
	if (!_obj) return;
	_obj = JSON.parse(_obj);
	win_lc.setItem("HWcamera", _obj.camera)
	win_lc.setItem("HWphoto", _obj.photo)
}

//随机生成设备码
function getDeviceNumber() {
	var deviceNumber = win_lc.getItem("deviceNumber")
	if (!deviceNumber) {
		var date = new Date()
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var day = date.getDate();

		var hour = date.getHours();
		var minute = date.getMinutes();
		var second = date.getSeconds();
		var Milliseconds = date.getMilliseconds()

		//这样写显示时间在1~9会挤占空间；所以要在1~9的数字前补零;
		if (month < 10) {
			month = '0' + month;
		}
		if (day < 10) {
			day = '0' + day;
		}
		if (hour < 10) {
			hour = '0' + hour;
		}
		if (minute < 10) {
			minute = '0' + minute;
		}
		if (second < 10) {
			second = '0' + second;
		}
		if (Milliseconds < 10) {
			Milliseconds = '00' + Milliseconds;
		} else if (10 < Milliseconds < 100) {
			Milliseconds = '0' + Milliseconds;
		}
		var text = "";
		var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		for (var i = 0; i < 15; i++)
			text += possible.charAt(Math.floor(Math.random() * possible.length));

		deviceNumber = '' + year + month + day + hour + minute + second + Milliseconds + text
		win_lc.setItem("deviceNumber", deviceNumber)
	}

	return deviceNumber
}

//获取栏目
function getColumn() {
	var column = {
		type: "",
		id: ""
	}
	var str_column = win_ls.getItem("column")
	try {
		if (str_column) {
			column = JSON.parse(win_ls.getItem("column"))
		}
	} catch (error) {
		console.log(error)
		column = {
			type: "",
			id: ""
		}
	}
	return column
}

//通知方法getMemberId 加载完成
if (member.sys == 'ios') {
	var dic = {
		'handlerInterface': 'intentTrailerListNative', 'function': 'H5Ready', 'parameters': {}
	};

	if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
		window.webkit.messageHandlers['intentTrailerListNative'].postMessage(dic);
	} else {
		window['intentTrailerListNative']['getH5Params'](JSON.stringify(dic));
	}
} else if (member.sys == 'android') {
	window.H5PluginManager.getDeviceInfo()
}

//  app样式兼容
function appStyleFit() {
	page_body.addClass('sys-app');

	if (member.sys == 'ios' && member.appver - 50 < 0) {
		try {
			(iosVersion <= 10 || iosVersion == 12) && page_body.addClass(isiOSX ? 'sys-iosx' : 'sys-ios');
		} catch (e) {
		}
	}
}

/*****************************/

// 跳转 - 活动
function getSingleHot(_id, _name) {
	stopSelf();
	getUrl('activity/templet/single_hot.html?activityId=' + _id, 'self', null, false, _name);
}

// 跳转 - 活动列表
function getUrlActive(e, _obj) {
	_obj = _obj || {};

	var _name = $(e).data('name'),
		_url = $(e).data('url'),
		_source = $(e).data('source'),
		_id = $(e).data('activityid'),
		_act = $(e).data('activityareaid'),

		_new = _source + '_' + _act;

	if (_source == 1) {
		if (_url) {
			getUrl(_url, 'self');
		} else {
			$.ajax({
				method: 'post',
				url: getApi.areaTempletByAreaId + '?activityAreaId=' + _act,
				success: function (e) {
					var _u = e.returnData.areaUrl;

					if (member.sys == 'ios') {
						callIntentTrailer('intentTrailerListNative', 'JumpWebView', { 'url': _u });
					} else {
						_u += /\?/g.test(_u) ? '&' : '?';
						getUrl(_u + 'new=' + _new, 'self', null, false, _name);
					}
				},
				error: function (e) {
					delayTip(e.returnMsg);
				}
			});
		}

		_obj.data && _act && traffic.setDtl(_obj.data, {
			type: 1,
			id: _act,
			pos: _obj.pos
		});
	} else {
		// getSingleHot(_id, _name);
		getUrlActiveSingle1(e)

		_obj.data && _id && traffic.setDtl(_obj.data, {
			type: 2,
			id: _id,
			pos: _obj.pos
		});
	}
}

// 跳转 - 活动列表(购物车)
function getUrlActive1(_this) {
	var _d = $(_this),
		_act = _d.data('activityareaid'),
		_type = _d.data('activityareatype'),
		_atype = _d.data('activitytype'),
		_id = _d.data('activityid'),
		_source = _d.data('source');

	if (_atype == 101) {
		getUrlShop(_act);
	} else {
		if (_act == -1) {
			// 新人专享
			getUrl('newman/index.html?type=1', 'self');
		} else if (_act == -2) {
			// 爆款活动
			getUrl('activity/templet/single_explosion.html?type=2', 'self');
		} else if (_act == -3) {
			// 限时抢购
			getUrl('rushbuy/index.html?type=3', 'self');
		} else if (_act == -4) {
			// 新人秒杀
			getUrl('newman/seckill/index.html');
		} else if (_act == -5) {
			// 积分兑换
			getUrl('integral/detail/index.html');
		} else if (_act == -6) {
			// 断码清仓
			getUrl('activity/templet/single_duanma', 'self');
		} else {
			if (_source == 2) {
				getSingleHot(_id);
			} else {
				if (_type == 1) {
					getUrl('activity/templet/brand_def.html?activityAreaId=' + _act, 'self');
				} else if (_type == 2 || _type == 3) {
					getUrl('activity/templet/single_def.html?activityAreaId=' + _act, 'self');
				} else if (_type == 6) {
					getSingleHot(_id);
				} else if (_type == 7) {
					getUrl('newman/index.html?type=1', 'self');
				}
			}
		}
	}
}

// 跳转 - 会场/活动/商品详情/外部链接/主会场
function getBrandDef(e, _obj) {
	_obj = _obj || {};

	var _name = e.getAttribute('data-name'),
		_type = e.getAttribute('data-type'),
		_url = e.getAttribute('data-url'),
		_link = e.getAttribute('data-link'),
		_id = e.getAttribute('data-id'),
		_pid = e.getAttribute('data-productid'),
		_new = _type + _url;

	_obj.data && traffic.setDtl(_obj.data, {
		type: 4,
		id: _id,
		pos: _obj.pos
	});
	if (_type == 1) {
		if (_url) {
			getUrl(_url, 'self', null, false, _name);
		} else {
			new ajax(getApi.areaTempletByAreaId, {
				data: {
					activityAreaId: _link,
				},
				success: function (e1) {
					// 其他链接
					getUrl(e1.returnData.areaUrl, 'self', null, false, _name);
				}
			});
		}

	} else if (_type == 2) {
		// 特卖
		getSingleHot(_link || _pid, _name);
	} else if (_type == 3) {
		// 商品详情
		getUrlGoods(e);
	} else if (_type == 4) {
		// 外部链接
		location.href = _link;
	} else if (_type == 6) {
		if (_pid == 1) {
			// 新人专享
			getUrl('newman/index.html?type=1', 'self');
		} else if (_pid == 2) {
			// 爆款
			getUrl('activity/templet/single_explosion.html?type=2', 'self');
		} else if (_pid == 3) {
			// 限时抢购
			getUrl('rushbuy/index.html?type=3', 'self');
		} else if (_pid == 4) {
			// 新人秒杀
			getUrl('newman/seckill/index.html');
		} else if (_pid == 5) {
			// 积分商城
			getUrl('integral/detail/index.html');
		} else if (_pid == 6) {
			// 断码清仓
			getUrl('activity/templet/single_duanma', 'self');
		} else if (_pid == 7) {
			// 签到
			getUrl('activity/newsign/index.html', 'self');
		} else if (_pid == 8) {
			// 砍价
			getUrl('activity/cutprice/index.html', 'self');
		} else if (_pid == 9) {
			// 邀请免单
			getUrl('activity/freeprice/index.html', 'self');
		} else if (_pid == 10) {
			// 有好货
		} else if (_pid == 11) {
			// 每日好店
		} else if (_pid == 12) {
			// 新品牌团（第一个分类）
		} else if (_pid == 13) {
			//  拉新分润好货推荐
			getUrl('activity/novaplan/pages/goods/index.html', 'self');
		} else if (_pid == 14) {
			// 新星计划
			getUrl('activity/novaplan/index.html', 'self');
		} else if (_link == 24) {
			// 主会场
			getUrl('main_venue/index.html', 'self');
		}
	} else if (_type == 7) {
		// 自定义装修
		var _url1 = 'activity/templet/brand_decorate.html?pageid=' + _pid;

		if (member.sys == 'ios') {
			callIntentTrailer(_native, 'JumpWebView', { 'url': _url1 });
		} else {
			getUrl(_url1, 'self');
		}
	} else if (_type == 8) {
		// 微淘关键字
	} else if (_type == 9) {
		// 新品牌团
	} else if (_type == 10) {
		// 商家店铺 
		getUrlShop(_link);
	} else if (_type == 11) {
		// 旧品牌特卖一级类目
		getUrl('activity/templet/brand_custom.html?productTypeId=' + _pid);
	} else if (_type == 12) {
		// 淘宝优选频道
	} else if (_type == 13) {
		// 新品牌团二级分类
		getUrl('activity/templet/brand_group.html?type=16&productType2Ids=' + _link +'&new=' + _new, 'self',null,false,'品牌特卖');

	} else if (_type == 14) {
		// 有好货二级分类
	} else if (_type == 15) {
		// 有好货品牌ID
	} else if (_type == 16) {
		// 潮鞋上新二级分类
	} else if (_type == 17) {
		// 潮鞋上新品牌ID
	} else if (_type == 18) {
		// 潮流男装二级分类
	} else if (_type == 19) {
		// 潮流男装品牌ID
	} else if (_type == 20) {
		// 断码特惠二级分类
	} else if (_type == 21) {
		// 断码特惠品牌ID
	} else if (_type == 22) {
		// 运动鞋服二级分类
	} else if (_type == 23) {
		// 运动鞋服品牌ID
	} else if (_type == 24) {
		// 潮流美妆二级分类
	} else if (_type == 25) {
		// 潮流美妆品牌ID
	} else if (_type == 26) {
		// 食品超市二级分类
	} else if (_type == 27) {
		// 食品超市品牌ID
	} else if (_type == 28) {
		// 商城一级分类
		if (member.sys == 'android') {
			intentNewShopmallPage(_link);
		} else if (member.sys == 'ios') {
			callIntentTrailer(_native, 'intentNewShopmallPage', { 'categoryId': _link });
		} else {
			getUrl('mall/index.html?shoptype=' + _link);
		}
	} else if (_type == 29) {
		// 优惠券ID
		new ajax(member.app ? getApi.addReceiveCouponH5 : getApi.addReceiveCoupon, {
			data: {
				couponId: _pid || _link,
				memberId: member.id
			},
			success: function (res) {
				alertTip(res.returnData.content);
			},
			error: function (res) {
				alertTip(res.returnMsg);
			},
			complete: function () {
				decorate_post_coupon = 1;
			},
			appLogin: function () {
				decorate_post_coupon = 1;
			}
		});
	} else {
		Untapped();
	}
}

// 跳转 - 店铺
function getUrlShop(e) {
	stopSelf();
	var _id = typeof e === 'object' ? e.getAttribute('data-id') : e;

	if (member.sys == 'ios') {
		checkAppVer(43, function () {
			callIntentTrailer('intentTrailerListNative', 'intentShopping', { 'mchtId': _id });
		}, function () {
			getUrl('seller/index.html?mchtId=' + _id, 'self');
		});
	} else if (member.sys == 'android') {
		checkAppVer(36, function () {
			intentShopping(_id);
		}, function () {
			getUrl('seller/index.html?mchtId=' + _id, 'self');
		});
	} else {
		getUrl('seller/index.html?mchtId=' + _id, 'self');
	}
}

// 领取优惠券
function getCoupon(e) {
	for (var i = 0; i < member.list.length; i++) {
		var _list = member.list[i];

		if (e.getAttribute('data-id') == _list.couponId) {
			if (_list.recType == 2) {
				confirmTip('兑换优惠券需' + _list.needIntegral + '积分', function () {
					getCouponTip(e, i);
					closeSelf();
				}, '兑换');
			} else {
				getCouponTip(e, i);
			}
			break;
		}
	}
}

function getCouponTip(e, _i) {
	new ajax(member.app ? getApi.addReceiveCouponH5 : getApi.addReceiveCoupon, {
		data: {
			couponId: e.getAttribute('data-id'),
			memberId: member.id
		},
		success: function (data) {
			var _list = data.returnData,
				_tip = _list.content,
				_lists = member.list[_i];

			// 是否允许领取, 积分是否够
			if (_list.isCanReceive && _list.mark) {
				// 免费兑换
				if (_lists.recLimitType == 1) {
					// 每人每天限领1张
					getCouponActive(e);
				} else if (_lists.recLimitType == 2) {
					// 每人限领指定数量
					if (_lists.memberCouponSize < _lists.recEach) {
						member.list[_i].memberCouponSize++;
						member.list[_i].memberCouponSize == _lists.recEach && getCouponActive(e);
						delayTip('还可领取' + (_lists.recEach - member.list[_i].memberCouponSize) + '次');
					}
				} else {
					// 不限
				}
			} else {
				getCouponActive(e);
			}

			alertTip(_tip);
			getCouponFlesh();
		}
	});
}

function getCouponFlesh() {
	if (member.sys == 'android') {
		checkAppVer(41, function () {
			anFleshCoupon();
		}, true);
	} else if (member.sys == 'ios') {
		checkAppVer(44, function () {
			callIntentTrailer('intentTrailerNative', 'succesCoupons');
		}, true);
	} else {
		$('.page[data-id="coupon"]').remove();
	}
}

function getCouponActive(e) {
	$(e).html('已领取').removeAttr('onclick').removeClass('f50').addClass('c9');
}

// 外部链接或请求路径, 标题, 返回的路径, 是否返回刷新
function linkPage(_url, _title, _urls, _self, _pop) {
	setStorage('link_page', JSON.stringify({
		url: _url,
		title: _title,
		urls: _urls,
		self: _self
	}));

	getUrl('link', 'self', null, _pop);
}

// 获取快递信息
function orderExpress(_sub, _id, _no, _src) {
	setStorage('order_express', JSON.stringify({
		subOrderId: _sub,
		expressId: _id,
		expressNo: _no,
		headerImg: _src
	}));

	getUrl('order/express/index.html', 'self');
}

// 去支付
function orderPayType(_com, _amount, _type) {
	new ajax(getApi.payType, {
		data: {
			type: _type,
			memberId: member.id,
			combineOrderId: _com,
		},
		success: function (e) {
			var _bot = $('.page:visible .popup-paymethod .bot-2'),
				_footer = _bot.siblings('.order-detail-footer'),
				_list = e.returnData.payMapList,
				_len = _list.length;

			var _str = '';
			console.log(_len)
			if (_len) {
				for (var i = 0; i < _len; i++) {
					if (_list[i].PayType == 6) {
						_str += '<div class="cursor flex ac jb" onclick="orderPayTypeTick(event, this)"><span class="flex ac"><i class="ico" style="background-image: url(' + _list[i].payIcon + ')"></i>' + _list[i].payName + '<span class="flex ac jc pay-type">支持花呗</span></span><i class="tick ico-btn' + (_list[i].defaultPay === '1' ? ' ticked' : '') + '" data-type="' + _list[i].PayType + '" data-id="' + _list[i].payId + '"></i></div>';
					} else {
						_str += '<div class="cursor flex ac jb" onclick="orderPayTypeTick(event, this)"><span class="flex ac"><i class="ico" style="background-image: url(' + _list[i].payIcon + ')"></i>' + _list[i].payName + '</span><i class="tick ico-btn' + (_list[i].defaultPay === '1' ? ' ticked' : '') + '" data-type="' + _list[i].PayType + '" data-id="' + _list[i].payId + '"></i></div>';
					}
				}

				_bot.html(_str);
				_footer.find('strong').html('¥' + _amount);
				_footer.find('span').attr('data-id', _com);
				popupShow('.popup-paymethod');
			}
		}
	});
}

// 勾选payId
function orderPayTypeTick(event, _this) {
	defSelf(event);

	var _tick = $(_this).find('.tick');

	if (!_tick.hasClass('ticked')) {
		_tick.addClass('ticked');
		$(_this).siblings().find('.tick').removeClass('ticked');
	}
}

// 提交待付款订单
function orderPay(_this, _id) {
	if (typeof _this === 'object') {
		_id = $(_this).closest('.order-detail-footer').siblings('.bot-2').find('.ticked').data('id');
		_this = $(_this).data('id');
	}

	if (_id) {
		new ajax(getApi.submitAfterPay, {
			data: {
				memberId: member.id,
				combineOrderId: _this,
				ip: member.ip,
				payId: _id
			},
			success: function (e) {
				var _list = e.returnData;
				payTypeApi(_id, _list);
			}
		});
	} else {
		delayTip('请先勾选支付方式！');
	}
}

// 我的订单
function myOrder(e, _pre, fn, _pay) {
	if (member.sys == 'android') {
		intentMyorder();
	} else if (member.sys == 'ios') {
		checkAppVer('2.1.8', function () {
			callIntentTrailer('intentTrailerNative', 'intentMyorder');
		});
	} else if (_pay) {
		if (_pre) {
			if (getStorage('order_pay_earnest')) {
				removeStorage('order_pay_earnest');
				history.replaceState(null, null, real_url.split(redirect_url)[0] + redirect_url + 'order/earnest/index.html');
			}
		} else {
			if (getStorage('pay_from_firm')) {
				removeStorage('pay_from_firm');
				history.replaceState(null, null, real_url.split(redirect_url)[0] + redirect_url + 'order/index.html?type=' + e);
			}
		}
		fn && fn();
	} else {
		getUrl('order/index.html?type=' + e, 'self');
	}
}

function onBridgeReady(_f) {
	var _list = getStorage('pay_sign');

	if (_list) {
		_list = JSON.parse(_list);

		WeixinJSBridge.invoke('getBrandWCPayRequest', {
			appId: _list.appId,
			timeStamp: _list.timeStamp.toString(),
			nonceStr: _list.nonceStr,
			package: _list.package,
			signType: _list.signType,
			paySign: _list.paySign
		}, function (res) {
			if (res.err_msg === 'get_brand_wcpay_request:ok') {
				delayTip('支付成功', 1e3, function () {
					_f ? _f() : location.reload();
				});
			} else if (res.err_msg === 'system:access_denied' && android_pay_fix) {
				android_pay_fix = 0;
				onBridgeReady(_f);
			} else {
				delayTip('支付失败', 1e3, function () {
					location.reload();
				});
			}
		});
	}
}

function weChatPay(_f) {
	if (typeof WeixinJSBridge == "undefined") {
		if (document.addEventListener) {
			document.addEventListener('WeixinJSBridgeReady', function () {
				onBridgeReady(_f);
			}, false);
		} else if (document.attachEvent) {
			document.attachEvent('WeixinJSBridgeReady', function () {
				onBridgeReady(_f);
			});
			document.attachEvent('onWeixinJSBridgeReady', function () {
				onBridgeReady(_f);
			});
		}
	} else {
		onBridgeReady(_f);
	}
}

function payTypeApi(_payid, _list, _pre, _f) {
	if (_payid == 1 || _payid == 2) {
		if (member.sys == 'android') {
			checkAppVer(50, function () {
				switchPayorder(JSON.stringify(_list));
			});
		} else if (member.sys == 'ios') {
			checkAppVer(53, function () {
				callIntentTrailer('intentTrailerListNative', 'switchPayorder', _list);
			});
		}
	} else if (_payid == 4) {
		myOrder('', _pre, function () {
			setStorage('pay_sign', JSON.stringify(_list));
			weChatPay();
		}, 1);
	} else if (_payid == 5) {
		myOrder('', _pre, function () {
			location.href = _list.mweb_url;
		}, 1);
	} else if (_payid == 6) {
		myOrder('', _pre, function () {
			$('.page:visible').append('<div class="hide">' + _list.form + '</div>');
		}, 1);
	} else if (_payid == 7) {
		myOrder('', _pre, function () {
			getUrl('activity/certification/index.html');
		}, 1)
	}
}

// 原生支付成功调用该方法通知刷新减价页面
function refleshReducePricePage() {
	location.reload();
}

// 开售提醒
function getRemind(_this, _txt, _f) {
	stopSelf();
	var _type = _this.getAttribute('data-type');

	if ($(_this).hasClass('reminded')) {
		new ajax(member.app ? getApi.deleteRemindSaleH5 : getApi.deleteRemindSale, {
			data: {
				memberId: member.id,
				type: _type,
				remindType: _type,
				remindId: _this.getAttribute('data-id')
			},
			success: function () {
				$(_this).removeClass('reminded');
				_txt && $(_this).text('收藏');
				delayTip('已取消收藏');
				_f && _f(0);
				$('.page:visible').siblings('.page').remove();
			},
			limit: 1
		});
	} else {
		new ajax(member.app ? getApi.addRemindSaleH5 : getApi.addRemindSale, {
			data: {
				memberId: member.id,
				type: _type,
				remindType: _type,
				remindId: _this.getAttribute('data-id')
			},
			success: function () {
				$(_this).addClass('reminded');
				_txt && $(_this).text('已收藏');
				delayTip('收藏成功');
				_f && _f(1);
				$('.page:visible').siblings('.page').remove();
			},
			limit: 1
		});
	}
}

// 获取用户信息
function getMember(_f, _dislogin) {
	if (member.id) {
		_f();
	} else if (!member.app) {
		new ajax(getApi.userInfo, {
			success: function (res) {
				var _list = res.returnData;

				member.id = _list.memberId;
				member.nick = _list.nick;
				member.pic = _list.pic;
				member.mobile = _list.mobile;
				member.mVerfiyFlag = _list.mVerfiyFlag;
				member.isSvip = _list.isSvip;

				_f(_list);
				updateMember();
			},
			error: _f,
			disLogin: _dislogin
		});
	} else {
		_dislogin ? _f() : memberFn();
	}
}

// 小能客服
var NTKF_PARAM = {};

// 对接组id, 商品id, 订单id, 订单价格, 商品其他参数
function getUrlServer(_settingid, _itemid, _orderid, _orderprice, _param) {
	_settingid && getMember(function () {
		NTKF_PARAM = {
			"siteid": "je_1000",
			"sellerid": "1000",
			"settingid": _settingid,
			"uid": member.id,
			"uname": member.nick,
			"isvip": 0,
			"userlevel": 0,
			"itemid": _itemid,
			"orderid": _orderid,
			"orderprice": _orderprice,
			"itemparam": _param,
			"erpparam": ""
		};

		setStorage('ntkf_param', JSON.stringify(NTKF_PARAM));

		require(['https://dl.ntalker.com/js/xn6/ntkfstat.js?siteid=' + NTKF_PARAM.siteid], function () {
			console.log(NTKF_PARAM.settingid)
			NTKF.im_openInPageChat(NTKF_PARAM.settingid);
		});
	});
}

// 添加地址后 返回 更新商品详情 砍价 收货地址
function updateGoodsDetailAddress() {
	getUrl('');
	goodsDetailPopupAddrPost && goodsDetailPopupAddrPost(false, true);
}


/*
* 单文件上传图片 (input[file], {success, size, format})
* success({src, name, format, input[file]})
* size, 文件大小, 单位M, 默认10M
* format, 格式限制, 如[jpg, png, pdf], 默认不限
*/
var upload = {
	init: function (_file, _obj) {
		var _ts = this;
		_obj.size = _obj.size || 10;
		if (typeof FileReader === 'undefined') {
			delayTip('该浏览器不支持上传文件');
			_file.setAttribute('disabled', 'disabled');
			return;
		}

		_file.addEventListener('change', function () {
			_ts.change(this, _obj);
		});
	},

	change: function (_file, _obj) {
		var _ts = this,
			_di = 0

		_files = _file.files[_di],
			_type = _files.type.split('/'),
			_ln = Number(_file.getAttribute('data-ln')) || 0;

		if (_file.getAttribute('post')) {
			delayTip('上传图片中...');
			return;
		}

		if (!_files) return;

		if (_files.size > _obj.size * 1024 * 1024) {
			delayTip('最大不超过' + _obj.size + 'M');
			return;
		}

		if (_obj.length) {
			if (_ln + _file.files.length > _obj.length) {
				delayTip('最多上传' + _obj.length + '张图片!');
				return;
			}
		}

		if (_obj.format) {
			var _b = 1,
				_format = new RegExp(_type[1].substring(0, 2));

			for (var i = 0; i < _obj.format.length; i++) {
				if (_format.test(_obj.format[i])) {
					_b = 0;
					break;
				}
			}

			if (_b) {
				delayTip('仅支持' + _obj.format.join(',') + '格式');
				return;
			}
		}

		_file.setAttribute('post', '1');

		var reader = new FileReader();
		reader.readAsDataURL(_files);

		reader.onload = function (res) {
			var _o = {
				src: res.target.result,
				name: _files.name,
				type: _type[0],
				file: _file
			};

			_obj.success(_o);
			_file.removeAttribute('post');

			setTimeout(function () {
				_di++
				if (_di < _file.files.length) {
					_files = _file.files[_di];
					if (_files.size > _obj.size * 1024 * 1024) {
						delayTip('最大不超过' + _obj.size + 'M');
						return;
					}
					reader.readAsDataURL(_files);
				} else if (_di == _file.files.length) {
					delayTip('上传结束!');
				}
			}, 30)

		}

		reader.onerror = function () {
			_file.removeAttribute('post');
			delayTip('上传失败');
		}

	}
};

/*
*上传图片
* (input[file], {success, size, quality})
* success({src, name, width, height})
* size, max-width|max-heigth; auto[false]
* quality, jpg(0, 1]; png[flase]
*/
var uploadHand = {
	post: 1,

	init: function (_file, _obj) {
		var _ts = this;
		_ts.post = 1;

		if (typeof FileReader === 'undefined') {
			alertTip('Not support FileReader!');
			_file.setAttribute('disabled', 'disabled');
			return;
		}

		_file.addEventListener('change', function () {
			_ts.change(this, _obj);
		});
	},

	change: function (_file, _obj) {
		var _ts = this;

		if (!_ts.post) {
			delayTip('上传图片中...');
			return;
		}
		_ts.post = 0;

		var _files = _file.files;

		for (var i = 0; i < _files.length; i++) {
			(function (n) {
				var reader = new FileReader();
				reader.readAsDataURL(_files[i]);

				reader.onload = function () {
					var c = document.createElement('canvas'),
						x = c.getContext('2d'),
						m = new Image();

					m.src = this.result;

					m.onload = function () {
						var _w = this.width,
							_h = this.height,
							_w0 = _w,
							_h0 = _h;

						if (_obj.size) {
							if (_w < _h) {
								_h = Math.min(_obj.size, _h);
								_w = _w * _h / _h0;
							} else {
								_w = Math.min(_obj.size, _w);
								_h = _w * _h / _w0;
							}
						}

						c.width = _w;
						c.height = _h;

						x.drawImage(m, 0, 0, _w, _h);

						_obj.success({
							src: _obj.quality ? c.toDataURL('image/jpeg', _obj.quality) : c.toDataURL(),
							name: n,
							width: _w,
							height: _h
						});

						_ts.post = 1;
					};

					m.onerror = function () {
						_ts.post = 1;
					};
				}
			})(_files[i].name);
		}
	}
};

/*
* uploadFile(input[file], {url, size, length, success})
*/
function uploadFile(_file, _obj) {
	this.file = _file;
	this.url = _obj.url;
	this.size = _obj.size;
	this.length = _obj.length;
	this.success = _obj.success;

	this.post = 0;

	var _ts = this,
		_fl = _ts.file;


	if (typeof FileReader === 'undefined') {
		_fl.setAttribute('disabled', 'disabled');
		alertTip('该设备不支持上传文件, 请更换浏览器!');
		return;
	}

	_fl.addEventListener('change', function () {
		var _di = 0,
			_dl = _fl.files.length,
			_fs = _fl.files[_di],
			_fr = new FileReader(),
			_ln = Number(_fl.getAttribute('data-length')) || 0;

		if (_ts.post) {
			delayTip('上传文件中!');
			return;
		}
		_ts.post = 1;

		if (_ln + _dl > _ts.length) {
			delayTip('最多上传' + _ts.length + '张文件!');
			_ts.post = 0;
			return;
		}

		if (_ts.size && _fs.size > _ts.size * 1024 * 1024) {
			delayTip('最大不超过' + _ts.size + 'M!');
			_ts.post = 0;
			return;
		}
		_fr.readAsDataURL(_fs);

		_fr.onload = function (res) {
			delayTip("上传中", 700)
			new ajax(_ts.url, {
				data: {
					memberId: member.id,
					fileName: _fs.name,
					pic: res.target.result
				},
				success: function (_e) {
					_ts.success(_e.returnData, res.target.result);
					_fl.setAttribute('data-length', ++_ln);
				},
				error: function () {
					delayTip('上传失败!');
				},
				complete: function () {
					_ts.post = 0;
					_di++
					if (_di < _dl) {
						_fs = _fl.files[_di]
						if (_ts.size && _fs.size > _ts.size * 1024 * 1024) {
							delayTip('最大不超过' + _ts.size + 'M!');
							_ts.post = 0;
							return;
						}
						_fr.readAsDataURL(_fs);
					} else if (_di == _dl) {
						delayTip('上传结束!');
					}
				}
			});
		}

		_fr.onerror = function () {
			_ts.post = 0;
			alertTip('文件上传中')
		}
	});
}


/************************** cache **************************/

function imgCache(_list, _cac) {
	if (!_list) {
		return _list;
	}

	var cache = {
		list: win_lc.getItem(_cac),
		path: [],
		name: []
	};

	if (cache.list) {
		cache.list = JSON.parse(cache.list);
		cache.path = cache.list.path,
			cache.name = cache.list.name;

		for (var j = 0, _name = _list.match(reg_cache_img)[0]; j < cache.list.name.length; j++) {
			(_name == cache.list.name[j]) && (_list = cache.list.path[j]);
		}
	}

	var _arr = _list.match(reg_cache_img);

	cache.path.push(_arr.input);
	cache.name.push(_arr[0]);

	var _name = [],
		_path = [],
		_same = {};

	for (var i = 0, d; (d = cache.name[i]) != null; i++) {
		if (!_same[d]) {
			_name.push(d);
			_path.push(cache.path[i]);
			_same[d] = true;
		}
	}
	cache.name = _name;
	cache.path = _path;

	win_lc.setItem(_cac, JSON.stringify({
		path: cache.path,
		name: cache.name
	}));

	return _list;
}

function imgCachePic(_list, _cac) {
	if (!_list || !_list.length) {
		return _list;
	}

	var cache = {
		list: win_lc.getItem(_cac),
		path: [],
		name: []
	};

	if (cache.list) {
		cache.list = JSON.parse(cache.list);
		cache.path = cache.list.path,
			cache.name = cache.list.name;

		for (var i = 0; i < _list.length; i++) {
			var _pic = _list[i].pic;

			if (_pic) {
				for (var j = 0, _name = _pic.match(reg_cache_img)[0]; j < cache.list.name.length; j++) {
					(_name == cache.list.name[j]) && (_list[i].pic = cache.list.path[j]);
				}
			}
		}
	}

	for (var i = 0; i < _list.length; i++) {
		var _pic = _list[i].pic;

		if (_pic) {
			var _arr = _pic.match(reg_cache_img);

			cache.path.push(_arr.input);
			cache.name.push(_arr[0]);
		}
	}

	var _name = [],
		_path = [],
		_same = {};

	for (var i = 0, d; (d = cache.name[i]) != null; i++) {
		if (!_same[d]) {
			_name.push(d);
			_path.push(cache.path[i]);
			_same[d] = true;
		}
	}
	cache.name = _name;
	cache.path = _path;

	win_lc.setItem(_cac, JSON.stringify({
		path: cache.path,
		name: cache.name
	}));

	return _list;
}

function imgCacheEntryPic(_list, _cac) {
	if (!_list || !_list.length) {
		return _list;
	}

	var cache = {
		list: win_lc.getItem(_cac),
		path: [],
		name: []
	};

	if (cache.list) {
		cache.list = JSON.parse(cache.list);
		cache.path = cache.list.path,
			cache.name = cache.list.name;

		for (var i = 0; i < _list.length; i++) {
			var _pic = _list[i].entryPic;

			if (_pic) {
				for (var j = 0, _name = _pic.match(reg_cache_img)[0]; j < cache.list.name.length; j++) {
					(_name == cache.list.name[j]) && (_list[i].entryPic = cache.list.path[j]);
				}
			}
		}
	}

	for (var i = 0; i < _list.length; i++) {
		var _pic = _list[i].entryPic;

		if (_pic) {
			var _arr = _pic.match(reg_cache_img);

			cache.path.push(_arr.input);
			cache.name.push(_arr[0]);
		}
	}

	var _name = [],
		_path = [],
		_same = {};

	for (var i = 0, d; (d = cache.name[i]) != null; i++) {
		if (!_same[d]) {
			_name.push(d);
			_path.push(cache.path[i]);
			_same[d] = true;
		}
	}
	cache.name = _name;
	cache.path = _path;

	win_lc.setItem(_cac, JSON.stringify({
		path: cache.path,
		name: cache.name
	}));

	return _list;
}

function imgCacheProductPic(_list, _cac) {
	if (!_list || !_list.length) {
		return _list;
	}

	var cache = {
		list: win_lc.getItem(_cac),
		path: [],
		name: []
	};

	if (cache.list) {
		cache.list = JSON.parse(cache.list);
		cache.path = cache.list.path,
			cache.name = cache.list.name;

		for (var i = 0; i < _list.length; i++) {
			var _pic = _list[i].productPic;

			if (_pic) {
				for (var j = 0, _name = _pic.match(reg_cache_img)[0]; j < cache.list.name.length; j++) {
					(_name == cache.list.name[j]) && (_list[i].productPic = cache.list.path[j]);
				}
			}
		}
	}

	for (var i = 0; i < _list.length; i++) {
		var _pic = _list[i].productPic;

		if (_pic) {
			var _arr = _pic.match(reg_cache_img);

			cache.path.push(_arr.input);
			cache.name.push(_arr[0]);
		}
	}

	var _name = [],
		_path = [],
		_same = {};

	for (var i = 0, d; (d = cache.name[i]) != null; i++) {
		if (!_same[d]) {
			_name.push(d);
			_path.push(cache.path[i]);
			_same[d] = true;
		}
	}
	cache.name = _name;
	cache.path = _path;

	win_lc.setItem(_cac, JSON.stringify({
		path: cache.path,
		name: cache.name
	}));

	return _list;
}

function imgCacheDecorateModulePic(_list, _cac) {
	var cache = {
		list: win_lc.getItem(_cac),
		path: [],
		name: []
	};

	if (!_list.length || !_list[0].decorateModulePic) {
		return _list;
	}

	if (cache.list) {
		cache.list = JSON.parse(cache.list);
		cache.path = cache.list.path,
			cache.name = cache.list.name;

		for (var i = 0; i < _list.length; i++) {
			var _name = _list[i].decorateModulePic;

			if (_name && _name.length) {
				_name = _name.match(reg_cache_img)[0];

				for (var j = 0; j < cache.list.name.length; j++) {
					(_name == cache.list.name[j]) && (_list[i].decorateModulePic = cache.list.path[j]);
				}
			}
		}
	}

	for (var i = 0; i < _list.length; i++) {
		var _arr = _list[i].decorateModulePic;

		if (_arr && _arr.length) {
			_arr = _arr.match(reg_cache_img);

			cache.path.push(_arr.input);
			cache.name.push(_arr[0]);
		}
	}

	var _name = [],
		_path = [],
		_same = {};

	for (var i = 0, d; (d = cache.name[i]) != null; i++) {
		if (!_same[d]) {
			_name.push(d);
			_path.push(cache.path[i]);
			_same[d] = true;
		}
	}
	cache.name = _name;
	cache.path = _path;

	win_lc.setItem(_cac, JSON.stringify({
		path: cache.path,
		name: cache.name
	}));

	return _list;
}

/************************** APP api **************************/

// ios跳转
function callIntentTrailer(handlerInterface, handlerMethod, parameters) {
	getH5Params();

	var dic = { 'handlerInterface': handlerInterface, 'function': handlerMethod, 'parameters': parameters };

	if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
		window.webkit.messageHandlers[handlerInterface].postMessage(dic);
	} else {
		window[handlerInterface][handlerMethod](JSON.stringify(dic));
	}
}

// ios跳转登录
function callLogin(handlerInterface, handlerMethod, errMsg) {
	getH5Params();

	var dic = { 'handlerInterface': handlerInterface, 'function': handlerMethod, 'errMsg': errMsg };

	if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
		window.webkit.messageHandlers[handlerInterface].postMessage(dic);
	} else {
		window[handlerInterface][handlerMethod](JSON.stringify(dic));
	}
}


getH5Params()

// 传给APP的统计数据
function getH5Params() {
	var _itm = traffic.list.length ? traffic.list[traffic.list.length - 1] : {};
	var _c = getColumn()
	if (member.sys == 'ios') {
		checkAppVer(58, function () {
			var dic = {
				'handlerInterface': 'intentTrailerListNative', 'function': 'getH5Params', 'parameters': {
					fromPageType: _itm.pageType || '',
					fromPvId: _itm.pvId ? _itm.pvId.replace(/^./, 'i') : '',
					fromColumnId: _c.id,
					fromDeviceNumber: getDeviceNumber(),
					fromColumnType: _c.type
				}
			};

			if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
				window.webkit.messageHandlers['intentTrailerListNative'].postMessage(dic);
			} else {
				window['intentTrailerListNative']['getH5Params'](JSON.stringify(dic));
			}
		}, true);
	} else if (member.sys == 'android') {

		var _e = member.appver;

		if (_e >= 64) {
			var dic = {
				fromPageType: _itm.pageType || '',
				fromPvId: _itm.pvId ? _itm.pvId.replace(/^./, 'a') : '',
				fromColumnId: _c.id,
				fromDeviceNumber: getDeviceNumber(),
				fromColumnType: _c.type
			}
			window.H5PluginManager.getH5Params(JSON.stringify(dic));
		} else {
			checkAppVer(57, function () {
				window.H5PluginManager.getH5Params(_itm.pageType, _itm.pvId ? _itm.pvId.replace(/^./, 'a') : '');
			}, true);
		}

	}
}


// 验证app版本是否符合接口
function checkAppVer(e, f, h) {
	getAppAgent();

	var _e = member.appver;

	if (member.sys == 'ios' && /\./g.test(e)) {
		if (member.iosver) {
			f();
			return;
		}

		e = parseInt(e.replace(/\./g, ''));
		_e = parseInt(_e.replace(/\./g, ''));
	}

	if (_e >= e) {
		f();
	} else if (typeof h == 'function') {
		h();
	} else if (!h) {
		delayTip('请下载最新版本！');
	}
}

// 购物车
function intentShoppingcard() {
	getH5Params();
	window.H5PluginManager.intentShoppingcard();
}

// Android登录接口
function login(str) {
	getH5Params();
	window.H5PluginManager.login(str);
}

// 商品详情
function intentProductDetail(a, b) {
	getH5Params();
	window.H5PluginManager.intentProductDetail(a, b);
}

// 活动列表
function intentTrailer(a, b) {
	getH5Params();
	window.H5PluginManager.intentTrailer(a, b);
}

// 全部订单
function intentMyorder() {
	getH5Params();
	window.H5PluginManager.intentMyorder();
}

// 新人专享
function intentNewUserShare() {
	getH5Params();
	window.H5PluginManager.intentNewUserShare();
}

// 单品爆款
function intentSingleExplo() {
	getH5Params();
	window.H5PluginManager.intentSingleExplo();
}

// 限时抢购
function intentSeckill() {
	getH5Params();
	window.H5PluginManager.intentSeckill();
}

// 新用户秒杀
function intentNewUserSeckill() {
	getH5Params();
	window.H5PluginManager.intentNewUserSeckill();
}

// 积分商城
function intentIntegralMall() {
	getH5Params();
	window.H5PluginManager.intentIntegralMall();
}

// 抵用积分
function intentIntegral() {
	getH5Params();
	window.H5PluginManager.intentIntegral();
}

// 断码清仓
function intentFaultCode2() {
	getH5Params();
	window.H5PluginManager.intentFaultCode2();
}

// 一级栏目
function intentCatalog(_name, _ad, _link) {
	window.H5PluginManager.intentCatalog(_name, _ad, _link);
}

// 商城1/2级
function intentShoppingMall(_one, _two, _name) {
	window.H5PluginManager.intentShoppingMall(_one, _two, _name);
}

// 新商城
function intentNewShopmallPage(_link) {
	getH5Params();
	window.H5PluginManager.intentNewShopmallPage(_link);
}

// 店铺
function intentShopping(_id) {
	getH5Params();
	window.H5PluginManager.intentShopping(_id);
}

// 拨打电话
function callPhone(e) {
	window.H5PluginManager.callPhone(e);
}

// 返回刷新
function anFleshCoupon() {
	window.H5PluginManager.anFleshCoupon();
}

// 首页刷新
function refreshHomeWebview() {
	window.H5PluginManager.refreshHomeWebview();
}

// 优惠券
function intentCoupon() {
	getH5Params();
	window.H5PluginManager.intentCoupon();
}

// 传签到日期 (毫秒级时间戳)
function getSignDate(_t) {
	window.H5PluginManager.getSignDate(_t);
}

// APP支付
function switchPayorder(_o) {
	window.H5PluginManager.switchPayorder(_o);
}

// 微淘关键字
function goToWeiTaoList(_n) {
	window.H5PluginManager.goToWeiTaoList(_n);
}

// 绑定手机(1)或微信(2)
function appBindDrawCash(_t) {
	window.H5PluginManager.appBindDrawCash(_t);
}

// 有好货
function haveGoods() {
	window.H5PluginManager.haveGoods();
}

// 每日好店
function everydayShop() {
	window.H5PluginManager.everydayShop();
}

// 品牌团
function brandGroup() {
	window.H5PluginManager.brandGroup();
}

// 新品牌团
function newBrandGroup(_n) {
	window.H5PluginManager.newBrandGroup(_n);
}

// 视频详情
function smallVideoDetail(_n) {
	window.H5PluginManager.smallVideoDetail(_n);
}

// 视频专区
function smallVideoCenter(_n) {
	window.H5PluginManager.smallVideoCenter();
}

//领券中心
function goToCouponCenter() {
	window.H5PluginManager.goToCouponCenter();
}


// 淘宝优选
function taobaoyouxuan() {
	window.H5PluginManager.taobaoyouxuan();
}

// 淘宝优选频道
function taobaoyouxuanpingdao(_n) {
	window.H5PluginManager.taobaoyouxuanpingdao(_n);
}

// 潮流一级
function chaoLiuList(_n) {
	window.H5PluginManager.chaoLiuList(_n);
}

// 潮流二级
function chaoLiuSecond(_n, _t) {
	window.H5PluginManager.chaoLiuSecond(_n, _t);
}

// 潮流栏目
function chaoLiuColumn(_n) {
	window.H5PluginManager.chaoLiuColumn(_n);
}

// 断码特惠
function chaoLiuDuanma(_n) {
	window.H5PluginManager.chaoLiuDuanma(_n);
}

// 显示分享ID
function getH5PageType_d(_n, _t, _a) {
	window.H5PluginManager.getH5PageType(_n, _t, _a);
}

// svip
function getSvip(_t) {
	stopSelf();

	if (member.sys == 'android') {
		checkAppVer(53, function () {
			getH5Params();
			window.H5PluginManager.intentSvip();
		});
	} else if (member.sys == 'ios') {
		checkAppVer(53, function () {
			callIntentTrailer('intentTrailerNative', 'intentSvip');
		});
	} else {
		closeSelf('.svip-tip');

		_t == 1 && delayTip('请下载醒购APP，进行开通SVIP哦！');
	}
}

// 醒购店长宣传介绍 // 客服
function callServer(e) {
	stopSelf(e);

	// if (member.sys == 'android') {
	// 	window.H5PluginManager.callServer(6, '', 2);
	// } else if (member.sys == 'ios') {
	// 	checkAppVer(60, function () {
	// 		callIntentTrailer('intentTrailerNative', 'callServer', {'type': 6, 'id': '', customerServiceSoftTypes: '2'});
	// 	});
	// } else {
	// 	getUrlServer('je_1000_1563431041105');
	// }
	getUrlServer('je_1000_1563431041105');
}

// 新星计划 - 任务进度
function renewalTaskProgress() {
	if (member.sys == 'android') {
		checkAppVer(55, function () {
			window.H5PluginManager.renewalTaskProgress();
		});
	} else if (member.sys == 'ios') {
		checkAppVer(57, function () {
			callIntentTrailer('intentTrailerNative', 'renewalTaskProgress');
		});
	} else {
		getUrl('activity/novaplan/pages/task/index.html', 'self');
	}
}

// 新星计划 - 开通成功
function openNovaPlanResult(_o, _f) {
	if (member.sys == 'android') {
		checkAppVer(55, function () {
			window.H5PluginManager.openNovaPlanResult(_o.isSuccess, _o.msg);
		});
	} else if (member.sys == 'ios') {
		checkAppVer(57, function () {
			callIntentTrailer('intentTrailerNative', 'openNovaPlanResult', { 'isSuccess': _o.isSuccess, 'msg': _o.msg });
		});
	} else if (_f) {
		_f();
	}
}

// 新星计划 - 首页
function novaPlanHome() {
	if (member.sys == 'android') {
		checkAppVer(55, function () {
			window.H5PluginManager.novaPlanHome();
		});
	} else if (member.sys == 'ios') {
		checkAppVer(57, function () {
			callIntentTrailer('intentTrailerNative', 'novaPlanHome');
		});
	} else {
		getUrl('activity/novaplan/index.html', 'self');
	}
}

// 绑定手机
function appBindPhone() {
	if (member.sys == 'android') {
		checkAppVer(55, function () {
			window.H5PluginManager.appBindPhone();
		});
	} else if (member.sys == 'ios') {
		checkAppVer(57, function () {
			callIntentTrailer('intentTrailerNative', 'appBindPhone');
		});
	} else {
		getUrl('register/mobilePwd/index.html?novaplan=1', 'self');
	}
}

// 清除缓存 (停用)
function clearCache() {
	if (member.sys == 'android') {
		checkAppVer(40, function () {
			window.H5PluginManager.clearCache();
		}, true);
	} else if (member.sys == 'ios') {
		checkAppVer(43, function () {
			callIntentTrailer('intentTrailerListNative', 'clearCache');
		}, true);
	} else {
		win_lc.clear();
	}
}

// 签到 - 设置消息通知
function checkInReminderSwitch() {
	if (member.sys == 'android') {
		checkAppVer(57, function () {
			window.H5PluginManager.checkInReminderSwitch();
		});
	} else if (member.sys == 'ios') {
		checkAppVer(58, function () {
			callIntentTrailer('intentTrailerListNative', 'checkInReminderSwitch');
		});
	}
}

// 签到 - 返回APP是否开启消息通知
var checkin_isremind = undefined;

function checkInReminderCallback(isRemind) {
	checkin_isremind = isRemind;
}

function getCheckInReminder(_f) {
	if (member.sys == 'android') {
		checkAppVer(57, function () {
			checkin_isremind = window.H5PluginManager.checkInReminderCallback();
			getCheckInReminderCallback(_f);
		}, function () {
			delayTip('请下载最新版本！');
			_f();
		});
	} else if (member.sys == 'ios') {
		checkAppVer(58, function () {
			callIntentTrailer('intentTrailerNative', 'checkInReminderCallback');
			getCheckInReminderCallback(_f);
		}, function () {
			delayTip('请下载最新版本！');
			_f();
		});
	}
}

function getCheckInReminderCallback(_f) {
	requestAnimationFrame(function () {
		checkin_isremind === undefined ? getCheckInReminderCallback(_f) : _f(checkin_isremind);
	});
}

/**************************  **************************/

// 分享
function intentShare(title, url, content, comment, pictureUrl, towx) {
	checkAppVer(37, function () {
		window.H5PluginManager.intentShare(title, url, content, comment, pictureUrl, towx);
	});
}

// 拨打电话
function confirmPhone(_tel, _txt) {
	if (!_tel) return;

	if (member.sys == 'android') {
		checkAppVer(39, function () {
			callPhone(_tel);
		});

		defSelf();
		return;
	}

	if (member.sys == 'ios') {
		checkAppVer('2.1.9', function () {
			callIntentTrailer('intentTrailerListNative', 'callPhone', { 'phone': _tel });
		});

		defSelf();
		return;
	}

	var _phone = $('.page:visible').find('.phone-tip'),
		_txt = _txt ? '<p class="flex ac jc txt">' + _txt + '</p>' : '';

	$('.popup').removeClass('show');

	if (_phone.length) {
		_phone.addClass('show');
	} else {
		$('.page:visible').append(
			'<div class="popup phone-tip">'
			+ '<div class="popup-center popup-tip">'
			+ '<div>'
			+ '<span class="flex ac jc">是否拨打</span>'
			+ '<h3 class="flex ac jc">' + _tel + '</h3>'
			+ _txt
			+ '</div>'
			+ '<div class="flex">'
			+ '<a class="flex fg ac jc bdr btn" onclick="closeSelf(this)">取消</a>'
			+ '<a class="flex fg ac jc btn" href="tel:' + _tel + '">拨打</a>'
			+ '</div>'
			+ '</div>'
			+ '</div>'
		);

		$('.page:visible').find('.phone-tip').addClass('show');
	}
}

// 购物车的快捷入口
function getQuickCart() {
	defSelf();

	if (member.sys == 'android') {
		intentShoppingcard();
	} else if (member.sys == 'ios') {
		callIntentTrailer('intentTrailerListNative', 'intentShoppingcard');
	} else {
		getUrl('cart', 'self');
	}
}

// 跳转 - 活动列表(single_hot)
function getUrlActiveSingle1(e, _o) {
	var _activityid = '',
		_name = '';

	_o = _o || {};

	if (typeof e === 'object') {
		_activityid = e.getAttribute('data-activityid');
		_name = e.getAttribute('data-name');
	} else {
		_activityid = e;
		_name = '';
	}

	_o.data && traffic.setDtl(_o.data, {
		type: 2,
		id: _activityid,
		pos: _o.pos
	});

	if (member.sys == 'android') {
		intentTrailer(_name, _activityid);
	} else if (member.sys == 'ios') {
		callIntentTrailer('intentTrailerNative', 'intentTrailer', { 'activityName': _name, 'activityId': _activityid });
	} else {
		getSingleHot(_activityid, _name);
	}
}

// 跳转 - 商品详情
function getUrlGoods(e, _o) {
	_o = _o || {};

	var _id = _o.code || e,
		_act = '';

	if (typeof e === 'object') {
		_id = e.getAttribute('data-productid');
		_act = e.getAttribute('data-activityareaid') || '';
	}
	if (!_id) return;

	if (member.sys == 'android') {
		intentProductDetail(_id, _act);
	} else if (member.sys == 'ios') {
		callIntentTrailer('intentTrailerListNative', 'intentProductDetail', { 'productId': _id, 'activityAreaId': _act });
	} else {
		getUrl('goods/detail?new=' + _id
			+ (_o.code ? '&code=' : '&id=') + _id
			+ (_act ? ('&activityAreaId=' + _act) : '')
			, 'self');
	}

	_o.data && traffic.setDtl(_o.data, {
		type: 3,
		id: _id,
		pos: _o.pos
	});
}

// 跳转模板
function getMapUrlType1(_url, _name, _native, _new) {
	if (!_url) return;

	if (member.sys == 'ios') {
		callIntentTrailer(_native, 'JumpWebView', { 'url': _url });
	} else {
		_url += /\?/g.test(_url) ? '&' : '?';
		getUrl(_url + 'new=' + _new, 'self', null, false, _name);
	}
}

/************************** scroll **************************/

var scrollHand = {
	post: 1,
	init: function (e, f, tp, f1) { // (scroll的jQ对象, 分页函数, 返回顶部, 滚动函数)
		var _ts = this,
			_horiz = false;

		_ts.post = 1;

		e.hasClass('scroll-x') && (_horiz = true);

		if (!e.attr('data-page')) {
			e.attr('data-page', 1);
			e.attr('data-old', 0);

			_horiz ? _ts.horiz(e, f) : _ts.vertic(e, f);
		}

		e.scroll(function () {
			if (_horiz) {
				var _l = this.scrollLeft;

				f1 && f1(_l);
				_ts.horiz(e, f);
			} else {
				var _t = this.scrollTop;

				tp && (_t > e.height() ? _ts.show(e) : _ts.hide(e));
				f1 && f1(_t);

				_ts.vertic(e, f);
			}
		});
	},

	// 垂直分页
	vertic: function (e, f) {
		if (!this.post) return;

		this.post = 0;

		var _s = e[0],
			_cur = Number(e.attr('data-page')),
			_old = Number(e.attr('data-old')),
			_st = _s.scrollTop,
			_sh = _s.offsetHeight,
			_ss = _s.scrollHeight,
			_eh = e.height() / 2;

		(_eh + _sh > _ss) && (_eh = (_ss - _sh) / 2);

		if (_old < _cur && _st + _sh + _eh >= _ss) {
			e.attr('data-old', _cur);
			f();
		} else {
			this.post = 1;
		}
	},

	// 水平分页
	horiz: function (e, f) {
		if (!this.post) return;

		this.post = 0;

		var _s = e[0],
			_cur = Number(e.attr('data-page')),
			_old = Number(e.attr('data-old')),
			_st = _s.scrollLeft,
			_sh = _s.offsetWidth,
			_ss = _s.scrollWidth,
			_eh = e.width() / 2;

		(_eh + _sh > _ss) && (_eh = (_ss - _sh) / 2);

		if (_old < _cur && _st + _sh + _eh >= _ss) {
			e.attr('data-old', _cur);
			f();
		} else {
			this.post = 1;
		}
	},

	back: function (e) { // (scroll的js对象)
		var _head = $('.decorate-header-con');

		if (_head.length) {
			_head.find('.nav-top').removeClass('active');
			_head.scrollLeft(0);
			_head.find('.nav-top:first-child').addClass('active');
		}
		$(e).siblings('.scroll-y').scrollTop(0);
	},
	show: function (e) { // 加入或显示"返回顶部" (scroll的jQ对象)
		var _e = e.parent().find('.ico-back-top');
		_e.length ? _e.removeClass('hide') : e.parent().append('<div class="ico-back-top" onclick="scrollHand.back(this)"></div>');
	},
	hide: function (e) { // 隐藏返回顶部 (scroll的jQ对象)
		e.parent().find('.ico-back-top').addClass('hide');
	}
};

/************************** decorate **************************/

function getMapUrl(_this, _native) {
	var _ts = $(_this),
		_type = _ts.attr('data-type'),
		_link = _ts.attr('data-link'),
		_productid = _ts.attr('data-productid'),
		_areaid = _ts.attr('data-activityareaid'),
		_ad = _ts.attr('data-ad'),
		_name = _ts.attr('data-name'),
		_linkUrl = _ts.attr('data-url'),
		_mid = _ts.attr('data-mid'),
		_pos = _ts.attr('data-pos'),
		_rec = _ts.attr('data-rectype'),
		_svip = _ts.attr('data-svip'),
		_new = _type + '_' + _link;
	!_native && (_native = 'intentTrailerListNative');

	if (_type == 34 && _link == 1) {
		if (member.sys == 'android' && member.appver < 63 || member.sys == 'ios' && member.appver < 62) {
			delayTip('对不起，操作此功能需要升级版本！');
		} else {
			if (member.id && member.sys == 'web') {
				memberSvip(member.id, _this)
			} else if (member.id && member.token && member.sys == 'android' || member.id && member.token && member.sys == 'ios') {
				new ajax(getApi.appLogin, {
					data: {
						memberId: member.id,
						token: member.token
					},
					success: function (e) {
						memberSvip(member.id, _this)
					}
				})
			} else {
				memberFn()
			}
		}

	}


	if (_type == 1) {
		// 模板 (会场id[data-activityareaid])
		if (_link !== undefined && _link !== '') {
			// $.ajax({
			// 	method: 'post',
			// 	url: getApi.areaTempletByAreaId + '?activityAreaId=' + _link,
			// 	success: function (e) {
			// 		getMapUrlType1(e.returnData.areaUrl, _name, _native, _new);
			// 	}
			// });
			new ajax(getApi.areaTempletByAreaId, {
				data: {
					activityAreaId: _link
				},
				success: function (e) {
					console.log(e.returnData)
					var _u = e.returnData.areaUrl;

					_u ? getMapUrlType1(_u, _name, _native, _new) : getUrl('activity/templet/brand_protection.html', 'self');
				}
			});
		}
	} else if (_type == 2) {
		// 特卖 (活动id[data-activityid], 活动名[data-name])
		getUrlActiveSingle1(_this, _areaid === 'undefined' ? undefined : { pos: _pos, data: brandDecorate.data });
	} else if (_type == 3) {
		// 商品详情 (商品id[data-productid], 会场id[data-activityareaid])
		getUrlGoods(_this, _areaid === 'undefined' ? undefined : { pos: _pos, data: brandDecorate.data });
	} else if (_type == 4) {
		// 自建页面 (&new=时间戳, 避免跳转后链接相同, 导致返回异常)
		var _url1 = 'activity/templet/brand_decorate.html?pageid=' + _link;

		if (member.sys == 'ios') {
			var _url1 = real_url.split(redirect_url)[0] + redirect_url + 'activity/templet/brand_decorate.html?pageid=' + _link;
			callIntentTrailer(_native, 'JumpWebView', { 'url': _url1 });
		} else {
			getUrl(_url1 + '&new=' + _new, 'self');
		}
	} else if (_type == 5) {
		if (_link == 1) {
			// 新人专享
			if (member.sys == 'android') {
				intentNewUserShare();
			} else if (member.sys == 'ios') {
				callIntentTrailer(_native, 'intentNewUserShare');
			} else {
				getUrl('newman/index.html?type=1', 'self');
			}
		} else if (_link == 2) {
			// 单品爆款
			if (member.sys == 'android') {
				intentSingleExplo();
			} else if (member.sys == 'ios') {
				callIntentTrailer(_native, 'intentSingleExplo');
			} else {
				getUrl('activity/templet/single_explosion.html?type=2', 'self');
			}
		} else if (_link == 3) {
			// 限时抢购
			if (member.sys == 'android') {
				intentSeckill();
			} else if (member.sys == 'ios') {
				callIntentTrailer(_native, 'intentSeckill');
			} else {
				getUrl('rushbuy/index.html?type=3', 'self');
			}
		} else if (_link == 4) {
			// 新人秒杀
			if (member.sys == 'android') {
				intentNewUserSeckill();
			} else if (member.sys == 'ios') {
				callIntentTrailer(_native, 'intentNewUserSeckill');
			} else {
				getUrl('newman/seckill/index.html');
			}
		} else if (_link == 5) {
			// 积分商城
			if (member.sys == 'android') {
				intentIntegralMall();
			} else if (member.sys == 'ios') {
				callIntentTrailer(_native, 'intentIntegralMall');
			} else {
				getUrl('integral/detail/index.html');
			}
		} else if (_link == 6) {
			// 断码清仓
			if (member.sys == 'android') {
				intentFaultCode2();
			} else if (member.sys == 'ios') {
				callIntentTrailer(_native, 'intentFaultCode2');
			} else {
				getUrl('activity/templet/single_duanma', 'self');
			}
		} else if (_link == 7) {
			// 签到
			var _url1 = real_url.split(redirect_url)[0] + redirect_url + 'activity/newsign/index.html';

			if (member.sys == 'ios') {
				callIntentTrailer(_native, 'JumpWebView', { 'url': _url1 });
			} else {
				getUrl(_url1);
			}
		} else if (_link == 8) {
			// 砍价
			var _url1 = real_url.split(redirect_url)[0] + redirect_url + 'activity/cutprice/index.html';

			if (member.sys == 'ios') {
				callIntentTrailer(_native, 'JumpWebView', { 'url': _url1 });
			} else {
				getUrl(_url1, 'self');
			}
		} else if (_link == 9) {
			// 免单
			var _url1 = real_url.split(redirect_url)[0] + redirect_url + 'activity/freeprice/index.html';

			if (member.sys == 'ios') {
				callIntentTrailer(_native, 'JumpWebView', { 'url': _url1 });
			} else {
				getUrl(_url1, 'self');
			}
		} else if (_link == 10) {
			// 有好货
			if (member.sys == 'android') {
				checkAppVer(53, function () {
					haveGoods();
				});
			} else if (member.sys == 'ios') {
				checkAppVer(56, function () {
					callIntentTrailer(_native, 'haveGoods');
				});
			}
		} else if (_link == 11) {
			// 每日好店
			if (member.sys == 'android') {
				checkAppVer(53, function () {
					everydayShop();
				});
			} else if (member.sys == 'ios') {
				checkAppVer(56, function () {
					callIntentTrailer(_native, 'everydayShop');
				});
			}
		} else if (_link == 12) {
			// 品牌团
			if (member.sys == 'android') {
				checkAppVer(53, function () {
					brandGroup();
				});
			} else if (member.sys == 'ios') {
				checkAppVer(56, function () {
					callIntentTrailer(_native, 'brandGroup');
				});
			}
		} else if (_link == 13) {
			// 新星计划
			novaPlanHome();
		} else if (_link == 14) {
			// 淘宝优选
			if (member.sys == 'android') {
				checkAppVer(55, function () {
					taobaoyouxuan();
				});
			} else if (member.sys == 'ios') {
				checkAppVer(57, function () {
					callIntentTrailer(_native, 'taobaoyouxuan');
				});
			}
		} else if (_link == 15 || _link == 16 || _link == 18 || _link == 19 || _link == 20) {
			// 潮流上新 / 潮流男装 / 运动鞋服 / 潮流美妆 / 食品超市
			if (member.sys == 'android') {
				checkAppVer(58, function () {
					chaoLiuColumn(_link);
				});
			} else if (member.sys == 'ios') {
				checkAppVer(59, function () {
					callIntentTrailer(_native, 'chaoLiuColumn', { 'type': _link });
				});
			}
		} else if (_link == 17) {
			// 断码特惠
			if (member.sys == 'android') {
				checkAppVer(58, function () {
					chaoLiuDuanma(_link);
				});
			} else if (member.sys == 'ios') {
				checkAppVer(59, function () {
					callIntentTrailer(_native, 'chaoLiuDuanma', { 'type': _link });
				});
			}
		} else if (_link == 22) {
			// svip
			getSvip();
		} else if (_link == 23) {
			// 领券中心
			if (member.sys == 'android') {
				checkAppVer(61, function () {
					goToCouponCenter();
				});
			} else if (member.sys == 'ios') {
				checkAppVer(61, function () {
					if (brand_decorate_api.homePage) {
						loadURL('http://goToCouponCenter');
					} else {
						callIntentTrailer(brand_decorate_api.iosNative, 'goToCouponCenter');
					}
				});
			}
		} else if (_link == 24) {
			// 视频专区
			if (member.sys == 'android') {
				checkAppVer(61, function () {
					smallVideoCenter();
				});
			} else if (member.sys == 'ios') {
				checkAppVer(61, function () {
					if (brand_decorate_api.homePage) {
						loadURL('http://smallVideoCenter');
					} else {
						callIntentTrailer(brand_decorate_api.iosNative, 'smallVideoCenter');
					}
				})
			}
		} else if (_link == 25) {
			// 主会场
			var _url1 = real_url.split(redirect_url)[0] + redirect_url + 'main_venue/index.html';
			if (member.sys == 'ios') {
				callIntentTrailer(_native, 'JumpWebView', { 'url': _url1 });
			} else {
				getUrl(_url1, 'self');
			}
		} else if (_link == 26) {
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
		} else {
			Untapped();
		}
	} else if (_type == 6) {
		// 一级栏目
		if (member.sys == 'android') {
			intentCatalog(_name, _ad, _link);
		} else if (member.sys == 'ios') {
			callIntentTrailer(_native, 'intentCatalog', {
				'id': _link,
				'ad': _ad,
				'name': _name,
				'productTypeId': _productid
			});
		} else {
			getUrl('activity/templet/brand_custom.html?productTypeId=' + _link + '&adCatalog=' + _ad + '&title=' + _name, 'self');
		}
	} else if (_type == 7) {
		// 商城一级页面
		if (member.sys == 'android') {
			intentShoppingMall(_link, '', _name);
		} else if (member.sys == 'ios') {
			callIntentTrailer(_native, 'intentShoppingMall', {
				'productTypeOne': _link,
				'productTypeTwo': '',
				'productTypeTitle': _name
			});
		} else {
			getUrl('mall/one.html?type=' + _link + '&new=' + _new, 'self');
		}
	} else if (_type == 8) {
		// 商城二级页面
		if (member.sys == 'android') {
			intentShoppingMall('', _link, _name);
		} else if (member.sys == 'ios') {
			callIntentTrailer(_native, 'intentShoppingMall', {
				'productTypeOne': '',
				'productTypeTwo': _link,
				'productTypeTitle': _name
			});
		} else {
			getUrl('mall/two.html?type=' + _link + '&new=' + _new, 'self');
		}
	} else if (_type == 9) {
		// 其他链接
		if (member.sys == 'ios') {
			callIntentTrailer(_native, 'JumpWebView', { 'url': _linkUrl });
		} else {
			var sp = _linkUrl.split("?redirect_url=")
			if (isWeiXin && sp[0].indexOf('webApp/xgbuy/views/index.html') > -1) {
				getUrl(sp[1])
			} else {
				window.location.href = _linkUrl;
			}
		}
	} else if (_type == 10) {
		// 分区锚点
		decorateJumpId(_mid);
	} else if (_type == 11) {
		if (_rec == 4 && (_svip == '0' || _svip == 'undefined')) {
			if (member.sys == 'android' || 'ios') {
				if (!member.id) {
					memberFn();
				} else {
					if (($('.page:visible').find('.svip-tip')).length > 0) {
						$('.page:visible').find('.svip-tip').addClass('show');
					} else {
						$('.page:visible').append(
							'<div class="popup svip-tip">'
							+ '<div class="popup-center svip-span">'
							+ '<div>'
							+ '<h3 class="flex ac jc ">' + '成为svip才能领取此优惠券哦' + '</h3>'
							+ '</div>'
							+ '<div class="flex">'
							+ '<div class="flex fg ac jc svip-btn" onclick="closeSelf(this)">'
							+ '<a class="flex fg ac jc"> ' + '我在想想' + '</a>'
							+ '</div>'
							+ '<div class="flex fg ac jc svip-btns" onclick="getSvip(1)">'
							+ '<a class="flex fg ac jc"> ' + '查看详情' + '</a>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
						);
						$('.page:visible').find('.svip-tip').addClass('show');
					}
				}
			} else {
				if (($('.page:visible').find('.svip-tip')).length > 0) {
					$('.page:visible').find('.svip-tip').addClass('show');
				} else {
					$('.page:visible').append(
						'<div class="popup svip-tip">'
						+ '<div class="popup-center svip-span">'
						+ '<div>'
						+ '<h3 class="flex ac jc ">' + '成为svip才能领取此优惠券哦' + '</h3>'
						+ '</div>'
						+ '<div class="flex">'
						+ '<div class="flex fg ac jc svip-btn" onclick="closeSelf(this)">'
						+ '<a class="flex fg ac jc"> ' + '我在想想' + '</a>'
						+ '</div>'
						+ '<div class="flex fg ac jc svip-btns" onclick="getSvip(1)">'
						+ '<a class="flex fg ac jc"> ' + '查看详情' + '</a>'
						+ '</div>'
						+ '</div>'
						+ '</div>'
						+ '</div>'
					);
					$('.page:visible').find('.svip-tip').addClass('show');
				}
			}
		} else {
			if (!member.id) {
				if (member.sys == 'web') {
					// 优惠券领取
					if (_this.getAttribute('data-msgtype') == 0) {
						_this.setAttribute('data-id', _link);
						decorateCoupons(_this);
					}
				} else {
					memberFn();
				}
			} else {
				if (_rec == 4 && (_svip == '0' || _svip == 'undefined')) {
					if (($('.page:visible').find('.svip-tip')).length > 0) {
						$('.page:visible').find('.svip-tip').addClass('show');
					} else {
						$('.page:visible').append(
							'<div class="popup svip-tip">'
							+ '<div class="popup-center svip-span">'
							+ '<div>'
							+ '<h3 class="flex ac jc ">' + '成为svip才能领取此优惠券哦' + '</h3>'
							+ '</div>'
							+ '<div class="flex">'
							+ '<div class="flex fg ac jc svip-btn" onclick="closeSelf(this)">'
							+ '<a class="flex fg ac jc"> ' + '我在想想' + '</a>'
							+ '</div>'
							+ '<div class="flex fg ac jc svip-btns" onclick="getSvip(1)">'
							+ '<a class="flex fg ac jc"> ' + '查看详情' + '</a>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
						);
						$('.page:visible').find('.svip-tip').addClass('show');
					}
				} else {
					// 首页领取优惠券
					if (member.sys == 'android') {
						if (member.appver >= 58) {
							checkAppVer(58, function () {
								window.H5PluginManager.getCounpons(_link);
							});
						} else {
							// 优惠券领取
							if (_this.getAttribute('data-msgtype') == 0) {
								_this.setAttribute('data-id', _link);
								decorateCoupons(_this);
							}
						}
					} else if (member.sys == 'ios') {
						if (member.appver >= 59) {
							checkAppVer(59, function () {
								callIntentTrailer(_native, 'getCounpons', { 'couponID': _link });
							});
						} else {
							// 优惠券领取
							if (_this.getAttribute('data-msgtype') == 0) {
								_this.setAttribute('data-id', _link);
								decorateCoupons(_this);
							}
						}
					} else {
						// 优惠券领取
						if (_this.getAttribute('data-msgtype') == 0) {
							_this.setAttribute('data-id', _link);
							decorateCoupons(_this);
						}
					}
				}
			}
		}
	} else if (_type == 12) {
		// APP商城
		if (member.sys == 'android') {
			intentNewShopmallPage(_link);
		} else if (member.sys == 'ios') {
			callIntentTrailer(_native, 'intentNewShopmallPage', { 'categoryId': _link });
		} else {
			getUrl('mall/index.html?shoptype=' + _link);
		}
	} else if (_type == 13) {
		// 店铺
		getUrlShop(_link);
	} else if (_type == 14) {
		// 微淘关键字
		if (member.sys == 'android') {
			checkAppVer(51, function () {
				goToWeiTaoList(_linkUrl);
			});
		} else if (member.sys == 'ios') {
			callIntentTrailer(_native, 'goToWeiTaoList', { 'weitaoKey': _linkUrl });
		}
	} else if (_type == 15) {
		// 新品牌团
		if (member.sys == 'android') {
			checkAppVer(55, function () {
				newBrandGroup(_link);
			});
		} else if (member.sys == 'ios') {
			checkAppVer(57, function () {
				callIntentTrailer(_native, 'newBrandGroup', { 'brandTeamTypeId': _link });
			});
		}
	} else if (_type == 16) {
		// 品牌团 - 二级分类
		getUrl('activity/templet/brand_group.html?type=16&productType2Ids=' + _linkUrl +'&new=' + _new, 'self',null,false,'品牌特卖');
		
	} else if (_type == 17) {
		// 淘宝优选频道
		if (member.sys == 'android') {
			checkAppVer(57, function () {
				taobaoyouxuanpingdao(_link);
			});
		} else if (member.sys == 'ios') {
			checkAppVer(58, function () {
				callIntentTrailer(_native, 'taobaoyouxuanpingdao', { 'id': _link });
			});
		}
	} else if (_type == 18) {
		// 有好货二级分类
	} else if (_type == 19 || _type == 21 || _type == 23 || _type == 25 || _type == 27 || _type == 29 || _type == 31) {
		// 有好货品牌 / 潮鞋上新品牌 / 潮流男装品牌 / 断码特惠品牌 / 运动鞋服品牌 / 潮流美妆品牌 / 食品超市品牌
		if (member.sys == 'android') {
			checkAppVer(58, function () {
				chaoLiuList(_linkUrl, _type);
			});
		} else if (member.sys == 'ios') {
			checkAppVer(59, function () {
				callIntentTrailer(_native, 'chaoLiuList', { 'id': _linkUrl, 'type': _type });
			});
		}
		// } else if (_type == 20 || _type == 22 || _type == 24 || _type == 26 || _type == 28 || _type == 30 || _type == 32) {
	} else if (_type == 20 || _type == 22 || _type == 24 || _type == 26 || _type == 28 || _type == 30) {
		// 潮鞋上新二级分类 / 潮流男装二级分类 / 断码特惠二级分类 / 运动鞋服二级分类 / 潮流美妆二级分类 / 食品超市二级分类 / 每日好店二级分类
		if (member.sys == 'android') {
			checkAppVer(58, function () {
				chaoLiuSecond(_linkUrl, _type);
			});
		} else if (member.sys == 'ios') {
			checkAppVer(59, function () {
				callIntentTrailer(_native, 'chaoLiuSecond', { 'id': _linkUrl, 'type': _type });
			});
		}
	} else if (_type == 33) {
		// 大学生创业二级分类
	} else if (_type == 32) {
		if (member.sys == 'android') {
			checkAppVer(61, function () {
				smallVideoDetail(_videoid);
			});
		} else if (member.sys == 'ios') {
			checkAppVer(61, function () {
				if (brand_decorate_api.homePage) {
					loadURL('http://smallVideoDetail?videoId=' + _videoid);
				} else {
					smallVideoDetail(_native, 'smallVideoDetail ', { 'videoId': _videoid });
				}
			})
		}
	}
}

//svip h5接入app
function memberSvip(memberId, that) {
	new ajax(getApi.checkRec, {
		data: {
			memberId: memberId,
		},
		success: function (e) {
			//false 則是沒有領取
			if (e.returnData.received) {
				delayTip('您已经领取SVIP体验卡，不能重复领取哦~');
				$(that).addClass('shadeSvip')
				$(that).removeAttr("onclick", "");
			} else {
				new ajax(getApi.svipPracticeRec, {
					data: {
						memberId: memberId,
					},
					success: function (e) {
						delayTip('领取成功');
						$(that).addClass('shadeSvip')
						$(that).removeAttr("onclick", "");
					}
				})
			}
		},
	});
}

/**************************  **************************/

// 卡顿
function scrollFix(e) {
	e = e || $('.page:visible .scroll-y');
	e.removeClass('scroll-fix');
}

// 复制
var clipHnad = function (elem) {
	require(['clipboard'], function (Clipboard) {
		var _a = new Clipboard(elem);

		_a.on('success', function (e) {
			// delayTip('【' + e.text + '】已复制到剪贴板');
			delayTip('复制成功');
			e.clearSelection();
		});

		_a.on('error', function (e) {
			delayTip('该设备不支持快捷复制, 请通过长按来复制');
		});
	});
};

// 水印图修复
function wkPicFix() {
	var _a = $('.page:visible .wk-pic');

	if (!_a.length) return;

	var _b = _a.closest('.page')[0].className.replace(' flex', '').replace(' dc', '').split(' ')[1];

	_a.eq(0).find('img')[0].onload = function () {
		var _w = font_size / 100 * this.naturalWidth;

		addStylesheetRules([
			['.' + _b + ' .wk-pic',
			['width', _w + 'px'],
			['opacity', '1']
			]
		]);
	}
}

// 全局模板
function getTpl(_f) {
	template ? _f() : require(['template'], function (_t) {
		template = _t;
		_f();
	});
}

// 抖音布码
function getDouyin(e) {
	global_douyin && meteor.track('shopping', { convert_id: e });
}

function imgErrorHead(_this) {
	_this.src = lead_url + 'static/images/bg-head.png';
}

function imgErrorEmpty(_this) {
	_this.style.display = 'none';
}

// 下拉刷新 dropDown($('.page:visible'), callBack);
function dropDown(_o, _f) {
	var drop_y1 = -1,
		drop_y2 = 0,
		drop_s = _o.find('.scroll-more'),
		drop_c = _o.find('.touchfix'),
		drop_b = _o.find('.bg-drop');

	if (!drop_b.length) {
		_o.append('<div class="bg-drop"></div>');
		drop_b = _o.find('.bg-drop');
	}

	_o.on('touchstart', function (e) {
		if (_f) {
			drop_s = _o.find('.scroll-more');
			drop_c = _o.find('.touchfix');
		}

		drop_s.scrollTop() == 0 && (drop_y1 = e.changedTouches[0].clientY);
	}).on('touchmove', function (e) {
		if (drop_y1 >= 0) {
			drop_y2 = e.changedTouches[0].clientY - drop_y1;

			if (drop_y2 > 0) {
				defSelf(e);
				drop_c.css('transform', 'translate3d(0, ' + drop_y2 + 'px, 0)');
				drop_b.height(drop_y2);
			}
		}
	}).on('touchend', function (e) {
		if (drop_y2 > 0) {
			drop_c.css('transform', 'translate3d(0, 0, 0)');
			drop_b.height(0);
			_f && _f();
		}

		drop_y1 = -1;
		drop_y2 = 0;
	});
}

// 判断是否当前设备 PC端
function isPC() {
	var userAgentInfo = navigator.userAgent;
	var Agents = ["Android", "iPhone",
		"SymbianOS", "Windows Phone",
		"iPad", "iPod"];
	var flag = true;
	for (var v = 0; v < Agents.length; v++) {
		if (userAgentInfo.indexOf(Agents[v]) > 0) {
			flag = false;
			break;
		}
	}
	return flag;
}
