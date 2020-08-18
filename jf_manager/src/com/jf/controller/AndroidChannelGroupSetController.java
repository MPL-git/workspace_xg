package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.entity.*;
import com.jf.service.AndroidChannelGroupService;
import com.jf.service.AndroidChannelGroupSetDtlService;
import com.jf.service.AndroidChannelGroupSetService;
import com.jf.service.SysStatusService;
import com.jf.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author Pengl
 * @create 2019-10-18 下午 1:54
 */
@Controller
public class AndroidChannelGroupSetController extends BaseController {

    @Autowired
    private AndroidChannelGroupSetService androidChannelGroupSetService;

    @Autowired
    private AndroidChannelGroupSetDtlService androidChannelGroupSetDtlService;

    @Autowired
    private AndroidChannelGroupService androidChannelGroupService;

    @Autowired
    private SysStatusService sysStatusService;

    @RequestMapping("/androidChannelGroupSet/androidChannelGroupSetManager.shtml")
    public ModelAndView androidChannelGroupSetManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/dataCenter/androidChannelGroupSet/androidChannelGroupSetList");
        return m;
    }

    @ResponseBody
    @RequestMapping("/androidChannelGroupSet/androidChannelGroupSetList.shtml")
    public Map<String, Object> androidChannelGroupSetList(HttpServletRequest request, HttpServletResponse response, Page page){
        Map<String, Object> resMap=new HashMap<String, Object>();
        List<AndroidChannelGroupSet> dataList = null;
        Integer totalCount = 0;
        try {
            AndroidChannelGroupSetExample androidChannelGroupSetExample = new AndroidChannelGroupSetExample();
            AndroidChannelGroupSetExample.Criteria androidChannelGroupSetCriteria = androidChannelGroupSetExample.createCriteria();
            androidChannelGroupSetCriteria.andDelFlagEqualTo("0");
            if(!StringUtil.isEmpty(request.getParameter("name")) ) {
                androidChannelGroupSetCriteria.andNameLike("%"+request.getParameter("name")+"%");
            }
            if(!StringUtil.isEmpty(request.getParameter("status")) ) {
                androidChannelGroupSetCriteria.andStatusEqualTo(request.getParameter("status"));
            }
            androidChannelGroupSetExample.setOrderByClause(" ifnull(seq_no, 999999) asc, create_date desc");
            androidChannelGroupSetExample.setLimitStart(page.getLimitStart());
            androidChannelGroupSetExample.setLimitSize(page.getLimitSize());
            totalCount = androidChannelGroupSetService.countByExample(androidChannelGroupSetExample);
            dataList = androidChannelGroupSetService.selectByExample(androidChannelGroupSetExample);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resMap.put("Rows", dataList);
        resMap.put("Total", totalCount);
        return resMap;
    }

    @ResponseBody
    @RequestMapping("/androidChannelGroupSet/updateSeqNo.shtml")
    public Map<String, Object> updateSeqNo(HttpServletRequest request, Page page) {
        Map<String, Object> map = new HashMap<String, Object>();
        String code = "200";
        String msg = "操作成功！";
        try {
            String id = request.getParameter("id");
            String seqNo = request.getParameter("seqNo");
            if(!StringUtil.isEmpty(id)) {
                AndroidChannelGroupSet androidChannelGroupSet = new AndroidChannelGroupSet();
                androidChannelGroupSet.setId(Integer.parseInt(id));
                androidChannelGroupSet.setSeqNo(Integer.parseInt(seqNo));
                androidChannelGroupSetService.updateByPrimaryKeySelective(androidChannelGroupSet);
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

    @RequestMapping("/androidChannelGroupSet/editAndroidChannelGroupSetManager.shtml")
    public ModelAndView editAndroidChannelGroupSetManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/dataCenter/androidChannelGroupSet/editAndroidChannelGroupSet");
        if(!StringUtil.isEmpty(request.getParameter("id")) ) {
            AndroidChannelGroupSet androidChannelGroupSet = androidChannelGroupSetService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
            m.addObject("androidChannelGroupSet", androidChannelGroupSet);
            AndroidChannelGroupSetDtlCustomExample androidChannelGroupSetDtlCustomExample = new AndroidChannelGroupSetDtlCustomExample();
            AndroidChannelGroupSetDtlCustomExample.Criteria androidChannelGroupSetDtlCustomCriteria = androidChannelGroupSetDtlCustomExample.createCriteria();
            androidChannelGroupSetDtlCustomCriteria.andDelFlagEqualTo("0").andAndroidChannelGroupSetIdEqualTo(androidChannelGroupSet.getId());
            List<AndroidChannelGroupSetDtlCustom> androidChannelGroupSetDtlCustomList = androidChannelGroupSetDtlService.selectByCustomExample(androidChannelGroupSetDtlCustomExample);
            m.addObject("androidChannelGroupSetDtlCustomList", androidChannelGroupSetDtlCustomList);
        }
        return m;
    }

    @RequestMapping("/androidChannelGroupSet/androidChannelGroupManager.shtml")
    public ModelAndView androidChannelGroupManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/dataCenter/androidChannelGroupSet/androidChannelGroupList");
        m.addObject("androidChannelGroupIds", request.getParameter("androidChannelGroupIds"));
        return m;
    }

    @ResponseBody
    @RequestMapping("/androidChannelGroupSet/androidChannelGroupList.shtml")
    public Map<String, Object> androidChannelGroupList(HttpServletRequest request, HttpServletResponse response, Page page){
        Map<String, Object> resMap=new HashMap<String, Object>();
        List<AndroidChannelGroup> dataList = null;
        Integer totalCount = 0;
        try {
            AndroidChannelGroupExample androidChannelGroupExample = new AndroidChannelGroupExample();
            AndroidChannelGroupExample.Criteria androidChannelGroupCriteria = androidChannelGroupExample.createCriteria();
            androidChannelGroupCriteria.andDelFlagEqualTo("0").andTypeEqualTo("1");
            if(!StringUtil.isEmpty(request.getParameter("androidChannelGroupIds")) ) {
                List<Integer> idList = new ArrayList<Integer>();
                String[] androidChannelGroupIds = request.getParameter("androidChannelGroupIds").split(",");
                for (String id : androidChannelGroupIds ) {
                    idList.add(Integer.parseInt(id));
                }
                androidChannelGroupCriteria.andIdNotIn(idList);
            }
            if(!StringUtil.isEmpty(request.getParameter("groupName")) ) {
                androidChannelGroupCriteria.andGroupNameLike("%"+request.getParameter("groupName")+"%");
            }
            if(!StringUtil.isEmpty(request.getParameter("status")) ) {
                androidChannelGroupCriteria.andStatusEqualTo(request.getParameter("status"));
            }
            androidChannelGroupExample.setOrderByClause(" create_date desc");
            androidChannelGroupExample.setLimitStart(page.getLimitStart());
            androidChannelGroupExample.setLimitSize(page.getLimitSize());
            dataList = androidChannelGroupService.selectByExample(androidChannelGroupExample);
            totalCount = androidChannelGroupService.countByExample(androidChannelGroupExample);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resMap.put("Rows", dataList);
        resMap.put("Total", totalCount);
        return resMap;
    }

    @ResponseBody
    @RequestMapping("/androidChannelGroupSet/editAndroidChannelGroupSet.shtml")
    public ModelAndView editAndroidChannelGroupSet(HttpServletRequest request){
        ModelAndView m = new ModelAndView("/success/success");
        String code = null;
        String msg = "";
        try {
            Date date = new Date();
            String id = request.getParameter("id");
            String androidChannelGroupIds = request.getParameter("androidChannelGroupIds");
            String name = request.getParameter("name");
            String status = request.getParameter("status");
            String remarks = request.getParameter("remarks");
            AndroidChannelGroupSet androidChannelGroupSet = new AndroidChannelGroupSet();
            if(!StringUtil.isEmpty(id) ) {
                androidChannelGroupSet.setId(Integer.parseInt(id));
                androidChannelGroupSet.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
                androidChannelGroupSet.setUpdateDate(date);
            }else {
                androidChannelGroupSet.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
                androidChannelGroupSet.setCreateDate(date);
            }
            if(!StringUtil.isEmpty(status) ) {
                androidChannelGroupSet.setStatus(status);;
            }
            if(!StringUtil.isEmpty(name) ) {
                androidChannelGroupSet.setName(name);
            }
            if(!StringUtil.isEmpty(remarks) ) {
                androidChannelGroupSet.setRemarks(remarks);
            }
            androidChannelGroupSetService.editAndroidChannelGroupSet(androidChannelGroupSet, androidChannelGroupIds);
            code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
            msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
        }catch (Exception e) {
            e.printStackTrace();
            code = StateCode.JSON_AJAX_ERROR.getStateCode();
            msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
        }
        m.addObject(this.JSON_RESULT_CODE, code);
        m.addObject(this.JSON_RESULT_MESSAGE, msg);
        return m;
    }

    @RequestMapping(value = "/androidChannelGroupSet/androidChannelManager.shtml")
    public ModelAndView androidChannelManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/dataCenter/androidChannelGroupSet/androidChannelList");
        return m;
    }

    @ResponseBody
    @RequestMapping(value = "/androidChannelGroupSet/androidChannelList.shtml")
    public Map<String, Object> androidChannelList(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<SysStatus> listMap = new ArrayList<>();
        Integer totalCount = 0;
        try {
            SysStatusExample sysStatusExample = new SysStatusExample();
            SysStatusExample.Criteria sysStatusCriteria = sysStatusExample.createCriteria();
            sysStatusCriteria.andTableNameEqualTo("BU_MEMBER_INFO").andColNameEqualTo("SPR_CHNL");
            if(!StringUtil.isEmpty(request.getParameter("remark")) ) {
                sysStatusCriteria.andStatusDescLike("%"+request.getParameter("remark")+"%");
            }
            sysStatusExample.setOrderByClause(" STATUS_ORDER");
            sysStatusExample.setLimitStart(page.getLimitStart());
            sysStatusExample.setLimitSize(page.getLimitSize());
            listMap = sysStatusService.selectByExample(sysStatusExample);
            totalCount = sysStatusService.countByExample(sysStatusExample);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resMap.put("Rows", listMap);
        resMap.put("Total", totalCount);
        return resMap;
    }

    @RequestMapping(value = "/androidChannelGroupSet/updateAndroidChannelManager.shtml")
    public ModelAndView updateAndroidChannelManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/dataCenter/androidChannelGroupSet/updateAndroidChannel");
        List<SysStatus> channelList = DataDicUtil.getStatusList("BU_SPREAD_ACTIVITY_GROUP", "CHANNEL");
        m.addObject("channelList", channelList);
        m.addObject("statusDescS", request.getParameter("statusDescS"));
        return m;
    }

    @RequestMapping("/androidChannelGroupSet/updateAndroidChannel.shtml")
    public ModelAndView updateAndroidChannel(HttpServletRequest request, HelpItem helpItem) {
        String rtPage = "/success/success";
        Map<String, Object> resMap = new HashMap<String, Object>();
        String code = "";
        String msg = "";
        try {
            if(!StringUtil.isEmpty(request.getParameter("statusDescS")) && !StringUtil.isEmpty("remark") ) {
                SysStatusExample sysStatusExample = new SysStatusExample();
                SysStatusExample.Criteria sysStatusCriteria = sysStatusExample.createCriteria();
                sysStatusCriteria.andTableNameEqualTo("BU_MEMBER_INFO").andColNameEqualTo("SPR_CHNL");
                String[] statusDescStr = request.getParameter("statusDescS").split(",");
                List<String> statusDescList = new ArrayList<>();
                for(String statusDesc : statusDescStr ) {
                    statusDescList.add(statusDesc);
                }
                sysStatusCriteria.andStatusDescIn(statusDescList);
                SysStatus ss = new SysStatus();
                ss.setRemark(request.getParameter("remark"));
                sysStatusService.updateByExampleSelective(ss, sysStatusExample);
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
