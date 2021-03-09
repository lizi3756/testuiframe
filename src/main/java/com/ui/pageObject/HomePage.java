package com.ui.pageObject;
import java.io.IOException;
import org.apache.log4j.Logger;
import java.io.InputStream;
import com.frame.driver.DriverBase;
import com.frame.element.ElementBeans;
//登录_对象库类
public class HomePage extends BasePage {
private static Logger log = Logger.getLogger(HomePage.class);//用于eclipse工程内运行查找对象库文件路径
 public   HomePage(DriverBase driver) {
//工程内读取对象库文件
	super(driver);

}
/***
* 用户名
* @return
* @throws IOException
*/
public  ElementBeans username() throws IOException
 {
   if(locatorMap.containsKey("username")||locatorMap.containsKey("ios_username")){
   ElementBeans elementBeans=locatorMap.get("username");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_username");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【username】元素信息"); return null;}
 }

/***
* 密码
* @return
* @throws IOException
*/
public  ElementBeans password() throws IOException
 {
   if(locatorMap.containsKey("password")||locatorMap.containsKey("ios_password")){
   ElementBeans elementBeans=locatorMap.get("password");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_password");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【password】元素信息"); return null;}
 }

/***
* 登录按钮
* @return
* @throws IOException
*/
public  ElementBeans login() throws IOException
 {
   if(locatorMap.containsKey("login")||locatorMap.containsKey("ios_login")){
   ElementBeans elementBeans=locatorMap.get("login");
       if(driver.platform.equalsIgnoreCase("ios")){
           elementBeans=locatorMap.get("ios_login");
       }
   return elementBeans;
 }
   else{
 log.error("在"+pagePath+"中不存在【login】元素信息"); return null;}
 }
}