package com.jf.controller.command.mcht;

import com.jf.common.constant.Const;
import com.jf.common.enums.ActivityStatusEnum;
import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.StrKit;
import com.jf.entity.ActivityArea;
import com.jf.entity.MchtUser;
import com.jf.service.ActivityAreaService;
import com.jf.service.MchtInfoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

import javax.annotation.Resource;

/**
 * 商家特卖活动列表
 *
 * @author huangdl
 */
public class CMchtActivityList extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtActivityList.class);

	@Resource
	private ActivityAreaService activityAreaService;
	
	@Resource
	private MchtInfoService mchtInfoService;


	private String name;
	private int productBrandId;
	private int status;

	private int pageNumber;
	private int pageSize;

	private MchtUser currentUser;

	@Override
	public void init() {
		name = getPara("name");
		productBrandId = getParaToInt("productBrandId");
		status = getParaToInt("status");

		pageNumber = getParaToInt("pageNumber", 1);
		pageSize = getParaToInt("pageSize", 20);

		currentUser = context.getUserInfo();
	}

	@Override
	public void doCommand() {
		QueryObject queryObject = new QueryObject(pageNumber, pageSize);
		queryObject.addSort("create_date", QueryObject.SORT_DESC);
		queryObject.addQuery("source", "2");//特卖
		if(StrKit.notBlank(name)){
			queryObject.addQuery("name", name.trim());
		}
		if(productBrandId > 0){
			queryObject.addQuery("productBrandId", productBrandId);
		}
		if(status > 0){
			//Assert.notFalse(ActivityStatusEnum.contains(status), "未知的活动状态");
			if(status < 5){
				queryObject.addQuery("activityStatus", status);
			}else{
				queryObject.addQuery("activityStatus", Const.ACTIVITY_STATUS_PASS);
				DateTime now = DateTime.now();
				if(status == ActivityStatusEnum.PREPARING.getValue()){
					queryObject.addQuery("activityBeginTimeAfter", now.plusDays(Const.ACTIVITY_PREHEAT_DAY).toDate());
				}else if(status == ActivityStatusEnum.PREHEAT.getValue()){
					queryObject.addQuery("activityBeginTimeBefore", now.plusDays(Const.ACTIVITY_PREHEAT_DAY).toDate());
					queryObject.addQuery("activityBeginTimeAfter", now.toDate());
				}else if(status == ActivityStatusEnum.PROCESSING.getValue()){
					queryObject.addQuery("activityBeginTimeBefore", now.toDate());
					queryObject.addQuery("activityEndTimeAfter", now.toDate());
				}else if(status == ActivityStatusEnum.FINISHED.getValue()){
					queryObject.addQuery("activityEndTimeBefore", now.toDate());
				}
			}

		}

		Page<ActivityArea> page = activityAreaService.paginateMcht(currentUser.getMchtId(), queryObject);
		data.put("mchtInfoStatus", mchtInfoService.selectByPrimaryKey(currentUser.getMchtId()).getStatus());
		data.put("page", page);
	}

}
