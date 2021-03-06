package com.jf.common.utils;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

/**
 * excel 工具类
 * 
 * @author yjc
 * 
 */
public class VideoProcessing {

		//视频文件路径：
//		public static String videoPath = "D:/test/video";

		//存放截取视频某一帧的图片
//		public static String videoFramesPath = "D:/test/img/";
		/**
		 * 将视频文件帧处理并以“jpg”格式进行存储。
		 * 依赖FrameToBufferedImage方法：将frame转换为bufferedImage对象
		 *
		 * @param videoFileName
		 */
		public static void grabberVideoFramer(String videoPath,String videoCoverPath){
			//最后获取到的视频的图片的路径
//			String videPicture="";
			//Frame对象
			Frame frame = null;
			//标识
			int flag = 0;
			try {
				 /*
	            	获取视频文件
	            */
				FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(videoPath);
				fFmpegFrameGrabber.start();
				
	            //获取视频总帧数
				int ftp = fFmpegFrameGrabber.getLengthInFrames();
//				System.out.println("时长 " + ftp / fFmpegFrameGrabber.getFrameRate() / 60);
				
				while (flag <= ftp) {
					frame = fFmpegFrameGrabber.grabImage();
					/*
					对视频的第一帧进行处理
					 */
					if (frame != null && flag==1) {//flag几就是第几帧
						//文件绝对路径+名字
//						String fileName = videoFramesPath + UUID.randomUUID().toString()+"_" + String.valueOf(flag) + ".jpg";
						String fileName = videoCoverPath;
						
						//文件储存对象
						File outPut = new File(fileName);
						ImageIO.write(FrameToBufferedImage(frame), "jpg", outPut);
						
						//视频第五帧图的路径
//						String savedUrl = PropertyPlaceholder.getProperty("img_path") + outPut.getName();
//						videPicture=savedUrl;
						break;
					}
					flag++;
				}
				fFmpegFrameGrabber.stop();
				fFmpegFrameGrabber.close();
			} catch (Exception E) {
				E.printStackTrace();
			}
//			return videPicture;
		}

		public static BufferedImage FrameToBufferedImage(Frame frame) {
			//创建BufferedImage对象
			Java2DFrameConverter converter = new Java2DFrameConverter();
			BufferedImage bufferedImage = converter.getBufferedImage(frame);
			return bufferedImage;
		}

}
