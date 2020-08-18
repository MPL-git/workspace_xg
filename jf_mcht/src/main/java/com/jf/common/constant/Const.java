package com.jf.common.constant;

import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 公共常量
 * @author hdl
 */
public class Const {


	// -----------------------------------------------------------------------------------------------------------------
	// 通用常量
	// -----------------------------------------------------------------------------------------------------------------

	// 标识状态
	public static final String FLAG_FALSE = "0";	//未标识
	public static final String FLAG_TRUE = "1";	//已标识

	// 信息完善状态
	public static final String INFO_PERFECT_STATUS_NOT_PERFECT = "0";	//未完善
	public static final String INFO_PERFECT_STATUS_WAIT_AUDIT = "1";	//待审核
	public static final String INFO_PERFECT_STATUS_WAIT = "2";	//待定
	public static final String INFO_PERFECT_STATUS_PASS = "3";	//通过
	public static final String INFO_PERFECT_STATUS_REJECT = "4";	//驳回
	public static String getInfoPerfectStatusStr(String status) {
		if(StrKit.isBlank(status)){
			return "未完善";
		}
		String str = "未知状态";
		if(status.equals(INFO_PERFECT_STATUS_NOT_PERFECT)){
			str = "未完善";
		}else if(status.equals(INFO_PERFECT_STATUS_WAIT_AUDIT)){
			//str = "待审核";
			str = "已填写";
		}else if(status.equals(INFO_PERFECT_STATUS_WAIT)){
			str = "待定";
		}else if(status.equals(INFO_PERFECT_STATUS_PASS)){
			str = "通过";
		}else if(status.equals(INFO_PERFECT_STATUS_REJECT)){
			str = "驳回";
		}
		return str;
	}

	// 审核状态
	public static final String AUDIT_STATUS_NONE = "0";	//未提审
	public static final String AUDIT_STATUS_WAIT = "1";	//待审核
	public static final String AUDIT_STATUS_PASS = "2";	//审核通过
	public static final String AUDIT_STATUS_REJECT = "3";	//审核驳回

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
	public static final String MCHT_STATUS_WAIT = "0";	//入驻中
	public static final String MCHT_STATUS_NORMAL = "1";	//正常
	public static final String MCHT_STATUS_STOP = "2";	//业务暂停
	public static final String MCHT_STATUS_CLOSED = "3";	//关闭

	// 商家总资质审核状态
	public static final String MCHT_TOTAL_AUDIT_STATUS_WAIT_AUDIT = "0";	//待审
	public static final String MCHT_TOTAL_AUDIT_STATUS_AUDITING = "1";	//审核中
	public static final String MCHT_TOTAL_AUDIT_STATUS_PASS = "2";	//通过
	public static final String MCHT_TOTAL_AUDIT_STATUS_REJECT = "3";	//驳回

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

	// 银行账号审核状态
	public static final String MCHT_BANK_STATUS_WAIT_AUDIT = "1";	//待审
	public static final String MCHT_BANK_STATUS_PASS = "2";	//通过
	public static final String MCHT_BANK_STATUS_REJECT = "3";	//驳回
	public static String getMchtBankStatusStr(String info) {
		String str = "未知状态";
		if(MCHT_BANK_STATUS_WAIT_AUDIT.equals(info)){
			str = "待审";
		}else if(MCHT_BANK_STATUS_PASS.equals(info)){
			str = "通过";
		}else if(MCHT_BANK_STATUS_REJECT.equals(info)){
			str = "驳回";
		}
		return str;
	}

	// -----------------------------------------------------------------------------------------------------------------
	// 特卖活动
	// -----------------------------------------------------------------------------------------------------------------

	// 活动专区来源
	public static final String ACTIVITY_SOURCE_HALL = "1";	//会场
	public static final String ACTIVITY_SOURCE_BRAND = "2";	//品牌特卖

	// 活动专区类型
	public static final String ACTIVITY_TYPE_BRAND = "1";	//品牌活动
	public static final String ACTIVITY_TYPE_DANPIN = "2";	//单品活动
	public static final String ACTIVITY_TYPE_BAOKUAN = "6";	//单品爆款
	public static final String ACTIVITY_TYPE_NEWGUY = "7";	//新用户专享
	public static final String ACTIVITY_TYPE_EXTENSION = "3";	//推广会场

	// 活动专区状态
	public static final String ACTIVITY_AREA_STATUS_DISABLE = "0";	//未启用
	public static final String ACTIVITY_AREA_STATUS_AVAILABLE = "1";	//启用

	// 活动预热天数
	public static final int ACTIVITY_PREHEAT_DAY = 1;

	// 活动状态
	public static final String ACTIVITY_STATUS_WAIT_COMMIT = "1";	//待提报
	public static final String ACTIVITY_STATUS_WAIT_AUDIT = "2";	//待审核
	public static final String ACTIVITY_STATUS_AUDITING = "3";	//审核中
	public static final String ACTIVITY_STATUS_AUDIT_REJECT = "4";	//驳回
	public static final String ACTIVITY_STATUS_STOP = "5";	//暂停
	public static final String ACTIVITY_STATUS_PASS = "6";	//通过

	// 活动商品审核状态
	public static final int ACTIVITY_PRODUCT_STATUS_WAIT = 1;	//未报名
	public static final int ACTIVITY_PRODUCT_STATUS_NORMAL = 2;	//已报名
	public static final int ACTIVITY_PRODUCT_STATUS_REJECT = 3;	//驳回

	// 特惠使用范围
	public static final String PREFERENTIAL_RANG_PLAT = "1";	//全平台
	public static final String PREFERENTIAL_RANG_HALL = "2";	//会场
	public static final String PREFERENTIAL_RANG_BRAND = "3";	//品牌特卖

	// 特惠费用归属方
	public static final String PREFERENTIAL_BELONG_PLAT = "1";	//平台
	public static final String PREFERENTIAL_BELONG_MCHT = "2";	//商家

	// 多买优惠类型
	public static final String FULL_DISCOUNT_TYPE_CUT = "1";	//满M件减N件
	public static final String FULL_DISCOUNT_TYPE_GIVE = "2";	//满M元任选N件
	public static final String FULL_DISCOUNT_TYPE_DISCOUNT = "3";	//满M件N折

	// 满赠类型
	public static final String FULL_GIVE_TYPE_FULL = "1";	//满额赠
	public static final String FULL_GIVE_TYPE_BUY = "2";	//买即赠


	// -----------------------------------------------------------------------------------------------------------------
	// 商家结算单
	// -----------------------------------------------------------------------------------------------------------------

	// 确认状态
	public static final String MCHT_SETTLE_ORDER_CONFIRM_STATUS_WAIT_MCHT = "1";	//待商家确认
	public static final String MCHT_SETTLE_ORDER_CONFIRM_STATUS_WAIT_PLAT = "2";	//待平台确认
	public static final String MCHT_SETTLE_ORDER_CONFIRM_STATUS_CONFIRMED = "3";	//已确认

	// 商家开票状态
	public static final String MCHT_SETTLE_ORDER_MCHT_INVOICE_STATUS_NO = "1";	//未寄
	public static final String MCHT_SETTLE_ORDER_MCHT_INVOICE_STATUS_YES = "2";	//已寄

	// 平台收票状态
	public static final String MCHT_SETTLE_ORDER_PLAT_COLLECT_STATUS_NO = "1";	//未收到
	public static final String MCHT_SETTLE_ORDER_PLAT_COLLECT_STATUS_YES = "2";	//已收到

	// 平台开票状态
	public static final String MCHT_SETTLE_ORDER_PLAT_INVOICE_STATUS_NO = "1";	//未开
	public static final String MCHT_SETTLE_ORDER_PLAT_INVOICE_STATUS_YES = "2";	//已开
	public static final String MCHT_SETTLE_ORDER_PLAT_INVOICE_STATUS_POST = "3";	//已寄出
	public static final String MCHT_SETTLE_ORDER_PLAT_INVOICE_STATUS_INVALID = "4";	//作废

	// 付款状态
	public static final String MCHT_SETTLE_ORDER_PAY_STATUS_QUEUE_NOT = "1";	//未排款
	public static final String MCHT_SETTLE_ORDER_PAY_STATUS_QUEUE_YES = "2";	//已排款
	public static final String MCHT_SETTLE_ORDER_PAY_STATUS_PAID = "3";			//已付款
	public static final String MCHT_SETTLE_ORDER_PAY_STATUS_CONFIRMED = "4";	//已确认


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

	//视频相关路径
	public static final String ffmpegpath = "D:/ffmpeg-20190826-0821bc4-win64-static/bin/ffmpeg.exe";		// ffmpeg工具安装位置
	public static final String mencoderpath = "D:/ffmpeg/bin/mencoder"; 	// mencoder工具安装的位置
	
	public static String videofolder = "interimDocuments"; 	// 需要被转换格式的视频目录
	public static String targetfolder = "video"; // 转码后视频保存的目录
	public static String imageRealPath = "finshVideoTemp"; // 截图的存放目录
	
	public static  String currentvideofolder= "interimDocuments"; // 需要被转换格式的视频子目录
	public static  String currentargetfolder= "video"; // 转码后视频保存的子目录

	//商品上下架操作人类型
	public static final String PLATFORM = "0";//平台
	public static final String BUSINESS = "1";//商家

	 static
	  {
	    InputStream stream = FileUtil.class.getResourceAsStream("/base_config.properties");
	    try
	    {
	      Properties properties = new Properties();
	      properties.load(stream);
	      String defaultPath = properties.getProperty("annex.filePath");
	      stream.close();
		      
	      SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMM");
		  String sYear = sFormat.format(new Date());
		  videofolder=defaultPath.concat(File.separator).concat(sYear).concat(File.separator).concat(videofolder);
		  targetfolder=defaultPath.concat(File.separator).concat(sYear).concat(File.separator).concat(targetfolder);
		  imageRealPath=defaultPath.concat(File.separator).concat(sYear).concat(File.separator).concat(imageRealPath);
		  currentvideofolder = sYear.concat(File.separator).concat(currentvideofolder);
		  currentargetfolder = sYear.concat(File.separator).concat(currentargetfolder);
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	    }
	  }
}