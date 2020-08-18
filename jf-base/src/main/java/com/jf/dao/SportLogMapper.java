package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SportLog;
import com.jf.entity.SportLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportLogMapper extends BaseDao<SportLog, SportLogExample> {
    int countByExample(SportLogExample example);

    int deleteByExample(SportLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SportLog record);

    int insertSelective(SportLog record);

    List<SportLog> selectByExample(SportLogExample example);

    SportLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SportLog record, @Param("example") SportLogExample example);

    int updateByExample(@Param("record") SportLog record, @Param("example") SportLogExample example);

    int updateByPrimaryKeySelective(SportLog record);

    int updateByPrimaryKey(SportLog record);
}
