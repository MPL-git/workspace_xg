package com.jf.controller.command.mcht;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

import com.jf.common.constant.Const;
import com.jf.common.enums.ActivityPreferentialTypeEnum;
import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.Activity;
import com.jf.entity.ActivityArea;
import com.jf.entity.ActivityCustom;
import com.jf.entity.ActivityExample;
import com.jf.entity.Coupon;
import com.jf.entity.FullCut;
import com.jf.entity.FullDiscount;
import com.jf.entity.FullGive;
import com.jf.entity.MchtUser;
import com.jf.entity.ProductBrand;
import com.jf.service.ActivityAreaService;
import com.jf.service.ActivityAuthService;
import com.jf.service.ActivityService;
import com.jf.service.CouponService;
import com.jf.service.FullCutService;
import com.jf.service.FullDiscountService;
import com.jf.service.FullGiveService;
import com.jf.service.MchtProductBrandService;
import com.jf.service.ProductBrandService;
import com.jf.service.ProductService;
import com.jf.service.ProductTypeService;

/**
 * 管理活动
 *
 * @author yjc
 */
public class CPlatActivityManage extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CPlatActivityManage.class);

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
	private CouponService couponService;
	@Resource
	private FullCutService fullCutService;
	@Resource
	private FullGiveService fullGiveService;
	@Resource
	private FullDiscountService fullDiscountService;
	@Resource
	private ProductService productService;
	private int id;
	private int pageNumber;
	private MchtUser currentUser;

	@Override
	public void init() {
		id = getParaToInt("id");
		Assert.moreThanZero(id, "活动专区不能为空");
		pageNumber = getParaToInt("pageNumber", 1);
		currentUser = context.getUserInfo();
	}

	@Override
	public void doCommand() {
		ActivityArea activityArea = activityAreaService.findById(id);
		if(activityArea == null){
			throw new BizException("未找到该活动");
		}
		boolean limit = activityAreaService.isLimitMchtNumFull(activityArea);
		boolean isEnd = activityArea.getEnrollEndTime().before(new Date());
		data.put("limit", limit);
		data.put("isEnd", isEnd);
		data.put("activityArea", activityArea);
		data.put("preferentialTypeDesc", DataDicUtil.getStatusDesc("BU_ACTIVITY_AREA", "PREFERENTIAL_TYPE", activityArea.getPreferentialType()));
		if(activityArea.getPreferentialType().equals(String.valueOf(ActivityPreferentialTypeEnum.COUPON.getValue()))){
			// 优惠券促销
			data.put("couponList", couponService.listByActivityAreaId(activityArea.getId()));
			data.put("belong", couponService.listByActivityAreaId(activityArea.getId()).get(0).getBelong());
		}else if(activityArea.getPreferentialType().equals(String.valueOf(ActivityPreferentialTypeEnum.CUT.getValue()))){
			// 满减促销
			FullCut fullCut = fullCutService.findByActivityAreaId(activityArea.getId());
			if(fullCut.getLadderFlag().equals(Const.FLAG_FALSE)){
				String[] rule = fullCut.getRule().split("[,]");
				fullCut.put("minimum", rule[0]);
				fullCut.put("money", rule[1]);
			}else{
				fullCut.put("ruleList", getRuleList(fullCut.getRule()));
			}
			data.put("fullCut", fullCut);
			data.put("belong", fullCut.getBelong());
		}else if(activityArea.getPreferentialType().equals(String.valueOf(ActivityPreferentialTypeEnum.GIVE.getValue()))){// 满赠
			FullGive fullGive = fullGiveService.findByActivityAreaId(activityArea.getId());
			String fullGiveDesc = "";
			if(fullGive.getProductId()!=null){//送商品
				fullGiveDesc = "(赠品"+fullGive.getProductNum()+"件："+productService.selectByPrimaryKey(fullGive.getProductId()).getName()+")；";
			}
			if(fullGive.getCouponFlag().equals("1") && !StringUtil.isEmpty(fullGive.getCouponIdGroup())){//送优惠券
				String[] idsArray = fullGive.getCouponIdGroup().split(",");
				List<Integer> ids = new ArrayList<Integer>();
				for(int i=0;i<idsArray.length;i++){
					ids.add(Integer.parseInt(idsArray[i]));
				}
				if(ids!=null && ids.size()>0){
					QueryObject queryObject = new QueryObject();
					queryObject.addQuery("delFlag", "0");
					queryObject.addQuery("idsIn", ids);
					List<Coupon> coupons = couponService.list(queryObject);
					String tmpStr = "(优惠券：";
					for(int i=0; i<coupons.size();i++){
						tmpStr += "满"+coupons.get(i).getMinimum()+"减"+coupons.get(i).getMoney();
						if(i!=coupons.size()-1){
							tmpStr+=",";
						}else{
							tmpStr+=")";
						}
					}
					fullGiveDesc += tmpStr;
				}
			}
			if(fullGive.getIntegralFlag().equals("1") && fullGive.getIntegral()!=null){//送积分
				fullGiveDesc += "；(赠送积分："+fullGive.getIntegral()+"个积分)";
			}
			fullGive.put("fullGiveDesc", fullGiveDesc);
			data.put("fullGive", fullGive);
			data.put("belong", fullGive.getBelong());
		}else if(activityArea.getPreferentialType().equals(String.valueOf(ActivityPreferentialTypeEnum.DISCOUNT.getValue()))){
			// 多买优惠
			FullDiscount fullDiscount = fullDiscountService.findByActivityAreaId(activityArea.getId());
			if(fullDiscount != null){
				fullDiscount.put("ruleList", getRuleList(fullDiscount.getRule()));
				data.put("fullDiscount", fullDiscount);
				data.put("belong", fullDiscount.getBelong());
			}

		}
		if(activityArea.getType().equals("1")){
			ActivityExample example = new ActivityExample();
			example.setOrderByClause("t.id desc");
			ActivityExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andMchtIdEqualTo(currentUser.getMchtId());
			criteria.andActivityAreaIdEqualTo(id);
			List<ActivityCustom> activityCustoms = activityService.selectActivityCustomByExample(example);
			data.put("activityCustoms", activityCustoms);
		}else{
			Activity activity = activityService.findByAreaId(currentUser.getMchtId(), id);
			if(activity != null){
				data.put("activity", activity);
				data.put("auth", activityAuthService.findByActivityId(activity.getId()));
			}else{
				DateTime now = DateTime.now();
				boolean inEnrollTime = now.isAfter(activityArea.getEnrollBeginTime().getTime()) && now.isBefore(activityArea.getEnrollEndTime().getTime());
				if(!inEnrollTime){
					throw new BizException("不在活动时间内");
				}
			}
			QueryObject queryObject = new QueryObject();
			queryObject.addQuery("status", "1");	//正常
			data.put("productTypeList", productTypeService.findListOfTopLevelByMchtId(currentUser.getMchtId(), queryObject));	// 类目
		}
		data.put("pageNumber", pageNumber);
		data.put("name", getPara("name"));
	}
	
	private List<Map<String, String>> getRuleList(String ruleStr){
		List<Map<String, String>> ruleList = new ArrayList<>();
		String[] ruleArray = ruleStr.split("[|]");
		Map<String, String> map;
		for(String rules : ruleArray){
			map = new HashMap<>();
			String[] rule = rules.split("[,]");
			map.put("minimum", rule[0]);
			map.put("money", rule[1]);
			ruleList.add(map);
		}
		return ruleList;
	}
}
