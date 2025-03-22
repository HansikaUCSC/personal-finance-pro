import org.financePro.ForgetPassword;
import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.PropertyFileReader;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ForgetPasswordTest {
    ForgetPassword forgetPassword;
    PropertyFileReader prop = new PropertyFileReader();
    String email = prop.getProperty("login","email");
    String newPassword = prop.getProperty("forgetPassword","new.password");
    String confirmPassword = prop.getProperty("forgetPassword","confirm.password");
    String optSentSuccessMessage = prop.getProperty("forgetPasswordPageObjects","opt.sent.success.message");
    String PasswordResetSuccessMsg = prop.getProperty("forgetPasswordPageObjects","password.reset.success.message");

    @BeforeTest
    public void setUp(){
        GlobalConfigurations.getInstance().openBrowser("chrome");
        GlobalConfigurations.getInstance().navigateToURL();
    }

    @Test
    public void forgetPasswordTest() throws Exception {
        forgetPassword = new ForgetPassword();
        forgetPassword.forgetPassword(email,newPassword,optSentSuccessMessage,PasswordResetSuccessMsg);
    }

    @AfterTest
    public void tearDown() {
        GlobalConfigurations.getInstance().closeBrowser();
    }
}
