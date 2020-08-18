package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.LandingPageAndroidInfo;
import com.jf.entity.LandingPageAndroidInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LandingPageAndroidInfoMapper extends BaseDao<LandingPageAndroidInfo, LandingPageAndroidInfoExample> {
    int countByExample(LandingPageAndroidInfoExample example);

    int deleteByExample(LandingPageAndroidInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LandingPageAndroidInfo record);

    int insertSelective(LandingPageAndroidInfo record);

    List<LandingPageAndroidInfo> selectByExample(LandingPageAndroidInfoExample example);

    LandingPageAndroidInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LandingPageAndroidInfo record, @Param("example") LandingPageAndroidInfoExample example);

    int updateByExample(@Param("record") LandingPageAndroidInfo record, @Param("example") LandingPageAndroidInfoExample example);

    int updateByPrimaryKeySelective(LandingPageAndroidInfo record);

    int updateByPrimaryKey(LandingPageAndroidInfo record);
}