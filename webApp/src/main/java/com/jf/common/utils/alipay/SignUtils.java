package com.jf.common.utils.alipay;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

import com.jf.common.utils.Base64;


/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月31日 下午8:28:22 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class SignUtils {
	
	 	private static final String ALGORITHM = "RSA";

	    private static final String SIGN_ALGORITHMS = "SHA256WithRSA";

	    private static final String DEFAULT_CHARSET = "UTF-8";

	    public static String sign(String content, String privateKey) {
	        try {
	            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
	                    Base64.decode(privateKey));
	            KeyFactory keyf = KeyFactory.getInstance(ALGORITHM);
	            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

	            java.security.Signature signature = java.security.Signature
	                    .getInstance(SIGN_ALGORITHMS);

	            signature.initSign(priKey);
	            signature.update(content.getBytes(DEFAULT_CHARSET));

	            byte[] signed = signature.sign();

	            return new String(Base64.encode(signed));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return null;
	    }

}
