package com.jf.dao;

import com.jf.entity.ThirdPlatformProductInfo;
import com.jf.entity.ThirdPlatformProductInfoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ThirdPlatformProductInfoMapper extends BaseDao<ThirdPlatformProductInfo, ThirdPlatformProductInfoExample>{
    int countByExample(ThirdPlatformProductInfoExample example);

    int deleteByExample(ThirdPlatformProductInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ThirdPlatformProductInfo record);

    int insertSelective(ThirdPlatformProductInfo record);

    List<ThirdPlatformProductInfo> selectByExample(ThirdPlatformProductInfoExample example);

    ThirdPlatformProductInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ThirdPlatformProductInfo record, @Param("example") ThirdPlatformProductInfoExample example);

    int updateByExample(@Param("record") ThirdPlatformProductInfo record, @Param("example") ThirdPlatformProductInfoExample example);

    int updateByPrimaryKeySelective(ThirdPlatformProductInfo record);

    int updateByPrimaryKey(ThirdPlatformProductInfo record);
}