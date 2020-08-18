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
public class TestProduct extends ATestSignatureController {

	@Override
	public void init(){
		topParamsMap.put("system", "1");
		topParamsMap.put("version", "58");
		//topParamsMap.put("token", "eab3eb7c-5e17-422e-ae3d-0630b17e7d4d");
		//postParamsMap.put("memberId", 13074043);

		topParamsMap.put("token", "a0ed9ea4-d10a-4b50-adce-20fb423552ca");
		postParamsMap.put("memberId", 1989);
	}

	@Test
	@Override
	public void doTest() {
		BaseUrl baseUrl = getProductInfo();
		System.out.println(formatJson(doPost(baseUrl.toString())));
	}

	public BaseUrl getProductInfo(){
		postParamsMap.put("id", 540);
		return new BaseUrl("mtest.xgbuy.cc/webApp", "/api/n/getProductInfo");
	}

	public BaseUrl getProductDetail(){
		postParamsMap.put("id", 540);
		return new BaseUrl("mtest.xgbuy.cc/webApp", "/api/n/getProductDetail");
	}

	public BaseUrl list(){
		postParamsMap.put("pageSize", 5);
		postParamsMap.put("currentPage", 1);
		postParamsMap.put("type", "2");
		return new BaseUrl("121.196.208.202:8902/appService1588", "/api/y/getAppMemberMessageByType");
	}





}
