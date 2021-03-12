package frame;

import com.frame.TestBase.ESCTestBase;
import com.ui.actions.LoginActions;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @Author: lizi
 * @Date: 2020/12/25 下午8:35
 */
public class LoginTest extends ESCTestBase {

//    private static final Logger LOGGER = Logger.getLogger(LoginTest.class);
    @Test
    public void login() throws IOException {
//        LOGGER.info("begin");
        LoginActions loginActions=new LoginActions(driver);
        loginActions.login();
//        LOGGER.info("end");
    }


}
