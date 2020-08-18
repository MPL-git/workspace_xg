package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.LandingPageAndroidInfoMapper;
import com.jf.dao.LandingPageH5InfoMapper;
import com.jf.dao.LandingPageMapper;
import com.jf.dao.TrackDataMapper;
import com.jf.entity.*;
import com.jf.vo.request.LandingPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author Pengl
 * @create 2020-06-28 下午 2:49
 */
@Service
@Transactional
public class LandingPageService extends BaseService<LandingPage, LandingPageExample> {

	@Autowired
	private LandingPageMapper landingPageMapper;
	@Autowired
	private SysParamCfgService sysParamCfgService;
	@Autowired
	private TrackDataMapper trackDataMapper;
	@Autowired
	private LandingPageAndroidInfoMapper landingPageAndroidInfoMapper;
	@Autowired
	private LandingPageH5InfoMapper landingPageH5InfoMapper;

	@Autowired
	private void setLandingPageMapper(LandingPageMapper landingPageMapper) {
		this.setDao(landingPageMapper);
		this.landingPageMapper = landingPageMapper;
	}

	public void saveLandingPage(HttpServletRequest request, LandingPageRequest landingPageRequest) {
		Date date = DateUtil.getDate();
		String ip = StringUtil.getIpAddr(request);
		String type = landingPageRequest.getType();
		String reqModel = landingPageRequest.getReqModel();
		String reqImei = landingPageRequest.getReqImei();
		String systemVersion = landingPageRequest.getSystemVersion();
		LandingPageH5InfoExample landingPageH5InfoExample = new LandingPageH5InfoExample();
		landingPageH5InfoExample.createCriteria().andDelFlagEqualTo("0")
				.andIpEqualTo(ip).andReqModelEqualTo(reqModel).andSystemVersionEqualTo(systemVersion)
				.andCreateDateBetween(DateUtil.getDateHourTime(date, -24), date);
		landingPageH5InfoExample.setOrderByClause(" id desc");
		landingPageH5InfoExample.setLimitStart(0);
		landingPageH5InfoExample.setLimitSize(1);
		List<LandingPageH5Info> landingPageH5Infos = landingPageH5InfoMapper.selectByExample(landingPageH5InfoExample);
		if(CollectionUtil.isNotEmpty(landingPageH5Infos)) {
			LandingPageH5Info landingPageH5Info = landingPageH5Infos.get(0);
			if ("1".equals(type)) { // 1.Android
				LandingPageAndroidInfoExample landingPageAndroidInfoExample = new LandingPageAndroidInfoExample();
				landingPageAndroidInfoExample.createCriteria().andDelFlagEqualTo("0").andReqImeiEqualTo(reqImei);
				List<LandingPageAndroidInfo> landingPageAndroidInfos = landingPageAndroidInfoMapper.selectByExample(landingPageAndroidInfoExample);
				if (CollectionUtil.isNotEmpty(landingPageAndroidInfos)) {
					LandingPageAndroidInfo landingPageAndroidInfo = landingPageAndroidInfos.get(0);
					Date newAndroidChnlDate = landingPageAndroidInfo.getNewAndroidChnlDate();
					String landingPageTime = "0";
					SysParamCfg sysParamCfg = sysParamCfgService.findByCode("LANDING_PAGE_TIME");
					if (sysParamCfg != null) {
						landingPageTime = sysParamCfg.getParamValue();
					}
					if (newAndroidChnlDate == null || date.compareTo(DateUtil.getDateHourTime(newAndroidChnlDate, Integer.parseInt(landingPageTime))) >= 0) {
						LandingPageAndroidInfo landingPageAndroid = new LandingPageAndroidInfo();
						landingPageAndroid.setId(landingPageAndroidInfo.getId());
						landingPageAndroid.setNewAndroidChnl(landingPageH5Info.getAndroidChnl());
						landingPageAndroid.setNewAndroidChnlDate(date);
						landingPageAndroid.setUpdateDate(date);
						landingPageAndroidInfoMapper.updateByPrimaryKeySelective(landingPageAndroid);
					}
				} else {
					LandingPageAndroidInfo landingPageAndroid = new LandingPageAndroidInfo();
					landingPageAndroid.setIp(ip);
					landingPageAndroid.setReqModel(reqModel);
					landingPageAndroid.setReqImei(reqImei);
					landingPageAndroid.setSystemVersion(systemVersion);
					landingPageAndroid.setFirstAndroidChnl(landingPageH5Info.getAndroidChnl());
					landingPageAndroid.setCreateDate(date);
					landingPageAndroid.setDelFlag("0");
					landingPageAndroidInfoMapper.insertSelective(landingPageAndroid);
				}
			} else if ("2".equals(type)) { // IOS
				TrackDataExample trackDataExample = new TrackDataExample();
				trackDataExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("2")
						.andIdfaEqualTo(reqImei);
				List<TrackData> trackDataList = trackDataMapper.selectByExample(trackDataExample);
				if(CollectionUtil.isNotEmpty(trackDataList)) {
					TrackData trackData = trackDataList.get(0);
					Date clicktime = trackData.getUpdateDate();
					String landingPageTime = "0";
					SysParamCfg sysParamCfg = sysParamCfgService.findByCode("LANDING_PAGE_TIME");
					if (sysParamCfg != null) {
						landingPageTime = sysParamCfg.getParamValue();
					}
					if (clicktime == null || date.compareTo(DateUtil.getDateHourTime(clicktime, Integer.parseInt(landingPageTime))) >= 0) {
						TrackData td = new TrackData();
						td.setId(trackData.getId());
						td.setSpreadname(landingPageH5Info.getIosActivityName());
						td.setChannel("落地页");
						td.setClicktime(date);
						td.setUpdateDate(date);
						trackDataMapper.updateByPrimaryKeySelective(td);
					}
				}else {
					TrackData td = new TrackData();
					td.setType("2");
					td.setSpreadname(landingPageH5Info.getIosActivityName());
					td.setChannel("落地页");
					td.setFirstspreadname(landingPageH5Info.getIosActivityName());
					td.setFirstchannel("落地页");
					td.setClicktime(date);
					td.setUip(ip);
					td.setActivetime(date);
					td.setOsversion(systemVersion);
					td.setIdfa(reqImei);
					td.setCreateDate(date);
					td.setDelFlag("0");
					trackDataMapper.insertSelective(td);
				}
			}
		}
	}



}
