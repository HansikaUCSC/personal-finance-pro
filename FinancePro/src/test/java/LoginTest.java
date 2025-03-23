import org.financePro.pages.UserLogin;
import org.financePro.pages.UserLogout;
import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.PropertyFileReader;
import org.financePro.pages.viewUserProfile;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
    PropertyFileReader prop = new PropertyFileReader();
    UserLogin login;
    viewUserProfile profile;
    UserLogout logout;
    String email = prop.getProperty("login","email");
    String password = prop.getProperty("login","password");
    String name = prop.getProperty("login","name");
    String url = prop.getProperty("config","url");
    String successMessage = prop.getProperty("login","successMessage");

    @BeforeTest
    public void setUp(){
       GlobalConfigurations.getInstance().openBrowser("chrome");
       GlobalConfigurations.getInstance().navigateToURL();
    }
    @Test(priority = 1)
    public void login(){
        login = new UserLogin();
        login.signIn(email,password,successMessage);
        login.verifySignIn(name);
    }
    @Test(priority = 2)
    public void viewProfile(){
        profile = new viewUserProfile();
        profile.viewProfile();
        profile.verifyProfileDetails(name,email);
    }
    @Test(priority = 3)
    public void logout(){
        logout = new UserLogout();
        logout.logOut();
        logout.verifyLogout(url);
    }
    @AfterTest
    public void tearDown(){
        GlobalConfigurations.getInstance().closeBrowser();
    }
}
