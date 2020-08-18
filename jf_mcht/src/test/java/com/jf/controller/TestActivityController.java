package com.jf.controller;

import com.jf.common.ext.test.ATestController;
import com.jf.common.ext.test.BaseUrl;
import org.junit.Test;

/**
 * 官方活动测试类
 * 
 * @author huangdl
 *
 */
public class TestActivityController extends ATestController {

	@Override
	public void init() throws Exception {
		System.out.print("xxx");
	}

	@Test
	@Override
	public void doTest() {
		System.out.println(formatJson(doPost()));
	}

	@Override
	protected BaseUrl getBaseUrl() {
		return testSave();
	}
	
	public BaseUrl testList(){
		return new BaseUrl("localhost", "/activity/list");
	}

	public BaseUrl testSave(){
		postParamsMap.put("name", "测试的活动11112222");
		postParamsMap.put("productTypeId", 2404);	//分类
		postParamsMap.put("productBrandId", 3964);	//品牌
		postParamsMap.put("benefitPoint", "全场八折");	//利益点
		postParamsMap.put("expectedStartTime", "2017-04-15");	//期望活动时间
		postParamsMap.put("preferentialType", "3");	//促销方式
		//postParamsMap.put("xxxx", "[{\"aaa\":520, \"bbb\": \"用顺丰速递\", \"ccc\":10, \"ddd\":1000248}]");
		return new BaseUrl("localhost", "/activity/save");
	}

}
