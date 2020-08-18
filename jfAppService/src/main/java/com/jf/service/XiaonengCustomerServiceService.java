package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.utils.Config;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.XiaonengCustomerServiceMapper;
import com.jf.entity.ActivityCustom;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.MchtInfo;
import com.jf.entity.Product;
import com.jf.entity.ProductPic;
import com.jf.entity.ProductPicExample;
import com.jf.entity.SubOrder;
import com.jf.entity.XiaonengCustomerService;
import com.jf.entity.XiaonengCustomerServiceExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年4月8日 下午2:05:10 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class XiaonengCustomerServiceService extends BaseService<XiaonengCustomerService, XiaonengCustomerServiceExample> {
	@Autowired
	private XiaonengCustomerServiceMapper xiaonengCustomerServiceMapper;
	
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
	@Resource
	private ActivityService activityService;
	@Resource
	private ProductPicService productPicService;
	@Autowired
	public void setXiaonengCustomerServiceMapper(XiaonengCustomerServiceMapper xiaonengCustomerServiceMapper) {
		this.setDao(xiaonengCustomerServiceMapper);
		this.xiaonengCustomerServiceMapper = xiaonengCustomerServiceMapper;
	}
	
	public Map<String, Object> getXiaoNengInfo(Integer id, String type, Map<String, Object> map) {
		String createDateText = "";
		//这个是用于区分vip用户与非vip用户的，0为游客，1为VIP，VIP用户不会受到排队的限制，直接进入咨询
		String userlevel = "0";
		//有效接待组Id,该组内必须有客服存在,建议使用非管理员客服
		String settingid = "";
		//客服组名称，默认的企业客服名称,在异常情况下显示(如网络异常)
		String groupName = "";
		//咨询发起页标题(必填)
		String pageTitle = "";
		//咨询发起页URL 1
		String pageUrl = "";
		//erp参数, 被用参数,小能只负责经由SDK传到客服端,不做任何处理(个人觉得暂时不考虑)
		String erpParam = "";
		//指定请求客(指定客服分两种模式：
		//1.指定客服时，优先去咨询指定的客服，指定客服不在线会由同组的客服接待
		//2.指定客服不在接待组或者不在线，直接提示客服不在线请留言)
		String kfuid = "";
		
		////////////////////////////////////////////App端商品展示功能Start/////////////////////////////
		//0 不展示
		//1 展示，并以商品id方式展示
		//2 展示，并以独立控件方式展示（独立控件方式只支持在SDK端展示商品）
		String appgoodsinfo_type = "2";
		//商品id
		String goods_id  = "";
		//商品拓展参数（所传参数由商品接口确定）
		String itemparam  = "";
		//商品名称
		String goods_name = "";
		//商品价格
		String goods_price = "";
		//商品图片（URL必须以"http://"开头 ）
		String goods_image = "";
		//商品地址（URL必须以"http://"开头
		String goods_url = "";
		////////////////////////////////////////////App端商品展示功能End/////////////////////////////
		
		////////////////////////////////////////////客服端商品展示Start/////////////////////////////
		//0  不展示1 展示
		String clientgoodsinfo_type = "0";
		////////////////////////////////////////////客服端商品展示End/////////////////////////////
		//商家id
		Integer mchtId = null;
		//小能1售后组客服 2售前组客服
		Integer xiaonengId = null;
		if(type.equals("1")){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", id);
			Product product = productService.selectByPrimaryKey(id);
			if(product == null){
				throw new ArgException("该商品已下架");
			}
			String pic = "";
			ProductPicExample productPicExample = new ProductPicExample();
			productPicExample.createCriteria().andProductIdEqualTo(id).andIsDefaultEqualTo("1").andDelFlagEqualTo("0");
			List<ProductPic> productPics = productPicService.selectByExample(productPicExample);
			if(CollectionUtils.isNotEmpty(productPics)){
				pic = productPics.get(0).getPic();
			}
			BigDecimal saleOrMallPrice = product.getMinMallPrice();
			if("1".equals(product.getSaleType())){
				//活动中的
				Map<String,Object> paramsActivityMap = new HashMap<String,Object>();
				paramsActivityMap.put("productId", id);
				List<ActivityCustom> activityCustoms = activityService.getActivityProductInfo(paramsActivityMap);
				if(CollectionUtils.isNotEmpty(activityCustoms)){
					if(activityCustoms.get(0).getMallPrice() != null){
						saleOrMallPrice = new BigDecimal(activityCustoms.get(0).getSalePrice()+"");
					}
				}
			}
			goods_image = StringUtil.getPic(pic, "");
			mchtId = product.getMchtId();
			goods_name = "商品名称："+product.getName();
			goods_url = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=goods/detail.html?code="+product.getCode();
			goods_id = product.getCode();
			goods_price = "商品价格：¥ "+saleOrMallPrice.toString();
			pageTitle = product.getName();
			pageUrl = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=goods/detail.html?code="+product.getCode();
			clientgoodsinfo_type = "1";
		}else if(type.equals("2")){
			SubOrder subOrder = subOrderService.findListById(id);
			mchtId = subOrder.getMchtId();
			goods_id = subOrder.getId().toString();
			goods_name = "订单编号："+subOrder.getSubOrderCode();
			goods_price = "实付金额：¥ "+subOrder.getPayAmount();
			createDateText = "创建时间："+DateUtil.getFormatDate(subOrder.getCreateDate(), "yyyy-MM-dd HH:mm:ss");
			appgoodsinfo_type = "2";
			clientgoodsinfo_type = "0";
		}else if(type.equals("3")){
			appgoodsinfo_type = "0";
			clientgoodsinfo_type = "0";
			xiaonengId = 1;
		}else if(type.equals("4")){
			//售后
			CustomerServiceOrder customerServiceOrder = customerServiceOrderService.selectByPrimaryKey(id);
			SubOrder subOrder = subOrderService.findListById(customerServiceOrder.getSubOrderId());
			mchtId = subOrder.getMchtId();
			goods_name = "售后单号："+customerServiceOrder.getOrderCode();
			createDateText = "创建时间："+DateUtil.getFormatDate(customerServiceOrder.getCreateDate(), "yyyy-MM-dd HH:mm:ss");
			appgoodsinfo_type = "2";
			clientgoodsinfo_type = "0";
		}else if(type.equals("5")){
			appgoodsinfo_type = "0";
			mchtId = id;
		}
		if(mchtId != null){
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
			if(mchtInfo.getStatus().equals("1") || mchtInfo.getStatus().equals("2")){
				xiaonengId = mchtInfo.getXiaonengId();
			}else{
				if(type.equals("1") || type.equals("5")){//商品详情客服
					xiaonengId = 2;
				}else if(type.equals("2")){//订单客服
					xiaonengId = 1;
				}else if(type.equals("3")){//我的平台客服
					xiaonengId = 1;
				}else if(type.equals("4")){//售后客服
					xiaonengId = 1;
				}
			}
			if(mchtInfo.getXiaonengId() == null){
				if(type.equals("1")){//商品详情客服
					xiaonengId = 2;
				}else if(type.equals("2")){//订单客服
					xiaonengId = 1;
				}else if(type.equals("3")){//我的平台客服
					xiaonengId = 1;
				}else if(type.equals("4")){//售后客服
					xiaonengId = 1;
				}
			}
		}else{
			xiaonengId = 1;
		}
		XiaonengCustomerService xg = selectByPrimaryKey(xiaonengId);
		String isSingle = xg.getType();
		settingid =  xg.getCode();
		if(type.equals("6")){//售后客服
			settingid = "je_1000_1563431041105";
		}
		String pic = StringUtil.getPic(xg.getPic(), "");
		map.put("pic", pic);
		map.put("createDateText", createDateText);
		map.put("userlevel", userlevel);
		map.put("settingid", settingid);
		map.put("groupName", groupName);
		map.put("pageTitle", pageTitle);
		map.put("pageUrl", pageUrl);
		map.put("erpParam", erpParam);
		map.put("kfuid", kfuid);
		map.put("appgoodsinfo_type", appgoodsinfo_type);
		map.put("goods_id", goods_id);
		map.put("itemparam", itemparam);
		map.put("goods_price", goods_price);
		map.put("goods_name", goods_name);
		map.put("goods_image", goods_image);
		map.put("goods_url", goods_url);
		map.put("clientgoodsinfo_type", clientgoodsinfo_type);
		map.put("isSingle", isSingle);
		return map;
	}

}
