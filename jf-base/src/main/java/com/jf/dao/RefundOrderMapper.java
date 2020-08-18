package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.RefundOrder;
import com.jf.entity.RefundOrderExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefundOrderMapper extends BaseDao<RefundOrder, RefundOrderExample> {
    int countByExample(RefundOrderExample example);

    int deleteByExample(RefundOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RefundOrder record);

    int insertSelective(RefundOrder record);

    List<RefundOrder> selectByExample(RefundOrderExample example);

    RefundOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RefundOrder record, @Param("example") RefundOrderExample example);

    int updateByExample(@Param("record") RefundOrder record, @Param("example") RefundOrderExample example);

    int updateByPrimaryKeySelective(RefundOrder record);

    int updateByPrimaryKey(RefundOrder record);
}
