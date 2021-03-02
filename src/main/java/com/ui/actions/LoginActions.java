package com.ui.actions;

import com.frame.driver.DriverBase;

import java.io.IOException;

/**
 * @Author: lizi
 * @Date: 2020/12/25 下午8:27
 */
public class LoginActions extends BaseActions{

    public LoginActions(DriverBase driver) {
        super(driver);
    }

    public void login() throws IOException {
        operate.sendKeys(homePage.username(),"zhangjingli");
        operate.sendKeys(homePage.password(), "aaa123");
        operate.click(homePage.login());
    }
}
