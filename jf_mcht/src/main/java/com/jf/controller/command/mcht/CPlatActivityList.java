package com.jf.controller.command.mcht;

import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.StrKit;
import com.jf.entity.ActivityArea;
import com.jf.entity.MchtUser;
import com.jf.service.ActivityAreaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 官方活动列表
 *
 * @author huangdl
 */
public class CPlatActivityList extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CPlatActivityList.class);

	@Resource
	private ActivityAreaService activityAreaService;


	private String name;
	private int pageStatus;

	private int pageNumber;
	private int pageSize;

	private MchtUser currentUser;

	@Override
	public void init() {
		name = getPara("name");
		pageStatus = getParaToInt("pageStatus");

		pageNumber = getParaToInt("pageNumber", 1);
		pageSize = getParaToInt("pageSize", 20);

		currentUser = context.getUserInfo();
	}

	@Override
	public void doCommand() {
		QueryObject queryObject = new QueryObject(pageNumber, pageSize);
		queryObject.addQuery("mchtId", currentUser.getMchtId());
		queryObject.addQuery("mchtType", context.getMchtInfo().getMchtType());
		List<String> types = new ArrayList<String>();
		types.add("1");
		types.add("2");
		types.add("3");
		queryObject.addQuery("typeIn", types);
		queryObject.addSort("create_date", QueryObject.SORT_DESC);
		if(StrKit.notBlank(name)){
			queryObject.addQuery("name", name.trim());
		}
		if(pageStatus > 0){
			queryObject.addQuery("pageStatus", pageStatus);
		}
		Page<ActivityArea> page = activityAreaService.paginatePlat(queryObject);
		data.put("page", page);
	}

}
