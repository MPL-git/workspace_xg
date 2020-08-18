package com.jf.controller;

import java.text.ParseException;
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

import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.entity.ShopProductCustomType;
import com.jf.entity.ShopProductCustomTypeCustom;
import com.jf.entity.ShopProductCustomTypeExample;
import com.jf.service.ShopProductCustomTypeService;

@Controller
@RequestMapping("/shopProductCustomType")
public class ShopProductCustomTypeController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(ShopProductCustomTypeController.class);

	@Resource
	private ShopProductCustomTypeService shopProductCustomTypeService;
	
	/**
	 * 商品分类列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/shopProductCustomTypeIndex")
	public String shopProductCustomTypeIndex(Model model, HttpServletRequest request) {
		return "shopProductCustomType/shopProductCustomTypeIndex";
	}
	
	/**
	 * 商品分类数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/getShopProductCustomTypeList")
	@ResponseBody
	public ResponseMsg getShopProductCustomTypeList(HttpServletRequest request, Page page) throws ParseException {
		Map<String, Object> returnData = new HashMap<String, Object>();
		ShopProductCustomTypeExample e = new ShopProductCustomTypeExample();
		e.setOrderByClause("t.seq_no asc,t.id desc");
		ShopProductCustomTypeExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		e.setLimitStart(page.getLimitStart());
		e.setLimitSize(page.getLimitSize());
		int totalCount = shopProductCustomTypeService.countByExample(e);
		List<ShopProductCustomTypeCustom> shopProductCustomTypeCustoms = shopProductCustomTypeService.selectCustomByExample(e);
		returnData.put("Rows", shopProductCustomTypeCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 添加
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/addShopProductCustomType")
	public String addShopProductCustomType(Model model, HttpServletRequest request) {
		return "shopProductCustomType/addShopProductCustomType";
	}
	
	/**
	 * 保存
	 * 
	 * @param request
	 * @param page
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/saveShopProductCustomType")
	@ResponseBody
	public ResponseMsg saveShopProductCustomType(HttpServletRequest request) throws ParseException {
		String name = request.getParameter("name");
		ShopProductCustomTypeExample example = new ShopProductCustomTypeExample();
		ShopProductCustomTypeExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		criteria.andNameEqualTo(name);
		List<ShopProductCustomType> shopProductCustomTypes = shopProductCustomTypeService.selectByExample(example);
		if(shopProductCustomTypes!=null && shopProductCustomTypes.size()>0){
			return new ResponseMsg(ResponseMsg.ERROR, "分类名称已存在，无法添加");
		}
		ShopProductCustomType shopProductCustomType = new ShopProductCustomType();
		shopProductCustomType.setDelFlag("0");
		shopProductCustomType.setCreateBy(this.getSessionMchtInfo(request).getId());
		shopProductCustomType.setCreateDate(new Date());
		shopProductCustomType.setMchtId(this.getSessionMchtInfo(request).getId());
		shopProductCustomType.setName(name);
		shopProductCustomType.setStatus("1");//1.显示
		ShopProductCustomTypeExample e = new ShopProductCustomTypeExample();
		ShopProductCustomTypeExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		int totalCount = shopProductCustomTypeService.countByExample(e);
		if(totalCount == 0){
			shopProductCustomType.setSeqNo(1);
		}else{
			int maxSeqNo = shopProductCustomTypeService.getMaxSeqNo(this.getSessionMchtInfo(request).getId());
			shopProductCustomType.setSeqNo(maxSeqNo+1);
		}
		shopProductCustomTypeService.insertSelective(shopProductCustomType);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 排序保存
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/saveSeqNo")
	@ResponseBody
	public ResponseMsg saveSeqNo(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			int seqNo = Integer.parseInt(request.getParameter("seqNo"));
			ShopProductCustomType shopProductCustomType = shopProductCustomTypeService.selectByPrimaryKey(Integer.parseInt(id));
			shopProductCustomType.setUpdateDate(new Date());
			shopProductCustomType.setUpdateBy(this.getSessionMchtInfo(request).getId());
			shopProductCustomTypeService.saveSeqNo(shopProductCustomType,seqNo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 分类名称修改保存
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/editName")
	@ResponseBody
	public ResponseMsg editName(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			ShopProductCustomTypeExample example = new ShopProductCustomTypeExample();
			ShopProductCustomTypeExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
			criteria.andNameEqualTo(name);
			List<ShopProductCustomType> shopProductCustomTypes = shopProductCustomTypeService.selectByExample(example);
			if(shopProductCustomTypes!=null && shopProductCustomTypes.size()>0){
				if(!shopProductCustomTypes.get(0).getId().equals(Integer.parseInt(id))){
					return new ResponseMsg(ResponseMsg.ERROR, "分类名称已存在，无法添加");
				}
			}
			ShopProductCustomType shopProductCustomType = shopProductCustomTypeService.selectByPrimaryKey(Integer.parseInt(id));
			shopProductCustomType.setUpdateDate(new Date());
			shopProductCustomType.setUpdateBy(this.getSessionMchtInfo(request).getId());
			shopProductCustomType.setName(name);
			shopProductCustomTypeService.updateByPrimaryKeySelective(shopProductCustomType);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 隐藏/显示
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/changeStatus")
	@ResponseBody
	public ResponseMsg changeStatus(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			ShopProductCustomType shopProductCustomType = shopProductCustomTypeService.selectByPrimaryKey(Integer.parseInt(id));
			if(shopProductCustomType.getStatus().equals("0")){
				shopProductCustomType.setStatus("1");
			}else{
				shopProductCustomType.setStatus("0");
			}
			shopProductCustomType.setUpdateDate(new Date());
			shopProductCustomType.setUpdateBy(this.getSessionMchtInfo(request).getId());
			shopProductCustomTypeService.updateByPrimaryKeySelective(shopProductCustomType);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseMsg delete(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			ShopProductCustomType shopProductCustomType = shopProductCustomTypeService.selectByPrimaryKey(Integer.parseInt(id));
			shopProductCustomType.setUpdateDate(new Date());
			shopProductCustomType.setUpdateBy(this.getSessionMchtInfo(request).getId());
			shopProductCustomType.setDelFlag("1");//删除
			shopProductCustomTypeService.updateByPrimaryKeySelective(shopProductCustomType);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
}
