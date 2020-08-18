package com.jf.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.ext.util.StrKit;
import com.jf.dao.CommonMapper;
import com.jf.entity.SysParamCfg;

@Service
@Transactional
public class CommonService {
@Resource
private CommonMapper commonMapper;

@Resource
private SysParamCfgService sysParamCfgService;

public Integer getSequence(String sequenceName){
	return commonMapper.getSequence(sequenceName);
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
	return new ArrayList<String>();
  }
}
