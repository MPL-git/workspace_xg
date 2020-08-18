package com.jf.controller;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gzs.common.beans.Menu;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.entity.ResultBean;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.SysRoleInfo;
import com.jf.entity.SysStaffRoleCustom;
import com.jf.entity.SysStaffRoleCustomExample;
import com.jf.service.RoleMngService;
import com.jf.service.SysRoleInfoService;
import com.jf.service.SysStaffRoleService;
import com.jf.service.WebcommonService;

/**
 * 角色管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/roleMng")
public class RoleMngController extends BaseController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private RoleMngService operationService;

	@Resource
	private WebcommonService webcommonService;
	
	@Resource
	private SysStaffRoleService sysStaffRoleService;
	
	@Resource
	private SysRoleInfoService sysRoleInfoService;


	/**
	 * 模块入口
	 * @param request
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/index.shtml")
	public ModelAndView index(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/roleMng/index";// 重定向
		Map<String,Object> resMap = new HashMap<String,Object>();
		resMap.put("getroletypeNameList", operationService.getroletypeNameList());//获取角色类型集合
		return new ModelAndView(rtPage,resMap);
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/datalist.shtml") 
	@ResponseBody 
	public Map<String, Object> datalist(HttpServletRequest request,HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {	
		 Map<String,Object> resMap = new HashMap<String,Object>(); 
		List<?> dataList = null;
		Integer totalCount =0;
		try {
			List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
			paramMap=this.setPageParametersLiger(request,paramMap);   
        	totalCount = operationService.queryDataCount(paramMap);
        	dataList = operationService.queryDataList(paramMap);
        	
        	if(dataList != null && dataList.size() > 0) {
        		for(int i=0; i<dataList.size(); i++ ) {
        			Map<String, Object> dataMap = (Map<String, Object>) dataList.get(i);
        			Map<String, Object> map = new HashMap<String, Object>();
        			map.put("dataMap", dataMap);
        			if(dataMap.get("ROLE_ID") != null && !"".equals(dataMap.get("ROLE_ID").toString())) {
        				SysStaffRoleCustomExample sysStaffRoleCustomExample = new SysStaffRoleCustomExample();
        				sysStaffRoleCustomExample.createCriteria().andStatusEqualTo("A").andRoleIdEqualTo((Integer)dataMap.get("ROLE_ID"));
        				List<SysStaffRoleCustom> sysStaffRoleCustomList = sysStaffRoleService.selectByCustomExample(sysStaffRoleCustomExample);
        				StringBuffer stringBuf = new StringBuffer();
        				for(SysStaffRoleCustom sysStaffRoleCustom : sysStaffRoleCustomList) {
        					if(sysStaffRoleCustom.getStaffName() != null && !"".equals(sysStaffRoleCustom.getStaffName())) {
        						if(stringBuf.length() == 0) {
        							stringBuf.append(sysStaffRoleCustom.getStaffName());
        						}else {
        							stringBuf.append( "，" + sysStaffRoleCustom.getStaffName());
        						}
        					}
        				}
        				map.put("staffNames", stringBuf.toString());
        				map.put("ROLE_ID", dataMap.get("ROLE_ID"));
        			}
        			listMap.add(map);
        		}
        	}
			resMap.put("Rows", listMap);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
			totalCount = 0;
		}
    	return resMap; 
	} 
	@RequestMapping(value = "/toadd.shtml")
	public ModelAndView toadd(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/roleMng/toadd";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
	        String roleId=StringUtil.valueOf(paramMap.get("ROLE_ID"));
	        if(!"".equals(roleId)){
	        	paramMap.put("CUR_ROLE_ID", roleId);
				/*StaffBean staffBean = this.getSessionStaffBean(request);
				paramMap.put("STAFF_ID", staffBean.getStaffID());*/
	        	List<?> dataList=operationService.queryDataList(paramMap);
	        	if(dataList!=null&&dataList.size()>0){
	        		HashMap<String,Object> tmpMap = (HashMap<String,Object>)dataList.get(0);
					resMap.putAll(tmpMap);
	        	}
			}
	        paramMap.put("TABLE_NAME","SYS_ROLE_INFO"); 
			paramMap.put("COL_NAME","ROLE_TYPE");
	    	List<?> ROLETYPElIST = webcommonService.sysStatusList(paramMap);
	    	resMap.put("ROLETYPElIST", ROLETYPElIST);
	    	List<?> parentRoleList = operationService.queryDataList(new HashMap<String, Object>());
        	resMap.put("parentRoleList", parentRoleList);
		} catch (Exception e) {
		}
		return new ModelAndView(rtPage, resMap);
	}
	@RequestMapping(value = "/save.shtml") 
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String rtPage = "/success/success";
		String msg = "";
		List<?> dataList=null;
		try {
			//参数不能为空验证
			String [] paramStr={"ROLE_NAME:角色名称","ROLE_TYPE:角色类型"};
			ResultBean checkBean=requiredCheck(paramStr,paramMap );
			if(!checkBean.getResultCode().equals(StateCode.SUCCESS.getStateCode())){
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, checkBean.getMessage());
				//return new ModelAndView(rtPage,resMap);
				 return new ModelAndView(rtPage,resMap); 
			}
			String roleId=StringUtil.valueOf(paramMap.get("ROLE_ID"));
			StaffBean staffBean = this.getSessionStaffBean(request);
			paramMap.put("STAFF_ID", staffBean.getStaffID());
			HashMap<String,Object> checkMap=new HashMap<String,Object>();
			checkMap.put("CHECK_ROLE_NAME", StringUtil.valueOf(paramMap.get("ROLE_NAME")));
			if(StringUtil.isEmpty(paramMap.get("PARENT_ID").toString())) {
				paramMap.put("PARENT_ID", null);
			}
			boolean saveFlag=true;
			if("".equals(roleId)){
				//验证唯一
	        	dataList=operationService.queryDataList(checkMap);
	        	if(dataList!=null&&dataList.size()>0){
	   			   code = StateCode.JSON_AJAX_ERROR.getStateCode();
				   msg ="该角色在系统中已存在，不能重复添加";
	        	   saveFlag=false;
	        	}
	        	if(saveFlag){
	        		operationService.saveData(paramMap);
	        	}
			}else{
				//验证唯一
				checkMap.put("CHECK_ROLE_ID", roleId);
				dataList=operationService.queryDataList(checkMap);
	        	if(dataList!=null&&dataList.size()>0){
		   			   code = StateCode.JSON_AJAX_ERROR.getStateCode();
					   msg ="该角色在系统中已存在，不能重复添加";
		        	   saveFlag=false;
		        	}
		        if(saveFlag){
		        	operationService.updateData(paramMap);
		        }
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
		 return new ModelAndView(rtPage,resMap);  
	}
	
	@RequestMapping(value = "/delData.shtml")
	@ResponseBody
	public Map<String, Object> delData(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			resMap.put("ROLE_NAME", StringUtil.valueOf(paramMap.get("ROLE_NAME")));
			//参数不能为空验证
			String [] paramStr={"ROLE_ID:角色ID"};
			ResultBean checkBean=requiredCheck(paramStr,paramMap );
			if(!checkBean.getResultCode().equals(StateCode.SUCCESS.getStateCode())){
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, checkBean.getMessage());
				return resMap;
			}
			//自己的角色不能删除
			String seqId=StringUtil.valueOf(paramMap.get("ROLE_ID"));
			StaffBean staffBean = this.getSessionStaffBean(request);
			String loginRoleId=staffBean.getRoleId();
			if(loginRoleId.equals(seqId)){
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, "这是您当前使用的登录角色，不能删除");
				return resMap;
			}
			//系统角色1不能删除
			if("1".equals(seqId)){
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, "当前选中角色为【系统初始化角色】，不能删除");
				return resMap;
			}
			resMap.put("CUR_ROLE_ID",seqId);
			List roleList = operationService.queryDataList((HashMap)resMap);
			if(roleList !=null &&roleList.size()>0){
			  String roleType=StringUtil.valueOf(((HashMap)roleList.get(0)).get("ROLE_TYPE"));
			  if("1".equals(roleType)){
				  resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
					resMap.put(this.JSON_RESULT_MESSAGE,"系统角色不能删除");
					return resMap; 
			  }
			}
			paramMap.put("STAFF_ID", staffBean.getStaffID());
			operationService.deleteData(paramMap);
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
	 * 所有菜单权限
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/toMenuMng.shtml")
	public ModelAndView toMenuMng(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {

		String rtPage = "/roleMng/menuMng";// 
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			//参数不能为空验证
			String [] paramStr = {"CHECK_ROLE_ID:角色ID"};
			ResultBean checkBean=requiredCheck(paramStr,paramMap );
			if(!checkBean.getResultCode().equals(StateCode.SUCCESS.getStateCode())){//如果验证返回不成功,把错误信息return
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, checkBean.getMessage());
				return new ModelAndView(rtPage, resMap);
			}
            String seqId=StringUtil.valueOf(paramMap.get("CHECK_ROLE_ID"));/*转换成字符串*/
			resMap.put("CUR_ROLE_ID",seqId);
			List roleList = operationService.queryDataList((HashMap)resMap);//查询角色
			if(roleList.size()>0){//当前选中角色存在,把信息放入Map
			  resMap.put("CHECK_ROLE_ID",((HashMap)roleList.get(0)).get("ROLE_ID"));
			  resMap.put("CHECK_ROLE_NAME",((HashMap)roleList.get(0)).get("ROLE_NAME"));

			 //查询权限菜单
			  StaffBean staffBean = this.getSessionStaffBean(request);
			  SysRoleInfo sysRoleInfo = sysRoleInfoService.selectByPrimaryKey(Integer.parseInt(staffBean.getRoleId()));
			  if(sysRoleInfo != null) {
				  paramMap.put("ROLE_ID", sysRoleInfo.getRoleId());
				  paramMap.put("PARENT_ROLE_ID", ((Map<String, Object>)roleList.get(0)).get("PARENT_ID"));
			  }
			  paramMap.put("STAFF_ID", staffBean.getStaffID());
			  ObjectMapper objectMapper = new ObjectMapper();
			  resMap.put("TREE_DATA_JSON", objectMapper.writeValueAsString(operationService.queryStaffMenusList(paramMap)));
			  //resMap.put("MENU_TREE_HTML", Menu.buildJUITree( Menu.buildTree(operationService.queryStaffMenusList(paramMap), 0), request.getContextPath(),0,"treeCheck","expand"));
              if("1".equals(seqId)){
				resMap.put("SYS_FLAG", "1");
				resMap.put("SYS_FLAG_MSG", "温馨提示：当前选中角色为【系统初始化角色】，权限不能修改");
			  }
			  // List<Menu>  menulist=operationService.queryStaffMenusList(paramMap);
			 // resMap.put("MENU_TREE_HTML", Menu.buildJUITree( Menu.buildTree(menulist, 0), request.getContextPath(),0,"treeCheck","expand"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage, resMap);
	
	}
	
	/**
	 * 保存菜单权限
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/saveMenus.shtml")
	@ResponseBody
	public Map<String, Object>  saveMenus(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			//参数不能为空验证
		/*	String [] paramStr={"ROLE_ID:角色ID","CHECK_MENUS:菜单树选择"};
			ResultBean checkBean=requiredCheck(paramStr,paramMap );
			if(!checkBean.getResultCode().equals(StateCode.SUCCESS.getStateCode())){
				resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
				resMap.put(this.JSON_RESULT_MESSAGE, checkBean.getMessage());
				return resMap;
			}
			*/
			/*HashMap<String, Object> paramCheckedMap = getParamCheckedMap(request, response, paramMap);
			List<Menu> menus = operationService.queryCheckedStaffMenusList(paramCheckedMap);
			String check_menus = (String) paramMap.get("CHECK_MENUS");
			String[] split = check_menus.split(",");
			List<String> newMenus = Arrays.asList(split);
			ArrayList<Object> deleteMenus = new ArrayList<>();
			for (Menu menu : menus) {
				int menuID = menu.getMenuID();
				boolean isdelete = true;
				for (String s : newMenus) {
					if (menuID==Integer.parseInt(s)){
						isdelete=false;
						newMenus.remove(s);
					}
				}
				if (isdelete){
					deleteMenus.add(menuID);
				}
			}*/

			StaffBean staffBean = this.getSessionStaffBean(request);
			paramMap.put("STAFF_ID", staffBean.getStaffID());
			operationService.insertStaffMenus(paramMap);
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
	public HashMap<String, Object> getParamCheckedMap(HttpServletRequest request, HttpServletResponse response,HashMap<String, Object> paramMap) {
		HashMap<String, Object> paramCheckedMap = new HashMap<>();
		paramCheckedMap.put("CHECK_ROLE_ID",paramMap.get("ROLE_ID"));
		Map<String, Object> resMap = new HashMap<>();
		try {
			String seqId = StringUtil.valueOf(paramMap.get("ROLE_ID"));
			resMap.put("CUR_ROLE_ID", seqId);
			List roleList = operationService.queryDataList((HashMap) resMap);
			if (roleList.size() > 0) {//当前选中角色存在

				//查询权限菜单
				StaffBean staffBean = this.getSessionStaffBean(request);
				SysRoleInfo sysRoleInfo = sysRoleInfoService.selectByPrimaryKey(Integer.parseInt(staffBean.getRoleId()));
				if (sysRoleInfo != null) {
					paramCheckedMap.put("ROLE_ID", sysRoleInfo.getRoleId());
					paramCheckedMap.put("PARENT_ROLE_ID", ((Map<String, Object>) roleList.get(0)).get("PARENT_ID"));
				}
				paramCheckedMap.put("STAFF_ID", staffBean.getStaffID());
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return paramCheckedMap;
	}
}
