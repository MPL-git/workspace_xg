package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.base.ServiceException;
import com.jf.common.constant.Const;
import com.jf.common.utils.JsonUtils;
import com.jf.entity.CustomerServiceOrderCustom;
import com.jf.service.CustomerServiceOrderService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomServiceOrderController {

    private static final Logger logger = Logger.getLogger(CustomServiceOrderController.class);

    @Resource
    private CustomerServiceOrderService service;

    /**
     * 退货退款
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/api/custom/agreeCustomService")
    @ResponseBody
    public ResponseMsg agreeCustomService(HttpServletRequest request) {
        JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
        JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
        if (JsonUtils.isEmpty(reqDataJson, "id")) {
            return new ResponseMsg(ResponseMsg.ERROR, "售后单id不能为空");
        }
        try {
            // 此处只校验该请求参数是否合法，实际更新使用值为系统指定变量值
            if (JsonUtils.isEmpty(reqDataJson, "proStatus")) {
                throw new ArgException("操作状态不能为空");
            }
            if (!Const.CUSTOMER_ORDER_PRO_STATUS_B5.equals(reqDataJson.getString("proStatus"))) {
                throw new ArgException("请求非法，操作状态错误");
            }
            if (JsonUtils.isEmpty(reqDataJson, "supplierUserId")) {
                throw new ArgException("操作用户id不能为空");
            }

            CustomerServiceOrderCustom customerServiceOrderCustom = new CustomerServiceOrderCustom();
            customerServiceOrderCustom.setId(Integer.parseInt(reqDataJson.getString("id")));
            customerServiceOrderCustom.setSupplierRemarks(reqDataJson.getString("reasonDescription"));
            customerServiceOrderCustom.setContent(reqDataJson.getString("content"));
            customerServiceOrderCustom.setCustomOrderPics(reqDataJson.getString("customOrderPics"));
            customerServiceOrderCustom.setSupplierUserId(Integer.parseInt(reqDataJson.getString("supplierUserId")));

            service.agreeB4FromSupplier(customerServiceOrderCustom);

            return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
        } catch (ServiceException e) {
            logger.warn("--------------id：" + reqDataJson.getString("id") + "的售后单退货退款失败----------------------");
            e.printStackTrace();
            return new ResponseMsg(ResponseMsg.ERROR, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
        }
    }

    /**
     * 退货退款
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/api/custom/refuseCustomService")
    @ResponseBody
    public ResponseMsg refuseCustomService(HttpServletRequest request) {
        JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
        JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
        if (JsonUtils.isEmpty(reqDataJson, "id")) {
            return new ResponseMsg(ResponseMsg.ERROR, "售后单id不能为空");
        }
        try {
            // 此处只校验该请求参数是否合法，实际更新使用值为系统指定变量值
            if (JsonUtils.isEmpty(reqDataJson, "proStatus")) {
                throw new ArgException("操作状态不能为空");
            }
            if (!Const.CUSTOMER_ORDER_PRO_STATUS_B6.equals(reqDataJson.getString("proStatus"))) {
                throw new ArgException("请求非法，操作状态错误");
            }
            if (JsonUtils.isEmpty(reqDataJson, "reasonDescription")) {
                throw new ArgException("具体原因不能为空");
            }
            if (JsonUtils.isEmpty(reqDataJson, "reason")) {
                throw new ArgException("拒绝理由不能为空");
            }
            if (JsonUtils.isEmpty(reqDataJson, "content")) {
                throw new ArgException("流程备注内容不能为空");
            }
            if (JsonUtils.isEmpty(reqDataJson, "customOrderPics")) {
                throw new ArgException("图片不能为空");
            }
            if (JsonUtils.isEmpty(reqDataJson, "supplierUserId")) {
                throw new ArgException("操作用户id不能为空");
            }

            CustomerServiceOrderCustom customerServiceOrderCustom = new CustomerServiceOrderCustom();
            customerServiceOrderCustom.setId(Integer.parseInt(reqDataJson.getString("id")));
            customerServiceOrderCustom.setSupplierRemarks(reqDataJson.getString("reasonDescription"));
            customerServiceOrderCustom.setSupplierRejectReason(reqDataJson.getString("reason"));
            customerServiceOrderCustom.setContent(reqDataJson.getString("content"));
            customerServiceOrderCustom.setCustomOrderPics(reqDataJson.getString("customOrderPics"));
            customerServiceOrderCustom.setSupplierUserId(Integer.parseInt(reqDataJson.getString("supplierUserId")));

            service.refuseB4FromSupplier(customerServiceOrderCustom);

            return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
        } catch (ServiceException e) {
            logger.warn("--------------id：" + reqDataJson.getString("id") + "的售后单退货退款失败----------------------");
            e.printStackTrace();
            return new ResponseMsg(ResponseMsg.ERROR, e.getMessage());
        } catch (ArgException e) {
            logger.warn("--------------id：" + reqDataJson.getString("id") + "的售后单退货退款失败----------------------");
            e.printStackTrace();
            return new ResponseMsg(ResponseMsg.ERROR, "短信发送失败");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
        }
    }
}
