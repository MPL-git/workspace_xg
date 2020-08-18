package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.BusinessCirclesAppealOrderPicMapper;
import com.jf.entity.BusinessCirclesAppealOrderPic;
import com.jf.entity.BusinessCirclesAppealOrderPicExample;

@Service
@Transactional
public class BusinessCirclesAppealOrderPicService extends BaseService<BusinessCirclesAppealOrderPic, BusinessCirclesAppealOrderPicExample> {
	@Autowired
	private BusinessCirclesAppealOrderPicMapper businessCirclesAppealOrderPicMapper;

	@Autowired
	public void setbusinessCirclesAppealOrderPicMapper(BusinessCirclesAppealOrderPicMapper businessCirclesAppealOrderPicMapper) {
		super.setDao(businessCirclesAppealOrderPicMapper);
		this.businessCirclesAppealOrderPicMapper = businessCirclesAppealOrderPicMapper;
	}
}
