package org.financePro;

import com.mailosaur.MailosaurException;
import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.OTPUtils;

import org.financePro.utils.PropertyFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;


public class UserRegistration {
    WebDriver driver = GlobalConfigurations.getInstance().getDriver();
    OTPUtils otpUtils;
    PropertyFileReader prop = new PropertyFileReader();
    String TermsConditionElement = prop.getProperty("registrationPageObjects","terms.condition.element");
    String TCCheckboxElement = prop.getProperty("registrationPageObjects","terms.checkbox.element");
    String nextButtonElement = prop.getProperty("registrationPageObjects","next.button.element");
    String userNameElement = prop.getProperty("registrationPageObjects","user.name.element");
    String userEmailElement = prop.getProperty("registrationPageObjects","user.email.element");
    String userPasswordElement = prop.getProperty("registrationPageObjects","user.password.element");
    String userConfirmPasswordElement = prop.getProperty("registrationPageObjects","user.confirm.password.element");
    String signUpButtonElement = prop.getProperty("registrationPageObjects","signup.button.element");
    String otpFieldElement = prop.getProperty("registrationPageObjects","otp.element");
    String submitOTPButtonElement = prop.getProperty("registrationPageObjects","otp.submit.button.element");
    String backToLoginButtonElement = prop.getProperty("registrationPageObjects","back.button.element");

    public void signUp(String name,String email,String password, String confrimPassword, String regSuccessMsg, String regCompletionMsg) throws MailosaurException, IOException {
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath(TermsConditionElement),30);
        driver.findElement(By.xpath(TermsConditionElement)).click();
        driver.findElement(By.id(TCCheckboxElement)).click();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(nextButtonElement),30);
        driver.findElement(By.id(nextButtonElement)).click();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(userNameElement),30);
        driver.findElement(By.id(userNameElement)).sendKeys(name);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(userEmailElement),30);
        driver.findElement(By.id(userEmailElement)).sendKeys(email);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(userPasswordElement),30);
        driver.findElement(By.id(userPasswordElement)).sendKeys(password);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(userConfirmPasswordElement),30);
        driver.findElement(By.id(userConfirmPasswordElement)).sendKeys(confrimPassword);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(signUpButtonElement),30);
        driver.findElement(By.id(signUpButtonElement)).click();
        GlobalConfigurations.getInstance().verifyAlert(regSuccessMsg);

        otpUtils = new OTPUtils();
        String otp = otpUtils.getOTPFromMailosaur(email);

        driver.findElement(By.xpath(otpFieldElement)).sendKeys(otp);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(submitOTPButtonElement), 30);
        driver.findElement(By.id(submitOTPButtonElement)).click();
        GlobalConfigurations.getInstance().verifyAlert(regCompletionMsg);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath(backToLoginButtonElement), 30);
        driver.findElement(By.xpath(backToLoginButtonElement)).click();

    }
}
