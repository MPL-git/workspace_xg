package com.jf.controller.command;

import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.DateTimeUtil;
import com.jf.common.ext.util.StrKit;
import com.jf.entity.StaffBean;
import com.jf.service.MchtContractService;
import net.sf.json.JSONArray;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.util.*;

/**
 * 商家统计
 *
 * @author huangdl
 */
public class CMchtInfoStatExpire extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtInfoStatExpire.class);

	@Resource
	private MchtContractService mchtContractService;

	private String auditStatus;	//合同审核状态
	private String archiveStatus;	//合同归档状态

	private String mchtInfoStatus;	//商家合作状态
	private String mchtInfoStatusIn;	//商家合作状态，查询多个状态用逗号隔开[1,2]

	private StaffBean currentUser;

	@Override
	public void init() {
		auditStatus = getPara("auditStatus");
		archiveStatus = getPara("archiveStatus");

		mchtInfoStatus = getPara("mchtInfoStatus");
		mchtInfoStatusIn = getPara("mchtInfoStatusIn");

		currentUser = context.getSessionStaffBean();
	}

	@Override
	public void doCommand() {
		QueryObject queryObject = new QueryObject();
		//查询合同
		if(StrKit.notBlank(auditStatus)){
			queryObject.addQuery("auditStatus", auditStatus);
		}
		if(StrKit.notBlank(archiveStatus)){
			queryObject.addQuery("archiveStatus", archiveStatus);
		}

		//查询商家
		if(StrKit.notBlank(mchtInfoStatus)){
			queryObject.addQuery("mchtInfoStatus", mchtInfoStatus);
		}
		if(StrKit.notBlank(mchtInfoStatusIn)){
			JSONArray jsonArray = JSONArray.fromObject(mchtInfoStatusIn);
			queryObject.addQuery("mchtInfoStatusIn", Arrays.asList(jsonArray.toArray()));
		}

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(10);
		Date now =new Date();
		Map<String, Object> info;
		Date date;
		String month;
		for(int i = -4; i <= 5; i++){
			info = new HashMap<String, Object>();
			date = DateTimeUtil.plusMonths(now, i);
			month = DateTimeUtil.formatDate(date, "yyyy-MM");
			queryObject.addQuery("endDateAfter", DateTimeUtil.getFirstdayOfMonth(date));
			queryObject.addQuery("endDateBefore", DateTimeUtil.getLastdayOfMonth(date));
			info.put("month", month);	//月份
			info.put("count", mchtContractService.count(queryObject));	//商家数量
			list.add(info);
		}

		data.put("Rows", list);
		data.put("Total", list.size());
	}

}
