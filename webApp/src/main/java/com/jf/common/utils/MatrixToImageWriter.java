package com.jf.common.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class MatrixToImageWriter {
	private static final int IMAGE_WIDTH = 100;
	private static final int IMAGE_HEIGHT = 100;
	private static final int IMAGE_HALF_WIDTH = IMAGE_WIDTH / 2;
	private static final int FRAME_WIDTH = 2;
	private static MultiFormatWriter mutiWriter = new MultiFormatWriter();

	public static void encode(String content, int width, int height,
			String srcImagePath, String destImagePath) {
		try {
			ImageIO.write(genBarcode(content, width, height, srcImagePath),
					"jpg", new File(destImagePath));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage genBarcode(String content, int width,
			int height, String srcImagePath) throws WriterException,
			IOException {
		BufferedImage scaleImage = scale(srcImagePath, IMAGE_WIDTH,
				IMAGE_HEIGHT, false);
		int[][] srcPixels = new int[IMAGE_WIDTH][IMAGE_HEIGHT];
		for (int i = 0; i < scaleImage.getWidth(); i++) {
			for (int j = 0; j < scaleImage.getHeight(); j++) {
				srcPixels[i][j] = scaleImage.getRGB(i, j);
			}
		}
		Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
		hint.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 生成二维码
		BitMatrix matrix = mutiWriter.encode(content, BarcodeFormat.QR_CODE,
				width, height, hint);
		// 二维矩阵转为一维像素数组
		int halfW = matrix.getWidth() / 2;
		int halfH = matrix.getHeight() / 2;
		int[] pixels = new int[width * height];
		for (int y = 0; y < matrix.getHeight(); y++) {
			for (int x = 0; x < matrix.getWidth(); x++) {
				if (x > halfW - IMAGE_HALF_WIDTH
						&& x < halfW + IMAGE_HALF_WIDTH
						&& y > halfH - IMAGE_HALF_WIDTH
						&& y < halfH + IMAGE_HALF_WIDTH) {
					pixels[y * width + x] = srcPixels[x - halfW
							+ IMAGE_HALF_WIDTH][y - halfH + IMAGE_HALF_WIDTH];
				} else if ((x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
						&& x < halfW - IMAGE_HALF_WIDTH + FRAME_WIDTH
						&& y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
						+ IMAGE_HALF_WIDTH + FRAME_WIDTH)
						|| (x > halfW + IMAGE_HALF_WIDTH - FRAME_WIDTH
								&& x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
								&& y > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
								+ IMAGE_HALF_WIDTH + FRAME_WIDTH)
						|| (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
								&& x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
								&& y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
								- IMAGE_HALF_WIDTH + FRAME_WIDTH)
						|| (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
								&& x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
								&& y > halfH + IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
								+ IMAGE_HALF_WIDTH + FRAME_WIDTH)) {
					pixels[y * width + x] = 0xfffffff;
					// 在图片四周形成边框
				} else {
					// 二维码颜色
					int num1 = (int) (50 - (50.0 - 13.0) / matrix.getHeight()
							* (y + 1));
					int num2 = (int) (165 - (165.0 - 72.0) / matrix.getHeight()
							* (y + 1));
					int num3 = (int) (162 - (162.0 - 107.0)
							/ matrix.getHeight() * (y + 1));
//					Color color = new Color(num1, num2, num3);
					Color color = new Color(0, 0, 0);
					int colorInt = color.getRGB();
					// 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
					pixels[y * width + x] = matrix.get(x, y) ? colorInt
							: 16777215;
					// 0x000000:0xffffff
				}
			}
		}
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		image.getRaster().setDataElements(0, 0, width, height, pixels);
		return image;
	}

	private static BufferedImage scale(String srcImageFile, int height,
			int width, boolean hasFiller) throws IOException {
		double ratio = 0.0; // 缩放比例
		File file = new File(srcImageFile);
		BufferedImage srcImage = ImageIO.read(file);
		Image destImage = srcImage.getScaledInstance(width, height,
				BufferedImage.SCALE_SMOOTH);
		// 计算比例
		if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {
			if (srcImage.getHeight() > srcImage.getWidth()) {
				ratio = (new Integer(height)).doubleValue()
						/ srcImage.getHeight();
			} else {
				ratio = (new Integer(width)).doubleValue()
						/ srcImage.getWidth();
			}
			AffineTransformOp op = new AffineTransformOp(
					AffineTransform.getScaleInstance(ratio, ratio), null);
			destImage = op.filter(srcImage, null);
		}
		return (BufferedImage) destImage;
	}

	public static void main(String[] args) throws WriterException, IOException {
		// 依次为内容(不支持中文),宽,长,中间图标路径,储存路径
//		MatrixToImageWriter.encode("http://www.baidu.com/", 512, 512,"D:\\xinggou.png", "D:\\2019-05-28.jpg");
//		String path = request.getSession().getServletContext().getRealPath("/images/320240jpg.jpg");//获取图像在项目中的路径
//		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
//		ServletContext servletContext = webApplicationContext.getServletContext();
//		String realPath = servletContext.getRealPath("/static/images/xinggou.png");
//		System.out.println(realPath);
		BufferedImage image= MatrixToImageWriter.genBarcode("http://www.baidu.com/", 400, 400,"D:\\xinggou.png");//二维码图片包含醒购logo
		//TODO 保存二维码到服务器上并返回filePath
		String filePath = FileUtil.getRandomFileName("invitation_code.jpg", 1, 0);//二维码图片Url
		InputStream stream = MatrixToImageWriter.class.getResourceAsStream("/config.properties");
		String defaultPath=null;
		try {
			Properties properties = new Properties();
			properties.load(stream);
			defaultPath = properties.getProperty("annex.filePath");
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(defaultPath==null){
			//TODO error
		}
		ImageIO.write(image,"png", new File(defaultPath+filePath));
		System.out.println(filePath);
		//TODO 设置会员(memberInfo)的二维码pic
		
		//TODO 把二维码作为水印加到背景图片上
		BufferedImage bg= ImageIO.read(new File("D:\\invitation_code_bg.png"));//获取背景图片
		Graphics2D g=bg.createGraphics();
		int width=image.getWidth(null) > bg.getWidth() * 5/10? (bg.getWidth() * 5/10) : image.getWidth(null);
		int height=image.getHeight(null) > bg.getHeight() *5 /10? (bg.getHeight() * 5/10) : image.getWidth(null);
		g.drawImage(image,(bg.getWidth()- width)/2,(bg.getHeight()-height)/2,width,height,null);
		g.dispose();
		bg.flush();
		image.flush();
		String filePath2 = filePath.substring(0, filePath.lastIndexOf("."))+"_extension"+filePath.substring(filePath.lastIndexOf("."));
		System.out.println(filePath2);
		ImageIO.write(bg,"png", new File(defaultPath+filePath2));
	}
}