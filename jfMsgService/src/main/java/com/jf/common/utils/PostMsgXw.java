package com.jf.common.utils;

import com.esms.PostMsg;
import com.jf.common.constant.Const;

/**
 * 
 * @ClassName PostMsgXw
 * @Description TODO(玄武科技短息发送——单例模式——静态内部类)
 * @author pengl
 * @date 2018年9月28日 下午12:48:19
 */
public class PostMsgXw {

	private PostMsgXw() {}

	private static class PostMsgInstance {
		private static final PostMsg INSTANCE = new PostMsg(Const.XW_LINK_MODE, Const.XW_SOCKET_OVER_TIME, Const.XW_SOCKET_CONCURRENT);
	}
	
	public static PostMsg getInstance() {
		return PostMsgInstance.INSTANCE;
	}
	
}
