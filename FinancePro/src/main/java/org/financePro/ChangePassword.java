package org.financePro;

import org.financePro.utils.GlobalConfigurations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChangePassword {
    WebDriver driver = GlobalConfigurations.getInstance().getDriver();

    public void changePassword(String currentPassword, String newPassword, String confirmPassword){
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("btn_dropDownHeader"),30);
        driver.findElement(By.id("btn_dropDownHeader")).click();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("btn_changePassword"),30);
        driver.findElement(By.id("btn_changePassword")).click();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath("//h1[(text() =  'Change password')]"),30);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("txt_currentPassword"),30);
        driver.findElement(By.id("txt_currentPassword")).clear();
        driver.findElement(By.id("txt_currentPassword")).sendKeys(currentPassword);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("txt_newPassword"),30);
        driver.findElement(By.id("txt_newPassword")).clear();
        driver.findElement(By.id("txt_newPassword")).sendKeys(newPassword);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("txt_confirmNewPassword"),30);
        driver.findElement(By.id("txt_confirmNewPassword")).clear();
        driver.findElement(By.id("txt_confirmNewPassword")).sendKeys(confirmPassword);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("btn_updatePassword"),30);
        driver.findElement(By.id("btn_updatePassword")).click();
    }
    public void verifyChangePassword(){
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath("//div[@role='alert']"),30);
        String alertMessage = driver.findElement(By.xpath("//div[@role='alert']")).getText();
        assert alertMessage.equals("Password Change Successful") : alertMessage;
    }
}
