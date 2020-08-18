package com.jf.biz;

import com.alibaba.fastjson.JSONObject;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.constant.ResourceConst;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ShoppingCartExtMapper;
import com.jf.dao.ShoppingCartMapper;
import com.jf.entity.CustomAdPageExt;
import com.jf.entity.ShoppingCart;
import com.jf.entity.ShoppingCartExample;
import com.jf.entity.ShoppingCartExt;
import com.jf.entity.ShoppingCartExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShoppingCartBiz extends BaseService<ShoppingCart, ShoppingCartExample> {

	@Autowired
	private ShoppingCartMapper dao;
	@Autowired
	private ShoppingCartExtMapper extDao;


	@Autowired
	private CustomAdPageBiz customAdPageBiz;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * 用户购物车
	 */
	public JSONObject memberCart(int memberId){
		JSONObject data = new JSONObject();
		List<ShoppingCartExt> list = listByMemberId(memberId);

		List<JSONObject> floorList = new ArrayList<>();

		data.put("floorList", floorList);



		//List<Map<String, Object>> memberCouponDTOList= memberCouponService.getMemberUseblePlatformCoupons(platParamsMap);
		data.put("platformCouponList", "用户平台优惠券");

		// 广告连接
		String adUrl = "";
		if(floorList.size() == 0){
			CustomAdPageExt customAdPageExt = customAdPageBiz.findByPageType("6", null);
			if(customAdPageExt != null){
				adUrl = ResourceConst.mUrl+"/xgbuy/views/activity/nest/decorate/brand_decorate.html?infoId="+customAdPageExt.getDecorateInfoId()+"&version=1";
			}
		}
		data.put("adUrl", adUrl);
		data.put("currentTime", new Date().getTime());
		return data;
	}

	public List<ShoppingCartExt> listByMemberId(int memberId){
		ShoppingCartExtExample example = new ShoppingCartExtExample();
		ShoppingCartExtExample.ShoppingCartExtCriteria criteria = example.createCriteria();
		criteria.andMemberIdEqualTo(memberId);
		criteria.andDelFlagEqualTo(Constant.FLAG_FALSE);
		criteria.andStatusEqualTo("0");	//未下单

		List<String> activityTypeList = new ArrayList<>();
		activityTypeList.add(Constant.ACTIVITY_TYPE_AREA);
		activityTypeList.add(Constant.ACTIVITY_TYPE_MCHT_SHOP);
		activityTypeList.add(Constant.ACTIVITY_TYPE_BROKEN_CODE_CLEARING);
		criteria.andActivityTypeIn(activityTypeList);
		return list(example);
	}

	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setShoppingCartMapper(ShoppingCartMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ShoppingCartExt save(ShoppingCartExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ShoppingCart update(ShoppingCart model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ShoppingCart model = new ShoppingCart();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ShoppingCartExt findById(int id){
		return extDao.findById(id);
	}

	public ShoppingCartExt find(ShoppingCartExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ShoppingCartExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ShoppingCartExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ShoppingCartExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ShoppingCartExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ShoppingCartExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ShoppingCartExt> list(ShoppingCartExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ShoppingCartExt> paginate(ShoppingCartExtExample example) {
		List<ShoppingCartExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
