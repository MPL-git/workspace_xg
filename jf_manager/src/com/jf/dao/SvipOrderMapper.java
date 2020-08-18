package com.jf.dao;

import com.jf.entity.SvipOrder;
import com.jf.entity.SvipOrderExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SvipOrderMapper extends BaseDao<SvipOrder,SvipOrderExample> {
    int countByExample(SvipOrderExample example);

    int deleteByExample(SvipOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SvipOrder record);

    int insertSelective(SvipOrder record);

    List<SvipOrder> selectByExample(SvipOrderExample example);

    SvipOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SvipOrder record, @Param("example") SvipOrderExample example);

    int updateByExample(@Param("record") SvipOrder record, @Param("example") SvipOrderExample example);

    int updateByPrimaryKeySelective(SvipOrder record);

    int updateByPrimaryKey(SvipOrder record);
}