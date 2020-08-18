package com.jf.common.utils;

import java.io.IOException;   
import java.io.InputStream;   
import java.util.Properties;   
 
public class PropetiesUtil {   
  
    public final static String CONFIG_APP   =  "application.properties"; 
    public final static String CONFIG_COMM   =  "comm.properties"; 
    public final static String MOBILE_COMM   =  "mobile/comm_mobile.properties"; 
       
    public static Object getKeyInfo(String filePath,String keyName){   
        InputStream proIn = PropetiesUtil.class.getClassLoader().getResourceAsStream(filePath);   
       Properties pro = new Properties();   
        try {   
            pro.load(proIn);   
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
        //back = pro.getProperty(keyName);   
        return pro.get(keyName);   
   }   
 
}