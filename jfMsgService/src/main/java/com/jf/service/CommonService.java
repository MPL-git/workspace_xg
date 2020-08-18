package com.jf.service;

import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.Config;
import com.jf.common.utils.StringUtil;
import com.jf.dao.CommonMapper;
import com.jf.entity.SysParamCfg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class CommonService {
	@Resource
	private CommonMapper commonMapper;
	@Autowired
	private SysParamCfgService sysParamCfgService;
	
	public Integer getSequence(String sequenceName){
		return commonMapper.getSequence(sequenceName);
	}
	
	public String getCloumnLinkUrl(String linkValue, String type) {
		if("1".equals(type)){//签到
			linkValue = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/newsign/index.html";
		}else if("2".equals(type)){//砍价
			linkValue = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/cutprice/index.html";
		}else if("3".equals(type)){//邀请免单
			linkValue = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/freeprice/index.html";
		}else if("4".equals(type)){//自建页面
			linkValue = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/templet/brand_decorate.html?pageid="+linkValue;
		}else if("5".equals(type)){//信息管理
			if(!StringUtil.isBlank(linkValue)){
				linkValue = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=link/information.html?id="+linkValue+"&type=";
			}
		}else if("6".equals(type)){//新星协议
			if(!StringUtil.isBlank(linkValue)){
				linkValue = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/novaplan/pages/create/index.html?id="+linkValue+"&type=";
			}
		}else if("7".equals(type)){//拉新页面
			if(!StringUtil.isBlank(linkValue)){
				linkValue = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/novaplan/pages/invite/share/index.html?invitationCode="+linkValue;
			}
		}else if("8".equals(type)){//新星攻略
			linkValue = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/novaplan/pages/strategy/index.html";
		}else if("9".equals(type)){//新星计划首页
			linkValue = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/novaplan/index.html";
		}
		return linkValue;
	}

	/**
	 * 获取特殊商家编码
	 * 这些商家具体特殊抽佣比例，他们的商品暂不支持使用平台优惠和积分抵扣，也无法获得积分
	 */
	public List<String> listSpecMchtCode(){
		SysParamCfg sysParamCfg = sysParamCfgService.findByCode("MCHT_SPEC_MCHT_CODE");
		if(sysParamCfg != null && StrKit.notBlank(sysParamCfg.getParamValue())){
			String[] values = sysParamCfg.getParamValue().split(",");
			return Arrays.asList(values);
		}
		return new ArrayList<>();
	}
}
