package com.jf.bean;

import com.jf.common.ext.util.SpringUtil;
import com.jf.common.utils.FileUtil;
import com.jf.entity.MchtCloseApplicationCustom;
import com.jf.entity.MchtCloseApplicationExample;
import com.jf.entity.MchtCloseApplicationExample.Criteria;
import com.jf.entity.MchtInfo;
import com.jf.service.MchtCloseApplicationService;
import com.jf.service.MchtDepositService;
import com.jf.service.MchtInfoService;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 生成付款单PDF
 */
public class PDFCreaterPaymentForm {
	
/*	@Resource
	private static MchtCloseApplicationService mchtCloseApplicationService;*/
	
		
	   public static void main(String[] args) throws Exception {
	    	
	    }
	   
	    private static int firstTitleSpacing = 5;     //一级标题前后间距
	    private static int leading = 25;     //段与段之间的距离
	    private static int signSplit = 90;     //底部签名间隔空格数
	   
	    /**
	     * 创建PDF文档
	     * @return 付款单
	     * @throws Exception
	     */
	   
	   public static String createPDF(MchtCloseApplicationCustom mchtCloseApplicationCustom) throws Exception {
		   
		   MchtCloseApplicationService mchtCloseApplicationService = SpringUtil.getBean("mchtCloseApplicationService");
		   MchtInfoService mchtInfoService = SpringUtil.getBean("mchtInfoService");
		   MchtDepositService mchtDepositService = SpringUtil.getBean("mchtDepositService");
		   
		   
		   //操作结算提交时的日期
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Date agreementIssueDate =mchtCloseApplicationCustom.getOpConfirmDate();
	        String agreementIssueDateStr = sdf.format(agreementIssueDate);
	        String agreementIssueDateYear = agreementIssueDateStr.substring(0, 4);
	        String agreementIssueDateMonth = agreementIssueDateStr.substring(5,7);
	        String agreementIssueDateDay = agreementIssueDateStr.substring(8);
	        
	       //获取单号
	        SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	        String agreementIssueDateStrNew = agreementIssueDateStr +" 00:00:00";
	        Date parse = sdfs.parse(agreementIssueDateStrNew);
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(parse);
	        
	        // 获取前月的第一天  
	        cal.add(Calendar.MONTH, 0);  
	        cal.set(Calendar.DAY_OF_MONTH, 1);  
	        Date firDayForNow = cal.getTime();
	        
	        //得到下个月的最后一天
	        cal.add(Calendar.MONTH, 1);
	        cal.set(Calendar.DAY_OF_MONTH, 0);
	        cal.set(Calendar.HOUR_OF_DAY,23);
	        cal.set(Calendar.MINUTE,59);
	        cal.set(Calendar.SECOND,59);
	        Date lastDayForNow=cal.getTime();
	        
	        MchtCloseApplicationExample  mcaExample= new MchtCloseApplicationExample();
	        Criteria createCriteria = mcaExample.createCriteria();
	        createCriteria.andDelFlagEqualTo("0").andProgressStatusEqualTo("10");
	        createCriteria.andOpConfirmDateGreaterThanOrEqualTo(firDayForNow);//这个月第一天
	        createCriteria.andOpConfirmDateLessThan(lastDayForNow);//这个月最后一天
			int bills = mchtCloseApplicationService.countByExample(mcaExample);//操作结算时间的排序
			
			String billsDay = "";
			if(bills>=0 && bills<10){
				billsDay = "00"+bills;
			}else if(bills>=10 && bills < 100){
				billsDay = "0"+bills;
			}else if(bills >=100 && bills <1000){
				billsDay = bills+"";
			}
			//拼接单号
			String billCount = agreementIssueDateYear + agreementIssueDateMonth + billsDay;
			
			// 商家信息
	        MchtInfo mchtInfo = mchtInfoService.findById(mchtCloseApplicationCustom.getMchtId());
			
			//金额
	      //  MchtDeposit mchtDeposit = mchtDepositService.selectByPrimaryKey(mchtCloseApplicationCustom.getMchtId());
	        /*String payAmts = "";
	        MchtDeposit mchtDeposit = null;
	        MchtDepositExample mchtDepositExample = new MchtDepositExample();
	        mchtDepositExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtCloseApplicationCustom.getMchtId());
			List<MchtDeposit> mchtDepositList = mchtDepositService.selectByExample(mchtDepositExample );
			if(mchtDepositList.size()>0 && mchtDepositList!=null ){
				 mchtDeposit = mchtDepositList.get(0);
			}
	        if(mchtDeposit!=null ){
	        	 BigDecimal pa =  mchtDeposit.getPayAmt();
	        	 int i=pa.compareTo(BigDecimal.ZERO); 
	        	 if(i==-1){//金额小于0
	        		 payAmts ="0";
	        	 }else{
	        		 payAmts=pa+"";
	        	 }
	        }*/
		    String payAmts = "";
		    BigDecimal pa = mchtCloseApplicationCustom.getReturnedDepositAmount();
		    int num=pa.compareTo(BigDecimal.ZERO);
		    if(num==-1){//金额小于0
			   payAmts ="0";
		    }else{
			   payAmts=pa+"";
		    }
	        
	        //金额大写
	        String money = payAmts.replace(".", "");
	        int length = money.length()-1;
	        String rmb = "";
	        for (int i = 8; i >= 0; i--) {
	        	String capitalization = "零";
	        	if(i<money.length()){
					if("0".equals(money.charAt(length-i)+"")){
						capitalization = "零";
					}else if("1".equals(money.charAt(length-i)+"")){
						capitalization = "壹";
					}else if("2".equals(money.charAt(length-i)+"")){
						capitalization = "贰";
					}else if("3".equals(money.charAt(length-i)+"")){
						capitalization = "叁";
					}else if("4".equals(money.charAt(length-i)+"")){
						capitalization = "肆";
					}else if("5".equals(money.charAt(length-i)+"")){
						capitalization = "伍";
					}else if("6".equals(money.charAt(length-i)+"")){
						capitalization = "陆";
					}else if("7".equals(money.charAt(length-i)+"")){
						capitalization = "柒";
					}else if("8".equals(money.charAt(length-i)+"")){
						capitalization = "捌";
					}else if("9".equals(money.charAt(length-i)+"")){
						capitalization = "玖";
					}
	        	}
				
				if(i==8){
					rmb += capitalization +"佰";
				}else if(i==7){
					rmb += capitalization +"拾";
				}else if(i==6){
					rmb += capitalization +"万";
				}else if(i==5){
					rmb += capitalization +"仟";
				}else if(i==4){
					rmb += capitalization +"佰";
				}else if(i==3){
					rmb += capitalization +"拾";
				}else if(i==2){
					rmb += capitalization +"元";
				}else if(i==1){
					rmb += capitalization +"角";
				}else if(i==0){
					rmb += capitalization +"分";
				}
			}
	
	        //审核人
	        String fwStaffName = mchtCloseApplicationCustom.getFwStaffName();//法务
	        String directorStaffName = mchtCloseApplicationCustom.getDirectorStaffName();//分管
	        String productStaffName = mchtCloseApplicationCustom.getProductStaffName();//操作关店
	        String settlementStaffName = mchtCloseApplicationCustom.getSettlementStaffName();//财务审核
	        String opStaffName = mchtCloseApplicationCustom.getOpStaffName();//操作结算
	        
	        String auditPeople = "法务:"+fwStaffName+"分管:"+directorStaffName+"操作关店:"+productStaffName+"财务审核:"+settlementStaffName+"操作结算:"+opStaffName;
	        
	        
		   //输出路径
	        String filePath = FileUtil.getRandomFileName(mchtCloseApplicationCustom.getId() + ".pdf", 0, mchtCloseApplicationCustom.getMchtId());
	        
	        //添加中文字体
	        BaseFont bfChinese = BaseFont.createFont(PDFCreaterEndAgreement.class.getResource("/resources/fonts/simsun.ttf").getPath(),BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
	        //设置字体样式
	        Font textFont = new Font(bfChinese,11.8f, Font.NORMAL); //正常
	        Font boldFont = new Font(bfChinese,11.95f,Font.BOLD); //加粗
	        Font underlineFont = new Font(bfChinese,11.95f,Font.UNDERLINE); //下划线
	        Font firstTitleFont = new Font(bfChinese,12,Font.BOLD); //一级标题
		   
	        //设置纸张
	        Rectangle rect = new Rectangle(PageSize.A4);

	        //创建文档实例
	        Document doc = new Document(rect);
	        
	        //创建输出流
	        PdfWriter writer = PdfWriter.getInstance(doc, FileUtil.getNewFileOutputStream(filePath));
	        
	        //打开文档
	        doc.open();
	        doc.newPage();
	        
	        //添加一个内容段落
	        Paragraph paragraph = new Paragraph("付款单", new Font(bfChinese, 25, Font.BOLD));
	        paragraph.setLeading(30);  // 设置和上面段落间距
	        paragraph.setSpacingBefore(5);
	        paragraph.setSpacingAfter(50);
	        paragraph.setAlignment(Element.ALIGN_CENTER);
	        doc.add(paragraph);
	        
	        paragraph = new Paragraph();
	        paragraph.setLeading(leading);
	        paragraph.setSpacingBefore(firstTitleSpacing);
	        paragraph.setSpacingAfter(firstTitleSpacing);
	        paragraph.setMultipliedLeading(2);
	        
	        /**
             *   创建一个有2列的表格
             */
	        PdfPTable table = new PdfPTable(2);
	        table.setTotalWidth(new float[]{ 260, 260 }); //设置列宽
	        table.setLockedWidth(true); //锁定列宽
	        table.setHorizontalAlignment(Element.ALIGN_CENTER);
	        PdfPCell cell;
	        cell = new PdfPCell(new Phrase("日期:"+ agreementIssueDateYear+"年"+agreementIssueDateMonth+"月"+agreementIssueDateDay+"日",textFont));
	        cell.setMinimumHeight(30); //设置单元格高度
	        cell.setBorderWidth(0);
	        cell.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
	        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("单号:"+billCount,textFont));
	        cell.setBorderWidth(0);
	        cell.setHorizontalAlignment(Cell.ALIGN_CENTER); //设置水平居中
	        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
	        table.addCell(cell);
	        doc.add(table);
	        
	        /**
             *  添加4列的表table2(商家序号,商家名称,摘要,金额)
             */
	        PdfPTable table2 = new PdfPTable(4);
            float[] columnWidths = {2f,4f,3.5f,4.5f};
	        table2.setWidths(columnWidths);
            
            PdfPCell cell21 = new PdfPCell(new Phrase("商家序号",textFont));
            cell21.disableBorderSide(2);
            setFormatCells(cell21);
            PdfPCell cell22 = new PdfPCell(new Phrase("商家名称",textFont));
            cell22.disableBorderSide(2);
            setFormatCells(cell22);
            PdfPCell cell23 = new PdfPCell(new Phrase("摘要",textFont));
            cell23.disableBorderSide(2);
            setFormatCells(cell23);
            PdfPCell cell24 = new PdfPCell(new Phrase("金额",textFont));
            setFormatCells(cell24);
            
            //把表格添加到文件中
            table2.addCell(cell21);
            table2.addCell(cell22);
            table2.addCell(cell23);
            table2.addCell(cell24);
            doc.add(table2);
            
            
            /**
             *  添加12列的表table2_1 在table2的下面但是前三个没有内容,最后一个补齐金额(前3 后 9 共12个)
             */
	        PdfPTable table2_1 = new PdfPTable(12);
            float[] columnWidths2_1 = {2f,4f,3.5f,0.5f,0.5f,0.5f,0.5f,0.5f,0.5f,0.5f,0.5f,0.5f};
            table2_1.setWidths(columnWidths2_1);
            //前三个隐藏上边框
            PdfPCell cell21_1 = new PdfPCell();
            cell21_1.disableBorderSide(1);
            setFormatCells(cell21_1);
            PdfPCell cell21_2 = new PdfPCell();
            cell21_2.disableBorderSide(1);
            setFormatCells(cell21_2);
            PdfPCell cell21_3 = new PdfPCell();
            cell21_3.disableBorderSide(1);
            setFormatCells(cell21_3);
            //后9个
            PdfPCell cell21_4 = new PdfPCell(new Phrase("百",textFont));
            setFormatCells(cell21_4);
            PdfPCell cell21_5 = new PdfPCell(new Phrase("十",textFont));
            setFormatCells(cell21_5);
            PdfPCell cell21_6 = new PdfPCell(new Phrase("万",textFont));
            setFormatCells(cell21_6);
            PdfPCell cell21_7 = new PdfPCell(new Phrase("千",textFont));
            setFormatCells(cell21_7);
            PdfPCell cell21_8 = new PdfPCell(new Phrase("百",textFont));
            setFormatCells(cell21_8);
            PdfPCell cell21_9 = new PdfPCell(new Phrase("十",textFont));
            setFormatCells(cell21_9);
            PdfPCell cell21_10 = new PdfPCell(new Phrase("元",textFont));
            setFormatCells(cell21_10);
            PdfPCell cell21_11 = new PdfPCell(new Phrase("角",textFont));
            setFormatCells(cell21_11);
            PdfPCell cell21_12 = new PdfPCell(new Phrase("分",textFont));
            setFormatCells(cell21_12);
            
            //把表格添加到文件中
            table2_1.addCell(cell21_1);
            table2_1.addCell(cell21_2);
            table2_1.addCell(cell21_3);
            table2_1.addCell(cell21_4);
            table2_1.addCell(cell21_5);
            table2_1.addCell(cell21_6);
            table2_1.addCell(cell21_7);
            table2_1.addCell(cell21_8);
            table2_1.addCell(cell21_9);
            table2_1.addCell(cell21_10);
            table2_1.addCell(cell21_11);
            table2_1.addCell(cell21_12);
            doc.add(table2_1);
            
            /**
             * 添加4列的表table3
             */
            PdfPTable table3 = new PdfPTable(12);
	        table3.setWidths(columnWidths2_1);
            PdfPCell cell31 = new PdfPCell(new Phrase(mchtInfo.getMchtCode(),textFont));
            setFormatCells2(cell31);
            PdfPCell cell32 = new PdfPCell(new Phrase(mchtInfo.getCompanyName(),textFont));
            setFormatCells2(cell32);
            PdfPCell cell33 = new PdfPCell(new Phrase("退店保证金",textFont));
            setFormatCells2(cell33);
            //把表格添加到文件中
            table3.addCell(cell31);
            table3.addCell(cell32);
            table3.addCell(cell33);
            //将保证金添加到表格中
            for (int i = 8; i >=0 ; i--) {
            	if(i>=money.length()){
            		PdfPCell cellFor9 = new PdfPCell();
            		setFormatCells2(cellFor9);
            		table3.addCell(cellFor9);
            	}else if(i<money.length()){
            		PdfPCell cellFor9 = new PdfPCell(new Phrase(money.charAt(length-i)+"",textFont));
            		setFormatCells2(cellFor9);
            		table3.addCell(cellFor9);
            	}
			}
            doc.add(table3);
            
            
            /**
             * 添加2列的表table4
             */
            PdfPTable table4 = new PdfPTable(1);
            float[] columnWidths2 = {14f};
            table4.setWidths(columnWidths2);
            PdfPCell cell41 = new PdfPCell(new Phrase("人民币:"+rmb,textFont));
            setFormatCells(cell41);
            //把表格添加到文件中
            table4.addCell(cell41);
            //将保证金添加到表格中
/*            for (int i = 8; i >=0 ; i--) {
            	if(i>money.length()){
            		PdfPCell cellFor9 = new PdfPCell();
            		setFormatCells2(cellFor9);
            		table4.addCell(cellFor9);
            	}else if(i==money.length()){
            		PdfPCell cellFor9 = new PdfPCell(new Phrase("¥",textFont));
            		setFormatCells2(cellFor9);
            		table4.addCell(cellFor9);
            	}else if(i<money.length()){
            		PdfPCell cellFor9 = new PdfPCell(new Phrase(money.charAt(length-i)+"",textFont));
            		setFormatCells2(cellFor9);
            		table4.addCell(cellFor9);
            	}
			}*/
            doc.add(table4);
            
            /**
             * 添加1列的表table5
             */
/*		   PdfPTable table5 = new PdfPTable(1);
            float[] columnWidths3 = {14f};
            table5.setWidths(columnWidths3);
            PdfPCell cell51 = new PdfPCell(new Phrase(auditPeople,textFont));
            setFormatCells(cell51);
         
            //把表格添加到文件中
            table5.addCell(cell51);
            doc.add(table5);*/
            
	        //关闭文档
	        doc.close();
	        
	        //关闭书写器
	        writer.close();
	        
	        return filePath;
			
	   }
	   
	   private static void setFormatCells(PdfPCell pdfPCell){
		   pdfPCell.setMinimumHeight(30); //设置单元格高度
		  // pdfPCell.setPaddingLeft(20);//左填充20
		   pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
		   pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
	   }
	   
	   private static void setFormatCells2(PdfPCell pdfPCell){
		   pdfPCell.setMinimumHeight(40); //设置单元格高度
		  // pdfPCell.setPaddingLeft(20);//左填充20
		   pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
		   pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
	   }

}
