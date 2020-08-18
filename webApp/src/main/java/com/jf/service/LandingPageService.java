package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.AppVersionMapper;
import com.jf.dao.LandingPageH5InfoMapper;
import com.jf.dao.LandingPageMapper;
import com.jf.dao.LandingPagePicMapper;
import com.jf.entity.*;
import com.jf.vo.request.GetLandingPageRequest;
import com.jf.vo.request.LandingPageRequest;
import com.jf.vo.response.LandingPageResponse;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Pengl
 * @create 2020-06-28 上午 9:59
 */
@Service
@Transactional
public class LandingPageService extends BaseService<LandingPage, LandingPageExample> {

	@Autowired
	private LandingPageMapper landingPageMapper;

	@Autowired
	private LandingPagePicMapper landingPagePicMapper;

	@Autowired
	private LandingPageH5InfoMapper landingPageH5InfoMapper;

	@Autowired
	private SysStatusService sysStatusService;

	@Autowired
	private AppVersionMapper appVersionMapper;

	@Autowired
	private void setLandingPageMapper(LandingPageMapper landingPageMapper) {
		this.setDao(landingPageMapper);
		this.landingPageMapper = landingPageMapper;
	}

	public LandingPageResponse getLandingPage(GetLandingPageRequest getLandingPageRequest) {
		LandingPageResponse landingPageResponse = new LandingPageResponse();
		LandingPage landingPage = landingPageMapper.selectByPrimaryKey(getLandingPageRequest.getId());
		if(landingPage != null) {
			LandingPagePicExample landingPagePicExample = new LandingPagePicExample();
			landingPagePicExample.createCriteria().andDelFlagEqualTo("0").andLandingPageIdEqualTo(getLandingPageRequest.getId());
			List<LandingPagePic> landingPagePics = landingPagePicMapper.selectByExample(landingPagePicExample);
			List<String> landingPagePicList = new ArrayList<>();
			for(LandingPagePic landingPagePic : landingPagePics) {
				landingPagePicList.add(StringUtil.getPic(landingPagePic.getPic(), ""));
			}
			landingPageResponse.setEditorRecommend(landingPage.getEditorRecommend());
			landingPageResponse.setApplicationInformation(landingPage.getApplicationInformation());
			landingPageResponse.setLandingPagePicList(landingPagePicList);
		}
		return landingPageResponse;
	}

	public Map<String, Object> saveLandingPageH5Info(HttpServletRequest request, LandingPageRequest landingPageRequest) {
		Map<String, Object> map = new HashMap<>();
		String packageUrl = "http://xgbuy.cc/app/xgbuyDUANXIN.apk";
		LandingPageH5Info landingPageH5Info = new LandingPageH5Info();
		landingPageH5Info.setType(landingPageRequest.getType());
		landingPageH5Info.setIp(StringUtil.getIpAddr(request));
		landingPageH5Info.setReqModel(landingPageRequest.getReqModel());
		landingPageH5Info.setSystemVersion(landingPageRequest.getSystemVersion());
		landingPageH5Info.setAndroidChnl(landingPageRequest.getAndroidChnl());
		landingPageH5Info.setIosActivityGroupId(landingPageRequest.getIosActivityGroupId());
		landingPageH5Info.setIosActivityName(landingPageRequest.getIosActivityName());
		landingPageH5Info.setCreateDate(DateUtil.getDate());
		landingPageH5Info.setDelFlag("0");
		landingPageH5InfoMapper.insertSelective(landingPageH5Info);
		if(!StringUtil.isEmpty(landingPageRequest.getAndroidChnl())) {
			SysStatusExample sysStatusExample = new SysStatusExample();
			sysStatusExample.createCriteria().andTableNameEqualTo("BU_MEMBER_INFO").andColNameEqualTo("SPR_CHNL")
					.andStatusDescEqualTo(landingPageRequest.getAndroidChnl());
			sysStatusExample.setOrderByClause(" status_order");
			sysStatusExample.setLimitStart(0);
			sysStatusExample.setLimitSize(1);
			List<SysStatus> sysStatusList = sysStatusService.selectByExample(sysStatusExample);
			if(CollectionUtils.isNotEmpty(sysStatusList) && !StringUtil.isEmpty(sysStatusList.get(0).getStatusValue())){
				AppVersionExample appVersionExample = new AppVersionExample();
				appVersionExample.createCriteria().andDelFlagEqualTo("0")
						.andAppTypeEqualTo("2").andSprChnlEqualTo(sysStatusList.get(0).getStatusValue())
						.andIsEffectEqualTo("1");
				appVersionExample.setOrderByClause(" id desc");
				appVersionExample.setLimitStart(0);
				appVersionExample.setLimitSize(1);
				List<AppVersion> appVersionList = appVersionMapper.selectByExample(appVersionExample);
				if(CollectionUtils.isNotEmpty(appVersionList) && !StringUtil.isEmpty(appVersionList.get(0).getPackageUrl())) {
					packageUrl = appVersionList.get(0).getPackageUrl();
				}
			}
		}
		map.put("packageUrl", packageUrl);
		return map;
	}


}
