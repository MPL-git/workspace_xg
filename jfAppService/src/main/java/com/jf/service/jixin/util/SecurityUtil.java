package com.jf.service.jixin.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * 接入认证信息生成工具
 */
public final class SecurityUtil {
    private static final ThreadLocal<MessageDigest> SHA1_DIGEST = new ThreadLocal() {
        @Override
        protected MessageDigest initialValue() {
            try {
                return MessageDigest.getInstance("SHA1");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return null;
        }
    };

    private static final ThreadLocal<MessageDigest> MD5_DIGEST = new ThreadLocal() {
        @Override
        protected MessageDigest initialValue() {
            try {
                return MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return null;
        }
    };

    /**
     * 生成sig签名消息，格式: SHA1(accountSid+authToken+timestamp)
     * （注：“+”号为合并意思，不包含在被加密的字符串中），用十六进制编码，输出32位小写字母
     */
    public static String generateSigParameter(String accountSid, String authToken, String timestamp) {
        String plainSignature = accountSid + authToken + timestamp;
        byte[] result = SHA1_DIGEST.get().digest(plainSignature.getBytes());
        return HexUtil.encodeHexString(result);
    }

    /**
     * 生成一键登录sign，格式: sign = MD5(appid + version + msgid + systemtime + strictcheck + token + APPSecret)
     * （注：“+”号为合并意思，不包含在被加密的字符串中），用十六进制编码，输出32位大写字母
     */
    public static String generateLoginTokenSign(String appId, String version, String msgId, String systemTime, String strictCheck, String token, String appSecret) {
        String plainSignature = appId + version + msgId + systemTime + strictCheck + token + appSecret;
        byte[] result = MD5_DIGEST.get().digest(plainSignature.getBytes());
        return HexUtil.encodeHexString(result).toUpperCase();
    }

    /**
     * 生成本机校验sign，格式: sign = MD5(appid + msgid + systemtime + phone + token + appKey)
     * （注：“+”号为合并意思，不包含在被加密的字符串中），用十六进制编码，输出32位大写字母
     */
    public static String generatePhoneVerifySign(String appId, String msgId, String systemTime, String phone, String token, String appKey) {
        String plainSignature = appId +  msgId + systemTime + phone + token + appKey;
        byte[] result = MD5_DIGEST.get().digest(plainSignature.getBytes());
        return HexUtil.encodeHexString(result).toUpperCase();
    }

    /**
     * 生成时间戳，格式为：yyyyMMddHHmmss
     *
     * @param date
     * @return
     */
    public static String generateTimeStamp(Date date) {
        return DateUtil.format(date, DateUtil.DateType.TIMESTAMP_NO_MILLISECOND);
    }

    /**
     * 生成时间戳，格式为：yyyyMMddHHmmssSS
     *
     * @param date
     * @return
     */
    public static String generateSystemTime(Date date) {
        return DateUtil.format(date, DateUtil.DateType.TIMESTAMP_WITH_MILLISECOND);
    }

    /**
     * 生成时间戳，格式为：yyyyMMddHHmmssSSS
     */
    public static String generateFullTimestamp(Date date) {
        return DateUtil.format(date, DateUtil.DateType.TIMESTAMP_FULL);
    }

    /**
     * 生成接入header Authorization字段，该字段格式为：Base64(accountSid+:+timestamp)
     *
     * @param accountSid
     * @param timestamp
     * @return
     */
    public static String generateAuthorization(String accountSid, String timestamp) {
        String authorization = accountSid + ":" + timestamp;
        return Base64.encode(authorization.getBytes(Charset.forName("utf-8")));
    }
}