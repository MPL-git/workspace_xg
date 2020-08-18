package com.jf.controller;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jdom2.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.HttpXmlUtils;
import com.jf.common.utils.JdomParseXmlUtils;
import com.jf.common.utils.RandCharsUtils;
import com.jf.common.utils.wechar.WXSignUtils;
import com.jf.controller.base.BaseController;
import com.jf.service.RefundOrderService;

import net.sf.json.JSONObject;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年6月21日 下午4:20:57 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class UploadController extends BaseController{
	
	@Resource
	private RefundOrderService refundOrderService;
	/**
	 * 
	 * 方法描述 ：图片上传服务器
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月21日 下午4:37:09 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/uploadPic")
	@ResponseBody
	public ResponseMsg uploadPic(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"pic"};
			this.requiredStr(obj,request);
			
			String pic = reqDataJson.getString("pic");
			String fileName = reqDataJson.getString("fileName");
			//图片上传 获取上传url
		    String filePath = FileUtil.saveFile(FileUtil.BaseToInputStream(pic), fileName, 1, 0);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,FileUtil.getImageServiceUrl()+filePath);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/api/n/testCa")
	@ResponseBody
	public ResponseMsg testCa(HttpServletRequest request){
		try {
			//获取xml信息
			  SortedMap<String, Object> params = new TreeMap<String, Object>();
			  String nonce_str = RandCharsUtils.getRandomString(32);
				params.put("mch_id", "1482047712");
				params.put("nonce_str", nonce_str);
				String sign = WXSignUtils.createSign("UTF-8", params);
				String xmlInfo = "<root>"
							+"<mch_id><![CDATA[1482047712]]></mch_id>"
							+"<nonce_str><![CDATA["+nonce_str+"]]></nonce_str>"
							+"<sign><![CDATA["+sign+"]]></sign>"
							+"</root>";
				
				String wxUrl = "https://apitest.mch.weixin.qq.com/sandboxnew/pay/getsignkey";
				
				String method = "POST";
				
				String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();
				
				List<Element> list = JdomParseXmlUtils.getWXPayResultElement(weixinPost);
				
				if (list != null && list.size() > 0) {
					for (Element element : list) {
						System.out.println(element.getName()+"--》》:"+element.getText());
					}
				}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
}
