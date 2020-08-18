package com.jf.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.StringUtil;
import com.jf.entity.ProductPic;
import com.jf.entity.ProductPicExample;
import com.jf.service.ProductPicService;

import net.sf.json.JSONObject;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年3月30日 上午10:15:47 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class TestController {
	
	@Resource
	private ProductPicService productPicService;
	
	@RequestMapping(value = "/api/n/getTestPic")
	@ResponseBody
	public ResponseMsg getFourCategories(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"pageSize","currentPage"};
			Integer currentPage = reqDataJson.getInt("currentPage");
			Integer pageSize = reqDataJson.getInt("pageSize");
			ProductPicExample productPicExample = new ProductPicExample();
			productPicExample.createCriteria().andDelFlagEqualTo("0");
			productPicExample.setLimitStart(currentPage*pageSize);
			productPicExample.setLimitSize(pageSize);
			List<ProductPic> productPics = productPicService.selectByExample(productPicExample);
			if(CollectionUtils.isNotEmpty(productPics)){
				for (ProductPic productPic : productPics) {
					productPic.setPic(StringUtil.getPic(productPic.getPic(), "M"));
				}
			}
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,productPics);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}

}
