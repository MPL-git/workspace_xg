package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DateUtil;
import com.jf.entity.*;
import com.jf.service.SvipPracticeRecordService;
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
 * @create 2019-09-29 下午 6:29
 */
@Controller
public class SvipPracticeRecordController extends BaseController {

    @Autowired
    private SvipPracticeRecordService svipPracticeRecordService;

    @RequestMapping("/svipPracticeRecord/svipPracticeRecordManager.shtml")
    public ModelAndView svipPracticeInfoManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/svipPractice/svipPracticeRecord/getSvipPracticeRecordList");
        return m;
    }

    @ResponseBody
    @RequestMapping("/svipPracticeRecord/getSvipPracticeRecordList.shtml")
    public Map<String, Object> getSvipPracticeRecordList(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<SvipPracticeRecordCustom> dataList = null;
        Integer totalCount = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SvipPracticeRecordCustomExample svipPracticeRecordCustomExample = new SvipPracticeRecordCustomExample();
            SvipPracticeRecordCustomExample.SvipPracticeRecordCustomCriteria svipPracticeRecordCustomCriteria = svipPracticeRecordCustomExample.createCriteria();
            svipPracticeRecordCustomCriteria.andDelFlagEqualTo("0");
            if(!StringUtil.isEmpty(request.getParameter("memberId")) ) {
                svipPracticeRecordCustomCriteria.andMemberIdEqualTo(Integer.parseInt(request.getParameter("memberId")));
            }
            if(!StringUtil.isEmpty(request.getParameter("recTimeStart")) ) {
                svipPracticeRecordCustomCriteria.andRecTimeGreaterThanOrEqualTo(sdf.parse(request.getParameter("recTimeStart")));
            }
            if(!StringUtil.isEmpty(request.getParameter("recTimeEnd")) ) {
                svipPracticeRecordCustomCriteria.andRecTimeLessThanOrEqualTo(sdf.parse(request.getParameter("recTimeEnd")));
            }
            svipPracticeRecordCustomExample.setOrderByClause(" t.rec_time desc");
            svipPracticeRecordCustomExample.setLimitStart(page.getLimitStart());
            svipPracticeRecordCustomExample.setLimitSize(page.getLimitSize());
            totalCount = svipPracticeRecordService.countByCustomExample(svipPracticeRecordCustomExample);
            dataList = svipPracticeRecordService.selectByCustomExample(svipPracticeRecordCustomExample);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resMap.put("Rows", dataList);
        resMap.put("Total", totalCount);
        return resMap;
    }

    @RequestMapping("/svipPracticeRecord/addPracticeTimeManager.shtml")
    public ModelAndView addPracticeTimeManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/svipPractice/svipPracticeRecord/addPracticeTime");
        SvipPracticeRecord svipPracticeRecord = svipPracticeRecordService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
        m.addObject("svipPracticeRecord", svipPracticeRecord);
        return m;
    }

    @RequestMapping("/svipPracticeRecord/addPracticeTime.shtml")
    public ModelAndView addPracticeTime(HttpServletRequest request) {
        String rtPage = "/success/success";
        Map<String, Object> resMap = new HashMap<String, Object>();
        String code = "";
        String msg = "";
        try {
            String staffId = this.getSessionStaffBean(request).getStaffID();
            Date date = new Date();
            if(!StringUtil.isEmpty(request.getParameter("id")) && !StringUtil.isEmpty(request.getParameter("addHour")) ) {
                SvipPracticeRecord spr = svipPracticeRecordService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
                SvipPracticeRecord svipPracticeRecord = new SvipPracticeRecord();
                svipPracticeRecord.setId(Integer.parseInt(request.getParameter("id")));
                if(spr.getPracticeEndTime().compareTo(date) == 1 ) {
                    svipPracticeRecord.setPracticeEndTime(DateUtil.updateHour(spr.getPracticeEndTime(), Integer.parseInt(request.getParameter("addHour"))));
                }else {
                    svipPracticeRecord.setPracticeEndTime(DateUtil.updateHour(date, Integer.parseInt(request.getParameter("addHour"))));
                }
                svipPracticeRecord.setUpdateBy(Integer.parseInt(staffId));
                svipPracticeRecord.setUpdateDate(date);
                svipPracticeRecordService.updateByPrimaryKeySelective(svipPracticeRecord);
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

}
