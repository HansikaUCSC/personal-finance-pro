package org.financePro.pages;

import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.PropertyFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class viewUserProfile {
    WebDriver driver = GlobalConfigurations.getInstance().getDriver();
    PropertyFileReader prop = new PropertyFileReader();
    String headerDropdown = prop.getProperty("globalObjects","header.dropdown.element");
    String profileOption = prop.getProperty("globalObjects","profile.dropdown.option.element");
    String pageHeaderElement = prop.getProperty("profilePageObjects","page.header.element");
    String profileNameElement = prop.getProperty("profilePageObjects","profile.name.element");
    String profileEmailElement = prop.getProperty("profilePageObjects","profile.email.element");
    String profileRoleElement = prop.getProperty("profilePageObjects","profile.role.element");

    public void viewProfile() {
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(headerDropdown),30);
        driver.findElement(By.id(headerDropdown)).click();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(profileOption),30);
        driver.findElement(By.id(profileOption)).click();
    }
    public void verifyProfileDetails(String expectedName,String expectedEmail){
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath(pageHeaderElement),30);
        String currentName = driver.findElement(By.id(profileNameElement)).getText();
        String currentEmail = driver.findElement(By.id(profileEmailElement)).getText();
        String role = driver.findElement(By.id(profileRoleElement)).getText();
        assert currentName.equals(expectedName);
        assert currentEmail.equals(expectedEmail);
        assert role.equals("USER");
    }
}
