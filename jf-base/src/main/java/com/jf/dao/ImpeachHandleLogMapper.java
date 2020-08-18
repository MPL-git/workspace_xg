package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ImpeachHandleLog;
import com.jf.entity.ImpeachHandleLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImpeachHandleLogMapper extends BaseDao<ImpeachHandleLog, ImpeachHandleLogExample> {
    int countByExample(ImpeachHandleLogExample example);

    int deleteByExample(ImpeachHandleLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ImpeachHandleLog record);

    int insertSelective(ImpeachHandleLog record);

    List<ImpeachHandleLog> selectByExample(ImpeachHandleLogExample example);

    ImpeachHandleLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ImpeachHandleLog record, @Param("example") ImpeachHandleLogExample example);

    int updateByExample(@Param("record") ImpeachHandleLog record, @Param("example") ImpeachHandleLogExample example);

    int updateByPrimaryKeySelective(ImpeachHandleLog record);

    int updateByPrimaryKey(ImpeachHandleLog record);
}
