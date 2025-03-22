package org.financePro;

import org.financePro.utils.GlobalConfigurations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserLogin {
    WebDriver driver = GlobalConfigurations.getInstance().getDriver();

    public void signIn(String email, String password,String name){
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("email"),30);
        driver.findElement(By.id("email")).sendKeys(email);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("password"),30);
        driver.findElement(By.id("password")).sendKeys(password);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("btn_signIn"),30);
        driver.findElement(By.id("btn_signIn")).click();
    }
    public void verifySignIn(String name){
//        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath("//div[@role='alert']"),30);
//        String alertMessage = driver.findElement(By.xpath("//div[@role='alert']")).getText();
//        assert alertMessage.equals("Login Successful") : alertMessage;
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("lbl_loginUser"),30);
        String profileName = driver.findElement(By.id("lbl_loginUser")).getText().trim();
        profileName = profileName.replaceAll("\\s+", " ");
        assert profileName.equals(name);
    }
}
