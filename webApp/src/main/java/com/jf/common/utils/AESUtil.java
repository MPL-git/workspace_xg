package com.jf.common.utils;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

	private static String cipherAlgorithm = "AES/CBC/PKCS7Padding";
	private static String keyAlgorithm = "AES";
	/**
	 * 生成指定长度的随机字符串
	 * 
	 * @param length
	 *            指定字符串长度
	 * @return
	 */
	public static String generateLenString(int length) {
		char[] cResult = new char[length];
		int[] flag = { 0, 0, 0 }; // A-Z, a-z, 0-9
		int i = 0;
		while (flag[0] == 0 || flag[1] == 0 || flag[2] == 0 || i < length) {
			i = i % length;
			int f = (int) (Math.random() * 3 % 3);
			if (f == 0)
				cResult[i] = (char) ('A' + Math.random() * 26);
			else if (f == 1)
				cResult[i] = (char) ('a' + Math.random() * 26);
			else
				cResult[i] = (char) ('0' + Math.random() * 10);
			flag[f] = 1;
			i++;
		}
		return new String(cResult);
	}
	
	public static byte[] encryptAES(byte[] plainBytes, byte[] keyBytes,byte[] ivBytes,String charset) throws Exception {

		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
//			Cipher cipher = Cipher.getInstance(cipherAlgorithm);
			Cipher cipher = Cipher.getInstance(cipherAlgorithm,"BC");
			SecretKey secretKey = new SecretKeySpec(keyBytes, keyAlgorithm);
			IvParameterSpec ivspec = new IvParameterSpec(ivBytes);

			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);

			byte[] encryptedBlock = cipher.doFinal(plainBytes);
			return encryptedBlock;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("AES加密失败");
		}
	}

	/**
	 * AES解密
	 * 
	 * @param cryptedBytes
	 *            密文字节数组
	 * @param keyBytes
	 *            对称密钥字节数组
	 * @param useBase64Code
	 *            是否使用Base64编码
	 * @param charset
	 *            编码格式
	 * @return byte[]
	 */
	public static byte[] decryptAES(byte[] cryptedBytes, byte[] keyBytes,byte[] ivBytes,String charset) throws Exception {


		try {
//			Cipher cipher = Cipher.getInstance(cipherAlgorithm);
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance(cipherAlgorithm,"BC");
			SecretKey secretKey = new SecretKeySpec(keyBytes, keyAlgorithm);
			IvParameterSpec ivspec = new IvParameterSpec(ivBytes);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);

			byte[] decryptedBlock = cipher.doFinal(cryptedBytes);

			return decryptedBlock;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("AES解密失败");
		}
	}
}
