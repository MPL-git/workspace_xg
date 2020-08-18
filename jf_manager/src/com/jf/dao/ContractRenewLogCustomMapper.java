package com.jf.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ContractRenewLog;
import com.jf.entity.ContractRenewLogCustom;
import com.jf.entity.ContractRenewLogExample;

@Repository
public interface ContractRenewLogCustomMapper {

	/**
	 * @MethodName selectCustomByExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月1日 下午4:29:53
	 */
	List<ContractRenewLogCustom> selectCustomByExample(
			ContractRenewLogExample contractRenewLogExample);
	
}