package org.financePro.pages;

import org.financePro.navigation.NavigationToFinancePage;
import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.PropertyFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class EditFinanceRecord {
    WebDriver driver = GlobalConfigurations.getInstance().getDriver();
    PropertyFileReader prop = new PropertyFileReader();
    NavigationToFinancePage navigationToFinancePage;
    String editIconElement = prop.getProperty("FinancePageObjects","edit.icon.element");
    String editTitleElement = prop.getProperty("FinancePageObjects","edit.page.header.element");
    String activityDateElement = prop.getProperty("FinancePageObjects","activity.date.edit.element");
    String amountFieldElement = prop.getProperty("FinancePageObjects","amount.element");
    String categoryFieldElement = prop.getProperty("FinancePageObjects","category.element");
    String subcategoryFieldElement = prop.getProperty("FinancePageObjects","subcategory.element");
    String updateButtonElement = prop.getProperty("FinancePageObjects","update.button.element");
    String confirmPopupTitleElement = prop.getProperty("FinancePageObjects","edit.confirm.popup.header.element");
    String confirmUpdateButtonElement = prop.getProperty("FinancePageObjects","edit.confirm.update.button.element");

    public void editFinances(String activityDate, String amount, String category, String subCategory){
        navigationToFinancePage = new NavigationToFinancePage();
        navigationToFinancePage.navigateToFinancePage();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath(editIconElement),30);
        driver.findElement(By.xpath(editIconElement)).click();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(editTitleElement),30);
        driver.findElement(By.id(activityDateElement)).clear();
        driver.findElement(By.id(activityDateElement)).sendKeys(activityDate);
        driver.findElement(By.id(amountFieldElement)).clear();
        driver.findElement(By.id(amountFieldElement)).sendKeys(amount);
        Select categorySelect = new Select(driver.findElement(By.id(categoryFieldElement)));
        categorySelect.selectByVisibleText(category);
        Select subCategorySelect = new Select(driver.findElement(By.id(subcategoryFieldElement)));
        subCategorySelect.selectByVisibleText(subCategory);
        GlobalConfigurations.getInstance().waitUntilNextElementClickable(By.id(updateButtonElement),30);
        driver.findElement(By.id(updateButtonElement)).click();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(confirmPopupTitleElement),30);
        driver.findElement(By.id(confirmUpdateButtonElement)).click();
    }
}
