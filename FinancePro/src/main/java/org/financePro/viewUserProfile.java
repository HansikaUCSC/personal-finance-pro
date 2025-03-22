package org.financePro;

import org.financePro.utils.GlobalConfigurations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class viewUserProfile {
    WebDriver driver = GlobalConfigurations.getInstance().getDriver();

    public void viewProfile() {
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("btn_dropDownHeader"),30);
        driver.findElement(By.id("btn_dropDownHeader")).click();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("btn_userProfile"),30);
        driver.findElement(By.id("btn_userProfile")).click();
    }
    public void verifyProfileDetails(String expectedName,String expectedEmail){
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath("//h1[(text() =  'User Profile')]"),30);
        String currentName = driver.findElement(By.id("lbl_name")).getText();
        String currentEmail = driver.findElement(By.id("lbl_email")).getText();
        String role = driver.findElement(By.id("lbl_role")).getText();
        assert currentName.equals(expectedName);
        assert currentEmail.equals(expectedEmail);
        assert role.equals("USER");
    }
}
