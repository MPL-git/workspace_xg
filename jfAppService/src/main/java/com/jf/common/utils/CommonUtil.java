package com.jf.common.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

public class CommonUtil {
	
	private static Map<String, List<String>> orderCodeMap=new HashMap<String, List<String>>();
	private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
	private static Random random = new Random();
	/**
	 * 获取单号,不重复
	 * @return
	 */
	public static String getOrderCode(){
	       
		//生成随机数:当前精确到秒的时间再加6位的数字随机序列
        String rdNum=df.format(new Date());
        String orderCode=null;
        orderCode = rdNum + getRandomNum(4);
        if(orderCodeMap.get(rdNum)==null){
        	orderCodeMap.clear();
        	List<String> orderCodeList=new ArrayList<String>();
        	orderCodeList.add(orderCode);
        	orderCodeMap.put(rdNum, orderCodeList);
        	return orderCode;
        	
        }else{
        	List<String> orderCodeList=orderCodeMap.get(rdNum);
        	if(!orderCodeList.contains(orderCode)){
        		orderCodeList.add(orderCode);
        		return orderCode;
        	}else{
        		return getOrderCode();
        	}
        }
        
	}
	
	/**
	 * 获取指定位数的随机数(纯数字)
	 * 
	 * @param length
	 *            随机数的位数
	 * @return String
	 */
	public static String getRandomNum(int length) {
		if (length <= 0) {
			length = 1;
		}
		StringBuilder res = new StringBuilder();
		int i = 0;
		while (i < length) {
			res.append(random.nextInt(10));
			i++;
		}
		return res.toString();
	}
	
	
	
	/**
	 * 生成带签名的请求数据
	 * @param paramData
	 * @return
	 */
	public static JSONObject createReqData(JSONObject reqParam){
		
		String timeStamp=String.valueOf(new Date().getTime() / 1000);
		String nonceStr=getRandomStringByLength(16);
		Map<String, String> paramMap=new HashMap<String, String>();
		if(reqParam.containsKey("token")){
			
			paramMap.put("token", reqParam.getString("token"));
		}
		paramMap.put("timeStamp", timeStamp);
		paramMap.put("nonceStr", nonceStr);
		paramMap.put("reqData", reqParam.get("reqData").toString());
		String sign=SignatureUtil.createSignature(paramMap);
		reqParam.put("timeStamp", timeStamp);
		reqParam.put("nonceStr", nonceStr);
		reqParam.put("signature", sign);
		return reqParam;
	}
	
	/**
	 * 获取随机数 	
	 * @param length
	 * @return
	 */
	    public static String getRandomStringByLength(int length) {
	        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < length; i++) {
	            int number = random.nextInt(base.length());
	            sb.append(base.charAt(number));
	        }
	        return sb.toString();
	    }
	    
	    
	    /**
	     * 
	     * 方法描述 ：获取渠道
	     * @author  chenwf: 
	     * @date 创建时间：2017年6月20日 下午7:54:07 
	     * @version
	     * @param sprChnl
	     * @return
	     */
	    public static String getSprChnl(String sprChnl){ 
	    	///XG_UC   UC
	    	//XG_GDT    广点通
	    	//XG_ZHT    智慧推
	    	//XG_sina    新浪
	    	//XG_iqiyi    爱奇艺
	    	//XG_momo   陌陌
	    	//XG_samsung  三星
	    	if(sprChnl.equals("1002")){
	    		//苹果渠道
	    		sprChnl = "1002";
	    	}else if(sprChnl.equals("XG_xinggou")){
	    		sprChnl = "1001";
	    	}else if(sprChnl.equals("XG_ali")){
	    		sprChnl = "1003";
	    	}else if(sprChnl.equals("XG_vivo")){
	    		sprChnl = "1004";
	    	}else if(sprChnl.equals("XG_xiaomi")){
	    		sprChnl = "1005";
	    	}else if(sprChnl.equals("XG_huawei")){
	    		sprChnl = "1006";
	    	}else if(sprChnl.equals("XG_lianxiang")){
	    		sprChnl = "1007";
	    	}else if(sprChnl.equals("XG_oppo")){
	    		sprChnl = "1008";
	    	}else if(sprChnl.equals("XG_meizu")){
	    		sprChnl = "1009";
	    	}else if(sprChnl.equals("XG_tencent")){
	    		sprChnl = "1010";
	    	}else if(sprChnl.equals("XG_baidu")){
	    		sprChnl = "1011";
	    	}else if(sprChnl.equals("XG_toutiao")){
	    		sprChnl = "1012";
	    	}else if(sprChnl.equals("XG_360")){
	    		sprChnl = "1013";
	    	}else if(sprChnl.equals("XG_UC")){
	    		sprChnl = "1014";
	    	}else if(sprChnl.equals("XG_GDT")){
	    		sprChnl = "1015";
	    	}else if(sprChnl.equals("XG_ZHT")){
	    		sprChnl = "1016";
	    	}else if(sprChnl.equals("XG_sina")){
	    		sprChnl = "1017";
	    	}else if(sprChnl.equals("XG_iqiyi")){
	    		sprChnl = "1018";
	    	}else if(sprChnl.equals("XG_momo")){
	    		sprChnl = "1019";
	    	}else if(sprChnl.equals("XG_samsung")){
		    	sprChnl = "1020";
	    	}else if(sprChnl.equals("XG_kuaishou")){
		    	sprChnl = "1021";
	    	}else if(sprChnl.equals("XG_duanzi")){
		    	sprChnl = "1022";
	    	}else if(sprChnl.equals("XG_duanxin")){
		    	sprChnl = "1023";
	    	}else if(sprChnl.equals("XG_GDT_YC")){
		    	sprChnl = "1024";
	    	}else if(sprChnl.equals("XG_GDT_LS")){
		    	sprChnl = "1025";
	    	}else if(sprChnl.equals("XG_GDT_YR")){
		    	sprChnl = "1026";
	    	}else if(sprChnl.equals("XG_GDT_YK")){
		    	sprChnl = "1027";
	    	}else if(sprChnl.equals("XG_weibo_LD")){
		    	sprChnl = "1028";
	    	}else if(sprChnl.equals("XG_toutiao_zixun")){
		    	sprChnl = "1029";
	    	}else if(sprChnl.equals("XG_GDT_YK_2")){
		    	sprChnl = "1030";
	    	}else if(sprChnl.equals("XG_GDT_YK_3")){
		    	sprChnl = "1031";
	    	}else if(sprChnl.equals("XG_GDT_YK_4")){
		    	sprChnl = "1032";
	    	}else if(sprChnl.equals("XG_GDT_YK_5")){
		    	sprChnl = "1033";
	    	}else if(sprChnl.equals("XG_GDT_YK_6")){
		    	sprChnl = "1034";
	    	}else if(sprChnl.equals("XG_GDT_YK_7")){
		    	sprChnl = "1035";
	    	}else if(sprChnl.equals("XG_GDT_YK_8")){
		    	sprChnl = "1036";
	    	}else if(sprChnl.equals("XG_GDT_YK_9")){
		    	sprChnl = "1037";
	    	}else if(sprChnl.equals("XG_GDT_YK_10")){
		    	sprChnl = "1038";
	    	}else if(sprChnl.equals("XG_GDT_LS_2")){
		    	sprChnl = "1039";
	    	}else if(sprChnl.equals("XG_GDT_LS_3")){
		    	sprChnl = "1040";
	    	}else if(sprChnl.equals("XG_GDT_LS_4")){
		    	sprChnl = "1041";
	    	}else if(sprChnl.equals("XG_GDT_LS_5")){
		    	sprChnl = "1042";
	    	}else if(sprChnl.equals("XG_GDT_LS_6")){
		    	sprChnl = "1043";
	    	}else if(sprChnl.equals("XG_GDT_LS_7")){
		    	sprChnl = "1044";
	    	}else if(sprChnl.equals("XG_GDT_LS_8")){
		    	sprChnl = "1045";
	    	}else if(sprChnl.equals("XG_GDT_LS_9")){
		    	sprChnl = "1046";
	    	}else if(sprChnl.equals("XG_GDT_LS_10")){
		    	sprChnl = "1047";
	    	}else if(sprChnl.equals("XG_GDT_YK_11")){
		    	sprChnl = "1048";
	    	}else if(sprChnl.equals("XG_GDT_YK_12")){
		    	sprChnl = "1049";
	    	}else if(sprChnl.equals("XG_GDT_YK_13")){
		    	sprChnl = "1050";
	    	}else if(sprChnl.equals("XG_GDT_YK_14")){
		    	sprChnl = "1051";
	    	}else if(sprChnl.equals("XG_GDT_YK_15")){
		    	sprChnl = "1052";
	    	}else if(sprChnl.equals("XG_GDT_YK_16")){
		    	sprChnl = "1053";
	    	}else if(sprChnl.equals("XG_GDT_YK_17")){
		    	sprChnl = "1054";
	    	}else if(sprChnl.equals("XG_GDT_YK_18")){
		    	sprChnl = "1055";
	    	}else if(sprChnl.equals("XG_GDT_YK_19")){
		    	sprChnl = "1056";
	    	}else if(sprChnl.equals("XG_GDT_YK_20")){
		    	sprChnl = "1057";
	    	}else if(sprChnl.equals("XG_GDT_LS_11")){
		    	sprChnl = "1058";
	    	}else if(sprChnl.equals("XG_GDT_LS_12")){
		    	sprChnl = "1059";
	    	}else if(sprChnl.equals("XG_GDT_LS_13")){
		    	sprChnl = "1060";
	    	}else if(sprChnl.equals("XG_GDT_LS_14")){
		    	sprChnl = "1061";
	    	}else if(sprChnl.equals("XG_GDT_LS_15")){
		    	sprChnl = "1062";
	    	}else if(sprChnl.equals("XG_GDT_LS_16")){
		    	sprChnl = "1063";
	    	}else if(sprChnl.equals("XG_GDT_LS_17")){
		    	sprChnl = "1064";
	    	}else if(sprChnl.equals("XG_GDT_LS_18")){
		    	sprChnl = "1065";
	    	}else if(sprChnl.equals("XG_GDT_LS_19")){
		    	sprChnl = "1066";
	    	}else if(sprChnl.equals("XG_GDT_LS_20")){
		    	sprChnl = "1067";
	    	}else if(sprChnl.equals("XG_GDT_YC_2")){
		    	sprChnl = "1068";
	    	}else if(sprChnl.equals("XG_GDT_YC_3")){
		    	sprChnl = "1069";
	    	}else if(sprChnl.equals("XG_GDT_YC_4")){
		    	sprChnl = "1070";
	    	}else if(sprChnl.equals("XG_GDT_YC_5")){
		    	sprChnl = "1071";
	    	}else if(sprChnl.equals("XG_GDT_YC_6")){
		    	sprChnl = "1072";
	    	}else if(sprChnl.equals("XG_GDT_YR_2")){
		    	sprChnl = "1073";
	    	}else if(sprChnl.equals("XG_GDT_YR_3")){
		    	sprChnl = "1074";
	    	}else if(sprChnl.equals("XG_GDT_YR_4")){
		    	sprChnl = "1075";
	    	}else if(sprChnl.equals("XG_GDT_YR_5")){
		    	sprChnl = "1076";
	    	}else if(sprChnl.equals("XG_GDT_YR_6")){
		    	sprChnl = "1077";
	    	}else if(sprChnl.equals("XG_zimeiti")){
		    	sprChnl = "1078";
	    	}else if(sprChnl.equals("XG_GDT_YR_7")){
		    	sprChnl = "1079";
	    	}else if(sprChnl.equals("XG_GDT_YR_8")){
		    	sprChnl = "1080";
	    	}else if(sprChnl.equals("XG_GDT_YR_9")){
		    	sprChnl = "1081";
	    	}else if(sprChnl.equals("XG_GDT_YR_10")){
		    	sprChnl = "1082";
	    	}else if(sprChnl.equals("XG_GDT_YR_11")){
		    	sprChnl = "1083";
	    	}else if(sprChnl.equals("XG_bilibili")){
		    	sprChnl = "1084";
		    }else if(sprChnl.equals("XG_GDT_LS_21")){
		    	sprChnl = "1085";
		    }else if(sprChnl.equals("XG_GDT_LS_22")){
		    	sprChnl = "1086";
		    }else if(sprChnl.equals("XG_GDT_LS_23")){
		    	sprChnl = "1087";
		    }else if(sprChnl.equals("XG_GDT_LS_24")){
		    	sprChnl = "1088";
		    }else if(sprChnl.equals("XG_GDT_LS_25")){
		    	sprChnl = "1089";
		    }else if(sprChnl.equals("XG_GDT_LS_26")){
		    	sprChnl = "1090";
		    }else if(sprChnl.equals("XG_GDT_LS_27")){
		    	sprChnl = "1091";
		    }else if(sprChnl.equals("XG_GDT_LS_28")){
		    	sprChnl = "1092";
		    }else if(sprChnl.equals("XG_GDT_LS_29")){
		    	sprChnl = "1093";
		    }else if(sprChnl.equals("XG_GDT_LS_30")){
		    	sprChnl = "1094";
		    }else if(sprChnl.equals("XG_GDT_LS_31")){
		    	sprChnl = "1095";
		    }else if(sprChnl.equals("XG_GDT_LS_32")){
		    	sprChnl = "1096";
		    }else if(sprChnl.equals("XG_GDT_LS_33")){
		    	sprChnl = "1097";
		    }else if(sprChnl.equals("XG_GDT_LS_34")){
		    	sprChnl = "1098";
		    }else if(sprChnl.equals("XG_GDT_LS_35")){
		    	sprChnl = "1099";
		    }else if(sprChnl.equals("XG_GDT_LS_36")){
		    	sprChnl = "1100";
		    }else if(sprChnl.equals("XG_GDT_LS_37")){
		    	sprChnl = "1101";
		    }else if(sprChnl.equals("XG_GDT_LS_38")){
		    	sprChnl = "1102";
		    }else if(sprChnl.equals("XG_GDT_LS_39")){
		    	sprChnl = "1103";
		    }else if(sprChnl.equals("XG_GDT_LS_40")){
		    	sprChnl = "1104";
		    }else if(sprChnl.equals("XG_2017BLH")){
		    	sprChnl = "1105";
	    	}else if(sprChnl.equals("XG_GDT_YK_21")){
		    	sprChnl = "1106";
	    	}else if(sprChnl.equals("XG_GDT_YK_22")){
		    	sprChnl = "1107";
	    	}else if(sprChnl.equals("XG_GDT_YK_23")){
		    	sprChnl = "1108";
	    	}else if(sprChnl.equals("XG_GDT_YK_24")){
		    	sprChnl = "1109";
	    	}else if(sprChnl.equals("XG_GDT_YK_25")){
		    	sprChnl = "1110";
	    	}else if(sprChnl.equals("XG_GDT_YK_26")){
		    	sprChnl = "1111";
	    	}else if(sprChnl.equals("XG_GDT_YK_27")){
		    	sprChnl = "1112";
	    	}else if(sprChnl.equals("XG_GDT_YK_28")){
		    	sprChnl = "1113";
	    	}else if(sprChnl.equals("XG_GDT_YK_29")){
		    	sprChnl = "1114";
	    	}else if(sprChnl.equals("XG_GDT_YK_30")){
		    	sprChnl = "1115";
	    	}else if(sprChnl.equals("XG_weixin_LD")){
		    	sprChnl = "1116";
	    	}else if(sprChnl.equals("XG_weixin_LD_2")){
		    	sprChnl = "1117";
	    	}else if(sprChnl.equals("XG_weixin_LD_3")){
		    	sprChnl = "1118";
	    	}else if(sprChnl.equals("XG_weixin_LD_4")){
		    	sprChnl = "1119";
	    	}else if(sprChnl.equals("XG_weixin_LD_5")){
		    	sprChnl = "1120";
	    	}else if(sprChnl.equals("XG_weixin_LD_6")){
		    	sprChnl = "1121";
	    	}else if(sprChnl.equals("XG_weixin_LD_7")){
		    	sprChnl = "1122";
	    	}else if(sprChnl.equals("XG_weixin_LD_8")){
		    	sprChnl = "1123";
	    	}else if(sprChnl.equals("XG_weixin_LD_9")){
		    	sprChnl = "1124";
	    	}else if(sprChnl.equals("XG_weixin_LD_10")){
		    	sprChnl = "1125";
	    	}else if(sprChnl.equals("XG_GDT_YR_12")){
		    	sprChnl = "1126";
	    	}else if(sprChnl.equals("XG_GDT_YR_13")){
		    	sprChnl = "1127";
	    	}else if(sprChnl.equals("XG_GDT_YR_14")){
		    	sprChnl = "1128";
	    	}else if(sprChnl.equals("XG_GDT_YR_15")){
		    	sprChnl = "1129";
	    	}else if(sprChnl.equals("XG_GDT_YR_16")){
		    	sprChnl = "1130";
	    	}else if(sprChnl.equals("XG_GDT_YR_17")){
		    	sprChnl = "1131";
	    	}else if(sprChnl.equals("XG_GDT_YR_18")){
		    	sprChnl = "1132";
	    	}else if(sprChnl.equals("XG_GDT_YR_19")){
		    	sprChnl = "1133";
	    	}else if(sprChnl.equals("XG_GDT_YR_20")){
		    	sprChnl = "1134";
	    	}else if(sprChnl.equals("XG_weixin_LD_11")){
		    	sprChnl = "1135";
	    	}else if(sprChnl.equals("XG_weixin_LD_12")){
		    	sprChnl = "1136";
	    	}else if(sprChnl.equals("XG_weixin_LD_13")){
		    	sprChnl = "1137";
	    	}else if(sprChnl.equals("XG_weixin_LD_14")){
		    	sprChnl = "1138";
	    	}else if(sprChnl.equals("XG_weixin_LD_15")){
		    	sprChnl = "1139";
	    	}else if(sprChnl.equals("XG_Meitu")){
		    	sprChnl = "1140";
	    	}else if(sprChnl.equals("XG_GDT_WS_1")){
		    	sprChnl = "1141";
	    	}else if(sprChnl.equals("XG_GDT_WS_2")){
		    	sprChnl = "1142";
	    	}else if(sprChnl.equals("XG_GDT_WS_3")){
		    	sprChnl = "1143";
	    	}else if(sprChnl.equals("XG_GDT_WS_4")){
		    	sprChnl = "1144";
	    	}else if(sprChnl.equals("XG_GDT_WS_5")){
		    	sprChnl = "1145";
	    	}else if(sprChnl.equals("XG_GDT_WS_6")){
		    	sprChnl = "1146";
	    	}else if(sprChnl.equals("XG_GDT_WS_7")){
		    	sprChnl = "1147";
	    	}else if(sprChnl.equals("XG_GDT_WS_8")){
		    	sprChnl = "1148";
	    	}else if(sprChnl.equals("XG_GDT_WS_9")){
		    	sprChnl = "1149";
	    	}else if(sprChnl.equals("XG_GDT_WS_10")){
		    	sprChnl = "1150";
	    	}else if(sprChnl.equals("XG_GDT_YR_21")){
		    	sprChnl = "1151";
	    	}else if(sprChnl.equals("XG_GDT_YR_22")){
		    	sprChnl = "1152";
	    	}else if(sprChnl.equals("XG_GDT_YR_23")){
		    	sprChnl = "1153";
	    	}else if(sprChnl.equals("XG_GDT_YR_24")){
		    	sprChnl = "1154";
	    	}else if(sprChnl.equals("XG_GDT_YR_25")){
		    	sprChnl = "1155";
	    	}else if(sprChnl.equals("XG_GDT_YR_26")){
		    	sprChnl = "1156";
	    	}else if(sprChnl.equals("XG_GDT_YR_27")){
		    	sprChnl = "1157";
	    	}else if(sprChnl.equals("XG_GDT_YR_28")){
		    	sprChnl = "1158";
	    	}else if(sprChnl.equals("XG_GDT_YR_29")){
		    	sprChnl = "1159";
	    	}else if(sprChnl.equals("XG_GDT_YR_30")){
		    	sprChnl = "1160";
	    	}else if(sprChnl.equals("XG_dm_toutiao")){
		    	sprChnl = "1161";//多盟头条
	    	}else if(sprChnl.equals("XG_ekh_toutiao")){
		    	sprChnl = "1162";//耳廓弧头条
	    	}else if(sprChnl.equals("XG_momo_LS_1")){
		    	sprChnl = "1163";
	    	}else if(sprChnl.equals("XG_momo_LS_2")){
		    	sprChnl = "1164";
	    	}else if(sprChnl.equals("XG_momo_LS_3")){
		    	sprChnl = "1165";
	    	}else if(sprChnl.equals("XG_momo_LS_4")){
		    	sprChnl = "1166";
	    	}else if(sprChnl.equals("XG_momo_LS_5")){
		    	sprChnl = "1167";
	    	}else if(sprChnl.equals("XG_momo_LS_6")){
		    	sprChnl = "1168";
	    	}else if(sprChnl.equals("XG_momo_LS_7")){
		    	sprChnl = "1169";
	    	}else if(sprChnl.equals("XG_momo_LS_8")){
		    	sprChnl = "1170";
	    	}else if(sprChnl.equals("XG_momo_LS_9")){
		    	sprChnl = "1171";
	    	}else if(sprChnl.equals("XG_momo_LS_10")){
		    	sprChnl = "1172";
	    	}else if(sprChnl.equals("XG_GDT_YR_31")){
		    	sprChnl = "1173";
	    	}else if(sprChnl.equals("XG_GDT_YR_32")){
		    	sprChnl = "1174";
	    	}else if(sprChnl.equals("XG_GDT_YR_33")){
		    	sprChnl = "1175";
	    	}else if(sprChnl.equals("XG_GDT_YR_34")){
		    	sprChnl = "1176";
	    	}else if(sprChnl.equals("XG_GDT_YR_35")){
		    	sprChnl = "1177";
	    	}else if(sprChnl.equals("XG_GDT_YR_36")){
		    	sprChnl = "1178";
	    	}else if(sprChnl.equals("XG_GDT_YR_37")){
		    	sprChnl = "1179";
	    	}else if(sprChnl.equals("XG_GDT_YR_38")){
		    	sprChnl = "1180";
	    	}else if(sprChnl.equals("XG_GDT_YR_39")){
		    	sprChnl = "1181";
	    	}else if(sprChnl.equals("XG_GDT_YR_40")){
		    	sprChnl = "1182";
	    	}else if(sprChnl.equals("XG_GDT_YK_31")){
		    	sprChnl = "1183";
	    	}else if(sprChnl.equals("XG_GDT_YK_32")){
		    	sprChnl = "1184";
	    	}else if(sprChnl.equals("XG_GDT_YK_33")){
		    	sprChnl = "1185";
	    	}else if(sprChnl.equals("XG_GDT_YK_34")){
		    	sprChnl = "1186";
	    	}else if(sprChnl.equals("XG_GDT_YK_35")){
		    	sprChnl = "1187";
	    	}else if(sprChnl.equals("XG_GDT_YK_36")){
		    	sprChnl = "1188";
	    	}else if(sprChnl.equals("XG_GDT_YK_37")){
		    	sprChnl = "1189";
	    	}else if(sprChnl.equals("XG_GDT_YK_38")){
		    	sprChnl = "1180";
	    	}else if(sprChnl.equals("XG_GDT_YK_39")){
		    	sprChnl = "1191";
	    	}else if(sprChnl.equals("XG_GDT_YK_40")){
		    	sprChnl = "1192";
	    	}else if(sprChnl.equals("XG_GDT_LS_41")){
		    	sprChnl = "1193";
	    	}else if(sprChnl.equals("XG_GDT_LS_42")){
		    	sprChnl = "1194";
	    	}else if(sprChnl.equals("XG_GDT_LS_43")){
		    	sprChnl = "1195";
	    	}else if(sprChnl.equals("XG_GDT_LS_44")){
		    	sprChnl = "1196";
	    	}else if(sprChnl.equals("XG_GDT_LS_45")){
		    	sprChnl = "1197";
	    	}else if(sprChnl.equals("XG_GDT_LS_46")){
		    	sprChnl = "1198";
	    	}else if(sprChnl.equals("XG_GDT_LS_47")){
		    	sprChnl = "1199";
	    	}else if(sprChnl.equals("XG_GDT_LS_48")){
		    	sprChnl = "1200";
	    	}else if(sprChnl.equals("XG_GDT_LS_49")){
		    	sprChnl = "1201";
	    	}else if(sprChnl.equals("XG_GDT_LS_50")){
		    	sprChnl = "1202";
	    	}else if(sprChnl.equals("XG_GDT_LS_51")){
		    	sprChnl = "1203";
	    	}else if(sprChnl.equals("XG_GDT_LS_52")){
		    	sprChnl = "1204";
	    	}else if(sprChnl.equals("XG_GDT_LS_53")){
		    	sprChnl = "1205";
	    	}else if(sprChnl.equals("XG_GDT_LS_54")){
		    	sprChnl = "1206";
	    	}else if(sprChnl.equals("XG_GDT_LS_55")){
		    	sprChnl = "1207";
	    	}else if(sprChnl.equals("XG_GDT_LS_56")){
		    	sprChnl = "1208";
	    	}else if(sprChnl.equals("XG_GDT_LS_57")){
		    	sprChnl = "1209";
	    	}else if(sprChnl.equals("XG_GDT_LS_58")){
		    	sprChnl = "1210";
	    	}else if(sprChnl.equals("XG_GDT_LS_59")){
		    	sprChnl = "1211";
	    	}else if(sprChnl.equals("XG_GDT_LS_60")){
		    	sprChnl = "1212";
	    	}else{
	    		sprChnl = "9999";
	    	}
	    	return sprChnl;
	    }
	
	
	public static void main(String[] args) {
		Map<String, String> map=new HashMap<String, String>();
		for (int i = 0; i < 1000555; i++) {
			String a=CommonUtil.getOrderCode();
			System.out.println(a);
			if(map.containsKey(a)){
				System.out.println("---has--"+i+"---"+a);
				break;
			}
			map.put(a, a);
			
		}
		
	}
}
