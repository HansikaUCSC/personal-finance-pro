import org.financePro.ChangePassword;
import org.financePro.UserLogin;
import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.PropertyFileReader;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PasswordChangeTest {
    UserLogin login;
    ChangePassword changePassword;
    PropertyFileReader prop = new PropertyFileReader();
    String email = prop.getProperty("login","email");
    String password = prop.getProperty("login","password");
    String name = prop.getProperty("login","name");
    String newPassword = prop.getProperty("changePassword","newPassword");
    String loginSuccessMessage = "Login Successful";
    String successMessage = "Password Change Successful";


    @BeforeTest
    public void setUp(){
        GlobalConfigurations.getInstance().openBrowser("chrome");
        GlobalConfigurations.getInstance().navigateToURL();
    }
    @Test
    public void changePassword(){
        login = new UserLogin();
        changePassword = new ChangePassword();
        login.signIn(email,password,name,loginSuccessMessage);
        changePassword.changePassword(password,newPassword,newPassword);
        GlobalConfigurations.getInstance().verifyAlert(successMessage);
    }
    @AfterTest
    public void tearDown(){
        GlobalConfigurations.getInstance().closeBrowser();
    }
}
