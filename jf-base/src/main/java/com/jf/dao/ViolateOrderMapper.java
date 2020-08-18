package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ViolateOrder;
import com.jf.entity.ViolateOrderExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViolateOrderMapper extends BaseDao<ViolateOrder, ViolateOrderExample> {
    int countByExample(ViolateOrderExample example);

    int deleteByExample(ViolateOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ViolateOrder record);

    int insertSelective(ViolateOrder record);

    List<ViolateOrder> selectByExample(ViolateOrderExample example);

    ViolateOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ViolateOrder record, @Param("example") ViolateOrderExample example);

    int updateByExample(@Param("record") ViolateOrder record, @Param("example") ViolateOrderExample example);

    int updateByPrimaryKeySelective(ViolateOrder record);

    int updateByPrimaryKey(ViolateOrder record);
}
