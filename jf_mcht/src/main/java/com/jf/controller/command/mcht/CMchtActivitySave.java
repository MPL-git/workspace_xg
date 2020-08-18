package com.jf.controller.command.mcht;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jf.common.constant.Const;
import com.jf.common.enums.ActivityPreferentialTypeEnum;
import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.dao.ActivityProductMapper;
import com.jf.entity.*;
import com.jf.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * 保存商家特卖
 *
 * @author huangdl
 */
public class CMchtActivitySave extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtActivitySave.class);

	@Resource
	private ActivityAreaService activityAreaService;
	@Resource
	private ActivityService activityService;
	@Resource
	private CouponService couponService;
	@Resource
	private FullCutService fullCutService;
	@Resource
	private FullGiveService fullGiveService;
	@Resource
	private FullDiscountService fullDiscountService;
	@Resource
	private ActivityProductMapper activityProductMapper;

	private ActivityArea paramActivityArea;
	private Activity paramActivity;
	private JSONObject jsonObject;

	private MchtUser currentUser;

	private MchtInfo currentInfo;
	@Override
	public void init() {
		paramActivityArea = getBean(ActivityArea.class, "activityArea");
		paramActivity = getBean(Activity.class, "activity");
		jsonObject = JSON.parseObject(getPara("json"));

		Assert.notBlank(paramActivityArea.getName(), "活动名称不能为空");
		Assert.moreThanZero(paramActivity.getProductTypeId(), "类目不能为空");
		Assert.moreThanZero(paramActivity.getProductTypeSecondId(), "二级类目不能为空");
		Assert.moreThanZero(paramActivity.getProductBrandId(), "品牌不能为空");
		Assert.notBlank(paramActivityArea.getBenefitPoint(), "利益点不能为空");

		Assert.notNull(paramActivity.getExpectedStartTime(), "期望活动时间不能为空");
		Assert.notFalse(new DateTime(paramActivity.getExpectedStartTime()).plusDays(0).isAfterNow(), "期望活动时间必须在今天之后");

		Assert.notBlank(paramActivityArea.getPreferentialType(), "促销方式不能为空");
		Assert.notFalse(ActivityPreferentialTypeEnum.contains(Integer.valueOf(paramActivityArea.getPreferentialType())), "不支持的促销方式");

		if(paramActivityArea.getPreferentialType().equals(String.valueOf(ActivityPreferentialTypeEnum.COUPON.getValue()))){
			Assert.notBlank(jsonObject.getString("couponList"), "优惠券信息不能为空");
			String jsonCouponList = jsonObject.getString("couponList");
			List<Coupon> couponList = JSONArray.parseArray(jsonCouponList, Coupon.class);
			BigDecimal money = new BigDecimal(0);
			for(Coupon coupon : couponList){
				if(coupon.getGrantQuantity() != null && coupon.getMinimum() != null && coupon.getMoney() != null){
					if(coupon.getMinimum().compareTo(coupon.getMoney()) <= 0){
						throw new BizException("优惠券使用条件不能小于面额");
					}
					if(coupon.getMoney().compareTo(money) <= 0){
						throw new BizException("优惠券面额应该按从小到大填写");
					}
					money = coupon.getMoney();
				}
			}
			if(money.equals(new BigDecimal(0))){
				throw new BizException("请填写有效优惠券信息");
			}

		}else if(paramActivityArea.getPreferentialType().equals(String.valueOf(ActivityPreferentialTypeEnum.CUT.getValue()))){
			JSONObject fullCutObject = jsonObject.getJSONObject("fullCut");
			Assert.notNull(fullCutObject, "满减信息不能为空");
			Boolean ladderFlag = fullCutObject.getBoolean("ladderFlag");
			Assert.notNull(ladderFlag, "请填写阶梯信息");

			if(!ladderFlag){
				String minimum = fullCutObject.getString("minimum");
				Assert.notNull(minimum, "请填写完整满减信息");
				String money = fullCutObject.getString("money");
				Assert.notNull(money, "请填写完整满减信息");
				if(Double.valueOf(minimum) <= Double.valueOf(money)){
					throw new BizException("满M元减N元，M必须大于N");
				}
			}else{
				String fullCutListJson = jsonObject.getString("fullCutList");
				Assert.notBlank(fullCutListJson, "阶梯满减信息不能为空");
				JSONArray fullCutArray = JSONArray.parseArray(fullCutListJson);
				for (int index = 0; index < fullCutArray.size(); index ++) {
					String minimum = fullCutArray.getJSONObject(index).getString("minimum");
					String money = fullCutArray.getJSONObject(index).getString("money");
					if(money != null && minimum != null){
						if(Double.valueOf(minimum) <= Double.valueOf(money)){
							throw new BizException("满M元减N元，M必须大于N");
						}
					}
				}
			}

		}else if(paramActivityArea.getPreferentialType().equals(String.valueOf(ActivityPreferentialTypeEnum.GIVE.getValue()))){
			// 满赠
			JSONObject fullGiveObject = jsonObject.getJSONObject("fullGive");
			String type = fullGiveObject.getString("type");
			Assert.notNull(type, "请填写满赠类型");
			Integer productId = fullGiveObject.getInteger("productId");
			Assert.notNull(productId, "请选择赠送的商品");
			if(type.equals("1")){
				Integer productNum = fullGiveObject.getInteger("productNum");
				Assert.notNull(productNum, "请填写商品数量");
			}
		}else if(paramActivityArea.getPreferentialType().equals(String.valueOf(ActivityPreferentialTypeEnum.DISCOUNT.getValue()))){
			// 多买优惠促销
			JSONObject fullDiscountObject = jsonObject.getJSONObject("fullDiscount");
			String type = fullDiscountObject.getString("type");
			Assert.notNull(type, "请填写多买类型");
			String listJson = jsonObject.getString("fullDiscountList");
			Assert.notBlank(listJson, "多买规则不能为空");

			int ruleSize = 0;
			JSONArray array = JSONArray.parseArray(listJson);
			for (int index = 0; index < array.size(); index ++) {
				String minimum = array.getJSONObject(index).getString("minimum");
				String money = array.getJSONObject(index).getString("money");

				if(money != null && minimum != null){
					ruleSize += 1;
					if(type.equals(Const.FULL_DISCOUNT_TYPE_CUT)){
						if(Integer.valueOf(minimum) <= Integer.valueOf(money)){
							throw new BizException("满M件减N件，M必须大于N");
						}
					}if(type.equals(Const.FULL_DISCOUNT_TYPE_DISCOUNT)){
						if(!(0.1d <= Double.valueOf(money) && Double.valueOf(money) <= 9.9d)){
							throw new BizException("折扣应该在0.1到9.9之间");
						}
					}
				}
			}
			Assert.moreThanZero(ruleSize, "请填写完整多买规则");

		}

		currentUser = context.getUserInfo();
		currentInfo = context.getMchtInfo();

	}

	@Override
	public void doCommand() {
		ActivityArea activityArea = activityAreaService.save(currentUser.getMchtId(), paramActivityArea);
		paramActivity.setName(activityArea.getName());
		Activity activity = activityService.save(currentUser.getMchtId(), activityArea.getId(), paramActivity);


		if(paramActivityArea.getPreferentialType().equals(String.valueOf(ActivityPreferentialTypeEnum.COUPON.getValue()))){
			// 优惠券促销
			String jsonCouponList = jsonObject.getString("couponList");
			List<Coupon> couponList = JSONArray.parseArray(jsonCouponList, Coupon.class);
			couponService.deleteByActivityAreaId(activityArea.getId());
			String couponIds = "";
			for(Coupon coupon : couponList){
				if(coupon.getGrantQuantity() != null && coupon.getMinimum() != null && coupon.getMoney() != null){
					coupon = couponService.save(currentUser.getMchtId(),currentInfo.getMchtType(),activityArea.getId(), activityArea.getName(), coupon.getMoney(), coupon.getMinimum(), coupon.getGrantQuantity());
					couponIds += coupon.getId() + ",";
				}
			}
			activityArea.setPreferentialIdGroup(couponIds.substring(0, couponIds.length()-1));
			activityAreaService.update(activityArea);
			//System.out.println(couponList.size());
		}else if(paramActivityArea.getPreferentialType().equals(String.valueOf(ActivityPreferentialTypeEnum.CUT.getValue()))){
			// 满减促销
			JSONObject fullCutObject = jsonObject.getJSONObject("fullCut");
			Boolean ladderFlag = fullCutObject.getBoolean("ladderFlag");
			FullCut fullCut;
			if(!ladderFlag){
				String minimum = fullCutObject.getString("minimum");
				String money = fullCutObject.getString("money");
				Boolean sumFlag = fullCutObject.getBoolean("sumFlag");
				if(sumFlag == null){
					sumFlag = false;
				}
				fullCut = fullCutService.save(activityArea.getId(),currentInfo, false, sumFlag, minimum + "," + money);
			}else{
				String fullCutListJson = jsonObject.getString("fullCutList");
				JSONArray fullCutArray = JSONArray.parseArray(fullCutListJson);
				List<JSONObject> list = JSONArray.parseArray(fullCutArray.toJSONString(), JSONObject.class);
				Collections.sort(list, new Comparator<JSONObject>() {
				    @Override
				    public int compare(JSONObject o1, JSONObject o2) {
				    	BigDecimal a = o1.getBigDecimal("minimum");
				    	BigDecimal b = o2.getBigDecimal("minimum");
				        if (a.compareTo(b)>0) {
				            return 1;
				        } else if(a.compareTo(b)==0) {
				            return 0;
				        } else
				            return -1;
				        }
				});
				fullCutArray = JSONArray.parseArray(list.toString());
				String rule = "";
				for (int index = 0; index < fullCutArray.size(); index ++) {
					String minimum = fullCutArray.getJSONObject(index).getString("minimum");
					String money = fullCutArray.getJSONObject(index).getString("money");

					if(money != null && minimum != null){
						if(rule.length() == 0){
							rule += minimum + "," + money;
						}else{
							rule += "|" + minimum + "," + money;
						}
					}
				}
				fullCut = fullCutService.save(activityArea.getId(),currentInfo, true, false, rule);
			}
			activityArea.setPreferentialIdGroup(String.valueOf(fullCut.getId()));
			activityAreaService.update(activityArea);

		}else if(paramActivityArea.getPreferentialType().equals(String.valueOf(ActivityPreferentialTypeEnum.GIVE.getValue()))){
			// 满赠
			JSONObject fullGiveObject = jsonObject.getJSONObject("fullGive");
			String type = fullGiveObject.getString("type");
			Integer productId = fullGiveObject.getInteger("productId");
			Integer productNum = fullGiveObject.getInteger("productNum");
			FullGive fullGive;
			if(type.equals(Const.FULL_GIVE_TYPE_FULL)){
				BigDecimal minimum = fullGiveObject.getBigDecimal("minimum");
				Boolean sumFlag = fullGiveObject.getBoolean("sumFlag");
				if(sumFlag == null){
					sumFlag = false;
				}
				fullGive = fullGiveService.saveFullGive(activityArea.getId(), minimum, sumFlag, productId, productNum,currentInfo);
			}else{
				productNum = 1;
				fullGive = fullGiveService.saveBuyGive(activityArea.getId(), productId, productNum,currentInfo);
			}
			activityArea.setPreferentialIdGroup(String.valueOf(fullGive.getId()));
			activityAreaService.update(activityArea);
			
			ActivityProductExample example = new ActivityProductExample();
			example.createCriteria().andDelFlagEqualTo("0").andActivityIdEqualTo(activity.getId()).andIsGiftEqualTo("1").andProductIdNotEqualTo(productId);
			List<ActivityProduct> activityProductList = activityProductMapper.selectByExample(example);
			if(activityProductList!=null && activityProductList.size()>0){
				for(ActivityProduct activityProduct:activityProductList){
					activityProduct.setDelFlag("1");
					activityProduct.setUpdateDate(new Date());
					activityProductMapper.updateByPrimaryKeySelective(activityProduct);
				}
			}
			ActivityProductExample ape = new ActivityProductExample();
			ape.createCriteria().andDelFlagEqualTo("0").andActivityIdEqualTo(activity.getId()).andProductIdEqualTo(productId).andRefuseFlagEqualTo("0");
			List<ActivityProduct> activityProducts = activityProductMapper.selectByExample(ape);
			
			ActivityProductExample e = new ActivityProductExample();
			e.createCriteria().andDelFlagEqualTo("0").andActivityIdEqualTo(activity.getId()).andProductIdEqualTo(productId).andRefuseFlagIsNull();
			List<ActivityProduct> apList = activityProductMapper.selectByExample(e);
			activityProducts.addAll(apList);
			
			if(activityProducts==null || activityProducts.size()==0){
				ActivityProduct ap = new ActivityProduct();
				ap.setCreateDate(new Date());
				ap.setDelFlag("0");
				ap.setActivityId(activity.getId());
				ap.setProductId(productId);
				ap.setIsGift("1");
				activityProductMapper.insertSelective(ap);
			}

		}else if(paramActivityArea.getPreferentialType().equals(String.valueOf(ActivityPreferentialTypeEnum.DISCOUNT.getValue()))){
			// 多买优惠促销
			JSONObject fullDiscountObject = jsonObject.getJSONObject("fullDiscount");
			String type = fullDiscountObject.getString("type");
			String listJson = jsonObject.getString("fullDiscountList");
			JSONArray array = JSONArray.parseArray(listJson);
			String rule = "";
			for (int index = 0; index < array.size(); index ++) {
				String minimum = array.getJSONObject(index).getString("minimum");
				String money = array.getJSONObject(index).getString("money");

				if(money != null && minimum != null){
					if(rule.length() == 0){
						rule += minimum + "," + money;
					}else{
						rule += "|" + minimum + "," + money;
					}
				}
			}
			FullDiscount fullDiscount = fullDiscountService.save(activityArea.getId(), type, rule,currentInfo);

			activityArea.setPreferentialIdGroup(String.valueOf(fullDiscount.getId()));
			activityAreaService.update(activityArea);
		}

		data.put("activityArea", activityArea);
		data.put("activity", activity);
	}

}
