package com.jf.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.TreeUtils;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.service.ProdRecService;

/**
 * 首页产品推荐
 * 
 * @author 1
 * 
 */
@Controller
public class ProdRecController  extends BaseController{

	@Autowired
	private ProdRecService prodRecService;

	/**
	 * 产品推荐首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/prodRec/index.shtml")
	public ModelAndView index(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/prodRec/index";// 重定向
		Map<String,Object> resMap = new HashMap<String,Object>();
		
		return new ModelAndView(rtPage,resMap);
	}
	@RequestMapping(value = "/prodRec/calalogtype.shtml")
	@ResponseBody
	public  List<Map<String, Object>> calalogtype(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	 
		Map<String, Object> resMap = new HashMap<String, Object>();
		/*try { 
			StaffBean staffBean = this.getSessionStaffBean(request);
			paramMap.put("STAFF_ID", staffBean.getStaffID());
			ObjectMapper objectMapper = new ObjectMapper();
			resMap.put("CATALOG_TREE_JSON", objectMapper.writeValueAsString(prodRecService.selectCatalogs()));
		 
		} catch (Exception e) {
			 
		}
		return new ModelAndView("/prodRec/index",resMap); */
		return prodRecService.selectCatalogs();
	}
	@RequestMapping(value ="/prodRec/list_index.shtml")
	public ModelAndView listIndex(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/prodRec/list";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			resMap.put("CATALOG_ID",StringUtil.valueOf(paramMap.get("CATALOG_ID")));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ModelAndView(rtPage,resMap);
	}
	@RequestMapping(value = "/prodRec/list.shtml")
	@ResponseBody
	public Map<String, Object> datalist(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		Integer totalCount =0;
		try { StaffBean staffBean = this.getSessionStaffBean(request);
		    resMap.put("CATALOG_ID",StringUtil.valueOf(paramMap.get("CATALOG_ID")));
			paramMap=this.setPageParametersLiger(request,paramMap);
        	totalCount = prodRecService.querycatadataListTotal(paramMap); 
			dataList =  prodRecService.querycatadataList(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
			totalCount = 0;
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	
	} 
	
	 
	
	/**
	 * 打开添加或者修改页面
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/prodRec/detail.shtml")
	public ModelAndView detail(@RequestParam Map<String, Object> param,
			HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		// List<Map<String,Object>> list = prodRecService.selectProducts(new
		// HashMap<String, Object>());
		resMap.put("CATALOG_ID",StringUtil.valueOf(param.get("CATALOG_ID")));
		return new ModelAndView("/prodRec/detail",resMap);
	}

	@RequestMapping(value = "/prodRec/productTypedata.shtml")
	public void productTypedata(@RequestParam Map<String, Object> param,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<Map<String, Object>> productTypes = prodRecService
				.selectProductTypes(new HashMap<String, Object>());

		JSONArray jsonArray = JSONArray.fromObject(TreeUtils.buildTree(productTypes,"0"));
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonArray.toString());
		response.getWriter().flush();
	}

	@RequestMapping(value = "/prodRec/search.shtml")
	public void productSearch(@RequestParam HashMap<String, Object> param, HttpServletResponse response,HttpServletRequest request) {

		try {
			param=this.setPageParametersLiger(request,param);
			String total = prodRecService.selectProductsForProductTypeIdCount(param);
			List<Map<String, Object>> products = prodRecService.selectProductsForProductTypeId(param);
			JSONArray jsonArray = JSONArray.fromObject(products);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("Rows", jsonArray.toString());
			jsonObject.put("Total", total);
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(jsonObject.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	/**
	 * 保存首页推荐产品
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/prodRec/saveRecommend.shtml")
	@ResponseBody
	public void saveRecommend(@RequestParam Map<String, Object> param,
			HttpServletResponse response,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		param.put("CREATE_STAFF_ID", this.getSessionStaffBean(request).getStaffID());
		try {
			prodRecService.saveRecommend(param );
			map.put("statusCode", StateCode.JSON_AJAX_SUCCESS.getStateCode());
			map.put("message", StateCode.JSON_AJAX_SUCCESS.getStateMessage());
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(JSONObject.fromObject(map).toString());
			response.getWriter().flush();
		} catch (Exception e) {
			map.put("statusCode", StateCode.JSON_AJAX_ERROR.getStateCode());
			map.put("message", StateCode.JSON_AJAX_ERROR.getStateMessage());
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/prodRec/save.shtml")
	public ModelAndView save(@RequestParam Map<String, Object> param) {

		return new ModelAndView();
	}

	/**
	 * 删除推荐产品
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/prodRec/delete.shtml")
	public void delete(@RequestParam Map<String, Object> param,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			prodRecService.delete(param);
			map.put("statusCode", StateCode.JSON_AJAX_SUCCESS.getStateCode());
			map.put("message", StateCode.JSON_AJAX_SUCCESS.getStateMessage());
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(JSONObject.fromObject(map).toString());
			response.getWriter().flush();
		} catch (Exception e) {
			map.put("statusCode", StateCode.JSON_AJAX_ERROR.getStateCode());
			map.put("message", StateCode.JSON_AJAX_ERROR.getStateMessage());
			e.printStackTrace();
		}

	}
	 
	@RequestMapping(value = "/prodRec/edit.shtml")
	public ModelAndView  catainfoEdit(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/prodRec/edit";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		  
    	List<?> dataList=prodRecService.selectCatalogId(paramMap);
    	if(dataList!=null&&dataList.size()>0){
    		HashMap<String,Object> tmpMap = (HashMap<String,Object>)dataList.get(0);
    		resMap.putAll(tmpMap); 
    	}  
          return new ModelAndView(rtPage, resMap);
	}
	@RequestMapping(value = "/prodRec/saveedit.shtml")
	@ResponseBody
	public  Map<String, Object>  saveedit(@RequestParam Map<String, Object> param,
			HttpServletResponse response,HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		 try {
			prodRecService.updatecatainfo(param );
			resMap.put("RESULT_CODE", 0);
			resMap.put("RESULT_MESSAGE", "操作成功!");
		} catch (Exception e) {
			resMap.put("RESULT_CODE",1);
			resMap.put("RESULT_MESSAGE", "操作异常!");
			e.printStackTrace();
		}
		return resMap;
	}
	
}
