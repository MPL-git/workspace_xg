package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.PlatformCapitalAccount;
@Repository
public interface PlatformCapitalAccountCustomMapper{

	List<PlatformCapitalAccount> getPlatformCapitalAccountListByPaymentType(String paymentType);

	List<PlatformCapitalAccount> getPlatformCapitalAccountListByPaymentTypeAndAccountType(Map<String,String> map);

	List<String> getPaymentNames();

}