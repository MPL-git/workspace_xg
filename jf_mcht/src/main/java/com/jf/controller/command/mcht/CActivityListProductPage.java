package com.jf.controller.command.mcht;

import com.jf.common.constant.Const;
import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.entity.Activity;
import com.jf.entity.ActivityArea;
import com.jf.entity.MchtUser;
import com.jf.service.ActivityAreaService;
import com.jf.service.ActivityProductService;
import com.jf.service.ActivityService;
import com.jf.service.MchtInfoService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;

/**
 * 活动商品列表页
 *
 * @author huangdl
 */
public class CActivityListProductPage extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CActivityListProductPage.class);

	@Resource
	private ActivityService activityService;
	@Resource
	private ActivityAreaService activityAreaService;
	@Resource
	private ActivityProductService activityProductService;
	@Resource
	private MchtInfoService mchtInfoService;

	private int activityId;
	private int pageNumber;

	private MchtUser currentUser;

	@Override
	public void init() {
		activityId = getParaToInt("activityId");
		Assert.notZero(activityId, "活动ID不能为空");
		pageNumber = getParaToInt("pageNumber", 1);
		currentUser = context.getUserInfo();
	}

	@Override
	public void doCommand() {
		Activity activity = activityService.findById(activityId);
		if(activity == null || !activity.getMchtId().equals(currentUser.getMchtId())){
			throw new BizException("未找到该活动");
		}
		data.put("activityAreaId", activity.getActivityAreaId());
		ActivityArea activityArea = activityAreaService.findById(activity.getActivityAreaId());
		if(StringUtils.isBlank(activityArea.getIsPreSell())){
			activityArea.setIsPreSell("0");
		}
		data.put("activityArea",activityArea);
		data.put("type", activityArea.getType());
		data.put("status", getParaToInt("status", Const.ACTIVITY_PRODUCT_STATUS_WAIT));
		data.put("activity", activity);
		data.put("waitCount", activityProductService.countProduct(currentUser.getMchtId(), activityId, Const.ACTIVITY_PRODUCT_STATUS_WAIT));
		data.put("normalCount", activityProductService.countProduct(currentUser.getMchtId(), activityId, Const.ACTIVITY_PRODUCT_STATUS_NORMAL));
		data.put("rejectCount", activityProductService.countProduct(currentUser.getMchtId(), activityId, Const.ACTIVITY_PRODUCT_STATUS_REJECT));
		data.put("mchtInfoStatus", mchtInfoService.selectByPrimaryKey(currentUser.getMchtId()).getStatus());
		data.put("name", getPara("name"));
		data.put("pageNumber", pageNumber);
		data.put("preferentialWay",activityAreaService.getPromotionMethod(activity.getActivityAreaId()));

	}

}
