package sys;

import com.jf.common.ext.test.ATestSignatureController;
import com.jf.common.ext.test.BaseUrl;
import org.junit.Test;

/**
 * 测试类
 *
 * @author huangdl
 *
 */
public class TestAppMessage extends ATestSignatureController {

	@Override
	public void init(){
		topParamsMap.put("system", "1");
		topParamsMap.put("version", "1");
		//topParamsMap.put("token", "eab3eb7c-5e17-422e-ae3d-0630b17e7d4d");
		//postParamsMap.put("memberId", 13074043);

		topParamsMap.put("token", "96269ec3-658d-4d45-98c0-275bcab9d666");
//		postParamsMap.put("memberId", 1320);
		postParamsMap.put("memberId", 1320);
	}

	@Test
	@Override
	public void doTest() {
		BaseUrl baseUrl = list();
		System.out.println(formatJson(doPost(baseUrl.toString())));
	}

	public BaseUrl index(){
		return new BaseUrl("localhost", "/api/n/video/typeRelative/list");
	}

	public BaseUrl list(){
		postParamsMap.put("pageSize", 10);
		postParamsMap.put("currentPage", 0);
		postParamsMap.put("type", "1");
		postParamsMap.put("productType1Id", "316");
		postParamsMap.put("id", "1");
		postParamsMap.put("videoId", "13");
		postParamsMap.put("videoIds", "193");
		postParamsMap.put("imgs", "sadfasd");
		postParamsMap.put("mchtId", 1);
		postParamsMap.put("content", "aaasdfgh");
		postParamsMap.put("commentId", "1");
		postParamsMap.put("remindType", "4");
		return new BaseUrl("localhost:8081/appService_war_exploded", "/api/n/video/list");
//		return new BaseUrl("121.196.208.202:8902/appService", "/api/n/video/typeRelative/list");
	}

	public BaseUrl clear(){
		postParamsMap.put("type", "3");
		return new BaseUrl("localhost", "/api/y/deleteAppMessageByType");
	}

	public BaseUrl activitySelectionDetail(){
		postParamsMap.put("id", 31);
		return new BaseUrl("localhost", "/api/n/activitySelection/detail");
	}

	public BaseUrl addLoginLogByStartUp(){
		postParamsMap.put("regIp", "xx");
		postParamsMap.put("mobileBrand", "xx");
		postParamsMap.put("mobileModel", "xx");
		postParamsMap.put("system", "IOS");
		postParamsMap.put("version", "11");
		postParamsMap.put("versionNum", "11");

		return new BaseUrl("121.196.208.202:8902/appService", "/api/y/addLoginLogByStartUp");
	}



}
