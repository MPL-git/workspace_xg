package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtInfo;
import com.jf.entity.PlatformContact;
import com.jf.entity.PlatformContactCustom;
import com.jf.entity.PlatformContactExample;

@Repository
public interface PlatformContactCustomMapper{
    int countByExample(PlatformContactExample example);
    List<PlatformContactCustom> selectByExample(PlatformContactExample example);
    PlatformContactCustom selectByPrimaryKey(Integer id);
	List<MchtInfo> selectMchtInfosByPlatContactId(Integer id);
	//通过商家id查找对接人信息
	List<PlatformContact> selectPlatformContByMchId(HashMap<String, Object> paramMap);
	public Integer getStaffById(Integer staffId);
	public String getContactNameByMchtIdAndContactType(Map<String, Integer> map);
}