package frame;

import com.frame.TestBase.ESCTestBase;
import com.ui.actions.AddCarActions;
import com.ui.actions.CarListActions;
import com.ui.actions.LoginActions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @Author: lizi
 * @Date: 2021/3/7 下午6:12
 */
public class CarListTest extends ESCTestBase {
    @Test
    public void test001_login() throws IOException {
        LoginActions loginActions=new LoginActions(driver);
        loginActions.login();
    }


    @Test(description = "市场查询")
    public void test002_market() throws IOException {
        CarListActions carListActions = new CarListActions(driver);
        carListActions.searchMarket();
        assertion.assertContainsText("场外", 3);

    }
    @Test(description = "车商录入正常流程")
    public void test003_addCar() throws IOException {
        AddCarActions addCarActions = new AddCarActions(driver);
        addCarActions.addCar();
        assertion.assertContainsText("车商保存成功", 3);

    }
    //@Test(description = "状态查询-未通过改")
    public void test004_status() throws IOException {
        CarListActions carListActions = new CarListActions(driver);
        carListActions.searchStatus();
        assertion.assertContainsText("未通过-改", 3);

    }





}
