package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.OrderProductSnapshot;
import com.jf.entity.OrderProductSnapshotExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductSnapshotMapper extends BaseDao<OrderProductSnapshot, OrderProductSnapshotExample> {
    int countByExample(OrderProductSnapshotExample example);

    int deleteByExample(OrderProductSnapshotExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderProductSnapshot record);

    int insertSelective(OrderProductSnapshot record);

    List<OrderProductSnapshot> selectByExample(OrderProductSnapshotExample example);

    OrderProductSnapshot selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderProductSnapshot record, @Param("example") OrderProductSnapshotExample example);

    int updateByExample(@Param("record") OrderProductSnapshot record, @Param("example") OrderProductSnapshotExample example);

    int updateByPrimaryKeySelective(OrderProductSnapshot record);

    int updateByPrimaryKey(OrderProductSnapshot record);

    List<OrderProductSnapshot> selectByExampleWithBLOBs(OrderProductSnapshotExample example);
    int updateByPrimaryKeyWithBLOBs(OrderProductSnapshot record);
    int updateByExampleWithBLOBs(@Param("record") OrderProductSnapshot record, @Param("example") OrderProductSnapshotExample example);
}
