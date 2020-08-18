package com.jf.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.entity.KeywordHomoionym;
import com.jf.entity.KeywordHomoionymCustomExample;
import com.jf.entity.KeywordHomoionymExample;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.service.KeywordHomoionymService;
import com.jf.vo.Page;

@Controller
public class KeywordHomoionymController extends BaseController {
	
	private static final long serialVersionUID = 1L;
		
	@Resource
	private KeywordHomoionymService keywordhomoionymService;
	
	
	//关键字近义词
	@RequestMapping("/keywordHomoionym/keywordhomoionymList.shtml")
	public ModelAndView keywordhomoionymList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/keywordHomoionym/keywordhomoionym");
		return m;
	}
	
	//关键字近义词数据
	@ResponseBody
	@RequestMapping("/keywordHomoionym/keywordhomoionymdata.shtml")
	public Map<String, Object> keywordhomoionymdata(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			KeywordHomoionymCustomExample keywordHomoionymCustomExample=new KeywordHomoionymCustomExample();
			KeywordHomoionymCustomExample.KeywordHomoionymCustomCriteria keywordHomoionymCustomCriteria=keywordHomoionymCustomExample.createCriteria();
			keywordHomoionymCustomCriteria.andDelFlagEqualTo("0");
			keywordHomoionymCustomExample.setOrderByClause("create_date desc");
			if (!StringUtil.isEmpty(request.getParameter("keyword"))) {
				keywordHomoionymCustomCriteria.andKeywordLike("%"+request.getParameter("keyword")+"%");
			}
			if (!StringUtil.isEmpty(request.getParameter("create_begin"))) {
				keywordHomoionymCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("create_begin")+" 00:00:00"));
			}
			if (!StringUtil.isEmpty(request.getParameter("create_end"))) {
				keywordHomoionymCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("create_end")+" 23:59:59"));
			}
			totalCount = keywordhomoionymService.countByExample(keywordHomoionymCustomExample);
			keywordHomoionymCustomExample.setLimitStart(page.getLimitStart());
			keywordHomoionymCustomExample.setLimitSize(page.getLimitSize());
			List<KeywordHomoionym> keywordHomoionymlist = keywordhomoionymService.selectByExample(keywordHomoionymCustomExample);
			
			resMap.put("Rows", keywordHomoionymlist);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	    //添加关键字近义词界面
		@RequestMapping(value = "/keywordHomoionym/edit.shtml")
		public ModelAndView addkeywordhomoionym(HttpServletRequest request) {
			String rtPage = "/keywordHomoionym/editkeywordhomoionym";
			String  keywordHomoionymid=request.getParameter("Id");
			
			Map<String, Object> resMap = new HashMap<String, Object>();
			if (keywordHomoionymid!=null && keywordHomoionymid!="") {
				KeywordHomoionym keywordHomoionym=keywordhomoionymService.selectByPrimaryKey(Integer.valueOf(keywordHomoionymid));
				resMap.put("keywordHomoionym", keywordHomoionym);
			
			}
			return new ModelAndView(rtPage, resMap);
		}
		
		        
				@RequestMapping(value = "/keywordHomoionym/addkeywordHomoionym.shtml")
				@ResponseBody
			 	public ModelAndView addkeywordHomoionym(HttpServletRequest request,KeywordHomoionym keywordHomoionym,String id){
					String rtPage = "/success/success";
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = null;
					String msg =null;
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						int staffId=Integer.valueOf(staffBean.getStaffID());
						
				         if (keywordHomoionym.getId()==null) {//添加
				        	 KeywordHomoionymExample keywordHomoionymExample=new KeywordHomoionymExample();
				    		 keywordHomoionymExample.createCriteria().andDelFlagEqualTo("0").andKeywordEqualTo(keywordHomoionym.getKeyword());
				        	 List<KeywordHomoionym> keywordHomoionyms=keywordhomoionymService.selectByExample(keywordHomoionymExample);
				        	 if (keywordHomoionyms!=null && keywordHomoionyms.size()>0) {
				        		 code = StateCode.JSON_AJAX_ERROR.getStateCode();
								 msg = "近义词库已存在该关键字~!";
								 resMap.put(this.JSON_RESULT_CODE, code);
								 resMap.put(this.JSON_RESULT_MESSAGE, msg);
								 return new ModelAndView(rtPage, resMap);
							}else {
								keywordHomoionym.setCreateBy(staffId);
								keywordHomoionym.setCreateDate(new Date());
								keywordhomoionymService.insertSelective(keywordHomoionym);
								
							}
							
						}else {//更新
							
							 KeywordHomoionym keywordhomoionyms=keywordhomoionymService.selectByPrimaryKey(Integer.valueOf(id));					 				
							 KeywordHomoionymExample keywordHomoionymExample=new KeywordHomoionymExample();
				    		 keywordHomoionymExample.createCriteria().andDelFlagEqualTo("0").andKeywordEqualTo(keywordHomoionym.getKeyword());
				        	 List<KeywordHomoionym> keywordHomoionyms=keywordhomoionymService.selectByExample(keywordHomoionymExample);
				        	 if (!keywordHomoionym.getKeyword().equals(keywordhomoionyms.getKeyword())) {//除本身							
				        		 if (keywordHomoionyms!=null && keywordHomoionyms.size()>0) {
				        			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
				        			 msg = "近义词库已存在该关键字~!";
				        			 resMap.put(this.JSON_RESULT_CODE, code);
				        			 resMap.put(this.JSON_RESULT_MESSAGE, msg);
                                     return new ModelAndView(rtPage, resMap);
							  }else {
								   
								    KeywordHomoionym keywordHomoionym1=keywordhomoionymService.selectByPrimaryKey(Integer.valueOf(id));
									keywordHomoionym1.setKeyword(keywordHomoionym.getKeyword());
									keywordHomoionym1.setHomoionym(keywordHomoionym.getHomoionym());
									keywordHomoionym1.setRemarks(keywordHomoionym.getRemarks());
									keywordHomoionym1.setUpdateBy(staffId);
									keywordHomoionym1.setUpdateDate(new Date());
									keywordhomoionymService.updateByPrimaryKeySelective(keywordHomoionym1);
							   }							 
					        	 
						   }else {
							   
							    KeywordHomoionym keywordHomoionym1=keywordhomoionymService.selectByPrimaryKey(Integer.valueOf(id));
								keywordHomoionym1.setHomoionym(keywordHomoionym.getHomoionym());
								keywordHomoionym1.setRemarks(keywordHomoionym.getRemarks());
								keywordHomoionym1.setUpdateBy(staffId);
								keywordHomoionym1.setUpdateDate(new Date());
								keywordhomoionymService.updateByPrimaryKeySelective(keywordHomoionym1);
						   }				        	 
				        	 							
						}
																									
						code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
						msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
					} catch (Exception e) {
						  e.printStackTrace();
						 code = StateCode.JSON_AJAX_ERROR.getStateCode();
						 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
					}
					resMap.put(this.JSON_RESULT_CODE, code);
					resMap.put(this.JSON_RESULT_MESSAGE, msg);
					return new ModelAndView(rtPage, resMap);
				}
				
				//删除
				@RequestMapping(value = "/keywordHomoionym/updatekeywordHomoionymdata.shtml")
				@ResponseBody
				public Map<String, Object> updatekeywordHomoionymdata(HttpServletRequest request,HttpServletResponse response,String id){
					Map<String, Object> resMap = new HashMap<String, Object>();
					resMap.put("returnCode", "0000");
					resMap.put("returnMsg", "成功");
					try {
						String keywordHomoionymid=request.getParameter("id");
						StaffBean staffBean = this.getSessionStaffBean(request);
						int staffId=Integer.valueOf(staffBean.getStaffID());
						
						KeywordHomoionym keywordHomoionym=keywordhomoionymService.selectByPrimaryKey(Integer.valueOf(keywordHomoionymid));
						keywordHomoionym.setUpdateBy(staffId);
						keywordHomoionym.setUpdateDate(new Date());
						keywordHomoionym.setDelFlag("1");
						keywordhomoionymService.updateByPrimaryKeySelective(keywordHomoionym);							
						
					} catch (NumberFormatException e) {
						e.printStackTrace();
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", e.getMessage());
					}
					
					return resMap;
				}
				
}
