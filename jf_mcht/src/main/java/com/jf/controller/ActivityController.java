package com.jf.controller;

import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.query.QueryObject;
import com.jf.controller.command.mcht.*;
import com.jf.dao.ActivityProductMapper;
import com.jf.entity.*;
import com.jf.service.*;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 官方活动
 */
@Controller
@RequestMapping("plat/activity")
public class ActivityController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(ActivityController.class);
	
	@Resource
	private ActivityAreaService activityAreaService;
	
	@Resource
	private ActivityService activityService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private ActivityProductMapper activityProductMapper;

	@Resource
	private MchtProductBrandService mchtProductBrandService;

	@Resource
	private MchtInfoService mchtInfoService;

	@Resource
	private InformationService informationService;
	
	@RequestMapping
	public String index() {
		MchtInfo mchtInfo = getMchtInfo();
		Map<String, Object> data = new HashMap<>();
		data.put("pageStatus", getParaToInt("pageStatus"));
		
		
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtId", mchtInfo.getId());
		queryObject.addQuery("mchtType", mchtInfo.getMchtType());
		queryObject.addQuery("pageStatus", 0);		
		data.put("allCount", activityAreaService.countPlat(queryObject));	//全部活动数量
		
		queryObject = new QueryObject();
		queryObject.addQuery("mchtId", mchtInfo.getId());
		queryObject.addQuery("mchtType", mchtInfo.getMchtType());
		queryObject.addQuery("pageStatus", 1);
		data.put("noActivityCount", activityAreaService.countPlat(queryObject));	// 未报名
		
		queryObject = new QueryObject();
		queryObject.addQuery("mchtId", mchtInfo.getId());
		queryObject.addQuery("mchtType", mchtInfo.getMchtType());
		queryObject.addQuery("pageStatus", 2);
		data.put("activityCount", activityAreaService.countPlat(queryObject));	//已报名
		
		queryObject = new QueryObject();
		queryObject.addQuery("mchtId", mchtInfo.getId());
		queryObject.addQuery("mchtType", mchtInfo.getMchtType());
		queryObject.addQuery("pageStatus", 3);
		data.put("rejectCount", activityAreaService.countPlat(queryObject));	//已驳回
		
		data.put("mchtInfoStatus", mchtInfo.getStatus());
		data.put("name", getPara("name"));
		data.put("pageNumber", getParaToInt("pageNumber"));
		return page(data,"platActivity/list");
	}

	/**
	 * 活动列表
	 */
	@ResponseBody
	@RequestMapping("list")
	public String list() {

		return json(doAction(new CPlatActivityList()));
	}

	/**
	 * 查看专区页面   后端新添查询mchtType进行spop展示判断
	 */
	@RequestMapping("view")
	public String view() {
		Map<String, Object> map=doAction(new CPlatActivityView());
		MchtUser usr = this.getUserInfo();
		map.put("mchtInfo",mchtInfoService.selectByPrimaryKey(usr.getMchtId()));
		return page(map, "platActivity/view_area");
		//return page(doAction(new CPlatActivityView()), "platActivity/view_area");
	}

	/**
	 * 添加活动页面
	 */
	@RequestMapping("edit")
	public String edit() {
		Map<String, Object> map = doAction(new CPlatActivityEdit());
		MchtUser usr = this.getUserInfo();
		// 此页面品牌下拉数据过滤是否特卖条件
		map.put("productBrandList", mchtProductBrandService.getActivityMchtProductBrandList(usr.getMchtId()));	// 品牌
		return page(map, "platActivity/edit");
	}
	
	/**
	 * 管理活动页面   隐藏促销方式
	 */
	@RequestMapping("manage")
	public String manage() {
		Map<String, Object> map = doAction(new CPlatActivityManage());
		MchtUser usr = this.getUserInfo();
		map.put("mchtInfo",mchtInfoService.selectByPrimaryKey(usr.getMchtId()));
		return page(map, "platActivity/manage");
	}
	
	/**
	 * 官方活动报名详情页面  隐藏促销方式
	 */
	@RequestMapping("viewActivity")
	public String viewActivity() {
		Map<String, Object> map = doAction(new CPlatActivityViewActivity());
		MchtUser usr = this.getUserInfo();
		map.put("mchtInfo",mchtInfoService.selectByPrimaryKey(usr.getMchtId()));
		return page(map, "platActivity/viewActivity");
	}

	
	/**
	 * 保存活动
	 */
	@ResponseBody
	@RequestMapping("save")
	public String save() {

		return json(doAction(new CPlatActivitySave()));
	}

	//活动商品管理优化,会场优惠方式新增购物津贴
	@RequestMapping("listProductPage")
	public String listProductPage() {
		return page(doAction(new CActivityListProductPage()), "platActivity/listProduct");
	}
	
	/**
	 * 删除活动
	 */
	@ResponseBody
	@RequestMapping("delete")
	public String delete(String activityId) {
		Assert.notBlank(activityId, "activityId不能为空");
		MchtUser mchtUser = getUserInfo();
		Activity activity = activityService.findById(Integer.parseInt(activityId));
		activity.setDelFlag("1");
		activity.setUpdateBy(mchtUser.getMchtId());
		activity.setUpdateDate(new Date());
		ActivityProductExample example = new ActivityProductExample();
		ActivityProductExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andActivityIdEqualTo(Integer.parseInt(activityId));
		ActivityProduct ap = new ActivityProduct();
		ap.setDelFlag("1");
		ap.setUpdateDate(new Date());
		activityProductMapper.updateByExampleSelective(ap, example);
		activityService.updateByPrimaryKeySelective(activity);
		return json();
	}
	
	/**
	 * 获取排序预览的商品
	 */
	@ResponseBody
	@RequestMapping("getProducts")
	public JSONObject getProducts(HttpServletRequest request,Integer activityId) {
		Activity activity = activityService.findById(activityId);
		List<ProductCustom> productCustoms = productService.getProductsByActivityId(activityId);
		net.sf.json.JSONArray ja = new net.sf.json.JSONArray();
		for(ProductCustom productCustom:productCustoms){
			JSONObject jo = new JSONObject();
			jo.put("id", productCustom.getId());
			jo.put("activityProductId", productCustom.getActivityProductId());
			jo.put("name", productCustom.getName());
			jo.put("img", productCustom.getPic());
			jo.put("price", productCustom.getSalePriceMin());
			jo.put("cost", productCustom.getTagPriceMin());
			java.text.DecimalFormat df =new java.text.DecimalFormat("#.0"); 
			BigDecimal discount = productCustom.getSalePriceMin().divide(productCustom.getTagPriceMin(),10,BigDecimal.ROUND_HALF_DOWN);
			jo.put("discount",df.format(discount.multiply(new BigDecimal(10))));
			ja.add(jo);
		}
		JSONObject data = new JSONObject();
		data.put("list", ja);
		data.put("posterPic", activity.getPosterPic());
		return data;
	}

	/**
	 * 购物津贴后台信息表content
	 */
	@RequestMapping("allowanceInfo")
	public String toAgreement(Model model, HttpServletRequest request) {
		InformationExample example = new InformationExample();
		example.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andIdEqualTo(296);//匹配id
		List<Information> informations = informationService.selectByExampleWithBLOBs(example);
		if(informations!=null && informations.size()>0){
			model.addAttribute("content", informations.get(0).getContent());
		}
		return "platActivity/allowanceInformation";
	}
}
