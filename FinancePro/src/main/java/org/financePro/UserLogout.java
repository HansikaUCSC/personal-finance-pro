package org.financePro;

import org.financePro.utils.GlobalConfigurations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserLogout {
    WebDriver driver = GlobalConfigurations.getInstance().getDriver();

    public void logOut(){
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("btn_dropDownHeader"),30);
        driver.findElement(By.id("btn_dropDownHeader")).click();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("btn_logout"),30);
        driver.findElement(By.id("btn_logout")).click();
    }
    public void verifyLogout(String expectedURL){
        String currentURL = driver.getCurrentUrl();
        assert currentURL.equals(expectedURL);
    }
}
