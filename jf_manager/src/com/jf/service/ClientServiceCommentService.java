package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ClientServiceCommentMapper;
import com.jf.entity.ClientServiceComment;
import com.jf.entity.ClientServiceCommentExample;

@Service
@Transactional
public class ClientServiceCommentService extends BaseService<ClientServiceComment, ClientServiceCommentExample> {

	@Autowired
	private ClientServiceCommentMapper clientServiceCommentMapper;
	
	@Autowired
	public void setClientServiceCommentMapper(ClientServiceCommentMapper clientServiceCommentMapper) {
		super.setDao(clientServiceCommentMapper);
		this.clientServiceCommentMapper = clientServiceCommentMapper;
	}
	
}
