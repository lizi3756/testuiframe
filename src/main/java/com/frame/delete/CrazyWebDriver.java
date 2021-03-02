package com.frame.delete;


import com.frame.driver.DriverBase;
import com.frame.utils.Command;
import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

/**

* @author

* @version 创建时间：2020年2月15日 下午4:26:46

*/
public class CrazyWebDriver extends DriverBase {
	private static Logger log = Logger.getLogger(CrazyWebDriver.class);

	public CrazyWebDriver(String platform) {
		super(platform);
		switch (platform.toLowerCase()) {
	      case "chrome":
	    	  if(Command.isWin()) {
	    		  System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_mac.exe");
	    	  }else if(Command.isMac()){
	    		  System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_mac");
	    	  }else {
	    		  System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_linux");
	    	  }
		      this.driver = new ChromeDriver();
		      break;
	      case "ff":
	    	  if(Command.isWin()) {
	    		  System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
	    	  }else if(Command.isMac()){
	    		  System.setProperty("webdriver.gecko.driver", "drivers/chromedriver_mac");
	    	  }else {
	    		  System.setProperty("webdriver.gecko.driver", "drivers/chromedriver_linux");
	    	  }
	          this.driver = new FirefoxDriver();
	          break;
	      case "ie":
	    	  if(Command.isWin()) {
	    		  System.setProperty("webdriver.gecko.driver", "drivers/IEDriverServer.exe");
	    	  }else {
	    		  log.error("ie浏览器仅仅支持windows系统");
	    		  throw new RuntimeException("ie浏览器仅仅支持windows系统");
	    	  }
	         this.driver = new InternetExplorerDriver();
	         break;
	      case "edge":
	    	  if(Command.isWin()) {
	    		  System.setProperty("webdriver.gecko.driver", "drivers/MicrosoftWebDriver.exe");
	    	  }else {
	    		  log.error("edge浏览器仅仅支持windows系统");
	    		  throw new RuntimeException("edge浏览器仅仅支持windows系统");
	    	  }
	          this.driver = new EdgeDriver();
	          break;
	      case "safari":
	    	  if(Command.isMac()) {
	    		  this.driver = new SafariDriver();
	    	  }else {
	    		  log.error("safari浏览器仅仅支持mac系统");
	    		  throw new RuntimeException("safari浏览器仅仅支持mac系统");
	    	  }

	          break;
	      default:
	        this.driver = new ChromeDriver();
	        break;
	    }
	    driver.manage().window().maximize();
	    implicitlyWaitDefault();//设置默认的隐式等待，在global.properties文件中进行的配置
	}



}
