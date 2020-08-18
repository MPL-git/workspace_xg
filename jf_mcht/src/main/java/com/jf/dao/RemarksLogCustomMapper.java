package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.RemarksLogCustom;
import com.jf.entity.RemarksLogExample;
@Repository
public interface RemarksLogCustomMapper{
	public List<RemarksLogCustom>selectByExample(RemarksLogExample remarksLogExample);
	
	public RemarksLogCustom getRemarksLogBySubOrderId(Integer subOrderId);
}