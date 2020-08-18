package com.jf.bean;

import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.SpringUtil;
import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.*;
import com.jf.service.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 合同生成PDF
 */
public class PDFCreaterEndAgreement {
    public static void main(String[] args) throws Exception {
    	
    }

    private static int firstTitleSpacing = 5;     //一级标题前后间距
    private static int leading = 25;     //段与段之间的距离
    private static int signSplit = 60;     //底部签名间隔空格数


    /**
     * 创建PDF文档
     * @return 合同路径
     * @throws Exception
     */
    public static String createPDF(MchtCloseApplication mchtCloseApplication) throws Exception {

        MchtInfoService mchtInfoService = SpringUtil.getBean("mchtInfoService");
        MchtContractService mchtContractService = SpringUtil.getBean("mchtContractService");
        MchtProductBrandService mchtProductBrandService = SpringUtil.getBean("mchtProductBrandService");
        MchtDepositService mchtDepositService = SpringUtil.getBean("mchtDepositService");
        ProductTypeService productTypeService = SpringUtil.getBean("productTypeService");
        MchtProductTypeService mchtProductTypeService = SpringUtil.getBean("mchtProductTypeService");
        PlatformContactService platformContactService = SpringUtil.getBean("platformContactService");
        
        //关店申请年月日
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String createDateStr = sdf.format(mchtCloseApplication.getCreateDate());
        String year = createDateStr.substring(0, 4);
        String month = createDateStr.substring(5,7);
        String day = createDateStr.substring(8);
        //协议出具日期
     /*   Date agreementIssueDate = mchtCloseApplication.getAgreementIssueDate();
        String agreementIssueDateStr = sdf.format(agreementIssueDate);
        String agreementIssueDateYear = agreementIssueDateStr.substring(0, 4);
        String agreementIssueDateMonth = agreementIssueDateStr.substring(5,7);
        String agreementIssueDateDay = agreementIssueDateStr.substring(8);*/
        //协议出具日期改为财务货代结清确认的当天日期
        Date agreementIssueDate = new Date();
        String agreementIssueDateStr = sdf.format(agreementIssueDate);
        String agreementIssueDateYear = agreementIssueDateStr.substring(0, 4);
        String agreementIssueDateMonth = agreementIssueDateStr.substring(5,7);
        String agreementIssueDateDay = agreementIssueDateStr.substring(8);
        
        // 商家信息
        MchtInfo mchtInfo = mchtInfoService.findById(mchtCloseApplication.getMchtId());
        //合同编号
        String currentContractCode = "";
        String prevContractCode = "";
        MchtContractExample mce = new MchtContractExample();
        mce.setOrderByClause("id desc");
        mce.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtCloseApplication.getMchtId()).andAuditStatusEqualTo("3");
        List<MchtContract> mchtContracts = mchtContractService.selectByExample(mce);
        if(mchtContracts!=null && mchtContracts.size()>0){
        	currentContractCode = mchtContracts.get(0).getContractCode();
        	if(mchtContracts.get(0).getLastContractId()!=null){
        		MchtContract mchtContract = mchtContractService.selectByPrimaryKey(mchtContracts.get(0).getLastContractId());
        		prevContractCode = mchtContract.getContractCode();
        	}
        }
        // 品牌信息
        String productBrandStr = "";
        MchtProductBrandExample e = new MchtProductBrandExample();
        MchtProductBrandExample.Criteria c = e.createCriteria();
        c.andDelFlagEqualTo("0");
        c.andMchtIdEqualTo(mchtInfo.getId());
        c.andAuditStatusEqualTo("3");
        List<MchtProductBrandCustom> mchtProductBrandCustoms = mchtProductBrandService.selectByExample2(e);
        for(MchtProductBrandCustom mchtProductBrandCustom:mchtProductBrandCustoms){
        	if(StringUtils.isEmpty(productBrandStr)){
        		productBrandStr = mchtProductBrandCustom.getBrandName();
        	}else{
        		productBrandStr+="、"+mchtProductBrandCustom.getBrandName();
        	}
        }
        
        
        // 经营类目
        String productTypeStr = "";
        QueryObject queryObject = new QueryObject();
        queryObject.addQuery("mchtId", mchtCloseApplication.getMchtId());
        List<String> statusIn = new ArrayList<String>();
        statusIn.add("1");	//正常
        queryObject.addQuery("statusIn", statusIn);
        List<MchtProductType> mchtProductTypeList = mchtProductTypeService.findList(queryObject);
        List<Integer> productTypeIds = new ArrayList<Integer>();
        for(MchtProductType mchtProductType : mchtProductTypeList){
            productTypeIds.add(mchtProductType.getProductTypeId());
        }
        queryObject = new QueryObject();
        queryObject.addQuery("status", "1");
        queryObject.addQuery("productTypeIds", productTypeIds);
        if(productTypeIds!=null && productTypeIds.size()>0){
        	List<ProductType> productTypeList = productTypeService.list(queryObject);
        	for(ProductType productType : productTypeList){
        		if(productType.gettLevel() == 1){
        			productTypeStr += productType.getName() + "、";
        		}
        	}
        }
        if(StrKit.notBlank(productTypeStr)){
            productTypeStr = productTypeStr.substring(0, productTypeStr.length() -1);
        }

        // 保证金
        MchtDeposit mchtDeposit = mchtDepositService.findByMchtId(mchtCloseApplication.getMchtId());
        
        // 平台招商对接人
        PlatformContact platformContact = platformContactService.findByMchtId(mchtCloseApplication.getMchtId(), Const.PLAT_CONTACT_TYPE_MERCHANTS);
        String contactName =  platformContact == null ? "" : platformContact.getContactName();
        
        //输出路径
        String filePath = FileUtil.getRandomFileName(mchtCloseApplication.getId() + ".pdf", 0, mchtCloseApplication.getMchtId());
        //String filePath = "F://temp/test2.pdf";

        //添加中文字体
        BaseFont bfChinese = BaseFont.createFont(PDFCreaterEndAgreement.class.getResource("/resources/fonts/simsun.ttf").getPath(),BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        //设置字体样式
        Font textFont = new Font(bfChinese,11, Font.NORMAL); //正常
        Font boldFont = new Font(bfChinese,10.95f,Font.BOLD); //加粗
        Font underlineFont = new Font(bfChinese,10.95f,Font.UNDERLINE); //下划线
        Font firstTitleFont = new Font(bfChinese,11,Font.BOLD); //一级标题

        //设置纸张
        Rectangle rect = new Rectangle(PageSize.A4);

        //创建文档实例
        Document doc = new Document(rect);

        //创建输出流
        PdfWriter writer = PdfWriter.getInstance(doc, FileUtil.getNewFileOutputStream(filePath));

        //页边空白  左右上下
        doc.setMargins(50, 50, 45, 45);

        // 设置页眉
        Phrase headPhrase = new Phrase();
        headPhrase.setLeading(9);
        headPhrase.add(new Chunk("厦门聚买网络科技有限公司", new Font(bfChinese, 6, Font.NORMAL, Color.GRAY)));
        headPhrase.add(new Chunk(" XiaMen (JuMai) Network Technology Co., Ltd.", new Font(BaseFont.createFont(),6, Font.NORMAL, Color.GRAY)));
        headPhrase.add(Chunk.NEWLINE); //换行
        headPhrase.add(new Chunk("TEL：      FAX：", new Font(BaseFont.createFont(), 6, Font.NORMAL, Color.GRAY)));
        headPhrase.add(Chunk.NEWLINE); //换行
        headPhrase.add(new Chunk("地址：厦门市思明区塔埔东路171号1104-A单元", new Font(bfChinese,6,Font.BOLD, Color.GRAY)));
        HeaderFooter header=new HeaderFooter(headPhrase,false);
        //设置是否有边框等
        //header.setBorder(Rectangle.NO_BORDER);
        header.setBorder(Rectangle.BOTTOM);
        header.setAlignment(0); //0是靠左   1是居中   2是居右
        header.setBorderColor(Color.black);
        header.setBorderWidth(1);
        doc.setHeader(header);

        writer.setPageEvent(new HeadFootInfoPdfPageEvent());

        doc.open();
        doc.newPage();

        Paragraph paragraph = new Paragraph("《醒购商城合作协议终止合作协议书》", new Font(bfChinese, 23, Font.BOLD));
        paragraph.setLeading(20);  // 设置和上面段落间距
        paragraph.setSpacingBefore(5);
        paragraph.setSpacingAfter(30);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        doc.add(paragraph);
        
        paragraph = new Paragraph();
        paragraph.setLeading(leading);
        paragraph.setSpacingBefore(firstTitleSpacing);
        paragraph.setSpacingAfter(firstTitleSpacing);
        paragraph.setMultipliedLeading(2);

        Phrase phase = new Phrase();
        phase.setLeading(0);
        phase.add(new Chunk("甲方：厦门聚买网络科技有限公司", firstTitleFont));
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
        phase.add(new Chunk("乙方：", firstTitleFont));
        phase.add(new Chunk(mchtInfo.getCompanyName(), underlineFont));
        phase.add(Chunk.NEWLINE); //换行
        paragraph.add(phase);
        doc.add(paragraph);

        paragraph = new Paragraph();
        paragraph.setLeading(1.5f);
        paragraph.setSpacingBefore(firstTitleSpacing);
        paragraph.setSpacingAfter(firstTitleSpacing);
        paragraph.setMultipliedLeading(1.5f);

        phase = new Phrase();
        phase.setLeading(-0.5f);
    	phase.add(new Chunk("鉴于甲乙双方签订的《醒购商城合作协议》（下文称原协议，原协议编号：", firstTitleFont));
    	phase.add(new Chunk(currentContractCode, underlineFont));
    	phase.add(new Chunk("、", firstTitleFont));
    	phase.add(Chunk.NEWLINE); //换行
    	phase.add(new Chunk("补充协议编号：", firstTitleFont));
    	phase.add(new Chunk("", underlineFont));
    	phase.add(new Chunk("）。协议约定乙方在醒购商城设立店铺，并通过醒购商城直接销", firstTitleFont));
    	phase.add(Chunk.NEWLINE); //换行
    	phase.add(new Chunk("售商品给消费者。", firstTitleFont));
        
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("商家序号：", firstTitleFont));
        phase.add(new Chunk(mchtInfo.getMchtCode(), underlineFont));
        phase.add(new Chunk("；店铺名称：", firstTitleFont));
        phase.add(new Chunk(mchtInfo.getShopName(), underlineFont));
        phase.add(new Chunk("；经营的类目：", firstTitleFont));
        phase.add(new Chunk(productTypeStr, underlineFont));
        phase.add(new Chunk("；", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("经营的品牌有：", firstTitleFont));
        phase.add(new Chunk(productBrandStr, underlineFont));
        phase.add(new Chunk("；", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("乙方于", firstTitleFont));
        phase.add(new Chunk(""+year, underlineFont));
        phase.add(new Chunk("年", firstTitleFont));
        phase.add(new Chunk(""+month, underlineFont));
        phase.add(new Chunk("月", firstTitleFont));
        phase.add(new Chunk(""+day, underlineFont));
        phase.add(new Chunk("日", firstTitleFont));
        phase.add(new Chunk("向甲方申请关闭店铺（品牌），并提出终止合作。现经双方", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("友好协商，就双方终止合作等相关事宜达成如下共识，以供双方共同遵守：", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("一、截止至", textFont));
        phase.add(new Chunk(""+agreementIssueDateYear, underlineFont));
        phase.add(new Chunk("年", textFont));
        phase.add(new Chunk(""+agreementIssueDateMonth, underlineFont));
        phase.add(new Chunk("月", textFont));
        phase.add(new Chunk(""+agreementIssueDateDay, underlineFont));
        phase.add(new Chunk("日", textFont));
        phase.add(new Chunk("乙方保证在醒购商城设立的店铺确已暂停运营、所有品牌在", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("售商品已下架、所有商品订单相关服务事项已完结、所有货款已结清。乙方因违反相关约定需", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("扣取相应的保证金，", textFont));
//        String unpayAmtStr="";
//        if(mchtDeposit.getUnpayAmt()!=null){
//        	int result = mchtDeposit.getUnpayAmt().compareTo(new BigDecimal(0));
//        	if(result<0){
//        		unpayAmtStr = mchtDeposit.getUnpayAmt().toString().substring(1);
//        	}
//        }else{
//        	unpayAmtStr = mchtDeposit.getUnpayAmt().toString();
//        }
//        phase.add(new Chunk(""+unpayAmtStr, underlineFont));
        phase.add(new Chunk("扣完之后", textFont));
        phase.add(new Chunk("保证金余额：", firstTitleFont));
        phase.add(new Chunk(""+mchtDeposit.getPayAmt(), underlineFont));
        phase.add(new Chunk("元。", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("二、在商品包修期内，如有发现之前遗漏处理的商品订单相关服务问题或者新发现的问题，甲", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("方将根据现行平台规则进行相应的处理，如产生罚款或其他导致保证金变动的，保证金余额", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("重新计算。", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("三、所有售出商品的包修期限均届满且未发生任何商品问题的，甲方将保证金无息退还给乙方。", firstTitleFont));
      /*  phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("方。", firstTitleFont));*/
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("四、未产生任何销售的，在本协议生效且届满一个月后无息退还。", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("五、乙方收到保证金后仍需继续执行售后服务并对已售出的商品承担一切责任，包括但不限于", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("人身损害赔偿、财产损害赔偿、“三包”有效期内的退货、换货、维修。若乙方拒绝执行售后", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("服务或拒绝承担相关责任，甲方有权要求乙方按保证金双倍支付惩罚金，并要求乙方赔偿由", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("此给甲方造成的损失。", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("六、本协议与原协议冲突的条款以本协议为准。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("七、本协议中的所有术语，除非另有说明，否则其定义与原协议中的定义相同。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        
        //合同id 1003
        MchtContractExample mchtContractExample = new MchtContractExample();
        mchtContractExample.setOrderByClause("id desc");
        mchtContractExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtCloseApplication.getMchtId()).andAuditStatusEqualTo("3");
		List<MchtContract> mchtContractList = mchtContractService.selectByExample(mce);
		if(mchtContractList!=null && mchtContractList.size()>0){
			if(mchtContractList.get(0).getId()>=1003){//合同ID大于等于1003的
				phase.add(new Chunk("八、原协议终止后，原协议第4.4款、第5.6款，第7.8款、第10.4款、第12款，第14款、", firstTitleFont));
				phase.add(Chunk.NEWLINE); //换行
				phase.add(new Chunk("第15.4款、第16.9款、第20.2款等相关条款仍继续有效。", firstTitleFont));
				phase.add(Chunk.NEWLINE); //换行
			}else{
				phase.add(new Chunk("八、原协议终止后，原协议第4.4款、第7.6款，第8.8款、第9.5款、第11款，第13款、", firstTitleFont));
				phase.add(Chunk.NEWLINE); //换行
				phase.add(new Chunk("第14.3款、第15.9款、第19.2款等相关条款仍继续有效。", firstTitleFont));
				phase.add(Chunk.NEWLINE); //换行
			}
		}
        phase.add(new Chunk("九、本协议自双方盖章后生效，一式贰份，双方各执壹份，均具有同等法律效力。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("（以下无正文。）", textFont));
        paragraph.add(phase);
        doc.add(paragraph);

        // 创建一个有2列的表格
        PdfPTable table = new PdfPTable(2);
        table.setTotalWidth(new float[]{ 260, 260 }); //设置列宽
        table.setLockedWidth(true); //锁定列宽
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("甲方：厦门聚买网络科技有限公司", firstTitleFont));
        cell.setMinimumHeight(30); //设置单元格高度
        cell.setBorderWidth(0);
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("乙方：", firstTitleFont));
        cell.setBorderWidth(0);
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("授权代表：" + contactName, textFont));
        cell.setBorderWidth(0);
        cell.setMinimumHeight(30); //设置单元格高度
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("授权代表：", textFont));
        cell.setBorderWidth(0);
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);
        
        //cell = new PdfPCell(new Phrase("签订日期：" + agreementIssueDateYear+"年"+agreementIssueDateMonth+"月"+agreementIssueDateDay+"日", textFont));
        cell = new PdfPCell(new Phrase("签订日期：", textFont));
        cell.setBorderWidth(0);
        cell.setMinimumHeight(30); //设置单元格高度
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("签订日期：", textFont));
        cell.setBorderWidth(0);
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);
        doc.add(table);
        
        doc.close();
        return filePath;
    }

}
