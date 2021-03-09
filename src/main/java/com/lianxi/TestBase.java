package com.lianxi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

/**
 * @Author: lizi
 * @Date: 2020/12/25 下午6:04
 */
public class TestBase {
    WebDriver driver;
    @BeforeClass
    public  void start() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","/Users/zhangjingli/Study/Testfan/UIframe/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://unionvip.58v5.cn/bsp/index");
        driver.findElement(By.id("username")).sendKeys("zhangjingli");
        driver.findElement(By.id("password")).sendKeys("aaa123");
        driver.findElement(By.id("input-btn")).click();
        //driver.findElement(By.id("dynamiccode")).sendKeys("111");
        //driver.findElement(By.className("submitPut")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//隐式等待
    }



}
