package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.DouyinSprLog;
import com.jf.entity.DouyinSprLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DouyinSprLogMapper extends BaseDao<DouyinSprLog, DouyinSprLogExample> {
    int countByExample(DouyinSprLogExample example);

    int deleteByExample(DouyinSprLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DouyinSprLog record);

    int insertSelective(DouyinSprLog record);

    List<DouyinSprLog> selectByExample(DouyinSprLogExample example);

    DouyinSprLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DouyinSprLog record, @Param("example") DouyinSprLogExample example);

    int updateByExample(@Param("record") DouyinSprLog record, @Param("example") DouyinSprLogExample example);

    int updateByPrimaryKeySelective(DouyinSprLog record);

    int updateByPrimaryKey(DouyinSprLog record);
}
