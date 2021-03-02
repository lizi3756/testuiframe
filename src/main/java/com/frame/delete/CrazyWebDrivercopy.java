package com.frame.delete;

import com.frame.delete.CrazyWebDriver;
import com.frame.driver.DriverBase;
import com.frame.utils.Command;
import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * @Author: lizi
 * @Date: 2020/12/28 下午8:23
 * 可以废弃吧
 */
public class CrazyWebDrivercopy extends DriverBase {
    public final static Logger log = Logger.getLogger(CrazyWebDriver.class);

    public CrazyWebDrivercopy(String platform) {
        super(platform);
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


}
