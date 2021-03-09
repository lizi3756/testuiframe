package com.ui.actions;

import com.frame.driver.DriverBase;
import com.frame.utils.RandomUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @Author: lizi
 * @Date: 2021/3/7 下午6:02
 */
public class AddCarActions extends BaseActions{
    public AddCarActions(DriverBase driver) {
        super(driver);
    }

    public void addCar() throws IOException {

        driver.get("http://shccrmv2.union.vip.58.com/carDealerInput/saveIndex");
        operate.click(addCarPage.cdCustomerType());
        String time = RandomUtil.time();
        operate.sendKeys(addCarPage.companyName(), "车商1"+time);
        operate.click(addCarPage.city());
        operate.click(addCarPage.beijing());
        operate.click(addCarPage.marketType());
        operate.click(addCarPage.changwai());
        operate.sendKeys(addCarPage.doorTitle(), "门头1"+time);
        operate.click(addCarPage.industryType());
        operate.click(addCarPage.save());
    }
}
