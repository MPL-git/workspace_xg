package com.jf.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.PlatformMsgCustomMapper;
import com.jf.dao.PlatformMsgMapper;
import com.jf.entity.MchtPlatformContactCustom;
import com.jf.entity.MchtPlatformContactExample;
import com.jf.entity.PlatformMsg;
import com.jf.entity.PlatformMsgCustom;
import com.jf.entity.PlatformMsgExample;
/**
 * 站内消息
 * @author luoyl
 * 创建时间：2017-3-27 14:46
 */
@Service
@Transactional
public class PlatformMsgService extends BaseService<PlatformMsg, PlatformMsgExample> {
	@Autowired
	private PlatformMsgMapper platformMsgMapper;
	
	@Resource
	private PlatformMsgCustomMapper platformMsgCustomMapper; 

	@Autowired
	public void setPlatformMsgMapper(PlatformMsgMapper platformMsgMapper) {
		super.setDao(platformMsgMapper);
		this.platformMsgMapper = platformMsgMapper;
	}
	
	/**
	 * 总条数
	 * @param example
	 * @return
	 */
	public int countPlatformMsgCustomByExample(PlatformMsgExample example){
		return platformMsgCustomMapper.countByExample(example);
	}
	/**
	 * 列表
	 * @param example
	 * @return
	 */
	public List<PlatformMsgCustom> selectPlatformMsgCustomByExample(PlatformMsgExample example){
    	return platformMsgCustomMapper.selectByExample(example);
    }
	/**
	 * 详情信息
	 * @param id
	 * @return
	 */
	public PlatformMsgCustom selectPlatformMsgCustomByPrimaryKey(Integer id){
		return platformMsgCustomMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 发送站内信 add by huangdl 2019-07-20
	 */
	public PlatformMsg save(int mchtId, String title, String content, String msgType){
		PlatformMsg model = new PlatformMsg();
		model.setMchtId(mchtId);
		model.setMsgType(msgType);
		model.setTitle(title);
		model.setContent(content);
    	insertSelective(model);
    	return model;
	}
}
