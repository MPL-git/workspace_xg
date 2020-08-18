package com.jf.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jf.common.constant.Const;
import com.jf.common.utils.Config;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.WeChatSendUtil;
import com.jf.common.utils.WeixinUtil;

@Service
public class WeixinService {

	/**
	 * 生成小程序二维码图片地址
	 * page:微信小程序的页面路径 page/product/detail
	 * scene:限制128长度，如：id=11&mid=333
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
		WeChatSendUtil.sendPost(Const.WXA_CODE_UNLIMIT + WeixinUtil.getXcxAccessToken(), paramMap, Config.getProperty("annex.filePath").concat(targetPath));
		return targetPath;
	}
	
}
