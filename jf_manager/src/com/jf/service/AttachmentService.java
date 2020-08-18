package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.AttachmentMapper;
import com.jf.entity.Attachment;
import com.jf.entity.AttachmentExample;

@Service
@Transactional
public class AttachmentService extends BaseService<Attachment, AttachmentExample> {
	@Autowired
	private AttachmentMapper attachmentMapper;

	@Autowired
	public void setattachmentMapper(AttachmentMapper attachmentMapper) {
		super.setDao(attachmentMapper);
		this.attachmentMapper = attachmentMapper;
	}
}
