package com.jf.controller;

import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.entity.SysAppCustomVersion;
import com.jf.entity.SysParamCfg;
import com.jf.service.SysAppVersionService;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年6月22日 下午2:13:56 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class AppVersionController extends BaseController{
	
	@Resource
	private SysAppVersionService sysAppVersionService;
	
	@RequestMapping(value = "/api/n/getAppNewVersion")
	@ResponseBody
	public ResponseMsg getAppNewVersion(HttpServletRequest request){
		try {
			//2 安卓  目前该接口只有安卓需要
			String appType = "2";
			Map<String,String> paramsMap = new HashMap<String,String>();
			paramsMap.put("appType", appType);
			List<SysAppCustomVersion> customVersions = sysAppVersionService.getAppNewVersion(paramsMap);
			SysAppCustomVersion customVersion = new SysAppCustomVersion();
			if(CollectionUtils.isNotEmpty(customVersions)){
				customVersion = customVersions.get(0);
				String a = customVersion.getLaunchContent();
				String[] contents = a.split("\n");
				String content = "";
				for (String str : contents) {
					if(!StringUtil.isBlank(str)){
						content+=str+"<br>";
					}
				}
				if(!StringUtil.isBlank(content)){
					content = content.substring(0, content.length()-4);
					customVersion.setLaunchContent(content);
					customVersion.setPackageUrl(customVersion.getPackageUrl().replace("http://p.xgbuy.cc", FileUtil.getImageServiceUrl()));
				}
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,customVersion);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
	
	@RequestMapping(value = "/api/n/getAppNewVersion20170823")
	@ResponseBody
	public ResponseMsg getAppNewVersion20170823(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			String sprChnl = reqDataJson.getString("sprChnl");
			if(StringUtil.isBlank(sprChnl)){
				sprChnl = "1001";
			}else{
				sprChnl = DataDicUtil.getSprChnl(sprChnl);
				if(sprChnl.equals("9999")){
					sprChnl = "1001";
				}
			}
			//2 安卓  目前该接口只有安卓需要
			String appType = "2";
			if(sprChnl.equals("1002")){
				appType = "1";
			}
			Map<String,String> paramsMap = new HashMap<String,String>();
			paramsMap.put("appType", appType);
			paramsMap.put("sprChnl", sprChnl);
			List<SysAppCustomVersion> customVersions = sysAppVersionService.getAppNewVersion(paramsMap);
			SysAppCustomVersion customVersion = new SysAppCustomVersion();
			if(CollectionUtils.isNotEmpty(customVersions)){
				customVersion = customVersions.get(0);
				String launchContent = customVersion.getLaunchContent();
				String[] contents = launchContent.split("\n");
				String content = "";
				for (String str : contents) {
					if(!StringUtil.isBlank(str)){
						content+=str+"<br>";
					}
				}
				if(!StringUtil.isBlank(content)){
					content = content.substring(0, content.length()-4);
					customVersion.setLaunchContent(content);
					customVersion.setPackageUrl(customVersion.getPackageUrl().replace("http://p.xgbuy.cc", FileUtil.getImageServiceUrl()));
				}
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,customVersion);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
	
	@RequestMapping(value = "/api/n/getIosNewVersion")
	@ResponseBody
	public ResponseMsg getIosNewVersion(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Object[] obj = {"frequency"};
			this.requiredStr(obj,request);
			String appType = "1";//IOS
			boolean isHasNewVersion = false;
			String content = "";
			Integer version = null;
			Integer frequency =  reqDataJson.getInt("frequency");
			//查看是否展示版本升级
			List<SysParamCfg> sysParamCfgs = DataDicUtil.getSysParamCfg("APP_IOS_VERSION");
			if(CollectionUtils.isNotEmpty(sysParamCfgs) && frequency <= 3){
				if(!StringUtil.isBlank(sysParamCfgs.get(0).getParamValue())
						&& sysParamCfgs.get(0).getParamValue().equals("1")){
					isHasNewVersion = true;
				}
			}
			
			if(reqPRM.containsKey("version") && !StringUtil.isBlank(reqPRM.getString("version"))){
				version = reqPRM.getInt("version");
			}else{
				isHasNewVersion = false;
			}
			if(isHasNewVersion){
				Map<String,String> paramsMap = new HashMap<String,String>();
				paramsMap.put("appType", appType);//1
				List<SysAppCustomVersion> customVersions = sysAppVersionService.getAppNewVersion(paramsMap);
				SysAppCustomVersion customVersion = new SysAppCustomVersion();
				if(CollectionUtils.isNotEmpty(customVersions)){
					customVersion = customVersions.get(0);
					Integer targetVersion = customVersion.getTargetVersion();
					if(version > targetVersion){//IOS上传的版本号>目标版本 不进行提示
						isHasNewVersion = false;
					}else{
						String launchContent = customVersion.getLaunchContent();
						String[] contents = launchContent.split("\n");
						for (String str : contents) {
							if(!StringUtil.isBlank(str)){
								content+=str;
							}
						}
//						if(!StringUtil.isBlank(content)){
//							content = content.substring(0, content.length()-4);
//							customVersion.setLaunchContent(content);
//						}
					}
				}else{
					isHasNewVersion = false;
				}
			}
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("isHasNewVersion", isHasNewVersion);
			map.put("content", content);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
}
