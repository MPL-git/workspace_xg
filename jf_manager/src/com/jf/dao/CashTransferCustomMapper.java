package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.CashTransferCustom;
import com.jf.entity.CashTransferCustomExample;

@Repository
public interface CashTransferCustomMapper {
    
	public int countByCustomExample(CashTransferCustomExample example);

	public List<CashTransferCustom> selectByCustomExample(CashTransferCustomExample example);

}