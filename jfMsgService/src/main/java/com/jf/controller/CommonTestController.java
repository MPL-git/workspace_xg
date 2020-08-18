package com.jf.controller;

import com.jf.common.base.ResponseMsg;
import com.jf.common.ext.core.BaseController;
import com.jf.common.utils.SmsUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.MchtStatisticalInfo;
import com.jf.entity.Sms;
import com.jf.service.SmsService;
import com.jf.task.CombineOrderExtendTask;
import com.jf.task.MchtStatisticalInfoTask;
import com.jf.task.MchtTask;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @ClassName CommonTestController
 * @Description TODO(公共测试入口)
 * @author pengl
 * @date 2018年9月28日 下午2:58:28
 */
@Controller
public class CommonTestController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(CommonTestController.class);

	@Autowired
	private SmsService smsService;
	
	@Autowired
	private MchtTask mchtTask;
	@Autowired
	private CombineOrderExtendTask combineOrderExtendTask;

	@Autowired
	private MchtStatisticalInfoTask mchtStatisticalInfoTask;
	
	/**
	 * 
	 * @Title sendSmsXw   
	 * @Description TODO(玄武科技短息发送——测试入口)   
	 * @author pengl
	 * @date 2018年9月28日 下午3:27:18
	 */
	@ResponseBody
    @RequestMapping("/commonTest/sendSmsXw")
	public ResponseMsg sendSmsXw(HttpServletRequest request) {
		String mobile = request.getParameter("mobile");
    	if(StringUtil.isEmpty(mobile)){
    		return new ResponseMsg(ResponseMsg.ERROR, "请输入手机号码！多个手机号码，使用逗号隔开。例如：12345678998,98765432112");
    	}
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String[] mobiles = mobile.split(",");
    	for(String mobileStr : mobiles) {
    		Sms sms = new Sms();
    		sms.setMobile(mobileStr);
    		sms.setContent("玄武科技短息发送，测试时间："+sdf.format(date));
    		sms.setSmsPlatform("3");
    		sms = SmsUtil.sendSms(sms);
    		sms.setSendDate(date);
			sms.setCreateDate(date);
			sms.setSendCount(1);
			sms.setDelFlag("0");
			smsService.insertSelective(sms);
    	}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	
	/**
	 * 
	 */
	@ResponseBody
    @RequestMapping("/mcht/mchtBlPicWatermark")
	public ResponseMsg mchtBlPicWatermark() {
		mchtTask.waterMarkBlPic();
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}



	/**
	 * 手动上报热云数据
	 */
	@ResponseBody
	@RequestMapping("/combineOrderExtend/commitPayInfoToThackingio")
	public String commitPayInfoToThackingio() {
		logger.info("用户付款信息手动上报热云");
		combineOrderExtendTask.commitPayInfoToThackingio();
		return json();
	}

	/**
	 * 手动更新商家统计信息
	 */
	@ResponseBody
	@RequestMapping("/mchtInfo/mchtStatisticalInfo")
	public String mchtStatisticalInfo() {
		logger.info("手动更新商家统计信息begin");
		mchtStatisticalInfoTask.mchtStatisticalInfo();
		logger.info("手动更新商家统计信息end");
		return json();
	}

	
	public static void main(String[] args) {
		Position position = Positions.BOTTOM_RIGHT;
		try {
			System.out.println("aa");
//			Thumbnails.of("D://home/jfweb/tomcat/files/201809/common/1537275164235_5189.jpg").scale(1f).watermark(position, ImageIO.read(new File("D://home/jfweb/tomcat/files/watermark/mcht_bl_pic_WK.png")), 1).toFile("F:/111_warter.jpg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
