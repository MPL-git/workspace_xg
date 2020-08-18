package com.jf.controller;

import com.jf.common.utils.StringUtils;
import com.jf.entity.SmsWhiteMobile;
import com.jf.entity.SmsWhiteMobileExample;
import com.jf.service.SmsWhiteMobileService;
import com.jf.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SmsWhiteMobileController
 * @Author YRD
 * @Date 2020/7/24 15:02
 **/

@Controller
public class SmsWhiteMobileController extends BaseController {

    @Autowired
    private SmsWhiteMobileService smsWhiteMobileService;

    /**
     * 白名单列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/member/sms/smsWhiteMobile.shtml")
    public ModelAndView smsBlackMobile(HttpServletRequest request) {
        String rtPage = "/member/sms/smsWhiteMobile";
        Map<String, Object> resMap = new HashMap<String, Object>();
        return new ModelAndView(rtPage, resMap);
    }

    /**
     *会员短信白名单列表数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/member/sms/getWhiteMobileData.shtml")
    @ResponseBody
    public Map<String, Object> getSmsBlackMobile(HttpServletRequest request, Page page) {

        Map<String, Object> resMap = new HashMap<String, Object>();
        Integer totalCount = 0;
        String mobile = request.getParameter("mobile");
        try {
            SmsWhiteMobileExample smmWhiteExample = new SmsWhiteMobileExample();
            SmsWhiteMobileExample.Criteria criteria = smmWhiteExample.createCriteria();
            criteria.andDelFlagEqualTo("0");
            if(!StringUtils.isBlank(mobile)){
                criteria.andMobileEqualTo(mobile);
            }
            List<SmsWhiteMobile> smsWhiteMobiles = smsWhiteMobileService.selectByExample(smmWhiteExample);
            totalCount = smsWhiteMobileService.countByExample(smmWhiteExample);

            resMap.put("Rows", smsWhiteMobiles);
            resMap.put("Total", totalCount);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resMap;
    }

    /**
     * 添加白名单页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/member/sms/addWhiteMobile.shtml")
    public ModelAndView addWhiteMobile(HttpServletRequest request) {
        String rtPage = "/member/sms/addWhiteMobile";
        Map<String, Object> resMap = new HashMap<String, Object>();
        return new ModelAndView(rtPage, resMap);
    }

    /**
     *保存白名单号码
     * @param request
     * @return
     */
    @RequestMapping(value = "/member/sms/saveWhiteMobile.shtml")
    @ResponseBody
    public Map<String, Object> saveWhiteMobile(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String code = "200";
        String message = "";
        try {
            String mobile = request.getParameter("mobile");
            if(!StringUtils.isEmpty(mobile) ) {
                String staffID = this.getSessionStaffBean(request).getStaffID();
                SmsWhiteMobile swm = new SmsWhiteMobile();
                swm.setCreateDate(new Date());
                swm.setCreateBy(Integer.parseInt(staffID));
                swm.setMobile(mobile);
                smsWhiteMobileService.insertSelective(swm);
            }
        } catch (Exception e) {
            code = "400";
            message = e.getMessage();
        }
        map.put("code", code);
        map.put("returnMessage", message);
        return map;
    }

    /**
     *删除白名单号码
     * @param request
     * @return
     */
    @RequestMapping(value = "/member/sms/delWhiteMobile.shtml")
    @ResponseBody
    public Map<String, Object> delWhiteMobile(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String statusCode = "200";
        String message = "";
        try {
            String id = request.getParameter("id");
            if(!StringUtils.isEmpty(id) ) {
                String staffID = this.getSessionStaffBean(request).getStaffID();
                SmsWhiteMobile smsWhiteMobile = smsWhiteMobileService.selectByPrimaryKey(Integer.parseInt(id));
                smsWhiteMobile.setUpdateBy(Integer.parseInt(staffID));
                smsWhiteMobile.setUpdateDate(new Date());
                smsWhiteMobile.setDelFlag("1");
                smsWhiteMobileService.updateByPrimaryKeySelective(smsWhiteMobile);
            }
        } catch (Exception e) {
            statusCode = "400";
            message = "系统错误";
        }
        map.put("statusCode", statusCode);
        map.put("message", message);
        return map;
    }


}
