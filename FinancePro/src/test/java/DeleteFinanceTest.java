import org.financePro.pages.DeleteFinanceRecord;
import org.financePro.pages.UserLogin;
import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.PropertyFileReader;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DeleteFinanceTest {
    UserLogin login;
    DeleteFinanceRecord deleteFinanceRecord;

    PropertyFileReader prop = new PropertyFileReader();
    String email = prop.getProperty("login","email");
    String password = prop.getProperty("login","password");
    String loginSuccessMsg = prop.getProperty("login","successMessage");
    String finalActivity = prop.getProperty("deleteFinance","finalActivity");

    @BeforeTest
    public void setUp(){
        GlobalConfigurations.getInstance().openBrowser("chrome");
        GlobalConfigurations.getInstance().navigateToURL();
    }
    @Test
    public void deleteFinanceTest(){
        login = new UserLogin();
        login.signIn(email,password,loginSuccessMsg);
        deleteFinanceRecord = new DeleteFinanceRecord();
        deleteFinanceRecord.deleteFinance(finalActivity);
    }
    @AfterTest
    public void tearDown(){
        GlobalConfigurations.getInstance().closeBrowser();
    }
}
