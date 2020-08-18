package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SubOrderAttachmentCustomMapper;
import com.jf.dao.SubOrderAttachmentMapper;
import com.jf.entity.SubOrderAttachment;
import com.jf.entity.SubOrderAttachmentCustom;
import com.jf.entity.SubOrderAttachmentExample;

@Service
@Transactional
public class SubOrderAttachmentService extends BaseService<SubOrderAttachment,SubOrderAttachmentExample> {
	@Autowired
	private SubOrderAttachmentMapper subOrderAttachmentMapper;
	@Autowired
	private SubOrderAttachmentCustomMapper subOrderAttachmentCustomMapper;
	
	@Autowired
	public void setOrderLogMapper(SubOrderAttachmentMapper subOrderAttachmentMapper) {
		super.setDao(subOrderAttachmentMapper);
		this.subOrderAttachmentMapper = subOrderAttachmentMapper;
	}
	
    public List<SubOrderAttachmentCustom> selectSubOrderAttachmentCustomByExample(SubOrderAttachmentExample example){
    	return subOrderAttachmentCustomMapper.selectByExample(example);
    }
}
