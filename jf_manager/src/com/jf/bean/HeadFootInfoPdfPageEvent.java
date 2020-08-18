package com.jf.bean;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.*;

import java.io.IOException;

/**
 * 设置打印单据的头部和底部信息
 *
 * @author
 *
 */
public class HeadFootInfoPdfPageEvent extends PdfPageEventHelper {
        //自定义传参数
        public PdfTemplate tpl;
        BaseFont bfChinese;

        //无参构造方法
        public HeadFootInfoPdfPageEvent() {
            super();
            try {
                bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void onOpenDocument(PdfWriter writer, Document document) {
            tpl = writer.getDirectContent().createTemplate(100, 20);
        }

        //实现页眉和页脚的方法
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                PdfContentByte headAndFootPdfContent = writer.getDirectContent();
                headAndFootPdfContent.saveState();
                headAndFootPdfContent.beginText();
                //设置中文
                headAndFootPdfContent.setFontAndSize(bfChinese, 10);
                //文档页脚信息设置
                float y = document.bottom(-10);
                //添加页码
                //页脚信息中间
                headAndFootPdfContent.showTextAligned(PdfContentByte.ALIGN_CENTER, "第 " + document.getPageNumber(),
                        (document.right() + document.left()) / 2 - 20, y, 0);
                //在每页结束的时候把“第x页”信息写道模版指定位置  
                headAndFootPdfContent.addTemplate(tpl, (document.right() + document.left()) / 2 - 13, y);//定位“y页” 在具体的页面调试时候需要更改这xy的坐标
                headAndFootPdfContent.endText();
                headAndFootPdfContent.restoreState();
            } catch (Exception de) {
                de.printStackTrace();
            }
        }

        public void onCloseDocument(PdfWriter writer, Document document) {
            //关闭document的时候获取总页数，并把总页数按模版写道之前预留的位置  
            tpl.beginText();
            tpl.setFontAndSize(bfChinese, 10);
            tpl.showText(" 页    共 " + Integer.toString(writer.getPageNumber() - 1) + " 页");
            tpl.endText();
            tpl.closePath();//sanityCheck();  
        }
    }