/**
 * @ClassName TODO
 * @Description
 * @author chengh
 * @date
 */
package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ComplainOrderPicMapper;
import com.jf.dao.ContractRenewLogCustomMapper;
import com.jf.dao.ContractRenewLogMapper;
import com.jf.entity.ContractRenewLog;
import com.jf.entity.ContractRenewLogCustom;
import com.jf.entity.ContractRenewLogExample;

/**
 * @ClassName ContractRenewLogService
 * @Description TODO
 * @author chengh
 * @date 2019年8月1日 下午4:06:23
 */
@Service
@Transactional
public class ContractRenewLogService extends BaseService<ContractRenewLog,ContractRenewLogExample>{
	
	@Autowired
	private ContractRenewLogMapper contractRenewLogMapper;
	
	@Autowired
	private ContractRenewLogCustomMapper contractRenewLogCustomMapper;
	@Autowired
	public void setComplainOrderPicMapper(ContractRenewLogMapper contractRenewLogMapper) {
		super.setDao(contractRenewLogMapper);
		this.dao = contractRenewLogMapper;
	}

	/**
	 * @MethodName selectCustomByExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月1日 下午4:28:03
	 */
	public List<ContractRenewLogCustom> selectCustomByExample(
			ContractRenewLogExample contractRenewLogExample) {
		// TODO Auto-generated method stub
		return contractRenewLogCustomMapper.selectCustomByExample(contractRenewLogExample);
	}
}
