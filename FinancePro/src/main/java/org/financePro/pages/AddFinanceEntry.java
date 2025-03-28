package org.financePro.pages;

import org.financePro.navigation.NavigationToFinancePage;
import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.PropertyFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AddFinanceEntry {
    WebDriver driver = GlobalConfigurations.getInstance().getDriver();
    PropertyFileReader prop = new PropertyFileReader();
    NavigationToFinancePage navigationToFinancePage;

    String addFirstEntryButtonElement = prop.getProperty("financePageObjects","add.first.button.element");
    String addNewEntryButtonElement = prop.getProperty("financePageObjects","add.button.element");
    String incomeRadioElement = prop.getProperty("financePageObjects","income.element");
    String expenseRadioElement = prop.getProperty("financePageObjects","expense.element");
    String activityDateElement = prop.getProperty("financePageObjects","activity.date.element");
    String amountFieldElement = prop.getProperty("financePageObjects","amount.element");
    String categoryFieldElement = prop.getProperty("financePageObjects","category.element");
    String subcategoryFieldElement = prop.getProperty("financePageObjects","subcategory.element");
    String createButtonElement = prop.getProperty("financePageObjects","create.button.element");

    public void addNewFinanceEntry(String isFirstEntry,String entryType,String activityDate, String amount, String category, String subCategory,String addSuccessMessage){
        if (isFirstEntry.equals("true")){
            GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(addFirstEntryButtonElement),30);
            driver.findElement(By.id(addFirstEntryButtonElement)).click();
        }else {
            navigationToFinancePage = new NavigationToFinancePage();
            navigationToFinancePage.navigateToFinancePage();
            GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(addNewEntryButtonElement),30);
            driver.findElement(By.id(addNewEntryButtonElement)).click();
        }
        if(entryType.equals("income")){
            GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(incomeRadioElement),30);
            driver.findElement(By.id(incomeRadioElement)).click();
        }else {
            GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(expenseRadioElement),30);
            driver.findElement(By.id(expenseRadioElement)).click();
        }
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(activityDateElement),30);
        driver.findElement(By.id(activityDateElement)).sendKeys(activityDate);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(amountFieldElement),30);
        driver.findElement(By.id(amountFieldElement)).sendKeys(amount);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(categoryFieldElement),30);
        Select categorySelect = new Select(driver.findElement(By.id(categoryFieldElement)));
        categorySelect.selectByVisibleText(category);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(subcategoryFieldElement),30);
        Select subCategorySelect = new Select(driver.findElement(By.id(subcategoryFieldElement)));
        subCategorySelect.selectByVisibleText(subCategory);
        GlobalConfigurations.getInstance().waitUntilNextElementClickable(By.id(createButtonElement),30);
        driver.findElement(By.id(createButtonElement)).click();
        GlobalConfigurations.getInstance().verifyAlert(addSuccessMessage);
    }
}
