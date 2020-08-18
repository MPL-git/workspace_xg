package sys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月22日 下午5:24:36 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class TestPust {
	//在极光注册上传应用的 appKey 和 masterSecret  
	private static final String appKey = "aa91494b4f60f7f3e362fa7c";
	private static final String masterSecret = "c6dfd7657ee428c0472777cd";
	public static void main(String[] args) throws ParseException {
		List<String> aliasList = new ArrayList<String>();
		aliasList.add("302");
		sendAlias("这是java后台发送的一个按照alia的通知", aliasList);
	}
	
	
	
	
	private static void sendAlias(String msg,List<String> aliasList){
		JPushClient jPushClient = new JPushClient(masterSecret, appKey);
		Map<String, String> extras = new HashMap<String, String>();
		extras.put("extMsg1", "我是消息1");
		extras.put("extMsg2", "我是消息2");
		PushPayload pushPayload = allPlatformAndAlias(msg,aliasList, extras);
		try {
			PushResult result = jPushClient.sendPush(pushPayload);
			System.out.println(result);
		} catch (APIConnectionException e) {
			System.out.println(e);
		} catch (APIRequestException e) {
			System.out.println(e);
			System.out.println("Error response from JPush server. Should review and fix it. "+e);
			System.out.println("HTTP Status: "+e.getMessage());
			System.out.println("Error Code: "+e.getErrorCode());
			System.out.println("Error Message: "+e.getErrorMessage());
			System.out.println("Msg ID: "+e.getMsgId());
		}
	}
	
	private static PushPayload allPlatformAndAlias(String alert,List<String> aliasList,Map<String, String> extras){
		
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(
							Notification
										.newBuilder()
										.setAlert(alert)
										.addPlatformNotification(
												AndroidNotification.newBuilder().addExtras(extras).build())
										.addPlatformNotification(
												IosNotification.newBuilder().addExtras(extras).build())
										.build())
				.setOptions(Options.newBuilder().setApnsProduction(false).build()).build();
	}
		
	private static PushPayload buildAndroid() {

		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias("302"))
				.setNotification(Notification.newBuilder()
						.addPlatformNotification(AndroidNotification.newBuilder()
								.setAlert("黄汝辉你个傻逼250")
								/*.setSound("happy")*/
								.addExtra("issuseId", "111")
								.addExtra("type", "我是问题")
								.build()).build())
				.setMessage(Message.content("abcdefg"))
				.setOptions(Options.newBuilder()
						.setApnsProduction(false)
						.build())
				.build();
	}

	private static PushPayload buildIos() {
		
		return PushPayload.newBuilder()
				.setPlatform(Platform.ios())
				.setAudience(Audience.alias("153"))
				.setNotification(Notification.newBuilder()
						.addPlatformNotification(IosNotification.newBuilder()
								.setAlert("大家好,黄汝辉你个傻逼250")
								.setSound("happy")
								.addExtra("linkType", "5")
								.addExtra("linkId", "53")
								.build()).build())
				.setMessage(Message.content("abcdefg"))
				.setOptions(Options.newBuilder()
						.setApnsProduction(false)
						.build())
				.build();
	}
	
	public static String getRealIp() throws SocketException {  
        String localip = null;// 本地IP，如果没有配置外网IP则返回它  
        String netip = null;// 外网IP  
  
        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();  
        InetAddress ip = null;  
        boolean finded = false;// 是否找到外网IP  
        while (netInterfaces.hasMoreElements() && !finded) {  
            NetworkInterface ni = netInterfaces.nextElement();  
            Enumeration<InetAddress> address = ni.getInetAddresses();  
            while (address.hasMoreElements()) {  
                ip = address.nextElement();  
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP  
                    netip = ip.getHostAddress();  
                    finded = true;  
                    break;  
                } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()  
                        && ip.getHostAddress().indexOf(":") == -1) {// 内网IP  
                    localip = ip.getHostAddress();  
                }  
            }  
        }  
  
        if (netip != null && !"".equals(netip)) {  
            return netip;  
        } else {  
            return localip;  
        }  
    } 
	
	
	private static String getMyIP() throws IOException {  
        InputStream ins = null;  
        try {  
            URL url = new URL("http://www.ip138.com");  
            URLConnection con = url.openConnection();  
            ins = con.getInputStream();  
            InputStreamReader isReader = new InputStreamReader(ins, "UTF-8");  
            BufferedReader bReader = new BufferedReader(isReader);  
            StringBuffer webContent = new StringBuffer();  
            String str = null;  
            while ((str = bReader.readLine()) != null) {  
                webContent.append(str);  
            }  
            int start = webContent.indexOf("[") + 1;  
            int end = webContent.indexOf("]");  
            System.out.println(webContent);
            return webContent.substring(start, end);  
        } finally {  
            if (ins != null) {  
                ins.close();  
            }  
        }  
    }  
  
    private static String getMyIPLocal() throws IOException {  
        InetAddress ia = InetAddress.getLocalHost();  
        return ia.getHostAddress();  
    } 

    
	private static String getMacOnWindow() {
		String s = "";
		try {
			String s1 = "CMD";
			Process process = Runtime.getRuntime().exec(s1);
			BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(process.getInputStream(),"GB2312"));
			String nextLine;
			for (String line = bufferedreader.readLine(); line != null; line = nextLine) {
				nextLine = bufferedreader.readLine();
				System.out.println(nextLine);
				if (line.indexOf("Physical   Address") <= 0) {
					continue;
				}
				int i = line.indexOf("Physical   Address") + 36;
				s = line.substring(i);
				break;
			}

			bufferedreader.close();
			process.waitFor();
		} catch (Exception exception) {
			s = "";
		}
		return s.trim();
	}


}
