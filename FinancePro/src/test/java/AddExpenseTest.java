import org.financePro.pages.AddFinanceEntry;
import org.financePro.pages.UserLogin;
import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.PropertyFileReader;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddExpenseTest {
    UserLogin login;
    AddFinanceEntry addFinanceEntry;
    PropertyFileReader prop = new PropertyFileReader();
    String email = prop.getProperty("login","email");
    String password = prop.getProperty("login","password");
    String loginSuccessMsg = prop.getProperty("login","successMessage");
    String firstEntry = prop.getProperty("addExpense","firstEntry");
    String entryType = prop.getProperty("addExpense","entryType");
    String activityDate = prop.getProperty("addExpense","activityDate");
    String amount = prop.getProperty("addExpense","amount");
    String expenseCategory = prop.getProperty("addExpense","expenseCategory");
    String expenseSubCategory = prop.getProperty("addExpense","expenseSubCategory");
    String addSuccessMessage = prop.getProperty("addExpense","addSuccessMessage");

    @BeforeTest
    public void setUp(){
        GlobalConfigurations.getInstance().openBrowser("chrome");
        GlobalConfigurations.getInstance().navigateToURL();
        login = new UserLogin();
        login.signIn(email,password,loginSuccessMsg);
    }

    @Test
    public void addExpenseEntry(){
        addFinanceEntry = new AddFinanceEntry();
        addFinanceEntry.addNewFinanceEntry(firstEntry,entryType,activityDate,amount,expenseCategory,expenseSubCategory);
        GlobalConfigurations.getInstance().verifyAlert(addSuccessMessage);
    }

    @AfterTest
    public void tearDown(){
        GlobalConfigurations.getInstance().closeBrowser();
    }
}
