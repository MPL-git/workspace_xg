package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.OrderProductSnapshot;
import com.jf.entity.OrderProductSnapshotCustom;
import com.jf.entity.OrderProductSnapshotCustomExample;

@Repository
public interface OrderProductSnapshotCustomMapper {
	
	public int countByCustomExample(OrderProductSnapshotCustomExample example);

	public List<OrderProductSnapshotCustom> selectByCustomExampleWithBLOBs(OrderProductSnapshotCustomExample example);

	public List<OrderProductSnapshotCustom> selectByCustomExample(OrderProductSnapshotCustomExample example);

	public OrderProductSnapshotCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") OrderProductSnapshot record, @Param("example") OrderProductSnapshotCustomExample example);


}