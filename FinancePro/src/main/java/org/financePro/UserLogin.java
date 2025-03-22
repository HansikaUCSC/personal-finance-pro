package org.financePro;

import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.PropertyFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserLogin {
    WebDriver driver = GlobalConfigurations.getInstance().getDriver();
    PropertyFileReader prop = new PropertyFileReader();
    String loginEmail = prop.getProperty("loginPageObjects","email.element");
    String loginPassword = prop.getProperty("loginPageObjects","password.element");
    String loginButton = prop.getProperty("loginPageObjects","loginButton.element");
    String profileIcon = prop.getProperty("loginPageObjects","profileIcon.element");

    public void signIn(String email, String password,String name,String successMessage){
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(loginEmail),30);
        driver.findElement(By.id(loginEmail)).sendKeys(email);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(loginPassword),30);
        driver.findElement(By.id(loginPassword)).sendKeys(password);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(loginButton),30);
        driver.findElement(By.id(loginButton)).click();
        GlobalConfigurations.getInstance().verifyAlert(successMessage);
    }
    public void verifySignIn(String name){
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(profileIcon),30);
        String profileName = driver.findElement(By.id(profileIcon)).getText().trim();
        profileName = profileName.replaceAll("\\s+", " ");
        assert profileName.equals(name);
    }
}
