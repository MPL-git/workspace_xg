package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.SpUser;
import com.jf.entity.SpUserExample;

@Repository
public interface SpUserMapper extends BaseDao<SpUser, SpUserExample> {
    int countByExample(SpUserExample example);

    int deleteByExample(SpUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpUser record);

    int insertSelective(SpUser record);

    List<SpUser> selectByExample(SpUserExample example);

    SpUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpUser record, @Param("example") SpUserExample example);

    int updateByExample(@Param("record") SpUser record, @Param("example") SpUserExample example);

    int updateByPrimaryKeySelective(SpUser record);

    int updateByPrimaryKey(SpUser record);
}
