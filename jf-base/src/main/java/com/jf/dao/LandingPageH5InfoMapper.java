package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.LandingPageH5Info;
import com.jf.entity.LandingPageH5InfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LandingPageH5InfoMapper extends BaseDao<LandingPageH5Info, LandingPageH5InfoExample> {
    int countByExample(LandingPageH5InfoExample example);

    int deleteByExample(LandingPageH5InfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LandingPageH5Info record);

    int insertSelective(LandingPageH5Info record);

    List<LandingPageH5Info> selectByExample(LandingPageH5InfoExample example);

    LandingPageH5Info selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LandingPageH5Info record, @Param("example") LandingPageH5InfoExample example);

    int updateByExample(@Param("record") LandingPageH5Info record, @Param("example") LandingPageH5InfoExample example);

    int updateByPrimaryKeySelective(LandingPageH5Info record);

    int updateByPrimaryKey(LandingPageH5Info record);
}