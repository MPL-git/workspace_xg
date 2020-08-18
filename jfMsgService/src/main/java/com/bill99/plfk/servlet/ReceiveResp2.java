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

public class ReceiveResp2 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * servlet销毁
	 */
	public void destroy() {
		super.destroy(); 
	}

	/**
	 *调用doPost()
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		InputStream is=request.getInputStream();
//		BufferedReader br = new BufferedReader(new InputStreamReader(is));
//		String rdline="";
//		while((rdline=br.readLine())!=null){
//			System.out.println(rdline);
//		}
		HashMap<String,String> hm=PaseXMLUtil.paseXML(is);
		String version=hm.get("version");
		String batchNo=hm.get("batchNo");
		String fileName=hm.get("fileName");
		String requestTime=hm.get("requestTime");
		
		BatchSettlementApplyResponse responseObject=null;
		CustomerTool ct=new CustomerTool();
		DealInfoEntity dealInfo=new DealInfoEntity();
		if(fileName!=null && !"".equals(fileName)){
			dealInfo.setMemberCode("10011629536");
			dealInfo.setFeatureCode("F888");
			System.out.println("开始下载返盘文件......");
			responseObject=ct.downFile_ftp(fileName,"downloadPath",dealInfo);
			if(responseObject!=null){
				List<Pay2bankResultType> pay2bankLists=responseObject.getResponseBody().getPay2bankLists();
				for(Pay2bankResultType payResult:pay2bankLists)
					System.out.println("订单号："+payResult.getPay2bank().getMerchantId());
				System.out.println("下载到的订单数为："+pay2bankLists.size());
				System.out.println("下载到的信息批次号："+responseObject.getResponseBody().getBatchNo());
				System.out.println("下载到的该批次处理状态："+responseObject.getResponseBody().getStatus());
				System.out.println("返盘文件下载及解析文件结束");
			}else{
				System.out.println("返盘文件下载并解析数据失败");
			}
		}
	}

	/**
	 * servlet初始化
	 */
	public void init() throws ServletException {
		System.out.println("初始化receive2....");
	}

	/**
	 * 创建应答XML
	 * 
	 * */
//	public String createResponse(String file, String status,
//			String batchid, String version, String digitalEnvelope,
//			String encryptedMemberCode, String signedMemberCode) {
//		String start = "<?xml version=\"1.0\" encoding=\"utf-8\"?><bpResponse>";
//		String end = "</bpResponse>";
//		StringBuffer sb = new StringBuffer();
//		sb.append(start);
//		sb.append("<memberCode>" + "membercode" + "</memberCode>");
//		sb.append("<fileName>" + file + "</fileName>");
//		sb.append("<status>" + status + "</status>");
//		sb.append("<batchNo>" + batchid + "</batchNo>");
//		sb.append("<version>" + version + "</version>");
//		sb.append("<digitalEnvelope>" + digitalEnvelope + "</digitalEnvelope>");
//		sb.append("<encryptedMemberCode>" + encryptedMemberCode + "</encryptedMemberCode>");
//		sb.append("<signedMemberCode>" + signedMemberCode + "</signedMemberCode>");
//		sb.append(end);
//		return sb.toString();
//	}
	
}
