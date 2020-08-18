package com.jf.controller.command.settle;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jf.common.constant.Const;
import com.jf.common.enums.InvoiceTypeEnum;
import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.entity.Express;
import com.jf.entity.MchtSettleOrder;
import com.jf.entity.MchtUser;
import com.jf.service.ExpressService;
import com.jf.service.MchtSettleOrderService;
import com.jf.service.OrderDtlService;

/**
 * 结算单详情
 *
 * @author huangdl
 */
public class CMchtSettleOrderDetail extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtSettleOrderDetail.class);

	@Resource
	private MchtSettleOrderService mchtSettleOrderService;
	@Resource
	private ExpressService expressService;
	@Resource
	private OrderDtlService orderDtlService;
	private int id;

	private MchtUser currentUser;

	@Override
	public void init() {
		id = getParaToInt("id");
		Assert.moreThanZero(id, "结算ID不能为空");

		currentUser = context.getUserInfo();
	}

	@Override
	public void doCommand() {
		MchtSettleOrder model = mchtSettleOrderService.findById(id);
		if(model == null || !model.getMchtId().equals(currentUser.getMchtId())){
			throw new BizException("未找到该结算单");
		}

		data.put("model", model);

		// 开票类型
		model.put("mchtCollectTypeStr", InvoiceTypeEnum.getNameOf(model.getMchtCollectType()));

		// 付款状态
		String payStatusStr = "未知状态";
		if(model.getPayStatus().equals(Const.MCHT_SETTLE_ORDER_PAY_STATUS_QUEUE_NOT)){
			payStatusStr = "未排款";
		}else if(model.getPayStatus().equals(Const.MCHT_SETTLE_ORDER_PAY_STATUS_QUEUE_YES)){
			payStatusStr = "已排款";
		}else if(model.getPayStatus().equals(Const.MCHT_SETTLE_ORDER_PAY_STATUS_PAID)){
			payStatusStr = "已付款";
		}else if(model.getPayStatus().equals(Const.MCHT_SETTLE_ORDER_PAY_STATUS_CONFIRMED)){
			payStatusStr = "已确认";
		}
		model.put("payStatusStr", payStatusStr);

		// 确认状态
		String confirmStatusStr = "待生成";
		if(model.getConfirmStatus().equals(Const.MCHT_SETTLE_ORDER_CONFIRM_STATUS_WAIT_MCHT)){
			confirmStatusStr = "待商家确认";
		}else if(model.getConfirmStatus().equals(Const.MCHT_SETTLE_ORDER_CONFIRM_STATUS_WAIT_PLAT)){
			confirmStatusStr = "待平台确认";
		}else if(model.getConfirmStatus().equals(Const.MCHT_SETTLE_ORDER_CONFIRM_STATUS_CONFIRMED)){
			confirmStatusStr = "已确认";
		}
		model.put("confirmStatusStr", confirmStatusStr);

		if(model.getMchtType().equals(Const.MCHT_TYPE_UNION)){
			// 开票状态
			String mchtInvoiceStatusStr = "未处理";
			if(model.getMchtInvoiceStatus().equals(Const.MCHT_SETTLE_ORDER_MCHT_INVOICE_STATUS_YES)){
				mchtInvoiceStatusStr = "已寄出";
				Express express = expressService.findById(model.getMchtInvoiceExpressId());
				mchtInvoiceStatusStr += "（" + express.getName() + " " + model.getMchtInvoiceExpressNo()  + "）";
			}
			model.put("mchtInvoiceStatusStr", mchtInvoiceStatusStr);
		}
		BigDecimal platformDiscount = orderDtlService.sumPlatformAndIntegralPreferential(model.getId());
		if(platformDiscount == null){
			platformDiscount = new BigDecimal(0.00);
		}
		model.put("platformDiscount", platformDiscount);
		model.put("needSettleAmount", model.getSettleAmount().subtract(model.getDepositAmount()));
	}



}
