package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.BaseDefine;
import com.jf.common.constant.Const;
import com.jf.common.constant.SysConfig;
import com.jf.common.utils.*;
import com.jf.dao.ExpressMapper;
import com.jf.dao.MchtLicenseChgMapper;
import com.jf.entity.*;
import com.jf.entity.MchtContactExample.Criteria;
import com.jf.service.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MchtUserController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(MchtUserController.class);

	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private MchtUserService mchtUserService;

	@Resource
	private RoleInfoService roleInfoService;

	@Resource
	private RoleUserService roleUserService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private MchtProductTypeService mchtProductTypeService;
	
	@Resource
	private MchtLicenseChgMapper mchtLicenseChgMapper;
	
	@Resource
	private MchtContactService mchtContactService;
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private ExpressMapper expressMapper;
	
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	/**
	 * 用户信息页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/mchtUser/mchtUserIndex")
	public String mchtBrandIndex(Model model, HttpServletRequest request) {
		MchtUser userInfo = mchtUserService.selectByPrimaryKey(this.getSessionUserInfo(request).getId());
		MchtInfoCustom mchtInfo = mchtInfoService.selectMchtInfoCustomById(this.getSessionMchtInfo(request).getId());
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("mchtInfo", mchtInfo);
		model.addAttribute("shopNameAuditStatus", DataDicUtil.getStatusDesc("BU_MCHT_INFO", "SHOP_NAME_AUDIT_STATUS", mchtInfo.getShopNameAuditStatus()==null?"0": mchtInfo.getShopNameAuditStatus()));
		// 如果是子账号，给出角色信息
		if(userInfo.getIsPrimary().equals(Const.FLAG_FALSE)){
			RoleUser roleUser = roleUserService.findByUserId(userInfo.getId());
			if(roleUser != null){
				RoleInfo roleInfo = roleInfoService.findById(roleUser.getRoleId());
				model.addAttribute("roleInfo", roleInfo);
			}
			return "mchtUser/subUserInfo";
		}
		MchtLicenseChgExample e = new MchtLicenseChgExample();
		e.setOrderByClause("id DESC");
		e.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		List<MchtLicenseChg> mchtLicenseChgs = mchtLicenseChgMapper.selectByExample(e);
		if(mchtLicenseChgs!=null && mchtLicenseChgs.size()>0){
			model.addAttribute("mchtLicenseChg", mchtLicenseChgs.get(0));
		}
		return "mchtUser/mchtUserIndex";
	}
	
	/**
	 * 绑定手机
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtUser/toBindMobile")
	public String toBindMobile(Model model, HttpServletRequest request) {
		return "mchtUser/bindMobile";
	}
	@RequestMapping("/mchtUser/toValidateMobile")
	public String toValidateMobile(Model model, HttpServletRequest request) {
		model.addAttribute("mobile", mchtUserService.selectByPrimaryKey(this.getSessionUserInfo(request).getId()).getMobile());
		return "mchtUser/validateMobile";
	}
	
	/**
	 * 绑定手机
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtUser/toSetProtect")
	public String toSetProtect(Model model, HttpServletRequest request) {
		model.addAttribute("userInfo", mchtUserService.selectByPrimaryKey(this.getSessionUserInfo(request).getId()));
		return "mchtUser/setProtect";
	}
	
	/**
	 * 绑定手机
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtUser/toChangePassword")
	public String toChangePassword(Model model, HttpServletRequest request) {
		model.addAttribute("userInfo", mchtUserService.selectByPrimaryKey(this.getSessionUserInfo(request).getId()));
		return "mchtUser/changePassword";
	}
	
	/**
	 * 验证密码
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mchtUser/checkPassword")
	@ResponseBody
	public ResponseMsg checkPassword(HttpServletRequest request) {
		try {
			String password=request.getParameter("password");
			if(StringUtil.isEmpty(password)){
				throw new ArgException("请输入密码");
			}
			
			MchtUser mchtUser=mchtUserService.selectByPrimaryKey(this.getSessionUserInfo(request).getId());
			
			password = SecurityUtil.md5Encode(password);
			if(!password.toLowerCase().equals(mchtUser.getPassword())){
				throw new ArgException("密码错误");
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
	 * 验证手机验证码
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mchtUser/checkMobileValidateCode")
	@ResponseBody
	public ResponseMsg checkMobileValidateCode(HttpServletRequest request) {
		try {
			String mobile=request.getParameter("mobile");
			String validateCode=request.getParameter("validateCode");
			if(StringUtil.isEmpty(mobile)){
				throw new ArgException("请输入手机号");
			}
			if(StringUtil.isEmpty(validateCode)){
				throw new ArgException("请输入验证码");
			}
			
			MchtUser mchtUser=mchtUserService.selectByPrimaryKey(this.getSessionUserInfo(request).getId());
			
			if(!mobile.equals(mchtUser.getMobile())){
				throw new ArgException("手机号错误");
			}
			
			if(!validateCode.equals(request.getSession().getAttribute(mobile))){
				throw new ArgException("验证码错误");
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
	 * 修改密码
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mchtUser/changePasswordConfirm")
	@ResponseBody
	public ResponseMsg changePasswordConfirm(HttpServletRequest request) {
		try {
			String mobile=request.getParameter("mobile");
			String validateCode=request.getParameter("validateCode");
			String oldPassword=request.getParameter("oldPassword");
			String newPassword=request.getParameter("newPassword");
			String confirmPassword=request.getParameter("confirmPassword");
	
			if(StringUtil.isEmpty(mobile)){
				throw new ArgException("请输入手机号");
			}
			if(StringUtil.isEmpty(validateCode)){
				throw new ArgException("请输入验证码");
			}
			
			if(StringUtil.isEmpty(oldPassword)){
				throw new ArgException("请输入旧密码");
			}
			if(StringUtil.isEmpty(newPassword)){
				throw new ArgException("请输入新密码");
			}
			if(StringUtil.isEmpty(confirmPassword)){
				throw new ArgException("请输入确认密码");
			}
			
			if(!newPassword.equals(confirmPassword)){
				throw new ArgException("两次输入的密码不一致");
			}
			
			MchtUser mchtUser=mchtUserService.selectByPrimaryKey(this.getSessionUserInfo(request).getId());
			
			if(!mobile.equals(mchtUser.getMobile())){
				throw new ArgException("手机号错误");
			}
			
			if(!validateCode.equals(request.getSession().getAttribute(mobile))){
				throw new ArgException("验证码错误");
			}
			
		
			
			oldPassword = SecurityUtil.md5Encode(oldPassword);
			if(!oldPassword.toLowerCase().equals(mchtUser.getPassword())){
				throw new ArgException("密码错误");
			}
			
			MchtUser mchtUser4Update=new MchtUser();
			mchtUser4Update.setId(mchtUser.getId());
			mchtUser4Update.setPassword(SecurityUtil.md5Encode(newPassword));
			mchtUser4Update.setUpdateBy(mchtUser.getId());
			mchtUser4Update.setUpdateDate(new Date());
			mchtUserService.updateByPrimaryKeySelective(mchtUser4Update);
			
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 绑定手机
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mchtUser/bindMobileCommit")
	@ResponseBody
	public ResponseMsg mchtInfoPerfectCommitAudit(HttpServletRequest request) {
		try {
			String mobile=request.getParameter("mobile");
			String validateCode=request.getParameter("validateCode");
			if(StringUtil.isEmpty(mobile)){
				throw new ArgException("请输入手机号");
			}
			if(StringUtil.isEmpty(validateCode)){
				throw new ArgException("请输入验证码");
			}
			
			if(!validateCode.equals(request.getSession().getAttribute(mobile))){
				throw new ArgException("验证码错误");
			}
			
			mchtUserService.bindMobile(this.getSessionUserInfo(request).getId(), mobile, true);
			
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	
	/**
	 * 绑定手机
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mchtUser/setPropectCommit")
	@ResponseBody
	public ResponseMsg setPropectCommit(HttpServletRequest request) {
		try {
			String mobile=request.getParameter("mobile");
			String validateCode=request.getParameter("validateCode");
			if(StringUtil.isEmpty(mobile)){
				throw new ArgException("请输入手机号");
			}
			if(StringUtil.isEmpty(validateCode)){
				throw new ArgException("请输入验证码");
			}
			
			if(!validateCode.equals(request.getSession().getAttribute(mobile))){
				throw new ArgException("验证码错误");
			}
			
			MchtUser mchtUser4Update=new MchtUser();
			mchtUser4Update.setId(this.getSessionUserInfo(request).getId());
			
			if("1".equals(request.getParameter("isLoginValidate"))){
				mchtUser4Update.setIsLoginValidate("1");
			}else{
				mchtUser4Update.setIsLoginValidate("0");
			}
			
			
			mchtUserService.updateByPrimaryKeySelective(mchtUser4Update);
			
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	
	
	/**
	 * 获取手机验证码
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/common/getMobileValidateCode")
	@ResponseBody
	public ResponseMsg getMobileValidateCode(Model model, HttpServletRequest request) {

		
		try {
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
				request.getSession().setAttribute(request.getParameter("mobile"),valiDateCode);
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
	 * 检验用户名
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtUser/checkUserCode")
	@ResponseBody
	public ResponseMsg checkUserCode(HttpServletRequest request) {
		
		
		try {
			if(StringUtil.isEmpty(request.getParameter("id"))){
				throw new ArgException("用户不存在!");
			}
			
			MchtUserExample mchtUserExample=new MchtUserExample();
			mchtUserExample.createCriteria().andIdNotEqualTo(Integer.valueOf(request.getParameter("id"))).andUserCodeEqualTo(request.getParameter("userCode"));
			int count=mchtUserService.countByExample(mchtUserExample);
			if(count>0){
				throw new ArgException("用户名已经存在!");
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
	 * 检验用户名
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtUser/checkUserMobile")
	@ResponseBody
	public ResponseMsg checkUserMobile(HttpServletRequest request) {
		
		
		try {
			if(StringUtil.isEmpty(request.getParameter("id"))){
				throw new ArgException("用户不存在!");
			}
			
			MchtUserExample mchtUserExample=new MchtUserExample();
			mchtUserExample.createCriteria().andIdNotEqualTo(Integer.valueOf(request.getParameter("id"))).andMobileEqualTo(request.getParameter("mobile"));
			int count=mchtUserService.countByExample(mchtUserExample);
			if(count>0){
				throw new ArgException("手机号已经存在!");
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
	 * 设置店铺名
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtUser/changeShopInfo")
	public String changeShopInfo(Model model, HttpServletRequest request) {
		MchtInfoCustom mchtInfoCustom = mchtInfoService.selectMchtInfoCustomById(this.getSessionMchtInfo(request).getId());
		if("1".equals(mchtInfoCustom.getIsManageSelf())){
			mchtInfoCustom.setShopType("8");
		}

		model.addAttribute("shopTypeStatusList",DataDicUtil.getStatusList("BU_MCHT_INFO", "SHOP_TYPE"));
		ProductTypeExample e = new ProductTypeExample();
		ProductTypeExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andStatusEqualTo("1");
		c.andTLevelEqualTo(1);
		List<ProductType> productTypes = productTypeService.selectByExample(e);
		model.addAttribute("productTypes", productTypes);
		MchtProductTypeExample mchtProductTypeExample = new MchtProductTypeExample();
		MchtProductTypeExample.Criteria mchtProductTypeCriteria = mchtProductTypeExample.createCriteria();
		mchtProductTypeCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andIsMainEqualTo("1").andStatusEqualTo("1").andDelFlagEqualTo("0");
		List<MchtProductType> mchtProductTypeList = mchtProductTypeService.selectByExample(mchtProductTypeExample);
		if(mchtProductTypeList!=null && mchtProductTypeList.size()>0){
			model.addAttribute("mchtProductType", mchtProductTypeList.get(0));
		}
		
		if(!mchtProductTypeList.isEmpty()){
			//二级类目
			ProductTypeExample e1 = new ProductTypeExample();
			ProductTypeExample.Criteria c1 = e1.createCriteria();
			c1.andDelFlagEqualTo("0");
			c1.andStatusEqualTo("1");
			c1.andParentIdEqualTo(mchtProductTypeList.get(0).getProductTypeId());
			List<ProductType> productTypes2 = productTypeService.selectByExample(e1);
			model.addAttribute("productTypes2", productTypes2);	
		}else{
			//获取默认的二级类目
			ProductTypeExample e1 = new ProductTypeExample();
			ProductTypeExample.Criteria c1 = e1.createCriteria();
			c1.andDelFlagEqualTo("0");
			c1.andStatusEqualTo("1");
			c1.andParentIdEqualTo(productTypes.get(0).getId());
			List<ProductType> productTypes2 = productTypeService.selectByExample(e1);
			model.addAttribute("productTypes2", productTypes2);	
		}			
			model.addAttribute("mchtInfo", mchtInfoCustom);
			return "mchtUser/changeShopInfo";
	}
	
	/**
	 * 检验用户名
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtUser/changeShopInfoCommit")
	@ResponseBody
	public ResponseMsg changeShopInfoCommit(HttpServletRequest request) {
		
		String productType2Id = request.getParameter("productType2Id");
		try {
			String shopNameAuditStatus = request.getParameter("shopNameAuditStatus");
			String zsTotalAuditStatus = request.getParameter("zsTotalAuditStatus");
			if("2".equals(shopNameAuditStatus) || "3".equals(shopNameAuditStatus) || "2".equals(zsTotalAuditStatus)){			
				String businessLicensePic = request.getParameter("businessLicensePic");
				MchtInfo mchtInfo4Update=new MchtInfo();
				mchtInfo4Update.setId(Integer.valueOf(request.getParameter("id")));
				mchtInfo4Update.setBusinessLicensePic(businessLicensePic);
				mchtInfo4Update.setLicenseStatus("1");//待审
				mchtInfo4Update.setUpdateBy(this.getSessionUserInfo(request).getId());
				mchtInfo4Update.setUpdateDate(new Date());
				if(!StringUtil.isEmpty(productType2Id)){
					mchtInfo4Update.setProductType2Id(Integer.valueOf(productType2Id.trim()));
				}
				mchtInfoService.updateByPrimaryKeySelective(mchtInfo4Update);
				
				String productTypeId = request.getParameter("productTypeId");
				if(!StringUtil.isEmpty(productTypeId)){
					MchtProductTypeExample mpyExample = new MchtProductTypeExample();
					mpyExample.createCriteria().andDelFlagEqualTo("0")
					.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
					int mchtProductTypeCount = mchtProductTypeService.countByExample(mpyExample);
					if(mchtProductTypeCount > 15){
						throw new ArgException("最多只能添加15个类目");
					}else {
						MchtProductTypeExample mchtProductTypeExample = new MchtProductTypeExample();
						MchtProductTypeExample.Criteria mchtProductTypeCriteria = mchtProductTypeExample.createCriteria();
						mchtProductTypeCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andIsMainEqualTo("1").andStatusEqualTo("1").andDelFlagEqualTo("0");
						List<MchtProductType> mchtProductTypeList = mchtProductTypeService.selectByExample(mchtProductTypeExample);
						MchtProductType mchtProductType = new MchtProductType();
						if(mchtProductTypeList != null && mchtProductTypeList.size() > 0) {		
							mchtProductType.setDelFlag("1");
							mchtProductType.setUpdateBy(this.getSessionUserInfo(request).getId());
							mchtProductType.setUpdateDate(new Date());
							mchtProductTypeService.updateByExampleSelective(mchtProductType, mchtProductTypeExample);					
						}
						//查询是否有现在要插入的商家品类表状态为删除的
						MchtProductTypeExample mchtProductTypeExample1 = new MchtProductTypeExample();
						MchtProductTypeExample.Criteria mchtProductTypeCriteria1 = mchtProductTypeExample1.createCriteria();
						mchtProductTypeCriteria1.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andProductTypeIdEqualTo(Integer.valueOf(productTypeId)).andIsMainEqualTo("1").andDelFlagEqualTo("1");
						List<MchtProductType> mchtProductTypeList1 = mchtProductTypeService.selectByExample(mchtProductTypeExample1);
						//如果存在，在该条数据上做更新
						MchtProductType newMchtProductType = new MchtProductType();
						if(!mchtProductTypeList1.isEmpty()){
							newMchtProductType = mchtProductTypeList1.get(0);
							newMchtProductType.setDelFlag("0");
							newMchtProductType.setCreateDate(new Date());
							newMchtProductType.setCreateBy(this.getSessionUserInfo(request).getId());
							newMchtProductType.setUpdateBy(this.getSessionUserInfo(request).getId());
							newMchtProductType.setUpdateDate(new Date());
							newMchtProductType.setStatus("1");
							mchtProductTypeService.updateByPrimaryKeySelective(newMchtProductType);
						}else{
							newMchtProductType.setDelFlag("0");
							newMchtProductType.setCreateDate(new Date());
							newMchtProductType.setCreateBy(this.getSessionUserInfo(request).getId());
							newMchtProductType.setUpdateBy(this.getSessionUserInfo(request).getId());
							newMchtProductType.setUpdateDate(new Date());
							newMchtProductType.setMchtId(this.getSessionMchtInfo(request).getId());
							newMchtProductType.setProductTypeId(Integer.valueOf(productTypeId));
							newMchtProductType.setStatus("1");					
							newMchtProductType.setIsMain("1");//是主营
							mchtProductTypeService.insert(newMchtProductType);
						}				
					}
					
				}
				return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);			
			}
			
			String shopType=request.getParameter("shopType").trim();
			String businessFirms = request.getParameter("businessFirms");
			String brand = request.getParameter("brand");
			String productType = request.getParameter("productType");
			String other = request.getParameter("other");
			MchtInfo mchtInfo4Update=new MchtInfo();
			mchtInfo4Update.setId(Integer.valueOf(request.getParameter("id")));
			mchtInfo4Update.setShopType(shopType);
			if(!StringUtil.isEmpty(businessFirms)){
				mchtInfo4Update.setBusinessFirms(businessFirms.trim());
			}else{
				mchtInfo4Update.setBusinessFirms("");
			}
			if(!StringUtil.isEmpty(brand)){
				mchtInfo4Update.setBrand(brand.trim());
			}else{
				mchtInfo4Update.setBrand("");
			}
			if(!StringUtil.isEmpty(productType)){
				mchtInfo4Update.setProductType(productType.trim());
			}else{
				mchtInfo4Update.setProductType("");
			}
			if(!StringUtil.isEmpty(other)){
				mchtInfo4Update.setOther(other.trim());
			}else{
				mchtInfo4Update.setOther("");
			}
			if(!StringUtil.isEmpty(productType2Id)){
				mchtInfo4Update.setProductType2Id(Integer.valueOf(productType2Id.trim()));
			}
			if(shopType.equals("1")){
				mchtInfo4Update.setShopName(request.getParameter("brand").trim()+"官方旗舰店");
			}
			if(shopType.equals("2")){
				mchtInfo4Update.setShopName(request.getParameter("brand").trim()+request.getParameter("productType")+"旗舰店");
			}
			if(shopType.equals("3")){
				mchtInfo4Update.setShopName(request.getParameter("brand").trim()+request.getParameter("businessFirms").trim()+"专卖店");
			}
			if(shopType.equals("4")){
				mchtInfo4Update.setShopName(request.getParameter("businessFirms").trim()+request.getParameter("productType").trim()+"专营店");
			}
			if(shopType.equals("5")){
				mchtInfo4Update.setShopName(request.getParameter("businessFirms").trim()+"官方旗舰店");
			}
			if(shopType.equals("6") || shopType.equals("7")){
				mchtInfo4Update.setShopName(request.getParameter("shopName").trim());
			}
			if(shopType.equals("8")){
				mchtInfo4Update.setShopName(request.getParameter("shopName").trim());
				mchtInfo4Update.setIsManageSelf("1");//自营
			}
			//招商总审未提交
			if("4".equals(zsTotalAuditStatus)){
				String businessLicensePic = request.getParameter("businessLicensePic");
				mchtInfo4Update.setBusinessLicensePic(businessLicensePic);
				mchtInfo4Update.setLicenseStatus("1");//待审
				mchtInfo4Update.setUpdateBy(this.getSessionUserInfo(request).getId());
				mchtInfo4Update.setUpdateDate(new Date());
			}
			MchtInfoExample mchtInfoExample=new MchtInfoExample();
			mchtInfoExample.createCriteria().andIdNotEqualTo(mchtInfo4Update.getId()).andShopNameEqualTo(mchtInfo4Update.getShopName()).andDelFlagEqualTo("0");
			int count=mchtInfoService.countByExample(mchtInfoExample);
			if(count>0){
				throw new ArgException("店铺名已经存在");
			}
			
			mchtInfo4Update.setShopNameAuditStatus("1");//1.已填
			mchtInfo4Update.setUpdateDate(new Date());
			mchtInfo4Update.setUpdateBy(this.getSessionUserInfo(request).getId());
			mchtInfoService.updateByPrimaryKeySelective(mchtInfo4Update);
			
			String productTypeId = request.getParameter("productTypeId");
			if(!StringUtil.isEmpty(productTypeId)){
				MchtProductTypeExample mpyExample = new MchtProductTypeExample();
				mpyExample.createCriteria().andDelFlagEqualTo("0")
				.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
				int mchtProductTypeCount = mchtProductTypeService.countByExample(mpyExample);
				if(mchtProductTypeCount > 15){
					throw new ArgException("最多只能添加15个类目");
				}else {
					MchtProductTypeExample mchtProductTypeExample = new MchtProductTypeExample();
					MchtProductTypeExample.Criteria mchtProductTypeCriteria = mchtProductTypeExample.createCriteria();
					mchtProductTypeCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andIsMainEqualTo("1").andStatusEqualTo("1").andDelFlagEqualTo("0");
					List<MchtProductType> mchtProductTypeList = mchtProductTypeService.selectByExample(mchtProductTypeExample);
					MchtProductType mchtProductType = new MchtProductType();
					if(mchtProductTypeList != null && mchtProductTypeList.size() > 0) {		
						mchtProductType.setDelFlag("1");
						mchtProductType.setUpdateBy(this.getSessionUserInfo(request).getId());
						mchtProductType.setUpdateDate(new Date());
						mchtProductTypeService.updateByExampleSelective(mchtProductType, mchtProductTypeExample);					
					}
					//查询是否有现在要插入的商家品类表状态为删除的
					MchtProductTypeExample mchtProductTypeExample1 = new MchtProductTypeExample();
					MchtProductTypeExample.Criteria mchtProductTypeCriteria1 = mchtProductTypeExample1.createCriteria();
					mchtProductTypeCriteria1.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andProductTypeIdEqualTo(Integer.valueOf(productTypeId)).andIsMainEqualTo("1").andDelFlagEqualTo("1");
					List<MchtProductType> mchtProductTypeList1 = mchtProductTypeService.selectByExample(mchtProductTypeExample1);
					//如果存在，在该条数据上做更新
					MchtProductType newMchtProductType = new MchtProductType();
					if(!mchtProductTypeList1.isEmpty()){
						newMchtProductType = mchtProductTypeList1.get(0);
						newMchtProductType.setDelFlag("0");
						newMchtProductType.setCreateDate(new Date());
						newMchtProductType.setCreateBy(this.getSessionUserInfo(request).getId());
						newMchtProductType.setUpdateBy(this.getSessionUserInfo(request).getId());
						newMchtProductType.setUpdateDate(new Date());
						newMchtProductType.setStatus("1");
						mchtProductTypeService.updateByPrimaryKeySelective(newMchtProductType);
					}else{
						newMchtProductType.setDelFlag("0");
						newMchtProductType.setCreateDate(new Date());
						newMchtProductType.setCreateBy(this.getSessionUserInfo(request).getId());
						newMchtProductType.setUpdateBy(this.getSessionUserInfo(request).getId());
						newMchtProductType.setUpdateDate(new Date());
						newMchtProductType.setMchtId(this.getSessionMchtInfo(request).getId());
						newMchtProductType.setProductTypeId(Integer.valueOf(productTypeId));
						newMchtProductType.setStatus("1");					
						newMchtProductType.setIsMain("1");//是主营
						mchtProductTypeService.insert(newMchtProductType);
					}				
				}
			}
		}catch(ArgException e){
			return new ResponseMsg(ResponseMsg.ERROR, e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		
		
		
	}
	
	
	
	/**
	 * 设置帐号信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtUser/setUserInfo")
	public String setUserInfo(Model model, HttpServletRequest request) {
		model.addAttribute("mchtUser",mchtUserService.selectByPrimaryKey(this.getSessionUserInfo(request).getId()));
		
		return "mchtUser/setUserInfo";
	}
	
	/**
	 * 设置用户信息
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mchtUser/setUserInfoCommit")
	@ResponseBody
	public ResponseMsg setUserInfoCommit(HttpServletRequest request,MchtUser mchtUser) {
		try {
			
			MchtUserExample mchtUserExample=new MchtUserExample();
			mchtUserExample.createCriteria().andUserCodeEqualTo(mchtUser.getUserCode()).andIdNotEqualTo(mchtUser.getId());
			int count=mchtUserService.countByExample(mchtUserExample);
			if(count>0){
				throw new ArgException("用户名已经存在");
			}
			
//			mchtUserExample=new MchtUserExample();
//			mchtUserExample.createCriteria().andIdNotEqualTo(mchtUser.getId()).andMobileEqualTo(mchtUser.getMobile());
//			count=mchtUserService.countByExample(mchtUserExample);
//			if(count>0){
//				throw new ArgException("手机号已经存在");
//			}
			
			MchtUser oldMchtUser=mchtUserService.selectByPrimaryKey(mchtUser.getId());
			
			String oldPassword=SecurityUtil.md5Encode(request.getParameter("oldPassword"));
			if(!oldPassword.toLowerCase().equals(oldMchtUser.getPassword().toLowerCase())){
				throw new ArgException("密码错误");
			}
			
			if(!request.getParameter("mobileValidateCode").equals(request.getSession().getAttribute(mchtUser.getMobile()))){
				throw new ArgException("验证码错误");
			}else{
				request.getSession().removeAttribute(mchtUser.getMobile());
			}
			
			if(!StringUtil.isEmpty(request.getParameter("newPassword"))){
				mchtUser.setPassword(SecurityUtil.md5Encode(request.getParameter("newPassword")));
			}
			mchtUser.setUpdateDate(new Date());
			mchtUser.setUpdateBy(this.getSessionUserInfo(request).getId());
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
	
	/**
	 * 经营许可证--申请更新
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtUser/toMchtLicenseChg")
	public String toMchtLicenseChg(Model model, HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		model.addAttribute("id", id);
		//Integer mchtId = this.getSessionUserInfo(request).getId();
		Integer mchtId = this.getSessionMchtInfo(request).getId();
		model.addAttribute("mchtId", mchtId);
		MchtLicenseChgExample e = new MchtLicenseChgExample();
		e.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
		List<MchtLicenseChg> mchtLicenseChgs = mchtLicenseChgMapper.selectByExample(e);
		if(mchtLicenseChgs!=null && mchtLicenseChgs.size()>0){
			model.addAttribute("mchtLicenseChg", mchtLicenseChgs.get(0));
		}
		model.addAttribute("source", request.getParameter("source"));
		return "mchtUser/toMchtLicenseChg";
	}
	
	/**
	 * 经营许可证--更新列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtUser/mchtLicenseChgList")
	public String mchtLicenseChgList(Model model, HttpServletRequest request) {
		Integer mchtId = Integer.parseInt(request.getParameter("mchtId"));
		model.addAttribute("mchtId", mchtId);
		model.addAttribute("source", request.getParameter("source"));
		MchtLicenseChgExample e = new MchtLicenseChgExample();
		e.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andAuditStatusNotEqualTo("1");
		model.addAttribute("applyCount",mchtLicenseChgMapper.countByExample(e));
		return "mchtUser/mchtLicenseChgList";
	}
	
	/**
	 * 更新列表数据
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/mchtUser/getMchtLicenseChgList")
	@ResponseBody
	public ResponseMsg getMchtLicenseChgList(HttpServletRequest request, Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Integer mchtId = Integer.parseInt(request.getParameter("mchtId"));
		MchtLicenseChgExample e = new MchtLicenseChgExample();
		e.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId);
		int totalCount = mchtLicenseChgMapper.countByExample(e);
		e.setLimitStart(page.getLimitStart());
		e.setLimitSize(page.getLimitSize());
		List<MchtLicenseChg> mchtLicenseChgs = mchtLicenseChgMapper.selectByExample(e);
		returnData.put("Rows", mchtLicenseChgs);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 保存行业经营许可证更新表
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mchtUser/saveMchtLicenseChg")
	@ResponseBody
	public ResponseMsg saveMchtLicenseChg(HttpServletRequest request) {
		try {
			Integer mchtId = Integer.parseInt(request.getParameter("mchtId"));
			String id = request.getParameter("id");
			String businessLicensePic = request.getParameter("businessLicensePic");
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
			if(StringUtil.isEmpty(mchtInfo.getBusinessLicensePic())){
				mchtInfo.setBusinessLicensePic(businessLicensePic);
				mchtInfo.setLicenseStatus("1");
				mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
			}
			MchtLicenseChg mchtLicenseChg = new MchtLicenseChg();
			mchtLicenseChg.setBusinessLicensePic(businessLicensePic);
			mchtLicenseChg.setAuditStatus("0");//未审核
			mchtLicenseChg.setArchiveDealStatus("0");//未归档
			mchtLicenseChg.setUpdateBy(this.getSessionUserInfo(request).getId());
			mchtLicenseChg.setUpdateDate(new Date());
			if(!StringUtil.isEmpty(id)){
				mchtLicenseChg.setId(Integer.parseInt(id));
				mchtLicenseChgMapper.updateByPrimaryKeySelective(mchtLicenseChg);
			}else{
				mchtLicenseChg.setDelFlag("0");
				mchtLicenseChg.setCreateBy(this.getSessionUserInfo(request).getId());
				mchtLicenseChg.setCreateDate(new Date());
				mchtLicenseChg.setMchtId(mchtId);
				mchtLicenseChgMapper.insertSelective(mchtLicenseChg);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		Map<String,String> data = new HashMap<String,String>();
		String source = request.getParameter("source");
		data.put("source", source);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,data);
	}
	
	/**
	 * 行业许可证更新记录立即寄件页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtUser/mchtLicenseChg/toSend")
	public String mchtLicenseChgToSend(Model model,HttpServletRequest request) {
		//获取最新店铺总负责人
		MchtContactExample mchtContactExample = new MchtContactExample();
		Criteria criteria = mchtContactExample.createCriteria();
		//criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andContactTypeEqualTo("1").andAuditStatusEqualTo("1").andDelFlagEqualTo("0");
		criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andContactTypeEqualTo("1").andDelFlagEqualTo("0");
		mchtContactExample.setOrderByClause(" id DESC");
		List<MchtContact> mchtContacts = mchtContactService.selectByExample(mchtContactExample);
		MchtContact contact = new MchtContact();
		if(!mchtContacts.isEmpty()){
			contact = mchtContactService.selectByExample(mchtContactExample).get(0);
		}
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());
		model.addAttribute("mchtInfo",mchtInfo);
		model.addAttribute("mchtContact",contact);
		String id = request.getParameter("id");
		model.addAttribute("id",id);
		ExpressExample e = new ExpressExample();
		ExpressExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		List<Express> expressList = expressMapper.selectByExample(e);
		model.addAttribute("expressList", expressList);
		//获取省市区名称
		List<Area> areas = (List<Area>) areaService.getAddress(contact);
		String address = "";
		for (Area area : areas) {
			address +=area.getAreaName();
		}
		address += contact.getAddress();
		if(StringUtils.equals(address, "null")){
			address = "";
		}
		model.addAttribute("address",address);
		return "mchtUser/mchtLicenseChgToSend";
	}
	
	/**
	 * 行业许可证更新记录立即寄件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/mchtUser/mchtLicenseChg/send")
	@ResponseBody
	public Map<String, Object> mchtBrandChgSend(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		
		MchtContactExample mchtContactExample = new MchtContactExample();
		Criteria criteria = mchtContactExample.createCriteria();
		Integer id2 = this.getSessionMchtInfo(request).getId();
		criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andContactTypeEqualTo("1").andDelFlagEqualTo("0");
		mchtContactExample.setOrderByClause(" id DESC");
		List<MchtContact> mchtContacts = mchtContactService.selectByExample(mchtContactExample);
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());
		if(!mchtContacts.isEmpty()){
			 MchtContact mchtContact = mchtContactService.selectByExample(mchtContactExample).get(0);
		 if("1".equals(mchtContact.getAuditStatus()) ){
				String id = request.getParameter("id");
				String expressId = request.getParameter("expressId");
				String expressNo = request.getParameter("expressNo");
				MchtLicenseChg mchtLicenseChg = mchtLicenseChgMapper.selectByPrimaryKey(Integer.parseInt(id));
				mchtLicenseChg.setExpressId(Integer.parseInt(expressId));
				mchtLicenseChg.setExpressNo(expressNo.trim());
				mchtLicenseChg.setUpdateBy(this.getSessionMchtInfo(request).getId());
				mchtLicenseChg.setUpdateDate(new Date());
				mchtLicenseChg.setArchiveStatus("1");//1.已寄出
				mchtLicenseChg.setArchiveDealStatus("0");//归档状态为未处理
				mchtLicenseChgMapper.updateByPrimaryKeySelective(mchtLicenseChg);
		  }else {

				 resMap.put("returnCode", "404");
					resMap.put("returnMsg", "信息正在审核中,请稍后再试");
			 }
		}else if("1".equals(mchtInfo.getIsManageSelf())){//自营
			String id = request.getParameter("id");
			String expressId = request.getParameter("expressId");
			String expressNo = request.getParameter("expressNo");
			MchtLicenseChg mchtLicenseChg = mchtLicenseChgMapper.selectByPrimaryKey(Integer.parseInt(id));
			mchtLicenseChg.setExpressId(Integer.parseInt(expressId));
			mchtLicenseChg.setExpressNo(expressNo.trim());
			mchtLicenseChg.setUpdateBy(this.getSessionMchtInfo(request).getId());
			mchtLicenseChg.setUpdateDate(new Date());
			mchtLicenseChg.setArchiveStatus("1");//1.已寄出
			mchtLicenseChg.setArchiveDealStatus("0");//归档状态为未处理
			mchtLicenseChgMapper.updateByPrimaryKeySelective(mchtLicenseChg);
		}else{
			resMap.put("returnCode", "404");
			resMap.put("returnMsg", "请添加电商总负责人信息");
		}
		return resMap;

		
	}
}
