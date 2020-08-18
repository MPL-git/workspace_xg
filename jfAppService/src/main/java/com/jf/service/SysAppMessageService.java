package com.jf.service;

import com.jf.biz.SysAppMessageBiz;
import com.jf.common.base.BaseService;
import com.jf.common.ext.query.Page;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.SysAppMessageCustomMapper;
import com.jf.dao.SysAppMessageMapper;
import com.jf.entity.SysAppMessage;
import com.jf.entity.SysAppMessageCustom;
import com.jf.entity.SysAppMessageExample;
import com.jf.entity.SysAppMessageExt;
import com.jf.entity.SysAppMessageExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年6月15日 上午10:24:50 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SysAppMessageService extends BaseService<SysAppMessage, SysAppMessageExample> {
	
	@Autowired
	private SysAppMessageMapper sysAppMessageMapper;
	
	@Autowired
	private SysAppMessageCustomMapper sysAppMessageCustomMapper;

	@Autowired
	private SysAppMessageBiz sysAppMessageBiz;
	@Autowired
	private CommonService commonService;
	@Autowired
	private TaskActivitySelectionService taskActivitySelectionService;


	@Autowired
	public void setSysAppMessageMapper(SysAppMessageMapper sysAppMessageMapper) {
		this.setDao(sysAppMessageMapper);
		this.sysAppMessageMapper = sysAppMessageMapper;
	}
	
	/**
	 * 
	 * 方法描述 ：获取系统消息
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月15日 上午11:57:53 
	 * @version
	 * @param params
	 * @return
	 */
	public List<SysAppMessageCustom> getSystemMsg(Map<String, Object> params) {
		
		return sysAppMessageCustomMapper.getSystemMsg(params);
	}
	/**
	 * 
	 * 方法描述 ：获取交易消息
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月15日 上午11:58:03 
	 * @version
	 * @param params
	 * @return
	 */
	public List<SysAppMessageCustom> getTransactionMsg(Map<String, Object> params) {
		
		return sysAppMessageCustomMapper.getTransactionMsg(params);
	}

	public SysAppMessage add(String type, String title, String content,
			String linkType, Integer linkId, Integer memberId, Integer createBy, String pushFlag) {
		
		SysAppMessage sysAppMessage = new SysAppMessage();
		sysAppMessage.setType(type);
		sysAppMessage.setTitle(title);
		sysAppMessage.setContent(content);
		sysAppMessage.setLinkType(linkType);
		sysAppMessage.setLinkId(linkId);
		sysAppMessage.setMemberId(memberId);
		sysAppMessage.setPushFlag(pushFlag);
		sysAppMessage.setCreateBy(createBy);
		sysAppMessage.setCreateDate(new Date());
		sysAppMessage.setDelFlag("0");
		
		return saveModel(sysAppMessage);
	}


	public SysAppMessage saveModel(SysAppMessage sysAppMessage) {
		sysAppMessageMapper.insertSelective(sysAppMessage);
		return sysAppMessage;
	}

	/**
	 * 获取最新一条消息
	 */
	public SysAppMessageExt findLatest(int memberId, String type){
		SysAppMessageExtExample example = new SysAppMessageExtExample();
		SysAppMessageExtExample.SysAppMessageExtCriteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andMemberIdEqualTo(memberId);
		criteria.andTypeEqualTo(type);
		example.setOrderByClause("a.id desc");
		return sysAppMessageBiz.find(example);
	}

	public Page<Map<String, Object>> paginate(SysAppMessageExtExample example){
		Page<SysAppMessageExt> page = sysAppMessageBiz.paginate(example);
		List<SysAppMessageExt> infoList = page.getList();
		List<Map<String, Object>> list = new ArrayList<>();
		for(SysAppMessageExt info : infoList){
			list.add(transToMap(info));
		}
		return new Page<>(list, page.getPageNumber(), page.getPageSize(), page.getTotalPage(), page.getTotalRow());
	}

	/**
	 * 实体转为前端展示的数据
	 */
	public Map<String, Object> transToMap(SysAppMessageExt model){
		Map<String, Object> map = new HashMap<>();
		map.put("id", model.getId());
		map.put("type", model.getType());
		map.put("title", model.getTitle());
		map.put("content", model.getContent());
		map.put("pic", StringUtil.getPic(model.getPic(),""));
		map.put("linkType", model.getLinkType());
		map.put("linkId", model.getLinkId());
		map.put("createDate", model.getCreateDate());

		if("11".equals(model.getLinkType()) && model.getLinkId() != null){
			String decorateUrl = commonService.getCloumnLinkUrl(String.valueOf(model.getLinkId()), "11");
			map.put("decorateUrl", decorateUrl);
		}
		String msgDate;
		//获取的创建时间
		String createDate = DateUtil.getFormatDate(model.getCreateDate(), "yyyyMMdd");
		if(createDate.equals(DateUtil.getFormatDate(new Date(), "yyyyMMdd"))){
			msgDate = DateUtil.getFormatDate(model.getCreateDate(), "HH:mm");
		}else if(createDate.equals(DateUtil.getFormatDate(DateUtil.addDay(new Date(), -1), "yyyyMMdd"))){
			msgDate = "昨天";
		}else if(createDate.equals(DateUtil.getFormatDate(DateUtil.addDay(new Date(), -2), "yyyyMMdd"))){
			msgDate = "前天";
		}else{
			msgDate = DateUtil.getFormatDate(model.getCreateDate(), "yyyy年MM月dd日");
		}
		map.put("msgDate", msgDate);
		map.put("activitySelectionTime", model.getCreateDate().getTime());
		return map;
	}
	
}
