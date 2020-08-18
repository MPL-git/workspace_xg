package com.jf.dao;

import com.jf.entity.DesignTaskRefundOrder;
import com.jf.entity.DesignTaskRefundOrderExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface DesignTaskRefundOrderMapper extends BaseDao<DesignTaskRefundOrder, DesignTaskRefundOrderExample>{
    int countByExample(DesignTaskRefundOrderExample example);

    int deleteByExample(DesignTaskRefundOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DesignTaskRefundOrder record);

    int insertSelective(DesignTaskRefundOrder record);

    List<DesignTaskRefundOrder> selectByExample(DesignTaskRefundOrderExample example);

    DesignTaskRefundOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DesignTaskRefundOrder record, @Param("example") DesignTaskRefundOrderExample example);

    int updateByExample(@Param("record") DesignTaskRefundOrder record, @Param("example") DesignTaskRefundOrderExample example);

    int updateByPrimaryKeySelective(DesignTaskRefundOrder record);

    int updateByPrimaryKey(DesignTaskRefundOrder record);
}