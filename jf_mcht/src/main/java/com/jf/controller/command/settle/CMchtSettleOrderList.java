package com.jf.controller.command.settle;

import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.entity.MchtSettleOrder;
import com.jf.entity.MchtUser;
import com.jf.service.MchtSettleOrderService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;

/**
 * 结算单列表
 *
 * @author huangdl
 */
public class CMchtSettleOrderList extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtSettleOrderList.class);

	@Resource
	private MchtSettleOrderService mchtSettleOrderService;


	private int confirmStatus;

	private int pageNumber;
	private int pageSize;

	private MchtUser currentUser;

	@Override
	public void init() {
		confirmStatus = getParaToInt("confirmStatus");

		pageNumber = getParaToInt("pageNumber", 1);
		pageSize = getParaToInt("pageSize", 20);

		currentUser = context.getUserInfo();
	}

	@Override
	public void doCommand() {
		QueryObject queryObject = new QueryObject(pageNumber, pageSize);
		queryObject.addQuery("mchtId", currentUser.getMchtId());
		if(confirmStatus > 0){
			queryObject.addQuery("confirmStatus", confirmStatus);
		}
		queryObject.addSort("create_date", QueryObject.SORT_DESC);
		Page<MchtSettleOrder> page = mchtSettleOrderService.paginate(queryObject);
		for(MchtSettleOrder mso:page.getList()){
			mso.put("needSettleAmount", mso.getSettleAmount().subtract(mso.getDepositAmount()));
		}
		data.put("page", page);
	}

}
