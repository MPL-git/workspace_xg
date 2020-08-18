package com.jf.common.filter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jf.common.constant.BaseDefine;
import com.jf.common.constant.Const;
import com.jf.common.utils.AESUtils;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.DouyinMemberBind;
import com.jf.entity.DouyinMemberBindExample;
import com.jf.entity.MemberInfo;
import com.jf.service.DouyinMemberBindService;
import com.jf.service.DouyinSprLogService;
import com.jf.service.MemberExtendService;
import com.jf.service.MemberInfoService;
import com.jf.service.MemberStatusLogService;

/**
 * 登录验证
 * 
 * @author
 * @since 2015-04-02
 */
public class DouYinFilter implements Filter {

	private MemberInfoService memberInfoService;
	private DouyinMemberBindService douyinMemberBindService;
	private MemberStatusLogService memberStatusLogService;
	private DouyinSprLogService douyinSprLogService;
	private MemberExtendService memberExtendService;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String uri = request.getRequestURI();
		if(uri.contains("/xgbuy/views/")||uri.contains("/test/weixin/testweixin")){
			String openid = request.getParameter("openid");
 			String adSprId = request.getParameter("adSprId");
			if (!StringUtil.isEmpty(openid) && (request.getSession().getAttribute("dyOpenId")==null||"".equals(request.getSession().getAttribute("dyOpenId"))||request.getSession().getAttribute(BaseDefine.MEMBER_INFO)==null)) {
				openid = AESUtils.decryptType("1", openid.toString());
				if(!StringUtil.isEmpty(openid)){
					Integer sprPlanId = StringUtil.isBlank(adSprId) ? null : Integer.parseInt(adSprId);
					ServletContext servletContext = request.getSession().getServletContext();
					WebApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
					if (memberInfoService == null) {
						memberInfoService = (MemberInfoService)appContext.getBean("memberInfoService");
					}
					if (douyinMemberBindService == null) {
						douyinMemberBindService = (DouyinMemberBindService)appContext.getBean("douyinMemberBindService");
					}
					if (memberStatusLogService == null) {
						memberStatusLogService = (MemberStatusLogService)appContext.getBean("memberStatusLogService");
					}
					if (memberStatusLogService == null) {
						memberStatusLogService = (MemberStatusLogService)appContext.getBean("memberStatusLogService");
					}
					if (douyinSprLogService == null) {
						douyinSprLogService = (DouyinSprLogService)appContext.getBean("douyinSprLogService");
					}
					request.getSession().setAttribute("dyOpenId", openid);
					MemberInfo memberInfo=null;
					DouyinMemberBindExample bindExample = new DouyinMemberBindExample();
					bindExample.createCriteria().andOpenIdEqualTo(openid).andDelFlagEqualTo("0");
					List<DouyinMemberBind> binds = douyinMemberBindService.selectByExample(bindExample);
					//抖音绑定表
					DouyinMemberBind bind = new DouyinMemberBind();
					if(CollectionUtils.isNotEmpty(binds)){
						memberInfo = memberInfoService.selectByPrimaryKey(binds.get(0).getMemberId());
						bind = binds.get(0);
						bind.setLastSprPlanId(sprPlanId);
						douyinMemberBindService.updateByPrimaryKeySelective(bind);
					}else{
						if (memberExtendService == null) {
							memberExtendService = (MemberExtendService)appContext.getBean("memberExtendService");
						}
						memberInfo = new MemberInfo();
						memberInfo.setType("2");
						memberInfo.setRemarks("抖音账号");
						memberInfo.setRegClient("6");
						memberInfo.setRegIp(StringUtil.getIpAddr(request));
						memberInfo.setCreateDate(new Date());
						memberInfo.setDelFlag("0");
						memberInfo.setGroupId(1);//注册会员标识
						memberInfo.setStatus("A");
						String headimg=FileUtil.getRandomFileName("123.jpg", 1, 0);
						memberInfo.setPic(headimg);
						memberInfo = memberInfoService.createMemberInfo(memberInfo);
						
						bind.setMemberId(memberInfo.getId());
						bind.setOpenId(openid);
						bind.setType("2");
						bind.setFirstSprPlanId(sprPlanId);
						bind.setLastSprPlanId(sprPlanId);
						bind.setDelFlag("0");
						bind.setCreateDate(new Date());
						douyinMemberBindService.insertSelective(bind);
						//添加会员注册状态
						memberStatusLogService.addMemberRegisterStatus(memberInfo.getId(), Const.MEMBER_INFO_STATUS_ACTIVATION_A);
						//会员扩展
						memberExtendService.addModel(memberInfo.getId(),"","");
					}
					//抖音日志表
					douyinSprLogService.addModel(sprPlanId,memberInfo.getId(),memberInfo.getRegIp());
					//登录
					request.getSession().setAttribute(BaseDefine.MEMBER_INFO, memberInfo);
					memberInfoService.addLoginLog(memberInfo.getId(), StringUtil.getIpAddr(request), "DY");
					System.out.println("登录sessionId:"+request.getSession().getId());
					String x="a";
				}
			}
			
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
