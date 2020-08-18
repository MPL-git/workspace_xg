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
public class TestCouponController extends ATestSignatureController {

	@Override
	public void init(){
		topParamsMap.put("system", "1");
		topParamsMap.put("version", "59");
		topParamsMap.put("token", "eab3eb7c-5e17-422e-ae3d-0630b17e7d4d");

		postParamsMap.put("memberId", 13074043);

	}

	@Test
	@Override
	public void doTest() {
		BaseUrl baseUrl = addReceiveCoupon();
		System.out.println(formatJson(doPost(baseUrl.toString())));
	}

	public BaseUrl popupAd(){

		postParamsMap.put("isReceivedRed", true);
		return new BaseUrl("121.196.208.202:8902/appService", "/api/n/getIndexPopupAds");
	}

	public BaseUrl addReceiveCoupon(){
		postParamsMap.put("couponId", "26744,26743,26745");
		//postParamsMap.put("receiveType", 7);
		//return new BaseUrl("localhost", "/api/y/addReceiveCoupon");
		return new BaseUrl("121.196.208.202:8902/appService", "/api/y/addReceiveCoupon");
	}

}
