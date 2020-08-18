package com.jf.controller.command.mcht;

import com.jf.common.constant.Const;
import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.StringUtil;
import com.jf.entity.Activity;
import com.jf.entity.ActivityArea;
import com.jf.entity.ActivityProduct;
import com.jf.entity.MchtUser;
import com.jf.service.ActivityAreaService;
import com.jf.service.ActivityProductService;
import com.jf.service.ActivityService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 提交活动
 *
 * @author huangdl
 */
public class CActivityCommit extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CActivityCommit.class);

	@Resource
	private ActivityService activityService;
	@Resource
	private ActivityAreaService activityAreaService;
	@Resource
	private ActivityProductService activityProductService;


	private int activityId;

	private MchtUser currentUser;

	@Override
	public void init() {
		activityId = getParaToInt("activityId");
		Assert.moreThanZero(activityId, "活动ID不能为空");

		currentUser = context.getUserInfo();
	}

	@Override
	public void doCommand() {
		Activity activity = activityService.findById(activityId);
		ActivityArea activityArea = activityAreaService.findById(activity.getActivityAreaId());
		if(activity == null || !activity.getMchtId().equals(currentUser.getMchtId())){
			throw new BizException("未找到该活动");
		}
		List<ActivityProduct> activityProducts = activityProductService.checkSvipDiscount(activityId);
		if(activityProducts!=null && activityProducts.size()>0 && !StringUtils.equals(activityArea.getIsPreSell(),"1")){
			throw new BizException("存在未设置SVIP折扣商品，无法提报审核");
		}
		if(StringUtil.isEmpty(activity.getBrandTeamPic()) && Objects.equals("2",activityArea.getSource())){
			throw new BizException("请先上传品牌团入口图！");
		}
		if(StringUtil.isEmpty(activity.getEntryPic())){
			throw new BizException("请先上传入口图！");
		}
		if(StringUtil.isEmpty(activity.getPosterPic())){
			throw new BizException("请先上传头部海报！");
		}
		QueryObject queryObject = new QueryObject(1,1);
		queryObject.addQuery("activityId", activityId);
		queryObject.addQuery("refuseFlag", Const.FLAG_FALSE);
		queryObject.addQuery("delFlag", Const.FLAG_FALSE);
		List<ActivityProduct> activityProductList = activityProductService.list(queryObject);
		try {
			Assert.moreThanZero(activityProductList.size(), "请先报名商品后提交");
		} catch (BizException e) {
			throw new BizException("请先报名商品后提交");
		}
		activityService.commit(activityId);
	}

}
