package com.frame.TestBase;

import com.frame.driver.DriverBase;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class ESCTestBase {
    private static Logger log =Logger.getLogger(ESCTestBase.class);
    public DriverBase driver;
    public Assertion assertion;
    @BeforeTest
    public void init(){
        //driver =new CrazyWebDriver("chrome");
        driver = new DriverBase("chrome");
        assertion=new Assertion(this.driver);
        this.driver.get("https://unionvip.58v5.cn/bsp/index");
        this.driver.sleep(2);
       // this.driver.skipCookies("");

    }

    @AfterTest
    public void quit(){
        driver.quit();
        log.info("浏览器已关闭");

    }

}
