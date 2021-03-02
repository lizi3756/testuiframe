package com.frame.TestBase;

import com.frame.driver.DriverBase;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;

public class ESCTestBase {
    private static Logger log =Logger.getLogger(ESCTestBase.class);
    public DriverBase driver;
    public Assertion assertion;
    @BeforeClass
    public void init(){
        //driver =new CrazyWebDriver("chrome");
        driver = new DriverBase("chrome");
        assertion=new Assertion(driver);
        driver.get("https://unionvip.58v5.cn/bsp/index");
        driver.sleep(2);
    }

    //@AfterClass
    public void qiut(){
        driver.quit();
        log.info("浏览器已关闭");

    }

}
