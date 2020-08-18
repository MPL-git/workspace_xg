package webApp;

import com.jf.common.ext.test.ATestSignatureController;
import com.jf.common.ext.test.BaseUrl;
import org.junit.Test;

/**
 * 测试类
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
		long begin = System.currentTimeMillis();
		BaseUrl baseUrl = list();
		String resultStr = formatJson(doPost(baseUrl.toString()));
		System.out.println(resultStr);
		System.out.println("接口耗时：" + (System.currentTimeMillis() - begin) + "ms");
	}

	public BaseUrl list(){
		postParamsMap.put("pageSize", 10);
		postParamsMap.put("currentPage", 0);
		postParamsMap.put("type", "3");
		postParamsMap.put("productType1Id", "316");
		postParamsMap.put("id", "1");
		postParamsMap.put("videoId", "13");
		postParamsMap.put("videoIds", "193");
		postParamsMap.put("imgs", "sadfasd");
		postParamsMap.put("mchtId", 1);
		postParamsMap.put("content", "aaasdfgh");
		postParamsMap.put("commentId", "1");
		postParamsMap.put("remindType", "4");
		return new BaseUrl("localhost:8080/webApp", "/api/n/video/comment/list");
//		return new BaseUrl("121.196.208.202:8902/appService", "/api/n/video/typeRelative/list");
	}




}
