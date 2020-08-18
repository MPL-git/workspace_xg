package com.jf.dao;

import com.jf.entity.AppealPlatformRemarksPic;
import com.jf.entity.AppealPlatformRemarksPicExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppealPlatformRemarksPicMapper extends BaseDao<AppealPlatformRemarksPic, AppealPlatformRemarksPicExample> {
    int countByExample(AppealPlatformRemarksPicExample example);

    int deleteByExample(AppealPlatformRemarksPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppealPlatformRemarksPic record);

    int insertSelective(AppealPlatformRemarksPic record);

    List<AppealPlatformRemarksPic> selectByExample(AppealPlatformRemarksPicExample example);

    AppealPlatformRemarksPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppealPlatformRemarksPic record, @Param("example") AppealPlatformRemarksPicExample example);

    int updateByExample(@Param("record") AppealPlatformRemarksPic record, @Param("example") AppealPlatformRemarksPicExample example);

    int updateByPrimaryKeySelective(AppealPlatformRemarksPic record);

    int updateByPrimaryKey(AppealPlatformRemarksPic record);
}