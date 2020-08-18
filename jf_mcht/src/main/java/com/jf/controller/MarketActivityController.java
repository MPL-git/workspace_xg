package com.jf.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.jf.common.enums.ActivityRuleConfigurationEnum;
import com.jf.common.enums.SourceNicheTypeEnum;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import com.jf.service.SysParamCfgService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.StringUtil;
import com.jf.dao.SingleProductActivityControlMapper;
import com.jf.entity.ActivityRuleConfiguration;
import com.jf.entity.ActivityRuleConfigurationCustom;
import com.jf.entity.ActivityRuleConfigurationExample;
import com.jf.entity.Information;
import com.jf.entity.InformationExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoExample;
import com.jf.entity.MchtProductType;
import com.jf.entity.MchtProductTypeExample;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductCustomExample;
import com.jf.entity.ProductCustomExample.ProductCustomCriteria;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.ShopStory;
import com.jf.entity.ShopStoryDetail;
import com.jf.entity.ShopStoryDetailExample;
import com.jf.entity.ShopStoryExample;
import com.jf.entity.SingleProductActivityControl;
import com.jf.entity.SingleProductActivityControlExample;
import com.jf.entity.SourceNiche;
import com.jf.entity.SourceNicheCustom;
import com.jf.entity.SourceNicheCustomExample;
import com.jf.entity.SourceNicheCustomExample.SourceNicheCustomCriteria;
import com.jf.entity.SourceNicheExample;
import com.jf.entity.SourceNicheExample.Criteria;
import com.jf.entity.SourceNicheProductCustom;
import com.jf.entity.SourceNicheProductExample;
import com.jf.service.ActivityRuleConfigurationService;
import com.jf.service.CommentService;
import com.jf.service.InformationService;
import com.jf.service.MchtInfoService;
import com.jf.service.MchtProductBrandService;
import com.jf.service.MchtProductTypeService;
import com.jf.service.ProductService;
import com.jf.service.ProductTypeService;
import com.jf.service.ShopScoreService;
import com.jf.service.ShopStoryDetailService;
import com.jf.service.ShopStoryService;
import com.jf.service.SourceNicheProductService;
import com.jf.service.SourceNicheService;

/**
 * 营销活动
 *
 * @author chengh
 */
/*
@SuppressWarnings("serial")
*/
@SuppressWarnings("all")
@Controller
@RequestMapping("/market")
public class MarketActivityController extends BaseController {
	
	@Resource
	private	SingleProductActivityControlMapper singleProductActivityControlMapper;	
	@Resource
	private MchtProductTypeService mchtProductTypeService;
	@Resource
	private SourceNicheService sourceNicheService;
	@Resource
	private InformationService informationService;
	@Resource
	private ActivityRuleConfigurationService activityRuleConfigurationService;
	@Resource
	private ProductTypeService productTypeService;
	@Resource
	private CommentService commentService;
	@Resource
	private ShopScoreService shopScoreService;
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	@Resource
	private MchtInfoService mchtInfoService;
	@Resource
	private ProductService productService;
	@Resource
	private SourceNicheProductService sourceNicheProductService;
	@Resource
	private ShopStoryService shopStoryService;
	@Resource
	private ShopStoryDetailService shopStoryDetailService ;
	@Resource
	private SysParamCfgService sysParamCfgService;
	
	
	//品牌活动报名下的营销活动报名列表
	@RequestMapping("signUpActivity")
	public String index(HttpServletRequest request,Model model) {
		Integer mchtId = this.getSessionMchtInfo(request).getId();
			//有好货   调用showSingleActivity方法  该方法在下面定义了  传两参 1商家id  1活动类型
			String showSingleActivity1 = this.showSingleActivity(mchtId,"11");
			model.addAttribute("showSingleActivity1",showSingleActivity1);
			//潮鞋上新
			String showSingleActivity2 = this.showSingleActivity(mchtId,"12");
			model.addAttribute("showSingleActivity2",showSingleActivity2);
			//潮流男装
			String showSingleActivity3 = this.showSingleActivity(mchtId,"13");
			model.addAttribute("showSingleActivity3",showSingleActivity3);
			//断码特惠
			String showSingleActivity4 = this.showSingleActivity(mchtId,"14");
			model.addAttribute("showSingleActivity4",showSingleActivity4);
			//运动鞋服
			String showSingleActivity5 = this.showSingleActivity(mchtId,"15");
			model.addAttribute("showSingleActivity5",showSingleActivity5);
			//潮流美妆
			String showSingleActivity6 = this.showSingleActivity(mchtId,"16");
			model.addAttribute("showSingleActivity6",showSingleActivity6);
			//食品超市
			String showSingleActivity7 = this.showSingleActivity(mchtId,"17");
			model.addAttribute("showSingleActivity7",showSingleActivity7);
			//每日好店
			String showSingleActivity8 = this.showSingleActivity(mchtId,"18");
			model.addAttribute("showSingleActivity8",showSingleActivity8);
			//大学生创业
			String showSingleActivity9 = this.showSingleActivity(mchtId,"19");
			model.addAttribute("showSingleActivity9",showSingleActivity9);
			//领券中心--入口  调showSingleActivity
			String showSingleActivity10 = this.showSingleActivity(mchtId, "20");
			model.addAttribute("showSingleActivity10",showSingleActivity10);
			//页面类型标识
			model.addAttribute("pageStatus","0");
			//跳转到页面展示
			return "marketActivity/signUpActivity";
	}
	
	//已报名商品
	@RequestMapping("enrolledProduct")
	public String enrolledProduct(HttpServletRequest request,Model model) {
		//页面类型标识
		model.addAttribute("pageStatus","1");
		return "marketActivity/enrolledProduct";
	}
	
	//获取已报名商品数据
	@RequestMapping("listProduct")
	@ResponseBody
	public ResponseMsg listProduct(HttpServletRequest request,Model model,Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Integer mchtId = this.getUserInfo().getMchtId();
		SourceNicheCustomExample sourceNicheCustomExample = new SourceNicheCustomExample();
		SourceNicheCustomCriteria criteria = sourceNicheCustomExample.createCriteria();
		criteria.andMchtIdEqualTo(mchtId).andStatusEqualTo("0").andDelFlagEqualTo("0");
		sourceNicheCustomExample.setOrderByClause(" create_date DESC");
		//去除商品已下架的数据
		criteria.andProductStatusJudge();
		//活动类型
		if(StringUtils.isNotBlank(request.getParameter("type"))){
			criteria.andTypeEqualTo(request.getParameter("type"));
		}else{
			criteria.andTypeIn(Arrays.asList("1","4","5","6","7","8","9","13"));
		}
		//商品名称
		if(StringUtils.isNotBlank(request.getParameter("name"))){
			criteria.andProductNameLike(request.getParameter("name"));
		}
		String productIDorCode = request.getParameter("productIDorCode");
		String productIDorCodeData = request.getParameter("productIDorCodeData");
		if(StringUtils.isNotBlank(request.getParameter("productIDorCode"))){
			if(StringUtils.equals(productIDorCode, "0") && StringUtils.isNotBlank(productIDorCodeData)){//商品ID
				String[] productIds = productIDorCodeData.split("\r\n");
				String productIdString = "";
				for (String product : productIds) {
					productIdString += "'"+product+"',";
				}
				criteria.andCodesIn(productIdString.substring(0,productIdString.length()-1));
			}else if(StringUtils.equals(productIDorCode, "1") && StringUtils.isNotBlank(productIDorCodeData)){//商品货号
				String[] artNos = productIDorCodeData.split("\r\n");
				String artNoString = "";
				for (String artNo : artNos) {
					artNoString += "'"+artNo+"',";
				}
				criteria.andProductArtNoEqualTo(artNoString.substring(0,artNoString.length()-1));
			}		
		}
		//报名状态
		if(StringUtils.isNotBlank(request.getParameter("auditStatus"))){
			criteria.andAuditStatusEqualTo(request.getParameter("auditStatus"));
		}
		//上线日期
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		try {
		if(StringUtils.isNotBlank(request.getParameter("upDateBegin"))){
			criteria.andUpDateGreaterThanOrEqualTo(format.parse(request.getParameter("upDateBegin") + " 00:00:00"));
		}
		if(StringUtils.isNotBlank(request.getParameter("upDateEnd"))){
			criteria.andUpDateLessThanOrEqualTo(format.parse(request.getParameter("upDateEnd") + " 23:59:59"));
		}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String pageNumberStr = request.getParameter("pageNumber");
		Integer pageNumber;
		if(!StringUtil.isEmpty(pageNumberStr)){
			pageNumber = Integer.parseInt(pageNumberStr);
		}else{
			pageNumber = page.getPage();
		}
		
		sourceNicheCustomExample.setLimitStart(page.getLimitSize()  * (pageNumber - 1));
		sourceNicheCustomExample.setLimitSize(page.getLimitSize());
		List<SourceNicheCustom> sourceNicheCustoms = sourceNicheService.selectByCustomExample(sourceNicheCustomExample);
		int totalCount = sourceNicheService.selectCountByCustomExample(sourceNicheCustomExample);
		returnData.put("Rows", sourceNicheCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}

	//单品活动入口控制   传商家id  活动类型
	public String showSingleActivity(Integer mchtId,String type){
		SingleProductActivityControlExample space = new SingleProductActivityControlExample();
		//匹配删除标记和类型
		space.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo(type);
		List<SingleProductActivityControl> singleProductActivityControlList = singleProductActivityControlMapper.selectByExample(space);
		String typeShow = "1";
		if(singleProductActivityControlList!=null && singleProductActivityControlList.size()>0){
			SingleProductActivityControl singleProductActivityControl = singleProductActivityControlList.get(0);
			String hideToMchtIds = singleProductActivityControl.getHideToMchtIds();
			String showToMchtIds = singleProductActivityControl.getShowToMchtIds();
			String productTypeIds = singleProductActivityControl.getProductTypeIds();
			if(!StringUtil.isEmpty(hideToMchtIds)){//判断特定不可见
				String[] hideToMchtIdArray = hideToMchtIds.split(",");
				boolean flag = Arrays.asList(hideToMchtIdArray).contains(mchtId.toString());
				if(flag){
					typeShow = "0";
				}else{
					//判断特定可见
					if(!StringUtil.isEmpty(showToMchtIds)){
						String[] showToMchtIdArray = showToMchtIds.split(",");
						flag = Arrays.asList(showToMchtIdArray).contains(mchtId.toString());
						if(!flag){
							typeShow = "0";
						}
					}else{
						//判断不可见类目
						if(!StringUtil.isEmpty(productTypeIds)){
							MchtProductTypeExample mpte = new MchtProductTypeExample();
							mpte.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andIsMainEqualTo("1").andStatusEqualTo("1");
							List<MchtProductType> mchtProductTypeList = mchtProductTypeService.selectByExample(mpte);
							String productTypeIdStr = "";
							if(mchtProductTypeList!=null && mchtProductTypeList.size()>0){
								MchtProductType mchtProductType = mchtProductTypeList.get(0);
								productTypeIdStr = mchtProductType.getProductTypeId().toString();
							}
							String[] productTypeIdArray = productTypeIds.split(",");
							flag = Arrays.asList(productTypeIdArray).contains(productTypeIdStr);
							if(flag){
								typeShow = "0";
							}
						}
					}
				}
			}else{
				//判断特定可见
				if(!StringUtil.isEmpty(showToMchtIds)){
					String[] showToMchtIdArray = showToMchtIds.split(",");
					boolean flag = Arrays.asList(showToMchtIdArray).contains(mchtId.toString());
					if(!flag){
						typeShow = "0";
					}
				}else{
					//判断不可见类目
					if(!StringUtil.isEmpty(productTypeIds)){
						MchtProductTypeExample mpte = new MchtProductTypeExample();
						mpte.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andIsMainEqualTo("1").andStatusEqualTo("1");
						List<MchtProductType> mchtProductTypeList = mchtProductTypeService.selectByExample(mpte);
						String productTypeIdStr = "";
						if(mchtProductTypeList!=null && mchtProductTypeList.size()>0){
							MchtProductType mchtProductType = mchtProductTypeList.get(0);
							productTypeIdStr = mchtProductType.getProductTypeId().toString();
						}
						String[] productTypeIdArray = productTypeIds.split(",");
						boolean flag = Arrays.asList(productTypeIdArray).contains(productTypeIdStr);
						if(flag){
							typeShow = "0";
						}
					}
				}
			}
		}
		return typeShow;
	}
	
	//退出活动
	@RequestMapping("quitActivity")
	@ResponseBody
	public ResponseMsg quitActivity(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			SourceNiche sourceNiche = sourceNicheService.selectByPrimaryKey(Integer.valueOf(id));
			sourceNiche.setStatus("1");
			sourceNiche.setDelFlag("1");
			sourceNicheService.updateByPrimaryKeySelective(sourceNiche);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	//立即报名
	@RequestMapping("signUpImmediately")
	public ModelAndView signUpImmediately(HttpServletRequest request){
		Integer mchtId = this.getMchtInfo().getId();
		Map<String, Object> paramMap = new HashMap<>();
		//活动介绍tab
		paramMap.put("tabType", 0);
		//活动类型标识
		String activityType = request.getParameter("activityType");
		paramMap.put("activityType", activityType);
		//获取DSR评分
		double dsrScore = 0.00;
		if(StringUtils.isBlank(request.getParameter("TAG"))){
			double productScore = commentService.countProductScoreByMhctId(mchtId);
			List<HashMap<String,Object>> list = shopScoreService.countShopScoreByMhctId(mchtId);
			double mchtScore =	Double.parseDouble( list.get(0).get("mchtScore").toString()) ;
			double wuliuScore = Double.parseDouble( list.get(0).get("wuliuScore").toString());
			//dsr评分计算
			dsrScore = (productScore+mchtScore+wuliuScore)/3;
		}
		double activityDsrScore = -1;
		//查询活动要求的店铺DSR评分
		if(StringUtils.isBlank(request.getParameter("TAG"))){
			//匹配查询
			ActivityRuleConfigurationExample e = new ActivityRuleConfigurationExample();
			e.createCriteria().andTypeEqualTo(ActivityRuleConfigurationEnum.getValueByCode(Integer.parseInt(activityType))).andDelFlagEqualTo("0");
			List<ActivityRuleConfiguration> activityRuleConfigurations = activityRuleConfigurationService.selectByExample(e);
			if(!activityRuleConfigurations.isEmpty()){
				if(activityRuleConfigurations.get(0).getShopComment() != null){
					//shopComment店铺评价
					activityDsrScore = activityRuleConfigurations.get(0).getShopComment().doubleValue();
				}	
			}			
			//判断是否可以报名
			if(activityDsrScore == -1 || dsrScore>=activityDsrScore){
				paramMap.put("TAG","0");
			}else{
				paramMap.put("TAG","1");
			}
			
		}else{
			paramMap.put("TAG", request.getParameter("TAG"));
		}

		//活动类型对应的活动介绍
		SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
		sysParamCfgExample.createCriteria().andParamCodeEqualTo("MARKET_ACTIVITY_TYPE").andParamNameEqualTo(activityType);
		List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
		//查询活动介绍
		InformationExample informationExample = new InformationExample();
		//bu_information的status 1启用 2停用 informationType是id   delflag删除标志
		informationExample.createCriteria().andIdEqualTo(Integer.parseInt(sysParamCfgs.get(0).getParamValue())).andStatusEqualTo("1").andDelFlagEqualTo("0");
		List<Information> informationList = informationService.selectByExampleWithBLOBs(informationExample);
		if(!informationList.isEmpty()){
			paramMap.put("information", informationList.get(0));
		}
		//查询活动要求
		ActivityRuleConfigurationCustom activityRuleConfigurationData = activityRuleConfigurationData(activityType);
		paramMap.put("activityRuleConfiguration", activityRuleConfigurationData);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		//每日好的资格判断
		if("8".equals(activityType)){
			SourceNicheCustomExample sourceNicheCustomExample = new SourceNicheCustomExample();
			sourceNicheCustomExample.createCriteria().andTypeEqualTo("2").andDelFlagEqualTo("0").andLinkIdEqualTo(mchtId).andStatusEqualTo("0");
			List<SourceNicheCustom> sourceNicheCustomList = sourceNicheService.selectByCustomExample(sourceNicheCustomExample );
			if(sourceNicheCustomList!=null && sourceNicheCustomList.size()>0 ){//报名过
				SourceNicheCustom sourceNicheCustom = sourceNicheCustomList.get(0);
				Date date = sourceNicheCustom.getAuditDate(); //审核时间
				String auditDate = sdf.format(date);
				paramMap.put("isExistence", "0");
				paramMap.put("sourceNiche", sourceNicheCustom);
				paramMap.put("auditDate", auditDate);
				
				
				if(StringUtil.isEmpty(sourceNicheCustomList.get(0).getAuditStatus())){
					 sourceNicheCustomList.get(0).setAuditStatus("-1");
				}
				
			}else{//没报名过
				paramMap.put("isExistence", "1");
			}
		}
		
		//大学生创业
		if("9".equals(activityType)){
			SourceNicheCustomExample sourceNicheCustomExample = new SourceNicheCustomExample();
			sourceNicheCustomExample.createCriteria().andTypeEqualTo("10").andDelFlagEqualTo("0").andLinkIdEqualTo(mchtId);
			List<SourceNicheCustom> sourceNicheCustomList = sourceNicheService.selectByCustomExample(sourceNicheCustomExample );
			if(sourceNicheCustomList!=null && sourceNicheCustomList.size()>0 ){//报名过
				SourceNicheCustom sourceNicheCustom = sourceNicheCustomList.get(0);
				Date date = sourceNicheCustom.getAuditDate();
				String auditDate = sdf.format(date);
				paramMap.put("isExistence", "0");
				paramMap.put("sourceNiche", sourceNicheCustom);
				paramMap.put("auditDate", auditDate);

				if(StringUtil.isEmpty(sourceNicheCustomList.get(0).getAuditStatus())){
					 sourceNicheCustomList.get(0).setAuditStatus("-1");
				}
				
			}else{//没报名过
				paramMap.put("isExistence", "1");
			}
		}

        //领劵中心报名判断
        if("10".equals(activityType)){
            SourceNicheCustomExample sourceNicheCustomExample = new SourceNicheCustomExample();
            sourceNicheCustomExample.createCriteria().andTypeEqualTo("11").andDelFlagEqualTo("0").andLinkIdEqualTo(mchtId);
            List<SourceNicheCustom> sourceNicheCustomList = sourceNicheService.selectByCustomExample(sourceNicheCustomExample );
            if(sourceNicheCustomList!=null && sourceNicheCustomList.size()>0 ){//报名过
                SourceNicheCustom sourceNicheCustom = sourceNicheCustomList.get(0);
                paramMap.put("isExistence", "0");
                paramMap.put("sourceNiche", sourceNicheCustom);
            }else{//没报名过
                paramMap.put("isExistence", "1");
            }
        }

		return new ModelAndView("marketActivity/signUpImmediately",paramMap);
	}
	
	//活动要求
	@RequestMapping("activityRequirements")
	public ModelAndView activityRequirements(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<>();
		//活动类型标识
		String activityType = request.getParameter("activityType");
		paramMap.put("activityType", activityType);
		//活动介绍tab
		paramMap.put("tabType", 1);
		//查询活动要求
		ActivityRuleConfigurationCustom activityRuleConfigurationData = activityRuleConfigurationData(activityType);
		paramMap.put("activityRuleConfiguration", activityRuleConfigurationData);
		//获取是否可以报名标识   tag碰到0就是符合  1就是不符合
		paramMap.put("TAG",request.getParameter("TAG"));
		
		//获取每日好店,大学生创业的判断 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Integer mchtId = this.getMchtInfo().getId();
		//每日好的资格判断
		if("8".equals(activityType)){
			//资源位管理表
			SourceNicheCustomExample sourceNicheCustomExample = new SourceNicheCustomExample();
			//资源类型type   delflag删除标志      linkid是根据type判断链接   status 添加状态
			sourceNicheCustomExample.createCriteria().andTypeEqualTo("2").andDelFlagEqualTo("0").andLinkIdEqualTo(mchtId).andStatusEqualTo("0");
			List<SourceNicheCustom> sourceNicheCustomList = sourceNicheService.selectByCustomExample(sourceNicheCustomExample );
			if(sourceNicheCustomList!=null && sourceNicheCustomList.size()>0 ){//报名过
				SourceNicheCustom sourceNicheCustom = sourceNicheCustomList.get(0);
				Date date = sourceNicheCustom.getAuditDate();//审核时间
				String auditDate = sdf.format(date);
				paramMap.put("isExistence", "0");
				paramMap.put("sourceNiche", sourceNicheCustom);
				paramMap.put("auditDate", auditDate);
				if(StringUtil.isEmpty(sourceNicheCustomList.get(0).getAuditStatus())){
					 sourceNicheCustomList.get(0).setAuditStatus("-1");
				}
				
			}else{//没报名过
				paramMap.put("isExistence", "1");
			}
		}
		
		//大学生创业
		if("9".equals(activityType)){
			SourceNicheCustomExample sourceNicheCustomExample = new SourceNicheCustomExample();
			sourceNicheCustomExample.createCriteria().andTypeEqualTo("10").andDelFlagEqualTo("0").andLinkIdEqualTo(mchtId);
			List<SourceNicheCustom> sourceNicheCustomList = sourceNicheService.selectByCustomExample(sourceNicheCustomExample );
			if(sourceNicheCustomList!=null && sourceNicheCustomList.size()>0 ){//报名过
				SourceNicheCustom sourceNicheCustom = sourceNicheCustomList.get(0);
				Date date = sourceNicheCustom.getAuditDate();
				String auditDate = sdf.format(date);
				paramMap.put("isExistence", "0");
				paramMap.put("sourceNiche", sourceNicheCustom);
				paramMap.put("auditDate", auditDate);
				if(StringUtil.isEmpty(sourceNicheCustomList.get(0).getAuditStatus())){
					 sourceNicheCustomList.get(0).setAuditStatus("-1");
				}
				
			}else{//没报名过
				paramMap.put("isExistence", "1");
			}
		}

        //领券中心报名判断
        if("10".equals(activityType)){
            SourceNicheCustomExample sourceNicheCustomExample = new SourceNicheCustomExample();
            sourceNicheCustomExample.createCriteria().andTypeEqualTo("11").andDelFlagEqualTo("0").andLinkIdEqualTo(mchtId);
            List<SourceNicheCustom> sourceNicheCustomList = sourceNicheService.selectByCustomExample(sourceNicheCustomExample );
            if(sourceNicheCustomList!=null && sourceNicheCustomList.size()>0 ){//报名过
                SourceNicheCustom sourceNicheCustom = sourceNicheCustomList.get(0);
                paramMap.put("isExistence", "0");
                paramMap.put("sourceNiche", sourceNicheCustom);
            }else{//没报名过
                paramMap.put("isExistence", "1");
            }
        }

		
		
		//DSR
		paramMap.put("activityDsrScore", request.getParameter("activityDsrScore"));
		return new ModelAndView("marketActivity/activityRequirements",paramMap);
	}
	
	//商品报名
		@RequestMapping("productRegistration")
		public ModelAndView productRegistration(HttpServletRequest request) {
			Map<String, Object> paramMap = new HashMap<>();
			Integer mchtId = this.getMchtInfo().getId();
			//商家可报名商品数
			MchtInfoExample mchtInfoExample = new MchtInfoExample();
			mchtInfoExample.createCriteria().andIdEqualTo(mchtId).andDelFlagEqualTo("0");
			MchtInfo mchtInfo = mchtInfoService.selectByExample(mchtInfoExample).get(0);
			String grade = mchtInfo.getGrade();
			if(StringUtils.isBlank(grade)){
				grade = "";
			}
			int count = 0;
			switch (grade) {
			case "":
				count = 1;
				break;
			case "1":
				count = 6;
				break;
			case "2":
				count = 3;
				break;
			case "3":
				count = 2;
				break;
			case "4":
				break;
			default:
				break;
			}
			paramMap.put("count", count);
			paramMap.put("counted", 0);
			//活动类型标识
			String activityType = request.getParameter("activityType");
			paramMap.put("activityType", activityType);
			return new ModelAndView("marketActivity/productRegistration",paramMap);
		}
	
	//获取已报名商品数/商家可报名商品数
		@RequestMapping("productAmount")
		@ResponseBody
		public ResponseMsg productAmount(HttpServletRequest request){
			Map<String, Object> paramMap = new HashMap<>();
			Integer mchtId = this.getMchtInfo().getId();
			String activityType = request.getParameter("activityType");
			try{
			//上线日期
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date upDateBegin = sf.parse(request.getParameter("upDate")+" 00:00:00");
			Date upDateEnd = sf.parse(request.getParameter("upDate")+" 23:59:59");
			//已报名商品数
			SourceNicheCustomExample sourceNicheCustomExample = new SourceNicheCustomExample();
			SourceNicheCustomCriteria criteria = sourceNicheCustomExample.createCriteria();
			criteria.andMchtIdEqualTo(mchtId).andStatusEqualTo("0").andUpDateBetween(upDateBegin, upDateEnd).andDelFlagEqualTo("0").andTypeEqualTo(SourceNicheTypeEnum.getValueByCode(Integer.parseInt(activityType)));
			int counted = sourceNicheService.selectCountByCustomExample(sourceNicheCustomExample);
			//商家可报名商品数
			MchtInfoExample mchtInfoExample = new MchtInfoExample();
			mchtInfoExample.createCriteria().andIdEqualTo(mchtId).andDelFlagEqualTo("0");
			MchtInfo mchtInfo = mchtInfoService.selectByExample(mchtInfoExample).get(0);
			String grade = mchtInfo.getGrade();
			if(StringUtils.isBlank(grade)){
				grade = "";
			}
			int count = 0;
			switch (grade) {
			case "":
				count = 1;
				break;
			case "1":
				count = 6;
				break;
			case "2":
				count = 3;
				break;
			case "3":
				count = 2;
				break;
			case "4":
				break;
			default:
				break;
			}
			paramMap.put("count", count);
			paramMap.put("counted", counted);
			}catch(Exception e){
				e.printStackTrace();
				return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
			}
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,paramMap);
		}
	
	//商品选择
	@RequestMapping("productChoose")
	public ModelAndView productChoose(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<>();
		//活动类型标识
		String activityType = request.getParameter("activityType");
		paramMap.put("activityType", activityType);
		//品牌
		paramMap.put("productBrandList", mchtProductBrandService.getMchtProductBrandList(getUserInfo().getMchtId()));
		return new ModelAndView("marketActivity/productChoose",paramMap);
	}
	
	//商品添加
	@RequestMapping("addProduct")
	@ResponseBody
	public ResponseMsg addProduct(HttpServletRequest request) {
		//活动类型标识
		String activityType = request.getParameter("activityType");
		//资格标识
		boolean tag = true;
		//根据活动类型获取活动报名规则设置
		try{
		//商品ID
		String productId = request.getParameter("productId");
		ProductCustomExample productCustomExample = new ProductCustomExample();
		ProductCustomCriteria criteria = productCustomExample.createCriteria();
		criteria.andIdEqualTo(Integer.valueOf(productId));
		ProductCustom product = productService.selectProductCustomByExample(productCustomExample).get(0);	
		//查询报名规则
		ActivityRuleConfigurationExample e = new ActivityRuleConfigurationExample();
		e.createCriteria().andTypeEqualTo(ActivityRuleConfigurationEnum.getValueByCode(Integer.parseInt(activityType))).andDelFlagEqualTo("0");
		ActivityRuleConfigurationCustom activityRuleConfigurationCustoms = activityRuleConfigurationService.selectCustomByExample(e).get(0);
		//判断是否有资格报名
		tag = qualification(product,activityRuleConfigurationCustoms);
		if(!tag){
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,tag);
		}
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,tag);
	}
	
	//商品查询
	@RequestMapping("selectProduct")
	@ResponseBody
	public ProductCustom selectProduct(HttpServletRequest request) {
		String productId = request.getParameter("productId");
		ProductCustom productCustom = productService.selectProductCustomByPrimaryKey(Integer.valueOf(productId));
		return productCustom;
	}

	//提交报名
	@RequestMapping("signUp")
	@ResponseBody
	public ResponseMsg signUp(HttpServletRequest request) {
		String paramList = request.getParameter("paramList");
		//活动类型标识
		String activityType = request.getParameter("activityType");
		String upDate = request.getParameter("upDate");
		Integer mchtInfoId = this.getMchtInfo().getId();
		try{
			//报名操作
			return sourceNicheService.signUp(paramList,activityType,mchtInfoId,SourceNicheTypeEnum.getValueByCode(Integer.parseInt(activityType)),upDate);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	
	private boolean qualification(ProductCustom product,ActivityRuleConfigurationCustom acCustom) {
		// TODO Auto-generated method stub
		//获取一级类目
		String productType1Id = String.valueOf(product.getProductType1Id());
		//判断是否符合价格规则
		String priceRules = acCustom.getPriceRules();
		if(!StringUtils.isEmpty(priceRules)){
			String[] priceRulesArray = priceRules.split(";");
			for(String priceRule:priceRulesArray){
				String[] priceRuleArray = priceRule.split("_");
				String productTypeIdStr = priceRuleArray[0];
				//如果存在该类目ID，则判断该类目下的价格规则
				if(StringUtils.equals(productType1Id, productTypeIdStr)){
					if(priceRuleArray[1].equals("0")){//吊牌价
						double salePriceMin = product.getSalePriceMin().doubleValue();
						double tagPriceMin = product.getTagPriceMin().doubleValue();
						if(salePriceMin>tagPriceMin*(Double.valueOf(priceRuleArray[2]))*0.1){
							return false;
						}
					}else if(priceRuleArray[1].equals("1")){//不高于XX元
						double salePriceMin = product.getSalePriceMin().doubleValue();
						if(salePriceMin>Double.valueOf(priceRuleArray[2])){
							return false;
						}
					}
				}	
			}
		}
		String salesRules = acCustom.getSalesRules();
		if(!StringUtils.isEmpty(salesRules)){
			String[] salesRulesArray = salesRules.split(";");
			for(String salesRule:salesRulesArray){
				String[] salesRuleArray = salesRule.split("_");
				String productTypeIdStr = salesRuleArray[0];
				//销量规则
				if(StringUtils.equals(productType1Id, productTypeIdStr)){
					Integer totalSalesVolume = product.getTotalSalesVolume() == null?0:product.getTotalSalesVolume();
					if(totalSalesVolume<Integer.valueOf(salesRuleArray[1])){
						return false;
					}
				}
			}
		}
		String stockRules = acCustom.getStockRules();
		if(!StringUtils.isEmpty(stockRules)){
			String[] stockRulesArray = stockRules.split(";");
			for(String stockRule:stockRulesArray){
				String[] stockRuleArray = stockRule.split("_");
				String productTypeIdStr = stockRuleArray[0];
				if(StringUtils.equals(productType1Id, productTypeIdStr)){
					Integer stock = product.getStock();
					if(stock<Integer.valueOf(stockRuleArray[1])){
						return false;
					}
				}
			}
		}
		String goodCommentRate = product.getGoodCommentRate();
		if(acCustom.getFavorableRate() != null){
		 if(Double.valueOf(goodCommentRate)<acCustom.getFavorableRate().doubleValue()){
			return false;
		 }
		}
		return true;
	}

	//商品列表
	@RequestMapping("productList")
	@ResponseBody
	public ResponseMsg productList(HttpServletRequest request,Model model,Page page) {
		String activityType = request.getParameter("activityType");
		Map<String, Object> returnData = new HashMap<String, Object>();
		ProductCustomExample productCustomExample = new ProductCustomExample();
		ProductCustomCriteria productCustomCriteria = productCustomExample.createCriteria();
		productCustomCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andStatusEqualTo("1").andSaleTypeEqualTo("1");
		productCustomExample.setOrderByClause("weights DESC");
		//如果是潮鞋上新，商品需属于运动鞋、男鞋、女鞋、奢品名鞋类目下
		if(StringUtils.equals(activityType, "2")){
			/*productCustomCriteria.andProductType2IdIn(Arrays.asList(6,11,12,658));*/
			productCustomCriteria.andProductType2IdIn(Arrays.asList(336));
		}
		//潮流男装，潮牌奢品类目下
		if(StringUtils.equals(activityType, "3")){
			/*productCustomCriteria.andProductType2IdEqualTo(613);*/
			productCustomCriteria.andProductType2IdEqualTo(336);
		}
		//品牌
		if (!StringUtil.isEmpty(request.getParameter("brandId"))) {
			productCustomCriteria.andBrandIdEqualTo(Integer.valueOf(request.getParameter("brandId")));
		}
		
		//商品ID、名称、货号
		String productIDorNameorArtNo = request.getParameter("productIDorNameorArtNo");
		String productIDorNameorArtNoData = request.getParameter("productIDorNameorArtNoData");
		if(StringUtils.isNotBlank(productIDorNameorArtNoData)){
			List<String> idList = Arrays.asList(productIDorNameorArtNoData.split("\r\n"));		
			if(StringUtils.equals(productIDorNameorArtNo, "0")){
				productCustomCriteria.andCodeIn(idList);
			}
			if(StringUtils.equals(productIDorNameorArtNo, "1")){
				productCustomCriteria.andNameIn(idList);
			}
			if(StringUtils.equals(productIDorNameorArtNo, "2")){
				productCustomCriteria.andArtNoIn(idList);
			}
		}
		
		
		String pageNumberStr = request.getParameter("pageNumber");
		Integer pageNumber;
		if(!StringUtil.isEmpty(pageNumberStr)){
			pageNumber = Integer.parseInt(pageNumberStr);
		}else{
			pageNumber = page.getPage();
		}
		
		//价格
		if (!StringUtil.isEmpty(request.getParameter("priceMin"))) {
			productCustomCriteria.andMinActivityPriceMoreThan(Double.valueOf(request.getParameter("priceMin")));
		}
		if (!StringUtil.isEmpty(request.getParameter("priceMax"))) {
			productCustomCriteria.andMaxActivityPriceLessThan(Double.valueOf(request.getParameter("priceMax")));
		}
		//已报名两个营销活动的商品，不可再进行报名（包括驳回活动）
		productCustomCriteria.andtwoActivity();
		//已报名该活动的商品不可再次报名
		productCustomCriteria.andsignUpEd(SourceNicheTypeEnum.getValueByCode(Integer.parseInt(activityType)));
		
		int totalCount = productService.countProductCustomByExample(productCustomExample);

		
		String pageSize = request.getParameter("pageSize");
		if(StringUtil.isEmpty(pageSize)){
			pageSize ="10";
		}
		
		productCustomExample.setLimitStart(Integer.parseInt(pageSize)  * (pageNumber - 1));
		productCustomExample.setLimitSize(page.getLimitSize());
		List<ProductCustom> productCustoms = productService.selectProductCustomByExample(productCustomExample);
		returnData.put("Rows", productCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 活动报名规则设置列表数据    活动要求
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/singleProductActivity/activityRuleConfiguration/data.shtml")
	@ResponseBody
	public ActivityRuleConfigurationCustom activityRuleConfigurationData(String activityType) {
		List<ActivityRuleConfigurationCustom> activityRuleConfigurationList = new ArrayList<>();
		try {
			//根据活动类型获取活动报名规则设置
			ActivityRuleConfigurationExample e = new ActivityRuleConfigurationExample();
			e.createCriteria().andTypeEqualTo(ActivityRuleConfigurationEnum.getValueByCode(Integer.parseInt(activityType))).andDelFlagEqualTo("0");
			activityRuleConfigurationList = activityRuleConfigurationService.selectCustomByExample(e);
			ProductTypeExample pte = new ProductTypeExample();
			pte.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
			List<ProductType> productTypeList = productTypeService.selectByExample(pte);
			Map<Integer,String> map = new HashMap<Integer,String>();
			for(ProductType productType:productTypeList){
				//商品类别id 商品类型名称
				map.put(productType.getId(), productType.getName());
			}
			for(ActivityRuleConfigurationCustom activityRuleConfigurationCustom : activityRuleConfigurationList){
				String priceRules = activityRuleConfigurationCustom.getPriceRules();
				if(!StringUtils.isEmpty(priceRules)){
					String[] priceRulesArray = priceRules.split(";");
					String priceRulesDesc = "";
					for(String priceRule:priceRulesArray){
						String[] priceRuleArray = priceRule.split("_");
						String productTypeIdStr = priceRuleArray[0];
						String productTypeName = map.get(Integer.parseInt(productTypeIdStr));
						if(StringUtils.isEmpty(priceRulesDesc)){
							if(priceRuleArray[1].equals("0")){
								priceRulesDesc+=productTypeName+"低于吊牌价<span>"+priceRuleArray[2]+"</span>折";
							}else if(priceRuleArray[1].equals("1")){
								priceRulesDesc+=productTypeName+"不高于<span >"+priceRuleArray[2]+"</span>元";
							}
						}else{
							if(priceRuleArray[1].equals("0")){
								priceRulesDesc+="<br>"+productTypeName+"低于吊牌价<span >"+priceRuleArray[2]+"</span>折";
							}else if(priceRuleArray[1].equals("1")){
								priceRulesDesc+="<br>"+productTypeName+"不高于<span >"+priceRuleArray[2]+"</span>元";
							}
						}
					}
					activityRuleConfigurationCustom.setPriceRulesDesc(priceRulesDesc);
				}
				String salesRules = activityRuleConfigurationCustom.getSalesRules();
				if(!StringUtils.isEmpty(salesRules)){
					String[] salesRulesArray = salesRules.split(";");
					String salesRulesDesc = "";
					for(String salesRule:salesRulesArray){
						String[] salesRuleArray = salesRule.split("_");
						String productTypeIdStr = salesRuleArray[0];
						String productTypeName = map.get(Integer.parseInt(productTypeIdStr));
						if(StringUtils.isEmpty(salesRulesDesc)){
							salesRulesDesc+=productTypeName+"商品总销量高于<span >"+salesRuleArray[1]+"</span>";
						}else{
							salesRulesDesc+="<br>"+productTypeName+"商品总销量高于<span >"+salesRuleArray[1]+"</span>";
						}
					}
					activityRuleConfigurationCustom.setSalesRulesDesc(salesRulesDesc);
				}
				
				String stockRules = activityRuleConfigurationCustom.getStockRules();
				if(!StringUtils.isEmpty(stockRules)){
					String[] stockRulesArray = stockRules.split(";");
					String stockRulesDesc = "";
					for(String stockRule:stockRulesArray){
						String[] stockRuleArray = stockRule.split("_");
						String productTypeIdStr = stockRuleArray[0];
						String productTypeName = map.get(Integer.parseInt(productTypeIdStr));
						if(StringUtils.isEmpty(stockRulesDesc)){
							stockRulesDesc+=productTypeName+"商品总库存高于<span >"+stockRuleArray[1]+"</span>";
						}else{
							stockRulesDesc+="<br>"+productTypeName+"商品总库存高于<span >"+stockRuleArray[1]+"</span>";
						}
					}
					activityRuleConfigurationCustom.setStockRulesDesc(stockRulesDesc);
				}
				
				String salesCycleRules = activityRuleConfigurationCustom.getSalesCycleRules();
				if(!StringUtils.isEmpty(salesCycleRules)){
					String[] salesCycleRulesArray = salesCycleRules.split(";");
					String salesCycleRulesDesc = "";
					for(String salesCycleRule:salesCycleRulesArray){
						String[] salesCycleRuleArray = salesCycleRule.split("_");
						String productTypeIdStr = salesCycleRuleArray[0];
						String productTypeName = map.get(Integer.parseInt(productTypeIdStr));
						if(!activityRuleConfigurationCustom.getType().equals("3")){
							if(StringUtils.isEmpty(salesCycleRulesDesc)){
								salesCycleRulesDesc+=productTypeName+"商品活动销售期<span >"+salesCycleRuleArray[1]+"</span>天 品牌报名数<span >"+salesCycleRuleArray[2]+"</span>个";
							}else{
								salesCycleRulesDesc+="<br>"+productTypeName+"商品活动销售期<span >"+salesCycleRuleArray[1]+"</span>天 品牌报名数<span >"+salesCycleRuleArray[2]+"</span>个";
							}
						}else{//限时秒杀/抢购
							if(StringUtils.isEmpty(salesCycleRulesDesc)){
								salesCycleRulesDesc+=productTypeName+" 品牌报名数<span >"+salesCycleRuleArray[2]+"</span>个";
							}else{
								salesCycleRulesDesc+="<br>"+productTypeName+" 品牌报名数<span >"+salesCycleRuleArray[2]+"</span>个";
							}
						}
					}
					activityRuleConfigurationCustom.setSalesCycleRulesDesc(salesCycleRulesDesc);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return activityRuleConfigurationList.get(0);
	}
	
	
	//89的商品报名
	@RequestMapping("shopRegistration")
	public ModelAndView shopRegistration(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<>();
		Integer mchtId = this.getMchtInfo().getId();
		//获取店铺名称
		MchtInfo mcht = mchtInfoService.selectByPrimaryKey(mchtId);
		paramMap.put("mchtName", mcht.getShopName());

		//活动类型标识
		String activityType = request.getParameter("activityType");
		paramMap.put("activityType", activityType);

	
			String type = "";
			if("8".equals(activityType)){
				type="2"; //2是每日好店
			}
			if("9".equals(activityType)){
				type="10";//10是大学生创业
			}
			
			//当类型为大学生创业时,获取店铺故事和详情
			if("9".equals(activityType)){
				ShopStoryExample storyExample = new ShopStoryExample();
				storyExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId);
				List<ShopStory> shopStoryList = shopStoryService.selectByExample(storyExample );
				if(shopStoryList!=null&&shopStoryList.size()>0){
					ShopStory shopStory = shopStoryList.get(0);
					paramMap.put("storyIntroduction", shopStory.getStoryIntroduction().replace("\r\n", "<br>").replace("\n", "<br>"));//故事简介
					
					ShopStoryDetailExample shopStoryDetailServicExample = new ShopStoryDetailExample();
					shopStoryDetailServicExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andShopStoryIdEqualTo(shopStory.getId());
					List<ShopStoryDetail> shopStoryDetailList = shopStoryDetailService.selectByExample(shopStoryDetailServicExample);
					if(shopStoryDetailList!=null&&shopStoryDetailList.size()>0){
							for(ShopStoryDetail ssd : shopStoryDetailList){
								if("0".equals(ssd.getType())){
									paramMap.put("shopStoryDetail", ssd.getContent().replace("\r\n", "<br>").replace("\n", "<br>"));//故事详情.的第一段文字
									break;
								}
							}
						paramMap.put("shopStoryDetailList", shopStoryDetailList);//故事详情
					}
					
				}
			}

			//已报名商品数
			SourceNicheExample sourceExample = new SourceNicheExample();
			Criteria createCriteria = sourceExample.createCriteria();
			createCriteria.andDelFlagEqualTo("0").andTypeEqualTo(type).andLinkIdEqualTo(mchtId).andStatusEqualTo("0");
			List<SourceNiche> sourceNiches = sourceNicheService.selectByExample(sourceExample );
			
			if(sourceNiches!=null && sourceNiches.size()>0){
				SourceNiche sourceNiche = sourceNiches.get(0);
				SourceNicheProductExample sourceNicheProductExample =new SourceNicheProductExample();
				sourceNicheProductExample.createCriteria().andSourceNicheIdEqualTo(sourceNiche.getId()).andDelFlagEqualTo("0");
				int counted = sourceNicheProductService.countByExample(sourceNicheProductExample );
				paramMap.put("count", 6);
				paramMap.put("counted", counted);
				
				//商家的状态
				String auditStatus = sourceNiche.getAuditStatus();
				paramMap.put("auditStatus", auditStatus);
				
				
				//已报名的商品
				List<SourceNicheProductCustom> sourceNicheProductList = sourceNicheProductService.selectCustomByExample(sourceNicheProductExample);
				paramMap.put("sourceNicheProductList", sourceNicheProductList);
				if(sourceNicheProductList!=null && sourceNicheProductList.size()>0){
				String productIdList="";
				/*List<Integer> productIdList = new ArrayList<>();*/
				for (int i = 0; i < sourceNicheProductList.size(); i++) {
					if(i==0){
						productIdList+=sourceNicheProductList.get(i).getProductId();
					}else{
						productIdList+=","+sourceNicheProductList.get(i).getProductId();
					}
				}
				paramMap.put("productIdList", productIdList);
				}
			}else{
				paramMap.put("count", 6);
				paramMap.put("counted", 0);
			}
			return new ModelAndView("marketActivity/shopActivities",paramMap);
		
	}
	
	
	
	
	//89的商品选择
	@RequestMapping("shopActiviChoose")
	public ModelAndView shopActiviChoose(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<>();
		//活动类型标识
		String activityType = request.getParameter("activityType");    
		paramMap.put("activityType", activityType);
		//品牌
		paramMap.put("productBrandList", mchtProductBrandService.getMchtProductBrandList(getUserInfo().getMchtId()));
		return new ModelAndView("marketActivity/shopActiviChoose",paramMap);
	}
	
	
		//89获取已报名商品数/商家可报名商品数
		@RequestMapping("shopProductAmount")
		@ResponseBody
		public ResponseMsg shopProductAmount(HttpServletRequest request){
			Map<String, Object> paramMap = new HashMap<>();
			Integer mchtId = this.getMchtInfo().getId();
			String activityType = request.getParameter("activityType");
			String type = "";
			try{			
				switch (activityType) {
				case "8":
					type = "2";
					break;
				case "9":
					type = "10";
					break;
				default:
					break;
				}

		
				String tempCount = request.getParameter("counted");
				int templateCount=0;
				if(!StringUtil.isEmpty(tempCount)){
					 templateCount = Integer.parseInt(tempCount);
				}
				//已报名商品数
				SourceNicheExample sourceExample = new SourceNicheExample();
				Criteria createCriteria = sourceExample.createCriteria();
				createCriteria.andDelFlagEqualTo("0").andTypeEqualTo(type).andLinkIdEqualTo(mchtId).andStatusEqualTo("0");
				List<SourceNiche> sourceNiches = sourceNicheService.selectByExample(sourceExample);
				if(sourceNiches!=null && sourceNiches.size()>0){
					SourceNiche sourceNiche = sourceNiches.get(0);
					SourceNicheProductExample sourceNicheProductExample =new SourceNicheProductExample();
					sourceNicheProductExample.createCriteria().andSourceNicheIdEqualTo(sourceNiche.getId()).andDelFlagEqualTo("0");
					int counted = sourceNicheProductService.countByExample(sourceNicheProductExample );
					paramMap.put("count", 6);
					paramMap.put("counted", counted+templateCount);
				}else{
					paramMap.put("count", 6);
					paramMap.put("counted", 0);
				}

				}catch(Exception e){
				e.printStackTrace();
				return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
				}
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,paramMap);
		}
		
	
	

	
	
	//89的提交报名
	@RequestMapping("shopSignUp")
	@ResponseBody
	public ResponseMsg shopSignUp(HttpServletRequest request) {
		String paramList = request.getParameter("paramList");
		//活动类型标识
		String activityType = request.getParameter("activityType");
		Integer mchtInfoId = this.getMchtInfo().getId();
		String type = "";
		switch (activityType) {
		case "8":
			type = "2";
			break;
		case "9":
			type = "10";
			break;
		default:
			break;
		}
		try{
			//报名操作
			return sourceNicheService.shopSignUp(paramList,activityType,mchtInfoId,type);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	
	
	//89报名成功后跳转店铺管理
	//已报名商品
	@RequestMapping("successManagement")
	public String successManagement(HttpServletRequest request,Model model) {
		
	Integer mchtId = this.getMchtInfo().getId();
	//获取店铺名称
	MchtInfo mcht = mchtInfoService.selectByPrimaryKey(mchtId);
	model.addAttribute("mchtName", mcht.getShopName());	
	
	
	
	String activityType = request.getParameter("activityType");
	String type = "";
	
		switch (activityType) {
		case "8":
			type = "2";
			break;
		case "9":
			type = "10";
			break;
		default:
			break;
		}
		String tempCount = request.getParameter("counted");
		int templateCount=0;
		if(!StringUtil.isEmpty(tempCount)){
			 templateCount = Integer.parseInt(tempCount);
		}
		
		//当类型为大学生创业时,获取店铺故事和详情
		if("9".equals(activityType)){
			ShopStoryExample storyExample = new ShopStoryExample();
			storyExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId);
			List<ShopStory> shopStoryList = shopStoryService.selectByExample(storyExample );
			if(shopStoryList!=null&&shopStoryList.size()>0){
				ShopStory shopStory = shopStoryList.get(0);
				model.addAttribute("storyContent", shopStory.getStoryContent());
				model.addAttribute("storyIntroduction", shopStory.getStoryIntroduction());
			}
		}
		

		//已报名商品数
		SourceNicheExample sourceExample = new SourceNicheExample();
		Criteria createCriteria = sourceExample.createCriteria();
		createCriteria.andDelFlagEqualTo("0").andTypeEqualTo(type).andLinkIdEqualTo(mchtId).andStatusEqualTo("0");
		List<SourceNiche> sourceNiches = sourceNicheService.selectByExample(sourceExample );
		if(sourceNiches!=null && sourceNiches.size()>0){
			SourceNiche sourceNiche = sourceNiches.get(0);
			SourceNicheProductExample sourceNicheProductExample =new SourceNicheProductExample();
			sourceNicheProductExample.createCriteria().andSourceNicheIdEqualTo(sourceNiche.getId()).andDelFlagEqualTo("0");
			int counted = sourceNicheProductService.countByExample(sourceNicheProductExample );
			model.addAttribute("count", 6);
			model.addAttribute("counted", counted+templateCount);
			
			//已报名的商品
			List<SourceNicheProductCustom> sourceNicheProductList = sourceNicheProductService.selectCustomByExample(sourceNicheProductExample);
			model.addAttribute("sourceNicheProductList", sourceNicheProductList);
			if(sourceNicheProductList!=null && sourceNicheProductList.size()>0){
			String productIdList="";
			/*List<Integer> productIdList = new ArrayList<>();*/
			for (int i = 0; i < sourceNicheProductList.size(); i++) {
				if(i==0){
					productIdList+=sourceNicheProductList.get(i).getProductId();
				}else{
					productIdList+=","+sourceNicheProductList.get(i).getProductId();
				}
			}
			model.addAttribute("productIdList", productIdList);
			}
			
		}else{
			model.addAttribute("count", 6);
			model.addAttribute("counted", 0);
		}
	
		model.addAttribute("auditStatus","0");
		return "marketActivity/shopActivities";
	}
	
	
	//店铺添加商品列表
		@RequestMapping("shopProductList")
		@ResponseBody
		public ResponseMsg shopProductList(HttpServletRequest request,Model model,Page page) {
			String activityType = request.getParameter("activityType");
			Map<String, Object> returnData = new HashMap<String, Object>();
			ProductCustomExample productCustomExample = new ProductCustomExample();
			ProductCustomCriteria productCustomCriteria = productCustomExample.createCriteria();
			productCustomCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andStatusEqualTo("1").andSaleTypeEqualTo("1").andDelFlagEqualTo("0");
			productCustomExample.setOrderByClause("weights DESC");

			//品牌
			if (!StringUtil.isEmpty(request.getParameter("brandId"))) {
				productCustomCriteria.andBrandIdEqualTo(Integer.valueOf(request.getParameter("brandId")));
			}
			
			//商品ID、名称、货号
			String productIDorNameorArtNo = request.getParameter("productIDorNameorArtNo");
			String productIDorNameorArtNoData = request.getParameter("productIDorNameorArtNoData");
			if(StringUtils.isNotBlank(productIDorNameorArtNoData)){
				List<String> idList = Arrays.asList(productIDorNameorArtNoData.split("\r\n"));		
				if(StringUtils.equals(productIDorNameorArtNo, "0")){
					productCustomCriteria.andCodeIn(idList);
				}
				if(StringUtils.equals(productIDorNameorArtNo, "1")){
					productCustomCriteria.andNameIn(idList);
				}
				if(StringUtils.equals(productIDorNameorArtNo, "2")){
					productCustomCriteria.andArtNoIn(idList);
				}
			}
			
			
			String pageNumberStr = request.getParameter("pageNumber");
			Integer pageNumber;
			if(!StringUtil.isEmpty(pageNumberStr)){
				pageNumber = Integer.parseInt(pageNumberStr);
			}else{
				pageNumber = page.getPage();
			}
			
			//价格
			if (!StringUtil.isEmpty(request.getParameter("priceMin"))) {
				productCustomCriteria.andMinActivityPriceMoreThan(Double.valueOf(request.getParameter("priceMin")));
			}
			if (!StringUtil.isEmpty(request.getParameter("priceMax"))) {
				productCustomCriteria.andMaxActivityPriceLessThan(Double.valueOf(request.getParameter("priceMax")));
			}

			
			int totalCount = productService.countProductCustomByExample(productCustomExample);

			
			String pageSize = request.getParameter("pageSize");
			if(StringUtil.isEmpty(pageSize)){
				pageSize ="10";
			}
			
			productCustomExample.setLimitStart(Integer.parseInt(pageSize)  * (pageNumber - 1));
			productCustomExample.setLimitSize(Integer.parseInt(pageSize));
			List<ProductCustom> productCustoms = productService.selectProductCustomByExample(productCustomExample);
			returnData.put("Rows", productCustoms);
			returnData.put("Total", totalCount);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
		}

	//领券中心需要报名
    @RequestMapping("couponSignUp")
    public String couponSignUp(HttpServletRequest request) {
        //活动类型标识
        String activityType = request.getParameter("activityType");
        Integer mchtInfoId = this.getMchtInfo().getId();
        String type = "";
        switch (activityType) {
            case "10":
                type = "11";
                break;
            default:
                break;
        }
        SourceNiche sourceNiche = new SourceNiche();
        sourceNiche.setType(type);
        sourceNiche.setLinkId(mchtInfoId);
        sourceNiche.setStatus("0");
        sourceNiche.setDelFlag("0");
        sourceNiche.setCreateDate(new Date());
        //报名操作
        sourceNicheService.insert(sourceNiche);
	/*	try{
			return "marketActivity/signUpImmediately";
		}
		catch (Exception e) {
			e.printStackTrace();
			return String.valueOf(new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG));
		}*/
		return "marketActivity/signUpImmediately";
    }
}
