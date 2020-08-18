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
 * SPOP结算结算单商家开票（提交）
 *
 * @author huangdl
 */
public class CMchtSettleOrderUnionInvoiceCommit extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtSettleOrderUnionInvoiceCommit.class);

	@Resource
	private MchtSettleOrderService mchtSettleOrderService;

	private int id;		// 结算单ID
	private int expressId;	// 快递公司
	private String expressNo;	// 快递单号

	private MchtUser currentUser;

	@Override
	public void init() {
		id = getParaToInt("id");
		Assert.moreThanZero(id, "结算ID不能为空");

		expressId = getParaToInt("expressId");
		Assert.moreThanZero(expressId, "快递ID不能为空");

		expressNo = getPara("expressNo");
		Assert.notBlank(expressNo, "快递单号不能为空");

		currentUser = context.getUserInfo();
	}

	@Override
	public void doCommand() {
		MchtSettleOrder model = mchtSettleOrderService.findById(id);
		if(model == null || !model.getMchtId().equals(currentUser.getMchtId())){
			throw new BizException("未找到该结算单");
		}

		mchtSettleOrderService.mchtInvoiced(id, expressId, expressNo);

	}

}
