
package com.single.payment;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.net.ssl.SSLContext;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.single.payment.dto.Pay2bankOrder;
import com.single.payment.dto.Pay2bankRequest;
import com.single.payment.dto.Pay2bankResponse;
import com.single.payment.util.CCSUtil;
import com.single.payment.util.PKIUtil;

import com.bill99.asap.exception.CryptoException;
import com.bill99.asap.service.ICryptoService;
import com.bill99.asap.service.impl.CryptoServiceFactory;
import com.bill99.schema.asap.commons.Mpf;
import com.bill99.schema.asap.data.SealedData;
import com.bill99.schema.asap.data.UnsealedData;

public class Pay2bankTestForSt2 {
	
	//接口版本
	private static String VERSION = "1.0";

	//字符编码
	private static String encoding = "UTF-8";
	
	//服务端URL
	private static String URL = "https://www.99bill.com/fo-pay/pay2bank/pay";  //生产地址
//	private static String URL = "https://sandbox.99bill.com/fo-pay/pay2bank/pay"; //测试地址
	//测试账户信息
//	private static String membercode = "10012138842";
	private static String membercode = "10210555915";
	//策略编码，固定值 F41
	private static String fetureCode = "F41";
//	private static String fetureCode = "F889";
	
	public static void main(String[] args) throws Exception {
		//生成pki加密报文
		String pkiMsg = test_genPKIMsg();
		String sealMsg = test_invokeCSSCollection(pkiMsg);
		//返回的加密报文解密
		test_unsealMsg(sealMsg);
	}
	
	public static void test_unsealMsg(String msg) throws Exception {
		System.out.println("加密返回报文 = " + msg);
		Pay2bankResponse response = CCSUtil.converyToJavaBean(msg, Pay2bankResponse.class);
		System.out.println(response.getResponseBody().getErrorCode());
		response.getResponseBody().getErrorCode().equals("0000");
		SealedData sealedData = new SealedData();
		sealedData.setOriginalData(response.getResponseBody().getSealDataType().getOriginalData()==null?null:PKIUtil.utf8String2ByteWithBase64(response.getResponseBody().getSealDataType().getOriginalData()));
		sealedData.setSignedData(response.getResponseBody().getSealDataType().getSignedData()==null?null:PKIUtil.utf8String2ByteWithBase64(response.getResponseBody().getSealDataType().getSignedData()));
		sealedData.setEncryptedData(response.getResponseBody().getSealDataType().getEncryptedData()==null?null:PKIUtil.utf8String2ByteWithBase64(response.getResponseBody().getSealDataType().getEncryptedData()));
		sealedData.setDigitalEnvelope(response.getResponseBody().getSealDataType().getDigitalEnvelope()==null?null:PKIUtil.utf8String2ByteWithBase64(response.getResponseBody().getSealDataType().getDigitalEnvelope()));
		Mpf mpf = genMpf(fetureCode , membercode);
		UnsealedData unsealedData = null; 
		try {
			ICryptoService service = CryptoServiceFactory.createCryptoService();
			unsealedData = service.unseal(mpf, sealedData);
		} catch (CryptoException e) {
			System.out.println(e);
		}
		byte[] decryptedData = unsealedData.getDecryptedData();
		if (null != decryptedData) {
			String rtnString = PKIUtil.byte2UTF8String(decryptedData);
			System.out.println("解密后返回报文 = " + rtnString);
		} else {
			String  rtnString = PKIUtil.byte2UTF8String(sealedData.getOriginalData());
			System.out.println("解密后返回报文 = " + rtnString);
		}
	}
	
	public static String test_genPKIMsg() {
		//构建一个订单对象
		Pay2bankOrder orderDto = CCSUtil.genOrder();
		String orderXml = CCSUtil.convertToXml(orderDto, encoding);
		System.out.println("请求明文报文 = " + orderXml);
		//获取原始报文
		String originalStr = orderXml;
		//加签、加密
		Mpf mpf = genMpf(fetureCode , membercode);
		SealedData sealedData = null;
		try {
			ICryptoService service = CryptoServiceFactory.createCryptoService();
			sealedData = service.seal(mpf, originalStr.getBytes());
		} catch (CryptoException e) {
			System.out.println(e);
		}
		Pay2bankRequest request = CCSUtil.genRequest(membercode , VERSION);
		byte[] nullbyte = {};
		byte[] byteOri = sealedData.getOriginalData() == null ? nullbyte : sealedData.getOriginalData();
		byte[] byteEnc = sealedData.getEncryptedData() == null ? nullbyte : sealedData.getEncryptedData();
		byte[] byteEnv = sealedData.getDigitalEnvelope() == null ? nullbyte : sealedData.getDigitalEnvelope();
		byte[] byteSig = sealedData.getSignedData() == null ? nullbyte : sealedData.getSignedData();
		request.getRequestBody().getSealDataType().setOriginalData(PKIUtil.byte2UTF8StringWithBase64(byteOri));
		//获取加签报文
		request.getRequestBody().getSealDataType().setSignedData(PKIUtil.byte2UTF8StringWithBase64(byteSig));
//		//获取加密报文
		request.getRequestBody().getSealDataType().setEncryptedData(PKIUtil.byte2UTF8StringWithBase64(byteEnc));
//		//数字信封
		request.getRequestBody().getSealDataType().setDigitalEnvelope(PKIUtil.byte2UTF8StringWithBase64(byteEnv));
		
		//请求报文
		String requestXml = CCSUtil.convertToXml(request, encoding);
		System.out.println("请求加密报文 = " + requestXml);
		return requestXml;
	}
	
	
	public static String test_invokeCSSCollection(String requestXml) throws Exception {
		//初始化HttpClient
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(URL);
		SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
	        sslContext.init(null, null, null);
	        SSLContext.setDefault(sslContext);
		//请求服务端
//		InputStream in_withcode = new ByteArrayInputStream(requestXml.getBytes(encoding));
//		method.setRequestBody(in_withcode);
		// url的连接等待超时时间设置  
        client.getHttpConnectionManager().getParams().setConnectionTimeout(2000);  

        // 读取数据超时时间设置  
        client.getHttpConnectionManager().getParams().setSoTimeout(3000);  
		method.setRequestEntity(new StringRequestEntity(requestXml, "text/html", "utf-8"));
		client.executeMethod(method);
		
		//打印服务器返回的状态
		System.out.println(method.getStatusLine());
		
		//打印返回的信息
		InputStream stream = method.getResponseBodyAsStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(stream,encoding));
		StringBuffer buf = new StringBuffer();
		String line;
		while (null != (line = br.readLine())) {
			buf.append(line).append("\n");
		}
		//释放连接
		method.releaseConnection();
		return buf.toString();
	}
	
	
	public static Mpf genMpf(String fetureCode, String membercode) {
		Mpf mpf = new Mpf();
		mpf.setFeatureCode(fetureCode);
		mpf.setMemberCode(membercode);
		return mpf;
	}
}
