package com.jf.common.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公共常量
 * @author hdl
 */
public class Const {

	//APP类型
	public static final String DEVICE_TYPE_IOS = "1"; //IOS
	public static final String DEVICE_TYPE_ANDROID = "2"; //Android

	//短信任务分页
	public static final int TASK_SMS_LIMIT_START = 0;
	public static final int TASK_SMS_LIMIT_SIZE = 100000;
	public static final int TASK_SMS_INSERT_COUNT = 10000;

	//任务时间间隔
	public static final int TASK_TIME_INTERVAL = 6; //分钟

	//任务时间间隔（发送条数，单位万）
	public static final int TASK_SEND_COUNT = 10;

	//任务状态
	public static final String TASK_STATUS_0 = "0"; //未提交
	public static final String TASK_STATUS_1 = "1"; //待审核
	public static final String TASK_STATUS_2 = "2"; //待审批
	public static final String TASK_STATUS_3 = "3"; //待执行
	public static final String TASK_STATUS_4 = "4"; //已执行
	public static final String TASK_STATUS_5 = "5"; //审核驳回
	public static final String TASK_STATUS_6 = "6"; //审批驳回
	public static final String TASK_STATUS_7 = "7"; //已取消
	
	// 任务类型
	public static final String TASK_TYPE_0 = "0"; //短信
	public static final String TASK_TYPE_1 = "1"; //营销信息
	public static final String TASK_TYPE_2 = "2"; //活动精选
	
	// 任务短信
	public static final String XW_SEND_PRICE = "XW_SEND_PRICE"; //玄武短信单价（元/条）
	public static final String XY_SEND_PRICE = "XY_SEND_PRICE"; //歆阳短信单价（元/条）
	public static final String MW_SEND_PRICE = "MW_SEND_PRICE"; //梦网短信单价（元/条）

	// 任务短信渠道
	public static final String XW_SMS_SEND_CHANNEL = "0"; //玄武渠道
	public static final String XY_SMS_SEND_CHANNEL = "2"; //上海歆阳渠道
	public static final String MW_SMS_SEND_CHANNEL = "3"; //梦网渠道

	public static final String WXA_PAGE = "page/spr/index"; //跳转页面
	public static final String WXA_CODE_UNLIMIT = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="; //获取小程序码
	
	// 抖音推广链接
	public static final String DOUYIN_SY = "/xgbuy/views/index.html?redirect_url=home/index.html?adSprId="; //1 首页
	public static final String DOUYIN_XSQG = "/xgbuy/views/index.html?redirect_url=rushbuy/index.html?type=3&adSprId="; //2 限时抢购
	public static final String DOUYIN_DPBK = "/xgbuy/views/index.html?redirect_url=activity/templet/single_explosion.html?type=2&adSprId="; //3 单品爆款
	public static final String DOUYIN_XYHZQ = "/xgbuy/views/index.html?redirect_url=newman/index.html?type=1&adSprId="; //4 新用户专区
	public static final String DOUYIN_XYHMS= "/xgbuy/views/index.html?redirect_url=newman/seckill/index.html?adSprId="; //5 新用户秒杀
	public static final String DOUYIN_DMQC = "/xgbuy/views/index.html?redirect_url=activity/templet/single_duanma.html?adSprId="; //6 断码清仓
	public static final String DOUYIN_SPXQY = "/xgbuy/views/index.html?redirect_url=goods/detail.html?id="; //7 商品详情页
	public static final String DOUYIN_TMXQY = "/xgbuy/views/index.html?redirect_url=activity/templet/single_hot.html?activityId="; //8 特卖详情页
	public static final String DOUYIN_PPHCXQY = "/xgbuy/views/index.html?redirect_url=activity/templet/brand_def.html?activityAreaId="; //9 品牌会场详情页
	public static final String DOUYIN_DPHCXQY = "/xgbuy/views/index.html?redirect_url=activity/templet/single_def.html?activityAreaId="; //10 单品会场详情页
	
	// 一级类目ID
	public static final int TYPE_ID_2 = 2; // 运动户外
	public static final int TYPE_ID_3 = 3; // 鞋靴箱包
	public static final int TYPE_ID_4 = 4; // 服装配饰
	public static final int TYPE_ID_5 = 5; // 生活家居
	public static final int TYPE_ID_430 = 430; // 钟表珠宝
	public static final int TYPE_ID_705 = 705; // 数码家电
	public static final int TYPE_ID_858 = 858; // 美妆个护
	public static final int TYPE_ID_954 = 954; // 母婴童装
	public static final int TYPE_ID_1047 = 1047; // 食品超市
	
	public static final int ROLE_ID_80 = 80; // 【平台端：推荐管理】特卖一级分类推荐和统计权限优化：角色ID=80可查看所有类目推荐、统计数据，角色ID！=80，只能查看负责类目的推荐、统计
	public static final int ROLE_ID_84 = 84; // 【优化】【平台端-优惠券管理】审核权控制
	public static final int ROLE_ID_99 = 99; // 【优化】【平台端-财务管理-POP结算】折扣调差控制
	public static final int ROLE_ID_94 = 94; // 复审操作权限控制，复审状态为【未复审】时出现。权限控制为：角色ID为94
	public static final int ROLE_ID_95 = 95; // 限出纳（角色ID为：95）才能操作【转账】、【驳回】
	public static final int ROLE_ID_60 = 60; // 拥有角色ID=60的用户，不呈现往期销量
	
	public static final String AD_CREATE = "https://ad.toutiao.com/open_api/2/ad/create/"; // 创建广告计划
	public static final String CREATIVE_CREATE_V2 = "https://ad.toutiao.com/open_api/2/creative/create_v2/"; // 创建广告创意（新版）
	public static final String ADVERTISER_INFO = "https://ad.toutiao.com/open_api/2/advertiser/info/"; // 广告主信息
	public static final String CAMPAIGN_GET = "https://ad.toutiao.com/open_api/2/campaign/get/"; // 获取广告组（新）
	public static final String AD_GET = "https://ad.toutiao.com/open_api/2/ad/get/"; // 获取广告计划（新）
	public static final String CREATIVE_READ_V2 = "https://ad.toutiao.com/open_api/2/creative/read_v2/"; // 创意详细信息（新版）
	public static final int SEND_ERROR_NUM = 3; // 发送报错重新请求次数

	// 标识状态
	public static final String FLAG_FALSE = "0";	//未标识
	public static final String FLAG_TRUE = "1";	//已标识


	// 总订单
	// 状态
	public static final String COMBINE_ORDER_STATUS_NOT_PAID = "0";	//未付
	public static final String COMBINE_ORDER_STATUS_PAID = "1";		//已付
	public static final String COMBINE_ORDER_STATUS_CANCEL = "4";	//取消
	// 付款状态
	public static final String COMBINE_ORDER_PAY_STATUS_SUCCESS = "1";	//付款成功
	public static final String COMBINE_ORDER_PAY_STATUS_FAILED = "2";	//付款失败

	// 积分进出账类型
	public static final String INTEGRAL_TALLY_MODE_PLUS = "1";	//增加
	public static final String INTEGRAL_TALLY_MODE_MINUS = "2";	//减少

	// 性别
	public static final String SET_TYPE_UNKNOW = "0";	//未知
	public static final String SET_TYPE_MAN = "1";	//男
	public static final String SET_TYPE_WOMAN = "2";	//女



	// -----------------------------------------------------------------------------------------------------------------
	// 商家
	// -----------------------------------------------------------------------------------------------------------------

	// 商家类型
	public static final String MCHT_TYPE_UNION = "1";	//SPOP
	public static final String MCHT_TYPE_POP = "2";		//POP
	public static String getMchtTypeStr(String info) {
		String str = "未知状态";
		if(MCHT_TYPE_UNION.equals(info)){
			str = "SPOP";
		}else if(MCHT_TYPE_POP.equals(info)){
			str = "POP";
		}
		return str;
	}

	// 商家合作状态
	public static final String MCHT_STATUS_ING = "0";	//入驻中
	public static final String MCHT_STATUS_NORMAL = "1";	//正常
	public static final String MCHT_STATUS_STOP = "2";	//业务暂停
	public static final String MCHT_STATUS_CLOSED = "3";	//关闭
	public static String getMchtStatusStr(String info) {
		String str = "未知状态";
		if(MCHT_STATUS_ING.equals(info)){
			str = "入驻中";
		}else if(MCHT_STATUS_NORMAL.equals(info)){
			str = "正常";
		}else if(MCHT_STATUS_STOP.equals(info)){
			str = "业务暂停";
		}else if(MCHT_STATUS_CLOSED.equals(info)){
			str = "关闭";
		}
		return str;
	}

	// 商家总资质审核状态
	public static final String MCHT_TOTAL_AUDIT_STATUS_WAIT_AUDIT = "0";	//待审
	public static final String MCHT_TOTAL_AUDIT_STATUS_AUDITING = "1";	//审核中
	public static final String MCHT_TOTAL_AUDIT_STATUS_PASS = "2";	//通过
	public static final String MCHT_TOTAL_AUDIT_STATUS_REJECT = "3";	//驳回
	public static String getMchtTotalAuditStatusStr(String info) {
		String str = "未知状态";
		if(MCHT_TOTAL_AUDIT_STATUS_WAIT_AUDIT.equals(info)){
			str = "待审";
		}else if(MCHT_TOTAL_AUDIT_STATUS_AUDITING.equals(info)){
			str = "审核中";
		}else if(MCHT_TOTAL_AUDIT_STATUS_PASS.equals(info)){
			str = "通过";
		}else if(MCHT_TOTAL_AUDIT_STATUS_REJECT.equals(info)){
			str = "驳回";
		}
		return str;
	}

	// 店铺类型
	public static final String SHOP_TYPE_1 = "1";	//品牌官方旗舰店
	public static final String SHOP_TYPE_2 = "2";	//品牌类目旗舰店
	public static final String SHOP_TYPE_3 = "3";	//品牌专卖店
	public static final String SHOP_TYPE_4 = "4";	//品类专营店
	public static final String SHOP_TYPE_5 = "5";	//卖场官方旗舰店

	// 商家账号状态
	public static final String MCHT_USER_STATUS_NORMAL = "0";	//正常
	public static final String MCHT_USER_STATUS_CLOSED = "1";	//禁用

	// 商家联系人类型
	public static final String MCHT_CONTACT_TYPE_ALL = "1";	//电商总负责人
	public static final String MCHT_CONTACT_TYPE_OPERATION = "2";	//运营对接人
	public static final String MCHT_CONTACT_TYPE_ORDER = "3";	//订单对接人
	public static final String MCHT_CONTACT_TYPE_ORDER_AFTER = "4";	//售后对接人
	public static final String MCHT_CONTACT_TYPE_FINANCE = "5";	//财务对接人
	public static final String MCHT_CONTACT_TYPE_CUSTOMER = "6";	//客服对接人

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
	public static String getMchtContractTypeStr(String info) {
		String str = "未知状态";
		if(MCHT_CONTRACT_TYPE_NEW.equals(info)){
			str = "新签";
		}else if(MCHT_CONTRACT_TYPE_RENEW.equals(info)){
			str = "续签";
		}
		return str;
	}

	// 续签状态
	public static final String MCHT_CONTRACT_RENEW_STATUS_WAIT = "0";	//未处理
	public static final String MCHT_CONTRACT_RENEW_STATUS_YES = "1";	//已生成续签
	public static final String MCHT_CONTRACT_RENEW_STATUS_NO = "2";	//不续签
	public static String getMchtContractRenewStatusStr(String info) {
		String str = "";
		if(MCHT_CONTRACT_RENEW_STATUS_WAIT.equals(info)){
			str = "未处理";
		}else if(MCHT_CONTRACT_RENEW_STATUS_YES.equals(info)){
			str = "已生成续签";
		}else if(MCHT_CONTRACT_RENEW_STATUS_NO.equals(info)){
			str = "不续签";
		}
		return str;
	}
	
	//合同续签进度
	public static final String MCHT_CONTRACT_RENEW_STATUS_ZEOR = "0";//未处理
	public static final String MCHT_CONTRACT_RENEW_STATUS_ONE = "1";//（商家）不再续签
	public static final String MCHT_CONTRACT_RENEW_STATUS_TWO = "2";	//待招商确认（商家申请续签）
	public static final String MCHT_CONTRACT_RENEW_STATUS_THREE = "3";	//待法务确认（招商确认续签）
	public static final String MCHT_CONTRACT_RENEW_STATUS_FOUR = "4";	//招商不予续签
	public static final String MCHT_CONTRACT_RENEW_STATUS_FIVE = "5";	//店铺暂停（运营操作，上份合同的续签状态改为3店铺暂停）
	public static final String MCHT_CONTRACT_RENEW_STATUS_SIX = "6";	//开放续签入口
	public static final String MCHT_CONTRACT_RENEW_STATUS_SEVEN = "7";	//法务确认续签(生成续签合同)
	public static String getMchtContractRenewProStatusStr(String info) {
		String str = "未知状态";
		if(MCHT_CONTRACT_RENEW_STATUS_ZEOR.equals(info)){
			str = "未处理";
		}else if(MCHT_CONTRACT_RENEW_STATUS_ONE.equals(info)){
			str = "不再续签";
		}else if(MCHT_CONTRACT_RENEW_STATUS_TWO.equals(info)){
			str = "续签申请";
		}
		else if(MCHT_CONTRACT_RENEW_STATUS_THREE.equals(info)){
			str = "确认续签";
		}
		else if(MCHT_CONTRACT_RENEW_STATUS_FOUR.equals(info)){
			str = "不予续签";
		}
		else if(MCHT_CONTRACT_RENEW_STATUS_FIVE.equals(info)){
			str = "确认不续签";
		}
		else if(MCHT_CONTRACT_RENEW_STATUS_SIX.equals(info)){
			str = "开放续签入口";
		}
		else if(MCHT_CONTRACT_RENEW_STATUS_SEVEN.equals(info)){
			str = "已生成合同";
		}
		return str;
	}
	
	// 审核状态
	public static final String MCHT_CONTRACT_AUDIT_STATUS_NONE = "1";	//未上传
	public static final String MCHT_CONTRACT_AUDIT_STATUS_WAIT = "2";	//待审核
	public static final String MCHT_CONTRACT_AUDIT_STATUS_PASS = "3";	//审核通过
	public static final String MCHT_CONTRACT_AUDIT_STATUS_REJECT = "4";	//审核驳回
	public static String getMchtContractAuditStatusStr(String info) {
		String str = "未知状态";
		if(MCHT_CONTRACT_AUDIT_STATUS_NONE.equals(info)){
			str = "未上传";
		}else if(MCHT_CONTRACT_AUDIT_STATUS_WAIT.equals(info)){
			str = "待审核";
		}else if(MCHT_CONTRACT_AUDIT_STATUS_PASS.equals(info)){
			str = "审核通过";
		}else if(MCHT_CONTRACT_AUDIT_STATUS_REJECT.equals(info)){
			str = "审核驳回";
		}
		return str;
	}

	// 归档状态
	public static final String MCHT_CONTRACT_ARCHIVE_STATUS_WAIT = "0";	//未处理
	public static final String MCHT_CONTRACT_ARCHIVE_STATUS_PASS = "1";	//通过已归档
	public static final String MCHT_CONTRACT_ARCHIVE_STATUS_REJECT = "2";	//不通过驳回
	public static final String MCHT_CONTRACT_ARCHIVE_STATUS_CLOSE = "4";	//不签约
	public static String getMchtContractArchiveStatusStr(String info) {
		String str = "未知状态";
		if(MCHT_CONTRACT_ARCHIVE_STATUS_WAIT.equals(info)){
			str = "未处理";
		}else if(MCHT_CONTRACT_ARCHIVE_STATUS_PASS.equals(info)){
			str = "已归档";
		}else if(MCHT_CONTRACT_ARCHIVE_STATUS_REJECT.equals(info)){
			str = "不通过驳回";
		}else if(MCHT_CONTRACT_ARCHIVE_STATUS_CLOSE.equals(info)){
			str = "不签约";
		}
		return str;
	}

	// 寄出状态
	public static final String MCHT_CONTRACT_SEND_STATUS_NO = "0";	//未寄
	public static final String MCHT_CONTRACT_SEND_STATUS_YES = "1";	//已寄
	public static String getMchtContractSendStatusStr(String info) {
		String str = "未知状态";
		if(MCHT_CONTRACT_SEND_STATUS_NO.equals(info)){
			str = "未寄出";
		}else if(MCHT_CONTRACT_SEND_STATUS_YES.equals(info)){
			str = "已寄出";
		}
		return str;
	}

	// 银行账号审核状态
	public static final String MCHT_BANK_STATUS_WAIT_AUDIT = "1";	//待审
	public static final String MCHT_BANK__STATUS_PASS = "2";	//通过
	public static final String MCHT_BANK_STATUS_REJECT = "3";	//驳回

	// 关店申请状态
	public static final String MCHT_CLOSE_APPLY_STATUS_WAIT = "1";	//待审核
	public static final String MCHT_CLOSE_APPLY_STATUS_AUDITED = "2";	//已审核

	// 关店申请人类型
	public static final String MCHT_CLOSE_APPLY_REQUEST_TYPE_LAW = "1";	// 法务
	public static final String MCHT_CLOSE_APPLY_REQUEST_TYPE_OPERATE = "2";	// 运营
	public static final String MCHT_CLOSE_APPLY_REQUEST_TYPE_MERCHANTS = "3";	// 招商
	public static String getMchtCloseApplyRequestTypeStr(String info) {
		String str = "未知";
		if(MCHT_CLOSE_APPLY_REQUEST_TYPE_LAW.equals(info)){
			str = "法务";
		}else if(MCHT_CLOSE_APPLY_REQUEST_TYPE_OPERATE.equals(info)){
			str = "运营";
		}else if(MCHT_CLOSE_APPLY_REQUEST_TYPE_MERCHANTS.equals(info)){
			str = "招商";
		}
		return str;
	}

	// 关店申请审核状态
	public static final String MCHT_CLOSE_APPLY_AUDIT_STATUS_AUDIT = "0";	//待审
	public static final String MCHT_CLOSE_APPLY_AUDIT_STATUS_PASS = "1";	//同意
	public static final String MCHT_CLOSE_APPLY_AUDIT_STATUS_REJECT = "2";	//不同意
	public static String getMchtCloseApplyAuditStatusStr(String info) {
		String str = "未知状态";
		if(MCHT_CLOSE_APPLY_AUDIT_STATUS_AUDIT.equals(info)){
			str = "待审";
		}else if(MCHT_CLOSE_APPLY_AUDIT_STATUS_PASS.equals(info)){
			str = "同意";
		}else if(MCHT_CLOSE_APPLY_AUDIT_STATUS_REJECT.equals(info)){
			str = "不同意";
		}
		return str;
	}
	public static List<Map<String, String>> getMchtCloseApplyAuditStatusList(){
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> infoA = new HashMap<String, String>();
		Map<String, String> infoB = new HashMap<String, String>();
		Map<String, String> infoC = new HashMap<String, String>();
		infoA.put("name", "待审");
		infoA.put("value", MCHT_CLOSE_APPLY_AUDIT_STATUS_AUDIT);
		list.add(infoA);
		infoB.put("name", "同意");
		infoB.put("value", MCHT_CLOSE_APPLY_AUDIT_STATUS_PASS);
		list.add(infoB);
		infoC.put("name", "不同意");
		infoC.put("value", MCHT_CLOSE_APPLY_AUDIT_STATUS_REJECT);
		list.add(infoC);
		return list;
	}

	// 关店执行状态
	public static final String MCHT_CLOSE_APPLY_EXE_STATUS_NO = "0";	//未执行
	public static final String MCHT_CLOSE_APPLY_EXE_STATUS_YES = "1";	//已执行
	public static String getMchtCloseApplyExeStatusStr(String info) {
		String str = "未知";
		if(MCHT_CLOSE_APPLY_EXE_STATUS_NO.equals(info)){
			str = "未执行";
		}else if(MCHT_CLOSE_APPLY_EXE_STATUS_YES.equals(info)){
			str = "已执行";
		}
		return str;
	}

	// 售后单 service_type
	public static final String CUSTOMER_SERVICE_ORDER_SERVICE_TYPE_A = "A";
	public static final String CUSTOMER_SERVICE_ORDER_SERVICE_TYPE_B = "B";
	public static final String CUSTOMER_SERVICE_ORDER_SERVICE_TYPE_C = "C";
	public static final String CUSTOMER_SERVICE_ORDER_SERVICE_TYPE_D = "D";
	// 站内信 title
	public static final String PLATFORM_MSG_TITLE_A = "关于用户申请退款通知";
	public static final String PLATFORM_MSG_TITLE_B = "关于用户申请售后退货通知";
	public static final String PLATFORM_MSG_TITLE_C = "关于用户申请售后换货通知";
	public static final String PLATFORM_MSG_TITLE_B21 = "关于售后退货(用户已寄件)通知";
	public static final String PLATFORM_MSG_TITLE_C31 = "关于售后换货(用户已寄件)通知";
	// 站内信 msg_type
	public static final String PLATFORM_MSG_MSG_TYPE_A1 = "A1";
	public static final String PLATFORM_MSG_MSG_TYPE_A2 = "A2";
	public static final String PLATFORM_MSG_MSG_TYPE_A3 = "A3";
	public static final String PLATFORM_MSG_MSG_TYPE_A4 = "A4";
	public static final String PLATFORM_MSG_MSG_TYPE_A5 = "A5";
	public static final String PLATFORM_MSG_MSG_TYPE_A6 = "A6";
	// 消息模版 msg_type
	public static final String MSG_TPL_MSG_TYPE_A1 = "A1";
	public static final String MSG_TPL_MSG_TYPE_A20 = "A20";
	public static final String MSG_TPL_MSG_TYPE_A21 = "A21";
	public static final String MSG_TPL_MSG_TYPE_A30 = "A30";
	public static final String MSG_TPL_MSG_TYPE_A31 = "A31";
	public static final String MSG_TPL_MSG_TYPE_A4 = "A4";
	public static final String MSG_TPL_MSG_TYPE_A5 = "A5";
	public static final String MSG_TPL_MSG_TYPE_A60 = "A60";
	public static final String MSG_TPL_MSG_TYPE_A61 = "A61";
	public static final String MSG_TPL_MSG_TYPE_A62 = "A62";
	public static final String MSG_TPL_MSG_TYPE_A63 = "A63";
	public static final String MSG_TPL_MSG_TYPE_A64 = "A64";
	
	//商品上下架操作人类型
	public static final String PLATFORM = "0";//平台
	public static final String BUSINESS = "1";//商家
}

