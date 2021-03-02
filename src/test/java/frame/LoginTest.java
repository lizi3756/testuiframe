package frame;

import com.frame.TestBase.ESCTestBase;
import com.ui.actions.LoginActions;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @Author: lizi
 * @Date: 2020/12/25 下午8:35
 */
public class LoginTest extends ESCTestBase {
    @Test
    public void login() throws IOException {
        LoginActions loginActions=new LoginActions(driver);
        loginActions.login();
    }


}
