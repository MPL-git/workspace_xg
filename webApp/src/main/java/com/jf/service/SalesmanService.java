package com.jf.service;

import java.util.List;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SalesmanMapper;
import com.jf.entity.Salesman;
import com.jf.entity.SalesmanExample;

@Service
@Transactional
public class SalesmanService extends BaseService<Salesman, SalesmanExample> {
	@Autowired
	private SalesmanMapper salesmanMapper;
	@Autowired
	public void setSalesmanMapper(SalesmanMapper salesmanMapper) {
		this.setDao(salesmanMapper);
		this.salesmanMapper = salesmanMapper;
	}
	public Salesman findModelByMemberId(Integer memberId) {
		Salesman salesman = null;
		SalesmanExample salesmanExample = new SalesmanExample();
		salesmanExample.createCriteria().andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
		List<Salesman> salesmans = selectByExample(salesmanExample);
		if(CollectionUtils.isNotEmpty(salesmans)){
			salesman = salesmans.get(0);
		}
		return salesman;
	}
	
	public Salesman findModelById(Integer salesmanId) {
		Salesman salesman = null;
		SalesmanExample salesmanExample = new SalesmanExample();
		salesmanExample.createCriteria().andMemberIdEqualTo(salesmanId).andStatusEqualTo("1").andDelFlagEqualTo("0");
		List<Salesman> salesmans = selectByExample(salesmanExample);
		if(CollectionUtils.isNotEmpty(salesmans)){
			salesman = salesmans.get(0);
		}
		return salesman;
	}
	
	
}
