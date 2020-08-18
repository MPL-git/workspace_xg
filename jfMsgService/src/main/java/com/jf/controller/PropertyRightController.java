package com.jf.controller;

import com.jf.common.base.ResponseMsg;
import com.jf.service.PropertyRightAppealService;
import com.jf.service.PropertyRightComplainService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: 知识产权
 * @Author: zhen.li
 * @Date: 2018/12/26 16:12
 */
@Controller
@RequestMapping("/propertyRight")
public class PropertyRightController {

    @Resource
    private PropertyRightAppealService propertyRightAppealService;

    @Resource
    private PropertyRightComplainService propertyRightComplainService;

    /**
     * 更新超期未声明投诉单的主状态为完成
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateOverComplainDueStatus")
    public ResponseMsg updateOverComplainDueStatus() {
        propertyRightAppealService.updateOverComplainDueStatus();
        return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
    }

    /**
     * 更新超期未起诉投诉单的主状态为撤销
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateOverProsecutionDueStatus")
    public ResponseMsg updateOverProsecutionDueStatus() {
        propertyRightComplainService.updateOverProsecutionDueStatus();
        return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
    }

}
