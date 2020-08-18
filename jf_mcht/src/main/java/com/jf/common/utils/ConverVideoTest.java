/**
 * @ClassName TODO
 * @Description
 * @author chengh
 * @date
 */
package com.jf.common.utils;

/**
 * @ClassName ConverVideoTest
 * @Description TODO
 * @author chengh
 * @date 2019年8月27日 下午6:23:38
 */
public class ConverVideoTest {
	
	/*本地测试专用--zoutao*/
	 public static void main(String[] args) {
		System.out.println("88888"+ConverVideoUtils.processVideoFormat("D:/home/jfweb/tomcat/files/201908/video/2/test1.flv"));
	}
 
	//web调用
    public boolean run(String mchtId,String yuanPATH,String filename_extension) {
        try {
            // 转码和截图功能开始
        	
            //String filePath = "D:/testfile/video/rmTest.rm";  //本地源视频测试
        	
        	String filePath = yuanPATH;				//web传入的源视频
        	System.out.println("ConverVideoTest说:传入工具类的源视频为:"+filePath);
        	
            ConverVideoUtils zout = new ConverVideoUtils(filePath);  //传入path
            String targetExtension = ".mp4";  				//设置转换的格式
            boolean isDelSourseFile = true;
            
            //删除源文件
            boolean beginConver = zout.beginConver(mchtId,targetExtension,isDelSourseFile,filename_extension);
            System.out.println(beginConver);
            return beginConver;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
