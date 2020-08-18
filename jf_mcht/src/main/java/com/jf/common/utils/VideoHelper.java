package com.jf.common.utils;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.jf.vo.video.VideoSplitInfo;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * 主要用于小视频相关功能
 *
 * @author luoyb
 * Created on 2019/9/18
 */
public final class VideoHelper {

    private static Logger logger = LoggerFactory.getLogger(VideoHelper.class);

    /**
     * 共取6帧：第一帧 及 再将视频均分5段各自随机取一帧
     *
     * @param defaultPath 服务器物理地址
     * @param videoUrl    视频相对地址
     */
    public static VideoSplitInfo splitVideo(String defaultPath, String videoUrl,String imgUrl) {
        FFmpegFrameGrabber frameGrabber = null;
        VideoSplitInfo videoSplitInfo = new VideoSplitInfo();
        videoSplitInfo.setVideoUrl(videoUrl);
        try {
            frameGrabber = FFmpegFrameGrabber.createDefault(defaultPath + videoUrl);
            frameGrabber.start();

            //视频总时长(s)
            long frameTime = frameGrabber.getLengthInTime() / 1000000;
            videoSplitInfo.setVideoTime(frameTime);

            //视频总帧数
            int frameLength = frameGrabber.getLengthInFrames();
            videoSplitInfo.setFrameLength(frameLength);

            //截帧列表
            List<Integer> splitFrameList = randomToSixPortion(frameLength);

            String relativeUrlName = imgUrl.substring(0, imgUrl.lastIndexOf("."));
            int index = 1;
            for (int i = 0; i < frameLength; i++) {
                Frame frame = frameGrabber.grabImage();
                if (splitFrameList.contains(i)) {
                    String name = relativeUrlName + "_" + (index++);
                    videoSplitInfo.getFramePicList().add(name + ".jpg");
                    if (i == 1) {
                        videoSplitInfo.setFirstFrame(name + ".jpg");
                    }
                    splitFrame(frame, defaultPath + name + ".jpg"); //取帧并保存图片
                }
            }
        } catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
            e.printStackTrace();
        } finally {
            if (frameGrabber != null) {
                try {
                    frameGrabber.stop();
                } catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
                    e.printStackTrace();
                }
            }
        }
        videoSplitInfo.setVideoThumbnails(Joiner.on(",").join(videoSplitInfo.getFramePicList()));
        return videoSplitInfo;
    }

    //取帧并保存图片
    private static void splitFrame(Frame frame, String fullName) {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage image = converter.getBufferedImage(frame);
        FileOutputStream outFile = null;
        try {
            outFile = new FileOutputStream(fullName);
            ImageIO.write(image, "jpg", outFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //共取6帧：第一帧 及 再将视频均分5段各自随机取一帧
    private static List<Integer> randomToSixPortion(int frameLength) {
        List<Integer> list = Lists.newArrayList();
        list.add(1);

        int avg = frameLength / 5;
        list.add(CommonUtil.rand(2, avg));
        list.add(CommonUtil.rand(avg + 1, avg * 2));
        list.add(CommonUtil.rand(avg * 2 + 1, avg * 3));
        list.add(CommonUtil.rand(avg * 3 + 1, avg * 4));
        list.add(CommonUtil.rand(avg * 4 + 1, frameLength));
        return list;
    }

    /**
     * 获取视频文件的code
     */
    public static int getVideoCodeC(String filePath) {
        FFmpegFrameGrabber frameGrabber = null;
        try {
            frameGrabber = FFmpegFrameGrabber.createDefault(filePath);
            frameGrabber.start();
            return frameGrabber.getVideoCodec();
        } catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (frameGrabber != null) {
                try {
                    frameGrabber.close();
                } catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    /**
     * 视频格式转化为mp4
     * ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
     */
    public static void convertToMP4(String inputFilePath,String outputFilePath) {
        logger.debug("调用了ffmpeg.exe工具");

        List<String> commands = Lists.newArrayList();
        commands.add("ffmpeg");         //ffmpeg.exe工具地址D:/ffmpeg/bin/ffmpeg.exe
        commands.add("-i");
        commands.add(inputFilePath);            //源视频路径
        commands.add("-vcodec");
        commands.add("h263");  //
        commands.add("-ab");        //新增4条
        commands.add("128");      //高品质:128 低品质:64
        commands.add("-acodec");
        commands.add("mp3");      //音频编码器：原libmp3lame
        commands.add("-ac");
        commands.add("2");       //原1
        commands.add("-ar");
        commands.add("22050");   //音频采样率22.05kHz
        commands.add("-r");
        commands.add("29.97");  //高品质:29.97 低品质:15
        commands.add("-c:v");
        commands.add("libx264");    //视频编码器：视频是h.264编码格式
        commands.add("-strict");
        commands.add("-2");
        commands.add("-profile:v");
        commands.add("baseline");
        commands.add(outputFilePath);  // //转码后的路径+名称，是指定后缀的视频
        logger.debug("ffmpeg输入的命令:" + Joiner.on(" ").join(commands));

        try {
            //多线程处理加快速度-解决rmvb数据丢失builder名称要相同
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            Process p = builder.start();   //多线程处理加快速度-解决数据丢失
            new ReadStreamThread(p.getInputStream()).start();
            new ReadStreamThread(p.getErrorStream()).start();
            p.waitFor();        //进程等待机制，必须要有，否则不生成mp4！！！

            logger.debug("生成mp4视频为:" + outputFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ReadStreamThread extends Thread {
        private final InputStream inputStream;

        private ReadStreamThread(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public void run() {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream));
            try {
                String lineC = null;
                while ((lineC = bufferedReader.readLine()) != null) {
                    if (StringUtils.hasText(lineC)) {
                        logger.debug(lineC); //打印mencoder转换过程代码}
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
