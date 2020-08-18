package com.jf.dao;

import com.jf.entity.DesignTaskOrder;
import com.jf.entity.DesignTaskOrderExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DesignTaskOrderMapper extends BaseDao<DesignTaskOrder,DesignTaskOrderExample>{
    int countByExample(DesignTaskOrderExample example);

    int deleteByExample(DesignTaskOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DesignTaskOrder record);

    int insertSelective(DesignTaskOrder record);

    List<DesignTaskOrder> selectByExample(DesignTaskOrderExample example);

    DesignTaskOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DesignTaskOrder record, @Param("example") DesignTaskOrderExample example);

    int updateByExample(@Param("record") DesignTaskOrder record, @Param("example") DesignTaskOrderExample example);

    int updateByPrimaryKeySelective(DesignTaskOrder record);

    int updateByPrimaryKey(DesignTaskOrder record);
}