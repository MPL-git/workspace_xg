package com.jf.dao;

import java.util.List;

import com.jf.entity.PropertyRightComplain;
import com.jf.entity.PropertyRightComplainExample;
import org.springframework.stereotype.Repository;



@Repository
public interface PropertyRightComplainCustomMapper{

	/**
	 * 查询超期未起诉列表
	 * 包含权利人未起诉，或者已发出起诉但状态还在待审核待修改状态数据
	 * 
	 * @param example
	 * @return
	 */
	List<PropertyRightComplain> selectOverDueByExample(PropertyRightComplainExample example);
}