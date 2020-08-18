package com.jf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.PlatformCapitalAccountCustomMapper;
import com.jf.dao.PlatformCapitalAccountMapper;
import com.jf.entity.PlatformCapitalAccount;
import com.jf.entity.PlatformCapitalAccountExample;

@Service
@Transactional
public class PlatformCapitalAccountService extends BaseService<PlatformCapitalAccount, PlatformCapitalAccountExample> {
	@Autowired
	private PlatformCapitalAccountMapper platformCapitalAccountMapper;
	
	@Autowired
	private PlatformCapitalAccountCustomMapper platformCapitalAccountCustomMapper;
	
	@Autowired
	public void setPlatformCapitalAccountMapper(PlatformCapitalAccountMapper platformCapitalAccountMapper) {
		super.setDao(platformCapitalAccountMapper);
		this.platformCapitalAccountMapper = platformCapitalAccountMapper;
	}
	
	public List<PlatformCapitalAccount> getPlatformCapitalAccountListByPaymentType(String paymentType){
		return platformCapitalAccountCustomMapper.getPlatformCapitalAccountListByPaymentType(paymentType);
	}

	public List<PlatformCapitalAccount> getPlatformCapitalAccountListByPaymentTypeAndAccountType(String paymentType,String accountType) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("paymentType", paymentType);
		map.put("accountType", accountType);
		return platformCapitalAccountCustomMapper.getPlatformCapitalAccountListByPaymentTypeAndAccountType(map);
	}

	public List<String> getPaymentNames() {
		return platformCapitalAccountCustomMapper.getPaymentNames();
	}
}
