package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.AppealOrderCustom;
import com.jf.entity.AppealOrderCustomExample;
import com.jf.entity.ImpeachMemberCustom;
import com.jf.entity.ImpeachMemberCustomExample;
import com.jf.entity.ImpeachMemberExample;

@Repository
public interface ImpeachMemberCustomMapper {
	public List<ImpeachMemberCustom>selectByExample(ImpeachMemberCustomExample example);
	
	public int countByExample(ImpeachMemberCustomExample example);

}
