package com.jf.dao;

import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoChg;
import com.jf.entity.MchtInfoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MchtInfoMapper extends BaseDao<MchtInfo, MchtInfoExample>{
    int countByExample(MchtInfoExample example);

    int deleteByExample(MchtInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtInfo record);

    int insertSelective(MchtInfo record);

    List<MchtInfo> selectByExampleWithBLOBs(MchtInfoExample example);

    List<MchtInfo> selectByExample(MchtInfoExample example);

    MchtInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtInfo record, @Param("example") MchtInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") MchtInfo record, @Param("example") MchtInfoExample example);

    int updateByExample(@Param("record") MchtInfo record, @Param("example") MchtInfoExample example);

    int updateByPrimaryKeySelective(MchtInfo record);

    int updateByPrimaryKeyWithBLOBs(MchtInfo record);

    int updateByPrimaryKey(MchtInfo record);
}