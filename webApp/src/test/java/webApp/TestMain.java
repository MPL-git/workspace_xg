package webApp;

import org.junit.Test;

import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.HttpUtil;

import net.sf.json.JSONObject;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年8月3日 上午9:21:53 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class TestMain {
	@Test
	public void getProductInfoList1() {
		JSONObject param=new JSONObject();
		
		
		
		//特定参数
		JSONObject reqData=new JSONObject();
		reqData.put("memberId", "244");
		reqData.put("id", "58");
		reqData.put("activityAreaId", "122");
		param.put("reqData", reqData);
		param.put("token", "6806177e-b2cb-4890-b119-abd935748f7b");
		param.put("version", "3");
		param.put("system", "2");
		
		String requestP=CommonUtil.createReqData(param).toString();
		System.out.println(requestP);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest("http://127.0.0.1:8081/webApp/api/n/getProductInfoList", CommonUtil.createReqData(param).toString()));
		System.out.println(result.toString());
	}
}
