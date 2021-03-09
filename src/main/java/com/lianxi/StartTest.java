package com.lianxi;

import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.*;

/**
 * @Author: lizi
 * @Date: 2020/12/21 下午5:08
 * 启动浏览器
 */
public class StartTest extends TestBase{

    //@Test(description = "车商搜索-旧")
    public void research1() throws InterruptedException {
        //select
        driver.get("http://shccrmv2.union.vip.58.com/cardealer/cardealerlist/department?_bspHttpsRefer=true");
        WebElement conMarketType = driver.findElement(By.id("conMarketType"));
        Select marketSelect =new Select(conMarketType);
        marketSelect.selectByIndex(3);
        sleep(1000);
        /* marketSelect.selectByValue("2");
        sleep(1000);
        marketSelect.selectByVisibleText("场内");*/
        /*WebElement firstSelectedOption = marketSelect.getFirstSelectedOption();
        List<WebElement> options = marketSelect.getOptions();
        for (WebElement webElement:options){
            System.out.println(webElement.getText());
        }*/

        //System.out.println(firstSelectedOption.getText());
        driver.findElement(By.xpath("//span[text()='查询']")).click();
        sleep(3000);
    }

   //@Test(description = "车商搜索-新")
    public void login() throws InterruptedException {
        driver.get("http://shccrmv2.union.vip.58.com/vue/?_bspHttpsRefer=true#/myDealer");
        //场内搜索，select

       //场内搜索，非select
       driver.findElement(By.xpath("//*[@id=\"dealer\"]/form/div[4]/div/div[1]/div/div/div/div/input")).click();
        sleep(3000);
        driver.findElement(By.xpath("//*[text()='场内']")).click();
        sleep(3000);
        driver.findElement(By.xpath("//span[text()=\"查询\"]")).click();

        String text = driver.findElement(By.cssSelector("#tab-car")).getText();
        System.out.println("文本内容为："+text);
        String id = driver.findElement(By.cssSelector("#tab-car")).getAttribute("id");
        System.out.println("id的值为:"+id);
        //从未合作
        driver.findElement(By.xpath("//label[@for='partnerRadio']/following-sibling::div/div/label[4]/span/span")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[text()=\"查询\"]")).click();
        Thread.sleep(3000);
    }
    //@Test(description = "新开页面")
    public void open(){
        driver.get("http://shccrmv2.union.vip.58.com/vue/?_bspHttpsRefer=true#/myDealer");
        driver.findElement(By.xpath("//*[@id=\"dealer\"]/div[1]/div/div[3]/table/tbody/tr[1]/td[21]/div/button[1]/span")).click();
        Set<String> windowHandles = driver.getWindowHandles();
        String string = windowHandles.toArray()[1].toString();
        driver.switchTo().window(string);
        for(String wo:windowHandles){
            driver.switchTo().window(wo);
            /*if(driver.getTitle().equals("车商详情")){
                System.out.println("定位到了哈哈哈");
                break;
            }*/
            /*String currentUrl = driver.getCurrentUrl();
            if(currentUrl.equals("http://shccrmv2.union.vip.58.com/vue/#/cardealerDetail?carDealerId=1292744417077125120")){
                System.out.println("通过url定位到了");
            }*/


        }

    }
    //@Test(description = "经纪人搜索")
    public void test001_serach() throws InterruptedException {
        driver.findElement(By.cssSelector("#tab-borker")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id='pane-borker']/descendant::div[25]/label[7]/span/span")).click();
        Thread.sleep(2000);
        //one
        WebElement until = new WebDriverWait(driver, 3).
                until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#tab-borker")));
        //second
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement element = wait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply( WebDriver input) {
                return input.findElement(By.cssSelector("#tab-borker"));
            }
        });
        wait.until(new ExpectedCondition<Boolean>() {

            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver input) {
                return input.getPageSource().contains("");
            }
        });


    }

    public void test002_alert(){
        /*WebDriverWait wait =new WebDriverWait(driver, 3);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();*/
        Alert alert = new WebDriverWait(driver, 3).until(ExpectedConditions.alertIsPresent());
        alert.accept();


    }
    @Test(description = "新增车商")
    public void test005_add() throws Exception{
        try {
            driver.get("http://shccrmv2.union.vip.58.com/carDealerInput/saveIndex");
            driver.findElement(By.xpath("//*[@name='cdCustomerType'][4]")).click();
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMMddHHmmss" );
            String time=formatter.format(new Date());
            driver.findElement(By.id("companyName")).sendKeys("1车商"+time);
            driver.findElement(By.name("city")).click();
            driver.findElement(By.xpath("//option[text()='bj-北京']")).click();
            driver.findElement(By.id("marketType")).click();
            driver.findElement(By.xpath("//option[text()='场外1']")).click();
            driver.findElement(By.id("address")).sendKeys("天安门");
            //driver.findElement(By.xpath("//span[text()='地图']")).click();
            //driver.findElement(By.id("searchArea")).sendKeys("天安门");
            Thread.sleep(2000);
            //document.getElementById('longitude').value = '1.333'
            JavascriptExecutor  js=(JavascriptExecutor) driver;
            js.executeScript("document.getElementById('longitude').value = '116.479502'");
            js.executeScript("document.getElementById('latitude').value = '39.929075'");

            //driver.findElement(By.xpath("//*[@id='carDealerMap']//div")).click();
            Thread.sleep(2000);
            /*Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);*/
            //Thread.sleep(3000);

            //driver.findElement(By.xpath("//a[@id='confirmGPS']/span")).click();
            Thread.sleep(3000);



            driver.findElement(By.id("doorTitle")).sendKeys("门头"+time);
            driver.findElement(By.xpath("//input[@name='industryType']")).click();

            driver.findElement(By.xpath("//span[text()='保存车商']")).click();

            WebDriverWait wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnMsgOK"))).click();





        }catch (Exception e){
            Assert.fail("失败");
            e.printStackTrace();
        }
    }
    //@Test(description = "车商编辑：单选框+市场随机选择")
    public void test003_dan() throws Exception {
        try {
            //车商编辑，随机选择市场
            driver.get("http://shccrmv2.union.vip.58.com/carDealerInput/updateIndex/1292744417077125120");
            WebElement marketType = driver.findElement(By.id("marketType"));
            Select marketTypeSelect =new Select(marketType);
            String first_text = marketTypeSelect.getFirstSelectedOption().getText();
            System.out.println("first_text:"+first_text);
            //int size = marketTypeSelect.getOptions().size();//选项大小
            int chose=(int)( Math.random()*2)+1;
            marketTypeSelect.selectByIndex(chose);
            String second_text = marketTypeSelect.getFirstSelectedOption().getText();//实际选择数据

            while(first_text.equals(second_text)){
                chose=(int)( Math.random()*2)+1;
                marketTypeSelect.selectByIndex(chose);
                second_text = marketTypeSelect.getFirstSelectedOption().getText();
            }

            System.out.println("second_text:"+second_text);

            sleep(2000);
            WebElement marketing = driver.findElement(By.id("marketing"));
            Select marketingSelect =new Select(marketing);
            marketingSelect.selectByIndex(3);
            sleep(2000);
            driver.findElement(By.xpath("//*[text()='保存车商']")).click();
            sleep(2000);
            driver.findElement(By.xpath("//*[@id='btnMsgOK']")).click();

            sleep(2000);
            driver.findElement(By.xpath("//span[text()='审核']")).click();
            driver.findElement(By.xpath("//span[text()='确认']")).click();



            String actual_text = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div[2]/div/div[4]/div[1]/div/span[2]")).getText();
            System.out.println("actual_text:"+actual_text);

            Assert.assertTrue(actual_text.contains(second_text));


            //单选框
        /*List<WebElement> webElements = driver.findElements(By.xpath("//*[@name='categoryType']"));
        for(WebElement we:webElements){
            if(!we.isSelected()){
                we.click();
                System.out.println("选中的："+we.getText());
                //System.out.println("tag:"+we.getTagName());
                break;
            }
        }*/

        }catch (Exception | Error  e){
            e.printStackTrace();
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            //注意路径，没有前面的/
            //new File(path+"/"+time+filename)
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMMdd-HHmmss" );
            String time=formatter.format(new Date());
            String filename="Error.png";
            FileUtils.copyFile(file, new File("report/"+time+filename));
            Assert.fail(e.getMessage());
        }
    }



}
