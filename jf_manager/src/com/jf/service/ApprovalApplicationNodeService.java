package com.jf.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.util.Base64;
import com.gzs.common.utils.StringUtil;
import com.jf.dao.ApprovalApplicationLogMapper;
import com.jf.dao.ApprovalApplicationMapper;
import com.jf.dao.ApprovalApplicationNodeCustomMapper;
import com.jf.dao.ApprovalApplicationNodeMapper;
import com.jf.dao.NodeApproverMapper;
import com.jf.dao.SysStaffInfoCustomMapper;
import com.jf.entity.ApprovalApplication;
import com.jf.entity.ApprovalApplicationLog;
import com.jf.entity.ApprovalApplicationNode;
import com.jf.entity.ApprovalApplicationNodeCustom;
import com.jf.entity.ApprovalApplicationNodeCustomExample;
import com.jf.entity.ApprovalApplicationNodeExample;
import com.jf.entity.ApprovalApplicationNodeExample.Criteria;
import com.jf.entity.NodeApprover;
import com.jf.entity.NodeApproverExample;
import com.jf.entity.SysStaffInfoCustom;

@Service
@Transactional
public class ApprovalApplicationNodeService extends BaseService<ApprovalApplicationNode, ApprovalApplicationNodeExample> {
	
	
	@Autowired
	private ApprovalApplicationNodeMapper approvalApplicationNodeMapper;
	
	@Autowired
	private ApprovalApplicationLogMapper approvalApplicationLogMapper;
	
	@Autowired
	private NodeApproverMapper nodeApproverMapper;
	
	@Autowired
	private  ApprovalApplicationMapper approvalApplicationMapper;
	
	@Autowired
	private SysStaffInfoCustomMapper sysStaffInfoCustomMapper;
	
	@Autowired
	private ApprovalApplicationNodeCustomMapper approvalApplicationNodeCustomMapper;
	
	
	@Autowired
	public void setApprovalApplicationNodeMapper(ApprovalApplicationNodeMapper approvalApplicationNodeMapper) {
		super.setDao(approvalApplicationNodeMapper);
		this.approvalApplicationNodeMapper = approvalApplicationNodeMapper;
	}
	
	 public List<ApprovalApplicationNodeCustom> selectCustomByExample(ApprovalApplicationNodeCustomExample example){
		 return approvalApplicationNodeCustomMapper.selectCustomByExample(example);
	 };
	
	public List<ApprovalApplicationNodeCustom> findToExamineCustom(HashMap<String, Object> paramMap){
		return approvalApplicationNodeCustomMapper.findToExamineCustom(paramMap);
	}
	
	public int countToExamineCustom(HashMap<String, Object> paramMap){
		return approvalApplicationNodeCustomMapper.countToExamineCustom(paramMap);
	}
	
	public int selectNowMaxSeqNo(Integer approvalApplicationId){
		return approvalApplicationNodeCustomMapper.selectNowMaxSeqNo(approvalApplicationId);
	}
	
	
	public  int selectMinCoreSeqNo(HashMap<String, Object> paramMap){
		return approvalApplicationNodeCustomMapper.selectMinCoreSeqNo(paramMap);
	}; 
	
		//保存通过审核
		public void savePassApprovalAndLog(HttpServletRequest request,String staffId) throws UnsupportedEncodingException{
			String graphicContent = request.getParameter("content");//富文本内容
			String standbys = request.getParameter("standbys");//备选节点ID
			String approvalRemark = request.getParameter("approvalRemark");//备注
			String nodeApproverId = request.getParameter("nodeApproverId");//审核人主键id
			if(!StringUtil.isEmpty(nodeApproverId)){
				NodeApprover nodeApprover = nodeApproverMapper.selectByPrimaryKey(Integer.parseInt(nodeApproverId));
					//审核人表 通过
					nodeApprover.setStatus("1");
					nodeApprover.setUpdateBy(Integer.parseInt(staffId));
					nodeApprover.setUpdateDate(new Date());
					nodeApproverMapper.updateByPrimaryKeySelective(nodeApprover);
					
					//判断有没有选中备选,有选择则增加审核节点    (存在并且为不需要审核的才能增加为需要审核的)
					if(!StringUtil.isEmpty(standbys)){
						JSONArray standbysJson = JSONArray.fromObject(standbys);				
						List<String> standbyList = (List<String>) JSONArray.toCollection(standbysJson, String.class);//备选节点名字集合
						for(String s : standbyList){
							ApprovalApplicationNodeExample aanExample = new ApprovalApplicationNodeExample();
							aanExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(s)).andNeedApprovalEqualTo("0");
							List<ApprovalApplicationNode> aan4Update = approvalApplicationNodeMapper.selectByExample(aanExample);
							if(aan4Update!=null && aan4Update.size()>0){
								aan4Update.get(0).setNeedApproval("1");//需要审核
								aan4Update.get(0).setApprovalStatus("0");//变成未审核
								approvalApplicationNodeMapper.updateByPrimaryKeySelective(aan4Update.get(0));//更改为需要审核
								//给备用节点创建审核节点和审核人
								ApprovalApplicationNode approvalApplicationNode = aan4Update.get(0);
				    			if("1".equals(approvalApplicationNode.getApproverType())){
				    				String[] approvers = approvalApplicationNode.getApprovers().split(",");
				    				if(approvers != null && approvers.length>0){
				    	    			for (int i = 0; i < approvers.length; i++) {	
				    	    				NodeApprover na = new NodeApprover();
				    	    				na.setNodeId(approvalApplicationNode.getId());
				    	    				na.setApprover(Integer.parseInt(approvers[i]));
				    	    				na.setStatus("0");//未审核
				    	    				na.setCreateBy(Integer.parseInt(staffId));
				    	    				na.setCreateDate(new Date());
				    	    				nodeApproverMapper.insertSelective(na);
				    	    				}
				    	    			}
				    			}else{
				    				List<SysStaffInfoCustom> superiors = sysStaffInfoCustomMapper.selectSuperior(Integer.parseInt(staffId));
				    				if(superiors!=null && superiors.size() > 0){
				    					for(SysStaffInfoCustom ssf : superiors){
				    						NodeApprover na = new NodeApprover();
				    						na.setNodeId(approvalApplicationNode.getId());
				    						na.setApprover(ssf.getStaffId());
				    						na.setStatus("0");//未审核
				    						na.setCreateBy(Integer.parseInt(staffId));
				    						na.setCreateDate(new Date());
				    	    				nodeApproverMapper.insertSelective(na);
				    					}
				    				}
				    			}
							}
						}
					}
					
					//审核节点表,判断是否该节点所有人都审核通过,是则改变节点状态为通过,并且设置当前审核步骤  steps
					NodeApproverExample nodeApproverExample = new NodeApproverExample();
					nodeApproverExample.createCriteria().andNodeIdEqualTo(nodeApprover.getNodeId()).andDelFlagEqualTo("0").andStatusNotEqualTo("1");
					int rejectCcount = nodeApproverMapper.countByExample(nodeApproverExample);
					if(rejectCcount<=0){//驳回数量为0  则为都通过
						//设置当前节点为通过
						ApprovalApplicationNode approvalApplicationNode = approvalApplicationNodeMapper.selectByPrimaryKey(nodeApprover.getNodeId());
						approvalApplicationNode.setApprovalStatus("1");
						approvalApplicationNodeMapper.updateByPrimaryKeySelective(approvalApplicationNode);
						
						Integer steps = approvalApplicationNode.getSteps();
						
						HashMap<String, Object> parHsMap = new HashMap<String, Object>();
						parHsMap.put("approvalApplicationId", approvalApplicationNode.getApprovalApplicationId());
						parHsMap.put("seqNo", approvalApplicationNode.getSeqNo());
						int minCoreSeqNo = approvalApplicationNodeCustomMapper.selectMinCoreSeqNo(parHsMap);
						
						//判断之间有没有需要审核的备用节点
						ApprovalApplicationNodeExample needExamineExample = new ApprovalApplicationNodeExample();
						Criteria createCriteria = needExamineExample.createCriteria();
						createCriteria.andDelFlagEqualTo("0").andNeedApprovalEqualTo("1").andTypeEqualTo("2").andApprovalApplicationIdEqualTo(approvalApplicationNode.getApprovalApplicationId());
						if(minCoreSeqNo!=0){
							/*createCriteria.andSeqNoBetween(approvalApplicationNode.getSeqNo(), minCoreSeqNo);*/
							createCriteria.andSeqNoGreaterThan(approvalApplicationNode.getSeqNo());
							createCriteria.andSeqNoLessThanOrEqualTo(minCoreSeqNo);
						}else{
							createCriteria.andSeqNoGreaterThan(approvalApplicationNode.getSeqNo());
							minCoreSeqNo=approvalApplicationNode.getSeqNo();
						}
						List<ApprovalApplicationNode> needExamineStandbyNode = approvalApplicationNodeMapper.selectByExample(needExamineExample);
						if(needExamineStandbyNode!=null&&needExamineStandbyNode.size()>0){
							steps = needExamineStandbyNode.get(0).getSeqNo();
						}else{
							steps=minCoreSeqNo;
						}
						//更改当前流程所有节点的steps
						ApprovalApplicationNodeExample applicationNode = new ApprovalApplicationNodeExample();
						applicationNode.createCriteria().andDelFlagEqualTo("0").andApprovalApplicationIdEqualTo(approvalApplicationNode.getApprovalApplicationId());
						ApprovalApplicationNode  aan4Steps = new ApprovalApplicationNode();
						aan4Steps.setSteps(steps);
						approvalApplicationNodeMapper.updateByExampleSelective(aan4Steps, applicationNode);
						
						
						//需要审核的审核节点表全通过,则审核表通过
						ApprovalApplicationNodeExample approvalApplicationNodeExample = new ApprovalApplicationNodeExample();
						approvalApplicationNodeExample.createCriteria().andDelFlagEqualTo("0").andNeedApprovalEqualTo("1").andApprovalApplicationIdEqualTo(approvalApplicationNode.getApprovalApplicationId()).andApprovalStatusNotEqualTo("1");
						int nodeRejectExample = approvalApplicationNodeMapper.countByExample(approvalApplicationNodeExample );
						if(nodeRejectExample<=0){//不为1 (通过) 的人数为0
							ApprovalApplication approvalApplication = approvalApplicationMapper.selectByPrimaryKey(approvalApplicationNode.getApprovalApplicationId());
							approvalApplication.setStatus("2");
							approvalApplicationMapper.updateByPrimaryKeySelective(approvalApplication);
						}
						
					}
					//日志表
					ApprovalApplicationLog aaLog = new ApprovalApplicationLog();
					ApprovalApplicationNode approvalApplicationNode4Log = approvalApplicationNodeMapper.selectByPrimaryKey(nodeApprover.getNodeId());
					ApprovalApplication aa = approvalApplicationMapper.selectByPrimaryKey(approvalApplicationNode4Log.getApprovalApplicationId());
					aaLog.setApprovalApplicationId(approvalApplicationNode4Log.getApprovalApplicationId());//审批申请id
					aaLog.setOperation("通过");//操作
					aaLog.setStatus(aa.getStatus());//审核表现在的审核状态
					aaLog.setApprovalNode(approvalApplicationNode4Log.getName());//审批节点
					if(!StringUtil.isEmpty(approvalRemark)){
						aaLog.setApprovalRemarks(approvalRemark);//审批备注
					}
					if(!StringUtil.isEmpty(graphicContent)){
						aaLog.setApprovalPic(graphicContent);//审批图片
					}
					aaLog.setCreateBy(Integer.parseInt(staffId));
					aaLog.setCreateDate(new Date());
					approvalApplicationLogMapper.insertSelective(aaLog);
				
			}
		}
		
		
		//保存驳回审核
		public void saveRejectApprovalAndLog(HttpServletRequest request,String staffId) {
			String nodeApproverId = request.getParameter("nodeApproverId");//审核人
			if(!StringUtil.isEmpty(nodeApproverId)){
			//审核人驳回
			NodeApprover nodeApprover = nodeApproverMapper.selectByPrimaryKey(Integer.parseInt(nodeApproverId));
			nodeApprover.setStatus("2");
			nodeApproverMapper.updateByPrimaryKeySelective(nodeApprover);
			
			//审核节点驳回
			ApprovalApplicationNode applicationNode = approvalApplicationNodeMapper.selectByPrimaryKey(nodeApprover.getNodeId());
			applicationNode.setApprovalStatus("2");
			approvalApplicationNodeMapper.updateByPrimaryKeySelective(applicationNode);
			
			//审核表驳回
			ApprovalApplication approvalApplication = approvalApplicationMapper.selectByPrimaryKey(applicationNode.getApprovalApplicationId());
			approvalApplication.setStatus("3");
			approvalApplicationMapper.updateByPrimaryKeySelective(approvalApplication);
			
			
			//日志表
			String graphicContent = request.getParameter("content");//富文本内容
			String approvalRemark = request.getParameter("approvalRemark");//备注
			ApprovalApplicationLog aaLog = new ApprovalApplicationLog();
			ApprovalApplicationNode approvalApplicationNode4Log = approvalApplicationNodeMapper.selectByPrimaryKey(nodeApprover.getNodeId());
			ApprovalApplication aa = approvalApplicationMapper.selectByPrimaryKey(approvalApplicationNode4Log.getApprovalApplicationId());
			aaLog.setApprovalApplicationId(approvalApplicationNode4Log.getApprovalApplicationId());//审批申请id
			aaLog.setOperation("驳回");//操作
			aaLog.setStatus(aa.getStatus());//审核表现在的审核状态
			aaLog.setApprovalNode(approvalApplicationNode4Log.getName());//审批节点
			if(!StringUtil.isEmpty(approvalRemark)){
				aaLog.setApprovalRemarks(approvalRemark);//审批备注
			}
			if(!StringUtil.isEmpty(graphicContent)){
				aaLog.setApprovalPic(graphicContent);//审批图片
			}
			aaLog.setCreateBy(Integer.parseInt(staffId));
			aaLog.setCreateDate(new Date());
			approvalApplicationLogMapper.insertSelective(aaLog);
			}
			
		}

}
