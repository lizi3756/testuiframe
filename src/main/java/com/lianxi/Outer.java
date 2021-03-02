package com.lianxi;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;


/**
 * @Author: lizi
 * @Date: 2020/12/24 下午5:39
 */
public class Outer {
    WebDriver driver;

    @BeforeClass(description = "模拟键盘")
    public void test01_bibi(){
        //System.setProperty("webdriver.chrome.driver","/Users/zhangjingli/Study/Testfan/UIframe/chromedriver_mac");
        driver=new ChromeDriver();
    }
    @Test
    public void test01(){
        driver.get("https://www.bilibili.com/");
        Actions actions=new Actions(driver);
        WebElement name = driver.findElement(By.className("name"));
        actions.moveToElement(name).perform();
        actions.sendKeys(Keys.ENTER).perform();//enter键
        actions.sendKeys(Keys.TAB).perform();
    }
    @Test(description = "键盘模拟选择图片")
    public void test02() throws AWTException {
       StringSelection file= new StringSelection("");
       Toolkit.getDefaultToolkit().getSystemClipboard().setContents(file, null);
       Robot robot= new Robot();
       robot.keyPress(KeyEvent.VK_CONTROL);
       robot.keyPress(KeyEvent.VK_V);
    }
}
