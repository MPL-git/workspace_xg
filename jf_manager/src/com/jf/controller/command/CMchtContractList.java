package com.jf.controller.command;

import com.jf.common.constant.Const;
import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.DateTimeUtil;
import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.StringUtils;
import com.jf.entity.MchtContract;
import com.jf.entity.PlatformContact;
import com.jf.entity.StaffBean;
import com.jf.service.MchtContractService;
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
 * 合同列表
 *
 * @author huangdl
 */
public class CMchtContractList extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtContractList.class);

	@Resource
	private MchtContractService mchtContractService;
	@Resource
	private PlatformContactService platformContactService;

	private String auditStatus;	//合同审核状态
	private String archiveStatus;	//合同归档状态
	private String archiveStatusIn;	//合同归档状态，多个状态用逗号隔开[1,2]
	private String contractCode;	//合同编号
	private String contractType;	//合同类型
	private String isMchtSend;	//合同商家寄件状态
	private String endYear; //合同到期年份
	private String endMonth; //合同到期月份
	private int productTypeId;	// 商品类目
	private int platContactStaffId;	//平台用户对接人

	private String mchtInfoStatus;	//商家合作状态
	private String mchtInfoStatusIn;	//商家合作状态，查询多个状态用逗号隔开[1,2]
	private String mchtCode;	//商家编号
	private String mchtName;	//商家简称
	private String mchtType;	//商家类型
    private String isPlatformSend;
    private String platformExpressNo;
	private boolean isMyMerchants;	//只看本人招商对接
	private boolean isMyFawu;		//只看本人法务对接
	private String contractarchivedateBegin;//合同归档日期开始日期筛选
    private String contractarchivedateEnd;//合同归档日期结束日期筛选
    
	// 排序：1 升序 2 逆序
	private int orderById;	//按ID排序
	private int orderByBeginDate;	//按合同开始时间排序
	private int orderByEndDate;	//按合同到期时间排序
	private int orderByUploadDate;	//按合同上传时间排序
	private int orderByAuditDate;	//按合同审核时间排序

	private int pageNumber;
	private int pageSize;
	private String platformContactId;
	private String notEqualMchtInfoStatus;
	private String mchtInfoArchiveStatus;
	private String settledType;
	private String isManageSelf;//是否自营
	private String cmd;

	private StaffBean currentUser;

	@Override
	public void init() {
		settledType = getPara("settledType");
		isManageSelf = getPara("isManageSelf");
		mchtInfoArchiveStatus = getPara("mchtInfoArchiveStatus");
		notEqualMchtInfoStatus = getPara("notEqualMchtInfoStatus");
		platContactStaffId = getParaToInt("platContactStaffId");
		platformContactId = getPara("platformContactId");
		productTypeId = getParaToInt("productTypeId");
		auditStatus = getPara("auditStatus");
		archiveStatus = getPara("archiveStatus");
		archiveStatusIn = getPara("archiveStatusIn");
		contractCode = getPara("contractCode");
		contractType = getPara("contractType");
		isMchtSend = getPara("isMchtSend");
		isPlatformSend = getPara("isPlatformSend");
		platformExpressNo = getPara("platformExpressNo");
		endYear = getPara("endYear");
		endMonth = getPara("endMonth");
		
		contractarchivedateBegin=getPara("contractarchivedateBegin");
		contractarchivedateEnd=getPara("contractarchivedateEnd");
	
		mchtInfoStatus = getPara("mchtInfoStatus");
		mchtInfoStatusIn = getPara("mchtInfoStatusIn");
		mchtCode = getPara("mcht_code");
		mchtName = getPara("mcht_name");
		mchtType = getPara("mcht_type");

		isMyMerchants = getParaToBoolean("is_my_merchants");
		isMyFawu = getParaToBoolean("is_my_fawu");

		orderById = getParaToInt("orderById");
		orderByBeginDate = getParaToInt("orderByBeginDate");
		orderByEndDate = getParaToInt("orderByEndDate");
		orderByUploadDate = getParaToInt("orderByUploadDate");
		orderByAuditDate = getParaToInt("orderByAuditDate");
		
		cmd = context.getAttr("cmd");
		pageNumber = getParaToInt("page", 1);
		pageSize = getParaToInt("pagesize", 20);

		currentUser = context.getSessionStaffBean();
	}

	@Override
	public void doCommand() {
		QueryObject queryObject = new QueryObject(pageNumber, pageSize);
		queryObject.addSort("create_date", QueryObject.SORT_DESC);
		//查询合同
		if(StrKit.notBlank(auditStatus)){
			queryObject.addQuery("auditStatus", auditStatus);
		}
		if(StrKit.notBlank(archiveStatus)){
			queryObject.addQuery("archiveStatus", archiveStatus);
		}
//		if(StrKit.notBlank(archiveStatusIn)){
//			JSONArray jsonArray = JSONArray.fromObject(archiveStatusIn);
//			queryObject.addQuery("archiveStatusIn", Arrays.asList(jsonArray.toArray()));
//		}
		if(StrKit.notBlank(contractCode)){
			queryObject.addQuery("contractCode", contractCode);
		}
		if(StrKit.notBlank(contractType)){
			queryObject.addQuery("contractType", contractType);
		}
		if(StrKit.notBlank(isMchtSend)){
			queryObject.addQuery("isMchtSend", isMchtSend);
		}
		if(StrKit.notBlank(isPlatformSend)){
			queryObject.addQuery("isPlatformSend", isPlatformSend);
		}
		if(StrKit.notBlank(platformExpressNo)){
			queryObject.addQuery("platformExpressNo", platformExpressNo);
		}
		if(StrKit.notBlank(contractarchivedateBegin)){
			queryObject.addQuery("contractarchivedateBegin", DateTimeUtil.parseDate(contractarchivedateBegin, "yyyy-MM-dd"));
		}
		if(StrKit.notBlank(contractarchivedateEnd)){
			queryObject.addQuery("contractarchivedateEnd", DateTimeUtil.parseDate(contractarchivedateEnd, "yyyy-MM-dd"));
		}
			
		if(StrKit.notBlank(endYear)){
			String endDateAfter;
			String endDateBefore;
			if(StrKit.notBlank(endMonth)){
				endDateAfter = endYear + "-" + endMonth + "-01 00:00:00";
				endDateBefore = endYear + "-" + endMonth + "-31 23:59:59";
			}else{
				endDateAfter = endYear + "-01-01 00:00:00";
				endDateBefore = endYear + "-12-31 23:59:59";
			}
			queryObject.addQuery("endDateAfter", DateTimeUtil.parseDate(endDateAfter, "yyyy-MM-dd hh:mm:ss"));
			queryObject.addQuery("endDateBefore", DateTimeUtil.parseDate(endDateBefore, "yyyy-MM-dd hh:mm:ss"));
		}
		
		
		if(StrKit.notBlank(notEqualMchtInfoStatus)){
			queryObject.addQuery("notEqualMchtInfoStatus", "0");
		}
		//查询商家
		if(StrKit.notBlank(mchtInfoStatus)){
			queryObject.addQuery("mchtInfoStatus", mchtInfoStatus);
		}
		if(StrKit.notBlank(mchtInfoStatusIn)){
			JSONArray jsonArray = JSONArray.fromObject(mchtInfoStatusIn);
			queryObject.addQuery("mchtInfoStatusIn", Arrays.asList(jsonArray.toArray()));
		}
		if(StrKit.notBlank(mchtCode)){
			queryObject.addQuery("mchtCode", mchtCode);
		}
		if(StrKit.notBlank(mchtName)){
			queryObject.addQuery("mchtShortName", mchtName);
		}
		if(StrKit.notBlank(mchtType)){
			queryObject.addQuery("mchtType", mchtType);
		}

		if(productTypeId > 0){
			queryObject.addQuery("productTypeId", productTypeId);
		}
		if(platContactStaffId > 0){
			queryObject.addQuery("platContactStaffId", platContactStaffId);
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
		if(!StringUtils.isEmpty(platformContactId)){
			platformContactIdList.add(Integer.parseInt(platformContactId));
		}
		queryObject.addQuery("platformContactIdList", platformContactIdList);



		if(StrKit.notBlank(endYear) && StrKit.notBlank(endMonth)){
			//到期月份不为空时， 按到期日期正序排序
			queryObject.addSort("end_date", QueryObject.SORT_ASC);
			queryObject.addSort("id", QueryObject.SORT_ASC);
		}else{
			if(orderById > 0){
				queryObject.addSort("id", orderById);
			}
			if(orderByBeginDate > 0){
				queryObject.addSort("begin_date", orderByBeginDate);
			}
			if(orderByEndDate > 0){
				queryObject.addSort("end_date", orderByEndDate);
			}
			if(orderByAuditDate > 0){
				queryObject.addSort("audit_date", orderByAuditDate);
			}
			if(orderByUploadDate > 0){
				queryObject.addSort("upload_date", orderByUploadDate);
			}
		}
		if(StrKit.notBlank(mchtInfoArchiveStatus)){
			queryObject.addQuery("mchtInfoArchiveStatus", mchtInfoArchiveStatus);
		}
		if(StrKit.notBlank(settledType)){
			queryObject.addQuery("settledType", settledType);
		}

		if(StrKit.notBlank(isManageSelf)){
			queryObject.addQuery("isManageSelf", isManageSelf);
		}
		
		if(StrKit.notBlank(cmd) && cmd.equals("export")){
			List<MchtContract> list = mchtContractService.listCustom(queryObject);
			data.put("list", list);
			return;
		}
		
		Page<Map<String, Object>> page = mchtContractService.paginateCustom(queryObject);

		data.put("Rows", page.getList());
		data.put("Total", page.getTotalRow());
	}

}
