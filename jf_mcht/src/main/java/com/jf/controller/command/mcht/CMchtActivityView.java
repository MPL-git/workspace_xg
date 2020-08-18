package com.jf.controller.command.mcht;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jf.common.constant.Const;
import com.jf.common.enums.ActivityPreferentialTypeEnum;
import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.Activity;
import com.jf.entity.ActivityArea;
import com.jf.entity.FullCut;
import com.jf.entity.FullDiscount;
import com.jf.entity.FullGive;
import com.jf.entity.MchtUser;
import com.jf.entity.Product;
import com.jf.service.ActivityAreaService;
import com.jf.service.ActivityAuthService;
import com.jf.service.ActivityProductService;
import com.jf.service.ActivityService;
import com.jf.service.CouponService;
import com.jf.service.FullCutService;
import com.jf.service.FullDiscountService;
import com.jf.service.FullGiveService;
import com.jf.service.MchtProductBrandService;
import com.jf.service.ProductService;
import com.jf.service.ProductTypeService;

/**
 * 查看商家特卖
 *
 * @author yjc
 */
public class CMchtActivityView extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtActivityView.class);

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
	private MchtProductBrandService mchtProductBrandService;
	@Resource
	private ProductTypeService productTypeService;
	@Resource
	private ActivityAuthService activityAuthService;
	@Resource
	private ProductService productService;
	@Resource
	private ActivityProductService activityProductService;
	private int id;

	private MchtUser currentUser;
	private String status;
	private int pageNumber;
	private int pageSize;
	
	@Override
	public void init() {
		id = getParaToInt("id");
		status = getPara("status");

		pageNumber = getParaToInt("pageNumber", 1);
		pageSize = getParaToInt("pageSize", 20);

		currentUser = context.getUserInfo();
	}

	@Override
	public void doCommand() {
		if(id > 0){
			ActivityArea activityArea = activityAreaService.findById(id);
			if(activityArea == null || !activityArea.getMchtId().equals(currentUser.getMchtId())){
				throw new BizException("未找到该活动");
			}
			data.put("activityArea", activityArea);

			Activity activity = activityService.findByAreaId(currentUser.getMchtId(), activityArea.getId());
			Assert.notNull(activity, "未找到该活动");
			data.put("activity", activity);

			if(activityArea.getPreferentialType().equals(String.valueOf(ActivityPreferentialTypeEnum.COUPON.getValue()))){
				// 优惠券促销
				data.put("couponList", couponService.listByActivityAreaId(activityArea.getId()));
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
			}else if(activityArea.getPreferentialType().equals(String.valueOf(ActivityPreferentialTypeEnum.GIVE.getValue()))){
				// 满赠
				FullGive fullGive = fullGiveService.findByActivityAreaId(activityArea.getId());
				if(fullGive.getProductId()!=null){
					Product product = productService.selectByPrimaryKey(fullGive.getProductId());
					fullGive.put("productCode", product.getCode());
					fullGive.put("productName", product.getName());
				}
				data.put("fullGive", fullGive);
			}else if(activityArea.getPreferentialType().equals(String.valueOf(ActivityPreferentialTypeEnum.DISCOUNT.getValue()))){
				// 多买优惠
				FullDiscount fullDiscount = fullDiscountService.findByActivityAreaId(activityArea.getId());
				if(fullDiscount != null){
					fullDiscount.put("ruleList", getRuleList(fullDiscount.getRule()));
					data.put("fullDiscount", fullDiscount);
				}

			}

			data.put("auth", activityAuthService.findByActivityId(activity.getId()));
			
			data.put("preferentialTypeDesc", DataDicUtil.getStatusDesc("BU_ACTIVITY_AREA", "PREFERENTIAL_TYPE", activityArea.getPreferentialType()));	// 促销方式
			data.put("statusDesc", DataDicUtil.getStatusDesc("BU_ACTIVITY", "STATUS", activity.getStatus()));
			data.put("pageNumber", pageNumber);
			try {
				if(!StringUtil.isEmpty(getPara("name"))){
					data.put("name", URLDecoder.decode(getPara("name"), "UTF-8"));
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			data.put("productBrandId", getPara("productBrandId"));
			data.put("status", status);
			if(activityArea.getActivityEndTime()!=null && activityArea.getActivityEndTime().before(new Date())){
				data.put("isHide", "1");
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
