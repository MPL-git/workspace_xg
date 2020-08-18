/**
 * @ClassName TODO
 * @Description
 * @author chengh
 * @date
 */
package com.jf.common.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * @ClassName QRCodeUtil
 * @Description TODO
 * @author chengh
 * @date 2019年7月16日 上午10:41:44
 */
public class QRCodeUtil {
	 public static BitMatrix generateQRCodeStream(String content,HttpServletResponse response) {
	        //给相应添加头部信息，主要告诉浏览器返回的是图片流
	        response.setHeader("Cache-Control", "no-store");
	        // 不设置缓存
	        response.setHeader("Pragma", "no-cache");
	        response.setDateHeader("Expires", 0);
	        response.setContentType("image/png");
	        //设置图片的文字编码以及内边框
	        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
	        //编码
	        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
	        //边框距
	        hints.put(EncodeHintType.MARGIN, 0);
	        BitMatrix bitMatrix;
	        try {
	            //参数分别为：编码内容、编码类型、图片宽度、图片高度，设置参数
	            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 300, 300,hints);
	        }catch(WriterException  e) {
	            e.printStackTrace();
	            return null;
	        }
	        return bitMatrix;        
	    }
}
