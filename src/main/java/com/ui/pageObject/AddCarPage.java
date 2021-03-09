package com.ui.pageObject;
import java.io.IOException;
import org.apache.log4j.Logger;
import java.io.InputStream;
import com.frame.driver.DriverBase;
import com.frame.element.ElementBeans;
//车商录入_对象库类
public class AddCarPage extends BasePage {
private static Logger log = Logger.getLogger(AddCarPage.class);//用于eclipse工程内运行查找对象库文件路径
 public   AddCarPage(DriverBase driver) {
//工程内读取对象库文件
	super(driver);

}
/***
* 商户类型
* @return
* @throws IOException
*/
public  ElementBeans cdCustomerType() throws IOException
 {
   if(locatorMap.containsKey("cdCustomerType")||locatorMap.containsKey("ios_cdCustomerType")){
   ElementBeans elementBeans=locatorMap.get("cdCustomerType");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_cdCustomerType");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【cdCustomerType】元素信息"); return null;}
 }

/***
* 商户名称
* @return
* @throws IOException
*/
public  ElementBeans companyName() throws IOException
 {
   if(locatorMap.containsKey("companyName")||locatorMap.containsKey("ios_companyName")){
   ElementBeans elementBeans=locatorMap.get("companyName");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_companyName");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【companyName】元素信息"); return null;}
 }

/***
* 所属城市
* @return
* @throws IOException
*/
public  ElementBeans city() throws IOException
 {
   if(locatorMap.containsKey("city")||locatorMap.containsKey("ios_city")){
   ElementBeans elementBeans=locatorMap.get("city");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_city");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【city】元素信息"); return null;}
 }

/***
* 所属城市
* @return
* @throws IOException
*/
public  ElementBeans beijing() throws IOException
 {
   if(locatorMap.containsKey("beijing")||locatorMap.containsKey("ios_beijing")){
   ElementBeans elementBeans=locatorMap.get("beijing");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_beijing");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【beijing】元素信息"); return null;}
 }

/***
* 所属市场
* @return
* @throws IOException
*/
public  ElementBeans marketType() throws IOException
 {
   if(locatorMap.containsKey("marketType")||locatorMap.containsKey("ios_marketType")){
   ElementBeans elementBeans=locatorMap.get("marketType");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_marketType");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【marketType】元素信息"); return null;}
 }

/***
* 场外
* @return
* @throws IOException
*/
public  ElementBeans changwai() throws IOException
 {
   if(locatorMap.containsKey("changwai")||locatorMap.containsKey("ios_changwai")){
   ElementBeans elementBeans=locatorMap.get("changwai");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_changwai");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【changwai】元素信息"); return null;}
 }

/***
* 门头照片
* @return
* @throws IOException
*/
public  ElementBeans doorTitle() throws IOException
 {
   if(locatorMap.containsKey("doorTitle")||locatorMap.containsKey("ios_doorTitle")){
   ElementBeans elementBeans=locatorMap.get("doorTitle");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_doorTitle");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【doorTitle】元素信息"); return null;}
 }

/***
* 行业
* @return
* @throws IOException
*/
public  ElementBeans industryType() throws IOException
 {
   if(locatorMap.containsKey("industryType")||locatorMap.containsKey("ios_industryType")){
   ElementBeans elementBeans=locatorMap.get("industryType");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_industryType");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【industryType】元素信息"); return null;}
 }

/***
* 保存车商
* @return
* @throws IOException
*/
public  ElementBeans save() throws IOException
 {
   if(locatorMap.containsKey("save")||locatorMap.containsKey("ios_save")){
   ElementBeans elementBeans=locatorMap.get("save");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_save");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【save】元素信息"); return null;}
 }
}