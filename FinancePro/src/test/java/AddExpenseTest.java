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
    String entryType = prop.getProperty("addExpense","entryType");
    String activityDate = prop.getProperty("addExpense","activityDate");
    String amount = prop.getProperty("addExpense","amount");
    String expenseCategory = prop.getProperty("addExpense","expenseCategory");
    String expenseSubCategory = prop.getProperty("addExpense","expenseSubCategory");
    String addSuccessMessage = prop.getProperty("addExpense","addSuccessMessage");
    String isFirstEntry = prop.getProperty("addExpense","isFirstEntry");

    @BeforeTest
    public void setUp(){
        GlobalConfigurations.getInstance().openBrowser("chrome");
        GlobalConfigurations.getInstance().navigateToURL();
    }

    @Test
    public void addExpenseEntry(){
        login = new UserLogin();
        login.signIn(email,password,loginSuccessMsg);
        addFinanceEntry = new AddFinanceEntry();
        addFinanceEntry.addNewFinanceEntry(isFirstEntry,entryType,activityDate,amount,expenseCategory,expenseSubCategory);
        GlobalConfigurations.getInstance().verifyAlert(addSuccessMessage);
    }

    @AfterTest
    public void tearDown(){
        GlobalConfigurations.getInstance().closeBrowser();
    }
}
