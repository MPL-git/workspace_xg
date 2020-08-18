package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.PlatformMsgCustom;
import com.jf.entity.PlatformMsgExample;
@Repository
public interface PlatformMsgCustomMapper{
	public List<PlatformMsgCustom> selectByExample(PlatformMsgExample example);
	public List<HashMap<String, Object>> getMsgTypesByMchtId(Integer mchtId);
	public Integer countByExample(PlatformMsgExample example);
}