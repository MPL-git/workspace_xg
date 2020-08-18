package com.jf.controller.command.mcht;

import com.jf.common.constant.Const;
import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.StrKit;
import com.jf.entity.Activity;
import com.jf.entity.ActivityArea;
import com.jf.entity.MchtUser;
import com.jf.entity.Product;
import com.jf.service.ActivityAreaService;
import com.jf.service.ActivityProductService;
import com.jf.service.ActivityService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 活动商品列表
 *
 * @author huangdl
 */
public class CActivityListProduct extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CActivityListProduct.class);

	@Resource
	private ActivityService activityService;
	@Resource
	private ActivityProductService activityProductService;
	@Resource
	private ActivityAreaService activityAreaService;

	private int activityId;
	private int status;
	private String searchKeywrod;
	private int searchKeywrodType;
	private String artNos;

	private int pageNumber;
	private int pageSize;

	private MchtUser currentUser;

	@Override
	public void init() {
		activityId = getParaToInt("activityId");
		Assert.moreThanZero(activityId, "活动ID不能为空");

		status = getParaToInt("status", Const.ACTIVITY_PRODUCT_STATUS_WAIT);
		searchKeywrod = getPara("searchKeywrod");
		searchKeywrodType = getParaToInt("searchKeywrodType");
		artNos = getPara("artNos");

		pageNumber = getParaToInt("pageNumber", 1);
		pageSize = getParaToInt("pageSize", 20);

		currentUser = context.getUserInfo();
	}

	@Override
	public void doCommand() {
		Activity activity = activityService.findById(activityId);
		if(activity == null || !activity.getMchtId().equals(currentUser.getMchtId())){
			throw new BizException("未找到该活动");
		}
		//查找活动专区信息
		ActivityArea activityArea = activityAreaService.findById(activity.getActivityAreaId());
		QueryObject queryObject = new QueryObject(pageNumber, pageSize);
		if (StrKit.notBlank(searchKeywrod)) {
			if(searchKeywrodType == 1){
				queryObject.addQuery("name", searchKeywrod);
			}else if(searchKeywrodType == 2){
				queryObject.addQuery("artNo", searchKeywrod);
			}else if(searchKeywrodType == 3){
				queryObject.addQuery("remarks", searchKeywrod);
			}else if(searchKeywrodType == 4){
				queryObject.addQuery("code", searchKeywrod);
			}else if(searchKeywrodType == 5){
				queryObject.addQuery("activityId", Integer.parseInt(searchKeywrod));
			}
		}
		if(StrKit.notBlank(artNos)){
			queryObject.addQuery("artNos", Arrays.asList(getPara("artNos").split("\r\n")));
			//queryObject.removeQuery("artNo");
			//queryObject.removeQuery("name");
			//queryObject.removeQuery("remarks");
		}
		queryObject.addQuery("mchtId", currentUser.getMchtId());
		if(status == 1){//可报名
			queryObject.addQuery("hasProductBrandStatus", true);
		}
		queryObject.addSort("create_date", QueryObject.SORT_DESC);
		queryObject.addQuery("saleType", "1");
		Page<Product> page = activityProductService.paginateProduct(activityArea.getId(),activityArea.getIsPreSell(),activityId, status, queryObject);

		data.put("page", page);
	}

}
