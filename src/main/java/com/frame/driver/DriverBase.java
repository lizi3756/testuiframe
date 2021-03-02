package com.frame.driver;

import com.frame.element.ElementBeans;
import com.frame.utils.Command;
import com.frame.utils.ProUtil;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
  所有driver的父类，定义公用方法和对象
* @version 创建时间：2020年12月15日 下午4:33:10

*/
public class DriverBase {
	private static Logger log = Logger.getLogger(DriverBase.class);
	public WebDriver driver;
	ProUtil pro=new ProUtil("src/main/resources/global.properties");
	public String platform;

	public DriverBase(String platform) {
		this.platform=platform;
		switch (platform.toLowerCase()){
			case "chrome":
				if(Command.isWin()){
					System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_mac.exe");
				}else if(Command.isMac()){
					System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_mac");
				}else {
					System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_linux");
				}
				this.driver=new ChromeDriver();
				break;
			case "ff":
				if(Command.isWin()){
					System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
				}else {
					System.setProperty("webdriver.gecko.driver", "drivers/chromedriver_linux");
				}
				this.driver=new FirefoxDriver();
				break;
			default:
				this.driver=new ChromeDriver();
				break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement findElement(ElementBeans elementBeans) {
		WebElement element=null;
		try {
			element=driver.findElement(elementBeans.getBy());//by.xxx("xx")
			log.info("定位【"+elementBeans.getElementName()+"】成功");
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("定位【"+elementBeans.getElementName()+"】失败",e);
		}
		return element;
	}
	public WebElement findElementTimeOut(ElementBeans elementBeans) {
		WebElement element=null;
		this.implicitlyWaitZero();
		try {
			element=new WebDriverWait(driver, elementBeans.getTimout()).until(ExpectedConditions.presenceOfElementLocated(elementBeans.getBy()));
			log.info("定位【"+elementBeans.getElementName()+"】成功");
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("定位【"+elementBeans.getElementName()+"】失败在"+elementBeans.getTimout()+"秒内",e);
		}
		this.implicitlyWaitDefault();
		return element;
	}

	public List<WebElement> findElements(ElementBeans elementBeans) {
		List<WebElement> elements=driver.findElements(elementBeans.getBy());
		log.info("定位【"+elementBeans.getElementName()+"】总计定位到"+elements.size()+"个");
		return elements;
	}
    public void get(String url) {
    	driver.get(url);
    	log.info("打开【"+url+"】成功");
    }

    public String getCurrentUrl() {
    	String currentUrl = driver.getCurrentUrl();
    	log.info("获取当前界面url是【"+currentUrl+"】");
    	return currentUrl;
    }

    public String getTitle() {
    	String title = driver.getTitle();
    	log.info("获取当前界面title是【"+title+"】");
    	return title;
    }

    public String getPageSource() {
    	String pageSource = driver.getPageSource();
    	log.info("获取当前界面pageSource是【"+pageSource+"】");
    	return pageSource;
    }

    public String getWindowHandle() {
    	String windowHandle = driver.getWindowHandle();
    	log.info("获取当前界面windowHandle是【"+windowHandle+"】");
    	return windowHandle;
    }

    public Set<String> getWindowHandles() {
    	Set<String> windowHandles = driver.getWindowHandles();
    	for(String s:windowHandles) {
    		log.info("获取所有界面windowHandles依次是【"+s+"】");
    	}
    	return windowHandles;
    }
    public void refresh() {
    	driver.navigate().refresh();
    	log.info("页面刷新成功");
    }
    public void forward() {
    	driver.navigate().forward();
    	log.info("浏览器前进成功");
    }
    public void back() {
    	driver.navigate().back();
    	log.info("浏览器后退成功");
    }
    public Alert switchToAlert() {
    	Alert alert=null;
    	try {
        	alert = driver.switchTo().alert();
        	log.info("alert切换成功");
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("alert切换失败", e);
		}
    	return alert;
    }
    public Alert switchToAlert(int timeout) {
    	Alert alert=null;
    	try {
    		alert = new WebDriverWait(driver, timeout).until(ExpectedConditions.alertIsPresent());
    		log.info("alert切换成功");
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("alert切换失败", e);
		}
    	return alert;
    }
    public void switchToFrame(int index) {
    	driver.switchTo().frame(index);
    	log.info("frame切换成功");
    }
    public void switchToFrame(String nameOrId) {
    	driver.switchTo().frame(nameOrId);
    	log.info("frame切换成功");
    }
    public void switchToFrame(ElementBeans elementBeans) {
    	driver.switchTo().frame(findElement(elementBeans));
    	log.info("frame切换成功");
    }
    public void switchToDefaultFrame() {
    	driver.switchTo().defaultContent();
    	log.info("回到最顶层的frame，也就是回到默认content");
    }
    public void switchToParentFrame() {
    	driver.switchTo().parentFrame();
    	log.info("回到父frame");
    }
    public void switchToFrame(ElementBeans elementBeans,int timeout) {
    	try {
    		new WebDriverWait(driver, timeout).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(findElement(elementBeans)));
    		log.info("frame切换成功");
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("frame切换失败", e);
		}
    }
    public void switchToWindow(String nameOrHandle) {
    	driver.switchTo().window(nameOrHandle);
    	log.info(nameOrHandle +"window切换成功");
    }

    public void swithToWindow(int index) {
    	//new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfWindowsToBe(2));
    	Set<String> windowHandles = this.driver.getWindowHandles();
    	for(String s:windowHandles) {
    		System.out.println("sssss:"+s);
    	}
    	Object object = driver.getWindowHandles().toArray()[index];
    	driver.switchTo().window(object.toString());
    	log.info("window切换到第"+index+"个成成功");
    }

    public void executeJS(String script) {
    	JavascriptExecutor js=(JavascriptExecutor)driver;
    	js.executeScript(script);
    	log.info("执行js成功，js是：" +script);
    }
    public void sleep(int seconds) {
    	try {
    		log.info("等待" +seconds+"秒");
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void implicitlyWait(int seconds) {
    	driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    	log.info("设置全局隐式等待为" +seconds+"秒");
    }
    public void implicitlyWaitDefault() {
    	int s=Integer.valueOf(pro.getKey("implicitlyWait"));
    	this.implicitlyWait(s);
    }
    public void implicitlyWaitZero() {
		this.implicitlyWait(0);
    }
    public void quit() {
    	driver.quit();
    }
    public String getScreenShot(String filename) {
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMMdd-HHmmssSSS" );
        String time=formatter.format(new Date()).toString();
    	File screenshotAs = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	try {
			FileUtils.copyFile(screenshotAs, new File("report/snapshot/"+time+filename));
			log.info("截图成功，图片是：report/snapshot/"+time+filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.debug("截图失败，图片是：report/snapshot/"+time+filename,e);
		}
    	return time+filename;
    }
    public String getScreenShot(String path,String filename) {
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMMdd-HHmmss" );
        String time=formatter.format(new Date()).toString();
    	File screenshotAs = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	try {
			FileUtils.copyFile(screenshotAs, new File(path+"/"+time+filename));
			log.info("截图成功，图片是："+path+"/"+time+filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.debug("截图失败，图片是："+path+"/"+time+filename,e);
		}
    	return time+filename;
    }
    public void close() {
		driver.close();
		log.info("当前界面关闭");
	}


}
