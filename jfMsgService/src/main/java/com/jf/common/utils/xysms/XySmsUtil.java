package com.jf.common.utils.xysms;

import com.jf.controller.SmsController;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XySmsUtil {

	private static final Logger logger = Logger.getLogger(SmsController.class);

	/**
	 * webservice服务器定义
	 */
	private static String serviceURL = "http://yd10086.cn:8070/Service.asmx";

	/**
	 * 营销账号
	 */
	public static String xy_task_corporateid;
	public static String xy_task_userid;
	public static String xy_task_password;
	/**
	 * 系统账号
	 */
	public static String xy_sys_corporateid;
	public static String xy_sys_userid;
	public static String xy_sys_password;


	static {
		try {
			Configuration configuration = new PropertiesConfiguration("sms_config.properties");

			xy_task_corporateid = configuration.getString("xy_task_sms_account");
			xy_task_userid = configuration.getString("xy_task_sms_username");
			xy_task_password = configuration.getString("xy_task_sms_send_interface_pwd");

			xy_sys_corporateid = configuration.getString("xy_account");
			xy_sys_userid = configuration.getString("xy_username");
			xy_sys_password = configuration.getString("xy_send_interface_pwd");
			
		} catch (Exception e) {
			throw new RuntimeException("[加载配置文件失败]", e);
		}
	}

	/**
	 * 方法名称：GetXyBal
	 * 功    能：获取余额
	 * 参    数：无
	 * @return :余额（String）
	 */
	public static String GetXyBal() {
		String result = "";
		String soapAction = "http://tempuri.org/GetBal";
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
		xml += "<soap:Body>";
		xml += "<GetBal xmlns=\"http://tempuri.org/\">";
		xml += "<corporateid>" + xy_task_corporateid + "</corporateid>";
		xml += "<userid>" + xy_task_userid + "</userid>";
		xml += "<password>" + xy_task_password + "</password>";
		xml += "</GetBal>";
		xml += "</soap:Body>";
		xml += "</soap:Envelope>";

		URL url;
		try {
			url = new URL(serviceURL);

			URLConnection connection = url.openConnection();
			HttpURLConnection httpconn = (HttpURLConnection) connection;
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			bout.write(xml.getBytes());
			byte[] b = bout.toByteArray();
			httpconn.setRequestProperty("Content-Length", String
					.valueOf(b.length));
			httpconn.setRequestProperty("Content-Type",
					"text/xml; charset=utf-8");
			httpconn.setRequestProperty("SOAPAction", soapAction);
			httpconn.setRequestMethod("POST");
			httpconn.setDoInput(true);
			httpconn.setDoOutput(true);

			OutputStream out = httpconn.getOutputStream();
			out.write(b);
			out.close();

			InputStreamReader isr = new InputStreamReader(httpconn
					.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			String inputLine;
			while (null != (inputLine = in.readLine())) {
				Pattern pattern = Pattern
						.compile("<GetBalResult>(.*)</GetBalResult>");
				Matcher matcher = pattern.matcher(inputLine);
				while (matcher.find()) {
					result = matcher.group(1);
				}
			}
			in.close();
			return new String(result.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

    /**
     * 方法名称：SendSysXySms(发送系统短信)
     * @param mobile :短信发送的目的号码.多个号码之间用半角逗号隔开。必填(支持10000个手机号,建议<=5000)多个英文逗号隔开
     * @param content :短信的内容，内容需要UTF-8编码支持长短信
     * @param subcode :定时字段:参数形式 2016-8-4 08:26:55如果实时发送请置为空
     * @param schtime :子号。例如：123（默认置空）请先询问配置的通道是否支持扩展子号，如果不支持，请填空。子号只能为数字，且最多5位数。
     * @return 唯一标识 :rrid将返回系统生成的
     */
    public static String SendSysXySms(String mobile, String content, String subcode, String schtime) {
        String result = "";
        String soapAction = "http://tempuri.org/SendSmsNew";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
        xml += "<soap:Body>";
        xml += "<SendSmsNew xmlns=\"http://tempuri.org/\">";
        xml += "<corporateid>" + xy_sys_corporateid + "</corporateid>";
        xml += "<userid>" + xy_sys_userid + "</userid>";
        xml += "<password>" + xy_sys_password + "</password>";
        xml += "<mobile>" + mobile + "</mobile>";
        xml += "<content>" + content + "</content>";
        xml += "<subcode>" + subcode + "</subcode>";
        xml += "<schtime>" + schtime + "</schtime>";
        xml += "</SendSmsNew>";
        xml += "</soap:Body>";
        xml += "</soap:Envelope>";

        URL url;
        try {
            url = new URL(serviceURL);

            URLConnection connection = url.openConnection();
            HttpURLConnection httpconn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            bout.write(xml.getBytes());
            byte[] b = bout.toByteArray();
            httpconn.setRequestProperty("Content-Length", String
                    .valueOf(b.length));
            httpconn.setRequestProperty("Content-Type",
                    "text/xml; charset=utf-8");
            httpconn.setRequestProperty("SOAPAction", soapAction);
            httpconn.setRequestMethod("POST");
            httpconn.setDoInput(true);
            httpconn.setDoOutput(true);

            OutputStream out = httpconn.getOutputStream();
            out.write(b);
            out.close();

            InputStreamReader isr = new InputStreamReader(httpconn
                    .getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String inputLine;
            while (null != (inputLine = in.readLine())) {
                Pattern pattern = Pattern.compile("<SendSmsNewResult>(.*)</SendSmsNewResult>");
                Matcher matcher = pattern.matcher(inputLine);
                while (matcher.find()) {
                    result = matcher.group(1);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

	/**
	 * 方法名称：SendXySms(发送短信)
	 * @param mobile :短信发送的目的号码.多个号码之间用半角逗号隔开。必填(支持10000个手机号,建议<=5000)多个英文逗号隔开
	 * @param content :短信的内容，内容需要UTF-8编码支持长短信
	 * @param subcode :定时字段:参数形式 2016-8-4 08:26:55如果实时发送请置为空
	 * @param schtime :子号。例如：123（默认置空）请先询问配置的通道是否支持扩展子号，如果不支持，请填空。子号只能为数字，且最多5位数。
	 * @return 唯一标识 :rrid将返回系统生成的
	 */
	public static String SendXySms(String mobile, String content, String subcode, String schtime) {
		String result = "";
		String soapAction = "http://tempuri.org/SendSmsNew";
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
		xml += "<soap:Body>";
		xml += "<SendSmsNew xmlns=\"http://tempuri.org/\">";
		xml += "<corporateid>" + xy_task_corporateid + "</corporateid>";
		xml += "<userid>" + xy_task_userid + "</userid>";
		xml += "<password>" + xy_task_password + "</password>";
		xml += "<mobile>" + mobile + "</mobile>";
		xml += "<content>" + content + "</content>";
		xml += "<subcode>" + subcode + "</subcode>";
		xml += "<schtime>" + schtime + "</schtime>";
		xml += "</SendSmsNew>";
		xml += "</soap:Body>";
		xml += "</soap:Envelope>";

		URL url;
		try {
			url = new URL(serviceURL);

			URLConnection connection = url.openConnection();
			HttpURLConnection httpconn = (HttpURLConnection) connection;
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			bout.write(xml.getBytes());
			byte[] b = bout.toByteArray();
			httpconn.setRequestProperty("Content-Length", String
					.valueOf(b.length));
			httpconn.setRequestProperty("Content-Type",
					"text/xml; charset=utf-8");
			httpconn.setRequestProperty("SOAPAction", soapAction);
			httpconn.setRequestMethod("POST");
			httpconn.setDoInput(true);
			httpconn.setDoOutput(true);

			OutputStream out = httpconn.getOutputStream();
			out.write(b);
			out.close();

			InputStreamReader isr = new InputStreamReader(httpconn
					.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			String inputLine;
			while (null != (inputLine = in.readLine())) {
				Pattern pattern = Pattern.compile("<SendSmsNewResult>(.*)</SendSmsNewResult>");
				Matcher matcher = pattern.matcher(inputLine);
				while (matcher.find()) {
					result = matcher.group(1);
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 方法名称：GetMo
	 * 功    能：接收短信
	 * 参    数：无
	 * @return : 接收到的信息
	 */
	public static String GetMo() {
		String result = "";
		String soapAction = "http://tempuri.org/GetMo";
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
		xml += "<soap:Body>";
		xml += "<GetMo xmlns=\"http://tempuri.org/\">";
		xml += "<corporateid>" + xy_task_corporateid + "</corporateid>";
		xml += "<userid>" + xy_task_userid + "</userid>";
		xml += "<password>" + xy_task_password + "</password>";
		xml += "</GetMo>";
		xml += "</soap:Body>";
		xml += "</soap:Envelope>";

		URL url;
		try {
			url = new URL(serviceURL);

			URLConnection connection = url.openConnection();
			HttpURLConnection httpconn = (HttpURLConnection) connection;
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			bout.write(xml.getBytes());
			byte[] b = bout.toByteArray();
			httpconn.setRequestProperty("Content-Length", String
					.valueOf(b.length));
			httpconn.setRequestProperty("Content-Type",
					"text/xml; charset=utf-8");
			httpconn.setRequestProperty("SOAPAction", soapAction);
			httpconn.setRequestMethod("POST");
			httpconn.setDoInput(true);
			httpconn.setDoOutput(true);

			OutputStream out = httpconn.getOutputStream();
			out.write(b);
			out.close();

			InputStreamReader isr = new InputStreamReader(httpconn
					.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			String inputLine;
			while (null != (inputLine = in.readLine())) {
				Pattern pattern = Pattern.compile("<GetMoResult>(.*)</GetMoResult>");
				Matcher matcher = pattern.matcher(inputLine);
				while (matcher.find()) {
					result = matcher.group(1);
				}
			}
			return result;


		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
