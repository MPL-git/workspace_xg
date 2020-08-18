package com.jf.common.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
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
import javax.swing.ImageIcon;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class FileUtil {
	public static String defaultPath = "";

	public static String prePath = "";

	private static Random random = new Random();

	static {
		InputStream stream = FileUtil.class.getResourceAsStream("/config.properties");
		try {
			Properties properties = new Properties();
			properties.load(stream);
			defaultPath = properties.getProperty("annex.filePath");
			prePath = properties.getProperty("annex.filePre");
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String readTxtFile(InputStream inputStream) {
		StringBuffer buffer = new StringBuffer();
		try {
			InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				buffer.append(line + "\n");
			}
			bufferedReader.close();
			reader.close();
			inputStream.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	public static String readTxtFile(File file) {
		StringBuffer buffer = new StringBuffer();
		if ((file.isFile()) && (file.exists())) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				buffer.append(readTxtFile(inputStream));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return buffer.toString();
	}

	public static String readTxtFile(String filePath) {
		StringBuffer buffer = new StringBuffer();
		try {
			FileInputStream file = new FileInputStream(filePath);
			buffer.append(readTxtFile(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	private static String getExtension(String fileName) {
		String ret = "";
		if (fileName != null) {
			int index = fileName.lastIndexOf(".");
			if ((index > 0) && (index < fileName.length() - 1)) {
				ret = fileName.substring(index + 1);
			}
		}
		return ret;
	}

	public static boolean isProhibitType(String fileName) {
		if (fileName != null) {
			String extension = getExtension(fileName);
			if (("JSP".equalsIgnoreCase(extension)) || ("INI".equalsIgnoreCase(extension)) || ("EXE".equalsIgnoreCase(extension)) || ("COM".equalsIgnoreCase(extension)) || ("HTM".equalsIgnoreCase(extension)) || ("HTML".equalsIgnoreCase(extension)) || ("CSS".equalsIgnoreCase(extension)) || ("JS".equalsIgnoreCase(extension)) || ("SH".equalsIgnoreCase(extension))) {
				return true;
			}
			return false;
		}
		return true;
	}

	public static boolean isProhibitType(File file) {
		return isProhibitType(file.getName());
	}

	public static boolean isImageFile(String fileName) {
		if (fileName != null) {
			String extension = getExtension(fileName);
			if (("JPG".equalsIgnoreCase(extension)) || ("JPEG".equalsIgnoreCase(extension)) || ("PNG".equalsIgnoreCase(extension)) || ("GIF".equalsIgnoreCase(extension))) {
				return true;
			}
		}
		return false;
	}

	public static boolean isImageFile(File file) {
		return isImageFile(file.getName());
	}

	public static String getRandomFileName(String fileName, int type, int mchtId) {
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
		} else {
			subFolder = "common";
		}

		subFolder = sYear.concat(File.separator).concat(subFolder);
		// subFolder = subFolder.concat(File.separator).concat(sYear);
		File folder = new File(defaultPath.concat(File.separator).concat(subFolder));
		if (!folder.exists()) {
			folder.mkdirs();
		} else if (!folder.isDirectory()) {
			System.out.println("目录名被文件占用");
			return null;
		}

		String subName = String.valueOf(new Date().getTime());
		String sFileName = subName.concat("_").concat(String.valueOf(random.nextInt(10000))).concat(extension);
		return File.separator.concat(subFolder).concat(File.separator).concat(sFileName);
	}

	public static OutputStream getNewFileOutputStream(String filePath) {
		OutputStream out = null;
		File dest = new File(defaultPath.concat(filePath));
		try {
			out = new FileOutputStream(dest);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return out;
	}

	private static boolean isExists(String path) {
		String sFileName = defaultPath.concat(File.separator).concat(path);
		File file = new File(sFileName);
		return file.exists();
	}

	public static String BASE64Encoder(String filePath) {
		byte[] data = null;
		try {
			InputStream in = new FileInputStream(filePath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Base64 base64 = new Base64();
		return new String(base64.encode(data));
	}

	public static String saveBase64File(String content, String fileName, int type, int mchtId) {

		if ((content == null) || (fileName == null)) {
			return null;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// 生成jpeg图片
			ByteArrayInputStream stream = null;
			byte[] bytes1 = decoder.decodeBuffer(content);
			stream = new ByteArrayInputStream(bytes1);
			String filePath = FileUtil.saveFile(stream, fileName, type, mchtId);
			stream.close();
			return filePath;
		} catch (Exception e) {
			return null;
		}

	}

	// public static String saveBase64File(String content, String fileName, int
	// type, int mchtId) {
	//
	// if ((content == null) || (fileName == null)) {
	// return null;
	// }
	// BASE64Decoder decoder = new BASE64Decoder();
	// try {
	// // Base64解码
	// byte[] b = decoder.decodeBuffer(content);
	// for (int i = 0; i < b.length; ++i) {
	// if (b[i] < 0) {// 调整异常数据
	// b[i] += 256;
	// }
	// }
	// // 生成jpeg图片
	// InputStream in = new ByteArrayInputStream(b);
	// String filePath = FileUtil.saveFile(in, fileName, type, mchtId);
	// in.close();
	// return filePath;
	// } catch (Exception e) {
	// return null;
	// }
	//
	// }

	// public static String saveBase64File(String content, String fileName, int
	// type, int mchtId)
	// {
	// if ((content == null) || (fileName == null)) {
	// return null;
	// }
	// Base64 base64 = new Base64();
	// try
	// {
	// byte[] bytes = base64.decode(content);
	// for (int i = 0; i < bytes.length; i++) {
	// if (bytes[i] < 0)
	// {
	// int tmp44_42 = i; byte[] tmp44_41 = bytes;tmp44_41[tmp44_42] =
	// ((byte)(tmp44_41[tmp44_42] + 256));
	// }
	// }
	// String filePath = null;
	// for (;;)
	// {
	// filePath = getRandomFileName(fileName,type,mchtId);
	// if (!isExists(filePath)) {
	// break;
	// }
	// }
	// File dest = new File(defaultPath.concat(filePath));
	// OutputStream out = new FileOutputStream(dest);
	// out.write(bytes);
	// out.flush();
	// out.close();
	// return filePath.replace("\\", "/");
	// }
	// catch (IOException e)
	// {
	// e.printStackTrace();
	// }
	// return null;
	// }

	protected static void saveAbsoluteFile(InputStream stream, String filePath) {
		File dest = new File(filePath);
		try {
			OutputStream destStream = new FileOutputStream(dest);
			BufferedInputStream bis = new BufferedInputStream(stream);
			byte[] buffer = new byte[2048];
			int length = 0;
			while ((length = bis.read(buffer)) != -1) {
				destStream.write(buffer, 0, length);
			}
			destStream.close();
			bis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String saveFile(InputStream stream, String fileName, int type, int mchtId) {
		String filePath = null;
		if (isProhibitType(fileName)) {
			return null;
		}

		for (;;) {
			filePath = getRandomFileName(fileName, type, mchtId);
			if (!isExists(filePath)) {
				break;
			}
		}
		File dest = new File(defaultPath.concat(filePath));
		try {
			OutputStream destStream = new FileOutputStream(dest);
			BufferedInputStream bis = new BufferedInputStream(stream);
			byte[] buffer = new byte[2048];
			int length = 0;
			while ((length = bis.read(buffer)) != -1) {
				destStream.write(buffer, 0, length);
			}
			destStream.close();
			bis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath.replace("\\", "/");
	}

	public static String saveFile(File file) {
		String filePath = null;
		try {
			InputStream stream = new FileInputStream(file);
			filePath = saveFile(stream, file.getName(), 0, 0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return filePath;
	}

	public static File getFile(String filePath) {
		filePath = filePath.replace("/", File.separator);
		File file = new File(defaultPath.concat(filePath));
		if ((file.exists()) && (file.isFile())) {
			return file;
		}
		return null;
	}

	public static File getFile(String filePath, String defaultFile) {
		File file = getFile(filePath);
		if (file == null) {
			file = getFile(defaultFile);
		}
		return file;
	}

	public static void main(String[] args) {
		Position position = Positions.BOTTOM_RIGHT;
		try {
			Thumbnails.of("D://home/jfweb/tomcat/files/201809/common/1537275164235_5189.jpg").scale(1f).watermark(position, ImageIO.read(new File("D://home/jfweb/tomcat/files/watermark/mcht_bl_pic_WK.png")), 1).toFile("F:/111_warter.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// imgWatermark("F:/1528858895105_7591_70Q.jpg",
		// "F:/1528858895105_7591_70Q_warter.jpg", position,
		// "F:/warterMark.png", 1f, 1f);

	}

	// 图片转化成base64字符串
	public static String GetImageStr(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	/**
	 * 
	 * @Title ImgWatermark
	 * @Description TODO(合成图片 水印)
	 * @date 2018年1月18日 上午11:00:06
	 * @param source
	 *            输入源
	 * @param output
	 *            输出源
	 * @param width
	 *            宽
	 * @param height
	 *            高
	 * @param position
	 *            水印位置
	 * @param watermark
	 *            水印图片地址
	 * @param transparency
	 *            透明度
	 * @param quality
	 *            图片质量
	 */
	public static void imgWatermarkAndReSize(String sourcePic, String targetPic, Position position, String watermarkPic, float opacity, int width, int height) throws IOException {
		Thumbnails.of(defaultPath + sourcePic).watermark(position, ImageIO.read(new File(defaultPath + watermarkPic)), opacity).size(width, height).toFile(defaultPath + targetPic);
	}

	/**
	 * 
	 * @Title ImgWatermark
	 * @Description TODO(合成图片 水印)
	 * @date 2018年1月18日 上午11:00:06
	 * @param source
	 *            输入源
	 * @param output
	 *            输出源
	 * @param width
	 *            宽
	 * @param height
	 *            高
	 * @param position
	 *            水印位置
	 * @param watermark
	 *            水印图片地址
	 * @param transparency
	 *            透明度
	 * @param quality
	 *            图片质量
	 */
	public static void imgWatermark(String sourcePic, String targetPic, Position position, String watermarkPic, float opacity) throws IOException {
		Thumbnails.of(defaultPath + sourcePic).scale(1f).watermark(position, ImageIO.read(new File(defaultPath + watermarkPic)), opacity).toFile(defaultPath + targetPic);
	}

	/**
	 * 按指定大小把图片进行缩放（会遵循原图高宽比例）
	 * 
	 * @param inSrc
	 * @param outSrc
	 * @param height
	 * @param width
	 * @return
	 */

	public static boolean resizeImage(String sourceFile, String targetFile, int width, int height) {
		sourceFile = defaultPath.concat(sourceFile);
		targetFile = defaultPath.concat(targetFile);
		try {
			Image image = new ImageIcon(sourceFile).getImage();
			BufferedImage bf = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);
			bf.getGraphics().drawImage(image, 0, 0, null);
			Thumbnails.of(bf).size(width, height).toFile(targetFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return copyFile(sourceFile, targetFile);
		}
		return true;
	}

	public static boolean compressImage(String sourceFile, String targetFile, float quality) {
		sourceFile = defaultPath.concat(sourceFile);
		targetFile = defaultPath.concat(targetFile);
		try {
			Image image = new ImageIcon(sourceFile).getImage();
			BufferedImage bf = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);
			bf.getGraphics().drawImage(image, 0, 0, null);
			Thumbnails.of(bf).scale(1f).outputQuality(quality).toFile(targetFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return copyFile(sourceFile, targetFile);
		}
		return true;
	}

	/**
	 * 复制单个文件
	 * 
	 * @param sourceFile
	 * @param targetFile
	 */
	public static boolean copyFile(String sourceFile, String targetFile) {
		try {
			int byteread = 0;
			File oldfile = new File(sourceFile);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(sourceFile); // 读入原文件
				FileOutputStream fs = new FileOutputStream(targetFile);
				byte[] buffer = new byte[2048];
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
