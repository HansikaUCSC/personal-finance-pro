package org.financePro.pages;

import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.PropertyFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChangePassword {
    WebDriver driver = GlobalConfigurations.getInstance().getDriver();
    PropertyFileReader prop = new PropertyFileReader();
    String headerDropdown = prop.getProperty("globalObjects","header.dropdown.element");
    String changePasswordOption = prop.getProperty("globalObjects","change.password.dropdown.option.element");
    String changePWPageHeader = prop.getProperty("changePasswordPageObjects","page.header.element");
    String currentPWElement = prop.getProperty("changePasswordPageObjects","current.password.element");
    String newPWElement = prop.getProperty("changePasswordPageObjects","new.password.element");
    String confirmPWElement = prop.getProperty("changePasswordPageObjects","confirm.password.element");
    String updatePWButton = prop.getProperty("changePasswordPageObjects","update.password.button.element");

    public void changePassword(String currentPassword, String newPassword, String confirmPassword){
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(headerDropdown),30);
        driver.findElement(By.id(headerDropdown)).click();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(changePasswordOption),30);
        driver.findElement(By.id(changePasswordOption)).click();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath(changePWPageHeader),30);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(currentPWElement),30);
        driver.findElement(By.id(currentPWElement)).clear();
        driver.findElement(By.id(currentPWElement)).sendKeys(currentPassword);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(newPWElement),30);
        driver.findElement(By.id(newPWElement)).clear();
        driver.findElement(By.id(newPWElement)).sendKeys(newPassword);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(confirmPWElement),30);
        driver.findElement(By.id(confirmPWElement)).clear();
        driver.findElement(By.id(confirmPWElement)).sendKeys(confirmPassword);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(updatePWButton),30);
        driver.findElement(By.id(updatePWButton)).click();
    }
}
