package com.jf.controller;

import java.awt.image.BufferedImage;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
/*import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;*/







import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.FileUtil;

@Controller
@RequestMapping(value = "/service/common")
public class CommonController {
	
	@ResponseBody
	@RequestMapping(value = "/ajax_upload.shtml")
	public ResponseEntity<String> upload(HttpServletRequest request,@RequestParam MultipartFile file, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		int fileSize = (int) file.getSize();
		String filePath = null;
		int fileType=0;
		int mchtId=0;
		json.put("RESULT_CODE", 1);
		json.put("RESULT_MESSAGE", "上传失败");
		if(fileSize!=0) {
			json.put("RESULT_CODE", 0);
			json.put("RESULT_MESSAGE", "上传成功");
			if (!StringUtil.isEmpty(request.getParameter("fileType"))) {
				fileType=Integer.valueOf(request.getParameter("fileType"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtId"))) {
				mchtId=Integer.valueOf(request.getParameter("mchtId"));
			}
			filePath = FileUtil.saveFile(file.getInputStream(), file.getOriginalFilename(), fileType, mchtId);
			json.put("FILE_PATH", filePath);
		}	
		HttpHeaders headers = new HttpHeaders();
		MediaType mt = new MediaType("text", "html", Charset.forName("utf-8"));
		headers.setContentType(mt);
		String returnString = new String(json.toString());
		return new ResponseEntity<String>(returnString, headers, HttpStatus.OK);
	}
	
	@RequestMapping("/fileUpload.shtml")
	@ResponseBody
	public ResponseEntity<String> fileUpload(Model model, HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String filePath = null;
		int fileType=0;
		int mchtId=0;
		json.put("RESULT_CODE", 1);
		json.put("RESULT_MESSAGE", "上传失败");
		if (!StringUtil.isEmpty(request.getParameter("fileType"))) {
			fileType=Integer.valueOf(request.getParameter("fileType"));
		}
		if (!StringUtil.isEmpty(request.getParameter("mchtId"))) {
			mchtId=Integer.valueOf(request.getParameter("mchtId"));
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获取图片
		Map<String, MultipartFile> fileUploadMap = multipartRequest.getFileMap();
		for (String key : fileUploadMap.keySet()) {
			try {
			MultipartFile file = fileUploadMap.get(key);
			filePath = FileUtil.saveFile(file.getInputStream(), file.getOriginalFilename(), fileType, mchtId);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(!StringUtil.isEmpty(filePath)){
			json.put("RESULT_CODE", 0);
			json.put("RESULT_MESSAGE", "上传成功");
			json.put("FILE_PATH", filePath);
		}
		HttpHeaders headers = new HttpHeaders();
		MediaType mt = new MediaType("text", "html", Charset.forName("utf-8"));
		headers.setContentType(mt);
		String returnString = new String(json.toString());
		return new ResponseEntity<String>(returnString, headers, HttpStatus.OK);
	}
	
	
	//多图上传
	@RequestMapping("/uploadFiles.shtml")
	@ResponseBody
	public ResponseEntity<String> uploadFiles(Model model, HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String filePath = null;
		int fileType=0;
		int mchtId=0;
		json.put("RESULT_CODE", 1);
		json.put("RESULT_MESSAGE", "上传失败");
		if (!StringUtil.isEmpty(request.getParameter("fileType"))) {
			fileType=Integer.valueOf(request.getParameter("fileType"));
		}
		if (!StringUtil.isEmpty(request.getParameter("mchtId"))) {
			mchtId=Integer.valueOf(request.getParameter("mchtId"));
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获取图片
		String filePathsString = "";
		List<MultipartFile> files = multipartRequest.getFiles("topPicFile");
		for (int i = 0; i < files.size(); i++) {
			try {
			MultipartFile file = files.get(i);
			filePath = FileUtil.saveFile(file.getInputStream(), file.getOriginalFilename(), fileType, mchtId);
			
			//验证图片尺寸
			InputStream stream = CommonController.class.getResourceAsStream("/base_config.properties");
			String defaultPath=null;
			try {
				Properties properties = new Properties();
				properties.load(stream);
				defaultPath = properties.getProperty("annex.filePath");
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			File fileTest = new File(defaultPath+filePath);
			BufferedImage read = ImageIO.read(fileTest);
			int width = read.getWidth();
			if(width!=750){
				json.put("RESULT_CODE", 1);
				json.put("RESULT_MESSAGE", "存在图片宽度不为750px");
				HttpHeaders headers = new HttpHeaders();
				MediaType mt = new MediaType("text", "html", Charset.forName("utf-8"));
				headers.setContentType(mt);
				String returnString = new String(json.toString());
				return new ResponseEntity<String>(returnString, headers, HttpStatus.OK);
			}
				if(i==0){
					filePathsString += filePath ;
				}else{
					filePathsString += ","+filePath;
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(!StringUtil.isEmpty(filePath)){
			json.put("RESULT_CODE", 0);
			json.put("RESULT_MESSAGE", "上传成功");
			json.put("FILE_PATH", filePathsString);
		}
		HttpHeaders headers = new HttpHeaders();
		MediaType mt = new MediaType("text", "html", Charset.forName("utf-8"));
		headers.setContentType(mt);
		String returnString = new String(json.toString());
		return new ResponseEntity<String>(returnString, headers, HttpStatus.OK);
	}
}
