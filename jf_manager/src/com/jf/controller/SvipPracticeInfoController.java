package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.entity.StateCode;
import com.jf.entity.SvipPracticeInfo;
import com.jf.entity.SvipPracticeInfoExample;
import com.jf.service.SvipPracticeInfoService;
import com.jf.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Pengl
 * @create 2019-09-29 下午 1:52
 */
@Controller
public class SvipPracticeInfoController extends BaseController {

    @Autowired
    private SvipPracticeInfoService svipPracticeInfoService;

    @RequestMapping("/svipPracticeInfo/svipPracticeInfoManager.shtml")
    public ModelAndView svipPracticeInfoManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/svipPractice/svipPracticeInfo/getSvipPracticeInfoList");
        return m;
    }

    @ResponseBody
    @RequestMapping("/svipPracticeInfo/getSvipPracticeInfoList.shtml")
    public Map<String, Object> getSvipPracticeInfoList(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<SvipPracticeInfo> dataList = null;
        Integer totalCount = 0;
        try {
            SvipPracticeInfoExample svipPracticeInfoExample = new SvipPracticeInfoExample();
            SvipPracticeInfoExample.Criteria svipPracticeInfoCriteria = svipPracticeInfoExample.createCriteria().andDelFlagEqualTo("0");
            if(!StringUtil.isEmpty(request.getParameter("id")) ) {
                svipPracticeInfoCriteria.andIdEqualTo(Integer.parseInt(request.getParameter("id")));
            }
            if(!StringUtil.isEmpty(request.getParameter("practiceTimeType")) ) {
                svipPracticeInfoCriteria.andPracticeTimeTypeEqualTo(request.getParameter("practiceTimeType"));
            }
            if(!StringUtil.isEmpty(request.getParameter("status")) ) {
                svipPracticeInfoCriteria.andStatusEqualTo(request.getParameter("status"));
            }
            svipPracticeInfoExample.setLimitStart(page.getLimitStart());
            svipPracticeInfoExample.setLimitSize(page.getLimitSize());
            svipPracticeInfoExample.setOrderByClause(" create_date desc");
            totalCount = svipPracticeInfoService.countByExample(svipPracticeInfoExample);
            dataList = svipPracticeInfoService.selectByExample(svipPracticeInfoExample);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resMap.put("Rows", dataList);
        resMap.put("Total", totalCount);
        return resMap;
    }

    @RequestMapping("/svipPracticeInfo/editSvipPracticeInfoManager.shtml")
    public ModelAndView editSvipPracticeInfoManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/svipPractice/svipPracticeInfo/editSvipPracticeInfo");
        if(!StringUtil.isEmpty(request.getParameter("id")) ) {
            SvipPracticeInfo svipPracticeInfo = svipPracticeInfoService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
            m.addObject("svipPracticeInfo", svipPracticeInfo);
        }
        return m;
    }

    @RequestMapping("/svipPracticeInfo/editSvipPracticeInfo.shtml")
    public ModelAndView editSvipPracticeInfo(HttpServletRequest request, SvipPracticeInfo svipPracticeInfo) {
        String rtPage = "/success/success";
        Map<String, Object> resMap = new HashMap<String, Object>();
        String code = "";
        String msg = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String staffId = this.getSessionStaffBean(request).getStaffID();
            Date date = new Date();
            if(svipPracticeInfo.getId() != null ) {
                svipPracticeInfo.setStartTime(sdf.parse(request.getParameter("startTimeStr")));
                svipPracticeInfo.setEndTime(sdf.parse(request.getParameter("endTimeStr")));
                if("1".equals(svipPracticeInfo.getPracticeTimeType()) ) {
                    svipPracticeInfo.setPracticeStartTime(sdf.parse(request.getParameter("practiceStartTimeStr")));
                    svipPracticeInfo.setPracticeEndTime(sdf.parse(request.getParameter("practiceEndTimeStr")));
                    svipPracticeInfo.setPracticeHours(null);
                }
                svipPracticeInfo.setUpdateBy(Integer.parseInt(staffId));
                svipPracticeInfo.setUpdateDate(date);
                svipPracticeInfoService.updateByPrimaryKeySelective(svipPracticeInfo);
            }else {
                svipPracticeInfo.setStartTime(sdf.parse(request.getParameter("startTimeStr")));
                svipPracticeInfo.setEndTime(sdf.parse(request.getParameter("endTimeStr")));
                if("1".equals(svipPracticeInfo.getPracticeTimeType()) ) {
                    svipPracticeInfo.setPracticeStartTime(sdf.parse(request.getParameter("practiceStartTimeStr")));
                    svipPracticeInfo.setPracticeEndTime(sdf.parse(request.getParameter("practiceEndTimeStr")));
                    svipPracticeInfo.setPracticeHours(null);
                }
                svipPracticeInfo.setCreateBy(Integer.parseInt(staffId));
                svipPracticeInfo.setCreateDate(date);
                svipPracticeInfoService.insertSelective(svipPracticeInfo);
            }
            code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
            msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
        } catch (Exception e) {
            e.printStackTrace();
            code = StateCode.JSON_AJAX_ERROR.getStateCode();
            msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
        }
        resMap.put(this.JSON_RESULT_CODE, code);
        resMap.put(this.JSON_RESULT_MESSAGE, msg);
        return new ModelAndView(rtPage, resMap);
    }

    @ResponseBody
    @RequestMapping("/svipPracticeInfo/statusSvipPracticeInfo.shtml")
    public Map<String, Object> statusSvipPracticeInfo(HttpServletRequest request, Page page) {
        Map<String, Object> map = new HashMap<String, Object>();
        String code = "200";
        String msg = "操作成功！";
        try {
            Date date = new Date();
            String staffID = this.getSessionStaffBean(request).getStaffID();
            String id = request.getParameter("id");
            String status = request.getParameter("status");
            if(!StringUtil.isEmpty(id) && !StringUtil.isEmpty(status) ) {
                SvipPracticeInfo svipPracticeInfo = new SvipPracticeInfo();
                svipPracticeInfo.setId(Integer.parseInt(id));
                svipPracticeInfo.setStatus(status);
                svipPracticeInfo.setUpdateBy(Integer.parseInt(staffID));
                svipPracticeInfo.setUpdateDate(date);
                svipPracticeInfoService.statusSvipPracticeInfo(svipPracticeInfo);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            code = "9999";
            msg = "系统异常请稍后再试！";
        }
        map.put("code", code);
        map.put("msg", msg);
        return map;
    }


}
