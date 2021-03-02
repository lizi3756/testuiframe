package com.ui.actions;


import com.frame.driver.DriverBase;
import com.frame.element.ElementOperate;
import com.ui.pageObject.HomePage;

/**
 * @Author: lizi
 * @Date: 2020/12/25 下午8:21
 */
public class BaseActions {
    ElementOperate operate;
    DriverBase driver;
    HomePage homePage;
    public BaseActions(DriverBase driver){
        this.driver=driver;
        this.operate=new ElementOperate(driver);
        this.homePage=new HomePage(driver);
    }
}
