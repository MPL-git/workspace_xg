package com.jf.common.utils;

import java.security.MessageDigest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Component;

import com.jf.entity.ViolateWord;
import com.jf.entity.ViolateWordExample;
import com.jf.service.ViolateWordService;
@Component
public class StringUtils {
	
	private static ViolateWordService violateWordService;
	
	//违禁词
	private static List<String> violateWordList=new ArrayList<String>();
	
	@Resource
	public synchronized void setViolateWordService(ViolateWordService violateWordService) {
		StringUtils.violateWordService = violateWordService;
		setViolateWordList();
	}

	public static List<String> getViolateWordList() {
		return violateWordList;
	}

	public static void setViolateWordList() {
		ViolateWordExample violateWordExample=new ViolateWordExample();
		violateWordExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("1");
		violateWordExample.setOrderByClause("LENGTH(word) desc");
		List<ViolateWord> violateWords=violateWordService.selectByExample(violateWordExample);
		if(violateWords!=null){
			for(ViolateWord violateWord:violateWords){
				violateWordList.add(violateWord.getWord());
			}
		}
	}
	

	public StringUtils() {
	}

	public static String valueOf(char c) {
		char ac[] = new char[1];
		ac[0] = c;
		return new String(ac);
	}

	public static String valueOf(double d) {
		return Double.toString(d);
	}

	public static String valueOf(float f) {
		return Float.toString(f);
	}

	public static String valueOf(int i) {
		char ac[] = new char[11];
		int j = ac.length;
		boolean flag = i < 0;
		if (!flag)
			i = -i;
		for (; i <= -10; i /= 10)
			ac[--j] = Character.forDigit(-(i % 10), 10);

		ac[--j] = Character.forDigit(-i, 10);
		if (flag)
			ac[--j] = '-';
		return new String(ac, j, ac.length - j);
	}

	public static String valueOf(long l) {
		return Long.toString(l, 10);
	}

	public static String valueOf(String value) {
		if (value != null && !value.equals("") && !value.equals("No data"))
			return value;
		else
			return "";
	}

	public static String valueOf(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	public static String valueOf(boolean flag) {
		return flag ? "true" : "false";
	}

	public static String valueOf(char ac[]) {
		return new String(ac);
	}

	public static String valueOf(Object array[]) {
		StringBuffer sb = new StringBuffer();
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				if (i > 0)
					sb.append(",");
				sb.append(valueOf(array[i]));
			}

		}
		return sb.toString();
	}

	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= '\377') {
				sb.append(c);
			} else {
				byte b[];
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append((new StringBuilder("%")).append(
							Integer.toHexString(k).toUpperCase()).toString());
				}

			}
		}

		return sb.toString();
	}

	public static String titleToUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= '\377') {
				sb.append(c);
			} else {
				byte b[];
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append((new StringBuilder("%")).append(
							Integer.toHexString(k).toUpperCase()).toString());
				}

			}
		}

		return sb.toString();
	}

	public static boolean matchPatter(String str, String patterns[]) {
		if (str == null || patterns == null)
			return false;
		for (int i = 0; i < patterns.length; i++)
			if (patterns[i] != null && Pattern.matches(patterns[i], str))
				return true;

		return false;
	}

	public static String toUnicode(String strText) throws Exception {
		String strRet = "";
		for (int i = 0; i < strText.length(); i++) {
			char c = strText.charAt(i);
			int intAsc = c;
			if (intAsc > 128) {
				String strHex = Integer.toHexString(intAsc);
				strRet = (new StringBuilder(StringUtils.valueOf(strRet)))
						.append("\\u").append(strHex).toString();
			} else {
				strRet = (new StringBuilder(StringUtils.valueOf(strRet)))
						.append(c).toString();
			}
		}

		return strRet;
	}

	public static boolean matcherRegex(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	public static boolean checkEmail(String email) {
		String regex = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		return matcherRegex(regex, email);
	}

	public static boolean isBlank(String str) {
		return str == null || "".equals(str.trim());
	}

	public static boolean isEmpty(Object str) {
		return str == null || "".equals(str.toString().trim());
	}

	public static boolean isDouble(String str) {
		if (isBlank(str))
			return false;
		try {
			Double.parseDouble(str);
		} catch (Exception exception) {
			return false;
		}
		return true;
	}

	public static boolean isInteger(String str) {
		if (isBlank(str))
			return false;
		try {
			Integer.parseInt(str);
		} catch (Exception exception) {
			return false;
		}
		return true;
	}

	public static boolean isLong(String str) {
		if (isBlank(str))
			return false;
		try {
			Long.parseLong(str);
		} catch (Exception exception) {
			return false;
		}
		return true;
	}

	public static boolean isTimestamp(String str) {
		if (isBlank(str))
			return false;
		
		try {
			Date.valueOf(str);
		} catch (Exception exception) {
			return false;
		}
		return true;
	}

	public static boolean isLongs(String str[]) {
		try {
			for (int i = 0; i < str.length; i++)
				Long.parseLong(str[i]);

		} catch (Exception exception) {
			return false;
		}
		return true;
	}

	public static boolean isIntegers(String str[]) {
		try {
			for (int i = 0; i < str.length; i++)
				Integer.parseInt(str[i]);

		} catch (Exception exception) {
			return false;
		}
		return true;
	}

	public static String Md5(String str) {
		StringBuffer result = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();
			int i = 0;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					result.append("0");
				result.append(Integer.toHexString(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	public static Long[] stringToLongs(String str[]) {
		Long lon[] = new Long[str.length];
		for (int i = 0; i < lon.length; i++)
			lon[i] = new Long(str[i]);

		return lon;
	}

	public static Integer[] stringToIntegers(String str[]) {
		Integer array[] = new Integer[str.length];
		for (int i = 0; i < array.length; i++)
			array[i] = new Integer(str[i]);

		return array;
	}

	public static double[] stringToDoubles(String str[]) {
		double array[] = new double[str.length];
		for (int i = 0; i < array.length; i++)
			array[i] = Double.parseDouble(str[i]);

		return array;
	}

	public static String getOnlyStringByTime() {
		return (new StringBuilder(StringUtils.valueOf(System
				.currentTimeMillis()))).append((new Random()).nextInt(10000))
				.toString();
	}

	public static String decodeHTML(String s) {
		if (isBlank(s)) {
			return "";
		} else {
			s = s.replaceAll("&amp;", "&");
			s = s.replaceAll("&lt;", "<");
			s = s.replaceAll("&gt;", ">");
			s = s.replaceAll("&quot;", "\"");
			return s;
		}
	}

	public static String encodeHTML(String s) {
		if (isBlank(s))
			return "";
		int ln = s.length();
		for (int i = 0; i < ln; i++) {
			char c = s.charAt(i);
			if (c != '<' && c != '>' && c != '&' && c != '"')
				continue;
			StringBuffer b = new StringBuffer(s.substring(0, i));
			switch (c) {
			case 60: // '<'
				b.append("&lt;");
				break;

			case 62: // '>'
				b.append("&gt;");
				break;

			case 38: // '&'
				b.append("&amp;");
				break;

			case 34: // '"'
				b.append("&quot;");
				break;
			}
			int next = ++i;
			for (; i < ln; i++) {
				c = s.charAt(i);
				if (c == '<' || c == '>' || c == '&' || c == '"') {
					b.append(s.substring(next, i));
					switch (c) {
					case 60: // '<'
						b.append("&lt;");
						break;

					case 62: // '>'
						b.append("&gt;");
						break;

					case 38: // '&'
						b.append("&amp;");
						break;

					case 34: // '"'
						b.append("&quot;");
						break;
					}
					next = i + 1;
				}
			}

			if (next < ln)
				b.append(s.substring(next));
			s = b.toString();
			break;
		}

		return s;
	}

	public static String getIpAddr(HttpServletRequest request) {
	    String ip = request.getHeader("WL-Proxy-Client-IP");
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	    	ip = request.getHeader("X-Forwarded-For");
	    	if(!StringUtils.isEmpty(ip)&&!"unknown".equalsIgnoreCase(ip)){
	    		ip=(ip.split(",")[0]).trim();
	    	}
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getHeader("Proxy-Client-IP");
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	      ip = request.getRemoteAddr();
	    }
	    return ip;
	  }

	public static String getRamCode(int f_length) {
		String f_Randchar = "0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
		String f_Randomizecode = "";
		String f_Randchararr[] = f_Randchar.split(",");
		int f_RandLen = f_length;
		for (int i = 1; i <= f_RandLen; i++) {
			int f_Random = (int) (Math.random() * 21D);
			f_Randomizecode = (new StringBuilder(String
					.valueOf(f_Randomizecode))).append(f_Randchararr[f_Random])
					.toString();
		}

		return f_Randomizecode;
	}

	public static String getRandListByLength(int f_RandNum, int f_length) {
		if (f_RandNum == 0 || f_length == 0)
			return null;
		String f_Randomizecode = "";
		for (int i = 1; i <= f_RandNum;) {
			Random rand = new Random();
			String f_Random = Integer.toString(rand.nextInt(f_length) + 1);
			if (f_Randomizecode.indexOf(f_Random) == -1) {
				f_Randomizecode = (new StringBuilder(String
						.valueOf(f_Randomizecode))).append(f_Random).toString();
				if (i != f_RandNum)
					f_Randomizecode = (new StringBuilder(String
							.valueOf(f_Randomizecode))).append(",").toString();
				i++;
			}
		}

		return f_Randomizecode;
	}

	public static String getRandListByArr(List<?> f_Randchar, int f_RandNum) {
		int f_length = f_Randchar.size();
		if (f_length == 0 || f_RandNum == 0)
			return null;
		String f_Randomizecode = ",";
		if (f_length < f_RandNum)
			f_RandNum = f_length;
		for (int i = 1; i <= f_RandNum;) {
			Random rand = new Random();
			String f_Random = (new StringBuilder()).append(
					f_Randchar.get(rand.nextInt(f_length))).toString();
			if (f_Randomizecode.indexOf((new StringBuilder(",")).append(
					f_Random).append(",").toString()) == -1) {
				f_Randomizecode = (new StringBuilder(String
						.valueOf(f_Randomizecode))).append(f_Random).toString();
				if (i != f_RandNum)
					f_Randomizecode = (new StringBuilder(String
							.valueOf(f_Randomizecode))).append(",").toString();
				i++;
			}
		}

		return f_Randomizecode.substring(1);
	}

	public static String showFormat(String str, String value, Object sel) {
		String result = "";
		String strArr[] = str.split(",");
		String valueArr[] = value.split(",");
		if (strArr.length != valueArr.length)
			return result;
		for (int i = 0; i < valueArr.length; i++) {
			String selStr = valueOf(sel);
			if (selStr != null && !selStr.equals("")
					&& !selStr.equalsIgnoreCase("null")
					&& selStr.equals(valueArr[i]))
				result = (new StringBuilder(StringUtils.valueOf(result)))
						.append(strArr[i]).toString();
		}

		return result;
	}

	public static String splitAndFilterString(String input, int length) {
		if (input == null || input.trim().equals(""))
			return "";
		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
				"<[^>]*>", "");
		str = str.replaceAll("[(/>)<]", "");
		int len = str.length();
		if (len <= length) {
			return str;
		} else {
			str = str.substring(0, length);
			str = (new StringBuilder(StringUtils.valueOf(str)))
					.append("......").toString();
			return str;
		}
	}

	public static String javaScriptStringEnc(String s) {
		int ln = s.length();
		for (int i = 0; i < ln; i++) {
			char c = s.charAt(i);
			if (c == '"' || c == '\'' || c == '\\' || c == '>' || c < ' ') {
				StringBuffer b = new StringBuffer(ln + 4);
				b.append(s.substring(0, i));
				do {
					if (c == '"')
						b.append("\\\"");
					else if (c == '\'')
						b.append("\\'");
					else if (c == '\\')
						b.append("\\\\");
					else if (c == '>')
						b.append("\\>");
					else if (c < ' ') {
						if (c == '\n')
							b.append("\\n");
						else if (c == '\r')
							b.append("\\r");
						else if (c == '\f')
							b.append("\\f");
						else if (c == '\b')
							b.append("\\b");
						else if (c == '\t') {
							b.append("\\t");
						} else {
							b.append("\\x");
							int x = c / 16;
							b.append((char) (x < 10 ? x + 48 : (x - 10) + 65));
							x = c & 0xf;
							b.append((char) (x < 10 ? x + 48 : (x - 10) + 65));
						}
					} else {
						b.append(c);
					}
					if (++i >= ln)
						return b.toString();
					c = s.charAt(i);
				} while (true);
			}
		}

		return s;
	}

	public static boolean isNumber(String str) {
		String number = "1234567890";
		for (int i = 0; i < str.length(); i++) {
			if (number.indexOf(str.charAt(i)) == -1) {
				return false;
			}
		}
		return true;
	}
	
	public static String[] stringToArray(String str, String flag) {
		String[] arr = null;
		if (str.indexOf(flag) > 0){
			arr=str.split(flag);
		} else {
			arr = new String[]{str};
		}
		return arr;
	}	
	
	/**
	 * 去除html标签
	 * 
	 * @param html
	 * @return
	 */
	public static String removeHtmlTag(String html) {
		if (html == null || "".equals(html))
			return "";
		String textStr = "";
		java.util.regex.Pattern pattern;
		java.util.regex.Matcher matcher;

		try {
			String regEx_remark = "<!--.+?-->";
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
			String regEx_html = "<[^>]+>";
			String regEx_html1 = "<[^>]+";
			html = html.replaceAll("\n", "");
			html = html.replaceAll("\t", "");
			html = html.replaceAll("&nbsp;", "");

			pattern = Pattern.compile(regEx_remark);
			matcher = pattern.matcher(html);
			html = matcher.replaceAll("");

			pattern = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(html);
			html = matcher.replaceAll("");

			pattern = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(html);
			html = matcher.replaceAll("");

			pattern = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(html);
			html = matcher.replaceAll("");

			pattern = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(html);
			html = matcher.replaceAll("");

			textStr = html.trim();

		} catch (Exception e) {
			System.out.println("获取HTML中的text出错:");
			e.printStackTrace();
		}
		return textStr;
	}
	
	public static String escapeHtmlAndIllegal(String input) {
		if (!StringUtils.isEmpty(input)) {
			// html 特殊字符
			input = input.replaceAll("<", "&lt;");
			input = input.replaceAll(">", "&gt;");

			// 禁用的字符
			input = input.replaceAll("(?i)"+"<!ENTITY", "66666");
		}
		return input;
	}

	public static String escapeViolateWord(String input) {
		if (!StringUtils.isEmpty(input)) {
			for (String violateWord:violateWordList) {
				input = input.replaceAll("(?i)"+violateWord, "");
			}
		}
		return input;
	}

	public static String buildMsg(String pattern, Object... params) {
		return params == null ? pattern : MessageFormatter.arrayFormat(pattern, params).getMessage();
	}
}