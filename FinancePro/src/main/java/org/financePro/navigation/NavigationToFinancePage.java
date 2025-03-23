package org.financePro.navigation;

import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.PropertyFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationToFinancePage {
    WebDriver driver = GlobalConfigurations.getInstance().getDriver();
    PropertyFileReader prop = new PropertyFileReader();
    String financeMenuElement = prop.getProperty("globalObjects", "finance.menu.element");
    String financePageHeaderElement = prop.getProperty("globalObjects", "finance.page.header.element");

    public void navigateToFinancePage(){
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath(financeMenuElement),30);
        driver.findElement(By.xpath(financeMenuElement)).click();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath(financePageHeaderElement),30);
    }
}
