package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.constant.SysConfig;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.WeChatSendUtil;
import com.jf.common.utils.WeixinUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeixinService {

	/**
	 * 生成小程序二维码图片地址
	 * page:微信小程序的页面路径，不能带参数 page/product/detail
	 * scene:限制32长度,一般用于存放参数，如：id=11&mid=333
	 */
	public String createAppletUnlimitQrcode(String page, String scene) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("scene", scene);
		paramMap.put("page", page);
		paramMap.put("width", 430);
		paramMap.put("auto_color", false);

		Map<String, String> lineColorMap = new HashMap<String, String>();
		lineColorMap.put("r", "0");
		lineColorMap.put("g", "0");
		lineColorMap.put("b", "0");
		paramMap.put("line_color", lineColorMap);
		paramMap.put("is_hyaline", false);

		String fileName = String.valueOf(new Date().getTime());
		String targetPath = FileUtil.getRandomFileName(fileName , 10, 0)+".jpg";
		String result = WeChatSendUtil.sendPost(Const.WXA_CODE_UNLIMIT + WeixinUtil.getXcxAccessToken(), paramMap, SysConfig.defaultPath.concat(targetPath));
		return result == null ? null : targetPath;
	}
	
}
