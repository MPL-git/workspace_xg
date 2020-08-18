package com.jf.common.utils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PDFUtil {


	/**
	 * 创建单元格
	 * @param table
	 * @param row
	 * @param cols
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	private static PdfPTable createCell(PdfPTable table, String[] title, int row, int cols) throws DocumentException, IOException {
		//添加中文字体
		BaseFont bfChinese=BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font font = new Font(bfChinese,11,Font.BOLD);

		for(int i = 0; i < row; i++){

			for(int j = 0; j < cols; j++){

				PdfPCell cell = new PdfPCell();

				if(i==0 && title!=null){//设置表头
					cell = new PdfPCell(new Phrase(title[j], font)); //这样表头才能居中
					if(table.getRows().size() == 0){
						cell.setBorderWidthTop(3);
					}
				}

				if(row==1 && cols==1){ //只有一行一列
					cell.setBorderWidthTop(3);
				}

				if(j==0){//设置左边的边框宽度
					cell.setBorderWidthLeft(3);
				}

				if(j==(cols-1)){//设置右边的边框宽度
					cell.setBorderWidthRight(3);
				}

				if(i==(row-1)){//设置底部的边框宽度
					cell.setBorderWidthBottom(3);
				}

				cell.setMinimumHeight(40); //设置单元格高度
				cell.setUseAscender(true); //设置可以居中
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中

				table.addCell(cell);
			}
		}

		return table;
	}

	/**
	 * 加水印（字符串）
	 * @param inputFile 需要加水印的PDF路径
	 * @param waterMarkName 水印字符
	 */
	public static void stringWaterMark(String inputFile, String waterMarkName) {
		try {
			String[] spe = separatePath(inputFile);
			String outputFile = spe[0] + "_WM." + spe[1];

			PdfReader reader = new PdfReader(inputFile);
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputFile));


//添加中文字体
			BaseFont bfChinese=BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);


			int total = reader.getNumberOfPages() + 1;


			PdfContentByte under;
			int j = waterMarkName.length();
			char c = 0;
			int rise = 0;
//给每一页加水印
			for (int i = 1; i < total; i++) {
				rise = 400;
				under = stamper.getUnderContent(i);
				under.beginText();
				under.setFontAndSize(bfChinese, 30);
				under.setTextMatrix(200, 120);
				for (int k = 0; k < j; k++) {
					under.setTextRise(rise);
					c = waterMarkName.charAt(k);
					under.showText(c + "");
				}


// 添加水印文字
				under.endText();
			}
			stamper.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加水印（图片）
	 * @param inputFile 需要加水印的PDF路径
	 * @param imageFile 水印图片路径
	 */
	public static void imageWaterMark(String inputFile, String imageFile) {
		try {
			String[] spe = separatePath(inputFile);
			String outputFile = spe[0] + "_WM." + spe[1];

			PdfReader reader = new PdfReader(inputFile);
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputFile));

			int total = reader.getNumberOfPages() + 1;

			Image image = Image.getInstance(imageFile);
			image.setAbsolutePosition(-100, 0);//坐标
			image.scaleAbsolute(800,1000);//自定义大小
			//image.setRotation(-20);//旋转 弧度
			//image.setRotationDegrees(-45);//旋转 角度
			//image.scalePercent(50);//依照比例缩放

			PdfGState gs = new PdfGState();
			gs.setFillOpacity(0.2f);// 设置透明度为0.2


			PdfContentByte under;
			//给每一页加水印
			for (int i = 1; i < total; i++) {
				under = stamper.getUnderContent(i);
				under.beginText();
				// 添加水印图片
				under.addImage(image);
				under.setGState(gs);
			}
			stamper.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置模拟数据
	 * @param list
	 * @param num
	 */
	public static void add(List<String> list, int num){
		for(int i=0;i<num;i++){
			list.add("test"+i);
		}
	}

	/**
	 * 设置间距
	 * @param tmp
	 * @return
	 */
	public static String printBlank(int tmp){
		String space="";
		for(int m=0;m<tmp;m++){
			space=space+" ";
		}
		return space;
	}

	/**
	 * 设置左边距
	 * @param str
	 * @param i
	 * @return
	 */
	public static String leftPad(String str, int i) {
		int addSpaceNo = i-getStrLength(str);
		String space = "";
		for (int k=0; k<addSpaceNo; k++){
			space= " "+space;
		};
		String result = str + space;
		System.out.println("|" + result + "|");
		return result;
	}

	/**
	 * 分割路径
	 * @param path
	 * @return 返回分割后的路径
	 */
	public static String[] separatePath(String path){
		if(StringUtils.isBlank(path)){
			return null;
		}
		String[] sep = path.split("\\.");
		return new String[]{sep[0],sep[1]};
	}

	/**
	 * 是否字母数字
	 *
	 * @param c
	 * @return
	 */
	private static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * @param s 需要得到长度的字符串
	 * @return int 得到的字符串长度
	 */
	public static int getStrLength(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}


}