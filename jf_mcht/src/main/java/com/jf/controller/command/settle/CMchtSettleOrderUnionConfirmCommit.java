package com.jf.controller.command.settle;

import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.entity.MchtSettleOrder;
import com.jf.entity.MchtUser;
import com.jf.service.MchtSettleOrderService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;

/**
 * SPOP结算单商家确认（提交）
 *
 * @author huangdl
 */
public class CMchtSettleOrderUnionConfirmCommit extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtSettleOrderUnionConfirmCommit.class);

	@Resource
	private MchtSettleOrderService mchtSettleOrderService;

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

		//if(model.getMchtInvoiceStatus().equals(Const.MCHT_SETTLE_ORDER_MCHT_INVOICE_STATUS_NO)){
		//	throw new BizException("请先开票再确认");
		//}

		mchtSettleOrderService.mchtConfirmed(id);

	}

}
