package com.ui.pageObject;
import java.io.IOException;
import org.apache.log4j.Logger;
import java.io.InputStream;
import com.frame.driver.DriverBase;
import com.frame.element.ElementBeans;
//我的车商列表页_对象库类
public class CarListPage extends BasePage {
private static Logger log = Logger.getLogger(CarListPage.class);//用于eclipse工程内运行查找对象库文件路径
 public   CarListPage(DriverBase driver) {
//工程内读取对象库文件
	super(driver);

}
/***
* 市场
* @return
* @throws IOException
*/
public  ElementBeans market() throws IOException
 {
   if(locatorMap.containsKey("market")||locatorMap.containsKey("ios_market")){
   ElementBeans elementBeans=locatorMap.get("market");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_market");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【market】元素信息"); return null;}
 }

/***
* 场外
* @return
* @throws IOException
*/
public  ElementBeans market1() throws IOException
 {
   if(locatorMap.containsKey("market1")||locatorMap.containsKey("ios_market1")){
   ElementBeans elementBeans=locatorMap.get("market1");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_market1");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【market1】元素信息"); return null;}
 }

/***
* 查询
* @return
* @throws IOException
*/
public  ElementBeans status() throws IOException
 {
   if(locatorMap.containsKey("status")||locatorMap.containsKey("ios_status")){
   ElementBeans elementBeans=locatorMap.get("status");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_status");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【status】元素信息"); return null;}
 }

/***
* 未通过-改
* @return
* @throws IOException
*/
public  ElementBeans nopass() throws IOException
 {
   if(locatorMap.containsKey("nopass")||locatorMap.containsKey("ios_nopass")){
   ElementBeans elementBeans=locatorMap.get("nopass");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_nopass");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【nopass】元素信息"); return null;}
 }

/***
* 查询
* @return
* @throws IOException
*/
public  ElementBeans search() throws IOException
 {
   if(locatorMap.containsKey("search")||locatorMap.containsKey("ios_search")){
   ElementBeans elementBeans=locatorMap.get("search");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_search");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【search】元素信息"); return null;}
 }
}