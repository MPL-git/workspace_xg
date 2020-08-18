package com.jf.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.jf.common.base.ResponseMsg;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.StringUtil;
import com.jf.entity.PlatformMsgCustom;
import com.jf.service.PlatformMsgService;

@Controller
@RequestMapping("platformMsg")
public class PlatformMsgController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(PlatformMsgController.class);

	@Resource
	private PlatformMsgService platformMsgService;

	@RequestMapping
	public String index(HttpServletRequest request,Model model) {
		List<HashMap<String, Object>> msgTypes = platformMsgService.getMsgTypesByMchtId(this.getSessionMchtInfo(request).getId());
		model.addAttribute("msgTypes", msgTypes);
		return page("platMsg/list");
	}

	@ResponseBody
	@RequestMapping("list")
	public String list(QueryObject queryObject) {
		String title = getPara("title");
		if(StrKit.notBlank(title)){
			queryObject.addQuery("title", title);
		}
		String status = getPara("status");
		if(StrKit.notBlank(status)){
			queryObject.addQuery("status", status);
		}
		String msgType = getPara("msgType");
		if(StrKit.notBlank(msgType)){
			queryObject.addQuery("msgType", msgType);
		}
		queryObject.addQuery("mchtId", getUserInfo().getMchtId());
		queryObject.addSort("create_date", QueryObject.SORT_DESC);

		Page<PlatformMsgCustom> page = platformMsgService.paginate(queryObject);

		Map<String, Object> data = new HashMap<>();
		data.put("page", page);
		return json(data);
	}

	@ResponseBody
	@RequestMapping("read")
	public String read(String ids) {
		Assert.notBlank(ids, "ID不能为空");
		JSONArray idArray = JSONArray.parseArray(ids);
		for (int index = 0; index < idArray.size(); index ++) {
			platformMsgService.read(idArray.getIntValue(index));
		}

		return json();
	}

	@ResponseBody
	@RequestMapping("delete")
	public String delete(String ids) {
		Assert.notBlank(ids, "ID不能为空");
		JSONArray idArray = JSONArray.parseArray(ids);
		for (int index = 0; index < idArray.size(); index ++) {
			platformMsgService.delete(idArray.getIntValue(index));
		}

		return json();
	}

	@RequestMapping("/checkFileExists")
	@ResponseBody
	public ResponseMsg checkFileExists(HttpServletRequest request) {
		String attachmentPath = request.getParameter("attachmentPath");
		if(!StringUtil.isEmpty(attachmentPath)){
			InputStream stream = PlatformMsgController.class.getResourceAsStream("/config.properties");
			String defaultPath=null;
			try {
				Properties properties = new Properties();
				properties.load(stream);
				defaultPath = properties.getProperty("annex.filePath");
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(defaultPath==null){
				return new ResponseMsg(ResponseMsg.ERROR, "文件目录不存在");
			}
			File file = new File(defaultPath+attachmentPath);
			//如果文件不存在
			if(!file.exists()){
				return new ResponseMsg(ResponseMsg.ERROR, "附件不存在或已被删除");
			}
		}else{
			return new ResponseMsg(ResponseMsg.ERROR, "附件不存在或已被删除",null);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	@RequestMapping("/downLoadAttachment")
	public void downLoadAttachment(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String filePath = request.getParameter("filePath");
		String fileName = request.getParameter("fileName");
		InputStream stream = PlatformMsgController.class.getResourceAsStream("/config.properties");
		String defaultPath=null;
		try {
			Properties properties = new Properties();
			properties.load(stream);
			defaultPath = properties.getProperty("annex.filePath");
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(defaultPath==null){
			return;
		}
		File file = new File(defaultPath+filePath);
		//如果文件不存在
		if(!file.exists()){
		    return;
		}
		//设置响应头，控制浏览器下载该文件
		String downloadFileName = "";
		String agent = request.getHeader("USER-AGENT");  
        if(agent != null && agent.toLowerCase().indexOf("firefox") > 0)
        {
            downloadFileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";    
        }
        else
        {
            downloadFileName =  java.net.URLEncoder.encode(fileName, "UTF-8");
        }
		response.setHeader("content-disposition", "attachment;filename=" + downloadFileName);
		//读取要下载的文件，保存到文件输入流
		FileInputStream in = new FileInputStream(defaultPath+filePath);
		//创建输出流
		OutputStream out = response.getOutputStream();
		//缓存区
		byte buffer[] = new byte[1024];
		int len = 0;
		//循环将输入流中的内容读取到缓冲区中
		while((len = in.read(buffer)) > 0){
		    out.write(buffer, 0, len);
		}
		//关闭
		in.close();
		out.flush();
		out.close(); 
	}
}
