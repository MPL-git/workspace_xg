package com.jf.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SalesmanCustomMapper;
import com.jf.dao.SalesmanMapper;
import com.jf.entity.Salesman;
import com.jf.entity.SalesmanCustom;
import com.jf.entity.SalesmanCustomExample;
import com.jf.entity.SalesmanExample;

@Service
@Transactional
public class SalesmanService extends BaseService<Salesman, SalesmanExample>{
	
	@Autowired
	private SalesmanMapper salesmanMapper;
	
	@Autowired
	private SalesmanCustomMapper salesmanCustomMapper;
	
	@Autowired
	public void setactivityAreaMapper(SalesmanMapper salesmanMapper) {
		super.setDao(salesmanMapper);
		this.salesmanMapper = salesmanMapper;
	}

	/**
	 * @MethodName countByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月12日 下午3:26:56
	 */
	public Integer countByCustomExample(
			SalesmanCustomExample salesmanCustomExample) {
		// TODO Auto-generated method stub
		return salesmanCustomMapper.countByCustomExample(salesmanCustomExample);
	}

	/**
	 * @MethodName selectByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月12日 下午3:27:04
	 */
	public List<SalesmanCustom> selectByCustomExample(
			SalesmanCustomExample salesmanCustomExample) {
		// TODO Auto-generated method stub
		return salesmanCustomMapper.selectByCustomExample(salesmanCustomExample);
	}
	
}
