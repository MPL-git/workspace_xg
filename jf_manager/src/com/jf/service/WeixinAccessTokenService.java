package com.jf.service;

import com.jf.dao.WeixinAccessTokenMapper;
import com.jf.entity.WeixinAccessToken;
import com.jf.entity.WeixinAccessTokenExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月5日 上午11:13:28 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class WeixinAccessTokenService extends BaseService<WeixinAccessToken, WeixinAccessTokenExample>{
	@Autowired
	private WeixinAccessTokenMapper weixinAccessTokenMapper;

	@Autowired
	public void setWeixinAccessTokenMapper(WeixinAccessTokenMapper weixinAccessTokenMapper) {
		this.setDao(weixinAccessTokenMapper);
		this.weixinAccessTokenMapper = weixinAccessTokenMapper;
	}

	/**
	 * 获取微信accessToken
	 */
	public String getAccessToken(String appId) {
		WeixinAccessTokenExample weixinAccessTokenExample = new WeixinAccessTokenExample();
		weixinAccessTokenExample.createCriteria().andDelFlagEqualTo("0").andAppIdEqualTo(appId);
		List<WeixinAccessToken> weixinAccessTokens = weixinAccessTokenMapper.selectByExample(weixinAccessTokenExample);
		if (weixinAccessTokens == null || weixinAccessTokens.size() == 0) {
			return null;
		} else {
			return weixinAccessTokens.get(0).getAccessToken();
		}
	}
}
