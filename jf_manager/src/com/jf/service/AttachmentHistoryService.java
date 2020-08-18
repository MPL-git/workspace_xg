package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.AttachmentHistoryMapper;
import com.jf.entity.AttachmentHistory;
import com.jf.entity.AttachmentHistoryExample;

@Service
@Transactional
public class AttachmentHistoryService extends BaseService<AttachmentHistory, AttachmentHistoryExample> {
	@Autowired
	private AttachmentHistoryMapper attachmentHistoryMapper;

	@Autowired
	public void setattachmentHistoryMapper(AttachmentHistoryMapper attachmentHistoryMapper) {
		super.setDao(attachmentHistoryMapper);
		this.attachmentHistoryMapper = attachmentHistoryMapper;
	}
}
