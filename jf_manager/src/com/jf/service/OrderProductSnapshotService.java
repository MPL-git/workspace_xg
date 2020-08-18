package com.jf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.OrderProductSnapshotCustomMapper;
import com.jf.dao.OrderProductSnapshotMapper;
import com.jf.entity.OrderProductSnapshot;
import com.jf.entity.OrderProductSnapshotCustom;
import com.jf.entity.OrderProductSnapshotCustomExample;
import com.jf.entity.OrderProductSnapshotExample;

@Service
@Transactional
public class OrderProductSnapshotService extends BaseService<OrderProductSnapshot, OrderProductSnapshotExample> {

	@Autowired
	private OrderProductSnapshotMapper orderProductSnapshotMapper;
	
	@Autowired
	private OrderProductSnapshotCustomMapper orderProductSnapshotCustomMapper;
	
	@Autowired
	public void setOrderProductSnapshotMapper(OrderProductSnapshotMapper orderProductSnapshotMapper) {
		super.setDao(orderProductSnapshotMapper);
		this.orderProductSnapshotMapper = orderProductSnapshotMapper;
	}
	
	public int countByCustomExample(OrderProductSnapshotCustomExample example) {
		return orderProductSnapshotCustomMapper.countByCustomExample(example);
	}

	public List<OrderProductSnapshotCustom> selectByCustomExampleWithBLOBs(OrderProductSnapshotCustomExample example) {
		return orderProductSnapshotCustomMapper.selectByCustomExampleWithBLOBs(example);
	}

	public List<OrderProductSnapshotCustom> selectByCustomExample(OrderProductSnapshotCustomExample example) {
		return orderProductSnapshotCustomMapper.selectByCustomExample(example);
	}

	public OrderProductSnapshotCustom selectByCustomPrimaryKey(Integer id) {
		return orderProductSnapshotCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") OrderProductSnapshot record, @Param("example") OrderProductSnapshotCustomExample example) {
		return orderProductSnapshotCustomMapper.updateByCustomExampleSelective(record, example);
	}
	
}
