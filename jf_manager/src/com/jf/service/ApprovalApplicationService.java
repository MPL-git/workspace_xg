package com.jf.service;

import com.gzs.common.utils.StringUtil;
import com.jf.dao.ApprovalApplicationCustomMapper;
import com.jf.dao.ApprovalApplicationLogMapper;
import com.jf.dao.ApprovalApplicationMapper;
import com.jf.dao.ApprovalApplicationNodeMapper;
import com.jf.dao.ApprovalApplicationPicMapper;
import com.jf.dao.NodeApproverMapper;
import com.jf.dao.ProcedureNodeMapper;
import com.jf.dao.SysStaffInfoCustomMapper;
import com.jf.entity.ApprovalApplication;
import com.jf.entity.ApprovalApplicationCustom;
import com.jf.entity.ApprovalApplicationCustomExample;
import com.jf.entity.ApprovalApplicationExample;
import com.jf.entity.ApprovalApplicationLog;
import com.jf.entity.ApprovalApplicationLogExample;
import com.jf.entity.ApprovalApplicationNode;
import com.jf.entity.ApprovalApplicationNodeExample;
import com.jf.entity.ApprovalApplicationPic;
import com.jf.entity.ApprovalApplicationPicExample;
import com.jf.entity.NodeApprover;
import com.jf.entity.NodeApproverExample;
import com.jf.entity.ProcedureNode;
import com.jf.entity.ProcedureNodeExample;
import com.jf.entity.SysStaffInfoCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ApprovalApplicationService extends BaseService<ApprovalApplication, ApprovalApplicationExample> {
	
	
	@Autowired
	private ApprovalApplicationMapper approvalApplicationMapper;
	
	@Autowired
	private ApprovalApplicationCustomMapper approvalApplicationCustomMapper;
	
	@Autowired
	private  ApprovalApplicationLogMapper  approvalApplicationLogMapper;
	
	@Autowired
	private ApprovalApplicationPicMapper approvalApplicationPicMapper;
	
	@Autowired
	private ProcedureNodeMapper procedureNodeMapper;
	
	@Autowired
	private ApprovalApplicationNodeMapper approvalApplicationNodeMapper;
	
	@Autowired
	private NodeApproverMapper nodeApproverMapper;
	
	@Autowired
	private SysStaffInfoCustomMapper sysStaffInfoCustomMapper;
	 
	@Autowired
	public void setApprovalApplicationMapper(ApprovalApplicationMapper approvalApplicationMapper) {
		super.setDao(approvalApplicationMapper);
		this.approvalApplicationMapper = approvalApplicationMapper;
	}
	
	public ApprovalApplicationCustom selectCustomByPrimaryKey(Integer id){
		return approvalApplicationCustomMapper.selectCustomByPrimaryKey(id);
	};
	
    public  List<ApprovalApplicationCustom> selectCustomsByExample(ApprovalApplicationCustomExample example){
    	 return approvalApplicationCustomMapper.selectCustomsByExample(example);
     };
     
     public  int countCustomsByExample(ApprovalApplicationCustomExample example){
    	 return approvalApplicationCustomMapper.countCustomByExample(example);
     };
     
     public  void insertApplicatoinAndLog(ApprovalApplication approvalApplication , String staffid ,List<String> picPathList){
    	  //添加我的申请
    	  approvalApplicationMapper.insertSelective(approvalApplication);
    	  
    	  //每个申请的图片创建一个实体
    	  if(picPathList!=null && picPathList.size()>0){
    	  for (int i = 0; i < picPathList.size(); i++) {
			ApprovalApplicationPic aap = new ApprovalApplicationPic();
			aap.setApprovalApplicationId(approvalApplication.getId());
			aap.setCreateBy(Integer.parseInt(staffid));
			aap.setCreateDate(new Date());
			aap.setPic(picPathList.get(i));
			approvalApplicationPicMapper.insertSelective(aap);
    	  }
		}
    	  

    	  //创建审批申请节点表（bu_approval_application_node）
    	  addApprovalApplicationAndNodeApprover(approvalApplication,staffid);
    		
    	//操作日志
    	recodeApplicationLog(approvalApplication,staffid,"创建");
    	 
     };
     
     public  void updateApplicatoinAndLog(ApprovalApplication approvalApplication , String staffid,List<String> picPathList){
      //修改我的申请
   	  approvalApplicationMapper.updateByPrimaryKeySelective(approvalApplication);

   	  //修改申请的图片
   	  ApprovalApplicationPicExample approvalApplicationPicExample = new ApprovalApplicationPicExample();
   	  approvalApplicationPicExample.createCriteria().andDelFlagEqualTo("0").andApprovalApplicationIdEqualTo(approvalApplication.getId());
      ApprovalApplicationPic approvalApplicationPic4Update = new ApprovalApplicationPic();
      approvalApplicationPic4Update.setDelFlag("1");
	  approvalApplicationPicMapper.updateByExampleSelective(approvalApplicationPic4Update, approvalApplicationPicExample);
	  
	  if(picPathList!=null && picPathList.size()>0){
	  for (int i = 0; i < picPathList.size(); i++) {
		  ApprovalApplicationPicExample aapExample = new ApprovalApplicationPicExample();
		  aapExample.createCriteria().andApprovalApplicationIdEqualTo(approvalApplication.getId()).andPicEqualTo(picPathList.get(i));
		  ApprovalApplicationPic aap4Update = new ApprovalApplicationPic();
		  aap4Update.setDelFlag("0");
		 int count = approvalApplicationPicMapper.updateByExampleSelective(aap4Update, aapExample);
		 if(count > 0){
			 continue;
		 }
			ApprovalApplicationPic aap = new ApprovalApplicationPic();
			aap.setApprovalApplicationId(approvalApplication.getId());
			aap.setCreateBy(Integer.parseInt(staffid));
			aap.setCreateDate(new Date());
			aap.setPic(picPathList.get(i));
			approvalApplicationPicMapper.insertSelective(aap);
		  }
		}
	  
	  	//修改节点和审批人的状态都为待审 (将原来的节点和审核人删除,重新再添加)
	  	//1.将原来的节点和审核人删除
	  	ApprovalApplicationNodeExample approvalApplicationNodeExample = new ApprovalApplicationNodeExample();
	  	approvalApplicationNodeExample.createCriteria().andDelFlagEqualTo("0").andApprovalApplicationIdEqualTo(approvalApplication.getId());
	  	ApprovalApplicationNode applicationNodes4Update  =  new ApprovalApplicationNode();
	  	applicationNodes4Update.setDelFlag("1");
	  	approvalApplicationNodeMapper.updateByExampleSelective(applicationNodes4Update, approvalApplicationNodeExample);//按申请表id查找进行删除
	  	
		List<ApprovalApplicationNode> applicationNodes4del = approvalApplicationNodeMapper.selectByExample(approvalApplicationNodeExample );//找出原来的所有节点将审核人删除
		if(applicationNodes4del!=null && applicationNodes4del.size()>0){
			for(ApprovalApplicationNode aan4Del : applicationNodes4del){
				String approvers4Del = aan4Del.getApprovers();
				if(!StringUtil.isEmpty(approvers4Del)){
					String[] approvers4DelArr = approvers4Del.split(",");
					for (int i = 0; i < approvers4DelArr.length; i++) {
						NodeApproverExample nodeApprover4DelExample = new NodeApproverExample();
						nodeApprover4DelExample.createCriteria().andNodeIdEqualTo(aan4Del.getId()).andApproverEqualTo(Integer.parseInt(approvers4DelArr[i]));
						NodeApprover nodeApprover4Del = new NodeApprover();
						nodeApprover4Del.setDelFlag("1");
						nodeApproverMapper.updateByExample(nodeApprover4Del , nodeApprover4DelExample);
					}
				}
			}
		}
	  
	  //2.重新再插入
	   addApprovalApplicationAndNodeApprover(approvalApplication,staffid);
	   
   	  //操作日志
	  recodeApplicationLog(approvalApplication,staffid,"修改");
   	 
    };
    
    //插入审批申请节点表和节点审批人表数据
    public void addApprovalApplicationAndNodeApprover(ApprovalApplication approvalApplication , String staffid){
	  	ProcedureNodeExample procedureNodeExample = new ProcedureNodeExample();
	  	
	  	procedureNodeExample.setOrderByClause("seq_no");//排序值排序
	  	procedureNodeExample.createCriteria().andDelFlagEqualTo("0").andProcedureIdEqualTo(approvalApplication.getProcedureId());
		List<ProcedureNode> procedureNodeList = procedureNodeMapper.selectByExample(procedureNodeExample );//按流程表中的节点进行添加
		for(ProcedureNode p : procedureNodeList){
			ApprovalApplicationNode aaNode = new ApprovalApplicationNode();
			aaNode.setApprovalApplicationId(approvalApplication.getId());
			aaNode.setName(p.getName());
			aaNode.setType(p.getType());
			aaNode.setApproverType(p.getApproverType());
			
			if("1".equals(p.getApproverType())){
				aaNode.setApprovers(p.getApprovers());//审批人
			}else if("0".equals(p.getApproverType())){
				   List<SysStaffInfoCustom> superiors = sysStaffInfoCustomMapper.selectSuperior(Integer.parseInt(staffid));
				   if(superiors!=null && superiors.size() > 0){//有上级
					   String approverString = "";
					   for(SysStaffInfoCustom ss : superiors){
						   if("".equals(approverString)){
							   approverString+=ss.getStaffId(); 
						   }else{
							   approverString+=","+ss.getStaffId();
						   }
					   }
					   aaNode.setApprovers(approverString);//审批人   
				   }else{ //自己已经是最顶级的
					   aaNode.setApprovers("");
				   }
			}
			aaNode.setSeqNo(p.getSeqNo());//排序值
			if("2".equals(p.getType())){//备用节点
				aaNode.setNeedApproval("0");//不需要审核
				aaNode.setApprovalStatus("1");//已审核通过,再选择需要审核时变成未审核
			}else{
				aaNode.setNeedApproval("1");//需要审核
			}
			aaNode.setApprovalStatus("0");//未审核
			aaNode.setCreateBy(Integer.parseInt(staffid));
			aaNode.setCreateDate(new Date());
			aaNode.setSteps(1);//当前审核步骤为1
			approvalApplicationNodeMapper.insertSelective(aaNode);
		  	
		    //创建节点审批人表（bu_node_approver）
			if("1".equals(aaNode.getNeedApproval())){//需要审核的节点
				if("1".equals(aaNode.getApproverType())){//指定人员
					String approverarr = aaNode.getApprovers();
				if(!StringUtil.isEmpty(approverarr)){
					String[] approvers =approverarr.split(",");
	    			for (int i = 0; i < approvers.length; i++) {	
	    				NodeApprover nodeApprover = new NodeApprover();
	    				nodeApprover.setNodeId(aaNode.getId());
	    				nodeApprover.setApprover(Integer.parseInt(approvers[i]));
	    				nodeApprover.setStatus("0");//未审核
	    				nodeApprover.setCreateBy(Integer.parseInt(staffid));
	    				nodeApprover.setCreateDate(new Date());
	    				nodeApproverMapper.insertSelective(nodeApprover);
	    				}
	    			}
			  }else{//上级
			   List<SysStaffInfoCustom> superiors = sysStaffInfoCustomMapper.selectSuperior(Integer.parseInt(staffid));
				if(superiors!=null && superiors.size() > 0){
					for(SysStaffInfoCustom ssf : superiors){
						NodeApprover nodeApprover = new NodeApprover();
	    				nodeApprover.setNodeId(aaNode.getId());
	    				nodeApprover.setApprover(ssf.getStaffId());
	    				nodeApprover.setStatus("0");//未审核
	    				nodeApprover.setCreateBy(Integer.parseInt(staffid));
	    				nodeApprover.setCreateDate(new Date());
	    				nodeApproverMapper.insertSelective(nodeApprover);
					}
				}
			}
		  }
		}
    }
    
    
    
    //修改操作日志
    public void recodeApplicationLog(ApprovalApplication approvalApplication,String staffid,String operation){
   	  ApprovalApplicationLog applicationLog = new ApprovalApplicationLog();
   	  applicationLog.setApprovalApplicationId(approvalApplication.getId());
   	  applicationLog.setOperation(operation);
   	  applicationLog.setApprovalNode(" ");
   	  applicationLog.setStatus(approvalApplication.getStatus());
	  applicationLog.setCreateBy(Integer.parseInt(staffid));
	  applicationLog.setCreateDate(new Date());
   	  approvalApplicationLogMapper.insertSelective(applicationLog);
    }
    
    
    //删除我的申请
    public void delMyApplication(ApprovalApplication approvalApplication,String staffid ){
		//审核申请表删除
		approvalApplication.setStatus("4");
		approvalApplicationMapper.updateByPrimaryKeySelective(approvalApplication);
		
/*		//审核申请节点删除
		ApprovalApplicationNodeExample approvalApplicationNodeExample = new ApprovalApplicationNodeExample();
		approvalApplicationNodeExample.createCriteria().andDelFlagEqualTo("0").andApprovalApplicationIdEqualTo(approvalApplication.getId());
		List<ApprovalApplicationNode> approvalApplicationNodes = approvalApplicationNodeMapper.selectByExample(approvalApplicationNodeExample);
		if(approvalApplicationNodes!=null && approvalApplicationNodes.size()>0){
		for(ApprovalApplicationNode aan : approvalApplicationNodes){
			aan.setDelFlag("1");
			approvalApplicationNodeMapper.updateByPrimaryKeySelective(aan);
			//审核人表的删除
			NodeApproverExample nodeExample = new NodeApproverExample();
			nodeExample.createCriteria().andDelFlagEqualTo("0").andNodeIdEqualTo(aan.getId());
			NodeApprover nodeApprover4Del = new NodeApprover();
			nodeApprover4Del.setDelFlag("1");
			nodeApproverMapper.updateByExampleSelective(nodeApprover4Del, nodeExample );
			
		 }
		}*/
		//操作日志
		  recodeApplicationLog(approvalApplication,staffid,"删除");
    }
    
    //提审我的申请
    public void arraignment(ApprovalApplication approvalApplication,String staffid ){
    	//提审
    	approvalApplication.setStatus("1");
    	approvalApplication.setCommitDate(new Date());//提审时间
    	approvalApplicationMapper.updateByPrimaryKeySelective(approvalApplication);
    	//操作日志
		  recodeApplicationLog(approvalApplication,staffid,"提审");
    }
    
    //恢复我的申请
    public void recovery(ApprovalApplication approvalApplication,String staffid ){
    	//审核申请表恢复
    	ApprovalApplicationLogExample logExample = new ApprovalApplicationLogExample();
    	logExample.createCriteria().andDelFlagEqualTo("0").andApprovalApplicationIdEqualTo(approvalApplication.getId());
		List<ApprovalApplicationLog> approvalApplicationLog = approvalApplicationLogMapper.selectByExample(logExample );
		ApprovalApplicationLog approvalLast2Status = approvalApplicationLog.get(approvalApplicationLog.size()-2);
		approvalApplication.setStatus(approvalLast2Status.getStatus());
		approvalApplicationMapper.updateByPrimaryKeySelective(approvalApplication);
		
/*		//审核申请节点恢复
		ApprovalApplicationNodeExample approvalApplicationNodeExample = new ApprovalApplicationNodeExample();
		approvalApplicationNodeExample.createCriteria().andDelFlagEqualTo("1").andApprovalApplicationIdEqualTo(approvalApplication.getId());
		List<ApprovalApplicationNode> approvalApplicationNodes = approvalApplicationNodeMapper.selectByExample(approvalApplicationNodeExample);
		if(approvalApplicationNodes!=null && approvalApplicationNodes.size()>0){
		for(ApprovalApplicationNode aan : approvalApplicationNodes){
			aan.setDelFlag("0");
			approvalApplicationNodeMapper.updateByPrimaryKeySelective(aan);
			//审核人表的恢复
			NodeApproverExample nodeExample = new NodeApproverExample();
			nodeExample.createCriteria().andDelFlagEqualTo("1").andNodeIdEqualTo(aan.getId());
			NodeApprover nodeApprover4Del = new NodeApprover();
			nodeApprover4Del.setDelFlag("0");
			nodeApproverMapper.updateByExampleSelective(nodeApprover4Del, nodeExample );
			
		 }
		}*/
    	
    	//操作日志
		  recodeApplicationLog(approvalApplication,staffid,"恢复");
    }

}
