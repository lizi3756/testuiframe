package com.frame.utils;



import com.frame.element.ElementBeans.ByType;
import com.frame.element.ElementBeans;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 */
public class XmlReadUtil {
	private static Logger log = Logger.getLogger(com.frame.utils.XmlReadUtil.class);

	// 获取定位方式,//{"username":elementbeans,"password":elementbeans,"loginBtn":elementbeans}
	public HashMap<String, ElementBeans> readXMLDocument(String path, String pageName) {
		// log.info("----------开始解析UILibrary.xml对象库-----------");
		// log.info("开始读取："+pageName+"页信息");
		HashMap<String, ElementBeans> locatorMap = new HashMap<String, ElementBeans>();
		locatorMap.clear();
		try {
			File file = new File(path);
			if (!file.exists()) {
				throw new IOException("Can't find " + path);
			}
			SAXReader reader = new SAXReader();
			Document document = reader.read(file);
			Element root = document.getRootElement();
			for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
				Element page = (Element) i.next();
				if (page.attribute(0).getValue().equalsIgnoreCase(pageName)) {
					// log.info("成功读取页名:" + pageName);
					for (Iterator<?> l = page.elementIterator(); l.hasNext();) {
						String type = null;
						String timeOut = "0";
						String value = null;
						String elementName = null;
						Element element = (Element) l.next();
						// 获取元素名
						elementName = element.getText();
						// log.info("开始读取"+locatorName+"定位信息");
						for (Iterator<?> j = element.attributeIterator(); j.hasNext();) {
							Attribute attribute = (Attribute) j.next();
							if (attribute.getName().equals("type")) {
								type = attribute.getValue();
								// log.info("读取定位方式： " + type);
							} else if (attribute.getName().equals("timeout")) {
								timeOut = attribute.getValue();
								// log.info("读取元素等待时间 ：" + timeOut);
							} else if (attribute.getName().equals("value")) {
								value = attribute.getValue();
								// log.info("读取定位内容：" + value);
							}
						}
						// trim()去除字符串前后空格
						ElementBeans temp = new ElementBeans(value.trim(), Integer.parseInt(timeOut), getByType(type),
								elementName.trim());
						// log.info("成功读取 " + locatorName+"元素信息！");
						locatorMap.put(elementName.trim(), temp);
					}
					continue;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// log.info("----------解析UILibrary.xml对象库完毕-----------\n");
		return locatorMap;
	}

	/**
	 * @param type
	 */
	public static ByType getByType(String type) {
		ByType byType = ByType.xpath;
		if (type == null || type.equalsIgnoreCase("xpath")) {
			byType = ByType.xpath;
		} else if (type.equalsIgnoreCase("id")) {
			byType = ByType.id;
		} else if (type.equalsIgnoreCase("linkText")) {
			byType = ByType.linkText;
		} else if (type.equalsIgnoreCase("name")) {
			byType = ByType.name;
		} else if (type.equalsIgnoreCase("className")) {
			byType = ByType.className;
		} else if (type.equalsIgnoreCase("cssSelector")) {
			byType = ByType.cssSelector;
		} else if (type.equalsIgnoreCase("partialLinkText")) {
			byType = ByType.partialLinkText;
		} else if (type.equalsIgnoreCase("tagName")) {
			byType = ByType.tagName;
		}
		return byType;
	}
}
