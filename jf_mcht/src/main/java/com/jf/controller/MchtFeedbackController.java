package com.jf.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.StringUtil;
import com.jf.entity.MchtFeedback;
import com.jf.service.MchtFeedbackService;

@SuppressWarnings("serial")
@Controller
public class MchtFeedbackController extends BaseController {

	@Autowired
	private MchtFeedbackService mchtFeedbackService;
	
	/**
	 * 
	 * @Title mchtFeedbackManager   
	 * @Description TODO(商家反馈)   
	 * @author pengl
	 * @date 2018年6月25日 下午5:14:56
	 */
	@RequestMapping("/mchtFeedback/mchtFeedbackManager")
	public ModelAndView mchtFeedbackManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/mchtFeedback/addMchtFeedback");
		return m;
	}
	
	/**
	 * 
	 * @Title addMchtFeedbackAndPic   
	 * @Description TODO(商家反馈)   
	 * @author pengl
	 * @date 2018年6月26日 下午2:29:54
	 */
	@ResponseBody
	@RequestMapping("/mchtFeedback/addMchtFeedbackAndPic")
	public ResponseMsg addMchtFeedbackAndPic(HttpServletRequest request) {
		try {
			String userAgent = request.getHeader("user-agent");
			MchtFeedback mchtFeedback = new MchtFeedback();
			mchtFeedback.setMchtId(this.getSessionMchtInfo(request).getId());
			mchtFeedback.setSystem(userAgent.substring(userAgent.indexOf("(")+1, userAgent.indexOf(")")));
			mchtFeedback.setBrowser(userAgent);
			mchtFeedback.setDealStatus("0");
			mchtFeedback.setCreateBy(this.getSessionUserInfo(request).getId());
			mchtFeedback.setCreateDate(new Date());
			mchtFeedback.setDelFlag("0");
			if(!StringUtil.isEmpty(request.getParameter("type"))) {
				mchtFeedback.setType(request.getParameter("type"));
			}
			if(!StringUtil.isEmpty(request.getParameter("content"))) {
				mchtFeedback.setContent(request.getParameter("content"));
			}
			if(!StringUtil.isEmpty(request.getParameter("contactPhone"))) {
				mchtFeedback.setContactPhone(request.getParameter("contactPhone"));
			}
			JSONArray mchtFeedbackPics = StringUtil.isEmpty(request.getParameter("mchtFeedbackPics"))?null:JSONArray.fromObject(request.getParameter("mchtFeedbackPics"));
			mchtFeedbackService.addMchtFeedbackAndPic(mchtFeedback, mchtFeedbackPics);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	} 
	
	
}
