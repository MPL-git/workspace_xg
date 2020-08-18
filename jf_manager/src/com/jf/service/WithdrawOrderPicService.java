package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.AttachmentMapper;
import com.jf.dao.WithdrawOrderPicMapper;
import com.jf.entity.WithdrawOrderPic;
import com.jf.entity.WithdrawOrderPicExample;

@Service
@Transactional
public class WithdrawOrderPicService extends BaseService<WithdrawOrderPic,WithdrawOrderPicExample>{
	@Autowired
	private WithdrawOrderPicMapper withdrawOrderPicMapper;

	@Autowired
	public void setattachmentMapper(WithdrawOrderPicMapper withdrawOrderPicMapper) {
		super.setDao(withdrawOrderPicMapper);
		this.withdrawOrderPicMapper = withdrawOrderPicMapper;
	}
}
