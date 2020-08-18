package com.jf.dao;

import com.jf.entity.LandingPagePic;
import com.jf.entity.LandingPagePicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LandingPagePicMapper extends BaseDao<LandingPagePic,LandingPagePicExample>{
    int countByExample(LandingPagePicExample example);

    int deleteByExample(LandingPagePicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LandingPagePic record);

    int insertSelective(LandingPagePic record);

    List<LandingPagePic> selectByExample(LandingPagePicExample example);

    LandingPagePic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LandingPagePic record, @Param("example") LandingPagePicExample example);

    int updateByExample(@Param("record") LandingPagePic record, @Param("example") LandingPagePicExample example);

    int updateByPrimaryKeySelective(LandingPagePic record);

    int updateByPrimaryKey(LandingPagePic record);
}