package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberAddressCustom;
@Repository
public interface MemberAddressCustomMapper{

	void updateDefaultAddressById(Integer id);

	void updateDefaultAddressByMemberId(Integer memberId);

	Integer getMemberAddressCount(Map<String, Object> params);

	List<MemberAddressCustom> getMemberAddressList(Map<String, Object> params);

	MemberAddressCustom getAddressById(Map<String, Object> addressParamsMap);

	MemberAddressCustom getAddressByMemberId(Map<String, Object> addressParamsMap);
    
}