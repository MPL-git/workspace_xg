package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.ViolateComplainOrder;
import com.jf.entity.ViolateComplainOrderExample;
@Repository
public interface ViolateComplainOrderMapper extends BaseDao<ViolateComplainOrder, ViolateComplainOrderExample>{
    int countByExample(ViolateComplainOrderExample example);

    int deleteByExample(ViolateComplainOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ViolateComplainOrder record);

    int insertSelective(ViolateComplainOrder record);

    List<ViolateComplainOrder> selectByExample(ViolateComplainOrderExample example);

    ViolateComplainOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ViolateComplainOrder record, @Param("example") ViolateComplainOrderExample example);

    int updateByExample(@Param("record") ViolateComplainOrder record, @Param("example") ViolateComplainOrderExample example);

    int updateByPrimaryKeySelective(ViolateComplainOrder record);

    int updateByPrimaryKey(ViolateComplainOrder record);
}