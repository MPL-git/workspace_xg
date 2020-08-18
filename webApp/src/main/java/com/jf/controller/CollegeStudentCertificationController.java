package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DataDicUtil;
import com.jf.entity.MemberCollegeStudentCertification;
import com.jf.entity.MemberCollegeStudentCertificationExample;
import com.jf.service.MemberCollegeStudentCertificationService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Pengl
 * @create 2020-03-31 上午 9:41
 */
@Controller
public class CollegeStudentCertificationController extends BaseController {

	@Autowired
	private MemberCollegeStudentCertificationService memberCollegeStudentCertificationService;


	@ResponseBody
	@RequestMapping({"/api/y/getCollegeStudentCertification","/api/z/getCollegeStudentCertificationH5"})
	public ResponseMsg getCollegeStudentCertification(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			Object[] obj = {"anewCommitStatus"};
			this.requiredStr(obj,request);
			boolean anewCommitStatus = reqDataJson.getBoolean("anewCommitStatus");
			boolean commitBtn = true;
			boolean anewCommitBtn = false;
			String status = "0";
			String remarks = "上传手持学生证照片（姓名需与收货人一致）";
			String auditReason = "";
			Integer memberId = getMemberId(request);
			if(!anewCommitStatus) {
				MemberCollegeStudentCertificationExample cscExample = new MemberCollegeStudentCertificationExample();
				cscExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(memberId);
				cscExample.setOrderByClause(" id desc");
				cscExample.setLimitStart(0);
				cscExample.setLimitSize(1);
				List<MemberCollegeStudentCertification> cscList = memberCollegeStudentCertificationService.selectByExample(cscExample);
				if(cscList.size() > 0) {
					MemberCollegeStudentCertification csc = cscList.get(0);
					status = csc.getStatus();
					if("1".equals(csc.getStatus())) {
						commitBtn = false;
						anewCommitBtn = false;
						remarks = "您已提交实名，请耐心等待平台审核";
						auditReason = "";
					}else if("2".equals(csc.getStatus())) {
						commitBtn = false;
						anewCommitBtn = false;
						remarks = "您已实名认证，无需再提交实名认证信息";
						auditReason = "";
					}else if("3".equals(csc.getStatus())) {
						commitBtn = false;
						anewCommitBtn = true;
						remarks = "您的实名认证未通过";
						auditReason = "原因："+ DataDicUtil.getStatusDesc("COLLEGE_STUDENT_CERTIFICATION", "AUDIT_REASON_STATUS", csc.getAuditReasonStatus());
					}
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("commitBtn", commitBtn);
			map.put("anewCommitBtn", anewCommitBtn);
			map.put("status", status);
			map.put("remarks", remarks);
			map.put("auditReason", auditReason);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG, map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping({"/api/y/addCollegeStudentCertification","/api/z/addCollegeStudentCertificationH5"})
	public ResponseMsg addCollegeStudentCertification(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			Object[] obj = {"pic"};
			this.requiredStr(obj,request);
			Integer memberId = getMemberId(request);
			memberCollegeStudentCertificationService.addCollegeStudentCertification(reqDataJson, memberId);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}


}
