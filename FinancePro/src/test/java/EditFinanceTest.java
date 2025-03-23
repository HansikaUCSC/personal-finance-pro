import org.financePro.pages.EditFinanceRecord;
import org.financePro.pages.UserLogin;
import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.PropertyFileReader;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EditFinanceTest {
    UserLogin login;
    EditFinanceRecord editFinanceRecord;
    PropertyFileReader prop = new PropertyFileReader();
    String email = prop.getProperty("login","email");
    String password = prop.getProperty("login","password");
    String loginSuccessMsg = prop.getProperty("login","successMessage");
    String activityDate = prop.getProperty("editFinance","activityDate");
    String amount = prop.getProperty("editFinance","amount");
    String category = prop.getProperty("editFinance","category");
    String subCategory = prop.getProperty("editFinance","subCategory");
    String updateSuccessMessage = prop.getProperty("editFinance","updateSuccessMessage");

    @BeforeTest
    public void setUp(){
        GlobalConfigurations.getInstance().openBrowser("chrome");
        GlobalConfigurations.getInstance().navigateToURL();
    }
    @Test
    public void editFinanceTest(){
        login = new UserLogin();
        login.signIn(email,password,loginSuccessMsg);
        editFinanceRecord = new EditFinanceRecord();
        editFinanceRecord.editFinances(activityDate,amount,category,subCategory);
        GlobalConfigurations.getInstance().verifyAlert(updateSuccessMessage);
    }
    @AfterTest
    public void tearDown(){
        GlobalConfigurations.getInstance().closeBrowser();
    }
}
