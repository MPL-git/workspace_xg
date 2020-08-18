package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ComplainOrderPicCustomMapper;
import com.jf.dao.ComplainOrderPicMapper;
import com.jf.entity.ComplainOrderPic;
import com.jf.entity.ComplainOrderPicExample;

@Service
@Transactional
public class ComplainOrderPicService extends BaseService<ComplainOrderPic,ComplainOrderPicExample> {
	@Autowired
	private ComplainOrderPicMapper dao;
	
	@Autowired
	private ComplainOrderPicCustomMapper complainOrderPicCustomMapper;
	
	@Autowired
	public void setComplainOrderPicMapper(ComplainOrderPicMapper complainOrderPicMapper) {
		super.setDao(complainOrderPicMapper);
		this.dao = complainOrderPicMapper;
	}

	public List<String> getPicsByViolateComplainOrderId(Integer complainOrderId) {
		return complainOrderPicCustomMapper.getPicsByViolateComplainOrderId(complainOrderId);
	}
	
	
}
