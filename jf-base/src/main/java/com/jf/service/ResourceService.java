package com.jf.service;

import com.jf.biz.SysParamCfgBiz;
import com.jf.common.ext.util.StrKit;
import com.jf.entity.SysParamCfg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ResourceService {

	private static Logger logger = LoggerFactory.getLogger(ResourceService.class);

	@Autowired
	private SysParamCfgBiz sysParamCfgBiz;

	/**
	 * 获取特殊商家编码
	 * 这些商家具体特殊抽佣比例，他们的商品暂不支持使用平台优惠和积分抵扣，也无法获得积分
	 */
	public List<String> listSpecMchtCode(){
		SysParamCfg sysParamCfg = sysParamCfgBiz.findByCode("MCHT_SPEC_MCHT_CODE");
		if(sysParamCfg != null && StrKit.notBlank(sysParamCfg.getParamValue())){
			String[] values = sysParamCfg.getParamValue().split(",");
			return Arrays.asList(values);
		}
		return new ArrayList<>();
	}
}
