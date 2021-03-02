package com.frame.utils;


import com.frame.element.ElementBeans.ByType;
import com.frame.element.ElementBeans;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 */
public class YamlReadUtil {
	public static void main(String args[]) throws FileNotFoundException,UnsupportedEncodingException {
		String path = "src/main/resources/pageObjectFiles/yml/UILibrary.yml";
		YamlReadUtil yamlReadUtil = new YamlReadUtil();
		yamlReadUtil.getLocatorMap(path, "com.testfan.ui.auto.pageObject.BingPage");
		//yamlReadUtil.getYamlPageUrl(path, "com.testfan.ui.auto.pageObject.BingPage");
	}

	/**
	 *
	 * @param path     对象库文件地址
	 * @param pageName pageName 页面名字
	 * @return 返回locator 哈希表 elementName:locator
	 * @throws UnsupportedEncodingException
	 *
	 * {"loginBtn":new ElementBeans(),"username":new ElementBeans(),"password":new ElementBeans()}
	 */
	public HashMap<String, ElementBeans> getLocatorMap(String path, String pageName)
			throws FileNotFoundException, UnsupportedEncodingException {
		HashMap<String, ElementBeans> locatorHashMap = new HashMap<>();
		Yaml yaml = new Yaml();
		Object yamlObject = yaml.load(new FileReader(path));
		Map yamlMap = (Map) yamlObject;
		ArrayList<HashMap<String, Object>> pages = (ArrayList<HashMap<String, Object>>) yamlMap.get("pages");
		for (int i = 0; i < pages.size(); i++)// 遍历Page节点
		{
			HashMap<String, Object> pageNode = pages.get(i);// 获取page节点
			HashMap<String, Object> pageElement = (HashMap<String, Object>) pageNode.get("page");
			if (pageElement.get("pageName").toString().equalsIgnoreCase(pageName))// 判断是否需要获取的Page节点
			{
//                System.out.println(pageElement.get("desc"));
				List<HashMap<String, Object>> locators = (List<HashMap<String, Object>>) pageElement.get("locators");// 获取locators列表
				for (int j = 0; j < locators.size(); j++)// 遍历locators列表
				{
					HashMap<String, Object> locatorNode = locators.get(j);
					String type = locatorNode.get("type").toString();
					String timeOut = locatorNode.get("timeout").toString();
					String value = locatorNode.get("value").toString();
					String elementName = locatorNode.get("name").toString();
					// trim()去除字符串前后空格
					ElementBeans temp = new ElementBeans(value.trim(), Integer.parseInt(timeOut), getByType(type),
							elementName.trim());
					locatorHashMap.put(locatorNode.get("name").toString(), temp);
				}

			} else {
				continue;
			}
//            System.out.println(pageObjet);
		}
//        System.out.println(locatorHashMap.get("登录").getLocalorName());
		return locatorHashMap;

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
