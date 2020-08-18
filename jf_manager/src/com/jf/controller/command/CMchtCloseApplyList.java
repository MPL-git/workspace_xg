package com.jf.controller.command;

import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.StrKit;
import com.jf.entity.StaffBean;
import com.jf.service.MchtCloseApplyService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 关店列表
 *
 * @author huangdl
 */
public class CMchtCloseApplyList extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtCloseApplyList.class);

	@Resource
	private MchtCloseApplyService mchtCloseApplyService;


	private String merchantsAuditStatus;	//招商审核状态
	private String operateAuditStatus;	//运营审核状态

	private String mchtInfoStatus;	//商家合作状态
	private String mchtCode;	//商家编号
	private String mchtName;	//商家简称

	// 排序：1 升序 2 逆序
	private int orderById;	//按ID排序

	private int pageNumber;
	private int pageSize;

	private StaffBean currentUser;

	@Override
	public void init() {
		merchantsAuditStatus = getPara("merchantsAuditStatus");
		operateAuditStatus = getPara("operateAuditStatus");

		mchtInfoStatus = getPara("mchtInfoStatus");
		mchtCode = getPara("mcht_code");
		mchtName = getPara("mcht_name");

		orderById = getParaToInt("orderById");
		pageNumber = getParaToInt("page", 1);
		pageSize = getParaToInt("pagesize", 100);

		currentUser = context.getSessionStaffBean();
	}

	@Override
	public void doCommand() {
		QueryObject queryObject = new QueryObject(pageNumber, pageSize);

		if(StrKit.notBlank(merchantsAuditStatus)){
			queryObject.addQuery("merchantsAuditStatus", merchantsAuditStatus);
		}
		if(StrKit.notBlank(operateAuditStatus)){
			queryObject.addQuery("operateAuditStatus", operateAuditStatus);
		}


		//查询商家
		if(StrKit.notBlank(mchtInfoStatus)){
			queryObject.addQuery("mchtInfoStatus", mchtInfoStatus);
		}
		if(StrKit.notBlank(mchtCode)){
			queryObject.addQuery("mchtCode", mchtCode);
		}
		if(StrKit.notBlank(mchtName)){
			queryObject.addQuery("mchtShortName", mchtName);
		}


		if(orderById > 0){
			queryObject.addSort("id", orderById);
		}

		Page<Map<String, Object>> page = mchtCloseApplyService.paginateCustom(queryObject);

		data.put("Rows", page.getList());
		data.put("Total", page.getTotalRow());
	}

}
