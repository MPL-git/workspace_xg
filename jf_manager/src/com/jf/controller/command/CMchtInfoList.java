package com.jf.controller.command;

import com.jf.common.constant.Const;
import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.DateTimeUtil;
import com.jf.common.ext.util.StrKit;
import com.jf.entity.PlatformContact;
import com.jf.entity.StaffBean;
import com.jf.service.MchtInfoService;
import com.jf.service.PlatformContactService;
import net.sf.json.JSONArray;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 商家列表
 *
 * @author huangdl
 */
public class CMchtInfoList extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtInfoList.class);

	@Resource
	private MchtInfoService mchtInfoService;
	@Resource
	private PlatformContactService platformContactService;

	private String status;	//商家合作状态
	private String statusIn;	//商家合作状态，查询多个状态用逗号隔开[1,2]
	private String mchtCode;	//商家编号
	private String mchtName;	//商家简称
	private String mchtType;	//商家类型
	private String agreementEndYear; //合同到期年份
	private String agreementEndMonth; //合同到期月份
	private boolean isMyMerchants;	//只看本人招商对接
	private boolean isMyFawu;		//只看本人法务对接

	private String contractAuditStatus;	//合同审核状态
	private String contractArchiveStatus;	//合同归档状态
	private String contractArchiveStatusIn;	//合同归档状态，多个状态用逗号隔开1,2
	private String contractCode;	//合同编号
	private String contractType;	//合同类型
	private String contractMchtSendStatus;	//合同商家寄件状态

	// 排序：1 升序 2 逆序
	private int orderById;	//按ID排序
	private int orderByAgreementBeginDate;	//按合同开始时间排序
	private int orderByAgreementEndDate;	//合同到期时间排序

	private int pageNumber;
	private int pageSize;

	private StaffBean currentUser;

	@Override
	public void init() {
		status = getPara("status");
		statusIn = getPara("statusIn");
		mchtCode = getPara("mcht_code");
		mchtName = getPara("mcht_name");
		mchtType = getPara("mcht_type");
		agreementEndYear = getPara("agreementEndYear");
		agreementEndMonth = getPara("agreementEndMonth");

		isMyMerchants = getParaToBoolean("is_my_merchants");
		isMyFawu = getParaToBoolean("is_my_fawu");

		contractAuditStatus = getPara("contractAuditStatus");
		contractArchiveStatus = getPara("contractArchiveStatus");
		contractArchiveStatusIn = getPara("contractArchiveStatusIn");
		contractCode = getPara("contractCode");
		contractType = getPara("contractType");
		contractMchtSendStatus = getPara("contractMchtSendStatus");
	
		orderById = getParaToInt("orderById");
		orderByAgreementBeginDate = getParaToInt("orderByAgreementBeginDate");
		orderByAgreementEndDate = getParaToInt("orderByAgreementEndDate");
		pageNumber = getParaToInt("page", 1);
		pageSize = getParaToInt("pagesize", 10);

		currentUser = context.getSessionStaffBean();
	}

	@Override
	public void doCommand() {
		QueryObject queryObject = new QueryObject(pageNumber, pageSize);
		if(StrKit.notBlank(status)){
			queryObject.addQuery("status", status);
		}
		if(StrKit.notBlank(statusIn)){
			JSONArray jsonArray = JSONArray.fromObject(statusIn);
			queryObject.addQuery("statusIn", Arrays.asList(jsonArray.toArray()));
		}

		if(StrKit.notBlank(mchtCode)){
			queryObject.addQuery("mchtCode", mchtCode);
		}
		if(StrKit.notBlank(mchtName)){
			queryObject.addQuery("shortName", mchtName);
		}
		if(StrKit.notBlank(mchtType)){
			queryObject.addQuery("mchtType", mchtType);
		}
		
		if(StrKit.notBlank(agreementEndYear)){
			String agreementEndDateAfter;
			String agreementEndDateBefore;
			if(StrKit.notBlank(agreementEndMonth)){
				agreementEndDateAfter = agreementEndYear + "-" + agreementEndMonth + "-01 00:00:00";
				agreementEndDateBefore = agreementEndYear + "-" + agreementEndMonth + "-31 23:59:59";
			}else{
				agreementEndDateAfter = agreementEndYear + "-01-01 00:00:00";
				agreementEndDateBefore = agreementEndYear + "-12-31 23:59:59";
			}
			queryObject.addQuery("agreementEndDateAfter", DateTimeUtil.parseDate(agreementEndDateAfter, "yyyy-MM-dd hh:mm:ss"));
			queryObject.addQuery("agreementEndDateBefore", DateTimeUtil.parseDate(agreementEndDateBefore, "yyyy-MM-dd hh:mm:ss"));
		}

		List<Integer> platformContactIdList = new ArrayList<Integer>();
		if(isMyMerchants){
			int platContactId = 0;
			PlatformContact platContact = platformContactService.findByStaffId(Integer.valueOf(currentUser.getStaffID()), Const.PLAT_CONTACT_TYPE_MERCHANTS);
			if(platContact != null){
				platContactId = platContact.getId();
			}
			platformContactIdList.add(platContactId);
		}
		if(isMyFawu){
			int platContactId = 0;
			PlatformContact platContact = platformContactService.findByStaffId(Integer.valueOf(currentUser.getStaffID()), Const.PLAT_CONTACT_TYPE_FAWU);
			if(platContact != null){
				platContactId = platContact.getId();
			}
			platformContactIdList.add(platContactId);
		}
		queryObject.addQuery("platformContactIdList", platformContactIdList);

		if(StrKit.notBlank(contractAuditStatus)){
			queryObject.addQuery("contractAuditStatus", contractAuditStatus);
		}
		if(StrKit.notBlank(contractArchiveStatus)){
			queryObject.addQuery("contractArchiveStatus", contractArchiveStatus);
		}
		if(StrKit.notBlank(contractArchiveStatusIn)){
			JSONArray jsonArray = JSONArray.fromObject(contractArchiveStatusIn);
			queryObject.addQuery("contractArchiveStatusIn", Arrays.asList(jsonArray.toArray()));
		}
		if(StrKit.notBlank(contractCode)){
			queryObject.addQuery("contractCode", contractCode);
		}
		if(StrKit.notBlank(contractType)){
			queryObject.addQuery("contractType", contractType);
		}
		if(StrKit.notBlank(contractMchtSendStatus)){
			queryObject.addQuery("contractMchtSendStatus", contractMchtSendStatus);
		}

		if(StrKit.notBlank(agreementEndYear) && StrKit.notBlank(agreementEndMonth)){
			//到期月份不为空时， 按到期日期正序排序
			queryObject.addSort("agreement_end_date", QueryObject.SORT_ASC);
			queryObject.addSort("id", QueryObject.SORT_ASC);
		}else{
			if(orderById > 0){
				queryObject.addSort("id", orderById);
			}
			if(orderByAgreementBeginDate > 0){
				queryObject.addSort("agreement_begin_date", orderByAgreementBeginDate);
			}
			if(orderByAgreementEndDate > 0){
				queryObject.addSort("agreement_end_date", orderByAgreementEndDate);
			}
		}

		Page<Map<String, Object>> page = mchtInfoService.paginateCustom(queryObject);

		data.put("Rows", page.getList());
		data.put("Total", page.getTotalRow());
	}

}
