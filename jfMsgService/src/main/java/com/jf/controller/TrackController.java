package com.jf.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.jf.common.constant.Const;
import com.jf.common.ext.skd.TrackingioApi;
import com.jf.common.utils.DateUtil;
import com.jf.task.TrackingIOTask;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.utils.MD5Util;
import com.jf.common.utils.StringUtil;
import com.jf.entity.TrackData;
import com.jf.entity.TrackDataExample;
import com.jf.service.TrackDataService;

/**
  * @author  chenwf: 热云回调地址
  * @date 创建时间：2017年9月5日 下午6:05:52 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class TrackController {
	private static Logger logger = LoggerFactory.getLogger(TrackController.class);
	@Resource
	private TrackDataService trackDataService;

	@RequestMapping(value = "/trackCallback",method = RequestMethod.GET)
	@ResponseBody
	public void trackCallback(HttpServletRequest request){
//		logger.info("热云回调:start");
		try {
			//点击参数
			String spreadurl = request.getParameter("spreadurl");
			String spreadname = request.getParameter("spreadname");
			spreadname = new String(spreadname.getBytes("iso-8859-1"),"utf-8");
			String channel = request.getParameter("channel");
			channel = new String(channel.getBytes("iso-8859-1"),"utf-8");
			String clicktime = request.getParameter("clicktime");
			String ua = request.getParameter("ua");
			String aip = request.getParameter("aip");
			//激活参数
			String appkey = request.getParameter("appkey");
			String activetime = request.getParameter("activetime");
			String osversion = request.getParameter("osversion");
			String devicetype = request.getParameter("devicetype");
			String idfa = request.getParameter("idfa");
			String mac = request.getParameter("mac");
			String imei = request.getParameter("imei");
			//安全参数
			String skey = request.getParameter("skey");
			Date currentDate = new Date();
			if((StringUtil.isBlank(idfa) || "00000000-0000-0000-0000-000000000000".equals(idfa))
					&& (StringUtil.isBlank(imei) || "00000000-0000-0000-0000-000000000000".equals(imei))
					|| StringUtil.isBlank(spreadname) && StringUtil.isBlank(channel)){
				return;
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date clicktimeDate = null;
			Date activetimeDate = null;
			if(!StringUtil.isBlank(clicktime)){
				String clicktimeStr = simpleDateFormat.format(Long.valueOf(clicktime)); 
				clicktimeDate=simpleDateFormat.parse(clicktimeStr);
			}
			if(!StringUtil.isBlank(activetime)){
				String activetimeStr = simpleDateFormat.format(Long.valueOf(activetime)); 
				activetimeDate=simpleDateFormat.parse(activetimeStr); 
			}
			String signAppkey = TrackingioApi.APPKEY_IOS.toUpperCase(); //IOS appkey
			if((StringUtil.isBlank(idfa) || "00000000-0000-0000-0000-000000000000".equals(idfa))
					&& !StringUtil.isBlank(imei)) {
				signAppkey = TrackingioApi.APPKEY_ANDROID.toUpperCase(); //Android appkey
			}
			String sign = MD5Util.MD5Encode(
					String.format("%s_%s_%s", activetime,
							signAppkey,
							"AA91494B4F60F7F3E362FA7C"),
							"utf-8").toUpperCase();
			
			if(sign.equals(skey)){
				TrackData trackData = new TrackData();
				TrackDataExample trackDataExample = new TrackDataExample();
				if((StringUtil.isBlank(idfa) || "00000000-0000-0000-0000-000000000000".equals(idfa))
						&& !StringUtil.isBlank(imei)) {
					logger.info("Android热云回调==============imei=============>"+imei);
					trackDataExample.createCriteria().andImeiEqualTo(imei).andDelFlagEqualTo("0"); //Android
				}else {
					logger.info("IOS热云回调==================idfa=============>"+idfa);
					trackDataExample.createCriteria().andIdfaEqualTo(idfa).andDelFlagEqualTo("0"); //IOS
				}
				trackDataExample.setOrderByClause("id desc");
				List<TrackData> trackDatas = trackDataService.selectByExample(trackDataExample);
				if(CollectionUtils.isNotEmpty(trackDatas)){
					trackData = trackDatas.get(0);
					trackData.setSpreadname(spreadname);
					trackData.setChannel(channel);
					trackData.setUpdateDate(currentDate);
					trackDataService.updateByPrimaryKeySelective(trackData);
				}else{
					trackData.setType("1");
					trackData.setSpreadurl(spreadurl);
					trackData.setSpreadname(spreadname);
					trackData.setChannel(channel);
					trackData.setFirstspreadname(spreadname);
					trackData.setFirstchannel(channel);
					trackData.setClicktime(clicktimeDate);
					trackData.setUa(ua);
					trackData.setUip(aip);
					trackData.setActivetime(activetimeDate);
					trackData.setDevicetype(devicetype);
					trackData.setOsversion(osversion);
					trackData.setIdfa(idfa);
					trackData.setMac(mac);
					trackData.setImei(imei);
					trackData.setCreateDate(currentDate);
					trackData.setDelFlag("0");
					trackDataService.insertSelective(trackData);
				}
			}else{
				logger.info("热云验证失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("热云回调:faile");
		}
//		logger.info("热云回调:end");
		
	}


}
