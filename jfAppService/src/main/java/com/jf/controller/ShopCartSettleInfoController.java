package com.jf.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.jf.controller.base.BaseController;
import com.jf.service.ShopPreferentialInfoService;
import com.jf.service.ShoppingCartService;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年6月4日 下午3:39:10 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class ShopCartSettleInfoController extends BaseController{
	@Resource
	private ShoppingCartService shoppingCartService;
	@Resource
	private ShopPreferentialInfoService shopPreferentialInfoService;
}
