package com.jf.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.NodeApproverCustomMapper;
import com.jf.dao.NodeApproverMapper;
import com.jf.entity.NodeApprover;
import com.jf.entity.NodeApproverCustom;
import com.jf.entity.NodeApproverCustomExample;
import com.jf.entity.NodeApproverExample;

@Service
@Transactional
public class NodeApproverService extends BaseService<NodeApprover, NodeApproverExample> {
	
	@Autowired
	private NodeApproverMapper nodeApproverMapper;
	
	@Autowired
	private NodeApproverCustomMapper nodeApproverCustomMapper;
	
	@Autowired
	public void setNodeApproverMapper(NodeApproverMapper nodeApproverMapper) {
		super.setDao(nodeApproverMapper);
		this.nodeApproverMapper = nodeApproverMapper;
	}
	
	public  List<NodeApproverCustom> selectCustomByExample(NodeApproverCustomExample example){
		return nodeApproverCustomMapper.selectCustomByExample(example);
	};
	
	public int countCustomByExample(NodeApproverCustomExample example){
		return nodeApproverCustomMapper.countCustomByExample(example);
	};
	

	public NodeApproverCustom selectCustomByPrimaryKey(Integer id){
		return nodeApproverCustomMapper.selectCustomByPrimaryKey(id);
	};

	
}
