package com.jf.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.MemberFeedback;
import com.jf.entity.MemberFeedbackExample;
import com.jf.entity.MemberFeedbackPic;
import com.jf.service.MemberFeedbackPicService;
import com.jf.service.MemberFeedbackService;

import net.sf.json.JSONObject;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月11日 下午6:29:32 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class MemberFeedbackController extends BaseController{
	
	@Resource
	private MemberFeedbackService memberFeedbackService;
	
	@Resource
	private MemberFeedbackPicService memberFeedbackPicService;
	
	/**
	 * 
	 * 方法描述 ：新增意见反馈问题
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月11日 下午7:36:32 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addMemberFeedback")
	@ResponseBody
	public ResponseMsg addMemberFeedback(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"type"};
			this.requiredStr(obj,request);
			Integer memberId = getMemberId(request);
			String type = reqDataJson.getString("type");
			String content = reqDataJson.getString("content");
			String phoneSystem = reqDataJson.getString("phoneSystem");
			String pic = reqDataJson.getString("pic");
			String contactPhone = reqDataJson.getString("contactPhone");
			Date date = new Date();
			
			//意见反馈每个用户每天只能提交一次
			MemberFeedbackExample memberFeedbackExample = new MemberFeedbackExample();
			memberFeedbackExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(memberId);
			memberFeedbackExample.setOrderByClause("create_date desc");
			List<MemberFeedback> memberFeedbacks = memberFeedbackService.selectByExample(memberFeedbackExample);
			if(CollectionUtils.isNotEmpty(memberFeedbacks)){
				MemberFeedback memberFeedback = memberFeedbacks.get(0);
				String createDate = DateUtil.getFormatDate(memberFeedback.getCreateDate(), "yyyyMMdd");
				String dateStr = DateUtil.getFormatDate(date, "yyyyMMdd");
				if(dateStr.equals(createDate)){
					throw new ArgException("您今天已经提交过反馈，客服正在处理中");
				}
			}
			MemberFeedback memberFeedback = new MemberFeedback();
			memberFeedback.setMemberId(memberId);
			memberFeedback.setType(type);
			memberFeedback.setContent(content);
			memberFeedback.setContactPhone(contactPhone);
			memberFeedback.setPhoneSystem(phoneSystem);
			memberFeedback.setCreateBy(memberId);
			memberFeedback.setCreateDate(date);
			memberFeedback.setDelFlag("0");
			memberFeedbackService.insertSelective(memberFeedback);
			if(!StringUtil.isBlank(pic)){
				String[] pics = pic.split(",");
				for (String picStr : pics) {
					picStr = StringUtil.replace(picStr,"xgbuy.cc");;
					MemberFeedbackPic memberFeedbackPic = new MemberFeedbackPic();
					memberFeedbackPic.setFeedbackId(memberFeedback.getId());
					memberFeedbackPic.setPic(picStr);
					memberFeedbackPic.setCreateBy(memberId);
					memberFeedbackPic.setCreateDate(date);
					memberFeedbackPic.setDelFlag("0");
					memberFeedbackPicService.insertSelective(memberFeedbackPic);
				}
			}

			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
}
