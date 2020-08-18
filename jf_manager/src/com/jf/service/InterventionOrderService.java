package com.jf.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzs.common.utils.StringUtil;
import com.jf.dao.ClientServiceCommentMapper;
import com.jf.dao.InterventionOrderCustomMapper;
import com.jf.dao.InterventionOrderLogMapper;
import com.jf.dao.InterventionOrderMapper;
import com.jf.dao.InterventionOrderStatusLogMapper;
import com.jf.dao.InterventionProcessMapper;
import com.jf.dao.SysAppMessageMapper;
import com.jf.entity.ClientServiceComment;
import com.jf.entity.InterventionOrder;
import com.jf.entity.InterventionOrderCustom;
import com.jf.entity.InterventionOrderCustomExample;
import com.jf.entity.InterventionOrderExample;
import com.jf.entity.InterventionOrderLog;
import com.jf.entity.InterventionOrderStatusLog;
import com.jf.entity.InterventionProcess;
import com.jf.entity.StaffBean;
import com.jf.entity.SysAppMessage;

@Service
@Transactional
public class InterventionOrderService extends BaseService<InterventionOrder, InterventionOrderExample> {

	@Autowired
	private InterventionOrderMapper interventionOrderMapper;
	
	@Autowired
	private InterventionOrderCustomMapper interventionOrderCustomMapper;
	
	@Autowired
	private InterventionOrderStatusLogMapper interventionOrderStatusLogMapper;
	
	@Autowired
	private InterventionOrderLogMapper interventionOrderLogMapper;
	
	@Autowired
	private ClientServiceCommentMapper clientServiceCommentMapper;
	
	@Autowired
	private InterventionProcessMapper interventionProcessMapper;
	
	@Autowired
	private SysAppMessageMapper sysAppMessageMapper;
	
	@Autowired
	public void setInterventionOrderMapper(InterventionOrderMapper interventionOrderMapper) {
		super.setDao(interventionOrderMapper);
		this.interventionOrderMapper = interventionOrderMapper;
	}
	
	public List<Map<String, Object>> getPlatformProcessorList() {
		return interventionOrderCustomMapper.getPlatformProcessorList();
	}
	
	public int countByCustomExample(InterventionOrderCustomExample example) {
		return interventionOrderCustomMapper.countByCustomExample(example);
	}
	
	public List<InterventionOrderCustom> selectByCustomExample(InterventionOrderCustomExample example) {
		return interventionOrderCustomMapper.selectByCustomExample(example);
	}
	
	public InterventionOrderCustom selectByPrimaryKeyCustom(Integer id) {
		return interventionOrderCustomMapper.selectByPrimaryKeyCustom(id);
	}
	
	
	public Map<String, Object> updateInterventionOrder(HttpServletRequest request, String statusFlag, StaffBean staffBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Integer interventionOrderId = Integer.parseInt(request.getParameter("interventionOrderId"));
		InterventionOrder interventionOr = interventionOrderMapper.selectByPrimaryKey(interventionOrderId);
		String statusStr = "";
		Integer staffId = Integer.parseInt(staffBean.getStaffID());
		Date date = new Date();
		String remarks = "";
		String content = "";
		InterventionOrder interventionOrder = new InterventionOrder();
		interventionOrder.setId(interventionOrderId);
		interventionOrder.setUpdateDate(date);
		interventionOrder.setUpdateBy(staffId);
		if(!StringUtil.isEmpty(statusFlag) && "1".equals(statusFlag)) { //领取
			interventionOrder.setPlatformProcessor(staffId);
			interventionOrder.setStatus("1"); //待处理
			remarks = "平台已领取，状态变更为待处理";
			content = "领取申请";
			map.put("platformProcessor", staffBean.getStaffName());
			map.put("updateDate", sdf.format(date));
			this.saveInterventionOrderStatusLog(interventionOrderId, "1", staffId, date, remarks);
			this.saveSysAppMessage("您申请的介入单，平台受理中，请耐心等待！", interventionOrderId, interventionOr.getCreateBy(), date, staffId);
		}else if(!StringUtil.isEmpty(statusFlag) && "2".equals(statusFlag)) { //待处理介入详情
			String status = request.getParameter("status");
			interventionOrder.setStatus(status);
			if(!StringUtil.isEmpty(status) && "2".equals(status)) { //2 待复审(客服驳回客户介入申请)
				interventionOrder.setRejectReason(request.getParameter("rejectReason"));
				interventionOrder.setPlatformRemarks(request.getParameter("platformRemarks"));
				remarks = "平台已处理，状态变更为待复审(客服驳回客户介入申请)";
				content = "驳回申请";
			}else if(!StringUtil.isEmpty(status) && "4".equals(status)) { //4 待商家申诉(客服同意客户介入申请)
				remarks = "平台已处理，状态变更为待商家申诉(客服同意客户介入申请)";
				content = "受理申请";
			}
			statusStr = status;
			this.saveInterventionOrderStatusLog(interventionOrderId, statusStr, staffId, date, remarks);
		}else if(!StringUtil.isEmpty(statusFlag) && "3".equals(statusFlag)) { //待处理（复审后）
			interventionOrder.setStatus(request.getParameter("directorStatus"));
			interventionOrder.setDirectorReason("");
			interventionOrder.setDirectorRemarks("");
			remarks = "重新受理，清空主管不同意驳回理由、主管内部备注";
			content = "重新受理";
		}else if(!StringUtil.isEmpty(statusFlag) && "4".equals(statusFlag)) { //评判详情
			String winType = request.getParameter("winType");
			interventionOrder.setWinType(winType);
			interventionOrder.setClientResultReason(request.getParameter("clientResultReason"));
			interventionOrder.setMchtResultReason(request.getParameter("mchtResultReason"));
			interventionOrder.setPlatformRemarks(request.getParameter("platformRemarks"));
			if(interventionOr.getProStatus().equals("B4") || interventionOr.getProStatus().equals("C4")){
				interventionOrder.setStatus("6");//待商家上传凭证
				statusStr = "6";
				remarks = "平台已评判，状态变更为待商家上传凭证（买家胜诉）";
			}else{
				if(!StringUtil.isEmpty(winType) && "1".equals(winType)) { //1.买家
					interventionOrder.setStatus("6");
					statusStr = "6";
					remarks = "平台已评判，状态变更为待商家上传凭证（买家胜诉）";
				}else if(!StringUtil.isEmpty(winType) && "2".equals(winType)) { //2.商家
					interventionOrder.setStatus("7");
					statusStr = "7";
					remarks = "平台已评判，状态变更为待结案";
				}
			}
			content = "提交评判";
			this.saveInterventionOrderStatusLog(interventionOrderId, statusStr, staffId, date, remarks);
		}else if(!StringUtil.isEmpty(statusFlag) && "5".equals(statusFlag)) { //待结案介入详情
			String status = request.getParameter("status");
			interventionOrder.setStatus(status);
			statusStr = status;
			if(!StringUtil.isEmpty(status) && "8".equals(status)) { //8 已结案
				interventionOrder.setRecordOfCases(request.getParameter("recordOfCases"));
				remarks = "平台已结案，状态变更为已结案";
				content = "提交结案";
				this.saveSysAppMessage("您申请的介入单，平台受理完成，请耐心等待！", interventionOrderId, interventionOr.getCreateBy(), date, staffId);
			}else if(!StringUtil.isEmpty(status) && "6".equals(status)) { //6 待商家上传凭证（买家胜诉）
				remarks = "平台已评判，状态变更为待商家上传凭证（买家胜诉）";
				content = "结案驳回";
				InterventionProcess interventionProcess = new InterventionProcess();
				interventionProcess.setInterventionOrderId(interventionOrderId);
				interventionProcess.setOperatorType("3");
				interventionProcess.setProcessType("3");
				interventionProcess.setContent(request.getParameter("recordOfCases"));
				interventionProcess.setCreateBy(staffId);
				interventionProcess.setCreateDate(date);
				interventionProcess.setUpdateDate(date);
				interventionProcess.setDelFlag("0");
				interventionProcessMapper.insertSelective(interventionProcess);
			}
			this.saveInterventionOrderStatusLog(interventionOrderId, statusStr, staffId, date, remarks);
		}else if(!StringUtil.isEmpty(statusFlag) && "6".equals(statusFlag)) { //待复审审核详情
			String directorStatus = request.getParameter("directorStatus");
			interventionOrder.setStatus(directorStatus);
			statusStr = directorStatus;
			if(!StringUtil.isEmpty(directorStatus) && "1".equals(directorStatus)) { //1 待处理
				interventionOrder.setDirectorReason(request.getParameter("directorReason"));
				interventionOrder.setDirectorRemarks(request.getParameter("directorRemarks"));
				remarks = "平台已复审，状态变更为待处理";
				content = "复审不同意驳回";
			}else if(!StringUtil.isEmpty(directorStatus) && "3".equals(directorStatus)) { //3复审驳回
				remarks = "平台已复审，状态变更为复审驳回";
				content = "复审同意驳回";
				this.saveSysAppMessage("您申请的介入单，被驳回，请及时修改！", interventionOrderId, interventionOr.getCreateBy(), date, staffId);
			}
			this.saveInterventionOrderStatusLog(interventionOrderId, statusStr, staffId, date, remarks);
		}else if(!StringUtil.isEmpty(statusFlag) && "7".equals(statusFlag)) { //受理
			interventionOrder.setPlatformProcessor(staffId);
			remarks = "更改平台处理人，平台处理人更为"+staffBean.getStaffName();
			content = "更改平台处理人，平台处理人更为"+staffBean.getStaffName();
			map.put("platformProcessor", staffBean.getStaffName());
			map.put("updateDate", sdf.format(date));
		}else if(!StringUtil.isEmpty(statusFlag) && "8".equals(statusFlag)) { //未发起详情（未发起主管权限）
			String isInitiateViolate = request.getParameter("isInitiateViolate");
			interventionOrder.setIsInitiateViolate(isInitiateViolate);
			if(!StringUtil.isEmpty("isInitiateViolate") && "0".equals(isInitiateViolate)) { //0 否
				remarks = "是否发起违规，未发起违规";
				content = "未发起违规";
			}else if(!StringUtil.isEmpty("isInitiateViolate") && "1".equals(isInitiateViolate)) { //1 是
				remarks = "是否发起违规，已发起违规";
				content = "已发起违规";
			}
			map.put("isInitiateViolate", isInitiateViolate);
			map.put("updateDate", sdf.format(date));
		}
		interventionOrderMapper.updateByPrimaryKeySelective(interventionOrder);
		this.saveInterventionOrderLog(interventionOrderId, staffId, content, date, remarks);
		return map;
	}
	
	/**
	 * 
	 * @Title saveInterventionOrderStatusLog   
	 * @Description TODO(保存介入单状态流水)   
	 * @author pengl
	 * @date 2018年4月9日 上午10:20:18
	 */
	public void saveInterventionOrderStatusLog(Integer interventionOrderId, String statusStr, Integer staffId, Date date, String remarks) {
		InterventionOrderStatusLog interventionOrderStatusLog = new InterventionOrderStatusLog();
		interventionOrderStatusLog.setInterventionOrderId(interventionOrderId);
		interventionOrderStatusLog.setStatus(statusStr);
		interventionOrderStatusLog.setCreateBy(staffId);
		interventionOrderStatusLog.setCreateDate(date);
		interventionOrderStatusLog.setRemarks(remarks);
		interventionOrderStatusLog.setDelFlag("0");
		interventionOrderStatusLogMapper.insert(interventionOrderStatusLog); //保存日志
	}
	
	/**
	 * 
	 * @Title saveInterventionOrderLog   
	 * @Description TODO(保存介入单客服记录表)   
	 * @author pengl
	 * @date 2018年4月9日 下午2:50:05
	 */
	public void saveInterventionOrderLog(Integer interventionOrderId, Integer staffId, String content, Date date, String remarks) {
		InterventionOrderLog interventionOrderLog = new InterventionOrderLog();
		interventionOrderLog.setInterventionOrderId(interventionOrderId);
		interventionOrderLog.setStaffId(staffId);
		interventionOrderLog.setContent(content);
		interventionOrderLog.setCreateBy(staffId);
		interventionOrderLog.setCreateDate(date);
		interventionOrderLog.setRemarks(remarks);
		interventionOrderLog.setDelFlag("0");
		interventionOrderLogMapper.insert(interventionOrderLog);
	}
	
	public void saveClientServiceComment(HttpServletRequest request, StaffBean staffBean) {
		ClientServiceComment clientServiceComment = new ClientServiceComment();
		clientServiceComment.setInterventionOrderId(Integer.parseInt(request.getParameter("interventionOrderId")));
		clientServiceComment.setStaffId(Integer.parseInt(staffBean.getStaffID()));
		clientServiceComment.setCommentType(request.getParameter("commentType"));
		clientServiceComment.setContent(request.getParameter("content"));
		clientServiceComment.setCreateBy(Integer.parseInt(staffBean.getStaffID()));
		clientServiceComment.setCreateDate(new Date());
		clientServiceComment.setDelFlag("0");
		clientServiceCommentMapper.insertSelective(clientServiceComment);
		InterventionOrder interventionOrder = new InterventionOrder();
		interventionOrder.setId(Integer.parseInt(request.getParameter("interventionOrderId")));
		interventionOrder.setIsComment("1");
		interventionOrderMapper.updateByPrimaryKeySelective(interventionOrder);
	}
	
	/**
	 * 
	 * @Title saveSysAppMessage   
	 * @Description TODO(app消息记录表)   
	 * @author pengl
	 * @date 2018年4月18日 下午5:29:38
	 */
	public void saveSysAppMessage(String content, Integer interventionOrderId, Integer memberId, Date date, Integer staffId) {
		SysAppMessage sysAppMessage = new SysAppMessage();
		sysAppMessage.setType("2");
		sysAppMessage.setTitle("11");
		sysAppMessage.setContent(content);
		sysAppMessage.setLinkType("8");
		sysAppMessage.setLinkId(interventionOrderId);
		sysAppMessage.setPushFlag("0");
		sysAppMessage.setMemberId(memberId);
		sysAppMessage.setCreateBy(staffId);
		sysAppMessage.setCreateDate(date);
		sysAppMessage.setUpdateDate(date);
		sysAppMessage.setDelFlag("0");
		sysAppMessageMapper.insertSelective(sysAppMessage);
	}
	
}
