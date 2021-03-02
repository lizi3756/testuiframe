package com.frame.poGenerate;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 *
 * @author 作者
 *
 * @version 创建时间：2020年2月14日 下午4:01:46
 *
 */
public class GenerateAutoCodeForXml {
	private static Logger log = Logger.getLogger(GenerateAutoCodeForXml.class);
	 static String path =
	 "src/main/resources/pageObjectFiles/myCarList.xml";
//	static String path = ExcelDataUtil.class.getClassLoader().getResource("pageObjectFiles/xml/UILibrary0216.xml")
//			.getFile();

	public static void autoCode() throws Exception {
		File file = new File(path);
		if (!file.exists()) {
			throw new IOException("Can't find " + path);
		}
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);
		// 对象库xml文件根节点
		Element root = document.getRootElement();
		// 遍历根节点下的第一个节点（page节点）
		for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
			Element page = (Element) i.next();
			// 获取page节点的name属性值，com.testfan.ui.auto.xml.pageObject.HomePage
			String pageName = page.attribute(0).getValue();
			System.out.println(pageName);
			// 将pageName存储为数组
			String[] pageNameArray = pageName.split("\\.");
			System.out.println(pageNameArray);
			System.out.println(pageNameArray[0]);
			// 获取要写入的page所属的类名
			String pageClassName = pageNameArray[pageNameArray.length - 1].toString();
			// 获取对象库包名
			String packageName = pageNameArray[0];
			for (int k = 1; k < pageNameArray.length - 1; k++) {
				packageName = packageName + "." + pageNameArray[k];
			}
			//com.testfan.ui.auto.xml.pageObject
			String filePath = packageName.replaceAll("\\.", "/");//com/testfan/ui/auto/xml/pageObject
			// --自动编写对象库代码（XXPage.java）开始--
			StringBuffer sb = new StringBuffer("package " + packageName + ";\n");
			sb.append("import java.io.IOException;\n");
			sb.append("import org.apache.log4j.Logger;\n");
			sb.append("import java.io.InputStream;\n");
			sb.append("import com.frame.driver.DriverBase;\n");
			sb.append("import com.frame.element.ElementBeans;\n");
			sb.append("//" + page.attribute(2).getValue() + "_对象库类\n");
			sb.append("public class " + pageClassName + " extends BasePage {\n");
			sb.append("private static Logger log = Logger.getLogger("+pageClassName+".class);");
			sb.append("//用于eclipse工程内运行查找对象库文件路径\n");
			sb.append(" public   " + pageClassName + "(DriverBase driver) {\n");
			sb.append("//工程内读取对象库文件\n	");
			sb.append("super(driver);\n");
			sb.append("\n}");
			// sb.append("\n private String
			// path=PageObjectAutoCode.class.getClassLoader().getResource(\"net/hk515/pageObjectConfig/UILibrary.xml\").getPath();");
			// 遍历Page节点下的Locator节点
			for (Iterator<?> j = page.elementIterator(); j.hasNext();) {
				// 获取locaror节点
				Element locator = (Element) j.next();
				String locatorName = locator.getTextTrim();
				if (!(locatorName.indexOf("ios_")==0)) {
					if (locator.attributeCount() > 3) {
						sb.append("\n/***\n" + "* " + locator.attribute(3).getValue() + "\n" + "* @return\n"
								+ "* @throws IOException\n" + "*/\n");
					} else {
						sb.append("\n");
					}
					sb.append("public  ElementBeans " + locatorName + "() throws IOException\n {\n");
					// sb.append(" setXmlObjectPath(path);\n");
					sb.append("   if(locatorMap.containsKey(\""+locatorName+"\")||locatorMap.containsKey(\"ios_"+locatorName+"\")){\n");
					sb.append("   ElementBeans elementBeans=locatorMap.get(\"" + locatorName + "\");\n");
					sb.append("       if(driver.platform.equalsIgnoreCase(\"ios\")){\n");
					sb.append("           elementBeans=locatorMap.get(\"ios_" + locatorName + "\");\n");
					sb.append("       }\n");
					sb.append("   return elementBeans;\n }\n");
					sb.append("   else{\n log.error(\"在\"+pagePath+\"中不存在【"+locatorName+"】元素信息\"); return null;}\n }\n");
				}
			}
			sb.append("}");
			// 将自动生成的PageObject代码写入文件
//			File pageObjectFile = new File(
//					"src/main/java/com/testfan/ui/auto/xml/pageObject/" + pageClassName + ".java");
			File pageObjectFile = new File("src/main/java/" + filePath + "/" + pageClassName + ".java");
			if (pageObjectFile.exists()) {
				pageObjectFile.delete();

			}
			try {
				FileWriter fileWriter = new FileWriter(pageObjectFile, false);
				BufferedWriter output = new BufferedWriter(fileWriter);
				output.write(sb.toString());
				output.flush();
				output.close();
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			System.out.println(sb);
			log.info("自动生成对象库java代码成功");
		}

	}

	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		GenerateAutoCodeForXml.autoCode();
	}
}
