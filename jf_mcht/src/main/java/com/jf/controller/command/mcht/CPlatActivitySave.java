package com.jf.controller.command.mcht;

import com.jf.common.constant.Const;
import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.entity.Activity;
import com.jf.entity.ActivityArea;
import com.jf.entity.MchtUser;
import com.jf.service.ActivityAreaService;
import com.jf.service.ActivityService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;

/**
 * 保存活动
 *
 * @author huangdl
 */
public class CPlatActivitySave extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CPlatActivitySave.class);

	@Resource
	private ActivityAreaService activityAreaService;
	@Resource
	private ActivityService activityService;

	private int activityAreaId;
	private int activityId;
	private Activity paramActivity;

	private MchtUser currentUser;

	@Override
	public void init() {
		activityAreaId = getParaToInt("activityArea.id");
		activityId = getParaToInt("activity.id");
		Assert.moreThanZero(activityAreaId, "活动专区不能为空");

		paramActivity = getBean(Activity.class, "activity");

		Assert.notBlank(paramActivity.getName(), "活动名称不能为空");

		currentUser = context.getUserInfo();
	}

	@Override
	public void doCommand() {
		ActivityArea activityArea = activityAreaService.findById(activityAreaId);
		Assert.notNull(activityArea, "未知的活动专区");
		if(activityAreaService.isLimitMchtNumFull(activityArea)){
			throw new BizException("活动报名名额已满");
		}

		if(activityArea.getType().equals(Const.ACTIVITY_TYPE_BRAND)){
			Assert.moreThanZero(paramActivity.getProductTypeId(), "类目不能为空");
			Assert.moreThanZero(paramActivity.getProductTypeSecondId(), "二级类目不能为空");
			Assert.moreThanZero(paramActivity.getProductBrandId(), "品牌不能为空");
		}
		if((activityArea.getSource().equals("1") && activityArea.getType().equals("2")) || (activityArea.getSource().equals("1") && activityArea.getType().equals("3"))){
			//会场单品,推广
			paramActivity.setStatus(Const.ACTIVITY_STATUS_PASS);//通过
			paramActivity.setCooAuditStatus("2");//总监审核通过
//			paramActivity.setStatus(Const.ACTIVITY_STATUS_WAIT_AUDIT);//待审核
//			paramActivity.setOperateAuditStatus("1");//待审核
		}
		Activity activity = activityService.save(currentUser.getMchtId(), activityAreaId,activityId, paramActivity);
		data.put("activity", activity);
		data.put("type", activityArea.getType());
		data.put("id", activityArea.getId());
	}

}
