package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.utils.Config;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.SobotCustomerServiceMapper;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.MchtInfo;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductPropValueCustom;
import com.jf.entity.SobotCustomerService;
import com.jf.entity.SobotCustomerServiceExample;
import com.jf.entity.SubOrder;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年7月27日 下午2:09:29 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SobotCustomerServiceService extends BaseService<SobotCustomerService, SobotCustomerServiceExample> {
	@Autowired
	private SobotCustomerServiceMapper sobotCustomerServiceMapper;

	@Autowired
	public void setSobotCustomerServiceMapper(SobotCustomerServiceMapper sobotCustomerServiceMapper) {
		this.setDao(sobotCustomerServiceMapper);
		this.sobotCustomerServiceMapper = sobotCustomerServiceMapper;
	}
	
	@Resource
	private ProductService productService;
	@Resource
	private MchtInfoService mchtInfoService;
	@Resource
	private SubOrderService subOrderService;
	@Resource
	private ProductPropValueService productPropValueService;
	@Resource
	private CustomerServiceOrderService customerServiceOrderService;

	public Map<String, Object> getSobotInfoProduct(Integer id, String type, Map<String, Object> map) {
		//背景图片 : 不填
		String backgroundPic = "";
		//背景颜色
		String backgroundColor = "";
		//⽂案类型:1 显⽰客服昵称(默认) 2显⽰固定⽂本 3显⽰console设置的企业名称   固定写死1
		String copyType = "1";
		//固定⽂本: 不填
		String fixedText = "";
		//接入模式: 默认false：显⽰转⼈⼯按钮。true：智能转⼈⼯ 固定写死false
		boolean accessMode = false;
		//accessMode = false 次数: 不填
		String accessCount = "";
		//客服模式控制: -1 不控制 1仅机器⼈ 2仅⼈⼯ 3机器⼈优先 4⼈⼯优先  固定写死-1
		String modelController = "-1";
		//技能组编号：
		String sobotCode = "";
		//技术能名称:
		String sobotNick = "";
		//title,imgUrl,fromUrl,describe,lable:
		//分别为商品名称、商品图片URL、商品链接URL、商品的描述（规格）、商品金额  取商品相关信息，商品链接URL先固定写死，后续再处理
		String title = "";
		String imgUrl = "";
		String fromUrl = "";
		String describe = "";
		String lable = "";
		//是否要求评价：固定写死true
		boolean isEvaluate = true;
		//机器人ID: 固定写死1
		String robotId = "1";
		//转接类型(0-可转⼊其他客服，1-必须转⼊指定客服) 如果有指定客服ID填1，否则填写0
		String changeIntoType = "";
		//指定客服id
		String robotCustomServiceId = "";
		//就否释放会话：true 表⽰释放会话 false 表⽰不释放会话 固定写死true
		boolean isReleaseSession = true;
		//isShowAnnoun 是否显⽰通告 false 
		boolean isShowAnnoun = false;
		//clickGone 点击通告之后是否隐藏
		boolean clickGone = true;
		//annTitle 通告标题
		String annTitle = "this's my title";
		//annLinkUrl 通告链接地址
		String annLinkUrl = "http://xgbuy.cc";
		//商家di
		Integer mchtId = null;
		//客服头像
		String customerPic = "";
		Integer sobotId = null;
		if(type.equals("1")){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", id);
			ProductCustom productCustom = productService.getProductModeById(params);
			if(productCustom == null){
				throw new ArgException("该商品已下架");
			}
			String productPropdesc = getProductPropdesc(productCustom);
			String pic = productCustom.getPic();
			if(StringUtil.isBlank(pic)){
				imgUrl = "";
			}else{
				imgUrl = FileUtil.getImageServiceUrl()+pic;
			}
			title = productCustom.getName();
			fromUrl = Config.getProperty("mUrl")+"/share.html?id="+productCustom.getCode();
			describe = productPropdesc;
			lable = productCustom.getSalePrice().toString();
			mchtId = productCustom.getMchtId();
		}else if(type.equals("2")){
			SubOrder subOrder = subOrderService.findListById(id);
			mchtId = subOrder.getMchtId();
			title = "订单编号："+subOrder.getSubOrderCode();
			fromUrl = "xgbuy.cc";
			lable = subOrder.getPayAmount().toString();
		}else if(type.equals("3")){
			//3平台客服
			sobotId = 6;
		}else if(type.equals("4")){
			//售后
			CustomerServiceOrder customerServiceOrder = customerServiceOrderService.selectByPrimaryKey(id);
			SubOrder subOrder = subOrderService.findListById(customerServiceOrder.getSubOrderId());
			title = "售后编号："+customerServiceOrder.getOrderCode();
			lable = "创建时间："+customerServiceOrder.getCreateDate();
			mchtId = subOrder.getMchtId();
		}else if(type.equals("5")){
			mchtId = id;
		}
		if(mchtId != null){
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
			if(mchtInfo.getStatus().equals("1") || mchtInfo.getStatus().equals("2")){
				sobotId = mchtInfo.getSobotId();
				if(sobotId == null){
					//预防智齿id为null，跳转到智齿的平台客服
					sobotId = 6;
				}
			}else{
				sobotId = 6;
			}
		}
		if(sobotId == null){
			changeIntoType = "0";
		}else{
			SobotCustomerService sobotCustomerService = findModelById(sobotId);
			if(sobotCustomerService == null){
				changeIntoType = "0";
			}else{
				if(sobotCustomerService.getType().equals("1")){
					changeIntoType = "1";
					robotCustomServiceId = sobotCustomerService.getCode();
					sobotNick = sobotCustomerService.getNick();
				}else if(sobotCustomerService.getType().equals("2")){
					sobotCode = sobotCustomerService.getCode();
					sobotNick = sobotCustomerService.getNick();
					changeIntoType = "0";
				}
				customerPic = sobotCustomerService.getPic();
			}
		}
		
		map.put("backgroundPic", backgroundPic);
		map.put("backgroundColor", backgroundColor);
		map.put("copyType", copyType);
		map.put("fixedText", fixedText);
		map.put("accessMode", accessMode);
		map.put("accessCount", accessCount);
		map.put("modelController", modelController);
		map.put("sobotCode", sobotCode);
		map.put("sobotNick", sobotNick);
		map.put("title", title);
		map.put("imgUrl", imgUrl);
		map.put("fromUrl", fromUrl);
		map.put("describe", describe);
		map.put("lable", lable);
		map.put("isEvaluate", isEvaluate);
		map.put("robotId", robotId);
		map.put("changeIntoType", changeIntoType);
		map.put("robotCustomServiceId", robotCustomServiceId);
		map.put("isReleaseSession", isReleaseSession);
		map.put("isShowAnnoun", isShowAnnoun);
		map.put("clickGone", clickGone);
		map.put("annTitle", annTitle);
		map.put("annLinkUrl", annLinkUrl);
		map.put("customerPic", customerPic);
		return map;
	}
	
	

	private String getProductPropdesc(ProductCustom productCustom) {
		String productPropdesc = "";
		String propValId = productCustom.getPropValId();
		String[] propValIds = propValId.split(",");
		List<String> propValIdList = new ArrayList<String>();
		if(propValIds != null && propValIds.length > 0){
			for (String idStr : propValIds) {
				propValIdList.add(idStr);
			}
		}
		if(CollectionUtils.isNotEmpty(propValIdList)){
			Map<String, Object> productParams = new HashMap<String, Object>();
			productParams.put("propValIdList", propValIdList);
			List<ProductPropValueCustom> productPropValueCustoms = productPropValueService
					.getProductPropdesc(productParams);
			if (productPropValueCustoms != null && productPropValueCustoms.size() > 0) {
				for (ProductPropValueCustom productPropValueCustom : productPropValueCustoms) {
					productPropdesc += productPropValueCustom.getPropValue() + "_";
				}
				productPropdesc = productPropdesc.substring(0,productPropdesc.length()-1);
			}
		}
		return productPropdesc;
	}

	private SobotCustomerService findModelById(Integer sobotId) {
		
		return sobotCustomerServiceMapper.selectByPrimaryKey(sobotId);
	}


	public Map<String, Object> getProductBasicsInfo(String code) {
		String name = "";
		String siteprice = "";
		String marketprice = "";
		String brand = "";
		String category = "";
		String imageurl = "";
		String url = "";
		String currency = "";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		ProductCustom productCustom = productService.getProductModeById(params);
		if(productCustom != null){
			name = productCustom.getName();
			imageurl = StringUtil.getPic(productCustom.getPic(), "");
			siteprice = productCustom.getSalePrice().toString();
			marketprice = productCustom.getTagPrice().toString();
			url = Config.getProperty("mUrl")+"/share.html?id="+productCustom.getCode();
		}
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> itemMap = new HashMap<String,Object>();
		itemMap.put("id", code);
		itemMap.put("name", name);
		itemMap.put("siteprice", siteprice);
		itemMap.put("marketprice", marketprice);
		itemMap.put("brand", brand);
		itemMap.put("category", category);
		itemMap.put("imageurl", imageurl);
		itemMap.put("url", url);
		itemMap.put("currency", currency);
		map.put("item", itemMap);
		return map;
	}


	public String getCustomerServiceSoftType(JSONObject reqPRM, MchtInfo mchtInfo) {
		Integer version = reqPRM.getInt("version");
		String system = reqPRM.getString("system");
		String customerServiceSoftType = "1";
		if((system.equals(Const.ANDROID) && version >= 33)
				|| (system.equals(Const.IOS) && version >= 34)){
			String type = mchtInfo.getCustomServiceType();
			Integer xiaonengid = mchtInfo.getXiaonengId();
			if(xiaonengid == null){
				customerServiceSoftType = "1";
			}else{
				if("1".equals(type)){
					customerServiceSoftType = "2";
				}else{
					customerServiceSoftType = mchtInfo.getCustomServiceType();
				}
			}
		}
		return customerServiceSoftType;
	}

}
