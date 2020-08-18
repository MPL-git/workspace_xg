package com.jf.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;

import org.apache.commons.codec.binary.Base64;

import com.gzs.common.utils.StringUtil;

public class FileUtil
{
  private static String defaultPath = "";
  
  private static String prePath="";
  
  private static Random random=new Random();
  
  static
  {
    InputStream stream = FileUtil.class.getResourceAsStream("/base_config.properties");
    try
    {
      Properties properties = new Properties();
      properties.load(stream);
      defaultPath = properties.getProperty("annex.filePath");
	  prePath = properties.getProperty("annex.filePre");
      stream.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public static String readTxtFile(InputStream inputStream)
  {
    StringBuffer buffer = new StringBuffer();
    try
    {
      InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
      BufferedReader bufferedReader = new BufferedReader(reader);
      String line = null;
      while ((line = bufferedReader.readLine()) != null) {
        buffer.append(line + "\n");
      }
      bufferedReader.close();
      reader.close();
      inputStream.close();
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return buffer.toString();
  }
  
  public static String readTxtFile(File file)
  {
    StringBuffer buffer = new StringBuffer();
    if ((file.isFile()) && (file.exists())) {
      try
      {
        FileInputStream inputStream = new FileInputStream(file);
        buffer.append(readTxtFile(inputStream));
      }
      catch (FileNotFoundException e)
      {
        e.printStackTrace();
      }
    }
    return buffer.toString();
  }
  
  public static String readTxtFile(String filePath)
  {
    StringBuffer buffer = new StringBuffer();
    try
    {
      FileInputStream file = new FileInputStream(filePath);
      buffer.append(readTxtFile(file));
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    return buffer.toString();
  }
  
  private static String getExtension(String fileName)
  {
    String ret = "";
    if (fileName != null)
    {
      int index = fileName.lastIndexOf(".");
      if ((index > 0) && (index < fileName.length() - 1)) {
        ret = fileName.substring(index + 1);
      }
    }
    return ret;
  }
  
  public static boolean isProhibitType(String fileName)
  {
    if (fileName != null)
    {
      String extension = getExtension(fileName);
      if (("JSP".equalsIgnoreCase(extension)) || ("INI".equalsIgnoreCase(extension)) || ("EXE".equalsIgnoreCase(extension)) || ("COM".equalsIgnoreCase(extension)) || ("HTM".equalsIgnoreCase(extension)) || ("HTML".equalsIgnoreCase(extension)) || ("CSS".equalsIgnoreCase(extension)) || ("JS".equalsIgnoreCase(extension)) || ("SH".equalsIgnoreCase(extension))) {
        return true;
      }
      return false;
    }
    return true;
  }
  
  public static boolean isProhibitType(File file)
  {
    return isProhibitType(file.getName());
  }
  
  public static boolean isImageFile(String fileName)
  {
    if (fileName != null)
    {
      String extension = getExtension(fileName);
      if (("JPG".equalsIgnoreCase(extension)) || ("JPEG".equalsIgnoreCase(extension)) || ("PNG".equalsIgnoreCase(extension)) || ("GIF".equalsIgnoreCase(extension))) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isImageFile(File file)
  {
    return isImageFile(file.getName());
  }
  
  public static String getRandomFileName(String fileName , int type, int mchtId)
  {
		String extension = "";
		if ((fileName.lastIndexOf(".") != -1) && (fileName.lastIndexOf(".") != 0)) {
			extension = fileName.substring(fileName.lastIndexOf("."));
		}
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMM");
		String sYear = sFormat.format(new Date());
		String subFolder = "";
		if (type == 1) {
			subFolder = "app";
		} else if (type == 2) {
			subFolder = "product/" + mchtId;
		} else if (type == 3) {
			subFolder = "activity";
		} else if (type == 6) {
			subFolder = "sport";
		} else if (type == 7) {
			subFolder = "activityGroup";
		} else if (type == 8) {
			subFolder = "attachment";
		} else if (type == 9) {
			subFolder = "mallCategory";
		} else if(type == 10) {
			subFolder = "weChatApplet";
		} else if(type == 11) {
			subFolder = "informationWordFile";
		} else if(type == 12){
			subFolder = "mcht/businessLicensePic";
		} else if(type == 13){
			subFolder = "weixin/weixinXcxSprDtl";
		} else if(type == 14){
			subFolder = "task";
		}else {
			subFolder = "common";
		}
		subFolder=sYear.concat(File.separator).concat(subFolder);
//		subFolder = subFolder.concat(File.separator).concat(sYear);
		File folder = new File(defaultPath.concat(File.separator).concat(subFolder));
		if (!folder.exists()) {
			folder.mkdirs();
		} else if (!folder.isDirectory()) {
			System.out.println("目录名被文件占用");
			return null;
		}
		if(type == -1){
			return File.separator.concat(subFolder).concat(File.separator).concat(fileName);		
		}else{
			String subName=String.valueOf(new Date().getTime());
			String sFileName = subName.concat("_").concat(String.valueOf(random.nextInt(10000))).concat(extension);
			return File.separator.concat(subFolder).concat(File.separator).concat(sFileName);
		}
	}
  
  public static OutputStream getNewFileOutputStream(String filePath)
  {
    OutputStream out = null;
    File dest = new File(defaultPath.concat(filePath));
    try
    {
      out = new FileOutputStream(dest);
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    return out;
  }
  
  private static boolean isExists(String path)
  {
    String sFileName = defaultPath.concat(File.separator).concat(path);
    File file = new File(sFileName);
    return file.exists();
  }
  
  public static String BASE64Encoder(String filePath)
  {
    byte[] data = null;
    try
    {
      InputStream in = new FileInputStream(filePath);
      data = new byte[in.available()];
      in.read(data);
      in.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    Base64 base64 = new Base64();
    return new String(base64.encode(data));
  }
  
  public static String saveBase64File(String content, String fileName)
  {
    if ((content == null) || (fileName == null)) {
      return null;
    }
    Base64 base64 = new Base64();
    try
    {
      byte[] bytes = base64.decode(content);
      for (int i = 0; i < bytes.length; i++) {
        if (bytes[i] < 0)
        {
          int tmp44_42 = i; byte[] tmp44_41 = bytes;tmp44_41[tmp44_42] = ((byte)(tmp44_41[tmp44_42] + 256));
        }
      }
      String filePath = null;
      for (;;)
      {
        filePath = getRandomFileName(fileName,0,0);
        if (!isExists(filePath)) {
          break;
        }
      }
      File dest = new File(defaultPath.concat(filePath));
      OutputStream out = new FileOutputStream(dest);
      out.write(bytes);
      out.flush();
      out.close();
      return filePath.replace("\\", "/");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  protected static void saveAbsoluteFile(InputStream stream, String filePath)
  {
    File dest = new File(filePath);
    try
    {
      OutputStream destStream = new FileOutputStream(dest);
      BufferedInputStream bis = new BufferedInputStream(stream);
      byte[] buffer = new byte[2048];
      int length = 0;
      while ((length = bis.read(buffer)) != -1) {
        destStream.write(buffer, 0, length);
      }
      destStream.close();
      bis.close();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public static String saveFile(InputStream stream, String fileName, int type, int mchtId)
  {
    String filePath = null;
    if (isProhibitType(fileName)) {
      return null;
    }
    
    if(type==4){
    	filePath=File.separator.concat("activityTemplate").concat(File.separator).concat(fileName);
		File folder = new File(defaultPath.concat(File.separator).concat("activityTemplate"));
		if(!folder.exists()) {
			folder.mkdirs();
		}else if(!folder.isDirectory()) {
			System.out.println("目录名被文件占用");
			return null;
		}
    }else if (type==5){
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMM");
		String sYear = sFormat.format(new Date());
    	filePath=File.separator.concat(sYear).concat(File.separator).concat("apk").concat(File.separator).concat(fileName);
		File folder = new File(defaultPath.concat(File.separator).concat(sYear).concat(File.separator).concat("apk"));
		if(!folder.exists()) {
			folder.mkdirs();
		}else if(!folder.isDirectory()) {
			System.out.println("目录名被文件占用");
			return null;
		}
    }else{
        for (;;)
        {
          filePath = getRandomFileName(fileName,type,mchtId);
          if (!isExists(filePath)) {
            break;
          }
        }
    }
    

    File dest = new File(defaultPath.concat(filePath));
    try
    {
      OutputStream destStream = new FileOutputStream(dest);
      BufferedInputStream bis = new BufferedInputStream(stream);
      byte[] buffer = new byte[2048];
      int length = 0;
      while ((length = bis.read(buffer)) != -1) {
        destStream.write(buffer, 0, length);
      }
      destStream.close();
      bis.close();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return filePath.replace("\\", "/");
  }
  
  public static String saveFile(File file)
  {
    String filePath = null;
    try
    {
      InputStream stream = new FileInputStream(file);
      filePath = saveFile(stream, file.getName(),0,0);
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    return filePath;
  }
  
  public static File getFile(String filePath)
  {
    filePath = filePath.replace("/", File.separator);
    File file = new File(defaultPath.concat(filePath));
    if ((file.exists()) && (file.isFile())) {
      return file;
    }
    return null;
  }
  
  public static File getFile(String filePath, String defaultFile)
  {
    File file = getFile(filePath);
    if (file == null) {
      file = getFile(defaultFile);
    }
    return file;
  }
  
  
  public static String getMiddleImageName(String sourceImageName){
	   if(StringUtil.isEmpty(sourceImageName)||sourceImageName.lastIndexOf(".")<0){
		   return sourceImageName;
	   }
	   String middleImageName=sourceImageName.substring(0,sourceImageName.lastIndexOf("."))+"_M"+sourceImageName.substring(sourceImageName.lastIndexOf("."));
	   return  middleImageName;
  }
  public static String getSmallImageName(String sourceImageName){
	   if(StringUtil.isEmpty(sourceImageName)||sourceImageName.lastIndexOf(".")<0){
		   return sourceImageName;
	   }
	   String smallImageName=sourceImageName.substring(0,sourceImageName.lastIndexOf("."))+"_S"+sourceImageName.substring(sourceImageName.lastIndexOf("."));
	   return  smallImageName;
  }
  public static String getQuality70ImageName(String sourceImageName){
	   if(StringUtil.isEmpty(sourceImageName)||sourceImageName.lastIndexOf(".")<0){
		   return sourceImageName;
	   }
	   String quality70ImageName=sourceImageName.substring(0,sourceImageName.lastIndexOf("."))+"_70Q"+sourceImageName.substring(sourceImageName.lastIndexOf("."));
	   return  quality70ImageName;
 }
  
  
  
  public static void main(String[] args)
  {
    System.out.println(BASE64Encoder("C:\\Users\\LY\\Downloads\\898441954111004-A001-20150113.csv"));
  }
  
  
  /**
   * 
   * @Title ImgWatermark   
   * @Description TODO(合成图片 水印)   
   * @author pengl
   * @date 2018年1月18日 上午11:00:06
   * @param source	输入源
   * @param output	输出源
   * @param width	宽
   * @param height	高
   * @param position	水印位置
   * @param watermark	水印图片地址
   * @param transparency	透明度
   * @param quality	图片质量
   */
  public static void ImgWatermark(String source, String output, int width, int height, Position position, String watermark, float transparency, float quality) throws IOException {
          Thumbnails.of(defaultPath+source).size(width, height).watermark(position, ImageIO.read(new File(defaultPath+watermark)), transparency).outputQuality(quality).toFile(defaultPath+output);
  }  

  public static void ImgWatermark(File source, String output, int width, int height, Position position, String watermark, float transparency, float quality) {  
      try {  
          Thumbnails.of(source).size(width, height).watermark(position, ImageIO.read(new File(defaultPath+watermark)), transparency).outputQuality(quality).toFile(defaultPath+output);  
      } catch (IOException e) {  
          e.printStackTrace();  
      }  
  }   
  
  
}
