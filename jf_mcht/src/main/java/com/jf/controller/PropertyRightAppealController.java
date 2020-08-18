package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.StringUtil;
import com.jf.entity.IntellectualPropertyRightAppealCustom;
import com.jf.entity.IntellectualPropertyRightAppealCustomExample;
import com.jf.entity.MchtProductBrandCustom;
import com.jf.entity.MchtProductBrandCustomExample;
import com.jf.service.PropertyRightAppealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 知识侵权管理控制层
 * @Author: zhen.li
 * @Date: 2018/12/13 16:16
 */
@Controller
@RequestMapping("/propertyRightAppeal")
public class PropertyRightAppealController extends BaseController {

    @Autowired
    private PropertyRightAppealService service;

    @RequestMapping("/rightAppealList")
    public String rightComplainList() {
        return "propertyRightManage/rightAppealList";
    }

    /**
     * 列表数据
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/rightAppealData")
    @ResponseBody
    public ResponseMsg rightAppealData(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        int totalCount = 0;
        try {
            IntellectualPropertyRightAppealCustomExample appealCustomExample = new IntellectualPropertyRightAppealCustomExample();
            IntellectualPropertyRightAppealCustomExample.IntellectualPropertyRightAppealCustomCriteria criteria = appealCustomExample.createCriteria();
            criteria.andDelFlagEqualTo("0");
            criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
            appealCustomExample.setOrderByClause("a.id asc");
            appealCustomExample.setPresentTime(new Date());
            if (!StringUtil.isEmpty(request.getParameter("complainStatus"))) {
                String complainStatus = request.getParameter("complainStatus");
                appealCustomExample.setComplainStatus(complainStatus);
            }
            if (!StringUtil.isEmpty(request.getParameter("appealType"))) {
                String appealType = request.getParameter("appealType");
                criteria.andAppealTypeEqualTo(appealType);
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!StringUtil.isEmpty(request.getParameter("createDateBegin"))) {
                criteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("createDateBegin") + " 00:00:00"));
            }

            if (!StringUtil.isEmpty(request.getParameter("createDateEnd"))) {
                criteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("createDateEnd") + " 23:59:59"));
            }

            totalCount = service.countByExample(appealCustomExample);

            appealCustomExample.setLimitStart(page.getLimitStart());
            appealCustomExample.setLimitSize(page.getLimitSize());
            List<IntellectualPropertyRightAppealCustom> rightAppealCustoms = service.selectCustomByExample(appealCustomExample);

            resMap.put("Rows", rightAppealCustoms);
            resMap.put("Total", totalCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, resMap);
    }

    /**
     * 投诉详情
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/rightAppealDetail")
    public String rightAppealDetail(Model model, Integer id) {
        model.addAttribute("detail", service.getDetail(id));
        return "propertyRightManage/rightAppealDetail";
    }

    /**
     *  提交申诉材料
     *
     * @param request
     * @param appealCustom
     * @return
     */
    @RequestMapping("/saveComplain")
    @ResponseBody
    public ResponseMsg saveComplain(HttpServletRequest request, IntellectualPropertyRightAppealCustom appealCustom) {
        try {
            appealCustom.setUpdateBy(this.getSessionMchtInfo(request).getId());
            service.saveComplain(appealCustom);
        } catch (ArgException arge) {
            arge.printStackTrace();
            return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
        }
        return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
    }
}
