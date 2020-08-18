package com.jf.controller.command.mcht;

import com.jf.common.constant.Const;
import com.jf.common.enums.ActivityPreferentialTypeEnum;
import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.QueryObject;
import com.jf.entity.*;
import com.jf.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 编辑活动
 *
 * @author huangdl
 */
public class CPlatActivityEdit extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CPlatActivityEdit.class);

	@Resource
	private ActivityAreaService activityAreaService;
	@Resource
	private ActivityService activityService;
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	@Resource
	private ProductTypeService productTypeService;
	@Resource
	private ActivityAuthService activityAuthService;
	@Resource
	private ProductBrandService productBrandService;
	@Resource
	private AllowanceInfoService allowanceInfoService;
	private int id;
	private int activityId;

	private MchtUser currentUser;

	@Override
	public void init() {
		id = getParaToInt("id");
		Assert.moreThanZero(id, "活动专区不能为空");
		activityId = getParaToInt("activityId");
		currentUser = context.getUserInfo();
	}

	@Override
	public void doCommand() {
		ActivityArea activityArea = activityAreaService.findById(id);
		if(activityArea == null){
			throw new BizException("未找到该活动");
		}
		if(activityArea.getSource().equals("1") && activityArea.getType().equals("3")){//推广会场
			ActivityExample e = new ActivityExample();
			ActivityExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andActivityAreaIdEqualTo(id);
			int count = activityService.countByExample(e);
			if(count>=activityArea.getLimitMchtNum()){
				throw new BizException("当前报名数已满");
			}
		}

		data.put("activityArea", activityArea);
		//如果是会场的优惠方式是购物津贴,则在会场活动报名时呈现购物津贴的rule
		if (activityArea.getPreferentialType().equals(String.valueOf(ActivityPreferentialTypeEnum.ALLOWANCE.getValue()))){
			AllowanceInfo allowanceInfo = allowanceInfoService.findByActivityAreaId(activityArea.getId());
			if(allowanceInfo.getLadderFlag().equals(Const.FLAG_FALSE)){
				String[] rule = allowanceInfo.getRule().split("[,]");
				allowanceInfo.put("minimum", rule[0]);
				allowanceInfo.put("money", rule[1]);
			}
			data.put("allowanceInfo", allowanceInfo);
		}
		Activity activity = activityService.selectByPrimaryKey(activityId);
		if(activity != null){
			data.put("activity", activity);
			data.put("auth", activityAuthService.findByActivityId(activity.getId()));
			ProductBrand productBrand = productBrandService.selectByPrimaryKey(activity.getProductBrandId());
			if(productBrand!=null){
				data.put("productBrandName", productBrand.getName());
			}
			if(activity.getProductTypeSecondId()!= null && !activity.getProductTypeSecondId().equals(0)){
				data.put("secondProductType", productTypeService.findById(activity.getProductTypeSecondId()));	//二级类目
			}
		}else{
			DateTime now = DateTime.now();
			boolean inEnrollTime = now.isAfter(activityArea.getEnrollBeginTime().getTime()) && now.isBefore(activityArea.getEnrollEndTime().getTime());
			if(!inEnrollTime){
				throw new BizException("不在报名时间内");
			}
		}
		if(activityService.findByAreaId(currentUser.getMchtId(), activityArea.getId())!=null){
			data.put("hasActivity", true);
		}
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("status", "1");	//正常
		data.put("productTypeList", productTypeService.findListOfTopLevelByMchtId(currentUser.getMchtId(), queryObject));	// 类目
		ActivityExample example = new ActivityExample();
		ActivityExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andMchtIdEqualTo(currentUser.getMchtId());
		criteria.andActivityAreaIdEqualTo(id);
		List<Activity> activitys = activityService.selectByExample(example);
		if(activitys != null && activitys.size()>0){
			List<Integer> productBrandIdList = new ArrayList<Integer>();
			for(Activity a:activitys){
				productBrandIdList.add(a.getProductBrandId());
			}
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("mchtId", currentUser.getMchtId());
			paramMap.put("productBrandIdList", productBrandIdList);
			List<ProductBrand> productBrandList = productBrandService.getMchtProductBrands(paramMap);
			data.put("productBrandList", productBrandList);	// 品牌
		}else{
			List<ProductBrand> productBrandList = mchtProductBrandService.getMchtProductBrandList(currentUser.getMchtId());
			data.put("productBrandList", productBrandList);	// 品牌
		}
	}
}
