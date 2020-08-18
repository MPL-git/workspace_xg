package com.jf.common.constant;

public class Const {
	//线上	
	public static final Integer PRODUCT_TYPE_2 = 2; //运动户外
	public static final Integer PRODUCT_TYPE_3 = 3; //鞋靴箱包
	public static final Integer PRODUCT_TYPE_4 = 4; //服装配饰
	//测试
//	public static final Integer PRODUCT_TYPE_2 = 316; //运动户外
//	public static final Integer PRODUCT_TYPE_3 = 317; //鞋靴箱包
//	public static final Integer PRODUCT_TYPE_4 = 318; //服装配饰
	
	public static final Integer RETURN_SIZE_10 = 10;
	public static final Integer RETURN_SIZE_20 = 20;	
	public static final Integer RETURN_SIZE_30 = 30;

	//商家类型
	public static final String MCHT_TYPE_SPOP = "1";
	public static final String MCHT_TYPE_POP = "2";

	//bu_appeal_order投诉单
	//投诉人类型 1.会员 2.平台客服
	public static final String APPEAL_ORDER_TYPE_MEMBER = "1";	//会员
	public static final String APPEAL_ORDER_TYPE_PLATFORM = "2";	//平台客服
	
	//投诉单据状态 1待客户回复 2待商家回复 3投诉单超时关闭 4平台关闭 5用户关闭
	public static final String APPEAL_ORDER_STATUS_1 = "1";	//待客户回复
	public static final String APPEAL_ORDER_STATUS_2 = "2";	//待商家回复
	public static final String APPEAL_ORDER_STATUS_3 = "3";	//投诉单超时关闭 
	public static final String APPEAL_ORDER_STATUS_4 = "4";	//平台关闭
	public static final String APPEAL_ORDER_STATUS_5 = "5";	//用户关闭
	
	// 站内信表（bu_platform_msg）
	//msg_type消息类型
	public static final String PLATFORM_MSG_TYPE_A1 = "A1";	//A1退款
	public static final String PLATFORM_MSG_TYPE_A2 = "A2";	//A2退货
	public static final String PLATFORM_MSG_TYPE_A3 = "A3";	//A3换货
	public static final String PLATFORM_MSG_TYPE_A4 = "A4";	//A4投诉
	public static final String PLATFORM_MSG_TYPE_A5 = "A5";	//A5违规
	public static final String PLATFORM_MSG_TYPE_A6 = "A6";	//A6活动
	public static final String PLATFORM_MSG_TYPE_TZ = "TZ";	//通知
	public static final String PLATFORM_MSG_TYPE_CQ = "CQ";	//产权投诉

	//title标题
	public static final String PLATFORM_MSG_TITLE_A1 = "关于用户申请退款通知";	
	public static final String PLATFORM_MSG_TITLE_A20 = "关于用户申请售后退货通知";	
	public static final String PLATFORM_MSG_TITLE_A21 = "关于售后退货(用户已寄件)通知";	
	public static final String PLATFORM_MSG_TITLE_A30 = "关于用户申请售后换货通知";	
	public static final String PLATFORM_MSG_TITLE_A31 = "关于售后换货(用户已寄件)通知";
	public static final String PLATFORM_MSG_TITLE_A4 = "关于客户发起投诉通知";
	public static final String PLATFORM_MSG_TITLE_A5 = "您有一条新的违规信息";
	public static final String PLATFORM_MSG_TITLE_A60 = "您有一条新的活动可报名";
	public static final String PLATFORM_MSG_TITLE_A61 = "关于品牌特卖审核通过通知";
	public static final String PLATFORM_MSG_TITLE_A62 = "关于会场活动审核通过通知";
	public static final String PLATFORM_MSG_TITLE_A63 = "关于品牌特卖审核通过通知";
	public static final String PLATFORM_MSG_TITLE_A64 = "关于会场活动审核驳回通知";
	//title标题
	public static final String MSG_TLP_TYPE_A1 = "A1";	
	public static final String MSG_TLP_TYPE_A20 = "A20";	
	public static final String MSG_TLP_TYPE_A21 = "A21";	
	public static final String MSG_TLP_TYPE_A30 = "A30";	
	public static final String MSG_TLP_TYPE_A31 = "A31";
	public static final String MSG_TLP_TYPE_A4 = "A4";
	public static final String MSG_TLP_TYPE_A5 = "A5";
	public static final String MSG_TLP_TYPE_A60 = "A60";
	public static final String MSG_TLP_TYPE_A61 = "A61";
	public static final String MSG_TLP_TYPE_A62 = "A62";
	public static final String MSG_TLP_TYPE_A63 = "A63";
	public static final String MSG_TLP_TYPE_A64 = "A64";
	
	
	//0：未读 1：已读
	public static final String PLATFORM_MSG_STATUS_0 = "0";	//0未读
	public static final String PLATFORM_MSG_STATUS_1 = "1";	//1已读
	// 系统类型值
	/**IOS */
	public static final String IOS = "1";	//IOS
	/**Android */
	public static final String ANDROID = "2";	//Android
	/**webApp */
	public static final String WEB_APP = "3";	//webApp
	/**Micro mall 微商城 */
	public static final String MICRO_MALL = "4";	//webApp
	/**微信小程序 */
	public static final String WX_XCX = "5";	//微信小程序
	
	// 积分类型
	public static final String INTEGRAL_TALLY_MODE_INCOME = "1";	//积分进账
	public static final String INTEGRAL_TALLY_MODE_ACCOUNT = "2";	//积分出账
	
	// member_info 用户类型
	public static final String MEMBER_INFO_TYPE_MEMBER = "1";	//正式会员
	public static final String MEMBER_INFO_TYPE_THIRD = "2";	//第三方
	public static final String MEMBER_INFO_TYPE_TOURIST = "3";	//游客
	public static final String MEMBER_INFO_TYPE_NEW_TOURIST = "4";	//新游客
	
	// member_info 用户状态 激活状态A 已激活 N 待激活 P黑名单C 注销
	public static final String MEMBER_INFO_STATUS_ACTIVATION_A = "A";	//已激活
	public static final String MEMBER_INFO_STATUS_UNACTIVATION_N = "N";	//待激活
	public static final String MEMBER_INFO_STATUS_BLACK_P = "P";	//黑名单
	public static final String MEMBER_INFO_STATUS_LOGOUT_C = "C";	//注销
	
	// 积分使用类型
	public static final String INTEGRAL_TYPE_SHOPPONG = "1";	//购物赠送
	public static final String INTEGRAL_TYPE_MOBILE_AUTHENTICATION = "2";//手机认证
	public static final String INTEGRAL_TYPE_DATA_PERFECT= "3";	//完善资料
	public static final String INTEGRAL_TYPE_ORDER_PAYMENT = "4";	//购物抵扣
	public static final String INTEGRAL_TYPE_EXCHANGE = "5";	//积分兑换
	public static final String INTEGRAL_TYPE_SYSTEM_GIFT = "6";	//系统赠送
	public static final String INTEGRAL_TYPE_DEDUCT_RETURN = "7";	//抵扣还还
	public static final String INTEGRAL_TYPE_IRREGULARITIES_COMPENSATION = "8";	//违规补偿
	public static final String INTEGRAL_TYPE_SYSTEM_SPORT_DE = "9";	//竞猜抵扣
	public static final String INTEGRAL_TYPE_DEDUCT_SPORT_REWARD = "10";	//竞猜奖励
	public static final String INTEGRAL_TYPE_EVERY_DAY_LEPING = "11";	//天天乐评
	public static final String INTEGRAL_TYPE_EVERY_EXPIRED = "12";	//积分过期
	public static final String INTEGRAL_TYPE_SIGN_IN_GIFT = "13";	//签到赠送
	public static final String INTEGRAL_TYPE_TIRED_SIGN_GIFT = "14";	//累签赠送
	public static final String INTEGRAL_TYPE_SVIP_MONTH_INTEGRAL = "16";	//SVIP月领取积分
	public static final String INTEGRAL_TYPE_INVITATION_CODE = "17";	//邀请码
	public static final String INTEGRAL_TYPE_SEE_NOVA_STRATEGY = "18";	//查看新星攻略
	public static final int INTEGRAL_TYPE_COUPON_RAIN = 20;	//红包雨

	// 系统类型值
	public static final String PAY_ZFB = "1";	//支付宝
	public static final String PAY_WX = "2";	//微信
	
	// 标识状态
	public static final String FLAG_FALSE = "0";	//未标识
	public static final String FLAG_TRUE = "1";	//已标识

	// 操作员类型
	public static final String OPERATOR_TYPE_MEMBER = "1";	//客户
	public static final String OPERATOR_TYPE_MCHT = "2";	//商家
	public static final String OPERATOR_TYPE_SYSTEM = "3";	//系统
	
	// 优惠券费用归属方
	public static final String COUPON_BELONG_TYPE_PLATFORM= "1";	//平台
	public static final String COUPON_BELONG_TYPE_MCHT = "2";	//商家

	// 总订单
	// 状态
	public static final String COMBINE_ORDER_STATUS_NOT_PAID = "0";	//未付
	public static final String COMBINE_ORDER_STATUS_PAID = "1";		//已付
	public static final String COMBINE_ORDER_STATUS_CANCEL = "4";	//取消
	// 付款状态
	public static final String COMBINE_ORDER_PAY_STATUS_SUCCESS = "1";	//付款成功
	public static final String COMBINE_ORDER_PAY_STATUS_FAILED = "2";	//付款失败
	// 财务确认状态
	public static final String COMBINE_ORDER_FINANCIAL_STATUS_NULL = "0";	//未处理
	public static final String COMBINE_ORDER_FINANCIAL_STATUS_WAIT = "1";	//已登记
	public static final String COMBINE_ORDER_FINANCIAL_STATUS_CONFIRMED = "2";	//已确认(付款成功，默认为已登记)
	// 付款状态
	public static final String COMBINE_ORDER_TYPE_ROUTINE = "1";	//常规订单
	public static final String COMBINE_ORDER_TYPE_SECKILL = "2";	//秒杀订单


	// 子订单
	// 状态
	public static final String ORDER_STATUS_NOT_PAID = "0";	//未付款
	public static final String ORDER_STATUS_PAID = "1";		//待发货
	public static final String ORDER_STATUS_SHIPPED = "2";	//待收货
	public static final String ORDER_STATUS_SUCCESS = "3";	//完成
	public static final String ORDER_STATUS_CANCEL = "4";	//取消
	public static final String ORDER_STATUS_CLOSE = "5";	//关闭
	public static final String ORDER_STATUS_RECEIVED_GOODS = "6";	//已收货


	// 订单明细
	// 状态
	public static final String ORDER_PRODUCT_STATUS_SUCCESS = "1";	//完成
	public static final String ORDER_PRODUCT_STATUS_REFUND_MONEY = "2";	//退款
	public static final String ORDER_PRODUCT_STATUS_REFUND_GOODS = "3";	//退货


	// 售后单
	// 售后类型
	public static final String CUSTOMER_ORDER_TYPE_A = "A";	//退款单
	public static final String CUSTOMER_ORDER_TYPE_B = "B";	//退货单
	public static final String CUSTOMER_ORDER_TYPE_C = "C";	//换货单
	public static final String CUSTOMER_ORDER_TYPE_D = "D";	//直赔单
	// 状态
	public static final String CUSTOMER_ORDER_STATUS_REFUNDING = "0";	//进行中
	public static final String CUSTOMER_ORDER_STATUS_SUCCESS = "1";	//已完成
	public static final String CUSTOMER_ORDER_STATUS_REJECT = "2";	//已拒绝
	public static final String CUSTOMER_ORDER_STATUS_CANCEL = "3";	//已撤销
	// 进度状态
	public static final String CUSTOMER_ORDER_PRO_STATUS_A1 = "A1";	//A1客户已申请（退款）
	public static final String CUSTOMER_ORDER_PRO_STATUS_A2 = "A2";	//A2商家同意申请（退款）
	public static final String CUSTOMER_ORDER_PRO_STATUS_A3 = "A3";	//A3商家不同意申请（退款）
	public static final String CUSTOMER_ORDER_PRO_STATUS_A4 = "A4";	//A4平台已退款（退款）

	public static final String CUSTOMER_ORDER_PRO_STATUS_B1 = "B1";	//B1客户已申请（退货）
	public static final String CUSTOMER_ORDER_PRO_STATUS_B2 = "B2";	//B2商家同意申请（退款）
	public static final String CUSTOMER_ORDER_PRO_STATUS_B3 = "B3";	//B3商家不同意申请（退货）
	public static final String CUSTOMER_ORDER_PRO_STATUS_B4 = "B4";	//B4客户已寄件（退货）
	public static final String CUSTOMER_ORDER_PRO_STATUS_B5 = "B5";	//B5商家已同意退款（退货）
	public static final String CUSTOMER_ORDER_PRO_STATUS_B6 = "B6";	//B6商家不同意退款（退货）
	public static final String CUSTOMER_ORDER_PRO_STATUS_B7 = "B7";	//B7平台已退款（退货）

	public static final String CUSTOMER_ORDER_PRO_STATUS_C1 = "C1";	//C1客户已申请（换货）
	public static final String CUSTOMER_ORDER_PRO_STATUS_C2 = "C2";	//C2商家同意申请（退款）
	public static final String CUSTOMER_ORDER_PRO_STATUS_C3 = "C3";	//C3商家不同意申请（换货）
	public static final String CUSTOMER_ORDER_PRO_STATUS_C4 = "C4";	//C4客户已寄件（换货）
	public static final String CUSTOMER_ORDER_PRO_STATUS_C5 = "C5";	//C5商家同意换货（换货）
	public static final String CUSTOMER_ORDER_PRO_STATUS_C6 = "C6";	//C6商家不同意换货（换货）
	public static final String CUSTOMER_ORDER_PRO_STATUS_C7 = "C7";	//C7商家已寄件（换货）

	public static final String CUSTOMER_ORDER_PRO_STATUS_D1 = "D1";	//D1商家创建直赔（直赔）
	public static final String CUSTOMER_ORDER_PRO_STATUS_D2 = "D2";	//D2平台已退款（直赔）



	// 保证金
	// 业务类型
	public static final String DEPOSIT_BIZ_TYPE_VOILATION_PAY = "1";	//交罚单
	public static final String DEPOSIT_BIZ_TYPE_VIOLATION = "2";	//违规单
	//变更类型
	public static final String DEPOSIT_TXN_TYPE_A = "A";	//A应缴变化
	public static final String DEPOSIT_TXN_TYPE_B = "B";	//B现金缴纳
	public static final String DEPOSIT_TXN_TYPE_C = "C";	//C货款抵缴
	public static final String DEPOSIT_TXN_TYPE_D = "D";	//D退保证金
	public static final String DEPOSIT_TXN_TYPE_E = "E";	//E违规扣款
	public static final String DEPOSIT_TXN_TYPE_F = "F";	//F申诉成功
	//变更类型子类
	public static final String DEPOSIT_TYPE_SUB_A1 = "A1";	//开店保证金
	public static final String DEPOSIT_TYPE_SUB_A2 = "A2";	//秒杀保证金
	public static final String DEPOSIT_TYPE_SUB_A3 = "A3";	//特卖保证金
	public static final String DEPOSIT_TYPE_SUB_B1 = "B1";	//支付宝转账
	public static final String DEPOSIT_TYPE_SUB_B2 = "B2";	//银行转账
	public static final String DEPOSIT_TYPE_SUB_B3 = "B3";	//微信转账
	public static final String DEPOSIT_TYPE_SUB_C1 = "C1";	//货款划到保证金
	public static final String DEPOSIT_TYPE_SUB_C2 = "C2";	//保证金划到货款
	public static final String DEPOSIT_TYPE_SUB_D1 = "D1";	//支付宝转账
	public static final String DEPOSIT_TYPE_SUB_D2 = "D2";	//银行转账
	public static final String DEPOSIT_TYPE_SUB_E1 = "E1";	//【入驻】提供虚假信息
	public static final String DEPOSIT_TYPE_SUB_E2 = "E2";	//【入驻】提供虚假资质
	public static final String DEPOSIT_TYPE_SUB_E3 = "E3";	//【入驻】不配合平台管理
	public static final String DEPOSIT_TYPE_SUB_E4 = "E4";	//【发布信息】滥发信息
	public static final String DEPOSIT_TYPE_SUB_E5 = "E5";	//【发布信息】发布第三方信息
	public static final String DEPOSIT_TYPE_SUB_E6 = "E6";	//【发布信息】描述不符
	public static final String DEPOSIT_TYPE_SUB_E7 = "E7";	//【销售】商品品质不合格
	public static final String DEPOSIT_TYPE_SUB_E8 = "E8";	//【销售】出售假冒伪劣商品
	public static final String DEPOSIT_TYPE_SUB_E9 = "E9";	//【销售】出售未经报关进口商品
	public static final String DEPOSIT_TYPE_SUB_E10 = "E10";	//【售后】虚假发货
	public static final String DEPOSIT_TYPE_SUB_E11 = "E11";	//【售后】未按规定时间发货
	public static final String DEPOSIT_TYPE_SUB_E12 = "E12";	//【售后】缺货
	public static final String DEPOSIT_TYPE_SUB_E13 = "E13";	//【售后】违背承诺
	public static final String DEPOSIT_TYPE_SUB_E14 = "E14";	//【售后】恶意骚扰客户
	public static final String DEPOSIT_TYPE_SUB_E15 = "E15";	//【售后】泄露客户信息
	public static final String DEPOSIT_TYPE_SUB_E16 = "E16";	//【售后】客诉处理超时
	public static final String DEPOSIT_TYPE_SUB_F1 = "F1";	//【入驻】提供虚假信息
	public static final String DEPOSIT_TYPE_SUB_F2 = "F2";	//【入驻】提供虚假资质
	public static final String DEPOSIT_TYPE_SUB_F3 = "F3";	//【入驻】不配合平台管理
	public static final String DEPOSIT_TYPE_SUB_F4 = "F4";	//【发布信息】滥发信息
	public static final String DEPOSIT_TYPE_SUB_F5 = "F5";	//【发布信息】发布第三方信息
	public static final String DEPOSIT_TYPE_SUB_F6 = "F6";	//【发布信息】描述不符
	public static final String DEPOSIT_TYPE_SUB_F7 = "F7";	//【销售】商品品质不合格
	public static final String DEPOSIT_TYPE_SUB_F8 = "F8";	//【销售】出售假冒伪劣商品
	public static final String DEPOSIT_TYPE_SUB_F9 = "F9";	//【销售】出售未经报关进口商品
	public static final String DEPOSIT_TYPE_SUB_F10 = "F10";	//【售后】虚假发货
	public static final String DEPOSIT_TYPE_SUB_F11 = "F11";	//【售后】未按规定时间发货
	public static final String DEPOSIT_TYPE_SUB_F12 = "F12";	//【售后】缺货
	public static final String DEPOSIT_TYPE_SUB_F13 = "F13";	//【售后】违背承诺
	public static final String DEPOSIT_TYPE_SUB_F14 = "F14";	//【售后】恶意骚扰客户
	public static final String DEPOSIT_TYPE_SUB_F15 = "F15";	//【售后】泄露客户信息
	public static final String DEPOSIT_TYPE_SUB_F16 = "F16";	//【售后】客诉处理超时


	// 违规单
	// 违规类型
	public static final String VIOLATE_ORDER_TYPE_A1 = "A1";	//延迟发货
	public static final String VIOLATE_ORDER_TYPE_A2 = "A2";	//出售假冒商品
	public static final String VIOLATE_ORDER_TYPE_A3 = "A3";	//客诉处理超时
	public static final String VIOLATE_ORDER_TYPE_A4 = "A4";	//滥发信息
	public static final String VIOLATE_ORDER_TYPE_A5 = "A5";	//提供虚假材料
	// 违规单来源
	public static final String VIOLATE_ORDER_SOURCE_SYSTEM = "1";	//系统
	public static final String VIOLATE_ORDER_SOURCE_MANUAL = "2";	//人工
	// 状态
	public static final String VIOLATE_ORDER_STATUS_NOT_APPEAL = "1";	//未申诉
	public static final String VIOLATE_ORDER_STATUS_CANNOT_APPEAL = "2";	//不可申诉
	public static final String VIOLATE_ORDER_STATUS_APPEALING = "3";	//申诉中
	public static final String VIOLATE_ORDER_STATUS_OVERDUE = "4";	//超期未申诉
	public static final String VIOLATE_ORDER_STATUS_FAILED = "5";	//申诉失败
	public static final String VIOLATE_ORDER_STATUS_SUCCESS = "6";	//申诉成功
	// 审核状态
	public static final String VIOLATE_ORDER_AUDIT_STATUS_WAIT = "0";	//未审核
	public static final String VIOLATE_ORDER_AUDIT_STATUS_PASS = "1";	//通过
	public static final String VIOLATE_ORDER_AUDIT_STATUS_REJECT = "2";	//驳回
	
	// bu_ad_info
	//广告链接类型(1 会场 2 活动 3 商品 4 外部链接)
	public static final String AD_ACTIVITY_AREA = "1";	//会场
	public static final String AD_ACTIVITY = "2";	//活动
	public static final String AD_PRODUCT = "3";	//商品
	public static final String AD_LINKURL = "4";	// 外部链接
	public static final String AD_UN_LINKURL = "5";	//无连接
	public static final String AD_CHANNEL = "6";	// 频道
	public static final String AD_CUSTOMER_PAGE = "7";	//自建页面
	
	
	//状态(0 下架 1 上架)
	public static final String AD_STATUS_DOWN = "0";
	public static final String AD_STATUS_UP = "1";
	
	//广告类型(1 广告图 2 推荐活动)
	public static final String AD_TYPE_1 = "1";	//广告图
	public static final String AD_TYPE_2 = "2";	//推荐活动
	
	//广告类目(1 首页 2 运动 3 鞋包 4 服饰 5 生活 6 新用户专享 7 限时秒杀)
	public static final String AD_CATALOG_HOME = "1";	//首页
	public static final String AD_CATALOG_SPORRT_ = "2";	// 运动
	public static final String AD_CATALOG_SHOES = "3";	// 鞋包
	public static final String AD_CATALOG_CLOTHES = "4";	// 服饰
	public static final String AD_CATALOG_LIFE = "5";	// 生活
	public static final String AD_CATALOG_NEW_ENJOY = "6";	// 新用户专享
	public static final String AD_CATALOG_SECKILL = "7";	// 限时秒杀
	public static final String AD_CATALOG_NEW_ENJOY_BG = "8";	// 新用户秒杀入口背景图
	public static final String AD_CATALOG_NEW_ENJOY_SECKILL = "9";	// 新用户秒杀频道
	public static final String AD_CATALOG_INTEGRAL_MALL = "10";	// 积分商城
	public static final String AD_CATALOG_SVIP = "16";	// svip
	public static final String AD_CATALOG_GOOD_EVERYDAY = "17";	// 每日好货
	public static final String AD_CATALOG_GOOD_SHOP = "18";	// 每日好店
	public static final String AD_CATALOG_START_AD = "19";	// 启动广告
	public static final String AD_CATALOG_GOOD_RECOMMEND = "20";	// 好货推荐
	public static final String AD_CATALOG_NEW_TRENDS = "21";	// 潮鞋上新
	public static final String AD_CATALOG_TRENDS_MEN_WEAR = "22";	// 潮流男装
	public static final String AD_CATALOG_CODE_BREAKING_PREFERENCE = "23";	// 断码特惠
	public static final String AD_CATALOG_SPORT_SHOES = "24";	// 运动鞋服
	public static final String AD_CATALOG_TRENDS_BEAUTY_MAKEUP = "25";	// 潮流美妆
	public static final String AD_CATALOG_FOOD_SUPERMARKET = "26";	// 食品超市
	public static final String AD_CATALOG_COLLEGE_STUDENT = "27";	// 大学生创业
	public static final String AD_CATALOG_COUPON_CENTER = "28";	// 领券中心
	public static final String AD_CATALOG_HOT_RECOMMEND = "29";	// 爆款推荐
	public static final String AD_CATALOG_PERSONAL = "30";	// 个人中心
	public static final String AD_CATALOG_STOCK_CLEARANCE = "31";	// 断码清仓（单品活动）

	//广告位置(1 头部海报 2 广告位1 3 广告位2 4 首页新用户专区 5首页秒杀专区 6 首页爆款专区 7 首页品牌专区 9 推荐区域10 积分商城11 断码清仓12 首页主题馆 13启动页面)
	public static final String AD_POSITION_TOP = "1";
	public static final String AD_POSITION_TOP_1 = "2";
	public static final String AD_POSITION_TOP_2 = "3";	
	public static final String AD_POSITION_HOME_NEW_ENJOY = "4";
	public static final String AD_POSITION_HOME_SECKILL = "5";
	public static final String AD_POSITION_HOME_EXPLOSION = "6";
	public static final String AD_POSITION_HOME_BRAND = "7";
	public static final String AD_POSITION_RECOMMEND = "9";
	public static final String AD_POSITION__INTEGRAL_MALL = "10";
	public static final String AD_POSITION_BROKEN_CODE_CLEARING = "11";
	public static final String AD_POSITION_THEME_PAVILIONES = "12";
	
	// bu_single_product_activity
	//单品活动类型(1：新用户专享 2：单品疯折 3：限时秒杀)
	public static final String PRODUCT_ACTIVITY_TYPE_AREA = "0";	//会场特卖
	public static final String PRODUCT_ACTIVITY_TYPE_NEW_ENJOY = "1";	//新用户专享
	public static final String PRODUCT_ACTIVITY_TYPE_EXPLOSION = "2";	//单品疯折
	public static final String PRODUCT_ACTIVITY_TYPE_SECKILL = "3";	//限时秒杀
	public static final String PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL = "4";	//新用户限时秒杀
	public static final String PRODUCT_ACTIVITY_TYPE_INTEGRAL_MALL = "5";	//积分商城
	public static final String PRODUCT_ACTIVITY_TYPE_BROKEN_CODE_CLEARING = "6";	//断码清仓
	public static final String PRODUCT_ACTIVITY_TYPE_BARGAIN = "7";	//砍价
	public static final String PRODUCT_ACTIVITY_TYPE_INVITE_FREE_CHARGE = "8";	//邀请免费拿
	public static final String PRODUCT_ACTIVITY_TYPE_COUPON_PRODUCT = "9";	//优惠券商品
	public static final String PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE = "10";	//助力大减价
	public static final String PRODUCT_ACTIVITY_TYPE_MCHT_SHOP = "101";	//商家店铺
	public static final String PRODUCT_ACTIVITY_TYPE_PRE_SELL = "11";	//预付会场
	
	// app消息记录表（sys_app_message）  
	//业务类型
	public static final String APP_MESSAGE_SUB_ORDER = "1";	//子订单
	public static final String APP_MESSAGE_SERVICE_ORDER = "2";	//售后单
	public static final String APP_MESSAGE_APPEAL_ORDER = "3";	//投诉单
	public static final String APP_MESSAGE_ACTIVITY_AREA = "4";	//会场
	public static final String APP_MESSAGE_ACTIVITY = "5";	//活动
	public static final String APP_MESSAGE_PRODUCT = "6";	//商品
	public static final String APP_MESSAGE_NOT_URL = "7";	//无连接
	
	// 消息类型
	public static final String APP_MESSAGE_TYPE_SYSTEM_MESSAGE = "1";	//系统消息
	public static final String APP_MESSAGE_TYPR_TRAN_MESSAGE = "2";	//交易物流
	
	//TITLE标题
	public static final String APP_MSG_TITLE_MCHT_SHIPPING = "1";	//1 商家已发货
	public static final String APP_MSG_TITLE_ORDER_RECEIPT = "2";	//2 订单已签收
	public static final String APP_MSG_TITLE_ORDER_DELIVERY = "3";	//3 订单派送中
	public static final String APP_MSG_TITLE_REFUND_APPLY = "4";	//4退款申请
	public static final String APP_MSG_TITLE_CUSTOMER_SERVER_APPLY = "5";	//5 售后申请
	public static final String APP_MSG_TITLE_REFUND = "6";	//退货退款
	public static final String APP_MSG_TITLE_EXCHANGE_REMIND = "7";	//换货提醒
	public static final String APP_MSG_TITLE_REFUND_SPEED_REMIND = "8";	//退款进度提醒
	public static final String APP_MSG_TITLE_APPEALY = "9";	//投诉
	public static final String APP_MSG_TITLE_COUPON_EXPIRED = "10";	//优惠券过期
	public static final String APP_MSG_TITLE_SALE_REMIND = "80";	//开售提醒
	
	/** 自定义广告页类型
	 1 首页主题管 2 日常营销入口 3 商品主题馆 4 现金签到 5 砍价免费拿 6 购物车 7 消息 8 商品未上架9:邀请享免单  10:小程序页面--首页栏目装修
	 11:H5装修页面--首页栏目装修 12:有好货 13:每日好店 14:潮鞋上新 15:潮流男装 16:断码特惠 17:运动鞋服 18:潮流美妆 19:食品超市 20.首页H5装修
	 21.醒购店长权益介绍 22.大学生创业 23.SVIP 24.领券中心 25.爆款推荐
	 */
	public static final String PAGE_TYPE_1 = "1";	
	public static final String PAGE_TYPE_2 = "2";	
	public static final String PAGE_TYPE_3 = "3";	
	public static final String PAGE_TYPE_4 = "4";	
	public static final String PAGE_TYPE_5 = "5";	
	public static final String PAGE_TYPE_6 = "6";	
	public static final String PAGE_TYPE_7 = "7";	
	public static final String PAGE_TYPE_8 = "8";
	public static final String PAGE_TYPE_9 = "9";
	public static final String PAGE_TYPE_10 = "10";
	public static final String PAGE_TYPE_11 = "11";
	public static final String PAGE_TYPE_12 = "12";
	public static final String PAGE_TYPE_13 = "13";
	public static final String PAGE_TYPE_14 = "14";
	public static final String PAGE_TYPE_15 = "15";
	public static final String PAGE_TYPE_16 = "16";
	public static final String PAGE_TYPE_17 = "17";
	public static final String PAGE_TYPE_18 = "18";
	public static final String PAGE_TYPE_19 = "19";
	public static final String PAGE_TYPE_20 = "20";
	public static final String PAGE_TYPE_21 = "21";
	public static final String PAGE_TYPE_22 = "22";
	public static final String PAGE_TYPE_23 = "23";
	public static final String PAGE_TYPE_24 = "24";
	public static final String PAGE_TYPE_25 = "25";

	//预售定金总订单表状态1 待付款 2 已付款 3 已取消
	public static final String COMBINE_DEPOSIT_ORDER_STATUS_NO_PAID = "1";//待付款
	public static final String COMBINE_DEPOSIT_ORDER_STATUS_PAID = "2";//已付款
	public static final String COMBINE_DEPOSIT_ORDER_STATUS_CANCEL = "3";//已取消
	
	//预售定金总订单表状态1 待付款 2 已付款 3 已取消
	public static final String SUB_DEPOSIT_STATUS_NO_PAID = "1";//定金未付
	public static final String SUB_DEPOSIT_STATUS_PAID = "2";//定金已付
	public static final String SUB_DEPOSIT_STATUS_TAIL_ARREADY_ORDER= "3";//尾款已下单
	public static final String SUB_DEPOSIT_STATUS_TAIL_PAID = "4";//尾款已付
	public static final String SUB_DEPOSIT_STATUS_TRAN_SUCCESS = "5";//交易完成
	public static final String SUB_DEPOSIT_STATUS_TRAN_REFUND = "6";//已退款
	public static final String SUB_DEPOSIT_STATUS_NO_PAID_DEPOSIT_CANCEL = "7";//未付定金取消
	public static final String SUB_DEPOSIT_STATUS_TAIL_NO_PAY_CANCEL = "8";//尾款下单后未支付取消
	public static final String SUB_DEPOSIT_STATUS_NO_PAID_TAIL = "9";//未付尾款取消（活动结束后未下单或下单后未支付）
	
	//优惠券类型1 全场通用2 品类券3 品牌券
	public static final String COUPON_PLAT_COUPON = "1";//1 全场通用
	public static final String COUPON_CATEGORY_COUPON = "2";//2 品类券
	public static final String COUPON_BRAND_COUPON = "3";//3品牌券
	public static final String COUPON_BRAND_PRODUCT_COUPON = "4";//4商品券

	//限时秒杀前端显示时间段的控制数
	public static final int TIME_INTERVAL = 4;
	
	//醒购规则
	public static final String CATALOG_REGULATION_ID = "CATALOG_REGULATION_ID";
	//关于醒购
	public static final String CATALOG_REGARD_ID = "CATALOG_REGARD_ID";
	//新星计划协议栏目id
	public static final Integer NOVA_PLAN_AGREEMENT_CATALOG_ID = 67;
	//店长计划栏目id
	public static final Integer NOVA_PLAN_SHOPWNER_AGREEMENT_CATALOG_ID = 68;


	public static final String COUPON_RECEIVE_TYPE_1 = "1";
	public static final String COUPON_RECEIVE_TYPE_2 = "2";
	public static final String COUPON_RECEIVE_TYPE_3 = "3";
	public static final String COUPON_RECEIVE_TYPE_4 = "4";
	public static final String COUPON_RECEIVE_TYPE_5 = "5";
	public static final String COUPON_RECEIVE_TYPE_6 = "6";
	public static final String COUPON_RECEIVE_TYPE_7 = "7";
	public static final String WXA_CODE_UNLIMIT = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="; //获取小程序码
	public static boolean valitateCouponReceiveType(String couponReceiveType) {
		return COUPON_RECEIVE_TYPE_1.equals(couponReceiveType) || COUPON_RECEIVE_TYPE_2.equals(couponReceiveType)
				|| COUPON_RECEIVE_TYPE_3.equals(couponReceiveType) || COUPON_RECEIVE_TYPE_4.equals(couponReceiveType)
				|| COUPON_RECEIVE_TYPE_5.equals(couponReceiveType) || COUPON_RECEIVE_TYPE_6.equals(couponReceiveType)
				|| COUPON_RECEIVE_TYPE_7.equals(couponReceiveType);
	}

	/** cron表达式
	 一个cron表达式有至少6个（也可能7个）有空格分隔的时间元素。
	 按顺序依次为
	 秒（0~59）
	 分钟（0~59）
	 小时（0~23）
	 天（月）（0~31，但是你需要考虑你月的天数）
	 月（0~11）
	 天（星期）（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）
	 年份（1970－2099）

	 其中每个元素可以是一个值(如6),一个连续区间(9-12),一个间隔时间(8-18/4)(/表示每隔4小时),一个列表(1,3,5),通配符。由于"月份中的日期"和"星期中的日期"这两个元素互斥的,必须要对其中一个设置?

	 0 0 10,14,16 * * ? 每天上午10点，下午2点，4点
	 0 0/30 9-17 * * ? 朝九晚五工作时间内每半小时
	 0 0 12 ? * WED 表示每个星期三中午12点
	 "0 0 12 * * ?" 每天中午12点触发
	 "0 15 10 ? * *" 每天上午10:15触发
	 "0 15 10 * * ?" 每天上午10:15触发
	 "0 15 10 * * ? *" 每天上午10:15触发
	 "0 15 10 * * ? 2005" 2005年的每天上午10:15触发
	 "0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发
	 "0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发
	 "0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发
	 "0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发
	 "0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发
	 "0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发
	 "0 15 10 15 * ?" 每月15日上午10:15触发
	 "0 15 10 L * ?" 每月最后一日的上午10:15触发
	 "0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发
	 "0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发
	 "0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发
	 */
}

