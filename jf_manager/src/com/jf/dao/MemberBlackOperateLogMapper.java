package com.jf.dao;

import com.jf.entity.MemberBlackOperateLog;
import com.jf.entity.MemberBlackOperateLogExample;
import com.jf.entity.MemberBlackPic;
import com.jf.entity.MemberBlackPicExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberBlackOperateLogMapper extends BaseDao<MemberBlackOperateLog, MemberBlackOperateLogExample>{
	
}