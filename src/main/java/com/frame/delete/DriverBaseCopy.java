package com.frame.delete;

import com.frame.element.ElementBeans;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author: lizi
 * @Date: 2020/12/28 下午8:39
 * 需要的参数：是哪个浏览器的platform
 * 方法：返回driver
 */
public class DriverBaseCopy {
   public static Logger logger =Logger.getLogger(DriverBaseCopy.class);
   public String platform;
   public WebDriver driver;
   /*
   构造方法
    */
    public DriverBaseCopy(String platform){
        this.platform=platform;
    }

    public WebDriver getDriver(){
        return driver;
    }

    public WebElement findelement(ElementBeans elementBeans){
        WebElement element=null;
        try {
            element = driver.findElement(elementBeans.getBy());
            logger.info("定位【"+elementBeans.getElementName()+"】成功");
        }catch (Exception e){
            logger.debug("定位【"+elementBeans.getElementName()+"】失败");
        }
        return element;
    }
    public WebElement findelementOut(ElementBeans elementBeans){
        WebElement element=null;
        try {
            element = new WebDriverWait(driver, elementBeans.getTimout()).until(ExpectedConditions.presenceOfElementLocated(elementBeans.getBy()));
        }catch (Exception e){
        }
        return element;
    }
    //定位多个元素
    public List<WebElement> findelements(ElementBeans elementBeans){
        List<WebElement> elements=null;
        try {
            elements = driver.findElements(elementBeans.getBy());
            logger.info("定位到【"+elementBeans.getElementName()+"】共计"+elements.size()+"个");
        }catch (Exception e){
            logger.debug("没有完全定位成功");
        }
        return elements;
    }
    public void getUrl(String url){
        driver.get(url);
        logger.info("打开【"+url+"】成功");
    }

    public String getTitle(){
        String title = driver.getTitle();
        logger.info("当前页的title为："+title);
        return title;

    }

    public String getCurrenturl(){
        String currentUrl = driver.getCurrentUrl();
        logger.info("当前页面的url为："+currentUrl);
        return currentUrl;
    }

    public Alert switchToAlert(){
        Alert alert = driver.switchTo().alert();
        return alert;

    }

    public Alert switchToAlert(int timeout){
        Alert alert=new WebDriverWait(driver,timeout).until(ExpectedConditions.alertIsPresent());
        return alert;
    }
    public void switchToFrame(int index){
        driver.switchTo().frame(index);
        logger.info("切换frame成功");
    }
    public void switchToFrame(String nameOrId){
        driver.switchTo().frame(nameOrId);
    }
    public void switchToFrame(ElementBeans elementBeans){
        driver.switchTo().frame(findelement(elementBeans));
    }
    public void switchToDefaultFrame(){
        driver.switchTo().defaultContent();
        driver.switchTo().parentFrame();
    }
    public void switchToFrame(ElementBeans elementBeans,int timeout){
         new WebDriverWait(driver, timeout).
                until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(findelement(elementBeans)));
    }

    public void switchToWindow(String nameOrHandle){
        driver.switchTo().window(nameOrHandle);
    }
    public void swithToWindow(int index){
        Set<String> windowHandles = driver.getWindowHandles();
        String string = driver.getWindowHandles().toArray()[index].toString();
        driver.switchTo().window(string);
    }
    public void executeJS(String script){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript(script);
    }

    public void getScreenShot(String filename) throws IOException {
        SimpleDateFormat formater =new SimpleDateFormat("yyyyMMdd-HHmmss");
        String time = formater.format(new Date());
        File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotAs, new File("image"+time+filename));
            logger.info("截图的图片是：image"+time+filename);
        }catch (Exception e){
            logger.info("截图失败");

        }
    }
}
