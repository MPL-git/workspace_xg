package com.jf.bean;

import java.awt.Color;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.DateTimeUtil;
import com.jf.common.ext.util.SpringUtil;
import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.FileUtil;
import com.jf.entity.Area;
import com.jf.entity.MchtContract;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtProductBrand;
import com.jf.entity.MchtProductType;
import com.jf.entity.MchtProductTypeExample;
import com.jf.entity.PlatformContact;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductType;
import com.jf.service.AreaService;
import com.jf.service.MchtDepositService;
import com.jf.service.MchtInfoService;
import com.jf.service.MchtProductBrandService;
import com.jf.service.MchtProductTypeService;
import com.jf.service.PlatformContactService;
import com.jf.service.ProductBrandService;
import com.jf.service.ProductTypeService;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 个体合同生成PDF
 */
public class PDFCreaterIndividualContract {
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
    public static String createPDF(MchtContract contract) throws Exception {
//        return null;
//    }
//    public static String createPDF() throws Exception {

        MchtInfoService mchtInfoService = SpringUtil.getBean("mchtInfoService");
        MchtProductBrandService mchtProductBrandService = SpringUtil.getBean("mchtProductBrandService");
        PlatformContactService platformContactService = SpringUtil.getBean("platformContactService");
        MchtDepositService mchtDepositService = SpringUtil.getBean("mchtDepositService");
        AreaService areaService = SpringUtil.getBean("areaService");
        ProductBrandService productBrandService = SpringUtil.getBean("productBrandService");
        ProductTypeService productTypeService = SpringUtil.getBean("productTypeService");
        MchtProductTypeService mchtProductTypeService = SpringUtil.getBean("mchtProductTypeService");

        // 商家信息
        MchtInfo mchtInfo = mchtInfoService.findById(contract.getMchtId());
        // 通讯地址
        Area province = areaService.selectByPrimaryKey(mchtInfo.getContactProvince());
        Area city = areaService.selectByPrimaryKey(mchtInfo.getContactCity());
        Area county = areaService.selectByPrimaryKey(mchtInfo.getContactCounty());
        String mchtContactAddress = (province == null ? "" : province.getAreaName());
        mchtContactAddress += (city == null ? "" : city.getAreaName());
        mchtContactAddress += (county == null ? "" : county.getAreaName());
        mchtContactAddress += (mchtInfo.getContactAddress() == null ? "" : mchtInfo.getContactAddress());

        // 平台招商对接人
        PlatformContact platformContact = platformContactService.findByMchtId(contract.getMchtId(), Const.PLAT_CONTACT_TYPE_MERCHANTS);
        String contactName =  platformContact == null ? "" : platformContact.getContactName();
//        String contactMobile =  platformContact == null ? "" : platformContact.getMobile();
        String contactMobile =  platformContact == null ? "" : platformContact.getTel();
        String contactEmail =  platformContact == null ? "" : platformContact.getEmail();

        // 品牌信息
        QueryObject queryObject = new QueryObject();
        queryObject.addQuery("mchtId", mchtInfo.getId());
        queryObject.addQuery("status", "1");
        queryObject.addQuery("auditStatus", "3");
//        List<String> auditStatusIn = new ArrayList<String>();
//        auditStatusIn.add("0");	//申请中
//        auditStatusIn.add("1");	//正常
//        queryObject.addQuery("auditStatusIn", auditStatusIn);
        List<MchtProductBrand> mchtProductBrandList = mchtProductBrandService.list(queryObject);

        // 经营类目
        String productTypeStr = "";
        queryObject = new QueryObject();
        queryObject.addQuery("mchtId", contract.getMchtId());
        List<String> statusIn = new ArrayList<String>();
        statusIn.add("0");	//申请中
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
        List<ProductType> productTypeList = productTypeService.list(queryObject);
        for(ProductType productType : productTypeList){
            if(productType.gettLevel() == 1){
                productTypeStr += productType.getName() + "、";
            }
        }
        if(StrKit.notBlank(productTypeStr)){
            productTypeStr = productTypeStr.substring(0, productTypeStr.length() -1);
        }

        // 保证金
//        MchtDeposit mchtDeposit = mchtDepositService.findByMchtId(mchtInfo.getId());
        String mchtDepositTotalAmt = "          ";
//        if(mchtDeposit != null){
//            mchtDepositTotalAmt = String.valueOf(mchtDeposit.getTotalAmt());
//        }
        if(mchtInfo.getContractDeposit()!=null){
        	mchtDepositTotalAmt = String.valueOf(mchtInfo.getContractDeposit());
        }
        

        // 合同起止时间
        String contractBeginDate = contract.getBeginDate() == null ? "            " : DateTimeUtil.formatDate(contract.getBeginDate(), "yyyy年MM月dd日");
        String contractEndDate = contract.getEndDate() == null ? "            " : DateTimeUtil.formatDate(contract.getEndDate(), "yyyy年MM月dd日");


        //输出路径
        String filePath = FileUtil.getRandomFileName(contract.getId() + ".pdf", 0, contract.getMchtId());
        //String filePath = "F://temp/test2.pdf";

        //添加中文字体
//        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        BaseFont bfChinese = BaseFont.createFont(PDFCreaterIndividualContract.class.getResource("/resources/fonts/simsun.ttf").getPath(),BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
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
//        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(new File(filePath)));
        PdfWriter writer = PdfWriter.getInstance(doc, FileUtil.getNewFileOutputStream(filePath));

        //页边空白  左右上下
        doc.setMargins(50, 50, 45, 45);

        // 设置页眉
        Phrase headPhrase = new Phrase();
        headPhrase.setLeading(9);
        headPhrase.add(new Chunk("厦门聚买网络科技有限公司", new Font(bfChinese, 6, Font.NORMAL, Color.GRAY)));
        headPhrase.add(new Chunk(" XiaMen (JuMai) Network Technology Co., Ltd.", new Font(BaseFont.createFont(),6, Font.NORMAL, Color.GRAY)));
        headPhrase.add(Chunk.NEWLINE); //换行
        headPhrase.add(new Chunk("TEL：4008088227", new Font(BaseFont.createFont(), 6, Font.NORMAL, Color.GRAY)));
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

        Paragraph paragraph = new Paragraph("《醒购平台合作协议》", new Font(bfChinese, 25, Font.BOLD));
        paragraph.setLeading(90);  // 设置和上面段落间距
        paragraph.setSpacingBefore(5);
        paragraph.setSpacingAfter(170);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        doc.add(paragraph);
        
        paragraph = new Paragraph();
        paragraph.setLeading(leading);
        paragraph.setSpacingBefore(firstTitleSpacing);
        paragraph.setSpacingAfter(firstTitleSpacing);
        paragraph.setMultipliedLeading(2);

        Phrase phase = new Phrase();
        phase.setLeading(0);
        phase.add(new Chunk("协议编号："+contract.getContractCode(), textFont));
        phase.add(Chunk.NEWLINE); //换行
        paragraph.add(phase);
        doc.add(paragraph);

        // 创建一个有2列的表格
        PdfPTable table = new PdfPTable(2);
        table.setTotalWidth(new float[]{ 240, 240 }); //设置列宽
        table.setLockedWidth(true); //锁定列宽
        PdfPCell cell;

        cell = new PdfPCell(new Phrase("甲方：厦门聚买网络科技有限公司", textFont));
        cell.setPaddingLeft(5);
        cell.setMinimumHeight(30); //设置单元格高度
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("乙方：" + mchtInfo.getCompanyName(), textFont));
        cell.setPaddingLeft(5);
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("联系电话：4008088227", textFont));
        cell.setPaddingLeft(5);
        cell.setMinimumHeight(30); //设置单元格高度
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("联系电话：", textFont));
        cell.setPaddingLeft(5);
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("电子邮件：zs@xgbuy.cc", textFont));
        cell.setPaddingLeft(5);
        cell.setMinimumHeight(30); //设置单元格高度
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("电子邮件：", textFont));
        cell.setPaddingLeft(5);
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("通讯地址：福建省厦门市思明区观音山运营中心五号楼2层", textFont));
        cell.setPaddingLeft(5);
        cell.setMinimumHeight(42); //设置单元格高度
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("通讯地址：" + mchtContactAddress, textFont));
        cell.setPaddingLeft(5);
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);
        doc.add(table);

        doc.newPage();

        paragraph = new Paragraph();
        paragraph.setLeading(leading);
        paragraph.setSpacingBefore(firstTitleSpacing);
        paragraph.setSpacingAfter(firstTitleSpacing);
        paragraph.setMultipliedLeading(2);

        phase = new Phrase();
        phase.setLeading(0);
        phase.add(new Chunk("1 签约须知", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("1.1 《醒购商城合作协议》（以下简称“本协议”）是由醒购（网址：xgbuy.cc）、醒购APP及醒购商城（以下简称“醒购”）的运营方即厦门聚买网络科技有限公司（以下简称“甲方”）与乙方达成的关于提供和使用醒购服务的各项条款、条件和规则。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("1.2 甲方在此特别提醒乙方认真阅读、充分理解本协议各条款（对于本协议中以加粗字体显示的内容，应重点阅读），并请乙方审慎考虑并选择接受或不接受签订本协议、本协议一经签订视为乙方同意接受双方合作的所有条款、条件和规则。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("1.3 协议内容：本协议内容包括协议正文及所有甲方已经发布的或将来可能发布的各类规则。所有规则为协议不可分割的一部分，与协议正文具有同等法律效力。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("1.4 醒购发布的规则与本协议冲突的，以醒购发布的最新规则为准。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("1.5 在适用的法律法规允许的最大限度内，甲方有权根据情况不时地制定、修订、调整或变更本协议正文及附件，并将提前至少7日公示。前述制定、修订、调整或变更后的本协议正文及附件一经甲方公示生效后，成为本协议的一部分；虽有前述约定，本协议生效后，如乙方不同意前述制定、修订、调整或变更后的本协议正文及附件，可自甲方在公示之日起3个工作日内向甲方提出书面异议，在此种情况下，本协议将于甲方收到乙方书面异议之日起自动终止，且甲方对于该等终止不负有任何违约责任或其他责任，乙方应与甲方协商本协议终止事宜（包括但不限于款项结算事宜)。如乙方未根据前述约定向甲方提出异议，即视为乙方接受前述制定、修订、调整或变更后的本协议正文及附件；如乙方登录或继续使用醒购相关账户（包括但不限于乙方账户，下同），则视为乙方接受前述制定、修订、调整或变更后的本协议正文及附件。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("1.6 商城开通：乙方首次申请入驻醒购商城时需签署本协议，但本协议生效后所涉的商城服务并不立即开通，乙方根据醒购要求履行上传相关资质、支付保证金等义务后，经甲方审核通过并向乙方发出服务开通的通知时，本协议项下的商城服务正式开通。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("", textFont));
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
        phase.add(new Chunk("2. 合作模式", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("2.1 乙方在醒购设立店铺，店铺名为：", textFont));
        phase.add(new Chunk(mchtInfo.getShopName(), underlineFont));
        phase.add(new Chunk("，并通过醒购商城直接销售商品给消费者。开店成功后，乙方可根据甲方系统规则安排商品上线销售。乙方应当根据甲方要求提供商品相关素材、参数、信息等、提供售前售中售后服务、提供商品配送服务；同时乙方应当负责给消费者开具发票 。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("2.2 甲方负责醒购商城的日常维护、技术支持，保证商城的正常运作。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("2.3 乙方有权决定销售商品的定价，甲方有权根据商品销量、价格竞争力、乙方的服务水平等单方决定商品售卖期和展示位置。", boldFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("2.4 甲方按本协议约定的送达方式向乙方提供商城后台的管理账户及初始密码，乙方应当及时修改并妥善保管账户与密码，不得泄露或提供给第三方，否则一切损失及后果由乙方自行承担。乙方保证严格遵守本协议及醒购商城发布的所有规则。", textFont));
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
        phase.add(new Chunk("3.服务费用：", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("3.1 乙方须按照双方约定的技术服务费率向甲方支付技术服务费，技术服务费率指乙方在醒购完成的每一笔订单交易额的百分比。常规销售按下列技术服务费费率执行，若乙方申请特卖，技术服务费费率双方另行协商。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("3.2以下为乙方在“醒购”上经营的品牌类目清单（可另附清单）、技术服务费费率", textFont));
        phase.add(Chunk.NEWLINE); //换行
        paragraph.add(phase);
        doc.add(paragraph);


        // 创建一个有2列的表格
        table = new PdfPTable(2);
        table.setTotalWidth(new float[]{ 250, 120 }); //设置列宽
        table.setLockedWidth(true); //锁定列宽
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingBefore(10);
        cell = new PdfPCell(new Phrase("品牌", textFont));
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
        
        ProductBrand productBrand;
        for(MchtProductBrand mchtProductBrand : mchtProductBrandList){
        	if(!mchtProductBrand.getProductBrandId().equals(0)){
        		productBrand = productBrandService.selectByPrimaryKey(mchtProductBrand.getProductBrandId());
        		cell = new PdfPCell(new Phrase(productBrand.getName(), textFont));
        		cell.setPaddingLeft(5);
        		cell.setMinimumHeight(30); //设置单元格高度
        		cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        		cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        		table.addCell(cell);
        		if(mchtProductBrand.getPopCommissionRate()!=null){
        			String popCommissionRateStr = String.valueOf(mchtProductBrand.getPopCommissionRate().multiply(new BigDecimal(100)));
        			Double d= Double.parseDouble(popCommissionRateStr); 
        			DecimalFormat df = new DecimalFormat("0.00"); 
        			String s = df.format(d);
        			cell = new PdfPCell(new Phrase(s+"%", textFont));
        		}else{
        			
        			cell = new PdfPCell(new Phrase("", textFont));
        		}
        		cell.setPaddingLeft(5);
        		cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        		cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        		table.addCell(cell);
        	}else{
        		cell = new PdfPCell(new Phrase("其他", textFont));
        		cell.setPaddingLeft(5);
        		cell.setMinimumHeight(30); //设置单元格高度
        		cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        		cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        		table.addCell(cell);
        		MchtProductTypeExample mpte = new MchtProductTypeExample();
        		mpte.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(contract.getMchtId()).andIsMainEqualTo("1").andStatusEqualTo("1");
        		List<MchtProductType> mchtProductTypes = mchtProductTypeService.selectByExample(mpte);
        		if(mchtProductTypes!=null && mchtProductTypes.size()>0){
        			ProductType productType = productTypeService.selectByPrimaryKey(mchtProductTypes.get(0).getProductTypeId());
        			if(productType!=null && productType.getIndividualFeeRate()!=null){
        				String individualFeeRateStr = String.valueOf(productType.getIndividualFeeRate().multiply(new BigDecimal(100)));
            			Double d= Double.parseDouble(individualFeeRateStr); 
            			DecimalFormat df = new DecimalFormat("0.00"); 
            			String s = df.format(d);
            			cell = new PdfPCell(new Phrase(s+"%", textFont));
        			}else{
        				cell = new PdfPCell(new Phrase("", textFont));
        			}
        		}else{
        			cell = new PdfPCell(new Phrase("", textFont));
        		}
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
        phase.add(new Chunk("3.3 乙方授权甲方从乙方销售货款中直接扣除乙方须支付给甲方的技术服务费。若：微信支付、支付宝支付等第三方支付机构调整支付服务费费用的比例，则本条款约定的支付费用比例应当相应调整。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("3.4 乙方充分理解并确认，因后期实际运营需要，甲方可根据商城不同力度的优惠活动对商城技术服务费用的固定比例进行调整，同时甲方保留变更收取商城费用标准的权利，具体收费标准将以商城规则形式发布。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("3.5 协议生效后，乙方须向甲方支付平台使用费 0 元/月。", textFont));
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
        phase.add(new Chunk("4. 资质提交：", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("4.1 资质文件提交：乙方欲使用本协议下服务，应当根据甲方要求向甲方或甲方指定合作方提交其在申请服务时应当提供的资质文件或其他相关证明。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("4.2 ", textFont));
        phase.add(new Chunk("资质文件变更的通知及提交：协议期内，上述相关资质文件的任何变更，乙方都应自变更之日起5日内通知甲方并提交变更后资质文件，如上述资质文件变更后导致乙方不再符合甲方要求或不再具备履行本协议的情形出现时，甲方有权立即终止或中止本协议。", boldFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("4.3 甲方有权对乙方的资质文件进行不定时的抽查，并有权要求乙方提供更多资质文件，乙方应当按甲方要求提供。如乙方不能提供，则甲方有权立即终止或中止本协议。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("4.4 ", textFont));
        phase.add(new Chunk("责任条款：乙方同意为其未及时的通知或更新其资质文件或其他证明信息承担全部责任，乙方保证其向甲方提供的全部资质文件真实、 合法、有效且不存在超过时效问题（即保证所有资质文件在整个合同履行期间都处于有效期内）。如因上述原因发生纠纷或被相关国家主管机关处罚，乙方应当独立承担全部责任，如给甲方（包括其合作伙伴、代理人、职员等）造成损失的，乙方同意赔偿其全部损失。", boldFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("4.5 ", textFont));
        phase.add(new Chunk("上述“资质文件”包含但不限于乙方的营业执照、身份证信息、银行卡信息、商标证书、品牌授权书、供货合同与发票、质检报告或商品质量合格证明、收款账户、通讯地址等。", boldFont));
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
        phase.add(new Chunk("5. 保证金", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("5.1 乙方同意向甲方缴纳一定金额的保证金，用于担保其对本协议的遵守与履行。乙方缴纳保证金的金额根据甲方资费标准及根据双方实际具体合作情况确定。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("5.2 保证金的付款方式为", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("5.2.1 乙方在本协议签订后的7个工作日内向甲方缴纳前述金额的保证金金额人民币", textFont));
        phase.add(new Chunk(mchtDepositTotalAmt, underlineFont));
        phase.add(new Chunk("元。乙方未及时缴纳的，甲方有权立即终止本协议。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("5.2.2 乙方在本协议签订后没有依约向甲方支付保证金。甲方有权从与乙方结算的销售货款中抵扣。抵扣金额达到上述保证金额时，停止抵扣。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("5.3 乙方发生业务变化及实际赔付率较高等其他可能给甲方造成不利后果及其他损害之情形时，甲方可以通知乙方调整保证金金额。乙方对保证金金额调整存在异议，可在收到通知之日起7个工作日内提出。乙方逾期未提出异议申请的，视为接受甲方调整。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("5.4 乙方保证金金额发生变动时，甲方有权从与乙方结算的货款中扣除相应金额充抵保证金。若货款不足以冲抵保证金的，乙方则应在收到甲方通知后5个工作日内补缴保证金，若乙方未补足的甲方有权中止本协议，直到乙方补足为止。在此期限，若给甲方造成损失的，乙方应当赔偿。逾期超过30天未能补足的，甲方有权终止本协议。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("5.5 保证金用途：", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("乙方同意并不可撤销地授权甲方：", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("5.5.1 合作期间该保证金的支配、使用权均归属于甲方，保证金专款专用，属履约保证金。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("5.5.2 当存在因乙方的侵权、违约、违法行为可能给甲方或消费者造成损失风险时，甲方有权从乙方保证金中直接扣除相应金额。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("5.5.3 乙方若解散、清算、破产时，甲方有权就乙方缴纳的保证金优先受偿。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("5.5.4 甲方有权根据本协议规定冻结、解冻或处置乙方的保证金。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("5.5.5 遇消费者投诉索赔时，甲方有权（但无义务）根据本协议规定、甲方商城有关规则及消费者提供的资料和证据,以其独立判断消费者索赔是否成立并确定赔偿金额。如因任一方提交的信息、数据不实导致任何责任,甲方均不承担。甲方无须对其做出的不完美的、有瑕疵的、不妥当的或者错误的赔付、理赔、支付、处置行为承担任何责任。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("5.5.6 乙方对保证金扣除存在异议，可在结算时提出。若未提出异议则视为同意甲方扣除。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("5.6 甲乙双方合作期满不再续签此协议时，且乙方若不存在任何违约或违法行为，也没有任何未处理完毕的投诉、纠纷，保证金将于本合同确定终止履行之日起需满三个月后方可无息退还；若乙方存在任何违反本协议义务或甲方商城规则的行为的，甲方有权从乙方保证金中扣除相应罚款、赔偿、违约金等款项，剩余部分甲方将于合同确认终止之日起需满三个月方可无息退还给乙方，如保证金不足以抵扣相应的罚款、赔偿、违约金等款项甲方有权要求乙方继续赔偿甲方损失。", textFont));
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
        phase.add(new Chunk("6	店铺要求", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("6.1 乙方申请店铺名、子域名等信息时，应遵守国家法律法规及相关规则，不得包含违法或涉嫌侵犯他人权利等的相关信息。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("6.2 乙方上传的店铺信息（包括店铺Logo、店铺名称、店铺公告、店铺介绍、店铺活动区域等）内容设置需真实、有效且与注册信息一致；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("6.3 出现以下任一情形时，甲方有权随时停止乙方该店铺的服务而无需承担任何责任，并有权要求乙方承当相应的违约责任并没收乙方保证金。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("6.3.1 乙方不满足入驻条件的；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("6.3.2 乙方提供虚假资质文件的；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("6.3.3 乙方商品价格、规格等信息标示错误，导致行政处罚、争议和纠纷的；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("6.3.4 乙方商品质量、标识不合格的，或者商品涉嫌走私、假冒伪劣、旧货、返修品的；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("6.3.5 未经甲方事先审核商品品牌，而私自上传某品牌商品的；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("6.3.6 乙方连续30日或累计45日未正常经营店铺的；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("6.3.7 乙方对用户进行恶意骚扰或者泄露用户隐私的；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("6.3.8 其他违反本协议约定或甲方规则的，或者其他甲方认为侵犯甲方或消费者权益的。", textFont));
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
        phase.add(new Chunk("7. 商品质量", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.1 商品的质量应符合下列要求：", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.1.1 商品不存在危及人身、财产安全的缺陷，符合保障人体健康和人身、财产安全的相关标准：", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.1.2 具备商品应当具备的使用性能，对商品存在使用性能的瑕疵已作出说明；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.1.3 符合在商品或者其包装上注明采用的商品标准，符合以广告、商品说明、实物样品等方式表明的质量状况。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.2 商品或者商品包装上的标识真实，并符合下列要求：", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.2.1 限期使用的商品，在显著位置标明生产日期和安全使用期或失效日期；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.2.2 商品成分说明或配料表，其中应标明主要成分及其含量；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.2.3 使用不当容易造成商品本身损坏或者可能危及人身、财产安全的商品，有警示标志；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.2.4 相关法律法规或行业惯例有关标识的其他适用要求。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.3 乙方保证商品不存在以下欺诈行为：", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.3.1 在商品中掺杂、掺假、以假充真、以次充好、以不合格商品冒充合格商品；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.3.2 销售国家明令淘汰的商品或者失效、变质的商品；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.3.3 伪造商品的产地，伪造或者冒用他人的厂名、厂址，伪造或者冒用认证标志、名优标志等质量标志；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.3.4 商品应当检验、检疫而未检验、检疫或者伪造检验、检疫结果；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.3.5 提供的商品存在严重缺陷，并且未立即告知甲方，也未采取防止危害的措施（包括但不限于召回）。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.4 乙方向消费者提供有关商品或者服务的信息真实，没有引人误解的虚假宣传，没有发布虚假广告、欺骗和误导消费者，使购买商品或者接受服务的消费者的合法权益受到损害的行为。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.5 乙方保证提供的商品：均为正牌商品，不存在假冒商品；没有侵犯他人专利权、著作权或者与著作权有关的权利或他人注册商标专用权或其他在先权利；来源合法、真实，不违反相关法律、法规。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.6 乙方提供的所有商品清洁、整齐、包装完好、适宜销售，送货时不得有任何包装破损或潮湿、变色。凡有保质期的商品，在送达消费者订单指定收货地时均应在保质期内。如存在下列情形，乙方应在商品页面的显著位置作出说明：", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.6.1 商品保质期大于六个月，且上架时距保质期届满之日已不足保质期的一半；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.6.2 商品保质期小于六个月，且上架时距保质期届满之日已不足保质期的三分之二。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("乙方违反以上规定应承担相应违约责任及赔偿甲方因此遭受的损失。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.7 货品、吊牌及商品外包装上，不能有任何之前在其他商场或任何渠道销售的标志、价格。如乙方对商品之前的价格标签未作处理或出现价格标签重叠、错粘及其他足以影响甲方商业信誉的任何标志及瑕疵进行处理，导致消费者对甲方售卖的商品存在任何负面性误解，造成甲方商业声誉受损或其他损害的，乙方应当为甲方消除影响并承担赔偿责任。如因此造成甲方损失或甲方对第三人承担任何法律责任，甲方承担责任后有权向乙方追偿。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.8 乙方提供的商品不符合本合同任一约定的，则视为不合格货品，因乙方提供不合格商品，消费者有权将该货品退回乙方，运费由乙方承担，造成甲方或消费者任何损害（包括人身和财产损失），乙方应当承担赔偿责任。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.9 质量抽检:", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.9.1 甲方有权根据商品品质控制需求或市场反馈自行或委托第三方质检机构进行不定期商品抽检（检测项目包括且不仅限于乙方销售商品的性能，质量，材料成分，是否符合国家法律法规要求等各方面），或要求乙方对甲方指定商品提供进货凭证，出厂检验报告或者第三方质检机构出具的检测报告等相关商品及批次的质量合格证明文件。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.9.2 如果乙方所销售商品抽检不合格或无法向甲方提供相关商品及批次质量合格的证明文件，甲方有权根据所公示的规则，并且依据问题的严重程度对乙方进行处罚及提出相应的限期整改要求。乙方必须配合限期整改及根据甲方的要求进行指定商品的第三方检测，并完全承担因此产生的所有费用。乙方拒绝执行的，甲方有权采取相关措施，包括且不仅限于停止乙方服务、终止本协议、要求乙方承担违约责任、损害赔偿等措施。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("7.9.3 如果因乙方商品质量问题而导致甲方产生损失（包括且不仅限于经济或声誉上的损失），甲方有权要求乙方赔偿因此而产生的所有费用，并保留进一步追究乙方相应责任的权利。", textFont));
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
        phase.add(new Chunk("8 商品交付", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("8.1 乙方应当根据醒购规则及时将商品交付消费者。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("8.2 乙方应按甲方要求（如有）对商品进行包装，在包装中应附带甲方要求的材料，包括但不限于发货单、合格证、说明书、含甲方logo的胶带、纸箱、纸袋、宣传单。且包装制作费用由乙方承担。乙方保证包装中不夹带任何侵犯甲方权益的资料、宣传单、第三方LOGO的资料、网站、店铺等。否则，甲方有权单方中止或终止本协议，同时因乙方前述行为给甲方带来的损失，乙方同意全部赔偿。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("8.3 乙方应当按甲方及消费者要求进行发货，若出现乙方迟延发货、虚假发货、商品缺货、未发货等情形，甲方有权根据醒购商城规则单方决定采取补救措施，包括但不限于赠送商城购物优惠券或积分给消费者、商城系统扣款、关闭交易、退款、转由第三方代发货。乙方同意甲方有权从保证金或相关订单货款中直接予以扣除由此产生的相关费用。“延迟发货”、“虚假发货”、“商品缺货”“未发货”等定义及相关处理规则详见甲方在醒购商城发布的规则。", textFont));
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
        phase.add(new Chunk("9. 货款结算", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("9.1 订单确认收货后即7日内无售后问题或售后问题已解决则订单完成：", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("9.1.1 乙方发货后，消费者收到货且未产生售后问题的，订单由消费者在商城用户中心确认收货（未确认收货且未产生售后问题的订单，自发货15日后系统默认为确认收货），消费者确认收货后，无售后问题的订单在确认收货后7日即可更新“完成”；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("9.1.2 若订单有产生售后问题，则待售后问题处理完毕方可结算：", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("若在确认收货后7日内处理完毕的则由系统默认为确认收货予以结算；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("若确认收货后7日内未处理完毕则订单结算顺延到下个结算周期，以此类推。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("9.2 可结算订单明细商品：", boldFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("9.2.1 在已完成的订单中，未退款、未退货的订单明细商品为可结算订单明细商品；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("9.2.2 换货商品结算以原始订单为依据，新的换货单不另行结算。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("9.3 结算单及对账方式：", boldFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("9.3.1 甲方系统在每周一自动对上星期（含周末）订单状态更新为完成的订单中属乙方的可结算订单明细商品进行生成结算汇总单，此结算汇总单简称为结算单。结算单内容包括但不限于：商品数量、订单实付金额、乙方直赔单金额、技术服务费金额、结算单金额。结算单金额为每个可结算订单明细商品的结算金额合计减乙方直赔单金额。可结算订单明细商品的结算金额=（商品金额-乙方优惠）*（1-技术服务费费率）；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("9.3.2 乙方应在商城上对结算单的信息及结算金额进行核对，若有异议应向甲方提出，否则视为无异议。乙方核对无误后由甲方财务进行核对，经双方确认账单金额无误后，甲方于15个工作日内向乙方支付结算单应结金额。", textFont));
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
        phase.add(new Chunk("10.  售后服务", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("10.1 乙方应根据相关法律规定、本协议约定以及醒购商城发布规则的规定，根据消费者的需求提供商品的“修理、更换、退货”等售后服务，服务期限以相关法律规定为准，若无相关法律规定的，则按本协议或醒购商城发布的规则执行。乙方应承担因上述售后服务而产生的一切费用（包括但不限于往返运费、退换货费），保障消费者的消费者权益。若因商品售后服务而产生的任何法律责任（包括但不限于任何赔偿或处罚）由乙方承担。若给甲方造成损失的，乙方还应承当相应的损害赔偿责任。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("10.2 消费者服务", boldFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("10.2.1	乙方应指派专人负责处理消费者服务问题，并需要将服务电话及工作邮件向甲方备案。乙方客服在线时间为每日早上9:00至晚上23:30，节假日不休。在收到消费者信息后应当于40秒内予以回复，并对所有消费者的回复率要达到100%。乙方的回复内容中应包含对于该问题的解释、明确的处理方案及事件说明，不能出现“正在查询/正在沟通”等无实质解决方案的回复。若乙方未按上述要求提供消费者服务，则视为违反消费者服务规则，具体以甲方的认定为准。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("10.2.2	消费者服务问题包括但不限于：商品咨询、退换货、取消订单、物流信息。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("10.2.3	甲方有权对乙方客服回复情况、回复态度等进行监督及检查，若乙方违反消费者服务规则的，甲方有权对消费者进行代金券补偿（补偿金额由乙方承担，乙方同意甲方有权从保证金或相关订单货款中直接予以扣除）。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("10.2.4	消费者发起投诉或提出售后服务时，乙方应在48小时内进行处理并回复消费者处理结果，若未回复则视为同意消费者的诉求，甲方有权按消费者的诉求进行处理。若消费者对乙方处理结果不满意，申请甲方介入。则乙方应在甲方介入后48小时内进行处理并回复甲方处理结果。并且，甲方有权根据醒购商城规则及本协议约定进行判定。乙方对甲方的判定有异议的，应该在甲方作出判定后48小时内提出，否则视为无异议，乙方应当执行甲方的判定。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("10.2.5	乙方应提高其服务质量尽量避免消费者退款退货，若退款退货频繁或影响甲方正常运营等情形，甲方有权单方面解除合作并扣除保证金，乙方货款在消费者退款、优惠券及违约金等费用抵扣完毕后予以结算。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("10.3 若经消费者投诉、品牌方投诉、甲方品控部门调查等途径，发现乙方存在下列情形之一：", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("10.3.1	乙方销售假冒伪劣商品或者过期商品；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("10.3.2	乙方提供非法服务；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("10.3.3	乙方通过微信、QQ、短信等方式截留醒购商城消费者。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("10.3.4	乙方冒用醒购商城名义，私下与消费者进行交易。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("10.3.5	乙方泄露消费者信息的。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("10.3.6	乙方通过电话、短信等骚扰、恐吓消费者的。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("10.3.7	乙方通过醒购平台向消费者发送第三方链接、二维码等信息，或发送色情、暴力、传销等违法信息。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("10.3.8	乙方违反本协议第7条“商品质量要求”且情节较为严重；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("10.3.9	其他与上述行为同类性质的行为，导致甲方认为不宜继续销售的情形。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("存在上述情形之一的商品简称为“严重问题商品”，甲方可按下述方式处理：对严重问题商品进行即时下架处理，并通知乙方提供相关证据，包括但不限于进货凭证、授权销售证明、商品证明等，乙方应立即提交。若乙方不能及时提供或经查投诉属实的，甲方有权采取下列一项或多项措施：", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("（1）解除本协议；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("（2）要求乙方支付乙方通过醒购销售的严重问题商品总金额的三倍作为违约金，若乙方拒绝支付违约金，则甲方有权以乙方账户内的货款抵扣违约金；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("（3）若违约金不足以支付甲方或其他第三方损失，要求乙方赔偿损失；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("（4）扣除乙方交纳的保证金。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("10.4 当因乙方提供的商品存在瑕疵危及人身财产安全，或因涉及重大安全问题危及甲方名誉受损，或因法律诉讼或甲方认为应该召回，甲方有权要求乙方召回有关商品，乙方应按甲方要求完成对召回商品的退、换、维修，并按照甲方的要求对甲方或甲方指定的其他网站就有关商品的召回信息进行公布，并承担由此产生的所有费用。乙方还应赔偿甲方及/或消费者由此产生的任何损失。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("10.5 乙方同意遵守本协议及醒购商城发布的规则。", textFont));
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
        phase.add(new Chunk("11. 知识产权", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("11.1 与商品相关的专利、商标、名称、特有标识、装潢、技术秘密等均属于其权利人所有。乙方应保证其已按照法律规定或者合同约定获得了销售商品及授权在醒购上销售商品的权利或许可。", boldFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("11.2 乙方保证有权授权并许可甲方为在本协议目的使用相关知识产权，该授权许可是免费的。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("11.3 乙方知悉并同意：甲方或其关联方拥有、使用、许可、控制的或者甲方或其关联方对之享有其他权利的一切知识产权，包括但不限于全部商标、著作权、名称、标识、标志、微信公众号、域名、艺术作品、人物形象、标志、专利，例如“醒购”、“醒购商城”、“醒购及图形”、“醒购app图标”。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("11.4 乙方同意并保证，其任一级代表、代理、受托人、高级管理人员或其他职工及其自身，不得在中国之境内外注册、使用与甲方及其关联方拥有、使用、许可或控制的商标、标识、标志、微信公众号、域名、艺术作品、人物形象等相同或近似的商标，或侵犯甲方或其关联方享有的一切知识产权、工业产权和专有权利，或妨碍甲方或其关联方独自全部拥有或保留前述权利。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("11.5 乙方同意并保证，若其注册与甲方及甲方关联方相同或近似的商标、标识、标志、微信公众号、域名等用于开展与甲方及甲方关联方实质相同或近似的业务，则应当在甲方或甲方关联方提出相关要求时，无偿将相关商标、标识、标志、微信公众号、域名转让给甲方或甲方关联方。同时，若乙方的行为侵犯了甲方利益，甲方有权暂停支付乙方在甲方的所有应付账款、解除本协议并要求乙方赔偿由此给甲方造成的损失。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("11.6 乙方同意并保证其任一级代表、代理、受托人、高级管理人员或其他职工均遵守本合同的约定，受到本合同的拘束，如同该人被指定为“乙方”一样。", textFont));
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
        phase.add(new Chunk("12. 保密条款：", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("12.1 本协议所称商业秘密包括但不限于本协议、任何补充协议所述内容及在合作过程中涉及的其他秘密信息。任何一方未经商业秘密提供方同意，均不得将该信息向任何第三方披露、传播、编辑或展示。协议方承诺，本协议终止后仍承担此条款下的保密义务，保密期将另行持续三年。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("12.2 因对方书面同意以及国家行政、司法强制行为而披露商业秘密的，披露方不承担责任；该商业秘密已为公众所知悉的，披露方不承担责任。", textFont));
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
        phase.add(new Chunk("13 权利与义务", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.1 甲方权利与义务", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.1.1	甲方有义务向乙方提供本协议约定的技术服务及其他相关服务，维护平台正常运行。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.1.2	甲方有权根据本协议的约定向乙方收取相关费用，并且有权先从结算货款中直接扣除相关费用。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.1.3	甲方有权（但非义务）对乙方拟在醒购发布的内容、信息进行形式审核，必要时甲方有权依据法律法规、政策规定及平台规则拒绝发布(如拟发布涉嫌黄、赌、毒及其他违法或者违反公序良俗的信息)。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.1.4	甲方有权根据市场实际需求适度调整平台技术服务费费率，并在费率调整后在醒购公示。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.1.5	如消费者就乙方商品或服务向醒购投诉，甲方经核实后，有权（但非义务）要求乙方配合解决该投诉，在该投诉未解决之前甲方有权拒绝向乙方支付涉及投诉的相关款项，待投诉解决后再行支付。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.1.6	甲方有权升级、更新醒购系统，如有变更，甲方将在醒购公示。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.1.7	甲方有权根据业务调整情况将本协议项下的全部权利义务一并转移给其关联公司,此种情况将会提前在醒购公示。乙方承诺对此不持有异议。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.1.8	甲方有权根据乙方的商品销量、价格竞争力、服务水平等单方决定乙方商品售卖期和展示位置。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.1.9	本协议及其附件、甲方通过醒购发布的相关规则确定的其他权利。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.2 乙方权利与义务", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.2.1	乙方具有履行本协议所需的所有必要资质和授权，其提供的资质和授权证明文件真实、有效，且如相关资质资料有变更，乙方应及时提供最新资料至甲方；如乙方提供虚假，失效文件或提供文件不及时，乙方应承担由此产生的法律责任，且甲方有权停止货款结算，解除本协议，并要求乙方赔偿由此给甲方造成的损失。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.2.2	乙方保证提供的商品：（1）来源正当合法，均为正品；(2)是乙方合法且排他拥有的；(3)不会导致任何第三方主张侵权或其他妨碍乙方销售的行为。乙方对上述保证承担完全责任，如因商品不符合上述保证而导致甲方遭受任何索偿、责任追究或损失，均由乙方承担。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.2.3	乙方应对其提供的商品的售后和质量负完全责任，并履行本协议规定的义务。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.2.4	乙方就商品提供给甲方的商品说明、介绍、图片、价格等信息资料是真实有效并能反映商品特性的，不存在虚假、伪造或侵犯第三方权益的内容；此外，乙方保证甲方在其商城使用上述信息资料不会侵犯第三方的权益。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.2.5	乙方承诺未隐瞒任何其他信息以致足以影响甲方签订及履行本协议。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.2.6	乙方将按照不低于《中华人民共和国商品质量法》、《中华人民共和国消费者权益保护法》及其他法规、部门规章和国家强制性标准规定的要求，出售商品并提供“三包”等售后服务。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.2.7	乙方在醒购出售商品，有义务按照消费者实际支付的款项为消费者开具发票。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.2.8	乙方承诺不得抄袭、模仿甲方商业模式、不得以任何方式、向任何第三方泄露/出售甲方商城用户信息，不得采取任何类似手段侵害甲方利益；若乙方违反上述承诺，则甲方有权扣除乙方保证金、单方解除本协议，终止与乙方的合作并追究乙方的违约。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.2.9	乙方不得以任何手段、利用甲方商城规则漏洞或系统漏洞，通过虚假交易、关联交易等方式，套取甲方商城红包、商城补贴；若乙方违反上述承诺，则甲方有权从乙方保证金以及未结货款中扣除相当于红包或补贴金额十倍的款项作为违约金，同时甲方有权单方解除本协议，终止与乙方的合作，乙方行为构成犯罪的，甲方有权向公安部门报案并进一步追究乙方刑事责任。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.2.10 对于被甲方开除或主动辞退的员工（“被辞退员工”）以及甲方在职员工，乙方承诺不得以合伙、合作、入股、咨询顾问、雇佣等任何形式与该等被辞退员工进行合作。若乙方违反上述承诺，则甲方有权单方解除本协议，终止与乙方的合作并追究乙方的违约及侵权责任。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.2.11 对于从甲方正常离职的员工，若乙方以合伙、合作、入股、咨询顾问、雇佣等任何形式与该等正常离职员工进行合作，则应当自合作开始之日起三日内向甲方进行报备。若乙方违反上述承诺，则甲方有权单方解除本协议，终止与乙方的合作并追究乙方的违约及侵权责任。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("13.2.12 乙方应按照甲方的规定，妥善保管、使用甲方提供的相关账户（包括但不限于用户名、原始密码信息）及/或乙方自行修改的密码，并确保使用其该等账户的主体均为乙方或乙方授权的人员；除非适用的法律法规另有明确规定或本协议另有明确的约定，乙方不得以任何形式泄露，擅自转让、披露或授权他人使用该账户。否则，由此产生的任何争议、纠纷、处罚、诉讼、仲裁、投诉、索赔等均由乙方自行负责处理并承担全部法律责任（包括但不限于赔偿由此给顾客、甲方及/或任何他方造成的全部损失），并且甲方有权立即终止乙方的账户的权利，及/或立即终止与乙方的部分或全部合作。", textFont));
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
        phase.add(new Chunk("14. 有限责任", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("14.1 乙方了解并同意，甲方及其关联公司并非司法机构，仅能以普通或非专业人员的知识水平标准对乙方提交的证据材料进行鉴别，甲方及其关联公司对交易纠纷的调处完全是基于乙方的委托，甲方及其关联公司无法保证交易纠纷处理结果符合乙方的期望，也不对交易纠纷调处结果及保证金赔付决定承担任何责任。乙方应保证其提交的证据材料的真实性、合法性，并承担其或消费者提供的信息、数据不实的风险和责任。如乙方因此遭受损失，乙方同意自行向受益人索赔。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("14.2 甲方对乙方使用甲方商城服务所涉的技术和信息的有效性，准确性，正确性，可靠性，质量，稳定，完整和及时性均不作承诺和保证。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("14.3 不论在何种情况下，甲方均不对由于Internet连接故障，电脑，通讯或其他系统的故障，电力故障，罢工，劳动争议，暴乱，起义，骚乱，生产力或生产资料不足，火灾，洪水，风暴，爆炸，不可抗力，战争，政府行为，国际、国内法院的命令或第三方的不作为而造成的不能服务或延迟服务承担责任。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("14.4 甲方将对乙方提供的相关资质进行审查，但是乙方了解并同意，鉴于甲方平台并非专业机构，甲方对乙方提交相关资质文件仅能以普通人员的知识水平进行鉴别审查，甲方无义务也没有能力保证乙方提交证明文件的真实性，因此甲方对信赖乙方资质文件而造成的任何损失不承担责任。", textFont));
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
        phase.add(new Chunk("15	协议期限、 变更、解除和终止", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("15.1 协议期限与续签", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("15.1.1	本协议的期限为自", textFont));
        phase.add(new Chunk(contractBeginDate, underlineFont));
        phase.add(new Chunk("至", textFont));
        phase.add(new Chunk(contractEndDate, underlineFont));
        phase.add(new Chunk("止，但保密、违约责任及售后服务义务及商品质量保证责任在协议终止/解除后将继续有效。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("15.1.2	合作期限到期后，若双方均未提出另行签订合作协议，则合作期限自动延续一年。合同期满前，如有一方不再续签，应提前90日以书面形式通知对方，否则应承担由此给对方造成的损失。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("15.2 协议变更", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("甲方若变更协议内容，则按本协议1.4条的约定进行处理。若乙方需变更本协议内容，包括但不限于品牌信息变更、店铺名称变更，则应向甲方提出申请，经甲方同意后方可变更。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("15.3 协议终止", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("15.3.1	任何一方提前解除合同，应提前30天书面通知另一方，经双方协商一致后可解除本协议。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("15.3.2	甲方单方解除权：如乙方违反甲方的任何规则或本协议中的任何承诺或保证，包括但不限于本协议项下的任何约定，甲方都有权立刻终止本协议，且按有关规则对乙方进行违约处理。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("15.3.3	乙方在超过90天的时间内未以甲方提供的服务账户及密码登录醒购商城的，甲方有权终止本协议。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("15.3.4	如非因甲方的原因，乙方未能按本协议及附件之规定，按期全额支付有关服务费用或活动费用，且在甲方规定的时限内仍未支付，甲方有权部分或全部中止或终止本协议。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("15.3.5	本协议规定的其他协议终止条件发生或实现，导致本协议终止。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("15.4 协议终止或解除后后续事项的处理", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("15.4.1	自本协议终止或解除之日起，甲方将关闭乙方在甲方的账户权限，并对乙方商品全部下架，乙方将无法再通过该账户进行任何形式的操作且甲方前端APP不再显示任何乙方商品信息。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("15.4.2	本协议终止或解除后，甲方有权保留乙方的注册信息及交易行为记录等数据，但甲方没有为乙方保留这些数据的义务，亦不承担在协议终止后向乙方或第三方提供任何数据信息的义务，也不就协议终止向乙方或任何第三方承担责任，但法律另有规定的除外。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("15.4.3	本协议终止或解除并不免除乙方依据本协议应向消费者承担的售后服务及商品保证责任，乙方仍应履行售后服务义务及商品质量保证责任；如在本协议终止后，因乙方商品质量问题或售后服务问题而导致甲方或其他第三方人身或财产损失的，乙方仍应独立承担全部责任。", textFont));
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
        phase.add(new Chunk("16. 违约责任", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("16.1 协议履行期间，如一方违反本协议及附件中任何约定而致使另一方利益遭受损失，另一方有权在违约行为发生后以书面形式告知违约方要求其赔偿。如因违约方违反本合同致使守约方发生任何费用、支出、责任或损失，违约方应就此对守约方承担责任并进行赔偿，保障守约方不受损害。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("16.2 乙方不得擅自终止本协议或者将本协议项下权利和义务转让给任何第三方，否则，甲方有权解除协议，并有权要求乙方就由此给甲方造成的损失承担赔偿责任。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("16.3 若因乙方提供的商品素材问题、商品问题或因发错货、超卖、退货处理不及时等售后服务问题导致甲方被第三方投诉、起诉的，甲方有权选择与第三方和解、调解或诉讼，乙方应承担由此支出的一切费用并承担甲方因此遭受的一切损失；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("16.4 因甲方宣传乙方商品，引起的第三方维权、投诉、诉讼等，造成的任何损失由乙方负责，同时甲方有权扣除乙方全部保证金，暂停支付乙方销售款项，要求乙方赔偿由此给甲方造成的损失。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("16.5 因乙方原因致使价格或活动设置错误的，则由乙方承担一切损失和责任，甲方进行先行赔付的，乙方应在3个工作日内赔偿给甲方，否则甲方有权扣除乙方全部保证金或暂停支付乙方销售款项。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("16.6 甲方有权根据乙方任何违约情形（包括但不仅限于乙方违反陈述与保证、无故停止经营、乙方单方终止协议、违反消费者服务规则等）在要求乙方承担合同约定责任外扣除部分或全部保证金。甲方扣划保证金后，乙方应在接到甲方通知后三日内补齐，否则，甲方有权在乙方结算款项中直接扣划。保证金的扣除并不免除甲方要求乙方按合同约定承担的违约责任。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("16.7 乙方之代理人、受托人、受雇人、股权所有人之一切行为均代表乙方，其违反本协议之行为视为乙方之行为，乙方应对此承担连带法律责任。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("16.8 乙方违反本协议约定而需向甲方支付的任何违约金、赔偿金及其他所有费用，应在甲方通知后5日内一次性支付给甲方，否则，甲方有权扣除保证金或在结算款项中直接扣除，并向乙方追索不足部分。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("16.9 由于乙方发生上述违约行为导致乙方应赔偿甲方损失或支付罚金或双方终止合作，乙方仍需继续执行售后服务并对已售出的商品承担一切责任。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("16.10 本协议所称损失，除守约方遭受的直接损失与间接损失外，还包括但不限于守约方为减少损失、防止损失扩大、固定证据或者追究违约方的相关法律责任所支出的诉讼费用、公证费用、律师费用、差旅费、鉴定费等一切有关费用。", textFont));
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
        phase.add(new Chunk("17. 不可抗力", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("由于不可抗力事件导致一方不能及时履行或者不能履行该方在本协议下的任何义务（付款义务除外）的，不构成违约。但是受不可抗力事件影响的一方应立即把不可抗力事件的性质和程度通知对方，并积极采取相应补救措施，以最大程度地减少和避免损失。", textFont));
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
        phase.add(new Chunk("18. 甲方反商业贿赂条款", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("18.1 双方严格遵守法律法规有关禁止商业贿赂行为规定，坚决拒绝商业贿赂、行贿及其他不正当商业行为的馈赠。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("18.2 本协议所指的商业贿赂是指乙方为获取与甲方的合作及合作的利益，乙方或其单位工作人员给予甲方员工的一切精神及物质上直接或间接的馈赠，如现金、回扣、娱乐、旅游等。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("18.3 甲方的任何员工、部门不得向乙方索取或收受金钱、物品及任何形式的馈赠。如乙方发现甲方商城员工存在上述行为，乙方应及时向甲方商城举报。（举报邮箱：hr@jfbuy.com；电话：0592-5929855）", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("18.4 乙方或乙方工作人员不得以乙方或个人名义向甲方任何员工及其亲属、关联方私下直接或间接赠送礼金、物品、有价证券、股份或采取其他变相手段提供不正当利益，否则均视为侵害甲方利益的行为。不正当利益包括但不限于现金、支票、信用礼品卡、样品、或其他商品、娱乐票券、会员卡、货币或货物形式的回扣、回佣、就业或置业、乙方付款的旅游、宴请及个人服务等。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("18.5 若甲方员工要求乙方给予其任何形式的不正当利益，乙方应及时投诉。并提供相关证据给甲方，经甲方相关人员查实后作出处理，并为乙方保密。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("18.6 若乙方贿赂甲方任何员工，以图获取任何不正当商业利益或更特殊的商业待遇或不配合甲方查处其员工的受贿行为的，甲方将立即开除涉事员工，永久停止与乙方的一切合作，并依法对乙方采取诸如冻结所有应付账款的措施，同时乙方应向甲方支付人民币伍万元整作为违约金。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("18.7 情节严重、造成重大经济损失的，甲方将依法移交司法机关处理。", textFont));
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
        phase.add(new Chunk("19. 关联关系条款", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("19.1 甲方有权将具备关联关系的乙方账户进行统一管理，形成关联圈。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("19.2 甲方有权对关联圈内的乙方账户进行统一管理，包括但不限于统一增加或扣减信誉值，统一扣罚保证金/账户资金，统一终止商城服务，统一终止/中止合作等。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("19.3 若关联圈中的任一账户，因违反相关法律法规、本协议、醒购商城规则而遭到消费者投诉或索赔，甲方有权无需通知立即暂停该账户的交易权限。经判定消费者投诉或索赔事由成立的，甲方有权依据本协议或醒购商城规则对违规的乙方进行违约处理，并有权立即直接扣划该账户及其关联圈内其他账户缴纳的保证金、在醒购的账户资金或其他款项。乙方应赔偿甲方因此遭受的包括赔偿金、诉讼费、律师费在内的一切损失。", textFont));
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
        phase.add(new Chunk("20. 其他约定", firstTitleFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("20.1 条款的独立性", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("如果根据适用的法律认定本协议中的任何条款或者任何条款中的任何部分无效、违法或者不具有可执行性，这种无效、违法或者不具有可执行性不影响本协议中的任何其它条款或者这些条款中的任何其它部分的效力。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("20.2 争议解决", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("对于因本协议而产生的或者与本协议有关的争议，双方均应努力通过友好协商的方式进行解决。协商不成，任何一方均可向甲方住所地人民法院提起诉讼。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("20.3 通知", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("20.3.1	本协议项下要求或允许给予对方的所有“公示”、“通知”、“公示并通知”、“同意”、“批准”、“许可”、“备案”、提出异议应为书面形式。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("20.3.2	除本协议另有约定外，甲方做出前述行为的书面方式应为：通过在醒购公示，或向乙方发送手机短信、传真、电子邮件，或专人派送、特快专递和/或挂号信，乙方的联系方式（联系电话、电子邮件、通讯地址）以本协议约定、乙方在甲方商城线上填写的信息为准；", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("20.3.3	除本协议另有约定外，乙方做出前述行为的书面方式应为通过乙方在甲方商城填写、登记的或本协议约定的联系人电话、电子邮箱，向甲方指定人员的电子邮箱发送电子信息或向甲方办公地址专人派送、特快专递和/或挂号信，或通过醒购系统通知甲方。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("20.3.4	任何一方联系方式发生变更的，应通过前述各自可以采取的方式发出通知来改变其以后所有接收通知的联系方式，否则需承担无法送达的后果。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("20.3.5	除非另有明确规定，通知若以在醒购公示形式发出，则送达时间应以醒购商城公示之时为准；若向乙方发送电子信息的，则送达时间应以电子信息到达乙方账号之时为准；若以传真形式发出，则送达时间应以传真传送记录所显示之时间为准；若以电子邮件形式发出，则送达时间应以邮件发出之时为准；若以专人派送、特快专递或挂号信形式发出，则送达时间为发送之日起3个工作日为准。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("20.4 在打印或填写过程中，乙方不得更改或删补本协议中的任何条款，更动后的协议将被视为无效。", textFont));
        phase.add(Chunk.NEWLINE); //换行
        phase.add(new Chunk("20.5 本协议一式两份，双方各执一份，具有同等的法律效力，经双方盖章后生效。（以下无正文）", textFont));
        phase.add(Chunk.NEWLINE); //换行
        paragraph.add(phase);
        doc.add(paragraph);

        paragraph = new Paragraph();
        paragraph.setLeading(leading);
        paragraph.setSpacingBefore(80);
        doc.add(paragraph);

        // 创建一个有2列的表格
        table = new PdfPTable(2);
        table.setTotalWidth(new float[]{ 260, 260 }); //设置列宽
        table.setLockedWidth(true); //锁定列宽
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell = new PdfPCell(new Phrase("甲方签章：", textFont));
        cell.setMinimumHeight(30); //设置单元格高度
        cell.setBorderWidth(0);
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("乙方签章：", textFont));
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
        
        cell = new PdfPCell(new Phrase("联系方式：4008088227", textFont));
        cell.setBorderWidth(0);
        cell.setMinimumHeight(30); //设置单元格高度
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("联系方式：", textFont));
        cell.setBorderWidth(0);
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("电子邮件：zs@xgbuy.cc" , textFont));
        cell.setBorderWidth(0);
        cell.setMinimumHeight(30); //设置单元格高度
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("电子邮件：", textFont));
        cell.setBorderWidth(0);
        cell.setHorizontalAlignment(Cell.ALIGN_LEFT); //设置水平居中
        cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); //设置垂直居中
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("签订日期：" + contractBeginDate, textFont));
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
        //return outPath;
        return filePath;
    }

}
