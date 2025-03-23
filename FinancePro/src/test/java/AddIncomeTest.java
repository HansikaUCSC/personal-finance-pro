import org.financePro.UserLogin;
import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.PropertyFileReader;
import org.testng.annotations.BeforeTest;

public class AddIncomeTest {
    UserLogin login;
    PropertyFileReader prop = new PropertyFileReader();
    String email = prop.getProperty("login","email");
    String password = prop.getProperty("login","password");
    String loginSuccessMsg = prop.getProperty("login","successMessage");
    @BeforeTest
    public void setUp(){
        GlobalConfigurations.getInstance().openBrowser("chrome");
        GlobalConfigurations.getInstance().navigateToURL();
        login = new UserLogin();
        login.signIn(email,password,loginSuccessMsg);
    }
}
