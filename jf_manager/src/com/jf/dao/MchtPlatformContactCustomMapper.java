package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtPlatformContactCustom;
import com.jf.entity.MchtPlatformContactExample;
@Repository
public interface MchtPlatformContactCustomMapper{
    int countByExample(MchtPlatformContactExample example);
    List<MchtPlatformContactCustom> selectByExample(MchtPlatformContactExample example);
    MchtPlatformContactCustom selectByPrimaryKey(Integer id);
	List<Integer> getMchtIdsByStaffId(int staffId);
}