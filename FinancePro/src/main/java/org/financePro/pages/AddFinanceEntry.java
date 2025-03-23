package org.financePro.pages;

import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.PropertyFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AddFinanceEntry {
    WebDriver driver = GlobalConfigurations.getInstance().getDriver();
    PropertyFileReader prop = new PropertyFileReader();
    String financeMenuElement = prop.getProperty("addFinancePageObjects", "finance.menu.element");
    String financePageHeaderElement = prop.getProperty("addFinancePageObjects", "finance.page.header.element");
    String addNewEntryButtonElement = prop.getProperty("addFinancePageObjects","add.button.element");
    String incomeRadioElement = prop.getProperty("addFinancePageObjects","income.element");
    String expenseRadioElement = prop.getProperty("addFinancePageObjects","expense.element");
    String activityDateElement = prop.getProperty("addFinancePageObjects","activity.date.element");
    String amountFieldElement = prop.getProperty("addFinancePageObjects","amount.element");
    String categoryFieldElement = prop.getProperty("addFinancePageObjects","category.element");
    String subcategoryFieldElement = prop.getProperty("addFinancePageObjects","subcategory.element");
    String createButtonElement = prop.getProperty("addFinancePageObjects","create.button.element");

    public void addNewFinanceEntry(String firstEntry,String entryType,String activityDate, String amount, String category, String subCategory){
        if (firstEntry.equals("no")){
            GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath(financeMenuElement),30);
            driver.findElement(By.xpath(financeMenuElement)).click();
            GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath(financePageHeaderElement),30);
        }
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath(addNewEntryButtonElement),30);
        driver.findElement(By.xpath(addNewEntryButtonElement)).click();
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
    }
}
