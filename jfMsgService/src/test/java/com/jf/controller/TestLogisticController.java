package com.jf.controller;

import com.jf.common.ext.test.ATestSignatureController;
import com.jf.common.ext.test.BaseUrl;
import org.junit.Test;

/**
 * 官方活动测试类
 * 
 * @author huangdl
 *
 */
public class TestLogisticController extends ATestSignatureController {

	@Override
	public void init(){
//		topParamsMap.put("system", "1");
//		topParamsMap.put("version", "1");
//		//topParamsMap.put("token", "eab3eb7c-5e17-422e-ae3d-0630b17e7d4d");
//		//postParamsMap.put("memberId", 13074043);
//
//		topParamsMap.put("token", "a0ed9ea4-d10a-4b50-adce-20fb423552ca");
//		postParamsMap.put("memberId", 1989);
	}

	@Test
	@Override
	public void doTest() {
		BaseUrl baseUrl = testQuery();
		System.out.println(formatJson(doPost(baseUrl.toString())));
	}

	public BaseUrl testSubscribe(){
		postParamsMap.put("shipperCode", "YTO");
		postParamsMap.put("logisticCode", "885126163773620588");
		return new BaseUrl("localhost", "/api/kdn/subscribe");
	}

	public BaseUrl testQuery(){
		//postParams.put("shipperCode", "YTO");
		//postParams.put("logisticCode", "885126163773620588");
		postParamsMap.put("shipperCode", "YTO");
		postParamsMap.put("logisticCode", "885159805703852801");
		return new BaseUrl("localhost", "/api/kdn/query");
	}

}
