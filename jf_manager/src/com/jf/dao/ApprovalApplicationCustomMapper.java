package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ApprovalApplicationCustom;
import com.jf.entity.ApprovalApplicationCustomExample;

@Repository
public interface ApprovalApplicationCustomMapper {
	
	ApprovalApplicationCustom selectCustomByPrimaryKey(Integer id);
    int countCustomByExample(ApprovalApplicationCustomExample example);
    List<ApprovalApplicationCustom> selectCustomsByExample(ApprovalApplicationCustomExample example);
    List<ApprovalApplicationCustom> selectByCustomExampleWithBLOBs(ApprovalApplicationCustomExample example);

}
