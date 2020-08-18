package com.jf.dao;

import com.jf.entity.SpreadActivityPermission;
import com.jf.entity.SpreadActivityPermissionExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpreadActivityPermissionMapper extends BaseDao<SpreadActivityPermission, SpreadActivityPermissionExample> {
    int countByExample(SpreadActivityPermissionExample example);

    int deleteByExample(SpreadActivityPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpreadActivityPermission record);

    int insertSelective(SpreadActivityPermission record);

    List<SpreadActivityPermission> selectByExample(SpreadActivityPermissionExample example);

    SpreadActivityPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpreadActivityPermission record, @Param("example") SpreadActivityPermissionExample example);

    int updateByExample(@Param("record") SpreadActivityPermission record, @Param("example") SpreadActivityPermissionExample example);

    int updateByPrimaryKeySelective(SpreadActivityPermission record);

    int updateByPrimaryKey(SpreadActivityPermission record);
}