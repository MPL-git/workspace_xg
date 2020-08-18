package com.jf.common.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;


public class JdomParseXmlUtils {
	
	
	public static List<Element> getWXPayResultElement(String xml){
		List<Element> list = null;
		try { 
			StringReader read = new StringReader(xml);
			
			InputSource source = new InputSource(read);

			SAXBuilder sb = new SAXBuilder();
			
			Document doc;
			doc = (Document) sb.build(source);

			Element root = doc.getRootElement();
			list = root.getChildren();

		} catch (JDOMException e) {
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
