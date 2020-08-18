package com.jf.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.jf.entity.ViolateWord;
import com.jf.entity.ViolateWordExample;
import com.jf.service.ViolateWordService;


@Component
@Lazy(false)
public class StringUtil{
	
	private static ViolateWordService violateWordService;
	
	//违禁词
	private static List<String> violateWordList=new ArrayList<String>();
	
	@Resource
	public synchronized void setViolateWordService(ViolateWordService violateWordService) {
		StringUtil.violateWordService = violateWordService;
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

	public static String getUnderlineName(String fieldName) {
		if ((fieldName == null) || ("".equals(fieldName.trim())))
			return "";
		StringBuffer buffer = new StringBuffer();
		int length = fieldName.length();
		for (int i = 0; i < length; i++) {
			char c = fieldName.charAt(i);
			if ((c >= 'A') && (c <= 'Z'))
				buffer.append("_");
			buffer.append(Character.toUpperCase(c));
		}
		return buffer.toString();
	}

	public static String getCamelCaseName(String fieldName) {
		if ((fieldName == null) || ("".equals(fieldName.trim())))
			return "";
		int length = fieldName.length();
		StringBuilder builder = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			char c = fieldName.charAt(i);
			if (c == '_') {
				i++;
				if (i < length)
					builder.append(Character.toUpperCase(fieldName.charAt(i)));
			} else {
				builder.append(Character.toLowerCase(c));
			}
		}
		return builder.toString();
	}

	public static String fullZero(String s, int len) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < len - s.length(); i++) {
			buffer.append("0");
		}
		buffer.append(s);
		return buffer.toString();
	}

	public static String fullZero(int s, int len) {
		return fullZero(valueOf(s), len);
	}

	public static String fullZero(long s, int len) {
		return fullZero(valueOf(s), len);
	}

	public static String UpperFirstCase(String str) {
		String result = null;
		if (!isEmpty(str)) {
			result = new StringBuilder().append(str.substring(0, 1).toUpperCase()).append(str.substring(1)).toString();
		}
		return result;
	}

	public static String objToStr(Object obj) {
		if (obj != null) {
			if ((obj instanceof String)) {
				return String.valueOf(obj).trim();
			}
			return obj.toString().trim();
		}

		return "";
	}

	public static String getObjectTypeName(Object obj) {
		String result = "this Object is null";
		if (obj != null) {
			result = obj.getClass().getSimpleName();
		}
		return result;
	}

	private static boolean isSimpleType(Class clz) {
		if (clz.equals(Integer.TYPE))
			return true;
		if (clz.equals(Integer.class))
			return true;
		if (clz.equals(Long.TYPE))
			return true;
		if (clz.equals(Long.class))
			return true;
		if (clz.equals(Byte.TYPE))
			return true;
		if (clz.equals(Byte.class))
			return true;
		if (clz.equals(Short.TYPE))
			return true;
		if (clz.equals(Short.class))
			return true;
		if (clz.equals(Boolean.TYPE))
			return true;
		if (clz.equals(Boolean.class))
			return true;
		if (clz.equals(Float.TYPE))
			return true;
		if (clz.equals(Float.class))
			return true;
		if (clz.equals(Double.TYPE))
			return true;
		if (clz.equals(Double.class))
			return true;
		if (clz.equals(String.class)) {
			return true;
		}
		return false;
	}

	private static boolean isIgnoreType(Class clz) {
		if (clz.equals(Class.class)) {
			return true;
		}
		return false;
	}

	public static String valueOf(char c) {
		char[] ac = new char[1];
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
		char[] ac = new char[11];
		int j = ac.length;
		boolean flag = i < 0;
		if (!flag)
			i = -i;
		for (; i <= -10; i /= 10) {
			ac[(--j)] = Character.forDigit(-(i % 10), 10);
		}
		ac[(--j)] = Character.forDigit(-i, 10);
		if (flag)
			ac[(--j)] = '-';
		return new String(ac, j, ac.length - j);
	}

	public static String valueOf(long l) {
		return Long.toString(l, 10);
	}

	public static String valueOf(String value) {
		if ((value != null) && (!value.equals("")) && (!value.equals("No data"))) {
			return value.trim();
		}
		return "";
	}

	public static String valueOf(Object obj) {
		return obj != null ? obj.toString().trim() : "";
	}

	public static String valueOf(boolean flag) {
		return flag ? "true" : "false";
	}

	public static String valueOf(char[] ac) {
		return new String(ac);
	}

	public static String valueOf(Object[] array) {
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

	public static boolean matchPatter(String str, String[] patterns) {
		if ((str == null) || (patterns == null))
			return false;
		for (int i = 0; i < patterns.length; i++) {
			if ((patterns[i] != null) && (Pattern.matches(patterns[i], str)))
				return true;
		}
		return false;
	}

	public static String toUnicode(String strText) throws Exception {
		String strRet = "";

		for (int i = 0; i < strText.length(); i++) {
			char c = strText.charAt(i);
			int intAsc = c;
			if (intAsc > 128) {
				String strHex = Integer.toHexString(intAsc);
				strRet = new StringBuilder().append(strRet).append("\\u").append(strHex).toString();
			} else {
				strRet = new StringBuilder().append(strRet).append(c).toString();
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
		return (str == null) || ("".equals(str.trim()));
	}

	public static boolean isEmpty(String str) {
		return (str == null) || ("".equals(str.trim()));
	}

	public static boolean isDouble(String str) {
		if (isBlank(str))
			return false;
		try {
			Double.parseDouble(str);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean isInteger(String str) {
		if (isBlank(str))
			return false;
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean isLong(String str) {
		if (isBlank(str))
			return false;
		try {
			Long.parseLong(str);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean isTimestamp(String str) {
		if (isBlank(str))
			return false;
		try {
			java.sql.Date d = java.sql.Date.valueOf(str);
			return true;
		} catch (Exception ex) {
		}
		return false;
	}

	public static boolean isFullTimestamp(String str) {
		if (isBlank(str))
			return false;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date date = format.parse(str);
			return date != null;
		} catch (Exception e) {
		}
		return false;
	}

	public static String decodeURIFromISO88591(String str) {
		if (isBlank(str))
			return "";
		try {
			return new String(str.getBytes("ISO-8859-1"), "GB2312");
		} catch (UnsupportedEncodingException e) {
		}
		return "";
	}

	public static boolean isLongs(String[] str) {
		try {
			for (int i = 0; i < str.length; i++)
				Long.parseLong(str[i]);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean isIntegers(String[] str) {
		try {
			for (int i = 0; i < str.length; i++)
				Integer.parseInt(str[i]);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static String Md5(String str) {
		StringBuffer result = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] b = md.digest();
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

	public static Long[] stringToLongs(String[] str) {
		Long[] lon = new Long[str.length];
		for (int i = 0; i < lon.length; i++)
			lon[i] = new Long(str[i]);
		return lon;
	}

	public static Integer[] stringToIntegers(String[] str) {
		Integer[] array = new Integer[str.length];
		for (int i = 0; i < array.length; i++)
			array[i] = new Integer(str[i]);
		return array;
	}

	public static double[] stringToDoubles(String[] str) {
		double[] array = new double[str.length];
		for (int i = 0; i < array.length; i++)
			array[i] = Double.parseDouble(str[i]);
		return array;
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

	public static String getOnlyStringByTime() {
		return new StringBuilder().append(System.currentTimeMillis()).append("").append(new Random().nextInt(10000)).toString();
	}

	public static String decodeHTML(String s) {
		if (isBlank(s))
			return "";
		s = s.replaceAll("&amp;", "&");
		s = s.replaceAll("&lt;", "<");
		s = s.replaceAll("&gt;", ">");
		s = s.replaceAll("&quot;", "\"");
		return s;
	}

	public static String encodeHTML(String s) {
		if (isBlank(s))
			return "";
		int ln = s.length();

		for (int i = 0; i < ln; i++) {
			char c = s.charAt(i);
			if ((c == '<') || (c == '>') || (c == '&') || (c == '"')) {
				StringBuffer b = new StringBuffer(s.substring(0, i));
				switch (c) {
				case '<':
					b.append("&lt;");
					break;
				case '>':
					b.append("&gt;");
					break;
				case '&':
					b.append("&amp;");
					break;
				case '"':
					b.append("&quot;");
				}

				i++;
				int next = i;
				while (i < ln) {
					c = s.charAt(i);
					if ((c == '<') || (c == '>') || (c == '&') || (c == '"')) {
						b.append(s.substring(next, i));
						switch (c) {
						case '<':
							b.append("&lt;");
							break;
						case '>':
							b.append("&gt;");
							break;
						case '&':
							b.append("&amp;");
							break;
						case '"':
							b.append("&quot;");
						}

						next = i + 1;
					}
					i++;
				}
				if (next < ln)
					b.append(s.substring(next));
				s = b.toString();
				break;
			}
		}
		return s;
	}

	public static String getIpAddr(HttpServletRequest request) {
	    String ip = request.getHeader("WL-Proxy-Client-IP");
	    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
	    	ip = request.getHeader("X-Forwarded-For");
	    	if(!StringUtil.isEmpty(ip)&&!"unknown".equalsIgnoreCase(ip)){
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

		String[] f_Randchararr = f_Randchar.split(",");
		int f_RandLen = f_length;
		for (int i = 1; i <= f_RandLen; i++) {
			int f_Random = (int) (Math.random() * 21.0D);
			f_Randomizecode = new StringBuilder().append(f_Randomizecode).append(f_Randchararr[f_Random]).toString();
		}
		return f_Randomizecode;
	}

	public static List<String> splitToList(String str, String fg) {
		List list = new ArrayList();
		String[] arr = str.split(fg);
		for (String s : arr) {
			list.add(s);
		}
		return list;
	}

	public static String getRandListByLength(int f_RandNum, int f_length) {
		if ((f_RandNum == 0) || (f_length == 0))
			return null;
		String f_Randomizecode = "";

		for (int i = 1; i <= f_RandNum;) {
			Random rand = new Random();
			String f_Random = Integer.toString(rand.nextInt(f_length) + 1);
			if (f_Randomizecode.indexOf(f_Random) == -1) {
				f_Randomizecode = new StringBuilder().append(f_Randomizecode).append(f_Random).toString();
				if (i != f_RandNum)
					f_Randomizecode = new StringBuilder().append(f_Randomizecode).append(",").toString();
				i++;
			}
		}
		return f_Randomizecode;
	}

	public static String getRandListByArr(List<?> f_Randchar, int f_RandNum) {
		int f_length = f_Randchar.size();
		if ((f_length == 0) || (f_RandNum == 0))
			return null;
		String f_Randomizecode = ",";

		if (f_length < f_RandNum)
			f_RandNum = f_length;
		for (int i = 1; i <= f_RandNum;) {
			Random rand = new Random();
			String f_Random = new StringBuilder().append("").append(f_Randchar.get(rand.nextInt(f_length))).toString();
			if (f_Randomizecode.indexOf(new StringBuilder().append(",").append(f_Random).append(",").toString()) == -1) {
				f_Randomizecode = new StringBuilder().append(f_Randomizecode).append(f_Random).toString();
				if (i != f_RandNum)
					f_Randomizecode = new StringBuilder().append(f_Randomizecode).append(",").toString();
				i++;
			}
		}
		return f_Randomizecode.substring(1);
	}

	public static String showFormat(String str, String value, Object sel) {
		String result = "";
		String[] strArr = str.split(",");
		String[] valueArr = value.split(",");
		if (strArr.length != valueArr.length) {
			return result;
		}
		for (int i = 0; i < valueArr.length; i++) {
			String selStr = valueOf(sel);
			if ((selStr != null) && (!selStr.equals("")) && (!selStr.equalsIgnoreCase("null")) && (selStr.equals(valueArr[i]))) {
				result = new StringBuilder().append(result).append(strArr[i]).toString();
			}
		}

		return result;
	}

	public static String clearHTMLTag(String str) {
		Matcher matcher = null;
		Pattern pattern = null;

		pattern = Pattern.compile("<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>", 2);
		matcher = pattern.matcher(str);
		str = matcher.replaceAll("");

		pattern = Pattern.compile("<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>", 2);
		matcher = pattern.matcher(str);
		str = matcher.replaceAll("");

		pattern = Pattern.compile("<[^>]+>", 2);
		matcher = pattern.matcher(str);
		str = matcher.replaceAll("");

		pattern = Pattern.compile("\\s", 2);
		matcher = pattern.matcher(str);
		str = matcher.replaceAll("");

		pattern = Pattern.compile("&nbsp;", 2);
		matcher = pattern.matcher(str);
		str = matcher.replaceAll("");

		return str;
	}

	public static String escapeHtmlAndIllegal(String input) {
		if (!StringUtil.isEmpty(input)) {
			// html 特殊字符
			input = input.replaceAll("<", "&lt;");
			input = input.replaceAll(">", "&gt;");

			// 禁用的字符
			input = input.replaceAll("(?i)"+"<!ENTITY", "66666");
		}
		return input;
	}

	public static String escapeViolateWord(String input) {
		if (!StringUtil.isEmpty(input)) {
			for (String violateWord:violateWordList) {
				input = input.replaceAll("(?i)"+violateWord, "");
			}
		}
		return input;
	}
	
	public static Set<String> containsViolateWord(String text) {
		Set<String> resultSet=new HashSet<String>();
		if (!StringUtil.isEmpty(text)) {
			for (String violateWord:violateWordList) {
			      if(Pattern.matches("(?i)[\\s\\S]*"+violateWord+"[\\s\\S]*", text)){
			    	  resultSet.add(violateWord);
			    	  text = text.replaceAll("(?i)"+violateWord, "");
			      }
			}
		}
		return resultSet;
	}

	public static String unEscapeHtmlAndIllegal(String input) {
		if (!StringUtil.isEmpty(input)) {
			// html 特殊字符
			input = input.replaceAll("&lt;", "<");
			input = input.replaceAll("&gt;", ">");
		}
		return input;
	}

	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();

	}

	public static String buildMsg(String pattern, Object... params) {
		return params == null ? pattern : MessageFormatter.arrayFormat(pattern, params).getMessage();
	}
	
	public static void main(String[] args) {
		
	      String content = "好Addi";
	    	 
	      String pattern = "(?i)[\\s\\S]*addi[\\s\\S]*";
	    	 
	      boolean isMatch = Pattern.matches(pattern, content);
		
		System.out.println(isMatch);
	}
	
}