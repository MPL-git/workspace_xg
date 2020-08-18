package com.bill99.plfk.servlet;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bill99.plfk.api.CustomerTool;
import com.bill99.plfk.entity.DealInfoEntity;
import com.bill99.plfk.util.PaseXMLUtil;
import com.bill99.schema.fo.settlement.BatchSettlementApplyResponse;
import com.bill99.schema.fo.settlement.Pay2bankResultType;


public class ReceiveResp1 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); 
	}

	/**
	 * 调用doPost()做逻辑处理
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * 函数用途：处理返回的应答信息
	 * @param request 请求信息
	 * @param response 应答信息
	 * @return void
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		System.out.println("消息来了");
		InputStream is=request.getInputStream();
//		BufferedReader br = new BufferedReader(new InputStreamReader(is));
//		String rdline="";
//		while((rdline=br.readLine())!=null){
//			System.out.println(rdline);
//		}
		//取出XML中的数据
		HashMap<String,String> hm=PaseXMLUtil.paseXML(is);
		String version=hm.get("version");
		String memberCode=hm.get("memberCode");
		String fileName=hm.get("fileName");
		String ext=hm.get("ext");
		String status=hm.get("status");
		String errorCode=hm.get("errorCode");
		String errorMsg=hm.get("errorMsg");
		String requestTime=hm.get("requestTime");
		String receiveTime=hm.get("receiveTime");
		
		BatchSettlementApplyResponse responseObject=null;
		CustomerTool ct=new CustomerTool();
		DealInfoEntity dealInfo=new DealInfoEntity();
		if(fileName!=null && !"".equals(fileName)){
			dealInfo.setMemberCode(memberCode);
			dealInfo.setFeatureCode("F888");
			System.out.println("开始下载申请回执文件......");
			responseObject=ct.downFile_ftp(fileName,"uploadPath",dealInfo);
			if(responseObject!=null){
				List<Pay2bankResultType> pay2bankLists=responseObject.getResponseBody().getPay2bankLists();
				for(Pay2bankResultType payResult:pay2bankLists)
					System.out.println("订单号："+payResult.getPay2bank().getMerchantId());
				System.out.println("下载到的订单数为："+pay2bankLists.size());
				System.out.println("下载到的信息批次号："+responseObject.getResponseBody().getBatchNo());
				System.out.println("下载到的该批次处理状态："+responseObject.getResponseBody().getStatus());
				System.out.println("回执文件下载及解析文件结束");
			}else{
				System.out.println("回执文件下载并解析数据失败");
			}
		}
	}

	/**
	 * 初始化类
	 */
	public void init() throws ServletException {
		System.out.println("初始化receive1....");
	}

}
