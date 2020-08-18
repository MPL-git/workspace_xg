package com.jf.bean;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.util.UriUtils;

import com.jf.common.ext.util.SpringUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.MchtCloseApplicationCustom;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtProductBrand;
import com.jf.entity.MchtProductBrandExample;
import com.jf.entity.MchtSettleOrder;
import com.jf.entity.MchtSettleOrderExample;
import com.jf.service.MchtInfoService;
import com.jf.service.MchtProductBrandService;
import com.jf.service.MchtSettleOrderService;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
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

/**
 * 关店记录生成PDF
 */
public class PDFCreaterMchtCloseApplication {

    private static int firstTitleSpacing = 5;     //一级标题前后间距
    //private static int secondTitleSpacing = 5;     //二级标题间距
    //private static int lineSpacing = 2; //行前后间距
    private static int leading = 25;     //段与段之间的距离
    private static int signSplit = 90;     //底部签名间隔空格数


    /**
     * 创建PDF文档
     * @return 合同路径
     * @throws Exception
     */
    public static String createPDF(MchtCloseApplicationCustom mchtCloseApplicationCustom) throws Exception {

    	MchtProductBrandService mchtProductBrandService = SpringUtil.getBean("mchtProductBrandService");
    	MchtSettleOrderService mchtSettleOrderService = SpringUtil.getBean("mchtSettleOrderService");
    	MchtInfoService mchtInfoService = SpringUtil.getBean("mchtInfoService");
        MchtProductBrandExample mpbe = new MchtProductBrandExample();
		mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtCloseApplicationCustom.getMchtId());
		List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mpbe);
		
		MchtSettleOrderExample msoe = new MchtSettleOrderExample();
		List<String> payStatusList = new ArrayList<String>();
		payStatusList.add("1");
		payStatusList.add("2");
		msoe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtCloseApplicationCustom.getMchtId()).andPayStatusIn(payStatusList);
		List<MchtSettleOrder> mchtSettleOrders = mchtSettleOrderService.selectByExample(msoe);
		BigDecimal total = new BigDecimal(0);
		for(MchtSettleOrder mchtSettleOrder:mchtSettleOrders){
			total.add(mchtSettleOrder.getNeedPayAmount());
		}
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtCloseApplicationCustom.getMchtId());
        //输出路径
		String fileName = UriUtils.encodePath(mchtInfo.getMchtCode() + "关店记录.pdf","UTF-8");
        String filePath = FileUtil.getRandomFileName(fileName, -1, mchtCloseApplicationCustom.getMchtId());
        //String filePath = "F://temp/test2.pdf";

        //添加中文字体
//        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        BaseFont bfChinese = BaseFont.createFont(PDFCreaterMchtCloseApplication.class.getResource("/resources/fonts/simsun.ttf").getPath(),BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        //设置字体样式
        Font textFont = new Font(bfChinese,11.8f, Font.NORMAL); //正常
        Font boldFont = new Font(bfChinese,11.95f,Font.BOLD); //加粗
        Font underlineFont = new Font(bfChinese,11.95f,Font.UNDERLINE); //下划线
        Font firstTitleFont = new Font(bfChinese,12,Font.BOLD); //一级标题
        //Font secondTitleFont = new Font(bfChinese,12,Font.BOLD); //二级标题

        //设置纸张
        Rectangle rect = new Rectangle(PageSize.A4);

        //创建文档实例
        Document doc = new Document(rect);

        //创建输出流
        PdfWriter writer = PdfWriter.getInstance(doc, FileUtil.getNewFileOutputStream(filePath));

        //页边空白  左右上下
        doc.setMargins(50, 50, 45, 45);
        writer.setPageEvent(new HeadFootInfoPdfPageEvent());
        doc.open();
        doc.newPage();

        Paragraph paragraph = new Paragraph("《醒购关店记录》", new Font(bfChinese, 25, Font.BOLD));
        paragraph.setLeading(50);  // 设置和上面段落间距
        paragraph.setSpacingBefore(5);
        paragraph.setSpacingAfter(100);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        doc.add(paragraph);
        
        paragraph = new Paragraph();
        paragraph.setLeading(leading);
        paragraph.setSpacingBefore(firstTitleSpacing);
        paragraph.setSpacingAfter(firstTitleSpacing);
        paragraph.setMultipliedLeading(2);
        
        Phrase phase = new Phrase();
        phase.setLeading(0);
        phase.add(new Chunk("申请信息", textFont));
        phase.add(Chunk.NEWLINE); //换行
        paragraph.add(phase);
        doc.add(paragraph);
        
     // 创建一个有4列的表格
        PdfPTable table = new PdfPTable(4);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingBefore(10);
        PdfPCell content11=new PdfPCell(new Paragraph("商家序号",textFont));
        content11.setColspan(1);
		content11.setPaddingLeft(5);
		content11.setMinimumHeight(30); //设置单元格高度
		content11.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content11.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content11);
        
		//第二列
		PdfPCell content12=new PdfPCell(new Paragraph(mchtCloseApplicationCustom.getMchtCode(),textFont));
		content12.setColspan(1);
		content12.setPaddingLeft(5);
		content12.setMinimumHeight(30); //设置单元格高度
		content12.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content12.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content12);
		
		//第三列
		PdfPCell content13=new PdfPCell(new Paragraph("公司名称",textFont));
		content13.setColspan(1);
		content13.setPaddingLeft(5);
		content13.setMinimumHeight(30); //设置单元格高度
		content13.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content13.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content13);
		
		//第四列
		PdfPCell content14=new PdfPCell(new Paragraph(mchtCloseApplicationCustom.getCompanyName(),textFont));
		content14.setColspan(1);
		content14.setPaddingLeft(5);
		content14.setMinimumHeight(30); //设置单元格高度
		content14.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content14.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content14);

		PdfPCell content21=new PdfPCell(new Paragraph("主营类目",textFont));
		content21.setColspan(1);
		content21.setPaddingLeft(5);
		content21.setMinimumHeight(30); //设置单元格高度
		content21.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content21.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content21);
		
		PdfPCell content22=new PdfPCell(new Paragraph(mchtCloseApplicationCustom.getProductTypeName(),textFont));
		content22.setColspan(1);
		content22.setPaddingLeft(5);
		content22.setMinimumHeight(30); //设置单元格高度
		content22.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content22.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content22);
		
		PdfPCell content23=new PdfPCell(new Paragraph("店铺名称",textFont));
		content23.setColspan(1);
		content23.setPaddingLeft(5);
		content23.setMinimumHeight(30); //设置单元格高度
		content23.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content23.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content23);
		
		PdfPCell content24=new PdfPCell(new Paragraph(mchtCloseApplicationCustom.getShopName(),textFont));
		content24.setColspan(1);
		content24.setPaddingLeft(5);
		content24.setMinimumHeight(30); //设置单元格高度
		content24.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content24.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content24);
		
		PdfPCell content31=new PdfPCell(new Paragraph("合同开始日期",textFont));
		content31.setColspan(1);
		content31.setPaddingLeft(5);
		content31.setMinimumHeight(30); //设置单元格高度
		content31.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content31.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content31);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String agreementBeginDateStr = "";
		if(mchtCloseApplicationCustom.getAgreementBeginDate()!=null){
			agreementBeginDateStr = sdf.format(mchtCloseApplicationCustom.getAgreementBeginDate());
		}
		PdfPCell content32=new PdfPCell(new Paragraph(agreementBeginDateStr,textFont));
		content32.setColspan(1);
		content32.setPaddingLeft(5);
		content32.setMinimumHeight(30); //设置单元格高度
		content32.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content32.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content32);
		
		PdfPCell content33=new PdfPCell(new Paragraph("合同结束日期",textFont));
		content33.setColspan(1);
		content33.setPaddingLeft(5);
		content33.setMinimumHeight(30); //设置单元格高度
		content33.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content33.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content33);
		
		String agreementEndDateStr = "";
		if(mchtCloseApplicationCustom.getAgreementEndDate()!=null){
			agreementEndDateStr = sdf.format(mchtCloseApplicationCustom.getAgreementEndDate());
		}
		PdfPCell content34=new PdfPCell(new Paragraph(agreementEndDateStr,textFont));
		content34.setColspan(1);
		content34.setPaddingLeft(5);
		content34.setMinimumHeight(30); //设置单元格高度
		content34.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content34.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content34);
		
		PdfPCell content41=new PdfPCell(new Paragraph("合同归档状态",textFont));
		content41.setColspan(1);
		content41.setPaddingLeft(5);
		content41.setMinimumHeight(30); //设置单元格高度
		content41.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content41.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content41);
		
		String archiveStatusStr="";
		if("0".equals(mchtCloseApplicationCustom.getArchiveStatus())){
			archiveStatusStr = "未处理"; 
		}else if("1".equals(mchtCloseApplicationCustom.getArchiveStatus())){
			archiveStatusStr = "通过已归档"; 
		}else if("2".equals(mchtCloseApplicationCustom.getArchiveStatus())){
			archiveStatusStr = "不通过驳回"; 
		}else if("3".equals(mchtCloseApplicationCustom.getArchiveStatus())){
			archiveStatusStr = "不签约"; 
		}else{
			archiveStatusStr = "未处理"; 
		}
		PdfPCell content42=new PdfPCell(new Paragraph(archiveStatusStr,textFont));
		content42.setColspan(1);
		content42.setPaddingLeft(5);
		content42.setMinimumHeight(30); //设置单元格高度
		content42.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content42.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content42);
		
		PdfPCell content43=new PdfPCell(new Paragraph("合同归档日期",textFont));
		content43.setColspan(1);
		content43.setPaddingLeft(5);
		content43.setMinimumHeight(30); //设置单元格高度
		content43.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content43.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content43);
		
		String archiveDateStr = "";
		if(mchtCloseApplicationCustom.getArchiveDate()!=null){
			archiveDateStr = sdf.format(mchtCloseApplicationCustom.getArchiveDate());
		}
		PdfPCell content44=new PdfPCell(new Paragraph(archiveDateStr,textFont));
		content44.setColspan(1);
		content44.setPaddingLeft(5);
		content44.setMinimumHeight(30); //设置单元格高度
		content44.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content44.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content44);
		
		PdfPCell content51=new PdfPCell(new Paragraph("经营品牌",textFont));
		content51.setColspan(1);
		content51.setPaddingLeft(5);
		content51.setMinimumHeight(30); //设置单元格高度
		content51.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content51.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content51);
		
		String brandInfo = "";
		for(MchtProductBrand mchtProductBrand:mchtProductBrands){
			String mchtProductBrandStr="";
			String statusDesc = "";
			if(mchtProductBrand.getStatus().equals("0")){
				statusDesc = "申请中";
			}else if(mchtProductBrand.getStatus().equals("1")){
				statusDesc = "正常";
			}else if(mchtProductBrand.getStatus().equals("2")){
				statusDesc = "暂停";
			}else if(mchtProductBrand.getStatus().equals("3")){
				statusDesc = "关闭";
			}else if(mchtProductBrand.getStatus().equals("4")){
				statusDesc = "驳回申请";
			}
			String s = "";
			if(mchtProductBrand.getPopCommissionRate()!=null){
    			String popCommissionRateStr = String.valueOf(mchtProductBrand.getPopCommissionRate().multiply(new BigDecimal(100)));
    			Double d= Double.parseDouble(popCommissionRateStr); 
    			DecimalFormat df = new DecimalFormat("0.00"); 
    			s = df.format(d)+"%";
    		}
			mchtProductBrandStr = "["+statusDesc+"]"+mchtProductBrand.getProductBrandName()+"["+s+"]";
			if(StringUtils.isEmpty(brandInfo)){
				brandInfo=mchtProductBrandStr;
			}else{
				brandInfo+=";"+mchtProductBrandStr;
			}
		}
		PdfPCell content52=new PdfPCell(new Paragraph(brandInfo,textFont));
		content52.setColspan(3);
		content52.setPaddingLeft(5);
		content52.setMinimumHeight(30); //设置单元格高度
		content52.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content52.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content52);
		
		PdfPCell content61=new PdfPCell(new Paragraph("申请类型",textFont));
		content61.setColspan(1);
		content61.setPaddingLeft(5);
		content61.setMinimumHeight(30); //设置单元格高度
		content61.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content61.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content61);
		
		String applySourceStr="";
		if(mchtCloseApplicationCustom.getApplySource().equals("1")){
			applySourceStr = "商家申请";
		}else{
			applySourceStr = "平台介入关店";
		}
		PdfPCell content62=new PdfPCell(new Paragraph(applySourceStr,textFont));
		content62.setColspan(3);
		content62.setPaddingLeft(5);
		content62.setMinimumHeight(30); //设置单元格高度
		content62.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content62.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content62);
		
		PdfPCell content71=new PdfPCell(new Paragraph("申请理由",textFont));
		content71.setColspan(1);
		content71.setPaddingLeft(5);
		content71.setMinimumHeight(30); //设置单元格高度
		content71.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content71.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content71);
		
		PdfPCell content72=new PdfPCell(new Paragraph(mchtCloseApplicationCustom.getApplyReason()==null?"":mchtCloseApplicationCustom.getApplyReason(),textFont));
		content72.setColspan(3);
		content72.setPaddingLeft(5);
		content72.setMinimumHeight(30); //设置单元格高度
		content72.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
		content72.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
		table.addCell(content72);
		doc.add(table);
		
		paragraph = new Paragraph();
        paragraph.setLeading(leading);
        paragraph.setSpacingBefore(firstTitleSpacing);
        paragraph.setSpacingAfter(firstTitleSpacing);
        paragraph.setMultipliedLeading(2);
        
        String zsStaffName = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getZsStaffName())){
        	String zsConfirmDate = format.format(mchtCloseApplicationCustom.getZsConfirmDate());
        	zsStaffName += "("+mchtCloseApplicationCustom.getZsStaffName()+" "+zsConfirmDate+")";
        }
        if(!StringUtils.isEmpty(zsStaffName)){
        	phase = new Phrase();
        	phase.setLeading(0);
        	phase.add(new Chunk("招商部确认"+zsStaffName, textFont));
        	phase.add(Chunk.NEWLINE); //换行
        	paragraph.add(phase);
        	doc.add(paragraph);
        	
        	// 创建一个有2列的表格
        	table = new PdfPTable(4);
        	table.setHorizontalAlignment(Element.ALIGN_LEFT);
        	table.setSpacingBefore(10);
        	
        	content11=new PdfPCell(new Paragraph("招商部确认结果",textFont));
        	content11.setColspan(2);
        	content11.setPaddingLeft(5);
        	content11.setMinimumHeight(30); //设置单元格高度
        	content11.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content11.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content11);
        	String zsConfirmStatusStr = "";
        	if("1".equals(mchtCloseApplicationCustom.getZsConfirmStatus())){
        		zsConfirmStatusStr = "同意申请";
        	}else if("2".equals(mchtCloseApplicationCustom.getZsConfirmStatus())){
        		zsConfirmStatusStr = "不同意关店";
        	}else if("0".equals(mchtCloseApplicationCustom.getZsConfirmStatus())){
        		zsConfirmStatusStr = "待确认";
        	}
        	content12=new PdfPCell(new Paragraph(zsConfirmStatusStr,textFont));
        	content12.setColspan(2);
        	content12.setPaddingLeft(5);
        	content12.setMinimumHeight(30); //设置单元格高度
        	content12.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content12.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content12);
        	doc.add(table);
        }
		
        
        paragraph = new Paragraph();
        paragraph.setLeading(leading);
        paragraph.setSpacingBefore(firstTitleSpacing);
        paragraph.setSpacingAfter(firstTitleSpacing);
        paragraph.setMultipliedLeading(2);
        
        String commodityStaffName = "";
        if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getCommodityStaffName())){
        	String commodityConfirmDate = format.format(mchtCloseApplicationCustom.getCommodityConfirmDate());
        	commodityStaffName += "("+mchtCloseApplicationCustom.getCommodityStaffName()+" "+commodityConfirmDate+")";
        }
        if(!StringUtils.isEmpty(commodityStaffName)){
        	phase = new Phrase();
        	phase.setLeading(0);
        	phase.add(new Chunk("商品部确认"+commodityStaffName, textFont));
        	phase.add(Chunk.NEWLINE); //换行
        	paragraph.add(phase);
        	doc.add(paragraph);
        	
        	// 创建一个有2列的表格
        	table = new PdfPTable(4);
        	table.setHorizontalAlignment(Element.ALIGN_LEFT);
        	table.setSpacingBefore(10);
        	
        	content11=new PdfPCell(new Paragraph("店铺暂停",textFont));
        	content11.setColspan(2);
        	content11.setPaddingLeft(5);
        	content11.setMinimumHeight(30); //设置单元格高度
        	content11.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content11.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content11);
        	String commodityConfirmStatusStr = "";
        	if("1".equals(mchtCloseApplicationCustom.getMchtInfoStatus())){
        		commodityConfirmStatusStr = "店铺已暂停";
        	}else if("0".equals(mchtCloseApplicationCustom.getMchtInfoStatus())){
        		commodityConfirmStatusStr = "店铺未暂停";
        	}
        	content12=new PdfPCell(new Paragraph(commodityConfirmStatusStr,textFont));
        	content12.setColspan(2);
        	content12.setPaddingLeft(5);
        	content12.setMinimumHeight(30); //设置单元格高度
        	content12.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content12.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content12);
        	
        	content21=new PdfPCell(new Paragraph("活动结束*",textFont));
        	content21.setColspan(2);
        	content21.setPaddingLeft(5);
        	content21.setMinimumHeight(30); //设置单元格高度
        	content21.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content21.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content21);
        	String activityStatusDesc = "";
        	if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getActivityStatus()) && mchtCloseApplicationCustom.getActivityStatus().equals("1")){
        		activityStatusDesc = "已结束";
        	}else{
        		activityStatusDesc = "未结束";
        	}
        	content22=new PdfPCell(new Paragraph(activityStatusDesc,textFont));
        	content22.setColspan(2);
        	content22.setPaddingLeft(5);
        	content22.setMinimumHeight(30); //设置单元格高度
        	content22.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content22.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content22);
        	
        	content31=new PdfPCell(new Paragraph("商品全部下架*",textFont));
        	content31.setColspan(2);
        	content31.setPaddingLeft(5);
        	content31.setMinimumHeight(30); //设置单元格高度
        	content31.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content31.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content31);
        	String commodityStatusDesc = "";
        	if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getCommodityStatus()) && mchtCloseApplicationCustom.getCommodityStatus().equals("1")){
        		commodityStatusDesc = "已下架";
        	}else{
        		commodityStatusDesc = "未下架";
        	}
        	content32=new PdfPCell(new Paragraph(commodityStatusDesc,textFont));
        	content32.setColspan(2);
        	content32.setPaddingLeft(5);
        	content32.setMinimumHeight(30); //设置单元格高度
        	content32.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content32.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content32);
        	
        	content41=new PdfPCell(new Paragraph("取消营销安排*",textFont));
        	content41.setColspan(2);
        	content41.setPaddingLeft(5);
        	content41.setMinimumHeight(30); //设置单元格高度
        	content41.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content41.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content41);
        	String marketingStatusDesc = "";
        	if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getMarketingStatus()) && mchtCloseApplicationCustom.getMarketingStatus().equals("1")){
        		marketingStatusDesc = "已全部取消";
        	}else{
        		marketingStatusDesc = "未全部取消";
        	}
        	content42=new PdfPCell(new Paragraph(marketingStatusDesc,textFont));
        	content42.setColspan(2);
        	content42.setPaddingLeft(5);
        	content42.setMinimumHeight(30); //设置单元格高度
        	content42.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content42.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content42);
        	doc.add(table);
        }
        
        paragraph = new Paragraph();
        paragraph.setLeading(leading);
        paragraph.setSpacingBefore(firstTitleSpacing);
        paragraph.setSpacingAfter(firstTitleSpacing);
        paragraph.setMultipliedLeading(2);
        
        String mchtArchiveStaffName = "";
        if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getMchtArchiveStaffName())){
        	String mchtArchiveConfirmDate = format.format(mchtCloseApplicationCustom.getMchtArchiveConfirmDate());
        	mchtArchiveStaffName += "("+mchtCloseApplicationCustom.getMchtArchiveStaffName()+" "+mchtArchiveConfirmDate+")";
        }
        if(!StringUtils.isEmpty(mchtArchiveStaffName)){
        	phase = new Phrase();
        	phase.setLeading(0);
        	phase.add(new Chunk("商家资料确认"+mchtArchiveStaffName, textFont));
        	phase.add(Chunk.NEWLINE); //换行
        	paragraph.add(phase);
        	doc.add(paragraph);
        	
        	// 创建一个有2列的表格
        	table = new PdfPTable(4);
        	table.setHorizontalAlignment(Element.ALIGN_LEFT);
        	table.setSpacingBefore(10);
        	
        	content11=new PdfPCell(new Paragraph("店铺关闭是否挂起",textFont));
        	content11.setColspan(2);
        	content11.setPaddingLeft(5);
        	content11.setMinimumHeight(30); //设置单元格高度
        	content11.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content11.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content11);
        	String fwCloseHangUpStatusDesc = "";
        	if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getFwCloseHangUpStatus()) && mchtCloseApplicationCustom.getFwCloseHangUpStatus().equals("1")){
        		fwCloseHangUpStatusDesc = "是";
        	}else{
        		fwCloseHangUpStatusDesc = "否";
        	}
        	content12=new PdfPCell(new Paragraph(fwCloseHangUpStatusDesc,textFont));
        	content12.setColspan(2);
        	content12.setPaddingLeft(5);
        	content12.setMinimumHeight(30); //设置单元格高度
        	content12.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content12.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content12);
        	
        	content21=new PdfPCell(new Paragraph("商家合同归档*",textFont));
        	content21.setColspan(2);
        	content21.setPaddingLeft(5);
        	content21.setMinimumHeight(30); //设置单元格高度
        	content21.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content21.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content21);
        	String mchtContractStatusDesc="";
        	if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getMchtContractStatus()) && mchtCloseApplicationCustom.getMchtContractStatus().equals("1")){
        		mchtContractStatusDesc = "已归档";
        	}else{
        		mchtContractStatusDesc = "未归档";
        	}
        	content22=new PdfPCell(new Paragraph(mchtContractStatusDesc,textFont));
        	content22.setColspan(2);
        	content22.setPaddingLeft(5);
        	content22.setMinimumHeight(30); //设置单元格高度
        	content22.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content22.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content22);
        	
        	content31=new PdfPCell(new Paragraph("商家资料齐全情况*",textFont));
        	content31.setColspan(2);
        	content31.setPaddingLeft(5);
        	content31.setMinimumHeight(30); //设置单元格高度
        	content31.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content31.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content31);
        	
        	String mchtArchiveStatusDesc="";
        	if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getMchtArchiveStatus()) && mchtCloseApplicationCustom.getMchtArchiveStatus().equals("1")){
        		mchtArchiveStatusDesc = "已齐全";
        	}else{
        		mchtArchiveStatusDesc = "未齐全";
        	}
        	content32=new PdfPCell(new Paragraph(mchtArchiveStatusDesc,textFont));
        	content32.setColspan(2);
        	content32.setPaddingLeft(5);
        	content32.setMinimumHeight(30); //设置单元格高度
        	content32.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content32.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content32);
        	doc.add(table);
        }
		
		paragraph = new Paragraph();
        paragraph.setLeading(leading);
        paragraph.setSpacingBefore(firstTitleSpacing);
        paragraph.setSpacingAfter(firstTitleSpacing);
        paragraph.setMultipliedLeading(2);
        
        String kfStaffName = "";
        if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getKfStaffName())){
        	String kfConfirmDate = format.format(mchtCloseApplicationCustom.getKfConfirmDate());
        	kfStaffName += "("+mchtCloseApplicationCustom.getKfStaffName()+" "+kfConfirmDate+")";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(!StringUtils.isEmpty(kfStaffName)){
        	phase = new Phrase();
        	phase.setLeading(0);
        	phase.add(new Chunk("客服部确认"+kfStaffName, textFont));
        	phase.add(Chunk.NEWLINE); //换行
        	paragraph.add(phase);
        	doc.add(paragraph);
        	
        	table = new PdfPTable(4);
        	table.setHorizontalAlignment(Element.ALIGN_LEFT);
        	table.setSpacingBefore(10);
        	
        	content11=new PdfPCell(new Paragraph("店铺关闭是否挂起",textFont));
        	content11.setColspan(2);
        	content11.setPaddingLeft(5);
        	content11.setMinimumHeight(30); //设置单元格高度
        	content11.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content11.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content11);
        	String kfCloseHangUpStatus = "";
        	if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getKfCloseHangUpStatus()) && mchtCloseApplicationCustom.getKfCloseHangUpStatus().equals("1")){
        		kfCloseHangUpStatus="是";
        	}else{
        		kfCloseHangUpStatus="否";
        	}
        	content12=new PdfPCell(new Paragraph(kfCloseHangUpStatus,textFont));
        	content12.setColspan(2);
        	content12.setPaddingLeft(5);
        	content12.setMinimumHeight(30); //设置单元格高度
        	content12.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content12.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content12);
        	
        	content21=new PdfPCell(new Paragraph("订单完成确认*",textFont));
        	content21.setColspan(2);
        	content21.setPaddingLeft(5);
        	content21.setMinimumHeight(30); //设置单元格高度
        	content21.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content21.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content21);
        	
        	String orderConfirmStatus = "";
        	if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getOrderConfirmStatus()) && mchtCloseApplicationCustom.getOrderConfirmStatus().equals("1")){
        		orderConfirmStatus="已完成";
        	}else{
        		orderConfirmStatus="未完成";
        	}
        	content22=new PdfPCell(new Paragraph(orderConfirmStatus,textFont));
        	content22.setColspan(2);
        	content22.setPaddingLeft(5);
        	content22.setMinimumHeight(30); //设置单元格高度
        	content22.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content22.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content22);
        	
        	content31=new PdfPCell(new Paragraph("售后全部完成确认*",textFont));
        	content31.setColspan(2);
        	content31.setPaddingLeft(5);
        	content31.setMinimumHeight(30); //设置单元格高度
        	content31.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content31.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content31);
        	
        	String serviceOrderConfirmStatus = "";
        	if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getServiceOrderConfirmStatus()) && mchtCloseApplicationCustom.getServiceOrderConfirmStatus().equals("1")){
        		serviceOrderConfirmStatus="已完成";
        	}else{
        		serviceOrderConfirmStatus="未完成";
        	}
        	content32=new PdfPCell(new Paragraph(serviceOrderConfirmStatus,textFont));
        	content32.setColspan(2);
        	content32.setPaddingLeft(5);
        	content32.setMinimumHeight(30); //设置单元格高度
        	content32.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content32.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content32);
        	
        	content41=new PdfPCell(new Paragraph("投诉全部完成确认*",textFont));
        	content41.setColspan(2);
        	content41.setPaddingLeft(5);
        	content41.setMinimumHeight(30); //设置单元格高度
        	content41.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content41.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content41);
        	
        	String appealOrderConfirmStatus = "";
        	if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getAppealOrderConfirmStatus()) && mchtCloseApplicationCustom.getAppealOrderConfirmStatus().equals("1")){
        		appealOrderConfirmStatus="已完成";
        	}else{
        		appealOrderConfirmStatus="未完成";
        	}
        	content42=new PdfPCell(new Paragraph(appealOrderConfirmStatus,textFont));
        	content42.setColspan(2);
        	content42.setPaddingLeft(5);
        	content42.setMinimumHeight(30); //设置单元格高度
        	content42.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content42.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content42);
        	
        	content51=new PdfPCell(new Paragraph("介入单全部完成确认*",textFont));
        	content51.setColspan(2);
        	content51.setPaddingLeft(5);
        	content51.setMinimumHeight(30); //设置单元格高度
        	content51.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content51.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content51);
        	
        	String interventionOrderConfirmStatus = "";
        	if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getInterventionOrderConfirmStatus()) && mchtCloseApplicationCustom.getInterventionOrderConfirmStatus().equals("1")){
        		interventionOrderConfirmStatus="已完成";
        	}else{
        		interventionOrderConfirmStatus="未完成";
        	}
        	content52=new PdfPCell(new Paragraph(interventionOrderConfirmStatus,textFont));
        	content52.setColspan(2);
        	content52.setPaddingLeft(5);
        	content52.setMinimumHeight(30); //设置单元格高度
        	content52.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content52.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content52);
        	
        	content61=new PdfPCell(new Paragraph("最后一个订单*",textFont));
        	content61.setColspan(2);
        	content61.setPaddingLeft(5);
        	content61.setMinimumHeight(30); //设置单元格高度
        	content61.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content61.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content61);
        	
        	String subOrderCode = "";
        	if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getSubOrderCode())){
        		subOrderCode = mchtCloseApplicationCustom.getSubOrderCode();
        	}
        	content62=new PdfPCell(new Paragraph(subOrderCode,textFont));
        	content62.setColspan(2);
        	content62.setPaddingLeft(5);
        	content62.setMinimumHeight(30); //设置单元格高度
        	content62.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content62.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content62);
        	
        	content71=new PdfPCell(new Paragraph("日期*",textFont));
        	content71.setColspan(2);
        	content71.setPaddingLeft(5);
        	content71.setMinimumHeight(30); //设置单元格高度
        	content71.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content71.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content71);
        	
        	String dateStr="发货日期：";
        	if(mchtCloseApplicationCustom.getDeliveryDate()!=null){
        		dateStr+=dateFormat.format(mchtCloseApplicationCustom.getDeliveryDate());
        	}
        	dateStr+="完成日期：";
        	if(mchtCloseApplicationCustom.getCompleteDate()!=null){
        		dateStr+=dateFormat.format(mchtCloseApplicationCustom.getCompleteDate());
        	}
        	content72=new PdfPCell(new Paragraph(dateStr,textFont));
        	content72.setColspan(2);
        	content72.setPaddingLeft(5);
        	content72.setMinimumHeight(30); //设置单元格高度
        	content72.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content72.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content72);
        	
        	PdfPCell content81=new PdfPCell(new Paragraph("三包期*",textFont));
        	content81.setColspan(2);
        	content81.setPaddingLeft(5);
        	content81.setMinimumHeight(30); //设置单元格高度
        	content81.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content81.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content81);
        	
        	String threePackagePeriod = "";
        	if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getThreePackagePeriod())){
        		threePackagePeriod = mchtCloseApplicationCustom.getThreePackagePeriod();
        	}
        	PdfPCell content82=new PdfPCell(new Paragraph(threePackagePeriod,textFont));
        	content82.setColspan(2);
        	content82.setPaddingLeft(5);
        	content82.setMinimumHeight(30); //设置单元格高度
        	content82.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content82.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content82);
        	doc.add(table);
        }
		
		paragraph = new Paragraph();
        paragraph.setLeading(leading);
        paragraph.setSpacingBefore(firstTitleSpacing);
        paragraph.setSpacingAfter(firstTitleSpacing);
        paragraph.setMultipliedLeading(2);
        
        String cwStaffName = "";
        if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getCwStaffName())){
        	String cwConfirmDate = format.format(mchtCloseApplicationCustom.getCwConfirmDate());
        	cwStaffName += "("+mchtCloseApplicationCustom.getCwStaffName()+" "+cwConfirmDate+")";
        }
        if(!StringUtils.isEmpty(cwStaffName)){
        	phase = new Phrase();
        	phase.setLeading(0);
        	phase.add(new Chunk("财务部货款结清确认"+cwStaffName, textFont));
        	phase.add(Chunk.NEWLINE); //换行
        	paragraph.add(phase);
        	doc.add(paragraph);
        	
        	// 创建一个有2列的表格
        	table = new PdfPTable(4);
        	table.setHorizontalAlignment(Element.ALIGN_LEFT);
        	table.setSpacingBefore(10);
        	
        	content11=new PdfPCell(new Paragraph("货款结清确认*",textFont));
        	content11.setColspan(2);
        	content11.setPaddingLeft(5);
        	content11.setMinimumHeight(30); //设置单元格高度
        	content11.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content11.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content11);
        	String paymentOfGoodsConfirm = "";
        	if("1".equals(mchtCloseApplicationCustom.getPaymentOfGoodsConfirm())){
        		paymentOfGoodsConfirm = "已结清";
        	}else{
        		paymentOfGoodsConfirm = "未结清";
        	}
        	content12=new PdfPCell(new Paragraph(paymentOfGoodsConfirm,textFont));
        	content12.setColspan(2);
        	content12.setPaddingLeft(5);
        	content12.setMinimumHeight(30); //设置单元格高度
        	content12.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content12.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content12);
        	
        	content21=new PdfPCell(new Paragraph("商家保证金情况",textFont));
        	content21.setColspan(2);
        	content21.setPaddingLeft(5);
        	content21.setMinimumHeight(30); //设置单元格高度
        	content21.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content21.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content21);
        	
        	String depositStr="保证金应缴总额：";
        	if(mchtCloseApplicationCustom.getTotalAmt()!=null){
        		depositStr+=mchtCloseApplicationCustom.getTotalAmt().toString()+"元";
        	}
        	depositStr+=" 已缴总额：";
        	if(mchtCloseApplicationCustom.getPayAmt()!=null){
        		depositStr+=mchtCloseApplicationCustom.getPayAmt().toString()+"元";
        	}
        	depositStr+=" 还需补缴：";
        	if(mchtCloseApplicationCustom.getUnpayAmt()!=null){
        		depositStr+=mchtCloseApplicationCustom.getUnpayAmt().toString()+"元";
        	}
        	content22=new PdfPCell(new Paragraph(depositStr,textFont));
        	content22.setColspan(2);
        	content22.setPaddingLeft(5);
        	content22.setMinimumHeight(30); //设置单元格高度
        	content22.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content22.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content22);
        	doc.add(table);
        }
        
        paragraph = new Paragraph();
        paragraph.setLeading(leading);
        paragraph.setSpacingBefore(firstTitleSpacing);
        paragraph.setSpacingAfter(firstTitleSpacing);
        paragraph.setMultipliedLeading(2);
        
        String fwStaffName = "";
        if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getFwStaffName())){
        	String fwConfirmDate = format.format(mchtCloseApplicationCustom.getFwConfirmDate());
        	fwStaffName += "("+mchtCloseApplicationCustom.getFwStaffName()+" "+fwConfirmDate+")";
        }
        if(!StringUtils.isEmpty(fwStaffName)){
        	phase = new Phrase();
        	phase.setLeading(0);
        	phase.add(new Chunk("法务部确认"+fwStaffName, textFont));
        	phase.add(Chunk.NEWLINE); //换行
        	paragraph.add(phase);
        	doc.add(paragraph);
        	
        	// 创建一个有2列的表格
        	table = new PdfPTable(4);
        	table.setHorizontalAlignment(Element.ALIGN_LEFT);
        	table.setSpacingBefore(10);
        	
        	content11=new PdfPCell(new Paragraph("签署终止合作协议*",textFont));
        	content11.setColspan(2);
        	content11.setPaddingLeft(5);
        	content11.setMinimumHeight(30); //设置单元格高度
        	content11.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content11.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content11);
        	String endCooperationAgreement = "";
        	if("1".equals(mchtCloseApplicationCustom.getEndCooperationAgreement())){
        		endCooperationAgreement = "已签署";
        	}else{
        		endCooperationAgreement = "未签署";
        	}
        	content12=new PdfPCell(new Paragraph(endCooperationAgreement,textFont));
        	content12.setColspan(2);
        	content12.setPaddingLeft(5);
        	content12.setMinimumHeight(30); //设置单元格高度
        	content12.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content12.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content12);
        	
        	content21=new PdfPCell(new Paragraph("协议出具日期",textFont));
        	content21.setColspan(2);
        	content21.setPaddingLeft(5);
        	content21.setMinimumHeight(30); //设置单元格高度
        	content21.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content21.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content21);
        	
        	String agreementIssueDateStr="";
        	if(mchtCloseApplicationCustom.getAgreementIssueDate()!=null){
        		agreementIssueDateStr=dateFormat.format(mchtCloseApplicationCustom.getAgreementIssueDate());
        	}
        	content22=new PdfPCell(new Paragraph(agreementIssueDateStr,textFont));
        	content22.setColspan(2);
        	content22.setPaddingLeft(5);
        	content22.setMinimumHeight(30); //设置单元格高度
        	content22.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content22.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content22);
        	
        	content31=new PdfPCell(new Paragraph("预计保证金退还日期",textFont));
        	content31.setColspan(2);
        	content31.setPaddingLeft(5);
        	content31.setMinimumHeight(30); //设置单元格高度
        	content31.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content31.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content31);
        	
        	String depositDateStr="";
        	if(mchtCloseApplicationCustom.getDepositDate()!=null){
        		depositDateStr=dateFormat.format(mchtCloseApplicationCustom.getDepositDate());
        	}
        	content32=new PdfPCell(new Paragraph(depositDateStr,textFont));
        	content32.setColspan(2);
        	content32.setPaddingLeft(5);
        	content32.setMinimumHeight(30); //设置单元格高度
        	content32.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content32.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content32);
        	doc.add(table);
        }
        
        paragraph = new Paragraph();
        paragraph.setLeading(leading);
        paragraph.setSpacingBefore(firstTitleSpacing);
        paragraph.setSpacingAfter(firstTitleSpacing);
        paragraph.setMultipliedLeading(2);
        
        String directorStaffName = "";
        if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getDirectorStaffName())){
        	String directorConfirmDate = format.format(mchtCloseApplicationCustom.getDirectorConfirmDate());
        	directorStaffName += "("+mchtCloseApplicationCustom.getDirectorStaffName()+" "+directorConfirmDate+")";
        }
        if(!StringUtils.isEmpty(directorStaffName)){
        	phase = new Phrase();
        	phase.setLeading(0);
        	phase.add(new Chunk("关店审核"+directorStaffName, textFont));
        	phase.add(Chunk.NEWLINE); //换行
        	paragraph.add(phase);
        	doc.add(paragraph);
        	
        	// 创建一个有2列的表格
        	table = new PdfPTable(4);
        	table.setHorizontalAlignment(Element.ALIGN_LEFT);
        	table.setSpacingBefore(10);
        	
        	content11=new PdfPCell(new Paragraph("确认关店*",textFont));
        	content11.setColspan(2);
        	content11.setPaddingLeft(5);
        	content11.setMinimumHeight(30); //设置单元格高度
        	content11.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content11.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content11);
        	String directorConfirmStatus = "";
        	if("1".equals(mchtCloseApplicationCustom.getDirectorConfirmStatus())){
        		directorConfirmStatus = "已确认关店";
        	}else{
        		directorConfirmStatus = "未确认关店";
        	}
        	content12=new PdfPCell(new Paragraph(directorConfirmStatus,textFont));
        	content12.setColspan(2);
        	content12.setPaddingLeft(5);
        	content12.setMinimumHeight(30); //设置单元格高度
        	content12.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content12.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content12);
        	doc.add(table);
        }
        
        paragraph = new Paragraph();
        paragraph.setLeading(leading);
        paragraph.setSpacingBefore(firstTitleSpacing);
        paragraph.setSpacingAfter(firstTitleSpacing);
        paragraph.setMultipliedLeading(2);
        
        String productStaffName = "";
        if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getProductStaffName())){
        	String productConfirmDate = format.format(mchtCloseApplicationCustom.getProductConfirmDate());
        	productStaffName += "("+mchtCloseApplicationCustom.getProductStaffName()+" "+productConfirmDate+")";
        }
        if(!StringUtils.isEmpty(productStaffName)){
        	phase = new Phrase();
        	phase.setLeading(0);
        	phase.add(new Chunk("产品部关店"+productStaffName, textFont));
        	phase.add(Chunk.NEWLINE); //换行
        	paragraph.add(phase);
        	doc.add(paragraph);
        	
        	// 创建一个有2列的表格
        	table = new PdfPTable(4);
        	table.setHorizontalAlignment(Element.ALIGN_LEFT);
        	table.setSpacingBefore(10);
        	
        	content11=new PdfPCell(new Paragraph("确认关店*",textFont));
        	content11.setColspan(2);
        	content11.setPaddingLeft(5);
        	content11.setMinimumHeight(30); //设置单元格高度
        	content11.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content11.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content11);
        	String productConfirmStatus = "";
        	if("1".equals(mchtCloseApplicationCustom.getProductConfirmStatus())){
        		productConfirmStatus = "店铺已关闭";
        	}else{
        		productConfirmStatus = "店铺未关闭";
        	}
        	content12=new PdfPCell(new Paragraph(productConfirmStatus,textFont));
        	content12.setColspan(2);
        	content12.setPaddingLeft(5);
        	content12.setMinimumHeight(30); //设置单元格高度
        	content12.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content12.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content12);
        	doc.add(table);
        }
        
        paragraph = new Paragraph();
        paragraph.setLeading(leading);
        paragraph.setSpacingBefore(firstTitleSpacing);
        paragraph.setSpacingAfter(firstTitleSpacing);
        paragraph.setMultipliedLeading(2);
        
        String settlementStaffName = "";
        if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getSettlementStaffName())){
        	String settlementConfirmDate = format.format(mchtCloseApplicationCustom.getSettlementConfirmDate());
        	settlementStaffName += "("+mchtCloseApplicationCustom.getSettlementStaffName()+" "+settlementConfirmDate+")";
        }
        if(!StringUtils.isEmpty(settlementStaffName)){
        	phase = new Phrase();
        	phase.setLeading(0);
        	phase.add(new Chunk("结算审核"+settlementStaffName, textFont));
        	phase.add(Chunk.NEWLINE); //换行
        	paragraph.add(phase);
        	doc.add(paragraph);
        	
        	// 创建一个有2列的表格
        	table = new PdfPTable(4);
        	table.setHorizontalAlignment(Element.ALIGN_LEFT);
        	table.setSpacingBefore(10);
        	
        	content11=new PdfPCell(new Paragraph("当前需退还的保证金",textFont));
        	content11.setColspan(2);
        	content11.setPaddingLeft(5);
        	content11.setMinimumHeight(30); //设置单元格高度
        	content11.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content11.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content11);
        	String payAmtStr = "";
        	if(mchtCloseApplicationCustom.getPayAmt()!=null){
        		payAmtStr = mchtCloseApplicationCustom.getPayAmt().toString();
        	}
        	content12=new PdfPCell(new Paragraph(payAmtStr,textFont));
        	content12.setColspan(2);
        	content12.setPaddingLeft(5);
        	content12.setMinimumHeight(30); //设置单元格高度
        	content12.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content12.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content12);
        	
        	content21=new PdfPCell(new Paragraph("还需支付的货款",textFont));
        	content21.setColspan(2);
        	content21.setPaddingLeft(5);
        	content21.setMinimumHeight(30); //设置单元格高度
        	content21.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content21.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content21);
        	
        	String totalStr = "";
        	if(total!=null){
        		totalStr = total.toString();
        	}
        	content22=new PdfPCell(new Paragraph(totalStr,textFont));
        	content22.setColspan(2);
        	content22.setPaddingLeft(5);
        	content22.setMinimumHeight(30); //设置单元格高度
        	content22.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content22.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content22);
        	
        	content31=new PdfPCell(new Paragraph("结算审核",textFont));
        	content31.setColspan(2);
        	content31.setPaddingLeft(5);
        	content31.setMinimumHeight(30); //设置单元格高度
        	content31.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content31.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content31);
        	
        	String settlementAuditStatus="";
        	if("1".equals(mchtCloseApplicationCustom.getSettlementAuditStatus())){
        		settlementAuditStatus = "可结算";
        	}else{
        		settlementAuditStatus = "不可结算";
        	}
        	content32=new PdfPCell(new Paragraph(settlementAuditStatus,textFont));
        	content32.setColspan(2);
        	content32.setPaddingLeft(5);
        	content32.setMinimumHeight(30); //设置单元格高度
        	content32.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content32.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content32);
        	doc.add(table);
        }
        
        paragraph = new Paragraph();
        paragraph.setLeading(leading);
        paragraph.setSpacingBefore(firstTitleSpacing);
        paragraph.setSpacingAfter(firstTitleSpacing);
        paragraph.setMultipliedLeading(2);
        
        String opStaffName = "";
        if(!StringUtils.isEmpty(mchtCloseApplicationCustom.getOpStaffName())){
        	String opConfirmDate = format.format(mchtCloseApplicationCustom.getOpConfirmDate());
        	opStaffName += "("+mchtCloseApplicationCustom.getOpStaffName()+" "+opConfirmDate+")";
        }
        if(!StringUtils.isEmpty(opStaffName)){
        	phase = new Phrase();
        	phase.setLeading(0);
        	phase.add(new Chunk("操作结算"+opStaffName, textFont));
        	phase.add(Chunk.NEWLINE); //换行
        	doc.add(paragraph);
        	
        	// 创建一个有2列的表格
        	table = new PdfPTable(4);
        	table.setHorizontalAlignment(Element.ALIGN_LEFT);
        	table.setSpacingBefore(10);
        	
        	content11=new PdfPCell(new Paragraph("当前需退还的保证金",textFont));
        	content11.setColspan(2);
        	content11.setPaddingLeft(5);
        	content11.setMinimumHeight(30); //设置单元格高度
        	content11.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content11.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content11);
        	
        	String depositReturnStatus = "";
        	if("1".equals(mchtCloseApplicationCustom.getDepositReturnStatus())){
        		depositReturnStatus = "已退还";
        	}else{
        		depositReturnStatus = "未退还";
        	}
        	content12=new PdfPCell(new Paragraph(depositReturnStatus,textFont));
        	content12.setColspan(2);
        	content12.setPaddingLeft(5);
        	content12.setMinimumHeight(30); //设置单元格高度
        	content12.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content12.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content12);
        	
        	content21=new PdfPCell(new Paragraph("退还保证金日期",textFont));
        	content21.setColspan(2);
        	content21.setPaddingLeft(5);
        	content21.setMinimumHeight(30); //设置单元格高度
        	content21.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content21.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content21);
        	
        	String depositReturnDateStr = "";
        	if(mchtCloseApplicationCustom.getDepositReturnDate()!=null){
        		depositReturnDateStr = dateFormat.format(mchtCloseApplicationCustom.getDepositReturnDate());
        	}
        	content22=new PdfPCell(new Paragraph(depositReturnDateStr,textFont));
        	content22.setColspan(2);
        	content22.setPaddingLeft(5);
        	content22.setMinimumHeight(30); //设置单元格高度
        	content22.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content22.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content22);
        	
        	content31=new PdfPCell(new Paragraph("还需支付的货款",textFont));
        	content31.setColspan(2);
        	content31.setPaddingLeft(5);
        	content31.setMinimumHeight(30); //设置单元格高度
        	content31.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content31.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content31);
        	
        	String needPayStatus="";
        	if("1".equals(mchtCloseApplicationCustom.getNeedPayStatus())){
        		needPayStatus = "已结清";
        	}else{
        		needPayStatus = "未结清";
        	}
        	content32=new PdfPCell(new Paragraph(needPayStatus,textFont));
        	content32.setColspan(2);
        	content32.setPaddingLeft(5);
        	content32.setMinimumHeight(30); //设置单元格高度
        	content32.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        	content32.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        	table.addCell(content32);
        	doc.add(table);
        }
        
        doc.close();
        return filePath;
    }

}
