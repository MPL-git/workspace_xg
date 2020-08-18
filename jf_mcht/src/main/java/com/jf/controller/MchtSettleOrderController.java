package com.jf.controller;

import com.jf.common.constant.Const;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.controller.command.settle.CMchtSettleOrderDetail;
import com.jf.controller.command.settle.CMchtSettleOrderExport;
import com.jf.controller.command.settle.CMchtSettleOrderList;
import com.jf.controller.command.settle.CMchtSettleOrderListOrder;
import com.jf.controller.command.settle.CMchtSettleOrderListRefundOrder;
import com.jf.controller.command.settle.CMchtSettleOrderPopConfirm;
import com.jf.controller.command.settle.CMchtSettleOrderPopConfirmCommit;
import com.jf.controller.command.settle.CMchtSettleOrderUnionConfirmCommit;
import com.jf.controller.command.settle.CMchtSettleOrderUnionInvoice;
import com.jf.controller.command.settle.CMchtSettleOrderUnionInvoiceCommit;
import com.jf.entity.MchtSettleOrder;
import com.jf.service.MchtSettleOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单结算
 *
 * @author huangdl
 */
@Controller
@RequestMapping("mchtSettleOrder")
public class MchtSettleOrderController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(MchtSettleOrderController.class);

	@Resource
	private MchtSettleOrderService mchtSettleOrderService;

	/**
	 * 订单结算列表页
     */
	@RequestMapping()
	public String index() {

		return page("mchtSettleOrder/list");
	}

	/**
	 * 订单结算列表
	 */
	@ResponseBody
	@RequestMapping("list")
	public String list() {

		return json(doAction(new CMchtSettleOrderList()));
	}

	/**
	 * POP结算单
	 */
	@RequestMapping("detail")
	public String detail() {
		Map<String, Object> data = doAction(new CMchtSettleOrderDetail());

		MchtSettleOrder model = (MchtSettleOrder)data.get("model");
		if(model.getMchtType().equals(Const.MCHT_TYPE_POP)){
			return page(data, "mchtSettleOrder/detail_pop");
		}else{
			return page(data, "mchtSettleOrder/detail_union");
		}

	}

	/**
	 * POP结算单确认页面
	 */
	@RequestMapping("confirm")
	public String confirm() {
		
		return page(doAction(new CMchtSettleOrderPopConfirm()), "mchtSettleOrder/confirm");
	}

	/**
	 * POP结算单确认
	 */
	@ResponseBody
	@RequestMapping("confirmCommit")
	public String confirmCommit() {
		int id = getParaToInt("id");
		Assert.moreThanZero(id, "结算ID不能为空");

		MchtSettleOrder model = mchtSettleOrderService.findById(id);
		if(model == null || !model.getMchtId().equals(getUserInfo().getMchtId())){
			throw new BizException("未找到该结算单");
		}
		if(model.getMchtType().equals(Const.MCHT_TYPE_POP)){
			return json(doAction(new CMchtSettleOrderPopConfirmCommit()));
		}else{
			return json(doAction(new CMchtSettleOrderUnionConfirmCommit()));
		}

	}


	/**
	 * SPOP结算单开票页面
	 */
	@RequestMapping("invoice")
	public String invoice() {

		return page(doAction(new CMchtSettleOrderUnionInvoice()), "mchtSettleOrder/invoice");
	}

	/**
	 * SPOP结算单开票
	 */
	@ResponseBody
	@RequestMapping("invoiceCommit")
	public String invoiceCommit() {

		return json(doAction(new CMchtSettleOrderUnionInvoiceCommit()));
	}



	/**
	 * 订单明细页面
	 */
	@RequestMapping("listOrderPage")
	public String listOrderPage() {
		int mchtSettleOrderId = getParaToInt("mchtSettleOrderId");
		Assert.moreThanZero(mchtSettleOrderId, "结算ID不能为空");

		MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.findById(mchtSettleOrderId);
		if(mchtSettleOrder == null || !mchtSettleOrder.getMchtId().equals(getUserInfo().getMchtId())){
			throw new BizException("未找到该结算单");
		}

		Map<String, Object> data = new HashMap<>();
		data.put("mchtSettleOrder", mchtSettleOrder);
		return page(data, "mchtSettleOrder/listOrder");
	}

	/**
	 * 订单明细列表
	 */
	@ResponseBody
	@RequestMapping("listOrder")
	public String listOrder(){

		return json(doAction(new CMchtSettleOrderListOrder()));
	}


	/**
	 * 直赔单列表页
	 */
	@RequestMapping("listRefundOrderPage")
	public String listRefundOrderPage() {

		return page("mchtSettleOrder/listRefundOrder");
	}

	/**
	 * 直赔单列表
	 */
	@ResponseBody
	@RequestMapping("listRefundOrder")
	public String listRefundOrder() {

		return json(doAction(new CMchtSettleOrderListRefundOrder()));
	}


	/**
	 * 结算单-导出
	 */
	@RequestMapping("export")
	public void export(HttpServletResponse response) throws Exception {

		exportExcel(doAction(new CMchtSettleOrderExport()), response);
	}

}
