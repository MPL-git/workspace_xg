package com.jf.service;

import com.jf.biz.PlatformMsgBiz;
import com.jf.entity.PlatformMsgExt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlatformMsgService extends PlatformMsgBiz {

	/**
	 * 发送站内信 add by huangdl 2019-07-22
	 */
	public PlatformMsgExt save(int mchtId, String title, String content, String msgType){
		PlatformMsgExt model = new PlatformMsgExt();
		model.setMchtId(mchtId);
		model.setMsgType(msgType);
		model.setTitle(title);
		model.setContent(content);
		return save(model);
	}


}
