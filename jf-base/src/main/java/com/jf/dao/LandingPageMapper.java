package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.LandingPage;
import com.jf.entity.LandingPageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LandingPageMapper extends BaseDao<LandingPage, LandingPageExample> {
    int countByExample(LandingPageExample example);

    int deleteByExample(LandingPageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LandingPage record);

    int insertSelective(LandingPage record);

    List<LandingPage> selectByExample(LandingPageExample example);

    LandingPage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LandingPage record, @Param("example") LandingPageExample example);

    int updateByExample(@Param("record") LandingPage record, @Param("example") LandingPageExample example);

    int updateByPrimaryKeySelective(LandingPage record);

    int updateByPrimaryKey(LandingPage record);
}