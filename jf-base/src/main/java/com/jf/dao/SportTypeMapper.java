package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SportType;
import com.jf.entity.SportTypeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportTypeMapper extends BaseDao<SportType, SportTypeExample> {
    int countByExample(SportTypeExample example);

    int deleteByExample(SportTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SportType record);

    int insertSelective(SportType record);

    List<SportType> selectByExample(SportTypeExample example);

    SportType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SportType record, @Param("example") SportTypeExample example);

    int updateByExample(@Param("record") SportType record, @Param("example") SportTypeExample example);

    int updateByPrimaryKeySelective(SportType record);

    int updateByPrimaryKey(SportType record);
}
