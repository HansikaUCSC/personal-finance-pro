import org.financePro.UserRegistration;
import org.financePro.utils.GlobalConfigurations;

import org.financePro.utils.PropertyFileReader;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class RegisterUserTest {
    UserRegistration userRegistration;

    PropertyFileReader prop = new PropertyFileReader();

    String name = prop.getProperty("registration", "name");
    String password = prop.getProperty("registration", "password");
    String confirmPassword = prop.getProperty("registration", "confirmPassword");
    String regSuccessMessage = prop.getProperty("registration", "regSuccessMessage");
    String regCompletionMessage = prop.getProperty("registration", "regCompletionMessage");
    String serverDomain = "hxd6i8zi.mailosaur.net";

    public String getRandomEmail() {
        return "autotest" + System.currentTimeMillis() + "@" + serverDomain;
    }

    @BeforeMethod
    public void setUp() {
        GlobalConfigurations.getInstance().openBrowser("chrome");
        GlobalConfigurations.getInstance().navigateToURL();
    }

    @Test
    public void testRegistrationWithOTP() throws Exception {
        String email = getRandomEmail();
        userRegistration = new UserRegistration();
        userRegistration.signUp(name, email, password, confirmPassword,regSuccessMessage, regCompletionMessage);
    }

    @AfterTest
    public void tearDown() {
        GlobalConfigurations.getInstance().closeBrowser();
    }
}
