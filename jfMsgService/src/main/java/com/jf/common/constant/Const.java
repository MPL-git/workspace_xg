package com.jf.common.constant;

/**
 * 公共常量
 * @author hdl
 */
public class Const {

	//热云付费报送
	public static final String REG_CLIENT_IOS = "ios";
	public static final String REG_CLIENT_ANDROID = "android";

	//极光推送平台
	public static final String PLATFORM_ALL = "all";
	public static final String PLATFORM_ANDROID = "android";
	public static final String PLATFORM_IOS = "ios";
	public static final Integer MAX_PAGE = 500;

	//玄武短信任务
	public static final String customMsgIDHead = "XW-TASK";
	public static final String customMsgIDTail = "TAIl";
	public static final Integer MAX_SEND_SMS = 500000;
	public static final Integer MAX_TASK_SEND_PAGE = 5000;

	//梦网短信
	public static final Integer MW_MAX_TASK_SEND_PAGE = 1000;
	public static final String MW_SYS_ACCOUNT = "1";//系统账户
	public static final String MW_TASK_ACCOUNT = "2";//营销账户
	public static final String MW_SINGLE_SEND = "1";//单条发送
	public static final String MW_BATCH_SEND = "2";//批量发送

	//短信签名
	public static final String MESSAGE_SIGNATURES = "【醒购】";

	//流量统计多线程
	public static final int threadNum = 5; //线程数
	public static final int pvNum = 2000; //使用多线程判断数据量
	public static final int pvListNum = 1000; //使用批量插入

	public static final int PER_INSERT_SIZE = 200;
	
	//一级类目ID
	//线上环境
	public static final String PRODUCT_TYPE2 = "2"; //2.运动户外
	public static final String PRODUCT_TYPE3 = "3"; //3.鞋靴箱包
	public static final String PRODUCT_TYPE4 = "4"; //4.服装配饰
	public static final String PRODUCT_TYPE5 = "5"; //5.生活家居
	public static final String PRODUCT_TYPE430 = "430"; //430.钟表珠宝
	public static final String PRODUCT_TYPE705 = "705"; //705.数码家电
	public static final String PRODUCT_TYPE858 = "858"; //858.美妆个护
	public static final String PRODUCT_TYPE954 = "954"; //954.母婴童装
	public static final String PRODUCT_TYPE1047 = "1047"; //1047.食品超市
	//测试环境
	/*public static final String PRODUCT_TYPE2 = "316"; //316.运动户外
	public static final String PRODUCT_TYPE3 = "318"; //318.鞋靴箱包
	public static final String PRODUCT_TYPE4 = "317"; //317.服装配饰
	public static final String PRODUCT_TYPE5 = "319"; //319.生活家居
	public static final String PRODUCT_TYPE430 = "433"; //433.钟表珠宝
	public static final String PRODUCT_TYPE705 = "500"; //500.数码家电
	public static final String PRODUCT_TYPE858 = "354"; //354.美妆个护
	public static final String PRODUCT_TYPE954 = "399"; //399.母婴童装
	public static final String PRODUCT_TYPE1047 = "364"; //364.食品超市*/
	// 每日广告位统计--广告类目
	public static final String AD_TYPE1 = "1"; //1.首页
	public static final String AD_TYPE2 = "2"; //2.运动户外
	public static final String AD_TYPE3 = "3"; //3.服装配饰
	public static final String AD_TYPE4 = "4"; //4.鞋靴箱包
	public static final String AD_TYPE5 = "5"; //5.钟表珠宝
	public static final String AD_TYPE6 = "6"; //6.生活家居
	public static final String AD_TYPE7 = "7"; //7.数码家电
	public static final String AD_TYPE8 = "8"; //8.美妆个护
	public static final String AD_TYPE9 = "9"; //9.母婴童装
	public static final String AD_TYPE10 = "10"; //10.食品超市
	public static final String AD_TYPE11 = "11"; //11.预告
	public static final String AD_TYPE12 = "12"; //12.限时秒杀
	public static final String AD_TYPE13 = "13"; //13.新用户专享
	public static final String AD_TYPE14 = "14"; //14.新人秒杀
	public static final String AD_TYPE15 = "15"; //15.积分商城
	public static final String AD_TYPE16 = "16"; //16.SVIP
	public static final String AD_TYPE17 = "17"; //17.有好货
	public static final String AD_TYPE18 = "18"; //18.每日好店
	public static final String AD_TYPE19 = "19"; //19.启动广告
	public static final String AD_TYPE20 = "20"; //20.拉新分润好货推荐
	public static final String AD_TYPE21 = "21"; //21.潮鞋上新
	public static final String AD_TYPE22 = "22"; //22.潮流男装
	public static final String AD_TYPE23 = "23"; //23.断码特惠
	public static final String AD_TYPE24 = "24"; //24.运动鞋服
	public static final String AD_TYPE25 = "25"; //25.潮流美妆
	public static final String AD_TYPE26 = "26"; //26.食品超市
	public static final String AD_TYPE27 = "27"; //27.大学生创业
	public static final String AD_TYPE28 = "28"; //28.全品类（淘宝客）
	public static final String AD_TYPE29 = "29"; //29.领券中心

	// 流量统计
	public static final String FLOW_PARSE_DATE = "FLOW_PARSE_DATE"; //流量统计到的日期
	// 页面分类
	public static final String PAGE_CLASSIFY1 = "1"; //1.限时抢购
	public static final String PAGE_CLASSIFY2 = "2"; //2.单品爆款
	public static final String PAGE_CLASSIFY3 = "3"; //3.新用户专区
	public static final String PAGE_CLASSIFY4 = "4"; //4.新积分商城
	public static final String PAGE_CLASSIFY5 = "5"; //5.断码清仓
	public static final String PAGE_CLASSIFY6 = "6"; //6.砍价免费拿
	public static final String PAGE_CLASSIFY7 = "7"; //7.邀请享免单
	public static final String PAGE_CLASSIFY8 = "8"; //8.签到领红包
	public static final String PAGE_CLASSIFY9 = "9"; //9.特卖
	public static final String PAGE_CLASSIFY10 = "10"; //10.商品
	public static final String PAGE_CLASSIFY11 = "11"; //11.会场
	public static final String PAGE_CLASSIFY12 = "12"; //12.消息页
	public static final String PAGE_CLASSIFY13 = "13"; //13.首页
	public static final String PAGE_CLASSIFY14 = "14"; //14.个人中心
	public static final String PAGE_CLASSIFY15 = "15"; //15.商城
	public static final String PAGE_CLASSIFY16 = "16"; //16.商家店铺
	public static final String PAGE_CLASSIFY17 = "17"; //17.特卖分类
	public static final String PAGE_CLASSIFY18 = "18"; //18.购物车
	public static final String PAGE_CLASSIFY19 = "19"; //19.助力减价
	public static final String PAGE_CLASSIFY20 = "20"; //20.登录页
	public static final String PAGE_CLASSIFY21 = "21"; //21.SVIP
	public static final String PAGE_CLASSIFY22 = "22"; //22.有好货
	public static final String PAGE_CLASSIFY23 = "23"; //23.每日好店
	public static final String PAGE_CLASSIFY24 = "24"; //24.拉新分润好货推荐
	public static final String PAGE_CLASSIFY25 = "25"; //25.启动页
	public static final String PAGE_CLASSIFY26 = "26"; //26.淘宝优选
	public static final String PAGE_CLASSIFY27 = "27"; //27.潮流上新
	public static final String PAGE_CLASSIFY28 = "28"; //28.潮流男装
	public static final String PAGE_CLASSIFY29 = "29"; //29.运动鞋服
	public static final String PAGE_CLASSIFY30 = "30"; //30.潮流美妆
	public static final String PAGE_CLASSIFY31 = "31"; //31.食品超市
	public static final String PAGE_CLASSIFY32 = "32"; //32.大学生创业
	public static final String PAGE_CLASSIFY33 = "33"; //33.断码特惠
	public static final String PAGE_CLASSIFY34 = "34"; //34.自建页面
	public static final String PAGE_CLASSIFY35 = "35"; //35.醒购视频
	public static final String PAGE_CLASSIFY36 = "36"; //36.领券中心
	public static final String PAGE_CLASSIFY37 = "37"; //37.醒购视频详情
	// 页面类型(参考数据参库表格)
	public static final String PAGE_TYPE1 = "1"; //1.限时抢购
	public static final String PAGE_TYPE2 = "2"; //2.限时抢购-品牌团
	public static final String PAGE_TYPE3 = "3"; //3.限时抢购-广告位
	public static final String PAGE_TYPE4 = "4"; //4.单品爆款
	public static final String PAGE_TYPE5 = "5"; //5.单品爆款-运动
	public static final String PAGE_TYPE6 = "6"; //6.单品爆款-服饰
	public static final String PAGE_TYPE7 = "7"; //7.单品爆款-鞋包
	public static final String PAGE_TYPE8 = "8"; //8.单品爆款-钟表
	public static final String PAGE_TYPE9 = "9"; //9.单品爆款-生活
	public static final String PAGE_TYPE10 = "10"; //10.单品爆款-数码
	public static final String PAGE_TYPE11 = "11"; //11.单品爆款-食品
	public static final String PAGE_TYPE12 = "12"; //12.单品爆款-美妆
	public static final String PAGE_TYPE13 = "13"; //13.单品爆款-母婴
	public static final String PAGE_TYPE14 = "14"; //14.新用户专区
	public static final String PAGE_TYPE15 = "15"; //15.新用户秒杀
	public static final String PAGE_TYPE16 = "16"; //16.新积分商城
	public static final String PAGE_TYPE17 = "17"; //17.断码清仓
	public static final String PAGE_TYPE18 = "18"; //18.断码清仓-类目ID
	public static final String PAGE_TYPE19 = "19"; //19.砍价免费拿
	public static final String PAGE_TYPE20 = "20"; //20.砍价免费拿-砍价商品
	public static final String PAGE_TYPE21 = "21"; //21.砍价免费拿-我的砍价
	public static final String PAGE_TYPE22 = "22"; //22.邀请享免单
	public static final String PAGE_TYPE23 = "23"; //23.邀请享免单-邀请商品
	public static final String PAGE_TYPE24 = "24"; //24.邀请享免单-我的邀请
	public static final String PAGE_TYPE25 = "25"; //25.签到领红包
	public static final String PAGE_TYPE26 = "26"; //26.签到领红包-提现
	public static final String PAGE_TYPE27 = "27"; //27.特卖ID
	public static final String PAGE_TYPE28 = "28"; //28.特卖商品列表
	public static final String PAGE_TYPE29 = "29"; //29.商品详情页
	public static final String PAGE_TYPE30 = "30"; //30.会场ID
	public static final String PAGE_TYPE31 = "31"; //31.消息页
	public static final String PAGE_TYPE32 = "32"; //32.消息页-系统消息
	public static final String PAGE_TYPE33 = "33"; //33.消息页-交易消息
	public static final String PAGE_TYPE34 = "34"; //34.首页
	public static final String PAGE_TYPE35 = "35"; //35.首页-广告位
	public static final String PAGE_TYPE36 = "36"; //36.个人中心
	public static final String PAGE_TYPE37 = "37"; //37.个人中心-个人资料
	public static final String PAGE_TYPE38 = "38"; //38.个人中心-我的订单
	public static final String PAGE_TYPE39 = "39"; //39.个人中心-可抵用积分
	public static final String PAGE_TYPE40 = "40"; //40.个人中心-优惠券
	public static final String PAGE_TYPE41 = "41"; //41.个人中心-我的收藏
	public static final String PAGE_TYPE42 = "42"; //42.个人中心-我的收藏（商品）
	public static final String PAGE_TYPE43 = "43"; //43.个人中心-我的收藏（店铺）
	public static final String PAGE_TYPE44 = "44"; //44.个人中心-浏览过的商品
	public static final String PAGE_TYPE45 = "45"; //45.个人中心-收货地址
	public static final String PAGE_TYPE46 = "46"; //46.个人中心-平台客服
	public static final String PAGE_TYPE47 = "47"; //47.个人中心-平台热线
	public static final String PAGE_TYPE48 = "48"; //48.个人中心-帮助中心
	public static final String PAGE_TYPE49 = "49"; //49.个人中心-意见反馈
	public static final String PAGE_TYPE50 = "50"; //50.个人中心-版本信息
	public static final String PAGE_TYPE51 = "51"; //51.个人中心-商家入驻申请
	public static final String PAGE_TYPE52 = "52"; //52.个人中心-分享APP
	public static final String PAGE_TYPE53 = "53"; //53.个人中心-设置
	public static final String PAGE_TYPE54 = "54"; //54.个人中心-关于醒购
	public static final String PAGE_TYPE55 = "55"; //55.商城
	public static final String PAGE_TYPE56 = "56"; //56.商城-商城分类（类目ID）
	public static final String PAGE_TYPE57 = "57"; //67.商城-广告位
	public static final String PAGE_TYPE58 = "58"; //58.商城-商品列表页（含搜索、筛选、结果）
	public static final String PAGE_TYPE59 = "59"; //59.商家店铺首页
	public static final String PAGE_TYPE60 = "60"; //60.运动
	public static final String PAGE_TYPE61 = "61"; //61.服饰
	public static final String PAGE_TYPE62 = "62"; //62.鞋包
	public static final String PAGE_TYPE63 = "63"; //63.钟表
	public static final String PAGE_TYPE64 = "64"; //64.生活
	public static final String PAGE_TYPE65 = "65"; //65.数码
	public static final String PAGE_TYPE66 = "66"; //66.食品
	public static final String PAGE_TYPE67 = "67"; //67.美妆
	public static final String PAGE_TYPE68 = "68"; //68.母婴
	public static final String PAGE_TYPE69 = "69"; //69.预告
	public static final String PAGE_TYPE70 = "70"; //70.购物车
	public static final String PAGE_TYPE71 = "71"; //71.购物车-确定订单页
	public static final String PAGE_TYPE72 = "72"; //72.助力减价
	public static final String PAGE_TYPE73 = "73"; //73.助力减价-助力减价商品
	public static final String PAGE_TYPE74 = "74"; //74.助力减价-我的邀请单
	public static final String PAGE_TYPE75 = "75"; //75.登录页
	public static final String PAGE_TYPE76 = "76"; //76.SVIP
	public static final String PAGE_TYPE77 = "77"; //77.有好货
	public static final String PAGE_TYPE78 = "78"; //78.每日好店
	public static final String PAGE_TYPE79 = "79"; //79.拉新分润好货推荐
	public static final String PAGE_TYPE80 = "80"; //80.启动页
	public static final String PAGE_TYPE81 = "81"; //81.淘宝优选
	public static final String PAGE_TYPE82 = "82"; //82.商品列表（含搜索、结果）
	public static final String PAGE_TYPE83 = "83"; //83.潮流上新
	public static final String PAGE_TYPE84 = "84"; //84.潮流男装
	public static final String PAGE_TYPE85 = "85"; //85.运动鞋服
	public static final String PAGE_TYPE86 = "86"; //86.潮流美妆
	public static final String PAGE_TYPE87 = "87"; //87.食品超市
	public static final String PAGE_TYPE88 = "88"; //88.大学生创业
	public static final String PAGE_TYPE89 = "89"; //89.首页-商品列表页（结果）
	public static final String PAGE_TYPE90 = "90"; //90.断码特惠
	public static final String PAGE_TYPE91 = "91"; //91.新星计划
	public static final String PAGE_TYPE92 = "92"; //92.订单详情
	public static final String PAGE_TYPE93 = "93"; //93.店铺动态详情
	public static final String PAGE_TYPE94 = "94"; //94.自建页面ID
	public static final String PAGE_TYPE95 = "95"; //95.我的关注
	public static final String PAGE_TYPE96 = "96"; //96.精选
	public static final String PAGE_TYPE97 = "97"; //97.其它醒购视频
	public static final String PAGE_TYPE98 = "98"; //98.领券中心
	public static final String PAGE_TYPE99 = "99"; //99.醒购视频详情

	
	// 玄武科技短息发送
	public static final boolean XW_LINK_MODE = false; // 连接模式: false代表长连接(短连接为true)
	public static final int XW_SOCKET_OVER_TIME = 10000; // 超时时间: socket超时时间(毫秒)
	public static final int XW_SOCKET_CONCURRENT = 3; // 下行短信每个账号最大并发socket连接数: 一般配置为2, 如果发送量比较大可调整为3-4
	public static final String XW_IP_A = "211.147.239.62"; // 设置网关的IP和port，用于发送信息
	public static final int XW_PORT_A = 9080; 
	public static final String XW_IP_B = "211.147.239.62"; // 设置网关的IP和port，用于获取账号信息、上行、状态报告等等
	public static final int XW_PORT_B = 9070;
	
	// 头条推广
	public static final String APP_ID = "1608387215542285"; // 头条APP_ID
	public static final String SECRET = "606341736f249a7628f6e0715c037c9aaaefb57a"; // 头条SECRET
	public static final String GRANT_TYPE_ACCESS_TOKEN = "auth_code"; // 头条授权类型（获取access_token）
	public static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token"; // 头条授权类型（刷新access_token）
	public static final String ADVERTISER_INFO = "https://ad.toutiao.com/open_api/2/advertiser/info/"; // 广告主信息
	public static final String ACCESS_TOKEN_POST = "https://ad.toutiao.com/open_api/oauth2/access_token/"; // 获取access_token
	public static final String REFRESH_TOKEN_POST = "https://ad.toutiao.com/open_api/oauth2/refresh_token/"; // 刷新access_token
	public static final String CAMPAIGN_GET = "https://ad.toutiao.com/open_api/2/campaign/get/"; // 获取广告组（新）
	public static final String AD_GET = "https://ad.toutiao.com/open_api/2/ad/get/"; // 获取广告计划（新）
	public static final String CREATIVE_SELECT = "https://ad.toutiao.com/open_api/2/creative/select/"; // 广告创意列表
	public static final String CREATIVE_READ_V2 = "https://ad.toutiao.com/open_api/2/creative/read_v2/"; // 创意详细信息（新版）
	public static final int SEND_ERROR_NUM = 3; // 发送报错重新请求次数
	
	
	
	
	
	// app消息记录表（sys_app_message） 
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
	public static final String APP_MSG_TITLE_INTERVENTION_ORDER = "11";	//介入消息
	public static final String APP_MSG_TITLE_ORDER_COMPLATE = "12";	//订单完成
	public static final String APP_MSG_TITLE_NOVA_PLAN = "13";	//新星计划
	public static final String APP_MSG_TITLE_NOT_USED_FIVE_HUNDRED = "15";	//新人500元优惠券未使用提醒
	public static final String APP_MSG_TITLE_SALE_REMIND = "80";	//开售提醒
	
	// 站内信表（bu_platform_msg） 
	//msg_type消息类型
	public static final String PLATFORM_MSG_TYPE_A1 = "A1";	//A1退款
	public static final String PLATFORM_MSG_TYPE_A2 = "A2";	//A2退货
	public static final String PLATFORM_MSG_TYPE_A3 = "A3";	//A3换货
	public static final String PLATFORM_MSG_TYPE_A4 = "A4";	//A4投诉
	public static final String PLATFORM_MSG_TYPE_A5 = "A5";	//A5违规
	public static final String PLATFORM_MSG_TYPE_A6 = "A6";	//A6活动
	
	//0：未读 1：已读
	public static final String PLATFORM_MSG_STATUS_0 = "0";	//0未读
	public static final String PLATFORM_MSG_STATUS_1 = "1";	//1已读

	// 标识状态
	public static final String FLAG_FALSE = "0";	//未标识
	public static final String FLAG_TRUE = "1";	//已标识

	// 操作员类型
	public static final String OPERATOR_TYPE_MEMBER = "1";	//客户
	public static final String OPERATOR_TYPE_MCHT = "2";	//商家
	public static final String OPERATOR_TYPE_SYSTEM = "3";	//系统
	
	// app消息记录表 
	//业务类型
	public static final String APP_MESSAGE_SUB_ORDER = "1";	//子订单
	public static final String APP_MESSAGE_SERVICE_ORDER = "2";	//售后单
	public static final String APP_MESSAGE_APPEAL_ORDER = "3";	//投诉单
	public static final String APP_MESSAGE_ACTIVITY_AREA = "4";	//会场
	public static final String APP_MESSAGE_ACTIVITY = "5";	//活动
	public static final String APP_MESSAGE_PRODUCT = "6";	//商品
	public static final String APP_MESSAGE_NOT_URL = "7";	//无连接
	public static final String APP_MESSAGE_INTEGRAL_MALL = "9";	//积分商城
	public static final String APP_MESSAGE_NOVA_PLAN_INDEX = "10";	//新星计划首页
	
	// 消息类型
	public static final String APP_MESSAGE_TYPE_SYSTEM_MESSAGE = "1";	//系统消息
	public static final String APP_MESSAGE_TYPR_TRAN_MESSAGE = "2";	//交易物流
	
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


	// 子订单
	// 状态
	public static final String ORDER_STATUS_NOT_PAID = "0";	//未付款
	public static final String ORDER_STATUS_PAID = "1";		//待发货
	public static final String ORDER_STATUS_SHIPPED = "2";	//待收货
	public static final String ORDER_STATUS_SUCCESS = "3";	//完成
	public static final String ORDER_STATUS_CANCEL = "4";	//取消
	public static final String ORDER_STATUS_CLOSE = "5";	//关闭
	public static final String ORDER_STATUS_RECEIPT = "6";	//已收货


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
	public static final String VIOLATE_ORDER_TYPE_D = "D";	//售后
	public static final String VIOLATE_ORDER_ACTION_D3 = "D3";	//延迟发货
	public static final String VIOLATE_ORDER_ACTION_D1 = "D1";	//虚假发货发货
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
	
	// 积分使用类型
	public static final String INTEGRAL_TYPE_SHOPPONG = "1";	//购物赠送
	public static final String INTEGRAL_TYPE_MOBILE_AUTHENTICATION = "2";//手机认证
	public static final String INTEGRAL_TYPE_DATA_PERFECT= "3";	//完善资料
	public static final String INTEGRAL_TYPE_ORDER_PAYMENT = "4";	//购物抵扣
	public static final String INTEGRAL_TYPE_EXCHANGE = "5";	//积分兑换
	public static final String INTEGRAL_TYPE_SYSTEM_GIFT = "6";	//系统赠送
	public static final String INTEGRAL_TYPE_DEDUCT_RETURN = "7";	//抵扣还还
	
	// 积分类型
	public static final String INTEGRAL_TALLY_MODE_INCOME = "1";	//积分进账
	public static final String INTEGRAL_TALLY_MODE_ACCOUNT = "2";	//积分出账




	// -----------------------------------------------------------------------------------------------------------------
	// 商家
	// -----------------------------------------------------------------------------------------------------------------

	// 商家类型
	public static final String MCHT_TYPE_UNION = "1";	//联营
	public static final String MCHT_TYPE_POP = "2";		//POP

	// 商家合作状态
	public static final String MCHT_STATUS_ING = "0";	//入驻中
	public static final String MCHT_STATUS_NORMAL = "1";	//正常
	public static final String MCHT_STATUS_STOP = "2";	//业务暂停
	public static final String MCHT_STATUS_CLOSED = "3";	//关闭

	// 商家总资质审核状态
	public static final String MCHT_TOTAL_AUDIT_STATUS_WAIT_AUDIT = "0";	//待审
	public static final String MCHT_TOTAL_AUDIT_STATUS_AUDITING = "1";	//审核中
	public static final String MCHT_TOTAL_AUDIT_STATUS_PASS = "2";	//通过
	public static final String MCHT_TOTAL_AUDIT_STATUS_REJECT = "3";	//驳回

	// 商家联系人分配状态
	public static final String MCHT_CONTACT_STATUS_NORMAL = "1";	//正常
	public static final String MCHT_CONTACT_STATUS_CANCEL = "2";	//取消

	// 平台联系人类型
	public static final String PLAT_CONTACT_TYPE_MERCHANTS = "1";	//招商对接人
	public static final String PLAT_CONTACT_TYPE_OPERATION = "2";	//运营对接人
	public static final String PLAT_CONTACT_TYPE_ORDER = "3";	//订单对接人
	public static final String PLAT_CONTACT_TYPE_ORDER_AFTER = "4";	//售后对接人
	public static final String PLAT_CONTACT_TYPE_FINANCE = "5";	//财务对接人
	public static final String PLAT_CONTACT_TYPE_CUSTOMER = "6";	//客服对接人
	public static final String PLAT_CONTACT_TYPE_FAWU = "7";	//法务对接人
	public static final String PLAT_CONTACT_TYPE_SERVICE = "8";	//商家服务专员

	// 平台联系人状态
	public static final String PLAT_CONTACT_STATUS_NORMAL = "0";	//正常
	public static final String PLAT_CONTACT_STATUS_STOP = "1";	//暂停
	public static final String PLAT_CONTACT_STATUS_FAIL = "2";	//失效

	// -----------------------------------------------------------------------------------------------------------------
	// 入驻合同
	// -----------------------------------------------------------------------------------------------------------------

	// 合同类型
	public static final String MCHT_CONTRACT_TYPE_NEW = "1";	//新签
	public static final String MCHT_CONTRACT_TYPE_RENEW = "2";	//续签

	// 续签状态
	public static final String MCHT_CONTRACT_RENEW_STATUS_WAIT = "0";	//未处理
	public static final String MCHT_CONTRACT_RENEW_STATUS_YES = "1";	//已生成续签
	public static final String MCHT_CONTRACT_RENEW_STATUS_NO = "2";	//不续签

	// 审核状态
	public static final String MCHT_CONTRACT_AUDIT_STATUS_NONE = "1";	//未上传
	public static final String MCHT_CONTRACT_AUDIT_STATUS_WAIT = "2";	//待审核
	public static final String MCHT_CONTRACT_AUDIT_STATUS_PASS = "3";	//审核通过
	public static final String MCHT_CONTRACT_AUDIT_STATUS_REJECT = "4";	//审核驳回

	// 归档状态
	public static final String MCHT_CONTRACT_ARCHIVE_STATUS_WAIT = "0";	//未处理
	public static final String MCHT_CONTRACT_ARCHIVE_STATUS_PASS = "1";	//通过已归档
	public static final String MCHT_CONTRACT_ARCHIVE_STATUS_REJECT = "2";	//不通过驳回
	public static final String MCHT_CONTRACT_ARCHIVE_STATUS_CLOSE = "4";	//不签约

	// 寄出状态
	public static final String MCHT_CONTRACT_SEND_STATUS_NO = "0";	//未寄
	public static final String MCHT_CONTRACT_SEND_STATUS_YES = "1";	//已寄


	// -----------------------------------------------------------------------------------------------------------------
	// 入驻合同
	// -----------------------------------------------------------------------------------------------------------------

	// 关店申请状态
	public static final String MCHT_CLOSE_APPLY_STATUS_WAIT = "1";	//待审核
	public static final String MCHT_CLOSE_APPLY_STATUS_AUDITED = "2";	//已审核

	// 关店申请人类型
	public static final String MCHT_CLOSE_APPLY_REQUEST_TYPE_LAW = "1";	// 法务
	public static final String MCHT_CLOSE_APPLY_REQUEST_TYPE_OPERATE = "2";	// 运营
	public static final String MCHT_CLOSE_APPLY_REQUEST_TYPE_MERCHANTS = "3";	// 招商

	// 关店申请审核状态
	public static final String MCHT_CLOSE_APPLY_AUDIT_STATUS_AUDIT = "0";	//待审
	public static final String MCHT_CLOSE_APPLY_AUDIT_STATUS_PASS = "1";	//同意
	public static final String MCHT_CLOSE_APPLY_AUDIT_STATUS_REJECT = "2";	//不同意

	// 关店执行状态
	public static final String MCHT_CLOSE_APPLY_EXE_STATUS_NO = "0";	//未执行
	public static final String MCHT_CLOSE_APPLY_EXE_STATUS_YES = "1";	//已执行
	
	// member_info 用户类型
	public static final String MEMBER_INFO_TYPE_MEMBER = "1";	//正式会员
	public static final String MEMBER_INFO_TYPE_THIRD = "2";	//第三方
	public static final String MEMBER_INFO_TYPE_TOURIST = "3";	//游客
	public static final String MEMBER_INFO_TYPE_NEW_TOURIST = "4";	//新游客
	
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
	public static final String PRODUCT_ACTIVITY_TYPE_MCHT_SHOP = "101";	//商家店铺
	/**
	 * 1.WebPower
	 */
	public static final String SMS_PLATFORM_WEBPOWER = "1";
	/**
	 * 2.快递鸟
	 */
	public static final String SMS_PLATFORM_KDN = "2";
	/**
	 * 3.玄武
	 */
	public static final String SMS_PLATFORM_XW = "3";
	/**
	 * 4.上海歆阳
	 */
	public static final String SMS_PLATFORM_SHXY = "4";
	/**
	 * 5.梦网
	 */
	public static final String SMS_PLATFORM_MW = "5";

	/**
	 * 营销短信渠道(0：玄武 2：上海歆阳 3：梦网)
	 */
	public static final String TASK_XW_SMS_CHANNEL = "0";
	public static final String TASK_SHXY_SMS_CHANNEL = "2";
	public static final String TASK_MW_SMS_CHANNEL = "3";

	/**
	 * 物流查询渠道(1：快递100 2：猪猪云)
	 */
	public static final String WULIU_QUERY_CHANNEL_KDN = "1";
	public static final String WULIU_QUERY_CHANNEL_ZZY = "2";

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

