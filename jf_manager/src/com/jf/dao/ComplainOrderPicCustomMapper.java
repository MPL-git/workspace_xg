package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ComplainOrderPicCustom;
import com.jf.entity.ComplainOrderPicCustomExample;
@Repository
public interface ComplainOrderPicCustomMapper{
	public List<ComplainOrderPicCustom>selectByExample(ComplainOrderPicCustomExample example);

	public List<String> getPicsByViolateComplainOrderId(Integer complainOrderId);
}