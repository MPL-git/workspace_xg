package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.WithdrawOrderStatusLog;
@Repository
public interface WithdrawOrderStatusLogCustomMapper{

	void batchInsert(List<WithdrawOrderStatusLog> withdrawOrderStatusLogs); 
	
}