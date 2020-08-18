package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.OrderAbnormalLogCustom;
import com.jf.entity.OrderAbnormalLogCustomExample;
import com.jf.entity.OrderAbnormalLogExample;

@Repository
public interface OrderAbnormalLogCustomMapper {

	public int countByCustomExample(OrderAbnormalLogExample example);
	public List<OrderAbnormalLogCustom> selectByCustomExample(OrderAbnormalLogExample example);
	public List<HashMap<String, Object>> getFollowByList(Map<String, Object> paramMap);
	public int countByExample(OrderAbnormalLogCustomExample oalce);
	public List<OrderAbnormalLogCustom> selectByExample(OrderAbnormalLogCustomExample oalce);
	
}
