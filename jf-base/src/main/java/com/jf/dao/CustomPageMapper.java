package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.CustomPage;
import com.jf.entity.CustomPageExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomPageMapper extends BaseDao<CustomPage, CustomPageExample> {
    int countByExample(CustomPageExample example);

    int deleteByExample(CustomPageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CustomPage record);

    int insertSelective(CustomPage record);

    List<CustomPage> selectByExample(CustomPageExample example);

    CustomPage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CustomPage record, @Param("example") CustomPageExample example);

    int updateByExample(@Param("record") CustomPage record, @Param("example") CustomPageExample example);

    int updateByPrimaryKeySelective(CustomPage record);

    int updateByPrimaryKey(CustomPage record);
}
