package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberDynamicCustom;
import com.jf.entity.MemberDynamicCustomExample;
import org.springframework.stereotype.Repository;

import java.util.List;




@Repository
public interface MemberDynamicCustomMapper extends BaseDao<MemberDynamicCustom, MemberDynamicCustomExample> {

	int countByExample(MemberDynamicCustomExample example);

	List<MemberDynamicCustom> selectByExample(MemberDynamicCustomExample example);
	
	

}
