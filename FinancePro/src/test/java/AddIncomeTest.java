import org.financePro.pages.AddFinanceEntry;
import org.financePro.pages.UserLogin;
import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.PropertyFileReader;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddIncomeTest {
    UserLogin login;
    AddFinanceEntry addFinanceEntry;
    PropertyFileReader prop = new PropertyFileReader();
    String email = prop.getProperty("login","email");
    String password = prop.getProperty("login","password");
    String loginSuccessMsg = prop.getProperty("login","successMessage");
    String entryType = prop.getProperty("addIncome","entryType");
    String activityDate = prop.getProperty("addIncome","activityDate");
    String amount = prop.getProperty("addIncome","amount");
    String incomeCategory = prop.getProperty("addIncome","incomeCategory");
    String incomeSubCategory = prop.getProperty("addIncome","incomeSubCategory");
    String addSuccessMessage = prop.getProperty("addIncome","addSuccessMessage");
    String isFirstEntry = prop.getProperty("addIncome","isFirstEntry");

    @BeforeTest
    public void setUp(){
        GlobalConfigurations.getInstance().openBrowser("chrome");
        GlobalConfigurations.getInstance().navigateToURL();
    }

    @Test
    public void addIncomeEntry(){
        login = new UserLogin();
        login.signIn(email,password,loginSuccessMsg);
        addFinanceEntry = new AddFinanceEntry();
        addFinanceEntry.addNewFinanceEntry(isFirstEntry,entryType,activityDate,amount,incomeCategory,incomeSubCategory);
        GlobalConfigurations.getInstance().verifyAlert(addSuccessMessage);
    }

    @AfterTest
    public void tearDown(){
        GlobalConfigurations.getInstance().closeBrowser();
    }
}
