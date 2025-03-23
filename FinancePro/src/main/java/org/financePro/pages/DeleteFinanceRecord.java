package org.financePro.pages;

import org.financePro.navigation.NavigationToFinancePage;
import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.PropertyFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeleteFinanceRecord {
    WebDriver driver = GlobalConfigurations.getInstance().getDriver();
    PropertyFileReader prop = new PropertyFileReader();
    NavigationToFinancePage navigationToFinancePage;

    String deleteIconElement = prop.getProperty("financePageObjects","delete.icon.element");
    String deleteConfirmPopupHeaderElement = prop.getProperty("financePageObjects","edit.confirm.popup.header.element");
    String deleteConfirmButtonElement = prop.getProperty("financePageObjects","edit.confirm.button.element");
    String deleteSuccessMessage = prop.getProperty("deleteFinance","deleteSuccessMessage");
    String noActivityWarning = prop.getProperty("deleteFinance","noActivityWarning");

    public void deleteFinance(String finalActivity){
        navigationToFinancePage = new NavigationToFinancePage();
        navigationToFinancePage.navigateToFinancePage();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath(deleteIconElement),30);
        driver.findElement(By.xpath(deleteIconElement)).click();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(deleteConfirmPopupHeaderElement),30);
        driver.findElement(By.id(deleteConfirmButtonElement)).click();
        if (finalActivity.equals("true")){
            GlobalConfigurations.getInstance().verifyAlert(noActivityWarning);
        }else {
            GlobalConfigurations.getInstance().verifyAlert(deleteSuccessMessage);
        }
    }
}
