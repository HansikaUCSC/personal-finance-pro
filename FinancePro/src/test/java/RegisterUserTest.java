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














//
//        // Fetch OTP from 10 Minute Mail (retry for 60 seconds)
//        String otp = null;
//        for (int i = 0; i < 12; i++) { // Retry for 1 minute (12 * 5 seconds)
//            otp = OTPUtils.getOTPFromMailosaur(email); // Pass email from property file
//            if (otp != null) break;
//            Thread.sleep(5000); // Wait 5 seconds between retries
//        }
//
//        if (otp == null) {
//            throw new RuntimeException("OTP not found in the inbox.");
//        }
//
//        // Submit OTP
