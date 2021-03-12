package com.ui.actions;

import com.frame.driver.DriverBase;
import com.frame.utils.RandomUtil;

import java.io.IOException;

/**
 * @Author: lizi
 * @Date: 2021/3/7 下午6:02
 */
public class CarListActions extends BaseActions{
    public CarListActions(DriverBase driver) {
        super(driver);
    }

    public void searchMarket() throws IOException {
        //市场查询
        driver.get("http://shccrmv2.union.vip.58.com/vue/?_bspHttpsRefer=true#/myDealer");
        operate.click(carListPage.market());
        operate.click(carListPage.market1());
        operate.click(carListPage.search());
    }
    public void searchStatus() throws IOException {
        //状态查询
        driver.get("http://shccrmv2.union.vip.58.com/vue/?_bspHttpsRefer=true#/myDealer");
        operate.click(carListPage.status());
        operate.click(carListPage.search());
    }
}
