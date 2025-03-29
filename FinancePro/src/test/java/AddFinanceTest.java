import org.financePro.pages.AddFinanceEntry;
import org.financePro.pages.UserLogin;
import org.financePro.utils.CSVReaderUtil;
import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.PropertyFileReader;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;

public class AddFinanceTest {
    UserLogin login;
    AddFinanceEntry addFinanceEntry;
    String csvFilePath = "src/test/resources/financeData.csv"; // Change path accordingly
    PropertyFileReader prop = new PropertyFileReader();
    String email = prop.getProperty("login","email");
    String password = prop.getProperty("login","password");
    String loginSuccessMsg = prop.getProperty("login","successMessage");

    @BeforeTest
    public void setUp() {
        GlobalConfigurations.getInstance().openBrowser("chrome");
        GlobalConfigurations.getInstance().navigateToURL();
        login = new UserLogin();
        login.signIn(email,password,loginSuccessMsg);
    }

    @DataProvider(name = "financeDataProvider")
    public Object[][] getFinanceData() {
        List<String[]> data = CSVReaderUtil.readCSV(csvFilePath);
        return data.toArray(new Object[0][0]);
    }

    @Test(dataProvider = "financeDataProvider")
    public void addFinanceEntry(String isFirstEntry, String entryType, String activityDate,
                               String amount, String incomeCategory, String incomeSubCategory,String addSuccessMessage) {
        addFinanceEntry = new AddFinanceEntry();
        addFinanceEntry.addNewFinanceEntry(isFirstEntry, entryType, activityDate, amount, incomeCategory, incomeSubCategory,addSuccessMessage);
         // Modify as needed
    }

    @AfterTest
    public void tearDown() {
        GlobalConfigurations.getInstance().closeBrowser();
    }
}