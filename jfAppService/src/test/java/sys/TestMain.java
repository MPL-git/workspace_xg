package sys;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.HttpUtil;


public class TestMain {

	@Test
	public void testLogin() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("loginCode", "13645004403");
		reqData.put("password", "e3ceb5881a0a1fdaad01296d7554868d");
		reqData.put("system", "IOS");
		reqData.put("mobileBrand", "苹果");
		reqData.put("mobileModel", "iphone6");
		reqData.put("version", 12);
		reqData.put("versionNum", "1.0.1");
		reqData.put("ip", "127.0.0.1");
		reqData.put("deviceId", "sfeessefef");
		
		param.put("token", "1234567890");
		param.put("reqData", reqData);
		
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/jfAppService/api/n/login", CommonUtil.createReqData(param).toString()));
		//JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://a.xgbuy.cc/appService/api/n/login", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void thirdPartyLogin() {
		
		JSONObject param=new JSONObject();
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("thirdPartyId", "123456789");
		reqData.put("thirdPartyMark", "QQ");
		
		reqData.put("nick", "cwf测试联合登入");
		reqData.put("regArea", "厦门");
		reqData.put("sprChnl", "联合登入");
		
		reqData.put("pic", "/201702/20170206155040_6320.png");
		reqData.put("sexType", "1");
		reqData.put("birthday", "2017-01-01");
		
		reqData.put("regClient", "1");
		reqData.put("system", "IOS");
		reqData.put("mobileBrand", "苹果");
		reqData.put("mobileModel", "iphone7");
		reqData.put("version", 12);
		reqData.put("versionNum", "1.0.1");
		reqData.put("regIp", "127.0.0.1");
		reqData.put("deviceId", "sfeessefef");
		
		param.put("token", "1234567890");
		param.put("reqData", reqData);
		
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/thirdPartyLogin", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void testLoginOut() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "1");
		reqData.put("deviceId", "sfeessefef");
		param.put("token", "52a8aa9c-646c-4aa8-a893-01cb3114766f");
		param.put("reqData", reqData);
		
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://192.168.0.158:8080/appService/api/y/loginOut", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	
	@Test
	public void testGetMobileVerificationCode() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("mobile", "13655082627");
		param.put("reqData", reqData);
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/getMobileVerificationCode4Register", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void testMemberRegister() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("mobile", "13645004456");
		reqData.put("password", "96e79218965eb72c92a549dd5a330112");
		reqData.put("mobileVerificationCode", "888888");
		param.put("reqData", reqData);
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/memberRegister", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void testGetSMSCode() {
		
		JSONObject param=new JSONObject();
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("mobile", "13655082627");
		param.put("reqData", reqData);
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/getMobileVerificationCode4ResetPassword", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void verificationSMSCode() {
		
		JSONObject param=new JSONObject();
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("mobile", "13655082627");
		reqData.put("mobileVerificationCode", "888888");
		param.put("reqData", reqData);
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/verificationSMSCode", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void resetPassword() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("mobile", "13655082627");//96e79218965eb72c92a549dd5a330112  111111
		reqData.put("password", "96e79218965eb72c92a549dd5a330112");//123456  e10adc3949ba59abbe56e057f20f883e
		param.put("reqData", reqData);
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/resetPassword", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void updatePassword() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "104");
		reqData.put("oldPwd", "e10adc3949ba59abbe56e057f20f883e");//111111
		reqData.put("newPwd", "96e79218965eb72c92a549dd5a330112");//123456  e10adc3949ba59abbe56e057f20f883e
		param.put("reqData", reqData);
		param.put("token", "b09f60e7-f03b-4129-8743-60911b11d16f");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/updatePassword", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void getUserInfo() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "106");
		param.put("reqData", reqData);
		param.put("token", "9a51bcd0-b88f-4622-b2e2-6ebfcc032da5");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getUserInfo", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void updateUserInfo() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "104");
		reqData.put("birthday", "2017-01-01");
		param.put("reqData", reqData);
		param.put("token", "3cba7f25-bbc6-4967-9246-a0fe5edd83d0");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/updateUserInfo", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void getMemberAddressList() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "104");
		reqData.put("currentPage", "1");
		reqData.put("pageSize", "2");
		param.put("reqData", reqData);
		param.put("token", "9fdd63ab-7ae0-4f03-afcd-ddb0dc70af30");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getMemberAddressList", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void addMemberAddress() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "104");
		reqData.put("phone", "13655080001");
		reqData.put("recipient", "小陈4号");
		reqData.put("address", "福建厦门思明观音山");
		reqData.put("province", "350000");
		reqData.put("city", "350100");
		reqData.put("county", "350102");
		param.put("reqData", reqData);
		param.put("token", "188f6c86-1867-4773-95fe-e8aeab9b391a");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/addMemberAddress", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void updateMemberAddress() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("id", "75");
		reqData.put("memberId", "104");
		reqData.put("phone", "13655080001");
		reqData.put("recipient", "修改小陈1号");
		reqData.put("address", "福建厦门思明观音山");
		reqData.put("province", "350000");
		reqData.put("city", "350100");
		reqData.put("county", "350102");
		param.put("reqData", reqData);
		param.put("token", "d42bbc45-e498-47bd-947b-24aa8734e4de");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/updateMemberAddress", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void updateDefaultAddress() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("id", "75");
		reqData.put("memberId", "104");
		param.put("reqData", reqData);
		param.put("token", "188f6c86-1867-4773-95fe-e8aeab9b391a");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/updateDefaultAddress", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void uploadUserPic() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		String content = FileUtil.BASE64Encoder("C:\\Users\\Administrator\\Desktop\\img\\java8.jpg");
		reqData.put("pic", content);
		reqData.put("memberId", "104");
		param.put("reqData", reqData);
		param.put("token", "d5edfd67-8ce1-4d39-8bfc-4d2955b57fc6");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/uploadUserPic", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void getMemberCouponList() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "104");
		reqData.put("currentPage", "0");
		reqData.put("pageSize", "1");
		param.put("reqData", reqData);
		param.put("token", "0b172ad3-1b06-4bf1-b73e-83e3b0aa92b3");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getMemberCouponList", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void getAreaByParentId() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "104");
		reqData.put("parentId", "0");
		param.put("reqData", reqData);
		param.put("token", "24b5ebdf-c7df-47be-a946-53ad6cf38aa4");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getAreaByParentId", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void getMemberFootprintList() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "104");
		reqData.put("currentPage", "0");
		reqData.put("pageSize", "1");
		param.put("reqData", reqData);
		param.put("token", "8dd7d413-4165-4dd5-914a-5be495cdf0e9");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getMemberFootprintList", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void getIntegralDtlList() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "104");
		reqData.put("currentPage", "0");
		reqData.put("pageSize", "1");
		param.put("reqData", reqData);
		param.put("token", "228d2548-5efc-4485-9e1b-2e0da3e445af");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getIntegralDtlList", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void protocol() {
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "104");
		reqData.put("currentPage", "0");
		reqData.put("pageSize", "1");
		param.put("reqData", reqData);
		param.put("token", "fc6691b0-26d7-4f69-9e63-1fa46010a990");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/n/platform/protocol", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void getOrderDtlList() {
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "112");
		reqData.put("subOrderStatus", "1");
		reqData.put("productStatus", "");
		reqData.put("currentPage", "0");
		reqData.put("pageSize", "20");
		param.put("reqData", reqData);
		param.put("token", "f5fddb41-924c-49de-9451-b185e5332efc");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getOrderDtlList", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void getOrderDtlInfoList() {
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "533");
		reqData.put("combineOrderId", "1111");
		reqData.put("subOrderId", "1175");
		param.put("reqData", reqData);
		param.put("token", "eec8c69d-b6c5-46fa-9a22-e9fac0277ae0");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getOrderDtlInfoList", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void getAdInfoList() {
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("type", "1");
		reqData.put("catalog", "1");
		reqData.put("position", "1");
		param.put("reqData", reqData);
		param.put("token", "3bbc7482-dac7-42c0-bc35-c68371d798f9");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/getAdInfoList", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void getActivityCustomList() {
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("productTypeId", "");
		reqData.put("adCatalog", "1");
		
		reqData.put("currentPage", "0");
		reqData.put("pageSize", "5");
		param.put("reqData", reqData);
		param.put("token", "485481a2-7a13-401b-bbd4-96c2165f5e38");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/getActivityCustomList", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void getMainCategoryId() {
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		param.put("reqData", reqData);
		param.put("token", "5693c57b-7e7c-43b7-96df-86b277515079");
		param.put("version", "3");
		param.put("system", "2");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/getMainCategoryId", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void getProductInfoList() {
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "244");
		reqData.put("id", "6");
		reqData.put("activityAreaId", "52");
		param.put("reqData", reqData);
		param.put("token", "6806177e-b2cb-4890-b119-abd935748f7b");
		param.put("version", "3");
		param.put("system", "2");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/getProductInfoList", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void getProductItemsById() {
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("id", "36");
		reqData.put("propId", "1");
		reqData.put("propName", "颜色");
		reqData.put("propIds", "1");
		reqData.put("activityAreaId", "96");
		param.put("reqData", reqData);
		param.put("token", "6806177e-b2cb-4890-b119-abd935748f7b");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/getProductItemsById", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	
	
	@Test
	public void getNewUserEnjoyList() {
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "");//可以为空
		reqData.put("type", "7");
		reqData.put("productTypeId", "");
		reqData.put("currentPage", "0");
		reqData.put("pageSize", "10");
		param.put("reqData", reqData);
		param.put("token", "3f579997-020b-4f18-bc29-9136f9baaf67");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/getNewUserEnjoyList", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void getCouponByActivityAreaId() {
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "94");
		reqData.put("activityAreaId", "999");
		param.put("reqData", reqData);
		param.put("token", "57e9b4af-601e-45e5-953a-c2c52a196b17");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getCouponByActivityAreaId", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void addCustomerService() {
		JSONObject param=new JSONObject();
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "104");
		reqData.put("subOrderId", "29");
		reqData.put("orderDtlId", "28");
		reqData.put("serviceType", "A");
		reqData.put("contactPhone", "13655082627");
		reqData.put("reason", "测试好删除它1");
		reqData.put("quantity", "2");
		reqData.put("amount", "12.2");
		reqData.put("remarks", "测试好删除1");
		reqData.put("pic", "/201702/20170206155040_6320.png,/201702/20170206155040_6320.png,/201702/20170206155040_6320.png");
		param.put("reqData", reqData);
		param.put("token", "6813a008-1b42-416b-ac59-2db6ae30991a");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/addCustomerService", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void getCustomerServiceList() {
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "104");
		reqData.put("currentPage", "0");
		reqData.put("pageSize", "10");
		param.put("reqData", reqData);
		param.put("token", "e803612a-e070-41ac-a24f-4cd3633819f4");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getCustomerServiceList", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void getRefundInfo() {
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "112");
		reqData.put("serviceOrderId", "69");
		param.put("reqData", reqData);
		param.put("token", "387c1e51-5514-43aa-af29-138374ae6a86");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getRefundInfo", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void getNewUserEnjoyCategory() {
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("type", "7");
		param.put("reqData", reqData);
		param.put("token", "d05fcac7-651e-4a46-8924-e1af4e5e33f0");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/getNewUserEnjoyCategory", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void getRefundDetailLog() {
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "617");
		reqData.put("serviceOrderId", "298");
		param.put("reqData", reqData);
		param.put("token", "f62fe054-689a-4a7b-96ab-de4c9de8dfae");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getRefundDetailLog", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void addComplaint() {
		JSONObject param=new JSONObject();
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "104");
		reqData.put("memberName", "cwf");
		reqData.put("mchtId", "16");
		reqData.put("subOrderId", "13");
		reqData.put("orderDtlId", "17");
		reqData.put("appealType", "10");
		reqData.put("contactName", "cwf");
		reqData.put("contactPhone", "13655082627");
		reqData.put("remarks", "cwf测试投诉");
		reqData.put("pic", "/201702/20170206155040_6320.png,/201702/20170206155040_6320.png,/201702/20170206155040_6320.png");
		param.put("reqData", reqData);
		param.put("token", "ad790e94-8f1c-4164-8f3a-d1dfe2b8a5e8");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/addComplaint", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
	
	@Test
	public void getComplaintList() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "104");
		reqData.put("appealOrderId", "1");
		param.put("reqData", reqData);
		param.put("token", "b23265d0-5851-409e-a4ea-f0a844b9e554");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getComplaintList", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void addReturnInformation() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "104");
		reqData.put("serviceOrderId", "1");
		reqData.put("memberExpressCompany", "顺丰快递");
		reqData.put("memberExpressNo", "KD123456789");
		param.put("reqData", reqData);
		param.put("token", "a84bf823-4494-474c-811d-4317a7343976");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/addReturnInformation", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void addRemindSale() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "104");
		reqData.put("remindType", "2");
		reqData.put("remindId", "43");
		reqData.put("memberExpressNo", "KD123456789");
		param.put("reqData", reqData);
		param.put("token", "382e7192-e6c0-419c-8f68-871e357edbd7");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/addRemindSale", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void getRemindSaleList() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "104");
		reqData.put("remindType", "2");
		reqData.put("currentPage", "0");
		reqData.put("pageSize", "10");
		param.put("reqData", reqData);
		param.put("token", "4bcbea22-b942-431c-8df7-2dba8f773c58");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getRemindSaleList", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void getDataDic() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("tableName", "BU_APPEAL_ORDER");
		reqData.put("colName", "APPEAL_TYPE");
		param.put("reqData", reqData);
		param.put("token", "b81b5b33-bbc0-4d67-8b76-20d7ba15b49e");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/getDataDic", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void getActivityPreheatList() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "");//可以为空
		reqData.put("preheatTime", "");
		reqData.put("currentPage", "0");
		reqData.put("pageSize", "10");
		param.put("reqData", reqData);
		param.put("token", "b81b5b33-bbc0-4d67-8b76-20d7ba15b49e");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/getActivityPreheatList", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void getActivityPreheatCategory() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		param.put("reqData", reqData);
		param.put("token", "b81b5b33-bbc0-4d67-8b76-20d7ba15b49e");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/getActivityPreheatCategory", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void getMemberFavoritesList() {
		
		JSONObject param=new JSONObject();
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "104");
		param.put("reqData", reqData);
		param.put("token", "9459a3a4-510f-4ab8-884d-e265f04b2161");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/getMemberFavoritesList", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void addMemberFavorites() {
		
		JSONObject param=new JSONObject();
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "104");
		reqData.put("favoritesId", "1");
		param.put("reqData", reqData);
		param.put("token", "f98450c1-2620-4d69-8dbd-be7950993853");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/addMemberFavorites", requestP));
		System.out.println(result.toString());
	}
	
	
	@Test
	public void getProductByActiviTyId() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "");
		reqData.put("activityId", "54");
		reqData.put("suitSex", "");
		reqData.put("suitGroup", "");
		reqData.put("productTypeId", "");
		reqData.put("salePriceMin", "");
		reqData.put("salePriceMax", "");
		reqData.put("salePriceSort", "");
		reqData.put("discountSort", "");
		reqData.put("stockMark", "");
		reqData.put("currentPage", "0");
		reqData.put("pageSize", "10");
		param.put("reqData", reqData);
		param.put("token", "b81b5b33-bbc0-4d67-8b76-20d7ba15b49e");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/getProductByActiviTyId", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void getProductScreeningConditions() {
		
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("activityId", "54");
		param.put("reqData", reqData);
		param.put("token", "b81b5b33-bbc0-4d67-8b76-20d7ba15b49e");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/getProductScreeningConditions", requestP));
		System.out.println(result.toString());
	}
	
	
	@Test
	public void addOrderInfo() {
		
		JSONObject param=new JSONObject();
		JSONArray dataList = new JSONArray();
		JSONObject data =new JSONObject();
		data.put("activityAreaId", "87");
		data.put("couponId", "");//15
		data.put("shopCardId", "10,11,12");
		dataList.add(data);
		data.put("activityAreaId", "101");
		data.put("couponId", "");//16
		data.put("shopCardId", "13");
		dataList.add(data);
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "112");
		reqData.put("memberNick", "cwf");
		reqData.put("sourceClient", "1");
		reqData.put("remarks", "测试");
		reqData.put("dataList", dataList);
		reqData.put("shopCardIds", "10,11,12,13");
		reqData.put("mermberPlatformCouponId", "");
		param.put("reqData", reqData);
		param.put("token", "f49534f6-f4d7-4912-b0d7-242022da1b76");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/addOrderInfo", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void orderPayNotify() {
		
		JSONObject param=new JSONObject();
	
		//特定参数
		JSONObject reqData=new JSONObject();
		
		param.put("reqData", reqData);
		param.put("token", "f49534f6-f4d7-4912-b0d7-242022da1b76");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/orderPayNotify", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void orderPayment() {
		
		JSONObject param=new JSONObject();
	
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "112");
		param.put("reqData", reqData);
		param.put("token", "24a2cd64-92a5-43a4-ac5c-ef85afe78e50");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/orderPayment", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void getExpressInfo() {
		
		JSONObject param=new JSONObject();
	
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "1");
		reqData.put("expressId", "5");
		reqData.put("expressNo", "885126163773620588");
		param.put("reqData", reqData);
		param.put("token", "1ca58bd1-db13-4bef-8b6f-d6a663231a94");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getExpressInfo", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void getPayOrderInfo() {
		
		JSONObject param=new JSONObject();
	
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "1");
		reqData.put("shopCardIds", "10,11,12,13");
		reqData.put("payAmount", "20");
		param.put("reqData", reqData);
		param.put("token", "707a6ba0-3c28-45cd-a168-0c06ab9181e8");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/gerPayOrderInfo", requestP));
		System.out.println(result.toString());
	}
	@Test
	public void submitOrderPayment() {
		
		JSONObject param=new JSONObject();
		JSONArray dataList = new JSONArray();
		JSONObject data =new JSONObject();
		data.put("activityAreaId", "87");
		data.put("couponId", "15");//15
		data.put("shopCardId", "10,11,12");
		dataList.add(data);
		data.put("activityAreaId", "101");
		data.put("couponId", "16");//16
		data.put("shopCardId", "13");
		dataList.add(data);
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "112");
		reqData.put("memberNick", "cwf");
		reqData.put("sourceClient", "1");
		reqData.put("remarks", "测试");
		reqData.put("dataList", dataList);
		reqData.put("shopCardIds", "10,11,12,13");
		reqData.put("mermberPlatformCouponId", "");
		reqData.put("isUserIntegral", false);
		reqData.put("integral", "0");
		reqData.put("payAmount", "10");
		reqData.put("payType", "ZFB");
		reqData.put("ip", "192.168.1.11");
		reqData.put("addressId", "75");
		param.put("reqData", reqData);
		param.put("token", "cbb48b19-397c-4c0f-b975-d48c79186f95");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/submitOrderPayment", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void getAppMemberMessage() {
		
		JSONObject param=new JSONObject();
	
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "112");
		param.put("reqData", reqData);
		param.put("token", "81a1dff7-c72e-471b-b78b-27fe130d1158");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getAppMemberMessage", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void getAppMemberMessageByType() {
		
		JSONObject param=new JSONObject();
	
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "112");
		reqData.put("type", "1");
		reqData.put("currentPage", "0");
		reqData.put("pageSize", "10");
		param.put("reqData", reqData);
		param.put("token", "81a1dff7-c72e-471b-b78b-27fe130d1158");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getAppMemberMessageByType", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void getPayType() {
		
		JSONObject param=new JSONObject();
	
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "112");
		param.put("reqData", reqData);
		param.put("token", "ae034cff-1d00-4e64-b65c-4e1c2ddb5bd2");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getPayType", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void test() {
		
		JSONObject param=new JSONObject();
	
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "112");
		param.put("reqData", reqData);
		param.put("token", "ae034cff-1d00-4e64-b65c-4e1c2ddb5bd2");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/test", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void getEffectiveSku() {
		
		JSONObject param=new JSONObject();
	
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "244");
		reqData.put("productItemId", "339");
		reqData.put("productId", "2");
		param.put("reqData", reqData);
		param.put("token", "207d293b-8632-44da-88da-aca2c978a4d3");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getEffectiveSku", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void getShoppingCartNum() {
		
		JSONObject param=new JSONObject();
	
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "244");
		param.put("reqData", reqData);
		param.put("token", "207d293b-8632-44da-88da-aca2c978a4d3");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getShoppingCartNum", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void addReceiveCoupon() {
		
		JSONObject param=new JSONObject();
	
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "244");
		reqData.put("couponId", "250");
		param.put("reqData", reqData);
		param.put("token", "a0948b30-594a-46b1-825b-f77072845e40");
		param.put("version", "3");
		param.put("system", "2");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/addReceiveCoupon", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void getConfigDataType() {
		
		JSONObject param=new JSONObject();
	
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("code", "APP_GUIDE_PIC");
		param.put("reqData", reqData);
		param.put("token", "a0948b30-594a-46b1-825b-f77072845e40");
		param.put("version", "3");
		param.put("system", "2");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/getConfigDataType", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void addCouponAndView() {
		
		JSONObject param=new JSONObject();
	
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "244");
		param.put("reqData", reqData);
		param.put("token", "b3194c03-6a99-4dd7-bb57-963878af5714");
		param.put("version", "3");
		param.put("system", "2");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/addCouponAndView", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void test1() {
		
		JSONObject param=new JSONObject();
	
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "244");
		param.put("reqData", reqData);
		param.put("token", "b3194c03-6a99-4dd7-bb57-963878af5714");
		param.put("version", "3");
		param.put("system", "2");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/test1", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void getUserOrderCount() {
		
		JSONObject param=new JSONObject();
	
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "244");
		param.put("reqData", reqData);
		param.put("token", "fd94c6dc-7fa1-459c-8dee-b992288e863b");
		param.put("version", "3");
		param.put("system", "2");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getUserOrderCount", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void test123() {
		
		JSONObject param=new JSONObject();
	
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "244");
		param.put("reqData", reqData);
		param.put("token", "fd94c6dc-7fa1-459c-8dee-b992288e863b");
		param.put("version", "3");
		param.put("system", "2");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/n/test123", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void getSobotInfo() {
		
		JSONObject param=new JSONObject();
	
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "617");
		reqData.put("type", "1");
		reqData.put("id", "1");
		param.put("reqData", reqData);
		param.put("token", "e6319003-48bf-45e2-95e8-edc1439dffad");
		param.put("version", "3");
		param.put("system", "2");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/appService/api/y/getSobotInfo", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void testL() {
		
		Integer iChargr = 100;
		Integer mint=100;
		double payAmount=1109.00;
		//获取用户积分
		double integralAmount = 0.00;
		//将积分转换为元
		integralAmount = mint.doubleValue()/iChargr.doubleValue();

		payAmount = payAmount * 0.5;
		if (payAmount <= integralAmount) {
			integralAmount = payAmount;
		}
		//可以使用的积分*(预防积分出现小数，把积分抵扣金额再次转化为积分)
		Integer integral = (int)(integralAmount * iChargr);
		//将积分转换为元
		integralAmount = integral.doubleValue()/iChargr.doubleValue();
		System.out.println(integralAmount);
	}
	
	@Test
	public void getNewUserSeckillTimeList() {
		
		JSONObject param=new JSONObject();
	
		//特定参数
		JSONObject reqData=new JSONObject();
		
		reqData.put("pageSize", 10);
		reqData.put("currentPage", 0);
		
		param.put("reqData", reqData);
		param.put("version", "3");
		param.put("system", "2");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/jfAppService/api/n/getNewUserSeckillTimeList", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void getAdInfos() {
		
		JSONObject param=new JSONObject();
		
		//特定参数
		JSONObject reqData=new JSONObject();
		
		reqData.put("catalog", 2);
		reqData.put("position", 1);
		
		param.put("reqData", reqData);
		param.put("version", "3");
		param.put("system", "2");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/jfAppService/api/n/getAdInfos", requestP));
		System.out.println(result.toString());
	}
	
	@Test
	public void getSingleNewEnjoyActivityContent() {
		
		JSONObject param=new JSONObject();
		
		//特定参数
		JSONObject reqData=new JSONObject();
		
		param.put("reqData", reqData);
		param.put("version", "3");
		param.put("system", "2");
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8080/jfAppService/api/n/getSingleNewEnjoyActivityContent", requestP));
		System.out.println(result.toString());
	}

	@Test
	public void queryLogisticByDate() {

		JSONObject param=new JSONObject();

		//特定参数
		JSONObject reqData=new JSONObject();

		reqData.put("beginDate", "2020-02-01 00:00:00");
		reqData.put("endDate", "2020-03-05 23:59:59");
		param.put("reqData",reqData);
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://msg.xgbuy.cc/msgService/api/kdn/queryLogisticByDate", requestP));
		System.out.println(result.toString());
	}

	
}
