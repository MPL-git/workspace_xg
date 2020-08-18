package com.jf.common.utils;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class FileUtil {
    public static String defaultPath = "";

    private static String prePath = "";

    private static Random random = new Random();

    private static Random imageSeriveUrlRandom = new Random();

    private static Map<Integer, String> imageSeriveUrlMap = new HashMap<Integer, String>();

    private static int imageSeriveTotalWeight = 0;

    private static Map<Integer, String> videoSeriveUrlMap = new HashMap<Integer, String>();
    private static int videoSeriveTotalWeight = 0;

    static {
        InputStream stream = FileUtil.class.getResourceAsStream("/config.properties");
        try {
            Properties properties = new Properties();
            properties.load(stream);
            defaultPath = properties.getProperty("annex.filePath");
            prePath = properties.getProperty("annex.filePre");

            //图片服务器地址
            String[] imageServiceUrls = properties.getProperty("imageServiceUrl").split(";");
            imageSeriveTotalWeight = 0;
            for (String imageServiceUrl : imageServiceUrls) {
                int currentUrlWeight = Integer.valueOf(imageServiceUrl.substring(imageServiceUrl.indexOf(",") + 1)).intValue();
                for (int i = imageSeriveTotalWeight + 1; i <= imageSeriveTotalWeight + currentUrlWeight; i++) {
                    imageSeriveUrlMap.put(i, imageServiceUrl.substring(0, imageServiceUrl.indexOf(",")));
                }
                imageSeriveTotalWeight = imageSeriveTotalWeight + currentUrlWeight;
            }

            //视频服务器地址
            String[] videoServiceUrls = properties.getProperty("videoServiceUrl").split(";");
            videoSeriveTotalWeight = 0;
            for (String videoServiceUrl : videoServiceUrls) {
                int currentUrlWeight = Integer.valueOf(videoServiceUrl.substring(videoServiceUrl.indexOf(",") + 1));
                if (currentUrlWeight > 0) {
                    for (int i = videoSeriveTotalWeight + 1; i <= videoSeriveTotalWeight + currentUrlWeight; i++) {
                        videoSeriveUrlMap.put(i, videoServiceUrl.substring(0, videoServiceUrl.indexOf(",")));
                    }
                    videoSeriveTotalWeight = videoSeriveTotalWeight + currentUrlWeight;
                }
            }

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
//		subFolder = subFolder.concat(File.separator).concat(sYear);
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

        for (; ; ) {
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

    public static InputStream BaseToInputStream(String base64string) {
        ByteArrayInputStream stream = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes1 = decoder.decodeBuffer(base64string);
            stream = new ByteArrayInputStream(bytes1);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return stream;
    }

    public static String getMiddleImageName(String sourceImageName) {
        String middleImageName = sourceImageName.substring(0, sourceImageName.lastIndexOf(".")) + "_M"
                + sourceImageName.substring(sourceImageName.lastIndexOf("."));
        return middleImageName;
    }

    public static String getSmallImageName(String sourceImageName) {
        String smallImageName = sourceImageName.substring(0, sourceImageName.lastIndexOf(".")) + "_S"
                + sourceImageName.substring(sourceImageName.lastIndexOf("."));
        return smallImageName;
    }

    public static String getMiddleImageMKName(String sourceImageName, Integer activityAreaId) {
        String middleImageName = sourceImageName.substring(0, sourceImageName.lastIndexOf(".")) + "_M_WM_"
                + (activityAreaId == null ? "" : activityAreaId)
                + sourceImageName.substring(sourceImageName.lastIndexOf("."));
        return middleImageName;
    }

    public static String getCompressMKImageName(String sourceImageName, Integer activityAreaId) {
        String smallImageName = sourceImageName.substring(0, sourceImageName.lastIndexOf(".")) + "_70Q_WM_"
                + (activityAreaId == null ? "" : activityAreaId)
                + sourceImageName.substring(sourceImageName.lastIndexOf("."));
        return smallImageName;
    }

    public static String getWMImageName(String sourceImageName) {
        String middleImageName = sourceImageName.substring(0, sourceImageName.lastIndexOf(".")) + "_WM"
                + sourceImageName.substring(sourceImageName.lastIndexOf("."));
        return middleImageName;
    }

    public static void main(String[] args) {
        System.out.println(BASE64Encoder("C:\\Users\\LY\\Downloads\\898441954111004-A001-20150113.csv"));
    }

    /**
     * @param sourceImageName
     * @param quality         压缩比例 如：70表示70%的比例
     * @return
     */
    public static String getCompressImageName(String sourceImageName, float quality) {
        if (quality > 1) {
            return sourceImageName.substring(0, sourceImageName.lastIndexOf(".")) + "_" + "100Q" + sourceImageName.substring(sourceImageName.lastIndexOf("."));
        }
        if (quality < 0) {
            return sourceImageName.substring(0, sourceImageName.lastIndexOf(".")) + "_" + "0Q" + sourceImageName.substring(sourceImageName.lastIndexOf("."));
        }
        if (StringUtil.isEmpty(sourceImageName) || sourceImageName.lastIndexOf(".") < 0) {
            return sourceImageName.substring(0, sourceImageName.lastIndexOf(".")) + "_" + "100Q" + sourceImageName.substring(sourceImageName.lastIndexOf("."));
        }

        String smallImageName = sourceImageName.substring(0, sourceImageName.lastIndexOf(".")) + "_" + (int) (quality * 100) + "Q" + sourceImageName.substring(sourceImageName.lastIndexOf("."));
        return smallImageName;
    }


    public static String getImageServiceUrl() {
        int key = imageSeriveUrlRandom.nextInt(imageSeriveTotalWeight - 1) + 1;
        return imageSeriveUrlMap.get(key);
    }

    public static String getVideoServiceUrl() {
        int key = random.nextInt(videoSeriveTotalWeight - 1) + 1;
        return videoSeriveUrlMap.get(key);
    }

}
