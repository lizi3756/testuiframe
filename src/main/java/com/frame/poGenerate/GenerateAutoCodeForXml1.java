package com.frame.poGenerate;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * @Author: lizi
 * @Date: 2020/12/30 下午7:24
 */
public class GenerateAutoCodeForXml1 {
    public static void main(String[] args) {
        String filename="src/main/resources/pageObjectFiles/myCarListcopy.xml";//元素文件
        File file = new File(filename);
        SAXReader reader =new SAXReader();//dom4j的方法
        try {
            Document document = reader.read(file);
            Element rootElement = document.getRootElement();//得到根节点map
            List<Element> pages = rootElement.elements("page");
            for(Element page:pages){
                //System.out.println(element);//得到page的那一行，共计3个属性
                //String pageName = page.attribute(0).getValue();//com.ui.pageObject.HomePagecopy
                String pageName = page.attribute("pagename").getValue();
                String[] pageNameArray =pageName.split("\\.");
                String pageClassName = pageNameArray[pageNameArray.length - 1];//HomePagecopy
                String packageName = pageNameArray[0];
                for (int k = 1; k < pageNameArray.length - 1; k++) {
                    packageName = packageName + "." + pageNameArray[k];
                }
                System.out.println("packageName:"+packageName);//com.ui.pageObject
                String filepath= packageName.replaceAll("\\.", "/");//得到文件路径com/ui/pageObject
                StringBuffer sb = new StringBuffer("package "+ packageName+";\n");
                sb.append("import java.io.IOException;\n");
                sb.append("import org.apache.log4j.Logger;\n");
                sb.append("import com.frame.driver.DriverBase;\n");
                sb.append("import com.frame.element.ElementBeans;\n");
                sb.append("\n");
                sb.append("public class " + pageClassName + " extends BasePage {\n");
                sb.append("    private static Logger log = Logger.getLogger("+pageClassName+".class);\n");
                sb.append("    public " + pageClassName + "(DriverBase driver) {\n");
                sb.append("        super(driver);\n");
                sb.append("    }\n");

                List<Element> locators = page.elements("locator");// 获取locaror节点
                for (Element locator:locators){
                    String locatorName = locator.getTextTrim();//username
                    System.out.println(locatorName);
                    if(locator.attributeCount()>3){
                        sb.append("\n/***\n" +"* "+locator.attribute(3).getValue() + "\n*/\n");
                    }else {
                        sb.append("\n");
                    }
                    sb.append("public  ElementBeans "+locatorName +"()  throws IOException{ \n");
                    sb.append("\tif(locatorMap.containsKey(\""+locatorName+"\")){\n");
                    sb.append("\t\tElementBeans elementBeans=locatorMap.get(\""+locatorName+"\");\n");
                    sb.append("\t\treturn elementBeans;\n");
                    sb.append("\t}\n");
                    sb.append("\telse{\n");
                    sb.append("\t\tlog.error(\"在\"+pagePath+\"中不存在【"+locatorName+"】元素信息\");\n ");
                    sb.append("\t\treturn null;\n");
                    sb.append("\t\t}\n");
                    sb.append("\t}\n");

                }
                sb.append("}\n");
                File pageObjectFile = new File("src/main/java/"+filepath+"/"+pageClassName+".java");
                if(pageObjectFile.exists()){
                    pageObjectFile.delete();
                }
                try{
                    //写文件
                    BufferedWriter output = new BufferedWriter(new FileWriter(pageObjectFile));
                    output.write(sb.toString());
                    output.flush();
                    output.close();

                } catch (Exception e){
                    e.printStackTrace();
                }
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }


}
