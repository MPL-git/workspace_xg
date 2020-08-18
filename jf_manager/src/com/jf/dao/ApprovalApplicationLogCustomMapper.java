package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ApprovalApplicationLogCustom;
import com.jf.entity.ApprovalApplicationLogExample;
import com.jf.entity.ApprovalApplicationNodeCustomExample;


@Repository
public interface ApprovalApplicationLogCustomMapper {
	
	List<ApprovalApplicationLogCustom> selectCustomByExample(ApprovalApplicationLogExample example);
	ApprovalApplicationLogCustom selectCustomByPrimaryKey(Integer id);
	int countCustomByExample(ApprovalApplicationNodeCustomExample example);

}
