import org.financePro.ForgetPassword;
import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.PropertyFileReader;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ForgetPassowrdTest {
    ForgetPassword forgetPassword;
    PropertyFileReader prop = new PropertyFileReader();
    String email = prop.getProperty("login","email");
    String password = prop.getProperty("login","password");

    @BeforeTest
    public void setUp() throws Exception {
        GlobalConfigurations.getInstance().openBrowser("chrome");
        GlobalConfigurations.getInstance().navigateToURL();
    }

    @Test
    public void ForgetPassowrdTest() throws Exception {
        forgetPassword = new ForgetPassword();
        forgetPassword.forgetPassword(email,password);
    }

    @AfterTest
    public void tearDown() throws Exception {
        GlobalConfigurations.getInstance().closeBrowser();
    }
}
