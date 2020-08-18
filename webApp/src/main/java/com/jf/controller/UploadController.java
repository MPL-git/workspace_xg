package com.jf.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.FileUtil;

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
            int delLength = pic.indexOf(",") + 1;  
            pic = pic.substring(delLength, pic.length() - delLength);  
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
	
	/**
	 * 
	 * 方法描述 ：商家上传入住申请资料文件图片接口
	 * @author  chenwf: 
	 * @date 创建时间：2018年4月23日 上午10:18:22 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/mchtUploadPic")
	@ResponseBody
	public ResponseMsg mchtUploadPic(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"pic"};
			this.requiredStr(obj,request);
			
			String pic = reqDataJson.getString("pic");
            int delLength = pic.indexOf(",") + 1;  
            pic = pic.substring(delLength, pic.length() - delLength);  
			String fileName = reqDataJson.getString("fileName");
			//图片上传 获取上传url
		    String filePath = FileUtil.saveFile(FileUtil.BaseToInputStream(pic), fileName, 0, 0);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,FileUtil.getImageServiceUrl()+filePath);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	@RequestMapping("/common/fileUpload")
	@ResponseBody
	public ResponseMsg fileUpload(HttpServletRequest request) {
		String filePath = "";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获取图片
		Map<String, MultipartFile> fileUploadMap = multipartRequest.getFileMap();
		for (String key : fileUploadMap.keySet()) {
			try {
			MultipartFile file = fileUploadMap.get(key);
			filePath = FileUtil.saveFile(file.getInputStream(), file.getOriginalFilename(), 1, 0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,FileUtil.getImageServiceUrl()+filePath);
	}
	
}
