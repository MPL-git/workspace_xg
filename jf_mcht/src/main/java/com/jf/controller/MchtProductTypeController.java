package com.jf.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.entity.MchtProductType;
import com.jf.entity.MchtProductTypeCustom;
import com.jf.entity.MchtProductTypeExample;
import com.jf.entity.ProductTypeExample;
import com.jf.service.MchtProductTypeService;
import com.jf.service.ProductTypeService;

@Controller
public class MchtProductTypeController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(MchtProductTypeController.class);
	
	
	@Resource
	private MchtProductTypeService mchtProductTypeService;
	
	@Resource
	private ProductTypeService productTypeService;

	/**
	 * 
	 * 
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/mchtProductType/mchtProductTypeIndex")
	public String mchtProductTypeIndex(Model model, HttpServletRequest request) {
		
		return "mchtProductType/mchtProductTypeIndex";
	}
	
	
	@RequestMapping("/mchtProductType/addMchtProductType")
	public String addMchtProductType(Model model, HttpServletRequest request) {
		
		MchtProductTypeExample mchtProductTypeExample = new MchtProductTypeExample();
		mchtProductTypeExample.createCriteria()
								.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andDelFlagEqualTo("0")
								.andStatusNotEqualTo("2");
		List<MchtProductType> mchtProductTypes = mchtProductTypeService.selectByExample(mchtProductTypeExample);
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria criteria = productTypeExample.createCriteria();
		criteria.andDelFlagEqualTo("0").andStatusNotEqualTo("0").andParentIdEqualTo(1);
		if(mchtProductTypes != null && mchtProductTypes.size() > 0){
			List<Integer> productTypeIds = new ArrayList<Integer>();
			for(MchtProductType mchtProductType : mchtProductTypes){
				productTypeIds.add(mchtProductType.getProductTypeId());
			}
			criteria.andIdNotIn(productTypeIds);
		}
		
		model.addAttribute("productTypeList", productTypeService.selectByExample(productTypeExample));
		return "mchtProductType/mchtProductTypeEdit";
	}
	/**
	 *
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtProductType/getMchtProductTypeList")
	@ResponseBody
	public ResponseMsg getMchtProductTypeList(HttpServletRequest request,Page page) {
		
		Map<String, Object> returnData = new HashMap<String, Object>();
		MchtProductTypeExample mchtProductTypeExample=new MchtProductTypeExample();
		mchtProductTypeExample.createCriteria().andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andDelFlagEqualTo("0");
		List<MchtProductTypeCustom> mchtProductTypes=mchtProductTypeService.selectMchtProductTypeCustomByExample(mchtProductTypeExample);
		returnData.put("Rows", mchtProductTypes);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	
	}
	
	
	/**
	 * 删除商家品类
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mchtProductType/delMchtProductType")
	@ResponseBody
	public ResponseMsg delMchtProductType( HttpServletRequest request) {
		try {
			MchtProductTypeExample mchtProductTypeExample=new MchtProductTypeExample();
			mchtProductTypeExample.createCriteria().andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andIdEqualTo(Integer.valueOf(request.getParameter("id")));
			List<MchtProductType> mchtProductTypes=mchtProductTypeService.selectByExample(mchtProductTypeExample);
			if(mchtProductTypes==null||mchtProductTypes.size()==0){
				throw new ArgException("信息不存在");
			}
			MchtProductType mchtProductType=mchtProductTypes.get(0);
			if(!"0".equals(mchtProductType.getStatus())&&!"2".equals(mchtProductType.getStatus())){
				throw new ArgException("次信息不可删除");
			}
			
			MchtProductType mchtProductType4Update=new MchtProductType();
			mchtProductType4Update.setDelFlag("1");
			mchtProductType4Update.setId(mchtProductType.getId());
			mchtProductType4Update.setUpdateDate(new Date());
			mchtProductType4Update.setUpdateBy(this.getSessionUserInfo(request).getId());
			mchtProductTypeService.updateByPrimaryKeySelective(mchtProductType4Update);
		}catch(ArgException e){
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	
	/**
	 * 添加商家品类
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mchtProductType/mchtProductTypeSave")
	@ResponseBody
	public ResponseMsg mchtProductTypeSave( HttpServletRequest request) {
		try {
			MchtProductTypeExample mpyExample = new MchtProductTypeExample();
			mpyExample.createCriteria().andDelFlagEqualTo("0")
										.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
			int count = mchtProductTypeService.countByExample(mpyExample);
			if(count > 15){
				throw new ArgException("最多只能添加15个类目");
			}else {
				MchtProductTypeExample mchtProductTypeExample = new MchtProductTypeExample();
				MchtProductTypeExample.Criteria mchtProductTypeCriteria = mchtProductTypeExample.createCriteria();
				mchtProductTypeCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId())
										.andProductTypeIdEqualTo(Integer.valueOf(request.getParameter("productTypeId")));
				List<MchtProductType> mchtProductTypeList = mchtProductTypeService.selectByExample(mchtProductTypeExample);
				MchtProductType mchtProductType = new MchtProductType();
				if(mchtProductTypeList != null && mchtProductTypeList.size() > 0) {
					mchtProductType.setDelFlag("0");
					mchtProductType.setStatus("0");
					mchtProductType.setUpdateBy(this.getSessionUserInfo(request).getId());
					mchtProductType.setUpdateDate(new Date());
					mchtProductTypeService.updateByExampleSelective(mchtProductType, mchtProductTypeExample);
				}else {
					mchtProductType.setDelFlag("0");
					mchtProductType.setCreateDate(new Date());
					mchtProductType.setCreateBy(this.getSessionUserInfo(request).getId());
					mchtProductType.setMchtId(this.getSessionMchtInfo(request).getId());
					mchtProductType.setProductTypeId(Integer.valueOf(request.getParameter("productTypeId")));
					mchtProductType.setStatus("0");
					if(count == 0){
						mchtProductType.setIsMain("1");//是主营
					}
					mchtProductTypeService.insertSelective(mchtProductType);
				}
			}
		}catch(ArgException e){
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
}
