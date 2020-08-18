package com.jf.test;

import com.alibaba.fastjson.JSONObject;
import com.jf.common.ext.test.ATestSignatureController;
import com.jf.common.ext.test.BaseUrl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试类
 * 
 * @author huangdl
 *
 */
public class TestUserController extends ATestSignatureController {

	@Override
	public void init(){
		topParamsMap.put("system", "1");
		topParamsMap.put("version", "1");
		//topParamsMap.put("token", "eab3eb7c-5e17-422e-ae3d-0630b17e7d4d");

		//postParamsMap.put("memberId", 13074043);

	}

	@Test
	@Override
	public void doTest() {
		BaseUrl baseUrl = xxx();
		System.out.println(formatJson(doPost(baseUrl.toString())));
	}

	public BaseUrl xxx(){

		//postParamsMap.put("pageSize", 2);
		postParamsMap.put("currentPage", 0);
		postParamsMap.put("brandTeamTypeId", 1);
		return new BaseUrl("localhost", "/api/n/getCategoryBrandGroup");
	}

	public BaseUrl list(){

		postParamsMap.put("pageSize", 2);
		return new BaseUrl("localhost", "/api/n/user/list");
	}

	public BaseUrl save(){
		JSONObject model = new JSONObject();
		//model.put("id", 58);
		model.put("type", 1);
		model.put("nickName", "可宜可以");
		model.put("realName", "KOI");
		model.put("mobile", "18750527333");
		model.put("amount", 300.33);
		model.put("feeRate", 0.04322);

		List<JSONObject> itemList = new ArrayList<>();
		itemList.add(instant(166751, "规格1", 5.55, 999));
		itemList.add(instant(2, "规格2", 105.55, 333));
		model.put("itemList", itemList);
		postParamsMap.put("model", model);

		List<JSONObject> skuList = new ArrayList<>();
		skuList.add(instant(166751, "规格1", 5.55, 999));
		skuList.add(instant(2, "规格2", 105.55, 333));
		postParamsMap.put("skuList", skuList);

		return new BaseUrl("localhost", "/api/y/user/save");
	}

	public BaseUrl edit(){
		postParamsMap.put("id", 1);
		return new BaseUrl("localhost", "/api/y/user/edit");
	}

	public BaseUrl delete(){
		postParamsMap.put("ids", new int[]{1});
		return new BaseUrl("localhost", "/api/y/user/delete");
	}







	private JSONObject instant(int id, String name, double price, int stock){
		JSONObject data = new JSONObject();
		data.put("id", id);
		data.put("name", name);
		data.put("price", price);
		data.put("stock", stock);
		return data;
	}

}
