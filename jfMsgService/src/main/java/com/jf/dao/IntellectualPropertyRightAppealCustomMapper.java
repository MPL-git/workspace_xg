package com.jf.dao;

import com.jf.entity.IntellectualPropertyRightAppeal;
import com.jf.entity.IntellectualPropertyRightAppealExample;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IntellectualPropertyRightAppealCustomMapper {

	/**
	 * 查询超期未声明列表
	 * 包含商家未申诉，或者已发出申诉但状态还在待审核待修改状态数据
	 * 
	 * @param example
	 * @return
	 */
	List<IntellectualPropertyRightAppeal> selectOverDueByExample(IntellectualPropertyRightAppealExample example);
    
}