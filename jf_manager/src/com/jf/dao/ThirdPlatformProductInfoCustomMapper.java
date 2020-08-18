package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ThirdPlatformProductInfoCustom;
import com.jf.entity.ThirdPlatformProductInfoCustomExample;
@Repository
public interface ThirdPlatformProductInfoCustomMapper{
	public int countByExample(ThirdPlatformProductInfoCustomExample example);
	
	public List<ThirdPlatformProductInfoCustom>selectByExample(ThirdPlatformProductInfoCustomExample example);
	
	public void updateNullSeqNo(Integer id);

}