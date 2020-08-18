package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ProcedureNodeCustomMapper;
import com.jf.dao.ProcedureNodeMapper;
import com.jf.entity.ProcedureNode;
import com.jf.entity.ProcedureNodeCustom;
import com.jf.entity.ProcedureNodeExample;

@Service
@Transactional
public class ProcedureNodeService extends BaseService<ProcedureNode, ProcedureNodeExample> {
	
	@Autowired
	private ProcedureNodeMapper procedureNodeMapper;
	
	@Autowired
	private ProcedureNodeCustomMapper procedureNodeCustomMapper;
	
	@Autowired
	public void setProcedureNodeMapper(ProcedureNodeMapper procedureNodeMapper) {
		super.setDao(procedureNodeMapper);
		this.procedureNodeMapper = procedureNodeMapper;
	}
	

	public  List<ProcedureNodeCustom> selectCustomsByExample(ProcedureNodeExample example){
		return procedureNodeCustomMapper.selectCustomsByExample(example);
	};

	public void delNode(Integer procedureNodeId){
		ProcedureNode procedureNode = procedureNodeMapper.selectByPrimaryKey(procedureNodeId);
		Integer seqNo = procedureNode.getSeqNo();//排序值
		Integer procedureId = procedureNode.getProcedureId();//流程ID
		
		ProcedureNodeExample example  = new ProcedureNodeExample();
		example.createCriteria().andProcedureIdEqualTo(procedureId).andDelFlagEqualTo("0");
		List<ProcedureNode> procedureNodesList = procedureNodeMapper.selectByExample(example);
		for(ProcedureNode p : procedureNodesList){
			if(p.getSeqNo() > seqNo){
				p.setSeqNo(p.getSeqNo()-1);
				procedureNodeMapper.updateByPrimaryKeySelective(p);
			}
		}
		procedureNode.setDelFlag("1");
		procedureNodeMapper.updateByPrimaryKeySelective(procedureNode);
		
	}

}
