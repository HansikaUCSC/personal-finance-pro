package org.financePro;

import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.PropertyFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserLogout {
    WebDriver driver = GlobalConfigurations.getInstance().getDriver();
    PropertyFileReader prop = new PropertyFileReader();
    String headerDropdown = prop.getProperty("globalObjects","header.dropdown.element");
    String logoutButton = prop.getProperty("globalObjects","logout.dropdown.option.element");

    public void logOut(){
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(headerDropdown),30);
        driver.findElement(By.id(headerDropdown)).click();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(logoutButton),30);
        driver.findElement(By.id(logoutButton)).click();
    }
    public void verifyLogout(String expectedURL){
        String currentURL = driver.getCurrentUrl();
        assert currentURL.equals(expectedURL);
    }
}
