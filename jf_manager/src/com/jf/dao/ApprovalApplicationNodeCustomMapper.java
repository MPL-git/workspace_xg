package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ApprovalApplicationNodeCustom;
import com.jf.entity.ApprovalApplicationNodeCustomExample;

@Repository
public interface ApprovalApplicationNodeCustomMapper {
	
	List<ApprovalApplicationNodeCustom> selectCustomByExample(ApprovalApplicationNodeCustomExample example);
	ApprovalApplicationNodeCustom selectCustomByPrimaryKey(Integer id);
	int countCustomByExample(ApprovalApplicationNodeCustomExample example);
	
	    
	//该我审核的 申请审核
	List<ApprovalApplicationNodeCustom> findToExamineCustom(HashMap<String, Object> paramMap);
	
	int countToExamineCustom(HashMap<String, Object> paramMap);
	
	//查找最大的当前需要审核的seq
	int selectNowMaxSeqNo(Integer approvalApplicationId);
	
	 // 查找未审核的排序值最小的核心节点 
	int selectMinCoreSeqNo(HashMap<String, Object> paramMap); 
	
}
