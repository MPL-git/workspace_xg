package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.DesignTaskRefundOrder;
import com.jf.entity.DesignTaskRefundOrderExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignTaskRefundOrderMapper extends BaseDao<DesignTaskRefundOrder, DesignTaskRefundOrderExample> {
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