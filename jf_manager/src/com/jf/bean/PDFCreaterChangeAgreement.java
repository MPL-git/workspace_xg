package com.jf.bean;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.jf.common.ext.util.DateTimeUtil;
import com.jf.common.ext.util.SpringUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtils;
import com.jf.dao.MchtBrandRateChangeCustomMapper;
import com.jf.entity.CooperationChangeApply;
import com.jf.entity.MchtBrandRateChangeCustom;
import com.jf.entity.MchtBrandRateChangeExample;
import com.jf.entity.MchtContract;
import com.jf.entity.MchtContractExample;
import com.jf.entity.MchtDeposit;
import com.jf.entity.MchtDepositExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtProductBrandCustom;
import com.jf.entity.MchtProductBrandExample;
import com.jf.entity.MchtProductType;
import com.jf.entity.MchtProductTypeExample;
import com.jf.entity.ProductType;
import com.jf.service.MchtContractService;
import com.jf.service.MchtDepositService;
import com.jf.service.MchtInfoService;
import com.jf.service.MchtProductBrandService;
import com.jf.service.MchtProductTypeService;
import com.jf.service.ProductTypeService;
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
 * 变更函生成PDF
 */
public class PDFCreaterChangeAgreement {
    public static void main(String[] args) throws Exception {
        //imageWaterMark(createPDF(), "F://title.png");
        //createPDF();

//        String a = "甲方签章：" + PDFExp.leftPad("", 50) + "乙方签章：似懂非";
//        String b = "授权代表：" + PDFExp.leftPad("林一", 50) + "授权代表：";
//        String c = "联系方式：" + PDFExp.leftPad("0不是吧5 - 12345678", 50) + "联系方式：12345678911";
//        System.out.println(a + "");
//        System.out.println(b + "");
//        System.out.println(c + "");
    }

    private static int firstTitleSpacing = 5;     //一级标题前后间距
    //private static int secondTitleSpacing = 5;     //二级标题间距
    //private static int lineSpacing = 2; //行前后间距
    private static int leading = 20;     //段与段之间的距离
    private static int signSplit = 90;     //底部签名间隔空格数


    /**
     * 创建PDF文档
     * @return 合同路径
     * @throws Exception
     */
    public static String createPDF(CooperationChangeApply cooperationChangeApply) throws Exception {

    	MchtDepositService mchtDepositService = SpringUtil.getBean("mchtDepositService");
    	MchtContractService mchtContractService = SpringUtil.getBean("mchtContractService");
    	MchtProductTypeService mchtProductTypeService = SpringUtil.getBean("mchtProductTypeService");
        ProductTypeService productTypeService = SpringUtil.getBean("productTypeService");
        MchtProductBrandService mchtProductBrandService = SpringUtil.getBean("mchtProductBrandService");
        MchtInfoService mchtInfoService = SpringUtil.getBean("mchtInfoService");
        MchtBrandRateChangeCustomMapper mchtBrandRateChangeCustomMapper = SpringUtil.getBean("mchtBrandRateChangeCustomMapper");
        MchtContractExample mce = new MchtContractExample();
        mce.setOrderByClause("id desc");
        mce.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(cooperationChangeApply.getMchtId());
        List<MchtContract> mchtContracts = mchtContractService.selectByExample(mce);
        MchtContract contract = new MchtContract();
        if(mchtContracts!=null && mchtContracts.size()>0){
        	contract = mchtContracts.get(0);
        }
        // 合同起止时间
        String contractBeginDate = contract.getBeginDate() == null ? "            " : DateTimeUtil.formatDate(contract.getBeginDate(), "yyyy年MM月dd日");
        String contractCode = contract.getContractCode() == null?"":contract.getContractCode();
        MchtBrandRateChangeExample mbrce = new MchtBrandRateChangeExample();
        mbrce.createCriteria().andCooperationChangeApplyIdEqualTo(cooperationChangeApply.getId()).andDelFlagEqualTo("0");
        List<MchtBrandRateChangeCustom> mchtBrandRateChangeCustoms = mchtBrandRateChangeCustomMapper.selectByExample(mbrce);
        MchtDepositExample mde = new MchtDepositExample();
        mde.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(cooperationChangeApply.getMchtId());
        List<MchtDeposit> mchtDeposits = mchtDepositService.selectByExample(mde);
        //输出路径
        String filePath = FileUtil.getRandomFileName(cooperationChangeApply.getId() + ".pdf", 0, cooperationChangeApply.getMchtId());
        //String filePath = "F://temp/test2.pdf";

        //添加中文字体
//        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        BaseFont bfChinese = BaseFont.createFont(PDFCreaterChangeAgreement.class.getResource("/resources/fonts/simsun.ttf").getPath(),BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        //设置字体样式
        Font textFont = new Font(bfChinese,11.8f, Font.NORMAL); //正常
        Font boldFont = new Font(bfChinese,11.95f,Font.BOLD); //加粗
        Font underlineFont = new Font(bfChinese,11.95f,Font.UNDERLINE); //下划线
        Font firstTitleFont = new Font(bfChinese,12,Font.BOLD); //一级标题
        //Font secondTitleFont = new Font(bfChinese,12,Font.BOLD); //二级标题

        //设置纸张
        Rectangle rect = new Rectangle(PageSize.A4);

        //创建文档实例
        Document doc = new Document(rect,40f, 40f, 50f, 20f);

        //创建输出流
        PdfWriter writer = PdfWriter.getInstance(doc, FileUtil.getNewFileOutputStream(filePath));

        //页边空白  左右上下
        doc.setMargins(40, 40, 35, 35);
        writer.setPageEvent(new HeadFootInfoPdfPageEvent());
        doc.open();
        doc.newPage();

        Paragraph paragraph = new Paragraph("《醒购商城合作协议变更申请函》", new Font(bfChinese, 20, Font.BOLD));
        paragraph.setLeading(30);  // 设置和上面段落间距
        paragraph.setSpacingBefore(5);
        paragraph.setSpacingAfter(30);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        doc.add(paragraph);
        
        paragraph = new Paragraph();
        paragraph.setLeading(leading);
        paragraph.setSpacingBefore(firstTitleSpacing);
        paragraph.setSpacingAfter(firstTitleSpacing);
//        paragraph.setMultipliedLeading(2);

        Phrase phase = new Phrase();
        phase.setLeading(0);
        phase.add(new Chunk("至厦门聚买网络科技有限公司：", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("申请人于", textFont));
        phase.add(new Chunk(contractBeginDate, underlineFont));
        phase.add(new Chunk("与贵司签署编号为", textFont));
        phase.add(new Chunk(contractCode, underlineFont));
        phase.add(new Chunk("的《醒购商城合作协议》，根据贵司平", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("台规则及要求，现就合作内容申请变更，具体申请内容如下：", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("一、经营品牌、技术服务费费率变更为： ", textFont));
        phase.add(Chunk.NEWLINE); //换行
        paragraph.add(phase);
        doc.add(paragraph);
        
     // 创建一个有2列的表格
        PdfPTable table = new PdfPTable(2);
        table.setTotalWidth(new float[]{ 250, 120 }); //设置列宽
        table.setLockedWidth(true); //锁定列宽
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingBefore(10);
        PdfPCell cell = new PdfPCell(new Phrase("品牌", textFont));
        cell.setPaddingLeft(5);
        cell.setMinimumHeight(30); //设置单元格高度
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("技术服务费费率", textFont));
        cell.setPaddingLeft(5);
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);
        MchtProductBrandExample mpbe = new MchtProductBrandExample();
    	mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(cooperationChangeApply.getMchtId()).andStatusEqualTo("1");
    	List<MchtProductBrandCustom> mchtProductBrandCustoms = mchtProductBrandService.selectByCustomExample(mpbe);
    	List<MchtProductBrandCustom> changeMchtProductBrandCustoms = new ArrayList<MchtProductBrandCustom>();
        if(mchtBrandRateChangeCustoms!=null && mchtBrandRateChangeCustoms.size()>0){
        	for(MchtBrandRateChangeCustom mchtBrandRateChangeCustom : mchtBrandRateChangeCustoms){
        		for(MchtProductBrandCustom mchtProductBrandCustom:mchtProductBrandCustoms){
        			if(mchtProductBrandCustom.getId().equals(mchtBrandRateChangeCustom.getMchtProductBrandId())){
        				changeMchtProductBrandCustoms.add(mchtProductBrandCustom);
        				cell = new PdfPCell(new Phrase(mchtBrandRateChangeCustom.getBrandName(), textFont));
        				cell.setPaddingLeft(5);
        				cell.setMinimumHeight(30); //设置单元格高度
        				cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        				table.addCell(cell);
        				String popCommissionRateStr = String.valueOf(mchtBrandRateChangeCustom.getPopCommissionRate().multiply(new BigDecimal(100)));
        				Double d= Double.parseDouble(popCommissionRateStr); 
        				DecimalFormat df = new DecimalFormat("0.00"); 
        				String s = df.format(d);
        				cell = new PdfPCell(new Phrase(mchtBrandRateChangeCustom.getPopCommissionRate() == null ? "" : s+"%", textFont));
        				cell.setPaddingLeft(5);
        				cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        				table.addCell(cell);
        			}
        		}
        	}
        	mchtProductBrandCustoms.removeAll(changeMchtProductBrandCustoms);
        	for(MchtProductBrandCustom mchtProductBrandCustom:mchtProductBrandCustoms){
        		cell = new PdfPCell(new Phrase(mchtProductBrandCustom.getBrandName(), textFont));
        		cell.setPaddingLeft(5);
        		cell.setMinimumHeight(30); //设置单元格高度
        		cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        		cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        		table.addCell(cell);
        		String popCommissionRateStr = String.valueOf(mchtProductBrandCustom.getPopCommissionRate().multiply(new BigDecimal(100)));
        		Double d= Double.parseDouble(popCommissionRateStr); 
        		DecimalFormat df = new DecimalFormat("0.00"); 
        		String s = df.format(d);
        		cell = new PdfPCell(new Phrase(mchtProductBrandCustom.getPopCommissionRate() == null ? "" : s+"%", textFont));
        		cell.setPaddingLeft(5);
        		cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        		cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        		table.addCell(cell);
        	}
        }else{
        	for(MchtProductBrandCustom mchtProductBrandCustom:mchtProductBrandCustoms){
        		cell = new PdfPCell(new Phrase(mchtProductBrandCustom.getBrandName(), textFont));
        		cell.setPaddingLeft(5);
        		cell.setMinimumHeight(30); //设置单元格高度
        		cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        		cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        		table.addCell(cell);
        		String popCommissionRateStr = String.valueOf(mchtProductBrandCustom.getPopCommissionRate().multiply(new BigDecimal(100)));
        		Double d= Double.parseDouble(popCommissionRateStr); 
        		DecimalFormat df = new DecimalFormat("0.00"); 
        		String s = df.format(d);
        		cell = new PdfPCell(new Phrase(mchtProductBrandCustom.getPopCommissionRate() == null ? "" : s+"%", textFont));
        		cell.setPaddingLeft(5);
        		cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        		cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        		table.addCell(cell);
        	}
        }
        
        doc.add(table);

        paragraph = new Paragraph();
        paragraph.setLeading(leading);
        paragraph.setSpacingBefore(firstTitleSpacing);
        paragraph.setSpacingAfter(firstTitleSpacing);
        paragraph.setMultipliedLeading(2);

        phase = new Phrase();
        phase.setLeading(0);
        phase.add(new Chunk("二、应缴纳保证金变更为：保证金金额（人民币）", textFont));
        if(cooperationChangeApply.getDeposit()!=null){
        	phase.add(new Chunk(cooperationChangeApply.getDeposit().toString(), underlineFont));
        }else{
        	phase.add(new Chunk(mchtDeposits.get(0).getTotalAmt().toString(), underlineFont));
        }
        phase.add(new Chunk(" 元，申请人应当予以及时缴纳。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("三、店铺名变更：申请人店铺名变更为：", textFont));
        if(!StringUtils.isEmpty(cooperationChangeApply.getShopName())){
        	phase.add(new Chunk(cooperationChangeApply.getShopName(), underlineFont));
        }else{
        	MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(cooperationChangeApply.getMchtId());
        	phase.add(new Chunk(mchtInfo.getShopName(), underlineFont));
        }
        phase.add(new Chunk("。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("四、经营类目变更：申请人经营类目变更为：", textFont));
        if(cooperationChangeApply.getProductTypeId()!=null){
        	ProductType productType = productTypeService.selectByPrimaryKey(cooperationChangeApply.getProductTypeId());
        	phase.add(new Chunk(productType.getName(), underlineFont));
        }else{
        	MchtProductTypeExample mpte = new MchtProductTypeExample();
        	mpte.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(cooperationChangeApply.getMchtId()).andStatusEqualTo("1").andIsMainEqualTo("1");
        	List<MchtProductType> mchtProductTypes = mchtProductTypeService.selectByExample(mpte);
        	if(mchtProductTypes!=null && mchtProductTypes.size()>0){
        		ProductType pt = productTypeService.selectByPrimaryKey(mchtProductTypes.get(0).getProductTypeId());
        		phase.add(new Chunk(pt.getName(), underlineFont));
        	}else{
        		phase.add(new Chunk("", underlineFont));
        	}
        }
        phase.add(new Chunk("。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("五、申请变更生效时间：", textFont));
        phase.add(new Chunk(DateTimeUtil.formatDate(cooperationChangeApply.getCreateDate(), "yyyy年MM月dd日"), underlineFont));
        phase.add(new Chunk("。申请人若申请多次变更的，经贵司同意", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("后，合作内容则以申请人最后一次变更申请为准。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("六、若贵司同意申请人变更申请，则本申请函所涉内容与《醒购商城合作协议》约定内容", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("不一致的，则以本申请函内容为准。其他事宜按《醒购商城合作协议》要求执行。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        paragraph.add(phase);
        doc.add(paragraph);


        paragraph = new Paragraph();
        paragraph.setLeading(leading);
        paragraph.setSpacingBefore(firstTitleSpacing);
        paragraph.setSpacingAfter(firstTitleSpacing);
        paragraph.setMultipliedLeading(2);

        phase = new Phrase();
        phase.setLeading(0);
        phase.add(new Chunk("顺颂商祺！", textFont));
        phase.add(Chunk.NEWLINE); //换行
        paragraph.add(phase);
        doc.add(paragraph);
        
        paragraph = new Paragraph();
        paragraph.setLeading(leading);
        paragraph.setSpacingBefore(firstTitleSpacing);
        paragraph.setSpacingAfter(firstTitleSpacing);
        paragraph.setMultipliedLeading(2);
        
        phase = new Phrase();
        phase.setLeading(0);
        phase.add(new Chunk("                                     申请人（盖章）", textFont));
        phase.add(Chunk.NEWLINE); //换行
        paragraph.add(phase);
        doc.add(paragraph);
        
        paragraph = new Paragraph();
        paragraph.setLeading(leading);
        paragraph.setSpacingBefore(firstTitleSpacing);
        paragraph.setSpacingAfter(firstTitleSpacing);
        paragraph.setMultipliedLeading(2);
        
        phase = new Phrase();
        phase.setLeading(0);
        phase.add(new Chunk("若同意申请，请在此处盖章确认！", textFont));
        paragraph.add(phase);
        doc.add(paragraph);
        
        paragraph = new Paragraph();
        paragraph.setLeading(leading);
        paragraph.setSpacingBefore(firstTitleSpacing);
        paragraph.setSpacingAfter(firstTitleSpacing);
        paragraph.setMultipliedLeading(2);
        
        phase = new Phrase();
        phase.setLeading(0);
        phase.add(new Chunk("厦门聚买网络科技有限公司", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("日期：", textFont));
        paragraph.add(phase);
        doc.add(paragraph);
        
        doc.close();
        return filePath;
    }

}
