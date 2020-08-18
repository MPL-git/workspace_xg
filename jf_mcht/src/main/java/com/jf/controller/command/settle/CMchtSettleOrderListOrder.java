package com.jf.controller.command.settle;

import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.StrKit;
import com.jf.entity.MchtSettleOrder;
import com.jf.entity.MchtUser;
import com.jf.entity.OrderDtl;
import com.jf.entity.SubOrder;
import com.jf.service.MchtSettleOrderService;
import com.jf.service.OrderDtlService;
import com.jf.service.SubOrderService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 结算单-订单明细列表
 *
 * @author huangdl
 */
public class CMchtSettleOrderListOrder extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtSettleOrderListOrder.class);

	@Resource
	private OrderDtlService orderDtlService;
	@Resource
	private SubOrderService subOrderService;
	@Resource
	private MchtSettleOrderService mchtSettleOrderService;

	private int mchtSettleOrderId;
	private String subOrderCode;
	private String productName;

	private int pageNumber;
	private int pageSize;

	private MchtUser currentUser;

	@Override
	public void init() {
		mchtSettleOrderId = getParaToInt("mchtSettleOrderId");
		Assert.moreThanZero(mchtSettleOrderId, "结算ID不能为空");

		subOrderCode = getPara("subOrderCode");
		productName = getPara("productName");

		pageNumber = getParaToInt("pageNumber", 1);
		pageSize = getParaToInt("pageSize", 20);

		currentUser = context.getUserInfo();
	}

	@Override
	public void doCommand() {
		MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.findById(mchtSettleOrderId);
		if(mchtSettleOrder == null || !mchtSettleOrder.getMchtId().equals(currentUser.getMchtId())){
			throw new BizException("未找到该结算单");
		}

		QueryObject queryObject = new QueryObject(pageNumber, pageSize);
		if(StrKit.notBlank(subOrderCode)){
			QueryObject subOrderQuery = new QueryObject();
			subOrderQuery.addQuery("mchtId", currentUser.getMchtId());
			subOrderQuery.addQuery("subOrderCode", subOrderCode);
			List<SubOrder> subOrderList = subOrderService.list(subOrderQuery);
			List<Integer> ids = new ArrayList<>();
			ids.add(0);
			for(SubOrder info : subOrderList){
				ids.add(info.getId());
			}
			queryObject.addQuery("subOrderIds", ids);
		}
		if(StrKit.notBlank(productName)){
			queryObject.addQuery("productName", productName);
		}
		queryObject.addQuery("mchtSettleOrderId", mchtSettleOrderId);
		queryObject.addSort("create_date", QueryObject.SORT_DESC);

		Page<OrderDtl> page = orderDtlService.paginate(queryObject);
		for(OrderDtl model : page.getList()){
			orderDtlService.fillInfo(model);
		}
		data.put("page", page);

	}

}
