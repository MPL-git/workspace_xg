package com.jf.service;

import java.util.Date;

import com.jf.common.base.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CutLinkClickLogMapper;
import com.jf.entity.CutLinkClickLog;
import com.jf.entity.CutLinkClickLogExample;

import net.sf.json.JSONObject;

@Service
@Transactional
public class CutLinkClickLogService extends BaseService<CutLinkClickLog, CutLinkClickLogExample> {
	private static Logger logger = LoggerFactory.getLogger(CutLinkClickLogService.class);
	@Autowired
	private CutLinkClickLogMapper cutLinkClickLogMapper;
	@Autowired
	private void setCutLinkClickLogMapper(CutLinkClickLogMapper cutLinkClickLogMapper) {
		this.setDao(cutLinkClickLogMapper);
		this.cutLinkClickLogMapper = cutLinkClickLogMapper;
	}
	
	public void addCutLinkClickLog(Integer memberId, JSONObject reqDataJson) {
		try {
			String shareLinkType = reqDataJson.getString("shareLinkType");
			String shareUrl = reqDataJson.getString("shareUrl");
			String linkValue = reqDataJson.getString("linkValue");
			CutLinkClickLog clickLog = new CutLinkClickLog();
			clickLog.setMemberId(memberId);
			clickLog.setShareLinkType(shareLinkType);
			clickLog.setShareUrl(shareUrl);
			clickLog.setLinkValue(linkValue);
			clickLog.setCreateDate(new Date());
			clickLog.setDelFlag("0");
			cutLinkClickLogMapper.insertSelective(clickLog);
		} catch (Exception e) {
			logger.error("分享链接点击日志添加失败");
		}
		
	}
		
		
}
