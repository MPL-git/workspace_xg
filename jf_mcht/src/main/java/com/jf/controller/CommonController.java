package com.jf.controller;

import com.alibaba.fastjson.JSONObject;
import com.jf.common.base.ResponseMsg;
import com.jf.common.ext.exception.BizException;
import com.jf.common.utils.*;
import com.jf.vo.FileSimpleInfo;
import com.jf.vo.video.VideoSplitInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Controller
public class CommonController extends BaseController {

	@RequestMapping("/common/fileUpload")
	@ResponseBody
	public ResponseMsg fileUpload(Model model, HttpServletRequest request) {

		int fileType = 0;
		int mchtId = 0;
		if (!StringUtil.isEmpty(request.getParameter("fileType"))) {
			fileType = Integer.valueOf(request.getParameter("fileType"));
            if (fileType == 2 || fileType == 10 || fileType == 11 || fileType == 12) {
                mchtId = this.getSessionMchtInfo(request).getId();
            }
		}
		String splitVideo = request.getParameter("splitVideo"); //小视频相关
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获取图片
		Map<String, MultipartFile> fileUploadMap = multipartRequest.getFileMap();
		String filePath = null;
		for (String key : fileUploadMap.keySet()) {
			try {
				MultipartFile file = fileUploadMap.get(key);

				filePath = FileUtil.saveFile(file.getInputStream(), file.getOriginalFilename(), fileType, mchtId);
				if(fileType == 11){
					InputStream stream = CommonController.class.getResourceAsStream("/config.properties");
					String defaultPath=null;
					try {
						Properties properties = new Properties();
						properties.load(stream);
						defaultPath = properties.getProperty("annex.filePath");
						stream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					String videoCoverPath = defaultPath+filePath.subSequence(0, filePath.lastIndexOf("."))+".jpg";//视频封面绝对路径
					VideoProcessing.grabberVideoFramer(defaultPath+filePath, videoCoverPath);
					File videoCoverFile = new File(videoCoverPath);
					int imgWidth = FileUtil.getImgWidth(videoCoverFile);
					int imgHeight = FileUtil.getImgHeight(videoCoverFile);
					/*if(imgWidth<imgHeight){
						 if (videoCoverFile.exists() && videoCoverFile.isFile()) {
							 videoCoverFile.delete();
						 }
						 File videoFile = new File(defaultPath+filePath);
						 if(videoFile.exists() && videoFile.isFile()){
							 videoFile.delete();
						 }
						return new ResponseMsg(ResponseMsg.ERROR, "视频尺寸错误，视频高度不得大于宽度！", "");
					}else{*/
						String suffix = filePath.substring(filePath.lastIndexOf("."));
						if(!suffix.toUpperCase().equals(".MP4")){//转换为MP4格式
							ConverVideoUtils.processVideoFormat(defaultPath+filePath);
							filePath = filePath.substring(0, filePath.lastIndexOf("."))+".mp4";
						}
//					}
				} else if (fileType == 12 && !StringUtil.isBlank(splitVideo)) { //小视频取帧
					return ResponseMsg.success(handleVideo(filePath));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, filePath);
	}

    private VideoSplitInfo handleVideo(String filePath) {
        if(filePath==null) throw new BizException("上传失败");

        boolean needTransfer = needTransfer(filePath);
        String outputPath = filePath;
        if (needTransfer) {
            outputPath = filePath.substring(0, filePath.lastIndexOf(".")) + "_t.mp4";
        }
        VideoSplitInfo videoSplitInfo = VideoHelper.splitVideo(FileUtil.defaultPath, filePath, outputPath);
        if (needTransfer) {
            VideoHelper.convertToMP4(FileUtil.defaultPath + filePath, FileUtil.defaultPath + outputPath);
            videoSplitInfo.setVideoUrl(outputPath);
        }
        videoSplitInfo.setFullVideoUrl(FileUtil.getVideoServiceUrl() + videoSplitInfo.getVideoUrl());

		if (StringUtils.hasText(videoSplitInfo.getFirstFrame())) {
			String firstFrameAbsolutePath = FileUtil.defaultPath + videoSplitInfo.getFirstFrame();
			FileSimpleInfo fileSimpleInfo = FileUtil.getImgWidthAndHeight(firstFrameAbsolutePath);
			if (fileSimpleInfo != null) {
				videoSplitInfo.setVideoWidth(fileSimpleInfo.getWidth());
				videoSplitInfo.setVideoHeight(fileSimpleInfo.getHeight());
			}
		}
        return videoSplitInfo;
    }

    private boolean needTransfer(String filePath) {
        String suffix = filePath.substring(filePath.lastIndexOf("."));
        if (!suffix.toUpperCase().equals(".MP4")) { //转换为MP4格式(27为h263编码)
            return true;
        }
        int videoCodeC = VideoHelper.getVideoCodeC(FileUtil.defaultPath + filePath);
        return 27 != videoCodeC;
    }

	@RequestMapping("/common/batchFileUpload")
	@ResponseBody
	public ResponseMsg batchFileUpload(Model model, HttpServletRequest request) {

		int fileType = 0;
		int mchtId = 0;
		if (!StringUtil.isEmpty(request.getParameter("fileType"))) {
			fileType = Integer.valueOf(request.getParameter("fileType"));
			if (fileType == 2) {
				mchtId = this.getSessionMchtInfo(request).getId();
			}
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获取图片
		Map<String, MultipartFile> fileUploadMap = multipartRequest.getFileMap();
		String filePath = null;
		List<JSONObject> uploadResult = new ArrayList<JSONObject>();
		for (String key : fileUploadMap.keySet()) {
			JSONObject resultObject = new JSONObject();
			try {
				MultipartFile file = fileUploadMap.get(key);
				filePath = FileUtil.saveFile(file.getInputStream(), file.getOriginalFilename(), fileType, mchtId);
				resultObject.put("isSuccess", "1");
				resultObject.put("filePath", filePath);
				resultObject.put("fileKey", key);
			} catch (IOException e) {
				e.printStackTrace();
				resultObject.put("isSuccess", "0");
				resultObject.put("filePath", filePath);
				resultObject.put("fileKey", key);
			}
			uploadResult.add(resultObject);
		}

		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, uploadResult);
	}

	@RequestMapping("/common/fileUpload4Base64")
	@ResponseBody
	public ResponseMsg fileUpload4Base64(Model model, HttpServletRequest request) {

		int fileType = 0;
		int mchtId = 0;
		if (!StringUtil.isEmpty(request.getParameter("fileType"))) {
			fileType = Integer.valueOf(request.getParameter("fileType"));
			if (fileType == 2) {
				mchtId = this.getSessionMchtInfo(request).getId();
			}
		}
		String imageData = request.getParameter("imageData");// data:image/jpg;base64,
		String imageName = request.getParameter("imageName");// data:image/jpg;base64,

		if (StringUtil.isEmpty(imageName)) {
			if (imageData.indexOf("image/jpg") > 0 || imageData.indexOf("image/jpeg") > 0) {
				imageName = "xxxx.jpg";
			}
			if (imageData.indexOf("image/png") > 0) {
				imageName = "xxxx.png";
			}
			if (imageData.indexOf("image/gif") > 0) {
				imageName = "xxxx.gif";
			}
			if (imageData.indexOf("image/bmp") > 0) {
				imageName = "xxxx.bmp";
			}
		}

		int delLength = imageData.indexOf(",") + 1;
		String str = imageData.substring(delLength, imageData.length() - delLength);
		String filePath = FileUtil.saveBase64File(str, imageName, fileType, mchtId);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, filePath);
	}

	@RequestMapping("/common/checkViolateWord")
	@ResponseBody
	public ResponseMsg checkViolateWord(HttpServletRequest request) {

		String word = request.getParameter("word");
		if (!StringUtil.isEmpty(word)) {
			Set<String> violateWordSet = StringUtil.containsViolateWord(word);
			if (!violateWordSet.isEmpty()) {
				String msg="对不起，您输入的词包含广告违禁词：";
				for (String violateWord : violateWordSet) {
					msg=msg+violateWord+",";
				}
				return new ResponseMsg(ResponseMsg.ERROR, msg.substring(0,msg.length()-1));
			} else {
				return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
			}
		} else {
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		}
	}

	// public boolean generateImage(String imgStr,String imageName)
	// {
	// if (imgStr == null) //图像数据为空
	// return false;
	// BASE64Decoder decoder = new BASE64Decoder();
	// try
	// {
	// //Base64解码
	// byte[] b = decoder.decodeBuffer(imgStr);
	// for(int i=0;i<b.length;++i)
	// {
	// if(b[i]<0)
	// {//调整异常数据
	// b[i]+=256;
	// }
	// }
	// //生成jpeg图片
	// InputStream in =new ByteArrayInputStream(b);
	// String filePath=FileUtil.saveFile(in, imageName);
	// in.close();
	// return true;
	// }
	// catch (Exception e)
	// {
	// return false;
	// }
	// }


}
