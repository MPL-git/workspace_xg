package com.jf.dao;

import com.jf.entity.SysAppMessage;
import com.jf.entity.SysAppMessageExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface SysAppMessageMapper extends BaseDao<SysAppMessage, SysAppMessageExample>{
    int countByExample(SysAppMessageExample example);

    int deleteByExample(SysAppMessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysAppMessage record);

    int insertSelective(SysAppMessage record);

    List<SysAppMessage> selectByExample(SysAppMessageExample example);

    SysAppMessage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysAppMessage record, @Param("example") SysAppMessageExample example);

    int updateByExample(@Param("record") SysAppMessage record, @Param("example") SysAppMessageExample example);

    int updateByPrimaryKeySelective(SysAppMessage record);

    int updateByPrimaryKey(SysAppMessage record);
}