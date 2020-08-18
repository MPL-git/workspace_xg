package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.entity.*;
import com.jf.service.HotSearchWordService;
import com.jf.service.SearchLogService;
import com.jf.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("serial")
@Controller
public class HotSearchWordController extends BaseController {

    @Autowired
    private HotSearchWordService hotSearchWordService;

    @Autowired
    private SearchLogService searchLogService;

    /**
     * @Title hotSearchWordManager
     * @Description TODO(这里用一句话描述这个方法的作用)
     * @author pengl
     * @date 2018年7月18日 下午5:33:13
     */
    @RequestMapping("/hotSearchWord/hotSearchWordManager.shtml")
    public ModelAndView hotSearchWordManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView();
        m.addObject("statusList", DataDicUtil.getTableStatus("BU_HOT_SEARCH_WORD", "STATUS")); //状态
        m.setViewName("/hotSearchWord/getHotSearchWordList");
        return m;
    }

    /**
     * @Title getHotSearchWordList
     * @Description TODO(这里用一句话描述这个方法的作用)
     * @author pengl
     * @date 2018年7月18日 下午6:08:22
     */
    @ResponseBody
    @RequestMapping("/hotSearchWord/getHotSearchWordList.shtml")
    public Map<String, Object> getHotSearchWordList(HttpServletRequest request, Page page, Integer beginSearchLogCount, Integer endSearchLogCount) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<HotSearchWordCustom> dataList = null;
        Integer totalCount = 0;
        try {
            Map<String,Object> map = new HashMap<>();
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -100);
            Date start = calendar.getTime();

            map.put("start",start);
            map.put("date",date);

            HotSearchWordCustomExample hotSearchWordCustomExample = new HotSearchWordCustomExample();
            HotSearchWordCustomExample.HotSearchWordCustomCriteria hotSearchWordCustomCriteria = hotSearchWordCustomExample.createCriteria();
            hotSearchWordCustomCriteria.andDelFlagEqualTo("0").andHotSearchPageEqualTo("1");
            if (!StringUtil.isEmpty(request.getParameter("word"))) {
                hotSearchWordCustomCriteria.andWordLike("%" + request.getParameter("word") + "%");
                map.put("word",request.getParameter("word"));
            }
            if (!StringUtil.isEmpty(request.getParameter("status"))) {
                hotSearchWordCustomCriteria.andStatusEqualTo(request.getParameter("status"));
                map.put("status",request.getParameter("status"));
            }
            if (beginSearchLogCount != null) {
                hotSearchWordCustomCriteria.andSearchCount100DaysGreaterThanOrEqualTo(beginSearchLogCount);
                map.put("beginSearchLogCount",beginSearchLogCount);
            }
            if (endSearchLogCount != null) {
                hotSearchWordCustomCriteria.andSearchCount100DaysLessThanOrEqualTo(endSearchLogCount);
                map.put("endSearchLogCount",endSearchLogCount);
            }
            map.put("limitStart",page.getLimitStart());
            map.put("limitSize",page.getLimitSize());

            hotSearchWordCustomExample.setOrderByClause("create_date desc");
            totalCount = hotSearchWordService.countByCustomExample(hotSearchWordCustomExample);
            dataList = hotSearchWordService.selectByCustomExampleNew(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resMap.put("Rows", dataList);
        resMap.put("Total", totalCount);
        return resMap;
    }

    /**
     * @Title updateStatus
     * @Description TODO(上下架)
     * @author pengl
     * @date 2018年7月19日 上午10:04:21
     */
    @ResponseBody
    @RequestMapping("/hotSearchWord/updateStatus.shtml")
    public Map<String, Object> updateStatus(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String code = "200";
        String msg = "操作成功！";
        try {
            Date date = new Date();
            String staffID = this.getSessionStaffBean(request).getStaffID();
            String hotSearchWordId = request.getParameter("hotSearchWordId");
            if (!StringUtil.isEmpty(hotSearchWordId)) {
                HotSearchWordExample hotSearchWordExample = new HotSearchWordExample();
                hotSearchWordExample.createCriteria().andIdEqualTo(Integer.parseInt(hotSearchWordId));
                HotSearchWord hotSearchWord = new HotSearchWord();
                hotSearchWord.setStatus(request.getParameter("status"));
                hotSearchWord.setUpdateBy(Integer.parseInt(staffID));
                hotSearchWord.setUpdateDate(date);
                hotSearchWordService.updateByExampleSelective(hotSearchWord, hotSearchWordExample);
            } else {
                code = "9999";
                msg = "ID为空！";
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

    /**
     * @Title updateSeqNo
     * @Description TODO(排序)
     * @author pengl
     * @date 2018年7月19日 上午10:24:58
     */
    @ResponseBody
    @RequestMapping("/hotSearchWord/updateSeqNo.shtml")
    public Map<String, Object> updateSeqNo(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String code = "200";
        String msg = "操作成功！";
        try {
            Date date = new Date();
            String staffID = this.getSessionStaffBean(request).getStaffID();
            String hotSearchWordId = request.getParameter("hotSearchWordId");
            String seqNo = request.getParameter("seqNo");
            if (!StringUtil.isEmpty(hotSearchWordId) && !StringUtil.isEmpty(seqNo)) {
                HotSearchWordExample hotSearchWordExample = new HotSearchWordExample();
                hotSearchWordExample.createCriteria().andIdEqualTo(Integer.parseInt(hotSearchWordId));
                HotSearchWord hotSearchWord = new HotSearchWord();
                hotSearchWord.setSeqNo(Integer.parseInt(seqNo));
                hotSearchWord.setUpdateBy(Integer.parseInt(staffID));
                hotSearchWord.setUpdateDate(date);
                hotSearchWordService.updateByExampleSelective(hotSearchWord, hotSearchWordExample);
            } else {
                code = "9999";
                msg = "ID为空！";
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

    /**
     * @Title delHotSearchWord
     * @Description TODO(删除)
     * @author pengl
     * @date 2018年7月19日 上午10:37:14
     */
    @ResponseBody
    @RequestMapping("/hotSearchWord/delHotSearchWord.shtml")
    public Map<String, Object> delHotSearchWord(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String code = "200";
        String msg = "操作成功！";
        try {
            Date date = new Date();
            String staffID = this.getSessionStaffBean(request).getStaffID();
            String hotSearchWordId = request.getParameter("hotSearchWordId");
            if (!StringUtil.isEmpty(hotSearchWordId)) {
                HotSearchWordExample hotSearchWordExample = new HotSearchWordExample();
                hotSearchWordExample.createCriteria().andIdEqualTo(Integer.parseInt(hotSearchWordId));
                HotSearchWord hotSearchWord = new HotSearchWord();
                hotSearchWord.setDelFlag("1");
                hotSearchWord.setUpdateBy(Integer.parseInt(staffID));
                hotSearchWord.setUpdateDate(date);
                hotSearchWordService.updateByExampleSelective(hotSearchWord, hotSearchWordExample);
            } else {
                code = "9999";
                msg = "ID为空！";
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

    /**
     * @Title addHotSearchWordManager
     * @Description TODO(添加)
     * @author pengl
     * @date 2018年7月19日 上午11:05:45
     */
    @RequestMapping("/hotSearchWord/addHotSearchWordManager.shtml")
    public ModelAndView addHotSearchWordManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView();
        m.setViewName("/hotSearchWord/addHotSearchWord");
        return m;
    }

    /**
     * @Title addHotSearchWord
     * @Description TODO(这里用一句话描述这个方法的作用)
     * @author pengl
     * @date 2018年7月19日 上午11:08:21
     */
    @RequestMapping("/hotSearchWord/addHotSearchWord.shtml")
    public ModelAndView addHotSearchWord(HttpServletRequest request) {
        String rtPage = "/success/success";
        Map<String, Object> resMap = new HashMap<String, Object>();
        String code = null;
        String msg = null;
        try {
            Date date = new Date();
            String staffID = this.getSessionStaffBean(request).getStaffID();
            HotSearchWord hotSearchWord = new HotSearchWord();
            hotSearchWord.setWord(request.getParameter("word"));
            hotSearchWord.setStatus(request.getParameter("status"));
            hotSearchWord.setSource("2"); //2平台创建
            hotSearchWord.setCreateBy(Integer.parseInt(staffID));
            hotSearchWord.setCreateDate(date);
            hotSearchWord.setDelFlag("0");
            hotSearchWordService.insertSelective(hotSearchWord);
            code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
            msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
        } catch (Exception e) {
            code = StateCode.JSON_AJAX_ERROR.getStateCode();
            msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
        }
        resMap.put(this.JSON_RESULT_CODE, code);
        resMap.put(this.JSON_RESULT_MESSAGE, msg);
        return new ModelAndView(rtPage, resMap);
    }


    /**
     * 微淘热搜管理
     *
     * @param request
     * @return
     */
    @RequestMapping("/wetaoHotSearchWord/list.shtml")
    public ModelAndView wetaoHotSearchWordManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView();
        m.addObject("statusList", DataDicUtil.getTableStatus("BU_HOT_SEARCH_WORD", "STATUS")); //状态
        m.setViewName("/hotSearchWord/wetaoHotSearchList");
        return m;
    }

    /**
     * 微淘热搜列表
     *
     * @param request
     * @param page
     * @param beginSearchLogCount
     * @param endSearchLogCount
     * @return
     */
    @ResponseBody
    @RequestMapping("/wetaoHotSearchWord/getHotSearchWordList.shtml")
    public Map<String, Object> wetaoHotSearchWordList(HttpServletRequest request, Page page, Integer beginSearchLogCount, Integer endSearchLogCount) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<HotSearchWordCustom> dataList = null;
        Integer totalCount = 0;
        try {
            HotSearchWordCustomExample hotSearchWordCustomExample = new HotSearchWordCustomExample();
            HotSearchWordCustomExample.HotSearchWordCustomCriteria wetaoHotSearchWordCustomCriteria = hotSearchWordCustomExample.createCriteria();
            wetaoHotSearchWordCustomCriteria.andDelFlagEqualTo("0").andHotSearchPageEqualTo("2");
            if (!StringUtil.isEmpty(request.getParameter("word"))) {
                wetaoHotSearchWordCustomCriteria.andWordLike("%" + request.getParameter("word") + "%");
            }
            if (!StringUtil.isEmpty(request.getParameter("status"))) {
                wetaoHotSearchWordCustomCriteria.andStatusEqualTo(request.getParameter("status"));
            }
            hotSearchWordCustomExample.setOrderByClause("IFNULL(seq_no,999999999) asc,create_date desc");
            hotSearchWordCustomExample.setLimitStart(page.getLimitStart());
            hotSearchWordCustomExample.setLimitSize(page.getLimitSize());
            totalCount = hotSearchWordService.countByCustomExample(hotSearchWordCustomExample);
            dataList = hotSearchWordService.selectByCustomExample(hotSearchWordCustomExample);

        } catch (Exception e) {
            e.printStackTrace();
        }
        resMap.put("Rows", dataList);
        resMap.put("Total", totalCount);
        return resMap;
    }

    /**
     * 添加微淘热搜页面跳转
     *
     * @param request
     * @return
     */
    @RequestMapping("/wetaoHotSearchWord/addHotSearchWordManager.shtml")
    public ModelAndView wetaoAddHotSearchWordManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView();
        m.setViewName("/hotSearchWord/wetaoSaveHotSearchWord");
        return m;
    }

    /**
     * 添加微淘热搜
     *
     * @param request
     * @return
     */
    @RequestMapping("/wetaoHotSearchWord/addHotSearchWord.shtml")
    public ModelAndView wetaoAddHotSearchWord(HttpServletRequest request) {
        String rtPage = "/success/success";
        Map<String, Object> resMap = new HashMap<String, Object>();
        String tag1 = request.getParameter("tag1");
        String tag2 = request.getParameter("tag2");
        String status = request.getParameter("status");
        String code = null;
        String msg = null;
        try {
            Date date = new Date();
            String staffID = this.getSessionStaffBean(request).getStaffID();
            HotSearchWord hotSearchWord = new HotSearchWord();
            hotSearchWord.setWord(request.getParameter("word"));
            if (!StringUtil.isEmpty(tag1)) {
                hotSearchWord.setTag(tag1);
            }
            if (!StringUtil.isEmpty(tag2)) {
                hotSearchWord.setTag(tag2);
            }
            hotSearchWord.setStatus(status);
            hotSearchWord.setCreateBy(Integer.parseInt(staffID));
            hotSearchWord.setCreateDate(date);
            hotSearchWord.setSource("2"); //2平台创建
            hotSearchWord.setHotSearchPage("2");//微淘热搜标识
            hotSearchWord.setDelFlag("0");
            hotSearchWordService.insertSelective(hotSearchWord);
            code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
            msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
        } catch (Exception e) {
            code = StateCode.JSON_AJAX_ERROR.getStateCode();
            msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
        }
        resMap.put(this.JSON_RESULT_CODE, code);
        resMap.put(this.JSON_RESULT_MESSAGE, msg);
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 删除微淘热搜
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/wetaoHotSearchWord/delHotSearchWord.shtml")
    public Map<String, Object> wetaoDelHotSearchWord(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String code = "200";
        String msg = "操作成功！";
        try {
            Date date = new Date();
            String staffID = this.getSessionStaffBean(request).getStaffID();
            String hotSearchWordId = request.getParameter("hotSearchWordId");
            if (!StringUtil.isEmpty(hotSearchWordId)) {
                HotSearchWordExample hotSearchWordExample = new HotSearchWordExample();
                hotSearchWordExample.createCriteria().andIdEqualTo(Integer.parseInt(hotSearchWordId));
                HotSearchWord hotSearchWord = new HotSearchWord();
                hotSearchWord.setDelFlag("1");
                hotSearchWord.setUpdateDate(date);
                hotSearchWord.setUpdateBy(Integer.parseInt(staffID));
                hotSearchWordService.updateByExampleSelective(hotSearchWord, hotSearchWordExample);
            } else {
                code = "9999";
                msg = "ID为空！";
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

    /**
     * 微淘热搜状态变更
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/wetaoHotSearchWord/updateStatus.shtml")
    public Map<String, Object> wetaoUpdateStatus(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String code = "200";
        String msg = "操作成功！";
        try {
            Date date = new Date();
            String staffID = this.getSessionStaffBean(request).getStaffID();
            String hotSearchWordId = request.getParameter("hotSearchWordId");
            if (!StringUtil.isEmpty(hotSearchWordId)) {
                HotSearchWordExample hotSearchWordExample = new HotSearchWordExample();
                hotSearchWordExample.createCriteria().andIdEqualTo(Integer.parseInt(hotSearchWordId));
                HotSearchWord hotSearchWord = new HotSearchWord();
                hotSearchWord.setStatus(request.getParameter("status"));
                hotSearchWord.setUpdateDate(date);
                hotSearchWord.setUpdateBy(Integer.parseInt(staffID));
                hotSearchWordService.updateByExampleSelective(hotSearchWord, hotSearchWordExample);
            } else {
                code = "9999";
                msg = "ID为空！";
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

    /**
     * 微淘热搜列表排序变更
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/wetaoHotSearchWord/updateSeqNo.shtml")
    public Map<String, Object> wetaoUpdateSeqNo(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String code = "200";
        String msg = "操作成功！";
        try {
            Date date = new Date();
            String staffID = this.getSessionStaffBean(request).getStaffID();
            String hotSearchWordId = request.getParameter("hotSearchWordId");
            String seqNo = request.getParameter("seqNo");
            if (!StringUtil.isEmpty(hotSearchWordId) && !StringUtil.isEmpty(seqNo)) {
                HotSearchWordExample hotSearchWordExample = new HotSearchWordExample();
                hotSearchWordExample.createCriteria().andIdEqualTo(Integer.parseInt(hotSearchWordId));
                HotSearchWord hotSearchWord = new HotSearchWord();
                hotSearchWord.setSeqNo(Integer.parseInt(seqNo));
                hotSearchWord.setUpdateDate(date);
                hotSearchWord.setUpdateBy(Integer.parseInt(staffID));
                hotSearchWordService.updateByExampleSelective(hotSearchWord, hotSearchWordExample);
            } else {
                code = "9999";
                msg = "ID为空！";
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
