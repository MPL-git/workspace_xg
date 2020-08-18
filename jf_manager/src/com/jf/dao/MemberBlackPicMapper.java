package com.jf.dao;

import com.jf.entity.MemberBlackPic;
import com.jf.entity.MemberBlackPicExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberBlackPicMapper extends BaseDao<MemberBlackPic, MemberBlackPicExample> {
	
}