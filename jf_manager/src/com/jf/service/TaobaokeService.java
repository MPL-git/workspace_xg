package com.jf.service;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtils;
import com.jf.common.utils.TaobaoUtil;
import com.jf.entity.ProductType;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class TaobaokeService {

	@Resource
	private ProductService productService;

	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private SysParamCfgService sysParamCfgService;
	
	@Resource
	private ThirdPlatformProductInfoService thirdPlatformProductInfoService;

	/**
	 * 
	 * @param productTypId
	 * @param isCoupon
	 * @param pageNo
	 * @param pageSize  页大小，默认20，1~100,最大100，最大100，最大100
	 */
	public void getProducts(int productTypId,Boolean isCoupon, int pageNo, int pageSize) {
		// 请用API
		try {
			if(pageSize==0){
				pageSize=20;
			}
			ProductType productType = productTypeService.selectByPrimaryKey(productTypId);
			SysParamCfgExample example = new SysParamCfgExample();
			example.createCriteria().andParamCodeEqualTo("TAOBAOKEPAGENO");
			List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(example);
			if(sysParamCfgs!=null && sysParamCfgs.size()>0){
				pageNo = Integer.parseInt(sysParamCfgs.get(0).getParamValue());
			}else{
				pageNo = 1;
				SysParamCfg sysParamCfg = new SysParamCfg();
				sysParamCfg.setParamCode("TAOBAOKEPAGENO");
				sysParamCfg.setParamValue("1");
				sysParamCfg.setParamName("淘宝客商品列表起始页码");
				sysParamCfg.setParamOrder("1");
				sysParamCfg.setParamDesc("淘宝客通用物料搜索API（导购）第几页");
				sysParamCfgService.insertSelective(sysParamCfg);
			}
//			JSONArray nTbkItems = TaobaoUtil.getMaterials(null, productType.getName(), isCoupon, pageNo, pageSize);
//			if(nTbkItems!=null&&nTbkItems.size()>0){
//				for (int i = 0; i < nTbkItems.size(); i++) {
//					JSONObject tbkItem=nTbkItems.getJSONObject(i);
//					if(tbkItem.has("coupon_id")&&!StringUtil.isEmpty(tbkItem.getString("coupon_id"))){
//						JSONObject couponObject=TaobaoUtil.getCouponInfo(tbkItem.getString("num_iid"), tbkItem.getString("coupon_id"));
//						if(couponObject!=null){
//							tbkItem.put("coupon_start_fee", JsonUtils.getString(couponObject, "coupon_start_fee"));
//							tbkItem.put("coupon_amount", JsonUtils.getString(couponObject, "coupon_amount"));
//						}
//					}
//					productService.createTaobaokeProduct(tbkItem, productTypId);
//				}
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createTaobaoProduct(String numIids, int productTypeId,int wetaoChannelId) {//频道
		// 请用API
		try {
			JSONArray items = TaobaoUtil.getItemInfo(numIids);
			if (items != null && items.size() > 0) {
				for (int i = 0; i < items.size(); i++) {
					productService.createTaobaokeProduct(items.getJSONObject(i), productTypeId,wetaoChannelId);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JSONArray getTaoBaoProducts(String catId, Boolean isCoupon, int pageNo, int pageSize,String source,String keyword,String start_price,String end_price,String start_tk_rate,String end_tk_rate,String need_free_shipment) {
		JSONArray nTbkItems = null;
		JSONArray resultItems = new JSONArray();
		try {
			nTbkItems = TaobaoUtil.getMaterials(null, keyword, isCoupon, start_price,end_price,start_tk_rate,end_tk_rate,need_free_shipment,pageNo, pageSize);
			if(nTbkItems!=null&&nTbkItems.size()>0){
				for (int i = 0; i < nTbkItems.size(); i++) {
					JSONObject tbkItem=nTbkItems.getJSONObject(i);
					if(!StringUtils.isEmpty(source)){
						if(source.equals("1")){//淘宝
							if(tbkItem.getString("user_type").equals("0")){
								resultItems.add(tbkItem);
							}
						}else{//天猫
							if(tbkItem.getString("user_type").equals("1")){
								resultItems.add(tbkItem);
							}
						}
					}else{
						resultItems.add(tbkItem);
					}
				}
			}
			if(resultItems!=null&&resultItems.size()>0){
				for(int j = 0; j < resultItems.size(); j++){
					JSONObject item=resultItems.getJSONObject(j);
					if(item.has("coupon_id")&&!StringUtil.isEmpty(item.getString("coupon_id"))){
						JSONObject couponObject=TaobaoUtil.getCouponInfo(item.getString("num_iid"), item.getString("coupon_id"));
						if(couponObject!=null){
							item.put("coupon_start_fee", JsonUtils.getString(couponObject, "coupon_start_fee"));
							item.put("coupon_amount", JsonUtils.getString(couponObject, "coupon_amount"));
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultItems;
	}

	public void createTaobaoProduct(int productTypeId,int favoritesId,int pageNo, int pageSize,int wetaoChannelId) {//频道
		try {
			JSONArray items = TaobaoUtil.getFavoritesItem(favoritesId, pageNo, pageSize);
			if (items != null && items.size() > 0) {
				for (int i = 0; i < items.size(); i++) {
					productService.createTaobaokeProduct(items.getJSONObject(i), productTypeId,wetaoChannelId);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

/**
	
	public void getProducts(int productTypId,Boolean isCoupon, int pageNo, int pageSize) {
	// 请用API
	try {

		ProductType productType = productTypeService.selectByPrimaryKey(productTypId);

		if(isCoupon){//领券商品
			JSONArray nTbkItems = TaobaoUtil.getCouponItems(null, productType.getName(), pageNo, pageSize);
			if (nTbkItems != null && nTbkItems.size() > 0) {
				for (int i = 0; i < nTbkItems.size(); i++) {
					//获取优惠券信息
					productService.createTaobaokeProduct(nTbkItems.getJSONObject(i),productType.getId(),true);
				}
			}
		}else{
			JSONArray nTbkItems = TaobaoUtil.getItems(null, productType.getName(), pageNo, pageSize);
			StringBuffer numIids = new StringBuffer();
			if (nTbkItems != null && nTbkItems.size() > 0) {
				for (int i = 0; i < nTbkItems.size(); i++) {
					numIids.append(",").append(nTbkItems.getJSONObject(i).get("num_iid").toString());
				}
			}
			if (numIids.length() > 0) {
				createTaobaoProduct(numIids.substring(1), productTypId);
			}
		}

	} catch (IOException e) {
		e.printStackTrace();
	}
}
**/
}
