package com.ui.pageObject;


import com.frame.driver.DriverBase;
import com.frame.element.ElementBeans;
import com.frame.utils.XmlReadUtil;
import com.frame.utils.YamlReadUtil;

import java.util.HashMap;

/**
 * @author
 *
 */
public class BasePage {

	public DriverBase driver;
	public String platform;
	public String pagePath="src/main/resources/pageObjectFiles/myCarList.xml";
	HashMap<String, ElementBeans> locatorMap;//{"username":elementbeans,"password":elementbeans,"loginBtn":elementbeans}

	public BasePage(DriverBase driver) {
		this.driver = driver;
		this.getLocatorMap();
	}

	public void getLocatorMap() {
		XmlReadUtil xmlReadUtil = new XmlReadUtil();
		YamlReadUtil yamlReadUtil = new YamlReadUtil();
		try {

			if (pagePath.contains(".xml")) {
				locatorMap = xmlReadUtil.readXMLDocument(pagePath, this.getClass().getCanonicalName());
			} else if (pagePath.contains(".yml")) {
				locatorMap = yamlReadUtil.getLocatorMap(pagePath, this.getClass().getCanonicalName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {

	}

}
