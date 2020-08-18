package com.jf.controller;

import com.gzs.common.utils.MD5;
import com.gzs.common.utils.StringUtil;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.ResultBean;
import com.jf.entity.StaffBean;
import com.jf.entity.StaffMchtcontactPermission;
import com.jf.entity.StaffMchtcontactPermissionExample;
import com.jf.entity.StateCode;
import com.jf.entity.SysRoleInfo;
import com.jf.entity.SysRoleInfoExample;
import com.jf.entity.SysStaffInfo;
import com.jf.entity.SysStaffInfoExample;
import com.jf.entity.SysStaffProductType;
import com.jf.entity.SysStaffProductTypeExample;
import com.jf.entity.SysStaffRole;
import com.jf.entity.SysStaffRoleExample;
import com.jf.service.OrgMngService;
import com.jf.service.ProductTypeService;
import com.jf.service.RoleMngService;
import com.jf.service.StaffMchtcontactPermissionService;
import com.jf.service.StatusService;
import com.jf.service.SysStaffInfoService;
import com.jf.service.SysStaffProductTypeService;
import com.jf.service.SysStaffRoleService;
import com.jf.service.WebcommonService;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/orgMng")
public class OrgMngController extends BaseController {
	@Resource
	private OrgMngService operationService;
	@Resource
	private RoleMngService roleService;
	@Resource
	private StatusService statusService;
	@Resource
	private WebcommonService webcommonService;
	@Resource
	private SysStaffInfoService sysStaffInfoService;
	@Resource
	private ProductTypeService productTypeService;
	@Resource
	private SysStaffProductTypeService sysStaffproductTypeService;
	@Resource
	private StaffMchtcontactPermissionService staffmchtcontactpermissionService;
	
	@Resource
	private SysStaffRoleService sysStaffRoleService;
	
	String RT_PAGE_ROOT="/orgMng";
	String DEFAULT_STAFF_PWD="123456";
	/**
	 * 模块入口
	 * @param request
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/index.shtml")
	public ModelAndView index(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = RT_PAGE_ROOT + "/index";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			// 查询权限菜单
			StaffBean staffBean = this.getSessionStaffBean(request);
			if ("4".equals(staffBean.getStaffID())){
				paramMap.put("STAFF_ID", 1);
			}else{
				paramMap.put("STAFF_ID", staffBean.getStaffID());
			}
			ObjectMapper objectMapper = new ObjectMapper();
			resMap.put("ORG_TREE_JSON", objectMapper.writeValueAsString(operationService.queryStaffOrgList(paramMap)));

			//resMap.put("MENU_TREE_HTML", Menu.buildJUITree(Menu.buildTree( operationService.queryStaffOrgList(paramMap), 0), request.getContextPath(), 0, "target=\"ajax\" rel=\"jbsxBox\""));

		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ModelAndView(rtPage,resMap);
	}
	@RequestMapping(value = "/orgtree.shtml")
	@ResponseBody
	public   Map<String, Object>   orgtree(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = RT_PAGE_ROOT + "/index";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			// 查询权限菜单
			StaffBean staffBean = this.getSessionStaffBean(request);
			if ("4".equals(staffBean.getStaffID())||"6".equals(staffBean.getStaffID())||"31".equals(staffBean.getStaffID())||"13".equals(staffBean.getStaffID())){
				paramMap.put("STAFF_ID", 1);
			}else{
				paramMap.put("STAFF_ID", staffBean.getStaffID());
			}
			ObjectMapper objectMapper = new ObjectMapper();
			resMap.put("ORG_TREE_JSON", objectMapper.writeValueAsString(operationService.queryStaffOrgList(paramMap)));
			 
			//resMap.put("MENU_TREE_HTML", Menu.buildJUITree(Menu.buildTree( operationService.queryStaffOrgList(paramMap), 0), request.getContextPath(), 0, "target=\"ajax\" rel=\"jbsxBox\""));

		} catch (Exception e) {
			// TODO: handle exception
		}
		return resMap;
	}
	@RequestMapping(value = "/staffindex.shtml")
	public ModelAndView staffindex(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = RT_PAGE_ROOT + "/staffindex";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			// 查询权限菜单
			StaffBean staffBean = this.getSessionStaffBean(request);
			if ("4".equals(staffBean.getStaffID())||"6".equals(staffBean.getStaffID())||"31".equals(staffBean.getStaffID())||"13".equals(staffBean.getStaffID())){
				paramMap.put("STAFF_ID", 1);
			}else{
				paramMap.put("STAFF_ID", staffBean.getStaffID());
			}
			ObjectMapper objectMapper = new ObjectMapper();
			resMap.put("ORG_TREE_JSON", objectMapper.writeValueAsString(operationService.queryStaffOrgList(paramMap)));

			//System.out.print(resMap);
			//resMap.put("MENU_TREE_HTML", Menu.buildJUITree(Menu.buildTree( operationService.queryStaffOrgList(paramMap), 0), request.getContextPath(), 0, "target=\"ajax\" rel=\"jbsxBox\""));

		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ModelAndView(rtPage,resMap);
	}
	/**
	 * 人员列表-index
	 * @param request
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/list_index.shtml")
	public ModelAndView listIndex(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = RT_PAGE_ROOT+"/list";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			resMap.put("ROLE_ID", staffBean.getRoleId()); 
			resMap.put("ORG_ID",StringUtil.valueOf(paramMap.get("ORG_ID")));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ModelAndView(rtPage,resMap);
	}
	@RequestMapping(value = "/list.shtml")
	@ResponseBody
	public Map<String, Object> datalist(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		String totalCount ="0";
		try {
			//查询权限菜单
			StaffBean staffBean = this.getSessionStaffBean(request);
			resMap.put("STAFF_ID", staffBean.getStaffID()); 
			paramMap=this.setPageParametersLiger(request,paramMap);
        	totalCount = operationService.queryDataCount(paramMap);
			dataList = operationService.queryDataList(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
			totalCount = "0";
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	
	}
	@RequestMapping(value = "/toadd_org.shtml")
	public ModelAndView toaddOrg(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = RT_PAGE_ROOT+"/toadd_org";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			String ifEdit=StringUtil.valueOf(paramMap.get("IF_EDIT"));
	        String seqId=StringUtil.valueOf(paramMap.get("ORG_ID"));
			resMap.put("ORG_ID", seqId);
	        if("1".equals(ifEdit)){
	        	Map <String,Object> beanMap= operationService.selectOrgInfo(paramMap);
	        	if(beanMap!=null&&beanMap.size()>0){
					resMap.putAll(beanMap);
	        	}
			}else{//新增
				resMap.put("ORG_ID", "");
				resMap.put("PARENT_ID", seqId);
			}
		} catch (Exception e) {
		}
		return new ModelAndView(rtPage, resMap);
	}
	@RequestMapping(value = "/save_org.shtml") 
	@ResponseBody
	public Map<String, Object> saveOrg(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		List<?> dataList=null;
		try {

			String seqId=StringUtil.valueOf(paramMap.get("ORG_ID"));
			StaffBean staffBean = this.getSessionStaffBean(request);
			paramMap.put("LOGIN_STAFF_ID", staffBean.getStaffID());
			boolean saveFlag=true;
			if("".equals(seqId)){//新增
        		operationService.saveDataOrg(paramMap);
			}else{//修改
	        	operationService.updateDataOrg(paramMap);
			}
			 if(saveFlag){
				 code = "0";
				 msg = "操作成功";
			  }
		} catch (Exception e) {
			  e.printStackTrace();
			  code = "1";
				 msg = "操作异常";
		}
		resMap.put("RESULT_CODE", code);
		resMap.put("RESULT_MESSAGE", msg); 
		resMap.put("ORG_ID", paramMap.get("ORG_ID"));
		resMap.put("ORG_NAME", paramMap.get("ORG_NAME"));
		return resMap;
		//return new ModelAndView(rtPage, resMap);
	}
	
	@RequestMapping(value = "/delData_org.shtml")
	@ResponseBody
	public Map<String, Object> delDataOrg(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		StaffBean staffBean = this.getSessionStaffBean(request);
		try {
			 
			//自己的部门不能删除
			String seqId=StringUtil.valueOf(paramMap.get("ORG_ID"));
			
			String loginOrgId=staffBean.getOrgId();
			if(loginOrgId.equals(seqId)){
				resMap.put("RESULT_CODE", 1);
				resMap.put("RESULT_MESSAGE", "对不起，您不能删除自己所在的部门");
				return resMap;
			}
			//系统初始化根节点1不能删除
			if("1".equals(seqId)){
				resMap.put("RESULT_CODE", 1);
				resMap.put("RESULT_MESSAGE", "当前选中部门为【部门初始化根节点】，不能删除"); 
				return resMap;
			}
			if(!"".equals(seqId)){
				HashMap <String,Object> orgMap =new HashMap<String,Object>();
				orgMap.put("F_ORG_ID", seqId);
	        	Map <String,Object> beanMap= operationService.selectOrgInfo(orgMap);
	        	if(beanMap!=null&&beanMap.size()>0){

					resMap.put("RESULT_CODE", 1);
					resMap.put("RESULT_MESSAGE", "当前选中部门还有下级部门，不能删除");  
					return resMap;
	        	}
	        	orgMap =new HashMap<String,Object>();
				orgMap.put("ORG_ID", seqId);
	        	List<?> staffList= operationService.queryDataList(orgMap);
	        	if(staffList!=null&&staffList.size()>0){
	        		resMap.put("RESULT_CODE", 1);
					resMap.put("RESULT_MESSAGE", "当前选中部门下还有人员，不能删除");   
					return resMap;
	        	}
			}
			paramMap.put("LOGIN_STAFF_ID", staffBean.getStaffID()); 
			operationService.deleteDataOrg(paramMap);
			code = 	"0";			 
			msg = "操作成功";
		} catch (Exception e) {
			  e.printStackTrace();
			  code = 	"1";			 
				msg = "操作异常";
		}

		resMap.put("ORG_ID", paramMap.get("ORG_ID"));
		resMap.put("RESULT_CODE", code);
		resMap.put("RESULT_MESSAGE",msg);
		return resMap;
	}
	
	@RequestMapping(value = "/toadd_staff.shtml")
	public ModelAndView toaddStaff(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = RT_PAGE_ROOT+"/toadd_staff";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		Date date = new Date();
    	GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		resMap.put("BIRTHDAY", sdf.format(gregorianCalendar.getTime())); 
		try {
			resMap.put("ORG_ID", StringUtil.valueOf(paramMap.get("ORG_ID")));
	        String seqId=StringUtil.valueOf(paramMap.get("STAFF_ID"));
       
	        if(!"".equals(seqId)){
	        	paramMap.put("CUR_STAFF_ID", seqId);
	        	List<?> dataList=operationService.queryDataList(paramMap);
	        	if(dataList!=null&&dataList.size()>0){
	        		HashMap<String,Object> tmpMap = (HashMap<String,Object>)dataList.get(0);
	        		resMap.remove("BIRTHDAY");
					resMap.putAll(tmpMap); 
	        	}
			}
	     
			paramMap.put("TABLE_NAME","SYS_STAFF_INFO"); 
			paramMap.put("COL_NAME","SEX_TYPE");
	    	List<?> SEXTYPElIST = webcommonService.sysStatusList(paramMap);
	    	resMap.put("SEXTYPElIST", SEXTYPElIST);
	    	paramMap.clear();
	    	paramMap.put("TABLE_NAME","SYS_STAFF_INFO");  
			paramMap.put("COL_NAME","CERT_TYPE");
	    	List<?> CERTTYPElist = webcommonService.sysStatusList(paramMap);
	    	resMap.put("CERTTYPElist", CERTTYPElist);
	    	
		} catch (Exception e) {
		}
		return new ModelAndView(rtPage, resMap);
	}
	
	@RequestMapping(value="/CHECKstaffinfo.shtml")
	public void hademailBeReg(HttpServletRequest request,HttpServletResponse response
			,@RequestParam HashMap<String, Object> map){ 
		Map resMap = new HashMap<String,Object>();
		List<?> dataList=null;
		String id = (String)map.get("id");  
		int rst =0;
		if (id.equals(""))
		{rst=0;}else{
		
		HashMap<String,Object> temp = new HashMap<String,Object>();
        temp.put("CHECK_STAFF_CODE", id.trim());  
        dataList= operationService.querycheckStaff(temp);
        
    	if(dataList!=null&&dataList.size()>0){
    		rst=0;
    	}else{
    		rst=1;
    	}}
		  
		map.put("rst", rst);
		response_json(map,response); 
		return ;
	}
	
	@RequestMapping(value = "/save_staff.shtml") 
	public ModelAndView saveStaff(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String rtPage = "/success/success";
		String code = null;
		String msg = "";
		List<?> dataList=null;
		try {
			//参数不能为空验证
			String [] paramStr={"ORG_ID:所属部门","STAFF_NAME:人员名称","MOBILE_PHONE:联系号码","EMAIL:EMAIL"};
			ResultBean checkBean=requiredCheck(paramStr,paramMap );
			if(!checkBean.getResultCode().equals(StateCode.SUCCESS.getStateCode())){
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, checkBean.getMessage());
				 return new ModelAndView(rtPage,resMap); 
			}
			String seqId=StringUtil.valueOf(paramMap.get("STAFF_ID"));
			StaffBean staffBean = this.getSessionStaffBean(request);
			paramMap.put("LOGIN_STAFF_ID", staffBean.getStaffID());
			HashMap<String,Object> checkMap=new HashMap<String,Object>();
			checkMap.put("CHECK_STAFF_CODE", StringUtil.valueOf(paramMap.get("STAFF_CODE"))); 
			boolean saveFlag=true;
			if( seqId==null || "".equals(seqId)){
				//验证唯一
	        	dataList=operationService.querycheckStaff(checkMap);
	        	if(dataList!=null&&dataList.size()>0){
	   			   code = StateCode.JSON_AJAX_ERROR.getStateCode();
				   msg ="该人员工号在系统中已存在，不能重复添加";
	        	   saveFlag=false;
	        	}
	        	String orgId=operationService.queryOrgStatus(paramMap);
	        	if(!"A".equals(orgId)){
	   			   code = StateCode.JSON_AJAX_ERROR.getStateCode();
				   msg ="部门已删除或停用，不能添加人员";
	        	   saveFlag=false;
	        	}
	        	if(saveFlag){
	        		operationService.saveDataStaff(paramMap);
	        		//人员密码加密
			    	MD5 md5Obj = new MD5();
			    	String enPwd=md5Obj.enPasswd(StringUtil.valueOf(paramMap.get("STAFF_ID")),DEFAULT_STAFF_PWD);
			    	paramMap.put("STAFF_PASS", enPwd);
			    	operationService.resetStaffPwd(paramMap);
	        	}
			}
			else{
				 
		        	operationService.updateDataStaff(paramMap); 
			}
			 if(saveFlag){
				 code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				 msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			  }
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		 return new ModelAndView(rtPage, resMap);
	}
	
	@RequestMapping(value = "/delData_staff.shtml")
	@ResponseBody
	public Map<String, Object> delDataStaff(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			//参数不能为空验证
			String [] paramStr={"STAFF_ID:人员ID"};
			ResultBean checkBean=requiredCheck(paramStr,paramMap );
			if(!checkBean.getResultCode().equals(StateCode.SUCCESS.getStateCode())){
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, checkBean.getMessage());
				return resMap;
			}
			//自己的工号不能删除
			String seqId=StringUtil.valueOf(paramMap.get("STAFF_ID"));
			StaffBean staffBean = this.getSessionStaffBean(request);
			String loginStaffId=staffBean.getStaffID();
			if(loginStaffId.equals(seqId)){
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, "对不起，您不能删除自己的工号");
				return resMap;
			}
			//系统初始化人员1不能删除
			if("1".equals(seqId)){
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, "当前选中人员为【系统初始化工号】，不能删除");
				return resMap;
			}
			paramMap.put("LOGIN_STAFF_ID", loginStaffId);
			operationService.deleteDataStaff(paramMap);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	@RequestMapping(value = "/reset_staff_pwd.shtml")
	@ResponseBody
	public Map<String, Object> resetStaffPwd(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			//参数不能为空验证
			String [] paramStr={"STAFF_ID:人员ID"};
			ResultBean checkBean=requiredCheck(paramStr,paramMap );
			if(!checkBean.getResultCode().equals(StateCode.SUCCESS.getStateCode())){
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, checkBean.getMessage());
				return resMap;
			}
			
			StaffBean staffBean = this.getSessionStaffBean(request);
			paramMap.put("LOGIN_STAFF_ID", staffBean.getStaffID());
			//密码加密
			MD5 md5Obj = new MD5();
	    	String enPwd=md5Obj.enPasswd(StringUtil.valueOf(paramMap.get("STAFF_ID")),DEFAULT_STAFF_PWD);
	    	paramMap.put("STAFF_PASS", enPwd);
			operationService.resetStaffPwd(paramMap);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	@RequestMapping(value = "/toupdate_pwd.shtml")
	public ModelAndView toUpdatePwd(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = RT_PAGE_ROOT+"/toupdate_pwd";
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage, resMap);
	}
	@RequestMapping(value = "/update_pwd.shtml")
	@ResponseBody
	public Map<String, Object> updatePwd(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			//参数不能为空验证
			String [] paramStr={"OLD_PWD:旧密码","NEW_PWD:新密码","RE_NEW_PWD:重复新密码"};
			ResultBean checkBean=requiredCheck(paramStr,paramMap );
			if(!checkBean.getResultCode().equals(StateCode.SUCCESS.getStateCode())){
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, checkBean.getMessage());
				return resMap;
			}
			String oldPwd=StringUtil.valueOf(paramMap.get("OLD_PWD"));
			String newPwd=StringUtil.valueOf(paramMap.get("NEW_PWD"));
			String reNewPwd=StringUtil.valueOf(paramMap.get("RE_NEW_PWD"));
			if(!"".equals(newPwd)&&!newPwd.equals(reNewPwd)){
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, "您输入的新密码与重复新密码不一致，请重新输入");
				return resMap;
			}
			StaffBean staffBean = this.getSessionStaffBean(request);
			MD5 md5Obj = new MD5();
			String enPwd="";
			//验证密码是否成功
			String loginStaffId=staffBean.getStaffID();
			HashMap checkMap=new HashMap<String,Object>();
			checkMap.put("CUR_STAFF_ID", loginStaffId); 
			List dataList=operationService.queryDataList(checkMap);
        	if(dataList!=null&&dataList.size()>0){
	    	   enPwd=md5Obj.enPasswd(loginStaffId,oldPwd);
	    	   String dbPwd=StringUtil.valueOf( ((HashMap)dataList.get(0)).get("STAFF_PASS"));
	    	   if(!"".equals(enPwd)&&!enPwd.equals(dbPwd)){
				   resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				   resMap.put(this.JSON_RESULT_MESSAGE, "您输入的旧密码错误，请重新输入");
				   return resMap;
	    	   }
	        }
			//密码加密保存
			paramMap.put("LOGIN_STAFF_ID", loginStaffId);
			paramMap.put("STAFF_ID", loginStaffId);
	    	enPwd=md5Obj.enPasswd(loginStaffId,newPwd);
	    	paramMap.put("STAFF_PASS", enPwd);
			operationService.resetStaffPwd(paramMap);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	@RequestMapping(value = "/toRoleMng_index.shtml")
	public ModelAndView toRoleMngIndex(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = RT_PAGE_ROOT+"/roleMng";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			resMap.put("CHECK_STAFF_ID",StringUtil.valueOf(paramMap.get("CHECK_STAFF_ID")));
			List  beanList = operationService.queryDataList((HashMap)resMap);
			if(beanList.size()>0){//当前选中角色存在
			  resMap.put("CHECK_STAFF_ID",((HashMap)beanList.get(0)).get("STAFF_ID"));
			  resMap.put("STAFF_NAME",((HashMap)beanList.get(0)).get("STAFF_NAME") );
			  resMap.put("STAFF_CODE",((HashMap)beanList.get(0)).get("STAFF_CODE") );
			}
			resMap.put("ROLE_TYPE_DATA", statusService.querytStatusList("SYS_ROLE_INFO","ROLE_TYPE"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ModelAndView(rtPage,resMap);
	}
	/**
	 * 所有角色权限
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/toRoleMng.shtml")
	@ResponseBody
	public Map<String, Object> toMenuMng(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = RT_PAGE_ROOT+"/roleMng";// 
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		List<?> dataList = null;
		int totalCount =0;
		try {
			//参数不能为空验证
			String [] paramStr={"CHECK_STAFF_ID:人员选中"};
			ResultBean checkBean=requiredCheck(paramStr,paramMap );
			if(!checkBean.getResultCode().equals(StateCode.SUCCESS.getStateCode())){
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, checkBean.getMessage());
				return resMap;
			}
			  paramMap=this.setPageParameters(request,paramMap);
			  StaffBean staffBean = this.getSessionStaffBean(request);
			  paramMap.put("STAFF_ID", staffBean.getStaffID());
				/*System.out.println(request.getAttribute("isFirst"));
				System.out.println(request.getAttribute("totalCount"));
				String isFirst=StringUtil.valueOf(request.getParameter("isFirst"));
	            if(!"0".equals(isFirst)){
	            	totalCount = roleService.queryDataCount(paramMap);
	    			this.initPage(request,totalCount);
	            }*/
            	totalCount = roleService.queryDataCount(paramMap);
				dataList = roleService.queryDataList(paramMap);				
			} catch (Exception e) {
				e.printStackTrace();
				dataList = new ArrayList<Map<String, Object>>();
				totalCount = 0;
			}
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
		
		
		return resMap;
	
	}
	@RequestMapping(value = "/saveRoles.shtml")
	@ResponseBody
	public Map<String, Object>  saveRoles(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			//参数不能为空验证
			String [] paramStr={"CHECK_STAFF_ID:人员选中","ROLE_IDS:角色列表选中"};
			ResultBean checkBean=requiredCheck(paramStr,paramMap );
			if(!checkBean.getResultCode().equals(StateCode.SUCCESS.getStateCode())){
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, checkBean.getMessage());
				return resMap;
			}
			//自己的工号不能删除
			String seqId=StringUtil.valueOf(paramMap.get("CHECK_STAFF_ID"));
			StaffBean staffBean = this.getSessionStaffBean(request);
			String loginStaffId=staffBean.getStaffID();
			if(loginStaffId.equals(seqId)){
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, "对不起，您不能操作自己的工号权限");
				return resMap;
			}
			//系统初始化人员1不能删除
			if("1".equals(seqId)){
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, "当前选中人员为【系统初始化工号】，不能操作");
				return resMap;
			}
			paramMap.put("LOGIN_STAFF_ID", loginStaffId);
			operationService.insertStaffRoles(paramMap);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	@RequestMapping(value = "/logupdate_pwd.shtml")
	public ModelAndView logupdate_pwd(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = RT_PAGE_ROOT+"/logupdate_pwd";
		Map<String, Object> resMap = new HashMap<String, Object>();
    	resMap.put("STAFF_ID",paramMap.get("STAFF_ID"));
		return new ModelAndView(rtPage, resMap);
	}
	@RequestMapping(value = "/logsaveupdate_pwd.shtml")
	@ResponseBody
	public Map<String, Object> logsaveupdate_pwd(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			//参数不能为空验证
			String [] paramStr={"OLD_PWD:旧密码","NEW_PWD:新密码","RE_NEW_PWD:重复新密码"};
			ResultBean checkBean=requiredCheck(paramStr,paramMap );
			if(!checkBean.getResultCode().equals(StateCode.SUCCESS.getStateCode())){
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, checkBean.getMessage());
				return resMap;
			}
			String oldPwd=StringUtil.valueOf(paramMap.get("OLD_PWD"));
			String newPwd=StringUtil.valueOf(paramMap.get("NEW_PWD"));
			String reNewPwd=StringUtil.valueOf(paramMap.get("RE_NEW_PWD"));
			if(!"".equals(newPwd)&&!newPwd.equals(reNewPwd)){
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, "您输入的新密码与重复新密码不一致，请重新输入");
				return resMap;
			}
			MD5 md5Obj = new MD5();
			String enPwd="";
			//验证密码是否成功
			String loginStaffId=paramMap.get("STAFF_ID").toString();
			HashMap checkMap=new HashMap<String,Object>();
			checkMap.put("CUR_STAFF_ID", loginStaffId); 
			List dataList=operationService.queryDataList(checkMap);
        	if(dataList!=null&&dataList.size()>0){
	    	   enPwd=md5Obj.enPasswd(loginStaffId,oldPwd);
	    	   String dbPwd=StringUtil.valueOf( ((HashMap)dataList.get(0)).get("STAFF_PASS"));
	    	   if(!"".equals(enPwd)&&!enPwd.equals(dbPwd)){
				   resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				   resMap.put(this.JSON_RESULT_MESSAGE, "您输入的旧密码错误，请重新输入");
				   return resMap;
	    	   }
	        }
			//密码加密保存
			paramMap.put("LOGIN_STAFF_ID", loginStaffId);
			paramMap.put("STAFF_ID", loginStaffId);
	    	enPwd=md5Obj.enPasswd(loginStaffId,newPwd);
	    	paramMap.put("STAFF_PASS", enPwd);
			operationService.resetStaffPwd(paramMap);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}

	/**
	 * 
	 * @Title updateStaffManagement   
	 * @Description TODO(用户管理)   
	 * @author pengl
	 * @date 2018年3月27日 下午2:11:29
	 */
	@ResponseBody
	@RequestMapping("/updateStaffManagement.shtml")
	public Map<String, Object> updateStaffManagement(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String returnCode = "200";
		String returnMsg = "操作成功！";
		try {
			if(!StringUtil.isEmpty(request.getParameter("staffId"))) {
				SysStaffInfo sysStaffInfo = new SysStaffInfo();
				sysStaffInfo.setStaffId(Integer.parseInt(request.getParameter("staffId")));
				sysStaffInfo.setIsManagement(request.getParameter("isManagement"));
				sysStaffInfoService.updateByPrimaryKeySelective(sysStaffInfo);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnCode = "9999";
			returnMsg = "系统错误！";
		}
		map.put("returnCode", returnCode);	
		map.put("returnMsg", returnMsg);
		return map;
	}
	
	/*用户管理负责类目设置*/
	@RequestMapping(value = "/addsysStaffProductType.shtml")
	public ModelAndView activityBrandGroup(HttpServletRequest request) {
		String rtPage = "/orgMng/addsysstaffproducttype";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("staffid", request.getParameter("staffid"));
		ProductTypeExample productTypeExample = new ProductTypeExample();
		productTypeExample.createCriteria().andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		List<ProductType> producttypeList = productTypeService.selectByExample(productTypeExample);	
		resMap.put("producttypeList", producttypeList);
	
		SysStaffProductTypeExample sysStaffProductTypeExample=new SysStaffProductTypeExample();
		SysStaffProductTypeExample.Criteria criteria=sysStaffProductTypeExample.createCriteria();
		criteria.andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.valueOf(request.getParameter("staffid")));
		List<SysStaffProductType> sysStaffProductTypes=sysStaffproductTypeService.selectByExample(sysStaffProductTypeExample);
		List<Integer> producttypeIds=new ArrayList<Integer>();
	    for (SysStaffProductType sysStaffProductType : sysStaffProductTypes) {    	
	    	producttypeIds.add(sysStaffProductType.getProductTypeId());
		}
		resMap.put("producttypeId",producttypeIds);
		return new ModelAndView(rtPage,resMap);
	}
	
	//设置添加类目
	@ResponseBody
	@RequestMapping("/addsysstaffProductTypes.shtml")
	public ModelAndView saveSeckillBrandGroup(HttpServletRequest request,String staffId,String productTypeId) {
		String rtPage = "/success/success";
		Map<String, Object> resMap=new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			Date date = new Date();
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());			
			SysStaffProductTypeExample example = new SysStaffProductTypeExample();
			SysStaffProductTypeExample.Criteria c = example.createCriteria();
			c.andStaffIdEqualTo(Integer.parseInt(staffId));
			SysStaffProductType sysStaffProductType = new SysStaffProductType();
			sysStaffProductType.setDelFlag("1");
			sysStaffproductTypeService.updateByExampleSelective(sysStaffProductType, example); 
			
			if(!StringUtil.isEmpty(productTypeId)) {
				String[] strs=productTypeId.split(",");
				for (String productTypeids : strs) {
					SysStaffProductType sysstaffProductType=new SysStaffProductType();
					sysstaffProductType.setProductTypeId(Integer.parseInt(productTypeids));
					sysstaffProductType.setStaffId(Integer.parseInt(staffId));
					sysstaffProductType.setCreateBy(staffID);
					sysstaffProductType.setCreateDate(date);
					sysstaffProductType.setDelFlag("0");
					sysStaffproductTypeService.insert(sysstaffProductType);
				}		
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap); 
	}

	//取消负责类目
	@ResponseBody
	@RequestMapping(value = "/upDatestaffProductTypeId")
	public Map<String, Object> saledel(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = "200";
		String msg = "操作成功！";
		try {
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer staffproducttypeid=Integer.valueOf(request.getParameter("staffproducttypeid"));
			SysStaffProductType sysStaffProductType=sysStaffproductTypeService.selectByPrimaryKey(staffproducttypeid);
			sysStaffProductType.setUpdateBy(staffId);
			sysStaffProductType.setUpdateDate(new Date());
			sysStaffProductType.setDelFlag("1");
			sysStaffproductTypeService.updateByPrimaryKeySelective(sysStaffProductType);
		}catch (Exception e) {
			e.printStackTrace();
			code = "9999";
			msg = "操作失败！";
		}
		resMap.put("code", code);
		resMap.put("msg", msg);
		return resMap;
	}
	
	//用户管理可查看商家对接人类型设置
	@RequestMapping(value = "/addstaffmchtcontactpermission.shtml")
	public ModelAndView addstaffmchtcontactpermission(HttpServletRequest request) {
		String rtPage = "/orgMng/addstaffmchtcontactpermission";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("staffid", request.getParameter("staffid"));
		StaffMchtcontactPermissionExample staffmchtcontactPermissionExample=new StaffMchtcontactPermissionExample();
		staffmchtcontactPermissionExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.valueOf(request.getParameter("staffid")));
		List<StaffMchtcontactPermission> staffMchtcontactPermissions=staffmchtcontactpermissionService.selectByExample(staffmchtcontactPermissionExample);
		
		List<String> mchtContactTypeList=new ArrayList<String>();
		for (StaffMchtcontactPermission staffmchtcontactpermissions : staffMchtcontactPermissions) {
			mchtContactTypeList.add(staffmchtcontactpermissions.getMchtContactType());
			
		}   
		resMap.put("mchtContactTypeList",mchtContactTypeList);
		return new ModelAndView(rtPage,resMap);
	}
	//设置商家联系人类型
	@ResponseBody
	@RequestMapping("/addStaffmchtcontactpermission.shtml")
	public ModelAndView addstaffmchtcontactpermission(HttpServletRequest request,String staffId,String mchtContactType) {
		String rtPage = "/success/success";
		Map<String, Object> resMap=new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			Date date = new Date();
			StaffMchtcontactPermissionExample staffMchtcontactPermissionexample = new StaffMchtcontactPermissionExample();
			StaffMchtcontactPermissionExample.Criteria criteria = staffMchtcontactPermissionexample.createCriteria();
			criteria.andStaffIdEqualTo(Integer.parseInt(staffId));
			StaffMchtcontactPermission StaffMchtcontactPermission=new StaffMchtcontactPermission();
			StaffMchtcontactPermission.setDelFlag("1");
			staffmchtcontactpermissionService.updateByExampleSelective(StaffMchtcontactPermission, staffMchtcontactPermissionexample); 
			
			if(!StringUtil.isEmpty(mchtContactType)) {
				String[] strs=mchtContactType.split(",");
				for (String mchtContactTypes1 : strs) {
					StaffMchtcontactPermission staffMchtcontactPermission=new StaffMchtcontactPermission();
					staffMchtcontactPermission.setMchtContactType(mchtContactTypes1);
					staffMchtcontactPermission.setStaffId(Integer.parseInt(staffId));
					staffMchtcontactPermission.setCreateBy(staffID);
					staffMchtcontactPermission.setCreateDate(date);
					staffMchtcontactPermission.setDelFlag("0");
					staffmchtcontactpermissionService.insert(staffMchtcontactPermission);
				}		
			}
			
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap); 
	}
	
	/**
	 * 
	 * @Title updateStaffManager   
	 * @Description TODO(设置管理层及下属人员)   
	 * @author pengl
	 * @date 2018年10月16日 下午5:38:12
	 */
	@RequestMapping(value = "/updateStaffManager.shtml")
	public ModelAndView updateStaffManager(HttpServletRequest request) {
		String rtPage = "/orgMng/updateStaff";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String staffId = request.getParameter("staffId");
		if(!StringUtil.isEmpty(staffId)) {
			SysStaffInfoExample sysStaffInfoExample = new SysStaffInfoExample();
			sysStaffInfoExample.createCriteria().andStaffIdEqualTo(Integer.parseInt(staffId));
			sysStaffInfoExample.setLimitSize(1);
			List<SysStaffInfo> sysStaffInfoList = sysStaffInfoService.selectByExample(sysStaffInfoExample);
			if(sysStaffInfoList != null && sysStaffInfoList.size() > 0) {
				resMap.put("sysStaffInfo", sysStaffInfoList.get(0));
			}
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 
	 * @Title getSubordinateStaffIds   
	 * @Description TODO(验证下属人员)   
	 * @author pengl
	 * @date 2018年10月16日 下午6:27:10
	 */
	@ResponseBody
	@RequestMapping("/getSubordinateStaffIds.shtml")
	public Map<String, Object> getSubordinateStaffIds(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		String status = "";
		String staffIds = "";
		try {
			String subordinateStaffIds = request.getParameter("subordinateStaffIds");
			if(!StringUtil.isEmpty(subordinateStaffIds)) {
				List<Integer> subordinateStaffIdList = new ArrayList<Integer>();
				String[] strs = subordinateStaffIds.split(",");
				for(String subordinateStaffId : strs) {
					subordinateStaffIdList.add(Integer.parseInt(subordinateStaffId));
				}
				SysStaffInfoExample sysStaffInfoExample = new SysStaffInfoExample();
				sysStaffInfoExample.createCriteria().andStaffIdIn(subordinateStaffIdList);
				List<SysStaffInfo> sysStaffInfoList = sysStaffInfoService.selectByExample(sysStaffInfoExample);
				if(sysStaffInfoList != null && sysStaffInfoList.size() > 0) {
					for(SysStaffInfo sysStaffInfo : sysStaffInfoList) {
						if(!subordinateStaffIdList.contains(sysStaffInfo.getStaffId())) {
							status = "1";
							if(StringUtil.isEmpty(staffIds)) {
								staffIds = sysStaffInfo.getStaffId().toString();
							}else {
								staffIds += "," +sysStaffInfo.getStaffId();
							}
						}
					}
				}else {
					status = "1";
					staffIds = subordinateStaffIds;
				}
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put("status", status);
		resMap.put("staffIds", staffIds);
		resMap.put("code", code);
		resMap.put("msg", msg);
		return resMap; 
	}
	
	
	/**
	 * 
	 * @Title updateStaff   
	 * @Description TODO(设置管理层及下属人员)   
	 * @author pengl
	 * @date 2018年10月16日 下午6:27:10
	 */
	@ResponseBody
	@RequestMapping("/updateStaff.shtml")
	public ModelAndView updateStaff(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			String staffId = request.getParameter("staffId");
			String isManagement = request.getParameter("isManagement");
			String canViewQualification=request.getParameter("canViewQualification");
			String subordinateStaffIds = request.getParameter("subordinateStaffIds");
			SysStaffInfo sysStaffInfo = new SysStaffInfo();
			sysStaffInfo.setStaffId(Integer.parseInt(staffId));
			sysStaffInfo.setIsManagement(isManagement);
			sysStaffInfo.setCanViewQualification(canViewQualification);
			sysStaffInfo.setSubordinateStaffIds(subordinateStaffIds);
			sysStaffInfoService.updateByPrimaryKeySelective(sysStaffInfo);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap); 
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/menuJurisdiction.shtml")
	public Map<String, Object> menuJurisdictionlist(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		List<String> dataList=null;
		try {
			if(!StringUtil.isEmpty(request.getParameter("staffID"))) {
				paramMap.put("staff_ID", request.getParameter("staffID"));
				String isManagement = this.getSessionStaffBean(request).getIsManagement();//管理层
				paramMap.put("isManagement", isManagement);
			    dataList=operationService.selectmenuList(paramMap);	
			    for (int i = 0; i < dataList.size(); i++) {															
					if (dataList.get(i).equals("307")) {
						map.put("Menu_307", dataList.get(i));
					}
					if (dataList.get(i).equals("422")) {
						map.put("Menu_422", dataList.get(i));
					}
					if (dataList.get(i).equals("423")) {
						map.put("Menu_423", dataList.get(i));
					}
					if (dataList.get(i).equals("308")) {
						map.put("Menu_308", dataList.get(i));
					}							
					if (dataList.get(i).equals("434")) {
						map.put("Menu_434", dataList.get(i));
					}
					if (dataList.get(i).equals("311")) {
						map.put("Menu_311", dataList.get(i));
					}
					if (dataList.get(i).equals("261")) {
						map.put("Menu_261", dataList.get(i));
					}
					if (dataList.get(i).equals("312")) {
						map.put("Menu_312", dataList.get(i));
					}
					if (dataList.get(i).equals("314")) {
						map.put("Menu_314", dataList.get(i));
					}
					if (dataList.get(i).equals("435")) {
						map.put("Menu_435", dataList.get(i));
					}					
					if (dataList.get(i).equals("313")) {
						map.put("Menu_313", dataList.get(i));					
					}
					if (dataList.get(i).equals("551")) {
						map.put("Menu_551", dataList.get(i));
					}
					if (dataList.get(i).equals("552")) {
						map.put("Menu_552", dataList.get(i));
					}
					if (dataList.get(i).equals("534")) {
						map.put("Menu_534", dataList.get(i));
					}
					if (dataList.get(i).equals("535")) {
						map.put("Menu_535", dataList.get(i));
					}
				}
    
			    List<Map<String, Object>> mapList=operationService.selectmenucout(paramMap);
			    for (Map<String, Object> map2 : mapList) {
						 map.put("Menu422_conut", map2.get("Menu422_conut"));
				    	 map.put("Menu423_conut", map2.get("Menu423_conut"));
				    	 map.put("Menu308_count", map2.get("Menu308_count"));
				    	 map.put("Menu311_count", map2.get("Menu311_count"));
				    	 map.put("Menu261_count", map2.get("Menu261_count"));
				         map.put("Menu435_count", map2.get("Menu435_count"));
				         map.put("Menu312_count", map2.get("Menu312_count"));
				         map.put("Menu314_count", map2.get("Menu314_count"));
				         map.put("Menu434_count", map2.get("Menu434_count"));
				         map.put("Menu313_count", map2.get("Menu313_count"));
				         map.put("Menu551_count", map2.get("Menu551_count"));
				         map.put("Menu552_count", map2.get("Menu552_count"));
				         map.put("Menu534_count", map2.get("Menu534_count"));
				         map.put("Menu535_count", map2.get("Menu535_count"));
				         map.put("Menu307_count", map2.get("Menu307_count"));
				 }
			   
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/menuJurisdictionStatistics.shtml")
	public Map<String, Object> menuJurisdictionStatistics(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		List<String> dataList=null;
		try {
			if(!StringUtil.isEmpty(request.getParameter("staffID"))) {
				paramMap.put("staff_ID", request.getParameter("staffID"));
			    dataList=operationService.selectmenuList(paramMap);	
			    for (int i = 0; i < dataList.size(); i++) {															
					 if (dataList.get(i).equals("426")) {
						 map.put("Menu_426", dataList.get(i));
					 }
					 if (dataList.get(i).equals("443")) {
						 map.put("Menu_443", dataList.get(i));
					 }
					 if (dataList.get(i).equals("430")) {
						 map.put("Menu_430", dataList.get(i));
					 }
					 if (dataList.get(i).equals("212")) {
						 map.put("Menu_212", dataList.get(i));
					 }
				 
			    }
    
			    List<Map<String, Object>> mapmenuList=operationService.selectmenucout1(paramMap);
			    for (Map<String, Object> map2 : mapmenuList) {
					 map.put("Menu426_conut", map2.get("Menu426_conut"));
					 map.put("Menu443_conut", map2.get("Menu443_conut"));
					 map.put("Menu430_conut", map2.get("Menu430_conut"));
					 map.put("Menu212_conut", map2.get("Menu212_conut"));
				}
			   
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/menuShopJurisdiction.shtml")
	public Map<String, Object> menuShopJurisdiction(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		List<String> dataList=null;
		try {
			if(!StringUtil.isEmpty(request.getParameter("staffID"))) {
				paramMap.put("staff_ID", request.getParameter("staffID"));
				String isManagement = this.getSessionStaffBean(request).getIsManagement();//管理层
				paramMap.put("isManagement", isManagement);
				List<String> archivestatuslist=new ArrayList<String>();
				archivestatuslist.add("0");
				archivestatuslist.add("1");
				paramMap.put("archivestatuslist", archivestatuslist);
			    dataList=operationService.selectmenuList(paramMap);	
			    for (int i = 0; i < dataList.size(); i++) {															
					 if (dataList.get(i).equals("325")) {
						 map.put("Menu_325", dataList.get(i));
					 }
					 if (dataList.get(i).equals("328")) {
						 map.put("Menu_328", dataList.get(i));
					 }
					 if (dataList.get(i).equals("326")) {
						 map.put("Menu_326", dataList.get(i));
					 }
					 if (dataList.get(i).equals("520")) {
						 map.put("Menu_520", dataList.get(i));
					 }
					 if (dataList.get(i).equals("522")) {
						 map.put("Menu_522", dataList.get(i));
					 }
					 if (dataList.get(i).equals("324")) {
						 map.put("Menu_324", dataList.get(i));
					 }
					 if (dataList.get(i).equals("523")) {
						 map.put("Menu_523", dataList.get(i));
					 }
					 if (dataList.get(i).equals("524")) {
						 map.put("Menu_524", dataList.get(i));
					 }
					 if (dataList.get(i).equals("537")) {
						 map.put("Menu_537", dataList.get(i));
					 }
					 if (dataList.get(i).equals("538")) {
						 map.put("Menu_538", dataList.get(i));
					 }
					 if (dataList.get(i).equals("539")) {
						 map.put("Menu_539", dataList.get(i));
					 }
					 if (dataList.get(i).equals("540")) {
						 map.put("Menu_540", dataList.get(i));
					 }
					 if (dataList.get(i).equals("541")) {
						 map.put("Menu_541", dataList.get(i));
					 }
					 if (dataList.get(i).equals("542")) {
						 map.put("Menu_542", dataList.get(i));
					 }
					 if (dataList.get(i).equals("543")) {
						 map.put("Menu_543", dataList.get(i));
					 }
					 if (dataList.get(i).equals("544")) {
						 map.put("Menu_544", dataList.get(i));
					 }
					 if (dataList.get(i).equals("545")) {
						 map.put("Menu_545", dataList.get(i));
					 }
					 if (dataList.get(i).equals("546")) {
						 map.put("Menu_546", dataList.get(i));
					 }
				 
			    }
    
			    List<Map<String, Object>> mapmenuList=operationService.selectmenucout2(paramMap);
			    for (Map<String, Object> map2 : mapmenuList) {
					 map.put("Menu325_conut", map2.get("Menu325_conut"));
					 map.put("Menu328_conut", map2.get("Menu328_conut"));
					 map.put("Menu537_conut", map2.get("Menu537_conut"));
					 map.put("Menu538_conut", map2.get("Menu538_conut"));
					 map.put("Menu539_conut", map2.get("Menu539_conut"));
					 map.put("Menu540_conut", map2.get("Menu540_conut"));
					 map.put("Menu541_conut", map2.get("Menu541_conut"));
					 map.put("Menu542_conut", map2.get("Menu542_conut"));
					 map.put("Menu543_conut", map2.get("Menu543_conut"));
					 map.put("Menu544_conut", map2.get("Menu544_conut"));
					 map.put("Menu545_conut", map2.get("Menu545_conut"));
					 map.put("Menu546_conut", map2.get("Menu546_conut"));
					 map.put("Menu326_conut", map2.get("Menu326_conut"));
					 map.put("Menu520_conut", map2.get("Menu520_conut"));
					 map.put("Menu522_conut", map2.get("Menu522_conut"));
					 map.put("Menu324_conut", map2.get("Menu324_conut"));
					 map.put("Menu523_conut", map2.get("Menu523_conut"));
					 map.put("Menu524_conut", map2.get("Menu524_conut"));
				}
			   
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	
	//获取原负责人名字和新人的电话
	@RequestMapping(value = "/getdutyStaffName.shtml")
	@ResponseBody
	public Map<String, Object> getdutyStaffName(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		List<String> dataList=null;
		try {
			String yuanId = request.getParameter("yuanId");//原id
			String staffName = request.getParameter("staffName");//原负责人
			String staffId = request.getParameter("staffId");//新id
			
			if(StringUtil.isEmpty(yuanId)){
				map.put("staffName", staffName);
			}else{
				SysStaffInfo staffInfo = sysStaffInfoService.selectByPrimaryKey(Integer.parseInt(yuanId));
				map.put("staffName", staffInfo.getStaffName());
			}
			
			if(!StringUtil.isEmpty(staffId)){
				SysStaffInfo staffInfo = sysStaffInfoService.selectByPrimaryKey(Integer.parseInt(staffId));
				if(staffInfo!=null){
                    map.put("mobilePhone", staffInfo.getMobilePhone());
                }else{
                    code = "9999";
                    msg = "请输入正确的负责人ID！";
                }

			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	 //用户查询权限菜单
	@RequestMapping(value = "/menuPermissions.shtml")
	public ModelAndView menuPermissions(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/orgMng/menuPermissions";
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			  StaffBean staffBean = this.getSessionStaffBean(request);
              String staffid=request.getParameter("staffid");
              SysStaffRoleExample sysStaffRoleExample=new SysStaffRoleExample();
              sysStaffRoleExample.createCriteria().andStaffIdEqualTo(Integer.valueOf(staffid)).andStatusEqualTo("A");
              List<SysStaffRole> sysStaffRoles=sysStaffRoleService.selectByExample(sysStaffRoleExample);
              List<Integer> getRoleIdList=new ArrayList<>();
              if (sysStaffRoles.size()>0) {
				  for (SysStaffRole sysStaffRole : sysStaffRoles) {
					  getRoleIdList.add(sysStaffRole.getRoleId());
				}
			}
              if (getRoleIdList.size()>0) {
            	  paramMap.put("getRoleIdList", getRoleIdList);
            	  paramMap.put("STAFF_ID", staffBean.getStaffID());
            	  ObjectMapper objectMapper = new ObjectMapper();
            	  resMap.put("TREE_DATA_JSON", objectMapper.writeValueAsString(roleService.getRoleIdMenusList(paramMap)));
			} else {
				String rtPage1 = "/orgMng/menuPermissionsTips";
				return new ModelAndView(rtPage1, resMap);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage, resMap);
	
	}
	
	
}
