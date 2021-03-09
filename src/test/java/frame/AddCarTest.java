package frame;

import com.frame.TestBase.ESCTestBase;
import com.ui.actions.AddCarActions;
import com.ui.actions.LoginActions;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @Author: lizi
 * @Date: 2021/3/7 下午6:12
 */
public class AddCarTest extends ESCTestBase {
    @Test
    public void test001_login() throws IOException {
        LoginActions loginActions=new LoginActions(driver);
        loginActions.login();
    }
    @Test(description = "车商录入正常流程")
    public void test002_addCar() throws IOException {
        AddCarActions addCarActions = new AddCarActions(driver);
        addCarActions.addCar();
        assertion.assertContainsText("保存成功", 3);

    }

}
