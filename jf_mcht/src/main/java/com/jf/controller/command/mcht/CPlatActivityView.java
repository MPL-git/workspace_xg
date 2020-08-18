package com.jf.controller.command.mcht;

import com.jf.common.constant.Const;
import com.jf.common.enums.ActivityPreferentialTypeEnum;
import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.*;
import com.jf.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看专区
 *
 * @author huangdl
 */
@SuppressWarnings("all")
public class CPlatActivityView extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CPlatActivityView.class);

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
	private ProductService productService;
	@Resource
	private AllowanceInfoService allowanceInfoService;
	private int id;

	private MchtUser currentUser;

	@Override
	public void init() {
		id = getParaToInt("id");
		Assert.moreThanZero(id, "活动专区不能为空");

		currentUser = context.getUserInfo();
	}

	@Override
	public void doCommand() {
		if(id > 0){
			ActivityArea activityArea = activityAreaService.findById(id);
			if(activityArea == null){
				throw new BizException("未找到该活动");
			}
			data.put("activityArea", activityArea);
			if(activityArea.getPreferentialType()!=null){
				if(activityArea.getPreferentialType().equals(String.valueOf(ActivityPreferentialTypeEnum.COUPON.getValue()))){
					// 优惠券促销
					data.put("couponList", couponService.listByActivityAreaId(activityArea.getId()));
					data.put("belone", couponService.listByActivityAreaId(activityArea.getId()).get(0).getBelong());
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
					data.put("belone", fullCut.getBelong());
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
					data.put("belone", fullGive.getBelong());
				}else if(activityArea.getPreferentialType().equals(String.valueOf(ActivityPreferentialTypeEnum.DISCOUNT.getValue()))){
					// 多买优惠
					FullDiscount fullDiscount = fullDiscountService.findByActivityAreaId(activityArea.getId());
					if(fullDiscount != null){
						fullDiscount.put("ruleList", getRuleList(fullDiscount.getRule()));
						data.put("fullDiscount", fullDiscount);
						data.put("belone", fullDiscount.getBelong());
					}
					
				}else if (activityArea.getPreferentialType().equals(String.valueOf(ActivityPreferentialTypeEnum.ALLOWANCE.getValue()))){
					AllowanceInfo allowanceInfo = allowanceInfoService.findByActivityAreaId(activityArea.getId());
					if(allowanceInfo.getLadderFlag().equals(Const.FLAG_FALSE)){
						String[] rule = allowanceInfo.getRule().split("[,]");
						allowanceInfo.put("minimum", rule[0]);
						allowanceInfo.put("money", rule[1]);
					}else{
						allowanceInfo.put("ruleList", getRuleList(allowanceInfo.getRule()));
					}
					data.put("allowanceInfo", allowanceInfo);
					data.put("belone", allowanceInfo.getBelong());

				}
				data.put("preferentialTypeDesc", DataDicUtil.getStatusDesc("BU_ACTIVITY_AREA", "PREFERENTIAL_TYPE", activityArea.getPreferentialType()));
			}
		}
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
