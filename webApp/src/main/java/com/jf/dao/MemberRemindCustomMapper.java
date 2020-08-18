package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberRemindCustom;
@Repository
public interface MemberRemindCustomMapper{

	List<MemberRemindCustom> getProductRemindList(Map<String, Object> params);

	List<MemberRemindCustom> getActivityRemindList(Map<String, Object> params);

	List<MemberRemindCustom> getMchtShopList(Map<String, Object> params);

	List<MemberRemindCustom> getProductCollectionList(Map<String, Object> params);

}